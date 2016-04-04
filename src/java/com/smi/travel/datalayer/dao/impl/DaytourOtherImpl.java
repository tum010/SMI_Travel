/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.report.model.DaytourOther;
import com.smi.travel.datalayer.view.dao.DaytourOtherDao;
import com.smi.travel.util.UtilityFunction;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Kanokporn
 */
public class DaytourOtherImpl implements DaytourOtherDao{
    private SessionFactory sessionFactory;
    @Override
    public List getDaytourOtherReport(String refno, String status) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        UtilityFunction util = new UtilityFunction();
         System.out.println("Get Report");
        List<Object[]> QueryDaytourOtherList = session.createSQLQuery("SELECT * FROM `DaytourOther` where refno = '"+refno+"'")
                .addScalar("refno", Hibernate.STRING)
                .addScalar("createdate", Hibernate.DATE)
                .addScalar("leadername", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .addScalar("date", Hibernate.DATE)
                .addScalar("time", Hibernate.TIME)
                .addScalar("adult", Hibernate.INTEGER)
                .addScalar("child", Hibernate.INTEGER)
                .addScalar("infant", Hibernate.INTEGER)
                .addScalar("passenger", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        System.out.println("Size Report Other Day tour : " + QueryDaytourOtherList.size());
        for (Object[] B : QueryDaytourOtherList) {
             DaytourOther daytourother = new DaytourOther();
             
             daytourother.setRefno(util.ConvertString(B[0]));
             
             SimpleDateFormat format =null;
                format = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
                Date curDate = (Date) B[1];
                String DateToStr = format.format(curDate);
                System.out.println(DateToStr);
            // String parseDate = util.parseDate(util.ConvertString(B[1]));
             daytourother.setCreatedate(DateToStr);
             //System.out.println("Date : "+ parseDate);
             
             daytourother.setLeadername(util.ConvertString(B[2]));
             daytourother.setCode(util.ConvertString(B[3]));
             daytourother.setDescription(util.ConvertString(B[4]));
             daytourother.setDate(util.ConvertString(B[5]));
             daytourother.setTime(util.ConvertString(B[6]));
             daytourother.setAdult(util.ConvertInt(B[7]));
             daytourother.setChild(util.ConvertInt(B[8]));
             daytourother.setInfant(util.ConvertInt(B[9]));             
             daytourother.setRemark(util.ConvertString(B[11]));
             data.add(daytourother);  
         }
        
        if(QueryDaytourOtherList != null){           
            List<String> queryLand = session.createSQLQuery("SELECT * FROM `land_voucher_passenger` where ref_no = '"+refno+"'")
                    .addScalar("leader_name", Hibernate.STRING)
                    .list();

            for(int i=0; i<data.size(); i++){
                DaytourOther daytourOther = (DaytourOther) data.get(i);
                for (int j=0; j<queryLand.size(); j++) {
                    String passergerName = queryLand.get(j);
                    if(j == 0){
                        daytourOther.setPassenger1(passergerName);
                    }else if(j == 1){
                        daytourOther.setPassenger2(passergerName);
                    }else if(j == 2){
                        daytourOther.setPassenger3(passergerName);
                    }else if(j == 3){
                        daytourOther.setPassenger4(passergerName);
                    }else if(j == 4){
                        daytourOther.setPassenger5(passergerName);
                    }else if(j == 5){
                        daytourOther.setPassenger6(passergerName);
                    }
                }
            }    
        }    
        
        if("OK".equals(status)){
            data = checkReportUni(data);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    private List<DaytourOther>  checkReportUni(List<DaytourOther> listDaytourOld){
        List data = new ArrayList<DaytourOther>();
        int j = 0 ;
        int i = 0 ;
        for ( i = 0; i < listDaytourOld.size(); i++) {
            for (j = (i+1) ; j < listDaytourOld.size() ; j++) {
                if(listDaytourOld.get(i).getCode().equalsIgnoreCase(listDaytourOld.get(j).getCode())){
                    listDaytourOld.remove(j);
                }
            }
        }
        data = listDaytourOld;
        return data;
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }   

    @Override
    public List getDaytourOperationOtherReport(String otherId, String passengerId, String refno, String status, String printby) {
       Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        UtilityFunction util = new UtilityFunction();
        System.out.println("Get Report");
        String[] otherIdList = otherId.split(",");
        String otherIdTemp = "";
        for(int i=0; i<otherIdList.length; i++){
            otherIdTemp += (!"".equalsIgnoreCase(otherIdTemp) ? ","+"'"+otherIdList[i]+"'" : "'"+otherIdList[i]+"'");
        }
        List<Object[]> QueryDaytourOtherList = session.createSQLQuery("SELECT * FROM `DaytourOther` where otherId IN (" + otherId + ")")
                .addScalar("refno", Hibernate.STRING)
                .addScalar("createdate", Hibernate.DATE)
                .addScalar("leadername", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .addScalar("date", Hibernate.DATE)
                .addScalar("time", Hibernate.TIME)
                .addScalar("adult", Hibernate.INTEGER)
                .addScalar("child", Hibernate.INTEGER)
                .addScalar("infant", Hibernate.INTEGER)
                .addScalar("passenger", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        System.out.println("Size Report Other Day tour : " + QueryDaytourOtherList.size());
        for (Object[] B : QueryDaytourOtherList) {
             DaytourOther daytourother = new DaytourOther();
             String refNo = util.ConvertString(B[0]);
             String refNo1 = refNo.substring(0, 2);
             String refNo2 = refNo.substring(2, 6);
             daytourother.setRefno(refNo1+"-"+refNo2);
             
             SimpleDateFormat format =null;
                format = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                Date curDate = (Date) B[1];
                String DateToStr = format.format(curDate);
                System.out.println(DateToStr);
            // String parseDate = util.parseDate(util.ConvertString(B[1]));
             daytourother.setCreatedate(DateToStr.toUpperCase());
             //System.out.println("Date : "+ parseDate);
             SimpleDateFormat dateformat = new SimpleDateFormat();
             dateformat.applyPattern("dd-MM-yyyy");         
             daytourother.setLeadername(util.ConvertString(B[2]));
             daytourother.setCode(util.ConvertString(B[3]));
             daytourother.setDescription(util.ConvertString(B[4]));
             daytourother.setDate((B[5] == null || "".equalsIgnoreCase(util.ConvertString(B[5])))? "" : String.valueOf(dateformat.format(util.convertStringToDate(String.valueOf(B[5])))));
             daytourother.setTime(util.ConvertString(B[6]));
             daytourother.setAdult(util.ConvertInt(B[7]));
             daytourother.setChild(util.ConvertInt(B[8]));
             daytourother.setInfant(util.ConvertInt(B[9]));             
             daytourother.setRemark(util.ConvertString(B[11]));
             daytourother.setPrintby(printby);
             data.add(daytourother);  
         }
        
        if(QueryDaytourOtherList != null && !"".equalsIgnoreCase(passengerId)){
            List<String> queryLand = session.createSQLQuery("SELECT * FROM `land_voucher_passenger` where customerId IN (" + passengerId + ") AND ref_no = '" + refno + "' order by orderno")
                    .addScalar("leader_name", Hibernate.STRING)
                    .list();

            for(int i=0; i<data.size(); i++){
                DaytourOther daytourOther = (DaytourOther) data.get(i);
                for (int j=0; j<queryLand.size(); j++) {
                    String passergerName = queryLand.get(j);
                    if(j == 0){
                        daytourOther.setPassenger1(passergerName);
                    }else if(j == 1){
                        daytourOther.setPassenger2(passergerName);
                    }else if(j == 2){
                        daytourOther.setPassenger3(passergerName);
                    }else if(j == 3){
                        daytourOther.setPassenger4(passergerName);
                    }else if(j == 4){
                        daytourOther.setPassenger5(passergerName);
                    }else if(j == 5){
                        daytourOther.setPassenger6(passergerName);
                    }
                }
            }    
        }    
        
        if("OK".equals(status)){
            data = checkReportUni(data);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
}
