package com.vincent.inc.Saturday.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;

import com.vincent.inc.Saturday.model.Organization;
import com.vincent.inc.Saturday.service.OrganizationService;
import com.vincent.inc.Saturday.util.Http.HttpResponseThrowers;

@RestController
@RequestMapping("/organizations")
class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @Operation(summary = "Get a list of all Organization")
    @GetMapping
    public ResponseEntity<List<?>> getAll(@RequestParam(name = "name", required = false) boolean nameOnly, @RequestHeader("user_id") int userId) {
        List<Organization> organizations = organizationService.getAll();
        organizations = organizations.parallelStream().filter(e -> e.getUsers().parallelStream().anyMatch(u -> u.getId() == userId)).collect(Collectors.toList());
        
        if (organizations.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        if(nameOnly) {
            List<String> names = new ArrayList<>();
            organizations.forEach(e -> names.add(e.getOrganizationProfile().getName()));
            return new ResponseEntity<>(names, HttpStatus.OK); 
        }

        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @Operation(summary = "Get Organization base on id in path variable")
    @GetMapping("{id}")
    public ResponseEntity<Organization> getById(@RequestHeader("user_id") int userId, @PathVariable("id") String id) {
        Organization organization = organizationService.getById(id);

        if(!this.organizationService.isInOrganization(organization, userId))
            HttpResponseThrowers.throwForbidden("User not allow to access this organization");

        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @Operation(summary = "Create a new Organization")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Organization> create(@RequestHeader("user_id") int userId, @RequestBody Organization organization) {
        Organization savedOrganization = organizationService.createOrganization(organization, userId);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @Operation(summary = "Modify a Organization base on id in path variable")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Organization> update(@RequestHeader("user_id") int userId, @PathVariable("id") String id, @RequestBody Organization organization) {
        organization = this.organizationService.modifyOrganization(id, organization, userId);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @Operation(summary = "Patch a Organization base on id in path variable")
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Organization> patch(@RequestHeader("user_id") int userId, @PathVariable("id") String id, @RequestBody Organization organization) {
        organization = this.organizationService.patchOrganization(id, organization, userId);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @Operation(summary = "Delete a Organization base on id in path variable")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> delete(@RequestHeader("user_id") int userId, @PathVariable("id") String id) {
        organizationService.deleteOrganization(id, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}