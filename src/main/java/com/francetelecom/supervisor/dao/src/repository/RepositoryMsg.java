package com.francetelecom.supervisor.dao.src.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.francetelecom.supervisor.dao.src.model.Msg;

@Repository
public interface RepositoryMsg extends JpaRepository<Msg, String> {
	@Query("select m from Msg m where m.partner_id =?1 and m.msg_date=?2") // get msg from a specified partner id
	List<Msg> getMsgs(int partner_id, String date);

	@Query("select count(partner_id) from Msg m where m.partner_id = ?1 and m.msg_date=?2") // get msg from a specified
																							// partner id
	int nbreAlarms(int partner_id, String date);
}