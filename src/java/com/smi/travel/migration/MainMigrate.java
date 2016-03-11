/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.migration;

import com.smi.travel.controller.excel.master.UtilityExcelFunction;
import com.smi.travel.datalayer.entity.MCity;
import com.smi.travel.datalayer.entity.MCountry;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.MProductType;
import com.smi.travel.util.UtilityFunction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

/**
 *
 * @author Surachai
 */
public class MainMigrate {
    
    private static final String TaxInvoiceReport = " SELECT SALE.*, agt.tax_no AS TAXNO, agt. BRANCH, agt.branch_no FROM ACCTSMI3.REPORT_TAX_INVOICE sale INNER JOIN \"TRAVOX3\".\"AC_TAX_INVOICE\" tax ON tax.\"ID\" = sale.TAX_ID LEFT JOIN TRAVOX3.AGENT AGT ON AGT.code = tax.TAX_INV_TO AND AGT.\"NAME\" = TAX.TAX_INV_NAME WHERE TO_CHAR (sale.TAX_DATE, 'mm') = '11' AND TO_CHAR (sale.TAX_DATE, 'yyyy') = '2014' ORDER BY sale.invoice_type, sale.TAX_NO, sale.TAX_DATE ";
    private static final String AgentReport = " SELECT * FROM TRAVOX3.AGENT ";
    private static final String StaffReport = " SELECT * FROM TRAVOX3.STAFF ";
    private static final String ExportFilePath = "C:\\Users\\Jittima\\Desktop\\ExcelFile\\";
    
    private static final String sqlAirline = " SELECT * FROM TRAVOX3.AIRLINE ";
    private static final String sqlProduct = " SELECT * FROM TRAVOX3.PRODUCT ";
    private static final String sqlPackageTour = " SELECT * FROM TRAVOX3.PACKAGE_TOUR ";
    private static final String sqlHotel = " SELECT * FROM TRAVOX3.HOTEL ";
    private static final String sqlCurrency = " SELECT * FROM TRAVOX3.CURRENCY ";
    private static final String sqlCountry = " SELECT * FROM TRAVOX3.COUNTRY ";
    private static final String sqlCity = " SELECT * FROM TRAVOX3.CITY ";
    private static final String sqlCustomer = " SELECT * FROM TRAVOX3.CUSTOMER ";
    
    public static void main(String[] args) {
        Connection connect = null;
        Statement s = null;  
        Statement stmt = null;
        try {  
            connect = OracleConnection.getConnection();
            s = connect.createStatement();
            if (connect != null) {
//                getTaxInvoice(s,stmt);
//                getAgentReport(s, stmt);
//                getStaffReport(s, stmt);
//                getCity(s, stmt);
//                getCountry(s,stmt);
//                getCurrency(s, stmt);
//                getAirline(s, stmt);
//                getPackageTour(s, stmt);
//                getProduct(s, stmt);
//                getHotel(s, stmt);
                getCustomer(s, stmt);
            } else {
                System.out.println("Database Connect Failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           if (connect != null) {
               try {
                   connect.close();
               } catch (SQLException ex) {
                   Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        } 
    }
    
    public static void getCustomer(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        UtilityFunction util = new UtilityFunction();
        try {
            ResultSet rs = s.executeQuery(sqlCustomer);
          
            while (rs.next()) {       
                String code = rs.getString("CODE") == null ? "" : rs.getString("CODE");
                String initialname = rs.getString("INITIAL_NAME") == null ? "" : new String(rs.getString("INITIAL_NAME").getBytes("ISO8859_1"),"TIS-620");
                String firstName = rs.getString("FIRST_NAME") == null ? "" : new String(rs.getString("FIRST_NAME").getBytes("ISO8859_1"),"TIS-620");
                String lastName = rs.getString("LAST_NAME") == null ? "" : new String(rs.getString("LAST_NAME").getBytes("ISO8859_1"),"TIS-620");
                String nationality = rs.getString("NATIONALITY") == null ? "" : new String(rs.getString("NATIONALITY").getBytes("ISO8859_1"),"TIS-620");
                String birthDate = rs.getString("BIRTH") == null ? "" : rs.getString("BIRTH");
                String age = rs.getString("AGE") == null ? null : rs.getString("AGE");
                String agemonth = rs.getString("AGE_MONTH") == null ? null : rs.getString("AGE_MONTH");
                String sex = rs.getString("SEX") == null ? "" : new String(rs.getString("SEX").getBytes("ISO8859_1"),"TIS-620");
                String formalAddress = rs.getString("FORMAL_ADDRESS") == null ? "" : new String(rs.getString("FORMAL_ADDRESS").getBytes("ISO8859_1"),"TIS-620");
                String formalTel = rs.getString("FORMAL_TEL") == null ? "" : new String(rs.getString("FORMAL_TEL").getBytes("ISO8859_1"),"TIS-620");
                String formalFax = rs.getString("FORMAL_FAX") == null ? "" : rs.getString("FORMAL_FAX");
                String formalEmail = rs.getString("FORMAL_EMAIL") == null ? "" : rs.getString("FORMAL_EMAIL");
                String postalAddress = rs.getString("POSTAL_ADDRESS") == null ? "" : new String(rs.getString("POSTAL_ADDRESS").getBytes("ISO8859_1"),"TIS-620");
                String postalTel = rs.getString("POSTAL_TEL") == null ? "" : new String(rs.getString("POSTAL_TEL").getBytes("ISO8859_1"),"TIS-620");
                String postalFax = rs.getString("POSTAL_FAX") == null ? "" : rs.getString("POSTAL_FAX");
                String postalEmail = rs.getString("POSTAL_EMAIL") == null ? "" : rs.getString("POSTAL_EMAIL");
                String idno = rs.getString("ID_NO") == null ? "" : rs.getString("ID_NO");
                String height = rs.getString("HEIGHT") == null ? "" : rs.getString("HEIGHT");
                String plaseBirth = rs.getString("PLASE_OF_BIRTH") == null ? "" : rs.getString("PLASE_OF_BIRTH");
                String dateIssue = rs.getString("DATE_OF_ISSUE") == null ? "" : rs.getString("DATE_OF_ISSUE");
                String passportType = rs.getString("PASSPORT_TYPE") == null ? "" : rs.getString("PASSPORT_TYPE");
                String countryCode = rs.getString("COUNTRY_CODE") == null ? "" : rs.getString("COUNTRY_CODE");
                String passportNo = rs.getString("PASSPORT_NO") == null ? "" : rs.getString("PASSPORT_NO");
                String adAccept = rs.getString("AD_ACCEPT") == null ? "" : rs.getString("AD_ACCEPT");
                String warning = rs.getString("WARNING") == null ? "" : rs.getString("WARNING");
                String status = rs.getString("STATUS") == null ? "" : rs.getString("STATUS");
                String customerType = rs.getString("CUSTOMER_TYPE") == null ? "" : rs.getString("CUSTOMER_TYPE");
                String citizenNo = rs.getString("CITIZEN_NO") == null ? "" : rs.getString("CITIZEN_NO");
                String postalCode = rs.getString("POSTAL_CODE") == null ? "" : rs.getString("POSTAL_CODE");
                String mobileNo = rs.getString("MOBILE_NO") == null ? "" : rs.getString("MOBILE_NO");
                String webmemberNo = rs.getString("WEBMEMBER_NO") == null ? "" : rs.getString("WEBMEMBER_NO");
                String wendywebNo = rs.getString("WENDYWEB_NO") == null ? "" : rs.getString("WENDYWEB_NO");
                String firstNameJapan = rs.getString("FIRST_NAME_JAPAN") == null ? "" : new String(rs.getString("FIRST_NAME_JAPAN").getBytes("ISO8859_1"),"TIS-620");
                String lastNameJapan= rs.getString("LAST_NAME_JAPAN") == null ? "" : new String(rs.getString("LAST_NAME_JAPAN").getBytes("ISO8859_1"),"TIS-620");

                MainMigrateModel migrateModel = new MainMigrateModel();
                migrateModel.setCode(code);
                String initial = "";
                if("-43".equalsIgnoreCase(initialname)){
                    initial = "1";
                }else if("-44".equalsIgnoreCase(initialname)){
                    initial = "2";
                }else if("-45".equalsIgnoreCase(initialname)){
                    initial = "3";
                }else if("-46".equalsIgnoreCase(initialname)){
                    initial = "4";
                }else if("-47".equalsIgnoreCase(initialname)){
                    initial = "5";
                }else if("-48".equalsIgnoreCase(initialname)){
                    initial = "6";
                }
                MInitialname mInitialname = new MInitialname();
                mInitialname.setId(initial);
                migrateModel.setInitialname(mInitialname);
                migrateModel.setFirstName(firstName);
                migrateModel.setLastName(lastName);
                migrateModel.setNationality(nationality);
                migrateModel.setBirthDate("".equalsIgnoreCase(birthDate) ? null : util.convertStringToDate(birthDate));
                migrateModel.setAge(age);
                migrateModel.setAgemonth(agemonth);
                migrateModel.setSex(sex);
                migrateModel.setFormalAddress(formalAddress);
                migrateModel.setFormalTel(formalTel);
                migrateModel.setFormalFax(formalFax);
                migrateModel.setFormalEmail(formalEmail);
                migrateModel.setPostalAddress(postalAddress);
                migrateModel.setPostalTel(postalTel);
                migrateModel.setPostalFax(postalFax);
                migrateModel.setPostalEmail(postalEmail);
                migrateModel.setIdno(idno);
                migrateModel.setHeight(height);
                migrateModel.setPlaseBirth(plaseBirth);
                migrateModel.setDateIssue("".equalsIgnoreCase(dateIssue) ? null :util.convertStringToDate(dateIssue));
                migrateModel.setPassportType(passportType);
//                MCountry mCountry = new MCountry();
//                mCountry.setCode(countryCode);
                migrateModel.setCountryCode(countryCode);
                migrateModel.setPassportNo(passportNo);
                migrateModel.setAdAccept(adAccept);
                migrateModel.setWarning(warning);
                migrateModel.setStatus(status);
                migrateModel.setCustomerType(customerType);
                migrateModel.setCitizenNo(citizenNo);
                migrateModel.setPostalCode(postalCode);
                migrateModel.setMobileNo(mobileNo);
                migrateModel.setWebmemberNo(webmemberNo);
                migrateModel.setWendywebNo(wendywebNo);
                migrateModel.setFirstNameJapan(firstNameJapan);
                migrateModel.setLastNameJapan(lastNameJapan);
                list.add(migrateModel);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            
            System.out.println(" list.size() "+ list.size());
            for(int i = 0 ; i< list.size(); i ++){ 
                String country = null;
                if(list.get(i).getCountry() != null && !"".equalsIgnoreCase(list.get(i).getCountry().getCode())){
                    country = " (select id from m_country where `code` ='"+list.get(i).getCountry().getCode()+"') ";
                }
                sql = " INSERT INTO  `customer` ( `code`, `initial_name`, `first_name`, `last_name`, `nationality`, `birth_date`, `age`, `age_month`, `sex`, `formal_address`, `formal_tel`, `formal_fax`, `formal_email`, `address`, `tel`, `fax`, `email`, `id_no`, `height`, `plase_of_birth`, `date_of_issue`, `passport_type`, `country_code`, `passport_no`, `ad_accept`, `remark`, `status`, `customer_type`, `personal_id`, `postal_code`, `phone`, `web_member_no`, `wendy_web_no`, `first_name_japan`, `last_name_japan` )  "
                    + "VALUES ('"+list.get(i).getCode()+"','"
                    + list.get(i).getInitialname().getId() +"','"
                    + list.get(i).getFirstName()+"','"
                    + list.get(i).getLastName()+"','"
                    + list.get(i).getNationality() +"',"
                    + list.get(i).getBirthDate() +","
                    + list.get(i).getAge() +","
                    + list.get(i).getAgemonth() +",'"
                    + list.get(i).getSex() +"','"
                    + list.get(i).getFormalAddress() +"','"
                    + list.get(i).getFormalTel() +"','"
                    + list.get(i).getFormalFax() +"','"
                    + list.get(i).getFormalEmail() +"','"
                    + list.get(i).getPostalAddress() +"','"
                    + list.get(i).getPostalTel() +"','"
                    + list.get(i).getPostalFax() +"','"
                    + list.get(i).getPostalEmail() +"','"
                    + list.get(i).getIdno() +"','"
                    + list.get(i).getHeight() +"','"
                    + list.get(i).getPlaseBirth() +"',"
                    + list.get(i).getDateIssue() +",'"
                    + list.get(i).getPassportType() +"','"
                    + list.get(i).getCountryCode() +"' ,'"
                    + list.get(i).getPassportNo() +"','"
                    + list.get(i).getAdAccept() +"','"
                    + list.get(i).getWarning() +"','"
                    + list.get(i).getStatus() +"','"
                    + list.get(i).getCustomerType() +"','"
                    + list.get(i).getCitizenNo() +"','"
                    + list.get(i).getPostalCode() +"','"
                    + list.get(i).getMobileNo() +"','"
                    + list.get(i).getWebmemberNo() +"','"
                    + list.get(i).getWendywebNo() +"','"
                    + list.get(i).getFirstNameJapan() +"','"
                    + list.get(i).getLastNameJapan()+"' ) ";
                System.out.println(" sql "+ sql);
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getHotel(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        try {
            ResultSet rs = s.executeQuery(sqlHotel);
            while (rs.next()) {            
                String code = rs.getString("CODE") == null ? "" : rs.getString("CODE");
                String name = rs.getString("NAME") == null ? "" : new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");
                String description = rs.getString("DESCRIPTION") == null ? "" : new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                String address = rs.getString("ADDRESS") == null ? "" : new String(rs.getString("ADDRESS").getBytes("ISO8859_1"),"TIS-620");
                String tel = rs.getString("TEL") == null ? "" : new String(rs.getString("TEL").getBytes("ISO8859_1"),"TIS-620");
                String fax = rs.getString("FAX") == null ? "" :rs.getString("FAX");
                String email = rs.getString("EMAIL") == null ? "" :rs.getString("EMAIL");
                String web = rs.getString("WEB") == null ? "" :rs.getString("WEB");
                String countrycode = rs.getString("COUNTRY_CODE") == null ? "" :rs.getString("COUNTRY_CODE");
                String citycode = rs.getString("CITY_CODE") == null ? "" :rs.getString("CITY_CODE");
                String updatehotel = rs.getString("UPDATE_HOTEL") == null ? "" :rs.getString("UPDATE_HOTEL");
                String refid = rs.getString("REF_ID") == null ? "" :rs.getString("REF_ID");
                
                MainMigrateModel hotel = new MainMigrateModel();
                hotel.setCode(code);
                hotel.setName(name);
                hotel.setRemark(description);
                hotel.setAddress(address);
                hotel.setTelNo(tel);
                hotel.setFax(fax);
                hotel.setEmail(email);
                hotel.setWeb(web);
                MCountry mCountry = new MCountry();
                mCountry.setCode(countrycode);
                hotel.setCountry(mCountry);
                MCity mCity = new MCity();
                mCity.setCode(citycode);
                hotel.setCity(mCity);
                hotel.setUpdateHotel(updatehotel);
                hotel.setRefId(refid);
                list.add(hotel);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            
            System.out.println(" list.size() "+ list.size());
            for(int i = 0 ; i< list.size() ; i ++){ 
                String city = null;
                String country = null;
                if(list.get(i).getCountry() != null && !"".equalsIgnoreCase(list.get(i).getCountry().getCode())){
                    country = " (select id from m_country where `code` ='"+list.get(i).getCountry().getCode()+"') ";
                }
                if(list.get(i).getCity() != null && !"".equalsIgnoreCase(list.get(i).getCity().getCode())){
                    city = " (select id from m_city where `code`='"+list.get(i).getCity().getCode()+"') ";
                }
                sql = " INSERT INTO  `hotel` (`code`,`name`,`remark`,`address`,`tel_no`,`fax`,`email`,`web`,`country`,`city`,`update_hotel`,`ref_id`)  "
                    + "VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getName().replaceAll("'", " ")+"','"+list.get(i).getRemark().replaceAll("'", " ")+"','"
                    + list.get(i).getAddress().replaceAll("'", " ")+"','"+list.get(i).getTelNo()+"','"+list.get(i).getFax()+"','"+list.get(i).getEmail()+"','"
                    + list.get(i).getWeb()+"',"+country+" , " +city+ " ,'"+list.get(i).getUpdateHotel()+"','"+list.get(i).getRefId()+"' ) ";
                System.out.println(" sql "+ sql);
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getProduct(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        try {
            ResultSet rs = s.executeQuery(sqlProduct);
            while (rs.next()) {
                String code = rs.getString("CODE") == null ? "" : rs.getString("CODE");
                String name = rs.getString("NAME") == null ? "" : new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");
                String description = rs.getString("DESCRIPTION") == null ? "" : new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                String listitemid = rs.getString("LIST_ITEM_ID") == null ? "" :rs.getString("LIST_ITEM_ID");
                String cost = rs.getString("COST") == null ? "0.00" : rs.getString("COST");
                String condition = rs.getString("CONDITION") == null ? "" :rs.getString("CONDITION");
                String include = rs.getString("INCLUDE") == null ? "" :rs.getString("INCLUDE");
                String instruction = rs.getString("INSTRUCTION") == null ? "" :rs.getString("INSTRUCTION");
                String remarks = rs.getString("REMARKS") == null ? "" :rs.getString("REMARKS");
                String update = rs.getString("UPDATE_Y_N") == null ? "" :rs.getString("UPDATE_Y_N");
                String productype = rs.getString("PRODUCT_TYPE") == null ? null :rs.getString("PRODUCT_TYPE");

                MainMigrateModel product = new MainMigrateModel();
                product.setCode(code);
                product.setName(name);
                product.setDescription(description);
                product.setListItemId(listitemid);
                product.setCost(new BigDecimal(cost));
                product.setCondition(condition);
                product.setInclude(include);
                product.setInstruction(instruction);
                product.setRemark(remarks);
                product.setIsUpdate(update);
                MProductType mProductType = new MProductType();
                mProductType.setId(productype);
                product.setProductType(mProductType);
                list.add(product);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" list.size() "+ list.size());
            for(int i = 0 ; i< list.size() ; i ++){               
                sql = " INSERT INTO `product` (`code`,`name`,`description`,`list_item_id`,`cost`,`condition`,`include`,`instruction`,`remark`,`is_update`,`product_type`) "
                        + "VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getName().replaceAll("'", " ")+"','"+list.get(i).getDescription().replaceAll("'", " ")+"','"
                        + list.get(i).getListItemId()+"','"+list.get(i).getCost()+"','"+list.get(i).getCondition().replaceAll("'", " ")+"','"+list.get(i).getInclude().replaceAll("'", " ")+"','"+list.get(i).getInstruction().replaceAll("'", " ")+"','"+list.get(i).getRemark().replaceAll("'", " ")+"','"+list.get(i).getIsUpdate()+"',"+list.get(i).getProductType().getId()+") ";
                System.out.println(" sql "+ sql);
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getPackageTour(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        try {
            ResultSet rs = s.executeQuery(sqlPackageTour);
            while (rs.next()) {
                String code = rs.getString("CODE") == null ? "" : rs.getString("CODE");
                String name = rs.getString("NAME") == null ? "" : new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");
                String serial = rs.getString("SERIAL") == null ? "" :rs.getString("SERIAL");
                String guide = rs.getString("GUIDE_STAFF_ID") == null ? "" :rs.getString("GUIDE_STAFF_ID");
                String paxmin = rs.getString("PAX_MIN") == null ? "0" :rs.getString("PAX_MIN");
                String paxmax = rs.getString("PAX_MAX") == null ? "0" :rs.getString("PAX_MAX");
                String remarks = rs.getString("REMARKS") == null ? "" :rs.getString("REMARKS");
                String status = rs.getString("STATUS") == null ? "" :rs.getString("STATUS");
                MainMigrateModel packageTour = new MainMigrateModel();
                packageTour.setCode(code);
                packageTour.setName(name);
                packageTour.setSerial(serial);
                packageTour.setGuideStaffId(guide);
                packageTour.setPaxMax(Integer.parseInt(paxmax));
                packageTour.setPaxMin(Integer.parseInt(paxmin));
                packageTour.setStatus(status);
                packageTour.setRemark(remarks);
                list.add(packageTour);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" list.size() "+ list.size());
            for(int i = 0 ; i< list.size() ; i ++){
                sql = " INSERT INTO `package_tour` (`code`,`name`,`serial`,`guide_staff_id`,`pax_min`,`pax_max`,`remark`,`status`) "
                        + "VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getName().replaceAll("'", " ")+"','"+list.get(i).getSerial()+"','"
                        + list.get(i).getGuideStaffId()+"','"+list.get(i).getPaxMin()+"','"+list.get(i).getPaxMax()+"','"+list.get(i).getRemark()+"','"+list.get(i).getStatus()+"') ";
                System.out.println(" sql "+ sql);
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getAirline(Statement s,Statement stmt){
        List<MainMigrateModel> list = new ArrayList<MainMigrateModel>();
        try {
            ResultSet rs = s.executeQuery(sqlAirline);
            while (rs.next()) {
                String code = rs.getString("CODE") == null ? "" : rs.getString("CODE");
                String description = rs.getString("DESCRIPTION") == null ? "" : new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                String code3 = rs.getString("CODE_3") == null ? "" :rs.getString("CODE_3");
                String arcode = rs.getString("AR_CODE") == null ? "" :rs.getString("AR_CODE");
                MainMigrateModel mAirline = new MainMigrateModel();
                mAirline.setCode(code);
                mAirline.setName(description);
                mAirline.setCode3Letter(code3);
                mAirline.setArcode(arcode);
                list.add(mAirline);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" list.size() "+ list.size());
            for(int i = 0 ; i< list.size() ; i ++){
                if(list.get(i).getCode().length() > 5 ){
                    sql = " INSERT INTO `m_airline` (`code`,`name`,`code_3_letter`,`ar_code`) VALUES ('"+list.get(i).getCode().substring(0,5)+"','"+list.get(i).getName().replaceAll("'", " ")+"','"+list.get(i).getCode3Letter().replaceAll("'", " ")+"','"+list.get(i).getArcode().replaceAll("'", " ")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_airline` (`code`,`name`,`code_3_letter`,`ar_code`) VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getName().replaceAll("'", " ")+"','"+list.get(i).getCode3Letter().replaceAll("'", " ")+"','"+list.get(i).getArcode().replaceAll("'", " ")+"'); " ;
                }
                System.out.println(" sql "+ sql);
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getCurrency(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List<MCurrency> list = new ArrayList<MCurrency>();
        try {
            ResultSet rs = s.executeQuery(sqlCurrency);
            while (rs.next()) {
                String code = rs.getString("CODE");
                String description = new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                MCurrency mCurrency = new MCurrency();
                mCurrency.setCode(code);
                mCurrency.setDescription(description);
                list.add(mCurrency);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(list != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" list.size() "+ list.size());
            for(int i = 0 ; i< list.size() ; i ++){
                if(list.get(i).getCode().length() > 3 ){
                    sql = " INSERT INTO `m_currency` (`CODE`,`DESCRIPTION`) VALUES ('"+list.get(i).getCode().substring(0,3)+"','"+list.get(i).getDescription().replaceAll("'", " ")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_currency` (`CODE`,`DESCRIPTION`) VALUES ('"+list.get(i).getCode()+"','"+list.get(i).getDescription().replaceAll("'", " ")+"'); " ;
                }
                System.out.println(" sql "+ sql);
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getCountry(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List<MCountry> mCountrys = new ArrayList<MCountry>();
        try {
            ResultSet rs = s.executeQuery(sqlCountry);
            while (rs.next()) {
                String code = rs.getString("CODE");
                String description = new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                MCountry mCountry = new MCountry();
                mCountry.setCode(code);
                mCountry.setName(description);
                mCountrys.add(mCountry);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        Connection connect = null;
        Statement stm = null; 
        if(mCountrys != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" mCitys.size() "+ mCountrys.size());
            for(int i = 0 ; i< mCountrys.size() ; i ++){
                if(mCountrys.get(i).getCode().length() > 3 ){
                    sql = " INSERT INTO `m_country` (`code`,`name`) VALUES ('"+mCountrys.get(i).getCode().substring(0,3)+"','"+mCountrys.get(i).getName().replaceAll("'", " ")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_country` (`code`,`name`) VALUES ('"+mCountrys.get(i).getCode()+"','"+mCountrys.get(i).getName().replaceAll("'", " ")+"'); " ;
                }
                System.out.println(" sql "+ sql);
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getCity(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List<MCity> mCitys = new ArrayList<MCity>();
        try {
            ResultSet rs = s.executeQuery(sqlCity);
            while (rs.next()) {
                String code = rs.getString("CODE");
                String description = new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                MCity mCity = new MCity();
                mCity.setCode(code);
                mCity.setName(description);
                mCitys.add(mCity);
            }
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        Connection connect = null;
        Statement stm = null; 
        if(mCitys != null){
            connect = MySqlConnection.getConnection();
            try {
                stm = connect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "";
            System.out.println(" mCitys.size() "+ mCitys.size());
            for(int i = 0 ; i< mCitys.size() ; i ++){
                if(mCitys.get(i).getCode().length() > 3 ){
                    sql = " INSERT INTO `m_city` (`code`,`name`) VALUES ('"+mCitys.get(i).getCode().substring(0,3)+"','"+mCitys.get(i).getName().replaceAll("'", " ")+"'); " ;
                }else{
                    sql = " INSERT INTO `m_city` (`code`,`name`) VALUES ('"+mCitys.get(i).getCode()+"','"+mCitys.get(i).getName().replaceAll("'", " ")+"'); " ;
                }
                try {
                    stm.executeUpdate(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getTaxInvoice(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        List reptax = new ArrayList<ReportTaxInvoice>();
        String sql = TaxInvoiceReport;
        try {
            System.out.println(" sql "+ sql);	
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("ID");
                String taxid = rs.getString("TAX_ID");
                String taxno = rs.getString("TAX_NO");
                String taxdate = String.valueOf(rs.getString("TAX_DATE"));
                String codeap = rs.getString("CODE_AP");
                String description = new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                BigDecimal grossamount = new BigDecimal(rs.getString("GROSS_AMOUNT"));
                BigDecimal vatamount = new BigDecimal(rs.getString("VAT_AMOUNT"));
                BigDecimal amount = new BigDecimal(rs.getString("AMOUNT"));
                String flagtype = rs.getString("FLAG_TYPE");
                String invoicetype = rs.getString("INVOICE_TYPE");
                String taxno1 = rs.getString("TAXNO");
                String branch = rs.getString("BRANCH");
                String branchno = rs.getString("BRANCH_NO");
                ReportTaxInvoice rti = new ReportTaxInvoice();
                rti.setId(id);
                rti.setTaxid(taxid);
                rti.setTaxno(taxno);
                rti.setTaxdate(taxdate);
                rti.setCodeap(codeap);
                rti.setDescription(description);
                rti.setGrossamount(grossamount);
                rti.setVatamount(vatamount);
                rti.setAmount(amount);
                rti.setFlagtype(flagtype);
                rti.setInvoicetype(invoicetype);
                rti.setTaxno1(taxno1);
                rti.setBranch(branch);
                rti.setBranchno(branchno);
                reptax.add(rti);
            }
            ExportTaxinvoiceReport(reptax);
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void ExportTaxinvoiceReport(List reptax){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("TaxInvoice");

        HSSFCellStyle styleC1 = wb.createCellStyle();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("Tax Invoice Report");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cellStart.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderTop(HSSFCellStyle.BORDER_THIN);

        HSSFRow row2 = sheet.createRow(2);
        HSSFCell cell20 = row2.createCell(0);
        cell20.setCellValue("ID");
        cell20.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(0);
        HSSFCell cell21 = row2.createCell(1);
        cell21.setCellValue("TAX ID");
        cell21.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(1);
        HSSFCell cell22 = row2.createCell(2);
        cell22.setCellValue("TAX NO");
        cell22.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(2);
        HSSFCell cell23 = row2.createCell(3);
        cell23.setCellValue("TAX DATE");
        cell23.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);
        HSSFCell cell24 = row2.createCell(4);
        cell24.setCellValue("CODE AP");
        cell24.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell25 = row2.createCell(5);
        cell25.setCellValue("DESCRIPTION");
        cell25.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(5);
        HSSFCell cell26 = row2.createCell(6);
        cell26.setCellValue("GROSS AMOUNT");
        cell26.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(6);
        HSSFCell cell27 = row2.createCell(7);
        cell27.setCellValue("VAT AMOUNT");
        cell27.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(7);
        HSSFCell cell28 = row2.createCell(8);
        cell28.setCellValue("AMOUNT");
        cell28.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(8);
        HSSFCell cell29 = row2.createCell(9);
        cell29.setCellValue("FLAG TYPE");
        cell29.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(9);
        HSSFCell cell30 = row2.createCell(10);
        cell30.setCellValue("INVOICE TYPE");
        cell30.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(10);
        HSSFCell cell31 = row2.createCell(11);
        cell31.setCellValue("TAX NO 1");
        cell31.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(11);
        HSSFCell cell32 = row2.createCell(12);
        cell32.setCellValue("BRANCH");
        cell32.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(12);
        HSSFCell cell33 = row2.createCell(13);
        cell33.setCellValue("BRANCH NO");
        cell33.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(13);

        int count = 3 ;

        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        styleC23.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        styleC24.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC24.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));

        for(int i=0;i<reptax.size();i++){
            ReportTaxInvoice data = (ReportTaxInvoice)reptax.get(i);
            HSSFRow row = sheet.createRow(count + i);
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(data.getId());
            cell0.setCellStyle(styleC23);
         HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(data.getTaxid());
            cell1.setCellStyle(styleC23);
         HSSFCell cell13 = row.createCell(2);
            cell13.setCellValue(data.getTaxno());
            cell13.setCellStyle(styleC23);  
         HSSFCell cell2 = row.createCell(3);
            cell2.setCellValue(String.valueOf(data.getTaxdate()));
            cell2.setCellStyle(styleC23);   
         HSSFCell cell3= row.createCell(4);
            cell3.setCellValue(data.getCodeap());
            cell3.setCellStyle(styleC24);
         HSSFCell cell4 = row.createCell(5);
            cell4.setCellValue(data.getDescription().trim());
            cell4.setCellStyle(styleC24);   
         HSSFCell cell5 = row.createCell(6);
            cell5.setCellValue(!"null".equalsIgnoreCase(String.valueOf(data.getGrossamount())) ? (data.getGrossamount()).doubleValue() : 0);
            cell5.setCellStyle(styleC25);
         HSSFCell cell6 = row.createCell(7);
            cell6.setCellValue(!"null".equalsIgnoreCase(String.valueOf(data.getVatamount())) ? (data.getVatamount()).doubleValue() : 0);
            cell6.setCellStyle(styleC25);
         HSSFCell cell7 = row.createCell(8);
            cell7.setCellValue(!"null".equalsIgnoreCase(String.valueOf(data.getAmount())) ? (data.getAmount()).doubleValue() : 0);
            cell7.setCellStyle(styleC25);
         HSSFCell cell8 = row.createCell(9);
            cell8.setCellValue(data.getFlagtype());
            cell8.setCellStyle(styleC23);
         HSSFCell cell9 = row.createCell(10);
            cell9.setCellValue(data.getInvoicetype());
            cell9.setCellStyle(styleC24);
            HSSFCell cell10 = row.createCell(11);
            cell10.setCellValue(data.getTaxno1());
            cell10.setCellStyle(styleC24);
            HSSFCell cell11 = row.createCell(12);
            cell11.setCellValue(data.getBranch());
            cell11.setCellStyle(styleC24);
            HSSFCell cell12 = row.createCell(13);
            cell12.setCellValue(data.getBranchno());
            cell12.setCellStyle(styleC23);

        }
        for(int j =0;j<15;j++){
            sheet.autoSizeColumn(j);
        }
        sheet.setColumnWidth(5, 256*40);//27
        exportFileExcel("TaxInvoiceReport",wb);
    }
    
    public static void getAgentReport(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List repAgent = new ArrayList<ReportAgent>();
        String sql = AgentReport ;
        try {
            System.out.println(" sql "+ sql);	
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("ID");
                String systemdate = rs.getString("SYSTEM_DATE") == null ? "" : String.valueOf(rs.getString("SYSTEM_DATE"));
                String systemstaff = rs.getString("SYSTEM_STAFF");
                String code = rs.getString("CODE");
                String name = rs.getString("NAME") == null ? "" :  new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");	
                String address = rs.getString("ADDRESS") == null ? "" :  new String(rs.getString("ADDRESS").getBytes("ISO8859_1"),"TIS-620");
                String tel = rs.getString("TEL") == null ? "" : new String(rs.getString("TEL").getBytes("ISO8859_1"),"TIS-620");
                String fax = rs.getString("FAX");
                String description = rs.getString("DESCRIPTION") == null ? "" : new String(rs.getString("DESCRIPTION").getBytes("ISO8859_1"),"TIS-620");
                String nameT = rs.getString("NAME_T") == null ? "" :  new String(rs.getString("NAME_T").getBytes("ISO8859_1"),"TIS-620");
                String descriptionT = rs.getString("DESCRIPTION_T") == null ? "" : new String(rs.getString("DESCRIPTION_T").getBytes("ISO8859_1"),"TIS-620");
                String addressT = rs.getString("ADDRESS_T") == null ? "" : new String(rs.getString("ADDRESS_T").getBytes("ISO8859_1"),"TIS-620");
                String email = rs.getString("EMAIL");
                String web = rs.getString("WEB");
                String remarks = rs.getString("REMARKS") == null ? "" : new String(rs.getString("REMARKS").getBytes("ISO8859_1"),"TIS-620").trim();
                String warning = rs.getString("WARNING");
                String refid = rs.getString("REF_ID");
                String branch = rs.getString("BRANCH");
                String branchno = rs.getString("BRANCH_NO");
                String taxno = rs.getString("TAX_NO");
                ReportAgent reportAgent = new ReportAgent();
                reportAgent.setId(id);
                reportAgent.setSystemdate(systemdate);
                reportAgent.setSystemstaff(systemstaff);
                reportAgent.setCode(code);
                reportAgent.setName(name);
                reportAgent.setAddress(address);
                reportAgent.setTel(tel);
                reportAgent.setFax(fax);
                reportAgent.setDescription(description);
                reportAgent.setNameT(nameT);
                reportAgent.setDescriptionT(descriptionT);
                reportAgent.setAddressT(addressT);
                reportAgent.setEmail(email);
                reportAgent.setWeb(web);
                reportAgent.setRemarks(remarks);
                reportAgent.setWarning(warning);
                reportAgent.setRefid(refid);
                reportAgent.setBranch(branch);
                reportAgent.setBranchno(branchno);
                reportAgent.setTaxno(taxno);
                repAgent.add(reportAgent);
            }

            ExportAgentReport(repAgent);
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void getStaffReport(Statement s,Statement stmt){
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        UtilityFunction util = new UtilityFunction();
        List repStaff = new ArrayList<ReportStaff>();
        String sql = StaffReport ;
        try {
            System.out.println(" sql "+ sql);	
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("ID");
                String systemdate = rs.getString("SYSTEM_DATE") == null ? "" : String.valueOf(rs.getString("SYSTEM_DATE"));
                String systemstaff = rs.getString("SYSTEM_STAFF");
                String code = rs.getString("CODE");
                String name = rs.getString("NAME") == null ? "" : new String(rs.getString("NAME").getBytes("ISO8859_1"),"TIS-620");	
                String passwd = rs.getString("PASSWD");
                String position = rs.getString("POSITION");
                String departmentid = rs.getString("DEPARTMENT_ID");
                String tel = rs.getString("TEL");
                String car = rs.getString("CAR") == null ? "" : new String(rs.getString("CAR").getBytes("ISO8859_1"),"TIS-620");
                String status = rs.getString("STATUS");
                String signature = rs.getString("SIGNATURE");                
                ReportStaff reportStaff = new ReportStaff();
                reportStaff.setId(id);
                reportStaff.setSystemdate(systemdate);
                reportStaff.setSystemstaff(systemstaff);
                reportStaff.setCode(code);
                reportStaff.setName(name);
                reportStaff.setPasswd(passwd);
                reportStaff.setPosition(position);
                reportStaff.setDepartmentid(departmentid);
                reportStaff.setTel(tel);
                reportStaff.setCar(car);
                reportStaff.setStatus(status);
                reportStaff.setSignature(signature);
                repStaff.add(reportStaff);
            }

            ExportStaffReport(repStaff);
        } catch (SQLException e ) {
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try { 
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void ExportAgentReport(List repAgent){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Agent");

        HSSFCellStyle styleC1 = wb.createCellStyle();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("Agent Report");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cellStart.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderTop(HSSFCellStyle.BORDER_THIN);

        HSSFRow row2 = sheet.createRow(2);
        HSSFCell cell20 = row2.createCell(0);
        cell20.setCellValue("ID");
        cell20.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(0);
        HSSFCell cell21 = row2.createCell(1);
        cell21.setCellValue("SYSTEM DATE");
        cell21.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(1);
        HSSFCell cell22 = row2.createCell(2);
        cell22.setCellValue("SYSTEM STAFF");
        cell22.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(2);
        HSSFCell cell23 = row2.createCell(3);
        cell23.setCellValue("CODE");
        cell23.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);
        HSSFCell cell24 = row2.createCell(4);
        cell24.setCellValue("NAME");
        cell24.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell25 = row2.createCell(5);
        cell25.setCellValue("ADDRESS");
        cell25.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(5);
        HSSFCell cell26 = row2.createCell(6);
        cell26.setCellValue("TEL");
        cell26.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(6);
        HSSFCell cell27 = row2.createCell(7);
        cell27.setCellValue("FAX");
        cell27.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(7);
        HSSFCell cell28 = row2.createCell(8);
        cell28.setCellValue("DESCRIPTION");
        cell28.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(8);
        HSSFCell cell29 = row2.createCell(9);
        cell29.setCellValue("NAME T");
        cell29.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(9);
        HSSFCell cell30 = row2.createCell(10);
        cell30.setCellValue("DESCRIPTION T");
        cell30.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(10);
        HSSFCell cell31 = row2.createCell(11);
        cell31.setCellValue("ADDRESS T");
        cell31.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(11);
        HSSFCell cell32 = row2.createCell(12);
        cell32.setCellValue("EMAIL");
        cell32.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(12);
        HSSFCell cell33 = row2.createCell(13);
        cell33.setCellValue("WEB");
        cell33.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(13);
        HSSFCell cell34 = row2.createCell(14);
        cell34.setCellValue("REMARKS");
        cell34.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(14);
        HSSFCell cell35 = row2.createCell(15);
        cell35.setCellValue("WARNING");
        cell35.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(15);
        HSSFCell cell36 = row2.createCell(16);
        cell36.setCellValue("REF ID");
        cell36.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(16);
        HSSFCell cell37 = row2.createCell(17);
        cell37.setCellValue("BRANCH");
        cell37.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(17);
        HSSFCell cell38 = row2.createCell(18);
        cell38.setCellValue("BRANCH NO");
        cell38.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(18);
        HSSFCell cell39 = row2.createCell(19);
        cell39.setCellValue("TAX NO");
        cell39.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(19);

        int count = 3 ;

        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        styleC23.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        styleC24.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC24.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));

        for(int i=0;i<repAgent.size();i++){
            ReportAgent data = (ReportAgent)repAgent.get(i);
            HSSFRow row = sheet.createRow(count + i);
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(data.getId());
            cell0.setCellStyle(styleC23);
         HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(String.valueOf(data.getSystemdate()));
            cell1.setCellStyle(styleC23);
         HSSFCell cell13 = row.createCell(2);
            cell13.setCellValue(data.getSystemstaff());
            cell13.setCellStyle(styleC24);  
         HSSFCell cell2 = row.createCell(3);
            cell2.setCellValue(data.getCode());
            cell2.setCellStyle(styleC24);   
         HSSFCell cell3= row.createCell(4);
            cell3.setCellValue(data.getName());
            cell3.setCellStyle(styleC24);
         HSSFCell cell4 = row.createCell(5);
            cell4.setCellValue(data.getAddress().trim());
            cell4.setCellStyle(styleC24);   
         HSSFCell cell5 = row.createCell(6);
            cell5.setCellValue(data.getTel());
            cell5.setCellStyle(styleC24);
         HSSFCell cell6 = row.createCell(7);
            cell6.setCellValue(data.getFax());
            cell6.setCellStyle(styleC24);
         HSSFCell cell7 = row.createCell(8);
            cell7.setCellValue(data.getDescription().trim());
            cell7.setCellStyle(styleC24);
         HSSFCell cell8 = row.createCell(9);
            cell8.setCellValue(data.getNameT());
            cell8.setCellStyle(styleC24);
         HSSFCell cell9 = row.createCell(10);
            cell9.setCellValue(data.getDescriptionT().trim());
            cell9.setCellStyle(styleC24);
        HSSFCell cell10 = row.createCell(11);
            cell10.setCellValue(data.getAddressT().trim());
            cell10.setCellStyle(styleC24);
        HSSFCell cell11 = row.createCell(12);
            cell11.setCellValue(data.getEmail());
            cell11.setCellStyle(styleC24);
        HSSFCell cell12 = row.createCell(13);
            cell12.setCellValue(data.getWeb());
            cell12.setCellStyle(styleC24);
        HSSFCell cell14 = row.createCell(14);
            cell14.setCellValue(data.getRemarks());
            cell14.setCellStyle(styleC24);
        HSSFCell cell15 = row.createCell(15);
            cell15.setCellValue(data.getWarning());
            cell15.setCellStyle(styleC24);
        HSSFCell cell16 = row.createCell(16);
            cell16.setCellValue(data.getRefid());
            cell16.setCellStyle(styleC24);    
        HSSFCell cell17 = row.createCell(17);
            cell17.setCellValue(data.getBranch());
            cell17.setCellStyle(styleC24);    
        HSSFCell cell18 = row.createCell(18);
            cell18.setCellValue(data.getBranchno());
            cell18.setCellStyle(styleC24);
        HSSFCell cell19 = row.createCell(19);
            cell19.setCellValue(data.getTaxno());
            cell19.setCellStyle(styleC23);
        }

        for(int j =0;j<20;j++){
            sheet.autoSizeColumn(j);
        }
        
        for(int k=4 ; k < 21 ; k++){
            if(k != 6 && k != 7 && k != 12&& k != 13&& k<16){
                sheet.setColumnWidth(k, 256*35);//27
            }else{
                sheet.setColumnWidth(k, 256*20);//27
            }
        }
        exportFileExcel("AgentReport",wb);
    }
    
    public static void ExportStaffReport(List repStaff){
        UtilityExcelFunction excelFunction = new UtilityExcelFunction();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Staff");

        HSSFCellStyle styleC1 = wb.createCellStyle();
        // Set align Text
        HSSFCellStyle styleC21 = wb.createCellStyle();
        styleC21.setAlignment(styleC21.ALIGN_RIGHT);
        HSSFCellStyle styleC22 = wb.createCellStyle();
        styleC22.setAlignment(styleC22.ALIGN_LEFT);

        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cellStart = row1.createCell(0);
        cellStart.setCellValue("Staff Report");
        styleC1.setFont(excelFunction.getHeaderFont(wb.createFont()));
        cellStart.setCellStyle(styleC1);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:F1"));

        // Header Table
        HSSFCellStyle styleC3Center = wb.createCellStyle();
        styleC3Center.setFont(excelFunction.getHeaderTable(wb.createFont()));
        styleC3Center.setAlignment(styleC3Center.ALIGN_CENTER);
        styleC3Center.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC3Center.setBorderTop(HSSFCellStyle.BORDER_THIN);

        HSSFRow row2 = sheet.createRow(2);
        HSSFCell cell20 = row2.createCell(0);
        cell20.setCellValue("ID");
        cell20.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(0);
        HSSFCell cell21 = row2.createCell(1);
        cell21.setCellValue("SYSTEM DATE");
        cell21.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(1);
        HSSFCell cell22 = row2.createCell(2);
        cell22.setCellValue("SYSTEM STAFF");
        cell22.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(2);
        HSSFCell cell23 = row2.createCell(3);
        cell23.setCellValue("CODE");
        cell23.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(3);
        HSSFCell cell24 = row2.createCell(4);
        cell24.setCellValue("NAME");
        cell24.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(4);
        HSSFCell cell25 = row2.createCell(5);
        cell25.setCellValue("PASSWD");
        cell25.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(5);
        HSSFCell cell26 = row2.createCell(6);
        cell26.setCellValue("POSITION");
        cell26.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(6);
        HSSFCell cell27 = row2.createCell(7);
        cell27.setCellValue("DEPARTMENT ID");
        cell27.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(7);
        HSSFCell cell28 = row2.createCell(8);
        cell28.setCellValue("TEL");
        cell28.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(8);
        HSSFCell cell29 = row2.createCell(9);
        cell29.setCellValue("CAR");
        cell29.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(9);
        HSSFCell cell30 = row2.createCell(10);
        cell30.setCellValue("STATUS");
        cell30.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(10);
        HSSFCell cell31 = row2.createCell(11);
        cell31.setCellValue("SIGNATURE");
        cell31.setCellStyle(styleC3Center);
        sheet.autoSizeColumn(11);
        int count = 3 ;

        HSSFDataFormat currency = wb.createDataFormat();
        HSSFCellStyle styleC23 = wb.createCellStyle();
        styleC23.setAlignment(styleC23.ALIGN_CENTER);
        styleC23.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC23.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC24 = wb.createCellStyle();
        styleC24.setAlignment(styleC24.ALIGN_LEFT);
        styleC24.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC24.setBorderRight(HSSFCellStyle.BORDER_THIN);
        HSSFCellStyle styleC25 = wb.createCellStyle();
        styleC25.setAlignment(styleC25.ALIGN_RIGHT);
        styleC25.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleC25.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleC25.setDataFormat(currency.getFormat("#,##0.00"));

        for(int i=0;i<repStaff.size();i++){
            ReportStaff data = (ReportStaff)repStaff.get(i);
            HSSFRow row = sheet.createRow(count + i);
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(data.getId());
            cell0.setCellStyle(styleC23);
         HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(String.valueOf(data.getSystemdate()));
            cell1.setCellStyle(styleC23);
         HSSFCell cell13 = row.createCell(2);
            cell13.setCellValue(data.getSystemstaff());
            cell13.setCellStyle(styleC24);  
         HSSFCell cell2 = row.createCell(3);
            cell2.setCellValue(data.getCode());
            cell2.setCellStyle(styleC24);   
         HSSFCell cell3= row.createCell(4);
            cell3.setCellValue(data.getName());
            cell3.setCellStyle(styleC24);
         HSSFCell cell4 = row.createCell(5);
            cell4.setCellValue(data.getPasswd());
            cell4.setCellStyle(styleC24);   
         HSSFCell cell5 = row.createCell(6);
            cell5.setCellValue(data.getPosition());
            cell5.setCellStyle(styleC24);
         HSSFCell cell6 = row.createCell(7);
            cell6.setCellValue(data.getDepartmentid());
            cell6.setCellStyle(styleC24);
         HSSFCell cell7 = row.createCell(8);
            cell7.setCellValue(data.getTel());
            cell7.setCellStyle(styleC24);
         HSSFCell cell8 = row.createCell(9);
            cell8.setCellValue(data.getCar());
            cell8.setCellStyle(styleC24);
         HSSFCell cell9 = row.createCell(10);
            cell9.setCellValue(data.getStatus());
            cell9.setCellStyle(styleC24);
        HSSFCell cell10 = row.createCell(11);
            cell10.setCellValue(data.getSignature());
            cell10.setCellStyle(styleC24);
        }
        for(int j =0;j<12;j++){
            sheet.autoSizeColumn(j);
        }
//        sheet.setColumnWidth(8, 256*40);//27
//        sheet.setColumnWidth(10, 256*40);//27
        exportFileExcel("StaffReport",wb);
    }
    
    static void exportFileExcel(String filename,HSSFWorkbook wb){
        try {
            FileOutputStream out = new FileOutputStream(new File(ExportFilePath+filename+".xls"));
            wb.write(out);
            out.close();
            System.out.println("Excel StaffReport written successfully..");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class OracleConnection{
    private static final String ip = "192.168.99.48";
    private static final String port = "1521";
    private static final String schema   = "ORCL";
    private static final String username = "travox3";
    private static final String password = "oracle";
    
     static{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     
    public static Connection getConnection(){
        Connection connect = null;
        try {   
            connect = DriverManager.getConnection("jdbc:oracle:thin:@"+ip+":"+port+"/"+schema+"",username,password);
            System.out.println("Database Connected.");
        } catch (SQLException ex) {
            
        }
        return connect;
    }
}

class MySqlConnection{
    private static final String ip = "192.168.99.48";
    private static final String port = "3306";
    private static final String schema   = "smitravel_uat";
    private static final String username = "root";
    private static final String password = "P@ssw0rd";
    
     static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainMigrate.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
     
    public static Connection getConnection(){
        Connection connect = null;
        try {   
            connect = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+schema+"?characterEncoding=UTF-8&amp;useUnicode=yes",username,password);
            System.out.println("Database Connected.");
        } catch (SQLException ex) {
            
        }
        return connect;
    }
}
