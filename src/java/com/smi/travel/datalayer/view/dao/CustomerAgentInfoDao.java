/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface CustomerAgentInfoDao {
    public List<CustomerAgentInfo> getListCustomerAgentInfo();
    public List<CustomerAgentInfo> SearchListCustomerAgentInfo(String name);
    public List<CustomerAgentInfo> SearchListCustomerAgentInfoReceiveTable(String name);
}
