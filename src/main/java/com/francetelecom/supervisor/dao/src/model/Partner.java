package com.francetelecom.supervisor.dao.src.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Partner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int partner_id;
	private String partner_name;
	private String url;
	private int max_response_time;
	private String mail;
	private int failuremax;
	private int failure_remaining;

	public Partner() {
	}

	public Partner(String partner_name, String url, int max_response_time, String mail, int failuremax,
			int failure_remaining) {
		super();
		this.partner_name = partner_name;
		this.url = url;
		this.max_response_time = max_response_time;
		this.mail = mail;
		this.failuremax = failuremax;
		this.failure_remaining = failure_remaining;
	}

	public int getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(int partner_id) {
		this.partner_id = partner_id;
	}

	public String getPartner_name() {
		return partner_name;
	}

	public void setPartner_name(String partner_name) {
		this.partner_name = partner_name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMax_response_time() {
		return max_response_time;
	}

	public void setMax_response_time(int max_response_time) {
		this.max_response_time = max_response_time;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getFailuremax() {
		return failuremax;
	}

	public void setFailuremax(int failuremax) {
		this.failuremax = failuremax;
	}

	public int getFailure_remaining() {
		return failure_remaining;
	}

	public void setFailure_remaining(int failure_remaining) {
		this.failure_remaining = failure_remaining;
	}

}
