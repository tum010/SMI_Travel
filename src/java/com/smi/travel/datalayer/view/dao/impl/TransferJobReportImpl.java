/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;
//test
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourBookingPrice;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.TourOperationDesc;
import com.smi.travel.datalayer.entity.TransferJob;
import com.smi.travel.datalayer.report.model.TransferJobReport;
import com.smi.travel.datalayer.view.dao.TransferJobReportDao;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Surachai
 */
public class TransferJobReportImpl implements TransferJobReportDao {

    private SessionFactory sessionFactory;
    private static final String GET_TRANSFER_JOB ="from TransferJob tj where tj.documentNo = :docno";
    @Override
    public List getTransferJobReport(String docno) {
        List datalist = new ArrayList();
        Session session = this.sessionFactory.openSession();
        List<TransferJob> jobList = session.createQuery(GET_TRANSFER_JOB)
                .setParameter("docno", docno)
                .list();
        if (jobList.isEmpty()) {
            return null;
        }else{
            
            TransferJob job = jobList.get(0);
            datalist =  getTransferjobData(job);
        }    
        this.sessionFactory.close();
        session.close();
        return datalist;
    }
    
    public List getTransferjobData(TransferJob job) {
        UtilityFunction util = new UtilityFunction();
        String TourDate = util.convertDateToString(job.getTransferDate());
        String TourId = job.getTour();
        String Place = job.getPlace();
        String Other = job.getPlaceOther();

        String getJobDetailQuery = "from DaytourBooking DB where DB.tourDate = '" + TourDate + "'"
                + " and DB.daytour.code in ('" + TourId.replaceAll(" ", "").replaceAll("\\|\\|", "','").trim() + "') ";
        String open = "";
        String close = "";
        List Transferjoblist = new ArrayList();
        if (Place != null) {
            if (Other != null) {
                open = "(";
                close = ")";
            }
            getJobDetailQuery += " and " + open + " DB.place.place in ('" + Place.replaceAll("\\|\\|", "','").replaceAll(" '", "'").replaceAll("' ", "'") + "')";
        }
        if (Other != null) {
            getJobDetailQuery += " or DB.pickupDetail in ('" + Other.replaceAll("\\|\\|", "','") + "') " + close;
        }
        getJobDetailQuery += "  ORDER BY DB.pickupTime";

        System.out.println("getJobDetailQuery : " + getJobDetailQuery);

        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = session.createQuery(getJobDetailQuery)
                .list();
        if (list.isEmpty()) {
            return null;
        }else{
            Transferjoblist = setTransferJobReportModel(list,job);
        }
        
        this.sessionFactory.close();
        session.close();
        return Transferjoblist;
    }
    
    private String getGuideTour(String tourid , Date tourdate) {
        String GuideTour = "";
        List datalist = new ArrayList();
        Session session = this.sessionFactory.openSession();
       
        System.out.println("getGuideTour query: "+GuideTour);
        List<TourOperationDesc> jobList = session.createQuery("from TourOperationDesc tod where tod.daytour.id = :tourid and tod.tourDate = :tourdate")
                .setParameter("tourid", tourid)
                .setParameter("tourdate", tourdate)
                .list();
        if (jobList.isEmpty()) {
            return "";
        }else{
            if(jobList.get(0).getStaffByGuide1() != null){
                GuideTour = jobList.get(0).getStaffByGuide1().getName();        
            }   
        }    
        
        System.out.println("getGuideTour : "+GuideTour);
        this.sessionFactory.close();
        session.close();
        return GuideTour;
    }
    
    private List setTransferJobReportModel(List<DaytourBooking> Booklist,TransferJob job){
        List list = new ArrayList();
        UtilityFunction util = new UtilityFunction();
        for(int i=0;i<Booklist.size();i++){
             TransferJobReport report = new TransferJobReport();
            DaytourBooking book = Booklist.get(i);
            Master booking = book.getMaster();
            Customer cus = booking.getCustomer();
            List<DaytourBookingPrice> PriceList = new ArrayList<DaytourBookingPrice>(book.getDaytourBookingPrices());
            report.setNo(String.valueOf(i+1));
            String[] passenger = calculatePassengerDaytour(PriceList);
            if(book.getPlace() != null){
                String place = book.getPlace().getPlace();
                String memo = "";
                if((book.getMemo() != null)&&(!"".equalsIgnoreCase(book.getMemo()))){
//                    place += "<br>"+book.getMemo();
                    memo = book.getMemo();
                }
                report.setPlace(place);
                report.setMemo(memo);
                System.out.println("place : "+place);
            }else{
                report.setPlace("");
            }
            
            
            if(book.getPickupRoom() == null){
                report.setRoom("");
            }else{
                report.setRoom(book.getPickupRoom());
            }
            
            report.setTime(util.convertTimeToString(book.getPickupTime()));
            report.setName(cus.getMInitialname().getName() +" "+ cus.getLastName() +" "+ cus.getFirstName()+"<br>"+cus.getTel());
            report.setAd(Integer.parseInt(passenger[0]));
            report.setCh(Integer.parseInt(passenger[1]));
            report.setIn(Integer.parseInt(passenger[2]));
            
            if(!PriceList.isEmpty()){
                if(PriceList.get(0).getDetail() != null){
                    
                    report.setCouse(PriceList.get(0).getDetail());
                }else{
                    report.setCouse("");
                }
                
            }else{
                report.setCouse("");
            }
            
            report.setGuidetour("");
            report.setPay(book.getIsPay()== 1?"Y":"N");
            report.setRemark(job.getRemark());
            report.setGuidetour(getGuideTour(book.getDaytour().getId(), book.getTourDate()));
            if(job.getStaffByGuildeId() != null){
                report.setGuide(job.getStaffByGuildeId().getName());
            }else{
                report.setGuide("");
            }
            
//            if(book.getGuide() != null){
//                report.setGuidetour(book.getGuide().getName());
//            }else{
//                report.setGuidetour("");
//            }
            
             if(job.getStaffByDriverId() != null){
                report.setDriver(job.getStaffByDriverId().getName());
            }else{
                report.setDriver("");
            }
            
            report.setSystemdate(new SimpleDateFormat("dd MMM yy hh:mm", new Locale("us", "us")).format(new Date()));
            report.setTourdate(new SimpleDateFormat("dd MMM yy", new Locale("us", "us")).format(book.getTourDate()));
            
           
            list.add(report);
        }
        return list;
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
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
