/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.RefundAirReportDao;

import com.smi.travel.datalayer.report.model.RefundAirReport;
import com.smi.travel.datalayer.view.entity.RefundTicketView;
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
public class RefundAirReportImpl implements RefundAirReportDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public List getRefundAir(String refundId) {
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        BigDecimal SumTicketAmount = new BigDecimal(0);
        BigDecimal SumReceive = new BigDecimal(0);
        BigDecimal SumPayCus = new BigDecimal(0);
         List<Object[]> QueryRefundList = session.createSQLQuery("SELECT * FROM `refund_ticket_view` where refundid = " + refundId)
                 .addScalar("refundno", Hibernate.STRING)
                 .addScalar("ticketdate", Hibernate.DATE)
                 .addScalar("passenger", Hibernate.STRING)
                 .addScalar("ticketno", Hibernate.STRING)
                 .addScalar("sectorissue", Hibernate.STRING)
                 .addScalar("sectorrefund", Hibernate.STRING)
                 .addScalar("remark", Hibernate.STRING)
                 .addScalar("refundby", Hibernate.STRING)
                 .addScalar("refunddate", Hibernate.DATE)
                 .addScalar("receiveby", Hibernate.STRING)
                 .addScalar("receivedate", Hibernate.DATE)
                 .addScalar("ticketamount", Hibernate.BIG_DECIMAL)
                 .addScalar("address", Hibernate.STRING)
                 .addScalar("totalreceive", Hibernate.BIG_DECIMAL)
                 .addScalar("totalpay", Hibernate.BIG_DECIMAL)
                 .list();
        for (Object[] B : QueryRefundList) {
             RefundAirReport report = new RefundAirReport();
             report.setRefundno(util.ConvertString(B[0]));
             report.setTicketdate(util.SetFormatDate((Date)B[1], "dd-MM-YYYY"));
             report.setPassenger(util.ConvertString(B[2]));
             report.setTicketno(util.ConvertString(B[3]));
             report.setSectorissue(util.ConvertString(B[4]));
             report.setSectorrefund(util.ConvertString(B[5]));
             report.setRemark(util.ConvertString(B[6]));
             report.setRefundby(util.ConvertString(B[7]));
             report.setRefunddate(util.SetFormatDate((Date)B[8], "dd-MM-YYYY"));
             report.setReceiveby(util.ConvertString(B[9]));
             report.setReceivedate(util.SetFormatDate((Date)B[10], "dd-MM-YYYY"));
             //report.setTicketamount(util.setFormatMoney(B[11]));
             report.setAddress(util.ConvertString(B[12]));
             SumTicketAmount = SumTicketAmount.add((BigDecimal) B[11]);
             SumReceive = SumReceive.add((BigDecimal) B[13]);
             SumPayCus = SumPayCus.add((BigDecimal) B[14]);
             data.add(report);
            
        }
        for(int i=0;i<data.size();i++){
            RefundAirReport temp = (RefundAirReport) data.get(i);
            //temp.setTicketamount(util.setFormatMoney(SumTicketAmount));
            temp.setTotalreceive(util.setFormatMoney(SumReceive));
            temp.setTotalpay(util.setFormatMoney(SumPayCus));
            data.set(0, temp);
            
        }
        
        this.sessionFactory.close();
        session.close();
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

    @Override
    public List getRefundTicketDetail(String refundagent, String refundnameby, String passenger, String receivefrom, String receiveto, String paidfrom, String paidto, String typeprint,String printby,String refundby,String sectortoberef) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        Date thisdate = new Date();
        List data = new ArrayList();
        String querydata = "";
        String query = "";
        int checkQuery = 0;
        if( refundagent != null || refundnameby != null || passenger != null || receivefrom != null || receiveto != null || paidfrom != null || paidto != null || refundby != null){
            query = "SELECT * FROM `refund_ticket_detail_view` invm  Where";
        }else{
            query = "SELECT * FROM `refund_ticket_detail_view` invm";
        }
        
        if ((paidfrom != null )&&(!"".equalsIgnoreCase(paidfrom))) {
            if ((paidto != null )&&(!"".equalsIgnoreCase(paidto))) {
                if(checkQuery == 1){
                     query += " and invm.paiddate  BETWEEN  '" + paidfrom + "' AND '" + paidto + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.paiddate  BETWEEN  '" + paidfrom + "' AND '" + paidto + "' ";
                }
            }
        }
        
        if ((receivefrom != null )&&(!"".equalsIgnoreCase(receivefrom))) {
            if ((receiveto != null )&&(!"".equalsIgnoreCase(receiveto))) {
                if(checkQuery == 1){
                     query += " and invm.receivedate  BETWEEN  '" + receivefrom + "' AND '" + receiveto + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.receivedate  BETWEEN  '" + receivefrom + "' AND '" + receiveto + "' ";
                }
            }
        }
        
//        if ((paidfrom != null )&&(!"".equalsIgnoreCase(paidfrom))) {
//            if ((paidto != null )&&(!"".equalsIgnoreCase(paidto))) {
//                if(checkQuery == 1){
//                     query += " and invm.paiddate  BETWEEN  '" + paidfrom + "' AND '" + paidto + "' ";
//                }else{
//                    checkQuery = 1;
//                     query += " invm.paiddate  BETWEEN  '" + paidfrom + "' AND '" + paidto + "' ";
//                }
//            }
//        }
//         
         if((refundagent != null) &&(!"".equalsIgnoreCase(refundagent))){
            if(checkQuery == 1){
                query += " and invm.agent  = '" + refundagent + "' ";
            }else{
                checkQuery = 1;
                query += " invm.agent  = '" + refundagent + "' ";
            }
         }
         
         if((sectortoberef != null) &&(!"".equalsIgnoreCase(sectortoberef))){
            if(checkQuery == 1){
                query += " and invm.sectorrefund  = '" + sectortoberef + "' ";
            }else{
                checkQuery = 1;
                query += " invm.sectorrefund  = '" + sectortoberef + "' ";
            }
         }
         
         if((passenger != null) &&(!"".equalsIgnoreCase(passenger))){
            if(checkQuery == 1){
                query += " and invm.passenger  LIKE  '%" + passenger + "%' ";
            }else{
                checkQuery = 1;
                query += " invm.passenger  LIKE  '%" + passenger + "%' ";
            }
         }
         
         if((refundby != null) &&(!"".equalsIgnoreCase(refundby))){
            if(checkQuery == 1){
                query += " and invm.refundby  = '" + refundby + "' ";
            }else{
                checkQuery = 1;
                query += " invm.refundby  = '" + refundby + "' ";
            }
         }
         
        System.out.println("query : "+query);
        
        List<Object[]> refundTicketDetailList = session.createSQLQuery(query)
                .addScalar("refundno", Hibernate.STRING)
                .addScalar("refunddate", Hibernate.STRING)
                .addScalar("air", Hibernate.STRING)
                .addScalar("docno", Hibernate.STRING)
                .addScalar("airlineagent", Hibernate.STRING)
               .addScalar("agentname", Hibernate.STRING)
                .addScalar("passenger", Hibernate.STRING)
               .addScalar("sectorrefund", Hibernate.STRING)
               .addScalar("receiveairline", Hibernate.STRING)
                .addScalar("receivedate", Hibernate.STRING)
               .addScalar("payno", Hibernate.STRING)
                .addScalar("export", Hibernate.STRING)
               .addScalar("paydate", Hibernate.STRING)
               .addScalar("paycustomer", Hibernate.STRING)
               .addScalar("airlinecomm", Hibernate.STRING)
                .addScalar("airlineagentname", Hibernate.STRING)
                .addScalar("profit", Hibernate.STRING)
                .addScalar("refundby", Hibernate.STRING)
                .addScalar("paiddate", Hibernate.STRING)
                .list();
        if(refundTicketDetailList != null && refundTicketDetailList.size() != 0){
        for (Object[] B : refundTicketDetailList) {
            RefundTicketView refund = new RefundTicketView();
            //header
            if(refundagent != null && !"".equals(refundagent)){
               refund.setRefundagentPage(refundagent);
            }else{
                refund.setRefundagentPage("");
            }
            if(refundnameby != null && !"".equals(refundnameby)){
               refund.setRefundbyPage(refundnameby);
            }else{
                refund.setRefundbyPage("");
            }
            if(passenger != null && !"".equals(passenger)){
               refund.setPassengerPage(passenger);
            }else{
                refund.setPassengerPage("");
            }
            refund.setSelectorrefundPage("");
            Date date = new Date();
            SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = sm.format(date);
            refund.setPrintondatePage(strDate);
            
            refund.setPrintbyPage(printby);
            
            if(receivefrom != null && !"".equals(receivefrom)){
                String receive = ""+ receivefrom +" To " + receiveto;
                refund.setReceivePage(receive);
            }else{
                refund.setReceivePage("");
            }
            if(paidfrom != null && !"".equals(paidfrom)){
                String paid = ""+ paidfrom +" To " + paidto;
                refund.setPaidPage(paid);
            }else{
                refund.setPaidPage("");
            }
            if(typeprint != null && !"".equals(typeprint)){
               refund.setTypeprintPage(typeprint);
            }else{
                refund.setTypeprintPage("");
            }
            
            refund.setRefundno((util.ConvertString(B[0])) == "" ? "" : util.ConvertString(B[0]));
            refund.setRefunddate((util.ConvertString(B[1])) == "" ? "" : util.ConvertString(B[1]));
            refund.setAir((util.ConvertString(B[2])) == "" ? "" : util.ConvertString(B[2]));
            refund.setDocno((util.ConvertString(B[3])) == "" ? "" : util.ConvertString(B[3]));
            refund.setAirlineagent((util.ConvertString(B[4])) == "" ? "" : util.ConvertString(B[4]));
            refund.setAgent((util.ConvertString(B[5])) == "" ? "" : util.ConvertString(B[5]));
            refund.setPassenger((util.ConvertString(B[6])) == "" ? "" : util.ConvertString(B[6]));
            refund.setSectorrefund((util.ConvertString(B[7])) == "" ? "" : util.ConvertString(B[7]));
            refund.setReceiveairline((util.ConvertString(B[8])) == "0" ? "" : util.ConvertString(B[8]));
            refund.setReceivedate((util.ConvertString(B[9])) == "" ? "" : util.ConvertString(B[9]));
            refund.setPayno((util.ConvertString(B[10])) == ""  ? "" : util.ConvertString(B[10]));
            refund.setExport((util.ConvertString(B[11])) == "" ? "" : util.ConvertString(B[11]));
            refund.setPaydate((util.ConvertString(B[12])) == "" ? "" : util.ConvertString(B[12]));
            refund.setPaycustomer((util.ConvertString(B[13])) == "" ? "0" : util.ConvertString(B[13]));
            refund.setAirlinecomm((util.ConvertString(B[14])) == "" ? "0" : util.ConvertString(B[14]));
            refund.setAirlineagentname((util.ConvertString(B[15])) == "" ? "" : util.ConvertString(B[15]));
            refund.setProfit((util.ConvertString(B[16])) == "" ? "0" : util.ConvertString(B[16]));
            refund.setRefundby((util.ConvertString(B[17])) == "" ? "" : util.ConvertString(B[17]));
            refund.setPaiddate((util.ConvertString(B[18])) == "" ? "" : util.ConvertString(B[18]));
            data.add(refund);
        }   
    }else{
            data = null;
    }
    return data;
}
}
