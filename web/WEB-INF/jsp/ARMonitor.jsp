<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="listType" value="${requestScope['listType']}" />
<c:set var="listAr" value="${requestScope['listAr']}" />
<c:set var="invoiceType" value="${requestScope['invoiceType']}" />
<c:set var="departmnt" value="${requestScope['departmnt']}" />
<c:set var="type" value="${requestScope['type']}" />
<c:set var="from" value="${requestScope['from']}" />
<c:set var="to" value="${requestScope['to']}" />
<c:set var="status" value="${requestScope['status']}" />
<c:set var="status_update" value="${requestScope['status_update']}" />
<input type="hidden" id="statusUpdate" name="statusUpdate" value="${status_update}">
<section class="content-header" >
    <h1>
        Nirvana Interface
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Nirvana Interface</a></li>          
        <li class="active"><a href="#"></a>AR Monitor</li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app="">
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px;width: 150px">
        <div ng-include="'WebContent/Accounting/NirvanaInterfaceMenu.html'"></div>
    </div>
    <div class="col-sm-10">
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6 " style="padding-right: 15px">
                <h4><b>AR Monitor</b></h4>                  
            </div>
            <div class="col-xs-12 form-group"><hr/></div>
        </div>
        <form action="ARMonitor.smi" method="post" id="arMonitorForm" role="form" autocomplete="off">
            <input type="hidden" value="searchAr" id="action" name="action">
            <input type="hidden" value="" id="page" name="page">
            <input type="hidden" value="" id="currentPage" name="currentPage">
            <input type="hidden" value="" id="row" name="row">
            <div class="col-xs-12" style="margin-top: -17px">
                <c:if test="${requestScope['update'] =='success'}">                                            
                    <div id="textAlertDivSave"  style="" class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Update Status Success!</strong> 
                    </div>
                </c:if>
                <c:if test="${requestScope['update'] =='fail'}">
                <div id="textAlertDivSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <strong>Update Status Unsuccess!</strong> 
                </div>
                </c:if>
                <c:if test="${requestScope['update'] == 'cannotconnect'}">
                <div id="textAlertDivSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
                   <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <strong>Can't Connect to Sybase !</strong> 
                </div>
                </c:if>
                <div id="textAlertDivNotChoose"  style="display: none" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" aria-label="Close" onclick="hideDiv()"><span aria-hidden="true">&times;</span></button>
                   <strong>Please choose the ar monitor list.!</strong> 
                </div>
                <div class="col-xs-1 text-left" style="width: 120px">
                    <label class="control-label" for="">Invoice Type</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <c:set var="selectInvoiceTypeVat" value="" />
                    <c:set var="selectInvoiceTypeNoVat" value="" />
                    <c:set var="selectInvoiceTypeTemp" value="" />
                    <c:set var="selectInvoiceTypeTic" value="" />
                    <c:set var="selectInvoiceTypeTax" value="" />
                    <c:if test="${invoiceType == 'V'}">
                        <c:set var="selectInvoiceTypeVat" value="selected" />
                    </c:if>
                    <c:if test="${invoiceType == 'N'}">
                        <c:set var="selectInvoiceTypeNoVat" value="selected" />
                    </c:if>
                    <c:if test="${invoiceType == 'T'}">
                        <c:set var="selectInvoiceTypeTemp" value="selected" />
                    </c:if>
                    <c:if test="${invoiceType == 'A'}">
                        <c:set var="selectInvoiceTypeTic" value="selected" />
                    </c:if>
                    <c:if test="${invoiceType == 'TAX'}">
                        <c:set var="selectInvoiceTypeTax" value="selected" />
                    </c:if>
                    <select class="form-control" id="invoiceType" name="invoiceType">
                        <option value="">--Select--</option>
                        <option value="V" ${selectInvoiceTypeVat}>Vat </option>
                        <option value="N" ${selectInvoiceTypeNoVat}>No Vat </option>
                        <option value="T" ${selectInvoiceTypeTemp}>Temp </option>
                        <option value="A" ${selectInvoiceTypeTic}>Ticket </option>
                        <option value="TAX" ${selectInvoiceTypeTax}>Tax </option>
                    </select>
                </div>
                <!--<div class="col-xs-1" style="width: 50px"></div>-->
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">Department </lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <c:set var="selectDepartWendy" value="" />
                    <c:set var="selectDepartOutbound" value="" />
                    <c:set var="selectDepartInbound" value="" />
                    <c:if test="${departmnt == 'Wendy'}">
                        <c:set var="selectDepartWendy" value="selected" />
                    </c:if>
                    <c:if test="${departmnt == 'Outbound'}">
                        <c:set var="selectDepartOutbound" value="selected" />
                    </c:if>
                    <c:if test="${departmnt == 'Inbound'}">
                        <c:set var="selectDepartInbound" value="selected" />
                    </c:if>
                    <select class="form-control" id="department" name="department">
                        <option value="">--Select--</option>
                        <option value="Wendy" ${selectDepartWendy}>Wendy </option>
                        <option value="Outbound" ${selectDepartOutbound}>Outbound </option>
                        <option value="Inbound" ${selectDepartInbound}>Inbound </option>
                    </select>
                </div>
                <div class="col-xs-1" style="width: 50px"></div>
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">Type</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <select class="form-control" id="arType" name="arType">
                        <option value="">--Select--</option>
                        <c:forEach var="type" items="${listType}" varStatus="count">
                            <c:set var="selectARtype" value="" />
                            <c:if test="${type == type.id}">
                                <c:set var="selectARtype" value="selected" />
                            </c:if>
                            <option value="${type.id}" ${selectARtype}>${type.name} </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12" style="margin-top: -13px"> <!--Row 2 -->
                <div class="col-xs-1 text-left" style="width: 120px">
                    <label class="control-label" for="">From<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1 form-group" style="width: 200px">
                    <div class='input-group date' id='InputFromDate'>
                    <c:if test='${from != null}'>
                        <c:set var="arFromDate" value="${from}" />
                        <fmt:parseDate value="${arFromDate}" var="arFromDate" pattern="yyyy-MM-dd" />
                        <fmt:formatDate value="${arFromDate}" var="arFromDate" pattern="dd-MM-yyyy" />
                        <input id="arFromDate" name="arFromDate"  type="text" 
                            class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${arFromDate}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${from == null}'>
                        <input id="arFromDate" name="arFromDate"  type="text" 
                            class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>  
                </div>
                <!--<div class="col-xs-1" style="width: 50px"></div>-->
                <div class="col-xs-1 text-left">
                    <label class="control-label">To<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1 form-group" style="width: 200px">
                    <div class='input-group date' id='InputToDate'>
                    <c:if test='${to != null}'>
                        <c:set var="arToDate" value="${to}" />
                        <fmt:parseDate value="${arToDate}" var="arToDate" pattern="yyyy-MM-dd" />
                        <fmt:formatDate value="${arToDate}" var="arToDate" pattern="dd-MM-yyyy" />
                        <input id="arToDate" name="arToDate"  type="text" 
                            class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${arToDate}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${to == null}'>
                        <input id="arToDate" name="arToDate"  type="text" 
                            class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>   
                </div>
                <div class="col-xs-1" style="width: 50px"></div>
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">Status</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <c:set var="selectNew" value="" />
                    <c:set var="selectExport" value="" />
                    <c:set var="selectChange" value="" />
                    <c:if test="${status == 'New'}">
                        <c:set var="selectNew" value="selected" />
                    </c:if>
                    <c:if test="${status == 'Export'}">
                        <c:set var="selectExport" value="selected" />
                    </c:if>
                    <c:if test="${status == 'Change'}">
                        <c:set var="selectChange" value="selected" />
                    </c:if>
                    <select class="form-control" id="arStatus" name="arStatus">
                        <option value="">--Select--</option>
                        <option value="New" ${selectNew}>New </option>
                        <option value="Export" ${selectExport}>Export </option>
                        <option value="Change" ${selectChange}>Change </option>
                    </select>
                </div>
            </div>
            <div class="col-xs-12" style="margin-top: -7px"> 
                <div class="col-xs-1 text-left" style="width: 120px">
                    <label class="control-label" for="">Acc</lable>
                </div>
                <div class="col-xs-1" style="width: 100px">
                    <select class="form-control" id="accno" name="accno">
                        <option value=""> </option>
                        <c:set var="accno1" value="" />
                        <c:if test="${'1' == requestScope['accno']}">
                            <c:set var="accno1" value="selected" />
                        </c:if>
                        <option value="1" ${accno1}>1</option>
                        <c:set var="accno2" value="" />
                        <c:if test="${'2' == requestScope['accno']}">
                            <c:set var="accno2" value="selected" />
                        </c:if>
                        <option value="2" ${accno2}>2</option>
                    </select>
                </div>
                <div class="col-xs-1 text-right" style="width: 715px">
                    <a id="ButtonStatus" class="btn btn-primary" data-toggle="modal" data-target="#UpdateStatusModal"  style="height: 34px"><i class="glyphicon glyphicon-th-list"></i>&nbsp;Status Export</a>&nbsp;
                    <button type="submit"  id="btnSearchAR"  name="btnSearchAR"  onclick="searchArmonitor()"  class="btn btn-primary btn-primary">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>
                </div>        
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
<!--                <div class="col-xs-10"></div>
                <div class="col-xs-1">
                    <button type="submit"  id="btnSearchAR"  name="btnSearchAR"  onclick="searchArmonitor()"  class="btn btn-primary btn-primary">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>
                </div>-->
                <div class="col-xs-12"><br></div>  
                <div class="col-xs-12" style="margin-top: -40px">
                    <input type="hidden" id="apCount" name="arCount" value="${listAr.size()}"/>
                    <table id="arDataListTable" class="display paginated" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden">Id</th>
                                <th style="width: 1%" onclick="selectAll()"><u>All</u></th>
                                <th style="width: 1%" >No</th>
                                <th style="width: 10%">Inv No.</th>
                                <th style="width: 9%">AR Code</th>
                                <th style="width: 26%">Inv To</th>
                                <th style="width: 12%">Acc Code</th>
                                <th style="width: 15%">Gross</th>
                                <th style="width: 7%">Vat</th>
                                <th style="width: 15%">Amount</th>
                                <th style="width: 2%">Cur</th>
                                <th style="width: 4%">Status</th>
                             </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ar_nirvana" items="${listAr}" varStatus="countar">
                            <tr>
                                <td class="hidden"><input class="form-control" type="text" id="inputId${countar.count}" name="inputId${countar.count}" value="${ar_nirvana.rowid}"></td>
                                <td align="center">
                                    <c:choose>
                                        <c:when test="${ar_nirvana.status == 'New'}">
                                            <input type="checkbox" class="form-control" id="selectAll${countar.count}" name="selectAll${countar.count}" value="${countar.count}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" class="form-control" id="selectAll" name="selectAll" value="" disabled=""/>
                                        </c:otherwise>
                                    </c:choose>                                  
                                </td>
                                <td align="center">${countar.count}</td>
                                <td>${ar_nirvana.intreference}</td>
                                <td>${ar_nirvana.customerid}</td>
                                <td>${ar_nirvana.customername}</td>
                                <td>${ar_nirvana.salesaccount1}</td>
                                <td align="right" class="money">${ar_nirvana.salesamt}</td>
                                <td align="right" class="money">${ar_nirvana.vatamt}</td>
                                <td align="right" class="money">${ar_nirvana.arhmamt}</td>
                                <td align="center">${ar_nirvana.currencyid}</td>
                                <td align="center">${ar_nirvana.status}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>    
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-1 text-right" style="width: 665px"></div>
                <div class="col-xs-1 text-right" style="width: 210px">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="changeARReport()">
                        <span id="btnDownloadAP" class="glyphicon glyphicon-print" ></span> Print Change AR Report
                    </button>
                </div>
                <div class="col-xs-1 text-right" style="">
                    <button type="button" class="btn btn-success btn-default" onclick="exportAR()"  data-dismiss="modal">
                        <span id="btnExportAP" class="glyphicon glyphicon-export" ></span> Export
                    </button>
                </div>    
            </div>
            <div class="col-xs-12"><br></div>
        </form>
    </div>
</div> 

<!--Export AR Modal-->
<div class="modal fade" id="arExportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Export AR</h4>
            </div>
            <div class="modal-body" id="copyReceiptModal" >
                <label class="text-right">Are you sure to export ar to nirvana ?</label>
                <input type="hidden" id="chooseAR" name="chooseAR" value=""/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="confirmExport()">
                    <span id="btnConfirmExport" class="glyphicon" ></span> Ok
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<!-- MODAL-->
<div class="modal fade" id="UpdateStatusModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Status Export AP</h4>
            </div>
            <!--<div class="modal-body" id="statusUpdateAlert">-->
            <div class="modal-body">
                <table class="display" id="StatusExportTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width:10%;">Interference</th>
                            <th style="width:15%;">Comment</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <!--<button type="button" onclick="selectTicketNo()" class="btn btn-danger">OK</button>-->
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>               
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<script language="javascript">
    $(document).ready(function () {
        setAlertModalUpdateDetail();
        $('.date').datetimepicker();
        $('.datemask').mask('00-00-0000');
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        $(".money").mask('000,000,000.00', {reverse: true});
        
        var table = $('#arDataListTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false,
            "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
            "iDisplayLength": 50,
            "bSort": false,
            "bPaginate": false
        });
        
        $('#arDataListTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#arDataListTable tbody tr.row_selected').attr("id"));
            }
        });
        
        $('#InputFromDate').datetimepicker().on('dp.change', function (e) {
            $('#arMonitorForm').bootstrapValidator('revalidateField', 'arFromDate');
        });
        $('#InputToDate').datetimepicker().on('dp.change', function (e) {
            $('#arMonitorForm').bootstrapValidator('revalidateField', 'arToDate');
        });
        
        $("#arMonitorForm").bootstrapValidator({
                    framework: 'bootstrap',
    //                container: 'tooltip',
                    feedbackIcons: {
                        valid: 'uk-icon-check',
                        invalid: 'uk-icon-times',
                        validating: 'uk-icon-refresh'
                    },
                    fields: {
                        arFromDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date From is required'
                                },
                                date: {
                                    format: 'DD-MM-YYYY',
                                    max: 'arToDate',
                                    message: 'The Date From is not a valid'
                                }
                            }
                        },
                        arToDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date From is required'
                                },
                                date: {
                                    format: 'DD-MM-YYYY',
                                    min: 'arFromDate',
                                    message: 'The Date To is not a valid'
                                }
                            }
                        }
                    }
                }).on('success.field.fv', function (e, data) {
            if (data.field === 'arFromDate' && data.fv.isValidField('arToDate') === false) {
                data.fv.revalidateField('arToDate');
            }

            if (data.field === 'arToDate' && data.fv.isValidField('arFromDate') === false) {
                data.fv.revalidateField('arFromDate');
            }
        });
        
        $('table.paginated').each(function() {
            var currentPage = 0;
            var numPerPage = 50;
            var $table = $(this);
            $table.bind('repaginate', function() {
                $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
            });
            $table.trigger('repaginate');
            var numRows = $table.find('tbody tr').length;
            var numPages = Math.ceil(numRows / numPerPage);
            var $pager = $('<div class="col-xs-12 text-right" id="pageNo"><font style="color: #499DD5"></font>&nbsp;</div>');
            var $br = $('<div class="col-xs-12"><br></div>');
            
            for (var page = 0; page < numPages; page++) {
                var isShowPage = (page < 5 ? "" : "hidden");
                if(page === 0){
                    $('<font style="color: #499DD5" id="noFirst" onclick="changeColor(\'noFirst\',\'first\',\''+page+'\')"><span class="page-number glyphicon"></span></font>').text(" " + "First" + "  ").bind('click', {
                    newPage: page
                    }, function(event) {
                        currentPage = event.data['newPage'];
                        $table.trigger('repaginate');
                        $(this).addClass('active').siblings().removeClass('active');
                        $(this).css("color", "#AFEEEE");
                    }).appendTo($pager).addClass('clickable');                                      
                    
                    if(numPages > 1){
                        for(var i=0; i<numPages; i++){
                            var isHidden = (i === 0 ? "" : "hidden");
                            $('<font style="color: #499DD5" id="noPrevious'+i+'" onclick="changeColor(\'noPrevious'+i+'\',\'previous\',\''+i+'\')" class="'+isHidden+'"><span class="page-number glyphicon"></span></font>').text(" " + "Previous" + "  ").bind('click', {
                            newPage: i
                            }, function(event) {
                                currentPage = event.data['newPage'];
                                $table.trigger('repaginate');
                                $(this).addClass('active').siblings().removeClass('active');
        //                        $(this).css("color", "#AFEEEE");
                            }).appendTo($pager).addClass('clickable');
                        }
                    }    
                }
                
                $('<font style="color: #499DD5" id="no' + page + '" onclick="changeColor(\'no'+page+'\',\'no\',\''+page+'\')" class="'+isShowPage+'"><span class="page-number glyphicon"></span></font>').text(" " + (page + 1) + "  ").bind('click', {
                    newPage: page
                }, function(event) {                  
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                    $(this).css("color", "#AFEEEE");
                }).appendTo($pager).addClass('clickable');
                
                if(page === (numPages - 1)){
                    if(numPages > 1){
                        for(var i=0; i<numPages; i++){
                            var isHidden = (i === 1 ? "" : "hidden");
                            $('<font style="color: #499DD5" id="noNext'+i+'" onclick="changeColor(\'noNext'+i+'\',\'next\',\''+i+'\')" class="'+isHidden+'"><span class="page-number glyphicon"></span></font>').text(" " + "Next" + "  ").bind('click', {
                            newPage: i
                            }, function(event) {
                                currentPage = event.data['newPage'];
                                $table.trigger('repaginate');
                                $(this).addClass('active').siblings().removeClass('active');
        //                        $(this).css("color", "#AFEEEE");
                            }).appendTo($pager).addClass('clickable');
                        }
                    }    
                                       
                    $('<font style="color: #499DD5" id="noLast" onclick="changeColor(\'noLast\',\'last\',\''+page+'\')"><span class="page-number glyphicon"></span></font>').text(" " + "Last" + "  ").bind('click', {
                    newPage: page
                    }, function(event) {
                        currentPage = event.data['newPage'];
                        $table.trigger('repaginate');
                        $(this).addClass('active').siblings().removeClass('active');
                        $(this).css("color", "#AFEEEE");
                    }).appendTo($pager).addClass('clickable');                                     
                }
            }
            $br.insertAfter($table).addClass('active');
            $pager.insertAfter($table).find('span.page-number:first').addClass('active');
            document.getElementById("pageNo").style.cursor="pointer";
            document.getElementById("page").value = numPages-1;
            document.getElementById("currentPage").value = 0;
            $("#noFirst").css("color", "#AFEEEE");
            $("#no0").css("color", "#AFEEEE");
            $("#noPrevious0").css("color", "#AFEEEE");
        });
               
    });
    
    function getPageNo(type){
        var page = parseInt($("#page").val())+1;
        var currentPage = parseInt($("#currentPage").val());
        var row = 0;
        var start = 0;
        var end = page-1;
        if(type === 'previous'){
            row = (currentPage !== start ? currentPage-1 : start);
        
        }else if(type === 'next'){
            row = (currentPage !== end ? currentPage+1 : end);
        }
        $("#row").val(row);
        return row;
    }
    
    function changeColor(id,type,page){
        var pageNo = parseInt($("#page").val())+1;
        for(var i=0; i<pageNo; i++){
            $("#no"+i).css("color", "#499DD5");
            $("#noPrevious"+i).css("color", "#499DD5");
            $("#noNext"+i).css("color", "#499DD5");
            $("#noFirst").css("color", "#499DD5");                
            $("#noLast").css("color", "#499DD5");
            
            $("#no"+i).addClass("hidden");
            $("#noPrevious"+i).addClass("hidden");
            $("#noNext"+i).addClass("hidden");
        }
        
        var pageShow = parseInt(page);
        if(pageShow > 2 && pageShow < pageNo - 2){
            for(var i=pageShow; i >= pageShow-2; i--){
               $("#no"+i).removeClass("hidden"); 
            }
            for(var i=pageShow; i <= pageShow+2; i++){
               $("#no"+i).removeClass("hidden");  
            }
        
        }else{
            if(pageShow <= 2){
                for(var i=0; i < 5; i++){
                    $("#no"+i).removeClass("hidden");  
                } 
            
            }else if(pageShow <= pageNo-1){
                for(var i=pageNo-5; i < pageNo; i++){
                    $("#no"+i).removeClass("hidden");  
                } 
            }
            
        }    
        
        var previous = ((parseInt(page) === 0 ? 0 : parseInt(page)-1));
        $("#noPrevious"+(previous)).removeClass("hidden");

        var next = ((parseInt(page) === pageNo-1 ? pageNo-1 : parseInt(page)+1));
        $("#noNext"+(next)).removeClass("hidden");
        
        $("#no"+page).css("color", "#AFEEEE");
        
        if(parseInt(page) === 0){
            $("#noFirst").css("color", "#AFEEEE");
            $("#noPrevious"+(previous)).css("color", "#AFEEEE");
        
        }else if(parseInt(page) === pageNo-1){
            $("#noLast").css("color", "#AFEEEE");
            $("#noNext"+(next)).css("color", "#AFEEEE");
        }
        
        if(pageNo-1 === 0){
            $("#noFirst").css("color", "#499DD5");                
            $("#noLast").css("color", "#499DD5");
            
            if(type === 'first'){
                $("#noFirst").css("color", "#AFEEEE");
                
            }else if(type === 'last'){
                $("#noLast").css("color", "#AFEEEE");
                
            }
        }
    
        $("#currentPage").val(page);
             
    }
    
    function exportAR(){
        var row = $('#arDataListTable tr').length;     
        var check = 0;
        for(var i=1;i<=row;i++){          
            var selectAll = document.getElementById("selectAll"+i);
            if(selectAll !== null && selectAll !== ''){
                if(document.getElementById("selectAll"+i).checked){
                    check++;
                }    
            }   
        }
        if(check === 0){
            document.getElementById("chooseAR").value = "false";
        } else {
            document.getElementById("chooseAR").value = "true";
        }
        $("#textAlertDivSave").hide();
        $('#textAlertDivNotChoose').hide();
        $("#arExportModal").modal("show");
    }
    
    function changeARReport(){
        var invoiceType = $('#invoiceType').val();
        var department = $('#department').val();
        var arType = $('#arType').val();
        var arStatus  = $('#arStatus').val();
        var arFromDate = $('#arFromDate').val();
        var arToDate = $('#arToDate').val();
        if((arFromDate === '') || (arToDate === '')){
            validateDate();
        } else {
            window.open("Excel.smi?name=ChangeARReport&invoiceType="+invoiceType+"&department="+department+"&arType="+arType+"&arStatus="+arStatus+"&arFromDate="+arFromDate+"&arToDate="+arToDate); 
        }   
    }
    
    function validateDate(date,option){
        if(option === 'over'){
            if(date === 'from'){
                $('#arMonitorForm').bootstrapValidator('revalidateField', 'arFromDate');
                $('#arMonitorForm').bootstrapValidator('revalidateField', 'arToDate');
            }
            if(date === 'to'){
                $('#arMonitorForm').bootstrapValidator('revalidateField', 'arFromDate');
                $('#arMonitorForm').bootstrapValidator('revalidateField', 'arToDate');
            }           
            $("#btnDownloadAP").addClass("disabled");
        } else {
            $('#arMonitorForm').bootstrapValidator('revalidateField', 'arFromDate');
            $('#arMonitorForm').bootstrapValidator('revalidateField', 'arToDate');
            $("#btnDownloadAP").addClass("disabled");
        }
    }
    
    function confirmExport(){
//        alert("EXX");       
        $("#arExportModal").modal("hide");
        var chooseAR = document.getElementById("chooseAR").value;
        if(chooseAR === 'true'){
//            var action = $('#action').val();
            $('#action').val('export');
            document.getElementById('arMonitorForm').submit();
        } else {
            $('#textAlertDivNotChoose').show();
        }    
    }
    
    function hideDiv(){
        $('#textAlertDivNotChoose').hide();
    }
    
    function searchArmonitor(){
        var action = $('#action').val();
        action.value = 'searchAr';
//        document.getElementById('arMonitorForm').submit();
    }
    function selectAll(){
        var row = $('#arDataListTable tr').length;     
        var check = 0;
        var unCheck = 0;
        for(var i=1;i<row;i++){          
            var selectAll = document.getElementById("selectAll"+i);
            if(selectAll !== null && selectAll !== ''){
                if(document.getElementById("selectAll"+i).checked){
                    check++;
                } else {
                    unCheck++;
                }
            }   
        }
//        alert("Check : " + check + "  Un : " + unCheck + " Row : " + row);
        if(check > unCheck){
//            alert("1");
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    if(document.getElementById("selectAll"+i).checked){

                    } else { 
                        document.getElementById("selectAll"+i).checked = true;                   
                    }    
                }   
            }
        }

        if(check < unCheck){
//            alert("2");
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    document.getElementById("selectAll"+i).checked = false;
                }   
            }
        }

        if(check === 0 && unCheck !== 0){
//            alert("3");
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    if(document.getElementById("selectAll"+i).checked){

                    } else { 
                        document.getElementById("selectAll"+i).checked = true;

                    }
                }    
            }    
        }

        if(check !== 0 && unCheck === 0){
//            alert("4");
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    document.getElementById("selectAll"+i).checked = false;
                }   
            }
        }

        if(check === unCheck){
//            alert("5");
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    if(document.getElementById("selectAll"+i).checked){

                    } else { 
                        document.getElementById("selectAll"+i).checked = true;                        
                    }    
                }             
            }            
        }
    }
    
    function setAlertModalUpdateDetail(){
        var statusUpdate = $("#statusUpdate").val();
        if(statusUpdate != ""){
            statusUpdate = statusUpdate.substring(1);
            var status = statusUpdate.split(",");
            for(var i = 0 ; i < status.length ; i++){
                var detail = status[i].split("||");
                $("#StatusExportTable tbody").append(
                    '<tr style="higth 100px">' +
                    '<td class="text-left">' + detail[0] + '</td>' +
                    '<td class="text-left">' + detail[1] + '</td>' +
                    '</tr>'
                );
            }
            $('#UpdateStatusModal').modal('show');
        }
    } 
</script>
