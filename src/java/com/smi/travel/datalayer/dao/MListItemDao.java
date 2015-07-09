/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Function;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MBookingstatus;
import com.smi.travel.datalayer.entity.MBranch;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDepartment;
import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MMeal;
import com.smi.travel.datalayer.entity.MPaymentDoctype;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.MPricecategory;
import com.smi.travel.datalayer.entity.MProductType;
import com.smi.travel.datalayer.entity.MStockStatus;
import com.smi.travel.datalayer.entity.MTicketType;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MListItemDao {
    public List<MAccpay> getListMAccpay();
    public List<MAccterm> getListMAccterm();
    public List<Function> getListFunction();
    public List<MProductType> getListMasterProductType();
    public List<MDepartment> getListDepartment();
    public List<MBranch> getListMBranch();
    public List<MBookingstatus> getListMBookingstatus();
    public MBookingstatus getMBookingstatusFromName(String name);
    public List<MItemstatus> getListMItemstatus();
    public MItemstatus getMItemstatusFromName(String name);
    public List<MCurrency> getListMCurrency();
    public List<MPricecategory> getListMPricecategory();
    public MPricecategory getMPricecategoryFromCode(String code);
    public List<MFlight> getListMFlightClass();
    public List<MTicketType> getListMTicketType();
    public List<MMeal> getListMMeal();
    public MMeal getMMealFromName(String name);
    public List<PackageTour> getListMPackage();
    public List<MInitialname> getListMInitialname();
    public MInitialname getMInitialnameFromId(String id);
    public MInitialname getMInitialnameFromName(String name);
    public MBilltype getMBilltypeFromName(String name);
    public List<MPaymentDoctype>  getListMpaymentDocType();
    
    
}
