<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/PaymentOutbound.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="statusList" value="${requestScope['statusList']}" />
<c:set var="payTypeList" value="${requestScope['payTypeList']}" />
<c:set var="currencyList" value="${requestScope['currencyList']}" />
<c:set var="invSupList" value="${requestScope['invSupList']}" />
<c:set var="paymentOutbound" value="${requestScope['paymentOutbound']}" />
<c:set var="paymentOutboundDetail" value="${requestScope['paymentOutboundDetail']}" />
<c:set var="refNoList" value="${requestScope['refNoList']}" />
<c:set var="result" value="${requestScope['result']}" />
<c:set var="mVat" value="${requestScope['mVat']}" />
<c:set var="mWht" value="${requestScope['mWht']}" />
<input type="hidden" id="mVat" name="mVat" value="${mVat}"/>
<input type="hidden" id="mWht" name="mWht" value="${mWht}"/>
<input type="hidden" id="refNoList" name="refNoList" value="${refNoList}"/>
<input type="hidden" id="result" name="result" value="${result}"/>

<section class="content-header" >
    <h1>
        Checking - Package Outbound
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Checking</a></li>          
        <li class="active"><a href="#">Package Outbound</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;padding-right: 0px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/Checking/CheckingOutboundMenu.html'"></div>
    </div>
    <div class="col-sm-10">
        <!--Alert Save -->
        <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Success!</strong> 
        </div>
        <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Not Success!</strong> 
        </div>
        <div id="textAlertNotFound"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Pay No. is not found!</strong> 
        </div>
    </div>    
    <!--Content -->
    <form action="PaymentOutbound.smi" method="post" id="PaymentOutboundForm" autocomplete="off" role="form">
    <div class="col-sm-10">
<!--        <div class="row" style="padding-left: 0px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Payment Outbound</b></h4>
            </div>
        </div>
        <hr/>-->
        <!--Search -->
<!--        <div role="tabpanel">
             Nav tabs                     
            <ul class="nav nav-tabs " role="tablist">
                <li role="presentation" class="active outboundheader"><a href="#ref" aria-controls="ref" role="tab" data-toggle="tab">REF</a></li>
                <li role="presentation" class="outboundheader"><a href="#stk" aria-controls="stk" role="tab" data-toggle="tab">Stock</a></li>
                <h4>
                    <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" style="margin-left: 48em" onclick="">
                        <span id="arrowReservstion" class="arrowReservstion glyphicon glyphicon-chevron-up"></span> 
                    </a>
                </h4>
            </ul>
             Tab BL 
            <div class="panel panel-default outboundborder">
                <div class="panel-body">
                    <div class="tab-content collapse in" id="collapseExample" aria-expanded="false">
                        <div role="tabpanel" class="tab-pane active" id="ref">
                            <div class="col-xs-12" style="padding-top: 20px; padding-left: 50px;padding-right: 50px">
                                <div class="col-xs-1 text-right" style="width: 120px">
                                    <label class="control-label text-right">Ref No</label>
                                </div>
                                <div class="col-md-2 form-group" id="refnopanel" style="width: 200px">
                                    <div class="input-group">
                                        <input type="text" maxlength="6" style="text-transform:uppercase" class="form-control" id="refNo" name="refNo" value="" onkeydown="refNoValidate()">
                                    </div>
                                </div>
                                <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxloadRefNo"  class="fa fa-spinner fa-spin hidden"></i></div>
                                <div class="col-xs-1 text-left"  style="width: 100px">
                                    <button type="button"  id="btnSearchRefNo"  name="btnSearchRefNo" onclick="searchRefNo()" class="btn btn-primary btn-sm">
                                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                                    </button> 
                                </div>                                  
                                Ref No Table
                                <div class="row" >    
                                    <div class="col-md-12">
                                        <table id="RefNoTable" class="display" cellspacing="0" width="100px">
                                            <thead>
                                                <tr class="datatable-header">
                                                    <th style="width: 5%" >No</th>
                                                    <th style="width: 10%">Type</th>
                                                    <th style="width: 35%">Description</th>
                                                    <th style="width: 20%">Cost</th>
                                                    <th style="width: 10%">Cur</th>
                                                    <th style="width: 1%">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>               

                                            </tbody>
                                        </table>    
                                    </div>
                                </div>
                            </div>
                        </div>
                         Tab Stock 
                        <div role="tabpanel" class="tab-pane" id="stk">
                            <div class="col-xs-12" style="padding-top: 20px; padding-left: 50px;padding-right: 50px">
                                <div class="col-xs-1 text-right" style="width: 120px">
                                    <label class="control-label text-right">Stock</label>
                                </div>
                                <div class="col-xs-1 form-group" style="width: 200px" id="stockpanel">
                                    <div class="input-group">
                                        <input id="stock" name="stock" type="text" class="form-control" value="" onkeydown="stockValidate()" >
                                    </div>
                                </div>
                                <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxloadStock"  class="fa fa-spinner fa-spin hidden"></i></div>
                                <div class="col-xs-1 text-left"  style="width: 100px">
                                    <button style="height:30px" type="button"  id="btnSearchStock"  name="btnSearchStock" onclick="searchStock();" class="btn btn-primary btn-sm" ${outbound}><i class="fa fa-search"></i>&nbsp;Search </button>
                                </div>
                                Stock Table
                                <div class="row">
                                    <table id="StockTable" class="display" cellspacing="0" width="100%">
                                        <thead>
                                            <tr class="datatable-header" >
                                                <th style="width:5%;">No</th>
                                                <th style="width:30%;">Pay No</th>
                                                <th style="width:60%;">Description</th>
                                                <th style="width:5%;">Action</th>
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
        </div>-->
        
        <!--Hidden Value-->
        <input type="hidden" id="countPaymentDetail" name="countPaymentDetail" value="${paymentOutboundDetail.size()+1}"/>
        <input type="hidden" id="action" name="action" value="save"/>
                                       
        <!--Row 1 -->
        <div class="panel panel-default outboundborder">
            <div class="panel-heading outboundheader">
                <h4 class="panel-title">Payment Detail</h4>
            </div>
            <div class="panel-body"  style="padding-right: 0px;">                                 
                <div class="row" style="padding-left: 0px">
                    <div class="col-xs-1 text-right" style="width:100px;padding-right: 0px;padding-left: 0px;">
                        <label class="control-label">PV No</lable>
                    </div>
                    <div class="col-md-1 form-group text-left"style="width: 245px" >
                        <input name="payId" id="payId" type="hidden" class="form-control" value="${paymentOutbound.id}" />
                        <input name="createBy" id="createBy" type="hidden" class="form-control" value="${paymentOutbound.createBy}" />
                        <input name="createDate" id="createDate" type="hidden" class="form-control" value="<fmt:formatDate type="date" pattern='yyyy-MM-dd' value="${paymentOutbound.createDate}"/>" />
                        <input name="updateDate" id="updateDate" type="hidden" class="form-control" value="<fmt:formatDate type="date" pattern='yyyy-MM-dd HH:mm:ss' value="${paymentOutbound.updateDate}"/>" />
                        <input name="payNo" id="payNo" type="text" maxlength="6" class="form-control" style="text-transform:uppercase" value="${paymentOutbound.payNo}" />
                    </div>
                    <div class="col-xs-1 text-left"  style="width: 100px">
                        <button type="button"  id="btnSearchPayNo"  name="btnSearchPayNo" onclick="searchPvNo()" class="btn btn-primary btn-sm">
                            <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                        </button> 
                    </div>
                    <div class="col-xs-1 text-right" style="width:105px;">
                        <label class="control-label">Pay Date<font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-3 form-group text-left" style="padding-left:0px;padding-right: 0px;width: 150px;">
                        <div class='input-group date payDate' id="payDateCheck">
                            <input name="payDate" id="payDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="<fmt:formatDate type="date" pattern='yyyy-MM-dd' value="${paymentOutbound.payDate}"/>" />
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="col-xs-1 text-right" style="width:80px;padding-left:10px;padding-right:0px;">
                        <label class="control-label">Account<font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-3 text-left" style="padding-top : 5px;padding-left:0px;padding-right:0px;">
                        <c:set var="account1" value=""/>
                        <c:set var="account2" value=""/>
                        <c:choose>
                            <c:when test="${paymentOutbound.account == 1}">
                                <c:set var="account1" value="checked"/>
                            </c:when>
                            <c:when test="${paymentOutbound.account == 2}">
                                <c:set var="account2" value="checked"/>
                            </c:when>
                        </c:choose>
                        <div class="col-sm-6 text-left " >
                            <input type="radio" id="account1" name="account" value="1" ${account1}/>&nbsp;account(1)
                        </div>
                        <div class="col-sm-6 text-left" >
                            <input type="radio" id="account2" name="account" value="2" ${account2}/>&nbsp;temp
                        </div>
                    </div>
                </div><!--End row 1-->
                <div class="row" style="padding-left: 0px">
                    <div class="col-xs-1 text-right" style="width:100px;padding-right: 0px;padding-left: 0px;">
                        <label class="control-label">Invoice Sup</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="width:150px;"> 
                        <div class="input-group">
                            <input type="hidden" class="form-control" id="invSupId" name="invSupId" value="" />
                            <input type="text" class="form-control" id="invSupCode" name="invSupCode" value="${paymentOutbound.invoiceSup}" />
                            <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchInvoiceSup">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                        </div>
                    </div>
                    <div class="col-md-2 form-group text-left" style="width:200px;padding-left: 0px;">
                        <input name="invSupName" id="invSupName" type="text" class="form-control" value="" readonly=""/>
                    </div>
                    <div class="col-xs-1 text-right" style="width:85px;padding-right: 0px;">
                        <label class="control-label">A/P Code<font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-1 form-group text-left" style="padding-left:0px;padding-right: 0px;width: 180px;">
                        <div class="col-sm-12">
                            <div class="input-group" id="CodeValidate">
                                <input name="invSupApCode" id="invSupApCode" type="text" class="form-control" value="${paymentOutbound.apCode}" />   
                            </div>    
                        </div> 
                    </div>
                    <div class="col-xs-1 text-right" style="width:60px;padding-right: 0px;">
                        <label class="control-label">Status<font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-1 form-group text-left" style="padding-left:0px;padding-right: 0px;width: 180px;">
                        <div class="col-sm-12">
                            <select class="form-control" name="status" id="status">
                                <option  value="" >---------</option>
                                <c:forEach var="status" items="${statusList}">                                       
                                    <c:set var="select" value="" />
                                    <c:if test="${status.id == paymentOutbound.MItemstatus.id}">
                                        <c:set var="select" value="selected" />
                                    </c:if>
                                    <option  value="${status.id}" ${select}>${status.name}</option>
                                </c:forEach>
                            </select>   
                        </div> 
                    </div>
                </div><!--End row 2-->
                <div class="col-xs-12 " style="padding-left: 0px;">
                    <div class="col-xs-1 text-right" style="margin-top: -5px; width: 100px; ">
                        <label class="control-label" for="">Search</lable>                           
                    </div>           
                    <div class="col-md-1 text-left" style="width: 50px; padding-left: 0px;">
                        <a data-toggle="collapse" href="#" aria-expanded="false" aria-controls="collapseExample" onclick="showSearchRefNo()">
                            <span id="spanEditRefNo">Ref</span>
                        </a>                           
                    </div>
                    <div class="col-md-1 text-left" style="width: 50px;">
                        <a data-toggle="collapse" href="#" aria-expanded="false" aria-controls="collapseExample" onclick="showSearchStock()">
                            <span id="spanEditStock">Stock</span>
                        </a>                           
                    </div>
                    <div class="col-md-1 text-left">
                        <div class="col-xs-1  text-right" style="padding: 5px 0px 0px 0px;"><i id="ajaxLoadSearch"  class="fa fa-spinner fa-spin hidden"></i></div>
                    </div>
                </div>
                <div class="col-xs-12 " id="searchRefNo1" style="padding-left: 0px;">
                    <div class="col-xs-1 text-right" style="width: 100px;">
                        <label class="control-label">Ref No</label>
                    </div>
                    <div class="col-xs-2 form-group" id="refnopanel" style="width: 180px; padding-left: 0px;">
                        <div class="input-group">
                            <input type="text" style="text-transform:uppercase" class="form-control" id="refNo" name="refNo" value="" onkeydown="refNoValidate()" maxlength="6">
                        </div>
                    </div>                        
                    <div class="col-xs-1 text-left"  style="width: 100px">
                        <button type="button"  id="btnSearchRefNo"  name="btnSearchRefNo" onclick="searchRefNo()" class="btn btn-primary btn-sm">
                            <span id="SpanSearchRefNo" class="glyphicon glyphicon-print fa fa-search"></span> Search
                        </button> 
                    </div>
                </div> 
                <div class="col-xs-12 hidden" id="searchRefNo2" style="padding: 0px 15px 0px 0px;">
                    <table id="RefNoTable" class="display" cellspacing="0" width="100px">
                        <thead>
                            <tr class="datatable-header">
                                <th style="width: 1%" >No</th>
                                <th style="width: 5%">Type</th>
                                <th style="width: 44%">Description</th>
                                <th style="width: 10%">Cost</th>
                                <th style="width: 3%">Cur</th>
                                <th style="width: 10%">Sale</th>
                                <th style="width: 3%">Cur</th>
                                <th style="width: 1%">Action</th>
                            </tr>
                        </thead>
                        <tbody>               

                        </tbody>
                    </table>        
                </div>
                <div class="col-xs-12 hidden" id="searchStock1" style="padding-left: 0px;">
                    <div class="col-xs-1 text-right" style="width: 100px;">
                        <label class="control-label">Stock</label>
                    </div>
                    <div class="col-xs-2 form-group" id="stockpanel" style="width: 180px; padding-left: 0px;">
                        <div class="input-group">
                            <input type="text" style="text-transform:uppercase" class="form-control" id="payStockNo" name="payStockNo" value="" onkeydown="stockValidate()" >
                        </div>
                    </div>                        
                    <div class="col-xs-1 text-left"  style="width: 100px">
                        <button type="button"  id="btnSearchStock"  name="btnSearchStock" onclick="searchStock()" class="btn btn-primary btn-sm">
                            <span id="SpanSearchStock" class="glyphicon glyphicon-print fa fa-search"></span> Search
                        </button> 
                    </div>
                </div> 
                <div class="col-xs-12 hidden" id="searchStock2" style="padding: 0px 15px 0px 0px;">
                    <table id="StockTable" class="display" cellspacing="0" width="100px">
                        <thead>
                            <tr class="datatable-header">
                                <th style="width: 5%" >No</th>
                                <th style="width: 35%">Pay Stock No.</th>
                                <th style="width: 20%">Cost</th>
                                <th style="width: 5%">Cur</th>
                                <th style="width: 20%">Sale</th>
                                <th style="width: 5%">Cur</th>
                                <th style="width: 1%">Action</th>
                            </tr>
                        </thead>
                        <tbody>               

                        </tbody>
                    </table>        
                </div>
                <div class="col-xs-12" style="padding-left: 0px;">
                    <br>
                    <div id="textAlertCurrencyNotMatch"  style="display:none; " class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Amount Currency is not match.</strong> 
                    </div>
                    <div id="textAlertCurrencyNotEmpty"  style="display:none; " class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Amount Currency is not empty.</strong> 
                    </div>
                </div>               
                <div class="row" style="padding-left: 15px;">             
                    <div class="row">
                        <div class="col-xs-11" style="width: 1030px">
                            <table class="display" id="PaymentDetailTable">
                                <thead>
                                    <tr class="datatable-header">
                                        <th style="width: 10%">Type</th>                                   
                                        <th style="width: 7%">Ref No</th>
                                        <th style="width: 9%">Invoice</th>
                                        <th style="width: 9%">Cost</th>
                                        <th style="width: 9%">Gross</th>
                                        <th style="width: 4%" onclick="checkVatAll()"><u>Is Vat</u></th>                                                                      
                                        <th style="width: 2%">Vat</th>
                                        <th style="width: 9%">Amount</th>
                                        <th style="width: 9%">Comm</th>
                                        <th style="width: 7%">Cur</th>
                                        <th style="width: 1%" >Action</th>
                                    </tr>
<!--                                    <tr class="datatable-header" >
                                        <th colspan="6">Description</th>
                                        <th colspan="5">Pay Stock</th>
                                    </tr>-->
                                </thead>
                                <tbody>
                                    <c:forEach var="detail" items="${paymentOutboundDetail}" varStatus="i">
                                    <tr>
                                        <td class="hidden">
                                            <input type="text" name="count${i.count}" id="count${i.count}" class="form-control" value="${i.count}"/>
                                            <input type="text" name="detailId${i.count}" id="detailId${i.count}" class="form-control" value="${detail.detailId}"/>
                                            <input type="text" name="payId${i.count}" id="payId${i.count}" class="form-control" value="${detail.payId}"/>
                                            <input type="text" name="bookDetailId${i.count}" id="payId${i.count}" class="form-control" value="${detail.bookDetailId}"/>
                                            <input type="text" name="bookDetailType${i.count}" id="bookDetailType${i.count}" class="form-control" value="${detail.bookDetailType}"/>
                                            <input type="text" name="accCode${i.count}" id="accCode${i.count}" class="form-control" value="${detail.accCode}"/>
                                            <input type="text" name="exportDate${i.count}" id="exportDate${i.count}" class="form-control" value="<fmt:formatDate type="date" pattern='yyyy-MM-dd HH:mm:ss' value="${detail.exportDate}"/>"/>
                                            <input type="text" name="isExport${i.count}" id="isExport${i.count}" class="form-control" value="${detail.isExport}"/>
                                            <input type="text" name="realExRate${i.count}" id="realExRate${i.count}" class="form-control" value="${detail.realExRate}"/>
                                            <input type="text" name="payExRate${i.count}" id="payExRate${i.count}" class="form-control" value="${detail.payExRate}"/>
                                            <input type="text" name="isWht${i.count}" id="isWht${i.count}" class="form-control" value="${detail.isWht}"/>
                                            <input type="text" name="wht${i.count}" id="wht${i.count}" class="form-control" value="${detail.wht}"/>
                                            <input type="text" name="whtTemp${i.count}" id="whtTemp${i.count}" class="form-control" value="${detail.wht}"/>
                                            <input type="text" name="whtAmount${i.count}" id="whtAmount${i.count}" class="form-control" value="${detail.whtAmount}"/>
                                            <input type="text" name="isComVat${i.count}" id="isComVat${i.count}" class="form-control" value="${detail.isVatRecCom}"/>
                                            <input type="text" name="vatRecCom${i.count}" id="vatRecCom${i.count}" class="form-control" value="${detail.vatRecCom}"/>
                                            <input type="text" name="vatRecComTemp${i.count}" id="vatRecComTemp${i.count}" class="form-control" value="${detail.vatRecCom}"/>
                                            <input type="text" name="vatRecComAmount${i.count}" id="vatRecComAmount${i.count}" class="form-control" value="${detail.vatRecComAmount}"/>
                                            <input type="text" name="value${i.count}" id="value${i.count}" class="form-control" value="${detail.value}"/>
                                            <input type="text" name="payStockId${i.count}" id="payStockId${i.count}" class="form-control" value="${detail.payStockId}"/>
                                        </td>
                                        <td>
                                            <select class="form-control" name="type${i.count}" id="type${i.count}" onchange="addRow('${i.count}')">
                                                <option  value="" >---------</option>
                                                <c:forEach var="payType" items="${payTypeList}">                                       
                                                    <c:set var="select" value="" />
                                                    <c:if test="${payType.id == detail.type}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option  value="${payType.id}" ${select}>${payType.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="text" maxlength="6" name="refNo${i.count}" id="refNo${i.count}" class="form-control" onfocusout="checkRefNo('${i.count}')" value="${detail.refNo}"/>
                                        </td>
                                        <td>
                                            <input type="text" name="invoice${i.count}" id="invoice${i.count}" class="form-control" value="${detail.invoice}"/>
                                        </td>                                                                   
                                        <td>
                                           <input type="text" name="cost${i.count}" id="cost${i.count}" class="form-control" style="text-align:right;" onkeyup="insertCommas(this)" onfocusout="setFormatNumber('cost','${i.count}')" value="${detail.cost}"/> 
                                        </td>
                                        <td>
                                            <input type="text" name="gross${i.count}" id="gross${i.count}" class="form-control" style="text-align:right;" onkeyup="insertCommas(this)" value="${detail.gross}" readonly=""/>
                                        </td>                                
                                        <td align="center">
                                            <c:set var="isVat" value=""/>
                                            <c:if test="${detail.isVat == 1}">
                                                <c:set var="isVat" value="checked"/>
                                            </c:if>
                                            <input type="checkbox" id="isVat${i.count}" name="isVat${i.count}" class="text-center"  onclick="calculateGross('${i.count}')" value="1" ${isVat}>
                                        </td>    
                                        <td align="right" id="vatShow${i.count}">
                                            <c:if test="${detail.isVat == 1}">
                                                ${detail.vat}
                                            </c:if>                                          
                                        </td>
                                        <td class="hidden">
                                            <input type="text" name="vat${i.count}" id="vat${i.count}" class="form-control" value="${detail.vat}"/>
                                        </td>
                                        <td>
                                            <input type="text" name="amount${i.count}" id="amount${i.count}" class="form-control" style="text-align:right;" onkeyup="insertCommas(this)" onfocusout="setFormatNumber('amount','${i.count}'); calculateWhtAmount('');" value="${detail.amount}"/>
                                        </td>
                                        <td>
                                            <input type="text" name="comm${i.count}" id="comm${i.count}" class="form-control" style="text-align:right;" onkeyup="insertCommas(this)" onfocusout="setFormatNumber('comm','${i.count}'); calculateVatRecComAmount();" value="${detail.comm}"/>
                                        </td>
                                        <td>
                                            <select class="form-control" name="cur${i.count}" id="cur${i.count}" onchange="addRow('${i.count}')">
                                                <option  value="" >---------</option>
                                                <c:forEach var="currency" items="${currencyList}">                                       
                                                    <c:set var="select" value="" />
                                                    <c:if test="${currency.code == detail.cur}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option  value="${currency.code}" ${select}>${currency.code}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td class="text-center" rowspan="2">                                 
                                            <a href="#" onclick=""  data-toggle="modal" data-target="">
                                                <span id="editPaymentDetail${i.count}" onclick="editPaymentDetail('${i.count}')" class="glyphicon glyphicon glyphicon-list-alt"></span>                                                
                                            </a>
                                            <a href="#" onclick=""  data-toggle="modal" data-target="">
                                                <span id="spanDelete${i.count}" class="glyphicon glyphicon-remove deleteicon" onclick="deletePaymentDetailList('${detail.detailId}','${i.count}')" data-toggle="modal"></span>
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="1" align="right" bgcolor="#E8EAFF">
                                            <b>Description</b>
                                        </td>
                                        <td colspan="2">
                                            <input type="text" name="description${i.count}" id="description${i.count}" class="form-control" value="${detail.description}"/>
                                        </td>
                                        <td colspan="1" align="right" bgcolor="#E8EAFF">
                                            <b>Pay Stock</b>
                                        </td>
                                        <td colspan="3">
                                            <input type="text" name="payStock${i.count}" id="payStock${i.count}" class="form-control" value="${detail.payStock}" onfocusout="checkPayStock('${i.count}')"/>
                                        </td>
                                        <td colspan="1" align="right" bgcolor="#E8EAFF">
                                            <b>Sale</b>
                                        </td>
                                        <td colspan="1">
                                            <input type="text" name="saleAmount${i.count}" id="saleAmount${i.count}" class="form-control" style="text-align:right;" onkeyup="insertCommas(this)" onfocusout="setFormatNumber('saleAmount','${i.count}')" value="${detail.saleAmount}"/>
                                        </td>
                                        <td colspan="1">
                                            <select class="form-control" name="saleCurrency${i.count}" id="saleCurrency${i.count}" onchange="addRow('${i.count}')">
                                                <option  value="" >---------</option>
                                                <c:forEach var="currency" items="${currencyList}">                                       
                                                    <c:set var="selectsaleCur" value="" />
                                                    <c:if test="${currency.code == detail.saleCurrency}">
                                                        <c:set var="selectsaleCur" value="selected" />
                                                    </c:if>
                                                    <option  value="${currency.code}" ${select}>${currency.code}</option>
                                                </c:forEach>
                                            </select>
                                        </td>      
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div id="tr_PaymentOutboundDetailAddRow" class="text-center hide" style="padding-top: 10px">
                                <a class="btn btn-success" onclick="addRowPaymentDetailTable()">
                                    <i class="glyphicon glyphicon-plus"></i> Add
                                </a>
                            </div>
                        </div>   
                    </div><!-- End Row 1-->
                </div>            
            </div>
        </div>
        
        <!-- Payment Detail Panel -->
        <div class="panel panel-default outboundborder hidden" id="paymentDetailPanel">                    
            <div class="panel-body">
                <div class="row" style="padding-left: 25px; margin-top: -10px;">
                    <div class="col-xs-2">
                        <label class="control-label">Payment Detail</lable>
                    </div>
                </div>
                <div class="row" style="padding-left: 25px; ">
                    <div class="col-xs-1 text-right" style="width: 110px;">
                        <label class="control-label">Real Rate</lable>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 200px;">
                        <input type="hidden" class="form-control text-right" id="rowDetail" name="rowDetail" value=""/>
                        <input type="text" class="form-control text-right numerical" id="realExRate" name="realExRate" value=""/>
                    </div>
                    <div class="col-xs-1 text-left" style="width: 120px;">
                        <label class="control-label">Pay EX Rate</lable>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 200px;">
                        <input type="text" class="form-control text-right numerical" id="payExRate" name="payExRate" value=""/>
                    </div>
                    <div class="col-xs-1 text-left" style="width: 70px;">
                        <label class="control-label">Detail</lable>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 300px;">
                        <textarea rows="3" cols="255" class="form-control" id="paymentDescription" name="paymentDescription" maxlength="255" data-bv-field="detail"></textarea>
                    </div>
                </div>
                <div class="row" style="padding-left: 25px; margin-top: -30px;">
                    <div class="col-xs-1 text-right" style="width: 110px;">
                        <label class="control-label">WHT</lable>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 50px; padding-top: 5px;">
                        <input type="checkbox" id="isWht" name="isWht" value="1"/>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 150px;">
                        <input type="text" class="form-control text-right" id="wht" name="wht" value="" readonly=""/>
                    </div>
                    <div class="col-xs-1 text-left" style="width: 120px;">
                        <label class="control-label">WHT Amount</lable>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 200px;">
                        <input type="text" class="form-control text-right" id="whtAmount" name="whtAmount" value="" readonly=""/>
                    </div>
                </div>
                <div class="row" style="padding-left: 25px; padding-top: 10px;">
                    <div class="col-xs-1 text-right" style="width: 110px;">
                        <label class="control-label">Com Vat</lable>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 50px; padding-top: 5px;">
                        <input type="checkbox" id="isComVat" name="isComVat" value="1"/>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 150px;">
                        <input type="text" class="form-control text-right" id="vatRecCom" name="vatRecCom" value="" readonly=""/>
                    </div>
                    <div class="col-xs-1 text-left" style="width: 120px;">
                        <label class="control-label">Com Amount</lable>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 200px;">
                        <input type="text" class="form-control text-right" id="vatRecComAmount" name="vatRecComAmount" value="" readonly=""/>
                    </div>
                    <div class="col-xs-1 text-left" style="width: 70px;">
                        <label class="control-label">Value</lable>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 200px;">
                        <input type="text" class="form-control text-right numerical" id="value" name="value" value=""/>
                    </div>                       
                </div>
                <div class="row" style="padding-left: 25px; padding-top: 10px;">
                    <div class="col-xs-12 text-center" >
                         <button type="button" onclick="savePaymentDetail()" class="btn btn-success">OK</button>
                    </div>
                </div>
            </div>
        </div><!--End Table Content -->
                            
        <div class="panel panel-default outboundborder">
            <div class="panel-body"  style="padding-right: 0px;">                                               
                <div class="row" >
                    <div class="col-md-12 ">
                        <div class="col-sm-6">
                            <div class="col-md-1 text-right" style="width:70px;padding-right: 0px;padding-left: 0px;">
                                <label class="control-label">Detail</lable>
                            </div>
                            <div class="col-md-1 text-left" style="width: 350px">
                                <textarea rows="3" cols="255" class="form-control" id="detail" name="detail" maxlength="255" data-bv-field="detail">${paymentOutbound.detail}</textarea>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="col-md-5 text-right" style="width: 195px">
                                <label class="control-label">Gross Total</lable>
                            </div>
                            <div class="col-md-3 text-right" style="width: 210px;padding-left:0px;padding-right: 0px;">
                                <input name="grossTotal" id="grossTotal" type="text" class="form-control text-right" value="" readonly=""/>
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="col-md-5 text-right" style="width: 195px;padding-top: 15px">
                                <label class="control-label">Vat Total</lable>
                            </div>
                            <div class="col-md-3 text-right" style="width: 210px;padding-left:0px;padding-right: 0px;padding-top: 15px">
                                <input name="vatTotal" id="vatTotal" type="text" class="form-control text-right" value="" readonly=""/>
                            </div>
                        </div>                       
                    </div>                     
                </div><!--End row 5-->   
                <div class="row" >
                    <div class="col-md-12 form-group">
                        <div class="col-sm-12">
                            <div class="col-md-9 text-right" style="width: 703px;padding-top: 15px">
                                <label class="control-label">Grand Total</lable>
                            </div>
                            <div class="col-md-3 text-right" style="width: 210px;padding-left:0px;padding-right: 0px;padding-top: 15px">
                                <input name="grandTotal" id="grandTotal" type="text" class="form-control text-right" value="" readonly=""/>
                            </div>
                        </div>
                    </div>
                </div>    
            </div>
        </div>
        <div class="row text-center" >
            <div class="col-xs-6 text-right">
                <button type="button" id="btnSave" name="btnSave" class="btn btn-success" onclick="validatePaymentOutbound('save')">
                    <i class="fa fa-save"></i> Save             
                </button>
            </div>
            <div class="col-xs-6 text-left">
                <a id="btnNew" name="btnNew" onclick="reloadPage()" class="btn btn-primary">
                    <i class="glyphicon glyphicon-plus"></i> New
                </a>
            </div>                         
        </div><!--End Button -->
    </div>
    </form>                        
</div>
<!--Delete Payment Outbound Modal-->
<div class="modal fade" id="delPaymentOutboundModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Payment Outbound</h4>
            </div>
            <div class="modal-body" id="delCode">
                Are you sure to delete this payment detail outbound ?
            </div>
            <div class="modal-footer">              
                <input type="hidden" id="delPaymentDetailId" name="delPaymentDetailId" value=""/>
                <input type="hidden" id="delPaymentDetailRow" name="delPaymentDetailRow" value=""/>
                <button id="btnDelete" type="button" onclick="confirmDeletePaymentDetailList()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--Search Modal-->
<div class="modal fade" id="SearchPaymentOutboundModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Search Payment Outbound</h4>
            </div>
            <div class="modal-body">
                <div style="height: 200px;">
                    <div class="text-right">
                        Invoice <input type="text" value="">
                    </div>
                <table class="display" id="InvoiceTable" >
                    <thead class="datatable-header">
                        <tr>
                            <th>Product</th>
                            <th>Description</th>
                            <th>Cost</th>
                            <th>Base Cost</th>
                            <th>Amount</th>
                            <th>Base Amount</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr class="">
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="text-center">
                                    <a href="">
                                        <span class="glyphicon glyphicon-check"></span>
                                    </a>
                                </td>
                            </tr>
                            <tr class="">
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="text-center">
                                    <a href="">
                                        <span class="glyphicon glyphicon-check"></span>
                                    </a>
                                </td>
                            </tr>
                    </tbody>
                </table>
                </div><br>
                <div style="height: 200px;">
                    <div class="text-right">
                        Ref No <input type="text" value="">
                    </div>
                <table class="display" id="RefNoTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Product</th>
                            <th>Description</th>
                            <th>Cost</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr class="">
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="">xxxx</td>
                                <td>xxx</td>
                            </tr>
                    </tbody>
                </table>
                </div>
            </div>
            <div class="modal-footer">
                <button id="SearchPaymentOutboundModalOK" name="SearchPaymentOutboundModalOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchPaymentOutboundModalClose" name="SearchPaymentOutboundModalClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--Search Invoice Sup-->
<div class="modal fade" id="SearchInvoiceSup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Invoice Supplier</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="SearchInvoicSupTable">
                    <thead class="datatable-header">
                        <script>
                            var invoiceSup = [];
                        </script>
                        <tr>
                            <th class="hidden">Id</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th>AP code</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="invSup" items="${invSupList}">
                            <tr onclick ="setupInvSupValue('${invSup.id}', '${invSup.code}', '${invSup.name}', '${invSup.apcode}')" >
                                <td class="hidden">${invSup.id}</td>
                                <td>${invSup.code}</td>
                                <td>${invSup.name}</td>
                                <td>${invSup.apcode}</td> 
                            </tr>
                            <script>
                                invoiceSup.push({id: "${invSup.id}", code: "${invSup.code}", name: "${invSup.name}", apcode: "${invSup.apcode}"});
                            </script>
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="SearchInvoiceSupOK" name="SearchInvoiceSupOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchInvoiceSupClose" name="SearchInvoiceSupClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->
<!--Search A/P Code-->
<div class="modal fade" id="SearchAPCode" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">A/P </h4>
            </div>
            <div class="modal-body">
                <table class="display" id="SearchAPCodeTable">
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
                <button id="SearchAPCodeOK" name="SearchAPCodeOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchAPCodeClose" name="SearchAPCodeClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->
<select class="form-control hidden" name="typeClone" id="typeClone">
    <c:forEach var="payType" items="${payTypeList}">                                       
        <option  value="${payType.id}">${payType.name}</option>
    </c:forEach>
</select>
<select class="form-control hidden" name="curClone" id="curClone">
    <c:forEach var="currency" items="${currencyList}">                                       
        <option  value="${currency.code}">${currency.code}</option>
    </c:forEach>
</select>
<script type="text/javascript">
    $(document).ready(function () {
        $('#payDateCheck').datetimepicker().on('dp.change', function(e) {
            $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'payDate');
        });
        $('#PaymentOutboundForm').bootstrapValidator({
            container: 'tooltip',
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                payDate: {
                    validators: {
                        notEmpty: {
                            message: 'Pay Date is required.'
                        }
                    }
                },
                status: {
                    validators: {
                        notEmpty: {
                            message: 'Status is required.'
                        }
                    }
                },
                invSupApCode: {
                    validators: {
                        notEmpty: {
                            message: 'A/P Code is required.'
                        }
                    }
                },
                account: {
                    validators: {
                        notEmpty: {
                            message: 'Account is required.'
                        }
                    }
                }                        
            }
        }).on('success.field.fv', function(e, data) {
            if (data.field === 'payDate' === false) {
                data.fv.revalidateField('payDate');
            }
        });
    });
</script>    

