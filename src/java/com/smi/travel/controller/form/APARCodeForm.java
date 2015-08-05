package com.smi.travel.controller.form;

import java.util.ArrayList;
import java.util.List;

import com.smi.travel.datalayer.view.entity.APARCode;

public class APARCodeForm {

	private String code;

	private String name;

	private String type;

	private List<APARCode> dataTable = new ArrayList<APARCode>();
	
	private APARCode aparCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<APARCode> getDataTable() {
		return dataTable;
	}

	public void setDataTable(List<APARCode> dataTable) {
		this.dataTable = dataTable;
	}

	public APARCode getAparCode() {
		return aparCode;
	}

	public void setAparCode(APARCode aparCode) {
		this.aparCode = aparCode;
	}

}
