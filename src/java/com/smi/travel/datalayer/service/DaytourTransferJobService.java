/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.TransferJobDao;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.entity.TransferJob;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class DaytourTransferJobService {
    private TransferJobDao  transferJobdao;
    
   
    public List<TransferJob> searchTransferJob(String StartDate , String EndDate,String Hotel){
         return transferJobdao.searchTransferJob(StartDate, EndDate,Hotel);
    }
    
    public String saveTransferjob(TransferJob Job){
        return transferJobdao.saveTransferjob(Job);
    }

    public TransferJobDao getTransferJobdao() {
        return transferJobdao;
    }

    public void setTransferJobdao(TransferJobDao transferJobdao) {
        this.transferJobdao = transferJobdao;
    }
    
    
}
