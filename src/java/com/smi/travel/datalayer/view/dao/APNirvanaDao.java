/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.datalayer.view.entity.NirvanaInterface;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface APNirvanaDao {
    public String ExportAPFileInterface(List<APNirvana> APList,String pathfile);
    public String UpdateStatusAPInterface(List<NirvanaInterface> nirvanaInterfaceList);
    public List<APNirvana> SearchApNirvanaFromFilter(String paymentType,String producttype,String status,String from,String to, String accno);
    public List getApNirvanaReport(String paymentType, String producttype, String status, String from, String to, String printby);
    public String MappingAPNirvana(List<APNirvana> apNirvanaData);
}
