package com.smi.travel.datalayer.dao;

import java.util.List;

import com.smi.travel.datalayer.entity.AccountCode;

public interface MAccountCodeDao {
	
	public List<AccountCode> search(String accCode,String accType);
	public int save(AccountCode accountcode);
	public int update(AccountCode accountcode);
	public int delete(AccountCode accountcode);

}
