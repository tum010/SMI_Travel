package com.smi.travel.controller;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.service.WorkSpaceService;
import com.smi.travel.datalayer.view.entity.BookingAirSummaryView;
import com.smi.travel.datalayer.view.entity.BookingDayTourSummaryView;
import com.smi.travel.datalayer.view.entity.BookingHotelSummaryView;
import com.smi.travel.datalayer.view.entity.BookingLandSummaryView;
import com.smi.travel.datalayer.view.entity.BookingOtherSummaryView;
import com.smi.travel.datalayer.view.entity.BookingPackageSummaryView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class BookInformationController extends SMITravelController {
    private static final ModelAndView BookInformation = new ModelAndView("BookInformation");
    private static final ModelAndView BookInformation_REFRESH = new ModelAndView(new RedirectView("BookInformation.smi", true));
    private static final String DATALIST = "booking_list";
    private static final String SEARCHTYPE = "search_type";
    private WorkSpaceService workspaceService;
    private UtilityService utilservice;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        String action = request.getParameter("action");
        String bookRefNo = request.getParameter("bookRefNo");
        String bookLeader = request.getParameter("bookLeader");
        String bookDate = request.getParameter("bookDate");
        String bookType = request.getParameter("bookType");
        String airPnr = request.getParameter("airPnr");
        String airDeptDate = request.getParameter("airDeptDate");
        String airFlight = request.getParameter("airFlight");
        String hotelName = request.getParameter("hotelName");
        String hotelCheckIn = request.getParameter("hotelCheckIn");
        String hotelCheckOut = request.getParameter("hotelCheckOut");
        String packageName = request.getParameter("packageName");
        String packageAgent = request.getParameter("packageAgent");
        String tourCode = request.getParameter("tourCode");
        String tourName = request.getParameter("tourName");
        String tourDate = request.getParameter("tourDate");
        String tourPickUp = request.getParameter("tourPickUp");
        String tourAgent = request.getParameter("tourAgent");
        String otherCode = request.getParameter("otherCode");
        String otherName = request.getParameter("otherName");
        String otherDate = request.getParameter("otherDate");
        String otherAgent = request.getParameter("otherAgent");
        String landOkBy = request.getParameter("landOkBy");
        String landCategory = request.getParameter("landCategory");
        String landAgent = request.getParameter("landAgent");
        
        //Set Date dormat
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
//        bookDate = (!"".equalsIgnoreCase(bookDate) && bookDate != null ? sdf.format(util.convertStringToDate(bookDate)) : "");
//        airDeptDate = (!"".equalsIgnoreCase(airDeptDate) && airDeptDate != null ? sdf.format(util.convertStringToDate(airDeptDate)) : "");
//        hotelCheckIn = (!"".equalsIgnoreCase(hotelCheckIn) && hotelCheckIn != null ? sdf.format(util.convertStringToDate(hotelCheckIn)) : "");
//        hotelCheckOut = (!"".equalsIgnoreCase(hotelCheckOut) && hotelCheckOut != null ? sdf.format(util.convertStringToDate(hotelCheckOut)) : "");
//        tourDate = (!"".equalsIgnoreCase(tourDate) && tourDate != null ? sdf.format(util.convertStringToDate(tourDate)) : "");   
//        otherDate = (!"".equalsIgnoreCase(otherDate) && otherDate != null ? sdf.format(util.convertStringToDate(otherDate)) : "");
        
        if("search".equalsIgnoreCase(action)){
            if("1".equalsIgnoreCase(bookType)){//Air
                List<BookingAirSummaryView> bookingAirSummaryViewList = new LinkedList<BookingAirSummaryView>();
                bookingAirSummaryViewList = workspaceService.getListBookingAirSummaryView(bookRefNo,bookLeader,bookDate,airPnr,airDeptDate,airFlight);
                request.setAttribute(DATALIST,bookingAirSummaryViewList);
                
            }else if("2".equalsIgnoreCase(bookType)){//Hotel
                List<BookingHotelSummaryView> bookingHotelSummaryViewList = new LinkedList<BookingHotelSummaryView>();
                bookingHotelSummaryViewList = workspaceService.getListBookingHotelSummaryView(bookRefNo,bookLeader,bookDate,hotelName,hotelCheckIn,hotelCheckOut);
                request.setAttribute(DATALIST,bookingHotelSummaryViewList);
                
            }else if("3".equalsIgnoreCase(bookType)){//Package
                List<BookingPackageSummaryView> bookingPackageSummaryViewList = new LinkedList<BookingPackageSummaryView>();
                bookingPackageSummaryViewList = workspaceService.getListBookingPackageSummaryView(bookRefNo,bookLeader,bookDate,packageName,packageAgent);
                request.setAttribute(DATALIST,bookingPackageSummaryViewList);
                
            }else if("4".equalsIgnoreCase(bookType)){//Day tours
                List<BookingDayTourSummaryView> bookingDayTourSummaryViewList = new LinkedList<BookingDayTourSummaryView>();
                bookingDayTourSummaryViewList = workspaceService.getListBookingDayTourSummaryView(bookRefNo,bookLeader,bookDate,tourCode,tourName,tourDate,tourPickUp,tourAgent);
                request.setAttribute(DATALIST,bookingDayTourSummaryViewList);
                
            }else if("5".equalsIgnoreCase(bookType)){//Other
                List<BookingOtherSummaryView> bookingOtherSummaryViewList = new LinkedList<BookingOtherSummaryView>();
                bookingOtherSummaryViewList = workspaceService.getListBookingOtherSummaryView(bookRefNo,bookLeader,bookDate,otherCode,otherName,otherDate,otherAgent);
                request.setAttribute(DATALIST,bookingOtherSummaryViewList);
                
            }else if("6".equalsIgnoreCase(bookType)){//Land
                List<BookingLandSummaryView> bookingLandSummaryViewList = new LinkedList<BookingLandSummaryView>();
                bookingLandSummaryViewList = workspaceService.getListBookingLandSummaryView(bookRefNo,bookLeader,bookDate,landOkBy,landAgent,landCategory);
                request.setAttribute(DATALIST,bookingLandSummaryViewList);
            }                              
        }
        request.setAttribute(SEARCHTYPE,bookType);
        request.setAttribute("bookRefNo",bookRefNo);
        request.setAttribute("bookLeader",bookLeader);
        request.setAttribute("bookDate",bookDate);
        request.setAttribute("airPnr",airPnr);
        request.setAttribute("airDeptDate",airDeptDate);
        request.setAttribute("airFlight",airFlight);
        request.setAttribute("hotelName",hotelName);
        request.setAttribute("hotelCheckIn",hotelCheckIn);
        request.setAttribute("hotelCheckOut",hotelCheckOut);
        request.setAttribute("packageName",packageName);
        request.setAttribute("packageAgent",packageAgent);
        request.setAttribute("tourCode",tourCode);
        request.setAttribute("tourName",tourName);
        request.setAttribute("tourDate",tourDate);
        request.setAttribute("tourPickUp",tourPickUp);
        request.setAttribute("tourAgent",tourAgent);
        request.setAttribute("otherCode",otherCode);
        request.setAttribute("otherName",otherName);
        request.setAttribute("otherDate",otherDate);
        request.setAttribute("otherAgent",otherAgent);
        request.setAttribute("landOkBy",landOkBy);
        request.setAttribute("landCategory",landCategory);
        request.setAttribute("landAgent",landAgent);
        
        return BookInformation;
    }

    public WorkSpaceService getWorkspaceService() {
        return workspaceService;
    }

    public void setWorkspaceService(WorkSpaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }
}
