/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MBankDao;
import com.smi.travel.datalayer.entity.MBank;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class MBankImpl  implements MBankDao{
    
    private SessionFactory sessionFactory;
    private Transaction transaction;
    @Override 
    public List<MBank> getListBank(MBank bank, int option) {
       Session session = this.sessionFactory.openSession();
       String query ="from MBank b where ";
       String queryOperation = "";
       String Prefix_Subfix ="";
       int check =0;
       if(option == 1){
           queryOperation = " = ";
           Prefix_Subfix = "";
       }else if(option == 2){
           queryOperation = " Like ";
           Prefix_Subfix = "%";
       }
       if((bank.getCode() != null) &&(!"".equalsIgnoreCase(bank.getCode()))){
           query += " b.code "+queryOperation+" '"+Prefix_Subfix+bank.getCode()+Prefix_Subfix+"'";
           check =1;
       }
       if((bank.getName()!= null) &&(!"".equalsIgnoreCase(bank.getName()))){
           if(check == 1){query += " and ";}
           query += " b.name "+queryOperation+" '"+Prefix_Subfix+bank.getName()+Prefix_Subfix+"'";
           check =1;
       }
       if(check == 0){
           query = query.replaceAll("where", " ");
       }
       System.out.println("query : "+query );
       List<MBank> list =  session.createQuery(query).list();
       if(list.isEmpty()){
           return null;
       }
       session.close();
       this.sessionFactory.close();
       return list;  
    }

    @Override
    public List<MBank> getListBank() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insertBank(MBank bank) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateBank(MBank bank) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int DeleteBank(MBank bank) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
