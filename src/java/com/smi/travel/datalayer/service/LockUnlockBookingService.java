/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MListItemDao;
import com.smi.travel.datalayer.dao.MasterDao;
import com.smi.travel.datalayer.entity.MBookingstatus;
import com.smi.travel.datalayer.entity.Master;
import java.util.List;

/**
 *
 * @author Jittima
 */
public class LockUnlockBookingService {
    private MasterDao masterdao;
    private MListItemDao listitemdao;
    
    public Master getbookingFromRefno(String refno) {
        return masterdao.getBookingFromRefno(refno);
    }
    
    public int[] getBookStatusFromRefno(String refno) {
        return masterdao.getBookStatusFromRefno(refno);
    }

    public int LockAndUnLockBooking(Master master) {
        return masterdao.LockAndUnLockBooking(master);
    }
    
    public List<MBookingstatus> getListMBookingstatus() {
        return listitemdao.getListMBookingstatus();
    }

    
    public MasterDao getMasterdao() {
        return masterdao;
    }

    public void setMasterdao(MasterDao masterdao) {
        this.masterdao = masterdao;
    }

    public MListItemDao getListitemdao() {
        return listitemdao;
    }

    public void setListitemdao(MListItemDao listitemdao) {
        this.listitemdao = listitemdao;
    }
    
}
