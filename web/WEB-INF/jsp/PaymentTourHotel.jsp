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
<c:set var="resultText" value="${requestScope['resultText']}" />
<c:set var="idRole" value="${requestScope['idRole']}" />
<c:set var="refNo_list" value="${requestScope['refNo_list']}" />
<c:set var="tourList" value="${requestScope['TourList']}" />

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
   
    <form action="PaymentTourHotel.smi" method="post" id="PaymentTourHotelForm" autocomplete="off" role="form" onsubmit="return validateForm()">
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
        <c:if test="${requestScope['resultText'] =='not found'}">
        <div id="textAlertDivFindNotFound"  style="" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <strong>Pay no not found.</strong> 
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
                        <input name="InputPayNo" id="InputPayNo" type="text" class="form-control" value="${requestScope['InputPayNo']}" maxlength="20"/>
                    </div>
                </div>
                <div class="col-xs-4 text-left" style="padding-left:10px;padding-right:0px;"></div>
                
                <c:choose>
                    <c:when test="${(idRole  == 22) || (idRole == 1)}">       
                    <div class="col-xs-1 text-left hidden" style="padding-left:10px;padding-right:0px;">
                        <label class="control-label">Account<font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-3  text-left hidden" style="padding-top : 5px;padding-left:0px;padding-right:0px;">
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
                            <input type="radio" name="account"  id="account2" value="2" ${check2}/>&nbsp;temp
                        </div>
                    </div>
                    </c:when>
                    <c:when test="${idRole  == 19}">       
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
                            <input type="radio" name="account"  id="account2" value="2" ${check2}/>&nbsp;temp
                        </div>
                    </div>
                    </c:when>     
                </c:choose>        
            </div>
        </div>
        <!--Row 2 -->
        <c:set var="readonly" value="" />
        <c:set var="disabled" value="" />
        <c:set var="star" value="" />
        <c:if test="${idRole  == 19}">
            <c:set var="readonly" value="readonly=\"\"" />
            <c:set var="disabled" value="disabled=\"\"" />
            <c:set var="star" value="*" />
        </c:if>
        <div class="row" style="padding-left: 0px">
            <div class="col-xs-12 ">
                <div class="col-xs-2 text-right" style="padding-left:0px;padding-right:0px;width:85px;">
                    <label class="control-label">Pay Date<font style="color: red">${star}</font></lable>
                </div>               
                <div class="col-md-2 form-group text-left" style="padding-left:28px">
                    <div class="col-sm-12">
                        <div class='input-group date' style="width:140px;">
                            <input name="InputPayDate" id="InputPayDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['InputPayDate']}"/>
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
                <div class="col-xs-2 text-right" style="padding-left:0px;padding-right:20px;">
                    <label class="control-label">PV Type</lable>
                </div>
                <div class="col-md-2 form-group text-left" style="padding-left:5px;padding-right:0px;">
                <c:choose>
                    <c:when test="${(idRole  == 22) || (idRole == 1)}">    
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
                    </c:when>
                    <c:when test="${(idRole  == 19)}">
                    <div class="col-sm-12">
                        <select name="itemPvTypeShow" id="itemPvTypeShow" class="form-control" disabled="">
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
                    <div class="hidden">
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
                    </c:when>
                </c:choose>    
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
                        <input name="InputInvoiceSupCode" id="InputInvoiceSupCode" type="text" class="form-control" value="${requestScope['InputInvoiceSupCode']}" onkeypress="getInvoiceSup()" style="text-transform:uppercase" ${readonly}/>                       
                        <c:choose>
                            <c:when test="${(idRole  == 22) || (idRole == 1)}">       
                                <span class="input-group-addon" data-toggle="modal" data-target="#SearchInvoiceSup">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </c:when>
                            <c:when test="${(idRole  == 19)}">
                                <span class="input-group-addon" data-toggle="modal">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </c:when>
                        </c:choose>
                    </div>    
                </div>   
            </div>
            <div class="col-md-4 form-group text-left" style="width:360px;">
                <div class="col-sm-12">
                    <input name="InputInvoiceSupName" id="InputInvoiceSupName" type="text" class="form-control" value="${requestScope['InputInvoiceSupName']}" readonly=""/>           
                </div>
            </div>
            <div class="col-xs-2 text-right" style="padding-left:10px;padding-right:0px;width:140px;">
                <label class="control-label">A/P Code<font style="color: red">*</font></lable>
            </div>
            <div class="col-md-2 form-group text-left" style="padding-left:9px;width:190px;">
                <div class="col-sm-12">
                    <div class="input-group" id="CodeValidate">
                        <input name="InputAPCode" id="InputAPCode" type="text" class="form-control" value="${requestScope['InputAPCode']}" readonly=""/>
                        <span class="input-group-addon hidden" data-toggle="modal" data-target="#SearchAPCode">
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
                    <textarea rows="3" cols="255" class="form-control" id="Detail" name="Detail" maxlength="255" ${readonly}>${requestScope['Detail']}</textarea>
                </div>   
            </div>
            <div class="col-xs-2 text-right" style="padding-left:10px;padding-right:0px;width:155px;">
                <label class="control-label">Payment</lable>
            </div>
            <div class="col-md-2 form-group text-left" style="padding-left:9px;width:190px;">
                <div class="col-sm-12">
                <c:choose>
                    <c:when test="${(idRole  == 22) || (idRole == 1)}">    
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
                    </c:when>
                    <c:when test="${(idRole  == 19)}">
                    <select class="form-control" id="itemPaymentShow" name="itemPaymentShow" disabled="">
                            <option id="" value="">---------</option>  
                            <c:forEach var="payment" items="${payment_list}">
                                <c:set var="select" value="" />
                                <c:if test="${payment.id == requestScope['itemPayment']}">
                                    <c:set var="select" value="selected" />
                                </c:if>
                                <option value="<c:out value="${payment.id}" />" ${select}><c:out value="${payment.name}" /></option>                                         
                            </c:forEach>
                    </select>    
                    <select class="form-control hidden" id="itemPayment" name="itemPayment">
                            <option id="" value="">---------</option>  
                            <c:forEach var="payment" items="${payment_list}">
                                <c:set var="select" value="" />
                                <c:if test="${payment.id == requestScope['itemPayment']}">
                                    <c:set var="select" value="selected" />
                                </c:if>
                                <option value="<c:out value="${payment.id}" />" ${select}><c:out value="${payment.name}" /></option>                                         
                            </c:forEach>
                    </select>
                    </c:when>
                </c:choose>    
                </div>
            </div>
            <div class="col-xs-1 text-right" style="padding-left:10px;padding-right:0px;width:155px;">
                <label class="control-label">Currency</lable>
            </div>    
            <div class="col-xs-1 form-group text-left" style="padding-left:25px;width:175px;">
            <c:choose>
                <c:when test="${(idRole  == 22) || (idRole == 1)}">
                <select class="form-control" name="InputCurrency" id="InputCurrency">
                    <option  value="">---------</option>
                    <c:forEach var="currency" items="${currency_list}" varStatus="status">
                        <c:set var="select" value="" />
                        <c:if test="${currency.code == requestScope['InputCurrency']}">
                            <c:set var="select" value="selected" />
                        </c:if>
                        <option value="<c:out value="${currency.code}" />" ${select}><c:out value="${currency.code}" /></option>       
                    </c:forEach>
                </select>
                </c:when>
                <c:when test="${(idRole  == 19)}">
                <select class="form-control" name="InputCurrencyShow" id="InputCurrencyShow" disabled="">
                    <option  value="">---------</option>
                    <c:forEach var="currency" items="${currency_list}" varStatus="status">
                        <c:set var="select" value="" />
                        <c:if test="${currency.code == requestScope['InputCurrency']}">
                            <c:set var="select" value="selected" />
                        </c:if>
                        <option value="<c:out value="${currency.code}" />" ${select}><c:out value="${currency.code}" /></option>       
                    </c:forEach>
                </select>    
                <select class="form-control hidden" name="InputCurrency" id="InputCurrency">
                    <option  value="">---------</option>
                    <c:forEach var="currency" items="${currency_list}" varStatus="status">
                        <c:set var="select" value="" />
                        <c:if test="${currency.code == requestScope['InputCurrency']}">
                            <c:set var="select" value="selected" />
                        </c:if>
                        <option value="<c:out value="${currency.code}" />" ${select}><c:out value="${currency.code}" /></option>       
                    </c:forEach>
                </select>    
                </c:when>
            </c:choose>    
            </div>     
        </div>
   
                
                
        <c:choose>
            <c:when test="${(idRole  == 22) || (idRole == 1)}">        
            <!-- Table Role Checking-->
            <div class="row" >
                <div class="col-12" style="width:1035px;padding-left:15px;">
                    <table class="display" id="PaymentHotelTable">
                        <thead class="datatable-header">
                            <tr>
                                <th class="hidden">Id</th>
                                <th style="width: 11%">Product</th>
                                <th style="width: 8%">Ref No</th>
                                <th style="width: 10%">Inv No</th>
                                <th style="width: 13%">Inv Date</th>
                                <th style="width: 8%">Type</th>
                                <th style="width: 11%">Amount</th>
                                <th style="width: 11%">Comm</th>
                                <th style="width: 11%">Description</th>
                                <th style="width: 7%">A/C</th>
                                <th style="width: 7%">Action</th>
                                <th class="hidden">Export Date</th>
                                <th class="hidden">Is Export</th>
                                <th class="hidden">Is Ex Inv</th>
                                <th class="hidden">Tour Id</th>
                                <th class="hidden">Tour Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pl" items="${productDetail_list}" varStatus="i">
                                <tr>
                                    <td class="hidden"><input id="tableId${i.count}" name="tableId${i.count}"  type="hidden" value="${pl.id}"></td>
                                    <td>                                   
                                        <select class="form-control" name="select-product${i.count}" id="select-product${i.count}" onchange="checkProduct('${i.count}')" onclick="onfocusTour('${i.count}')">
                                            <option  value="" >---------</option>
                                        <c:forEach var="product" items="${product_list}" varStatus="status">                                       
                                            <c:set var="select" value="" />
                                            <c:if test="${product.id == pl.MPaytype.id}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option  value="${product.id}" ${select}>${product.name}</option>
                                        </c:forEach>
                                        </select>                                                                  
                                    </td>
                                    <td> <input style="width: ${RefNo}" id="refNo${i.count}" name="refNo${i.count}" maxlength ="10"  type="text" class="form-control" value="${pl.master.referenceNo}" onfocusout="checkRefNo('${i.count}')" onfocus="onfocusTour('${i.count}')"> </td>
                                    <td> <input style="width: ${InvNo}" id="invNo${i.count}" name="invNo${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.invoiceCreditor}" onfocus="onfocusTour('${i.count}')">  </td>
                                    <td>
                                        <div class="input-group daydatepicker" id="daydatepicker${i.count}">
                                            <input type="text" name="invDate${i.count}" id="invDate${i.count}" class="form-control datemask" data-date-format="YYYY-MM-DD" value="${pl.invDate}" onfocus="onfocusTour('${i.count}')"/>
                                            <span class="input-group-addon spandate" style="padding : 1px 10px;"><span class="glyphicon-calendar glyphicon"></span></span>
                                        </div>
                                    </td>
                                    <td class="center">
                                        <c:set var="type1" value="" />
                                        <c:if test="${'T' == pl.amountType}">
                                            <c:set var="type1" value="checked" />
                                        </c:if>  
                                        <input type="radio" name="type${i.count}" id="typeT${i.count}" value="T" ${type1} onclick="onfocusTour('${i.count}')"> T&nbsp;
                                        <c:set var="type2" value="" />
                                        <c:if test="${'C' == pl.amountType}">
                                            <c:set var="type2" value="checked" />
                                        </c:if>  
                                        <input type="radio" name="type${i.count}" id="typeC${i.count}" value="C" ${type2} onclick="onfocusTour('${i.count}')"> C
                                    </td>
                                    <td class="hidden" align="center">
                                        <c:set var="vatChk" value="" />
                                        <c:if test="${'1' == pl.isVat}">
                                            <c:set var="vatChk" value="checked" />
                                        </c:if>  
                                        <input type="checkbox" id="isVat${i.count}" name="isVat${i.count}" value="check" ${vatChk}>
                                    </td>
                                    <td class="hidden">
                                        <input class="form-control" type="text" id="vat${i.count}" name="vat${i.count}" value="${pl.vat}" readonly="">
                                    </td>
                                    <td class="hidden">
                                        <input class="form-control" type="text" id="gross${i.count}" name="gross${i.count}" value="${pl.gross}" readonly="">
                                    </td>
                                    <td> <input style="width: ${Amount};text-align:right;"  id="amount${i.count}" name="amount${i.count}" maxlength ="15"  type="text" class="form-control numerical" onfocusout="CalculateGrandTotal('${pl.id}')" onfocus="onfocusTour('${i.count}')" onkeyup="insertCommas(this)" value="${pl.amount}"> </td>
                                    <td> <input style="width: ${recCom};text-align:right;"  id="recCom${i.count}" name="recCom${i.count}" maxlength ="15"  type="text" class="form-control numerical" onfocusout="calculateComm('${i.count}')" onfocus="onfocusTour('${i.count}')" onkeyup="insertCommas(this)" value="${pl.recCom}"> </td>                               
                                    <td> <input style="width: ${Description}" id="description${i.count}" name="description${i.count}" maxlength ="255"  type="text" class="form-control" value="${pl.description}" onfocus="onfocusTour('${i.count}')"> </td>
                                    <td> <input style="width: ${AC}" id="ac${i.count}" name="ac${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.accCode}" readonly=""> </td>
                                    <td class="text-center">
                                        <a href="#" onclick=""  data-toggle="modal" data-target="">
                                            <span id="editSpan${i.count}" class="glyphicon glyphicon-th-list" onclick="editlist('${pl.id}','${i.count}'); onfocusTour('${i.count}')"></span>
                                        </a>
                                        <a href="#" onclick=""  data-toggle="modal" data-target="">
                                            <span id="editTour${i.count}" onclick="editTour('${i.count}')" class="glyphicon glyphicon glyphicon-list-alt"></span>
                                        </a>    
                                        <a href="#" onclick=""  data-toggle="modal" data-target="">
                                            <span id="SpanRemove${i.count}" onclick="deletelist('${pl.id}','${i.count}'); onfocusTour('${i.count}')" class="glyphicon glyphicon-remove deleteicon "></span>
                                        </a>
                                    </td>
                                    <td class="hidden"> <input id="exportDate${i.count}" name="exportDate${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.exportDate}"> </td>
                                    <td class="hidden"> <input id="isExport${i.count}" name="isExport${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.isExport}"> </td>
                                    <td class="hidden"> <input id="isExInv${i.count}" name="isExInv${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.isExInv}"> </td>
                                    <td class="hidden"> <input id="tourId${i.count}" name="tourId${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.tourId}"> </td>
                                    <td class="hidden"> <input id="tourDate${i.count}" name="tourDate${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.tourDate}"> </td>
                                </tr>                       
                            </c:forEach> 
                        </tbody>
                    </table>
                    <div id="tr_ProductDetailAddRow" class="text-center hide" style="padding-top: 10px">
                        <a class="btn btn-success" onclick="AddRow()">
                            <i class="glyphicon glyphicon-plus"></i> Add
                        </a>
                    </div>
                </div>
            </div><!--End Table --><br>
            </c:when>
            <c:when test="${idRole  == 19}">
            <input type="hidden" id="vatDefaultData" name="vatDefaultData" value="${requestScope['vatDefaultData']}">    
            <!-- Table Role Account-->
            <div class="row" >
                <div class="col-12" style="width:1035px;padding-left:15px;">
                    <table class="display" id="PaymentHotelTable">
                        <thead class="datatable-header">
                            <tr>
                                <th class="hidden">Id</th>
                                <th style="width: 8%">Product</th>
                                <th style="width: 6%">Ref No</th>
                                <th style="width: 6%">Inv No</th>
                                <th style="width: 9%">Inv Date</th>
                                <th style="width: 2%">Type</th>
                                <th style="width: 5%" onclick="checkVatAll()"><u>Is vat</u></th>
                                <th style="width: 5%">Vat</th>
                                <th style="width: 11%">Gross</th>
                                <th style="width: 11%">Amount</th>
                                <th style="width: 11%">Comm</th>
                                <th style="width: 14%">Description</th>
                                <th style="width: 4%">A/C</th>
                                <th style="width: 1%">Action</th>
                                <th class="hidden">Export Date</th>
                                <th class="hidden">Is Export</th>
                                <th class="hidden">Tour Id</th>
                                <th class="hidden">Tour Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pl" items="${productDetail_list}" varStatus="i">
                                <tr>
                                    <td class="hidden"><input id="tableId${i.count}" name="tableId${i.count}"  type="hidden" value="${pl.id}"></td>                                                                                                                                                                           
                                    <td align="center">${pl.MPaytype.name}</td>
                                    <td class="hidden">                                   
                                        <select class="form-control" name="select-product${i.count}" id="select-product${i.count}" onchange="checkProduct('${i.count}')">
                                            <option  value="" >---------</option>
                                        <c:forEach var="product" items="${product_list}" varStatus="status">                                       
                                            <c:set var="select" value="" />
                                            <c:if test="${product.id == pl.MPaytype.id}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option  value="${product.id}" ${select}>${product.name}</option>
                                        </c:forEach>
                                        </select>                                                                  
                                    </td>
                                    <td align="center">${pl.master.referenceNo}</td>
                                    <td class="hidden"> <input style="width: ${RefNo}" id="refNo${i.count}" name="refNo${i.count}" maxlength ="10"  type="text" class="form-control" value="${pl.master.referenceNo}"> </td>
                                    <td align="center">${pl.invoiceCreditor}</td>
                                    <td class="hidden"> <input style="width: ${InvNo}" id="invNo${i.count}" name="invNo${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.invoiceCreditor}">  </td>
                                    <td align="center">${pl.invDate}</td>
                                    <td class="hidden"> <input id="invDate${i.count}" name="invDate${i.count}" type="text" class="form-control" value="${pl.invDate}">  </td>
                                    <td align="center">${pl.amountType}</td>
                                    <td class="hidden">
                                        <c:set var="type1" value="" />
                                        <c:if test="${'T' == pl.amountType}">
                                            <c:set var="type1" value="checked" />
                                        </c:if>  
                                        <input type="radio" name="type${i.count}" id="typeT${i.count}" value="T" ${type1}> T&nbsp;
                                        <c:set var="type2" value="" />
                                        <c:if test="${'C' == pl.amountType}">
                                            <c:set var="type2" value="checked" />
                                        </c:if>  
                                        <input type="radio" name="type${i.count}" id="typeC${i.count}" value="C" ${type2}> C
                                    </td>
                                    <td align="center">
                                        <c:set var="vatChk" value="" />
                                        <c:if test="${'1' == pl.isVat}">
                                            <c:set var="vatChk" value="checked" />
                                        </c:if>  
                                        <input type="checkbox" id="isVat${i.count}" name="isVat${i.count}" value="check" onclick="calculateGross('${i.count}')" ${vatChk}>
                                    </td>
                                    <td>
                                        <input class="form-control" type="text" id="vat${i.count}" name="vat${i.count}" value="${pl.vat}" readonly="">
                                    </td>
                                    <td>
                                        <input class="form-control" style="text-align:right;"  type="text" id="gross${i.count}" name="gross${i.count}" value="${pl.gross}" readonly="">
                                    </td>
                                    <td class="hidden">
                                        <input class="form-control" type="text" id="amountCal${i.count}" name="amountCal$${i.count}" value="${pl.amount}">
                                    </td>
                                    <td> <input style="width: ${Amount};text-align:right;" id="amount${i.count}" name="amount${i.count}" maxlength ="15"  type="text" class="form-control numerical" value="${pl.amount}" readonly=""> </td>
                                    <td> <input style="width: ${recCom};text-align:right;"  id="recCom${i.count}" name="recCom${i.count}" maxlength ="15"  type="text" class="form-control money2" value="${pl.recCom}" readonly=""> </td>
                                    <td>${pl.description}</td>
                                    <td class="hidden"> <input style="width: ${Description}" id="description${i.count}" name="description${i.count}" maxlength ="255"  type="text" class="form-control" value="${pl.description}"> </td>                                   
                                    <td align="center">${pl.accCode}</td>
                                    <td align="center">
                                        <a href="#" onclick=""  data-toggle="modal" data-target="">
                                            <span id="editSpan${i.count}" class="glyphicon glyphicon-th-list" onclick="editlist('${pl.id}','${i.count}')" ></span>
                                        </a>
                                        <a href="#" onclick=""  data-toggle="modal" data-target="">
                                            <span id="editTour${i.count}" onclick="editTour('${i.count}')" class="glyphicon glyphicon glyphicon-list-alt"></span>
                                        </a>   
                                    </td>    
                                    <td class="hidden"> <input style="width: ${AC}" id="ac${i.count}" name="ac${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.accCode}" readonly=""> </td>
                                    <td class="hidden"> <input id="exportDate${i.count}" name="exportDate${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.exportDate}"> </td>
                                    <td class="hidden"> <input id="isExport${i.count}" name="isExport${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.isExport}"> </td>
                                    <td class="hidden"> <input id="tourId${i.count}" name="tourId${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.tourId}"> </td>
                                    <td class="hidden"> <input id="tourDate${i.count}" name="tourDate${i.count}" maxlength ="15"  type="text" class="form-control" value="${pl.tourDate}"> </td>
                                </tr>                       
                            </c:forEach> 
                        </tbody>
                    </table>
                    <div id="tr_ProductDetailAddRow" class="text-center hide" style="padding-top: 10px">
                        <a class="btn btn-success" onclick="AddRow()">
                            <i class="glyphicon glyphicon-plus"></i> Add
                        </a>
                    </div>
                </div>
            </div><!--End Table --><br>    
            </c:when>
        </c:choose>
        
        <input type="hidden" class="form-control" id="paymentId" name="paymentId" value="${requestScope['paymentId']}" />
        <input type="hidden" class="form-control" id="counter" name="counter" value="${requestScope['paymenthotelcount']}" />
        <input type="hidden" class="form-control" id="payNo" name="payNo" value="${requestScope['payNo']}" />
        <input type="hidden" class="form-control" id="crateDate" name="crateDate" value="${requestScope['crateDate']}" />
        <input type="hidden" class="form-control" id="tourDescId" name="tourDescId" value="${requestScope['tourDescId']}" />
        <input type="hidden" class="form-control" id="isExport" name="isExport" value="${requestScope['isExport']}">  
        <input type="hidden" class="form-control" id="ProductTourHotel" name="ProductTourHotel">  
        <input type="hidden" class="form-control" id="productList_id" name="productList_id" />
        <input type="hidden" name="productCountDel" id="productCountDel">
        <input type="hidden" name="plTableId" id="plTableId">
        <input type="hidden" id="createBy" name="createBy" value="${requestScope['createBy']}">
        
        <!-- Table Content -->
        <div class="panel panel-default hidden" id="packagepanel">                    
            <div class="panel-body">
                <!--Row 1.1 -->
                <div class="row" style="padding-left: 25px;">
                    <div class="col-xs-1" style="width: 100px">
                        <label class="control-label">Tour Code</lable>
                    </div>
                    <div class="col-xs-1 form-group" style="width: 170px" id="codepanel"> 
                        <div class="input-group" >                      
                            <input name="tourCode" id="tourCode" type="text" class="form-control" value="" onkeypress="" style="text-transform:uppercase" ${readonly}/>                          
                            <span class="input-group-addon" data-toggle="modal" data-target="#SearchPackageCode">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                        </div>    
                    </div>
                    <div class="col-xs-1" style="width: 310px" id="namepanel">
                        <input name="tourName" id="tourName" type="text" class="form-control" value="" style="text-transform:uppercase" readonly=""/>
                    </div>
                    <div class="col-xs-1 form-group text-right" style="width: 120px">
                        <label class="control-label">Tour Date</lable>
                    </div>
                    <div class="col-xs-1 form-group" style="width: 170px">
                        <div class='input-group date' id="datepanel">
                            <input name="tourDate" id="tourDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value=""/>
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>                           
                    </div>
                    <div class="col-xs-1 form-group" style="width: 100px">
                         <div class='input-group'>
                            <button type="button" id="btnView" name="btnView" class="btn btn-primary" onclick="viewDayTour()">
                                <i class="fa fa-search"></i> View             
                            </button>
                        </div>       
                    </div>        
                    <div class="col-xs-1 hidden" style="width: 260px">
                        <input name="tourId" id="tourId" type="text" class="form-control" value="" />
                        <input name="tourRow" id="tourRow" type="text" class="form-control" value="" />
                    </div>  
                </div>
                <!--Row 1.2 -->
                <div class="row" style="padding-left: 25px;">
                    <div class="col-xs-1 text-right" style="width: 975px">
                         <button type="button" onclick="checkDaytour()" class="btn btn-success">OK</button>
                    </div>    
                </div>
            </div>
        </div><!--End Table Content -->
        
        <!-- Table Content -->
        <div class="panel panel-default">                    
            <div class="panel-body">
                <!--Row 1.1 -->
                <div class="row" style="padding-left: 25px;">
                    <div class="col-xs-1 text-right">
                        <label class="control-label">Remark</lable>
                    </div>
                    <div class="col-md-6 form-group text-left">
                        <textarea rows="3" cols="255" class="form-control" id="InputRemark" name="InputRemark"  maxlength="255">${requestScope['InputRemark']}</textarea>        
                    </div>
                    <c:choose>
                        <c:when test="${(idRole  == 22) || (idRole == 1)}">
                            <div class="col-xs-2 text-right">
                                <label class="control-label">Grand Total</lable>
                            </div>
                            <div class="col-md-2 form-group text-left">
                                <input name="InputGrandTotal" style="text-align: right;" id="InputGrandTotal" type="text" class="form-control " value="" readonly=""/>            
                            </div>         
                        </c:when>
                        <c:when test="${idRole  == 19}">
                            <div class="col-xs-2 text-right">
                                <label class="control-label" >Gross Total</lable>
                            </div>
                            <div class="col-md-2 form-group text-left">
                                <input name="InputGrossTotal" style="text-align: right;"  id="InputGrossTotal" type="text" class="form-control " value="" readonly=""/>            
                            </div>
                            <div class="col-xs-2 text-right">
                                <label class="control-label">Vat Total</lable>
                            </div>
                            <div class="col-md-2 form-group ">
                                <input name="InputVatTotal" style="text-align: right;" id="InputVatTotal" type="text" class="form-control " value="" readonly=""/>            
                            </div>                           
                            <div class="col-xs-9 text-right">
                                <label class="control-label">Grand Total</lable>
                            </div>
                            <div class="col-md-2 form-group text-left">
                                <input name="InputGrandTotal" style="text-align: right;"  id="InputGrandTotal" type="text" class="form-control " value="" readonly=""/>            
                            </div>  
                        </c:when>
                    </c:choose>                                 
                </div>
                <!--Row 1.2 -->
                <div class="row" style="padding-left: 25px;">
                    <div class="col-xs-1 text-right hidden">
                        <label class="control-label">Cash</lable>
                    </div>
                    <div class="col-md-2 form-group text-left hidden">
                        <input name="InputCash" id="InputCash" type="text" class="form-control money number" value="${requestScope['InputCash']}" />           
                    </div>
                </div>
                <!--Row 1.3 -->
                <div class="row" style="padding-left: 25px;">
                    <div class="col-xs-1 text-right hidden">
                        <label class="control-label">Chq No</lable>
                    </div>
                    <div class="col-md-2 form-group text-left hidden">
                        <input maxlength="20" name="InputChqNo" id="InputChqNo" type="text" class="form-control" value="${requestScope['InputChqNo']}" />             
                    </div>
                    <div class="col-xs-3 text-right hidden">
                        <label class="control-label">Chq Amount</lable>
                    </div>
                    <div class="col-md-2 form-group text-left hidden">
                        <input name="InputChqAmount" id="InputChqAmount" type="text" class="form-control money number" value="${requestScope['InputChqAmount']}" />           
                    </div>
                    
                </div>
            </div>
        </div><!--End Table Content -->
        <!--Button -->
        <div class="row text-center" >
            <div class="col-xs-6 text-right">
                                  
                <button type="submit" id="btnSave" name="btnSave" class="btn btn-success" onclick="" value="add">
                    <i class="fa fa-save"></i> Save             
                </button>
                <input type="hidden" name="action" id="action" value="add" class="form-control" >    
                
               
            </div>
            <div class="col-xs-6 text-left">
                <c:choose>
                    <c:when test="${(idRole  == 22) || (idRole == 1)}">
                    <button type="button" id="btnNew" name="btnNew" onclick="clearScreen()" class="btn btn-primary">
                        <i class="glyphicon glyphicon-plus"></i> New
                    </button>
                    </c:when>
                </c:choose>
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
                            <th class="hidden">Id</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th>AP code</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="invSup" items="${invoiceSup_list}">
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

<!--Tour Modal-->
<div class="modal fade" id="SearchPackageCode" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Tour</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="tourTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th style="width:20%">Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <script>
                        tourCode = [];
                        
                    </script>
                    <tbody>
                        <c:forEach var="tour" items="${tourList}">
                            <tr>
                                <td class="tour-id hidden">${tour.id}</td>
                                <td class="tour-code">${tour.code}</td>
                                <td class="tour-name">${tour.name}</td>
                            </tr>
                        <script>
                            tourCode.push({id: "${tour.id}", code: "${tour.code}", name: "${tour.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Script Daytour List table-->
            <script>
                $(document).ready(function () {
                    $("#tourTable tr").on('click', function () {//winit
                        $("#SearchPackageCode").modal('hide');
                        var tour_id = $(this).find(".tour-id").html();
                        var tour_code = $(this).find(".tour-code").html();
                        var tour_name = $(this).find(".tour-name").html();
                        $("#tourId").val(tour_id);
                        $("#tourCode").val(tour_code);
                        $("#tourName").val(tour_name);
                        $("#tourCode").focus();
                    });
                    
                    // tourTable
                    var tourTable = $('#tourTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": true,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });

                    $('#tourTable tbody').on('click', 'tr', function () {
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                        }
                        else {
                            tourTable.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                        }

                    });
                    // ON KEY INPUT AUTO SELECT TOURCODE-TOURNAME
                    $(function () {
                        var availableTags = [];

                        $.each(tourCode, function (key, value) {
                            availableTags.push(value.code);
                            if (!(value.name in availableTags)) {
                                availableTags.push(value.name);
                            }
                        });

                        $("#tourCode").autocomplete({
                            source: availableTags,
                            close: function (event, ui) {
                                $("#tourCode").trigger('keyup');
                            }
                        });


                        $("#tourCode").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var name = this.value;
                            var code = this.value.toUpperCase();
                            $("#tourName").val(null);
                            $.each(tourCode, function (key, value) {
                                if (name === value.name) {
                                    $("#tourCode").val(value.code);
                                    code = $("#tourCode").val().toUpperCase();
                                }
                                if (value.code.toUpperCase() === code) {
                                    $("#tourId").val(value.id);
                                    $("#tourName").val(value.name);
                                }
                            }); //end each tourCode
                        }); // end InputTourCode keyup
                    }); // end AutoComplete TourCode TourName

                });
            </script>
            <div class="modal-footer">
                <button id="" type="button" onclick="" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

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

<!--PACKAGE MODAL-->
<!--<div class="fade modal" id="DayTourModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Day Tour Operation</h4>
            </div>
            <div class="modal-body">
                <div class="col-xs-12 form-group">
                    <div class="col-xs-1 form-group" style="width: 100px">
                        <label class="control-label">Tour Code</lable>
                    </div>
                    <div class="col-xs-1 form-group" style="width: 170px" id="codepanel"> 
                        <div class="input-group" >                      
                            <input name="tourCode" id="tourCode" type="text" class="form-control" value="" onkeypress="" style="text-transform:uppercase" ${readonly}/>                          
                            <span class="input-group-addon" data-toggle="modal">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                        </div>    
                    </div>
                    <div class="col-xs-1" style="width: 260px" id="namepanel">
                        <input name="tourName" id="tourName" type="text" class="form-control" value="" style="text-transform:uppercase" readonly=""/>
                    </div>  
                </div>
                <div class="col-xs-12 form-group">
                    <div class="col-xs-1 form-group" style="width: 100px">
                        <label class="control-label">Tour Date</lable>
                    </div>
                    <div class="col-xs-1 form-group" style="width: 170px">
                        <div class='input-group date' id="datepanel">
                            <input name="tourDate" id="tourDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value=""/>
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>  
                </div>
                <div class="col-xs-1 hidden" style="width: 260px">
                    <input name="tourId" id="tourId" type="text" class="form-control" value="" />
                    <input name="tourRow" id="tourRow" type="text" class="form-control" value="" />
                </div>  
                <br>
                <br>
                <br>
                <br>
                <br>
            </div>
            <div class="modal-footer">
                <button type="submit" onclick="viewDayTour()" class="btn btn-primary">View</button>
                <button type="submit" onclick="checkDaytour()" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>               
            </div>
        </div> /.modal-content 
    </div> /.modal-dialog 
</div>-->

<!--Tour Code List-->                            
<!--<select class="hidden" id="select-list-city">
    <c:forEach var="tour" items="${tourList}" varStatus="status">                                                      
    <c:set var="select" value="" />
    <c:if test="${pass.id == requestScope['city']}">
        <c:set var="select" value="selected" />
    </c:if>
    <option value="<c:out value="${tour.id}" />" ${select}><c:out value="${tour.name}" /></option>    
    </c:forEach>
</select>                            -->


<!--<select class="form-control" id="city" name="city">
    <option value=""> -- tour code --</option>
</select>-->
    
<script type="text/javascript">
    $(document).ready(function () {
        //Select City
//        Selectize.define('clear_selection', function(options) {
//            var self = this;
//            self.plugins.settings.dropdown_header = {
//                title: 'Clear Selection'
//            };
//            this.require('dropdown_header');
//            self.setup = (function() {
//                var original = self.setup;
//                return function() {
//                    original.apply(this, arguments);
//                    this.$dropdown.on('mousedown', '.selectize-dropdown-header', function(e) {
//                        self.setValue('');
//                        self.close();
//                        self.blur();
//                        return false;
//                    });
//                };
//            })();
//        });
//
//        var dataCity = [];
//        dataCity = tourCode;
//        $("#select-list-city option").clone().appendTo("#city");
//
//        var nameCity = "#city";
//        console.log("name = " + nameCity);
//
//        $(nameCity).selectize({
//            removeItem: '',
//            sortField: 'text',
//            create: false,
//            dropdownParent: 'body',
//            plugins: {
//                'clear_selection': {}
//            }
//
//        });        
        
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });
        
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
   
        $(".money").mask('0000000000', {reverse: true});
        $(".money2").mask('000,000,000,000.0000', {reverse: true});
        
        
        $( ".numerical" ).on('input', function() { 
            var value=$(this).val().replace(/[^0-9.,]*/g, '');
            value=value.replace(/\.{2,}/g, '.');
            value=value.replace(/\.,/g, ',');
            value=value.replace(/\,\./g, ',');
            value=value.replace(/\,{2,}/g, ',');
            value=value.replace(/\.[0-9]+\./g, '.');
            $(this).val(value)
        });
        
        var idRole = '${idRole}';
        if(idRole === '19'){
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
                                message: 'Invoice Sup is required.'
                            }
                        }
                    },
                    InputAPCode: {
                        validators: {
                            notEmpty: {
                                message: 'A/P Code is required.'
                            }
                        }
                    },
                    itemStatus: {
                        validators: {
                            notEmpty: {
                                message: 'Status is required.'
                            }
                        }
                    },
                    account: {
                        validators: {
                            notEmpty: {
                                message: 'Account is required.'
                            }
                        }
                    }
                    ,InputPayDate: {
                        validators: {
                            notEmpty: {
                                message: 'Pay Date is required.'
                            }
                        }
                    }
                }
            });
        }else if((idRole === '22') || (idRole === '1')){
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
                                message: 'Invoice Sup is required.'
                            }
                        }
                    },
                    InputAPCode: {
                        validators: {
                            notEmpty: {
                                message: 'A/P Code is required.'
                            }
                        }
                    },
                    itemStatus: {
                        validators: {
                            notEmpty: {
                                message: 'Status is required.'
                            }
                        }
                    },
                    account: {
                        validators: {
                            notEmpty: {
                                message: 'Account is required.'
                            }
                        }
                    }
                }
            });
        }
        
        $(".daydatepicker").datetimepicker({
            pickTime: false
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

        AddRow((parseInt($("#counter").val())));
        
        $("#tr_ProductDetailAddRow a").click(function () {
            $(this).parent().removeClass("show");
            $(this).parent().addClass("hide");
        });
        
        $("#InputPayNo").keyup(function (event) {
            if (event.keyCode === 13) {
                $('#textAlertDivSave').modal('hide');
                searchPaymentTour();
            }
        });
        
        $('#InputGrandTotal').ready(function () {
            CalculateGrandTotal('');
        });
        
        $('#InputGrossTotal').ready(function () {
            CalculateGrossTotal('',$("#counter").val());
        });
        
        setEnvironment();
        
        var codeInvoiceSup = [];
        $.each(invoiceSup, function (key, value) {
            codeInvoiceSup.push(value.code);
            if ( !(value.name in codeInvoiceSup) ){
               codeInvoiceSup.push(value.name);        
            }
        });
        
        $("#InputInvoiceSupCode").autocomplete({
            source: codeInvoiceSup,
            close:function( event, ui ) {
               $("#InputInvoiceSupCode").trigger('keyup');
            }
        });
        
        $("#InputInvoiceSupCode").on('keyup', function () {
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var code = this.value.toUpperCase();
            var name = this.value;
            $("#InputInvoiceSupId,#InputInvoiceSupName,#InputAPCode").val(null);

            $.each(invoiceSup, function (key, value) {
                if (value.code.toUpperCase() === code) {           
                    $("#InputInvoiceSupId").val(value.id);
                    $("#InputInvoiceSupName").val(value.name);
                    $("#InputAPCode").val(value.apcode);
                }
                if(name === value.name){
                    $("#InputInvoiceSupCode").val(value.code);
                    $("#InputInvoiceSupId").val(value.id);
                    $("#InputInvoiceSupName").val(value.name);
                    $("#InputAPCode").val(value.apcode);
                    code = $("#InputInvoiceSupCode").val().toUpperCase();
                }
            });  
        });
        
        $("#InputInvoiceSupCode").on('blur', function () {
            var delay=500;//1 seconds
             setTimeout(function(){
               $.each(invoiceSup, function (key, value) {
                  //alert(value.code);
                    if($("#InputInvoiceSupCode").val() === value.code){
                        $("#InputInvoiceSupId").val(value.id);
                        $("#InputInvoiceSupCodeName").val(value.name);
                        $("#InputAPCode").val(value.apcode);
                    }     
                });   

            },delay); 

        });
        
//        ON KEY INPUT AUTO SELECT TOURCODE-TOURNAME
//        $(function () {
//            var availableTags = [];
//
//            $.each(tourCode, function (key, value) {
//                availableTags.push(value.code);
//                if (!(value.name in availableTags)) {
//                    availableTags.push(value.name);
//                }
//            });
//
//            $("#tourCode").autocomplete({
//                source: availableTags,
//                close: function (event, ui) {
//                    $("#tourCode").trigger('keyup');
//                }
//            });
//
//            $("#tourCode").keyup(function () {
//                var position = $(this).offset();
//                $(".ui-widget").css("top", position.top + 30);
//                $(".ui-widget").css("left", position.left);
//                $(".ui-widget").css("relative", position.fixed);
//                var name = this.value;
//                var code = this.value.toUpperCase();
//                $("#tourName").val(null);
//                $.each(tourCode, function (key, value) {
//                    if (name === value.name) {
//                        $("#tourCode").val(value.code);
//                        code = $("#tourCode").val().toUpperCase();
//                    }
//                   if (value.code.toUpperCase() === code) {
//                        $("#tourId").val(value.id);
//                        $("#tourName").val(value.name);
//                    }
//                }); //end each tourCode
//            }); // end InputTourCode keyup
//        }); // end AutoComplete TourCode TourName
        

        //Auto complete
//        var showflag = 1;
//        $("#tourCode").keyup(function(event) {
//            var position = $(this).offset();
//            $(".ui-widget").css("top", position.top + 30);
//            $(".ui-widget").css("left", position.left);
//            $(".ui-widget").css("relative", position.relative);
//            if ($(this).val() === "") {
//                $("#tourCode").val("");
//                $("#tourName").val("");
//                $("#tourId").val("");
//            } else {
//                if (event.keyCode === 13) {
//                    searchTourCodeAutoList(this.value);
//                }
//            }
//        });
//        $("#tourCode").keydown(function() {
//            var position = $(this).offset();
//            $(".ui-widget").css("top", position.top + 30);
//            $(".ui-widget").css("left", position.left);
//            if (showflag == 0) {
//                $(".ui-widget").css("top", -1000);
//                showflag = 1;
//            }
//        });

    });
    
    function setEnvironment(){
        var count = parseInt($("#counter").val());
        for(var i=0;i<=count;i++){
            var recCom = document.getElementById('recCom'+i);
            if((recCom!==null) && (recCom.value!=='')){
                recCom.value = formatNumber(parseFloat(recCom.value));
            }
        }
    }
    
    function searchPaymentTour() {
        var action = document.getElementById('action');
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
        $('#PaymentTourHotelForm').bootstrapValidator('revalidateField', 'InputAPCode');
    }
    
//    AddRow(parseInt($("#counter").val()));
                  
    function AddRow(row) {
        var idRole = '${idRole}';
        if((idRole === '22') || (idRole === '1')){                  
            $("#PaymentHotelTable tbody").append(
                '<tr style="higth 100px">' +
                '<td class="hidden"> <input id="tableId' + row + '" name="tableId' + row + '"  type="hidden" >  </td>' +
                '<td>' + 
                '<select class="form-control" name="select-product' + row + '" id="select-product' + row + '" onchange="checkProduct(\''+row+'\')" onclick="onfocusTour(\''+row+'\')"><option value="">---------</option></select>' +                          
                '</td>' +
                '<td><input maxlength ="10" id="refNo' + row + '" name="refNo' + row + '"   type="text" class="form-control " onfocusout="checkRefNo(\''+row+'\')" onfocus="onfocusTour(\''+row+'\')"></td>' +
                '<td><input maxlength ="15" id="invNo' + row + '" name="invNo' + row + '"   type="text" class="form-control " onfocus="onfocusTour(\''+row+'\')"></td>' +
                '<td>' +
                    '<div class="input-group daydatepicker" id="daydatepicker' + row + '">' +
                    '<input type="text" name="invDate' + row + '" id="invDate' + row + '" class="form-control datemask" data-date-format="YYYY-MM-DD" onfocus="onfocusTour(\''+row+'\')"/>' +
                    '<span class="input-group-addon spandate" style="padding : 1px 10px;" onclick="AddrowBySelect(\'' + row + '\')"><span class="glyphicon-calendar glyphicon"></span></span>' +
                    '</div>' +            
                '</td>' +
                '<td align="center">' +
                '<input type="radio" name="type' + row + '" id="typeT' + row + '" value="T" onclick="onfocusTour(\''+row+'\')"> T&nbsp;&nbsp;' +
                '<input type="radio" name="type' + row + '" id="typeC' +row + '" value="C" onclick="onfocusTour(\''+row+'\')"> C' +
                '</td>' +
                '<td><input maxlength ="15" class="form-control numerical"  style="text-align:right;" id="amount' + row + '" name="amount' + row + '"  align="right" type="text" onfocusout="CalculateGrandTotal(\'\')" onfocus="onfocusTour(\''+row+'\')" onkeyup="insertCommas(this)"></td>' +
                '<td><input maxlength ="15" class="form-control numerical"  style="text-align:right;" id="recCom' + row + '" name="recCom' + row + '"  align="right" type="text" onfocusout="calculateComm(\''+row+'\')" onfocus="onfocusTour(\''+row+'\')" onkeyup="insertCommas(this)"></td>' +
                '<td><input class="form-control" maxlength="255" id="description' + row + '" name="description' + row + '" rows="2" onfocus="onfocusTour(\''+row+'\')"></td>' +
                '<td><input id="ac' + row + '" name="ac' + row + '"   type="text" class="form-control" readonly=""></td>' +
                '<td class="text-center">' +
                    '<a href="#" onclick=""  data-toggle="modal" data-target=""> ' +
                        '<span id="editSpan' + row + '" class="glyphicon glyphicon-th-list" onclick="editlist(\'\',\''+ row + '\'); onfocusTour(\''+row+'\')" ></span>' +
                    '</a>' +
                    '<a href="#" onclick=""  data-toggle="modal" data-target=""> ' +
                        '<span id="editTour' + row + '" onclick="editTour(\'' + row + '\')" class="glyphicon glyphicon glyphicon-list-alt"></span>' +
                    '</a>' +    
                    '<a href="#" onclick=""  data-toggle="modal" data-target=""> '+
                        '<span id="SpanRemove' + row + '"class="glyphicon glyphicon-remove deleteicon" onclick="deletelist(\'\', \''+row+'\'); onfocusTour(\''+row+'\')"></span>' +
                    '</a>' +
                '</td>' +
                '<td class="hidden"> <input id="exportDate' + row + '" name="exportDate' + row + '" maxlength ="15"  type="text" class="form-control"></td>' +
                '<td class="hidden"> <input id="isExport' + row + '" name="isExport' + row + '" maxlength ="15"  type="text" class="form-control"> </td>' +
                '<td class="hidden"> <input id="isExInv' + row + '" name="isExInv' + row + '" maxlength ="15"  type="text" class="form-control"> </td>' +
                '<td class="hidden"> <input id="tourId' + row + '" name="tourId' + row + '" maxlength ="15"  type="text" class="form-control"> </td>' +
                '<td class="hidden"> <input id="tourDate' + row + '" name="tourDate' + row + '" maxlength ="15"  type="text" class="form-control"> </td>' +
                '</tr>'
            );
            $("#select_product_list option").clone().appendTo("#select-product" + row);
            var tempCount = parseInt($("#counter").val()) + 1;
            $("#counter").val(tempCount);
            reloadDatePicker();
        }
      
    }
    
    function reloadDatePicker() {
        try {
            $(".daydatepicker").datetimepicker({
                pickTime: false
            });
            $('span').click(function() {
                var position = $(this).offset();
                $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
            });
        } catch (e) {

        }
    }
    
    function AddrowBySelect(row) {
        var count = parseInt($("#counter").val());
        row = parseInt(row);
        if (row === (count-1)) {
            AddRow(count);
        }
    }
    
    function calculateComm(row){
        var comm = document.getElementById('recCom'+row);
        if(comm.value !== ''){
            document.getElementById('recCom'+row).value = formatNumber(parseFloat((comm.value).replace(/,/g,"")));
        }
    }
    
    function CalculateGrandTotal(id){
        var count = parseInt(document.getElementById('counter').value);
        var i;
        var grandTotal = 0;
        if((id!==null) || (id!=='') ){
            for(i=0;i<count+1;i++){
                var amount = document.getElementById("amount" + i);

                if (amount !== null){
                    var value = amount.value;
                    
                    if(value !== ''){
                        value = value.replace(/,/g,"");
                        var total = parseFloat(value);
                        grandTotal += total;
                        document.getElementById('amount' + i).value = formatNumber(total);
                    }
                }    
            }
            document.getElementById('InputGrandTotal').value = formatNumber(grandTotal);
            $( ".numerical" ).on('input', function() { 
                var value=$(this).val().replace(/[^0-9.,]*/g, '');
                value=value.replace(/\.{2,}/g, '.');
                value=value.replace(/\.,/g, ',');
                value=value.replace(/\,\./g, ',');
                value=value.replace(/\,{2,}/g, ',');
                value=value.replace(/\.[0-9]+\./g, '.');
                $(this).val(value);
            });
        }
    }
    
    function CalculateGrossTotal(id,row){
        var idRole = '${idRole}';
        if(idRole === '19'){ 
            var i;
            var grossTotal = 0;
            var vatTotal = 0;
            if((id!==null) || (id!=='') ){
                for(i=0;i<row+1;i++){
                    var gross = document.getElementById("gross" + i);
                    var vat = document.getElementById("vat" + i);
                    var amount = document.getElementById("amount" + i);

                    if (gross !== null){
                        var grossValue = gross.value;
                        var amountValue = amount.value;

                        if(grossValue !== ''){
                            grossValue = grossValue.replace(/,/g,"");
                            var total = parseFloat(grossValue);
                            grossTotal += total;
                            document.getElementById("gross" + i).value = formatNumber(total);

                            amountValue = amountValue.replace(/,/g,"");
                            var vatCal = amountValue - total;
                            vatTotal += vatCal;
                        }
                    }    
                }           
                document.getElementById('InputGrossTotal').value = formatNumber(grossTotal);
                document.getElementById('InputVatTotal').value = formatNumber(vatTotal);
            }
        }    
    }
    
    function formatNumber(num) {
        return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
    }   
    
//    function formatNumberComm(num) {
//        return  num.toFixed(4).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
//    }
    
    function insertCommas(nField){

        if (/^0/.test(nField.value)){
            nField.value = nField.value.substring(0,1);
        }
        if (Number(nField.value.replace(/,/g,""))){
            var tmp = nField.value.replace(/,/g,"");
            tmp = tmp.toString().split('').reverse().join('').replace(/(\d{3})/g,'$1,').split('').reverse().join('').replace(/^,/,'');
            if (/\./g.test(tmp)){
                tmp = tmp.split(".");
                tmp[1] = tmp[1].replace(/\,/g,"").replace(/ /,"");
                nField.value = tmp[0]+"."+tmp[1]
            } else {
                nField.value = tmp.replace(/ /,"");
            } 
        } else {
            nField.value = nField.value.replace(/[^\d\,\.]/g,"").replace(/ /,"");
        }
    }
    
    function checkProduct(row){
        var productField = document.getElementById('select-product'+row).style.borderColor;
        var productVal = document.getElementById('select-product'+row).value;
        if(productField === 'red'){
            if(productVal !== ''){
                document.getElementById('select-product'+row).style.borderColor = "";
            }
        }
    }
    
    function editlist(id,row){
        var refNoField = document.getElementById('refNo'+row).style.borderColor;
        var productVal = document.getElementById('select-product'+row).value;
        var product = $("#select-product"+row+" option:selected").text();
        var refNo = $("#refNo"+row).val();
        if((id !== '') && (refNo !== '') && (productVal !== '')){
            if(product === 'Others'){
                window.open("Other.smi?referenceNo="+refNo+"&action=edit");
            }else if(product === 'Land'){
                window.open("Land.smi?referenceNo="+refNo+"&action=edit");
            }else if(product === 'Hotel'){
                window.open("HotelBooking.smi?referenceNo="+refNo+"&action=edit");
            }else if(product === 'Day Tour'){
                window.open("Daytour.smi?referenceNo="+refNo+"&action=edit");
            }
        }else if((refNoField === 'green') && (productVal !== '')){           
            if(product === 'Others'){
                window.open("Other.smi?referenceNo="+refNo+"&action=edit");
            }else if(product === 'Land'){
                window.open("Land.smi?referenceNo="+refNo+"&action=edit");
            }else if(product === 'Hotel'){
                window.open("HotelBooking.smi?referenceNo="+refNo+"&action=edit");
            }else if(product === 'Day Tour'){
                window.open("Daytour.smi?referenceNo="+refNo+"&action=edit");
            }
        }else{
            if(productVal === ''){
                document.getElementById('select-product'+row).style.borderColor = "red";
            }
            if((refNo === '') || (refNoField === 'red')){
                document.getElementById('refNo'+row).style.borderColor = "red";
            }          
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
        CalculateGrandTotal('');
    }
   
   function getInvoiceSup(){
        $.each(invoiceSup, function (key, value) {

            if($("#InputInvoiceSupCode").val() == value.code){
                $("#InputInvoiceSupId").val(value.id);
                $("#InputInvoiceSupName").val(value.name);
                $("#InputAPCode").val(value.apcode);
            }     
        });  
    }
    
    function clearScreen(){
        var action = document.getElementById('action');
        action.value = 'new';
        document.getElementById('PaymentTourHotelForm').submit();
//        $('#paymentId, #InputPayNo, #InputPayDate, #itemPvType, #itemStatus, #InputInvoiceSupId, #InputInvoiceSupCode, #InputInvoiceSupName, #InputAPCode, #Detail, #itemPayment, #InputRemark, #InputGrandTotal, #InputCash, #InputChqNo, #InputChqAmount, #InputCurrency, #createBy').val('');
//        document.getElementById("account1").checked = false;
//        document.getElementById("account2").checked = false;
//        document.getElementById("InputGrandTotal").value = "0.00";
//        var count = document.getElementById("counter").value;
//        var i ;
//        for(i=0;i<=count;i++){
//           var select_product = document.getElementById("select-product" + i);
//           var select_currency = document.getElementById("select-currency" + i);
//           var refNo = document.getElementById("refNo"+i); 
//           var invNo = document.getElementById("invNo"+i); 
//           var code = document.getElementById("code"+i); 
//           var amount = document.getElementById("amount"+i); 
//           var description = document.getElementById("description"+i); 
//           var ac = document.getElementById("ac"+i);
//           var typeT = document.getElementById("typeT"+i);
//           var typeC = document.getElementById("typeC"+i);
//           var isVat = document.getElementById("isVat"+i);
//           var vat = document.getElementById("vat"+i);
//           var gross = document.getElementById("gross"+i);
//           
//           if(select_product !== null){
//               document.getElementById("select-product"+i).value = '';
//           }
//           if(select_currency !== null){
//               document.getElementById("select-currency"+i).value = '';
//           }
//           if(refNo !== null){
//               document.getElementById("refNo"+i).value = ''; 
//           }
//           if(invNo !== null){
//               document.getElementById("invNo"+i).value = ''; 
//           }
//           if(code !== null){
//               document.getElementById("code"+i).value = '';
//           }
//           if(amount !== null){
//               document.getElementById("amount"+i).value = '';
//           }
//           if(description !== null){
//               document.getElementById("description"+i).value = '';
//           }
//           if(ac !== null){
//               document.getElementById("ac"+i).value = '';
//           }
//           if((typeT !== null) && (typeC !== null)){
//               document.getElementById("typeT"+i).checked = false;
//               document.getElementById("typeC"+i).checked = false;
//           }
//           if((isVat !== null) && (isVat !== null)){
//               document.getElementById("isVat"+i).checked = false;
//           }
//           if(vat !== null){
//               document.getElementById("vat"+i).value = '';
//           }
//           if(gross !== null){
//               document.getElementById("gross"+i).value = '';
//           }            
//        }    
    }
    
    function calculateGross(row){
        var idRole = '${idRole}';
        if(idRole === '19'){ 
            var amount = document.getElementById('amountCal'+row).value;
            var gross = document.getElementById('gross'+row).value;
            var vat = document.getElementById('vat'+row).value;
            var vatDefaultData = parseFloat(document.getElementById('vatDefaultData').value);

            amount = amount.replace(/,/g,"");
            var grossTotal = parseFloat(amount);

            if((gross === '')){
                grossTotal = (amount*100)/(100+vatDefaultData);
                document.getElementById('gross'+row).value = formatNumber(grossTotal);
                document.getElementById('vat'+row).value = vatDefaultData;
            } else {
                document.getElementById('gross'+row).value = '';
                document.getElementById('vat'+row).value = '';
            }
            CalculateGrossTotal('',row);
        }    
    }  
    
    function checkRefNo(row){
        var idRole = '${idRole}';
        if((idRole === '22') || (idRole === '1')){ 
            var list = '${refNo_list}';
            var refNo = document.getElementById('refNo'+row).value;

            if(refNo===''){
                var refNoField = document.getElementById('refNo'+row);
                refNoField.style.borderColor = "";
                $("#btnSave").removeClass("disabled");
                return;
            }        

            list = list.replace("[","");
            list = list.replace("]","");
            list = list.replace(/ /g,"");
            
            var refNo_list = list.split(',');
            for(var i = 0;i<=refNo_list.length;i++){
               if(String(refNo) === String(refNo_list[i])){
                    var refNoField = document.getElementById('refNo'+row);
                    refNoField.style.borderColor = "Green";
                    $("#btnSave").removeClass("disabled");
                    return;
               } else {
                    var refNoField = document.getElementById('refNo'+row);
                    refNoField.style.borderColor = "Red";
                    $("#btnSave").addClass("disabled");
               }
            }
        }    
    }
    
    function validateForm(){
//        var count = document.getElementById('counter').value;
//        
//        for(var i=0;i<=count;i++){
//            var refNoField = document.getElementById('refNo'+i);
//            
//            if(refNoField !== null){
//                var color = document.getElementById('refNo'+i).style.borderColor;
//                if(color === "red"){
//                    return false;
//                }   
//            }
//        }
    }
    
    function checkVatAll(){
        var idRole = '${idRole}';
        if(idRole === '19'){ 
            var row = document.getElementById('counter').value;
            var check = 0;
            var unCheck = 0;
            for(var i=0;i<=row;i++){          
                var isVatCheck = document.getElementById("isVat"+i);
                if(isVatCheck !== null && isVatCheck !== ''){
                    if(document.getElementById("isVat"+i).checked){
                       check++;
                    } else {
                       unCheck++;
                    }
                }   
            }

            if(check > unCheck){
                for(var i=0;i<=row;i++){
                    var isVatCheck = document.getElementById("isVat"+i);
                    if(isVatCheck !== null && isVatCheck !== ''){
                        if(document.getElementById("isVat"+i).checked){
                        
                        } else { 
                            document.getElementById("isVat"+i).checked = true;
                            var amountChk = document.getElementById('amountCal'+i);
                            if(amountChk !== null && amountChk !== ''){
                                var amount = document.getElementById('amountCal'+i).value;
                                var gross = document.getElementById('gross'+i).value;
                                var vat = document.getElementById('vat'+i).value;
                                var vatDefaultData = parseFloat(document.getElementById('vatDefaultData').value);

                                amount = amount.replace(/,/g,"");
                                var grossTotal = parseFloat(amount);

                                if((gross === '')){
                                    grossTotal = (amount*100)/(100+vatDefaultData);
                                    document.getElementById('gross'+i).value = formatNumber(grossTotal);
                                    document.getElementById('vat'+i).value = vatDefaultData;
                                } else {
                                    document.getElementById('gross'+i).value = '';
                                    document.getElementById('vat'+i).value = '';
                                }
                            }
                        }    
                    }   
                }
            }
            
            if(check < unCheck){
                for(var i=0;i<=row;i++){
                    var isVatCheck = document.getElementById("isVat"+i);
                    if(isVatCheck !== null && isVatCheck !== ''){
                        document.getElementById("isVat"+i).checked = false;
                        document.getElementById("vat"+i).value = '';
                        document.getElementById("gross"+i).value = '';
                    }   
                }
            }
         
            if(check === 0 && unCheck !== 0){
                for(var i=0;i<=row;i++){
                    var isVatCheck = document.getElementById("isVat"+i);
                    if(isVatCheck !== null && isVatCheck !== ''){
                        if(document.getElementById("isVat"+i).checked){
                        
                        } else { 
                            document.getElementById("isVat"+i).checked = true;
                            var amountChk = document.getElementById('amountCal'+i);
                            if(amountChk !== null && amountChk !== ''){
                                var amount = document.getElementById('amountCal'+i).value;
                                var gross = document.getElementById('gross'+i).value;
                                var vat = document.getElementById('vat'+i).value;
                                var vatDefaultData = parseFloat(document.getElementById('vatDefaultData').value);

                                amount = amount.replace(/,/g,"");
                                var grossTotal = parseFloat(amount);

                                if((gross === '')){
                                    grossTotal = (amount*100)/(100+vatDefaultData);
                                    document.getElementById('gross'+i).value = formatNumber(grossTotal);
                                    document.getElementById('vat'+i).value = vatDefaultData;
                                } else {
                                    document.getElementById('gross'+i).value = '';
                                    document.getElementById('vat'+i).value = '';
                                }
                            }
                        }    
                    }    
                }
            } 
            
            if(check !== 0 && unCheck === 0){
                for(var i=0;i<=row;i++){
                    var isVatCheck = document.getElementById("isVat"+i);
                    if(isVatCheck !== null && isVatCheck !== ''){
                        document.getElementById("isVat"+i).checked = false;
                        document.getElementById("vat"+i).value = '';
                        document.getElementById("gross"+i).value = '';
                    }   
                }
            }
            
            if(check === unCheck){
                for(var i=0;i<=row;i++){
                    var isVatCheck = document.getElementById("isVat"+i);
                    if(isVatCheck !== null && isVatCheck !== ''){
                        if(document.getElementById("isVat"+i).checked){
                        
                        } else { 
                            document.getElementById("isVat"+i).checked = true;
                            var amountChk = document.getElementById('amountCal'+i);
                            if(amountChk !== null && amountChk !== ''){
                                var amount = document.getElementById('amountCal'+i).value;
                                var gross = document.getElementById('gross'+i).value;
                                var vat = document.getElementById('vat'+i).value;
                                var vatDefaultData = parseFloat(document.getElementById('vatDefaultData').value);

                                amount = amount.replace(/,/g,"");
                                var grossTotal = parseFloat(amount);

                                if((gross === '')){
                                    grossTotal = (amount*100)/(100+vatDefaultData);
                                    document.getElementById('gross'+i).value = formatNumber(grossTotal);
                                    document.getElementById('vat'+i).value = vatDefaultData;
                                } else {
                                    document.getElementById('gross'+i).value = '';
                                    document.getElementById('vat'+i).value = ''
                                }
                            }
                        }    
                    }    
                }             
            }
            
            CalculateGrossTotal('',$("#counter").val());   
        }
    }
    
    function onfocusTour(row){
        if(!$("#packagepanel").hasClass("hidden")){
            if(row === $("#tourRow").val()){
                var tourId = $("#tourId"+row).val();
                var tourDate = $("#tourDate"+row).val();
                if((tourId !== '') && (tourDate !== '')){
                    for(var i=0;i<tourCode.length;i++){
                        var id = tourCode[i].id;
                        if(tourId === id){
                            $("#tourRow").val(row);
                            $("#tourId").val(tourId);
                            $("#tourDate").val(tourDate);
                            $("#tourCode").val(tourCode[i].code);
                            $("#tourName").val(tourCode[i].name);
                            i = tourCode.length;
                        }    
                    }           
                }else{
                    $("#tourRow").val(row);
                    $("#tourId").val('');
                    $("#tourDate").val('');
                    $("#tourCode").val('');
                    $("#tourName").val('');
                }
                $("#codepanel").removeClass("has-error");
                $("#namepanel").removeClass("has-error");
                $("#datepanel").removeClass("has-error");
                $("#packagepanel").removeClass("hidden");
        //        $("#DayTourModel").modal("show");
            }else{
                $("#tourRow").val('');
                $("#packagepanel").addClass("hidden");
            }
        }
    }
    
    function editTour(row){
        if(row !== $("#tourRow").val()){
            var tourId = $("#tourId"+row).val();
            var tourDate = $("#tourDate"+row).val();
            if((tourId !== '') && (tourDate !== '')){
                for(var i=0;i<tourCode.length;i++){
                    var id = tourCode[i].id;
                    if(tourId === id){
                        $("#tourRow").val(row);
                        $("#tourId").val(tourId);
                        $("#tourDate").val(tourDate);
                        $("#tourCode").val(tourCode[i].code);
                        $("#tourName").val(tourCode[i].name);
                        i = tourCode.length;
                    }    
                }           
            }else{
                $("#tourRow").val(row);
                $("#tourId").val('');
                $("#tourDate").val('');
                $("#tourCode").val('');
                $("#tourName").val('');
            }
            $("#codepanel").removeClass("has-error");
            $("#namepanel").removeClass("has-error");
            $("#datepanel").removeClass("has-error");
            $("#packagepanel").removeClass("hidden");
    //        $("#DayTourModel").modal("show");
        }else{
            $("#tourRow").val('');
            $("#packagepanel").addClass("hidden");
        }
    }
    
    function viewDayTour(){
        var tourId = $("#tourId").val();
        var tourDate = $("#tourDate").val();
        if((tourId !== '') && (tourDate !== '')){
            $("#codepanel").removeClass("has-error");
            $("#namepanel").removeClass("has-error");
            $("#datepanel").removeClass("has-error");
            window.open("DaytourOperationDetail.smi?action=edit&tourID="+tourId+"&tourDate="+tourDate);
        }else{
            $("#codepanel").addClass("has-error");
            $("#namepanel").addClass("has-error");
            $("#datepanel").addClass("has-error");
        }
    }
    
    function checkDaytour(){
        var tourId = $("#tourId").val();
        var tourCode = $("#tourCode").val();
        var tourName = $("#tourName").val();
        var tourDate = $("#tourDate").val();
        var row = $("#tourRow").val();
        
        if((tourId !== '')&& (tourCode !== '') && (tourDate !== '')){
            var servletName = 'PaymentTourHotelServlet';
            var servicesName = 'AJAXBean';        
            var param = 'action=' + 'text' +
                    '&servletName=' + servletName +
                    '&servicesName=' + servicesName +
                    '&tourId=' + tourId +
                    '&tourCode=' + tourCode +
                    '&tourName=' + tourName +
                    '&tourDate=' + tourDate +
                    '&type=' + 'checkDayToursOperationDetail';
            CallAjaxCheck(param);
        }else{
//            $("#tourId").val('');
//            $("#tourCode").val('');
//            $("#tourName").val('');
//            $("#tourDate").val('');
//            $("#tourId"+row).val('');
//            $("#tourDate"+row).val('');
            if($("#tourCode").val() === ''){
                $("#codepanel").addClass("has-error");
                $("#namepanel").addClass("has-error");                
            }
            if($("#tourDate").val() === ''){
                $("#datepanel").addClass("has-error");
            }
//            $("#packagepanel").addClass("hidden");
//            $("#DayTourModel").modal("hide");
        }
        
    }
    
    function CallAjaxCheck(param) {
        var url = 'AJAXServlet';
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function(msg) {
                    if(msg === 'success'){
                        var row = $("#tourRow").val();
                        $("#tourId"+row).val($("#tourId").val());
                        $("#tourDate"+row).val($("#tourDate").val());
                        $("#codepanel").removeClass("has-error");
                        $("#namepanel").removeClass("has-error");
                        $("#datepanel").removeClass("has-error");
                        $("#packagepanel").addClass("hidden");
//                        $("#DayTourModel").modal("hide");
                    }else{                        
                        $("#codepanel").addClass("has-error");
                        $("#namepanel").addClass("has-error");
                        $("#datepanel").addClass("has-error");
                    }
                }, error: function(msg) {
                    console.log('auto ERROR');
                }
            });
        } catch (e) {
            alert(e);
        }
    }
    
    //Auto Complete
    function searchTourCodeAutoList(tourCode) {
        var servletName = 'PaymentTourHotelServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&tourCode=' + tourCode +
                '&type=' + 'getTourCodeAutoList';
        CallAjaxAuto(param);
    }
    
    function CallAjaxAuto(param) {
        var url = 'AJAXServlet';
        var billArray = [];
        var billListId = [];
        var billListName = [];
        var billListCode = [];
        var billid, billname, billcode;

        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                beforeSend: function() {
    //                  $("#dataload").removeClass("hidden");    
                },
                success: function(msg) {
                    var billJson = JSON.parse(msg);
                    for (var i in billJson) {
                        if (billJson.hasOwnProperty(i)) {
                            billid = billJson[i].id;
                            billname = billJson[i].name;
                            billcode = billJson[i].code;
                            billArray.push(billcode);
                            billArray.push(billname);
                            billListId.push(billid);
                            billListName.push(billname);
                            billListCode.push(billcode);
                        }
    //                        $("#dataload").addClass("hidden"); 
                    }
    //                   $("#InvTo_Id").val(billid);
                    $("#tourId").val(billid);
                    $("#tourName").val(billname);
                    $("#tourCode").val(billcode);
    //                   $("#InvToAddress").val(billaddr);

                    $("#tourCode").autocomplete({
                        source: billArray,
                        close: function() {
                            $("#tourCode").trigger("keyup");
                            var billselect = $("#tourCode").val();
                            for (var i = 0; i < billListId.length; i++) {
                                if ((billselect == billListName[i]) || (billselect == billListCode[i])) {
                                    $("#tourCode").val(billListCode[i]);
                                    $("#tourId").val(billListId[i]);
                                    $("#tourName").val(billListName[i]);
    //                                   $("#InvToAddress").val(billListAddress[i]);
                                }
                            }
                        }
                    });

                    var billval = $("#tourCode").val();
                    for (var i = 0; i < billListCode.length; i++) {
                        if (billval == billListName[i]) {
                            $("#tourCode").val(billListCode[i]);
                        }
                    }
                    if (billListCode.length == 1) {
                        showflag = 0;
                        $("#tourCode").val(billListCode[0]);
                    }
                    var event = jQuery.Event('keydown');
                    event.keyCode = 40;
                    $("#tourCode").trigger(event);

                }, error: function(msg) {
                    console.log('auto ERROR');
    //                   $("#dataload").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }
    
    function closePackagePanel(){
        $("#tourRow").val('');
        $("#tourId").val('');
        $("#tourDate").val('');
        $("#tourCode").val('');
        $("#tourName").val('');
        $("#codepanel").removeClass("has-error");
        $("#namepanel").removeClass("has-error");
        $("#datepanel").removeClass("has-error");
        $("#packagepanel").addClass("hidden");
    }
</script>
