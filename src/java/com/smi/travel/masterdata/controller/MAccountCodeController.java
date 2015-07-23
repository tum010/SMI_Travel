package com.smi.travel.masterdata.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smi.travel.controller.form.MAccountCodeForm;
import com.smi.travel.datalayer.service.MAccountCodeService;



@Controller
@RequestMapping("/MAccountCode.smi")
public class MAccountCodeController {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static String default_view = "MAccountCode";
	
	@Autowired
	private MAccountCodeService mAccountCodeService;

	@RequestMapping
	public String initial(@ModelAttribute("form") MAccountCodeForm form,Model model,HttpServletRequest request) {
		logger.debug("MAccountCodeController : initial request");
		
		
		
		return default_view;
	}
	/*
	@RequestMapping("MapCode.smi")
	public String mapcode(Model model){
		return default_view;
	}
	
	@RequestMapping("MarCode.smi")
	public String marCode(Model model){
		return default_view;	
	}*/
}
