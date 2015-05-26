package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MAirport;
import com.smi.travel.datalayer.service.MAirportService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MAirportController extends SMITravelController {

    private static final ModelAndView MAirport = new ModelAndView("MAirport");
    private static final String DataList = "Airport_List";
    private static final String DataLap = "airportLap";
    private static final String TransectionResult = "result";
    private MAirportService airportService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("AirportCode");
        String name = request.getParameter("AirportName");
        String AirportID = request.getParameter("AirportID");
        System.out.println("action  :" + action);
        int result = 0;
        String resultValidate = "";
        MAirport airport = new MAirport();
        airport.setCode(code);
        airport.setName(name);
        airport.setId(AirportID);

        if ("search".equalsIgnoreCase(action)) {
            List<MAirport> list = airportService.searchAirport(airport, 2);
            request.setAttribute(DataList, list);
        } else if ("add".equalsIgnoreCase(action)) {
            resultValidate = airportService.validateAirport(airport, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = airportService.insertAirport(airport);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, airportService.searchAirport(airport, 1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }

            }
        } else if ("update".equalsIgnoreCase(action)) {
            resultValidate = airportService.validateAirport(airport, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = airportService.UpdateAirport(airport);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, airportService.searchAirport(airport, 1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            result = airportService.DeleteAirport(airport);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        
        request.setAttribute("airportCode", code);
        request.setAttribute("airportName", name);
        
        return MAirport;
    }

    public MAirportService getAirportService() {
        return airportService;
    }

    public void setAirportService(MAirportService airportService) {
        this.airportService = airportService;
    }

}
