/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.CreditNoteSummaryReport;
import com.smi.travel.datalayer.view.dao.CreditNoteSummaryReportDao;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class CreditNoteSummaryReportImpl implements CreditNoteSummaryReportDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public List getCreditNoteSummaryReport(String from, String to, String department, String status, String systemuser) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<CreditNoteSummaryReport>();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy HH:mm:ss");
        
        String departmentshow = "ALL";
        StringBuffer query = new StringBuffer(" SELECT * FROM `creditnote_summary` ");
        boolean haveCondition = false;
        if ((from != null) && (!"".equalsIgnoreCase(from))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `creditnote_summary`.notedate >= '" + from + "'");
            haveCondition = true;
        }
        if ((to != null) && (!"".equalsIgnoreCase(to))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `creditnote_summary`.notedate <= '" + to + "'");
            haveCondition = true;
        }
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if("NORMAL".equalsIgnoreCase(status)){status = "";}
            query.append(haveCondition ? " and" : " where");
            query.append(" `creditnote_summary`.status = '" + status + "'");
            haveCondition = true;
        }
        if ((department != null) && (!"".equalsIgnoreCase(department))) {
            query.append(haveCondition ? " and" : " where");
            if(department.indexOf(",") == -1){
                query.append(" `creditnote_summary`.department = '" + department + "'"); 
                departmentshow = department;
            }else{
                String[] departmentTemp = department.split(",");
                query.append(" `creditnote_summary`.department in ('" + departmentTemp[0] + "','" + departmentTemp[1] + "') "); 
                departmentshow = "WO";
            }
            haveCondition = true;            
        }

        List<Object[]> QueryList =  session.createSQLQuery(query.toString())
                .addScalar("id",Hibernate.STRING)
                .addScalar("noteno",Hibernate.STRING)
                .addScalar("notedate",Hibernate.STRING)
                .addScalar("notefrom",Hibernate.STRING)
                .addScalar("notename",Hibernate.STRING)
                .addScalar("notedetail",Hibernate.STRING)
                .addScalar("refno",Hibernate.STRING)
                .addScalar("subtotal",Hibernate.STRING)
                .addScalar("subtotalreal",Hibernate.STRING)
                .addScalar("differ",Hibernate.STRING)
                .addScalar("vat",Hibernate.STRING)
                .addScalar("amount",Hibernate.STRING)
                .addScalar("status",Hibernate.STRING)
                .addScalar("department",Hibernate.STRING)
                .list();
        
        int no = 1;
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");
        for (Object[] B : QueryList) {
            CreditNoteSummaryReport creditNoteSummaryReport = new CreditNoteSummaryReport();
            creditNoteSummaryReport.setNo(String.valueOf(no));
            creditNoteSummaryReport.setId(util.ConvertString(B[0]));
            creditNoteSummaryReport.setNoteno(util.ConvertString(B[1]));
            creditNoteSummaryReport.setNotedate((("null".equals(String.valueOf(B[2])) ? "" : String.valueOf(df.format(util.convertStringToDate(String.valueOf(B[2])))))));
            creditNoteSummaryReport.setNotefrom(util.ConvertString(B[3]));
            creditNoteSummaryReport.setNotename(util.ConvertString(B[4]));           
            creditNoteSummaryReport.setStatus(util.ConvertString(B[12]));
            creditNoteSummaryReport.setDepartment(util.ConvertString(B[13]));
            creditNoteSummaryReport.setSystemdate(String.valueOf(dateformat.format(new Date())));
            creditNoteSummaryReport.setFrom(!"".equalsIgnoreCase(from) ? String.valueOf(df.format(util.convertStringToDate(from))) : "");
            creditNoteSummaryReport.setTo(!"".equalsIgnoreCase(to) ? String.valueOf(df.format(util.convertStringToDate(to))) : "");
            creditNoteSummaryReport.setUser(systemuser);
            creditNoteSummaryReport.setDepartmentshow(departmentshow);
            
            if(!"".equalsIgnoreCase(util.ConvertString(B[7]))){
                creditNoteSummaryReport.setSubtotal(util.ConvertString(B[7]));
            } else {
                creditNoteSummaryReport.setSubtotal("0");
            }
            if(!"".equalsIgnoreCase(util.ConvertString(B[8]))){
                creditNoteSummaryReport.setSubtotalreal(util.ConvertString(B[8]));
            } else {
                creditNoteSummaryReport.setSubtotalreal("0");
            }
            if(!"".equalsIgnoreCase(util.ConvertString(B[9]))){
                creditNoteSummaryReport.setDiffer(util.ConvertString(B[9]));
            } else {
                creditNoteSummaryReport.setDiffer("0");
            }
            if(!"".equalsIgnoreCase(util.ConvertString(B[10]))){
                creditNoteSummaryReport.setVat(util.ConvertString(B[10]));
            } else {
                creditNoteSummaryReport.setVat("0");
            }if(!"".equalsIgnoreCase(util.ConvertString(B[11]))){
                creditNoteSummaryReport.setAmount(util.ConvertString(B[11]));
            } else {
                creditNoteSummaryReport.setAmount("0");
            }
            
            String noteDetail = checkNoteDetail(util.ConvertString(B[5]));
            creditNoteSummaryReport.setNotedetail(noteDetail);
            String refNo = checkRefNo(util.ConvertString(B[6]));
            creditNoteSummaryReport.setRefno(refNo);     
            
            data.add(creditNoteSummaryReport);
            no++;
        }
        
        return data;
    }

    private String checkNoteDetail(String checkNoteList) {
        String result = "";
        String[] chkNoteList = checkNoteList.split("\\,");
        List<String> chkNoteChkList = new ArrayList<String>();
        for(int i=0;i<chkNoteList.length;i++){
            String checkNote1 = chkNoteList[i];
            int match = 0;
            if(!chkNoteChkList.isEmpty()){
                for(int j=0;j<chkNoteChkList.size();j++){
                    String checkNote2 = chkNoteChkList.get(j);
                    if(checkNote1.equalsIgnoreCase(checkNote2)){
                        match++;
                        j = chkNoteChkList.size();
                    }
                }
                if(match == 0){
                    result += ",";
                    chkNoteChkList.add(checkNote1);
                    result += checkNote1;
                }
            } else {
                chkNoteChkList.add(checkNote1);
                result += checkNote1;
            }           
        }
        
        return result;
    }
    
    private String checkRefNo(String referenceNoList) {
        String result = "";
        String[] refNoList = referenceNoList.split("\\,");
        List<String> refNoChkList = new ArrayList<String>();
        for(int i=0;i<refNoList.length;i++){
            String refNo1 = refNoList[i];
            int match = 0;
            if(!refNoChkList.isEmpty()){
                for(int j=0;j<refNoChkList.size();j++){
                    String refNo2 = refNoChkList.get(j);
                    if(refNo1.equalsIgnoreCase(refNo2)){
                        match++;
                        j = refNoChkList.size();
                    }
                }
                if(match == 0){
                    result += "\n";
                    refNoChkList.add(refNo1);
                    result += refNo1;
                }
            } else {
                refNoChkList.add(refNo1);
                result += refNo1;
            }           
        }
        
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
     
}
