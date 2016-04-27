/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.HotelBooking;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.view.dao.BillableViewDao;
import com.smi.travel.datalayer.view.entity.BillableView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Surachai
 */
public class BillableViewImpl implements BillableViewDao{
private SessionFactory sessionFactory;
    
    @Override
    public List<BillableView> getListBillableView(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `billable_view` where `billable_view`.ref_no =  "+refno)
                .addScalar("bill_type_id",Hibernate.INTEGER)
                .addScalar("bill_type",Hibernate.STRING)
                .addScalar("cost",Hibernate.STRING)
                .addScalar("price",Hibernate.STRING)
                .addScalar("ref_no",Hibernate.STRING)
                .addScalar("detail",Hibernate.STRING)
                .addScalar("cur_amount",Hibernate.STRING)
                .addScalar("id",Hibernate.STRING)
                .addScalar("cur_cost",Hibernate.STRING)
                .list();
        
               
        List<BillableView> BillableList =  new LinkedList<BillableView>();
        for(Object[] B : QueryList){
            BillableView bill = new BillableView();
            bill.setBillID((B[0].toString()));
            bill.setBilltype(B[1].toString());
            bill.setCost(B[2] != null ? util.ConvertString(B[2]) : "0.00");
            bill.setPrice(B[3] != null ? util.ConvertString(B[3]) : "0.00");
            bill.setRefno(B[4].toString());
            bill.setDetail(B[5].toString());
            bill.setCurAmount(String.valueOf(B[6]));  
            bill.setCurCost(String.valueOf(B[8]));
            bill.setId(B[7].toString());       
            BillableList.add(bill);  
        }
       
        if (BillableList.isEmpty()) {
            return null;
        }
        session.close();
        return BillableList;
    }
    
    @Override
    public List<BillableDesc> MappingObjectBillable(List<BillableView> ListBill) {
        List<BillableDesc> BillDesc = new LinkedList<BillableDesc>();
        if (ListBill == null) {
            return null;
        }
        for(BillableView B : ListBill){
            BillableDesc Billdata = new BillableDesc();
            MBilltype billtype = new MBilltype();
            if(B.getBilltype().equalsIgnoreCase("Air Ticket")){
              //  System.out.println("set Airline id : "+ B.getId());
               // Billdata.setRefItemId(B.getId());
            }else{
                System.out.println("set  id : "+ B.getId());
                Billdata.setRefItemId(B.getId());
            }
            billtype.setName(B.getBilltype());
            billtype.setId(B.getBillID());
            Billdata.setMBilltype(billtype);
            Billdata.setCost(new BigDecimal(B.getCost()));
            Billdata.setPrice(new BigDecimal(B.getPrice()));
            Billdata.setDetail(B.getDetail());
            Billdata.setCurrency(B.getCurAmount());
            Billdata.setCurCost(B.getCurCost());
            BillDesc.add(Billdata);
        }
        return BillDesc;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
}
