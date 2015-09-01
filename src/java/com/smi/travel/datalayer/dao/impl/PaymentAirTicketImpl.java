/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.yy
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.PaymentAirTicketDao;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.entity.PaymentAirticket;
import com.smi.travel.datalayer.entity.PaymentAirticketFare;
import com.smi.travel.datalayer.entity.PaymentAirticketRefund;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.entity.RefundAirticketDetailView;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import java.math.BigInteger;
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
            result = gennaratePaymentRunning();
            payAir.setPayNo(result);
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
            transaction.rollback();
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
                    ticketFareView.setSalePrice(listAirline.get(i).getSalePrice());
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
        return code;
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
    public String addRefundAirTicket(String refundNo,String rowCount) {
        String result ="";
        String query = "from RefundAirticketDetail r where r.refundAirticket.refundNo = :refundNo";
        Session session = this.sessionFactory.openSession();
        List<RefundAirticketDetail> refundAirticketDetails = session.createQuery(query).setParameter("refundNo", refundNo).list();
        
        if (refundAirticketDetails.isEmpty()) {
            return null;
        }else{
            result =  buildRefundTicketHTML(refundAirticketDetails , rowCount);
        }
        session.close();
        this.sessionFactory.close();
        return result;        
    }
    
    public String buildRefundTicketHTML(List<RefundAirticketDetail> refundAirticketDetails ,String rowCount) {
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
                }
            }

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
        htmlList.add(html);
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
    public List<PaymentAirticketFare> searchTicketFare(String ticketfareId, String invoiceSubCode) {
        String query = "from PaymentAirticketFare fare where fare.ticketFareAirline.id = :ticketfareId and fare.paymentAirticket.invoiceSup = :invoiceSup";
        Session session = this.sessionFactory.openSession();
        List<PaymentAirticketFare> paymentAirticketFares = session.createQuery(query).setParameter("ticketfareId", ticketfareId).setParameter("invoiceSup", invoiceSubCode).list();
        
        if (paymentAirticketFares.isEmpty()) {
            return null;
        }
        
        session.close();
        this.sessionFactory.close();
        return paymentAirticketFares; 
    }

}
