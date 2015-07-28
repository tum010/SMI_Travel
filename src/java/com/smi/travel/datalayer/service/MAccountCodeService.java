package com.smi.travel.datalayer.service;

import java.util.List;

import com.smi.travel.datalayer.entity.AccountCode;

public interface MAccountCodeService {
	
	public List<AccountCode> search(String accCode,String accType);

}
