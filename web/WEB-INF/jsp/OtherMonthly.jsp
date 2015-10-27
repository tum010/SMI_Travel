<%-- 
    Document   : OtherMonthly
    Created on : Oct 26, 2015, 11:06:49 AM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="content-header"  >
    <h4>
        <b>Report : Other Monthly </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Other Monthly</a></li>
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
                    <h3>Other Monthly Report</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="OtherMonthlyFrom" method="post" class="form-horizontal">      
                    
                    
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="fromdatepanel">
                                <label class="col-md-5 control-label text-right">From<font style="color: red">*</font></label>
                                <div class="col-md-4">  
                                    <div class="form-group">
                                        <div class='input-group date' id='DateFrom'>
                                            <input type='text' id="fromdate" name="fromdate" class="form-control" data-date-format="YYYY-MM-DD"/>
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
                                <label class="col-md-5 control-label text-right">To<font style="color: red">*</font></label>
                                <div class="col-md-4">  
                                    <div class="form-group">
                                        <div class='input-group date' id='DateTo'>
                                            <input type='text' id="todate" name="todate"  class="form-control" data-date-format="YYYY-MM-DD" />
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
                                            <option value="">--Select--</option>
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
                                <div class="col-sm-7 text-right">
                                    <button type="button"  id="printbutton" name="printbutton" onclick="printOtherMonthlyReport()" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
                                </div>
<!--                                <div class="col-sm-2 text-left">
                                    <button type="button" onclick="" class="btn btn-warning"><span class="glyphicon glyphicon-print"></span> Cancel</button>
                                </div>-->
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
        $('.date').datetimepicker({
        });
        
        $('span').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });

        
         $("#OtherMonthlyFrom")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    fromdate: {
                        trigger: 'focus keyup change',
                            validators: {
                                date: {
                                    format: 'YYYY-MM-DD',
                                    max: 'todate',
                                    message: 'The Date From is not a valid'
                                },notEmpty: {
                                    message: 'The Date From is required'
                                }
                            }
                    },
                    todate: {
                        trigger: 'focus keyup change',
                            validators: {
                                date: {
                                    format: 'YYYY-MM-DD',
                                    min: 'fromdate',
                                    message: 'The Date To is not a valid'
                                },notEmpty: {
                                    message: 'The Date From is required'
                                }
                            }
                    }
                }
            }).on('success.field.fv', function (e, data) {
//                alert("1");
                if (data.field === 'fromdate' && data.fv.isValidField('todate') === false) {
                    data.fv.revalidateField('todate');
                }

                if (data.field === 'todate' && data.fv.isValidField('fromdate') === false) {
                    data.fv.revalidateField('fromdate');
                }
            });
            
            $('#DateFrom').datetimepicker().on('dp.change', function (e) {
                $('#OtherMonthlyFrom').bootstrapValidator('revalidateField', 'fromdate');
                var fromdate = document.getElementById("fromdate").value;
                var todate = document.getElementById("todate").value;
                if(((fromdate !== '') && (todate !== '')) && fromdate < todate){
                    $("#printbutton").removeClass("disabled");
                }else if((((fromdate !== '') && (todate !== '')) && fromdate === todate)) {
                    $("#printbutton").removeClass("disabled");
                }else{
                    $("#printbutton").addClass("disabled");
                }
            });
            $('#DateTo').datetimepicker().on('dp.change', function (e) {
                $('#OtherMonthlyFrom').bootstrapValidator('revalidateField', 'todate');
                var fromdate = document.getElementById("fromdate").value;
                var todate = document.getElementById("todate").value;
                if(((fromdate !== '') && (todate !== '')) && fromdate < todate){
                    $("#printbutton").removeClass("disabled");
                }else if((((fromdate !== '') && (todate !== '')) && fromdate === todate)) {
                    $("#printbutton").removeClass("disabled");
                }else{
                    $("#printbutton").addClass("disabled");
                }
            });  
    });   
    
    function printOtherMonthlyReport(){
        var fromdate = document.getElementById("fromdate").value;
        var todate = document.getElementById("todate").value;
        var department = document.getElementById("department").value;
        var detail = document.getElementById("detail").value;
        
        if(((fromdate !== '') && (todate !== '')) && fromdate < todate){
            $("#printbutton").removeClass("disabled");
            window.open("report.smi?name=OtherMonthlyReport"+"&fromdate="+fromdate+"&todate="+todate+"&department="+department+"&detail="+detail);  
        }else{
            $('#OtherMonthlyFrom').bootstrapValidator('revalidateField', 'fromdate');
            $('#OtherMonthlyFrom').bootstrapValidator('revalidateField', 'todate');
            $("#printbutton").addClass("disabled");
        }
        
    }
    
    
    
    
</script>
<!--<script type="text/javascript" src="js/HotelSummaryReport.js"></script> -->
