/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.smi.travel.datalayer.dao.DefineVarDao;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.util.UtilityFunction;

/**
 *
 * @author Surachai
 */
public class DefineVarImpl extends HibernateDaoSupport implements DefineVarDao {

	private Transaction transaction;
	@Override
	public String saveVariable(List<MDefaultData> datas) {
		int result = 0;
		try {
			for (MDefaultData data : datas) {
				getHibernateTemplate().save(data);
			}
			result = 1;
		} catch (Exception e) {
			result = 0;
		}
		return String.valueOf(result);
	}

	@Override
	public List<MDefaultData> getListDefaultData() {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		List dataList = new ArrayList();
		UtilityFunction util = new UtilityFunction();

		List<Object[]> queryDefineVarList = session.createSQLQuery("SELECT * FROM `M_DEFAULT_DATA`")
				.addScalar("id", Hibernate.INTEGER).addScalar("name", Hibernate.STRING)
				.addScalar("value", Hibernate.STRING).addScalar("type", Hibernate.STRING).list();
		for (Object[] B : queryDefineVarList) {
			MDefaultData defineVar = new MDefaultData();

			defineVar.setId(util.ConvertString(B[0]));
			defineVar.setName(util.ConvertString(B[1]));
			defineVar.setValue(util.ConvertString(B[2]));
			defineVar.setType(util.ConvertString(B[3]));
			dataList.add(defineVar);
		}
		this.getHibernateTemplate().getSessionFactory().close();
		session.close();
		return dataList;
	}
}
