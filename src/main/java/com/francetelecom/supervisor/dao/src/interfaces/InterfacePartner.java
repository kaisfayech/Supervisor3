package com.francetelecom.supervisor.dao.src.interfaces;

import java.util.List;

import com.francetelecom.supervisor.dao.src.model.Partner;

public interface InterfacePartner {
	public List<Partner> getAllparts();

	public void addPart(Partner partenaire);

	public void deletePartnersById(int id);

	public String getUrl(int id);

	public void response(String url, int id);

	public void responseall();

	public List<Partner> getParts(int id);

	public List<Partner> getStats(int id);

}
