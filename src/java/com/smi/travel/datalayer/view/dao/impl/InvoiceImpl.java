/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.report.model.InvoiceMonthly;
import com.smi.travel.datalayer.report.model.InvoiceReport;
import com.smi.travel.datalayer.view.dao.InvoiceReportDao;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public List getInvoice(String InvoiceId,String BankId,String showStaff,String showLeader,String sign,String printBy,String isTemp) {
        Session session = this.sessionFactory.openSession();
        System.out.println("Sign : " + sign + "Print By : " + printBy);
        UtilityFunction util = new UtilityFunction();  
        List data = new ArrayList();
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String accName = "S.M.I. TRAVEL CO., LTD.";
        String accType = "CURRENT ACCOUNT";
        String Branch1 = "";
        String Accno1 = "";
        String Bank1 = "";
        String Branch2 = "";
        String Accno2 = "";
        String Bank2 = "";
        
        String[] bankCode = BankId.split(",");
        String queryBank = "SELECT * FROM `m_bank` ";
        for(int i=0;i<bankCode.length;i++){
            queryBank += (i > 0 ? " or " : " where ");
            queryBank += " code = '" + bankCode[i] + "' ";
        }        
        System.out.println("Bank : " + queryBank);
        List<Object[]> QueryBankList = session.createSQLQuery(queryBank)
                 .addScalar("code", Hibernate.STRING)
                 .addScalar("name", Hibernate.STRING)
                 .addScalar("branch", Hibernate.STRING)
                 .addScalar("acc_no", Hibernate.STRING)
                 .list();
        int i = 0;
        for (Object[] B : QueryBankList) {
            if(i == 0){
                Bank1 = (util.ConvertString(B[1])).toUpperCase();
                Branch1 = (util.ConvertString(B[2])).toUpperCase();
                Accno1 = (util.ConvertString(B[3])).toUpperCase();
                i++;
            }else{
                Bank2 = (util.ConvertString(B[1])).toUpperCase();
                Branch2 = (util.ConvertString(B[2])).toUpperCase();
                Accno2 = (util.ConvertString(B[3])).toUpperCase(); 
            }
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
                .addScalar("refno", Hibernate.STRING)
                .addScalar("vatpercent", Hibernate.INTEGER)
                .addScalar("billable_desc_id", Hibernate.STRING)
                .addScalar("create_by", Hibernate.STRING)
                .list();
        int count = 0;
        int vat = 0;
        int itemNo = 1;
        for (Object[] B : QueryInvoiceList) {
            boolean isVat = false;
            count++;
            InvoiceReport invoice = new InvoiceReport();
            invoice.setItemno(String.valueOf(itemNo));
            invoice.setAccname(accName);
            invoice.setAccno1(Accno1);
            invoice.setAccno2(Accno2);
            invoice.setAcctype(accType);
            invoice.setPrintby(printBy);
            invoice.setIsTemp(isTemp);
            invoice.setCurrency(util.ConvertString(B[17]));
            invoice.setRefno(util.ConvertString(B[20]) != null && !"".equals(util.ConvertString(B[20])) ? util.ConvertString(B[20]) : "");
            vat = ((Integer) (B[21] != null && (Integer) B[21] != 0 ? (Integer)B[21] : vat));
            invoice.setVatpercent(vat);
            invoice.setAmount(B[11] != null ? df.format(B[11]) : "0.00");
            invoice.setBank1(Bank1);
            invoice.setBank2(Bank2);
            invoice.setBranch1(Branch1);
            invoice.setBranch2(Branch2);
            invoice.setInvto(util.ConvertString(B[0]));
            invoice.setInvno(util.ConvertString(B[13]));
            invoice.setBankid(BankId);          
            invoice.setTaxid(util.ConvertString(B[14]));
            String taxBranch = util.getTaxBranch(util.ConvertString(B[0]),util.ConvertString(B[15]));
            invoice.setTaxbranch(taxBranch);
            if(B[1] != null){
                invoice.setInvdate(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format((Date)B[1]));
            }
            if(B[16] != null){
                invoice.setDuedate(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format(util.convertStringToDate(util.ConvertString(B[16]))));
            }else{
                invoice.setDuedate("");
            }
            
            
            invoice.setStaff(util.ConvertString(B[2]));
            invoice.setPayment(util.ConvertString(B[3]));
            
            if(count == QueryInvoiceList.size()){
                String[] desc = util.getTagPDescription(util.ConvertString(B[4]));
                invoice.setDescription(desc[0]);
                invoice.setPrice(desc[1]);
                invoice.setIsHide(desc[0].indexOf("\n") == -1 ? "0" : "1");
                System.out.println("===== Index of ===== : "+desc[0].indexOf("\n"));
//                invoice.setDescription(util.ConvertString(B[4]));
                String Description = invoice.getDescription();
                String remark = util.ConvertString(B[19]) == null ? "" : util.ConvertString(B[19]);
                if(!"".equalsIgnoreCase(remark)){
                    invoice.setDescription(Description + "\n"+remark);
                }
                
                
                System.out.println("Remark : " + remark);
            }else{
                String[] desc = util.getTagPDescription(util.ConvertString(B[4]));
                invoice.setDescription(desc[0]);
                invoice.setPrice(desc[1]);
                invoice.setIsHide(desc[0].indexOf("\n") == -1 ? "0" : "1");
                System.out.println("===== Index of ===== : "+desc[0].indexOf("\n"));
//                invoice.setDescription(util.ConvertString(B[4]));
            }
            
            if(B[5] != null){
                String grossTemp = util.ConvertString(B[5]);
                invoice.setGross("0.00".equalsIgnoreCase(grossTemp) ? "0.00" : df.format(B[5]));
            }
            if(B[6] != null){
                isVat = true;
                String totalTemp = util.ConvertString(B[6]);
                invoice.setVat("0.00".equalsIgnoreCase(totalTemp) ? "0.00" :  df.format(B[6]));
            }
            if(B[7] != null){
                String totalTemp = util.ConvertString(B[7]);
                invoice.setTotal("0.00".equalsIgnoreCase(totalTemp) ? "0.00" : df.format(B[7]));
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
                currency = " YEN" ;
            }else if("THB".equals(currency)){
                currency = " BAHT" ;
            }else if("USD".equals(currency)){
                currency = " DOLLAR" ;
            }else{
                currency = " BAHT" ;
            }        
            String totalWord = textmoney +currency+ textmoneypoint;
            if("".equalsIgnoreCase(textmoneypoint.trim())){
                totalWord = textmoney +currency+" ONLY";
            }
            System.out.println(" totalWord " + totalWord);
            invoice.setTextmoney(totalWord.substring(0,1).toUpperCase() + totalWord.substring(1));
            
            invoice.setShowleader(showLeader);
            invoice.setShowstaff(showStaff);
            if(sign != null){
                if("".equals(sign)){
                    invoice.setSign("nosign");
                    invoice.setSignname(util.ConvertString(B[23]));
                }else{
                    invoice.setSign(sign);
                    String querySystemUser = "from SystemUser s where s.name like '%"+sign+"%'";
                    List<SystemUser> systemUser = session.createQuery(querySystemUser).list();
                    if(!systemUser.isEmpty()) {
                        invoice.setSignname(systemUser.get(0).getName());
                    }        
                    
                }
            }
            invoice.setGrossadd("");
            invoice.setVatadd("");
            invoice.setAmountadd("");
            
            data.add(invoice);
            itemNo += 1;
            
            String billableDescId = (B[22] != null ? util.ConvertString(B[22]) : "");
            if(!"".equalsIgnoreCase(billableDescId)){
                List<InvoiceReport> invoiceReportAdditionalList = getInvoiceReportAdditional(billableDescId,vat,isVat,session);
                
                if(!invoiceReportAdditionalList.isEmpty()){
                    System.out.println("===== invoiceReportAdditionalList =====");
                    for(InvoiceReport invoiceReportAdditional : invoiceReportAdditionalList){
                        InvoiceReport invoiceReportTemp = new InvoiceReport();
                        invoiceReportTemp.setDescription(invoiceReportAdditional.getDescription());                      
                        invoiceReportTemp.setGrossadd(df.format(new BigDecimal(invoiceReportAdditional.getGrossadd())));
                        invoiceReportTemp.setVatadd(df.format(new BigDecimal(invoiceReportAdditional.getVatadd())));
                        invoiceReportTemp.setAmountadd(df.format(new BigDecimal(invoiceReportAdditional.getAmountadd())));
                        
                        invoiceReportTemp.setPrice("");
                        invoiceReportTemp.setItemno("");
                        invoiceReportTemp.setAccname(accName);
                        invoiceReportTemp.setAccno1(Accno1);
                        invoiceReportTemp.setAccno2(Accno2);
                        invoiceReportTemp.setAcctype(accType);
                        invoiceReportTemp.setPrintby(printBy);
                        invoiceReportTemp.setIsTemp(isTemp);
                        invoiceReportTemp.setCurrency(util.ConvertString(B[17]));
                        invoiceReportTemp.setVatpercent(vat);
                        invoiceReportTemp.setBank1(Bank1);
                        invoiceReportTemp.setBank2(Bank2);
                        invoiceReportTemp.setBranch1(Branch1);
                        invoiceReportTemp.setBranch2(Branch2);
                        invoiceReportTemp.setBankid(BankId);
                        invoiceReportTemp.setGrtotal(df.format(B[9]));
                        if(B[7] != null){
                            String totalTemp = util.ConvertString(B[7]);
                            invoiceReportTemp.setTotal("0.00".equalsIgnoreCase(totalTemp) ? "0.00" : df.format(B[7]));
                        } 
                        if(B[8] != null){
                            invoiceReportTemp.setTotalvat(df.format(B[8])); 
                        }
                        invoiceReportTemp.setTextmoney(totalWord.substring(0,1).toUpperCase() + totalWord.substring(1));
                        if(sign != null){
                            if("".equals(sign)){
                                invoiceReportTemp.setSign("nosign");
                                invoiceReportTemp.setSignname(util.ConvertString(B[23]));
                            }else{
                                invoiceReportTemp.setSign(sign);
                                String querySystemUser = "from SystemUser s where s.name like '%"+sign+"%'";
                                List<SystemUser> systemUser = session.createQuery(querySystemUser).list();
                                if(!systemUser.isEmpty()) {
                                    invoiceReportTemp.setSignname(systemUser.get(0).getName());
                                }        

                            }
                        }
                        data.add(invoiceReportTemp);
                        
                        InvoiceReport invoiceReportPrevious = (InvoiceReport) data.get(count-1);
                        BigDecimal gross1 = new BigDecimal(invoiceReportPrevious.getGross().replaceAll(",", ""));
                        BigDecimal vat1 = (invoiceReportPrevious.getVat() != null ? new BigDecimal(invoiceReportPrevious.getVat().replaceAll(",", "")) : new BigDecimal("0.00"));
                        BigDecimal amount1 = new BigDecimal(invoiceReportPrevious.getAmount().replaceAll(",", ""));
                        BigDecimal gross2 = new BigDecimal(invoiceReportTemp.getGrossadd().replaceAll(",", ""));
                        BigDecimal vat2 = new BigDecimal(invoiceReportTemp.getVatadd().replaceAll(",", ""));
                        BigDecimal amount2 = new BigDecimal(invoiceReportTemp.getAmountadd().replaceAll(",", ""));
                        invoiceReportPrevious.setGross(df.format(gross1.subtract(gross2)));
                        invoiceReportPrevious.setVat(df.format(vat1.subtract(vat2)));
                        invoiceReportPrevious.setAmount(df.format(amount1.subtract(amount2)));
                        
                    }
                    
                }
            }
            
            
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
        if(checkQuery == 1){
            query += " and (recstatus  like '%7%' or recstatus is null) ";
        }
        
        
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
            invM.setHeaddepartment((!"".equalsIgnoreCase(department)) && (department != null) ? department : "ALL");
            if(!"".equals(util.ConvertString(B[2]))){
                String dayy[] = util.ConvertString(B[2]).split("-");
                System.out.println("Date : " + util.ConvertString(B[2]));
                String date = ""+dayy[2]+"/"+dayy[1]+"/"+dayy[0];
                try {
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date dateBefore = df.parse(date);
                    invM.setInvdate(new SimpleDateFormat("dd/MM/yyyy", new Locale("us", "us")).format(dateBefore));
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
            invM.setType((!"".equalsIgnoreCase(vattype)) && (vattype != null) ? vattype : "ALL");
            invM.setBillingattn(billingAttn);
            invM.setBillingdate((!"".equalsIgnoreCase(billingDate) && (billingDate != null)) ? util.SetFormatDate(util.convertStringToDate(billingDate), "dd MMM yyyy") : "");
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

    private List<InvoiceReport> getInvoiceReportAdditional(String billableDescId, int mVat, boolean isVat, Session session) {
        UtilityFunction util = new UtilityFunction();
        List<InvoiceReport> invoiceReportList = new ArrayList<InvoiceReport>();
        String query = "from BillableDesc bd where bd.id = :billableDescId ";
         
        List<BillableDesc> billableDescList = session.createQuery(query)
                .setParameter("billableDescId", billableDescId)
                .list();
        
        if(billableDescList.isEmpty()){
            return invoiceReportList;
        }
        
        String mBillType = billableDescList.get(0).getMBilltype().getName();
        String refNo = billableDescList.get(0).getBillable().getMaster().getReferenceNo();
        if("HOTEL".equalsIgnoreCase(mBillType.toUpperCase())){
            invoiceReportList = getHotelAdditional(refNo,mVat,isVat,session);
        }
         
        return invoiceReportList;
    }

    private List<InvoiceReport> getHotelAdditional(String refNo, int mVat, boolean isVat, Session session) {
        UtilityFunction util = new UtilityFunction();
        List<Object[]> hotelAdditionalList = session.createSQLQuery(" SELECT * FROM `invoice_view_hotel_additional` WHERE ref_no = '" + refNo + "' ")      
                .addScalar("ref_no", Hibernate.STRING)
                .addScalar("cate_desc", Hibernate.STRING)
                .addScalar("cost", Hibernate.STRING)
                .addScalar("price", Hibernate.STRING)
                .list();
        
        List<InvoiceReport> invoiceReportList = new ArrayList<InvoiceReport>();
            
        for(Object[] B : hotelAdditionalList){
            InvoiceReport invoiceReport = new InvoiceReport();
            BigDecimal amount = (B[3] != null ? new BigDecimal(util.ConvertString(B[3])) : new BigDecimal("0.00"));
            BigDecimal vatTemp = new BigDecimal(String.valueOf(mVat));
            BigDecimal gross = new BigDecimal("0.00");
            BigDecimal vat = new BigDecimal("0.00");

            if(isVat){
                gross = amount.multiply(new BigDecimal("100.00")).divide(new BigDecimal("100.00").add(vatTemp),2,RoundingMode.HALF_UP);
                vat = amount.subtract(gross);          

            } else {
                gross = amount;
            }

            invoiceReport.setDescription(B[1] != null ? "            : " + util.ConvertString(B[1]) : "");                   
            invoiceReport.setGrossadd(String.valueOf(gross)); 
            invoiceReport.setVatadd(String.valueOf(vat));
            invoiceReport.setAmountadd(String.valueOf(amount));

            invoiceReportList.add(invoiceReport);

        }
        
        return invoiceReportList;
    }
  
}
