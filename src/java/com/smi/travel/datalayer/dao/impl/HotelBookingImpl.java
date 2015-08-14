/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.HotelBookingDao;
import com.smi.travel.datalayer.entity.HotelBooking;
import com.smi.travel.datalayer.entity.HotelPassenger;
import com.smi.travel.datalayer.entity.HotelRequest;
import com.smi.travel.datalayer.entity.HotelRoom;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author sumeta
 */
public class HotelBookingImpl implements HotelBookingDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String SEARCHBOOKINGHOTELFROMID = "from HotelBooking h where h.id = :HotelBookingID";

    @Override
    public List<HotelBooking> getListBookingHotel(HotelBooking hotelBooking, int option) {
        String query = "from HotelBooking h";
        //String query = "from HotelBooking h where ";
        Session session = this.sessionFactory.openSession();
        System.out.println("query : " + query);
        List<HotelBooking> HotelList = session.createQuery(query).list();
        
        if (HotelList.isEmpty()) {
            return null;
        }
        return HotelList;
    }

    @Override
    public List<HotelBooking> getListBookingHotelFromRefNo(String RefNo) {
        String query = "from HotelBooking h where h.master.referenceNo=:refno";
        Session session = this.sessionFactory.openSession();
        System.out.println("query : " + query);
        List<HotelBooking> List = session.createQuery(query).setParameter("refno", RefNo).list();

        if (List.isEmpty()) {
            return null;
        }
        return List;
    }

    @Override
    public HotelBooking getBookingHotelFromID(String HotelBookingID) {
        HotelBooking result = new HotelBooking();
        Session session = this.sessionFactory.openSession();
        List<HotelBooking> HotelList = session.createQuery(SEARCHBOOKINGHOTELFROMID).setParameter("HotelBookingID", HotelBookingID).list();
        if (HotelList.isEmpty()) {
            return null;
        } else {
            result = HotelList.get(0);
        }
        return result;
    }

    @Override
    public int insertBookingHotel(HotelBooking hotel) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(hotel);
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
    public int updateBookingHotel(HotelBooking hotel) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Workaround - Hibernate does not allow us to save object that is in another session.
            // Now we cannot get the exsisting/current Hibernate session, so we need to copy values to a new object from another session to this new session.
            List<HotelBooking> HotelList = session.createQuery(SEARCHBOOKINGHOTELFROMID).setParameter("HotelBookingID", hotel.getId()).list();
            if (HotelList.isEmpty()) {
                return 0;
            }
            HotelBooking hotelBooking = HotelList.get(0);
            hotelBooking.setAdult(hotel.getAdult());
            hotelBooking.setCheckin(hotel.getCheckin());
            hotelBooking.setCheckout(hotel.getCheckout());
            hotelBooking.setChild(hotel.getChild());
            hotelBooking.setHotel(hotel.getHotel());
            hotelBooking.setHotelRef(hotel.getHotelRef());
            hotelBooking.setInfant(hotel.getInfant());
            hotelBooking.setIsBill(hotel.getIsBill());
            hotelBooking.setMItemstatus(hotel.getMItemstatus());
            hotelBooking.setMMeal(hotel.getMMeal());
            hotelBooking.setMaster(hotel.getMaster());
            hotelBooking.setOrderNo(hotel.getOrderNo());
            hotelBooking.setReconfirm(hotel.getReconfirm());
            hotelBooking.setRemark(hotel.getRemark());
            hotelBooking.setFlight(hotel.getFlight());
            hotelBooking.setDeadline(hotel.getDeadline());
            hotelBooking.setCurrency(hotel.getCurrency());
            session.update(hotelBooking);

            for (HotelPassenger hotelPassenger : new ArrayList<HotelPassenger>(hotel.getHotelPassengers())) {
                session.saveOrUpdate(hotelPassenger);
            }
            for (HotelRequest hotelRequest : new ArrayList<HotelRequest>(hotel.getHotelRequests())) {
                session.saveOrUpdate(hotelRequest);
            }

            for (HotelRoom hotelRoom : new ArrayList<HotelRoom>(hotel.getHotelRooms())) {
                session.saveOrUpdate(hotelRoom);
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
    public int DeleteBookingHotel(HotelBooking hotel) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(hotel);
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
    public int cancelBookHotel(String HotelBookingID) {
        int result = 0;
        String hql = "UPDATE HotelBooking hotel set   hotel.MItemstatus.id = 2"
                + "WHERE hotel.id = :HotelBookingID";
        try {
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            query.setParameter("HotelBookingID", HotelBookingID);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public int enableBookHotel(String HotelBookingID) {
        int result = 0;
        //   Date thisDate = new Date();
        String hql = "UPDATE HotelBooking hotel set   hotel.MItemstatus.id = 1"
                + "WHERE hotel.id = :HotelBookingID";
        try {
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(hql);
            //     query.setParameter("thisdate", thisDate);
            query.setParameter("HotelBookingID", HotelBookingID);
            result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }
    
    @Override
    public int DeleteRoom(HotelBooking hotel, String roomId) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            for (HotelRoom hotelRoom : new ArrayList<HotelRoom>(hotel.getHotelRooms())) {
                if (roomId.equalsIgnoreCase(hotelRoom.getId())) {
                    hotelRoom.setId(roomId);
                    session.delete(hotelRoom);
                }
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
    public int DeleteRequest(HotelBooking hotel, String requestId) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            for (HotelRequest hotelRequest : new ArrayList<HotelRequest>(hotel.getHotelRequests())) {
                if (requestId.equalsIgnoreCase(hotelRequest.getId())) {
                    hotelRequest.setId(requestId);
                    session.delete(hotelRequest);
                }
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
    public int DeletePassenger(HotelBooking hotel, String passengerd) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            for (HotelPassenger hotelPassenger : new ArrayList<HotelPassenger>(hotel.getHotelPassengers())) {
                if(passengerd.equalsIgnoreCase(hotelPassenger.getId())) {
                    hotelPassenger.setId(passengerd);
                    session.delete(hotelPassenger);
                }
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

}
