package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.service.APNirvanaService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class APMonitorController extends SMITravelController {
    private static final ModelAndView APMonitor = new ModelAndView("APMonitor");
    private static final ModelAndView APMonitor_REFRESH = new ModelAndView(new RedirectView("APMonitor.smi", true));
    private static final String MPAYTYPELIST = "mpaytype_list";
    private static final String DATALIST = "data_list";
    private APNirvanaService apNirvanaService;
    private UtilityService utilservice;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<MPaytype> mPayTypeList = utilservice.getListMPayType();
        request.setAttribute(MPAYTYPELIST, mPayTypeList);
        
        String action = request.getParameter("action");
        String apPayment = request.getParameter("apPayment");
        String apType = request.getParameter("apType");
        String apStatus = request.getParameter("apStatus");
        String apFromDate = request.getParameter("apFromDate");
        String apToDate = request.getParameter("apToDate");
        
//        if("search".equalsIgnoreCase(action)){
//            List<APNirvana> apNirvanaList = apNirvanaService.SearchApNirvanaFromFilter(apPayment, apType, apStatus, apFromDate, apToDate);
//            request.setAttribute(DATALIST, apNirvanaList);
//        }
        
        request.setAttribute("apPayment", apPayment);
        request.setAttribute("apType", apType);
        request.setAttribute("apStatus", apStatus);
        request.setAttribute("apFromDate", apFromDate);
        request.setAttribute("apToDate", apToDate);
        
        return APMonitor;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public APNirvanaService getApNirvanaService() {
        return apNirvanaService;
    }

    public void setApNirvanaService(APNirvanaService apNirvanaService) {
        this.apNirvanaService = apNirvanaService;
    }
}
