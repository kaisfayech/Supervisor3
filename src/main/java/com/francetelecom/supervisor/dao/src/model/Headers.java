package com.francetelecom.supervisor.dao.src.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Headers {
	@Id

	private int partner_id;
	private String header_name;
	private String header_password;

	public Headers() {
	}

	public Headers(int partner_id, String header_name, String header_password) {
		super();
		this.partner_id = partner_id;
		this.header_name = header_name;
		this.header_password = header_password;
	}

	public int getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(int partner_id) {
		this.partner_id = partner_id;
	}

	public String getHeader_name() {
		return header_name;
	}

	public void setHeader_name(String header_name) {
		this.header_name = header_name;
	}

	public String getHeader_password() {
		return header_password;
	}

	public void setHeader_password(String header_password) {
		this.header_password = header_password;
	}

}
