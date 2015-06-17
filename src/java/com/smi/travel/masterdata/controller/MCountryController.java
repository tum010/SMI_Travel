package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MCountry;
import com.smi.travel.datalayer.service.MCountryService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MCountryController extends SMITravelController {

    private static final ModelAndView MCountry = new ModelAndView("MCountry");
    private static final String DataList = "Country_List";
    private static final String DataLap = "countryLap";
    private static final String TransectionResult = "result";
    private MCountryService CountryService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("CountryCode");
        String name = request.getParameter("CountryName");
        String CountryID = request.getParameter("CountryID");
        int result = 0;
        System.out.println("action  :" + action);
        String resultValidate = "";
        MCountry country = new MCountry();
        country.setCode((String.valueOf(code)).toUpperCase());
        country.setName((String.valueOf(name)).toUpperCase());
        country.setId(CountryID);

        if ("search".equalsIgnoreCase(action)) {
            List<MCountry> list = CountryService.searchCountry(country,2);
            request.setAttribute(DataList, list);
        } else if ("add".equalsIgnoreCase(action)) {
            resultValidate = CountryService.validateCountry(country, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = CountryService.insertCountry(country);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, CountryService.searchCountry(country,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }
        } else if ("update".equalsIgnoreCase(action)) {
            resultValidate = CountryService.validateCountry(country, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = CountryService.UpdateCountry(country);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, CountryService.searchCountry(country,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            result = CountryService.DeleteCountry(country);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        
        request.setAttribute("countryCode", code);
        request.setAttribute("countryName", name);
        
        return MCountry;
    }

    public MCountryService getCountryService() {
        return CountryService;
    }

    public void setCountryService(MCountryService CountryService) {
        this.CountryService = CountryService;
    }

}
