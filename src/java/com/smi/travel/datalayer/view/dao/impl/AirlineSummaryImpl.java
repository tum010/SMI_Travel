/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.report.model.AirlineSummary;
import com.smi.travel.datalayer.report.model.StaffSummary;
import com.smi.travel.datalayer.view.dao.AirlineSummaryDao;
import com.smi.travel.datalayer.view.entity.SummaryAirline;
import com.smi.travel.datalayer.view.entity.SummaryAirlinePaxView;
import com.smi.travel.datalayer.view.entity.TicketSummaryAirlineView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
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
public class AirlineSummaryImpl implements AirlineSummaryDao {

    private SessionFactory sessionFactory;
    private UtilityFunction utilityFunction;

    private static final String AirLineSumQuery = "select at.air AS air "
            + ",count(at.ref_no) AS passenger "
            + ",sum(at.net_fare) AS net "
            + ",sum(at.sale_fare) AS selling "
            + ",sum(at.tax) AS tax "
            + ",(sum(at.sale_fare) - sum(at.net_fare)) AS profit  "
            + "from airticket_ticket at  ";

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List getAirlineSummary(String ticketfrom, String tickettype, String startdate, String enddate, String username) {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        Date thisDate = new Date();
        List data = new ArrayList();
        String Query = AirLineSumQuery;
        Query += CreateAirlineSummaryQuery(ticketfrom, tickettype, startdate, enddate);
        System.out.println("Query : " + Query);

        List<Object[]> QueryStaffList = session.createSQLQuery(Query)
                .addScalar("air", Hibernate.STRING)
                .addScalar("passenger", Hibernate.INTEGER)
                .addScalar("net", Hibernate.INTEGER)
                .addScalar("selling", Hibernate.INTEGER)
                .addScalar("tax", Hibernate.INTEGER)
                .addScalar("profit", Hibernate.INTEGER)
                .list();
 
        
        for (Object[] B : QueryStaffList) {
            AirlineSummary sum = new AirlineSummary();
            System.out.println("sum data :");
            sum.setAir(util.ConvertString(B[0]));
            sum.setTicketnum(B[1]== null ? 0:(Integer)B[1]);
            sum.setFare(B[2]== null ? 0:(Integer)B[2]);
            sum.setSale(B[3]== null ? 0:(Integer)B[3]);
            sum.setTax(B[4]== null ? 0:(Integer)B[4]);
            sum.setProfit(B[5]== null ? 0:(Integer)B[5]);
            sum.setSystemdate(new SimpleDateFormat("dd MMM yyyy hh:mm:ss", new Locale("us", "us")).format(thisDate));
            sum.setUsername(username);
            
            sum.setStartdate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(startdate)));
            sum.setEnddate(new SimpleDateFormat("dd MMM yyyy", new Locale("us", "us")).format(util.convertStringToDate(enddate)));
            sum.setFrom(ticketfrom);
            sum.setType(tickettype);

            data.add(sum);
        }
        
        session.close();
        this.sessionFactory.close();
        return data;
    }

    
    public String CreateAirlineSummaryQuery(String ticketfrom, String tickettype, String startdate, String enddate) {
        String Query = "Where ";
         int check =0;
        if((ticketfrom != null) && (!"".equalsIgnoreCase(ticketfrom))) {
            check =1;
            Query += "  `at`.ticket_from = '"+ticketfrom+"' ";
        }
        if((tickettype != null) && (!"".equalsIgnoreCase(tickettype))) {
            if(check == 1){Query += " and ";}
            Query += "  `at`.ticket_type = '"+tickettype+"' ";
            check =1;
        }
        
        if (((startdate != null) && (!"".equalsIgnoreCase(startdate))) && ((enddate != null) && (!"".equalsIgnoreCase(enddate)))) {
            if(check == 1){Query += " and ";}
            Query += " at.create_date between '"+startdate+"' and '"+enddate+"' ";
            check =1;
        }
        if(check == 0){
           Query = Query.replaceAll("Where", " ");
        }
        Query += " group by at.air;";
        return Query;
    }

    @Override
    public List listSummaryAirline() {
        Session session = this.sessionFactory.openSession();
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<SummaryAirline>();
        String query = "SELECT * FROM `ticket_summary_rounting_detail`";
        
        List<Object[]> QueryList =  session.createSQLQuery(query)
                .addScalar("routing",Hibernate.STRING)
                .addScalar("pax",Hibernate.INTEGER)
                .addScalar("netsales",Hibernate.BIG_DECIMAL)
                .addScalar("tax",Hibernate.BIG_DECIMAL)
                .addScalar("ins",Hibernate.BIG_DECIMAL)
                .addScalar("comms",Hibernate.BIG_DECIMAL)
                .addScalar("amountwendy",Hibernate.BIG_DECIMAL)
                .addScalar("amountinbound",Hibernate.BIG_DECIMAL)
                .addScalar("diff",Hibernate.BIG_DECIMAL)             
                .list();
        for (Object[] B : QueryList) {
            SummaryAirline summaryAirline = new SummaryAirline();
            summaryAirline.setRouting(util.ConvertString(B[0]));
            
            summaryAirline.setInvoicedatefrom(new Date());
            summaryAirline.setInvoicedateto(new Date());
            summaryAirline.setPrinton("");
            summaryAirline.setPrintby("");
            summaryAirline.setRoutingdetail("");
            summaryAirline.setTyperouting("");
            summaryAirline.setPassenger("");
            summaryAirline.setAir("");
            summaryAirline.setSalestaff("");
            summaryAirline.setAgentname("");
            summaryAirline.setDepartment("");
            summaryAirline.setTermpay("");
            
            if(B[1] != null && !"".equals(B[1])){
                summaryAirline.setPax((Integer) B[1]);
            }else{
                summaryAirline.setPax(0);
            }
            if(B[2] != null && !"".equals(B[2])){
                summaryAirline.setNetsale((BigDecimal) B[2]);
            }else{
                summaryAirline.setNetsale(new BigDecimal(0.0));
            }
            if(B[3] != null && !"".equals(B[3])){
                summaryAirline.setTax((BigDecimal) B[3]);
            }else{
               summaryAirline.setTax(new BigDecimal(0.0));
            }
            if(B[4] != null && !"".equals(B[4])){
                summaryAirline.setIns((BigDecimal) B[4]);
            }else{
                summaryAirline.setIns(new BigDecimal(0.0));
            }
            if(B[5] != null && !"".equals(B[5])){
                summaryAirline.setComms((BigDecimal) B[5]);
            }else{
                summaryAirline.setComms(new BigDecimal(0.0));
            }
            if(B[6] != null && !"".equals(B[6])){
                summaryAirline.setAmountwendy((BigDecimal) B[6]);
            }else{
                summaryAirline.setAmountwendy(new BigDecimal(0.0));
            }
            if(B[7] != null && !"".equals(B[7])){
                summaryAirline.setAmountinbound((BigDecimal) B[7]);
            }else{
                summaryAirline.setAmountinbound(new BigDecimal(0.0));
            }
            if(B[8] != null && !"".equals(B[8])){
                summaryAirline.setDiff((BigDecimal) B[8]);
            }else{
                summaryAirline.setDiff(new BigDecimal(0.0));
            }
            data.add(summaryAirline);
        }
        return data;
    }

    @Override
    public List getSumAirlinePax(String typeRouting, String routingDetail, String dateFrom, String dateTo, String invdateForm, String invdateTo, String airlineCode, String passenger, String agentId, String department, String saleBy, String termPay, String printby) {
        UtilityFunction util = new UtilityFunction();
        List data = new ArrayList<SummaryAirlinePaxView>();
        String query = "";
        String departmenttemp = "";
        String termPaytemp = "";
        String typeRoutingtemp = "";
        String routingDetailtemp = "";
        String agentIdtemp = "";
        String saleBytemp = "";
        String airtemp = "";
        for(int i = 0 ; i < 2 ; i++){
            Session session = this.sessionFactory.openSession();
            if(i == 0){ // inv
                query = "select (case when (`inv`.`inv_type` = 'T') then substr(`inv`.`inv_no`,1,5) else substr(`inv`.`inv_no`,1,6) end) AS `invno`,sum((case when (`fare`.`department` = 'wendy') then `fare`.`inv_amount` else NULL end)) AS `amountwendy`,sum((case when (`fare`.`department` = 'inbound') then `fare`.`inv_amount` else NULL end)) AS `amountinbound`,sum((case when (`fare`.`department` = 'outbound') then `fare`.`inv_amount` else NULL end)) AS `amountoutbound` from ((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) TEMPS ";
            }else if(i == 1){ //pax
                query = "select (case when (`fare`.`ticket_type` = 'B') then 'BSP' when (`fare`.`ticket_type` = 'A') then 'AGENT' when (`fare`.`ticket_type` = 'D') then 'DOMESTIC' when (`fare`.`ticket_type` = 'TI') then 'TG INTER' when (`fare`.`ticket_type` = 'TD') then 'TG DOMESTIC' else `fare`.`ticket_type` end) AS `typepayment`,(case when (`fare`.`ticket_rounting` = 'I') then 'INTER' when (`fare`.`ticket_rounting` = 'D') then 'DOMESTIC' when (`fare`.`ticket_rounting` = 'C') then 'CANCELLED' else `fare`.`ticket_rounting` end) AS `typerounting`,`fare`.`routing_detail` AS `rounting`,count(`fare`.`ticket_no`) AS `pax`,substr(`fare`.`ticket_no`,1,3) AS `air`,sum(ifnull(`fare`.`ticket_fare`,0)) AS `netsales`,sum(ifnull(`fare`.`ticket_tax`,0)) AS `tax`,sum(ifnull(`fare`.`ticket_ins`,0)) AS `ins`,sum(ifnull(`fare`.`ticket_commission`,0)) AS `comms`,sum(ifnull(`GET_COST_TICKETFARE`(`fare`.`id`),0)) AS `cost`,sum((ifnull(`fare`.`inv_amount`,0) - ifnull(`GET_COST_TICKETFARE`(`fare`.`id`),0))) AS `pfloss`,sum(ifnull(`fare`.`diff_vat`,0)) AS `diff`,sum(ifnull(`fare`.`inv_amount`,0)) AS `invamount`,(case when (`inv`.`inv_type` = 'T') then substr(`inv`.`inv_no`,1,5) else substr(`inv`.`inv_no`,1,6) end) AS `invno` from ((((`ticket_fare_airline` `fare` left join `ticket_fare_invoice` `finv` on((`finv`.`ticket_fare_id` = `fare`.`id`))) left join `invoice` `inv` on((`inv`.`id` = `finv`.`invoice_id`))) left join `m_accterm` `term` on((`term`.`id` = `inv`.`term_pay`))) left join `staff` `st` on((`st`.`name` = `fare`.`owner`))) TEMPS ";
            }
            int checkQuery = 0;
            String prefix ="";
            
            if(((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))) &&((dateTo != null) &&(!"".equalsIgnoreCase(dateTo)))){
                query += " `fare`.`issue_date` >= '" +dateFrom +"' and `fare`.`issue_date` <= '"+dateTo +"' ";
                checkQuery = 1;
            }else if((dateFrom != null) &&(!"".equalsIgnoreCase(dateFrom))){
                checkQuery = 1;
                query +=  " `fare`.`issue_date` >= '" +dateFrom +"'";
            }else if((dateTo != null) &&(!"".equalsIgnoreCase(dateTo))){
                checkQuery = 1;
                query += " `fare`.`issue_date` <= '" +dateTo +"'";
            }
            
            if(((invdateForm != null) &&(!"".equalsIgnoreCase(invdateForm))) &&((invdateTo != null) &&(!"".equalsIgnoreCase(invdateTo)))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+" `inv`.`inv_date` >= '" +invdateForm +"' and `inv`.`inv_date`  <= '"+invdateTo +"' ";
            }else if((invdateForm != null) &&(!"".equalsIgnoreCase(invdateForm))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query +=  prefix+" `inv`.`inv_date` >= '" +invdateForm +"'";
            }else if((invdateTo != null) &&(!"".equalsIgnoreCase(invdateTo))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+" `inv`.`inv_date` <= '" +invdateTo +"'";
            }
            
            if((typeRouting != null) &&(!"".equalsIgnoreCase(typeRouting))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+" fare.ticket_rounting = '"+typeRouting+"'";
                typeRoutingtemp  = typeRouting;
            }else{
                typeRoutingtemp = "ALL";
            }

            if((routingDetail != null) &&(!"".equalsIgnoreCase(routingDetail))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " `fare`.`routing_detail` = '"+routingDetail+"'";
                routingDetailtemp = routingDetail;
            }else{
                routingDetailtemp = "ALL";
            }
            
            if((airlineCode != null) &&(!"".equalsIgnoreCase(airlineCode))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " substr(`fare`.`ticket_no`, 1, 3) = '"+airlineCode+"'";
                airtemp = airlineCode;
            }else{
                airtemp = "ALL";
            }
            
            if((passenger != null) &&(!"".equalsIgnoreCase(passenger))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " `fare`.`passenger` = '"+passenger+"'";
            }

            if((agentId != null) &&(!"".equalsIgnoreCase(agentId))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " fare.agent_id = '"+agentId+"'";
                agentIdtemp = agentId;
            }else{
                agentIdtemp = "ALL";
            }

            if((department != null) &&(!"".equalsIgnoreCase(department))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+" fare.department = '"+department+"'";
                departmenttemp = department;
            }else{
                departmenttemp = "ALL";
            }

            if((saleBy != null) &&(!"".equalsIgnoreCase(saleBy))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " `st`.`username` = '"+saleBy+"'";
                saleBytemp = saleBy;
            }else{
                saleBytemp = "ALL";
            }

            if((termPay != null) &&(!"".equalsIgnoreCase(termPay))){
                if(checkQuery == 1){prefix = " and "; }else{checkQuery = 1;}
                query += prefix+ " `term`.`name` = '"+termPay+"'";
                termPaytemp = termPay;
            }else{
                termPaytemp = "ALL";
            }

            if(checkQuery == 0){
                query = query.replaceAll("TEMPS", "");
            }else{
                query = query.replaceAll("TEMPS", "WHERE");
            }
            
            List<Object[]> QueryList = new ArrayList<Object[]>();
            if(i == 0){ //inv
                System.out.println(" INVVVVVVVVVVVVVVVVVVVVV ");
                query += "group by (case when (`inv`.`inv_type` = 'T') then substr(`inv`.`inv_no`,1,5) else substr(`inv`.`inv_no`,1,6) end)";
                QueryList = session.createSQLQuery(query)
                            .addScalar("invno",Hibernate.STRING)
                            .addScalar("amountwendy",Hibernate.STRING)
                            .addScalar("amountinbound",Hibernate.STRING)
                            .addScalar("amountoutbound",Hibernate.STRING)
                            .list();        
            }else if(i == 1){ //detail
                System.out.println(" Detailllllll ");
                query += "group by `fare`.`ticket_type`,`fare`.`ticket_rounting`,substr(`fare`.`ticket_no`,1,3)";
                QueryList = session.createSQLQuery(query)
                            .addScalar("typepayment",Hibernate.STRING)
                            .addScalar("typerounting",Hibernate.STRING)
                            .addScalar("rounting",Hibernate.STRING)
                            .addScalar("pax",Hibernate.STRING)
                            .addScalar("air",Hibernate.STRING)
                            .addScalar("netsales",Hibernate.STRING)
                            .addScalar("tax",Hibernate.STRING)
                            .addScalar("ins",Hibernate.STRING)
                            .addScalar("comms",Hibernate.STRING)
                            .addScalar("cost",Hibernate.STRING)
                            .addScalar("pfloss",Hibernate.STRING)
                            .addScalar("diff",Hibernate.STRING)
                            .addScalar("invamount",Hibernate.STRING)
                            .addScalar("invno",Hibernate.STRING)
                            .list();
            }
//            System.out.println("=========================================================");
            System.out.println("query : "+query);
//            System.out.println("=========================================================");
            SimpleDateFormat df = new SimpleDateFormat();
            df.applyPattern("dd-MM-yyyy hh:mm");
            SimpleDateFormat dateformat = new SimpleDateFormat();
            dateformat.applyPattern("dd-MM-yyyy");

            if("wendy".equalsIgnoreCase(department)){
                departmenttemp = "WENDY";
            }else if("inbound".equalsIgnoreCase(department)){
                departmenttemp = "INBOUND";
            }else if("outbound".equalsIgnoreCase(department)){
                departmenttemp = "OUTBOUND";
            }

            if("1".equalsIgnoreCase(termPay)){
                termPaytemp = "cash on demand";
            }else if("2".equalsIgnoreCase(termPay)){
                termPaytemp = "credit 7 days";
            }else if("3".equalsIgnoreCase(termPay)){
                termPaytemp = "credit 14 days";
            }else if("4".equalsIgnoreCase(termPay)){
                termPaytemp = "credit card";
            }else if("5".equalsIgnoreCase(termPay)){
                termPaytemp = "credit 30 days";
            }else if("6".equalsIgnoreCase(termPay)){
                termPaytemp = "post date cheque";
            }else if("7".equalsIgnoreCase(termPay)){
                termPaytemp = "credit 15 days";
            }

            if("I".equalsIgnoreCase(typeRouting)){
                typeRoutingtemp = "INTER";
            }else if("D".equalsIgnoreCase(typeRouting)){
                typeRoutingtemp = "DOMESTIC";
            }else if("C".equalsIgnoreCase(typeRouting)){
                typeRoutingtemp = "CANCEL";
            }
                        
            Agent agent = new Agent();
            String queryAgent = "from Agent a where a.id= :agentid";
            List<Agent> agentList = session.createQuery(queryAgent).setParameter("agentid", agentIdtemp).list();
            session.close();
            if (!agentList.isEmpty()) {
                agent =  agentList.get(0);
            }
            
            for (Object[] B : QueryList) {
                SummaryAirlinePaxView ticket = new SummaryAirlinePaxView();
                //set header
                ticket.setHeaderinvdatefrom("".equals(String.valueOf(invdateForm)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(invdateForm))));
                ticket.setHeaderinvdateto("".equals(String.valueOf(invdateTo)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(invdateTo))));
                ticket.setHeaderissuedatefrom("".equals(String.valueOf(dateFrom)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(dateFrom))));
                ticket.setHeaderissuedateto("".equals(String.valueOf(dateTo)) ? "" : util.ConvertString(dateformat.format(util.convertStringToDate(dateTo))));
                ticket.setHeaderprinton(String.valueOf(df.format(new Date())));
                ticket.setHeaderprintby(printby);
                ticket.setHeadertyperouting(typeRoutingtemp);
                ticket.setHeaderroutingdetail(routingDetailtemp);
                ticket.setHeaderair(airtemp);
                ticket.setHeaderpassenger(passenger);
                ticket.setHeaderagentname(agent.getName());
//                ticket.setHeaderdepartment(departmenttemp);
//                ticket.setHeadersalestaff(saleBytemp);
//                ticket.setHeadertermpay(termPaytemp);

                if(i == 0){ // inv
                    ticket.setInvnoPax(util.ConvertString(B[0]));
                    ticket.setAmountwendy(util.ConvertString(B[1]));
                    ticket.setAmountinbound(util.ConvertString(B[2]));
                    ticket.setAmountoutbound(util.ConvertString(B[3]));
                    ticket.setPage("inv");
                }else if(i == 1){ //detail
                    ticket.setTypepayment(util.ConvertString(B[0]));
                    ticket.setTyperounting(util.ConvertString(B[1]));
                    ticket.setRounting(util.ConvertString(B[2]));
                    ticket.setPax(util.ConvertString(B[3]));
                    ticket.setAir(util.ConvertString(B[4]));
                    ticket.setNetsales(util.ConvertString(B[5]));
                    ticket.setTax(util.ConvertString(B[6]));
                    ticket.setIns(util.ConvertString(B[7]));
                    ticket.setComms(util.ConvertString(B[8]));
                    ticket.setCost(util.ConvertString(B[9]));
                    ticket.setPfloss(util.ConvertString(B[10]));
                    ticket.setDiff(util.ConvertString(B[11]));
                    
                    ticket.setInvno(util.ConvertString(B[13]));
                    if("".equalsIgnoreCase(util.ConvertString(B[13]))){
                        ticket.setNoinvamount(util.ConvertString(B[12]));
                        ticket.setInvamount("");
                    }else{
                        ticket.setInvamount(util.ConvertString(B[12]));
                        ticket.setNoinvamount("");
                    }
//                    ticket.setTaxD(util.ConvertString(B[14]));
                    ticket.setPage("detail");
                }
                data.add(ticket);
            }
            
        }
        
        return data;
    }

    public UtilityFunction getUtilityFunction() {
        return utilityFunction;
    }

    public void setUtilityFunction(UtilityFunction utilityFunction) {
        this.utilityFunction = utilityFunction;
    }
}
