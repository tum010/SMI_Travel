<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/TaxInvoice.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="Type" value="${requestScope['Department']}" />
<c:set var="page" value="${requestScope['page']}" />
<c:set var="product_list" value="${requestScope['productList']}" />
<c:set var="currency_list" value="${requestScope['currencyList']}" />
<c:set var="customer_agent_list" value="${requestScope['customerAgentList']}"/>
<c:set var="refNo_list" value="${requestScope['refNo_list']}" />
<c:set var="taxInvoice" value="${requestScope['taxInvoice']}" />
<c:set var="taxInvoiceDetail" value="${requestScope['taxInvoiceDetail_list']}" />
<c:set var="resultText" value="${requestScope['result_text']}" />
<input type="hidden" id="Type" name="Type" value="${param.Department}">
<section class="content-header" >
    <h1>
        Finance & Cashier
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
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6 " style="padding-right: 15px">
		<c:choose>
                    <c:when test="${fn:contains(page , 'W')}">
                        <h4><b>Tax Invoice Wendy</b></h4>
                    </c:when>
                    <c:when test="${fn:contains(page , 'O')}">
                        <h4><b>Tax Invoice Outbound</b></h4>
                    </c:when> 
                    <c:when test="${fn:contains(page , 'I')}">
                        <h4><b>Tax Invoice Inbound</b></h4>
                    </c:when> 
		</c:choose> 
            </div>
            <div class="col-xs-12 form-group"><hr/></div>
        </div>
        <form action="TaxInvoice${page}.smi" method="post" id="TaxInvoiceForm" role="form" autocomplete="off">
        <!--Search Invoice-->
        <div class="row" style="padding-left: 15px">  
           <div role="tabpanel">
                <div class="tab-content">
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
                                        <div class="col-md-2 form-group">
                                            <div class="input-group">
                                                <input type="text" class="form-control" id="invoiceNo" name="invoiceNo" value="" onkeydown="invoiceNoValidate()">
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
                                            <table id="InvoiceNoTable" class="display" cellspacing="0" width="100%">
                                                <thead>
                                                    <tr class="datatable-header">
                                                        <th style="width: 15%" >Product</th>
                                                        <th style="width: 60%">Description</th>
                                                        <th style="width: 10%">Amount</th>
                                                        <th style="width: 1%">Currency</th>
                                                        <th style="width: 1%">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>               
<!--                                                <tr>
                                                    <td>TEST</td>
                                                    <td>Hello World</td>
                                                    <td align="center">100000</td>
                                                    <td align="center">THB</td>
                                                    <td align="center" > 
                                                        <center> 
                                                            <a href=""><span class="glyphicon glyphicon-plus"></span></a>
                                                        </center>
                                                    </td>
                                                    </tr>-->
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
            </div>
            <div class="col-xs-12 form-group"></div>             
                <!--Search-->  
                
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Tax Invoice No</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <input type="hidden" class="form-control" id="TaxInvId" name="TaxInvId" value="${taxInvoice.id}"/>
                            <input type="text"  class="form-control" id="TaxInvNo" name="TaxInvNo"  value="${taxInvoice.taxNo}" >
                        </div>
                        <div class="col-md-1" >
                            <button type="button"  id="btnSearchInvoiceNo"  name="btnSearchInvoiceNo" onclick="searchInvoiceNo()" class="btn btn-primary btn-sm">
                                <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                            </button>
                        </div>
                        <div class="col-xs-2 text-right">
                            <label class="control-label" for="">Invoice date</lable>
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
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>                             
                            </div>               
                        </div>
                    </div>    
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Tax Inv To</lable>
                        </div>
                        <div class="col-md-1 form-group" style="width: 585px">
                            <div class="input-group">                               
                                <input type="text" class="form-control" id="TaxInvTo" name="TaxInvTo" value="${taxInvoice.taxInvTo}" style="background-color: #ffffff">
                                <span class="input-group-addon" id="TaxInvTo_Modal"  data-toggle="modal" data-target="#TaxInvToModal">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Name </lable>
                        </div>    
                        <div class="col-md-1 form-group" style="width: 585px">
                            <input  type="text" id="InvToName" name="InvToName" class="form-control" value="${taxInvoice.taxInvName}">
                        </div>  
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Address </lable>
                        </div>
                        <div class="col-md-1 form-group" style="width: 585px">
                            <textarea  rows="3" cols="100" id="InvToAddress" name="InvToAddress" class="form-control" value="" >${taxInvoice.taxInvAddr}</textarea>
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Passenger</label>
                        </div>                       
                        <div class="col-md-2 form-group">
                            <div class="input-group">
                            <input type="hidden" class="form-control" id="PassengerId" name="PassengerId" value=""/>
                            <input type="text" class="form-control" id="PassengerCode" name="PassengerCode" value="" style="background-color: #ffffff">
                            <span class="input-group-addon" id="Passenger_Modal"  data-toggle="modal" data-target="#PassengerModal">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                            </div>
                        </div>
                        <div class="col-md-3 form-group">
                            <input type="text"  class="form-control" id="PassengerName" name="PassengerName"  value="" readonly="">
                        </div>
                        <div class="col-md-1 text-right">
                            <label class="control-label" for="" >A/R&nbsp;Code</label>
                        </div>  
                        <div class="col-md-2 form-group">
                            <div class="input-group">
                                <input type="hidden" class="form-control" id="ARCodeId" name="ARCodeId" value=""/>
                                <input type="text" class="form-control" id="ARCode" name="ARCode" value="${taxInvoice.arCode}" style="background-color: #ffffff">
                               
                            </div>
                        </div>
                    </div>    
                                
            </div>
                                            
            <div class="col-xs-12 form-group"></div>
            <div role="tabpanel">
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane  active" id="infoMasterProduct">
                        <div class="panel panel-default">                              
                            <div class="panel-body">
                                <div class="row" style="">    
                                    <div class="col-md-12">
                                        <input type="text" class="hidden" id="countTaxInvoice" name="countTaxInvoice" value="1" >
                                        <table id="TaxInvoiceTable" class="display" cellspacing="0" width="100%">
                                            <thead>
                                                <tr class="datatable-header">
                                                    <th style="width: 1%" class="hidden">Id</th>
                                                    <th style="width: 10%" >Product</th>
                                                    <th style="width: 10%" >Ref No</th>
                                                    <th style="width: 10%">Description</th>
                                                    <th style="width: 10%" >Cost</th>
                                                    <th style="width: 10%" >Cur</th>
                                                    <th style="width: 5%" >Is vat</th>
                                                    <th style="width: 10%" >Vat</th>
                                                    <th style="width: 10%" >Gross</th>
                                                    <th style="width: 10%">Amount</th>
                                                    <th style="width: 10%">Cur</th>
                                                    <th style="width: 4%">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="expen" items="${dayTourOperationExpense}" varStatus="i">
                                                <tr>
                                                    <td class="hidden"><input class="form-control" type="text" id="taxDetailId${i.count}" name="taxDetailId${i.count}" value=""></td>
                                                    <td>
                                                        <select class="form-control" name="product${i.count}" id="product${i.count}">
                                                            <option  value="" >---------</option>
                                                        <c:forEach var="product" items="${product_list}" varStatus="status">                                       
                                                            <c:set var="select" value="" />
                                                            <c:if test="${product.id == pl.MPaytype.id}">
                                                                <c:set var="select" value="selected" />
                                                            </c:if>
                                                            <option  value="${product.id}" ${select}>${product.name}</option>
                                                        </c:forEach>
                                                        </select>      
                                                    </td>
                                                    <td><input class="form-control" type="text" id="refNo${i.count}" name="refNo${i.count}" value=""></td>
                                                    <td><input class="form-control" type="text" id="description${i.count}" name="description${i.count}" value=""></td>
                                                    <td align="right"><input class="form-control" type="text" id="cost${i.count}" name="cost${i.count}" value=""></td>
                                                    <td>
                                                        <select class="form-control" name="currencyCost${i.count}" id="currencyCost${i.count}">
                                                            <option  value="" >---------</option>
                                                        <c:forEach var="currency" items="${currency_list}" varStatus="status">                                       
                                                            <c:set var="select" value="" />
                                                            <c:if test="${currency.code == pl.MPaytype.id}">
                                                                <c:set var="select" value="selected" />
                                                            </c:if>
                                                            <option  value="${currency.code}" ${select}>${currency.code}</option>
                                                        </c:forEach>
                                                        </select>        
                                                    </td>
                                                    <td align="center">
                                                        <c:set var="vatChk" value="" />
                                                        <c:if test="${'1' == pl.isVat}">
                                                            <c:set var="vatChk" value="checked" />
                                                        </c:if>  
                                                        <input type="checkbox" id="isVat${i.count}" name="isVat${i.count}" value="1" ${vatChk}>
                                                    </td>
                                                    <td align="right"><input class="form-control" type="text" id="vat" name="vat" value=""></td>
                                                    <td align="right"><input class="form-control" type="text" id="gross" name="gross" value=""></td>
                                                    <td align="right"><input class="form-control" type="text" id="amount" name=amount value=""></td>
                                                    <td>
                                                        <select class="form-control" name="currencyAmount${i.count}" id="currencyAmount${i.count}">
                                                            <option  value="" >---------</option>
                                                        <c:forEach var="currency" items="${currency_list}" varStatus="status">                                       
                                                            <c:set var="select" value="" />
                                                            <c:if test="${currency.code == pl.MPaytype.id}">
                                                                <c:set var="select" value="selected" />
                                                            </c:if>
                                                            <option  value="${currency.code}" ${select}>${currency.code}</option>
                                                        </c:forEach>
                                                        </select>        
                                                    </td>
                                                    <td class="text-center">
                                                        <a id="expenButtonRemove${i.count}" name="expenButtonRemove${i.count}" onclick="setExpenId(${expen.id})"  data-toggle="modal" data-target="#DeleteExpenModal">
                                                            <span id="expenSpanRemove${i.count}" name="expenSpanRemove${i.count}"  class="glyphicon glyphicon-remove deleteicon"></span>
                                                        </a>
                                                    </td>
                                                </tr>
                                                </c:forEach>    
                                            </tbody>
                                        </table>    
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
                            <div class="panel panel-default">                              
                                <div class="panel-body">
                                    <div class="col-xs-12 ">
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Remark&nbsp;</lable>                                         
                                        </div>
                                        <div class="col-sm-6" style="padding-left: 50px">
                                            <textarea  rows="3" cols="200" id="Remark" name="Remark" class="form-control" value="">${taxInvoice.remark}</textarea>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 form-group"></div>
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
                                        <div class="col-sm-1" style="width: 280px">
                                            <input  rows="3" cols="200" id="TotalAmount" name="TotalAmount" class="form-control" value="" readonly="">
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
                            <div class="panel panel-default">                              
                                <div class="panel-body">
                                    <div class="col-xs-12 ">
                                        <div class="col-md-2 text-right ">
                                            <button type="button" onclick="" class="btn btn-default">
                                                <span id="SpanPrintPackage" class="glyphicon glyphicon-print"></span> Print Package
                                            </button>
                                        </div>
                                        <div class="col-md-2 text-left " style="padding-left: 0px">
                                            <button type="button" onclick="" class="btn btn-default">
                                                <span id="SpanPrintInvoiceNew" class="glyphicon glyphicon-print"></span> Print Invoice New
                                            </button>
                                        </div>
                                        <div class="col-md-4 text-right "></div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="printVoucher('');" class="btn btn-default">
                                                <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" class="btn btn-primary hidden" onclick="EnableVoid();" data-toggle="modal" data-target="#EnableVoid">
                                                <span id="SpanEnableVoid" class="glyphicon glyphicon-ok" ></span> Void
                                            </button>
                                            <button type="button" class="btn btn-danger" onclick="DisableVoid();" data-toggle="modal" data-target="#DisableVoid">
                                                <span id="SpanDisableVoid" class="glyphicon glyphicon-remove" ></span> Void
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="submit" onclick="" class="btn btn-success">
                                                <span id="SpanSave" class="fa fa-save"></span> Save 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="" class="btn btn-success">
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

<!--Disable Modal-->
<div class="modal fade" id="DisableVoid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Finance & Cashier - Tax Invoice</h4>
            </div>
            <div class="modal-body" id="disableVoid">
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick='window.top.location.href="Invoice.smi?type=${param.type}&action=edit"'>Delete</button>               
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
                <table class="display" id="SearchTaxInvoiceToTable">
                    <thead class="datatable-header">
                        <script>
                            var taxInvoiceTo = [];
                        </script>
                        <tr>
                            <th style="width: 30%">Code</th>
                            <th style="width: 70%">Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customerAgent" items="${customer_agent_list}">
                            <tr onclick ="setupCustomerAgentValue('${customerAgent.billTo}', '${customerAgent.billName}', '${customerAgent.address}')">
                                <td>${customerAgent.billTo}</td>
                                <td>${customerAgent.billName}</td>
                            </tr>
                            <script>
                                taxInvoiceTo.push({name: "${customerAgent.billTo}", billTo: "${customerAgent.billName}", address: "${customerAgent.address}"});
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
<div class="modal fade" id="delTaxInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <c:choose>
                    <c:when test="${param.Department=='WO'}">
                        <h4 class="modal-title">Delete Tax Invoice Wendy/Outbound</h4>
                    </c:when>
                    <c:when test="${param.Department=='INB'}">
                        <h4 class="modal-title">Delete Tax Invoice Inbound</h4>
                    </c:when> 
                </c:choose>
                <!--<h4 class="modal-title">Delete Tax Invoice </h4>-->
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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
    $(document).ready(function () {
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
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
        
        $('#SearchTaxInvoiceToTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
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
        
        $(".money").mask('0000000000', {reverse: true});
     
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
        
        $("#invoiceNo").keyup(function (event) {
            if (event.keyCode === 13) {
                searchInvoiceNo();
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
                        AddRowTaxInvoiceTable(rowAll);
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
        
    });
    
    function setupCustomerAgentValue(billTo,billName,address){
        $('#TaxInvToModal').modal('hide');
        document.getElementById('TaxInvTo').value = billTo;
        document.getElementById('InvToName').value = billName;
        if(address === null){
            document.getElementById('InvToAddress').value = '';
        } else {
            document.getElementById('InvToAddress').value = address;
        }       
        document.getElementById('ARCode').value = billTo;
        document.getElementById('InvTo').focus();
//        $('#PaymentTourHotelForm').bootstrapValidator('revalidateField', 'InputInvoiceSupCode');
//        $('#PaymentTourHotelForm').bootstrapValidator('revalidateField', 'InputAPCode');
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
                   $("#InvTo_Id").val(billid);
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
                                }                 
                            }   
                       }
                    });

                   var billval = $("#TaxInvTo").val();
                   for(var i =0;i<billListId.length;i++){
                       if(billval==billListName[i]){
                           $("#TaxInvTo").val(billListId[i]);
                       }
                   }
                   if(billListId.length == 1){
                       showflag = 0;
                       $("#TaxInvTo").val(billListId[0]);
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
        var invoiceNo = $("#invoiceNo").val();
        if(invoiceNo === ""){
            if(!$('#invoiceNoInput').hasClass('has-feedback')) {
                $('#invoiceNoInput').addClass('has-feedback');
            }
            $('#invoiceNoInput').removeClass('has-success');
            $('#invoiceNoInput').addClass('has-error');
        } else {
            var servletName = 'TaxInvoiceServlet';
            var servicesName = 'AJAXBean';
            var param = 'action=' + 'text' +
                    '&servletName=' + servletName +
                    '&servicesName=' + servicesName +
                    '&invoiceNo=' + invoiceNo +
                    '&type=' + 'searchInvoiceNo';
            CallAjaxSearchInvoice(param);
        }  
    }
    
    function invoiceNoValidate(){
        $('#invoiceNoInput').removeClass('has-feedback');
        $('#invoiceNoInput').addClass('has-success');
        $('#invoiceNoInput').removeClass('has-error');  
    }
    
    function AddRowTaxInvoiceTable(row) {
        if (!row) {
            row = 1;
        }
        $("#TaxInvoiceTable tbody").append(           
            '<tr>' +
            '<td class="hidden"><input class="form-control" type="text" id="taxDetailId' + row + '" name="taxDetailId' + row + '" value=""></td>' +
            '<td><select class="form-control" name="product' + row + '" id="product' + row + '"><option  value="" >---------</option></select></td>' +
            '<td><input class="form-control" type="text" id="refNo' + row + '" name="refNo' + row + '" value=""></td>' +
            '<td><input class="form-control" type="text" id="description' + row + '" name="description' + row + '" value=""></td>' +
            '<td align="right"><input class="form-control" type="text" id="cost' + row + '" name="cost' + row +'" value=""></td>' +
            '<td><select class="form-control" name="currencyCost' + row + '" id="currencyCost' + row + '"><option  value="" >---------</option></select></td>' +
            '<td align="center"><input type="checkbox" id="isVat' + row + '" name="isVat' + row + '" value="check"></td>' +
            '<td align="right"><input class="form-control" type="text" id="vat' + row + '" name="vat' + row + '" value=""></td>' +
            '<td align="right"><input class="form-control" type="text" id="gross' + row + '" name="gross' + row + '" value=""></td>' +
            '<td align="right"><input class="form-control" type="text" id="amount' + row + '" name="amount' + row + '" value=""></td>' +
            '<td><select class="form-control" name="currencyAmount' + row + '" id="currencyAmount' + row + '"><option  value="" >---------</option></select></td>' +
            '<td align="center" >' + 
                '<center>' +
                '<a id="expenButtonRemove' + row + '" name="expenButtonRemove' + row + '" class="RemoveRow" onclick="">' + 
                '<span id="expenSpanEdit' + row + '" name="expenSpanEdit' + row + '" class="glyphicon glyphicon-remove deleteicon"></span></a>' + 
                '</center>' +
            '</td>' +
            '</tr>'           
        );
        $("#select_product_list option").clone().appendTo("#product" + row);
        $("#select_currency_list option").clone().appendTo("#currencyCost" + row);
        $("#select_currency_list option").clone().appendTo("#currencyAmount" + row);
        $("#countTaxInvoice").val(row);

    }
    
    
    
</script>
