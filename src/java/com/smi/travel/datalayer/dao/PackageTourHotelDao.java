/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao;

import java.util.List;

/**
 *
 * @author Kanokporn
 */
public interface PackageTourHotelDao {
    public List getHotelSummary(String from,String to ,String department);
    public List getHotelMonthly(String from,String to ,String department,String detail);
}
