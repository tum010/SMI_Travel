package com.smi.travel.masterdata.controller;

import com.smi.travel.controller.LoginController;
import com.smi.travel.datalayer.entity.MAmadeus;
import com.smi.travel.datalayer.service.MAmadeusService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MAmadeusController extends SMITravelController {

    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private static final String className = "MAmadeusController";
    private static final ModelAndView Amadeus = new ModelAndView("MAmadeus");
    private static final ModelAndView Amadeus_REFRESH = new ModelAndView(new RedirectView("MAmadeus.smi", true));
    private static final String DataList = "Amadeus_List";
    private static final String DataLap = "AmadeusLap";
    private static final String TransactionResult = "result";
    private MAmadeusService mAmadeusService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");

        System.out.println(className + " - action  :" + action);
        int result = 0;

        if ("update".equalsIgnoreCase(action)) {
            log.info("this here edit function");
            String id = request.getParameter("GID");
            String name = request.getParameter("Name");
            String section = request.getParameter("Section");
            String nodlm = request.getParameter("NodLm");
            String length = request.getParameter("Length");
            String startLength = request.getParameter("StartLength");
            MAmadeus mAma = new MAmadeus();
            mAma.setId(id);
            mAma.setName(name);
            mAma.setSection(section);
            mAma.setNodlm(nodlm);
//            mAma.setLine(line);
            mAma.setLength(Integer.parseInt(length));
            mAma.setStartlength(Integer.parseInt(startLength));
            result = mAmadeusService.EditGalileo(mAma);
            if (result == 1) {
                request.setAttribute(TransactionResult, "save successful");
            } else {
                request.setAttribute(TransactionResult, "save unsuccessful");
            }

        }
        List<MAmadeus> listMAmadeus = mAmadeusService.getAmadeusList();
        request.setAttribute(DataList, listMAmadeus);
        return Amadeus;
    }

    public void loadcritite() {

    }

    public void saveCritite() {

    }

    public MAmadeusService getmAmadeusService() {
        return mAmadeusService;
    }

    public void setmAmadeusService(MAmadeusService mAmadeusService) {
        this.mAmadeusService = mAmadeusService;
    }

}
