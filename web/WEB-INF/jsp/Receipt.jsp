<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Receipt.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="dataPVList" value="${requestScope['PVList']}" />
<c:set var="Type" value="${requestScope['typeInvoice']}" />
<input type="hidden" id="Type" name="Type" value="${param.Type}">

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
                        <c:when test="${fn:contains(Type , 'outtemp')}">
                            <h4><b>Receipt Temp Wendy/Outbound</b></h4>
                        </c:when>
                        <c:when test="${fn:contains(Type , 'outvat')}">
                            <h4><b>Receipt Vat Wendy/Outbound</b></h4>
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
                                        <div class="col-xs-1 form-group" style="width: 200px">
                                            <div class="input-group">
                                                <input id="invoiceNo" name="invoiceNo" type="text" class="form-control" value="">
                                            </div>
                                        </div>
                                        <div class="col-xs-1 text-left"  style="width: 100px">
                                            <button style="height:30px" type="submit"  id="searchInvoice"  name="searchInvoice" onclick="searchInvoice();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search </button>
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
                            <div class="col-xs-8 form-group" style="padding-top: 0px;">
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Receive No </label>                                    
                                    </div>
                                    <div class="col-xs-1 form-group" style="width: 140px">
                                        <input id="receiveNo" name="receiveNo" type="text" class="form-control" value="">
                                    </div>
                                    <div class="col-xs-1 text-left" style="width: 200px">
                                        <label class="control-label">
                                            <input type="checkbox" id="groupCheck" name="groupCheck"/>&nbsp;Group Yes/No&nbsp;  
                                        </label>
                                        <input type="hidden" id="groupCheckbox" value=""/>
                                    </div>
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
                                </div>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right" for="codeBillto">Receive From <font style="color: red">*</font></label> 
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 580px">
                                        <div class="input-group" id="receiveFromValidate">
                                            <input type="hidden" class="form-control" id="receiveFromId" name="receiveFromId" value="${Selected.id}">                           
                                            <input type="text" class="form-control" id="receiveFromCode" name="receiveFromCode" value="${Selected.code}" />
                                            <span class="input-group-addon" id="receive_modal"  data-toggle="modal" data-target="#ReceiveFromModal">
                                                <span class="glyphicon-search glyphicon"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>  
                                <div class="row" style="padding-top: 16px;">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                         <label class="control-label text-right">Name </label> 
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 580px">
                                        <input type="text" class="form-control" id="receiveFromName" name="receiveFromName" value="${Selected.name}">                           
                                    </div>
                                </div>  
                                <div class="row" style="padding-top: 16px;">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                         <label class="control-label text-right">Address </label> 
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 580px">
                                        <div class="input-group">                                    
                                            <textarea rows="3" class="form-control" id="billToAddress" name="billToAddress" style="width: 339%"></textarea>  
                                        </div>                               
                                    </div>
                                </div>  
                                <div class="row" style="padding-top: 16px;">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                         <label class="control-label text-right">Remark </label> 
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 580px">
                                        <input type="text" class="form-control" id="remark" name="remark" value="">                           
                                    </div>
                                </div>  
                            </div>
                            <div class="col-xs-4 form-group" style="padding-top: 0px;">
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Receive Date </label>
                                    </div>
                                    <div class="col-xs-1 form-group" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="inputReceiveDate" name="inputReceiveDate"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>  
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Confirm Date </label>
                                    </div>
                                    <div class="col-xs-1 form-group" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="inputConfirmDate" name="inputConfirmDate"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>  
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Status </label>
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <select id="inputStatus" name="inputStatus" class="form-control selectize">
                                            <option value="">---Status---</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="row" style="padding-top: 15px;">
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">A/R Code </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <div class="input-group" id="arCodeValidate">
                                            <input type="text" class="form-control" id="arCode" name="arCode" value="${Selected.code}" />
                                            <span class="input-group-addon" id="arcode_modal"  data-toggle="modal" data-target="#ARCodeModal">
                                                <span class="glyphicon-search glyphicon"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="row" style="padding-top: 15px;">
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Credit No </label>
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <input type="text" class="form-control" id="creditNo" name="creditNo" value="" />
                                    </div>
                                </div>
                                            
                            </div>
                        </div>
                        
                        <!---Table-->
                        <div class="row" style="padding-right: 10px;padding-left: 10px;padding-bottom:  10px;">
                            <div class="col-md-12 ">
                                <table id="ReceiptListTable" class="display" cellspacing="0" width="100%">
                                    <thead>
                                        <tr class="datatable-header" >
                                            <th style="width:5%;"></th>
                                            <th style="width:7%;">Description</th>
                                            <th style="width:7%;">T/C</th>
                                            <th style="width:10%;">Cost</th>
                                            <th style="width:10%;">Vat</th>
                                            <th style="width:10%;">Amount</th>
                                            <th style="width:7%;">Total Amount</th>
                                            <th style="width:7%;">Remarks</th>
                                            <th style="width:5%;">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><center>111111</center></td>
                                            <td><center>222222</center></td>
                                            <td><center>333333</center></td>
                                            <td><center>444444</center></td>
                                            <td><center>555555</center></td>
                                            <td><center>666666</center></td>
                                            <td><center>777777</center></td>
                                            <td><center>888888</center></td>
                                            <td>
                                                <center>
                                                    <span id="removeSpan${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteAir('${table.id}', '${table.code}')" data-toggle="modal" data-target="#delReceiptModal"></span>
                                                </center>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>      
                            </div>
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
                                        <input id="inputWt" name="inputWt" type="text" class="form-control" value="">
                                    </div>
                                </div><hr/>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Cash Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 200px">
                                        <input id="cashAmount" name="cashAmount" type="text" class="form-control" value="">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 160px">
                                        <label class="control-label text-right">Cash - Amount</label>                                    
                                    </div>
                                </div><hr/>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Bank Transfer </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 200px">
                                        <input id="bankTransfer" name="bankTransfer" type="text" class="form-control" value="">
                                    </div>
                                </div><hr/>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Chq Bank </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <input style="width: 130px" id="chqBank" name="chqBank" type="text" class="form-control" value="">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Chq No </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input style="width: 115px" id="chqNo" name="chqNo" type="text" class="form-control" value="">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Date </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="chqDate" name="chqDate"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 100px">
                                        <label class="control-label text-right">Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input id="chqAmount" name="chqAmount" type="text" class="form-control" value="">
                                        
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
                                        <input style="width: 130px" id="chqBank2" name="chqBank2" type="text" class="form-control" value="">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Chq No </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input  style="width: 115px" id="chqNo2" name="chqNo2" type="text" class="form-control" value="">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Date </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="chqDate2" name="chqDate2"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 100px">
                                        <label class="control-label text-right">Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input id="chqAmount2" name="chqAmount2" type="text" class="form-control" value="">
                                    </div>
                                    <div class="col-xs-1" style="width: 50px ;">
                                        <h4><a class="col-xs-1">
                                        <span class="glyphicon glyphicon-remove deleteicon" id="deleteChqButton"></span>
                                        </a></h4>                                        
                                    </div>
                                </div>
                                <hr/>
                                <div class="row">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Credit Bank </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <select style="width: 130px" id="creditBank" name="creditBank" class="form-control selectize">
                                                    <option value=""> Credit Bank </option>
                                        </select>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Credit No </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input style="width: 115px" id="creditNo" name="creditNo" type="text" class="form-control" value="">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Credit Expire </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="creditExpire" name="creditExpire"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 100px">
                                        <label class="control-label text-right">Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input id="creditAmount" name="creditAmount" type="text" class="form-control" value="">
                                    </div>
                                    <div class="col-xs-1 text-left" style="width: 50px ;">
                                        <h4><a class="col-xs-1">
                                        <span class="glyphicon glyphicon-plus-sign" id="addCreditButton"></span>
                                        </a></h4>                                        
                                    </div>
                                </div>
                                <div class="row hidden active" id="addCredit" style="padding-top: 8px ">
                                    <div class="col-xs-1 text-right" style="width: 140px">
                                        <label class="control-label text-right">Credit Bank </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 130px">
                                        <select style="width: 130px" id="creditBank2" name="creditBank2" class="form-control selectize">
                                                    <option value=""> Credit Bank </option>
                                        </select>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 110px">
                                        <label class="control-label text-right">Credit No </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input style="width: 115px" id="creditNo2" name="creditNo2" type="text" class="form-control" value="">
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 130px">
                                        <label class="control-label text-right">Credit Expire </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 170px">
                                        <div class='input-group date'>
                                            <input id="creditExpire2" name="creditExpire2"  type="text" 
                                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                    <div class="col-xs-1 text-right" style="width: 100px">
                                        <label class="control-label text-right">Amount </label>                                    
                                    </div>
                                    <div class="col-xs-1" style="width: 120px">
                                        <input id="creditAmount2" name="creditAmount2" type="text" class="form-control" value="">
                                    </div>
                                    <div class="col-xs-1" style="width: 50px ;">
                                        <h4><a class="col-xs-1">
                                        <span class="glyphicon glyphicon-remove deleteicon" id="deleteCreditButton"></span>
                                        </a></h4>                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-body"  style="padding-right: 0px;">
                            <div class="col-xs-12 form-group">
                                <div class="col-md-1 text-left" style="width: 150px">
                                    <button type="button" class="btn btn-default">
                                        <span id="buttonPrintReceipt" class="glyphicon glyphicon-print"></span> Print Receipt
                                    </button>
                                </div>
                                <div class="col-md-1 text-left " style="width: 150px">
                                    <button type="button" class="btn btn-default">
                                        <span id="buttonPrintInvoice" class="glyphicon glyphicon-print"></span> Print Invoice
                                    </button>
                                </div>
                                <div class="col-md-1 text-left " style="width: 150px">
                                    <button type="button" class="btn btn-default" onclick="printReceiptNew('')">
                                        <span id="buttonPrintReceiptNew" class="glyphicon glyphicon-print"></span> Print Receipt New
                                    </button>
                                </div>
                                <div class="col-md-2 text-right "></div>
                                <div class="col-md-1 text-right " style="width: 100px">
                                    <button type="button" class="btn btn-default" onclick="printReceipt('')">
                                        <span id="buttonPrint" class="glyphicon glyphicon-print"></span> Print 
                                    </button>
                                </div>
                                <div class="col-md-1 text-right " style="width: 100px">
                                    <button type="button" class="btn btn-primary hidden" onclick="EnableVoid();" data-toggle="modal" data-target="#EnableVoid">
                                        <span id="buttonEnableVoid" class="glyphicon glyphicon-ok" ></span> Void
                                    </button>
                                    <button type="button" class="btn btn-danger" onclick="DisableVoid();" data-toggle="modal" data-target="#DisableVoid">
                                        <span id="buttonDisableVoid" class="glyphicon glyphicon-remove" ></span> Void
                                    </button>
                                </div>
                                <div class="col-md-1 text-right " style="width: 100px">
                                    <button type="button" onclick="" class="btn btn-success">
                                        <span id="buttonSave" class="fa fa-save"></span> Save 
                                    </button>
                                </div>
                                <div class="col-md-1 text-right " style="width: 100px">
                                    <button type="button" onclick="" class="btn btn-success">
                                        <span id="buttonNew" class="fa fa-plus-circle"></span> New 
                                    </button>
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
                <h4 class="modal-title"  id="Titlemodel">Receive From</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="ListReceiveFromTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden"></th>
                            <th class="hidden"></th>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="ListReceiveFromModalOK" name="ListReceiveFromModalOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="ListReceiveFromModalClose" name="ListReceiveFromModalClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->

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
        
//        $('#ReceiptForm').bootstrapValidator({
//            container: 'tooltip',
//            excluded: [':disabled'],
//            feedbackIcons: {
//                valid: 'uk-icon-check',
//                invalid: 'uk-icon-times',
//                validating: 'uk-icon-refresh'
//            },
//            fields: {
//                receiveFromCode: {
//                    validators: {
//                        notEmpty: {
//                            message: 'The Code is required'
//                        }
//                    }
//                }       
//            }
//        }).on('success.field.bv', function (e, data) {
//            if (data.bv.isValid()) {
//                data.bv.disableSubmitButtons(false);
//            }
//        });
            
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

    });
    
    function printReceiptNew() {
        window.open("report.smi?name=ReceiptEmail");
    }
    
    function printReceipt() {
        window.open("report.smi?name=ReceiptReport");
    }

</script>
