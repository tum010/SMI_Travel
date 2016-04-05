package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.entity.TransferJob;
import com.smi.travel.datalayer.service.DaytourTransferJobService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
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
public class DaytourTransferController extends SMITravelController {

    private static final Logger log = Logger.getLogger(DaytourTransferController.class.getName());
    private static final ModelAndView DaytourTransfer = new ModelAndView("DaytourTransfer");
    private UtilityService utilservice;
    private UtilityFunction util;
    private DaytourTransferJobService daytourTransferJobService;
    private static final String GuideList = "GuideList";
    private static final String DriverList = "DriverList";
    private static final String TourList = "TourList";
    private static final String HotelList = "HotelList";
    private static final String TRANSFERJOB = "TransferJob";
    private static final String OtherList = "OtherList";
    private static final String TransactionResult = "result";
    private static final String TOURDATE = "tourdate";
    private static final String NEWACTION = "newAction";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        String action = request.getParameter("action");
        String transferJobId = request.getParameter("transferJobId");
        String documentNo = request.getParameter("InputDocument");
        String tourDateS = util.covertStringDateToFormatYMD(request.getParameter("InputDate"));
        String guide = request.getParameter("InputGuide");
        String driver = request.getParameter("InputDriver");
        String remark = request.getParameter("InputRemark");
        String tourCodeList = request.getParameter("tourTableList");
        String hotelList = request.getParameter("hotelTableList");
        String otherList = request.getParameter("otherTableList");

        log.info("action = " + action + ", guide=" + guide + ", driver=" + driver + ", remark=" + remark);
        if ("delete".equalsIgnoreCase(action)) {
            return new ModelAndView("redirect:HotelBooking.smi?action=edit");
           
            
        } else if ("edit".equalsIgnoreCase(action)) {
            log.info("transferJobId =" + transferJobId);
        } else if ("save".equalsIgnoreCase(action) || ("saveANDnew").equalsIgnoreCase(action)) {
            log.info("transferJobId =" + transferJobId);
            log.info("save");
            TransferJob job = new TransferJob();

            if (StringUtils.isNotEmpty(transferJobId)) {
                job.setId(transferJobId);
            }

            if (StringUtils.isNotEmpty(documentNo)) {
                job.setDocumentNo(documentNo);
            }

            util = new UtilityFunction();
             Date tourDate = null;
            if(StringUtils.isNoneEmpty(tourDateS)){
                 tourDate = util.convertStringToDate(tourDateS);
            }
           
            
            job.setTransferDate(tourDate);

            if (StringUtils.isNotEmpty(guide)) {
                SystemUser guideJob = new SystemUser();
                guideJob.setId(guide);
                job.setStaffByGuildeId(guideJob);
            }

            if (StringUtils.isNotEmpty(driver)) {
                SystemUser driverJob = new SystemUser();
                driverJob.setId(driver);
                job.setStaffByDriverId(driverJob);
            }
            
            log.info("tourCodeList = "+tourCodeList);
            log.info("hotelList = "+hotelList);
            log.info("otherList = "+otherList);
//            String[] tourcodes = tourCodeList.split(Pattern.quote("||")); //\\s*||\\s*
//            String[] hotels = hotelList.split(Pattern.quote("||"));
//            int count = 0; 
//            for (String tourcode : tourcodes) {
//                count++;
//                log.info("tourcode"+ count +" = "+tourcode.trim());
//                     job.setTour(tourcode.trim());
//            } 
//            for (String hotel : hotels) {
//                count++;
//                log.info("hotel"+count+" = "+hotel.trim());
//                    job.setPlace(hotel.trim());
//
//            } 
            
            job.setRemark(remark);
            job.setTour(tourCodeList);
            job.setPlace(hotelList);
            job.setPlaceOther(otherList);
            
           
            String result = daytourTransferJobService.saveTransferjob(job);
            log.info("result =" + result + ", job.id=" + job.getId());

            String tourDateStr = util.convertDateToString(job.getTransferDate());
            request.setAttribute(TOURDATE, tourDateStr);
            if ("saveANDnew".equalsIgnoreCase(action)) {
                log.info("Clear Save&New");
                job.setRemark("");
                job.setStaffByDriverId(null);
                job.setStaffByGuildeId(null);
                job.setDocumentNo("");
                job.setPlace(null);
                job.setPlaceOther(null);
                request.setAttribute(NEWACTION, "1");
            } 
            request.setAttribute(TRANSFERJOB, job);
            request.setAttribute(TransactionResult, result);
//            return new ModelAndView("redirect:DaytourTransfer.smi?action=edit&transferJobId=" + job.getId());
        } 
//        else {
//            setGeneralResponseAttribute(request);
//        }
        setGeneralResponseAttribute(request);

        return DaytourTransfer;
    }

    public void setGeneralResponseAttribute(HttpServletRequest request) {
        List<SystemUser> guideList = utilservice.getGuildeList();
        request.setAttribute(GuideList, guideList);
        List<SystemUser> driverList = utilservice.getDriverList();
        request.setAttribute(DriverList, driverList);

    }

    public DaytourTransferJobService getDaytourTransferJobService() {
        return daytourTransferJobService;
    }

    public void setDaytourTransferJobService(DaytourTransferJobService daytourTransferJobService) {
        this.daytourTransferJobService = daytourTransferJobService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }
}
