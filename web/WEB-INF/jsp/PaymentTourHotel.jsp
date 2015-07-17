<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="agent_list" value="${requestScope['agent_list']}" />
<c:set var="pvType_list" value="${requestScope['pvType_list']}" />
<c:set var="status_list" value="${requestScope['status_list']}" />
<c:set var="invoiceSup_list" value="${requestScope['invoiceSup_list']}" />
<c:set var="APcode_list" value="${requestScope['APcode_list']}" />
<c:set var="payment_list" value="${requestScope['payment_list']}" />
<c:set var="paymentHotel_list" value="${requestScope['paymentHotel_list']}" />
<c:set var="product_list" value="${requestScope['product_list']}" />
<c:set var="currency_list" value="${requestScope['currency_list']}" />
<c:set var="detail" value="${requestScope['BookDetail']}" />
<c:set var="resultText" value="${requestScope['resultText']}" />

<section class="content-header" >
    <h1>
        Checking - Package Tour/Hotel
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Checking</a></li>          
        <li class="active"><a href="#">Package Tour/Hotel</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/Checking/CheckingPackageTourHotel.html'"></div>
    </div>
    <!--Content -->
   
    <form action="PaymentTourHotel.smi" method="post" id="PaymentTourHotelForm" autocomplete="off" role="form">
    <div class="col-sm-10">
        <c:if test="${requestScope['resultText'] =='success'}">                                            
            <div id="textAlertDivSave"  style="" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Success!</strong> 
            </div>
        </c:if>
        <c:if test="${requestScope['resultText'] =='fail'}">
        <div id="textAlertDivNotSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <strong>Save Unsuccess!</strong> 
        </div>
        </c:if>
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Payment Tour / Hotel</b></h4>
            </div>
        </div>
        <hr/>
        
        <!--Row 1 -->
        <div class="row" style="padding-left: 15px">
            <div class="col-xs-12 ">
                <div class="col-xs-1 text-right">
                    <label class="control-label">Pay No</lable>
                </div>
                <div class="col-md-3 form-group text-left">
                    <div class="col-sm-12">
                        <input name="InputPayNo" id="InputPayNo" type="text" class="form-control" value="${requestScope['InputPayNo']}"/>
                    </div>
                </div>
                <div class="col-xs-4 text-left" style="padding-left:10px;padding-right:0px;"></div>
                <div class="col-xs-1 text-left" style="padding-left:10px;padding-right:0px;">
                    <label class="control-label">Account<font style="color: red">*</font></lable>
                </div>
                <div class="col-md-3  text-left" style="padding-top : 5px;padding-left:0px;padding-right:0px;">
                    <div class="col-sm-6" text-left> 
                        <c:set var="check1" value="" />
                        <c:if test="${1 == requestScope['account']}">
                            <c:set var="check1" value="checked" />
                        </c:if>  
                        <input type="radio" name="account"  id="account1" value="1" ${check1}/> &nbsp;account(1)
                    </div>
                    <div class="col-sm-6" text-left>
                        <c:set var="check2" value="" />
                        <c:if test="${2 == requestScope['account']}">
                            <c:set var="check2" value="checked" />
                        </c:if>  
                        <input type="radio" name="account"  id="account2" value="2" ${check2}/>&nbsp;account(2)
                    </div>
                </div>
            </div>
        </div>
        <!--Row 2 -->
        <div class="row" style="padding-left: 0px">
            <div class="col-xs-12 ">
                <div class="col-xs-2 text-right" style="padding-left:0px;padding-right:0px;width:85px;">
                    <label class="control-label">Pay Date</lable>
                </div>               
                <div class="col-md-2 form-group text-left" style="padding-left:28px">
                    <div class="col-sm-12">
                        <div class='input-group date' style="width:140px;">
                            <input name="InputPayDate" id="InputPayDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['InputPayDate']}" />
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
                <div class="col-xs-2 text-right" style="padding-left:0px;padding-right:20px;">
                    <label class="control-label">PV Type</lable>
                </div>
                <div class="col-md-2 form-group text-left" style="padding-left:5px;padding-right:0px;">
                    <div class="col-sm-12">
                        <select name="itemPvType" id="itemPvType" class="form-control">
                            <option id="" value="">---------</option>  
                            <c:forEach var="PVType" items="${pvType_list}">
                                <c:set var="select" value="" />
                                <c:if test="${PVType.id == requestScope['itemPvType']}">
                                    <c:set var="select" value="selected" />
                                </c:if>
                                <option value="<c:out value="${PVType.id}" />" ${select}><c:out value="${PVType.name}" /></option>                                         
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-xs-2 text-right" style="padding-left:5px;padding-right: 10px;">
                    <label class="control-label">Status<font style="color: red">*</font></lable>
                </div>
                <div class="col-md-2  text-left" style="padding-top:5px;padding-left:0px;padding-right:0px;">
                    <div class="col-sm-12">
                        <select class="form-control" id="itemStatus" name="itemStatus">
                            <option id="" value="">---------</option>  
                            <c:forEach var="status" items="${status_list}">
                                <c:set var="select" value="" />
                                <c:if test="${status.id == requestScope['itemStatus']}">
                                    <c:set var="select" value="selected" />
                                </c:if>
                                <option value="<c:out value="${status.id}" />" ${select}><c:out value="${status.name}" /></option>                                         
                            </c:forEach>
                        </select>    
                    </div>
                </div>
            </div>
        </div>
        <!--Row 3 -->
        <div class="row" >
            <div class="col-xs-2 text-right" style="padding-left:10px;padding-right:0px;width:100px;">
                    <label class="control-label">Invoice Sup<font style="color: red">*</font></lable>
            </div>
            <div class="col-md-2 form-group text-right" style="padding-left:30px;padding-right:0px;"> 
                <div class="col-sm-12">
                    <div class="input-group" id="CodeValidate">
                        <input name="InputInvoiceSupId" id="InputInvoiceSupId" type="hidden" class="form-control" value="${requestScope['InputInvoiceSupId']}" />
                        <input name="InputInvoiceSupCode" id="InputInvoiceSupCode" type="text" class="form-control" value="${requestScope['InputInvoiceSupCode']}" />
                        <span class="input-group-addon" data-toggle="modal" data-target="#SearchInvoiceSup">
                            <span class="glyphicon-search glyphicon"></span>
                        </span>    
                    </div>    
                </div>   
            </div>
            <div class="col-md-4 form-group text-left" style="width:360px;">
                <div class="col-sm-12">
                    <input name="InputInvoiceSupName" id="InputInvoiceSupName" type="text" class="form-control" value="${requestScope['InputInvoiceSupName']}" />           
                </div>
            </div>
            <div class="col-xs-2 text-right" style="padding-left:10px;padding-right:0px;width:140px;">
                <label class="control-label">A/P Code<font style="color: red">*</font></lable>
            </div>
            <div class="col-md-2 form-group text-left" style="padding-left:9px;width:190px;">
                <div class="col-sm-12">
                    <div class="input-group" id="CodeValidate">
                        <input name="InputAPCode" id="InputAPCode" type="text" class="form-control" value="${requestScope['InputAPCode']}" />
                        <span class="input-group-addon" data-toggle="modal" data-target="#SearchAPCode">
                            <span class="glyphicon-search glyphicon"></span>
                        </span>    
                    </div>    
                </div>  
            </div>
        </div>
        <!--Row 4 -->
        <div class="row" >
            <div class="col-xs-2 text-right" style="padding-left:10px;padding-right:0px;width:100px;">
                    <label class="control-label">Detail</lable>
            </div>
            <div class="col-md-6 form-group text-left" style="padding-left:30px;padding-right:0px;width:520px;">
                <div class="col-sm-12">
                    <textarea rows="3" cols="255" class="form-control" id="Detail" name="Detail">${requestScope['Detail']}</textarea>
                </div>   
            </div>
            <div class="col-xs-2 text-right" style="padding-left:10px;padding-right:0px;width:155px;">
                <label class="control-label">Payment</lable>
            </div>
            <div class="col-md-2 form-group text-left" style="padding-left:9px;width:190px;">
                <div class="col-sm-12">
                    <select class="form-control" id="itemPayment" name="itemPayment">
                            <option id="" value="">---------</option>  
                            <c:forEach var="payment" items="${payment_list}">
                                <c:set var="select" value="" />
                                <c:if test="${payment.id == requestScope['itemPayment']}">
                                    <c:set var="select" value="selected" />
                                </c:if>
                                <option value="<c:out value="${payment.id}" />" ${select}><c:out value="${payment.name}" /></option>                                         
                            </c:forEach>
                    </select>         
                </div>
            </div>
        </div>
       
        <!-- Table -->
        <div class="row" >
            <div class="col-12" style="width:1035px;padding-left:15px;">
                <table class="display" id="PaymentHotelTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden" style="width: 13%">Id</th>
                            <th style="width: 13%">Product</th>
                            <th style="width: 8%">Ref No</th>
                            <th style="width: 10%">Inv No</th>
                            <th style="width: 8%">Code</th>
                            <th style="width: 9%">Type</th>
                            <th style="width: 10%">Amount</th>
                            <th style="width: 9%">Cur</th>
                            <th style="width: 20%">Description</th>
                            <th style="width: 8%">A/C</th>
                            <th style="width: 7%">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pl" items="${productDetail_list}" varStatus="i">
                            <tr>
                                <td class="hidden"><input id="tableId${i.count}" name="tableId${i.count}"  type="hidden" value="${pl.id}"></td>
                                <td>                                   
                                    <select class="form-control" name="select-product${i.count}" id="select-product${i.count}">
                                    <c:forEach var="product" items="${product_list}" varStatus="status"> 
                                        <c:set var="select" value="" />
                                        <c:if test="${product.id == pl.MPaytype.id}">
                                            <c:set var="select" value="selected" />
                                        </c:if>
                                        <option  value="${product.id}" ${select}>${product.name}</option>
                                    </c:forEach>
                                    </select>                                                                  
                                </td>
                                <td> <input style="width: ${RefNo}" id="refNo${i.count}" name="refNo${i.count}" maxlength ="10"  type="text" class="form-control" value="${pl.master.referenceNo}"> </td>
                                <td> <input style="width: ${InvNo}" id="invNo${i.count}" name="invNo${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.invoiceCreditor}">  </td>
                                <td> <input style="width: ${Code}" id="code${i.count}" name="code${i.count}" maxlength ="15"  type="text" class="form-control" value="">  </td>
                                <td>
                                    <c:set var="type1" value="" />
                                    <c:if test="${'T' == pl.amountType}">
                                        <c:set var="type1" value="checked" />
                                    </c:if>  
                                    <input type="radio" name="type${i.count}" id="row-${i.count}-typeT" value="T" ${type1}> T&nbsp;
                                    <c:set var="type2" value="" />
                                    <c:if test="${'C' == pl.amountType}">
                                        <c:set var="type2" value="checked" />
                                    </c:if>  
                                    <input type="radio" name="type${i.count}" id="row-${i.count}-typeC" value="C" ${type2}> C
                                </td>
                                <td> <input style="width: ${Amount}" id="amount${i.count}" name="amount${i.count}" maxlength ="15"  type="text" class="form-control money" onkeyup="CalculateGrandTotal('${pl.id}','${i.count}')" value="${pl.amount}"> </td>
                                <td> 
                                    <select class="form-control" id="select_currency${i.count}" name="select_currency${i.count}">
                                        <c:forEach var="currency" items="${currency_list}" varStatus="status">
                                            <c:set var="select" value="" />
                                            <c:if test="${currency.id == pl.currency}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option  value="${currency.id}" ${select}>${currency.code}</option>
                                        </c:forEach>
                                    </select>                                 
                                </td>
                                <td> <input style="width: ${Description}" id="description${i.count}" name="description${i.count}" maxlength ="255"  type="text" class="form-control" value="${pl.description}"> </td>
                                <td> <input style="width: ${AC}" id="ac${i.count}" name="ac${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.accCode}"> </td>
                                <td class="text-center">
                                    
                                        <a class="remCF"><span id="SpanRemove${i.count}" onclick="deletelist('${pl.id}','${i.count}');" class="glyphicon glyphicon-remove deleteicon "></span></a>
                                   
                                    <c:if test="${lockUnlockBooking == 1}">
                                        <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                    </c:if>
                                </td>
                            </tr>                       
                        </c:forEach> 
                    </tbody>
                </table>
                <div id="tr_ProductDetailAddRow" class="text-center hide" style="padding-top: 10px">
                    <a class="btn btn-success" onclick="AddRow()">
                        <i class="glyphicon glyphicon-plus"></i> Add
                    </a>
                </div>
                <input type="hidden" class="form-control" id="paymentId" name="paymentId" value="${paymentId}" />
                <input type="hidden" class="form-control" id="counter" name="counter" value="${paymenthotelcount}" />
                <input type="hidden" class="form-control" id="ProductTourHotel" name="ProductTourHotel">  
                <input type="hidden" class="form-control" id="productList_id" name="productList_id" />
                <input type="hidden" name="productCountDel" id="productCountDel">
                <input type="hidden" name="plTableId" id="plTableId">
                <input type="hidden" value="${detail.createBy}" id="master-createBy">
            </div>
        </div><!--End Table --><br>
        <!-- Table Content -->
        <div class="panel panel-default">                    
            <div class="panel-body">
                <!--Row 1.1 -->
                <div class="row" style="padding-left: 25px;">
                    <div class="col-xs-1 text-right">
                        <label class="control-label">Remark</lable>
                    </div>
                    <div class="col-md-3 form-group text-left">
                        <input name="InputRemark" id="InputRemark" type="text" class="form-control" value="${requestScope['InputRemark']}" />           
                    </div>
                    <div class="col-xs-2 text-right">
                        <label class="control-label">Grand Total</lable>
                    </div>
                    <div class="col-md-2 form-group text-left">
                        <input name="InputGrandTotal" id="InputGrandTotal" type="text" class="form-control money" value="" readonly=""/>            
                    </div>
                </div>
                <!--Row 1.2 -->
                <div class="row" style="padding-left: 25px;">
                    <div class="col-xs-1 text-right">
                        <label class="control-label">Cash</lable>
                    </div>
                    <div class="col-md-2 form-group text-left">
                        <input name="InputCash" id="InputCash" type="text" class="form-control money number" value="${requestScope['InputCash']}" />           
                    </div>
                </div>
                <!--Row 1.3 -->
                <div class="row" style="padding-left: 25px;">
                    <div class="col-xs-1 text-right">
                        <label class="control-label">Chq No</lable>
                    </div>
                    <div class="col-md-2 form-group text-left">
                        <input maxlength="20" name="InputChqNo" id="InputChqNo" type="text" class="form-control" value="${requestScope['InputChqNo']}" />             
                    </div>
                    <div class="col-xs-3 text-right">
                        <label class="control-label">Chq Amount</lable>
                    </div>
                    <div class="col-md-2 form-group text-left">
                        <input name="InputChqAmount" id="InputChqAmount" type="text" class="form-control money number" value="${requestScope['InputChqAmount']}" />           
                    </div>
                    
                </div>
            </div>
        </div><!--End Table Content -->
        <!--Button -->
        <div class="row text-center" >
            <div class="col-xs-6 text-right">
                <c:if test="${requestScope['btnSave'] =='save'}">                   
                    <button type="submit" id="btnSave" name="btnSave" class="btn btn-success" onclick="">
                        <i class="fa fa-save"></i> Save             
                    </button>
                    <input type="hidden" name="action" id="action" value="add" class="form-control" >    
                </c:if>
                <c:if test="${requestScope['btnSave'] =='update'}">
                    <button type="submit" id="btnSave" name="btnSave" class="btn btn-success" onclick="">
                        <i class="fa fa-save"></i> Save             
                    </button>
                    <input type="hidden" name="action" id="action" value="update" class="form-control" >
                </c:if>    
            </div>
            <div class="col-xs-6 text-left">
                <button type="submit" id="btnNew" name="btnNew" onclick="" class="btn btn-primary">
                    <i class="glyphicon glyphicon-plus"></i> New
                </button>
            </div>                         
        </div><!--End Button -->
    </div>
            
    </form>
</div>
            
<select class="hidden" name="select_product_list" id="select_product_list">
    <c:forEach var="product" items="${product_list}" varStatus="status">                                
        <option  value="${product.id}">${product.name}</option>
    </c:forEach>
</select> 

<select class="hidden" name="select_currency_list" id="select_currency_list">
    <c:forEach var="currency" items="${currency_list}" varStatus="status">                                
        <option  value="${currency.id}">${currency.code}</option>
    </c:forEach>
</select> 
               
<!--Delete Payment Outbound Modal-->
<div class="modal fade" id="delSearchPaymentTourHotelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Payment Tour/Hotel</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
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
                <table class="display" id="SearchInvoicSupTable">
                    <thead class="datatable-header">
                        <script>
                            var invoiceSup = [];
                        </script>
                        <tr>
                            <th>Id</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th>AP code</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="invSup" items="${invoiceSup_list}">
                            <tr onclick ="setupInvSupValue('${invSup.id}', '${invSup.code}', '${invSup.name}', '${invSup.apcode}')" >
                                <td class="">${invSup.id}</td>
                                <td>${invSup.code}</td>
                                <td>${invSup.name}</td>
                                <td>${invSup.apcode}</td> 
                            </tr>
                            <script>
                                invoiceSup.push({id: "${invSup.id}", code: "${invSup.code}", name: "${invSup.name}", apcode "${invSup.apcode}"});
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
<!--Search A/P Code-->
<div class="modal fade" id="SearchAPCode" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">A/P </h4>
            </div>
            <div class="modal-body">
                <table class="display" id="SearchAPCodeTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr class="packet">
                                <td class="">XXX
                                <td>XXXXX</td> 
                            </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="SearchAPCodeOK" name="SearchAPCodeOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchAPCodeClose" name="SearchAPCodeClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->

<!--DELETE MODAL-->
<div class="modal fade" id="DeleteProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Master Package Detail</h4>
            </div>
            <div class="modal-body" id="delProduct">

            </div>
            <div class="modal-footer">
                <button type="submit" onclick="DeleteRowProduct()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>               
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
        
        $('#SearchInvoicSupTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": true,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
            });
            
        $('#SearchAPCodeTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": true,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
            });
                  
        $('#PaymentHotelTable tbody tr:last td .input-group-addon').click(function() {  
            AddRow(parseInt($("#counter").val()));
        });
        
        $("#PaymentHotelTable").on("keyup", function() {
            var rowAll = $("#PaymentHotelTable tr").length;
            $("td").keyup(function() {
                if ($(this).find("input").val() != '') {
                    var colIndex = $(this).parent().children().index($(this));
                    var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                    rowAll = $("#PaymentHotelTable tr").length;
                    //console.log('Row: ' + rowIndex + ', Column: ' + colIndex + ', All Row ' + rowAll);
                    if (rowIndex == rowAll) {
                        AddRow(parseInt($("#counter").val()));
                    }
                    if (rowAll < 2) {
                        $("#tr_ItineraryAddRow").removeClass("hide");
                        $("#tr_ItineraryAddRow").addClass("show");
                    }
                 }
            });
        });
   
        $(".money").mask('000,000,000,000,000,000', {reverse: true});
        
        $('#PaymentTourHotelForm').bootstrapValidator({
            container: 'tooltip',
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                InputInvoiceSupCode: {
                    validators: {
                        notEmpty: {
                            message: 'Invoice Sup is required'
                        }
                    }
                },
                InputAPCode: {
                    validators: {
                        notEmpty: {
                            message: 'A/P Code is required'
                        }
                    }
                },
                itemStatus: {
                    validators: {
                        notEmpty: {
                            message: 'Status is required'
                        }
                    }
                },
                account: {
                    validators: {
                        notEmpty: {
                            message: 'Account is required'
                        }
                    }
                }           
        }
        });
        
        $("#PaymentHotelTable").on("change", "select:last", function () {
            var row = parseInt($("#counter").val());
            AddRow(row);
        });
        
        $("#PaymentHotelTable").on('click', '.newRemCF', function () {
            $(this).parent().parent().remove();
                var rowAll = $("#PaymentHotelTable tr").length;
                if (rowAll < 2) {

                    $("#tr_ProductDetailAddRow").removeClass("hide");
                    $("#tr_ProductDetailAddRow").addClass("show");
            }
        });
        
        $("#tr_ProductDetailAddRow a").click(function () {
            $(this).parent().removeClass("show");
            $(this).parent().addClass("hide");
        });
        
        $("#InputPayNo").keyup(function (event) {
            if (event.keyCode === 13) {
                searchPaymentTour();
            }
        });
        
    });
    
    function searchPaymentTour() {
        var action = document.getElementById('action');
        var payNo = document.getElementById('InputPayNo').value;
        action.value = 'edit';
        document.getElementById('PaymentTourHotelForm').submit();
    }
    
    function setupInvSupValue(id,code,name,apcode){
        $('#SearchInvoiceSup').modal('hide');
        document.getElementById('InputInvoiceSupId').value = id;
        document.getElementById('InputInvoiceSupCode').value = code;
        document.getElementById('InputInvoiceSupName').value = name;
        document.getElementById('InputAPCode').value = apcode;
        document.getElementById('InputInvoiceSupCode').focus();
        $('#PaymentTourHotelForm').bootstrapValidator('revalidateField', 'InputInvoiceSupCode');
    }
    
    AddRow(parseInt($("#counter").val()));
                  
    function AddRow(row) {
        $("#PaymentHotelTable tbody").append(
                '<tr style="higth 100px">' +
                '<td class="hidden"> <input id="tableId' + row + '" name="tableId' + row + '"  type="hidden" >  </td>' +
                '<td>' + 
                '<select class="form-control" name="select-product' + row + '" id="select-product' + row + '" ><option value="">- - Product - -</option></select>' +                          
                '</td>' +
                '<td><input maxlength ="10" id="refNo' + row + '" name="refNo' + row + '"   type="text" class="form-control "></td>' +
                '<td><input maxlength ="15" id="invNo' + row + '" name="invNo' + row + '"   type="text" class="form-control "></td>' +
                '<td><input maxlength ="15" id="code' + row + '" name="code' + row + '"   type="text" class="form-control "></td>' +
                '<td>' +
                '<input type="radio" name="type' + row + '" id="typeT' + row + '" value="T"> T&nbsp;&nbsp;' +
                '<input type="radio" name="type' + row + '" id="typeC' +row + '" value="C" > C' +
                '</td>' +
                '<td><input class="money" id="amount' + row + '" name="amount' + row + '" type="text" onkeyup="CalculateGrandTotal(\'\', \''+row+'\')"></td>' +
                '<td>' + 
                '<select class="form-control" id="select-currency' + row + '" name="select-currency' + row + '"><option value="">- - Currency - -</option></select>' +                                 
                '</td>' +
                '<td><input class="form-control" maxlength="255" style="width: ${DescriptionSize}" id="description' + row + '" name="description' + row + '" rows="2" ></td>' +
                '<td><input id="ac' + row + '" name="ac' + row + '"   type="text" class="form-control "></td>' +
                '<td class="text-center">' +
                '<a class="remCF" onclick="deletelist(\'\', \''+row+'\')">  '+
                '<span id="SpanRemove' + row + '"class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
                '</tr>'
        );
        $("#select_product_list option").clone().appendTo("#select-product" + row);
        $("#select_currency_list option").clone().appendTo("#select-currency" + row);
        var tempCount = parseInt($("#counter").val()) + 1;
        $("#counter").val(tempCount);
      
    }
    
    function CalculateGrandTotal(id,row){
        var i;
        var result =0;
        if((id!==null) || (id!=='') ){
            for(i=0;i<row;i++){
                var amount = document.getElementById("amount" + i);

                if (amount !== null){
                    var value = amount.value;

                    if(value !== ''){
                        value = value.replace(/,/g,"");
                        var total = parseInt(value);
                        result += total;
                    }
                }    
            }
            document.getElementById('InputGrandTotal').value = result;
        }
    }    
     
    function deletelist(id,Ccount) {
        document.getElementById('plTableId').value = id;
        document.getElementById('productCountDel').value = Ccount;
        $("#delProduct").text('Are you sure delete this product ?');
        $('#DeleteProduct').modal('show');
    }
    
    function DeleteRowProduct(){
        var cCount = document.getElementById('productCountDel').value;
        var id = document.getElementById('plTableId').value;
        
        if(id === ''){
            $("#select-product" + cCount).parent().parent().remove();
            var rowAll = $("#PaymentHotelTable tr").length;
            if (rowAll <= 1) {
                $("#tr_ProductDetailAddRow").removeClass("hide");
                $("#tr_ProductDetailAddRow").addClass("show");
            }
            
        } else {
            $.ajax({
                url: 'PaymentTourHotel.smi?action=deleteProductDetail',
                type: 'get',
                data: {ProductDetail: id},
                success: function () {

                    $("#select-product" + cCount).parent().parent().remove();
                    var rowAll = $("#PaymentHotelTable tr").length;
                    if (rowAll <= 1) {
                        $("#tr_ProductDetailAddRow").removeClass("hide");
                        $("#tr_ProductDetailAddRow").addClass("show");
                    }

                },
                error: function () {
                    console.log("error");
                    result =0;
                }
            }); 
        }    
        $('#DeleteProduct').modal('hide');
    }
   
</script>