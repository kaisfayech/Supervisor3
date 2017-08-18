package com.francetelecom.supervisor.dao.src.interfaces;

import java.util.List;

import com.francetelecom.supervisor.dao.src.model.Link;

public interface InterfaceLink {
	public List<Integer> findLinks(int id);

	public void addLink(Link link);

	public void deleteLinkByPartnerId(int id);

	public void deleteLinkByUserId(int id);

	public void deleteLink(int userid, int partnerid);
}
