package com.vincent.inc.Saturday.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import org.springframework.data.domain.Example;
import com.vincent.inc.Saturday.dao.OrganizationJoinRequestDao;
import com.vincent.inc.Saturday.model.OrganizationJoinRequest;
import com.vincent.inc.viesspringutils.exception.HttpResponseThrowers;
import com.vincent.inc.viesspringutils.util.DatabaseUtils;
import com.vincent.inc.viesspringutils.util.ReflectionUtils;

@Service
public class OrganizationJoinRequestService {
    public static final String HASH_KEY = "com.vincent.inc.Saturday.service.OrganizationJoinRequestService";

    private DatabaseUtils<OrganizationJoinRequest, Integer> databaseUtils;

    private OrganizationJoinRequestDao organizationJoinRequestDao;

    public OrganizationJoinRequestService(DatabaseUtils<OrganizationJoinRequest, Integer> databaseUtils, OrganizationJoinRequestDao organizationJoinRequestDao) {
        this.databaseUtils = databaseUtils.init(organizationJoinRequestDao, HASH_KEY);
        this.organizationJoinRequestDao = organizationJoinRequestDao;
    }

    public List<OrganizationJoinRequest> getAll() {
        return this.organizationJoinRequestDao.findAll();
    }

    public OrganizationJoinRequest getById(int id) {
        OrganizationJoinRequest organizationJoinRequest = this.databaseUtils.getAndExpire(id);

        if (ObjectUtils.isEmpty(organizationJoinRequest))
            HttpResponseThrowers.throwBadRequest("OrganizationJoinRequest Id not found");

        return organizationJoinRequest;
    }

    public OrganizationJoinRequest tryGetById(int id) {
        OrganizationJoinRequest organizationJoinRequest = this.databaseUtils.getAndExpire(id);
        return organizationJoinRequest;
    }

    public List<OrganizationJoinRequest> getAllByMatchAll(OrganizationJoinRequest organizationJoinRequest) {
        Example<OrganizationJoinRequest> example = ReflectionUtils.getMatchAllMatcher(organizationJoinRequest);
        return this.organizationJoinRequestDao.findAll(example);
    }

    public List<OrganizationJoinRequest> getAllByMatchAny(OrganizationJoinRequest organizationJoinRequest) {
        Example<OrganizationJoinRequest> example = ReflectionUtils.getMatchAnyMatcher(organizationJoinRequest);
        return this.organizationJoinRequestDao.findAll(example);
    }

    public List<OrganizationJoinRequest> getAllByMatchAll(OrganizationJoinRequest organizationJoinRequest, String matchCase) {
        Example<OrganizationJoinRequest> example = ReflectionUtils.getMatchAllMatcher(organizationJoinRequest, matchCase);
        return this.organizationJoinRequestDao.findAll(example);
    }

    public List<OrganizationJoinRequest> getAllByMatchAny(OrganizationJoinRequest organizationJoinRequest, String matchCase) {
        Example<OrganizationJoinRequest> example = ReflectionUtils.getMatchAnyMatcher(organizationJoinRequest, matchCase);
        return this.organizationJoinRequestDao.findAll(example);
    }

    public OrganizationJoinRequest createOrganizationJoinRequest(OrganizationJoinRequest organizationJoinRequest) {
        OrganizationJoinRequest newOrganizationJoinRequest = new OrganizationJoinRequest();
        ReflectionUtils.patchValue(newOrganizationJoinRequest, organizationJoinRequest);
        newOrganizationJoinRequest = this.databaseUtils.saveAndExpire(newOrganizationJoinRequest);
        return newOrganizationJoinRequest;
    }

    public OrganizationJoinRequest modifyOrganizationJoinRequest(int id, OrganizationJoinRequest organizationJoinRequest) {
        OrganizationJoinRequest oldOrganizationJoinRequest = this.getById(id);

        ReflectionUtils.replaceValue(oldOrganizationJoinRequest, organizationJoinRequest);

        oldOrganizationJoinRequest = this.databaseUtils.saveAndExpire(oldOrganizationJoinRequest);

        return oldOrganizationJoinRequest;
    }

    public OrganizationJoinRequest patchOrganizationJoinRequest(int id, OrganizationJoinRequest organizationJoinRequest) {
        OrganizationJoinRequest oldOrganizationJoinRequest = this.getById(id);

        ReflectionUtils.patchValue(oldOrganizationJoinRequest, organizationJoinRequest);

        oldOrganizationJoinRequest = this.databaseUtils.saveAndExpire(oldOrganizationJoinRequest);

        return oldOrganizationJoinRequest;
    }

    public void deleteOrganizationJoinRequest(int id) {
        this.databaseUtils.deleteById(id);
    }
}