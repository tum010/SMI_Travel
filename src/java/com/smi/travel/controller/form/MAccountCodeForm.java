package com.smi.travel.controller.form;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.smi.travel.datalayer.entity.AccountCode;

public class MAccountCodeForm {
	 
	 private String id;
     private String accCode;
     private String accCode2;
     private String detail;
     private String accType;
     
     private String idModal;
     private String accCodeModal;
     private String accCodeModal2;
     private String detailModal;
     private String accTypeModal;
     
     
     
     
     private List<AccountCode> dataTable;
     
     private Map<String, String> accTypeList = new LinkedHashMap<String, String>();
     
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

	public Map<String, String> getAccTypeList() {
		return accTypeList;
	}

	public void setAccTypeList(Map<String, String> accTypeList) {
		this.accTypeList = accTypeList;
	}

	public String getIdModal() {
		return idModal;
	}

	public void setIdModal(String idModal) {
		this.idModal = idModal;
	}

	public String getAccCodeModal() {
		return accCodeModal;
	}

	public void setAccCodeModal(String accCodeModal) {
		this.accCodeModal = accCodeModal;
	}

	public String getDetailModal() {
		return detailModal;
	}

	public void setDetailModal(String detailModal) {
		this.detailModal = detailModal;
	}

	public String getAccTypeModal() {
		return accTypeModal;
	}

	public void setAccTypeModal(String accTypeModal) {
		this.accTypeModal = accTypeModal;
	}

	public String getAccCode2() {
		return accCode2;
	}

	public void setAccCode2(String accCode2) {
		this.accCode2 = accCode2;
	}

	public String getAccCodeModal2() {
		return accCodeModal2;
	}

	public void setAccCodeModal2(String accCodeModal2) {
		this.accCodeModal2 = accCodeModal2;
	}
}
