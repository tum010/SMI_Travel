package com.smi.travel.datalayer.view.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.smi.travel.datalayer.view.dao.APARCodeDao;
import com.smi.travel.datalayer.view.entity.APARCode;

public class APARCodeDaoImpl implements APARCodeDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<APARCode> search(String code,String name, String type) {
		Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        String query ="SELECT * FROM `ap_ar_code` where code like :code  and name like :name and type like :type";
       
        SQLQuery q = session.createSQLQuery(query);
        		q.setParameter("code", String.valueOf(code) + "%").setParameter("name", String.valueOf(name) + "%").setParameter("type", String.valueOf(type) + "%");
        		q.addScalar("id", Hibernate.INTEGER)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("type", Hibernate.STRING)
                .addScalar("apcode", Hibernate.STRING)
                .addScalar("arcode", Hibernate.STRING);
        		List<Object[]> QueryList =  q.list();        
        for (Object[] B : QueryList) {
        	APARCode   a = new  APARCode(); 
        	a.setId(Integer.parseInt(B[0].toString()));
            a.setCode(B[1].toString()); 
            a.setName(B[2].toString());
            a.setType(B[3].toString());
            a.setApcode(B[4].toString());
            a.setArcode(B[5].toString());
             data.add(a);
        }               
        return data;
	}

}
