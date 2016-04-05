package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.TransferJob;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.BookingHotelService;
import com.smi.travel.datalayer.service.DaytourTransferJobService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author sumeta
 */
public class DaytourHistoryTransferController extends SMITravelController {

    private static final Logger log = Logger.getLogger(DaytourHistoryTransferController.class.getName());
    private static final ModelAndView DaytourHistoryTransfer = new ModelAndView("DaytourHistoryTransfer");
    private UtilityService utilservice;
    private DaytourTransferJobService daytourTransferJobService;

    private static final String DataList = "transferJobs";
    private static final String DateTo = "dateTo";
    private static final String DateFrom = "dateFrom";
    private static final String Hotel = "Hotel";

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        String action = request.getParameter("action");
        String dateFrom = util.covertStringDateToFormatYMD(request.getParameter("InputDateFrom"));
        String dateTo = util.covertStringDateToFormatYMD(request.getParameter("InputDateTo"));
        String hotel = request.getParameter("hotel");
        log.info("action = " + action + ", dateFrom = " + dateFrom + ", dateTo = " + dateTo);
        if ("search".equalsIgnoreCase(action)) {
            List<TransferJob> transferJobs = daytourTransferJobService.searchTransferJob(dateFrom, dateTo,hotel);
            request.setAttribute(DataList, transferJobs);
            request.setAttribute(DateTo, dateTo);
            request.setAttribute(DateFrom, dateFrom);
            request.setAttribute(Hotel, hotel);
            setGeneralResponseAttribute(request);
        } else {
            setGeneralResponseAttribute(request);
        }

        return DaytourHistoryTransfer;
    }

    public void setGeneralResponseAttribute(HttpServletRequest request) {
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
