<%-- 
    Document   : OutboundAirTicketAirlineStaffSummary
    Created on : Dec 1, 2015, 10:18:50 AM
    Author     : chonnasith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/OutboundAirStaffSummary.js"></script>
<section class="content-header"  >
    <h4>
        <b>Report : Outbound Airline/Staff Summary </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Outbound Airline/Staff Summary</a></li>
    </ol>
</section>
<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/OutboundSummaryMenu.html'"></div>
            </div>
            <div class="form-group">
                <div class="col-md-6">
                    <h3>Outbound Airline/Staff Summary Report</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="OutboundAirlineStaffSummaryReportFrom" method="post" class="form-horizontal" >                   
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="ticketUse">Type<font style="color: red">*</font></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="Doctype" id="Doctype"  class="form-control">
                                            <option value="1"  selected="selected"> Airline </option>
                                            <option value="2">Staff</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>    
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="fromdatepanel">
                                <label class="col-md-6 control-label text-right"> From Date<font style="color: red">*</font></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <div class='input-group date' id='fromdate'>
                                            <input type='text' id="startdate" name="startdate"  class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="todatepanel">
                                <label class="col-md-6 control-label text-right"> To Date<font style="color: red">*</font></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <div class='input-group date' id='todate'>
                                            <input type='text' id="enddate" name="enddate"  class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="ticketUse">Ticket From</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="ticketFrom" id="ticketFrom"  class="form-control">
                                            <option value=""  selected="selected">-- all --</option>
                                            <option value="C" >In</option>
                                            <option value="O" >Out</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="ticketType">Ticket Type</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="ticketType" id="ticketType"  class="form-control">
                                            <option value=""  selected="selected">-- all --</option>
                                            <option value="I">Inter</option>
                                            <option value="D">Domestic</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>            
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="rept"></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <button type="button" onclick="printDocSummary();" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                </form>                
            </div>
        </div>
    </div>
</div>