<%-- 
    Document   : HotelMonthly
    Created on : Oct 26, 2015, 11:03:55 AM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="printby" value="${requestScope['printby']}" />
<section class="content-header"  >
    <h4>
        <b>Report : Hotel Monthly </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Hotel Monthly</a></li>
    </ol>
</section>
<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/PackageTourHotelMenu.html'"></div>
            </div>
            <div class="form-group">
                <div class="col-md-6">
                    <h3>Hotel Monthly Report</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="HotelMonthlyReportFrom" method="post" class="form-horizontal" onsubmit="printHotelMonthly();">                   
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right"> From <font style="color: red;">*</font></label>
                                <div class="col-md-4">  
                                    <div class="form-group" id="DateFrom">
                                        <div class='input-group date fromdate' id="fromdatepanel">
                                            <input type='text' id="fromdate" name="fromdate" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY"/>
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
                                        <div class='input-group date todate' id="todatepanel">
                                            <input   type='text' id="todate" name="todate" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" />
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
                                <label for="depart" class="col-sm-5 control-label text-right">Detail</label>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <select id="detail" name="detail"  class="form-control">
                                            <option value="1">Show</option>
                                            <option value="2">Not Show</option>
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
                                <div class="col-sm-8 text-right">
                                    <button type="button"  id="printbutton" name="printbutton"  onclick="printHotelMonthly()"class="btn btn-success"><span class="glyphicon glyphicon-print" ></span> Print</button>
                                </div>
                                <div class="col-sm-2 text-left hidden">
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
<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () { 
        $('.datemask').mask('00-00-0000');
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
        
//         $("#HotelMonthlyReportFrom")
//            .bootstrapValidator({
//                framework: 'bootstrap',
//                feedbackIcons: {
//                    valid: 'uk-icon-check',
//                    invalid: 'uk-icon-times',
//                    validating: 'uk-icon-refresh'
//                },
//                fields: {
//                    fromdate: {
//                        trigger: 'focus keyup change',
//                            validators: {
//                                date: {
//                                    format: 'DD-MM-YYYY',
//                                    max: 'todate',
//                                    message: 'The Date From is not a valid'
//                                },notEmpty: {
//                                    message: 'The Date From is required'
//                                }
//                            }
//                    },
//                    todate: {
//                        trigger: 'focus keyup change',
//                            validators: {
//                                date: {
//                                    format: 'DD-MM-YYYY',
//                                    min: 'fromdate',
//                                    message: 'The Date To is not a valid'
//                                },notEmpty: {
//                                    message: 'The Date From is required'
//                                }
//                            }
//                    }
//                }
//            }).on('success.field.fv', function (e, data) {
////                alert("1");
//                if (data.field === 'fromdate' && data.fv.isValidField('todate') === false) {
//                    data.fv.revalidateField('todate');
//                }
//
//                if (data.field === 'todate' && data.fv.isValidField('fromdate') === false) {
//                    data.fv.revalidateField('fromdate');
//                }
//            });
//            $('#DateFrom').datetimepicker().on('dp.change', function (e) {
////                alert("1");
//                $('#HotelMonthlyReportFrom').bootstrapValidator('revalidateField', 'fromdate');
//            });
//            $('#DateTo').datetimepicker().on('dp.change', function (e) {
//                $('#HotelMonthlyReportFrom').bootstrapValidator('revalidateField', 'todate');
//            });  
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
            var fromDate = (convertFormatDate(inputFromDate.value)).split('-');
            var toDate = (convertFormatDate(InputToDate.value)).split('-');
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
</script>
<input type="text" class="hidden" value="${printby}" id="systemuser" name="systemuser">
<script type="text/javascript" src="js/HotelMonthly.js"></script> 