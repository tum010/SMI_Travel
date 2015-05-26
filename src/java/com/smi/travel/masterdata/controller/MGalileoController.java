package com.smi.travel.masterdata.controller;

import com.smi.travel.controller.LoginController;
import com.smi.travel.datalayer.entity.MGalileo;
import com.smi.travel.datalayer.service.MGalileoService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MGalileoController extends SMITravelController {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private static final ModelAndView Galileo = new ModelAndView("MGalileo");
    private static final ModelAndView Galileo_REFRESH = new ModelAndView(new RedirectView("MGalileo.smi", true));
    private static final String DataList = "Galileo_List";
    private static final String DataLap = "staffLap";
    private static final String TransactionResult = "result";
    private MGalileoService mGalileoService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");

        int result = 0;

        if ("update".equalsIgnoreCase(action)) {
            log.info("this here edit function");
            String id = request.getParameter("GID");
            String name = request.getParameter("Name");
            String section = request.getParameter("Section");
            String line = request.getParameter("Line");
            String length = request.getParameter("Length");
            String startLength = request.getParameter("StartLength");
            MGalileo mGali = new MGalileo();
            mGali.setId(id);
            mGali.setName(name);
            mGali.setSection(section);
            mGali.setLine(line);
            mGali.setLength(Integer.parseInt(length));
            mGali.setStartlength(Integer.parseInt(startLength));
            result = mGalileoService.EditGalileo(mGali);
            if (result == 1) {
                request.setAttribute(TransactionResult, "save successful");
            } else {
                request.setAttribute(TransactionResult, "save unsuccessful");
            }
        }
        List<MGalileo> listGalileo = mGalileoService.getGalileoList();
        request.setAttribute(DataList, listGalileo);
        return Galileo;
    }

    public void loadcritite() {

    }

    public void saveCritite() {

    }

    public MGalileoService getmGalileoService() {
        return mGalileoService;
    }

    public void setmGalileoService(MGalileoService mGalileoService) {
        this.mGalileoService = mGalileoService;
    }
}
