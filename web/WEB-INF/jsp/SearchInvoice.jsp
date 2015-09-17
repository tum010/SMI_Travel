<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
<c:set var="listInvoice" value="${requestScope['listInvoice']}" />
<c:set var="fromdate" value="${requestScope['fromdate']}" />
<c:set var="todate" value="${requestScope['todate']}" />
<c:set var="department" value="${requestScope['department']}" />
<c:set var="type" value="${requestScope['type']}" />
<c:set var="agent" value="${requestScope['agent']}" />
<c:set var="agentName" value="${requestScope['agentName']}" />
<c:set var="listAgent" value="${requestScope['listAgent']}" />
<c:set var="result" value="${requestScope['result']}" />

<section class="content-header" >
    <h1>
        Finance & Cashier - Invoice
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Search Invoice</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app="">
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/InvoiceMenu.html'"></div>
        </div>
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Search Invoice</b></h4>
                </div>            
            </div>
            <form action="SearchInvoice.smi" method="post" id="SearchInvoiceForm" name="SearchInvoiceForm" role="form">
                <input type="text" class="hidden" id="action" name="action" value="">
                <div class="col-xs-12 ">
                    <div class="col-xs-1 text-right">
                        <label class="control-label" for="">From<font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-2 form-group"> 
                        <div class='input-group date fromdate' id="DateFrom">
                            <c:if test='${fromdate != null}'>
                                <input id="FromDate" name="FromDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${fromdate}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>
                            <c:if test='${fromdate == null}'>
                                <input id="FromDate" name="FromDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['']}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>                             
                        </div>
                    </div>
                    <div class="col-xs-1 text-right">
                        <label class="control-label" for="">To <font style="color: red">*</font>&nbsp;</lable>
                    </div>
                    <div class="col-md-2 form-group"> 
                        <div class='input-group date todate' id="DateTo">
                            <c:if test='${todate != null}'>
                                <input id="ToDate" name="ToDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${todate}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>
                            <c:if test='${todate == null}'>
                                <input id="ToDate" name="ToDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['']}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>                             
                        </div>
                    </div>
                    <div class="col-xs-1 text-right" style="padding: 0px 0px 0px 20px">
                        <label class="control-label" for="">Department</lable>
                    </div>
                    <div class="col-md-2 form-group" style="padding: 0px 0px 0px 30px">
                        <select class="form-control" id="Department" name="Department">
                            <option value="">--select--</option>
                                <c:set var="selectDepartW" value="" />
                                <c:set var="selectDepartO" value="" />
                                <c:set var="selectDepartI" value="" />
                                <c:choose>
                                    <c:when test="${department == 'Wendy'}">
                                        <c:set var="selectDepartW" value="selected" />
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${department == 'Outbound'}">
                                        <c:set var="selectDepartO" value="selected" />
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${department == 'Inbound'}">
                                        <c:set var="selectDepartI" value="selected" />
                                    </c:when>
                                </c:choose>
                            <option value="Wendy" ${selectDepartW}>Wendy</option>
                            <option value="Outbound" ${selectDepartO}>Outbound</option>
                            <option value="Inbound" ${selectDepartI}>Inbound</option>
                        </select>    
                    </div>
                    <div class="col-xs-1 text-right" style="padding: 0px 0px 0px 20px">
                        <label class="control-label" for="">Type</lable>
                    </div>
                    <div class="col-md-2 form-group" style="padding: 0px 0px 0px 30px">
                        <select class="form-control" id="Type" name="Type">
                            <option value="">--select--</option>
                                <c:choose>
                                    <c:when test="${type == 'V'}">
                                    <c:set var="accountSelectedV" value="selected" />
                                    </c:when>
                                     <c:when test="${type == 'N'}">
                                        <c:set var="accountSelectedN" value="selected" />
                                    </c:when>
                                     <c:when test="${type == 'T'}">
                                        <c:set var="accountSelectedT" value="selected" />
                                    </c:when>
                                     <c:when test="${type == 'A'}">
                                        <c:set var="accountSelectedA" value="selected" />
                                    </c:when>
                                </c:choose>         
       
                            <option value="V" ${accountSelectedV}>Vat</option>
                            <option value="N" ${accountSelectedN}>No Vat</option>
                            <option value="T" ${accountSelectedT}>Temp</option>
                            <option value="A" ${accountSelectedA}>Ticket</option>
                        </select>    
                    </div>
                </div>
                <div class="col-xs-12 form-group"></div>        
                <div class="col-xs-12 form-group">
                    <input type="hidden" class="form-control" id="InvToId" name="InvToId" value="" />
                    <div class="col-md-1 text-right"  style="padding-left: 15px;">
                        <label class="control-label">Agent </lable>
                    </div>
                    <div class="col-md-3 form-group text-left" style="padding-left:15px;width: 160px;"> 
                        <div class="input-group" id="gr" >
                            <input type="text" class="form-control" id="InvTo" name="InvTo" value="${agent}" />
                            <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#AgentModal">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                        </div>
                    </div>
                    <div class="col-md-2 form-group text-left" style="width: 200px;">
                        <input name="InvToName" id="InvToName" type="text" class="form-control" value="${agentName}" readonly=""/>
                    </div>
                    <div class="col-md-4 "  ></div>
                    <div class="col-md-1 text-right " style="padding: 0px 30px 0px 0px;">
                        <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="search()" class="btn btn-primary btn-primary ">
                            <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                        </button>                                          
                    </div>                   
                    <div class="col-md-1 text-right " style="padding: 0px 0px 0px 0px">
                        <button id="btnPrint" type="button" onclick="printInvoiceSummary();" class="btn btn-default">
                            <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print
                        </button>
                    </div>    
                </div>
            </form>
            <div class="col-xs-12 form-group"><hr/></div>
            <div class="row">    
                <div class="col-md-12">
                    <table id="MasterInvoice" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden" >Invoice Id</th>
                                <th style="width: 8%" >Invoice No</th>
                                <th style="width: 10%" >Invoice Date</th>
                                <th style="width: 20%" >Name</th>         
                                <th style="width: 20%">Address</th>
                                <th style="width: 10%">Total Price</th>
                                <th style="width: 5%">Currency</th>
                                <th style="width: 5%">Department</th>
                                <th style="width: 5%">Type</th>
                                <th style="width: 15%">Term Pay</th>
                                <th style="width: 2%">Action</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="inv" items="${listInvoice}" varStatus="taxdesc">
                                <tr>
                                    <td class="hidden"><input type="text"  id="inputInvoiceId${taxdesc.count}" name="inputInvoiceId" value="${inv.invoiceId}"></td>
                                    <td align="center">${inv.invoiceNo}</td>
                                    <td >${inv.invoiceDate}</td>
                                    <td >${inv.name}</td>
                                    <td >${inv.address}</td>
                                    <td align="right"><fmt:formatNumber type="number" maxFractionDigits="3" value="${inv.totalPrice}"/> </td>
                                    <td align="center">${inv.currency}</td>
                                    <td>${inv.department}
                                        <c:choose>
                                            <c:when test="${inv.department == 'Wendy'}">
                                                <c:set var="depart" value="W" />
                                            </c:when>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${inv.department == 'Outbound'}">
                                                <c:set var="depart" value="O" />
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${inv.type == 'N'}">
                                                <c:set var="typeName" value="No Vat" />
                                            </c:when>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${inv.type == 'V'}">
                                                <c:set var="typeName" value="Vat" />
                                            </c:when>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${inv.type == 'T'}">
                                                <c:set var="typeName" value="Temp" />
                                            </c:when>
                                        </c:choose>
                                        ${typeName}
                                    </td>
                                    <td>${inv.termPayName}</td>
                                    <td align="center" > 
                                        <center> 
                                        <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon" onclick="window.open('/SMITravel/Invoice${depart}${inv.type}.smi?typeInvoice=${inv.type}&departmentInvoice=${inv.department}&idInvoice=${inv.invoiceId}&action=edit');">
                                        </span>
                                        </center>
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
<div class="modal fade" id="AgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Agent</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->
                <div style="text-align: right"> <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchInvoiceFrom" name="searchInvoiceFrom"/> </div> 
                <table class="display" id="AgentTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${listAgent}">
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
<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        var table = $('#MasterInvoice').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });
        
        $('#MasterInvoice tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#MasterInvoice tbody tr.row_selected').attr("id"));
            }
        });
        
        $("#SearchInvoiceForm")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    FromDate: {
                        trigger: 'focus keyup change',
                            validators: {
                                date: {
                                    format: 'YYYY-MM-DD',
                                    max: 'ToDate',
                                    message: 'The Date From is not a valid'
                                },notEmpty: {
                                    message: 'The Date From is required'
                                }
                            }
                    },
                    ToDate: {
                        trigger: 'focus keyup change',
                            validators: {
                                date: {
                                    format: 'YYYY-MM-DD',
                                    min: 'FromDate',
                                    message: 'The Date To is not a valid'
                                },notEmpty: {
                                    message: 'The Date From is required'
                                }
                            }
                    }
                }
            }).on('success.field.fv', function (e, data) {
//                alert("1");
                if (data.field === 'FromDate' && data.fv.isValidField('ToDate') === false) {
                        data.fv.revalidateField('ToDate');
                    }

                    if (data.field === 'ToDate' && data.fv.isValidField('FromDate') === false) {
                        data.fv.revalidateField('FromDate');
                    }
            });
    $('#DateFrom').datetimepicker().on('dp.change', function (e) {
        $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'FromDate');
    });
    $('#DateTo').datetimepicker().on('dp.change', function (e) {
        $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'ToDate');
    });
    });   
</script>

<script type="text/javascript" src="js/SearchInvoice.js"></script> 