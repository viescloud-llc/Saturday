package com.vincent.inc.Saturday.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.vincent.inc.Saturday.model.Organization;

public interface OrganizationDao extends JpaRepository<Organization, String> {
@Query(value = "select * from Organization as organization where ", nativeQuery = true)
	public List<Organization> getAllByMatchAll();

	@Query(value = "select * from Organization as organization where ", nativeQuery = true)
	public List<Organization> getAllByMatchAny();
}