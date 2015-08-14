/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.BillableDao;
import com.smi.travel.datalayer.entity.AirticketAirline;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.Billable;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.HotelBooking;
import com.smi.travel.datalayer.entity.HotelRoom;
import com.smi.travel.datalayer.entity.LandBooking;
import com.smi.travel.datalayer.entity.LandItinerary;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class BillableImpl implements BillableDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String BillQuery = "from Billable B where B.master.referenceNo =:refno ";
    private static final String QUERY_AIRTICKET = "from Billable B where B.master.referenceNo =:refno ";
    private static final String QUERY_OTHERS = "from OtherBooking ot where ot.id = :refitemid";
    private static final String QUERY_LAND = "from LandBooking lb where lb.id =   :refitemid";
    private static final String QUERY_HOTEL = "from HotelBooking hb where hb.id = :refitemid";
    private static final String QUERY_DAYTOUR = "from DaytourBooking db where db.id = :refitemid";
    private static final String SEARCH_MBILL_NAME = "from MBilltype mb where mb.id =:typeId ";
    private static final String OtherBookingUpdate = "UPDATE OtherBooking other set  other.isBill = 1 "
            + "WHERE other.id = :Keyid";
    private static final String LandBookingUpdate = "UPDATE LandBooking land set  land.isBill = 1 "
            + "WHERE land.id = :Keyid";
    private static final String AirBookingUpdate = "UPDATE AirticketFlight air set  air.isBill = 1 "
            + "WHERE air.airticketAirline.id in ";
    private static final String SelectAirLineId = "FROM AirticketFlight F where F.airticketAirline.airticketPnr.airticketBooking.master.id = :Keyid  and F.isBill = 0";
//    private static final String HotelBookingUpdate = "UPDATE HotelBooking hotel set  hotel.isBill = 1 "
//            + "WHERE hotel.master.id = :masterid";
    private static final String HotelBookingUpdate = "UPDATE HotelBooking hotel set  hotel.isBill = 1 "
            + "WHERE hotel.id = :Keyid";
    private static final String AirBookingDescUpdate = "UPDATE AirticketDesc AD set  AD.isBill = 1 "
            + "WHERE AD.id = :Keyid ";
    private static final String SelectAirBookId = "FROM AirticketBooking AB where AB.master.id = :masterid )";
//    private static final String DaytourBookingUpdate = "UPDATE DaytourBooking DB set  DB.isBill = 1 "
//            + "WHERE DB.master.id = :masterid";
    private static final String DaytourBookingUpdate = "UPDATE DaytourBooking DB set  DB.isBill = 1 "
            + "WHERE DB.id = :Keyid";

    @Override
    public Billable getBillableBooking(String refno) {
        Session session = this.sessionFactory.openSession();
        List<Billable> list = session.createQuery(BillQuery).setParameter("refno", refno).list();

        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public int updateBillStatusBooking(String refno, Billable bill) {
        int result = 0;

        try {
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(OtherBookingUpdate);
            query.setParameter("refno", refno);
            result = query.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public int insertBillableBooking(Billable bill) {
        int result = 0;
        HashSet<String> ListBill = new HashSet<String>();
        Session session = this.sessionFactory.openSession();
        try {
            
            transaction = session.beginTransaction();
            session.save(bill);
            if (bill.getBillableDescs() != null) {
                List<BillableDesc> BillDescList = new ArrayList<BillableDesc>(bill.getBillableDescs());
                System.out.println("save getBillableDescs :" + BillDescList.size());
                for (BillableDesc b : BillDescList) {
                    System.out.println("getBillableDescs cost : " + b.getCost());
                    if (b.getId() == null) {
                        b.setBillable(bill);
                        b.setIsBill(1);
                        session.save(b);
                        updateBillDesc(session, b);
                    } else {
                        b.setBillable(bill);
                        b.setIsBill(1);
                        session.update(b);
                        //updateBillDesc(session, b);
                    }
                    ListBill.add(b.getMBilltype().getId());
                }
              //  Iterator<String> listbilltype = ListBill.iterator();

                /*
                 while (listbilltype.hasNext()) {
                 String hql = "";
                 String billtype = listbilltype.next();
                 System.out.println("billtype : " + billtype);
                 if (billtype.equalsIgnoreCase("1")) {
                 List<AirticketAirline> list = session.createQuery(SelectAirLineId).setParameter("masterid", bill.getMaster().getId()).list();
                 String AirlineList = "(";

                 for (int i = 0; i < list.size(); i++) {
                 AirlineList += list.get(i).getId() + ",";
                 }
                 AirlineList = AirlineList.substring(0, AirlineList.length() - 1) + ")";
                 hql = AirBookingUpdate + AirlineList;

                 //update Airticket_desc
                 String AirBookList = "";
     
                 List<AirticketBooking> listAirBook = session.createQuery(SelectAirBookId).setParameter("masterid", bill.getMaster().getId()).list();
                 for (int i = 0; i < listAirBook.size(); i++) {
                 AirBookList += listAirBook.get(i).getId() + ",";
                 }
                 AirBookList = AirBookList.substring(0, AirBookList.length() - 1) + "";
                 System.out.println("update Book Desc HQL : "+AirBookingDescUpdate+" "+AirBookList);
                 Query DescQuery = session.createQuery(AirBookingDescUpdate+" "+AirBookList);
                 DescQuery.executeUpdate();

                 } else if (billtype.equalsIgnoreCase("2")) {
                 System.out.println("update other");
                 hql = OtherBookingUpdate;
                 } else if (billtype.equalsIgnoreCase("3")) {
                 System.out.println("update land");
                 hql = LandBookingUpdate;
                 } else if (billtype.equalsIgnoreCase("4")) {
                 System.out.println("update hotel");
                 hql = HotelBookingUpdate;
                 }else if (billtype.equalsIgnoreCase("6")) {
                 System.out.println("update daytour");
                 hql = DaytourBookingUpdate;
                 }
                    
                    
                 Query query = session.createQuery(hql);
                 if (!billtype.equalsIgnoreCase("1")) {
                 //   query.setParameter("Keyid", );
                 }
                 query.executeUpdate();
                    
                 } */
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            this.sessionFactory.close();
            ex.printStackTrace();
            result = 0;
        }
        return result;

    }
    
    public boolean IsLapAirline(String data,String airlineid){
        boolean result = false;
        if(data.equalsIgnoreCase("")){
            return result;
        }
        String[] path = data.split(",");
        for(int i=0;i<path.length;i++){
            if(airlineid.equalsIgnoreCase(path[i])){
                return true;
            }
        }
        return result;
    }

    public void updateBillDesc(Session session, BillableDesc bill) {
        String hql = "";
        String AirlineId = "";
        String billtype = bill.getMBilltype().getId();
        if (billtype.equalsIgnoreCase("1")) {
            System.out.println("bill.getBillable()"+bill.getBillable());
            System.out.println("bill.master()"+bill.getBillable().getMaster());
            System.out.println("bill.masterid()"+bill.getBillable().getMaster().getId());
            List<AirticketFlight> listAirFlight = session.createQuery(SelectAirLineId).setParameter("Keyid", bill.getBillable().getMaster().getId()).list();
             for(int i=0;i<listAirFlight.size();i++){
                 String airlineId = listAirFlight.get(i).getAirticketAirline().getId();
                 System.out.println(airlineId);     
                 if(!IsLapAirline(AirlineId,airlineId)){
                     AirlineId += airlineId +",";
                 }
             }
             if(!AirlineId.equalsIgnoreCase("")){
                 String refItem = "";
                 refItem = AirlineId.substring(0,AirlineId.length()-1);
                 AirlineId = "("+ AirlineId.substring(0,AirlineId.length()-1) + ")";
                 bill.setRefItemId(refItem);
             }
             System.out.println(AirlineId);
             hql = AirBookingUpdate + " "+AirlineId;
        } else if ((billtype.equalsIgnoreCase("2")) || (billtype.equalsIgnoreCase("8"))) {
            System.out.println("update other");
            hql = OtherBookingUpdate;
        } else if (billtype.equalsIgnoreCase("3")) {
            System.out.println("update land");
            hql = LandBookingUpdate;
        } else if (billtype.equalsIgnoreCase("4")) {
            System.out.println("update hotel");
            hql = HotelBookingUpdate;
        } else if (billtype.equalsIgnoreCase("6")) {
            System.out.println("update daytour");
            hql = DaytourBookingUpdate;
        } else if (billtype.equalsIgnoreCase("7")) {
            System.out.println("update Air ticket additional");
            hql = AirBookingDescUpdate;
        }
        Query query = session.createQuery(hql);
        if (!billtype.equalsIgnoreCase("1")) {
            query.setParameter("Keyid", bill.getRefItemId());
        }
        query.executeUpdate();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int updateBillableBooking(Billable bill) {
        int result = 0;
        HashSet<String> ListBill = new HashSet<String>();
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(bill);
            if (bill.getBillableDescs() != null) {
                List<BillableDesc> BillDescList = new ArrayList<BillableDesc>(bill.getBillableDescs());
                System.out.println("save getBillableDescs :" + BillDescList.size());
                for (BillableDesc b : BillDescList) {
                    System.out.println("getBillableDescs cost : " + b.getCost());
                    if (b.getId() == null) {
                        b.setBillable(bill);
                        b.setIsBill(1);
                        session.save(b);
                        updateBillDesc(session, b);
                    } else {
                        b.setBillable(bill);
                        b.setIsBill(1);
                        session.update(b);
                       // updateBillDesc(session, b);
                    }
                    ListBill.add(b.getMBilltype().getId());
                }
            //    Iterator<String> listbilltype = ListBill.iterator();
                /*
                 while (listbilltype.hasNext()) {
                 String hql = "";
                 String billtype = listbilltype.next();
                 System.out.println("billtype : " + billtype);
                 if (billtype.equalsIgnoreCase("1")) {
                 List<AirticketAirline> list = session.createQuery(SelectAirLineId).setParameter("masterid", bill.getMaster().getId()).list();
                 String AirlineList = "(";

                 for (int i = 0; i < list.size(); i++) {
                 AirlineList += list.get(i).getId() + ",";
                 }
                 AirlineList = AirlineList.substring(0, AirlineList.length() - 1) + ")";
                 hql = AirBookingUpdate + AirlineList;

                 //update Airticket_desc
                 String AirBookList = "";
     
                 List<AirticketBooking> listAirBook = session.createQuery(SelectAirBookId).setParameter("masterid", bill.getMaster().getId()).list();
                 for (int i = 0; i < listAirBook.size(); i++) {
                 AirBookList += listAirBook.get(i).getId() + ",";
                 }
                 AirBookList = AirBookList.substring(0, AirBookList.length() - 1) + "";
                 System.out.println("update Book Desc HQL : "+AirBookingDescUpdate+" "+AirBookList);
                 Query DescQuery = session.createQuery(AirBookingDescUpdate+" "+AirBookList);
                 DescQuery.executeUpdate();
                 } else if (billtype.equalsIgnoreCase("2")) {
                 List<AirticketAirline> list = session.createQuery(SelectAirLineId).setParameter("masterid", bill.getMaster().getId()).list();
                 String AirlineList = "(";
                 for (int i = 0; i < list.size(); i++) {
                 AirlineList += list.get(i).getId() + ",";
                 }
                 AirlineList = AirlineList.substring(0, AirlineList.length() - 1) + ")";
                 System.out.println("AirlineList :" + AirlineList);
                 System.out.println("update other");
                 hql = OtherBookingUpdate;
                 } else if (billtype.equalsIgnoreCase("3")) {
                 System.out.println("update land");
                 hql = LandBookingUpdate;
                 } else if (billtype.equalsIgnoreCase("4")) {
                 System.out.println("update hotel");
                 hql = HotelBookingUpdate;
                 } else if (billtype.equalsIgnoreCase("6")) {
                 System.out.println("update daytour");
                 hql = DaytourBookingUpdate;
                 }
                 Query query = session.createQuery(hql);
                 if (!billtype.equalsIgnoreCase("1")) {
                 query.setParameter("Keyid", bill.getMaster().getId());
                 }
                 query.executeUpdate();

                 } */

            }

            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @Override
    public String getMBillTypeName(String typeId) {
        String typeName = "";
        Session session = this.sessionFactory.openSession();
        List<MBilltype> list = session.createQuery(SEARCH_MBILL_NAME).setParameter("typeId", typeId).list();

        if (list.isEmpty()) {
            return null;
        }else{
            typeName = list.get(0).getName();
        }

        return typeName;
    }

    @Override
    public String getDescriptionInvoiceAirTicket(String refno) {
        String description = "";
        Session session = this.sessionFactory.openSession();
        List<AirticketAirline> list = session.createQuery(QUERY_AIRTICKET).setParameter("refitemid", refno).list();

        if (list.isEmpty()) {
            return null;
        }else{
            UtilityFunction utility = new UtilityFunction();
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getAirticketFlights() != null){ // flight
                    description += ""+list.get(i).getAirticketFlights() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getAirticketPassengers() != null){ // passengers
                    description += ""+ list.get(i).getAirticketPassengers() +"|";
                }else{
                     description += " |";
                }
                List<AirticketFlight> listFight = new LinkedList<AirticketFlight>(list.get(i).getAirticketFlights());
                if(utility.GetRounting(listFight) != null){ // flight
                    description += ""+ utility.GetRounting(listFight) +"|";
                }else{
                     description += " |";
                }
                if(listFight.get(i).getMTicketType().getName() != null){ // name flight
                    description += ""+listFight.get(i).getMTicketType().getName() +"|";
                }else{
                     description += " |";
                }
                if(listFight.get(i).getDepartDate() != null){ // depart date
                    description += ""+listFight.get(i).getDepartDate() +"|";
                }else{
                     description += " |";
                }
                if(listFight.get(i).getFlightNo() != null){ // flight no
                    description += ""+listFight.get(i).getFlightNo() +"|";
                }else{
                     description += " |";
                }
                List<AirticketPassenger> listPassenger = new LinkedList<AirticketPassenger>(list.get(i).getAirticketPassengers());
                if(listPassenger.get(i).getMInitialname().getName() != null){// prename
                    description += ""+listPassenger.get(i).getMInitialname().getName() +"|";
                }else{
                     description += " |";
                }
                if(listPassenger.get(i).getLastName() != null){// lastname
                    description += ""+listPassenger.get(i).getLastName() +"|";
                }else{
                     description += " |";
                }
                if(listPassenger.get(i).getFirstName() != null){// firstname
                    description += ""+listPassenger.get(i).getFirstName() +"|";
                }else{
                     description += " |";
                }
            }
        }

        return description;
    }

    @Override
    public String getDescriptionInvoiceOthers(String refno) {
        String description = "";
        Session session = this.sessionFactory.openSession();
        List<OtherBooking> list = session.createQuery(QUERY_OTHERS).setParameter("refitemid", refno).list();

        if (list.isEmpty()) {
            return null;
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMaster().getReferenceNo() != null){ // Ref no
                    description += ""+list.get(i).getMaster().getReferenceNo() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getMInitialname().getName() != null){ // prename
                    description += ""+list.get(i).getMaster().getCustomer().getMInitialname().getName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                    description += ""+list.get(i).getMaster().getCustomer().getLastName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                    description += ""+list.get(i).getMaster().getCustomer().getFirstName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getProduct().getName() != null){ // Product Name
                    description += ""+list.get(i).getProduct().getName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getOtherDate() != null){ // Other Date
                    description += ""+list.get(i).getOtherDate() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getAdCost() != null){ // Adult Cost
                    description += "("+list.get(i).getAdCost() +" x ";
                    if(list.get(i).getAdQty() != null){ // Adult Qty
                        description += ""+list.get(i).getAdQty() +")|";
                    }else{
                        description += "0)|";
                    }
                }else{
                     description += " |";
                }
                if(list.get(i).getChCost() != null){ // Children Cost
                    description += "("+list.get(i).getChCost() +" x ";
                    if(list.get(i).getChQty()!= null){ // Children Qty
                        description += ""+list.get(i).getChQty() +")|";
                    }else{
                        description += "0)|";
                    }
                }else{
                     description += " |";
                }
                if(list.get(i).getInCost() != null){ // Infant Cost
                    description += "("+list.get(i).getInCost() +" x ";
                    if(list.get(i).getInQty()!= null){ // Infant Qty
                        description += ""+list.get(i).getInQty() +")|";
                    }else{
                        description += "0)|";
                    }
                }else{
                     description += " |";
                }
            }
        }

        return description;
    }

    @Override
    public String getDescriptionInvoiceLand(String refno) {
        String description = "";
        Session session = this.sessionFactory.openSession();
        List<LandBooking> list = session.createQuery(QUERY_LAND).setParameter("refitemid", refno).list();

        if (list.isEmpty()) {
            return null;
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMaster().getReferenceNo() != null){ // Ref no
                    description += ""+list.get(i).getMaster().getReferenceNo() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getMInitialname().getName() != null){ // prename
                    description += ""+list.get(i).getMaster().getCustomer().getMInitialname().getName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                    description += ""+list.get(i).getMaster().getCustomer().getLastName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                    description += ""+list.get(i).getMaster().getCustomer().getFirstName() +"|";
                }else{
                     description += " |";
                }
                List<LandItinerary> listLand = new LinkedList<LandItinerary>();
                listLand = list.get(i).getLandItineraries();
                for (int j = 0; j < listLand.size(); j++) {
                    if(listLand.get(j).getDayDate() != null){ //day date
                        description += ""+listLand.get(j).getDayDate() +"|";
                    }else{
                         description += " |";
                    }
                    if(listLand.get(j).getDescription() != null){ // description
                        description += ""+listLand.get(j).getDescription() +"|";
                    }else{
                         description += " |";
                    }
                }
                
                if(list.get(i).getMaster().getBookingType() != null){ // Qty
                    if("I".equals(list.get(i).getMaster().getBookingType())){
                        int sum = 0;
                        int adult = list.get(i).getInboundQty();
                        int child = list.get(i).getInboundChQty();
                        int infant = list.get(i).getInboundInQty();
                        sum = adult + child + infant;
                        if(sum != 0){
                            description += ""+ sum +"|";
                        }else{
                            description += "0|";
                        } 
                    }else if ("O".equals(list.get(i).getMaster().getBookingType())){
                        int sum = 0;
                        int adult = list.get(i).getOutboundAdQty();
                        int child = list.get(i).getOutboundChQty();
                        int infant = list.get(i).getOutboundInQty();
                        sum = adult + child + infant;
                        if(sum != 0){
                            description += ""+ sum +"|";
                        }else{
                            description += "0|";
                        } 
                    }else{
                        description += "0|";
                    }
                }else{
                     description += " |";
                }        
            }
        }

        return description;
    }

    @Override
    public String getDescriptionInvoiceHotel(String refno) {
        String description = "";
        Session session = this.sessionFactory.openSession();
        List<HotelBooking> list = session.createQuery(QUERY_HOTEL).setParameter("refitemid", refno).list();

        if (list.isEmpty()) {
            return null;
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMaster().getReferenceNo() != null){ // Ref no
                    description += ""+list.get(i).getMaster().getReferenceNo() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getMInitialname().getName() != null){ // prename
                    description += ""+list.get(i).getMaster().getCustomer().getMInitialname().getName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                    description += ""+list.get(i).getMaster().getCustomer().getLastName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                    description += ""+list.get(i).getMaster().getCustomer().getFirstName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getHotel().getName() != null){ // Hotel Name
                    description += ""+list.get(i).getHotel().getName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getCheckin() != null){ // check in
                    description += ""+list.get(i).getCheckin() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getCheckout() != null){ // check out
                    description += ""+list.get(i).getCheckout() +"|";
                }else{
                     description += " |";
                }
                List<HotelRoom> listRoom = new LinkedList<HotelRoom>();
                listRoom = list.get(i).getHotelRooms();
                for (int j = 0; j < listRoom.size(); j++) {
                    if( listRoom.get(j).getPrice() != 0){ // room price
                        description += ""+listRoom.get(j).getPrice() +"|";
                    }else{
                         description += " |";
                    }
                    if(listRoom.get(j).getQty() != 0){ // room qty
                        description += ""+listRoom.get(j).getQty() +"|";
                    }else{
                         description += " |";
                    }
                    if(listRoom.get(j).getRoom() != null){ // room number
                        description += ""+listRoom.get(j).getRoom() +"|";
                    }else{
                         description += " |";
                    }
                    if(listRoom.get(j).getCategory() != null){ // room catagory
                        description += ""+listRoom.get(j).getCategory() +"|";
                    }else{
                         description += " |";
                    }
                }
                
                int day = getDifferenceDays(list.get(i).getCheckin(), list.get(i).getCheckout()); // Day 
                if( day != 0){
                   description += ""+day +"|";
                }else{
                     description += " |";
                }  
            }
        }

        return description;
    }

    @Override
    public String getDescriptionInvoiceDayTour(String refno) {
        String description = "";
        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = session.createQuery(QUERY_DAYTOUR).setParameter("refitemid", refno).list();

        if (list.isEmpty()) {
            return null;
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMaster().getReferenceNo() != null){ // Ref no
                    description += ""+list.get(i).getMaster().getReferenceNo() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getMInitialname().getName() != null){ // prename
                    description += ""+ list.get(i).getMaster().getCustomer().getMInitialname().getName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                    description += ""+list.get(i).getMaster().getCustomer().getLastName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                    description += ""+list.get(i).getMaster().getCustomer().getFirstName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getDaytour().getCode() != null){ // code
                    description += ""+list.get(i).getDaytour().getCode() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getDaytour().getName()!= null){ // name
                    description += ""+list.get(i).getDaytour().getName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getTourDate() != null){ // date
                    description += ""+list.get(i).getTourDate() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getAdult() != 0){ // adult
                    description += " Adult "+ list.get(i).getAdult() +" Pax |";
                }else{
                     description += " |";
                }
                if(list.get(i).getChild() != 0){ // child
                    description += " Child "+ list.get(i).getChild() +" Pax |";
                }else{
                     description += " |";
                }
                if(list.get(i).getInfant() != 0){ // infant
                    description += " Infant "+ list.get(i).getInfant() +" Pax |)";
                }else{
                     description += " |";
                }
            }
        }
        System.out.println("DEscription : " + description);
        return description;
    }
    
    private  int getDifferenceDays(Date d1, Date d2) {
        int daysdiff = 0;
        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
        daysdiff = (int) diffDays;
        return daysdiff;
    }
}
