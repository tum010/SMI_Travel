<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/ReceiveTable.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="customerAgentInfoList" value="${requestScope['customerAgentInfoList']}" />
<c:set var="mAccpayList" value="${requestScope['mAccpayList']}" />
<c:set var="mCreditBankList" value="${requestScope['mCreditBankList']}" />
<c:set var="advanceReceiveList" value="${requestScope['advanceReceiveList']}" />
<c:set var="advanceReceive" value="${requestScope['advanceReceive']}" />
<c:set var="advanceReceiveCreditList" value="${requestScope['advanceReceiveCreditList']}" />

<input type="hidden" name="result" id="result" value="${requestScope['result']}">

<section class="content-header" >
    <h1>
        Finance & Cashier - Receive Table
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Receive Table</a></li>
    </ol>
</section>
<form action="ReceiveTable.smi" method="post" id="receiveForm" role="form" autocomplete="off">
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app="">  
    <div class="col-sm-12" style="padding-left: 50px;padding-right: 50px;">
        <!--Alert Save -->
        <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Success!</strong> 
        </div>
        <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Not Success!</strong> 
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                 <label class="control-label">Receive Table</lable>
            </div>
            <div class="panel-body">               
                <div class="row" style="padding-left: 0px">
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                            <label class="control-label">Date </lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="padding-left:5px">
                            <div class="col-sm-12">
                                <div class='input-group datesearch' style="width:140px;">
                                    <input name="InputDate" id="InputDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['inputDate']}" />
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                            <label class="control-label">Vat Type </lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right:0px;width:150px;">
                            <div class="col-sm-12">
                                <select name="SelectStatus" id="SelectStatus" class="form-control" onchange="checkSearch()">
                                    <c:set var="vat" value=""/>
                                    <c:if test="${requestScope['selectStatus'] == 'V'}">
                                        <c:set var="vat" value="selected"/>
                                    </c:if>
                                     <c:set var="temp" value=""/>
                                    <c:if test="${requestScope['selectStatus'] == 'T'}">
                                        <c:set var="temp" value="selected"/>
                                    </c:if>
                                    <option value=""></option>
                                    <option value="V" ${vat}>Vat</option>
                                    <option value="T" ${temp}>Temp</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-2 text-left" style="width: 100px;">
                            <a id="ButtonSearch" name="ButtonSearch" onclick="searchReceive()" class="btn btn-primary">
                                <i class="glyphicon glyphicon-search"></i> Search
                            </a>
                        </div>
                        <div class="col-xs-2 text-right" style="width: 100px;" >
                            <button type="button" id="ButtonPrint" name="ButtonPrint" class="btn btn-default">
                                <i class="fa fa-print"></i> Print             
                            </button>
                        </div>
                        <div class="col-xs-2 text-right" style="width: 390px;">
                            <a data-toggle="collapse" class="btn btn-success" href="#" aria-expanded="false" onclick="AddReceiveData()">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                    </div>   
                </div><!-- End Row 1--><br>                             
                <div class="row" style="padding-left: 25px;width: 100%;">
                    <table class="display" id="ReceiveTable">
                        <thead class="datatable-header">
                            <tr>
                                <th style="width: 10%">Receive Date</th>
                                <th style="width: 8%">A/R Code</th>
                                <th style="width: 20%">Receive Name</th>
                                <th style="width: 20%">Description</th>
                                <th style="width: 10%">Payment</th>
                                <th style="width: 12%">Receive Amount</th>
                                <th style="width: 8%">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="advanceReceive" items="${advanceReceiveList}" varStatus="i">
                            <tr>
                                <td align="center">${advanceReceive.recDate}</td>
                                <td align="center">${advanceReceive.arCode}</td>
                                <td>${advanceReceive.recTo}</td>
                                <td>${advanceReceive.description}</td>
                                <td align="center">${advanceReceive.MAccpay.name}</td>
                                <td align="right" class="money">${advanceReceive.recAmount}</td>
                                <td class="text-center">
                                    <a href="#" onclick=""  data-toggle="modal" data-target="">
                                        <span id="editSpan${i.count}" class="glyphicon glyphicon-edit editicon" onclick="editAdvanceReceiveConfirm('${advanceReceive.id}')" ></span>
                                    </a>
                                    <a href="#" onclick=""  data-toggle="modal" data-target="">
                                        <span id="removeSpan${i.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="deleteAdvanceReceiveConfirm('${advanceReceive.id}')" data-toggle="modal"></span>
                                    </a>                                 
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <br>
                <div class="panel panel-default hidden" id="receiveData">
                    <div class="panel-body">
                        <div class="tab-content" id="collapseExample" aria-expanded="false">
                        <div role="tabpanel" class="tab-pane hidden active" id="receive">
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1 form-group" style="width: 135px">
                                    <label class="control-label text-left">Receive Date<font style="color: red">*</font></lable>        
                                </div>
                                <div class="col-xs-1 form-group" style="width: 170px">
                                    <div class='input-group date' id='InputDatePicker'>
                                        <input name="receiveDate" id="receiveDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${advanceReceive.recDate}" />
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                                <div class="col-xs-1" style="width: 120px"></div>
                                <div class="col-xs-1" style="width: 100px">
                                    <label class="control-label text-left">Vat Type</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <select name="vatType" id="vatType" class="form-control">
                                        <c:set var="vat" value=""/>
                                        <c:if test="${advanceReceive.vatType == 'V'}">
                                            <c:set var="vat" value="selected"/>
                                        </c:if>
                                         <c:set var="temp" value=""/>
                                        <c:if test="${advanceReceive.vatType == 'T'}">
                                            <c:set var="temp" value="selected"/>
                                        </c:if>
                                        <option value=""></option>
                                        <option value="V" ${vat}>Vat</option>
                                        <option value="T" ${temp}>Temp</option>
                                    </select>    
                                </div>
                            </div>   
                        </div><!-- End Row 1--><br>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1 form-group" style="width: 135px">
                                    <label class="control-label text-left">Receive Name<font style="color: red">*</font></lable>        
                                </div>
                                <div class="col-xs-1 form-group" style="width: 170px">
                                    <div class="input-group">
                                        <input name="receiveId" id="receiveId" type="hidden" class="form-control" value="${advanceReceive.id}" />
                                        <input name="receiveCode" id="receiveCode" type="text" class="form-control" value="${advanceReceive.recTo}" />
                                        <span class="input-group-addon" id="receiveModal"  data-toggle="modal" data-target="#ReceiveModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>    
                                </div>
                                <div class="col-xs-1 form-group" style="width: 420px">
                                    <input name="receiveName" id="receiveName" type="text" class="form-control" value="${advanceReceive.recName}" />
                                </div>
                                <div class="col-xs-1 text-right" style="width: 170px">
                                    <label class="control-label">AR Code</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="receiveArCode" id="receiveArCode" type="text" class="form-control" value="${advanceReceive.arCode}" readonly=""/>
                                </div>
                            </div>
                        </div><!-- End Row 2--><br>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1" style="width: 135px">
                                    <label class="control-label text-left">Description</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 590px">
                                    <textarea name="description" id="description" class="form-control" rows="3">${advanceReceive.description}</textarea>
                                </div>
                                <div style="col-xs-1">
                                    <div class="col-xs-1 text-right" style="width: 170px;padding-top: 5px">
                                        <label class="control-label">Status<font style="color: red">*</font></lable>        
                                    </div>
                                    <div class="col-xs-1 form-group" style="width: 200px;padding-top: 5px">
                                        <select name="status" id="status" class="form-control">
                                            <option value=""></option>
                                            <c:forEach var="mAccpay" items="${mAccpayList}">                                       
                                                <c:set var="select" value="" />
                                                <c:if test="${mAccpay.id == advanceReceive.MAccpay.id}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option  value="${mAccpay.id}" ${select}>${mAccpay.name}</option>
                                            </c:forEach>                                            
                                        </select>    
                                    </div>                               
                                    <div class="col-xs-1 text-right" style="width: 895px">
                                        <label class="control-label text-left">Receive Amount<font style="color: red">*</font></lable>        
                                   </div>                               
                                    <div class="col-xs-1 form-group" style="width: 200px">
                                        <input name="receiveAmount" id="receiveAmount" type="text" class="form-control numerical" style="text-align:right;" value="${advanceReceive.recAmount}" onkeyup="insertCommas(this)" onfocusout="calculate(this)"/>
                                    </div>
                                </div>
                            </div>
                        </div><!-- End Row 3--><br>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1" style="width: 135px">
                                    <label class="control-label text-left">Cash Amount</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="cashAmount" id="cashAmount" type="text" class="form-control numerical" style="text-align:right;" value="${advanceReceive.cashAmount}" onkeyup="insertCommas(this)" onfocusout="calculate(this)"/>
                                </div>
                                <div class="col-xs-1" style="width: 60px"></div>
                                <div class="col-xs-1" style="width: 130px">
                                    <label class="control-label text-left">Bank Amount</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="bankAmount" id="bankAmount" type="text" class="form-control numerical" style="text-align:right;" value="${advanceReceive.bankAmount}" onkeyup="insertCommas(this)" onfocusout="calculate(this)"/>
                                </div>
                                <div class="col-xs-1" style="width: 35px"></div>
                                <div class="col-xs-1 text-right" style="width: 135px">
                                    <label class="control-label">Chq Amount</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="chqAmount" id="chqAmount" type="text" class="form-control numerical" style="text-align:right;" value="${advanceReceive.chqAmount}" onkeyup="insertCommas(this)" onfocusout="calculate(this)"/>
                                </div>
                            </div>
                        </div><!-- End Row 4--><br>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1" style="width: 135px">
                                    <label class="control-label text-left">Chq Bank</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="chqBank" id="chqBank" type="text" class="form-control numerical" style="text-align:right;" value="${advanceReceive.chqBank}" onkeyup="insertCommas(this)" onfocusout="calculate(this)"/>
                                </div>
                                <div class="col-xs-1" style="width: 60px"></div>
                                <div class="col-xs-1" style="width: 130px">
                                    <label class="control-label text-left">Chq Date</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <div class='input-group date'>
                                        <input name="chqDate" id="chqDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${advanceReceive.chqDate}" />
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                                <div class="col-xs-1" style="width: 55px"></div>
                                <div class="col-xs-1 text-left" style="width: 115px">
                                    <label class="control-label">Chq No</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="chqNo" id="chqNo" type="text" class="form-control numerical" style="text-align:right;" value="${advanceReceive.chqNo}" onkeyup="insertCommas(this)" onfocusout="calculate(this)"/>
                                </div>
                            </div>
                        </div><!-- End Row 5-->
                        <div class="col-sm-12"><br></div>
                        <input type="hidden" name="countCredit" id="countCredit" class="form-control" value="1"/>
                        <div class="row" style="padding-left: 15px;width: 100%;">
                            <table class="display" id="CreditTable">
                                <thead class="datatable-header">
                                    <tr>
                                        <th style="width: 10%">Credit Bank</th>
                                        <th style="width: 10%">Credit No</th>
                                        <th style="width: 10%">Credit Expire</th>
                                        <th style="width: 10%">Amount</th>
                                        <th style="width: 10%">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="adReCre" items="${advanceReceiveCreditList}" varStatus="i">   
                                    <tr>
                                        <td class="hidden"><input class="form-control" type="text" id="advanceReceiveCreditId${i.count}" name="advanceReceiveCreditId${i.count}" value="${adReCre.id}"></td>
                                        <td class="hidden"><input class="form-control" type="text" id="advanceReceiveId${i.count}" name="advanceReceiveId${i.count}" value="${adReCre.advanceReceive.id}"></td>
                                        <td>
                                            <select class="form-control" name="creditCard${i.count}" id="creditCard${i.count}" onchange="AddrowBySelect()">
                                                <option value=""></option>
                                                <c:forEach var="mCreditBank" items="${mCreditBankList}">
                                                    <c:set var="select" value="" />
                                                    <c:if test="${mCreditBank.id == adReCre.MCreditBank.id}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option  value="${mCreditBank.id}" ${select}>${mCreditBank.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td><input class="form-control" type="text" id="creditNo${i.count}" name="creditNo${i.count}" value="${adReCre.creditNo}"></td>
                                        <td>
                                            <div class="input-group daydatepicker" id="daydatepicker">
                                                <input name="creditExpire${i.count}" id="creditExpire${i.count}" type="text" class="form-control" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${adReCre.creditExpire}" />
                                                <span class="input-group-addon" onclick="AddrowBySelect()"><i class="glyphicon glyphicon-calendar"></i></span>
                                            </div>
                                        </td>
                                        <td><input class="form-control numerical" style="text-align:right;" type="text" id="creditAmount${i.count}" name="creditAmount${i.count}" value="${adReCre.creditAmount}" onkeyup="insertCommas(this)" onfocusout="calculate(this)"></td>
                                        <td>
                                            <center>
                                                <a id="expenButtonRemove${i.count}" name="expenButtonRemove${i.count}" onclick="deleteAdvanceReceiveCreditConfirm('${adReCre.id}')"  data-toggle="modal">
                                                <span id="expenSpanEdit${i.count}" name="expenSpanEdit${i.count}" class="glyphicon glyphicon-remove deleteicon"></span>
                                                </a>
                                            </center>
                                        </td>        
                                    </tr>
                                    </c:forEach> 
                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-12"><br></div>
                        <div class="row text-center" >
                            <div class="col-xs-5 text-right" ></div>                   
                            <div class="col-xs-2 text-left" style="width: 100px;">
                                <button type="submit" id="ButtonSave" name="ButtonSave" onclick="" class="btn btn-success">
                                    <i class="fa fa-save"></i> Save
                                </button>
                            </div> 
                            <div class="col-xs-2 text-right" style="width: 100px;" >
                                <button type="button" id="ButtonNew" name="ButtonNew" class="btn btn-primary">
                                    <i class="fa fa-plus"></i> New             
                                </button>
                            </div>
                            <div class="col-xs-2 text-right"  ></div>
                        </div>
                    </div>
                    </div>
                    </div>                        
                </div>                               
                <input type="hidden" name="action" id="action" value="save"/>
                <input type="hidden" name="createBy" id="createBy" value=""/>
                <input type="hidden" name="createDate" id="createDate" value=""/>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <label class="control-label">Total Receive</lable>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div id='TextBoxesGroup'>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Date</lable>
                                </div>  
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">
                                    <div class="col-sm-12">
                                        <div class='input-group' style="width:165px;">
                                            <input name="InputDateOne" id="InputDateOne" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" readonly=""/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-1" style="width: 80px"></div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:150px;">
                                    <label class="control-label">Cash Amount</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">
                                    <div class="col-sm-12">
                                        <input name="InputCashAmount" id="InputCashAmount" type="text" class="form-control money" value="" readonly=""/>
                                    </div>
                                </div>
                                <div class="col-sm-1" style="width: 80px"></div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Cash(--)</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">                                
                                    <div class="col-sm-12">
                                       <input name="InputCash" id="InputCash" type="text" class="form-control money" value="" readonly=""/>                              
                                    </div>
                                </div>                           
                            </div>   
                        </div>                      
                    </div><!-- End Text 1-->
                    <div id="TextBoxDiv2" >                    
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">                        
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Cheque</lable>
                                </div>  
                                <div class="col-md-2 form-group text-left" style="padding-left:20px;width:200px;">
                                    <input name="InputCheque" id="InputCheque" type="text" class="form-control money"  placeholder="" value="" readonly=""/>
                                </div>
                                <div class="col-sm-1" style="width: 80px"></div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:150px;">
                                    <label class="control-label">Bank Amount</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">
                                    <div class="col-sm-12">
                                        <input name="InputBankAmount" id="InputBankAmount" type="text" class="form-control money" value="" readonly=""/>
                                    </div>
                                </div>
                                <div class="col-sm-1" style="width: 80px"></div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Credit Card</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">                                
                                    <div class="col-sm-12">
                                       <input name="InputCreditCard" id="InputCreditCard" type="text" class="form-control money"  placeholder="" value="" readonly=""/>                               
                                    </div>
                                </div>                                                        
                            </div>   
                        </div><!--End Row 2 -->
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">  
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Detail</lable>
                                </div>  
                                <div class="col-md-2 form-group text-left" style="padding-left:20px;width:390px;">
                                    <textarea class="form-control" rows="3" id="commentDetail" name="commentDetail" readonly=""></textarea>
                                </div>                                                                               
                            </div>   
                        </div>         
                    </div>
                </div>                
            </div>
        </div>
    </div>                        
</div>
</form>                                            
<!--Modal Receive-->
<div class="modal fade" id="ReceiveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Receive</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="SearchReceiveTable">
                    <thead class="datatable-header">
                        <script>
                            var mAccpay = [];
                        </script>
                        <tr>
                            <th style="width: 30%">Code</th>
                            <th style="width: 70%">Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customerAgent" items="${customerAgentInfoList}">
                            <tr onclick ="setupCustomerAgentValue('${customerAgent.billTo}', '${customerAgent.billName}', '${customerAgent.address}')">
                                <td>${customerAgent.billTo}</td>
                                <td>${customerAgent.billName}</td>
                            </tr>
                            <script>
                                mAccpay.push({name: "${customerAgent.billTo}", billTo: "${customerAgent.billName}", address: "${customerAgent.address}"});
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
                                            
<!--Delete Receive Modal-->
<div class="modal fade" id="delReceiveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Receive Table</h4>
            </div>
            <div class="modal-body" id="delCode">Are you sure to delete this receive table ?</div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="deleteAdvanceReceive()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--MCredit Bank List-->
<select class="form-control hidden" name="select_bank_list" id="select_bank_list">
    <c:forEach var="mCreditBank" items="${mCreditBankList}">                                              
        <option  value="${mCreditBank.id}">${mCreditBank.name}</option>
    </c:forEach>
</select>

<script type="text/javascript">
    
</script>
<script type="text/javascript">
$(document).ready(function(){
    $("#addButton").click(function () {
        $('#TextBoxDiv2').show();
    });
    $("#removeButton").click(function () {
        $('#TextBoxDiv2').hide();
    });
  });
</script>
