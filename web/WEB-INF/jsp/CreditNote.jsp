<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/CreditNote.js"></script> 
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
        Finance & Cashier - Credit Note
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Credit Note</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;" ng-app="">
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/CreditNoteMainMenu.html'"></div>
        </div>
        <div class="col-sm-10">
            <div class="col-sm-6 " style="padding-right: 15px">
                <c:choose>
                     <c:when test="${fn:contains(page , 'W')}">
                        <h4><b>Credit Note Wendy</b></h4>
                    </c:when>
                    <c:when test="${fn:contains(page , 'O')}">
                        <h4><b>Credit Note Outbound</b></h4>
                    </c:when>     
                    <c:when test="${fn:contains(page , 'I')}">
                        <h4><b>Credit Note Inbound</b></h4>
                    </c:when> 
                </c:choose> 
            </div>
            <hr/>
            <form action="CreditNote${page}.smi" method="post" id="CreditNoteForm" name="CreditNoteForm" role="form">
               <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right" style="width: 120px">
                            <label class="control-label text-right">CN No. </label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <input id="cnNo" name="cnNo" type="text" class="form-control" value="">
                        </div>
                        <div class="col-xs-1 text-right"  style="width: 80px">
                            <label class="control-label text-right">Date </label>
                        </div>
                        <div class="col-xs-1"  style="width: 200px">
                            <div class='input-group date'>
                                <input id="inputDate" name="inputDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 100px">
                            <label class="control-label text-right">AP Code </label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <input id="apCode" name="apCode" type="text" class="form-control" value="">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right" style="width: 120px">
                            <label class="control-label text-right">Name </label>
                        </div>
                        <div class="col-xs-1" style="width: 481px">
                            <input id="name" name="name" type="text" class="form-control" value="">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right"  style="width: 120px">
                            <label class="control-label text-right">Address </label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <div class="input-group">                                    
                                <textarea rows="3" class="form-control" id="remark" name="remark" style="width: 279%"></textarea>  
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            
            <!-- tab search invoice -->
            <form action="" method="post" id="SearchInvoiceForm">
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="infoSearchInvoice">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h2 class="panel-title">
                                        <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" onclick="">
                                            <span id="SpanEdit${advanced.search}">Search Invoice</span>
                                        </a>
                                        <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" style="margin-left: 54em" onclick="">
                                            <span id="arrowReservstion" class="arrowReservstion glyphicon glyphicon-chevron-up"></span> 
                                        </a>
                                    </h2>               
                                </div>
                                <div class="panel-body">               
                                    <div class=" accordion-body collapse in" id="collapseExample${advanced.search}" aria-expanded="false">
                                        <div class="col-md-12">
                                            <div class="col-xs-1 text-right" style="width: 70px">
                                                <label class="control-label text-right">No <font style="color: red">*</font></label>
                                            </div>
                                            <div class="col-xs-1" style="width: 150px">
                                                <div class="input-group">
                                                    <input id="invoiceNo" name="invoiceNo" type="text" class="form-control" value="">
                                                </div>
                                            </div>
                                            <div class="col-xs-1 text-right" style="width: 120px">
                                                <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="" class="btn btn-primary btn-sm">
                                                    <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                                                </button>                                          
                                            </div>      
                                        </div>
                                        <div class="col-xs-12 form-group"></div>
                                        <div class="row" style="padding-left:10px;padding-right: 10px;">    
                                            <div class="col-md-12">
                                                <table id="InvoiceSearchTable" class="display" cellspacing="0" width="100%">
                                                    <thead>
                                                        <tr class="datatable-header">
                                                            <th style="width:7%;">No.</th>
                                                            <th style="width:15%;">Date</th>
                                                            <th style="width:15%;">Product Type</th>
                                                            <th style="width:15%;">Amount</th>
                                                            <th style="width:15%;">Real Amount</th>
                                                            <th style="width:25%;">Description</th>
                                                            
                                                        </tr>
                                                    </thead>
                                                    <tbody>               
                                                        <tr>
                                                            <td align="center">1</td>
                                                            <td align="center">2015-07-21</td>
                                                            <td align="center">TEST!!!</td>
                                                            <td align="center">100000</td>
                                                            <td align="center">100000</td>
                                                            <td align="center">test test test</td>
                                                            
                                                        </tr>
                                                    </tbody>
                                                </table>    
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>     

            <!--Table-->
            <div class="row">
                <div class="col-md-12 ">
                    <div class="col-xs-1 text-left" style="width: 150px">
                       <h4><b>Item Credit</b></h4>
                    </div>
                    <table id="ItemCreditTable" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header" >

                                <th style="width:12%;">Inv Type</th>
                                <th style="width:8%;">No</th>
                                <th style="width:12%;">Date</th>
                                <th style="width:12%;">Product Type</th>
                                <th style="width:12%;">Amount</th>
                                <th style="width:12%;">Real Amount</th>
                                <th style="width:25%;">Description</th>
                                <th style="width:10%;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                              <%--<c:forEach var="refundAirline" items="${refundAirlineList}" varStatus="varRefundAirline">--%>
                            <tr>

                                <td style="text-align:center">12345678321</td>
                                <td style="text-align:center">1</td>
                                <td style="text-align:center">2015-01-01</td>
                                <td style="text-align:center">W</td>
                                <td style="text-align:center">2500</td>
                                <td style="text-align:center">2300</td>
                                <td style="text-align:center">TEST!!!!!</td>
                                <td class="text-center">
                                    <a id="ButtonRemove${varRefundAirline.count}" data-toggle="modal" data-target="#DeleteRefundAirline" onclick="">
                                        <i id="IRemove${varRefundAirline.count}" class="glyphicon glyphicon-remove deleteicon"></i>
                                    </a>
                                </td>
                            </tr>
                            <%--</c:forEach>--%>
                        </tbody>
                    </table>
                </div>
            </div> 
            <div class="row">
                <div class="col-md-12" style="padding-top: 15px">
                    <div class="col-xs-1 text-right" style="width: 80px">
                        <label class="control-label text-right">Remark</label>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 800px">
                        <div class="input-group">                                    
                            <textarea rows="3" class="form-control" id="remark" name="remark" style="width: 582%"></textarea>  
                        </div>
                    </div>
                </div>
            </div>
            <div class="row"><div class="col-md-12" style="padding-top: 15px"></div></div>
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
                                            <button type="button" onclick="" class="btn btn-success">
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
            </div>  
        </div>
    </div>
</div>

