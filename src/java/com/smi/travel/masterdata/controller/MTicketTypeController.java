package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MTicketType;
import com.smi.travel.datalayer.service.MTicketTypeService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MTicketTypeController extends SMITravelController {

    private static final ModelAndView MTicketType = new ModelAndView("MTicketType");
    private static final String DataList = "TicketType_List";
    private static final String DataLap = "tickettypeLap";
    private static final String TransectionResult = "result";
    private MTicketTypeService TicketTypeService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("TicketTypeCode");
        String name = request.getParameter("TicketTypeName");
        String TicketTypeID = request.getParameter("TicketTypeID");
        int result = 0;
        System.out.println("action  :" + action);
        String resultValidate = "";
        MTicketType tickettype = new MTicketType();
        tickettype.setCode((String.valueOf(code)).toUpperCase());
        tickettype.setName((String.valueOf(name)).toUpperCase());
        tickettype.setId(TicketTypeID);

        if ("search".equalsIgnoreCase(action)) {
            List<MTicketType> list = TicketTypeService.searchTicketType(tickettype,2);
            request.setAttribute(DataList, list);
        } else if ("add".equalsIgnoreCase(action)) {
            resultValidate = TicketTypeService.validateTicketType(tickettype, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = TicketTypeService.insertTicketType(tickettype);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, TicketTypeService.searchTicketType(tickettype,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }
        } else if ("update".equalsIgnoreCase(action)) {
            resultValidate = TicketTypeService.validateTicketType(tickettype, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = TicketTypeService.UpdateTicketType(tickettype);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, TicketTypeService.searchTicketType(tickettype,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            result = TicketTypeService.DeleteTicketType(tickettype);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        
        request.setAttribute("ticketTypeCode", code);
        request.setAttribute("ticketTypeName", name);
        
        return MTicketType;
    }

    public MTicketTypeService getTicketTypeService() {
        return TicketTypeService;
    }

    public void setTicketTypeService(MTicketTypeService TicketTypeService) {
        this.TicketTypeService = TicketTypeService;
    }

}
