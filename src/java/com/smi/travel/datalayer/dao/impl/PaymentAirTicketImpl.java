/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PaymentAirTicketDao;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.entity.PaymentAirticket;
import com.smi.travel.datalayer.entity.PaymentAirticketFare;
import com.smi.travel.datalayer.entity.PaymentAirticketRefund;
import com.smi.travel.datalayer.entity.RefundAirticket;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import java.util.ArrayList;
import java.util.List;
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
    
    @Override
    public String InsertPaymentAir(PaymentAirticket payAir) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
//            result = gennaratePaymentRunning();
            payAir.setPayNo(gennaratePaymentRunning());
            session.save(payAir);
            
            List<PaymentAirticketFare> paymentAirticketFares = payAir.getPaymentAirticketFares();
            
            if(paymentAirticketFares != null){
                for(int i = 0; i < paymentAirticketFares.size(); i++){
                   session.save(paymentAirticketFares.get(i));
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
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
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
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            result = "fail";
        }
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
            transaction.rollback();
            result = "fail";
        }
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
    public List<TicketFareView> getListTicketFare(String from, String to, String by, String airAgentId) {
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
                ticketFareView.setSalePrice(listAirline.get(i).getSalePrice());
                ticketFareView.setReferenceNo(listAirline.get(i).getMaster().getReferenceNo());
                listView.add(ticketFareView);
            }
        }
        
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
        return code;
    }
    
    private boolean IsExistPaymentAirticketFare(String paymentAirId) {
        boolean result;
        Session session = this.sessionFactory.openSession();
        List<PaymentAirticketFare> list = session.createQuery("from PaymentAirticketFare p WHERE p.paymentAirticket = :paymentAirId").setParameter("paymentAirId", paymentAirId).list();
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
        List<PaymentAirticketRefund> list = session.createQuery("from PaymentAirticketRefund p WHERE p.paymentAirticket = :paymentAirId").setParameter("paymentAirId", paymentAirId).list();
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
    
//    @Override
//    public RefundAirticket addRefundAirTicket(String refundNo) {
//        RefundAirticket refundAirticket = new RefundAirticket();
//        String query = "from RefundAirticket r where r.refundNo = :refundNo";
//        Session session = this.sessionFactory.openSession();
//        List<RefundAirticket> refundAirticketList = session.createQuery(query).setParameter("refundNo", refundNo).list();
//        session.close();
//        if (refundAirticketList.isEmpty()) {
//            return null;
//        }else{
//            refundAirticket =  refundAirticketList.get(0);
//        }
//        return refundAirticket;        
//    }
    @Override
    public String addRefundAirTicket(String refundNo) {
        String result ="";
        String query = "from RefundAirticket r where r.refundNo = :refundNo";
        Session session = this.sessionFactory.openSession();
        List<RefundAirticket> refundAirticketList = session.createQuery(query).setParameter("refundNo", refundNo).list();
        session.close();
        if (refundAirticketList.isEmpty()) {
            return null;
        }else{
            result =  buildRefundTicketHTML(refundAirticketList);
        }
        return result;        
    }
    
    public String buildRefundTicketHTML(List<RefundAirticket> refundAirticketList) {
        StringBuffer html = new StringBuffer();
        if (refundAirticketList == null || refundAirticketList.size() == 0) {
            return html.toString();
        } 
        String id = "" ;
        String refund = "";
        String ticketNo = "";
        String department = "";
        String route = "";
        String commission = "";
        String amount = "";
        int count = 0;
        for(int i = 0 ; i < refundAirticketList.size() ; i++ ){
            id = refundAirticketList.get(i).getId();
            refund = refundAirticketList.get(i).getRefundNo();
            ticketNo = "12345678";
            department = "1000.00";
            route = "1000.00";
            commission = "1000.00";
            amount = "1000.01";
            route = "TEST";
            count = i;
//            List<RefundAirticketDetail> refundAirticketDetailList = new ArrayList<RefundAirticketDetail>(refundAirticketList.get(i).getRefundAirticketDetails());
//            if(refundAirticketDetailList.size() != 0){
//                commission = String.valueOf(refundAirticketDetailList.get(i).getCommission());
//                route = refundAirticketDetailList.get(i).getSectorRefund();
//                
//                if(refundAirticketDetailList.get(i).getTicketFareAirline() != null){
//                    ticketNo =  refundAirticketDetailList.get(i).getTicketFareAirline().getTicketNo();
//                    department = refundAirticketDetailList.get(i).getTicketFareAirline().getDepartment();
//                    commission = String.valueOf(refundAirticketDetailList.get(i).getTicketFareAirline().getTicketCommission());
//                    amount =  String.valueOf(refundAirticketDetailList.get(i).getTicketFareAirline().getSalePrice());
//                }
//            }

            String newrow
                    = "<tr>"
                    + "<td>" + refund + "</td>"
                    + "<td>" + ticketNo + "</td>"
                    + "<td>" + department + "</td>"
                    + "<td>" + route + "</td>"
                    + "<td>" + commission + "</td>"
                    + "<td>" + amount + "</td>"
                    + "<td><center><a class=\"remCF\"><span onclick=\"deleteRefund('"+id+"','"+refund+"')\" class=\"glyphicon glyphicon-remove deleteicon \"></span></center></td>"
                    + "</tr>";
            System.out.println("newrow [[[[[[[ "+newrow +" ]]]]");
            html.append(newrow);
        }

        return html.toString();
    }    public SessionFactory getSessionFactory() {
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
                TicketFareView ticketFareView = new TicketFareView();
                
                ticketFareView.setId(String.valueOf(PaymentAirticketFareList.get(i).getTicketFareAirline().getId()));
                ticketFareView.setType(String.valueOf(PaymentAirticketFareList.get(i).getTicketFareAirline().getTicketType()));
                ticketFareView.setBuy(String.valueOf(PaymentAirticketFareList.get(i).getTicketFareAirline().getTicketBuy()));
                ticketFareView.setRouting(String.valueOf(PaymentAirticketFareList.get(i).getTicketFareAirline().getTicketRouting()));
                MAirlineAgent mAirlineAgent = new MAirlineAgent();
                if(PaymentAirticketFareList.get(i).getTicketFareAirline().getMAirlineAgent() != null){
                    mAirlineAgent.setId(PaymentAirticketFareList.get(i).getTicketFareAirline().getMAirlineAgent().getId());
                    mAirlineAgent.setCode(PaymentAirticketFareList.get(i).getTicketFareAirline().getMAirlineAgent().getCode());
                    ticketFareView.setAirline(String.valueOf(mAirlineAgent.getCode()));
                }
                ticketFareView.setTicketNo(String.valueOf(PaymentAirticketFareList.get(i).getTicketFareAirline().getTicketNo()));
                ticketFareView.setIssueDate(PaymentAirticketFareList.get(i).getTicketFareAirline().getIssueDate());
                ticketFareView.setDepartment(PaymentAirticketFareList.get(i).getTicketFareAirline().getDepartment());
                ticketFareView.setFare(PaymentAirticketFareList.get(i).getTicketFareAirline().getTicketFare());
                ticketFareView.setTax(PaymentAirticketFareList.get(i).getTicketFareAirline().getTicketTax());
                ticketFareView.setTicketCommission(PaymentAirticketFareList.get(i).getTicketFareAirline().getTicketCommission());
                ticketFareView.setAgentCommission(PaymentAirticketFareList.get(i).getTicketFareAirline().getAgentCommission());
                ticketFareView.setDiffVat(PaymentAirticketFareList.get(i).getTicketFareAirline().getDiffVat());
                ticketFareView.setTicketIns(PaymentAirticketFareList.get(i).getTicketFareAirline().getTicketIns());
                ticketFareView.setSalePrice(PaymentAirticketFareList.get(i).getTicketFareAirline().getSalePrice());
                ticketFareView.setReferenceNo(PaymentAirticketFareList.get(i).getTicketFareAirline().getMaster().getReferenceNo());
                listView.add(ticketFareView);
            }
        }
        session.close();
        this.sessionFactory.close();
        return listView;
    }




}
