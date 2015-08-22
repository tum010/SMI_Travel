<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/Receipt.js"></script>--> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>

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

<c:set var="roleName" value="${requestScope['roleName']}" />
<section class="content-header" >
    <h1>
        Finance & Cashier - Receipt 
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Finance & Cashier</a></li>          
        <li class="active"><a href="#">Receipt</a></li>
    </ol>
</section>

<div style="padding-top: 15px;padding-right: 0px "ng-app=""> 
    <div class="row">
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
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/ReceiptMenu.html'"></div>
        </div>
        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <c:choose>
                        <c:when test="${fn:contains(page , 'WT')}">
                            <c:set var="typeReceipt" value="T" />
                            <c:set var="typeDepartment" value="Wendy" />
                            <h4><b>Receipt Temp Wendy</b></h4>
                        </c:when>
                        <c:when test="${fn:contains(page , 'WV')}">
                            <c:set var="typeReceipt" value="V" />
                            <c:set var="typeDepartment" value="Wendy" />
                            <h4><b>Receipt Vat Wendy</b></h4>
                        </c:when>    
                        <c:when test="${fn:contains(page , 'OT')}">
                            <c:set var="typeReceipt" value="T" />
                            <c:set var="typeDepartment" value="Outbound" />
                            <h4><b>Receipt Temp Outbound</b></h4>
                        </c:when>    
                        <c:when test="${fn:contains(page , 'OV')}">
                            <c:set var="typeReceipt" value="V" />
                            <c:set var="typeDepartment" value="Outbound" />
                            <h4><b>Receipt Vat Outbound</b></h4>
                        </c:when>    
                        <c:when test="${fn:contains(page , 'IT')}">
                            <c:set var="typeReceipt" value="T" />
                            <c:set var="typeDepartment" value="Inbound" />
                            <h4><b>Receipt Temp Inbound</b></h4>
                        </c:when>   
                        <c:when test="${fn:contains(page , 'IV')}">
                            <c:set var="typeReceipt" value="V" />
                            <c:set var="typeDepartment" value="Inbound" />
                            <h4><b>Receipt Vat Inbound</b></h4>
                        </c:when>    
                    </c:choose>                
                </div>
                <div class="col-xs-12 form-group"><hr/></div>
            </div>
            <hr/>

            <form action="${callPage}" method="post" id="ReceiptForm" name="ReceiptForm" role="form">
                <div role="tabpanel">
                     <!-- Nav tabs -->
                     
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#inv" aria-controls="inv" role="tab" data-toggle="tab">INV</a></li>
                        <li role="presentation" class=""><a href="#ref" aria-controls="ref" role="tab" data-toggle="tab">REF</a></li>
                        <li role="presentation" class=""><a href="#com" aria-controls="com" role="tab" data-toggle="tab">COM</a></li>
                        <h4><a class="col-xs-9 text-right" data-toggle="collapse" href="#collapseTab" aria-expanded="false" aria-controls="collapseTab">
                            <span id="arrowReceipt" class="arrowReceipt glyphicon glyphicon-chevron-up"></span>
                        </a></h4>
                    </ul>
                                
                    <!-- Tab BL -->
                    <div class="panel panel-default">
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
                                        <!--Invoice Table-->
                                        <div class="row" style="padding-left: 10px;padding-right: 10px">
                                            <table id="InvoiceListTable" class="display" cellspacing="0" width="100%">
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
                                <!-- Tab REF -->
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
                                        <!--RefNo Table-->
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
                                <!-- Tab COM -->
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
                                        <!--Invoice Table-->
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
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="panel panel-default">
                        <div class="panel-body"  style="padding-right: 0px;">
                            <div class="col-xs-8" style="padding-top: 0px;">
                                <div class="col-xs-1 text-right" style="width: 135px">
                                    <label class="control-label text-right">Receive No </label>                                    
                                </div>
                                <div class="col-xs-1" style="width: 180px" id='receivenumber'>
                                    <input id="receiveId" name="receiveId" type="hidden" class="form-control" maxlength="11" value="${receipt.id}">
                                    <input id="receiveNo" name="receiveNo" type="text" style="width: 180px" class="form-control" maxlength="20" value="${receipt.recNo}">
                                </div>
                                <div class="col-xs-1 text-right" style="width:10px"></div>
                                <div class="col-xs-1 text-right" style="width: 80px">
                                    <button style="height:34px" type="button"  id="ButtonSearch"  name="ButtonSearch" onclick="searchReceiveNo();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search</button>
                                </div>
                                <div class="col-xs-1 text-right" style="width:10px"></div>
                                <div class="col-xs-1 text-left" style="width: 70px">
                                    <label class="control-label text-right">Date </label>
                                </div>
                                <div class="col-xs-1 form-group" style="width: 170px">
                                    <div class='input-group date'>
                                        <input id="inputDate" name="inputDate"  type="text" 
                                           class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                                <div class="col-xs-1 text-right" style="width: 135px">
                                    <label class="control-label text-right" for="codeBillto">Receive From <font style="color: red">*</font></label> 
                                </div>
                                <div class="form-group col-xs-1 text-right" style="width: 560px">
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="receiveFromId" name="receiveFromId" value=""/>
                                        <input type="text" class="form-control" id="receiveFromCode" name="receiveFromCode" maxlength="11" value="${receipt.recFrom}" style="text-transform:uppercase"/>
                                        <span class="input-group-addon" id="receive_modal"  data-toggle="modal" data-target="#ReceiveFromModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-xs-1 text-right" style="width: 135px">
                                     <label class="control-label text-right">Name </label> 
                                </div>
                                <div class="form-group col-xs-1 text-right" style="width: 560px">
                                    <input type="text" class="form-control" id="receiveFromName" name="receiveFromName" value="${receipt.recName}">                           
                                </div>
                                <div class="col-xs-1 text-right" style="width: 135px">
                                     <label class="control-label text-right">Address </label>  
                                </div>
                                <div class="form-group col-xs-1 text-right" style="width: 560px">
                                    <div class="input-group">                                    
                                        <textarea rows="3" class="form-control" id="receiveFromAddress" name="receiveFromAddress" style="width: 327%" value="${receipt.recAddress}">${receipt.recAddress}</textarea>  
                                    </div>                               
                                </div>
                                <div class="col-xs-1 text-right" style="width: 135px">
                                     <label class="control-label text-right">Remark </label> 
                                </div>
                                <div class="form-group col-xs-1 text-right" style="width: 560px">
                                    <input type="text" class="form-control" id="remark" name="remark" value="${receipt.remark}">                           
                                </div>
                            </div>
                            <div class="col-xs-4" style="padding-top: 0px;">
                                <div class="col-xs-1 text-right" style="width: 130px">
                                    <label class="control-label text-right">Receive Date </label>
                                </div>
                                <div class="col-xs-1 form-group" style="width: 170px">
                                    <div class='input-group date'>
                                        <input id="receiveFromDate" name="receiveFromDate"  type="text" 
                                           class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['receiveFromDate']}">
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>  
                                </div>
                                <div class="col-xs-1 text-right" style="width: 130px">
                                    <label class="control-label text-right">Status </label>
                                </div>
                                <div class="form-group col-xs-1" style="width: 170px">
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
                                <div class="col-xs-1 text-right" style="width: 130px">
                                    <label class="control-label text-right">A/R Code <font style="color: red">*</font></label>                                    
                                </div>
                                <div class="form-group col-xs-1" style="width: 170px">
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="arCode" name="arCode" maxlength="11" value="${receipt.arCode}" readonly="" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <!---Table-->
                        <div class="row" style="padding-right: 10px;padding-left: 10px;padding-bottom:  10px;">
                            <div class="col-md-12 ">
                                <table id="ReceiptListTable" class="display" cellspacing="0" width="100%">
                                    <thead>
                                        <tr class="datatable-header">
                                            <th style="width:10%;">Product</th>
                                            <th style="width:15%;">Description</th>
                                            <th style="width:10%;">Cost</th>
                                            <th style="width:7%;">Cur</th>
                                            <th style="width:5%;">Is Vat</th>
                                            <th style="width:4%;">Vat</th>
                                            <!--<th style="width:10%;">Gross</th>-->
                                            <th style="width:10%;">Amount</th>
                                            <th style="width:7%;">Cur</th>
                                            <th style="width:5%;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody> 
                                        <c:forEach var="table" items="${receiptDetailList}" varStatus="i">
                                            <tr>
                                                <input type="hidden" name="count${i.count}" id="count${i.count}" value="${i.count}">
                                                <input id="invId${i.count}"  name="invId${i.count}"   type="hidden" value="${table.invoiceDetail.id}" >
                                                <input type="hidden" name="tableId${i.count}" id="tableId${i.count}" value="${table.id}">
                                                <input id="receiveAmountTemp${i.count}" name="receiveAmountTemp${i.count}"  type="hidden" value="${table.amount}" >
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
                                                <td><input maxlength="255" id="receiveDes${i.count}" name="receiveDes${i.count}" type="text" class="form-control" value="${table.displayDescription}"></td>
                                                <td><input maxlength="10" id="receiveCost${i.count}"  name="receiveCost${i.count}"  type="text" class="form-control text-right"  value="${table.cost}" onkeyup="insertCommas(this)"></td>
                                                <td>                                   
                                                    <select class="form-control" name="receiveCurCost${i.count}" id="receiveCurCost${i.count}">
                                                        <option  value="" >---------</option>
                                                        <c:forEach var="curCost" items="${currencyList}" varStatus="status">                                       
                                                            <c:set var="select" value="" />
                                                            <c:if test="${curCost.code == table.curCost}">
                                                                <c:set var="select" value="selected" />
                                                            </c:if>
                                                            <option  value="${curCost.code}" ${select}>${curCost.code}</option>
                                                        </c:forEach>
                                                    </select>                                                                  
                                                </td>
                                                <td align="center">
                                                    <c:choose>
                                                        <c:when test="${table.isVat == '1'}">
                                                            <input type="checkbox" checked name="receiveIsVat${i.count}" id="receiveIsVat${i.count}" onclick="handleClick(this,${i.count})" value="${table.isVat}">
                                                        </c:when>
                                                        <c:when test="${table.isVat == '0'}">
                                                            <input type="checkbox"  name="receiveIsVat${i.count}" id="receiveIsVat${i.count}" onclick="handleClick(this,${i.count})" value="${table.isVat}">
                                                        </c:when>
                                                    </c:choose>
                                                </td> 
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${table.isVat == '1'}">
                                                            <div id="receiveVat${i.count}" name="receiveVat${i.count}"  value="">${requestScope['vat']}</div>                                                        
                                                        </c:when>
                                                        <c:when test="${table.isVat == '0'}">
                                                            <div id="receiveVat${i.count}" name="receiveVat${i.count}" style="display:none" ></div>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td><input id="receiveAmount${i.count}" name="receiveAmount${i.count}" type="text" class="form-control text-right" onkeyup="insertCommas(this)" onkeypress="checkAmount('${i.count}')" value="${table.amount}"></td>
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
                                                <td class="text-center">
                                                    <a class="remCF"><span id="SpanRemove${i.count}" onclick="deleteReceiptList('${table.id}','${i.count}');" class="glyphicon glyphicon-remove deleteicon "></span></a>
                                                </td>                                   
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>      
                            </div>
                        </div> 
                        <!--<input type="hidden" name="mAccPayBillable" id="mAccPayBillable" value="">-->
                        <input type="hidden" name="amountTemp" id="amountTemp" value="">
                        <input type="hidden" name="receiptIdDelete" id="receiptIdDelete" value="">
                        <input type="hidden" name="receiptDetailIdDelete" id="receiptDetailIdDelete" value="">
                        <input type="hidden" name="receiptRowDelete" id="receiptRowDelete" value="">
                        
                        <input type="hidden" name="receiptCreditIdDelete" id="receiptCreditIdDelete" value="">
                        <input type="hidden" name="receiptCreditRowDelete" id="receiptCreditRowDelete" value="">
                        
                        <input type="hidden" name="action" id="action" value="">
                        <input type="hidden" class="form-control" id="countRowCredit" name="countRowCredit" value="${requestScope['creditRowCount']}" />
                        <input type="hidden" class="form-control" id="counter" name="counter" value="${requestScope['productRowCount']}" />
                        <input type="hidden" name="vatValue" id="vatValue" value="${requestScope['vat']}">
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
                            <a class="btn btn-success" onclick="AddRowProduct()">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                        <div id="tr_CreditDetailAddRow" class="text-center hide" style="padding-top: 10px">
                            <a class="btn btn-success" onclick="AddRowCredit()">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                        <div class="row" style="padding-top: 15px;padding-bottom:  15px; padding-left:  650px;">
                            <div class="col-xs-1 text-right" style="width: 130px">
                                <label class="control-label text-right">Grand Total </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <input type="text" class="form-control" id="grandTotal" name="grandTotal" value="" />
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-body"  style="padding-right: 0px;">
                            <div class="col-xs-12">
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">W/T </label>                                    
                                    </div>
                                    <div class="col-xs-1 " style="width: 200px">
                                        <input id="withTax" name="withTax" type="text" class="form-control"  maxlength="12" style="text-align: right" onkeyup="insertCommas(this)" value="${receipt.withTax}">
                                    </div>
                                </div><hr/>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Cash Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 200px">
                                        <input id="cashAmount" name="cashAmount" type="text" class="form-control" maxlength="12" style="text-align: right" onkeyup="insertCommas(this)" value="${receipt.cashAmount}" >
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 160px">
                                        <label class="control-label text-right">Cash(-) Amount</label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 200px">
                                        <input id="cashMinusAmount" name="cashMinusAmount" type="text" class="form-control" value="${receipt.cashMinusAmount}" maxlength="12" style="text-align: right" onkeyup="insertCommas(this)">
                                    </div>
                                </div><hr/>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Bank Transfer </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 200px">
                                        <input id="bankTransfer" name="bankTransfer" type="text" class="form-control" value="${receipt.bankTransfer}" maxlength="12" style="text-align: right" onkeyup="insertCommas(this)">
                                    </div>
                                </div><hr/>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Chq Bank </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px" id="chqBank1" name="chqBank1" type="text" class="form-control" value="${receipt.chqBank1}" maxlength="100">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Chq No </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input style="width: 115px" id="chqNo1" name="chqNo1" type="text" class="form-control" value="${receipt.chqNo1}" maxlength="100">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Date </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="chqDate1" name="chqDate1"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['chqDate1']}">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 100px">
                                        <label class="control-label text-right">Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input id="chqAmount1" name="chqAmount1" type="text" class="form-control" value="${receipt.chqAmount1}" maxlength="12" style="text-align: right" onkeyup="insertCommas(this)">
                                        
                                    </div>
                                    <div class="col-xs-1" style="width: 50px ;">
                                        <h4><a class="col-xs-1">
                                        <span class="glyphicon glyphicon-plus-sign" id="addChqButton"></span>
                                        </a></h4>                                        
                                    </div>
                                </div>
                                <div class="row hidden active" id="addChq" style="padding-top: 8px ">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Chq Bank </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px" id="chqBank2" name="chqBank2" type="text" class="form-control" value="${receipt.chqBank2}" maxlength="100">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Chq No </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input  style="width: 115px" id="chqNo2" name="chqNo2" type="text" class="form-control" value="${receipt.chqNo2}"  maxlength="100">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Date </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="chqDate2" name="chqDate2"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['chqDate2']}">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 100px">
                                        <label class="control-label text-right">Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input id="chqAmount2" name="chqAmount2" type="text" class="form-control" value="${receipt.chqAmount2}" maxlength="12" style="text-align: right" onkeyup="insertCommas(this)">
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
                                            <th style="width:22%;">Bank</th>
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
                                                    <a class="remCF"><span id="SpanRemove${i.count}" onclick="deleteCreditList('${table.id}','${i.count}');" class="glyphicon glyphicon-remove deleteicon "></span></a>
                                                </td>                                   
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                          
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-body"  style="padding-right: 0px;">
                            <div class="col-xs-12">
                                <div class="col-md-1 text-left" style="width: 200px" >
                                    <select name="selectPrint" id="selectPrint" class="form-control" style="height:34px">
                                        <option value="">--- Select Print ---</option> 
                                         <c:choose>
                                            <c:when test="${requestScope['SelectPrint'] == '1'}">
                                                <c:set var="selected1" value="selected" />
                                            </c:when>
                                        </c:choose>
                                        <option value="1" ${selected1}>Receipt</option>
                                        <c:choose>
                                            <c:when test="${requestScope['SelectPrint'] == '2'}">
                                                <c:set var="selected2" value="selected" />
                                            </c:when>
                                        </c:choose>
                                        <option value="2" ${selected2}>Receipt Email</option>
                                        <c:choose>
                                            <c:when test="${requestScope['SelectPrint'] == '3'}">
                                                <c:set var="selected3" value="selected" />
                                            </c:when>
                                        </c:choose>
                                        <option value="3" ${selected3}>Invoice</option>
                                    </select>
                                </div>
                                <div class="col-md-1 text-left " style="width: 100px">
                                    <button type="button" class="btn btn-default" onclick="printReceipt()">
                                        <span id="buttonPrint" class="glyphicon glyphicon-print" ></span> Print 
                                    </button>
                                </div>
                                <div class="col-md-1 text-left " style="width: 125px">
                                    <button type="button" class="btn btn-default" onclick="sendEmailReceipt()">
                                        <span id="buttonEmail" class="glyphicon glyphicon-send" ></span> SendEmail 
                                    </button>
                                </div>
                                <div class="col-md-3 text-right " ></div>
                                <div class="col-md-2 text-right ">
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
                                    <c:if test="${receipt.MFinanceItemstatus.id == '2'}">        
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
                                    <button type="submit" id="ButtonSave" name="ButtonSave" onclick="saveReceipt()" class="btn btn-success" ${isSaveVoid}><i class="fa fa-save"></i> Save</button>
                                </div>
                                <div class="col-md-1 text-right ">
                                    <button type="submit" id="ButtonNew" name="ButtonNew" onclick="clearNew()" class="btn btn-success"><i class="fa fa-plus-circle"></i> New</button>
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
                    $(document).ready(function () {
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
                        
                        $('#ReceiveFromTable tbody').on('click', 'tr', function () {
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
                        $("#receiveFromCode").keyup(function(event){   
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left); 
                            if($(this).val() === ""){
                                $("#receiveFromName").val("");
                                $("#receiveFromAddress").val("");
                                $("#arCode").val("");
                            }else{
//                                if(event.keyCode === 13){
                                    searchCustomerAutoList(this.value); 
//                                }
                            }
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                        });
                        $("#receiveFromCode").keydown(function(){
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left); 
                            if(showflag == 0){
                                $(".ui-widget").css("top", -1000);
                                showflag=1;
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
                    
                    function searchCustomerAutoList(name){
                        var servletName = 'BillableServlet';
                        var servicesName = 'AJAXBean';
                        var param = 'action=' + 'text' +
                                '&servletName=' + servletName +
                                '&servicesName=' + servicesName +
                                '&name=' + name +
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
                                    $("#receiveFromId").val(billid);
                                    $("#receiveFromName").val(billname);
                                    $("#receiveFromAddress").val(billaddr);
                                    
                                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                                    
                                    $("#receiveFromCode").autocomplete({
                                        source: billArray,
                                        close: function(){
                                            $("#receiveFromCode").trigger("keyup");
                                            var billselect = $("#receiveFromCode").val();
                                            for(var i =0;i<billListId.length;i++){
                                                if((billselect==billListName[i])||(billselect==billListId[i])){      
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
                                    for(var i =0;i<billListId.length;i++){
                                        if(billval==billListName[i]){
                                            $("#receiveFromCode").val(billListId[i]);
                                            $("#arCode").val(billListId[i]);
                                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                                        }
                                    }
                                    if(billListId.length == 1){
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
                <div class="col-xs-1" style="width: 500px">
                    <label class="text-right">select option for print receipt<font style="color: red">*</font></label>                                    
                </div>
                <div class="text-center" style="width: 250px" >
                    <select name="optionPrint" id="optionPrint" class="form-control" style="height:34px">
                        <option value="1" >Not Show Description</option>
                        <option value="2" >Show Description</option>
                        <option value="3" >Print Format Package Tour</option>
                    </select>
                </div>
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
    $(document).ready(function() {
        $("#inv,#ref,#com").removeClass('hidden');
        $('.datemask').mask('0000-00-00');
        $('.date').datetimepicker();
        $(".money").mask('000,000,000.00', {reverse: true});
//        $(".moneyformat").mask('000,000,000', {reverse: true});
        $("#receiveNo").keyup(function (event) {
            if(event.keyCode === 13){
               searchReceiveNo();
            }
        });
        $("#invoiceNo").keyup(function (event) {
            if(event.keyCode === 13){
               searchInvoice();
            }
        });
        $("#refNo").keyup(function (event) {
            if(event.keyCode === 13){
               searchRefNo();
            }
        });
        $("#searchPaymentNoAir").keyup(function (event) {
            if(event.keyCode === 13){
               searchPaymentNoAir();
            }
        });
		
	$("#searchPaymentNoTour").keyup(function (event) {
            if(event.keyCode === 13){
               searchPaymentNoTour();
            }
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
                }
            }
        });
            
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
        
        $('#deleteCreditButton').on('click', function() {
            $("#addCredit").addClass('hidden');
            $("#addCreditDetail").addClass('hidden');
        });

        $('#collapseTab').on('shown.bs.collapse', function () {
            $(".arrowReceipt").removeClass("glyphicon glyphicon-chevron-down").addClass("glyphicon glyphicon-chevron-up");
        });

        $('#collapseTab').on('hidden.bs.collapse', function () {
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
        
        $('#ReceiptListTable tbody tr:last td .input-group-addon').click(function(){  
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
        $("#ReceiptListTable").on("change", "select:last", function () {
            var row = parseInt($("#counter").val());
            AddRowProduct(row);
        });
        $("#ReceiptListTable").on('click', '.newRemCF', function () {
            $(this).parent().parent().remove();
                var rowAll = $("#ReceiptListTable tr").length;
                if (rowAll < 2) {
                    $("#tr_ProductDetailAddRow").removeClass("hide");
                    $("#tr_ProductDetailAddRow").addClass("show");
            }
        });
        $("#tr_ProductDetailAddRow a").click(function () {
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
        
        $("#CreditDetailTable").on("change", "select:last", function () {
            var row = parseInt($("#countRowCredit").val());
            AddRowCredit(row);
        });
        
        $("#CreditDetailTable").on('click', '.newRemCF', function () {
            $(this).parent().parent().remove();
                var rowAll = $("#CreditDetailTable tr").length;
                if (rowAll < 2) {
                    $("#tr_CreditDetailAddRow").removeClass("hide");
                    $("#tr_CreditDetailAddRow").addClass("show");
            }
        });
        
        $("#tr_CreditDetailAddRow a").click(function () {
            $(this).parent().removeClass("show");
            $(this).parent().addClass("hide");
        });
        
        
        //set format money
        $("#withTax").focusout(function(){
            setFormatCurrencyReceipt();
        }); 
        $("#cashAmount").focusout(function(){
            setFormatCurrencyReceipt();
        }); 
        $("#cashMinusAmount").focusout(function(){
            setFormatCurrencyReceipt();
        }); 
        $("#bankTransfer").focusout(function(){
            setFormatCurrencyReceipt();
        }); 
        $("#chqAmount1").focusout(function(){
            setFormatCurrencyReceipt();
        }); 
        $("#chqAmount2").focusout(function(){
            setFormatCurrencyReceipt();
        });
        
        setFormatCurrencyReceipt();       
//        var creditlength = $("#CreditDetailTable tr").length ;
//        var detaillength = $("#ReceiptListTable tr").length ;
        
    });
    
    function setFormatCurrencyReceipt(){
        var withTax = replaceAll(",","",$('#withTax').val()); 
        if (withTax == ""){
            withTax = 0;
        }
        withTax = parseFloat(withTax); 
        document.getElementById("withTax").value = formatNumber(withTax);

        if (withTax == "" || withTax == 0){
            document.getElementById("withTax").value = "";
        }
        
        var cashAmount = replaceAll(",","",$('#cashAmount').val()); 
        if (cashAmount == ""){
            cashAmount = 0;
        }
        cashAmount = parseFloat(cashAmount); 
        document.getElementById("cashAmount").value = formatNumber(cashAmount);

        if (cashAmount == "" || cashAmount == 0){
            document.getElementById("cashAmount").value = "";
        }
        
        var cashMinusAmount = replaceAll(",","",$('#cashMinusAmount').val()); 
        if (cashMinusAmount == ""){
            cashMinusAmount = 0;
        }
        cashMinusAmount = parseFloat(cashMinusAmount); 
        document.getElementById("cashMinusAmount").value = formatNumber(cashMinusAmount);

        if (cashMinusAmount == "" || cashMinusAmount == 0){
            document.getElementById("cashMinusAmount").value = "";
        }
        
        var bankTransfer = replaceAll(",","",$('#bankTransfer').val()); 
        if (bankTransfer == ""){
            bankTransfer = 0;
        }
        bankTransfer = parseFloat(bankTransfer); 
        document.getElementById("bankTransfer").value = formatNumber(bankTransfer);

        if (bankTransfer == "" || bankTransfer == 0){
            document.getElementById("bankTransfer").value = "";
        }
        
        var chqAmount1 = replaceAll(",","",$('#chqAmount1').val()); 
        if (chqAmount1 == ""){
            chqAmount1 = 0;
        }
        chqAmount1 = parseFloat(chqAmount1); 
        document.getElementById("chqAmount1").value = formatNumber(chqAmount1);

        if (chqAmount1 == "" || chqAmount1 == 0){
            document.getElementById("chqAmount1").value = "";
        }
        
        var chqAmount2 = replaceAll(",","",$('#chqAmount2').val()); 
        if (chqAmount2 == ""){
            chqAmount2 = 0;
        }
        chqAmount2 = parseFloat(chqAmount2); 
        document.getElementById("chqAmount2").value = formatNumber(chqAmount2);

        if (chqAmount2 == "" || chqAmount2 == 0){
            document.getElementById("chqAmount2").value = "";
        }
    }
    
    function printReceiptNew() {
        window.open("report.smi?name=ReceiptEmail");
    }
    
    function printReceipt() {
        $('#PrintReceiptModal').modal('show');       
    }
    
    function confirmPrintReceipt() {
        $('#PrintReceiptModal').modal('hide'); 
        var printtype = document.getElementById('selectPrint').value;
        var receiveId = document.getElementById('receiveId').value;
        var receiveNo = document.getElementById('receiveNo').value;
        var optionPrint =  document.getElementById('optionPrint').value;
        if(receiveId == ""){
            alert("please save before print");
        }else if(printtype == 0){
            alert('please select option print');
        }else if(printtype == 1){
            window.open("report.smi?name=ReceiptReport&receiveId="+receiveId+"&receiveNo="+receiveNo+"&optionPrint="+optionPrint);
        }else if(printtype == 2){
            window.open("report.smi?name=ReceiptEmail&receiveId="+receiveId+"&receiveNo="+receiveNo+"&optionPrint="+optionPrint);
        }else if(printtype == 3){
//            window.open("report.smi?name=ReceiptEmail&receiveId="+receiveId+"&receiveNo="+receiveNo+"&optionPrint="+optionPrint);
        }          
    }
    //http://localhost:8080/SMITravel/SendMail.smi?reportname=Invoice
    function sendEmailReceipt(){
        var receiveId = document.getElementById('receiveId').value;
        window.open("SendMail.smi?reportname=ReceiptEmail&reportid="+receiveId);
    }
    
    function AddRowProduct(row) {           
            $("#ReceiptListTable tbody").append(
                '<tr style="higth 100px">' +
                '<input id="tableId' + row + '" name="tableId' + row + '"  type="hidden" >' +
                '<td>' + 
                '<select class="form-control" name="receiveProduct' + row + '" id="receiveProduct' + row + '" ><option value="">---------</option></select>' +                          
                '</td>' +
                '<td><input maxlength="255" id="receiveDes' + row + '" name="receiveDes' + row + '" type="text" class="form-control" ></td>' +
                '<td><input maxlength="10" id="receiveCost' + row + '" name="receiveCost' + row + '" type="text" class="form-control" onkeyup="insertCommas(this)" disabled="disabled" ></td>' +
                '<td>' + 
                '<select class="form-control" name="receiveCurCost' + row + '" id="receiveCurCost' + row + '" disabled="disabled"><option value="">---------</option></select>' +                          
                '</td>' +
                '<td align="center">' +
                '<input type="checkbox" name="receiveIsVat' + row + '" id="receiveIsVat' + row + '" onclick="handleClick(this,'+row+')" value="" disabled="disabled">' +
                '</td>' +
                '<td><div id="receiveVat' + row + '" style="display:none" ></div></td>' +
                '<td><input id="receiveAmount' + row + '" name="receiveAmount' + row + '" type="text" class="form-control text-right" onkeyup="insertCommas(this)"></td>' +
                '<td>' + 
                '<select class="form-control" name="receiveCurrency' + row + '" id="receiveCurrency' + row + '" ><option value="">---------</option></select>' +                          
                '</td>' +
                '<td class="text-center">' +
                '<a class="remCF" onclick="deleteReceiptList(\'\', \''+row+'\')">  '+
                '<span id="SpanRemove' + row + '"class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
                '</tr>'
            );
            $("#billTypeList option").clone().appendTo("#receiveProduct" + row);
            $("#currencyList option").clone().appendTo("#receiveCurrency" + row);
            $("#currencyList option").clone().appendTo("#receiveCurCost" + row);
            
            $("#receiveAmount"+row).focusout(function(){
//        calculatGross(row);
                setFormatCurrency(row);
            }); 
            $("#receiveCost"+row).focusout(function(){
                setFormatCurrency(row);
            }); 
            var tempCount = parseInt($("#counter").val()) + 1;
            $("#counter").val(tempCount);
//        }
      
    }
function handleClick(cb,row) {
  if(cb.checked){
    $("#receiveIsVat" + row).val("1");  
    $("#receiveVat" + row).val($("#vatValue").val());
    document.getElementById('receiveVat'+row).style.display = 'block';
    document.getElementById('receiveVat'+row).innerHTML = $("#vatValue").val();
  }else{
    $("#receiveIsVat" + row).val("0"); 
    $("#receiveVat" + row).val("");
    document.getElementById('receiveVat'+row).style.display = 'none';
  }
}

function calculatGross(row) {
//  Total Amount Refund  vat = vat * Total Amount Refund / 100
    var receiveAmount = replaceAll(",","",$("#receiveAmount"+row).val()); 
    if (receiveAmount == ""){
        receiveAmount = 0;
    }
    
    var receiveVat = replaceAll(",","",$("#receiveVat"+row).val());
    if (receiveVat == ""){
        receiveVat = 0;
    }
    
    var amount = parseFloat(receiveAmount); 
    var vat = parseFloat(receiveVat);
    var beforevat = parseFloat(100/(100+vat)).toFixed(2);
    var receiveGross = amount * beforevat;
    document.getElementById("receiveGross"+row).value = formatNumber(receiveGross);
}

function replaceAll(find, replace, str) {
  return str.replace(new RegExp(find, 'g'), replace);
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g,"$1,")
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
        }else{
            nField.value = tmp.replace(/ /,"");
        } 
    }else{
        nField.value = nField.value.replace(/[^\d\,\.]/g,"").replace(/ /,"");
    }
}

function setFormatCurrency(row){  
    var receiveAmount = replaceAll(",","",$('#receiveAmount'+row).val()); 
    if (receiveAmount == ""){
        receiveAmount = 0;
    }
    receiveAmount = parseFloat(receiveAmount); 
    document.getElementById("receiveAmount"+row).value = formatNumber(receiveAmount);
    
    var receiveCost = replaceAll(",","",$('#receiveCost'+row).val()); 
    if (receiveCost == ""){
        receiveCost = 0;
    }
    receiveCost = parseFloat(receiveCost); 
    document.getElementById("receiveCost"+row).value = formatNumber(receiveCost);

    if (receiveAmount == "" || receiveAmount == 0){
        document.getElementById("receiveAmount"+row).value = "";
    }
    
    if (receiveCost == "" || receiveCost == 0){
        document.getElementById("receiveCost"+row).value = "";
    }
}

function AddRowCredit(row) {
        $("#CreditDetailTable tbody").append(
            '<tr style="higth 100px">' +
            '<input id="tableCreditId' + row + '" name="tableCreditId' + row + '"  type="hidden" >' +
            '<td>' + 
            '<select class="form-control" name="creditBank' + row + '" id="creditBank' + row + '" ><option value="">---------</option></select>' +                          
            '</td>' +
            '<td><input maxlength="20" id="creditNo' + row + '" name="creditNo' + row + '" type="text" class="form-control" ></td>' +
            '<td><div class="input-group date"><input id="creditExpired'+row+'" name="creditExpired'+row+'"  type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value=""><span class="input-group-addon spandate" style="padding : 1px 10px;"><span class="glyphicon glyphicon-calendar"></span></span></div></td>' +
            '<td><input id="creditAmount' + row + '" name="creditAmount' + row + '" type="text" class="form-control text-right" onkeyup="insertCommas(this)"></td>' +
            '<td class="text-center">' +
            '<a class="remCF" onclick="deleteCreditList(\'\', \''+row+'\')">  '+
            '<span id="SpanRemove' + row + '"class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
            '</tr>'
        );
        $("#creditBankList option").clone().appendTo("#creditBank" + row);
                    
        $("#creditAmount"+row).focusout(function(){
            var creditAmount = replaceAll(",","",$('#creditAmount'+row).val()); 
            if (creditAmount == ""){
                creditAmount = 0;
            }
            creditAmount = parseFloat(creditAmount); 
            document.getElementById("creditAmount"+row).value = formatNumber(creditAmount);
            
            if (creditAmount == "" || creditAmount == 0){
                document.getElementById("creditAmount"+row).value = "";
            }
        }); 
        
        var tempCount = parseInt($("#countRowCredit").val()) + 1;
        $("#countRowCredit").val(tempCount);
        reloadDatePicker();
} 

function reloadDatePicker(){
    try{
       $(".date").datetimepicker({
            pickTime: false   
       });  
       $('span').click(function() {
            var position = $(this).offset();
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
       });
    }catch(e){

    }  
}

function clearNew(){
    $("#receiveId").val("");
    $("#receiveNo").val("");
    $("#inputDate").val("");
    $("#receiveFromId").val("");
    $("#receiveFromCode").val("");
    $("#receiveFromName").val("");
    $("#receiveFromAddress").val("");
    $("#remark").val("");
    $("#receiveFromDate").val("");
    $("#inputStatus").val("");
    $("#arCode").val("");
    $("#grandTotal").val("");
    $("#withTax").val("");
    $("#cashAmount").val("");
    $("#cashMAmount").val("");
    $("#bankTransfer").val("");
    $("#chqBank").val("");
    $("#chqNo").val("");
    $("#chqDate").val("");
    $("#chqAmount").val("");
    $("#chqBank2").val("");
    $("#chqNo2").val("");
    $("#chqDate2").val("");
    $("#chqAmount2").val("");

    $('#ReceiptListTable').dataTable().fnClearTable();
    $('#ReceiptListTable').dataTable().fnDestroy();
    $("#ReceiptListTable tbody").empty();

    $('#CreditDetailTable').dataTable().fnClearTable();
    $('#CreditDetailTable').dataTable().fnDestroy();
    $("#CreditDetailTable tbody").empty();
    AddRowProduct(0);
    AddRowCredit(0);
}

function searchInvoice() {
    var invoiceNo = $("#invoiceNo").val();
    var department = "${typeDepartment}";
    var invType = "${typeReceipt}";
    var invoicenopanel = $("#invoicenopanel").val();
    if(invoiceNo == ""){
        if(!$('#invoicenopanel').hasClass('has-feedback')) {
            $('#invoicenopanel').addClass('has-feedback');
        }
        $('#invoicenopanel').removeClass('has-success');
        $('#invoicenopanel').addClass('has-error');
    }
    else{
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

function CallAjaxSearchInvoice(param) {
    var url = 'AJAXServlet';
    $("#ajaxload1").removeClass("hidden");
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
                    }else{
                        $('#InvoiceListTable').dataTable().fnClearTable();
                        $('#InvoiceListTable').dataTable().fnDestroy();
                        $("#InvoiceListTable tbody").append(msg);
                        
                        document.getElementById("receiveFromCode").value = $("#receiveFromInvoice").val();
                        document.getElementById("receiveFromName").value = $("#receiveNameInvoice").val();
                        document.getElementById("receiveFromAddress").value = $("#receiveAddressInvoice").val();
                        document.getElementById("arCode").value = $("#arcodeInvoice").val();
                    }
                    $("#ajaxload1").addClass("hidden");
                     
                } catch (e) {
                    alert(e);
                }

            }, error: function (msg) {
                 $("#ajaxload1").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}   

function invoicenoValidate(){
    $('#invoicenopanel').removeClass('has-feedback');
    $('#invoicenopanel').addClass('has-success');
    $('#invoicenopanel').removeClass('has-error');  
}
function addProduct(product,description,cost,cur,isVat,vat,amount,currency,invId,billDescId,paymentId,airlineCode,checkadd){
    if(checkadd == 1){
        $("#ButtonSearchRefNo").attr("disabled", "disabled");
        $("#searchPaymentNoAir").attr("disabled", "disabled");
        $("#searchPaymentNoTour").attr("disabled", "disabled");
    }else if(checkadd == 2){
        $("#ButtonSearchInvoice").attr("disabled", "disabled");
        $("#searchPaymentNoAir").attr("disabled", "disabled");
        $("#searchPaymentNoTour").attr("disabled", "disabled");
    }else if(checkadd == 3){
        $("#ButtonSearchRefNo").attr("disabled", "disabled");
        $("#ButtonSearchInvoice").attr("disabled", "disabled");
        $("#searchPaymentNoTour").attr("disabled", "disabled");
    }
    var tempCount = parseInt($("#counter").val());
    AddDataRowProduct(tempCount,product,description,cost,cur,isVat,vat,amount,currency,invId,billDescId,paymentId,airlineCode);
}
function AddDataRowProduct(row,product,description,cost,cur,isVat,vat,amount,currency,invId,billDescId,paymentId,airlineCode) {
    var rowAll = $("#ReceiptListTable tr").length;
    var tempCount = parseInt(rowAll-2);
//    alert(rowAll + "___" + tempCount + " row :: "+row);
    for(var i =0; i<rowAll ;i++){
        if($("#receiveProduct"+i).val() != "" 
            || $("#receiveDes"+i).val() != "" 
            || $("#receiveCost"+i).val() != "" 
            || $("#receiveCurCost"+i).val() != "" 
            || $("#receiveVat"+i).val() != ""
            || $("#receiveAmount"+i).val() != "" 
            || $("#receiveCurrency"+i).val() != ""
            ){
        
        }else{
            $("#receiveProduct" +i).parent().parent().remove();
            row = parseInt(i);
            $("#counter").val(row);
        }
    }

    $("#ReceiptListTable tbody").append(
        '<tr style="higth 100px">' +
        '<input id="invId' + row + '" name="invId' + row + '"  type="hidden" value="'+invId+'" >' +
        '<input id="tableId' + row + '" name="tableId' + row + '"  type="hidden" >' +
        '<input id="billDescId' + row + '" name="billDescId' + row + '"  type="hidden" value="'+billDescId+'" >' +
        '<input id="paymentId' + row + '" name="paymentId' + row + '"  type="hidden" value="'+paymentId+'" >' +
        '<input id="airlineCode' + row + '" name="airlineCode' + row + '"  type="hidden" value="'+airlineCode+'" >' +
        '<input id="receiveAmountTemp' + row + '" name="receiveAmountTemp' + row + '"  type="hidden" value="'+amount+'" >' +
        '<td>' + 
        '<select class="form-control" name="receiveProduct' + row + '" id="receiveProduct' + row + '" ><option value="'+product+'" selected></option></select>' +                          
        '</td>' +
        '<td><input maxlength="255" id="receiveDes' + row + '" name="receiveDes' + row + '" type="text" class="form-control" value="'+description+'"></td>' +
        '<td><input maxlength="10" id="receiveCost' + row + '" name="receiveCost' + row + '" type="text" class="form-control" value="'+cost+'" onkeyup="insertCommas(this)" disabled="disabled"></td>' +
        '<td>' + 
        '<select class="form-control" name="receiveCurCost' + row + '" id="receiveCurCost' + row + '" disabled="disabled"><option value="'+cur+'" ></option></select>' +                          
        '</td>' +
        '<td align="center">' +
        '<input type="checkbox" name="receiveIsVat' + row + '" id="receiveIsVat' + row + '" disabled="disabled" onclick="handleClick(this,'+row+')" value="'+isVat+'">' +
        '</td>' +
        '<td><div id="receiveVat' + row + '" style="display:none" ></div></td>' +
        '<td><input id="receiveAmount' + row + '" name="receiveAmount' + row + '" type="text" class="form-control text-right" onkeyup="insertCommas(this)" onkeypress="checkAmount('+row+')" value="'+amount+'"></td>' +
        '<td>' + 
        '<select class="form-control" name="receiveCurrency' + row + '" id="receiveCurrency' + row + '" ><option value="'+currency+'"></option></select>' +                           
        '</td>' +
        '<td class="text-center">' +
        '<a class="remCF" onclick="deleteReceiptList(\'\', \''+row+'\')">  '+
        '<span id="SpanRemove' + row + '"class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
        '</tr>'
    );
    $("#billTypeList option").clone().appendTo("#receiveProduct" + row);
    $("#currencyList option").clone().appendTo("#receiveCurrency" + row);
    $("#currencyList option").clone().appendTo("#receiveCurCost" + row);
    var isvat = $('#receiveIsVat'+row).val();
    if (isvat === '1')
    {
        $('#receiveIsVat'+row).prop('checked', true);
        document.getElementById('receiveVat'+row).style.display = 'block';
        document.getElementById('receiveVat'+row).innerHTML = $("#vatValue").val();
    }
    if (isvat === '0')
    {
        $('#receiveVat'+row).val("");
    }
    $('[name=receiveProduct'+row+'] option').filter(function() { 
        return ($(this).val() === product);
    }).prop('selected', true);
    
    $('[name=receiveCurCost'+row+'] option').filter(function() { 
        return ($(this).val() === cur);
    }).prop('selected', true);
    
    $('[name=receiveCurrency'+row+'] option').filter(function() { 
        return ($(this).val() === currency);
    }).prop('selected', true); 
                
    $("#receiveAmount"+row).focusout(function(){
//        calculatGross(row);
        setFormatCurrency(row);
    }); 
    $("#receiveCost"+row).focusout(function(){
        setFormatCurrency(row);
    }); 
    var tempCount = parseInt($("#counter").val()) + 1;
    $("#counter").val(tempCount);
    AddRowProduct(tempCount);
}

function searchReceiveNo(){
    var action = document.getElementById('action');
    action.value = 'searchReceiveNo';
    var receiveNo = document.getElementById('receiveNo');
    receiveNo.value = $("#receiveNo").val();
    var type = document.getElementById('type');
    type.value = $("#type").val();
    document.getElementById('ReceiptForm').submit();
}

function saveReceipt(){
    var action = document.getElementById('action');
    action.value = 'saveReceipt';
    var counter = document.getElementById('counter');
    counter.value = $("#ReceiptListTable tr").length;
    var countRowCredit = document.getElementById('countRowCredit');
    countRowCredit.value = $("#CreditDetailTable tr").length;
}

function searchRefNo() {
    var refNo = $("#refNo").val();
    if(refNo == ""){
        if(!$('#refnopanel').hasClass('has-feedback')) {
            $('#refnopanel').addClass('has-feedback');
        }
        $('#refnopanel').removeClass('has-success');
        $('#refnopanel').addClass('has-error');
    }
    else{
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

function CallAjaxSearchRef(param) {
    var url = 'AJAXServlet';
    $("#ajaxload2").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                try { 
                    if(msg == "null"){
                        $('#RefNoListTable').dataTable().fnClearTable();
                        $('#RefNoListTable').dataTable().fnDestroy();
                    }else{
                        $('#RefNoListTable').dataTable().fnClearTable();
                        $('#RefNoListTable').dataTable().fnDestroy();
                        $("#RefNoListTable tbody").append(msg);
                        
                        document.getElementById("receiveFromCode").value = $("#receiveFromBillable").val();
                        document.getElementById("receiveFromName").value = $("#receiveNameBillable").val();
                        document.getElementById("receiveFromAddress").value = $("#receiveAddressBillable").val();
                        document.getElementById("arCode").value = $("#arcodeBillable").val();
                        document.getElementById("inputStatus").value = $("#mAccPayBillable").val();
                    }
                    $("#ajaxload2").addClass("hidden");
                     
                } catch (e) {
                    alert(e);
                }

            }, error: function (msg) {
                 $("#ajaxload2").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

function refnoValidate(){
    $('#refnopanel').removeClass('has-feedback');
    $('#refnopanel').addClass('has-success');
    $('#refnopanel').removeClass('has-error');  
}

function searchPaymentNoAir() {
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

function CallAjaxSearchPaymentNoAir(param) {
    var url = 'AJAXServlet';
    $("#ajaxload3").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                try { 
                    if(msg == "null"){
                        $('#AircommissionTable').dataTable().fnClearTable();
                        $('#AircommissionTable').dataTable().fnDestroy();
                        $('#AircommissionTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 5
                        });
                        $("#AircommissionTable_wrapper").css("min-height",100);
                    }else{
                        $('#AircommissionTable').dataTable().fnClearTable();
                        $('#AircommissionTable').dataTable().fnDestroy();
                        $("#AircommissionTable tbody").empty().append(msg);
                        $('#AircommissionTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 5
                        });
                        $("#AircommissionTable_wrapper").css("min-height",100);
                    }
                    $("#ajaxload3").addClass("hidden");
                     
                } catch (e) {
                    alert(e);
                }

            }, error: function (msg) {
                 $("#ajaxload3").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}
function searchPaymentNoTour() {
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

function CallAjaxSearchPaymentNoTour(param) {
    var url = 'AJAXServlet';
    $("#ajaxload4").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                try { 
                    if(msg == "null"){
                        $('#TourcommissionTable').dataTable().fnClearTable();
                        $('#TourcommissionTable').dataTable().fnDestroy();
                        $('#TourcommissionTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 5
                        });
                        $("#TourcommissionTable_wrapper").css("min-height",100);
                    }else{
                        $('#TourcommissionTable').dataTable().fnClearTable();
                        $('#TourcommissionTable').dataTable().fnDestroy();
                        $("#TourcommissionTable tbody").empty().append(msg);
                        $('#TourcommissionTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 5
                        });
                        $("#TourcommissionTable_wrapper").css("min-height",100);
                    }
                    $("#ajaxload4").addClass("hidden");
                     
                } catch (e) {
                    alert(e);
                }

            }, error: function (msg) {
                 $("#ajaxload4").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

function deleteReceiptList(id,Ccount) {
    document.getElementById('receiptDetailIdDelete').value = id;
    document.getElementById('receiptRowDelete').value = Ccount;
    $("#delProduct").text('Are you sure delete this product ?');
    $('#DeleteProduct').modal('show');
}

function DeleteRowProduct(){
    var cCount = document.getElementById('receiptRowDelete').value;
    var id = document.getElementById('receiptDetailIdDelete').value;
    if(id === ''){
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
            success: function () {
                $("#receiveProduct" + cCount).parent().parent().remove();
                var rowAll = $("#ReceiptListTable tr").length;
                if (rowAll <= 1) {
                    $("#tr_ProductDetailAddRow").removeClass("hide");
                    $("#tr_ProductDetailAddRow").addClass("show");
                }
                
                AddRowProduct()
            },
            error: function () {
                console.log("error");
                result =0;
            }
        }); 
    }    
    $('#DeleteProduct').modal('hide');
    
    var tempcount = parseInt($("#ReceiptListTable tr").length);
    if(tempcount == 1){
        $("#ButtonSearchRefNo").removeAttr("disabled");
        $("#ButtonSearchInvoice").removeAttr("disabled");
        $("#searchPaymentNoAir").removeAttr("disabled");
        $("#searchPaymentNoTour").removeAttr("disabled");
    }else if(tempcount == 2){
        $('#ReceiptListTable').dataTable().fnClearTable();
        $('#ReceiptListTable').dataTable().fnDestroy();
        $("#counter").val(0);
        AddRowProduct(0);
        var amount = document.getElementById('receiveAmount0').value;
        if(amount === ""){
            $("#ButtonSearchRefNo").removeAttr("disabled");
            $("#ButtonSearchInvoice").removeAttr("disabled");
            $("#searchPaymentNoAir").removeAttr("disabled");
            $("#searchPaymentNoTour").removeAttr("disabled");
        }
    }

}

function deleteCreditList(id,Ccount) {
    document.getElementById('receiptCreditIdDelete').value = id;
    document.getElementById('receiptCreditRowDelete').value = Ccount;
    $("#delCredit").text('Are you sure delete this credit ?');
    $('#DeleteReceiptCredit').modal('show');
}

function DeleteRowCredit(){
    var cCount = document.getElementById('receiptCreditRowDelete').value;
    var id = document.getElementById('receiptCreditIdDelete').value;
    if(id === ''){
        $("#creditBank" + cCount).parent().parent().remove();
        var rowAll = $("#CreditDetailTable tr").length;
        if (rowAll <= 1) {
            $("#tr_CreditDetailAddRow").removeClass("hide");
            $("#tr_CreditDetailAddRow").addClass("show");
        }
    } 
    else {
        $.ajax({
            url: '${callPage}?action=deleteReceiptCredit',
            type: 'get',
            data: {receiptCreditIdDelete: id},
            success: function () {
                $("#creditBank" + cCount).parent().parent().remove();
                var rowAll = $("#CreditDetailTable tr").length;
                if (rowAll <= 1) {
                    $("#tr_CreditDetailAddRow").removeClass("hide");
                    $("#tr_CreditDetailAddRow").addClass("show");
                }
            },
            error: function () {
                console.log("error");
                result =0;
            }
        }); 
    }    
    $('#DeleteReceiptCredit').modal('hide');
}

function DisableVoidReceipt(){
    var receiveNo = document.getElementById('receiveNo');
    document.getElementById('disableVoid').innerHTML = "Are you sure to delete booking other : " + receiveNo.value + " ?";
}

function EnableVoidReceipt(){
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

function checkAmount(row){
    var amountTemp = document.getElementById('receiveAmountTemp'+row).value;
    var amount = document.getElementById('receiveAmount'+row).value;
    
    amountTemp = replaceAll(",","",amountTemp.toString()); 
    amount = replaceAll(",","",amount.toString()); 
    
    $("#receiveAmount"+row).focusout(function(){
        if(parseInt(amount) > parseInt(amountTemp)){
            document.getElementById('receiveAmount'+row).value = amountTemp; 
        }
    }); 
}
</script>
