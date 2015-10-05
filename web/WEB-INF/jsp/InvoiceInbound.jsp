<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="page" value="${requestScope['page']}" />

<section class="content-header" >
    <h1>
        Finance & Cashier - Invoice Inbound
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Invoice Inbound</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app="">
    <div class="row">
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/FinanceAndCashier/InvoiceMenu.html'"></div>
    </div>
        <div class="col-sm-10">
            <form action="Invoice${page}.smi" method="post" id="InvoiceInboundForm" role="form" onsubmit="">
                <!--<input type="text" id="action" name="action" value="Action">--> 
            <div id="textAlertDisable"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Void Success </strong> 
            </div>
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <c:choose>
                        <c:when test="${fn:contains(page , 'PM')}">
                            <h4><b>Proforma Invoice  </h4>
                        </c:when>
                        <c:when test="${fn:contains(page , 'RV')}">
                            <h4><b>Revenue Invoice  </h4>
                        </c:when>                        
                    </c:choose> 
                </div>
                <div class="col-xs-12 form-group"><hr/></div>
            </div>
            
            <!--Search Invoice-->
            <div class="row" style="padding-left: 15px">                         
                <div class="col-xs-12 form-group"></div>   
                <input type="hidden"  class="form-control" id="InvoiceInboundId" name="InvoiceInboundId"  value="" >
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right">
                            <label class="control-label" for="">INV no</lable>
                        </div>
                        <div class="col-md-1 form-group" style="width: 125px;">
                            <input type="text"  class="form-control" id="InvNo" name="InvNo"  value="" >
                        </div>
                        <div class="col-md-1 form-group" style="width: 120px;">
                            <button type="button"  id="ButtonSearchInvoiceInboundNo"  name="ButtonSearchInvoiceInboundNo" onclick="" class="btn btn-primary btn-sm">
                                <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                            </button>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 105px;padding-left: 0px;">
                            <label class="control-label" for="">Invoice date<font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <div class='input-group date' id='InputDatePicker'>    
                                <c:if test='${invoice.invDate != null}'>
                                    <input id="InputInvDate" name="InputInvDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                         
                                </c:if>
                                <c:if test='${invoice.invDate == null}'>
                                    <input id="InputInvDate" name="InputInvDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                              
                                </c:if>  
                            </div>
                        </div>                  
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-sm-1 text-right">
                            <label class="control-label" for="">Inv To<font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-6 form-group">
                            <div class="input-group">
                            <input type="hidden" class="form-control" id="InvToId" name="InvToId" value=""/>
                            <input type="text" class="form-control" id="InvTo" name="InvTo" value="" style="background-color: #ffffff">
                            <span class="input-group-addon" id="InvTo_Modal"  data-toggle="modal" data-target="#InvToModal">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                            </div>
                        </div>
                        <div class="col-xs-2 text-right">
                            <label class="control-label" for="">AR Code</lable>
                        </div>
                        <div class="col-md-2 form-group">                      
                            <input  type="text" id="InputArCode" name="InputArCode" class="form-control" value="" readonly >
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-sm-1 text-right">
                            <label class="control-label" for="">Name<font style="color: red">*</font></lable>
                        </div>    
                        <div class="col-md-6 form-group">
                            <input  type="text" id="InvToName" name="InvToName" class="form-control" value="${invoice.invName}" >
                        </div>
                        <div class="col-xs-2 text-right">
                            <label class="control-label" for="">System Date</lable>
                        </div>
                        <div class="col-md-2 form-group">                      
                            <input  type="text" id="InputSystemDate" name="InputSystemDate" class="form-control" value="" readonly >
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-sm-1 text-right">
                            <label class="control-label" for="">Address </lable>
                        </div>
                        <div class="col-md-6 form-group">
                            <textarea  rows="3" cols="100" id="InvToAddress" name="InvToAddress" class="form-control" >${invoice.invAddress}</textarea>
                        </div>                  
                    </div>                              
                </div>
        
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="infoDetailBillable">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div id="textAlertCurrency"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <strong>Currency is not match!!! </strong> 
                                    </div>   
                                    <table class="display" id="DetailBillableTable">
                                        <thead class="datatable-header">
                                            <tr>
                                                <th class="hidden"> Id</th>
                                                <th style="width:30%;" align="left">Description</th>
                                                <th style="width: 3%" onclick="checkVatInvoiceAll()" align="center"><u>Is vat</u></th> 
                                                <th style="width: 3%" align="center">Vat</th>
                                                <th style="width: 8%" align="center">Gross</th>
                                                <th style="width: 8%" align="center">Amount</th>
                                                <th style="width: 7%" align="center" class="" >Cur</th>
                                                <th style="width: 3%" align="center">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr> 
                                                <td class="hidden"><input type="text" class="form-control" id="detailId${taxdesc.count}" name="detailId${taxdesc.count}" value="" > </td>
                                                <td><input type="text" class="form-control" id="BillDescriptionTemp${taxdesc.count}" name="BillDescriptionTemp${taxdesc.count}"  onkeyup="" value=""></td>                                              
                                                <td><input type="checkbox" id="checkUse${taxdesc.count}" name="checkUse${taxdesc.count}"  onclick="" value=""></td>
                                                <td>7</td>                                            
                                                <td ><input type="text" maxlength ="15" readonly  onfocusout="changeFormatGrossNumber(${taxdesc.count})" class="form-control numerical" id="InputGross${taxdesc.count}" name="InputGross${taxdesc.count}" value="" ></td>
                                                <td><input type="text" maxlength ="15" class="form-control numerical text-right" id="InputAmount${taxdesc.count}" name="InputAmount${taxdesc.count}" onfocusout="changeFormatAmountNumber('${taxdesc.count}');"  value=""></td>
                                                <td class="priceCurrencyAmount">
                                                    <select id="SelectCurrencyAmount${taxdesc.count}" name="SelectCurrencyAmount${taxdesc.count}" class="form-control" onclick="validFromInvoice()">
                                                        <option value='' ></option>
                                                        <c:forEach var="cur" items="${listCurrency}">
                                                            <c:set var="selectA" value="" />
                                                            <c:if test="${cur.code == ind.curAmount}">
                                                                <c:set var="selectA" value="selected" />
                                                            </c:if> 
                                                            <option value='${cur.code}' ${selectA}>${cur.code}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>                                             
                                                <td align="center" >
                                                    <span  class="glyphicon glyphicon-th-list" data-toggle="modal" data-target="#DescriptionInvoiceDetailModal" onclick="" id="InputDescription${taxdesc.count}"></span>
                                                    <span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill('${taxdesc.count}','${ind.description}')" data-toggle="modal" data-target="#DelDetailBill" >  </span>        
                                                </td>                                         
                                            </tr>
                                        </tbody>
                                    </table>                                                                                        
                            <div id="tr_FormulaAddRow" class="text-center" style="padding-top: 10px;display: none;">
                                <a class="btn btn-success" onclick="AddRowDetailBillAble()">
                                    <i class="glyphicon glyphicon-plus"></i> Add
                                </a>
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
                            <div class="col-xs-12 form-group"></div>
                            <div class="col-xs-12 ">
                                <div class="col-sm-1">
                                    <label class="control-label" for="">Text&nbsp;Amount&nbsp;:</lable>                                         
                                </div>                                      
                                <div class="col-sm-5" style="padding-left: 50px">
                                    <input  rows="3" cols="200" id="TextAmount" name="TextAmount" class="form-control" value="" readonly="">
                                </div>
                                <div class="col-sm-2 text-right" >
                                    <label class="control-label" for="">Total&nbsp;Net&nbsp;:</lable>                                         
                                </div>
                                <div class="col-sm-3" >
                                    <input  rows="3" cols="200" id="TotalNet" name="TotalNet" class="form-control" value="" readonly="">
                                </div>
                            </div>
                            <div class="col-xs-12 "><br></div>
                            <div class="col-xs-12 ">                                     
                                <div class="col-sm-6" style="padding-left: 50px"></div>
                                <div class="col-sm-2 text-right">
                                    <label class="control-label" for="">Vat&nbsp;Net&nbsp;:</lable>                                         
                                </div>
                                <div class="col-sm-3" >
                                    <input  rows="3" cols="200" id="VatNet" name="VatNet" class="form-control" value="" readonly="">
                                </div>
                            </div> 
                            <div class="col-xs-12 "><br></div>
                            <div class="col-xs-12 ">                                     
                                <div class="col-sm-6" style="padding-left: 50px"></div>
                                <div class="col-sm-2 text-right">
                                    <label class="control-label" for="">Grand Total&nbsp;Net&nbsp;:</lable>                                         
                                </div>
                                <div class="col-sm-3" >
                                    <input  rows="3" cols="200" id="GrandTotal" name="GrandTotal" class="form-control" value="" readonly="">
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
                            <div class="col-xs-12 text-right">
                                <div class="col-md-6 text-right "></div>                                   
                                <div class="col-md-1 text-left " style="padding-left: 0px">
                                    <button type="button" class="btn btn-default" id="printButton" onclick="" data-toggle="modal" data-target="#PrintModal">
                                        <span id="SpanPrintInvoiceNew" class="glyphicon glyphicon-print"></span> Print
                                    </button>
                                </div>
                                <div class="col-md-1 text-left " style="width: 125px">
                                    <button type="button" class="btn btn-default" id="sendEmailButton" onclick="" data-toggle="modal" data-target="#PrintModal">
                                        <span id="buttonEmail" class="glyphicon glyphicon-send" ></span> Send Email 
                                    </button>
                                </div>

                                <div class="col-md-1 text-right ">                                      
                                    <button type="button" class="btn btn-danger" onclick="" data-toggle="modal" data-target="#DisableVoid" id="disableVoidButton" name="disableVoidButton"  >
                                        <span id="SpanDisableVoid" class="glyphicon glyphicon-remove" ></span> Void
                                    </button>
                                </div>
                                <div class="col-md-1 text-right ">
                                    <button type="submit"  id="saveInvoice" name="saveInvoice" class="btn btn-success" >
                                        <span id="SpanSave" class="fa fa-save"></span> Save 
                                    </button>
                                </div>
                                <div class="col-md-1 text-right ">
                                    <button type="button" onclick="" class="btn btn-success"  >
                                        <span id="SpanNew" class="fa fa-plus-circle"></span> New 
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </form>
</div>
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
                Are you confirm to void invoice ?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick='' data-dismiss="modal">Void</button>               
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
                Are you confirm to cancel void invoice ${invoice.invNo}?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal" onclick='Enable()'>Cancel Void</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<!--Delete Detail Billable Modal-->
<div class="modal fade" id="DelDetailBill" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Delete Detail Billable </h4>
            </div>
            <div class="modal-body" id="DeleteDetailBillable">
                
            </div>
            <div class="modal-footer">  
                <button type="button" onclick="DeleteBill()" class="btn btn-danger" data-dismiss="modal">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade " id="DescriptionInvoiceDetailModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Description</h4>
            </div>
            
            <div class="modal-body" >
                <div class="row">
                    <div class="col-md-2">
                        <h5>Description</h5>
                    </div>
                    <div class="col-md-10">
                        <textarea id="InputDescriptionDetail" rows="12" style="width: 450px" class="form-control" >

                        </textarea>
<!--<input type="text" id="InputDescriptionDetail" value="" style="width: 400px;height: 200px;">-->
                    </div>
                </div>
                
            </div>
            <div class="modal-footer">  
                <button type="button" onclick="saveDescriptionDetail()" class="btn btn-success" data-dismiss="modal">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade " id="CopyModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Copy Invoice</h4>
            </div>
            
            <div class="modal-body" >
                You want copy invoice ? If copy click OK
            </div>
            <div class="modal-footer">  
                <button type="button" onclick="copyInvoice()" class="btn btn-success" data-dismiss="modal">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade " id="PrintModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Print</h4>
            </div>
            <div class="modal-body" >
                <div class="row">
                    <div class="col-md-5">
                        <h5>Sales Staff </h5>
                    </div>
                    <div class="col-md-7">
                        <select id="selectSalesStaff" name="selectSalesStaff" class="form-control">
                            <option value="0">Not Show Sales Staff</option>
                            <option value="1">Show Sales Staff</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <h5>Show Leader to Invoice </h5>
                    </div>
                    <div class="col-md-7">
                        <select id="selectLeader" name="selectLeader" class="form-control">
                            <option value="0">Not Show Leader</option>
                            <option value="1">Show Leader</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <h5>Payment by money Transfer</h5>
                    </div>
                    <div class="col-md-7">
                        <select id="selectPayment" name="selectPayment" class="form-control">
                            <option value="0">Not show</option>
                            <option value="SCB2">Payment Bank Siam commercial bank PCL</option>
                            <option value="BBL">Payment Bank Bangkok bank PCL</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <h5>Sign </h5>
                    </div>
                    <div class="col-md-7">
                        <select id="SelectSign" name="SelectSign" class="form-control">
                            <option value="">--Sign--</option>
                            <option value="benjaporn">Benjaporn</option>
                            <option value="pawina">Pawina</option>
                            <option value="supavadee">Supavadee</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer">  
                <button type="button" onclick="printInvoiceNew()" class="btn btn-success" data-dismiss="modal">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--Inv To Modal-->
<div class="modal fade" id="InvToModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Invoice To</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->
                <div style="text-align: right"> <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchInvoiceFrom" name="searchInvoiceFrom"/> </div> 
                <table class="display" id="InvToTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${listCustomerAgentInfo}">
                            <tr onclick="setBillValue('${item.billTo}', '${item.billName}', '${item.address}', '${item.term}', '${item.pay}');">                                
                                <td class="item-billto">${item.billTo}</td>
                                <td class="item-name">${item.billName}</td>                                
                                <td class="item-address hidden">${item.address}</td>
                                <td class="item-tel hidden">${item.tel}</td>
                            </tr>
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
    </div><!-- /.modal-dialog --> <!-- /.modal-dialog -->
</div>

<!--Sale Staff To Modal-->
<div class="modal fade" id="SaleStaffModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Sale Staff</h4>
            </div>
            <div class="modal-body">
                <script>
                    staff = [];
                </script>
                <table class="display" id="SaleStaffTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th>staff Name</th>
                        </tr>
                    </thead>                  
                    <tbody>                  
                        <c:forEach var="item" items="${listStaff}" varStatus="loop">
                            <tr class="packet">
                                <td class="staff-id hidden"><c:out value="${item.id}" /></td>
                                <td class="staff-code"><c:out value="${item.username}" /></td>
                                <td class="staff-name"><c:out value="${item.name}" /></td>                            
                            </tr>
                            <script>
                                staff.push({id: "${item.id}", code: "${item.username}", name: "${item.name}"});
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
<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
       $('.date').datetimepicker();
       $('.datemask').mask('0000-00-00');
       $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

       });
//       $('#MasterReservation tbody').on('click', 'tr', function () {
//            if ($(this).hasClass('row_selected')) {
//                $(this).removeClass('row_selected');
//                $('#hdGridSelected').val('');
//            }
//            else {
//                table.$('tr.row_selected').removeClass('row_selected');
//                $(this).addClass('row_selected');
//                $('#hdGridSelected').val($('#MasterReservation tbody tr.row_selected').attr("id"));
//            }
//        });
    
//        $('#DetailBillableTable tbody').on('click', 'tr', function () {
//            if ($(this).hasClass('row_selected')) {
//                $(this).removeClass('row_selected');
//                $('#hdGridSelected').val('');
//            }
//            else {
//                table.$('tr.row_selected').removeClass('row_selected');
//                $(this).addClass('row_selected');
//                $('#hdGridSelected').val($('#DetailBillableTable tbody tr.row_selected').attr("id"));
//            }
//        });
        
        $('#collapseExample${advanced.search}').on('shown.bs.collapse', function () {
            $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-down").addClass("glyphicon glyphicon-chevron-up");
        });

        $('#collapseExample${advanced.search}').on('hidden.bs.collapse', function () {
           $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-up").addClass("glyphicon glyphicon-chevron-down");
        });
    });
    
            $("#InvoiceForm")
        .bootstrapValidator({
            framework: 'bootstrap',
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {                
                InvTo: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input Invoice To'
                        }
                    }
                },
                InvToName: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input Invoice To Name'
                        }
                    }
                },
                ARCode: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input A/R Code'
                        }
                    }
                },
                InputInvDate: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input Invoice Date'
                        }
                    }
                } 
            }  
        });
</script>
<script type="text/javascript" charset="utf-8">
    var select = "<option value='' ></option>";
    var select_cost = "<option value='' ></option>";
    var selectType = "<option value='' ></option>";
    var defaultD = "";
    $(document).ready(function () {
        <c:forEach var="cur" items="${listCurrency}">
            select += "<option value='${cur.code}' ><c:out value='${cur.code}' /></option>";
            select_cost += "<option value='${cur.code}' ><c:out value='${cur.code}' /></option>";
        </c:forEach>
        <c:forEach var="ty" items="${listType}">
            selectType += "<option value='${ty.id}' ><c:out value='${ty.name}' /></option>";
        </c:forEach>
        
        var recipt = $('#checkRecipt').val();
        if(recipt === "yesReceipt"){
            console.log("C");
            $('#textAlertRecipt').show();
        }else if(recipt === "noReceipt"){
             $('#textAlertRecipt').hide();
        }
        var taxin = $('#checkTaxinvoice').val();
        if(taxin === "yesTaxinvoice"){
            $('#textAlertTaxinvoice').show();
        }else if(taxin === "noTaxinvoice"){
             $('#textAlertTaxinvoice').hide();
        }
    }); 
</script>
<c:if test="${defaultData != null}">        
    <script language="javascript">
       defaultD = ${defaultData.value};
    </script>
</c:if>
<input type="hidden" id="showvat" name="showvat" value="${showvat}">      
<input type="hidden" id="type" name="type" value="${param.type}">
<input type="hidden" id="resultAction" name="resultAction" value="${result}">
<input type="hidden" id="roleName" name="roleName" value="${roleName}">
<input type="hidden" id="InputDescriptionDetailId" name="InputDescriptionDetailId" value="">
<input type="hidden" id="resultText" name="resultText" value="${result}">
<input type="hidden" id="checkTaxinvoice" name="checkTaxinvoice" value="${checkTaxinvoice}">
<input type="hidden" id="checkRecipt" name="checkRecipt" value="${checkRecipt}">
<input type="hidden" id="typeBooking" name="typeBooking" value="${typeBooking}">
<input type="hidden" id="typePrint" name="typePrint" value="">
<input type="hidden" value="${textVoid}">
<input type="hidden" id="invoiceType" name="invoiceType" value="${invoiceType}">

