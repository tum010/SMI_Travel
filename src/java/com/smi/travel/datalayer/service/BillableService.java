/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.BillableDao;
import com.smi.travel.datalayer.entity.Billable;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.view.dao.BillableViewDao;
import com.smi.travel.datalayer.view.dao.CustomerAgentInfoDao;
import com.smi.travel.datalayer.view.entity.BillableView;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class BillableService {
    private BillableDao billableDao;
    private CustomerAgentInfoDao customerAgentInfodao;
    private BillableViewDao billableviewdao;
    
    public Billable getBillableBooking(String refno) {
        return billableDao.getBillableBooking(refno);
    }
    
    public List<BillableDesc>  getListBillableNopay(String refno){
        List<BillableView> billListnopay = billableviewdao.getListBillableView(refno);
        return billableviewdao.MappingObjectBillable(billListnopay);
    }
    
    public int saveBillable(Billable bill){
        int result =0;
        if(bill.getId() == null){
            result =billableDao.insertBillableBooking(bill);
        }else{
            result =billableDao.updateBillableBooking(bill);
        }
        return result;
    }
    
    public String DeleteBillableDesc(String billdescId){
        return billableDao.DeleteBillableDesc(billdescId);
    }
    
    public List<CustomerAgentInfo> getListCustomerAgentInfo() {
        return customerAgentInfodao.getListCustomerAgentInfo();
    }

    public BillableDao getBillableDao() {
        return billableDao;
    }

    public void setBillableDao(BillableDao billableDao) {
        this.billableDao = billableDao;
    }

    public CustomerAgentInfoDao getCustomerAgentInfodao() {
        return customerAgentInfodao;
    }

    public void setCustomerAgentInfodao(CustomerAgentInfoDao customerAgentInfodao) {
        this.customerAgentInfodao = customerAgentInfodao;
    }

    public BillableViewDao getBillableviewdao() {
        return billableviewdao;
    }

    public void setBillableviewdao(BillableViewDao billableviewdao) {
        this.billableviewdao = billableviewdao;
    }

    public String printTicketOrder(String refNo) {
        return billableDao.printTicketOrder(refNo);
    }

    public List<CustomerAgentInfo> SearchListCustomerAgentInfo(String billTo) {
        return customerAgentInfodao.SearchListCustomerAgentInfo(billTo);
    }

    public String searchBillableType(Master master) {
        return billableDao.searchBillableType(master);
    }
            
}
