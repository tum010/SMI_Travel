package com.smi.travel.datalayer.service;

import java.util.List;

import com.smi.travel.datalayer.dao.DefineVarDao;
import com.smi.travel.datalayer.entity.MDefaultData;

public class DefineVarService {

	private DefineVarDao defineVarDao;

	public String saveVariable(List<MDefaultData> data) {
		return defineVarDao.saveVariable(data);
	}

	public List<MDefaultData> getListDefaultData() {
		return defineVarDao.getListDefaultData();
	}

	public void setDefineVarDao(final DefineVarDao defineVarDao) {
		this.defineVarDao = defineVarDao;
	}

}
