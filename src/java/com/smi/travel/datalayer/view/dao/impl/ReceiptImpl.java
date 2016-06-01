/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.view.dao.ReceiptDao;
import com.smi.travel.datalayer.view.entity.ReceiptView;
import com.smi.travel.util.UtilityFunction;
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
public class ReceiptImpl implements ReceiptDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public List getReceipt(String receiptId,int option,String sign,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `receipt_view` where `receipt_view`.id =  "+receiptId)
                .addScalar("id",Hibernate.STRING)
                .addScalar("recto",Hibernate.STRING)
                .addScalar("recadd",Hibernate.STRING)
                .addScalar("tel",Hibernate.STRING)
                .addScalar("fax",Hibernate.STRING)
                .addScalar("recno",Hibernate.STRING)
                .addScalar("recdate",Hibernate.STRING)
                .addScalar("paidby",Hibernate.STRING)
                .addScalar("description",Hibernate.STRING)
                .addScalar("non_description",Hibernate.STRING)
                .addScalar("invdesc",Hibernate.STRING)
                .addScalar("amount",Hibernate.STRING)
                .addScalar("totalamount",Hibernate.STRING)
                .addScalar("cash",Hibernate.STRING)
                .addScalar("cashflag",Hibernate.STRING)
                .addScalar("transfer",Hibernate.STRING)
                .addScalar("transferflag",Hibernate.STRING)
                .addScalar("tax",Hibernate.STRING)
                .addScalar("taxflag",Hibernate.STRING)
                .addScalar("chqbank",Hibernate.STRING)
                .addScalar("chqno",Hibernate.STRING)
                .addScalar("chqdate",Hibernate.STRING)
                .addScalar("chqvalue",Hibernate.STRING)
                .addScalar("chqflag",Hibernate.STRING)
                .addScalar("credit",Hibernate.STRING)
                .addScalar("creditflag",Hibernate.STRING)
                .addScalar("chqbank2",Hibernate.STRING)
                .addScalar("chqno2",Hibernate.STRING)
                .addScalar("chqdate2",Hibernate.STRING)
                .addScalar("chqvalue2",Hibernate.STRING)
                .addScalar("chqflag2",Hibernate.STRING)
                .addScalar("currency",Hibernate.STRING)
                .addScalar("tax_no",Hibernate.STRING)
                .addScalar("branch",Hibernate.STRING)
                .list();
        
        List data = new ArrayList();
        String invdescTemp = "";
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        ReceiptView receiptViewTemp  = new ReceiptView();
        for(Object[] T : QueryList){
            ReceiptView receiptView = new ReceiptView();
            receiptView.setId((("null".equals(String.valueOf(T[0])) ? "" : String.valueOf(T[0]))));
            receiptView.setRecto((("null".equals(String.valueOf(T[1])) ? "" : String.valueOf(T[1]))));
            receiptView.setRecadd((("null".equals(String.valueOf(T[2])) ? "" : String.valueOf(T[2]))));
            receiptView.setTel((("null".equals(String.valueOf(T[3])) ? "" : String.valueOf(T[3]))));
            receiptView.setFax((("null".equals(String.valueOf(T[4])) ? "" : String.valueOf(T[4]))));
            receiptView.setRecno((("null".equals(String.valueOf(T[5])) ? "" : String.valueOf(T[5]))));
            receiptView.setRecdate((("null".equals(String.valueOf(T[6])) ? "" : String.valueOf(sf.format(util.convertStringToDate(String.valueOf(T[6])))))));
            receiptView.setPaidby((("null".equals(String.valueOf(T[7]))? "" : String.valueOf(T[7]))));
            receiptView.setDescription((("null".equals(String.valueOf(T[8])) ? "" : String.valueOf(T[8]))));
            receiptView.setNondescription((("null".equals(String.valueOf(T[9])) ? "" : String.valueOf(T[9]))));
            receiptView.setInvdesc((("null".equals(String.valueOf(T[10])) ? "" : String.valueOf(T[10]))));
            receiptView.setAmount((("0.00".equals(String.valueOf(T[11])) ? "" : String.valueOf(T[11]))));
            receiptView.setTotalamount((("0.00".equals(String.valueOf(T[12]))? "" : String.valueOf(T[12]))));
            receiptView.setCash((("0.00".equals(String.valueOf(T[13])) ? "" : String.valueOf(T[13]))));
            receiptView.setCashflag((("0.00".equals(String.valueOf(T[14])) ? "" : String.valueOf(T[14]))));
            receiptView.setTransfer((("0.00".equals(String.valueOf(T[15]))? "" : String.valueOf(T[15]))));
            receiptView.setTransferflag((("0.00".equals(String.valueOf(T[16])) ? "" : String.valueOf(T[16]))));
            receiptView.setTax((("0.00".equals(String.valueOf(T[17])) ? "" : String.valueOf(T[17]))));
            receiptView.setTaxflag((("0.00".equals(String.valueOf(T[18])) ? "" : String.valueOf(T[18]))));
            receiptView.setChqbank((("null".equals(String.valueOf(T[19])) ? "" : String.valueOf(T[19]))));
            receiptView.setChqno((("null".equals(String.valueOf(T[20])) ? "" : String.valueOf(T[20]))));
            receiptView.setChqdate((("null".equals(String.valueOf(T[21])) ? "" : String.valueOf(sf.format(util.convertStringToDate(String.valueOf(T[21])))))));
            receiptView.setChqvalue((("0.00".equals(String.valueOf(T[22])) ? "" : String.valueOf(T[22]))));
            receiptView.setChqbankflag((("0.00".equals(String.valueOf(T[23]))? "" : String.valueOf(T[23]))));
            receiptView.setCredit((("null".equals(String.valueOf(T[24])) ? "" : String.valueOf(T[24]))));
            receiptView.setCreditflag((("0.00".equals(String.valueOf(T[25])) ? "" : String.valueOf(T[25]))));
            receiptView.setChqbank2((("null".equals(String.valueOf(T[26])) ? "" : String.valueOf(T[26]))));
            receiptView.setChqno2((("null".equals(String.valueOf(T[27])) ? "" : String.valueOf(T[27]))));
            receiptView.setChqdate2((("null".equals(String.valueOf(T[28])) ? "" : String.valueOf(sf.format(util.convertStringToDate(String.valueOf(T[28])))))));
            receiptView.setChqvalue2((("0.00".equals(String.valueOf(T[29])) ? "" : String.valueOf(T[29]))));
            receiptView.setChqbankflag2((("0.00".equals(String.valueOf(T[30]))? "" : String.valueOf(T[30]))));
            receiptView.setTaxidno(T[32] != null ? String.valueOf(T[32]) : "");
            receiptView.setBranch(T[33] != null ? String.valueOf(T[33]) : "");
            if(sign != null){
                if("".equals(sign)){
                    receiptView.setSign("nosign");
                    receiptView.setSignname(printby);
                }else{
                    receiptView.setSign(sign);
                    String querySystemUser = "from SystemUser s where s.name like '%"+sign+"%'";
                    List<SystemUser> systemUser = session.createQuery(querySystemUser).list();
                    if(!systemUser.isEmpty()) {
                        receiptView.setSignname(systemUser.get(0).getName());
                    }        
                    
                }
            }
            String curtemp = "null".equals(String.valueOf(T[31])) ? "" : String.valueOf(T[31]);
            System.err.println("receiptView cash " +receiptView.getCash());
//            String total = (receiptView.getTotalamount()).replaceAll("\\.", ",");
//            String[] totals = total.split(",");
//            BigDecimal totalWord = new BigDecimal(BigInteger.ZERO);
//            totalWord = new BigDecimal(String.valueOf(totals[0]));
            String string = String.valueOf(receiptView.getTotalamount());
            String[] parts = string.split("\\.");
            String part1 = parts[0]; // number
            String part2 = parts[1]; // point
            String textmoney = (utilityFunction.convert(Integer.parseInt(part1)));
            String textmoneypoint = (utilityFunction.changPoint(String.valueOf(part2)));
            String currency = "";
            if("JPY".equalsIgnoreCase(curtemp)){
                currency = " YEN";
            }else if("THB".equalsIgnoreCase(curtemp)){
                currency = " BAHT";
            }else if("USD".equalsIgnoreCase(curtemp)){
                currency = " DOLLAR";
            }else{
                currency = curtemp;
            }
            String totalWord = textmoney + currency + textmoneypoint;
            if("".equalsIgnoreCase(textmoneypoint.trim())){
                totalWord = textmoney +currency+" ONLY";
            }
            System.out.println(" totalWord " + totalWord);
            receiptView.setTextmoney(totalWord.substring(0,1).toUpperCase() + totalWord.substring(1));
//            receiptView.setTextmoney(textmoney.substring(0,1).toUpperCase() + textmoney.substring(1));

            if(option == 1){
                receiptView.setDescription(receiptView.getNondescription());
                System.out.println(" receiptView.getDes " +receiptView.getDescription());
                data.add(receiptView);
            }else if(option == 2 ){
                receiptView.setInvdesc("");
                System.out.println(" receiptView.getDes " +receiptView.getDescription());
                data.add(receiptView);
            }
            else if(option == 3 ){
                receiptView.setAmount(receiptView.getTotalamount());
                invdescTemp += receiptView.getInvdesc() + ",";
                receiptViewTemp = receiptView;
            }
            
           
        }
        if(option == 3){
            System.out.println(" invdesc " + invdescTemp);
            String[] invdescs = invdescTemp.split(",");
            String invdesc = invdescTemp;
            for (int j=0;j<invdescs.length;j++){
                for (int k=j+1;k<invdescs.length;k++){
                    if (k!=j && invdescs[k].equals(invdescs[j])){
                        invdesc = invdescs[k];
                    }
                }
            }
            receiptViewTemp.setDescription("TOUR" + " " + invdesc);
            receiptViewTemp.setInvdesc("");
            data.add(receiptViewTemp);
        }
        
        if(data.isEmpty()) {
            return null;
        }
        
        session.close();
        return data;
    }

    @Override
    public List getReceiptSummary(String dateFrom,String dateTo,String departmentRec,String recType,String status,String username){
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        String query = "SELECT * FROM `receipt_summary` where";
        int checkQuery = 0;
        String prefix ="";
        
        if(((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))) &&((dateTo != null) &&(!"".equalsIgnoreCase(dateTo)))){
            query += " recdate >= '" + util.covertStringDateToFormatYMD(dateFrom) +"' and recdate <= '" + util.covertStringDateToFormatYMD(dateTo) + "' ";
            checkQuery = 1;
        }else if((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))){
            checkQuery = 1;
            query +=  " recdate >= '" + util.covertStringDateToFormatYMD(dateFrom) + "'";

        }else if((dateTo != null) &&(!"".equalsIgnoreCase(dateTo))){
            checkQuery = 1;
            query += " recdate <= '" + util.covertStringDateToFormatYMD(dateTo) + "'";
        }

        if((departmentRec != null) &&(!"".equalsIgnoreCase(departmentRec))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            if(departmentRec.indexOf(",") == -1){
                query += prefix+" department = '"+departmentRec+"'";
            }else{
                String[] departmentTemp = departmentRec.split(",");
                query += prefix+" department in ('" + departmentTemp[0] + "','" + departmentTemp[1] + "') ";
            }            
        }

        if((recType != null) &&(!"".equalsIgnoreCase(recType))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " rectype = '"+recType+"'";
        }
        
        if((status != null) &&(!"".equalsIgnoreCase(status))){
            if("NORMAL".equalsIgnoreCase(status)){status = "NORMAL";}
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " status = '"+status+"'";
        }
        query += " order by payby = 'Wait' desc , recno asc ; " ;
        
        if(checkQuery == 0){query = query.replaceAll("where", "");}
        System.out.println("query : "+query);
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                                    .addScalar("recno",Hibernate.STRING)
                                    .addScalar("recdate",Hibernate.STRING)
                                    .addScalar("receivedate",Hibernate.STRING)
                                    .addScalar("recfrom",Hibernate.STRING)
                                    .addScalar("recname",Hibernate.STRING)
                                    .addScalar("rectype",Hibernate.STRING)
                                    .addScalar("department",Hibernate.STRING)
                                    .addScalar("recdetail",Hibernate.STRING)
                                    .addScalar("invno",Hibernate.STRING)
                                    .addScalar("invamount",Hibernate.STRING)
                                    .addScalar("diff",Hibernate.STRING)
                                    .addScalar("recamount",Hibernate.STRING)
                                    .addScalar("payby",Hibernate.STRING)
                                    .addScalar("cash",Hibernate.STRING)
                                    .addScalar("chq",Hibernate.STRING)
                                    .addScalar("credit",Hibernate.STRING)
                                    .addScalar("banktransfer",Hibernate.STRING)
                                    .addScalar("wt",Hibernate.STRING)
                                    .addScalar("cashminus",Hibernate.STRING)
                                    .addScalar("status",Hibernate.STRING)
                                    .list();
        List data = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.US);
        int i = 1;
        for(Object[] recSum : QueryList){
            ReceiptView receiptView = new ReceiptView();
            receiptView.setSystemdate(String.valueOf(dateformat.format(new Date())));
            receiptView.setUser(username);
            if((dateFrom != null) && (!"".equalsIgnoreCase(dateFrom))){
                receiptView.setFrom(String.valueOf(df.format(util.convertStringToDate(dateFrom))));
            }else{
                receiptView.setFrom("");
            }
            if((dateTo != null) && (!"".equalsIgnoreCase(dateTo))){
                receiptView.setTo(String.valueOf(df.format(util.convertStringToDate(dateTo))));
            }else{
                receiptView.setTo("");
            }
            
            if("Wendy".equals(String.valueOf(departmentRec))){
                receiptView.setDepartment("WENDY");
            }else if("Inbound".equals(String.valueOf(departmentRec))){
                receiptView.setDepartment("INBOUND");
            }else if("Outbound".equals(String.valueOf(departmentRec))){
                receiptView.setDepartment("OUTBOUND");
            }else if("Wendy,Outbound".equals(String.valueOf(departmentRec))){
                receiptView.setDepartment("WO");
            }else{
                receiptView.setDepartment("ALL");
            }
            
            receiptView.setRecno((("null".equals(String.valueOf(recSum[0])) ? "" : String.valueOf(recSum[0]))));
            receiptView.setRecdate((("null".equals(String.valueOf(recSum[1])) ? "" : String.valueOf(df.format(util.convertStringToDate(String.valueOf(recSum[1])))))));
            receiptView.setReceivedate((("null".equals(String.valueOf(recSum[2])) ? "" : String.valueOf(df.format(util.convertStringToDate(String.valueOf(recSum[2])))))));
            receiptView.setRecfrom((("null".equals(String.valueOf(recSum[3])) ? "" : String.valueOf(recSum[3]))));
            receiptView.setRecname((("null".equals(String.valueOf(recSum[4])) ? "" : String.valueOf(recSum[4]))));
            receiptView.setInvamount((("0.00".equals(String.valueOf(recSum[9])) ? "0" : String.valueOf(recSum[9]))));
            receiptView.setDiff((("0.00".equals(String.valueOf(recSum[10])) ? "0" : String.valueOf(recSum[10]))));
            receiptView.setRecamount((("0.00".equals(String.valueOf(recSum[11])) ? "0" : String.valueOf(recSum[11]))));
            receiptView.setPayby((("null".equals(String.valueOf(recSum[12])) ? "" : String.valueOf(recSum[12]))));
            receiptView.setCash((("0.00".equals(String.valueOf(recSum[13])) ? "0" : String.valueOf(recSum[13]))));
            receiptView.setChq((("0.00".equals(String.valueOf(recSum[14])) ? "0" : String.valueOf(recSum[14]))));
            receiptView.setCreditcard((("0.00".equals(String.valueOf(recSum[15])) ? "0" : String.valueOf(recSum[15]))));
            receiptView.setBanktransfer((("0.00".equals(String.valueOf(recSum[16])) ? "0" : String.valueOf(recSum[16]))));
            receiptView.setWt((("0.00".equals(String.valueOf(recSum[17])) ? "0" : String.valueOf(recSum[17]))));
            receiptView.setCashminus((("0.00".equals(String.valueOf(recSum[18])) ? "0" : String.valueOf(recSum[18]))));
            receiptView.setNo(String.valueOf(i));
            receiptView.setStatus((("null".equals(String.valueOf(recSum[19])) ? "" : String.valueOf(recSum[19]))));

            String recdetail = checkRecDetail(util.ConvertString(recSum[7]));
            receiptView.setRecdetail(recdetail);
            
            String invNo = checkInvoiceNo(util.ConvertString(recSum[8]));
            receiptView.setInvno(invNo);
            
            data.add(receiptView);
            i++;
        }

        if(data.isEmpty()) {
            return null;
        }
        
        session.close();
        return data;
    }
    
    private String checkInvoiceNo(String invoiceNoList) {
        String result = "";
        String[] recNoList = invoiceNoList.split("\\,");
        List<String> invChkList = new ArrayList<String>();
        for(int i=0;i<recNoList.length;i++){
            String invNo1 = recNoList[i];
            int match = 0;
            if(!invChkList.isEmpty()){
                for(int j=0;j<invChkList.size();j++){
                    String invNo2 = invChkList.get(j);
                    if(invNo1.equalsIgnoreCase(invNo2)){
                        match++;
                        j = invChkList.size();
                    }
                }
                if(match == 0){
                    result += "\n";
                    invChkList.add(invNo1);
                    result += invNo1;
                }
            } else {
                invChkList.add(invNo1);
                result += invNo1;
            }           
        }
        
        return result;
    }
    
    private String checkRecDetail(String recDetailList) {
        String result = "";
        String[] recDeList = recDetailList.split("\\,");
        List<String> recDeChkList = new ArrayList<String>();
        for(int i=0;i<recDeList.length;i++){
            String detail = recDeList[i];
            int match = 0;
            if(!recDeChkList.isEmpty()){
                for(int j=0;j<recDeChkList.size();j++){
                    String detail2 = recDeChkList.get(j);
                    if(detail.equalsIgnoreCase(detail2)){
                        match++;
                        j = recDeChkList.size();
                    }
                }
                if(match == 0){
                    result += ",";
                    recDeChkList.add(detail);
                    result += detail;
                }
            } else {
                recDeChkList.add(detail);
                result += detail;
            }           
        }
        
        return result;
    }
    
    @Override
    public List getReceiptTemp(String receiptId, int option, String sign, String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `receipt_view` where `receipt_view`.id =  "+receiptId)
                .addScalar("id",Hibernate.STRING)
                .addScalar("recto",Hibernate.STRING)
                .addScalar("recadd",Hibernate.STRING)
                .addScalar("tel",Hibernate.STRING)
                .addScalar("fax",Hibernate.STRING)
                .addScalar("recno",Hibernate.STRING)
                .addScalar("recdate",Hibernate.STRING)
                .addScalar("paidby",Hibernate.STRING)
                .addScalar("description",Hibernate.STRING)
                .addScalar("non_description",Hibernate.STRING)
                .addScalar("invdesc",Hibernate.STRING)
                .addScalar("amount",Hibernate.STRING)
                .addScalar("totalamount",Hibernate.STRING)
                .addScalar("cash",Hibernate.STRING)
                .addScalar("cashflag",Hibernate.STRING)
                .addScalar("transfer",Hibernate.STRING)
                .addScalar("transferflag",Hibernate.STRING)
                .addScalar("tax",Hibernate.STRING)
                .addScalar("taxflag",Hibernate.STRING)
                .addScalar("chqbank",Hibernate.STRING)
                .addScalar("chqno",Hibernate.STRING)
                .addScalar("chqdate",Hibernate.STRING)
                .addScalar("chqvalue",Hibernate.STRING)
                .addScalar("chqflag",Hibernate.STRING)
                .addScalar("credit",Hibernate.STRING)
                .addScalar("creditflag",Hibernate.STRING)
                .addScalar("chqbank2",Hibernate.STRING)
                .addScalar("chqno2",Hibernate.STRING)
                .addScalar("chqdate2",Hibernate.STRING)
                .addScalar("chqvalue2",Hibernate.STRING)
                .addScalar("chqflag2",Hibernate.STRING)
                .addScalar("currency",Hibernate.STRING)
                .addScalar("tax_no",Hibernate.STRING)
                .addScalar("branch",Hibernate.STRING)
                .list();
        
        List data = new ArrayList();
        String invdescTemp = "";
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        ReceiptView receiptViewTemp  = new ReceiptView();
        for(Object[] T : QueryList){
            ReceiptView receiptView = new ReceiptView();
            receiptView.setId((("null".equals(String.valueOf(T[0])) ? "" : String.valueOf(T[0]))));
            receiptView.setRecto((("null".equals(String.valueOf(T[1])) ? "" : String.valueOf(T[1]))));
            receiptView.setRecadd((("null".equals(String.valueOf(T[2])) ? "" : String.valueOf(T[2]))));
            receiptView.setTel((("null".equals(String.valueOf(T[3])) ? "" : String.valueOf(T[3]))));
            receiptView.setFax((("null".equals(String.valueOf(T[4])) ? "" : String.valueOf(T[4]))));
            receiptView.setRecno((("null".equals(String.valueOf(T[5])) ? "" : String.valueOf(T[5]))));
            receiptView.setRecdate((("null".equals(String.valueOf(T[6])) ? "" : String.valueOf(sf.format(util.convertStringToDate(String.valueOf(T[6])))))));
            receiptView.setPaidby((("null".equals(String.valueOf(T[7]))? "" : String.valueOf(T[7]))));
            receiptView.setDescription((("null".equals(String.valueOf(T[8])) ? "" : String.valueOf(T[8]))));
            receiptView.setNondescription((("null".equals(String.valueOf(T[9])) ? "" : String.valueOf(T[9]))));
            receiptView.setInvdesc((("null".equals(String.valueOf(T[10])) ? "" : String.valueOf(T[10]))));
            receiptView.setAmount((("0.00".equals(String.valueOf(T[11])) ? "" : String.valueOf(T[11]))));
            receiptView.setTotalamount((("0.00".equals(String.valueOf(T[12]))? "" : String.valueOf(T[12]))));
            receiptView.setCash((("0.00".equals(String.valueOf(T[13])) ? "" : String.valueOf(T[13]))));
            receiptView.setCashflag((("0.00".equals(String.valueOf(T[14])) ? "" : String.valueOf(T[14]))));
            receiptView.setTransfer((("0.00".equals(String.valueOf(T[15]))? "" : String.valueOf(T[15]))));
            receiptView.setTransferflag((("0.00".equals(String.valueOf(T[16])) ? "" : String.valueOf(T[16]))));
            receiptView.setTax((("0.00".equals(String.valueOf(T[17])) ? "" : String.valueOf(T[17]))));
            receiptView.setTaxflag((("0.00".equals(String.valueOf(T[18])) ? "" : String.valueOf(T[18]))));
            receiptView.setChqbank((("null".equals(String.valueOf(T[19])) ? "" : String.valueOf(T[19]))));
            receiptView.setChqno((("null".equals(String.valueOf(T[20])) ? "" : String.valueOf(T[20]))));
            receiptView.setChqdate((("null".equals(String.valueOf(T[21])) ? "" : String.valueOf(sf.format(util.convertStringToDate(String.valueOf(T[21])))))));
            receiptView.setChqvalue((("0.00".equals(String.valueOf(T[22])) ? "" : String.valueOf(T[22]))));
            receiptView.setChqbankflag((("0.00".equals(String.valueOf(T[23]))? "" : String.valueOf(T[23]))));
            receiptView.setCredit((("null".equals(String.valueOf(T[24])) ? "" : String.valueOf(T[24]))));
            receiptView.setCreditflag((("0.00".equals(String.valueOf(T[25])) ? "" : String.valueOf(T[25]))));
            receiptView.setChqbank2((("null".equals(String.valueOf(T[26])) ? "" : String.valueOf(T[26]))));
            receiptView.setChqno2((("null".equals(String.valueOf(T[27])) ? "" : String.valueOf(T[27]))));
            receiptView.setChqdate2((("null".equals(String.valueOf(T[28])) ? "" : String.valueOf(sf.format(util.convertStringToDate(String.valueOf(T[28])))))));
            receiptView.setChqvalue2((("0.00".equals(String.valueOf(T[29])) ? "" : String.valueOf(T[29]))));
            receiptView.setChqbankflag2((("0.00".equals(String.valueOf(T[30]))? "" : String.valueOf(T[30]))));
            receiptView.setTaxidno(T[32] != null ? String.valueOf(T[32]) : "");
            receiptView.setBranch(T[33] != null ? String.valueOf(T[33]) : "");
            if(sign != null){
                if("".equals(sign)){
                    receiptView.setSign("nosign");
                    receiptView.setSignname("ACCOUNT");
                }else{
                    receiptView.setSign(sign);
                    String querySystemUser = "from SystemUser s where s.name like '%"+sign+"%'";
                    List<SystemUser> systemUser = session.createQuery(querySystemUser).list();
                    if(!systemUser.isEmpty()) {
                        receiptView.setSignname("ACCOUNT");
                    }        
                }
            }
            String curtemp = "null".equals(String.valueOf(T[31])) ? "" : String.valueOf(T[31]);
            System.err.println("receiptView cash " +receiptView.getCash());
//            String total = (receiptView.getTotalamount()).replaceAll("\\.", ",");
//            String[] totals = total.split(",");
//            BigDecimal totalWord = new BigDecimal(BigInteger.ZERO);
//            totalWord = new BigDecimal(String.valueOf(totals[0]));
            String string = String.valueOf(receiptView.getTotalamount());
            String[] parts = string.split("\\.");
            String part1 = parts[0]; // number
            String part2 = parts[1]; // point
            String textmoney = (utilityFunction.convert(Integer.parseInt(part1)));
            String textmoneypoint = (utilityFunction.changPoint(String.valueOf(part2)));
            String currency = "";
            if("JPY".equalsIgnoreCase(curtemp)){
                currency = " YEN";
            }else if("THB".equalsIgnoreCase(curtemp)){
                currency = " BAHT";
            }else if("USD".equalsIgnoreCase(curtemp)){
                currency = " DOLLAR";
            }
            String totalWord = textmoney + currency + textmoneypoint;
            if("".equalsIgnoreCase(textmoneypoint.trim())){
                totalWord = textmoney +currency+" ONLY";
            }
            System.out.println(" totalWord " + totalWord);
            receiptView.setTextmoney(totalWord.substring(0,1).toUpperCase() + totalWord.substring(1));
//            receiptView.setTextmoney(textmoney.substring(0,1).toUpperCase() + textmoney.substring(1));

            if(option == 1){
                receiptView.setDescription(receiptView.getNondescription());
                System.out.println(" receiptView.getDes " +receiptView.getDescription());
                data.add(receiptView);
            }else if(option == 2 ){
                receiptView.setInvdesc("");
                System.out.println(" receiptView.getDes " +receiptView.getDescription());
                data.add(receiptView);
            }
            else if(option == 3 ){
                receiptView.setAmount(receiptView.getTotalamount());
                invdescTemp += receiptView.getInvdesc() + ",";
                receiptViewTemp = receiptView;
            }
            
           
        }
        if(option == 3){
            System.out.println(" invdesc " + invdescTemp);
            String[] invdescs = invdescTemp.split(",");
            String invdesc = invdescTemp;
            for (int j=0;j<invdescs.length;j++){
                for (int k=j+1;k<invdescs.length;k++){
                    if (k!=j && invdescs[k].equals(invdescs[j])){
                        invdesc = invdescs[k];
                    }
                }
            }
            receiptViewTemp.setDescription("TOUR" + " " + invdesc);
            receiptViewTemp.setInvdesc("");
            data.add(receiptViewTemp);
        }
        
        if(data.isEmpty()) {
            return null;
        }
        
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
