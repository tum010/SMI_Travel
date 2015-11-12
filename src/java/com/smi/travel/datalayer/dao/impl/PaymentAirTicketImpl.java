/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.yy
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PaymentAirTicketDao;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.entity.PaymentAirCredit;
import com.smi.travel.datalayer.entity.PaymentAirDebit;
import com.smi.travel.datalayer.entity.PaymentAirticket;
import com.smi.travel.datalayer.entity.PaymentAirticketFare;
import com.smi.travel.datalayer.entity.PaymentAirticketRefund;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.entity.RefundAirticketDetailView;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.report.model.PaymentAirline;
import com.smi.travel.datalayer.view.entity.PaymentAirFare;
import com.smi.travel.datalayer.view.entity.PaymentAirRefund;
import com.smi.travel.datalayer.view.entity.PaymentAirView;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Jittima
 */
public class PaymentAirTicketImpl implements PaymentAirTicketDao {
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction utilityFunction;
    
    @Override
    public String InsertPaymentAir(PaymentAirticket payAir) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            result = gennaratePaymentRunning();
            payAir.setPayNo(result);
            payAir.setIsExport(0);
            session.save(payAir);
            
            List<PaymentAirticketFare> paymentAirticketFares = payAir.getPaymentAirticketFares();
            
            if(paymentAirticketFares != null){
                for(int i = 0; i < paymentAirticketFares.size(); i++){
                   session.save(paymentAirticketFares.get(i));
                }
            }
            
            List<PaymentAirticketRefund> paymentAirticketRefunds = payAir.getPaymentAirticketRefunds();
            
            if(paymentAirticketRefunds != null){
                for(int i = 0; i < paymentAirticketRefunds.size(); i++){
                   session.save(paymentAirticketRefunds.get(i));
                }
            }
            
            List<PaymentAirCredit> paymentAirCredits = payAir.getPaymentAirCredits();
            
            if(paymentAirCredits != null){
                for(int i = 0; i < paymentAirCredits.size(); i++){
                   session.save(paymentAirCredits.get(i));
                }
            }
            
            List<PaymentAirDebit> paymentAirDebits = payAir.getPaymentAirDebits();
            
            if(paymentAirDebits != null){
                for(int i = 0; i < paymentAirDebits.size(); i++){
                   session.save(paymentAirDebits.get(i));
                }
            }
            
            transaction.commit();
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = null;
        }
        System.out.println("result::"+result);
        return result;
    }

    @Override
    public String UpdatePaymentAir(PaymentAirticket payAir) {
        UtilityFunction util = new UtilityFunction();
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            payAir.setUpdateDate(new Date());
            session.update(payAir);
            List<PaymentAirticketFare> paymentAirticketFares = payAir.getPaymentAirticketFares();
            
            if(paymentAirticketFares != null){
                for(int i = 0; i < paymentAirticketFares.size(); i++){
                    if(paymentAirticketFares.get(i).getId() == null){
                        session.save(paymentAirticketFares.get(i));
                    } else {
                        session.update(paymentAirticketFares.get(i));
                    }             
                }
            }
            
            List<PaymentAirticketRefund> paymentAirticketRefunds = payAir.getPaymentAirticketRefunds();
            
            if(paymentAirticketRefunds != null){
                for(int i = 0; i < paymentAirticketRefunds.size(); i++){
                    if(paymentAirticketRefunds.get(i).getId() == null){
                        session.save(paymentAirticketRefunds.get(i));
                    } else {
                        session.update(paymentAirticketRefunds.get(i));
                    }             
                }
            }
            
            List<PaymentAirCredit> paymentAirCredits = payAir.getPaymentAirCredits();
            
            if(paymentAirCredits != null){
                for(int i = 0; i < paymentAirCredits.size(); i++){
                    if(paymentAirCredits.get(i).getId() == null){
                        session.save(paymentAirCredits.get(i));
                    } else {
                        session.update(paymentAirCredits.get(i));
                    }             
                }
            }
            
            List<PaymentAirDebit> paymentAirDebits = payAir.getPaymentAirDebits();
            
            if(paymentAirDebits != null){
                for(int i = 0; i < paymentAirDebits.size(); i++){
                    if(paymentAirDebits.get(i).getId() == null){
                        session.save(paymentAirDebits.get(i));
                    } else {
                        session.update(paymentAirDebits.get(i));
                    }             
                }
            }
            
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
        System.out.println("result::"+result);
        return result;  
    }

    @Override
    public String DeletePaymentAir(PaymentAirticket payAir) {
        String result = "";
        System.out.println("payAir.getId() :" + payAir.getId());
        if (IsExistPaymentAirticketFare(payAir.getId())) {
            result = "delete unsuccessful.Please delete all payment airticket in this Payment Airticket Fare";
            return result;
        }
        if (IsExistPaymentAirticketRefund(payAir.getId())) {
            result = "delete unsuccessful.Please delete all payment airticket in this Payment Airticket Refund";
            return result;
        }
        
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(payAir);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            getTransaction().rollback();
            result = "fail";
        }
        System.out.println("result::"+result);
        return result;
    }

    @Override
    public String DeletePaymentAirFare(String paymentAirId , String ticketFareId , int option) {
        String result = "";
        PaymentAirticketFare paymentAirticketFare = new PaymentAirticketFare();
        List<PaymentAirticketFare> PaymentAirticketFareList = new ArrayList<PaymentAirticketFare>();
        Session session = this.sessionFactory.openSession();
        if(option == 1){
            String query = "from PaymentAirticketFare pay where pay.paymentAirticket.id = :paymentAirId and pay.ticketFareAirline.id =:ticketFareId ";
            PaymentAirticketFareList = session.createQuery(query).setParameter("ticketFareId", ticketFareId).setParameter("paymentAirId", paymentAirId).list();
            System.out.println(" PaymentAirticketFareList size "+PaymentAirticketFareList.size());
            if (PaymentAirticketFareList.isEmpty()) {
                return null;
            }else{
                paymentAirticketFare =  PaymentAirticketFareList.get(0);
                try {
                    transaction = session.beginTransaction();
                    session.delete(paymentAirticketFare);
                    transaction.commit();
                    result = "success";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    result = "fail";
                }
            }
        
        } else if (option == 2){
            String query = "from PaymentAirticketFare pay where pay.paymentAirticket.id = :paymentAirId";
            PaymentAirticketFareList = session.createQuery(query).setParameter("paymentAirId", paymentAirId).list();
            
            System.out.println(" PaymentAirticketFareList size "+PaymentAirticketFareList.size());
            if (PaymentAirticketFareList.isEmpty()) {
                return null;
            }else{
                for(int i = 0; i < PaymentAirticketFareList.size(); i++){
                    paymentAirticketFare =  PaymentAirticketFareList.get(i);
                    try {
                        transaction = session.beginTransaction();
                        session.delete(paymentAirticketFare);
                        transaction.commit();
                        result = "success";
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = "fail";
                    }
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public String DeletePaymentAirRefund(String paymentAirId,String refundDetailId , int option){
        String result = "";
        PaymentAirticketRefund paymentAirticketRefund = new PaymentAirticketRefund();
        List<PaymentAirticketRefund> paymentAirticketRefundList = new ArrayList<PaymentAirticketRefund>();
        Session session = this.sessionFactory.openSession();
        if(option == 1){
            String query = "from PaymentAirticketRefund pay where pay.paymentAirticket.id = :paymentAirId and pay.refundAirticketDetail.id =:refundDetailId ";
            paymentAirticketRefundList = session.createQuery(query).setParameter("paymentAirId", paymentAirId).setParameter("refundDetailId", refundDetailId).list();
            System.out.println(" PaymentAirticketFareList size "+paymentAirticketRefundList.size());
            if (paymentAirticketRefundList.isEmpty()) {
                return null;
            }else{
                paymentAirticketRefund =  paymentAirticketRefundList.get(0);
                try {
                    transaction = session.beginTransaction();
                    session.delete(paymentAirticketRefund);
                    transaction.commit();
                    result = "success";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    result = "fail";
                }
            }
        
        } else if (option == 2){
            String query = "from PaymentAirticketRefund pay where pay.paymentAirticket.id = :paymentAirId ";
            paymentAirticketRefundList = session.createQuery(query).setParameter("paymentAirId", paymentAirId).list();
            
            System.out.println(" PaymentAirticketFareList size "+paymentAirticketRefundList.size());
            if (paymentAirticketRefundList.isEmpty()) {
                return null;
            }else{
                for(int i = 0; i < paymentAirticketRefundList.size(); i++){
                    paymentAirticketRefund =  paymentAirticketRefundList.get(i);
                    try {
                        transaction = session.beginTransaction();
                        session.delete(paymentAirticketRefund);
                        transaction.commit();
                        result = "success";
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = "fail";
                    }
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public PaymentAirticket getPaymentAirTicketFromPayno(String payNo) {
        PaymentAirticket paymentAirticket = new PaymentAirticket();
        String query = "from PaymentAirticket pay where pay.payNo = :payNo";
        Session session = this.sessionFactory.openSession();
        List<PaymentAirticket> paymentAirticketList = session.createQuery(query).setParameter("payNo", payNo).list();
        session.close();
        if (paymentAirticketList.isEmpty()) {
            return null;
        }else{
            paymentAirticket =  paymentAirticketList.get(0);
        }
        return paymentAirticket;    
    }

    @Override
    public List<TicketFareView> getListTicketFare(String from, String to, String by, String airAgentId,String invoiceSubCode) {
        Session session = this.sessionFactory.openSession();
        String query ="from TicketFareAirline t where";
        String queryOperation = "";
        String Prefix_Subfix ="";
        int check =0;
        int option = 2;
        if(option == 1){
             queryOperation = " = ";
             Prefix_Subfix = "";
        }else if(option == 2){
             queryOperation = " Like ";
             Prefix_Subfix = "%";
        }
        if((by != null) &&(!"".equalsIgnoreCase(by))){
             query += " t.ticketBuy "+queryOperation+" '"+Prefix_Subfix+by+Prefix_Subfix+"'";
             check =1;
        }
        if((airAgentId != null) &&(!"".equalsIgnoreCase(airAgentId))){
            if(check == 1){query += " and ";}
            query += " t.MAirlineAgent "+queryOperation+" '"+Prefix_Subfix+airAgentId+Prefix_Subfix+"'";
            check =1;
        }
        if((from != null) && (!"".equalsIgnoreCase(String.valueOf(from)))){
            if(check == 1){query += " and ";}
            query += " t.issueDate >= "+"'"+from+"'";
            check =1;
        } 
        if((to != null) && (!"".equalsIgnoreCase(String.valueOf(to)))){
            if(check == 1){query += " and ";}
            query += " t.issueDate <= "+"'"+to+"'";
            check =1;
        } 

        if(check == 0){
            query = query.replaceAll("where", " ");
        }
        
        query += " ORDER BY t.Master ASC";
        System.out.println("query : "+query );
        List<TicketFareAirline> listAirline =  session.createQuery(query).list();
        System.out.println("listAirline.size() "+listAirline.size());
        List<TicketFareView> listView = new ArrayList<TicketFareView>();
        
        if(listAirline.isEmpty()){
            System.out.println("List null ");
            return null;
        }else{
            for(int i=0;i<listAirline.size();i++){
                
                System.out.println(" invoiceSubCode " + invoiceSubCode);
                String paymentAirFare = "from PaymentAirticketFare fare where fare.ticketFareAirline.id = :ticketfareId and fare.paymentAirticket.invoiceSup = :invoiceSup";
                List<PaymentAirticketFare> paymentAirticketFares = session.createQuery(paymentAirFare).setParameter("ticketfareId", listAirline.get(i).getId()).setParameter("invoiceSup", invoiceSubCode).list();
                System.out.println(" paymentAirticketFares .size "+ paymentAirticketFares.size());
                if(paymentAirticketFares.isEmpty()){
                    
                    System.out.println(" paymentAirticketFares.isEmpty() ");
                    TicketFareView ticketFareView = new TicketFareView();
                    ticketFareView.setId(String.valueOf(listAirline.get(i).getId()));
                    ticketFareView.setType(String.valueOf(listAirline.get(i).getTicketType()));
                    ticketFareView.setBuy(String.valueOf(listAirline.get(i).getTicketBuy()));
                    ticketFareView.setRouting(String.valueOf(listAirline.get(i).getTicketRouting()));
                    MAirlineAgent mAirlineAgent = new MAirlineAgent();
                    if(listAirline.get(i).getMAirlineAgent() != null){
                        mAirlineAgent.setId(listAirline.get(i).getMAirlineAgent().getId());
                        mAirlineAgent.setCode(listAirline.get(i).getMAirlineAgent().getCode());
                        ticketFareView.setAirline(String.valueOf(mAirlineAgent.getCode()));
                    }
                    ticketFareView.setTicketNo(String.valueOf(listAirline.get(i).getTicketNo()));
                    ticketFareView.setIssueDate(listAirline.get(i).getIssueDate());
                    ticketFareView.setDepartment(listAirline.get(i).getDepartment());
                    ticketFareView.setFare(listAirline.get(i).getTicketFare());
                    ticketFareView.setTax(listAirline.get(i).getTicketTax());
                    ticketFareView.setTicketCommission(listAirline.get(i).getTicketCommission());
                    ticketFareView.setAgentCommission(listAirline.get(i).getAgentCommission());
                    ticketFareView.setDiffVat(listAirline.get(i).getDiffVat());

                    ticketFareView.setTicketIns(listAirline.get(i).getTicketIns());
                    //Ticket Fare --> amount
                    BigDecimal amount = new BigDecimal(BigInteger.ZERO);
                    if("B".equalsIgnoreCase(String.valueOf(listAirline.get(i).getTicketType())) || "TI".equalsIgnoreCase(String.valueOf(listAirline.get(i).getTicketType()))){
                        amount = amount.add(listAirline.get(i).getTicketFare());
                        amount = amount.add(listAirline.get(i).getTicketTax());
                        amount = amount.add(listAirline.get(i).getTicketIns());
                        amount = amount.add(listAirline.get(i).getTicketCommission());
                    }else if("D".equalsIgnoreCase(String.valueOf(listAirline.get(i).getTicketType())) || "TD".equalsIgnoreCase(String.valueOf(listAirline.get(i).getTicketType())) || "A".equalsIgnoreCase(String.valueOf(listAirline.get(i).getTicketType()))){
                        amount = amount.add(listAirline.get(i).getTicketFare());
                        amount = amount.add(listAirline.get(i).getTicketTax());
                        amount = amount.add(listAirline.get(i).getTicketIns());
                    }
                    ticketFareView.setTicketFareAmount(amount);
//                    ticketFareView.setSalePrice(amount);
                    if(listAirline.get(i).getMaster() != null){
                        ticketFareView.setReferenceNo(listAirline.get(i).getMaster().getReferenceNo());
                    }
                    listView.add(ticketFareView);
                }else{
                    System.out.println(" paymentAirticketFares " +  listAirline.get(i).getId() + " ---- " + paymentAirticketFares.size());
                }
            }
        }
        System.out.println("listView " + listView.size());
        session.close();
        this.sessionFactory.close();
        return listView; 
    }

    public String gennaratePaymentRunning(){
        String hql = "from MRunningCode run where run.type =  :type";
        Session session = this.sessionFactory.openSession();
        List<MRunningCode> list = session.createQuery(hql).setParameter("type", "PA").list();
        if (list.isEmpty()) {
            return null;
        }
        
        String code = String.valueOf(list.get(0).getRunning()+1);
        for(int i=code.length();i<6;i++){
            code = "0"+code;
        }
        
        Query query = session.createQuery("update MRunningCode run set run.running = :running" +
    				" where run.type = :type");
        query.setParameter("running", list.get(0).getRunning()+1);
        query.setParameter("type", "PA");
        int result = query.executeUpdate();
        
        session.close();
        this.sessionFactory.close();
        return "A"+code;
    }
    
    private boolean IsExistPaymentAirticketFare(String paymentAirId) {
        boolean result;
        Session session = this.sessionFactory.openSession();
        List<PaymentAirticketFare> list = session.createQuery("from PaymentAirticketFare p WHERE p.paymentAirticket.id = :paymentAirId").setParameter("paymentAirId", paymentAirId).list();
        if (list.isEmpty()) {
            result = false;
        } else {
            result = true;
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
    
    private boolean IsExistPaymentAirticketRefund(String paymentAirId) {
        boolean result;
        Session session = this.sessionFactory.openSession();
        List<PaymentAirticketRefund> list = session.createQuery("from PaymentAirticketRefund p WHERE p.paymentAirticket.id = :paymentAirId").setParameter("paymentAirId", paymentAirId).list();
        if(list.isEmpty()){
            result = false;
        }else{
            result = true;
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
    
    @Override
    public String validateSavePaymentAir(PaymentAirticket payAir) {
        String result = "";
        String query = "from PaymentAirticket pay where pay.payNo = :payNo";
        Session session = this.sessionFactory.openSession();
        List<PaymentAirticket> paymentAirticketList = session.createQuery(query).setParameter("payNo",payAir.getPayNo()).list();
        System.out.println("query : "+query );
        if (paymentAirticketList.isEmpty()){
            System.out.println("+++++++++++ InsertPaymentAir ++++++++++++");
            result = InsertPaymentAir(payAir);
        }else{
            System.out.println("+++++++++++ UpdatePaymentAir ++++++++++++");
            System.out.println("payAir" + payAir.getPayNo());
            result = UpdatePaymentAir(payAir);
        }        
        session.close();
        this.sessionFactory.close();
        return result;
    }

    @Override
    public String addRefundAirTicket(String refundNo,String rowCount,String ticketNoList) {
        String result ="";
        String query = "from RefundAirticketDetail r where r.refundAirticket.refundNo = :refundNo";
        Session session = this.sessionFactory.openSession();
        List<RefundAirticketDetail> refundAirticketDetails = session.createQuery(query).setParameter("refundNo", refundNo).list();
        
        if (refundAirticketDetails.isEmpty()) {
            return null;
        }else{
            result =  buildRefundTicketHTML(refundAirticketDetails , rowCount ,ticketNoList);
        }
        session.close();
        this.sessionFactory.close();
        return result;        
    }
    
    public String buildRefundTicketHTML(List<RefundAirticketDetail> refundAirticketDetails ,String rowCount ,String ticketNoList) {
        StringBuffer html = new StringBuffer();
        List<StringBuffer> htmlList = new ArrayList<StringBuffer>();
        String id = "" ;
        String refund = "";
        String ticketNo = "";
        String department = "";
        String route = "";
        String commission = "";
        String amount = "";
        String payCustomer = "";
        String ticketNoNotAdd = "";
        boolean check = false;
        if (refundAirticketDetails == null || refundAirticketDetails.size() == 0) {
            return html.toString();
        }
        System.out.println("refundAirticketDetails.size() "+refundAirticketDetails.size());
        for(int i = 0 ; i < refundAirticketDetails.size() ; i++ ){
            
            if(String.valueOf(refundAirticketDetails.get(i).getId()) != null){
                id = String.valueOf(refundAirticketDetails.get(i).getId());
            }
            if(String.valueOf(refundAirticketDetails.get(i).getAirComission()) != null){
                commission = String.valueOf(refundAirticketDetails.get(i).getAirComission()) ;
            }
            if(String.valueOf(refundAirticketDetails.get(i).getSectorRefund()) != null){
                route = String.valueOf(refundAirticketDetails.get(i).getSectorRefund());
            }
            if(refundAirticketDetails.get(i).getRefundAirticket() != null){
                if(String.valueOf(refundAirticketDetails.get(i).getRefundAirticket().getRefundNo()) != null){
                    refund = String.valueOf(refundAirticketDetails.get(i).getRefundAirticket().getRefundNo());
                }
            }
            amount = String.valueOf(refundAirticketDetails.get(i).getReceiveAirline());
            payCustomer = String.valueOf(refundAirticketDetails.get(i).getPayCustomer());
            if(refundAirticketDetails.get(i).getAirticketPassenger() != null){
                ticketNo =  String.valueOf(refundAirticketDetails.get(i).getAirticketPassenger().getSeries1())
                            + String.valueOf(refundAirticketDetails.get(i).getAirticketPassenger().getSeries2())
                            + String.valueOf(refundAirticketDetails.get(i).getAirticketPassenger().getSeries3());
                
                if(String.valueOf(refundAirticketDetails.get(i).getAirticketPassenger().getAirticketAirline()) != null
                    && refundAirticketDetails.get(i).getAirticketPassenger().getAirticketAirline().getAirticketPnr() != null
                    && refundAirticketDetails.get(i).getAirticketPassenger().getAirticketAirline().getAirticketPnr().getAirticketBooking() != null
                    && refundAirticketDetails.get(i).getAirticketPassenger().getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster() != null
                    ){
                    department = String.valueOf(refundAirticketDetails.get(i).getAirticketPassenger().getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType());
                    if("I".equalsIgnoreCase(department)){
                        department = "Wendy";
                    }else if("O".equalsIgnoreCase(department)){
                        department = "Outbound";
                    }
                }
            }
            String[] ticketNoTicketFare = ticketNoList.split(",");
            for(int j = 0 ; j < ticketNoTicketFare.length ; j++ ){
                String ticketNoRefund = String.valueOf(ticketNo).trim();
                String ticketNoFare = String.valueOf(ticketNoTicketFare[j]).trim();
                if("customer".equalsIgnoreCase(ticketNoList)){
                    ticketNoFare = String.valueOf(ticketNo).trim();
                }
                if(ticketNoRefund.equalsIgnoreCase(ticketNoFare)){
                    check = true;
                    if(refundAirticketDetails.size() > 1){
                        int countrow = Integer.parseInt(rowCount)+i; 
                        String newrow 
                                = "<tr>"
                                + "<input type='hidden' name='count"+countrow+"' id='count"+countrow+"' value='"+countrow+"'>"
                                + "<input type='hidden' name='tableRefundId"+countrow+"' id='tableRefundId"+countrow+"' value='"+id+"'>"
                                + "<input type='hidden' name='refundNoRow"+countrow+"' id='refundNoRow"+countrow+"' value='"+refund+"'>"
        //                        + "<input type='hidden' name='payCustomer"+countrow+"' id='payCustomer"+countrow+"' value='"+payCustomer+"'>"
                                + "<td align='center'>"+ (refund  == "null" ? "" : refund )+ "</td>"
                                + "<td align='left'>" + (ticketNo == "null" ? "": ticketNo )+  "</td>"
                                + "<td align='left'>" + (department == "null" ? "": department )+ "</td>"
                                + "<td align='center'>" + (route == "null" ? "" : route )+  "</td>"
                                + "<td align='right' class='money'>" + (commission == "null" ? "": commission )+  "</td>"
                                + "<td class='money'>" + (amount == "null" ? "" : amount )+  "</td>"
                                + "<td class='money'>" + (payCustomer == "null" ? "" : payCustomer )+  "</td>"
                                + "<td><center><a class=\"remCF\"><span onclick=\"deleteRefund('"+id+"','"+refund+"','"+countrow+"')\" class=\"glyphicon glyphicon-remove deleteicon \"></span></center></td>"
                                + "</tr>";
                        System.out.println("newrow [[[[[[[ "+newrow +" ]]]]");
                        html.append(newrow);
                    }else{
                        System.out.println(" uuuu ");
                        String newrow
                                = "<tr>"
                                + "<input type='hidden' name='count"+rowCount+"' id='count"+rowCount+"' value='"+rowCount+"'>"
                                + "<input type='hidden' name='tableRefundId"+rowCount+"' id='tableRefundId"+rowCount+"' value='"+id+"'>"
                                + "<input type='hidden' name='refundNoRow"+rowCount+"' id='refundNoRow"+rowCount+"' value='"+refund+"'>"
        //                        + "<input type='hidden' name='payCustomer"+rowCount+"' id='payCustomer"+rowCount+"' value='"+payCustomer+"'>"
                                + "<td align='center'>" + (refund  == "null" ?  "" : refund )+ "</td>"
                                + "<td align='left'>" + (ticketNo == "null" ? "" : ticketNo )+  "</td>"
                                + "<td align='left'>" + (department == "null" ? "": department )+ "</td>"
                                + "<td align='center'>" + (route == "null" ? "" : route )+  "</td>"
                                + "<td align='right' class='money'>" + (commission == "null" ? "" : commission )+  "</td>"
                                + "<td class='money'>" + (amount == "null" ? "" : amount )+  "</td>"
                                + "<td class='money'>" + (payCustomer == "null" ? "" : payCustomer )+  "</td>"
                                + "<td><center><a class=\"remCF\"><span onclick=\"deleteRefund('"+id+"','"+refund+"','"+rowCount+"')\" class=\"glyphicon glyphicon-remove deleteicon \"></span></center></td>"
                                + "</tr>";
                        System.out.println("newrow [[[[[[[ "+newrow +" ]]]]");
                        html.append(newrow);
                    }
                }
            }
            if(!check){
                ticketNoNotAdd += "|" + ticketNo ;
                
            }
        }
        html.append(ticketNoNotAdd);
        htmlList.add(html);
        if(htmlList.size() == 0){
            return null;
        }
        return htmlList.toString();
    }    

    @Override
    public List<TicketFareView> getTicketFareViewsByPaymentAirId(String paymentAirId) {
        PaymentAirticketFare paymentAirticketFare = new PaymentAirticketFare();
        String query = "from PaymentAirticketFare pay where pay.paymentAirticket.id = :paymentAirId";
        Session session = this.sessionFactory.openSession();
        System.out.println(" paymentAirId "+paymentAirId);
        List<TicketFareView> listView = new ArrayList<TicketFareView>();
        List<PaymentAirticketFare> PaymentAirticketFareList = session.createQuery(query).setParameter("paymentAirId", paymentAirId).list();
        System.out.println(" PaymentAirticketFareList size "+PaymentAirticketFareList.size());
        
        
        if (PaymentAirticketFareList.isEmpty()) {
            return null;
        }else{
            for(int i=0;i<PaymentAirticketFareList.size();i++){
                String ticketType = "";
                BigDecimal ticketfareamount = new BigDecimal(BigInteger.ZERO);
                
                TicketFareView ticketFareView = new TicketFareView();
                TicketFareAirline ticketFareAirline = PaymentAirticketFareList.get(i).getTicketFareAirline() ;
                if(ticketFareAirline != null){
                    
                    ticketFareView.setId(String.valueOf(ticketFareAirline.getId()));
                    ticketFareView.setType(String.valueOf(ticketFareAirline.getTicketType()));
                    ticketFareView.setBuy(String.valueOf(ticketFareAirline.getTicketBuy()));
                    ticketFareView.setRouting(String.valueOf(ticketFareAirline.getTicketRouting()));
                    MAirlineAgent mAirlineAgent = new MAirlineAgent();
                    
                    if(ticketFareAirline.getMAirlineAgent() != null){
                        mAirlineAgent.setId(ticketFareAirline.getMAirlineAgent().getId());
                        mAirlineAgent.setCode(ticketFareAirline.getMAirlineAgent().getCode());
                        ticketFareView.setAirline(String.valueOf(mAirlineAgent.getCode()));
                    }
                    ticketFareView.setTicketNo(String.valueOf(ticketFareAirline.getTicketNo()));
                    ticketFareView.setIssueDate(ticketFareAirline.getIssueDate());
                    ticketFareView.setDepartment(ticketFareAirline.getDepartment());
                    ticketFareView.setFare(ticketFareAirline.getTicketFare());
                    ticketFareView.setTax(ticketFareAirline.getTicketTax());
                    ticketFareView.setTicketCommission(ticketFareAirline.getTicketCommission());
                    ticketFareView.setAgentCommission(ticketFareAirline.getAgentCommission());
                    ticketFareView.setDiffVat(ticketFareAirline.getDiffVat());
                    ticketFareView.setTicketIns(ticketFareAirline.getTicketIns());
                    ticketFareView.setSalePrice(ticketFareAirline.getSalePrice());
                    ticketType = String.valueOf(ticketFareAirline.getTicketType());
                    System.out.println(" +++++++++++++ ticketType +++++++++++++ "+ ticketType);
                    if("B".equalsIgnoreCase(ticketType) || "TI".equalsIgnoreCase(ticketType)){
                        ticketfareamount = ticketfareamount.add(ticketFareAirline.getTicketFare());
                        ticketfareamount = ticketfareamount.add(ticketFareAirline.getTicketTax());
                        ticketfareamount = ticketfareamount.add(ticketFareAirline.getTicketIns());
                        ticketfareamount = ticketfareamount.add(ticketFareAirline.getTicketCommission());
                    }else if("D".equalsIgnoreCase(ticketType) || "A".equalsIgnoreCase(ticketType) || "TD".equalsIgnoreCase(ticketType)){
                        ticketfareamount = ticketfareamount.add(ticketFareAirline.getTicketFare());
                        ticketfareamount = ticketfareamount.add(ticketFareAirline.getTicketTax());
                        ticketfareamount = ticketfareamount.add(ticketFareAirline.getTicketIns());
                    }
                    ticketFareView.setTicketFareAmount(ticketfareamount);
                    if(ticketFareAirline.getMaster() != null){
                        ticketFareView.setReferenceNo(ticketFareAirline.getMaster().getReferenceNo());
                    }
                listView.add(ticketFareView);
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        return listView;
    }

    @Override
    public List<RefundAirticketDetailView> getRefundDetailByPaymentAirId(String paymentAirId) {
        String query = "from PaymentAirticketRefund pay where pay.paymentAirticket.id = :paymentAirId";
        Session session = this.sessionFactory.openSession();
        System.out.println(" paymentAirId " +paymentAirId);
        List<RefundAirticketDetailView> listView = new ArrayList<RefundAirticketDetailView>();
        List<PaymentAirticketRefund> PaymentAirticketRefundList = session.createQuery(query).setParameter("paymentAirId", paymentAirId).list();
        System.out.println(" PaymentAirticketRefundList size "+PaymentAirticketRefundList.size());
        if (PaymentAirticketRefundList.isEmpty()){
            return null;
        }else{
            for(int i=0;i<PaymentAirticketRefundList.size();i++){
                RefundAirticketDetailView refundView = new RefundAirticketDetailView();
                RefundAirticketDetail refundAirticketDetail = PaymentAirticketRefundList.get(i).getRefundAirticketDetail();
                if(refundAirticketDetail != null){
                    refundView.setId(refundAirticketDetail.getId());
                    refundView.setRoute(refundAirticketDetail.getSectorRefund());
                    refundView.setCommisssion(refundAirticketDetail.getAirComission());
                    refundView.setPayCus(refundAirticketDetail.getPayCustomer());
                    refundView.setAmount(refundAirticketDetail.getReceiveAirline());
                    if(refundAirticketDetail.getRefundAirticket() != null){
                        refundView.setRefundNo(refundAirticketDetail.getRefundAirticket().getRefundNo());
                    }
                    if(refundAirticketDetail.getAirticketPassenger() != null){
                        refundView.setTicketNo(refundAirticketDetail.getAirticketPassenger().getSeries1()+refundAirticketDetail.getAirticketPassenger().getSeries2()+refundAirticketDetail.getAirticketPassenger().getSeries3());
                        
                        if(refundAirticketDetail.getAirticketPassenger().getAirticketAirline() != null
                            && refundAirticketDetail.getAirticketPassenger().getAirticketAirline().getAirticketPnr() != null
                            && refundAirticketDetail.getAirticketPassenger().getAirticketAirline().getAirticketPnr().getAirticketBooking() != null
                            && refundAirticketDetail.getAirticketPassenger().getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster() != null
                            ){
                            refundView.setDepartment(refundAirticketDetail.getAirticketPassenger().getAirticketAirline().getAirticketPnr().getAirticketBooking().getMaster().getBookingType());
                        }                        
                    }

                }
                listView.add(refundView);
            }
        }
        session.close();
        this.sessionFactory.close();
        return listView;
    }
    
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
    public List<PaymentAirCredit> getPaymentAirCreditByPaymentAirId(String paymentAirId) {
        String query = "from PaymentAirCredit pay where pay.paymentAirticket.id = :paymentAirId";
        Session session = this.sessionFactory.openSession();
        List<PaymentAirCredit> list = session.createQuery(query).setParameter("paymentAirId", paymentAirId).list();

        if (list.isEmpty()){
            return null;
        }
        
        session.close();
        this.sessionFactory.close();
        return list;
    }

    @Override
    public String DeletePaymentAirCredit(String paymentAirId, String paymentCreditId) {
        String result = "";
        PaymentAirCredit paymentAirCredit = new PaymentAirCredit();
        List<PaymentAirCredit> paymentAirCreditList = new ArrayList<PaymentAirCredit>();
        Session session = this.sessionFactory.openSession();
        if(paymentAirId.isEmpty() || "".equals(paymentAirId)){
            String query = "from PaymentAirCredit pay where pay.id = :paymentCreditId";
            paymentAirCreditList = session.createQuery(query).setParameter("paymentCreditId", paymentCreditId).list();
            System.out.println(" Delete paymentAirCreditList size (1) "+paymentAirCreditList.size());
            if (paymentAirCreditList.isEmpty()) {
                return null;
            }else{
                paymentAirCredit =  paymentAirCreditList.get(0);
                try {
                    transaction = session.beginTransaction();
                    session.delete(paymentAirCredit);
                    transaction.commit();
                    result = "success";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    result = "fail";
                }
            }
        } else { 
            String query = "from PaymentAirCredit pay where pay.id = :paymentCreditId and pay.paymentAirticket.id =:paymentAirId ";
            paymentAirCreditList = session.createQuery(query).setParameter("paymentCreditId", paymentCreditId).setParameter("paymentAirId", paymentAirId).list();
            System.out.println(" Delete ReceiptDetailList size "+paymentAirCreditList.size());
            if (paymentAirCreditList.isEmpty()) {
                return null;
            }else{
                for(int i = 0; i < paymentAirCreditList.size(); i++){
                    paymentAirCredit = paymentAirCreditList.get(i);
                    try {
                        transaction = session.beginTransaction();
                        session.delete(paymentAirCredit);
                        transaction.commit();
                        result = "success";
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = "fail";
                    }
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }

    public UtilityFunction getUtilityFunction() {
        return utilityFunction;
    }

    public void setUtilityFunction(UtilityFunction utilityFunction) {
        this.utilityFunction = utilityFunction;
    }

    @Override
    public List<PaymentAirView> getPaymentAirViewReport(String payno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String query="";
        int AndQuery = 0;
        if(payno == null  && "".equals(payno)){
            query = "SELECT * FROM paymentair_view  pa " ; 
        }else{
            query = "SELECT * FROM paymentair_view  pa  where " ;
        }
        
        if(payno != null && (!"".equalsIgnoreCase(payno))){
            if(AndQuery == 1){
                query += " and pa.payno = '" + payno + "'";
           }else{
               AndQuery = 1;
               query += " pa.payno = '" + payno + "'";
           }
        }

        System.out.println("Query : " + query);

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("payno", Hibernate.STRING)
                .addScalar("totalpay", Hibernate.STRING)
                .addScalar("totalcom", Hibernate.STRING)
                .addScalar("cash", Hibernate.STRING)
                .addScalar("chqno", Hibernate.STRING)
                .addScalar("amount", Hibernate.STRING)
                .addScalar("refund", Hibernate.STRING)
                .addScalar("comrefund", Hibernate.STRING)
                .addScalar("withtax", Hibernate.STRING)
                .addScalar("creditnote", Hibernate.STRING)
                .addScalar("debitnote", Hibernate.STRING)
                .list();
        
//                payno
//                totalpay
//                totalcom
//                cash
//                chqno
//                amount
//                refund
//                comrefund
//                withtax
//                creditnote
//                debitnote
        
//                totalpaymentamount
//                receivefromair
//                receivefrominv
//                invno
        
        
        for (Object[] B : QueryStaffList) {
            PaymentAirView payment = new PaymentAirView();
            payment.setPayno(util.ConvertString(B[0]));
            payment.setTotalpay(B[1]== null ? "" :util.ConvertString(B[1]));
            payment.setTotalcom(B[2]== null ? "" :util.ConvertString(B[2]));
            payment.setCash(B[3]== null ? "" :util.ConvertString(B[3]));
            payment.setChqno(B[4]== null ? "" :util.ConvertString(B[4]));
            payment.setAmount(B[5]== null ? "" :util.ConvertString(B[5]));
            payment.setRefund(B[6]== null ? "" :util.ConvertString(B[6]));
            payment.setComrefund(B[7]== null ? "" :util.ConvertString(B[7]));
            
            payment.setWithtax(B[8]== null ? "" :util.ConvertString(B[8]));
            payment.setCreditnote(B[9]== null ? "" :util.ConvertString(B[9]));
            payment.setDebitnote(B[10]== null ? "" :util.ConvertString(B[10]));
            data.add(payment);
        }
        
        session.close();
        this.sessionFactory.close();
        return data;
    }

    @Override
    public PaymentAirline getPaymentAirlineReport(String payno,String printby) {
        PaymentAirline paymentAirline = new PaymentAirline();
        paymentAirline.setPaymentAirlineReportDataSource(new JRBeanCollectionDataSource(getPaymentAirline(payno,printby)));
        paymentAirline.setPaymentAirlineListReportDataSource(new JRBeanCollectionDataSource(getPaymentAirlineList(payno,printby)));
        paymentAirline.setPaymentAirlineRefundReportDataSource(new JRBeanCollectionDataSource(getPaymentAirlineRefund(payno,printby)));
        return paymentAirline; 
           
//        GuideCommissionInfo guideCommissionInfo = new GuideCommissionInfo();
//        guideCommissionInfo.setGuideCommissionSummaryDataSource(new JRBeanCollectionDataSource(getGuideComissionSummaryReport(datefrom, dateto, username, guideid)));
//        guideCommissionInfo.setGuideCommissionDataSource(new JRBeanCollectionDataSource(getGuideComissionReport(datefrom, dateto, username, guideid)));
//        return guideCommissionInfo;
    }
    public List getPaymentAirline(String payno,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        Date thisDate = new Date();
        String query="";
        int AndQuery = 0;
        if(payno == null  && "".equals(payno)){
            query = "SELECT * FROM paymentair_view  pa " ; 
        }else{
            query = "SELECT * FROM paymentair_view  pa  where " ;
        }
        
        if(payno != null && (!"".equalsIgnoreCase(payno))){
            if(AndQuery == 1){
                query += " and pa.payno = '" + payno + "'";
           }else{
               AndQuery = 1;
               query += " pa.payno = '" + payno + "'";
           }
        }

        System.out.println("Query : " + query);

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("payno", Hibernate.STRING)
                .addScalar("totalpay", Hibernate.STRING)
                .addScalar("totalcom", Hibernate.STRING)
                .addScalar("cash", Hibernate.STRING)
                .addScalar("chqno", Hibernate.STRING)
                .addScalar("amount", Hibernate.STRING)
                .addScalar("refund", Hibernate.STRING)
                .addScalar("comrefund", Hibernate.STRING)
                .addScalar("withtax", Hibernate.STRING)
                .addScalar("creditnote", Hibernate.STRING)
                .addScalar("debitnote", Hibernate.STRING)
                .addScalar("totalpaymentamount", Hibernate.STRING)
                .addScalar("receivefromair", Hibernate.STRING)
//                .addScalar("receivefrominv", Hibernate.STRING)
//                .addScalar("invno", Hibernate.STRING)
//                .addScalar("ticketins", Hibernate.STRING)
                .list();
              
        for (Object[] B : QueryStaffList) {
            PaymentAirView payment = new PaymentAirView();
            payment.setPayno(util.ConvertString(B[0]));
            payment.setTotalpay(B[1]== null ? "" :util.ConvertString(B[1]));
            payment.setTotalcom(B[2]== null ? "" :util.ConvertString(B[2]));
            payment.setCash(B[3]== null ? "" :util.ConvertString(B[3]));
            payment.setChqno(B[4]== null ? "" :util.ConvertString(B[4]));
            payment.setAmount(B[5]== null ? "" :util.ConvertString(B[5]));
            payment.setRefund(B[6]== null ? "" :util.ConvertString(B[6]));
            payment.setComrefund(B[7]== null ? "" :util.ConvertString(B[7]));
            payment.setWithtax(B[8]== null ? "" :util.ConvertString(B[8]));
            payment.setCreditnote(B[9]== null ? "" :util.ConvertString(B[9]));
            payment.setDebitnote(B[10]== null ? "" :util.ConvertString(B[10]));
            payment.setTotalpaymentamount(B[11]== null ? "" :util.ConvertString(B[11]));
            payment.setReceivefromair(B[12]== null ? "" :util.ConvertString(B[12]));
//            payment.setReceivefrominv(B[13]== null ? "" :util.ConvertString(B[13]));
//            payment.setInvno(B[14]== null ? "" :util.ConvertString(B[14]));
//            payment.setTicketins(B[15]== null ? "" :util.ConvertString(B[15]));
            data.add(payment);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    public List getPaymentAirlineList(String payno,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        String query="";
        int AndQuery = 0;
        if(payno == null  && "".equals(payno)){
            query = "SELECT * FROM payment_air_fare  pa " ; 
        }else{
            query = "SELECT * FROM payment_air_fare  pa  where " ;
        }
        
        if(payno != null && (!"".equalsIgnoreCase(payno))){
            if(AndQuery == 1){
                query += " and pa.payno = '" + payno + "'";
           }else{
               AndQuery = 1;
               query += " pa.payno = '" + payno + "'";
           }
        }

        System.out.println("Query : " + query);

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("payno", Hibernate.STRING)
                .addScalar("air", Hibernate.STRING)
                .addScalar("document", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("issuedate", Hibernate.STRING)
                .addScalar("tax", Hibernate.STRING)
                .addScalar("actual", Hibernate.STRING)
                .addScalar("ins", Hibernate.STRING)
                .addScalar("netsales", Hibernate.STRING)
                .addScalar("vat", Hibernate.STRING)
                .addScalar("balance_pay", Hibernate.STRING)
                .addScalar("department", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy hh:mm");    
        
        for (Object[] B : QueryStaffList) {
            PaymentAirFare payment = new PaymentAirFare();
            payment.setPayno(payno);
            payment.setUser(printby);
            payment.setSystemdate(String.valueOf(dateformat.format(new Date())));
//            payment.setPayno(util.ConvertString(B[0]));
            payment.setAir(B[1]== null ? "" :util.ConvertString(B[1]));
            payment.setDocument(B[2]== null ? "" :util.ConvertString(B[2]));
            payment.setRefno(B[3]== null ? "" :util.ConvertString(B[3]));
            payment.setIssuedate(B[4]== null ? "" :util.ConvertString(B[4]));
            payment.setTax(B[5]== null ? "" :util.ConvertString(B[5]));
            payment.setActual(B[6]== null ? "" :util.ConvertString(B[6]));
            payment.setInsurance(B[7]== null ? "" :util.ConvertString(B[7]));
            payment.setNetsale(B[8]== null ? "" :util.ConvertString(B[8]));
            payment.setVat(B[9]== null ? "" :util.ConvertString(B[9]));
            payment.setBalancepay(B[10]== null ? "" :util.ConvertString(B[10]));
            payment.setDepartment(B[11]== null ? "" :util.ConvertString(B[11]));
            data.add(payment);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    public List getPaymentAirlineRefund(String payno,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        String query="";
        int AndQuery = 0;
        if(payno == null  && "".equals(payno)){
            query = "SELECT * FROM payment_air_refund  pa " ; 
        }else{
            query = "SELECT * FROM payment_air_refund  pa  where " ;
        }
        
        if(payno != null && (!"".equalsIgnoreCase(payno))){
            if(AndQuery == 1){
                query += " and pa.payno = '" + payno + "'";
           }else{
               AndQuery = 1;
               query += " pa.payno = '" + payno + "'";
           }
        }

        System.out.println("Query : " + query);

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("payno", Hibernate.STRING)
                .addScalar("refundno", Hibernate.STRING)
                .addScalar("ticketno", Hibernate.STRING)
                .addScalar("route", Hibernate.STRING)
                .addScalar("sectorrefund", Hibernate.STRING)
                .addScalar("commission", Hibernate.STRING)
                .addScalar("refundamount", Hibernate.STRING)
                .list();
              
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy hh:mm");    
        
        for (Object[] B : QueryStaffList) {
            PaymentAirRefund payment = new PaymentAirRefund();
            payment.setPayno(payno);
            payment.setUser(printby);
            payment.setSystemdate(String.valueOf(dateformat.format(new Date())));
            payment.setRefundno(B[1]== null ? "" :util.ConvertString(B[1]));
            payment.setTicketno(B[2]== null ? "" :util.ConvertString(B[2]));
            payment.setRoute(B[3]== null ? "" :util.ConvertString(B[3]));
            payment.setSectorrefund(B[4]== null ? "" :util.ConvertString(B[4]));
            payment.setCommission(B[5]== null ? "0" :util.ConvertString(B[5]));
            payment.setRefundamount(B[6]== null ? "0" :util.ConvertString(B[6]));
            data.add(payment);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    
    @Override
    public List<PaymentAirDebit> getPaymentAirDebitByPaymentAirId(String paymentAirId) {
        String query = "from PaymentAirDebit pay where pay.paymentAirticket.id = :paymentAirId";
        Session session = this.sessionFactory.openSession();
        List<PaymentAirDebit> list = session.createQuery(query).setParameter("paymentAirId", paymentAirId).list();

        if (list.isEmpty()){
            return null;
        }
        
        session.close();
        this.sessionFactory.close();
        return list;
    }

    @Override
    public String DeletePaymentAirDebit(String paymentAirId, String paymentDebitId) {
        String result = "";
        PaymentAirDebit paymentAirDebit = new PaymentAirDebit();
        List<PaymentAirDebit> paymentAirDebitList = new ArrayList<PaymentAirDebit>();
        Session session = this.sessionFactory.openSession();
        if(paymentAirId.isEmpty() || "".equals(paymentAirId)){
            String query = "from PaymentAirDebit pay where pay.id = :paymentDebitId";
            paymentAirDebitList = session.createQuery(query).setParameter("paymentDebitId", paymentDebitId).list();
            System.out.println(" Delete paymentAirDebitList size (1) "+paymentAirDebitList.size());
            if (paymentAirDebitList.isEmpty()) {
                return null;
            }else{
                paymentAirDebit =  paymentAirDebitList.get(0);
                try {
                    transaction = session.beginTransaction();
                    session.delete(paymentAirDebit);
                    transaction.commit();
                    result = "success";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    result = "fail";
                }
            }
        } else { 
            String query = "from PaymentAirDebit pay where pay.id = :paymentDebitId and pay.paymentAirticket.id =:paymentAirId ";
            paymentAirDebitList = session.createQuery(query).setParameter("paymentDebitId", paymentDebitId).setParameter("paymentAirId", paymentAirId).list();
            System.out.println(" Delete ReceiptDetailList size "+paymentAirDebitList.size());
            if (paymentAirDebitList.isEmpty()) {
                return null;
            }else{
                for(int i = 0; i < paymentAirDebitList.size(); i++){
                    paymentAirDebit = paymentAirDebitList.get(i);
                    try {
                        transaction = session.beginTransaction();
                        session.delete(paymentAirDebit);
                        transaction.commit();
                        result = "success";
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = "fail";
                    }
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
}
