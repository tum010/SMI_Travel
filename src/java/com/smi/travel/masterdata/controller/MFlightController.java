package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.entity.MFlightservice;
import com.smi.travel.datalayer.service.MFlightService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.block.Arrangement;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MFlightController extends SMITravelController {

    private static final ModelAndView MFlight = new ModelAndView("MFlight");
    private static final String DataList = "Flight_List";
    private static final String DataFlight = "FlightData";
    private static final String DataListService = "FlightService_List";
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
        if(!"delete".equals(action)){
            List<MFlightservice> listFlightService = getListMFlightService(request, flight, action);
            if(listFlightService != null){
                flight.setmFlightservice(listFlightService);
            }else{
                flight.setmFlightservice(null);
            }   
        }
        if ("search".equalsIgnoreCase(action)) {
            List<MFlight> list = FlightService.searchFlight(flight,2);
//            request.setAttribute(DataListService, FlightService.getListFlightService(flight.getId()));
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
            String idDelete = request.getParameter("setIdMFlightService");
            List<String> idDel = new ArrayList<String>();
            String id[] = idDelete.split("/");
            
            for (int i = 0; i < id.length ; i++) {
                idDel.add(id[i]);
            }
            System.out.println("Size id : " + idDel.size());
            resultValidate = FlightService.validateFlight(flight, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = FlightService.UpdateFlight(flight,idDel);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, FlightService.searchFlight(flight,1));
                    request.setAttribute(DataFlight, flight);
                    request.setAttribute(DataListService, FlightService.getListFlightService(flight.getId()));
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
    
    public List<MFlightservice> getListMFlightService(HttpServletRequest request,MFlight mFlight,String action){
        List<MFlightservice> listFlightService = new LinkedList<MFlightservice>();
        String count = request.getParameter("counterTable");
        if (count == null) {
            return null;
        }
        
        int counter = Integer.parseInt(count);
        if (counter < 1) {
            return null;
        }
        
        for (int i = 1; i <= counter ; i++) {
            MFlightservice mflightService = new MFlightservice();
            String id = request.getParameter("FlightServiceId"+i);
            String code = request.getParameter("FlightServiceCode"+i);
            String name = request.getParameter("FlightServiceName"+i);
            
            if(id != null && !"".equals(id)){
                mflightService.setId(id);
            }
            
            if(name != null && !"".equals(name)){
                mflightService.setClassName(name);
            }
            
            mflightService.setMFlight(mFlight);
            if(code != null && !"".equals(code)){
                mflightService.setClassCode(code);
                listFlightService.add(mflightService);
            }
        }
        
        return listFlightService;
    }

    public MFlightService getFlightService() {
        return FlightService;
    }

    public void setFlightService(MFlightService FlightService) {
        this.FlightService = FlightService;
    }

}
