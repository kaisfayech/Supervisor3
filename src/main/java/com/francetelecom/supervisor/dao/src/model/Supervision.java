package com.francetelecom.supervisor.dao.src.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Supervision {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int request_id;
	private String partner_name;
	private String date;
	private String http_status;
	private int response_time;

	public Supervision(String partner_name, String date, String http_status, int response_time) {
		super();
		this.partner_name = partner_name;
		this.date = date;
		this.http_status = http_status;
		this.response_time = response_time;
	}

	public Supervision() {

	}

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	public String getPartner_name() {
		return partner_name;
	}

	public void setPartner_name(String partner_name) {
		this.partner_name = partner_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHttp_status() {
		return http_status;
	}

	public void setHttp_status(String http_status) {
		this.http_status = http_status;
	}

	public int getResponse_time() {
		return response_time;
	}

	public void setResponse_time(int response_time) {
		this.response_time = response_time;
	}

}
