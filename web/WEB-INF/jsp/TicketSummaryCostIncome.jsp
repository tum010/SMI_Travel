<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/TicketSummaryCostIncome.js"></script> -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="termPayList" value="${requestScope['termPayList']}" />
<c:set var="userList" value="${requestScope['userList']}" />
<section class="content-header"  >
    <h4>
        <b>Report : Ticket Summary Cost & Income report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Ticket Summary Cost & Income</a></li>
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
                    <h3>Ticket Summary Cost & Income</h3>
                </div>
            </div>
            
            <div class="col-md-10" >
                <form action="TicketSummaryCostIncome.smi" method="post" id="TicketSummaryCostIncomeForm" name="TicketSummaryCostIncomeForm" role="form">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Report Type</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="reportType" id="reportType"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="1">Ticket Cost Income</option>
                                            <option value="2">Ticket Adjust Cost Income</option>
                                            <option value="3">Ticket Commission Receive</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="fromdatepanel">
                                <label class="col-md-6 control-label text-right">Invoice Date From<font style="color: red"></font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date fromdate' id='DateFrom'>
                                            <input type='text' id="invoiceFromDate" name="invoiceFromDate" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD"/>
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
                                <label class="col-md-6 control-label text-right">Invoice Date To<font style="color: red"></font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date todate' id='DateTo'>
                                            <input type='text' id="invoiceToDate" name="invoiceToDate" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD" />
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
                            <div class="form-group" id="issuefromdatepanel">
                                <label class="col-md-6 control-label text-right">Issue Date From<font style="color: red"></font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date issuefromdate' id='DateFromIssue'>
                                            <input type='text' id="issueFrom" name="issueFrom" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD"/>
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
                            <div class="form-group" id="issuetodatepanel">
                                <label class="col-md-6 control-label text-right">Issue Date To<font style="color: red"></font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date issuetodate' id='DateToIssue'>
                                            <input type='text' id="issueTo" name="issueTo" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD"/>
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
                                <label class="col-md-6 control-label text-right" >Payment Type<font style="color: red"></font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="paymentType" id="paymentType"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="B" >BSP</option>
                                            <option value="D" >DOMESTIC</option>
                                            <option value="A" >AGENT</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Department<font style="color: red"></font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="department" id="department" class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="wendy" >Wendy</option>
                                            <option value="inbound" >Inbound</option>
                                            <option value="outbound" >Outbound</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Sale By</label>
                                <div class="col-md-3 form-group">  
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="salebyId" name="salebyId" value=""/>
                                        <input type="text" class="form-control" id="salebyUser" name="salebyUser" value="" />
                                        <span class="input-group-addon" id="saleby_modal"  data-toggle="modal" data-target="#SaleByModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="salebyName" name="salebyName" value="" readonly="">
                                </div>
                            </div>   
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Term Pay<font style="color: red"></font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="termPay" id="termPay"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:forEach var="term" items="${termPayList}" >
                                                <c:set var="select" value="" />
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
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
                                        <button type="button" id="printbutton"  name="printbutton"  class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
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

<div class="modal fade" id="SaleByModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Sale By</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="SaleByTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        saleby = [];
                    </script>
                    <c:forEach var="table" items="${userList}">
                        <tr>
                            <td class="saleby-id hidden">${table.id}</td>
                            <td class="saleby-user">${table.username}</td>
                            <td class="saleby-name">${table.name}</td>
                        </tr>
                        <script>
                            saleby.push({id: "${table.id}", username: "${table.username}", name: "${table.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="SaleByModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div> 

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
         $('.datemask').mask('0000-00-00');
        $('.date').datetimepicker({
            
        });
        
        $('span').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });
        
//        $("#TicketSummaryCostIncomeForm").bootstrapValidator({
//            framework: 'bootstrap',
//            feedbackIcons: {
//                valid: 'uk-icon-check',
//                invalid: 'uk-icon-times',
//                validating: 'uk-icon-refresh'
//            },
//            fields: {
//                invoiceFromDate: {
//                    trigger: 'focus keyup change',
//                    validators: {
//                        date: {
//                            format: 'YYYY-MM-DD',
//                            max: 'invoiceToDate',
//                            message: 'The Date From is not a valid'
//                        },
//                        notEmpty: {
//                            message: 'The Date From is required'
//                        }
//                    }
//                },
//                invoiceToDate: {
//                    trigger: 'focus keyup change',
//                    validators: {
//                        date: {
//                            format: 'YYYY-MM-DD',
//                            min: 'invoiceFromDate',
//                            message: 'The Date To is not a valid'
//                        },
//                        notEmpty: {
//                            message: 'The Date To is required'
//                        }
//                    }
//                },
//                issueFrom: {
//                    trigger: 'focus keyup change',
//                    validators: {
//                        date: {
//                            format: 'YYYY-MM-DD',
//                            max: 'issueTo',
//                            message: 'The Date To is not a valid'
//                        },
//                        notEmpty: {
//                            message: 'The Date Issue From is required'
//                        }
//                    }
//                },
//                issueTo: {
//                    trigger: 'focus keyup change',
//                    validators: {
//                        date: {
//                            format: 'YYYY-MM-DD',
//                            min: 'issueFrom',
//                            message: 'The Date To is not a valid'
//                        },
//                        notEmpty: {
//                            message: 'The Date Issue To is required'
//                        }
//                    }
//                }
//            }
//        }).on('success.field.fv', function (e, data) {
//            if (data.field === 'invoiceFromDate' && data.fv.isValidField('invoiceToDate') === false) {
//                    data.fv.revalidateField('invoiceToDate');
//            }
//            if (data.field === 'invoiceToDate' && data.fv.isValidField('invoiceFromDate') === false) {
//                data.fv.revalidateField('invoiceFromDate');
//            }
//
//            if (data.field === 'issueFrom' && data.fv.isValidField('issueTo') === false) {
//                    data.fv.revalidateField('issueTo');
//            }
//            if (data.field === 'issueTo' && data.fv.isValidField('issueFrom') === false) {
//                data.fv.revalidateField('issueFrom');
//            }
//        });
//        
//        $('#DateFrom').datetimepicker().on('dp.change', function (e) {
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceFromDate');
//        });
//        $('#DateTo').datetimepicker().on('dp.change', function (e) {
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceToDate');
//        });
//        $('#DateFromIssue').datetimepicker().on('dp.change', function (e) {
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
//        });
//        $('#DateToIssue').datetimepicker().on('dp.change', function (e) {
//           $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
//        });
        
        $('.fromdate').datetimepicker().change(function(){                          
            checkFromDateField();
        });
        $('.todate').datetimepicker().change(function(){                          
            checkToDateField();
        });
        $('.issuefromdate').datetimepicker().change(function(){                          
            checkIssueFromDateField();
        });
        $('.issuetodate').datetimepicker().change(function(){                          
            checkIssueToDateField();
        });
        
        $("#SaleByTable tr").on('click', function () {
            var saleby_id = $(this).find(".saleby-id").text();
            var saleby_user = $(this).find(".saleby-user").text();
            var saleby_name = $(this).find(".saleby-name").text();
            $("#salebyId").val(saleby_id);
            $("#salebyUser").val(saleby_user);
            $("#salebyName").val(saleby_name);
            $("#SaleByModal").modal('hide');
        });

        var salebyuser = [];
        $.each(saleby, function (key, value) {
            salebyuser.push(value.username);
            salebyuser.push(value.name);
        });

        $("#salebyUser").autocomplete({
            source: salebyuser,
            close:function( event, ui ) {
               $("#salebyUser").trigger('keyup');
            }
        });

        $("#salebyUser").on('keyup',function(){
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var username = this.value.toUpperCase();
            var name = this.value.toUpperCase();
           // console.log("Name :"+ name);
            $("#salebyId,#salebyName").val(null);
            $.each(saleby, function (key, value) {
                if (value.username.toUpperCase() === username ) {  
                    $("#salebyId").val(value.id);
                    $("#salebyUser").val(value.username);
                    $("#salebyName").val(value.name);
                }
                else if(value.name.toUpperCase() === name){
                    $("#salebyUser").val(value.username);
                    $("#salebyId").val(value.id);
                    $("#salebyName").val(value.name);
                }
            }); 
        }); 

        $('#SaleByTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });

        $('#SaleByTable tbody').on('click', 'tr', function () {
            $(this).addClass('row_selected').siblings().removeClass('row_selected');
        });
        
        $('#printbutton').click( function() {  
            printTicketSummaryCostIncome();
        });
    });
    
    function printTicketSummaryCostIncome(){
        var invoiceFromDate = document.getElementById('invoiceFromDate').value;
        var invoiceToDate = document.getElementById('invoiceToDate').value;
        var issueFrom = document.getElementById('issueFrom').value;
        var issueTo = document.getElementById('issueTo').value;
        var reportType = document.getElementById('reportType').value;
        var paymentType = document.getElementById('paymentType').value;
        var departmentt = document.getElementById('department').value;
        var termPayt = document.getElementById('termPay').value;
        var salebyUser = document.getElementById('salebyUser').value;
        if((invoiceFromDate !== '') && (invoiceToDate !== '')){
            if(reportType === '1'){
                window.open("Excel.smi?name=SummaryTicketCostAndIncome"+"&reportType="+reportType+"&invoiceFromDate="+invoiceFromDate+"&invoiceToDate="+invoiceToDate+"&issueFrom="+issueFrom+"&issueTo="+issueTo+"&paymentType="+paymentType+"&department="+departmentt+"&salebyName="+salebyUser+"&termPay="+termPayt);  
            }else if(reportType === '2'){
                window.open("Excel.smi?name=SummaryTicketAdjustCostAndIncome"+"&reportType="+reportType+"&invoiceFromDate="+invoiceFromDate+"&invoiceToDate="+invoiceToDate+"&issueFrom="+issueFrom+"&issueTo="+issueTo+"&paymentType="+paymentType+"&department="+departmentt+"&salebyName="+salebyUser+"&termPay="+termPayt);  
            }else if(reportType === '3'){
                window.open("Excel.smi?name=SummaryTicketCommissionReceive"+"&reportType="+reportType+"&invoiceFromDate="+invoiceFromDate+"&invoiceToDate="+invoiceToDate+"&issueFrom="+issueFrom+"&issueTo="+issueTo+"&paymentType="+paymentType+"&department="+departmentt+"&salebyName="+salebyUser+"&termPay="+termPayt);  
            }else{
                window.open("Excel.smi?name=SummaryTicketAdjustCostAndIncome"+"&reportType="+reportType+"&invoiceFromDate="+invoiceFromDate+"&invoiceToDate="+invoiceToDate+"&issueFrom="+issueFrom+"&issueTo="+issueTo+"&paymentType="+paymentType+"&department="+departmentt+"&salebyName="+salebyUser+"&termPay="+termPayt);  
            }
        } else if((issueFrom !== '') && (issueTo !== '')){
            if(reportType === '1'){
                window.open("Excel.smi?name=SummaryTicketCostAndIncome"+"&reportType="+reportType+"&invoiceFromDate="+invoiceFromDate+"&invoiceToDate="+invoiceToDate+"&issueFrom="+issueFrom+"&issueTo="+issueTo+"&paymentType="+paymentType+"&department="+departmentt+"&salebyName="+salebyUser+"&termPay="+termPayt);  
            }else if(reportType === '2'){
                window.open("Excel.smi?name=SummaryTicketAdjustCostAndIncome"+"&reportType="+reportType+"&invoiceFromDate="+invoiceFromDate+"&invoiceToDate="+invoiceToDate+"&issueFrom="+issueFrom+"&issueTo="+issueTo+"&paymentType="+paymentType+"&department="+departmentt+"&salebyName="+salebyUser+"&termPay="+termPayt);  
            }else if(reportType === '3'){
                window.open("Excel.smi?name=SummaryTicketCommissionReceive"+"&reportType="+reportType+"&invoiceFromDate="+invoiceFromDate+"&invoiceToDate="+invoiceToDate+"&issueFrom="+issueFrom+"&issueTo="+issueTo+"&paymentType="+paymentType+"&department="+departmentt+"&salebyName="+salebyUser+"&termPay="+termPayt);  
            }else{
                window.open("Excel.smi?name=SummaryTicketAdjustCostAndIncome"+"&reportType="+reportType+"&invoiceFromDate="+invoiceFromDate+"&invoiceToDate="+invoiceToDate+"&issueFrom="+issueFrom+"&issueTo="+issueTo+"&paymentType="+paymentType+"&department="+departmentt+"&salebyName="+salebyUser+"&termPay="+termPayt);  
            }
        } else {
           validateDate();  
        }
    }
    
    function checkFromDateField(){
        var InputToDate = document.getElementById("invoiceToDate");
        var inputFromDate = document.getElementById("invoiceFromDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#printbutton").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){        
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceToDate');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceFromDate');           
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success");
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        } else {
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceToDate');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceFromDate');
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
        var InputToDate = document.getElementById("invoiceToDate");
        var inputFromDate = document.getElementById("invoiceFromDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#printbutton").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){ 
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceToDate');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceFromDate');
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success");
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        }else{
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceToDate');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceFromDate');
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
    
    function checkIssueFromDateField(){
        var issueToDate = document.getElementById("issueTo");
        var issueFromDate = document.getElementById("issueFrom");
        if(issueFromDate.value === '' && issueToDate.value === ''){
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
            $("#issuefromdatepanel").removeClass("has-error");
            $("#issuetodatepanel").removeClass("has-error");
            $("#printbutton").removeClass("disabled");
        }else if(issueFromDate.value === '' || issueToDate.value === ''){        
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
            $("#issuefromdatepanel").removeClass("has-success");
            $("#issuetodatepanel").removeClass("has-success");  
            $("#issuefromdatepanel").addClass("has-error");
            $("#issuetodatepanel").addClass("has-error");  
            $("#printbutton").addClass("disabled");
        } else {
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#issuefromdatepanel").removeClass("has-error");
            $("#issuetodatepanel").removeClass("has-error");
            $("#issuefromdatepanel").addClass("has-success");
            $("#issuetodatepanel").addClass("has-success");
            $("#printbutton").removeClass("disabled");
            checkDateValue("issuefrom","");
        }    
    }
    
    function checkIssueToDateField(){
        var issueToDate = document.getElementById("issueTo");
        var issueFromDate = document.getElementById("issueFrom");
        if(issueFromDate.value === '' && issueToDate.value === ''){
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
            $("#issuefromdatepanel").removeClass("has-error");
            $("#issuetodatepanel").removeClass("has-error");
            $("#printbutton").removeClass("disabled");
        }else if(issueFromDate.value === '' || issueToDate.value === ''){ 
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
            $("#issuefromdatepanel").removeClass("has-success");
            $("#issuetodatepanel").removeClass("has-success");  
            $("#issuefromdatepanel").addClass("has-error");
            $("#issuetodatepanel").addClass("has-error"); 
            $("#printbutton").addClass("disabled");
        }else{
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#issuefromdatepanel").removeClass("has-error");
            $("#issuetodatepanel").removeClass("has-error");
            $("#issuefromdatepanel").addClass("has-success");
            $("#issuetodatepanel").addClass("has-success");
            $("#printbutton").removeClass("disabled");
            checkDateValue("issueto","");
        }       
    }
    
    function checkDateValue(date){
        var inputFromDate = "";
        var InputToDate = "";
        if((date === 'from') || (date === 'to')){
            inputFromDate = document.getElementById("invoiceFromDate");
            InputToDate = document.getElementById("invoiceToDate");
        } else {
            inputFromDate = document.getElementById("issueFrom");
            InputToDate = document.getElementById("issueTo");
        }
        
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
            if(date === 'from'){
//               $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceFromDate');
//               $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceToDate');
                $("#fromdatepanel").removeClass("has-success");
                $("#fromdatepanel").addClass("has-error");                                 
            }
            if(date === 'to'){
//               $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceFromDate');
//               $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceToDate');
                $("#todatepanel").removeClass("has-success");
                $("#todatepanel").addClass("has-error");
            }
            if(date === 'issuefrom'){
//               $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
//               $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
                $("#issuefromdatepanel").removeClass("has-success");
                $("#issuefromdatepanel").addClass("has-error");
            }
            if(date === 'issueto'){
//               $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
//               $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
                $("#issuetodatepanel").removeClass("has-success"); 
                $("#issuetodatepanel").addClass("has-error");
            }       
            $("#printbutton").addClass("disabled");
        } else {
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceFromDate');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'invoiceToDate');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueFrom');
//            $('#TicketSummaryCostIncomeForm').bootstrapValidator('revalidateField', 'issueTo');
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success");
            $("#issuefromdatepanel").removeClass("has-success");
            $("#issuetodatepanel").removeClass("has-success"); 
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#issuefromdatepanel").addClass("has-error");
            $("#issuetodatepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        }
    }
</script>