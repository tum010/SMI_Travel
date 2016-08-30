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
//            query = "from BookingViewMin book where ";
            query = "select `mt`.`Reference No` AS `ref_no`,`ag`.`code` AS `agent_code`,`GET_LEADER_NAME`(`mt`.`id`) AS `leader_name`,"
                    + "`bs`.`name` AS `status_name`,`bs`.`id` AS `status_id`,`pnr`.`pnr` AS `pnr`,ifnull(`bvt`.`hotel`,'') AS `hotel_name`,'' AS `first_depart_date`,'' AS `first_checkin_date`,"
                    + "`mt`.`Create_date` AS `Create_date`,`mt`.`Create_by` AS `Create_by`,`dm`.`name` AS `department_name`,`dm`.`id` AS `department_id`,"
                    + "'' AS `tel`,'' AS `remark`,'' AS `ticket_no`,'' AS `email`,'' AS `payby`,'' AS `accid`,'' AS `transfer_date`,"
                    + "`bdv`.`tour_date` AS `tour_date`,`bdv`.`tour_code` AS `tour_code` "
                    + "from ((((((((((`master` `mt` "
                    + "left join `agent` `ag` on((`mt`.`Agent_id` = `ag`.`id`))) "
                    + "join `passenger` `pg` on((`mt`.`id` = `pg`.`master_id`))) "
                    + "join `customer` `cm` on((`pg`.`customer_id` = `cm`.`id`))) "
                    + "left join `m_initialname` `mi` on((`cm`.`initial_name` = `mi`.`id`))) "
                    + "join `m_bookingstatus` `bs` on((`mt`.`Status` = `bs`.`id`))) "
                    + "join `staff` `st` on((`mt`.`Staff_id` = `st`.`id`))) "
                    + "left join `m_department` `dm` on((`st`.`department_id` = `dm`.`id`))) "
                    + "left join `billable` `bill` on((`bill`.`master_id` = `mt`.`id`))) "
                    + "left join `booking_daytour_view` `bdv` on((`bdv`.`master_id` = `mt`.`id`))) "
                    + "left join `booking_view_hotel` `bvt` on((`bvt`.`id` = `mt`.`id`))) "
                    + "left join `booking_view_pnr` `pnr` ON ((`pnr`.`id` = `mt`.`id`))"
                    + "where  ";
            isBookingViewMin = true;
        
        } else {
//            query = "from BookingView book where ";
            query = "select distinct `mt`.`Reference No` AS `ref_no`,`ag`.`code` AS `agent_code`,`GET_LEADER_NAME`(`mt`.`id`) AS `leader_name`,"
                    + "`bs`.`name` AS `status_name`,`bs`.`id` AS `status_id`,ifnull(`bpnr`.`pnr`,'') AS `pnr`,ifnull(`bvt`.`hotel`,'') AS `hotel_name`,"
                    + "`GET_MIN_DEPART_DATE`(`mt`.`id`) AS `first_depart_date`,`GET_MIN_CHECKIN_DATE`(`mt`.`id`) AS `first_checkin_date`,`mt`.`Create_date` AS `Create_date`,"
                    + "`mt`.`Create_by` AS `Create_by`,`dm`.`name` AS `department_name`,`dm`.`id` AS `department_id`,`cm`.`tel` AS `tel`,`cm`.`remark` AS `remark`,"
                    + "'' AS `ticket_no`,`cm`.`email` AS `email`,`bill`.`pay_by` AS `payby`,`bill`.`acc_id` AS `accid`,`bill`.`transfer_date` AS `transfer_date`,"
                    + "`bdv`.`tour_date` AS `tour_date`,`bdv`.`tour_code` AS `tour_code` "
                    + "from (((((((((((`master` `mt` "
                    + "left join `agent` `ag` on((`mt`.`Agent_id` = `ag`.`id`))) "
                    + "left join `passenger` `pg` on((`mt`.`id` = `pg`.`master_id`))) "
                    + "left join `customer` `cm` on((`pg`.`customer_id` = `cm`.`id`))) "
                    + "left join `m_initialname` `mi` on((`cm`.`initial_name` = `mi`.`id`))) "
                    + "left join `m_bookingstatus` `bs` on((`mt`.`Status` = `bs`.`id`))) "
                    + "left join `staff` `st` on((`mt`.`Staff_id` = `st`.`id`))) "
                    + "left join `m_department` `dm` on((`st`.`department_id` = `dm`.`id`))) "
                    + "left join `billable` `bill` on((`bill`.`master_id` = `mt`.`id`))) "
                    + "left join `booking_daytour_view` `bdv` on((`bdv`.`master_id` = `mt`.`id`))) "
                    + "left join `booking_view_hotel` `bvt` on((`bvt`.`id` = `mt`.`id`))) "
                    + "left join `booking_view_pnr` `bpnr` on((`bpnr`.`id` = `mt`.`id`))) "
                    + "where  ";
            isBookingViewMin = true;
        }
        
        String subquery = " book.refno in (select master.referenceNo from Passenger p ";
        Session session = this.sessionFactory.openSession();
        int check = 0;
        int checksub = 0;

        if ((refno != null) && (!"".equalsIgnoreCase(refno))) {
            if (check == 1) {query += " and ";}
            query += " `mt`.`Reference No` = '" +refno +"'";
            check = 1;
        }
        
        if ((departmentID != null) && (!"".equalsIgnoreCase(departmentID))) {
            if (check == 1) {query += " and ";}
            query += " `dm`.`id` = '" +departmentID +"'";
            check = 1;
        }
        
        if ((username != null) && (!"".equalsIgnoreCase(username))) {
            if (check == 1) {query += " and ";}
            query += " `mt`.`Create_by` = '" +username +"'";
            check = 1;
        }
        
        if ((Bookdate != null) && (!"".equalsIgnoreCase(Bookdate))) {
            if (check == 1) {query += " and ";}
            query += " `mt`.`Create_date` = '" +Bookdate +"'";
            check = 1;
        }
        
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if (check == 1) {query += " and ";}
            query += " `bs`.`id` = '" +status +"'";
            check = 1;
        }
        
        if ((pnr != null) && (!"".equalsIgnoreCase(pnr))) {
            if (check == 1) {query += " and ";}
            query += " ifnull(`bpnr`.`pnr`, '') Like '%" + pnr + "%'";
            check = 1;
        }
        
        if ((ticketNo != null) && (!"".equalsIgnoreCase(ticketNo))) {
            if (check == 1) {query += " and ";}
            query += " GET_TICKETNO_FROM_REFNO(mt.`Reference No`) like '%" + ticketNo + "%' COLLATE utf8_unicode_ci";
            check = 1;
        }
//        
        if ((passFirst != null) && (!"".equalsIgnoreCase(passFirst))) {
            if (check == 1) {query += " and ";}
//            subquery += " where p.customer.firstName like '%"+passFirst+"%'";
//            checksub = 1;
            query += " `cm`.`first_name` like '%" + passFirst + "%' ";
            check = 1;
        }

        if ((passLast != null) && (!"".equalsIgnoreCase(passLast))) {
            if (check == 1) {query += " and ";}
            query += " `cm`.`last_name` like '%" + passLast + "%' ";
            check = 1;
//            if ((check == 1)&&(checksub != 1)) {query += " and ";}
//            check = 1;
//            if(checksub == 1){
//                subquery += " and ";
//            }else{
//                 subquery += " where "; 
//            }
//            checksub = 1;
//            subquery += "  p.customer.lastName like '%"+passLast+"%'";
        }
        
        if ((payBy != null) && (!"".equalsIgnoreCase(payBy))) {
            if (check == 1) {query += " and ";}
            query += " `bill`.`pay_by` = '" +payBy +"'";
            check = 1;
        }
        
        if ((bankTransfer != null) && (!"".equalsIgnoreCase(bankTransfer))) {
            if (check == 1) {query += " and ";}
            query += " `bill`.`acc_id` = '" +bankTransfer +"'";
            check = 1;
        }
        
        if ((transferDateFrom != null) && (!"".equalsIgnoreCase(transferDateFrom))) {
            if (check == 1) {query += " and ";}
            query += " `bill`.`transfer_date` >= '" +transferDateFrom +"'";
            check = 1;
        }
        
        if ((transferDateTo != null) && (!"".equalsIgnoreCase(transferDateTo))) {
            if (check == 1) {query += " and ";}
            query += " `bill`.`transfer_date` <= '" +transferDateTo +"'";
            check = 1;
        }
                
        if (check == 0) {
            query = query.replaceAll("where", " ");
        }
        if(checksub == 1){
            query += subquery +")";
        }
       
        query += " group by `mt`.`id` order by `mt`.`Reference No` desc ";
         System.out.println("query book view  : "+query);
       // List<BookingView> BookingList = session.createQuery(query).list();
//        Query HqlQuery = session.createQuery(query);
//        HqlQuery.setMaxResults(MAX_ROW);
        
        List<BookingView> BookingList = new ArrayList<>();
        if(isBookingViewMin){
//            List<BookingViewMin> BookingMinList = HqlQuery.list();      
//            if (BookingMinList.isEmpty()) {
//                return null;
//            }         
//            for(BookingViewMin A : BookingMinList){
//                BookingList.add((mappingBookingViewMinToBookingView(A)));
//            }
            List<Object[]> queryList = session.createSQLQuery(query)
                    .addScalar("ref_no", Hibernate.STRING)
                    .addScalar("agent_code", Hibernate.STRING)
                    .addScalar("leader_name", Hibernate.STRING)
                    .addScalar("status_name", Hibernate.STRING)
                    .addScalar("pnr", Hibernate.STRING)
                    .addScalar("hotel_name", Hibernate.STRING)
                    .addScalar("Create_date", Hibernate.STRING)
                    .addScalar("Create_by", Hibernate.STRING)
                    .addScalar("department_name", Hibernate.STRING)
                    .addScalar("department_id", Hibernate.STRING)
                    .addScalar("status_id", Hibernate.STRING)
                    .addScalar("tel", Hibernate.STRING)
                    .addScalar("remark", Hibernate.STRING)
                    .addScalar("email", Hibernate.STRING)                                      
                    .addScalar("payby", Hibernate.STRING)
                    .addScalar("accid", Hibernate.STRING)
                    .addScalar("tour_code", Hibernate.STRING)
                    .setMaxResults(300)
                    .list();
            
            for(Object[] A : queryList){
                BookingList.add((mappingBookingViewMinToBookingView(A)));
            }
                      
        } else {
//            BookingList = HqlQuery.list();      
//            if (BookingList.isEmpty()) {
//                return null;
//            }
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
        try{
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
                    .setMaxResults(300)
                    .list();
            
            boolean hasData = QueryHotel != null && QueryHotel.size() > 0 ? true : false;
            if(!hasData){
                return bookingHotelSummaryViewList;
            }

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
            
        }finally{
            this.sessionFactory.close();
            session.close();
        }
              
        return bookingHotelSummaryViewList;
    }

    @Override
    public List<BookingAirSummaryView> getListBookingAirSummaryView(String bookRefNo, String bookLeader, String bookDate, String airPnr, String airDeptDate, String airFlight) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingAirSummaryView> bookingAirSummaryViewList = new ArrayList<BookingAirSummaryView>();
        try{
            String query = "SELECT b.*, billd.id AS billid FROM ( SELECT `mt`.`Reference No` AS `refno`, date_format( `mt`.`Create_date`, '%d-%m-%Y' ) AS `refdate`, `agt`.`name` AS `agent`, `GET_LEADER_NAME` (`mt`.`id`) AS `leader`, ( SELECT count(0) FROM `airticket_passenger` `ap` WHERE ( `ap`.`airline_id` = `aa`.`id` )) AS `pax`, `pnr`.`pnr` AS `pnr`, `af`.`des_code` AS `arrv`, `af`.`source_code` AS `dept`, date_format( `af`.`depart_date`, '%d-%m-%Y' ) AS `depart_date`, ( CASE WHEN ( `af`.`depart_time` IS NOT NULL ) THEN concat( substr(`af`.`depart_time`, 1, 2), ':', substr(`af`.`depart_time`, 3, 4)) ELSE NULL END ) AS `depart_time`, date_format( `af`.`arrive_date`, '%d-%m-%Y' ) AS `arrive_date`, ( CASE WHEN ( `af`.`arrive_time` IS NOT NULL ) THEN concat( substr(`af`.`arrive_time`, 1, 2), ':', substr(`af`.`arrive_time`, 3, 4)) ELSE NULL END ) AS `arrive_time`, ( CASE WHEN (( `af`.`filght_class` IS NOT NULL ) AND ( `af`.`sub_flight_class` IS NOT NULL )) THEN concat( `mfli`.`name`, ' (', `af`.`sub_flight_class`, ')' ) ELSE `mfli`.`name` END ) AS `class`, `af`.`flight_no` AS `flight`, `aa`.`id` AS `airline_id` FROM (((((( `master` `mt` JOIN `agent` `agt` ON ((`agt`.`id` = `mt`.`Agent_id`))) JOIN `airticket_booking` `ab` ON ((`ab`.`master_id` = `mt`.`id`))) JOIN `airticket_pnr` `pnr` ON (( `pnr`.`booking_id` = `ab`.`id` ))) JOIN `airticket_airline` `aa` ON ((`aa`.`pnr_id` = `pnr`.`id`))) JOIN `airticket_flight` `af` ON (( `af`.`airline_id` = `aa`.`id` ))) LEFT JOIN `m_flight` `mfli` ON (( `mfli`.`id` = `af`.`filght_class` ))) subQuery ) b LEFT JOIN ( SELECT billd.id, billd.ref_item_id FROM billable_desc billd WHERE billd.bill_type = 1 ) billd ON billd.ref_item_id = b.airline_id ";
    //        String query = " SELECT tt.* FROM booking_air_summary_min tt ";
            String subQuery = "";
            boolean condition = false;
            
            if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " `mt`.`Reference No` = '" + bookRefNo + "' " ;
                condition = true;
            }
            if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " `GET_LEADER_NAME` (`mt`.`id`) LIKE '%" + bookLeader + "%' COLLATE utf8_unicode_ci " ;
                condition = true;
            }
            if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " date_format(`mt`.`Create_date`,'%d-%m-%Y') = '" + bookDate + "' " ;
                condition = true;
            }
            if((airPnr != null) &&(!"".equalsIgnoreCase(airPnr))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " `pnr`.`pnr` LIKE '%" + airPnr + "%' " ;
                condition = true;
            }
            if((airDeptDate != null) &&(!"".equalsIgnoreCase(airDeptDate))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " date_format(`af`.`depart_date`,'%d-%m-%Y') = '" + airDeptDate + "' " ;
                condition = true;
            }
            if((airFlight != null) &&(!"".equalsIgnoreCase(airFlight))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " `af`.`flight_no` LIKE '%" + airFlight + "%' " ;
                condition = true;
            }       
            subQuery += " ORDER BY `mt`.`Reference No` DESC ";
            query = query.replace("subQuery", subQuery);
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
                    .setMaxResults(300)
                    .list();

            boolean hasData = QueryAir != null && QueryAir.size() > 0 ? true : false;
            if(!hasData){
                return bookingAirSummaryViewList;
            }
            
            String billdescid = "";          
            for (Object[] B : QueryAir) {
                if(B[10] != null){
                    billdescid += ",";
                }
                billdescid += B[10] == null ? "" : util.ConvertString(B[10]);
            }
            
            Map<String, String> mapInvoice = null;
            Map<String, String> mapReceipt = null;
            if(!"".equals(billdescid)){
                mapInvoice = getInvoiceMap(session, billdescid);
                mapReceipt = getReceiptMap(session, billdescid);
            }

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
                
                if(mapInvoice != null){
                    if(mapInvoice.get(B[10]== null ? "" :util.ConvertString(B[10])) != null 
                            && !"".equals(mapInvoice.get(B[10]== null ? "" :util.ConvertString(B[10])))){
                        bookingAirSummaryView.setInvoice(mapInvoice.get(B[10]== null ? "" :util.ConvertString(B[10])));
                    }else{
                        String refNo = B[0]== null ? "" : util.ConvertString(B[0]);
                        String invoiceNo = getInvoiceMapAirticketDesc(session, refNo);
                        bookingAirSummaryView.setInvoice(invoiceNo);
                    }
                }
                
                if(mapReceipt != null){
                    if(mapInvoice.get(B[10]== null ? "" :util.ConvertString(B[10])) != null 
                            && !"".equals(mapReceipt.get(B[10]== null ? "" :util.ConvertString(B[10])))){
                        bookingAirSummaryView.setReceipt(mapReceipt.get(B[10]== null ? "" :util.ConvertString(B[10])));
                    }else{
                        String refNo = B[0]== null ? "" : util.ConvertString(B[0]);
                        String receiptNo = getReceiptMapAirticketDesc(session, refNo);
                        bookingAirSummaryView.setReceipt(receiptNo);
                    }
                }
                               
                bookingAirSummaryViewList.add(bookingAirSummaryView);
            }
            
        }finally{
            this.sessionFactory.close();
            session.close();
        }
            
        return bookingAirSummaryViewList;
    }

    @Override
    public List<BookingPackageSummaryView> getListBookingPackageSummaryView(String bookRefNo, String bookLeader, String bookDate, String packageName, String packageAgent) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingPackageSummaryView> bookingPackageSummaryViewList = new ArrayList<BookingPackageSummaryView>();
        try{
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
                    .setMaxResults(300)
                    .list();

            boolean hasData = QueryPackage != null && QueryPackage.size() > 0 ? true : false;
            if(!hasData){
                return bookingPackageSummaryViewList;
            }
            
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
            
        }finally{
            this.sessionFactory.close();
            session.close();
        }
               
        return bookingPackageSummaryViewList;
    }

    @Override
    public List<BookingDayTourSummaryView> getListBookingDayTourSummaryView(String bookRefNo, String bookLeader, String bookDate, String tourCode, String tourName, String tourDate, String tourPickUp, String tourAgent) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingDayTourSummaryView> bookingDayTourSummaryViewList = new ArrayList<BookingDayTourSummaryView>();
        try{
//        String query = " SELECT * FROM `booking_daytour_summary_min` ";
            String query = "SELECT bd.*, billd.id AS billid FROM ( SELECT `mt`.`Reference No` AS `refno`, date_format( `mt`.`Create_date`, '%d-%m-%Y' ) AS `refdate`, `agt`.`code` AS `agent`, `GET_LEADER_NAME` (`mt`.`id`) AS `leader`, sum(`dp`.`qty`) AS `pax`, `dt`.`name` AS `tour_name`, `dt`.`code` AS `tour_code`, date_format( `db`.`tour_date`, '%d-%m-%Y' ) AS `tour_date`, ( CASE WHEN (`pl`.`place` = 'OTHERS') THEN `db`.`pickup_detail` ELSE `pl`.`place` END ) AS `pickup`, date_format(`db`.`pickup_time`, '%H:%i') AS `time`, sum(( CASE WHEN (`dp`.`category_id` = 1) THEN `dp`.`qty` ELSE 0 END )) AS `adult`, sum(( CASE WHEN (`dp`.`category_id` = 2) THEN `dp`.`qty` ELSE 0 END )) AS `child`, sum(( CASE WHEN (`dp`.`category_id` = 3) THEN `dp`.`qty` ELSE 0 END )) AS `infant`, `db`.`remark` AS `remark`, '' AS `invoice`, '' AS `receipt`, `db`.`id` AS `id` FROM ((((( `daytour_booking` `db` JOIN `master` `mt` ON ((`mt`.`id` = `db`.`master_id`))) JOIN `agent` `agt` ON ((`agt`.`id` = `mt`.`Agent_id`))) JOIN `daytour` `dt` ON ((`dt`.`id` = `db`.`tour_id`))) JOIN `place` `pl` ON ((`pl`.`id` = `db`.`pickup`))) LEFT JOIN `daytour_booking_price` `dp` ON (( `dp`.`daytour_booking_id` = `db`.`id` ))) subQuery ) bd LEFT JOIN ( SELECT billd.id, billd.ref_item_id FROM billable_desc billd WHERE billd.bill_type = 6 ) billd ON billd.ref_item_id = bd.id ";
            String subQuery = "";
            boolean condition = false;

            if((bookRefNo != null) && (!"".equalsIgnoreCase(bookRefNo))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " `mt`.`Reference No` = '" + bookRefNo + "' " ;
                condition = true;
            }
            if((bookLeader != null) &&(!"".equalsIgnoreCase(bookLeader))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " `GET_LEADER_NAME` (`mt`.`id`) LIKE '%" + bookLeader + "%' COLLATE utf8_unicode_ci " ;
                condition = true;
            }
            if((bookDate != null) &&(!"".equalsIgnoreCase(bookDate))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " date_format(`mt`.`Create_date`,'%d-%m-%Y') = '" + bookDate + "' " ;
                condition = true;
            }
            if((tourCode != null) &&(!"".equalsIgnoreCase(tourCode))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " `dt`.`code` LIKE '%" + tourCode + "%' " ;
                condition = true;
            }
            if((tourName != null) &&(!"".equalsIgnoreCase(tourName))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " `dt`.`name` LIKE '%" + tourName + "%' " ;
                condition = true;
            }
            if((tourDate != null) &&(!"".equalsIgnoreCase(tourDate))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " date_format(`db`.`tour_date`,'%d-%m-%Y') = '" + tourDate + "' " ;
                condition = true;
            }
            if((tourPickUp != null) &&(!"".equalsIgnoreCase(tourPickUp))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " (CASE WHEN (`pl`.`place` = 'OTHERS') THEN `db`.`pickup_detail` ELSE `pl`.`place` END ) LIKE '%" + tourPickUp + "%' " ;
                condition = true;
            }
            if((tourAgent != null) &&(!"".equalsIgnoreCase(tourAgent))){
                subQuery += (condition ? " and " : " where ");
                subQuery += " `agt`.`code` LIKE '%" + tourAgent + "%' " ;
                condition = true;
            }
            subQuery += " group by `db`.`id` ORDER BY `mt`.`Reference No` DESC ";
            query = query.replace("subQuery", subQuery);

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
                    .setMaxResults(300)
                    .list();
            
            boolean hasData = QueryDayTour != null && QueryDayTour.size() > 0 ? true : false;
            if(!hasData){
                return bookingDayTourSummaryViewList;
            }

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
            
        }finally{
            this.sessionFactory.close();
            session.close();
        }
               
        return bookingDayTourSummaryViewList;
    }

    @Override
    public List<BookingOtherSummaryView> getListBookingOtherSummaryView(String bookRefNo, String bookLeader, String bookDate, String otherCode, String otherName, String otherDate, String otherAgent) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingOtherSummaryView> bookingOtherSummaryViewList = new ArrayList<BookingOtherSummaryView>();
        try{
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
                    .setMaxResults(300)
                    .list();
            
            boolean hasData = QueryOther != null && QueryOther.size() > 0 ? true : false;
            if(!hasData){
                return bookingOtherSummaryViewList;
            }

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
        
        } finally {
            this.sessionFactory.close();
            session.close();
        }
               
        return bookingOtherSummaryViewList;
    }

    @Override
    public List<BookingLandSummaryView> getListBookingLandSummaryView(String bookRefNo, String bookLeader, String bookDate, String landOkBy, String landAgent, String landCategory) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<BookingLandSummaryView> bookingLandSummaryViewList = new ArrayList<BookingLandSummaryView>();
        try{
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
                query += " ok_by LIKE '%" + landOkBy + "%' " ;
                condition = true;
            }
            if((landAgent != null) &&(!"".equalsIgnoreCase(landAgent))){
                query += (condition ? " and " : " where ");
                query += " agent LIKE '%" + landAgent + "%' " ;
                condition = true;
            }
            if((landCategory != null) &&(!"".equalsIgnoreCase(landCategory))){
                query += (condition ? " and " : " where ");
                query += " category LIKE '%" + landCategory + "%' " ;
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
                    .setMaxResults(300)
                    .list();
            
            boolean hasData = QueryLand != null && QueryLand.size() > 0 ? true : false;
            if(!hasData){
                return bookingLandSummaryViewList;
            }

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
        
        }finally{
            this.sessionFactory.close();
            session.close();
        }
        
        return bookingLandSummaryViewList;
    }

    private BookingView mappingBookingViewMinToBookingView(Object[] A) {
        UtilityFunction util = new UtilityFunction();
        BookingView bookingView = new BookingView();
        bookingView.setRefno(A[0] != null ? util.ConvertString(A[0]) : "");
        bookingView.setAgentCode(A[1] != null ? util.ConvertString(A[1]) : "");
        bookingView.setLeaderName(A[2] != null ? util.ConvertString(A[2]) : "");
        bookingView.setStatusName(A[3] != null ? util.ConvertString(A[3]) : "");
        bookingView.setPnr(A[4] != null ? util.ConvertString(A[4]) : "");
        bookingView.setHotelName(A[5] != null ? util.ConvertString(A[5]) : "");
        bookingView.setCreateDate(A[6] != null ? util.convertStringToDate(util.ConvertString(A[6])) : null);
        bookingView.setCreateBy(A[7] != null ? util.ConvertString(A[7]) : "");
        bookingView.setDepartmentName(A[8] != null ? util.ConvertString(A[8]) : "");
        bookingView.setDepartmentId(A[9] != null ? util.ConvertString(A[9]) : "");
        bookingView.setStatusId(A[10] != null ? util.ConvertString(A[10]) : "");
        bookingView.setTel(A[11] != null ? util.ConvertString(A[11]) : "");
        bookingView.setRemark(A[12] != null ? util.ConvertString(A[12]) : "");
        bookingView.setEmail(A[13] != null ? util.ConvertString(A[13]) : "");
        bookingView.setPayBy(A[14] != null ? util.ConvertString(A[14]) : "");
        bookingView.setAccId(A[15] != null ? util.ConvertString(A[15]) : "");
        bookingView.setTourCode(A[16] != null ? util.ConvertString(A[16]) : "");
//        bookingView.setRefno(bookingViewMin.getRefno() != null && !"".equals(bookingViewMin.getRefno()) ? bookingViewMin.getRefno() : "");
//        bookingView.setAgentCode(bookingViewMin.getAgentCode() != null && !"".equals(bookingViewMin.getAgentCode()) ? bookingViewMin.getAgentCode() : "");
//        bookingView.setLeaderName(bookingViewMin.getLeaderName() != null && !"".equals(bookingViewMin.getLeaderName()) ? bookingViewMin.getLeaderName() : "");
//        bookingView.setStatusName(bookingViewMin.getStatusName() != null && !"".equals(bookingViewMin.getStatusName()) ? bookingViewMin.getStatusName() : "");
//        bookingView.setPnr(bookingViewMin.getPnr() != null && !"".equals(bookingViewMin.getPnr()) ? bookingViewMin.getPnr() : "");
//        bookingView.setHotelName(bookingViewMin.getHotelName() != null && !"".equals(bookingViewMin.getHotelName()) ? bookingViewMin.getHotelName() : "");
//        bookingView.setCreateDate(bookingViewMin.getCreateDate() != null ? bookingViewMin.getCreateDate() : null);
//        bookingView.setCreateBy(bookingViewMin.getCreateBy() != null && !"".equals(bookingViewMin.getCreateBy()) ? bookingViewMin.getCreateBy() : "");
//        bookingView.setDepartmentName(bookingViewMin.getDepartmentName() != null && !"".equals(bookingViewMin.getDepartmentName()) ? bookingViewMin.getDepartmentName() : "");
//        bookingView.setDepartmentId(bookingViewMin.getDepartmentId() != null && !"".equals(bookingViewMin.getDepartmentId()) ? bookingViewMin.getDepartmentId() : "");
//        bookingView.setStatusId(bookingViewMin.getStatusId() != null && !"".equals(bookingViewMin.getStatusId()) ? bookingViewMin.getStatusId() : "");
//        bookingView.setTel(bookingViewMin.getTel() != null && !"".equals(bookingViewMin.getTel()) ? bookingViewMin.getTel() : "");
//        bookingView.setRemark(bookingViewMin.getRemark() != null && !"".equals(bookingViewMin.getRemark()) ? bookingViewMin.getRemark() : "");
//        bookingView.setEmail(bookingViewMin.getEmail() != null && !"".equals(bookingViewMin.getEmail()) ? bookingViewMin.getEmail() : "");
//        bookingView.setTicketNo(bookingViewMin.getTicketNo() != null && !"".equals(bookingViewMin.getTicketNo()) ? bookingViewMin.getTicketNo() : "");
//        bookingView.setPayBy(bookingViewMin.getPayBy() != null && !"".equals(bookingViewMin.getPayBy()) ? bookingViewMin.getPayBy() : "");
//        bookingView.setAccId(bookingViewMin.getAccId() != null && !"".equals(bookingViewMin.getAccId()) ? bookingViewMin.getAccId() : "");
//        bookingView.setTourCode(bookingViewMin.getTourCode() != null && !"".equals(bookingViewMin.getTourCode()) ? bookingViewMin.getTourCode() : "");
        
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

    private String getInvoiceMapAirticketDesc(Session session, String refNo) {
        UtilityFunction util = new UtilityFunction();
        String queryairticketdesc = " SELECT b.*, billd.id AS billid FROM ( SELECT `mt`.`Reference No` AS `refno`, `ad`.`id` AS `airticket_desc_id` FROM `master` `mt` JOIN `airticket_booking` `ab` ON ((`ab`.`master_id` = `mt`.`id`)) INNER JOIN `airticket_desc` `ad` ON (( `ad`.`booking_id` = `ab`.`id` )) WHERE `mt`.`Reference No` = :refNo ORDER BY `mt`.`Reference No` DESC ) b LEFT JOIN ( SELECT billd.id, billd.ref_item_id, billd.bill_type FROM billable_desc billd WHERE billd.bill_type = 7 ) billd ON billd.ref_item_id = b.airticket_desc_id ";
        queryairticketdesc = queryairticketdesc.replace(":refNo", refNo);
        List<Object[]> QueryAirticketDesc = session.createSQLQuery(queryairticketdesc)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("airticket_desc_id", Hibernate.STRING)
                .addScalar("billid", Hibernate.STRING)
                .list();
        
        String invoiceNo = "";
        String billdescid = "";
        for (Object[] B : QueryAirticketDesc) {
            if(B[2] != null){
                billdescid += ",";
            }
            billdescid += B[2] == null ? "" : util.ConvertString(B[2]);
        }
        
        if(QueryAirticketDesc.isEmpty()){
            return invoiceNo;
        }
              
        String[] billdescidList = billdescid.split(",");
        Map<String, String> mapInvoice = getInvoiceMap(session, billdescid);
        for(int i = 0; i < billdescidList.length; i++){
            if(i == 0){
                invoiceNo = mapInvoice.get(billdescidList[i]) != null && !"".equals(mapInvoice.get(billdescidList[i])) ? mapInvoice.get(billdescidList[i]) : "";
            
            } else {
                String invoiceTemp = mapInvoice.get(billdescidList[i]) != null && !"".equals(mapInvoice.get(billdescidList[i])) ? mapInvoice.get(billdescidList[i]) : "";
                invoiceNo = invoiceTemp.length() > invoiceNo.length() ? invoiceTemp : invoiceNo;
            }
        }
        
        return invoiceNo;
    }

    private String getReceiptMapAirticketDesc(Session session, String refNo) {
        UtilityFunction util = new UtilityFunction();
        String queryairticketdesc = " SELECT b.*, billd.id AS billid FROM ( SELECT `mt`.`Reference No` AS `refno`, `ad`.`id` AS `airticket_desc_id` FROM `master` `mt` JOIN `airticket_booking` `ab` ON ((`ab`.`master_id` = `mt`.`id`)) INNER JOIN `airticket_desc` `ad` ON (( `ad`.`booking_id` = `ab`.`id` )) WHERE `mt`.`Reference No` = :refNo ORDER BY `mt`.`Reference No` DESC ) b LEFT JOIN ( SELECT billd.id, billd.ref_item_id, billd.bill_type FROM billable_desc billd WHERE billd.bill_type = 7 ) billd ON billd.ref_item_id = b.airticket_desc_id ";
        queryairticketdesc = queryairticketdesc.replace(":refNo", refNo);
        List<Object[]> QueryAirticketDesc = session.createSQLQuery(queryairticketdesc)
                .addScalar("refno", Hibernate.STRING)
                .addScalar("airticket_desc_id", Hibernate.STRING)
                .addScalar("billid", Hibernate.STRING)
                .list();
        
        String receiptNo = "";
        String billdescid = "";
        for (Object[] B : QueryAirticketDesc) {
            if(B[2] != null){
                billdescid += ",";
            }
            billdescid += B[2] == null ? "" : util.ConvertString(B[2]);
        }
        
        if(QueryAirticketDesc.isEmpty()){
            return receiptNo;
        }
        
        String[] billdescidList = billdescid.split(",");
        Map<String, String> mapReceipt = getReceiptMap(session, billdescid);
        for(int i = 0; i < billdescidList.length; i++){
            if(i == 0){
                receiptNo = mapReceipt.get(billdescidList[i]) != null && !"".equals(mapReceipt.get(billdescidList[i])) ? mapReceipt.get(billdescidList[i]) : "";
            
            } else {
                String receiptTemp = mapReceipt.get(billdescidList[i]) != null && !"".equals(mapReceipt.get(billdescidList[i])) ? mapReceipt.get(billdescidList[i]) : "";
                receiptNo = receiptTemp.length() > receiptNo.length() ? receiptTemp : receiptNo;
            }
        }
        
        return receiptNo;
    }
    
}
