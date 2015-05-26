/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.common.HibernateSession;
import com.smi.travel.datalayer.dao.AirticketBookingDao;
import com.smi.travel.datalayer.dao.AirticketPnrDao;
import com.smi.travel.datalayer.entity.AirticketBooking;
import com.smi.travel.datalayer.entity.AirticketDesc;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.AirticketPnr;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class AirticketBookingImpl implements AirticketBookingDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String SEARCHBOOKINGAIR = "from AirticketBooking Book  where Book.master.referenceNo=:refno";
    private static final String SEARCHBOOKINGPNR = "from AirticketPnr airpnr  where airpnr.pnr=:pnrno and airpnr.airticketBooking.master.referenceNo = :refno";
    private static final String SEARCHBOOKINGPNRID = "from AirticketPnr airpnr  where airpnr.id=:pnrid and airpnr.airticketBooking.master.referenceNo = :refno";
    private static final String NUMOFFLIGHT = "from AirticketFlight flight where flight.airticketAirline.airticketPnr.airticketBooking.master.referenceNo =:refno";

    public AirticketBooking getBookDetailAir(String refno) {
        Session session = this.sessionFactory.openSession();
        List<AirticketBooking> BookingAirList = session.createQuery(SEARCHBOOKINGAIR).setParameter("refno", refno).list();
        if (BookingAirList.isEmpty()) {
            return null;
        }
        return BookingAirList.get(0);
    }

    public AirticketPnr getPNRDetail(String PNR,String refno) {
        Session session = this.sessionFactory.openSession();
        List<AirticketPnr> AirPNRList = session.createQuery(SEARCHBOOKINGPNR).setParameter("pnrno", PNR).setParameter("refno", refno).list();
        if (AirPNRList.isEmpty()) {
            return null;
        }
        return AirPNRList.get(0);
    }

    @Override
    public int insertBookingAirTicket(AirticketBooking booking) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(booking);
            List<AirticketDesc> airticketDescsList = new ArrayList<AirticketDesc>(booking.getAirticketDescs());
            for (int i = 0; i < airticketDescsList.size(); i++) {
                session.save(airticketDescsList.get(i));
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
    public int getNumberOfFlight(String refno) {
        int result = 0;
        Session session = this.sessionFactory.openSession();
        List<AirticketFlight> AirFlightList = session.createQuery(NUMOFFLIGHT).setParameter("refno", refno).list();
        if (AirFlightList.isEmpty()) {
            return 0;
        }
        session.close();
        return AirFlightList.size();
    }

    @Override
    public int updateBookingAirTicket(AirticketBooking booking) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String refNo = booking.getMaster().getReferenceNo();
            List<AirticketBooking> BookingAirList = session.createQuery(SEARCHBOOKINGAIR).setParameter("refno", refNo).list();
            if (BookingAirList.isEmpty()) {
                return 0;
            }
            AirticketBooking dbBooking = BookingAirList.get(0);
//            booking.getStaffByOwnerBy().getName();
            dbBooking.setStaffByOwnerBy(booking.getStaffByOwnerBy());
            dbBooking.setStaffByIssueBy(booking.getStaffByIssueBy());
            dbBooking.setDeadline(booking.getDeadline());
            dbBooking.setReConfirm(booking.getReConfirm());
            dbBooking.setRemark(booking.getRemark());
            session.update(dbBooking);
            List<AirticketDesc> airticketDescsList = new ArrayList<AirticketDesc>(booking.getAirticketDescs());
            for (int i = 0; i < airticketDescsList.size(); i++) {
                if (airticketDescsList.get(i).getId() == null) {
                    session.save(airticketDescsList.get(i));
                } else {
                    session.update(airticketDescsList.get(i));
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
    public int DeleteDesc(AirticketBooking booking, String descId) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
             for (AirticketDesc airticketDesc : new ArrayList<AirticketDesc>(booking.getAirticketDescs())) {
                if (descId.equalsIgnoreCase(airticketDesc.getId())) {
                    airticketDesc.setId(descId);
                    session.delete(airticketDesc);
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

    @Override
    public AirticketPnr getPNRDetailByID(String PNRID, String refno) {
        Session session = this.sessionFactory.openSession();
        List<AirticketPnr> AirPNRList = session.createQuery(SEARCHBOOKINGPNRID).setParameter("pnrid", PNRID).setParameter("refno", refno).list();
        if (AirPNRList.isEmpty()) {
            return null;
        }
        return AirPNRList.get(0);
    }

}
