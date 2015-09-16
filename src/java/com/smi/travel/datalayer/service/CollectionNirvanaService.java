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
    
    public List<CollectionNirvana> SearchCollectionNirvanaFromFilter(String department,String type,String status,String from,String to,String invno){
        return collectionNirvanaDao.SearchCollectionNirvanaFromFilter(department, type, status, from, to, invno);
    }

    public CollectionNirvanaDao getCollectionNirvanaDao() {
        return collectionNirvanaDao;
    }

    public void setCollectionNirvanaDao(CollectionNirvanaDao collectionNirvanaDao) {
        this.collectionNirvanaDao = collectionNirvanaDao;
    }
    
}
