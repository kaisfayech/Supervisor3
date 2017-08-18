package com.francetelecom.supervisor.dao.src.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.francetelecom.supervisor.dao.src.interfaces.InterfaceLink;
import com.francetelecom.supervisor.dao.src.model.Link;
import com.francetelecom.supervisor.dao.src.repository.RepositoryLink;

@Service
public class ServiceLinkImpl implements InterfaceLink {
	@Autowired
	private RepositoryLink repositorylink;

	public List<Integer> findLinks(int user_id) {// get all links
		return repositorylink.findLinks(user_id);
	}

	public void addLink(Link link) {// add a link to the table
		repositorylink.save(link);
	}

	public void deleteLinkByPartnerId(int id) {// delete links with a specified partner id
		repositorylink.deleteLinkByPartnerId(id);
	}

	public void deleteLinkByUserId(int id) {// delete links with a specified user_id
		repositorylink.deleteLinkByUserId(id);
	}

	public void deleteLink(int userid, int partnerid) {// delete a link
		repositorylink.deleteLink(userid, partnerid);
	}
}
