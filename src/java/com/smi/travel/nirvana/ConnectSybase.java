/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.nirvana;

import com.smi.travel.migration.MainMigrate;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sybase.jdbcx.*;  
/**
 *
 * @author Jittima
 */
public class ConnectSybase {
    Connection con = null;  
    Statement stmt = null;  
    SybDriver sybDriver = null;  
    private static final String username = "softpack";
    private static final String password = "nirvana";  

    public void makeConnection() throws Exception {  
        sybDriver = (SybDriver) Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();  
        System.out.println("Driver loaded");  
        con = DriverManager.getConnection("jdbc:sybase:Tds:192.168.96.152:2638","softpack", "nirvana");  
        stmt.close();  
    }  
  
    public static void main(String args[]) throws Exception {  
        ConnectSybase sc = new ConnectSybase();  
        sc.makeConnection();  
    }  
}  

//class OracleConnection{
//    private static final String ip = "192.168.99.48";
//    private static final String port = "1521";
//    private static final String schema   = "ORCL";
//    private static final String username = "softpack";
//    private static final String password = "nirvana";
//    
////     static{
////        try {
////            Class.forName("com.sybase.jdbc.SybDriver").newInstance();
////        } catch (ClassNotFoundException ex) {
////            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
////        } catch (InstantiationException ex) {
////            Logger.getLogger(OracleConnection.class.getName()).log(Level.SEVERE, null, ex);
////        } catch (IllegalAccessException ex) {
////            Logger.getLogger(OracleConnection.class.getName()).log(Level.SEVERE, null, ex);
////        } 
////    }
//     
//    public static Connection getConnection(){
//        Connection connect = null;
//        SybDriver sybDriver = null;  
//        // uid - user id
//        // pwd - password
//        // eng - Sybase database server name
//        // database - sybase database name
//        // host - database host machine ip
//        sybDriver = (SybDriver) Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();  
//   System.out.println("Driver loaded");  
//
//        try {   
//            connect = DriverManager.getConnection("jdbc:sqlanywhere:uid="+username+";pwd="+password+";eng=nirvana;database=nirvana;links=tcpip(host=192.168.96.152)");
//
////                    DriverManager.getConnection("jdbc:sqlanywhere:uid="+username+";pwd="+password+";eng=nirvana;database=nirvana;links=tcpip(host=192.168.96.152)");
//            System.out.println("Database Connected.");
//        } catch (SQLException ex) {
//            System.out.println("Database not Connected.");
//        }
//        return connect;
//    }
//}