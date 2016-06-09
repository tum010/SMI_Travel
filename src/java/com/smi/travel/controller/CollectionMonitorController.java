package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Receipt;
import com.smi.travel.datalayer.service.CollectionNirvanaService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CollectionNirvana;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class CollectionMonitorController extends SMITravelController {
    private static final ModelAndView CollectionMonitor = new ModelAndView("CollectionMonitor");
    private static final ModelAndView CollectionMonitor_REFRESH = new ModelAndView(new RedirectView("CollectionMonitor.smi", true));
    private static final String TYPE = "type";
    private static final String DEPARTMENT = "department";
    private static final String STATUS = "status";
    private static final String DATEFROM = "collectionFromDate";
    private static final String DATETO = "collectionToDate";
    private static final String INVNO = "invno";
    private static final String CollectionList = "CollectionList";
    private static final String STATUSUPDATE = "status_update";
    private static final String BANKNIRVANALIST = "bankNirvanaList";
    private CollectionNirvanaService collectionNirvanaService;
    private UtilityService utilityService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        String action = request.getParameter("action");
        String department = request.getParameter("department");
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        String from = util.covertStringDateToFormatYMD(request.getParameter("inputFromDate"));
        String to = util.covertStringDateToFormatYMD(request.getParameter("inputToDate"));
        String invno = request.getParameter("invno");
        
        request.setAttribute(DEPARTMENT,department);
        request.setAttribute(TYPE,type);
        request.setAttribute(STATUS,status);
        request.setAttribute(DATEFROM,from);
        request.setAttribute(DATETO,to);
        request.setAttribute(INVNO,invno);
        request.setAttribute(BANKNIRVANALIST,utilityService.getListBankNirvana());
        
        if ("search".equalsIgnoreCase(action)){
            List<CollectionNirvana>  collectionNirvanas = collectionNirvanaService.getCollectionNirvanaFromFilter(department, type, status, from, to, invno, "");
            if(collectionNirvanas != null){
                request.setAttribute(CollectionList,collectionNirvanas);
            } 
        }else if("export".equals(action)){
            String coCount = request.getParameter("coCount");
            List<CollectionNirvana> listCo = new LinkedList<>();
            int count = Integer.parseInt(coCount);
            for(int i=1;i<=count;i++){
                String isSelect = request.getParameter("selectAll"+i);
                if(isSelect != null){
                    CollectionNirvana cn = new CollectionNirvana();
                    String inputId = request.getParameter("inputId"+i);
                    System.out.println(" inputId  :::" + inputId);
                    cn.setRowid(inputId);
                    listCo.add(cn);
                }
            }
           if(listCo != null){
//             String isExport = arMonitorService.ExportARFileInterface(listAr,arMonitorService.GetPartFileExport());
//               String result = "success";
               String result = collectionNirvanaService.MappingCollectionNirvana(listCo);
               request.setAttribute(STATUSUPDATE, result);
               if("".equalsIgnoreCase(result)){
                    request.setAttribute("update", "updatesuccess");
                }else{
                    request.setAttribute("update", "updatefail");
                }
//               if("success".equals(result)){
//                   String isUpdate = collectionNirvanaService.UpdateStatusCollection(listCo);
                   
//               }               
               listCo = collectionNirvanaService.getCollectionNirvanaFromFilter(department, type, status, from, to, invno, "");
               request.setAttribute(CollectionList, listCo);
           }else{
               request.setAttribute(CollectionList, null);
           }
        }else if("updatereceipt".equals(action)){
            String coCount = request.getParameter("coCount");
            List<CollectionNirvana> listCo = new LinkedList<CollectionNirvana>();
            int count = Integer.parseInt(coCount);
            List<Receipt> receiptList = new LinkedList<Receipt>();
            for(int i=1;i<=count;i++){
                String inputId = request.getParameter("inputId"+i);
                String bankcode = request.getParameter("bankcode"+i);
                if(!"".equalsIgnoreCase(inputId)){
                    System.out.println(" inputId :: " + inputId);
                    System.out.println(" bankcode :: " + bankcode);
                    Receipt rec = new Receipt();
                    rec.setId(inputId);
                    rec.setBankCode(!"".equalsIgnoreCase(bankcode) && !"null".equalsIgnoreCase(bankcode) && bankcode != null ? bankcode : "");
                    receiptList.add(rec);
                }
            }
            String resultsave = collectionNirvanaService.UpdateBankCodeToReceipt(receiptList);
            request.setAttribute("saveresult", resultsave);
            listCo = collectionNirvanaService.getCollectionNirvanaFromFilter(department, type, status, from, to, invno, "");
            request.setAttribute(CollectionList, listCo);
        }
//        System.out.println(" action " + action);
//        System.out.println(" collectionDepartment " + collectionDepartment);
//        System.out.println(" collectionType " + collectionType);
//        System.out.println(" collectionStatus " + collectionStatus);
//        System.out.println(" collectionFromDate " + inputFromDate);
//        System.out.println(" collectionToDate " + inputToDate);
//        System.out.println(" collectionInvNo " + collectionInvNo);

        return CollectionMonitor;
    }

    public CollectionNirvanaService getCollectionNirvanaService() {
        return collectionNirvanaService;
    }

    public void setCollectionNirvanaService(CollectionNirvanaService collectionNirvanaService) {
        this.collectionNirvanaService = collectionNirvanaService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
}
