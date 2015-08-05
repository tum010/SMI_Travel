package com.smi.travel.datalayer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smi.travel.datalayer.service.APARCodeService;
import com.smi.travel.datalayer.view.dao.APARCodeDao;
import com.smi.travel.datalayer.view.entity.APARCode;

@Service
public class APARCodeServiceImpl implements APARCodeService {
	
	@Autowired
	private APARCodeDao aPARCodeDao;

	@Override
	public List<APARCode> search(String code, String name, String type) {
		return aPARCodeDao.search(code, name, type);
	}

	@Override
	public int update(APARCode aparCode) {
		return aPARCodeDao.update(aparCode);
	}

}
