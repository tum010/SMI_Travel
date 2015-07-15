package com.smi.travel.masterdata.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.service.DefineVarService;
import com.smi.travel.master.controller.SMITravelController;

public class DefineVarController extends SMITravelController {
	private static final ModelAndView DefineVar = new ModelAndView("DefineVar");
	private static final ModelAndView DefineVar_REFRESH = new ModelAndView(new RedirectView("DefineVar.smi", true));

	private DefineVarService defineVarService;

	@Override
	protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		HashMap<String, String> form = new HashMap<String, String>();
		request.setAttribute("form", form);
		String action = request.getParameter("action");
		if ("Save".equals(action)) {
			String vat = request.getParameter("vat");
			String bankChart = request.getParameter("bankChart");
			String withholdingTax = request.getParameter("withholdingTax");
			List<MDefaultData> list = new ArrayList<MDefaultData>();
			MDefaultData vatm = new MDefaultData("2","vat",vat,"double");
			MDefaultData bankChartm = new MDefaultData("3","bank chart",bankChart,"double");
			MDefaultData withholdingTaxm = new MDefaultData("4","withholding tax",withholdingTax,"double");
			list.add(vatm);
			list.add(bankChartm);
			list.add(withholdingTaxm);
			form.put("vat", vatm.getValue());
			form.put("bankchart", bankChartm.getValue());
			form.put("withholdingtax", withholdingTaxm.getValue());
			if("1".equals(defineVarService.saveVariable(list))){
				//success
				request.setAttribute("result", "saved");
			}else{
				//fail
				request.setAttribute("result", "fail");
			}
			return DefineVar;
		} else {
			List<MDefaultData> list = defineVarService.getListDefaultData();
			for(MDefaultData data: list){
				form.put(data.getName().replaceAll(" ", ""), data.getValue());
			}
			return DefineVar;
		}
	}
	
	public  void setDefineVarService(final DefineVarService defineVarService) {
		this.defineVarService = defineVarService;
	}
}
