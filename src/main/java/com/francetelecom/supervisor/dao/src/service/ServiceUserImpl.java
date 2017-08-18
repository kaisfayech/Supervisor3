package com.francetelecom.supervisor.dao.src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.francetelecom.supervisor.dao.src.interfaces.InterfaceUser;
import com.francetelecom.supervisor.dao.src.model.User;
import com.francetelecom.supervisor.dao.src.repository.RepositoryUser;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceUserImpl implements InterfaceUser {
	@Autowired
	private RepositoryUser repositoryUser;
	@Autowired
	private ServiceLinkImpl serviceLink;

	public List<User> getAllUsers() {// show all users expect admin
		List<User> list = new ArrayList<>();
		repositoryUser.findAll().forEach(list::add);
		list.remove(0);
		return list;
	}

	public List<User> login() {// show all users
		List<User> list = new ArrayList<>();
		repositoryUser.findAll().forEach(list::add);
		return list;
	}

	public void addUser(User user) {// add an user
		repositoryUser.save(user);
	}

	public void deleteUserById(int id) {// delete an user
		repositoryUser.deleteUserById(id);
		serviceLink.deleteLinkByUserId(id);
	}

	public void modifyUser(int id, String pseudo, String password) {// modify an user
		repositoryUser.modifyUser(id, pseudo, password);
	}

}