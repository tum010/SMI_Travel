<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/ReceiveTable.js"></script> 
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="css/jquery-ui.css" rel="stylesheet">
<c:set var="customerAgentInfoList" value="${requestScope['customerAgentInfoList']}" />
<c:set var="mAccpayList" value="${requestScope['mAccpayList']}" />
<c:set var="mCreditBankList" value="${requestScope['mCreditBankList']}" />
<c:set var="advanceReceiveList" value="${requestScope['advanceReceiveList']}" />
<c:set var="advanceReceive" value="${requestScope['advanceReceive']}" />
<c:set var="advanceReceiveCreditList" value="${requestScope['advanceReceiveCreditList']}" />
<c:set var="advanceReceivePeriod" value="${requestScope['advanceReceivePeriod']}" />
<c:set var="advanceReceivePeriodView" value="${requestScope['advanceReceivePeriodView']}" />
<c:set var="department" value="${requestScope['department']}" />
<c:set var="advanceReceivePeriodList" value="${requestScope['advanceReceivePeriodList']}" />

<input type="hidden" name="result" id="result" value="${requestScope['result']}">
<input type="hidden" name="periodMessage" id="periodMessage" value="${requestScope['periodMessage']}">

<section class="content-header" >
    <h1>
        <c:set var="panelheader" value=""/>
        <c:set var="panelborder" value=""/>
        <c:set var="tableheader" value=""/>
        <c:set var="tableborder" value=""/>
        <c:set var="fontcolor" value="white"/>
        <c:set var="page" value=""/>
        <c:set var="underLineWO1" value=""/>
        <c:set var="underLineWO2" value=""/>
        <c:set var="underLineW1" value=""/>
        <c:set var="underLineW2" value=""/>
        <c:set var="underLineO1" value=""/>
        <c:set var="underLineO2" value=""/>
        <c:set var="underLineI1" value=""/>
        <c:set var="underLineI2" value=""/>
        <c:choose>
            <c:when test="${department == 'W'}">
                <c:set var="page" value="Wendy"/>
                <c:set var="panelheader" value="wendyheader"/>
                <c:set var="panelborder" value="wendyborder"/>
                <c:set var="tableheader" value="#FFC07B"/>
                <c:set var="tableborder" value="#FFC07B"/>
                <c:set var="underLineW1" value="<u>"/>
                <c:set var="underLineW2" value="</u>"/>
            </c:when>
            <c:when test="${department == 'O'}">
                <c:set var="page" value="Outbound"/>
                <c:set var="panelheader" value="outboundheader"/>
                <c:set var="panelborder" value="outboundborder"/>
                <c:set var="tableheader" value="#FF8003"/>
                <c:set var="tableborder" value="#FF8003"/>
                <c:set var="underLineO1" value="<u>"/>
                <c:set var="underLineO2" value="</u>"/>
            </c:when>
            <c:when test="${department == 'I'}">
                <c:set var="page" value="Inbound"/>
                <c:set var="panelheader" value="inboundborderheader"/>
                <c:set var="panelborder" value="inboundborder"/>
                <c:set var="tableheader" value="#11BF00"/>
                <c:set var="tableborder" value="#11BF00"/>
                <c:set var="underLineI1" value="<u>"/>
                <c:set var="underLineI2" value="</u>"/>
            </c:when>
            <c:when test="${department == 'WO'}">
                <c:set var="page" value="Wendy and Outbound"/>
                <c:set var="panelheader" value=""/>
                <c:set var="panelborder" value=""/>
                <c:set var="tableheader" value=""/>
                <c:set var="tableborder" value=""/>
                <c:set var="underLineWO1" value="<u>"/>
                <c:set var="underLineWO2" value="</u>"/>
                <c:set var="fontcolor" value="black"/>
            </c:when>
        </c:choose>
        Finance & Cashier - Receive Table ${page}
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Receive Table</a></li>
    </ol>
</section>
<form action="ReceiveTable${department}.smi" method="post" id="receiveForm" role="form" autocomplete="off">  
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app="">  
    <div class="col-xs-12" style="margin: 5px 0px 5px 0px;">
        <div class="col-xs-1 text-right" style="width: 170px;">
            ${underLineWO1}<a href="ReceiveTableWO.smi" id="menu-wendyoutbound" ><b style="color: #FFC07B;">Wendy</b>+<b style="color: #FF8003;">Outbound</b></a>${underLineWO2}       
        </div>
        <div class="col-xs-1" style="width: 80px;">        
            ${underLineW1}<a href="ReceiveTableW.smi" id="menu-wendyoutbound" style="color: #FFC07B;"><b>Wendy</b></a>${underLineW2}
        </div>
        <div class="col-xs-1" style="width: 100px;">
            ${underLineO1}<a href="ReceiveTableO.smi" id="menu-wendyoutbound" style="color: #FF8003;"><b>Outbound</b></a>${underLineO1}        
        </div>
        <div class="col-xs-1" style="width: 80px;">
            ${underLineI1}<a href="ReceiveTableI.smi" id="menu-wendyoutbound" style="color: #11BF00;"><b>Inbound</b></a>${underLineI1}       
        </div>   
    </div>  
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
        <div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Delete Success!</strong> 
        </div>
        <div id="textAlertDivNotDelete"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Delete Not Success!</strong> 
        </div>       
        <div class="panel panel-default ${panelborder}">
            <div class="panel-body">               
                <div class="row" style="padding-left: 0px">
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                            <label class="control-label">Date </lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="padding-left:5px">
                            <div class="col-sm-12">
                                <div class='input-group datesearch' style="width:140px;">
                                    <input name="InputDate" id="InputDate" type="text" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${requestScope['inputDate']}" />
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
                            <button type="button" id="ButtonPrint" name="ButtonPrint" onclick="printReceiveTableReport()" class="btn btn-default">
                                <i class="fa fa-print"></i> Print             
                            </button>
                        </div>       
                        <div class="col-xs-2 text-right" style="width: 305px;" >
                            <button type="button" id="ButtonNew" name="ButtonNew" class="btn btn-primary" onclick="newReceiveTable()">
                                <i class="fa fa-plus"></i> New             
                            </button>
                        </div>        
                        <div class="col-xs-2" style="width: 50px;padding: 0px 0px 0px 0px;">
                            <button type="button" id="ButtonAdd" name="ButtonAdd" class="btn btn-success" onclick="AddReceiveData()">
                                <i class="glyphicon glyphicon-plus" id="addIcon"></i> Add             
                            </button>
                        </div>
                    </div>   
                </div><!-- End Row 1-->               
                <c:set var="receiveTable" value=""/>
                <c:if test="${advanceReceiveList == null}">
                   <c:set var="receiveTable" value="hidden"/>
                </c:if>
                <div class="row ${receiveTable}" style="padding-left: 25px; width: 100%">
                    <table class="display" id="ReceiveTable" >
                        <thead class="datatable-header">
                            <tr>
                                <th style="width: 10%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">Receive Date</font></th>
                                <th style="width: 8%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">A/R Code</font></th>
                                <th style="width: 20%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">Receive Name</font></th>
                                <th style="width: 20%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">Description</font></th>
                                <th style="width: 10%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">Payment</font></th>
                                <th style="width: 12%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">Receive Amount</font></th>
                                <th style="width: 8%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">Action</font></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="advanceReceive" items="${advanceReceiveList}" varStatus="i">
                            <tr>                               
                                <fmt:formatDate value="${advanceReceive.recDate}" var="recDate" pattern="dd-MM-yyyy" />
                                <td align="center">${recDate}</td>
                                <td align="center">${advanceReceive.arCode}</td>
                                <td>${advanceReceive.recName}</td>
                                <td>${advanceReceive.description}</td>
                                <td align="center">${advanceReceive.MAccpay.name}</td>
                                <td align="right" class="money">${advanceReceive.recAmount}</td>
                                <td class="text-center">
                                    <a href="#" onclick=""  data-toggle="modal" data-target="">
                                        <span id="editSpan${i.count}" class="glyphicon glyphicon-edit editicon" onclick="editAdvanceReceive('${advanceReceive.id}')" ></span>
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
                <div class="panel panel-default hidden ${panelborder}" id="receiveData" >
                    <div class="panel-body">
                        <div class="tab-content" id="collapseExample" aria-expanded="false">
                        <div role="tabpanel" class="tab-pane hidden active" id="receive">
                        <div class="row" style="padding-left: 0px;">
                            <!--<div class="col-xs-12 " style="margin-top: -10px;">-->
                                <div class="col-xs-1 form-group" style="width: 135px">
                                    <label class="control-label text-left">Receive Date<font style="color: red">*</font></lable>        
                                </div>
                                <div class="col-xs-1 form-group" style="width: 170px">
                                    <fmt:formatDate value="${advanceReceive.recDate}" var="recDate" pattern="dd-MM-yyyy" />
                                    <div class='input-group date' id='InputDatePicker'>
                                        <input name="receiveDate" id="receiveDate" type="text" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${recDate}" />
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
                                <div class="col-xs-1 text-right" style="width: 170px;">
                                    <label class="control-label">Status<font style="color: red">*</font></lable>        
                                </div>
                                <div class="col-xs-1 form-group" style="width: 200px;">
                                    <select name="status" id="status" class="form-control" onchange="setStatusFormat()">
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
                            <!--</div>-->   
                        </div><!-- End Row 1-->
                        <div class="row" style="padding-left: 0px">
                            <!--<div class="col-xs-12 " style="margin-top: -10px;">-->
                                <div class="col-xs-1 form-group hidden" style="margin-top: -10px;">
                                    <input name="receiveId" id="receiveId" type="hidden" class="form-control" value="${advanceReceive.id}" />
                                    <input name="receiveCreditId" id="receiveCreditId" type="hidden" class="form-control" value="" />
                                    <input name="receiveCreditRow" id="receiveCreditRow" type="hidden" class="form-control" value="" />
                                    <input name="department" id="department" type="hidden" class="form-control" value="${department}" />
                                    <input name="creditAmount" id="creditAmount" type="hidden" class="form-control" value="0" />
                                </div>
                                <div class="col-xs-1 form-group" style="width: 135px; margin-top: -10px;">
                                    <label class="control-label text-left">Receive Name<font style="color: red">*</font></lable>        
                                </div>
                                <div class="col-xs-1 form-group hidden" style="width: 170px; margin-top: -10px;">
                                    <div class="input-group">                                 
                                        <input name="receiveCode" id="receiveCode" type="text" class="form-control" value="${advanceReceive.recTo}" />
                                        <span class="input-group-addon" id="receiveModal"  data-toggle="modal" data-target="#ReceiveModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>    
                                </div>                                      
                                <div class="col-xs-1 " style="width: 290px; margin-top: -10px;">
                                    <div class="col-xs-1" style="width: 250px; padding-left: 0px;">
                                        <input name="receiveName" id="receiveName" type="text" class="form-control" style="text-transform: uppercase;" value="${advanceReceive.recName}" maxlength="100"/>                                  
                                    </div>
                                    <div class="col-xs-1" style="width: 10px; padding-left: 0px; padding-right: 0px; padding-top: 5px;">
                                        <i id="ajaxload" class="fa fa-spinner fa-spin hidden"></i>
                                    </div>
                                </div>        
                                <div class="col-xs-1 text-left" style="width: 100px; margin-top: -10px;">
                                    <label class="control-label">AR Code</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px; margin-top: -10px;">
                                    <input name="receiveArCode" id="receiveArCode" type="text" class="form-control" value="${advanceReceive.arCode}" readonly="" maxlength="20"/>
                                </div>                                                                
                                <div class="col-xs-1 text-right" style="width: 170px; margin-top: -10px;">
                                   <label class="control-label text-left">Receive Amount<font style="color: red">*</font></lable>        
                                </div>                               
                                <div class="col-xs-1 form-group" style="width: 200px; margin-top: -10px;">
                                    <input name="receiveAmount" id="receiveAmount" type="text" class="form-control decimal" value="${advanceReceive.recAmount}"/>
                                </div>
                            <!--</div>-->
                        </div><!-- End Row 2-->
                        <div class="row" style="padding-left: 0px">
                            <!--<div class="col-xs-12 " style="margin-top: -10px;">-->
                                <div class="col-xs-1" style="width: 135px; margin-top: -10px;">
                                    <label class="control-label text-left">Description</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 590px; margin-top: -10px;">
                                    <textarea name="description" id="description" class="form-control" rows="3" maxlength="255">${advanceReceive.description}</textarea>
                                </div>
                                <div class="col-xs-1 text-right" style="width: 170px; margin-top: -10px;">
                                    <label class="control-label ">Wht</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px; margin-top: -10px;">
                                    <input name="wht" id="wht" type="text" class="form-control decimal" value="${advanceReceive.wht}" />
                                </div>
                            <!--</div>-->
                        </div><!-- End Row 3--><br>
                        <div class="row" style="padding-left: 0px">
                            <!--<div class="col-xs-12 " style="margin-top: -15px;">-->                                                                 
                                <div class="col-xs-1" style="width: 135px; margin-top: -15px;">
                                    <label class="control-label text-left">Cash Amount</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px; margin-top: -15px;" id="cashAmountPanel">
                                    <input name="cashAmount" id="cashAmount" type="text" class="form-control decimal" value="${advanceReceive.cashAmount}" />
                                </div>
                                <div class="col-xs-1" style="width: 60px"></div>
                                <div class="col-xs-1" style="width: 130px; margin-top: -15px;">
                                    <label class="control-label text-left">Bank Amount</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px; margin-top: -15px;" id="bankAmountPanel">
                                    <input name="bankAmount" id="bankAmount" type="text" class="form-control decimal" value="${advanceReceive.bankAmount}"/>
                                </div>
                                <div class="col-xs-1" style="width: 35px"></div>
                                <div class="col-xs-1 text-right" style="width: 135px; margin-top: -15px;">
                                    <label class="control-label">Chq Amount</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px; margin-top: -15px;" id="chqAmountPanel">
                                    <input name="chqAmount" id="chqAmount" type="text" class="form-control decimal" value="${advanceReceive.chqAmount}"/>
                                </div>
                            <!--</div>-->
                        </div><!-- End Row 4--><br>
                        <div class="row" style="padding-left: 0px">
                            <!--<div class="col-xs-12 " style="margin-top: -15px;">-->
                                <div class="col-xs-1" style="width: 135px; margin-top: -15px;">
                                    <label class="control-label text-left">Chq Bank</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px; margin-top: -15px;">
                                    <input name="chqBank" id="chqBank" type="text" maxlength="50" class="form-control" value="${advanceReceive.chqBank}" />
                                </div>
                                <div class="col-xs-1" style="width: 60px"></div>
                                <div class="col-xs-1" style="width: 130px; margin-top: -15px;">
                                    <label class="control-label text-left">Chq Date</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px; margin-top: -15px;">
                                    <fmt:formatDate value="${advanceReceive.chqDate}" var="chqDate" pattern="dd-MM-yyyy" />
                                    <div class='input-group date'>
                                        <input name="chqDate" id="chqDate" type="text" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${chqDate}" />
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                                <div class="col-xs-1" style="width: 55px"></div>
                                <div class="col-xs-1 text-left" style="width: 115px; margin-top: -15px;">
                                    <label class="control-label">Chq No</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px; margin-top: -15px;">
                                    <input name="chqNo" id="chqNo" type="text" maxlength="100" class="form-control" value="${advanceReceive.chqNo}" />
                                </div>
                            <!--</div>-->
                        </div><!-- End Row 5-->
                        <div class="col-sm-12"><br></div>
                        <input type="hidden" name="countCredit" id="countCredit" class="form-control" value="${advanceReceiveCreditList.size()}"/>
                        <div class="row" style="padding-left: 15px;width: 100%;">
                            <table class="display" id="CreditTable">
                                <thead class="datatable-header">
                                    <tr>
                                        <th style="width: 10%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">Credit Bank</font></th>
                                        <th style="width: 20%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">Credit No</font></th>
                                        <th style="width: 9%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">Credit Expire</font></th>
                                        <th style="width: 10%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">Amount</font></th>
                                        <th style="width: 1%" bgcolor="${tableheader}"><font style="color: ${fontcolor}">Action</font></th>
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
                                        <td><input class="form-control" type="text" id="creditNo${i.count}" name="creditNo${i.count}" value="${adReCre.creditNo}" maxlength="20"></td>
                                        <td>
                                            <fmt:formatDate value="${adReCre.creditExpire}" var="creditExpire" pattern="dd-MM-yyyy" />
                                            <div class="input-group daydatepicker" id="daydatepicker${i.count}">
                                                <input type="text" name="creditExpire${i.count}" id="creditExpire${i.count}" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${creditExpire}" />
                                                <span class="input-group-addon spandate" style="padding : 1px 10px;"><span class="glyphicon-calendar glyphicon"></span></span>
                                            </div>
                                        </td>
                                        <td><input class="form-control" style="text-align:right;" type="text" id="creditAmount${i.count}" name="creditAmount${i.count}" value="${adReCre.creditAmount}" onfocusout="calculateCreditAmount(); setCreditAmount(); setCashOnDemand();"></td>
                                        <td>
                                            <center>
                                                <a id="expenButtonRemove${i.count}" name="expenButtonRemove${i.count}" onclick="deleteAdvanceReceiveCreditConfirm('${adReCre.id}','${i.count}')"  data-toggle="modal">
                                                <span id="expenSpanEdit${i.count}" name="expenSpanEdit${i.count}" class="glyphicon glyphicon-remove deleteicon"></span>
                                                </a>
                                            </center>
                                        </td>        
                                    </tr>
                                    </c:forEach> 
                                </tbody>
                            </table>
                            <div id="tr_CreditTableAddRow" class="text-center hide" style="padding-top: 10px">
                                <a class="btn btn-success" onclick="addNewRowCreditTable()">
                                    <i class="glyphicon glyphicon-plus"></i> Add
                                </a>
                            </div>
                        </div>
                        <div class="col-sm-12"><br></div>
                        <div class="row text-center" >                 
                            <div class="col-xs-12 text-center">
                                <button type="submit" id="ButtonSave" name="ButtonSave" onclick="" class="btn btn-success">
                                    <i class="fa fa-save"></i> Save
                                </button>
                            </div>                           
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
        <div id="textAlertDivSavePeriod"  style="display:none;" class="alert alert-success">
            <button type="button" class="close" aria-label="Close" onclick="hideTextAlertDivSavePeriod()"><span aria-hidden="true">&times;</span></button>
            <strong>Save Period Success!</strong> 
        </div>
        <div id="textAlertDivNotSavePeriod"  style="display:none;" class="alert alert-danger">
            <button type="button" class="close" aria-label="Close" onclick="hideTextAlertDivNotSavePeriod()"><span aria-hidden="true">&times;</span></button>
            <strong>Period has already used!</strong> 
        </div>
        <div id="textAlertDivDeletePeriod"  style="display:none;" class="alert alert-success">
            <button type="button" class="close" aria-label="Close" onclick="hideTextAlertDivDeletePeriod()"><span aria-hidden="true">&times;</span></button>
            <strong>Delete Period Success!</strong> 
        </div>
        <div id="textAlertDivPeriodMeaasge"  style="display:none;" class="alert alert-danger">
            <button type="button" class="close" aria-label="Close" onclick="hideTextAlertPeriodMessage()"><span aria-hidden="true">&times;</span></button>
            <strong id="periodAlertMessage"></strong>
            <button type="button" id="btnUpdate" name="btnUpdate" onclick="updateReceivePeriod()" class="btn btn-xs btn-warning">
                <i class="glyphicon glyphicon-usd"></i> Update            
            </button>
        </div>
        <div id="textAlertDivUpdatePeriod"  style="display:none;" class="alert alert-success">
            <button type="button" class="close" aria-label="Close" onclick="hideTextAlertDivUpdatePeriod()"><span aria-hidden="true">&times;</span></button>
            <strong>Update Period Success!</strong> 
        </div>
        <div class="panel panel-default ${panelborder}">
            <div class="panel-heading ${panelheader}">
                    <h4 class="panel-title"><font style="color: ${fontcolor}">Total Receive </font><i id="ajaxPeriod" class="fa fa-spinner fa-spin hidden"></i></h4>
                </div>
            <div class="panel-body">
                <div class="row">
                    <div id='TextBoxesGroup'>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 " style="margin-top: -10px;">
                                <input name="periodId" id="periodId" type="hidden" class="form-control" value="${advanceReceivePeriod.id}" />
                                <input name="receiveFrom" id="receiveFrom" type="hidden" class="form-control" value="${advanceReceivePeriod.receiveFrom}" />
                                <input name="receiveTo" id="receiveTo" type="hidden" class="form-control" value="${advanceReceivePeriod.receiveTo}" />
                                <input name="receiveCashAmount" id="receiveCashAmount" type="hidden" class="form-control" value="${advanceReceivePeriod.cashAmount}" />
                                <input name="receiveCash" id="receiveCash" type="hidden" class="form-control" value="${advanceReceivePeriod.cashMinusAmount}" />
                                <input name="receiveCheque" id="receiveCheque" type="hidden" class="form-control" value="${advanceReceivePeriod.chqAmount}" />
                                <input name="receiveBankAmount" id="receiveBankAmount" type="hidden" class="form-control" value="${advanceReceivePeriod.bankTransfer}" />
                                <input name="receiveCreditCard" id="receiveCreditCard" type="hidden" class="form-control" value="${advanceReceivePeriod.creditAmount}" />
                                <input name="receiveDetail" id="receiveDetail" type="hidden" class="form-control" value="${advanceReceivePeriod.detail}" />
                                <input name="receiveVatType" id="receiveVatType" type="hidden" class="form-control" value="${requestScope['selectStatus']}" />
                                <div class="col-xs-1 " style="width:30px;"></div>
                                <div class="col-xs-1 " style="width:125px;">
                                    <label class="control-label">From</lable>
                                </div>  
                                <div class="col-xs-1 form-group" style="width: 180px">
                                    <fmt:formatDate value="${advanceReceivePeriod.receiveFrom}" var="fromDate" pattern="dd-MM-yyyy" />
                                    <div class='input-group date fromdate' id="fromdatepanel">
                                        <input name="fromDate" id="fromDate" type="text" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${fromDate}" onclick="hideTextAlertDivSavePeriod(); hideTextAlertDivNotSavePeriod();">
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                                <div class="col-xs-1 " style="width:30px;"></div>
                                <div class="col-xs-1 " style="width:115px;">
                                    <label class="control-label">To</lable>
                                </div>  
                                <div class="col-xs-1 form-group" style="width: 180px">
                                    <fmt:formatDate value="${advanceReceivePeriod.receiveTo}" var="toDate" pattern="dd-MM-yyyy" />
                                    <div class='input-group date todate' id="todatepanel">
                                        <input name="toDate" id="toDate" type="text" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${toDate}" onclick="hideTextAlertDivSavePeriod(); hideTextAlertDivNotSavePeriod();"/>
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:120px;">
                                    <label class="control-label">Vat Type </lable>
                                </div>
                                <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right:0px;width:150px;">
                                    <div class="col-sm-12 " id="vattypepanel">
                                        <select name="periodVatType" id="periodVatType" class="form-control" onchange="checkPeriod(); checkPeriodVatType();">
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
                                <div class="col-xs-1 text-right" style="width: 200px;">                              
                                    <button type="button" id="btnPeriodList" name="btnPeriodList" onclick="callPeriodList()" class="btn btn-default">
                                        <i class="glyphicon glyphicon-align-justify"></i> Period
                                    </button>                                
                                </div>        
                            </div>   
                        </div>                      
                    </div><!-- End Text 1-->
                    <div id="TextBoxDiv2" >                    
                        <div class="row" style="padding-left: 0px;">
                            <div class="col-xs-12 " style="margin-top: -10px;">
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:150px;">
                                    <label class="control-label">Cash Amount</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">
                                    <div class="col-sm-12">
                                        <input name="periodCashAmount" id="periodCashAmount" type="text" class="form-control money" value="${advanceReceivePeriod.cashAmount - advanceReceivePeriod.cashMinusAmount}" readonly=""/>
                                    </div>
                                </div>
                                <div class="col-sm-1" style="width: 30px"></div>
                                <div class="col-xs-1" style="padding-left: 0px;width:85px;">
                                    <label class="control-label">Cash(-)</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="width:210px;">                                
                                    <div class="col-sm-12">
                                       <input name="periodCash" id="periodCash" type="text" class="form-control money" value="${advanceReceivePeriod.cashMinusAmount - 0}" readonly=""/>                              
                                    </div>
                                </div>       
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Cheque</lable>
                                </div>  
                                <div class="col-md-2 form-group text-left" style="padding-left:20px;width:200px;">
                                    <input name="periodCheque" id="periodCheque" type="text" class="form-control money" value="${advanceReceivePeriod.chqAmount - 0}" readonly=""/>
                                </div>
                            </div>
                        </div><!--End Row 2 -->
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 " style="margin-top: -10px;">
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:150px;">
                                    <label class="control-label">Bank Amount</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">
                                    <div class="col-sm-12">
                                        <input name="periodBankAmount" id="periodBankAmount" type="text" class="form-control money" value="${advanceReceivePeriod.bankTransfer - 0}" readonly=""/>
                                    </div>
                                </div>
                                <div class="col-sm-1" style="width: 20px"></div>
                                <div class="col-xs-1 " style="padding-left: 0px;width:95px;">
                                    <label class="control-label">Credit Card</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">                                
                                    <div class="col-sm-12">
                                       <input name="periodCreditCard" id="periodCreditCard" type="text" class="form-control money" value="${advanceReceivePeriod.creditAmount - 0}" readonly=""/>                               
                                    </div>
                                </div>
                                <div class="col-xs-1 text-right" style="width:85px;">
                                    <label class="control-label ">Detail</lable>
                                </div>  
                                <div class="col-md-2 form-group text-left" style="padding-left:35px;width:370px;">
                                    <textarea class="form-control" rows="3" id="periodDetail" name="periodDetail" onclick="hideTextAlertDivSavePeriod(); hideTextAlertDivNotSavePeriod();">${advanceReceivePeriod.detail}</textarea>
                                </div>  
                            </div>   
                        </div><!--End Row 3 -->
                        <div class="row" >
                            <div class="col-xs-6 text-right" style="margin-top: -10px; padding: 0px 5px 0px 0px;">                              
                                <button type="button" id="btnNew" name="btnNew" onclick="newReceivePeriod()" class="btn btn-primary">
                                    <i class="fa fa-plus"></i> New
                                </button>                                
                            </div>
                            <div class="col-xs-6 text-left" style="margin-top: -10px; padding: 0px 0px 0px 5px;">                              
                                <button type="button" id="btnSave" name="btnSave" onclick="saveReceivePeriod()" class="btn btn-success">
                                    <i class="fa fa-save"></i> Save
                                </button>                   
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
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Receive Table</h4>
            </div>
            <div class="modal-body" id="delCode">Are you sure to delete this advance receive ?</div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="deleteAdvanceReceive()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--Delete Receive Modal-->
<div class="modal fade" id="delReceiveCreditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Receive Credit</h4>
            </div>
            <div class="modal-body" id="delCode">Are you sure to delete this advance receive credit?</div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="deleteAdvanceReceiveCredit()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--Period Receive Modal-->
<div class="modal fade" id="receivePeriodModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Period</h4>
            </div>
            <div class="hidden">               
                <table class="display" id="periodTableTemp">
                    <tbody>
                        <tr>
                            
                        </tr>
                    </tbody>
                </table>
            </div>    
            <div class="modal-body">               
                <table class="display" id="periodTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width: 2%"></th>
                            <th style="width: 40%">From</th>
                            <th style="width: 40%">To</th>
                            <th style="width: 18%">Vat Type</th>
                            <th style="width: 5%">Action</th>
                        </tr>
                    </thead>
                    <tbody>                   
                        <c:forEach var="advanceReceivePeriodList" items="${advanceReceivePeriodList}" varStatus="i">
                            <tr>
                                <td class="text-center">
                                    <input type="checkbox" id="periodCheckbox${i.count}" name="periodCheckbox${i.count}" value="1"/>
                                    <input type="hidden" id="periodRow${i.count}" name="periodRow${i.count}" value="${i.count}"/>
                                    <input type="hidden" id="periodId${i.count}" name="periodId${i.count}" value="${advanceReceivePeriodList.id}"/>
                                </td>
                                <fmt:formatDate value="${advanceReceivePeriodList.receiveFrom}" var="receiveFrom" pattern="dd-MM-yyyy" />
                                <td class="text-center">${receiveFrom}</td>
                                <fmt:formatDate value="${advanceReceivePeriodList.receiveTo}" var="receiveTo" pattern="dd-MM-yyyy" />
                                <td class="text-center">${receiveTo}</td>
                                <td class="text-center">
                                    <c:set var="vatType" value=""/>
                                    <c:choose>
                                        <c:when test="${advanceReceivePeriodList.vatType == 'V'}">
                                            <c:set var="vatType" value="Vat"/>
                                        </c:when>
                                        <c:when test="${advanceReceivePeriodList.vatType == 'T'}">
                                            <c:set var="vatType" value="Temp"/>
                                        </c:when>                                      
                                    </c:choose>
                                    ${vatType}
                                </td>
                                <td class="text-center">
                                    <span class="glyphicon glyphicon-edit editicon" onclick="editAdvanceReceivePeriod('${advanceReceivePeriodList.id}','${advanceReceivePeriodList.receiveFrom}','${advanceReceivePeriodList.receiveTo}','${advanceReceivePeriodList.detail}','${advanceReceivePeriodList.vatType}','${advanceReceivePeriodList.department}','${advanceReceivePeriodList.cashAmount}','${advanceReceivePeriodList.cashMinusAmount}','${advanceReceivePeriodList.bankTransfer}','${advanceReceivePeriodList.chqAmount}','${advanceReceivePeriodList.creditAmount}');"></span>                                                                                                                                     
                                </td>
                            </tr>   
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="periodSize" name="periodSize" value="${advanceReceivePeriodList.size()}"/>
                <button id="btnDelete" type="button" onclick="deleteAdvanceReceivePeriod()" class="btn btn-danger">Delete</button>
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
