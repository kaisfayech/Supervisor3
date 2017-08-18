package com.francetelecom.supervisor.dao.src.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.francetelecom.supervisor.dao.src.interfaces.InterfaceMsg;
import com.francetelecom.supervisor.dao.src.model.Msg;
import com.francetelecom.supervisor.dao.src.repository.RepositoryMsg;

@Service
public class ServiceMsgImpl implements InterfaceMsg {
	@Autowired
	private ServiceLinkImpl serviceLink;
	@Autowired
	private ServicePartnerImpl servicePartner;
	@Autowired
	private RepositoryMsg repositoryMsg;

	public List<Msg> getMsgs(int user_id) {// get messages of alerts today
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(System.currentTimeMillis());
		List<Integer> listL = serviceLink.findLinks(user_id);
		List<Msg> listM = new ArrayList<>();
		for (int i = 0; i < listL.size(); i++) {
			listM.addAll(repositoryMsg.getMsgs(listL.get(i), date));
		}
		return listM;
	}

	public void addMsg(Msg msg) {// add a new message
		repositoryMsg.save(msg);
	}

	public int nbreAlarms(String partner_name) {// get nomber of alarms of a specific partner today
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(System.currentTimeMillis());
		return repositoryMsg.nbreAlarms(servicePartner.getId(partner_name), date);
	}
}
