package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MCity;
import com.smi.travel.datalayer.entity.PackageCity;
import com.smi.travel.datalayer.entity.PackageItinerary;
import com.smi.travel.datalayer.entity.PackagePrice;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.service.PackageTourService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MPackageDetailController extends SMITravelController {

    private static final ModelAndView MPackageDetail = new ModelAndView("MPackageDetail");
    private static final ModelAndView MPackage = new ModelAndView("MPackage");
    private static final ModelAndView MPackageDetail_REFRESH = new ModelAndView(new RedirectView("MPackageDetail.smi", true));
    private PackageTourService packageTourservice;
    private UtilityService utilityService;
    private static final String ITINERARYLIST = "itinerary_List";
    private static final String PRICELIST = "price_list";
    private static final String DATALIST = "package_list";
    private static final String TransectionResult = "result";
    private static final String DataLap = "packageLap";
    private static final String DisabledCode = "disabledcode";
    UtilityFunction util;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        util = new UtilityFunction();
        String action = request.getParameter("action");
        String code = util.StringUtilReplaceChar(request.getParameter("packagecode"));
        String name = util.StringUtilReplaceChar(request.getParameter("packagename"));
        String packageID = request.getParameter("packageid");
        String remark = util.StringUtilReplaceChar(request.getParameter("remark"));
        String detail = util.StringUtilReplaceChar(request.getParameter("detail"));
        String status = util.StringUtilReplaceChar(request.getParameter("status"));
        String countItinerary = request.getParameter("counterItinerary");
        String conutCity = request.getParameter("passengerCounter");
        String countPrice = request.getParameter("counterPrice");
        System.out.println("action  :" + action);
        String result = "";
        String resultValidate = "";
        String operation = "";
        PackageTour pack = new PackageTour();
        System.out.println("code : "+code);
        pack.setCode(code);
        pack.setName(name);
        pack.setRemark(remark);
        pack.setDetail(detail);
        pack.setStatus(status);
        if (StringUtils.isNotEmpty(packageID)) {
            pack.setId(packageID);
        }
        List<MCity> mCity = utilityService.getListMCity();
        List<PackageCity> packageCity = pack.getPackageCities();
        if ("save".equalsIgnoreCase(action)) {
            if ((packageID == null) || ("".equalsIgnoreCase(packageID))) {
                operation = "add";
            } else {
                operation = "update";

            }
            resultValidate = packageTourservice.validatePackage(pack, operation);
            System.out.println("resultValidate  :" + resultValidate);
            System.out.println("operation  :" + operation);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                if (util.convertStringToInteger(countItinerary) != 0) {
                    setPackageItinerary(request, countItinerary, pack);
                }
                if (util.convertStringToInteger(countPrice) != 0) {
                    setPackagePrice(request, countPrice, pack);
                }
                // Package City
                if(util.convertStringToInteger(conutCity) != 0){
                    setPckageCity(request, conutCity, pack);
                }
                List<PackagePrice> paList = new ArrayList<PackagePrice>(pack.getPackagePrices());
                for (int i = 0; i < paList.size(); i++) {
                    System.out.println("cost : price " + paList.get(i).getAdCost() + " , " + paList.get(i).getAdPrice());
                }
                
                result = packageTourservice.SavePackage(pack);

                if (result.equalsIgnoreCase("success")) {
                    request.setAttribute(TransectionResult, "1");
                    PackageTour Packageresult = packageTourservice.SearchPackage(pack, 1).get(0);
                    code = Packageresult.getCode();
                    name = Packageresult.getName();
                    remark = Packageresult.getRemark();
                    detail = Packageresult.getDetail();
                    status = Packageresult.getStatus();
                    packageID = Packageresult.getId();
                    action = "edit";
                    request.setAttribute(DisabledCode, "readonly");
                    request.setAttribute(ITINERARYLIST, SortItineraryList(new ArrayList<PackageItinerary>(Packageresult.getPackageItineraries())));
                    request.setAttribute(PRICELIST, SortPriceList(new ArrayList<PackagePrice>(Packageresult.getPackagePrices())));
                    if (status.equalsIgnoreCase("inactive")) {
                        request.setAttribute("IsInactive", "selected");
                    }
                    request.setAttribute("packagecode", code);
                    request.setAttribute("packagename", name);
                    request.setAttribute("remark", remark);
                    request.setAttribute("detail", detail);
                    request.setAttribute("status", status);
                    request.setAttribute("packageid", packageID);
                    return new ModelAndView("redirect:MPackageDetail.smi?packageid="+packageID+"&action=edit&result=1");
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }
        } else if ("edit".equalsIgnoreCase(action)) {
            String packagetourid = request.getParameter("packageid");
            PackageTour Packagedetail = packageTourservice.getPackageFromID(packagetourid);
            code = Packagedetail.getCode();
            name = Packagedetail.getName();
            remark = Packagedetail.getRemark();
            detail = Packagedetail.getDetail();
            status = Packagedetail.getStatus();
            packageID = Packagedetail.getId();
            request.setAttribute(DisabledCode, "readonly");
            request.setAttribute(ITINERARYLIST, SortItineraryList(new ArrayList<PackageItinerary>(Packagedetail.getPackageItineraries())));
            // Package Price
            List<PackagePrice> paList = new ArrayList<PackagePrice>(Packagedetail.getPackagePrices());
            request.setAttribute(PRICELIST, SortPriceList(paList));
            // Package City 
            List<PackageCity> listPackageCity = Packagedetail.getPackageCities();
            request.setAttribute("ListPackageCity", listPackageCity);
            
            if (status.equalsIgnoreCase("inactive")) {
            }                request.setAttribute("IsInactive", "selected");

            if(request.getParameter(TransectionResult) != null){
                if("1".equalsIgnoreCase(request.getParameter(TransectionResult).toString())){
                    request.setAttribute(TransectionResult, "save successful");
                }else if("0".equalsIgnoreCase(request.getAttribute(TransectionResult).toString())){
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }                
            }

            
        } else if ("deleterItinerary".equalsIgnoreCase(action)) {
            System.out.println("Delete Itinerary");
            String ItiId = request.getParameter("ItiID");
            result = packageTourservice.DeletePackageItinerary(ItiId);
            System.out.println(result);

        } else if ("deleterPrice".equalsIgnoreCase(action)) {
            System.out.println("Delete Price");
            String priceId = request.getParameter("priceId");
            System.out.println("priceId : " + priceId);
            result = packageTourservice.DeletePackagePrice(priceId);
            System.out.println(result);
        }else if("deleteCity".equalsIgnoreCase(action)){
            System.out.println("Delete City");
            String cityId = request.getParameter("CityID");
            System.out.println("city id : " + cityId);
            result = packageTourservice.DeletePackageCity(cityId);
            System.out.println(result);
        }

        request.setAttribute("packagecode", code);
        request.setAttribute("packagename", name);
        request.setAttribute("remark", remark);
        request.setAttribute("detail", detail);
        request.setAttribute("status", status);
        request.setAttribute("packageid", packageID);
        request.setAttribute("ListCity", mCity);
        request.setAttribute("ListPackageCity", packageCity);
        return MPackageDetail;
    }
    
    public void setPackageItinerary(HttpServletRequest request, String ItineraryRows, PackageTour packagetour) {
        System.out.println("setItinerary Method");
        util = new UtilityFunction();
        int itineraryRows = Integer.parseInt(ItineraryRows);
        if (itineraryRows == 1) {
            return;
        }

        for (int i = 0; i < itineraryRows - 1; i++) {
            PackageItinerary Itinerary = new PackageItinerary();
            String id = request.getParameter("row-" + i + "-itineraryid");
            String no = request.getParameter("row-" + i + "-no");
            String time = request.getParameter("row-" + i + "-hour");
            String detail = request.getParameter("row-" + i + "-des");

            if ((no != null) && ((!"".equalsIgnoreCase(detail)) || (!"".equalsIgnoreCase(no)) || (!"".equalsIgnoreCase(time)))) {

                if (StringUtils.isNotEmpty(id)) {
                    Itinerary.setId(id);
                }
                if (StringUtils.isNotEmpty(no)) {
                    Itinerary.setOrderNo(util.convertStringToInteger(no));
                }
                if (StringUtils.isNotEmpty(time)) {
                    Itinerary.setTime(util.convertStringToTime(time));
                }
                if (StringUtils.isNotEmpty(detail)) {
                    Itinerary.setDetail(detail);
                }
                Itinerary.setPackageTour(packagetour);
                packagetour.getPackageItineraries().add(Itinerary);
            }

        }
    }

    public void setPackagePrice(HttpServletRequest request, String PriceRows, PackageTour packagetour) {

        util = new UtilityFunction();
        int priceRows = Integer.parseInt(PriceRows);
        if (priceRows == 1) {
            return;
        }
        for (int i = 0; i < priceRows - 1; i++) {
            PackagePrice price = new PackagePrice();
            String id = request.getParameter("row-" + i + "-priceid");
            String datefrom = request.getParameter("row-" + i + "-datefrom");
            String dateto = request.getParameter("row-" + i + "-dateto");
            String adcost = request.getParameter("row-" + i + "-adcost");
            String chcost = request.getParameter("row-" + i + "-chcost");
            String incost = request.getParameter("row-" + i + "-incost");
            String adprice = request.getParameter("row-" + i + "-adprice");
            String chprice = request.getParameter("row-" + i + "-chprice");
            String inprice = request.getParameter("row-" + i + "-inprice");

            if ((id != null) && ((!"".equalsIgnoreCase(datefrom)) || (!"".equalsIgnoreCase(dateto)) || (!"".equalsIgnoreCase(adcost)) || (!"".equalsIgnoreCase(chcost)) || (!"".equalsIgnoreCase(incost)) && (!"".equalsIgnoreCase(adprice)) || (!"".equalsIgnoreCase(chprice)) || (!"".equalsIgnoreCase(inprice)))) {
                if (StringUtils.isNotEmpty(id)) {
                    price.setId(id);
                }
                if (StringUtils.isNotEmpty(datefrom)) {
                    price.setEffectiveFrom(util.convertStringToDate(datefrom));
                }
                if (StringUtils.isNotEmpty(dateto)) {
                    price.setEffectiveTo(util.convertStringToDate(dateto));
                }
                if (StringUtils.isNotEmpty(adcost)) {
                    price.setAdCost(util.convertStringToInteger(adcost));
                }
                if (StringUtils.isNotEmpty(chcost)) {
                    price.setChCost(util.convertStringToInteger(chcost));
                }
                if (StringUtils.isNotEmpty(incost)) {
                    price.setInCost(util.convertStringToInteger(incost));
                }
                if (StringUtils.isNotEmpty(adprice)) {
                    price.setAdPrice(util.convertStringToInteger(adprice));
                }
                if (StringUtils.isNotEmpty(chprice)) {
                    price.setChPrice(util.convertStringToInteger(chprice));
                }
                if (StringUtils.isNotEmpty(inprice)) {
                    price.setInPrice(util.convertStringToInteger(inprice));
                }
                price.setPackageTour(packagetour);
                packagetour.getPackagePrices().add(price);
            }

        }
    }

    public void setPckageCity(HttpServletRequest request, String CityRows, PackageTour packagetour){
        util = new UtilityFunction();
        int cityRows = Integer.parseInt(CityRows);
        if(packagetour.getPackageCities() == null){
            packagetour.setPackageCities(new ArrayList<PackageCity>());
        }
        
        if (cityRows == 1) {
            return;
        }
        
        for (int i = 1; i < cityRows  ; i++) {
            PackageCity city = new PackageCity();
            MCity mCity = new MCity();
            String nameCity = request.getParameter("row-passenger-" + i + "-name");
            String idCity = request.getParameter("row-passenger-" + i + "-id");
            
            if(nameCity != null){
                mCity.setName(nameCity);
            }
            if(idCity != null){
                mCity.setId(idCity);
            }
            city.setMCity(mCity);
            city.setPackageTour(packagetour);
            packagetour.getPackageCities().add(city);
        }
    }
    
    public PackageTourService getPackageTourservice() {
        return packageTourservice;
    }

    public void setPackageTourservice(PackageTourService packageTourservice) {
        this.packageTourservice = packageTourservice;
    }
    
    public List<PackageItinerary> SortItineraryList(List<PackageItinerary> data) {
        List<PackageItinerary> sortItinerary = new ArrayList<PackageItinerary>();
        List Dataindex = new ArrayList();
        if(data == null){
            return data;
        }else if(data.size() == 0){
            return data;
        }
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getOrderNo() == null) {
                return data;
            }
        }
        for (int i = 0; i < data.size(); i++) {
            Dataindex.add(data.get(i).getOrderNo());
        }

        Collections.sort(Dataindex);
        for (int i = 0; i < Dataindex.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (Dataindex.get(i).equals(data.get(j).getOrderNo())) {
                   if(!CheckDupilate(sortItinerary,data.get(j))){
                        sortItinerary.add(data.get(j));
                   }
                    //sortItinerary.add(data.get(j)); 
                }
            }
        }

        return sortItinerary;
    }
    
   public boolean CheckDupilate(List<PackageItinerary> data , PackageItinerary newdata){
        for(int i=0;i<data.size();i++){
            PackageItinerary compare = data.get(i);
            if(compare.getId().equalsIgnoreCase(newdata.getId())){
                System.out.println("id : "+compare.getId() +" is dup");
                return true;
            } 
        }
        return false;
    }
    
    public List<PackagePrice> SortPriceList(List<PackagePrice> data) {
        List<PackagePrice> sortPrice = new ArrayList<PackagePrice>();
        List Dataindex = new ArrayList();
        if(data == null){
            return data;
        }else if(data.size() == 0){
            return data;
        }
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId()== null) {
                return data;
            }
        }
        for (int i = 0; i < data.size(); i++) {
            Dataindex.add(data.get(i).getId());
        }

        Collections.sort(Dataindex);
        for (int i = 0; i < Dataindex.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (Dataindex.get(i).equals(data.get(j).getId())) {
                    sortPrice.add(data.get(j));
                }
            }
        }

        return sortPrice;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }
}
