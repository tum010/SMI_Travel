package com.smi.travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smi.travel.datalayer.service.impl.PratzServiceImpl;

@Controller
@RequestMapping("/Test")
public class PratzController {
	
	@Autowired
	private PratzServiceImpl pratzService;
	
	@RequestMapping("/Pratz.smi")
	public String initial(Model model){
		model.addAttribute("data-model", pratzService.getData());
		return "Pratz";
	}
	
	@RequestMapping("/Pratz2.smi")
	public String initial2(Model model){
		model.addAttribute("data-model", pratzService.getData());
		return "Pratz";
	}
	
}
