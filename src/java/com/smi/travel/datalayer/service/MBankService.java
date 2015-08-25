/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MBankDao;
import com.smi.travel.datalayer.entity.MBank;
import java.util.List;

/**
 *
 * @author Jittima
 */
public class MBankService {
    
    private MBankDao mbankdao;
    
    /**
     * @return the mbankdao
     */
    public MBankDao getMbankdao() {
        return mbankdao;
    }

    /**
     * @param mbankdao the mbankdao to set
     */
    public void setMbankdao(MBankDao mbankdao) {
        this.mbankdao = mbankdao;
    }
    
    public List<MBank> getListBank(MBank bank,int option){
        return mbankdao.getListBank(bank, option);
    }

    public List<MBank> getListBank(){
        return mbankdao.getListBank();
    }
    
    public int insertBank(MBank bank){
        return mbankdao.insertBank(bank);
    }
    
    public int updateBank(MBank bank){
        return mbankdao.updateBank(bank);
    }
    
    public int DeleteBank(MBank bank){
        return mbankdao.DeleteBank(bank);
    }
    
    public String validateBank(MBank bank, String operation) {
        String validate = "";
        MBank mbank = new MBank();
        mbank.setCode(bank.getCode());
        List<MBank> list = mbankdao.getListBank(mbank,1);
        if (list != null) {
            if ("update".equalsIgnoreCase(operation)) {
                if (!(list.get(0).getId().equalsIgnoreCase(bank.getId()))) {
                    validate = "bank code already exist";
                }
            } else {
                validate = "bank code already exist";
            }
        }
        mbank.setAccNo(bank.getAccNo());
        mbank.setCode(null);
        list = mbankdao.getListBank(mbank, 1);
        if (list != null) {
            if ("update".equalsIgnoreCase(operation)) {
                if (!(list.get(0).getId().equalsIgnoreCase(bank.getId()))) {
                    validate = "account no already exist";
                }
            } else {
                validate = "bank code already exist";
            }
        }
        return validate;
    }
}
