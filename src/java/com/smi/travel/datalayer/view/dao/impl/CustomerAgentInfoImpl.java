/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.CustomerAgentInfoDao;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.util.UtilityFunction;
import java.util.LinkedList;
import java.util.List;
import org.codehaus.groovy.util.StringUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Surachai
 */
public class CustomerAgentInfoImpl implements CustomerAgentInfoDao{
    private SessionFactory sessionFactory;
    private UtilityFunction util;
    @Override
    public List<CustomerAgentInfo> getListCustomerAgentInfo() {
        Session session = this.sessionFactory.openSession();
        util = new UtilityFunction();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `customer_agent_info` ")
                .addScalar("bill_To",Hibernate.STRING)
                .addScalar("bill_Name",Hibernate.STRING)
                .addScalar("tel",Hibernate.STRING)
                .addScalar("address",Hibernate.STRING)
                .addScalar("term",Hibernate.INTEGER)
                .addScalar("pay",Hibernate.INTEGER)
                .addScalar("type",Hibernate.STRING)
                .setMaxResults(100)
                .list();
        
        List<CustomerAgentInfo> CustomerAgentInfoList =  new LinkedList<CustomerAgentInfo>();
        for(Object[] B : QueryList){
            
            CustomerAgentInfo CustomerAgent = new CustomerAgentInfo();
            CustomerAgent.setBillTo(B[0].toString());
            CustomerAgent.setBillName(B[1].toString());
            CustomerAgent.setTel(util.inputString(B[2]));
            CustomerAgent.setAddress(util.inputString(B[3]));
            if(B[4] != null){
                CustomerAgent.setTerm(util.ConvertInt(B[4]));
            }
            if(B[5] != null){
                CustomerAgent.setPay(util.ConvertInt(B[5]));
            }
            CustomerAgent.setType(B[6] != null ? util.inputString(B[6]) : "");
            CustomerAgentInfoList.add(CustomerAgent);
        }
       
        if (CustomerAgentInfoList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return CustomerAgentInfoList;
    }
    
    

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CustomerAgentInfo> SearchListCustomerAgentInfo(String name) {
        Session session = this.sessionFactory.openSession();
        util = new UtilityFunction();
//        String sql ="SELECT * FROM `customer_agent_info` where bill_name like '%"+name+"%' or bill_to like '"+name+"%' limit 200";
        String sql = "SELECT concat( ifnull(concat(`mi`.`name`, ' '), ''), ifnull( concat(`cm`.`last_name`, ' '), '' ), "
                + "ifnull( concat(' ', `cm`.`first_name`), '' )) AS `bill_name`, `cm`.`code` AS `bill_to`, `cm`.`tel` AS `tel`, "
                + "`cm`.`address` AS `address`, NULL AS `fax`, NULL AS `term`, NULL AS `pay`, 'C' AS `type` "
                + "FROM ( `customer` `cm` LEFT JOIN `m_initialname` `mi` ON (( `mi`.`id` = `cm`.`initial_name` ))) "
                + "WHERE concat( ifnull(concat(`mi`.`name`, ' '), ''), ifnull( concat(`cm`.`last_name`, ' '), '' ), ifnull( concat(' ', `cm`.`first_name`), '' )) LIKE '%" + name + "%' "
                + "OR `cm`.`code` LIKE '%" + name + "%' "
                + "UNION ALL "
                + "SELECT `ag`.`name` AS `bill_name`, `ag`.`code` AS `bill_to`, `ag`.`tel` AS `tel`, `ag`.`address` AS `address`, "
                + "`ag`.`fax` AS `fax`, `ag`.`term_id` AS `term`, `ag`.`pay_id` AS `pay`, 'A' AS `agent` "
                + "FROM `agent` `ag` "
                + "WHERE `ag`.`name` LIKE '%" + name + "%' OR `ag`.`code` LIKE '%" + name + "%' "
                + "UNION ALL "
                + "SELECT concat('G.', `st`.`name`) AS `bill_name`, `st`.`ar_code` AS `bill_to`, `st`.`tel` AS `tel`, NULL AS `address`, NULL AS `fax`, "
                + "NULL AS `term`, NULL AS `pay`, 'S' AS `type` "
                + "FROM `staff` `st` "
                + "WHERE (`st`.`position` = 'GUIDE') AND ( concat('G.', `st`.`name`) LIKE '%" + name + "%' OR `st`.`ar_code` LIKE '%" + name + "%' )";
        List<Object[]> QueryList =  session.createSQLQuery(sql)
                .addScalar("bill_To",Hibernate.STRING)
                .addScalar("bill_Name",Hibernate.STRING)
                .addScalar("tel",Hibernate.STRING)
                .addScalar("address",Hibernate.STRING)
                .addScalar("term",Hibernate.INTEGER)
                .addScalar("pay",Hibernate.INTEGER)
                .addScalar("type",Hibernate.STRING)
                .setMaxResults(300)
                .list();
        
        List<CustomerAgentInfo> CustomerAgentInfoList =  new LinkedList<CustomerAgentInfo>();
        for(Object[] B : QueryList){
            CustomerAgentInfo CustomerAgent = new CustomerAgentInfo();
            CustomerAgent.setBillTo(B[0] != null ? B[0].toString() : "");
            CustomerAgent.setBillName(B[1] != null ? B[1].toString() : "");
            CustomerAgent.setTel(B[2] != null ? util.inputString(B[2]) : "");
            CustomerAgent.setAddress(B[3] != null ? util.inputString(B[3]) : "");
            if(B[4] != null){
                CustomerAgent.setTerm(B[4] != null ? util.ConvertInt(B[4]) : null);
            }
            if(B[5] != null){
                CustomerAgent.setPay(B[5] != null ? util.ConvertInt(B[5]) : null);
            }
            CustomerAgent.setType(B[6] != null ? util.inputString(B[6]) : "");
            CustomerAgentInfoList.add(CustomerAgent);
        }
       
        if (CustomerAgentInfoList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return CustomerAgentInfoList;
    }

    @Override
    public List<CustomerAgentInfo> SearchListCustomerAgentInfoReceiveTable(String name) {
        Session session = this.sessionFactory.openSession();
        util = new UtilityFunction();
//        String sql ="SELECT * FROM `customer_agent_info` where bill_name like '%"+name+"%' limit 200";
        String sql = "SELECT concat( ifnull(concat(`mi`.`name`, ' '), ''), ifnull( concat(`cm`.`last_name`, ' '), '' ), "
                + "ifnull( concat(' ', `cm`.`first_name`), '' )) AS `bill_name`, `cm`.`code` AS `bill_to`, `cm`.`tel` AS `tel`, "
                + "`cm`.`address` AS `address`, NULL AS `fax`, NULL AS `term`, NULL AS `pay`, 'C' AS `type` "
                + "FROM ( `customer` `cm` LEFT JOIN `m_initialname` `mi` ON (( `mi`.`id` = `cm`.`initial_name` ))) "
                + "WHERE concat( ifnull(concat(`mi`.`name`, ' '), ''), ifnull( concat(`cm`.`last_name`, ' '), '' ), ifnull( concat(' ', `cm`.`first_name`), '' )) LIKE '%" + name + "%' "
                + "OR `cm`.`code` LIKE '%" + name + "%' "
                + "UNION ALL "
                + "SELECT `ag`.`name` AS `bill_name`, `ag`.`code` AS `bill_to`, `ag`.`tel` AS `tel`, `ag`.`address` AS `address`, "
                + "`ag`.`fax` AS `fax`, `ag`.`term_id` AS `term`, `ag`.`pay_id` AS `pay`, 'A' AS `agent` "
                + "FROM `agent` `ag` "
                + "WHERE `ag`.`name` LIKE '%" + name + "%' OR `ag`.`code` LIKE '%" + name + "%' "
                + "UNION ALL "
                + "SELECT concat('G.', `st`.`name`) AS `bill_name`, `st`.`ar_code` AS `bill_to`, `st`.`tel` AS `tel`, NULL AS `address`, NULL AS `fax`, "
                + "NULL AS `term`, NULL AS `pay`, 'S' AS `type` "
                + "FROM `staff` `st` "
                + "WHERE (`st`.`position` = 'GUIDE') AND ( concat('G.', `st`.`name`) LIKE '%" + name + "%' OR `st`.`ar_code` LIKE '%" + name + "%' )";    
        List<Object[]> QueryList =  session.createSQLQuery(sql)
                .addScalar("bill_To",Hibernate.STRING)
                .addScalar("bill_Name",Hibernate.STRING)
                .addScalar("tel",Hibernate.STRING)
                .addScalar("address",Hibernate.STRING)
                .addScalar("term",Hibernate.INTEGER)
                .addScalar("pay",Hibernate.INTEGER)
                .addScalar("type",Hibernate.STRING)
                .list();
        
        List<CustomerAgentInfo> CustomerAgentInfoList =  new LinkedList<CustomerAgentInfo>();
        for(Object[] B : QueryList){
            CustomerAgentInfo CustomerAgent = new CustomerAgentInfo();
            CustomerAgent.setBillTo(B[0].toString());
            CustomerAgent.setBillName(B[1].toString());
            CustomerAgent.setTel(util.inputString(B[2]));
            CustomerAgent.setAddress(util.inputString(B[3]));
            if(B[4] != null){
                CustomerAgent.setTerm(util.ConvertInt(B[4]));
            }
            if(B[5] != null){
                CustomerAgent.setPay(util.ConvertInt(B[5]));
            }
            CustomerAgent.setType(B[6] != null ? util.inputString(B[6]) : "");
            CustomerAgentInfoList.add(CustomerAgent);
        }
       
        if (CustomerAgentInfoList.isEmpty()) {
            return null;
        }
        session.close();
        this.sessionFactory.close();
        return CustomerAgentInfoList;
    }
    
    
    
}
