package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MStockStatus;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.service.MStaffService;
import com.smi.travel.datalayer.service.StockService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.StockViewSummary;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
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
        String expire = request.getParameter("SelectExpire");
        Date create = utility.convertStringToDate(createDate);
        Date from = utility.convertStringToDate(EffecttiveFrom);
        Date to = utility.convertStringToDate(EffectiveTo);
        System.out.println("Date View Create : " + createDate + "  From : " + EffecttiveFrom +"  To : " + EffectiveTo +" : ");
            // Serach Product Stock
            List<Product> listProductStock =  stockService.getListStockProduct();
            request.setAttribute("ListProductStock", listProductStock);
            
            //List Item Status
            List<MStockStatus> listItemStatus = utilityService.getListStockStatus();
            request.setAttribute("ListItemStatus", listItemStatus);
            
        if("search".equalsIgnoreCase(action)){
            // Pay Status
            String pStatus = request.getParameter("SelectPayStatus");
            // Item Status
            String iStatus =  request.getParameter("SelectItemStatus");
            Stock stock = new Stock();
            Product product = new Product();
            
            if(productCode != null && !"".equals(productCode)){
                product.setCode(productCode); 
                if(productId != null){
                    product.setId(productId);           
                }
            }else{
                product.setId(null);
                productId = "";
            }
            if((productName != null) && (!"".equals(productName))){
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
            // Search Stock
            List<Stock> listStock = stockService.searchStock(productId, createDate, EffecttiveFrom, EffectiveTo,expire);
            if(listStock != null){
                request.setAttribute("stock", stock);
                request.setAttribute("listStock", listStock);
                request.setAttribute("createDate", createDate);
                request.setAttribute("EffecttiveFrom", EffecttiveFrom);
                request.setAttribute("EffectiveTo", EffectiveTo);
            }
            request.setAttribute("payStatus", pStatus);
            request.setAttribute("itemStatus", iStatus);
            request.setAttribute("expire", expire);
        }else if("view".equalsIgnoreCase(action)){
            // view Stock Detail After Search Stock
            String stockId = request.getParameter("stockIdView");
            String status = request.getParameter("SelectPayStatus");
            String itemStatus =  request.getParameter("SelectItemStatus");
            // Search Stock
            StockViewSummary stockDataDetail = stockService.searchStockDetail(stockId, status, itemStatus);
           
            if(stockDataDetail != null){
                 System.out.println("Stock View Summary : " + stockDataDetail.getId() + " " +stockDataDetail.getProductName() );
                    System.out.println("set summary");
                 request.setAttribute("stockSummary", stockDataDetail);
                 request.setAttribute("payStatus", status);
                 request.setAttribute("itemStatus", itemStatus);
                 request.setAttribute("stockSumDetail", stockDataDetail.getItemList());
            }else{
                request.setAttribute("stockSummary", null);
            }
            
            request.setAttribute("itemStatus", itemStatus);
            request.setAttribute("payStatus", status);
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
            // Search Stock Detail
            List<Stock> listStock = stockService.searchStock(productId, createDate, EffecttiveFrom, EffectiveTo,expire);
            System.out.println("List Stock View :" + listStock.size());
            if(listStock != null){
                request.setAttribute("stock", stock);
                request.setAttribute("listStock", listStock);
                request.setAttribute("expire", expire);
                request.setAttribute("createDate", createDate);
                request.setAttribute("EffecttiveFrom", EffecttiveFrom);
                request.setAttribute("EffectiveTo", EffectiveTo);
            }
            
        }else if("searchStock".equalsIgnoreCase(action)){
            String stockid = request.getParameter("InputId");
            String productid = request.getParameter("productId");
            String proCode = request.getParameter("productCode");
            String proName = request.getParameter("productName");
            String adddate = request.getParameter("createDate");
            String EffectFrom = request.getParameter("EffecttiveFrom");
            String EffectTo = request.getParameter("EffectiveTo");
            String result = request.getParameter("result");
            System.out.println("Date Search Stock :" + adddate);
            System.out.println("Stock id View : "+ stockid);
            List<Stock> listStock = stockService.getStockById(stockid);
            if(listStock != null){
                Stock stock = new Stock();
                Product product = new Product();
                product.setId(listStock.get(0).getProduct().getId());
                product.setCode(listStock.get(0).getProduct().getCode());
                product.setName(listStock.get(0).getProduct().getName());
                stock.setProduct(product);
                stock.setCreateDate(listStock.get(0).getCreateDate());
                stock.setEffectiveFrom(listStock.get(0).getEffectiveFrom());
                stock.setEffectiveTo(listStock.get(0).getEffectiveTo());
                
                // Stock Detail 
                StockViewSummary stockDataDetail = stockService.searchStockDetail(stockid, "", "");
                if(stockDataDetail != null){
                    System.out.println("Stock View Summary : " + stockDataDetail.getId() + " " +stockDataDetail.getProductName() );
                    System.out.println("set summary");
                    request.setAttribute("stockSummary", stockDataDetail);
                    request.setAttribute("stockSumDetail", stockDataDetail.getItemList());
                }else{
                    request.setAttribute("stockSummary", null);
                    request.setAttribute("stockSumDetail", null);
                }
                
                request.setAttribute("listStock", listStock);
                request.setAttribute("stock", stock);
                request.setAttribute("productid", productid);
                request.setAttribute("proCode", proCode);
                request.setAttribute("proName", proName);
                request.setAttribute("createDate", adddate);
                request.setAttribute("EffecttiveFrom", EffectFrom);
                request.setAttribute("EffectiveTo", EffectTo);
                request.setAttribute("result", result);
            }else{
                request.setAttribute("listStock", null);
            }
        
        }else{
            request.setAttribute("listStock", null);
            request.setAttribute("stockSummary", null);
        }           
        return SearchStock;
    }
    
//    private Date convertDate(Date stockDate){
//        UtilityFunction utility = new UtilityFunction();
////        String day = utility.convertDateToString(stockDate);
////        System.out.println("String date (day): " + day);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//	String date = sdf.format(stockDate);
//        System.out.println("String date : " + date);
//        Date dateNew = new Date();
//        dateNew = utility.convertStringToDate(date);
//        System.out.println("String dateNew : " + dateNew);
//        return dateNew;
//    }

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