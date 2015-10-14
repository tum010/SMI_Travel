/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.AirticketAirline;
import com.smi.travel.datalayer.entity.AirticketBooking;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.BookingAirline;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.BookingPassenger;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.service.MStaffService;
import com.smi.travel.datalayer.service.MAirportService;
import com.smi.travel.datalayer.service.MAirticketService;
import com.smi.travel.datalayer.entity.MAirline;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.MAirport;
import com.smi.travel.datalayer.entity.BookingPnr;
import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.MTicketType;
import com.smi.travel.datalayer.entity.MPricecategory;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.util.UtilityFunction;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author sumeta
 */
public class AirTicketDetailController extends SMITravelController {

    private static final ModelAndView AirTicketDetail = new ModelAndView("AirTicketDetail");
    private static final ModelAndView AirTicket_REFRESH = new ModelAndView(new RedirectView("AirTicketDetail.smi", true));
    private BookingAirticketService bookingAirticketService;
    private MStaffService mStaffService;
    private MAirticketService mAirticketService;
    private MAirportService mAirportService;
    private UtilityService utilservice;

    private static final String Bookiing_Size = "BookingSize";
    private static final String Master = "Master";

    private static final String CurrentPnr = "CurrentPnr";
    private static final String Airline = "Airline";
    private static final String AllFlights = "Flights";
    private static final String AllPassengers = "Passengers";
    private static final String Airport = "Airport";
    private static final String List_BookingPnrs = "List_BookingPnrs";
    private static final String MFlightList = "MFlightList";
    private static final String MTicketTypeList = "MTicketTypeList";
    private static final String MPricecategorysList = "MPricecategorysList";
    private static final String InitialName = "InitialName";
    private static final String MItemstatusList = "MItemstatusList";
    private static final String MAirlineList = "MAirlineList";
    private static final String Action = "Action";
    private static final String TransactionResult = "result";
    private static final String[] resultText = {"Save unsuccessful", "Save successful", "Airline doesnot exist"};
    private static final String MInitialname = "MInitialname";
    private static final String Result = "Result";
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private static final String CHECKPNR = "checkPnr_list";


    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String referenceNo = request.getParameter("referenceNo");
        String action = request.getParameter("action");
        String pnr = request.getParameter("pnr");
        String pnrIdTemp = request.getParameter("pnrIdTemp");
        int result = 0;
        //set pnrId
        if(!"".equalsIgnoreCase(pnr) && pnr != null){
            request.setAttribute("pnrIdTemp", pnr);
        }
        System.out.println(AirTicketDetailController.class.getName() + " action[" + action + "],[pnr=" + pnr + "]");

        if ("add".equalsIgnoreCase(action)) {
            System.out.println(AirTicketDetailController.class.getName() + " add");
            request.setAttribute(Action, "newpnr");
            setResponseAttribute(request, null, referenceNo);
            List<String> checkPnr = bookingAirticketService.getListPnrFromRefno(referenceNo);
            request.setAttribute(CHECKPNR, checkPnr);
        } else if ("import".equalsIgnoreCase(action)) {
            String bookingPnrNo = request.getParameter("pnrname");
            System.out.println(AirTicketDetailController.class.getName() + " import");
            System.out.println(AirTicketDetailController.class.getName() + " pnr - [" + bookingPnrNo + "]");
            BookingPnr importPnr = bookingAirticketService.getBookingPnr(bookingPnrNo);
            AirticketPnr newAirPnr = this.importBookingPnr(importPnr, request.getParameter("referenceNo"),pnrIdTemp);
            request.setAttribute(Action, "update");
            
            setResponseAttribute(request, newAirPnr, referenceNo);
            if("".equalsIgnoreCase(pnrIdTemp)){
                return new ModelAndView("redirect:AirTicketDetail.smi?referenceNo=" + referenceNo + "&pnr=" + newAirPnr.getId() + "&action=edit&result=1");
            }else{
                return new ModelAndView("redirect:AirTicketDetail.smi?referenceNo=" + referenceNo + "&pnr=" + pnrIdTemp + "&action=edit&result=1");
            }
        } else if ("update".equalsIgnoreCase(action)) {
            System.out.println("update");
            AirticketPnr airticketPnr = bookingAirticketService.getPNRDetailByID(pnr, referenceNo);
            try {
                result = updateAirPnr(request, airticketPnr, referenceNo);
                request.setAttribute(Action, "update");
                //return new ModelAndView("redirect:AirTicketDetail.smi?referenceNo=" + referenceNo + "&pnr=" + pnr + "&action=edit&result=" + result);
            } catch (NonExistAirlineException ne) {
                setResponseAttribute(request, airticketPnr, referenceNo);
                request.setAttribute(Action, "update");
                request.setAttribute(TransactionResult, resultText[ne.result]);
                //return new ModelAndView("redirect:AirTicketDetail.smi?referenceNo=" + referenceNo + "&pnr=" + pnr + "&action=edit&result=" + result);
            }
            request.setAttribute(Result, result);
            return new ModelAndView("redirect:AirTicketDetail.smi?referenceNo=" + referenceNo + "&pnr=" + pnr + "&action=edit&result=" + result);
        } else if ("newpnr".equalsIgnoreCase(action)) {
            System.out.println(action);
            AirticketPnr airPnr = new AirticketPnr();
            if (!hasFlight(request)) {
                return new ModelAndView("redirect:AirTicketDetail.smi?referenceNo=" + referenceNo + "&pnr=" + pnr + "&action=add&result=" + result);
            }
            try {
                result = buildDummyAirticketPnr(request, referenceNo, airPnr);
                request.setAttribute(Action, "update");
                return new ModelAndView("redirect:AirTicket.smi?referenceNo=" + referenceNo + "&action=edit&result=" + result);
            } catch (NonExistAirlineException ne) {
                setResponseAttribute(request, airPnr, referenceNo);
                request.setAttribute(Action, "update");
                request.setAttribute(TransactionResult, resultText[ne.result]);
            }
        } else if ("disableFlight".equalsIgnoreCase(action)) {
            String flightId = request.getParameter("disableFlightId");
            System.out.println("Set disable flight id [" + flightId + "].");
            result = bookingAirticketService.cancelBookAirticketFilght(flightId);
            //Add Service method to mark flight delete.
            return new ModelAndView("redirect:AirTicketDetail.smi?referenceNo=" + referenceNo + "&pnr=" + pnr + "&action=edit&result=" + result);
        } else if ("enableFlight".equalsIgnoreCase(action)) {
            String flightId = request.getParameter("enableFlightId");
            System.out.println("Set enable flight id [" + flightId + "].");
            result = bookingAirticketService.enableBookAirticketFilght(flightId);
            //Add Service method to mark flight delete.
            return new ModelAndView("redirect:AirTicketDetail.smi?referenceNo=" + referenceNo + "&pnr=" + pnr + "&action=edit&result=" + result);
        } else if ("deletePassenger".equalsIgnoreCase(action)) {
            String passengerId = request.getParameter("passengerId");
            bookingAirticketService.deletePassenger(passengerId);
        } else {
            System.out.println(AirTicketDetailController.class.getName() + " action=" + action + "[pnr=" + pnr + "]");
            AirticketPnr airticketPnr = bookingAirticketService.getPNRDetailByID(pnr, referenceNo);
            //Error occurs when others tab is no object yet.
            setResponseAttribute(request, airticketPnr, referenceNo);
            request.setAttribute(Action, "update");
            List<String> checkPnr = bookingAirticketService.getListPnrFromRefno(referenceNo);
            request.setAttribute(CHECKPNR, checkPnr);
        }

        return AirTicketDetail;
    }

    private boolean hasFlight(HttpServletRequest request) {
        String flight = request.getParameter("countRow");

        if (StringUtils.isEmpty(flight)) {
            System.out.println("AirticketDetailController : Cannot retrieved countRow[" + flight + "]");
            return false;
        }

        if (flight.equalsIgnoreCase("1")) {
            System.out.println("AirticketDetailController : No flight rows to retrieved countRow[" + flight + "]");
            return false;
        }
        return true;
    }

    private void setResponseAttribute(HttpServletRequest request, AirticketPnr airticketPnr, String referenceNo) {
        String codeAirline = request.getParameter("");
        Master master = utilservice.getMasterdao().getBookingFromRefno(referenceNo);
        request.setAttribute(Master, master);
        // Mbookstatus ==> 2 Finish , 5 Finish by Finance
        if(("1").equals(String.valueOf(master.getFlagAir())) 
            || ("2").equals(String.valueOf(master.getMBookingstatus().getId()))
            || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }
        
        int[] booksize = utilservice.getCountItemFromBooking(referenceNo);
        request.setAttribute(Bookiing_Size, booksize);

        List<MFlight> mFlightList = utilservice.getListMFlightClass();
        request.setAttribute(MFlightList, mFlightList);

        List<MTicketType> mTicketTypeList = utilservice.getListMTicketType();
        request.setAttribute(MTicketTypeList, mTicketTypeList);

        List<MPricecategory> mPricecategorysList = utilservice.getListMPricecategory();
        request.setAttribute(MPricecategorysList, mPricecategorysList);
        MAirline airline = new MAirline();
        List<MAirline> mAirlines = bookingAirticketService.getmAirlineDao().getListAirLine(airline, 1);
        request.setAttribute(MAirlineList, mAirlines);

        List<MItemstatus> mItemstatuses = utilservice.getListMItemstatus();
        request.setAttribute(MItemstatusList, mItemstatuses);

        MAirport airport = new MAirport();
        List<MAirport> mAirport = new ArrayList<MAirport>(mAirportService.searchAirport(airport, 1));
        request.setAttribute(Airport, mAirport);

        List<MInitialname> mInitialname = utilservice.getListMInitialname();
        request.setAttribute(MInitialname, mInitialname);

        List<BookingPnr> listBookingPnr = bookingAirticketService.getListBookingPnr();

        List<MInitialname> listInitialName = utilservice.getListMInitialname();
        request.setAttribute(InitialName, listInitialName);
        request.setAttribute(List_BookingPnrs, listBookingPnr);

        calculateTotalEachFlightInPnr(airticketPnr);
        TreeSet<AirticketFlight> sortedFlight = new TreeSet<AirticketFlight>(new AirticketFlightComparator());
        if (airticketPnr != null) {
            List<AirticketAirline> airlines = new ArrayList<AirticketAirline>(airticketPnr.getAirticketAirlines());
            List<AirticketFlight> allFlights = new ArrayList<AirticketFlight>();
            List<AirticketPassenger> allPassengers = new ArrayList<AirticketPassenger>();
            for (int i = 0; i < airlines.size(); i++) {
//                allFlights.addAll(airlines.get(i).getAirticketFlights());
                // AirTicketDetail.jsp in TableAir
                sortedFlight.addAll(airlines.get(i).getAirticketFlights());
                allPassengers.addAll(airlines.get(i).getAirticketPassengers());
            }

            allFlights.addAll(sortedFlight);
            request.setAttribute(CurrentPnr, airticketPnr);
            request.setAttribute(Airline, airlines);
            request.setAttribute(AllFlights, allFlights);
            request.setAttribute(AllPassengers, allPassengers);
        }
    }

    private int updateAirticketPassenger(HttpServletRequest request, AirticketPnr airPnr) throws NonExistAirlineException {

        int result = 0;
        String rowS = request.getParameter("countRowPassenger");
        int row = Integer.parseInt(rowS);

        UtilityFunction utilityFunction = new UtilityFunction();

        for (int i = 1; i < row; i++) {
            String passengerId = request.getParameter("passengerId" + i);
            String initial = request.getParameter("passengerIntialname" + i);
            String firstname = request.getParameter("passengerfirstname" + i);
            String lastname = request.getParameter("passengerlastname" + i);
            String series1 = request.getParameter("passengerSeriesOne" + i);
            String series2 = request.getParameter("passengerSeriesTwo" + i);
            String series3 = request.getParameter("passengerSeriesThree" + i);
            String airlineId = request.getParameter("passengerAirlineId" + i);
            String airlineCode = request.getParameter("passengerAirlineCode" + i);
            String ticketFare = request.getParameter("passengerFare" + i);
            String ticketTax = request.getParameter("passengerTax" + i);
            String ticketType = request.getParameter("passengerTicketType" + i);
            String ticketForm = request.getParameter("passengerFrom" + i);
            String category = request.getParameter("passengerCategory" + i);

            AirticketPassenger airticketPassenger = getAirticketPassenger(airPnr, passengerId);
            MInitialname initialName = utilservice.getMInitialnameFromName(initial);
            airticketPassenger.setMInitialname(initialName);
            airticketPassenger.setFirstName(firstname);
            airticketPassenger.setLastName(lastname);
            airticketPassenger.setSeries1(series1);
            airticketPassenger.setSeries2(series2);
            airticketPassenger.setSeries3(series3);
            airticketPassenger.setTicketFare(utilityFunction.convertStringToInteger(ticketFare));
            airticketPassenger.setTicketTax(utilityFunction.convertStringToInteger(ticketTax));
            MPricecategory priceCategory = utilservice.getMPricecategoryFromCode(category);
            airticketPassenger.setMPricecategory(priceCategory);
            airticketPassenger.setTicketType(ticketType);
            airticketPassenger.setTicketFrom(ticketForm);

            if (passengerId == null) {
                Set<AirticketAirline> airlines = airPnr.getAirticketAirlines();
                boolean foundAirline = false;
                for (AirticketAirline airline : airlines) {
                    if (airline.getMAirline().getCode().equalsIgnoreCase(airlineCode)) {
                        airline.getAirticketPassengers().add(airticketPassenger);
                        foundAirline = true;
                        airticketPassenger.setAirticketAirline(airline);
                    }
                }

                if (!foundAirline) {
                    throw new NonExistAirlineException();
                }
            } else {
                AirticketAirline currentAirline = airticketPassenger.getAirticketAirline();
                currentAirline.getAirticketFlights().remove(airticketPassenger);
//                if (!isExistingAirline(airPnr, airlineCode)) {
//
//                    AirticketAirline newAirline = new AirticketAirline();
//                    MAirline airline = new MAirline();
//                    airline.setCode(airlineCode);
//                    List<MAirline> listAirline = bookingAirticketService.getmAirlineDao().getListAirLine(airline, 1);
//                    newAirline.setMAirline(listAirline.get(0));
//                    newAirline.getAirticketPassengers().add(airticketPassenger);
//                    airticketPassenger.setAirticketAirline(newAirline);
//                    airPnr.getAirticketAirlines().add(newAirline);
//                    newAirline.setAirticketPnr(airPnr);
//                    newAirline.setTicketDate(new Date());
//                } else {
                //Might need to setup new airline. Need analysis.
                AirticketAirline airline = getAirlineByAircode(airPnr, airlineCode);
                airline.getAirticketPassengers().add(airticketPassenger);
                airticketPassenger.setAirticketAirline(airline);
                System.out.println("Existing Airline and Existing Passenger");

//                }
            }
        }
        return 1;

    }

    private AirticketPassenger getAirticketPassenger(AirticketPnr airPnr, String airPassengerId) {
        if (airPassengerId == null) {
            AirticketPassenger airticketPassenger = new AirticketPassenger();
            return airticketPassenger;
        }
        Set<AirticketAirline> airlines = airPnr.getAirticketAirlines();
        for (AirticketAirline airline : airlines) {

            Iterator<AirticketPassenger> iterator = airline.getAirticketPassengers().iterator();
            while (iterator.hasNext()) {
                AirticketPassenger passenger = iterator.next();
                if (airPassengerId.equalsIgnoreCase(passenger.getId())) {
                    return passenger;
                }
            }
        }
        return null;
    }

    private void setAirticketFlight(HttpServletRequest request, String RowsCount, AirticketAirline airline) {
        int Rows = Integer.parseInt(RowsCount);
        if (Rows == 1) {
            return;
        }
        for (int i = 1; i < Rows; i++) {
            String flightId = request.getParameter("flight-" + i + "-id");
            String status = request.getParameter("flight-" + i + "-status");
            String flightNo = request.getParameter("flight-" + i + "-flightNo");
            String departureId = request.getParameter("departure-" + i + "-id");
            String deparDate = request.getParameter("flight-" + i + "-deparDate");
            String deparTime = request.getParameter("flight-" + i + "-deparTime");
            String arrivalId = request.getParameter("arrival-" + i + "-id");
            String arrivalDate = request.getParameter("flight-" + i + "-arriveDate");
            String arrivalTime = request.getParameter("flight-" + i + "-arriveTime");
            String ticketType = request.getParameter("flight-" + i + "-ticketType");
            String flightClass = request.getParameter("flight-" + i + "-class");
            String subFlightClass = request.getParameter("flight-" + i + "-subClass");
            String adCost = request.getParameter("adCost-" + i);
            String chCost = request.getParameter("chCost-" + i);
            String inCost = request.getParameter("inCost-" + i);
            String adPrice = request.getParameter("adPrice-" + i);
            String chPrice = request.getParameter("chPrice-" + i);
            String inPrice = request.getParameter("inPrice-" + i);
            String adTax = request.getParameter("adTax-" + i);
            String chTax = request.getParameter("chTax-" + i);
            String inTax = request.getParameter("inTax-" + i);
            String adTaxCost = request.getParameter("adTaxCost-" + i);
            String chTaxCost = request.getParameter("chTaxCost-" + i);
            String inTaxCost = request.getParameter("inTaxCost-" + i);
            
            AirticketFlight airFlight = getAirFlight(flightId, null);
            if (airFlight == null) {
                airFlight = new AirticketFlight();
            }
            airFlight.setId(flightId);
            airFlight.setMItemstatus(utilservice.getMItemstatusFromName(status));
            airFlight.setFlightNo(flightNo);
            airFlight.setSourceCode(departureId);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date depar = formatter.parse(deparDate);
                airFlight.setDepartDate(depar);
                Date arrive = formatter.parse(arrivalDate);
                airFlight.setArriveDate(arrive);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            airFlight.setDepartTime(deparTime);
            airFlight.setDesCode(arrivalId);
            airFlight.setArriveTime(arrivalTime);
            MFlight mFlight = bookingAirticketService.MappingFlightClass(flightClass);
            airFlight.setMFlight(mFlight);
            MTicketType mTicketType = bookingAirticketService.MappingTicketLife(ticketType);
            airFlight.setMTicketType(mTicketType);
            //airFlight.set
            //airFlight.setClass

            if (StringUtils.isNotEmpty(adCost)) {
                airFlight.setAdCost(Integer.valueOf(adCost));
            }
            if (StringUtils.isNotEmpty(chCost)) {
                airFlight.setChCost(Integer.valueOf(chCost));
            }
            if (StringUtils.isNotEmpty(inCost)) {
                airFlight.setInCost(Integer.valueOf(inCost));
            }
            if (StringUtils.isNotEmpty(adPrice)) {
                airFlight.setAdPrice(Integer.valueOf(adPrice));
            }
            if (StringUtils.isNotEmpty(chPrice)) {
                airFlight.setChPrice(Integer.valueOf(chPrice));
            }
            if (StringUtils.isNotEmpty(inPrice)) {
                airFlight.setInPrice(Integer.valueOf(inPrice));
            }
            if (StringUtils.isNotEmpty(adTax)) {
                airFlight.setAdTax(Integer.valueOf(adTax));
            }
            if (StringUtils.isNotEmpty(chTax)) {
                airFlight.setChTax(Integer.valueOf(chTax));
            }
            if (StringUtils.isNotEmpty(inTax)) {
                airFlight.setInTax(Integer.valueOf(inTax));
            }
            if (StringUtils.isNotEmpty(adTaxCost)) {
                airFlight.setAdTaxCost(Integer.valueOf(adTaxCost));
            }
            if (StringUtils.isNotEmpty(chTaxCost)) {
                airFlight.setChTaxCost(Integer.valueOf(chTaxCost));
            }
            if (StringUtils.isNotEmpty(inTaxCost)) {
                airFlight.setInTaxCost(Integer.valueOf(inTaxCost));
            }
            if (StringUtils.isNotEmpty(subFlightClass)) {
                airFlight.setSubFlightClass(subFlightClass);
            }
            if (StringUtils.isNotEmpty(airFlight.getSourceCode())) {
                if (airFlight.getId() == null) {
                    airline.getAirticketFlights().add(airFlight);
                    //airFlight.setAirticketAirline(airline);
                }
            } else {
                System.out.println("Detail is null, Not update DB this object " + i);
            }
        }
    }

    private AirticketFlight getAirFlight(String flightId, AirticketPnr airPnr) {
        if (flightId == null) {
            return null;
        }
        Set<AirticketAirline> airlines = airPnr.getAirticketAirlines();
        for (AirticketAirline airline : airlines) {

            Iterator<AirticketFlight> iterator = airline.getAirticketFlights().iterator();
            while (iterator.hasNext()) {
                AirticketFlight flight = iterator.next();
                if (flightId.equalsIgnoreCase(flight.getId())) {
                    return flight;
                }
            }
        }
        return null;
    }

    private int updateFlight(HttpServletRequest request, int i, AirticketPnr airPnr) {
        UtilityFunction util = new UtilityFunction();
        int result = 1;
        String Id = null;
        Id = request.getParameter("flight-" + i + "-id");
        String status = request.getParameter("flight-" + i + "-status");
        String flightNo = request.getParameter("flight-" + i + "-flightNo");
        String departureId = request.getParameter("departure-" + i + "-id");
        String departureCode = request.getParameter("departure-" + i + "-code");
        String departDateS = request.getParameter("flight-" + i + "-departDate");
        String departTime = request.getParameter("flight-" + i + "-departTime");
        departTime = departTime.replace(":", "");
        String arrivalId = request.getParameter("arrival-" + i + "-id");
        String arrivalCode = request.getParameter("arrival-" + i + "-code");
        String arrivalDateS = request.getParameter("flight-" + i + "-arriveDate");
        String arrivalTime = request.getParameter("flight-" + i + "-arriveTime");
        arrivalTime = arrivalTime.replace(":", "");
        String ticketType = request.getParameter("flight-" + i + "-ticketType");
        String flightClass = request.getParameter("flight-" + i + "-class");
        String subFlightClass = request.getParameter("flight-" + i + "-subClass");
        String adCost = request.getParameter("adCost-" + i);
        String chCost = request.getParameter("chCost-" + i);
        String inCost = request.getParameter("inCost-" + i);
        String adPrice = request.getParameter("adPrice-" + i);
        String chPrice = request.getParameter("chPrice-" + i);
        String inPrice = request.getParameter("inPrice-" + i);
        String adTax = request.getParameter("adTax-" + i);
        String chTax = request.getParameter("chTax-" + i);
        String inTax = request.getParameter("inTax-" + i);
        String adTaxCost = request.getParameter("adTaxCost-" + i);
        String chTaxCost = request.getParameter("chTaxCost-" + i);
        String inTaxCost = request.getParameter("inTaxCost-" + i);
        
        String airlineCode = request.getParameter("airlineCode" + i);
        String flightOrder = request.getParameter("flight-" + i + "-flightOrder");

        AirticketFlight airFlight = getAirFlight(Id, airPnr);

        if (airFlight == null) {
            airFlight = new AirticketFlight();
            airFlight.setIsBill("0");
        }
//        airFlight.setId(Id);
        airFlight.setMItemstatus(utilservice.getMItemstatusFromName(status));
        airFlight.setFlightNo(flightNo);
        airFlight.setSourceCode(departureId);
        airFlight.setDesCode(inCost);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date departDate = null;
        Date arriveDate = null;
        try {
            departDate = formatter.parse(departDateS);
            airFlight.setDepartDate(departDate);
            arriveDate = formatter.parse(arrivalDateS);
            airFlight.setArriveDate(arriveDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        airFlight.setDepartDate(departDate);
        airFlight.setArriveDate(arriveDate);
        airFlight.setDepartTime(departTime);
        airFlight.setSourceCode(departureCode);
        airFlight.setDesCode(arrivalCode);
        airFlight.setArriveTime(arrivalTime);
        System.out.println("flightClass " + flightClass);
        System.out.println("subFlightClass " + subFlightClass);
        MFlight mFlight = new MFlight();
        mFlight.setId(flightClass);
        if(flightClass != null){
            airFlight.setMFlight(mFlight);
        }
        if (StringUtils.isNotEmpty(subFlightClass)) {
            airFlight.setSubFlightClass(subFlightClass);
        }
        if("".equalsIgnoreCase(flightClass)){
            airFlight.setMFlight(null);
        }
        
        MTicketType mTicketType = new MTicketType();
        mTicketType.setId(ticketType);
        if (ticketType != null) {
            airFlight.setMTicketType(mTicketType);
        }
        if("".equalsIgnoreCase(ticketType)){
            airFlight.setMTicketType(null);
        }
        
        if(!"".equalsIgnoreCase(flightOrder)){
            airFlight.setFlightOrder(Integer.parseInt(flightOrder));
        }else{
            airFlight.setFlightOrder(null);
        }
        
        if (StringUtils.isNotEmpty(adCost)) {
            //airFlight.setAdCost(Integer.valueOf(adCost));
            airFlight.setAdCost(util.convertStringToInteger(adCost));
        }
        if (StringUtils.isNotEmpty(chCost)) {
            //airFlight.setChCost(Integer.valueOf(chCost));
            airFlight.setChCost(util.convertStringToInteger(chCost));
        }
        if (StringUtils.isNotEmpty(inCost)) {
            //airFlight.setInCost(Integer.valueOf(inCost));
            airFlight.setInCost(util.convertStringToInteger(inCost));
        }
        if (StringUtils.isNotEmpty(adPrice)) {
            //airFlight.setAdPrice(Integer.valueOf(adPrice));
            airFlight.setAdPrice(util.convertStringToInteger(adPrice));
        }
        if (StringUtils.isNotEmpty(chPrice)) {
            //airFlight.setChPrice(Integer.valueOf(chPrice));
            airFlight.setChPrice(util.convertStringToInteger(chPrice));
        }
        if (StringUtils.isNotEmpty(inPrice)) {
            //airFlight.setInPrice(Integer.valueOf(inPrice));
            airFlight.setInPrice(util.convertStringToInteger(inPrice));
        }
        if (StringUtils.isNotEmpty(adTax)) {
            //airFlight.setAdTax(Integer.valueOf(adTax));
            airFlight.setAdTax(util.convertStringToInteger(adTax));
        }
        if (StringUtils.isNotEmpty(chTax)) {
            //airFlight.setChTax(Integer.valueOf(chTax));
            airFlight.setChTax(util.convertStringToInteger(chTax));
        }
        if (StringUtils.isNotEmpty(inTax)) {
            //airFlight.setInTax(Integer.valueOf(inTax));
            airFlight.setInTax(util.convertStringToInteger(inTax));
        }
        if (StringUtils.isNotEmpty(adTaxCost)) {
            airFlight.setAdTaxCost(util.convertStringToInteger(adTaxCost));
        }
        if (StringUtils.isNotEmpty(chTaxCost)) {
            airFlight.setChTaxCost(util.convertStringToInteger(chTaxCost));
        }
        if (StringUtils.isNotEmpty(inTaxCost)) {
            airFlight.setInTaxCost(util.convertStringToInteger(inTaxCost));
        }
        if (airFlight.getId() == null) {

            Set<AirticketAirline> airlines = airPnr.getAirticketAirlines();
            boolean foundAirline = false;
            for (AirticketAirline airline : airlines) {
                if (airline.getMAirline().getCode().equalsIgnoreCase(airlineCode)) {
                    airline.getAirticketFlights().add(airFlight);
                    foundAirline = true;
                    airFlight.setAirticketAirline(airline);
                }
            }
            if (!foundAirline) {

                AirticketAirline newAirline = new AirticketAirline();
                MAirline airline = new MAirline();
                airline.setCode(airlineCode);
                List<MAirline> listAirline = bookingAirticketService.getmAirlineDao().getListAirLine(airline, 1);
                newAirline.setMAirline(listAirline.get(0));
                newAirline.setTicketDate(new Date());
                newAirline.getAirticketFlights().add(airFlight);
                newAirline.setAirticketPnr(airPnr);
                airFlight.setAirticketAirline(newAirline);
                airPnr.getAirticketAirlines().add(newAirline);
            }
        } else {
            AirticketAirline currentAirline = airFlight.getAirticketAirline();
            currentAirline.getAirticketFlights().remove(airFlight);
            if (!isExistingAirline(airPnr, airlineCode)) {

                AirticketAirline newAirline = new AirticketAirline();
                MAirline airline = new MAirline();
                airline.setCode(airlineCode);
                List<MAirline> listAirline = bookingAirticketService.getmAirlineDao().getListAirLine(airline, 1);
                newAirline.setMAirline(listAirline.get(0));
                newAirline.getAirticketFlights().add(airFlight);
                airFlight.setAirticketAirline(newAirline);
                airPnr.getAirticketAirlines().add(newAirline);
                newAirline.setAirticketPnr(airPnr);
                newAirline.setTicketDate(new Date());
            } else {
                //Might need to setup new airline. Need analysis.
                AirticketAirline airline = getAirlineByAircode(airPnr, airlineCode);
                airline.getAirticketFlights().add(airFlight);
                airFlight.setAirticketAirline(airline);
                System.out.println("Existing Airline and Existing Flight");

            }
        }

        return result;
    }

    private int buildDummyAirticketPnr(HttpServletRequest request, String refNo, AirticketPnr airticketPnr) throws NonExistAirlineException {
        int result = 0;
        String rowCount = request.getParameter("countRow");
        System.out.println("Extract [" + rowCount + "]");
        int intCount = Integer.parseInt(rowCount);
        for (int i = 1; i < intCount; i++) {
            updateFlight(request, i, airticketPnr);
        }

        result = updateAirticketPassenger(request, airticketPnr);
        MItemstatus status = utilservice.getMItemstatusFromName("ok");
        AirticketBooking airBook = (AirticketBooking) utilservice.getbookingFromRefno(refNo).getAirticketBookings().iterator().next();

        airticketPnr.setAirticketBooking(airBook);
        airticketPnr.setPnr("DUMMY");
        airticketPnr.setMItemstatus(status);
        result = this.bookingAirticketService.insertAirticketPnr(airticketPnr);
        return result;
    }

    private int updateAirPnr(HttpServletRequest request, AirticketPnr airticketPnr, String refNo) throws NonExistAirlineException {
        int result = 0;
        String rowCount = request.getParameter("countRow");
        String rowPassenger = request.getParameter("countRowPassenger");
        int intCount = Integer.parseInt(rowCount);
        for (int i = 1; i < intCount; i++) {
            updateFlight(request, i, airticketPnr);
        }

        result = updateAirticketPassenger(request, airticketPnr);
        result = this.bookingAirticketService.UpdateAirticketPnr(airticketPnr);
        return result;
    }
    
    private AirticketPnr importBookingPnr(BookingPnr bPnr, String refNo,String pnrId) {
        AirticketPnr airPnr = new AirticketPnr();
        String subpnr = "";
        //get sub pnr from pnrId
        if(pnrId != null && !"null".equalsIgnoreCase(pnrId) && !"".equalsIgnoreCase(pnrId)){
            AirticketPnr airPnrtemp = bookingAirticketService.getAirticketPnrFromId(pnrId);
            subpnr = (String.valueOf(airPnrtemp.getSubpnr()) + "," + airPnrtemp.getPnr()).replaceAll("null,", "");
        }
        // Add value in AirticketPnr
        airPnr.setId(pnrId);
        airPnr.setPnr(bPnr.getPnr());
        if(!"".equalsIgnoreCase(subpnr)){
            airPnr.setSubpnr(subpnr);
        }
        // Build AirticketAirlines
        Set<BookingAirline> listBookingAirline = bPnr.getBookingAirlines();
        Iterator iteratorBookingAirline = listBookingAirline.iterator();
        while (iteratorBookingAirline.hasNext()) {
            BookingAirline bAirline = (BookingAirline) iteratorBookingAirline.next();
            AirticketAirline airAirline = new AirticketAirline();

            MAirline airline = new MAirline();
            airline.setCode(bAirline.getAirlineCode());
            List<MAirline> listAirline = bookingAirticketService.getmAirlineDao().getListAirLine(airline, 1);
            airAirline.setMAirline(listAirline.get(0));
            airAirline.setTicketDate(bAirline.getTicketDate());

            airAirline.setAirticketPnr(airPnr);
            airPnr.getAirticketAirlines().add(airAirline);

            buildAirticketFlights(bAirline, airAirline);
            buildAirticketPassenger(bAirline, airAirline);

        }
        AirticketBooking AirBook = bookingAirticketService.getBookDetailAir(refNo);
        airPnr.setAirticketBooking(AirBook);
        MItemstatus status = utilservice.getListitemdao().getMItemstatusFromName("ok");
        airPnr.setMItemstatus(status);
        if (isExistingPNR(airPnr.getPnr(), AirBook)) {
            bookingAirticketService.importExistingAirticketPnr(airPnr);
        } else {
            if(!"null".equalsIgnoreCase(String.valueOf(airPnr.getId())) && !"".equalsIgnoreCase(String.valueOf(airPnr.getId()))){
                bookingAirticketService.importUpdateAirticketPnr(airPnr);
            }else{
                bookingAirticketService.insertAirticketPnr(airPnr);
            }

        }
        return airPnr;
    }
    
    private int buildAirticketFlights(BookingAirline bAir, AirticketAirline airAirline) {
        int result = 1;
        Set<BookingFlight> listFlight = bAir.getBookingFlights();

        Iterator<BookingFlight> iteratorFlight = listFlight.iterator();
        while (iteratorFlight.hasNext()) {
            BookingFlight bFlight = iteratorFlight.next();
            AirticketFlight airFlight = new AirticketFlight();
            airFlight.setAirticketAirline(airAirline);
            airFlight.setFlightNo(bFlight.getFlightNo());
            airFlight.setDepartDate(bFlight.getDepartureDate());
            airFlight.setDepartTime(bFlight.getDepartTime());
            airFlight.setArriveDate(bFlight.getArrivalDate());
            airFlight.setArriveTime(bFlight.getArriveTime());
            airFlight.setSourceCode(bFlight.getSourceCode());
            airFlight.setDesCode(bFlight.getDesCode());
            airFlight.setAdCost(bFlight.getAdCost());
            airFlight.setAdPrice(bFlight.getAdPrice());
            airFlight.setAdTax(bFlight.getAdTax());
            airFlight.setChCost(bFlight.getChCost());
            airFlight.setChPrice(bFlight.getChPrice());
            airFlight.setChTax(bFlight.getChTax());
            airFlight.setInCost(bFlight.getInCost());
            airFlight.setInPrice(bFlight.getInPrice());
            airFlight.setInTax(bFlight.getInTax());
            airFlight.setAdTaxCost(bFlight.getAdTax());
            airFlight.setChTaxCost(bFlight.getChTax());
            airFlight.setInTaxCost(bFlight.getInTax());
            airFlight.setSubFlightClass(bFlight.getFlightClass());
            MFlight flightClass = null;
            flightClass = this.bookingAirticketService.MappingFlightClass(bFlight.getFlightClass());
            airFlight.setMFlight(flightClass);
            airFlight.setIsBill("0");
            airFlight.setMItemstatus(utilservice.getMItemstatusFromName("ok"));

            airAirline.getAirticketFlights().add(airFlight);
        }
        return result;
    }

    private int buildAirticketPassenger(BookingAirline bAir, AirticketAirline airAirline) {
        int result = 1;
        Set<BookingPassenger> listPassenger = bAir.getBookingPassengers();
        Iterator<BookingPassenger> iteratorPassenger = listPassenger.iterator();
        while (iteratorPassenger.hasNext()) {
            BookingPassenger bPassenger = iteratorPassenger.next();
            AirticketPassenger airPassenger = new AirticketPassenger();
            airPassenger.setAirticketAirline(airAirline);
            // wleenavo
            MInitialname initial = utilservice.getMInitialnameFromName(bPassenger.getInitialName());
            airPassenger.setMInitialname(initial);
            airPassenger.setFirstName(bPassenger.getFirstName());
            airPassenger.setLastName(bPassenger.getLastName());
            airPassenger.setSeries1(bPassenger.getTicketnoS1());
            airPassenger.setSeries2(bPassenger.getTicketnoS2());
            airPassenger.setSeries3(bPassenger.getTicketnoS3());
            airPassenger.setTicketFare(bPassenger.getTicketFare());
            if ("X".equalsIgnoreCase(bPassenger.getTicketType())) {
                airPassenger.setTicketType("I");
            } else {
                airPassenger.setTicketType("D");
            }
            airPassenger.setTicketFrom("C");
            airPassenger.setTicketTax(bPassenger.getTicketTax());
            MPricecategory passengerType = utilservice.getMPricecategoryFromCode(bPassenger.getPassengerType());
            airPassenger.setMPricecategory(passengerType);

            airAirline.getAirticketPassengers().add(airPassenger);
        }
        return result;
    }

    private boolean isExistingPNR(String pnr, AirticketBooking airBook) {
        boolean exist = false;

        Iterator<AirticketPnr> iteratorPnr = airBook.getAirticketPnrs().iterator();
        while (iteratorPnr.hasNext()) {
            AirticketPnr airPnr = iteratorPnr.next();
            if (pnr.equalsIgnoreCase(airPnr.getPnr())) {
                System.out.println("Existing PNR[" + pnr + "] on AirBooking MasterRefNo[" + airBook.getMaster().getReferenceNo() + "]");
                return true;
            }
        }

        return exist;
    }

    private boolean isExistingAirline(AirticketPnr airPnr, String airlineCode) {
        boolean exist = false;

        Set<AirticketAirline> airlines = airPnr.getAirticketAirlines();
        for (AirticketAirline airline : airlines) {
            if (airlineCode.equalsIgnoreCase(airline.getMAirline().getCode())) {
                exist = true;
                break;
            }
        }

        return exist;
    }

    public BookingAirticketService getBookingAirticketService() {
        return bookingAirticketService;
    }

    public void setBookingAirticketService(BookingAirticketService bookingAirticketService) {
        this.bookingAirticketService = bookingAirticketService;
    }

    public MStaffService getMStaffService() {
        return mStaffService;
    }

    public void setMStaffService(MStaffService mStaffService) {
        this.mStaffService = mStaffService;
    }

    public MAirticketService getMAirticketService() {
        return mAirticketService;
    }

    public void setMAirticketService(MAirticketService mAirticketService) {
        this.mAirticketService = mAirticketService;
    }

    public MAirportService getmAirportService() {
        return mAirportService;
    }

    public void setmAirportService(MAirportService mAirportService) {
        this.mAirportService = mAirportService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    private AirticketAirline getAirticketAirline(AirticketPnr airPnr, String airlineId) {
        if (airlineId == null) {
            return null;
        }
        Set<AirticketAirline> airlines = airPnr.getAirticketAirlines();
        for (AirticketAirline airline : airlines) {

            if (airlineId.equalsIgnoreCase(airline.getMAirline().getId())) {
                return airline;
            }
        }
        return null;
    }

    private void calculateTotalEachFlightInPnr(AirticketPnr airticketPnr) {
        if (airticketPnr == null) {
            return;
        }

        Set<AirticketAirline> airlines = airticketPnr.getAirticketAirlines();
        for (AirticketAirline airline : airlines) {
            int adult = 0;
            int child = 0;
            int infant = 0;
            Set<AirticketPassenger> passengers = airline.getAirticketPassengers();
            for (AirticketPassenger passenger : passengers) {
                if ("ADT".equalsIgnoreCase(passenger.getMPricecategory().getCode())) {

                    adult += 1;
                } else if ("CHD".equalsIgnoreCase(passenger.getMPricecategory().getCode())) {
                    child += 1;
                } else if ("INF".equalsIgnoreCase(passenger.getMPricecategory().getCode())) {
                    infant += 1;
                }
            }
            System.out.println("adult : "+adult );
            System.out.println("child : "+child );
            System.out.println("infant : "+infant );
            Set<AirticketFlight> flights = airline.getAirticketFlights();
            for (AirticketFlight flight : flights) {
                int totalCost;
                int totalPrice;
                int costAdult = 0;
                int priceAdult = 0;
                int costChild = 0, priceChild = 0, costInfant = 0, priceInfant = 0;
                if (flight.getAdCost() != null) {
                    costAdult = flight.getAdCost() * adult;
                }
                if (flight.getAdPrice() != null) {
                    priceAdult = flight.getAdPrice() * adult;
                    System.out.println("adult adprice : "+flight.getAdPrice() );
                }
                if (flight.getChCost() != null) {
                    costChild = flight.getChCost() * child;
                }
                if (flight.getChPrice() != null) {
                    priceChild = flight.getChPrice() * child;
                    System.out.println("adult chprice : "+flight.getChPrice() );
                }
                if (flight.getInCost() != null) {
                    costInfant = flight.getInCost() * infant;
                }
                if (flight.getInPrice() != null) {
                    priceInfant = flight.getInPrice() * infant;
                }
                System.out.println("priceAdult : "+priceAdult );
                System.out.println("priceChild : "+priceChild );
                System.out.println("priceInfant : "+priceInfant );
                totalCost = costAdult + costChild + costInfant;
                totalPrice = priceAdult + priceChild + priceInfant;
                flight.setTotalCost(totalCost);
                flight.setTotalPrice(totalPrice);
            }
        }
    }

    private AirticketAirline getAirlineByAircode(AirticketPnr airPnr, String airlineCode) {
        Set<AirticketAirline> airlines = airPnr.getAirticketAirlines();
        for (AirticketAirline airline : airlines) {
            if (airlineCode.equalsIgnoreCase(airline.getMAirline().getCode())) {
                return airline;
            }
        }
        return null;
    }

    private static class NonExistAirlineException extends Exception {

        int result = 2;

        public NonExistAirlineException() {
        }
    }

    private class AirticketFlightComparator implements Comparator<AirticketFlight> {

        public AirticketFlightComparator() {
        }
        
        @Override
        public int compare(AirticketFlight f1, AirticketFlight f2) {
            if(f1.getId()!=null && f2.getId()!=null){
                if(f1.getFlightOrder()!=null && f2.getFlightOrder()!=null){
                    int f1Id = Integer.parseInt(f1.getId());
                    int f2Id = Integer.parseInt(f2.getId());
                    int f1FlightOrder = f1.getFlightOrder();
                    int f2FlightOrder = f2.getFlightOrder();
                    int a = Integer.compare(f1FlightOrder, f2FlightOrder);
                    if(f1FlightOrder != f2FlightOrder){
                        return Integer.compare(f1FlightOrder, f2FlightOrder);
                    }else{
                        return Integer.compare(f1Id, f2Id);
                    }                    
                } else {
                    return 1;
                }
            }          
            return -1;
        }
//        @Override
//        public int compare(AirticketFlight f1, AirticketFlight f2) {
//            if(f1.getId()!=null && f1.getId()!=null){
//                int f1Id = Integer.parseInt(f1.getId());
//                int f2Id = Integer.parseInt(f2.getId());          
//                return Integer.compare(f1Id, f2Id);
//            } else {
//                return -1;
//            }
//        }

    }
}
