/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MBank;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MBankDao {
    public List<MBank> getListBank(MBank bank,int option);
    public List<MBank> getListBank();
    public int insertBank(MBank bank);
    public int updateBank(MBank bank);
    public int DeleteBank(MBank bank);
}
