<%-- 
    Document   : OutboundAirTicketAirlineStaffSummary
    Created on : Dec 1, 2015, 10:18:50 AM
    Author     : chonnasith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right"> From <font style="color: red;">*</font></label>
                                <div class="col-md-6">  
                                    <div class="form-group" id="DateFrom">
                                        <div class='input-group date fromdate' id="fromdatepanel">
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
                                <label class="col-md-6 control-label text-right"> To <font style="color: red;">*</font></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <div class='input-group date todate' id="todatepanel">
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
                                        <button type="button" onclick="printDocSummary();" id="printbutton" name="printbutton" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
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

<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () { 
        $('.date').datetimepicker();
        $('span').click(function () {
            var position = $(this).offset();
            console.log("positon :"+position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        
        var from = setValueFromDate();
        var to = setValueToDate();
        $("#fromdate").val(from);
        $("#todate").val(to);

        $('.fromdate').datetimepicker().change(function(){                          
            checkFromDateField();
        });
        $('.todate').datetimepicker().change(function(){                          
            checkToDateField();
        });
    });   
    
    function validateDate(date,option){
        if(option === 'over'){
            $("#fromdatepanel").removeClass("has-success");
            $("#fromdatepanel").addClass("has-error");                                 
            $("#todatepanel").removeClass("has-success");
            $("#todatepanel").addClass("has-error");   
            $("#printbutton").addClass("disabled");
        } else {
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success"); 
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        }
    }
    
    function checkFromDateField(){      
        var inputFromDate = document.getElementById("fromdate");
        var InputToDate = document.getElementById("todate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");  
            $("#printbutton").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success");
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        } else {
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#issuefromdatepanel").removeClass("has-error");
            $("#issuetodatepanel").removeClass("has-error");
            $("#fromdatepanel").addClass("has-success");
            $("#todatepanel").addClass("has-success");
            $("#printbutton").removeClass("disabled");
            checkDateValue("from","");
        }
    }
    
    function checkToDateField(){
        var InputToDate = document.getElementById("todate");
        var inputFromDate = document.getElementById("fromdate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");  
            $("#printbutton").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success");
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        }else{
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#issuefromdatepanel").removeClass("has-error");
            $("#issuetodatepanel").removeClass("has-error");
            $("#fromdatepanel").addClass("has-success");
            $("#todatepanel").addClass("has-success");
            $("#printbutton").removeClass("disabled");
            checkDateValue("to","");
        }       
    }
    
    function checkDateValue(date){
        var inputFromDate = document.getElementById("fromdate");
        var InputToDate = document.getElementById("todate");
        if((inputFromDate.value !== '') && (InputToDate.value !== '')){
            var fromDate = (inputFromDate.value).split('-');
            var toDate = (InputToDate.value).split('-');
            if((parseInt(fromDate[0])) > (parseInt(toDate[0]))){
                validateDate(date,"over");
            }
            if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))){
                validateDate(date,"over");
            }
            if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[2])) > (parseInt(toDate[2]))){
                validateDate(date,"over");
            }          
        }
    }
    
    function printDocSummary() {
        var type = document.getElementById("Doctype").value;
        var ticketfrom = document.getElementById("ticketFrom").value;
        var tickettype = document.getElementById("ticketType").value;
        var startdate = document.getElementById("fromdate").value;
        var enddate = document.getElementById("todate").value;

        if((startdate !== '') && (enddate !== '')){
            if (type == 1) {
                window.open("report.smi?name=AirlineSummary&ticketfrom=" + ticketfrom + "&tickettype=" + tickettype + "&startdate=" + startdate + "&enddate=" + enddate + "&department=Outbound");
            } else {
                window.open("report.smi?name=StaffSummary&ticketfrom=" + ticketfrom + "&tickettype=" + tickettype + "&startdate=" + startdate + "&enddate=" + enddate + "&department=Outbound");
            }
        }else{    
            validateDate();
        }  
    }

</script>
