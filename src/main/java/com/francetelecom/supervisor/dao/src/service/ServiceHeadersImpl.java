package com.francetelecom.supervisor.dao.src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.francetelecom.supervisor.dao.src.model.Headers;
import com.francetelecom.supervisor.dao.src.repository.RepositoryHeaders;

@Service
public class ServiceHeadersImpl {
	@Autowired
	private RepositoryHeaders repositoryHeaders;

	public void addHeader(Headers header) {// add a header
		repositoryHeaders.save(header);
	}

	public String getName(int id) {
		return repositoryHeaders.getName(id);// get header name
	}

	public String getPassword(int id) {// get header password
		return repositoryHeaders.getPassword(id);
	}

}
