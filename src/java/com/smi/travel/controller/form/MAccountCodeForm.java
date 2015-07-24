package com.smi.travel.controller.form;

import java.util.List;

import com.smi.travel.datalayer.entity.AccountCode;

public class MAccountCodeForm {
	 
	private String id;
     private String accCode;
     private String detail;
     private String accType;
     
     private List<AccountCode> dataTable;
     
     public String getId() {
         return this.id;
     }
     
     public void setId(String id) {
         this.id = id;
     }
     public String getAccCode() {
         return this.accCode;
     }
     
     public void setAccCode(String accCode) {
         this.accCode = accCode;
     }
     public String getDetail() {
         return this.detail;
     }
     
     public void setDetail(String detail) {
         this.detail = detail;
     }
     public String getAccType() {
         return this.accType;
     }
     
     public void setAccType(String accType) {
         this.accType = accType;
     }

	public List<AccountCode> getDataTable() {
		return dataTable;
	}

	public void setDataTable(List<AccountCode> dataTable) {
		this.dataTable = dataTable;
	}
}
