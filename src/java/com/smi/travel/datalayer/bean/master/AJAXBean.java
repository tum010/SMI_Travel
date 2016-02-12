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
import com.smi.travel.datalayer.dao.PaymentStockDao;
import com.smi.travel.datalayer.dao.PaymentWendytourDao;
import com.smi.travel.datalayer.dao.ProductDetailDao;
import com.smi.travel.datalayer.dao.ReceiptDao;
import com.smi.travel.datalayer.dao.ReceiveTableDao;
import com.smi.travel.datalayer.dao.RefundAirticketDao;
import com.smi.travel.datalayer.dao.TaxInvoiceDao;
import com.smi.travel.datalayer.dao.TicketFareAirlineDao;
import com.smi.travel.datalayer.dao.TransferJobDao;
import com.smi.travel.datalayer.entity.AdvanceReceive;
import com.smi.travel.datalayer.entity.AdvanceReceivePeriod;
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
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.PackageItinerary;
import com.smi.travel.datalayer.entity.PackagePrice;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.PaymentStock;
import com.smi.travel.datalayer.entity.PaymentStockItem;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.entity.ProductDetail;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.entity.TaxInvoice;
import com.smi.travel.datalayer.entity.TaxInvoiceDetail;
import com.smi.travel.datalayer.view.dao.BookingSummaryDao;
import com.smi.travel.datalayer.view.dao.CustomerAgentInfoDao;
import com.smi.travel.datalayer.view.dao.TicketAircommissionViewDao;
import com.smi.travel.datalayer.view.entity.AdvanceReceivePeriodView;
import com.smi.travel.datalayer.view.entity.BookSummary;
import com.smi.travel.datalayer.view.entity.BookingOutboundView;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.datalayer.view.entity.OtherBookingView;
import com.smi.travel.datalayer.view.entity.PaymentTourCommissionView;
import com.smi.travel.datalayer.view.entity.TicketAircommissionView;
import com.smi.travel.util.Mail;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
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
import javax.swing.text.Document;
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
    private static final String PAYMENTSTOCK = "PaymentStockServlet"; 
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
    private PaymentStockDao paymentStockDao;
    
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
                } else if (obj instanceof PaymentStockDao) {
                    paymentStockDao = (PaymentStockDao) obj;
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
//                Customer customer = new Customer();
//                String[] pathname = name.trim().split("/");
//                int filter = 0;
//                if (pathname.length == 1) {
//                    customer.setFirstName(pathname[0]);
//                    customer.setLastName(pathname[0]);
//                    customer.setCode(pathname[0]);
//                    filter = 1;
//                } else {
//                    filter = 0;
//                    customer.setFirstName(pathname[1]);
//                    customer.setLastName(pathname[0]);
//                    customer.setCode(pathname[0] + pathname[1]);
//                }
//                List<OtherBooking> summaryList = otherBookingDao.searchOtherBooking(name);
                List<OtherBookingView> summaryList = otherBookingDao.getListBookingAllView(name);                                             
                if (summaryList != null) {
                    for (int i = 0; i < summaryList.size(); i++) {

                        OtherBookingView otherBooking = summaryList.get(i);
//                            if (bookDetail.getDateTour() != null) {
//                                tourdate = bookDetail.getDateTour().toString();
//                            }
                        String refNo1 = otherBooking.getRefno().substring(0, 2);
                        String refNo2 = otherBooking.getRefno().substring(2, 6);
                        result += "<tr>"
//                                + "<td class=\"text-center\">" + otherBooking.getMaster().getReferenceNo().trim() + "</td>"
                                + "<td class=\"text-center\">" + refNo1+"-"+refNo2 + "</td>"
                                + "<td class=\"text-center\">" + (otherBooking.getOtherdate()!= null ? otherBooking.getOtherdate() : "") + "</td>"
                                + "<td class=\"text-left\">" + otherBooking.getLeader() + "</td>"
                                + "<td class=\"text-left\">" + otherBooking.getProduct() + "</td>"
                                + "<td class=\"text-center\">" + otherBooking.getStatus() + "</td>" 
                                + "<td class=\"text-center\"><a href='DaytourOperationOther.smi?InputRefNo="+otherBooking.getRefno()+ "&action=search'><span class='glyphicon glyphicon-check'></span></a></td>"
                                + "</tr>";
                    }
                }
                System.out.println(" result :: " +result);
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
            if ("getGuideCommission".equalsIgnoreCase(type)){
                String otherDate = map.get("otherDate").toString();
                String row = map.get("row").toString();
                String price = map.get("price").toString();
                System.out.println(" otherDate :: "+ otherDate);
                System.out.println(" row :: "+ row);
                System.out.println(" price :: "+ price);
                result = otherBookingDao.getGuideCommission(otherDate, row,price);
            }
            if ("getAgentCommission".equalsIgnoreCase(type)){
                String otherDate = map.get("otherDate").toString();
                String row = map.get("row").toString();
                String agentId = map.get("agentId").toString();
                String price = map.get("price").toString();
                System.out.println(" otherDate :: "+ otherDate);
                System.out.println(" row :: "+ row);
                System.out.println(" agentId :: "+ agentId);
                System.out.println(" price :: "+ price);
                result = otherBookingDao.getAgentCommission(otherDate, row, agentId,price);
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
            } else if ("getAutoListBilltoReceiveTable".equalsIgnoreCase(type)) {
                String name = map.get("name").toString();
                result = buildBillListJSON(customerAgentInfoDao.SearchListCustomerAgentInfoReceiveTable(name));
            } else if ("deleteInvoiceDetail".equalsIgnoreCase(type)) {
                String id = map.get("name").toString();
                result = invoicedao.DeleteInvoiceDetail(id);
            } else if("searchFlightService".equalsIgnoreCase(type)){
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
            } else if("getListBilltoOverdueSummary".equalsIgnoreCase(type)){
                String name = map.get("name").toString();
                List<CustomerAgentInfo> data = customerAgentInfoDao.SearchListCustomerAgentInfo(name);
                String tabledata = "";
                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        CustomerAgentInfo row = data.get(i);
                        tabledata += "<tr onclick=\"setBillValue('" + row.getBillTo() + "','" + ReplaceEnterKey(row.getBillName()) + "','" + ReplaceEnterKey(row.getAddress()) + "','" + row.getTerm() + "','" + row.getPay() + "');\">";
                        tabledata += "<td class='item-billto'>" + row.getBillTo() + "</td>";
                        tabledata += "<td class='item-name'>" + ReplaceEnterKey(row.getBillName()) + "</td>";
                        tabledata += "<td class='item-address'>" + ReplaceEnterKey(row.getAddress()) + "</td>";
                        tabledata += "<td class='item-tel'>" + row.getTel() + "</td>";
                        tabledata += "</tr>";
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
                    master.setDepartmentNo(masterlist.getDepartmentNo());
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
                String department = map.get("department").toString();
                Billable bill = billableDao.getBillableBooking(searchRefNo);
                result = getListInvoice(bill, invType, department);
            } else if ("searchInvoiceDescription".equalsIgnoreCase(type)) {
                String searchRefNo = map.get("refNo").toString();
                String typeId = map.get("typeId").toString();
                String typeName = billableDao.getMBillTypeName(typeId);
                if (typeName != null) {
                    result = "";
                    if("Air Ticket".equals(typeName)) {
                        result += billableDao.getDescriptionInvoiceAirTicket(searchRefNo,1);
                    } else if (("Others".equals(typeName)) || ("Coupon".equals(typeName))) {
                        result += billableDao.getDescriptionInvoiceOthers(searchRefNo,1);
                    } else if ("Land".equals(typeName)) {
                        result += billableDao.getDescriptionInvoiceLand(searchRefNo,1);
                    } else if ("Hotel".equals(typeName)) {
                        result += billableDao.getDescriptionInvoiceHotel(searchRefNo,1);
                    } else if ("Day Tour".equals(typeName)) {
                        result += billableDao.getDescriptionInvoiceDayTour(searchRefNo,1);
                    } else if ("Air Additional".equals(typeName)) {
                        System.out.println("result LL : " + result);;
                        result += billableDao.getDescriptionInvoiceAirAdditional(searchRefNo,1);
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
            }else if ("getTicketFareBooking".equalsIgnoreCase(type)) {
                String ticketNo = map.get("ticketNo").toString();
                String AirBooking = map.get("AirBooking").toString();
                System.out.println("ticketNo : "+ticketNo +" , AirBooking : "+AirBooking);
                HashMap<String, Object> ticketFare = ticketFareAirlineDao.getDetailTicketFareAirline(ticketNo,AirBooking);
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
                System.out.println(" invoice.getInvNo() ====  " + invoice.getInvNo());
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
            }else if ("searchAmountByBillDescId".equalsIgnoreCase(type)) {
                String billDescId = map.get("billDescId").toString();
                String receiptDetailId = map.get("receiptDetailId").toString();
                String recAmount = map.get("recAmount").toString();
                System.out.println(" billDescId :: " +billDescId);
                System.out.println(" receiptDetailId :: " +receiptDetailId);
                System.out.println(" recAmount :: " +recAmount);
                BillableDesc bill = billableDao.getBillableDescFromBillDescId(billDescId);
                if (bill == null) {
                    result = "null";
                } else {
                    result = checkInvoiceDetailFromBilldescIdAndRecDetailId(bill,billDescId,receiptDetailId,recAmount);
                }
            }else if ("searchAmountByPaymentId".equalsIgnoreCase(type)) {
                String paymentTourId = map.get("paymentTourId").toString();
                String recAmount = map.get("recAmount").toString();
                System.out.println(" paymentTourId :: " +paymentTourId);
                PaymentTourCommissionView ptcv = ticketAircommissionViewDao.getPaymentTourCommissionView(paymentTourId);
                if (ptcv == null) {
                    result = "null";
                } else {
                    BigDecimal receiptAmount = new BigDecimal(recAmount);
                    BigDecimal paymentTourAmount = ptcv.getCommision();
                    int resultcompare = paymentTourAmount.compareTo(receiptAmount);
                    if(resultcompare == 0){
                        result = "success";
                    }else if(resultcompare == 1){
                        result = "success";
                    }else if(resultcompare == -1){
                        result = "fail";
                    }
                }
            }else if ("searchAmountByInvDetailId".equalsIgnoreCase(type)) {
                String invDetailId = map.get("invDetailId").toString();
                String receiptDetailId = map.get("receiptDetailId").toString();
                String recAmount = map.get("recAmount").toString();
                System.out.println(" invDetailId :: " +invDetailId);
                
                result = receiptdao.checkAmountReceiptDetailFromInvDetailId(invDetailId,receiptDetailId,recAmount);

            }
        } else if (TAXINVOICE.equalsIgnoreCase(servletName)) {
            if ("searchInvoiceNo".equalsIgnoreCase(type)) {
                String invoiceNo = map.get("invoiceNo").toString();
                String department = map.get("department").toString();
                String invType = "";
                System.out.println("invoiceNo ::: " + invoiceNo);
                Invoice invoice = new Invoice();
                invoice = invoicedao.searchInvoiceForTaxInvoice(invoiceNo, department);
                if (invoice == null) {
                    result = "null";
                } else {
                    result = buildTaxInvoiceListHTML(invoice);
                }
            }else if("searchRefNo".equalsIgnoreCase(type)){
                String searchRefNo = map.get("refNo").toString();
                Billable bill = billableDao.getBillableBookingForTaxInvoice(searchRefNo);
//                if ("".equals(bill.getId()) || null == bill.getId()) {
//                    result = "null";
//                }
                if (bill != null) {
                    if ("".equals(bill.getId()) || null == bill.getId()) {
                        result = "null";
                    }else{
//                        BillableDesc billableDescTemp = new BillableDesc();
//                        billableDescTemp = (BillableDesc) bill.getBillableDescs().get(0);
//                        Invoice invoice = billableDao.getInvoiceForTaxInvoice(billableDescTemp.getId());                       
                        Invoice invoice = billableDao.getInvoiceForTaxInvoice(bill);
                        if ("".equals(invoice.getId()) || null == invoice.getId()){
                            result = "null";
                        } else {
                            if("O".equalsIgnoreCase(bill.getMaster().getBookingType())){
                                result = buildBillableListTaxHTML(bill,invoice);
                            } else {
                                result = bill.getMaster().getBookingType();
                            }
                        }
                    }
                }else{
                    result = "null";
                }  
               
            }else if("getTaxInvoice".equalsIgnoreCase(type)){
                String invoiceNo = map.get("invoiceNo").toString();
                String department = map.get("department").toString();
                String creditNoteId = map.get("id").toString();
                TaxInvoice taxInv = taxInvoiceDao.getTaxInvoiceByTaxNo(invoiceNo,department);
                JSONObject obj = new JSONObject(convertInvoiceToMap(taxInv,creditNoteId));
                result = obj.toJSONString();
            
            }else if("getTaxInvoiceAmountTotal".equalsIgnoreCase(type)){
                String invoiceNo = map.get("invoiceNo").toString();
                String department = map.get("department").toString();
                String id = map.get("id").toString();
                TaxInvoice taxInv = taxInvoiceDao.getTaxInvoiceByTaxNo(invoiceNo,department);
                if(taxInv != null){
                    BigDecimal amountTotal = taxInvoiceDao.getTaxInvoiceAmountTotal(taxInv,id);
                    if(amountTotal != null){
                        result = amountTotal;
                    }else{
                        result = "null";
                    }
                    
                }else{
                    result = "null";
                }                    
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
                String receiveFrom = map.get("receiveFrom").toString();
                String receiveTo = map.get("receiveTo").toString();        
                String fromDate = map.get("fromDate").toString();
                String toDate = map.get("toDate").toString();
                String periodDetail = map.get("periodDetail").toString();
                String department = map.get("department").toString();
                String vatType = map.get("vatType").toString();
                String check = "";
                if(!"".equalsIgnoreCase(periodId) && periodId != null){
                    check = receiveTableDao.checkReceivePeriod(periodId,fromDate,toDate,department,vatType);
                    if("success".equalsIgnoreCase(check)){
                        AdvanceReceivePeriodView advanceReceivePeriodView = new AdvanceReceivePeriodView();
                        advanceReceivePeriodView = receiveTableDao.getAdvanceReceivePeriodView(fromDate,toDate,department,vatType);
                        check = receiveTableDao.updateReceivePeriod(periodId,fromDate,toDate,vatType,periodDetail);                   
                        if("1".equalsIgnoreCase(check)){
                            AdvanceReceivePeriod advanceReceivePeriod = new AdvanceReceivePeriod();
                            advanceReceivePeriod = receiveTableDao.getReceivePeriod(fromDate,department,vatType);  
                            List<AdvanceReceivePeriod> advanceReceivePeriodList = receiveTableDao.getReceivePeriodList(department);
                            result =  buildAdvanceReceivePeriodListTaxHTML(advanceReceivePeriodList, advanceReceivePeriod, "update");                                                                                  
//                            result = buildAdvanceReceivePeriodViewListJSON(advanceReceivePeriod,advanceReceivePeriodView);
                        }else{
                            result = "fail";
                        }
                    }else{
                        result = "fail";
                    }    
                }else{
                    check = receiveTableDao.checkReceivePeriod(periodId,fromDate,toDate,department,vatType);
                    if("success".equalsIgnoreCase(check)){
                        AdvanceReceivePeriodView advanceReceivePeriodView = new AdvanceReceivePeriodView();
                        advanceReceivePeriodView = receiveTableDao.getAdvanceReceivePeriodView(fromDate,toDate,department,vatType);
                        check = receiveTableDao.saveReceivePeriod(periodId,fromDate,toDate,periodDetail,department,vatType,advanceReceivePeriodView);                   
                        if("success".equalsIgnoreCase(check)){
                            AdvanceReceivePeriod advanceReceivePeriod = new AdvanceReceivePeriod();
                            advanceReceivePeriod = receiveTableDao.getReceivePeriod(fromDate,department,vatType);
                            List<AdvanceReceivePeriod> advanceReceivePeriodList = receiveTableDao.getReceivePeriodList(department);
                            result =  buildAdvanceReceivePeriodListTaxHTML(advanceReceivePeriodList, advanceReceivePeriod, "save");                                                        
//                            result = buildAdvanceReceivePeriodViewListJSON(advanceReceivePeriod,advanceReceivePeriodView);
                        }
                    }else{
                        result = "fail";
                    }
                }                
            }else if("deletePeriodDate".equalsIgnoreCase(type)){
                String periodId = map.get("periodId").toString();
                String department = map.get("department").toString();
                String check = receiveTableDao.deleteReceivePeriod(periodId);
                if("success".equalsIgnoreCase(check)){
                   List<AdvanceReceivePeriod> advanceReceivePeriodList = receiveTableDao.getReceivePeriodList(department);
                   result =  buildAdvanceReceivePeriodListTaxHTML(advanceReceivePeriodList, null, "delete");
                }else{
                    result = "fail";
                }                
            }else if("compareReceiptSummary".equalsIgnoreCase(type)){
                UtilityFunction util = new UtilityFunction();
                String receiveFrom = map.get("receiveFrom").toString();
                String department = map.get("department").toString();
                String vatType = map.get("vatType").toString();
                String check = "";
                AdvanceReceivePeriod advanceReceivePeriod = new AdvanceReceivePeriod();
                advanceReceivePeriod = receiveTableDao.getReceivePeriod(receiveFrom,department,vatType);
                AdvanceReceivePeriodView advanceReceivePeriodView = new AdvanceReceivePeriodView();
                advanceReceivePeriodView = receiveTableDao.getAdvanceReceivePeriodView(util.convertDateToString(advanceReceivePeriod.getReceiveFrom()),util.convertDateToString(advanceReceivePeriod.getReceiveTo()),department,vatType);
                result = receiveTableDao.compareReceiptSummary(advanceReceivePeriod,advanceReceivePeriodView);               
            }else if("updateReceivePeriodSummary".equalsIgnoreCase(type)){
                String periodId = map.get("periodId").toString();
                String receiveFrom = map.get("receiveFrom").toString();
                String receiveTo = map.get("receiveTo").toString();        
                String department = map.get("department").toString();
                String vatType = map.get("vatType").toString();
                String check = "";
                AdvanceReceivePeriodView advanceReceivePeriodView = new AdvanceReceivePeriodView();
                advanceReceivePeriodView = receiveTableDao.getAdvanceReceivePeriodView(receiveFrom,receiveTo,department,vatType);
                check = receiveTableDao.updateReceivePeriodSummary(periodId,advanceReceivePeriodView);
                if("1".equalsIgnoreCase(check)){
                    AdvanceReceivePeriod advanceReceivePeriod = new AdvanceReceivePeriod();
                    advanceReceivePeriod = receiveTableDao.getReceivePeriod(receiveFrom,department,vatType);  
                    List<AdvanceReceivePeriod> advanceReceivePeriodList = receiveTableDao.getReceivePeriodList(department);
                    result =  buildAdvanceReceivePeriodListTaxHTML(advanceReceivePeriodList, advanceReceivePeriod, "update");          
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
            }else if("searchStock".equalsIgnoreCase(type)){
                String payStockNo = map.get("payStockNo").toString();
                List<PaymentStock> paymentStockList = paymentOutboundDao.getPaymentStock(payStockNo);
                if (paymentStockList.size() > 0) {
                    result = buildPaymentStockHTML(paymentStockList);
                } else {
                    result = "null";
                }
            }else if("checkPayStock".equalsIgnoreCase(type)){
                String payStockNo = map.get("payStockNo").toString();
                List<PaymentStock> paymentStockList = paymentOutboundDao.getPaymentStock(payStockNo);
                if (paymentStockList != null) {
                    result = "success";
                } else {
                    result = "fail";
                }
            }          
        }else if(PAYMENTSTOCK.equalsIgnoreCase(servletName)){
            if("getStockDetail".equalsIgnoreCase(type)){
                String stockId = map.get("stockId").toString();
                String countRowDetail = map.get("countRowDetail").toString();
                String noStockTable = map.get("noStockTable").toString();
                
                List<StockDetail> stockDetails = paymentStockDao.getListStockDetailFromStockId(stockId);
                if (stockDetails != null) {
                    result = buildPaymentStockDetailHTML(stockDetails,countRowDetail,noStockTable);
                } else {
                    result = "null";
                }
            } 
            if("getPaymentStockItemCostSale".equalsIgnoreCase(type)){
                String psdId = map.get("psdId").toString();
                String countRowDetail = map.get("countRowDetail").toString();
                List<PaymentStockItem> paymentStockItems = paymentStockDao.getListPaymentStockItemFromPaymentStockDetailId(psdId);
                if (paymentStockItems != null) {
                    result = getPaymentStockItemCostSale(paymentStockItems,countRowDetail);
                } else {
                    result = "null";
                }
            } 
            if("getStockDetailTempCal".equalsIgnoreCase(type)){
                String stockId = map.get("stockId").toString();
                String countRowDetail = map.get("countRowDetail").toString();
                List<StockDetail> stockDetails = paymentStockDao.getListStockDetailFromStockId(stockId);
                if (stockDetails != null) {
                    result = buildPaymentStockDetailTempCountHTML(stockDetails,countRowDetail);
                } else {
                    result = "null";
                }
            } 
        }
        return result;
    }

    private String getPaymentStockItemCostSale(List<PaymentStockItem> paymentStockItems,String row) {
        StringBuffer html = new StringBuffer();     
        UtilityFunction utilty = new UtilityFunction();
        int no = 1;
        String paystockitemid = "" ;
        String paystockdetailid = "" ;
        String stockdetailid = "" ;     
        String cost = "" ;
        String sale = "";
        String noMaxTemp = String.valueOf(paymentStockItems.size());
        System.out.println(" paymentStockItems.size() " + paymentStockItems.size());
        for (int i = 0; i < paymentStockItems.size(); i++) {
            PaymentStockItem paymentStockItem = new PaymentStockItem();
            paymentStockItem = paymentStockItems.get(i);
            
            paystockitemid = paymentStockItem.getId() ;
            paystockdetailid = paymentStockItem.getPaymentStockDetail().getId() ;
            stockdetailid = paymentStockItem.getStockDetail().getId() ;     
            cost = String.valueOf(paymentStockItem.getCost());
            sale = String.valueOf(paymentStockItem.getSale());
            
            String newrow = "";              
            newrow += "<input type='hidden' id='noMaxTemp' name='noMaxTemp'  value='" + noMaxTemp + "'>"
                    + "<input type='hidden' id='psiIdTemp" + no + "' name='psiIdTemp" + no + "'  value='" + paystockitemid + "'>"
                    + "<input type='hidden' id='psdIdTemp" + no + "' name='psdIdTemp" + no + "'  value='" + paystockdetailid + "'>" 
                    + "<input type='hidden' id='stockDetailIdTemp" + no + "' name='stockDetailIdTemp" + no + "'  value='" + stockdetailid + "'>"
                    + "<input id=\"costTemp" + no + "\" name=\"costTemp" + no + "\" type=\"hidden\" class=\"form-control text-right\" value='" + cost + "' >"
                    + "<input id=\"saleTemp" + no + "\" name=\"saleTemp" + no + "\" type=\"hidden\" class=\"form-control text-right\" value='" + sale + "' >";
            html.append(newrow);
            no++;
        } 
        return html.toString();
    }
    
    private String buildPaymentStockDetailHTML(List<StockDetail> stockDetails,String row,String noStockTable) {
        StringBuffer html = new StringBuffer();     
        UtilityFunction utilty = new UtilityFunction();
        int no = Integer.parseInt(row);
        String code = "" ;
        String type = "" ;
        String refno = "" ;
        String pickup = "" ;
        String pickdate = "" ;
        String psiIdTable = "" ;
        String psdIdTable = "" ;
        String stockDetailIdTable = "" ;
        String stockId = "";
        String cost = "" ;
        String sale = "";
        for (int i = 0; i < stockDetails.size(); i++) {
            StockDetail stockDetail = new StockDetail();
            stockDetail = stockDetails.get(i);
            code = stockDetail.getCode();
            if(stockDetail.getTypeId() != null){
                type = stockDetail.getTypeId().getName() ;
            }
            if(stockDetail.getOtherBooking() != null && stockDetail.getOtherBooking().getMaster() != null){
                refno = stockDetail.getOtherBooking().getMaster().getReferenceNo();
            }
            if(stockDetail.getStaff() != null){
                pickup = stockDetail.getStaff().getName();
            }
            pickdate = utilty.convertDateToString(stockDetail.getPickupDate());
            stockDetailIdTable = stockDetail.getId();
            stockId = stockDetail.getStock().getId();
            String newrow = "";              
            newrow += "<tr>"
                    + "<input type='hidden' id='psdIdTable" + no + "' name='psdIdTable" + no + "'  value='" + psdIdTable + "'>"
                    + "<input type='hidden' id='psiIdTable" + no + "' name='psiIdTable" + no + "'  value='" +  psiIdTable + "'>"
                    + "<input type='hidden' id='stockDetailIdTable" + no + "' name='stockDetailIdTable" + no + "'  value='" + stockDetailIdTable + "'>" 
                    + "<input type='hidden' id='stockIdTable" + no + "' name='stockIdTable" + no + "'  value='" + stockId + "'>"
                    + "<td class='text-center'>" + no + "</td>"
                    + "<td class='text-left'>" + code + "</td>"
                    + "<td class='text-left'>" + type + "</td>"
                    + "<td class='text-left'>" + refno + "</td>"
                    + "<td class='text-center'>" + pickup + "</td>"
                    + "<td class='text-center'>" + pickdate + "</td>"
                    + "<td><input maxlength=\"10\" id=\"cost" + no + "\" name=\"cost" + no + "\" type=\"text\" class=\"form-control text-right\" value='" + cost + "' onkeyup=\"insertCommas(this)\" onkeydown=\"setFormatCurrencyOnFocusOut('"+  no + "' ,'"+  noStockTable + "')\" ></td>" 
                    + "<td><input maxlength=\"10\" id=\"sale" + no + "\" name=\"sale" + no + "\" type=\"text\" class=\"form-control text-right\" value='" + sale + "' onkeyup=\"insertCommas(this)\" onkeydown=\"setFormatCurrencyOnFocusOut('"+  no + "' , '"+  noStockTable + "' )\" ></td>"
                    + "</tr>";
            html.append(newrow);
            no++;
        } 
        return html.toString();
    }
    
    private String buildPaymentStockDetailTempCountHTML(List<StockDetail> stockDetails,String row) {
        StringBuffer html = new StringBuffer();     
        UtilityFunction utilty = new UtilityFunction();
        int no = Integer.parseInt(row);
        String code = "" ;
        String type = "" ;
        String refno = "" ;
        String pickup = "" ;
        String pickdate = "" ;
        String psiIdTable = "" ;
        String psdIdTable = "" ;
        String stockDetailIdTable = "" ;
        String stockId = "";
        String cost = "" ;
        String sale = "";
        for (int i = 0; i < stockDetails.size(); i++) {
            StockDetail stockDetail = new StockDetail();
            stockDetail = stockDetails.get(i);
            code = stockDetail.getCode();
            if(stockDetail.getTypeId() != null){
                type = stockDetail.getTypeId().getName() ;
            }
            if(stockDetail.getOtherBooking() != null && stockDetail.getOtherBooking().getMaster() != null){
                refno = stockDetail.getOtherBooking().getMaster().getReferenceNo();
            }
            if(stockDetail.getStaff() != null){
                pickup = stockDetail.getStaff().getName();
            }
            pickdate = utilty.convertDateToString(stockDetail.getPickupDate());
            stockDetailIdTable = stockDetail.getId();
            stockId = stockDetail.getStock().getId();
            
            String newrow = "";              
            newrow += "<tr>"
                    + "<input type='hidden' id='psdIdTableTempCount" + no + "' name='psdIdTableTempCount" + no + "'  value='" + psdIdTable + "'>"
                    + "<input type='hidden' id='psiIdTableTempCount" + no + "' name='psiIdTableTempCount" + no + "'  value='" +  psiIdTable + "'>"
                    + "<input type='hidden' id='stockDetailIdTableTempCount" + no + "' name='stockDetailIdTableTempCount" + no + "'  value='" + stockDetailIdTable + "'>" 
                    + "<input type='hidden' id='stockIdTableTempCount" + no + "' name='stockIdTableTempCount" + no + "'  value='" + stockId + "'>"
                    + "<td class='text-center'>" + no + "</td>"
                    + "<td class='text-left'>" + code + "</td>"
                    + "<td class='text-left'>" + type + "</td>"
                    + "<td class='text-left'>" + refno + "</td>"
                    + "<td class='text-center'>" + pickup + "</td>"
                    + "<td class='text-center'>" + pickdate + "</td>"
                    + "<td><input maxlength=\"10\" id=\"costTempCount" + no + "\" name=\"costTempCount" + no + "\" type=\"text\" class=\"form-control text-right\" value='" + cost + "' onkeyup=\"insertCommas(this)\" onkeydown=\"setFormatCurrencyOnFocusOutTempCount('"+  no + "')\" ></td>"
                    + "<td><input maxlength=\"10\" id=\"saleTempCount" + no + "\" name=\"saleTempCount" + no + "\" type=\"text\" class=\"form-control text-right\" value='" + sale + "' onkeyup=\"insertCommas(this)\" onkeydown=\"setFormatCurrencyOnFocusOutTempCount('"+  no + "')\" ></td>"
                    + "</tr>";
            html.append(newrow);
            no++;
        } 
        return html.toString();
    }
    
    
    public String buildTicketAircommissionViewListHTML(List<TicketAircommissionView> ticketList) {
        StringBuffer html = new StringBuffer();
        int No = 0;

        for (int i = 0; i < ticketList.size(); i++) {
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
                receiveAddress = (ticketList.get(i).getAgentaddress()).replaceAll("(\r\n|\n)", "<br>");
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
                        + "<td><center><a href=\"#/com\"><span onclick=\"addProduct('" + product + "','" + description + "','','','1','" + vat + "','" + commission + "','" + currency + "','','','" + paymentId + "','" + airline + "','3','" + description + "','" + payNo + "','','" + receiveFrom + "','" + receiveName + "','" + receiveAddress + "','','')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                        + "</tr>";
            }
            html.append(newrow);
        }

        return html.toString();
    }
    
    public String buildPaymentTourCommissionViewListHTML(List<PaymentTourCommissionView> paymentTourList) {
        StringBuffer html = new StringBuffer();
        int No = 0;

        for (int i = 0; i < paymentTourList.size(); i++) {
            No = i + 1;
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
                receiveAddress = (paymentTourList.get(i).getSupaddress()).replaceAll("(\r\n|\n)", "<br>");
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
                        + "<td><center><a href=\"#/com\"><span onclick=\"addProduct('" + product + "','" + description + "','','','1','" + vat + "','" + commission + "','" + currency + "','','','','" + airline + "','4','" + description + "','" + payNo + "','" + paymentTourId + "','" + receiveFrom + "','" + receiveName + "','" + receiveAddress + "','','')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
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
            invoiceDetail = invoiceDetaillList.get(i);
            invDetailId = invoiceDetail.getId();
            String isProfit = taxInvoiceDao.checkIsProfitForSearchInvoice(invDetailId);
            
            if("success".equalsIgnoreCase(isProfit)){
                BigDecimal costInvoice = new BigDecimal(0);
                BigDecimal amountInvoice = new BigDecimal(0);           
                product = (invoiceDetail.getMbillType() != null ? invoiceDetail.getMbillType().getName() : "");
                description = (invoiceDetail.getDescription() != null && !"".equalsIgnoreCase(invoiceDetail.getDescription()) ? invoiceDetail.getDescription() : "");
                curCost = (!"".equalsIgnoreCase(invoiceDetail.getCurCost()) && invoiceDetail.getCurCost() != null ? invoiceDetail.getCurCost() : "");
                curAmount = (!"".equalsIgnoreCase(invoiceDetail.getCurAmount()) && invoiceDetail.getCurAmount() != null ? invoiceDetail.getCurAmount() : "");
                isVat = String.valueOf(invoiceDetail.getIsVat());

                if (invoiceDetail.getCostLocal()!= null) {
                    costInvoice = invoiceDetail.getCostLocal();
                } else {
                    costInvoice = new BigDecimal(0);
                }
                if (invoiceDetail.getAmountLocal()!= null) {
                    amountInvoice = invoiceDetail.getAmountLocal();
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
                            + "<td style=\"text-align:center;\">" + "THB" + "</td>"
                            + "<td><center><a href=\"\"><span onclick=\"AddProduct('"+invDetailId+"','"+product+"','"+description+"','"+cost+"','"+curCost+"','"+amount+"','THB','"+isVat+"','"+refNo+"','"+vat+"')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                            + "</tr>";
                        html.append(newrow);
                }
                row++;
            }    
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
        String curCost = "";
        String bookId = "";
        BigDecimal sale = new BigDecimal(0);
        String curSale = "";
                
        for (int i = 0; i < bookingOutboundViewList.size(); i++) {
            BookingOutboundView bookingOutboundView = new BookingOutboundView();
            bookingOutboundView = bookingOutboundViewList.get(i);
            refNo = bookingOutboundView.getRefNo();
            type = bookingOutboundView.getType();
            description = bookingOutboundView.getDescription();
            billType = bookingOutboundView.getBilltype();
            cost = (!"".equalsIgnoreCase(bookingOutboundView.getCost()) ? new BigDecimal(bookingOutboundView.getCost()) : new BigDecimal(0));
            curCost = bookingOutboundView.getCurcost();
            bookId = bookingOutboundView.getBookid();
            sale = (!"".equalsIgnoreCase(bookingOutboundView.getSale()) ? new BigDecimal(bookingOutboundView.getSale()) : new BigDecimal(0));
            curSale = bookingOutboundView.getCursale();
                       
            String newrow = "";              
            newrow += "<tr>"
                    + "<td class='text-center'>" + no + "</td>"
                    + "<td class='text-center'>" + type + "</td>"
                    + "<td>" + description + "</td>"
                    + "<td id='mCost" + no + "' class='text-right'>" + cost + "</td>"
                    + "<td class='text-center'>" + curCost + "</td>"
                    + "<td id='mSale" + no + "' class='text-right'>" + sale + "</td>"
                    + "<td class='text-center'>" + curSale + "</td>"
                    + "<td><center><a href=\"#/ref\"><span onclick=\"addRefNo('" + refNo + "','" + type + "','" + description + "','" + billType + "','" + cost + "','" + curCost + "','" + sale + "','" + curSale + "','" + bookId + "')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                    + "</tr>";
            html.append(newrow);
            no++;
        } 
        return html.toString();
    }
    
    public String buildBillableListTaxHTML(Billable billable, Invoice invoice) {
        StringBuffer html = new StringBuffer();
        List<BillableDesc> billableDescs = new ArrayList<BillableDesc>(billable.getBillableDescs());
        List<InvoiceDetail> invoiceDetailList = new ArrayList<InvoiceDetail>(invoice.getInvoiceDetails());
        String receiveTaxInvTo = invoice.getInvTo();
        String receiveInvToName = invoice.getInvName();
        String receiveInvToAddress = invoice.getInvAddress();
        String receiveARCode = invoice.getArcode();
//        String invoiceDetailId = invoice.getId();
        String invoiceDetailId = "";
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
//        if (billableDescs == null || billableDescs.size() == 0) {
            String newrow = "";
            newrow += "<tr>"
                    + "<input type='hidden' name='receiveTaxInvTo' id='receiveTaxInvTo' value='" + receiveTaxInvTo + "'>"
                    + "<input type='hidden' name='receiveInvToName' id='receiveInvToName' value='" + receiveInvToName + "'>"
                    + "<input type='hidden' name='receiveInvToAddress' id='receiveInvToAddress' value='" + receiveInvToAddress + "'>"
                    + "<input type='hidden' name='receiveARCode' id='receiveARCode' value='" + receiveARCode + "'>"
                    + "</tr>";
            newrow += "//";
            html.append(newrow);
//            return html.toString();
//        }
        for (int i = 0; i < billableDescs.size(); i++) {
            billableDescId = billableDescs.get(i).getId();
            for(int j=0; j<invoiceDetailList.size(); j++){
                BigDecimal exrate = invoiceDetailList.get(j).getExRate();
                curcost = (billableDescs.get(i).getCurCost() == null ? "" : billableDescs.get(i).getCurCost());
                curamount = (billableDescs.get(i).getCurrency() == null ? "" : billableDescs.get(i).getCurrency());
                BigDecimal profitTaxInvoice = new BigDecimal(BigInteger.ZERO);
                BigDecimal costCheck = (billableDescs.get(i).getCost() != 0 ? new BigDecimal(billableDescs.get(i).getCost()) : new BigDecimal(BigInteger.ZERO));
                BigDecimal amountCheck = (billableDescs.get(i).getPrice()!= 0 ? new BigDecimal(billableDescs.get(i).getPrice()) : new BigDecimal(BigInteger.ZERO));
                BigDecimal profitCheck = new BigDecimal(BigInteger.ZERO);
                
                if(billableDescId.equalsIgnoreCase(invoiceDetailList.get(j).getBillableDesc().getId()) && !"".equalsIgnoreCase(curcost) && !"".equalsIgnoreCase(curamount)){
                    boolean check = true;
                    if(!"THB".equalsIgnoreCase(curcost) || !"THB".equalsIgnoreCase(curamount)){
                        if(exrate.compareTo(BigDecimal.ZERO) == 0){
                            check = false;
                        
                        }else{
                            profitTaxInvoice = taxInvoiceDao.getProfitFromTaxInvoice(billableDescId,"");                           
                            if(!"THB".equalsIgnoreCase(curcost) && !"THB".equalsIgnoreCase(curamount)){
                                profitCheck = ((amountCheck.multiply(exrate)).subtract(costCheck.multiply(exrate))).setScale(2, RoundingMode.UP);

                            }else{
                                profitCheck = (amountCheck.subtract(costCheck)).setScale(2, RoundingMode.UP);
                            }
                            
                            if(profitCheck.compareTo(profitTaxInvoice) == 0 || profitCheck.compareTo(profitTaxInvoice) < 0){
                                check = false;
                            }
                        }                       
                    }
                       
                    if(check){
                        invoiceDetailId = invoiceDetailList.get(j).getId();
                        String isProfit = taxInvoiceDao.checkIsProfitForSearchRefNo(invoiceDetailId);

                        if("success".equalsIgnoreCase(isProfit)){
                            String invNo = taxInvoiceDao.getInvoiceNoByInvoiceDetailId(invoiceDetailId);
                            description = billableDescs.get(i).getDetail();
                            BigDecimal amounttemp = new BigDecimal(billableDescs.get(i).getPrice());
                            amountinvoice = amounttemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                            if (billableDescs.get(i).getMBilltype() != null) {
                                product = billableDescs.get(i).getMBilltype().getId();
                                billTypeName = billableDescs.get(i).getMBilltype().getName();
                            }

                            BigDecimal costtemp = new BigDecimal(billableDescs.get(i).getCost());
                            costinvoice = costtemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);                      

                            BigDecimal[] value = checkTaxInvoiceDetailFromBilldescId(invoiceDetailId);
                            BigDecimal costTemp = value[0];
//                            BigDecimal amountTemp = value[1];
                            BigDecimal amountTemp = taxInvoiceDao.getProfitFromTaxInvoice(billableDescId,""); 

                            amount = amountinvoice;
                            cost = costinvoice;
                            System.out.println(" amount =  " + amountinvoice + "-" + amountTemp + " = " + amount);
                            System.out.println(" cost =  " + costinvoice + "-" + costTemp + " = " + cost);

                            refItemId = billableDescs.get(i).getRefItemId();

                            String displaydescription = "";
                            String displaydesTemp = "";

                            BigDecimal profit = new BigDecimal(0);
                            BigDecimal remain = new BigDecimal(0);
    //                        exrate = billableDescs.get(i).getExRate();

                            if(exrate != null && exrate.compareTo(BigDecimal.ZERO) != 0){
                                profit = ((amount.multiply(exrate)).subtract(cost.multiply(exrate))).setScale(2, RoundingMode.HALF_UP);
                                remain = profit.subtract(amountTemp);

                            }else{
                                profit = amount.subtract(cost);
                                remain = profit.subtract(amountTemp);
                            }                        

    //                        if((curcost.equalsIgnoreCase(curamount)) && (!"".equalsIgnoreCase(curcost)) && (!"".equalsIgnoreCase(curamount))){
    //                            profit = amount.subtract(cost);
    //                            remain = profit.subtract(amountTemp);
    //                        } else {
    //                            if(exrate == null){
    //                                exrate = new BigDecimal(0);
    //                            }
    //                            profit = amount.subtract(cost.multiply(exrate)).setScale(2, RoundingMode.HALF_UP);
    //                            remain = profit.subtract(amountTemp);
    //                        }


                            if (remain.compareTo(BigDecimal.ZERO) != 0 && remain.compareTo(BigDecimal.ZERO) != -1) {
                                newrow = "";              
                                newrow += "<tr>"
                                        + "<input type='hidden' name='receiveTaxInvTo' id='receiveTaxInvTo' value='" + receiveTaxInvTo + "'>"
                                        + "<input type='hidden' name='receiveInvToName' id='receiveInvToName' value='" + receiveInvToName + "'>"
                                        + "<input type='hidden' name='receiveInvToAddress' id='receiveInvToAddress' value='" + receiveInvToAddress + "'>"
                                        + "<input type='hidden' name='receiveARCode' id='receiveARCode' value='" + receiveARCode + "'>"
                                        + "<td class='text-center'>" + No + "</td>"
                                        + "<td class='text-center'>" + invNo + "</td>"
                                        + "<td>" + description + "</td>"
                                        + "<td class='text-right money'>" + cost + "</td>"
                                        + "<td class='text-center'>" + curcost + "</td>"
                                        + "<td class='text-right money'>" + amount + "</td>"
                                        + "<td class='text-center'>" + curamount + "</td>"
                                        + "<td class='text-right money3'>" + ("0".equalsIgnoreCase(String.valueOf(exrate)) ? "" : exrate) + "</td>"
                                        + "<td class='text-right money'>" + profit + "</td>"
                                        + "<td class='text-right money'>" + remain + "</td>"
                                        + "<td><center><a href=\"#/ref\"><span onclick=\"AddRefNo('" + product + "','" + description + "','" + cost + "','" + curcost + "','" + remain + "','THB','" + invoiceDetailId + "','" + displaydescription + "','" + refNo + "')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
    //                                    + "<td><center><a href=\"#/ref\"><span onclick=\"AddRefNo('" + product + "','" + description + "','" + cost + "','" + curcost + "','" + remain + "','" + curamount + "','" + invoiceDetailId + "','" + displaydescription + "','" + refNo + "')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                                        + "</tr>";
                                html.append(newrow);
                                No++;
                            }    
            //                } else {
            //                    String newrow = "";
            //                    newrow += "<tr>"
            //                            + "<input type='hidden' name='receiveTaxInvTo' id='receiveTaxInvTo' value='" + receiveTaxInvTo + "'>"
            //                            + "<input type='hidden' name='receiveInvToName' id='receiveInvToName' value='" + receiveInvToName + "'>"
            //                            + "<input type='hidden' name='receiveInvToAddress' id='receiveInvToAddress' value='" + receiveInvToAddress + "'>"
            //                            + "<input type='hidden' name='receiveARCode' id='receiveARCode' value='" + receiveARCode + "'>"
            //                            + "</tr>";
            //                    newrow += "//";
            //                    html.append(newrow);
            //                }
                        }
                    }    
                }
            }
            
               
        }
        return html.toString();
    }

    public String buildInvoiceListHTML(Invoice invoice) {
        StringBuffer html = new StringBuffer();
        List<InvoiceDetail> invoiceDetaill = new ArrayList<InvoiceDetail>(invoice.getInvoiceDetails());
        int No = 1;
        String receiveFrom = invoice.getInvTo();
        String receiveName = invoice.getInvName();
//        String receiveAddress = (invoice.getInvAddress()).replaceAll("(\r\n|\n)", "<br>");
        String receiveAddress = invoice.getInvAddress();
        String arcode = invoice.getArcode();
        String invNo = invoice.getInvNo();
        System.out.println("invoiceDetaill.size() " + String.valueOf(invoiceDetaill.size()));
        if (invoiceDetaill == null || invoiceDetaill.size() == 0) {
            String newrow = "";
            newrow += "<input type='hidden' name='receiveFromInvoice' id='receiveFromInvoice' value='" + receiveFrom + "'>"
                    + "<input type='hidden' name='receiveNameInvoice' id='receiveNameInvoice' value='" + receiveName + "'>"
                    + "<input type='hidden' name='receiveAddressInvoice' id='receiveAddressInvoice' value='" + receiveAddress + "'>"
                    + "<input type='hidden' name='arcodeInvoice' id='arcodeInvoice' value='" + arcode + "'>";
            html.append(newrow);
            return html.toString();
        }
        for (int i = 0; i < invoiceDetaill.size(); i++) {
            String invId = "";
            String description = "";
            String currency = "";
            String product = "";
            String cur = "";
            String isVat = "";
            String vat = "";
            String refItemId = "";
            String billTypeName = "";
            BigDecimal amount = new BigDecimal(BigInteger.ZERO);
            BigDecimal cost = new BigDecimal(BigInteger.ZERO);
            BigDecimal amountinvoice = new BigDecimal(BigInteger.ZERO);
            BigDecimal costinvoice = new BigDecimal(BigInteger.ZERO);
            BigDecimal amountlocalinvoice = new BigDecimal(BigInteger.ZERO);
            
            invId = invoiceDetaill.get(i).getId();
            if(!"null".equalsIgnoreCase(String.valueOf(invoiceDetaill.get(i).getDescription())) && invoiceDetaill.get(i).getDescription() != null){
                description = invoiceDetaill.get(i).getDescription();
            }else{
                description = "";
            }
            if(invoiceDetaill.get(i).getAmount() != null){
                amountinvoice = invoiceDetaill.get(i).getAmount().compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : invoiceDetaill.get(i).getAmount();
            }
            if(invoiceDetaill.get(i).getAmountLocal()!= null){
                amountlocalinvoice = invoiceDetaill.get(i).getAmountLocal().compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : invoiceDetaill.get(i).getAmountLocal();
            }
            if(invoiceDetaill.get(i).getCurAmount() != null && !"".equalsIgnoreCase(invoiceDetaill.get(i).getCurAmount())){
                currency = invoiceDetaill.get(i).getCurAmount();
            }
//            currency = "THB";
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
                        + "<td><center><a href=\"#/inv\"><span onclick=\"addProduct('" + product + "','" + description + "','" + cost + "','" + cur + "','" + isVat + "','" + vat + "','" + amount + "','" + "THB" + "','" + invId + "','','','','1','" + displaydescription + "','" + invNo + "','','','','' ,'" + invoice.getId() + "','" + invoice.getInvNo()+ "')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                        + "</tr>";
                html.append(newrow);
                No++;
            } else {
                String newrow = "";
                newrow += "<input type='hidden' name='receiveFromInvoice' id='receiveFromInvoice' value='" + receiveFrom + "'>"
                        + "<input type='hidden' name='receiveNameInvoice' id='receiveNameInvoice' value='" + receiveName + "'>"
                        + "<input type='hidden' name='receiveAddressInvoice' id='receiveAddressInvoice' value='" + receiveAddress + "'>"
                        + "<input type='hidden' name='arcodeInvoice' id='arcodeInvoice' value='" + arcode + "'>"
                        ;
                html.append(newrow);
            }
        }
        return html.toString();
    }

    public String buildBillableListHTML(Billable billable) {
        StringBuffer html = new StringBuffer();
        List<BillableDesc> billableDescs = new ArrayList<BillableDesc>(billable.getBillableDescs());

        int No = 1;

//        String displaydescription = "";
        String refItemId = "";
        String billTypeName = "";
//        String displaydesTemp = ""; 

        String mAccPay = "";
        String receiveFrom = billable.getBillTo();
        String receiveName = billable.getBillName();
//        String receiveAddress = (billable.getBillAddress()).replaceAll("(\r\n|\n)", "<br>");
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
            String description = "";
            String currency = "";
            String product = "";
            String cur = "";
            String isVat = "";
            String vat = "";
            String billableDescId = "";
            BigDecimal amount = new BigDecimal(BigInteger.ZERO);
            BigDecimal cost = new BigDecimal(BigInteger.ZERO);
            BigDecimal amountinvoice = new BigDecimal(BigInteger.ZERO);
            BigDecimal costinvoice = new BigDecimal(BigInteger.ZERO);
            
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
                        + "<td><center><a href=\"#/ref\"><span onclick=\"addProduct('" + product + "','" + description + "','" + cost + "','" + cur + "','','','" + amount + "','" + "THB" + "','','" + billableDescId + "','','','2','" + displaydescription + "','" + refNo + "','','','','','','')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                        + "</tr>";
                html.append(newrow);
                No++;
            } else {
                String newrow = "";
                newrow +=  
                         "<input type='hidden' name='masterBookType' id='masterBookType' value='" + billable.getMaster().getBookingType() + "'>"
                        + "<input type='hidden' name='receiveFromBillable' id='receiveFromBillable' value='" + receiveFrom + "'>"
                        + "<input type='hidden' name='receiveNameBillable' id='receiveNameBillable' value='" + receiveName + "'>"
                        + "<input type='hidden' name='receiveAddressBillable' id='receiveAddressBillable' value='" + receiveAddress + "'>"
                        + "<input type='hidden' name='arcodeBillable' id='arcodeBillable' value='" + arcode + "'>"
                        + "<input type='hidden' name='mAccPayBillable' id='mAccPayBillable' value='" + mAccPay + "'>"
                        + "";
                        
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
        if(listCutomerInfo != null){
            for (int i = 0; i < listCutomerInfo.size(); i++) {
                CustomerAgentInfo customer = listCutomerInfo.get(i);
                JSONObject field = new JSONObject();
                field.put("id", customer.getBillTo());
                field.put("name", customer.getBillName());
                field.put("address", customer.getAddress());
                record.add(field);
            }
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

    public String getListInvoice(Billable bill, String invType, String department) {
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
                boolean isDaytour = true;
                if("Outbound".equalsIgnoreCase(department)){
                    isDaytour = (!billdeescList.get(i).getMBilltype().getName().equals("Day Tour") ? true : false);
                }
                if (!billdeescList.get(i).getMBilltype().getName().equals("Air Ticket") && !billdeescList.get(i).getMBilltype().getName().equals("Air Additional") && isDaytour) {
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
            if(cost != null){
                resultCost = resultCost.add(cost);
            }else{
                resultCost = resultCost.add(BigDecimal.ZERO);
            }
            
            if(amount != null){
                resultAmount = resultAmount.add(amount);
            }else{
                resultAmount = resultAmount.add(BigDecimal.ZERO);
            }
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
        System.out.println("taxInvoiceDetailList : "+taxInvoiceDetailList);
        System.out.println("invoiceDetailId : "+invoiceDetailId); 
        if (taxInvoiceDetailList == null || taxInvoiceDetailList.size() == 0) {
            value[0] = resultCost;
            value[1] = resultAmount;
            return value;
        }
        for (int i = 0; i < taxInvoiceDetailList.size(); i++) {
            cost = taxInvoiceDetailList.get(i).getCost();
            amount = taxInvoiceDetailList.get(i).getAmount();
            if(cost != null){
                resultCost = resultCost.add(cost);
            }else{
                resultCost = resultCost.add(BigDecimal.ZERO);
            }
            
            if(amount != null){
                resultAmount = resultAmount.add(amount);
            }else{
                resultAmount = resultAmount.add(BigDecimal.ZERO);
            }
        }
        System.out.println("resultCost : "+resultCost);
        System.out.println("resultAmount : "+resultAmount); 
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

    private Map convertInvoiceToMap(TaxInvoice tax, String creditNoteId) {
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
//        BigDecimal amountTotal = new BigDecimal(0);
        BigDecimal amountTotal = taxInvoiceDao.getTaxInvoiceAmountTotal(tax,creditNoteId);
        if(amountTotal == null){
            amountTotal = new BigDecimal(BigInteger.ZERO);
        }
        for (Iterator detailList = tax.getTaxInvoiceDetails().iterator(); detailList.hasNext();) {
            TaxInvoiceDetail detail = (TaxInvoiceDetail) detailList.next();
//            amountTotal = amountTotal.add(detail.getAmount() != null ? detail.getAmount() : new BigDecimal(0));
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
            if(detail.getMbillType() != null){
               detailMap.put("product", detail.getMbillType().getName()); 
            }           
            if(detail.getMaster() != null){
                detailMap.put("refNo", detail.getMaster().getReferenceNo());
                System.out.println("refNo : " + detail.getMaster().getReferenceNo());
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
        map.put("amountTotal",amountTotal);
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

    private JSONArray buildAdvanceReceivePeriodViewListJSON(AdvanceReceivePeriod advanceReceivePeriod ,AdvanceReceivePeriodView advanceReceivePeriodView) {
        UtilityFunction utilty = new UtilityFunction();
        JSONArray record = new JSONArray();
        JSONObject field = new JSONObject();
        field.put("periodId", advanceReceivePeriod.getId());
        field.put("periodIdDetail", advanceReceivePeriod.getDetail());
        field.put("receiveFrom", utilty.convertDateToString(advanceReceivePeriod.getReceiveFrom()));
        field.put("receiveTo", utilty.convertDateToString(advanceReceivePeriod.getReceiveTo()));
        field.put("cashamount", advanceReceivePeriodView.getCashamount());
        field.put("bankamount", advanceReceivePeriodView.getBankamount());
        field.put("cashminusamount", advanceReceivePeriodView.getCashminusamount());
        field.put("cheque", advanceReceivePeriodView.getCheque());
        field.put("creditcard", advanceReceivePeriodView.getCreditcard());
        record.add(field);

        return record;
    }

    private String buildAdvanceReceivePeriodListTaxHTML(List<AdvanceReceivePeriod> advanceReceivePeriodList, AdvanceReceivePeriod period, String option) {
        UtilityFunction util = new UtilityFunction();
        StringBuffer html = new StringBuffer();
        int row = 1;
        String id = "";
        String receiveFrom = "";
        String receiveTo = "";
        String detail = "";
        String vatType = "";
        String department = "";
        String cashAmount = "";
        String cashMinusAmount = "";
        String bankTransfer = "";
        String chqAmount = "";
        String creditAmount = "";
        int periodSize = (advanceReceivePeriodList != null ? advanceReceivePeriodList.size() : 0);
        String newrow = "";
        newrow += "<tr>"
                + "<input type='hidden' name='periodSizeTemp' id='periodSizeTemp' value='" + periodSize + "'>"
                + "</tr>";
        html.append(newrow);
        
        if("save".equalsIgnoreCase(option) || "update".equalsIgnoreCase(option)){
            id = period.getId();
            detail = (!"".equalsIgnoreCase(period.getDetail()) && period.getDetail() != null ? period.getDetail() : "");
            receiveFrom = util.convertDateToString(period.getReceiveFrom());
            receiveTo = util.convertDateToString(period.getReceiveTo());
            vatType = (!"".equalsIgnoreCase(period.getVatType()) && period.getVatType() != null ? String.valueOf(period.getVatType()) : "");
            cashAmount = (period.getCashAmount() != null ? String.valueOf(period.getCashAmount()) : "");
            cashMinusAmount = (period.getCashMinusAmount()!= null ? String.valueOf(period.getCashMinusAmount()) : "");
            bankTransfer = (period.getBankTransfer()!= null ? String.valueOf(period.getBankTransfer()) : "");
            chqAmount = (period.getChqAmount()!= null ? String.valueOf(period.getChqAmount()) : "");
            creditAmount = (period.getCreditAmount()!= null ? String.valueOf(period.getCreditAmount()) : "");
            department = (!"".equalsIgnoreCase(period.getDepartment()) && period.getDepartment() != null ? period.getDepartment() : "");
            newrow = "";              
            newrow += "<tr>"
                    + "<input type='hidden' name='periodIdTemp' id='periodIdTemp' value='" + id + "'>"
                    + "<input type='hidden' name='periodDetailTemp' id='periodDetailTemp' value='" + detail + "'>"
                    + "<input type='hidden' name='periodFromTemp' id='periodFromTemp' value='" + receiveFrom + "'>"
                    + "<input type='hidden' name='periodToTemp' id='periodToTemp' value='" + receiveTo + "'>"
                    + "<input type='hidden' name='periodVatTypeTemp' id='periodVatTypeTemp' value='" + vatType + "'>"
                    + "<input type='hidden' name='periodCashAmountTemp' id='periodCashAmountTemp' value='" + cashAmount + "'>"
                    + "<input type='hidden' name='periodCashMinusAmountTemp' id='periodCashMinusAmountTemp' value='" + cashMinusAmount + "'>"
                    + "<input type='hidden' name='periodBankAmountTemp' id='periodBankAmountTemp' value='" + bankTransfer + "'>"
                    + "<input type='hidden' name='periodChqAmountTemp' id='periodChqAmountTemp' value='" + chqAmount + "'>"
                    + "<input type='hidden' name='periodCreditAmountTemp' id='periodCreditAmountTemp' value='" + creditAmount + "'>"
                    + "<input type='hidden' name='periodDepartmentTemp' id='periodDepartmentTemp' value='" + department + "'>"  
                    + "</tr>";
            html.append(newrow);
        }
        html.append("//");
         
        if(advanceReceivePeriodList != null){
            for(int i=0; i<advanceReceivePeriodList.size(); i++){
                AdvanceReceivePeriod advanceReceivePeriod = new AdvanceReceivePeriod();
                advanceReceivePeriod = advanceReceivePeriodList.get(i);
                id = advanceReceivePeriod.getId();
                receiveFrom = util.convertDateToString(advanceReceivePeriod.getReceiveFrom());
                receiveTo = util.convertDateToString(advanceReceivePeriod.getReceiveTo());
                detail = (!"".equalsIgnoreCase(advanceReceivePeriod.getDetail()) && advanceReceivePeriod.getDetail() != null ? advanceReceivePeriod.getDetail() : "");
                vatType = advanceReceivePeriod.getVatType();
                department = advanceReceivePeriod.getDepartment();
                cashAmount = (advanceReceivePeriod.getCashAmount() != null ? String.valueOf(advanceReceivePeriod.getCashAmount()) : "");
                cashMinusAmount = (advanceReceivePeriod.getCashMinusAmount()!= null ? String.valueOf(advanceReceivePeriod.getCashMinusAmount()) : "");
                bankTransfer = (advanceReceivePeriod.getBankTransfer()!= null ? String.valueOf(advanceReceivePeriod.getBankTransfer()) : "");
                chqAmount = (advanceReceivePeriod.getChqAmount()!= null ? String.valueOf(advanceReceivePeriod.getChqAmount()) : "");
                creditAmount = (advanceReceivePeriod.getCreditAmount()!= null ? String.valueOf(advanceReceivePeriod.getCreditAmount()) : "");

                newrow = "";              
                newrow += "<tr>"
                        + "<td class=\"text-center\">"
                        +       "<input type=\"checkbox\" id=\"periodCheckbox" + row + "\" name=\"periodCheckbox" + row + "\" value=\"\"/>"
                        +       "<input type=\"hidden\" id=\"periodRow" + row + "\" name=\"periodRow" + row + "\" value=\"" + row + "\"/>"
                        +       "<input type=\"hidden\" id=\"periodId" + row + "\" name=\"periodId" + row + "\" value=\"" + id + "\"/>"
                        + "</td>" 
                        + "<td class=\"text-center\">" + receiveFrom + "</td>"
                        + "<td class=\"text-center\">" + receiveTo + "</td>"
                        + "<td class=\"text-center\">" + ("V".equalsIgnoreCase(vatType) ? "Vat" : "Temp") + "</td>"
                        + "<td class=\"text-center\">"
                        +       "<span class=\"glyphicon glyphicon-edit editicon\" onclick=\"editAdvanceReceivePeriod('" + id + "','" + receiveFrom + "','" + receiveTo + "','" + detail + "','" + vatType + "','" + department + "','" + cashAmount + "','" + cashMinusAmount + "','" + bankTransfer + "','" + chqAmount + "','" + creditAmount + "');\"></span>"                                                                                                                                     
                        + "</td>"
                        + "</tr>";
                html.append(newrow);
                row++;    
            }
        }    
        return html.toString();
    }

    private String buildPaymentStockHTML(List<PaymentStock> paymentStockList) {
        StringBuffer html = new StringBuffer();      
        int no = 1;
        String stockId = "";
        String payStockNo = "";
        BigDecimal costAmount = new BigDecimal(0);
        BigDecimal saleAmount = new BigDecimal(0);
        String curCost = "";       
        String curSale = "";
                
        for (int i = 0; i < paymentStockList.size(); i++) {
            PaymentStock paymentStock = new PaymentStock();
            paymentStock = paymentStockList.get(i);
            stockId = paymentStock.getId();
            payStockNo = paymentStock.getPayStockNo();
            costAmount = (paymentStock.getCostAmount() != null ? paymentStock.getCostAmount() : new BigDecimal(0));
            saleAmount = (paymentStock.getSaleAmount() != null ? paymentStock.getSaleAmount() : new BigDecimal(0));
            curCost = paymentStock.getCurCost();
            curSale = paymentStock.getCurSale();
                       
            String newrow = "";              
            newrow += "<tr>"
                    + "<td class='text-center'>" + no + "</td>"
                    + "<td class='text-left'>" + payStockNo + "</td>"
                    + "<td id='mCostAmount" + no + "' class='text-right'>" + costAmount + "</td>"
                    + "<td class='text-center'>" + curCost + "</td>"
                    + "<td id='mSaleAmount" + no + "' class='text-right'>" + saleAmount + "</td>"
                    + "<td class='text-center'>" + curSale + "</td>"
                    + "<td><center><a href=\"#/ref\"><span onclick=\"addStock('" + stockId + "','" + payStockNo + "','" + costAmount + "','" + saleAmount + "','" + curCost + "','" + curSale + "')\" class=\"glyphicon glyphicon-plus\"></span></a></center></td>"
                    + "</tr>";
            html.append(newrow);
            no++;
        } 
        return html.toString();
    }

    public OtherBookingDao getOtherBookingDao() {
        return otherBookingDao;
    }

    public void setOtherBookingDao(OtherBookingDao otherBookingDao) {
        this.otherBookingDao = otherBookingDao;
    }
    
    public String checkInvoiceDetailFromBilldescIdAndRecDetailId(BillableDesc bill, String billDescId,String receiptDetailId,String recAmount){
        String result = "";
        BigDecimal invDetailAmount = new BigDecimal(0);
        BigDecimal amount = new BigDecimal(0);
        List<InvoiceDetail> invoiceDetailList = invoicedao.getInvoiceDetailFromBillDescIdAndRecDetailId(billDescId,receiptDetailId);
        BigDecimal billDescAmount = new BigDecimal(bill.getPrice());
        BigDecimal receiptAmount = new BigDecimal(recAmount);
        if(invoiceDetailList != null){
            for (int i = 0; i < invoiceDetailList.size(); i++) {
                if(invoiceDetailList.get(i).getAmount() != null){
                    invDetailAmount = invDetailAmount.add(invoiceDetailList.get(i).getAmount());
                }
            }    
        }
        amount = receiptAmount.add(invDetailAmount);
        int resultcompare = billDescAmount.compareTo(amount);
        
        if(resultcompare == 0){
            result = "success";
        }else if(resultcompare == 1){
            result = "success";
        }else if(resultcompare == -1){
            result = "fail";
        }
        return result;
    }
}
