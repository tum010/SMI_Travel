/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;
import com.smi.travel.datalayer.report.model.InvoiceReport;
import com.smi.travel.datalayer.view.dao.InvoiceReportDao;
import com.smi.travel.util.UtilityFunction;
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
public class InvoiceImpl implements InvoiceReportDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;

    @Override
    public List getInvoice(String InvoiceId,String BankId) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        List data = new ArrayList();
        DecimalFormat df = new DecimalFormat("###,###.00");
        String accName = "S.M.I. TRAVEL CO., LTD.";
        String accType = "CURRENT ACCOUNT";
        String Branch = "";
        String Accno = "";
        String Bank = "";
         List<Object[]> QueryBankList = session.createSQLQuery("SELECT * FROM `m_bank` where id= " + BankId)
                 .addScalar("code", Hibernate.STRING)
                 .addScalar("name", Hibernate.STRING)
                 .addScalar("branch", Hibernate.STRING)
                 .addScalar("acc_no", Hibernate.STRING)
                 .list();
        for (Object[] B : QueryBankList) {
            Bank = util.ConvertString(B[1]);
            Branch = util.ConvertString(B[2]);
            Accno = util.ConvertString(B[3]);   
            
        }
        List<Object[]> QueryInvoiceList = session.createSQLQuery(" SELECT * FROM `invoice_view` where id =  " + InvoiceId)      
                .addScalar("invto", Hibernate.STRING)
                .addScalar("invdate", Hibernate.DATE)
                .addScalar("staff", Hibernate.STRING)
                .addScalar("payment", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .addScalar("gross", Hibernate.BIG_DECIMAL)
                .addScalar("vat", Hibernate.BIG_DECIMAL)
                .addScalar("total", Hibernate.BIG_DECIMAL)
                .addScalar("totalvat", Hibernate.BIG_DECIMAL)
                .addScalar("grtotal", Hibernate.BIG_DECIMAL)
                .addScalar("user_create", Hibernate.STRING)
                .addScalar("amount", Hibernate.BIG_DECIMAL)
                .list();
        
        for (Object[] B : QueryInvoiceList) {
            InvoiceReport invoice = new InvoiceReport();
            invoice.setAccname(accName);
            invoice.setAccno(Accno);
            invoice.setAcctype(accType);
            invoice.setRefno("");
            invoice.setAmount(df.format(B[11]));
            invoice.setBank(Bank);
            invoice.setBranch(Branch);
            invoice.setInvto(util.ConvertString(B[0]));
            invoice.setInvno(util.ConvertString(B[1]));
            invoice.setBankid(BankId);
            if(B[1] != null){
                invoice.setInvdate(new SimpleDateFormat("dd-mm-yyyy", new Locale("us", "us")).format((Date)B[1]));
            }
            
            invoice.setStaff(util.ConvertString(B[2]));
            invoice.setPayment(util.ConvertString(B[3]));
            invoice.setDescription(util.ConvertString(B[4]));
            if(B[5] != null){
                invoice.setGross(df.format(B[5]));
            }
            if(B[6] != null){
                invoice.setVat(df.format(B[6]));
            }
            invoice.setTotal(df.format(B[7]));
            if(B[8] != null){
                invoice.setTotalvat(df.format(B[8])); 
            }
            
            invoice.setGrtotal(df.format(B[9]));
            invoice.setUser(util.ConvertString(B[10]));
            invoice.setTextmoney("");
            data.add(invoice);
        }
        

        session.close();
        this.sessionFactory.close();
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
