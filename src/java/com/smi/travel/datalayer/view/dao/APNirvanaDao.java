/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.view.entity.APNirvana;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface APNirvanaDao {
    public String ExportAPFileInterface(List<APNirvana> APList,String pathfile);
    public String UpdateStatusAPInterface(List<APNirvana> APList);
    public List<APNirvana> SearchApNirvanaFromFilter(String paymentType,String producttype,String status,String from,String to);
    public List getApNirvanaReport(String paymentType, String producttype, String status, String from, String to, String printby);
}
