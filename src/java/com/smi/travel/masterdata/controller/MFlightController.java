package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.service.MFlightService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MFlightController extends SMITravelController {

    private static final ModelAndView MFlight = new ModelAndView("MFlight");
    private static final String DataList = "Flight_List";
    private static final String DataLap = "flightLap";
    private static final String TransectionResult = "result";
    private MFlightService FlightService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("FlightCode");
        String name = request.getParameter("FlightName");
        String FlightID = request.getParameter("FlightID");
        int result = 0;
        System.out.println("action  :" + action);
        String resultValidate = "";
        MFlight flight = new MFlight();
        flight.setCode((String.valueOf(code)).toUpperCase());
        flight.setName((String.valueOf(name)).toUpperCase());
        flight.setId(FlightID);

        if ("search".equalsIgnoreCase(action)) {
            List<MFlight> list = FlightService.searchFlight(flight,2);
            request.setAttribute(DataList, list);
        } else if ("add".equalsIgnoreCase(action)) {
            resultValidate = FlightService.validateFlight(flight, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = FlightService.insertFlight(flight);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, FlightService.searchFlight(flight,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }
        } else if ("update".equalsIgnoreCase(action)) {
            resultValidate = FlightService.validateFlight(flight, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = FlightService.UpdateFlight(flight);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, FlightService.searchFlight(flight,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            result = FlightService.DeleteFlight(flight);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        
        request.setAttribute("flightCode", code);
        request.setAttribute("flightName", name);
        
        return MFlight;
    }

    public MFlightService getFlightService() {
        return FlightService;
    }

    public void setFlightService(MFlightService FlightService) {
        this.FlightService = FlightService;
    }

}
