package com.smi.travel.masterdata.controller;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smi.travel.controller.form.APARCodeForm;
import com.smi.travel.datalayer.service.APARCodeService;
import com.smi.travel.datalayer.view.entity.APARCode;
import com.smi.travel.master.controller.AbstractController;


@Controller
@RequestMapping("/APARCode.smi")
public class APARCodeController extends AbstractController {
	
	@Autowired
	private APARCodeService aPARCodeService;
	
	
	private static String DEFAULT_VIEW = "APARCode";

	private static final String SEARCH_ACTION = "search";
	private static final String EDIT_ACTION = "edit";
	
	private static final Map<String, String> typeList = new LinkedHashMap<String, String>();
	
	static{
		typeList.put(null, null);
		typeList.put("Hotel", "Hotel");
		typeList.put("Agent", "Agent");
		typeList.put("Staff", "Staff");
		
	}
	
	@ModelAttribute
	public void setup(@ModelAttribute("form") APARCodeForm form, Model model) {
		logger.debug("Model Attr ....");	
		model.addAttribute("typeList", typeList);
	}
	
	
	@RequestMapping
	public String initial(@ModelAttribute("form") APARCodeForm form, Model model,@RequestParam(value="action",required=false) String action) {
		logger.debug(" APARCodeController : initial request");
		if(SEARCH_ACTION.equals(action)){
			List<APARCode> dataTable = aPARCodeService.search(form.getCode(), form.getName(), form.getType());
			if(dataTable==null){
				dataTable = new ArrayList<APARCode>();
			}
			form.setDataTable(dataTable);
		}else if(EDIT_ACTION.equals(action)){
			
			
		}
		return DEFAULT_VIEW;
	}

}
