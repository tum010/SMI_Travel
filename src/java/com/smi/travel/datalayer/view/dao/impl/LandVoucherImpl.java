/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.LandVoucher;
import com.smi.travel.datalayer.view.dao.LandVoucherDao;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
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
public class LandVoucherImpl implements  LandVoucherDao{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public LandVoucher getLandVoucher(String refno,String name) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        LandVoucher voucher = new LandVoucher();
        voucher.setCheckin("2014-01-01");
        Date thisDate = new Date();
        voucher.setSystemdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(thisDate));
        voucher.setUser(name);
         List<Object[]> QueryLandList = session.createSQLQuery(" SELECT * FROM `land_voucher_info` where  `land_voucher_info`.ref_no =  " + refno)
                .addScalar("ref_no", Hibernate.STRING)
                .addScalar("Adult", Hibernate.STRING)
                .addScalar("Child", Hibernate.STRING)
                .addScalar("Infant", Hibernate.STRING)
                .addScalar("total_passenger", Hibernate.STRING)
                .addScalar("leader_name", Hibernate.STRING)
                .list();
         for (Object[] B : QueryLandList) {
             voucher.setRefno(util.ConvertString(B[0]));
             voucher.setAdult(util.ConvertString(B[1]));
             voucher.setChild(util.ConvertString(B[2]));
             voucher.setInfant(util.ConvertString(B[3]));
             voucher.setTotal(util.ConvertString(B[4]));
             voucher.setLeadername(util.ConvertString(B[5]));
         }
         List<Object[]> QueryLandCategoryList = session.createSQLQuery(" SELECT * FROM `land_voucher_category` where  `land_voucher_category`.ref_no =  " + refno)   
                .addScalar("category", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .addScalar("inbound_qty", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("address", Hibernate.STRING)
                .addScalar("tel", Hibernate.STRING)
                .addScalar("fax", Hibernate.STRING)
                .addScalar("okby", Hibernate.STRING)
                .list();
         
       
         
         for(int i=0;i<QueryLandCategoryList.size();i++){
             Object[] Category = QueryLandCategoryList.get(i);
             voucher.setName(util.ConvertString(Category[3]));
             voucher.setAddress(util.ConvertString(Category[4]));
             voucher.setTel(util.ConvertString(Category[5]));
             voucher.setFax(util.ConvertString(Category[6])); 
             voucher.setOkby(util.ConvertString(Category[7]));
             if(i == 0){
                 voucher.setCategory(util.ConvertString(Category[0]));
                 voucher.setDescription(util.ConvertString(Category[1]));
                 voucher.setQty(util.ConvertString(Category[2]));
             }else if(i == 1){
                 voucher.setCategory1(util.ConvertString(Category[0]));
                 voucher.setDescription1(util.ConvertString(Category[1]));
                 voucher.setQty1(util.ConvertString(Category[2]));
             }else if(i == 2){
                 voucher.setCategory2(util.ConvertString(Category[0]));
                 voucher.setDescription2(util.ConvertString(Category[1]));
                 voucher.setQty2(util.ConvertString(Category[2]));
             }
         }
         
         List<String> QueryLandPassengerList = session.createSQLQuery(" SELECT * FROM `land_voucher_passenger` where  `land_voucher_passenger`.ref_no =  " + refno)   
                .addScalar("leader_name", Hibernate.STRING)
                .list();
         for(int i=0;i<QueryLandPassengerList.size();i++){
             String passname = QueryLandPassengerList.get(i);
              if(i == 0){
                  voucher.setPassenger1(util.CheckNullString(passname));
              }else if(i == 1){
                  voucher.setPassenger2(util.CheckNullString(passname));
              }else if(i == 2){
                  voucher.setPassenger3(util.CheckNullString(passname));
              }else if(i == 3){
                  voucher.setPassenger4(util.CheckNullString(passname));
              }else if(i == 4){
                  voucher.setPassenger5(util.CheckNullString(passname));
              }else if(i == 5){
                  voucher.setPassenger6(util.CheckNullString(passname));
              }
         }
        session.close();
        this.sessionFactory.close();
        return voucher;
    }
    
}
