/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Customer;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface CustomerDao {
    public List<Customer> getListCustomer();
    public String insertCustomer(Customer customer);
    public String insertCustomerAjax(Customer customer);
    public int updateCustomer(Customer customer);
    public Customer getCustomerFromID(String customerID);
    public boolean isExistCustomer(String initialID,String firstname ,String  lastname);
    public String generateCustomerCode(String FirstCharName);
    public List<Customer> searchCustomer(Customer customer, int option);
    public List<Customer> FiterCustomer(Customer customer, int filter);
}
