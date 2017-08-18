package com.francetelecom.supervisor.dao.src.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.francetelecom.supervisor.dao.src.model.User;

@Repository
public interface RepositoryUser extends JpaRepository<User, String> {
	@Transactional
	@Modifying
	@Query("delete from User u where u.id = ?1")
	void deleteUserById(int id);

	@Transactional
	@Modifying
	@Query("update User u set u.pseudo=?2,u.password=?3 where u.id = ?1")
	void modifyUser(int id, String user, String password);

}