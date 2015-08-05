package com.smi.travel.datalayer.service;

import java.util.List;

import com.smi.travel.datalayer.view.entity.APARCode;

public interface APARCodeService {
	
	public List<APARCode> search(String code,String name,String type);

}
