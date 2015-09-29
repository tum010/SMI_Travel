package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MPricecategory;
import com.smi.travel.datalayer.entity.MStockStatus;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.MStaffService;
import com.smi.travel.datalayer.service.StockService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.Date;
import java.util.LinkedList;
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
        UtilityFunction utility = new UtilityFunction();
        String action = request.getParameter("action");
            // Serach Product Stock
            List<Product> listProductStock =  stockService.getListStockProduct();
            request.setAttribute("ListProductStock", listProductStock);

            // Serach Staff
            List<SystemUser> listStaffStock = mStaffService.getListStaff();
            request.setAttribute("ListStaffStock", listStaffStock);

            // Search Item List
            List<MPricecategory> mPriceCategory = utilityService.getListMPricecategory();
            request.setAttribute("getType", mPriceCategory);
            
            request.setAttribute("thisdate", utility.convertDateToString(new Date()));
        // Save Stock
        if("save".equals(action)){
            Stock stock = new Stock();
            Product product = new Product();
            SystemUser staff = new SystemUser();
            String stockId = request.getParameter("InputStockId");
            String productId = request.getParameter("InputProductId");
            String productCode = request.getParameter("InputProduct");
            String productName = request.getParameter("InputProductName");
            String staffId = request.getParameter("InputStaffId");
            String staffCode = request.getParameter("InputStaff");
            String staffName = request.getParameter("InputStaffName");
            String  effectiveFrom = request.getParameter("InputEffectiveFromDate");
            String  effectiveTo = request.getParameter("InputInputEffectiveToDate");
            String  createDate = request.getParameter("InputStockDate");
            String description = request.getParameter("descriptionStock");
                if(!stockId.equals("")){
                    stock.setId(stockId);
                }
                if(productId != null){
                    product.setId(productId);
                }
                if(productCode != null){
                    product.setCode(productCode);
                }
                if(productName != null){
                    product.setName(productName);
                }
                if(staffId != null && (!"".equals(staffId))){
                     staff.setId(staffId);
                    if(staffCode != null && (!"".equals(staffId)) ){
                        staff.setUsername(staffCode);
                    }
                    if(staffName != null && (!"".equals(staffId))){
                         staff.setName(staffName);
                    } 
                    stock.setStaff(staff);
                }
                stock.setProduct(product);            
                stock.setCreateDate(utility.convertStringToDate(createDate));
                stock.setEffectiveFrom(utility.convertStringToDate(effectiveFrom));
                stock.setEffectiveTo(utility.convertStringToDate(effectiveTo));
                stock.setDescription(description);
                stock.setStockDetails(setStockDetails(request, stock));
                List<StockDetail> listStockDetail = setStockDetails(request, stock);
                String isSave = stockService.saveStock(stock);
                if(isSave.equals("success")){  // Save New Stock              
                    // Search Stock Id from Data Add
                    String  findStockId = stockService.getStockId(stock);
                    if("fail".equals(findStockId)){
                        isSave = "moreOne";
                    }else{
                        isSave = "success";
                        //  Search Stock and StockDetail from  stock id
                        Stock stockNew = new Stock();
                        List<StockDetail> listStockDetailNew =  new LinkedList<StockDetail>();
                        // Stock and StockDetail New
                        stockNew = stockService.getStock(findStockId);
                        listStockDetailNew = stockService.checkStockDetail(findStockId);
                        if(stockNew != null){
                            request.setAttribute("stockData", stockNew);
                        }else{
                            request.setAttribute("stockData", null);
                        }
                        if(listStockDetailNew != null){
                            request.setAttribute("listStockDetail", listStockDetailNew);
                        }else{
                            request.setAttribute("listStockDetail", null);
                        }
                        request.setAttribute("FromDate", effectiveFrom);
                        request.setAttribute("ToDate", effectiveTo);
                        request.setAttribute("CreateDate", createDate);
                        ModelAndView searchstock = new ModelAndView(new RedirectView("SearchStock.smi?InputId="+stockNew.getId()+"&action=searchStock&productId="+stockNew.getProduct().getId()+"&productCode="+stockNew.getProduct().getCode()+"&productName="+stockNew.getProduct().getName()+"&createDate="+stockNew.getCreateDate()+"&EffecttiveFrom="+stockNew.getEffectiveFrom()+"&EffectiveTo="+stockNew.getEffectiveTo()+"&result="+isSave, true));
                        return searchstock;
                    }
                }else if(isSave.equals("update success")){ // Save is Update
                    isSave = "success";
                    request.setAttribute("stockData", stock);
                    request.setAttribute("FromDate", effectiveFrom);
                    request.setAttribute("ToDate", effectiveTo);
                    request.setAttribute("CreateDate", createDate);
                    request.setAttribute("listStockDetail", listStockDetail);
                    ModelAndView searchstock = new ModelAndView(new RedirectView("SearchStock.smi?InputId="+stock.getId()+"&action=searchStock&productId="+stock.getProduct().getId()+"&productCode="+stock.getProduct().getCode()+"&productName="+stock.getProduct().getName()+"&createDate="+createDate+"&EffecttiveFrom="+effectiveFrom+"&EffectiveTo="+effectiveTo+"&result="+isSave, true));
                    return searchstock;
                }else{
                    isSave = "fail";
                }
                request.setAttribute("result", isSave);
        }else if("edit".equals(action)){
            String stockId = request.getParameter("InputStockId");
            Stock stockData = stockService.getStock(stockId);         
            List<StockDetail> listStockDetail = stockService.checkStockDetail(stockId);
            
            request.setAttribute("listStockDetail", listStockDetail);
            request.setAttribute("stockData", stockData);
            request.setAttribute("FromDate", stockData.getEffectiveFrom());
            request.setAttribute("ToDate", stockData.getEffectiveTo());
            request.setAttribute("CreateDate", stockData.getCreateDate());
        }else if("delete".equals(action)){
            String stockId = request.getParameter("InputStockId");
            String stockDetailId = request.getParameter("idStockDelete");
            String result = stockService.DeleteStockDetail(stockDetailId);
            
            if("success".equals(result)){
                Stock stockData = stockService.getStock(stockId);         
                List<StockDetail> listStockDetail = stockService.checkStockDetail(stockId);
                request.setAttribute("listStockDetail", listStockDetail);
                request.setAttribute("stockData", stockData);
                request.setAttribute("FromDate", stockData.getEffectiveFrom());
                request.setAttribute("ToDate", stockData.getEffectiveTo());
                request.setAttribute("CreateDate", stockData.getCreateDate());
            }          
        }
        
        return Stock;
    }
    
    private List<StockDetail> setStockDetails(HttpServletRequest request,Stock stock){
        List<StockDetail> listStockDetail = new LinkedList<StockDetail>();
        String stockDetailRows = request.getParameter("counterTable");
        UtilityFunction utility = new UtilityFunction();
        if (stockDetailRows == null) {
            return null;
        }
        int driverRows = Integer.parseInt(stockDetailRows);
        if (driverRows == 1) {
            return null;
        }
//        driverRows = driverRows - 1;
        for (int i = 1; i <= driverRows; i++) {
            StockDetail stockDetail = new StockDetail();
            MStockStatus mStockStatus = new MStockStatus();
            MPricecategory mPriceCategory = new MPricecategory();
            OtherBooking otherbooking = new OtherBooking();
            SystemUser staff = new SystemUser();
            
            String stockDetailId = request.getParameter("stockDetailId"+i);
            String codeItemList = request.getParameter("codeItemList"+i);
            String typeitem = request.getParameter("SeleteTypeItemList"+i);
            String pay = request.getParameter("payTemp"+i);
            String items = request.getParameter("itemTemp"+i);
            String otherBookingId = request.getParameter("otherBookingId"+i);
            String staffId = request.getParameter("staffId"+i);
            String pickupDate = request.getParameter("pickupDate"+i);
            Integer  payStatusItemListInt = utility.convertStringToInteger(pay);
//            mStockStatus.setName("NEW");
            mStockStatus.setId(items);
            mPriceCategory.setId(typeitem);
            if(!"".equals(stockDetailId)){
                stockDetail.setId(stockDetailId);
            }
            if(otherBookingId != null && !"".equals(otherBookingId)){
                otherbooking.setId(otherBookingId);
                stockDetail.setOtherBooking(otherbooking);
            }
            if(staffId != null && !"".equals(staffId)){
                staff.setId(staffId);
                stockDetail.setStaff(staff);
            }
            if(pickupDate != null && !"".equals(pickupDate)){
                Date date = utility.convertStringToDate(pickupDate);
                stockDetail.setPickupDate(date);
            }
            stockDetail.setCode(codeItemList);
            stockDetail.setPayStatus(payStatusItemListInt);
            stockDetail.setStock(stock);
            stockDetail.setTypeId(mPriceCategory);
            stockDetail.setMStockStatus(mStockStatus);
            if("".equals(codeItemList) || codeItemList == null){
                listStockDetail.remove(stockDetail);
            }else{
                listStockDetail.add(stockDetail);
            }
        }
        return  listStockDetail;
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