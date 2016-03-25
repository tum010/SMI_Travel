/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.view.dao.CollectionNirvanaDao;
import com.smi.travel.datalayer.view.entity.CollectionNirvana;
import java.util.List;

/**
 *
 * @author Jittima
 */
public class CollectionNirvanaService {
    private CollectionNirvanaDao collectionNirvanaDao;
    
    public List<CollectionNirvana> getCollectionNirvanaFromFilter(String department,String type,String status,String from,String to,String invno,String printby){
        return collectionNirvanaDao.getCollectionNirvanaFromFilter(department, type, status, from, to, invno, printby);
    }
    
    public String UpdateStatusCollection(List<CollectionNirvana> collectionNirvanaList){
        return collectionNirvanaDao.UpdateStatusCollection(collectionNirvanaList);
    }
    
    public String MappingCollectionNirvana(List<CollectionNirvana> cnData){
        return collectionNirvanaDao.MappingCollectionNirvana(cnData);
    }
    
    public CollectionNirvanaDao getCollectionNirvanaDao() {
        return collectionNirvanaDao;
    }

    public void setCollectionNirvanaDao(CollectionNirvanaDao collectionNirvanaDao) {
        this.collectionNirvanaDao = collectionNirvanaDao;
    }
    
}
