/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.report.model.TaxInvoiceReport;
import com.smi.travel.datalayer.view.entity.TaxInvoiceView;
import com.smi.travel.datalayer.view.dao.TaxInvoiceReportDao;
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
public class TaxInvoiceImpl implements TaxInvoiceReportDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public List getTaxInvoice(String taxInvId,int option,String sign,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        DecimalFormat df = new DecimalFormat("###,##0.00");
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `taxinvoice_view` where `taxinvoice_view`.id =  " + taxInvId)
                .addScalar("id",Hibernate.STRING)
                .addScalar("customer",Hibernate.STRING)
                .addScalar("address",Hibernate.STRING)
                .addScalar("tel",Hibernate.STRING)
                .addScalar("fax",Hibernate.STRING)
                .addScalar("taxno",Hibernate.STRING)
                .addScalar("taxdate",Hibernate.STRING)
                .addScalar("paid",Hibernate.STRING)
                .addScalar("description",Hibernate.STRING)
                .addScalar("nondescription",Hibernate.STRING)
                .addScalar("invdesc",Hibernate.STRING)
                .addScalar("amount",Hibernate.BIG_DECIMAL)
                .addScalar("total",Hibernate.BIG_DECIMAL)
                .addScalar("vat",Hibernate.BIG_DECIMAL)
                .addScalar("grandtotal",Hibernate.BIG_DECIMAL)
                .addScalar("user",Hibernate.STRING)
                .addScalar("curamount",Hibernate.STRING)
                .addScalar("tax_no",Hibernate.STRING)
                .addScalar("branch",Hibernate.STRING)
                .list();
        
        String invdescTemp = "";
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        TaxInvoiceReport taxInvoiceViewTemp  = new TaxInvoiceReport();
        for (Object[] B : QueryList) {
            TaxInvoiceReport taxInvoiceView = new TaxInvoiceReport();
            taxInvoiceView.setId(util.ConvertString(B[0]));
            taxInvoiceView.setCustomer(util.ConvertString(B[1]));
            taxInvoiceView.setAddress(util.ConvertString(B[2]));
            taxInvoiceView.setTel(util.ConvertString(B[3]));
            taxInvoiceView.setFax(util.ConvertString(B[4]));
            taxInvoiceView.setTaxno(util.ConvertString(B[5]));
            taxInvoiceView.setTaxdate(String.valueOf(sf.format(util.convertStringToDate(String.valueOf(B[6])))));
            taxInvoiceView.setPaid(util.ConvertString(B[7]));
            taxInvoiceView.setDescription(util.ConvertString(B[8]));
            taxInvoiceView.setNondescription(util.ConvertString(B[9]));
            taxInvoiceView.setInvoice(util.ConvertString(B[10]));
            taxInvoiceView.setGrandtotal(B[14] != null ? df.format(B[14]) : "0.00");
            taxInvoiceView.setUser(util.ConvertString(B[15]));
            taxInvoiceView.setCuramount(util.ConvertString(B[16]));
            taxInvoiceView.setTaxidno(B[17] != null ? util.ConvertString(B[17]) : "");
            String taxBranch = util.getTaxBranch(util.ConvertString(B[1]), util.ConvertString(B[18]));
            taxInvoiceView.setBranch(taxBranch);
            String curamount = "";
            if("THB".equalsIgnoreCase(util.ConvertString(B[16]))){
                curamount = " BAHT ";
//            } else if("JPY".equalsIgnoreCase(util.ConvertString(B[16]))){
//                curamount = " YEN ";
//            } else if("USD".equalsIgnoreCase(util.ConvertString(B[16]))){
//                curamount = " DOLLAR ";
            } else {
                curamount = " " + util.ConvertString(B[16]) + " ";
            }
//            if(B[12] != null){
                taxInvoiceView.setTotal(B[12] != null && !"0.00".equalsIgnoreCase(util.ConvertString(B[12])) ? df.format(B[12]) : "0.00");
//            }
//            if(B[13] != null){
                taxInvoiceView.setVat(B[13] != null && !"0.00".equalsIgnoreCase(util.ConvertString(B[13])) ? df.format(B[13]) : "0.00");
//            }                        
//            if(B[11] != null){
                taxInvoiceView.setAmount(B[11] != null && !"0.00".equalsIgnoreCase(util.ConvertString(B[11])) ? df.format(B[11]) : "0.00");
//            }
           
            if(sign != null){
                if("".equals(sign)){
                    taxInvoiceView.setSign("nosign");
                    taxInvoiceView.setSignname(printby);
                }else{
                    taxInvoiceView.setSign(sign);
                    String querySystemUser = "from SystemUser s where s.name like '%"+sign+"%'";
                    List<SystemUser> systemUser = session.createQuery(querySystemUser).list();
                    if(!systemUser.isEmpty()) {
                        taxInvoiceView.setSignname(systemUser.get(0).getName());
                    }        
                    
                }
            }
//            String total = taxInvoiceView.getGrandtotal().replaceAll(",", "");
//            total = total.replaceAll("\\.", ",");
//            String[] totals = total.split(",");
//            int totalWord = 0;
//            totalWord = Integer.parseInt(String.valueOf(totals[0]));
//            String wordAmount = utilityFunction.convert(totalWord);
//            String first = (wordAmount.substring(0, 1)).toUpperCase();
//            wordAmount = wordAmount.substring(1);
//            wordAmount = first+wordAmount;
//            taxInvoiceView.setTextamount(wordAmount+" "+curamount);
            String string = String.valueOf(taxInvoiceView.getGrandtotal()).replaceAll(",", "");
            String[] parts = string.split("\\.");
            String part1 = parts[0]; // number
            String part2 = parts[1]; // point
            String textmoney = (utilityFunction.convert(Integer.parseInt(part1)));
            String textmoneypoint = (utilityFunction.changPoint(String.valueOf(part2)));
            String totalWord = textmoney + curamount + textmoneypoint;
            if("".equalsIgnoreCase(textmoneypoint.trim())){
                totalWord = textmoney +curamount+" ONLY";
            }
            System.out.println(" totalWord " + totalWord);
            taxInvoiceView.setTextamount(totalWord.substring(0,1).toUpperCase() + totalWord.substring(1));
            
            if(option == 1){
                taxInvoiceView.setDescription(taxInvoiceView.getNondescription());
                data.add(taxInvoiceView);
            }else if(option == 2 ){
                taxInvoiceView.setInvoice("");
                data.add(taxInvoiceView);
            }else if(option == 3 ){
                taxInvoiceView.setAmount(taxInvoiceView.getGrandtotal());
                invdescTemp += taxInvoiceView.getInvoice() + ",";
                taxInvoiceViewTemp = taxInvoiceView;
            }
//            data.add(taxInvoiceView);
        }
        if(option == 3){
//            invdescTemp = invdescTemp.replaceAll("\\s","");
//            invdescTemp = invdescTemp.replaceAll("Inv:", "");          
//            String[] invdescs = invdescTemp.split(",");
//            String invdesc = "";
//            for (int j=0;j<invdescs.length;j++){
//                for (int k=j+1;k<invdescs.length;k++){
//                    if (k!=j && invdescs[k].equals(invdescs[j])){
//                        invdesc += (!"".equalsIgnoreCase(invdesc) ? " , " : "");
//                        invdesc += invdescs[k];
//                    }
//                }
//            }
            
            invdescTemp = invdescTemp.replaceAll("\\s","");
            invdescTemp = invdescTemp.replaceAll("Inv:", "");          
            String[] invdescs = invdescTemp.split(",");
            String invdesc = "";
            List<String> invdescsChkList = new ArrayList<String>();
            for(int i=0;i<invdescs.length;i++){
                String invNo1 = invdescs[i];
                int match = 0;
                if(!invdescsChkList.isEmpty()){
                    for(int j=0;j<invdescsChkList.size();j++){
                        String invNo2 = invdescsChkList.get(j);
                        if(invNo1.equalsIgnoreCase(invNo2)){
                            match++;
                            j = invdescsChkList.size();
                        }
                    }
                    if(match == 0){
                        invdesc += " , ";
                        invdescsChkList.add(invNo1);
                        invdesc += invNo1;
                    }
                } else {
                    invdescsChkList.add(invNo1);
                    invdesc += invNo1;
                }           
            }
            
            taxInvoiceViewTemp.setDescription("TOUR" + " " + invdesc);
            taxInvoiceViewTemp.setInvoice("");
            data.add(taxInvoiceViewTemp);
        }
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
