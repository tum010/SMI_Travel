<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/TaxInvoice.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<c:set var="Type" value="${requestScope['Department']}" />
<c:set var="page" value="${requestScope['page']}" />
<c:set var="vatDefault" value="${requestScope['vatDefault']}" />
<c:set var="product_list" value="${requestScope['productList']}" />
<c:set var="currency_list" value="${requestScope['currencyList']}" />
<c:set var="customer_agent_list" value="${requestScope['customerAgentList']}"/>
<c:set var="refNo_list" value="${requestScope['refNo_list']}" />
<c:set var="taxInvoice" value="${requestScope['taxInvoice']}" />
<c:set var="taxInvoiceDetail" value="${requestScope['taxInvoiceDetail_list']}" />
<c:set var="roleName" value="${requestScope['roleName']}" />
<input type="hidden" id="Type" name="Type" value="${param.Department}">
<section class="content-header" >
    <h1>
        <c:set var="voidTaxInvoice" value="" />
        <c:if test="${taxInvoice.MFinanceItemstatus.id == '2'}">
            <c:set var="voidTaxInvoice" value="VOID" />
        </c:if>
        <c:set var="panelheader" value=""/>
        <c:set var="panelborder" value=""/>
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6 " style="padding-right: 15px">
                <c:set var="outbound" value=""/>
		<c:choose>
                    <c:when test="${fn:contains(page , 'W')}">
                        <h4><b>Finance & Cashier - Tax Invoice Wendy <font style="color: red">${voidTaxInvoice}</font></b></h4>
                        <c:set var="outbound" value="hidden"/>
                        <c:set var="panelheader" value="wendyheader"/>
                        <c:set var="panelborder" value="wendyborder"/>
                    </c:when>
                    <c:when test="${fn:contains(page , 'O')}">
                        <h4><b>Finance & Cashier - Tax Invoice Outbound <font style="color: red">${voidTaxInvoice}</font></b></h4> 
                        <c:set var="panelheader" value="outboundheader"/>
                        <c:set var="panelborder" value="outboundborder"/>
                    </c:when> 
                    <c:when test="${fn:contains(page , 'I')}">
                        <h4><b>Finance & Cashier - Tax Invoice Inbound <font style="color: red">${voidTaxInvoice}</font></b></h4>
                        <c:set var="outbound" value="hidden"/>
                        <c:set var="panelheader" value="inboundborderheader"/>
                        <c:set var="panelborder" value="inboundborder"/>
                    </c:when> 
		</c:choose> 
            </div>
        </div>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Tax Invoice</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/FinanceAndCashier/TaxInvoiceMainMenu.html'"></div>
    </div>
    <!--Content -->
    <div class="col-sm-10">
        <c:if test="${requestScope['result_text'] =='success'}">                                            
            <div id="textAlertDivSave"  style="" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Success!.</strong> 
            </div>
        </c:if>        
        <c:if test="${requestScope['result_text'] =='fail'}">
        <div id="textAlertDivNotSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <strong>Save Unsuccess!.</strong> 
        </div>
        </c:if>
        <c:if test="${requestScope['result_text'] =='not found'}">
        <div id="textAlertDivFindNotFound"  style="" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <strong>Tax invoice no not found!.</strong> 
        </div>
        </c:if>           
        <c:if test="${requestScope['result_text'] =='cost much over'}">
        <div id="textAlertCostOver"  style="" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <strong>Amount over than invoice.</strong> 
        </div>
        </c:if>  
        <c:if test="${requestScope['result_text'] =='amount much over'}">
        <div id="textAlertAmountOver"  style="" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <strong>Amount over than invoice.</strong> 
        </div>
        </c:if>
        <c:if test="${requestScope['result_text'] =='disableVoid unsuccess'}">
        <div id="textAlertAmountOver"  style="" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Cannot void Tax invoice. It use in credit note no ${requestScope['cnNoList']}</strong> 
        </div>
        </c:if>        
        <form action="TaxInvoice${page}.smi" method="post" id="TaxInvoiceForm" role="form" autocomplete="off" >
        <!--Search Invoice-->
<!--           <div role="tabpanel">
                Nav tabs                     
                <ul class="nav nav-tabs " role="tablist">
                    <li role="presentation" class="active ${panelheader}"><a href="#inv" aria-controls="inv" role="tab" data-toggle="tab">INV</a></li>
                    <li role="presentation" class="${panelheader}"><a href="#ref" aria-controls="ref" role="tab" data-toggle="tab">REF</a></li>
                    <h4>
                        <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" style="margin-left: 48em" onclick="">
                            <span id="arrowReservstion" class="arrowReservstion glyphicon glyphicon-chevron-up"></span> 
                        </a>
                    </h4>
                </ul>-->
               <!-- Tab BL -->
<!--                <div class="panel panel-default ${panelborder}">
                    <div class="panel-body">
                        <div class="tab-content collapse in" id="collapseExample" aria-expanded="false">
                            <div role="tabpanel" class="tab-pane hidden active" id="inv">
                                <div class="col-xs-12" style="padding-top: 20px; padding-left: 50px;padding-right: 50px">
                                    <div class="col-xs-1 text-right" style="width: 120px">
                                        <label class="control-label text-right">Invoice No</label>
                                    </div>
                                    <div class="col-md-2 form-group" id="invoicenopanel" style="width: 200px">
                                        <div class="input-group">
                                            <input type="text" style="text-transform:uppercase" class="form-control" id="invoiceNo" name="invoiceNo" value="" onkeydown="invoiceNoValidate()">
                                        </div>
                                    </div>
                                    <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxload1"  class="fa fa-spinner fa-spin hidden"></i></div>
                                    <div class="col-xs-1 text-left"  style="width: 100px">
                                        <button type="button"  id="btnSearchInvoiceNo"  name="btnSearchInvoiceNo" onclick="searchInvoiceNo()" class="btn btn-primary btn-sm">
                                            <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                                        </button> 
                                    </div>                                  
                                    Invoice Table
                                    <div class="row" >    
                                        <div class="col-md-12">
                                            <table id="InvoiceListTable" class="display" cellspacing="0" width="100px">
                                                <thead>
                                                    <tr class="datatable-header">
                                                        <th style="width: 15%" >Product</th>
                                                        <th style="width: 35%">Description</th>
                                                        <th style="width: 20%">Cost</th>
                                                        <th style="width: 10%">Cur</th>
                                                        <th style="width: 20%">Amount</th>
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
                             Tab REF 
                            <div role="tabpanel" class="tab-pane hidden" id="ref">
                                <div class="col-xs-12" style="padding-top: 20px; padding-left: 50px;padding-right: 50px">
                                    <div class="col-xs-1 text-right" style="width: 120px">
                                        <label class="control-label text-right">Ref No </label>
                                    </div>
                                    <div class="col-xs-1 form-group" style="width: 200px" id="refnopanel">
                                        <div class="input-group">
                                            <input id="refNo" name="refNo" type="text" class="form-control" value="" onkeydown="refnoValidate()" ${outbound}>
                                        </div>
                                    </div>
                                    <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxload2"  class="fa fa-spinner fa-spin hidden"></i></div>
                                    <div class="col-xs-1 text-left"  style="width: 100px">
                                        <button style="height:30px" type="button"  id="btnSearchRefNo"  name="btnSearchRefNo" onclick="searchRefNo();" class="btn btn-primary btn-sm" ${outbound}><i class="fa fa-search"></i>&nbsp;Search </button>
                                    </div>
                                    <div class="col-md-5 ">
                                        <div id='AlertBooking' style='display:none'><font color="red">This Ref No can get billable detail from outbound only.</font></div>  
                                    </div>
                                    RefNo Table
                                    <div class="row">
                                        <table id="RefNoListTable" class="display" cellspacing="0" width="100%">
                                            <thead>
                                                <tr class="datatable-header" >
                                                    <th style="width:1%;">No</th>
                                                    <th style="width:20%;">Description</th>
                                                    <th style="width:10%;">Cost</th>
                                                    <th style="width:5%;">Cur</th>
                                                    <th style="width:10%;">Amount</th>
                                                    <th style="width:5%;">Cur</th>
                                                    <th style="width:10%;">Ex Rate</th>
                                                    <th style="width:10%;">Profit</th>
                                                    <th style="width:1%;">Action</th>
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
<!--                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane  active" id="infoSearchInvoice">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2 class="panel-title">
                                    <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" onclick="">
                                        <span id="SpanEdit">Search Invoice</span>
                                    </a>
                                    <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" style="margin-left: 54em" onclick="">
                                        <span id="arrowReservstion" class="arrowReservstion glyphicon glyphicon-chevron-up"></span> 
                                    </a>
                                </h2>               
                            </div>
                            <div class="panel-body">               
                                <div class=" accordion-body collapse in" id="collapseExample" aria-expanded="false">
                                    <div class="col-md-12">
                                        <div class="col-xs-1 text-right">
                                            <label class="control-label">Inv No </lable>
                                        </div>
                                        <div class="col-md-2 form-group" id="invoicenopanel">
                                            <div class="input-group">
                                                <input type="text" style="text-transform:uppercase" class="form-control" id="invoiceNo" name="invoiceNo" value="" onkeydown="invoiceNoValidate()">
                                            </div>
                                        </div>
                                        <div class="col-xs-1  text-right" style="width: 8px;padding-top: 7px"><i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i></div>
                                        <div class="col-md-1 text-right">
                                            <button type="button"  id="btnSearchInvoiceNo"  name="btnSearchInvoiceNo" onclick="searchInvoiceNo()" class="btn btn-primary btn-sm">
                                                <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                                            </button>                                          
                                        </div>      
                                    </div>
                                    <div class="col-xs-12 form-group"></div>
                                    <div class="row" style="padding-left:35px">    
                                        <div class="col-md-12">
                                            <table id="InvoiceListTable" class="display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr class="datatable-header">
                                                        <th style="width: 15%" >Product</th>
                                                        <th style="width: 35%">Description</th>
                                                        <th style="width: 20%">Cost</th>
                                                        <th style="width: 10%">Cur</th>
                                                        <th style="width: 20%">Amount</th>
                                                        <th style="width: 10%">Cur</th>
                                                        <th style="width: 1%">Action</th>
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
            </div>-->
            <!--</div>-->
            <!--Search-->  
            <div class="panel panel-default ${panelborder}">
                <div class="panel-heading ${panelheader}">
                    <h4 class="panel-title"><font style="color: white">Tax Invoice Detail</font></h4>
                </div>
                <div class="panel-body"  style="padding-right: 0px;">
                    <div class="col-xs-12 " style="margin-top: -10px">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Tax Invoice No</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <input type="hidden" class="form-control" id="department" name="department" value="${taxInvoice.department}"/>
                            <input type="hidden" class="form-control" id="TaxInvId" name="TaxInvId" value="${taxInvoice.id}"/>
                            <input type="hidden" class="form-control" id="TaxInvStatus" name="TaxInvStatus" value="${taxInvoice.MFinanceItemstatus.id}"/>
                            <input type="hidden" class="form-control" id="createDate" name="createDate" value="${requestScope['createDate']}"/>
                            <input type="hidden" class="form-control" id="createBy" name="createBy" value="${taxInvoice.createBy}"/>
                            <!--<input type="hidden" class="form-control" id="postDate" name="postDate" value="${requestScope['postDate']}"/>-->
                            <!--<input type="hidden" class="form-control" id="outputTaxStatus" name="outputTaxStatus" value="${taxInvoice.outputTaxStatus}"/>-->
                            <input type="hidden" class="form-control" id="wildCardSearch" name="wildCardSearch"  value="${requestScope['wildCardSearch']}" >
                            <input type="hidden" class="form-control" id="keyCode" name="keyCode"  value="" >                           
                            <input type="text" style="text-transform:uppercase" class="form-control" id="TaxInvNo" name="TaxInvNo" value="${taxInvoice.taxNo}" >
                        </div>
                        <div class="col-md-1" >
                            <button type="button"  id="btnSearchTaxInvoiceNo"  name="btnSearchTaxInvoiceNo" onclick="searchTaxInvoiceNo()" class="btn btn-primary btn-sm">
                                <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                            </button>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 180px;">
                            <label class="control-label" for="">Tax Invoice date<font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <div class='input-group date' id='InputDatePicker'>
                            <c:if test='${taxInvoice.taxInvDate != null}'>
                                <input id="InvToDate" name="InvToDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['invToDate']}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>

                            </c:if>
                            <c:if test='${taxInvoice.taxInvDate == null}'>
                                <input id="InvToDate" name="InvToDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['defaultInvToDate']}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>

                            </c:if>                             
                            </div>               
                        </div>
                    </div>    
                    <div class="col-xs-12 " style="margin-top: -10px">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Tax Inv To<font style="color: red">*</font></lable>
                        </div>
                        <div class="form-group col-xs-1 text-right" style="width: 265px">
                            <div class="input-group">
                                <input type="text" class="form-control" id="TaxInvTo" name="TaxInvTo" maxlength="11" value="${taxInvoice.taxInvTo}" style="text-transform:uppercase"/>
                                <span class="input-group-addon" id="TaxInvTo_Modal"  data-toggle="modal" data-target="#TaxInvToModal">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>        
                        <div class="col-md-1 text-right" style="width: 160px;">
                            <label class="control-label" for="" >A/R&nbsp;Code<font style="color: red">*</font></label>
                        </div>  
                        <div class="col-md-2 form-group" >
                            <input type="hidden" class="form-control" id="ARCodeId" name="ARCodeId" value=""/>
                            <input type="text" maxlength="20" class="form-control" id="ARCode" name="ARCode" value="${taxInvoice.arCode}" style="background-color: #ffffff">                              
                        </div>        
                    </div>
                    <div class="col-xs-12 " style="margin-top: -10px">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Name </lable>
                        </div>    
                        <div class="col-md-1 form-group" style="width: 585px">
                            <input  type="text" maxlength="255" id="InvToName" name="InvToName" class="form-control" value="${taxInvoice.taxInvName}">
                        </div>  
                    </div>
                    <div class="col-xs-12 " style="margin-top: -10px">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Address<font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-1 form-group" style="width: 585px">
                            <textarea maxlength="255" rows="3" cols="100" id="InvToAddress" name="InvToAddress" class="form-control" value="" >${taxInvoice.taxInvAddr}</textarea>
                        </div>
                    </div>
                    <div class="col-xs-12 " style="margin-top: -10px">
                        <div class="col-md-2 text-left hidden">
                            <label class="control-label" for="">Passenger</label>
                        </div>                       
                        <div class="col-md-2 form-group hidden">
                            <div class="input-group">
                            <input type="hidden" class="form-control" id="PassengerId" name="PassengerId" value=""/>
                            <input type="text" class="form-control" id="PassengerCode" name="PassengerCode" value="" style="background-color: #ffffff">
                            <span class="input-group-addon" id="Passenger_Modal"  data-toggle="modal" data-target="#PassengerModal">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                            </div>
                        </div>
                        <div class="col-md-3 form-group hidden">
                            <input type="text"  class="form-control" id="PassengerName" name="PassengerName"  value="" readonly="">
                        </div>                       
                    </div>
                    <div class="col-xs-12 " style="margin-top: -20px">
                        <div class="col-md-1 text-left" style="width: 100px;">
                            <label class="control-label" for="">Search</lable>                           
                        </div>
                        <div class="col-md-1 text-left">
                            <div class="col-xs-1  text-right" style="padding: 10px 20px 0px 0px; "><i id="ajaxloadsearch"  class="fa fa-spinner fa-spin hidden"></i></div>
                        </div>                        
                        <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" onclick="showSearchInvoiceNo()">
                            <span id="SpanEdit${advanced.search}">Invoice No.</span>
                        </a>                                                   
                        <label class="control-label text-right" sty>&nbsp;&nbsp;/&nbsp;&nbsp;</label>        
                        <a class="${outbound}" data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" onclick="showSearchRefNo()">
                            <span id="SpanEdit${advanced.search}">Ref No</span>
                        </a>                                                       
                    </div>
                    <div class="col-xs-12 " id="searchInvoiceNo1">
                        <div class="col-xs-1 text-left" style="width: 160px;">
                            <label class="control-label text-right">Invoice No</label>
                        </div>
                        <div class="col-xs-2 form-group" id="invoicenopanel" style="width: 180px">
                            <div class="input-group">
                                <input type="text" style="text-transform:uppercase" class="form-control" id="invoiceNo" name="invoiceNo" value="" onkeydown="invoiceNoValidate()">
                            </div>
                        </div>                        
                        <div class="col-xs-1 text-left"  style="width: 100px">
                            <button type="button"  id="btnSearchInvoiceNo"  name="btnSearchInvoiceNo" onclick="searchInvoiceNo()" class="btn btn-primary btn-sm">
                                <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                            </button> 
                        </div>
                    </div> 
                    <div class="col-xs-12 hidden" id="searchInvoiceNo2" style="padding: 0px 15px 0px 0px;">
                        <table id="InvoiceListTable" class="display" cellspacing="0" width="100px">
                            <thead>
                                <tr class="datatable-header">
                                    <th style="width: 15%" >Product</th>
                                    <th style="width: 35%">Description</th>
                                    <th style="width: 20%">Cost</th>
                                    <th style="width: 10%">Cur</th>
                                    <th style="width: 20%">Amount</th>
                                    <th style="width: 10%">Cur</th>
                                    <th style="width: 1%">Action</th>
                                </tr>
                            </thead>
                            <tbody>               

                            </tbody>
                        </table>        
                    </div>         
                    <div class="col-xs-12 hidden " id="searchRefNo1">
                        <div class="col-xs-1 text-left" style="width: 160px;">
                            <label class="control-label text-right">Ref No</label>
                        </div>
                        <div class="col-xs-1 form-group" style="width: 180px" id="refnopanel">
                            <div class="input-group">
                                <input id="refNo" name="refNo" type="text" class="form-control" value="" onkeydown="refnoValidate()" >
                            </div>
                        </div>
                        <div class="col-xs-1 text-left"  style="width: 100px">
                            <button style="height:30px" type="button"  id="btnSearchRefNo"  name="btnSearchRefNo" onclick="searchRefNo();" class="btn btn-primary btn-sm" ><i class="fa fa-search"></i>&nbsp;Search </button>
                        </div>
                        <div class="col-md-5 ">
                            <div id='AlertBooking' style='display:none'><font color="red">This Ref No can get billable detail from outbound only.</font></div>  
                        </div>      
                    </div>
                    <div class="col-xs-12 hidden" id="searchRefNo2" style="padding: 0px 15px 0px 0px;">
                        <table id="RefNoListTable" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr class="datatable-header" >
                                    <th style="width:1%;">No</th>
                                    <th style="width:20%;">Description</th>
                                    <th style="width:10%;">Cost</th>
                                    <th style="width:5%;">Cur</th>
                                    <th style="width:10%;">Amount</th>
                                    <th style="width:5%;">Cur</th>
                                    <th style="width:10%;">Ex Rate</th>
                                    <th style="width:10%;">Profit</th>
                                    <th style="width:1%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                                    
                            </tbody>
                        </table>
                    </div>    
                </div>             
            <!--</div>-->
            <!--<div role="tabpanel">-->
                <div class="tab-content" style="margin-top: -25px">
                    <div role="tabpanel" class="tab-pane  active" id="infoMasterProduct">
                        <!--<div class="panel panel-default ${panelborder}">-->                              
                            <div class="panel-body">
                                <div id="textAlertCurrencyAmountNotEmpty"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <strong>Currency Amount Not Empty</strong> 
                                </div>
                                <div id="textAlertCurrencyAmountNotMatch"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <strong>Currency Amount Not Match</strong> 
                                </div>
                                <div class="row" style="">    
                                    <div class="col-md-12">
                                        <input type="hidden" class="form-control" id="vatDefault" name="vatDefault" value="${vatDefault}"/>
                                        <input type="hidden" class="form-control" id="countTaxInvoice" name="countTaxInvoice" value="1" >
                                        <table id="TaxInvoiceTable" class="display" cellspacing="0" width="100%">
                                            <thead>
                                                <tr class="datatable-header">
                                                    <th style="width: 1%" class="hidden">Id</th>
                                                    <th style="width: 10%" >Product</th>
                                                    <th style="width: 10%" >Ref No</th>
                                                    <th style="width: 19%">Description</th>
                                                    <th style="width: 10%" >Cost</th>
                                                    <th style="width: 8%" >Cur</th>
                                                    <th style="width: 5%" onclick="checkVatAll()"><u>Is vat</u></th>
                                                    <th style="width: 5%" >Vat</th>
                                                    <th style="width: 10%" >Gross</th>
                                                    <th style="width: 10%">Amount</th>
                                                    <th style="width: 8%">Cur</th>
                                                    <th style="width: 4%">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="taxDetail" items="${taxInvoiceDetail}" varStatus="i">
                                                <tr>
                                                    <td class="hidden"><input class="form-control" type="text" id="taxDetailId${i.count}" name="taxDetailId${i.count}" value="${taxDetail.id}"></td>
                                                    <td class="hidden"><input class="form-control" type="text" id="invoiceDetailId${i.count}" name="invoiceDetailId${i.count}" value="${taxDetail.invoiceDetail.id}"></td>
                                                    <td class="hidden"><input class="form-control" type="text" id="invoiceDetailCost${i.count}" name="invoiceDetailCost${i.count}" value="${taxDetail.invoiceDetail.cost}"></td>
                                                    <td class="hidden"><input class="form-control" type="text" id="invoiceDetailAmount${i.count}" name="invoiceDetailAmount${i.count}" value="${taxDetail.invoiceDetail.amount}"></td>
                                                    <td class="hidden"><input class="form-control" type="text" id="isExport${i.count}" name="isExport${i.count}" value="${taxDetail.isExport}"></td>
                                                    <td class="hidden"><input class="form-control" type="text" id="isProfit${i.count}" name="isProfit${i.count}" value="${taxDetail.isProfit}"></td>
                                                    <td class="hidden">
                                                        <input class="form-control" type="text" id="exportDate${i.count}" name="exportDate${i.count}" value="<fmt:formatDate type="date" pattern='yyyy-MM-dd HH:mm:ss' value="${taxDetail.exportDate}" />">
                                                    </td>
                                                    <td class="hidden">
                                                        <input class="form-control" type="text" id="createDateDetail${i.count}" name="createDateDetail${i.count}" value="<fmt:formatDate type="date" pattern='yyyy-MM-dd' value="${taxDetail.createDate}" />">
                                                    </td>
                                                    <td>
                                                        <select class="form-control" name="product${i.count}" id="product${i.count}" onchange="AddrowBySelect('${i.count}')">
                                                            <option  value="" >---------</option>
                                                        <c:forEach var="product" items="${product_list}" varStatus="status">                                       
                                                            <c:set var="select" value="" />
                                                            <c:if test="${product.id == taxDetail.mbillType.id}">
                                                                <c:set var="select" value="selected" />
                                                            </c:if>
                                                            <option  value="${product.id}" ${select}>${product.name}</option>
                                                        </c:forEach>
                                                        </select>      
                                                    </td>
                                                    <td><input class="form-control" type="text" maxlength="6" id="refNo${i.count}" name="refNo${i.count}" value="${taxDetail.master.referenceNo}" onfocusout="checkRefNo('${i.count}')"></td>
                                                    <td><input class="form-control" type="text" maxlength="255" id="description${i.count}" name="description${i.count}" value="${taxDetail.description}"></td>
                                                    <td align="right"><input class="form-control decimal" style="text-align:right;" type="text" id="cost${i.count}" name="cost${i.count}" value="${taxDetail.cost}" onfocusout="CalculateAmountTotal()"></td>
                                                    <td>
                                                        <select class="form-control" name="currencyCost${i.count}" id="currencyCost${i.count}" onchange="AddrowBySelect(${i.count})">
                                                            <option  value="" >---------</option>
                                                        <c:forEach var="currency" items="${currency_list}" varStatus="status">                                       
                                                            <c:set var="select" value="" />
                                                            <c:if test="${currency.code == taxDetail.curCost}">
                                                                <c:set var="select" value="selected" />
                                                            </c:if>
                                                            <option  value="${currency.code}" ${select}>${currency.code}</option>
                                                        </c:forEach>
                                                        </select>        
                                                    </td>
                                                    <td align="center">
                                                        <c:set var="vatChk" value="" />
                                                        <c:if test="${'1' == taxDetail.isVat}">
                                                            <c:set var="vatChk" value="checked" />
                                                        </c:if>  
                                                        <input type="checkbox" id="isVat${i.count}" name="isVat${i.count}" value="1" ${vatChk} onclick="CalculateGross('${i.count}')">
                                                    </td>
                                                    <td align="center" id="vatShow${i.count}">
                                                        <c:if test="${'1' == taxDetail.isVat}">
                                                            <fmt:parseNumber var="vatShow" type="number" value="${taxDetail.vat}" />
                                                            ${vatShow}
                                                        </c:if>    
                                                    </td>
                                                    <td class="hidden"><input class="form-control" style="text-align:right;" type="text" id="vat${i.count}" name="vat${i.count}" readonly="" value="${taxDetail.vat}"></td>
                                                    <td align="right"><input class="form-control decimal" style="text-align:right;" type="text" id="gross${i.count}" name="gross${i.count}" value="${taxDetail.gross}" readonly=""></td>
                                                    <td class="hidden"><input class="form-control" style="text-align:right;" type="text" id="grossTemp${i.count}" name="grossTemp${i.count}" value="${taxDetail.gross}"></td>
                                                    <td align="right"><input class="form-control decimal" style="text-align:right;" type="text" id="amount${i.count}" name="amount${i.count}" value="${taxDetail.amount}" onfocusout="CalculateAmountTotal('${i.count}')"></td>
                                                    <td>
                                                        <select class="form-control" name="currencyAmount${i.count}" id="currencyAmount${i.count}" onchange="AddrowBySelect(${i.count}); CalculateAmountTotal('');">
                                                            <option  value="" >---------</option>
                                                        <c:forEach var="currency" items="${currency_list}" varStatus="status">                                       
                                                            <c:set var="select" value="" />
                                                            <c:if test="${currency.code == taxDetail.curAmount}">
                                                                <c:set var="select" value="selected" />
                                                            </c:if>
                                                            <option  value="${currency.code}" ${select}>${currency.code}</option>
                                                        </c:forEach>
                                                        </select>        
                                                    </td>
                                                    <td class="text-center">
                                                        <a id="expenButtonRemove${i.count}" name="expenButtonRemove${i.count}" onclick="deleteTaxList('${taxDetail.id}','${i.count}')"  data-toggle="modal" data-target="#DeleteExpenModal">
                                                            <span id="expenSpanRemove${i.count}" name="expenSpanRemove${i.count}"  class="glyphicon glyphicon-remove deleteicon"></span>
                                                        </a>
                                                    </td>
                                                </tr>
                                                </c:forEach>    
                                            </tbody>
                                        </table>
                                        <div id="tr_TaxInvoiceDetailAddRow" class="text-center hide" style="padding-top: 10px">
                                            <a class="btn btn-success" onclick="AddRowTaxInvoiceTable()">
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
<!--                                    <div class="col-xs-12 ">
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Remark&nbsp;</lable>                                         
                                        </div>
                                        <div class="col-sm-6" style="padding-left: 50px">
                                            <textarea  rows="3" cols="200" id="Remark" name="Remark" class="form-control" value="">${taxInvoice.remark}</textarea>
                                        </div>
                                    </div>-->
<!--                                    <div class="col-xs-12 form-group"></div>-->
                                    <div class="col-xs-12 ">
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Text&nbsp;Amount&nbsp;:</lable>                                         
                                        </div>                                      
                                        <div class="col-sm-6" style="padding-left: 50px">
                                            <textarea rows="3" cols="200" id="TextAmount" name="TextAmount" class="form-control" readonly=""></textarea>
<!--                                            <input  rows="3" cols="200" id="TextAmount" name="TextAmount" class="form-control" value="" readonly="">-->
                                        </div>
                                        <div class="col-sm-1" style="width: 110px">
                                            <label class="control-label" for="">Total&nbsp;Amount&nbsp;:</lable>                                         
                                        </div>
                                        <div class="col-sm-1" style="width: 285px">
                                            <input style="text-align: right" id="TotalAmount" name="TotalAmount" class="form-control" value="" readonly="">
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
                                        <div class="col-md-1 text-right hidden" style="width: 200px">
                                            <select class="form-control" name="select_print" id="select_print">
                                                <option  value="">--- Select Print ---</option>
                                                <option  value="taxInvoice">Tax Invoice</option>
                                                <option  value="taxInvoiceEmail">Tax Invoice Email</option>
                                            </select>
                                        </div>
                                        <div class="col-md-1 text-left " style="width: 160px">
                                            <c:set var="print" value="" />
                                            <c:if test="${(taxInvoice.id == '') || (taxInvoice.id == null) }">        
                                                <c:set var="print" value="disabled='true'" />
                                            </c:if>
                                            <button type="button" onclick="selectPrintTaxInvoiceReport()" class="btn btn-default" ${print}>
                                                <span id="SpanPrintInvoiceNew" class="glyphicon glyphicon-print"></span> Print Tax Invoice
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-left " style="width: 170px">
                                            <c:set var="print" value="" />
                                            <c:if test="${(taxInvoice.id == '') || (taxInvoice.id == null) }">        
                                                <c:set var="print" value="disabled='true'" />
                                            </c:if>
                                            <button type="button" onclick="selectPrintTaxInvoiceEmail()" class="btn btn-default" ${print}>
                                                <span id="SpanPrintInvoiceNew" class="glyphicon glyphicon-print"></span> Print Tax Invoice Email
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-left hidden">
                                            <c:set var="print" value="" />
                                            <c:if test="${(taxInvoice.id == '') || (taxInvoice.id == null) }">        
                                                <c:set var="print" value="disabled='true'" />
                                            </c:if>
                                            <button type="button" onclick="selectPrintType()" class="btn btn-default" ${print}>
                                                <span id="SpanPrintInvoiceNew" class="glyphicon glyphicon-print"></span> Print
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right" style="width: 170px">
                                            <c:set var="sendemail" value="" />
                                            <c:if test="${(taxInvoice.id == '') || (taxInvoice.id == null) }">        
                                                <c:set var="sendemail" value="disabled='true'" />
                                            </c:if>
                                            <button type="button" class="btn btn-default" onclick="selectEmailType()" ${sendemail}>
                                                <span id="buttonEmail" class="glyphicon glyphicon-send" ></span> SendEmail 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-left hidden" style="width: 170px">
                                            <c:set var="copy" value="" />
                                            <c:if test="${(taxInvoice.id == '') || (taxInvoice.id == null) }">        
                                                <c:set var="copy" value="disabled='true'" />
                                            </c:if>
                                            <button type="button" class="btn btn-default" onclick="copyTaxInvoice()" ${copy}>
                                                <span id="ButtonCopy" class="glyphicon glyphicon-copyright-mark" ></span> Copy 
                                            </button>
                                        </div>
                                        <div class="col-md-1 " style="width: 120px"></div>
                                        <div class="col-md-2 text-right">
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
                                            <c:if test="${taxInvoice.MFinanceItemstatus.id == '2'}">        
                                                <c:set var="isDisableVoid" value="style='display: none;'" />                                             
                                                <c:set var="isEnableVoid" value="" />
                                                <c:set var="isSaveVoid" value="disabled='true'" />
                                            </c:if>
                                            <c:if test="${result =='cancelvoid'}">        
                                                <c:set var="isDisableVoid" value="" />
                                            </c:if>
                                            <c:if test="${taxInvoice.MFinanceItemstatus.id == '1'}">        
                                                <c:set var="isDisableVoid" value="" />
                                            </c:if>
                                            <button type="button" class="btn btn-primary" onclick="EnableVoidInvoice();" data-toggle="modal" data-target="#EnableVoid" id="enableVoidButton" name="enableVoidButton"  ${isEnableVoid}>
                                                <span id="SpanEnableVoid" class="glyphicon glyphicon-ok" ></span> Cancel Void
                                            </button>
                                            
                                            <button type="button" class="btn btn-danger" onclick="DisableVoidInvoice();" data-toggle="modal" data-target="#DisableVoid" id="disableVoidButton" name="disableVoidButton" ${isDisableVoid} >
                                                <span id="SpanDisableVoid" class="glyphicon glyphicon-remove" ></span> Void
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-left ">
                                            <button type="button" onclick="validateForm()" class="btn btn-success" ${isSaveVoid}>
                                                <span id="SpanSave" class="fa fa-save"></span> Save 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-left ">
                                            <button type="button" onclick="clearScreen()" class="btn btn-success" >
                                                <span id="SpanNew" class="fa fa-plus-circle"></span> New 
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
            <input type="hidden" id="action" name="action" value="save">
            </form>
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
                Are you confirm to void invoice ${taxInvoice.taxNo}?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick='DisableInvoice()' data-dismiss="modal">Cancel Void</button>               
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
                Are you confirm to cancel void invoice ${taxInvoice.taxNo}?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal" onclick='Enable()'>Enable</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->                                                
                                            
<!--Modal Search Tax Inv To-->
<div class="modal fade" id="TaxInvToModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Tax Invoice To</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <div style="text-align: right"> <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchTaxInvoiceFrom" name="searchTaxInvoiceFrom"/> </div> 
                <table class="display" id="SearchTaxInvoiceToTable">
                    <thead class="datatable-header">
                        <script>
                            var taxInvoiceTo = [];
                        </script>
                        <tr>
                            <th style="width: 30%">Code</th>
                            <th style="width: 70%">Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customerAgent" items="${customer_agent_list}">
                            <tr onclick ="setBillValue('${customerAgent.billTo}', '${customerAgent.billName}', '${customerAgent.address}', '${customerAgent.term}', '${customerAgent.pay}')">
                                <td class="item-billto">${customerAgent.billTo}</td>
                                <td class="item-name">${customerAgent.billName}</td>                                
                                <td class="item-address hidden">${customerAgent.address}</td>
                                <td class="item-tel hidden">${customerAgent.tel}</td>
                            </tr>
                            <script>
                                taxInvoiceTo.push({name: "${customerAgent.billTo}", billTo: "${customerAgent.billName}", address: "${customerAgent.address}", term: "${customerAgent.term}", pay: "${customerAgent.pay}"});
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

<!--Modal Search Passenger -->
<div class="modal fade" id="PassengerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Passenger</h4>
            </div>
            <div class="modal-body">
                <!--Passenger List Table-->
                <table class="display" id="PassengerTable">
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
                <div class="text-right">
                    <button id="SearchPassengerModal" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--A/R Code Modal-->
<div class="modal fade" id="ARCodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">A/R Code</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->

                <table class="display" id="ARCodeTable">
                    <thead>    
                        <script>
                            var arcode = [];
                        </script>
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                        </tr>                      
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${agent_list}">
                            <tr onclick ="setupagentvalue('${table.id}', '${table.code}', '${table.name}')" >
                                <td class="hidden">${table.id}</td>
                                <td>${table.code} </td>
                                <td>${table.name} </td>
                            </tr>    
                            <script>
                                arcode.push({id: "${table.id}", code: "${table.code}", name: "${table.name}"});
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

<!--Delete Tax Invoice Modal-->
<div class="modal fade" id="delTaxInvoiceDetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <c:choose>
                    <c:when test="${fn:contains(page , 'W')}">
                        <h4 class="modal-title">Delete Tax Invoice Wendy</h4>
                    </c:when>
                    <c:when test="${fn:contains(page , 'O')}">
                        <h4 class="modal-title">Delete Tax Invoice Outbound</h4>
                    </c:when> 
                    <c:when test="${fn:contains(page , 'I')}">
                        <h4 class="modal-title">Delete Tax Invoice Inbound</h4>
                    </c:when> 
		</c:choose>                
            </div>
            <div class="modal-body" id="delCode">
                
            </div>
            <input class="hidden" id="delTaxDetailId" name="delTaxDetailId" value="">
            <input class="hidden" id="delTaxDetailRow" name="delTaxDetailRow" value="">
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="deleteTaxInvoiceDetailList()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--Print Modal-->
<div class="modal fade" id="PrintTaxInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Print Tax Invoice</h4>
            </div>
            <div class="modal-body" id="printReceiptModal" >
                <div class="col-xs-1" style="width: 280px">
                    <label class="text-right">Select option for print tax invoice<font style="color: red">*</font></label>                                    
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
                <input type="hidden" id="printType" name="printType" value=""/>
                <button type="button" class="btn btn-default" onclick="printTaxInvoice()"  data-dismiss="modal">
                    <span id="buttonPrint" class="glyphicon glyphicon-print" ></span> Print 
                </button>          
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!--Copy Tax Invoice Modal-->
<div class="modal fade" id="CopyTaxInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Copy Tax Invoice</h4>
            </div>
            <div class="modal-body" id="copyReceiptModal" >
                <label class="text-right">Are you sure to copy tax invoice ?</label>                                  
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-default" onclick="confirmCopyTaxInvoice()">
                    <span id="buttonCopyTaxInvoice" class="glyphicon glyphicon-copyright-mark" ></span> Copy
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!--Email Modal-->
<div class="modal fade" id="EmailTaxInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Send Email Tax Invoice</h4>
            </div>
            <div class="modal-body" id="printReceiptModal" >
                <div class="col-xs-1" style="width: 320px">
                    <label class="text-right">Select option for send email tax invoice<font style="color: red">*</font></label>                                    
                </div>
                <div class="col-xs-1" style="width: 200px" >
                    <select name="optionsend" id="optionsend" class="form-control" style="height:34px">
                        <option value="1" >Not Show Description</option>
                        <option value="2" >Show Description</option>
                        <option value="3" >Print Format Package Tour</option>
                    </select>
                </div>
                </br>
                </br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="sendEmailTaxInvoice()"  data-dismiss="modal">
                    <span id="buttonPrint" class="glyphicon glyphicon-send" ></span> SendEmail 
                </button>          
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<select class="form-control hidden" name="select_product_list" id="select_product_list">
    <c:forEach var="product" items="${product_list}" varStatus="status">                                              
        <option  value="${product.id}">${product.name}</option>
    </c:forEach>
</select>

<select class="form-control hidden" name="select_currency_list" id="select_currency_list">
    <c:forEach var="currency" items="${currency_list}" varStatus="status">                                       
        <option  value="${currency.code}">${currency.code}</option>
    </c:forEach>
</select>        

<script language="javascript">
    var showflag = 1;
    $(document).ready(function () {
        
        $("#inv,#ref").removeClass('hidden');
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
        $(".money").mask('000,000,000.00', {reverse: true});
        $(".money2").mask('#,##0.00;-#,##0.00', {reverse: true});
        $(".money3").mask('000,000,000.0000', {reverse: true});
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        
        $('#PassengerTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": true,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 3
        });
        
//        $('#SearchTaxInvoiceToTable').dataTable({bJQueryUI: true,
//            "sPaginationType": "full_numbers",
//            "bAutoWidth": false,
//            "bFilter": false,
//            "bPaginate": true,
//            "bInfo": false,
//            "bLengthChange": false,
//            "iDisplayLength": 10
//        });
        
        var SearchTaxInvoiceToTable = $('#SearchTaxInvoiceToTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });

                        $('#SearchTaxInvoiceToTable tbody').on('click', 'tr', function() {
                            $('.collapse').collapse('show');
                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                            }
                            else {
                                SearchTaxInvoiceToTable.$('tr.row_selected').removeClass('row_selected');
                                $(this).addClass('row_selected');
                            }
                        });
        
        $('#ARCodeTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
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
                          
        $(".numerical").on('input', function() { 
            var value=$(this).val().replace(/[^0-9.,]*/g, '');
            value=value.replace(/\.{2,}/g, '.');
            value=value.replace(/\.,/g, ',');
            value=value.replace(/\,\./g, ',');
            value=value.replace(/\,{2,}/g, ',');
            value=value.replace(/\.[0-9]+\./g, '.');
            $(this).val(value);
        });
        
        $('#collapseExample').on('shown.bs.collapse', function () {
            $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-down").addClass("glyphicon glyphicon-chevron-up");
        });

        $('#collapseExample').on('hidden.bs.collapse', function () {
           $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-up").addClass("glyphicon glyphicon-chevron-down");
        });
        
        $('#InputDatePicker').datetimepicker().on('dp.change', function (e) {
                $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'InvToDate');
        });
        
        $('#TaxInvoiceForm').bootstrapValidator({
            container: 'tooltip',
            excluded: [':disabled'],
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                TaxInvTo: {
                    validators: {
                        notEmpty: {
                            message: 'The Tax Invoice No. is required'
                        }
                    }
                },
                ARCode: {
                    validators: {
                        notEmpty: {
                            message: 'The A/R Code is required'
                        }
                    }
                },
                InvToDate: {
                    validators: {
                        notEmpty: {
                            message: 'The Invoice Date is required'
                        }
                    }
                },
                InvToAddress: {
                    validators: {
                        notEmpty: {
                            message: 'The Address is required'
                        }
                    }
                }
            }
        });
        
        var wildCardSearch = ($("#wildCardSearch").val()).indexOf("%");
//        if($("#TaxInvId").val() !== ''){
            $("#TaxInvNo").focus();
//        }        
        $("#TaxInvNo").keyup(function (event) {           
//            var wildCardSearch = ($("#TaxInvNo").val()).indexOf("%");
            if (event.keyCode === 13) {
                searchTaxInvoiceNo();
            
            } else if(event.keyCode === 38){
                if((parseInt(wildCardSearch) >= 0) || ($("#TaxInvId").val() !== '')){
                    $("#keyCode").val(event.keyCode);
                    var action = document.getElementById('action');
                    action.value = 'wildCardSearch';
                    document.getElementById('TaxInvoiceForm').submit();
                }
            
            } else if(event.keyCode === 40){
                if((parseInt(wildCardSearch) >= 0) || ($("#TaxInvId").val() !== '')){
                    $("#keyCode").val(event.keyCode);
                    var action = document.getElementById('action');
                    action.value = 'wildCardSearch';
                    document.getElementById('TaxInvoiceForm').submit();
                }
            
            } else if(event.keyCode === 118){
                $("#keyCode").val(event.keyCode);
                var action = document.getElementById('action');
                action.value = 'new';
                document.getElementById('TaxInvoiceForm').submit();
            
            } else if(event.keyCode === 119){
                $("#keyCode").val(event.keyCode);
                var action = document.getElementById('action');
                action.value = 'wildCardSearch';
                document.getElementById('TaxInvoiceForm').submit();

            }
        });
        
        $("#invoiceNo").keyup(function (event) {
            if (event.keyCode === 13) {
                searchInvoiceNo();
            }
        });
        
        $("#refNo").keyup(function (event) {
            if(event.keyCode === 13){
               searchRefNo();
            }
        });
        
        var rowTaxInvoiceTable = $("#TaxInvoiceTable tr").length;
        AddRowTaxInvoiceTable(rowTaxInvoiceTable);
        
        $("#TaxInvoiceTable").on("keyup", function () {
            var rowAll = $("#TaxInvoiceTable tr").length;
            $("td").keyup(function () {
                if ($(this).find("input").val() !== '') {
                    var colIndex = $(this).parent().children().index($(this));
                    var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                    rowAll = $("#TaxInvoiceTable tr").length;
//                    alert("rowIndex = "+rowIndex);
//                    alert("rowAll = "+rowAll);
                    if (rowIndex == rowAll) {
                        console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                        AddRowTaxInvoiceTable(parseInt($("#countTaxInvoice").val()));
                    }
                    if (rowAll < 2) {
                        $("#tr_TaxInvoiceDetailAddRow").removeClass("hide");
                        $("#tr_TaxInvoiceDetailAddRow").addClass("show");
                    }
                }
            });
        });
        
        //autocomplete
        $("#TaxInvTo").keyup(function(event){ 
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left); 
            if($(this).val() === ""){
                $("#TaxInvTo").val("");
                $("#InvToName").val("");
                $("#InvToAddress").val("");
                $("#ARCode").val("");
            }else{
                if(event.keyCode === 13){
                    searchCustomerAgentAutoList(this.value); 
                }
            }
        });
        
        $("#TaxInvTo").keydown(function(){
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left); 
            if(showflag == 0){
                $(".ui-widget").css("top", -1000);
                showflag=1;
            }
        });
        
        $('#TotalAmount').ready(function () {
            CalculateAmountTotal();
            setGross();
        });
        
        $("#searchTaxInvoiceFrom").keyup(function(event) {
            if (event.keyCode === 13) {
//                if ($("#searchTaxInvoiceFrom").val() !== "") {
                    // alert('please input data');
                    searchCustomerAgentList($("#searchTaxInvoiceFrom").val());
//                }               
            }
        });
        
    });
    
    function selectPrintType(){
        var printType = document.getElementById('select_print').value;
        if(printType !== ""){
            $("#PrintTaxInvoiceModal").modal("show");
        }       
    }
    
    function selectPrintTaxInvoiceReport(){
//        var printType = document.getElementById('select_print').value;
        document.getElementById('printType').value = "taxInvoice";
        if(printType !== ""){
            $("#PrintTaxInvoiceModal").modal("show");
        }       
    }
    
    function selectPrintTaxInvoiceEmail(){
//        var printType = document.getElementById('select_print').value;
        document.getElementById('printType').value = "taxInvoiceEmail";
        if(printType !== ""){
            $("#PrintTaxInvoiceModal").modal("show");
        }       
    }
    
    function printTaxInvoice(){
        $("#PrintTaxInvoiceModal").modal("hide");
        var printType = document.getElementById('printType').value;
        var taxInvId = document.getElementById('TaxInvId').value;
        var optionPrint =  document.getElementById('optionPrint').value;
        var department = '${page}';

        if("W" === department){
            department = "Wendy";
        } else if("O" === department){
            department = "Outbound";
        } else if("I" === department){
            department = "Inbound";
        }
        
        if(printType === ""){
            alert("Please choose print type.");
        }else if(printType === "taxInvoice"){
            window.open("report.smi?name=TaxInvoiceReport&taxInvId="+taxInvId+"&department="+department+"&optionPrint="+optionPrint);
        }else if(printType === "taxInvoiceEmail"){
            window.open("report.smi?name=TaxInvoiceEmailReport&taxInvId="+taxInvId+"&department="+department+"&optionPrint="+optionPrint);
        }
    }
    
    function copyTaxInvoice(){
        var invDate = document.getElementById("InvToDate").value;
        if(invDate !== ''){
            $("#CopyTaxInvoiceModal").modal("show");
        }        
    }
    
    function confirmCopyTaxInvoice(){
        var row = $('#TaxInvoiceTable tr').length;
        document.getElementById('TaxInvId').value = '';
        document.getElementById('TaxInvNo').value = '';
        document.getElementById('createBy').value = '';
        document.getElementById('createDate').value = '';
        for(var i=1;i<row;i++){
            document.getElementById('taxDetailId' + i).value = '';
        }
        $("#CopyTaxInvoiceModal").modal("hide");
        document.getElementById('TaxInvoiceForm').submit();           
    }
    
    function selectEmailType(){
        $("#EmailTaxInvoiceModal").modal("show");      
    }  
    
    function sendEmailTaxInvoice(){
        $("#EmailTaxInvoiceModal").modal("hide");
        var taxInvId = document.getElementById('TaxInvId').value;
        var optionsend = document.getElementById('optionsend').value;
        window.open("SendMail.smi?reportname=TaxInvoiceEmail&reportid="+taxInvId+"&optionsend="+optionsend);    
    }
    
    function searchTaxInvoiceNo(){
        var action = document.getElementById('action');
        action.value = 'search';
        document.getElementById('TaxInvoiceForm').submit();
    }
    
    function setBillValue(billTo,billName,address,term,pay){      
        document.getElementById('TaxInvTo').value = billTo;
        document.getElementById('InvToName').value = billName;
        document.getElementById('ARCode').value = billTo;
        if(address === null){
            document.getElementById('InvToAddress').value = '';
        } else {
            document.getElementById('InvToAddress').value = address;
        }              
        $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'TaxInvTo');
        $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'ARCode');
        $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'InvToAddress');
        $('#TaxInvToModal').modal('hide');
    }
    
    function formatNumber(num) {
        return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
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
            } else {
                nField.value = tmp.replace(/ /,"");
            } 
        } else {
            nField.value = nField.value.replace(/[^\d\,\.]/g,"").replace(/ /,"");
        }
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
                    $('#SearchTaxInvoiceToTable').dataTable().fnClearTable();
                    $('#SearchTaxInvoiceToTable').dataTable().fnDestroy();
                    $("#SearchTaxInvoiceToTable tbody").empty().append(msg);

                    $('#SearchTaxInvoiceToTable').dataTable({bJQueryUI: true,
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
            $("#ajaxload").addClass("hidden");
            alert(e);
        }
    }
    
    function searchCustomerAgentAutoList(billTo){
        var servletName = 'BillableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&name=' + billTo +
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
        $("#TaxInvTo").autocomplete("destroy");
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
//                   $("#InvTo_Id").val(billid);
//                   $("#TaxInvTo").val(billid);
                   $("#ARCode").val(billid);
                   $("#InvToName").val(billname);
                   $("#InvToAddress").val(billaddr);

                   $("#TaxInvTo").autocomplete({
                       source: billArray,
                       close: function(){
                            $("#TaxInvTo").trigger("keyup");
                            var billselect = $("#TaxInvTo").val();
                            for(var i =0;i<billListId.length;i++){
                                if((billselect==billListName[i])||(billselect==billListId[i])){      
                                   $("#TaxInvTo").val(billListId[i]);
                                   $("#ARCode").val(billListId[i]);
                                   $("#InvToName").val(billListName[i]);
                                   $("#InvToAddress").val(billListAddress[i]);
                                   
                                   $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'TaxInvTo');
                                   $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'ARCode');
                                   $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'InvToAddress');
                                }                 
                            }   
                       }
                    });

                   var billval = $("#TaxInvTo").val();
                   for(var i =0;i<billListId.length;i++){
                       if(billval==billListName[i]){
                           $("#TaxInvTo").val(billListId[i]);
                           $("#ARCode").val(billListId[i]);
                           $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'TaxInvTo');
                           $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'ARCode');
                           $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'InvToAddress');
                       }
                   }
                   if(billListId.length == 1){
                       showflag = 0;
                       $("#TaxInvTo").val(billListId[0]);
                       $("#ARCode").val(billListId[0]);
                       $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'TaxInvTo');
                       $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'ARCode');
                       $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'InvToAddress');
                   }
                   var event = jQuery.Event('keydown');
                   event.keyCode = 40;
                   $("#TaxInvTo").trigger(event);

                }, error: function(msg) {
                   console.log('auto ERROR');
                   $("#dataload").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }
    
    function searchInvoiceNo(){
        if($("#ajaxloadsearch").hasClass("hidden")){
            var invoiceNo = $("#invoiceNo").val();
            var invoicenopanel = $("#invoicenopanel").val();
            var department = '${page}';
            if("W" === department){
                department = "Wendy";
            } else if("O" === department){
                department = "Outbound";
            } else if("I" === department){
                department = "Inbound";
            }
            if(invoiceNo === ""){
                if(!$('#invoicenopanel').hasClass('has-feedback')) {
                    $('#invoicenopanel').addClass('has-feedback');
                }
                $('#invoicenopanel').removeClass('has-success');
                $('#invoicenopanel').addClass('has-error');
            } else {
                var servletName = 'TaxInvoiceServlet';
                var servicesName = 'AJAXBean';
                var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&invoiceNo=' + invoiceNo +
                        '&department=' + department +
                        '&type=' + 'searchInvoiceNo';
                CallAjaxSearchInvoice(param);
            }
        }    
    }
    
    function CallAjaxSearchInvoice(param) {
        var url = 'AJAXServlet';
        $("#ajaxloadsearch").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function (msg) {
                    try { 
                        if(msg == "null"){
                            $('#InvoiceListTable > tbody  > tr').each(function() {
                                $(this).remove();
                            });
                            $("#searchInvoiceNo2").addClass("hidden");
                            document.getElementById("TaxInvTo").value = '';
                            document.getElementById("InvToName").value = '';
                            document.getElementById("InvToAddress").value = '';
                            document.getElementById("ARCode").value = '';
                            
                        }else{
                            $('#InvoiceListTable > tbody  > tr').each(function() {
                                $(this).remove();
                            });
                            $("#InvoiceListTable tbody").empty().append(msg);
                            
                            if(document.getElementById("receiveTaxInvTo")!==null && ($("#receiveTaxInvTo").val()!==undefined)){
                                document.getElementById("TaxInvTo").value = $("#receiveTaxInvTo").val();
                            } else {
                                document.getElementById("TaxInvTo").value = '';
                            }
                            if((document.getElementById("InvToName")!==null) && ($("#receiveInvToName").val()!==undefined)){
                                document.getElementById("InvToName").value = $("#receiveInvToName").val();
                            } else {
                                document.getElementById("InvToName").value = '';
                            }
                            
                            if((document.getElementById("InvToAddress")!==null) && ($("#receiveInvToAddress").val()!==undefined)){
                                document.getElementById("InvToAddress").value = $("#receiveInvToAddress").val();
                            } else {
                               document.getElementById("InvToAddress").value = ''; 
                            }
                            
                            if((document.getElementById("receiveARCode")!==null) && ($("#receiveARCode").val()!==undefined)){
                                document.getElementById("ARCode").value = $("#receiveARCode").val();
                            } else {
                                document.getElementById("ARCode").value = '';
                            }
                            
                            if(msg !== ''){
                                $("#searchInvoiceNo2").removeClass("hidden");
                            } else {
                                $("#searchInvoiceNo2").addClass("hidden");
                            }
                            
//                            if((document.getElementById("receiveInvToDate")!==null) && ($("#receiveInvToDate").val()!==undefined)){
//                                document.getElementById("InvToDate").value = $("#receiveInvToDate").val();
//                            } else {
//                                document.getElementById("InvToDate").value = '';
//                            }                          
                        }
                        $("#ajaxloadsearch").addClass("hidden");

                    } catch (e) {
                        alert(e);
                    }

                }, error: function (msg) {
                     $("#ajaxloadsearch").addClass("hidden");
                }
            });
        } catch (e) {
            alert('error');
        }
    }
    
    function searchRefNo() {
        if($("#ajaxloadsearch").hasClass("hidden")){
            var refNo = $("#refNo").val();
            if(refNo == ""){
                if(!$('#refnopanel').hasClass('has-feedback')) {
                    $('#refnopanel').addClass('has-feedback');
                }
                $('#refnopanel').removeClass('has-success');
                $('#refnopanel').addClass('has-error');
            }
            else{
                var servletName = 'TaxInvoiceServlet';
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
        $("#ajaxloadsearch").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function (msg) {
                    try {
                        if(msg == "null"){
                            $('#RefNoListTable > tbody  > tr').each(function() {
                                $(this).remove();
                            });
                            $("#searchRefNo2").addClass("hidden");
                            document.getElementById("TaxInvTo").value = '';
                            document.getElementById("InvToName").value = '';
                            document.getElementById("InvToAddress").value = '';
                            document.getElementById("ARCode").value = '';
                            document.getElementById("InvToDate").value = '';
                            $('#AlertBooking').hide();
                        }else if(msg == "I"){
                            $('#RefNoListTable > tbody  > tr').each(function() {
                                $(this).remove();
                            });
                            $("#searchRefNo2").addClass("hidden");
                            $('#AlertBooking').show();
                        }else{
                            $('#RefNoListTable > tbody  > tr').each(function() {
                                $(this).remove();
                            });
                            $("#RefNoListTable tbody").append(msg);
                            $('#AlertBooking').hide();

                            if(document.getElementById("receiveTaxInvTo")!==null && ($("#receiveTaxInvTo").val()!==undefined)){
                                document.getElementById("TaxInvTo").value = $("#receiveTaxInvTo").val();
                            } else {
                                document.getElementById("TaxInvTo").value = '';
                            }
                            if((document.getElementById("InvToName")!==null) && ($("#receiveInvToName").val()!==undefined)){
                                document.getElementById("InvToName").value = $("#receiveInvToName").val();
                            } else {
                                document.getElementById("InvToName").value = '';
                            }
                            
                            if((document.getElementById("InvToAddress")!==null) && ($("#receiveInvToAddress").val()!==undefined)){
                                document.getElementById("InvToAddress").value = $("#receiveInvToAddress").val();
                            } else {
                               document.getElementById("InvToAddress").value = ''; 
                            }
                            
                            if((document.getElementById("receiveARCode")!==null) && ($("#receiveARCode").val()!==undefined)){
                                document.getElementById("ARCode").value = $("#receiveARCode").val();
                            } else {
                                document.getElementById("ARCode").value = '';
                            }
                            if(msg !== ''){
                                $("#searchRefNo2").removeClass("hidden");
                            } else {
                                $("#searchRefNo2").addClass("hidden");
                            }
                        }
                        $("#ajaxloadsearch").addClass("hidden");

                    } catch (e) {
                        $('#RefNoListTable > tbody  > tr').each(function() {
                            $(this).remove();
                        });
                        $("#ajaxloadsearch").addClass("hidden");
                    }

                }, error: function (msg) {
                    $('#RefNoListTable > tbody  > tr').each(function() {
                        $(this).remove();
                    });
                    $("#ajaxloadsearch").addClass("hidden");
                }
            });
        } catch (e) {
            $('#RefNoListTable > tbody  > tr').each(function() {
                $(this).remove();
            });
        }
    }
    
    function invoiceNoValidate(){
        $('#invoicenopanel').removeClass('has-feedback');
        $('#invoicenopanel').addClass('has-success');
        $('#invoicenopanel').removeClass('has-error');  
    }
    
    function refnoValidate(){
        $('#refnopanel').removeClass('has-feedback');
        $('#refnopanel').addClass('has-success');
        $('#refnopanel').removeClass('has-error');  
    }
    
    function AddRowTaxInvoiceTable(row) {
        $('#TaxInvoiceTable tr input:last').removeClass('lastrow');
        if (!row) {
            row = 1;
        }
        $("#TaxInvoiceTable tbody").append(           
            '<tr>' +
            '<td class="hidden"><input class="form-control" type="text" id="taxDetailId' + row + '" name="taxDetailId' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailId' + row + '" name="invoiceDetailId' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailCost' + row + '" name="invoiceDetailCost' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailAmount' + row + '" name="invoiceDetailAmount' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="isExport' + row + '" name="isExport' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="exportDate' + row + '" name="exportDate' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="isProfit' + row + '" name="isProfit' + row + '" value=""></td>' +
            '<td><select class="form-control" name="product' + row + '" id="product' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
            '<td><input class="form-control" maxlength="6" type="text" id="refNo' + row + '" name="refNo' + row + '" value="" onfocusout="checkRefNo(\'' + row + '\')"></td>' +
            '<td><input class="form-control" type="text" maxlength="255" id="description' + row + '" name="description' + row + '" value=""></td>' +
            '<td><input class="form-control decimal" style="text-align:right;" type="text" id="cost' + row + '" name="cost' + row +'" value="" onfocusout="CalculateAmountTotal()"></td>' +
            '<td><select class="form-control" name="currencyCost' + row + '" id="currencyCost' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
            '<td align="center"><input type="checkbox" id="isVat' + row + '" name="isVat' + row + '" value="1" onclick="CalculateGross(\'' + row + '\')" checked></td>' +
            '<td align="center" id="vatShow' + row + '"></td>' +
            '<td class="hidden"><input class="form-control" style="text-align:right;" type="text" id="vat' + row + '" name="vat' + row + '" readonly=""></td>' +
            '<td><input class="form-control decimal" style="text-align:right;" type="text" id="gross' + row + '" name="gross' + row + '" value="0.00" readonly=""></td>' +
            '<td><input class="form-control decimal" style="text-align:right;" type="text" id="amount' + row + '" name="amount' + row + '" value="" onfocusout="CalculateAmountTotal(\'' + row + '\')"></td>' +
            '<td><select class="form-control" name="currencyAmount' + row + '" id="currencyAmount' + row + '" onchange="AddrowBySelect(\'' + row + '\'); CalculateAmountTotal(\'\');"><option  value="" >---------</option></select></td>' +
            '<td>' + 
                '<center>' +
                '<a id="expenButtonRemove' + row + '" name="expenButtonRemove' + row + '" onclick="deleteTaxList(\'\',\'' + row + '\')"  data-toggle="modal" data-target="#DeleteExpenModal">' + 
                '<span id="expenSpanEdit' + row + '" name="expenSpanEdit' + row + '" class="glyphicon glyphicon-remove deleteicon"></span>' +
                '</a>' + 
                '</center>' +
            '</td>' +
            '</tr>'           
        );
        $("#tr_TaxInvoiceDetailAddRow").removeClass("show");
        $("#tr_TaxInvoiceDetailAddRow").addClass("hide");
        $("#select_product_list option").clone().appendTo("#product" + row);
        $("#select_currency_list option").clone().appendTo("#currencyCost" + row);
        $("#select_currency_list option").clone().appendTo("#currencyAmount" + row);
//        var vatData = parseFloat($("#vatDefault").val());
        document.getElementById('vatShow'+row).innerHTML = parseFloat($("#vatDefault").val()); 
        document.getElementById('vat'+row).value = parseFloat($("#vatDefault").val());
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
        $('#TaxInvoiceTable input:last').addClass('lastrow');
        $("#refNo"+row+",#description"+row+",#cost"+row+",#gross"+row+",#amount"+row).focus(function() {
            if($("#amount"+row).hasClass("lastrow")){
               AddRowTaxInvoiceTable(parseInt($("#countTaxInvoice").val()));
            }  
//             $('#TaxInvoiceTable-area table td').each(function(){
//    
//                  $(this).addClass("my-class");
//                
//              });
//             $('table:first tr').each(function(){
//                $(this).find('td:last').addClass('LastTD');
//            });
//            if($('#TaxInvoiceTable input:last').attr('id') === parseInt($("#countTaxInvoice").val())){
//                AddRowTaxInvoiceTable(parseInt($("#countTaxInvoice").val()));
//            }           
        });
        $("#countTaxInvoice").val(row+1);
        
    }
    
    function AddrowBySelect(row){
        var count =  parseInt($("#countTaxInvoice").val());
//        var rowTable = $('#TaxInvoiceTable tr').length;
        row = parseInt(row);
        if(row === (count-1)){
           AddRowTaxInvoiceTable(count); 
        }       
    }
    
    function AddProduct(id,product,description,cost,curCost,amount,curAmount,isVat,refNo,vat){
        var count = parseInt($("#countTaxInvoice").val());
        var row = parseInt(count)+1;
        var match = CheckInvoiceProduct(id,count);
        console.log(match);
        if(match === 0){
            AddDataRowProduct(row,count,id,product,description,cost,curCost,amount,curAmount,isVat,refNo,'',vat);
        }    
    }
    
    function AddRefNo(product,description,cost,curcost,amount,curamount,id,displaydescription,refNo){
        var count = parseInt($("#countTaxInvoice").val());
        var row = parseInt(count)+1;
        var match = CheckInvoiceProduct(id,count);
        var isProfit = 1;
        console.log(match);
        if(match === 0){
            AddDataRowProduct(row,count,id,product,description,cost,curcost,amount,curamount,'',refNo,isProfit,'');
        }    
    }
    
    function CheckInvoiceProduct(id,count){
        var row = parseInt(count);
        var match = 0;
        for(var i=1;i<row;i++){
            var invoiceDetailId = document.getElementById("invoiceDetailId"+i);
            if(invoiceDetailId !== null){
                if(invoiceDetailId.value === id){
                    match++;
                    i = row;
                }
            }
        }        
        return match;
    }
    
    function CheckRefNoProduct(id,count){
        var row = parseInt(count);
        var match = 0;
        for(var i=1;i<row;i++){
            var invoiceDetailId = document.getElementById("invoiceDetailId"+i);
            if(invoiceDetailId !== null){
                if(invoiceDetailId.value === id){
                    match++;
                    i = row;
                }
            }
        }        
        return match;
    }
    
    function AddDataRowProduct(row,count,id,product,description,cost,curCost,amount,curAmount,isVat,refNo,isProfit,vat) {
        $('#TaxInvoiceTable tr input:last').removeClass('lastrow');
        if (!row) {
            row = 1;
        }
        
        var rowAll = row + 1;
        for (var i = 1; i < rowAll; i++) {
            if ($("#product" + i).val() !== undefined
                    || $("#refNo" + i).val() !== undefined
                    || $("#description" + i).val() !== undefined
                    || $("#cost" + i).val() !== undefined
                    || $("#currencyCost" + i).val() !== undefined
                    || $("#amount" + i).val() !== undefined
                    || $("#currencyAmount" + i).val() !== undefined
                    ) {
                if ($("#product" + i).val() !== ""
                        || $("#refNo" + i).val() !== ""
                        || $("#description" + i).val() !== ""
                        || $("#cost" + i).val() !== ""
                        || $("#currencyCost" + i).val() !== ""
                        || $("#amount" + i).val() !== ""
                        || $("#currencyAmount" + i).val() !== ""
                        ) {

                } else {
                    $("#product" + i).parent().parent().remove();
                    row = parseInt(i);
                    count = parseInt(i);
//                    $("#counter").val(row);
                }
            }    
        }
        
//        if(document.getElementById("invoiceDetailId"+(count-1))===null){
           $("#TaxInvoiceTable tbody").append(           
                '<tr>' +
                '<td class="hidden"><input class="form-control" type="text" id="taxDetailId' + count + '" name="taxDetailId' + count + '" value=""></td>' +
                '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailId' + count + '" name="invoiceDetailId' + count + '" value=""></td>' +
                '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailCost' + count + '" name="invoiceDetailCost' + count + '" value=""></td>' +
                '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailAmount' + count + '" name="invoiceDetailAmount' + count + '" value=""></td>' +
                '<td class="hidden"><input class="form-control" type="text" id="isExport' + count + '" name="isExport' + count + '" value=""></td>' +
                '<td class="hidden"><input class="form-control" type="text" id="exportDate' + count + '" name="exportDate' + count + '" value=""></td>' +
                '<td class="hidden"><input class="form-control" type="text" id="isProfit' + count + '" name="isProfit' + count + '" value=""></td>' +
                '<td><select class="form-control" name="product' + count + '" id="product' + count + '" onchange="AddrowBySelect(\'' + count + '\')"><option  value="" >---------</option></select></td>' +
                '<td><input class="form-control" maxlength="6" type="text" id="refNo' + count + '" name="refNo' + count + '" value="" onfocusout="checkRefNo(\'' + count + '\')"></td>' +
                '<td><input class="form-control" type="text" maxlength="255" id="description' + count + '" name="description' + count + '" value=""></td>' +
                '<td><input class="form-control decimal" style="text-align:right;" type="text" id="cost' + count + '" name="cost' + count +'" value="" onfocusout="CalculateAmountTotal()"></td>' +
                '<td><select class="form-control" name="currencyCost' + count + '" id="currencyCost' + count + '" onchange="AddrowBySelect(\'' + count + '\')"><option  value="" >---------</option></select></td>' +
                '<td align="center"><input type="checkbox" id="isVat' + count + '" name="isVat' + count + '" value="1" onclick="CalculateGross(\'' + count + '\')" checked></td>' +
                '<td align="center" id="vatShow' + count + '"></td>' +
                '<td class="hidden"><input class="form-control" style="text-align:right;" type="text" id="vat' + count + '" name="vat' + count + '" readonly=""></td>' +
                '<td><input class="form-control decimal" style="text-align:right;" type="text" id="gross' + count + '" name="gross' + count + '" value="0.00" readonly=""></td>' +
                '<td><input class="form-control decimal" style="text-align:right;" type="text" id="amount' + count + '" name="amount' + count + '" value="" onfocusout="CalculateAmountTotal(\'' + row + '\')"></td>' +
                '<td><select class="form-control" name="currencyAmount' + count + '" id="currencyAmount' + count + '" onchange="AddrowBySelect(\'' + count + '\'); CalculateAmountTotal(\'\');"><option  value="" >---------</option></select></td>' +
                '<td>' + 
                    '<center>' +
                    '<a id="expenButtonRemove' + count + '" name="expenButtonRemove' + count + '" onclick="deleteTaxList(\'\',\'' + count + '\')"  data-toggle="modal" data-target="#DeleteExpenModal">' + 
                    '<span id="expenSpanEdit' + count + '" name="expenSpanEdit' + count + '" class="glyphicon glyphicon-remove deleteicon"></span>' +
                    '</a>' + 
                    '</center>' +
                '</td>' +
                '</tr>'           
            );
            $("#tr_TaxInvoiceDetailAddRow").removeClass("show");
            $("#tr_TaxInvoiceDetailAddRow").addClass("hide");
            $("#select_product_list option").clone().appendTo("#product" + count);
            $("#select_currency_list option").clone().appendTo("#currencyCost" + count);
            $("#select_currency_list option").clone().appendTo("#currencyAmount" + count);
            
            
            $("#invoiceDetailCost" + count).val(formatNumber(parseFloat(cost)));
            $("#invoiceDetailAmount" + count).val(formatNumber(parseFloat(amount)));            
            $("#description" + count).val(description);
            $("#cost" + count).val(formatNumber(parseFloat(cost)));
            $('[name=currencyCost' + count + '] option').filter(function() { 
                return ($(this).text() === curCost);
            }).prop('selected', true);
            $("#amount" + count).val(formatNumber(parseFloat(amount)));
            $('[name=currencyAmount' + count + '] option').filter(function() { 
                return ($(this).text() === curAmount);
            }).prop('selected', true);           
            document.getElementById('vat'+(count)).value = (vat !== '' ? parseFloat(vat) : parseFloat($("#vatDefault").val()));
            if (isVat === '1'){
                $('#isVat'+count).prop('checked', true);
                document.getElementById('vatShow'+(count)).innerHTML = (vat !== '' ? parseFloat(vat) : parseFloat($("#vatDefault").val()));
//                var vatData = parseFloat($("#vatDefault").val());
//                document.getElementById('vatShow'+count).innerHTML = vatData;
                CalculateGross(count);
            }else{
                $('#isVat'+count).prop('checked', false);
            }
            if(isProfit !== ''){
                $("#invoiceDetailId" + count).val(id);
                $("#isProfit" + count).val(isProfit);
                $('[name=product' + count + '] option').filter(function() { 
                    return ($(this).val() === product);
                }).prop('selected', true);
            } else {
                $("#invoiceDetailId" + count).val(id);
                $('[name=product' + count + '] option').filter(function() { 
                    return ($(this).text() === product);
                }).prop('selected', true);
            }
//            var vatData = parseFloat($("#vatDefault").val());
//            document.getElementById('vatShow'+count).innerHTML = vatData;
            $("#refNo" + count).val(refNo);
            row = count + 1;
            $('#TaxInvoiceTable input:last').addClass('lastrow');
            $("#refNo"+count+",#description"+count+",#cost"+count+",#gross"+count+",#amount"+count).focus(function() {
                if($("#amount"+count).hasClass("lastrow")){
                   AddRowTaxInvoiceTable(parseInt($("#countTaxInvoice").val()));
                }
            });	
            var tempCount = row+1;
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
            $("#countTaxInvoice").val(row);
            CalculateAmountTotal();
            AddRowTaxInvoiceTable(parseInt($("#countTaxInvoice").val()));
//        } else {
//            if(isProfit !== ''){
//                $("#invoiceDetailId" + (count-1)).val(id);
//                $("#isProfit" + (count-1)).val(isProfit);
//                $('[name=product' + (count-1) + '] option').filter(function() { 
//                    return ($(this).val() === product);
//                }).prop('selected', true);
//            } else {
//                $("#invoiceDetailId" + (count-1)).val(id);
//                $('[name=product' + (count-1) + '] option').filter(function() { 
//                    return ($(this).text() === product);
//                }).prop('selected', true);
//            }
////            $("#invoiceDetailId" + (count-1)).val(id);
//            $("#invoiceDetailCost" + (count-1)).val(formatNumber(parseFloat(cost)));
//            $("#invoiceDetailAmount" + (count-1)).val(formatNumber(parseFloat(amount)));
//            $("#description" + (count-1)).val(description);
//            $("#cost" + (count-1)).val(formatNumber(parseFloat(cost)));
//            $('[name=currencyCost' + (count-1) + '] option').filter(function() { 
//                return ($(this).text() === curCost);
//            }).prop('selected', true);
//            $("#amount" + (count-1)).val(formatNumber(parseFloat(amount)));
//            $('[name=currencyAmount' + (count-1) + '] option').filter(function() { 
//                return ($(this).text() === curAmount);
//            }).prop('selected', true);
//            document.getElementById('vatShow'+(count-1)).innerHTML = (vat !== '' ? parseFloat(vat) : parseFloat($("#vatDefault").val()));
//            document.getElementById('vat'+(count-1)).value = (vat !== '' ? parseFloat(vat) : parseFloat($("#vatDefault").val()));
//            if (isVat === '1'){
//                $('#isVat'+(count-1)).prop('checked', true);
////                var vatData = parseFloat($("#vatDefault").val());
////                document.getElementById('vatShow'+(count-1)).innerHTML = vatData;
//                CalculateGross((count-1));
//            } else {
//                $('#isVat'+(count-1)).prop('checked', true);
////                var vatData = parseFloat($("#vatDefault").val());
////                document.getElementById('vatShow'+(count-1)).innerHTML = vatData;
//                CalculateGross((count-1));
//            }
////            var vatData = parseFloat($("#vatDefault").val());
////            document.getElementById('vatShow'+(count-1)).innerHTML = vatData;            
//            $("#refNo" + (count-1)).val(refNo);
//            row = count + 1;
//        }    
                  
//        $("#TaxInvoiceTable tbody").append(           
//            '<tr>' +
//            '<td class="hidden"><input class="form-control" type="text" id="taxDetailId' + row + '" name="taxDetailId' + row + '" value=""></td>' +
//            '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailId' + row + '" name="invoiceDetailId' + row + '" value=""></td>' +
//            '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailCost' + row + '" name="invoiceDetailCost' + row + '" value=""></td>' +
//            '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailAmount' + row + '" name="invoiceDetailAmount' + row + '" value=""></td>' +
//            '<td class="hidden"><input class="form-control" type="text" id="isExport' + count + '" name="isExport' + count + '" value=""></td>' +
//            '<td class="hidden"><input class="form-control" type="text" id="exportDate' + row + '" name="exportDate' + row + '" value=""></td>' +
//            '<td class="hidden"><input class="form-control" type="text" id="isProfit' + count + '" name="isProfit' + count + '" value=""></td>' +
//            '<td><select class="form-control" name="product' + row + '" id="product' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
//            '<td><input class="form-control" maxlength="6" type="text" id="refNo' + row + '" name="refNo' + row + '" value="" onfocusout="checkRefNo(\'' + row + '\')"></td>' +
//            '<td><input class="form-control" type="text" maxlength="255" id="description' + row + '" name="description' + row + '" value=""></td>' +
//            '<td><input class="form-control decimal" style="text-align:right;" type="text" id="cost' + row + '" name="cost' + row +'" value="" onfocusout="CalculateAmountTotal()"></td>' +
//            '<td><select class="form-control" name="currencyCost' + row + '" id="currencyCost' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
//            '<td align="center"><input type="checkbox" id="isVat' + row + '" name="isVat' + row + '" value="1" onclick="CalculateGross(\'' + row + '\')" checked></td>' +
//            '<td align="right" id="vatShow' + row + '"></td>' +
//            '<td class="hidden"><input class="form-control numerical" style="text-align:right;" type="text" id="vat' + row + '" name="vat' + row + '" readonly=""></td>' +
//            '<td><input class="form-control decimal" style="text-align:right;" type="text" id="gross' + row + '" name="gross' + row + '" value="0.00" readonly=""></td>' +
//            '<td><input class="form-control decimal" style="text-align:right;" type="text" id="amount' + row + '" name="amount' + row + '" value="" onfocusout="CalculateAmountTotal(\'' + row + '\')"></td>' +
//            '<td><select class="form-control" name="currencyAmount' + row + '" id="currencyAmount' + row + '" onchange="AddrowBySelect(\'' + row + '\'); CalculateAmountTotal(\'\');"><option  value="" >---------</option></select></td>' +
//            '<td>' + 
//                '<center>' +
//                '<a id="expenButtonRemove' + row + '" name="expenButtonRemove' + row + '" onclick="deleteTaxList(\'\',\'' + row + '\')"  data-toggle="modal" data-target="#DeleteExpenModal">' + 
//                '<span id="expenSpanEdit' + row + '" name="expenSpanEdit' + row + '" class="glyphicon glyphicon-remove deleteicon"></span>' +
//                '</a>' + 
//                '</center>' +
//            '</td>' +
//            '</tr>'           
//        );
//        $("#tr_TaxInvoiceDetailAddRow").removeClass("show");
//        $("#tr_TaxInvoiceDetailAddRow").addClass("hide");
//        $("#select_product_list option").clone().appendTo("#product" + row);
//        $("#select_currency_list option").clone().appendTo("#currencyCost" + row);
//        $("#select_currency_list option").clone().appendTo("#currencyAmount" + row);
////        var vatData = parseFloat($("#vatDefault").val());
//        document.getElementById('vatShow'+row).innerHTML = parseFloat($("#vatDefault").val());
//        document.getElementById('vat'+row).value = parseFloat($("#vatDefault").val());
//        $('#TaxInvoiceTable input:last').addClass('lastrow');
//        $("#refNo"+row+",#description"+row+",#cost"+row+",#gross"+row+",#amount"+row).focus(function() {
//            if($("#amount"+row).hasClass("lastrow")){
//               AddRowTaxInvoiceTable(parseInt($("#countTaxInvoice").val()));
//            }
//        });	
//        var tempCount = row+1;
//        $(".decimal").inputmask({
//            alias: "decimal",
//            integerDigits: 8,
//            groupSeparator: ',',
//            autoGroup: true,
//            digits: 2,
//            allowMinus: false,
//            digitsOptional: false,
//            placeholder: "0.00",
//        });
//        $("#countTaxInvoice").val(tempCount);
//        CalculateAmountTotal();
    }
    
    function CalculateAmountTotal(row){
        var count = parseInt(document.getElementById('countTaxInvoice').value);
//        var count = $('#TaxInvoiceTable tr').length;
        
        var i;
        var grandTotal = 0;
        for(i=1;i<count+1;i++){
            var amount = document.getElementById("amount" + i);
            var cost = document.getElementById("cost" + i);
            if (amount !== null){
                var value = amount.value;
                    
                if(value !== ''){
                    value = value.replace(/,/g,"");
                    var total = parseFloat(value);
                    grandTotal += total;
                    document.getElementById('amount' + i).value = formatNumber(total);
                    
 
                }
            }
            if (cost !== null){
                var costVal = cost.value;
                if(costVal !== ''){
                    costVal = costVal.replace(/,/g,"");
                    var costTotal = parseFloat(costVal);
                    document.getElementById('cost' + i).value = formatNumber(costTotal);
                }
            }
        }
        var currency = getCurrency();
        document.getElementById('TotalAmount').value = formatNumber(grandTotal);
        document.getElementById('TextAmount').value = toWords(grandTotal,currency);
        
        if(row){
            if((document.getElementById("isVat"+row).checked)){
//                if(document.getElementById("gross"+row).value === '0.00'){
//                    CalculateGross(row);
//                } else if(document.getElementById("gross"+row).value !== '0.00'){
//                    CalculateGross(row);
//                } else {
//                    
//                }
                CalculateGrossByGross(row);
            }            
        }
    }
 
    function getCurrency(){
        var countTaxInvoice = parseInt($("#countTaxInvoice").val());
        var currency = '';
        for(var i=1; i<=countTaxInvoice; i++){
            var currency1 = document.getElementById('currencyAmount'+i);
            var product1 = document.getElementById('product'+i);
            var refNo1 = document.getElementById('refNo'+i);
            var description1 = document.getElementById('description'+i);
            var cost1 = document.getElementById('cost'+i);
            var amount1 = document.getElementById('amount'+i);
            if(currency1 !== null){
                if(product1.value !== '' || refNo1.value !== '' || description1.value !== '' || cost1.value !== '' || amount1.value !== ''){
                    currency = currency1.value;
                    var currencyTemp1 = currency1.value;
                    for(var j=i+1; j<=countTaxInvoice; j++){
                        var currency2 = document.getElementById('currencyAmount'+j);
                        var product2 = document.getElementById('product'+j);
                        var refNo2 = document.getElementById('refNo'+j);
                        var description2 = document.getElementById('description'+j);
                        var cost2 = document.getElementById('cost'+j);
                        var amount2 = document.getElementById('amount'+j);
                        if(currency2 !== null){
                            if(product2.value !== '' || refNo2.value !== '' || description2.value !== '' || cost2.value !== '' || amount2.value !== ''){
                                var currencyTemp2 = currency2.value;
                                if((currencyTemp1 !== currencyTemp2)){
                                    currency = ''; 
                                    i = countTaxInvoice+1;
                                    j = countTaxInvoice+1;
                                }
                            }    
                        }
                    }
                }    
            }    
        }
        return currency;
    }
    
    function CalculateGrossByGross(row){       
        var amount = document.getElementById('amount'+row).value;
        var gross = document.getElementById('gross'+row).value;
//        var vatData = parseFloat(document.getElementById('vatDefault').value);
        var vatData = parseFloat(document.getElementById('vat'+row).value);

        amount = amount.replace(/,/g,"");
        var grossTotal = parseFloat(amount);
        var vatTotal = parseFloat(vatData);


            grossTotal = (amount*100)/(100+vatData);
            document.getElementById('gross'+row).value = formatNumber(grossTotal);
            document.getElementById('vatShow'+row).innerHTML = vatTotal;
//            document.getElementById('vat'+row).value = vatTotal;
       
    }
    
    function CalculateGross(row){       
        var amount = document.getElementById('amount'+row).value;
        var gross = document.getElementById('gross'+row).value;
//        var vatData = parseFloat(document.getElementById('vatDefault').value);
        var vatData = parseFloat(document.getElementById('vat'+row).value);
        var isVat = document.getElementById('isVat'+row).checked;

        amount = amount.replace(/,/g,"");
        var grossTotal = parseFloat(amount);
        var vatTotal = parseFloat(vatData);

        if(isVat){
            grossTotal = (amount*100)/(100+vatData);
            document.getElementById('gross'+row).value = formatNumber(grossTotal);
            document.getElementById('vatShow'+row).innerHTML = vatTotal;
//            document.getElementById('vat'+row).value = vatTotal;
        } else {
            document.getElementById('gross'+row).value = '0.00';
            document.getElementById('vatShow'+row).innerHTML = '';
//            document.getElementById('vat'+row).value = vatTotal;
        }
    }
    
    function setGross(){
        var count = parseInt(document.getElementById('countTaxInvoice').value);   
        for(var i=1;i<count+1;i++){
            var gross = document.getElementById("grossTemp" + i);
            if (gross !== null){
                var grossVal = gross.value;                   
                if(grossVal !== ''){
                    grossVal = grossVal.replace(/,/g,"");
                    var grossTotal = parseFloat(grossVal);
                    document.getElementById('gross' + i).value = formatNumber(grossTotal);                  
                }
            }
        }
    }   
               
    function checkRefNo(row){
        var list = '${refNo_list}';
        var refNo = document.getElementById('refNo'+row).value;

        if(refNo===''){
            var refNoField = document.getElementById('refNo'+row);
            refNoField.style.borderColor = "";
            return;
        }        

        list = list.replace("[","");
        list = list.replace("]","");
        list = list.replace(/ /g,"");

        var refNo_list = list.split(',');
        for(var i = 0;i<=refNo_list.length;i++){
            if(String(refNo) === String(refNo_list[i])){
                var refNoField = document.getElementById('refNo'+row);
                refNoField.style.borderColor = "Green";
                return;
            } else {
                var refNoField = document.getElementById('refNo'+row);
                refNoField.style.borderColor = "Red";
            }
        } 
    }
    
    function validateForm(){
        //Check Tax Invoice Field
        var taxInvDate = $("#InvToDate").val();
        var taxInvTo = $("#TaxInvTo").val();
        var arCode = $("#ARCode").val();
        var address = $("#InvToAddress").val();        
        if(taxInvDate === '' || taxInvTo === '' || arCode === '' || address === ''){
            $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'TaxInvTo');
            $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'ARCode');
            $('#TaxInvoiceForm').bootstrapValidator('revalidateField', 'InvToAddress');
            return;
        }
        
        var countTaxInvoice = parseInt($("#countTaxInvoice").val());
        //Check Ref No       
        var refNoNotMatch = false;
        for(var i=1; i<=countTaxInvoice; i++){
            var refNo = document.getElementById('refNo'+i);
            if(refNo !== null){
                if(refNo.style.borderColor === 'red'){
                    refNoNotMatch = true;
                    i = countTaxInvoice+1;
//                    return;
                }
            }    
        }
        
        //Check Currency
        var currencyNotMatch = false;
        var currencyNotEmpty = 0;
        for(var i=1; i<=countTaxInvoice; i++){
            var currency1 = document.getElementById('currencyAmount'+i);
            var product1 = document.getElementById('product'+i);
            var refNo1 = document.getElementById('refNo'+i);
            var description1 = document.getElementById('description'+i);
            var cost1 = document.getElementById('cost'+i);
            var amount1 = document.getElementById('amount'+i);
            if(currency1 !== null){
                if(product1.value !== '' || refNo1.value !== '' || description1.value !== '' || cost1.value !== '' || amount1.value !== ''){
                    var currencyTemp1 = currency1.value;
                    for(var j=i+1; j<=countTaxInvoice; j++){
                        var currency2 = document.getElementById('currencyAmount'+j);
                        var product2 = document.getElementById('product'+j);
                        var refNo2 = document.getElementById('refNo'+j);
                        var description2 = document.getElementById('description'+j);
                        var cost2 = document.getElementById('cost'+j);
                        var amount2 = document.getElementById('amount'+j);
                        if(currency2 !== null){
                            var currencyTemp2 = currency2.value;
                            if(product2.value !== '' || refNo2.value !== '' || description2.value !== '' || cost2.value !== '' || amount2.value !== ''){                               
                                if((currencyTemp1 !== currencyTemp2)){
                                    currencyNotMatch = true;
                                    i = countTaxInvoice+1;
                                    j = countTaxInvoice+1;
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
        if(currencyNotMatch){
            $("#textAlertCurrencyAmountNotEmpty").hide();
            $("#textAlertCurrencyAmountNotMatch").hide();
           for(var i=1; i<=countTaxInvoice; i++){
                var currency = document.getElementById('currencyAmount'+i);
                var product = document.getElementById('product'+i);
                var refNo = document.getElementById('refNo'+i);
                var description = document.getElementById('description'+i);
                var cost = document.getElementById('cost'+i);
                var amount = document.getElementById('amount'+i);
                if(currency !== null){
                    if(product.value !== '' || refNo.value !== '' || description.value !== '' || cost.value !== '' || amount.value !== ''){  
                        currency.style.borderColor = 'red';
                    }    
                }    
            }
            $("#textAlertCurrencyAmountNotMatch").show();
            return;
        }
        if(currencyNotEmpty > 0){
            $("#textAlertCurrencyAmountNotEmpty").hide();
            $("#textAlertCurrencyAmountNotMatch").hide();
            for(var i=1; i<=countTaxInvoice; i++){
                var currency = document.getElementById('currencyAmount'+i);
                var product = document.getElementById('product'+i);
                var refNo = document.getElementById('refNo'+i);
                var description = document.getElementById('description'+i);
                var cost = document.getElementById('cost'+i);
                var amount = document.getElementById('amount'+i);
                if(currency !== null){
                    if(product.value !== '' || refNo.value !== '' || description.value !== '' || cost.value !== '' || amount.value !== ''){      
                        currency.style.borderColor = 'red';
                    }    
                }    
            }
            $("#textAlertCurrencyAmountNotEmpty").show();
            return;
        }
        
        if(!refNoNotMatch && !currencyNotMatch && currencyNotEmpty === 0){
            $("#textAlertCurrencyAmountNotEmpty").hide();
            $("#textAlertCurrencyAmountNotMatch").hide();
            document.getElementById('TaxInvoiceForm').submit();
        }
        
//        var count = document.getElementById('counter').value;
//        var count = $('#TaxInvoiceTable tr').length;
//        
//        for(var i=1;i<=count;i++){
//            var refNoField = document.getElementById('refNo'+i);
//            
//            if(refNoField !== null){
//                var color = document.getElementById('refNo'+i).style.borderColor;
//                if(color === "red"){
//                    return false;
//                }   
//            }
//        }
    }
    
    function deleteTaxList(id,row) {
        document.getElementById('delTaxDetailId').value = id;
        document.getElementById('delTaxDetailRow').value = row;
        $("#delCode").text('Are you sure delete this product ?');
        $('#delTaxInvoiceDetailModal').modal('show');
    }
    
    function deleteTaxInvoiceDetailList(){
        var id = document.getElementById('delTaxDetailId').value;
        var row = document.getElementById('delTaxDetailRow').value;
        var count = parseInt(document.getElementById('countTaxInvoice').value);
        if(id === ''){
            $("#product" + row).parent().parent().remove();
//            AddRowTaxInvoiceTable(count++);
            var rowAll = $("#TaxInvoiceTable tr").length;
            if (rowAll <= 1) {
                $("#tr_TaxInvoiceDetailAddRow").removeClass("hide");
                $("#tr_TaxInvoiceDetailAddRow").addClass("show");
            }
            $('#TaxInvoiceTable tr input:last').removeClass('lastrow');
            $('#TaxInvoiceTable input:last').addClass('lastrow');
//            $("#countTaxInvoice").val(count+1);
        } else {
            $.ajax({
                url: 'TaxInvoice'+'${page}'+'.smi?action=deleteTaxInvoiceDetail',
                type: 'get',
                data: {taxInvoiceDetailId: id},
                success: function () {
                    $("#product" + row).parent().parent().remove();
//                    AddRowTaxInvoiceTable(count++);
                    var rowAll = $("#TaxInvoiceTable tr").length;
                    if (rowAll <= 1) {
                        $("#tr_TaxInvoiceDetailAddRow").removeClass("hide");
                        $("#tr_TaxInvoiceDetailAddRow").addClass("show");
                    }
                    $('#TaxInvoiceTable tr input:last').removeClass('lastrow');
                    $('#TaxInvoiceTable input:last').addClass('lastrow');
//                    $("#countTaxInvoice").val(count+1);
                },
                error: function () {
                    console.log("error");
                    result =0;
                }
            }); 
        }    
        $('#delTaxInvoiceDetailModal').modal('hide');
        CalculateAmountTotal();
    }
    
    function clearScreen(){
//        $("#department").val("");
//        $("#invoiceNo").val("");
//        $("#TaxInvId").val("");
//        $("#TaxInvNo").val("");
//        $("#InvToDate").val("${requestScope['defaultInvToDate']}");
//        $("#TaxInvTo").val("");
//        $("#InvToName").val("");
//        $("#InvToAddress").val("");
//        $("#ARCode").val("");
//        $("#Remark").val("");
//        $("#TextAmount").val("");
//        $("#TotalAmount").val("");
//        $("#taxInvStatus").val("");
//        $("#createDate").val("");
//        $("#createBy").val("");
//        $("#countTaxInvoice").val("1");              
//        $('#InvoiceListTable > tbody  > tr').each(function() {
//            $(this).remove();
//        });       
//        $('#TaxInvoiceTable > tbody  > tr').each(function() {
//            $(this).remove();
//        });
//        $("#textAlertDivSave").addClass("hidden");
//        $("#textAlertDivNotSave").addClass("hidden");
//        $("#textAlertDivFindNotFound").addClass("hidden");
//        $("#textAlertTaxInvoiceVoid").addClass("hidden");
//        $("#textAlertCostOver").addClass("hidden");
//        $("#textAlertAmountOver").addClass("hidden");
//        AddRowTaxInvoiceTable();
//        CalculateAmountTotal();
          var action = document.getElementById("action");
          action.value = "new";
          document.getElementById('TaxInvoiceForm').submit();
    }
    
    function DisableVoidInvoice(){
        var TaxInvNo = document.getElementById('TaxInvNo');
        document.getElementById('disableVoid').innerHTML = "Are you sure to delete booking other : " + TaxInvNo.value + " ?";
    }

    function EnableVoidInvoice(){
        var TaxInvNo = document.getElementById('TaxInvNo');
        document.getElementById('enableVoid').innerHTML = "Are you sure to enable booking other : " + TaxInvNo.value + " ?";
    }
    
    function Enable() {
        var action = document.getElementById('action');
        action.value = 'enableVoid';
        document.getElementById('TaxInvoiceForm').submit();
    }

    function DisableInvoice() {
        var action = document.getElementById('action');
        action.value = 'disableVoid';
        document.getElementById('TaxInvoiceForm').submit();
    }
    
    function checkVatAll(){
        var row = document.getElementById('countTaxInvoice').value;
//        var row = $('#TaxInvoiceTable tr').length;
//        var vatDefaultData = parseFloat(document.getElementById('vatDefault').value);      
        var check = 0;
        var unCheck = 0;
        for(var i=1;i<row;i++){          
            var isVatCheck = document.getElementById("isVat"+i);
            if(isVatCheck !== null && isVatCheck !== ''){
                if(document.getElementById("isVat"+i).checked){
                    check++;
                } else {
                    unCheck++;
                }
            }   
        }

        if(check > unCheck){
            for(var i=1;i<row;i++){
                var isVatCheck = document.getElementById("isVat"+i);               
                if(isVatCheck !== null && isVatCheck !== ''){
                    if(document.getElementById("isVat"+i).checked){
                        
                    } else { 
                        document.getElementById("isVat"+i).checked = true;
                        var vatDefaultData = parseFloat(document.getElementById('vat'+i).value);   
                        var amountChk = document.getElementById('amount'+i);
                        if(amountChk !== null && amountChk !== ''){
                            var amount = document.getElementById('amount'+i).value;
                            var gross = document.getElementById('gross'+i).value;

                            amount = amount.replace(/,/g,"");
                            var grossTotal = parseFloat(amount);

                            if((gross === '')){
                                grossTotal = (amount*100)/(100+vatDefaultData);
                                document.getElementById('gross'+i).value = formatNumber(grossTotal);
                                document.getElementById('vatShow'+i).innerHTML = vatDefaultData;
                            } else {
                                document.getElementById('gross'+i).value = '';
                                document.getElementById('vatShow'+i).innerHTML = '';
                            }
                        }
                    }    
                }   
            }
        }
            
        if(check < unCheck){
            for(var i=1;i<row;i++){
                var isVatCheck = document.getElementById("isVat"+i);
                if(isVatCheck !== null && isVatCheck !== ''){
                    document.getElementById("isVat"+i).checked = false;
                    document.getElementById("vatShow"+i).innerHTML = '';
                    document.getElementById("gross"+i).value = '';
                }   
            }
        }
         
        if(check === 0 && unCheck !== 0){
            for(var i=1;i<row;i++){
                var isVatCheck = document.getElementById("isVat"+i);  
                if(isVatCheck !== null && isVatCheck !== ''){
                    if(document.getElementById("isVat"+i).checked){
                        
                    } else { 
                        document.getElementById("isVat"+i).checked = true;
                        var vatDefaultData = parseFloat(document.getElementById('vat'+i).value);   
                        var amountChk = document.getElementById('amount'+i);
                        if(amountChk !== null && amountChk !== ''){
                            var amount = document.getElementById('amount'+i).value;
                            var gross = document.getElementById('gross'+i).value;
                            
                            amount = amount.replace(/,/g,"");
                            var grossTotal = parseFloat(amount);

                            if((gross === '')){
                                grossTotal = (amount*100)/(100+vatDefaultData);
                                document.getElementById('gross'+i).value = formatNumber(grossTotal);
                                document.getElementById('vatShow'+i).innerHTML = vatDefaultData;
                            } else {
                                document.getElementById('gross'+i).value = '';
                                document.getElementById('vatShow'+i).innerHTML = '';
                            }
                        }
                    }    
                }    
            }
        } 
            
        if(check !== 0 && unCheck === 0){
            for(var i=1;i<row;i++){
                var isVatCheck = document.getElementById("isVat"+i);
                if(isVatCheck !== null && isVatCheck !== ''){
                    document.getElementById("isVat"+i).checked = false;
                    document.getElementById('vatShow'+i).innerHTML = '';
                    document.getElementById("gross"+i).value = '';
                }   
            }
        }
            
        if(check === unCheck){
            for(var i=1;i<row;i++){
                var isVatCheck = document.getElementById("isVat"+i);
                if(isVatCheck !== null && isVatCheck !== ''){
                    if(document.getElementById("isVat"+i).checked){
                        
                    } else { 
                        document.getElementById("isVat"+i).checked = true;
                        var vatDefaultData = parseFloat(document.getElementById('vat'+i).value);   
                        var amountChk = document.getElementById('amount'+i);
                        if(amountChk !== null && amountChk !== ''){
                            var amount = document.getElementById('amount'+i).value;
                            var gross = document.getElementById('gross'+i).value;
                            
                            amount = amount.replace(/,/g,"");
                            var grossTotal = parseFloat(amount);

                            if((gross === '')){
                                grossTotal = (amount*100)/(100+vatDefaultData);
                                document.getElementById('gross'+i).value = formatNumber(grossTotal);
                                document.getElementById('vatShow'+i).innerHTML = vatDefaultData;
                            } else {
                                document.getElementById('gross'+i).value = '';
                                document.getElementById('vatShow'+i).innerHTML = '';
                            }
                        }
                    }    
                }    
            }             
        }            
        CalculateAmountTotal();   
    }
    
    function showSearchInvoiceNo(){
        if($("#searchInvoiceNo1").hasClass("hidden")){
            $("#searchRefNo1").addClass("hidden");
            $("#searchRefNo2").addClass("hidden");
            $("#searchInvoiceNo1").removeClass("hidden");
        }else{
            $("#searchInvoiceNo1").addClass("hidden");
        }
        $("#searchInvoiceNo2").addClass("hidden");
    }
    
    function showSearchRefNo(){
        if($("#searchRefNo1").hasClass("hidden")){
            $("#searchInvoiceNo1").addClass("hidden");
            $("#searchInvoiceNo2").addClass("hidden");
            $("#searchRefNo1").removeClass("hidden");
        }else{
            $("#searchRefNo1").addClass("hidden");
        }
        $("#searchRefNo2").addClass("hidden");
    }
</script>
