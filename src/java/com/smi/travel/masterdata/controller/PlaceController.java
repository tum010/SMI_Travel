package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MPlacestatus;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.service.PlaceService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class PlaceController extends SMITravelController {

    private static final ModelAndView Place = new ModelAndView("Place");
    private static final String DataList = "Place_List";
    private static final String DataLap = "placeLap";
    private static final String TransectionResult = "result";
    private static final String LIST_STATUS = "List_status";
    private PlaceService placeService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String name = request.getParameter("Placename");
        String status = request.getParameter("Placestatus");
        String PlaceID = request.getParameter("PlaceID");
        int result = 0;
        System.out.println("action  :" + action);
        String resultValidate = "";
        Place place = new Place();
        place.setPlace((String.valueOf(name)).toUpperCase());
        
        if ((status != null) && !("0".equalsIgnoreCase(status))) {
            MPlacestatus Placestatus = new MPlacestatus();
            Placestatus.setId(status);
            place.setMPlacestatus(Placestatus);
            System.out.println("status : "+place.getMPlacestatus().getId());
        }

        place.setId(PlaceID);

        if ("search".equalsIgnoreCase(action)) {
            List<Place> list = placeService.searchPlace(place, 2);    
            request.setAttribute(DataList, list);
        } else if ("add".equalsIgnoreCase(action)) {
            resultValidate = placeService.validatePlace(place, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = placeService.insertPlace(place);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, placeService.searchPlace(place, 1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }
        } else if ("update".equalsIgnoreCase(action)) {
            Place Vplace = new Place();
            Vplace.setPlace((String.valueOf(name)).toUpperCase());
            Vplace.setId(PlaceID);
            
            resultValidate = placeService.validatePlace(Vplace, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = placeService.UpdatePlace(place);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, placeService.searchPlace(place, 1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            result = placeService.DeletePlace(place);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        request.setAttribute(LIST_STATUS, placeService.getListStatus());
        request.setAttribute("placeName", name);
        request.setAttribute("placeStatusID", status);

        return Place;
    }

    public PlaceService getPlaceService() {
        return placeService;
    }

    public void setPlaceService(PlaceService placeService) {
        this.placeService = placeService;
    }

}
