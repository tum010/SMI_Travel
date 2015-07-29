package com.smi.travel.master.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public abstract class AbstractController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

}
