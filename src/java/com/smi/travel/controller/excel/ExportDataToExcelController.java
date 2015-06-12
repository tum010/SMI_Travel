/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller.excel;

import com.smi.travel.master.controller.SMITravelController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jittima
 */
public class ExportDataToExcelController  extends SMITravelController{
    /*
    protected ExportDataToExcelService exportDataService = new ExportDataToExcelService();

	@RequestMapping(value = "/DataList", method = RequestMethod.GET)
	public String getData(Model model) {
		List<ExportDataToExcel> dataList = exportDataService.getDataList();
		model.addAttribute("dataList", dataList);
		return "DataList";
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public ModelAndView getExcel() {
		List<ExportDataToExcel> dataList = exportDataService.getDataList();
		return new ModelAndView("DataListExcel", "dataList", dataList);
	}
     
 */
//	protected ModelAndView handleRequestInternal(HttpServletRequest request,
//		HttpServletResponse response) throws Exception {
// 
//		String output =
//			ServletRequestUtils.getStringParameter(request, "output");
// 
//		//dummy data
//		Map<String,String> revenueData = new HashMap<String,String>();
//		revenueData.put("Jan-2010", "$100,000,000");
//		revenueData.put("Feb-2010", "$110,000,000");
//		revenueData.put("Mar-2010", "$130,000,000");
//		revenueData.put("Apr-2010", "$140,000,000");
//		revenueData.put("May-2010", "$200,000,000");
// 
//		if(output ==null || "".equals(output)){
//			//return normal view
//			return new ModelAndView("RevenueSummary","revenueData",revenueData);
// 
//		}else if("EXCEL".equals(output.toUpperCase())){
//			//return excel view
//			return new ModelAndView("ExportDataToExcel","revenueData",revenueData);
// 
//		}else{
//			//return normal view
//			return new ModelAndView("RevenueSummary","revenueData",revenueData);
// 
//		}	
//	}

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String output =  request.getParameter("output");
                 
		//dummy data
		Map<String,String> revenueData = new HashMap<String,String>();
		revenueData.put("Jan-2010", "$100,000,000");
		revenueData.put("Feb-2010", "$110,000,000");
		revenueData.put("Mar-2010", "$130,000,000");
		revenueData.put("Apr-2010", "$140,000,000");
		revenueData.put("May-2010", "$200,000,000");
 

		return new ModelAndView("ExportDataToExcelView","revenueData",revenueData);

    }

}
