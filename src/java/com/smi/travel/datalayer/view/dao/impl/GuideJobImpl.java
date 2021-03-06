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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
//        System.out.println("=====query===== :"+"SELECT * FROM `guide_job_info` where tourdate = '"+ String.valueOf(df.format(util.convertStringToDate(tourdate)))+"' and code='"+tourID+"'");
//        "SELECT * FROM `guide_job_info` where tourdate = '"+ String.valueOf(df.format(util.convertStringToDate(tourdate)))+"' and code='"+tourID+"'"
        String sql = " SELECT `tde`.`id` AS `tourdescid`, `st1`.`name` AS `guide`, `dri`.`name` AS `driver`, `dri`.`id` AS `driverid`, `tdr`.`car_no` AS `car_no`, concat(( CASE WHEN ifnull(`tdr`.`gas_value`, 0) THEN format(`tdr`.`gas_value`, 2) ELSE 0 END ), ' : ', `tdr`.`gas_fee` ) AS `gas`, concat(( CASE WHEN ifnull(`tdr`.`tip_value`, 0) THEN format(`tdr`.`tip_value`, 2) ELSE 0 END ), ' : ', `tdr`.`tip_fee` ) AS `tip`, `st1`.`tel` AS `guidetel`, `dri`.`tel` AS `drivertel`, `dt`.`code` AS `code`, `tde`.`tour_date` AS `tourdate`, `tde`.`transfer_info` AS `transferinfo`, `dbp`.`detail` AS `couse`, concat( ifnull(`ini`.`name`, ''), ' ', `cus`.`last_name`, ' ', `cus`.`first_name`, '<br>(', concat( substr(`mt`.`Reference No`, 1, 2), '-', substr(`mt`.`Reference No`, 3, 6)), ')' ) AS `customer`, ( CASE WHEN (`db`.`adult` > 0) THEN `db`.`adult` ELSE '' END ) AS `ad`, ( CASE WHEN (`db`.`child` > 0) THEN `db`.`child` ELSE '' END ) AS `ch`, ( CASE WHEN (`db`.`infant` > 0) THEN `db`.`infant` ELSE '' END ) AS `inf`, ( CASE WHEN (`pl`.`place` = 'OTHERS') THEN `db`.`pickup_detail` ELSE `pl`.`place` END ) AS `place`, `db`.`pickup_room` AS `room`, `db`.`pickup_time` AS `time`, `GET_PRICE_DAYTOUR_BOOK` (`db`.`id`) AS `amount`, `GET_PRICE_COUPON` (`db`.`id`) AS `coupon`, ( CASE WHEN (`db`.`is_pay` = 1) THEN 'PAID' WHEN (`db`.`is_pay` = 0) THEN 'NOT PAID' ELSE '' END ) AS `pay`, `tde`.`meal` AS `meal`, `tde`.`remark` AS `remark`, concat('Remarks : ', `db`.`remark`) AS `book_remark`, `md`.`value` AS `stafftour`, `GET_COUPON_NAME` (`db`.`id`) AS `coup_name` FROM (((((((((((( `tour_operation_desc` `tde` LEFT JOIN `tour_operation_driver` `tdr` ON (( `tdr`.`tour_desc_id` = `tde`.`id` ))) LEFT JOIN `daytour` `dt` ON ((`dt`.`id` = `tde`.`tour_id`))) LEFT JOIN `daytour_booking` `db` ON ((( `db`.`tour_date` = `tde`.`tour_date` ) AND ( `db`.`tour_id` = `tde`.`tour_id` )))) LEFT JOIN `daytour_booking_price` `dbp` ON (( `dbp`.`daytour_booking_id` = `db`.`id` ))) LEFT JOIN `master` `mt` ON ((`mt`.`id` = `db`.`master_id`))) LEFT JOIN `customer` `cus` ON (( `mt`.`FalimyLeader_id` = `cus`.`id` ))) LEFT JOIN `place` `pl` ON ((`pl`.`id` = `db`.`pickup`))) LEFT JOIN `m_initialname` `ini` ON (( `ini`.`id` = `cus`.`initial_name` ))) LEFT JOIN `staff` `st1` ON ((`st1`.`id` = `tde`.`guide_1`))) LEFT JOIN `staff` `st2` ON ((`st2`.`id` = `tde`.`guide_2`))) LEFT JOIN `staff` `dri` ON (( `tdr`.`driver_id` = `dri`.`id` ))) JOIN `m_default_data` `md` ON ((1 = 1))) WHERE (((`mt`.`Status` = 1) OR (`mt`.`Status` = 2)) AND (`db`.`status` = 1)) AND `tde`.`tour_date` = '"+ String.valueOf(df.format(util.convertStringToDate(tourdate)))+"' AND `dt`.`code` = '"+tourID+"' GROUP BY `db`.`id` ORDER BY `tde`.`tour_date`, `tde`.`tour_id`, `db`.`pickup_order` ";
        System.out.println(" sql :: " + sql);
        List<Object[]> QueryGuideJobList = session.createSQLQuery(sql)
                .addScalar("guide", Hibernate.STRING)
                .addScalar("driver", Hibernate.STRING)
                .addScalar("car_no", Hibernate.STRING)
                .addScalar("gas", Hibernate.STRING)
                .addScalar("tip", Hibernate.STRING)
                .addScalar("drivertel", Hibernate.STRING)
                .addScalar("guidetel", Hibernate.STRING)
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
                .addScalar("amount", Hibernate.STRING)
                .addScalar("pay", Hibernate.STRING)
                .addScalar("meal", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("book_remark", Hibernate.STRING)
                .addScalar("stafftour", Hibernate.STRING)
                .addScalar("coupon", Hibernate.STRING)
                .addScalar("coup_name", Hibernate.STRING)
                .addScalar("driverid", Hibernate.STRING)
                .addScalar("tourdescid", Hibernate.STRING)
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
             
             guidejob.setAmount(B[18]== null ? "0.00" : util.ConvertString(B[18]));
             guidejob.setPay(util.ConvertString(B[19]));
             guidejob.setMeal(util.ConvertString(B[20]));
             String driverid = util.ConvertString(B[26]);
             String tourdescid = util.ConvertString(B[27]);
             guidejob.setRemark(getremark(tourdate,tourID,driverid,tourdescid));
             if(B[22] != null){
                String coup = (B[25] != null ? "\n"+util.ConvertString(B[25]) : "");
                if(B[25] != null){
//                    String coupNew = splitIndexCoupon(coup);
                    System.out.println("coup : "+coup);
                    coup = coup.replaceAll("\n", "\nCoupon : ");
                    coup = coup.replaceAll("<br>", "\n");
                    guidejob.setCoup_name(coup);
                }else{
                    guidejob.setCoup_name("");
                }  
                guidejob.setBookremark(util.ConvertString(B[22]));
             }else{
                if(B[25] != null){
                    String coupNew = splitIndexCoupon(util.ConvertString(B[25]));
                    System.out.println("data : "+coupNew);
                    coupNew = coupNew.replaceAll("\n", "\nCoupon : ");
                    guidejob.setCoup_name(coupNew);
                } 
                guidejob.setBookremark("");
             }
             System.out.println("===== Book Remark ===== :"+guidejob.getBookremark());
             guidejob.setStafftour(util.ConvertString(B[23]));
             if(B[24] != null){
                 guidejob.setCoupon("("+util.convertStringToDecimalFormat(util.ConvertString(B[24]))+")");
             }else{
                 guidejob.setCoupon("");
             }
                    
             data.add(guidejob);            
         }
         this.sessionFactory.close();
         session.close();

        return data;
    }
    
    public String splitIndexCoupon(String couponName){
        int indexOf = couponName.lastIndexOf("<br>");
        String coupNew = couponName.substring(indexOf);
        return coupNew;
    }
    
    public String getremark(String tourdate ,String tourid,String driverid,String tourdescid){
        Session session = this.sessionFactory.openSession();
        String remark = "";
        UtilityFunction util = new UtilityFunction();
        SimpleDateFormat df = new SimpleDateFormat();
        boolean driverhaveone = true;
        String tourdescsize = " SELECT * FROM `tour_operation_driver` where tour_desc_id = '"+tourdescid+"' ";
        List<Object[]> QueryTourDescList = session.createSQLQuery(tourdescsize)
                .addScalar("id", Hibernate.STRING)
                .list();
        
        df.applyPattern("yyyy-MM-dd"); 
        String query = "SELECT `tde`.`tour_date` AS `tour_date`, `dt`.`code` AS `tour_id`, group_concat( `st`.`name`, ' Tel:', `st`.`tel`, ' Car No.:', `tdr`.`car_no`, ' Gas:', ifnull(`tdr`.`gas_value`, 0), ':', `tdr`.`gas_fee`, ' Tip:', ifnull(`tdr`.`tip_value`, 0), ':', `tdr`.`tip_fee`, '\\n' SEPARATOR '' ) AS `remark`, ifnull( concat( `gui2`.`name`, ',', 'Tel:', `gui2`.`tel`, '\\n' ), '' ) AS `guide2`, `tde`.`remark` AS `operation_remark` FROM (((( `tour_operation_desc` `tde` LEFT JOIN `tour_operation_driver` `tdr` ON (( `tdr`.`tour_desc_id` = `tde`.`id` ))) LEFT JOIN `staff` `st` ON (( `st`.`id` = `tdr`.`driver_id` ))) LEFT JOIN `staff` `gui2` ON (( `gui2`.`id` = `tde`.`guide_2` ))) LEFT JOIN `daytour` `dt` ON ((`dt`.`id` = `tde`.`tour_id`))) where  tde.tour_date ='"+String.valueOf(df.format(util.convertStringToDate(tourdate)))+"' and dt.code = '"+tourid+"'  ";
        
        if(QueryTourDescList != null && QueryTourDescList.size() > 1){
            driverhaveone = false;
            query += "  and tdr.driver_id <> '"+driverid+"'  ";
        }
        
        query += "  GROUP BY `tde`.`tour_date`, `tde`.`tour_id` ";

//        String query = "SELECT `tde`.`tour_date` AS `tour_date`, `dt`.`code` AS `tour_id`, group_concat( `st`.`name`, ' Tel:', `st`.`tel`, ' Car No.:', `tdr`.`car_no`, ' Gas:', `tdr`.`gas_value`, ':', `tdr`.`gas_fee`, ' Tip:', `tdr`.`tip_value`, ':', `tdr`.`tip_fee`, '\\n' SEPARATOR '' ) AS `remark`, ifnull( concat( `gui2`.`name`, ',', 'Tel:', `gui2`.`tel`, '\\n' ), '' ) AS `guide2`, `tde`.`remark` AS `operation_remark` FROM (((( `tour_operation_desc` `tde` LEFT JOIN `tour_operation_driver` `tdr` ON (( `tdr`.`tour_desc_id` = `tde`.`id` ))) LEFT JOIN `staff` `st` ON (( `st`.`id` = `tdr`.`driver_id` ))) LEFT JOIN `staff` `gui2` ON (( `gui2`.`id` = `tde`.`guide_2` ))) LEFT JOIN `daytour` `dt` ON ((`dt`.`id` = `tde`.`tour_id`))) where  tde.tour_date ='"+String.valueOf(df.format(util.convertStringToDate(tourdate)))+"' and dt.code = '"+tourid+"'  and tdr.driver_id <> '"+driverid+"' GROUP BY `tde`.`tour_date`, `tde`.`tour_id` ";
        System.out.println("query : "+query);
        List<Object[]> QueryRemarkList = session.createSQLQuery(query)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("guide2", Hibernate.STRING)
                .addScalar("operation_remark", Hibernate.STRING)
                .list();
        if(QueryRemarkList.isEmpty()){
            remark = "";
        }else{
            String GuideRemark = "";
            String Guide2Detail = "";
            String OperationRemark = "";
            for (Object[] B : QueryRemarkList) {
                if(!driverhaveone){
                    GuideRemark = util.ConvertString(B[0]);
                }
                Guide2Detail = util.ConvertString(B[1]);  
                OperationRemark  = util.ConvertString(B[2]);  
            }
            System.out.println("===== GuideRemark.indexOf(\"\\n\") ===== : "+GuideRemark.indexOf("\n"));
//            if(GuideRemark.indexOf("\n") != -1){
//                GuideRemark = GuideRemark.substring(GuideRemark.indexOf("\n")+1);
//            }
            if((GuideRemark != null)&&(!"".equalsIgnoreCase(GuideRemark))){
                GuideRemark +=  "\n";
            }
//            if((Guide2Detail != null)&&(!"".equalsIgnoreCase(Guide2Detail))){
//                Guide2Detail +=  "\n";
//            }
            if((OperationRemark != null)&&(!"".equalsIgnoreCase(OperationRemark))){
                OperationRemark +=  "\n";
            }
            
            remark = OperationRemark + Guide2Detail +GuideRemark;
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
