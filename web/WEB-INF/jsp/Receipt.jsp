<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/Receipt.js"></script>--> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--<script type="text/javascript" src="js/workspace.js"></script>--> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>

<c:set var="dataPVList" value="${requestScope['PVList']}" />
<c:set var="type" value="${requestScope['typeReceipt']}" />
<input type="hidden" id="type" name="type" value="${param.type}">
<c:set var="page" value="${requestScope['page']}" />
<c:set var="customerAgentList" value="${requestScope['customerAgent']}" />
<c:set var="productListTable" value="${requestScope['Product_List']}" />
<c:set var="billTypeList" value="${requestScope['billTypeList']}" /> 
<c:set var="currencyList" value="${requestScope['currencyList']}" />
<c:set var="creditBankList" value="${requestScope['creditBankList']}" />
<c:set var="statusList" value="${requestScope['statusList']}" />
<c:set var="receipt" value="${requestScope['receipt']}" />
<c:set var="result" value="${requestScope['result']}" />
<c:set var="callPage" value="${requestScope['callPage']}" />
<c:set var="receiptDetailList" value="${requestScope['receiptDetailList']}" />
<c:set var="receiptCreditList" value="${requestScope['receiptCreditList']}" />
<c:set var="invoiceIdList" value="${requestScope['invoiceIdList']}" />
<c:set var="roleName" value="${requestScope['roleName']}" />
<c:set var="typeBooking" value="" />


<section class="content-header" >
    <c:set var="panelheader" value=""/>
    <c:set var="panelborder" value=""/>
    <c:if test="${receipt.MFinanceItemstatus.id == '2'}">
        <c:set var="receiptVoid" value="VOID" />
    </c:if>
    <h1>               
        <c:choose>
            <c:when test="${fn:contains(page , 'WT')}">
                <c:set var="typeReceipt" value="T" />
                <c:set var="typeDepartment" value="Wendy" />
                <c:set var="typeBooking" value="I" />
                <h4><b>Finance & Cashier - Receipt Temp Wendy   <font style="color: red">${receiptVoid}</font></b></h4>
                <c:set var="panelheader" value="wendyheader"/>
                <c:set var="panelborder" value="wendyborder"/>
            </c:when>
            <c:when test="${fn:contains(page , 'WV')}">
                <c:set var="typeReceipt" value="V" />
                <c:set var="typeDepartment" value="Wendy" />
                <c:set var="typeBooking" value="I" />
                <h4><b>Finance & Cashier - Receipt Vat Wendy   <font style="color: red">${receiptVoid}</font></b></h4>
                <c:set var="panelheader" value="wendyheader"/>
                <c:set var="panelborder" value="wendyborder"/>
            </c:when>    
            <c:when test="${fn:contains(page , 'OT')}">
                <c:set var="typeReceipt" value="T" />
                <c:set var="typeDepartment" value="Outbound" />
                <c:set var="typeBooking" value="O" />
                <h4><b>Finance & Cashier - Receipt Temp Outbound   <font style="color: red">${receiptVoid}</font></b></h4>
                <c:set var="panelheader" value="outboundheader"/>
                <c:set var="panelborder" value="outboundborder"/>
            </c:when>    
            <c:when test="${fn:contains(page , 'OV')}">
                <c:set var="typeReceipt" value="V" />
                <c:set var="typeDepartment" value="Outbound" />
                <c:set var="typeBooking" value="O" />
                <h4><b>Finance & Cashier - Receipt Vat Outbound   <font style="color: red">${receiptVoid}</font></b></h4>
                <c:set var="panelheader" value="outboundheader"/>
                <c:set var="panelborder" value="outboundborder"/>
            </c:when>    
            <c:when test="${fn:contains(page , 'IT')}">
                <c:set var="typeReceipt" value="T" />
                <c:set var="typeDepartment" value="Inbound" />
                <c:set var="typeBooking" value="I" />
                <h4><b>Finance & Cashier - Receipt Temp Inbound   <font style="color: red">${receiptVoid}</font></b></h4>
                <c:set var="panelheader" value="inboundborderheader"/>
                <c:set var="panelborder" value="inboundborder"/>
            </c:when>   
            <c:when test="${fn:contains(page , 'IV')}">
                <c:set var="typeReceipt" value="V" />
                <c:set var="typeDepartment" value="Inbound" />
                <c:set var="typeBooking" value="I" />
                <h4><b>Finance & Cashier - Receipt Vat Inbound   <font style="color: red">${receiptVoid}</font></b></h4>
                <c:set var="panelheader" value="inboundborderheader"/>
                <c:set var="panelborder" value="inboundborder"/>
            </c:when>    
        </c:choose>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Finance & Cashier</a></li>          
        <li class="active"><a href="#">Receipt</a></li>
    </ol>
</section>

<div style="padding-top: 15px;padding-right: 0px "ng-app=""> 
    <div class="row">
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/ReceiptMenu.html'"></div>
        </div>
        <div class="col-sm-10">
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Success!</strong> 
            </div>
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Unsuccess!</strong> 
            </div>
            <div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Delete Success!</strong> 
            </div>
            <div id="textAlertDivNotDelete"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Delete Unsuccess!</strong> 
            </div>       
            <div id="textAlertReceiveNo"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Receive no. not available !</strong> 
            </div>
            <div id="textAlertDuplicateProduct"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Not add duplicate detail!</strong> 
            </div>
            <div id="textAlertAmountOver"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Amount over than invoice.</strong> 
            </div>

        </div>               
        <div class="col-sm-10">
            <!--<hr/>-->
            <div class="row" style="padding-left: 15px;padding-right:15px"> 
                <input type="text" class="hidden" value="${typeBooking}">
                <c:choose>
                    <c:when test="${typeBooking == 'O'}">
                        <div id="AlertBookingRefno"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong>This Ref No can get billable detail from wendy only</strong> 
                        </div>
                    </c:when>
                    <c:when test="${typeBooking == 'I'}">
                        <div id="AlertBookingRefno"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong>This Ref No can get billable detail from outbound only</strong> 
                        </div>
                    </c:when>
                </c:choose>
            </div>
            <form action="${callPage}" method="post" id="ReceiptForm" name="ReceiptForm" role="form">
                <div role="tabpanel">
                    <!-- Nav tabs -->

<!--                    <ul class="nav nav-tabs " role="tablist">
                        <li role="presentation" class="active "><a href="#inv" aria-controls="inv" role="tab" data-toggle="tab">INV</a></li>
                        <li role="presentation" class=""><a href="#ref" aria-controls="ref" role="tab" data-toggle="tab">REF</a></li>
                        <li role="presentation" class=""><a href="#com" aria-controls="com" role="tab" data-toggle="tab">COM</a></li>
                        <h4><a class="col-xs-9 text-right" data-toggle="collapse" href="#collapseTab" aria-expanded="false" aria-controls="collapseTab">
                                <span id="arrowReceipt" class="arrowReceipt glyphicon glyphicon-chevron-up"></span>
                            </a></h4>
                    </ul>-->

<!--                     Tab BL 
                    <div class="panel panel-default ${panelborder}">
                        <div class="panel-body">
                            <div class="tab-content collapse in" id="collapseTab" aria-expanded="false">
                                <div role="tabpanel" class="tab-pane hidden active" id="inv">
                                    <div class="col-xs-12" style="padding-top: 20px; padding-left: 50px;padding-right: 50px">
                                        <div class="col-xs-1 text-right" style="width: 120px">
                                            <label class="control-label text-right">Invoice No </label>
                                        </div>
                                        <div class="col-xs-1 form-group" style="width: 200px" id="invoicenopanel">
                                            <div class="input-group">
                                                <input id="invoiceNo" name="invoiceNo" type="text" class="form-control" value="" onkeydown="invoicenoValidate()">
                                            </div>
                                        </div>
                                        <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxload1"  class="fa fa-spinner fa-spin hidden"></i></div>
                                        <div class="col-xs-1 text-left"  style="width: 100px">
                                            <button style="height:30px" type="button"  id="ButtonSearchInvoice"  name="ButtonSearchInvoice" onclick="searchInvoice();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search </button>
                                        </div>
                                        Invoice Table
                                        <div class="row" style="padding-left: 10px;padding-right: 10px">
                                            <table id="InvoiceListTable" class="display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr class="datatable-header" >
                                                        <th style="width:10%;">No</th>
                                                        <th style="width:30%;">Description</th>
                                                        <th style="width:20%;">Amount</th>
                                                        <th style="width:20%;">Amount Local</th>
                                                        <th style="width:20%;">Currency</th>
                                                        <th style="width:5%;">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                 Tab REF 
                                <div role="tabpanel" class="tab-pane hidden" id="ref">
                                    <div class="col-xs-12" style="padding-top: 20px; padding-left: 50px;padding-right: 50px">
                                        <div class="col-xs-1 text-right" style="width: 120px">
                                            <label class="control-label text-right">Ref No </label>
                                        </div>
                                        <div class="col-xs-1 form-group" style="width: 200px" id="refnopanel">
                                            <div class="input-group">
                                                <input id="refNo" name="refNo" type="text" class="form-control" value="" onkeydown="refnoValidate()">
                                            </div>
                                        </div>
                                        <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxload2"  class="fa fa-spinner fa-spin hidden"></i></div>
                                        <div class="col-xs-1 text-left"  style="width: 100px">
                                            <button style="height:30px" type="button"  id="ButtonSearchRefNo"  name="ButtonSearchRefNo" onclick="searchRefNo();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search </button>
                                        </div>
                                        RefNo Table
                                        <div class="row" style="padding-left: 10px;padding-right: 10px">
                                            <table id="RefNoListTable" class="display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr class="datatable-header" >
                                                        <th style="width:10%;">No</th>
                                                        <th style="width:40%;">Description</th>
                                                        <th style="width:20%;">Amount</th>
                                                        <th style="width:20%;">Currency</th>
                                                        <th style="width:10%;">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                 Tab COM 
                                <div role="tabpanel" class="tab-pane hidden" id="com">
                                    <div class="col-xs-6" style="padding-top: 20px; border-right:solid 1px #D9D9D9">
                                        <div class="col-xs-1 text-left" style="width: 200px">
                                            <label class="control-label text-right">Air Commission </label>
                                        </div>
                                        <div class="col-xs-1 text-right" style="width: 120px">
                                            <label class="control-label text-right">Search</label>
                                        </div>
                                        <div class="col-xs-1 form-group" style="width: 155px" >
                                            <input style="width: 150px" id="searchPaymentNoAir" name="searchPaymentNoAir" type="text" class="form-control" value="">
                                        </div>
                                        <div class="col-xs-1  text-left" style="padding-top: 6px;width: 4px"><i id="ajaxload3"  class="fa fa-spinner fa-spin hidden"></i></div>
                                        Invoice Table
                                        <div class="row" style="padding-left: 10px;padding-right: 10px">
                                            <table id="AircommissionTable" class="display" width="100%">
                                                <thead>
                                                    <tr class="datatable-header" >
                                                        <th style="width:10%;">No</th>
                                                        <th style="width:10%;">Airline</th>
                                                        <th style="width:10%;">Commission</th>
                                                        <th style="width:10%;">Is Use</th>
                                                        <th style="width:10%;">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-xs-6" style="padding-top: 20px;">
                                        <div class="col-xs-1 text-left" style="width: 200px">
                                            <label class="control-label text-right">Tour Commission </label>
                                        </div>
                                        <div class="col-xs-1 text-right" style="width: 135px">
                                            <label class="control-label text-right">Search</label>
                                        </div>
                                        <div class="col-xs-1 form-group" style="width: 155px" >
                                            <input style="width: 150px" id="searchPaymentNoTour" name="searchPaymentNoTour" type="text" class="form-control" value="">
                                        </div>
                                        <div class="col-xs-1  text-left" style="padding-top: 6px;width: 4px"><i id="ajaxload4"  class="fa fa-spinner fa-spin hidden"></i></div>

                                        <div class="row" style="padding-left: 10px;padding-right: 10px">
                                            <table id="TourcommissionTable" class="display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr class="datatable-header" >
                                                        <th style="width:10%;">No</th>
                                                        <th style="width:15%;">Commission</th>
                                                        <th style="width:15%;">Is Use</th>
                                                        <th style="width:10%;">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>-->

                    <div class="panel panel-default ${panelborder}">
                        <div class="panel-heading ${panelheader}">
                            <h4 class="panel-title">Receipt Detail</h4>
                        </div>
                        <div class="panel-body"  style="padding-right: 0px;">
                            <div class="col-xs-7" style="padding-top: 0px;">
                                <div class="col-xs-1 text-right" style="width: 135px; margin-top: -10px" >
                                    <label class="control-label text-right">Receive No </label>                                    
                                </div> 
                                <div class="col-xs-1" style="width: 80px; margin-top: -10px" id='receivenumber'>
                                    <input type="hidden" class="form-control" id="wildCardSearch" name="wildCardSearch"  value="${requestScope['wildCardSearch']}" >
                                    <input type="hidden" class="form-control" id="keyCode" name="keyCode"  value="" >
                                    <input id="receiveId" name="receiveId" type="hidden" class="form-control" maxlength="11" value="${receipt.id}">
                                    <input id="receiveNo" name="receiveNo" type="text" style="width: 80px" class="form-control" maxlength="20" value="${receipt.recNo}">
                                </div>
                                <div class="col-xs-1 text-right" style="width: 8px;"></div>
                                <div class="col-xs-1 text-right" style="width: 80px; margin-top: -10px">
                                    <button style="height:34px" type="button"  id="ButtonSearch"  name="ButtonSearch" onclick="searchReceiveNo();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search</button>
                                </div>
                                <div class="col-xs-1 text-right" style="width: 125px; margin-top: -10px">
                                    <label class="control-label text-right">Receipt Date<font style="color: red">*</font></label>
                                </div>
                                <div class="col-xs-1 form-group" style="width: 155px; margin-top: -10px">
                                    <div class='input-group date' id="ReceiveDate">
                                        <input id="receiveFromDate" name="receiveFromDate"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['receiveFromDate']}">
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>  
                                </div>
                                <div class="col-xs-1 text-right" style="width: 135px; margin-top: -10px">
                                    <label class="control-label text-right" for="codeBillto">Receive From <font style="color: red">*</font></label> 
                                </div>
                                <div class="form-group col-xs-1 text-right" style="width: 470px; margin-top: -10px">
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="receiveFromId" name="receiveFromId" value=""/>
                                        <input type="text" class="form-control" id="receiveFromCode" name="receiveFromCode" maxlength="11" value="${receipt.recFrom}" style="text-transform:uppercase"/>
                                        <span class="input-group-addon" id="receive_modal"  data-toggle="modal" data-target="#ReceiveFromModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-xs-1 text-right" style="width: 135px; margin-top: -10px">
                                    <label class="control-label text-right">Name </label> 
                                </div>
                                <div class="form-group col-xs-1 text-right" style="width: 470px; margin-top: -10px">
                                    <input type="text" class="form-control" id="receiveFromName" name="receiveFromName" value="${receipt.recName}">                           
                                </div>
                                <div class="col-xs-1 text-right" style="width: 135px; margin-top: -10px">
                                    <label class="control-label text-right">Address </label>  
                                </div>
                                <div class="form-group col-xs-1 text-right" style="width: 470px; margin-top: -10px">
                                    <div class="input-group">                                    
                                        <textarea rows="3" class="form-control" id="receiveFromAddress" name="receiveFromAddress" style="width: 271%" value="${receipt.recAddress}">${receipt.recAddress}</textarea>  
                                    </div>                               
                                </div>
                                
                            </div>
                            <div class="col-xs-5" style="padding-top: 0px;">
                                <div class="col-xs-1 text-right" style="width: 130px; margin-top: -10px">
                                    <label class="control-label text-right">Receive Date</label>
                                </div>
                                <div class="col-xs-1 form-group" style="width: 200px; margin-top: -10px">
                                    <div class='input-group date'>
                                        <input id="receiveDate" name="receiveDate"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['receiveDate']}">
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>  
                                </div>
                                <div class="col-xs-1 text-right" style="width: 130px; margin-top: -10px">
                                    <label class="control-label text-right">Payment <font style="color: red">*</font></label>
                                </div>
                                <div class="form-group col-xs-1" style="width: 200px; margin-top: -10px">
                                    <select name="inputStatus" id="inputStatus" class="form-control">
                                        <option value="">--- Status ---</option> 
                                        <c:forEach var="table" items="${statusList}" >
                                            <c:set var="select" value="" />
                                            <c:set var="selectedId" value="${receipt.MAccpay.id}" />
                                            <c:if test="${table.id == selectedId}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option value="${table.id}" ${select}>${table.name}</option>  
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-xs-1 text-right" style="width: 130px; margin-top: -10px">
                                    <label class="control-label text-right">A/R Code <font style="color: red">*</font></label>                                    
                                </div>
                                <div class="form-group col-xs-1" style="width: 200px; margin-top: -10px">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="arCode" name="arCode" maxlength="11" value="${receipt.arCode}" readonly="" />
                                    </div>
                                </div>
                                <div class="col-xs-1 text-right" style="width: 130px; margin-top: -10px">
                                    <label class="control-label text-right">Remark </label> 
                                </div>
                                <div class="form-group col-xs-1 text-right" style="width: 200px; margin-top: -10px">
                                    <div class="input-group">                                    
                                        <textarea rows="3" class="form-control" id="remark" name="remark" style="width: 105%" value="${receipt.remark}">${receipt.remark}</textarea>  
                                    </div>                               
                                </div>    
<!--                                <div class="form-group col-xs-1 text-right" style="width: 170px; margin-top: -10px">
                                    <input type="text" class="form-control" id="remark" name="remark" value="${receipt.remark}">                           
                                </div>-->
                            </div>
                        </div>
                        <div class="col-xs-12" style="margin-top: -25px">
                            <div class="col-xs-12">
                                <div class="col-xs-1  text-right" style="width: 147px">
                                    <label class="control-label text-right" > Search &nbsp;&nbsp;</label>
                                </div>
                                <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" onclick="showSearchInvno()">
                                    <span id="SpanEdit${advanced.search}">Invoice No.</span>
                                </a>
                                <c:if test="${typeDepartment == 'Inbound'}">   
                                    <label class="control-label text-right">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                </c:if>
                                <c:if test="${typeDepartment != 'Inbound'}">
                                    <label class="control-label text-right">&nbsp;&nbsp;/&nbsp;&nbsp;</label>
                                    <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" onclick="showSearchRefno()">
                                        <span id="SpanEdit${advanced.search}">Ref No.</span>
                                    </a>
                                </c:if>
                                <c:if test="${typeDepartment == 'Wendy'}">
                                    <label class="control-label text-right">&nbsp;&nbsp;/&nbsp;&nbsp;</label>
                                    <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" onclick="showSearchAirCom()">
                                        <span id="SpanEdit${advanced.search}">Air Commission</span>
                                    </a>
                                    <label class="control-label text-right">&nbsp;&nbsp;/&nbsp;&nbsp;</label>
                                    <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" onclick="showSearchTourCom()">
                                        <span id="SpanEdit${advanced.search}">Tour Commission</span>
                                    </a>
                                </c:if>
                            </div>
                        </div>            

                        <div class="col-xs-12" id="searchinvtext">
                           <div class="col-xs-1 text-right" style="width: 160px">
                               <label class="control-label text-right">Invoice No </label>
                           </div>
                           <div class="col-xs-1 form-group" style="width: 200px" id="invoicenopanel">
                               <div class="input-group">
                                   <input id="invoiceNo" name="invoiceNo" type="text" class="form-control" value="" onkeydown="invoicenoValidate()">
                               </div>
                           </div>
                           <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxload1"  class="fa fa-spinner fa-spin hidden"></i></div>
                           <div class="col-xs-1 text-left"  style="width: 100px">
                               <button style="height:30px" type="button"  id="ButtonSearchInvoice"  name="ButtonSearchInvoice" onclick="searchInvoice();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search </button>
                           </div>
                        </div>
                        <div class="col-xs-12 hidden" style="margin-bottom: 10px" id="searchinvtable">
                            <div class="row" style="padding-left: 10px;padding-right: 10px">
                                <table id="InvoiceListTable" class="display" cellspacing="0" width="100%">
                                    <thead>
                                        <tr class="datatable-header" >
                                            <th style="width:10%;">No</th>
                                            <th style="width:30%;">Description</th>
                                            <th style="width:20%;">Amount</th>
                                            <th style="width:20%;">Amount Local</th>
                                            <th style="width:20%;">Currency</th>
                                            <th style="width:5%;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
          
                        <div class="col-xs-12 hidden" id="searchreftext">
                            <div class="col-xs-1 text-right" style="width: 160px">
                                <label class="control-label text-right">Ref No </label>
                            </div>
                            <div class="col-xs-1 form-group" style="width: 200px" id="refnopanel">
                                <div class="input-group">
                                    <input id="refNo" name="refNo" type="text" class="form-control" value="" onkeydown="refnoValidate()">
                                </div>
                            </div>
                            <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxload2"  class="fa fa-spinner fa-spin hidden"></i></div>
                            <div class="col-xs-1 text-left"  style="width: 100px">
                                <button style="height:30px" type="button"  id="ButtonSearchRefNo"  name="ButtonSearchRefNo" onclick="searchRefNo();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search </button>
                            </div>
                        </div>
                        <!--RefNo Table-->
                        <div class="col-xs-12 hidden" style="margin-bottom: 10px" id="searchreftable">
                            <div class="row" style="padding-left: 10px;padding-right: 10px">
                                <table id="RefNoListTable" class="display" cellspacing="0" width="100%">
                                    <thead>
                                        <tr class="datatable-header" >
                                            <th style="width:10%;">No</th>
                                            <th style="width:40%;">Description</th>
                                            <th style="width:20%;">Amount</th>
                                            <th style="width:20%;">Currency</th>
                                            <th style="width:10%;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="col-xs-12 hidden" id="searchaircomtext">
                            <div class="col-xs-1 text-right" style="width: 160px">
                                <label class="control-label text-right">Air Commission </label>
                            </div>
                            <div class="col-xs-1 form-group" style="width: 200px" >
                                <input style="width: 170px" id="searchPaymentNoAir" name="searchPaymentNoAir" type="text" class="form-control" value="">
                            </div>
                            <!--<div class="col-xs-1  text-left" style="padding-top: 6px;width: 4px"><i id="ajaxload3"  class="fa fa-spinner fa-spin hidden"></i></div>-->
                            <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxload3"  class="fa fa-spinner fa-spin hidden"></i></div>
                            <div class="col-xs-1 text-left"  style="width: 100px">
                                <button style="height:30px" type="button"  id="ButtonSearchPaymentNoAir"  name="ButtonSearchPaymentNoAir" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search </button>
                            </div>
                        </div>    
                        <!--Invoice Table-->
                        <div class="col-xs-12 hidden" style="margin-bottom: 10px" id="searchaircomtable">
                            <div class="row" style="padding-left: 10px;padding-right: 10px">
                                <table id="AircommissionTable" class="display" cellspacing="0" width="100%">
                                    <thead>
                                        <tr class="datatable-header" >
                                            <th style="width:10%;">No</th>
                                            <th style="width:40%;">Airline</th>
                                            <th style="width:30%;">Commission</th>
                                            <th style="width:10%;">Is Use</th>
                                            <th style="width:10%;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-xs-12 hidden" id="searchtourcomtext">
                            <div class="col-xs-1 text-right" style="width: 160px">
                                <label class="control-label text-right">Tour Commission </label>
                            </div>
                            <div class="col-xs-1 form-group" style="width: 200px" >
                                <input style="width: 170px" id="searchPaymentNoTour" name="searchPaymentNoTour" type="text" class="form-control" value="">
                            </div>
                            <div class="col-xs-1 text-right" style="width: 8px"><i id="ajaxload4"  class="fa fa-spinner fa-spin hidden"></i></div>
                            <div class="col-xs-1 text-left"  style="width: 100px">
                                <button style="height:30px" type="button"  id="ButtonSearchPaymentNoTour"  name="ButtonSearchPaymentNoTour" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search </button>
                            </div>
                        </div>
                        <div class="col-xs-12 hidden" style="margin-bottom: 10px" id="searchtourcomtable">
                            <div class="row" style="padding-left: 10px;padding-right: 10px">
                                <table id="TourcommissionTable" class="display" cellspacing="0" width="100%">
                                    <thead>
                                        <tr class="datatable-header" >
                                            <th style="width:10%;">No</th>
                                            <th style="width:40%;">Commission</th>
                                            <th style="width:30%;">Is Use</th>
                                            <th style="width:20%;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-xs-12" style="margin-bottom: 5px" >
                            <div id="textAlertCurrencyAmountNotEmpty"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <strong>Currency Amount Not Empty</strong> 
                            </div>
                            <div id="textAlertCurrencyAmountNotMatch"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <strong>Currency Amount Not Match</strong> 
                            </div>
                        </div>
                        <!---Table-->
                        <div class="row" style="padding-right: 10px;padding-left: 10px;padding-bottom:  10px;">
                            <div class="col-md-12 ">
                                <table id="ReceiptListTable" class="display" cellspacing="0" width="100%">
                                    <thead>
                                        <tr class="datatable-header">
                                            <th style="width:9%;">Product</th>
                                            <th style="width:12%;">Description</th>
                                            <th style="width:10%;">Cost</th>
                                            <th style="width:7%;">Cur</th>
                                            <th style="width:4%;"><u>Is Vat</u></th>
                                            <th style="width:2%;">Vat</th>
                                            <!--<th style="width:10%;">Gross</th>-->
                                            <th style="width:10%;">Amount</th>
                                            <th style="width:7%;">Cur</th>
                                            <th style="width:7%;">Ex Rate</th>
                                            <th style="width:3%;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody> 
                                        <c:forEach var="table" items="${receiptDetailList}" varStatus="i">
                                            <tr>
                                        <input type="hidden" name="count${i.count}" id="count${i.count}" value="${i.count}">
                                        <input id="invId${i.count}"  name="invId${i.count}"   type="hidden" value="${table.invoiceDetail.id}" >
                                        <input id="invoiceTableNo${i.count}"  name="invoiceTableNo${i.count}"   type="hidden" value="${table.invoiceDetail.invoice.invNo}" >
                                        <input id="invoiceTableId${i.count}"  name="invoiceTableId${i.count}"   type="hidden" value="${table.invoiceDetail.invoice.id}" >
                                        <input type="hidden" name="tableId${i.count}" id="tableId${i.count}" value="${table.id}">
                                        <input id="receiveAmountTemp${i.count}" name="receiveAmountTemp${i.count}"  type="hidden" value="${table.amount}" >
                                        <input id="paymentTourId${i.count}" name="paymentTourId${i.count}"  type="hidden" value="${table.paymentDetailWendy.id}" >
                                        <input id="paymentId${i.count}" name="paymentId${i.count}"  type="hidden" value="${table.paymentAirticket.id}" >
                                        <input id="DescriptionReceiptDetail${i.count}" name="DescriptionReceiptDetail${i.count}"  type="hidden" value="${table.displayDescription}" >
                                        <td>
                                            <select class="form-control" name="receiveProduct${i.count}" id="receiveProduct${i.count}">
                                                <option  value="" >---------</option>
                                                <c:forEach var="product" items="${billTypeList}" varStatus="status">                                       
                                                    <c:set var="select" value="" />
                                                    <c:if test="${product.id == table.MBilltype.id}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option  value="${product.id}" ${select}>${product.name}</option>
                                                </c:forEach>
                                            </select>                                                                  
                                        </td>
                                        <td><input maxlength="255" id="receiveDes${i.count}" name="receiveDes${i.count}" type="text" class="form-control" value="${table.description}"></td>
                                        <td><input id="receiveCost${i.count}"  name="receiveCost${i.count}"  type="text" class="form-control decimal"  value="${table.cost}" readonly=""></td>
                                        <td>                                   
                                            <select class="form-control" name="receiveCurCostTemp${i.count}" id="receiveCurCostTemp${i.count}" disabled="disabled">
                                                <option  value="" >---------</option>
                                                <c:forEach var="curCost" items="${currencyList}" varStatus="status">                                       
                                                    <c:set var="select" value="" />
                                                    <c:if test="${curCost.code == table.curCost}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option  value="${curCost.code}" ${select} >${curCost.code}</option>
                                                </c:forEach>
                                            </select>                                                                  
                                        </td>
                                        <td class="hidden">                                   
                                            <select class="form-control" name="receiveCurCost${i.count}" id="receiveCurCost${i.count}" >
                                                <option  value="" >---------</option>
                                                <c:forEach var="curCost" items="${currencyList}" varStatus="status">                                       
                                                    <c:set var="select" value="" />
                                                    <c:if test="${curCost.code == table.curCost}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option  value="${curCost.code}" ${select} >${curCost.code}</option>
                                                </c:forEach>
                                            </select>                                                                  
                                        </td>
                                        <td align="center">
                                            <c:choose>
                                                <c:when test="${typeReceipt == 'V'}">
                                                    <c:choose>
                                                        <c:when test="${table.isVat == '1'}">
                                                            <input type="checkbox" checked name="receiveIsVat${i.count}" id="receiveIsVat${i.count}" onclick="handleClick(this,${i.count});" value="${table.isVat}" >
                                                        </c:when>
                                                        <c:when test="${table.isVat == '0'}">
                                                            <input type="checkbox"  name="receiveIsVat${i.count}" id="receiveIsVat${i.count}" onclick="handleClick(this,${i.count});" value="${table.isVat}" >
                                                        </c:when>
                                                    </c:choose>      
                                                </c:when>
                                                <c:when test="${typeReceipt == 'T'}">
                                                    <c:choose>
                                                        <c:when test="${table.isVat == '1'}">
                                                            <input type="checkbox" checked name="receiveIsVat${i.count}" id="receiveIsVat${i.count}" onclick="return false" value="${table.isVat}" readonly="">
                                                        </c:when>
                                                        <c:when test="${table.isVat == '0'}">
                                                            <input type="checkbox"  name="receiveIsVat${i.count}" id="receiveIsVat${i.count}" onclick="return false" value="${table.isVat}" readonly="">
                                                        </c:when>
                                                    </c:choose>
                                                </c:when>          
                                            </c:choose>
                                        </td> 
                                        <td align="center">
                                            <fmt:parseNumber var="vatShow" type="number" value="${requestScope['vat']}" />
                                            <c:choose>
                                                <c:when test="${table.isVat == '1'}">
                                                    <div id="receiveVat${i.count}" name="receiveVat${i.count}"  value="">${vatShow}</div>                                                        
                                                </c:when>
                                                <c:when test="${table.isVat == '0'}">
                                                    <div id="receiveVat${i.count}" name="receiveVat${i.count}" style="display:none" ></div>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td><input id="receiveAmount${i.count}" name="receiveAmount${i.count}" type="text" class="form-control decimal" value="${table.amount}" onfocusout="calculateGrandTotal();"></td>
                                        <td>                                   
                                            <select class="form-control" name="receiveCurrency${i.count}" id="receiveCurrency${i.count}">
                                                <option  value="" >---------</option>
                                                <c:forEach var="curAmount" items="${currencyList}" varStatus="status">                                       
                                                    <c:set var="select" value="" />
                                                    <c:if test="${curAmount.code == table.curAmount}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option  value="${curAmount.code}" ${select}>${curAmount.code}</option>
                                                </c:forEach>
                                            </select>                                                                  
                                        </td>
                                        <td><input type="text" value="${table.exRate}" id="receiveExRate${i.count}" name="receiveExRate${i.count}" class="form-control decimalexrate"></td>
                                        <td class="hidden"><input type="text" value="${table.exRate}" id="curExRateTemp${i.count}" name="curExRateTemp${i.count}" class="form-control" ></td>
                                        <td class="text-center">
                                            <a href="#/inv" data-toggle="modal" data-target="#DescriptionReceiptDetailModal" onclick="getDescriptionDetail('${i.count}')"><span class="glyphicon glyphicon-th-list"></span></a>&nbsp
                                            <a class="remCF"><span id="SpanRemove${i.count}" onclick="deleteReceiptList('${table.id}', '${i.count}');" class="glyphicon glyphicon-remove deleteicon "></span></a>
                                        </td>                                   
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>      
                            </div>
                        </div>
                        <input type="hidden" class="form-control" id="selectPrint" name="selectPrint"  value="" >
                        <input type="hidden" id="InputDescriptionDetailId" name="InputDescriptionDetailId" value="">
                        <!--<input type="hidden" name="mAccPayBillable" id="mAccPayBillable" value="">-->
                        <input type="hidden" id="typeBooking" name="typeBooking" value="${typeBooking}">
                        <input type="hidden" name="sumCreditAmountTemp" id="sumCreditAmountTemp" value="">
                        <input type="hidden" name="sumAmountBeforeSave" id="sumAmountBeforeSave" value="">
                        <input type="hidden" name="amountTemp" id="amountTemp" value="">
                        <input type="hidden" name="receiptIdDelete" id="receiptIdDelete" value="">
                        <input type="hidden" name="receiptDetailIdDelete" id="receiptDetailIdDelete" value="">
                        <input type="hidden" name="receiptRowDelete" id="receiptRowDelete" value="">
                        <input type="hidden" name="receiptCreditIdDelete" id="receiptCreditIdDelete" value="">
                        <input type="hidden" name="receiptCreditRowDelete" id="receiptCreditRowDelete" value="">
                        <input type="hidden" name="action" id="action" value="">
                        <input type="hidden" class="form-control" id="countRowCredit" name="countRowCredit" value="${requestScope['creditRowCount']}" />
                        <input type="hidden" class="form-control" id="counter" name="counter" value="${requestScope['productRowCount']}" />
                        <fmt:parseNumber var="mVat" type="number" value="${requestScope['vat']}" />
                        <input type="hidden" name="vatValue" id="vatValue" value="${mVat}">
                        <input type="hidden" name="searchReceipt" id="searchReceipt" value="${requestScope['searchReceipt']}">
                        <select class="hidden" name="billTypeList" id="billTypeList">
                            <c:forEach var="product" items="${billTypeList}" varStatus="status">                                
                                <option  value="${product.id}">${product.name}</option>
                            </c:forEach>
                        </select>
                        <select class="hidden" name="currencyList" id="currencyList">
                            <c:forEach var="cur" items="${currencyList}" varStatus="status">                                
                                <option  value="${cur.code}">${cur.code}</option>
                            </c:forEach>
                        </select>
                        <select class="hidden" name="creditBankList" id="creditBankList">
                            <c:forEach var="bank" items="${creditBankList}" varStatus="status">                                
                                <option  value="${bank.id}">${bank.name}</option>
                            </c:forEach>
                        </select>
                        <div id="tr_ProductDetailAddRow" class="text-center hide" style="padding-top: 10px">
                            <a class="btn btn-success" onclick="AddRowProduct(1)">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                        <div class="row" style="padding-top: 15px;padding-bottom:  15px; padding-left:  650px;">
                            <div class="col-xs-1 text-right" style="width: 130px">
                                <label class="control-label text-right">Grand Total </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <input type="text" class="form-control text-right" id="grandTotal" name="grandTotal" value="" readonly=""/>
                            </div>
                        </div>
                    </div>
                    <div id="textAlertReceiveAmount"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Incorrect Receive Amount!</strong> 
                    </div>     
                    <div class="panel panel-default ${panelborder}">
                        <div class="panel-body"  style="padding-right: 0px;">
                            <div class="col-xs-12">
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">W/T </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px" id="withTax" name="withTax" type="text" class="form-control decimal" value="${receipt.withTax}">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Cash </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px" id="cashAmount" name="cashAmount" type="text" class="form-control decimal" value="${receipt.cashAmount}" >
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Cash(-) </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <input id="cashMinusAmount" name="cashMinusAmount" type="text" class="form-control decimal" value="${receipt.cashMinusAmount}" >
                                    </div>
                                    <div class="col-xs-1 text-left" style="width: 130px">
                                        <label class="control-label text-right">Bank Transfer </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px" id="bankTransfer" name="bankTransfer" type="text" class="form-control decimal" value="${receipt.bankTransfer}" >
                                    </div>
                                </div><hr/>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Chq Bank </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px" id="chqBank1" name="chqBank1" type="text" class="form-control" value="${receipt.chqBank1}" maxlength="100">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Chq No </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px" id="chqNo1" name="chqNo1" type="text" class="form-control" value="${receipt.chqNo1}" maxlength="100">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Date </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="chqDate1" name="chqDate1"  type="text" 
                                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['chqDate1']}">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px" id="chqAmount1" name="chqAmount1" type="text" class="form-control decimal" value="${receipt.chqAmount1}">
                                    </div>
                                    <div class="col-xs-1" style="width: 50px;">
                                        <h4><a class="col-xs-1">
                                                <span class="glyphicon glyphicon-plus-sign" id="addChqButton"></span>
                                        </a></h4>                                        
                                    </div>
                                </div>
                                <div class="row hidden active" id="addChq" style="padding-top: 8px ">
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Chq Bank </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px" id="chqBank2" name="chqBank2" type="text" class="form-control" value="${receipt.chqBank2}" maxlength="100">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Chq No </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input  style="width: 130px" id="chqNo2" name="chqNo2" type="text" class="form-control" value="${receipt.chqNo2}"  maxlength="100">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Date </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="chqDate2" name="chqDate2"  type="text" 
                                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['chqDate2']}">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px"  id="chqAmount2" name="chqAmount2" type="text" class="form-control decimal" value="${receipt.chqAmount2}">
                                    </div>
                                    <div class="col-xs-1" style="width: 50px ;">
                                        <h4><a class="col-xs-1">
                                            <span class="glyphicon glyphicon-remove deleteicon" id="deleteChqButton"></span>
                                        </a></h4>                                        
                                    </div>
                                </div>
                                <hr/>
                                <!----- Credit Detail ----->

                                <table class="display" id="CreditDetailTable">
                                    <thead class="datatable-header">
                                        <tr>
                                            <th style="width:22%;">Credit Card</th>
                                            <th style="width:22%;">No</th>
                                            <th style="width:22%;">Expired</th>
                                            <th style="width:22%;">Amount</th>
                                            <th style="width:20%;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="table" items="${receiptCreditList}" varStatus="i">
                                            <tr>
                                        <input type="hidden" name="countCredit${i.count}" id="countCredit${i.count}" value="${i.count}">
                                        <input type="hidden" name="tableCreditId${i.count}" id="tableCreditId${i.count}" value="${table.id}">
                                        <td>                                   
                                            <select class="form-control" name="creditBank${i.count}" id="creditBank${i.count}">
                                                <option  value="" >---------</option>
                                                <c:forEach var="credit" items="${creditBankList}" varStatus="status">                                       
                                                    <c:set var="select" value="" />
                                                    <c:if test="${credit.id == table.MCreditBank.id}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option  value="${credit.id}" ${select}>${credit.name}</option>
                                                </c:forEach>
                                            </select>                                                                  
                                        </td>
                                        <td><input maxlength="20" id="creditNo${i.count}" name="creditNo${i.count}" type="text" class="form-control" value="${table.creditNo}"></td>
                                        <td><div class="input-group date">
                                                <input id="creditExpired${i.count}" name="creditExpired${i.count}"  type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${table.creditExpire}">
                                                <span class="input-group-addon spandate" style="padding : 1px 10px;"><span class="glyphicon glyphicon-calendar"></span></span>
                                            </div>
                                        </td>
                                        <td><input id="creditAmount${i.count}" name="creditAmount${i.count}" type="text" class="form-control text-right" onkeyup="insertCommas(this)" value="${table.creditAmount}"></td>                                                           
                                        <td class="text-center">
                                            <a class="remCF"><span id="SpanRemove${i.count}" onclick="deleteCreditList('${table.id}', '${i.count}');" class="glyphicon glyphicon-remove deleteicon "></span></a>
                                        </td>                                   
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div id="tr_CreditDetailAddRow" class="text-center hide" style="padding-top: 10px">
                                    <a class="btn btn-success" onclick="AddRowCredit(1)">
                                        <i class="glyphicon glyphicon-plus"></i> Add
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default ${panelborder}">
                        <div class="panel-body"  style="padding-right: 0px;">
                            <div class="col-xs-12">

                                <div class="col-md-1 text-left " style="width: 99px">
                                    <button type="button" class="btn btn-default" onclick="printReceipt(1)">
                                        <span id="buttonPrint" class="glyphicon glyphicon-print" ></span> Receipt
                                    </button>
                                </div>
                                <div class="col-md-1 text-left " style="width: 138px">
                                    <button type="button" class="btn btn-default" onclick="printReceipt(2)">
                                        <span id="buttonPrint" class="glyphicon glyphicon-print" ></span> Receipt Email
                                    </button>
                                </div>
                                <div class="col-md-1 text-left " style="width: 96px">
                                    <button type="button" class="btn btn-default" onclick="printReceipt(3)">
                                        <span id="buttonPrint" class="glyphicon glyphicon-print" ></span> Invoice
                                    </button>
                                </div>
                                <div class="col-md-1 text-left " style="width: 136px">
                                    <button type="button" class="btn btn-default" onclick="printReceipt(4)">
                                        <span id="buttonPrint" class="glyphicon glyphicon-print" ></span> Invoice Email
                                    </button>
                                </div>

                                <div class="col-md-1 text-left " style="width: 136px">
                                    <button type="button" class="btn btn-default" onclick="sendEmailReceipt()">
                                        <span id="buttonEmailRec" class="glyphicon glyphicon-send" ></span> Send Receipt
                                    </button>
                                </div>

                                <div class="col-md-1 text-left " style="width: 140px">
                                    <button type="button" class="btn btn-default" onclick="printReceipt(5)">
                                        <span id="buttonEmailInv" class="glyphicon glyphicon-send" ></span> Send Invoice
                                    </button>
                                </div>

                                <div class="col-md-2 text-right"> 
                                </div>
                                <div class="col-md-1 text-right">                                    
                                    <button type="button" class="btn btn-default hidden" onclick="copyReceipt()">
                                        <span id="ButtonCopy" class="glyphicon glyphicon-copyright-mark" ></span> Copy 
                                    </button>
                                </div>
                                <div class="col-md-1 text-right " style="width: 126px">
                                    <c:set var="isDisableVoid" value="disabled='true'" />
                                    <c:set var="isEnableVoid" value="style='display: none;'" />
                                    <c:set var="isSaveVoid" value="" />
                                    <c:if test="${result =='success'}">        
                                        <c:set var="isDisableVoid" value="" />
                                    </c:if>
                                    <c:if test="${result =='void'}">        
                                        <c:set var="isDisableVoid" value="style='display: none;'" />
                                        <c:set var="isEnableVoid" value="" />
                                        <c:set var="isSaveVoid" value="disabled='true'" />
                                    </c:if>
                                    <c:if test="${receipt.MFinanceItemstatus.id == '2'}">        
                                        <c:set var="isDisableVoid" value="style='display: none;'" />
                                        <c:set var="isEnableVoid" value="" />
                                        <c:set var="isSaveVoid" value="disabled='true'" />
                                    </c:if>
                                    <c:if test="${result =='cancelvoid'}">        
                                        <c:set var="isDisableVoid" value="" />
                                    </c:if>
                                    <c:if test="${receipt.MFinanceItemstatus.id == '1'}">        
                                        <c:set var="isDisableVoid" value="" />
                                    </c:if>
                                    <button type="button" class="btn btn-primary" onclick="EnableVoidReceipt();" data-toggle="modal" data-target="#EnableVoid" id="enableVoidButton" name="enableVoidButton"  ${isEnableVoid} >
                                        <span id="SpanEnableVoid" class="glyphicon glyphicon-ok" ></span> Cancel Void
                                    </button>
                                    <button type="button" class="btn btn-danger" onclick="DisableVoidReceipt();" data-toggle="modal" data-target="#DisableVoid" id="disableVoidButton" name="disableVoidButton" ${isDisableVoid} >
                                        <span id="SpanDisableVoid" class="glyphicon glyphicon-remove" ></span> Void
                                    </button>
                                </div>
                                <div class="col-md-1 text-right ">
                                    <button type="button" id="ButtonSave" name="ButtonSave" onclick="saveReceipt()" class="btn btn-success" ${isSaveVoid}><i class="fa fa-save"></i> Save</button>
                                </div>
                                <div class="col-md-1 text-right ">
                                    <button type="button" id="ButtonNew" name="ButtonNew" onclick="clearNew()" class="btn btn-success"><i class="fa fa-plus-circle"></i> New</button>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>                          
            </form>
        </div>
    </div>      
</div>
<hr/>

<!--List Receive From Modal-->
<div class="modal fade" id="ReceiveFromModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4  class="modal-title">Receive From</h4>
            </div>
            <div class="modal-body">
                <!-- Receive From List Table-->
                <div style="text-align: right"> <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchReceiveFrom" name="searchReceiveFrom"/> </div> 
                <table class="display" id="ReceiveFromTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${customerAgentList}">
                            <tr onclick="setBillValue('${item.billTo}', '${item.billName}', '${item.address}', '${item.term}', '${item.pay}');">                                
                                <td class="item-billto">${item.billTo}</td>
                                <td class="item-name">${item.billName}</td>                                
                                <td class="item-address hidden">${item.address}</td>
                                <td class="item-tel hidden">${item.tel}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <!--Script Receive From List Table-->
                <script>
                    var showflag = 1;
                    $(document).ready(function() {
                        // Receive From Table
                        var ReceiveFromTable = $('#ReceiveFromTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });

                        $('#ReceiveFromTable tbody').on('click', 'tr', function() {
                            $('.collapse').collapse('show');
                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                            }
                            else {
                                ReceiveFromTable.$('tr.row_selected').removeClass('row_selected');
                                $(this).addClass('row_selected');
                            }
                        });

                        $("#searchReceiveFrom").keyup(function(event) {
                            if (event.keyCode === 13) {
                                if ($("#searchReceiveFrom").val() == "") {
                                    // alert('please input data');
                                }
                                searchCustomerAgentList($("#searchReceiveFrom").val());
                            }
                        });

//                        autocomplete
                        $("#receiveFromCode").keyup(function(event) {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            if ($(this).val() === "") {
                                $("#receiveFromName").val("");
                                $("#receiveFromAddress").val("");
                                $("#arCode").val("");
                            } else {
                                if(event.keyCode === 13){
                                    searchCustomerAutoList(this.value);
                                }
                            }
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                        });
                        $("#receiveFromCode").keydown(function() {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            if (showflag == 0) {
                                $(".ui-widget").css("top", -1000);
                                showflag = 1;
                            }
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                        });
                    });

                    function setBillValue(billto, billname, address, term, pay) {
                        $("#receiveFromCode").val(billto);
                        $("#arCode").val(billto);
                        $("#receiveFromName").val(billname);
                        if (address == 'null') {
                            $("#receiveFromAddress").val("");
                        } else {
                            $("#receiveFromAddress").val(address);
                        }
                        $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                        $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                        $("#ReceiveFromModal").modal('hide');
                    }

                    function searchCustomerAgentList(name) {
                        var servletName = 'BillableServlet';
                        var servicesName = 'AJAXBean';
                        var param = 'action=' + 'text' +
                                '&servletName=' + servletName +
                                '&servicesName=' + servicesName +
                                '&name=' + name +
                                '&type=' + 'getListBillto';
                        CallAjax(param);
                    }

                    function CallAjax(param) {
                        var url = 'AJAXServlet';
                        $("#ajaxload").removeClass("hidden");
                        try {
                            $.ajax({
                                type: "POST",
                                url: url,
                                cache: false,
                                data: param,
                                success: function(msg) {
                                    $('#ReceiveFromTable').dataTable().fnClearTable();
                                    $('#ReceiveFromTable').dataTable().fnDestroy();
                                    $("#ReceiveFromTable tbody").empty().append(msg);

                                    $('#ReceiveFromTable').dataTable({bJQueryUI: true,
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
                            alert(e);
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
                        CallAjaxAuto(param);
                    }

                    function CallAjaxAuto(param) {
                        var url = 'AJAXServlet';
                        var billArray = [];
                        var billListId = [];
                        var billListName = [];
                        var billListAddress = [];
                        var billid, billname, billaddr;
                        $("#receiveFromCode").autocomplete("destroy");
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
                                    var billJson = JSON.parse(msg);
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
                                        }
                                        $("#dataload").addClass("hidden");
                                    }
                                    $("#receiveFromId").val(billid);
                                    $("#receiveFromName").val(billname);
                                    $("#receiveFromAddress").val(billaddr);

                                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');

                                    $("#receiveFromCode").autocomplete({
                                        source: billArray,
                                        close: function() {
                                            $("#receiveFromCode").trigger("keyup");
                                            var billselect = $("#receiveFromCode").val();
                                            for (var i = 0; i < billListId.length; i++) {
                                                if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                                                    $("#receiveFromCode").val(billListId[i]);
                                                    $("#arCode").val(billListId[i]);
                                                    $("#receiveFromName").val(billListName[i]);
                                                    $("#receiveFromAddress").val(billListAddress[i]);

                                                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                                                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                                                }
                                            }
                                        }
                                    });

                                    var billval = $("#receiveFromCode").val();
                                    for (var i = 0; i < billListId.length; i++) {
                                        if (billval == billListName[i]) {
                                            $("#receiveFromCode").val(billListId[i]);
                                            $("#arCode").val(billListId[i]);
                                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                                        }
                                    }
                                    if (billListId.length == 1) {
                                        showflag = 0;
                                        $("#receiveFromCode").val(billListId[0]);
                                        $("#arCode").val(billListId[0]);
                                        $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                                        $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                                    }
                                    var event = jQuery.Event('keydown');
                                    event.keyCode = 40;
                                    $("#receiveFromCode").trigger(event);

                                }, error: function(msg) {
                                    console.log('auto ERROR');
                                    $("#dataload").addClass("hidden");
                                }
                            });
                        } catch (e) {
                            alert(e);
                        }
                    }
                </script>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="ButtonBilltoModal" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Disable Modal-->
<div class="modal fade" id="DisableVoid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Disable Void Receipt</h4>
            </div>
            <div class="modal-body" id="disableVoidModal">
                Are you confirm to void receipt ${receipt.recNo}?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick='DisableReceipt()' data-dismiss="modal">Cancel Void</button>               
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
                <h4 class="modal-title"  id="Titlemodel">Enable Void Receipt</h4>
            </div>
            <div class="modal-body" id="enableVoid">
                Are you confirm to cancel void receipt ${receipt.recNo}?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal" onclick='Enable()'>Enable</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<div class="modal fade" id="delReceiptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Receipt</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--DELETE MODAL-->
<div class="modal fade" id="DeleteProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Receipt Detail</h4>
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

<!--DELETE MODAL-->
<div class="modal fade" id="DeleteReceiptCredit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Receipt Credit</h4>
            </div>
            <div class="modal-body" id="delCredit">

            </div>
            <div class="modal-footer">
                <button type="submit" onclick="DeleteRowCredit()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>               
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Disable Modal-->
<div class="modal fade" id="PrintReceiptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Print Receipt</h4>
            </div>
            <div class="modal-body" id="printReceiptModal" >
                <div class="col-xs-1" style="width: 280px">
                    <label class="text-right">select option for print receipt<font style="color: red">*</font></label>                                    
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
                <button type="button" class="btn btn-default" onclick="confirmPrintReceipt()"  data-dismiss="modal">
                    <span id="buttonPrint" class="glyphicon glyphicon-print" ></span> Print 
                </button>          
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade " id="PrintInvoiceModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Print</h4>
            </div>
            <div class="modal-body" >
                <div class="row">
                    <div class="col-md-5">
                        <h5>Invoice No </h5>
                    </div>
                    <div class="col-md-7">
                        <select id="selectInvoiceId" name="selectInvoiceId" class="form-control">
                            <c:forEach var="table" items="${invoiceIdList}" >
                                <option value="${table.invoiceId} | ${table.invoiceType}">${table.invoiceNo}</option>  
                            </c:forEach>
                        </select>
                    </div>
                </div>
<!--                <div class="row">
                    <div class="col-md-5">
                        <h5>Sales Staff </h5>
                    </div>
                    <div class="col-md-7">
                        <select id="selectSalesStaff" name="selectSalesStaff" class="form-control">
                            <option value="1">Show Sales Staff</option>
                            <option value="0">Not  Show Sales Staff</option>
                        </select>
                    </div>
                </div>-->
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
                            <option value="SCB2,BBL">All</option>
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
                <button type="button" id="confirmPrintInv" name="confirmPrintInv" onclick="confirmPrintInvoice()" class="btn btn-success" data-dismiss="modal">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--Disable Modal-->
<div class="modal fade" id="SendEmailReceiptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Send Email</h4>
            </div>
            <div class="modal-body" id="sendEmailReceiptModal" >
                <div class="col-xs-1" style="width: 500px">
                    <label class="text-right">select option for send email receipt<font style="color: red">*</font></label>                                    
                </div>
                <div class="text-center" style="width: 250px" >
                    <select name="optionSend" id="optionSend" class="form-control" style="height:34px">
                        <option value="1" >Not Show Description</option>
                        <option value="2" >Show Description</option>
                        <option value="3" >Print Format Package Tour</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="confirmSendEmailReceipt()">
                    <span id="buttonEmail" class="glyphicon glyphicon-send" ></span> Send Email 
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade " id="DescriptionReceiptDetailModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                        <input maxlength="255" id="InputDescriptionDetail" name="InputDescriptionDetail" type="text" class="form-control" value=""></td>
                        <!--<textarea id="InputDescriptionDetail" rows="12" cols="80" class="form-control" >-->
                        <!--                        </textarea>-->
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

<div class="modal fade" id="CopyReceiptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Copy Receipt</h4>
            </div>
            <div class="modal-body" id="copyReceiptModal" >
                <label class="text-right">Are you sure to copy receipt ?</label>                                    
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="confirmCopyReceipt()">
                    <span id="buttonCopyReceipt" class="glyphicon glyphicon-copyright-mark" ></span> Copy
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<c:if test="${! empty requestScope['saveresult']}">
    <c:if test="${requestScope['saveresult'] =='save successful'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['saveresult'] =='save unsuccessful'}">        
        <script language="javascript">
            $('#textAlertDivNotSave').show();
        </script>
    </c:if>
</c:if>
<c:if test="${! empty requestScope['deleteresult']}">
    <c:if test="${requestScope['deleteresult'] =='delete successful'}">        
        <script language="javascript">
            $('#textAlertDivDelete').show();
        </script>
    </c:if>
    <c:if test="${requestScope['deleteresult'] =='delete unsuccessful'}">        
        <script language="javascript">
            $('#textAlertDivNotDelete').show();
        </script>
    </c:if>
</c:if>
<!--Script-->       
<script type="text/javascript" charset="utf-8">

    var setinvoice = 0;

    $(document).ready(function() {
        
        $("#inv,#ref,#com").removeClass('hidden');
        $('.datemask').mask('0000-00-00');
        $('.date').datetimepicker();
        $(".money").mask('000,000,000.00', {reverse: true});

        if ($('#searchReceipt').val() == "dummy") {
            $('#textAlertReceiveNo').show();
        }
//        $(".moneyformat").mask('000,000,000', {reverse: true});

        var wildCardSearch = ($("#wildCardSearch").val()).indexOf("%");
//        if ($("#receiveId").val() !== '') {
            $("#receiveNo").focus();
//        }
        $("#receiveNo").keyup(function(event) {
            if (event.keyCode === 13) {
                searchReceiveNo();
            } else if (event.keyCode === 38) {
                if ((parseInt(wildCardSearch) >= 0) || ($("#receiveId").val() !== '')) {
                    $("#keyCode").val(event.keyCode);
                    var action = document.getElementById('action');
                    action.value = 'wildCardSearch';
                    document.getElementById('ReceiptForm').submit();
                }

            } else if (event.keyCode === 40) {
                if ((parseInt(wildCardSearch) >= 0) || ($("#receiveId").val() !== '')) {
                    $("#keyCode").val(event.keyCode);
                    var action = document.getElementById('action');
                    action.value = 'wildCardSearch';
                    document.getElementById('ReceiptForm').submit();
                }

            } else if (event.keyCode === 118) {
                $("#keyCode").val(event.keyCode);
                var action = document.getElementById('action');
                action.value = 'new';
                document.getElementById('ReceiptForm').submit();

            } else if (event.keyCode === 119) {
                $("#keyCode").val(event.keyCode);
                var action = document.getElementById('action');
                action.value = 'wildCardSearch';
                document.getElementById('ReceiptForm').submit();

            }
        });
        $("#invoiceNo").keyup(function(event) {
            if (event.keyCode === 13) {
                searchInvoice();
            }
        });
        $("#refNo").keyup(function(event) {
            if (event.keyCode === 13) {
                searchRefNo(); 
            }
        });
        $("#searchPaymentNoAir").keyup(function(event) {
            if (event.keyCode === 13) {
                searchPaymentNoAir();
            }
        });
        $("#ButtonSearchPaymentNoTour").on('click', function () {
            searchPaymentNoTour();
        });
        
        $("#ButtonSearchPaymentNoAir").on('click', function () {
            searchPaymentNoAir();
        });

        $("#searchPaymentNoTour").keyup(function(event) {
            if (event.keyCode === 13) {
                searchPaymentNoTour();
            }
        });

        $('#ReceiveDate').datetimepicker().on('dp.change', function(e) {
            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromDate');
        });

        $('#ReceiptForm').bootstrapValidator({
            container: 'tooltip',
            excluded: [':disabled'],
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                receiveFromCode: {
                    validators: {
                        notEmpty: {
                            message: 'The Receive From Code is required'
                        }
                    }
                },
                arCode: {
                    validators: {
                        notEmpty: {
                            message: 'The A/R Code is required'
                        }
                    }
                },
                receiveFromDate: {
                    validators: {
                        notEmpty: {
                            message: 'The Receive Date is required'
                        }
                    }
                },
                inputStatus: {
                    validators: {
                        notEmpty: {
                            message: 'The Receive Date is required'
                        }
                    }
                }
            }
        });
        
        var chqBank2 = $('#chqBank2').val();
        var chqNo2 = $('#chqNo2').val();
        var chqAmount2 = $('#chqAmount2').val();
        
        if (chqBank2 != "" || chqNo2 != "" || chqAmount2 != "") {
            $("#addChq").removeClass('hidden');
        } 
        
        $('#addChqButton').on('click', function() {
            $("#addChq").removeClass('hidden');
        });

        $('#deleteChqButton').on('click', function() {
            $("#addChq").addClass('hidden');
        });

        $('#addCreditButton').on('click', function() {
            $("#addCredit").removeClass('hidden');
            $("#addCreditDetail").removeClass('hidden');
        });

//        $('#selectInvoiceId').on('click', function() {
//            if($('#selectInvoiceId').val() === ''){
//                $("#confirmPrintInv").addClass("disabled");
//            }else{
//                $("#confirmPrintInv").removeClass("disabled");
//            }
//        });

        $('#deleteCreditButton').on('click', function() {
            $("#addCredit").addClass('hidden');
            $("#addCreditDetail").addClass('hidden');
        });

        $('#collapseTab').on('shown.bs.collapse', function() {
            $(".arrowReceipt").removeClass("glyphicon glyphicon-chevron-down").addClass("glyphicon glyphicon-chevron-up");
        });

        $('#collapseTab').on('hidden.bs.collapse', function() {
            $(".arrowReceipt").removeClass("glyphicon glyphicon-chevron-up").addClass("glyphicon glyphicon-chevron-down");
        });

        var receiveFromTable = $('#ListReceiveFromTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });

        var ARCodeTable = $('#ListARCodeTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });

        // +++++++++++++++++++++ Product Table +++++++++++++++++++++ //
        AddRowProduct(parseInt($("#counter").val()));

        $('#ReceiptListTable tbody tr:last td .input-group-addon').click(function() {
            AddRowProduct(parseInt($("#counter").val()));
        });

        $("#ReceiptListTable").on("keyup", function() {
            var rowAll = $("#ReceiptListTable tr").length;
            $("td").keyup(function() {
                if ($(this).find("input").val() != '') {
                    var colIndex = $(this).parent().children().index($(this));
                    var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                    rowAll = $("#ReceiptListTable tr").length;
                    //console.log('Row: ' + rowIndex + ', Column: ' + colIndex + ', All Row ' + rowAll);
                    if (rowIndex == rowAll) {
                        AddRowProduct(parseInt($("#counter").val()));
                    }
                    if (rowAll < 2) {
                        $("#tr_ProductDetailAddRow").removeClass("hide");
                        $("#tr_ProductDetailAddRow").addClass("show");
                    }
                }
            });
        });
        $("#ReceiptListTable").on("change", "select:last", function() {
            var row = parseInt($("#counter").val());
            AddRowProduct(row);
        });
        $("#ReceiptListTable").on('click', '.newRemCF', function() {
            $(this).parent().parent().remove();
            var rowAll = $("#ReceiptListTable tr").length;
            if (rowAll < 2) {
                $("#tr_ProductDetailAddRow").removeClass("hide");
                $("#tr_ProductDetailAddRow").addClass("show");
            }
        });
        $("#tr_ProductDetailAddRow a").click(function() {
            $(this).parent().removeClass("show");
            $(this).parent().addClass("hide");
        });

        // +++++++++++++++++++++ Credit Detail Table +++++++++++++++++++++ //
        AddRowCredit(parseInt($("#countRowCredit").val()));
//        $('#CreditDetailTable tbody tr:last td .input-group-addon').click(function() {  
//            AddRowCredit(parseInt($("#countRowCredit").val()));
//        });
        $("#CreditDetailTable").on("keyup", function() {
            var rowAll = $("#CreditDetailTable tr").length;
            $("td").keyup(function() {
                if ($(this).find("input").val() != '') {
                    var colIndex = $(this).parent().children().index($(this));
                    var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                    rowAll = $("#CreditDetailTable tr").length;
                    //console.log('Row: ' + rowIndex + ', Column: ' + colIndex + ', All Row ' + rowAll);
                    if (rowIndex == rowAll) {
                        AddRowCredit(parseInt($("#countRowCredit").val()));
                    }
                    if (rowAll < 2) {
                        $("#tr_CreditDetailAddRow").removeClass("hide");
                        $("#tr_CreditDetailAddRow").addClass("show");
                    }
                }
            });
        });

        $("#CreditDetailTable").on("change", "select:last", function() {
            var row = parseInt($("#countRowCredit").val());
            AddRowCredit(row);
        });

        $("#CreditDetailTable").on('click', '.newRemCF', function() {
            $(this).parent().parent().remove();
            var rowAll = $("#CreditDetailTable tr").length;
            if (rowAll < 2) {
                $("#tr_CreditDetailAddRow").removeClass("hide");
                $("#tr_CreditDetailAddRow").addClass("show");
            }
        });

        $("#tr_CreditDetailAddRow a").click(function() {
            $(this).parent().removeClass("show");
            $(this).parent().addClass("hide");
        });


        //set format money
        $("#withTax").focusout(function() {
            setFormatCurrencyReceipt();
        });
        $("#cashAmount").focusout(function() {
            setFormatCurrencyReceipt();
        });
        $("#cashMinusAmount").focusout(function() {
            setFormatCurrencyReceipt();
        });
        $("#bankTransfer").focusout(function() {
            setFormatCurrencyReceipt();
        });
        $("#chqAmount1").focusout(function() {
            setFormatCurrencyReceipt();
        });
        $("#chqAmount2").focusout(function() {
            setFormatCurrencyReceipt();
        });

        setFormatCurrencyReceipt();
//        var creditlength = $("#CreditDetailTable tr").length ;
        var detaillength = $("#ReceiptListTable tr").length;

        if (detaillength > 1) {
            for (var i = 1; i < detaillength; i++) {
                if ($('#receiveCost' + i).val() != "") {
                    setFormatCurrency(i);
                }
                if ($('#receiveAmount' + i).val() != "") {
                    setFormatCurrency(i);
                }
                if ($('#receiveExRate' + i).val() != "") {
                    setFormatExRate(i);
                }
            }
        }
        calculateGrandTotal();
        
        var creditDetailTableLength = $("#CreditDetailTable tr").length;
        
        if (creditDetailTableLength > 1) {
            for (var i = 1; i < creditDetailTableLength; i++) {
                if ($('#creditAmount' + i).val() != "") {
                    setFormatCreditAmount(i);
                }

            }
        }
        
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
        $(".decimalexrate").inputmask({
            alias: "decimal",
            integerDigits: 6,
            groupSeparator: ',',
            autoGroup: true,
            digits: 4,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0.0000",
        });
    });

//    function setFormatCurrencyDetail(){
//        var detaillength = $("#ReceiptListTable tr").length ;
//        if(detaillength > 1) {
//            for(var i =1;i<detaillength;i++){
//                if($('#receiveCost'+i).val() != ""){
//                     var receiveCost = replaceAll(",","",$('#receiveCost'+i).val()); 
//                     if (receiveCost == ""){
//                         receiveCost = 0;
//                     }
//                     receiveCost = parseFloat(receiveCost); 
//                     document.getElementById("receiveCost"+i).value = formatNumber(receiveCost);
//
// //                    if (receiveCost == "" || receiveCost == 0){
// //                        document.getElementById("receiveCost"+i).value = "";
// //                    }
//                }
//                if($('#receiveAmount'+i).val() != ""){
//                    var receiveAmount = replaceAll(",","",$('#receiveAmount'+i).val()); 
//                    if (receiveAmount == ""){
//                        receiveAmount = 0;
//                    }
//                    receiveAmount = parseFloat(receiveAmount); 
//                    document.getElementById("receiveAmount"+i).value = formatNumber(receiveAmount);
//                    if (receiveAmount == "" || receiveAmount == 0){
//                        document.getElementById("receiveAmount"+i).value = "";
//                    }
//                }
//           }
//
//        }
//
//    }

    function setFormatCurrencyReceipt() {
        var withTax = replaceAll(",", "", $('#withTax').val());
        if (withTax == "") {
            withTax = 0;
        }
        withTax = parseFloat(withTax);
        document.getElementById("withTax").value = formatNumber(withTax);

        if (withTax == "" || withTax == 0) {
            document.getElementById("withTax").value = "";
        }

        var cashAmount = replaceAll(",", "", $('#cashAmount').val());
        if (cashAmount == "") {
            cashAmount = 0;
        }
        cashAmount = parseFloat(cashAmount);
        document.getElementById("cashAmount").value = formatNumber(cashAmount);

        if (cashAmount == "" || cashAmount == 0) {
            document.getElementById("cashAmount").value = "";
        }

        var cashMinusAmount = replaceAll(",", "", $('#cashMinusAmount').val());
        if (cashMinusAmount == "") {
            cashMinusAmount = 0;
        }
        cashMinusAmount = parseFloat(cashMinusAmount);
        document.getElementById("cashMinusAmount").value = formatNumber(cashMinusAmount);

        if (cashMinusAmount == "" || cashMinusAmount == 0) {
            document.getElementById("cashMinusAmount").value = "";
        }

        var bankTransfer = replaceAll(",", "", $('#bankTransfer').val());
        if (bankTransfer == "") {
            bankTransfer = 0;
        }
        bankTransfer = parseFloat(bankTransfer);
        document.getElementById("bankTransfer").value = formatNumber(bankTransfer);

        if (bankTransfer == "" || bankTransfer == 0) {
            document.getElementById("bankTransfer").value = "";
        }

        var chqAmount1 = replaceAll(",", "", $('#chqAmount1').val());
        if (chqAmount1 == "") {
            chqAmount1 = 0;
        }
        chqAmount1 = parseFloat(chqAmount1);
        document.getElementById("chqAmount1").value = formatNumber(chqAmount1);

        if (chqAmount1 == "" || chqAmount1 == 0) {
            document.getElementById("chqAmount1").value = "";
        }

        var chqAmount2 = replaceAll(",", "", $('#chqAmount2').val());
        if (chqAmount2 == "") {
            chqAmount2 = 0;
        }
        chqAmount2 = parseFloat(chqAmount2);
        document.getElementById("chqAmount2").value = formatNumber(chqAmount2);

        if (chqAmount2 == "" || chqAmount2 == 0) {
            document.getElementById("chqAmount2").value = "";
        }
    }

    function printReceiptNew() {
        window.open("report.smi?name=ReceiptEmail");
    }

    function printReceipt(printtype) {
        document.getElementById('selectPrint').value = printtype;
        if (printtype == 1 || printtype == 2) {
            $('#PrintReceiptModal').modal('show');
        } else if (printtype == 3 || printtype == 4 || printtype == 5) {
            $('#PrintInvoiceModal').modal('show');
        }
    }

    function confirmPrintReceipt() {
        $('#PrintReceiptModal').modal('hide');
        var printtype = document.getElementById('selectPrint').value;
        var receiveId = document.getElementById('receiveId').value;
        var receiveNo = document.getElementById('receiveNo').value;
        var optionPrint = document.getElementById('optionPrint').value;
        if (receiveId == "") {
            alert("please save before print");
        } else if (printtype == 0) {
            alert('please select option print');
        } else if (printtype == 1) {
            window.open("report.smi?name=ReceiptReport&receiveId=" + receiveId + "&receiveNo=" + receiveNo + "&optionPrint=" + optionPrint);
        } else if (printtype == 2) {
            window.open("report.smi?name=ReceiptEmail&receiveId=" + receiveId + "&receiveNo=" + receiveNo + "&optionPrint=" + optionPrint);
        }
    }

    function confirmPrintInvoice() {
        var printtype = document.getElementById('selectPrint').value;
        var invoice = document.getElementById('selectInvoiceId').value;

        var inv = invoice.split("|");
        var invoiceId = inv[0];
        var invType = inv[1];
//        var sale = document.getElementById('selectSalesStaff').value;
        var payment = document.getElementById('selectPayment').value;
        var sign = document.getElementById('SelectSign').value;
        var leader = document.getElementById('selectLeader').value;

        if (printtype == 3) {
            if (invoice === '') {
            } else {
                if (invType === 'T') {
                    window.open("report.smi?name=InvoiceTemp&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + '' + "&showleader=" + leader + "&sign=" + sign);
                } else {
                    window.open("report.smi?name=InvoiceReport&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + '' + "&showleader=" + leader + "&sign=" + sign);
                }
            }
        } else if (printtype == 4) {
            if (invoice === '') {
            } else {
                if (invType === 'T') {
                    window.open("report.smi?name=InvoiceTemp&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + '' + "&showleader=" + leader + "&sign=" + sign);
                } else {
                    window.open("report.smi?name=InvoiceEmail&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + '' + "&showleader=" + leader + "&sign=" + sign);
                }
            }
        } else if (printtype == 5) {
            if (invoice === '') {
            } else {
                if (invType === 'T') {
                    window.open("report.smi?name=InvoiceTemp&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + '' + "&showleader=" + leader + "&sign=" + sign);
                } else {
                    window.open("SendMail.smi?reportname=Invoice&reportid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + '' + "&showleader=" + leader + "&sign=" + sign);
                }
            }
        }
    }



    //http://localhost:8080/SMITravel/SendMail.smi?reportname=Invoice
    function sendEmailReceipt() {
        $('#SendEmailReceiptModal').modal('show');
    }

    function confirmSendEmailReceipt() {
        $('#SendEmailReceiptModal').modal('hide');
        var optionSend = document.getElementById('optionSend').value;
        var receiveId = document.getElementById('receiveId').value;
        window.open("SendMail.smi?reportname=ReceiptEmail&reportid=" + receiveId + "&optionsend=" + optionSend);
    }

    function AddRowProduct(row) {
        
        var typeRec = "${typeReceipt}";
        if(typeRec === "V"){
            $("#ReceiptListTable tbody").append(
                    '<tr style="higth 100px">' +
                    '<input id="tableId' + row + '" name="tableId' + row + '"  type="hidden" >' +
                    '<input id="DescriptionReceiptDetail' + row + '" name="DescriptionReceiptDetail' + row + '"  type="hidden" >' +
                    '<input id="receiveAmountTemp' + row + '" name="receiveAmountTemp' + row + '"  type="hidden" value="9999999" >' +
                    '<td>' +
                    '<select class="form-control" name="receiveProduct' + row + '" id="receiveProduct' + row + '" ><option value="">---------</option></select>' +
                    '</td>' +
                    '<td><input maxlength="255" id="receiveDes' + row + '" name="receiveDes' + row + '" type="text" class="form-control" ></td>' +
                    '<td><input id="receiveCost' + row + '" name="receiveCost' + row + '" type="text" class="form-control decimal" readonly="" ></td>' +
                    '<td>' +
                    '<select class="form-control" name="receiveCurCostTemp' + row + '" id="receiveCurCostTemp' + row + '"><option value="">---------</option></select>' +
                    '</td>' +
                    '<td class="hidden">' +
                    '<select class="form-control" name="receiveCurCost' + row + '" id="receiveCurCost' + row + '"><option value="" >---------</option></select>' +
                    '</td>' +
                    '<td align="center">' +
                    '<input type="checkbox" name="receiveIsVat' + row + '" id="receiveIsVat' + row + '" onclick="handleClick(this,' + row + ');" value="" >' +
                    '</td>' +
                    '<td align="center"><div id="receiveVat' + row + '" style="display:none" ></div></td>' +
                    '<td><input id="receiveAmount' + row + '" name="receiveAmount' + row + '" type="text" class="form-control decimal" ></td>' +
                    '<td>' +
                    '<select class="form-control" name="receiveCurrency' + row + '" id="receiveCurrency' + row + '" ><option value="">---------</option></select>' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" value="" id="receiveExRate' + row + '" name="receiveExRate' + row + '" class="form-control decimalexrate" >' +
                    '</td>' +
                    '<td class="hidden">' +
                    '<input type="text" value="" id="curExRateTemp' + row + '" name="curExRateTemp' + row + '" class="form-control" >' +
                    '</td>' +
                    '<td class="text-center">' +
                    '<a href="#/inv" data-toggle="modal" data-target="#DescriptionReceiptDetailModal" onclick="getDescriptionDetail(' + row + ')" id="InputDescription' + row + '"><span class="glyphicon glyphicon-th-list"></span></a>&nbsp&nbsp' +
                    '<a class="remCF" onclick="deleteReceiptList(\'\', \'' + row + '\')">' +
                    '<span id="SpanRemove' + row + '"class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
                    '</td>' +
                    '</tr>'
                    );
        }else {
            $("#ReceiptListTable tbody").append(
                    '<tr style="higth 100px">' +
                    '<input id="tableId' + row + '" name="tableId' + row + '"  type="hidden" >' +
                    '<input id="DescriptionReceiptDetail' + row + '" name="DescriptionReceiptDetail' + row + '"  type="hidden" >' +
                    '<input id="receiveAmountTemp' + row + '" name="receiveAmountTemp' + row + '"  type="hidden" value="9999999" >' +
                    '<td>' +
                    '<select class="form-control" name="receiveProduct' + row + '" id="receiveProduct' + row + '" ><option value="">---------</option></select>' +
                    '</td>' +
                    '<td><input maxlength="255" id="receiveDes' + row + '" name="receiveDes' + row + '" type="text" class="form-control" ></td>' +
                    '<td><input id="receiveCost' + row + '" name="receiveCost' + row + '" type="text" class="form-control decimal" onkeyup="insertCommas(this)" readonly="" ></td>' +
                    '<td>' +
                    '<select class="form-control" name="receiveCurCostTemp' + row + '" id="receiveCurCostTemp' + row + '"><option value="">---------</option></select>' +
                    '</td>' +
                    '<td class="hidden">' +
                    '<select class="form-control" name="receiveCurCost' + row + '" id="receiveCurCost' + row + '"><option value="" >---------</option></select>' +
                    '</td>' +
                    '<td align="center">' +
                    '<input type="checkbox" name="receiveIsVat' + row + '" id="receiveIsVat' + row + '" onclick="return false" value="" >' +
                    '</td>' +
                    '<td align="center"><div id="receiveVat' + row + '" style="display:none" ></div></td>' +
                    '<td><input id="receiveAmount' + row + '" name="receiveAmount' + row + '" type="text" class="form-control decimal"></td>' +
                    '<td>' +
                    '<select class="form-control" name="receiveCurrency' + row + '" id="receiveCurrency' + row + '" ><option value="">---------</option></select>' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" value="" id="receiveExRate' + row + '" name="receiveExRate' + row + '" class="form-control decimalexrate" >' +
                    '</td>' +
                    '<td class="text-center">' +
                    '<a href="#/inv" data-toggle="modal" data-target="#DescriptionReceiptDetailModal" onclick="getDescriptionDetail(' + row + ')" id="InputDescription' + row + '"><span class="glyphicon glyphicon-th-list"></span></a>&nbsp&nbsp' +
                    '<a class="remCF" onclick="deleteReceiptList(\'\', \'' + row + '\')">' +
                    '<span id="SpanRemove' + row + '"class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
                    '</td>' +
                    '</tr>'
                    );
        }
        $("#billTypeList option").clone().appendTo("#receiveProduct" + row);
        $("#currencyList option").clone().appendTo("#receiveCurrency" + row);
        $("#currencyList option").clone().appendTo("#receiveCurCost" + row);
        $("#currencyList option").clone().appendTo("#receiveCurCostTemp" + row);
        $('#receiveCurCostTemp' + row).attr("disabled", true);
        $("#receiveAmount" + row).focusout(function() {
//              calculatGross(row);
            setFormatCurrency(row);
            calculateGrandTotal();
        });
        $("#receiveCost" + row).focusout(function() {
            setFormatCurrency(row);
        });
        $("#receiveExRate" + row).focusout(function() {
            setFormatExRate(row);
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
        $(".decimalexrate").inputmask({
            alias: "decimal",
            integerDigits: 6,
            groupSeparator: ',',
            autoGroup: true,
            digits: 4,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0.0000",
        });
//            var tempCount = parseInt($("#counter").val()) + 1;
        $("#counter").val(row + 1);
//        }
        if(typeRec === "V"){
            $('#receiveIsVat' + row).prop('checked', true);
            document.getElementById('receiveVat' + row).style.display = 'block';
            document.getElementById('receiveVat' + row).innerHTML = $("#vatValue").val();
            $('#receiveIsVat' + row).val('1');
        }
    }
    function handleClick(cb, row) {
        if (cb.checked) {
            $("#receiveIsVat" + row).val("1");
            $("#receiveVat" + row).val($("#vatValue").val());
            document.getElementById('receiveVat' + row).style.display = 'block';
            document.getElementById('receiveVat' + row).innerHTML = $("#vatValue").val();
        } else {
            $("#receiveIsVat" + row).val("0");
            $("#receiveVat" + row).val("");
            document.getElementById('receiveVat' + row).style.display = 'none';
        }
    }

    function calculatGross(row) {
//  Total Amount Refund  vat = vat * Total Amount Refund / 100
        var receiveAmount = replaceAll(",", "", $("#receiveAmount" + row).val());
        if (receiveAmount == "") {
            receiveAmount = 0;
        }

        var receiveVat = replaceAll(",", "", $("#receiveVat" + row).val());
        if (receiveVat == "") {
            receiveVat = 0;
        }

        var amount = parseFloat(receiveAmount);
        var vat = parseFloat(receiveVat);
        var beforevat = parseFloat(100 / (100 + vat)).toFixed(2);
        var receiveGross = amount * beforevat;
        document.getElementById("receiveGross" + row).value = formatNumber(receiveGross);
    }

    function replaceAll(find, replace, str) {
        return str.replace(new RegExp(find, 'g'), replace);
    }

    function formatNumber(num) {
        return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
    }
    
    function formatExRateNumber(num) {
        return  num.toFixed(4).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
    }
    
    function insertCommas(nField) {
        if (/^0/.test(nField.value)) {
            nField.value = nField.value.substring(0, 1);
        }
        if (Number(nField.value.replace(/,/g, ""))) {
            var tmp = nField.value.replace(/,/g, "");
            tmp = tmp.toString().split('').reverse().join('').replace(/(\d{3})/g, '$1,').split('').reverse().join('').replace(/^,/, '');
            if (/\./g.test(tmp)) {
                tmp = tmp.split(".");
                tmp[1] = tmp[1].replace(/\,/g, "").replace(/ /, "");
                nField.value = tmp[0] + "." + tmp[1]
            } else {
                nField.value = tmp.replace(/ /, "");
            }
        } else {
            nField.value = nField.value.replace(/[^\d\,\.]/g, "").replace(/ /, "");
        }
    }
    
    function setFormatExRate(row){
        var receiveExRate = replaceAll(",", "", $('#receiveExRate' + row).val());
        if (receiveExRate == "") {
            receiveExRate = 0;
        }
        receiveExRate = parseFloat(receiveExRate);
        document.getElementById("receiveExRate" + row).value = (receiveExRate !== 0 ? formatExRateNumber(receiveExRate) : '');
    }

    function setFormatCurrency(row) {
        var receiveAmount = replaceAll(",", "", $('#receiveAmount' + row).val());
        if (receiveAmount == "") {
            receiveAmount = 0;
        }
        receiveAmount = parseFloat(receiveAmount);
        document.getElementById("receiveAmount" + row).value = formatNumber(receiveAmount);

        var receiveCost = replaceAll(",", "", $('#receiveCost' + row).val());
        if (receiveCost == "") {
            receiveCost = 0;
        }
        receiveCost = parseFloat(receiveCost);
        document.getElementById("receiveCost" + row).value = formatNumber(receiveCost);

        if (receiveAmount == "" || receiveAmount == 0) {
            document.getElementById("receiveAmount" + row).value = "";
        }

        if (receiveCost == "" || receiveCost == 0) {
            document.getElementById("receiveCost" + row).value = "";
        }
        calculateGrandTotal();
    }
    
    function setFormatCreditAmount(row){
        var creditAmount = replaceAll(",", "", $('#creditAmount' + row).val());
        if (creditAmount == "") {
            creditAmount = 0;
        }
        creditAmount = parseFloat(creditAmount);
        document.getElementById("creditAmount" + row).value = formatNumber(creditAmount);
        sumTotalCreditAmount();
        $("#creditAmount" + row).focusout(function() {
            var creditAmount = replaceAll(",", "", $('#creditAmount' + row).val());
            if (creditAmount == "") {
                creditAmount = 0;
            }
            creditAmount = parseFloat(creditAmount);
            document.getElementById("creditAmount" + row).value = formatNumber(creditAmount);

            if (creditAmount == "" || creditAmount == 0) {
                document.getElementById("creditAmount" + row).value = "";
            }
            sumTotalCreditAmount();
        });
    }

    function AddRowCredit(row) {
        $("#CreditDetailTable tbody").append(
                '<tr style="higth 100px">' +
                '<input id="tableCreditId' + row + '" name="tableCreditId' + row + '"  type="hidden" >' +
                '<td>' +
                '<select class="form-control" name="creditBank' + row + '" id="creditBank' + row + '" ><option value="">---------</option></select>' +
                '</td>' +
                '<td><input maxlength="20" id="creditNo' + row + '" name="creditNo' + row + '" type="text" class="form-control" ></td>' +
                '<td><div class="input-group date"><input id="creditExpired' + row + '" name="creditExpired' + row + '"  type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value=""><span class="input-group-addon spandate" style="padding : 1px 10px;"><span class="glyphicon glyphicon-calendar"></span></span></div></td>' +
                '<td><input id="creditAmount' + row + '" name="creditAmount' + row + '" type="text" class="form-control decimal"></td>' +
                '<td class="text-center">' +
                '<a class="remCF" onclick="deleteCreditList(\'\', \'' + row + '\')">  ' +
                '<span id="SpanRemove' + row + '"class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
                '</tr>'
                );
        $("#creditBankList option").clone().appendTo("#creditBank" + row);

        $("#creditAmount" + row).focusout(function() {
            var creditAmount = replaceAll(",", "", $('#creditAmount' + row).val());
            if (creditAmount == "") {
                creditAmount = 0;
            }
            creditAmount = parseFloat(creditAmount);
            document.getElementById("creditAmount" + row).value = formatNumber(creditAmount);

            if (creditAmount == "" || creditAmount == 0) {
                document.getElementById("creditAmount" + row).value = "";
            }
            sumTotalCreditAmount();
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
        $(".decimalexrate").inputmask({
            alias: "decimal",
            integerDigits: 6,
            groupSeparator: ',',
            autoGroup: true,
            digits: 4,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0.0000",
        });

        var tempCount = parseInt($("#countRowCredit").val()) + 1;
        $("#countRowCredit").val(tempCount);
        reloadDatePicker();
    }

    function reloadDatePicker() {
        try {
            $(".date").datetimepicker({
                pickTime: false
            });
            $('span').click(function() {
                var position = $(this).offset();
                $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
            });
        } catch (e) {

        }
    }

    function clearNew() {
        var action = document.getElementById('action');
        action.value = 'new';
        document.getElementById('ReceiptForm').submit();
    }

    function searchInvoice() {
        if($("#ajaxload1").hasClass("hidden")){
            var invoiceNo = $("#invoiceNo").val();
            var department = "${typeDepartment}";
            var invType = "${typeReceipt}";
            var invoicenopanel = $("#invoicenopanel").val();
            if (invoiceNo == "") {
                if (!$('#invoicenopanel').hasClass('has-feedback')) {
                    $('#invoicenopanel').addClass('has-feedback');
                }
                $('#invoicenopanel').removeClass('has-success');
                $('#invoicenopanel').addClass('has-error');
            }
            else {
                var servletName = 'ReceiptServlet';
                var servicesName = 'AJAXBean';
                var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&invoiceNo=' + invoiceNo +
                        '&department=' + department +
                        '&invType=' + invType +
                        '&type=' + 'searchInvoiceNo';
                CallAjaxSearchInvoice(param);
            }
        }    
    }

    function CallAjaxSearchInvoice(param) {
        var url = 'AJAXServlet';
        $("#ajaxload1").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function(msg) {
                    try {
                        if (msg == "null") {
                            $("#searchinvtable").addClass("hidden");
                            $("#searchreftable").addClass("hidden");
                            $("#searchaircomtable").addClass("hidden");
                            $("#searchtourcomtable").addClass("hidden");

                            $('#InvoiceListTable > tbody  > tr').each(function() {
                                $(this).remove();
                            });

                            document.getElementById("receiveFromCode").value = '';
                            document.getElementById("receiveFromName").value = '';
                            document.getElementById("receiveFromAddress").value = '';
                            document.getElementById("arCode").value = '';
                        } else {
                            $('#InvoiceListTable > tbody  > tr').each(function() {
                                $(this).remove();
                            });
                            $("#InvoiceListTable tbody").empty().append(msg);
                            
                            var rowAll = $("#InvoiceListTable tr").length;
                            if(rowAll === 1){
                                $("#searchinvtable").addClass("hidden");
                            }else{
                                $("#searchinvtable").removeClass("hidden");
                            }

                            setinvoice = 1;
                            document.getElementById("receiveFromCode").value = $("#receiveFromInvoice").val();
                            document.getElementById("receiveFromName").value = $("#receiveNameInvoice").val();
                            document.getElementById("receiveFromAddress").value = $("#receiveAddressInvoice").val();
                            document.getElementById("arCode").value = $("#arcodeInvoice").val();
                        }
                        $("#ajaxload1").addClass("hidden");

                    } catch (e) {
                        alert(e);
                    }

                }, error: function(msg) {
                    $("#ajaxload1").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }

    function invoicenoValidate() {
        $('#invoicenopanel').removeClass('has-feedback');
        $('#invoicenopanel').addClass('has-success');
        $('#invoicenopanel').removeClass('has-error');
    }
    
    function addProduct(product, description, cost, cur, isVat, vat, amount, currency, invId, billDescId, paymentId, airlineCode, checkadd, disdescription, number, paymentTourId, receiveFrom, receiveName, receiveAddress,invTableId,invTableNo) {
        var receiveAddressTemp = replaceAll("<br>", "\n" , receiveAddress.toString());
        $('#textAlertDuplicateProduct').hide();
        var tempCount = parseInt($("#counter").val());
        var checkAddDuplicate = false;
        if (checkadd == 1) {
            $("#ButtonSearchRefNo").attr("disabled", "disabled");
            $("#searchPaymentNoAir").attr("disabled", "disabled");
            $("#searchPaymentNoTour").attr("disabled", "disabled");
            $("#ButtonSearchPaymentNoAir").attr("disabled", "disabled");
            $("#ButtonSearchPaymentNoTour").attr("disabled", "disabled");
            $("#refNo").attr("disabled", "disabled");
            var rowAll = tempCount;
            for (var i = 1; i < rowAll; i++) {
                var invoiceId = $("#invId" + i).val();
                if (invoiceId != "" && invId === invoiceId) {
                    checkAddDuplicate = true;
                }
            }

        } else if (checkadd == 2) {
            $("#invoiceNo").attr("disabled", "disabled");
            $("#ButtonSearchInvoice").attr("disabled", "disabled");
            $("#searchPaymentNoAir").attr("disabled", "disabled");
            $("#searchPaymentNoTour").attr("disabled", "disabled");
            $("#ButtonSearchPaymentNoAir").attr("disabled", "disabled");
            $("#ButtonSearchPaymentNoTour").attr("disabled", "disabled");
            var rowAll = tempCount;
            for (var i = 1; i < rowAll; i++) {
                var billId = $("#billDescId" + i).val();
                if (billId != "" && billDescId === billId) {
                    checkAddDuplicate = true;
                }
            }

        } else if (checkadd == 3) {
            $("#invoiceNo").attr("disabled", "disabled");
            $("#refNo").attr("disabled", "disabled");
            $("#ButtonSearchRefNo").attr("disabled", "disabled");
            $("#ButtonSearchInvoice").attr("disabled", "disabled");
            $("#searchPaymentNoTour").attr("disabled", "disabled");
            $("#ButtonSearchPaymentNoTour").attr("disabled", "disabled");
            var rowAll = tempCount;
            for (var i = 1; i < rowAll; i++) {
                var payId = $("#paymentId" + i).val();
                if (payId != "" && paymentId === payId) {
                    checkAddDuplicate = true;
                }
            }

        } else if (checkadd == 4) {
            $("#invoiceNo").attr("disabled", "disabled");
            $("#refNo").attr("disabled", "disabled");
            $("#ButtonSearchRefNo").attr("disabled", "disabled");
            $("#ButtonSearchInvoice").attr("disabled", "disabled");
            $("#searchPaymentNoAir").attr("disabled", "disabled");
            $("#ButtonSearchPaymentNoAir").attr("disabled", "disabled");
            var rowAll = tempCount;
            for (var i = 1; i < rowAll; i++) {
                var paymentTour = $("#paymentTourId" + i).val();
                if (paymentTour != "" && paymentTourId === paymentTour) {
                    checkAddDuplicate = true;
                }
            }

        }
        if (!checkAddDuplicate) {
            AddDataRowProduct(tempCount, product, description, cost, cur, isVat, vat, amount, currency, invId, billDescId, paymentId, airlineCode, disdescription, number, paymentTourId, receiveFrom, receiveName, receiveAddressTemp,invTableId,invTableNo);
        } else {
            $('#textAlertDuplicateProduct').show();
        }
    }
    function AddDataRowProduct(row, product, description, cost, cur, isVat, vat, amount, currency, invId, billDescId, paymentId, airlineCode, disdescription, number, paymentTourId, receiveFrom, receiveName, receiveAddress,invTableId,invTableNo) {
        var grossinv = 0;
        if (vat !== '' && isVat !== '0') {
            var x = parseFloat(amount);
            var v = parseFloat(vat);
            grossinv = x * (100 / (100 + v));
            grossinv = formatNumber(grossinv);
        }

        if (setinvoice !== 1) {
            document.getElementById("receiveFromCode").value = receiveFrom;
            document.getElementById("receiveFromName").value = receiveName;
            document.getElementById("receiveFromAddress").value = receiveAddress;
            document.getElementById("arCode").value = receiveFrom;
        }

        var rowAll = row + 1;
        for (var i = 1; i < rowAll; i++) {
            if ($("#receiveProduct" + i).val() != ""
                    || $("#receiveDes" + i).val() != ""
                    || $("#receiveCost" + i).val() != ""
                    || $("#receiveCurCost" + i).val() != ""
                    || $("#receiveVat" + i).val() != ""
                    || $("#receiveAmount" + i).val() != ""
                    || $("#receiveCurrency" + i).val() != ""
                    ) {

            } else {
                $("#receiveProduct" + i).parent().parent().remove();
                row = parseInt(i);
                $("#counter").val(row);
            }
        }
        var typeRec = "${typeReceipt}";
        if(typeRec === "V"){
            $("#ReceiptListTable tbody").append(
                    '<tr style="higth 100px">' +
                    '<input id="invoiceTableNo' + row + '"  name="invoiceTableNo' + row + '"   type="hidden" value="' + invTableNo + '" >' +
                    '<input id="invoiceTableId' + row + '"  name="invoiceTableId' + row + '"   type="hidden" value="' + invTableId + '" >' +
                    '<input id="grossInvoice' + row + '"  name="grossInvoice' + row + '"   type="hidden" value="' + grossinv + '" >' +
                    '<input id="invId' + row + '" name="invId' + row + '"  type="hidden" value="' + invId + '" >' +
                    '<input id="tableId' + row + '" name="tableId' + row + '"  type="hidden" >' +
                    '<input id="billDescId' + row + '" name="billDescId' + row + '"  type="hidden" value="' + billDescId + '" >' +
                    '<input id="paymentId' + row + '" name="paymentId' + row + '"  type="hidden" value="' + paymentId + '" >' +
                    '<input id="paymentTourId' + row + '" name="paymentTourId' + row + '"  type="hidden" value="' + paymentTourId + '" >' +
                    '<input id="airlineCode' + row + '" name="airlineCode' + row + '"  type="hidden" value="' + airlineCode + '" >' +
                    '<input id="receiveAmountTemp' + row + '" name="receiveAmountTemp' + row + '"  type="hidden" value="' + amount + '" >' +
                    '<input id="DescriptionReceiptDetail' + row + '" name="DescriptionReceiptDetail' + row + '"  type="hidden" value="' + disdescription + '" >' +
                    '<td>' +
                    '<select class="form-control" name="receiveProduct' + row + '" id="receiveProduct' + row + '" ><option value="' + product + '" selected></option></select>' +
                    '</td>' +
                    '<td><input maxlength="255" id="receiveDes' + row + '" name="receiveDes' + row + '" type="text" class="form-control" value="' + description + '"></td>' +
                    '<td><input id="receiveCost' + row + '" name="receiveCost' + row + '" type="text" class="form-control decimal" value="' + cost + '" readonly="" ></td>' +
                    '<td>' +
                    '<select class="form-control" name="receiveCurCostTemp' + row + '" id="receiveCurCostTemp' + row + '"><option value="' + cur + '" ></option></select>' +
                    '</td>' +
                    '<td class="hidden">' +
                    '<select class="form-control" name="receiveCurCost' + row + '" id="receiveCurCost' + row + '"><option value="' + cur + '" ></option></select>' +
                    '</td>' +
                    '<td align="center">' +
                    '<input type="checkbox" name="receiveIsVat' + row + '" id="receiveIsVat' + row + '" value="' + isVat + '"  onclick="handleClick(this,' + row + ');">' +
                    '</td>' +
                    '<td align="center"><div id="receiveVat' + row + '" style="display:none" value="' + vat + '"></div></td>' +
                    '<td><input id="receiveAmount' + row + '" name="receiveAmount' + row + '" type="text" class="form-control decimal" onfocusout="checkAmount(' + row + ')" value="' + amount + '"></td>' +
                    '<td>' +
                    '<select class="form-control" name="receiveCurrency' + row + '" id="receiveCurrency' + row + '" ><option value="' + currency + '"></option></select>' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" value="" id="receiveExRate' + row + '" name="receiveExRate' + row + '" class="form-control decimalexrate" >' +
                    '</td>' +
                    '<td class="text-center">' +
                    '<a href="#/inv" data-toggle="modal" data-target="#DescriptionReceiptDetailModal" onclick="getDescriptionDetail(' + row + ')" id="InputDescription' + row + '"><span class="glyphicon glyphicon-th-list"></span></a>&nbsp' +
                    '<a class="remCF" onclick="deleteReceiptList(\'\', \'' + row + '\')">  ' +
                    '<span id="SpanRemove' + row + '"class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
                    '</tr>'
                    );
        }else{
            $("#ReceiptListTable tbody").append(
                    '<tr style="higth 100px">' +
                    '<input id="invoiceTableNo' + row + '"  name="invoiceTableNo' + row + '"   type="hidden" value="' + invTableNo + '" >' +
                    '<input id="invoiceTableId' + row + '"  name="invoiceTableId' + row + '"   type="hidden" value="' + invTableId + '" >' +
                    '<input id="grossInvoice' + row + '"  name="grossInvoice' + row + '"   type="hidden" value="' + grossinv + '" >' +
                    '<input id="invId' + row + '" name="invId' + row + '"  type="hidden" value="' + invId + '" >' +
                    '<input id="tableId' + row + '" name="tableId' + row + '"  type="hidden" >' +
                    '<input id="billDescId' + row + '" name="billDescId' + row + '"  type="hidden" value="' + billDescId + '" >' +
                    '<input id="paymentId' + row + '" name="paymentId' + row + '"  type="hidden" value="' + paymentId + '" >' +
                    '<input id="paymentTourId' + row + '" name="paymentTourId' + row + '"  type="hidden" value="' + paymentTourId + '" >' +
                    '<input id="airlineCode' + row + '" name="airlineCode' + row + '"  type="hidden" value="' + airlineCode + '" >' +
                    '<input id="receiveAmountTemp' + row + '" name="receiveAmountTemp' + row + '"  type="hidden" value="' + amount + '" >' +
                    '<input id="DescriptionReceiptDetail' + row + '" name="DescriptionReceiptDetail' + row + '"  type="hidden" value="' + disdescription + '" >' +
                    '<td>' +
                    '<select class="form-control" name="receiveProduct' + row + '" id="receiveProduct' + row + '" ><option value="' + product + '" selected></option></select>' +
                    '</td>' +
                    '<td><input maxlength="255" id="receiveDes' + row + '" name="receiveDes' + row + '" type="text" class="form-control" value="' + description + '"></td>' +
                    '<td><input id="receiveCost' + row + '" name="receiveCost' + row + '" type="text" class="form-control decimal" value="' + cost + '" readonly="" ></td>' +
                    '<td>' +
                    '<select class="form-control" name="receiveCurCostTemp' + row + '" id="receiveCurCostTemp' + row + '"><option value="' + cur + '" ></option></select>' +
                    '</td>' +
                    '<td class="hidden">' +
                    '<select class="form-control" name="receiveCurCost' + row + '" id="receiveCurCost' + row + '"><option value="' + cur + '" ></option></select>' +
                    '</td>' +
                    '<td align="center">' +
                    '<input type="checkbox" name="receiveIsVat' + row + '" id="receiveIsVat' + row + '" value="' + isVat + '"  onclick="return false">' +
                    '</td>' +
                    '<td align="center"><div id="receiveVat' + row + '" style="display:none" value="' + vat + '"></div></td>' +
                    '<td><input id="receiveAmount' + row + '" name="receiveAmount' + row + '" type="text" class="form-control decimal" onfocusout="checkAmount(' + row + ')" value="' + amount + '"></td>' +
                    '<td>' +
                    '<select class="form-control" name="receiveCurrency' + row + '" id="receiveCurrency' + row + '" ><option value="' + currency + '"></option></select>' +
                    '</td>' +
                    '<td>' +
                    '<input type="text" value="" id="receiveExRate' + row + '" name="receiveExRate' + row + '" class="form-control decimalexrate" >' +
                    '</td>' +
                    '<td class="text-center">' +
                    '<a href="#/inv" data-toggle="modal" data-target="#DescriptionReceiptDetailModal" onclick="getDescriptionDetail(' + row + ')" id="InputDescription' + row + '"><span class="glyphicon glyphicon-th-list"></span></a>&nbsp' +
                    '<a class="remCF" onclick="deleteReceiptList(\'\', \'' + row + '\')">  ' +
                    '<span id="SpanRemove' + row + '"class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
                    '</tr>'
                    );
        }
        $("#billTypeList option").clone().appendTo("#receiveProduct" + row);
        $("#currencyList option").clone().appendTo("#receiveCurrency" + row);
        $("#currencyList option").clone().appendTo("#receiveCurCost" + row);
        $("#currencyList option").clone().appendTo("#receiveCurCostTemp" + row);
        $('#receiveCurCostTemp' + row).attr("disabled", true);
        var isvat = $('#receiveIsVat' + row).val();
        if (isvat === '1')
        {
            $('#receiveIsVat' + row).prop('checked', true);
            document.getElementById('receiveVat' + row).style.display = 'block';
            document.getElementById('receiveVat' + row).innerHTML = $("#vatValue").val();
        }
        if (isvat === '0')
        {
            $('#receiveVat' + row).val("");
        }
        
        
        if(typeRec === "V" && billDescId != ""){
            $('#receiveIsVat' + row).prop('checked', true);
            document.getElementById('receiveVat' + row).style.display = 'block';
            document.getElementById('receiveVat' + row).innerHTML = $("#vatValue").val();
            $('#receiveIsVat' + row).val('1');
        }
        
        $('[name=receiveProduct' + row + '] option').filter(function() {
            return ($(this).val() === product);
        }).prop('selected', true);

        $('[name=receiveCurCost' + row + '] option').filter(function() {
            return ($(this).val() === cur);
        }).prop('selected', true);

        $('[name=receiveCurCostTemp' + row + '] option').filter(function() {
            return ($(this).val() === cur);
        }).prop('selected', true);

        $('[name=receiveCurrency' + row + '] option').filter(function() {
            return ($(this).val() === currency);
        }).prop('selected', true);

        $("#receiveAmount" + row).focusout(function() {
//        calculatGross(row);
            setFormatCurrency(row);
            calculateGrandTotal();
        });
        $("#receiveCost" + row).focusout(function() {
            setFormatCurrency(row);
        });
        $("#receiveExRate" + row).focusout(function() {
            setFormatExRate(row);
        });
        setFormatCurrency(row);
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
        $(".decimalexrate").inputmask({
            alias: "decimal",
            integerDigits: 6,
            groupSeparator: ',',
            autoGroup: true,
            digits: 4,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0.0000",
        });
        var tempCount = parseInt($("#counter").val()) + 1;
        $("#counter").val(tempCount);
        AddRowProduct(tempCount);
        calculateGrandTotal();
    }

    function searchReceiveNo() {
        var action = document.getElementById('action');
        action.value = 'searchReceiveNo';
        var receiveNo = document.getElementById('receiveNo');
        receiveNo.value = $("#receiveNo").val();
        var type = document.getElementById('type');
        type.value = $("#type").val();
        document.getElementById('ReceiptForm').submit();
    }

    function saveReceipt() {
        $('#textAlertDivSave').hide();
        $('#textAlertDivNotSave').hide();
        $('#textAlertDivDelete').hide();
        $('#textAlertDivNotDelete').hide();
        $('#textAlertReceiveNo').hide();
        $('#textAlertDuplicateProduct').hide();
        $('#textAlertReceiveAmount').hide();

        var i = 0;
        var checksave = 1;
        var action = document.getElementById('action');
        action.value = 'saveReceipt';
        var counter = document.getElementById('counter');
        counter.value = $("#ReceiptListTable tr").length;
        var countRowCredit = document.getElementById('countRowCredit');
        countRowCredit.value = $("#CreditDetailTable tr").length;


        checkSumAmountBeforeSave();

        var inputStatus = document.getElementById('inputStatus').value;
        var receiveFromCode = document.getElementById('receiveFromCode').value;
        var arCode = document.getElementById('arCode').value;
        var receiveFromDate = document.getElementById('receiveFromDate').value;
        var inputStatus = document.getElementById('inputStatus').value;
        
        //Check Currency
        var countRec = counter.value;
        var currencyNotMatch = false;
        var currencyNotEmpty = 0;
        for(var i=1; i<=countRec; i++){
            var currency1 = document.getElementById('receiveCurrency'+i);
            var product1 = document.getElementById('receiveProduct'+i);
            var description1 = document.getElementById('receiveDes'+i);
            var amount1 = document.getElementById('receiveAmount'+i);
            var exrate1 = document.getElementById('receiveExRate'+i);
            if(currency1 !== null){
                if(product1.value !== '' || description1.value !== '' || amount1.value !== '' || amount1.value !== '' || exrate1.value !== ''){
                    var currencyTemp1 = currency1.value;
                    for(var j=i+1; j<=countRec; j++){
                        var currency2 = document.getElementById('receiveCurrency'+j);
                        var product2 = document.getElementById('receiveProduct'+j);
                        var description2 = document.getElementById('receiveDes'+j);
                        var amount2 = document.getElementById('receiveAmount'+j);
                        var exrate2 = document.getElementById('receiveExRate'+j);
                        if(currency2 !== null){
                            var currencyTemp2 = currency2.value;
                            if(product2.value !== '' || description2.value !== '' || amount2.value !== '' || amount2.value !== '' || exrate2.value !== ''){                               
                                if((currencyTemp1 !== currencyTemp2)){
                                    currencyNotMatch = true;
                                    i = countRec+1;
                                    j = countRec+1;
                                }                              
                            }
                            if(currencyTemp1 === '' && currencyTemp2 === ''){
                                currencyNotEmpty++;
                            }
                        }
                    }
                }    
            }    
        }
        if(currencyNotEmpty > 0){
            $("#textAlertCurrencyAmountNotEmpty").hide();
            $("#textAlertCurrencyAmountNotMatch").hide();
            for(var i=1; i<=countRec; i++){
                var currency = document.getElementById('receiveCurrency'+i);
                var product = document.getElementById('receiveProduct'+i);
                var description = document.getElementById('receiveDes'+i);
                var amount = document.getElementById('receiveAmount'+i);
                var exrate = document.getElementById('receiveExRate'+i);
                if(currency !== null){
                    if(product.value !== '' || description.value !== '' || amount.value !== '' || exrate.value !== ''){  
                        currency.style.borderColor = 'red';
                    }    
                }    
            }
            $("#textAlertCurrencyAmountNotEmpty").show();
            return;
        }
        if(currencyNotMatch){
            $("#textAlertCurrencyAmountNotEmpty").hide();
            $("#textAlertCurrencyAmountNotMatch").hide();
            for(var i=1; i<=countRec; i++){
                 var currency = document.getElementById('receiveCurrency'+i);
                 var product = document.getElementById('receiveProduct'+i);
                 var description = document.getElementById('receiveDes'+i);
                 var amount = document.getElementById('receiveAmount'+i);
                 var exrate = document.getElementById('receiveExRate'+i);
                 if(currency !== null){
                     if(product.value !== '' || description.value !== '' || amount.value !== '' || exrate.value !== ''){  
                         currency.style.borderColor = 'red';
                     }    
                 }    
             }
             $("#textAlertCurrencyAmountNotMatch").show();
             return;
        }
        $("#textAlertCurrencyAmountNotEmpty").hide();
        $("#textAlertCurrencyAmountNotMatch").hide();
        if (counter.value > 2) {
            for (i = 1; i < counter.value - 1; i++) {
                var amountTemp = document.getElementById('receiveAmountTemp' + i).value;
                var amount = document.getElementById('receiveAmount' + i).value;
                var currency = document.getElementById('receiveCurrency'+i);
                currency.style.borderColor = 'green';
               
                amount = replaceAll(",", "", amount.toString());
                amountTemp = replaceAll(",", "", amountTemp.toString());

                if (parseFloat(amount) > parseFloat(amountTemp)) {
                    //         $('#textAlertReceiveAmount').show();
                    $('#textAlertAmountOver').show();
                    checksave = 2;
                } else {
                    if (inputStatus !== '7') {
                        var sumAmountBeforeSave = document.getElementById('sumAmountBeforeSave').value;
                        var grandTotal = document.getElementById('grandTotal').value;
                        if (grandTotal === sumAmountBeforeSave) {
                            $('#textAlertReceiveAmount').hide();
                        } else {
                            $('#textAlertReceiveAmount').show();
                            checksave = 2;
                        }

                        if (checksave === 1) {
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromDate');
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'inputStatus');
                            if (receiveFromCode != "" && arCode != "" && receiveFromDate != "" && inputStatus != "") {
                                document.getElementById('ReceiptForm').submit();
                            }
                            $('#textAlertReceiveAmount').hide();
                        }
                    } else {
                        if (checksave === 1) {
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromDate');
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'inputStatus');

                            if (receiveFromCode != "" && arCode != "" && receiveFromDate != "" && inputStatus != "") {
                                document.getElementById('ReceiptForm').submit();
                            }

                            $('#textAlertReceiveAmount').hide();
                        }
                    }
                }
            }
        } else if(counter.value == 2) {
            if (inputStatus !== '7') {
                var sumAmountBeforeSave = document.getElementById('sumAmountBeforeSave').value;
                var grandTotal = document.getElementById('grandTotal').value;
                if (grandTotal === sumAmountBeforeSave) {
                    $('#textAlertReceiveAmount').hide();
                } else {
                    $('#textAlertReceiveAmount').show();
                    checksave = 2;
                }

                if (checksave === 1) {
                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromDate');
                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'inputStatus');
                    if (receiveFromCode != "" && arCode != "" && receiveFromDate != "" && inputStatus != "") {
                        document.getElementById('ReceiptForm').submit();
                    }
                    $('#textAlertReceiveAmount').hide();
                }
            } else {
                if (checksave === 1) {
                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromDate');
                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'inputStatus');

                    if (receiveFromCode != "" && arCode != "" && receiveFromDate != "" && inputStatus != "") {
                        document.getElementById('ReceiptForm').submit();
                    }

                    $('#textAlertReceiveAmount').hide();
                }
            }
        }else {
            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
            $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromDate');
            $('#ReceiptForm').bootstrapValidator('revalidateField', 'inputStatus');
            if (receiveFromCode != "" && arCode != "" && receiveFromDate != "" && inputStatus != "") {
                document.getElementById('ReceiptForm').submit();
            }
        }
    }

    function searchRefNo() {
        if($("#ajaxload1").hasClass("hidden")){
            var refNo = $("#refNo").val();
            if (refNo == "") {
                if (!$('#refnopanel').hasClass('has-feedback')) {
                    $('#refnopanel').addClass('has-feedback');
                }
                $('#refnopanel').removeClass('has-success');
                $('#refnopanel').addClass('has-error');
            }
            else {
                var servletName = 'ReceiptServlet';
                var servicesName = 'AJAXBean';
                var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&refNo=' + refNo +
                        '&type=' + 'searchRefNo';
                CallAjaxSearchRef(param);
            }
        }    
    }

    function CallAjaxSearchRef(param) {
        var url = 'AJAXServlet';
        var BookintType = "";
        $("#ajaxload2").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function(msg) {
                    try {
                        if (msg == "null") {
                            $("#searchinvtable").addClass("hidden");
                            $("#searchreftable").addClass("hidden");
                            $("#searchaircomtable").addClass("hidden");
                            $("#searchtourcomtable").addClass("hidden");
//                        $('#RefNoListTable').dataTable().fnClearTable();
//                        $('#RefNoListTable').dataTable().fnDestroy();
                            $('#RefNoListTable > tbody  > tr').each(function() {
                                $(this).remove();
                            });

                        } else {
                            $("#RefNoListTable tbody").empty().append(msg);
                            
                            var rowAll = $("#RefNoListTable tr").length;
                            if(rowAll === 1){
                                $("#searchreftable").addClass("hidden");
                            }else{
                                $("#searchreftable").removeClass("hidden");
                            }
                            
                            BookintType = $("#masterBookType").val();
                            if (BookintType == $('#typeBooking').val()) {
                                $('#RefNoListTable > tbody  > tr').each(function() {
                                    $(this).remove();
                                });
                                $('#AlertBookingRefno').hide();
                                try {
                                    $("#RefNoListTable tbody").empty().append(msg);
                                    document.getElementById("receiveFromCode").value = $("#receiveFromBillable").val();
                                    document.getElementById("receiveFromName").value = $("#receiveNameBillable").val();
                                    document.getElementById("receiveFromAddress").value = $("#receiveAddressBillable").val();
                                    document.getElementById("arCode").value = $("#arcodeBillable").val();
                                    document.getElementById("inputStatus").value = $("#mAccPayBillable").val();
                                } catch (e) {
                                    alert(e);
                                }
                            } else {
                                $('#RefNoListTable > tbody  > tr').each(function() {
                                    $(this).remove();
                                });
                                $('#AlertBookingRefno').show();
                            }

                        }
                        $("#ajaxload2").addClass("hidden");
                        setinvoice = 1;
                    } catch (e) {
                        alert(e);
                    }

                }, error: function(msg) {
                    $("#ajaxload2").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }

    function refnoValidate() {
        $('#refnopanel').removeClass('has-feedback');
        $('#refnopanel').addClass('has-success');
        $('#refnopanel').removeClass('has-error');
    }

    function searchPaymentNoAir() {
        if($("#ajaxload1").hasClass("hidden")){
            var paymentNoAir = $("#searchPaymentNoAir").val();
            var servletName = 'ReceiptServlet';
            var servicesName = 'AJAXBean';
            var param = 'action=' + 'text' +
                    '&servletName=' + servletName +
                    '&servicesName=' + servicesName +
                    '&paymentNo=' + paymentNoAir +
                    '&type=' + 'searchPaymentNoAir';
            CallAjaxSearchPaymentNoAir(param);
        }    
    }

    function CallAjaxSearchPaymentNoAir(param) {
        var url = 'AJAXServlet';
        $("#ajaxload3").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function(msg) {
                    try {
                        if (msg == "null") {
                            $("#searchinvtable").addClass("hidden");
                            $("#searchreftable").addClass("hidden");
                            $("#searchaircomtable").addClass("hidden");
                            $("#searchtourcomtable").addClass("hidden");
                            
                            $('#AircommissionTable').dataTable().fnClearTable();
                            $('#AircommissionTable').dataTable().fnDestroy();

                            $('#AircommissionTable').dataTable({bJQueryUI: true,
                                "sPaginationType": "full_numbers",
                                "bAutoWidth": true,
                                "bFilter": false,
                                "bPaginate": true,
                                "bInfo": false,
                                "bLengthChange": false,
                                "iDisplayLength": 5
                            });
                            $("#AircommissionTable_wrapper").css("min-height", 100);
                        } else {
//                            $("#searchaircomtable").removeClass("hidden");
                            $('#AircommissionTable').dataTable().fnClearTable();
                            $('#AircommissionTable').dataTable().fnDestroy();
                            $("#AircommissionTable tbody").empty().append(msg);
                            
                            
                            var rowAll = $("#AircommissionTable tr").length;
                            if(rowAll === 1){
                                $("#searchaircomtable").addClass("hidden");
                            }else{
                                $("#searchaircomtable").removeClass("hidden");
                            }
                            
                            $('#AircommissionTable').dataTable({bJQueryUI: true,
                                "sPaginationType": "full_numbers",
                                "bAutoWidth": true,
                                "bFilter": false,
                                "bPaginate": true,
                                "bInfo": false,
                                "bLengthChange": false,
                                "iDisplayLength": 5
                            });

//                        document.getElementById("receiveFromCode").value = $("#receiveFromTicAir").val();
//                        document.getElementById("receiveFromName").value = $("#receiveNameTicAir").val();
//                        document.getElementById("receiveFromAddress").value = $("#receiveAddressTicAir").val();
//                        document.getElementById("arCode").value = $("#receiveFromTicAir").val();

                            $("#AircommissionTable_wrapper").css("min-height", 100);
                        }
                        $("#ajaxload3").addClass("hidden");
                        setinvoice = 0;
                    } catch (e) {
                        alert(e);
                    }

                }, error: function(msg) {
                    $("#ajaxload3").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }
    function searchPaymentNoTour() {
        if($("#ajaxload1").hasClass("hidden")){
            var paymentNoTour = $("#searchPaymentNoTour").val();
            var servletName = 'ReceiptServlet';
            var servicesName = 'AJAXBean';
            var param = 'action=' + 'text' +
                    '&servletName=' + servletName +
                    '&servicesName=' + servicesName +
                    '&paymentNo=' + paymentNoTour +
                    '&type=' + 'searchPaymentNoTour';
            CallAjaxSearchPaymentNoTour(param);
        }    
    }

    function CallAjaxSearchPaymentNoTour(param) {
        var url = 'AJAXServlet';
        $("#ajaxload4").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function(msg) {
                    try {
                        if (msg == "null") {
                            $("#searchinvtable").addClass("hidden");
                            $("#searchreftable").addClass("hidden");
                            $("#searchaircomtable").addClass("hidden");
                            $("#searchtourcomtable").addClass("hidden");
                            
                            $('#TourcommissionTable').dataTable().fnClearTable();
                            $('#TourcommissionTable').dataTable().fnDestroy();
                            $('#TourcommissionTable').dataTable({bJQueryUI: true,
                                "sPaginationType": "full_numbers",
                                "bAutoWidth": true,
                                "bFilter": false,
                                "bPaginate": true,
                                "bInfo": false,
                                "bLengthChange": false,
                                "iDisplayLength": 5
                            });
                            $("#TourcommissionTable_wrapper").css("min-height", 100);
                        } else {
//                            $("#searchtourcomtable").removeClass("hidden");
                            
                            $('#TourcommissionTable').dataTable().fnClearTable();
                            $('#TourcommissionTable').dataTable().fnDestroy();
                            $("#TourcommissionTable tbody").empty().append(msg);
                            
                            var rowAll = $("#TourcommissionTable tr").length;
                            if(rowAll === 1){
                                $("#searchtourcomtable").addClass("hidden");
                            }else{
                                $("#searchtourcomtable").removeClass("hidden");
                            }
                            
                            $('#TourcommissionTable').dataTable({bJQueryUI: true,
                                "sPaginationType": "full_numbers",
                                "bAutoWidth": true,
                                "bFilter": false,
                                "bPaginate": true,
                                "bInfo": false,
                                "bLengthChange": false,
                                "iDisplayLength": 5
                            });
                            $("#TourcommissionTable_wrapper").css("min-height", 100);
                        }
                        $("#ajaxload4").addClass("hidden");
                        setinvoice = 0;
                    } catch (e) {
                        alert(e);
                    }

                }, error: function(msg) {
                    $("#ajaxload4").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }

    function deleteReceiptList(id, Ccount) {
        document.getElementById('receiptDetailIdDelete').value = id;
        document.getElementById('receiptRowDelete').value = Ccount;
        $("#delProduct").text('Are you sure delete this product ?');
        $('#DeleteProduct').modal('show');
    }

    function DeleteRowProduct() {
        $('#textAlertDuplicateProduct').hide();
        var cCount = document.getElementById('receiptRowDelete').value;
        var id = document.getElementById('receiptDetailIdDelete').value;
        if (id === '') {
            $("#receiveProduct" + cCount).parent().parent().remove();
            var rowAll = $("#ReceiptListTable tr").length;
            if (rowAll <= 1) {
                $("#tr_ProductDetailAddRow").removeClass("hide");
                $("#tr_ProductDetailAddRow").addClass("show");
            }
        }
        else {
            $.ajax({
                url: '${callPage}?action=deleteReceiptDetail',
                type: 'get',
                data: {receiptDetailIdDelete: id},
                success: function() {
                    $("#receiveProduct" + cCount).parent().parent().remove();
                    var rowAll = $("#ReceiptListTable tr").length;
                    if (rowAll <= 1) {
                        $("#tr_ProductDetailAddRow").removeClass("hide");
                        $("#tr_ProductDetailAddRow").addClass("show");
                    }
                    calculateGrandTotal();
//                AddRowProduct();
                },
                error: function() {
                    console.log("error");
                    result = 0;
                }
            });
        }
        $('#DeleteProduct').modal('hide');

    var tempcount = parseInt($("#ReceiptListTable tr").length);
    if(tempcount == 1){
        $("#invoiceNo").removeAttr("disabled");
        $("#refNo").removeAttr("disabled");
        $("#ButtonSearchRefNo").removeAttr("disabled");
        $("#ButtonSearchInvoice").removeAttr("disabled");
        $("#searchPaymentNoAir").removeAttr("disabled");
        $("#searchPaymentNoTour").removeAttr("disabled");
        $("#ButtonSearchPaymentNoAir").removeAttr("disabled");
        $("#ButtonSearchPaymentNoTour").removeAttr("disabled");
    }else if(tempcount == 2){
        var count = $("#counter").val();
        count = count-1;
        $("#receiveProduct" + count).parent().parent().remove();
        $("#counter").val(1);
        AddRowProduct(1);
        var amount = document.getElementById('receiveAmount1').value;
        if(amount === ""){
            $("#invoiceNo").removeAttr("disabled");
            $("#refNo").removeAttr("disabled");
            $("#ButtonSearchRefNo").removeAttr("disabled");
            $("#ButtonSearchInvoice").removeAttr("disabled");
            $("#searchPaymentNoAir").removeAttr("disabled");
            $("#searchPaymentNoTour").removeAttr("disabled");
            $("#ButtonSearchPaymentNoAir").removeAttr("disabled");
            $("#ButtonSearchPaymentNoTour").removeAttr("disabled");
        }
    }
        calculateGrandTotal();
    }

    function deleteCreditList(id, Ccount) {
        document.getElementById('receiptCreditIdDelete').value = id;
        document.getElementById('receiptCreditRowDelete').value = Ccount;
        $("#delCredit").text('Are you sure delete this credit ?');
        $('#DeleteReceiptCredit').modal('show');
    }

    function DeleteRowCredit() {
        var cCount = document.getElementById('receiptCreditRowDelete').value;
        var id = document.getElementById('receiptCreditIdDelete').value;
        if (id === '') {
            $("#creditBank" + cCount).parent().parent().remove();
            var rowAll = $("#CreditDetailTable tr").length;
            if (rowAll <= 1) {
                $("#tr_CreditDetailAddRow").removeClass("hide");
                $("#tr_CreditDetailAddRow").addClass("show");
            }
            var tempcount = parseInt($("#CreditDetailTable tr").length);
            if(tempcount == 1){

            }else if(tempcount == 2){
                var count = $("#countRowCredit").val();
                count = count-1;
                $("#creditBank" + count).parent().parent().remove();
                $("#countRowCredit").val(1);
                AddRowCredit(1);
            }
        }
        else {
            $.ajax({
                url: '${callPage}?action=deleteReceiptCredit',
                type: 'get',
                data: {receiptCreditIdDelete: id},
                success: function() {
                    $("#creditBank" + cCount).parent().parent().remove();
                    var rowAll = $("#CreditDetailTable tr").length;
                    if (rowAll <= 1) {
                        $("#tr_CreditDetailAddRow").removeClass("hide");
                        $("#tr_CreditDetailAddRow").addClass("show");
                    }
                    
                    var tempcount = parseInt($("#CreditDetailTable tr").length);
                    if(tempcount == 1){

                    }else if(tempcount == 2){
                        var count = $("#countRowCredit").val();
                        count = count-1;
                        $("#creditBank" + count).parent().parent().remove();
                        $("#countRowCredit").val(1);
                        AddRowCredit(1);
                    }   
                },
                error: function() {
                    console.log("error");
                    result = 0;
                }
            });
        }
        $('#DeleteReceiptCredit').modal('hide');
        

    }

    function DisableVoidReceipt() {
        var receiveNo = document.getElementById('receiveNo');
        document.getElementById('disableVoid').innerHTML = "Are you sure to delete booking other : " + receiveNo.value + " ?";
    }

    function EnableVoidReceipt() {
        var receiveNo = document.getElementById('receiveNo');
        document.getElementById('enableVoid').innerHTML = "Are you sure to enable booking other : " + receiveNo.value + " ?";
    }

    function Enable() {
        var action = document.getElementById('action');
        action.value = 'enableVoid';
        document.getElementById('ReceiptForm').submit();
    }

    function DisableReceipt() {
        var action = document.getElementById('action');
        action.value = 'disableVoid';
        document.getElementById('ReceiptForm').submit();
    }

    function checkAmount(row) {
        var amountTemp = document.getElementById('receiveAmountTemp' + row).value;
        var amount = document.getElementById('receiveAmount' + row).value;

        amountTemp = replaceAll(",", "", amountTemp.toString());
        amount = replaceAll(",", "", amount.toString());

//    $("#receiveAmount"+row).focusout(function(){
//        if(parseInt(amount) > parseInt(amountTemp)){
//            document.getElementById('receiveAmount'+row).value = amountTemp; 
//        }
//    });
        calculateGrandTotal();
    }

    function getDescriptionDetail(row) {
        var description = $('#DescriptionReceiptDetail' + row).val();
        $('#InputDescriptionDetailId').val(row);
        $('#InputDescriptionDetail').val(description);
    }

    function saveDescriptionDetail() {
        var row = $('#InputDescriptionDetailId').val();
        var descriptionDetail = $('#InputDescriptionDetail').val();
        $('#DescriptionReceiptDetail' + row).val(descriptionDetail);
        $('#InputDescriptionDetail' + row).val(descriptionDetail);
        $('#InputDescription' + row).val(descriptionDetail);
    }


    function copyReceipt() {
        if ($("#receiveFromDate").val() != "") {
            $('#CopyReceiptModal').modal('show');
        }
    }

    function confirmCopyReceipt() {
        $('#CopyReceiptModal').modal('hide');
        var action = document.getElementById('action');
        action.value = 'saveReceipt';
        var receiveId = document.getElementById('receiveId');
        receiveId.value = '';
        var receiveNo = document.getElementById('receiveNo');
        receiveNo.value = '';
        var counter = document.getElementById('counter');
        counter.value = $("#ReceiptListTable tr").length;
        var countRowCredit = document.getElementById('countRowCredit');
        countRowCredit.value = $("#CreditDetailTable tr").length;
        document.getElementById('ReceiptForm').submit();
    }

    function calculateGrandTotal() {
//    var temp = 0;
//    var i = 1;
//    var amountTemp = parseFloat(0);
//    var tableProduct = $("#ReceiptListTable tr").length;
//    for (i ; i < tableProduct ; i++) {
//        temp = document.getElementById("receiveAmount" + i);
//        if(temp !== null){
//            temp = temp.value;
//            if(temp == '') {
//                temp = 0;
//            }
//            temp = replaceAll(",","",temp.toString());
//            var value = parseFloat(temp) ;
//            var amount = amountTemp + value ;
//            amountTemp = amount;
//        }   
//    }
//    document.getElementById("grandTotal").value = formatNumber(amount);

        var count = parseInt(document.getElementById('counter').value);
        var i;
        var grandTotal = 0;
        for (i = 1; i < count + 1; i++) {
            var amount = document.getElementById("receiveAmount" + i);
            if (amount !== null) {
                var value = amount.value;
                if (value !== '') {
                    value = value.replace(/,/g, "");
                    var total = parseFloat(value);
                    grandTotal += total;
                    document.getElementById('receiveAmount' + i).value = formatNumber(total);
                }
            }
        }
        document.getElementById('grandTotal').value = formatNumber(grandTotal);
    }


    function setFormatCurrencyOnFocusOut(row) {
        $('#receiveAmount' + row).focusout(function() {
            setFormatCurrency(row);
            calculateGrandTotal();
        });

        $('#receiveCost' + row).focusout(function() {
            setFormatCurrency(row);
            calculateGrandTotal()
        });

    }
    
    function setFormatExRateOnFocusOut(row) {
        $('#receiveExRate' + row).focusout(function() {
            setFormatExRate(row);
        });
    }

    function checkSumAmountBeforeSave() {
        sumTotalCreditAmount();

        var sumCreditAmountTemp = replaceAll(",", "", $("#sumCreditAmountTemp").val());
        if (sumCreditAmountTemp == "") {
            sumCreditAmountTemp = 0;
        }

        var chqAmount1 = replaceAll(",", "", $("#chqAmount1").val());
        if (chqAmount1 == "") {
            chqAmount1 = 0;
        }
        var chqAmount2 = replaceAll(",", "", $("#chqAmount2").val());
        if (chqAmount2 == "") {
            chqAmount2 = 0;
        }

        var withTax = replaceAll(",", "", $("#withTax").val());
        if (withTax == "") {
            withTax = 0;
        }

        var cashAmount = replaceAll(",", "", $("#cashAmount").val());
        if (cashAmount == "") {
            cashAmount = 0;
        }

        var bankTransfer = replaceAll(",", "", $("#bankTransfer").val());
        if (bankTransfer == "") {
            bankTransfer = 0;
        }

        var sumCreditAmount = parseFloat(sumCreditAmountTemp);
        var chq1 = parseFloat(chqAmount1);
        var chq2 = parseFloat(chqAmount2);
        var tax = parseFloat(withTax);
        var cash = parseFloat(cashAmount);
        var bank = parseFloat(bankTransfer);

        //Sum Amount =  W/T + Cast Amount + Bank Transfer + Sum  Amount of Chq Bank + Sum Amount in table Credit Card
        var sumAmount = tax + cash + bank + chq1 + chq2 + sumCreditAmount;
        document.getElementById("sumAmountBeforeSave").value = formatNumber(sumAmount);

    }


    function sumTotalCreditAmount() {
        //Sum Amount =  W/T + Cast Amount + Bank Transfer + Sum  Amount of Chq Bank + Sum Amount in table Credit Card

        var temp = 0;
        var i = 1;
        var amountTemp = parseFloat(0);
        var tableCredit = $("#CreditDetailTable tr").length;
        for (i; i < tableCredit; i++) {
            temp = document.getElementById("creditAmount" + i);
            if (temp !== null) {
                temp = temp.value;
                if (temp == '') {
                    temp = 0;
                }
                temp = replaceAll(",", "", temp.toString());
                var value = parseFloat(temp);
                var amount = amountTemp + value;
                amountTemp = amount;
            }
        }
        document.getElementById("sumCreditAmountTemp").value = formatNumber(amount);
    }
    
    function showSearchInvno(){
        if($("#searchinvtext").hasClass("hidden")){
            $("#searchreftext").addClass("hidden");
            $("#searchaircomtext").addClass("hidden");
            $("#searchtourcomtext").addClass("hidden");
            $("#searchinvtext").removeClass("hidden");
        }else{
            $("#searchinvtext").addClass("hidden");
        }
        $("#searchtourcomtable").addClass("hidden");
        $("#searchaircomtable").addClass("hidden");
        $("#searchreftable").addClass("hidden");
        $("#searchinvtable").addClass("hidden");
    }
    
    function showSearchRefno(){
        if($("#searchreftext").hasClass("hidden")){
            $("#searchinvtext").addClass("hidden");
            $("#searchaircomtext").addClass("hidden");
            $("#searchtourcomtext").addClass("hidden");
            
            $("#searchreftext").removeClass("hidden");
        }else{
            $("#searchreftext").addClass("hidden");
        }
        $("#searchtourcomtable").addClass("hidden");
        $("#searchaircomtable").addClass("hidden");
        $("#searchreftable").addClass("hidden");
        $("#searchinvtable").addClass("hidden");
    }
    
    function showSearchAirCom(){
        if($("#searchaircomtext").hasClass("hidden")){
            $("#searchinvtext").addClass("hidden");
            $("#searchreftext").addClass("hidden");
            $("#searchtourcomtext").addClass("hidden");
            
            $("#searchaircomtext").removeClass("hidden");
        }else{
            $("#searchaircomtext").addClass("hidden");
        }
        $("#searchtourcomtable").addClass("hidden");
        $("#searchaircomtable").addClass("hidden");
        $("#searchreftable").addClass("hidden");
        $("#searchinvtable").addClass("hidden");
    }
    
    function showSearchTourCom(){
        if($("#searchtourcomtext").hasClass("hidden")){
            $("#searchinvtext").addClass("hidden");
            $("#searchreftext").addClass("hidden");
            $("#searchaircomtext").addClass("hidden");
            
            $("#searchtourcomtext").removeClass("hidden");
        }else{
            $("#searchtourcomtext").addClass("hidden");
        }
        $("#searchtourcomtable").addClass("hidden");
        $("#searchaircomtable").addClass("hidden");
        $("#searchreftable").addClass("hidden");
        $("#searchinvtable").addClass("hidden");
    }

</script>
