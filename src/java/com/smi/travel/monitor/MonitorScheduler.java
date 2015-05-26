/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.monitor;

import com.smi.travel.datalayer.entity.BookingAirline;
import com.smi.travel.datalayer.entity.BookingPnr;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.BookingPassenger;
import static com.smi.travel.monitor.MonitorGalileo.monthStringArrays;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author wleenavo
 */
public abstract class MonitorScheduler {

    static String[] monthStringArrays = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    abstract void run();

    abstract void buildContentList(String file);

    abstract BookingPnr buildBookingPnr(String filename);

    abstract BookingAirline buildBookingAirline();

    abstract void buildBookingFlight(BookingAirline bAir);

    abstract void buildBookingPassenger(BookingAirline bAir);

    protected Date convertStringToDate(String sDate) {
        String day = sDate.substring(0, 2);
        String month = sDate.substring(2, 5);
        String year = sDate.substring(5);

        if (year.length() == 2) {
            year = "20" + year;
        }
        System.out.println("Date string is day:" + day + ", month: " + month + " , year:" + year);
        Calendar calendar = new GregorianCalendar(Integer.parseInt(year), getMonthIndexFromString(month), Integer.parseInt(day));
        return calendar.getTime();
    }

    //Galileo Rules
    // 2 = sameday;
    // 3 = nextday;
    // 4 = 2 days;
    protected Date convertStringToDate(String sDate, int arrival) {
        String day = sDate.substring(0, 2);
        String month = sDate.substring(2, 5);
        String year = sDate.substring(5);

        if (year.length() == 2) {
            year = "20" + year;
        }
        int date = Integer.parseInt(day);
        date = date + arrival - 2;
        Calendar calendar = new GregorianCalendar(Integer.parseInt(year), getMonthIndexFromString(month), date);
        return calendar.getTime();
    }

    private static int getMonthIndexFromString(String sMonth) {
        return Arrays.asList(monthStringArrays).indexOf(sMonth);

    }

    abstract int processDataFile(String file);

    abstract int archiveDataFile(String file, int option);

    protected BookingFlight getMostEarlyFlight(BookingPnr bPnr) {

        TreeSet<BookingFlight> sortedFlight = new TreeSet<BookingFlight>(new BookingFlightComparator());
        Iterator<BookingAirline> iterator = bPnr.getBookingAirlines().iterator();
        while(iterator.hasNext()) {
            BookingAirline ba = iterator.next();
            sortedFlight.addAll(ba.getBookingFlights());
        }
        
        return sortedFlight.first();
    }
    
    protected String  stripNumberDecimalString(String decimalString) {
        int index_dot = decimalString.indexOf(".");
        String integerString;
            if ( index_dot > 0 )
                integerString = decimalString.substring(0, index_dot);
            else 
                integerString = decimalString;
            return integerString;
    }
}
