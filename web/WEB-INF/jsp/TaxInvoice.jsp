<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/TaxInvoice.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="Type" value="${requestScope['Department']}" />
<c:set var="page" value="${requestScope['page']}" />
<c:set var="vatDefault" value="${requestScope['vatDefault']}" />
<c:set var="product_list" value="${requestScope['productList']}" />
<c:set var="currency_list" value="${requestScope['currencyList']}" />
<c:set var="customer_agent_list" value="${requestScope['customerAgentList']}"/>
<c:set var="refNo_list" value="${requestScope['refNo_list']}" />
<c:set var="taxInvoice" value="${requestScope['taxInvoice']}" />
<c:set var="taxInvoiceDetail" value="${requestScope['taxInvoiceDetail_list']}" />
<c:set var="roleName" value="${requestScope['roleName']}" />
<input type="hidden" id="Type" name="Type" value="${param.Department}">
<section class="content-header" >
    <h1>
        Finance & Cashier
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Tax Invoice</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/FinanceAndCashier/TaxInvoiceMainMenu.html'"></div>
    </div>
    <!--Content -->
    <div class="col-sm-10">
        <c:if test="${requestScope['result_text'] =='success'}">                                            
            <div id="textAlertDivSave"  style="" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Success!.</strong> 
            </div>
        </c:if>        
        <c:if test="${requestScope['result_text'] =='fail'}">
        <div id="textAlertDivNotSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <strong>Save Unsuccess!.</strong> 
        </div>
        </c:if>
        <c:if test="${requestScope['result_text'] =='not found'}">
        <div id="textAlertDivFindNotFound"  style="" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <strong>Tax invoice no not found!.</strong> 
        </div>
        </c:if>
        <c:if test="${taxInvoice.MFinanceItemstatus.id == '2'}">
        <div id="textAlertDivFindNotFound"  style="" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <strong>Tax invoice void!.</strong> 
        </div>
        </c:if>       
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6 " style="padding-right: 15px">
		<c:choose>
                    <c:when test="${fn:contains(page , 'W')}">
                        <h4><b>Tax Invoice Wendy</b></h4>
                    </c:when>
                    <c:when test="${fn:contains(page , 'O')}">
                        <h4><b>Tax Invoice Outbound</b></h4>
                    </c:when> 
                    <c:when test="${fn:contains(page , 'I')}">
                        <h4><b>Tax Invoice Inbound</b></h4>
                    </c:when> 
		</c:choose> 
            </div>
            <div class="col-xs-12 form-group"><hr/></div>
        </div>
        <form action="TaxInvoice${page}.smi" method="post" id="TaxInvoiceForm" role="form" autocomplete="off" onsubmit="return validateForm()">
        <!--Search Invoice-->
        <div class="row" style="padding-left: 15px">  
           <div role="tabpanel">
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane  active" id="infoSearchInvoice">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2 class="panel-title">
                                    <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" onclick="">
                                        <span id="SpanEdit">Search Invoice</span>
                                    </a>
                                    <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" style="margin-left: 54em" onclick="">
                                        <span id="arrowReservstion" class="arrowReservstion glyphicon glyphicon-chevron-up"></span> 
                                    </a>
                                </h2>               
                            </div>
                            <div class="panel-body">               
                                <div class=" accordion-body collapse in" id="collapseExample" aria-expanded="false">
                                    <div class="col-md-12">
                                        <div class="col-xs-1 text-right">
                                            <label class="control-label">Inv No </lable>
                                        </div>
                                        <div class="col-md-2 form-group" id="invoicenopanel">
                                            <div class="input-group">
                                                <input type="text" style="text-transform:uppercase" class="form-control" id="invoiceNo" name="invoiceNo" value="" onkeydown="invoiceNoValidate()">
                                            </div>
                                        </div>
                                        <div class="col-xs-1  text-right" style="width: 8px;padding-top: 7px"><i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i></div>
                                        <div class="col-md-1 text-right">
                                            <button type="button"  id="btnSearchInvoiceNo"  name="btnSearchInvoiceNo" onclick="searchInvoiceNo()" class="btn btn-primary btn-sm">
                                                <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                                            </button>                                          
                                        </div>      
                                    </div>
                                    <div class="col-xs-12 form-group"></div>
                                    <div class="row" style="padding-left:35px">    
                                        <div class="col-md-12">
                                            <table id="InvoiceListTable" class="display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr class="datatable-header">
                                                        <th style="width: 15%" >Product</th>
                                                        <th style="width: 50%">Description</th>
                                                        <th style="width: 20%">Amount</th>
                                                        <th style="width: 1%">Currency</th>
                                                        <th style="width: 1%">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>               
<!--                                                <tr>
                                                    <td>TEST</td>
                                                    <td>Hello World</td>
                                                    <td align="center">100000</td>
                                                    <td align="center">THB</td>
                                                    <td align="center" > 
                                                        <center> 
                                                            <a href=""><span class="glyphicon glyphicon-plus"></span></a>
                                                        </center>
                                                    </td>
                                                    </tr>-->
                                                </tbody>
                                            </table>    
                                        </div>
                                    </div>
                                    <div class="col-xs-12 form-group"><hr/></div>
                                </div>
                            </div>                                       
                        </div>                                       
                    </div>
                </div>
            </div>
            <div class="col-xs-12 form-group"></div>             
                <!--Search-->  
                
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Tax Invoice No</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <input type="hidden" class="form-control" id="department" name="department" value="${taxInvoice.department}"/>
                            <input type="hidden" class="form-control" id="TaxInvId" name="TaxInvId" value="${taxInvoice.id}"/>
                            <input type="hidden" class="form-control" id="TaxInvStatus" name="TaxInvStatus" value="${taxInvoice.MFinanceItemstatus.id}"/>
                            <input type="hidden" class="form-control" id="createDate" name="createDate" value="${requestScope['createDate']}"/>
                            <input type="hidden" class="form-control" id="createBy" name="createBy" value="${taxInvoice.createBy}"/>
                            <input type="text" style="text-transform:uppercase" class="form-control" id="TaxInvNo" name="TaxInvNo"  value="${taxInvoice.taxNo}" >
                        </div>
                        <div class="col-md-1" >
                            <button type="button"  id="btnSearchTaxInvoiceNo"  name="btnSearchTaxInvoiceNo" onclick="searchTaxInvoiceNo()" class="btn btn-primary btn-sm">
                                <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                            </button>
                        </div>
                        <div class="col-xs-2 text-right">
                            <label class="control-label" for="">Invoice date<font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <div class='input-group date' id='InputDatePicker'>
                            <c:if test='${taxInvoice.taxInvDate != null}'>
                                <input id="InvToDate" name="InvToDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['invToDate']}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>
                            <c:if test='${taxInvoice.taxInvDate == null}'>
                                <input id="InvToDate" name="InvToDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>                             
                            </div>               
                        </div>
                    </div>    
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Tax Inv To<font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-1 form-group" style="width: 585px">
                            <div class="input-group">                               
                                <input type="text" style="text-transform:uppercase" class="form-control" id="TaxInvTo" name="TaxInvTo" value="${taxInvoice.taxInvTo}" style="background-color: #ffffff">
                                <span class="input-group-addon" id="TaxInvTo_Modal"  data-toggle="modal" data-target="#TaxInvToModal">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Name </lable>
                        </div>    
                        <div class="col-md-1 form-group" style="width: 585px">
                            <input  type="text" id="InvToName" name="InvToName" class="form-control" value="${taxInvoice.taxInvName}">
                        </div>  
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Address </lable>
                        </div>
                        <div class="col-md-1 form-group" style="width: 585px">
                            <textarea  rows="3" cols="100" id="InvToAddress" name="InvToAddress" class="form-control" value="" >${taxInvoice.taxInvAddr}</textarea>
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left hidden">
                            <label class="control-label" for="">Passenger</label>
                        </div>                       
                        <div class="col-md-2 form-group hidden">
                            <div class="input-group">
                            <input type="hidden" class="form-control" id="PassengerId" name="PassengerId" value=""/>
                            <input type="text" class="form-control" id="PassengerCode" name="PassengerCode" value="" style="background-color: #ffffff">
                            <span class="input-group-addon" id="Passenger_Modal"  data-toggle="modal" data-target="#PassengerModal">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                            </div>
                        </div>
                        <div class="col-md-3 form-group hidden">
                            <input type="text"  class="form-control" id="PassengerName" name="PassengerName"  value="" readonly="">
                        </div>
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="" >A/R&nbsp;Code<font style="color: red">*</font></label>
                        </div>  
                        <div class="col-md-2 form-group" >
                            <input type="hidden" class="form-control" id="ARCodeId" name="ARCodeId" value=""/>
                            <input type="text" class="form-control" id="ARCode" name="ARCode" value="${taxInvoice.arCode}" style="background-color: #ffffff">                              
                        </div>
                    </div>    
                                
            </div>
                                            
            <div class="col-xs-12 form-group"></div>
            <div role="tabpanel">
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane  active" id="infoMasterProduct">
                        <div class="panel panel-default">                              
                            <div class="panel-body">
                                <div class="row" style="">    
                                    <div class="col-md-12">
                                        <input type="hidden" class="form-control" id="vatDefault" name="vatDefault" value="${vatDefault}"/>
                                        <input type="hidden" class="hidden" id="countTaxInvoice" name="countTaxInvoice" value="1" >
                                        <table id="TaxInvoiceTable" class="display" cellspacing="0" width="100%">
                                            <thead>
                                                <tr class="datatable-header">
                                                    <th style="width: 1%" class="hidden">Id</th>
                                                    <th style="width: 10%" >Product</th>
                                                    <th style="width: 10%" >Ref No</th>
                                                    <th style="width: 10%">Description</th>
                                                    <th style="width: 10%" >Cost</th>
                                                    <th style="width: 10%" >Cur</th>
                                                    <th style="width: 5%" onclick="checkVatAll()"><u>Is vat</u></th>
                                                    <th style="width: 10%" >Vat</th>
                                                    <th style="width: 10%" >Gross</th>
                                                    <th style="width: 10%">Amount</th>
                                                    <th style="width: 10%">Cur</th>
                                                    <th style="width: 4%">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="taxDetail" items="${taxInvoiceDetail}" varStatus="i">
                                                <tr>
                                                    <td class="hidden"><input class="form-control" type="text" id="taxDetailId${i.count}" name="taxDetailId${i.count}" value="${taxDetail.id}"></td>
                                                    <td class="hidden"><input class="form-control" type="text" id="invoiceDetailId${i.count}" name="invoiceDetailId${i.count}" value="${taxDetail.invoiceDetail.id}"></td>
                                                    <td>
                                                        <select class="form-control" name="product${i.count}" id="product${i.count}" onchange="AddrowBySelect('${i.count}')">
                                                            <option  value="" >---------</option>
                                                        <c:forEach var="product" items="${product_list}" varStatus="status">                                       
                                                            <c:set var="select" value="" />
                                                            <c:if test="${product.id == taxDetail.mbillType.id}">
                                                                <c:set var="select" value="selected" />
                                                            </c:if>
                                                            <option  value="${product.id}" ${select}>${product.name}</option>
                                                        </c:forEach>
                                                        </select>      
                                                    </td>
                                                    <td><input class="form-control" type="text" id="refNo${i.count}" name="refNo${i.count}" value="${taxDetail.master.referenceNo}" onfocusout="checkRefNo('${i.count}')"></td>
                                                    <td><input class="form-control" type="text" id="description${i.count}" name="description${i.count}" value="${taxDetail.description}"></td>
                                                    <td align="right"><input class="form-control numerical" style="text-align:right;" type="text" id="cost${i.count}" name="cost${i.count}" value="${taxDetail.cost}" onfocusout="CalculateAmountTotal()" onkeyup="insertCommas(this)"></td>
                                                    <td>
                                                        <select class="form-control" name="currencyCost${i.count}" id="currencyCost${i.count}" onchange="AddrowBySelect(${i.count})">
                                                            <option  value="" >---------</option>
                                                        <c:forEach var="currency" items="${currency_list}" varStatus="status">                                       
                                                            <c:set var="select" value="" />
                                                            <c:if test="${currency.code == taxDetail.curCost}">
                                                                <c:set var="select" value="selected" />
                                                            </c:if>
                                                            <option  value="${currency.code}" ${select}>${currency.code}</option>
                                                        </c:forEach>
                                                        </select>        
                                                    </td>
                                                    <td align="center">
                                                        <c:set var="vatChk" value="" />
                                                        <c:if test="${'1' == taxDetail.isVat}">
                                                            <c:set var="vatChk" value="checked" />
                                                        </c:if>  
                                                        <input type="checkbox" id="isVat${i.count}" name="isVat${i.count}" value="1" ${vatChk} onclick="CalculateGross('${i.count}')">
                                                    </td>
                                                    <td align="right" id="vatShow${i.count}">${taxDetail.vat}</td>
                                                    <td align="right"><input class="form-control numerical" style="text-align:right;" type="text" id="gross${i.count}" name="gross${i.count}" value="" readonly=""></td>
                                                    <td align="right"><input class="form-control numerical" style="text-align:right;" type="text" id="amount${i.count}" name="amount${i.count}" value="${taxDetail.amount}" onfocusout="CalculateAmountTotal('${i.count}')" onkeyup="insertCommas(this)"></td>
                                                    <td>
                                                        <select class="form-control" name="currencyAmount${i.count}" id="currencyAmount${i.count}" onchange="AddrowBySelect(${i.count})">
                                                            <option  value="" >---------</option>
                                                        <c:forEach var="currency" items="${currency_list}" varStatus="status">                                       
                                                            <c:set var="select" value="" />
                                                            <c:if test="${currency.code == taxDetail.curAmount}">
                                                                <c:set var="select" value="selected" />
                                                            </c:if>
                                                            <option  value="${currency.code}" ${select}>${currency.code}</option>
                                                        </c:forEach>
                                                        </select>        
                                                    </td>
                                                    <td class="text-center">
                                                        <a id="expenButtonRemove${i.count}" name="expenButtonRemove${i.count}" onclick="deleteTaxList('${taxDetail.id}','${i.count}')"  data-toggle="modal" data-target="#DeleteExpenModal">
                                                            <span id="expenSpanRemove${i.count}" name="expenSpanRemove${i.count}"  class="glyphicon glyphicon-remove deleteicon"></span>
                                                        </a>
                                                    </td>
                                                </tr>
                                                </c:forEach>    
                                            </tbody>
                                        </table>
                                        <div id="tr_TaxInvoiceDetailAddRow" class="text-center hide" style="padding-top: 10px">
                                            <a class="btn btn-success" onclick="AddRowTaxInvoiceTable()">
                                                <i class="glyphicon glyphicon-plus"></i> Add
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                        
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="infoRemark">
                            <div class="panel panel-default">                              
                                <div class="panel-body">
                                    <div class="col-xs-12 ">
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Remark&nbsp;</lable>                                         
                                        </div>
                                        <div class="col-sm-6" style="padding-left: 50px">
                                            <textarea  rows="3" cols="200" id="Remark" name="Remark" class="form-control" value="">${taxInvoice.remark}</textarea>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 form-group"></div>
                                    <div class="col-xs-12 ">
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Text&nbsp;Amount&nbsp;:</lable>                                         
                                        </div>                                      
                                        <div class="col-sm-6" style="padding-left: 50px">
                                            <textarea rows="3" cols="200" id="TextAmount" name="TextAmount" class="form-control" readonly=""></textarea>
<!--                                            <input  rows="3" cols="200" id="TextAmount" name="TextAmount" class="form-control" value="" readonly="">-->
                                        </div>
                                        <div class="col-sm-1" style="width: 110px">
                                            <label class="control-label" for="">Total&nbsp;Amount&nbsp;:</lable>                                         
                                        </div>
                                        <div class="col-sm-1" style="width: 285px">
                                            <input style="text-align: right" id="TotalAmount" name="TotalAmount" class="form-control" value="" readonly="">
                                        </div>
                                    </div>    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="infoButton">
                            <div class="panel panel-default">                              
                                <div class="panel-body">
                                    <div class="col-xs-12 ">                                       
                                        <div class="col-md-1 text-right " style="width: 200px">
                                            <select class="form-control" name="select_print" id="select_print">
                                                <option  value="">--- Select Print ---</option>
                                                <option  value="taxInvoice">Tax Invoice</option>
                                                <option  value="taxInvoiceEmail">Tax Invoice Email</option>
                                            </select>
                                        </div>
                                        <div class="col-md-1 text-left ">
                                            <c:set var="print" value="" />
                                            <c:if test="${(taxInvoice.id == '') || (taxInvoice.id == null) }">        
                                                <c:set var="print" value="disabled='true'" />
                                            </c:if>
                                            <button type="button" onclick="selectPrintType()" class="btn btn-default" ${print}>
                                                <span id="SpanPrintInvoiceNew" class="glyphicon glyphicon-print"></span> Print
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right" style="width: 170px">
                                            <c:set var="sendemail" value="" />
                                            <c:if test="${(taxInvoice.id == '') || (taxInvoice.id == null) }">        
                                                <c:set var="sendemail" value="disabled='true'" />
                                            </c:if>
                                            <button type="button" class="btn btn-default" onclick="selectEmailType()" ${sendemail}>
                                                <span id="buttonEmail" class="glyphicon glyphicon-send" ></span> SendEmail 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-left" style="width: 170px">
                                            <c:set var="copy" value="" />
                                            <c:if test="${(taxInvoice.id == '') || (taxInvoice.id == null) }">        
                                                <c:set var="copy" value="disabled='true'" />
                                            </c:if>
                                            <button type="button" class="btn btn-default" onclick="copyTaxInvoice()" ${copy}>
                                                <span id="ButtonCopy" class="glyphicon glyphicon-copyright-mark" ></span> Copy 
                                            </button>
                                        </div>
                                        <div class="col-md-1 " style="width: 180px"></div>
                                        <div class="col-md-2 text-right">
                                            <c:set var="isDisableVoid" value="disabled='true'" />
                                            <c:set var="isEnableVoid" value="style='display: none;'" />
                                            <c:set var="isSaveVoid" value="" />
                                            <c:if test="${result =='success'}">        
                                                <c:set var="isDisableVoid" value="" />
                                            </c:if>
                                            <c:if test="${result =='void'}">        
                                                <c:set var="isDisableVoid" value="style='display: none;'" />
                                                <c:if test="${roleName =='YES'}">        
                                                    <c:set var="isEnableVoid" value="" />
                                                    <c:set var="isSaveVoid" value="disabled='true'" />
                                                </c:if>
                                                <c:if test="${roleName =='NO'}">        
                                                    <c:set var="isEnableVoid" value="disabled='true'" />
                                                    <c:set var="isSaveVoid" value="disabled='true'" />
                                                </c:if>
                                            </c:if>
                                            <c:if test="${taxInvoice.MFinanceItemstatus.id == '2'}">        
                                                <c:set var="isDisableVoid" value="style='display: none;'" />
                                                <c:if test="${roleName =='YES'}">        
                                                    <c:set var="isEnableVoid" value="" />
                                                    <c:set var="isSaveVoid" value="disabled='true'" />
                                                </c:if>
                                                <c:if test="${roleName =='NO'}">        
                                                    <c:set var="isEnableVoid" value="disabled='true'" />
                                                    <c:set var="isSaveVoid" value="disabled='true'" />
                                                </c:if>
                                            </c:if>
                                            <c:if test="${result =='cancelvoid'}">        
                                                <c:set var="isDisableVoid" value="" />
                                            </c:if>
                                            <c:if test="${taxInvoice.MFinanceItemstatus.id == '1'}">        
                                                <c:set var="isDisableVoid" value="" />
                                            </c:if>
                                            <button type="button" class="btn btn-primary" onclick="EnableVoidInvoice();" data-toggle="modal" data-target="#EnableVoid" id="enableVoidButton" name="enableVoidButton"  ${isEnableVoid} >
                                                <span id="SpanEnableVoid" class="glyphicon glyphicon-ok" ></span> Cancel Void
                                            </button>
                                            
                                            <button type="button" class="btn btn-danger" onclick="DisableVoidInvoice();" data-toggle="modal" data-target="#DisableVoid" id="disableVoidButton" name="disableVoidButton" ${isDisableVoid} >
                                                <span id="SpanDisableVoid" class="glyphicon glyphicon-remove" ></span> Void
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-left ">
                                            <button type="submit" onclick="" class="btn btn-success" ${isSaveVoid}>
                                                <span id="SpanSave" class="fa fa-save"></span> Save 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-left ">
                                            <button type="button" onclick="clearScreen()" class="btn btn-success" >
                                                <span id="SpanNew" class="fa fa-plus-circle"></span> New 
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
            <input type="hidden" id="action" name="action" value="save">
            </form>
    </div>

<!--Disable Modal-->
<div class="modal fade" id="DisableVoid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Disable Void Invoice</h4>
            </div>
            <div class="modal-body" id="disableVoidModal">
                Are you confirm to void invoice ${taxInvoice.taxNo}?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick='DisableInvoice()' data-dismiss="modal">Cancel Void</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--Enable Modal-->
<div class="modal fade" id="EnableVoid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Finance & Cashier - Invoice</h4>
            </div>
            <div class="modal-body" id="enableVoid">
                Are you confirm to cancel void invoice ${taxInvoice.taxNo}?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal" onclick='Enable()'>Enable</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->                                                
                                            
<!--Modal Search Tax Inv To-->
<div class="modal fade" id="TaxInvToModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Tax Invoice To</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="SearchTaxInvoiceToTable">
                    <thead class="datatable-header">
                        <script>
                            var taxInvoiceTo = [];
                        </script>
                        <tr>
                            <th style="width: 30%">Code</th>
                            <th style="width: 70%">Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customerAgent" items="${customer_agent_list}">
                            <tr onclick ="setupCustomerAgentValue('${customerAgent.billTo}', '${customerAgent.billName}', '${customerAgent.address}')">
                                <td>${customerAgent.billTo}</td>
                                <td>${customerAgent.billName}</td>
                            </tr>
                            <script>
                                taxInvoiceTo.push({name: "${customerAgent.billTo}", billTo: "${customerAgent.billName}", address: "${customerAgent.address}"});
                            </script>
                            </tr>    
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="SearchToModal" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Modal Search Passenger -->
<div class="modal fade" id="PassengerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Passenger</h4>
            </div>
            <div class="modal-body">
                <!--Passenger List Table-->
                <table class="display" id="PassengerTable">
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
                <div class="text-right">
                    <button id="SearchPassengerModal" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--A/R Code Modal-->
<div class="modal fade" id="ARCodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">A/R Code</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->

                <table class="display" id="ARCodeTable">
                    <thead>    
                        <script>
                            var arcode = [];
                        </script>
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                        </tr>                      
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${agent_list}">
                            <tr onclick ="setupagentvalue('${table.id}', '${table.code}', '${table.name}')" >
                                <td class="hidden">${table.id}</td>
                                <td>${table.code} </td>
                                <td>${table.name} </td>
                            </tr>    
                            <script>
                                arcode.push({id: "${table.id}", code: "${table.code}", name: "${table.name}"});
                            </script>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div  class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>  

<!--Delete Tax Invoice Modal-->
<div class="modal fade" id="delTaxInvoiceDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <c:choose>
                    <c:when test="${fn:contains(page , 'W')}">
                        <h4 class="modal-title">Delete Tax Invoice Wendy</h4>
                    </c:when>
                    <c:when test="${fn:contains(page , 'O')}">
                        <h4 class="modal-title">Delete Tax Invoice Outbound</h4>
                    </c:when> 
                    <c:when test="${fn:contains(page , 'I')}">
                        <h4 class="modal-title">Delete Tax Invoice Inbound</h4>
                    </c:when> 
		</c:choose>                
            </div>
            <div class="modal-body" id="delCode">
                
            </div>
            <input class="hidden" id="delTaxDetailId" name="delTaxDetailId" value="">
            <input class="hidden" id="delTaxDetailRow" name="delTaxDetailRow" value="">
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="deleteTaxInvoiceDetailList()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--Print Modal-->
<div class="modal fade" id="PrintTaxInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Print Tax Invoice</h4>
            </div>
            <div class="modal-body" id="printReceiptModal" >
                <div class="col-xs-1" style="width: 280px">
                    <label class="text-right">Select option for print tax invoice<font style="color: red">*</font></label>                                    
                </div>
                <div class="col-xs-1" style="width: 200px" >
                    <select name="optionPrint" id="optionPrint" class="form-control" style="height:34px">
                        <option value="1" >Not Show Description</option>
                        <option value="2" >Show Description</option>
                        <option value="3" >Print Format Package Tour</option>
                    </select>
                </div>
                </br>
                </br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="printTaxInvoice()"  data-dismiss="modal">
                    <span id="buttonPrint" class="glyphicon glyphicon-print" ></span> Print 
                </button>          
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!--Copy Tax Invoice Modal-->
<div class="modal fade" id="CopyTaxInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Copy Tax Invoice</h4>
            </div>
            <div class="modal-body" id="copyReceiptModal" >
                <label class="text-right">Are you sure to copy tax invoice ?</label>                                  
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-default" onclick="confirmCopyTaxInvoice()">
                    <span id="buttonCopyTaxInvoice" class="glyphicon glyphicon-copyright-mark" ></span> Copy
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!--Email Modal-->
<div class="modal fade" id="EmailTaxInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Send Email Tax Invoice</h4>
            </div>
            <div class="modal-body" id="printReceiptModal" >
                <div class="col-xs-1" style="width: 320px">
                    <label class="text-right">Select option for send email tax invoice<font style="color: red">*</font></label>                                    
                </div>
                <div class="col-xs-1" style="width: 200px" >
                    <select name="optionsend" id="optionsend" class="form-control" style="height:34px">
                        <option value="1" >Not Show Description</option>
                        <option value="2" >Show Description</option>
                        <option value="3" >Print Format Package Tour</option>
                    </select>
                </div>
                </br>
                </br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="sendEmailTaxInvoice()"  data-dismiss="modal">
                    <span id="buttonPrint" class="glyphicon glyphicon-send" ></span> SendEmail 
                </button>          
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<select class="form-control hidden" name="select_product_list" id="select_product_list">
    <c:forEach var="product" items="${product_list}" varStatus="status">                                              
        <option  value="${product.id}">${product.name}</option>
    </c:forEach>
</select>

<select class="form-control hidden" name="select_currency_list" id="select_currency_list">
    <c:forEach var="currency" items="${currency_list}" varStatus="status">                                       
        <option  value="${currency.code}">${currency.code}</option>
    </c:forEach>
</select>        

<script language="javascript">
    $(document).ready(function () {
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
        $(".money").mask('000,000,000.00', {reverse: true});
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        
        $('#PassengerTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": true,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 3
        });
        
        $('#SearchTaxInvoiceToTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });
        
        $('#ARCodeTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });
                   
        $(".numerical").on('input', function() { 
            var value=$(this).val().replace(/[^0-9.,]*/g, '');
            value=value.replace(/\.{2,}/g, '.');
            value=value.replace(/\.,/g, ',');
            value=value.replace(/\,\./g, ',');
            value=value.replace(/\,{2,}/g, ',');
            value=value.replace(/\.[0-9]+\./g, '.');
            $(this).val(value);
        });
        
        $('#collapseExample').on('shown.bs.collapse', function () {
            $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-down").addClass("glyphicon glyphicon-chevron-up");
        });

        $('#collapseExample').on('hidden.bs.collapse', function () {
           $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-up").addClass("glyphicon glyphicon-chevron-down");
        });
        
        $('#TaxInvoiceForm').bootstrapValidator({
            container: 'tooltip',
            excluded: [':disabled'],
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                TaxInvTo: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'The Tax Invoice No. is required'
                        }
                    }
                },
                ARCode: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'The A/R Code is required'
                        }
                    }
                },
                InvToDate: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'The Invoice Date is required'
                        }
                    }
                }
            }
        });
        
        $("#TaxInvNo").keyup(function (event) {
            if (event.keyCode === 13) {
                searchTaxInvoiceNo();
            }
        });
        
        $("#invoiceNo").keyup(function (event) {
            if (event.keyCode === 13) {
                searchInvoiceNo();
            }
        });
        
        var rowTaxInvoiceTable = $("#TaxInvoiceTable tr").length;
        AddRowTaxInvoiceTable(rowTaxInvoiceTable);
        
        $("#TaxInvoiceTable").on("keyup", function () {
            var rowAll = $("#TaxInvoiceTable tr").length;
            $("td").keyup(function () {
                if ($(this).find("input").val() !== '') {
                    var colIndex = $(this).parent().children().index($(this));
                    var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                    rowAll = $("#TaxInvoiceTable tr").length;
//                    alert("rowIndex = "+rowIndex);
//                    alert("rowAll = "+rowAll);
                    if (rowIndex == rowAll) {
                        console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                        AddRowTaxInvoiceTable(rowAll);
                    }
                    if (rowAll < 2) {
                        $("#tr_TaxInvoiceDetailAddRow").removeClass("hide");
                        $("#tr_TaxInvoiceDetailAddRow").addClass("show");
                    }
                }
            });
        });
        
        //autocomplete
        $("#TaxInvTo").keyup(function(event){ 
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left); 
            if($(this).val() === ""){
                $("#TaxInvTo").val("");
                $("#InvToName").val("");
                $("#InvToAddress").val("");
                $("#ARCode").val("");
            }else{
                if(event.keyCode === 13){
                    searchCustomerAgentAutoList(this.value); 
                }
            }
        });
        
        $("#TaxInvTo").keydown(function(){
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left); 
            if(showflag == 0){
                $(".ui-widget").css("top", -1000);
                showflag=1;
            }
        });
        
        $('#TotalAmount').ready(function () {
            CalculateAmountTotal();
            setGross();
        });
    });
    
    function selectPrintType(){
        var printType = document.getElementById('select_print').value;
        if(printType !== ""){
            $("#PrintTaxInvoiceModal").modal("show");
        }       
    }  
    
    function printTaxInvoice(){
        $("#PrintTaxInvoiceModal").modal("hide");
        var printType = document.getElementById('select_print').value;
        var taxInvId = document.getElementById('TaxInvId').value;
        var optionPrint =  document.getElementById('optionPrint').value;
        var department = '${page}';

        if("W" === department){
            department = "Wendy";
        } else if("O" === department){
            department = "Outbound";
        } else if("I" === department){
            department = "Inbound";
        }
        
        if(printType === ""){
            alert("Please choose print type.");
        }else if(printType === "taxInvoice"){
            window.open("report.smi?name=TaxInvoiceReport&taxInvId="+taxInvId+"&department="+department+"&optionPrint="+optionPrint);
        }else if(printType === "taxInvoiceEmail"){
            window.open("report.smi?name=TaxInvoiceEmailReport&taxInvId="+taxInvId+"&department="+department+"&optionPrint="+optionPrint);
        }
    }
    
    function copyTaxInvoice(){
        var invDate = document.getElementById("InvToDate").value;
        if(invDate !== ''){
            $("#CopyTaxInvoiceModal").modal("show");
        }        
    }
    
    function confirmCopyTaxInvoice(){
        var row = $('#TaxInvoiceTable tr').length;
        document.getElementById('TaxInvId').value = '';
        document.getElementById('TaxInvNo').value = '';
        document.getElementById('createBy').value = '';
        document.getElementById('createDate').value = '';
        for(var i=1;i<row;i++){
            document.getElementById('taxDetailId' + i).value = '';
        }
        $("#CopyTaxInvoiceModal").modal("hide");
        document.getElementById('TaxInvoiceForm').submit();           
    }
    
    function selectEmailType(){
        $("#EmailTaxInvoiceModal").modal("show");      
    }  
    
    function sendEmailTaxInvoice(){
        $("#EmailTaxInvoiceModal").modal("hide");
        var taxInvId = document.getElementById('TaxInvId').value;
        var optionsend = document.getElementById('optionsend').value;
        window.open("SendMail.smi?reportname=TaxInvoiceEmail&reportid="+taxInvId+"&optionsend="+optionsend);    
    }
    
    function searchTaxInvoiceNo(){
        var action = document.getElementById('action');
        action.value = 'search';
        document.getElementById('TaxInvoiceForm').submit();
    }
    
    function setupCustomerAgentValue(billTo,billName,address){      
        document.getElementById('TaxInvTo').value = billTo;
        document.getElementById('InvToName').value = billName;
        document.getElementById('ARCode').value = billTo;
        if(address === null){
            document.getElementById('InvToAddress').value = '';
        } else {
            document.getElementById('InvToAddress').value = address;
        }              
        $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'TaxInvTo');
        $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'ARCode');
        $('#TaxInvToModal').modal('hide');
    }
    
    function formatNumber(num) {
        return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
    }
    
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
    
    function searchCustomerAgentAutoList(billTo){
        var servletName = 'BillableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&name=' + billTo +
                '&type=' + 'getAutoListBillto';
        CallAjaxAuto(param);
    }
    
    function CallAjaxAuto(param){
        var url = 'AJAXServlet';
        var billArray = [];
        var billListId= [];
        var billListName= [];
        var billListAddress= [];
        var billid , billname ,billaddr;
        $("#TaxInvTo").autocomplete("destroy");
        try {
            $.ajax({
               type: "POST",
               url: url,
               cache: false,
               data: param,
               beforeSend: function() {
                  $("#dataload").removeClass("hidden");    
               },
               success: function(msg) {     
                   var billJson =  JSON.parse(msg);
                   for (var i in billJson){
                       if (billJson.hasOwnProperty(i)){
                           billid = billJson[i].id;
                           billname = billJson[i].name;
                           billaddr = billJson[i].address;
                           billArray.push(billid);
                           billArray.push(billname);
                           billListId.push(billid);
                           billListName.push(billname);
                           billListAddress.push(billaddr);
                       }                 
                        $("#dataload").addClass("hidden"); 
                   }
                   $("#InvTo_Id").val(billid);
                   $("#ARCode").val(billid);
                   $("#InvToName").val(billname);
                   $("#InvToAddress").val(billaddr);

                   $("#TaxInvTo").autocomplete({
                       source: billArray,
                       close: function(){
                            $("#TaxInvTo").trigger("keyup");
                            var billselect = $("#TaxInvTo").val();
                            for(var i =0;i<billListId.length;i++){
                                if((billselect==billListName[i])||(billselect==billListId[i])){      
                                   $("#TaxInvTo").val(billListId[i]);
                                   $("#ARCode").val(billListId[i]);
                                   $("#InvToName").val(billListName[i]);
                                   $("#InvToAddress").val(billListAddress[i]);
                                }                 
                            }   
                       }
                    });

                   var billval = $("#TaxInvTo").val();
                   for(var i =0;i<billListId.length;i++){
                       if(billval==billListName[i]){
                           $("#TaxInvTo").val(billListId[i]);
                       }
                   }
                   if(billListId.length == 1){
                       showflag = 0;
                       $("#TaxInvTo").val(billListId[0]);
                   }
                   var event = jQuery.Event('keydown');
                   event.keyCode = 40;
                   $("#TaxInvTo").trigger(event);

                }, error: function(msg) {
                   console.log('auto ERROR');
                   $("#dataload").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }
    
    function searchInvoiceNo(){
        var invoiceNo = $("#invoiceNo").val();
        var invoicenopanel = $("#invoicenopanel").val();
        var department = '${page}';
        if("W" === department){
            department = "Wendy";
        } else if("O" === department){
            department = "Outbound";
        } else if("I" === department){
            department = "Inbound";
        }
        if(invoiceNo === ""){
            if(!$('#invoicenopanel').hasClass('has-feedback')) {
                $('#invoicenopanel').addClass('has-feedback');
            }
            $('#invoicenopanel').removeClass('has-success');
            $('#invoicenopanel').addClass('has-error');
        } else {
            var servletName = 'TaxInvoiceServlet';
            var servicesName = 'AJAXBean';
            var param = 'action=' + 'text' +
                    '&servletName=' + servletName +
                    '&servicesName=' + servicesName +
                    '&invoiceNo=' + invoiceNo +
                    '&department=' + department +
                    '&type=' + 'searchInvoiceNo';
            CallAjaxSearchInvoice(param);
        }  
    }
    
    function CallAjaxSearchInvoice(param) {
        var url = 'AJAXServlet';
        $("#ajaxload").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function (msg) {
                    try { 
                        if(msg == "null"){
                            $('#InvoiceListTable').dataTable().fnClearTable();
                            $('#InvoiceListTable').dataTable().fnDestroy();                            
                            document.getElementById("TaxInvTo").value = '';
                            document.getElementById("InvToName").value = '';
                            document.getElementById("InvToAddress").value = '';
                            document.getElementById("ARCode").value = '';
                            document.getElementById("InvToDate").value = '';
                            
                        }else{
                            $('#InvoiceListTable').dataTable().fnClearTable();
                            $('#InvoiceListTable').dataTable().fnDestroy();
                            $("#InvoiceListTable tbody").empty().append(msg);
                            
                            if(document.getElementById("receiveTaxInvTo")!==null && ($("#receiveTaxInvTo").val()!==undefined)){
                                document.getElementById("TaxInvTo").value = $("#receiveTaxInvTo").val();
                            } else {
                                document.getElementById("TaxInvTo").value = '';
                            }
                            if((document.getElementById("InvToName")!==null) && ($("#receiveInvToName").val()!==undefined)){
                                document.getElementById("InvToName").value = $("#receiveInvToName").val();
                            } else {
                                document.getElementById("InvToName").value = '';
                            }
                            
                            if((document.getElementById("InvToAddress")!==null) && ($("#receiveInvToAddress").val()!==undefined)){
                                document.getElementById("InvToAddress").value = $("#receiveInvToAddress").val();
                            } else {
                               document.getElementById("InvToAddress").value = ''; 
                            }
                            
                            if((document.getElementById("receiveARCode")!==null) && ($("#receiveARCode").val()!==undefined)){
                                document.getElementById("ARCode").value = $("#receiveARCode").val();
                            } else {
                                document.getElementById("ARCode").value = '';
                            }
                            
//                            if((document.getElementById("receiveInvToDate")!==null) && ($("#receiveInvToDate").val()!==undefined)){
//                                document.getElementById("InvToDate").value = $("#receiveInvToDate").val();
//                            } else {
//                                document.getElementById("InvToDate").value = '';
//                            }                          
                        }
                        $("#ajaxload").addClass("hidden");

                    } catch (e) {
                        alert(e);
                    }

                }, error: function (msg) {
                     $("#ajaxload").addClass("hidden");
                }
            });
        } catch (e) {
            alert('error');
        }
    }   
    
    function invoiceNoValidate(){
        $('#invoicenopanel').removeClass('has-feedback');
        $('#invoicenopanel').addClass('has-success');
        $('#invoicenopanel').removeClass('has-error');  
    }
    
    function AddRowTaxInvoiceTable(row) {
        if (!row) {
            row = 1;
        }
        $("#TaxInvoiceTable tbody").append(           
            '<tr>' +
            '<td class="hidden"><input class="form-control" type="text" id="taxDetailId' + row + '" name="taxDetailId' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailId' + row + '" name="invoiceDetailId' + row + '" value=""></td>' +
            '<td><select class="form-control" name="product' + row + '" id="product' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
            '<td><input class="form-control" type="text" id="refNo' + row + '" name="refNo' + row + '" value="" onfocusout="checkRefNo(\'' + row + '\')"></td>' +
            '<td><input class="form-control" type="text" id="description' + row + '" name="description' + row + '" value=""></td>' +
            '<td><input class="form-control numerical" style="text-align:right;" type="text" id="cost' + row + '" name="cost' + row +'" value="" onfocusout="CalculateAmountTotal()" onkeyup="insertCommas(this)"></td>' +
            '<td><select class="form-control" name="currencyCost' + row + '" id="currencyCost' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
            '<td align="center"><input type="checkbox" id="isVat' + row + '" name="isVat' + row + '" value="1" onclick="CalculateGross(\'' + row + '\')"></td>' +
            '<td align="right" id="vatShow' + row + '"></td>' +
            '<td><input class="form-control numerical" style="text-align:right;" type="text" id="gross' + row + '" name="gross' + row + '" value="" readonly=""></td>' +
            '<td><input class="form-control numerical" style="text-align:right;" type="text" id="amount' + row + '" name="amount' + row + '" value="" onfocusout="CalculateAmountTotal(\'' + row + '\')" onkeyup="insertCommas(this)"></td>' +
            '<td><select class="form-control" name="currencyAmount' + row + '" id="currencyAmount' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
            '<td>' + 
                '<center>' +
                '<a id="expenButtonRemove' + row + '" name="expenButtonRemove' + row + '" onclick="deleteTaxList(\'\',\'' + row + '\')"  data-toggle="modal" data-target="#DeleteExpenModal">' + 
                '<span id="expenSpanEdit' + row + '" name="expenSpanEdit' + row + '" class="glyphicon glyphicon-remove deleteicon"></span>' +
                '</a>' + 
                '</center>' +
            '</td>' +
            '</tr>'           
        );
        $("#tr_TaxInvoiceDetailAddRow").removeClass("show");
        $("#tr_TaxInvoiceDetailAddRow").addClass("hide");
        $("#select_product_list option").clone().appendTo("#product" + row);
        $("#select_currency_list option").clone().appendTo("#currencyCost" + row);
        $("#select_currency_list option").clone().appendTo("#currencyAmount" + row);
        $("#countTaxInvoice").val(row);
        
    }
    
    function AddrowBySelect(row){
        var rowTable = $('#TaxInvoiceTable tr').length;
        row = parseInt(row) + 1;
        if(row === rowTable){
           AddRowTaxInvoiceTable(rowTable); 
        }       
    }
    
    function AddProduct(id,product,description,cost,curCost,amount,curAmount,isVat){
        var count = $("#countTaxInvoice").val();
        var row = $("#TaxInvoiceTable tr").length;
        AddDataRowProduct(row,count,id,product,description,cost,curCost,amount,curAmount,isVat);

    }
    
    function AddDataRowProduct(row,count,id,product,description,cost,curCost,amount,curAmount,isVat) {
        if (!row) {
            row = 1;
        }

        $("#invoiceDetailId" + count).val(id);
        $('[name=product' + count + '] option').filter(function() { 
            return ($(this).text() === product);
        }).prop('selected', true);
        $("#description" + count).val(description);
        $("#cost" + count).val(formatNumber(parseFloat(cost)));
        $('[name=currencyCost' + count + '] option').filter(function() { 
            return ($(this).text() === curCost);
        }).prop('selected', true);
        $("#amount" + count).val(formatNumber(parseFloat(amount)));
        $('[name=currencyAmount' + count + '] option').filter(function() { 
            return ($(this).text() === curAmount);
        }).prop('selected', true);       
        if (isVat === '1'){
            $('#isVat'+count).prop('checked', true);
            var vatData = parseFloat($("#vatDefault").val());
            document.getElementById('vatShow'+count).innerHTML = formatNumber(vatData);
            CalculateGross(count);
        }
      
        var tempCount = parseInt($("#countTaxInvoice").val()) + 1;
        
        $("#TaxInvoiceTable tbody").append(           
            '<tr>' +
            '<td class="hidden"><input class="form-control" type="text" id="taxDetailId' + row + '" name="taxDetailId' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailId' + row + '" name="invoiceDetailId' + row + '" value=""></td>' +
            '<td><select class="form-control" name="product' + row + '" id="product' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
            '<td><input class="form-control" type="text" id="refNo' + row + '" name="refNo' + row + '" value="" onfocusout="checkRefNo(\'' + row + '\')"></td>' +
            '<td><input class="form-control" type="text" id="description' + row + '" name="description' + row + '" value=""></td>' +
            '<td><input class="form-control numerical" style="text-align:right;" type="text" id="cost' + row + '" name="cost' + row +'" value="" onfocusout="CalculateAmountTotal()" onkeyup="insertCommas(this)"></td>' +
            '<td><select class="form-control" name="currencyCost' + row + '" id="currencyCost' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
            '<td align="center"><input type="checkbox" id="isVat' + row + '" name="isVat' + row + '" value="1" onclick="CalculateGross(\'' + row + '\')"></td>' +
            '<td align="right" id="vatShow' + row + '"></td>' +
            '<td><input class="form-control numerical" style="text-align:right;" type="text" id="gross' + row + '" name="gross' + row + '" value="" readonly=""></td>' +
            '<td><input class="form-control numerical" style="text-align:right;" type="text" id="amount' + row + '" name="amount' + row + '" value="" onfocusout="CalculateAmountTotal(\'' + row + '\')" onkeyup="insertCommas(this)"></td>' +
            '<td><select class="form-control" name="currencyAmount' + row + '" id="currencyAmount' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
            '<td>' + 
                '<center>' +
                '<a id="expenButtonRemove' + row + '" name="expenButtonRemove' + row + '" onclick="deleteTaxList(\'\',\'' + row + '\')"  data-toggle="modal" data-target="#DeleteExpenModal">' + 
                '<span id="expenSpanEdit' + row + '" name="expenSpanEdit' + row + '" class="glyphicon glyphicon-remove deleteicon"></span>' +
                '</a>' + 
                '</center>' +
            '</td>' +
            '</tr>'           
        );
        $("#tr_TaxInvoiceDetailAddRow").removeClass("show");
        $("#tr_TaxInvoiceDetailAddRow").addClass("hide");
        $("#select_product_list option").clone().appendTo("#product" + row);
        $("#select_currency_list option").clone().appendTo("#currencyCost" + row);
        $("#select_currency_list option").clone().appendTo("#currencyAmount" + row);               
        $("#countTaxInvoice").val(row);
        CalculateAmountTotal();
    }
    
    function CalculateAmountTotal(row){
//        var count = parseInt(document.getElementById('counter').value);
        var count = $('#TaxInvoiceTable tr').length;
        
        var i;
        var grandTotal = 0;
        for(i=1;i<=count+1;i++){
            var amount = document.getElementById("amount" + i);
            var cost = document.getElementById("cost" + i);
            if (amount !== null){
                var value = amount.value;
                    
                if(value !== ''){
                    value = value.replace(/,/g,"");
                    var total = parseFloat(value);
                    grandTotal += total;
                    document.getElementById('amount' + i).value = formatNumber(total);
                    
//                    var isVatCheck = document.getElementById("isVat"+i);
//                    if(isVatCheck !== null && isVatCheck !== ''){
//                        if(document.getElementById("isVat"+i).checked){
//                            CalculateGross(i);
//                        } 
//                    }   
                }
            }
            if (cost !== null){
                var costVal = cost.value;
                if(costVal !== ''){
                    costVal = costVal.replace(/,/g,"");
                    var costTotal = parseFloat(costVal);
                    document.getElementById('cost' + i).value = formatNumber(costTotal);
                }
            }
        }
        document.getElementById('TotalAmount').value = formatNumber(grandTotal);
        document.getElementById('TextAmount').value = toWords(grandTotal);
//        $( ".numerical" ).on('input', function() { 
//            var value=$(this).val().replace(/[^0-9.,]*/g, '');
//            value=value.replace(/\.{2,}/g, '.');
//            value=value.replace(/\.,/g, ',');
//            value=value.replace(/\,\./g, ',');
//            value=value.replace(/\,{2,}/g, ',');
//            value=value.replace(/\.[0-9]+\./g, '.');
//            $(this).val(value);
//        });
        if(row){
            if(document.getElementById("isVat"+row).checked){
                CalculateGross(row);
            }            
        }
    }
    
    function CalculateGross(row){       
        var amount = document.getElementById('amount'+row).value;
        var gross = document.getElementById('gross'+row).value;
        var vatData = parseFloat(document.getElementById('vatDefault').value);

        amount = amount.replace(/,/g,"");
        var grossTotal = parseFloat(amount);
        var vatTotal = parseFloat(vatData);

        if((gross === '') || (gross === '0.00')){
            grossTotal = (amount*100)/(100+vatData);
            document.getElementById('gross'+row).value = formatNumber(grossTotal);
            document.getElementById('vatShow'+row).innerHTML = formatNumber(vatTotal);
        } else {
            document.getElementById('gross'+row).value = '';
            document.getElementById('vatShow'+row).innerHTML = '';
        }
    }
    
    function setGross(){
        var row = $('#TaxInvoiceTable tr').length;
        for(var i=1;i<=row;i++){          
            var isVatCheck = document.getElementById("isVat"+i);
            if(isVatCheck !== null && isVatCheck !== ''){
                if(document.getElementById("isVat"+i).checked){
                    CalculateGross(i);
                } else {
                    
                }
            }   
        }
    }   
        
    function toWords(s){
        if(s === 0){
            var defaultWord = '';
            return  defaultWord;
        }
        var th = ['','thousand','million', 'billion','trillion'];
        var dg = ['zero','one','two','three','four', 'five','six','seven','eight','nine']; 
        var tn = ['ten','eleven','twelve','thirteen', 'fourteen','fifteen','sixteen', 'seventeen','eighteen','nineteen']; 
        var tw = ['twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety']; 
        s = s.toString(); 
        s = s.replace(/[\, ]/g,''); 
        if (s != parseFloat(s)) return 'not a number'; 
        var x = s.indexOf('.'); 
        if (x == -1) x = s.length; if (x > 15) return 'too big'; 
        var n = s.split(''); 
        var str = ''; 
        var sk = 0; 
        for (var i=0; i < x; i++) {
            if ((x-i)%3==2) {
                if (n[i] == '1') {
                    str += tn[Number(n[i+1])] + ' '; i++; sk=1;
                } else if (n[i]!=0) {
                    str += tw[n[i]-2] + ' ';sk=1;}
            } else if (n[i]!=0) {
                str += dg[n[i]] +' '; 
                if ((x-i)%3==0) str += 'hundred ';sk=1;
            }             
            if ((x-i)%3==1) {
                if (sk) str += th[(x-i-1)/3] + ' ';
                sk=0;
            }
        } 
        if (x != s.length) {
            var y = s.length; str += 'point '; 
            for (var i=x+1; i<y; i++) str += dg[n[i]] +' ';
        }
        str += ' baht';
        return str.replace(/\s+/g,' ').toUpperCase();
    }
    
    function checkRefNo(row){
        var list = '${refNo_list}';
        var refNo = document.getElementById('refNo'+row).value;

        if(refNo===''){
            var refNoField = document.getElementById('refNo'+row);
            refNoField.style.borderColor = "";
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
                return;
            } else {
                var refNoField = document.getElementById('refNo'+row);
                refNoField.style.borderColor = "Red";
            }
        } 
    }
    
    function validateForm(){
//        var count = document.getElementById('counter').value;
        var count = $('#TaxInvoiceTable tr').length;
        
        for(var i=1;i<=count;i++){
            var refNoField = document.getElementById('refNo'+i);
            
            if(refNoField !== null){
                var color = document.getElementById('refNo'+i).style.borderColor;
                if(color === "red"){
                    return false;
                }   
            }
        }
    }
    
    function deleteTaxList(id,row) {
        document.getElementById('delTaxDetailId').value = id;
        document.getElementById('delTaxDetailRow').value = row;
        $("#delCode").text('Are you sure delete this product ?');
        $('#delTaxInvoiceDetailModal').modal('show');
    }
    
    function deleteTaxInvoiceDetailList(){
        var id = document.getElementById('delTaxDetailId').value;
        var row = document.getElementById('delTaxDetailRow').value;
        if(id === ''){
            $("#product" + row).parent().parent().remove();
            var rowAll = $("#TaxInvoiceTable tr").length;
            if (rowAll <= 1) {
                $("#tr_TaxInvoiceDetailAddRow").removeClass("hide");
                $("#tr_TaxInvoiceDetailAddRow").addClass("show");
            }
            
        } else {
            $.ajax({
                url: 'TaxInvoice'+'${page}'+'.smi?action=deleteTaxInvoiceDetail',
                type: 'get',
                data: {taxInvoiceDetailId: id},
                success: function () {
                    $("#product" + row).parent().parent().remove();
                    var rowAll = $("#TaxInvoiceTable tr").length;
                    if (rowAll <= 1) {
                        $("#tr_TaxInvoiceDetailAddRow").removeClass("hide");
                        $("#tr_TaxInvoiceDetailAddRow").addClass("show");
                    }
                },
                error: function () {
                    console.log("error");
                    result =0;
                }
            }); 
        }    
        $('#delTaxInvoiceDetailModal').modal('hide');
        CalculateAmountTotal();
    }
    
    function clearScreen(){
        $("#department").val("");
        $("#invoiceNo").val("");
        $("#TaxInvId").val("");
        $("#TaxInvNo").val("");
        $("#InvToDate").val("");
        $("#TaxInvTo").val("");
        $("#InvToName").val("");
        $("#InvToAddress").val("");
        $("#ARCode").val("");
        $("#Remark").val("");
        $("#TextAmount").val("");
        $("#TotalAmount").val("");
        $("#taxInvStatus").val("");
        $("#createDate").val("");
        $("#createBy").val("");
        $("#countTaxInvoice").val("1");              
        $('#InvoiceListTable > tbody  > tr').each(function() {
            $(this).remove();
        });       
        $('#TaxInvoiceTable > tbody  > tr').each(function() {
            $(this).remove();
        });              
        AddRowTaxInvoiceTable();
        CalculateAmountTotal();
    }
    
    function DisableVoidInvoice(){
        var TaxInvNo = document.getElementById('TaxInvNo');
        document.getElementById('disableVoid').innerHTML = "Are you sure to delete booking other : " + TaxInvNo.value + " ?";
    }

    function EnableVoidInvoice(){
        var TaxInvNo = document.getElementById('TaxInvNo');
        document.getElementById('enableVoid').innerHTML = "Are you sure to enable booking other : " + TaxInvNo.value + " ?";
    }
    
    function Enable() {
        var action = document.getElementById('action');
        action.value = 'enableVoid';
        document.getElementById('TaxInvoiceForm').submit();
    }

    function DisableInvoice() {
        var action = document.getElementById('action');
        action.value = 'disableVoid';
        document.getElementById('TaxInvoiceForm').submit();
    }
    
    function checkVatAll(){
//        var row = document.getElementById('counter').value;
        var row = $('#TaxInvoiceTable tr').length;
        var vatDefaultData = parseFloat(document.getElementById('vatDefault').value);      
        var check = 0;
        var unCheck = 0;
        for(var i=1;i<row;i++){          
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
            for(var i=1;i<row;i++){
                var isVatCheck = document.getElementById("isVat"+i);
                if(isVatCheck !== null && isVatCheck !== ''){
                    if(document.getElementById("isVat"+i).checked){
                        
                    } else { 
                        document.getElementById("isVat"+i).checked = true;
                        var amountChk = document.getElementById('amount'+i);
                        if(amountChk !== null && amountChk !== ''){
                            var amount = document.getElementById('amount'+i).value;
                            var gross = document.getElementById('gross'+i).value;

                            amount = amount.replace(/,/g,"");
                            var grossTotal = parseFloat(amount);

                            if((gross === '')){
                                grossTotal = (amount*100)/(100+vatDefaultData);
                                document.getElementById('gross'+i).value = formatNumber(grossTotal);
                                document.getElementById('vatShow'+i).innerHTML = formatNumber(vatDefaultData);
                            } else {
                                document.getElementById('gross'+i).value = '';
                                document.getElementById('vatShow'+i).innerHTML = '';
                            }
                        }
                    }    
                }   
            }
        }
            
        if(check < unCheck){
            for(var i=1;i<row;i++){
                var isVatCheck = document.getElementById("isVat"+i);
                if(isVatCheck !== null && isVatCheck !== ''){
                    document.getElementById("isVat"+i).checked = false;
                    document.getElementById("vatShow"+i).innerHTML = '';
                    document.getElementById("gross"+i).value = '';
                }   
            }
        }
         
        if(check === 0 && unCheck !== 0){
            for(var i=1;i<row;i++){
                var isVatCheck = document.getElementById("isVat"+i);
                if(isVatCheck !== null && isVatCheck !== ''){
                    if(document.getElementById("isVat"+i).checked){
                        
                    } else { 
                        document.getElementById("isVat"+i).checked = true;
                        var amountChk = document.getElementById('amount'+i);
                        if(amountChk !== null && amountChk !== ''){
                            var amount = document.getElementById('amount'+i).value;
                            var gross = document.getElementById('gross'+i).value;
                            
                            amount = amount.replace(/,/g,"");
                            var grossTotal = parseFloat(amount);

                            if((gross === '')){
                                grossTotal = (amount*100)/(100+vatDefaultData);
                                document.getElementById('gross'+i).value = formatNumber(grossTotal);
                                document.getElementById('vatShow'+i).innerHTML = formatNumber(vatDefaultData);
                            } else {
                                document.getElementById('gross'+i).value = '';
                                document.getElementById('vatShow'+i).innerHTML = '';
                            }
                        }
                    }    
                }    
            }
        } 
            
        if(check !== 0 && unCheck === 0){
            for(var i=1;i<row;i++){
                var isVatCheck = document.getElementById("isVat"+i);
                if(isVatCheck !== null && isVatCheck !== ''){
                    document.getElementById("isVat"+i).checked = false;
                    document.getElementById('vatShow'+i).innerHTML = '';
                    document.getElementById("gross"+i).value = '';
                }   
            }
        }
            
        if(check === unCheck){
            for(var i=1;i<row;i++){
                var isVatCheck = document.getElementById("isVat"+i);
                if(isVatCheck !== null && isVatCheck !== ''){
                    if(document.getElementById("isVat"+i).checked){
                        
                    } else { 
                        document.getElementById("isVat"+i).checked = true;
                        var amountChk = document.getElementById('amount'+i);
                        if(amountChk !== null && amountChk !== ''){
                            var amount = document.getElementById('amount'+i).value;
                            var gross = document.getElementById('gross'+i).value;
                            
                            amount = amount.replace(/,/g,"");
                            var grossTotal = parseFloat(amount);

                            if((gross === '')){
                                grossTotal = (amount*100)/(100+vatDefaultData);
                                document.getElementById('gross'+i).value = formatNumber(grossTotal);
                                document.getElementById('vatShow'+i).innerHTML = formatNumber(vatDefaultData);
                            } else {
                                document.getElementById('gross'+i).value = '';
                                document.getElementById('vatShow'+i).innerHTML = '';
                            }
                        }
                    }    
                }    
            }             
        }            
        CalculateAmountTotal();   
    }
    
</script>
