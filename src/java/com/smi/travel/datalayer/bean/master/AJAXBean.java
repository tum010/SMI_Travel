/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.bean.master;

import com.smi.travel.common.bean.AbstractBean;
import com.smi.travel.datalayer.ajax.service.AbstractAJAXServices;
import com.smi.travel.datalayer.dao.BillableDao;
import com.smi.travel.datalayer.dao.CreditNoteDao;
import com.smi.travel.datalayer.dao.CustomerDao;
import com.smi.travel.datalayer.dao.DaytourBookingDao;
import com.smi.travel.datalayer.dao.DaytourComissionDao;
import com.smi.travel.datalayer.dao.DaytourDao;
import com.smi.travel.datalayer.dao.DefineVarDao;
import com.smi.travel.datalayer.dao.InvoiceDao;
import com.smi.travel.datalayer.dao.MAirportDao;
import com.smi.travel.datalayer.dao.MFilghtDao;
import com.smi.travel.datalayer.dao.MasterDao;
import com.smi.travel.datalayer.dao.OtherBookingDao;
import com.smi.travel.datalayer.dao.PackageTourDao;
import com.smi.travel.datalayer.dao.PaymentAirTicketDao;
import com.smi.travel.datalayer.dao.PaymentOutboundDao;
import com.smi.travel.datalayer.dao.PaymentWendytourDao;
import com.smi.travel.datalayer.dao.ProductDetailDao;
import com.smi.travel.datalayer.dao.ReceiptDao;
import com.smi.travel.datalayer.dao.ReceiveTableDao;
import com.smi.travel.datalayer.dao.RefundAirticketDao;
import com.smi.travel.datalayer.dao.TaxInvoiceDao;
import com.smi.travel.datalayer.dao.TicketFareAirlineDao;
import com.smi.travel.datalayer.dao.TransferJobDao;
import com.smi.travel.datalayer.entity.AdvanceReceive;
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
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MFlightservice;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PackageItinerary;
import com.smi.travel.datalayer.entity.PackagePrice;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.entity.ProductDetail;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.entity.TaxInvoiceDetail;
import com.smi.travel.datalayer.view.dao.BookingSummaryDao;
import com.smi.travel.datalayer.view.dao.CustomerAgentInfoDao;
import com.smi.travel.datalayer.view.dao.TicketAircommissionViewDao;
import com.smi.travel.datalayer.view.entity.BookSummary;
import com.smi.travel.datalayer.view.entity.BookingOutboundView;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.datalayer.view.entity.PaymentTourCommissionView;
import com.smi.travel.datalayer.view.entity.TicketAircommissionView;
import com.smi.travel.util.Mail;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
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
    private static final String CREDITNOTE = "CreditNoteServlet";
    private static final String PAYMENTTOURHOTEL = "PaymentTourHotelServlet";
    private static final String RECEIVETABLE = "ReceiveTableServlet";
    private static final String PAYMENTOUTBOUND = "PaymentOutboundServlet"; 
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
    private TaxInvoiceDao taxInvoiceDao;
    private CreditNoteDao creditNoteDao;
    private MFilghtDao mFlightDao;
    private PaymentWendytourDao paymentWendytourDao;
    private ReceiveTableDao receiveTableDao;
    private PaymentOutboundDao paymentOutboundDao;
    private DefineVarDao defineVardao;
    
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
                } else if (obj instanceof PaymentAirTicketDao) {
                    paymentairticketdao = (PaymentAirTicketDao) obj;
                } else if (obj instanceof BillableDao) {
                    billableDao = (BillableDao) obj;
                } else if (obj instanceof RefundAirticketDao) {
                    refundAirticketDao = (RefundAirticketDao) obj;
                } else if (obj instanceof InvoiceDao) {
                    invoicedao = (InvoiceDao) obj;
                } else if (obj instanceof ReceiptDao) {
                    receiptdao = (ReceiptDao) obj;
                } else if (obj instanceof TicketAircommissionViewDao) {
                    ticketAircommissionViewDao = (TicketAircommissionViewDao) obj;
                } else if (obj instanceof TaxInvoiceDao) {
                    taxInvoiceDao = (TaxInvoiceDao) obj;
                } else if (obj instanceof CreditNoteDao) {
                    creditNoteDao = (CreditNoteDao) obj;
                } else if (obj instanceof MFilghtDao) {
                    mFlightDao = (MFilghtDao) obj;
                } else if (obj instanceof PaymentWendytourDao) {
                    paymentWendytourDao = (PaymentWendytourDao) obj;
                } else if (obj instanceof ReceiveTableDao) {
                    receiveTableDao = (ReceiveTableDao) obj;
                } else if (obj instanceof PaymentOutboundDao) {
                    paymentOutboundDao = (PaymentOutboundDao) obj;
                } else if (obj instanceof DefineVarDao) {
                    defineVardao = (DefineVarDao) obj;
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
                customer.setFirstName((map.get("first").toString()).toUpperCase());
                customer.setLastName((map.get("last").toString()).toUpperCase());
                customer.setAddress((map.get("address").toString()).toUpperCase());
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
            } else if ("getCustomerAutoList".equalsIgnoreCase(type)) {
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
                result = buildCustomerListJSON(customerdao.FiterCustomer(customer, filter));
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
            if ("getCouponCheck".equalsIgnoreCase(type)) {
                String couponId = map.get("couponId").toString();
                boolean coupons = otherBookingDao.CheckUsabilityCoupon(couponId);
                if (coupons) {
                    result = "true";
                } else {
                    result = "false";
                }
                System.out.println("result :" + result);
            }
            if ("getOtherBookList".equalsIgnoreCase(type)) {
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
            if ("getStock".equalsIgnoreCase(type)){
                String productID = map.get("productid").toString();
                String otherdate = map.get("otherdate").toString();
                if ("".equalsIgnoreCase(productID) || "".equalsIgnoreCase(otherdate)) {
                    result = "notStock";
                } else {
                    result = otherBookingDao.checkStock(productID, otherdate);
                }               
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
            } else if ("getAutoListBillto".equalsIgnoreCase(type)) {
                String name = map.get("name").toString();
                result = buildBillListJSON(customerAgentInfoDao.SearchListCustomerAgentInfo(name));
            } else if ("deleteInvoiceDetail".equalsIgnoreCase(type)) {
                String id = map.get("name").toString();
                result = invoicedao.DeleteInvoiceDetail(id);
            }else if("searchFlightService".equalsIgnoreCase(type)){
                String id = map.get("name").toString();
                List<MFlightservice> data = mFlightDao.getListFlightService(id);
                String tabledata = "";
                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        System.out.println("Search Flight : " + data.get(i).getClassCode());
                        tabledata += "<tr><td class=\"hidden\"><input type=\"text\" class=\"form-control\" maxlength=\"3\" id=\"FlightServiceId"+ (i+1)+"\" style=\"text-transform:uppercase\" name=\"FlightServiceId"+ (i+1)+"\" value=\'"+ data.get(i).getId()+"'\"></td>";
                        tabledata += "<td style=\"width: 30%\"><input type=\"text\" class=\"form-control\" maxlength=\"1\" id=\"FlightServiceCode"+ (i+1)+"\" style=\"text-transform:uppercase\" name=\"FlightServiceCode"+ (i+1)+"\" value=\'"+ data.get(i).getClassCode()+"'\"></td>";
                        tabledata += "<td style=\"width: 55%\"><input type=\"text\" class=\"form-control\" maxlength=\"50\" id=\"FlightServiceName"+ (i+1)+"\" style=\"text-transform:uppercase\" name=\"FlightServiceName"+ (i+1)+"\" value=\'"+ data.get(i).getClassName()+"'\"></td>";
                        tabledata += "<td style=\"width: 10%\"><center><span id=\"spanRemove"+ (i+1)+"\" class=\"glyphicon glyphicon-remove deleteicon\"  onclick=\"deleteMFlightService('" + data.get(i).getId() + "','" + data.get(i).getClassCode() + "','" + (i+1) + "')\" ></span></center></td></tr> ";
                    }
                }
                System.out.println("tabledata : " + tabledata);
                result = tabledata;
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
            } else if ("searchairportArrive".equalsIgnoreCase(type)) {
                result = buildAirportListHTMLArrive(airportdao.searchAirport(name));
            } else if ("getautoairport".equalsIgnoreCase(type)) {//winit
                result = buildAirportListJSON(airportdao.searchAirport(name));
            } else if ("getairportname".equalsIgnoreCase(type)) {
                List<MAirport> data = airportdao.searchAirport(name);
                String result2 = "";
                if (data == null) {
                    result = "";
                } else {
                    result2 = data.get(0).getId() + "," + data.get(0).getName();
                    result = result2;
                }
                System.out.println("result2 =" + result2);
                System.out.println(result);
            }
        } else if (PASSENGER.equalsIgnoreCase(servletName)) {
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
                    System.out.println("First : " + pathname[1]);
                    System.out.println("Last : " + pathname[0]);
                    filter = 0;
                    customer.setFirstName(pathname[1]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0] + pathname[1]);
                }

                List<Customer> customerList = customerdao.FiterCustomer(customer, filter);
                result = buildPassengerListHTML(customerList);
                System.out.println("result passenger: " + result);
            } else if ("searchAutoPassenger".equalsIgnoreCase(type)) {
                Customer customer = new Customer();
                String[] pathname = name.trim().split("/");
                int filter = 0;
                if (pathname.length == 1) {

                    customer.setFirstName(pathname[0]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0]);
                    filter = 1;
                } else {
                    System.out.println("First : " + pathname[1]);
                    System.out.println("Last : " + pathname[0]);
                    filter = 0;
                    customer.setFirstName(pathname[1]);
                    customer.setLastName(pathname[0]);
                    customer.setCode(pathname[0] + pathname[1]);
                }
                result = buildPassengerListJSON(customerdao.FiterCustomer(customer, filter));
            }
        } else if (MAIL.equalsIgnoreCase(servletName)) {
            if ("sendMail".equalsIgnoreCase(type)) {

            }
//                } catch (MalformedURLException ex) {
        } else if (BOOKINGSTATUS.equalsIgnoreCase(servletName)) {
            if ("search".equalsIgnoreCase(type)) {
                if (refNo == null) {
                    System.out.print("refno is null");
                } else {
                    int[] bookStatus = masterdao.getBookStatusFromRefno(refNo);
                    if (bookStatus == null) {
                        result = "0,0,0,0,0,0";
                    } else {
                        result = bookStatus[0] + ","
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
                if (refNo == null) {
                    System.out.print("refno is null");
                } else {
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
                            + savesuccess;
                }
                System.out.println("result save:" + result);
            }
        } else if (TICKETFAREAIRLINE.equalsIgnoreCase(servletName)) {
            if ("getTicketList".equalsIgnoreCase(type)) {
                String referNo = map.get("referNo").toString();
                System.out.println("referNo" + referNo);
                result = ticketFareAirlineDao.getListTicketFareFromRefno(referNo);
                if (result == null) {
                    result = "null";
                }
            }
            if ("getTicketListByInvno".equalsIgnoreCase(type)) {
                String invNo = map.get("invNo").toString();
                System.out.println("invNo ::: " + invNo);
                result = ticketFareAirlineDao.getListTicketFareFromInvno(invNo);
                if (result == null) {
                    result = "null";
                }
            }
            if ("checkTicketNo".equalsIgnoreCase(type)) {
                String ticketNo = map.get("ticketNo").toString();
                System.out.println("ticketNo ::: " + ticketNo);
                result = ticketFareAirlineDao.getListTicketFareFromTicketNo(ticketNo);
                if (result == null) {
                    result = "null";
                }
            }
            if ("getMAirlineAgentByAirCode".equalsIgnoreCase(type)) {
                String airlineCode = map.get("airlineCode").toString();
                System.out.println("airlinecode ::: " + airlineCode);
                result = ticketFareAirlineDao.getMAirlineAgentFromAirlineCode(airlineCode);
                System.out.println(" ++++++++++++++++ result ++++++++++++++++" + result);
                if (result == null) {
                    result = "null";
                }
            }
        } else if (PAYMENTAIRTICKET.equalsIgnoreCase(servletName)) {
            if ("addRefund".equalsIgnoreCase(type)) {
                String refundNo = map.get("refundNo").toString();
                String rowCount = map.get("rowCount").toString();
                String ticketNoList = map.get("ticketNoList").toString();
                System.out.println("rowCount ::: " + rowCount);
                result = paymentairticketdao.addRefundAirTicket(refundNo, rowCount,ticketNoList);
                if (result == null) {
                    result = "null";
                }
            }
        } else if (INVOICE.equalsIgnoreCase(servletName)) {
            if ("searchInvoice".equalsIgnoreCase(type)) {
                String searchRefNo = map.get("refNo").toString();
                String invType = map.get("invType").toString();
                Billable bill = billableDao.getBillableBooking(searchRefNo);
                result = getListInvoice(bill, invType);
            } else if ("searchInvoiceDescription".equalsIgnoreCase(type)) {
                String searchRefNo = map.get("refNo").toString();
                String typeId = map.get("typeId").toString();
                String typeName = billableDao.getMBillTypeName(typeId);
                if (typeName != null) {
                    result = "";
                    if("Air Ticket".equals(typeName)) {
                        result += billableDao.getDescriptionInvoiceAirTicket(searchRefNo);
                    } else if (("Others".equals(typeName)) || ("Coupon".equals(typeName))) {
                        result += billableDao.getDescriptionInvoiceOthers(searchRefNo);
                    } else if ("Land".equals(typeName)) {
                        result += billableDao.getDescriptionInvoiceLand(searchRefNo);
                    } else if ("Hotel".equals(typeName)) {
                        result += billableDao.getDescriptionInvoiceHotel(searchRefNo);
                    } else if ("Day Tour".equals(typeName)) {
                        result += billableDao.getDescriptionInvoiceDayTour(searchRefNo);
                    } else if ("Air Additional".equals(typeName)) {
                        System.out.println("result LL : " + result);;
                        result += billableDao.getDescriptionInvoiceAirAdditional(searchRefNo);
                    }
                }
            }
        } else if (REFUNDAIRLINE.equalsIgnoreCase(servletName)) {
            if ("getTicketFare".equalsIgnoreCase(type)) {
                String ticketNo = map.get("ticketNo").toString();
                HashMap<String, Object> ticketFare = ticketFareAirlineDao.getDetailTicketFareAirline(ticketNo);
                if (ticketFare != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    DecimalFormat df = new DecimalFormat("###,##0.00");
                    ticketFare.put("TicketDate", sdf.format(ticketFare.get("TicketDate")));
                    String total = ticketFare.get("Total").toString();
                    ticketFare.put("Total", df.format(Double.valueOf(total) * 1.00));
                    JSONObject obj = new JSONObject(ticketFare);
                    result = obj.toJSONString();
                } else {
                    result = "";
                }
            } else if ("delete".equalsIgnoreCase(type)) {
                result = false;
                String detailId = map.get("detailId").toString();
                if (refundAirticketDao.checkPaymentAirticketRefund(detailId)) {
                    result = refundAirticketDao.DeleteRefundAirticketDetail(detailId);
                }
            }
        } else if (RECEIPT.equalsIgnoreCase(servletName)) {
            if ("searchInvoiceNo".equalsIgnoreCase(type)) {
                String invoiceNo = map.get("invoiceNo").toString();
                String department = map.get("department").toString();
                String invType = map.get("invType").toString();
                Invoice invoice = new Invoice();
                
                String invTypeTemp = "";
                if("V".equals(invType)){
                    invTypeTemp = invoiceNo.substring(1, 2);
                    invType = invTypeTemp;
                    if("T".equals(invTypeTemp)){
                        invType = "A";
                    }
                }
                invoice = invoicedao.searchInvoiceFromInvoiceNumber(invoiceNo, department, invType);
                if ("".equals(invoice.getId()) || null == invoice.getId()) {
                    result = "null";
                } else {
                    result = buildInvoiceListHTML(invoice);
                }
            } else if ("searchRefNo".equalsIgnoreCase(type)) {
                String searchRefNo = map.get("refNo").toString();
                Billable bill = billableDao.getBillableBooking(searchRefNo);
                if (bill == null) {
                    result = "null";
                } else {
                    result = buildBillableListHTML(bill);
                }
            } else if ("searchPaymentNoAir".equalsIgnoreCase(type)) {
                String paymentNoAir = map.get("paymentNo").toString();
                System.out.println(" paymentNoAir :::  " + paymentNoAir + "::: ");
                List<TicketAircommissionView> ticketList = ticketAircommissionViewDao.getListTicketAircommissionView(paymentNoAir);
                if (ticketList == null) {
                    result = "null";
                } else {
                    result = buildTicketAircommissionViewListHTML(ticketList);
                }
            } else if ("searchPaymentNoTour".equalsIgnoreCase(type)) {
                String paymentNoTour = map.get("paymentNo").toString();
                System.out.println(" paymentNoTour :::  " + paymentNoTour + "::: ");
                List<PaymentTourCommissionView> paymentList = ticketAircommissionViewDao.getListPaymentTourCommissionView(paymentNoTour);
                if (paymentList == null) {
                    result = "null";
                } else {
                    result = buildPaymentTourCommissionViewListHTML(paymentList);
                }
            }
        } else if (TAXINVOICE.equalsIgnoreCase(servletName)) {
            if ("searchInvoiceNo".equalsIgnoreCase(type)) {
                String invoiceNo = map.get("invoiceNo").toString();
                String department = map.get("department").toString();
                String invType = "";
                System.out.println("invoiceNo ::: " + invoiceNo);
                Invoice invoice = new Invoice();
                invoice = invoicedao.searchInvoiceForTaxInvoice(invoiceNo, department);
                if ("".equals(invoice.getId()) || null == invoice.getId()) {
                    result = "null";
                } else {
                    result = buildTaxInvoiceListHTML(invoice);
                }
            }else if("searchRefNo".equalsIgnoreCase(type)){
                String searchRefNo = map.get("refNo").toString();
                Billable bill = billableDao.getBillableBookingForTaxInvoice(searchRefNo);
                if ("".equals(bill.getId()) || null == bill.getId()) {
                    result = "null";
                } 
                BillableDesc billableDescTemp = new BillableDesc();
                billableDescTemp = (BillableDesc) bill.getBillableDescs().get(0);
                Invoice invoice = billableDao.getInvoiceForTaxInvoice(billableDescTemp.getId());
                if ("".equals(invoice.getId()) || null == invoice.getId()){
                    result = "null";
                } else {
                    if("O".equalsIgnoreCase(bill.getMaster().getBookingType())){
                        result = buildBillableListTaxHTML(bill,invoice);
                    } else {
                        result = bill.getMaster().getBookingType();
                    }
                    
                }
            }else if("getTaxInvoice".equalsIgnoreCase(type)){
                String invoiceNo = map.get("invoiceNo").toString();
                TaxInvoice taxInv = taxInvoiceDao.getTaxInvoiceByTaxNo(invoiceNo);
                JSONObject obj = new JSONObject(convertInvoiceToMap(taxInv));
                result = obj.toJSONString();

            } 
        }else if (CREDITNOTE.equalsIgnoreCase(servletName)) {
            if ("delete".equalsIgnoreCase(type)) {
                String cnDetailId = map.get("cnDetailId").toString();
                if (cnDetailId != null && !cnDetailId.equals("")) {
                    result = creditNoteDao.DeleteCreditNoteDetail(cnDetailId);
                }
            }
            
        }else if (PAYMENTTOURHOTEL.equalsIgnoreCase(servletName)){
            if("checkDayToursOperationDetail".equalsIgnoreCase(type)){
                String tourId = map.get("tourId").toString();
                String tourCode = map.get("tourCode").toString();
                String tourName = map.get("tourName").toString();
                String tourDate = map.get("tourDate").toString();
                result = paymentWendytourDao.checkDayTourOperationDetail(tourId,tourDate);
                System.out.println("Result : "+result);
            }else if("getTourCodeAutoList".equalsIgnoreCase(type)){
                String name = map.get("tourCode").toString();
                result = buildTourListJSON(paymentWendytourDao.searchListTourCode(name));
            }
        }else if (RECEIVETABLE.equalsIgnoreCase(servletName)){
            if("checkPeriodDate".equalsIgnoreCase(type)){
                String periodId = map.get("periodId").toString();
                String fromDate = map.get("fromDate").toString();
                String toDate = map.get("toDate").toString();
                String periodDetail = map.get("periodDetail").toString();
                String check = receiveTableDao.checkReceivePeriod(periodId,fromDate,toDate);
                if("success".equalsIgnoreCase(check)){
                    result = receiveTableDao.saveReceivePeriod(periodId,fromDate,toDate,periodDetail);
                    System.out.println("Result : "+result);
                }else{
                    result = "fail";
                }    
            }
        }else if(PAYMENTOUTBOUND.equalsIgnoreCase(servletName)){
            if("searchRefNo".equalsIgnoreCase(type)){
                String searchRefNo = map.get("refNo").toString();
                List<BookingOutboundView> bookingOutboundViewList = paymentOutboundDao.getBookingOutboundView(searchRefNo);
                if (bookingOutboundViewList.size() > 0) {
                    result = buildPaymentOutboundViewHTML(bookingOutboundViewList);
                } else {
                    result = "null";
                }
            }          
        }

        return result;
    }

    public String buildTicketAircommissionViewListHTML(List<TicketAircommissionView> ticketList) {
        StringBuffer html = new StringBuffer();
        int No = 0;
        String airline = "";
        String commission = "";
        String isUse = "";
        String paymentId = "";
        String description = "";
        String payNo = "";
        String product = "";
        String currency = "";
        String receiveFrom = "";
        String receiveName = "";
        String receiveAddress = "";
        String vat = "";
        for (int i = 0; i < ticketList.size(); i++) {
            No = i + 1;
            product = "9";
            currency = "THB";
            paymentId = ticketList.get(i).getPaymentId();
            payNo = ticketList.get(i).getPayNo();
            airline = ticketList.get(i).getAirline();
            commission = String.valueOf(ticketList.get(i).getCommision());
            isUse = String.valueOf(ticketList.get(i).getIsUse());
            description = String.valueOf(ticketList.get(i).getDetail());
            String newrow = "";
            System.out.println(" ticketList.get(i).getAgentcode() "+ ticketList.get(i).getAgentcode());
            if(!"null".equalsIgnoreCase(ticketList.get(i).getAgentcode()) ){
                receiveFrom = ticketList.get(i).getAgentcode();
            }
            if(!"null".equalsIgnoreCase(ticketList.get(i).getAgentname())){
                receiveName = ticketList.get(i).getAgentname();
            }
            if(!"null".equalsIgnoreCase(ticketList.get(i).getAgentaddress()) ){
                receiveAddress = ticketList.get(i).getAgentaddress();
            }
            MDefaultData mDefaultData = getMDefaultDataFromType("vat");
            vat = mDefaultData.getValue();
            if ("U".equals(isUse)) {
                newrow += "<tr>"
                        + "<td class='text-center'>" + No + "</td>"
                        + "<td>" + airline + "</td>"
                        + "<td class='money'>" + commission + "</td>"
                        + "<td class='text-center'>" + isUse + "</td>"
                        + "<td><center><span class='glyphicon glyphicon-plus disable'></span></center></td>"
                        + "</tr>";
            } else if ("N".equals(isUse)) {
                newrow += "<tr>"
                        + "<td class='text-center'>" + No + "</td>"
                        + "<td>" + airline + "</td>"
                        + "<td class='money'>" + commission + "</td>"
                        + "<td class='text-center'>" + isUse + "</td>"
                        + "<td><center><a href=\"#/com\"><span onclick=\"addProduct('" + product + "','" + description + "','','','1','" + vat + "','" + commission + "','" + currency + "','','','" + paymentId + "','" + airline + "','3','" + description + "','" + payNo + "','','" + receiveFrom + "','" + receiveName + "','" + receiveAddress + "')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                        + "</tr>";
            }
            html.append(newrow);
        }

        return html.toString();
    }
    
    public String buildPaymentTourCommissionViewListHTML(List<PaymentTourCommissionView> paymentTourList) {
        StringBuffer html = new StringBuffer();
        int No = 0;
        String airline = "";
        String commission = "";
        String isUse = "";
        String paymentTourId = "";
        String description = "";
        String payNo = "";
        String product = "";
        String currency = "";
        String receiveFrom = "";
        String receiveName = "";
        String receiveAddress = "";
        String vat = "";
        for (int i = 0; i < paymentTourList.size(); i++) {
            No = i + 1;
            product = "6";
            currency = "THB";
            paymentTourId = paymentTourList.get(i).getPaymentId();
            payNo = paymentTourList.get(i).getPayNo();
            commission = String.valueOf(paymentTourList.get(i).getCommision());
            isUse = String.valueOf(paymentTourList.get(i).getIsUse());
            description = String.valueOf(paymentTourList.get(i).getDetail());
            String newrow = "";
            
            if(!"null".equalsIgnoreCase(paymentTourList.get(i).getSupcode()) ){
                receiveFrom = paymentTourList.get(i).getSupcode();
            }
            if(!"null".equalsIgnoreCase(paymentTourList.get(i).getSupname())){
                receiveName = paymentTourList.get(i).getSupname();
            }
            if(!"null".equalsIgnoreCase(paymentTourList.get(i).getSupaddress()) ){
                receiveAddress = paymentTourList.get(i).getSupaddress();
            }
            
            MDefaultData mDefaultData = getMDefaultDataFromType("vat");
            vat = mDefaultData.getValue();
            
            if ("U".equals(isUse)) {
                newrow += "<tr>"
                        + "<td class='text-center'>" + No + "</td>"
//                        + "<td>" + airline + "</td>"
                        + "<td class='money'>" + commission + "</td>"
                        + "<td class='text-center'>" + isUse + "</td>"
                        + "<td><center><span class='glyphicon glyphicon-plus disable'></span></center></td>"
                        + "</tr>";
            } else if ("N".equals(isUse)) {
                newrow += "<tr>"
                        + "<td class='text-center'>" + No + "</td>"
//                        + "<td>" + airline + "</td>"
                        + "<td class='money'>" + commission + "</td>"
                        + "<td class='text-center'>" + isUse + "</td>"
                        + "<td><center><a href=\"#/com\"><span onclick=\"addProduct('" + product + "','" + description + "','','','1','" + vat + "','" + commission + "','" + currency + "','','','','" + airline + "','4','" + description + "','" + payNo + "','" + paymentTourId + "','" + receiveFrom + "','" + receiveName + "','" + receiveAddress + "')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                        + "</tr>";
            }
            html.append(newrow);
        }

        return html.toString();
    }
    
    private String buildTaxInvoiceListHTML(Invoice invoice) {
        StringBuffer html = new StringBuffer();
        List<InvoiceDetail> invoiceDetaillList = new ArrayList<InvoiceDetail>(invoice.getInvoiceDetails());
        String receiveTaxInvTo = invoice.getInvTo();
        String receiveInvToName = invoice.getInvName();
        String receiveInvToAddress = invoice.getInvAddress();
        String receiveARCode = invoice.getArcode();
        String invDetailId = "";
        String product = "";
        String description = "";
        BigDecimal cost = new BigDecimal(0);
        String curCost = "";
        BigDecimal amount = new BigDecimal(0);
        String curAmount = "";
        String isVat = "";
        String refNo = "";
        BigDecimal vat = new BigDecimal(0);

        UtilityFunction utilty = new UtilityFunction();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        String receiveInvToDate = dateFormat.format(cal.getTime());

        int row = 1;
        if (invoiceDetaillList == null || invoiceDetaillList.size() == 0) {
            String newrow = "";
            newrow += "<tr>"
                    + "<input type='hidden' name='receiveTaxInvTo' id='receiveTaxInvTo' value='" + receiveTaxInvTo + "'>"
                    + "<input type='hidden' name='receiveInvToName' id='receiveInvToName' value='" + receiveInvToName + "'>"
                    + "<input type='hidden' name='receiveInvToAddress' id='receiveInvToAddress' value='" + receiveInvToAddress + "'>"
                    + "<input type='hidden' name='receiveARCode' id='receiveARCode' value='" + receiveARCode + "'>"
                    + "<input type='hidden' name='receiveInvToDate' id='receiveInvToDate' value='" + receiveInvToDate + "'></tr>";
            return html.toString();
        }
        for (int i = 0; i < invoiceDetaillList.size(); i++) {
            InvoiceDetail invoiceDetail = new InvoiceDetail();
            BigDecimal costInvoice = new BigDecimal(0);
            BigDecimal amountInvoice = new BigDecimal(0);
            invoiceDetail = invoiceDetaillList.get(i);
            invDetailId = invoiceDetail.getId();
            product = invoiceDetail.getMbillType().getName();
            description = invoiceDetail.getDescription();
            curCost = invoiceDetail.getCurCost();
            curAmount = invoiceDetail.getCurAmount();
            isVat = String.valueOf(invoiceDetail.getIsVat());
            
            if (invoiceDetail.getCost() != null) {
                costInvoice = invoiceDetail.getCost();
            } else {
                costInvoice = new BigDecimal(0);
            }
            if (invoiceDetail.getAmount() != null) {
                amountInvoice = invoiceDetail.getAmount();
            } else {
                amountInvoice = new BigDecimal(0);
            }
            if (invoiceDetail.getVat()!= null) {
                vat = invoiceDetail.getVat();
            } else {
                vat = new BigDecimal(0);
            }
            
            
            if(invoiceDetail.getBillableDesc() != null){
                refNo = invoiceDetail.getBillableDesc().getBillable().getMaster().getReferenceNo();
            } else {
                refNo = "";
            }

            BigDecimal[] value = checkTaxInvoiceDetail(invDetailId);
            BigDecimal costTemp = value[0];
            BigDecimal amountTemp = value[1];
            cost = costInvoice.subtract(costTemp);
            amount = amountInvoice.subtract(amountTemp);

            if (amount.compareTo(BigDecimal.ZERO) != 0) {
                String newrow = "";
                newrow += "<tr>"
                        + "<input type='hidden' name='receiveTaxInvTo' id='receiveTaxInvTo' value='" + receiveTaxInvTo + "'>"
                        + "<input type='hidden' name='receiveInvToName' id='receiveInvToName' value='" + receiveInvToName + "'>"
                        + "<input type='hidden' name='receiveInvToAddress' id='receiveInvToAddress' value='" + receiveInvToAddress + "'>"
                        + "<input type='hidden' name='receiveARCode' id='receiveARCode' value='" + receiveARCode + "'>"
                        + "<input type='hidden' name='receiveInvToDate' id='receiveInvToDate' value='" + receiveInvToDate + "'>"
                        + "<input type='hidden' name='invoiceId' id='invoiceId' value='" + invoice.getId() + "'>"
                        + "<input type='hidden' name='invoiceId" + row + "' id='invoiceId" + row + "' value='" + invDetailId + "'>"
                        + "<td class='text-center'>" + product + "</td>"
                        + "<td>" + description + "</td>"
                        + "<td class='money' style=\"text-align:right;\">" + cost + "</td>"
                        + "<td style=\"text-align:center;\">" + curCost + "</td>"
                        + "<td class='money' style=\"text-align:right;\">" + amount + "</td>"
                        + "<td style=\"text-align:center;\">" + curAmount + "</td>"
                        + "<td><center><a href=\"\"><span onclick=\"AddProduct('"+invDetailId+"','"+product+"','"+description+"','"+cost+"','"+curCost+"','"+amount+"','"+curAmount+"','"+isVat+"','"+refNo+"','"+vat+"')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                        + "</tr>";
                    html.append(newrow);
            }
            row++;
        }
        return html.toString();
    }
    
    private String buildPaymentOutboundViewHTML(List<BookingOutboundView> bookingOutboundViewList) {
        StringBuffer html = new StringBuffer();      
        int no = 1;
        String refNo = "";
        String type = "";
        String description = "";
        String billType = "";
        BigDecimal cost = new BigDecimal(0);
        String cur = "";
        String bookId = "";
                
        for (int i = 0; i < bookingOutboundViewList.size(); i++) {
            BookingOutboundView bookingOutboundView = new BookingOutboundView();
            bookingOutboundView = bookingOutboundViewList.get(i);
            refNo = bookingOutboundView.getRefNo();
            type = bookingOutboundView.getType();
            description = bookingOutboundView.getDescription();
            billType = bookingOutboundView.getBilltype();
            cost = (!"".equalsIgnoreCase(bookingOutboundView.getCost()) ? new BigDecimal(bookingOutboundView.getCost()) : new BigDecimal(0));
            cur = bookingOutboundView.getCur();
            bookId = bookingOutboundView.getBookid();
                       
            String newrow = "";              
            newrow += "<tr>"
                    + "<td class='text-center'>" + no + "</td>"
                    + "<td class='text-center'>" + type + "</td>"
                    + "<td>" + description + "</td>"
                    + "<td id='mCost" + no + "' class='text-right'>" + cost + "</td>"
                    + "<td class='text-center'>" + cur + "</td>"
                    + "<td><center><a href=\"#/ref\"><span onclick=\"addRefNo('" + refNo + "','" + type + "','" + description + "','" + billType + "','" + cost + "','" + cur + "','" + bookId + "')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                    + "</tr>";
            html.append(newrow);
            no++;
        } 
        return html.toString();
    }
    
    public String buildBillableListTaxHTML(Billable billable, Invoice invoice) {
        StringBuffer html = new StringBuffer();
        List<BillableDesc> billableDescs = new ArrayList<BillableDesc>(billable.getBillableDescs());
        String receiveTaxInvTo = invoice.getInvTo();
        String receiveInvToName = invoice.getInvName();
        String receiveInvToAddress = invoice.getInvAddress();
        String receiveARCode = invoice.getArcode();
        String invoiceDetailId = invoice.getId();
        String description = "";
        String curcost = "";
        String product = "";
        String curamount = "";
        String isVat = "";
        String vat = "";
        String billableDescId = "";
        BigDecimal amount = new BigDecimal(0);
        BigDecimal cost = new BigDecimal(0);
        BigDecimal amountinvoice = new BigDecimal(0);
        BigDecimal costinvoice = new BigDecimal(0);       
        int No = 1;

//        String displaydescription = "";
        String refItemId = "";
        String billTypeName = "";
//        String displaydesTemp = ""; 

//        String mAccPay = "";
        String refNo = billable.getMaster().getReferenceNo();
//        if (billable.getMAccpay() != null) {
//            mAccPay = billable.getMAccpay().getId();
//        }
        if (billableDescs == null || billableDescs.size() == 0) {
            String newrow = "";
            newrow += "<tr>"
                    + "<input type='hidden' name='receiveTaxInvTo' id='receiveTaxInvTo' value='" + receiveTaxInvTo + "'>"
                    + "<input type='hidden' name='receiveInvToName' id='receiveInvToName' value='" + receiveInvToName + "'>"
                    + "<input type='hidden' name='receiveInvToAddress' id='receiveInvToAddress' value='" + receiveInvToAddress + "'>"
                    + "<input type='hidden' name='receiveARCode' id='receiveARCode' value='" + receiveARCode + "'>"
                    + "</tr>";
            html.append(newrow);
            return html.toString();
        }
        for (int i = 0; i < billableDescs.size(); i++) {
            billableDescId = billableDescs.get(i).getId();
            description = billableDescs.get(i).getDetail();
            BigDecimal amounttemp = new BigDecimal(billableDescs.get(i).getPrice());
            amountinvoice = amounttemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            curcost = (billableDescs.get(i).getCurCost() == null ? "" : billableDescs.get(i).getCurCost());
            if (billableDescs.get(i).getMBilltype() != null) {
                product = billableDescs.get(i).getMBilltype().getId();
                billTypeName = billableDescs.get(i).getMBilltype().getName();
            }

            BigDecimal costtemp = new BigDecimal(billableDescs.get(i).getCost());
            costinvoice = costtemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);

            curamount = (billableDescs.get(i).getCurrency() == null ? "" : billableDescs.get(i).getCurrency());

            BigDecimal[] value = checkTaxInvoiceDetailFromBilldescId(invoiceDetailId);
            BigDecimal costTemp = value[0];
            BigDecimal amountTemp = value[1];
            amount = amountinvoice.subtract(amountTemp);
            cost = costinvoice.subtract(costTemp);
            System.out.println(" amount =  " + amountinvoice + "-" + amountTemp + " = " + amount);
            System.out.println(" cost =  " + costinvoice + "-" + costTemp + " = " + cost);

            refItemId = billableDescs.get(i).getRefItemId();

            String displaydescription = "";
            String displaydesTemp = "";
            
            BigDecimal exrate = new BigDecimal(0);
            BigDecimal profit = new BigDecimal(0);
            exrate = billableDescs.get(i).getExRate();           
            if((curcost.equalsIgnoreCase(curamount)) && (!"".equalsIgnoreCase(curcost)) && (!"".equalsIgnoreCase(curamount))){
                profit = amount.subtract(cost);
            } else {
                if(exrate == null){
                    exrate = new BigDecimal(0);
                }
                profit = amount.subtract(cost.multiply(exrate)).setScale(2, RoundingMode.HALF_UP);
            }

//            if ("1".equals(product)) {
//                displaydescription = billTypeName;
//            } else if ("2".equals(product) || "8".equals(product)) {
//                if (!"".equals(refItemId)) {
//                    displaydescription += billTypeName + " #-- ";
//                    displaydesTemp = billableDao.getDescriptionInvoiceOthersFromRefId(refItemId);
//                    if(displaydesTemp != null && !"".equalsIgnoreCase(displaydesTemp)){
//                    String[] parts = displaydesTemp.split("\\|");
//                        displaydescription += parts[4] + " : " + parts[5];
//                    }
//                }
//            } else if ("3".equals(product)) {
//                displaydescription = billTypeName;
//            } else if ("4".equals(product)) {
//                displaydescription = billTypeName;
//            } else if ("6".equals(product)) {
//                if (!"".equals(refItemId)) {
//                    displaydescription += billTypeName + " ";
//                    displaydesTemp = billableDao.getDescriptionInvoiceDayTourFromRefId(refItemId);
//                    if(displaydesTemp != null && !"".equalsIgnoreCase(displaydesTemp)){
//                        String[] parts = displaydesTemp.split("\\|");
//                        displaydescription += parts[5] + " : " + parts[6];
//                    }
//                }
//            }
//
//            System.out.println("displaydescription" + displaydescription);

            if (amount.compareTo(BigDecimal.ZERO) != 0) {
                String newrow = "";              
                newrow += "<tr>"
                        + "<input type='hidden' name='receiveTaxInvTo' id='receiveTaxInvTo' value='" + receiveTaxInvTo + "'>"
                        + "<input type='hidden' name='receiveInvToName' id='receiveInvToName' value='" + receiveInvToName + "'>"
                        + "<input type='hidden' name='receiveInvToAddress' id='receiveInvToAddress' value='" + receiveInvToAddress + "'>"
                        + "<input type='hidden' name='receiveARCode' id='receiveARCode' value='" + receiveARCode + "'>"
                        + "<td class='text-center'>" + No + "</td>"
                        + "<td>" + description + "</td>"
                        + "<td class='text-right money'>" + cost + "</td>"
                        + "<td class='text-center'>" + curcost + "</td>"
                        + "<td class='text-right money'>" + amount + "</td>"
                        + "<td class='text-center'>" + curamount + "</td>"
                        + "<td class='text-right money3'>" + ("0".equalsIgnoreCase(String.valueOf(exrate)) ? "" : exrate) + "</td>"
                        + "<td class='text-right money'>" + profit + "</td>"
                        + "<td><center><a href=\"#/ref\"><span onclick=\"AddRefNo('" + product + "','" + description + "','" + cost + "','" + curcost + "','" + profit + "','" + curamount + "','" + invoiceDetailId + "','" + displaydescription + "','" + refNo + "')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                        + "</tr>";
                html.append(newrow);
                No++;
            } else {
                String newrow = "";
                newrow += "<tr>"
                        + "<input type='hidden' name='receiveTaxInvTo' id='receiveTaxInvTo' value='" + receiveTaxInvTo + "'>"
                        + "<input type='hidden' name='receiveInvToName' id='receiveInvToName' value='" + receiveInvToName + "'>"
                        + "<input type='hidden' name='receiveInvToAddress' id='receiveInvToAddress' value='" + receiveInvToAddress + "'>"
                        + "<input type='hidden' name='receiveARCode' id='receiveARCode' value='" + receiveARCode + "'>"
                        + "</tr>";
                html.append(newrow);
            }
        }
        return html.toString();
    }

    public String buildInvoiceListHTML(Invoice invoice) {
        StringBuffer html = new StringBuffer();
        List<InvoiceDetail> invoiceDetaill = new ArrayList<InvoiceDetail>(invoice.getInvoiceDetails());
        String invId = "";
        String description = "";
        BigDecimal amount = new BigDecimal(0);
        BigDecimal cost = new BigDecimal(0);
        BigDecimal amountinvoice = new BigDecimal(0);
        BigDecimal costinvoice = new BigDecimal(0);
        BigDecimal amountlocalinvoice = new BigDecimal(0);
        String currency = "";
        String product = "";
        String cur = "";
        String isVat = "";
        String vat = "";
        String refItemId = "";
        String billTypeName = "";

        int No = 1;
        String receiveFrom = invoice.getInvTo();
        String receiveName = invoice.getInvName();
        String receiveAddress = invoice.getInvAddress();
        String arcode = invoice.getArcode();
        String invNo = invoice.getInvNo();
        System.out.println("invoiceDetaill.size() " + String.valueOf(invoiceDetaill.size()));
        if (invoiceDetaill == null || invoiceDetaill.size() == 0) {
            String newrow = "";
            newrow += "<tr><input type='hidden' name='receiveFromInvoice' id='receiveFromInvoice' value='" + receiveFrom + "'>"
                    + "<input type='hidden' name='receiveNameInvoice' id='receiveNameInvoice' value='" + receiveName + "'>"
                    + "<input type='hidden' name='receiveAddressInvoice' id='receiveAddressInvoice' value='" + receiveAddress + "'>"
                    + "<input type='hidden' name='arcodeInvoice' id='arcodeInvoice' value='" + arcode + "'></tr>";
            html.append(newrow);
            return html.toString();
        }
        for (int i = 0; i < invoiceDetaill.size(); i++) {
            invId = invoiceDetaill.get(i).getId();
            description = invoiceDetaill.get(i).getDescription();
            if(invoiceDetaill.get(i).getAmount() != null){
                amountinvoice = invoiceDetaill.get(i).getAmount().compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : invoiceDetaill.get(i).getAmount();
            }
            if(invoiceDetaill.get(i).getAmountLocal()!= null){
                amountlocalinvoice = invoiceDetaill.get(i).getAmountLocal().compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : invoiceDetaill.get(i).getAmountLocal();
            }
            currency = "THB";
            if (invoiceDetaill.get(i).getMbillType() != null) {
                product = invoiceDetaill.get(i).getMbillType().getId();
                billTypeName = invoiceDetaill.get(i).getMbillType().getName();
            }
            if(invoiceDetaill.get(i).getCost() != null){
                costinvoice = invoiceDetaill.get(i).getCost().compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : invoiceDetaill.get(i).getCost();
            }
            cur = invoiceDetaill.get(i).getCurCost();
            isVat = String.valueOf(invoiceDetaill.get(i).getIsVat());
            vat = String.valueOf(invoiceDetaill.get(i).getVat());
            System.out.println(" invId " + invId);

            BigDecimal[] value = checkReceiptDetail(invId);
            BigDecimal costTemp = value[0];
            BigDecimal amountTemp = value[1];
            amount = amountlocalinvoice.subtract(amountTemp);
            cost = costinvoice.subtract(costTemp);
            System.out.println(" amount =  " + amountlocalinvoice + "-" + amountTemp + " = " + amount);
            System.out.println(" cost =  " + costinvoice + "-" + costTemp + " = " + cost);

            if (invoiceDetaill.get(i).getBillableDesc() != null) {
                refItemId = invoiceDetaill.get(i).getBillableDesc().getRefItemId();
            }

            String displaydescription = "";
            String displaydesTemp = "";
            if ("1".equals(product)) {                
                displaydescription = billTypeName;

            } else if ("2".equals(product) || "8".equals(product)) {
                if (!"".equals(refItemId)) {
                    displaydescription += billTypeName + " :- ";
                    displaydesTemp = billableDao.getDescriptionInvoiceOthersFromRefId(refItemId);
                    System.out.println("displaydesTemp" + displaydesTemp);
                    if(displaydesTemp != null && !"".equalsIgnoreCase(displaydesTemp)){
                        String[] parts = displaydesTemp.split("\\|");
                        displaydescription += parts[9] + " : " + parts[4] + " : " + parts[5];

                    }
                }else{
                    displaydescription = billTypeName + " : "+description;
                }
            } else if ("3".equals(product)) {
                displaydescription = billTypeName + " : "+description;
            } else if ("4".equals(product)) {
                displaydescription = billTypeName + " : "+description;
            } else if ("6".equals(product)) {
                if (!"".equals(refItemId)) {
                    displaydescription += billTypeName + " : ";
                    displaydesTemp = billableDao.getDescriptionInvoiceDayTourFromRefId(refItemId);
                    if(displaydesTemp != null && !"".equalsIgnoreCase(displaydesTemp)){
                        String[] parts = displaydesTemp.split("\\|");
                        displaydescription += parts[4] + " : " + parts[5] + " : " + parts[6];
                        System.out.println("displaydescription" + displaydescription);
                    }
                }else{
                   displaydescription = billTypeName + " : "+description;
                }
            } else {
                displaydescription = billTypeName + " : "+description;
            }

            if (amount.compareTo(BigDecimal.ZERO) != 0) {
                String newrow = "";
                newrow += "<tr>"
                        + "<input type='hidden' name='receiveFromInvoice' id='receiveFromInvoice' value='" + receiveFrom + "'>"
                        + "<input type='hidden' name='receiveNameInvoice' id='receiveNameInvoice' value='" + receiveName + "'>"
                        + "<input type='hidden' name='receiveAddressInvoice' id='receiveAddressInvoice' value='" + receiveAddress + "'>"
                        + "<input type='hidden' name='arcodeInvoice' id='arcodeInvoice' value='" + arcode + "'>"
                        + "<input type='hidden' name='invoiceId' id='invoiceId' value='" + invoice.getId() + "'>"
                        + "<td class='text-center'>" + No + "</td>"
                        + "<td>" + displaydescription + "</td>"
                        + "<td class='money'>" + amountinvoice + "</td>"
                        + "<td class='money'>" + amount + "</td>"
                        + "<td>" + currency + "</td>"
                        + "<td><center><a href=\"#/inv\"><span onclick=\"addProduct('" + product + "','" + description + "','" + cost + "','" + cur + "','" + isVat + "','" + vat + "','" + amount + "','" + currency + "','" + invId + "','','','','1','" + displaydescription + "','" + invNo + "','','','','' )\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                        + "</tr>";
                html.append(newrow);
                No++;
            } else {
                String newrow = "";
                newrow += "<tr><input type='hidden' name='receiveFromInvoice' id='receiveFromInvoice' value='" + receiveFrom + "'>"
                        + "<input type='hidden' name='receiveNameInvoice' id='receiveNameInvoice' value='" + receiveName + "'>"
                        + "<input type='hidden' name='receiveAddressInvoice' id='receiveAddressInvoice' value='" + receiveAddress + "'>"
                        + "<input type='hidden' name='arcodeInvoice' id='arcodeInvoice' value='" + arcode + "'></tr>"
                        ;
                html.append(newrow);
            }
        }
        return html.toString();
    }

    public String buildBillableListHTML(Billable billable) {
        StringBuffer html = new StringBuffer();
        List<BillableDesc> billableDescs = new ArrayList<BillableDesc>(billable.getBillableDescs());
        String description = "";
        String currency = "";
        String product = "";
        String cur = "";
        String isVat = "";
        String vat = "";
        String billableDescId = "";
        BigDecimal amount = new BigDecimal(0);
        BigDecimal cost = new BigDecimal(0);
        BigDecimal amountinvoice = new BigDecimal(0);
        BigDecimal costinvoice = new BigDecimal(0);
        int No = 1;

//        String displaydescription = "";
        String refItemId = "";
        String billTypeName = "";
//        String displaydesTemp = ""; 

        String mAccPay = "";
        String receiveFrom = billable.getBillTo();
        String receiveName = billable.getBillName();
        String receiveAddress = billable.getBillAddress();
        String arcode = billable.getBillTo();
        String refNo = billable.getMaster().getReferenceNo();
        String leader = ""; 
        if(billable.getMaster().getCustomer() != null){
            if(billable.getMaster().getCustomer().getMInitialname()  != null){
                leader += billable.getMaster().getCustomer().getMInitialname().getName();
            }
            leader += " "+ billable.getMaster().getCustomer().getFirstName() + " " +  billable.getMaster().getCustomer().getLastName();
        }
       
        if (billable.getMAccpay() != null) {
            mAccPay = billable.getMAccpay().getId();
        }
        if (billableDescs == null || billableDescs.size() == 0) {
            String newrow = "";
            newrow += 
                    "<input type='hidden' name='masterBookType' id='masterBookType' value='" + billable.getMaster().getBookingType() + "'>"
                    + "<input type='hidden' name='receiveFromBillable' id='receiveFromBillable' value='" + receiveFrom + "'>"
                    + "<input type='hidden' name='receiveNameBillable' id='receiveNameBillable' value='" + receiveName + "'>"
                    + "<input type='hidden' name='receiveAddressBillable' id='receiveAddressBillable' value='" + receiveAddress + "'>"
                    + "<input type='hidden' name='arcodeBillable' id='arcodeBillable' value='" + arcode + "'>"
                    ;
            html.append(newrow);
            return html.toString();
        }
        for (int i = 0; i < billableDescs.size(); i++) {
            billableDescId = billableDescs.get(i).getId();
//            description = billableDescs.get(i).getDetail();
            BigDecimal amounttemp = new BigDecimal(billableDescs.get(i).getPrice());
            amountinvoice = amounttemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            currency = billableDescs.get(i).getCurrency() == null ? "" : billableDescs.get(i).getCurrency();
            if (billableDescs.get(i).getMBilltype() != null) {
                product = billableDescs.get(i).getMBilltype().getId();
                billTypeName = billableDescs.get(i).getMBilltype().getName();
            }
            BigDecimal costtemp = new BigDecimal(billableDescs.get(i).getCost());
            costinvoice = costtemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            cur = billableDescs.get(i).getCurCost() == null ? "" : billableDescs.get(i).getCurCost();

            BigDecimal[] value = checkInvoiceDetailFromBilldescId(billableDescId);
            BigDecimal costTemp = value[0];
            BigDecimal amountTemp = value[1];
            amount = amountinvoice.subtract(amountTemp);
            cost = costinvoice.subtract(costTemp);
            System.out.println(" amount =  " + amountinvoice + "-" + amountTemp + " = " + amount);
            System.out.println(" cost =  " + costinvoice + "-" + costTemp + " = " + cost);

            refItemId = billableDescs.get(i).getRefItemId();

            String displaydescription = "";
            String displaydesTemp = "";
            String displaydescriptionother = "";
            description = refNo + " " +leader +" " + billTypeName ;
            if ("1".equals(product)) {
                displaydescription = billTypeName;
            } else if ("2".equals(product) || "8".equals(product)) {
                if (!"".equals(refItemId)) {
                    displaydescription += billTypeName + " #-- ";
                    displaydescriptionother += billTypeName + " #-- ";
                    displaydesTemp = billableDao.getDescriptionInvoiceOthersFromRefId(refItemId);
                    if(displaydesTemp != null && !"".equalsIgnoreCase(displaydesTemp)){
                        String[] parts = displaydesTemp.split("\\|");
                        displaydescription += parts[4] + " : " + parts[5];
                        displaydescriptionother += parts[9] + " : " + parts[4] + " : " + parts[5];
                    }
                }
            } else if ("3".equals(product)) {
                displaydescription = billTypeName;
            } else if ("4".equals(product)) {
                displaydescription = billTypeName;
            } else if ("6".equals(product)) {
                if (!"".equals(refItemId)) {
                    displaydescription += billTypeName + " ";
                    displaydesTemp = billableDao.getDescriptionInvoiceDayTourFromRefId(refItemId);
                    if(displaydesTemp != null && !"".equalsIgnoreCase(displaydesTemp)){
                        String[] parts = displaydesTemp.split("\\|");
                        displaydescription += parts[4] + " : " + parts[6];
                    }
                }else{
                    displaydescription = billTypeName;
                }
            }else{
                displaydescription = billTypeName;
            }

            System.out.println("displaydescription" + displaydescription);
            
//            MDefaultData mDefaultData = getMDefaultDataFromType("vat");
//            vat = mDefaultData.getValue();
            
            if (amount.compareTo(BigDecimal.ZERO) != 0) {
                String newrow = "";
                newrow +=
                        "<tr>"
                        + "<input type='hidden' name='masterBookType' id='masterBookType' value='" + billable.getMaster().getBookingType() + "'>"
                        + "<input type='hidden' name='receiveFromBillable' id='receiveFromBillable' value='" + receiveFrom + "'>"
                        + "<input type='hidden' name='receiveNameBillable' id='receiveNameBillable' value='" + receiveName + "'>"
                        + "<input type='hidden' name='receiveAddressBillable' id='receiveAddressBillable' value='" + receiveAddress + "'>"
                        + "<input type='hidden' name='arcodeBillable' id='arcodeBillable' value='" + arcode + "'>"
                        + "<input type='hidden' name='mAccPayBillable' id='mAccPayBillable' value='" + mAccPay + "'>"
                        + "<td class='text-center'>" + No + "</td>";
                        if ("2".equals(product) || "8".equals(product)) {
                            newrow += "<td>" + displaydescriptionother + "</td>";
                        }else{
                            newrow += "<td>" + displaydescription + "</td>";
                        }
                newrow += "<td class='money'>" + amount + "</td>"
                        + "<td>" + currency + "</td>"
                        + "<td><center><a href=\"#/ref\"><span onclick=\"addProduct('" + product + "','" + description + "','" + cost + "','" + cur + "','','','" + amount + "','" + currency + "','','" + billableDescId + "','','','2','" + displaydescription + "','" + refNo + "','','','','')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                        + "</tr>";
                html.append(newrow);
                No++;
            } else {
                String newrow = "";
                newrow += "<tr>" 
                        + "<input type='hidden' name='masterBookType' id='masterBookType' value='" + billable.getMaster().getBookingType() + "'>"
                        + "<input type='hidden' name='receiveFromBillable' id='receiveFromBillable' value='" + receiveFrom + "'>"
                        + "<input type='hidden' name='receiveNameBillable' id='receiveNameBillable' value='" + receiveName + "'>"
                        + "<input type='hidden' name='receiveAddressBillable' id='receiveAddressBillable' value='" + receiveAddress + "'>"
                        + "<input type='hidden' name='arcodeBillable' id='arcodeBillable' value='" + arcode + "'>"
                        + "<input type='hidden' name='mAccPayBillable' id='mAccPayBillable' value='" + mAccPay + "'>"
                        + "</tr>";
                        
                html.append(newrow);
            }
        }
        return html.toString();
    }        

    public String buildPassengerListHTML(List<Customer> passList) {
        String passenger = "";
        String MInitialname = "";
        String MInitialID = "";
        for (int i = 0; i < passList.size(); i++) {
            Customer cus = passList.get(i);
            if (cus.getMInitialname() != null) {
                MInitialname = cus.getMInitialname().getName();
                MInitialID = cus.getMInitialname().getId();
            }

            passenger += "<tr>"
                    + "<td class='customer-id hidden'>" + cus.getId() + "</td>"
                    + "<td class='customer-code '>" + cus.getCode() + "</td>"
                    + "<td class='customer-initial '>" + MInitialname + "</td>"
                    + "<td class='customer-initialId hidden'>" + MInitialID + "</td>"
                    + "<td class='customer-lastname '>" + cus.getLastName() + "</td>"
                    + "<td class='customer-firstname '>" + cus.getFirstName() + "</td>"
                    + "<td class='customer-sex hidden'>" + (cus.getSex() == null ? "" : cus.getSex()) + "</td>"
                    + "<td class='customer-address hidden'>" + (cus.getAddress() == null ? "" : cus.getAddress()) + "</td>"
                    + "<td class='customer-tel hidden'>" + (cus.getTel() == null ? "" : cus.getTel()) + "</td>"
                    + "<td class='customer-phone hidden'>" + (cus.getPhone() == null ? "" : cus.getPhone()) + "</td>"
                    + "<td class='customer-postal hidden'>" + (cus.getPostalCode() == null ? "" : cus.getPostalCode()) + "</td>"
                    + "<td class='customer-email hidden'>" + (cus.getEmail() == null ? "" : cus.getEmail()) + "</td>"
                    + "<td class='customer-japanfirstname hidden'>" + cus.getFirstNameJapan() + "</td>"
                    + "<td class='customer-japanlastname hidden'>" + cus.getLastNameJapan() + "</td>"
                    + "<td class='customer-remark hidden'>" + (cus.getRemark() == null ? "" : cus.getRemark()) + "</td>"
                    + "<td class='customer-passportno hidden'>" + (cus.getPassportNo() == null ? "" : cus.getPassportNo()) + "</td>"
                    + "</tr>";

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
    
    public JSONArray buildTourListJSON(List<Daytour> listDaytour) {
        JSONArray record = new JSONArray();
        for (int i = 0; i < listDaytour.size(); i++) {
            Daytour daytour = listDaytour.get(i);
            JSONObject field = new JSONObject();
            field.put("id", daytour.getId());
            field.put("code", daytour.getCode());
            field.put("name", daytour.getName());
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
        int ad = 0;
        int ch = 0;
        int inf = 0;
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
                ad += Integer.parseInt(passenger[0]);
                ch += Integer.parseInt(passenger[1]);
                inf += Integer.parseInt(passenger[2]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        result += "<tr>"
                + "<td></td>"
                + "<td></td>"
                + "<td></td>"
                + "<td></td>"
                + "<td style='text-align: right'><u>Total</u></td>"
                + "<td>" + String.valueOf(ad) + "</td>"
                + "<td>" + String.valueOf(ch) + "</td>"
                + "<td>" + String.valueOf(inf) + "</td>"
                + "<td></td>"
                + "<td></td>"
                + "</tr>";
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

    public String getListInvoice(Billable bill, String invType) {
        UtilityFunction utility = new UtilityFunction();
        String result = "";
        String term = "";
        if (bill.getMAccterm() != null) {
            term = "" + bill.getMAccterm().getId();
        }
        String dateDue = "";
        if (bill.getMAccterm() != null) {
            if (bill.getMAccterm().getValue() != null) {
                Date ff = new Date();
                System.out.println(ff);
                ff.setDate(ff.getDate() + bill.getMAccterm().getValue());
                System.out.println("Value Term : " + bill.getMAccterm().getValue());
                System.out.println("Dueeeeeeeee  Date : " + ff);
                dateDue = utility.convertDateToString(ff);
            }
        }

        result += bill.getMaster().getBookingType() + "||";
        result += bill.getBillTo() + "//" + bill.getBillName() + "//" + bill.getBillAddress() + "//" + term
                + "//" + bill.getMaster().getStaff().getId() + "//" + bill.getMaster().getStaff().getName() + "//" + bill.getMaster().getStaff().getUsername() + "//" + dateDue + "//" + "||";
        List<BillableDesc> billdeescList = bill.getBillableDescs();
        int count = 0;
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        if ("Air Ticket".equals(invType)) {
            for (int i = 0; i < billdeescList.size(); i++) {
                if (billdeescList.get(i).getMBilltype().getName().equals(invType) || billdeescList.get(i).getMBilltype().getName().equals("Air Additional")) {
                    BigDecimal[] valueresult = invoicedao.checkBillDescInuse(billdeescList.get(i).getId(), String.valueOf(billdeescList.get(i).getCost()), String.valueOf(billdeescList.get(i).getPrice()));
                    System.out.println("valueresult[1] : " + valueresult[1]);
                    if (valueresult[1].compareTo(BigDecimal.ZERO) != 0) {

                        System.out.println("11valueresult[1] : " + valueresult[1]);
                        String cost = numberFormat.format(valueresult[0]);
                        String price = numberFormat.format(valueresult[1]);
                        System.out.println("Cost And Price : " + cost + " && " + price);
                        if (billdeescList.get(i).getCurrency() == null) {
                            billdeescList.get(i).setCurrency("");
                        }
                        if (billdeescList.get(i).getCurCost() == null) {
                            billdeescList.get(i).setCurCost("");
                        }
                        if (billdeescList.get(i).getDetail() == null) {
                            billdeescList.get(i).setDetail("");
                        }
                        result += "<tr>"
                                + "<td align=\"center\">" + (count + 1) + "</td>"
                                + "<td class=\"hidden\"><input type=\"hidden\" id=\"invoiceIdSearch" + (count + 1) + "\" name=\"invoiceIdSearch" + (count + 1) + "\" value=" + billdeescList.get(i).getId() + "></td>"
                                + "<td class=\"hidden\"><input type=\"hidden\" id=\"invoiceIdType" + (count + 1) + "\" name=\"invoiceIdType" + (count + 1) + "\" value=" + billdeescList.get(i).getMBilltype().getId() + "></td>"
                                + "<td>" + billdeescList.get(i).getMBilltype().getName() + "</td>"
                                + "<td>" + billdeescList.get(i).getDetail() + "</td>"
                                + "<td align=\"right\">" + cost + "</td>"
                                + "<td align=\"center\">" + billdeescList.get(i).getCurCost() + "</td>"
                                + "<td align=\"right\">" + price + "</td>"
                                + "<td align=\"center\">" + billdeescList.get(i).getCurrency() + "</td>"
                                + "<td align=\"center\"><center><a href=\"\" onclick=\"addInvoiceDetail(" + (count + 1) + ")\"><span class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                                + "<td class=\"hidden\"><input type=\"hidden\" id=\"RefItemId" + (count + 1) + "\" name=\"RefItemId" + (count + 1) + "\" value=" + billdeescList.get(i).getRefItemId() + "></td>"
                                + "</tr>";
                        count += 1;
                    }
                }
            }
        } else {
            for (int i = 0; i < billdeescList.size(); i++) {
                if (!billdeescList.get(i).getMBilltype().getName().equals("Air Ticket") && !billdeescList.get(i).getMBilltype().getName().equals("Air Additional")) {
                    BigDecimal[] valueresult = invoicedao.checkBillDescInuse(billdeescList.get(i).getId(), String.valueOf(billdeescList.get(i).getCost()), String.valueOf(billdeescList.get(i).getPrice()));
                    System.out.println("valueresult[1] : " + valueresult[1]);
  
                    if (valueresult[1].compareTo(BigDecimal.ZERO) != 0 && valueresult[1].compareTo(BigDecimal.ZERO) != -1) {

                        System.out.println("11valueresult[1] : " + valueresult[1]);
                        String cost1 = numberFormat.format(valueresult[0]);
                        String price1 = numberFormat.format(valueresult[1]);
                        System.out.println("Cost And Price : " + cost1 + " && " + price1);
                        if (billdeescList.get(i).getCurrency() == null) {
                            billdeescList.get(i).setCurrency("");
                        }
                        if (billdeescList.get(i).getCurCost() == null) {
                            billdeescList.get(i).setCurCost("");
                        }
                        if (billdeescList.get(i).getDetail() == null) {
                            billdeescList.get(i).setDetail("");
                        }
                        result += "<tr>"
                                + "<td align=\"center\">" + (count + 1) + "</td>"
                                + "<td class=\"hidden\"><input type=\"hidden\" id=\"invoiceIdSearch" + (count + 1) + "\" name=\"invoiceIdSearch" + (count + 1) + "\" value=" + billdeescList.get(i).getId() + "></td>"
                                + "<td class=\"hidden\"><input type=\"hidden\" id=\"invoiceIdType" + (count + 1) + "\" name=\"invoiceIdType" + (count + 1) + "\" value=" + billdeescList.get(i).getMBilltype().getId() + "></td>"
                                + "<td>" + billdeescList.get(i).getMBilltype().getName() + "</td>"
                                + "<td>" + billdeescList.get(i).getDetail() + "</td>"
                                + "<td align=\"right\">" + cost1 + "</td>"
                                + "<td align=\"center\">" + billdeescList.get(i).getCurCost() + "</td>"
                                + "<td align=\"right\">" + price1 + "</td>"
                                + "<td align=\"center\">" + billdeescList.get(i).getCurrency() + "</td>"
                                + "<td align=\"center\"><center><a href=\"\" onclick=\"addInvoiceDetail(" + (count + 1) + ")\"><span class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                                + "<td class=\"hidden\"><input type=\"hidden\" id=\"RefItemId" + (count + 1) + "\" name=\"RefItemId" + (count + 1) + "\" value=" + billdeescList.get(i).getRefItemId() + "></td>"
                                + "</tr>";
                        count += 1;
                    }
                }
            }
        }
        return result;
    }

    public BigDecimal[] checkReceiptDetail(String invDetailId) {
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

    private BigDecimal[] checkTaxInvoiceDetail(String invDetailId) {
        BigDecimal[] value = new BigDecimal[2];
        BigDecimal resultAmount = new BigDecimal(0);
        BigDecimal resultCost = new BigDecimal(0);
        List<TaxInvoiceDetail> taxInvoiceDetailList = taxInvoiceDao.getTaxInvoiceDetailFromInvDetailId(invDetailId);
        if (taxInvoiceDetailList == null || taxInvoiceDetailList.size() == 0) {
            value[0] = resultCost;
            value[1] = resultAmount;
            return value;
        }
        for (int i = 0; i < taxInvoiceDetailList.size(); i++) {
            BigDecimal amount = new BigDecimal(0);
            BigDecimal cost = new BigDecimal(0);
            if ((taxInvoiceDetailList.get(i).getCost() != null)) {
                cost = taxInvoiceDetailList.get(i).getCost();
            } else {
                cost = new BigDecimal(0);
            }
            if ((taxInvoiceDetailList.get(i).getAmount() != null)) {
                amount = taxInvoiceDetailList.get(i).getAmount();
            } else {
                amount = new BigDecimal(0);
            }
            resultCost = resultCost.add(cost);
            resultAmount = resultAmount.add(amount);
        }

        value[0] = resultCost;
        value[1] = resultAmount;
        return value;
    }

    public BigDecimal[] checkInvoiceDetailFromBilldescId(String billdescId) {
        BigDecimal[] value = new BigDecimal[2];
        BigDecimal amount = new BigDecimal(0);
        BigDecimal cost = new BigDecimal(0);
        BigDecimal resultAmount = new BigDecimal(0);
        BigDecimal resultCost = new BigDecimal(0);

        List<InvoiceDetail> invoiceDetailList = invoicedao.getInvoiceDetailFromBillDescId(billdescId);
        if (invoiceDetailList == null || invoiceDetailList.size() == 0) {
            value[0] = resultCost;
            value[1] = resultAmount;
            return value;
        }
        for (int i = 0; i < invoiceDetailList.size(); i++) {
            cost = invoiceDetailList.get(i).getCost();
            amount = invoiceDetailList.get(i).getAmount();
            resultCost = resultCost.add(cost);
            resultAmount = resultAmount.add(amount);
        }

        value[0] = resultCost;
        value[1] = resultAmount;
        return value;
    }
    
    public BigDecimal[] checkTaxInvoiceDetailFromBilldescId(String invoiceDetailId) {
        BigDecimal[] value = new BigDecimal[2];
        BigDecimal amount = new BigDecimal(0);
        BigDecimal cost = new BigDecimal(0);
        BigDecimal resultAmount = new BigDecimal(0);
        BigDecimal resultCost = new BigDecimal(0);

        List<TaxInvoiceDetail> taxInvoiceDetailList = taxInvoiceDao.getTaxInvoiceDetailFromBillDescId(invoiceDetailId);
        if (taxInvoiceDetailList == null || taxInvoiceDetailList.size() == 0) {
            value[0] = resultCost;
            value[1] = resultAmount;
            return value;
        }
        for (int i = 0; i < taxInvoiceDetailList.size(); i++) {
            cost = taxInvoiceDetailList.get(i).getCost();
            amount = taxInvoiceDetailList.get(i).getAmount();
            resultCost = resultCost.add(cost);
            resultAmount = resultAmount.add(amount);
        }

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

    private Map convertInvoiceToMap(TaxInvoice tax) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("taxId", tax.getId());
        map.put("taxNo", tax.getTaxNo());
        map.put("taxTo", tax.getTaxInvTo());
        map.put("taxName", tax.getTaxInvName());
        map.put("taxAddress", tax.getTaxInvAddr());
        UtilityFunction util = new UtilityFunction();
        map.put("taxDate", util.convertDateToString(tax.getCreateDate()));
        map.put("status", tax.getMFinanceItemstatus().getId());
        String invNo = "";
        List<Map<String, Object>> detailMapList = new ArrayList<Map<String, Object>>();
        Set set = new HashSet();
        for (Iterator detailList = tax.getTaxInvoiceDetails().iterator(); detailList.hasNext();) {
            TaxInvoiceDetail detail = (TaxInvoiceDetail) detailList.next();
            BigDecimal detailAmount = detail.getAmount();
            BigDecimal datailVat = new BigDecimal("0.00");
            if (detail.getVat() != null && !"".equals(detail.getVat())) {
                if (detail.getAmount() != null && !"".equals(detail.getAmount())) {
                    System.out.println("Vat : " + detail.getVat() + " Amount : " + detail.getAmount());
                    datailVat = detail.getAmount().multiply(detail.getVat()).divide(new BigDecimal("100.00"));
                }
            }
            if (detail.getInvoiceDetail() != null) {
                set.add(detail.getInvoiceDetail().getInvoice().getInvNo());
            }
            Map<String, Object> detailMap = new HashMap<String, Object>();
            detailMap.put("product", detail.getMbillType().getName());
            if(detail.getMaster() != null){
                detailMap.put("refNo", detail.getMaster().getReferenceNo());
            }
            
            detailMap.put("description", detail.getDescription());
            detailMap.put("amount", util.setFormatMoney(detail.getAmount()));
            detailMap.put("cur", detail.getCurAmount());
            detailMapList.add(detailMap);
        }
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {
            String next = (String) iterator.next();
            invNo += next;
            if (iterator.hasNext()) {
                invNo += ", ";
            }
        }
        map.put("detailList", detailMapList);
        map.put("taxAmount", tax.getAmountExcludeVat());
        map.put("taxDesc", invNo);
        return map;
    }

    public MFilghtDao getmFlightDao() {
        return mFlightDao;
    }

    public void setmFlightDao(MFilghtDao mFlightDao) {
        this.mFlightDao = mFlightDao;
    }

    public DefineVarDao getDefineVardao() {
        return defineVardao;
    }

    public void setDefineVardao(DefineVarDao defineVardao) {
        this.defineVardao = defineVardao;
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
}
