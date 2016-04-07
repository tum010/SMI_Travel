package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.CreditNote;
import com.smi.travel.datalayer.entity.CreditNoteDetail;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.service.CheckDuplicateUserService;
import com.smi.travel.datalayer.service.CreditNoteService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CheckDuplicateUser;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class CreditNoteController extends SMITravelController {

    private CreditNoteService creditNoteService;
    private UtilityService utilityService;
    private static final ModelAndView CreditNote = new ModelAndView("CreditNote");
    private static final String LINKNAME = "CreditNote";
    private static final ModelAndView CreditNote_REFRESH = new ModelAndView(new RedirectView("CreditNote.smi", true));
    private static final HashMap<String,String> type = new HashMap();
    private static final String CHECKDUPLICATEUSER = "checkDuplicateUser";
    private static String checkDuplicate = "";
    private static String callPageFrom = "";
    private static String department = "";
    private CheckDuplicateUserService checkDuplicateUserService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        type.put("W", "Wendy");
        type.put("I", "Inbound");
        type.put("O", "Outbound");
        UtilityFunction utilty = new UtilityFunction();
        request.setAttribute("enableVoid", true);
        request.setAttribute("disableVoid", true);
        System.out.println("request.getRequestURI() :" + request.getRequestURI());
        callPageFrom = utilty.getAddressUrl(request.getRequestURI()).replaceAll(LINKNAME, "");//request.getParameter("type");
        department = "";
//        if (callPageFrom != null) {
//            department = callPageFrom;
//        }
        if (callPageFrom != null) {
            if("W".equalsIgnoreCase(callPageFrom)){
                department = "Wendy";
            }else if("O".equalsIgnoreCase(callPageFrom)){
                department = "Outbound";
            }else if("I".equalsIgnoreCase(callPageFrom)){
                department = "Inbound";
            }            
        }
        String action = request.getParameter("action");
        String creditNo = request.getParameter("cnNo");
        String creditId = request.getParameter("cnId");
        SystemUser user = (SystemUser) session.getAttribute("USER");
        request.setAttribute("username", user.getUsername());
        String wildCardSearch = request.getParameter("wildCardSearch");
        String keyCode = request.getParameter("keyCode");
        
        String cnNoForCheckUser = request.getParameter("cnNoForCheckUser");
        String operationTableId = request.getParameter("operationTableId");
        String operationUser = request.getParameter("operationUser");
        String operationDate = request.getParameter("operationDate");
        String checkDuplicateUser = "";
        String clearDuplicateUser = "";
        
        //Duplicate User
        if("operationUpdate".equalsIgnoreCase(action)){
            checkDuplicateUser = checkDuplicateUser(request,response,session,operationTableId,3);
            action = "search";
        }

        if ("search".equalsIgnoreCase(action)) {
            if(cnNoForCheckUser != null){
                if(!"".equalsIgnoreCase(cnNoForCheckUser) && !cnNoForCheckUser.equalsIgnoreCase(creditNo)){
                    CheckDuplicateUser cdu = new CheckDuplicateUser();
                    cdu.setOperationTable("Credit_Note");
                    cdu.setTableId(operationTableId);
                    cdu.setOperationUser(user.getUsername());
                    checkDuplicateUserService.updateOperationNull(cdu);
                }
            }            
            CreditNote creditNote = new CreditNote();
//            creditNote = creditNoteService.getCreditNote(creditNo, type.get(department));
            creditNote = creditNoteService.getCreditNote(creditNo, department);
            if (creditNote == null) {
                request.setAttribute("failStatus", true);
                request.setAttribute("failMessage", "Credit note no:" + creditNo + " not available !");
            } else {
                request.setAttribute("creditNote", creditNote);
                request.setAttribute("enableVoid", true);
                //Duplicate User
                checkDuplicateUser = checkDuplicateUser(request,response,session,creditNote.getId(),1);
            }
        } else if ("save".equalsIgnoreCase(action)) {
            CreditNote cn = mapCreditNoteRequest(request,response,session);
            if("fail".equalsIgnoreCase(cn.getId())){
                return new ModelAndView(new RedirectView("CreditNote"+callPageFrom+".smi?action=search&cnNo="+cn.getCnNo()+"&department="+department, true));
            }
            String result = "fail";
            if (null != cn) {
                if (null == cn.getId() || "".equals(cn.getId())) {
//                    cn.setDepartment(type.get(department));
                    cn.setDepartment(department);
                    cn.setCreateBy(user.getUsername());
                    cn.setCreateDate(Calendar.getInstance().getTime());
                    cn.setOutputTaxStatus(0);
                }else{
                    String createDate = request.getParameter("createDate");
                    cn.setCreateDate(utilty.convertStringToDate(createDate));
                    cn.setDepartment(department);
                }
                result = creditNoteService.saveCreditNote(cn);
            }
            if (!"fail".equals(result)) {
//                CreditNote creditNote = creditNoteService.getCreditNote(result, type.get(department));
                CreditNote creditNote = creditNoteService.getCreditNote(result, department);
                request.setAttribute("creditNote", creditNote);
                request.setAttribute("successStatus", true);
                request.setAttribute("successMessage", "Save Success!");
            } else {
                request.setAttribute("creditNote", cn);
                request.setAttribute("failStatus", true);
                request.setAttribute("failMessage", "Save fail!");
            }

        } else if ("void".equalsIgnoreCase(action)) {          
            String status = request.getParameter("status");
            String cnId = request.getParameter("cnId");
            String cnNo = request.getParameter("cnNo");
            String method = "2".equals(status) ? "Void" : "Cancel Void";
            //Duplicate User
            if(!"".equalsIgnoreCase(cnId) && cnId != null){
                checkDuplicateUser = checkDuplicateUser(request,response,session,cnId,2);
                if("fail".equalsIgnoreCase(checkDuplicateUser)){
                    request.setAttribute("page", callPageFrom);
                    return new ModelAndView(new RedirectView("CreditNote"+callPageFrom+".smi?action=search&cnNo="+creditNo+"&department="+department, true));            
                }
            } 
            String result = creditNoteService.UpdateFinanceStatusCreditNote(cnId, status);
            if ("success".equals(result)) {
                request.setAttribute("successStatus", true);
                request.setAttribute("successMessage", method + " Success!");
            } else {
                request.setAttribute("failStatus", true);
                request.setAttribute("failMessage", method + " fail!");
            }
//            CreditNote creditNote = creditNoteService.getCreditNote(cnNo, type.get(department));
            CreditNote creditNote = creditNoteService.getCreditNote(cnNo, department);
            request.setAttribute("creditNote", creditNote);
            //Duplicate User
            checkDuplicateUser = checkDuplicateUser(request,response,session,cnId,1);
        }else if("new".equalsIgnoreCase(action)){
            clearDuplicateUser = clearDuplicateUser(request,response,session,creditId);
        
        }else if("wildCardSearch".equalsIgnoreCase(action)){
            String cnId = request.getParameter("cnId");
            String cnNo = request.getParameter("cnNo");
            CreditNote creditNote = new CreditNote();
            creditNote = creditNoteService.getCreditNoteByWildCardSearch(cnId,cnNo,wildCardSearch,keyCode,department);            
            if (creditNote == null) {
                request.setAttribute("failStatus", true);
                request.setAttribute("failMessage", "Credit note no:" + creditNo + " not available !");
            } else {
                request.setAttribute("creditNote", creditNote);
                request.setAttribute("enableVoid", true);
                if(cnNoForCheckUser != null){
                    if(!"".equalsIgnoreCase(cnNoForCheckUser) && !"".equalsIgnoreCase(keyCode)){
                        CheckDuplicateUser cdu = new CheckDuplicateUser();
                        cdu.setOperationTable("Credit_Note");
                        cdu.setTableId(operationTableId);
                        cdu.setOperationUser(user.getUsername());
                        checkDuplicateUserService.updateOperationNull(cdu);
                    }
                }
                //Duplicate User
                if(creditNote != null){
                    checkDuplicateUser = checkDuplicateUser(request,response,session,creditNote.getId(),1);
                }
            }
                       
        }
//        CreditNote cn = (CreditNote) request.getAttribute("creditNote");
//        if (cn != null && cn.getId() != null && !"".equals(cn.getId())) {
//            if (user != null && user.getRole() != null && user.getRole().getId() != null
//                    && user.getRole().getId().equals("23")) {
//                request.setAttribute("disableVoid", true);
//            }
//        }

//                    session.setAttribute("USER", UserAuthen);
        List<MPaytype> dd = getUtilityService().getListMPayType();
        request.setAttribute("productTypeList", getUtilityService().getListMPayType());
        request.setAttribute("vat", getUtilityService().getMDefaultDataFromType("vat").getValue());
        request.setAttribute("page", callPageFrom);
        
        if((!"".equalsIgnoreCase(creditNo)) && (creditNo != null)){
            if("search".equalsIgnoreCase(action)){
                if((creditNo.indexOf("%") >= 0)){
                    request.setAttribute("wildCardSearch", creditNo);
                }else{
                    request.setAttribute("wildCardSearch", ""); 
                }
            }else if("118".equalsIgnoreCase(keyCode)){
                request.setAttribute("wildCardSearch", ""); 
            }else if("119".equalsIgnoreCase(keyCode)){
                request.setAttribute("wildCardSearch", ""); 
            }else{
                if((creditNo.indexOf("%") >= 0)){
                    request.setAttribute("wildCardSearch", creditNo);
                }else if((!"".equalsIgnoreCase(wildCardSearch)) && (wildCardSearch.indexOf("%") == 0)){
                    request.setAttribute("wildCardSearch", wildCardSearch);
                }else{
                    request.setAttribute("wildCardSearch", ""); 
                }
            }
        }
        
        return new ModelAndView(LINKNAME + callPageFrom);
    }

    /**
     * @return the creditNoteService
     */
    public CreditNoteService getCreditNoteService() {
        return creditNoteService;
    }

    /**
     * @param creditNoteService the creditNoteService to set
     */
    public void setCreditNoteService(CreditNoteService creditNoteService) {
        this.creditNoteService = creditNoteService;
    }

    /**
     * @return the utilityService
     */
    public UtilityService getUtilityService() {
        return utilityService;
    }

    /**
     * @param utilityService the utilityService to set
     */
    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    private CreditNote mapCreditNoteRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction uf = new UtilityFunction();
        CreditNote cn = null;
        try {
            cn = new CreditNote();
            String cnId = request.getParameter("cnId");
            String cnNo = request.getParameter("cnNo");
            String date = request.getParameter("inputDate");
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String remark = request.getParameter("remark");
            String apCode = request.getParameter("apCode");
            
            String operationUser = request.getParameter("operationUser");
            String operationDate = request.getParameter("operationDate");
            
            //Duplicate User
            if(!"".equalsIgnoreCase(cnId) && cnId != null){
                checkDuplicate = checkDuplicateUser(request,response,session,cnId,2);
                if("fail".equalsIgnoreCase(checkDuplicate)){
                    request.setAttribute("page", callPageFrom);
                    cn.setId(checkDuplicate);
                    cn.setCnNo(cnNo);
                    return cn;
                
                }else if("success".equalsIgnoreCase(checkDuplicate)){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                    cn.setOperationDate(uf.convertStringToDateTime(operationDate));
                    cn.setOperationUser(operationUser);
                }
            }

            cn.setId(cnId);
            cn.setCnNo(cnNo);
            cn.setCnDate(uf.convertStringToDate(date));
            cn.setCnName(name);
            cn.setCnAddress(address);
            cn.setCnRemark(remark);
            cn.setApCode(apCode);

            String[] id = request.getParameterValues("id");
            String[] taxId = request.getParameterValues("taxId");
            String[] taxNo = request.getParameterValues("taxNo");
            String[] taxDate = request.getParameterValues("taxDate");
            String[] taxType = request.getParameterValues("taxType");
            String[] taxAmount = request.getParameterValues("taxAmount");
            String[] taxAmountReal = request.getParameterValues("taxAmountReal");
            String[] taxReal = request.getParameterValues("taxReal");
            String[] taxVat = request.getParameterValues("taxVat");
            String[] taxVatReal = request.getParameterValues("taxVatReal");
            String[] taxDesc = request.getParameterValues("taxDesc");
            for (int i = 0; i < taxId.length; i++) {
                if (null != taxId && null != taxId[i] && !"".equals(taxId[i])) {
                    try {
                        CreditNoteDetail cnd = new CreditNoteDetail();
                        cnd.setId(id[i]);
                        TaxInvoice taxInv = new TaxInvoice();
                        taxInv.setId(taxId[i]);
                        cnd.setTaxInvoice(taxInv);
                        cnd.setCreditNote(cn);
//                        if (taxAmount[i] != null && !taxAmount[i].equals("")) {
//                            cnd.setAmount(new BigDecimal(uf.StringUtilReplaceChar(taxAmount[i])));
//                        }
                        if (taxAmountReal[i] != null && !taxAmountReal[i].equals("")) {
                            cnd.setAmount(new BigDecimal(uf.StringUtilReplaceChar(taxAmountReal[i])));
                        }
                        if (taxReal[i] != null && !taxReal[i].equals("")) {
                            cnd.setRealamount(new BigDecimal(uf.StringUtilReplaceChar(taxReal[i])));
                        }
//                        if (taxVat[i] != null && !taxVat[i].equals("")) {
//                            cnd.setVat(new BigDecimal(uf.StringUtilReplaceChar(taxVat[i])));
//                        }
                        if (taxVatReal[i] != null && !taxVatReal[i].equals("")) {
                            cnd.setVat(new BigDecimal(uf.StringUtilReplaceChar(taxVatReal[i])));
                        }
                        
                        cnd.setDescription(taxDesc[i]);
                        if (!"".equals(taxType[i])) {
                            MPaytype type = new MPaytype();
                            type.setId(taxType[i]);
                            cnd.setMPayType(type);
                        }
                        cn.getCreditNoteDetails().add(cnd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cn;
    }
    
    private String checkDuplicateUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, String cnId, int step) {
        UtilityFunction util = new UtilityFunction();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        String result = "fail";
        SystemUser user = (SystemUser) session.getAttribute("USER");
        CheckDuplicateUser chuSession = new CheckDuplicateUser();
        chuSession.setOperationTable("Credit_Note");
        chuSession.setTableId(cnId);
        if(step == 1){
            chuSession.setOperationDate(String.valueOf(df.format(new Date())));
            chuSession.setOperationUser(user.getUsername());
        }else if(step == 2){
            String operationDate = request.getParameter("operationDate");
            String operationUser = request.getParameter("operationUser");
            System.out.println("operationDate : "+operationDate);
            System.out.println("new Date : "+new Date());
            chuSession.setOperationDate((df.format(util.convertStringToDateTime(operationDate))));
            chuSession.setOperationUser(operationUser);
            System.out.println("chuSession.getOperationDate() : "+chuSession.getOperationDate());
        }else if(step == 3){
            chuSession.setOperationDate(String.valueOf(df.format(new Date())));
            chuSession.setOperationUser(user.getUsername());
        }    
        session.setAttribute("checkDuplicateUser", chuSession);
        CheckDuplicateUser cdu = checkDuplicateUserService.CheckAndUpdateOperationDetail(chuSession, step);
        request.setAttribute(CHECKDUPLICATEUSER, cdu);
        if(cdu.getIsDuplicateUser() == 0){
            result = "success";          
        }
        return result;
    }
    
    private String clearDuplicateUser(HttpServletRequest request, HttpServletResponse response,HttpSession session, String taxInvId){
        String result = "fail";
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy-MM-dd HH:mm:ss");
        SystemUser  user = (SystemUser) session.getAttribute("USER");
        CheckDuplicateUser chuSession = new CheckDuplicateUser();
        session.setAttribute("checkDuplicateUser", chuSession);
        CheckDuplicateUser chu = new CheckDuplicateUser();
        chu.setOperationTable("Credit_Note");
        chu.setTableId(taxInvId);
        chu.setOperationDate(String.valueOf(df.format(new Date())));
        chu.setOperationUser(user.getUsername());     
        int update = checkDuplicateUserService.updateOperationNull(chu);
        if(update == 1){
            result = "success";
        }
        return result;
    }

    public CheckDuplicateUserService getCheckDuplicateUserService() {
        return checkDuplicateUserService;
    }

    public void setCheckDuplicateUserService(CheckDuplicateUserService checkDuplicateUserService) {
        this.checkDuplicateUserService = checkDuplicateUserService;
    }
}
