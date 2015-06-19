/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.bean.master;

import com.smi.travel.common.bean.AbstractBean;
import com.smi.travel.datalayer.ajax.service.AbstractAJAXServices;
import com.smi.travel.datalayer.dao.CustomerDao;
import com.smi.travel.datalayer.dao.DaytourBookingDao;
import com.smi.travel.datalayer.dao.DaytourComissionDao;
import com.smi.travel.datalayer.dao.DaytourDao;
import com.smi.travel.datalayer.dao.MAirportDao;
import com.smi.travel.datalayer.dao.PackageTourDao;
import com.smi.travel.datalayer.dao.ProductDetailDao;
import com.smi.travel.datalayer.dao.TransferJobDao;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourBookingPrice;
import com.smi.travel.datalayer.entity.DaytourPrice;
import com.smi.travel.datalayer.entity.MAirport;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.PackageItinerary;
import com.smi.travel.datalayer.entity.PackagePrice;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.entity.ProductDetail;
import com.smi.travel.datalayer.view.dao.BookingSummaryDao;
import com.smi.travel.datalayer.view.dao.CustomerAgentInfoDao;
import com.smi.travel.datalayer.view.entity.BookSummary;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.util.UtilityFunction;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
        System.out.println("servletName : " + servletName);
        System.out.println("type : " + type);
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
                    customer.setFirstName(pathname[0]);
                    customer.setLastName(pathname[1]);
                    customer.setCode(pathname[0] + pathname[1]);
                }

                List<Customer> customerList = customerdao.FiterCustomer(customer, filter);
                result = this.buildCustomerListHTML(customerList);
                // System.out.println("result : " + result);O
            }
        } else if (BOOKOTHER.equalsIgnoreCase(servletName)) {
            //result = customerdao.isExistCustomer(initialID, first, last);
            System.out.println("ajax : " + BOOKOTHER);
            if ("getvalueProduct".equalsIgnoreCase(type)) {
                String productID = map.get("productid").toString();
                String otherdate = map.get("otherdate").toString();
                if ("".equalsIgnoreCase(productID)) {
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
                List<PackageItinerary> packItinerary = packagedao.SortItineraryList(new ArrayList<PackageItinerary>(pa.getPackageItineraries()));
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
            }else  if ("AutoListBillto".equalsIgnoreCase(type)) {
                String name = map.get("name").toString();
                List<CustomerAgentInfo> data = customerAgentInfoDao.SearchListCustomerAgentInfo(name);
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
            }else if("autoairport".equalsIgnoreCase(type)){
                result = airportdao.searchAirport(name);
                System.out.println(result);
            }else if("getairportname".equalsIgnoreCase(type)){
                List<MAirport> data = airportdao.searchAirport(name);
                if(data == null){
                    result = "";
                }else{
                    result = data.get(0);
                }
                System.out.println(result);
            }
        }
        return result;
    }
    
    public String buildAirportListJSON(List<MAirport> listAirport) {
        JSONArray record = new JSONArray();
        for (int i = 0; i < listAirport.size(); i++) {
            MAirport airport = listAirport.get(i);
            JSONObject field = new JSONObject();
            field.put("id", airport.getId());
            field.put("code", airport.getCode());
            field.put("name", airport.getName());
            record.add(field);
        }
        return record.toJSONString();
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
}
