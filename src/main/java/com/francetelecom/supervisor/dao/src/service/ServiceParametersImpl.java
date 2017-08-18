package com.francetelecom.supervisor.dao.src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francetelecom.supervisor.dao.src.interfaces.InterfaceParameters;
import com.francetelecom.supervisor.dao.src.repository.RepositoryParameters;

@Service
public class ServiceParametersImpl implements InterfaceParameters {
	@Autowired
	private  RepositoryParameters repositoryParameters;
	public  String getName(int id){//add an element to the table supervision
		return repositoryParameters.getName(id);
	}
	public  String getValue(int id){//add an element to the table supervision
		return repositoryParameters.getValue(id);
	}

}
