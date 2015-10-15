/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.AgentDao;
import com.smi.travel.datalayer.dao.AirticketBookingDao;
import com.smi.travel.datalayer.dao.BookingHistoryDao;
import com.smi.travel.datalayer.dao.CustomerDao;
import com.smi.travel.datalayer.dao.DaytourDao;
import com.smi.travel.datalayer.dao.DefineVarDao;
import com.smi.travel.datalayer.dao.MBankDao;
import com.smi.travel.datalayer.dao.MCityDao;
import com.smi.travel.datalayer.dao.MListItemDao;
import com.smi.travel.datalayer.dao.MasterDao;
import com.smi.travel.datalayer.dao.PlaceDao;
import com.smi.travel.datalayer.dao.SystemUserDao;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.HistoryBooking;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MAirline;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.MBank;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MBookingstatus;
import com.smi.travel.datalayer.entity.MCity;
import com.smi.travel.datalayer.entity.MCreditBank;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;
import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MMeal;
import com.smi.travel.datalayer.entity.MPaymentDoctype;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.MPricecategory;
import com.smi.travel.datalayer.entity.MStockStatus;
import com.smi.travel.datalayer.entity.MTicketType;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.view.dao.CustomerAgentInfoDao;
import com.smi.travel.datalayer.view.dao.InvoiceSuppilerDao;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Surachai
 */
public class UtilityService {

    private MasterDao masterdao;
    private MListItemDao listitemdao;
    private AirticketBookingDao airticketdao;
    private CustomerDao customerdao;
    private AgentDao agentdao;
    private SystemUserDao systemUserdao;
    private PlaceDao placedao;
    private DaytourDao daytourdao;
    private MCityDao citydao;
    private MBankDao mbankdao;
    private CustomerAgentInfoDao customeragentinfodao;
    private InvoiceSuppilerDao invoicesuppilerdao;
    private DefineVarDao defineVardao;
    private BookingHistoryDao bookinghistorydao;
    
    public int[] getCountItemFromBooking(String refno) {
        int[] Booking_size = new int[7];
        try {
            Master data = masterdao.getBookingFromRefno(refno);
            Booking_size[0] = airticketdao.getNumberOfFlight(refno);
            if(data != null){
                if(data.getHotelBookings() != null){
                    System.out.println(" data.getHotelBookings().size() " + data.getHotelBookings().size());
                    Booking_size[1] = data.getHotelBookings().size();
                }
                if(data.getOtherBookings() != null){
                    System.out.println(" data.getOtherBookings().size() " + data.getOtherBookings().size());
                    Booking_size[2] = data.getOtherBookings().size();
                }
                if(data.getLandBookings() != null){
                    System.out.println(" data.getLandBookings().size() " + data.getLandBookings().size());
                    Booking_size[3] = data.getLandBookings().size();
                }
                if(data.getPassengers() != null){
                    System.out.println(" data.getPassengers().size() " + data.getPassengers().size());
                    Booking_size[4] = data.getPassengers().size();
                }
                if(data.getBillables() != null){
                    System.out.println(" data.getBillables().size() " + data.getBillables().size());
                    Booking_size[5] = data.getBillables().size();
                }
                if(data.getDaytourBookings() != null){
                    System.out.println(" data.getDaytourBookings().size() " + data.getDaytourBookings().size());
                    Booking_size[6] = data.getDaytourBookings().size();
                }
            }
//            Booking_size[1] = data.getHotelBookings().size();
//            Booking_size[2] = data.getOtherBookings().size();
//            Booking_size[3] = data.getLandBookings().size();
//            Booking_size[4] = data.getPassengers().size();
//            Booking_size[5] = data.getBillables().size();
//            Booking_size[6] = data.getDaytourBookings().size();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Booking_size;
    }

    public Master getbookingFromRefno(String refno) {
        return masterdao.getBookingFromRefno(refno);
    }

    public List<MItemstatus> getListMItemstatus() {
        return listitemdao.getListMItemstatus();
    }

    public MItemstatus getMItemstatusFromName(String name) {
        return listitemdao.getMItemstatusFromName(name);
    }

    public List<MCurrency> getListMCurrency() {
        return listitemdao.getListMCurrency();
    }

    public List<MBookingstatus> getListMBookingstatus() {
        return listitemdao.getListMBookingstatus();
    }

    public MBookingstatus getMBookingstatusFromName(String name) {
        return listitemdao.getMBookingstatusFromName(name);
    }

    public List<MInitialname> getListMInitialname() {
        return listitemdao.getListMInitialname();
    }

    public MInitialname getMInitialnameFromId(String id) {
        return listitemdao.getMInitialnameFromId(id);
    }
    
    public MDefaultData getMDefaultDataFromType(String name){   
        List<MDefaultData> data = defineVardao.getListDefaultData();
        for(int i=0;i<data.size();i++){
            if(data.get(i).getName().equalsIgnoreCase(name)){
                return data.get(i);
            }
        }
        return null; 
    }
    
    public List<MCity> getListMCity(){
        return citydao.getListCity();
    }
    
    public List<MBank> getListBank(MBank bank){
        return mbankdao.getListBank(bank, 1);
    }

    public List<MBank> getListBank(){
        return mbankdao.getListBank();
    }
    
    public MInitialname getMInitialnameFromName(String name) {
        return listitemdao.getMInitialnameFromName(name);
    }

    public List<MPricecategory> getListMPricecategory() {
        return listitemdao.getListMPricecategory();
    }

    public MPricecategory getMPricecategoryFromCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        return listitemdao.getMPricecategoryFromCode(code);
    }

    public List<MFlight> getListMFlightClass() {
        return listitemdao.getListMFlightClass();
    }

    public List<MTicketType> getListMTicketType() {
        return listitemdao.getListMTicketType();
    }

    public List<MMeal> getListMMeal() {
        return listitemdao.getListMMeal();
    }

    public MMeal getMMealFromName(String name) {
        return listitemdao.getMMealFromName(name);
    }

    public List<Customer> getListCustomer() {
        return customerdao.getListCustomer();
    }

    public List<Agent> getListAgent() {
        return agentdao.getListAgent();
    }
    
    public  List<Daytour> getListDaytour(){
        return daytourdao.getTourListActive();
    }
    
    public List<MAccpay> getListMAccpay() {
        return listitemdao.getListMAccpay();
    }

    public List<MAccterm> getListMAccterm() {
        return listitemdao.getListMAccterm();
    }

    public MBilltype getMBilltypeFromName(String name) {
        return listitemdao.getMBilltypeFromName(name);
    }
    
    public List<MBilltype> getListMBilltype() {
        return listitemdao.getListMBilltype();
    }
    
    public List<MPaymentDoctype> getListMpaymentDocType(String department){
        return listitemdao.getListMpaymentDocType(department);
    }
    
    public List<MAirlineAgent> getListMAirLineAgent() {
        return listitemdao.getListMAirLineAgent();
    }
    
    public List<MAirline> getListMAirlineCode() {
        return listitemdao.getListMAirlineCode();
    }
    
    public List<SystemUser> getGuildeList() {
        return systemUserdao.getGuildeList();
    }
    
    public List<SystemUser> getDriverList() {
        return systemUserdao.getDriverList();
    }
    
    public List<MStockStatus> getListStockStatus(){
        return listitemdao.getListMStockStatus();
    }
    
    public List<Place> getPickupList(){
        return placedao.getListPlaceFromStatus("active");
    }
    
    public List<MPaytype> getListMPayType() {
        return listitemdao.getListMPayType();
    }
    
    public List<CustomerAgentInfo> getListCustomerAgentInfo() {
        return customeragentinfodao.getListCustomerAgentInfo();
    }
    
    public List<InvoiceSupplier> getListInvoiceSuppiler() {
        return invoicesuppilerdao.getListInvoiceSupplier();
    }
    
    public InvoiceSupplier getDataInvoiceSuppiler(String InputInvoiceSupCode) {
        return invoicesuppilerdao.getDataInvoiceSuppiler(InputInvoiceSupCode);
    }
    
    public List<SystemUser> getUserList() {
       return systemUserdao.getUserList();
    }
     
    public List<MCreditBank> getListCreditBank(){
        return listitemdao.getListCreditBank();
    }
    
    public List<MFinanceItemstatus> getListMFinanceItemstatus(){
        return listitemdao.getListMFinanceItemstatus();
    }
    
    public List<HistoryBooking> getHistoryFromRefno(String refno){
        return bookinghistorydao.getHistoryFromRefno(refno);
    }
    
    public int insertHistoryBooking(HistoryBooking historyBooking){
        return bookinghistorydao.insertHistoryBooking(historyBooking);
    }
    
    public int UpdateHistoryBooking(HistoryBooking historyBooking){
        return bookinghistorydao.UpdateHistoryBooking(historyBooking);
    }

    public MasterDao getMasterdao() {
        return masterdao;
    }

    public void setMasterdao(MasterDao masterdao) {
        this.masterdao = masterdao;
    }

    public MListItemDao getListitemdao() {
        return listitemdao;
    }

    public void setListitemdao(MListItemDao listitemdao) {
        this.listitemdao = listitemdao;
    }
    

    public AirticketBookingDao getAirticketdao() {
        return airticketdao;
    }

    public void setAirticketdao(AirticketBookingDao airticketdao) {
        this.airticketdao = airticketdao;
    }

    public CustomerDao getCustomerdao() {
        return customerdao;
    }

    public void setCustomerdao(CustomerDao customerdao) {
        this.customerdao = customerdao;
    }

    public AgentDao getAgentdao() {
        return agentdao;
    }

    public void setAgentdao(AgentDao agentdao) {
        this.agentdao = agentdao;
    }

    public SystemUserDao getSystemUserdao() {
        return systemUserdao;
    }

    public void setSystemUserdao(SystemUserDao systemUserdao) {
        this.systemUserdao = systemUserdao;
    }

    public PlaceDao getPlacedao() {
        return placedao;
    }

    public void setPlacedao(PlaceDao placedao) {
        this.placedao = placedao;
    }

    public DaytourDao getDaytourdao() {
        return daytourdao;
    }

    public void setDaytourdao(DaytourDao daytourdao) {
        this.daytourdao = daytourdao;
    }

    public MCityDao getCitydao() {
        return citydao;
    }

    public void setCitydao(MCityDao citydao) {
        this.citydao = citydao;
    }

    public MBankDao getMbankdao() {
        return mbankdao;
    }

    public void setMbankdao(MBankDao mbankdao) {
        this.mbankdao = mbankdao;
    }

    public CustomerAgentInfoDao getCustomeragentinfodao() {
        return customeragentinfodao;
    }

    public void setCustomeragentinfodao(CustomerAgentInfoDao customeragentinfodao) {
        this.customeragentinfodao = customeragentinfodao;
    }

    public InvoiceSuppilerDao getInvoicesuppilerdao() {
        return invoicesuppilerdao;
    }

    public void setInvoicesuppilerdao(InvoiceSuppilerDao invoicesuppilerdao) {
        this.invoicesuppilerdao = invoicesuppilerdao;
    }

    public DefineVarDao getDefineVardao() {
        return defineVardao;
    }

    public void setDefineVardao(DefineVarDao defineVardao) {
        this.defineVardao = defineVardao;
    }
    
    public List<CustomerAgentInfo> SearchListCustomerAgentInfo(String name) {
        return customeragentinfodao.SearchListCustomerAgentInfo(name);
    }

    public BookingHistoryDao getBookinghistorydao() {
        return bookinghistorydao;
    }

    public void setBookinghistorydao(BookingHistoryDao bookinghistorydao) {
        this.bookinghistorydao = bookinghistorydao;
    }
    
    
    
}
