package com.smi.travel.datalayer.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smi.travel.datalayer.dao.MAccountCodeDao;
import com.smi.travel.datalayer.entity.AccountCode;
import com.smi.travel.datalayer.entity.Customer;
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
		// TODO Auto-generated method stub
		//return sessionFactory.openSession().createQuery("from AccountCode ag where ag.accCode=  and ag.accType=").list();
		
		 String query = "from AccountCode c where c.accCode=:accCode and c.accType=:accType";
	        Session session = this.sessionFactory.openSession();
	        Query q = session.createQuery(query).setParameter(1, accCode).setParameter(2, accType);
	     //   q.setFirstResult(Firstrow);
	     //   q.setMaxResults(maxrow);
	        List<AccountCode> accountCodeList = q.list();
	        session.close();
	        if (accountCodeList.isEmpty()) {
	            return null;
	        }
	        return accountCodeList;
		
	}

}
