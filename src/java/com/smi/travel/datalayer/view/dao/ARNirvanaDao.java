/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.datalayer.view.entity.NirvanaInterface;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface ARNirvanaDao {
    public List<ARNirvana> SearchArNirvanaFromFilter(String invtype,String department,String billtype,String from,String to,String status,String accno);
    public String ExportARFileInterface(List<ARNirvana> APList,String pathfile);
    public String UpdateStatusARInterface(List<NirvanaInterface> nirvanaInterfaceList);
    public String MappingARNirvana(List<ARNirvana> arNirvanaData);
}
