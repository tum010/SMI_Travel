/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MCurrency;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MCurrencyDao {
    public List<MCurrency> getListCurrency(MCurrency currency,int option);
    public int insertCurrency(MCurrency currency);
    public int updateCurrency(MCurrency currency);
    public int DeleteCurrency(MCurrency currency);
}
