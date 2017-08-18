package com.francetelecom.supervisor.dao.src.interfaces;

import java.util.List;

import com.francetelecom.supervisor.dao.src.model.Msg;

public interface InterfaceMsg {
	public List<Msg> getMsgs(int partner_id);

	public void addMsg(Msg msg);

	public int nbreAlarms(String partner_name);

}
