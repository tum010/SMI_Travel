/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MHost;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public interface MHostDao {
    public List<MHost> getListHost(MHost currency);
    public int insertHost(MHost currency);
    public int updateHost(MHost currency);
    public int DeleteHost(MHost currency);
}
