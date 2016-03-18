/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.nirvana;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.sybase.jdbcx.*;  
/**
 *
 * @author Jittima
 */
public class ConnectSybase {
    private static final String ip = "192.168.96.152";
    private static final String port = "2638";
    private static final String username = "softpack";
    private static final String password = "nirvana";
    private static final String url = "jdbc:sybase:Tds:"+ip+":"+port; 
    Connection con = null;  
    Statement stmt = null;  
    SybDriver sybDriver = null; 
    
    public static void main(String args[]) throws Exception {  
        ConnectSybase cs = new ConnectSybase();  
        cs.makeConnection();  
    }
    
    public void makeConnection() throws Exception {  
        sybDriver = (SybDriver) Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();  
        con = DriverManager.getConnection(url,username, password);  
        if(con != null){
            System.out.println(" sybase connected ");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Currency");
            while (rs.next()) {    
                System.out.println("Active ::  " + rs.getString("CurrencyID") == null ? " null " : rs.getString("CurrencyID"));
            }
        }
        stmt.close();
        con.close();  
    }  
}  
