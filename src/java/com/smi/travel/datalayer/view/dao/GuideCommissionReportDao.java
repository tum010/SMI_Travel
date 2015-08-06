/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.report.model.GuideCommissionInfo;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface GuideCommissionReportDao {
    public List getGuideComissionReport(String datefrom,String dateto,String username,String guideid);
    public GuideCommissionInfo getGuideCommissionInfoReport(String datefrom,String dateto,String username,String guideid);
}
