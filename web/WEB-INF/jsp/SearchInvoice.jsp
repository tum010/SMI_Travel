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
<c:set var="status" value="${requestScope['status']}" />
<c:set var="result" value="${requestScope['result']}" />
<c:set var="showtemp" value="${requestScope['showtemp']}" />
<c:set var="airticketWendy" value="${requestScope['airticketWendy']}" />
<section class="content-header" >
    <h1>
        Finance & Cashier - Search Invoice
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Search Invoice</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app="">
    <form action="SearchInvoice.smi" method="post" id="SearchInvoiceForm" name="SearchInvoiceForm" role="form">
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/InvoiceMenu.html'"></div>
        </div>
        <div class="col-sm-10">
<!--            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Search Invoice</b></h4>
                </div>            
            </div>-->
            
                <input type="text" class="hidden" id="action" name="action" value="">
                <div class="sm_row col-xs-12 " style="padding-top: 25px">
                    <div class="col-xs-1 text-right">
                        <label class="control-label" for="">From<font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-2 form-group"> 
                        <div class='input-group date fromdate' id="DateFrom">
                            <c:if test='${fromdate != null}'>
                                <c:set var="FromDate" value="${fromdate}" />
                                <fmt:parseDate value="${FromDate}" var="FromDate" pattern="yyyy-MM-dd" />
                                <fmt:formatDate value="${FromDate}" var="FromDate" pattern="dd-MM-yyyy" />
                                <input id="FromDate" name="FromDate"  type="text" 
                                   class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${FromDate}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>
                            <c:if test='${fromdate == null}'>
                                <c:set var="FromDate" value="${requestScope['']}" />
                                <fmt:parseDate value="${FromDate}" var="FromDate" pattern="yyyy-MM-dd" />
                                <fmt:formatDate value="${FromDate}" var="FromDate" pattern="dd-MM-yyyy" />
                                <input id="FromDate" name="FromDate"  type="text" 
                                   class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="FromDate">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>                             
                        </div>
                    </div>
                    <div class="col-xs-1 text-left" style="padding: 0px 0px 0px 10px;width: 33px">
                        <label class="control-label" for="">To<font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-2 form-group" style="padding: 0px 0px 0px 20px; width: 160px"> 
                        <div class='input-group date todate' id="DateTo">
                            <c:if test='${todate != null}'>
                                <c:set var="ToDate" value="${todate}" />
                                <fmt:parseDate value="${ToDate}" var="ToDate" pattern="yyyy-MM-dd" />
                                <fmt:formatDate value="${ToDate}" var="ToDate" pattern="dd-MM-yyyy" />
                                <input id="ToDate" name="ToDate"  type="text" 
                                   class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${ToDate}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>
                            <c:if test='${todate == null}'>
                                <c:set var="ToDate" value="${requestScope['']}" />
                                <fmt:parseDate value="${ToDate}" var="ToDate" pattern="yyyy-MM-dd" />
                                <fmt:formatDate value="${ToDate}" var="ToDate" pattern="dd-MM-yyyy" />
                                <input id="ToDate" name="ToDate"  type="text" 
                                   class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${ToDate}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>                             
                        </div>
                    </div>
                    <div class="col-xs-1 text-right" style="padding: 0px 0px 0px 20px">
                        <label class="control-label" for="">Department</lable>
                    </div>
                    <div class="col-md-1 form-group" style="padding: 0px 0px 0px 30px;width: 125px">
                        <select class="form-control" id="Department" name="Department">
                            <option value="">--select--</option>
                                <c:set var="selectDepartW" value="" />
                                <c:set var="selectDepartO" value="" />
                                <c:set var="selectDepartI" value="" />
                                <c:set var="selectDepartWO" value="" />
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
                                <c:choose>
                                    <c:when test="${department == 'WendyOutbound'}">
                                        <c:set var="selectDepartWO" value="selected" />
                                    </c:when>
                                </c:choose>
                            <option value="Wendy" ${selectDepartW}>Wendy</option>
                            <option value="Outbound" ${selectDepartO}>Outbound</option>
                            <option value="Inbound" ${selectDepartI}>Inbound</option>
                            <option value="WendyOutbound" ${selectDepartWO}>Wendy + Outbound</option>
                        </select>    
                    </div>
                    <div class="col-xs-1 text-left" style="padding: 0px 0px 0px 25px; width: 140px">
                        <label class="control-label" for="">Type</lable>
                    </div>
                    <div class="col-md-1 form-group" style="padding: 0px 0px 0px 30px; width: 125px" id="classHideTemp" >
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
                            <option value="A" ${accountSelectedA}>Ticket</option>
                        </select>    
                    </div>
                    <div class="col-md-2 form-group" style="padding: 0px 0px 0px 30px; width: 125px" id="classShowTemp" hidden="">
<!--                        <select class="form-control" id="Type" name="Type">   
                            <option value="T" ${accountSelectedT}>Temp</option>
                        </select>    -->
                    </div>
                </div>
                <!--<div class="sm_row col-xs-12 form-group"></div>-->        
                <div class="sm_row col-xs-12 form-group">
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
                    <div class="col-md-2 form-group text-left" style="width: 215px;">
                        <input name="InvToName" id="InvToName" type="text" class="form-control" value="${agentName}" readonly=""/>
                    </div>
                    <div class="col-xs-1 text-left" style="padding: 0px 0px 0px 5px">
                        <label class="control-label" for="">Status</lable>
                    </div>
                    <div class="col-md-1 form-group" style="padding: 0px 0px 0px 15px;width: 110px">
                        <select class="form-control" id="status" name="status">
                            <option value="">--choose--</option>
                            <c:set var="statusN" value="" />
                            <c:set var="statusV" value="" />
                                <c:choose>
                                    <c:when test="${status == '1'}">
                                    <c:set var="statusN" value="selected" />
                                    </c:when>
                                     <c:when test="${status == '2'}">
                                        <c:set var="statusV" value="selected" />
                                    </c:when>                                  
                                </c:choose>>     
                            <option value="1" ${statusN}>Normal</option>
                            <option value="2" ${statusV}>Void</option>
                        </select>    
                    </div>
                    <div class="col-xs-1 text-right" style="padding: 0px 0px 0px 20px;width: 140px">
                        <label class="control-label" for="">Air Ticket Wendy</lable>
                    </div>
                    <div class="col-md-1 form-group" style="padding: 0px 0px 0px 30px;width: 125px">
                        <select class="form-control" id="airticketWendy" name="airticketWendy">
                            <option value="">--choose--</option>
                            <c:set var="a" value="" />
                            <c:set var="p" value="" />
                                <c:choose>
                                    <c:when test="${airticketWendy == 'airticket'}">
                                    <c:set var="a" value="selected" />
                                    </c:when>
                                     <c:when test="${airticketWendy == 'package'}">
                                        <c:set var="p" value="selected" />
                                    </c:when>                                  
                                </c:choose>>     
                            <option value="airticket" ${a}>Air Ticket</option>
                            <option value="package" ${p}>Package</option>
                        </select>    
                    </div>    
                    <div class="col-md-1 "  ></div>                   
                </div>
                <div class="sm_row col-xs-12 form-group">
                    <div class="col-md-9 text-right "></div>
                    <div class="sm_row col-md-1 text-right " style="padding: 0px 30px 0px 0px;">
                        <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="search()" class="btn btn-primary btn-primary ">
                            <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                        </button>                                          
                    </div>                   
                    <div class="sm_row col-md-1 text-right " style="padding: 0px 0px 0px 0px">
                        <button id="btnPrint" type="button" onclick="printInvoiceSummary();" class="btn btn-default">
                            <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print
                        </button>
                    </div>           
                </div>
            <!--<div class="sm_row col-xs-12 form-group"><hr/></div>-->
            <div class="sm_row col-xs-12 form-group"><hr/></div>
            <div class="sm_row row">    
                <div class="sm_row col-md-12">
                    <table id="MasterInvoice" class="display" cellspacing="0" width="100%" style="table-layout: fixed">
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden" >Invoice Id</th>
                                <th style="width: 9%" >Invoice No</th>
                                <th style="width: 9%" >Invoice Date</th>
                                <th style="width: 15%" >Name</th>         
                                <th style="width: 17%">Address</th>
                                <th style="width: 11%">Total Price</th>
                                <th style="width: 8%">Currency</th>
                                <th style="width: 10%">Department</th>
                                <th style="width: 6%">Type</th>
                                <th style="width: 8%">Term Pay</th>
                                <th style="width: 7%">Action</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="inv" items="${listInvoice}" varStatus="taxdesc">
                                <tr>
                                    <td class="hidden"><input type="text"  id="inputInvoiceId${taxdesc.count}" name="inputInvoiceId" value="${inv.invoiceId}"></td>
                                    <td align="center">${inv.invoiceNo}</td>
                                    <td>
                                        <c:set var="invoiceDate" value="${inv.invoiceDate}" />
                                        <fmt:parseDate value="${invoiceDate}" var="invoiceDate" pattern="yyyy-MM-dd" />
                                        <fmt:formatDate value="${invoiceDate}" var="invoiceDate" pattern="dd-MM-yyyy" />
                                        ${invoiceDate}
                                    </td>
                                    <td >${inv.name}</td>
                                    <td >${inv.address}</td>
<!--                                    <td align="right" class="money"><fmt:formatNumber type="number" maxFractionDigits="3" value="${inv.totalPrice}"/> </td>-->
                                    <td align="right" class="money">${inv.totalPrice}</td>
                                    <td align="center">${inv.currency}</td>
                                    <td>${inv.department}
                                        <c:set var="depart" value="" />
                                        <c:choose>
                                            <c:when test="${inv.department == 'Wendy'}">
                                                <c:set var="depart" value="W" />
                                            </c:when>
                                            <c:when test="${inv.department == 'Outbound'}">
                                                <c:set var="depart" value="O" />
                                            </c:when>
                                            <c:when test="${inv.department == 'Inbound'}">
                                                <c:set var="depart" value="Inbound" />
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:set var="typeName" value="" />
                                        <c:choose>
                                            <c:when test="${inv.type == 'N'}">
                                                <c:set var="typeName" value="No Vat" />
                                            </c:when>
                                            <c:when test="${inv.type == 'V'}">
                                                <c:set var="typeName" value="Vat" />
                                            </c:when>
                                            <c:when test="${inv.type == 'T'}">
                                                <c:set var="typeName" value="Temp" />
                                            </c:when>
                                            <c:when test="${inv.type == 'A'}">
                                                <c:set var="typeName" value="Ticket" />
                                            </c:when>
                                        </c:choose>    
                                        ${typeName}
                                    </td>
                                    <td>${inv.termPayName}</td>
                                    <td align="center" >
                                        <c:set var="type" value="" />
                                        <c:choose>
                                            <c:when test="${inv.department == 'Wendy' || inv.department == 'Outbound'}">
                                                <c:choose>
                                                    <c:when test="${inv.type == 'N'}">
                                                        <c:set var="type" value="N" />
                                                    </c:when>
                                                    <c:when test="${inv.type == 'V'}">
                                                        <c:set var="type" value="V" />
                                                    </c:when>
                                                    <c:when test="${inv.type == 'T'}">
                                                        <c:set var="type" value="T" />
                                                    </c:when>
                                                    <c:when test="${inv.type == 'A'}">
                                                        <c:set var="type" value="A" />
                                                    </c:when>
                                                </c:choose>
                                            </c:when>
                                            <c:when test="${inv.department == 'Inbound'}">
                                                <c:choose>
                                                    <c:when test="${inv.type == 'V'}">
                                                        <c:set var="type" value="RV" />
                                                    </c:when>
                                                    <c:when test="${inv.type == 'T'}">
                                                        <c:set var="type" value="PM" />
                                                    </c:when>
                                                </c:choose>
                                            </c:when>                                         
                                        </c:choose>    
                                        <center> 
                                        <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon" onclick="window.open('/SMITravel/Invoice${depart}${type}.smi?typeInvoice=${inv.type}&departmentInvoice=${inv.department}&idInvoice=${inv.invoiceId}&action=edit');">
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
    </form>
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
                <div style="text-align: right"> <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchInvoiceFrom" name="searchInvoiceFrom" placeholder ="LAST/FIRST"/> </div> 
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
        var typetemp = '${requestScope['type']}' ;
        $('.datemask').mask('00-00-0000');
        if(typetemp === 'T'){
            $('#Type')
                .find('option')
                .remove()
                .end()
                .append('<option value="T">Temp</option>')
                .val('')
            ;
            $('[name=Type] option').filter(function() { 
                return ($(this).val() === 'T');
            }).prop('selected', true);  
        }
        
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
                                    format: 'DD-MM-YYYY',
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
                                    format: 'DD-MM-YYYY',
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