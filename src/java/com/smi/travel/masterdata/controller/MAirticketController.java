package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MAirline;
import com.smi.travel.datalayer.service.MAirticketService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MAirticketController extends SMITravelController {

    private static final ModelAndView MAirticket = new ModelAndView("Mairticket");
    private static final String DataList ="Airline_List";
    private static final String DataListSize ="Airline_ListSize";
    private static final String DataLap ="airlineLap";
    private static final String TransectionResult ="result";
    private MAirticketService AirticketService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        String action = request.getParameter("action");
        String code = request.getParameter("AirCode");
        String name = request.getParameter("AirName");
        String code3L = request.getParameter("Code3");
        String AirID = request.getParameter("AirID");
        System.out.println("action  :" + action);
        String resultValidate = "";
        int result = 0;
        MAirline air = new MAirline();
        air.setCode(code);
        air.setName(name);
        air.setCode3Letter(code3L);
        air.setId(AirID);

        if ("search".equalsIgnoreCase(action)) {
            code = request.getParameter("AirCodeS");
            name = request.getParameter("AirNameS");
            code3L = request.getParameter("Code3S");

            air.setCode(code.toUpperCase());
            air.setName(name.toUpperCase());
            air.setCode3Letter(code3L.toUpperCase());
            List<MAirline> list = AirticketService.searchAirline(air,2);
           
            request.setAttribute(DataList, list);
         
            
        } else if ("add".equalsIgnoreCase(action)) {
            resultValidate = AirticketService.validateAirline(air, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = AirticketService.InsertAirline(air);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, AirticketService.searchAirline(air,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }

            }
        } else if ("update".equalsIgnoreCase(action)) {
            resultValidate = AirticketService.validateAirline(air, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = AirticketService.UpdateAirline(air);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, AirticketService.searchAirline(air,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            result = AirticketService.DeleteAirline(air);
            if (result == 1) {
                request.setAttribute("result", "delete successful");    
            } else {
                request.setAttribute("result", "delete unsuccessful");
            }
        }

        request.setAttribute("airCode", code);
        request.setAttribute("airName", name);
        request.setAttribute("code3", code3L);
        
        return MAirticket;
    }

    public void loadcritite() {

    }

    public void saveCritite() {

    }

    public MAirticketService getAirticketService() {
        return AirticketService;
    }

    public void setAirticketService(MAirticketService AirticketService) {
        this.AirticketService = AirticketService;
    }

}
