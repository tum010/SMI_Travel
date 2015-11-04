/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.report.model.InvoiceMonthly;
import com.smi.travel.datalayer.report.model.InvoiceReport;
import com.smi.travel.datalayer.view.dao.InvoiceReportDao;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
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
    public List getInvoice(String InvoiceId,String BankId,String showStaff,String showLeader,String sign) {
        Session session = this.sessionFactory.openSession();
        System.out.println("Sign : " + sign);
        UtilityFunction util = new UtilityFunction();  
        List data = new ArrayList();
        DecimalFormat df = new DecimalFormat("###,###.00");
        String accName = "S.M.I. TRAVEL CO., LTD.";
        String accType = "CURRENT ACCOUNT";
        String Branch = "";
        String Accno = "";
        String Bank = "";
         List<Object[]> QueryBankList = session.createSQLQuery("SELECT * FROM `m_bank` where code= '" + BankId+"'")
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
                .addScalar("duedate", Hibernate.STRING)
                .addScalar("currency", Hibernate.STRING)
                .addScalar("address", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        int count = 0;
        for (Object[] B : QueryInvoiceList) {
            count++;
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
            invoice.setDuedate(util.ConvertString(B[16]));
            if(B[1] != null){
                invoice.setInvdate(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format((Date)B[1]));
            }
            
            invoice.setStaff(util.ConvertString(B[2]));
            invoice.setPayment(util.ConvertString(B[3]));
            
            if(count == QueryInvoiceList.size()){
                invoice.setDescription(util.ConvertString(B[4]));
                String remark = util.ConvertString(B[19]);
                invoice.setRemark(util.ConvertString(B[19]) == null ? "" : util.ConvertString(B[19]));
            }else{
                invoice.setDescription(util.ConvertString(B[4]));
            }
            
            if(B[5] != null){
                invoice.setGross(df.format(B[5]));
            }
            if(B[6] != null){
                invoice.setVat(df.format(B[6]));
            }
            if(B[7] != null){
                invoice.setTotal(df.format(B[7]));
            }         
            if(B[8] != null){
                invoice.setTotalvat(df.format(B[8])); 
            }
            
            invoice.setCo(getLeaderNameFromInvoiceID(util.ConvertString(B[12])));
            invoice.setGrtotal(df.format(B[9]));
            invoice.setUser(util.ConvertString(B[10]));
            invoice.setAddress(util.ConvertString(B[18]));
            
            // Set Text Amount 
//            System.out.println("B9 : " + B[9]);
//            String text = util.ConvertString(B[9]);
//            System.out.println("Text B[9] : " +text);
//            text = convertGrantotal(text,util.ConvertString(B[17]));
//            System.out.println("Text Amount Last : " + text);
//            invoice.setTextmoney(text);
            
            String string = String.valueOf(B[9]);
            String[] parts = string.split("\\.");
            String part1 = parts[0]; // number
            String part2 = parts[1]; // point
            String textmoney = (utilityFunction.convert(Integer.parseInt(part1)));
            String textmoneypoint = (utilityFunction.changPoint(String.valueOf(part2)));
            String currency = util.ConvertString(B[17]);
            if("JPY".equals(currency)){
                currency = " yen" ;
            }else if("THB".equals(currency)){
                currency = " baht" ;
            }else if("USD".equals(currency)){
                currency = " dollar" ;
            }else{
                currency = " baht" ;
            }        
            String totalWord = textmoney +currency+ textmoneypoint;
            if("".equalsIgnoreCase(textmoneypoint.trim())){
                totalWord = textmoney +currency+" only";
            }
            System.out.println(" totalWord " + totalWord);
            invoice.setTextmoney(totalWord.substring(0,1).toUpperCase() + totalWord.substring(1));
            
            
            
            invoice.setShowleader(showLeader);
            invoice.setShowstaff(showStaff);
            if(sign != null){
                if("".equals(sign)){
                    invoice.setSign("nosign");
                }else{
                    invoice.setSign(sign);
                }
            }
            
            data.add(invoice);
        }
        session.close();
        this.sessionFactory.close();
        return data;
    }
    
    private String convertGrantotal(String grand,String currency){
        String text = "";
        Long numLong = null;
        int dot = grand.indexOf('.');
        int intGross = grand.length();
	System.out.println("Dot : " + dot + "  Size : " + intGross);
        
        if((dot + 1) == intGross){
            numLong = numLong.valueOf(grand);
            text += utilityFunction.convert(numLong);
        }else{
            String number = grand.substring(0, dot);
            String point = grand.substring((dot+1));
            numLong = numLong.valueOf(number);
            text += utilityFunction.convert(numLong);
            
            if (point != null && !"".equals(point)) {
                text += changPoint(point);
                System.out.println("Text Amount : " + text);
            }
        }
        String firstText = text.substring(0,1);
        String lastText  = text.substring(1);
        text = firstText.toUpperCase() +"" + lastText;
        System.out.println("Money New : " + text);
        
        if("THB".equals(currency)){
            currency = "baht only" ;
        }
        
        text += " " + currency;
        System.out.println("Text Amount Total : " + text);
        return text;
    }
    
    private String changPoint(String point){
        String text = " point ";
        String one = point.substring(0,1);
        String two = point.substring(1,2);
        System.out.println("Point SubString : " + one + " : " + two );

        String array[] = { one, two };

        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i] != "") {
                System.out.println("Array : "+array[i]+":");
                if ("0".equals(array[i])) {
                        text += "zero ";
                } else if ("1".equals(array[i])) {
                        text += "one ";
                } else if ("2".equals(array[i])) {
                        text += "two ";
                } else if ("3".equals(array[i])) {
                        text += "three ";
                } else if ("4".equals(array[i])) {
                        text += "four ";
                } else if ("5".equals(array[i])) {
                        text += "five ";
                } else if ("6".equals(array[i])) {
                        text += "six ";
                } else if ("7".equals(array[i])) {
                        text += "seven ";
                } else if ("8".equals(array[i])) {
                        text += "eight ";
                } else if ("9".equals(array[i])) {
                        text += "nine ";
                }
            }
        }
	System.out.println("Amount :  " + text);
	return text;
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
            System.out.println("Size Leader Name : " + invoiceList.size());
            if(invoiceList != null && invoiceList.size() != 0){
            for(int i=0;i<invoiceList.size();i++){
                String Cusname = "";
                if(invoiceList.get(i).getBillableDesc() != null && !"".equals(invoiceList.get(i).getBillableDesc())){
                    Cusname = util.getCustomerName(invoiceList.get(i).getBillableDesc().getBillable().getMaster().getCustomer());
                }
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
    public List getInvoiceMonthly(String BillTo,String ClientName, String Accno, String vattype, String from, String to, String department, String billingAttn, String billingFrom, String billingTel, String billingFax, String billingMail, String billingDate) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        Date thisdate = new Date();
        List data = new ArrayList();
        String querydata = "";
        String query = "";
        int checkQuery = 0;
        String prefix ="";
        if( !"".equals(BillTo)  || !"".equals(vattype) || !"".equals(from) || !"".equals(to) || !"".equals(department)){
            query = "SELECT * FROM `invoice_monthly_view` invm  Where";
        }else{
            query = "SELECT * FROM `invoice_monthly_view` invm";
        }
        
        if ((from != null )&&(!"".equalsIgnoreCase(from))) {
            if ((to != null )&&(!"".equalsIgnoreCase(to))) {
                if(checkQuery == 1){
                     query += " and invm.invdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }else{
                    checkQuery = 1;
                     query += " invm.invdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }
            }
        }
//        if(((from != null) &&(!"".equalsIgnoreCase(from))) &&((to != null) &&(!"".equalsIgnoreCase(to)))){
//             query += " invm.invdate >= '" +from +"' and invm.invdate <= '"+to +"' ";
//             checkQuery = 1;
//        }else if((from != null) &&(!"".equalsIgnoreCase(from))){
//             checkQuery = 1;
//             query +=  " invm.invdate >= '" +from +"'";   
//        }else if((to != null) &&(!"".equalsIgnoreCase(to))){
//             checkQuery = 1;
//             query += " invm.invdate <= '" +to +"'";
//        }
//         
         
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
         
        if(checkQuery == 0){
            query += " where invm.invstatus != 2 ";
        }else{
            query += " and  invm.invstatus  != 2 ";
        }
//        if(checkQuery == 0){query = query.replaceAll("Where", "");}
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
        if(QueryInvoiceMounthList != null && QueryInvoiceMounthList.size() != 0){
        String[] billing = Accno.split(",");
        for (Object[] B : QueryInvoiceMounthList) {
            InvoiceMonthly invM = new InvoiceMonthly();
            
            invM.setSystemdate(util.SetFormatDate(thisdate, "dd MMM yyyy hh:mm:ss"));
            invM.setBillto(ClientName);
            invM.setDepartment(util.ConvertString(B[7]));
            invM.setDetail(util.ConvertString(B[3]));
            invM.setHeaddepartment(department);
            if(!"".equals(util.ConvertString(B[2]))){
                String dayy[] = util.ConvertString(B[2]).split("-");
                System.out.println("Date : " + util.ConvertString(B[2]));
                String date = ""+dayy[2]+"-"+dayy[1]+"-"+dayy[0];
                try {
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Date dateBefore = df.parse(date);
                    invM.setInvdate(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format(dateBefore));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
//            invM.setInvdate(util.SetFormatDate(util.ConvertString(B[2]), "dd MMM yyyy"));
            invM.setInvname(util.ConvertString(B[0]));
            invM.setInvno(util.ConvertString(B[1]));
            invM.setJpy((BigDecimal) (B[5]));
            invM.setThb((BigDecimal)(B[4]));
            invM.setUsd((BigDecimal)(B[6]));
            invM.setRecamt((BigDecimal)(B[9]));
//            System.out.println("Recamt : " + util.setFormatMoney(B[8]));
            invM.setRecno(util.ConvertString(B[8]));
            invM.setType(vattype);
            invM.setBillingattn(billingAttn);
            invM.setBillingdate(billingDate);
            invM.setBillingfax(billingFax);
            invM.setBillingfrom(billingFrom);
            invM.setBillingmail(billingMail);
            invM.setBillingtel(billingTel);
            if(!"".equalsIgnoreCase(Accno)){
                invM.setRemittanceto1("REMITTANCE TO : "+billing[0].toUpperCase());
                invM.setRemittanceto2(billing[1].toUpperCase()+" CURRENT ACCOUNT NO. "+billing[2]);
            }          
            data.add(invM);
        }
        }
        session.close();
        this.sessionFactory.close();
        return data;
    }
  
}
