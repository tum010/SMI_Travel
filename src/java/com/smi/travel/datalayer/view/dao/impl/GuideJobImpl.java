/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.GuideJob;
import com.smi.travel.datalayer.view.dao.GuideJobDao;
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
 * @author Surachai
 */
public class GuideJobImpl implements GuideJobDao{

    private SessionFactory sessionFactory;
    
    @Override
    public List getGuildJobReport(String tourdate, String tourID, String username) {
        Session session = this.sessionFactory.openSession();
        List data = new ArrayList();
        Date thisdate = new Date();
        String time = "";
        String pattern = "###,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        UtilityFunction util = new UtilityFunction();
        int no = 0;
        List<Object[]> QueryGuideJobList = session.createSQLQuery("SELECT * FROM `guide_job_info` where tourdate = '"+tourdate+"' and code='"+tourID+"'")
                .addScalar("guide", Hibernate.STRING)
                .addScalar("driver", Hibernate.STRING)
                .addScalar("car_no", Hibernate.STRING)
                .addScalar("gas", Hibernate.STRING)
                .addScalar("tip", Hibernate.STRING)
                .addScalar("guidetel", Hibernate.STRING)
                .addScalar("drivertel", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("tourdate", Hibernate.DATE)
                .addScalar("transferinfo", Hibernate.STRING)
                .addScalar("couse", Hibernate.STRING)
                .addScalar("customer", Hibernate.STRING)
                .addScalar("ad", Hibernate.STRING)
                .addScalar("ch", Hibernate.STRING)
                .addScalar("inf", Hibernate.STRING)
                .addScalar("place", Hibernate.STRING)
                .addScalar("room", Hibernate.STRING)
                .addScalar("time", Hibernate.STRING)
                .addScalar("amount", Hibernate.INTEGER)
                .addScalar("pay", Hibernate.STRING)
                .addScalar("meal", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("book_remark", Hibernate.STRING)
                .addScalar("stafftour", Hibernate.STRING)
                .addScalar("coupon", Hibernate.INTEGER)
                .list();
        
         for (Object[] B : QueryGuideJobList) {
             GuideJob guidejob = new GuideJob();
             no +=1;
             guidejob.setNo(String.valueOf(no));
             guidejob.setUser(username);
             
             guidejob.setGuide(util.ConvertString(B[0]));
             guidejob.setDriver(util.ConvertString(B[1]));
             guidejob.setCarno(util.ConvertString(B[2]));
             guidejob.setGas(util.ConvertString(B[3]));
             guidejob.setTip(util.ConvertString(B[4]));
             guidejob.setDrivertel(util.ConvertString(B[5]));
             guidejob.setGuidetel(util.ConvertString(B[6]));
             guidejob.setCode(util.ConvertString(B[7]));
             guidejob.setTourdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(B[8]));
             guidejob.setTransferinfo(util.ConvertString(B[9]));
             guidejob.setCourse(util.ConvertString(B[10]));
             guidejob.setCustomer(util.ConvertString(B[11]));
             guidejob.setAd(util.ConvertString(B[12]));
             guidejob.setCh(util.ConvertString(B[13]));
             guidejob.setInf(util.ConvertString(B[14]));
             guidejob.setPlace(util.ConvertString(B[15]));
             guidejob.setRoom(util.ConvertString(B[16]));
             if("".equalsIgnoreCase(util.ConvertString(B[17]))){
                 guidejob.setTime("");
             }else{
                 guidejob.setTime(util.ConvertString(B[17]).substring(0, util.ConvertString(B[17]).length()-3));
             }
             
             guidejob.setAmount(B[18]== null ? 0:(Integer)B[18]);
             guidejob.setPay(util.ConvertString(B[19]));
             guidejob.setMeal(util.ConvertString(B[20]));
             guidejob.setRemark(getremark(tourdate,tourID));
             guidejob.setBookremark(util.ConvertString(B[22]));
             guidejob.setStafftour(util.ConvertString(B[23]));
             if(B[24] != null){
                 guidejob.setCoupon(util.ConvertString(decimalFormat.format(B[24])));
             }
             data.add(guidejob);
             
         }
         this.sessionFactory.close();
         session.close();

        return data;
    }
    
    public String getremark(String tourdate ,String tourid){
        Session session = this.sessionFactory.openSession();
        String remark = "";
        UtilityFunction util = new UtilityFunction();
        String query = "SELECT remark,guide2 FROM `guide_job_remark` gr where  gr.tour_date ='"+tourdate+"' and gr.tour_id = '"+tourid+"'";
        System.out.println("query : "+query);
        List<Object[]> QueryRemarkList = session.createSQLQuery(query)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("guide2", Hibernate.STRING)
                .list();
        if(QueryRemarkList.isEmpty()){
            remark = "";
        }else{
            String GuideRemark = "";
            String Guide2Detail = "";
            for (Object[] B : QueryRemarkList) {
                GuideRemark = util.ConvertString(B[0]);
                Guide2Detail = util.ConvertString(B[1]);      
            }
            if(GuideRemark.indexOf("<br>") != -1){
                GuideRemark = GuideRemark.substring(GuideRemark.indexOf("<br>")+4);
            }
            remark = Guide2Detail +GuideRemark;
        }
        return remark;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
