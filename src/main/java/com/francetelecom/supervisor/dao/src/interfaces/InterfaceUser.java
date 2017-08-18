package com.francetelecom.supervisor.dao.src.interfaces;

import java.util.List;
import com.francetelecom.supervisor.dao.src.model.User;

public interface InterfaceUser {
	public List<User> getAllUsers();

	public List<User> login();

	public void addUser(User user);

	public void deleteUserById(int id);

	public void modifyUser(int id, String pseudo, String password);

}
