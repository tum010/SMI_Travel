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
public interface OutboundSummaryDao {
    public List getOutboundPackageSummaryReportList(String fromdate,String todate,String cityId, String packageId,String salebyId,String paybyId,String bankId,String statusId);
}
