/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.OutboundSummaryDao;
import com.smi.travel.datalayer.entity.Hotel;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MBank;
import com.smi.travel.datalayer.entity.MCity;
import com.smi.travel.datalayer.entity.MCountry;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.view.entity.OutboundHotelSummaryView;
import com.smi.travel.datalayer.view.entity.OutboundPackageSummaryView;
import com.smi.travel.datalayer.view.entity.OutboundProductSummaryExcel;
import com.smi.travel.datalayer.view.entity.OutputTaxView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Kanokporn
 */
public class OutboundSummaryImpl implements OutboundSummaryDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public List getOutboundPackageSummaryReportList(String fromdate, String todate, String cityId, String packageId, String salebyId, String paybyId, String bankId, String statusId) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<OutputTaxView>();
        String paybyname = "ALL";
        String cityname = "ALL";
        String packagename = "ALL";
        String statusname = "ALL";
        String bankname = "ALL";
        
        String query = "SELECT * FROM `outbound_package_summary` op where op.departdate BETWEEN '"+fromdate+"' and '"+todate+"'";
        
        if((cityId != null) && (!"".equalsIgnoreCase(cityId))) {
            query += " and op.city like '%C" + cityId + "%,'" ;
            
            String querycity = "from MCity c where c.id = '"+cityId+"'";
            List<MCity> mCity = session.createQuery(querycity).list();
            if(!mCity.isEmpty()) {
                cityname = mCity.get(0).getName();
            }
        }
         
        if((packageId != null) && (!"".equalsIgnoreCase(packageId))) {
            query += " and op.packageid = '" + packageId + "'" ;
            
            String querypackage= " from PackageTour pt where pt.id = '"+packageId+"'";
            List<PackageTour> packageTours = session.createQuery(querypackage).list();
            if(!packageTours.isEmpty()) {
                packagename = packageTours.get(0).getName();
            }
        }
        
        if((salebyId != null) && (!"".equalsIgnoreCase(salebyId))) {
            query += " and op.saleby = '" + salebyId + "'" ;
        }
        
        if((paybyId != null) && (!"".equalsIgnoreCase(paybyId))) {
            query += " and op.payid = '" + paybyId + "'" ;
            
            String querypayby = "from MAccpay pay where pay.id = '"+paybyId+"'";
            List<MAccpay> mAccpays = session.createQuery(querypayby).list();
            if(!mAccpays.isEmpty()) {
                paybyname = mAccpays.get(0).getName();
            }
        }
       
        if((bankId != null) && (!"".equalsIgnoreCase(bankId))) {
            query += " and op.bank = '" + bankId + "'" ;
            
            String querybank = "from MBank bank where bank.id = '"+bankId+"'";
            List<MBank> mBank = session.createQuery(querybank).list();
            if(!mBank.isEmpty()) {
                bankname = mBank.get(0).getName();
            }
        }
        
        if((statusId != null) && (!"".equalsIgnoreCase(statusId))) {
            query += " and op.status = '" + statusId + "'" ;
            
            String querystatus = "from MItemstatus s where s.id = '"+statusId+"'";
            List<MItemstatus> maItemstatus = session.createQuery(querystatus).list();
            if(!maItemstatus.isEmpty()) {
                statusname = maItemstatus.get(0).getName();
            }
        }

        query += " ORDER BY op.departdate ";
        
        System.out.println("query : "+query);
        
        List<Object[]> QueryList = session.createSQLQuery(query)
                .addScalar("departdate", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("recondno", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("payby", Hibernate.STRING)
                .addScalar("packagename", Hibernate.STRING)
                .addScalar("period", Hibernate.STRING)
                .addScalar("pax_adult", Hibernate.STRING)
                .addScalar("pax_child", Hibernate.STRING)
                .addScalar("pax_infant", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("profit", Hibernate.STRING)
                .addScalar("transferdate", Hibernate.STRING)
                .addScalar("seller", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("packagecode", Hibernate.STRING)
                .addScalar("banktransfer", Hibernate.STRING)
                .addScalar("statusname", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy");
        SimpleDateFormat dateformatDetail = new SimpleDateFormat();
        dateformatDetail.applyPattern("dd-MM-yyyy");
        for (Object[] B : QueryList){
            OutboundPackageSummaryView opsv = new OutboundPackageSummaryView();
            opsv.setHeadercity(cityname);
            opsv.setHeaderpayby(paybyname);
            opsv.setHeaderpackage(packagename);
            opsv.setHeaderbank(bankname);
            opsv.setHeaderstatus(statusname);
            opsv.setHeaderdate(util.ConvertString(dateformat.format(util.convertStringToDate(fromdate))) + " to " + util.ConvertString(dateformat.format(util.convertStringToDate(todate))));
//            opsv.setDepartdate("null".equals(String.valueOf(B[0])) ? "" : util.ConvertString(dateformatDetail.format(util.convertStringToDate(String.valueOf(B[0])))));
            if(!"null".equalsIgnoreCase(String.valueOf(B[0])) && !"".equalsIgnoreCase(String.valueOf(B[0]))){
                opsv.setDepartdate(util.ConvertString(dateformatDetail.format(util.convertStringToDate(String.valueOf(B[0])))));
            }else{
                opsv.setDepartdate("");
            }
            opsv.setRefno(util.ConvertString(B[1]));
            opsv.setRecondno(util.ConvertString(B[2]));
            opsv.setLeader(util.ConvertString(B[3]));
            opsv.setPayby(util.ConvertString(B[4]));
            opsv.setPackagename(util.ConvertString(B[5]));
            opsv.setPeriod(util.ConvertString(B[6]));
            opsv.setPaxadult(util.ConvertString(B[7]));
            opsv.setPaxchild(util.ConvertString(B[8]));
            opsv.setPaxinfant(util.ConvertString(B[9]));
            opsv.setNet((B[10]) != null ? util.ConvertString(B[10]) : "0.00");
            opsv.setSell((B[11]) != null ? util.ConvertString(B[11]) : "0.00");
            opsv.setProfit((B[12]) != null ? util.ConvertString(B[12]) : "0.00");
            if(!"null".equalsIgnoreCase(String.valueOf(B[13])) && !"".equalsIgnoreCase(String.valueOf(B[13]))){
                opsv.setTransferdate(util.ConvertString(dateformatDetail.format(util.convertStringToDate(String.valueOf(B[13])))));
            }else{
                opsv.setTransferdate("");
            }
            opsv.setSeller(util.ConvertString(B[14]));
            opsv.setInvno(util.ConvertString(B[15]));
            opsv.setPackagecode(util.ConvertString(B[16]));
            opsv.setBanktransfer(util.ConvertString(B[17]));
            opsv.setStatusname(util.ConvertString(B[18]));
            opsv.setRemark(util.ConvertString(B[19]));
            data.add(opsv);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    @Override
    public List getOutboundProductSummary(String productid, String from, String to, String saleby, String payby, String bank,String printby,String productname,String salename,String status) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        String query ="";
        int checkQuery = 0;
        if(productid == null && from == null && to == null && saleby == null && payby == null && bank == null){
            query = "SELECT *  FROM  `outbound_product_summary` opm ";
        }else{
            if("".equals(productid) && "".equals(from) && "".equals(to) && "".equals(saleby) && "".equals(payby) && "".equals(bank)){
                query = "SELECT *  FROM  `outbound_product_summary` opm ";
            }else{
                query = "SELECT *  FROM  `outbound_product_summary` opm  where ";
            }
        }
        
        
       if ((from != null )&&(!"".equalsIgnoreCase(from))) {
            if ((to != null )&&(!"".equalsIgnoreCase(to))) {
                if(checkQuery == 1){
                     query += " and opm.otherdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }else{
                    checkQuery = 1;
                     query += " opm.otherdate  BETWEEN  '" + from + "' AND '" + to + "' ";
                }
            }
        }
        if ((productid != null )&&(!"".equalsIgnoreCase(productid))) {
            if(checkQuery == 1){
                 query += " and opm.productid  = " + productid;
            }else{
                checkQuery = 1;
                 query += " opm.productid = " + productid;
            }
        }
        if ((saleby != null )&&(!"".equalsIgnoreCase(saleby))) {
            if(checkQuery == 1){
                 query += " and opm.saleby  = " + saleby;
            }else{
                checkQuery = 1;
                 query += " opm.saleby = " + saleby;
            }
        }
        if ((payby != null )&&(!"".equalsIgnoreCase(payby))) {
            if(checkQuery == 1){
                 query += " and opm.payid  = " + payby;
            }else{
                checkQuery = 1;
                 query += " opm.payid = " + payby;
            }
        }
        if ((bank != null )&&(!"".equalsIgnoreCase(bank))) {
            if(checkQuery == 1){
                 query += " and opm.bank  = " + bank;
            }else{
                checkQuery = 1;
                 query += " opm.bank = " + bank;
            }
        }
        
        System.out.println(" query :::: " +query);
               
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("otherdate", Hibernate.STRING)//0
                .addScalar("refno", Hibernate.STRING)
                .addScalar("recondno", Hibernate.STRING)
                .addScalar("productname", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("passno", Hibernate.STRING)// 5
                .addScalar("pax_adult", Hibernate.INTEGER)
                .addScalar("pax_child", Hibernate.INTEGER)
                .addScalar("pax_infant", Hibernate.INTEGER)
                .addScalar("net_adult", Hibernate.BIG_DECIMAL)
                .addScalar("net_child", Hibernate.BIG_DECIMAL)// 10
                .addScalar("net_infant", Hibernate.BIG_DECIMAL)
                .addScalar("sell_adult", Hibernate.BIG_DECIMAL)
                .addScalar("sell_child", Hibernate.BIG_DECIMAL)
                .addScalar("sell_infant", Hibernate.BIG_DECIMAL)
                .addScalar("profit", Hibernate.BIG_DECIMAL)//15
                .addScalar("payby", Hibernate.STRING)
                .addScalar("transferdate", Hibernate.STRING)
                .addScalar("seller", Hibernate.STRING)
                .addScalar("dulation", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)// 20
                .addScalar("saleby", Hibernate.STRING)
                .addScalar("productid", Hibernate.STRING)
                .addScalar("payid", Hibernate.STRING)
                .addScalar("bank", Hibernate.STRING)
                .list();
        
            String paybyTemp = "";
            
            if("1".equalsIgnoreCase(payby)){
                paybyTemp = "Cash";
            }else if("2".equalsIgnoreCase(payby)){
                paybyTemp = "Cash on Demand";
            }else if("3".equalsIgnoreCase(payby)){
                paybyTemp = "Credit Card";
            }else if("4".equalsIgnoreCase(payby)){
                paybyTemp = "Bank Transfer";
            }else if("5".equalsIgnoreCase(payby)){
                paybyTemp = "Cheque";
            }else if("6".equalsIgnoreCase(payby)){
                paybyTemp = "Void";
            }else if("7".equalsIgnoreCase(payby)){
                paybyTemp = "Wait";
            }else{
                paybyTemp = "ALL";
            }
            
            String statusTemp = "";
                    
            if("1".equalsIgnoreCase(status)){
                statusTemp = "OK";
            }else if("2".equalsIgnoreCase(status)){
                statusTemp = "WAIT";
            }else if("3".equalsIgnoreCase(status)){
                statusTemp = "CANCEL";
            }else{
                statusTemp = "ALL";
            }
            
            String bankTemp = "";
            if(bank != null && !"".equals(bank)){
                String queryBank = "from MBank b where b.id = '"+bank+"' ";
                List<MBank> mBanks = session.createQuery(queryBank).list();
                if(mBanks!=null){
                MBank mBank = mBanks.get(0);
                bankTemp = mBank.getName();
                }
            }else{
                bankTemp = "ALL";
            }        
            
        for (Object[] B : QueryStaffList) {
            OutboundProductSummaryExcel other = new OutboundProductSummaryExcel();
            other.setSaledate(B[0] != null ?  util.SetFormatDate( util.convertStringToDate(util.ConvertString(B[0])),"dd-MM-yyyy") : "");
            other.setRecordno(B[2]== null ? "" :util.ConvertString(B[2]));
            other.setTravoxno(B[1]== null ? "" :util.ConvertString(B[1])); // 
            other.setPasstype(B[3]== null ? "" :util.ConvertString(B[3])); // 
            other.setPassno(B[5]== null ? "" :util.ConvertString(B[5]));
            other.setDulation(B[19]== null ? "" :util.ConvertString(B[19]));
            other.setInvno(B[20]== null ? "" :util.ConvertString(B[20]));
            other.setCustomername(B[4]== null ? "" :util.ConvertString(B[4]));//
            other.setPaxad((Integer)B[6]);
            other.setPaxch((Integer)B[7]);
            other.setPaxin((Integer)B[8]);
            other.setTotalnettadult((BigDecimal)B[9]);
            other.setTotalnettchild((BigDecimal)B[10]);
            other.setTotalnettinfant((BigDecimal)B[11]);
            other.setTotalsaleadult((BigDecimal)B[12]);
            other.setTotalsalechild((BigDecimal)B[13]);
            other.setTotalsaleinfant((BigDecimal)B[14]);
            other.setProfittotal((BigDecimal)B[15]);
            other.setPayby(B[16]== null ? "" :util.ConvertString(B[16]));
            other.setDatetrsf(B[17]== null ? "" :util.SetFormatDate( util.convertStringToDate(util.ConvertString(B[17])),"dd-MM-yyyy"));
            other.setSeller(B[18]== null ? "" :util.ConvertString(B[18]));
            // Set Header
            if(productname != null && !"".equals(productname)){
                other.setProductnamepage(util.ConvertString(B[3]));
            }else{
                other.setProductnamepage("ALL");
            }
            other.setProductname(B[3]== null ? "" :util.ConvertString(B[3]));
            if(salename != null && !"".equals(salename)){
                other.setSalebypage(salename);
            }else{
                other.setSalebypage("ALL");
            }          
            if(bank != null && !"".equals(bank)){
                other.setBankpage(bankTemp);
            }else{
                other.setBankpage("ALL");
            }
            if(from != null && !"".equals(from)){
                if(to != null && !"".equals(to)){
                    String dateOutbound = from + " To " + to;
                    other.setSaledatepage(dateOutbound);
                }else{
                    other.setSaledatepage("");
                }
            }else{
                other.setSaledatepage("ALL");
            }          
            if(payby != null && !"".equals(payby)){
                other.setPaybypage(paybyTemp);
            }else{
                other.setPaybypage("ALL");
            }
            if(status != null && !"".equals(status)){
                other.setStatuspage(statusTemp);           
            }else{
                other.setStatuspage("ALL");           
            }
            data.add(other);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }

    @Override
    public List getOutboundHotelSummary(String hotelid, String fromdate, String todate, String saleby, String payby, String bank, String status, String city, String country, String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        String paybyname = "ALL";
        String cityname = "ALL";
        String countryname = "ALL";
        String packagename = "ALL";
        String statusname = "ALL";
        String bankname = "ALL";
        String hotelname = "ALL";
        
        String query = "SELECT * FROM `outbound_hotel_summary` ohs where ohs.hoteldate BETWEEN '"+fromdate+"' and '"+todate+"'";
        
        if((city != null) && (!"".equalsIgnoreCase(city))) {
            query += " and ohs.city = '" + city + "'" ;
            
            String querycity = "from MCity c where c.id = '"+city+"'";
            List<MCity> mCity = session.createQuery(querycity).list();
            if(!mCity.isEmpty()) {
                cityname = mCity.get(0).getName();
            }
        }
        
        if((country != null) && (!"".equalsIgnoreCase(country))) {
            query += " and ohs.country = '" + country + "'" ;
            
            String querycountry = "from MCountry c where c.id = '"+country+"'";
            List<MCountry> mCountry = session.createQuery(querycountry).list();
            if(!mCountry.isEmpty()) {
                countryname = mCountry.get(0).getName();
            }
        }
         
        if((hotelid != null) && (!"".equalsIgnoreCase(hotelid))) {
            query += " and ohs.hotelid = '" + hotelid + "'" ;
            
            String queryhotel= " from Hotel h where h.id = '"+hotelid+"'";
            List<Hotel> hotel = session.createQuery(queryhotel).list();
            if(!hotel.isEmpty()) {
                hotelname = hotel.get(0).getName();
            }
        }
        
        if((saleby != null) && (!"".equalsIgnoreCase(saleby))) {
            query += " and ohs.saleby = '" + saleby + "'" ;
        }
        
        if((payby != null) && (!"".equalsIgnoreCase(payby))) {
            query += " and ohs.payid = '" + payby + "'" ;
            
            String querypayby = "from MAccpay pay where pay.id = '"+payby+"'";
            List<MAccpay> mAccpays = session.createQuery(querypayby).list();
            if(!mAccpays.isEmpty()) {
                paybyname = mAccpays.get(0).getName();
            }
        }
       
        if((bank != null) && (!"".equalsIgnoreCase(bank))) {
            query += " and ohs.bank = '" + bank + "'" ;
            
            String querybank = "from MBank bank where bank.id = '"+bank+"'";
            List<MBank> mBank = session.createQuery(querybank).list();
            if(!mBank.isEmpty()) {
                bankname = mBank.get(0).getName();
            }
        }
        
        if((status != null) && (!"".equalsIgnoreCase(status)) && (!"-- ALL --".equalsIgnoreCase(status))) {
            query += " and ohs.status = '" + status + "'" ;
            statusname = status;
//            String querystatus = "from MItemstatus s where s.id = '"+statusId+"'";
//            List<MItemstatus> maItemstatus = session.createQuery(querystatus).list();
//            if(!maItemstatus.isEmpty()) {
//                statusname = maItemstatus.get(0).getName();
//            }
        }

        query += " ORDER BY ohs.hoteldate ";
        
        System.out.println("query : "+query);
        
        List<Object[]> QueryList = session.createSQLQuery(query)
                .addScalar("hoteldate", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("recondno", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("payby", Hibernate.STRING)
                .addScalar("hotel", Hibernate.STRING)
                .addScalar("period", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("sale", Hibernate.STRING)
                .addScalar("reference", Hibernate.STRING)
                .addScalar("status", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("totalnet", Hibernate.STRING)
                .addScalar("totalsell", Hibernate.STRING)
                .addScalar("totalprofit", Hibernate.STRING)
                .addScalar("transferdate", Hibernate.STRING)
                .addScalar("seller", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("banktransfer", Hibernate.STRING)
                .addScalar("supplier", Hibernate.STRING)
                .list();
         //util.
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy");
        SimpleDateFormat dateformatdetail = new SimpleDateFormat();
        dateformatdetail.applyPattern("dd-mm-yyyy");
        for (Object[] B : QueryList){
            OutboundHotelSummaryView outboundHotelSummaryView = new OutboundHotelSummaryView();
            outboundHotelSummaryView.setHeadcountry(countryname);
            outboundHotelSummaryView.setHeadcity(cityname);
            outboundHotelSummaryView.setHeadhotel(hotelname);
            outboundHotelSummaryView.setHeaddate(util.ConvertString(dateformat.format(util.convertStringToDate(fromdate))) + " to " + util.ConvertString(dateformat.format(util.convertStringToDate(todate))));
            outboundHotelSummaryView.setHeadpayby(paybyname);
            outboundHotelSummaryView.setHeadbanktransfer(bankname);
            outboundHotelSummaryView.setHeadstatus(statusname);
            outboundHotelSummaryView.setHoteldate(B[0] != null ? util.ConvertString(dateformat.format(util.convertStringToDate(fromdate))) : "");
            outboundHotelSummaryView.setRefno(B[1] != null ? util.ConvertString(B[1]) : "");
            outboundHotelSummaryView.setRecordno(B[2] != null ? util.ConvertString(B[2]) : "");
            outboundHotelSummaryView.setLeader(B[3] != null ? util.ConvertString(B[3]) : "");
            outboundHotelSummaryView.setPayby(B[4] != null ? util.ConvertString(B[4]) : "");
            outboundHotelSummaryView.setHotel(B[5] != null ? util.ConvertString(B[5]) : "");
            outboundHotelSummaryView.setPeriod(B[6] != null ? util.ConvertString(B[6]) : "");
            outboundHotelSummaryView.setPax(B[7] != null ? util.ConvertString(B[7]) : "0.00");
            outboundHotelSummaryView.setNet(B[8] != null ? util.ConvertString(B[8]) : "0.00");
            outboundHotelSummaryView.setSale(B[9] != null ? util.ConvertString(B[9]) : "0.00");
            outboundHotelSummaryView.setReference(B[10] != null ? util.ConvertString(B[10]) : "");
            outboundHotelSummaryView.setStatus(B[11] != null ? util.ConvertString(B[11]) : "");
            outboundHotelSummaryView.setRemark(B[12] != null ? util.ConvertString(B[12]) : "");
            outboundHotelSummaryView.setTotelnet(B[13] != null ? util.ConvertString(B[13]) : "0.00");
            outboundHotelSummaryView.setTotalsell(B[14] != null ? util.ConvertString(B[14]) : "0.00");
            outboundHotelSummaryView.setTotalprofit(B[15] != null ? util.ConvertString(B[15]) : "0.00");
            outboundHotelSummaryView.setTransferdate(B[16] != null ?  util.SetFormatDate( util.convertStringToDate(util.ConvertString(B[16])),"dd-MM-yyyy") : "");
            outboundHotelSummaryView.setSeller(B[17] != null ? util.ConvertString(B[17]) : "");
            outboundHotelSummaryView.setInvno(B[18] != null ? util.ConvertString(B[18]) : "");
            outboundHotelSummaryView.setBank(B[19] != null ? util.ConvertString(B[19]) : "");
            outboundHotelSummaryView.setSupplier(B[20] != null ? util.ConvertString(B[20]) : "");
            data.add(outboundHotelSummaryView);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
}
