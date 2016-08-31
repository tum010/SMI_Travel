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
import java.util.ArrayList;
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
        List<BookingFlight> newflighta = new ArrayList<BookingFlight>();
        List<BookingPassenger> newpassengera = new ArrayList<BookingPassenger>();
        
        try {
            System.out.println("update pnr");
            Set listNewAirlines = bPnr.getBookingAirlines();
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            List<BookingPnr> BookingList = session.createQuery(BOOKINGPNR).setParameter("pnrA", bPnr.getPnr()).list();
            BookingPnr currentBookingPnr = BookingList.get(0);
            Set listCurrentAirlines = currentBookingPnr.getBookingAirlines();

            Iterator<BookingAirline> iteratorNewAirline = listNewAirlines.iterator();

            while (iteratorNewAirline.hasNext()) {
                BookingAirline newAirline = iteratorNewAirline.next();
                System.out.println("Air Code" + newAirline.getAirlineCode());
  
                List<BookingAirline> currentAir = new ArrayList<BookingAirline>(currentBookingPnr.getBookingAirlines());
                
                //set currentflight
                List<BookingFlight> currentflight = new ArrayList<BookingFlight>();
                for(int i =0;i<currentAir.size();i++){
                    List<BookingFlight> tempflight = new ArrayList<BookingFlight>(currentAir.get(i).getBookingFlights()); 
                    for(int j =0;j<tempflight.size();j++){
                        currentflight.add(tempflight.get(j));
                    }
                }
                for(int k =0;k<currentflight.size();k++){
                    System.out.println("Old Flight : "+currentflight.get(k).getFlightNo());
                }
                
                //set current passenger
                List<BookingPassenger> currentpassenger = new ArrayList<BookingPassenger>();
                for(int i =0;i<currentAir.size();i++){
                    List<BookingPassenger> temppassenger = new ArrayList<BookingPassenger>(currentAir.get(i).getBookingPassengers()); 
                    for(int j =0;j<temppassenger.size();j++){
                        currentpassenger.add(temppassenger.get(j));
                    }
                }
                for(int k =0;k<currentpassenger.size();k++){
                    System.out.println("Old Passenger : "+currentpassenger.get(k).getTicketnoS1()+currentpassenger.get(k).getTicketnoS2()+currentpassenger.get(k).getTicketnoS3());
                }
                
                List<BookingFlight> newflight = new ArrayList<BookingFlight>(newAirline.getBookingFlights());
                List<BookingPassenger> newPassenger = new ArrayList<BookingPassenger>(newAirline.getBookingPassengers());
                int isAirlinelap = 1;
                int isPassengerlap = 1;
                
             
                if (!isExistAirline(newAirline, listCurrentAirlines)) {
                    System.out.println("not isExistAirline");
                    currentBookingPnr.getBookingAirlines().add(newAirline);
                    newAirline.setBookingPnr(currentBookingPnr);
                    updateFilenameFlag = true;
                    isAirlinelap = 0;
                }else if (!isExistPassenger(newAirline, listCurrentAirlines)) {
                    System.out.println("Air Code" + newAirline.getAirlineCode());
                    System.out.println("Add more passengers!");
                    addPassengersInAirline(newAirline, listCurrentAirlines);
                    updateFlightsInAirline(newAirline, listCurrentAirlines);
                    updateFilenameFlag = true;
                    isPassengerlap =0 ;
                }
              
                
                if(isAirlinelap==1) {
                    BookingAirline airline = new BookingAirline();
                    System.out.println("isExistAirline");
                    updateFilenameFlag = true;
                    List<BookingAirline> Airlist = new ArrayList<BookingAirline>(listCurrentAirlines);
                    
                    for(int i =0;i<Airlist.size();i++){
                       System.out.println("Airlist ID:"+Airlist.get(i).getId() + "Airlist CODE:"+Airlist.get(i).getAirlineCode() );
                       if(newAirline.getAirlineCode().equalsIgnoreCase(Airlist.get(i).getAirlineCode())){
                           System.out.println("Airline ID : " +  Airlist.get(i).getId());
                           airline =  Airlist.get(i); 
                       }
                       
                    }
                    for(int j=0;j<newflight.size();j++){
                       // System.out.println("newflight : "+);
                       //check overlap flight
                        boolean islap = false;
                        newflight.get(j).setBookingAirline(airline);
                        //newflighta.add(newflight.get(j));
                        for(int k=0;k<currentflight.size();k++){
                            if(currentflight.get(k).getFlightNo().equalsIgnoreCase(newflight.get(j).getFlightNo())){
                                islap = true;
                                k += currentflight.size();
                            }
                        }
                        
                        if(!islap){
                            System.out.println("add flight not lap : "+newflight.get(j).getFlightNo());
                            newflighta.add(newflight.get(j));
                        }
                       
                    }
                    if(isPassengerlap == 1){
                        System.out.println("add passenger");
                        BookingAirline airlinePass = new BookingAirline();    
                        List<BookingAirline> AirPasslist = new ArrayList<BookingAirline>(listCurrentAirlines);
                    
                        for(int i =0;i<AirPasslist.size();i++){
                            System.out.println("AirPasslist ID:"+AirPasslist.get(i).getId() + "AirPasslist CODE:"+AirPasslist.get(i).getAirlineCode() );
                            if(newAirline.getAirlineCode().equalsIgnoreCase(AirPasslist.get(i).getAirlineCode())){
                                System.out.println("Airline ID : " +  AirPasslist.get(i).getId());
                                Iterator<BookingFlight> iteratorFlight =  AirPasslist.get(i).getBookingFlights().iterator();
                                while(iteratorFlight.hasNext()){
                                    BookingFlight flight = iteratorFlight.next();
                                    for(int j=0;j<newflight.size();j++){
                                        System.out.println(newflight.get(j).getFlightNo() + " = " + flight.getFlightNo());
                                        if(newflight.get(j).getFlightNo().equalsIgnoreCase(flight.getFlightNo())){
                                            System.out.println("get this Airline : "+ AirPasslist.get(i).getId());
                                            airlinePass =  AirPasslist.get(i); 
                                            break;
                                        }
                                    }
                                }
                                if(airlinePass.getId() == null){
                                    System.out.println("===== Airline Pass is NULL =====");
                                    List<BookingAirline> AirPasslistTemp = session.createQuery("from BookingAirline ba where ba.id = :id")
                                                                            .setParameter("id", AirPasslist.get(i).getId())
                                                                            .list();
                                    if(!AirPasslistTemp.isEmpty()){
                                        airlinePass = AirPasslistTemp.get(0);
                                    }
                                }
                            }
                        }
                        
                        
                        for(int j=0;j<newPassenger.size();j++){
                            boolean islap = false;
                            newPassenger.get(j).setBookingAirline(airlinePass);
                            for(int k=0;k<currentpassenger.size();k++){
                                String ticket =  currentpassenger.get(k).getTicketnoS1() + currentpassenger.get(k).getTicketnoS2()+currentpassenger.get(k).getTicketnoS3();
                                String ticketnew =  newPassenger.get(j).getTicketnoS1() + newPassenger.get(j).getTicketnoS2()+newPassenger.get(j).getTicketnoS3();
                                if(ticket.equalsIgnoreCase(ticketnew)){
                                    islap = true;
                                    k += currentflight.size();
                                }
                            } 
                            if(!islap){
                                System.out.println("add passenger not lap : "+newPassenger.get(j).getTicketnoS1()+newPassenger.get(j).getTicketnoS2()+newPassenger.get(j).getTicketnoS3());
                                newpassengera.add(newPassenger.get(j));
                            }
                        }
                    }
                   
                }  

            }

            if (updateFilenameFlag) {
                String newFilename = currentBookingPnr.getFilename() + "," + bPnr.getFilename();
                currentBookingPnr.setFilename(newFilename);
            }
//            int dd = 0;
//            
//            Iterator<BookingAirline> airr =  currentBookingPnr.getBookingAirlines().iterator();
//                
//            while(airr.hasNext() && dd <= 20){
//               BookingAirline ss = (BookingAirline)airr.next();
//               Iterator<BookingPassenger> pp =  ss.getBookingPassengers().iterator();
//           
//                dd++;
//               while(pp.hasNext() && dd <= 20){
//                    BookingPassenger f = (BookingPassenger)pp.next();
//                    System.out.println("air id : "+ss.getId()+"ticket : "+f.getTicketnoS3() + " air id :"+f.getBookingAirline().getId());
//                    dd++;
//                }   
//            }
            session.save(currentBookingPnr);
            transaction.commit();
            session.close();
        
            for(int i=0;i<newflighta.size();i++){
                insertBookingFlight(newflighta.get(i));            
            }
            
            for(int i=0;i<newpassengera.size();i++){
                insertBookingPassenger(newpassengera.get(i));            
            }
            
            result = 1;
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    private int insertBookingFlight(BookingFlight flight) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(flight);
            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }
    
    private int insertBookingPassenger(BookingPassenger passenger) {
        int result = 0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(passenger);
            transaction.commit();
            session.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    private boolean isExistAirline(BookingAirline newAirline, Set currentAirlineSet) {
        Iterator<BookingAirline> iteratorCurrentAirline = currentAirlineSet.iterator();
       
        while (iteratorCurrentAirline.hasNext()) {
            int isNew = 0;
            BookingAirline currentAirline = iteratorCurrentAirline.next();
            System.out.println(" airline code d " + currentAirline.getAirlineCode());
            
            
            if (currentAirline.getAirlineCode().equalsIgnoreCase(newAirline.getAirlineCode())) {
                
                //Fix from issue http://192.168.99.49:8012/issue-log/view.php?id=750
                BookingPnr pnr = currentAirline.getBookingPnr();
                boolean CheckFileMatching = true;
                if("GALILEO".equalsIgnoreCase(pnr.getGds())){
                    Iterator<BookingPassenger> iteratorCurrentPassenger =  currentAirline.getBookingPassengers().iterator();
                    Iterator<BookingPassenger> iteratorNewPassenger =newAirline.getBookingPassengers().iterator();
                    while (iteratorCurrentPassenger.hasNext()) {
                        String passengerType = iteratorCurrentPassenger.next().getPassengerType();
                        while (iteratorNewPassenger.hasNext()) {
                            String NewPassengerType = iteratorNewPassenger.next().getPassengerType();
                            if(passengerType.equalsIgnoreCase(NewPassengerType)){
                                CheckFileMatching = false; 
                            }
                        }
                    }
                    if(!CheckFileMatching){
                        return false;
                    } 
                }
                //******************************************************************
                System.out.println("Duplicate airline " + currentAirline.getAirlineCode());
                Iterator<BookingFlight> iteratorCurrentFlight =  currentAirline.getBookingFlights().iterator();
                while (iteratorCurrentFlight.hasNext()) {
                    BookingFlight currentFlight = iteratorCurrentFlight.next();
                    System.out.println("currentFlight Order: "+currentFlight.getFlightOrder() +","+currentFlight.getFlightNo());
                    Iterator<BookingFlight> iteratorNewFlight =  newAirline.getBookingFlights().iterator();
                    
                    while (iteratorNewFlight.hasNext()) {
                        BookingFlight newFlight = iteratorNewFlight.next();
                        System.out.println("newFlight Order: "+newFlight.getFlightOrder()+"||"+newFlight.getFlightNo());
                        if(newFlight.getFlightOrder() == currentFlight.getFlightOrder()){
                            System.out.println("lap order "+newFlight.getFlightOrder()+"||"+newFlight.getFlightNo() +" , "+currentFlight.getFlightOrder()+" || "+currentFlight.getFlightNo());
                            isNew = 1;
                            break;
                        }
                    }
                   if(isNew == 1){break;}
                }
                
                 if((isNew == 0)&&(!iteratorCurrentAirline.hasNext())){
                        System.out.println("is new airline");
                        return false;
                 }
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
        int isLab = 0;
        for (BookingAirline currentAirline : currentAirlines) {
            if(isLab == 0){
            if (currentAirline.getAlrlineName().equalsIgnoreCase(newAirline.getAlrlineName())) {
                 
                Iterator<BookingFlight> iteratorCurrentFlight =  currentAirline.getBookingFlights().iterator();
                System.out.println("check currentAirline.getAlrlineName() :" + currentAirline.getAlrlineName());
                while (iteratorCurrentFlight.hasNext()) {
                    BookingFlight currentFlight = iteratorCurrentFlight.next();
                    System.out.println("currentFlight Order: "+currentFlight.getFlightOrder()+","+currentFlight.getFlightNo());   
                    Iterator<BookingFlight> iteratorNewFlight =  newAirline.getBookingFlights().iterator();
                    while (iteratorNewFlight.hasNext()) {
                        BookingFlight newFlight = iteratorNewFlight.next();
                        System.out.println("newFlight Order: "+newFlight.getFlightOrder()+","+newFlight.getFlightNo());
                        ///////////////////////
                        if(newFlight.getFlightNo().equalsIgnoreCase(currentFlight.getFlightNo())){ 
                            isLab = 1;
                            for (BookingPassenger passenger : (Set<BookingPassenger>) newAirline.getBookingPassengers()) {                            
                                passenger.setBookingAirline(currentAirline);
                                currentAirline.getBookingPassengers().add(passenger);
                            }
                            break;
                        }
                    }
                    
                   
                }
            }   
            }
        }
        
        if(isLab == 0){
            for (BookingAirline currentAirline : currentAirlines) {
            if (currentAirline.getAlrlineName().equalsIgnoreCase(newAirline.getAlrlineName())) {
                for (BookingPassenger passenger : (Set<BookingPassenger>) newAirline.getBookingPassengers()) {
                    passenger.setBookingAirline(currentAirline);
                }
                currentAirline.getBookingPassengers().addAll(newAirline.getBookingPassengers());
                break;
            }   
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
//                currentAirline.getBookingPassengers().addAll(newAirline.getBookingPassengers());
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
