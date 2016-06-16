package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.service.ARMonitorService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
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
    private static final String STATUSUPDATE = "status_update";
    private UtilityService utilityService;
    private ARMonitorService arMonitorService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        String action = request.getParameter("action");
        String invoiceType = request.getParameter("invoiceType");
        String departmnt = request.getParameter("department");
        String type = request.getParameter("arType");
        String from = util.covertStringDateToFormatYMD(request.getParameter("arFromDate"));
        String to = util.covertStringDateToFormatYMD(request.getParameter("arToDate"));
        String status = request.getParameter("arStatus");
        String accno = request.getParameter("accno");
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
           listAr = arMonitorService.SearchArNirvanaFromFilter(invoiceType, departmnt, type, from, to, status, accno);
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
            request.setAttribute("accno", accno);
        }else if("export".equals(action)){
            String arCount = request.getParameter("arCount");
            List<ARNirvana> listAr = new LinkedList<>();
            int count = Integer.parseInt(arCount);
            for(int i=1;i<=count;i++){
                String isSelect = request.getParameter("selectAll"+i);
                System.out.println("isSelect : "+isSelect);
                if(isSelect != null){
                    ARNirvana ar = new ARNirvana();
                    String inputId = request.getParameter("inputId"+i);
                  //  ar.setInvid(inputId);
                    ar.setRowid(inputId);
                    listAr.add(ar);
                    
                    System.out.println("data : "+ar);
                }
            }
           if(listAr != null){
               System.out.println("export : ");
//               String isExport = arMonitorService.ExportARFileInterface(listAr,arMonitorService.GetPartFileExport());
               String result = arMonitorService.MappingARNirvana(listAr);
               request.setAttribute(STATUSUPDATE, result);
                if("".equalsIgnoreCase(result)){
                    request.setAttribute("update", "success");
                }else if("cannotconnect".equalsIgnoreCase(result)){
                    request.setAttribute("update", "cannotconnect");    
                }else{
                    request.setAttribute("update", "fail");
                }
               
//               if("success".equals(result)){
//                   String isUpdate = arMonitorService.UpdateStatusARInterface(listAr);
//                   System.out.println("Update ??? : " + isUpdate);
//                   request.setAttribute("update", isUpdate);
//               }               
               listAr = arMonitorService.SearchArNirvanaFromFilter(invoiceType, departmnt, type, from, to, status, accno);
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
            request.setAttribute("accno", accno);
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
