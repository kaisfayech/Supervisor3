package com.francetelecom.supervisor.dao.src.interfaces;

import java.util.List;

import com.francetelecom.supervisor.dao.src.model.Supervision;

public interface InterfaceSupervision {
	public void addService(String partner, String date, String response, int response_time);

	public List<Supervision> getStats(String name);

	public int getMoyenne(String name);
}
