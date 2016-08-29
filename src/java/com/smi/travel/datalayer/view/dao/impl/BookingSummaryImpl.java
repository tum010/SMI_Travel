/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.BookingSummaryDao;
import com.smi.travel.datalayer.view.entity.BookSummary;
import com.smi.travel.datalayer.view.entity.BookingHeaderSummaryView;
import com.smi.travel.datalayer.view.entity.BookingInvoiceView;
import com.smi.travel.datalayer.view.entity.BookingNonInvoiceView;
import com.smi.travel.datalayer.view.entity.BookingSummaryDetailView;
import com.smi.travel.datalayer.view.entity.ConfirmSlipDetailReport;
import com.smi.travel.datalayer.view.entity.ConfirmSlipHeaderReport;
import com.smi.travel.datalayer.view.entity.OutboundStaffSummaryReport;
import com.smi.travel.datalayer.view.entity.OutboundStaffSummarySubReport;
import com.smi.travel.util.UtilityFunction;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class BookingSummaryImpl implements BookingSummaryDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction utilityFunction;
    
    @Override
    public List<BookSummary> getListBookSummary(String refno) {
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        List<Object[]> QueryList =  session.createSQLQuery("SELECT * FROM `booking_summary_view` where ref_no = "+refno)
                .addScalar("type",Hibernate.STRING)
                .addScalar("description",Hibernate.STRING)
                .addScalar("date_tour",Hibernate.DATE)
                .addScalar("price",Hibernate.STRING)
                .addScalar("book_date",Hibernate.DATE)
                .list();
                
        List<BookSummary> BookSummaryList =  new LinkedList<BookSummary>();
        for(Object[] B : QueryList){
            BookSummary book = new BookSummary();
            book.setRefNo(refno);
            book.setType(String.valueOf(B[0]));
            book.setDescription(String.valueOf(B[1]));
            book.setDateTour((Date) B[2]);
            book.setPrice(B[3] != null ? util.convertStringToDecimalFormat(String.valueOf(B[3])) : "");
            book.setBookdate((Date) B[4]);
            BookSummaryList.add(book);
            
        }
       
        if (BookSummaryList.isEmpty()) {
            return null;
        }
        session.close();
        return BookSummaryList;
    }
    
    @Override
    public BookingHeaderSummaryView getBookingSummaryReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        BookingHeaderSummaryView booksummary = new BookingHeaderSummaryView();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy hh:mm");
        
//        String query = "SELECT * FROM `booking_header_summary` where  refno = '"+refno+"'";
        String query = "  SELECT `mt`.`Reference No` AS `refno`, `mt`.`DepartmentNo` AS `departmentNo`, `agt`.`name` AS `agent`, `GET_LEADER_NAME` (`mt`.`id`) AS `leader`, concat(( CASE WHEN ( isnull(`mt`.`Adult`) OR (`mt`.`Adult` = 0)) THEN '-' ELSE `mt`.`Adult` END ), ' ADT ', ( CASE WHEN ( isnull(`mt`.`Child`) OR (`mt`.`Child` = 0)) THEN '-' ELSE `mt`.`Child` END ), ' CHD ', ( CASE WHEN ( isnull(`mt`.`Infant`) OR (`mt`.`Infant` = 0)) THEN '-' ELSE `mt`.`Infant` END ), ' INF ' ) AS `pax`, `sale`.`name` AS `sale`, `bill`.`bill_name` AS `billname`, `bill`.`bill_address` AS `address`, `term`.`name` AS `termpay`, ( CASE WHEN (`pay`.`id` = 4) THEN concat( 'BTF', ' : ', date_format( `bill`.`transfer_date`, '%d/%m/%Y' ), ' ( ', `bank`.`code`, ' ) ' ) ELSE `pay`.`name` END ) AS `payby`, `cus`.`tel` AS `telfax`, `mbook`.`name` AS `bookstatus`, ( SELECT min(`fde`.`depart_date`) AS `firstdept` FROM `first_depart_booking` `fde` WHERE ( `fde`.`refno` = `mt`.`Reference No` )) AS `firstdept`, `pt`.`name` AS `package` FROM (((((((((( `master` `mt` JOIN `agent` `agt` ON ((`agt`.`id` = `mt`.`Agent_id`))) JOIN `staff` `sale` ON (( `sale`.`username` = `mt`.`Create_by` ))) LEFT JOIN `billable` `bill` ON (( `bill`.`master_id` = `mt`.`id` ))) LEFT JOIN `m_accterm` `term` ON (( `bill`.`term_pay` = `term`.`id` ))) LEFT JOIN `m_accpay` `pay` ON ((`pay`.`id` = `bill`.`pay_by`))) JOIN `m_bookingstatus` `mbook` ON ((`mbook`.`id` = `mt`.`Status`))) LEFT JOIN `package_tour` `pt` ON (( `pt`.`id` = `mt`.`package_id` ))) JOIN `passenger` `pg` ON (((`pg`.`is_leader` = 1) AND (`pg`.`master_id` = `mt`.`id`)))) JOIN `customer` `cus` ON (( `cus`.`id` = `pg`.`customer_id` ))) LEFT JOIN `m_bank` `bank` ON (( `bank`.`id` = `bill`.`acc_id` )))  where  `mt`.`Reference No`  = '"+refno+"' GROUP BY `mt`.`Reference No` ";
        System.out.println(" query :::: " +query);
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("sale", Hibernate.STRING)
                .addScalar("billname", Hibernate.STRING)
                .addScalar("address", Hibernate.STRING)
                .addScalar("termpay", Hibernate.STRING)
                .addScalar("payby", Hibernate.STRING)
                .addScalar("departmentNo", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryStaffList) {
            booksummary.setSystemdate(String.valueOf(dateformat.format(new Date())));
            String refnum = "";
            if(B[0] != null){ 
                refnum = util.ConvertString(B[0]);
            }
            if(B[9] != null){
                refnum += "/"+util.ConvertString(B[9]);
            }
            
            booksummary.setRefno(refnum);
            booksummary.setAgent(B[1]== null ? "" :util.ConvertString(B[1]));
            booksummary.setLeader(B[2]== null ? "" :util.ConvertString(B[2]));
            booksummary.setPax(B[3]== null ? "" :util.ConvertString(B[3]));
            booksummary.setSale(B[4]== null ? "" :util.ConvertString(B[4]));
            booksummary.setBillto(B[5]== null ? "" :util.ConvertString(B[5]));
            booksummary.setAddress(B[6]== null ? "" :util.ConvertString(B[6]));
            booksummary.setTermpay(B[7]== null ? "" :util.ConvertString(B[7]));
            booksummary.setPayby(B[8]== null ? "" :util.ConvertString(B[8]));
        }
        
        booksummary.setBookingSummaryFlightSubReportDataSource(new JRBeanCollectionDataSource(getBookingSummaryFlightSubReport(refno)));
        booksummary.setBookingSummaryHotelSubReportDataSource(new JRBeanCollectionDataSource(getBookingSummaryHotelSubReport(refno)));
        booksummary.setBookingSummaryOtherSubReportDataSource(new JRBeanCollectionDataSource(getBookingSummaryOtherSubReport(refno))); //package
        booksummary.setBookingSummaryLandSubReportDataSource(new JRBeanCollectionDataSource(getBookingSummaryLandSubReport(refno)));
        booksummary.setBookingSummaryPassengerSubReportDataSource(new JRBeanCollectionDataSource(getBookingSummaryPassengerSubReport(refno)));

        this.sessionFactory.close();
        session.close();
        return booksummary;
    }
    
    public List getBookingSummaryFlightSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
//        String query = " SELECT * FROM `booking_air_summary_min` a JOIN booking_air_value_summary b ON a.refno = b.ref_no WHERE a.refno = '"+refno+"'";
        String query = " SELECT b.*, billd.id AS billid, bav.* FROM ( SELECT `mt`.`Reference No` AS `refno`, date_format( `mt`.`Create_date`, '%d-%m-%Y' ) AS `refdate`, `agt`.`name` AS `agent`, `GET_LEADER_NAME` (`mt`.`id`) AS `leader`, ( SELECT count(0) FROM `airticket_passenger` `ap` WHERE ( `ap`.`airline_id` = `aa`.`id` )) AS `pax`, `pnr`.`pnr` AS `pnr`, `af`.`des_code` AS `arrv`, `af`.`source_code` AS `dept`, date_format( `af`.`depart_date`, '%d-%m-%Y' ) AS `depart_date`, ( CASE WHEN ( `af`.`depart_time` IS NOT NULL ) THEN concat( substr(`af`.`depart_time`, 1, 2), ':', substr(`af`.`depart_time`, 3, 4)) ELSE NULL END ) AS `depart_time`, date_format( `af`.`arrive_date`, '%d-%m-%Y' ) AS `arrive_date`, ( CASE WHEN ( `af`.`arrive_time` IS NOT NULL ) THEN concat( substr(`af`.`arrive_time`, 1, 2), ':', substr(`af`.`arrive_time`, 3, 4)) ELSE NULL END ) AS `arrive_time`, ( CASE WHEN (( `af`.`filght_class` IS NOT NULL ) AND ( `af`.`sub_flight_class` IS NOT NULL )) THEN concat( `mfli`.`name`, ' (', `af`.`sub_flight_class`, ')' ) ELSE `mfli`.`name` END ) AS `class`, `af`.`flight_no` AS `flight`, `aa`.`id` AS `airline_id` FROM (((((( `master` `mt` JOIN `agent` `agt` ON ((`agt`.`id` = `mt`.`Agent_id`))) JOIN `airticket_booking` `ab` ON ((`ab`.`master_id` = `mt`.`id`))) JOIN `airticket_pnr` `pnr` ON (( `pnr`.`booking_id` = `ab`.`id` ))) JOIN `airticket_airline` `aa` ON ((`aa`.`pnr_id` = `pnr`.`id`))) JOIN `airticket_flight` `af` ON (( `af`.`airline_id` = `aa`.`id` ))) LEFT JOIN `m_flight` `mfli` ON (( `mfli`.`id` = `af`.`filght_class` )))) b LEFT JOIN ( SELECT billd.id, billd.ref_item_id FROM billable_desc billd WHERE billd.bill_type = 1 ) billd ON billd.ref_item_id = b.airline_id JOIN booking_air_value_summary bav ON b.refno = bav.ref_no WHERE b.refno = '"+refno+"' " ;
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("flight", Hibernate.STRING)
                .addScalar("dept", Hibernate.STRING)
                .addScalar("depart_date", Hibernate.STRING)
                .addScalar("depart_time", Hibernate.STRING)
                .addScalar("arrv", Hibernate.STRING)
                .addScalar("arrive_date", Hibernate.STRING)
                .addScalar("arrive_time", Hibernate.STRING)
                .addScalar("class", Hibernate.STRING)
                .addScalar("cost", Hibernate.STRING)
                .addScalar("cost_tax", Hibernate.STRING)
                .addScalar("cur_cost", Hibernate.STRING)
                .addScalar("price", Hibernate.STRING)
                .addScalar("price_tax", Hibernate.STRING)
                .addScalar("cur_amount", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        
        for (Object[] B : QueryStaffList) {
            BookingSummaryDetailView booksumdetail = new BookingSummaryDetailView();
            booksumdetail.setFlight(B[0]== null ? "" :util.ConvertString(B[0]));
            booksumdetail.setDepart(B[1]== null ? "" :util.ConvertString(B[1]));
            booksumdetail.setDepartdate(B[2]== null ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2])))));
            booksumdetail.setDeparttime(B[3]== null ? "" :util.ConvertString(B[3]));
            booksumdetail.setArrive(B[4]== null ? "" :util.ConvertString(B[4]));
            booksumdetail.setArrivedate(B[5]== null ? "" :util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[5])))));
            booksumdetail.setArrivetime(B[6]== null ? "" :util.ConvertString(B[6]));
            booksumdetail.setFlightclass(B[7]== null ? "" :util.ConvertString(B[7]));
            booksumdetail.setCost(B[8]== null ? "" :util.ConvertString(B[8]));
            booksumdetail.setCosttax(B[9]== null ? "" :util.ConvertString(B[9]));
            booksumdetail.setCurcost(B[10]== null ? "" :util.ConvertString(B[10]));
            booksumdetail.setPrice(B[11]== null ? "" :util.ConvertString(B[11]));
            booksumdetail.setPricetax(B[12]== null ? "" :util.ConvertString(B[12]));
            booksumdetail.setCuramount(B[13]== null ? "" :util.ConvertString(B[13]));
            data.add(booksumdetail);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getBookingSummaryHotelSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();

        String query = " select `mt`.`Reference No` AS `refno`,date_format(`mt`.`Create_date`,'%d-%m-%Y') AS `refdate`,`agt`.`name` AS `agent`,`GET_LEADER_NAME`(`mt`.`id`) AS `leader`,`ht`.`name` AS `hotel`,date_format(`hb`.`checkin`,'%d-%m-%Y') AS `checkin`,date_format(`hb`.`checkout`,'%d-%m-%Y') AS `checkout`,(to_days(`hb`.`checkout`) - to_days(`hb`.`checkin`)) AS `night`,((ifnull((select sum((`hr`.`cost` * `hr`.`qty`)) from `hotel_room` `hr` where (`hr`.`booking_hotel_id` = `hb`.`id`)),0) * (to_days(`hb`.`checkout`) - to_days(`hb`.`checkin`))) + ifnull((select sum(`hr`.`cost`) from `hotel_request` `hr` where (`hr`.`booking_hotel_id` = `hb`.`id`)),0)) AS `Total_cost`,`hb`.`cur_cost` AS `curcost`,((ifnull((select sum((`hr`.`price` * `hr`.`qty`)) from `hotel_room` `hr` where (`hr`.`booking_hotel_id` = `hb`.`id`)),0) * (to_days(`hb`.`checkout`) - to_days(`hb`.`checkin`))) + ifnull((select sum(`hr`.`price`) from `hotel_request` `hr` where (`hr`.`booking_hotel_id` = `hb`.`id`)),0)) AS `Total_price`,`hb`.`cur_amount` AS `curamount`,`hb`.`id` AS `id`,`hb`.`supplier` AS `supplier`,`hb`.`remark` AS `remark`,`billd`.`id` AS `billid` from ((((`hotel_booking` `hb` join `master` `mt` on((`mt`.`id` = `hb`.`master_id`))) join `agent` `agt` on((`agt`.`id` = `mt`.`Agent_id`))) join `hotel` `ht` on((`ht`.`id` = `hb`.`hotel_id`))) left join `billable_desc` `billd` on(((`billd`.`ref_item_id` = `hb`.`id`) and (`billd`.`bill_type` = 4)))) where `mt`.`Reference No` = '"+refno+"' group by `hb`.`id` order by `mt`.`Reference No` desc ";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("hotel", Hibernate.STRING)
                .addScalar("checkin", Hibernate.STRING)
                .addScalar("checkout", Hibernate.STRING)
                .addScalar("night", Hibernate.STRING)
                .addScalar("Total_cost", Hibernate.STRING)
                .addScalar("curcost", Hibernate.STRING)
                .addScalar("Total_price", Hibernate.STRING)
                .addScalar("curamount", Hibernate.STRING)
                .addScalar("supplier", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        
        for (Object[] B : QueryStaffList) {
            BookingSummaryDetailView booksumdetail = new BookingSummaryDetailView();
            String checkinout = "" ;
            booksumdetail.setHotel(B[0]== null ? "" :util.ConvertString(B[0]));
            if(B[1] != null && B[2] != null){
                checkinout = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[1]))))
                        + " - " + util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2]))));
            }
            booksumdetail.setCheckinout(checkinout);
            booksumdetail.setNight(B[3]== null ? "" :util.ConvertString(B[3]));
            booksumdetail.setTotalcost(B[4]== null ? "" :util.ConvertString(B[4]));
            booksumdetail.setCurcost(B[5]== null ? "" :util.ConvertString(B[5]));
            booksumdetail.setTotalprice(B[6]== null ? "" :util.ConvertString(B[6]));
            booksumdetail.setCuramount(B[7]== null ? "" :util.ConvertString(B[7]));
            booksumdetail.setSupplier(B[8]== null ? "" :util.ConvertString(B[8]));
            data.add(booksumdetail);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getBookingSummaryOtherSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `other_booking_view` WHERE refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("name", Hibernate.STRING)
                .addScalar("other_date", Hibernate.STRING)
                .addScalar("other_time", Hibernate.STRING)
                .addScalar("adult", Hibernate.STRING)
                .addScalar("child", Hibernate.STRING)
                .addScalar("infant", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("cur_cost", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("cur_amount", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        
        for (Object[] B : QueryStaffList) {
            BookingSummaryDetailView booksumdetail = new BookingSummaryDetailView();
            booksumdetail.setName(B[0]== null ? "" :util.ConvertString(B[0]));
            String datetime = "" ;
            if(B[1] != null){
                datetime = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[1]))));
            }
            if(B[2] != null){
                datetime +=  " " + util.ConvertString(B[2]);
            }
            booksumdetail.setOtherdatetime(datetime);
            booksumdetail.setAdult(B[3]== null ? "" :util.ConvertString(B[3]));
            booksumdetail.setChild(B[4]== null ? "" :util.ConvertString(B[4]));
            booksumdetail.setInfant(B[5]== null ? "" :util.ConvertString(B[5]));
            booksumdetail.setNet(B[6]== null ? "" :util.ConvertString(B[6]));
            booksumdetail.setCurcost(B[7]== null ? "" :util.ConvertString(B[7]));
            booksumdetail.setSell(B[8]== null ? "" :util.ConvertString(B[8]));
            booksumdetail.setCuramount(B[9]== null ? "" :util.ConvertString(B[9]));
            booksumdetail.setRemark(B[10]== null ? "" :util.ConvertString(B[10]));
            data.add(booksumdetail);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    public List getBookingSummaryLandSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `land_booking_view` where refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("category", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .addScalar("net", Hibernate.STRING)
                .addScalar("cur_cost", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("cur_amount", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        
        for (Object[] B : QueryStaffList) {
            BookingSummaryDetailView booksumdetail = new BookingSummaryDetailView();
            booksumdetail.setAgent(B[0]== null ? "" :util.ConvertString(B[0]));
            booksumdetail.setCategory(B[1]== null ? "" :util.ConvertString(B[1]));
            booksumdetail.setDescription(B[2]== null ? "" : util.ConvertString(B[2]));
            booksumdetail.setNet(B[3]== null ? "" :util.ConvertString(B[3]));
            booksumdetail.setCurcost(B[4]== null ? "" :util.ConvertString(B[4]));
            booksumdetail.setSell(B[5]== null ? "" :util.ConvertString(B[5]));
            booksumdetail.setCuramount(B[6]== null ? "" :util.ConvertString(B[6]));
            data.add(booksumdetail);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    public List getBookingSummaryPassengerSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        
        String query = "SELECT * FROM `booking_passenger_view` where refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("name", Hibernate.STRING)
                .addScalar("age", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .list();
        
        int no = 1 ;
        for (Object[] B : QueryStaffList) {
            BookingSummaryDetailView booksumdetail = new BookingSummaryDetailView();
            booksumdetail.setNo(String.valueOf(no));
            booksumdetail.setName(B[0]== null ? "" :util.ConvertString(B[0]));
            booksumdetail.setAge(B[1]== null ? "" :util.ConvertString(B[1]));
            booksumdetail.setRemark(B[2]== null ? "" : util.ConvertString(B[2]));
            data.add(booksumdetail);
            no++; 
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
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
    public ConfirmSlipHeaderReport getConfirmSlipHeaderReport(String refno,String user) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        ConfirmSlipHeaderReport confirmSlip = new ConfirmSlipHeaderReport();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy hh:mm");
        
//        String query = "SELECT * FROM `booking_header_summary` where  refno = '"+refno+"'";
        String query = " select `mt`.`Reference No` AS `refno`,`mt`.`DepartmentNo` AS `departmentNo`,`agt`.`name` AS `agent`,`GET_LEADER_NAME`(`mt`.`id`) AS `leader`,concat((case when (isnull(`mt`.`Adult`) or (`mt`.`Adult` = 0)) then '-' else `mt`.`Adult` end),' ADT ',(case when (isnull(`mt`.`Child`) or (`mt`.`Child` = 0)) then '-' else `mt`.`Child` end),' CHD ',(case when (isnull(`mt`.`Infant`) or (`mt`.`Infant` = 0)) then '-' else `mt`.`Infant` end),' INF ') AS `pax`,`sale`.`name` AS `sale`,`bill`.`bill_name` AS `billname`,`bill`.`bill_address` AS `address`,`term`.`name` AS `termpay`,(case when (`pay`.`id` = 4) then concat('BTF',' : ',date_format(`bill`.`transfer_date`,'%d/%m/%Y'),' ( ',`bank`.`code`,' ) ') else `pay`.`name` end) AS `payby`,`cus`.`tel` AS `telfax`,`mbook`.`name` AS `bookstatus`,min(`fde`.`depart_date`) AS `firstdept`,`pt`.`name` AS `package` from (((((((((((`master` `mt` join `agent` `agt` on((`agt`.`id` = `mt`.`Agent_id`))) join `staff` `sale` on((`sale`.`username` = `mt`.`Create_by`))) left join `billable` `bill` on((`bill`.`master_id` = `mt`.`id`))) left join `m_accterm` `term` on((`bill`.`term_pay` = `term`.`id`))) left join `m_accpay` `pay` on((`pay`.`id` = `bill`.`pay_by`))) join `m_bookingstatus` `mbook` on((`mbook`.`id` = `mt`.`Status`))) left join `first_depart_booking` `fde` on((`fde`.`refno` = `mt`.`Reference No`))) left join `package_tour` `pt` on((`pt`.`id` = `mt`.`package_id`))) join `passenger` `pg` on(((`pg`.`is_leader` = 1) and (`pg`.`master_id` = `mt`.`id`)))) join `customer` `cus` on((`cus`.`id` = `pg`.`customer_id`))) left join `m_bank` `bank` on((`bank`.`id` = `bill`.`acc_id`))) where  `mt`.`Reference No`  = '"+refno+"' group by `mt`.`Reference No` ";

        System.out.println(" query :::: " +query);
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("address", Hibernate.STRING)
                .addScalar("telfax", Hibernate.STRING)
                .addScalar("bookstatus", Hibernate.STRING)
                .addScalar("sale", Hibernate.STRING)
                .addScalar("firstdept", Hibernate.STRING)
                .addScalar("package", Hibernate.STRING)
                .list();

        for (Object[] B : QueryStaffList) {
            confirmSlip.setUser(user);
            System.out.println("confirmSlip " +confirmSlip.getUser());
            confirmSlip.setSystemdate(String.valueOf(dateformat.format(new Date())));
            confirmSlip.setRefno(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmSlip.setAgent(B[1]== null ? "" :util.ConvertString(B[1]));
            confirmSlip.setLeader(B[2]== null ? "" :util.ConvertString(B[2]));
            confirmSlip.setAddress(B[3]== null ? "" :util.ConvertString(B[3]));
            confirmSlip.setTelfax(B[4]== null ? "" :util.ConvertString(B[4]));
            confirmSlip.setBookstatus(B[5]== null ? "" :util.ConvertString(B[5]));
            confirmSlip.setIncharge(B[6]== null ? "" :util.ConvertString(B[6]));
            Date firstDept = (B[7]== null ? new Date() : util.convertStringToDate(util.ConvertString(B[7])));
            confirmSlip.setFirstdept(B[7]== null ? "" : sdf.format(firstDept));
            confirmSlip.setPackages(B[8]== null ? "" :util.ConvertString(B[8]));
        }
        
        confirmSlip.setConfirmSlipFlightSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipFlightSubReport(refno)));
        confirmSlip.setConfirmSlipHotelSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipHotelSubReport(refno)));
        confirmSlip.setConfirmSlipDaytourSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipDaytourSubReport(refno))); 
        confirmSlip.setConfirmSlipOtherSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipOtherSubReport(refno)));
        confirmSlip.setConfirmSlipLandSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipLandSubReport(refno)));
        confirmSlip.setConfirmSlipPassengerSubReportDataSource(new JRBeanCollectionDataSource(getConfirmSlipPassengerSubReport(refno)));
        
        this.sessionFactory.close();
        session.close();
        return confirmSlip;    
    }

    public List getConfirmSlipFlightSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `airticket_booking_view` a WHERE a.refno = '"+refno+"'";

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("airline", Hibernate.STRING)
                .addScalar("pnr", Hibernate.STRING)
                .addScalar("flight", Hibernate.STRING)
                .addScalar("dept", Hibernate.STRING)
                .addScalar("deptname", Hibernate.STRING)
                .addScalar("depart_time", Hibernate.STRING)
                .addScalar("arrv", Hibernate.STRING)
                .addScalar("arrvname", Hibernate.STRING)
                .addScalar("arrive_time", Hibernate.STRING)
//                .addScalar("price", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        int order = 1 ;
        for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setOrder(String.valueOf(order));
            order ++ ;
            
            confirmdetail.setAirline(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setPnr(B[1]== null ? "" :util.ConvertString(B[1]));
            confirmdetail.setFlight(B[2]== null ? "" :util.ConvertString(B[2]));
            confirmdetail.setDepart(B[3]== null ? "" :util.ConvertString(B[3]));
            confirmdetail.setDepartname(B[4]== null ? "" :util.ConvertString(B[4]));
            String deptime1 = util.ConvertString(B[5]).substring(0,2);
            String deptime2 = util.ConvertString(B[5]).substring(2,4);
            String arrtime1 = util.ConvertString(B[8]).substring(0,2);
            String arrtime2 = util.ConvertString(B[8]).substring(2,4);
            confirmdetail.setDeparttime(deptime1+":"+deptime2 + " - " +arrtime1+":"+arrtime2 );
            confirmdetail.setArrive(B[6]== null ? "" :util.ConvertString(B[6]));
            confirmdetail.setArrivename(B[7]== null ? "" :util.ConvertString(B[7]));

            confirmdetail.setArrivetime(B[8]== null ? "" :util.ConvertString(B[8]));
//            confirmdetail.setPrice(B[9]== null ? "" :util.ConvertString(B[9]));
            data.add(confirmdetail);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getConfirmSlipHotelSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();

        String query = "SELECT * FROM `hotel_booking_view` a where a.refno = '"+refno+"' ";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("name", Hibernate.STRING)
                .addScalar("checkin", Hibernate.STRING)
                .addScalar("checkout", Hibernate.STRING)
                .addScalar("room", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("cur", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MMM-yyyy");
        String checkinout = "";
        int order = 1 ;
        for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setOrder(String.valueOf(order));
            order ++ ;
            
            confirmdetail.setHotel(B[0]== null ? "" :util.ConvertString(B[0]));
            if(B[1] != null && B[2] != null){
                checkinout = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[1]))))
                        + " - " + util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2]))));
            }
            
            confirmdetail.setCheckinout(checkinout);
            confirmdetail.setCategory(B[3]== null ? "" :util.ConvertString(B[3]));
            confirmdetail.setAmount(B[4]== null ? "" :util.ConvertString(B[4]));
            confirmdetail.setCuramount(B[5]== null ? "" :util.ConvertString(B[5]));
            data.add(confirmdetail);
        }
        
        String queryAdditional = "SELECT * FROM `hotel_booking_additional_view` a where a.refno = '"+refno+"' ";
        
        List<Object[]> QueryList = session.createSQLQuery(queryAdditional)
                .addScalar("description", Hibernate.STRING)
                .addScalar("add_price", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("curcost", Hibernate.STRING)
                .addScalar("curamount", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setOrder(String.valueOf(order));
            order ++ ;
            confirmdetail.setHotel(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setAmount(B[1]== null ? "" :util.ConvertString(B[1]));
            confirmdetail.setCuramount(B[4] == null ? "" :util.ConvertString(B[4]));
            data.add(confirmdetail);
        }

        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getConfirmSlipDaytourSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `daytour_booking_view` WHERE refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("tour_date", Hibernate.STRING)
                .addScalar("qty", Hibernate.STRING)
                .addScalar("total", Hibernate.STRING)
                .addScalar("curamount", Hibernate.STRING)
                .addScalar("pickup", Hibernate.STRING)
                .addScalar("pickup_time", Hibernate.STRING)
                .addScalar("guide", Hibernate.STRING)
                .addScalar("carno", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        
        int order = 1 ;
        for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setOrder(String.valueOf(order));
            order ++ ;
            
            confirmdetail.setCode(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setDescription(B[1]== null ? "" :util.ConvertString(B[1]));
            
            String datetime = "" ;
            if(B[2] != null){
                datetime = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2]))));
            }

            confirmdetail.setDate(datetime);
            confirmdetail.setConfirmQty(B[3]== null ? "" :util.ConvertString(B[3]));
            confirmdetail.setAmount(B[4]== null ? "" :util.ConvertString(B[4]));
            confirmdetail.setCuramount(B[5]== null ? "" :util.ConvertString(B[5]));
            confirmdetail.setPickup(B[6]== null ? "" :util.ConvertString(B[6]));
            confirmdetail.setPickuptime(B[7]== null ? "" :util.ConvertString(B[7]));
            confirmdetail.setGuide(B[8]== null ? "" :util.ConvertString(B[8]));
            confirmdetail.setCarno(B[9]== null ? "" :util.ConvertString(B[9]));
            data.add(confirmdetail);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
    public List getConfirmSlipOtherSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `other_booking_view` WHERE refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("other_date", Hibernate.STRING)
                .addScalar("other_time", Hibernate.STRING)
                .addScalar("qty", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("cur_amount", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        
         for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setCode(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setDescription(B[1]== null ? "" :util.ConvertString(B[1]));
            String datetime = "" ;
            if(B[2] != null){
                datetime = util.ConvertString(dateformat.format(util.convertStringToDate(util.ConvertString(B[2]))));
            }
            if(B[3] != null){
                datetime +=  " " + util.ConvertString(B[3]);
            }
            confirmdetail.setOtherdate(datetime);
            confirmdetail.setQty(B[4]== null ? "" :util.ConvertString(B[4]));
            confirmdetail.setTotalsell(B[5]== null ? "" :util.ConvertString(B[5]));
            confirmdetail.setCuramount(B[6]== null ? "" :util.ConvertString(B[6]));
            data.add(confirmdetail);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    public List getConfirmSlipLandSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `land_booking_view` where refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("category", Hibernate.STRING)
                .addScalar("okby", Hibernate.STRING)
                .addScalar("sell", Hibernate.STRING)
                .addScalar("cur_amount", Hibernate.STRING)
                .list();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd MMM yyyy");
        
        for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setCategory(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setOkby(B[1]== null ? "" :util.ConvertString(B[1]));
            confirmdetail.setSell(B[2]== null ? "" : util.ConvertString(B[2]));
            confirmdetail.setCursell(B[3]== null ? "" :util.ConvertString(B[3]));
            data.add(confirmdetail);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    public List getConfirmSlipPassengerSubReport(String refno) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        
        String query = "SELECT * FROM `booking_passenger_view` where refno = '"+refno+"'";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("tel", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("age", Hibernate.STRING)
                .addScalar("isleader", Hibernate.STRING)
                .list();
        
        int order = 1 ;
        for (Object[] B : QueryStaffList) {
            ConfirmSlipDetailReport confirmdetail = new ConfirmSlipDetailReport();
            confirmdetail.setOrder(String.valueOf(order));
            order ++ ;
            confirmdetail.setTel(B[0]== null ? "" :util.ConvertString(B[0]));
            confirmdetail.setName(B[1]== null ? "" :util.ConvertString(B[1]));
            confirmdetail.setAge(B[2]== null ? "" : util.ConvertString(B[2]));
            confirmdetail.setLeader(B[3]== null ? "" : util.ConvertString(B[3]));
            data.add(confirmdetail);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }

    @Override
    public OutboundStaffSummaryReport getOutboundStaffSummaryReport(String from, String to, String saleby, String currency, String detail, String user) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy hh:mm");
        
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd MMM yyyy");
        
        OutboundStaffSummaryReport ossr = new OutboundStaffSummaryReport();
        
        String fromto = "";
        if(!"".equalsIgnoreCase(from) && !"".equalsIgnoreCase(to)){
            fromto = String.valueOf(df.format(util.convertStringToDate(from))) 
                    + " to " + String.valueOf(df.format(util.convertStringToDate(to))) ;
        }
        ossr.setUser(user);
        ossr.setSystemdate(String.valueOf(dateformat.format(new Date())));
        ossr.setFromto(fromto);

        ossr.setOutboundStaffSummaryListReportDataSource(new JRBeanCollectionDataSource(getOutboundStaffSummaryList(from,to,saleby,currency)));
        
        if("1".equalsIgnoreCase(detail)){
            ossr.setOutboundStaffSummaryDetailReportDataSource(new JRBeanCollectionDataSource(getOutboundStaffSummaryDetail(from,to,saleby,currency)));
        }else{
            ossr.setOutboundStaffSummaryDetailReportDataSource(null);
        }
        
        this.sessionFactory.close();
        session.close();
        return ossr;        
    }
    
    public List getOutboundStaffSummaryList(String from, String to, String saleby, String currency) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT st.`name` AS staff, pt. NAME AS productType, sum(bd.cost) * ifnull(bd.ex_rate, 1) AS cost, sum(bd.price) AS price, sum(bd.price) - (sum(bd.cost) * ifnull(bd.ex_rate, 1)) AS profit FROM `billable` bi INNER JOIN billable_desc bd ON bi.id = bd.billable_id INNER JOIN `master` mt ON mt.id = bi.master_id INNER JOIN m_billtype pt ON pt.id = bd.bill_type INNER JOIN staff st ON st.id = mt.Staff_id WHERE mt.booking_type = 'O' AND bd.bill_type <> 6 AND mt.Create_date BETWEEN '"+from+"' AND '"+to+"' ";
        
        if(saleby != null && !"".equalsIgnoreCase(saleby)){
            query += " and st.id = '"+saleby+"'";
        }
        
        if(currency != null && !"".equalsIgnoreCase(currency)){
            query += " and bd.currency = '"+currency+"'";
        }
        
        query += " and st.department_id = 2 GROUP BY mt.Staff_id , pt.id ORDER BY st.`name`";

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("staff", Hibernate.STRING)
                .addScalar("productType", Hibernate.STRING)
                .addScalar("cost", Hibernate.STRING)
                .addScalar("price", Hibernate.STRING)
                .addScalar("profit", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryStaffList) {
            OutboundStaffSummarySubReport osssr = new OutboundStaffSummarySubReport();
            osssr.setStaff(B[0]== null ? "" :util.ConvertString(B[0]));
            osssr.setProducttype(B[1]== null ? "" :util.ConvertString(B[1]));
            osssr.setNet(B[2]== null ? "" : util.ConvertString(B[2]));
            osssr.setSale(B[3]== null ? "" : util.ConvertString(B[3]));
            osssr.setProfit(B[4]== null ? "" : util.ConvertString(B[4]));
            data.add(osssr);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
     
    public List getOutboundStaffSummaryDetail(String from, String to, String saleby, String currency) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        
        String query = "SELECT * FROM `outbound_staff_detail` where bookdate BETWEEN '"+from+"' and  '"+to+"' ";
        
        if(saleby != null && !"".equalsIgnoreCase(saleby)){
            query += " and staffid = '"+saleby+"'";
        }
        
        if(currency != null && !"".equalsIgnoreCase(currency)){
            query += " and currency = '"+currency+"'";
        }
        
        query += " ORDER BY staff , departmentno ";
        
        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("staff", Hibernate.STRING)
                .addScalar("productType", Hibernate.STRING)
                .addScalar("productname", Hibernate.STRING)
                .addScalar("cost", Hibernate.STRING)
                .addScalar("price", Hibernate.STRING)
                .addScalar("profit", Hibernate.STRING)
                .addScalar("departmentno", Hibernate.STRING)
                .list();

        for (Object[] B : QueryStaffList) {
            OutboundStaffSummarySubReport osssr = new OutboundStaffSummarySubReport();
            osssr.setStaff(B[0]== null ? "" :util.ConvertString(B[0]));
            osssr.setType(B[1]== null ? "" :util.ConvertString(B[1]));
            osssr.setName(B[2]== null ? "" : util.ConvertString(B[2]));
            osssr.setNet(B[3]== null ? "" : util.ConvertString(B[3]));
            osssr.setSale(B[4]== null ? "" : util.ConvertString(B[4]));
            osssr.setProfit(B[5]== null ? "" : util.ConvertString(B[5]));
            osssr.setDepartmentno(B[6]== null ? "" : util.ConvertString(B[6]));
            data.add(osssr);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }

    @Override
    public List getBookingInvoiceReport(String owner,String invto,String bookdatefrom,String bookdateto,String invdatefrom,String invdateto,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        int check = 0 ;
        
        boolean ownersearch = false;
        boolean invtosearch = false;
        
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        DecimalFormat df = new DecimalFormat("#.00"); 
        
        String headerowner = "ALL";
        String headerbookdate = "ALL";
        String headerinvto = "ALL";
        String headerinvdate = "ALL";
        
        String query = "SELECT * FROM `booking_invoice` where ";
        
        if((bookdatefrom != null && !"".equalsIgnoreCase(bookdatefrom)) && (bookdateto != null && !"".equalsIgnoreCase(bookdateto))){
            query += " bookdate BETWEEN '"+bookdatefrom+"' and  '"+bookdateto+"' ";
            check = 1;
            headerbookdate = util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(bookdatefrom)))) + " To " +
                    util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(bookdateto))));
        }
        
        if((invdatefrom != null && !"".equalsIgnoreCase(invdatefrom)) && (invdateto != null && !"".equalsIgnoreCase(invdateto))){
            if(check == 1){  query += " and "; }
            query += " invdate BETWEEN '"+invdatefrom+"' and  '"+invdateto+"' ";
            check = 1;
            headerinvdate = util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(invdatefrom)))) + " To " +
                    util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(invdateto))));
        }
        
        if((owner != null && !"".equalsIgnoreCase(owner))){
            if(check == 1){  query += " and "; }
            query += " ownercode = '"+owner+"'";
            check = 1;
            ownersearch = true;
        }
        
        if((invto != null && !"".equalsIgnoreCase(invto))){
            if(check == 1){  query += " and "; }
            query += " invcode like '%"+invto+"%'";
            check = 1;
            invtosearch = true;
        }
        
        query += " ORDER BY refno , bookdate ";

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("owner", Hibernate.STRING)
                .addScalar("bookdate", Hibernate.STRING)
                .addScalar("invno", Hibernate.STRING)
                .addScalar("invdate", Hibernate.STRING)
                .addScalar("invto", Hibernate.STRING)
                .addScalar("cost", Hibernate.STRING)
                .addScalar("currency", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .list();
        
        
        for (Object[] B : QueryStaffList) {
            BookingInvoiceView biv = new BookingInvoiceView();
            biv.setHeaderbookingdate(headerbookdate);
            biv.setHeaderinvdate(headerinvdate);
            if(ownersearch){
                biv.setHeaderowner(B[1]== null ? "" :util.ConvertString(B[1]));
            }else{
                biv.setHeaderowner(headerowner);
            }
            if(invtosearch){
                biv.setHeaderinvto(B[5]== null ? "" : util.ConvertString(B[5]));
            }else{
                biv.setHeaderinvto(headerinvto);
            }
            biv.setRefno(B[0]== null ? "" :util.ConvertString(B[0]));
            biv.setOwner(B[1]== null ? "" :util.ConvertString(B[1]));
            biv.setBookdate(util.convertStringToDateFormat(String.valueOf(B[2])));
//            biv.setBookdate("null".equals(String.valueOf(B[2])) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[2])))));
            biv.setInvno(B[3]== null ? "" : util.ConvertString(B[3]));
//            if((!"null".equalsIgnoreCase(String.valueOf(B[4])) && B[4] != null) && !"".equalsIgnoreCase(String.valueOf(B[4]))){
//                String invdatemp="";
//                String invDate[] = String.valueOf(B[4]).split("\r\n");
//                if(invDate.length > 1 ){
//                   for(int x= 0; x<invDate.length;x++){
//                       if(invDate[x] != null && !"".equalsIgnoreCase(invDate[x].trim())){
//                            invdatemp += util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(invDate[x]).trim())))+"\r\n";
//                            biv.setInvdate(invdatemp);
//                       }
//                   }
//                }else{
//                  biv.setInvdate(util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(B[4]).trim()))));
//                }
//            }else{
//                biv.setInvdate("");
//            }
            
            biv.setInvdate(util.convertStringToDateFormat(String.valueOf(B[4])));
            biv.setInvto(B[5]== null ? "" : util.ConvertString(B[5]));
            biv.setCost(util.convertStringToDecimalFormat(util.ConvertString(B[6])));
            biv.setCurrency(B[7]== null ? "" : util.ConvertString(B[7]));
            biv.setDescription(B[8]== null ? "" : util.ConvertString(B[8]));
            data.add(biv);
        }
        this.sessionFactory.close();
        session.close();
        return data;
    }

    @Override
    public List getBookingNonInvoiceReport(String owner,String invsup,String bookdatefrom,String bookdateto,String paydatefrom,String paydateto,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList();
        int check = 0 ;
        boolean ownersearch = false;
        boolean invsupsearch = false;
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        DecimalFormat df = new DecimalFormat("#,##0.00"); 
        
        String headerowner = "ALL";
        String headerbookdate = "ALL";
        String headerinvsup = "ALL";
        String headerpaydate = "ALL";
        
        String query = "SELECT * FROM `booking_non_invoice` where ";
        
        if((bookdatefrom != null && !"".equalsIgnoreCase(bookdatefrom)) && (bookdateto != null && !"".equalsIgnoreCase(bookdateto))){
            query += " bookdate BETWEEN '"+bookdatefrom+"' and  '"+bookdateto+"' ";
            check = 1;
            headerbookdate = util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(bookdatefrom)))) + " To " +
                            util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(bookdateto))));
        }
        
        if((paydatefrom != null && !"".equalsIgnoreCase(paydatefrom)) && (paydateto != null && !"".equalsIgnoreCase(paydateto))){
            if(check == 1){  query += " and "; }
            query += " paydate BETWEEN '"+paydatefrom+"' and  '"+paydateto+"' ";
            check = 1;
            headerpaydate = util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(paydatefrom)))) + " To " +
                            util.ConvertString(dateformat.format(util.convertStringToDate(String.valueOf(paydateto))));
        }
        
        if((owner != null && !"".equalsIgnoreCase(owner))){
            if(check == 1){  query += " and "; }
            query += " ownercode = '"+owner+"'";
            check = 1;
            ownersearch = true;
        }
        
        if((invsup != null && !"".equalsIgnoreCase(invsup))){
            if(check == 1){  query += " and "; }
            query += " supcode = '"+invsup+"'";
            check = 1;
            invsupsearch = true;
        }
        
//        query += " ORDER BY payno , paydate ";

        List<Object[]> QueryStaffList = session.createSQLQuery(query)
                .addScalar("invoicesup", Hibernate.STRING)
                .addScalar("payno", Hibernate.STRING)
                .addScalar("paydate", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("owner", Hibernate.STRING)
                .addScalar("bookdate", Hibernate.STRING)
                .addScalar("payamount", Hibernate.STRING)
                .addScalar("currency", Hibernate.STRING)
                .addScalar("sale", Hibernate.STRING)
                .addScalar("salecurrency", Hibernate.STRING)
                .list();
        
        for (Object[] B : QueryStaffList) {
            BookingNonInvoiceView bniv = new BookingNonInvoiceView();
            bniv.setHeaderbookingdate(headerbookdate);
            bniv.setHeaderpaydate(headerpaydate);
            if(ownersearch){
                bniv.setHeaderowner(B[5]== null ? "" : util.ConvertString(B[5]));
            }else{
                bniv.setHeaderowner(headerowner);
            }
            if(invsupsearch){
                bniv.setHeaderinvoicesup(B[0]== null ? "" :util.ConvertString(B[0]));
            }else{
                bniv.setHeaderinvoicesup(headerinvsup);
            }
            bniv.setInvoicesup(B[0]== null ? "" :util.ConvertString(B[0]));
            bniv.setPayno(B[1]== null ? "" :util.ConvertString(B[1]));
            bniv.setPaydate(util.convertStringToDateFormat(String.valueOf(B[2])));
            bniv.setDescription(B[3]== null ? "" : util.ConvertString(B[3]));
            bniv.setRefno(B[4]== null ? "" : util.ConvertString(B[4]));
            bniv.setOwner(B[5]== null ? "" : util.ConvertString(B[5]));
            bniv.setBookdate(util.convertStringToDateFormat(String.valueOf(B[6])));
            bniv.setPayamount(util.convertStringToDecimalFormat(util.ConvertString(B[7])));
            bniv.setCurrency(B[8]== null ? "" : util.ConvertString(B[8]));
            bniv.setSale(util.convertStringToDecimalFormat(util.ConvertString(B[9])));
            bniv.setSalecurrency(B[10]== null ? "" : util.ConvertString(B[10]));
            data.add(bniv);
        }
        
        this.sessionFactory.close();
        session.close();
        return data;
    }
    
}