<%-- 
    Document   : OutboundProductSummary
    Created on : Nov 23, 2015, 5:40:05 PM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="content-header"  >
    <h4>
        <b>Report : Outbound Product Summary </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Outbound Product Summary</a></li>
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
                    <h3>Outbound Product Summary Report</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="HotelSummaryReportFrom" method="post" class="form-horizontal" >                   
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right"> From <font style="color: red;">*</font></label>
                                <div class="col-md-4">  
                                    <div class="form-group" id="DateFrom">
                                        <div class='input-group date'>
                                            <input type='text' id="fromdate" name="fromdate" class="form-control" data-date-format="YYYY-MM-DD" />
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
                            <div class="form-group" id="DateTo">
                                <label class="col-md-5 control-label text-right"> To <font style="color: red;">*</font></label>
                                <div class="col-md-4">  
                                    <div class="form-group">
                                        <div class='input-group date'>
                                            <input   type='text' id="todate" name="todate" class="form-control" data-date-format="YYYY-MM-DD"  />
                                            <span class="input-group-addon"><span  class="glyphicon glyphicon-calendar"></span>
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
                                <label for="depart" class="col-sm-5 control-label text-right">Department</label>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <select id="department" name="department"  class="form-control">
                                            <option value="">--Select--</option>
                                            <option value="I">Wendy</option>
                                            <option value="O">Outbound</option>
                                            <!--<option value="Inbound">Inbound</option>-->
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <div class="col-sm-7 text-right">
                                    <button type="button"  class="btn btn-success" onclick="printHotelSummary();"><span class="glyphicon glyphicon-print" id="btnDownloadAP"></span> Print</button>
                                </div>
                                <div class="col-sm-2 text-left">
                                    <button type="button" onclick="" class="btn btn-warning"><span class="glyphicon glyphicon-print"></span> Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>                
            </div>
        </div>
    </div>
</div>