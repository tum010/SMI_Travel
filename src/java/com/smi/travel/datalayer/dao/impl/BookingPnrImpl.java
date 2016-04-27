/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.BookingPnrDao;
import com.smi.travel.datalayer.entity.BookingAirline;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.BookingPassenger;
import com.smi.travel.datalayer.entity.BookingPnr;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class BookingPnrImpl implements BookingPnrDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String BOOKINGPNR = "from BookingPnr Book  where Book.pnr=:pnrA";
    private static final String bookingPnrQuery = "from BookingPnr";

    @Override
    public BookingPnr getBookingPnr(String PNR) {
        Session session = this.sessionFactory.openSession();
        List<BookingPnr> BookingList = session.createQuery(BOOKINGPNR).setParameter("pnrA", PNR).list();
        if (BookingList.isEmpty()) {
            return null;
        }
        return BookingList.get(0);
    }

    @Override
    public int insertBookingPnr(BookingPnr bPnr) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(bPnr);
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
    public List<BookingPnr> getListBookingPnr() {
        Session session = this.sessionFactory.openSession();
        List<BookingPnr> list = session.createQuery(bookingPnrQuery).list();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public int updateBookingPnr(BookingPnr bPnr) {
        int result = 0;
        boolean updateFilenameFlag = false;
        try {
            Set listNewAirlines = bPnr.getBookingAirlines();
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            List<BookingPnr> BookingList = session.createQuery(BOOKINGPNR).setParameter("pnrA", bPnr.getPnr()).list();
            BookingPnr currentBookingPnr = BookingList.get(0);
            Set listCurrentAirlines = currentBookingPnr.getBookingAirlines();

            Iterator<BookingAirline> iteratorNewAirline = listNewAirlines.iterator();

            while (iteratorNewAirline.hasNext()) {
                BookingAirline newAirline = iteratorNewAirline.next();
                if (!isExistAirline(newAirline, listCurrentAirlines)) {
                    currentBookingPnr.getBookingAirlines().add(newAirline);
                    newAirline.setBookingPnr(currentBookingPnr);
                    updateFilenameFlag = true;
                } else if (!isExistPassenger(newAirline, listCurrentAirlines)) {
                    //Add passenger to same airline.
                    System.out.println("Add more passengers!");
                    addPassengersInAirline(newAirline, listCurrentAirlines);
                    updateFlightsInAirline(newAirline, listCurrentAirlines);
                    updateFilenameFlag = true;
                }

            }

            if (updateFilenameFlag) {
                String newFilename = currentBookingPnr.getFilename() + "," + bPnr.getFilename();
                currentBookingPnr.setFilename(newFilename);
            }
            session.save(currentBookingPnr);
            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private boolean isExistAirline(BookingAirline newAirline, Set currentAirlineSet) {
        Iterator<BookingAirline> iteratorCurrentAirline = currentAirlineSet.iterator();
        while (iteratorCurrentAirline.hasNext()) {

            BookingAirline currentAirline = iteratorCurrentAirline.next();
            if (currentAirline.getAlrlineName().equalsIgnoreCase(newAirline.getAlrlineName())) {
                System.out.println("Duplicate airline " + currentAirline.getAlrlineName());
                return true;
            }
        }
        return false;
    }

    private boolean isExistPassenger(BookingAirline newAirline, Set currentAirlineSet) {

        if (newAirline.getBookingPassengers().isEmpty()) {
            return true;
        }

        BookingPassenger bp = (BookingPassenger) newAirline.getBookingPassengers().iterator().next();
        Set<BookingAirline> bAirlines = currentAirlineSet;
        for (BookingAirline airline : bAirlines) {
            Set<BookingPassenger> passengers = airline.getBookingPassengers();
            for (BookingPassenger passenger : passengers) {
                if (bp.getInitialName().equalsIgnoreCase(passenger.getInitialName())
                        && bp.getFirstName().equalsIgnoreCase(passenger.getFirstName())
                        && bp.getLastName().equalsIgnoreCase(passenger.getLastName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void addPassengersInAirline(BookingAirline newAirline, Set currentAirlineSet) {

        Set<BookingAirline> currentAirlines = currentAirlineSet;

        for (BookingAirline currentAirline : currentAirlines) {
            if (currentAirline.getAlrlineName().equalsIgnoreCase(newAirline.getAlrlineName())) {
                for (BookingPassenger passenger : (Set<BookingPassenger>) newAirline.getBookingPassengers()) {
                    passenger.setBookingAirline(currentAirline);
                }
                currentAirline.getBookingPassengers().addAll(newAirline.getBookingPassengers());
            }
        }
    }

    private void updateFlightsInAirline(BookingAirline newAirline, Set currentAirlineSet) {
        Set<BookingAirline> currentAirlines = currentAirlineSet;

        for (BookingAirline currentAirline : currentAirlines) {
            if (currentAirline.getAlrlineName().equalsIgnoreCase(newAirline.getAlrlineName())) {
                for (BookingFlight newFlight : (Set<BookingFlight>) newAirline.getBookingFlights()) {
                    BookingFlight currentFlight = getFlight(newFlight.getFlightNo(), currentAirline);
                    updateFlightAdult(newFlight, currentFlight);
                    updateFlightChild(newFlight, currentFlight);
                    updateFlightInfant(newFlight, currentFlight);
                }
                currentAirline.getBookingPassengers().addAll(newAirline.getBookingPassengers());
            }
        }
    }
    
    private BookingFlight getFlight(String flightNo, BookingAirline airline) {
        Set<BookingFlight> flights = airline.getBookingFlights();
        for( BookingFlight flight : flights ) {
            if (flightNo.equalsIgnoreCase(flight.getFlightNo())) {
                return flight;
            }
        }
        return new BookingFlight();
    }
    
    private void updateFlightAdult(BookingFlight newFlight, BookingFlight currentFlight) {
        if ((newFlight.getAdCost().compareTo(BigDecimal.ZERO)) != 0 ) {
            currentFlight.setAdCost(newFlight.getAdCost());
            currentFlight.setAdPrice(newFlight.getAdPrice());
            currentFlight.setAdTax(newFlight.getAdTax());
        }
    }
    private void updateFlightChild(BookingFlight newFlight, BookingFlight currentFlight) {
        if ( (newFlight.getChCost().compareTo(BigDecimal.ZERO)) != 0 ) {
            currentFlight.setChCost(newFlight.getChCost());
            currentFlight.setChPrice(newFlight.getChPrice());
            currentFlight.setChTax(newFlight.getChTax());
        }
    }
    private void updateFlightInfant(BookingFlight newFlight, BookingFlight currentFlight) {
        if ( (newFlight.getInCost().compareTo(BigDecimal.ZERO)) != 0 ) {
            currentFlight.setInCost(newFlight.getInCost());
            currentFlight.setInPrice(newFlight.getInPrice());
            currentFlight.setInTax(newFlight.getInTax());
        }
    }

}
