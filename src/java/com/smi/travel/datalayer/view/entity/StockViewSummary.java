/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class StockViewSummary {
    private String id;
    private String ProductName;
    private String Staff;
    private Date Adddate;
    private Date EffectiveDateFrom;
    private Date EffectiveDateTo;
    private int NumOfItem;
    private int Normal;
    private int Cancel;
    private int Bill;
    private int Inuse;
    private List<StockView> ItemList = new LinkedList<StockView>();
   
    
    public StockViewSummary(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getStaff() {
        return Staff;
    }

    public void setStaff(String Staff) {
        this.Staff = Staff;
    }

    public Date getAdddate() {
        return Adddate;
    }

    public void setAdddate(Date Adddate) {
        this.Adddate = Adddate;
    }

    public Date getEffectiveDateFrom() {
        return EffectiveDateFrom;
    }

    public void setEffectiveDateFrom(Date EffectiveDateFrom) {
        this.EffectiveDateFrom = EffectiveDateFrom;
    }

    public Date getEffectiveDateTo() {
        return EffectiveDateTo;
    }

    public void setEffectiveDateTo(Date EffectiveDateTo) {
        this.EffectiveDateTo = EffectiveDateTo;
    }

    public int getNumOfItem() {
        return NumOfItem;
    }

    public void setNumOfItem(int NumOfItem) {
        this.NumOfItem = NumOfItem;
    }

    public int getNormal() {
        return Normal;
    }

    public void setNormal(int Normal) {
        this.Normal = Normal;
    }

    public int getCancel() {
        return Cancel;
    }

    public void setCancel(int Cancel) {
        this.Cancel = Cancel;
    }

    public int getBill() {
        return Bill;
    }

    public void setBill(int Bill) {
        this.Bill = Bill;
    }

    public int getInuse() {
        return Inuse;
    }

    public void setInuse(int Inuse) {
        this.Inuse = Inuse;
    }

    public List<StockView> getItemList() {
        return ItemList;
    }

    public void setItemList(List<StockView> ItemList) {
        this.ItemList = ItemList;
    }
    
    

}
