/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.RefundAirReportDao;

import com.smi.travel.datalayer.report.model.RefundAirReport;
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
             data.add(report);
            
        }
        for(int i=0;i<data.size();i++){
            RefundAirReport temp = (RefundAirReport) data.get(i);
            temp.setTicketamount(util.setFormatMoney(SumTicketAmount));
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
    
}
