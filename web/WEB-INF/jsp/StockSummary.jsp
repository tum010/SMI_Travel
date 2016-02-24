<%-- 
    Document   : StockSummary
    Created on : Feb 23, 2016, 3:02:19 PM
    Author     : chonnasith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="productList" value="${requestScope['productList']}" />
<c:set var="customerAgentList" value="${requestScope['customerAgentList']}" />
<c:set var="invSupList" value="${requestScope['invSupList']}" />

<!DOCTYPE html>
<section class="content-header"  >
    <h4>
        <b>Report : Checking Outbound report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">OverDue Summary</a></li>
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
                    <h3>Stock Summary</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="stockSummaryForm" name="stockSummaryForm" method="post" class="form-horizontal" >
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Type</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="reportType" id="reportType" class="form-control">
                                            <option value="1" selected="">Invoice</option>
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
                                <label class="col-md-5 control-label text-right" >Product</label>
                                <div class="col-md-3 form-group">  
                                    <div class="input-group">
                                        <input type='hidden' id="productId" name="productId"  class="form-control" />
                                        <input type='text' id="productCode" name="productCode"  class="form-control" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-search" data-toggle="modal" data-target="#productModal"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-1" style="width: 250px;">
                                    <input type='text' readonly="" id="productName" name="productName" class="form-control" />
                                </div>
                            </div>   
                        </div>
                    </div>                                     
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Effective Date From<font style="color:red">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date effectivefromdate' id='effectivefromdatepanel'>                    
                                            <input id="effectiveFromDate" name="effectiveFromDate"  type="text" 
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
                                <label class="col-md-5 control-label text-right" >Effective Date To<font style="color:red">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date effectivetodate' id='effectivetodatepanel'>                    
                                            <input id="effectiveToDate" name="effectiveToDate"  type="text" 
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
                                <label class="col-md-5 control-label text-right" >Add Date<font style="color:red">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date adddate' id='adddatepanel'>                    
                                            <input id="addDate" name="addDate"  type="text" 
                                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div id="invoice">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="col-md-5 control-label text-right" >Inv To</label>
                                    <div class="col-md-3 form-group">  
                                        <div class="input-group">
                                            <input type='text' id="billTo" name="billTo"  class="form-control" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-search" data-toggle="modal" data-target="#billToModal"></span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-md-1" style="width: 250px;">
                                        <input type='text' readonly="" id="billName" name="billName" class="form-control" />
                                    </div>
                                </div>   
                            </div>
                        </div>                                     
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="col-md-5 control-label text-right" >Invoice Date From<font style="color:red">*</font></label>
                                    <div class="col-md-5">  
                                        <div class="form-group">
                                            <div class='input-group date invoicefromdate' id='invoicefromdatepanel'>                    
                                                <input id="invoiceFromDate" name="invoiceFromDate"  type="text" 
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
                                    <label class="col-md-5 control-label text-right" >Invoice Date To<font style="color:red">*</font></label>
                                    <div class="col-md-5">  
                                        <div class="form-group">
                                            <div class='input-group date invoicetodate' id='invoicetodatepanel'>                    
                                                <input id="invoiceToDate" name="invoiceToDate"  type="text" 
                                                    class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                            </div>
                                        </div>
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="nonInvoice" class="hidden">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="col-md-5 control-label text-right" >Invoice Sup</label>
                                    <div class="col-md-3 form-group">  
                                        <div class="input-group">
                                            <input type='text' id="invSupCode" name="invSupCode"  class="form-control" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-search" data-toggle="modal" data-target="#invSupModal"></span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-md-1" style="width: 250px;">
                                        <input type='text' readonly="" id="invSupName" name="invSupName" class="form-control" />
                                    </div>
                                </div>   
                            </div>
                        </div>                                     
                        <div class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="col-md-5 control-label text-right" >Pay Date From<font style="color:red">*</font></label>
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
                                    <label class="col-md-5 control-label text-right" >Pay Date To<font style="color:red">*</font></label>
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
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="col-md-10 text-center">  
                                    <div class="form-group">
                                    <button type="button"  class="btn btn-success" id="printReport"  name="printReport" onclick="printStockSummary();"><span class="glyphicon glyphicon-print" ></span> Print</button>
                                </div>
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
<div class="modal fade" id="productModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Product</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->
                <table class="display" id="productTable" style="table-layout: fixed;">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">Id</th>
                            <th style="width: 30%;">Code</th>
                            <th style="width: 70%;">Name</th>
                        </tr>
                    </thead>
                    <script>
                        productTo = [];
                    </script>
                    <tbody>
                        <c:forEach var="product" items="${productList}">
                            <tr onclick="setProduct('${product.id}', '${product.code}', '${product.name}');">
                                <td class="item-productid hidden">${product.id}</td>
                                <td class="item-productcode">${product.code}</td>
                                <td class="item-productname">${product.name}</td>                                
                            </tr>
                        <script>
                            productTo.push({id: "${product.id}", code: "${product.code}", name: "${product.name}"})
                        </script>
                        </c:forEach>                           
                    </tbody>

                </table>
                <!--Script Bill To List Table-->
                <script>
                    $(document).ready(function () {
                                                // BillTo Table
                        var productTable = $('#productTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                        var product = [];
                        $.each(productTo, function (key, value) {
                            product.push(value.code);
                            if ( !(value.name in product) ){
                               product.push(value.name);
                            }
                        });                       
                        
                        $("#productCode").autocomplete({
                            source: product,
                            close: function(event, ui) {
                                $("#productCode").trigger('keyup');
                            }
                        });

                        $("#productCode").on('keyup', function() {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var code = this.value.toUpperCase();
                            var name = this.value;
                            
                            if ($(this).val() === "") {
                                $("#productCode").val("");
                                $("#productName").val("");

                            }

                            $.each(productTo, function(key, value) {
                                if (value.code.toUpperCase() === code) {
                                    $("#productCode").val(value.code);
                                    $("#productName").val(value.name);
                                }
                                if (name === value.name) {
                                    $("#productCode").val(value.code);
                                    $("#productName").val(value.name);
                                    code = $("#productCode").val().toUpperCase();
                                }
                            });
                        });

                        $("#productCode").on('blur', function() {
                            var delay = 500;//1 seconds
                            setTimeout(function() {
                                $.each(invoiceSup, function(key, value) {
                                    //alert(value.code);
                                    if ($("#productCode").val() === value.code) {
                                        $("#productName").val(value.name);
                                    }
                                });

                            }, delay);
                        });
                        
                        
                        //autocomplete
//                        $("#productCode").keyup(function (event) {
//                            var position = $(this).offset();
//                            $(".ui-widget").css("top", position.top + 30);
//                            $(".ui-widget").css("left", position.left);
//                            if ($(this).val() === "") {
//                                $("#productId").val("");
//                                $("#productCode").val("");
//                                $("#productName").val("");
//                            } else {
//                                if (event.keyCode === 13) {
//                                    searchProductAutoList(this.value);
//                                }
//                            }
//                        });
//
//                        var showflag = 1;
//                        $("#productCode").keydown(function () {
//                            var position = $(this).offset();
//                            $(".ui-widget").css("top", position.top + 30);
//                            $(".ui-widget").css("left", position.left);
//                            if (showflag == 0) {
//                                $(".ui-widget").css("top", -1000);
//                                showflag = 1;
//                            }
//                        });
//
//                        
//                        $("#productTable tr").on('click', function () {
//                            var productId = $(this).find(".item-productid").text();
//                            var productCode = $(this).find(".item-productcode").text();
//                            var productName = $(this).find(".item-productname").text();
//                            $("#productId").val(productId);
//                            $("#productCode").val(productCode);
//                            $("#productName").val(productName);
//                            $("#productModal").modal('hide');
//                        });
//
//
//                        $('#productTable tbody').on('click', 'tr', function () {
//                            $('.collapse').collapse('show');
//                            if ($(this).hasClass('row_selected')) {
//                                $(this).removeClass('row_selected');
//                            }
//                            else {
//                                productTable.$('tr.row_selected').removeClass('row_selected');
//                                $(this).addClass('row_selected');
//                            }
//                        });
                        
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
<div class="modal fade" id="invSupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Invoice Supplier</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="invSupTable">
                    <thead class="datatable-header">
                        <script>
                            invoiceSup = [];
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
                            <tr onclick ="setInvSup('${invSup.id}', '${invSup.code}', '${invSup.name}', '${invSup.apcode}')" >
                                <td class="hidden">${invSup.id}</td>
                                <td class="item-invsupcode">${invSup.code}</td>
                                <td class="item-invsupname">${invSup.name}</td>
                                <td>${invSup.apcode}</td> 
                            </tr>
                            <script>
                                invoiceSup.push({id: "${invSup.id}", code: "${invSup.code}", name: "${invSup.name}", apcode: "${invSup.apcode}"});
                            </script>
                        </c:forEach>    
                    </tbody>
                </table>
                
                <script>
                    $(document).ready(function () {
                                                // BillTo Table
                        var invSupTable = $('#invSupTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                        var invoiceSupTo = [];
                        $.each(invoiceSup, function (key, value) {
                            invoiceSupTo.push(value.code);
                            if ( !(value.name in invoiceSupTo) ){
                               invoiceSupTo.push(value.name);
                            }
                        });
                        
                        $("#invSupCode").autocomplete({
                            source: invoiceSupTo,
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
                            
                            if ($(this).val() === "") {
                                $("#invSupCode").val("");
                                $("#invSupName").val("");

                            }

                            $.each(invoiceSup, function(key, value) {
                                if (value.code.toUpperCase() === code) {
                                    $("#invSupCode").val(value.code);
                                    $("#invSupName").val(value.name);
                                }
                                if (name === value.name) {
                                    $("#invSupCode").val(value.code);
                                    $("#invSupName").val(value.name);
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
                                        $("#invSupName").val(value.name);
                                    }
                                });

                            }, delay);
                        });
                        
                        
//                        //autocomplete
//                        $("#invSupCode").keyup(function (event) {
//                            var position = $(this).offset();
//                            $(".ui-widget").css("top", position.top + 30);
//                            $(".ui-widget").css("left", position.left);
//                            if ($(this).val() === "") {
//                                $("#invSupCode").val("");
//                                $("#invSupName").val("");
//
//                            } else {
//                                if (event.keyCode === 13) {
//                                    searchProductAutoList(this.value);
//                                }
//                            }
//                        });
//
//                        var showflag = 1;
//                        $("#invSupCode").keydown(function () {
//                            var position = $(this).offset();
//                            $(".ui-widget").css("top", position.top + 30);
//                            $(".ui-widget").css("left", position.left);
//                            if (showflag == 0) {
//                                $(".ui-widget").css("top", -1000);
//                                showflag = 1;
//                            }
//                        });
//                        
//                        $("#invSupTable tr").on('click', function () {
//                            var invSupCode = $(this).find(".item-invsupcode").text();
//                            var invSupName = $(this).find(".item-invsupname").text();
//                            $("#invSupCode").val(invSupCode);
//                            $("#invSupName").val(invSupName);
//                            $("#invSupModal").modal('hide');
//                        });
//
//                        $('#productTable tbody').on('click', 'tr', function () {
//                            $('.collapse').collapse('show');
//                            if ($(this).hasClass('row_selected')) {
//                                $(this).removeClass('row_selected');
//                            }
//                            else {
//                                invSupTable.$('tr.row_selected').removeClass('row_selected');
//                                $(this).addClass('row_selected');
//                            }
//                        });
                        
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
</div><!-- /.modal-dialog -->
<div class="modal fade" id="billToModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Bill To</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->
                <div style="text-align: right"> <i id="ajaxloadInvoice"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchBillTo" name="searchBillTo" placeholder="Bill To,Name"/> </div> 
                <table class="display" id="billToTable" style="table-layout: fixed;">
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
                        <c:forEach var="item" items="${customerAgentList}">
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
                        var billToTable = $('#billToTable').dataTable({bJQueryUI: true,
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
                        $("#billTo").keyup(function (event) {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            if ($(this).val() === "") {
                                $("#billTo").val("");
                                $("#billName").val("");
                            } else {
                                if (event.keyCode === 13) {
                                    searchCustomerAutoList(this.value);
                                }
                            }
                        });

                        var showflag = 1;
                        $("#billTo").keydown(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            if (showflag == 0) {
                                $(".ui-widget").css("top", -1000);
                                showflag = 1;
                            }
                        });
                        
                        $("#billToTable tr").on('click', function () {
                            var billTo = $(this).find(".item-billto").text();
                            var billName = $(this).find(".item-name").text();
                            $("#billTo").val(billTo);
                            $("#billName").val(billName);
                            $("#billToModal").modal('hide');
                        });


                        $('#billToTable tbody').on('click', 'tr', function () {
                            $('.collapse').collapse('show');
                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                            }
                            else {
                                billToTable.$('tr.row_selected').removeClass('row_selected');
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
//        $('#stockSummaryForm').bootstrapValidator({
//            container: 'tooltip',
//            excluded: [':disabled'],
//            feedbackIcons: {
//                valid: 'uk-icon-check',
//                invalid: 'uk-icon-times',
//                validating: 'uk-icon-refresh'
//            },
//            fields: {
//                effectiveFromDate: {
//                    validators: {
//                        notEmpty: {
//                            message: 'The Effective Date From is required'
//                        }
//                    }
//                },
//                effectiveToDate: {
//                    validators: {
//                        notEmpty: {
//                            message: 'The Effective To Date is required'
//                        }
//                    }
//                },
//                addDate: {
//                    validators: {
//                        notEmpty: {
//                            message: 'The Add Date is required'
//                        }
//                    }
//                },
//                invoiceFromDate: {
//                    validators: {
//                        notEmpty: {
//                            message: 'The Invoice Date From is required'
//                        }
//                    }
//                },
//                invoiceToDate: {
//                    validators: {
//                        notEmpty: {
//                            message: 'The Invoice Date To is required'
//                        }
//                    }
//                },
//                payFromDate: {
//                    validators: {
//                        notEmpty: {
//                            message: 'The Pay Date From is required'
//                        }
//                    }
//                },
//                payToDate: {
//                    validators: {
//                        notEmpty: {
//                            message: 'The Pay Date To is required'
//                        }
//                    }
//                }
//            }
//        });
                    
        $('.date').datetimepicker();
        
        $('.datemask').mask('0000-00-00', {reverse: true});
        
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });

        $('.effectivefromdate').datetimepicker().change(function(){                          
            checkEffectiveFromDateField();
        });
        
        $('.effectivetodate').datetimepicker().change(function(){                          
            checkEffectiveToDateField();
        });
        
        $('.invoicefromdate').datetimepicker().change(function(){                          
            checkInvoiceFromDateField();
        });
        
        $('.invoicetodate').datetimepicker().change(function(){                          
            checkInvoiceToDateField();
        });
        
        $('.payfromdate').datetimepicker().change(function(){                          
            checkPayFromDateField();
        });
        
        $('.paytodate').datetimepicker().change(function(){                          
            checkPayToDateField();
        });
        
        $('.adddate').datetimepicker().change(function(){                          
            if($("#addDate").val() !== ''){
                $("#adddatepanel").removeClass("has-error");
                $("#adddatepanel").addClass("has-success");
            
            }else{
                $("#adddatepanel").removeClass("has-error");
                $("#adddatepanel").removeClass("has-success");
            }
        });
        
        $("#searchBillTo").keyup(function(event) {
            if (event.keyCode === 13) {
                searchBillTo($("#searchBillTo").val());             
            }
        });
        
        $("#reportType").change(function() {
            $("#printReport").removeClass("disabled");
            setScreen(this.value);
        });
        
    });
    
    function setScreen(type){
        if(type === '1'){
            $("#nonInvoice").addClass("hidden");
            $("#invoice").removeClass("hidden");
        
        }else if(type === '2'){
            $("#invoice").addClass("hidden");
            $("#nonInvoice").removeClass("hidden");
        }
    }

    function setProduct(id, code, name) {
        $("#productId").val(id);
        $("#productCode").val(code);
        $("#productName").val(name);
        $("#productModal").modal('hide');
    }
    
    function setBillValue(billto, billname, address, term, pay) {
       $("#billTo").val(billto);
       $("#billName").val(billname);
       $("#billToModal").modal('hide');
    }
    
    function setInvSup(id, code, name, apcode) {       
       $("#invSupCode").val(code);
       $("#invSupName").val(name);
       $("#invSupModal").modal('hide');
    }
    
    function checkEffectiveFromDateField(){      
        var inputFromDate = document.getElementById("effectiveFromDate");
        var InputToDate = document.getElementById("effectiveToDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#effectivefromdatepanel").removeClass("has-error");
            $("#effectivetodatepanel").removeClass("has-error");  
            $("#printReport").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){
            $("#effectivefromdatepanel").removeClass("has-success");
            $("#effectivetodatepanel").removeClass("has-success");
            $("#effectivefromdatepanel").addClass("has-error");
            $("#effectivetodatepanel").addClass("has-error");
            $("#printReport").addClass("disabled");
        } else {
            $("#effectivefromdatepanel").removeClass("has-error");
            $("#effectivetodatepanel").removeClass("has-error");
            $("#effectivefromdatepanel").addClass("has-success");
            $("#effectivetodatepanel").addClass("has-success");
            $("#printReport").removeClass("disabled");
            checkDateValue("effectiveFromDate","effectiveToDate");
        }
    }

    function checkEffectiveToDateField(){
        var inputFromDate = document.getElementById("effectiveFromDate");
        var InputToDate = document.getElementById("effectiveToDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#effectivefromdatepanel").removeClass("has-error");
            $("#effectivetodatepanel").removeClass("has-error");  
            $("#printReport").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){
            $("#effectivefromdatepanel").removeClass("has-success");
            $("#effectivetodatepanel").removeClass("has-success");
            $("#effectivefromdatepanel").addClass("has-error");
            $("#effectivetodatepanel").addClass("has-error");
            $("#printReport").addClass("disabled");
        }else{
            $("#effectivefromdatepanel").removeClass("has-error");
            $("#effectivetodatepanel").removeClass("has-error");
            $("#effectivefromdatepanel").addClass("has-success");
            $("#effectivetodatepanel").addClass("has-success");
            $("#printReport").removeClass("disabled");
            checkDateValue("effectiveFromDate","effectiveToDate");
        }       
    }
    
    function checkInvoiceFromDateField(){      
        var inputFromDate = document.getElementById("invoiceFromDate");
        var InputToDate = document.getElementById("invoiceToDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#invoicefromdatepanel").removeClass("has-error");
            $("#invoicetodatepanel").removeClass("has-error");  
            $("#printReport").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){
            $("#invoicefromdatepanel").removeClass("has-success");
            $("#invoicetodatepanel").removeClass("has-success");
            $("#invoicefromdatepanel").addClass("has-error");
            $("#invoicetodatepanel").addClass("has-error");
            $("#printReport").addClass("disabled");
        } else {
            $("#invoicefromdatepanel").removeClass("has-error");
            $("#invoicetodatepanel").removeClass("has-error");
            $("#invoicefromdatepanel").addClass("has-success");
            $("#invoicetodatepanel").addClass("has-success");
            $("#printReport").removeClass("disabled");
            checkDateValue("invoiceFromDate","invoiceToDate");
        }
    }

    function checkInvoiceToDateField(){
        var inputFromDate = document.getElementById("invoiceFromDate");
        var InputToDate = document.getElementById("invoiceToDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#invoicefromdatepanel").removeClass("has-error");
            $("#invoicetodatepanel").removeClass("has-error");  
            $("#printReport").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){
            $("#invoicefromdatepanel").removeClass("has-success");
            $("#invoicetodatepanel").removeClass("has-success");
            $("#invoicefromdatepanel").addClass("has-error");
            $("#invoicetodatepanel").addClass("has-error");
            $("#printReport").addClass("disabled");
        }else{
            $("#invoicefromdatepanel").removeClass("has-error");
            $("#invoicetodatepanel").removeClass("has-error");
            $("#invoicefromdatepanel").addClass("has-success");
            $("#invoicetodatepanel").addClass("has-success");
            $("#printReport").removeClass("disabled");
            checkDateValue("invoiceFromDate","invoiceToDate");
        }       
    }
    
    function checkPayFromDateField(){      
        var inputFromDate = document.getElementById("payFromDate");
        var InputToDate = document.getElementById("payToDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#payfromdatepanel").removeClass("has-error");
            $("#paytodatepanel").removeClass("has-error");  
            $("#printReport").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){
            $("#payfromdatepanel").removeClass("has-success");
            $("#paytodatepanel").removeClass("has-success");
            $("#payfromdatepanel").addClass("has-error");
            $("#paytodatepanel").addClass("has-error");
            $("#printReport").addClass("disabled");
        } else {
            $("#payfromdatepanel").removeClass("has-error");
            $("#paytodatepanel").removeClass("has-error");
            $("#payfromdatepanel").addClass("has-success");
            $("#paytodatepanel").addClass("has-success");
            $("#printReport").removeClass("disabled");
            checkDateValue("payFromDate","payToDate");
        }
    }

    function checkPayToDateField(){
        var inputFromDate = document.getElementById("payFromDate");
        var InputToDate = document.getElementById("payToDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#payfromdatepanel").removeClass("has-error");
            $("#paytodatepanel").removeClass("has-error");  
            $("#printReport").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){
            $("#payfromdatepanel").removeClass("has-success");
            $("#paytodatepanel").removeClass("has-success");
            $("#payfromdatepanel").addClass("has-error");
            $("#paytodatepanel").addClass("has-error");
            $("#printReport").addClass("disabled");
        }else{
            $("#payfromdatepanel").removeClass("has-error");
            $("#paytodatepanel").removeClass("has-error");
            $("#payfromdatepanel").addClass("has-success");
            $("#paytodatepanel").addClass("has-success");
            $("#printReport").removeClass("disabled");
            checkDateValue("payFromDate","payToDate");
        }       
    }

    function checkDateValue(from,to){
        var inputFromDate = document.getElementById(from);
        var InputToDate = document.getElementById(to);
        if((inputFromDate.value !== '') && (InputToDate.value !== '')){
            var fromDate = (inputFromDate.value).split('-');
            var toDate = (InputToDate.value).split('-');
            if((parseInt(fromDate[0])) > (parseInt(toDate[0]))){
                validateDate(from,"over");
            }
            if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))){
                validateDate(from,"over");
            }
            if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[2])) > (parseInt(toDate[2]))){
                validateDate(from,"over");
            }          
        }
    }

    function validateDate(panel,option){
        if(option === 'over'){
            if(panel === 'effectiveFromDate'){
                $("#effectivefromdatepanel").removeClass("has-success");
                $("#effectivefromdatepanel").addClass("has-error");                                 
                $("#effectivetodatepanel").removeClass("has-success");
                $("#effectivetodatepanel").addClass("has-error");   
            
            }else if(panel === 'invoiceFromDate'){
                $("#invoicefromdatepanel").removeClass("has-success");
                $("#invoicefromdatepanel").addClass("has-error");                                 
                $("#invoicetodatepanel").removeClass("has-success");
                $("#invoicetodatepanel").addClass("has-error");
                
            }else if(panel === 'payFromDate'){
                $("#payfromdatepanel").removeClass("has-success");
                $("#payfromdatepanel").addClass("has-error");                                 
                $("#paytodatepanel").removeClass("has-success");
                $("#paytodatepanel").addClass("has-error");
                
            }
            
            $("#printReport").addClass("disabled");
            
        } else {
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success"); 
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#printReport").addClass("disabled");
        }
    }

    function printStockSummary(){ 
        var isPrint = true;
        var reportType = $("#reportType").val();
        var product = $("#productCode").val();
        var effectiveDateFrom = $("#effectiveFromDate").val();
        var effectiveDateTo = $("#effectiveToDate").val();
        var addDate = $("#addDate").val();
        
        var invTo = $("#billTo").val();
        var invoiceDateFrom = $("#invoiceFromDate").val();
        var invoiceDateTo = $("#invoiceToDate").val();
        
        var invoiceSup = $("#invSupCode").val();
        var payDateFrom = $("#payFromDate").val();
        var payDateTo = $("#payToDate").val();
        
        if(reportType === '1'){
            reportType = "StockInvoiceSummary";
            if(effectiveDateFrom === '' && effectiveDateTo === '' && addDate === '' && invoiceDateFrom === '' && invoiceDateTo === ''){
                isPrint = false;
                $("#effectivefromdatepanel").removeClass("has-success");
                $("#effectivefromdatepanel").addClass("has-error");                                 
                $("#effectivetodatepanel").removeClass("has-success");
                $("#effectivetodatepanel").addClass("has-error");
                $("#invoicefromdatepanel").removeClass("has-success");
                $("#invoicefromdatepanel").addClass("has-error");                                 
                $("#invoicetodatepanel").removeClass("has-success");
                $("#invoicetodatepanel").addClass("has-error");
                $("#adddatepanel").removeClass("has-success");
                $("#adddatepanel").addClass("has-error");
            }
            
        }else if(reportType === '2'){
            reportType = "StockNonInvoiceSummary";
            if(effectiveDateFrom === '' && effectiveDateTo === '' && addDate === '' && payDateFrom === '' && payDateTo === ''){
                isPrint = false;
                $("#effectivefromdatepanel").removeClass("has-success");
                $("#effectivefromdatepanel").addClass("has-error");                                 
                $("#effectivetodatepanel").removeClass("has-success");
                $("#effectivetodatepanel").addClass("has-error");
                $("#payfromdatepanel").removeClass("has-success");
                $("#payfromdatepanel").addClass("has-error");                                 
                $("#paytodatepanel").removeClass("has-success");
                $("#paytodatepanel").addClass("has-error");
                $("#adddatepanel").removeClass("has-success");
                $("#adddatepanel").addClass("has-error");
            }
        }
                    
        if(isPrint && reportType === "StockInvoiceSummary"){
            window.open("Excel.smi?name="+reportType+"&product="+product+"&effectiveDateFrom="+effectiveDateFrom+"&effectiveDateTo="+effectiveDateTo+"&addDate="+addDate+"&invTo="+invTo+"&invoiceDateFrom="+invoiceDateFrom+"&invoiceDateTo="+invoiceDateTo);  
        
        }else if(isPrint && reportType === "StockNonInvoiceSummary"){
            window.open("Excel.smi?name="+reportType+"&product="+product+"&effectiveDateFrom="+effectiveDateFrom+"&effectiveDateTo="+effectiveDateTo+"&addDate="+addDate+"&invoiceSup="+invoiceSup+"&payDateFrom="+payDateFrom+"&payDateTo="+payDateTo);  
            
        }     
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
                    var billselect = $("#billTo").val();
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
                                $("#billTo").val(billListId[i]);
                                $("#billName").val(billListName[i]);
                            }
                        }
                        $("#dataload").addClass("hidden");
                    }
                    // $("#refundBy").val(billid);
                    //$("#refundByName").val(billname);

                    $("#billTo").autocomplete({
                        source: billArray,
                        close: function () {
                            $("#billTo").trigger("keyup");
                            var billselect = $("#billTo").val();
                            for (var i = 0; i < billListId.length; i++) {
                                if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                                    $("#billTo").val(billListId[i]);
                                    $("#billName").val(billListName[i]);
                                }
                            }
                        }
                    });

                    var billval = $("#billTo").val();
                    for (var i = 0; i < billListId.length; i++) {
                        if (billval == billListName[i]) {
                            $("#billTo").val(billListId[i]);
                        }
                    }
                    if (billListId.length == 1) {
                        showflag = 0;
                        $("#billTo").val(billListId[0]);
                    }
                    var event = jQuery.Event('keydown');
                    event.keyCode = 40;
                    $("#billTo").trigger(event);

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
        $("#ajaxloadInvoice").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function(msg) {
                    $('#billToTable').dataTable().fnClearTable();
                    $('#billToTable').dataTable().fnDestroy();
                    $("#billToTable tbody").empty().append(msg);

                    $('#billToTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });
                    $("#ajaxloadInvoice").addClass("hidden");

                }, error: function(msg) {
                    $("#ajaxloadInvoice").addClass("hidden");
                    alert('error');
                }
            });
        } catch (e) {
            $("#ajaxloadInvoice").addClass("hidden");
            alert(e);
        }
    }
</script>
