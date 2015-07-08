package com.smi.travel.controller;
import com.smi.travel.datalayer.service.StockService;
import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchStockController extends SMITravelController {
    private StockService   stockService;
    private static final ModelAndView SearchStock = new ModelAndView("SearchStock");
    private static final ModelAndView SearchStock_REFRESH = new ModelAndView(new RedirectView("SearchStock.smi", true));
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        return SearchStock;
    }

    public StockService getStockService() {
        return stockService;
    }

    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }
    
    
}
