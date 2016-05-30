/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.MRunningCode;
import com.smi.travel.datalayer.view.dao.CollectionNirvanaDao;
import com.smi.travel.datalayer.view.entity.CollectionNirvana;
import com.smi.travel.datalayer.view.entity.CollectionNirvanaCashReceipt;
import com.smi.travel.datalayer.view.entity.CollectionNirvanaExpenseReceipt;
import com.smi.travel.model.nirvana.SsDataexch;
import com.smi.travel.model.nirvana.SsDataexchTr;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Jittima
 */
public class CollectionNirvanaImpl implements CollectionNirvanaDao{
    private SessionFactory sessionFactory;
    private Transaction transaction;
    private UtilityFunction utilityFunction;
    @Override
    public List<CollectionNirvana> getCollectionNirvanaFromFilter(String department, String type, String status, String dateFrom, String dateTo, String invno,String printby) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        
        List<CollectionNirvana> collectionNirvanaList = new ArrayList<CollectionNirvana>();
        
        String query = "SELECT * FROM `collection_nirvana` where";
        int checkQuery = 0;
        String prefix ="";
        
        if(((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))) &&((dateTo != null) &&(!"".equalsIgnoreCase(dateTo)))){
            query += " invdate >= '" +dateFrom +"' and invdate <= '"+dateTo +"' ";
            checkQuery = 1;
        }else if((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))){
            checkQuery = 1;
            query +=  " invdate >= '" +dateFrom +"'";

        }else if((dateTo != null) &&(!"".equalsIgnoreCase(dateTo))){
            checkQuery = 1;
            query += " invdate <= '" +dateTo +"'";
        }

        if((department != null) &&(!"".equalsIgnoreCase(department))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+" department = '"+department+"'";
        }else{
            department = "ALL";
        }

        if((type != null) &&(!"".equalsIgnoreCase(type))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " type = '"+type+"'";
        }
        
        if((status != null) &&(!"".equalsIgnoreCase(status))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " collection_status = '"+status+"'";
        }
        
        if((invno != null) &&(!"".equalsIgnoreCase(invno))){
            if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
            query += prefix+ " invno = '"+invno+"'";
        }
         query += " ORDER BY invno desc  ";
         
        if(checkQuery == 0){query = query.replaceAll("where", "");}
        System.out.println("query : "+query);
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                                .addScalar("invno",Hibernate.STRING)
                                .addScalar("invto",Hibernate.STRING)
                                .addScalar("arcode",Hibernate.STRING)
                                .addScalar("acccode",Hibernate.STRING)
                                .addScalar("suminvamount",Hibernate.BIG_DECIMAL)
                                .addScalar("recno",Hibernate.STRING)
                                .addScalar("sumrecamount",Hibernate.BIG_DECIMAL)
                                .addScalar("cur",Hibernate.STRING)
                                .addScalar("diff",Hibernate.BIG_DECIMAL)
                                .addScalar("collection_status",Hibernate.STRING)
                                .addScalar("department",Hibernate.STRING)
                                .addScalar("type",Hibernate.STRING)
                                .addScalar("invdate",Hibernate.DATE)
                                .addScalar("invamount",Hibernate.STRING)
                                .addScalar("wth",Hibernate.BIG_DECIMAL)
                                .addScalar("cash",Hibernate.BIG_DECIMAL)
                                .addScalar("chq",Hibernate.BIG_DECIMAL)
                                .addScalar("credit",Hibernate.BIG_DECIMAL)
                                .addScalar("banktransfer",Hibernate.BIG_DECIMAL)
                                .addScalar("wt",Hibernate.BIG_DECIMAL)
                                .addScalar("cashminus",Hibernate.BIG_DECIMAL)
                                .addScalar("pay_by",Hibernate.STRING)
                                .addScalar("itf_status",Hibernate.STRING)
                                .addScalar("rowid",Hibernate.STRING)

                                .addScalar("trancode",Hibernate.STRING)
                                .addScalar("prefix",Hibernate.STRING)
                                .addScalar("docno",Hibernate.STRING)
                                .addScalar("refvoucher",Hibernate.STRING)
                                .addScalar("year",Hibernate.STRING)
                                .addScalar("period",Hibernate.STRING)
                                .addScalar("arcur",Hibernate.STRING)
                                .addScalar("recur",Hibernate.STRING)
                                .addScalar("homerate",Hibernate.STRING)
                                .addScalar("foreignrate",Hibernate.STRING)
                                .addScalar("bankamt",Hibernate.BIG_DECIMAL)
                                .addScalar("note",Hibernate.STRING)
                                .addScalar("is_void",Hibernate.STRING)
                                .addScalar("chequeno",Hibernate.STRING)
                                .addScalar("chequedate",Hibernate.DATE)
                                .addScalar("comid",Hibernate.STRING)
                                .addScalar("invname",Hibernate.STRING)
                                .addScalar("transdate",Hibernate.DATE)
                                .addScalar("bankcode",Hibernate.STRING)
                                .list();

        List data = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy hh:mm");
        int count = 1;
        BigDecimal cash = new BigDecimal(BigInteger.ZERO);
        BigDecimal chq  = new BigDecimal(BigInteger.ZERO);
        BigDecimal credit = new BigDecimal(BigInteger.ZERO);
        BigDecimal banktransfer = new BigDecimal(BigInteger.ZERO);
        BigDecimal wt = new BigDecimal(BigInteger.ZERO);
        BigDecimal cashminus = new BigDecimal(BigInteger.ZERO);
        
        BigDecimal totalamount = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountwait  = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountvoid = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountinvoice = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountdiff = new BigDecimal(BigInteger.ZERO);
        
        for(Object[] CN : QueryList){
            CollectionNirvana collectionNirvana = new CollectionNirvana();
            collectionNirvana.setInvno(util.ConvertString(CN[0]));
            collectionNirvana.setInvto(util.ConvertString(CN[1]));
            collectionNirvana.setArcode(util.ConvertString(CN[2]));
            collectionNirvana.setAcccode(util.ConvertString(CN[3]));
            collectionNirvana.setInvamount((BigDecimal) CN[4]);
            
            String receipt = util.ConvertString(CN[5]);
            String[] parts = receipt.split(",");
            String recno = parts[0];
            for(int i = 1; i < parts.length; i++) {
                if(!(String.valueOf(parts[i])).equalsIgnoreCase(String.valueOf(parts[i-1])) ) {
                    recno += "," +parts[i] ;
                }
            }
            collectionNirvana.setRecno(recno);
            collectionNirvana.setRecamount((BigDecimal) CN[6]);
            collectionNirvana.setCur(util.ConvertString(CN[7]));
            collectionNirvana.setDiff((BigDecimal) CN[8]);
            collectionNirvana.setCollectionStatus(util.ConvertString(CN[9]));
            collectionNirvana.setDepartment(util.ConvertString(CN[10]));
            collectionNirvana.setType(util.ConvertString(CN[11]));
            collectionNirvana.setInvdate(util.convertStringToDate(util.ConvertString(CN[12])));
            collectionNirvana.setInvoiceamount(util.ConvertString(CN[13]));
            collectionNirvana.setWithtax((BigDecimal) CN[14]);
            
            cash = cash.add((BigDecimal) CN[15]);
            collectionNirvana.setCash(cash);
            
            chq = chq.add((BigDecimal) CN[16]);
            collectionNirvana.setChq(chq);
            
            credit = credit.add((BigDecimal) CN[17]);
            collectionNirvana.setCredit(credit);
            
            banktransfer = banktransfer.add((BigDecimal) CN[18]);
            collectionNirvana.setBanktransfer(banktransfer);
            
            wt = wt.add((BigDecimal) CN[19]);
            collectionNirvana.setWt(wt);
            
            cashminus = cashminus.add((BigDecimal) CN[20]);
            collectionNirvana.setCashminus(cashminus);
            
            String paybuy = util.ConvertString(CN[21]);
            collectionNirvana.setPayby(util.ConvertString(CN[21]));
            if(!"Wait".equalsIgnoreCase(paybuy) && !"Void".equalsIgnoreCase(paybuy)){
                totalamount = totalamount.add((BigDecimal) CN[6]);
            }
            if("Wait".equalsIgnoreCase(paybuy)){
                totalamountwait = totalamountwait.add((BigDecimal) CN[6]);
            }
            if("Void".equalsIgnoreCase(paybuy)){
                totalamountvoid = totalamountvoid.add((BigDecimal) CN[6]);
            }
            totalamountinvoice = totalamountinvoice.add((BigDecimal) CN[4]);
            totalamountdiff = totalamountdiff.add((BigDecimal) CN[8]);
            collectionNirvana.setTotalamount(totalamount);
            collectionNirvana.setTotalamountwait(totalamountwait);
            collectionNirvana.setTotalamountvoid(totalamountvoid);
            collectionNirvana.setTotalamountinvoice(totalamountinvoice);
            collectionNirvana.setTotalamountdiff(totalamountdiff);
            
            collectionNirvana.setSystemdate(String.valueOf(dateformat.format(new Date())));
            collectionNirvana.setUser(printby);
            if((dateFrom != null) && (!"".equalsIgnoreCase(dateFrom))){
                collectionNirvana.setFrom(String.valueOf(df.format(util.convertStringToDate(dateFrom))));
            }else{
                collectionNirvana.setFrom("");
            }
            if((dateTo != null) && (!"".equalsIgnoreCase(dateTo))){
                collectionNirvana.setTo(String.valueOf(df.format(util.convertStringToDate(dateTo))));
            }else{
                collectionNirvana.setTo("");
            }
            collectionNirvana.setHeaderdepartment(department);
            
            collectionNirvana.setStatus(util.ConvertString(CN[22]));
            collectionNirvana.setRowid(util.ConvertString(CN[23]));
            
            collectionNirvana.setTrancode(util.ConvertString(CN[24]));
            collectionNirvana.setPrefix(util.ConvertString(CN[25]));
            collectionNirvana.setDocno(util.ConvertString(CN[26]));
            collectionNirvana.setRefvoucher(util.ConvertString(CN[27]));
            collectionNirvana.setYear(util.ConvertString(CN[28]));
            collectionNirvana.setPeriod(util.ConvertString(CN[29]));
            collectionNirvana.setArcur(util.ConvertString(CN[30]));
            collectionNirvana.setRecur(util.ConvertString(CN[31]));
            collectionNirvana.setHomerate(util.ConvertString(CN[32]));
            collectionNirvana.setForeignrate(util.ConvertString(CN[33]));
            collectionNirvana.setBankamt((BigDecimal) CN[34]);
            collectionNirvana.setNote(util.ConvertString(CN[35]));
            collectionNirvana.setIs_void(util.ConvertString(CN[36]));
            collectionNirvana.setChequeno(util.ConvertString(CN[37]));
            collectionNirvana.setChequedate(util.convertStringToDate(util.ConvertString(CN[38])));
            collectionNirvana.setComid(util.ConvertString(CN[39]));
            collectionNirvana.setInvname(util.ConvertString(CN[40]));
            collectionNirvana.setTransdate(util.convertStringToDate(util.ConvertString(CN[41])));
            collectionNirvana.setBankid(util.ConvertString(CN[42]));
            collectionNirvanaList.add(collectionNirvana);
        }
        
        session.close();
        this.sessionFactory.close();
        return collectionNirvanaList;
    }
    
    
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

    public UtilityFunction getUtilityFunction() {
        return utilityFunction;
    }

    public void setUtilityFunction(UtilityFunction utilityFunction) {
        this.utilityFunction = utilityFunction;
    }

    @Override
    public String UpdateStatusCollection(List<CollectionNirvana> cnList) {
        UtilityFunction utilty =  new UtilityFunction();
        String isUpdate ="";
        try {
            Session session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            for (int i = 0; i < cnList.size(); i++) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String strDate = sdf.format(cal.getTime());
                Date date = new Date();
                
                String hql = "";
                String id = "";
                if(cnList.get(i).getRowid() != null && !"".equalsIgnoreCase(cnList.get(i).getRowid())){
                    id = cnList.get(i).getRowid();
                    hql = "update Receipt rec set rec.isExport = 1 , rec.exportDate = :date where rec.id = :recid";
                }
                Query query = session.createQuery(hql);
                query.setParameter("recid", String.valueOf(id));
                query.setParameter("date", date);
                int result = query.executeUpdate();
                System.out.println("Query Update : " + result + ":" + query);
            }

            transaction.commit();
            session.close();
            this.sessionFactory.close();
            isUpdate = "updatesuccess";
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
            isUpdate = "updatefail";
        }
        return isUpdate;
    }

    @Override
    public String MappingCollectionNirvana(List<CollectionNirvana> cnData) {
        String result = "fail";
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss", Locale.US);
        UtilityFunction util = new UtilityFunction();
        List<CollectionNirvana> cnDataList = this.SearchCollectionNirvanaFromRowId(cnData);
        List<SsDataexch> ssDataexchList = new ArrayList<SsDataexch>();
        for(int i=0; i<cnDataList.size(); i++){
            CollectionNirvana co = cnDataList.get(i);
            String colNirvanaNo = gennarateColNirvanaNo("CL");
            Date date = new Date();
            //Data ss_dataexch2 For Header , Receipt Voucher
            SsDataexch ssDataexchTemp = new SsDataexch();
            ssDataexchTemp.setDataCd("240030");            
            ssDataexchTemp.setDataNo(colNirvanaNo);
            ssDataexchTemp.setEntSysCd("SMI");           
            ssDataexchTemp.setEntSysDate(sdf.format(date));
            ssDataexchTemp.setRcvStaCd("1");
            ssDataexchTemp.setRcvSysDate("00000000.000000");
            ssDataexchTemp.setRcvComment("");
            //DataArea Header
            ssDataexchTemp.setDataArea(setDataArea(co));
            
            //Ss_dataextrchtr2
            List<SsDataexchTr> ssDataexchTrList = setCollectionNirvanaCashReceipt(co.getRowid() , colNirvanaNo); 
            ssDataexchTemp.setSsDataexchTrList(ssDataexchTrList);
            
            //Ss_dataextrchtr3
            
        }
        return "";
    }
    private String setDataArea(CollectionNirvana col){
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss", Locale.US);
        UtilityFunction util = new UtilityFunction();
        String dataArea = "";
        
        String companyId = (col.getComid()!= null && !"".equalsIgnoreCase(col.getComid()) ? col.getComid() : "");
        dataArea += util.generateDataAreaNirvana(companyId,21);
        
        String tranCode = (col.getTrancode() != null && !"".equalsIgnoreCase(col.getTrancode()) ? col.getTrancode() : "");
        dataArea += util.generateDataAreaNirvana(tranCode,2);
        
        String prefix = (col.getPrefix()!= null && !"".equalsIgnoreCase(col.getPrefix()) ? col.getPrefix() : "");
        dataArea += util.generateDataAreaNirvana(prefix,6);

        String docno = (col.getDocno()!= null && !"".equalsIgnoreCase(col.getDocno()) ? col.getDocno() : "");
        dataArea += util.generateDataAreaNirvana(docno,9);

        String refvoucher = (col.getRefvoucher()!= null && !"".equalsIgnoreCase(col.getRefvoucher()) ? col.getRefvoucher() : "");
        dataArea += util.generateDataAreaNirvana(refvoucher,21);

        String invto = (col.getInvto()!= null && !"".equalsIgnoreCase(col.getInvto()) ? col.getInvto() : "");
        dataArea += util.generateDataAreaNirvana(invto,21);

        String name = (col.getInvname() != null && !"".equalsIgnoreCase(col.getInvname()) ? col.getInvname() : "");
        dataArea += util.generateDataAreaNirvana(name,101);
        
        String year = (col.getYear() != null ? String.valueOf(col.getYear()) : "");
        dataArea += util.generateDataAreaNirvana(year,4);

        String period = (col.getPeriod() != null ? String.valueOf(col.getPeriod()) : "");
        dataArea += util.generateDataAreaNirvana(period,2);
        
        String bankid = (col.getBankid() != null ? String.valueOf(col.getBankid()) : "");
        dataArea += util.generateDataAreaNirvana(bankid,6);
        
        String transdate = (col.getTransdate()!= null && !"".equalsIgnoreCase(String.valueOf(col.getTransdate())) ? sf.format(col.getTransdate()) : "");
        dataArea += util.generateDataAreaNirvana(transdate,10);
        
        String arcur = (col.getArcur() != null && !"".equalsIgnoreCase(col.getArcur()) ? col.getArcur() : "");
        dataArea += util.generateDataAreaNirvana(arcur,6);
        
        String recur = (col.getRecur()!= null && !"".equalsIgnoreCase(col.getRecur()) ? col.getRecur() : "");
        dataArea += util.generateDataAreaNirvana(recur,6);
        
        String homeRate = (col.getHomerate()!= null ? String.valueOf(col.getHomerate()) : "0.000000");
        dataArea += util.generateDataAreaNirvana(homeRate,20);

        String foreignRate = (col.getForeignrate() != null ? String.valueOf(col.getForeignrate()) : "0.000000");
        dataArea += util.generateDataAreaNirvana(foreignRate,20);

        String bankamt = (col.getBankamt() != null ? String.valueOf(col.getBankamt()) : "0.00");
        dataArea += util.generateDataAreaNirvana(bankamt,30);
        
        String note = (col.getNote() != null && !"".equalsIgnoreCase(col.getNote()) ? col.getNote() : "");
        dataArea += util.generateDataAreaNirvana(note,150);
        
        String isVoid = (col.getIs_void()!= null && !"".equalsIgnoreCase(col.getIs_void()) ? col.getIs_void() : "");
        dataArea += util.generateDataAreaNirvana(isVoid,1);
        
        String intReceiptNo = (col.getDocno()!= null && !"".equalsIgnoreCase(col.getDocno()) ? col.getDocno() : "");
        dataArea += util.generateDataAreaNirvana(intReceiptNo,21);
        
        String chequeno = (col.getChequeno()!= null && !"".equalsIgnoreCase(col.getDocno()) ? col.getDocno() : "");
        dataArea += util.generateDataAreaNirvana(chequeno,11);

        String chequedate = (col.getChequedate()!= null && !"".equalsIgnoreCase(String.valueOf(col.getChequedate())) ? sf.format(col.getChequedate()) : "");
        dataArea += util.generateDataAreaNirvana(chequedate,10);
       
        String customerbankid = "";
        dataArea += util.generateDataAreaNirvana(customerbankid,6);
        
        String customerbankbranch = "";
        dataArea += util.generateDataAreaNirvana(customerbankbranch,61);
        
        String cust_taxid = "";
        dataArea += util.generateDataAreaNirvana(cust_taxid,21);
        
        String cust_branch = "";
        dataArea += util.generateDataAreaNirvana(cust_branch,6);
        
        String company_branch = "";
        dataArea += util.generateDataAreaNirvana(company_branch,6);
        
        return dataArea;
    }
    private String gennarateColNirvanaNo(String type){
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
    
    private List<CollectionNirvana> SearchCollectionNirvanaFromRowId(List<CollectionNirvana> cnList) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<CollectionNirvana> collectionNirvanaList = new ArrayList<CollectionNirvana>();
        
        
        String rowId = "";
        for(int i = 0;i<cnList.size();i++){
            rowId += ",'"+cnList.get(i).getRowid()+"'";
        }
        String query = " SELECT * FROM `collection_nirvana` where rowid in ("+rowId.substring(1)+")" ;
        List<Object[]> QueryList = session.createSQLQuery(query)
                .addScalar("invno",Hibernate.STRING)
                .addScalar("invto",Hibernate.STRING)
                .addScalar("arcode",Hibernate.STRING)
                .addScalar("acccode",Hibernate.STRING)
                .addScalar("suminvamount",Hibernate.BIG_DECIMAL)
                .addScalar("recno",Hibernate.STRING)
                .addScalar("sumrecamount",Hibernate.BIG_DECIMAL)
                .addScalar("cur",Hibernate.STRING)
                .addScalar("diff",Hibernate.BIG_DECIMAL)
                .addScalar("collection_status",Hibernate.STRING)
                .addScalar("department",Hibernate.STRING)
                .addScalar("type",Hibernate.STRING)
                .addScalar("invdate",Hibernate.DATE)
                .addScalar("invamount",Hibernate.STRING)
                .addScalar("wth",Hibernate.BIG_DECIMAL)
                .addScalar("cash",Hibernate.BIG_DECIMAL)
                .addScalar("chq",Hibernate.BIG_DECIMAL)
                .addScalar("credit",Hibernate.BIG_DECIMAL)
                .addScalar("banktransfer",Hibernate.BIG_DECIMAL)
                .addScalar("wt",Hibernate.BIG_DECIMAL)
                .addScalar("cashminus",Hibernate.BIG_DECIMAL)
                .addScalar("pay_by",Hibernate.STRING)
                .addScalar("itf_status",Hibernate.STRING)
                .addScalar("rowid",Hibernate.STRING)
                .addScalar("trancode",Hibernate.STRING)
                .addScalar("prefix",Hibernate.STRING)
                .addScalar("docno",Hibernate.STRING)
                .addScalar("refvoucher",Hibernate.STRING)
                .addScalar("year",Hibernate.STRING)
                .addScalar("period",Hibernate.STRING)
                .addScalar("arcur",Hibernate.STRING)
                .addScalar("recur",Hibernate.STRING)
                .addScalar("homerate",Hibernate.STRING)
                .addScalar("foreignrate",Hibernate.STRING)
                .addScalar("bankamt",Hibernate.BIG_DECIMAL)
                .addScalar("note",Hibernate.STRING)
                .addScalar("is_void",Hibernate.STRING)
                .addScalar("chequeno",Hibernate.STRING)
                .addScalar("chequedate",Hibernate.DATE)
                .addScalar("comid",Hibernate.STRING)
                .addScalar("invname",Hibernate.STRING)
                .addScalar("transdate",Hibernate.DATE)
                .addScalar("bankcode",Hibernate.STRING)
                .list();
        
         List data = new ArrayList();
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");
        SimpleDateFormat dateformat = new SimpleDateFormat();
        dateformat.applyPattern("dd-MM-yyyy hh:mm");
        int count = 1;
        BigDecimal cash = new BigDecimal(BigInteger.ZERO);
        BigDecimal chq  = new BigDecimal(BigInteger.ZERO);
        BigDecimal credit = new BigDecimal(BigInteger.ZERO);
        BigDecimal banktransfer = new BigDecimal(BigInteger.ZERO);
        BigDecimal wt = new BigDecimal(BigInteger.ZERO);
        BigDecimal cashminus = new BigDecimal(BigInteger.ZERO);
        
        BigDecimal totalamount = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountwait  = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountvoid = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountinvoice = new BigDecimal(BigInteger.ZERO);
        BigDecimal totalamountdiff = new BigDecimal(BigInteger.ZERO);
        
        for(Object[] CN : QueryList){
            CollectionNirvana collectionNirvana = new CollectionNirvana();
            collectionNirvana.setInvno(util.ConvertString(CN[0]));
            collectionNirvana.setInvto(util.ConvertString(CN[1]));
            collectionNirvana.setArcode(util.ConvertString(CN[2]));
            collectionNirvana.setAcccode(util.ConvertString(CN[3]));
            collectionNirvana.setInvamount((BigDecimal) CN[4]);
            
            String receipt = util.ConvertString(CN[5]);
            String[] parts = receipt.split(",");
            String recno = parts[0];
            for(int i = 1; i < parts.length; i++) {
                if(!(String.valueOf(parts[i])).equalsIgnoreCase(String.valueOf(parts[i-1])) ) {
                    recno += "," +parts[i] ;
                }
            }
            collectionNirvana.setRecno(recno);
            collectionNirvana.setRecamount((BigDecimal) CN[6]);
            collectionNirvana.setCur(util.ConvertString(CN[7]));
            collectionNirvana.setDiff((BigDecimal) CN[8]);
            collectionNirvana.setCollectionStatus(util.ConvertString(CN[9]));
            collectionNirvana.setDepartment(util.ConvertString(CN[10]));
            collectionNirvana.setType(util.ConvertString(CN[11]));
            collectionNirvana.setInvdate(util.convertStringToDate(util.ConvertString(CN[12])));
            collectionNirvana.setInvoiceamount(util.ConvertString(CN[13]));
            collectionNirvana.setWithtax((BigDecimal) CN[14]);
            
            cash = cash.add((BigDecimal) CN[15]);
            collectionNirvana.setCash(cash);
            
            chq = chq.add((BigDecimal) CN[16]);
            collectionNirvana.setChq(chq);
            
            credit = credit.add((BigDecimal) CN[17]);
            collectionNirvana.setCredit(credit);
            
            banktransfer = banktransfer.add((BigDecimal) CN[18]);
            collectionNirvana.setBanktransfer(banktransfer);
            
            wt = wt.add((BigDecimal) CN[19]);
            collectionNirvana.setWt(wt);
            
            cashminus = cashminus.add((BigDecimal) CN[20]);
            collectionNirvana.setCashminus(cashminus);
            
            String paybuy = util.ConvertString(CN[21]);
            collectionNirvana.setPayby(util.ConvertString(CN[21]));
            if(!"Wait".equalsIgnoreCase(paybuy) && !"Void".equalsIgnoreCase(paybuy)){
                totalamount = totalamount.add((BigDecimal) CN[6]);
            }
            if("Wait".equalsIgnoreCase(paybuy)){
                totalamountwait = totalamountwait.add((BigDecimal) CN[6]);
            }
            if("Void".equalsIgnoreCase(paybuy)){
                totalamountvoid = totalamountvoid.add((BigDecimal) CN[6]);
            }
            totalamountinvoice = totalamountinvoice.add((BigDecimal) CN[4]);
            totalamountdiff = totalamountdiff.add((BigDecimal) CN[8]);
            collectionNirvana.setTotalamount(totalamount);
            collectionNirvana.setTotalamountwait(totalamountwait);
            collectionNirvana.setTotalamountvoid(totalamountvoid);
            collectionNirvana.setTotalamountinvoice(totalamountinvoice);
            collectionNirvana.setTotalamountdiff(totalamountdiff);
            collectionNirvana.setStatus(util.ConvertString(CN[22]));
            collectionNirvana.setRowid(util.ConvertString(CN[23]));
            collectionNirvana.setTrancode(util.ConvertString(CN[24]));
            collectionNirvana.setPrefix(util.ConvertString(CN[25]));
            collectionNirvana.setDocno(util.ConvertString(CN[26]));
            collectionNirvana.setRefvoucher(util.ConvertString(CN[27]));
            collectionNirvana.setYear(util.ConvertString(CN[28]));
            collectionNirvana.setPeriod(util.ConvertString(CN[29]));
            collectionNirvana.setArcur(util.ConvertString(CN[30]));
            collectionNirvana.setRecur(util.ConvertString(CN[31]));
            collectionNirvana.setHomerate(util.ConvertString(CN[32]));
            collectionNirvana.setForeignrate(util.ConvertString(CN[33]));
            collectionNirvana.setBankamt((BigDecimal) CN[34]);
            collectionNirvana.setNote(util.ConvertString(CN[35]));
            collectionNirvana.setIs_void(util.ConvertString(CN[36]));
            collectionNirvana.setChequeno(util.ConvertString(CN[37]));
            collectionNirvana.setChequedate(util.convertStringToDate(util.ConvertString(CN[38])));
            collectionNirvana.setComid(util.ConvertString(CN[39]));
            collectionNirvana.setInvname(util.ConvertString(CN[40]));
            collectionNirvana.setTransdate(util.convertStringToDate(util.ConvertString(CN[41])));
            collectionNirvana.setBankid(util.ConvertString(CN[42]));
            collectionNirvanaList.add(collectionNirvana);
        }
        
        session.close();
        this.sessionFactory.close();
        return collectionNirvanaList;
    }
    
    
    private List<SsDataexchTr> setCollectionNirvanaCashReceipt(String rowid , String datano) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<CollectionNirvanaCashReceipt> cncrList = new ArrayList<CollectionNirvanaCashReceipt>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss", Locale.US);
        String query = " SELECT * FROM `collection_nirvana_cash_receipt` where receiptid = '"+rowid+"'" ;
        List<Object[]> QueryList = session.createSQLQuery(query)
                .addScalar("receiptid",Hibernate.STRING)
                .addScalar("transcode",Hibernate.STRING)
                .addScalar("prefix",Hibernate.STRING)
                .addScalar("documentno",Hibernate.STRING)
                .addScalar("discount_amt",Hibernate.BIG_DECIMAL)
                .addScalar("allowance_amt",Hibernate.BIG_DECIMAL)
                .addScalar("wht_amt",Hibernate.BIG_DECIMAL)
                .addScalar("vatamount",Hibernate.BIG_DECIMAL)
                .addScalar("ClearArAmt",Hibernate.BIG_DECIMAL)
                .addScalar("note",Hibernate.STRING)
                .addScalar("basewhtamt",Hibernate.BIG_DECIMAL)
                .addScalar("intrecno",Hibernate.STRING)
                .addScalar("basevatamt",Hibernate.BIG_DECIMAL)
                .list();
        for(Object[] CN : QueryList){
            CollectionNirvanaCashReceipt cncr = new CollectionNirvanaCashReceipt();
            cncr.setReceiptid(util.ConvertString(CN[0]));
            cncr.setTranscode(util.ConvertString(CN[1]));
            cncr.setPrefix(util.ConvertString(CN[2]));
            cncr.setDocumentno(util.ConvertString(CN[3]));
            cncr.setDiscountamt((BigDecimal) CN[4]);
            cncr.setAllowanceamt((BigDecimal) CN[5]);
            cncr.setWhtamt((BigDecimal) CN[6]);
            cncr.setVatamount((BigDecimal) CN[7]);
            cncr.setClearArAmt((BigDecimal) CN[8]);
            cncr.setNote(util.ConvertString(CN[9]));
            cncr.setBasewhtamt((BigDecimal) CN[10]);
            cncr.setIntrecno(util.ConvertString(CN[11]));
            cncr.setBasevatamt((BigDecimal) CN[12]);
            cncrList.add(cncr);
        }
        
        List<SsDataexchTr> ssDataexchTrList = new ArrayList<SsDataexchTr>();
        
        for(int i = 0 ; i < cncrList.size() ; i ++){
            SsDataexchTr ssDataexchTr = new SsDataexchTr();
            ssDataexchTr.setDataCd("240030");            
            ssDataexchTr.setDataNo(datano);
            if(!"".equalsIgnoreCase(datano)){
                ssDataexchTr.setDataSeq(String.valueOf(Integer.parseInt(datano)-1));
            }
            ssDataexchTr.setEntSysCd("SMI");           
            ssDataexchTr.setEntSysDate(sdf.format(new Date()));
            ssDataexchTr.setRcvComment("");
            ssDataexchTr.setDataArea(setDataAreaSsDataexChTr2(cncrList.get(i)));
            ssDataexchTrList.add(ssDataexchTr);
        }        
        session.close();
        this.sessionFactory.close();
        return ssDataexchTrList;
    }
    
    
    private String setDataAreaSsDataexChTr2(CollectionNirvanaCashReceipt cncr){
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss", Locale.US);
        UtilityFunction util = new UtilityFunction();
        String dataArea = "";
        
        String referenceTransCode = (cncr.getTranscode()!= null && !"".equalsIgnoreCase(cncr.getTranscode()) ? cncr.getTranscode() : "");
        String referencePrefix = (cncr.getPrefix()!= null && !"".equalsIgnoreCase(cncr.getPrefix()) ? cncr.getPrefix() : "");
        String referenceNo = (cncr.getDocumentno()!= null && !"".equalsIgnoreCase(cncr.getDocumentno()) ? cncr.getDocumentno() : "");
        String discountAmt = (cncr.getDiscountamt() != null ? String.valueOf(cncr.getDiscountamt()) : "0.00");
        String allowanceAmt = (cncr.getAllowanceamt() != null ? String.valueOf(cncr.getAllowanceamt()) : "0.00");
        String withholdTaxAmt = (cncr.getWhtamt()!= null ? String.valueOf(cncr.getWhtamt()) : "0.00");
        String vatAmt = (cncr.getVatamount()!= null ? String.valueOf(cncr.getVatamount()) : "0.00");
        String clearArAmt = (cncr.getClearArAmt() != null ? String.valueOf(cncr.getClearArAmt()) : "0.00");
        String note = (cncr.getNote()!= null && !"".equalsIgnoreCase(cncr.getNote()) ? cncr.getNote(): "");
        String vatFlag = (cncr.getVatflag()!= null && !"".equalsIgnoreCase(cncr.getVatflag()) ? cncr.getVatflag() : "");
        String vatid = (cncr.getVatid()!= null && !"".equalsIgnoreCase(cncr.getVatid()) ? cncr.getVatid(): "");
        String basewithholdtaxamt = (cncr.getBasewhtamt()!= null ? String.valueOf(cncr.getBasewhtamt()) : "0.00");
        String intReceiptNo = (cncr.getIntrecno() != null && !"".equalsIgnoreCase(cncr.getIntrecno()) ? cncr.getIntrecno() : "");
        String basevatamt = (cncr.getBasevatamt()!= null ? String.valueOf(cncr.getBasevatamt()) : "0.00");

        dataArea += util.generateDataAreaNirvana(referenceTransCode,2);
        dataArea += util.generateDataAreaNirvana(referencePrefix,6); 
        dataArea += util.generateDataAreaNirvana(referenceNo,9); 
        dataArea += util.generateDataAreaNirvana(discountAmt,30); 
        dataArea += util.generateDataAreaNirvana(allowanceAmt,30);  
        dataArea += util.generateDataAreaNirvana(withholdTaxAmt,30);  
        dataArea += util.generateDataAreaNirvana(vatAmt,30);  
        dataArea += util.generateDataAreaNirvana(clearArAmt,30);  
        dataArea += util.generateDataAreaNirvana(note,150); 
        dataArea += util.generateDataAreaNirvana(vatFlag,1);  
        dataArea += util.generateDataAreaNirvana(vatid,6);  
        dataArea += util.generateDataAreaNirvana(basewithholdtaxamt,30); 
        dataArea += util.generateDataAreaNirvana(intReceiptNo,21);
        dataArea += util.generateDataAreaNirvana(basevatamt,30); 
        System.out.println(" referenceTransCode   :::  " + referenceTransCode);
        System.out.println(" referencePrefix   :::  " + referencePrefix); 
        System.out.println(" referenceNo   :::  " + referenceNo); 
        System.out.println(" discountAmt   :::  " + discountAmt); 
        System.out.println(" allowanceAmt   :::  " + allowanceAmt );  
        System.out.println(" withholdTaxAmt   :::  " + withholdTaxAmt);  
        System.out.println(" vatAmt   :::  " + vatAmt );  
        System.out.println(" clearArAmt   :::  " + clearArAmt );  
        System.out.println(" note   :::  " + note); 
        System.out.println(" vatFlag   :::  " + vatFlag);  
        System.out.println(" vatid   :::  " +vatid );  
        System.out.println(" basewithholdtaxamt   :::  " + basewithholdtaxamt); 
        System.out.println(" intReceiptNo   :::  " + intReceiptNo);
        System.out.println(" basevatamt   :::  " + basevatamt); 
        
        return dataArea;
    }
    
    private List<SsDataexchTr> setCollectionNirvanaExpenseReceipt(String rowid , String datano) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List<CollectionNirvanaExpenseReceipt> cnerList = new ArrayList<CollectionNirvanaExpenseReceipt>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss", Locale.US);
        String query = " select * from collection_nirvana_expense_receipt where receiptid = '"+rowid+"'" ;
        List<Object[]> QueryList = session.createSQLQuery(query)
                .addScalar("receiptid",Hibernate.STRING)
                .addScalar("transcode",Hibernate.STRING)
                .addScalar("prefix",Hibernate.STRING)
                .addScalar("discount_amt",Hibernate.BIG_DECIMAL)
                .addScalar("note",Hibernate.STRING)
                .list();
        for(Object[] CN : QueryList){
            CollectionNirvanaExpenseReceipt cner = new CollectionNirvanaExpenseReceipt();
            cner.setProjectid(util.ConvertString(CN[0]));
            cner.setDivisionid(util.ConvertString(CN[1]));
            cner.setGlaccountid(util.ConvertString(CN[2]));
            cner.setAmount((BigDecimal) CN[3]);
            cner.setNote(util.ConvertString(CN[4]));
            cnerList.add(cner);
        }
        
        List<SsDataexchTr> ssDataexchTrList = new ArrayList<SsDataexchTr>();
        
        for(int i = 0 ; i < cnerList.size() ; i ++){
            SsDataexchTr ssDataexchTr = new SsDataexchTr();
            ssDataexchTr.setDataCd("240030");            
            ssDataexchTr.setDataNo(datano);
            if(!"".equalsIgnoreCase(datano)){
                ssDataexchTr.setDataSeq(String.valueOf(Integer.parseInt(datano)-1));
            }
            ssDataexchTr.setEntSysCd("SMI");           
            ssDataexchTr.setEntSysDate(sdf.format(new Date()));
            ssDataexchTr.setRcvComment("");
            ssDataexchTr.setDataArea(setDataAreaSsDataexChTr3(cnerList.get(i)));
            ssDataexchTrList.add(ssDataexchTr);
        }        
        session.close();
        this.sessionFactory.close();
        return ssDataexchTrList;
    }
    
    private String setDataAreaSsDataexChTr3(CollectionNirvanaExpenseReceipt cner){
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd.HHmmss", Locale.US);
        UtilityFunction util = new UtilityFunction();
        String dataArea = "";

        String projectID = (cner.getProjectid()!= null && !"".equalsIgnoreCase(cner.getProjectid()) ? cner.getProjectid() : "");
        String divisionID = (cner.getDivisionid()!= null && !"".equalsIgnoreCase(cner.getDivisionid()) ? cner.getDivisionid() : "");
        String glAccountID = (cner.getGlaccountid()!= null && !"".equalsIgnoreCase(cner.getGlaccountid()) ? cner.getGlaccountid() : "");
        String amount = (cner.getAmount()!= null ? String.valueOf(cner.getAmount()) : "0.00");
        String note = (cner.getNote()!= null && !"".equalsIgnoreCase(cner.getNote()) ? cner.getNote(): "");
      
        dataArea += util.generateDataAreaNirvana(projectID,21);
        dataArea += util.generateDataAreaNirvana(divisionID,21); 
        dataArea += util.generateDataAreaNirvana(glAccountID,21); 
        dataArea += util.generateDataAreaNirvana(amount,30); 
        dataArea += util.generateDataAreaNirvana(note,150); 

        System.out.println(" projectID   :::  " + projectID);
        System.out.println(" divisionID   :::  " + divisionID); 
        System.out.println(" glAccountID   :::  " + glAccountID); 
        System.out.println(" amount   :::  " + amount); 
        System.out.println(" note   :::  " + note); 

        return dataArea;
    }

}
