package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Coupon;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourBookingPrice;
import com.smi.travel.datalayer.entity.HistoryBooking;
import com.smi.travel.datalayer.entity.LandBooking;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MPricecategory;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingDaytourService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.BillableView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sumeta
 */
public class DaytourDetailController extends SMITravelController {

    private static final Logger log = Logger.getLogger(DaytourDetailController.class.getName());
    private static final ModelAndView DaytourDetail = new ModelAndView("DaytourDetail");
    private UtilityService utilservice;
    private UtilityFunction utilfunction;
    private BookingDaytourService bookingDaytourService;
    private static final String Bookiing_Size = "BookingSize";
    private static final String Master = "Master";
    private static final String TourList = "TourList";
    private static final String Places = "Places";
    private static final String ACTION = "action";
    private static final String DAYTOURBOOKING = "DaytourBooking";
    private static final String TransactionResult = "TransactionResult";
    private static final String Coupons = "Coupons";
    private static final String DAYTOURBOOKPRICES = "DAYTOURBOOKPRICES";
    private static final String GuideList = "GuideList";
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private static final String MCurrency = "MCurrency";
    private static final String ISBILLSTATUS = "IsBillStatus";
    private static final String EnableSave = "EnableSave";
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        utilfunction = new UtilityFunction();
        String action = request.getParameter("action");
        String refNo = request.getParameter("referenceNo");
        String dBookingId = request.getParameter("daytourBooking");

        String tourId = request.getParameter("InputTourId");
        String tourCode = request.getParameter("InputTourCode");
        String tourDateS = request.getParameter("InputTourDate");
        String sNumAdult = request.getParameter("InputAdult");
        String sNumChild = request.getParameter("InputChild");
        String sNumInfant = request.getParameter("InputInfant");
        String guide = request.getParameter("InputGuide");
        String payS = request.getParameter("CheckPay");
        String pickupId = request.getParameter("SelectPickUp");
        String pickupOther = request.getParameter("InputPickUp");
        String room = request.getParameter("InputRoom");
        String time = request.getParameter("InputTime");
        String requirement = request.getParameter("TextareaRequirement");
        String memo = request.getParameter("TextareaMemo");
        String remark = request.getParameter("TextareaRemark");
        Integer pay = 0;
        String counterPrice = request.getParameter("counterPrice");
        String counterCoupon = request.getParameter("counterCoupon");

        if (StringUtils.isNotEmpty(payS)) {
            pay = 1;
        }

        DaytourBooking dBooking = new DaytourBooking();
        request.setAttribute(ISBILLSTATUS,0);
        SystemUser user = (SystemUser) session.getAttribute("USER");
        log.info("action[" + action + "] refNo[" + refNo + "] counterPrice(" + counterPrice + "), counterCoupon(" + counterCoupon + ")");
        if ("delete".equalsIgnoreCase(action)) {
            System.out.println("Delete me");
            String bookingPriceId = request.getParameter("deleteId");
//            log.info("bookingPriceId = " + bookingPriceId);
            String result = bookingDaytourService.DeleteBookingDaytourPrice(bookingPriceId);
            log.info("delete result = " + result);
            return new ModelAndView("redirect:DaytourDetail.smi?referenceNo=" + refNo + "&action=edit&daytourBooking=" + dBookingId + "&result=" + result);
        } else if ("enable".equalsIgnoreCase(action)) {
            return new ModelAndView("redirect:DaytourDetail.smi?referenceNo=" + refNo + "&action=edit");
        } else if ("new".equalsIgnoreCase(action)) {
            request.setAttribute(ACTION, "update");
            setGeneralResponseAttribute(request, refNo);
            request.setAttribute(EnableSave,0);
        } else if ("edit".equalsIgnoreCase(action)) {
            log.info("dBookingId " + dBookingId);
            // Setup new DaytourBooking;

            DaytourBooking bDaytour = bookingDaytourService.getBookDetailDaytourFromID(dBookingId);
            
            saveHistoryBooking(refNo,user,bDaytour,"VIEW");
            
            List<DaytourBookingPrice> bookPriceList = new ArrayList(bDaytour.getDaytourBookingPrices());
            Collections.sort(bookPriceList, new Comparator<DaytourBookingPrice>(){
                public int compare(DaytourBookingPrice one, DaytourBookingPrice two) {
                    return one.getId().compareTo(two.getId());
                }
            });
            System.out.println(" bDaytour.getIsBill() " + bDaytour.getIsBill());
            request.setAttribute(ISBILLSTATUS,bDaytour.getIsBill());
            
            request.setAttribute(DAYTOURBOOKPRICES,bookPriceList);
            
            request.setAttribute(DAYTOURBOOKING, bDaytour);
            setGeneralResponseAttribute(request, refNo);
            
            String billDescId = utilservice.getBillableDescId(dBookingId, "6");
            if("".equalsIgnoreCase(billDescId)){
                request.setAttribute(EnableSave,1);
            }else{
                request.setAttribute(EnableSave,0);
                BillableView billableView = utilservice.getBillableDescByBookId(dBookingId);
                int resultupdate = utilservice.updateBillableDesc(billableView,billDescId);
            }
            
        } else if ("save".equalsIgnoreCase(action)) {
            System.out.println("Save me");
            log.info("dBookingId " + dBookingId + ", tourId " + tourId + "; pay" + pay + ";time" + time + ";pickupId=" + pickupId + ";pickupOther=" + pickupOther);
            DaytourBooking bDaytour = null;
            if (StringUtils.isEmpty(dBookingId)) {
                bDaytour = new DaytourBooking();
            } else {
                bDaytour = bookingDaytourService.getBookDetailDaytourFromID(dBookingId);
            }
            Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
            bDaytour.setMaster(master);

            Daytour refDaytour = bookingDaytourService.getDaytourdao().getTourFromID(tourId);
            bDaytour.setDaytour(refDaytour);
            Date tourDate = utilfunction.convertStringToDate(tourDateS);
            bDaytour.setTourDate(tourDate);

            Integer adult = utilfunction.convertStringToInteger(sNumAdult);
            bDaytour.setAdult(adult);
            Integer child = utilfunction.convertStringToInteger(sNumChild);
            bDaytour.setChild(child);
            Integer infant = utilfunction.convertStringToInteger(sNumInfant);
            bDaytour.setInfant(infant);

            Place p = new Place();
            p.setId(pickupId);
//            if ("1".equalsIgnoreCase(pickupId)) {///==1
                bDaytour.setPickupDetail(pickupOther);
//            }

//            if (StringUtils.isNotEmpty(guide.trim())) {
//                SystemUser guideB = new SystemUser();
//                guideB.setId(guide);
//                bDaytour.setGuide(guideB);
//            }
            bDaytour.setPlace(p);
            if (StringUtils.isNotEmpty(room)) {
                bDaytour.setPickupRoom(room);
            }
            bDaytour.setMemo(memo);
            bDaytour.setRequirement(requirement);
            bDaytour.setRemark(remark);
            bDaytour.setIsPay(pay);
            if (StringUtils.isNotEmpty(time)) {
                bDaytour.setPickupTime(utilfunction.convertStringToTime(time));
            }

            if (StringUtils.isNotEmpty(dBookingId)) {
                bDaytour.setId(dBookingId);
                DaytourBooking dbDaytour = bookingDaytourService.getBookDetailDaytourFromID(dBookingId);
                bDaytour.setMItemstatus(dbDaytour.getMItemstatus());
                bDaytour.setIsBill(dbDaytour.getIsBill());

                request.setAttribute(ISBILLSTATUS,dbDaytour.getIsBill());
                saveHistoryBooking(refNo,user,bDaytour,"UPDATE");
            } else {
                MItemstatus okayStatus = new MItemstatus();
                okayStatus.setId("1");
                bDaytour.setMItemstatus(okayStatus);
                bDaytour.setIsBill(new Integer(0));

                request.setAttribute(ISBILLSTATUS,bDaytour.getIsBill());
                saveHistoryBooking(refNo,user,bDaytour,"CREATE");
            }
            setDaytourPriceRows(request, counterPrice, bDaytour);
            setDaytourCouponRows(request, counterCoupon, bDaytour);

            String result = bookingDaytourService.saveBookingDaytour(bDaytour);
            log.info("saveBookingDaytour result " + result);

            setGeneralResponseAttribute(request, refNo);
            
            String billDescId = utilservice.getBillableDescId(dBookingId, "6");
            if("".equalsIgnoreCase(billDescId)){
                request.setAttribute(EnableSave,1);
            }else{
                request.setAttribute(EnableSave,0);
                BillableView billableView = utilservice.getBillableDescByBookId(dBookingId);
                int resultupdate = utilservice.updateBillableDesc(billableView,billDescId);
            }
            
            if (StringUtils.isNotEmpty(dBookingId)) {
                return new ModelAndView("redirect:DaytourDetail.smi?referenceNo=" + refNo + "&action=edit&daytourBooking=" + dBookingId + "&result=" + result);
            } else {
                return new ModelAndView("redirect:Daytour.smi?referenceNo=" + refNo + "&action=edit&result="+result);
            }

        } else if ("deletePrice".equalsIgnoreCase(action)){
            System.out.println("deletePrice");
            log.info("deletePrice tourCode " + tourCode  + ", daytourBookingId(" + dBookingId + ")");
            String result = bookingDaytourService.DeleteBookingDaytourPriceNotMatch(tourCode, dBookingId);
            log.info("DeleteDaytourPrice Result=" + result);
            request.setAttribute(TransactionResult, result);
        
        } else {
            setGeneralResponseAttribute(request, refNo);
        }

        return DaytourDetail;
    }

    public void setGeneralResponseAttribute(HttpServletRequest request, String refNo) {

        List<Daytour> tourList = bookingDaytourService.getTourList();
        request.setAttribute(TourList, tourList);

        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        request.setAttribute(Bookiing_Size, booksize);
        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);
        List<Place> listPlaces = utilservice.getPickupList();
        request.setAttribute(Places, listPlaces);

        List<SystemUser> guideList = utilservice.getGuildeList();
        request.setAttribute(GuideList, guideList);

        List<OtherBooking> couponList = bookingDaytourService.getCouponList(refNo);
        request.setAttribute(Coupons, couponList);
        if(("1").equals(String.valueOf(master.getFlagDaytour())) 
            || ("2").equals(String.valueOf(master.getMBookingstatus().getId()))
            || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }
        
        List<MCurrency> mCurrency = utilservice.getListMCurrency();
        request.setAttribute(MCurrency, mCurrency);
    }

    //price
    private void setDaytourPriceRows(HttpServletRequest request, String priceCounter, DaytourBooking daytourbooking) {
        utilfunction = new UtilityFunction();
        int priceRows = 0;
        if (priceCounter != null) {
            priceRows = Integer.parseInt(priceCounter);
        }

        for (int i = 1; i <= priceRows; i++) {
            String id = request.getParameter("row-" + i + "-bookingpriceid");
            String categoryid = request.getParameter("row-" + i + "-pricecategoryid");
            String deatil = request.getParameter("row-" + i + "-pricedetail");
            String amount = request.getParameter("row-" + i + "-priceamount");
            String qty = request.getParameter("row-" + i + "-priceqty");
            String currency = request.getParameter("row-" + i + "-pricecurrency");
            //String priceDefault = request.getParameter("row-" + i + "-priceDefault");

            log.info("DaytourPrice row(" + priceRows + ") id(" + id + ") amount(" + amount + ")");
            Integer amountInt = null;
            Integer qtyInt = null;
            //Integer priceDefaultInt = null;

            if (StringUtils.isNotEmpty(amount)) {
                amountInt = utilfunction.convertStringToInteger(amount);
            }
            if (StringUtils.isNotEmpty(qty)) {
                qtyInt = utilfunction.convertStringToInteger(qty);
            }
            //            if (StringUtils.isNotEmpty(priceDefault)) {
            //                priceDefaultInt = utilfunction.convertStringToInteger(priceDefault);
            //            }
            log.info("amountInt= " + amountInt);
            log.info("qtyInt= " + qtyInt);
            log.info("categoryid= " + categoryid);
            //log.info("priceDefault= " + priceDefaultInt);
            DaytourBookingPrice daytourprice = getDaytourPrice(id, daytourbooking);

            if (daytourprice == null) {
                daytourprice = new DaytourBookingPrice();
            }

            MPricecategory category = new MPricecategory();
            category.setId(categoryid);

            daytourprice.setMPricecategory(category);
            daytourprice.setDetail(deatil);
            daytourprice.setPrice(amountInt);
            daytourprice.setQty(qtyInt);
            daytourprice.setCurrency(currency);
            //daytourprice.setPdefault(priceDefaultInt);

            if (daytourprice.getId() == null) {
                log.info("save daytourprice begin");
                daytourbooking.getDaytourBookingPrices().add(daytourprice);
                daytourprice.setDaytourBooking(daytourbooking);
            }
        }
    }

    private DaytourBookingPrice getDaytourPrice(String daytourPriceId, DaytourBooking daytourbooking) {
        if (daytourPriceId == null) {
            return null;
        }
        if (daytourbooking.getDaytourBookingPrices().isEmpty()) {
            return null;
        }

        Iterator<DaytourBookingPrice> iterator = daytourbooking.getDaytourBookingPrices().iterator();
        while (iterator.hasNext()) {
            DaytourBookingPrice daytourprice = iterator.next();

            if (daytourPriceId.equalsIgnoreCase(daytourprice.getId())) {
                return daytourprice;
            }
        }
        return null;
    }

    //coupon
    private void setDaytourCouponRows(HttpServletRequest request, String couponCounter, DaytourBooking daytourbooking) {
        utilfunction = new UtilityFunction();
        int couponRows = 0;
        if ((couponCounter != null)&&(!"".equalsIgnoreCase(couponCounter))) {
            couponRows = Integer.parseInt(couponCounter);
        }

        for (int i = 1; i <= couponRows; i++) {
            String bOtherId = request.getParameter("row-" + i + "-bookingotherid");
            String couponId = request.getParameter("row-" + i + "-couponid");
            String couponCheck = request.getParameter("row-" + i + "-couponcheck");

            log.info("DaytourCoupon row(" + i + ")  bOtherId(" + bOtherId + "), couponId(" + couponId + "), couponCheck(" + couponCheck + ")");
            Coupon c = getCoupon(couponId, daytourbooking);

            if (StringUtils.isEmpty(couponCheck)) {
                //not checked
                if (c != null) {
                    daytourbooking.getCoupons().remove(c);
                }
            } else {
                //checked
                if (c == null) {
                    c = new Coupon();
                    c.setDaytourBooking(daytourbooking);
                    OtherBooking other = new OtherBooking();
                    other.setId(bOtherId);
                    c.setOtherBooking(other);

                    daytourbooking.getCoupons().add(c);
                }
            }

        }
    }

    private Coupon getCoupon(String couponId, DaytourBooking daytourbooking) {
        if (couponId == null) {
            return null;
        }
        if (daytourbooking.getCoupons().isEmpty()) {
            return null;
        }

        Iterator<Coupon> iterator = daytourbooking.getCoupons().iterator();
        while (iterator.hasNext()) {
            Coupon coupon = iterator.next();

            if (couponId.equalsIgnoreCase(coupon.getId())) {
                return coupon;
            }
        }
        return null;
    }
    
    public void saveHistoryBooking(String refNo,SystemUser user,DaytourBooking daytour,String action) {
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");        
        HistoryBooking historyBooking = new HistoryBooking();
        historyBooking.setHistoryDate(new Date());
        historyBooking.setAction(action+" DAY TOURS BOOKING");
        String detail = "";
        if(!"VIEW".equalsIgnoreCase(action)){
            if(daytour.getDaytour() != null ){
                detail += "TOUR : " + daytour.getDaytour().getCode() + " : " +daytour.getDaytour().getName() + "\r\n" ;
            }else{
                detail += "TOUR : " + "\r\n" ;
            }
            if(daytour.getTourDate() != null){
                detail += "DATE : " + String.valueOf(df.format(daytour.getTourDate()))  + "\r\n" ;
            }else{
                detail += "DATE : " + "\r\n" ;
            }
                detail += "PAX : ADT : " + daytour.getAdult() + " CHD : " + daytour.getChild() + " INF : " + daytour.getInfant() + "\r\n"
                    + "PICK UP : " + daytour.getPickupDetail() +"\r\n"
                    + "MEMO : " + daytour.getMemo() ;
        }
        historyBooking.setDetail(detail);
        historyBooking.setMaster(daytour.getMaster());
        historyBooking.setStaff(user);
        int resultsave = utilservice.insertHistoryBooking(historyBooking);
    }
    
    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public BookingDaytourService getBookingDaytourService() {
        return bookingDaytourService;
    }

    public void setBookingDaytourService(BookingDaytourService bookingDaytourService) {
        this.bookingDaytourService = bookingDaytourService;
    }

}
