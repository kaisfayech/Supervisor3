package com.francetelecom.supervisor.dao.src.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 
@Entity

public class Msg {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int msg_id;
	private String msg_text;
	private String msg_date;
	private int partner_id;

	public Msg(String msg_text, String msg_date, int partner_id) {
		super();
		this.msg_text = msg_text;
		this.msg_date = msg_date;
		this.partner_id = partner_id;
	}

	public Msg() {

	}

	public int getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}

	public String getMsg_text() {
		return msg_text;
	}

	public void setMsg_text(String msg_text) {
		this.msg_text = msg_text;
	}

	public String getMsg_date() {
		return msg_date;
	}

	public void setMsg_date(String msg_date) {
		this.msg_date = msg_date;
	}

	public int getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(int partner_id) {
		this.partner_id = partner_id;
	}

}
