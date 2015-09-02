/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.TaxInvoiceReport;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;
import com.smi.travel.datalayer.view.dao.TaxInvoiceReportDao;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 *
 * @author chonnasith
 */
public class TaxInvoiceImpl implements TaxInvoiceReportDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public List getTaxInvoice(String taxInvId,int option) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        DecimalFormat df = new DecimalFormat("###,###.00");
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `taxinvoice_view` where `taxinvoice_view`.id =  " + taxInvId)
                .addScalar("id",Hibernate.STRING)
                .addScalar("customer",Hibernate.STRING)
                .addScalar("address",Hibernate.STRING)
                .addScalar("tel",Hibernate.STRING)
                .addScalar("fax",Hibernate.STRING)
                .addScalar("taxno",Hibernate.STRING)
                .addScalar("taxdate",Hibernate.STRING)
                .addScalar("paid",Hibernate.STRING)
                .addScalar("description",Hibernate.STRING)
                .addScalar("nondescription",Hibernate.STRING)
                .addScalar("invdesc",Hibernate.STRING)
                .addScalar("amount",Hibernate.BIG_DECIMAL)
                .addScalar("total",Hibernate.BIG_DECIMAL)
                .addScalar("vat",Hibernate.BIG_DECIMAL)
                .addScalar("grandtotal",Hibernate.BIG_DECIMAL)
                .addScalar("user",Hibernate.STRING)            
                .list();
        
        String invdescTemp = "";
        TaxInvoiceReport taxInvoiceViewTemp  = new TaxInvoiceReport();
        for (Object[] B : QueryList) {
            TaxInvoiceReport taxInvoiceView = new TaxInvoiceReport();
            taxInvoiceView.setId(util.ConvertString(B[0]));
            taxInvoiceView.setCustomer(util.ConvertString(B[1]));
            taxInvoiceView.setAddress(util.ConvertString(B[2]));
            taxInvoiceView.setTel(util.ConvertString(B[3]));
            taxInvoiceView.setFax(util.ConvertString(B[4]));
            taxInvoiceView.setTaxno(util.ConvertString(B[5]));
            taxInvoiceView.setTaxdate(util.ConvertString(B[6]));
            taxInvoiceView.setPaid(util.ConvertString(B[7]));
            taxInvoiceView.setDescription(util.ConvertString(B[8]));
            taxInvoiceView.setNondescription(util.ConvertString(B[9]));
            taxInvoiceView.setInvoice(util.ConvertString(B[10]));
            if(B[12] != null){
                taxInvoiceView.setTotal(df.format(B[12]));
            }
            if(B[13] != null){
                taxInvoiceView.setVat(df.format(B[13]));
            }
            
            taxInvoiceView.setGrandtotal(df.format(B[14]));
            taxInvoiceView.setUser(util.ConvertString(B[15]));
            if(B[11] != null){
                taxInvoiceView.setAmount(df.format(B[11]));
            }
            String total = taxInvoiceView.getGrandtotal().replaceAll(",", "");
            total = total.replaceAll("\\.", ",");
            String[] totals = total.split(",");
            int totalWord = 0;
            totalWord = Integer.parseInt(String.valueOf(totals[0]));
            taxInvoiceView.setTextamount(utilityFunction.convert(totalWord)+" BAHT");
            
            if(option == 1){
                taxInvoiceView.setDescription(taxInvoiceView.getNondescription());
                data.add(taxInvoiceView);
            }else if(option == 2 ){
                //taxInvoiceView.setInvoice("");
                data.add(taxInvoiceView);
            }else if(option == 3 ){
                taxInvoiceView.setAmount(taxInvoiceView.getGrandtotal());
                invdescTemp += taxInvoiceView.getInvoice() + ",";
                taxInvoiceViewTemp = taxInvoiceView;
            }
//            data.add(taxInvoiceView);
        }
        if(option == 3){
            String[] invdescs = invdescTemp.split(",");
            String invdesc = invdescTemp;
            for (int j=0;j<invdescs.length;j++){
                for (int k=j+1;k<invdescs.length;k++){
                    if (k!=j && invdescs[k].equals(invdescs[j])){
                        invdesc = invdescs[k];
                    }
                }
            }
            taxInvoiceViewTemp.setDescription("TOUR" + " " + invdesc);
            taxInvoiceViewTemp.setInvoice("");
            data.add(taxInvoiceViewTemp);
        }
        return data;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    } 

    public UtilityFunction getUtilityFunction() {
        return utilityFunction;
    }

    public void setUtilityFunction(UtilityFunction utilityFunction) {
        this.utilityFunction = utilityFunction;
    }
    
}
