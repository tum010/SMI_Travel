<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/APMonitor.js"></script> -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="mpaytype_list" value="${requestScope['mpaytype_list']}" />
<c:set var="data_list" value="${requestScope['data_list']}" />
<section class="content-header" >
    <h1>
        Nirvana Interface
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Nirvana Interface</a></li>          
        <li class="active"><a href="#"></a>AP Monitor</li>
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
                <h4><b>AP Monitor</b></h4>                  
            </div>
            <div class="col-xs-12 form-group"><hr/></div>
        </div>
        <form action="APMonitor.smi" method="post" id="apMonitorForm" role="form" autocomplete="off">
            <div class="col-xs-12">
                <c:if test="${requestScope['update'] =='1'}">                                            
                    <div id="textAlertDivSave"  style="" class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Update Status Success!</strong> 
                    </div>
                </c:if>
                <c:if test="${requestScope['update'] =='0'}">
                <div id="textAlertDivSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <strong>Update Status Unsuccess!</strong> 
                </div>
                </c:if>
                <div id="textAlertDivNotChoose"  style="display: none" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" aria-label="Close" onclick="hideDiv()"><span aria-hidden="true">&times;</span></button>
                    <strong id="message"></strong> 
                </div>
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">Payment</lable>
                </div>
                <div class="col-xs-1" style="width: 240px">
                    <select class="form-control" id="apPayment" name="apPayment" onchange="apPaymentAir()">
                        <option value=""> </option>
                        <c:set var="A" value="" />
                        <c:if test="${'A' == requestScope['apPayment']}">
                            <c:set var="A" value="selected" />
                        </c:if>
                        <option value="A" ${A}>Air Ticket</option>
                        <c:set var="W" value="" />
                        <c:if test="${'W' == requestScope['apPayment']}">
                            <c:set var="W" value="selected" />
                        </c:if>
                        <option value="W" ${W}>Wendy</option>
                        <c:set var="O" value="" />
                        <c:if test="${'O' == requestScope['apPayment']}">
                            <c:set var="O" value="selected" />
                        </c:if>
                        <option value="O" ${O}>Outbound</option>
                    </select>
                </div>
                <div class="col-xs-1" style="width: 50px"></div>
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">Type</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <c:set var="air" value="" />
                        <c:if test="${'A' == requestScope['apPayment']}">
                            <c:set var="air" value="disabled" />
                        </c:if>
                    <select class="form-control" id="apType" name="apType" ${air}>
                        <option value=""> </option>
                        <c:forEach var="mpaytype_list" items="${mpaytype_list}">
                            <c:set var="select" value="" />
                            <c:if test="${mpaytype_list.id == requestScope['apType']}">
                                <c:set var="select" value="selected" />
                            </c:if>
                            <option value="<c:out value="${mpaytype_list.id}" />" ${select}><c:out value="${mpaytype_list.name}" /></option>                                         
                        </c:forEach>
                    </select>
                </div>
                <div class="col-xs-1" style="width: 52px"></div>
                <div class="col-xs-1 text-right">
                    <label class="control-label" for="">Status</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <select class="form-control" id="apStatus" name="apStatus">
                        <option value=""> </option>
                        <c:set var="A" value="" />
                        <c:if test="${'N' == requestScope['apStatus']}">
                            <c:set var="N" value="selected" />
                        </c:if>
                        <option value="N" ${N}>New</option>
                        <c:set var="E" value="" />
                        <c:if test="${'E' == requestScope['apStatus']}">
                            <c:set var="E" value="selected" />
                        </c:if>
                        <option value="E" ${E}>Export</option>
                        <c:set var="C" value="" />
                        <c:if test="${'C' == requestScope['apStatus']}">
                            <c:set var="C" value="selected" />
                        </c:if>
                        <option value="C" ${C}>Change</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">From<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1 form-group" style="width: 170px">
                    <div class='input-group date fromdate' id='InputFromDate'>
                    <c:if test='${taxInvoice.taxInvDate != null}'>
                        <input id="apFromDate" name="apFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${taxInvoice.taxInvDate == null}'>
                        <input id="apFromDate" name="apFromDate"  type="text" 
                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['apFromDate']}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>               
                </div>
                <div class="col-xs-1" style="width: 120px"></div>
                <div class="col-xs-1 text-left">
                    <label class="control-label">To<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1 form-group" style="width: 170px">
                    <div class='input-group date todate' id='InputToDate'>
                    <c:if test='${taxInvoice.taxInvDate != null}'>
                        <input id="apToDate" name="apToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${taxInvoice.taxInvDate == null}'>
                        <input id="apToDate" name="apToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['apToDate']}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>               
                </div>
                <div class="col-xs-1 text-right" style="width: 163px">
                    <label class="control-label" for="">Acc</lable>
                </div>
                <div class="col-xs-1" style="width: 100px">
                    <select class="form-control" id="apAccno" name="apAccno">
                        <option value=""> </option>
                        <c:set var="accno1" value="" />
                        <c:if test="${'1' == requestScope['apAccno']}">
                            <c:set var="accno1" value="selected" />
                        </c:if>
                        <option value="1" ${accno1}>1</option>
                        <c:set var="accno2" value="" />
                        <c:if test="${'2' == requestScope['apAccno']}">
                            <c:set var="accno2" value="selected" />
                        </c:if>
                        <option value="2" ${accno2}>2</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-12">
                <div class="col-xs-1" style="width: 870px"></div>
                <div class="col-xs-1">
                    <button type="submit"  id="btnSearchAP"  name="btnSearchAP" class="btn btn-primary btn-primary">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>
                </div>
                <div class="col-xs-12"><br></div>
                <div class="col-xs-12">
                    <input type="hidden" id="apCount" name="apCount" value="${data_list.size()}"/>
                    <table id="apDataListTable" class="display paginated" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden">Id</th>
                                <th onclick="selectAll()" style="width: 1%"><u>All</u></th>
                                <th style="width: 1%" >No</th>
                                <th style="width: 12%">Pay No.</th>
                                <th style="width: 12%">AP Code</th>
                                <th style="width: 26%">Invoice Sup</th>
                                <th style="width: 12%">Acc Code</th>
                                <th style="width: 15%">Gross</th>
                                <th style="width: 15%">Amount</th>
                                <th style="width: 2%">Cur</th>
                                <th style="width: 5%">Status</th>
                             </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="data_list" items="${data_list}" varStatus="i">
                            <tr>
                                <td class="hidden">
                                    <input type="hidden" id="paymentDetailId${i.count}" name="paymentDetailId${i.count}" value="${data_list.payment_detail_id}"/> 
                                    <input type="hidden" id="paymentType${i.count}" name="paymentType${i.count}" value="${data_list.paymenttype}"/>
                                    <input type="hidden" id="rowid${i.count}" name="rowid${i.count}" value="${data_list.rowid}"/>
                                </td>                              
                                <td align="center">
                                    <c:choose>
                                        <c:when test="${data_list.itf_status == 'New'}">
                                            <input type="checkbox" class="form-control" id="selectAll${i.count}" name="selectAll${i.count}" value="1"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" class="form-control" id="selectAll" name="selectAll" value="" disabled=""/>
                                        </c:otherwise>
                                    </c:choose>                                  
                                </td>
                                <td align="center">${i.count}</td>
                                <td>${data_list.payno}</td>
                                <td>${data_list.vendorid}</td>
                                <td>${data_list.vendorname}</td>
                                <td>${data_list.puraccount1}</td>
                                <td align="right" class="money">${data_list.vatamt}</td>
                                <td align="right" class="money">${data_list.basevatamt}</td>
                                <td align="center">${data_list.currencyid}</td>
                                <td align="center">${data_list.itf_status}</td>
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
                    <button id="btnDownloadAP"  type="button" class="btn btn-default" data-dismiss="modal" onclick="printChangeApReport()">
                        <span class="glyphicon glyphicon-print" ></span> Print Change AP Report 
                    </button>
                </div>
                <div class="col-xs-1 text-left" style="">
                    <button type="button" class="btn btn-success btn-default" onclick="exportAP()"  data-dismiss="modal">
                        <span id="btnExportAP" class="glyphicon glyphicon-export" ></span> Export
                    </button>
                </div>    
            </div>
            <input type="hidden" id="action" name="action" value="search"/>
        </form>
    </div>
</div> 

<!--Export AP Modal-->
<div class="modal fade" id="apExportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Export AP</h4>
            </div>
            <div class="modal-body" id="copyReceiptModal" >
                <label class="text-right">Are you sure to ap to nirvana ?</label>
                <input type="hidden" id="chooseAP" name="chooseAP" value=""/>
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
        var table = $('#apDataListTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false,
            "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
            "iDisplayLength": 50,
            "bSort": false,
            "bPaginate": false
        });
        
        $('#apDataListTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#apDataListTable tbody tr.row_selected').attr("id"));
            }
        });
        
        $('#InputFromDate').datetimepicker().on('dp.change', function (e) {
            $('#apMonitorForm').bootstrapValidator('revalidateField', 'apFromDate');
        });
        $('#InputToDate').datetimepicker().on('dp.change', function (e) {
            $('#apMonitorForm').bootstrapValidator('revalidateField', 'apToDate');
        });
        
        $("#apMonitorForm").bootstrapValidator({
                    framework: 'bootstrap',
    //                container: 'tooltip',
                    feedbackIcons: {
                        valid: 'uk-icon-check',
                        invalid: 'uk-icon-times',
                        validating: 'uk-icon-refresh'
                    },
                    fields: {
                        apFromDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date From is required'
                                },
                                date: {
                                    format: 'YYYY-MM-DD',
                                    max: 'apToDate',
                                    message: 'The Date From is not a valid'
                                }
                            }
                        },
                        apToDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date From is required'
                                },
                                date: {
                                    format: 'YYYY-MM-DD',
                                    min: 'apFromDate',
                                    message: 'The Date To is not a valid'
                                }
                            }
                        }
                    }
                }).on('success.field.fv', function (e, data) {
            if (data.field === 'apFromDate' && data.fv.isValidField('apToDate') === false) {
                data.fv.revalidateField('apToDate');
            }

            if (data.field === 'apToDate' && data.fv.isValidField('apFromDate') === false) {
                data.fv.revalidateField('apFromDate');
            }                         
        });
        
        $('.fromdate').datetimepicker().change(function(){                          
            checkFromDateField();
        });
        $('.todate').datetimepicker().change(function(){                          
            checkToDateField();
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
                if(page === 0){
                    $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + "First" + "  ").bind('click', {
                    newPage: page
                    }, function(event) {
                        currentPage = event.data['newPage'];
                        $table.trigger('repaginate');
                        $(this).addClass('active').siblings().removeClass('active');
                    }).appendTo($pager).addClass('clickable');
                }
                                             
                $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + (page + 1) + " ").bind('click', {
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
            document.getElementById("pageNo").style.cursor="pointer";
        });
        
    });
    
    function exportAP(){
        var row = $('#apDataListTable tr').length;
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
            document.getElementById("chooseAP").value = "false";
        } else {
            document.getElementById("chooseAP").value = "true";
        }
        $("#textAlertDivSave").hide();
        $('#textAlertDivNotChoose').hide();
        $("#apExportModal").modal("show");
    }
    
    function confirmExport(){
        $("#apExportModal").modal("hide");
        var chooseAP = document.getElementById("chooseAP").value;
        if(chooseAP === 'true'){
            var action = document.getElementById("action");
            action.value = "export";
            document.getElementById("apMonitorForm").submit();
        } else {
            document.getElementById("message").innerHTML = 'Please choose the ap monitor list.!';
            $('#textAlertDivNotChoose').show();
        }        
    }
    
    function hideDiv(){
        $('#textAlertDivNotChoose').hide();
    }
    
    function selectAll(){
        var row = $('#apDataListTable tr').length;
        var check = 0;
        var unCheck = 0;
        for(var i=1;i<=row;i++){          
            var selectAll = document.getElementById("selectAll"+i);
            if(selectAll !== null && selectAll !== ''){
                if(document.getElementById("selectAll"+i).checked){
                    check++;
                } else {
                    unCheck++;
                }
            }   
        }

        if(check > unCheck){
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
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    document.getElementById("selectAll"+i).checked = false;
                }   
            }
        }

        if(check === 0 && unCheck !== 0){
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
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    document.getElementById("selectAll"+i).checked = false;
                }   
            }
        }

        if(check === unCheck){
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
    
    function apPaymentAir(){
        var apPayment = document.getElementById("apPayment").value;
        if(apPayment !== 'A'){
            $('#apType').removeAttr('disabled');           
        } else {
            $('#apType').attr('disabled', 'disabled');
            $('[name=apType] option').filter(function() { 
                return ($(this).val() === '');
            }).prop('selected', true);      
        }
    }
    
    function printChangeApReport(){
        var payment = document.getElementById("apPayment").value;
        var ticketType = document.getElementById("apType").value;
        var status = document.getElementById("apStatus").value;
        var dateFrom = document.getElementById("apFromDate").value;
        var dateTo = document.getElementById("apToDate").value;
        if((dateFrom !== '') && (dateTo !== '')){
            window.open("Excel.smi?name=ApReport&payment=" + payment + "&ticketType=" + ticketType + "&status=" + status + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo);
        } else {
            validateDate();         
        }
    }
    
    function checkFromDateField(){
        var inputFromDate = document.getElementById("apFromDate");
        if(inputFromDate.value === ''){          
            $('#apMonitorForm').bootstrapValidator('revalidateField', 'apFromDate');
            $("#btnDownloadAP").addClass("disabled");         
        } else {
            $("#btnDownloadAP").removeClass("disabled");
            checkDateValue("from","");
        }      
    }
    
    function checkToDateField(){
        var InputToDate = document.getElementById("apToDate");
        if(InputToDate.value === ''){
            $('#apMonitorForm').bootstrapValidator('revalidateField', 'apToDate');
            $("#btnDownloadAP").addClass("disabled"); 
        }else{
            $("#btnDownloadAP").removeClass("disabled");
            checkDateValue("to","");
        }               
    }
    
    function checkDateValue(date){
        var inputFromDate = document.getElementById("apFromDate");
        var InputToDate = document.getElementById("apToDate");
        if((inputFromDate.value !== '') && (InputToDate.value !== '')){
            var fromDate = (inputFromDate.value).split('-');
            var toDate = (InputToDate.value).split('-');      
            if((parseInt(fromDate[0])) > (parseInt(toDate[0]))){
                validateDate(date,"over");
            }else if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))){
                validateDate(date,"over");
            }else if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[2])) > (parseInt(toDate[2]))){
                validateDate(date,"over");
            }else{
                $('#apMonitorForm').bootstrapValidator('revalidateField', 'apFromDate');
                $('#apMonitorForm').bootstrapValidator('revalidateField', 'apToDate');
            }           
        }
    }
    
    function validateDate(date,option){
        if(option === 'over'){
            if(date === 'from'){
                $('#apMonitorForm').bootstrapValidator('revalidateField', 'apFromDate');
                $('#apMonitorForm').bootstrapValidator('revalidateField', 'apToDate');
            }
            if(date === 'to'){
                $('#apMonitorForm').bootstrapValidator('revalidateField', 'apFromDate');
                $('#apMonitorForm').bootstrapValidator('revalidateField', 'apToDate');
            }           
            $("#btnDownloadAP").addClass("disabled");
        } else {
            $('#apMonitorForm').bootstrapValidator('revalidateField', 'apFromDate');
            $('#apMonitorForm').bootstrapValidator('revalidateField', 'apToDate');
            $("#btnDownloadAP").addClass("disabled");
        }
    }
</script>