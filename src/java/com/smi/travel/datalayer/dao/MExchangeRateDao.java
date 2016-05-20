/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MExchangeRate;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public interface MExchangeRateDao {
    public List searchExchangeRate(String from, String to, String currency);
    public List searchExchangeRateById(String exchangedate, String currency);
    public List getDaliyExchangeRate(String currentdate, String currency);
    public String findExchangeDuplicate(String exchangedate, String currency,String id);
    public String insertExchange(MExchangeRate mExchangeRate);
    public String updateExchange(MExchangeRate mExchangeRate);
    public String deleteExchange(MExchangeRate mExchangeRate);
//    public List<MExchangeRate> searchExchangeRateToday(String todayDate, String currency);
    
}
