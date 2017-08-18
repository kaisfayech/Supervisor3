package com.francetelecom.supervisor.dao.src.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.francetelecom.supervisor.dao.src.model.Partner;

@Repository
public interface RepositoryPartner extends JpaRepository<Partner, String> {
	@Query("select url from Partner p where p.partner_id = ?1")
	String getUrl(int id);

	@Query("select partner_name from Partner p where p.url = ?1")
	String getName(String url);

	@Query("select partner_name from Partner p where p.id = ?1")
	String getName(int id);

	@Query("select partner_id from Partner p where p.partner_name = ?1")
	int getId(String url);

	@Transactional
	@Modifying
	@Query("delete from Partner p where p.partner_id = ?1")
	void deletePartnersById(int id);

	@Query("select max_response_time from Partner p where p.partner_id = ?1")
	int getMaxTime(int id);

	@Query("select mail from Partner p where p.partner_id = ?1")
	String getEmail(int id);

	@Query("select failuremax from Partner p where p.partner_id = ?1")
	int getFailuremax(int id);

	@Query("select failure_remaining from Partner p where p.partner_id = ?1")
	int getFailure_remaining(int id);

	@Query("select p from Partner p where p.partner_id =?1")
	Partner getParts(int id);

	@Transactional
	@Modifying
	@Query("update Partner p set p.partner_name=?2,p.url=?3,p.max_response_time=?4,p.mail=?5,p.failuremax=?6 where p.id = ?1")
	void modifyPartner(int id, String name, String url, int max, String mail, int failuremax);

	@Transactional
	@Modifying
	@Query("update Partner p set p.failure_remaining=?2 where p.id = ?1")
	void modifyFailure_remaining(int id, int failure_remaning);

}