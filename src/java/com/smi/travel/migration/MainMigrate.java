/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Surachai
 */
public class MainMigrate {
    
    public static void main(String[] args){
         Connection connect = null;
         Statement s = null;  
         try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.99.48:1521/ORCL","travox3","oracle");
         s = connect.createStatement();
      
         if (connect != null) {
         System.out.println("Database Connected.");
         String sql = "select * from ACCTSMI3.REPORT_TAX_INVOICE tax where to_char(tax.TAX_DATE,'mm') = '11' " +
            " and to_char(tax.TAX_DATE,'yyyy') = '2014'   order by tax.invoice_type,tax.TAX_NO, tax.TAX_DATE ";
			
//         ResultSet rec = s.executeQuery(sql);
//	 		
//         while((rec!=null) && (rec.next())){
//         id[count] = rec.getString("id");
//         
//         code[count] = rec.getString("code")==null ? "":rec.getString("code");
//         name[count] = rec.getString("name")==null ? "":rec.getString("name");
//         address[count] = rec.getString("address")==null ? "":rec.getString("address");
//                       
//         //                         
//                                
//       //  byte[] b = name.getBytes("TIS-620");
//         code[count] = new String(code[count].getBytes("ISO8859_1"),"TIS-620");
//         address[count] = new String(address[count].getBytes("ISO8859_1"),"TIS-620");
//         name[count] = new String(name[count].getBytes("ISO8859_1"),"TIS-620");
// 
//          //System.out.println(id[count]+","+name[count] +" , "+address[count]);  
//         count++;  
//          //update agent set name='JICA', address='1674/1 NEW PETCHBURI RD,BANGKOK 10320' where id= 7;
//         //       writer.println(id+";"+name+";"+code+";"+address+";"+tel+";"+fax+";"+tax_no+";"+email+";"+remark+";"+warning+";"+branch+";"+branch_no+";"+term_id+";"+pay_id+";"+web+";"+detail);
//         //   writer.println(id+"|"+name+"|"+code+"|"+address+"|"+remark+"|");
//         //    writer.println("update agent set code='"+code+"', name='"+name+"', address='"+address+"', remark='"+remark+"' where id= "+id+";");
//                            
//         // writer.println("The second line");
//            
//         }
//         
//        
//         for(int i=2291;i<2812;i++){
//             System.out.println(i+"  _  "+id[i]+","+code[i] +","+name[i]+","+address[i]); 
//            sql = "update  `temp_agent_2` set code='"+code[i]+"' , name='"+name[i].replaceAll("'", "")+"' , address ='"+address[i].replaceAll("'", "")+"' where id ="+id[i].replaceAll("'", "");
//             System.out.println(sql);
//            int result = s.executeUpdate(sql);
//           // System.out.println(result+"  _  "+id[i]+","+code[i] +","+name[i]+","+address[i]); 
//         }
//                
                
         } else {
         System.out.println("Database Connect Failed.");
         }
     //    writer.close();

         } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }

         // Close
         try {
         if (connect != null) {
         connect.close();
         }
         } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }
    }
    
   
}
