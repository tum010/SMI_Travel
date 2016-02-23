<%-- 
    Document   : BookingSummary
    Created on : Feb 23, 2016, 2:58:36 PM
    Author     : Jittima
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="invSupList" value="${requestScope['invSupList']}" />
<c:set var="userList" value="${requestScope['staffList']}" />
<c:set var="productList" value="${requestScope['productList']}" />

<section class="content-header"  >
    <h4>
        <b>Report : Checking Outbound report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report </a></li>          
        <li class="active"><a href="#">Booking Summary</a></li>
    </ol>
</section>

<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/CheckingOutboundMenu.html'"></div>
            </div>

            <div class="form-group">
                <div class="col-md-6">
                    <h3>Booking Summary</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="BookingSummaryFrom" name="BookingSummaryFrom" method="post" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Type</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectPrintReportType" id="SelectPrintReportType" class="form-control" onchange="selectPrintReport()">
                                            <!--<option value=""  selected="selected">-- ALL --</option>-->
                                            <option value="1" >Invoice</option>
                                            <option value="2" >Non Invoice</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Owner</label>
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
                                    <input type="text" class="form-control" id="salebyName" name="salebyName" value="" readonly="" style="width: 132px">
                                </div>
                            </div>   
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right">Booking Date From</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date bookfromdate' id='bookfromdatepanel'>                    
                                            <input id="bookFromDate" name="bookFromDate"  type="text" 
                                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Booking Date To</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date booktodate' id='booktodatepanel'>                    
                                            <input id="bookToDate" name="bookToDate"  type="text" 
                                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>    
                    <div class="row hidden active" id="booknoninvoice">                     
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="col-md-5 control-label text-right" >Invoice Sup</label>
                                    <div class="col-md-3 form-group">  
                                        <div class="input-group">
                                            <input type="hidden" class="form-control" id="invSupId" name="invSupId" value=""/>
                                            <input type="text" class="form-control" id="invSupCode" name="invSupCode" value="" />
                                            <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchInvoiceSup">
                                                <span class="glyphicon-search glyphicon"></span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <input type="text" class="form-control" id="invSupName" name="invSupName" value="" readonly="" style="width: 132px">
                                    </div>
                                </div>   
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="col-md-5 control-label text-right">Pay Date From</label>
                                    <div class="col-md-5">  
                                        <div class="form-group">
                                            <div class='input-group date payfromdate' id='payfromdatepanel'>                    
                                                <input id="payFromDate" name="payFromDate"  type="text" 
                                                    class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                            </div>
                                        </div>
                                    </div>   
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="col-md-5 control-label text-right" >Pay Date To</label>
                                    <div class="col-md-5">  
                                        <div class="form-group">
                                            <div class='input-group date paytodate' id='paytodatepanel'>                    
                                                <input id="payToDate" name="payToDate"  type="text" 
                                                    class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                            </div>
                                        </div>
                                    </div>   
                                </div>
                            </div>
                        </div>     
                    </div>      
                    <div class="row hidden active" id="bookinvoice">                    
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="col-md-5 control-label text-right" >Inv To</label>
                                    <div class="col-md-3 form-group">  
                                        <div class="input-group">
                                            <input type='text' id="billto" name="billto"  class="form-control" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-search" data-toggle="modal" data-target="#BillToModal"></span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <input type='text' readonly="" id="billname" name="billname" class="form-control" />
                                    </div>
                                </div>   
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="col-md-5 control-label text-right">Invoice Date From</label>
                                    <div class="col-md-5">  
                                        <div class="form-group">
                                            <div class='input-group date invfromdate' id='invfromdatepanel'>                    
                                                <input id="invFromDate" name="invFromDate"  type="text" 
                                                    class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                            </div>
                                        </div>
                                    </div>   
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="col-md-5 control-label text-right" >Invoice Date To</label>
                                    <div class="col-md-5">  
                                        <div class="form-group">
                                            <div class='input-group date invtodate' id='invtodatepanel'>                    
                                                <input id="invToDate" name="invToDate"  type="text" 
                                                    class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                            </div>
                                        </div>
                                    </div>   
                                </div>
                            </div>
                        </div>     
                    </div>                    
                    
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right"></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <button type="button"  class="btn btn-success" id="printbutton"  name="printbutton" onclick="printBookingSummary();"><span class="glyphicon glyphicon-print" ></span> Print</button>
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

<div class="modal fade" id="SearchInvoiceSup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Invoice Supplier</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="SearchInvoicSupTable">
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

<div class="modal fade" id="BillToModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Bill To</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->
                <div style="text-align: right"> <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchBillTo" name="searchBillTo" placeholder="Bill To,Name"/> </div> 
                <table class="display" id="BillToTable" style="table-layout: fixed;">
                    <thead>                        
                        <tr class="datatable-header">
                            <th style="width: 15%;">Bill To</th>
                            <th style="width: 30%;">Bill Name</th>
                            <th style="width: 35%;">Address</th>
                            <th style="width: 20%;">Tel</th>
                        </tr>
                    </thead>
                    <script>
                        bill = [];
                    </script>
                    <tbody>
                        <c:forEach var="item" items="${customerList}">
                            <tr onclick="setBillValue('${item.billTo}', '${item.billName}', '${item.address}', '${item.term}', '${item.pay}');">                             
                                <td class="item-billto">${item.billTo}</td>
                                <td class="item-name">${item.billName}</td>                                
                                <td class="item-address ">${item.address}</td>
                                <td class="item-tel ">${item.tel}</td>
                            </tr>
                        <script>
                            bill.push({code: "${item.billTo}", name: "${item.billName}", address: "${item.address}" , tel: "${item.tel}"})
                        </script>
                        </c:forEach>
                            
                    </tbody>

                </table>
                <!--Script Bill To List Table-->
                <script>
                    $(document).ready(function () {
                                                // BillTo Table
                        var BillToTable = $('#BillToTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                        var billTo = [];
                        $.each(bill, function (key, value) {
                            billTo.push(value.code);
                            if ( !(value.name in billTo) ){
                               billTo.push(value.name);
                            }
                        });
                                        //autocomplete
                        $("#billto").keyup(function (event) {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            if ($(this).val() === "") {
                                $("#billto").val("");
                                $("#billname").val("");
                            } else {
                                if (event.keyCode === 13) {
                                    searchCustomerAutoList(this.value);
                                }
                            }
                        });

                        var showflag = 1;
                        $("#billto").keydown(function () {

                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            if (showflag == 0) {
                                $(".ui-widget").css("top", -1000);
                                showflag = 1;
                            }
                        });

                        $("#BillToTable tr").on('click', function () {
                            var billto = $(this).find(".item-billto").text();
                            var billname = $(this).find(".item-name").text();
                            var address = $(this).find(".item-address").text();
                            var tel = $(this).find(".item-tel").text();
                            $("#billto").val(billto);
                            $("#billname").val(billname);
                            $("#address").val(address);
                            $("#BillToModal").modal('hide');
                        });


                        $('#BillToTable tbody').on('click', 'tr', function () {
                            $('.collapse').collapse('show');
                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                            }
                            else {
                                BillToTable.$('tr.row_selected').removeClass('row_selected');
                                $(this).addClass('row_selected');
                            }
                        });
                        
                    });

                </script>

            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
    
    $('.date').datetimepicker();
    $('.datemask').mask('0000-00-00', {reverse: true});
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });

    $("#searchBillTo").keyup(function(event) {
        if (event.keyCode === 13) {
            searchBillTo($("#searchBillTo").val());             
        }
    });
    
    
    var type = $("#SelectPrintReportType").val();
    if(type == 1){
        $("#booknoninvoice").addClass('hidden');
        $("#bookinvoice").removeClass('hidden');
    }else{
        $("#booknoninvoice").removeClass('hidden');
        $("#bookinvoice").addClass('hidden');
    }

    $("#printbutton").addClass("disabled");
         
    $('.bookfromdate').datetimepicker().change(function(){                          
        checkBookFromDateField();
    });
    $('.booktodate').datetimepicker().change(function(){                          
        checkBookToDateField();
    });
    $('.payfromdate').datetimepicker().change(function(){                          
        checkPayFromDateField();
    });
    $('.paytodate').datetimepicker().change(function(){                          
        checkPayToDateField();
    });
    $('.invfromdate').datetimepicker().change(function(){                          
        checkInvFromDateField();
    });
    $('.invtodate').datetimepicker().change(function(){                          
        checkInvToDateField();
    });


    var codeInvoiceSup = [];
    $.each(invoiceSup, function(key, value) {
        codeInvoiceSup.push(value.code);
        if (!(value.name in codeInvoiceSup)) {
            codeInvoiceSup.push(value.name);
        }
    });

    $("#invSupCode").autocomplete({
        source: codeInvoiceSup,
        close: function(event, ui) {
            $("#invSupCode").trigger('keyup');
        }
    });

    $("#invSupCode").on('keyup', function() {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value;
        $("#invSupId,#invSupName,#invSupApCode").val(null);
        $.each(invoiceSup, function(key, value) {
            if (value.code.toUpperCase() === code) {
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
                $("#invSupApCode").val(value.apcode);
            }
            if (name === value.name) {
                $("#invSupCode").val(value.code);
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
                $("#invSupApCode").val(value.apcode);
                code = $("#invSupCode").val().toUpperCase();
            }
        });
    });

    $("#invSupCode").on('blur', function() {
        var delay = 500;//1 seconds
        setTimeout(function() {
            $.each(invoiceSup, function(key, value) {
                //alert(value.code);
                if ($("#invSupCode").val() === value.code) {
                    $("#invSupId").val(value.id);
                    $("#invSupName").val(value.name);
                    $("#invSupApCode").val(value.apcode);
                }
            });
        }, delay);
    });
    
    $('#SearchInvoicSupTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false
    });
    
    //Sale By Auto Complete
    $("#SaleByTable tr").on('click', function() {
        var saleby_id = $(this).find(".saleby-id").text();
        var saleby_user = $(this).find(".saleby-user").text();
        var saleby_name = $(this).find(".saleby-name").text();
        $("#salebyId").val(saleby_id);
        $("#salebyUser").val(saleby_user);
        $("#salebyName").val(saleby_name);
        $("#SaleByModal").modal('hide');
    });

    var salebyuser = [];
    $.each(saleby, function(key, value) {
        salebyuser.push(value.username);
        salebyuser.push(value.name);
    });

    $("#salebyUser").autocomplete({
        source: salebyuser,
        close: function(event, ui) {
            $("#salebyUser").trigger('keyup');
        }
    });

    $("#salebyUser").on('keyup', function() {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var username = this.value.toUpperCase();
        var name = this.value.toUpperCase();
        // console.log("Name :"+ name);
        $("#salebyId,#salebyName").val(null);
        $.each(saleby, function(key, value) {
            if (value.username.toUpperCase() === username) {
                $("#salebyId").val(value.id);
                $("#salebyUser").val(value.username);
                $("#salebyName").val(value.name);
            }
            else if (value.name.toUpperCase() === name) {
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

    $('#SaleByTable tbody').on('click', 'tr', function() {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
});

function setBillValue(billto, billname, address, term, pay) {

       $("#billto").val(billto);
       $("#billname").val(billname);
       $("#BillToModal").modal('hide');
}
   
function searchCustomerAutoList(name) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getAutoListBillto';

    var url = 'AJAXServlet';
    var billArray = [];
    var billListId = [];
    var billListName = [];
    var billListAddress = [];
    var billid, billname, billaddr;
    $("#billto").autocomplete("destroy");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            beforeSend: function () {
                $("#dataload").removeClass("hidden");
            },
            success: function (msg) {
                var billJson = JSON.parse(msg);
                var billselect = $("#billto").val();
                for (var i in billJson) {
                    if (billJson.hasOwnProperty(i)) {
                        billid = billJson[i].id;
                        billname = billJson[i].name;
                        billaddr = billJson[i].address;
                        billArray.push(billid);
                        billArray.push(billname);
                        billListId.push(billid);
                        billListName.push(billname);
                        billListAddress.push(billaddr);
                        if ((billselect === billid) || (billselect === billname)) {
                            $("#billto").val(billListId[i]);
                            $("#billname").val(billListName[i]);
                        }
                    }
                    $("#dataload").addClass("hidden");
                }
                // $("#refundBy").val(billid);
                //$("#refundByName").val(billname);

                $("#billto").autocomplete({
                    source: billArray,
                    close: function () {
                        $("#billto").trigger("keyup");
                        var billselect = $("#billto").val();
                        for (var i = 0; i < billListId.length; i++) {
                            if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                                $("#billto").val(billListId[i]);
                                $("#billname").val(billListName[i]);
                            }
                        }
                    }
                });

                var billval = $("#billto").val();
                for (var i = 0; i < billListId.length; i++) {
                    if (billval == billListName[i]) {
                        $("#billto").val(billListId[i]);
                    }
                }
                if (billListId.length == 1) {
                    showflag = 0;
                    $("#billto").val(billListId[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $("#billto").trigger(event);

            }, error: function (msg) {
                console.log('auto ERROR');
                $("#dataload").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

function searchBillTo(name){
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getListBilltoOverdueSummary';
    callAjax(param);
}

function callAjax(param){
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                $('#BillToTable').dataTable().fnClearTable();
                $('#BillToTable').dataTable().fnDestroy();
                $("#BillToTable tbody").empty().append(msg);

                $('#BillToTable').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bPaginate": true,
                    "bInfo": false,
                    "bLengthChange": false,
                    "iDisplayLength": 10
                });
                $("#ajaxload").addClass("hidden");

            }, error: function(msg) {
                $("#ajaxload").addClass("hidden");
                alert('error');
            }
        });
    } catch (e) {
        $("#ajaxload").addClass("hidden");
        alert(e);
    }
}

function selectPrintReport(){
    var type = $("#SelectPrintReportType").val();
    if(type == 1){
        $("#booknoninvoice").addClass('hidden');
        $("#bookinvoice").removeClass('hidden');
        $("#payFromDate").val("");
        $("#payToDate").val("");
    }else{
        $("#booknoninvoice").removeClass('hidden');
        $("#bookinvoice").addClass('hidden');
        $("#invFromDate").val("");
        $("#invToDate").val("");
    }
}


function checkBookFromDateField(){
    var inputFromDate = document.getElementById("bookFromDate");
    var InputToDate = document.getElementById("bookToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#bookfromdatepanel").removeClass("has-error");
        $("#booktodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#bookfromdatepanel").removeClass("has-success");
        $("#booktodatepanel").removeClass("has-success");
        $("#bookfromdatepanel").addClass("has-error");
        $("#booktodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#bookfromdatepanel").removeClass("has-error");
        $("#booktodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#bookfromdatepanel").addClass("has-success");
        $("#booktodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("bookfrom","");
    }
}
    
function checkBookToDateField(){
    var inputFromDate = document.getElementById("bookFromDate");
    var InputToDate = document.getElementById("bookToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#bookfromdatepanel").removeClass("has-error");
        $("#booktodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#bookfromdatepanel").removeClass("has-success");
        $("#booktodatepanel").removeClass("has-success");
        $("#bookfromdatepanel").addClass("has-error");
        $("#booktodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#bookfromdatepanel").removeClass("has-error");
        $("#booktodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#bookfromdatepanel").addClass("has-success");
        $("#booktodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("bookto","");
    }       
}

// Pay Date
function checkPayFromDateField(){
    var inputFromDate = document.getElementById("payFromDate");
    var InputToDate = document.getElementById("payToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#payfromdatepanel").removeClass("has-success");
        $("#paytodatepanel").removeClass("has-success");
        $("#payfromdatepanel").addClass("has-error");
        $("#paytodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#bookfromdatepanel").removeClass("has-error");
        $("#booktodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#payfromdatepanel").addClass("has-success");
        $("#paytodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("payfrom","");
    }
}
    
function checkPayToDateField(){
    var inputFromDate = document.getElementById("payFromDate");
    var InputToDate = document.getElementById("payToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#payfromdatepanel").removeClass("has-success");
        $("#paytodatepanel").removeClass("has-success");
        $("#payfromdatepanel").addClass("has-error");
        $("#paytodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#bookfromdatepanel").removeClass("has-error");
        $("#booktodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#payfromdatepanel").addClass("has-success");
        $("#paytodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("payto","");
    }    
}
//Inv Date
function checkInvFromDateField(){
    var inputFromDate = document.getElementById("invFromDate");
    var InputToDate = document.getElementById("invToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#invfromdatepanel").removeClass("has-success");
        $("#invtodatepanel").removeClass("has-success");
        $("#invfromdatepanel").addClass("has-error");
        $("#invtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#bookfromdatepanel").removeClass("has-error");
        $("#booktodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#invfromdatepanel").addClass("has-success");
        $("#invtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("invfrom","");
    }
}
    
function checkInvToDateField(){
    var inputFromDate = document.getElementById("invFromDate");
    var InputToDate = document.getElementById("invToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#invfromdatepanel").removeClass("has-success");
        $("#invtodatepanel").removeClass("has-success");
        $("#invfromdatepanel").addClass("has-error");
        $("#invtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#bookfromdatepanel").removeClass("has-error");
        $("#booktodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#invfromdatepanel").addClass("has-success");
        $("#invtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("invto","");
    }     
}

function checkDateValue(date){
    var inputFromDate = "";
        var InputToDate = "";
        if((date === 'bookfrom') || (date === 'bookto')){
            inputFromDate = document.getElementById("bookFromDate");
            InputToDate = document.getElementById("bookToDate");
        } else if((date === 'payfrom') || (date === 'payto')){
            inputFromDate = document.getElementById("payFromDate");
            InputToDate = document.getElementById("payToDate");
        } else {
            inputFromDate = document.getElementById("invFromDate");
            InputToDate = document.getElementById("invToDate");
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
        if(date === 'bookfrom'){
            $("#bookfromdatepanel").removeClass("has-success");
            $("#bookfromdatepanel").addClass("has-error");                                 
        }
        if(date === 'bookto'){
            $("#booktodatepanel").removeClass("has-success");
            $("#booktodatepanel").addClass("has-error");
        }
        if(date === 'payfrom'){
            $("#payfromdatepanel").removeClass("has-success");
            $("#payfromdatepanel").addClass("has-error");                                 
        }
        if(date === 'payto'){
            $("#paytodatepanel").removeClass("has-success");
            $("#paytodatepanel").addClass("has-error");
        }
        if(date === 'invfrom'){
            $("#invfromdatepanel").removeClass("has-success");
            $("#invfromdatepanel").addClass("has-error");                                 
        }
        if(date === 'invto'){
            $("#invtodatepanel").removeClass("has-success");
            $("#invtodatepanel").addClass("has-error");
        }
        $("#printbutton").addClass("disabled");
    } else {
        $("#bookfromdatepanel").removeClass("has-success");
        $("#booktodatepanel").removeClass("has-success");
        $("#payfromdatepanel").removeClass("has-success");
        $("#paytodatepanel").removeClass("has-success");
        $("#invfromdatepanel").removeClass("has-success");
        $("#invtodatepanel").removeClass("has-success");
        $("#bookfromdatepanel").addClass("has-error");
        $("#booktodatepanel").addClass("has-error");
        $("#payfromdatepanel").addClass("has-error");
        $("#paytodatepanel").addClass("has-error");
        $("#invfromdatepanel").addClass("has-error");
        $("#invtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    }
}


function printBookingSummary(){
    var reportType = document.getElementById("SelectPrintReportType").value;
    var owner = document.getElementById("salebyUser").value;
    var bookFromDate = document.getElementById("bookFromDate").value;
    var bookToDate = document.getElementById("bookToDate").value;
    var invSupCode = document.getElementById("invSupCode").value;
    var invSupName = document.getElementById("invSupName").value;
    var payFromDate = document.getElementById("payFromDate").value;
    var payToDate = document.getElementById("payToDate").value;
    var billto = document.getElementById("billto").value;
    var billname = document.getElementById("billname").value;
    var invFromDate = document.getElementById("invFromDate").value;
    var invToDate = document.getElementById("invToDate").value;
    
    if(((bookFromDate !== '') && (bookToDate !== '')) 
        || ((payFromDate !== '') && (payToDate !== '')) 
        || ((invFromDate !== '') && (invToDate !== ''))) {
        if(reportType == 1){
            window.open("Excel.smi?name=BookingInvoiceSummary&owner=" + owner + 
                "&invto=" + billto + 
                "&bookdatefrom=" + bookFromDate + 
                "&bookdateto=" + bookToDate+ 
                "&invdatefrom=" + invFromDate +""+
                "&invdateto=" + invToDate+"");
        }else if(reportType == 2){
            window.open("Excel.smi?name=BookingNonInvoiceSummary&owner=" + owner +
                "&invsup=" + invSupCode +
                "&bookdatefrom=" + bookFromDate + 
                "&bookdateto=" + bookToDate +
                "&paydatefrom=" + payFromDate +
                "&paydateto=" + payToDate);
        }
    } else {
        validateDate();  
    }
 
}
</script>