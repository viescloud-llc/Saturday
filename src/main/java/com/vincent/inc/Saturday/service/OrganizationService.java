package com.vincent.inc.Saturday.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import com.vincent.inc.Saturday.dao.OrganizationDao;
import com.vincent.inc.Saturday.fiegn.AuthenticatorClient;
import com.vincent.inc.Saturday.model.ORole;
import com.vincent.inc.Saturday.model.OUser;
import com.vincent.inc.Saturday.model.Organization;
import com.vincent.inc.Saturday.model.Permission;
import com.vincent.inc.Saturday.model.Authentication.User;
import com.vincent.inc.Saturday.util.DatabaseUtils;
import com.vincent.inc.Saturday.util.ReflectionUtils;
import com.vincent.inc.Saturday.util.Http.HttpResponseThrowers;

@Service
public class OrganizationService {
    public static final String HASH_KEY = "com.vincent.inc.Saturday.service.OrganizationService";

    private DatabaseUtils<Organization, String> databaseUtils;

    private OrganizationDao organizationDao;

    @Autowired
    private AuthenticatorClient authenticatorClient;

    public OrganizationService(DatabaseUtils<Organization, String> databaseUtils, OrganizationDao organizationDao) {
        this.databaseUtils = databaseUtils.init(organizationDao, HASH_KEY);
        this.organizationDao = organizationDao;
    }

    public List<Organization> getAll() {
        return this.organizationDao.findAll();
    }

    public Organization getById(String id) {
        Organization organization = this.databaseUtils.getAndExpire(id);

        if (ObjectUtils.isEmpty(organization))
            HttpResponseThrowers.throwBadRequest("Organization Id not found");

        return organization;
    }

    public Organization tryGetById(String id) {
        Organization organization = this.databaseUtils.getAndExpire(id);
        return organization;
    }

    public List<Organization> getAllByMatchAll(Organization organization) {
        Example<Organization> example = ReflectionUtils.getMatchAllMatcher(organization);
        return this.organizationDao.findAll(example);
    }

    public List<Organization> getAllByMatchAny(Organization organization) {
        Example<Organization> example = ReflectionUtils.getMatchAnyMatcher(organization);
        return this.organizationDao.findAll(example);
    }

    public List<Organization> getAllByMatchAll(Organization organization, String matchCase) {
        Example<Organization> example = ReflectionUtils.getMatchAllMatcher(organization, matchCase);
        return this.organizationDao.findAll(example);
    }

    public List<Organization> getAllByMatchAny(Organization organization, String matchCase) {
        Example<Organization> example = ReflectionUtils.getMatchAnyMatcher(organization, matchCase);
        return this.organizationDao.findAll(example);
    }

    public Organization createOrganization(Organization organization) {
        var newOrganization = new Organization();
        ReflectionUtils.patchValue(newOrganization, organization);
        newOrganization = this.databaseUtils.saveAndExpire(newOrganization);
        return newOrganization;
    }

    public Organization createOrganization(Organization organization, int userId) {
        List<ORole> roles = new ArrayList<>();
        Permission permission = new Permission();
        permission.setAll(true);
        roles.add(ORole.builder().title("OWNER").active(true).permission(permission).build());
        organization.setRoles(roles);
        var newOrganization = new Organization();
        ReflectionUtils.patchValue(newOrganization, organization);
        newOrganization = this.databaseUtils.saveAndExpire(newOrganization);
        roles = newOrganization.getRoles();
        newOrganization.getUsers().add(new OUser(userId, roles));
        newOrganization = this.databaseUtils.saveAndExpire(newOrganization);

        return newOrganization;
    }

    public Organization modifyOrganization(String id, Organization organization) {
        Organization oldOrganization = this.getById(id);

        ReflectionUtils.replaceValue(oldOrganization, organization);

        oldOrganization = this.databaseUtils.saveAndExpire(oldOrganization);

        return oldOrganization;
    }

    public Organization patchOrganization(String id, Organization organization) {
        Organization oldOrganization = this.getById(id);

        ReflectionUtils.patchValue(oldOrganization, organization);

        oldOrganization = this.databaseUtils.saveAndExpire(oldOrganization);

        return oldOrganization;
    }

    public void deleteOrganization(String id) {
        this.databaseUtils.deleteById(id);
    }

    public boolean isInOrganization(Organization organization ,int userId) {
        return organization.getUsers().parallelStream().anyMatch(u -> u.getId() == userId);
    }

    private User getUser(int userId) {
        var user = this.authenticatorClient.getUserById(userId);
        if(ObjectUtils.isEmpty(user))
            HttpResponseThrowers.throwBadRequest("User does not exist");
        return user;
    }

    private void populateUser(OUser eUser, User user) {
        eUser.setUserProfile(user.getUserProfile());
    }
}