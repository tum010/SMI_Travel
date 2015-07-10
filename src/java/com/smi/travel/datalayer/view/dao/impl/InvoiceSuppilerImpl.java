/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;
import com.smi.travel.datalayer.view.dao.InvoiceSuppilerDao;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.util.UtilityFunction;
import java.util.List;
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
        List<InvoiceSupplier> invoiceSupplierList = session.createSQLQuery(" SELECT * FROM `invoice_supplier` ")
                .addScalar("id", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("apcode", Hibernate.STRING)
                .list();
        
//        for (List<InvoiceSupplier> A : invoiceSupplierList) {
//            InvoiceSupplier invoiceSupplier = new InvoiceSupplier();
//            invoiceSupplier.setId(String.valueOf(A));
//            invoiceSupplier.setCode(String.valueOf(A));
//        }
        
//        for (int i=0;i<=invoiceSupplierList.size();i++) {
//            InvoiceSupplier invoiceSupplierListRe = new InvoiceSupplier();
//            System.out.println("invoiceSupplierList.get(i).getId()" +invoiceSupplierList.get(i).getId());
//            invoiceSupplierListRe.setId(util.ConvertString(invoiceSupplierList.get(i).getId()));
//            invoiceSupplierListRe.setCode(util.ConvertString(invoiceSupplierList.get(i).getCode()));
//            invoiceSupplierListRe.setName(util.ConvertString(invoiceSupplierList.get(i).getName()));
//            invoiceSupplierListRe.setApcode(util.ConvertString(invoiceSupplierList.get(i).getApcode()));
//
//         }
        return invoiceSupplierList;
    }
    
}
