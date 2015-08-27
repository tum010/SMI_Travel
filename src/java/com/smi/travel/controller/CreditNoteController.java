package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.CreditNote;
import com.smi.travel.datalayer.entity.CreditNoteDetail;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.service.CreditNoteService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.util.List;
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

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction utilty = new UtilityFunction();
        request.setAttribute("enableVoid", false);
        request.setAttribute("disableVoid", false);
        System.out.println("request.getRequestURI() :" + request.getRequestURI());
        String callPageFrom = utilty.getAddressUrl(request.getRequestURI()).replaceAll(LINKNAME, "");//request.getParameter("type");
        String Department = "";
        if (callPageFrom != null) {
            Department = callPageFrom;
        }

        String action = request.getParameter("action");
        String creditNo = request.getParameter("cnNo");
        if ("search".equalsIgnoreCase(action)) {
            CreditNote creditNote = new CreditNote();
            creditNote = creditNoteService.getCreditNote(creditNo);
            if (creditNote == null) {
                request.setAttribute("failStatus", true);
                request.setAttribute("failMessage", "Credit note no:" + creditNo + " not available !");
            } else {
                request.setAttribute("creditNote", creditNote);
                request.setAttribute("enableVoid", true);
            }
        } else if ("save".equalsIgnoreCase(action)) {
            CreditNote cn = mapCreditNoteRequest(request);
            String result = creditNoteService.saveCreditNote(cn);
            if (!"fail".equals(result)) {
                CreditNote creditNote = creditNoteService.getCreditNote(result);
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
            String result = creditNoteService.UpdateFinanceStatusCreditNote(cnId, status);
            if ("success".equals(result)) {
                request.setAttribute("successStatus", true);
                request.setAttribute("successMessage", method + " Success!");
            } else {
                request.setAttribute("failStatus", true);
                request.setAttribute("failMessage", method + " fail!");
            }
            CreditNote creditNote = creditNoteService.getCreditNote(cnNo);
            request.setAttribute("creditNote", creditNote);
        }
        CreditNote cn = (CreditNote) request.getAttribute("creditNote");
        if (cn != null && cn.getId() != null && !"".equals(cn.getId())) {
            SystemUser user = (SystemUser) session.getAttribute("USER");
            if (user != null && user.getRole() != null && user.getRole().getId() != null
                    && user.getRole().getId().equals("23")) {
                request.setAttribute("enableVoid", true);
                request.setAttribute("disableVoid", true);
            }
        }

//                    session.setAttribute("USER", UserAuthen);
        List<MPaytype> dd = getUtilityService().getListMPayType();
        request.setAttribute("productTypeList", getUtilityService().getListMPayType());
        request.setAttribute("vat", getUtilityService().getMDefaultDataFromType("vat").getValue());
        request.setAttribute("page", callPageFrom);
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

    private CreditNote mapCreditNoteRequest(HttpServletRequest request) {
        UtilityFunction uf = new UtilityFunction();
        CreditNote cn = new CreditNote();
        String cnId = request.getParameter("cnId");
        String cnNo = request.getParameter("cnNo");
        String date = request.getParameter("inputDate");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String remark = request.getParameter("remark");

        cn.setId(cnId);
        cn.setCnNo(cnNo);
        cn.setCreateDate(uf.convertStringToDate(date));
        cn.setCnName(name);
        cn.setCnAddress(address);
        cn.setCnRemark(remark);

        String[] id = request.getParameterValues("id");
        String[] taxId = request.getParameterValues("taxId");
        String[] taxNo = request.getParameterValues("taxNo");
        String[] taxDate = request.getParameterValues("taxDate");
        String[] taxType = request.getParameterValues("taxType");
        String[] taxAmount = request.getParameterValues("taxAmount");
        String[] taxReal = request.getParameterValues("taxReal");
        String[] taxVat = request.getParameterValues("taxVat");
        String[] taxDesc = request.getParameterValues("taxDesc");

        for (int i = 0; i < taxId.length; i++) {
            if (null != taxId && null != taxId[i] && !"".equals(taxId[i])) {
                CreditNoteDetail cnd = new CreditNoteDetail();
                cnd.setId(id[i]);
                TaxInvoice taxInv = new TaxInvoice();
                taxInv.setId(taxId[i]);
                cnd.setTaxInvoice(taxInv);
                cnd.setCreditNote(cn);
                if (taxAmount[i] != null && !taxAmount[i].equals("")) {
                    cnd.setAmount(new BigDecimal(uf.StringUtilReplaceChar(taxAmount[i])));
                }
                if (taxReal[i] != null && !taxReal[i].equals("")) {
                    cnd.setRealAmount(new BigDecimal(uf.StringUtilReplaceChar(taxReal[i])));
                }
                if (taxVat[i] != null && !taxVat[i].equals("")) {
                    cnd.setVat(new BigDecimal(uf.StringUtilReplaceChar(taxVat[i])));
                }
                cnd.setDescription(taxDesc[i]);
                MPaytype type = new MPaytype();
                type.setId(taxType[i]);
                cnd.setMPayType(type);
                cn.getCreditNoteDetails().add(cnd);
            }
        }

        return cn;
    }
}
