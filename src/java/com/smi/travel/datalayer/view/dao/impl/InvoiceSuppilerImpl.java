/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;
import com.smi.travel.datalayer.view.dao.InvoiceSuppilerDao;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author chonnasith
 */
public class InvoiceSuppilerImpl implements InvoiceSuppilerDao{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<InvoiceSupplier> getListInvoiceSupplier() {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<Object[]> invoiceSupplierList = session.createSQLQuery(" SELECT * FROM `invoice_supplier` ")
                .addScalar("id", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("apcode", Hibernate.STRING)
                .list();
        
        List<InvoiceSupplier> result = new ArrayList<InvoiceSupplier>();
        for (Object[] A : invoiceSupplierList) {
            InvoiceSupplier invoiceSupplier = new InvoiceSupplier();
            invoiceSupplier.setId(util.ConvertString(A[0]));
            invoiceSupplier.setCode(util.ConvertString(A[1]));
            invoiceSupplier.setName(util.ConvertString(A[2]));
            invoiceSupplier.setApcode(util.ConvertString(A[3]));
            result.add(invoiceSupplier);
        }       
        return result;
    }
    
}
