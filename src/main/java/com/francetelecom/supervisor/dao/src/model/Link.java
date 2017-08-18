package com.francetelecom.supervisor.dao.src.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Link {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int link_id;
	private int user_id;
	private int partner_id;

	public Link() {
		// TODO Auto-generated constructor stub
	}

	public Link(int user_id, int partner_id) {
		super();
		this.user_id = user_id;
		this.partner_id = partner_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(int partner_id) {
		this.partner_id = partner_id;
	}

	public int getLink_id() {
		return link_id;
	}

	public void setLink_id(int link_id) {
		this.link_id = link_id;
	}

}
