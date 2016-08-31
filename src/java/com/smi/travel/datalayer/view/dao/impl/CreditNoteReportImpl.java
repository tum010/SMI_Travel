/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.report.model.CreditNoteReport;
import com.smi.travel.datalayer.view.dao.CreditNoteReportDao;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Surachai
 */
public class CreditNoteReportImpl implements  CreditNoteReportDao{
    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;
    
    @Override
    public List getCreditNoteReport(String creditId) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();  
        List data = new ArrayList();
        DecimalFormat df = new DecimalFormat("###,##0.00");
        BigDecimal Subtotal = new BigDecimal(0);
        BigDecimal Grandtotal = new BigDecimal(0);
        BigDecimal Difsubtotal = new BigDecimal(0);
        BigDecimal sumvat = new BigDecimal(0);
        BigDecimal Realsubtotal = new BigDecimal(0);
        BigDecimal percent = new BigDecimal(100);        
        
        //query creditnote_view
        String queryCreditNoteView = "SELECT `note`.`id` AS `id`, `note`.`cn_name` AS `customer`, `note`.`cn_address` AS `address`, `note`.`cn_no` AS `cnno`, `note`.`cn_date` AS `cndate`, ( CASE WHEN ( isnull(`noted`.`description`) OR (`noted`.`description` = '')) THEN concat( 'Refernce Tax Invoice No ', `tax`.`tax_no`, ' Date ', ifnull( date_format( `note`.`cn_date`, '%d/%m/%Y' ), '' ), ': ', ifnull(`pay`.`name`, '')) ELSE concat( 'Refernce Tax Invoice No ', `tax`.`tax_no`, ' Date ', ifnull( date_format( `note`.`cn_date`, '%d/%m/%Y' ), '' ), ': ', ifnull(`pay`.`name`, ''), ' [', `noted`.`description`, ']' ) END ) AS `description`, round(`noted`.`amount`, 2) AS `amount`, round( sum(( CASE WHEN (`taxd`.`is_vat` = '1') THEN `taxd`.`gross` ELSE 0 END )), 2 ) AS `test`, `noted`.`real_amount` AS `realamount`, round(`noted`.`vat`, 2) AS `vat`, `note`.`cn_remark` AS `remark`, `st`.`name` AS `user`, `taxd`.`vat` AS `realvat`, `taxd`.`cur_amount` AS `curamount`, `agt`.`tax_no` AS `tax_no`, ( CASE WHEN (`agt`.`branch` = 1) THEN 'สำนักงานใหญ่' WHEN (`agt`.`branch` = 2) THEN 'สาขา' ELSE '' END ) AS `branch` FROM (((((((( `credit_note` `note` JOIN `credit_note_detail` `noted` ON (( `noted`.`credit_note_id` = `note`.`id` ))) JOIN `tax_invoice` `tax` ON (( `tax`.`id` = `noted`.`tax_invoice_id` ))) JOIN `tax_invoice_detail` `taxd` ON (( `taxd`.`tax_invoice_id` = `tax`.`id` ))) LEFT JOIN `invoice_detail` `invd` ON (( `invd`.`id` = `taxd`.`invoice_detail_id` ))) LEFT JOIN `invoice` `inv` ON (( `inv`.`id` = `invd`.`invoice_id` ))) LEFT JOIN `staff` `st` ON (( `st`.`username` = `note`.`create_by` ))) LEFT JOIN `m_paytype` `pay` ON (( `pay`.`id` = `noted`.`product_type` ))) LEFT JOIN `agent` `agt` ON (( `agt`.`code` = `note`.`ap_code` ))) WHERE `note`.`id` = :cnid GROUP BY `noted`.`id`";
        queryCreditNoteView = queryCreditNoteView.replace(":cnid", creditId);
        List<Object[]> QueryCNList = session.createSQLQuery(queryCreditNoteView)
                 .addScalar("customer", Hibernate.STRING)
                 .addScalar("address", Hibernate.STRING)
                 .addScalar("cnno", Hibernate.STRING)
                 .addScalar("cndate", Hibernate.DATE)
                 .addScalar("description", Hibernate.STRING)
                 .addScalar("amount", Hibernate.BIG_DECIMAL)   
                 .addScalar("remark", Hibernate.STRING)
                 .addScalar("user", Hibernate.STRING)
                 .addScalar("vat", Hibernate.BIG_DECIMAL)
                 .addScalar("realamount", Hibernate.BIG_DECIMAL)
                 .addScalar("realvat", Hibernate.BIG_DECIMAL)
                 .addScalar("curamount", Hibernate.STRING)
                 .addScalar("tax_no", Hibernate.STRING)
                 .addScalar("branch", Hibernate.STRING)
                 .list();
        
        String curAmount = "";
        for (Object[] B : QueryCNList) {
            CreditNoteReport cn = new CreditNoteReport();
            cn.setCustomer(util.ConvertString(B[0]));
            cn.setAddress(util.ConvertString(B[1]));
            cn.setCnno(util.ConvertString(B[2]));
            if(B[3] != null){
                cn.setCndate(new SimpleDateFormat("dd-MM-yyyy", new Locale("us", "us")).format((Date)B[3]));
            }else{
                cn.setCndate("");
            }
            cn.setDescription(util.ConvertString(B[4]));
            cn.setAmount(util.setFormatMoney(B[5]));
            BigDecimal amount = (BigDecimal) B[5] == null ? new BigDecimal(0):(BigDecimal) B[5];
            Subtotal= Subtotal.add(amount);
            Grandtotal = Grandtotal.add((BigDecimal) B[9] == null ? new BigDecimal(0):(BigDecimal) B[9]);
            BigDecimal realamount = (BigDecimal) B[9] == null ? new BigDecimal(0):(BigDecimal) B[9];
            BigDecimal vat = (BigDecimal) B[8] == null ? new BigDecimal(0):(BigDecimal) B[8];
            //
            BigDecimal subtotal = realamount.multiply(percent).divide(percent.add((BigDecimal) B[10]),2,RoundingMode.HALF_UP);
            Difsubtotal = Difsubtotal.add(subtotal);
            sumvat = sumvat.add(vat);
            System.out.println("amount : "+amount);
            System.out.println("subtotal : "+subtotal);
            BigDecimal RealTotal = amount.subtract(subtotal);
            System.out.println("RealTotal : "+RealTotal);
            Realsubtotal = Realsubtotal.add(RealTotal);
            cn.setRemark(util.ConvertString(B[6]));
            cn.setUser(util.ConvertString(B[7]));
            cn.setTaxidno(B[12] != null ? util.ConvertString(B[12]) : "");
            String taxBranch = util.getTaxBranch(util.ConvertString(B[0]), util.ConvertString(B[13]));
            cn.setBranch(taxBranch);
            curAmount = util.ConvertString(B[11]);
            data.add(cn);
        }
        
        if("THB".equalsIgnoreCase(curAmount)){
            curAmount = "BAHT";
        }
        for(int i =0;i<data.size();i++){
            CreditNoteReport re = (CreditNoteReport) data.get(i);
            re.setSubtotal(util.setFormatMoney(Subtotal));
            re.setGrandtotal(util.setFormatMoney(Grandtotal));
            re.setDifsubtotal(util.setFormatMoney(Difsubtotal));
            re.setVat(util.setFormatMoney(sumvat));
            re.setReadsubtotal(util.setFormatMoney(Realsubtotal));
            String total = re.getGrandtotal().replaceAll(",", "");
            total = total.replaceAll("\\.", ",");
            String[] totals = total.split(",");
            int totalWord = 0;
            if(!"".equalsIgnoreCase(totals[0])){
                totalWord = Integer.parseInt(String.valueOf(totals[0]));
            }  
            re.setTextamount(utilityFunction.convert(totalWord)+" "+curAmount);
            data.set(i, re);
        }
        
        
                
        return data;
    }
    

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
}
