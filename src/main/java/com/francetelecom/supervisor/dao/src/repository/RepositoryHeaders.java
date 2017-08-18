package com.francetelecom.supervisor.dao.src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.francetelecom.supervisor.dao.src.model.Headers;

@Repository
public interface RepositoryHeaders extends JpaRepository<Headers, Integer> {
	@Query("select header_name from Headers h where h.partner_id =?1")
	String getName(int id);

	@Query("select header_password from Headers h where h.partner_id =?1")
	String getPassword(int id);

}