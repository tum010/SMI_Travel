package com.smi.travel.masterdata.controller;
import com.smi.travel.datalayer.entity.MHost;
import com.smi.travel.datalayer.service.MHostService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class MHostController extends SMITravelController {
    private static final ModelAndView MHost = new ModelAndView("MHost");
    private static final ModelAndView MHost_REFRESH = new ModelAndView(new RedirectView("MHost.smi", true));
    private static final String DataList = "Currency_List";
    private static final String DataLap = "currencyLap";
    private static final String TransectionResult = "result";
    private MHostService MHostService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String name = request.getParameter("HostName");
        String status = request.getParameter("HostStatus");
        String HostID = request.getParameter("HostID");
        int result = 0;
        System.out.println("action  :" + action);
        String resultValidate = "";
        MHost host = new MHost();
        host.setStatus((String.valueOf(status)).toUpperCase());
        host.setName((String.valueOf(name)).toUpperCase());
        host.setId(HostID);
        
        if ("search".equalsIgnoreCase(action)) {
            List<MHost> list = MHostService.searchHost(host);
            request.setAttribute(DataList, list);
        } else if ("add".equalsIgnoreCase(action)) {
            resultValidate = MHostService.validateHost(host, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = MHostService.insertHost(host);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, MHostService.searchHost(host));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }
        } else if ("update".equalsIgnoreCase(action)) {
            resultValidate = MHostService.validateHost(host, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = MHostService.UpdateHost(host);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, MHostService.searchHost(host));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            result = MHostService.DeleteHost(host);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        
        request.setAttribute("hostStatus", status);
        request.setAttribute("hostName", name);
        return MHost;
    }

    public MHostService getMHostService() {
        return MHostService;
    }

    public void setMHostService(MHostService MHostService) {
        this.MHostService = MHostService;
    }
    
    
}
