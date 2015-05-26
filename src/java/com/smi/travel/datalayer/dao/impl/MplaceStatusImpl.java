/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.MplaceStatusDao;
import com.smi.travel.datalayer.entity.MPlacestatus;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Surachai
 */
public class MplaceStatusImpl extends HibernateDaoSupport implements MplaceStatusDao{

    @Override
    public List<MPlacestatus> getListStatus() {
        List<MPlacestatus> list = getHibernateTemplate().find("from MPlacestatus mp");
        if (list.isEmpty()){
            return null;
        }
        return list;
    }
    
}
