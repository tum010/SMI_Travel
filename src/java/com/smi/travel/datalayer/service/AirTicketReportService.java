/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.view.dao.CustomerAgentInfoDao;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class AirTicketReportService {
    private CustomerAgentInfoDao customerAgentInfodao;
    
    public List<CustomerAgentInfo> getListCustomerAgentInfo() {
        return customerAgentInfodao.getListCustomerAgentInfo();
    }

    public CustomerAgentInfoDao getCustomerAgentInfodao() {
        return customerAgentInfodao;
    }

    public void setCustomerAgentInfodao(CustomerAgentInfoDao customerAgentInfodao) {
        this.customerAgentInfodao = customerAgentInfodao;
    }
    
    
}
