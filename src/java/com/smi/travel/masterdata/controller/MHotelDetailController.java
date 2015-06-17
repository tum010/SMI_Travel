package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.Hotel;
import com.smi.travel.datalayer.entity.MCity;
import com.smi.travel.datalayer.entity.MCountry;
import com.smi.travel.datalayer.service.HotelService;
import com.smi.travel.datalayer.service.MCityService;
import com.smi.travel.datalayer.service.MCountryService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MHotelDetailController extends SMITravelController {

    private static final ModelAndView MHotelDetail = new ModelAndView("MHotelDetail");
    private static final ModelAndView MHotel = new ModelAndView("MHotel");
    private HotelService hotelservice;
    private MCityService cityservice;
    private MCountryService countryservice;
    private static final String CITYLIST = "city_list";
    private static final String COUNTRYLIST = "country_list";
    private static final String DATALIST = "hotel_list";
    private static final String TransectionResult = "result";
    private static final String DataLap = "hotelLap";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        String action = request.getParameter("action");
        String code = request.getParameter("hotelcode");
        String name = request.getParameter("hotelname");
        String cityid = request.getParameter("city");
        String countryid = request.getParameter("country");
        String Telno = request.getParameter("Telno");
        String Fax = request.getParameter("Fax");
        String Email = request.getParameter("Email");
        String Web = request.getParameter("Web");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String HotelID = request.getParameter("HotelID");
        String tempHotel = request.getParameter("temphotelcode");
        String operation ="";
        System.out.println("action  :" + action);
        System.out.println("code : "+code);
        if((code == null)||("null".equalsIgnoreCase(code))){
            System.out.println("temp : "+tempHotel);
            code = tempHotel;
        }
        int result = 0;
        String resultValidate = "";
        MCity city = new MCity();
        city.setId(cityid);
        MCountry country = new MCountry();
        country.setId(countryid);
        Hotel hotel = new Hotel();
        hotel.setCode((String.valueOf(code)).toUpperCase());
        hotel.setName((String.valueOf(name)).toUpperCase());
        if(!"0".equalsIgnoreCase(cityid)){
            hotel.setMCity(city);
        }
        if(!"0".equalsIgnoreCase(countryid)){
            hotel.setMCountry(country);
        }
        request.setAttribute("disableHotelCode", "");
        hotel.setTelNo(Telno);
        hotel.setFax(Fax);
        hotel.setEmail(Email);
        hotel.setWeb(Web);
        hotel.setRemark(description);
        hotel.setAddress((String.valueOf(address)).toUpperCase());
        hotel.setId(HotelID);
        System.out.println("HotelID  :" + HotelID);
        if ("save".equalsIgnoreCase(action)) {
            if((HotelID == null)||("".equalsIgnoreCase(HotelID))){
                operation = "add";
            }else{
                operation = "update";
            }
            resultValidate = hotelservice.validateHotel(hotel, operation);
            System.out.println("resultValidate  :" + resultValidate);
            System.out.println("operation  :" + operation);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                if("add".equalsIgnoreCase(operation)){
                     result = hotelservice.insertHotel(hotel);
                }else if("update".equalsIgnoreCase(operation)){
                     result = hotelservice.updateHotel(hotel);
                }
               
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DATALIST, hotelservice.getListHotel(hotel, 1));
                    return MHotel;
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        }else if("edit".equalsIgnoreCase(action)){
            Hotel hoteldetail = hotelservice.getHotelFromID(request.getParameter("hotelid").toString());     
            code = (String.valueOf(hoteldetail.getCode())).toUpperCase();
            name = (String.valueOf(hoteldetail.getName())).toUpperCase(); 
            if(hoteldetail.getMCity() != null){
                cityid = hoteldetail.getMCity().getId();
            }
            if(hoteldetail.getMCountry() != null){
                countryid = hoteldetail.getMCountry().getId();
            }
            System.out.println("cityid : "+cityid);
            System.out.println("countryid : "+countryid);
            request.setAttribute("disableHotelCode", "readonly");
            Telno = hoteldetail.getTelNo();
            Fax = hoteldetail.getFax();
            Email = hoteldetail.getEmail();
            Web = hoteldetail.getWeb();
            description = hoteldetail.getRemark();
            address = (String.valueOf(hoteldetail.getAddress())).toUpperCase();
            HotelID = hoteldetail.getId();
            
        }
        request.setAttribute(CITYLIST, cityservice.getlistCity());
        request.setAttribute(COUNTRYLIST, countryservice.getListCountry());
        
        request.setAttribute("hotelcode", code );
        request.setAttribute("hotelname", name );
        request.setAttribute("city", cityid );
        request.setAttribute("country", countryid );
        request.setAttribute("Telno", Telno );
        request.setAttribute("Fax", Fax );
        request.setAttribute("Email", Email );
        request.setAttribute("Web", Web );
        request.setAttribute("description", description );
        request.setAttribute("address", address );
        request.setAttribute("HotelID", HotelID );
         
        return MHotelDetail;
    }

    public HotelService getHotelservice() {
        return hotelservice;
    }

    public void setHotelservice(HotelService hotelservice) {
        this.hotelservice = hotelservice;
    }

    public MCityService getCityservice() {
        return cityservice;
    }

    public void setCityservice(MCityService cityservice) {
        this.cityservice = cityservice;
    }

    public MCountryService getCountryservice() {
        return countryservice;
    }

    public void setCountryservice(MCountryService countryservice) {
        this.countryservice = countryservice;
    }

}
