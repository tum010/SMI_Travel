<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/Receipt.js"></script>--> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="dataPVList" value="${requestScope['PVList']}" />
<c:set var="Type" value="${requestScope['typeInvoice']}" />
<input type="hidden" id="Type" name="Type" value="${param.Type}">
<c:set var="customerAgentList" value="${requestScope['customerAgent']}" />
<c:set var="productListTable" value="${requestScope['Product_List']}" />
<c:set var="billTypeList" value="${requestScope['billTypeList']}" /> 
<c:set var="currencyList" value="${requestScope['currencyList']}" />
<c:set var="creditBankList" value="${requestScope['creditBankList']}" />
<c:set var="statusList" value="${requestScope['statusList']}" />
<c:set var="receipt" value="${requestScope['receipt']}" />
<c:set var="result" value="${requestScope['result']}" />
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
       
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/ReceiptMenu.html'"></div>
        </div>
        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <c:choose>
                        <c:when test="${fn:contains(Type , 'wendytemp')}">
                            <h4><b>Receipt Temp Wendy</b></h4>
                        </c:when>
                        <c:when test="${fn:contains(Type , 'wendyvat')}">
                            <h4><b>Receipt Vat Wendy</b></h4>
                        </c:when>    
                        <c:when test="${fn:contains(Type , 'outtemp')}">
                            <h4><b>Receipt Temp Outbound</b></h4>
                        </c:when>
                        <c:when test="${fn:contains(Type , 'outvat')}">
                            <h4><b>Receipt Vat Outbound</b></h4>
                        </c:when>    
                        <c:when test="${fn:contains(Type , 'intemp')}">
                            <h4><b>Receipt Temp Inbound</b></h4>
                        </c:when>    
                        <c:when test="${fn:contains(Type , 'invat')}">
                            <h4><b>Receipt Vat Inbound</b></h4>
                        </c:when> 
                    </c:choose>                
                </div>
                <div class="col-xs-12 form-group"><hr/></div>
            </div>
            <hr/>
            
            <form action="Receipt.smi" method="post" id="ReceiptForm" name="ReceiptForm" role="form">
                <div role="tabpanel">
                     <!-- Nav tabs -->
                     
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#bl" aria-controls="bl" role="tab" data-toggle="tab">BL</a></li>
                        <li role="presentation" class=""><a href="#com" aria-controls="com" role="tab" data-toggle="tab">COM</a></li>
                        <h4><a class="col-xs-10 text-right" data-toggle="collapse" href="#collapseTab" aria-expanded="false" aria-controls="collapseTab">
                            <span id="arrowReceipt" class="arrowReceipt glyphicon glyphicon-chevron-up"></span>
                        </a></h4>
                    </ul>
                                
                    <!-- Tab BL -->
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="tab-content collapse in" id="collapseTab" aria-expanded="false">
                                <div role="tabpanel" class="tab-pane hidden active" id="bl">
                                    <div class="col-xs-6 form-group" style="padding-top: 20px;">
                                        <div class="col-xs-1 text-right" style="width: 120px">
                                            <label class="control-label text-right">Invoice No </label>
                                        </div>
                                        <div class="col-xs-1 form-group" style="width: 200px" id="invoicenopanel">
                                            <div class="input-group">
                                                <input id="invoiceNo" name="invoiceNo" type="text" class="form-control" value="" onkeydown="invoicenoValidate()">
                                            </div>
                                        </div>
                                        <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i></div>
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
                                                        <th style="width:20%;">Price</th>
                                                        <th style="width:10%;">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 form-group" style="padding-top: 20px;">
                                        <div class="col-xs-1 text-right" style="width: 120px">
                                            <label class="control-label text-right">Ref No </label>
                                        </div>
                                        <div class="col-xs-1 form-group" style="width: 200px">
                                            <div class="input-group">
                                                <input id="refNo" name="refNo" type="text" class="form-control" value="">
                                            </div>
                                        </div>
                                        <div class="col-xs-1 text-left"  style="width: 100px">
                                            <button style="height:30px" type="submit"  id="searchRefNo"  name="searchRefNo" onclick="searchRefNo();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search </button>
                                        </div>
                                        <!--RefNo Table-->
                                        <div class="row" style="padding-left: 10px;padding-right: 10px">
                                            <table id="RefNoListTable" class="display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr class="datatable-header" >
                                                        <th style="width:10%;">No</th>
                                                        <th style="width:40%;">Description</th>
                                                        <th style="width:20%;">Price</th>
                                                        <th style="width:10%;">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td><center>111111</center></td>
                                                        <td><center>222222</center></td>
                                                        <td><center>333333</center></td>
                                                        <td>
                                                            <center>
                                                                <a href=""><span class="glyphicon glyphicon-plus"></span></a>
                                                            </center>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <!-- Tab COM -->
                                <div role="tabpanel" class="tab-pane hidden" id="com">
                                    <div class="col-xs-4 form-group" style="padding-top: 20px;">
                                        <div class="col-xs-1 text-right" style="width: 80px">
                                            <label class="control-label text-right">PV List </label>
                                        </div>
                                        <div class="col-xs-1 text-right" style="width: 135px">
                                            <label class="control-label text-right">Search</label>
                                        </div>
                                        <div class="col-xs-1 form-group">
                                            <input style="width: 105px" id="pvList" name="pvList" type="text" class="form-control" value="">
                                        </div>
                                        <!--Invoice Table-->
                                        <div class="row" style="padding-left: 10px;padding-right: 10px">
                                            <table id="InvoiceListTable" class="display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr class="datatable-header" >
                                                        <th style="width:10%;">No</th>
                                                        <th style="width:40%;">Payment No</th>
                                                        <th style="width:10%;">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%--<c:forEach var="table" items="${dataPVList}" varStatus="dataStatus">--%>
                                                        <tr>
                                                            <td> 111</td>
                                                            <td> 222</td>
<!--                                                            <td class="hidden" ><c:out value="${table.no}" /></td>
                                                            <td><c:out value="${fn:toUpperCase(table.paymentNo)}"  /></td>-->
                                                            <td>
                                                                <center> 
                                                                    <a href="Receipt.smi?action=edit&paymentNo=222">
                                                                        <span class="glyphicon glyphicon-check"></span>
                                                                    </a>
                                                                </center>
                                                            </td>                 
                                                        </tr>  
                                                    <%--</c:forEach>--%>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-xs-8 form-group" style="padding-top: 20px;">
                                        <div class="row">
                                            <div class="col-xs-1 text-right" style="width: 200px">
                                                <label class="control-label text-right">Payment No </label>
                                            </div>
                                            <div class="col-xs-1 form-group" style="width: 300px">
                                                <input id="paymentNo" name="paymentNo" type="text" class="form-control" value="">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-1 text-right" style="width: 200px">
                                                <label class="control-label text-right">Airline </label>
                                            </div>
                                            <div class="col-xs-1 form-group" style="width: 300px">
                                                <select id="inputAirline" name="inputAirline" class="form-control selectize">
                                                    <option value=""> Code : Name </option>

                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-1 text-right" style="width: 200px">
                                                <label class="control-label text-right">Commission </label>
                                            </div>
                                            <div class="col-xs-1 form-group" style="width: 300px">
                                                <input id="commission" name="commission" type="text" class="form-control" value="">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-1 text-right" style="width: 500px">
                                                <button style="height:30px" type="submit"  id="ButtonAdd"  name="ButtonAdd" onclick="addAction();" class="btn btn-primary btn-sm">&nbsp;&nbsp;Add&nbsp;&nbsp;</button>
                                            </div>
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
                                           class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${receipt.recDate}">
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
                                            <c:set var="selectedId" value="${receipt.MItemStatus.id}" />
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
                                            <th style="width:5%;">Product</th>
                                            <th style="width:7%;">Description</th>
                                            <th style="width:7%;">Cost</th>
                                            <th style="width:7%;">Cur</th>
                                            <th style="width:7%;">Is Vat</th>
                                            <th style="width:10%;">Vat</th>
                                            <!--<th style="width:10%;">Gross</th>-->
                                            <th style="width:10%;">Amount</th>
                                            <th style="width:7%;">Cur</th>
                                            <th style="width:5%;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody> 
                                        <%--<c:forEach var="table" items="${productListTable}" varStatus="dataStatus">--%>
<!--                                            <tr>
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
                                                        <a class="remCF"><span id="SpanRemove${dataStatus.count}" onclick="DeleteAir('${table.id}','${table.ticketNo}','${dataStatus.count}');" class="glyphicon glyphicon-remove deleteicon " data-toggle="modal" data-target="#delReceiptModal"></span></a>
                                                    </center>
                                                    <input type="hidden" name="deleteTicketNo" id="deleteTicketNo">
                                                    <input type="hidden" name="deleteTicketId" id="deleteTicketId">
                                                    <input type="hidden" name="deleteTicketCount" id="deleteTicketCount">
                                                </td>                                    
                                            </tr>-->
                                        <%--</c:forEach>--%>
                                    </tbody>
                                </table>      
                            </div>
                        </div>
                        <input type="hidden" name="type" id="type" value="${requestScope['typeInvoice']}">
                        <input type="hidden" name="action" id="action" value="">
                        <input type="hidden" class="form-control" id="countRowCredit" name="countRowCredit" value="${requestScope['productRowCount']}" />
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
                            <div class="col-xs-12 form-group">
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">W/T </label>                                    
                                    </div>
                                    <div class="col-xs-1 " style="width: 200px">
                                        <input id="withTax" name="withTax" type="text" class="form-control" value="${receipt.withTax}">
                                    </div>
                                </div><hr/>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Cash Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 200px">
                                        <input id="cashAmount" name="cashAmount" type="text" class="form-control" value="${receipt.cashAmount}">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 160px">
                                        <label class="control-label text-right">Cash(-) Amount</label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 200px">
                                        <input id="cashMinusAmount" name="cashMinusAmount" type="text" class="form-control" value="${receipt.cashMinusAmount}">
                                    </div>
                                </div><hr/>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Bank Transfer </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 200px">
                                        <input id="bankTransfer" name="bankTransfer" type="text" class="form-control" value="${receipt.bankTransfer}">
                                    </div>
                                </div><hr/>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Chq Bank </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px" id="chqBank1" name="chqBank1" type="text" class="form-control" value="${receipt.chqBank1}">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Chq No </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input style="width: 115px" id="chqNo1" name="chqNo1" type="text" class="form-control" value="${receipt.chqNo1}">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Date </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="chqDate1" name="chqDate1"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${receipt.chqDate1}">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 100px">
                                        <label class="control-label text-right">Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input id="chqAmount1" name="chqAmount1" type="text" class="form-control" value="${receipt.chqAmount1}">
                                        
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
                                        <input style="width: 130px" id="chqBank2" name="chqBank2" type="text" class="form-control" value="${receipt.chqBank2}">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Chq No </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input  style="width: 115px" id="chqNo2" name="chqNo2" type="text" class="form-control" value="${receipt.chqNo2}">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Date </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="chqDate2" name="chqDate2"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${receipt.chqDate2}">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 100px">
                                        <label class="control-label text-right">Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input id="chqAmount2" name="chqAmount2" type="text" class="form-control" value="${receipt.chqAmount2}">
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
                                                <c:set var="selectedC" value="selected" />
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
                                <div class="col-md-1 text-left " style="width: 150px">
                                    <button type="button" class="btn btn-default" onclick="print('')">
                                        <span id="buttonPrint" class="glyphicon glyphicon-print" ></span> Print 
                                    </button>
                                </div>
                                    
                                <div class="col-md-4 text-right " style="width: 400px"></div>
                                <div class="col-md-1 text-right " style="width: 100px">
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
                                    <button type="button" class="btn btn-primary" onclick="EnableVoid();" data-toggle="modal" data-target="#EnableVoid" id="enableVoidButton" name="enableVoidButton"  ${isEnableVoid} >
                                        <span id="SpanEnableVoid" class="glyphicon glyphicon-ok" ></span> Cancel Void
                                    </button>

                                    <button type="button" class="btn btn-danger" onclick="DisableVoid();" data-toggle="modal" data-target="#DisableVoid" id="disableVoidButton" name="disableVoidButton" ${isDisableVoid} >
                                        <span id="SpanDisableVoid" class="glyphicon glyphicon-remove" ></span> Void
                                    </button>
                                </div>
                                <div class="col-md-1 text-right " style="width: 100px">
                                    <button type="submit" id="ButtonSave" name="ButtonSave" onclick="saveReceipt()" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                                </div>
                                <div class="col-md-1 text-right " style="width: 100px">
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

<!--List ARCode Modal -->
<div class="modal fade" id="ARCodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">A/R</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="ListARCodeTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Code</th>
                            <th>Name</th> 
                            <th class="hidden"></th>
                            <th class="hidden"></th>
                        </tr>
                    </thead>
                    <tbody>
<!--                        <tr>
                            <td><center>A10</center></td>
                            <td><center>Test</center></td>
                        </tr>-->
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="ListARCodeModalOK" name="ListARCodeModalOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="ListARCodeModalClose" name="ListARCodeModalClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->

<!--Disable-->
<div class="modal fade" id="DisableVoid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Finance & Cashier - Receipt</h4>
            </div>
            <div class="modal-body" id="disableVoid">
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick='window.top.location.href="Receipt.smi?button=disable"'>Delete</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<!--Enable-->
<div class="modal fade" id="EnableVoid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Finance & Cashier - Receipt </h4>
            </div>
            <div class="modal-body" id="enableCode">
                
            </div>
            <div class="modal-footer">
                <button type="button" onclick="Enable()" class="btn btn-success">Enable</button>
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

<!--Script-->       
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $("#bl,#com").removeClass('hidden');
        $('.datemask').mask('0000-00-00');
        $('.date').datetimepicker();
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

        $( ".numerical" ).on('input', function() { 
            var value=$(this).val().replace(/[^0-9.,]*/g, '');
            value=value.replace(/\.{2,}/g, '.');
            value=value.replace(/\.,/g, ',');
            value=value.replace(/\,\./g, ',');
            value=value.replace(/\,{2,}/g, ',');
            value=value.replace(/\.[0-9]+\./g, '.');
            $(this).val(value)
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
    });
    
    function deletelist(id,Ccount) {
        document.getElementById('plTableId').value = id;
        document.getElementById('productCountDel').value = Ccount;
        $("#delProduct").text('Are you sure delete this product ?');
        $('#DeleteProduct').modal('show');
    }
    
//    function DeleteRowProduct(){
//        var cCount = document.getElementById('productCountDel').value;
//        var id = document.getElementById('plTableId').value;
//        
//        if(id === ''){
//            $("#receiveProduct" + cCount).parent().parent().remove();
//            var rowAll = $("#ReceiptListTable tr").length;
//            if (rowAll <= 1) {
//                $("#tr_ProductDetailAddRow").removeClass("hide");
//                $("#tr_ProductDetailAddRow").addClass("show");
//            }
//            
//        } else {
//            $.ajax({
//                url: 'PaymentTourHotel.smi?action=deleteProductDetail',
//                type: 'get',
//                data: {ProductDetail: id},
//                success: function () {
//                    $("#receiveProduct" + cCount).parent().parent().remove();
//                    var rowAll = $("#ReceiptListTable tr").length;
//                    if (rowAll <= 1) {
//                        $("#tr_ProductDetailAddRow").removeClass("hide");
//                        $("#tr_ProductDetailAddRow").addClass("show");
//                    }
//                },
//                error: function () {
//                    console.log("error");
//                    result =0;
//                }
//            }); 
//        }    
//        $('#DeleteProduct').modal('hide');
//        CalculateGrandTotal('');
//    }
    function printReceiptNew() {
        window.open("report.smi?name=ReceiptEmail");
    }
    
    function printReceipt() {
        window.open("report.smi?name=ReceiptReport");
    }
    
    function AddRowProduct(row) {
//        var idRole = '${idRole}';
//        if((idRole === '22') || (idRole === '1')){                  
            $("#ReceiptListTable tbody").append(
                '<tr style="higth 100px">' +
                '<input id="tableId' + row + '" name="tableId' + row + '"  type="hidden" >' +
                '<td>' + 
                '<select class="form-control" name="receiveProduct' + row + '" id="receiveProduct' + row + '" ><option value="">---------</option></select>' +                          
                '</td>' +
                '<td><input maxlength="255" id="receiveDes' + row + '" name="receiveDes' + row + '" type="text" class="form-control" ></td>' +
                '<td><input maxlength="10" id="receiveCost' + row + '" name="receiveCost' + row + '" type="text" class="form-control" ></td>' +
                '<td><input maxlength="10" id="receiveCurCost' + row + '" name="receiveCurCost' + row + '" type="text" class="form-control" ></td>' +
                '<td align="center">' +
                '<input type="checkbox" name="receiveIsVat' + row + '" id="receiveIsVat' + row + '" onclick="handleClick(this,'+row+')" value="">' +
                '</td>' +
                '<td><input id="receiveVat' + row + '" name="receiveVat' + row + '" type="text" class="form-control text-right"></td>' +
//                '<td><input id="receiveGross' + row + '" name="receiveGross' + row + '" type="text" class="form-control text-right" onkeyup="insertCommas(this)"></td>' +
                '<td><input id="receiveAmount' + row + '" name="receiveAmount' + row + '" type="text" class="form-control text-right" onkeyup="insertCommas(this)"></td>' +
                '<td>' + 
                '<select class="form-control" name="receiveCurrency' + row + '" id="receiveCurrency' + row + '" ><option value="">---------</option></select>' +                          
                '</td>' +
                '<td class="text-center">' +
                '<a class="remCF" onclick="deletelist(\'\', \''+row+'\')">  '+
                '<span id="SpanRemove' + row + '"class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
                '</tr>'
            );
            $("#billTypeList option").clone().appendTo("#receiveProduct" + row);
            $("#currencyList option").clone().appendTo("#receiveCurrency" + row);
            
            $("#receiveAmount"+row).focusout(function(){
                calculatGross(row);
                setFormatCurrency(row);
            }); 
            $("#receiveGross"+row).focusout(function(){
                setFormatCurrency(row);
            }); 
            var tempCount = parseInt($("#counter").val()) + 1;
            $("#counter").val(tempCount);
//        }
      
    }
function handleClick(cb,row) {
  if(cb.checked){
    $("#receiveVat" + row).val($("#vatValue").val());
  }else{
    $("#receiveVat" + row).val("");
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
    
    var receiveGross = replaceAll(",","",$('#receiveGross'+row).val()); 
    if (receiveGross == ""){
        receiveGross = 0;
    }
    receiveGross = parseFloat(receiveGross); 
    document.getElementById("receiveGross"+row).value = formatNumber(receiveGross);

    if (receiveAmount == "" || receiveAmount == 0){
        document.getElementById("receiveAmount"+row).value = "";
    }
    
    if (receiveGross == "" || receiveGross == 0){
        document.getElementById("receiveGross"+row).value = "";
    }
}

function AddRowCredit(row) {
//        var idRole = '${idRole}';
//        if((idRole === '22') || (idRole === '1')){                  
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
            '<a class="remCF" onclick="deletelist(\'\', \''+row+'\')">  '+
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
                        
                    }else{
                        $("#InvoiceListTable tbody").append(msg);
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
        alert(e);
    }
}   

function invoicenoValidate(){
    $('#invoicenopanel').removeClass('has-feedback');
    $('#invoicenopanel').addClass('has-success');
    $('#invoicenopanel').removeClass('has-error');  
}

function addReceiveFrom(receiveid,receivefrom,name,address,arcode){
   document.getElementById("receiveFromId").value = receiveid;
   document.getElementById("receiveFromCode").value = receivefrom;
   document.getElementById("receiveFromName").value = name;
   document.getElementById("receiveFromAddress").value = address;
   document.getElementById("arCode").value = arcode;
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
}

</script>
