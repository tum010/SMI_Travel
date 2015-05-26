/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MCity;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MCityDao {
    public List<MCity> getListCity(MCity city,int option);
    public List<MCity> getListCity();
    public int insertCity(MCity city);
    public int updateCity(MCity city);
    public int DeleteCity(MCity city);
}
