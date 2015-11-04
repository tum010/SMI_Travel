<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<script type="text/javascript" src="js/CreditNote.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<script type="text/javascript" charset="utf-8">
    var vat = ${vat};
</script>
<style type="text/css">
    /*table tr:nth-child(n) {background: #EEE}*/
    table tr:nth-child(2n) {background: #F5F5F5}

</style>
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
<div style="padding-top: 15px;padding-right: 0px "ng-app="">
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/CreditNoteMainMenu.html'"></div>
        </div>
        <c:set var="panelheader" value=""/>
        <c:set var="panelborder" value=""/>
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <c:set var="type" value=""/>
                    <c:choose>
                        <c:when test="${fn:contains(page , 'W')}">
                            <c:set var="type" value="Wendy"/>
                            <c:set var="panelheader" value="wendyheader"/>
                            <c:set var="panelborder" value="wendyborder"/>
                        </c:when>
                        <c:when test="${fn:contains(page , 'O')}">
                            <c:set var="type" value="Outbound"/>
                            <c:set var="panelheader" value="outboundheader"/>
                            <c:set var="panelborder" value="outboundborder"/>
                        </c:when>     
                        <c:when test="${fn:contains(page , 'I')}">
                            <c:set var="type" value="Inbound"/>
                        </c:when> 
                    </c:choose> 
                    <h4><b>Credit Note ${type} <font style="color: red;">${creditNote.MFinanceItemstatus.id == '2' ? 'VOID' : ''}</font></b></h4>
                </div>
            </div>
            <hr/>
            <form action="CreditNote${page}.smi" method="post" id="CreditNoteForm" name="CreditNoteForm" role="form" class="ng-pristine ng-valid bv-form">
                <div id="alertSuccess"  style="" class="alert alert-success alert-dismissible" role="alert" <c:if test="${successStatus != true}">hidden="true"</c:if> >
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong id="alertTextSuccess">${successMessage}</strong> 
                </div>
                <div id="alertFail"  style="" class="alert alert-danger alert-dismissible" role="alert"  <c:if test="${failStatus != true}">hidden="true"</c:if>>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong id="alertTextFail">${failMessage}</strong> 
                </div>
                <input type="hidden" name="action" id="action" value="search">
                <input type="hidden" name="cnId" id="cnId" value="${creditNote.id}"/>
                <div class="row">
                    <div class="col-xs-5 form-group">
                        <div class="col-xs-1 text-right" style="width: 120px">
                            <label class="control-label text-right">CN No. </label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <input id="cnNo" name="cnNo" type="text" class="form-control" value="${creditNote.cnNo}">
                        </div>
                        <div class="col-md-1 text-right ">
                            <button type="button" id="buttonSearch" class="btn btn-primary">
                                <span class="fa fa-search"></span> Search 
                            </button>
                        </div>
                    </div>
                    <div class="col-xs-4 form-group" style="padding-left: 70px">
                        <div class="col-xs-1 text-right"  style="width: 80px; margin-left: -70px">
                            <label class="control-label text-right">Date <font style="color: red">*</font></label>
                        </div>
                        <div class="col-xs-1"  style="width: 200px">
                            <div class='input-group date'>
                                <input id="inputDate" name="inputDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${creditNote.createDate}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-3 form-group">
                        <div class="col-xs-1 text-right" style="width: 100px; margin-left: -100px">
                            <label class="control-label text-right">AP Code </label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <input id="apCode" name="apCode" type="text" class="form-control" value="${creditNote.apCode}" readonly="">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right" style="width: 120px">
                            <label class="control-label text-right">Name <font style="color: red">*</font></label>
                        </div>
                        <div class="col-xs-1" style="width: 481px">
                            <input id="name" name="name" type="text" class="form-control" value="${creditNote.cnName}">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right"  style="width: 120px">
                            <label class="control-label text-right">Address <font style="color: red">*</font></label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <div class="input-group">                                    
                                <textarea rows="3" class="form-control" id="address" name="address" style="width: 279%"><c:out value="${creditNote.cnAddress}"/></textarea>  
                            </div>
                        </div>
                    </div>
                </div>

                <!-- tab search invoice -->
                <!--Table-->
                <div class="row">
                    <div class="col-md-12 ">
                        <div class="col-xs-1 text-left" style="width: 150px">
                            <h4><b>Item Credit</b></h4>
                        </div>
                        <table id="ItemCreditTable" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr class="datatable-header" >


                                    <th style="width:10%;">Tax Invoice No</th>
                                    <th style="width:15%;">Date</th>
                                    <th style="width:10%;">Product Type</th>
                                    <th style="width:12%;">Amount</th>
                                    <th style="width:12%;">Real Amount</th>
                                    <th style="width:10%;">Vat Amount</th>
                                    <th style="width:25%;">Description</th>
                                    <th style="width:20%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="creditNoteDetail" items="${creditNote.creditNoteDetails}" varStatus="varRefundAirline">
                                    <tr>
                                        <td style="text-align:center">
                                            <input name="taxId" value="${creditNoteDetail.taxInvoice.id}"  type="hidden"/>
                                            <input name="id" value="${creditNoteDetail.id}"  type="hidden"/>
                                            <input id="taxNo" name="taxNo" type="text" class="form-control" value="${creditNoteDetail.taxInvoice.taxNo}" /></td>
                                        <td style="text-align:center">
                                            <div class="input-group date" >
                                                <input type="text" name="taxDate" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD"   value="${creditNoteDetail.taxInvoice.taxInvDate}" />
                                                <span class="input-group-addon spandate" style="padding : 1px 10px;"><span class="glyphicon-calendar glyphicon"></span></span>
                                            </div>
                                        </td>
                                        <td style="text-align:center">
                                            <!--<input type="" name="taxType" class="form-control" value=""/>-->
                                            <select name="taxType" >
                                                <option value=""></option>
                                                <c:forEach var="item" items="${productTypeList}" >
                                                    <option value="${item.id}" ${creditNoteDetail.MPayType.id == item.id ? 'selected="selected"' : ''}>${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td style="text-align:center">
                                            <!--<input type="hidden" name="taxAmount" value="${creditNoteDetail.amount}"/>-->
                                            <input type="text" id="taxAmount" name="taxAmount" class="form-control text-right decimal" value="${creditNoteDetail.amount}"/>
                                        </td>
                                        <td style="text-align:center">
                                            <!--<input type="hidden" name="taxReal" value="${creditNoteDetail.realamount}"/>-->
                                            <input type="text" id="taxReal" name="taxReal" class="form-control text-right decimal" value="${creditNoteDetail.realamount}"/>
                                        </td>
                                        <td style="text-align:center">
                                            <!--<input type="hidden" name="taxVat" value="${creditNoteDetail.vat}" readonly/>-->
                                            <input type="text" id="taxVat" name="taxVat" class="form-control text-right decimal" value="${creditNoteDetail.vat2Digits}" readonly=""/>
                                        </td>
                                        <td style="text-align:center">
                                            <input type="text" name="taxDesc" class="form-control" value="${creditNoteDetail.description}"/></td>
                                        <td class="text-center">
                                            <a id="ButtonRemove${varRefundAirline.count}" data-toggle="modal" data-target="#DeleteRefundAirline" onclick="show('${creditNoteDetail.taxInvoice.taxNo}')">
                                                <i id="IRemove${varRefundAirline.count}" class="glyphicon glyphicon-list "></i>
                                            </a>
                                            <a id="ButtonRemove${varRefundAirline.count}" data-toggle="modal" data-target="#DeleteDetail" onclick="setDeletRow(this)">
                                                <i id="IRemove${varRefundAirline.count}" class="glyphicon glyphicon-remove deleteicon"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div id="addRow" class="text-center hide" style="padding-top: 10px">
                            <a class="btn btn-success" onclick="addRow(); this.hide()">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                    </div>
                </div> 

                <div class="collapse" id="flight1" aria-expanded="true" style="">
                    <!--Order Panel-->
                    <div class="panel panel-default ${panelborder}" style="margin-top: 10px">
                        <div class="panel-heading">
                            <h3 class="panel-title">Tax Invoice Detail</h3>
                        </div>
                        <div class="panel-body">

                            <table id="taxDetail" class="display" cellspacing="0" width="100%">
                                <thead>
                                    <tr class="datatable-header" >
                                        <th style="width:10%;">No</th>
                                        <th style="width:10%;">Product</th>
                                        <th style="width:10%;">Refno</th>
                                        <th style="width:10%;">Description</th>
                                        <th style="width:10%;">Amount</th>
                                        <th style="width:10%;">Cur</th>
                                    </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-md-12" style="padding-top: 15px">
                        <div class="col-xs-1 text-right" style="width: 80px">
                            <label class="control-label text-right">Remark</label>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 800px">
                            <div class="input-group">                                    
                                <textarea rows="3" class="form-control" id="remark" name="remark" style="width: 582%"><c:out value="${creditNote.cnRemark}"/></textarea>  
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row"><div class="col-md-12" style="padding-top: 15px"></div></div>
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="infoButton">
                            <div class="panel panel-default ${panelborder}">                              
                                <div class="panel-body">
                                    <div class="col-xs-12 ">
                                        <div class="col-md-2 text-right ">
                                            <button type="button" onclick="openReport()" class="btn btn-default">
                                                <span id="SpanPrintPackage" class="glyphicon glyphicon-print"></span> Print
                                            </button>
                                        </div>
                                        <div class="col-md-2 text-left " style="padding-left: 0px">
                                            <button type="button" onclick="sendMail()" class="btn btn-default">
                                                <span id="SpanPrintInvoiceNew" class="glyphicon glyphicon-envelope"></span> Send Mail
                                            </button>
                                        </div>
                                        <div class="col-md-3 text-right "></div>

                                        <div class="col-md-2 text-right ">
                                            <button type="button" class="btn btn-danger ${creditNote.MFinanceItemstatus.id == '1' ? '' : 'hidden'}" 
                                                    onclick="enableVoid();" data-toggle="modal" data-target="#voidModal"
                                                    ${enableVoid ? '' : 'disabled'}>
                                                <span id="SpanEnableVoid" class="glyphicon glyphicon-remove" ></span> Void
                                            </button>
                                            <button type="button" class="btn btn-danger ${creditNote.MFinanceItemstatus.id == '2' ? '' : 'hidden'}" 
                                                    onclick="disableVoid();" data-toggle="modal" data-target="#voidModal" 
                                                    ${disableVoid ? '' : 'disabled'}>
                                                <span id="SpanDisableVoid" class="glyphicon glyphicon-remove" ></span> Cancel Void
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="" id="buttonSave" class="btn btn-success">
                                                <span id="SpanSave" class="fa fa-save"></span> Save 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="" id="buttonNew" class="btn btn-success">
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
<div class="modal fade" id="DeleteDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Credit Note</h4>
            </div> 
            <div class="modal-body" id="delCode">
            </div>
            <div class="modal-footer">
                <button type="button" onclick="deleteCreditNotedetail()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /Delete Hotel modal -->
<div class="modal fade" id="voidModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="titleVoidModel"></h4>
            </div> 
            <div class="modal-body" id="voidCode">
            </div>
            <div class="modal-footer">
                <button id="voidBtn" type="button" onclick="" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /Delete Hotel modal -->

<div class="hide">
    <table id="tempTable">
        <tbody>
            <tr>
                <td style="text-align:center">
                    <input name="taxId" value="" type="hidden"/>
                    <input name="id" value=""  type="hidden"/>
                    <input id="taxNo" name="taxNo" type="text" class="form-control"  /></td>
                <td style="text-align:center">
                    <div class="input-group date" >
                        <input type="text" name="taxDate" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD"  />
                        <span class="input-group-addon spandate" style="padding : 1px 10px;"><span class="glyphicon-calendar glyphicon"></span></span>
                    </div>
                </td>
                <td style="text-align:center">
                    <select name="taxType" >
                        <option value=""></option>
                        <c:forEach var="item" items="${productTypeList}">
                            <option value="${item.id}" title="">${item.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td style="text-align:center">
                    <!--<input type="hidden" name="taxAmount"/>-->
                    <input type="text" id="taxAmount" name="taxAmount" class="form-control text-right decimal" />
                </td>
                <td style="text-align:center">
                    <!--<input type="hidden" name="taxReal"/>-->
                    <input type="text" id="taxReal" name="taxReal" class="form-control text-right decimal" />
                </td>
                <td style="text-align:center">
                    <!--<input type="hidden" name="taxVat"/>-->
                    <input type="text" id="taxVat" name="taxVat" class="form-control text-right decimal" readonly=""/>
                </td>
                <td style="text-align:center"><input type="text" name="taxDesc" class="form-control" /></td>
                <td class="text-center">
                    <a id="ButtonRemove${varRefundAirline.count}" name="btnDetail" data-toggle="modal" data-target="#DeleteRefundAirline" onclick="hide()">
                        <i id="IRemove${varRefundAirline.count}" class="glyphicon glyphicon-list "></i>
                    </a>
                    <a id="ButtonRemove${varRefundAirline.count}" data-toggle="modal" data-target="#DeleteRefundAirline" onclick="setDeletRow(this)">
                        <i id="IRemove${varRefundAirline.count}" class="glyphicon glyphicon-remove deleteicon"></i>
                    </a>
                </td>
            </tr>
        </tbody>
    </table>
</div>