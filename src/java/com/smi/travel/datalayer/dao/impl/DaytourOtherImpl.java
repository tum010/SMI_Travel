/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.report.model.DaytourOther;
import com.smi.travel.datalayer.view.dao.DaytourOtherDao;
import com.smi.travel.datalayer.view.dao.impl.GuideJobImpl;
import com.smi.travel.util.UtilityFunction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public List getDaytourOtherReport(String refno) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        UtilityFunction util = new UtilityFunction();
         System.out.println("Get Report");
        List<Object[]> QueryDaytourOtherList = session.createSQLQuery("SELECT * FROM `DaytourOther` where refno = '"+refno+"'")
                .addScalar("refno", Hibernate.STRING)
                .addScalar("createdate", Hibernate.STRING)
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
        for (Object[] B : QueryDaytourOtherList) {
             DaytourOther daytourother = new DaytourOther();
             
             daytourother.setRefno(util.ConvertString(B[0]));
             String parseDate = parseDate(util.ConvertString(B[1]));
             daytourother.setCreatedate(parseDate);
             System.out.println("Date : "+ parseDate);
             daytourother.setLeadername(util.ConvertString(B[2]));
             daytourother.setCode(util.ConvertString(B[3]));
             daytourother.setDescription(util.ConvertString(B[4]));
             daytourother.setDate(util.ConvertString(B[5]));
             daytourother.setTime(util.ConvertString(B[6]));
             daytourother.setAdult(util.ConvertInt(B[7]));
             daytourother.setChild(util.ConvertInt(B[8]));
             daytourother.setInfant(util.ConvertInt(B[9]));
             daytourother.setPassenger(util.ConvertString(B[10]));
             daytourother.setRemark(util.ConvertString(B[11]));
             data.add(daytourother);  
         }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public String parseDate(String date){
        String text="";
        String[] parseDate = date.split("-");
        String day = parseDate[2];
        String month = parseDate[1];
        month = checkMonth(month);
        String year = parseDate[0];
        text = day + " " + month + " " + year;
        return text;
    }
    
    public String checkMonth(String month){
        String mon = "";
        switch (month){
            case "01" : mon = "Jan"; break;
            case "02" : mon = "Feb"; break;
            case "03" : mon = "Mar"; break;
            case "04" : mon = "Apr"; break;
            case "05" : mon = "May"; break;
            case "06" : mon = "Jun"; break;
            case "07" : mon = "Jul"; break;
            case "08" : mon = "Aug"; break;
            case "09" : mon = "Sep"; break;
            case "10" : mon = "Oct"; break;
            case "11" : mon = "Dec"; break;
            case "12" : mon = "Nov"; break;
            default: mon = "null"; 
        }
        return mon;
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
