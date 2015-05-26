/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.view.entity.BillableView;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface BillableViewDao {
    public List<BillableView> getListBillableView(String refno);
    public List<BillableDesc> MappingObjectBillable(List<BillableView> ListBill);
}
