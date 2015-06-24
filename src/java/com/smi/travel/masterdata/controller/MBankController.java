package com.smi.travel.masterdata.controller;
import com.smi.travel.controller.LoginController;
import com.smi.travel.datalayer.entity.MBank;
import com.smi.travel.datalayer.service.MBankService;
import com.smi.travel.master.controller.SMITravelController;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
/**
 *
 * @author chonnasith
 */
public class MBankController extends SMITravelController {
    
    private static final Logger log = Logger.getLogger(LoginController.class.getName());
    private static final ModelAndView Bank = new ModelAndView("MBank");
    private static final ModelAndView Bank_REFRESH = new ModelAndView(new RedirectView("MBank.smi", true));
    private static final String DataList = "Bank_List";
    private static final String BankSearch = "BankSearch";
    private static final String TransactionResult = "result";
    private MBankService mBankService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        String action = request.getParameter("action");
        String code = request.getParameter("BankCode");
        String name = request.getParameter("BankName");
        String branch = request.getParameter("BankBranch");
        String accountNo = request.getParameter("BankAccountNo");
        String accountType = request.getParameter("BankAccountType");
        String bankId = request.getParameter("BankId");
                
        System.out.println("MBankController - action  :" + action);
        
        int result = 0;
        List<MBank> listBanks = null;
        
        MBank bank = new MBank();
        bank.setCode((String.valueOf(code)).toUpperCase());
        bank.setName((String.valueOf(name)).toUpperCase());
        bank.setBranch((String.valueOf(branch)).toUpperCase());
        bank.setAccNo((String.valueOf(accountNo)).toUpperCase());
          
        if ("search".equalsIgnoreCase(action)) {
            log.info("Bank searching...");
            if(accountType != ""){
                bank.setAccType(Integer.parseInt(accountType));
            }
            request.setAttribute(BankSearch, bank);
            listBanks = mBankService.getListBank(bank, 2);
            request.setAttribute(DataList, listBanks);

        } else if ("add".equalsIgnoreCase(action)) {
            log.info("Bank add function");
            MBank validateBank = new MBank();
            validateBank.setCode((String.valueOf(code)).toUpperCase());
            validateBank.setAccNo((String.valueOf(accountNo)).toUpperCase());
            String validateList = mBankService.validateBank(validateBank, "add");
            if ("".equalsIgnoreCase(validateList)) {
                if(accountType != ""){
                    bank.setAccType(Integer.parseInt(accountType));
                }
                result = mBankService.insertBank(bank);
                if (result == 1) {
                    request.setAttribute(TransactionResult, "save successful");
                    listBanks = mBankService.getListBank(bank, result);
                    System.out.println("Search in add " + listBanks.size());
                    request.setAttribute(DataList, listBanks);
                } else {
                    request.setAttribute(TransactionResult, "save unsuccessful");
                }
            } else {
                request.setAttribute(TransactionResult, "already used");
            }
            
        } else if ("update".equalsIgnoreCase(action)) {
            log.info("Bank update function");
            MBank validateBank = new MBank();
            validateBank.setCode(code);
            validateBank.setAccNo(accountNo);
            System.out.println("bank id :" + bankId);
            String validateList = mBankService.validateBank(validateBank, "update");
            if (!"".equalsIgnoreCase(validateList)) {
                request.setAttribute(TransactionResult, validateList);
            } else {
                bank.setId(bankId);
                bank.setAccType(Integer.parseInt(accountType));
                result = mBankService.updateBank(bank);
                if (result == 1) {
                    request.setAttribute(TransactionResult, "save successful");
                    request.setAttribute(DataList, mBankService.getListBank(bank, 1));
                } else {
                    request.setAttribute(TransactionResult, "save unsuccessful");
                }
            }
            
        } else if ("delete".equalsIgnoreCase(action)) {
            System.out.println("bankid : "+ bank.getId());
            bank.setId(bankId);
            result = mBankService.DeleteBank(bank);
            if (result == 1) {
                request.setAttribute(TransactionResult, "delete successful");
            } else {
                request.setAttribute(TransactionResult, "delete unsuccessful");
            }
        } 
        
        request.setAttribute("bankCode", code);
        request.setAttribute("bankName", name);
        request.setAttribute("bankBranch", branch);
        request.setAttribute("bankAccountNo", accountNo);
        request.setAttribute("bankAccountType", accountType);
        
        return Bank;
    }

    public MBankService getmBankService() {
        return mBankService;
    }

    public void setmBankService(MBankService mBankService) {
        this.mBankService = mBankService;
    }
    
}
