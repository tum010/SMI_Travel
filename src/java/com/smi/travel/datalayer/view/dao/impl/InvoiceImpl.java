/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.report.model.InvoiceMonthly;
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
    private static final String GET_INVOICE_FROMID = "FROM InvoiceDetail invD where invD.invoice.id = :invId";

    @Override
    public List getInvoice(String InvoiceId,String BankId,String showStaff,String showLeader) {
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
                .addScalar("id", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("tax_no", Hibernate.STRING)
                .addScalar("branch", Hibernate.STRING)
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
            invoice.setInvno(util.ConvertString(B[13]));
            invoice.setBankid(BankId);
            invoice.setTaxid(util.ConvertString(B[14]));
            invoice.setTaxbranch(util.ConvertString(B[15]));
            if(B[1] != null){
                invoice.setInvdate(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format((Date)B[1]));
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
            
            invoice.setCo(getLeaderNameFromInvoiceID(util.ConvertString(B[12])));
            invoice.setGrtotal(df.format(B[9]));
            invoice.setUser(util.ConvertString(B[10]));
            invoice.setTextmoney("");
            invoice.setShowleader(showLeader);
            invoice.setShowstaff(showStaff);
            data.add(invoice);
        }
        session.close();
        this.sessionFactory.close();
        return data;
    }
    
    private String getLeaderNameFromInvoiceID(String InvId){
        String result ="";
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        System.out.println("InvId : "+InvId);
        List<InvoiceDetail> invoiceList = session.createQuery(GET_INVOICE_FROMID)
                .setParameter("invId", InvId)
                .list();
        if (invoiceList.isEmpty()) {
            return "";
        }else{
            for(int i=0;i<invoiceList.size();i++){
                String Cusname = util.getCustomerName(invoiceList.get(i).getBillableDesc().getBillable().getMaster().getCustomer());
                System.out.println("Cusname : "+Cusname);
                
                    if(result.length() != 0){
                        String[] data = result.split("/");
                        int isLap = 0;
                        for(int j=0;j<data.length;j++){
                            if(data[j].equalsIgnoreCase(Cusname))isLap =1;
                        }
                        if(isLap == 0)
                        result += "/"+Cusname;
                        
                    }else{
                        result += Cusname;
                    }
                    
                
            }
        }
        session.close();
        this.sessionFactory.close();
        return result;
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
    public List getInvoiceMonthly(String BillFrom, String BillTo,String ClientName, String Payment, String Accno, String vattype, String from, String to, String department) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        Date thisdate = new Date();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `invoice_monthly_view` invm Where";
        int checkQuery = 0;
        String prefix ="";
        if(((from != null) &&(!"".equalsIgnoreCase(from))) &&((to != null) &&(!"".equalsIgnoreCase(to)))){
             query += " invm.invdate >= '" +from +"' and invm.invdate <= '"+to +"' ";
             checkQuery = 1;
        }else if((from != null) &&(!"".equalsIgnoreCase(from))){
             checkQuery = 1;
             query +=  " invm.invdate >= '" +from +"'";   
        }else if((to != null) &&(!"".equalsIgnoreCase(to))){
             checkQuery = 1;
             query += " invm.invdate <= '" +to +"'";
        }
         
         
         if((BillTo != null) &&(!"".equalsIgnoreCase(BillTo))){
             if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
             query += prefix+" invm.invto = '"+BillTo+"'";
         }
         if((vattype != null) &&(!"".equalsIgnoreCase(vattype))){
             if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
             query += prefix+" invm.type = '"+vattype+"'";
         }
         if((department != null) &&(!"".equalsIgnoreCase(department))){
             if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
             query += prefix+" invm.department = '"+department+"'";
         }
         
        
        if(checkQuery == 0){query = query.replaceAll("Where", "");}
         System.out.println("query : "+query);
         
        List<Object[]> QueryInvoiceMounthList = session.createSQLQuery(query)      
                .addScalar("invname", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("invdate", Hibernate.DATE)
                .addScalar("detail", Hibernate.STRING)
                .addScalar("thb", Hibernate.BIG_DECIMAL)
                .addScalar("jpy", Hibernate.BIG_DECIMAL)
                .addScalar("usd", Hibernate.BIG_DECIMAL)
                .addScalar("department", Hibernate.STRING)
                .addScalar("recno", Hibernate.STRING)
                .addScalar("recamt", Hibernate.BIG_DECIMAL)
                .addScalar("type", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryInvoiceMounthList) {
            InvoiceMonthly invM = new InvoiceMonthly();
            
            invM.setSystemdate(util.SetFormatDate(thisdate, "dd MMM yyyy hh:mm:ss"));
            invM.setAccno(Accno);
            invM.setBillfrom(BillFrom);
            invM.setBillto(ClientName);
            invM.setDepartment(util.ConvertString(B[7]));
            invM.setDetail(util.ConvertString(B[3]));
            invM.setHeaddepartment(department);
            invM.setInvdate(util.SetFormatDate(thisdate, "dd MMM yyyy"));
            invM.setInvname(util.ConvertString(B[0]));
            invM.setInvno(util.ConvertString(B[1]));
            invM.setJpy(util.setFormatMoney(B[5]));
            invM.setThb(util.setFormatMoney(B[4]));
            invM.setUsd(util.setFormatMoney(B[6]));
            invM.setPayment(Payment);
            invM.setRecamt(util.setFormatMoney(B[9]));
            invM.setRecno(util.setFormatMoney(B[8]));
            invM.setType(vattype);
            data.add(invM);
        }
        
        session.close();
        this.sessionFactory.close();
        return data;
    }
  
}
