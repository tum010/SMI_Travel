/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MTicketTypeDao;
import com.smi.travel.datalayer.entity.MTicketType;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MTicketTypeService {

    private MTicketTypeDao mTicketTypeDao;

    public List<MTicketType> searchTicketType(MTicketType TicketType,int option) {
        return mTicketTypeDao.getListTicketType(TicketType,option);
    }

    public String validateTicketType(MTicketType VTicketType, String operation) {
        String validate = "";
        MTicketType TicketType = new MTicketType();
        TicketType.setCode(VTicketType.getCode());
        List<MTicketType> list = mTicketTypeDao.getListTicketType(TicketType,1);
        if (list != null) {
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(VTicketType.getId()))){
                    validate = "ticket type code already exist";
                }
            }else{
                 validate = "ticket type code already exist";
            }
            
        }
        TicketType.setName(VTicketType.getName());
        TicketType.setCode(null);
        list = mTicketTypeDao.getListTicketType(TicketType,1);
        if (list != null) {
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(VTicketType.getId()))){
                    validate = "ticket type name already exist";
                }
            }else{
                 validate = "ticket type name already exist";
            }
            
        }
        return validate;
    }

    public int insertTicketType(MTicketType TicketType) {
        return mTicketTypeDao.insertTicketType(TicketType);
    }

    public int UpdateTicketType(MTicketType TicketType) {
        return mTicketTypeDao.updateTicketType(TicketType);
    }

    public int DeleteTicketType(MTicketType TicketType) {
        return mTicketTypeDao.DeleteTicketType(TicketType);
    }

    public MTicketTypeDao getmTicketTypeDao() {
        return mTicketTypeDao;
    }

    public void setmTicketTypeDao(MTicketTypeDao mTicketTypeDao) {
        this.mTicketTypeDao = mTicketTypeDao;
    }

}
