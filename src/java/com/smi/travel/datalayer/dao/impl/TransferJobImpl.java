/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao.impl;

import com.smi.travel.datalayer.dao.TransferJobDao;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.entity.TourOperationDesc;
import com.smi.travel.datalayer.entity.TransferJob;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class TransferJobImpl implements TransferJobDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private static final int MAX_JOB = 100;
    private static final String FILTERTOUR_FROM_DATE_QUERY = "from DaytourBooking DB where DB.tourDate = :date and DB.master.MBookingstatus.id <> 3 and DB.master.MBookingstatus.id <> 4  and DB.MItemstatus.id = 1 GROUP BY DB.daytour.code ";
    private static final String GET_JOB_QUERY = "from TransferJob tr where tr.transferDate >= :startdate and tr.transferDate <= :enddate";
    private static final String GET_LASTDOCNO_QUERY = "from TransferJob tr where tr.documentNo Like :docno Order by tr.id desc ";
    private static final String GET_DOCNOFROMID_QUERY = "from TransferJob tr where tr.documentNo = :docno";
    @Override
    public List<DaytourBooking> filterTourFromDate(String TourDate) {
        UtilityFunction util = new UtilityFunction();
        List<Daytour> tourList = new LinkedList<Daytour>();
        List<DaytourBooking> tourbookList = new LinkedList<DaytourBooking>();
        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = session.createQuery(FILTERTOUR_FROM_DATE_QUERY)
                .setParameter("date", util.convertStringToDate(TourDate))
                .list();

        if (list.isEmpty()) {
            return null;
        } else {
            for (DaytourBooking L : list) {
//                Daytour tour = L.getDaytour();
//                tourList.add(tour);
                tourbookList.add(L);
            }
            return tourbookList;
        }
    }

    @Override
    public List<Place> filterPlaceFromDateAndTour(String TourDate, String TourID) {
        String filterPlaceQuery = "from DaytourBooking DB where DB.tourDate = '" + TourDate + "'"
                + " and DB.daytour.id in (" + TourID + ") and DB.master.MBookingstatus.id <> 3 and DB.master.MBookingstatus.id <> 4  and DB.MItemstatus.id = 1 GROUP BY DB.place.id order by DB.place.place ";
        Session session = this.sessionFactory.openSession();
        List<Place> placeList = new LinkedList<Place>();
        List<DaytourBooking> list = session.createQuery(filterPlaceQuery)
                .list();
        if (list.isEmpty()) {

            return null;
        } else {

            for (DaytourBooking P : list) {
                Place place = P.getPlace();
                placeList.add(place);
            }

            return placeList;
        }
    }

    @Override
    public List<String> filterPlaceOtherFromDateAndTour(String TourDate, String TourID) {
        String filterPlaceOtherQuery = "from DaytourBooking DB where DB.tourDate = '" + TourDate + "'"
                + " and DB.place.place = 'OTHERS' and DB.daytour.id in (" + TourID + ") and DB.master.MBookingstatus.id <> 3 and DB.master.MBookingstatus.id <> 4  and DB.MItemstatus.id = 1 "
                + " Group by DB.pickupDetail ";
        System.out.println("filterPlaceOtherQuery : " + filterPlaceOtherQuery);
        Session session = this.sessionFactory.openSession();
        List<String> otherList = new LinkedList<String>();
        List<DaytourBooking> list = session.createQuery(filterPlaceOtherQuery)
                .list();
        if (list.isEmpty()) {

            return null;
        } else {
            for (DaytourBooking P : list) {
                String other = P.getPickupDetail();
                otherList.add(other);
            }

            return otherList;
        }

    }

    @Override
    public List<TransferJob> searchTransferJob(String StartDate, String EndDate,String Hotel) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy-MM-dd");   
        List<TransferJob> listdata = new LinkedList<TransferJob>();
        String start = String.valueOf(df.format(util.convertStringToDate(StartDate)));
        String end = String.valueOf(df.format(util.convertStringToDate(EndDate)));
        String query = "from TransferJob tr where tr.transferDate >= '"+start+"' and tr.transferDate <= '"+end+"'";
        
        if(!"".equalsIgnoreCase(Hotel)&&(Hotel != null)){
            query += " and tr.place like '%"+Hotel+"%'";
        }
        System.out.println("query : "+query);
        List<TransferJob> list = session.createQuery(query).list();
        if (list.isEmpty()) {
            return null;
        }else{
            for(int i = 0;i<list.size();i++){
                TransferJob tj = list.get(i);
                tj.setPlaceOther(util.generateSpecialCharacter(list.get(i).getPlaceOther()));
                listdata.add(tj);
            }
        }
        //this.sessionFactory.close();
        //session.close();
        return listdata;
    }

    @Override
    public String saveTransferjob(TransferJob Job) {
        String result = "";
        int issave =0;
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            Job.setTransferTime(new Date());
            if((Job.getDocumentNo() == null)||("".equalsIgnoreCase(Job.getDocumentNo()))){
                Job.setDocumentNo(generateDocumentNo());
                issave = 1;
                session.save(Job);
            }else{
                System.out.println("update transfer job");
                Job.setId(getDocNoFromID(Job.getDocumentNo()));
                session.update(Job);
            }
            transaction.commit();
            session.close();
            this.sessionFactory.close();
            result = "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            if(issave == 1){
                Job.setDocumentNo("");
            }
            result = "save unsuccessful.";
            if(Job.getTransferDate() == null){
                result = "save unsuccessful. Please fill in transfer date";
            }
            
            
        }
        return result;
    }
    
    public String getDocNoFromID(String Docno){
        String ID ="";
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery(GET_DOCNOFROMID_QUERY);
        query.setParameter("docno", Docno);
        query.setMaxResults(1);
        List<TransferJob> list = query.list();
        if(list.isEmpty()){
            
        }else{
            System.out.println("ID : "+ID);
           ID = list.get(0).getId();
        }
        
        this.sessionFactory.close();
        session.close();
        return ID;
    }

    public String generateDocumentNo() {
        String Docno = "";
        Session session = this.sessionFactory.openSession();
        List<TransferJob> list = new LinkedList<TransferJob>();
        Date thisdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyyMM");
        Query query = session.createQuery(GET_LASTDOCNO_QUERY);
        query.setParameter("docno", df.format(thisdate) + "%");
        query.setMaxResults(1);
        list = query.list();
        if (list.isEmpty()) {
            Docno = df.format(thisdate) + "-" + "001";
        } else {
            Docno = list.get(0).getDocumentNo();
            if (!Docno.equalsIgnoreCase("")) {
                int running = Integer.parseInt(Docno.split("-")[1]) + 1;
                String temp = String.valueOf(running);
                for (int i = temp.length(); i < 3; i++) {
                    temp = "0" + temp;
                }
                Docno = df.format(thisdate) + "-" + temp;
            }
        }
        this.sessionFactory.close();
        session.close();
        return Docno;
    }

    @Override
    public List<DaytourBooking> getTransferjobData(String TourId, String TourDate, String Place, String Other) {
        String getJobDetailQuery = "from DaytourBooking DB where DB.tourDate = '" + TourDate + "'"
                + " and DB.daytour.code in ('" + TourId.replaceAll(" ", "").replaceAll("\\|\\|", "','").trim() + "') and DB.MItemstatus.id = 1 and DB.master.MBookingstatus.id <> 3  and DB.master.MBookingstatus.id <> 4  ";
        String open = "";
        String close = "";
//        List Transferjoblist = new ArrayList();
        List<DaytourBooking> dblist = new LinkedList<DaytourBooking>();
        boolean checkplace = false;
        if (Place != null) {
            if (Other != null) {
                open = "(";
                close = ")";
            }
            String PlaceTemp = "";
            String placetemp[] = Place.replaceAll("OTHERS", "").split("\\|\\|");
            for(int i = 0 ; i<placetemp.length;i++){
                PlaceTemp += ",'"+placetemp[i]+"'";
            }
            if(!"".equalsIgnoreCase(PlaceTemp.substring(1))){
                getJobDetailQuery += " and " + open + " DB.place.place in (" + PlaceTemp.substring(1).replaceAll(" '", "'").replaceAll("' ", "'").replaceAll(",''", "") + ")";
                checkplace = true;
            }
        }
        if (Other != null && !"".equalsIgnoreCase(Other)) {
            if(checkplace){
                getJobDetailQuery += " or DB.pickupDetail in ('" + Other.replaceAll("\\|\\|", "','").replaceAll(" '", "'").replaceAll("' ", "'") + "') " + close;
            }else{
                getJobDetailQuery += " and DB.pickupDetail in ('" + Other.replaceAll("\\|\\|", "','").replaceAll(" '", "'").replaceAll("' ", "'") + "') " ;
            }
        }else{
            getJobDetailQuery += close;
        }
        getJobDetailQuery += "  ORDER BY DB.pickupTime , DB.place.place , DB.pickupDetail ";
        
        System.out.println("getJobDetailQuery : " + getJobDetailQuery);

        Session session = this.sessionFactory.openSession();
        List<DaytourBooking> list = session.createQuery(getJobDetailQuery)
                .list();
        if (list.isEmpty()) {
            return null;
        }
        for(int i = 0 ; i < list.size() ; i ++){
            DaytourBooking db = list.get(i);
            db.setGuideTour(getGuideTour(db.getDaytour().getId(), db.getTourDate()));
            dblist.add(db);
        }
        return dblist;
    }
        
    private String getGuideTour(String tourid , Date tourdate) {
        String GuideTour = "";
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
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<DaytourBooking> sortTransferjobDataFromTime(List<DaytourBooking> daytourList) {
        List<DaytourBooking> sortDaytour = new ArrayList<DaytourBooking>();
       
        if(daytourList == null){
            return daytourList;
        }else if(daytourList.size() == 0){
            return daytourList;
        }
        
        
        List Dataindex = new ArrayList();
        for (int i = 0; i < daytourList.size(); i++) {
//            System.out.println("data id : " + daytourList.get(i).getId());
            if (daytourList.get(i).getId() == null) {
//                System.out.println("data id : null ");
                return daytourList;
            }
        }
        for (int i = 0; i < daytourList.size(); i++) {
            Dataindex.add(daytourList.get(i).getPickupTime());
        }

        Collections.sort(Dataindex);
        for (int i = 0; i < Dataindex.size(); i++) {
            for (int j = 0; j < daytourList.size(); j++) {
                if (Dataindex.get(i).equals(daytourList.get(j).getId())) {
//                    System.out.println("order no : " + daytourList.get(j).getId());
                    sortDaytour.add(daytourList.get(j));
                }
            }
        }

        return sortDaytour;
    }

}
