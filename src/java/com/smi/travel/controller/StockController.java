package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MPricecategory;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.MStaffService;
import com.smi.travel.datalayer.service.StockService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class StockController extends SMITravelController {
    private static final ModelAndView Stock = new ModelAndView("Stock");
    private static final ModelAndView Stock_REFRESH = new ModelAndView(new RedirectView("Stock.smi", true));
    private StockService stockService;
    private MStaffService mStaffService;
    private UtilityService utilityService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        // Serach Product Stock
        List<Product> listProductStock =  stockService.getListStockProduct();
        request.setAttribute("ListProductStock", listProductStock);
        
        // Serach Staff
        List<SystemUser> listStaffStock = mStaffService.searchSystemUser(new SystemUser(), 1);
        request.setAttribute("ListStaffStock", listStaffStock);
        
        // Search Item List
        List<MPricecategory> mPriceCategory = utilityService.getListMPricecategory();
        request.setAttribute("getType", mPriceCategory);
        return Stock;
    }

    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    public StockService getStockService() {
        return stockService;
    }

    public void setmStaffService(MStaffService mStaffService) {
        this.mStaffService = mStaffService;
    }

    public MStaffService getmStaffService() {
        return mStaffService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }
    
}