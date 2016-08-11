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
//        String sqlQuery = " SELECT * FROM `invoice_supplier` ";
        String sqlQuery = "SELECT `h`.`id` AS `id`, `h`.`code` AS `code`, `h`.`name` AS `name`, `h`.`code` AS `apcode`, '' AS `taxno`, '' AS `branchno`, "
                + "'Hotel' AS `type` "
                + "FROM `hotel` `h` "            
                + "UNION ALL "
                + "SELECT `a`.`id` AS `id`, `a`.`code` AS `code`, `a`.`name` AS `name`, `a`.`code` AS `apcode`, `a`.`tax_no` AS `taxno`, "
                + "`a`.`branch` AS `branchno`, 'Agent' AS `type` "
                + "FROM `agent` `a` "               
                + "UNION ALL "
                + "SELECT `s`.`id` AS `id`, `s`.`username` AS `code`, `s`.`name` AS `name`, `s`.`ap_code` AS `apcode`, '' AS `taxno`, '' AS `branchno`, "
                + "'Staff' AS `type` "
                + "FROM `staff` `s` "
                + "UNION ALL "
                + "SELECT `sup`.`id` AS `id`, `sup`.`ap_code` AS `code`, `sup`.`name` AS `name`, `sup`.`ap_code` AS `apcode`, '' AS `taxno`, "
                + "'' AS `branchno`, 'Supplier' AS `type` "
                + "FROM `supplier` `sup` ";

        List<Object[]> invoiceSupplierList = session.createSQLQuery(sqlQuery)
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

    @Override
    public InvoiceSupplier getDataInvoiceSuppiler(String InputInvoiceSupCode) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        InvoiceSupplier invoiceSupplier = new InvoiceSupplier();
        List<Object[]> invoiceSupplierList = session.createSQLQuery(" SELECT * FROM `invoice_supplier` WHERE `invoice_supplier`.code = '" + InputInvoiceSupCode + "'")
                .addScalar("id", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("apcode", Hibernate.STRING)
                .list();
        
      
        for (Object[] A : invoiceSupplierList) {
            invoiceSupplier.setId(util.ConvertString(A[0]));
            invoiceSupplier.setCode(util.ConvertString(A[1]));
            invoiceSupplier.setName(util.ConvertString(A[2]));
            invoiceSupplier.setApcode(util.ConvertString(A[3]));
        }
        return invoiceSupplier;
    }
    
}
