package com.smi.travel.masterdata.controller;
import com.smi.travel.datalayer.entity.MPricecategory;
import com.smi.travel.datalayer.service.MPriceCategoryService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class MPriceCategoryController extends SMITravelController {
    private static final ModelAndView MPriceCategory = new ModelAndView("MPricecategory");
    private static final String DataList = "price_List";
    private static final String DataLap = "priceLap";
    private static final String TransectionResult = "result";
    private MPriceCategoryService priceCategoryService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("PricecategoryCode");
        String name = request.getParameter("PricecategoryName");
        String PriceID = request.getParameter("PricecategoryID");
        System.out.println("action  :" + action);
        int result = 0;
        String resultValidate = "";
        MPricecategory price = new MPricecategory();
        price.setCode((String.valueOf(code)).toUpperCase());
        price.setName((String.valueOf(name)).toUpperCase());
        price.setId(PriceID);

        if ("search".equalsIgnoreCase(action)) {
            List<MPricecategory> list = priceCategoryService.searchPrice(price, 2);
            request.setAttribute(DataList, list);
        } else if ("add".equalsIgnoreCase(action)) {
            resultValidate = priceCategoryService.validatePrice(price, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = priceCategoryService.insertPrice(price);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, priceCategoryService.searchPrice(price, 1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }

            }
        } else if ("update".equalsIgnoreCase(action)) {
            resultValidate = priceCategoryService.validatePrice(price, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = priceCategoryService.UpdatePrice(price);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, priceCategoryService.searchPrice(price, 1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            result = priceCategoryService.DeletePrice(price);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        
        request.setAttribute("pricecategoryCode", code);
        request.setAttribute("pricecategoryName", name);
        
        return MPriceCategory;
    }

    public MPriceCategoryService getPriceCategoryService() {
        return priceCategoryService;
    }

    public void setPriceCategoryService(MPriceCategoryService priceCategoryService) {
        this.priceCategoryService = priceCategoryService;
    }
    
    
}
