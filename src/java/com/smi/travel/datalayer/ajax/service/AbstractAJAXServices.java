/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.ajax.service;

import java.util.List;
import java.util.Map;

   
public interface AbstractAJAXServices {
	public List load(Map map);
	public List loadMulti(Map map);
        public Object loadSingle(Map map);
}
