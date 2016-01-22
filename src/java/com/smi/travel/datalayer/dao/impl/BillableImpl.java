/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.BillableDao;
import com.smi.travel.datalayer.entity.AirticketAirline;
import com.smi.travel.datalayer.entity.AirticketDesc;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.Billable;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.HotelBooking;
import com.smi.travel.datalayer.entity.HotelRoom;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.LandBooking;
import com.smi.travel.datalayer.entity.LandItinerary;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.PaymentAirCredit;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.datalayer.entity.TaxInvoiceDetail;
import com.smi.travel.util.UtilityFunction;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class BillableImpl implements BillableDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final String BillQuery = "from Billable B where B.master.referenceNo =:refno and B.master.MBookingstatus.id != 3";
    private static final String QUERY_AIRTICKET = "from AirticketAirline AA where AA.id in ";
    private static final String QUERY_OTHERS = "from OtherBooking ot where ot.id = :refitemid";
    private static final String QUERY_LAND = "from LandBooking lb where lb.id =   :refitemid";
    private static final String QUERY_HOTEL = "from HotelBooking hb where hb.id = :refitemid";
    private static final String QUERY_DAYTOUR = "from DaytourBooking db where db.id = :refitemid";
    private static final String SEARCH_MBILL_NAME = "from MBilltype mb where mb.id =:typeId ";
    private static final String OtherBookingUpdate = "UPDATE OtherBooking other set  other.isBill = 1 "
            + "WHERE other.id = :Keyid";
    private static final String LandBookingUpdate = "UPDATE LandBooking land set  land.isBill = 1 "
            + "WHERE land.id = :Keyid";
    private static final String AirBookingUpdate = "UPDATE AirticketFlight air set  air.isBill = 1 "
            + "WHERE air.airticketAirline.id in ";
    private static final String SelectAirLineId = "FROM AirticketFlight F where F.airticketAirline.airticketPnr.airticketBooking.master.id = :Keyid  and F.isBill = 0";
//    private static final String HotelBookingUpdate = "UPDATE HotelBooking hotel set  hotel.isBill = 1 "
//            + "WHERE hotel.master.id = :masterid";
    private static final String HotelBookingUpdate = "UPDATE HotelBooking hotel set  hotel.isBill = 1 "
            + "WHERE hotel.id = :Keyid";
    private static final String AirBookingDescUpdate = "UPDATE AirticketDesc AD set  AD.isBill = 1 "
            + "WHERE AD.id = :Keyid ";
    private static final String SelectAirBookId = "FROM AirticketBooking AB where AB.master.id = :masterid )";
//    private static final String DaytourBookingUpdate = "UPDATE DaytourBooking DB set  DB.isBill = 1 "
//            + "WHERE DB.master.id = :masterid";
    private static final String DaytourBookingUpdate = "UPDATE DaytourBooking DB set  DB.isBill = 1 "
            + "WHERE DB.id = :Keyid";
    private static final String QUERY_AIRADDITIONAL = "from AirticketDesc aird where aird.id = :refitemid";
    UtilityFunction utility = new UtilityFunction();

    @Override
    public Billable getBillableBooking(String refno) {
        Session session = this.sessionFactory.openSession();
        List<Billable> list = session.createQuery(BillQuery).setParameter("refno", refno).list();

        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }
    
    @Override
    public Billable getBillableBookingForTaxInvoice(String refNo) {
        Session session = this.sessionFactory.openSession();
        List<Billable> billableList = session.createQuery(BillQuery).setParameter("refno", refNo).list();
        if (billableList.isEmpty()) {
            return null;
        }
        List<BillableDesc> billableDescList = new ArrayList<BillableDesc>();
        for(int i=0;i<billableList.size();i++){
            String queryInvDetail = "from InvoiceDetail invDetail where invDetail.billableDesc.id = :id ";
            List<BillableDesc> billableDescListTemp = new ArrayList<BillableDesc>();
            billableDescListTemp = billableList.get(i).getBillableDescs();
            for(int j=0;j<billableDescListTemp.size();j++){
                List<InvoiceDetail> invoiceDetailList = session.createQuery(queryInvDetail).setParameter("id", billableDescListTemp.get(j).getId()).list();
                if(!invoiceDetailList.isEmpty()){
                    for(int k=0;k<invoiceDetailList.size();k++){
                        BillableDesc billableDesc = new BillableDesc();
                        billableDesc = invoiceDetailList.get(k).getBillableDesc();
                        billableDescList.add(billableDesc);
                    }                  
                }
            }                 
        }
        List<BillableDesc> billableDescResult = new ArrayList<BillableDesc>();
        for(int a=0;a<billableDescList.size();a++){
//            String queryTaxInvDetail = "from TaxInvoiceDetail taxDetail where taxDetail.invoiceDetail.billableDesc.id = :id ";
//            List<TaxInvoiceDetail> taxInvoiceDetailList = session.createQuery(queryTaxInvDetail).setParameter("id", billableDescList.get(a).getId()).list();
//            if(taxInvoiceDetailList.isEmpty()){
                billableDescResult.add(billableDescList.get(a));
//            }
        }
        billableList.get(0).setBillableDescs(billableDescResult);  
        Billable billable = new Billable();
        billable = billableList.get(0);
        Master master = new Master();
        master.setBookingType(billableList.get(0).getMaster().getBookingType());
        master.setReferenceNo(billableList.get(0).getMaster().getReferenceNo());
        billable.setMaster(master);
//        session.close();
        return billable;
    }
    
    @Override
    public Invoice getInvoiceForTaxInvoice(Billable bill) {
        Session session = this.sessionFactory.openSession();
        Invoice invoice = new Invoice();
        List<BillableDesc> billableDescList = bill.getBillableDescs();
        List<InvoiceDetail> invoiceDetailList = new ArrayList<InvoiceDetail>();
        for(int i=0; i<billableDescList.size(); i++){
            BillableDesc billableDesc = billableDescList.get(i);
            String queryInv = "from InvoiceDetail inv where inv.billableDesc.id = :id ";
            List<InvoiceDetail> queryInvoiceDetailList = session.createQuery(queryInv).setParameter("id",billableDesc.getId()).list();            
            if(i==0){
//                invoice.setId(invoiceDetailList.get(0).getId());
                invoice.setId(queryInvoiceDetailList.get(0).getInvoice().getId());
                invoice.setInvTo(queryInvoiceDetailList.get(0).getInvoice().getInvTo());
                invoice.setInvName(queryInvoiceDetailList.get(0).getInvoice().getInvName());
                invoice.setInvAddress(queryInvoiceDetailList.get(0).getInvoice().getInvAddress());
                invoice.setArcode(queryInvoiceDetailList.get(0).getInvoice().getArcode());
            }
            InvoiceDetail invoiceDetail = new InvoiceDetail();           
            invoiceDetail.setId(queryInvoiceDetailList.get(0).getId());
            invoiceDetail.setBillableDesc(billableDesc);
            invoiceDetailList.add(invoiceDetail);
        }
        invoice.setInvoiceDetails(invoiceDetailList);
        session.close();
        return invoice;
    }

    @Override
    public int updateBillStatusBooking(String refno, Billable bill) {
        int result = 0;

        try {
            Session session = this.sessionFactory.openSession();
            Query query = session.createQuery(OtherBookingUpdate);
            query.setParameter("refno", refno);
            result = query.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }

        return result;
    }

    @Override
    public int insertBillableBooking(Billable bill) {
        int result = 0;
        HashSet<String> ListBill = new HashSet<String>();
        Session session = this.sessionFactory.openSession();
        try {
            
            transaction = session.beginTransaction();
            session.save(bill);
            if (bill.getBillableDescs() != null) {
                List<BillableDesc> BillDescList = new ArrayList<BillableDesc>(bill.getBillableDescs());
                System.out.println("save getBillableDescs :" + BillDescList.size());
                for (BillableDesc b : BillDescList) {
                    System.out.println("getBillableDescs cost : " + b.getCost());
                    if (b.getId() == null) {
                        b.setBillable(bill);
                        b.setIsBill(1);
                        session.save(b);
                        updateBillDesc(session, b);
                    } else {
                        b.setBillable(bill);
                        b.setIsBill(1);
                        session.update(b);
                        //updateBillDesc(session, b);
                    }
                    ListBill.add(b.getMBilltype().getId());
                }
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            this.sessionFactory.close();
            ex.printStackTrace();
            result = 0;
        }
        return result;

    }
    
    public boolean IsLapAirline(String data,String airlineid){
        boolean result = false;
        if(data.equalsIgnoreCase("")){
            return result;
        }
        String[] path = data.split(",");
        for(int i=0;i<path.length;i++){
            if(airlineid.equalsIgnoreCase(path[i])){
                return true;
            }
        }
        return result;
    }

    public void updateBillDesc(Session session, BillableDesc bill) {
        String hql = "";
        String AirlineId = "";
        String billtype = bill.getMBilltype().getId();
        if (billtype.equalsIgnoreCase("1")) {         
            List<AirticketFlight> listAirFlight = session.createQuery(SelectAirLineId).setParameter("Keyid", bill.getBillable().getMaster().getId()).list();
             for(int i=0;i<listAirFlight.size();i++){
                 String airlineId = listAirFlight.get(i).getAirticketAirline().getId();
                 System.out.println(airlineId);     
                 if(!IsLapAirline(AirlineId,airlineId)){
                     AirlineId += airlineId +",";
                 }
             }
             if(!AirlineId.equalsIgnoreCase("")){
                 String refItem = "";
                 refItem = AirlineId.substring(0,AirlineId.length()-1);
                 AirlineId = "("+ AirlineId.substring(0,AirlineId.length()-1) + ")";
                 bill.setRefItemId(refItem);
             }
             System.out.println(AirlineId);
             hql = AirBookingUpdate + " "+AirlineId;
        } else if ((billtype.equalsIgnoreCase("2")) || (billtype.equalsIgnoreCase("8"))) {
            System.out.println("update other");
            hql = OtherBookingUpdate;
        } else if (billtype.equalsIgnoreCase("3")) {
            System.out.println("update land");
            hql = LandBookingUpdate;
        } else if (billtype.equalsIgnoreCase("4")) {
            System.out.println("update hotel");
            hql = HotelBookingUpdate;
        } else if (billtype.equalsIgnoreCase("6")) {
            System.out.println("update daytour");
            hql = DaytourBookingUpdate;
        } else if (billtype.equalsIgnoreCase("7")) {
            System.out.println("update Air ticket additional");
            hql = AirBookingDescUpdate;
        }
        Query query = session.createQuery(hql);
        if (!billtype.equalsIgnoreCase("1")) {
            query.setParameter("Keyid", bill.getRefItemId());
        }
        query.executeUpdate();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int updateBillableBooking(Billable bill) {
        int result = 0;
        HashSet<String> ListBill = new HashSet<String>();
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(bill);
            if (bill.getBillableDescs() != null) {
                List<BillableDesc> BillDescList = new ArrayList<BillableDesc>(bill.getBillableDescs());
                System.out.println("save getBillableDescs :" + BillDescList.size());
                for (BillableDesc b : BillDescList) {
                    System.out.println("getBillableDescs cost : " + b.getCost());
                    if (b.getId() == null) {
                        b.setBillable(bill);
                        b.setIsBill(1);
                        session.save(b);
                        updateBillDesc(session, b);
                    } else {
                        b.setBillable(bill);
                        b.setIsBill(1);
                        session.update(b);
                       // updateBillDesc(session, b);
                    }
                    ListBill.add(b.getMBilltype().getId());
                }
         
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            result = 0;
        }
        return result;
    }

    @Override
    public String getMBillTypeName(String typeId) {
        String typeName = "";
        Session session = this.sessionFactory.openSession();
        List<MBilltype> list = session.createQuery(SEARCH_MBILL_NAME).setParameter("typeId", typeId).list();

        if (list.isEmpty()) {
            return null;
        }else{
            typeName = list.get(0).getName();
        }
        session.close();
        this.sessionFactory.close();
        return typeName;
    }

    @Override
    public String getDescriptionInvoiceAirTicket(String refno,int format) {
        String description = "";
        Session session = this.sessionFactory.openSession();
        String airlineQuery = QUERY_AIRTICKET + "(" + refno +")";
        List<AirticketAirline> list = session.createQuery(airlineQuery).list();
        
        if (list.isEmpty()) {
            return null;
        }else{
            UtilityFunction utility = new UtilityFunction();
            String LeaderName = "";
            for(int k =0;k<list.size();k++){
                String FlightDescription ="";
                String FlightShort ="";
                AirticketAirline  airline = list.get(k);
                int isgroup = 0;
                //get group pax
                isgroup = (airline.getAirticketPnr().getAirticketBooking().getGroupPax() == null) ? 0:airline.getAirticketPnr().getAirticketBooking().getGroupPax();
                
                String ticketlife = "";
                DecimalFormat df = new DecimalFormat("###,##0.00");
                String DepartDateAndFlight = "";
                Integer price = new Integer(0);
                Integer tax = new Integer(0);
                //Ref No. {Ref NO}
                if(k ==0){
                    FlightDescription += "Ref No. "+airline.getAirticketPnr().getAirticketBooking().getMaster().getReferenceNo()+" : "+"\n";                    
                }

                //AIR TICKET         {ROUNTING} Ticket Type: {TICKET LIFE NAME}
                List<AirticketFlight> flight = new ArrayList<AirticketFlight>(airline.getAirticketFlights()); 
            
                for(int f =0;f<flight.size();f++){
                    AirticketFlight flightDetail = flight.get(f);
                    //get Ticket Type
                    if(flight.get(f).getMTicketType() != null){
                        if(ticketlife.indexOf(flightDetail.getMTicketType().getName()) == -1){
                            ticketlife += flightDetail.getMTicketType().getName()+",";
                        }
                    }
                
                //get Depart date and flight
                
                DepartDateAndFlight += "                     "+new SimpleDateFormat("ddMMMyyyy", new Locale("us", "us")).format(flightDetail.getDepartDate()) + "/"+flightDetail.getFlightNo() +"\n";
                
                //PRICE
                if(flightDetail.getAdPrice() != null){
                    if(flightDetail.getChPrice() != null){
                        if(flightDetail.getInPrice() != null){
                            price += flightDetail.getAdPrice() + flightDetail.getChPrice() + flightDetail.getInPrice();
                        }else{
                            price += flightDetail.getAdPrice() + flightDetail.getChPrice() + 0;
                        }
                    }else{
                        if(flightDetail.getInPrice() != null){
                            price += flightDetail.getAdPrice() + 0 + flightDetail.getInPrice();
                        }else{
                            price += flightDetail.getAdPrice() + 0 + 0;
                        }
                    }
                }else{
                    if(flightDetail.getChPrice() != null){
                        if(flightDetail.getInPrice() != null){
                            price += 0 + flightDetail.getChPrice() + flightDetail.getInPrice();
                        }else{
                            price += 0 + flightDetail.getChPrice() + 0;
                        }
                    }else{
                        if(flightDetail.getInPrice() != null){
                            price += 0 + 0 + flightDetail.getInPrice();
                        }else{
                            price += 0;
                        }
                    }
                }    
                
                //TAX
                tax += (flightDetail.getAdTax() != null ? flightDetail.getAdTax() : 0) + (flightDetail.getChTax() != null ? flightDetail.getChTax() : 0) + (flightDetail.getInTax() != null ? flightDetail.getInTax() : 0);
                
            }
            if(ticketlife.length() > 0){
                    ticketlife = ticketlife.substring(0, ticketlife.length()-1);
            }
            if(k ==0){
                FlightDescription += "AIR TICKET"+"    "+utility.GetRounting(flight)
                        + " Ticket Type: "+ticketlife+"\n";     
            }else{
                FlightDescription += "                     "+utility.GetRounting(flight)
                        + " Ticket Type: "+ticketlife+"\n"; 
            }
            FlightShort += "                     "+utility.GetRounting(flight)
                        + " Ticket Type: "+ticketlife+"\n";
            
            //{DEPART DATE}/{FLIGHT}
            FlightDescription += DepartDateAndFlight;
            FlightShort  += DepartDateAndFlight;
            
            List<AirticketPassenger>  passengerList = new ArrayList<AirticketPassenger>(airline.getAirticketPassengers());
            String name = "";
            String ticketno = "";
            
            for(int p =0;p<passengerList.size();p++){
                AirticketPassenger passenger = passengerList.get(p);
                if(p == 0){
                    description += FlightDescription +"";
                }else{
                    description += FlightShort +"";
                }  
                
                String Initname = "";
                if(passenger.getMInitialname() != null){
                    Initname = passenger.getMInitialname().getName();
                }
                if(isgroup == 1){
                    //FOR  {INITNAME} {LAST NAME}/{FIRST NAME}        {PRICE} + {TAX}(PAX)
                    description += "FOR" +"               " + Initname +" "+passenger.getLastName() +"/"+passenger.getFirstName() +"<P>"+ utility.setFormatMoney(price) +" + "+utility.setFormatMoney(tax)+" ("+passengerList.size()+")</P>\n";
                    ticketno+= "                   "+"  TICKET NO. "+ passenger.getSeries1() +" - "+passenger.getSeries2()+" - "+passenger.getSeries3()+"\n\n";
                    //Break Loop
                    p +=  passengerList.size(); 
                }else{
                    //FOR  {INITNAME} {LAST NAME}/{FIRST NAME}        {PRICE} + {TAX}
                    description += "FOR" +"               " + Initname +" "+passenger.getLastName() +"/"+passenger.getFirstName() +"<P>"+ utility.setFormatMoney(price) +" + "+utility.setFormatMoney(tax)+"</P>\n";
                    ticketno+= "                   "+"  TICKET NO. "+ passenger.getSeries1() +" - "+passenger.getSeries2()+" - "+passenger.getSeries3()+"\n\n";
                }
                description += ticketno;
                ticketno = "";
            }
            
            String MInitialname = "";
            if(passengerList.get(0).getMInitialname() != null){
                MInitialname = passengerList.get(0).getMInitialname().getName();
            }
            if(format == 1){
             if(k ==0){
                LeaderName = "|"+ MInitialname +" "+ passengerList.get(0).getLastName() +" "+ passengerList.get(0).getFirstName() ;
                
             }
            }
             
             if(isgroup == 1){
                 k += list.size();
             }
            }
            description += LeaderName;  
        }
        
        System.out.println("description : "+description);
        session.close();
        this.sessionFactory.close();
        return description;
    }

    @Override
    public String getDescriptionInvoiceOthers(String refno,int format) {
        String description = "";
        Session session = this.sessionFactory.openSession();
        List<OtherBooking> list = session.createQuery(QUERY_OTHERS).setParameter("refitemid", refno).list();

        if (list.isEmpty()) {
            return null;
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMaster().getReferenceNo() != null){ // Ref no
                    description += "Ref No. "+list.get(i).getMaster().getReferenceNo() +" : ";
                }else{
                     description += "";
                }
                if(list.get(i).getMaster().getCustomer().getMInitialname() != null){ // prename
                    description += " "+list.get(i).getMaster().getCustomer().getMInitialname().getName() +"";
                }else{
                     description += " ";
                }
                if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                    description += " "+list.get(i).getMaster().getCustomer().getLastName() +" ";
                }else{
                     description += " ";
                }
                if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                    description += " "+list.get(i).getMaster().getCustomer().getFirstName() +"  \n";
                }else{
                     description += " \n";
                }
                if(list.get(i).getProduct().getName() != null){ // Product Name
                    description += ""+list.get(i).getProduct().getName() +" ";
                }else{
                     description += " ";
                }
                if(list.get(i).getOtherDate() != null){ // Other Date
                    String  date = utility.convertDateToString(list.get(i).getOtherDate());
                    String dateArr[] = date.split("-");
                    String newDate = dateArr[2] +"/" + dateArr[1] + "/" + dateArr[0];
                    description += "  ("+newDate +")\n";
                }else{
                     description += "\n";
                }
                String amount = "";
                if(list.get(i).getAdCost() != null && list.get(i).getAdCost() != 0 ){ // Adult Cost
                    if(list.get(i).getAdQty() != null && list.get(i).getAdQty() != 0){ // Adult Qty
                        amount += "("+list.get(i).getAdCost() +" x ";
                        amount += ""+list.get(i).getAdQty() +") ";
                    }else{
                        amount += "";
                    }
                }else{
                     amount += " ";
                }
                if(list.get(i).getChCost() != null && list.get(i).getChCost() != 0){ // Children Cost
                    if(list.get(i).getChQty()!= null && list.get(i).getChQty() != 0){ // Children Qty
                        amount += " ("+list.get(i).getChCost() +" x ";
                        amount += ""+list.get(i).getChQty() +")";
                    }else{
                        amount += "";
                    }
                }else{
                     amount += "";
                }
                if(list.get(i).getInCost() != null && list.get(i).getInCost() != 0){ // Infant Cost
                    if(list.get(i).getInQty()!= null && list.get(i).getInQty() != 0){ // Infant Qty
                        amount += "  ("+list.get(i).getInCost() +" x ";
                        amount += ""+list.get(i).getInQty() +")";
                    }else{
                        amount += "";
                    }
                }else{
                     amount += "";
                }
                
                if(amount.length() !=0){  
                    description += "Amount              "+amount;
                }
                if(format == 1){
                    if(list.get(i).getMaster().getCustomer().getMInitialname() != null){ // prename
                        description += "|"+list.get(i).getMaster().getCustomer().getMInitialname().getName() +" ";
                    }else{
                        description += " ";
                    }
                    if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                        description += " "+list.get(i).getMaster().getCustomer().getLastName() +" ";
                    }else{
                         description += " ";
                    }
                    if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                        description += " "+list.get(i).getMaster().getCustomer().getFirstName() +"";
                    }else{
                         description += "";
                    }
                }
            }
        }
        System.out.println("Description :" + description);
        session.close();
        this.sessionFactory.close();
        return description;
    }

    @Override
    public String getDescriptionInvoiceLand(String refno,int format) {
        String description = "";
        Session session = this.sessionFactory.openSession();
        List<LandBooking> list = session.createQuery(QUERY_LAND).setParameter("refitemid", refno).list();

        if (list.isEmpty()) {
            return null;
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMaster().getReferenceNo() != null){ // Ref no
                    description += "Ref No. "+list.get(i).getMaster().getReferenceNo() +" : ";
                }else{
                     description += "";
                }
                if(list.get(i).getMaster().getCustomer().getMInitialname() != null){ // prename
                    description += " "+list.get(i).getMaster().getCustomer().getMInitialname().getName() +" ";
                }else{
                     description += " ";
                }
                if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                    description += " "+list.get(i).getMaster().getCustomer().getLastName() +" ";
                }else{
                     description += " ";
                }
                if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                    description += " "+list.get(i).getMaster().getCustomer().getFirstName() +"  \n";
                }else{
                     description += "  \n";
                }
                description += "Land  ";
                List<LandItinerary> listLand = new LinkedList<LandItinerary>();
                listLand = list.get(i).getLandItineraries();
                for (int j = 0; j < listLand.size(); j++) {
                    if(j < (listLand.size() - 1)){                    
                        if(listLand.get(j).getDayDate() != null){ //day date
                            String  date = utility.convertDateToString(listLand.get(j).getDayDate());
                            String dateArr[] = date.split("-");
                            String newDate = dateArr[2] +"/" + dateArr[1] + "/" + dateArr[0];
                            description += "  ("+newDate +")";
                        }else{
                             description += "";
                        }
                        if(listLand.get(j).getDescription() != null){ // description
                            description += "          "+listLand.get(j).getDescription() +" NTS \n         ";
                        }else{
                             description += " 0 NTS  \n";
                        }
                    }else{
                        if(listLand.get(j).getDayDate() != null){ //day date
                            String  date = utility.convertDateToString(listLand.get(j).getDayDate());
                            String dateArr[] = date.split("-");
                            String newDate = dateArr[2] +"/" + dateArr[1] + "/" + dateArr[0];
                            description += "  ("+newDate +")";
                        }else{
                             description += "";
                        }
                        if(listLand.get(j).getDescription() != null){ // description
                            description += "          "+listLand.get(j).getDescription() +" NTS   ";
                        }else{
                             description += " 0 NTS   ";
                        }
                    }            
                }
                
                if(list.get(i).getMaster().getBookingType() != null){ // Qty
                    if("I".equals(list.get(i).getMaster().getBookingType())){
                        int sum = 0;
                        int adult = list.get(i).getInboundQty();
                        int child = list.get(i).getInboundChQty();
                        int infant = list.get(i).getInboundInQty();
                        sum = adult + child + infant;
                        if(sum != 0){
                            description += " "+ sum +"  PAX ";
                        }else{
                            description += " 0  PAX ";
                        } 
                    }else if ("O".equals(list.get(i).getMaster().getBookingType())){
                        int sum = 0;
                        int adult = list.get(i).getOutboundAdQty();
                        int child = list.get(i).getOutboundChQty();
                        int infant = list.get(i).getOutboundInQty();
                        sum = adult + child + infant;
                        if(sum != 0){
                            description += "<P>"+ sum +" PAX </P>";
                        }else{
                            description += "<P> 0  PAX </P>";
                        } 
                    }else{
                        description += "<P> 0  PAX </P>";
                    }
                }else{
                     description += " ";
                }
                if(format == 1){
                    if(list.get(i).getMaster().getCustomer().getMInitialname() != null){ // prename
                        description += "|"+list.get(i).getMaster().getCustomer().getMInitialname().getName() +" ";
                    }else{
                         description += " ";
                    }
                    if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                        description += " "+list.get(i).getMaster().getCustomer().getLastName() +" ";
                    }else{
                         description += " ";
                    }
                    if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                        description += " "+list.get(i).getMaster().getCustomer().getFirstName() +"";
                    }else{
                         description += "";
                    }
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        System.out.println("description : "+description);
        return description;
    }

    @Override
    public String getDescriptionInvoiceHotel(String refno,int format) {
        String description = "";
        Session session = this.sessionFactory.openSession();
        List<HotelBooking> list = session.createQuery(QUERY_HOTEL).setParameter("refitemid", refno).list();

        if (list.isEmpty()) {
            return null;
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMaster().getReferenceNo() != null){ // Ref no
                    description += "Ref No. "+list.get(i).getMaster().getReferenceNo() +" : ";
                }else{
                     description += "";
                }
                if(list.get(i).getMaster().getCustomer().getMInitialname() != null){ // prename
                    description += " "+list.get(i).getMaster().getCustomer().getMInitialname().getName() +" ";
                }else{
                     description += " ";
                }
                if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                    description += " "+list.get(i).getMaster().getCustomer().getLastName() +" ";
                }else{
                     description += " ";
                }
                if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                    description += " "+list.get(i).getMaster().getCustomer().getFirstName() +"  \n";
                }else{
                     description += "  \n";
                }
                description += "HOTEL ";
                if(list.get(i).getHotel().getName() != null){ // Hotel Name
                    description += "  "+list.get(i).getHotel().getName() +" \n";
                }else{
                     description += " \n";
                }
                if(list.get(i).getCheckin() != null){ // check in
                    String  date = utility.convertDateToString(list.get(i).getCheckin());
                    String dateArr[] = date.split("-");
                    String newDate  = dateArr[2] +" " + changeMonth(dateArr[1]) + " " + dateArr[0];
                    description += "            "+ newDate +" ";
                }else{
                     description += "            ";
                }
                if(list.get(i).getCheckout() != null){ // check out
                    String  date = utility.convertDateToString(list.get(i).getCheckout());
                    String dateArr[] = date.split("-");
                    String newDate  = dateArr[2] +" " + changeMonth(dateArr[1]) + " " + dateArr[0];
                    description += " -  "+newDate +" \n";
                }else{
                     description += " \n";
                }
                List<HotelRoom> listRoom = new LinkedList<HotelRoom>();
                listRoom = list.get(i).getHotelRooms();
                for (int j = 0; j < listRoom.size(); j++) {
                    if( listRoom.get(j).getPrice() != 0){ // room price
                        DecimalFormat myFormatter = new DecimalFormat("#,###");
                        String output = myFormatter.format(listRoom.get(j).getPrice());
                        description += "            "+ output +"  ";
                    }else{
                         description += "            ";
                    }
                    if(listRoom.get(j).getQty() != 0){ // room qty
                        description += " "+listRoom.get(j).getQty() +"  ";
                    }else{
                         description += "  ";
                    }
                    if(listRoom.get(j).getRoom() != null){ // room number
                        description += " "+listRoom.get(j).getRoom() +"  ";
                    }else{
                         description += "  ";
                    }
                    if(listRoom.get(j).getCategory() != null){ // room catagory
                        description += "  "+listRoom.get(j).getCategory() +"  ";
                    }else{
                         description += "  ";
                    }
                }
                
                int day = getDifferenceDays(list.get(i).getCheckin(), list.get(i).getCheckout()); // Day 
                if( day != 0){
                   description += "  :  "+day +" NST";
                }else{
                     description += "0 NST";
                }
                if(format == 1){
                    if(list.get(i).getMaster().getCustomer().getMInitialname() != null){ // prename
                        description += "|"+list.get(i).getMaster().getCustomer().getMInitialname().getName() +" ";
                    }else{
                         description += " ";
                    }
                    if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                        description += " "+list.get(i).getMaster().getCustomer().getLastName() +" ";
                    }else{
                         description += " ";
                    }
                    if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                        description += " "+list.get(i).getMaster().getCustomer().getFirstName() +"";
                    }else{
                         description += "";
                    }
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        return description;
    }

    @Override
    public String getDescriptionInvoiceDayTour(String refno,int format) {
        String description = "";
        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = session.createQuery(QUERY_DAYTOUR).setParameter("refitemid", refno).list();

        if (list.isEmpty()) {
            return null;
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMaster().getReferenceNo() != null){ // Ref no
                    description += "Ref No. "+list.get(i).getMaster().getReferenceNo() +" : ";
                }else{
                     description += "";
                }
                if(list.get(i).getMaster().getCustomer().getMInitialname() != null){ // prename
                    description += " "+list.get(i).getMaster().getCustomer().getMInitialname().getName() +" ";
                }else{
                     description += " ";
                }
                if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                    description += " "+list.get(i).getMaster().getCustomer().getLastName() +" ";
                }else{
                     description += " ";
                }
                if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                    description += " "+list.get(i).getMaster().getCustomer().getFirstName() +"  \n";
                }else{
                     description += "  \n";
                }
                description += "Day Tour ";
                if(list.get(i).getDaytour().getCode() != null){ // code
                    description += " "+list.get(i).getDaytour().getCode() +" ";
                }else{
                     description += " |";
                }
                if(list.get(i).getDaytour().getName()!= null){ // name
                    description += " : "+list.get(i).getDaytour().getName() +"  \n";
                }else{
                     description += "  \n";
                }
                if(list.get(i).getTourDate() != null){ // date
                    String  date = utility.convertDateToString(list.get(i).getTourDate());
                    String dateArr[] = date.split("-");
                    String newDate = dateArr[2] +"/" + dateArr[1] + "/" + dateArr[0];
                    description += "               ("+newDate +"  ";
                }else{
                     description += "  ";
                }
                if(list.get(i).getAdult() != 0){ // adult
                    description += " Adult "+ list.get(i).getAdult() +" Pax";
                }else{
                     description += "  ";
                }
                if(list.get(i).getChild() != 0){ // child
                    description += " Child "+ list.get(i).getChild() +" Pax";
                }else{
                     description += "  ";
                }
                if(list.get(i).getInfant() != 0){ // infant
                    description += " Infant "+ list.get(i).getInfant() +" Pax)";
                }else{
                     description += ")";
                }
                if(format == 1){
                    if(list.get(i).getMaster().getCustomer().getMInitialname() != null){ // prename
                        description += "|"+list.get(i).getMaster().getCustomer().getMInitialname().getName() +" ";
                    }else{
                         description += " ";
                    }
                    if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                        description += " "+list.get(i).getMaster().getCustomer().getLastName() +" ";
                    }else{
                         description += " ";
                    }
                    if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                        description += " "+list.get(i).getMaster().getCustomer().getFirstName() +"";
                    }else{
                         description += "";
                    }
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        System.out.println("DEscription : " + description);
        return description;
    }
    private String changeMonth(String monthInt){
        String text = "";
        switch (monthInt){
            case "01" : text = "JAN";break;
            case "02" : text = "FEB";break;
            case "03" : text = "MAR";break;
            case "04" : text = "APR";break;
            case "05" : text = "MAY";break;
            case "06" : text = "JUN";break;
            case "07" : text = "JUL";break;
            case "08" : text = "AUG";break;
            case "09" : text = "SEP";break;
            case "10" : text = "OCT";break;
            case "11" : text = "NOV";break;
            case "12" : text = "DEC";break;
            case "" : text = "" ;break;
        }
        return text;
    }
    private  int getDifferenceDays(Date d1, Date d2) {
        int daysdiff = 0;
        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
        daysdiff = (int) diffDays;
        return daysdiff;
    }

    @Override
    public String getDescriptionInvoiceAirAdditional(String refno,int format) {
        String description = "";
        UtilityFunction util = new UtilityFunction();
        Session session = this.sessionFactory.openSession();
        List<AirticketDesc> list = session.createQuery(QUERY_AIRADDITIONAL).setParameter("refitemid", refno).list();
        //Ref No. 250034 : Mr. IWATA ARISA
        if (list.isEmpty()) {
            return null;
        }else{
            AirticketDesc desc = list.get(0);
            description += "Ref No. "+desc.getAirticketBooking().getMaster().getReferenceNo()+" : "+util.getCustomerName(desc.getAirticketBooking().getMaster().getCustomer())+"\n";
            description += desc.getDetail() +"\t\t\t\t\t"+ "(" + desc.getAmount() +" * "+desc.getQty() +")";
            if(format == 1){
                description += "|"+ util.getCustomerName(desc.getAirticketBooking().getMaster().getCustomer());
            }
        }
        
        session.close();
        this.sessionFactory.close();
        System.out.println("DEscription : " + description);
        return description;

    }

    @Override
    public String getDescriptionInvoiceOthersFromRefId(String refId) {
        String description = "";
        Session session = this.sessionFactory.openSession();
        List<OtherBooking> list = session.createQuery(QUERY_OTHERS).setParameter("refitemid", refId).list();
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        
        if (list.isEmpty()) {
            return null;
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMaster().getReferenceNo() != null){ // Ref no
                    description += ""+list.get(i).getMaster().getReferenceNo() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getMInitialname() != null){ // prename
                    description += ""+list.get(i).getMaster().getCustomer().getMInitialname().getName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                    description += ""+list.get(i).getMaster().getCustomer().getLastName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                    description += ""+list.get(i).getMaster().getCustomer().getFirstName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getProduct().getName() != null){ // Product Name
                    description += ""+list.get(i).getProduct().getName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getOtherDate() != null){ // Other Date
                    description += ""+String.valueOf(dateformat.format(list.get(i).getOtherDate()))  +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getAdCost() != null){ // Adult Cost
                    description += "("+list.get(i).getAdCost() +" x ";
                    if(list.get(i).getAdQty() != null){ // Adult Qty
                        description += ""+list.get(i).getAdQty() +")|";
                    }else{
                        description += "0)|";
                    }
                }else{
                     description += " |";
                }
                if(list.get(i).getChCost() != null){ // Children Cost
                    description += "("+list.get(i).getChCost() +" x ";
                    if(list.get(i).getChQty()!= null){ // Children Qty
                        description += ""+list.get(i).getChQty() +")|";
                    }else{
                        description += "0)|";
                    }
                }else{
                     description += " |";
                }
                if(list.get(i).getInCost() != null){ // Infant Cost
                    description += "("+list.get(i).getInCost() +" x ";
                    if(list.get(i).getInQty()!= null){ // Infant Qty
                        description += ""+list.get(i).getInQty() +")|";
                    }else{
                        description += "0)|";
                    }
                }else{
                     description += " |";
                }
                
                if(list.get(i).getProduct().getCode() != null){ // Product code
                    description += ""+list.get(i).getProduct().getCode() +"|";
                }else{
                    description += " |";
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        return description;
    }

    @Override
    public String getDescriptionInvoiceDayTourFromRefId(String refId) {
                String description = "";
        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = session.createQuery(QUERY_DAYTOUR).setParameter("refitemid", refId).list();
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy");
        if (list.isEmpty()) {
            return null;
        }else{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).getMaster().getReferenceNo() != null){ // Ref no
                    description += ""+list.get(i).getMaster().getReferenceNo() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getMInitialname().getName() != null){ // prename
                    description += ""+ list.get(i).getMaster().getCustomer().getMInitialname().getName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getLastName() != null){ //last name
                    description += ""+list.get(i).getMaster().getCustomer().getLastName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getMaster().getCustomer().getFirstName() != null){// firstname
                    description += ""+list.get(i).getMaster().getCustomer().getFirstName() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getDaytour().getCode() != null){ // code
                    description += ""+list.get(i).getDaytour().getCode() +"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getDaytour().getName()!= null){ // name
                    description += ""+list.get(i).getDaytour().getName() +"|";
                }else{
                     description += " |";
                }
                
                if(list.get(i).getTourDate() != null){ // date
                    description += ""+ String.valueOf(dateformat.format(list.get(i).getTourDate()))+"|";
                }else{
                     description += " |";
                }
                if(list.get(i).getAdult() != 0){ // adult
                    description += " Adult "+ list.get(i).getAdult() +" Pax |";
                }else{
                     description += " |";
                }
                if(list.get(i).getChild() != 0){ // child
                    description += " Child "+ list.get(i).getChild() +" Pax |";
                }else{
                     description += " |";
                }
                if(list.get(i).getInfant() != 0){ // infant
                    description += " Infant "+ list.get(i).getInfant() +" Pax |)";
                }else{
                     description += " |";
                }
            }
        }
        session.close();
        this.sessionFactory.close();
        System.out.println("DEscription : " + description);
        return description;
    }      

    @Override
    public String printTicketOrder(String refNo) {
        String result = "fail";
        Session session = this.sessionFactory.openSession();
        String query = " From AirticketPnr pnr where pnr.airticketBooking.master.referenceNo = :refno ";
        List<AirticketPnr> airticketPnrList = session.createQuery(query).setParameter("refno",refNo).list();
        if(!airticketPnrList.isEmpty()){
            if(airticketPnrList.get(0).getId() != null){
                result = airticketPnrList.get(0).getId();
            }          
        }
        session.close();
        return result;
    }

    @Override
    public String DeleteBillableDesc(String billdescId){
        boolean resultdelete = false;
        String billTypeId = "";
        String refItemId = "";
        int resulttemp = 0;
        String result = "fail";
        String queryupdate = "";
        String resultdeleted = "";
        String invno = "";
        Session session = this.sessionFactory.openSession();   
        List<BillableDesc> billableDescs = new ArrayList<BillableDesc>();
        List<InvoiceDetail> invoiceDetails = new ArrayList<InvoiceDetail>();
        BillableDesc billableDesc = new BillableDesc();
        
        String query = "from BillableDesc bill where bill.id = :billid";
        billableDescs = session.createQuery(query).setParameter("billid", billdescId).list();
        String queryinvdetail = "from InvoiceDetail inv where inv.billableDesc.id = :billid";
        invoiceDetails = session.createQuery(queryinvdetail).setParameter("billid", billdescId).list();
                
        if (billableDescs.isEmpty()) {
            return "fail";
        }else{
            System.out.println("billableDescs not empty");
            billableDesc =  billableDescs.get(0);
            billTypeId = String.valueOf(billableDesc.getMBilltype().getId());
            refItemId = String.valueOf(billableDesc.getRefItemId());
            try {
                transaction = session.beginTransaction();
                if(!invoiceDetails.isEmpty()){
                    for(int i = 0 ; i < invoiceDetails.size() ; i++){
                        InvoiceDetail invoiceDetail = new InvoiceDetail();
                        invoiceDetail = invoiceDetails.get(i);
                        invno = invoiceDetail.getInvoice().getInvNo();
//                        List<ReceiptDetail> receiptDetailList = invoiceDetail.getReceiptDetails();
//                        if(!receiptDetailList.isEmpty()){
//                            for(int j = 0 ; j < receiptDetailList.size() ; j++){
//                                ReceiptDetail receiptDetail = new ReceiptDetail();
//                                receiptDetail = receiptDetailList.get(j);
//                                session.delete(receiptDetail);
//                            }
//                        }
//                        session.delete(invoiceDetail);
                    }
                    return invno;
                }else{
                    session.delete(billableDesc);
                    transaction.commit();
                    resultdelete = true;
                    resultdeleted = "success";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                resultdeleted = "fail";
            }
        }
        if(resultdelete){
            if("1".equalsIgnoreCase(billTypeId)){
                String[] parts = refItemId.split(",");
                refItemId = "";
                for(int j=0;j<parts.length;j++){
                    if(j==(parts.length-1)){
                        refItemId += "'"+parts[j]+"'";
                    }else{
                        refItemId += "'"+parts[j]+"',";
                    }
                }
                queryupdate = "update AirticketFlight flight set flight.isBill = 0 where flight.airticketAirline.id in ("+refItemId+")";
            }else if("2".equalsIgnoreCase(billTypeId) || "8".equalsIgnoreCase(billTypeId)){
                queryupdate = "update  OtherBooking  other  set  other.isBill = 0 where other.id  = :refid";
            }else if("3".equalsIgnoreCase(billTypeId)){
                queryupdate = "update  LandBooking   land  set  land.isBill = 0 where land.id  = :refid";
            }else if("4".equalsIgnoreCase(billTypeId)){
                queryupdate = "update  HotelBooking   hotel  set  hotel.isBill = 0 where  hotel.id  = :refid";
            }else if("6".equalsIgnoreCase(billTypeId)){
                queryupdate = "update  DaytourBooking tour  set  tour.isBill = 0 where tour.id  = :refid";
            }
            
            try {
                Query queryup = session.createQuery(queryupdate);
                if(!"1".equalsIgnoreCase(billTypeId)){
                    queryup.setParameter("refid", refItemId);
                }
                System.out.println(" queryup " + queryup);
                resulttemp = queryup.executeUpdate();
                System.out.println(" resulttemp " + resulttemp);
                if(resulttemp == 0){
                    result = "fail";
                }else{
                    result = "success";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                resulttemp = 0;
                result = "fail";

            }
        }
        session.close();
        this.sessionFactory.close();
        return result;
    }
}
