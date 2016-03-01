package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MCity;
import com.smi.travel.datalayer.entity.MCountry;
import com.smi.travel.datalayer.service.MCityService;
import com.smi.travel.datalayer.service.MCountryService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MCityController extends SMITravelController {

    private static final ModelAndView MCity = new ModelAndView("MCity");
    private static final ModelAndView MCity_REFRESH = new ModelAndView(new RedirectView("MCity.smi", true));
    private static final String DataList = "City_List";
    private static final String COUNTRYLIST = "Country_List";
    private static final String DataLap = "cityLap";
    private static final String TransectionResult = "result";
    private MCityService CityService;
    private MCountryService countryservice;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("CityCode");
        String name = request.getParameter("CityName");
        String CityID = request.getParameter("CityID");
        String SelectCountry = request.getParameter("SelectCountry");
        System.out.println("action  :" + action);
        int result = 0;
        String resultValidate = "";
        MCity city = new MCity();
        city.setCode((String.valueOf(code)).toUpperCase());
        city.setName((String.valueOf(name)).toUpperCase());
        city.setId(CityID);
        MCountry mCountry = new MCountry();
        mCountry.setId(SelectCountry);
        city.setMCountry(mCountry);
        request.setAttribute(COUNTRYLIST, countryservice.getListCountry());
        
        if ("search".equalsIgnoreCase(action)) {
            List<MCity> list = CityService.searchCity(city,2);
            request.setAttribute(DataList, list);
        } else if ("add".equalsIgnoreCase(action)) {
            resultValidate = CityService.validateCity(city, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = CityService.insertCity(city);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, CityService.searchCity(city,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }

            }
        } else if ("update".equalsIgnoreCase(action)) {
            resultValidate = CityService.validateCity(city, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = CityService.UpdateCity(city);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, CityService.searchCity(city,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            result = CityService.DeleteCity(city);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        
        request.setAttribute("cityCode", code);
        request.setAttribute("cityName", name);
        
        return MCity;
    }

    public MCityService getCityService() {
        return CityService;
    }

    public void setCityService(MCityService CityService) {
        this.CityService = CityService;
    }

    public MCountryService getCountryservice() {
        return countryservice;
    }

    public void setCountryservice(MCountryService countryservice) {
        this.countryservice = countryservice;
    }

}
