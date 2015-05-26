package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.Hotel;
import com.smi.travel.datalayer.service.HotelService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class HotelController extends SMITravelController {

    private static final ModelAndView Hotel = new ModelAndView("MHotel");
    private static final String DATALIST = "hotel_list";
    private static final String TransectionResult = "result";
    private HotelService hotelservice;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String HotelID = request.getParameter("hotelID");
        int result = 1;
        Hotel hotel = new Hotel();
        hotel.setCode(code);
        hotel.setName(name);
        hotel.setId(HotelID);
        if ("search".equalsIgnoreCase(action)) {
            List<Hotel> list = hotelservice.getListHotel(hotel, 2);
            request.setAttribute(DATALIST, list);
        } else if ("delete".equalsIgnoreCase(action)) {
            result = hotelservice.DeleteHotel(hotel);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        
        request.setAttribute("hotelCode", code);
        request.setAttribute("hotelName", name);
        
        return Hotel;
    }

    public HotelService getHotelservice() {
        return hotelservice;
    }

    public void setHotelservice(HotelService hotelservice) {
        this.hotelservice = hotelservice;
    }

}
