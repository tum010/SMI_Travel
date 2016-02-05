<%-- 
    Document   : TicketProfitLost
    Created on : Oct 9, 2015, 10:17:16 AM
    Author     : chonnasith
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/TicketRefundSummary.js"></script>--> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="content-header"  >
    <h4>
        <b>Report : Ticket Profit Loss </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Ticket Profit Loss</a></li>
    </ol>
</section>

<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/AirticketReportMenu.html'"></div>
            </div>

            <div class="form-group">
                <div class="col-md-6">
                    <h3>Ticket Profit Loss Summary</h3>
                </div>
            </div>
            
            <div class="col-md-10" >
                <form action="TicketProfitLost.smi" method="post" id="TicketProfitLostForm" name="TicketProfitLostForm" role="form">
                    <div class="row">    
                        <div class="col-xs-1 text-right"  style="width: 330px">
                            <label class="control-label text-right">Invoice Date From<font style="color:red">*</font></label>
                        </div>
                        <div class="col-xs-1 form-group" style="width: 300px"> 
                            <div class='input-group date fromdate' id='fromdatepanel'>                    
                                <input id="invoiceDateFrom" name="invoiceDateFrom"  type="text" 
                                    class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                            </div>
                        </div>
                    </div>
                    <div class="row">    
                        <div class="col-xs-1 text-right"  style="width: 330px">
                            <label class="control-label text-right">Invoice Date To<font style="color:red">*</font></label>
                        </div>
                        <div class="col-xs-1 form-group" style="width: 300px"> 
                            <div class='input-group date todate' id='todatepanel'>                    
                                <input id="invoiceDateTo" name="invoiceDateTo"  type="text" 
                                    class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-11 text-center">
                            <button type="button" id="printbutton"  name="printbutton"  onclick="printTicketProfitLost();" class="btn btn-success">
                                <span class="glyphicon glyphicon-print"></span> Print
                            </button>
                        </div>
                    </div>    
                </form>
            </div>
        </div>
    </div>    
</div>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $('.date').datetimepicker();       
        $('.datemask').mask('0000-00-00');
        $('.spandate').click(function () {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        $('.fromdate').datetimepicker().change(function(){                          
            checkFromDateField();
        });
        $('.todate').datetimepicker().change(function(){                          
            checkToDateField();
        });
    });
    
    function checkFromDateField(){      
        var inputFromDate = document.getElementById("invoiceDateFrom");
        var InputToDate = document.getElementById("invoiceDateTo");
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
        var InputToDate = document.getElementById("invoiceDateTo");
        var inputFromDate = document.getElementById("invoiceDateFrom");
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
        var inputFromDate = document.getElementById("invoiceDateFrom");
        var InputToDate = document.getElementById("invoiceDateTo");
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
    
    function printTicketProfitLost(){
        var inputFromDate = document.getElementById("invoiceDateFrom").value;
        var InputToDate = document.getElementById("invoiceDateTo").value;
        if((inputFromDate !== '') && (InputToDate !== '')){
            window.open("Excel.smi?name=TicketProfitLoss"+ 
                    "&invoiceFromDate=" + inputFromDate + 
                    "&invoiceToDate=" + InputToDate);
        } else {
            validateDate();
        }
    }
</script>