/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import java.util.Date;

/**
 *
 * @author Jittima
 */
public class CheckDuplicateUser {
    
    private Date operationDate;
    private String operationUser;
    private String operationTable;
    private int isDuplicateUser; // 0 is not duplicate
    private String tableId;
    private int isSave; // 0 is can save
    
    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    public String getOperationTable() {
        return operationTable;
    }

    public void setOperationTable(String operationTable) {
        this.operationTable = operationTable;
    }

    public int getIsDuplicateUser() {
        return isDuplicateUser;
    }

    public void setIsDuplicateUser(int isDuplicateUser) {
        this.isDuplicateUser = isDuplicateUser;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public int getIsSave() {
        return isSave;
    }

    public void setIsSave(int isSave) {
        this.isSave = isSave;
    }


    
}
