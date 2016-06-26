<%-- 
    Document   : PaymentSummary
    Created on : Feb 16, 2016, 11:41:08 AM
    Author     : Jittima
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="listCity" value="${requestScope['city_list']}" />
<c:set var="listCountry" value="${requestScope['country_list']}" />
<c:set var="product_list" value="${requestScope['listProduct']}" />
<c:set var="invSupList" value="${requestScope['invSupList']}" />
<c:set var="userList" value="${requestScope['staffList']}" />
<c:set var="ProductTypeList" value="${requestScope['ProductTypeList']}" />

<section class="content-header"  >
    <h4>
        <b>Report : Checking Outbound report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Payment Summary</a></li>
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
                    <h3>Payment Summary</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="PaymentSummaryFrom" name="PaymentSummaryFrom" method="post" class="form-horizontal" >
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Payment Date From</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date fromdate' id='fromdatepanel'>                    
                                            <input id="FromDate" name="FromDate"  type="text" 
                                                class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="">
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
                                <label class="col-md-5 control-label text-right" >Payment Date To</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date todate' id='todatepanel'>                    
                                            <input id="ToDate" name="ToDate"  type="text" 
                                                class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>    
                     <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="invnofromdatepanel">
                                <label class="col-md-5 control-label text-right">Invoice Date From<font style="color: red"></font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date invoicefromdate' id='DateFrom'>
                                            <input type='text' id="invoiceFromDate" name="invoiceFromDate" class="form-control datemask" placeholder="DD-MM-YYYY" data-date-format="DD-MM-YYYY"/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="invnotodatepanel">
                                <label class="col-md-5 control-label text-right">Invoice Date To<font style="color: red"></font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date invoicetodate' id='DateTo'>
                                            <input type='text' id="invoiceToDate" name="invoiceToDate"  class="form-control datemask" placeholder="DD-MM-YYYY" data-date-format="DD-MM-YYYY" />
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
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
                                <label class="col-md-5 control-label text-right" >Staff</label>
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
                        <div class="col-xs-8 ">
                            <div class="form-group">
                            <label class="col-md-5 control-label text-right" >Inv Name</label>
                            <div class="col-md-3 form-group">  
                                <div class="input-group" id="refundByValidate">
                                    <input type="text" class="form-control" id="refundBy" name="refundBy" value="${refundAirline.refundBy}" />
                                    <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#refundCustModal">
                                        <span class="glyphicon-search glyphicon"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <!--<div class="input-group">-->
                                    <input id="refundByName" name="refundByName" type="text" class="form-control" value="${refundByName}" readonly="" style="width: 132px" >
                                <!--</div>-->
                            </div>
                            </div>    
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Product Type</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select id="SelectProductType" name="SelectProductType" class="form-control">
                                               <option value='' ></option>
                                                <c:forEach var="typeP" items="${ProductTypeList}">
                                                    <c:set var="selectTypePro" value="" />
                      
                                                     <option value='${typeP.name}' ${selectTypePro}>${typeP.name}</option>
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
                                <label class="col-md-5 control-label text-right" >Product </label>
                                <div class="col-md-3 form-group" id="agentcodepanel">  
                                    <div class="input-group">
                                        <input name="InputId" id="InputId" type="hidden" class="form-control" value="" />
                                        <input type="text" class="form-control" id="InputProductId" name="InputProductId" value="" />
                                        <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#ProductModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3" id="agentnamepanel">
                                    <input name="InputProductName" id="InputProductName" type="text" class="form-control" value="" readonly="" />
                                </div>
                            </div> 
                        </div>
                    </div>   
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-left" >Country</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectCountry" id="SelectCountry"  class="form-control selectize">
                                            <option value=""  selected="selected">-- ALL --</option>             
                                            <c:forEach var="term" items="${listCountry}" > 
                                                <option value="${term.name}" >${term.name}</option>  
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
                                <label class="col-md-5 control-label text-left" >City</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectCity" id="SelectCity"  class="form-control selectize">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listCity}" >
                                              
                                                <option value="${term.name}" ${select}>${term.name}</option>  
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
                                <label class="col-md-5 control-label text-right" >Ref no</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <input id="refno" name="refno"  type="text" class="form-control" value="" >
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right"></label>
                                <div class="col-md-5 text-center">  
                                    <div class="form-group">
                                        <button type="button"  class="btn btn-success" id="printbutton"  name="printbutton" onclick="printPaymentSummary();"><span class="glyphicon glyphicon-print" ></span> Print</button>
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

<div class="modal fade" id="refundCustModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Inv Name</h4>
            </div>
            <div class="modal-body">
                <div style="text-align: right"> 
                    <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchCustFrom" name="searchCustFrom"/> 
                </div> 
                <table class="display" id="refundCustTable">
                    <thead >   
                        <tr class="datatable-header">
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cust}">
                            <tr onclick="setBillValue('${item.billTo}', '${item.billName}', '${item.address}', '${item.term}', '${item.pay}');">
                                <td class="item-billto">${item.billTo}</td>
                                <td class="item-name">${item.billName}</td>                                
                                <td class="item-address hidden">${item.address}</td>
                                <td class="item-tel hidden">${item.tel}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="rrefundCustModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>


<div class="modal fade" id="ProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Product</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="productTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th style="width:20%">Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <script>
                        productCode = [];
                    </script>
                    <tbody>
                        <c:forEach var="pro" items="${product_list}">
                            <tr>
                                <td class="product-id hidden">${pro.id}</td>
                                <td class="product-code">${pro.code}</td>
                                <c:set var="name1" value="${pro.name}"/>
                                <c:set var="name2" value="${fn:replace(name1, '\\\\', '')}" />
                                <td class="product-name">${name2}</td>
                            </tr>
                        <script>
                            productCode.push({id: "${pro.id}", code: "${pro.code}", name: "${pro.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Script Product List table-->
            <script>
                $(document).ready(function () {
//                    alert("<%=new java.util.Date()%>");
                    $("#productTable tr").on('click', function () {//winit
                        $("#SearchProduct").modal('hide');
                        var product_id = $(this).find(".product-id").html();
                        var product_code = $(this).find(".product-code").html();
                        var product_name = $(this).find(".product-name").html();
                        $("#InputId").val(product_id);
                        $("#InputProductId").val(product_code);
                        $("#InputProductName").val(product_name);
                        $("#ProductModal").modal('hide');
                    });

                    // productTable
                    var productTable = $('#productTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": true,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });

                    $('#productTable tbody').on('click', 'tr', function () {
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                        }
                        else {
                            productTable.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                        }
                    });
                    
                    $("#refundBy").keyup(function (event) {
                        var position = $(this).offset();
                        $(".ui-widget").css("top", position.top + 30);
                        $(".ui-widget").css("left", position.left);
                        if ($(this).val() === "") {
                            $("#refundBy").val("");
                            $("#refundByName").val("");
                        } else {
                        if (event.keyCode === 13) {
                            searchCustomerAutoList(this.value);
                        }
                        }
                     });

                    var productuser = [];
                    $.each(productCode, function (key, value) {
                        productuser.push(value.code);
                        productuser.push(value.name);
                    });

                    $("#InputProductId").autocomplete({
                        source: productuser,
                        close:function( event, ui ) {
                           $("#InputProductId").trigger('keyup');
                        }
                    });

                    $("#InputProductId").on('keyup',function(){
                        var position = $(this).offset();
                        $(".ui-widget").css("top", position.top + 30);
                        $(".ui-widget").css("left", position.left);
                        var code = this.value.toUpperCase();
                        var name = this.value.toUpperCase();
                       // console.log("Name :"+ name);
                        $("#InputId,#InputProductName").val(null);
                        if($("#InputProductId").val() !== ''){
                            $.each(productCode, function (key, value) {                           
                                if (value.code.toUpperCase() === code ) {  
                                    $("#InputId").val(value.id);
                                    $("#InputProductId").val(value.code);
                                    $("#InputProductName").val(value.name);
                                }
                                else if(value.name.toUpperCase() === name){
                                    $("#InputProductId").val(value.code);
                                    $("#InputId").val(value.id);
                                    $("#InputProductName").val(value.name);
                                }
    //                            else if(value.name === '' && value.code === ''){
    //                                $("#InputProductId").val('');
    //                                $("#InputId").val('');
    //                                $("#InputProductName").val('');
    //                            }
                            });
                        
                        }else{
                            $("#InputProductId").val('');
                            $("#InputId").val('');
                            $("#InputProductName").val('');
                        }
                    }); 
                    
                     $("#searchCustFrom").keyup(function (event) {
                        if (event.keyCode === 13) {
                        if ($("#searchCustFrom").val() === "") {
                            // alert('please input data');
                        }
                        searchCustomerAgentList($("#searchCustFrom").val());
                    }
                    });
                    
                });        
            </script>
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
    
        Selectize.define('clear_selection', function(options) {
        var self = this;
        self.plugins.settings.dropdown_header = {
            title: 'Clear Selection'
        };
        this.require('dropdown_header');
        self.setup = (function() {
            var original = self.setup;
            return function() {
                original.apply(this, arguments);
                this.$dropdown.on('mousedown', '.selectize-dropdown-header', function(e) {
                    self.setValue('');
                    self.close();
                    self.blur();
                    return false;
                });
            };
        })();
    });
    
    $('.date').datetimepicker();
    $('.datemask').mask('00-00-0000');
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

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
    
     $('.fromdate').datetimepicker().change(function(){                          
        checkFromDateField();
    });
    $('.todate').datetimepicker().change(function(){                          
        checkToDateField();
    });
    
    $('.invoicefromdate').datetimepicker().change(function(){                          
        checkInvoiceFromDateField();
    });
    $('.invoicetodate').datetimepicker().change(function(){                          
        checkInvoiceToDateField();
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
            if (value.code.toUpperCase() === code && code.length > 1) {
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
                $("#invSupApCode").val(value.apcode);
            }
            if (name === value.name && name.length > 1) {
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
    
    var Country = "#SelectCountry";
    $(Country).selectize({
        removeItem: '',
        sortField: 'text',
        create: false,
        dropdownParent: 'body',
        plugins: {
            'clear_selection': {}
        }
    });
//
    var City = "#SelectCity";
    $(City).selectize({
        removeItem: '',
        sortField: 'text',
        create: false,
        dropdownParent: 'body',
        plugins: {
            'clear_selection': {}
        }
    });
});

function setBillValue(billto, billname, address, term, pay) {

       $("#billto").val(billto);
       $("#billname").val(billname);
       $("#BillToModal").modal('hide');
}
function checkFromDateField(){      
    var inputFromDate = document.getElementById("FromDate");
    var InputToDate = document.getElementById("ToDate");
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

function searchCustomerAgentList(name) {
    name = generateSpecialCharacter(name);
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getListBillto';
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                $('#refundCustTable').dataTable().fnClearTable();
                $('#refundCustTable').dataTable().fnDestroy();
                $("#refundCustTable tbody").empty().append(msg);

                $('#refundCustTable').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bPaginate": true,
                    "bInfo": false,
                    "bLengthChange": false,
                    "iDisplayLength": 10
                });
                $("#ajaxload").addClass("hidden");

            }, error: function (msg) {
                $("#ajaxload").addClass("hidden");
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function setBillValue(billto, billname, address, term, pay) {

   $("#refundBy").val(billto);
   $("#refundByName").val(billname);
   $("#refundCustModal").modal('hide');
}

    
function checkToDateField(){
    var inputFromDate = document.getElementById("FromDate");
    var InputToDate = document.getElementById("ToDate");
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
    var inputFromDate = ""; //document.getElementById("FromDate");
    var InputToDate = "";//document.getElementById("ToDate");
    
    if((date === 'from') || (date === 'to')){
            inputFromDate = document.getElementById("FromDate");
            InputToDate = document.getElementById("ToDate");
        } else {
            inputFromDate = document.getElementById("invoiceFromDate");
            InputToDate = document.getElementById("invoiceToDate");
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

function checkInvoiceFromDateField(){
    var InputToDate = document.getElementById("invoiceToDate");
    var inputFromDate = document.getElementById("invoiceFromDate");
   // alert(12);
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#invnofromdatepanel").removeClass("has-error");
        $("#invnotodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#invnofromdatepanel").removeClass("has-success");
        $("#invnotodatepanel").removeClass("has-success");
        $("#invnofromdatepanel").addClass("has-error");
        $("#invnotodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#invnofromdatepanel").removeClass("has-error");
        $("#invnotodatepanel").removeClass("has-error");
      //  $("#issuefromdatepanel").removeClass("has-error");
    //    $("#issuetodatepanel").removeClass("has-error");
        $("#invnofromdatepanel").addClass("has-success");
        $("#invnotodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("invfrom","");
    }
}
    
function checkInvoiceToDateField(){
    var InputToDate = document.getElementById("invoiceToDate");
    var inputFromDate = document.getElementById("invoiceFromDate");
    //    alert(13);
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#invnofromdatepanel").removeClass("has-error");
        $("#invnotodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#invnofromdatepanel").removeClass("has-success");
        $("#invnotodatepanel").removeClass("has-success");
        $("#invnofromdatepanel").addClass("has-error");
        $("#invnotodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    }else{
        $("#invnofromdatepanel").removeClass("has-error");
        $("#invnotodatepanel").removeClass("has-error");
   //     $("#issuefromdatepanel").removeClass("has-error");
    //    $("#issuetodatepanel").removeClass("has-error");
        $("#invnofromdatepanel").addClass("has-success");
        $("#invnotodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("invto","");
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
        if(date === 'invfrom'){
            $("#invnofromdatepanel").removeClass("has-success");
            $("#invnofromdatepanel").addClass("has-error");
        }
        if(date === 'invto'){
            $("#invnotodatepanel").removeClass("has-success"); 
            $("#invnotodatepanel").addClass("has-error");
        }       
        $("#printbutton").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success"); 
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#invnofromdatepanel").removeClass("has-success");
        $("#invnotodatepanel").removeClass("has-success"); 
        $("#invnofromdatepanel").addClass("has-error");
        $("#invnotodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    }
}

function printPaymentSummary(){  
    var from = convertFormatDate($('#FromDate').val());
    var to = convertFormatDate($('#ToDate').val());
    var invSupCode = $('#invSupCode').val();
    var salebyUser = $('#salebyUser').val();
    var refno = $('#refno').val();
    var invto = convertFormatDate($('#invoiceToDate').val());
    var invfrom = convertFormatDate($('#invoiceFromDate').val());
    var billname = $('#refundBy').val();
    var productid = $('#InputId').val();
    var country  = $("#SelectCountry").val();
    var city  = $("#SelectCity").val();
    var paytype = $('#SelectProductType').val();
    var billnameDetail = $('#refundByName').val();
    var productname = $('#InputProductName').val();
    if(((from === '') || (to === '')) && ((invto === '') || (invfrom === ''))){
        validateDate();
    } else {
        window.open("Excel.smi?name=PaymentSummaryReport"+"&from="+from+"&to="+to+"&invSupCode="+invSupCode+"&salebyUser="+salebyUser+"&refno="+refno  
                +"&invto="+invto+"&invfrom="+invfrom+"&billname="+billname+"&productid="+productid+"&country="+country+"&city="+city+"&paytype="+paytype+"&billnamedetail="+billnameDetail+"&productname="+productname); 
    } 
}
   
function setupInvSupValue(id, code, name, apcode) {
    $('#SearchInvoiceSup').modal('hide');
    document.getElementById('invSupId').value = id;
    document.getElementById('invSupCode').value = code;
    document.getElementById('invSupName').value = name;
    document.getElementById('invSupApCode').value = apcode;
    document.getElementById('invSupCode').focus();
}
</script>