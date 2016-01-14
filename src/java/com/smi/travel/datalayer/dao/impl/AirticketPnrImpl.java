/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.AirticketPnrDao;
import com.smi.travel.datalayer.entity.AirticketAirline;
import com.smi.travel.datalayer.entity.AirticketBooking;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.BookingAirline;
import com.smi.travel.datalayer.entity.BookingPnr;
import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.entity.MFlightservice;
import com.smi.travel.datalayer.entity.MTicketType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author Surachai
 */
public class AirticketPnrImpl implements AirticketPnrDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String AIRTICKETPNR = "from AirticketPnr pnr  where pnr.master.referenceNo =:refno";

    @Override
    public AirticketPnr getAirticketDetail(String refno) {
        Session session = this.sessionFactory.openSession();
        List<AirticketPnr> BookingList = session.createQuery(AIRTICKETPNR).setParameter("refno", refno).list();
        if (BookingList.isEmpty()) {
            return null;
        }
        return BookingList.get(0);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int insertAirticketPnr(AirticketPnr airPnr) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(airPnr);
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
    public int UpdateAirticketPnr(AirticketPnr airPnr) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            AirticketPnr dbAirPnr = (AirticketPnr) session.get(AirticketPnr.class, airPnr.getId());
            
            dbAirPnr.setId(airPnr.getId());
            dbAirPnr.setPnr(airPnr.getPnr());
            dbAirPnr.setSubpnr(airPnr.getSubpnr());
            
            Set<AirticketAirline> airlines = airPnr.getAirticketAirlines();
            for (AirticketAirline airline : airlines) {
                if (StringUtils.isEmpty(airline.getId())) {
                    dbAirPnr.getAirticketAirlines().add(airline);
                } else {
                    Set<AirticketFlight> flights = airline.getAirticketFlights();
                    for (AirticketFlight flight : flights) {
                        if (StringUtils.isEmpty(flight.getId())) {
                            // new flight
                            AirticketAirline dbAirline = (AirticketAirline) session.get(AirticketAirline.class, airline.getId());
                            dbAirline.getAirticketFlights().add(flight);
                            session.saveOrUpdate(dbAirline);
                        } else {
                            // update flight                       
                            AirticketFlight dbFlight = (AirticketFlight) session.get(AirticketFlight.class, flight.getId());
                            
                            //might need to update airline.
                            if (!flight.getAirticketAirline().getId().equalsIgnoreCase(dbFlight.getAirticketAirline().getId())) {
//                                AirticketAirline oldDbAirline = (AirticketAirline) session.get(AirticketAirline.class, dbFlight.getAirticketAirline().getId());
//                                oldDbAirline.getAirticketFlights().remove(dbFlight);
//                                AirticketAirline newDbAirline = (AirticketAirline) session.get(AirticketAirline.class, flight.getAirticketAirline().getId());
//                                newDbAirline.getAirticketFlights().add(dbFlight);
//                                dbFlight.setAirticketAirline(newDbAirline);
                                
//                                session.saveOrUpdate(oldDbAirline);
//                                session.saveOrUpdate(newDbAirline);
                                
                            }
                            BeanUtils.copyProperties(flight, dbFlight, new String[]{"id"});
                            
                            session.saveOrUpdate(dbFlight);
                        }
                    }

                    Set<AirticketPassenger> passengers = airline.getAirticketPassengers();
                    for (AirticketPassenger passenger : passengers) {
                        if (StringUtils.isEmpty(passenger.getId())) {
                            // new passenger
                            AirticketAirline dbAirline = (AirticketAirline) session.get(AirticketAirline.class, airline.getId());
                            dbAirline.getAirticketPassengers().add(passenger);
                            session.saveOrUpdate(dbAirline);
                        } else {
                            // upddbAirlineate passenger
                            AirticketPassenger dbPassenger = (AirticketPassenger) session.get(AirticketPassenger.class, passenger.getId());
                            BeanUtils.copyProperties(passenger, dbPassenger, new String[]{"id"});
                            session.saveOrUpdate(dbPassenger);
                        }
                    }

                    if (airline.getAirticketFlights().isEmpty() && airline.getAirticketPassengers().isEmpty()) {
                        AirticketAirline dbAirline = (AirticketAirline) session.get(AirticketAirline.class, airline.getId());
//                        dbAirline.getAirticketFlights().clear();
//                        dbAirline.getAirticketPassengers().clear();
                        
                        dbAirPnr.getAirticketAirlines().remove(dbAirline);
//                        session.delete(dbAirline);
                    }
                }
            }
            
            session.update(dbAirPnr);
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    private AirticketPnr findAirticketPnr(String pnr, AirticketBooking airBook) {
        Iterator<AirticketPnr> iterator = airBook.getAirticketPnrs().iterator();
        while (iterator.hasNext()) {
            AirticketPnr currentAirPnr = iterator.next();
            if (pnr.equalsIgnoreCase(pnr)) {
                return currentAirPnr;
            }
        }

        return null;
    }

    public int importExistingAirticketPnr(AirticketPnr newAirPnr) {
        int result = 0;
        AirticketBooking existAirBook = newAirPnr.getAirticketBooking();
        try {
            Set listNewAirlines = newAirPnr.getAirticketAirlines();
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            AirticketBooking currentAirBook = (AirticketBooking) session.get(AirticketBooking.class, existAirBook.getId());

            AirticketPnr currentAirPnr = findAirticketPnr(newAirPnr.getPnr(), currentAirBook);
            if (currentAirPnr == null) {
                return result;
            }

            Set listCurrentAirlines = currentAirPnr.getAirticketAirlines();

            Iterator<AirticketAirline> iteratorNewAirline = listNewAirlines.iterator();

            while (iteratorNewAirline.hasNext()) {
                AirticketAirline newAirline = iteratorNewAirline.next();
                if (!isExistAirline(newAirline, listCurrentAirlines)) {
                    currentAirPnr.getAirticketAirlines().add(newAirline);
                    newAirline.setAirticketPnr(currentAirPnr);
                }
            }

            session.save(currentAirPnr);
            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    private boolean isExistAirline(AirticketAirline newAirline, Set currentAirlineSet) {
        Iterator<AirticketAirline> iteratorCurrentAirline = currentAirlineSet.iterator();
        while (iteratorCurrentAirline.hasNext()) {

            AirticketAirline currentAirline = iteratorCurrentAirline.next();
            if (currentAirline.getMAirline().getName().equalsIgnoreCase(newAirline.getMAirline().getName())) {
                System.out.println("Duplicate airline " + currentAirline.getMAirline().getName());
                return true;
            }
        }
        return false;
    }

    @Override
    public int cancelBookAirticketPNR(String PNRID) {
        int result = 0;
        String hql = "UPDATE AirticketPnr Air set   Air.MItemstatus.id = 3"
                + "WHERE Air.id = :PNRID";
        try {
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("PNRID", PNRID);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public int enableBookAirticketPNR(String PNRID) {
        int result = 0;
        String hql = "UPDATE AirticketPnr Air set   Air.MItemstatus.id = 1"
                + "WHERE Air.id = :PNRID";
        try {
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("PNRID", PNRID);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public int cancelBookAirticketFlight(String FlightID) {
        int result = 0;
        String hql = "UPDATE AirticketFlight flight set   flight.MItemstatus.id = 2"
                + "WHERE flight.id = :FLIGHTID";
        try {
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("FLIGHTID", FlightID);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public int enableBookAirticketFlight(String FlightID) {
        int result = 0;
        String hql = "UPDATE AirticketFlight flight set   flight.MItemstatus.id = 1"
                + "WHERE flight.id = :FLIGHTID";
        try {
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("FLIGHTID", FlightID);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public int deletePassenger(String airPassenger) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            // DELETE PASSENGER
            Query query = session.createQuery("DELETE FROM AirticketPassenger WHERE id =:pId");
            query.setParameter("pId", airPassenger);
            query.executeUpdate();
            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

//    @Override
//    public MFlight MappingFlightClass(String Flight) {
//        MFlight mFlight = null;
//        String query = "from MFlight Flight ";
//        Session session = this.sessionFactory.openSession();
//        List<MFlight> List = session.createQuery(query).list();
//        if (List.isEmpty()) {
//            return null;
//        }
//        if ("ECO".equalsIgnoreCase(Flight)) {
//            for (MFlight F : List) {
//                if (F.getName().equalsIgnoreCase("Economy Class")) {
//                    mFlight = F;
//                }
//            }
//        }
//        session.close();
//        this.sessionFactory.close();
//        return mFlight;
//    }
    @Override
    public MFlight MappingFlightClass(String flightClass) {
        MFlightservice mFlightservice = new MFlightservice();
        MFlight mFlight = new MFlight();
        List<MFlightservice> mFlightserviceList = new ArrayList<MFlightservice>();
        Session session = this.sessionFactory.openSession(); 
        String query = "from MFlightservice f where f.classCode = :classCode";
        mFlightserviceList = session.createQuery(query).setParameter("classCode", flightClass).list();
        if (mFlightserviceList.isEmpty()) {
            return null;
        }
        else{
            mFlightservice = mFlightserviceList.get(0);
            mFlight.setId(mFlightservice.getMFlight().getId());
        }
        session.close();
        this.sessionFactory.close();
        return mFlight;
    }
    
    @Override
    public MTicketType MappintTicketLife(String TicketLife) {
        MTicketType Ticket = null;
        return Ticket;
    }

    @Override
    public String MappingTicketType(String TicketType) {
        if (TicketType == null) {
            return null;
        } else if (TicketType.equalsIgnoreCase("X")) {
            return "I";
        } else if (TicketType.equalsIgnoreCase(" ")) {
            return "D";
        }
        return null;
    }

    @Override
    public List<String> getListPnrFromRefno(String Refno) {
        List<String> pnrList = new LinkedList<String>();
        String query = "from AirticketPnr pnr where  pnr.airticketBooking.master.referenceNo = :refno";
        Session session = this.sessionFactory.openSession();
        List<AirticketPnr> List = session.createQuery(query)
                .setParameter("refno", Refno)
                .list();
        if (List.isEmpty()) {
            session.close();
            this.sessionFactory.close();
            return null;
        }else{
           for(int i=0;i<List.size();i++){
               pnrList.add(List.get(i).getPnr());
           }
        }
       
        session.close();
        this.sessionFactory.close();
        return pnrList;

    }

    @Override
    public int importUpdateAirticketPnr(AirticketPnr airPnr) {
        int result = 0;
        String hql = "update AirticketPnr pnr set pnr.pnr = :newpnr , pnr.subpnr = :subpnr where pnr.id = :id";
        System.out.println(" ============================================ ");
        System.out.println(" airPnr.getPnr() " + airPnr.getPnr());
        System.out.println(" airPnr.getSubpnr() " + airPnr.getSubpnr());
        System.out.println(" airPnr.getId() " + airPnr.getId());
        try {
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("newpnr", airPnr.getPnr());
            query.setParameter("subpnr", airPnr.getSubpnr());
            query.setParameter("id", airPnr.getId());
            System.out.println(" query : " + query);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            session.close();
            this.sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @Override
    public AirticketPnr getAirticketPnrFromId(String id) {
        AirticketPnr airticketPnr = new AirticketPnr();
        
        String query = "from AirticketPnr pnr where  pnr.id = :id";
        Session session = this.sessionFactory.openSession();
        List<AirticketPnr> List = session.createQuery(query)
                .setParameter("id", id)
                .list();
        if (List.isEmpty()) {
//            session.close();
//            this.sessionFactory.close();
            return null;
        }else{
//           for(int i=0;i<List.size();i++){
               airticketPnr = List.get(0);
//           }
        }
//       
//        session.close();
//        this.sessionFactory.close();
        return airticketPnr;    
    }

}
