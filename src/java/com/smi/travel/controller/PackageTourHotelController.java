/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.PackageTourHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Kanokporn
 */
public class PackageTourHotelController extends SMITravelController {
    private static final ModelAndView PackageTourHotel = new ModelAndView("PackageTourHotel");
    private static final ModelAndView PackageTourHotel_REFRESH = new ModelAndView(new RedirectView("PackageTourHotel.smi", true));
    private UtilityService utilityService;
    private PackageTourHotelService packageTourHotelService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String printby = user.getRole().getName();
        request.setAttribute("printby", printby);
        return PackageTourHotel;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public PackageTourHotelService getPackageTourHotelService() {
        return packageTourHotelService;
    }

    public void setPackageTourHotelService(PackageTourHotelService packageTourHotelService) {
        this.packageTourHotelService = packageTourHotelService;
    }
    
}
