package com.smi.travel.masterdata.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smi.travel.datalayer.entity.AccountCode;
import com.smi.travel.datalayer.service.MAccountCodeService;
import com.smi.travel.master.controller.AbstractController;

@Controller
@RequestMapping("/MAccountCode.smi")
public class MAccountCodeController extends AbstractController {

	private static String DEFAULT_VIEW = "MAccountCode";

	private static final Map<String, String> accTypeList = new LinkedHashMap<String, String>();

	private static final String SEARCH_ACTION = "search";
	private static final String SAVE_ACTION = "save";
	private static final String DELETE_ACTION = "delete";
	private static final String inx = " ALSIE";

	static {
		accTypeList.put("", "");
		accTypeList.put("A", "ASSETS");
		accTypeList.put("L", "LIABILITIES");
		accTypeList.put("S", "SHARE HOLDER");
		accTypeList.put("I", "INCOMES");
		accTypeList.put("E", "EXPENSE");
	}

	@Autowired
	private MAccountCodeService mAccountCodeService;

	@ModelAttribute
	public void setup(Model model,HttpServletRequest request) {
		logger.debug("Model Attribute Process...");
		model.addAttribute("accTypeList", accTypeList);
		model.addAttribute("message", null);
		model.addAttribute("dataTable", new ArrayList<AccountCode>());
		
		Enumeration<String> e = request.getParameterNames();
	 while(e.hasMoreElements()){
		 String key = e.nextElement();
		 model.addAttribute(key, request.getParameter(key));
	 }
		
	}

	@RequestMapping
	public String initial(Model model, @RequestParam(value = "action", required = false) String action,
			HttpServletRequest request) {
		logger.debug("MAccountCodeController : initial request");

		if (SEARCH_ACTION.equals(action)) {
			String accCode = request.getParameter("accCode") == null ? "" : request.getParameter("accCode");
			String accType = request.getParameter("accType") == null ? "" : request.getParameter("accType");
			List<AccountCode> accountCodeList = mAccountCodeService.search(accCode, accType);
			model.addAttribute("dataTable", accountCodeList);
		} else if (SAVE_ACTION.equals(action)) {
			int result = 0;
			
			String id = request.getParameter("idModal");
			String accCode = request.getParameter("accCodeModal2");
			String accType="";
			if(StringUtils.isEmpty(id)){
				accType = request.getParameter("accTypeModal");
			}else{
				accType = request.getParameter("accTypeHidden");
			}
			
			accCode = inx.indexOf(accType)+accCode;
			String detail = request.getParameter("detailModal");
			AccountCode accountCode = new AccountCode(id, accCode, detail, accType);
			
			if (StringUtils.isEmpty(id)) {
				result = mAccountCodeService.save(accountCode);
			} else {
				result = mAccountCodeService.update(accountCode);
			}
			if (result == 1) {
				List<AccountCode> accountCodeList = mAccountCodeService.search(accCode,accType);
				model.addAttribute("dataTable", accountCodeList);
				model.addAttribute("accCode", accCode);
				model.addAttribute("accType", accType);
				
				if (accCode != null && accCode.length() > 0)
					model.addAttribute("accCode2", accCode.substring(1));
				else
					model.addAttribute("accCode2", "");
				
				Map<String, String> message = new HashMap<String, String>();
				message.put("type", "success");
				message.put("messageInfo", "Save Success!");
				model.addAttribute("message", message);
			} else {
				Map<String, String> message = new HashMap<String, String>();
				message.put("type", "failure");
				message.put("messageInfo", "Save Failure!");
				model.addAttribute("message", message);
			}

			
		} else if (DELETE_ACTION.equals(action)) {
			String id = request.getParameter("id");
			String accCode = request.getParameter("accCode");
			String accType = request.getParameter("accType");
			String detail = request.getParameter("detail");
			AccountCode accountCode = new AccountCode(id, accCode, detail, accType);
			logger.debug("Delete : id=" + id);
			int result = mAccountCodeService.delete(accountCode);
			if (result == 1) {
				Map<String, String> message = new HashMap<String, String>();
				message.put("type", "success");
				message.put("messageInfo", "Delete Success!");
				model.addAttribute("message", message);
			} else {
				Map<String, String> message = new HashMap<String, String>();
				message.put("type", "failure");
				message.put("messageInfo", "Delete failure!");
				model.addAttribute("message", message);
			}
		} else {
			model.addAttribute("dataTable", new ArrayList<AccountCode>());
		}
		return DEFAULT_VIEW;
	}

}
