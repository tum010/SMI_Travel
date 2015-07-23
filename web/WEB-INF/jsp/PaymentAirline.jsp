<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/PaymentAirline.js"></script> 
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

<c:set var="ticketFareList" value="${requestScope['ticketFareList']}" /> 
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
                <input type="hidden" name="action" id="action" value="">
                <div class="panel panel-default">
                    <div class="panel-body"  style="padding-right: 0px;" style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Payment No </label>
                            </div>
                            <div class="col-xs-1" style="width:180px">
                                <div class='input-group'>
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
                                        <option value="${table.id}" ${select}>${table.code}</option>  
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
                                <button style="height:34px" type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="searchTicketFare();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search</button>
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
                                        <td align="center"> <c:out value="${table.referenceNo}" /></td>
                                        <td align="center"> <c:out value="${table.ticketNo}" /></td>
                                        <td align="center"> <c:out value="${table.department}" /></td>
                                        <td align="center"> <c:out value="${table.fare}" /></td>
                                        <td align="center"> <c:out value="${table.tax}" /></td>
                                        <td align="center"> <c:out value="${table.ticketIns}" /></td>
                                        <td align="center"> <c:out value="${table.ticketCommission}" /></td>
                                        <td align="center"> <c:out value="${table.salePrice}" /></td>
                                        <td> 
                                            <center> 
                                            <span  class="glyphicon glyphicon-remove deleteicon"  onclick="deleteTicket('${table.id}','${table.ticketNo}')" 
                                                   data-toggle="modal" data-target="#DelTicket" >  </span>
                                            </center>
                                            <input type="hidden" name="deleteTicketNo" id="deleteTicketNo" value="${table.ticketNo}">
                                            <input type="hidden" name="deleteTicketId" id="deleteTicketId" value="${table.id}">
                                        </td>                                    
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
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
                                    <th style="width:20%;">Route</th>
                                    <th style="width:10%;">Commission</th>
                                    <th style="width:10%;">Amount</th>
                                    <th style="width:10%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <input type="hidden" id="delRefundNo" name="delRefundNo" >
                                <input type="hidden" id="delRefundId" name="delRefundId" >
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
                                    <input id="agentAmount" name="agentAmount" type="text" class="form-control money" value="${paymentAirticket.agentAmount}">
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
                                    <input id="creditAmount" name="creditAmount" type="text" class="form-control money" value="${paymentAirticket.creditAmount}">
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Commission Vat </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="commissionVat" name="commissionVat" type="text" class="form-control money" value="">
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
                                    <input id="debitAmount" name="debitAmount" type="text" class="form-control money" value="${paymentAirticket.debitAmount}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Cash </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="cash" name="cash" type="text" class="form-control money" value="${paymentAirticket.cash}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Withholding Tax </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="withholdingTax" name="withholdingTax" type="text" class="form-control money" value="${paymentAirticket.witholdingTax}">
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
                                    <input id="amount" name="amount" type="text" class="form-control money" value="${paymentAirticket.chqAmount}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 140px">
                                <label class="control-label text-right">Total Payment </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="totalPayment" name="totalPayment" type="text" class="form-control money" readonly="" value="${paymentAirticket.totalAmount}">
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
                            <tr>
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

<!--Delete Refund Modal-->
<div class="modal fade" id="DelRefund" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"> Delete Refund </h4>
            </div>
            <div class="modal-body" id="delRefundNoAlert"></div>
            <div class="modal-footer" id="delfooter">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->   

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

<!--Script-->       
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $(".money").mask('000,000,000.00', {reverse: true});
        var ApCodeTable = $('#ApCodeTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
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
            console.log("invoiceCount=="+invoiceSup.length);
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
        
//        $('#PaymentAirlineForm').bootstrapValidator({
//            container: 'tooltip',
//            excluded: [':disabled', ':hidden', ':not(:visible)'],
//            feedbackIcons: {
//                valid: 'uk-icon-check',
//                invalid: 'uk-icon-times',
//                validating: 'uk-icon-refresh'
//            },
//            fields: {
//                invoiceSupCode: {
//                    validators: {
//                        notEmpty: {
//                            message: 'Invoice Sup is required'
//                        }
//                    }
//                },
//                apCode: {
//                    validators: {
//                        notEmpty: {
//                            message: 'A/P Code is required'
//                        }
//                    }
//                }      
//            }
//        });
        
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
        //calculate Amount
        $("#withholdingTax").focusout(function(){
            calculateAmount();
        });
        $("#totalPayment").focusout(function(){
            calculateAmount();
        });
        
        //calculate TotalPayment
        $("#totalCommissionTicketFare").focusout(function(){
            calculateTotalPayment();
        });
        $("#totalAmountTicketFare").focusout(function(){
            calculateTotalPayment();
        });
        $("#totalAmountRefund").focusout(function(){
            calculateTotalPayment();
        });
        $("#totalAmountRefundVat").focusout(function(){
            calculateTotalPayment();
        });
        $("#creditAmount").focusout(function(){
            calculateTotalPayment();
        });
    });
    
function searchPaymentNo() {
    var action = document.getElementById('action');
    action.value = 'search';
    var paymentNo = document.getElementById('paymentNo');
    paymentNo.value = $("#paymentNo").val();
    document.getElementById('PaymentAirlineForm').submit();
}
function searchTicketFare() {
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
    document.getElementById('PaymentAirlineForm').submit();
}

function addAction(){
    var refundNo = $("#refundNo").val();
    var servletName = 'PaymentAirTicketServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&refundNo=' + refundNo +
            '&type=' + 'addRefund';
    CallAjaxAdd(param);
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
                    $('#RefundTicketTable').dataTable().fnClearTable();
                    $('#RefundTicketTable').dataTable().fnDestroy();
                    $("#RefundTicketTable tbody").empty().append(msg);
                    $('#RefundTicketTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });
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
    
    document.getElementById('PaymentAirlineForm').submit();
}

function deleteRefund(id,refundNo){
    var delRefundId = document.getElementById('delRefundId');
    delRefundId.value = id;
    var delRefundNo = document.getElementById('delRefundNo');
    delRefundNo.value = refundNo;
    document.getElementById('delRefundNoAlert').innerHTML = "Are you sure to delete Refund No : " + refundNo + " ?";
}

function Delete() {
    var action = document.getElementById('action');
    action.value = 'delete';
    var delRefundId = document.getElementById('delRefundId');
    delRefundId.value = $("#delRefundId").val();
    var delRefundNo = document.getElementById('delRefundNo');
    delRefundNo.value = $("#delRefundNo").val();
    document.getElementById('PaymentAirlineForm').submit();
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

function replaceAll(find, replace, str) {
  return str.replace(new RegExp(find, 'g'), replace);
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
}
</script>
