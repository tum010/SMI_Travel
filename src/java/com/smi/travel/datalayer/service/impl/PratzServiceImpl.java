package com.smi.travel.datalayer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smi.travel.datalayer.dao.impl.PratzDaoImpl;
import com.smi.travel.datalayer.service.PratzService;
@Service
public class PratzServiceImpl implements PratzService {

	@Autowired
	private PratzDaoImpl pratzDao;
	
	@Transactional
	public String getData() {
		return pratzDao.getData();
	}

}
