/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.OutboundSummaryDao;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MBank;
import com.smi.travel.datalayer.entity.MCity;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.view.entity.OutboundPackageSummaryView;
import com.smi.travel.datalayer.view.entity.OutputTaxView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Kanokporn
 */
public class OutboundSummaryImpl implements OutboundSummaryDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public List getOutboundPackageSummaryReportList(String fromdate, String todate, String cityId, String packageId, String salebyId, String paybyId, String bankId, String statusId) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<OutputTaxView>();
        String paybyname = "ALL";
        String cityname = "ALL";
        String packagename = "ALL";
        String statusname = "ALL";
        String bankname = "ALL";
        
        String query = "SELECT * FROM `outbound_package_summary` op where op.departdate BETWEEN '"+fromdate+"' and '"+todate+"'";
        
        if((cityId != null) && (!"".equalsIgnoreCase(cityId))) {
            query += " and op.city like '%" + cityId + "%'" ;
            
            String querycity = "from MCity c where c.id = '"+cityId+"'";
            List<MCity> mCity = session.createQuery(querycity).list();
            if(!mCity.isEmpty()) {
                cityname = mCity.get(0).getName();
            }
        }
         
        if((packageId != null) && (!"".equalsIgnoreCase(packageId))) {
            query += " and op.packageid = '" + packageId + "'" ;
            
            String querypackage= " from PackageTour pt where pt.id = '"+packageId+"'";
            List<PackageTour> packageTours = session.createQuery(querypackage).list();
            if(!packageTours.isEmpty()) {
                packagename = packageTours.get(0).getName();
            }
        }
        
        if((salebyId != null) && (!"".equalsIgnoreCase(salebyId))) {
            query += " and op.saleby = '" + salebyId + "'" ;
        }
        
        if((paybyId != null) && (!"".equalsIgnoreCase(paybyId))) {
            query += " and op.payid = '" + paybyId + "'" ;
            
            String querypayby = "from MAccpay pay where pay.id = '"+paybyId+"'";
            List<MAccpay> mAccpays = session.createQuery(querypayby).list();
            if(!mAccpays.isEmpty()) {
                paybyname = mAccpays.get(0).getName();
            }
        }
       
        if((bankId != null) && (!"".equalsIgnoreCase(bankId))) {
            query += " and op.bank = '" + bankId + "'" ;
            
            String querybank = "from MBank bank where bank.id = '"+bankId+"'";
            List<MBank> mBank = session.createQuery(querybank).list();
            if(!mBank.isEmpty()) {
                bankname = mBank.get(0).getName();
            }
        }
        
        if((statusId != null) && (!"".equalsIgnoreCase(statusId))) {
            query += " and op.status = '" + statusId + "'" ;
            
            String querystatus = "from MItemstatus s where s.id = '"+statusId+"'";
            List<MItemstatus> maItemstatus = session.createQuery(querystatus).list();
            if(!maItemstatus.isEmpty()) {
                statusname = maItemstatus.get(0).getName();
            }
        }

        query += " ORDER BY op.departdate ";
        
        System.out.println("query : "+query);
        
        List<Object[]> QueryList = session.createSQLQuery(query)
                .addScalar("departdate", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("recondno", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("payby", Hibernate.STRING)
                .addScalar("packagename", Hibernate.STRING)
                .addScalar("period", Hibernate.STRING)
                .addScalar("pax_adult", Hibernate.STRING)
                .addScalar("pax_child", Hibernate.STRING)
                .addScalar("pax_infant", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("profit", Hibernate.STRING)
                .addScalar("transferdate", Hibernate.STRING)
                .addScalar("seller", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("packagecode", Hibernate.STRING)
                .addScalar("netad", Hibernate.STRING)
                .addScalar("netch", Hibernate.STRING)
                .addScalar("netin", Hibernate.STRING)
                .addScalar("banktransfer", Hibernate.STRING)
                .addScalar("statusname", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy");
       
        for (Object[] B : QueryList){
            OutboundPackageSummaryView opsv = new OutboundPackageSummaryView();
            opsv.setHeadercity(cityname);
            opsv.setHeaderpayby(paybyname);
            opsv.setHeaderpackage(packagename);
            opsv.setHeaderbank(bankname);
            opsv.setHeaderstatus(statusname);
            opsv.setHeaderdate(util.ConvertString(dateformat.format(util.convertStringToDate(fromdate))) + " to " + util.ConvertString(dateformat.format(util.convertStringToDate(todate))));
            opsv.setDepartdate("null".equals(String.valueOf(B[0])) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[0])))));
            opsv.setRefno(util.ConvertString(B[1]));
            opsv.setRecondno(util.ConvertString(B[2]));
            opsv.setLeader(util.ConvertString(B[3]));
            opsv.setPayby(util.ConvertString(B[4]));
            opsv.setPackagename(util.ConvertString(B[5]));
            opsv.setPeriod(util.ConvertString(B[6]));
            opsv.setPaxadult(util.ConvertString(B[7]));
            opsv.setPaxchild(util.ConvertString(B[8]));
            opsv.setPaxinfant(util.ConvertString(B[9]));
            opsv.setNet((B[10]) != null ? util.ConvertString(B[10]) : "0.00");
            opsv.setSell((B[11]) != null ? util.ConvertString(B[11]) : "0.00");
            opsv.setProfit((B[12]) != null ? util.ConvertString(B[12]) : "0.00");
            opsv.setTransferdate("".equals(String.valueOf(B[13])) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[13])))));
            opsv.setSeller(util.ConvertString(B[14]));
            opsv.setInvno(util.ConvertString(B[15]));
            opsv.setPackagecode(util.ConvertString(B[16]));
            opsv.setNetad((B[17]) != null ? util.ConvertString(B[17]) : "0.00");
            opsv.setNetch((B[18]) != null ? util.ConvertString(B[18]) : "0.00");
            opsv.setNetin((B[19]) != null ? util.ConvertString(B[19]) : "0.00");
            opsv.setBanktransfer(util.ConvertString(B[20]));
            opsv.setStatusname(util.ConvertString(B[21]));
            opsv.setRemark(util.ConvertString(B[22]));
            data.add(opsv);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
}
