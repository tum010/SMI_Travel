<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/PaymentAirline.js"></script> -->
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="airlineList" value="${requestScope['airlineList']}" />
<c:set var="payByList" value="${requestScope['payByList']}" />
<c:set var="invoiceSupList" value="${requestScope['invoiceSupList']}" />
<c:set var="paymentAirticket" value="${requestScope['paymentAirticket']}" />
<c:set var="SelectedInvoice" value="${requestScope['SelectedInvoice']}" />
<c:set var="vat" value="${requestScope['vat']}" />
<c:set var="ticketFareList" value="${requestScope['ticketFareList']}" />
<c:set var="paymentAirFare" value="${requestScope['paymentAirFare']}" /> 
<c:set var="paymentAirRefund" value="${requestScope['paymentAirRefund']}" />
<c:set var="flagSearch" value="${requestScope['flagSearch']}" /> 
<c:set var="addRefundList" value="${requestScope['addRefundList']}" />
<section class="content-header" >
    <h1>
        Checking - Air Ticket
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Checking</a></li>          
        <li class="active"><a href="#">Payment Airline</a></li>
    </ol>
</section>

<div style="padding-top: 15px;padding-right: 0px "ng-app=""> 
    <div class="row">
        <!--Alert Save and Update-->
        <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Success!</strong> 
        </div>
        <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Unsuccess!</strong> 
        </div>
        <div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Delete Success!</strong> 
        </div>
        <div id="textAlertDivNotDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Delete Unsuccess!</strong> 
        </div>       
        
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Checking/CheckingAirTicketMenu.html'"></div>
        </div>
        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6" style="padding-right: 15px">
                    <h4><b>Payment Airline</b></h4>
                </div>
            </div>
            <hr/>
            
            <form action="PaymentAirline.smi" method="post" id="PaymentAirlineForm" name="PaymentAirlineForm" role="form">
                <input id="paymentAirFareId" name="paymentAirFareId" type="hidden" class="form-control" maxlength="11" value="${paymentAirFare.id}">
                <input id="paymentAirRefundId" name="paymentAirRefundId" type="hidden" class="form-control" maxlength="11" value="${paymentAirRefund.id}">
                <input type="hidden" name="action" id="action" value="">
                <input type="hidden" name="vat" id="vat" value="">
                <input type="hidden" name="flagSearch" id="flagSearch" value="${flagSearch}">
                <div class="panel panel-default">
                    <div class="panel-body"  style="padding-right: 0px;" style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Payment No </label>
                            </div>
                            <div class="col-xs-1" style="width:180px" id="paymentnopanel">
                                <div class='input-group' id='paymentnumber'>
                                    <input id="paymentId" name="paymentId" type="hidden" class="form-control" maxlength="11" value="${paymentAirticket.id}">
                                    <input id="paymentNo" name="paymentNo" type="text" style="width: 180px" class="form-control" value="${requestScope['payNo']}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 8px"></div>
                            <div class="col-xs-1 text-right" style="width: 80px">
                                <button style="height:34px" type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="searchPaymentNo();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search</button>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 140px">
                                <label class="control-label text-right">Payment Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 170px">
                                <div class='input-group date'>
                                    <input id="paymentDate" name="paymentDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['payDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 165px">
                                <label class="control-label text-right">Due Payment Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 170px">
                                <div class='input-group date'>
                                    <input id="duePaymentDate" name="duePaymentDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['dueDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Invoice Sup<font style="color: red">*</font></label>
                            </div>
                            <div class="col-xs-1"  style="width: 155px">
                                <div class="input-group">
                                    <input type="hidden" class="form-control" id="invoiceSupId" name="invoiceSupId" value="${SelectedInvoice.id}"/>
                                    <input type="text" class="form-control" id="invoiceSupCode" name="invoiceSupCode" value="${SelectedInvoice.code}" style="text-transform:uppercase"/>
                                    <span class="input-group-addon" id="invoiceSup_modal"  data-toggle="modal" data-target="#InvoiceSupModal">
                                        <span class="glyphicon-search glyphicon"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-left" style="width: 160px">
                                <input type="text" class="form-control" id="invoiceSupName" name="invoiceSupName" value="${SelectedInvoice.name}" readonly=""
                                               data-bv-notempty="true" data-bv-notempty-message="agent empty !">                           
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 115px">
                                <label class="control-label text-right">A/P Code<font style="color: red">*</font></label>
                            </div>
                            <div class="col-xs-1"  style="width: 170px">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="apCode" name="apCode" value="${SelectedInvoice.apcode}" />
<!--                                    <span class="input-group-addon" id="ap_modal"  data-toggle="modal" data-target="#ApModal">
                                        <span class="glyphicon-search glyphicon"></span>
                                    </span>-->
                                </div>
                            </div>
                        </div> 
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Detail </label>
                            </div>
                            <div class="col-xs-1"  style="width: 310px">
                                <div class="input-group">                                    
                                    <textarea rows="3" class="form-control" id="detail" name="detail" style="width: 175%">${paymentAirticket.detail}</textarea>  
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 120px">
                                <label class="control-label text-right">Pay By </label>
                            </div>
                            <div class="col-xs-1" style="width: 170px">
                                <select name="payBy" id="payBy" class="form-control">
                                    <option value="">--- Pay By ---</option> 
                                    <c:forEach var="table" items="${payByList}" >
                                        <c:set var="select" value="" />
                                        <c:set var="selectedId" value="${paymentAirticket.MAccpay.id}" />
                                        <c:if test="${table.id == selectedId}">
                                            <c:set var="select" value="selected" />
                                        </c:if>
                                        <option value="${table.id}" ${select}>${table.name}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div> 

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">Ticket Fare</h4>
                    </div> 
                    <div class="panel-body" style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 128px">
                                <label class="control-label text-right">Ticket From </label>
                            </div>
                            <div class="col-xs-1" style="width: 300px">
                                <select name="ticketFrom" id="ticketFrom" class="form-control">
                                    <option value="">--- Ticket From ---</option> 
                                     <c:choose>
                                        <c:when test="${requestScope['TicketForm'] == 'C'}">
                                            <c:set var="selectedC" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="C" ${selectedC}>IN</option>
                                    <c:choose>
                                        <c:when test="${requestScope['TicketForm'] == 'O'}">
                                            <c:set var="selectedO" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="O" ${selectedO}>OUT</option>
                                </select>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 122px">
                                <label class="control-label text-right">Type Airline </label>
                            </div>
                            <div class="col-xs-1" style="width: 300px">
                                <select name="typeAirline" id="typeAirline" class="form-control">
                                    <option value="">--- Airline ---</option> 
                                    <c:forEach var="table" items="${airlineList}" >
                                        <c:set var="select" value="" />
                                        <c:set var="selectedId" value="${requestScope['TypeAirline']}" />
                                        <c:if test="${table.id == selectedId}">
                                            <c:set var="select" value="selected" />
                                        </c:if>
                                        <option value="${table.id}" ${select}>${table.code}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i></div>
                            <div class="col-xs-1 text-right" style="width: 80px">
                                <button style="height:34px" type="button"  id="ButtonSearch"  name="ButtonSearch" onclick="searchTicketFare();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search</button>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 128px">
                                <label class="control-label text-right">Form </label>
                            </div>
                            <div class="col-xs-1" style="width: 170px">
                                <div class='input-group date'>
                                    <input id="dateFrom" name="dateFrom"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['dateFrom']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 130px">
                            </div>
                            <div class="col-xs-1 text-right" style="width: 122px">
                                <label class="control-label text-right">To </label>
                            </div>
                            <div class="col-xs-1" style="width: 170px">
                                <div class='input-group date'>
                                    <input id="dateTo" name="dateTo"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['dateTo']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <table class="display" id="TicketFareTable">
                            <thead class="datatable-header">
                                <tr>
                                    <!--<th style="width:5%;">Id</th>-->
                                    <th style="width:5%;">Ref No</th>
                                    <th style="width:15%;">Ticket No</th>
                                    <th style="width:10%;">Department</th>
                                    <th style="width:20%;">Fare</th>
                                    <th style="width:10%;">Tax</th>
                                    <th style="width:10%;">Insurance</th>
                                    <th style="width:10%;">Commission</th>
                                    <th style="width:10%;">Amount</th>
                                    <th style="width:10%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="table" items="${ticketFareList}" varStatus="dataStatus">
                                    <tr>
                                        <input type="hidden" name="count${dataStatus.count}" id="count${dataStatus.count}" value="${dataStatus.count}">
                                        <input type="hidden" name="tableId${dataStatus.count}" id="tableId${dataStatus.count}" value="${table.id}">
                                        <td align="center"> <c:out value="${table.referenceNo}" /></td>
                                        <td align="left"> <c:out value="${table.ticketNo}" /></td>
                                        <td align="left"> <c:out value="${table.department}" /></td>
                                        <td class="money">${table.fare}</td>
                                        <td class="money">${table.tax}</td>
                                        <td class="money">${table.ticketIns}</td>
                                        <td class="money">${table.ticketCommission}</td>
                                        <td class="money">${table.salePrice}</td>
                                        <td> 
                                            <center> 
                                                <a class="remCF"><span id="SpanRemove${dataStatus.count}" onclick="deleteTicket('${table.id}','${table.ticketNo}','${dataStatus.count}');" class="glyphicon glyphicon-remove deleteicon "></span></a>
                                            </center>
                                            <input type="hidden" name="deleteTicketNo" id="deleteTicketNo">
                                            <input type="hidden" name="deleteTicketId" id="deleteTicketId">
                                            <input type="hidden" name="deleteTicketCount" id="deleteTicketCount">
                                        </td>                                    
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <input type="hidden" class="form-control" id="counter" name="counter" value="${requestScope['ticketFareCount']}" />
                        
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 400px">
                                <label class="control-label text-right">Total Commission</label>
                            </div>
                            <div class="col-xs-1"  style="width: 170px">
                                <div class="input-group">                                    
                                    <input type="text" class="form-control money" id="totalCommissionTicketFare" name="totalCommissionTicketFare" readonly="" value="" />
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 250px">
                                <label class="control-label text-right">Total Amount</label>
                            </div>
                            <div class="col-xs-1"  style="width: 170px">
                                <div class="input-group">                                    
                                    <input type="text" class="form-control money" id="totalAmountTicketFare" name="totalAmountTicketFare" readonly="" value="" />
                                </div>
                            </div>
                        </div>            
                    </div>
                </div>
                            
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">Refund Ticket</h4>
                    </div> 
                    <div class="panel-body" style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 128px">
                                <label class="control-label text-right">Refund </label>
                            </div>
                            <div class="col-xs-1" style="width: 280px">
                                <div class='input-group'>
                                    <input id="refundNo" name="refundNo" type="text" style="width: 260px" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i></div>
                            <div class="col-xs-1 text-right" style="width: 100px">
                                <button style="height:30px" type="button"  id="ButtonAdd"  name="ButtonAdd" onclick="addAction();" class="btn btn-primary btn-sm">&nbsp;&nbsp;Add&nbsp;&nbsp;</button>
                            </div>
                        </div>
                        <table class="display" id="RefundTicketTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:5%;">Refund</th>
                                    <th style="width:15%;">Ticket No</th>
                                    <th style="width:10%;">Department</th>
                                    <th style="width:10%;">Route</th>
                                    <th style="width:15%;">Commission</th>
                                    <th style="width:15%;">Amount</th>
                                    <th style="width:10%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="table" items="${addRefundList}" varStatus="dataStatus">
                                    <tr>
                                        <input type="hidden" name="count${dataStatus.count}" id="count${dataStatus.count}" value="${dataStatus.count}">
                                        <input type="hidden" name="tableRefundId${dataStatus.count}" id="tableRefundId${dataStatus.count}" value="${table.id}">
                                        <input type="hidden" name="refundNoRow${dataStatus.count}" id="refundNoRow${dataStatus.count}" value="${table.refundNo}">
                                        <td align="center"> <c:out value="${table.refundNo}" /></td>
                                        <td align="left"> <c:out value="${table.ticketNo}" /></td>
                                        <td align="left"> <c:out value="${table.department}" /></td>
                                        <td align="center"> <c:out value="${table.route}" /></td>
                                        <td align="right" class="moneyformat">${table.commisssion}</td>
                                        <td class="money">${table.amount}</td>
                                        <td> 
                                            <center> 
                                                <a class="remCF"><span id="SpanRemove${dataStatus.count}" onclick="deleteRefund('${table.id}','${table.refundNo}','${dataStatus.count}');" class="glyphicon glyphicon-remove deleteicon "></span></a>
                                            </center>
                                        </td>                                    
                                    </tr>
                                </c:forEach>
                                <input type="hidden" id="delRefundNo" name="delRefundNo" >
                                <input type="hidden" id="delRefundId" name="delRefundId" >
                                <input type="hidden" id="deleteRefundCount" name="deleteRefundCount"/>
                                <input type="hidden" id="rowRefundCount" name="rowRefundCount"/>
                                <input type="hidden" name="tableRefundId" id="tableRefundId" value="">
                            </tbody>
                        </table>
                        <div class="col-xs-12 form-group" style="padding-top: 15px">
                            <div class="col-xs-1 text-right"  style="width: 400px">
                                <label class="control-label text-right">Total Amount Refund</label>
                            </div>
                            <div class="col-xs-1"  style="width: 170px">
                                <div class="input-group">                                    
                                    <input type="text" class="form-control money" id="totalAmountRefund" name="totalAmountRefund" readonly="" value="" />
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 250px">
                                <label class="control-label text-right">Total Amount Refund Vat</label>
                            </div>
                            <div class="col-xs-1"  style="width: 170px">
                                <div class="input-group">                                    
                                    <input type="text" class="form-control money" id="totalAmountRefundVat" name="totalAmountRefundVat" readonly="" value="" />
                                </div>
                            </div>
                        </div>   
                    </div>
                </div>
                            
                <div class="panel panel-default">
<!--                    <div class="panel-heading">
                        <h4 class="panel-title">Ticket Detail</h4>
                    </div> -->
                    <div class="panel-body" style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Agent Amount </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentAmount" name="agentAmount" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" value="${paymentAirticket.agentAmount}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Credit Note </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="creditNote" name="creditNote" type="text" class="form-control" value="${paymentAirticket.creditNote}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 140px">
                                <label class="control-label text-right">Credit Amount </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="creditAmount" name="creditAmount" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" value="${paymentAirticket.creditAmount}">
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Commission Vat </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="commissionVat" name="commissionVat" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Debit Note </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="debitNote" name="debitNote" type="text" class="form-control" value="${paymentAirticket.debitNote}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 140px">
                                <label class="control-label text-right">Debit Amount </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="debitAmount" name="debitAmount" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" value="${paymentAirticket.debitAmount}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Cash </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="cash" name="cash" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" value="${paymentAirticket.cash}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Withholding Tax </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="withholdingTax" name="withholdingTax" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" value="${paymentAirticket.witholdingTax}" >
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Chq No </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="chqNo" name="chqNo" type="text" class="form-control" value="${paymentAirticket.chqNo}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Amount </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="amount" name="amount" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" value="${paymentAirticket.chqAmount}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 140px">
                                <label class="control-label text-right">Total Payment </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="totalPayment" name="totalPayment" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" readonly="" value="${paymentAirticket.totalAmount}">
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-12 text-right" >
                            <button type="submit" id="ButtonSave" name="ButtonSave" onclick="saveAction()" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                            <button type="submit" id="ButtonSaveAndNew" name="ButtonSaveAndNew" class="btn btn-success"><i class="fa fa-save"></i> Save & New</button>
                        </div>
                    </div>
                </div>           
            </form>               
        </div>
    </div>
                                               
<!--Modal  Agent-->
<div class="modal fade" id="InvoiceSupModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Invoice Sup</h4>
            </div>
            <div class="modal-body">
                 <table class="display" id="InvoiceSupTable">
                    <thead class="datatable-header">                     
                        <tr>
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden">AP Code</th>
                        </tr>
                    </thead>
                    <tbody>
                        <script>
                            invoiceSup = [];
                        </script>
                        <c:forEach var="table" items="${invoiceSupList}">
                            <tr onclick ="setupInvSupValue()">
                                <td class="invoice-id hidden">${table.id}</td>
                                <td class="invoice-code">${table.code}</td>
                                <td class="invoice-name">${table.name}</td>
                                <td class="invoice-apcode hidden">${table.apcode}</td>
                            </tr>
                            <script>
                                invoiceSup.push({id: "${table.id}", code: "${table.code}", name: "${table.name}", apcode: "${table.apcode}"});
                            </script>
                        </c:forEach>
                        

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="InvoiceSupClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--Modal  A/P Code-->
<div class="modal fade" id="ApModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">A/P Code</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="ApCodeTable">
                    <thead class="datatable-header">                     
                        <tr>
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                            <th class="hidden">Fax</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="ApModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--DELETE MODAL-->
<div class="modal fade" id="DelRefund" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Delete Refund</h4>
            </div>
            <div class="modal-body" id="delRefundAlert">

            </div>
            <div class="modal-footer">
                <button type="submit" onclick="DeleteRowRefund()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>               
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--DELETE MODAL-->
<div class="modal fade" id="DeleteTicket" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Delete Ticket Fare</h4>
            </div>
            <div class="modal-body" id="delTicket">

            </div>
            <div class="modal-footer">
                <button type="submit" onclick="DeleteRowTicket()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>               
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--ConfirmSearchTicket MODAL-->
<div class="modal fade" id="ConfirmSearchTicket" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Search Ticket Fare</h4>
            </div>
            <div class="modal-body" id="searchTicketFare">

            </div>
            <div class="modal-footer">
                <button type="submit" onclick="searchTicketFareCF()" class="btn btn-danger">Search</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>               
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--Add Refund MODAL-->
<div class="modal fade" id="AddRefundNoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Add Refund No</h4>
            </div>
            <div class="modal-body" id="addRefundNoAlert">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="addRefundNo()" class="btn btn-danger">Add</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>               
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
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
<c:if test="${! empty requestScope['setCalculateTicket']}">
    <c:if test="${requestScope['setCalculateTicket'] == 1 }">        
        <script language="javascript">
            $(document).ready(function() {
                $("#vat").val(${vat});
                calculateTotalAmount();
                calculateTotalCommission();
                calculateTotalPayment();
                calculateAmount();
            });
        </script>
    </c:if>
</c:if>        
<c:if test="${! empty requestScope['setCalculateRefund']}">
    <c:if test="${requestScope['setCalculateRefund'] == 1 }">        
        <script language="javascript">
            $(document).ready(function() {
                $("#vat").val(${vat});
                calculateTotalAmountRefund();
                calculateTotalRefundVat();
                calculateTotalPayment();
                calculateAmount();
            });
        </script>
    </c:if>
</c:if>             
        
<!--Script-->       
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $('.date').datetimepicker();
        $(".moneyformat").mask('000,000,000', {reverse: true});
        $(".money").mask('000,000,000.00', {reverse: true});
        $("#vat").val(${vat});
        $("#countRow").val("0");
        
        $( ".numerical" ).on('input', function() { 
            var value=$(this).val().replace(/[^0-9.,]*/g, '');
            value=value.replace(/\.{2,}/g, '.');
            value=value.replace(/\.,/g, ',');
            value=value.replace(/\,\./g, ',');
            value=value.replace(/\,{2,}/g, ',');
            value=value.replace(/\.[0-9]+\./g, '.');
            $(this).val(value)
        });
        
        $('#InvoiceSupTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });

        $('#InvoiceSupTable tbody').on('click', 'tr', function () {
            $(this).addClass('row_selected').siblings().removeClass('row_selected');
        });
        
        $("#InvoiceSupTable tr").on('click', function () {
            
            var invoice_id = $(this).find(".invoice-id").text();
            var invoice_code = $(this).find(".invoice-code").text();
            var invoice_name = $(this).find(".invoice-name").text();
            var invoice_apcode = $(this).find(".invoice-apcode").text();
            $("#invoiceSupId").val(invoice_id);
            $("#invoiceSupCode").val(invoice_code);
            $("#invoiceSupName").val(invoice_name);
            $("#apCode").val(invoice_apcode);
            $('#PaymentAirlineForm').bootstrapValidator('revalidateField', 'invoiceSupCode');
            $('#PaymentAirlineForm').bootstrapValidator('revalidateField', 'apCode');
            $("#InvoiceSupModal").modal('hide');
        });
        
        
        var invoiceSupCode = [];
        $.each(invoiceSup, function (key, value) {
//            console.log("invoiceCount=="+invoiceSup.length);
            invoiceSupCode.push(value.code);
            invoiceSupCode.push(value.name);
        });

        $("#invoiceSupCode").autocomplete({
            source: invoiceSupCode,
            close:function( event, ui ) {
               $("#invoiceSupCode").trigger('keyup');
            }
        });
        
        $("#invoiceSupCode").on('keyup',function(){
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var code = this.value.toUpperCase();
            var name = this.value.toUpperCase();
            $("#invoiceSupId,#invoiceSupName,#apCode").val(null);
            $.each(invoiceSup, function (key, value) {
                
                if (value.code.toUpperCase() === code ) {  
                    $("#invoiceSupId").val(value.id);
                    $("#invoiceSupName").val(value.name);
                    $("#apCode").val(value.apcode);
                }
                else if(value.name.toUpperCase() === name){
                    $("#invoiceSupCode").val(value.code);
                    $("#invoiceSupId").val(value.id);
                    $("#invoiceSupName").val(value.name);
                    $("#apCode").val(value.apcode);
                }
            }); 
            
        });
        
        $('#PaymentAirlineForm').bootstrapValidator({
            container: 'tooltip',
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                invoiceSupCode: {
                    validators: {
                        notEmpty: {
                            message: 'Invoice Sup is required'
                        }
                    }
                },
                apCode: {
                    validators: {
                        notEmpty: {
                            message: 'A/P Code is required'
                        }
                    }
                },
                refundNo: {
                    validators: {
                        notEmpty: {
                            message: 'Refund No. is required'
                        }
                    }
                }
            }
        });
        
        $("#paymentNo").keyup(function (event) {
            if(event.keyCode === 13){
               searchPaymentNo();
            }
        });

//      Ticket Fare Table       
        var ticketTable = $('#TicketFareTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "aaSorting": [[ 0, "desc" ]]
        });

        $('.dataTables_length label').remove();

        $('#TicketFareTable tbody').on('click', 'tr', function() {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                ticketTable.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#TicketFareTable tbody tr.row_selected').attr("id"));
            }
        });
        
        $("#cash").focusout(function(){
            setFormatCurrency();
            setDataCurrency();    
        });
        $("#agentAmount").focusout(function(){
            setFormatCurrency();
            setDataCurrency();    
        });    
        $("#debitAmount").focusout(function(){
            setFormatCurrency();
            setDataCurrency();        
        });
        $("#amount").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });    
        //calculate Amount
        $("#withholdingTax").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
            calculateAmount();
        });
        
        //calculate TotalPayment
        $("#creditAmount").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
            calculateTotalPayment();
            calculateAmount();
        });
        setFormatCurrency();
        setDataCurrency();
//        calculateTotalAmount();
//        calculateTotalCommission();
        calculateTotalPayment();
        calculateAmount();
      
//        $('#RefundTicketTable').dataTable({bJQueryUI: true,
//            "sPaginationType": "full_numbers",
//            "bAutoWidth": false,
//            "bFilter": false,
//            "aaSorting": [[ 0, "desc" ]]
//        });
//        $('.dataTables_length label').remove();
        

    });
function setFormatCurrency(){  
    var withholdingTax = replaceAll(",","",$('#withholdingTax').val()); 
    if (withholdingTax == ""){
        withholdingTax = 0;
    }
    withholdingTax = parseFloat(withholdingTax); 
    document.getElementById("withholdingTax").value = formatNumber(withholdingTax);
    
    var cash = replaceAll(",","",$('#cash').val()); 
    if (cash == ""){
        cash = 0;
    }
    cash = parseFloat(cash); 
    document.getElementById("cash").value = formatNumber(cash);
    
    var agentAmount = replaceAll(",","",$('#agentAmount').val()); 
    if (agentAmount == ""){
        agentAmount = 0;
    }
    var agentAmount = parseFloat(agentAmount); 
    document.getElementById("agentAmount").value = formatNumber(agentAmount);
    
    var creditAmount = replaceAll(",","",$('#creditAmount').val()); 
    if (creditAmount == ""){
        creditAmount = 0;
    }
    var creditAmount = parseFloat(creditAmount); 
    document.getElementById("creditAmount").value = formatNumber(creditAmount);
    
    var debitAmount = replaceAll(",","",$('#debitAmount').val()); 
    if (debitAmount == ""){
        debitAmount = 0;
    }
    var debitAmount = parseFloat(debitAmount); 
    document.getElementById("debitAmount").value = formatNumber(debitAmount);
}

function setDataCurrency(){
    var withholdingTax = replaceAll(",","",$('#withholdingTax').val()); 
    if (withholdingTax == "" || withholdingTax == 0){
        document.getElementById("withholdingTax").value = "";
    }
    
    var cash = replaceAll(",","",$('#cash').val()); 
    if (cash == "" || cash == 0){
        document.getElementById("cash").value = "";
    }
    
    var agentAmount = replaceAll(",","",$('#agentAmount').val()); 
    if (agentAmount == "" || agentAmount == 0){
        document.getElementById("agentAmount").value = "";  
    }
            
    
    var debitAmount = replaceAll(",","",$('#debitAmount').val()); 
    if (debitAmount == "" || debitAmount == 0){
        document.getElementById("debitAmount").value = ""; 
    }
    
    
    var creditAmount = replaceAll(",","",$('#creditAmount').val()); 
    if (creditAmount == "" || creditAmount == 0){
        document.getElementById("creditAmount").value = ""; 
    }
    
    var amount = replaceAll(",","",$('#amount').val()); 
    if (amount == "" || amount == 0){
        document.getElementById("amount").value = "";  
    }
}   

function setupInvSupValue(){    
    $("#InvoiceSupModal").modal('hide');
}

function searchPaymentNo() {
    var action = document.getElementById('action');
    action.value = 'search';
    var paymentNo = document.getElementById('paymentNo');
    paymentNo.value = $("#paymentNo").val();
    document.getElementById('PaymentAirlineForm').submit();
}

function searchTicketFare() {
    var flagSearch = $("#flagSearch").val();
    if(flagSearch == "1"){
        $("#searchTicketFare").text('Are you sure to sarch ticket fare');
        $('#ConfirmSearchTicket').modal('show');
    }else{
        searchTicketFareCF();
    }
}

function searchTicketFareCF() {
    var action = document.getElementById('action');
    action.value = 'searchTicketFare';
    var ticketFrom = document.getElementById('ticketFrom');
    ticketFrom.value = $("#ticketFrom").val();
    var typeAirline = document.getElementById('typeAirline');
    typeAirline.value = $("#typeAirline").val();
    var dateFrom = document.getElementById('dateFrom');
    dateFrom.value = $("#dateFrom").val();
    var dateTo = document.getElementById('dateTo');
    dateTo.value = $("#dateTo").val();
    
    var paymentNo = document.getElementById('paymentNo');
    paymentNo.value = $("#paymentNo").val();
    var paymentDate = document.getElementById('paymentDate');
    paymentDate.value = $("#paymentDate").val();
    var duePaymentDate = document.getElementById('duePaymentDate');
    duePaymentDate.value = $("#duePaymentDate").val();
    var invoiceSupCode = document.getElementById('invoiceSupCode');
    invoiceSupCode.value = $("#invoiceSupCode").val();
    var apCode = document.getElementById('apCode');
    apCode.value = $("#apCode").val(); 
    var detail = document.getElementById('detail');
    detail.value = $("#detail").val();
    var payBy = document.getElementById('payBy');
    payBy.value = $("#payBy").val();
    var agentAmount = document.getElementById('agentAmount');
    agentAmount.value = $("#agentAmount").val();
    var creditNote = document.getElementById('creditNote'); 
    creditNote.value = $("#creditNote").val();
    var creditAmount = document.getElementById('creditAmount');
    creditAmount.value = $("#creditAmount").val();
    var commissionVat = document.getElementById('commissionVat');
    commissionVat.value = $("#commissionVat").val();
    var debitNote = document.getElementById('debitNote');
    debitNote.value = $("#debitNote").val();
    var cash = document.getElementById('cash');
    cash.value = $("#cash").val();
    var withholdingTax = document.getElementById('withholdingTax');
    withholdingTax.value = $("#withholdingTax").val();
    var chqNo = document.getElementById('chqNo');
    chqNo.value = $("#chqNo").val();
    var amount = document.getElementById('amount');
    amount.value = $("#amount").val();
    var totalPayment = document.getElementById('totalPayment');
    totalPayment.value = $("#totalPayment").val();
    var debitAmount = document.getElementById('debitAmount');
    debitAmount.value = $("#debitAmount").val();
    
    var ticketFrom = document.getElementById('ticketFrom');
    ticketFrom.value = $("#ticketFrom").val();
    var typeAirline = document.getElementById('typeAirline');
    typeAirline.value = $("#typeAirline").val();
    var dateFrom = document.getElementById('dateFrom');
    dateFrom.value = $("#dateFrom").val();
    var dateTo = document.getElementById('dateTo');
    dateTo.value = $("#dateTo").val();
    

    var totalCommissionTicketFare = document.getElementById('totalCommissionTicketFare');
    totalCommissionTicketFare.value = $("#totalCommissionTicketFare").val();
    var totalAmountTicketFare = document.getElementById('totalAmountTicketFare');
    totalAmountTicketFare.value = $("#totalAmountTicketFare").val();
    var totalAmountRefund = document.getElementById('totalAmountRefund');
    totalAmountRefund.value = $("#totalAmountRefund").val();
    var totalAmountRefundVat = document.getElementById('totalAmountRefundVat');
    totalAmountRefundVat.value = $("#totalAmountRefundVat").val();
    document.getElementById('PaymentAirlineForm').submit();
}

function addAction(){
    var refundNo = $("#refundNo").val();
    var tablelength = $('#RefundTicketTable tr').length;
    var duplicate = 0;
    
    if(tablelength == 1){
        duplicate = 1;
    }
    
    var i = 1;
    for (i = 1; i < tablelength; i++) { 
        var refund = $("#refundNoRow"+i).val();
        var refundNumber = refund.toString();
        if(refundNumber != ""){
            if(refundNo == refundNumber){
                duplicate = 2;
            }
        }
    }
    
    if(duplicate == 0 || duplicate == 1){
        addRefundNo();
    }else if(duplicate == 2){
        $("#addRefundNoAlert").text('Are you sure to add duplicate Refund No. : '+refundNo + " ?");
        $('#AddRefundNoModal').modal('show');
    }
    
    duplicate = 0;
}

function addRefundNo(){
    $('#AddRefundNoModal').modal('hide');
    var refundNo = $("#refundNo").val();
    if(refundNo == ""){
        $('#PaymentAirlineForm').bootstrapValidator('revalidateField', 'refundNo');
    }else{
        var rowCount = $('#RefundTicketTable tr').length;
        var servletName = 'PaymentAirTicketServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&refundNo=' + refundNo +
                '&rowCount=' + rowCount +
                '&type=' + 'addRefund';
        CallAjaxAdd(param);
    }
}

function CallAjaxAdd(param) {
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
            //RefundTicketTable
                try {
                    $("#RefundTicketTable tbody").append(msg);

                    calculateTotalAmountRefund();
                    calculateTotalRefundVat();
                    calculateTotalPayment();
                    calculateAmount();
                    
                    $("#ajaxload").addClass("hidden");
                } catch (e) {
                    alert(e);
                }
            }, error: function (msg) {
                 $("#ajaxload").addClass("hidden");
            }

        });
    } catch (e) {
        alert(e);
    }
}     

function saveAction(){
    var action = document.getElementById('action');
    action.value = 'save';
    var paymentNo = document.getElementById('paymentNo');
    paymentNo.value = $("#paymentNo").val();
    var paymentDate = document.getElementById('paymentDate');
    paymentDate.value = $("#paymentDate").val();
    var duePaymentDate = document.getElementById('duePaymentDate');
    duePaymentDate.value = $("#duePaymentDate").val();
    var invoiceSupCode = document.getElementById('invoiceSupCode');
    invoiceSupCode.value = $("#invoiceSupCode").val();
    var apCode = document.getElementById('apCode');
    apCode.value = $("#apCode").val(); 
    var detail = document.getElementById('detail');
    detail.value = $("#detail").val();
    var payBy = document.getElementById('payBy');
    payBy.value = $("#payBy").val();
    var agentAmount = document.getElementById('agentAmount');
    agentAmount.value = $("#agentAmount").val();
    var creditNote = document.getElementById('creditNote'); 
    creditNote.value = $("#creditNote").val();
    var creditAmount = document.getElementById('creditAmount');
    creditAmount.value = $("#creditAmount").val();
    var commissionVat = document.getElementById('commissionVat');
    commissionVat.value = $("#commissionVat").val();
    var debitNote = document.getElementById('debitNote');
    debitNote.value = $("#debitNote").val();
    var cash = document.getElementById('cash');
    cash.value = $("#cash").val();
    var withholdingTax = document.getElementById('withholdingTax');
    withholdingTax.value = $("#withholdingTax").val();
    var chqNo = document.getElementById('chqNo');
    chqNo.value = $("#chqNo").val();
    var amount = document.getElementById('amount');
    amount.value = $("#amount").val();
    var totalPayment = document.getElementById('totalPayment');
    totalPayment.value = $("#totalPayment").val();
    var debitAmount = document.getElementById('debitAmount');
    debitAmount.value = $("#debitAmount").val();
    
    var ticketFrom = document.getElementById('ticketFrom');
    ticketFrom.value = $("#ticketFrom").val();
    var typeAirline = document.getElementById('typeAirline');
    typeAirline.value = $("#typeAirline").val();
    var dateFrom = document.getElementById('dateFrom');
    dateFrom.value = $("#dateFrom").val();
    var dateTo = document.getElementById('dateTo');
    dateTo.value = $("#dateTo").val();
    

    var totalCommissionTicketFare = document.getElementById('totalCommissionTicketFare');
    totalCommissionTicketFare.value = $("#totalCommissionTicketFare").val();
    var totalAmountTicketFare = document.getElementById('totalAmountTicketFare');
    totalAmountTicketFare.value = $("#totalAmountTicketFare").val();
    var totalAmountRefund = document.getElementById('totalAmountRefund');
    totalAmountRefund.value = $("#totalAmountRefund").val();
    var totalAmountRefundVat = document.getElementById('totalAmountRefundVat');
    totalAmountRefundVat.value = $("#totalAmountRefundVat").val();
    
    var rowRefundCount = document.getElementById('rowRefundCount');
    rowRefundCount.value = $('#RefundTicketTable tr').length;
    
    document.getElementById('PaymentAirlineForm').submit();
}

function deleteTicket(id,ticketNo,count){
    document.getElementById('deleteTicketNo').value = ticketNo;
    document.getElementById('deleteTicketId').value = id;
    document.getElementById('deleteTicketCount').value = count;
    $("#delTicket").text('Are you sure to delete Ticket No : '+ticketNo + " ?");
    $('#DeleteTicket').modal('show');
}

function DeleteRowTicket(){
    var no = document.getElementById('deleteTicketNo').value;
    var ticketid = document.getElementById('deleteTicketId').value;
    var count = document.getElementById('deleteTicketCount').value;
    var paymentId = document.getElementById('paymentId').value;
    
    $("#tableId" + count).parent().remove();
    calculateTotalAmount();
    calculateTotalCommission();
    calculateTotalPayment();
    calculateAmount();
    $.ajax({
        url: 'PaymentAirline.smi?action=deleteTicket',
        type: 'get',
        data: {deleteTicketId: ticketid , paymentId:paymentId},
        success: function () {
            
        calculateTotalAmount();
        calculateTotalCommission();
        calculateTotalPayment();
        calculateAmount();

        },
        error: function () {
            console.log("error");
            result =0;
        }
    }); 
    $('#DeleteTicket').modal('hide');
}
function deleteRefund(id,refundNo,rowCount){
    $("#deleteRefundCount").val(rowCount);
    $("#delRefundId").val(id);
    $("#delRefundNo").val(refundNo);
    $("#delRefundAlert").text('Are you sure to delete Refund No : '+refundNo + " ?");
    $('#DelRefund').modal('show');
}

function DeleteRowRefund(){
    var rowCount = document.getElementById('deleteRefundCount').value;
    var refundno = document.getElementById('delRefundNo').value;
    var refundid = document.getElementById('delRefundId').value;
    var paymentId = document.getElementById('paymentId').value;
    $("#tableRefundId" + rowCount).parent().remove();
    calculateTotalAmountRefund();
    calculateTotalRefundVat();
    calculateTotalPayment();
    calculateAmount();
    $.ajax({
        url: 'PaymentAirline.smi?action=deleteRefund',
        type: 'get',
        data: {delRefundId:refundid ,paymentId:paymentId },
        success: function () {
            calculateTotalAmountRefund();
            calculateTotalRefundVat();
            calculateTotalPayment();
            calculateAmount();
        },
        error: function () {
            console.log("error");
            result =0;
        }
    }); 
    $('#DelRefund').modal('hide');
}

function calculateAmount() {
    var withholding = replaceAll(",","",$('#withholdingTax').val()); 
    if (withholding == ""){
        withholding = 0;
    }
    
    var totalPay = replaceAll(",","",$('#totalPayment').val()); 
    if (totalPay == ""){
        totalPay = 0;
    }

    var tax = parseFloat(withholding); 
    var pay = parseFloat(totalPay);
    
    var amount = pay+tax;
    document.getElementById("amount").value = formatNumber(amount);
}

function calculateTotalPayment() {
//    Total Payment = Total Amount - TotalComission - Total Refund + Total Amount Refund Vat - Credit Amount
    var totalAmount = replaceAll(",","",$('#totalAmountTicketFare').val()); 
    if (totalAmount == ""){
        totalAmount = 0;
    }
    
    var totalCommission = replaceAll(",","",$('#totalCommissionTicketFare').val()); 
    if (totalCommission == ""){
        totalCommission = 0;
    }
    
    var totalRefund = replaceAll(",","",$('#totalAmountRefund').val()); 
    if (totalRefund == ""){
        totalRefund = 0;
    }    
    
    var totalRefundVat = replaceAll(",","",$('#totalAmountRefundVat').val()); 
    if (totalRefundVat == ""){
        totalRefundVat = 0;
    }
    
    var credit = replaceAll(",","",$('#creditAmount').val()); 
    if (credit == ""){
        credit = 0;
    }
    
    var amountTotal = parseFloat(totalAmount); 
    var comTotal = parseFloat(totalCommission);
    var refundTotal = parseFloat(totalRefund);
    var refundVat = parseFloat(totalRefundVat);
    var creditAmount = parseFloat(credit);
    
    
    var totalPayment = amountTotal - comTotal - refundTotal + refundVat - creditAmount;
    document.getElementById("totalPayment").value = formatNumber(totalPayment);
}

function calculateTotalRefundVat() {
//  Total Amount Refund  vat = vat * Total Amount Refund / 100
    var vatValue = replaceAll(",","",$('#vat').val()); 
    if (vatValue == ""){
        vatValue = 0;
    }
    var totalRefund = replaceAll(",","",$('#totalAmountRefund').val()); 
    if (totalRefund == ""){
        totalRefund = 0;
    }
    
    var vat = parseFloat(vatValue); 
    var refundTotal = parseFloat(totalRefund);
    
    
    var totalRefundVat = refundTotal * (vat / 100);
    document.getElementById("totalAmountRefundVat").value = formatNumber(totalRefundVat);
}

function calculateTotalCommission() {
    var temp = 0;
    var commissionTemp = parseFloat(0);
    var tableTicket = document.getElementById('TicketFareTable');
    for (var r = 1, n = tableTicket.rows.length; r < n; r++) {
        temp = tableTicket.rows[r].cells[6].innerHTML;
        temp = (temp.trim) ? temp.trim() : temp.replace(/^\s+/,'');
        if(temp == '') {
            temp = 0;
        }
        temp = replaceAll(",","",temp.toString()); 
        var valueCom = parseFloat(temp) ;
        var commission = commissionTemp + valueCom ;
        commissionTemp = commission;

        }
        
    document.getElementById("totalCommissionTicketFare").value = formatNumber(commission);
}

function calculateTotalAmount(){
    var temp = 0;
    var amountTemp = parseFloat(0);
    var tableTicket = document.getElementById('TicketFareTable');
    for (var r = 1, n = tableTicket.rows.length; r < n; r++) {
        temp = tableTicket.rows[r].cells[7].innerHTML;
        temp = (temp.trim) ? temp.trim() : temp.replace(/^\s+/,'');
        if(temp == '') {
            temp = 0;
        }
        temp = replaceAll(",","",temp.toString()); 
        var value = parseFloat(temp) ;
        var amount = amountTemp + value ;
        amountTemp = amount;

    }
    document.getElementById("totalAmountTicketFare").value = formatNumber(amount);
}

function calculateTotalAmountRefund(){
    var temp = 0;
    var refundTemp = parseFloat(0);
    var table = document.getElementById('RefundTicketTable');
    for (var r = 1, n = table.rows.length; r < n; r++) {
        temp = table.rows[r].cells[5].innerHTML;
        temp = (temp.trim) ? temp.trim() : temp.replace(/^\s+/,'');
        if(temp == '') {
            temp = 0;
        }
        temp = replaceAll(",","",temp.toString());
        var value = parseFloat(temp) ;
        var refund = refundTemp + value ;
        refundTemp = refund;

    }
    document.getElementById("totalAmountRefund").value = formatNumber(refund);
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
</script>