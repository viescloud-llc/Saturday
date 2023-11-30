package com.vincent.inc.Saturday.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vincent.inc.Saturday.model.OrganizationJoinRequest;

public interface OrganizationJoinRequestDao extends JpaRepository<OrganizationJoinRequest, Integer> {
public OrganizationJoinRequest findByUserId(int userId);
	public List<OrganizationJoinRequest> findAllByUserId(int userId);

	public OrganizationJoinRequest findByOrganizationId(String organizationId);
	public List<OrganizationJoinRequest> findAllByOrganizationId(String organizationId);

	public OrganizationJoinRequest findByMessage(String message);
	public List<OrganizationJoinRequest> findAllByMessage(String message);

	@Query(value = "select * from OrganizationJoinRequest as organizationJoinRequest where organizationJoinRequest.userId = :userId and organizationJoinRequest.organizationId = :organizationId and organizationJoinRequest.message = :message", nativeQuery = true)
	public List<OrganizationJoinRequest> getAllByMatchAll(@Param("userId") int userId, @Param("organizationId") String organizationId, @Param("message") String message);

	@Query(value = "select * from OrganizationJoinRequest as organizationJoinRequest where organizationJoinRequest.userId = :userId or organizationJoinRequest.organizationId = :organizationId or organizationJoinRequest.message = :message", nativeQuery = true)
	public List<OrganizationJoinRequest> getAllByMatchAny(@Param("userId") int userId, @Param("organizationId") String organizationId, @Param("message") String message);
}