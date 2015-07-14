package com.smi.travel.datalayer.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.smi.travel.datalayer.dao.DefineVarDao;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.util.UtilityFunction;

public class DefineVarDaoImpl implements DefineVarDao {
	private SessionFactory sessionFactory;
	private Transaction transaction;
	private static final String DEFINEVAR_QUERY = "FROM MDefaultData m where m.id in ('2','3','4')";

	@Override
	public String saveVariable(List<MDefaultData> datas) {
		Session session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		for (MDefaultData data : datas) {
			session.update(data);
		}
		transaction.commit();
		session.close();
		sessionFactory.close();
		return "1";
	}

	@Override
	public List<MDefaultData> getListDefaultData() {
		UtilityFunction util = new UtilityFunction();
        Session session = sessionFactory.openSession();
        List<MDefaultData> list = session.createQuery(DEFINEVAR_QUERY).list();
        if (list.isEmpty()) {
            return null;
        } else {
            return list;
        }
		
	}

	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
