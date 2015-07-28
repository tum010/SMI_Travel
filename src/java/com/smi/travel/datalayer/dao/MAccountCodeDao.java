package com.smi.travel.datalayer.dao;

import java.util.List;

import com.smi.travel.datalayer.entity.AccountCode;

public interface MAccountCodeDao {
	
	public List<AccountCode> search(String accCode,String accType);

}
