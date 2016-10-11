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
        BigDecimal SumClient = new BigDecimal(0);
        BigDecimal SumAirlineCharge = new BigDecimal(0);
         List<Object[]> QueryRefundList = session.createSQLQuery(" SELECT `ref`.`id` AS `refundid`, `ref`.`refund_no` AS `refundno`, `aira`.`ticket_date` AS `ticketdate`, trim( concat( ifnull(`mname`.`name`, ''), ' ', `airp`.`last_name`, ' ', `airp`.`first_name` )) AS `passenger`, `refd`.`ticket_no` AS `ticketno`, `refd`.`ticket_passenger_id` AS `sdsd`, `GET_ROUTING` (`aira`.`id`) AS `sectorissue`, `refd`.`sector_refund` AS `sectorrefund`, `ref`.`remark` AS `remark`, min(`agt`.`bill_name`) AS `refundby`, `ref`.`refund_date` AS `refunddate`, `st1`.`name` AS `receiveby`, `ref`.`receive_date` AS `receivedate`, ifnull(( SELECT sum(( CASE WHEN (`airp`.`passenger_type` = 1) THEN ( `airf`.`ad_price` + `airf`.`ad_tax` ) WHEN (`airp`.`passenger_type` = 2) THEN ( `airf`.`ch_price` + `airf`.`ch_tax` ) WHEN (`airp`.`passenger_type` = 3) THEN ( `airf`.`in_price` + `airf`.`in_tax` ) ELSE 0 END )) FROM `airticket_flight` `airf` WHERE ( `airf`.`airline_id` = `aira`.`id` )), 0 ) AS `ticketamount`, ifnull(`refd`.`receive_airline`, 0) AS `totalreceive`, ifnull(`refd`.`pay_customer`, 0) AS `totalpay`, ifnull(`refd`.`client_charge`, 0) AS `totalclientcharge`, ifnull(`refd`.`airline_charge`, 0) AS `totalairlinecharge`, min(`agt`.`address`) AS `address`, `ref`.`refund_type` AS `refundtype`, `ref`.`other_reason` AS `otherreason` FROM ((((((( `refund_airticket` `ref` JOIN `refund_airticket_detail` `refd` ON (( `refd`.`refund_id` = `ref`.`id` ))) LEFT JOIN `airticket_passenger` `airp` ON (( `airp`.`id` = `refd`.`ticket_passenger_id` ))) LEFT JOIN `airticket_airline` `aira` ON (( `aira`.`id` = `airp`.`airline_id` ))) LEFT JOIN `staff` `st` ON (( `st`.`username` = `ref`.`refund_by` ))) LEFT JOIN `staff` `st1` ON (( `st1`.`username` = `ref`.`receive_by` ))) LEFT JOIN `customer_agent_info` `agt` ON (( `agt`.`bill_to` = `ref`.`refund_by` ))) LEFT JOIN `m_initialname` `mname` ON (( `mname`.`id` = `airp`.`initial_name` ))) WHERE `ref`.`id` = " + refundId + "  GROUP BY `refd`.`id` ")
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
                 .addScalar("totalclientcharge", Hibernate.BIG_DECIMAL)
                 .addScalar("refundtype", Hibernate.STRING)
                 .addScalar("otherreason", Hibernate.STRING)
                 .addScalar("totalairlinecharge", Hibernate.BIG_DECIMAL)
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
             SumTicketAmount = SumTicketAmount.add(B[11] != null ? (BigDecimal) B[11] : new BigDecimal(0));
             SumReceive = SumReceive.add(B[13] != null ? (BigDecimal) B[13] : new BigDecimal(0));
             SumPayCus = SumPayCus.add(B[14] != null ? (BigDecimal) B[14] : new BigDecimal(0));
             SumClient = SumClient.add(B[15] != null ? (BigDecimal) B[15] : new BigDecimal(0));
             SumAirlineCharge = SumAirlineCharge.add(B[18] != null ? (BigDecimal) B[18] : new BigDecimal(0));
             report.setRefundtype(util.ConvertString(B[16]));
             report.setOtherreason(util.ConvertString(B[17]));
             
//             report.setTotalreceive(util.setFormatMoney(SumReceive) != null && !"0.00".equals(util.setFormatMoney(SumReceive)) ? util.setFormatMoney(SumReceive) : "");
//             report.setTotalpay(util.setFormatMoney(SumPayCus) != null && !"0.00".equals(util.setFormatMoney(SumPayCus)) ? util.setFormatMoney(SumPayCus) : "");
//             report.setTotalclientcharge(util.setFormatMoney(SumClient) != null && !"0.00".equals(util.setFormatMoney(SumClient)) ? util.setFormatMoney(SumClient) : "");
             
             data.add(report);
            
        }
        if(QueryRefundList != null){
            for(int i=0; i<data.size(); i++){
                RefundAirReport refundAirReportTemp = (RefundAirReport) data.get(i);
                refundAirReportTemp.setTotalreceive(util.setFormatMoney(SumAirlineCharge) != null && !"0.00".equals(util.setFormatMoney(SumAirlineCharge)) ? util.setFormatMoney(SumAirlineCharge) : "");
                refundAirReportTemp.setTotalpay(util.setFormatMoney(SumPayCus) != null && !"0.00".equals(util.setFormatMoney(SumPayCus)) ? util.setFormatMoney(SumPayCus) : "");
                refundAirReportTemp.setTotalclientcharge(util.setFormatMoney(SumClient) != null && !"0.00".equals(util.setFormatMoney(SumClient)) ? util.setFormatMoney(SumClient) : "");
            }    
        }
//        for(int i=0;i<data.size();i++){
//            RefundAirReport temp = (RefundAirReport) data.get(i);
//            //temp.setTicketamount(util.setFormatMoney(SumTicketAmount));
//            System.out.println("Sum Receive : " +SumReceive + ": " +  util.setFormatMoney(SumReceive) );
//            temp.setTotalreceive(util.setFormatMoney(SumReceive) != null && !"0.00".equals(util.setFormatMoney(SumReceive)) ? util.setFormatMoney(SumReceive) : "");
//            temp.setTotalpay(util.setFormatMoney(SumPayCus) != null && !"0.00".equals(util.setFormatMoney(SumPayCus)) ? util.setFormatMoney(SumPayCus) : "");
//            temp.setTotalclientcharge(util.setFormatMoney(SumClient) != null && !"0.00".equals(util.setFormatMoney(SumClient)) ? util.setFormatMoney(SumClient) : "");
//            data.set(0, temp);
//        }
        
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
        System.out.println("Refund By : " + refundnameby);
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
                query += " and invm.airlineagent  = '" + refundagent + "' ";
            }else{
                checkQuery = 1;
                query += " invm.airlineagent  = '" + refundagent + "' ";
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
                .addScalar("inv", Hibernate.STRING)
                .addScalar("rec", Hibernate.STRING)
                .list();
        if(refundTicketDetailList != null && refundTicketDetailList.size() != 0){
        for (Object[] B : refundTicketDetailList) {
            RefundTicketView refund = new RefundTicketView();
            //header
            if(refundagent != null && !"".equals(refundagent)){
               refund.setRefundagentPage((util.ConvertString(B[5])) == "" ? "" : util.ConvertString(B[5]));
            }else{
                refund.setRefundagentPage("ALL");
            }
            if(refundnameby != null && !"".equals(refundnameby)){
               refund.setRefundbyPage(refundnameby);
            }else{
                refund.setRefundbyPage("ALL");
            }
            if(passenger != null && !"".equals(passenger)){
               refund.setPassengerPage(passenger);
            }else{
                refund.setPassengerPage("ALL");
            }
            if(sectortoberef != null && !"".equals(sectortoberef)){
                refund.setSelectorrefundPage(sectortoberef);
            }else{
                refund.setSelectorrefundPage("ALL");
            }
            Date date = new Date();
            SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = sm.format(date);
            refund.setPrintondatePage(strDate);
            
            refund.setPrintbyPage(printby);
            
            SimpleDateFormat rc = new SimpleDateFormat("dd-MM-yyyy");
            if(receivefrom != null && !"".equals(receivefrom)){
                String receive = ""+ util.convertStringToDateFormat(receivefrom) +" To " + util.convertStringToDateFormat(receiveto);
                refund.setReceivePage(receive);
            }else{
                refund.setReceivePage("ALL");
            }
            if(paidfrom != null && !"".equals(paidfrom)){
                String paid = ""+ paidfrom +" To " + paidto;
                refund.setPaidPage(paid);
            }else{
                refund.setPaidPage("ALL");
            }
            if(typeprint != null && !"".equals(typeprint)){
               refund.setTypeprintPage(typeprint);
            }else{
                refund.setTypeprintPage("ALL");
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
            refund.setInvoice((util.ConvertString(B[19])) == "" ? "" : util.ConvertString(B[19]).replaceAll(",", "\n"));
            refund.setReceipt((util.ConvertString(B[20])) == "" ? "" : util.ConvertString(B[20]).replaceAll(",", "\n"));
            data.add(refund);
        }   
    }else{
            data = null;
    }
    return data;
}
}
