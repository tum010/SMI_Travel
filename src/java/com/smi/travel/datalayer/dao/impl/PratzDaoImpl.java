package com.smi.travel.datalayer.dao.impl;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smi.travel.datalayer.dao.PratzDao;

@Repository
public class PratzDaoImpl implements PratzDao {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private SessionFactory sessionFactory;

	
	@Transactional
	public String getData() {
		logger.debug(sessionFactory.toString());
		return "Data From Service Implemented Class";
	}

}
