package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.service.MStaffService;
import com.smi.travel.datalayer.service.StockService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.StockViewSummary;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class SearchStockController extends SMITravelController {
    private StockService stockService;
    private static final ModelAndView SearchStock = new ModelAndView("SearchStock");
    private static final ModelAndView SearchStock_REFRESH = new ModelAndView(new RedirectView("SearchStock.smi", true));
    private MStaffService mStaffService;
    private UtilityService utilityService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction utility = new UtilityFunction();
        String action = request.getParameter("action");
        // Attribute Search Stock
        String productId = request.getParameter("InputId");
        String productCode = request.getParameter("InputProductId");
        String productName = request.getParameter("InputProductName");
        String createDate = request.getParameter("InputStockDate");
        String EffecttiveFrom = request.getParameter("InputEffectiveFromDate");
        String EffectiveTo = request.getParameter("InputInputEffectiveToDate");
        Date create = utility.convertStringToDate(createDate);
        Date from = utility.convertStringToDate(EffecttiveFrom);
        Date to = utility.convertStringToDate(EffectiveTo);
            // Serach Product Stock
            List<Product> listProductStock =  stockService.getListStockProduct();
            request.setAttribute("ListProductStock", listProductStock);
        
        if("search".equalsIgnoreCase(action)){
            Stock stock = new Stock();
            Product product = new Product();
            if(productId != null){
                product.setId(productId);           
            }
            if(productCode != null){
                product.setCode(productCode);           
            }
            if(productName != null){
                product.setName(productName);           
            }
            stock.setProduct(product);
            if(createDate != null){
                stock.setCreateDate(create);
            }
            if(EffecttiveFrom != null){
                stock.setEffectiveFrom(from);
            }
            if(EffectiveTo != null){
                stock.setEffectiveTo(to);
            }
            List<Stock> listStock = stockService.searchStock(productId, create, from, to);
            if(listStock != null){
                request.setAttribute("stock", stock);
                request.setAttribute("listStock", listStock);
            }
        }else if("view".equalsIgnoreCase(action)){
            String stockId = request.getParameter("stockIdView");
            String status = request.getParameter("SelectPayStatus");
            StockViewSummary stockDataDetail = stockService.searchStockDetail(stockId, status);
            
            if(stockDataDetail != null){
                System.out.println("set summary");
                 request.setAttribute("stockSummary", stockDataDetail);
                 request.setAttribute("stockSumDetail", stockDataDetail.getItemList());
            }else{
                request.setAttribute("stockSummary", null);
            }
            // Data Before Search
            Stock stock = new Stock();
            Product product = new Product();
            if(productId != null){
                product.setId(productId);           
            }
            if(productCode != null){
                product.setCode(productCode);           
            }
            if(productName != null){
                product.setName(productName);           
            }
            stock.setProduct(product);
            if(createDate != null){
                stock.setCreateDate(create);
            }
            if(EffecttiveFrom != null){
                stock.setEffectiveFrom(from);
            }
            if(EffectiveTo != null){
                stock.setEffectiveTo(to);
            }
            List<Stock> listStock = stockService.searchStock(productId, create, from, to);
            if(listStock != null){
                request.setAttribute("stock", stock);
                request.setAttribute("listStock", listStock);
            }
            
        }else{
            request.setAttribute("listStock", null);
            request.setAttribute("stockSummary", null);
        }
            
        return SearchStock;
    }

    public StockService getStockService() {
        return stockService;
    }

    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setmStaffService(MStaffService mStaffService) {
        this.mStaffService = mStaffService;
    }

    public MStaffService getmStaffService() {
        return mStaffService;
    }
   
}