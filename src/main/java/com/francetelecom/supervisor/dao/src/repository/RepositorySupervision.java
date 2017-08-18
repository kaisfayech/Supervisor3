package com.francetelecom.supervisor.dao.src.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.francetelecom.supervisor.dao.src.model.Supervision;

@Repository
public interface RepositorySupervision extends JpaRepository<Supervision, String> {
	@Query("select s from Supervision s where s.partner_name = ?1")
	List<Supervision> getStats(String name);

	@Query("select AVG(response_time) from Supervision s where s.partner_name =?1 AND s.response_time>0")
	int getMoyenne(String name);

	@Query("select COUNT(*) from Supervision s where s.partner_name = ?1 AND s.http_status='200'")
	int getSuccess(String name);

	@Query("select COUNT(*) from Supervision s where s.partner_name = ?1")
	int getTotal(String name);

	@Transactional
	@Modifying
	@Query("update Supervision s set s.partner_name=?2 where s.partner_name = ?1")
	void modifyPartnerName(String oldname, String newname);

	@Query("select request_id from Supervision s where  s.request_id= 791")
	int getDate();

	@Query("select  response_time from Supervision s where  s.partner_name=?1 ")
	List<Integer> getCurveTimes(String partner_name);

	@Query("select  date from Supervision s where  s.partner_name=?1 ")
	List<String> getCurveDates(String partner_name);

}