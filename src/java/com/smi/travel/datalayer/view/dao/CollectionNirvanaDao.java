/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.view.entity.CollectionNirvana;
import com.smi.travel.datalayer.view.entity.NirvanaInterface;
import java.util.List;

/**
 *
 * @author Jittima
 */
public interface CollectionNirvanaDao {
    public List<CollectionNirvana> getCollectionNirvanaFromFilter(String department,String type,String status,String from,String to,String invno,String printby);
    public String UpdateStatusCollection(List<NirvanaInterface> nirvanaInterfaceList);
    public String MappingCollectionNirvana(List<CollectionNirvana> cnData);
}
