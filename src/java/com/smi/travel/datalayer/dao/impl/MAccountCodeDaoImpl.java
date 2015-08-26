package com.smi.travel.datalayer.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smi.travel.datalayer.dao.MAccountCodeDao;
import com.smi.travel.datalayer.entity.AccountCode;

@Repository
public class MAccountCodeDaoImpl implements MAccountCodeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<AccountCode> search(String accCode, String accType) {

		String query = "from AccountCode c where c.accCode like :accCode and c.accType like :accType";
		Session session = this.sessionFactory.openSession();
		Query q = session.createQuery(query).setParameter("accCode", String.valueOf(accCode) + "%")
				.setParameter("accType", String.valueOf(accType) + "%");

		List<AccountCode> accountCodeList = q.list();
		session.close();
		if (accountCodeList.isEmpty()) {
			return null;
		}
		return accountCodeList;

	}

	@Override
	public int save(AccountCode accountCode) {
		try{
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(accountCode);
		tx.commit();
		session.close();
		return 1;
		}catch(Exception ex){
			return 0;
		}
	}
	
	@Override
	public int update(AccountCode accountCode) {
		try{
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(accountCode);
		tx.commit();
		session.close();
		return 1;
		}catch(Exception ex){
			return 0;
		}
	}

	@Override
	public int delete(AccountCode accountCode) {
		try{
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.delete(accountCode);
			tx.commit();
			session.close();
			return 1;
			}catch(Exception ex){
				return 0;
			}
	}
}
