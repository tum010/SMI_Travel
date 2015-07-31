package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourBookingPrice;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MPaymentDoctype;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PaymentDetailWendy;
import com.smi.travel.datalayer.entity.PaymentWendy;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.entity.TourOperationDesc;
import com.smi.travel.datalayer.entity.TourOperationDriver;
import com.smi.travel.datalayer.entity.TourOperationExpense;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.BookingDaytourService;
import com.smi.travel.datalayer.service.DaytourOperationService;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.http.converter.HttpMessageNotWritableException;
//import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

/**
 *
 * @author sumeta
 */
public class DaytourOperationDetailController extends SMITravelController {

    private static final ModelAndView DaytourOperationDetail = new ModelAndView("DaytourOperationDetail");
    private BookingAirticketService bookingAirticketService;
    private UtilityService utilservice;
    private DaytourOperationService daytourOperationService;
    private BookingDaytourService bookingDaytourService;
    private PaymentTourHotelService paymentTourHotelService;
    private static final String Bookiing_Size = "BookingSize";
    private static final String Master = "Master";
    private static final String DayTourList = "DayTourList";
    private static final String DayTourDetailList = "DayTourDetailList";
    private static final String DayTourOperation = "DayTourOperation";
    private static final String DaytourPrice = "DaytourPrice";
    private static final String DaytourPassengers = "DaytourPassengers";
    private static final String GuildeList = "GuildeList";
    private static final String DriverList = "DriverList";
    private static final String TourDescDriverList = "TourDescDriverList";
    private static final String TourDescExpenseList = "TourDescExpenseList";
    private static final String TransactionResult = "resultStatus";
    private static final String PickupList = "PickupList";
    private static final String MasterPrice = "MasterPrice";
    private static final String MasterExpen = "MasterExpen";
    private static final String MCurrency = "MCurrency";
    private static final String DaytourList = "DaytourList";
    private static final String TourList = "TourList";
    private static final String StatusList = "StatusList";
    private static final String InvoiceSupList = "InvoiceSupList";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String refNo = request.getParameter("referenceNo");
        String tourID = request.getParameter("tourID");
        String tourDate = request.getParameter("tourDate");

        String result = "";

        if ("new".equalsIgnoreCase(action)) {

        } else if ("edit".equalsIgnoreCase(action)) {
            System.out.print("tourID : "+ tourID);
            System.out.print("tourDate : "+ tourDate);
            
            if (tourID != null) {
                List<DaytourBooking> daytourBookingDetail = daytourOperationService.getTourDetail(tourID, tourDate);
                if(daytourBookingDetail != null){
                    Daytour daytour = daytourBookingDetail.get(0).getDaytour();
                    List<DaytourBooking> daytourBookings = new ArrayList<DaytourBooking>(daytour.getDaytourBookings());
                    List<DaytourBookingPrice> daytourBookingPrice = new ArrayList<DaytourBookingPrice>(daytourBookings.get(0).getDaytourBookingPrices());
                    CalculatePassengertype(daytourBookings);
                    Set daytourExpenses = daytour.getDaytourExpenses();
                    Set daytourPrice = daytour.getDaytourPrices();
                    Master master = daytourBookingDetail.get(0).getMaster();
                    Set passenger = master.getPassengers();
                    TourOperationDesc daytourOperation = daytourOperationService.getTouroperation(tourID, tourDate);
                    request.setAttribute(DaytourPrice, daytourBookingPrice);
                    request.setAttribute(DaytourPassengers, passenger);
                    request.setAttribute(DayTourDetailList,daytourOperationService.SortBookOrder(daytourBookingDetail));
                    System.out.println("start Tour Driver");
                    if(daytourOperation != null){

                        System.out.println("Tour Driver is not null");
                        List<TourOperationDriver> DriverList = daytourOperationService.SortDriver(new ArrayList<TourOperationDriver>(daytourOperation.getTourOperationDrivers()));
                        request.setAttribute(TourDescDriverList, daytourOperationService.SortDriver(DriverList));

                        List<TourOperationExpense> ExpenseList = new ArrayList<TourOperationExpense>(daytourOperation.getTourOperationExpenses());
                        request.setAttribute(TourDescExpenseList, daytourOperationService.SortExpense(ExpenseList));
                    }
                    request.setAttribute(DayTourOperation, daytourOperation);
                    request.setAttribute(MasterPrice, daytourPrice);
                    request.setAttribute(MasterExpen, daytourExpenses);
                    request.setAttribute(DaytourList, daytour);
                }
            }
            List<DaytourBooking> daytourBooking = daytourOperationService.getTourJob();
            request.setAttribute(DayTourList, daytourBooking);
            setGeneralResponseAttribute(request, refNo);
        } else if ("update".equalsIgnoreCase(action)) {
            result = updatetourOperationDesc(request, tourID, tourDate);
//            request.setAttribute(TransactionResult, "Save successful");
//            request.setAttribute("redirectUrl" , "DaytourOperationDetail.smi?action=edit&tourID=" + tourID + "&tourDate=" + tourDate);
            return new ModelAndView("redirect:DaytourOperationDetail.smi?action=edit&tourID=" + tourID + "&tourDate=" + tourDate + "&result=" + result);

        } else if ("deleteBookingDriver".equalsIgnoreCase(action)) {
            String driverId = request.getParameter("driverId");
            result = daytourOperationService.deleteBookDriver(driverId);
        } else if ("deleteBookingExpense".equalsIgnoreCase(action)) {
            String expenId = request.getParameter("refBookId");
            result = daytourOperationService.deleteBookExpen(expenId);
        } else if ("confirmGuideBill".equalsIgnoreCase(action)){
            String status = request.getParameter("status");
            String amount = request.getParameter("amount");
            SystemUser user = (SystemUser) session.getAttribute("USER");
            
            String InputDetailTourCode = request.getParameter("tourCode");
            String InputTourDetailTourDate = request.getParameter("tourDate");
            
            String guideName = request.getParameter("guideName");
            String InvoiceSupCode = paymentTourHotelService.getInvoiceSupCodeByGuideName(guideName);
                       
            UtilityFunction utilfunction = new UtilityFunction();
            PaymentWendy paymentWendy = new PaymentWendy();

            paymentWendy.setInvoiceSup(InvoiceSupCode);
            paymentWendy.setPayDate(null);
            paymentWendy.setApCode(null);
            paymentWendy.setDetail(InputDetailTourCode+":"+InputTourDetailTourDate);                
            paymentWendy.setRemark(null);
            paymentWendy.setCurrency(null);           
            paymentWendy.setChqNo(null);
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String createDate = sdf.format(date);
            paymentWendy.setCreateDate(utilfunction.convertStringToDate(createDate));
            paymentWendy.setAccount(null);
            paymentWendy.setMPaymentDoctype(null);                    
            paymentWendy.setMAccpay(null);
            paymentWendy.setCash(null);                           
            paymentWendy.setChqAmount(null);
            paymentWendy.setIsExport(0);
            paymentWendy.setCreateBy(user.getUsername());
            
            MItemstatus mitemStatus = new MItemstatus();
            if(status != ""){
                mitemStatus.setId(status);
                paymentWendy.setMItemstatus(mitemStatus);
            } else {
                paymentWendy.setMItemstatus(null);
            }
            
            PaymentDetailWendy paymentDetailWendy = new PaymentDetailWendy();
            
            MPaytype mpayType = new MPaytype();                   
            mpayType.setId("6");
            paymentDetailWendy.setMPaytype(mpayType);
            String acc_code = paymentTourHotelService.getAccountCode("6");
            paymentDetailWendy.setAccCode(acc_code);           
            paymentDetailWendy.setAmountType("T");
            paymentDetailWendy.setMaster(null);
            
            BigDecimal amountRe = new BigDecimal(amount.replaceAll(",",""));
            paymentDetailWendy.setAmount(amountRe);
            
            paymentWendy.getPaymentDetailWendies().add(paymentDetailWendy);

            result = paymentTourHotelService.InsertPaymentWendy(paymentWendy);
            System.out.println("result : " + result);
            if("fail".equalsIgnoreCase(result)){
                request.setAttribute("resultText", "fail");
            } else {
                request.setAttribute("resultText", "success");
                request.setAttribute("PayNoGuideBill", result);
            }
        }
        
        request.setAttribute("tourDate", tourDate);

        return DaytourOperationDetail;
    }

    public void setGeneralResponseAttribute(HttpServletRequest request, String refNo) {
        List<MCurrency> mCurrency = utilservice.getListMCurrency();
        request.setAttribute(MCurrency, mCurrency);
        List<SystemUser> guildeList = utilservice.getGuildeList();
        request.setAttribute(GuildeList, guildeList);
        List<SystemUser> driverList = utilservice.getDriverList();
        request.setAttribute(DriverList, driverList);
        List<Place> pickupList = utilservice.getPickupList();
        request.setAttribute(PickupList, pickupList);
        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);      
        List<Daytour> tourList = bookingDaytourService.getTourList();
        request.setAttribute(TourList, tourList);
        List<MItemstatus> statusList = utilservice.getListMItemstatus();
        request.setAttribute(StatusList, statusList);
        List<InvoiceSupplier> invoiceSupplierList = getPaymentTourHotelService().getListInvoiceSuppiler();
        request.setAttribute(InvoiceSupList, invoiceSupplierList);

    }

    private String updatetourOperationDesc(HttpServletRequest request, String tourID, String tourDate) {
        String result = "";
        String meal = request.getParameter("TextareaMeal");
        String info = request.getParameter("TextareaTransferInfo");
        String remark = request.getParameter("TextareaRemark");
        String guide1Id = request.getParameter("SelectGuideCode1");
        String guide2Id = request.getParameter("SelectGuideCode2");

        TourOperationDesc tourOperationDesc = daytourOperationService.getTouroperation(tourID, tourDate);
        if (tourOperationDesc == null) {
            tourOperationDesc = new TourOperationDesc();
        }
        Daytour daytour = tourOperationDesc.getDaytour();
        if (daytour == null) {
            daytour = new Daytour();
            daytour.setId(tourID);
        }

        SystemUser guide1 = new SystemUser();
        SystemUser guide2 = new SystemUser();
        
        if(!"".equalsIgnoreCase(guide1Id)){
            guide1.setId(guide1Id);
            tourOperationDesc.setStaffByGuide1(guide1);
        }
        if(!"".equalsIgnoreCase(guide2Id)){
            guide2.setId(guide2Id);
            tourOperationDesc.setStaffByGuide2(guide2);
        }
        
        
        UtilityFunction util = new UtilityFunction();
        tourOperationDesc.setDaytour(daytour);
        tourOperationDesc.setMeal(meal);
        tourOperationDesc.setRemark(remark);
        
        
        tourOperationDesc.setTourDate(util.convertStringToDate(tourDate));
        
        setTourOperationExpenses(request, tourOperationDesc);
        tourOperationDesc.setTransferInfo(info);
        List<DaytourBooking> daytourBookings = setDaytourBookings(request, tourOperationDesc, tourID, tourDate);
        result = daytourOperationService.saveTourOperation(tourOperationDesc, daytourBookings,setTourOperationDrivers(request, tourOperationDesc));
        return result;
    }

    private List<TourOperationDriver> setTourOperationDrivers(HttpServletRequest request, TourOperationDesc tourOperationDesc) {
        UtilityFunction util = new UtilityFunction();
        String driverRow = request.getParameter("countInfoTable");
        
        List<TourOperationDriver> DriverList = new LinkedList<TourOperationDriver>();
        if (driverRow == null) {
            return null;
        }
        int driverRows = Integer.parseInt(driverRow);
        if (driverRows == 1) {
            return null;
        }
        if(driverRows==5){
         driverRows = 6;
        }
        for (int i = 1; i < driverRows; i++) {
            String id = request.getParameter("InfoTableId" + i);
            String driverId = request.getParameter("SelectTableDrive" + i);
            String carNo = request.getParameter("carNo" + i);
            String gasFee = request.getParameter("InfoTableGasFee" + i);
            String gasValue = request.getParameter("InfoTableGasValue" + i);
            String tipFee = request.getParameter("InfoTableTipFee" + i);
            String tipValue = request.getParameter("InfoTableTipValue" + i);
            TourOperationDriver driver = getTourOperationDrivers(id, tourOperationDesc);

            driver.setId(id);
            SystemUser staff = new SystemUser();
            staff.setId(driverId);
            driver.setStaff(staff);
            driver.setCarNo(carNo);
            driver.setGasFee(gasFee);
            driver.setGasValue(util.convertStringToInteger(gasValue));
            driver.setTourOperationDesc(tourOperationDesc);
            driver.setTipFee(tipFee);
            driver.setTipValue(util.convertStringToInteger(tipValue));
            if(!"".equalsIgnoreCase(driverId)){
                DriverList.add(driver);
            }
            
            
        }
        System.out.println("DriverList size : "+DriverList.size());
        return DriverList;

    }
    
    public void CalculatePassengertype(List<DaytourBooking> daytourBookingDetail){
        for(int i=0;i<daytourBookingDetail.size();i++){
            DaytourBooking data = daytourBookingDetail.get(i);
            List<DaytourBookingPrice> PriceList = new ArrayList<DaytourBookingPrice>(data.getDaytourBookingPrices());
            String[] passengerCount = daytourOperationService.calculatePassengerDaytour(PriceList);
            data.setAdult(Integer.parseInt(passengerCount[0]));
            data.setChild(Integer.parseInt(passengerCount[1]));
            data.setInfant(Integer.parseInt(passengerCount[2]));
            System.out.println("passenger :"+data.getAdult()+" , "+data.getChild()+" , "+data.getInfant());
        }
    }

    private TourOperationDriver getTourOperationDrivers(String tourId, TourOperationDesc tourOperationDesc) {
        if (tourId == null) {
            TourOperationDriver driver = new TourOperationDriver();
            return driver;
        }

        Set<TourOperationDriver> tourOperationDrivers = tourOperationDesc.getTourOperationDrivers();
        for (TourOperationDriver driver : tourOperationDrivers) {
            if (tourId.equalsIgnoreCase(driver.getId())) {
                return driver;
            }
        }
        return null;
    }

    private void setTourOperationExpenses(HttpServletRequest request, TourOperationDesc tourOperationDesc) {
        UtilityFunction util = new UtilityFunction();
        String expenRow = request.getParameter("countExpen");
        Set expense = tourOperationDesc.getTourOperationExpenses();
        int row = Integer.parseInt(expenRow);
        if (row == 1) {
            return;
        }
        for (int i = 1; i <= row; i++) {
            String id = request.getParameter("expenId" + i);
            String description = request.getParameter("expenDescription" + i);
            String qty = request.getParameter("expenQty" + i);
            String amount = request.getParameter("expenAmount" + i);
            String priceType = request.getParameter("expenPriceType" + i);
            String selectCur = request.getParameter("expenSelectCur" + i);

            TourOperationExpense tourOperationExpense = gettourOperationExpense(id, tourOperationDesc);
            tourOperationExpense.setDescription(description);
            tourOperationExpense.setQty(util.convertStringToInteger(qty));
            tourOperationExpense.setAmount(util.convertStringToInteger(amount));
            tourOperationExpense.setPriceType(priceType);
            tourOperationExpense.setCurrency(selectCur);
            tourOperationExpense.setTourOperationDesc(tourOperationDesc);
            expense.add(tourOperationExpense);
        }
        tourOperationDesc.setTourOperationExpenses(expense);

    }

    private TourOperationExpense gettourOperationExpense(String tourId, TourOperationDesc tourOperationDesc) {
        if ("".equals(tourId)) {
            TourOperationExpense expense = new TourOperationExpense();
            return expense;
        }
        if (tourId == null) {
            TourOperationExpense expense = new TourOperationExpense();
            return expense;
        }
        Set<TourOperationExpense> operationExpenses = tourOperationDesc.getTourOperationExpenses();
        for (TourOperationExpense expen : operationExpenses) {
            if (tourId.equalsIgnoreCase(expen.getId())) {
                return expen;
            }
        }
        return null;
    }

    private List<DaytourBooking> setDaytourBookings(HttpServletRequest request, TourOperationDesc tourOperationDesc, String tourID, String tourDate) {
        UtilityFunction util = new UtilityFunction();
        List<DaytourBooking> daytourBookingDetail = daytourOperationService.getTourDetail(tourID, tourDate);
        int row = daytourBookingDetail.size();
        for (int i = 0; i < row; i++) {
            String id = request.getParameter("refBookId" + (i + 1));
            String pu = request.getParameter("InputPu" + (i + 1));
            daytourBookingDetail.get(i).setId(id);
            daytourBookingDetail.get(i).setPickupOrder(util.convertStringToInteger(pu));
        }
        return daytourBookingDetail;
    }

    public BookingAirticketService getBookingAirticketService() {
        return bookingAirticketService;
    }

    public void setBookingAirticketService(BookingAirticketService bookingAirticketService) {
        this.bookingAirticketService = bookingAirticketService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public DaytourOperationService getDaytourOperationService() {
        return daytourOperationService;
    }

    public void setDaytourOperationService(DaytourOperationService daytourOperationService) {
        this.daytourOperationService = daytourOperationService;
    }

    public BookingDaytourService getBookingDaytourService() {
        return bookingDaytourService;
    }

    public void setBookingDaytourService(BookingDaytourService bookingDaytourService) {
        this.bookingDaytourService = bookingDaytourService;
    }

    /**
     * @return the paymentTourHotelService
     */
    public PaymentTourHotelService getPaymentTourHotelService() {
        return paymentTourHotelService;
    }

    /**
     * @param paymentTourHotelService the paymentTourHotelService to set
     */
    public void setPaymentTourHotelService(PaymentTourHotelService paymentTourHotelService) {
        this.paymentTourHotelService = paymentTourHotelService;
    }

   
}
