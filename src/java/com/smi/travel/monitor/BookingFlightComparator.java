/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.monitor;

import com.smi.travel.datalayer.entity.BookingFlight;
import java.util.Comparator;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author wleenavo
 */
public class BookingFlightComparator implements Comparator<BookingFlight> {

    public BookingFlightComparator() {
    }

    @Override
    public int compare(BookingFlight f1, BookingFlight f2) {
        int compareDate = f1.getDepartureDate().compareTo(f2.getDepartureDate());
        if (compareDate != 0) {
            return compareDate;
        } else {
            int departTime1;
            int departTime2;
            if (StringUtils.isEmpty(f1.getDepartTime())) {
                departTime1 = 0;
            } else {
                departTime1 = Integer.valueOf(f1.getDepartTime().trim());
            }
            if (StringUtils.isEmpty(f2.getDepartTime())) {
                departTime2 = 0;
            } else {
                departTime2 = Integer.valueOf(f2.getDepartTime().trim());
            }

            return Integer.compare(departTime1, departTime2);
        }
    }
}
