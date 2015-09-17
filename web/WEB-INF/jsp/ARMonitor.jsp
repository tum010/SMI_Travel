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
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
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
            <div class="col-xs-12">
                <c:if test="${requestScope['update'] =='updatesuccess'}">                                            
                    <div id="textAlertDivSave"  style="" class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Update Status Success!</strong> 
                    </div>
                </c:if>
                <c:if test="${requestScope['update'] =='updatefail'}">
                <div id="textAlertDivSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <strong>Update Status Unsuccess!</strong> 
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
                    <c:if test="${invoiceType == 'V'}">
                        <c:set var="selectInvoiceTypeVat" value="selected" />
                    </c:if>
                    <c:if test="${invoiceType == 'N'}">
                        <c:set var="selectInvoiceTypeNoVat" value="selected" />
                    </c:if>
                    <c:if test="${invoiceType == 'T'}">
                        <c:set var="selectInvoiceTypeTemp" value="selected" />
                    </c:if>
                    <select class="form-control" id="invoiceType" name="invoiceType">
                        <option value="">--Select--</option>
                        <option value="V" ${selectInvoiceTypeVat}>Vat </option>
                        <option value="N" ${selectInvoiceTypeNoVat}>No Vat </option>
                        <option value="T" ${selectInvoiceTypeTemp}>Temp </option>
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
            </div><br><br>
            <div class="col-xs-12"></div>
            <div class="col-xs-12"> <!--Row 2 -->
                <div class="col-xs-1 text-left" style="width: 120px">
                    <label class="control-label" for="">From<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1 form-group" style="width: 200px">
                    <div class='input-group date' id='InputFromDate'>
                    <c:if test='${from != null}'>
                        <input id="arFromDate" name="arFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${from}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${from == null}'>
                        <input id="arFromDate" name="arFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
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
                        <input id="arToDate" name="arToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${to}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${to == null}'>
                        <input id="arToDate" name="arToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
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
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-10"></div>
                <div class="col-xs-1">
                    <button type="submit"  id="btnSearchAR"  name="btnSearchAR"  onclick="searchArmonitor()"  class="btn btn-primary btn-primary">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>
                </div>
                <div class="col-xs-12"><br></div>  
                <div class="col-xs-12">
                    <table id="arDataListTable" class="display paginated" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden">Id</th>
                                <th style="width: 1%" onclick="selectAll()"><u>All</u></th>
                                <th style="width: 1%" >No</th>
                                <th style="width: 12%">Inv No.</th>
                                <th style="width: 12%">AR Code</th>
                                <th style="width: 26%">Inv To</th>
                                <th style="width: 12%">Acc Code</th>
                                <th style="width: 15%">Gross</th>
                                <th style="width: 15%">Amount</th>
                                <th style="width: 2%">Cur</th>
                                <th style="width: 5%">Status</th>
                             </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ar_nirvana" items="${listAr}" varStatus="countar">
                            <tr>
                                <td class="hidden"><input class="form-control" type="text" id="inputId${countar.count}" name="inputId${countar.count}" value="${ar_nirvana.id}"></td>
                                <td align="center">
                                    <input class="form-control" type="checkbox" id="selectAll${countar.count}" name="selectAll${countar.count}" value="${countar.count}">
                                </td>
                                <td align="center">${countar.count}</td>
                                <td>${ar_nirvana.intreference}</td>
                                <td>${ar_nirvana.customerid}</td>
                                <td>${ar_nirvana.customername}</td>
                                <td>${ar_nirvana.salesaccount1}</td>
                                <td align="right" class="money">${ar_nirvana.aramt -  ar_nirvana.vatamt}</td>
                                <td align="right" class="money">${ar_nirvana.aramt}</td>
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
                <label class="text-right">Are you sure to ar to nirvana ?</label>
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

<script language="javascript">
    $(document).ready(function () {
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
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
            "bPaginate": false,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10,
            "bSort": false,
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
                                    format: 'YYYY-MM-DD',
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
                                    format: 'YYYY-MM-DD',
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
            var $pager = $('<div class="col-xs-12 text-right"><font style="color: #499DD5"></font>&nbsp;</div>');
            var $br = $('<div class="col-xs-12"><br></div>');
            for (var page = 0; page < numPages; page++) {
                if(page === 0){
                    $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + "First" + "  ").bind('click', {
                    newPage: page
                    }, function(event) {
                        currentPage = event.data['newPage'];
                        $table.trigger('repaginate');
                        $(this).addClass('active').siblings().removeClass('active');
                    }).appendTo($pager).addClass('clickable');
                }
                
                $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + (page + 1) + "  ").bind('click', {
                    newPage: page
                }, function(event) {
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                }).appendTo($pager).addClass('clickable');
                
                if(page === (numPages - 1)){
                    $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + "Last" + "  ").bind('click', {
                    newPage: page
                    }, function(event) {
                        currentPage = event.data['newPage'];
                        $table.trigger('repaginate');
                        $(this).addClass('active').siblings().removeClass('active');
                    }).appendTo($pager).addClass('clickable');
                }
            }
            $br.insertAfter($table).addClass('active');
            $pager.insertAfter($table).find('span.page-number:first').addClass('active');            
        });
               
    });
    
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
        window.open("Excel.smi?name=ChangeARReport"); 
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
</script>
