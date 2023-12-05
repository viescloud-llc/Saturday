package com.vincent.inc.Saturday.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vincent.inc.Saturday.dao.OrganizationJoinRequestDao;
import com.vincent.inc.Saturday.model.OrganizationJoinRequest;
import com.vincent.inc.viesspringutils.service.ViesService;
import com.vincent.inc.viesspringutils.util.DatabaseUtils;

@Service
public class OrganizationJoinRequestService extends ViesService<OrganizationJoinRequest, Integer, OrganizationJoinRequestDao> {
    
    public OrganizationJoinRequestService(DatabaseUtils<OrganizationJoinRequest, Integer> databaseUtils,
            OrganizationJoinRequestDao repositoryDao) {
        super(databaseUtils, repositoryDao);
    }

    @Override
    protected OrganizationJoinRequest newEmptyObject() {
        return new OrganizationJoinRequest();
    }

    public List<OrganizationJoinRequest> getAllByOrganizationId(String id) {
        return this.repositoryDao.findAllByOrganizationId(id);
    }
}