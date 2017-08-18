package com.francetelecom.supervisor.dao.src.model;

import javax.persistence.Entity;
import javax.persistence.Id;

// 
@Entity

public class Parameters {
	@Id
	private int parameter_id;
	private int partner_id;
	private String parameter_name;
	private String parameter_value;

	public Parameters() {
	}

	public Parameters(int parameter_id, int partner_id, String parameter_name, String parameter_value) {
		super();
		this.parameter_id = parameter_id;
		this.partner_id = partner_id;
		this.parameter_name = parameter_name;
		this.parameter_value = parameter_value;
	}

	public int getParameter_id() {
		return parameter_id;
	}

	public void setParameter_id(int parameter_id) {
		this.parameter_id = parameter_id;
	}

	public int getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(int partner_id) {
		this.partner_id = partner_id;
	}

	public String getParameter_nom() {
		return parameter_name;
	}

	public void setParameter_nom(String parameter_name) {
		this.parameter_name = parameter_name;
	}

	public String getParameter_value() {
		return parameter_value;
	}

	public void setParameter_value(String parameter_value) {
		this.parameter_value = parameter_value;
	}

}
