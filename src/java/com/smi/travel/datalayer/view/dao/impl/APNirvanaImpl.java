/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.view.dao.APNirvanaDao;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.datalayer.view.entity.NirvanaInterface;
import com.smi.travel.model.nirvana.SsDataexch;
import com.smi.travel.model.nirvana.SsDataexchTr;
import com.smi.travel.util.UtilityFunction;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Surachai
 */
public class APNirvanaImpl implements APNirvanaDao {

    private SessionFactory sessionFactory;
    private Transaction transaction;

    //Delimiter used in CSV file
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String[] FILE_HEADER
            = {"refinvoiceno", "intreference", "vendorid", "vendorname", "divisionid",
                "projectid", "transcode", "transdate", "duedate", "currencyid",
                "homerate", "foreignrate", "basevatamt", "basevathmamt", "vatamt",
                "vathmamt", "transamt", "transhmamt", "vatflag", "vatid",
                "whtflag", "whtid", "basewhtamt", "basewhthmamt", "whtamt",
                "whthmamt", "year", "period", "note", "puraccount1",
                "purdivision1", "purproject1", "puramt1", "purhmamt1", "puraccount2",
                "purdivision2", "purproject2", "puramt2", "purhmamt2", "puraccount3",
                "purdivision3", "purproject3", "puramt3", "purhmamt3", "puraccount4",
                "purdivision4", "purproject4", "puramt4", "purhmamt4", "puraccount5",
                "purdivision5", "purproject5", "puramt5", "purhmamt5", "puraccount6",
                "purdivision6", "purproject6", "puramt6", "purhmamt6", "puraccount7",
                "purdivision7", "purproject7", "puramt7", "purhmamt7", "puraccount8",
                "purdivision8", "purproject8", "puramt8", "purhmamt8", "puraccount9",
                "purdivision9", "purproject9", "puramt9", "purhmamt9", "puraccount10",
                "purdivision10", "purproject10", "puramt10", "purhmamt10", "service",
                "apaccount", "prefix", "voucherno", "Taxid", "vendor_branch",
                "company_branch"};

    @Override
    public String ExportAPFileInterface(List<APNirvana> APList, String pathFile) {
        String status = "";
        List<APNirvana> apDataList = this.SearchApNirvanaFromPaymentDetailId(APList);
        for (int i=0 ; i< apDataList.size() ; i++) {
            APNirvana ap = apDataList.get(i);
            
        }
        return status;
    }
    
    public List<SsDataexchTr> setApNirvanaDetail(APNirvana ap,String datano,String paymentType){
        List<SsDataexchTr> ssdtrList = new ArrayList<SsDataexchTr>();
        UtilityFunction util = new UtilityFunction();
        String dataArea = "";
        int count = 1;
        if(!"A".equalsIgnoreCase(paymentType)){
            for(int i=1; i<11; i++){
                String puraccount = "";
                String purdivision = "";
                String purproject = "";
                String puramt = "";
                String purhmamt = "";
                if(i == 1){
                    puraccount = (ap.getPuraccount1() != null && !"".equalsIgnoreCase(ap.getPuraccount1()) ? ap.getPuraccount1() : "");
                    purdivision = (ap.getPurdivision1() != null && !"".equalsIgnoreCase(ap.getPurdivision1()) ? ap.getPurdivision1() : "");
                    purproject = (ap.getPurproject1() != null && !"".equalsIgnoreCase(ap.getPurproject1()) ? ap.getPurproject1() : "");
                    puramt = (ap.getPuramt1() != null ? String.valueOf(ap.getPuramt1()) : "0.00");
                    purhmamt = (ap.getPurhmamt1() != null ? String.valueOf(ap.getPurhmamt1()) : "0.00");
                }else if(i == 2){
                    puraccount = (ap.getPuraccount2() != null && !"".equalsIgnoreCase(ap.getPuraccount2()) ? ap.getPuraccount2() : "");
                    purdivision = (ap.getPurdivision2() != null && !"".equalsIgnoreCase(ap.getPurdivision2()) ? ap.getPurdivision2() : "");
                    purproject = (ap.getPurproject2() != null && !"".equalsIgnoreCase(ap.getPurproject2()) ? ap.getPurproject2() : "");
                    puramt = (ap.getPuramt2() != null ? String.valueOf(ap.getPuramt2()) : "0.00");
                    purhmamt = (ap.getPurhmamt2() != null ? String.valueOf(ap.getPurhmamt2()) : "0.00");
                }else if(i == 3){
                    puraccount = (ap.getPuraccount3() != null && !"".equalsIgnoreCase(ap.getPuraccount3()) ? ap.getPuraccount3() : "");
                    purdivision = (ap.getPurdivision3() != null && !"".equalsIgnoreCase(ap.getPurdivision3()) ? ap.getPurdivision3() : "");
                    purproject = (ap.getPurproject3() != null && !"".equalsIgnoreCase(ap.getPurproject3()) ? ap.getPurproject3() : "");
                    puramt = (ap.getPuramt3() != null ? String.valueOf(ap.getPuramt3()) : "0.00");
                    purhmamt = (ap.getPurhmamt3() != null ? String.valueOf(ap.getPurhmamt3()) : "0.00");
                }else if(i == 4){
                    puraccount = (ap.getPuraccount4() != null && !"".equalsIgnoreCase(ap.getPuraccount4()) ? ap.getPuraccount4() : "");
                    purdivision = (ap.getPurdivision4() != null && !"".equalsIgnoreCase(ap.getPurdivision4()) ? ap.getPurdivision4() : "");
                    purproject = (ap.getPurproject4() != null && !"".equalsIgnoreCase(ap.getPurproject4()) ? ap.getPurproject4() : "");
                    puramt = (ap.getPuramt4() != null ? String.valueOf(ap.getPuramt4()) : "0.00");
                    purhmamt = (ap.getPurhmamt4() != null ? String.valueOf(ap.getPurhmamt4()) : "0.00");
                }else if(i == 5){
                    puraccount = (ap.getPuraccount5() != null && !"".equalsIgnoreCase(ap.getPuraccount5()) ? ap.getPuraccount5() : "");
                    purdivision = (ap.getPurdivision5() != null && !"".equalsIgnoreCase(ap.getPurdivision5()) ? ap.getPurdivision5() : "");
                    purproject = (ap.getPurproject5() != null && !"".equalsIgnoreCase(ap.getPurproject5()) ? ap.getPurproject5() : "");
                    puramt = (ap.getPuramt5() != null ? String.valueOf(ap.getPuramt5()) : "0.00");
                    purhmamt = (ap.getPurhmamt5() != null ? String.valueOf(ap.getPurhmamt5()) : "0.00");
                }else if(i == 6){
                    puraccount = (ap.getPuraccount6() != null && !"".equalsIgnoreCase(ap.getPuraccount6()) ? ap.getPuraccount6() : "");
                    purdivision = (ap.getPurdivision6() != null && !"".equalsIgnoreCase(ap.getPurdivision6()) ? ap.getPurdivision6() : "");
                    purproject = (ap.getPurproject6() != null && !"".equalsIgnoreCase(ap.getPurproject6()) ? ap.getPurproject6() : "");
                    puramt = (ap.getPuramt6() != null ? String.valueOf(ap.getPuramt6()) : "0.00");
                    purhmamt = (ap.getPurhmamt6() != null ? String.valueOf(ap.getPurhmamt6()) : "0.00");
                }else if(i == 7){
                    puraccount = (ap.getPuraccount7() != null && !"".equalsIgnoreCase(ap.getPuraccount7()) ? ap.getPuraccount7() : "");
                    purdivision = (ap.getPurdivision7() != null && !"".equalsIgnoreCase(ap.getPurdivision7()) ? ap.getPurdivision7() : "");
                    purproject = (ap.getPurproject7() != null && !"".equalsIgnoreCase(ap.getPurproject7()) ? ap.getPurproject7() : "");
                    puramt = (ap.getPuramt7() != null ? String.valueOf(ap.getPuramt7()) : "0.00");
                    purhmamt = (ap.getPurhmamt7() != null ? String.valueOf(ap.getPurhmamt7()) : "0.00");
                }else if(i == 8){
                    puraccount = (ap.getPuraccount8() != null && !"".equalsIgnoreCase(ap.getPuraccount8()) ? ap.getPuraccount8() : "");
                    purdivision = (ap.getPurdivision8() != null && !"".equalsIgnoreCase(ap.getPurdivision8()) ? ap.getPurdivision8() : "");
                    purproject = (ap.getPurproject8() != null && !"".equalsIgnoreCase(ap.getPurproject8()) ? ap.getPurproject8() : "");
                    puramt = (ap.getPuramt8() != null ? String.valueOf(ap.getPuramt8()) : "0.00");
                    purhmamt = (ap.getPurhmamt8() != null ? String.valueOf(ap.getPurhmamt8()) : "0.00");
                }else if(i == 9){
                    puraccount = (ap.getPuraccount9() != null && !"".equalsIgnoreCase(ap.getPuraccount9()) ? ap.getPuraccount9() : "");
                    purdivision = (ap.getPurdivision9() != null && !"".equalsIgnoreCase(ap.getPurdivision9()) ? ap.getPurdivision9() : "");
                    purproject = (ap.getPurproject9() != null && !"".equalsIgnoreCase(ap.getPurproject9()) ? ap.getPurproject9() : "");
                    puramt = (ap.getPuramt9() != null ? String.valueOf(ap.getPuramt9()) : "0.00");
                    purhmamt = (ap.getPurhmamt9() != null ? String.valueOf(ap.getPurhmamt9()) : "0.00");
                }else if(i == 10){
                    puraccount = (ap.getPuraccount10() != null && !"".equalsIgnoreCase(ap.getPuraccount10()) ? ap.getPuraccount10() : "");
                    purdivision = (ap.getPurdivision10() != null && !"".equalsIgnoreCase(ap.getPurdivision10()) ? ap.getPurdivision10() : "");
                    purproject = (ap.getPurproject10() != null && !"".equalsIgnoreCase(ap.getPurproject10()) ? ap.getPurproject10() : "");
                    puramt = (ap.getPuramt10() != null ? String.valueOf(ap.getPuramt10()) : "0.00");
                    purhmamt = (ap.getPurhmamt10() != null ? String.valueOf(ap.getPurhmamt10()) : "0.00");
                }

                if(!"".equalsIgnoreCase(puraccount)){
                    dataArea += util.generateDataAreaNirvana(puraccount,21);
                    dataArea += util.generateDataAreaNirvana(purdivision,21);
                    dataArea += util.generateDataAreaNirvana(purproject,21);
                    dataArea += util.generateDataAreaNirvana(puramt,20);
                    dataArea += util.generateDataAreaNirvana(purhmamt,20);
                    dataArea += util.generateDataAreaNirvana("",61);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss", Locale.US);
                    SsDataexchTr ssdtr = new SsDataexchTr();
                    ssdtr.setDataCd("240020");
                    ssdtr.setDataNo(datano);
                    ssdtr.setDataSeq(String.valueOf(count));
                    ssdtr.setEntSysCd("SMI");
                    ssdtr.setEntSysDate(sdf.format(new Date()));
            //        ssdtr.setEntDataNo(datano);
            //        ssdtr.setEntComment("");
            //        ssdtr.setRcvSysCd("NIRVANA");
            //        ssdtr.setRcvStaCd("1");
            //        ssdtr.setCvSysDate("00000000.000000");
                    ssdtr.setRcvComment("");
            //        ssdtr.setTraNesCd("1");
            //        ssdtr.setTraStaCd("1");
            //        ssdtr.setTraSysDate("00000000.000000");
                    ssdtr.setDataArea(dataArea);
                    ssdtrList.add(ssdtr);
                    count += 1;
                }

            }
        
        } else {
            Session session = this.sessionFactory.openSession();
            String paymentId = ap.getPayment_detail_id();
            String query = " SELECT * FROM `ap_nirvana_pay_detail` where payment_id = '"+paymentId+"'" ;
System.out.println("query ap_nirvana : "+query);
            List<Object[]> APNirvanaList = session.createSQLQuery(query)
                    .addScalar("payment_id", Hibernate.STRING)
                    .addScalar("puraccount", Hibernate.STRING)
                    .addScalar("purdivision", Hibernate.STRING)
                    .addScalar("purproject", Hibernate.STRING)
                    .addScalar("puramt", Hibernate.STRING)
                    .addScalar("purhmamt", Hibernate.STRING)
                    .addScalar("note", Hibernate.STRING)
                    .list();

            for (Object[] B : APNirvanaList) {
                String payment_id = (B[0] != null && !"".equalsIgnoreCase(String.valueOf(B[0])) ? String.valueOf(B[0]) : "");
                String puraccount = (B[1] != null && !"".equalsIgnoreCase(String.valueOf(B[1])) ? String.valueOf(B[1]) : "");
                String purdivision = (B[2] != null && !"".equalsIgnoreCase(String.valueOf(B[2])) ? String.valueOf(B[2]) : "");
                String purproject = (B[3] != null && !"".equalsIgnoreCase(String.valueOf(B[3])) ? String.valueOf(B[3]) : "");
                String puramt = (B[4] != null && !"".equalsIgnoreCase(String.valueOf(B[4])) ? String.valueOf(B[4]) : "0.00");
                String purhmamt = (B[5] != null && !"".equalsIgnoreCase(String.valueOf(B[5])) ? String.valueOf(B[5]) : "0.00");
                String note = (B[6] != null && !"".equalsIgnoreCase(String.valueOf(B[6])) ? String.valueOf(B[6]) : "");
                
                if(!"".equalsIgnoreCase(puraccount)){
                    dataArea += util.generateDataAreaNirvana(puraccount,21);
                    dataArea += util.generateDataAreaNirvana(purdivision,21);
                    dataArea += util.generateDataAreaNirvana(purproject,21);
                    dataArea += util.generateDataAreaNirvana(puramt,20);
                    dataArea += util.generateDataAreaNirvana(purhmamt,20);
                    dataArea += util.generateDataAreaNirvana(note,61);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss", Locale.US);
                    SsDataexchTr ssdtr = new SsDataexchTr();
                    ssdtr.setDataCd("240020");
                    ssdtr.setDataNo(datano);
                    ssdtr.setDataSeq(String.valueOf(count));
                    ssdtr.setEntSysCd("SMI");
                    ssdtr.setEntSysDate(sdf.format(new Date()));
            //        ssdtr.setEntDataNo(datano);
            //        ssdtr.setEntComment("");
            //        ssdtr.setRcvSysCd("NIRVANA");
            //        ssdtr.setRcvStaCd("1");
            //        ssdtr.setCvSysDate("00000000.000000");
                    ssdtr.setRcvComment("");
            //        ssdtr.setTraNesCd("1");
            //        ssdtr.setTraStaCd("1");
            //        ssdtr.setTraSysDate("00000000.000000");
                    ssdtr.setDataArea(dataArea);
                    ssdtrList.add(ssdtr);
                    count += 1;
                    System.out.println("ssdtrList.add(ssdtr); ");
                    dataArea = "";
                }

            }                      

            session.close();
            this.sessionFactory.close();
        }

        return ssdtrList;
    }
    
    @Override
    public String UpdateStatusAPInterface(List<NirvanaInterface> nirvanaInterfaceList) {
        int result = 0;
        try {
            Session session = this.getSessionFactory().openSession();
            setTransaction(session.beginTransaction());

            for (int i = 0; i < nirvanaInterfaceList.size(); i++) {
                NirvanaInterface nirvanaInterface = nirvanaInterfaceList.get(i);
                String paymentDetailId = nirvanaInterface.getPayment_detail_id();
                String paymentType = nirvanaInterface.getPaymenttype();
                String dataNo = nirvanaInterface.getDatano();
//                String dataNo = apNirvana.getDataNo();
                System.out.println("===== paymentDetailId ===== : "+paymentDetailId);
                System.out.println("===== paymentType ===== : "+paymentType);
                System.out.println("===== dataNo ===== : "+dataNo);
                Date date = new Date();
                if ("W".equalsIgnoreCase(paymentType)) {
                    String hql = "update PaymentDetailWendy pay set pay.isExport = 1 , pay.exportDate = :date , pay.dataNo = :dataNo where pay.id = :paymentDetailId";
                    try {
                        Query query = session.createQuery(hql);
                        query.setParameter("paymentDetailId", paymentDetailId);
                        query.setParameter("date", date);
                        query.setParameter("dataNo", dataNo);
                        
                        System.out.println(" query " + query);
                        
                        result = query.executeUpdate();
                        System.out.println("Rows affected: " + result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = 0;
                    }
                } else if ("A".equalsIgnoreCase(paymentType)) {
                    String hql = "update PaymentAirticket air set air.isExport = 1 , air.exportDate = :date , air.dataNo = :dataNo where air.id = :paymentDetailId";
                    try {
                        Query query = session.createQuery(hql);
                        query.setParameter("paymentDetailId", paymentDetailId);
                        query.setParameter("date", date);
                        query.setParameter("dataNo", dataNo);
                        
                        System.out.println(" query " + query);
                        
                        result = query.executeUpdate();
                        System.out.println("Rows affected: " + result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = 0;
                    }
                } else if ("O".equalsIgnoreCase(paymentType)) {
                    String hql = "update PaymentOutboundDetail pod set pod.isExport = 1 , pod.exportDate = :date , pod.dataNo = :dataNo where pod.id = :paymentDetailId";
                    try {
                        Query query = session.createQuery(hql);
                        query.setParameter("paymentDetailId", paymentDetailId);
                        query.setParameter("date", date);
                        query.setParameter("dataNo", dataNo);
                        
                        System.out.println(" query " + query);
                        
                        result = query.executeUpdate();
                        System.out.println("Rows affected: " + result);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        result = 0;
                    }
                }
            }
            getTransaction().commit();
            session.close();
            this.getSessionFactory().close();
        } catch (Exception ex) {
            getTransaction().rollback();
            ex.printStackTrace();
            result = 0;
        }
        return (result == 1 ? "success" : "fail");
    }

    @Override
    public List<APNirvana> SearchApNirvanaFromFilter(String paymentType, String productType, String status, String from, String to, String accno) {
        UtilityFunction util = new UtilityFunction();
        List<APNirvana> apNirvanaList = new ArrayList<APNirvana>();
        Session session = this.getSessionFactory().openSession();
        StringBuffer query = new StringBuffer(" SELECT * FROM `ap_nirvana` ");
        boolean haveCondition = false;
        if ((paymentType != null) && (!"".equalsIgnoreCase(paymentType))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.paymenttype = '" + paymentType + "'");
            haveCondition = true;
        }
        if ((productType != null) && (!"".equalsIgnoreCase(productType))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.producttype = '" + productType + "'");
            haveCondition = true;
        }
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if ("N".equalsIgnoreCase(status)) {
                status = "New";
            }
            if ("E".equalsIgnoreCase(status)) {
                status = "Export";
            }
            if ("C".equalsIgnoreCase(status)) {
                status = "Change";
            }
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.itf_status = '" + status + "'");
            haveCondition = true;
        }
        if ((from != null) && (!"".equalsIgnoreCase(from))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.transdate >= '" + from + "'");
            haveCondition = true;
        }
        if ((to != null) && (!"".equalsIgnoreCase(to))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.transdate <= '" + to + "'");
            haveCondition = true;
        }
        if ((accno != null) && (!"".equalsIgnoreCase(accno))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.accno = '" + accno + "'");
            haveCondition = true;
        }
        
        query.append(" Order by `ap_nirvana`.payno desc");
        System.out.println("Query ap_nirvana : "+query);
        
        List<Object[]> QueryList = session.createSQLQuery(query.toString())
                .addScalar("refinvoiceno", Hibernate.STRING)
                .addScalar("intreference", Hibernate.STRING)
                .addScalar("vendorid", Hibernate.STRING)
                .addScalar("vendorname", Hibernate.STRING)
                .addScalar("divisionid", Hibernate.STRING)
                .addScalar("projectid", Hibernate.STRING)
                .addScalar("transcode", Hibernate.STRING)
                //            .addScalar("transdate",Hibernate.DATE)
                .addScalar("duedate", Hibernate.DATE)
                .addScalar("currencyid", Hibernate.STRING)
                .addScalar("homerate", Hibernate.BIG_DECIMAL)
                .addScalar("foreignrate", Hibernate.BIG_DECIMAL)
                .addScalar("basevatamt", Hibernate.BIG_DECIMAL)
                .addScalar("basevathmamt", Hibernate.BIG_DECIMAL)
                .addScalar("vatamt", Hibernate.BIG_DECIMAL)
                .addScalar("vathmamt", Hibernate.BIG_DECIMAL)
                .addScalar("transamt", Hibernate.BIG_DECIMAL)
                .addScalar("transhmamt", Hibernate.BIG_DECIMAL)
                .addScalar("vatflag", Hibernate.STRING)
                .addScalar("vatid", Hibernate.STRING)
                .addScalar("whtflag", Hibernate.STRING)
                .addScalar("whtid", Hibernate.STRING)
                .addScalar("basewhtamt", Hibernate.BIG_DECIMAL)
                .addScalar("basewhthmamt", Hibernate.BIG_DECIMAL)
                .addScalar("whtamt", Hibernate.BIG_DECIMAL)
                .addScalar("whthmamt", Hibernate.BIG_DECIMAL)
                .addScalar("year", Hibernate.INTEGER)
                .addScalar("period", Hibernate.INTEGER)
                .addScalar("note", Hibernate.STRING)
                .addScalar("puraccount1", Hibernate.STRING)
                .addScalar("purdivision1", Hibernate.STRING)
                .addScalar("purproject1", Hibernate.STRING)
                .addScalar("puramt1", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt1", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount2", Hibernate.STRING)
                .addScalar("purdivision2", Hibernate.STRING)
                .addScalar("purproject2", Hibernate.STRING)
                .addScalar("puramt2", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt2", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount3", Hibernate.STRING)
                .addScalar("purdivision3", Hibernate.STRING)
                .addScalar("purproject3", Hibernate.STRING)
                .addScalar("puramt3", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt3", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount4", Hibernate.STRING)
                .addScalar("purdivision4", Hibernate.STRING)
                .addScalar("purproject4", Hibernate.STRING)
                .addScalar("puramt4", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt4", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount5", Hibernate.STRING)
                .addScalar("purdivision5", Hibernate.STRING)
                .addScalar("purproject5", Hibernate.STRING)
                .addScalar("puramt5", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt5", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount6", Hibernate.STRING)
                .addScalar("purdivision6", Hibernate.STRING)
                .addScalar("purproject6", Hibernate.STRING)
                .addScalar("puramt6", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt6", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount7", Hibernate.STRING)
                .addScalar("purdivision7", Hibernate.STRING)
                .addScalar("purproject7", Hibernate.STRING)
                .addScalar("puramt7", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt7", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount8", Hibernate.STRING)
                .addScalar("purdivision8", Hibernate.STRING)
                .addScalar("purproject8", Hibernate.STRING)
                .addScalar("puramt8", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt8", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount9", Hibernate.STRING)
                .addScalar("purdivision9", Hibernate.STRING)
                .addScalar("purproject9", Hibernate.STRING)
                .addScalar("puramt9", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt9", Hibernate.BIG_DECIMAL)
                .addScalar("puraccount10", Hibernate.STRING)
                .addScalar("purdivision10", Hibernate.STRING)
                .addScalar("purproject10", Hibernate.STRING)
                .addScalar("puramt10", Hibernate.BIG_DECIMAL)
                .addScalar("purhmamt10", Hibernate.BIG_DECIMAL)
                .addScalar("service", Hibernate.STRING)
                .addScalar("apaccount", Hibernate.STRING)
                .addScalar("prefix", Hibernate.STRING)
                .addScalar("voucherno", Hibernate.INTEGER)
                .addScalar("taxid", Hibernate.STRING)
                .addScalar("vendor_branch", Hibernate.INTEGER)
                .addScalar("company_branch", Hibernate.INTEGER)
                .addScalar("itf_status", Hibernate.STRING)
                .addScalar("payment_id", Hibernate.STRING)
                .addScalar("paymenttype", Hibernate.STRING)
                .addScalar("payment_detail_id", Hibernate.STRING) //88
                .addScalar("rowid", Hibernate.STRING) //89
                .addScalar("payno", Hibernate.STRING) //89
                .list();

        for (Object[] B : QueryList) {
            APNirvana apNirvana = new APNirvana();
            apNirvana.setIntreference(util.ConvertString(B[1]));
            apNirvana.setVendorid(util.ConvertString(B[2]));
            apNirvana.setVendorname(util.ConvertString(B[3]));
            apNirvana.setCurrencyid(util.ConvertString(B[8]));
            apNirvana.setBasevatamt((B[11]) != null ? new BigDecimal(util.ConvertString(B[11])) : new BigDecimal("0.00"));
            apNirvana.setVatamt((B[13]) != null ? new BigDecimal(util.ConvertString(B[13])) : new BigDecimal("0.00"));
            apNirvana.setTransamt((B[15]) != null ? new BigDecimal(util.ConvertString(B[15])) : new BigDecimal("0.00"));
            apNirvana.setPuraccount1(util.ConvertString(B[28]));
            apNirvana.setItf_status(util.ConvertString(B[85]));
            apNirvana.setPaymenttype(util.ConvertString(B[87]));
            apNirvana.setPayment_detail_id(util.ConvertString(B[88]));
            apNirvana.setRowid(util.ConvertString(B[89]));
            apNirvana.setPayno(util.ConvertString(B[90]));
            apNirvanaList.add(apNirvana);
        }

        this.sessionFactory.close();
        session.close();
        return apNirvanaList;

    }

//    private List<APNirvana> mappingAPNirvana(List<APNirvana> apNirvanaList) {
//        List<APNirvana> mappingData = new ArrayList<APNirvana>();
//        for(int i=0;i<apNirvanaList.size();i++){
//            APNirvana data = new APNirvana();
//            data = apNirvanaList.get(i);
//            APNirvana apNirvana = new APNirvana();
//            apNirvana.setIntreference(data.getIntreference());
//            apNirvana.setVendorid(data.getVatid());
//            apNirvana.setVendorname(data.getVendorname());
//            apNirvana.setPuraccount1(data.getPuraccount1());
//            apNirvana.setBasevatamt(data.getBasevatamt() != null ? data.getBasevatamt() : new BigDecimal("0.00"));
//            apNirvana.setCurrencyid(data.getCurrencyid());
//            mappingData.add(apNirvana);
//        }
//        
//        return mappingData;
//    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<APNirvana> SearchApNirvanaFromPaymentDetailId(List<APNirvana> APList) {
        Session session = this.getSessionFactory().openSession();
//        StringBuffer query = new StringBuffer("");
//        for (int i = 0; i < APList.size(); i++) {
//            APNirvana ap = APList.get(i);
//            if (i != 0) {
//                query.append(" OR ");
//            }
//            query.append("( payment_detail_id = " + ap.getPayment_detail_id() + " AND paymenttype = '" + ap.getPaymenttype() + "' )");
//        }

      //  SQLQuery sQLQuery = session.createSQLQuery(query.toString()).addEntity(APNirvana.class);
      //  List result = sQLQuery.list();
        String query = "from APNirvana ap where ap.rowid in (";
        for (int i = 0; i < APList.size(); i++) {
            query += (i == 0 ? "" : ",");
            query += ("'"+APList.get(i).getRowid()+"'");
        } 
        query += ") order by accno , intreference asc " ;
        System.out.println(" query :: " + query);
        Query HqlQuery = session.createQuery(query);
        List<APNirvana> result = HqlQuery.list();
       
        this.sessionFactory.close();
        session.close();
        return result;
    }

    @Override
    public List getApNirvanaReport(String paymentType, String productType, String status, String from, String to, String printby) {
        UtilityFunction util = new UtilityFunction();
        Session session = this.getSessionFactory().openSession();
        StringBuffer query = new StringBuffer(" SELECT `ap_nirvana`.* FROM `ap_nirvana` ");
        boolean haveCondition = false;
        if ((paymentType != null) && (!"".equalsIgnoreCase(paymentType))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.paymenttype = '" + paymentType + "'");
            haveCondition = true;
        }
        if ((productType != null) && (!"".equalsIgnoreCase(productType))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.producttype = '" + productType + "'");
            haveCondition = true;
        }
        if ((status != null) && (!"".equalsIgnoreCase(status))) {
            if ("N".equalsIgnoreCase(status)) {
                status = "New";
            }
            if ("E".equalsIgnoreCase(status)) {
                status = "Export";
            }
            if ("C".equalsIgnoreCase(status)) {
                status = "Change";
            }
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.itf_status = '" + status + "'");
            haveCondition = true;
        }
        if ((from != null) && (!"".equalsIgnoreCase(from))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.transdate >= '" + from + "'");
            haveCondition = true;
        }
        if ((to != null) && (!"".equalsIgnoreCase(to))) {
            query.append(haveCondition ? " and" : " where");
            query.append(" `ap_nirvana`.transdate <= '" + to + "'");
            haveCondition = true;
        }
        
        query.append(" Order by `ap_nirvana`.payno desc");
//        SQLQuery sQLQuery = session.createSQLQuery(query.toString()).addEntity(APNirvana.class);
//        List result = new ArrayList<APNirvana>();
//        List result = sQLQuery.list();
        List<Object[]> QueryList = session.createSQLQuery(query.toString())
                .addScalar("intreference", Hibernate.STRING)
                .addScalar("vendorid", Hibernate.STRING)
                .addScalar("vendorname", Hibernate.STRING)
                .addScalar("puraccount1", Hibernate.STRING)
                .addScalar("vatamt", Hibernate.BIG_DECIMAL)
                .addScalar("basevatamt", Hibernate.BIG_DECIMAL)
                .addScalar("currencyid", Hibernate.STRING)
                .addScalar("payno", Hibernate.STRING)
                .addScalar("refinvoiceno", Hibernate.STRING)
                .addScalar("transdate", Hibernate.DATE)
                .list();
        List result = new ArrayList<APNirvana>();
        if(QueryList.isEmpty()){
            SimpleDateFormat dateformat = new SimpleDateFormat();
            dateformat.applyPattern("dd-MM-yyyy HH:mm:ss");
            APNirvana apNirvana = new APNirvana();
            apNirvana.setUser(printby);
            apNirvana.setSystemdate(String.valueOf(dateformat.format(new Date())));
            apNirvana.setDatefrom(from);
            apNirvana.setDateto(to);
            result.add(apNirvana);
            return result;
        }
        boolean header = true;
        for (Object[] B : QueryList) {
            APNirvana apNirvana = new APNirvana();
            apNirvana.setIntreference(util.ConvertString(B[0]));
            apNirvana.setVendorid(util.ConvertString(B[1]));
            apNirvana.setVendorname(util.ConvertString(B[2]));
            apNirvana.setPuraccount1(util.ConvertString(B[3]));
            apNirvana.setCurrencyid(util.ConvertString(B[6]));
            apNirvana.setVatamt((B[4]) != null ? new BigDecimal(util.ConvertString(B[4])) : new BigDecimal("0.00"));
            apNirvana.setBasevatamt((B[5]) != null ? new BigDecimal(util.ConvertString(B[5])) : new BigDecimal("0.00"));
            apNirvana.setPayno(util.ConvertString(B[7]));
            apNirvana.setRefinvoiceno(util.ConvertString(B[8]));
            apNirvana.setTransdate(util.convertStringToDate(util.ConvertString(B[9])));
            if(header){
                SimpleDateFormat dateformat = new SimpleDateFormat();
                dateformat.applyPattern("dd-MM-yyyy HH:mm:ss");         
                apNirvana.setUser(printby);
                apNirvana.setSystemdate(String.valueOf(dateformat.format(new Date())));
                apNirvana.setDatefrom(from);
                apNirvana.setDateto(to);
                header = false;
            }
            result.add(apNirvana);
        }
        
//        APNirvana dataheader = new APNirvana();
//        dataheader = (APNirvana) result.get(0);
//        dataheader.setDatefrom(from);
//        dataheader.setDateto(to);
//        result.set(0, dataheader);
        
        this.sessionFactory.close();
        session.close();
        return result;
    }
    
    private String genReport(List<APNirvana> apDataList , String fullFileName , List<APNirvana> APList){
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd/MM/yyyy");
        String status ="";
        UtilityFunction util = new UtilityFunction();
     try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            int rownum = 0;
            for (APNirvana ap : apDataList) {
                HSSFRow dataRow = sheet.createRow(rownum++);
                int cellnum = 0;
                HSSFCell cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getRefinvoiceno());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getIntreference());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVendorid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVendorname());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getDivisionid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getProjectid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getTranscode());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getTransdate() == null ? "" : util.ConvertString(df.format(util.convertStringToDate(String.valueOf(ap.getTransdate())))));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getDuedate() == null ? "" : util.ConvertString(df.format(util.convertStringToDate(String.valueOf(ap.getDuedate())))));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getCurrencyid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getHomerate()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getForeignrate()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasevatamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasevathmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getVatamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getVathmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getTransamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getTranshmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVatflag());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVatid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getWhtflag());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getWhtid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasewhtamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getBasewhthmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getWhtamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getWhthmamt()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getYear() == null ? "":ap.getYear().toString());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPeriod() == null ? "":ap.getPeriod().toString());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getNote());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount1());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision1());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject1());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt1()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt1()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount2());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision2());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject2());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt2()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt2()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount3());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision3());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject3());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt3()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt3()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount4());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision4());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject4());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt4()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt4()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount5());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision5());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject5());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt5()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt5()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount6());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision6());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject6());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt6()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt6()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount7());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision7());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject7());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt7()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt7()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount8());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision8());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject8());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt8()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt8()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount9());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision9());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject9());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt9()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt9()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPuraccount10());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurdivision10());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPurproject10());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPuramt10()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(UtilityFunction.getObjectString(ap.getPurhmamt10()));
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getService());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getApaccount());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getPrefix());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVoucherno());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getTaxid());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getVendor_branch()== null? "0":ap.getVendor_branch().toString());
                cell = dataRow.createCell(cellnum++);
                cell.setCellValue(ap.getCompany_branch());
//                for(int j =0;j<100;j++){
//                    sheet.autoSizeColumn(j);
//                }
            }

            FileOutputStream out = new FileOutputStream(new File(fullFileName + ".xls"));
            workbook.write(out);
            out.close();
            status = "success";
        } catch (Exception e) {
            e.printStackTrace();
            for (APNirvana ap : APList) {
                if(!"".equals(status)){
                    status += ", ";
                }
                status += ap.getPayment_detail_id();
            }
        }
     return status;
    }

    @Override
    public String MappingAPNirvana(List<APNirvana> APList) {
        String result = "fail";
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        UtilityFunction util = new UtilityFunction();
        List<APNirvana> apDataList = this.SearchApNirvanaFromPaymentDetailId(APList);
        List<SsDataexch> ssDataexchList = new ArrayList<SsDataexch>();
        for(int i=0; i<apDataList.size(); i++){
            APNirvana apNirvana = apDataList.get(i);
            String apNirvanaNo = gennarateAPNirvanaNo("AP");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss", Locale.US);
            SsDataexch ssDataexchTemp = new SsDataexch();
            ssDataexchTemp.setDataCd("240020");            
            ssDataexchTemp.setDataNo(apNirvanaNo);
            ssDataexchTemp.setEntSysCd("SMI");           
            ssDataexchTemp.setEntSysDate(sdf.format(date));
//            ssDataexchTemp.setEntDataNo(apNirvanaNo);
//            ssDataexchTemp.setEntComment("");
//            ssDataexchTemp.setRcvSysCd("NIRVANA");
            ssDataexchTemp.setRcvStaCd("1");
            ssDataexchTemp.setRcvSysDate("00000000.000000");
            ssDataexchTemp.setRcvComment("");
//            ssDataexchTemp.setTraNesCd("1");
//            ssDataexchTemp.setTraStaCd("1");
//            ssDataexchTemp.setTraSysDate("00000000.000000");
            
            String dataArea = "";
            String companyId = (apNirvana.getComid()!= null && !"".equalsIgnoreCase(apNirvana.getComid()) ? apNirvana.getComid() : "");
            dataArea += util.generateDataAreaNirvana(companyId,21);

            String refInvoiceNo = (apNirvana.getRefinvoiceno()!= null && !"".equalsIgnoreCase(apNirvana.getRefinvoiceno()) ? apNirvana.getRefinvoiceno() : "");
            dataArea += util.generateDataAreaNirvana(refInvoiceNo,21);
            
            String vendorId = (apNirvana.getVendorid()!= null && !"".equalsIgnoreCase(apNirvana.getVendorid()) ? apNirvana.getVendorid() : "");
            dataArea += util.generateDataAreaNirvana(vendorId,21);
            
            String vendorName = (apNirvana.getVendorname()!= null && !"".equalsIgnoreCase(apNirvana.getVendorname()) ? apNirvana.getVendorname() : "");
            dataArea += util.generateDataAreaNirvana(vendorName,61);
            
            String remitId = (apNirvana.getVendorid()!= null && !"".equalsIgnoreCase(apNirvana.getVendorid()) ? apNirvana.getVendorid() : "");
            dataArea += util.generateDataAreaNirvana(remitId,21);
            
            String divisionId = (apNirvana.getDivisionid() != null && !"".equalsIgnoreCase(apNirvana.getDivisionid()) ? apNirvana.getDivisionid() : "");
            dataArea += util.generateDataAreaNirvana(divisionId,21);
            
            String projectId = "00";
            dataArea += util.generateDataAreaNirvana(projectId,21);
            
            String transCode = (apNirvana.getTranscode() != null && !"".equalsIgnoreCase(apNirvana.getTranscode()) ? apNirvana.getTranscode() : "");
            dataArea += util.generateDataAreaNirvana(transCode,2);
            
            String transDate = (apNirvana.getTransdate() != null && !"".equalsIgnoreCase(String.valueOf(apNirvana.getTransdate())) ? sf.format(apNirvana.getTransdate()) : "");
            dataArea += util.generateDataAreaNirvana(transDate,10);
            
            String dueDate = (apNirvana.getDuedate() != null && !"".equalsIgnoreCase(String.valueOf(apNirvana.getDuedate())) ? sf.format(apNirvana.getDuedate()) : "");
            dataArea += util.generateDataAreaNirvana(dueDate,10);
            
            String vatFlag = (apNirvana.getVatflag() != null && !"".equalsIgnoreCase(apNirvana.getVatflag()) ? apNirvana.getVatflag() : "");
            dataArea += util.generateDataAreaNirvana(vatFlag,1);
            
            String vatId = (apNirvana.getVatid() != null && !"".equalsIgnoreCase(apNirvana.getVatid()) ? apNirvana.getVatid() : "");
            dataArea += util.generateDataAreaNirvana(vatId,6);
            
            String transAmt = (apNirvana.getTransamt() != null ? String.valueOf(apNirvana.getTransamt()) : "0.00");
            dataArea += util.generateDataAreaNirvana(transAmt,20);
            
            String transHmamt = (apNirvana.getTranshmamt()!= null ? String.valueOf(apNirvana.getTranshmamt()) : "0.00");
            dataArea += util.generateDataAreaNirvana(transHmamt,20);
            
            String totBaseVatAmt = (apNirvana.getTranshmamt()!= null ? String.valueOf(apNirvana.getBasevatamt()) : "0.00");
            //String totBaseVatAmt = ("Y".equalsIgnoreCase(vatFlag) ? apNirvana.getBasevatamt() : "0.00");
            dataArea += util.generateDataAreaNirvana(totBaseVatAmt,20);
            
            String totBaseVatHmAmt = (apNirvana.getTranshmamt()!= null ? String.valueOf(apNirvana.getBasevathmamt()) : "0.00");
            //String totBaseVatHmAmt = ("Y".equalsIgnoreCase(vatFlag) ? transHmamt : "0.00");
            dataArea += util.generateDataAreaNirvana(totBaseVatHmAmt,20);
            System.out.println("base vat amt : "+totBaseVatAmt +"base vat home"+ totBaseVatHmAmt);
            String totVatAmt = (apNirvana.getVatamt() != null ? String.valueOf(apNirvana.getVatamt()) : "0.00");
            dataArea += util.generateDataAreaNirvana(totVatAmt,20);
            
            String totVatHmAmt = (apNirvana.getVathmamt() != null ? String.valueOf(apNirvana.getVathmamt()) : "0.00");
            dataArea += util.generateDataAreaNirvana(totVatHmAmt,20);
            
            String currencyId = (apNirvana.getCurrencyid() != null && !"".equalsIgnoreCase(apNirvana.getCurrencyid()) ? apNirvana.getCurrencyid() : "");
            dataArea += util.generateDataAreaNirvana(currencyId,6);
            
            String homeRate = (apNirvana.getHomerate()!= null ? String.valueOf(apNirvana.getHomerate()) : "0.000000");
            dataArea += util.generateDataAreaNirvana(homeRate,18);
            
            String foreignRate = (apNirvana.getForeignrate() != null ? String.valueOf(apNirvana.getForeignrate()) : "0.000000");
            dataArea += util.generateDataAreaNirvana(foreignRate,18);
            
            String note = (apNirvana.getNote() != null && !"".equalsIgnoreCase(apNirvana.getNote()) ? apNirvana.getNote() : "");
            dataArea += util.generateDataAreaNirvana(note,61);
            
            String year = (apNirvana.getYear() != null ? String.valueOf(apNirvana.getYear()) : "");
            dataArea += util.generateDataAreaNirvana(year,4);
            
            String period = (apNirvana.getPeriod() != null ? String.valueOf(apNirvana.getPeriod()) : "");
            dataArea += util.generateDataAreaNirvana(period,2);
            
            String service = (apNirvana.getService() != null && !"".equalsIgnoreCase(apNirvana.getService()) ? apNirvana.getService(): "");
            dataArea += util.generateDataAreaNirvana(service,1);
            
            String apAccount = (apNirvana.getApaccount()!= null && !"".equalsIgnoreCase(apNirvana.getApaccount()) ? apNirvana.getApaccount(): "");
            dataArea += util.generateDataAreaNirvana(apAccount,21);
            
            String totBaseWithHoldTaxAmt = (apNirvana.getBasewhtamt()!= null ? String.valueOf(apNirvana.getBasewhtamt()) : "0");
            dataArea += util.generateDataAreaNirvana(totBaseWithHoldTaxAmt,20);
            
            String totBaseWithHoldTaxHmAmt = (apNirvana.getBasewhthmamt()!= null ? String.valueOf(apNirvana.getBasewhthmamt()) : "0");
            dataArea += util.generateDataAreaNirvana(totBaseWithHoldTaxHmAmt,20);
            
            String totWithHoldTaxAmt = (apNirvana.getWhtamt()!= null ? String.valueOf(apNirvana.getWhtamt()) : "0");
            dataArea += util.generateDataAreaNirvana(totWithHoldTaxAmt,20);
            
            String totWithHoldTaxHmAmt = (apNirvana.getWhthmamt()!= null ? String.valueOf(apNirvana.getWhthmamt()) : "0");
            dataArea += util.generateDataAreaNirvana(totWithHoldTaxHmAmt,20);
            
            String withHoldTaxFlag = (apNirvana.getWhtflag()!= null && !"".equalsIgnoreCase(apNirvana.getWhtflag()) ? apNirvana.getWhtflag() : "");
            dataArea += util.generateDataAreaNirvana(withHoldTaxFlag,1);
            
            String intReference = (apNirvana.getIntreference()!= null && !"".equalsIgnoreCase(apNirvana.getIntreference()) ? apNirvana.getIntreference() : "");
            dataArea += util.generateDataAreaNirvana(intReference,21);
            
            String companyBranch = (apNirvana.getCompany_branch()!= null ? String.valueOf(apNirvana.getCompany_branch()) : "");
            dataArea += util.generateDataAreaNirvana(companyBranch,6);
            
            String custTaxId = (apNirvana.getTaxid()!= null && !"".equalsIgnoreCase(apNirvana.getTaxid()) ? apNirvana.getTaxid() : "");
            dataArea += util.generateDataAreaNirvana(custTaxId,21);
            
            String custBranch = (apNirvana.getVendor_branch()!= null ? String.valueOf(apNirvana.getVendor_branch()) : "0");
            dataArea += util.generateDataAreaNirvana(custBranch,6);
            
            ssDataexchTemp.setDataArea(dataArea);
                    
            String paymentDetailId = (apNirvana.getPayment_detail_id()!= null ? String.valueOf(apNirvana.getPayment_detail_id()) : "");
            ssDataexchTemp.setPayment_detail_id(paymentDetailId);
            
            String paymentType = (apNirvana.getPaymenttype()!= null ? String.valueOf(apNirvana.getPaymenttype()) : "");
            ssDataexchTemp.setPaymenttype(paymentType);
            
            List<SsDataexchTr> ssDataexchTrList = setApNirvanaDetail(apNirvana,apNirvanaNo,paymentType);
            ssDataexchTemp.setSsDataexchTrList(ssDataexchTrList);

            util.logsNirvana(ssDataexchTemp,apNirvana.getRowid());
            
            try {
                result = ssDataexchTemp.connectSybase(ssDataexchTemp);
            } catch (Exception ex) {
                Logger.getLogger(APNirvanaImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ssDataexchList.add(ssDataexchTemp);
            
            if(i == APList.size()-1){
                try {
                    List<NirvanaInterface> nirvanaInterfaceList = ssDataexchTemp.callStoredProcedureAP(ssDataexchList);
                    if(nirvanaInterfaceList != null){
                        System.out.println("===== UpdateStatusAPInterface =====");
                        result = UpdateStatusAPInterface(nirvanaInterfaceList);
                    }
               
                } catch (Exception ex) {
                    Logger.getLogger(APNirvanaImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
//                result = "success";
            }
        }
        
        return result;
    }
    
    private String gennarateAPNirvanaNo(String type){
        String hql = "from MRunningCode run where run.type =  :type";
        Session session = this.sessionFactory.openSession();
        List<MRunningCode> list = session.createQuery(hql).setParameter("type", type).list();
        if (list.isEmpty()) {
            return null;
        }
        
        String code = String.valueOf(list.get(0).getRunning()+1);
        for(int i=code.length();i<6;i++){
            code = "0"+code;
        }
        
        Query query = session.createQuery("update MRunningCode run set run.running = :running" +
    				" where run.type = :type");
        query.setParameter("running", list.get(0).getRunning()+1);
        query.setParameter("type", type);
        int result = query.executeUpdate();
        
        session.close();
        this.sessionFactory.close();
        return code;
    }
}
