package com.smi.travel.masterdata.controller;

import com.smi.travel.controller.DaytourController;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.Hotel;
import com.smi.travel.datalayer.service.HotelService;
import com.smi.travel.datalayer.service.MDaytourService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MDaytourController extends SMITravelController {

    private static final Logger log = Logger.getLogger(MDaytourController.class.getName());
    private static final ModelAndView MDaytour = new ModelAndView("MDaytour");
    private static final ModelAndView MDaytour_REFRESH = new ModelAndView(new RedirectView("MDaytour.smi", true));
    private MDaytourService daytourService;
    private static final String DATALIST = "MDaytour_list";
    private static final String TransectionResult = "result";
    private static final String DaytourSearch = "DaytourSearch"; 
    private static final String Stafftour = "stafftour";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        String action = request.getParameter("action");
        String code = util.StringUtilReplaceChar(request.getParameter("tourCode"));
        String name = util.StringUtilReplaceChar(request.getParameter("tourName"));
        String daytourId = request.getParameter("daytourId");
      
        log.info("action["+action+"] code["+code+"] name["+name+"] id["+daytourId+"]");
        Daytour daytour = new Daytour();
        daytour.setCode(code);
        daytour.setName(name);
        daytour.setId(daytourId);
        if ("search".equalsIgnoreCase(action)) {
            System.out.println("id =" +daytourId);
            List<Daytour> list = daytourService.searchTourList(daytour, 2);
            request.setAttribute(DaytourSearch, daytour);
            request.setAttribute(DATALIST, list);
        } else if ("delete".equalsIgnoreCase(action)) {
            String transactionResult = daytourService.DeleteTour(daytour);
            request.setAttribute(TransectionResult, transactionResult);
        }
        request.setAttribute(Stafftour, daytourService.getStafftour());
        return MDaytour;
    }

    private void getDaytourFromRequest(Daytour daytour) {
        
    }
    
    public MDaytourService getDaytourService() {
        return daytourService;
    }

    public void setDaytourService(MDaytourService daytourService) {
        this.daytourService = daytourService;
    }

}
