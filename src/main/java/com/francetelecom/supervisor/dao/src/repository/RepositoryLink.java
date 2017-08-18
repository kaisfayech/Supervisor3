package com.francetelecom.supervisor.dao.src.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.francetelecom.supervisor.dao.src.model.Link;

@Repository
public interface RepositoryLink extends JpaRepository<Link, Integer> {
	@Query("select partner_id from Link l where l.user_id =?1")
	List<Integer> findLinks(int user_id);

	@Transactional
	@Modifying
	@Query("delete from Link l where l.partner_id = ?1")
	void deleteLinkByPartnerId(int id);

	@Transactional
	@Modifying
	@Query("delete from Link l where l.user_id = ?1")
	void deleteLinkByUserId(int id);

	@Transactional
	@Modifying
	@Query("delete from Link l where l.user_id = ?1 AND l.partner_id = ?2")
	void deleteLink(int user, int partnerid);
}