<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/SearchPaymentOutbound.js"></script>--> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>

<c:set var="statusList" value="${requestScope['statusList']}" />
<c:set var="paymentOutboundViewList" value="${requestScope['paymentOutboundViewList']}" />
<c:set var="result" value="${requestScope['result']}" />
<input type="hidden" id="result" name="result" value="${result}"/>

<section class="content-header" >
    <h1>
        Checking - Search Payment Outbound
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Checking</a></li>          
        <li class="active"><a href="#">Search Payment Outbound</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/Checking/CheckingOutboundMenu.html'"></div>
    </div>
    <!--Content -->
    <div class="col-sm-10">
        <form action="SearchPaymentOutbound.smi" method="post" id="searchPaymentOutboundForm" name="searchPaymentOutboundForm" role="form">
        <!--Alert Save -->
        <div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Delete Success!</strong> 
        </div>
        <div id="textAlertDivNotDelete"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Delete Not Success!</strong> 
        </div>
        <div class="row" style="padding-left: 0px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Search Payment Outbound</b></h4>
            </div>
        </div>
        <hr/>
        <div class="row" style="padding-left: 0px;">
            <div class="col-xs-1 text-right" style="width:150px;padding-right: 0px;padding-left: 0px;">
                <label class="control-label">Pay Date From<font style="color: red;">*</font></lable>
            </div>
            <div class="col-md-2 form-group text-left" id="fromdatepanel">
                <div class='input-group date fromdate' id="DateFrom">
                    <c:set var="fromDate" value="${requestScope['fromDate']}" />
                    <input name="fromDate" id="fromDate" type="text" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${fromDate}" />
                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <div class="col-xs-1 text-right" style="width:150px;padding-right: 0px;padding-left: 0px;">
                <label class="control-label">To<font style="color: red;">*</font></lable>
            </div>
            <div class="col-md-2 form-group text-left" id="todatepanel">
                <div class='input-group date todate' id="DateTo">
                    <c:set var="toDate" value="${requestScope['toDate']}" />
                    <input name="toDate" id="toDate" type="text" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${toDate}" />
                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <div class="col-xs-1 text-right" style="width:150px;padding-right: 0px;padding-left: 0px;">
                <label class="control-label">Pay No</lable>
            </div>
            <div class="col-md-2 form-group text-left" >
                <input name="payNo" id="payNo" type="text" class="form-control" value="${requestScope['payNo']}" style="text-transform: uppercase;"/>
            </div>            
        </div><!-- End Row 1-->
        <div class="row" style="padding-left: 0px;">
            <div class="col-xs-1 text-right" style="width:150px;padding-right: 0px;padding-left: 0px;margin-top:-10px;">
                <label class="control-label">Due Date From<font style="color: red;"></font></lable>
            </div>
            <div class="col-md-2 form-group text-left" style="margin-top:-10px;" id="duefromdatepanel">
                <div class='input-group date duefromdate' id="DateFromDue">
                    <c:set var="dueDateFrom" value="${requestScope['dueDateFrom']}" />
                    <%--<fmt:parseDate value="${dueDateFrom}" var="dueDateFrom" pattern="yyyy-MM-dd" />--%>
                    <%--<fmt:formatDate value="${dueDateFrom}" var="dueDateFrom" pattern="dd-MM-yyyy" />--%>
                    <input name="dueDateFrom" id="dueDateFrom" type="text" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${dueDateFrom}" />
                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <div class="col-xs-1 text-right" style="width:150px;padding-right: 0px;padding-left: 0px;margin-top:-10px;">
                <label class="control-label">To<font style="color: red;"></font></lable>
            </div>
            <div class="col-md-2 form-group text-left" style="margin-top:-10px;" id="duetodatepanel">
                <div class='input-group date duetodate' id='DateToDue'>
                    <c:set var="dueDateTo" value="${requestScope['dueDateTo']}" />
                    <%--<fmt:parseDate value="${dueDateTo}" var="dueDateTo" pattern="yyyy-MM-dd" />--%>
                    <%--<fmt:formatDate value="${dueDateTo}" var="dueDateTo" pattern="dd-MM-yyyy" />--%>
                    <input name="dueDateTo" id="dueDateTo" type="text" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${dueDateTo}" />
                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <div class="col-xs-1 text-right" style="width:150px;padding-right: 0px;padding-left: 0px;margin-top:-10px;">
                <label class="control-label">Status</lable>
            </div>
            <div class="col-md-2 form-group text-left" style="margin-top:-10px;">
                <select class="form-control" name="status" id="status">
                    <option  value="" >---------</option>
                    <c:forEach var="status" items="${statusList}">                                       
                        <c:set var="select" value="" />
                        <c:if test="${status.id == requestScope['status']}">
                            <c:set var="select" value="selected" />
                        </c:if>
                        <option  value="${status.id}" ${select}>${status.name}</option>
                    </c:forEach>
                </select>   
            </div>            
        </div><!-- End Row 2-->
        <div class="row" style="padding-left: 0px;">
            <div class="col-xs-1 text-right" style="width:150px;padding-right: 0px;padding-left: 0px;margin-top:-10px;">
                <label class="control-label">Invoice Sup</lable>
            </div>
            <div class="col-md-2 form-group text-left" style="margin-top:-10px;"> 
                <div class="input-group" id="gr" >
                    <input type="hidden" class="form-control" id="invSupId" name="invSupId" value="" />
                    <input type="text" class="form-control" id="invSupCode" name="invSupCode" value="${requestScope['invSupCode']}" />
                    <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchInvoiceSup">
                        <span class="glyphicon-search glyphicon"></span>
                    </span>
                </div>
            </div>
            <div class="col-md-2 form-group text-left" style="width:310px;padding-right: 0px;padding-left: 0px;margin-top:-10px;">
                <input name="invSupName" id="invSupName" type="text" class="form-control" value="${requestScope['invSupName']}" readonly=""/>
            </div>
            <div class="col-xs-1 text-right" style="width:165px;padding-right: 0px;padding-left: 0px;margin-top:-10px;">
                <label class="control-label">Ref No</lable>
            </div>
            <div class="col-md-2 form-group text-left" style="margin-top:-10px;">
                <input name="refNo" id="refNo" type="text" class="form-control" value="${requestScope['refNo']}" />
            </div>
        </div><!-- End Row 3-->
        <div class="row" style="padding-left: 775px;margin-top:-10px;">
            <div class="col-xs-1 text-left" style="width: 100px;margin-top:-10px;">
                <button type="button"  id="btnSearch"  name="btnSearch" onclick="searchPaymentOutbound()" class="btn btn-primary btn-primary ">
                    <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                </button>   
            </div>
            <div class="col-xs-1 text-left" style="width: 100px;margin-top:-10px;">
                <a id="btnPrint" name="btnPrint" onclick="printPaymentOutbound()" class="btn btn-default">
                    <i class="glyphicon glyphicon-print"></i> Print
                </a>
            </div>
        </div>
        <div class="row" style="padding-left: 15px;width: 1040px;margin-top:-10px;">
            <table class="display" id="searchPaymentTable" style="table-layout: fixed;">
                <thead class="datatable-header">
                    <tr>
                        <th style="width: 9%">Pay No.</th>
                        <th style="width: 9%">Pay Date</th>
                        <th style="width: 8%">Ref No.</th>
                        <th style="width: 17%">Invoice Sup</th>
                        <th style="width: 10%">Invoice No.</th>
                        <th style="width: 11%">Amount</th>
                        <th style="width: 5%">Cur</th>
                        <th style="width: 11%">Sale</th>
                        <th style="width: 5%">Cur</th>
                        <th style="width: 7%">Status</th>
                        <th style="width: 8%">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="paymentOutboundView" items="${paymentOutboundViewList}" varStatus="i">
                    <tr>
                        <td align="center">${paymentOutboundView.payno}</td>
                        <c:set var="paydate" value="${paymentOutboundView.paydate}" />
                        <fmt:parseDate value="${paydate}" var="paydate" pattern="yyyy-MM-dd" />
                        <fmt:formatDate value="${paydate}" var="paydate" pattern="dd-MM-yyyy" />
                        <td align="center">${paydate}</td>
                        <td align="center">${paymentOutboundView.refno}</td>
                        <td>${paymentOutboundView.invoicesup}</td>
                        <td align="center">${paymentOutboundView.invoiceno}</td>
                        <!--<td class="money" align="right">${paymentOutboundView.amount}</td>-->
                        <td align="right">${paymentOutboundView.amount}</td>
                        <td align="center">${paymentOutboundView.curamount}</td>
<!--                        <td class="money" align="right">${paymentOutboundView.sale}</td>-->
                        <td align="right">${paymentOutboundView.sale}</td>
                        <td align="center">${paymentOutboundView.cursale}</td>
                        <td align="center">
                            <c:set var="statusName" value="" />
                            <c:forEach var="status" items="${statusList}">                                                                       
                                <c:if test="${status.id == paymentOutboundView.status}">
                                    <c:set var="statusName" value="${status.name}" />
                                </c:if>
                            </c:forEach>
                            ${statusName}
                        </td>
                        <td class="text-center">
                            <c:choose>
                                <c:when test="${paymentOutboundView.paystockno != ''}">
                                    <a href="#" onclick="editPaymentStock('${paymentOutboundView.paystockno}','${paymentOutboundView.paystockid}');"  data-toggle="modal" data-target="" id="linkPayment" name="linkPayment">
                                        <span class="glyphicon glyphicon-th-list " ></span>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="#" data-toggle="modal" data-target="" id="linkPayment" name="linkPayment">
                                        <span class="glyphicon glyphicon-th-list " style="color: gray;"></span>
                                    </a>
                                </c:otherwise>
                            </c:choose>                           
                            <a href="#" onclick="editPaymentOutbound('${paymentOutboundView.paymentid}','${paymentOutboundView.payno}')"  data-toggle="modal" data-target="" id="editPayment" name="editPayment">
                                <span class="glyphicon glyphicon-edit editicon"></span>
                            </a>    
                            <a href="#" onclick="deletePaymentOutbound('${paymentOutboundView.paymentid}','${paymentOutboundView.payno}')"  data-toggle="modal" data-target="" id="deletePayment" name="deletePayment">
                                <span class="glyphicon glyphicon-remove deleteicon" ></span>
                            </a>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <input type="hidden" id="action" name="action" value="search"/>
        <input type="hidden" id="paymentId" name="paymentId" value=""/>
        </form>
    </div>
</div>
<!--Delete Payment Outbound Modal-->
<div class="modal fade" id="delSearchPaymentOutboundModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Payment Outbound</h4>
            </div>
            <div class="modal-body" id="delPaymentOutboundMessage"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="confirmDeletePaymentOutbound()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--Search Invoice Sup-->
<div class="modal fade" id="SearchInvoiceSup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Invoice Supplier</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="searchInvoicSupTable">
                    <thead class="datatable-header">
                        <script>
                            var invoiceSup = [];
                        </script>
                        <tr>
                            <th class="hidden">Id</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th>AP code</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="invSup" items="${invSupList}">
                            <tr onclick ="setupInvSupValue('${invSup.id}', '${invSup.code}', '${invSup.name}', '${invSup.apcode}')" >
                                <td class="hidden">${invSup.id}</td>
                                <td>${invSup.code}</td>
                                <td>${invSup.name}</td>
                                <td>${invSup.apcode}</td> 
                            </tr>
                            <script>
                                invoiceSup.push({id: "${invSup.id}", code: "${invSup.code}", name: "${invSup.name}", apcode: "${invSup.apcode}"});
                            </script>
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="SearchInvoiceSupOK" name="SearchInvoiceSupOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchInvoiceSupClose" name="SearchInvoiceSupClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->
<!--Payment Stock Modal-->
<div class="modal fade " id="paymentStockModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Pay Stock</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="paymentStockTable" style="table-layout: fixed;">
                    <thead class="datatable-header">                      
                        <tr>
                            <th style="width: 70%;">Pay Stock </th>
                            <th style="width: 30%;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
    
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">              
                <button type="button" class="btn btn-default" data-dismiss="modal" >Cancel</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      
<script type="text/javascript">
$(document).ready(function () {
    $('.date').datetimepicker();
    $('.datemask').mask('00-00-0000');
    $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
    $(".money").mask('000,000,000,000.00', {reverse: true});
    
    var table = $('#searchPaymentTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": true,
        "iDisplayLength": 10
    });
    
    $('#searchInvoicSupTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false
    });
     $(".decimal").inputmask({
            alias: "decimal",
            integerDigits: 8,
            groupSeparator: ',',
            autoGroup: true,
            digits: 2,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0.00",
        });
    var codeInvoiceSup = [];
    $.each(invoiceSup, function (key, value) {
        codeInvoiceSup.push(value.code);
        if ( !(value.name in codeInvoiceSup) ){
           codeInvoiceSup.push(value.name);        
        }
    });
        
    $("#invSupCode").autocomplete({
        source: codeInvoiceSup,
        close:function( event, ui ) {
            $("#invSupCode").trigger('keyup');
        }
    });
        
    $("#invSupCode").on('keyup', function () {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value;
        $("#invSupId,#invSupName").val(null);

        $.each(invoiceSup, function (key, value) {
            if (value.code.toUpperCase() === code) {           
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
            }
            if(name === value.name){
                $("#invSupCode").val(value.code);
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
                code = $("#invSupCode").val().toUpperCase();
            }
        });  
    });
        
    $("#invSupCode").on('blur', function () {
        var delay=500;//1 seconds
        setTimeout(function(){
            $.each(invoiceSup, function (key, value) {
                if($("#invSupCode").val() === value.code){
                    $("#invSupId").val(value.id);
                    $("#invSupName").val(value.name);
                }     
            });   
        },delay); 
    });
    
    $('#searchPaymentTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
            $('#hdGridSelected').val('');
        }else{
            table.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
            $('#hdGridSelected').val($('#searchPaymentTable tbody tr.row_selected').attr("id"));
        }
    });
        
    //validate date
//    $('#fromDateDiv').datetimepicker().on('dp.change', function (e) {
//        $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'fromDate');
//    });
//    $('#toDateDiv').datetimepicker().on('dp.change', function (e) {
//        $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'toDate');
//    });

//    $("#searchPaymentOutboundForm").bootstrapValidator({
//        framework: 'bootstrap',
//    //  container: 'tooltip',
//        feedbackIcons: {
//            valid: 'uk-icon-check',
//            invalid: 'uk-icon-times',
//            validating: 'uk-icon-refresh'
//        },
//        fields: {
//            fromDate: {
//                trigger: 'focus keyup change',
//                validators: {
//                    notEmpty: {
//                        message: 'The Date From is required'
//                    },
//                    date: {
//                        format: 'DD-MM-YYYY',
//                        max: 'toDate',
//                        message: 'The Date From is not a valid'
//                    }
//                }
//            },
//            toDate: {
//                trigger: 'focus keyup change',
//                validators: {
//                    notEmpty: {
//                        message: 'The Date From is required'
//                    },
//                    date: {
//                        format: 'DD-MM-YYYY',
//                        min: 'fromDate',
//                        message: 'The Date To is not a valid'
//                    }
//                }
//            }
//        }
//    }).on('success.field.fv', function (e, data) {
//        if (data.field === 'fromDate' && data.fv.isValidField('toDate') === false) {
//            data.fv.revalidateField('toDate');
//        }
//        if (data.field === 'toDate' && data.fv.isValidField('fromDate') === false) {
//            data.fv.revalidateField('fromDate');
//        }
//    });
    
//    $('.fromdate').datetimepicker().change(function(){                          
//        checkFromDateField();
//    });
//    $('.todate').datetimepicker().change(function(){                          
//        checkToDateField();
//    });
     $('.fromdate').datetimepicker().change(function(){                          
            checkFromDateField();
        });
        $('.todate').datetimepicker().change(function(){                          
            checkToDateField();
        });
        $('.duefromdate').datetimepicker().change(function(){                          
            checkDueDateFromField();
        });
        $('.duetodate').datetimepicker().change(function(){                          
            checkDueDateToField();
        });
        
    var result = $('#result').val();
    if (result === "success") {
        $('#textAlertDivDelete').show();
    } else if (result === 'fail') {
        $('#textAlertDivNotDelete').show();
    }
    
});

    function setupInvSupValue(id,code,name,apcode){
        $('#SearchInvoiceSup').modal('hide');
        document.getElementById('invSupId').value = id;
        document.getElementById('invSupCode').value = code;
        document.getElementById('invSupName').value = name;
        document.getElementById('invSupCode').focus();
//        $('#PaymentTourHotelForm').bootstrapValidator('revalidateField', 'InputInvoiceSupCode');
//        $('#PaymentTourHotelForm').bootstrapValidator('revalidateField', 'InputAPCode');
    }

    function printPaymentOutbound(){
        var fromdate = convertFormatDate(document.getElementById("fromDate").value);
        var todate= convertFormatDate(document.getElementById("toDate").value);
        var status = document.getElementById("status").value;
        var invSupCode= document.getElementById("invSupCode").value;
        var refNo= document.getElementById("refNo").value;
        
        if((fromdate === '') || (todate === '')){
           validateDate();
        } else {
            window.open("report.smi?name=PaymentOutboundSummaryReport&fromdate="+fromdate+"&todate="+todate+"&status="+status+"&invSupCode="+invSupCode+"&refno="+refNo);
        }    
    }
    
//    function checkFromDateField(){
//        var inputFromDate = document.getElementById("fromDate");
//        if(inputFromDate.value === ''){          
//            $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'fromDate');
//            $("#btnPrint").addClass("disabled");         
//        } else {
//            $("#btnPrint").removeClass("disabled");
//            checkDateValue("from","");
//        }      
//    }
//    
//    function checkToDateField(){
//        var InputToDate = document.getElementById("toDate");
//        if(InputToDate.value === ''){
//            $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'toDate');
//            $("#btnPrint").addClass("disabled");  
//        }else{
//            $("#btnPrint").removeClass("disabled");
//            checkDateValue("to","");
//        }               
//    }
    
//    function checkDateValue(date){
//        var inputFromDate = document.getElementById("fromDate");
//        var InputToDate = document.getElementById("toDate");
//        if((inputFromDate.value !== '') && (InputToDate.value !== '')){
//            var fromDate = (inputFromDate.value).split('-');
//            var toDate = (InputToDate.value).split('-');
//            if((parseInt(fromDate[2])) > (parseInt(toDate[2]))){
//                validateDate(date,"over");
//            }else if(((parseInt(fromDate[2])) >= (parseInt(toDate[2]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))){
//                validateDate(date,"over");
//            }else if(((parseInt(fromDate[2])) >= (parseInt(toDate[2]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[0])) > (parseInt(toDate[0]))){
//                validateDate(date,"over");
//            }else{
//                $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'fromDate');
//                $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'toDate');
//            }          
//        }
//    }
    
//    function validateDate(date,option){
//        if(option === 'over'){
//            if(date === 'from'){
//                $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'fromDate');
//                $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'toDate');
//            }
//            if(date === 'to'){
//                $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'fromDate');
//                $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'toDate');
//            }           
//            $("#btnPrint").addClass("disabled");
//        } else {
//            $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'fromDate');
//            $('#searchPaymentOutboundForm').bootstrapValidator('revalidateField', 'toDate');
//            $("#btnPrint").addClass("disabled");
//        }
//    }
//    
    function editPaymentOutbound(paymentId,payNo){
        window.location = ("PaymentOutbound.smi?action=edit&payId="+paymentId+"&payNo="+payNo);
    }
    
    function deletePaymentOutbound(paymentId,payNo){
        $("#paymentId").val(paymentId);
        $("#delPaymentOutboundMessage").text("Are you sure to delete payment "+payNo+" ?");
        $("#delSearchPaymentOutboundModal").modal("show");
    }
    
    function confirmDeletePaymentOutbound(){
        $("#delSearchPaymentOutboundModal").modal("hide");
        $("#action").val("deletePaymentOutbound");
        document.getElementById("searchPaymentOutboundForm").submit();
        
    }
    
    function editPaymentStock(payStockNo,payStockId){
        $('#paymentStockTable > tbody  > tr').each(function() {
            $(this).remove();
        });
        var payStockNoList = payStockNo.split(",");
        var payStockIdList = payStockId.split(",");
        var table = '';
//        alert("pay no : "+payStockNoList[0]+" , "+payStockNoList[1]);
//        alert("pay id : "+payStockIdList[0]+" , "+payStockIdList[1]);
        if(payStockNoList.length > 1){         
            for(var i=0; i<payStockNoList.length; i++){
                var payNo = payStockNoList[i];
                if(payNo !== ''){
                    table += '<tr >' +
                                '<td>'+payNo+'</td>' +          
                                '<td class="text-center">' +
                                    '<a href="PaymentStock.smi?action=searchPayNo&payNo=' + payNo + '" target="_blank">' +
                                        '<span class="glyphicon glyphicon-th-list " ></span>' +
                                    '</a>' +
                                '</td>' +
                            '</tr>';
                }            
            }

            $("#paymentStockTable tbody").append(table);
            $("#paymentStockModal").modal("show");
        
        }else{
            window.open('PaymentStock.smi?action=searchPayNo&payNo='+payStockNoList[0]);
        }
    }
    
    function checkFromDateField(){
        var InputToDate = document.getElementById("toDate");
        var inputFromDate = document.getElementById("fromDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#btnSearch").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){        
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success");
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#btnSearch").addClass("disabled");
        } else {
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#duefromdatepanel").removeClass("has-error");
            $("#duetodatepanel").removeClass("has-error");
            $("#fromdatepanel").addClass("has-success");
            $("#todatepanel").addClass("has-success");
            $("#btnSearch").removeClass("disabled");
            checkDateValue("from","");
        }
    }
    
    function checkToDateField(){
        var InputToDate = document.getElementById("toDate");
        var inputFromDate = document.getElementById("fromDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#btnSearch").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){ 
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success");
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#btnSearch").addClass("disabled");
        }else{
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#duefromdatepanel").removeClass("has-error");
            $("#duetodatepanel").removeClass("has-error");
            $("#fromdatepanel").addClass("has-success");
            $("#todatepanel").addClass("has-success");
            $("#btnSearch").removeClass("disabled");
            checkDateValue("to","");
        }       
    }
    
    function checkDueDateFromField(){
        var dueToDate = document.getElementById("dueDateTo");
        var dueToFrom = document.getElementById("dueDateFrom");
        if(dueToFrom.value === '' && dueToDate.value === ''){
            $("#duefromdatepanel").removeClass("has-error");
            $("#duetodatepanel").removeClass("has-error");
            $("#btnSearch").removeClass("disabled");
        }else if(dueToFrom.value === '' || dueToDate.value === ''){        
            $("#duefromdatepanel").removeClass("has-success");
            $("#duetodatepanel").removeClass("has-success");  
            $("#duefromdatepanel").addClass("has-error");
            $("#duetodatepanel").addClass("has-error");  
            $("#btnSearch").addClass("disabled");
        } else {
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#duefromdatepanel").removeClass("has-error");
            $("#duetodatepanel").removeClass("has-error");
            $("#duefromdatepanel").addClass("has-success");
            $("#duetodatepanel").addClass("has-success");
            $("#btnSearch").removeClass("disabled");
            checkDateValue("duefrom","");
        }    
    }
    
    function checkDueDateToField(){
        var dueToDate = document.getElementById("dueDateTo");
        var dueToFrom = document.getElementById("dueDateFrom");
        if(dueToFrom.value === '' && dueToDate.value === ''){
            $("#duefromdatepanel").removeClass("has-error");
            $("#duetodatepanel").removeClass("has-error");
            $("#btnSearch").removeClass("disabled");
        }else if(dueToFrom.value === '' || dueToDate.value === ''){ 
            $("#duefromdatepanel").removeClass("has-success");
            $("#duetodatepanel").removeClass("has-success");  
            $("#duefromdatepanel").addClass("has-error");
            $("#duetodatepanel").addClass("has-error"); 
            $("#btnSearch").addClass("disabled");
        }else{
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#duefromdatepanel").removeClass("has-error");
            $("#duetodatepanel").removeClass("has-error");
            $("#duefromdatepanel").addClass("has-success");
            $("#duetodatepanel").addClass("has-success");
            $("#btnSearch").removeClass("disabled");
            checkDateValue("dueto","");
        }       
    }
    
    function checkDateValue(date){
        var inputFromDate = "";
        var InputToDate = "";
        if((date === 'from') || (date === 'to')){
            inputFromDate = document.getElementById("fromDate");
            InputToDate = document.getElementById("toDate");
        } else {
            inputFromDate = document.getElementById("dueDateFrom");
            InputToDate = document.getElementById("dueDateTo");
        }
        
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
    
    function validateDate(date,option){
        if(option === 'over'){
            if(date === 'from'){
                $("#fromdatepanel").removeClass("has-success");
                $("#fromdatepanel").addClass("has-error");                                 
            }
            if(date === 'to'){
                $("#todatepanel").removeClass("has-success");
                $("#todatepanel").addClass("has-error");
            }
            if(date === 'duefrom'){
                $("#duefromdatepanel").removeClass("has-success");
                $("#duefromdatepanel").addClass("has-error");
            }
            if(date === 'dueto'){
                $("#duetodatepanel").removeClass("has-success"); 
                $("#duetodatepanel").addClass("has-error");
            }       
            $("#btnSearch").addClass("disabled");
        } else {
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success");
            $("#duefromdatepanel").removeClass("has-success");
            $("#duetodatepanel").removeClass("has-success"); 
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
//            $("#duefromdatepanel").addClass("has-error");
//            $("#duetodatepanel").addClass("has-error");
            $("#btnSearch").addClass("disabled");
        }
    }
    
    function searchPaymentOutbound(){
        var fromDate = document.getElementById('fromDate').value;
        var toDate = document.getElementById('toDate').value;
        var dueDateFrom = document.getElementById('dueDateFrom').value;
        var dueDateTo = document.getElementById('dueDateTo').value;
        var payno = document.getElementById('payNo').value;
        if(payno !== ''){
                $("#searchPaymentOutboundForm").submit();
        }else{
            if((fromDate !== '') && (toDate !== '')){
                $("#searchPaymentOutboundForm").submit();
            }else{
                validateDate();  
            }
        }
    }



</script>
