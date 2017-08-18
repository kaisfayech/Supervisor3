package com.francetelecom.supervisor.dao.src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.francetelecom.supervisor.dao.src.model.Parameters;

@Repository
public interface RepositoryParameters extends JpaRepository<Parameters, Integer> {
	@Query("select parameter_name from Parameters p where p.partner_id = ?1") // get partner url from a specified id
	String getName(int id);

	@Query("select parameter_value from Parameters p where p.partner_id = ?1") // get partner url from a specified id
	String getValue(int id);
}