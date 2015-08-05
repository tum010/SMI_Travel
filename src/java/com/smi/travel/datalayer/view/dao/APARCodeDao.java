package com.smi.travel.datalayer.view.dao;

import java.util.List;

import com.smi.travel.datalayer.view.entity.APARCode;

public interface APARCodeDao {
	
	public List<APARCode> search(String code,String name, String type);
	public int update(APARCode aparCode);

}
