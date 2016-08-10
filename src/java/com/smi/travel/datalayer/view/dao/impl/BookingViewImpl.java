/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.view.dao.BookingViewDao;
import com.smi.travel.datalayer.view.entity.BookingAirSummaryView;
import com.smi.travel.datalayer.view.entity.BookingDayTourSummaryView;
import com.smi.travel.datalayer.view.entity.BookingHotelSummaryView;
import com.smi.travel.datalayer.view.entity.BookingLandSummaryView;
import com.smi.travel.datalayer.view.entity.BookingOtherSummaryView;
import com.smi.travel.datalayer.view.entity.BookingPackageSummaryView;
import com.smi.travel.datalayer.view.entity.BookingView;
import com.smi.travel.datalayer.view.entity.BookingViewMin;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 *
 * @author Surachai
 */
public class BookingViewImpl implements BookingViewDao{
    private SessionFactory sessionFactory;
    private static final int MAX_ROW = 200;
    @Override
    public List<BookingView> getBookingList(String refno,String passFirst,String passLast,String username,String departmentID,
            String Bookdate,String status,String pnr,String ticketNo,String payBy, String bankTransfer, String transferDateFrom, String transferDateTo) {
        
        String query = "";
        boolean isBookingViewMin = false;
        if((pnr == null || "".equals(pnr)) && (ticketNo == null || "".equals(ticketNo)) && (payBy == null || "".equals(payBy)) && (bankTransfer == null || "".equals(bankTransfer)) 
                && (transferDateFrom == null || "".equals(transferDateFrom)) && (transferDateTo == null || "".equals(transferDateTo))){
            query = "from BookingViewMin book where ";
            isBookingViewMin = true;
        
        } else {
            query = "from BookingView book where ";
        }
        
        String subquery = " book.refno in (select master.referenceNo from Passenger p ";
        Session session = this.sessionFactory.openSession();
        int check = 0;
        int checksub = 0;
        System.out.println("passLast : "+passLast);
        if ((refno != null) && (!"".equalsIgnoreCase(refno))) {
            query += " book.refno like '%" +refno +"%'";
            check = 1;
        }
        
        if ((departmentID != null) && (!"".equalsIgnoreCase(departmentID))) {
            if (check == 1) {query += " and ";}
            query += " book.departmentId = '" +departmentID +"'";
            check = 1;
        }
        
        if ((username != null) && (!"".equalsIgnoreCase(username))) {
            if (check == 1) {query += " and ";}
            query += " book.createBy = '" +username +"'";
            check = 1;
        }
        
        if ((Bookdate != null) && (!"".equalsIgnoreCase(Bookdate))) {
            if (check == 1) {query += " and ";}
            query += " book.createDate = '" +Bookdate +"'";
            check = 1;
        }
        
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if (check == 1) {query += " and ";}
            query += " book.statusId = '" +status +"'";
            check = 1;
        }
        
        if ((pnr != null) && (!"".equalsIgnoreCase(pnr))) {
            if (check == 1) {query += " and ";}
            query += " book.pnr Like '%" +pnr +"%'";
            check = 1;
        }
        
        if ((ticketNo != null) && (!"".equalsIgnoreCase(ticketNo))) {
            if (check == 1) {query += " and ";}
            query += " book.ticketNo like '%" +ticketNo +"%'";
            check = 1;
        }
        
        if ((passFirst != null) && (!"".equalsIgnoreCase(passFirst))) {
            if (check == 1) {query += " and ";}
            subquery += " where p.customer.firstName like '%"+passFirst+"%'";
            checksub = 1;
            check = 1;
        }

        if ((passLast != null) && (!"".equalsIgnoreCase(passLast))) {
            if ((check == 1)&&(checksub != 1)) {query += " and ";}
            check = 1;
            if(checksub == 1){
                subquery += " and ";
            }else{
                 subquery += " where "; 
            }
            checksub = 1;
            subquery += "  p.customer.lastName like '%"+passLast+"%'";
        }
        
        if ((payBy != null) && (!"".equalsIgnoreCase(payBy))) {
            if (check == 1) {query += " and ";}
            query += " book.payBy = '" +payBy +"'";
            check = 1;
        }
        
        if ((bankTransfer != null) && (!"".equalsIgnoreCase(bankTransfer))) {
            if (check == 1) {query += " and ";}
            query += " book.accId = '" +bankTransfer +"'";
            check = 1;
        }
        
        if ((transferDateFrom != null) && (!"".equalsIgnoreCase(transferDateFrom))) {
            if (check == 1) {query += " and ";}
            query += " book.transferDate >= '" +transferDateFrom +"'";
            check = 1;
        }
        
        if ((transferDateTo != null) && (!"".equalsIgnoreCase(transferDateTo))) {
            if (check == 1) {query += " and ";}
            query += " book.transferDate <= '" +transferDateTo +"'";
            check = 1;
        }
                
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        if(checksub == 1){
            query += subquery +")";
        }
       
        query += " order by  ref_no desc ";
         System.out.println("query book view  : "+query);
       // List<BookingView> BookingList = session.createQuery(query).list();
        Query HqlQuery = session.createQuery(query);
        HqlQuery.setMaxResults(MAX_ROW);
        
        List<BookingView> BookingList = new ArrayList<>();
        if(isBookingViewMin){
            List<BookingViewMin> BookingMinList = HqlQuery.list();      
            if (BookingMinList.isEmpty()) {
                return null;
            }         
            for(BookingViewMin A : BookingMinList){
                BookingList.add((mappingBookingViewMinToBookingView(A)));
            }
                      
        } else {
            BookingList = HqlQuery.list();      
            if (BookingList.isEmpty()) {
                return null;
            }
        }
        
        return BookingList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int cancelBook(String refNoEdit) {
        int result = 0;
        String hql = "UPDATE Master m set m.MBookingstatus.id = 3 WHERE m.referenceNo = :refNo";
        try {
            org.hibernate.classic.Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("refNo", refNoEdit);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
         } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public String checkBook(String refNoEdit) {
        String result = "fail";
        Session session = this.sessionFactory.openSession();
        List<InvoiceDetail> invoiceDetailList = session.createQuery("from InvoiceDetail inv where inv.billableDesc.billable.master.referenceNo = :refNo").setParameter("refNo", refNoEdit).list();
        if(!invoiceDetailList.isEmpty()){
            return result;
        } 
        result = "success";       
        return result;
    }

    @Override
    public int enableBook(String refNoEdit) {
        int result = 0;
        String hql = "UPDATE Master m set m.MBookingstatus.id = 1 WHERE m.referenceNo = :refNo";
        try {
            org.hibernate.classic.Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("refNo", refNoEdit);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
         } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public List<BookingHotelSummaryView> getListBookingHotelSummaryView(String bookRefNo, String bookLeader, String bookDate, String hotelName, String hotelCheckIn, String hotelCheckOut) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingHotelSummaryView> bookingHotelSummaryViewList = new ArrayList<BookingHotelSummaryView>();
        
        String query = " SELECT * FROM `booking_hotel_summary_min` ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " refno = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) && (!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " leader LIKE '%" + bookLeader + "%' " ;
            condition = true;
        }
        if((bookDate != null) && (!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " refdate = '" + bookDate + "' " ;
            condition = true;
        }
        if((hotelName != null) && (!"".equalsIgnoreCase(hotelName))){
            query += (condition ? " and " : " where ");
            query += " hotel LIKE '%" + hotelName + "%' " ;
            condition = true;
        }
        if((hotelCheckIn != null) && (!"".equalsIgnoreCase(hotelCheckIn))){
            query += (condition ? " and " : " where ");
            query += " checkin = '" + hotelCheckIn + "' " ;
            condition = true;
        }
        if((hotelCheckOut != null) && (!"".equalsIgnoreCase(hotelCheckOut))){
            query += (condition ? " and " : " where ");
            query += " checkout = '" + hotelCheckOut + "' " ;
            condition = true;
        }
        
        List<Object[]> QueryHotel = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("hotel", Hibernate.STRING)
                .addScalar("checkin", Hibernate.STRING)
                .addScalar("checkout", Hibernate.STRING)
                .addScalar("Total_cost", Hibernate.STRING)
                .addScalar("curcost", Hibernate.STRING)
                .addScalar("Total_price", Hibernate.STRING)
                .addScalar("curamount", Hibernate.STRING)
                .addScalar("id", Hibernate.STRING)
                .addScalar("billid", Hibernate.STRING)
                .setMaxResults(500)
                .list();
        
        String billdescid = "";
        for (Object[] B : QueryHotel) {
            billdescid += ",";
            billdescid += B[12] == null ? null : util.ConvertString(B[12]);
        }
        
        Map<String, String> mapInvoice = getInvoiceMap(session, billdescid);
        Map<String, String> mapReceipt = getReceiptMap(session, billdescid);
        
        for (Object[] B : QueryHotel) {
            BookingHotelSummaryView bookingHotelSummaryView = new BookingHotelSummaryView();
            bookingHotelSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingHotelSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingHotelSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingHotelSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingHotelSummaryView.setHotel(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingHotelSummaryView.setCheckin(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingHotelSummaryView.setCheckout(B[6]== null ? "" :util.ConvertString(B[6]));
            bookingHotelSummaryView.setTotalcost(B[7]== null ? "" :util.ConvertString(B[7]));
            bookingHotelSummaryView.setCurcost(B[8]== null ? "" :util.ConvertString(B[8]));
            bookingHotelSummaryView.setTotalprice(B[9]== null ? "" :util.ConvertString(B[9]));
            bookingHotelSummaryView.setCuramount(B[10]== null ? "" :util.ConvertString(B[10]));
            bookingHotelSummaryView.setId(B[11]== null ? "" :util.ConvertString(B[11]));
            bookingHotelSummaryView.setInvoice(mapInvoice.get(B[12]== null ? "" :util.ConvertString(B[12])));
            bookingHotelSummaryView.setReceipt(mapReceipt.get(B[12]== null ? "" :util.ConvertString(B[12])));
            
            bookingHotelSummaryViewList.add(bookingHotelSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingHotelSummaryViewList;
    }

    @Override
    public List<BookingAirSummaryView> getListBookingAirSummaryView(String bookRefNo, String bookLeader, String bookDate, String airPnr, String airDeptDate, String airFlight) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingAirSummaryView> bookingAirSummaryViewList = new ArrayList<BookingAirSummaryView>();
//        String query = " SELECT tt.* FROM (SELECT `mt`.`Reference No` AS `refno`, date_format( `mt`.`Create_date`, '%d-%m-%Y' ) AS `refdate`, `agt`.`name` AS `agent`, `GET_LEADER_NAME` (`mt`.`id`) AS `leader`, ( SELECT count(0) FROM `airticket_passenger` `ap` WHERE ( `ap`.`airline_id` = `aa`.`id` )) AS `pax`, `pnr`.`pnr` AS `pnr`, `af`.`des_code` AS `arrv`, `af`.`source_code` AS `dept`, date_format( `af`.`depart_date`, '%d-%m-%Y' ) AS `depart_date`, ( CASE WHEN ( `af`.`depart_time` IS NOT NULL ) THEN concat( substr(`af`.`depart_time`, 1, 2), ':', substr(`af`.`depart_time`, 3, 4)) ELSE NULL END ) AS `depart_time`, date_format( `af`.`arrive_date`, '%d-%m-%Y' ) AS `arrive_date`, ( CASE WHEN ( `af`.`arrive_time` IS NOT NULL ) THEN concat( substr(`af`.`arrive_time`, 1, 2), ':', substr(`af`.`arrive_time`, 3, 4)) ELSE NULL END ) AS `arrive_time`, ( CASE WHEN (( `af`.`filght_class` IS NOT NULL ) AND ( `af`.`sub_flight_class` IS NOT NULL )) THEN concat( `mfli`.`name`, ' (', `af`.`sub_flight_class`, ')' ) ELSE `mfli`.`name` END ) AS `class`, `af`.`flight_no` AS `flight`, `billd`.`id` AS `billid` FROM ((((((( `master` `mt` JOIN `agent` `agt` ON ((`agt`.`id` = `mt`.`Agent_id`))) JOIN `airticket_booking` `ab` ON ((`ab`.`master_id` = `mt`.`id`))) JOIN `airticket_pnr` `pnr` ON (( `pnr`.`booking_id` = `ab`.`id` ))) JOIN `airticket_airline` `aa` ON ((`aa`.`pnr_id` = `pnr`.`id`))) JOIN `airticket_flight` `af` ON (( `af`.`airline_id` = `aa`.`id` ))) LEFT JOIN `billable_desc` `billd` ON ((( `billd`.`ref_item_id` = `aa`.`id` ) AND (`billd`.`bill_type` = 1)))) LEFT JOIN `m_flight` `mfli` ON (( `mfli`.`id` = `af`.`filght_class` )))) tt ";
        String query = " SELECT tt.* FROM booking_air_summary_min tt ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " tt.refno = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " tt.leader LIKE '%" + bookLeader + "%' " ;
            condition = true;
        }
        if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " tt.refdate = '" + bookDate + "' " ;
            condition = true;
        }
        if((airPnr != null) &&(!"".equalsIgnoreCase(airPnr))){
            query += (condition ? " and " : " where ");
            query += " tt.pnr = '" + airPnr + "' " ;
            condition = true;
        }
        if((airDeptDate != null) &&(!"".equalsIgnoreCase(airDeptDate))){
            query += (condition ? " and " : " where ");
            query += " tt.depart_date = '" + airDeptDate + "' " ;
            condition = true;
        }
        if((airFlight != null) &&(!"".equalsIgnoreCase(airFlight))){
            query += (condition ? " and " : " where ");
            query += " tt.flight = '" + airFlight + "' " ;
            condition = true;
        }       
        query += " ORDER BY tt.refno DESC ";
        List<Object[]> QueryAir = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("pnr", Hibernate.STRING)
                .addScalar("dept", Hibernate.STRING)
                .addScalar("arrv", Hibernate.STRING)
                .addScalar("depart_date", Hibernate.STRING)
                .addScalar("flight", Hibernate.STRING)
                .addScalar("billid", Hibernate.STRING)
                .setMaxResults(500)
                .list();
        
        String billdescid = "";
        for (Object[] B : QueryAir) {
            billdescid += ",";
            billdescid += B[10] == null ? null : util.ConvertString(B[10]);
        }
        
        Map<String, String> mapInvoice = getInvoiceMap(session, billdescid);
        Map<String, String> mapReceipt = getReceiptMap(session, billdescid);
        
        for (Object[] B : QueryAir) {
            BookingAirSummaryView bookingAirSummaryView = new BookingAirSummaryView();
            bookingAirSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingAirSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingAirSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingAirSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingAirSummaryView.setPax(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingAirSummaryView.setPnr(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingAirSummaryView.setDept(B[6]== null ? "" :util.ConvertString(B[6]));
            bookingAirSummaryView.setArrv(B[7]== null ? "" :util.ConvertString(B[7]));
            bookingAirSummaryView.setDepartdate(B[8]== null ? "" :util.ConvertString(B[8]));
            bookingAirSummaryView.setFlight(B[9]== null ? "" :util.ConvertString(B[9]));
            bookingAirSummaryView.setBillid(B[10]== null ? "" :util.ConvertString(B[10]));
            bookingAirSummaryView.setInvoice(mapInvoice.get(B[10]== null ? "" :util.ConvertString(B[10])));
            bookingAirSummaryView.setReceipt(mapReceipt.get(B[10]== null ? "" :util.ConvertString(B[10])));
            bookingAirSummaryViewList.add(bookingAirSummaryView);
        }
        this.sessionFactory.close();
        session.close();
        return bookingAirSummaryViewList;
    }

    @Override
    public List<BookingPackageSummaryView> getListBookingPackageSummaryView(String bookRefNo, String bookLeader, String bookDate, String packageName, String packageAgent) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingPackageSummaryView> bookingPackageSummaryViewList = new ArrayList<BookingPackageSummaryView>();
        
        String query = " SELECT * FROM `booking_package_summary_min` b ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " b.refno = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " b.leader LIKE '%" + bookLeader + "%' COLLATE utf8_unicode_ci " ;
            condition = true;
        }
        if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " b.refdate = '" + bookDate + "' " ;
            condition = true;
        }
        if((packageName != null) &&(!"".equalsIgnoreCase(packageName))){
            query += (condition ? " and " : " where ");
            query += " (b.code LIKE '%" + packageName + "%' or b.name LIKE '%" + packageName + "%') " ;
            condition = true;
        }
        if((packageAgent != null) &&(!"".equalsIgnoreCase(packageAgent))){
            query += (condition ? " and " : " where ");
            query += " b.agent LIKE '%" + packageAgent + "%' " ;
            condition = true;
        }
        query += " GROUP BY b.refno ";
        query += " ORDER BY b.refno DESC ";

        List<Object[]> QueryPackage = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("billid", Hibernate.STRING)
                .setMaxResults(500)
                .list();
        
        String billdescid = "";
        for (Object[] B : QueryPackage) {
            billdescid += ",";
            billdescid += B[6] == null ? null : util.ConvertString(B[6]);
        }
        
        Map<String, String> mapInvoice = getInvoiceMap(session, billdescid);
        Map<String, String> mapReceipt = getReceiptMap(session, billdescid);
        
        for (Object[] B : QueryPackage) {
            BookingPackageSummaryView bookingPackageSummaryView = new BookingPackageSummaryView();
            bookingPackageSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingPackageSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingPackageSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingPackageSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingPackageSummaryView.setCode(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingPackageSummaryView.setName(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingPackageSummaryView.setInvoice(mapInvoice.get(B[6]== null ? "" :util.ConvertString(B[6])));
            bookingPackageSummaryView.setReceipt(mapReceipt.get(B[6]== null ? "" :util.ConvertString(B[6])));
            bookingPackageSummaryViewList.add(bookingPackageSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingPackageSummaryViewList;
    }

    @Override
    public List<BookingDayTourSummaryView> getListBookingDayTourSummaryView(String bookRefNo, String bookLeader, String bookDate, String tourCode, String tourName, String tourDate, String tourPickUp, String tourAgent) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingDayTourSummaryView> bookingDayTourSummaryViewList = new ArrayList<BookingDayTourSummaryView>();
        
//        String query = " SELECT * FROM `booking_daytour_summary_min` ";
        String query = "select `mt`.`Reference No` AS `refno`,date_format(`mt`.`Create_date`,'%d-%m-%Y') AS `refdate`,`agt`.`code` AS `agent`,"
                + "`GET_LEADER_NAME`(`mt`.`id`) AS `leader`,sum(`dp`.`qty`) AS `pax`,`dt`.`name` AS `tour_name`,`dt`.`code` AS `tour_code`,"
                + "date_format(`db`.`tour_date`,'%d-%m-%Y') AS `tour_date`,(case when (`pl`.`place` = 'OTHERS') then `db`.`pickup_detail` else `pl`.`place` end) AS `pickup`,"
                + "date_format(`db`.`pickup_time`,'%H:%i') AS `time`,sum((case when (`dp`.`category_id` = 1) then `dp`.`qty` else 0 end)) AS `adult`,"
                + "sum((case when (`dp`.`category_id` = 2) then `dp`.`qty` else 0 end)) AS `child`,sum((case when (`dp`.`category_id` = 3) then `dp`.`qty` else 0 end)) AS `infant`,"
                + "`db`.`remark` AS `remark`,'' AS `invoice`,'' AS `receipt`,`db`.`id` AS `id`,`billd`.`id` AS `billid` "
                + "from ((((((`daytour_booking` `db` join `master` `mt` on((`mt`.`id` = `db`.`master_id`))) "
                + "join `agent` `agt` on((`agt`.`id` = `mt`.`Agent_id`))) "
                + "join `daytour` `dt` on((`dt`.`id` = `db`.`tour_id`))) "
                + "join `place` `pl` on((`pl`.`id` = `db`.`pickup`))) "
                + "left join `billable_desc` `billd` on(((`billd`.`ref_item_id` = `db`.`id`) and (`billd`.`bill_type` = 6)))) "
                + "left join `daytour_booking_price` `dp` on((`dp`.`daytour_booking_id` = `db`.`id`))) ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " `mt`.`Reference No` = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " `GET_LEADER_NAME` (`mt`.`id`) LIKE '%" + bookLeader + "%' COLLATE utf8_unicode_ci " ;
            condition = true;
        }
        if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " date_format(`mt`.`Create_date`,'%d-%m-%Y') = '" + bookDate + "' " ;
            condition = true;
        }
        if((tourCode != null) &&(!"".equalsIgnoreCase(tourCode))){
            query += (condition ? " and " : " where ");
            query += " `dt`.`code` LIKE '%" + tourCode + "%' " ;
            condition = true;
        }
        if((tourName != null) &&(!"".equalsIgnoreCase(tourName))){
            query += (condition ? " and " : " where ");
            query += " `dt`.`name` LIKE '%" + tourName + "%' " ;
            condition = true;
        }
        if((tourDate != null) &&(!"".equalsIgnoreCase(tourDate))){
            query += (condition ? " and " : " where ");
            query += " date_format(`db`.`tour_date`,'%d-%m-%Y') = '" + tourDate + "' " ;
            condition = true;
        }
        if((tourPickUp != null) &&(!"".equalsIgnoreCase(tourPickUp))){
            query += (condition ? " and " : " where ");
            query += " (CASE WHEN (`pl`.`place` = 'OTHERS') THEN `db`.`pickup_detail` ELSE `pl`.`place` END ) LIKE '%" + tourPickUp + "%' " ;
            condition = true;
        }
        if((tourAgent != null) &&(!"".equalsIgnoreCase(tourAgent))){
            query += (condition ? " and " : " where ");
            query += " `agt`.`code` LIKE '%" + tourAgent + "%' " ;
            condition = true;
        }
        query += " group by `db`.`id` ORDER BY `mt`.`Reference No` DESC ";

        List<Object[]> QueryDayTour = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("pax", Hibernate.STRING)
                .addScalar("tour_code", Hibernate.STRING)
                .addScalar("tour_name", Hibernate.STRING)
                .addScalar("tour_date", Hibernate.STRING)
                .addScalar("pickup", Hibernate.STRING)
                .addScalar("time", Hibernate.STRING)
                .addScalar("adult", Hibernate.STRING)
                .addScalar("child", Hibernate.STRING)
                .addScalar("infant", Hibernate.STRING)
                .addScalar("remark", Hibernate.STRING)
                .addScalar("id", Hibernate.STRING)
                .addScalar("billid", Hibernate.STRING)
                .setMaxResults(500)
                .list();
        
        String billdescid = "";
        for (Object[] B : QueryDayTour) {
            billdescid += ",";
            billdescid += B[15] == null ? null : util.ConvertString(B[15]);
        }
        
        Map<String, String> mapInvoice = getInvoiceMap(session, billdescid);
        Map<String, String> mapReceipt = getReceiptMap(session, billdescid);
        
        for (Object[] B : QueryDayTour) {
            BookingDayTourSummaryView bookingDayTourSummaryView = new BookingDayTourSummaryView();
            bookingDayTourSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingDayTourSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingDayTourSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingDayTourSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingDayTourSummaryView.setPax(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingDayTourSummaryView.setTourcode(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingDayTourSummaryView.setTourname(B[6]== null ? "" :util.ConvertString(B[6]));
            bookingDayTourSummaryView.setTourdate(B[7]== null ? "" :util.ConvertString(B[7]));
            bookingDayTourSummaryView.setPickup(B[8]== null ? "" :util.ConvertString(B[8]));
            bookingDayTourSummaryView.setTime(B[9]== null ? "" :util.ConvertString(B[9]));
            bookingDayTourSummaryView.setAdult(B[10]== null ? "" :util.ConvertString(B[10]));
            bookingDayTourSummaryView.setChild(B[11]== null ? "" :util.ConvertString(B[11]));
            bookingDayTourSummaryView.setInfant(B[12]== null ? "" :util.ConvertString(B[12]));
            bookingDayTourSummaryView.setRemark(B[13]== null ? "" :util.ConvertString(B[13]));
            bookingDayTourSummaryView.setId(B[14]== null ? "" :util.ConvertString(B[14]));
            bookingDayTourSummaryView.setInvoice(mapInvoice.get(B[15]== null ? "" :util.ConvertString(B[15])));
            bookingDayTourSummaryView.setReceipt(mapReceipt.get(B[15]== null ? "" :util.ConvertString(B[15])));
            bookingDayTourSummaryViewList.add(bookingDayTourSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingDayTourSummaryViewList;
    }

    @Override
    public List<BookingOtherSummaryView> getListBookingOtherSummaryView(String bookRefNo, String bookLeader, String bookDate, String otherCode, String otherName, String otherDate, String otherAgent) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingOtherSummaryView> bookingOtherSummaryViewList = new ArrayList<BookingOtherSummaryView>();
        
        String query = " SELECT * FROM `booking_other_summary_min` ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " refno = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " leader LIKE '%" + bookLeader + "%' COLLATE utf8_unicode_ci " ;
            condition = true;
        }
        if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " refdate = '" + bookDate + "' " ;
            condition = true;
        }
        if((otherCode != null) &&(!"".equalsIgnoreCase(otherCode))){
            query += (condition ? " and " : " where ");
            query += " code LIKE '%" + otherCode + "%' " ;
            condition = true;
        }
        if((otherName != null) &&(!"".equalsIgnoreCase(otherName))){
            query += (condition ? " and " : " where ");
            query += " name LIKE '%" + otherName + "%' " ;
            condition = true;
        }
        if((otherDate != null) &&(!"".equalsIgnoreCase(otherDate))){
            query += (condition ? " and " : " where ");
            query += " other_date = '" + otherDate + "' " ;
            condition = true;
        }
        if((otherAgent != null) &&(!"".equalsIgnoreCase(otherAgent))){
            query += (condition ? " and " : " where ");
            query += " agent LIKE '%" + otherAgent + "%' " ;
            condition = true;
        }
        query += " ORDER BY refno DESC ";

        List<Object[]> QueryOther = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("code", Hibernate.STRING)
                .addScalar("name", Hibernate.STRING)
                .addScalar("other_date", Hibernate.STRING)
                .addScalar("id", Hibernate.STRING)
                .addScalar("billid", Hibernate.STRING)
                .setMaxResults(500)
                .list();
        
        String billdescid = "";
        for (Object[] B : QueryOther) {
            billdescid += ",";
            billdescid += B[8] == null ? null : util.ConvertString(B[8]);
        }
        
        Map<String, String> mapInvoice = getInvoiceMap(session, billdescid);
        Map<String, String> mapReceipt = getReceiptMap(session, billdescid);    
        
        for (Object[] B : QueryOther) {
            BookingOtherSummaryView bookingOtherSummaryView = new BookingOtherSummaryView();
            bookingOtherSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingOtherSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingOtherSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingOtherSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingOtherSummaryView.setCode(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingOtherSummaryView.setName(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingOtherSummaryView.setOtherdate(B[6]== null ? "" :util.ConvertString(B[6]));
            bookingOtherSummaryView.setId(B[7]== null ? "" :util.ConvertString(B[7]));
            bookingOtherSummaryView.setInvoice(mapInvoice.get(B[8]== null ? "" :util.ConvertString(B[8])));
            bookingOtherSummaryView.setReceipt(mapReceipt.get(B[8]== null ? "" :util.ConvertString(B[8])));
            bookingOtherSummaryViewList.add(bookingOtherSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingOtherSummaryViewList;
    }

    @Override
    public List<BookingLandSummaryView> getListBookingLandSummaryView(String bookRefNo, String bookLeader, String bookDate, String landOkBy, String landAgent, String landCategory) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingLandSummaryView> bookingLandSummaryViewList = new ArrayList<BookingLandSummaryView>();
        
        String query = " SELECT * FROM `booking_land_summary_min` ";
        boolean condition = false;
        
        if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
            query += (condition ? " and " : " where ");
            query += " refno = '" + bookRefNo + "' " ;
            condition = true;
        }
        if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
            query += (condition ? " and " : " where ");
            query += " leader LIKE '%" + bookLeader + "%' COLLATE utf8_unicode_ci " ;
            condition = true;
        }
        if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
            query += (condition ? " and " : " where ");
            query += " refdate = '" + bookDate + "' " ;
            condition = true;
        }
        if((landOkBy != null) &&(!"".equalsIgnoreCase(landOkBy))){
            query += (condition ? " and " : " where ");
            query += " ok_by = '" + landOkBy + "' " ;
            condition = true;
        }
        if((landAgent != null) &&(!"".equalsIgnoreCase(landAgent))){
            query += (condition ? " and " : " where ");
            query += " agent LIKE '%" + landAgent + "%' " ;
            condition = true;
        }
        if((landCategory != null) &&(!"".equalsIgnoreCase(landCategory))){
            query += (condition ? " and " : " where ");
            query += " category = '" + landCategory + "' " ;
            condition = true;
        }
        query += " ORDER BY refno DESC ";

        List<Object[]> QueryLand = session.createSQLQuery(query)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("refdate", Hibernate.STRING)
                .addScalar("agent", Hibernate.STRING)
                .addScalar("leader", Hibernate.STRING)
                .addScalar("ok_by", Hibernate.STRING)
                .addScalar("description", Hibernate.STRING)
                .addScalar("category", Hibernate.STRING)
                .addScalar("qty", Hibernate.STRING)
                .addScalar("id", Hibernate.STRING)
                .addScalar("billid", Hibernate.STRING)
                .setMaxResults(500)
                .list();        
        
        String billdescid = "";
        for (Object[] B : QueryLand) {
            billdescid += ",";
            billdescid += B[9] == null ? null : util.ConvertString(B[9]);
        }
        
        Map<String, String> mapInvoice = getInvoiceMap(session, billdescid);
        Map<String, String> mapReceipt = getReceiptMap(session, billdescid);
		
        for (Object[] B : QueryLand) {
            BookingLandSummaryView bookingLandSummaryView = new BookingLandSummaryView();
            bookingLandSummaryView.setRefno(B[0]== null ? "" : util.ConvertString(B[0]));
            bookingLandSummaryView.setRefdate(B[1]== null ? "" : util.ConvertString(B[1]));
            bookingLandSummaryView.setAgent(B[2]== null ? "" :util.ConvertString(B[2]));
            bookingLandSummaryView.setLeader(B[3]== null ? "" :util.ConvertString(B[3]));
            bookingLandSummaryView.setOkby(B[4]== null ? "" :util.ConvertString(B[4]));
            bookingLandSummaryView.setDescription(B[5]== null ? "" :util.ConvertString(B[5]));
            bookingLandSummaryView.setCategory(B[6]== null ? "" :util.ConvertString(B[6]));
            bookingLandSummaryView.setQty(B[7]== null ? "" :util.ConvertString(B[7]));
            bookingLandSummaryView.setId(B[8]== null ? "" :util.ConvertString(B[8]));     
            bookingLandSummaryView.setInvoice(mapInvoice.get(B[9]== null ? "" :util.ConvertString(B[9])));
            bookingLandSummaryView.setReceipt(mapReceipt.get(B[9]== null ? "" :util.ConvertString(B[9])));
            bookingLandSummaryViewList.add(bookingLandSummaryView);
        }
        
        this.sessionFactory.close();
        session.close();
        return bookingLandSummaryViewList;
    }

    private BookingView mappingBookingViewMinToBookingView(BookingViewMin bookingViewMin) {
        BookingView bookingView = new BookingView();
        bookingView.setRefno(bookingViewMin.getRefno() != null && !"".equals(bookingViewMin.getRefno()) ? bookingViewMin.getRefno() : "");
        bookingView.setAgentCode(bookingViewMin.getAgentCode() != null && !"".equals(bookingViewMin.getAgentCode()) ? bookingViewMin.getAgentCode() : "");
        bookingView.setLeaderName(bookingViewMin.getLeaderName() != null && !"".equals(bookingViewMin.getLeaderName()) ? bookingViewMin.getLeaderName() : "");
        bookingView.setStatusName(bookingViewMin.getStatusName() != null && !"".equals(bookingViewMin.getStatusName()) ? bookingViewMin.getStatusName() : "");
        bookingView.setPnr(bookingViewMin.getPnr() != null && !"".equals(bookingViewMin.getPnr()) ? bookingViewMin.getPnr() : "");
        bookingView.setHotelName(bookingViewMin.getHotelName() != null && !"".equals(bookingViewMin.getHotelName()) ? bookingViewMin.getHotelName() : "");
        bookingView.setCreateDate(bookingViewMin.getCreateDate() != null ? bookingViewMin.getCreateDate() : null);
        bookingView.setCreateBy(bookingViewMin.getCreateBy() != null && !"".equals(bookingViewMin.getCreateBy()) ? bookingViewMin.getCreateBy() : "");
        bookingView.setDepartmentName(bookingViewMin.getDepartmentName() != null && !"".equals(bookingViewMin.getDepartmentName()) ? bookingViewMin.getDepartmentName() : "");
        bookingView.setDepartmentId(bookingViewMin.getDepartmentId() != null && !"".equals(bookingViewMin.getDepartmentId()) ? bookingViewMin.getDepartmentId() : "");
        bookingView.setStatusId(bookingViewMin.getStatusId() != null && !"".equals(bookingViewMin.getStatusId()) ? bookingViewMin.getStatusId() : "");
        bookingView.setTel(bookingViewMin.getTel() != null && !"".equals(bookingViewMin.getTel()) ? bookingViewMin.getTel() : "");
        bookingView.setRemark(bookingViewMin.getRemark() != null && !"".equals(bookingViewMin.getRemark()) ? bookingViewMin.getRemark() : "");
        bookingView.setEmail(bookingViewMin.getEmail() != null && !"".equals(bookingViewMin.getEmail()) ? bookingViewMin.getEmail() : "");
        bookingView.setTicketNo(bookingViewMin.getTicketNo() != null && !"".equals(bookingViewMin.getTicketNo()) ? bookingViewMin.getTicketNo() : "");
        bookingView.setPayBy(bookingViewMin.getPayBy() != null && !"".equals(bookingViewMin.getPayBy()) ? bookingViewMin.getPayBy() : "");
        bookingView.setAccId(bookingViewMin.getAccId() != null && !"".equals(bookingViewMin.getAccId()) ? bookingViewMin.getAccId() : "");
        bookingView.setTourCode(bookingViewMin.getTourCode() != null && !"".equals(bookingViewMin.getTourCode()) ? bookingViewMin.getTourCode() : "");
        
        return bookingView;
    }
   
    private Map<String, String> getInvoiceMap(Session session,String billdescid){
        UtilityFunction util = new UtilityFunction();
        String querybookingbill = " SELECT bs.* FROM `booking_billable_summary` bs where bs.id in ("+billdescid.substring(1)+") ";
        List<Object[]> QueryBill = session.createSQLQuery(querybookingbill)
                .addScalar("id", Hibernate.STRING)
                .addScalar("invoice", Hibernate.STRING)
                .addScalar("receipt", Hibernate.STRING)
                .list();
        
        Map<String, String> mapInvoice = new HashMap<String, String>();
        for (Object[] B : QueryBill) {
            mapInvoice.put(B[0]== null ? "" : util.ConvertString(B[0]), B[1]== null ? "" : util.ConvertString(B[1]));
        }
        return mapInvoice;
    }
    
    private Map<String, String> getReceiptMap(Session session,String billdescid){
        UtilityFunction util = new UtilityFunction();
        String querybookingbill = " SELECT bs.* FROM `booking_billable_summary` bs where bs.id in ("+billdescid.substring(1)+") ";
        List<Object[]> QueryBill = session.createSQLQuery(querybookingbill)
                .addScalar("id", Hibernate.STRING)
                .addScalar("invoice", Hibernate.STRING)
                .addScalar("receipt", Hibernate.STRING)
                .list();

        Map<String, String> mapReceipt = new HashMap<String, String>();
        for (Object[] B : QueryBill) {
            mapReceipt.put(B[0]== null ? "" : util.ConvertString(B[0]), B[2]== null ? "" : util.ConvertString(B[2]));
        }
        return mapReceipt;
    }
    
}
