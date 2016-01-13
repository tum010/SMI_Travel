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
                .addScalar("curamount",Hibernate.STRING)   
                .list();
        
        String invdescTemp = "";
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd-MM-yyyy");
        TaxInvoiceReport taxInvoiceViewTemp  = new TaxInvoiceReport();
        for (Object[] B : QueryList) {
            TaxInvoiceReport taxInvoiceView = new TaxInvoiceReport();
            taxInvoiceView.setId(util.ConvertString(B[0]));
            taxInvoiceView.setCustomer(util.ConvertString(B[1]));
            taxInvoiceView.setAddress(util.ConvertString(B[2]));
            taxInvoiceView.setTel(util.ConvertString(B[3]));
            taxInvoiceView.setFax(util.ConvertString(B[4]));
            taxInvoiceView.setTaxno(util.ConvertString(B[5]));
            taxInvoiceView.setTaxdate(String.valueOf(sf.format(util.convertStringToDate(String.valueOf(B[6])))));
            taxInvoiceView.setPaid(util.ConvertString(B[7]));
            taxInvoiceView.setDescription(util.ConvertString(B[8]));
            taxInvoiceView.setNondescription(util.ConvertString(B[9]));
            taxInvoiceView.setInvoice(util.ConvertString(B[10]));
            taxInvoiceView.setGrandtotal(df.format(B[14]));
            taxInvoiceView.setUser(util.ConvertString(B[15]));
            taxInvoiceView.setCuramount(util.ConvertString(B[16]));
            String curamount = "";
            if("THB".equalsIgnoreCase(util.ConvertString(B[16]))){
                curamount = " BAHT ";
//            } else if("JPY".equalsIgnoreCase(util.ConvertString(B[16]))){
//                curamount = " YEN ";
//            } else if("USD".equalsIgnoreCase(util.ConvertString(B[16]))){
//                curamount = " DOLLAR ";
            } else {
                curamount = " " + util.ConvertString(B[16]) + " ";
            }
            if(B[12] != null){
                taxInvoiceView.setTotal(!"0.00".equalsIgnoreCase(util.ConvertString(B[12])) ? df.format(B[12]) : "0.00");
            }
            if(B[13] != null){
                taxInvoiceView.setVat(!"0.00".equalsIgnoreCase(util.ConvertString(B[13])) ? df.format(B[13]) : "0.00");
            }                        
            if(B[11] != null){
                taxInvoiceView.setAmount(!"0.00".equalsIgnoreCase(util.ConvertString(B[11])) ? df.format(B[11]) : "0.00");
            }
//            String total = taxInvoiceView.getGrandtotal().replaceAll(",", "");
//            total = total.replaceAll("\\.", ",");
//            String[] totals = total.split(",");
//            int totalWord = 0;
//            totalWord = Integer.parseInt(String.valueOf(totals[0]));
//            String wordAmount = utilityFunction.convert(totalWord);
//            String first = (wordAmount.substring(0, 1)).toUpperCase();
//            wordAmount = wordAmount.substring(1);
//            wordAmount = first+wordAmount;
//            taxInvoiceView.setTextamount(wordAmount+" "+curamount);
            String string = String.valueOf(taxInvoiceView.getGrandtotal()).replaceAll(",", "");
            String[] parts = string.split("\\.");
            String part1 = parts[0]; // number
            String part2 = parts[1]; // point
            String textmoney = (utilityFunction.convert(Integer.parseInt(part1)));
            String textmoneypoint = (utilityFunction.changPoint(String.valueOf(part2)));
            String totalWord = textmoney + curamount + textmoneypoint;
            System.out.println(" totalWord " + totalWord);
            taxInvoiceView.setTextamount(totalWord.substring(0,1).toUpperCase() + totalWord.substring(1));
            
            if(option == 1){
                taxInvoiceView.setDescription(taxInvoiceView.getNondescription());
                data.add(taxInvoiceView);
            }else if(option == 2 ){
                taxInvoiceView.setInvoice("");
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
