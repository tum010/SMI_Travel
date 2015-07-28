package com.smi.travel.datalayer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smi.travel.datalayer.dao.MAccountCodeDao;
import com.smi.travel.datalayer.entity.AccountCode;
import com.smi.travel.datalayer.service.MAccountCodeService;

@Service
public class MAccountCodeServiceImpl implements MAccountCodeService {
	
	
	@Autowired
	private MAccountCodeDao mAccountCodeDao;

	@Override
	public List<AccountCode> search(String accCode, String accType) {
		return mAccountCodeDao.search(accCode, accType);
	}

}
