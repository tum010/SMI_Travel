/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.report.model.PackageMonthlyReport;

/**
 *
 * @author Jittima
 */
public interface PackageMonthlyDao {
    public PackageMonthlyReport getPackageMonthlyReport(String datefrom,String dateto,String department,String detail,String user);
}
