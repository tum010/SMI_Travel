package com.smi.travel.datalayer.entity;
// Generated Jul 7, 2015 11:22:27 AM by Hibernate Tools 3.6.0



import java.util.LinkedList;
import java.util.List;



public class MStockStatus  {


     private String id;
     private String name;
     private List stockDetails = new LinkedList<StockDetail>();

    public MStockStatus() {
    }

	
    public MStockStatus(String name) {
        this.name = name;
    }
    public MStockStatus(String name, List stockDetails) {
       this.name = name;
       this.stockDetails = stockDetails;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public List getStockDetails() {
        return this.stockDetails;
    }
    
    public void setStockDetails(List stockDetails) {
        this.stockDetails = stockDetails;
    }




}


