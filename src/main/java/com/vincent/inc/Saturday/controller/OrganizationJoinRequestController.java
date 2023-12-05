package com.vincent.inc.Saturday.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;

import com.vincent.inc.Saturday.model.OrganizationJoinRequest;
import com.vincent.inc.Saturday.service.OrganizationJoinRequestService;
import com.vincent.inc.Saturday.service.OrganizationService;
import com.vincent.inc.viesspringutils.exception.HttpResponseThrowers;

@RestController
@RequestMapping("/organizationJoinRequests")
public class OrganizationJoinRequestController {
    @Autowired
    private OrganizationJoinRequestService organizationJoinRequestService;

    @Autowired
    private OrganizationService organizationService;

    @Operation(summary = "Get a list of all OrganizationJoinRequest")
    @GetMapping("/organization/{id}")
    public ResponseEntity<List<OrganizationJoinRequest>> getAll(@RequestHeader("user_id") int userId, @PathVariable("id") String id) {
        if(!OrganizationService.isInOrganization(organizationService.getById(id), userId))
            HttpResponseThrowers.throwBadRequest("User does not belong to organization");
            
        List<OrganizationJoinRequest> organizationJoinRequests = organizationJoinRequestService.getAllByOrganizationId(id);

        if (organizationJoinRequests.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(organizationJoinRequests, HttpStatus.OK);
    }

    @Operation(summary = "Get OrganizationJoinRequest base on id in path variable")
    @GetMapping("{id}")
    public ResponseEntity<OrganizationJoinRequest> getById(@PathVariable("id") int id) {
        OrganizationJoinRequest organizationJoinRequest = organizationJoinRequestService.getById(id);

        return new ResponseEntity<>(organizationJoinRequest, HttpStatus.OK);
    }

    @Operation(summary = "Create a new OrganizationJoinRequest")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrganizationJoinRequest> create(@RequestBody OrganizationJoinRequest organizationJoinRequest) {
        OrganizationJoinRequest savedOrganizationJoinRequest = organizationJoinRequestService.create(organizationJoinRequest);
        return new ResponseEntity<>(savedOrganizationJoinRequest, HttpStatus.CREATED);

    }

    @Operation(summary = "Delete a OrganizationJoinRequest base on id in path variable")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        organizationJoinRequestService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}