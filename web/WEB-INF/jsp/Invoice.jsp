<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="type" value="${requestScope['typeInvoice']}" />
<c:set var="listCurrency" value="${requestScope['listCurrency']}" />
<c:set var="defaultData" value="${requestScope['defaultData']}" />
<c:set var="listCustomerAgentInfo" value="${requestScope['listCustomerAgentInfo']}" />
<c:set var="listStaff" value="${requestScope['listStaff']}" />
<c:set var="listTermPay" value="${requestScope['listTermPay']}" />
<c:set var="invoice" value="${requestScope['invoice']}" />
<c:set var="listInvoiceDetail" value="${requestScope['listInvoiceDetail']}" />
<c:set var="listType" value="${requestScope['listType']}" />
<c:set var="result" value="${requestScope['result']}" />
<c:set var="roleName" value="${requestScope['roleName']}" />
<c:set var="page" value="${requestScope['page']}" />
<c:set var="create" value="${requestScope['thisdate']}" />
<c:set var="checkRecipt" value="${requestScope['checkRecipt']}" />
<c:set var="checkTaxinvoice" value="${requestScope['checkTaxinvoice']}" />
<c:set var="showvat" value="false" />
<c:set var="typeBooking" value="" />
<c:set var="textVoid" value="" />
<c:set var="invoiceType" value="${requestScope['invoiceType']}" />
<c:set var="vat" value="${requestScope['vat']}" />

<section class="content-header" >
    <h1>
        Finance & Cashier - Invoice
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Invoice</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app="">
    <div class="row">        
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/InvoiceMenu.html'"></div>
        </div>
    
        <div class="col-sm-10">          
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Success!</strong> 
            </div>
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Not  Success!</strong> 
            </div>
            <div id="textAlertNotInvoice"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Not have Invoice In Page!!</strong> 
            </div>
            <div id="textAlertDuplicate"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Duplicate</strong> 
            </div>
            <div id="textAlertMoney"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Money more than Billable, Please Input money</strong> 
            </div>
            <div id="textAlertTaxinvoice"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Cannot void invoice. It use in tax invoice no ${invoice.invNo}</strong> 
            </div>
            <div id="textAlertRecipt"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Cannot void invoice. It use in receipt no ${invoice.invNo}</strong> 
            </div>
            <div id="textAlertInvoiceNotEmpty"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Invoice Not Empty</strong> 
            </div>
            <div id="textAlertCurrencyAmountNotEmpty"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Currency Amount Not Empty</strong> 
            </div>
            <form action="Invoice${page}.smi" method="post" id="InvoiceForm" role="form" onsubmit="return validFromInvoice();">
            <div id="textAlertDisable"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Void Success </strong> 
            </div>
            <c:if test="${invoice.MFinanceItemstatus.id == '2'}">        
                 <c:set var="textVoid" value="VOID" />
            </c:if>
            <c:set var="panelheader" value=""/>
            <c:set var="panelborder" value=""/>
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <c:choose>
                        <c:when test="${fn:contains(page , 'OT')}">
                            <c:set var="typeInvoice" value="O/T" />
                            <c:set var="typeBooking" value="O" />
                            <h4><b>Invoice Temp Outbound  <font style="color: red;"> ${textVoid}</font></b></h4>
                            <c:set var="panelheader" value="outboundheader"/>
                            <c:set var="panelborder" value="outboundborder"/>
                        </c:when>
                        <c:when test="${fn:contains(page , 'OV')}">
                            <c:set var="typeInvoice" value="O/V" />
                            <c:set var="typeBooking" value="O" />
                            <c:set var="showvat" value="true" />
                            <h4><b>Invoice Vat Outbound <font style="color: red;"> ${textVoid} </font>
                                &nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/vat.png" style="width:100px;height:50px;">  
                            </b></h4>
                            <c:set var="panelheader" value="outboundheader"/>
                            <c:set var="panelborder" value="outboundborder"/>
                        </c:when>
                        <c:when test="${fn:contains(page , 'WT')}">
                            <c:set var="typeInvoice" value="W/T" />
                            <c:set var="typeBooking" value="I" />
                            <h4><b>Invoice Temp Wendy <font style="color: red;"> ${textVoid}</font></b></h4>
                            <c:set var="panelheader" value="wendyheader"/>
                            <c:set var="panelborder" value="wendyborder"/>
                        </c:when>
                        <c:when test="${fn:contains(page , 'WV')}">
                            <c:set var="typeInvoice" value="W/V" />
                            <c:set var="typeBooking" value="I" />
                            <c:set var="showvat" value="true" />
                            <h4><b>Invoice Vat Wendy <font style="color: red;"> ${textVoid} </font>
                                &nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/vat.png" style="width:100px;height:50px;">  
                            </b></h4>
                            <c:set var="panelheader" value="wendyheader"/>
                            <c:set var="panelborder" value="wendyborder"/>
                        </c:when>
                        <c:when test="${fn:contains(page , 'WN')}">
                            <c:set var="typeInvoice" value="W/N" />
                            <c:set var="typeBooking" value="I" />
                            <h4><b>Invoice No Vat Wendy <font style="color: red;"> ${textVoid}</font>
                                &nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/novat.png" style="width:100px;height:50px;">  
                            </b></h4>
                            <c:set var="panelheader" value="wendyheader"/>
                            <c:set var="panelborder" value="wendyborder"/>
                        </c:when> 
                        <c:when test="${fn:contains(page , 'ON')}">
                            <c:set var="typeInvoice" value="O/N" />
                            <c:set var="typeBooking" value="O" />
                            <h4><b>Invoice No Vat Outbound <font style="color: red;"> ${textVoid}</font>
                                &nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/novat.png" style="width:100px;height:50px;">  
                            </b></h4>
                            <c:set var="panelheader" value="outboundheader"/>
                            <c:set var="panelborder" value="outboundborder"/>
                        </c:when> 
                        <c:when test="${fn:contains(page , 'WA')}">
                            <c:set var="typeInvoice" value="W/A" />
                            <c:set var="invType" value="Air Ticket" />
                            <c:set var="typeBooking" value="I" />
                            <h4><b>Invoice Air Ticket Wendy <font style="color: red;"> ${textVoid}</font>
                                &nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/plain.jpg" style="width:120px;height:50px;">  
                            </b></h4>
                            <c:set var="panelheader" value="wendyheader"/>
                            <c:set var="panelborder" value="wendyborder"/>
                        </c:when> 
                        <c:when test="${fn:contains(page , 'OA')}">
                            <c:set var="typeInvoice" value="O/A" />
                            <c:set var="invType" value="Air Ticket" />
                            <c:set var="typeBooking" value="O" />
                            <h4><b>Invoice Air Ticket Outbound <font style="color: red;"> ${textVoid}</font>
                                &nbsp;&nbsp;<img src="${pageContext.request.contextPath}/img/plain.jpg" style="width:120px;height:50px;">  
                            </b></h4>
                            <c:set var="panelheader" value="outboundheader"/>
                            <c:set var="panelborder" value="outboundborder"/>
                        </c:when> 
                    </c:choose> 
                    <input type="text" class="hidden" value="${typeInvoice}" id="InputInvoiceType" name="InputInvoiceType">
                    <input type="text" class="hidden" value="${invType}" id="invType" name="invType">
                </div>
                <div class="col-xs-12 form-group"><hr/></div>
            </div>
            
            <!--Search Invoice-->
            <div class="row" style="padding-left: 15px;padding-right:15px">  
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active " id="infoSearchInvoice">
                            <div class="panel panel-default ${panelborder}">
                                <div class="panel-heading ${panelheader}">
                                    <h2 class="panel-title">
                                        <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" onclick="">
                                            <span id="SpanEdit${advanced.search}">Search Invoice</span>
                                        </a>
                                        <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" style="margin-left: 54em" onclick="">
                                            <span id="arrowReservstion" class="arrowReservstion glyphicon glyphicon-chevron-up"></span> 
                                        </a>
                                    </h2>               
                                </div>  
                                <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxloadsearch"  class="fa fa-spinner fa-spin hidden"></i></div>           
                                <div class="panel-body">               
                                    <div class=" accordion-body collapse in" id="collapseExample${advanced.search}" aria-expanded="false">
                                        <div class="col-md-12">
                                            <div class="col-xs-1 text-right">
                                                <label class="control-label" for="">Ref no </lable>
                                            </div>
                                            <div class="col-md-2 form-group">
                                                <input type="text" class="form-control" id="SearchRefNo" name="SearchRefNo" value="" >                                 
                                            </div>
                                            <div class="col-md-1 text-right">
                                                <button type="button"  id="ButtonSearch"  name="ButtonSearch" onclick="searchAction();" class="btn btn-primary btn-sm">
                                                    <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                                                </button>   
                                            </div>  
                                            <div class="col-md-5 ">
                                                <c:choose>
                                                    <c:when test="${typeBooking == 'O'}">
                                                        <div id='AlertBooking' style='display:none'><font color="red">This Ref No can get billable detail from wendy only</font></div>
                                                    </c:when>
                                                    <c:when test="${typeBooking == 'I'}">
                                                        <div id='AlertBooking' style='display:none'><font color="red">This Ref No can get billable detail from outbound only</font></div>
                                                    </c:when>
                                                </c:choose>   
                                            </div>
                                            
                                            
                                        </div>
                                        <div class="col-xs-12 form-group"></div>
                                        <div class="row" style="padding-left:35px">
                                            <input type="text" class="hidden" id="counter" name="counter" value="1">
                                            <div class="col-md-12">
                                                <table id="MasterReservation" class="display" cellspacing="0" width="100%">
                                                    <thead>
                                                        <tr class="datatable-header">
                                                            <th style="width: 5%" >No</th>
                                                            <th class="hidden" >ID</th>
                                                            <th class="hidden" >ID Type</th>
                                                            <th style="width: 15%" >Type</th>
                                                            <th style="width: 60%">Description</th>
                                                            <th style="width: 10%">Cost</th>
                                                            <th style="width: 2%">Cur</th>
                                                            <th style="width: 10%">Price</th>
                                                            <th style="width: 2%" >Cur</th>
                                                            <th style="width: 1%">Action</th>
                                                            <th class="hidden" >Ref Item Id</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>               
                                                        
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
                </div>                                
                <!--<div class="col-xs-12 form-group"></div>-->
                <div class="panel panel-default ${panelborder}">
                    <div class="panel-heading ${panelheader}">
                        <h4 class="panel-title">Invoice Detail</h4>
                    </div>
                    <div class="panel-body"  style="padding-right: 0px;">
                        <input type="hidden"  class="form-control" id="InvoiceId" name="InvoiceId"  value="${invoice.id}" >
                        <div class="col-xs-12 ">
                            <div class="col-xs-1 text-right">
                                <label class="control-label" for="">INV no</lable>
                            </div>
                            <div class="col-md-1 form-group" style="width: 125px;">
                                <input type="text"  class="form-control" id="InvNo" name="InvNo"  value="${invoice.invNo}" >
                            </div>
                            <div class="col-md-1 form-group" style="width: 120px;">
                                <button type="button"  id="ButtonSearchInvoiceNo"  name="ButtonSearchInvoiceNo" onclick="searchInvoiceFromInvoiceNo();" class="btn btn-primary btn-sm">
                                    <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                                </button>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 105px;padding-left: 0px;">
                                <label class="control-label" for="">Invoice date<font style="color: red">*</font></lable>
                            </div>
                            <div class="col-md-2 form-group">
                                <div class='input-group date' id='InputDatePicker'>    
                                    <c:if test='${invoice.invDate != null}'>
                                        <input id="InputInvDate" name="InputInvDate"  type="text" 
                                           class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${invoice.invDate}">
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                         
                                    </c:if>
                                    <c:if test='${invoice.invDate == null}'>
                                        <input id="InputInvDate" name="InputInvDate"  type="text" 
                                           class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${create}">
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                              
                                    </c:if>  
                                </div>
                            </div>
                            <div class="col-xs-2 text-right">
                                <label class="control-label" for="">Due date </lable>
                            </div>
                            <div class="col-md-2 form-group">
                                <div class='input-group date' id='InputDatePicker'>
                                <c:if test='${invoice.dueDate != null}'>
                                    <input id="InputDueDate" name="InputDueDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${invoice.dueDate}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                         
                                </c:if>
                                <c:if test='${invoice.dueDate == null}'>
                                    <input id="InputDueDate" name="InputDueDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                              
                                </c:if>                             
                            </div>
                            </div>
                        </div>
                        <div class="col-xs-12 ">
                            <div class="col-sm-1 text-right">
                                <label class="control-label" for="">Inv To<font style="color: red">*</font></lable>
                            </div>
                            <div class="col-md-6 form-group">
                                <div class="input-group">
                                <input type="hidden" class="form-control" id="InvToId" name="InvToId" value="${invoice.invTo}"/>
                                <input type="text" class="form-control" id="InvTo" name="InvTo" value="${invoice.invTo}" style="background-color: #ffffff">
                                <span class="input-group-addon" id="InvTo_Modal"  data-toggle="modal" data-target="#InvToModal">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                                </div>
                            </div>
                            <div class="col-xs-2 text-right">
                                <label class="control-label" for="">Term pay</lable>
                            </div>
                            <div class="col-md-2 form-group">                      
                                <select class="form-control" id="TermPay" name="TermPay">
                                    <!--<option value="" >--select--</option>-->
                                    <c:forEach var="item" items="${listTermPay}" >
                                        <c:set var="selectTerm" value="" />
                                        <c:if test="${item.id == invoice.MAccTerm.id}">
                                            <c:set var="selectTerm" value="selected" />
                                        </c:if>
                                        <option value="${item.id}" ${selectTerm}>${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-12 ">
                            <div class="col-sm-1 text-right">
                                <label class="control-label" for="">Name<font style="color: red">*</font></lable>
                            </div>    
                            <div class="col-md-6 form-group">
                                <input  type="text" id="InvToName" name="InvToName" class="form-control" value="${invoice.invName}" >
                            </div>
                            <c:set var="isHidden" value="hidden" />
                                <c:if test="${typeInvoice == 'W/A'}">
                                    <c:set var="isHidden" value="" />
                                </c:if>
                            <div class="form-group ${isHidden}" >
                                <div class="col-sm-2" style="padding-left: 53px">
                                <label for="Department" class="col-sm-3 control-label" >Department</label>
                                </div>
                                <c:set var="chekcDepartAir" value="" />
                                <c:if test="${invoice.subDepartment == 'AirTicket'}">
                                    <c:set var="chekcDepartAir" value="checked" />
                                </c:if>      
                                <div class="radio col-sm-2 "  >   
                                   <label ${isHidden}><input value="AirTicket" id="DepartmentAirTicket" name="Department" type="radio" ${chekcDepartAir} >Air Ticket</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 ">
                            <div class="col-sm-1 text-right">
                                <label class="control-label" for="">Address </lable>
                            </div>
                            <div class="col-md-6 form-group">
                                <textarea  rows="3" cols="100" id="InvToAddress" name="InvToAddress" class="form-control" >${invoice.invAddress}</textarea>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-2"></div>
                                <div class="radio col-sm-2 ${isHidden}" > 
                                    <c:set var="checkDepartPackage" value="" />
                                    <c:if test="${invoice.subDepartment == 'Package'}">
                                        <c:set var="checkDepartPackage" value="checked" />
                                    </c:if>
                                    <label ${isHidden}><input value="Package" id="DepartmentPackage" name="Department" type="radio" ${checkDepartPackage}>Package</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 ">
                            <div class="col-xs-1 text-right">
                                <label class="control-label" for="">Sale&nbsp;Staff</label>
                            </div>                       
                            <div class="col-md-2 form-group">
                                <div class="input-group">
                                <input type="hidden" class="form-control" id="SaleStaffId" name="SaleStaffId" value="${invoice.staff.id}"/>
                                <input type="text" class="form-control" id="SaleStaffCode" name="SaleStaffCode" value="${invoice.staff.username}" style="background-color: #ffffff">
                                <span class="input-group-addon" id="SaleStaff_Modal"  data-toggle="modal" data-target="#SaleStaffModal">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                                </div>
                            </div>
                            <div class="col-md-3 form-group">
                                <input type="text"  class="form-control" id="SaleStaffName" name="SaleStaffName"  value="${invoice.staff.name}" readonly="">
                            </div>
                            <div class="col-md-2 form-group">
                                <c:set var="checkGroup" value="" />
                                <c:if test="${invoice.isGroup == 1}">
                                    <c:set var="checkGroup" value="checked" />
                                </c:if>
                                 <label class="control-label"><input onclick='' type="checkbox" id="Grpup" name="Grpup"  ${checkGroup}>  Group Yes/No</label>
                                 <input class="hidden" type="text" value="${invoice.isGroup}"  >
                            </div>
                            <div class="col-xs-1 text-right">
                                <label class="control-label" for="" >A/R&nbsp;Code<font style="color: red">*</font></label>
                            </div>  
                            <div class="col-md-2 form-group">
                                <input type="hidden" class="form-control" id="ARCodeId" name="ARCodeId" value="${invoice.arcode}"/>
                                <input type="text" class="form-control" id="ARCode" name="ARCode" value="${invoice.arcode}" style="background-color: #ffffff">                    
                            </div>
                        </div>             
                        <div role="tabpanel">
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane  active" id="infoDetailBillable">
                                    <div class="panel-body">
                                        <div class="col-xs-12">    
                                            <div id="textAlertCurrency"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                <strong>Currency is not match!!! </strong> 
                                            </div> 
                                        </div>
                                        <input type="text" class="hidden" id="counterTable" name="counterTable" value="1" >
                                        <input type="text" class="hidden" id="idDeleteDetailBillable" name="idDeleteDetailBillable" value="0" >
                                        <input type="text" class="hidden" id="action" name="action" value="save" >
                                        <c:choose>
                                            <c:when test="${showvat =='true'}">
                                        <table class="display" id="DetailBillableTable">
                                            <thead class="datatable-header">
                                                <tr>
                                                    <th class="hidden"> Id</th>
                                                    <th class="hidden">Detail Id</th>
                                                    <th style="width:10%;" align="center">Product</th>
                                                    <th style="width:13%;" align="center">Description</th>
                                                    <th class="hidden">Description Temp</th>
                                                    <th style="width: 8%" align="center">Cost</th>
                                                    <th style="width: 7%" align="center" class="priceCurrencyCost">Cur</th>
                                                    <th style="width: 8%" align="center">Cost Local</th>
                                                    <th class="hidden">Cost Local Input</th>
                                                    <th style="width: 3%" onclick="checkVatInvoiceAll()" align="center"><u>Is vat</u></th> 
                                                    <th style="width: 3%" align="center">Vat</th>
                                                    <th class="hidden" align="center">Vat Temp</th>
                                                    <th style="width: 8%" align="center">Gross</th>
                                                    <th style="width: 8%" align="center">Amount</th>
                                                    <th style="width: 7%" align="center" class="" >Cur</th>
                                                    <th style="width: 8%" align="center">Amount Local</th>
                                                    <th class="hidden">Amount Local Input</th>
                                                    <th style="width: 3%" align="center">Action</th>
                                                    <th class="hidden">Description Temp Type</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="ind" items="${listInvoiceDetail}" varStatus="taxdesc">
                                                <tr> 
                                                    <td class="hidden"><input type="text" class="form-control" id="detailId${taxdesc.count}" name="detailId${taxdesc.count}" value="${ind.id}" > </td>
                                                    <td class="hidden"><input type="text" class="form-control" id="DetailBillId${taxdesc.count}" name="DetailBillId${taxdesc.count}" value="${ind.billableDesc.id}" > </td>
                                                    <td>  
                                                        <select id="SelectProductType${taxdesc.count}" name="SelectProductType${taxdesc.count}" class="form-control">
                                                            <option value='' ></option>
                                                            <c:forEach var="typeP" items="${listType}">
                                                                <c:set var="selectTypePro" value="" />
                                                                <c:if test="${typeP.id == ind.mbillType.id}">
                                                                    <c:set var="selectTypePro" value="selected" />

                                                                </c:if> 
                                                                <option value='${typeP.id}' ${selectTypePro}>${typeP.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td> 
                                                        <input type="text" class="form-control" id="BillDescriptionTemp${taxdesc.count}" name="BillDescriptionTemp${taxdesc.count}" value="${ind.description}" onkeyup="setDescription(${taxdesc.count})">                                           
                                                    </td>
                                                    <td class="hidden"><input type="text" class="form-control" id="BillDescription${taxdesc.count}" name="BillDescription${taxdesc.count}" value="${ind.description}" > </td>
                                                    <td><input type="text" maxlength ="15" class="form-control numerical text-right" onfocusout="changeFormatCostNumber(${taxdesc.count})"  id="InputCost${taxdesc.count}" name="InputCost${taxdesc.count}" value="${ind.cost}" ></td>
                                                    <td class="priceCurrencyCost">
                                                        <select id="SelectCurrencyCost${taxdesc.count}" name="SelectCurrencyCost${taxdesc.count}" class="form-control">
                                                             <option value='' ></option>
                                                            <c:forEach var="cur" items="${listCurrency}">
                                                                <c:set var="select" value="" />
                                                                <c:if test="${cur.code == ind.curCost}">
                                                                    <c:set var="select" value="selected" />

                                                                </c:if> 
                                                                <option value='${cur.code}' ${select}>${cur.code}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" value="${ind.costLocal}" onfocusout="changeFormatCostLocalNumber(${taxdesc.count})" id="InputCostLocal${taxdesc.count}" name="InputCostLocal${taxdesc.count}" class="form-control text-right"></td>
                                                    <td class="hidden"><input type="text" value="${ind.costLocal}" id="InputCostLocalTemp${taxdesc.count}" name="InputCostLocalTemp${taxdesc.count}"></td>      
                                                    <td>
                                                        <c:set var="checkIsVat" value="" />
                                                        <c:if test="${ind.isVat == 1}">
                                                            <c:set var="checkIsVat" value="checked" />
                                                        </c:if> 
                                                        <input type="checkbox" id="checkUse${taxdesc.count}" name="checkUse${taxdesc.count}"  onclick="calculateGross('${taxdesc.count}')"  ${checkIsVat}>
                                                    </td>
                                                    <td>${ind.vat}</td>
                                                    <td class="hidden"><input type="text" class="form-control" id="InputVatTemp${taxdesc.count}" name="InputVatTemp${taxdesc.count}" value="${ind.vat}" ></td>
                                                    <td ><input type="text" maxlength ="15" readonly  onfocusout="changeFormatGrossNumber(${taxdesc.count})" class="form-control numerical" id="InputGross${taxdesc.count}" name="InputGross${taxdesc.count}" value="${ind.gross}" ></td>
                                                    <td><input type="text" maxlength ="15" class="form-control numerical text-right" id="InputAmount${taxdesc.count}" name="InputAmount${taxdesc.count}" value="${ind.amount}" onfocusout="changeFormatAmountNumber('${taxdesc.count}');"></td>
                                                    <td class="priceCurrencyAmount">
                                                        <select id="SelectCurrencyAmount${taxdesc.count}" name="SelectCurrencyAmount${taxdesc.count}" class="form-control" onclick="validFromInvoice()">
                                                            <option value='' ></option>
                                                            <c:forEach var="cur" items="${listCurrency}">
                                                                <c:set var="selectA" value="" />
                                                                <c:if test="${cur.code == ind.curAmount}">
                                                                    <c:set var="selectA" value="selected" />
                                                                </c:if> 
                                                                <option value='${cur.code}' ${selectA}>${cur.code}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" value="${ind.amount}" id="InputAmountLocal${taxdesc.count}" onfocusout="changeFormatAmountLocalNumber(${taxdesc.count})" name="InputAmountLocal${taxdesc.count}" class="form-control text-right" ></td>
                                                    <td class="hidden"><input type="text" value="${ind.amount}" onfocusout="changeFormatAmountLocalTempNumber(${taxdesc.count})" id="InputAmountLocalTemp${taxdesc.count}" name="InputAmountLocalTemp${taxdesc.count}"  ></td>
                                                    <td align="center" >
                                                        <span  class="glyphicon glyphicon-th-list" data-toggle="modal" data-target="#DescriptionInvoiceDetailModal" onclick="getDescriptionDetail('${taxdesc.count}')" id="InputDescription${taxdesc.count}"></span>
                                                        <span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill('${taxdesc.count}','${ind.description}')" data-toggle="modal" data-target="#DelDetailBill" >  </span>        
                                                    </td>
                                                    <td class="hidden">
                                                        <c:set var="displayDescriptionTemp" value="${fn:trim(ind.displayDescription)}" />
                                                        <textarea id="DescriptionInvoiceDetail${taxdesc.count}" name="DescriptionInvoiceDetail${taxdesc.count}" >${displayDescriptionTemp}</textarea>
                                                    </td>
                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>                                        

                                            </c:when>
                                            <c:when test="${showvat =='false'}">
                                        <table class="display" id="DetailBillableTable">
                                            <thead class="datatable-header">
                                                <tr>
                                                    <th class="hidden"> Id</th>
                                                    <th class="hidden">Detail Id</th>
                                                    <th style="width:10%;" align="center">Product</th>
                                                    <th style="width:15%;" align="center">Description</th>
                                                    <th class="hidden">Description Temp</th>
                                                    <th style="width: 10%" align="center">Cost</th>
                                                    <th style="width: 7%" align="center">Cur</th>
                                                    <th style="width: 10%" align="center">Cost Local</th>
                                                    <th class="hidden">Cost Local Input</th>
                                                    <th class="hidden" onclick="checkVatInvoiceAll()" align="center"><u>Is vat</u></th> 
                                                    <th class="hidden" align="center">Vat</th>
                                                    <th class="hidden" align="center">Vat Temp</th>
                                                    <th class="hidden" align="center">Gross</th>
                                                    <th style="width: 10%" align="center">Amount</th>
                                                    <th style="width: 7%" align="center">Cur</th>
                                                    <th style="width: 10%" align="center">Amount Local</th>
                                                    <th class="hidden">Amount Local Input</th>
                                                    <th style="width: 3%" align="center">Action</th>
                                                    <th class="hidden">Description Temp Type</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="ind" items="${listInvoiceDetail}" varStatus="taxdesc">
                                                <tr> 
                                                    <td class="hidden"><input type="text" class="form-control" id="detailId${taxdesc.count}" name="detailId${taxdesc.count}" value="${ind.id}" > </td>
                                                    <td class="hidden"><input type="text" class="form-control" id="DetailBillId${taxdesc.count}" name="DetailBillId${taxdesc.count}" value="${ind.billableDesc.id}" > </td>
                                                    <td>  
                                                        <select id="SelectProductType${taxdesc.count}" name="SelectProductType${taxdesc.count}" class="form-control">
                                                            <option value='' ></option>
                                                            <c:forEach var="typeP" items="${listType}">
                                                                <c:set var="selectTypePro" value="" />
                                                                <c:if test="${typeP.id == ind.mbillType.id}">
                                                                    <c:set var="selectTypePro" value="selected" />

                                                                </c:if> 
                                                                <option value='${typeP.id}' ${selectTypePro}>${typeP.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td> 
                                                        <input type="text" class="form-control" id="BillDescriptionTemp${taxdesc.count}" name="BillDescriptionTemp${taxdesc.count}" value="${ind.description}" onkeyup="setDescription(${taxdesc.count})">                                           
                                                    </td>
                                                    <td class="hidden"><input type="text" class="form-control" id="BillDescription${taxdesc.count}" name="BillDescription${taxdesc.count}" value="${ind.description}" > </td>
                                                    <td><input type="text" maxlength ="15" onfocusout="changeFormatCostNumber(${taxdesc.count})" class="form-control numerical text-right" id="InputCost${taxdesc.count}" name="InputCost${taxdesc.count}" value="${ind.cost}" ></td>
                                                    <td>
                                                        <select id="SelectCurrencyCost${taxdesc.count}" name="SelectCurrencyCost${taxdesc.count}" class="form-control">
                                                             <option value='' ></option>
                                                            <c:forEach var="cur" items="${listCurrency}">
                                                                <c:set var="select" value="" />
                                                                <c:if test="${cur.code == ind.curCost}">
                                                                    <c:set var="select" value="selected" />

                                                                </c:if> 
                                                                <option value='${cur.code}' ${select}>${cur.code}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" value="${ind.costLocal}" onfocusout="changeFormatCostLocalNumber(${taxdesc.count})" id="InputCostLocal${taxdesc.count}" name="InputCostLocal${taxdesc.count}" class="form-control text-right"></td>
                                                    <td class="hidden"><input type="text" value="${ind.costLocal}" id="InputCostLocalTemp${taxdesc.count}" name="InputCostLocalTemp${taxdesc.count}"></td>      
                                                    <td class="hidden">
                                                        <c:set var="checkIsVat" value="" />
                                                        <c:if test="${ind.isVat == 1}">
                                                            <c:set var="checkIsVat" value="checked" />
                                                        </c:if> 
                                                        <input class="hidden" type="checkbox" id="checkUse${taxdesc.count}" name="checkUse${taxdesc.count}"  onclick="calculateGross('${taxdesc.count}')"  ${checkIsVat}>
                                                    </td>
                                                    <td class="hidden" >${ind.vat}</td>
                                                    <td class="hidden" ><input type="text" class="form-control" id="InputVatTemp${taxdesc.count}" name="InputVatTemp${taxdesc.count}" value="${ind.vat}" ></td>
                                                    <td class="hidden" ><input type="text" maxlength ="15"  onfocusout="changeFormatGrossNumber(${taxdesc.count})" class="form-control numerical" id="InputGross${taxdesc.count}" name="InputGross${taxdesc.count}" value="${ind.gross}" ></td>
                                                    <td><input type="text" maxlength ="15" class="form-control numerical text-right" id="InputAmount${taxdesc.count}" name="InputAmount${taxdesc.count}" value="${ind.amount}" onfocusout="changeFormatAmountNumber('${taxdesc.count}');"></td>
                                                    <td class="priceCurrencyAmount">
                                                        <select id="SelectCurrencyAmount${taxdesc.count}" name="SelectCurrencyAmount${taxdesc.count}" class="form-control" onclick="validFromInvoice()">
                                                             <option value='' ></option>
                                                            <c:forEach var="cur" items="${listCurrency}">
                                                                <c:set var="selectA" value="" />
                                                                <c:if test="${cur.code == ind.curAmount}">
                                                                    <c:set var="selectA" value="selected" />
                                                                </c:if> 
                                                                <option value='${cur.code}' ${selectA}>${cur.code}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td><input type="text" value="${ind.amount}" onfocusout="changeFormatAmountLocalNumber(${taxdesc.count})" id="InputAmountLocal${taxdesc.count}" name="InputAmountLocal${taxdesc.count}" class="form-control text-right" ></td>
                                                    <td class="hidden"><input type="text" value="${ind.amount}" id="InputAmountLocalTemp${taxdesc.count}" onfocusout="changeFormatAmountLocalTempNumber(${taxdesc.count})" name="InputAmountLocalTemp${taxdesc.count}"  ></td>
                                                    <td align="center" >
                                                        <span  class="glyphicon glyphicon-th-list" data-toggle="modal" data-target="#DescriptionInvoiceDetailModal" onclick="getDescriptionDetail('${taxdesc.count}')" id="InputDescription${taxdesc.count}"></span>
                                                        <span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill('${taxdesc.count}','${ind.id}')" data-toggle="modal" data-target="#DelDetailBill" >  </span>        
                                                    </td>
                                                    <td class="hidden">
                                                        <c:set var="displayDescriptionTemp" value="${fn:trim(ind.displayDescription)}" />
                                                        <textarea id="DescriptionInvoiceDetail${taxdesc.count}" name="DescriptionInvoiceDetail${taxdesc.count}" >${fn:trim(displayDescriptionTemp)}</textarea>
                                                    </td>
                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>                                        

                                            </c:when>
                                        </c:choose> 


                                        <div id="tr_FormulaAddRow" class="text-center" style="padding-top: 10px;display: none;">
                                            <a class="btn btn-success" onclick="AddRowDetailBillAble()">
                                                <i class="glyphicon glyphicon-plus"></i> Add
                                            </a>
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
                            <div class="panel panel-default ${panelborder}">                              
                                <div class="panel-body">
                                    <div class="col-xs-12 ">
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Remark&nbsp;</lable>                                         
                                        </div>
                                        <div class="col-sm-6" style="padding-left: 50px">
                                            <textarea  rows="3" cols="200" id="Remark" name="Remark" class="form-control" value="">${invoice.remark}</textarea>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 form-group"></div>
                                    <div class="col-xs-12 ">
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Text&nbsp;Amount&nbsp;:</lable>                                         
                                        </div>                                      
                                        <div class="col-sm-6" style="padding-left: 50px">
                                            <input  rows="3" cols="200" id="TextAmount" name="TextAmount" class="form-control" value="" readonly="">
                                        </div>
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Total&nbsp;Net&nbsp;:</lable>                                         
                                        </div>
                                        <div class="col-sm-3" >
                                            <input  rows="3" cols="200" id="TotalNet" name="TotalNet" class="form-control" value="" readonly="">
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
                            <div class="panel panel-default ${panelborder}">                              
                                <div class="panel-body">
                                    <div class="col-xs-12 ">
                                        <div class="col-md-2 text-right ">
                                            <select id="SelectTypePrint" name="SelectTypePrint" class="form-control">
                                                <option value="InvoiceReport">Invoice</option>
                                                <option value="InvoiceEmail">Invoice Email</option>
                                                <option value="Package">Package</option>
                                            </select>          
                                        </div>
                                        <div class="col-md-1 text-left " style="padding-left: 0px">
                                            <button type="button" class="btn btn-default" id="printButton" onclick="printInvoice('print')" data-toggle="modal" data-target="#PrintModal">
                                                <span id="SpanPrintInvoiceNew" class="glyphicon glyphicon-print"></span> Print
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-left " style="width: 125px">
                                            <button type="button" class="btn btn-default" id="sendEmailButton" onclick="printInvoice('email')" data-toggle="modal" data-target="#PrintModal">
                                                <span id="buttonEmail" class="glyphicon glyphicon-send" ></span> Send Email 
                                            </button>
                                        </div>
                                        
                                        <div class="col-md-2 text-right hidden" >
                                            <button type="button" class="btn btn-success" id="copyButton" onclick="printInvoice('email')" data-toggle="modal" data-target="#CopyModal">
                                                <span id="buttonCopy" class="glyphicon glyphicon-file" ></span> Copy 
                                            </button>
                                        </div>
                                        
                                        <div class="col-md-1 text-right ">
                                            <c:set var="isDisableVoid" value="disabled='true'" />
                                            <c:set var="isEnableVoid" value="style='display: none;'" />
                                            <c:set var="isSaveVoid" value="" />
                                            <c:if test="${result =='success'}">        
                                                <c:set var="isDisableVoid" value="" />
                                            </c:if>
                                            <c:if test="${result =='void'}">        
                                                <c:set var="isDisableVoid" value="style='display: none;'" />
                                                <c:set var="isEnableVoid" value="style='display: block;'" />
                                                <%--<c:if test="${roleName =='YES'}">--%>        
                                                    <%--<c:set var="isEnableVoid" value="" />--%>
                                                    <%--<c:set var="isSaveVoid" value="disabled='true'" />--%>
                                                    <%--<c:set var="textVoid" value="VOID" />--%>
                                                <%--</c:if>--%>
                                                <%--<c:if test="${roleName =='NO'}">--%>        
                                                    <%--<c:set var="isEnableVoid" value="disabled='true'" />--%>
                                                    <%--<c:set var="isSaveVoid" value="disabled='true'" />--%>
                                                    <%--<c:set var="textVoid" value="VOID" />--%>
                                                <%--</c:if>--%>
                                            </c:if>
                                            <c:if test="${invoice.MFinanceItemstatus.id == '2'}">        
                                                <c:set var="isDisableVoid" value="style='display: none;'" />
                                                <c:set var="isEnableVoid" value="style='display: block;'" />
                                                <%--<c:if test="${roleName =='YES'}">        
                                                    <c:set var="isEnableVoid" value="" />
                                                    <c:set var="isSaveVoid" value="disabled='true'" />
                                                    <c:set var="textVoid" value="VOID" />
                                                </c:if>
                                                <c:if test="${roleName =='NO'}">        
                                                    <c:set var="isEnableVoid" value="disabled='true'" />
                                                    <c:set var="isSaveVoid" value="disabled='true'" />
                                                    <c:set var="textVoid" value="VOID" />
                                                </c:if>--%>
                                            </c:if>
                                            <c:if test="${result =='cancelvoid'}">        
                                                <c:set var="isDisableVoid" value="" />
                                            </c:if>
                                            <c:if test="${invoice.MFinanceItemstatus.id == '1'}">        
                                                <c:set var="isDisableVoid" value="" />
                                            </c:if>
                                           <button type="button" class="btn btn-primary" onclick="EnableVoidInvoice();" data-toggle="modal" data-target="#EnableVoid" id="enableVoidButton" name="enableVoidButton"  ${isEnableVoid} >
                                                <span id="SpanEnableVoid" class="glyphicon glyphicon-ok" ></span>Cancel
                                            </button>
                                            
                                            <button type="button" class="btn btn-danger" onclick="DisableVoidInvoice();" data-toggle="modal" data-target="#DisableVoid" id="disableVoidButton" name="disableVoidButton" ${isDisableVoid} >
                                                <span id="SpanDisableVoid" class="glyphicon glyphicon-remove" ></span> Void
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="submit"  id="saveInvoice" name="saveInvoice" class="btn btn-success" ${isSaveVoid}>
                                                <span id="SpanSave" class="fa fa-save"></span> Save 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="clearScreenInvoice()" class="btn btn-success"  >
                                                <span id="SpanNew" class="fa fa-plus-circle"></span> New 
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
            </form>
        </div>
    </div>
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
                Are you confirm to void invoice ${invoice.invNo}?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick='DisableInvoice()' data-dismiss="modal">Void</button>               
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
                Are you confirm to cancel void invoice ${invoice.invNo}?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal" onclick='Enable()'>Cancel Void</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<!--Delete Detail Billable Modal-->
<div class="modal fade" id="DelDetailBill" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Delete Detail Billable </h4>
            </div>
            <div class="modal-body" id="DeleteDetailBillable">
                
            </div>
            <div class="modal-footer">  
                <button type="button" onclick="DeleteBill()" class="btn btn-danger" data-dismiss="modal">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade " id="DescriptionInvoiceDetailModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Description</h4>
            </div>
            
            <div class="modal-body" >
                <div class="row">
                    <div class="col-md-2">
                        <h5>Description</h5>
                    </div>
                    <div class="col-md-10">
                        <textarea id="InputDescriptionDetail" rows="12" style="width: 450px" class="form-control" >

                        </textarea>
<!--<input type="text" id="InputDescriptionDetail" value="" style="width: 400px;height: 200px;">-->
                    </div>
                </div>
                
            </div>
            <div class="modal-footer">  
                <button type="button" onclick="saveDescriptionDetail()" class="btn btn-success" data-dismiss="modal">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade " id="CopyModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Copy Invoice</h4>
            </div>
            
            <div class="modal-body" >
                You want copy invoice ? If copy click OK
            </div>
            <div class="modal-footer">  
                <button type="button" onclick="copyInvoice()" class="btn btn-success" data-dismiss="modal">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade " id="PrintModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Print</h4>
            </div>
            <div class="modal-body" >
                <div class="row">
                    <div class="col-md-5">
                        <h5>Sales Staff </h5>
                    </div>
                    <div class="col-md-7">
                        <select id="selectSalesStaff" name="selectSalesStaff" class="form-control">
                            <option value="0">Not Show Sales Staff</option>
                            <option value="1">Show Sales Staff</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <h5>Show Leader to Invoice </h5>
                    </div>
                    <div class="col-md-7">
                        <select id="selectLeader" name="selectLeader" class="form-control">
                            <option value="0">Not Show Leader</option>
                            <option value="1">Show Leader</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <h5>Payment by money Transfer</h5>
                    </div>
                    <div class="col-md-7">
                        <select id="selectPayment" name="selectPayment" class="form-control">
                            <option value="0">Not show</option>
                            <option value="SCB2">Payment Bank Siam commercial bank PCL</option>
                            <option value="BBL">Payment Bank Bangkok bank PCL</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <h5>Sign </h5>
                    </div>
                    <div class="col-md-7">
                        <select id="SelectSign" name="SelectSign" class="form-control">
                            <option value="">--Sign--</option>
                            <option value="benjaporn">Benjaporn</option>
                            <option value="pawina">Pawina</option>
                            <option value="supavadee">Supavadee</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer">  
                <button type="button" onclick="printInvoiceNew()" class="btn btn-success" data-dismiss="modal">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--Inv To Modal-->
<div class="modal fade" id="InvToModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Invoice To</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->
                <div style="text-align: right"> <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchInvoiceFrom" name="searchInvoiceFrom"/> </div> 
                <table class="display" id="InvToTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${listCustomerAgentInfo}">
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
                <div  class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog --> <!-- /.modal-dialog -->
</div>

<!--Sale Staff To Modal-->
<div class="modal fade" id="SaleStaffModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Sale Staff</h4>
            </div>
            <div class="modal-body">
                <script>
                    staff = [];
                </script>
                <table class="display" id="SaleStaffTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th>staff Name</th>
                        </tr>
                    </thead>                  
                    <tbody>                  
                        <c:forEach var="item" items="${listStaff}" varStatus="loop">
                            <tr class="packet">
                                <td class="staff-id hidden"><c:out value="${item.id}" /></td>
                                <td class="staff-code"><c:out value="${item.username}" /></td>
                                <td class="staff-name"><c:out value="${item.name}" /></td>                            
                            </tr>
                            <script>
                                staff.push({id: "${item.id}", code: "${item.username}", name: "${item.name}"});
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
<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
       $('.date').datetimepicker();
       $('.datemask').mask('0000-00-00');
       $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

       });
//       $('#MasterReservation tbody').on('click', 'tr', function () {
//            if ($(this).hasClass('row_selected')) {
//                $(this).removeClass('row_selected');
//                $('#hdGridSelected').val('');
//            }
//            else {
//                table.$('tr.row_selected').removeClass('row_selected');
//                $(this).addClass('row_selected');
//                $('#hdGridSelected').val($('#MasterReservation tbody tr.row_selected').attr("id"));
//            }
//        });
    
//        $('#DetailBillableTable tbody').on('click', 'tr', function () {
//            if ($(this).hasClass('row_selected')) {
//                $(this).removeClass('row_selected');
//                $('#hdGridSelected').val('');
//            }
//            else {
//                table.$('tr.row_selected').removeClass('row_selected');
//                $(this).addClass('row_selected');
//                $('#hdGridSelected').val($('#DetailBillableTable tbody tr.row_selected').attr("id"));
//            }
//        });
        
        $('#collapseExample${advanced.search}').on('shown.bs.collapse', function () {
            $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-down").addClass("glyphicon glyphicon-chevron-up");
        });

        $('#collapseExample${advanced.search}').on('hidden.bs.collapse', function () {
           $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-up").addClass("glyphicon glyphicon-chevron-down");
        });
    });
    
            $("#InvoiceForm")
        .bootstrapValidator({
            framework: 'bootstrap',
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {                
                InvTo: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input Invoice To'
                        }
                    }
                },
                InvToName: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input Invoice To Name'
                        }
                    }
                },
                ARCode: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input A/R Code'
                        }
                    }
                },
                InputInvDate: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input Invoice Date'
                        }
                    }
                } 
            }  
        });
</script>
<script type="text/javascript" charset="utf-8">
    var select = "<option value='' ></option>";
    var select_cost = "<option value='' ></option>";
    var selectType = "<option value='' ></option>";
    var defaultD = "";
    $(document).ready(function () {
        <c:forEach var="cur" items="${listCurrency}">
            select += "<option value='${cur.code}' ><c:out value='${cur.code}' /></option>";
            select_cost += "<option value='${cur.code}' ><c:out value='${cur.code}' /></option>";
        </c:forEach>
        <c:forEach var="ty" items="${listType}">
            selectType += "<option value='${ty.id}' ><c:out value='${ty.name}' /></option>";
        </c:forEach>
        
        var recipt = $('#checkRecipt').val();
        if(recipt === "yesReceipt"){
            console.log("C");
            $('#textAlertRecipt').show();
        }else if(recipt === "noReceipt"){
             $('#textAlertRecipt').hide();
        }
        var taxin = $('#checkTaxinvoice').val();
        if(taxin === "yesTaxinvoice"){
            $('#textAlertTaxinvoice').show();
        }else if(taxin === "noTaxinvoice"){
             $('#textAlertTaxinvoice').hide();
        }
    }); 
</script>
<c:if test="${defaultData != null}">        
    <script language="javascript">
       defaultD = ${defaultData.value};
    </script>
</c:if>
<input type="hidden" id="showvat" name="showvat" value="${showvat}">      
<input type="hidden" id="type" name="type" value="${param.type}">
<input type="hidden" id="resultAction" name="resultAction" value="${result}">
<input type="hidden" id="roleName" name="roleName" value="${roleName}">
<input type="hidden" id="InputDescriptionDetailId" name="InputDescriptionDetailId" value="">
<input type="hidden" id="resultText" name="resultText" value="${result}">
<input type="hidden" id="checkTaxinvoice" name="checkTaxinvoice" value="${checkTaxinvoice}">
<input type="hidden" id="checkRecipt" name="checkRecipt" value="${checkRecipt}">
<input type="hidden" id="typeBooking" name="typeBooking" value="${typeBooking}">
<input type="hidden" id="vatBase" name="vatBase" value="${vat}">
<input type="hidden" id="typePrint" name="typePrint" value="">
<input type="hidden" value="${textVoid}">
<input type="hidden" id="invoiceType" name="invoiceType" value="${invoiceType}">
<script type="text/javascript" src="js/Invoice.js"></script>
