package com.smi.travel.controller.form;

import java.util.ArrayList;
import java.util.List;

public class MAccountCodeForm {
	
	private String code;
	private String accType;
	private String detail;
	
	private List<MAccountCodeForm> dataTable = new ArrayList<MAccountCodeForm>();
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public List<MAccountCodeForm> getDataTable() {
		return dataTable;
	}
	public void setDataTable(List<MAccountCodeForm> dataTable) {
		this.dataTable = dataTable;
	}

}
