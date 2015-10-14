/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.StockDao;
import com.smi.travel.datalayer.entity.MStockStatus;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.view.entity.StockView;
import com.smi.travel.datalayer.view.entity.StockViewSummary;
import com.smi.travel.util.UtilityFunction;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 *
 * @author Surachai
 */
public class StockImpl implements StockDao{
    
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String DELETEALL_STOCK_QUERY ="DELETE FROM Stock st where st.id = :stockID";
    private static final String DELETE_STOCKDETAIL_QUERY ="DELETE FROM StockDetail std where std.id = :stockDetailID";
    private static final String SELECT_STOCK_DETAIL = "FROM StockDetail std where std.stock.id = :stockID ORDER BY  std.typeId.name,std.code  ASC";
    private static final String SELECT_STOCK_PRODUCT = "FROM Product pr where pr.isStock = 1";
    private static final String GET_STOCK_ID = "FROM Stock st where st.product.id = :proID and st.staff.username = :staffID and st.effectiveFrom = :from  and st.effectiveTo = :to and st.createDate = :create ";
    private static final String GET_STOCK = "FROM Stock st where st.id = :stockID ";
    private static final String SEARCH_STOCKDETAIL = "FROM StockDetail std where std.id = :stockDetailID";
    
    @Override
    public String InsertStock(Stock ItemLot) {
        String result = "";
        Session session = this.sessionFactory.openSession();
        try { 
            transaction = session.beginTransaction();
            session.save(ItemLot);
            List<StockDetail> stockDetail =ItemLot.getStockDetails();
            if(stockDetail != null){
                for (int i = 0; i < stockDetail.size(); i++) {
                    session.save(stockDetail.get(i));
                }
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            session.close();
            this.sessionFactory.close();
            result = "fail";
        }
        return result;
    }

    @Override
    public String UpdateStock(Stock ItemLot) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(ItemLot);
            List<StockDetail> stockDetail = ItemLot.getStockDetails();
            for (int i = 0; i < stockDetail.size(); i++) {
                if (stockDetail.get(i).getId() == null) {
                    session.save(stockDetail.get(i));
                } else {
                    session.update(stockDetail.get(i));
                }
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "update success";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "update fail";
        }
        return result;
    }

    @Override
    public String DeleteStock(Stock ItemLot) {
        List<StockDetail> stockDetailList = new LinkedList<StockDetail>();
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            stockDetailList = checkStockDetail(ItemLot.getId());
            if(stockDetailList == null){
                Query query = session.createQuery(DELETEALL_STOCK_QUERY);
                query.setParameter("stockID", ItemLot.getId());
                System.out.println("row delete : "+query.executeUpdate());
                transaction.commit();
                session.close();
                this.sessionFactory.close();
                result = "success";
            }else{
                result = "fail";
            }          
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }

    @Override
    public String DeleteStockDetail(String DetailID) {
        String result = "";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            String textResult = getStockDetail(DetailID);
            if("pass".equals(textResult)){
                Query query = session.createQuery(DELETE_STOCKDETAIL_QUERY);
                query.setParameter("stockDetailID", DetailID);
                System.out.println("row delete : "+query.executeUpdate());
                transaction.commit();
                session.close();
                this.sessionFactory.close();
                result = "success";
            }else{
                result = "fail";
            }
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            result = "fail";
        }
        return result;
    }
    
    public String getStockDetail(String stockdetailId){
        String result = "";
        Session session = this.sessionFactory.openSession();
        List<StockDetail> stockDetailList = session.createQuery(SEARCH_STOCKDETAIL)
                .setParameter("stockDetailID", stockdetailId)
                .list();
        if (stockDetailList.isEmpty()) {
            return null;
        }else{
            if(!"1".equals(stockDetailList.get(0).getMStockStatus().getId())){
                result = "fail";
            }else{
                result = "pass";
            }
        }
        System.out.println("Result Check Status :" + result);
        return result;
    }

    @Override
    public StockViewSummary SearchStockFromFilter(String productId,String payStatus,String itemStatus,Date createDate,Date EffecttiveFrom,Date EffectiveTo) {
        StockViewSummary stockViewSummary = new StockViewSummary();
        StockView stockView = new StockView();
        Session session = this.sessionFactory.openSession();
        String query = "FROM StockDetail  std where" ;
        
        if ( productId != null && (!"".equalsIgnoreCase(productId)) ) {
            query += " std.stock.product.id = " + productId;
        }
        if (payStatus != null && (!"".equalsIgnoreCase(payStatus)) ) {
            query += " and st.payStatus = " + payStatus;
        }
     
        if (itemStatus != null && (!"".equalsIgnoreCase(itemStatus)) ) {
            query += " and st.MStockStatus.id = '" + itemStatus + "'";
        }
       
        if (createDate != null ) {
            query += " and st.stock.createDate = '" + createDate + "'";
        }
        
        if (EffecttiveFrom != null ) {
            query += " and st.stock.effectiveFrom = '" + EffecttiveFrom + "'";
        }
        
        if (EffectiveTo != null ) {
            query += " and st.stock.effectiveTo = '" + EffectiveTo + "'";
        }
        
        System.out.println("query : " + query);
        List<StockDetail> list = session.createQuery(query).list();
        // Set Value In StockViewSummary >>>>>>>>>> Map
        session.close();
        this.sessionFactory.close();
        return stockViewSummary;
    }
    
   private StockViewSummary mappingStockViewSummary(List<StockDetail> stockDataList){
        StockViewSummary stockview = new StockViewSummary();
        UtilityFunction util = new UtilityFunction();
        if(stockDataList == null){
            return null;
        }else if(stockDataList.isEmpty()){
            return null;
        }
        Stock stockData = stockDataList.get(0).getStock();
        int SumNormal = 0;
        int SumCancel = 0;
        int SumInuse =0;
        for(int i=0;i<stockDataList.size();i++){
            MStockStatus status =  stockDataList.get(i).getMStockStatus();
            if("1".equalsIgnoreCase(status.getId())){
                SumNormal += 1;
            }else if("2".equalsIgnoreCase(status.getId())){
                SumInuse += 1;
            }else if("3".equalsIgnoreCase(status.getId())){
                SumCancel +=1;
            }
            System.out.println("Code Stock Detail : " + stockDataList.get(i).getCode());
        }
        stockview.setId(stockData.getId());
        stockview.setAdddate(util.convertDateToString(stockData.getCreateDate()));
        stockview.setEffectiveDateFrom(util.convertDateToString(stockData.getEffectiveFrom()));
        stockview.setEffectiveDateTo(util.convertDateToString(stockData.getEffectiveTo()));
        stockview.setNormal(SumNormal);
        stockview.setNumOfItem(stockData.getStockDetails().size());
        stockview.setProductName(stockData.getProduct().getName());
        if(stockData.getStaff() != null){
            stockview.setStaff(stockData.getStaff().getUsername());
        }
        
        stockview.setCancel(SumCancel);
        stockview.setInuse(SumInuse);
        stockview.setItemList(mappingStockView(stockDataList));
        List<StockDetail> listStockDetail = stockDataList;
        for(int i =0;i< listStockDetail.size();i++){
            
            System.out.println("Code TTTT : " + listStockDetail.get(i).getCode());
        }
        return stockview;
    }
    
    private List<StockView> mappingStockView(List<StockDetail> Listdetail){
        
        List<StockView> StockList =new LinkedList<StockView>();
        
        UtilityFunction util = new UtilityFunction();
        for(int i =0;i<Listdetail.size();i++){
            StockView stock = new StockView();
            StockDetail detail = Listdetail.get(i);
            stock.setCode(detail.getCode());
            if(detail.getOtherBooking() != null){
                stock.setRefNo(detail.getOtherBooking().getMaster().getReferenceNo()); 
            }
            if(detail.getStaff() != null){
                stock.setPickup(detail.getStaff().getUsername());
            }
            
            if(detail.getTypeId() != null){
                System.out.println("type : "+detail.getTypeId().getId());
                stock.setTypeId(detail.getTypeId().getId());
                stock.setTypeName(detail.getTypeId().getName());
            }
            stock.setItemStatus(detail.getMStockStatus().getName());          
            stock.setPickupDate(util.convertDateToString(detail.getPickupDate()));
            stock.setPayStatusName(String.valueOf(detail.getPayStatus()));
            StockList.add(stock);
            System.out.println("Code Stock View Detail : " + Listdetail.get(i).getCode());
        }
        
        
        return StockList;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<StockDetail> checkStockDetail(String stockId) {
        Session session = this.sessionFactory.openSession();
        List<StockDetail> stockDetailList = session.createQuery(SELECT_STOCK_DETAIL)
                .setParameter("stockID", stockId)
                .list();
        if (stockDetailList.isEmpty()) {
            return null;
        }
        for(int i=0;i<stockDetailList.size();i++){
           System.out.println("stockDetailList : "+stockDetailList.get(i).getTypeId().getName());
        }
        
       
        return stockDetailList;
    }

    @Override
    public List<Product> getListStockProduct() {
        Session session = this.sessionFactory.openSession();
        List<Product> productList = session.createQuery(SELECT_STOCK_PRODUCT)
                .list();
        if (productList.isEmpty()) {
            return null;
        }

        return productList;
    }

    @Override
    public String getStockId(Stock stock) {
        UtilityFunction utility = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        String stockId = "";       
        String query = "FROM Stock st where" ;
        
        if ( stock.getProduct().getId() != null && (!"".equalsIgnoreCase(stock.getProduct().getId())) ) {
            query += " st.product.id = " + stock.getProduct().getId();
        }
        
        if (stock.getStaff() != null  ) {
            query += " and st.staff.username = '" + stock.getStaff().getUsername() + "'";
        }
       
        if (stock.getCreateDate() != null ) {          
            query += " and st.createDate = '" + utility.convertDateToString(stock.getCreateDate()) + "'";
        }
        
        if (stock.getEffectiveFrom() != null ) {
            query += " and st.effectiveFrom = '" + utility.convertDateToString(stock.getEffectiveFrom()) + "'";
        }
        
        if (stock.getEffectiveTo() != null ) {
            query += " and st.effectiveTo = '" + utility.convertDateToString(stock.getEffectiveTo()) + "'";
        }
        
        System.out.println("query : " + query);
        List<Stock> stockNew = session.createQuery(query).list();

       if (!stockNew.isEmpty()) {
           if(stockNew.size() > 1){
               stockId = "fail";
           }else{
               stockId = stockNew.get(0).getId();
           }
        }

        return stockId;
       
    }

    @Override
    public Stock getStock(String stockId) {
        Session session = this.sessionFactory.openSession();
        Stock stock = new Stock();
        List<Stock> stockList = session.createQuery(GET_STOCK)
                .setParameter("stockID", stockId)
                .list();
        if (!stockList.isEmpty()) {
            stock.setId(stockList.get(0).getId());
            stock.setProduct(stockList.get(0).getProduct());
            if(stockList.get(0).getStaff() != null){
                stock.setStaff(stockList.get(0).getStaff());
            }           
            stock.setEffectiveFrom(stockList.get(0).getEffectiveFrom());
            stock.setEffectiveTo(stockList.get(0).getEffectiveTo());
            stock.setCreateDate(stockList.get(0).getCreateDate());
            stock.setDescription(stockList.get(0).getDescription());
            stock.setStockDetails(stockList.get(0).getStockDetails());
        }
        return stock;
    }

    @Override
    public List<Stock> searchStock(String productId, String createDate, String EffecttiveFrom, String EffectiveTo,String expire) {
        Session session = this.sessionFactory.openSession();
          System.out.println("From : " + EffecttiveFrom);
        System.out.println("To : " + EffectiveTo);
        System.out.println("Create : " + createDate);
        System.out.println("Ex : " + expire);
        UtilityFunction utility = new UtilityFunction();
        String query = "";
        int AndQuery = 0;
        if("".equals(productId) && "".equals(createDate) && "".equals(EffecttiveFrom) &&  "".equals(EffectiveTo) ){
            query = "FROM Stock st " ;
        }else{
            query = "FROM Stock st where" ;
        }
        
        if ( productId != null && (!"".equalsIgnoreCase(productId)) ) {
             AndQuery = 1;
            query += " st.product.id = " + productId;
        }
       
        if (createDate != null && (!"".equalsIgnoreCase(createDate)) ) {
            if(AndQuery == 1){
                query += " and st.createDate = '" + createDate + "'";
            }else{
               AndQuery = 1;
               query += " st.createDate = '" + createDate + "'";
            }
        }
        System.out.println("Add Date : " + createDate);
        
        if (EffecttiveFrom != null  && (!"".equalsIgnoreCase(EffecttiveFrom)) ) {
            if(AndQuery == 1){
                query += " and st.effectiveFrom = '" + EffecttiveFrom + "'";
            }else{
               AndQuery = 1;
               query += "  st.effectiveFrom = '" + EffecttiveFrom + "'";
            }
        }
        
        if (EffectiveTo != null && (!"".equalsIgnoreCase(EffectiveTo)) ) {
            if(AndQuery == 1){
                query += " and st.effectiveTo = '" + EffectiveTo + "'";
            }else{
               AndQuery = 1;
               query += "  st.effectiveTo = '" + EffectiveTo + "'";
            }  
        }
        
//        query += "ORDER BY  st.stockDetails.code  ASC";
        System.out.println("query : " + query);
        List<Stock> list = session.createQuery(query).list();
        for(int i=0;i< list.size();i++){
            System.out.println("Stock Id Show"+ i + " : " + list.get(i).getId() );
        }
        if("0".equals(expire)){
            List<Stock> listTemp = checkItemStatusStockDetail(list);
            if(listTemp == null || listTemp.size() == 0){
                listTemp =  list;
            }
        }
       
        return list;
    }
    
    private List<Stock> checkItemStatusStockDetail(List<Stock> listStock){
        Session session = this.sessionFactory.openSession();
        String query = "";
        List<Stock> stockList = new LinkedList<Stock>();
        if(listStock != null){
            for (int i=0;i< listStock.size();i++) {
                List<StockDetail> listStockDetail = listStock.get(i).getStockDetails();
                query = "FROM StockDetail std where std.stock.id = " + listStock.get(i).getId() + " and std.MStockStatus.id = 1 ";
                System.out.println("query : " + query);
                listStockDetail = session.createQuery(query).list();
                if(listStockDetail != null && !"".equals(listStockDetail) && listStockDetail.size() != 0){
                    stockList.add(listStock.get(i));
                    System.out.println("Stock Id "+ i + " : " + listStock.get(i).getId() );
                }
            }
        }
        return stockList;
    }

    @Override
    public StockViewSummary searchStockDetail(String productId, String payStatus,String itemStatus) {
        StockViewSummary stockview = new StockViewSummary();
        Session session = this.sessionFactory.openSession();
        String query = "";
        if(productId == null && payStatus == null && itemStatus == null){
            if("".equals(productId) && "".equals(payStatus) && "".equals(itemStatus)){
                query = "FROM StockDetail st" ;
            }else{
                query = "FROM StockDetail st where" ;
            }
        }else if("".equals(productId) && "".equals(payStatus) && "".equals(itemStatus)){
            query = "FROM StockDetail st" ;
        }else{
            query = "FROM StockDetail st where" ;
        }
            
        if ( productId != null && (!"".equalsIgnoreCase(productId)) ) {
            query += " st.stock.id = " + productId;
        }
       
        if (payStatus != null && (!"".equalsIgnoreCase(payStatus))) {
            query += " and st.payStatus = " + payStatus ;
        }
        
        if (itemStatus != null && (!"".equalsIgnoreCase(itemStatus))) {
            query += " and st.MStockStatus.id = " + itemStatus ;
        }
        
        query += "  ORDER BY  st.typeId.name,st.code  ASC";
        System.out.println("query view: " + query);
        List<StockDetail> list = session.createQuery(query).list();
        stockview = mappingStockViewSummary(list);
        session.close();
        this.sessionFactory.close();
        return stockview;
    }

    @Override
    public List<Stock> getStockById(String stockId) {
        Session session = this.sessionFactory.openSession();
        Stock stock = new Stock();
        List<Stock> stockList = session.createQuery(GET_STOCK)
                .setParameter("stockID", stockId)
                .list();
        System.out.println("Size Stock View : " + stockList.size());
        if (!stockList.isEmpty()) {
            return stockList;
        }else{
            return null;
        }
    }

}