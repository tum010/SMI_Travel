package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.service.ARMonitorService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.master.controller.SMITravelController;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class ARMonitorController extends SMITravelController {
    private static final ModelAndView ARMonitor = new ModelAndView("ARMonitor");
    private static final ModelAndView ARMonitor_REFRESH = new ModelAndView(new RedirectView("ARMonitor.smi", true));
    private UtilityService utilityService;
    private ARMonitorService arMonitorService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String invoiceType = request.getParameter("invoiceType");
        String departmnt = request.getParameter("department");
        String type = request.getParameter("arType");
        String from = request.getParameter("arFromDate");
        String to = request.getParameter("arToDate");
        String status = request.getParameter("arStatus");
        
        //List Type
        List<MBilltype> listType = new LinkedList<>();
        listType = utilityService.getListMBilltype();
        if(listType != null){
            request.setAttribute("listType", listType);
        }else{
            request.setAttribute("listType", null);
        }
        
         //Search
        if("searchAr".equals(action)){
           List<ARNirvana> listAr = new LinkedList<>();
           listAr = arMonitorService.SearchArNirvanaFromFilter(invoiceType, departmnt, type, from, to, status);
           if(listAr != null){
               request.setAttribute("listAr", listAr);
           }else{
               request.setAttribute("listAr", null);
           }
            request.setAttribute("invoiceType", invoiceType);
            request.setAttribute("departmnt", departmnt);
            request.setAttribute("type", type);
            request.setAttribute("from", from);
            request.setAttribute("to", to);
            request.setAttribute("status", status);
        }else if("export".equals(action)){
            List<ARNirvana> listAr = new LinkedList<>();
           listAr = arMonitorService.SearchArNirvanaFromFilter(invoiceType, departmnt, type, from, to, status);
           if(listAr != null){
               String isExport = arMonitorService.ExportARFileInterface(listAr,arMonitorService.GetPartFileExport());
               if("success".equals(isExport)){
                   String isUpdate = arMonitorService.UpdateStatusARInterface(listAr);
                   System.out.println("Update ??? : " + isUpdate);
                   request.setAttribute("update", isUpdate);
               }
               request.setAttribute("listAr", listAr);
           }else{
               request.setAttribute("listAr", null);
           }
            request.setAttribute("invoiceType", invoiceType);
            request.setAttribute("departmnt", departmnt);
            request.setAttribute("type", type);
            request.setAttribute("from", from);
            request.setAttribute("to", to);
            request.setAttribute("status", status);            
        }
        return ARMonitor;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setArMonitorService(ARMonitorService arMonitorService) {
        this.arMonitorService = arMonitorService;
    }

    public ARMonitorService getArMonitorService() {
        return arMonitorService;
    }
    

}
