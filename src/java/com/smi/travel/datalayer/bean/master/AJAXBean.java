/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.bean.master;

import com.smi.travel.common.bean.AbstractBean;
import com.smi.travel.controller.LockUnlockBookingController;
import com.smi.travel.datalayer.ajax.service.AbstractAJAXServices;
import com.smi.travel.datalayer.dao.BillableDao;
import com.smi.travel.datalayer.dao.CustomerDao;
import com.smi.travel.datalayer.dao.DaytourBookingDao;
import com.smi.travel.datalayer.dao.DaytourComissionDao;
import com.smi.travel.datalayer.dao.DaytourDao;
import com.smi.travel.datalayer.dao.InvoiceDao;
import com.smi.travel.datalayer.dao.MAirportDao;
import com.smi.travel.datalayer.dao.MasterDao;
import com.smi.travel.datalayer.dao.OtherBookingDao;
import com.smi.travel.datalayer.dao.PackageTourDao;
import com.smi.travel.datalayer.dao.PaymentAirTicketDao;
import com.smi.travel.datalayer.dao.ProductDetailDao;
import com.smi.travel.datalayer.dao.ReceiptDao;
import com.smi.travel.datalayer.dao.RefundAirticketDao;
import com.smi.travel.datalayer.dao.TicketFareAirlineDao;
import com.smi.travel.datalayer.dao.TransferJobDao;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.Billable;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourBookingPrice;
import com.smi.travel.datalayer.entity.DaytourPrice;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.MAirport;
import com.smi.travel.datalayer.entity.MBookingstatus;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PackageItinerary;
import com.smi.travel.datalayer.entity.PackagePrice;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.entity.ProductDetail;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.view.dao.BookingSummaryDao;
import com.smi.travel.datalayer.view.dao.CustomerAgentInfoDao;
import com.smi.travel.datalayer.view.dao.TicketAircommissionViewDao;
import com.smi.travel.datalayer.view.entity.BookSummary;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.datalayer.view.entity.TicketAircommissionView;
import com.smi.travel.util.Mail;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Surachai
 */
public class AJAXBean extends AbstractBean implements
        AbstractAJAXServices {

    private static final String BOOKDETAIL = "BookDetailServlet";
    private static final String BOOKOTHER = "BookOtherServlet";
    private static final String BOOKLAND = "BookLandServlet";
    private static final String BOOK = "BookServlet";
    private static final String BILLABLE = "BillableServlet";
    private static final String BOOKDAYTOUR = "BookDaytourServlet";
    private static final String TRANSFERJOB = "TransferJobServlet";
    private static final String DAYTOURCOMM = "DaytourCommissionServlet";
    private static final String DAYTOUR = "DaytourServlet";
    private static final String AIRTICKET = "AirTicketServlet";
    private static final String PASSENGER = "PassengerServlet";
    private static final String MAIL = "MailServlet";
    private static final String BOOKINGSTATUS = "BookingStatusServlet";
    private static final String TICKETFAREAIRLINE = "TicketFareAirlineServlet";
    private static final String PAYMENTAIRTICKET = "PaymentAirTicketServlet";
    private static final String INVOICE = "InvoiceServlet";
    private static final String REFUNDAIRLINE = "RefundAirlineServlet";
    private static final String RECEIPT = "ReceiptServlet";
    private static final String TAXINVOICE = "TaxInvoiceServlet";
    private CustomerDao customerdao;
    private ProductDetailDao productDetailDao;
    private BookingSummaryDao bookingsummarydao;
    private CustomerAgentInfoDao customerAgentInfoDao;
    private DaytourBookingDao daytourBookingdao;
    private DaytourDao daytourdao;
    private TransferJobDao transferJobdao;
    private PackageTourDao packagedao;
    private DaytourComissionDao daytourComdao;
    private MAirportDao airportdao;
    private Mail sendMail;
    private MasterDao masterdao;
    private OtherBookingDao otherBookingDao;
    private TicketFareAirlineDao ticketFareAirlineDao; 
    private PaymentAirTicketDao paymentairticketdao; 
    private BillableDao billableDao;
    private RefundAirticketDao refundAirticketDao;
    private InvoiceDao invoicedao;
    private ReceiptDao receiptdao;
    private TicketAircommissionViewDao ticketAircommissionViewDao;
    
    public AJAXBean(List queryList) {
        super(queryList);
        if (queryList != null && queryList.size() > 0) {
            for (int i = 0; i < queryList.size(); i++) {
                Object obj = queryList.get(i);

                if (obj instanceof CustomerDao) {
                    customerdao = (CustomerDao) obj;
                } else if (obj instanceof ProductDetailDao) {
                    productDetailDao = (ProductDetailDao) obj;
                } else if (obj instanceof BookingSummaryDao) {
                    bookingsummarydao = (BookingSummaryDao) obj;
                } else if (obj instanceof CustomerAgentInfoDao) {
                    customerAgentInfoDao = (CustomerAgentInfoDao) obj;
                } else if (obj instanceof DaytourBookingDao) {
                    daytourBookingdao = (DaytourBookingDao) obj;
                } else if (obj instanceof DaytourDao) {
                    daytourdao = (DaytourDao) obj;
                } else if (obj instanceof TransferJobDao) {
                    transferJobdao = (TransferJobDao) obj;
                } else if (obj instanceof PackageTourDao) {
                    packagedao = (PackageTourDao) obj;
                } else if (obj instanceof DaytourComissionDao) {
                    daytourComdao = (DaytourComissionDao) obj;
                } else if (obj instanceof MAirportDao) {
                    airportdao = (MAirportDao) obj;
                } else if (obj instanceof Mail) {
                    sendMail = (Mail) obj;
                } else if (obj instanceof MasterDao) {
                    masterdao = (MasterDao) obj;
                } else if (obj instanceof OtherBookingDao) {
                    otherBookingDao = (OtherBookingDao) obj;
                } else if (obj instanceof TicketFareAirlineDao) {
                    ticketFareAirlineDao = (TicketFareAirlineDao) obj;
                } else if (obj instanceof PaymentAirTicketDao){
                    paymentairticketdao = (PaymentAirTicketDao) obj;
                }else if (obj instanceof BillableDao){
                    billableDao = (BillableDao) obj;
                }else if (obj instanceof RefundAirticketDao){
                    refundAirticketDao = (RefundAirticketDao) obj;
                }else if (obj instanceof InvoiceDao){
                    invoicedao = (InvoiceDao) obj;
                }else if (obj instanceof ReceiptDao){
                    receiptdao = (ReceiptDao) obj;
                }else if (obj instanceof TicketAircommissionViewDao){
                    ticketAircommissionViewDao = (TicketAircommissionViewDao) obj;
                }
            }
        }
    }

    @Override
    public List load(Map map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List loadMulti(Map map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object loadSingle(Map map) { 
        Object result = new Object();
        String servletName = String.valueOf(map.get("servletName"));
        String type = String.valueOf(map.get("type"));
        String sendTo = String.valueOf(map.get("sendTo"));
        String sendCc = String.valueOf(map.get("sendCc"));
        String subject = String.valueOf(map.get("subject"));
        String content = String.valueOf(map.get("content"));
        String attachfile = String.valueOf(map.get("attachfile"));
        
        String refNo = String.valueOf(map.get("refNo"));
        String selectStatus = String.valueOf(map.get("selectStatus"));
        String flagAir = String.valueOf(map.get("flagAir"));
        String flagHotel = String.valueOf(map.get("flagHotel"));
        String flagDaytour = String.valueOf(map.get("flagDaytour"));
        String flagLand = String.valueOf(map.get("flagLand"));
        String flagOther = String.valueOf(map.get("flagOther"));

        if (BOOKDETAIL.equalsIgnoreCase(servletName)) {

            if ("checkExistCustomer".equalsIgnoreCase(type)) {
                String initialID = map.get("initialID").toString();
                String first = map.get("first").toString();
                String last = map.get("last").toString();
                result = customerdao.isExistCustomer(initialID, first, last);
                System.out.println("result : " + result.toString());
            } else if ("saveCustomer".equalsIgnoreCase(type)) {
                Customer customer = new Customer();
                String initialID = map.get("initialID").toString();
                customer.setFirstName(map.get("first").toString());
                customer.setLastName(map.get("last").toString());
                customer.setAddress(map.get("address").toString());
                if (map.get("initialID") != null) {
                    MInitialname initialname = new MInitialname();
                    initialname.setId(initialID);
                    customer.setMInitialname(initialname);
                }
                customer.setTel(map.get("tel").toString());
                result = customerdao.insertCustomerAjax(customer);
                //result = 1;
            } else if ("getCustomerList".equalsIgnoreCase(type)) {
                Customer customer = new Customer();
                String name = map.get("name").toString();
                String[] pathname = name.trim().split("/");
                int filter = 0;
                if (pathname.length == 1) {
                    customer.setFirstName(pathname[0]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0]);
                    filter = 1;
                } else {
                    filter = 0;
                    customer.setFirstName(pathname[1]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0] + pathname[1]);
                }

                List<Customer> customerList = customerdao.FiterCustomer(customer, filter);
                result = this.buildCustomerListHTML(customerList);
                // System.out.println("result : " + result);O
            }else if("getCustomerAutoList".equalsIgnoreCase(type)){
                String name = map.get("name").toString();
                Customer customer = new Customer();
                String[] pathname = name.trim().split("/");
                int filter = 0;
                if (pathname.length == 1) {
                    customer.setFirstName(pathname[0]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0]);
                    filter = 1;
                } else {
                    filter = 0;
                    customer.setFirstName(pathname[1]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0] + pathname[1]);
                }
                result = buildCustomerListJSON(customerdao.FiterCustomer(customer,filter));
            }
        } else if (BOOKOTHER.equalsIgnoreCase(servletName)) {
            //result = customerdao.isExistCustomer(initialID, first, last);
            System.out.println("ajax : " + BOOKOTHER);
            if ("getvalueProduct".equalsIgnoreCase(type)) {
                String productID = map.get("productid").toString();
                String otherdate = map.get("otherdate").toString();
                if ("".equalsIgnoreCase(productID) || "".equalsIgnoreCase(otherdate)) {
                    result = "0,0,0,0,0,0";
                } else {
                    ProductDetail product = productDetailDao.getValueFromProduct(productID, otherdate);
                    if (product == null) {
                        result = "0,0,0,0,0,0";
                    } else {
                        result = product.getAdCost() + "," + product.getChCost() + "," + product.getInCost() + ","
                                + product.getAdPrice() + "," + product.getChPrice() + "," + product.getInPrice();
                    }
                }
                System.out.println("result :" + result);
            }
            if ("getCouponCheck".equalsIgnoreCase(type)){
                String couponId = map.get("couponId").toString();
                boolean coupons = otherBookingDao.CheckUsabilityCoupon(couponId);
                if(coupons){
                    result = "true";
                } else {
                    result = "false";
                }
                System.out.println("result :" + result);
            }
            if ("getOtherBookList".equalsIgnoreCase(type)){
                String name = map.get("name").toString();
                Customer customer = new Customer();
                String[] pathname = name.trim().split("/");
                int filter = 0;
                if (pathname.length == 1) {
                    customer.setFirstName(pathname[0]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0]);
                    filter = 1;
                } else {
                    filter = 0;
                    customer.setFirstName(pathname[1]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0] + pathname[1]);
                }
                result = otherBookingDao.searchOtherBooking(customer, filter);
            }
        } else if (BOOKLAND.equalsIgnoreCase(servletName)) {
            //result = customerdao.isExistCustomer(initialID, first, last);
            System.out.println("ajax : " + BOOKLAND);
            if ("getvaluePackage".equalsIgnoreCase(type)) {
                System.out.println("getvaluePackage : " + BOOKLAND);
                String packageID = map.get("packageid").toString();
                String departDate = map.get("departdate").toString();
                PackagePrice price = new PackagePrice();
                price = packagedao.getValueFromPackage(packageID, departDate);
                if (price == null) {
                    result = "0,0,0,0,0,0";
                } else {
                    result = price.getAdCost() + "," + price.getChCost() + "," + price.getInCost() + ","
                            + price.getAdPrice() + "," + price.getChPrice() + "," + price.getInPrice();
                }
                System.out.println("result :" + result);

            } else if ("getListItinerary".equalsIgnoreCase(type)) {
                String packageID = map.get("packageid").toString();
                UtilityFunction util = new UtilityFunction();
                String Iti = "";
                PackageTour pa = packagedao.getPackageFromID(packageID);
                List<PackageItinerary> packItinerary = pa.getPackageItineraries();
                for (int i = 0; i < packItinerary.size(); i++) {
                    PackageItinerary ItiList = packItinerary.get(i);
                    Iti += ItiList.getOrderNo() + "&=" + util.convertTimeToString(ItiList.getTime()) + "&=" + ItiList.getDetail();
                    Iti += "*=";
                }
                if (Iti.length() != 0) {
                    Iti = Iti.substring(0, Iti.length() - 2);
                }
                System.out.println("Iti : " + Iti);
                result = Iti;

            }
        } else if (BOOK.equalsIgnoreCase(servletName)) {
            try {
                if ("summaryBook".equalsIgnoreCase(type)) {
                    result = "";
                    String refno = map.get("refno").toString();
                    List<BookSummary> summaryList = bookingsummarydao.getListBookSummary(refno);
                    if (summaryList != null) {
                        for (int i = 0; i < summaryList.size(); i++) {
                            String tourdate = "";

                            BookSummary bookDetail = summaryList.get(i);
                            if (bookDetail.getDateTour() != null) {
                                tourdate = bookDetail.getDateTour().toString();
                            }

                            result += "<tr>"
                                    + "<td class='tdcenter'>" + bookDetail.getBookdate() + "</td>"
                                    + "<td class='tdcenter'>" + bookDetail.getType() + "</td>"
                                    + "<td>" + bookDetail.getDescription() + "</td>"
                                    + "<td class='tdcenter'>" + tourdate + "</td>"
                                    + "<td class='moneyformat tdright'>" + bookDetail.getPrice() + "</td>"
                                    + "</tr>";
                        }
                    }

                    System.out.println("result : " + result);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (BILLABLE.equalsIgnoreCase(servletName)) {
            if ("getListBillto".equalsIgnoreCase(type)) {
                String name = map.get("name").toString();
                List<CustomerAgentInfo> data = customerAgentInfoDao.SearchListCustomerAgentInfo(name);
                String tabledata = "";
                if (data != null) {

                    for (int i = 0; i < data.size(); i++) {
                        CustomerAgentInfo row = data.get(i);
                        tabledata += "<tr onclick=\"setBillValue('" + row.getBillTo() + "','" + ReplaceEnterKey(row.getBillName()) + "','" + ReplaceEnterKey(row.getAddress()) + "','" + row.getTerm() + "','" + row.getPay() + "');\">";
                        tabledata += "<td class='item-billto'>" + row.getBillTo() + "</td>";
                        tabledata += "<td class='item-name'>" + ReplaceEnterKey(row.getBillName()) + "</td>";
                        tabledata += "<td class='item-address hidden'>" + ReplaceEnterKey(row.getAddress()) + "</td>";
                        tabledata += "<td class='item-tel hidden'>" + row.getTel() + "</td>";
                        tabledata += "</tr>";
                    }
                }
                System.out.println("tabledata : " + tabledata);
                result = tabledata;
            }else  if ("getAutoListBillto".equalsIgnoreCase(type)) {
                String name = map.get("name").toString();
                result = buildBillListJSON(customerAgentInfoDao.SearchListCustomerAgentInfo(name));
            }
        } else if (BOOKDAYTOUR.equalsIgnoreCase(servletName)) {
            String TourID = null;
            String TourDate = null;
            if ("TourReference".equalsIgnoreCase(type)) {
                TourID = map.get("tourid").toString();
                TourDate = map.get("date").toString();
                System.out.println("start ajax service : TourReference");
                List<DaytourBooking> ListBook = daytourBookingdao.getTourReference(TourID, TourDate);
                result = buildTourReferenceListHTML(ListBook);
            } else if ("DaytourPrice".equalsIgnoreCase(type)) {
                TourID = map.get("tourid").toString();
                List<DaytourPrice> pricelist = daytourdao.getDaytourPrice(TourID);
                result = buildTourPriceListHTML(pricelist);
            }
        } else if (TRANSFERJOB.equalsIgnoreCase(servletName)) {
            String TourID = null;
            String TourCode = null;
            String TourDate = null;
            String Place = null;
            String Other = null;
            if ("filterTour".equalsIgnoreCase(type)) {
                TourDate = map.get("date").toString();
                List<DaytourBooking> ListBook = transferJobdao.filterTourFromDate(TourDate);
                result = buildTourListHTML(ListBook);
            } else if ("filterPlace".equalsIgnoreCase(type)) {
                TourID = map.get("tourid").toString();
                TourDate = map.get("date").toString();
                List<Place> ListPlace = transferJobdao.filterPlaceFromDateAndTour(TourDate, TourID);
                result = buildTourPlaceListHTML(ListPlace);
            } else if ("filterOther".equalsIgnoreCase(type)) {
                TourID = map.get("tourid").toString();
                TourDate = map.get("date").toString();
                List<String> ListOther = transferJobdao.filterPlaceOtherFromDateAndTour(TourDate, TourID);
                result = buildTourPlaceOtherListHTML(ListOther);
            } else if ("getjobDetail".equalsIgnoreCase(type)) {
                TourCode = map.get("tourcode").toString();
                TourDate = map.get("date").toString();
                Place = map.get("place").toString();
                Other = map.get("other").toString();
                List<DaytourBooking> Booklist = transferJobdao.getTransferjobData(TourCode, TourDate, Place, Other);
                result = buildJobDetailHTML(Booklist);
            }
        } else if (DAYTOURCOMM.equalsIgnoreCase(servletName)) {
            String TourCode = null;
            String TourDate = null;
            String Price = null;
            String AgentId = null;
            if ("getguidecom".equalsIgnoreCase(type)) {
                TourCode = map.get("tourcode").toString();
                result = String.valueOf(daytourComdao.GetGuideComissionFromTour(TourCode));
                System.out.println("result guide commission  : " + result);
            } else if ("getagentcom".equalsIgnoreCase(type)) {
                TourCode = map.get("tourcode").toString();
                TourDate = map.get("tourdate").toString();
                Price = map.get("price").toString();
                AgentId = map.get("agentid").toString();
                System.out.println("test ajax");
                System.out.println(TourCode);
                System.out.println(TourDate);
                System.out.println(Price);
                System.out.println(AgentId);
                if (daytourdao == null) {
                    System.out.println(" id null");
                }
                double commission = daytourComdao.GetAgentComissionFromTour(AgentId, TourCode, TourDate);
                double output = (Double.parseDouble(Price) * commission / 100);
                System.out.println("commission : " + commission + " price : " + output);
                result = String.valueOf(output);
            }
        } else if (DAYTOUR.equalsIgnoreCase(servletName)) {
            String name = map.get("name").toString();
            if ("savestaff".equalsIgnoreCase(type)) {
                result = daytourdao.saveStafftour(name);
            }
        } else if (AIRTICKET.equalsIgnoreCase(servletName)) {
            String name = map.get("name").toString();
            if ("searchairportDeparture".equalsIgnoreCase(type)) {
                result = buildAirportListHTMLDeparture(airportdao.searchAirport(name));
            }else if ("searchairportArrive".equalsIgnoreCase(type)) {
                result = buildAirportListHTMLArrive(airportdao.searchAirport(name));
            }else if("getautoairport".equalsIgnoreCase(type)){//winit
                result = buildAirportListJSON(airportdao.searchAirport(name));
            }else if("getairportname".equalsIgnoreCase(type)){
                List<MAirport> data = airportdao.searchAirport(name);
                String result2 = "";
                if(data == null){
                    result = "";
                }else{
                    result2 = data.get(0).getId() + "," + data.get(0).getName();
                    result = result2;
                }
                System.out.println("result2 =" + result2);
                System.out.println(result);
            }
        }else if(PASSENGER.equalsIgnoreCase(servletName)){
            String name = map.get("name").toString();
            if ("searchPassenger".equalsIgnoreCase(type)) {
                Customer customer = new Customer();
                String[] pathname = name.trim().split("/");
                int filter = 0;
                if (pathname.length == 1) {
                    
                    customer.setFirstName(pathname[0]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0]);
                    filter = 1;
                } else {
                    System.out.println("First : "+pathname[1]);
                    System.out.println("Last : "+pathname[0]);
                    filter = 0;
                    customer.setFirstName(pathname[1]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0] + pathname[1]);
                }

                List<Customer> customerList = customerdao.FiterCustomer(customer, filter);
                result = buildPassengerListHTML(customerList);
                System.out.println("result passenger: "+result);
            }else if("searchAutoPassenger".equalsIgnoreCase(type)){
                Customer customer = new Customer();
                String[] pathname = name.trim().split("/");
                int filter = 0;
                if (pathname.length == 1) {
                    
                    customer.setFirstName(pathname[0]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0]);
                    filter = 1;
                } else {
                    System.out.println("First : "+pathname[1]);
                    System.out.println("Last : "+pathname[0]);
                    filter = 0;
                    customer.setFirstName(pathname[1]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0] + pathname[1]);
                }
                result = buildPassengerListJSON(customerdao.FiterCustomer(customer,filter));
            }
        }else if (MAIL.equalsIgnoreCase(servletName)) {
            if ("sendMail".equalsIgnoreCase(type)) {
                try {
                    result = sendMail.main(sendTo,subject,content,attachfile,sendCc);
                } catch (EmailException ex) {
                    Logger.getLogger(AJAXBean.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(AJAXBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (BOOKINGSTATUS.equalsIgnoreCase(servletName)) {
            if ("search".equalsIgnoreCase(type)) {
                if(refNo == null){
                    System.out.print("refno is null");
                }else{
                    int[] bookStatus = masterdao.getBookStatusFromRefno(refNo);
                    if (bookStatus == null) {
                        result = "0,0,0,0,0,0";
                    }else{
                        result = bookStatus[0]+ "," 
                                + bookStatus[1] + "," 
                                + bookStatus[2] + ","
                                + bookStatus[3] + "," 
                                + bookStatus[4] + "," 
                                + bookStatus[5];
                    }
                }
                System.out.println("result :" + result);
            }
            if ("save".equalsIgnoreCase(type)) {
                if(refNo == null){
                    System.out.print("refno is null");
                }else{
                    MBookingstatus mbookstatus = new MBookingstatus();
                    mbookstatus.setId(String.valueOf(selectStatus));
                    Master masterlist = masterdao.getBookingFromRefno(refNo);
                    Master master = new Master();
                    master.setId(masterlist.getId());
                    master.setReferenceNo(masterlist.getReferenceNo());
                    master.setStaff(masterlist.getStaff());
                    master.setAgent(masterlist.getAgent());
                    master.setCustomer(masterlist.getCustomer());
                    master.setAdult(masterlist.getAdult());
                    master.setChild(masterlist.getChild());
                    master.setInfant(masterlist.getInfant());
                    master.setIsPackage(masterlist.getIsPackage());
                    master.setAgentRef(masterlist.getAgentRef());
                    master.setRevisedBy(masterlist.getRevisedBy());
                    master.setRevisedDate(masterlist.getRevisedDate());
                    master.setBookingType(masterlist.getBookingType());
                    master.setCreateBy(masterlist.getCreateBy());
                    master.setCreateDate(masterlist.getCreateDate());
                    master.setCurrency(masterlist.getCurrency());
                    master.setMBookingstatus(mbookstatus);
                    master.setFlagAir(Integer.parseInt(String.valueOf(flagAir)));
                    master.setFlagHotel(Integer.parseInt(String.valueOf(flagHotel)));
                    master.setFlagDaytour(Integer.parseInt(String.valueOf(flagDaytour)));
                    master.setFlagLand(Integer.parseInt(String.valueOf(flagLand)));
                    master.setFlagOther(Integer.parseInt(String.valueOf(flagOther)));
                    int savesuccess = masterdao.LockAndUnLockBooking(master);
                    result = selectStatus + "," 
                            + flagAir + ","
                            + flagHotel + "," 
                            + flagDaytour + "," 
                            + flagLand + "," 
                            + flagOther + ","
                            + savesuccess
                            ;
                }
                System.out.println("result save:" + result);
            }
        } else if (TICKETFAREAIRLINE.equalsIgnoreCase(servletName)) {
            if ("getTicketList".equalsIgnoreCase(type)) {
                String referNo = map.get("referNo").toString();
                System.out.println("referNo"+referNo);
                result = ticketFareAirlineDao.getListTicketFareFromRefno(referNo);
                if(result == null){
                    result = "null";
                }
            }
        } else if (PAYMENTAIRTICKET.equalsIgnoreCase(servletName)) {
            if ("addRefund".equalsIgnoreCase(type)) {
                String refundNo = map.get("refundNo").toString();
                String rowCount = map.get("rowCount").toString();
                System.out.println("rowCount ::: "+rowCount);
                result = paymentairticketdao.addRefundAirTicket(refundNo,rowCount);
                if(result == null){
                    result = "null";
                }
            }
        } else if (INVOICE.equalsIgnoreCase(servletName)){
            if("searchInvoice".equalsIgnoreCase(type)){
                String searchRefNo = map.get("refNo").toString();
                Billable bill = billableDao.getBillableBooking(searchRefNo);
                result = getListInvoice(bill);
            }else if("searchInvoiceDescription".equalsIgnoreCase(type)){
                String searchRefNo = map.get("refNo").toString();
                String typeId = map.get("typeId").toString();
                String typeName = billableDao.getMBillTypeName(typeId);
                if(typeName != null){
                    if("Air Ticket".equals(typeName)){
                        result += "|Air Ticket|";
                        result += billableDao.getDescriptionInvoiceAirTicket(searchRefNo);
                    }else if("Others".equals(typeName)){
                        result += "|Others|";
                        result += billableDao.getDescriptionInvoiceOthers(searchRefNo);
                    }else if("Land".equals(typeName)){
                        result += "|Land|";
                        result += billableDao.getDescriptionInvoiceLand(searchRefNo);
                    }else if("Hotel".equals(typeName)){
                        result += "|Hotel|";
                        result += billableDao.getDescriptionInvoiceHotel(searchRefNo);
                    }else if("Day Tour".equals(typeName)){
                        result += "|Day Tour|";
                        result += billableDao.getDescriptionInvoiceDayTour(searchRefNo);
                    }
                }
            }
        } else if (REFUNDAIRLINE.equalsIgnoreCase(servletName)) {
            if("getTicketFare".equalsIgnoreCase(type)){
                String ticketNo = map.get("ticketNo").toString();
                HashMap<String, Object> ticketFare = ticketFareAirlineDao.getDetailTicketFareAirline(ticketNo);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                DecimalFormat df = new DecimalFormat("###,##0.00");
                ticketFare.put("TicketDate", sdf.format(ticketFare.get("TicketDate")));
                String total = ticketFare.get("Total").toString();
                ticketFare.put("Total", df.format(Double.valueOf(total) * 1.00));
                JSONObject obj =new JSONObject(ticketFare);
                result = obj.toJSONString();
            }else if("delete".equalsIgnoreCase(type)){
                result = false;
                String detailId = map.get("detailId").toString();
                if(refundAirticketDao.checkPaymentAirticketRefund(detailId)){
                    result = refundAirticketDao.DeleteRefundAirticketDetail(detailId);
                }
            }
        }else if (RECEIPT.equalsIgnoreCase(servletName)) {
            if("searchInvoiceNo".equalsIgnoreCase(type)){
                String invoiceNo = map.get("invoiceNo").toString();
                System.out.println("invoiceNo ::: "+invoiceNo);
                Invoice invoice = new Invoice();
                invoice = invoicedao.getInvoiceFromInvoiceNumber(invoiceNo);
                if("".equals(invoice.getId()) || null == invoice.getId()){
                    result = "null";
                }else{
                    result = buildInvoiceListHTML(invoice);
                }
            }
            else if("searchRefNo".equalsIgnoreCase(type)){
                String searchRefNo = map.get("refNo").toString();
                Billable bill = billableDao.getBillableBooking(searchRefNo);
                if("".equals(bill.getId()) || null == bill.getId()){
                    result = "null";
                }else{
                    result = buildBillableListHTML(bill);
                }
            }else if("searchPaymentNoAir".equalsIgnoreCase(type)){
                String paymentNoAir = map.get("paymentNo").toString();
                System.out.println(" paymentNoAir :::  " + paymentNoAir + "::: ");
                List<TicketAircommissionView> ticketList = ticketAircommissionViewDao.getListTicketAircommissionView(paymentNoAir);
                System.out.println("ticketList size ::; " + ticketList.size() + " ////");
                if(ticketList.size() == 0){
                    result = "null";
                }else{
                    result = buildTicketAircommissionViewListHTML(ticketList);
                }
            }else if("searchPaymentNoTour".equalsIgnoreCase(type)){
                String paymentNoTour = map.get("paymentNo").toString();
                System.out.println(" paymentNoTour :::  " + paymentNoTour + "::: ");
                
            }
        }else if (TAXINVOICE.equalsIgnoreCase(servletName)) {
            String invoiceNo = map.get("invoiceNo").toString();
            List<HashMap<String,Object>> invoiceNoList = invoicedao.getInvoiceDetailFromInvoiceNumber(invoiceNo);
            Invoice invoice = new Invoice();
            invoice = invoicedao.getInvoiceFromInvoiceNumber(invoiceNo);
                
            if("".equals(invoice.getId()) || null == invoice.getId()){
                result = "null";
            }else{
                result = buildInvoiceListHTML(invoice);
            }
            
        }  
        
        return result;
    }
    
    public String buildTicketAircommissionViewListHTML(List<TicketAircommissionView> ticketList){
        StringBuffer html = new StringBuffer();
        int No = 0;
        String airline = "";
        String commission = "";
        String isUse = "";
        String paymentId ="";
        String description="";
        String payNo = "";
        String product = "";
        String currency = "";
        for(int i = 0 ; i < ticketList.size() ; i++ ){
            No = i+1;
            product = "9";
            currency = "THB";
            paymentId = ticketList.get(i).getPaymentId();
            payNo = ticketList.get(i).getPayNo();
            airline = ticketList.get(i).getAirline();
            commission = String.valueOf(ticketList.get(i).getCommision());
            isUse = String.valueOf(ticketList.get(i).getIsUse());
            description = String.valueOf(ticketList.get(i).getDetail());
            String newrow = "";
            if("U".equals(isUse)){
                newrow +=   "<tr>"+
//                            "<input type='hidden' name='paymentId' id='paymentId' value='"+paymentId+"'>" +
                            "<td class='text-center'>"+No+"</td>"+
                            "<td>"+airline+"</td>"+
                            "<td class='money'>"+commission+"</td>"+
                            "<td class='text-center'>"+isUse+"</td>"+ 
                            "<td><center><span class='glyphicon glyphicon-plus disable'></span></center></td>" +
                            "</tr>";
            }else if("N".equals(isUse)){
                newrow +=   "<tr>"+
//                            "<input type='hidden' name='paymentId' id='paymentId' value='"+paymentId+"'>" +
                            "<td class='text-center'>"+No+"</td>"+
                            "<td>"+airline+"</td>"+
                            "<td class='money'>"+commission+"</td>"+
                            "<td class='text-center'>"+isUse+"</td>"+ 
                            "<td><center><a href=\"\"><span onclick=\"addProduct('"+product+"','"+description+"','','','','','"+commission+"','"+currency+"','','','"+paymentId+"','"+airline+"')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>" +
                            "</tr>";
            }
            html.append(newrow);
        }
        
        return html.toString();
    }
    
    public String buildInvoiceListHTML(Invoice invoice){
        StringBuffer html = new StringBuffer();
        List<InvoiceDetail> invoiceDetaill = new ArrayList<InvoiceDetail>(invoice.getInvoiceDetails());
        String invId = "";
        String description = "";
        BigDecimal amount = new BigDecimal(0);
        BigDecimal cost = new BigDecimal(0);
        BigDecimal amountinvoice = new BigDecimal(0);
        BigDecimal costinvoice = new BigDecimal(0);
        String currency = "";
        String product = "";
        String cur = "" ; 
        String isVat = "";
        String vat = "";
        int No = 0;
        String receiveFrom = invoice.getInvTo();
        String receiveName = invoice.getInvName();
        String receiveAddress = invoice.getInvAddress();
        String arcode = invoice.getArcode();
        if (invoiceDetaill == null || invoiceDetaill.size() == 0) {
            return html.toString();
        }
        for(int i = 0 ; i < invoiceDetaill.size() ; i++ ){
            No = i+1;
            invId = invoiceDetaill.get(i).getId();
            description = invoiceDetaill.get(i).getDescription();
            amountinvoice = invoiceDetaill.get(i).getAmount().compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : invoiceDetaill.get(i).getAmount();
            currency = invoiceDetaill.get(i).getCurAmount();
            product = invoiceDetaill.get(i).getMbillType().getId();
            costinvoice = invoiceDetaill.get(i).getCost().compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : invoiceDetaill.get(i).getCost();
            cur = invoiceDetaill.get(i).getCurCost();
            isVat = String.valueOf(invoiceDetaill.get(i).getIsVat());
            vat = String.valueOf(invoiceDetaill.get(i).getVat());
            
            System.out.println(" invId " + invId);

            BigDecimal[] value = checkReceiptDetail(invId); 
            BigDecimal costTemp = value[0];
            BigDecimal amountTemp = value[1];
            amount = amountinvoice.subtract(amountTemp);
            cost = costinvoice.subtract(costTemp);
            System.out.println(" amount =  " + amountinvoice + "-" + amountTemp + " = "+  amount);
            System.out.println(" cost =  " + costinvoice + "-" + costTemp + " = "+  cost);

            if(amount.compareTo(BigDecimal.ZERO) != 0){
                String newrow = "";
                newrow +=   "<tr>"+
                            "<input type='hidden' name='receiveFromInvoice' id='receiveFromInvoice' value='"+receiveFrom+"'>" +
                            "<input type='hidden' name='receiveNameInvoice' id='receiveNameInvoice' value='"+receiveName+"'>" +
                            "<input type='hidden' name='receiveAddressInvoice' id='receiveAddressInvoice' value='"+receiveAddress+"'>" +
                            "<input type='hidden' name='arcodeInvoice' id='arcodeInvoice' value='"+arcode+"'>" +
                            "<input type='hidden' name='invoiceId' id='invoiceId' value='"+invoice.getId()+"'>" +
                            "<td class='text-center'>"+No+"</td>"+
                            "<td>"+description+"</td>"+
                            "<td class='money'>"+amount+"</td>"+
                            "<td>"+currency+"</td>"+ 
                            "<td><center><a href=\"\"><span onclick=\"addProduct('"+product+"','"+description+"','"+cost+"','"+cur+"','"+isVat+"','"+vat+"','"+amount+"','"+currency+"','"+invId+"','','','')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>" +
                            "</tr>";
                html.append(newrow);
            }else{
                String newrow = "";
                newrow +=   "<tr>"+
                            "<input type='hidden' name='receiveFromInvoice' id='receiveFromInvoice' value='"+receiveFrom+"'>" +
                            "<input type='hidden' name='receiveNameInvoice' id='receiveNameInvoice' value='"+receiveName+"'>" +
                            "<input type='hidden' name='receiveAddressInvoice' id='receiveAddressInvoice' value='"+receiveAddress+"'>" +
                            "<input type='hidden' name='arcodeInvoice' id='arcodeInvoice' value='"+arcode+"'>" +
                            "</tr>";
                html.append(newrow);
            
            }
        }
        return html.toString();
    }
    
    public String buildBillableListHTML(Billable billable){
        StringBuffer html = new StringBuffer();
        List<BillableDesc> billableDescs = new ArrayList<BillableDesc>(billable.getBillableDescs());
        String description = "";
        String amount = "";
        String currency = "";
        String product = "";
        String cost = "" ;
        String cur = "" ; 
        String isVat = "";
        String vat = "";
        String billableDescId = "";
        int No = 0;
        String mAccPay = "";
        String receiveFrom = billable.getBillTo();
        String receiveName = billable.getBillName();
        String receiveAddress = billable.getBillAddress();
        String arcode = billable.getBillTo();
        if(billable.getMAccpay() != null){
            mAccPay = billable.getMAccpay().getId();
        }
        if (billableDescs == null || billableDescs.size() == 0) {
            return html.toString();
        }
        for(int i = 0 ; i < billableDescs.size() ; i++ ){
            No = i+1;
            billableDescId = billableDescs.get(i).getId();
            description = billableDescs.get(i).getDetail();
            
            BigDecimal amounttemp = new BigDecimal(billableDescs.get(i).getPrice());
            amounttemp = amounttemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            amount = String.valueOf(amounttemp);
            
            currency = billableDescs.get(i).getCurrency();
            if(billableDescs.get(i).getMBilltype() != null){
                product = billableDescs.get(i).getMBilltype().getId();
            }
            cost = String.valueOf(billableDescs.get(i).getCost());
            cur = billableDescs.get(i).getCurrency();
            String newrow = "";
            newrow +=   "<tr>"+
                        "<input type='hidden' name='receiveFromBillable' id='receiveFromBillable' value='"+receiveFrom+"'>" +
                        "<input type='hidden' name='receiveNameBillable' id='receiveNameBillable' value='"+receiveName+"'>" +
                        "<input type='hidden' name='receiveAddressBillable' id='receiveAddressBillable' value='"+receiveAddress+"'>" +
                        "<input type='hidden' name='arcodeBillable' id='arcodeBillable' value='"+arcode+"'>" +
                        "<input type='hidden' name='mAccPayBillable' id='mAccPayBillable' value='"+mAccPay+"'>" +
//                        "<input type='hidden' name='billableDescId' id='billableDescId' value='"+billableDescId+"'>" +
                        "<td class='text-center'>"+No+"</td>"+
                        "<td>"+description+"</td>"+
                        "<td class='money'>"+amount+"</td>"+
                        "<td>"+currency+"</td>"+ 
                        "<td><center><a href=\"\"><span onclick=\"addProduct('"+product+"','"+description+"','"+cost+"','"+cur+"','','','"+amount+"','"+currency+"','','"+billableDescId+"','','')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>" +
                        "</tr>";
            html.append(newrow);
        }
        return html.toString();
    }
    public String buildPassengerListHTML(List<Customer> passList){
        String passenger = "";
        String MInitialname = "";
        String MInitialID = "";
        for(int i=0;i<passList.size();i++){
            Customer cus = passList.get(i);
            if(cus.getMInitialname() != null){
                MInitialname = cus.getMInitialname().getName();
                MInitialID = cus.getMInitialname().getId();
            }
           
            passenger += "<tr>"+
                    "<td class='customer-id hidden'>"+cus.getId()+"</td>"+
                    "<td class='customer-code '>"+cus.getCode()+"</td>"+
                    "<td class='customer-initial '>"+MInitialname+"</td>"+
                    "<td class='customer-initialId hidden'>"+MInitialID+"</td>"+
                    "<td class='customer-lastname '>"+cus.getLastName()+"</td>"+
                    "<td class='customer-firstname '>"+cus.getFirstName()+"</td>"+
                    "<td class='customer-sex hidden'>"+(cus.getSex()==null? "":cus.getSex())+"</td>"+
                    "<td class='customer-address hidden'>"+(cus.getAddress()==null? "":cus.getAddress())+"</td>"+
                    "<td class='customer-tel hidden'>"+(cus.getTel()==null? "":cus.getTel())+"</td>"+
                    "<td class='customer-phone hidden'>"+(cus.getPhone()==null? "":cus.getPhone())+"</td>"+
                    "<td class='customer-postal hidden'>"+(cus.getPostalCode()==null? "":cus.getPostalCode())+"</td>"+
                    "<td class='customer-email hidden'>"+(cus.getEmail()==null? "":cus.getEmail())+"</td>"+
                    "<td class='customer-japanfirstname hidden'>"+cus.getFirstNameJapan()+"</td>"+
                    "<td class='customer-japanlastname hidden'>"+cus.getLastNameJapan()+"</td>"+
                    "<td class='customer-remark hidden'>"+(cus.getRemark()==null? "":cus.getRemark())+"</td>"+
                    "<td class='customer-passportno hidden'>"+(cus.getPassportNo()==null? "":cus.getPassportNo())+"</td>"+
                    "</tr>";
                        
        }
       
        return passenger;
    }
    
    
    
    public JSONArray buildAirportListJSON(List<MAirport> listAirport) {
        JSONArray record = new JSONArray();
        for (int i = 0; i < listAirport.size(); i++) {
            MAirport airport = listAirport.get(i);
            JSONObject field = new JSONObject();
            field.put("id", airport.getId());
            field.put("code", airport.getCode());
            field.put("name", airport.getName());
            record.add(field);
        }
        return record;
    }
    
    public JSONArray buildPassengerListJSON(List<Customer> listCutomer) {
        JSONArray record = new JSONArray();
        for (int i = 0; i < listCutomer.size(); i++) {
            Customer customer = listCutomer.get(i);
            JSONObject field = new JSONObject();
            field.put("id", customer.getId());
            field.put("code", customer.getCode());
            field.put("initialname", customer.getMInitialname().getId());
            field.put("firstname", customer.getFirstName());
            field.put("lastname", customer.getLastName());
            field.put("sex", customer.getSex());
            field.put("address", customer.getAddress());
            field.put("tel", customer.getTel());
            field.put("phone", customer.getPhone());
            field.put("email", customer.getEmail());
            field.put("remark", customer.getRemark());
            field.put("passportno", customer.getPassportNo());
            field.put("firstnamejapan", customer.getFirstNameJapan());
            field.put("lastnamejapan", customer.getLastNameJapan());
            record.add(field);
        }
        return record;
    }
    
    public JSONArray buildBillListJSON(List<CustomerAgentInfo> listCutomerInfo) {
        JSONArray record = new JSONArray();
        for (int i = 0; i < listCutomerInfo.size(); i++) {
            CustomerAgentInfo customer = listCutomerInfo.get(i);
            JSONObject field = new JSONObject();
            field.put("id", customer.getBillTo());
            field.put("name", customer.getBillName());
            field.put("address", customer.getAddress());
            record.add(field);
        }
        return record;
    }
    
    public JSONArray buildCustomerListJSON(List<Customer> listCutomer) {
        JSONArray record = new JSONArray();
        for (int i = 0; i < listCutomer.size(); i++) {
            Customer customer = listCutomer.get(i);
            JSONObject field = new JSONObject();
            field.put("id", customer.getId());
            field.put("code", customer.getCode());
            field.put("initialname", customer.getMInitialname().getName());
            field.put("firstname", customer.getFirstName());
            field.put("lastname", customer.getLastName());
            field.put("tel", customer.getTel());
            field.put("address", customer.getAddress());
            record.add(field);
        }
        return record;
    }
 
    public String buildAirportListHTMLDeparture(List<MAirport> listAirport) {
        String result = "";
        for (int i = 0; i < listAirport.size(); i++) {
            MAirport airport = listAirport.get(i);
            result += " <tr class='departure-tr'>"
                    + " <td class='departure-id hidden'>" + airport.getId() + "</td>"
                    + " <td class='departure-code'>" + airport.getCode() + "</td>"
                    + " <td class='departure-name'>" + airport.getName() + "</td>"
                    + "</tr>";

        }
        return result;
    }
    public String buildAirportListHTMLArrive(List<MAirport> listAirport) {
        String result = "";
        for (int i = 0; i < listAirport.size(); i++) {
            MAirport airport = listAirport.get(i);
            result += " <tr class='arrival-tr'>"
                    + " <td class='arrival-id hidden'>" + airport.getId() + "</td>"
                    + " <td class='arrival-code'>" + airport.getCode() + "</td>"
                    + " <td class='arrival-name'>" + airport.getName() + "</td>"
                    + "</tr>";
        }
        return result;
    }

    public String buildTourReferenceListHTML(List<DaytourBooking> ListBook) {
        String result = "";
        if (ListBook == null) {
            System.out.println("DaytourBooking null");
            return result;
        }
        System.out.println("DaytourBooking size " + ListBook.size());
        for (int i = 0; i < ListBook.size(); i++) {
            DaytourBooking book = ListBook.get(i);
            Customer cus = book.getMaster().getCustomer();
            List<DaytourBookingPrice> prices = new ArrayList<DaytourBookingPrice>(book.getDaytourBookingPrices());
            String[] AllQty = calculatePassengerDaytour(prices);

            Integer sumPrice = 0;
            if (prices != null) {
                for (DaytourBookingPrice price : prices) {
                    Integer p = (price.getPrice() == null ? 0 : price.getPrice()) * (price.getQty() == null ? 0 : price.getQty());
                    sumPrice += p;
                }
            }

            String picktime = "";
            if (book.getPickupTime() != null) {
                picktime = book.getPickupTime().toString().substring(0, 5);
            }
            result += "<tr>"
                    + "<td>" + book.getMaster().getReferenceNo() + "</td>"
                    + "<td>" + cus.getMInitialname().getName() + " " + cus.getLastName() + " " + cus.getFirstName() + "</td>"
                    + "<td>" + AllQty[0] + "</td>"
                    + "<td>" + AllQty[1] + "</td>"
                    + "<td>" + AllQty[2] + "</td>"
                    + "<td class='money'>" + sumPrice + "</td>"
                    //                    + "<td>" + prices.get(0).getPrice() + "</td>"
                    + "<td>" + book.getPlace().getPlace() + "</td>"
                    + "<td>" + picktime + "</td>"
                    + "<td>" + book.getMItemstatus().getName() + "</td>"
                    + "<td>" + book.getMaster().getCreateBy() + "</td>"
                    + "</tr>";
        }
        return result;
    }

    public String buildTourListHTML(List<DaytourBooking> ListBook) {
        String result = "";
        if (ListBook == null) {
            result = "<input type='hidden' id='tourSize' name='tourSize' value='0'>";
            return result;
        }
        for (int i = 0; i < ListBook.size(); i++) {
            DaytourBooking daytourbooking = ListBook.get(i);
            result += "<tr id='trTourId" + daytourbooking.getDaytour().getId() + "'>"
                    + "<td class='tourid hide'>" + daytourbooking.getDaytour().getId() + "</td>"
                    + "<td class='tourplaceid hide '>" + daytourbooking.getPlace().getId() + "</td>"
                    + "<td class='tourplacename hide '>" + daytourbooking.getPlace().getPlace() + "</td>"
                    + "<td class='tourcode'>" + daytourbooking.getDaytour().getCode() + "</td>"
                    + "<td class='tourname'>" + daytourbooking.getDaytour().getName() + "</td>"
                    + "<td class='text-center'><input type='checkbox' class='action' id='row-" + daytourbooking.getDaytour().getId() + "-tour' ></td>"
                    + "</tr>";
        }
        result += "<input type='hidden' id='tourSize' name='tourSize' value='" + ListBook.size() + "'/>";
        return result;
    }

    public String buildTourPlaceListHTML(List<Place> ListPlace) {
        String result = "";
        if (ListPlace == null) {
            result = "<input type='hidden' id='placeSize' name='placeSize' value='0'>";
            return result;
        }

        for (int i = 0; i < ListPlace.size(); i++) {
            Place place = ListPlace.get(i);
            result += "<tr id='trPlaceId" + place.getId() + "'>"
                    + "<td class='placeid hidden '>" + place.getId() + "</td>"
                    + "<td class='placename'>" + place.getPlace() + "</td>"
                    + "<td class='text-center'><input type='checkbox' id='row-" + place.getId() + "-place'</td>"
                    + "</tr>";
        }
        result += "<input type='hidden' id='placeSize' name='placeSize' value='" + ListPlace.size() + "'/>";
        return result;
    }

    public String buildJobDetailHTML(List<DaytourBooking> bookingList) {
        String result = "";
        if (bookingList == null) {
            System.out.println("Empty JobDetail");
            return result;
        }
        result = "<tbody>";
        int row = 1;
        for (DaytourBooking daytour : bookingList) {
            try {

                String pickupPlace = "";
                Customer cus = daytour.getMaster().getCustomer();
                String Initialname = (cus.getMInitialname() == null ? "" : cus.getMInitialname().getName());
                if (daytour.getPlace() != null) {
                    if (daytour.getPlace().getPlace().equalsIgnoreCase("Others")) {
                        pickupPlace = daytour.getPickupDetail();
                    } else {
                        pickupPlace = daytour.getPlace().getPlace();
                    }
                }

                List<DaytourBookingPrice> PriceList = new ArrayList<DaytourBookingPrice>(daytour.getDaytourBookingPrices());
                String[] passenger = calculatePassengerDaytour(PriceList);
                result += "<tr>"
                        + "<td>" + row + "</td>"
                        + "<td>" + pickupPlace + "</td>"
                        + "<td class='text-center'>" + (daytour.getPickupRoom() == null ? "" : daytour.getPickupRoom()) + "</td>"
                        + "<td class='text-center'>" + (daytour.getPickupTime() == null ? "" : daytour.getPickupTime()) + "</td>"
                        + "<td>" + Initialname + " " + cus.getLastName() + " " + cus.getFirstName() + "</td>"
                        + "<td class='text-center'>" + passenger[0] + "</td>"
                        + "<td class='text-center'>" + passenger[1] + "</td>"
                        + "<td class='text-center'>" + passenger[2] + "</td>"
                        + "<td style='text-align: right' >" + calculatePriceDaytour(PriceList) + "</td>"
                        + "<td>" + (daytour.getGuide() == null ? "" : daytour.getGuide().getName()) + "</td>"
                        + "</tr>";
                row += 1;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        result += "</tbody>";
        System.out.println("JobDetail size(" + bookingList.size() + ")");
        return result;
    }

    public String[] calculatePassengerDaytour(List<DaytourBookingPrice> DriverList) {
        String result[] = new String[3];
        int adult = 0;
        int child = 0;
        int infant = 0;
        UtilityFunction util = new UtilityFunction();
        int Pricesum = 0;
        for (int i = 0; i < DriverList.size(); i++) {
            DaytourBookingPrice price = DriverList.get(i);

            if (price.getMPricecategory() != null) {
                String passType = price.getMPricecategory().getName();
                if ("ADULT".equalsIgnoreCase(passType)) {
                    adult += (price.getQty() == null ? 0 : price.getQty());
                } else if ("CHILD".equalsIgnoreCase(passType)) {
                    child += (price.getQty() == null ? 0 : price.getQty());
                } else if ("INFANT".equalsIgnoreCase(passType)) {
                    infant += (price.getQty() == null ? 0 : price.getQty());
                }
            }

        }
        result[0] = String.valueOf(adult);
        result[1] = String.valueOf(child);
        result[2] = String.valueOf(infant);
        return result;
    }

    public String calculatePriceDaytour(List<DaytourBookingPrice> DriverList) {
        String result = "";
        UtilityFunction util = new UtilityFunction();
        int Pricesum = 0;
        for (int i = 0; i < DriverList.size(); i++) {
            DaytourBookingPrice price = DriverList.get(i);
            Pricesum += util.ConvertInt(price.getQty()) * util.ConvertInt(price.getPrice());
        }

        return String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(Pricesum));
    }

    public String buildTourPlaceOtherListHTML(List<String> ListOther) {
        String result = "";
        if (ListOther == null) {
            return result;
        }
        for (int i = 0; i < ListOther.size(); i++) {
            String other = ListOther.get(i);
            if ((other != null) && (!"".equalsIgnoreCase(other))) {
                result += "<tr id='trOtherId" + i + "'>"
                        + "<td class='otherid hidden'>" + i + "</td>"
                        + "<td class='othername'>" + other + "</td>"
                        + "<td class='text-center'><input type='checkbox' id='row-" + i + "-other'></td>"
                        + "</tr>";
            }
        }
        return result;
    }

    public String buildTourPriceListHTML(List<DaytourPrice> pricelist) {
        String result = "";
        if (pricelist == null) {
            return result;
        }
        for (int i = 0; i < pricelist.size(); i++) {
            DaytourPrice price = pricelist.get(i);
            if (i % 2 == 0) {
                result += "<tr id='trpriceid" + price.getId() + "' style=background-color:#FFF >";
            } else {
                result += "<tr id='trpriceid" + price.getId() + "' style=background-color:#F2F2F2 >";
            }
            result += "<td class='bookingPriceId hidden'></td>"
                    + "<td class='priceId hidden'>" + price.getId() + "</td>"
                    + "<td class='priceCategoryId hidden'>" + price.getMPricecategory().getId() + "</td>"
                    + "<td class='priceCategoryName '>" + price.getMPricecategory().getName() + "</td>"
                    + "<td class='tourCode hidden'>" + price.getDaytour().getCode() + "</td>"
                    + "<td class='priceDetail'>" + price.getDetail() + "</td>"
                    + "<td class='priceAmount money'>" + price.getPrice() + "</td>"
                    + "<td class='priceCurrency text-center'>" + price.getCurrency() + "</td>"
                    + "<td class='text-center'><input type='checkbox' id='row-" + i + "-check'></td>"
                    + "</tr>";
        }
        return result;
    }

    public String buildCustomerListHTML(List<Customer> customerList) {
        StringBuffer html = new StringBuffer();
        if (customerList == null || customerList.size() == 0) {
            return html.toString();
        }

        Iterator<Customer> iter = customerList.iterator();
        while (iter.hasNext()) {
            Customer c = iter.next();
            String id = c.getId();
            String code = c.getCode();
            String Initialname = "";
            if (c.getMInitialname() != null) {
                Initialname = c.getMInitialname().getName();
            }

            String first = c.getFirstName();
            String last = c.getLastName();
            String address = c.getAddress();
            String tel = "";
            if (c.getTel() != null) {
                tel = c.getTel();
            }
            String newrow
                    = "<tr onclick=\"setCustomerDetail('" + id + "','" + code + "','" + Initialname + "','" + first + "','" + last + "','" + ReplaceEnterKey(address) + "','" + tel + "')\">"
                    + "<td class='hidden'>" + id + "</td>"
                    + "<td>" + code + "</td>"
                    + "<td class='hidden'>" + Initialname + "</td>"
                    + "<td >" + last + "</td>"
                    + "<td >" + first + "</td>"
                    + "<td class='hidden'>" + address + "</td>"
                    + "<td>" + tel + "</td>"
                    + "</tr>";
            html.append(newrow);
        }

        return html.toString();
    }

    public String ReplaceEnterKey(String input) {
        String data = input;
        if (input == null) {
            return "";
        }
        data = data.replaceAll("\n", "");
        data = data.replaceAll(System.getProperty("line.separator"), "");
        data = data.replaceAll("\\r|\\n", "");
        return data;
    }
    
    public String getListInvoice(Billable bill) {
        String result = "";
        result +=  bill.getBillTo() +","+ bill.getBillName() +"," + bill.getBillAddress()+"," + bill.getMAccterm().getId()
                +","+bill.getMaster().getStaff().getId()+","+bill.getMaster().getStaff().getName() + ","+ bill.getMaster().getStaff().getUsername()+"||";
        List<BillableDesc> billdeescList = bill.getBillableDescs();
        
        for (int i = 0; i < billdeescList.size(); i++) {
            if(billdeescList.get(i).getCurrency() == null){
                billdeescList.get(i).setCurrency("");
            }
            if(billdeescList.get(i).getDetail() == null){
                billdeescList.get(i).setDetail("");
            }
            result += "<tr>"
                    + "<td align=\"center\">" + (i+1) + "</td>"
                    + "<td class=\"hidden\"><input type=\"hidden\" id=\"invoiceIdSearch"+(i+1)+"\" name=\"invoiceIdSearch"+(i+1)+"\" value=" + billdeescList.get(i).getId() + "></td>"
                    + "<td class=\"hidden\"><input type=\"hidden\" id=\"invoiceIdType"+(i+1)+"\" name=\"invoiceIdType"+(i+1)+"\" value=" + billdeescList.get(i).getMBilltype().getId() + "></td>"
                    + "<td>" + billdeescList.get(i).getMBilltype().getName() + "</td>"
                    + "<td>" +  billdeescList.get(i).getDetail() + "</td>"
                    + "<td align=\"center\">" + billdeescList.get(i).getCost() + "</td>"
                    + "<td align=\"center\">" + billdeescList.get(i).getPrice() + "</td>"
                    + "<td align=\"center\">" + billdeescList.get(i).getCurrency() + "</td>"
                    + "<td align=\"center\"><center><a href=\"\" onclick=\"addInvoiceDetail("+(i+1)+")\"><span class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                    + "<td class=\"hidden\"><input type=\"hidden\" id=\"RefItemId"+(i+1)+"\" name=\"RefItemId"+(i+1)+"\" value=" + billdeescList.get(i).getRefItemId() + "></td>"
                    + "</tr>";

        }
        return result;
    }
    
    public Integer[] checkBillDescInuse(String billdesc){
        Integer[] value = new Integer[2];
        Integer amount = new Integer(0);
        Integer cost = new Integer(0);
        
        //from InvoiceDetail inv where inv.billableDesc.id = :billdesc
        //get amount and cost from InvoiceDetail
        
        value[0] = cost;
        value[1] = amount;
        return value;
    }
    
    public BigDecimal[] checkReceiptDetail(String invDetailId){
        BigDecimal[] value = new BigDecimal[2];
        BigDecimal amount = new BigDecimal(0);
        BigDecimal cost = new BigDecimal(0);
        BigDecimal resultAmount = new BigDecimal(0);
        BigDecimal resultCost = new BigDecimal(0);
        
        List<ReceiptDetail> receiptDetailList = receiptdao.getReceiptDetailFromInvDetailId(invDetailId);
        if (receiptDetailList == null || receiptDetailList.size() == 0) {
            value[0] = resultCost;
            value[1] = resultAmount;
            return value;
        }
        for (int i = 0; i < receiptDetailList.size(); i++) {
            cost = receiptDetailList.get(i).getCost();
            amount = receiptDetailList.get(i).getAmount();
            resultCost = resultCost.add(cost);
            resultAmount = resultAmount.add(amount);
        }
        //from InvoiceDetail inv where inv.billableDesc.id = :billdesc
        //get amount and cost from InvoiceDetail

        value[0] = resultCost;
        value[1] = resultAmount;
        return value;
    }
    public Mail getSendMail() {
        return sendMail;
    }
    
    public void setSendMail(Mail sendMail) {
        this.sendMail = sendMail;
    }

    public PaymentAirTicketDao getPaymentairticketdao() {
        return paymentairticketdao;
    }

    public void setPaymentairticketdao(PaymentAirTicketDao paymentairticketdao) {
        this.paymentairticketdao = paymentairticketdao;
    }

 
  
}
