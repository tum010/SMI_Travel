package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.service.APNirvanaService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
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
        UtilityFunction util = new UtilityFunction();
        List<MPaytype> mPayTypeList = utilservice.getListMPayType();
        request.setAttribute(MPAYTYPELIST, mPayTypeList);
        
        String action = request.getParameter("action");
        String apPayment = request.getParameter("apPayment");
        String apType = request.getParameter("apType");
        String apStatus = request.getParameter("apStatus");
        String apFromDate = util.covertStringDateToFormatYMD(request.getParameter("apFromDate"));
        String apToDate = util.covertStringDateToFormatYMD(request.getParameter("apToDate"));
        String apCount = request.getParameter("apCount");
        String apAccno = request.getParameter("apAccno");
        if("search".equalsIgnoreCase(action)){
            List<APNirvana> apNirvanaList = apNirvanaService.SearchApNirvanaFromFilter(apPayment, apType, apStatus, apFromDate, apToDate, apAccno);
            request.setAttribute(DATALIST, apNirvanaList);
        } else if("export".equalsIgnoreCase(action)){
            List<APNirvana> apNirvanaData = new ArrayList<APNirvana>();
            int count = Integer.parseInt(apCount);
            for(int i=1;i<=count;i++){
                String isSelect = request.getParameter("selectAll"+i);
                if("1".equalsIgnoreCase(isSelect)){
                    APNirvana apNirvana = new APNirvana();
                    String paymentDetailId = request.getParameter("paymentDetailId"+i);
                    String paymentType = request.getParameter("paymentType"+i);
                    String rowid = request.getParameter("rowid"+i);
                    System.out.println(" paymentDetailId " + paymentDetailId);
                    System.out.println(" rowid " + rowid);
                    apNirvana.setPayment_detail_id(paymentDetailId);
                    apNirvana.setPaymenttype(paymentType);
                    apNirvana.setRowid(rowid);
                    apNirvanaData.add(apNirvana);
                }
            }
//            String result = apNirvanaService.ExportAPFileInterface(apNirvanaData,apNirvanaService.GetPartFileExport()); 
            String result = apNirvanaService.MappingAPNirvana(apNirvanaData);
            if(!"fail".equalsIgnoreCase(result)){             
                String update = apNirvanaService.UpdateStatusAPInterface(apNirvanaData,result);
                System.out.println("Update Result : "+update);                
                request.setAttribute("update", update);
            }
            List<APNirvana> apNirvanaList = apNirvanaService.SearchApNirvanaFromFilter(apPayment, apType, apStatus, apFromDate, apToDate, apAccno);
            request.setAttribute(DATALIST, apNirvanaList);
        }
        
        request.setAttribute("apPayment", apPayment);
        request.setAttribute("apType", apType);
        request.setAttribute("apStatus", apStatus);
        request.setAttribute("apFromDate", apFromDate);
        request.setAttribute("apToDate", apToDate);
        request.setAttribute("apAccno", apAccno);
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
