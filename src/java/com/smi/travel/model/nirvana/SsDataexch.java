/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.model.nirvana;

import com.smi.travel.datalayer.view.entity.NirvanaInterface;
import com.sybase.jdbcx.SybDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author chonnasith
 */
public class SsDataexch {
    private static final String ip = "192.168.129.184";
    private static final String port = "2638";
    private static final String username = "ICONEXT";
    private static final String password = "iconext";
    private static final String url = "jdbc:sybase:Tds:"+ip+":"+port; 
    Connection con = null;  
    Statement stmt = null;  
    SybDriver sybDriver = null; 
    
    private String dataCd;
    private String dataNo;
    private String entSysCd;
    private String entSysDate;
    private String entDataNo;
    private String entComment;
    private String rcvSysCd;
    private String rcvStaCd;
    private String rcvSysDate;
    private String rcvComment;
    private String traNesCd;
    private String traStaCd;
    private String traSysDate;
    private String dataArea;
    private List ssDataexchTrList = new ArrayList<SsDataexchTr>();
    
    //Nirvana Interface
    //AP
    private String payment_detail_id;
    private String paymenttype;
    //AR
    private String rowid;
    
    public String connectSybase(SsDataexch ssDataexch) throws Exception {
        String result = "";
        String status = "";
        sybDriver = (SybDriver) Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();  
        con = DriverManager.getConnection(url,username, password);
        try {
            if(con != null){
                System.out.println(" sybase connected ");
                stmt = con.createStatement();
                result = insertHeader(ssDataexch);
    //            stmt.executeQuery(" exec SOFTPACK.zz_SMI_payablejrnl ");
//                if(!"null".equalsIgnoreCase(result)){
//                    ResultSet rs = stmt.executeQuery("select * from ss_dataexch2 where data_no = '" + ssDataexch.getDataNo() + "'");
//                    while (rs.next()) {    
//                        status = rs.getString("rcv_sta_cd") == null ? "" : rs.getString("rcv_sta_cd");
//                        System.out.println("Active ::  " + status );
//                    }
//                }
            }      
        
//            if(!"9".equalsIgnoreCase(status)){
//                return "fail";
//            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            stmt.close();
            con.close(); 
            
        }
        
        return ssDataexch.getDataNo();
    }
    
    public List<NirvanaInterface> callStoredProcedureAP(List<SsDataexch> ssDataexchList) throws Exception {
        String result = "success";
        List<NirvanaInterface> nirvanaInterfaceList = new ArrayList<NirvanaInterface>();
        sybDriver = (SybDriver) Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();  
        con = DriverManager.getConnection(url,username, password);
        try {
            if(con != null){
                System.out.println(" ===== callStoredProcedure AP===== ");
                stmt = con.createStatement();
                stmt.executeUpdate(" exec SOFTPACK.zz_SMI_payablejrnl "); 
                if(!"null".equalsIgnoreCase(result)){
                    for(int i=0; i<ssDataexchList.size(); i++){
                        SsDataexch ssDataexch = ssDataexchList.get(i);
                        System.out.println("===== Data No ===== : "+ssDataexch.getDataNo());
                        ResultSet rs = stmt.executeQuery("select * from ss_dataexch2 where data_no = '" + ssDataexch.getDataNo() + "'");
                        while (rs.next()) {    
                            String status = rs.getString("rcv_sta_cd") == null ? "" : rs.getString("rcv_sta_cd");
                            
                            if("9".equalsIgnoreCase(status)){
//                                result = "fail";
                                //AP
                                String datano = (ssDataexch.getDataNo() != null && !"".equalsIgnoreCase(ssDataexch.getDataNo()) ? ssDataexch.getDataNo() : "");
                                String paymentDetailId = (ssDataexch.getPayment_detail_id() != null && !"".equalsIgnoreCase(ssDataexch.getPayment_detail_id()) ? ssDataexch.getPayment_detail_id() : "");
                                String paymentType = (ssDataexch.getPaymenttype() != null && !"".equalsIgnoreCase(ssDataexch.getPaymenttype()) ? ssDataexch.getPaymenttype() : "");

                                //AR
                                String rowid = (ssDataexch.getRowid() != null && !"".equalsIgnoreCase(ssDataexch.getRowid()) ? ssDataexch.getRowid() : "");

                                NirvanaInterface nirvanaInterface = new NirvanaInterface();
                                nirvanaInterface.setDatano(datano);
                                nirvanaInterface.setPayment_detail_id(paymentDetailId);
                                nirvanaInterface.setPaymenttype(paymentType);
                                nirvanaInterface.setRowid(rowid);
                                
                                nirvanaInterfaceList.add(nirvanaInterface);
                            }
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            stmt.close();
            con.close();
            
        }
                
        return nirvanaInterfaceList;
    }
    
    public List<NirvanaInterface> callStoredProcedureAR(List<SsDataexch> ssDataexchList) throws Exception {
        String result = "success";
        List<NirvanaInterface> nirvanaInterfaceList = new ArrayList<NirvanaInterface>();
        sybDriver = (SybDriver) Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();  
        con = DriverManager.getConnection(url,username, password);
        try {
            if(con != null){
                System.out.println(" ===== callStoredProcedure AR ===== ");
                stmt = con.createStatement();
                stmt.executeUpdate(" exec SOFTPACK.zz_smi_salesjrnl "); 
                if(!"null".equalsIgnoreCase(result)){
                    for(int i=0; i<ssDataexchList.size(); i++){
                        SsDataexch ssDataexch = ssDataexchList.get(i);
                        System.out.println("===== Data No ===== : "+ssDataexch.getDataNo());
                        ResultSet rs = stmt.executeQuery("select * from ss_dataexch2 where data_no = '" + ssDataexch.getDataNo() + "'");
                        while (rs.next()) {    
                            String status = rs.getString("rcv_sta_cd") == null ? "" : rs.getString("rcv_sta_cd");
                            
                            if("9".equalsIgnoreCase(status)){
//                                result = "fail";
                                //AP
                                String datano = (ssDataexch.getDataNo() != null && !"".equalsIgnoreCase(ssDataexch.getDataNo()) ? ssDataexch.getDataNo() : "");
                                String paymentDetailId = (ssDataexch.getPayment_detail_id() != null && !"".equalsIgnoreCase(ssDataexch.getPayment_detail_id()) ? ssDataexch.getPayment_detail_id() : "");
                                String paymentType = (ssDataexch.getPaymenttype() != null && !"".equalsIgnoreCase(ssDataexch.getPaymenttype()) ? ssDataexch.getPaymenttype() : "");

                                //AR
                                String rowid = (ssDataexch.getRowid() != null && !"".equalsIgnoreCase(ssDataexch.getRowid()) ? ssDataexch.getRowid() : "");

                                NirvanaInterface nirvanaInterface = new NirvanaInterface();
                                nirvanaInterface.setDatano(datano);
                                nirvanaInterface.setPayment_detail_id(paymentDetailId);
                                nirvanaInterface.setPaymenttype(paymentType);
                                nirvanaInterface.setRowid(rowid);
                                
                                nirvanaInterfaceList.add(nirvanaInterface);
                            }
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            stmt.close();
            con.close();
            
        }
                
        return nirvanaInterfaceList;
    }
    
    public String insertHeader(SsDataexch ssDataexch) throws SQLException {
        String dataNo = "";
        String sql = " INSERT INTO ss_dataexch2 values ("
                + "'" + ssDataexch.getDataCd() + "'"
                + ",'" + ssDataexch.getDataNo() + "'"
                + ",'" + ssDataexch.getEntSysCd() + "'"
                + ",'" + ssDataexch.getEntSysDate()+ "'"
                + ",'" + ssDataexch.getRcvStaCd()+ "'"
                + ",'" + ssDataexch.getRcvSysDate()+ "'"
                + ",'" + ssDataexch.getRcvComment()+ "'"
                + ",'" + ssDataexch.getDataArea()+ "' );";
        
        try {
            stmt.executeUpdate(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }     

        String datanodetail = insertDetail(ssDataexch.getSsDataexchTrList());
        System.out.println(" datanodetail ::: " +datanodetail);
        
        return dataNo;
    }
    
    public String insertDetail(List<SsDataexchTr> ssDataexchTrList) throws SQLException {
        String dataNo = "";
        for(int i=0; i<ssDataexchTrList.size(); i++){
            SsDataexchTr ssDataexchTr = ssDataexchTrList.get(i);
            String sql = " INSERT INTO ss_dataexchtr2 values ("
                + "'" + ssDataexchTr.getDataCd() + "'"
                + ",'" + ssDataexchTr.getDataNo() + "'"
                + ",'" + ssDataexchTr.getDataSeq()+ "'"
                + ",'" + ssDataexchTr.getEntSysCd()+ "'"
                + ",'" + ssDataexchTr.getEntSysDate()+ "'"
                + ",'" + ssDataexchTr.getRcvComment()+ "'"
                + ",'" + ssDataexchTr.getDataArea()+ "' );";
            
            try {
                stmt.executeUpdate(sql);
                
            } catch (Exception e) {
                e.printStackTrace();
                
            }
            
        }
        
        return dataNo;
    }

    public String getDataCd() {
        return dataCd;
    }

    public void setDataCd(String dataCd) {
        this.dataCd = dataCd;
    }

    public String getDataNo() {
        return dataNo;
    }

    public void setDataNo(String dataNo) {
        this.dataNo = dataNo;
    }

    public String getEntSysCd() {
        return entSysCd;
    }

    public void setEntSysCd(String entSysCd) {
        this.entSysCd = entSysCd;
    }

    public String getEntSysDate() {
        return entSysDate;
    }

    public void setEntSysDate(String entSysDate) {
        this.entSysDate = entSysDate;
    }

    public String getEntDataNo() {
        return entDataNo;
    }

    public void setEntDataNo(String entDataNo) {
        this.entDataNo = entDataNo;
    }

    public String getEntComment() {
        return entComment;
    }

    public void setEntComment(String entComment) {
        this.entComment = entComment;
    }

    public String getRcvSysCd() {
        return rcvSysCd;
    }

    public void setRcvSysCd(String rcvSysCd) {
        this.rcvSysCd = rcvSysCd;
    }

    public String getRcvStaCd() {
        return rcvStaCd;
    }

    public void setRcvStaCd(String rcvStaCd) {
        this.rcvStaCd = rcvStaCd;
    }

    public String getRcvSysDate() {
        return rcvSysDate;
    }

    public void setRcvSysDate(String rcvSysDate) {
        this.rcvSysDate = rcvSysDate;
    }

    public String getRcvComment() {
        return rcvComment;
    }

    public void setRcvComment(String rcvComment) {
        this.rcvComment = rcvComment;
    }

    public String getTraNesCd() {
        return traNesCd;
    }

    public void setTraNesCd(String traNesCd) {
        this.traNesCd = traNesCd;
    }

    public String getTraStaCd() {
        return traStaCd;
    }

    public void setTraStaCd(String traStaCd) {
        this.traStaCd = traStaCd;
    }

    public String getTraSysDate() {
        return traSysDate;
    }

    public void setTraSysDate(String traSysDate) {
        this.traSysDate = traSysDate;
    }

    public String getDataArea() {
        return dataArea;
    }

    public void setDataArea(String dataArea) {
        this.dataArea = dataArea;
    }

    public List getSsDataexchTrList() {
        return ssDataexchTrList;
    }

    public void setSsDataexchTrList(List ssDataexchTrList) {
        this.ssDataexchTrList = ssDataexchTrList;
    }

    public String getPayment_detail_id() {
        return payment_detail_id;
    }

    public void setPayment_detail_id(String payment_detail_id) {
        this.payment_detail_id = payment_detail_id;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }
    
}
