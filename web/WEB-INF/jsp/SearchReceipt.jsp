<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/SearchReceipt.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="receiptSearchList" value="${requestScope['receiptSearchList']}" />
<c:set var="callPage" value="${requestScope['callPage']}" />
<section class="content-header" >
    <h1>
        Finance & Cashier - Receipt
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Finance & Cashier</a></li>          
        <li class="active"><a href="#">Search Receipt</a></li>
    </ol>
</section>

<div style="padding-top: 15px;padding-right: 0px "ng-app=""> 
    <div class="row">
       
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/ReceiptMenu.html'"></div>
        </div>
        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6" style="padding-right: 15px">
                    <h4><b>Search Receipt</b></h4>
                </div>
            </div>
            <hr/>
            
            <form action="SearchReceipt.smi" method="post" id="SearchReceiptForm" name="SearchReceiptForm" role="form">
                <div class="col-xs-12">
                    <div class="col-xs-1 text-right"  style="width: 80px">
                        <label class="control-label text-right">From </label>
                    </div>
                    <div class="col-xs-1"  style="width: 170px">
                        <div class=" form-group"> 
                            <div class='input-group date' id="DateFrom">
                                <input id="inputFromDate" name="inputFromDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['inputFromDate']}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-1 text-right"  style="width: 80px">
                        <label class="control-label text-right">To </label>
                    </div>
                    <div class="col-xs-1"  style="width: 170px">
                        <div class=" form-group"> 
                            <div class='input-group date' id="DateTo">
                                <input id="inputToDate" name="inputToDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['inputToDate']}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>        
                    </div>
                    <div class="col-xs-1 text-right" style="width: 100px">
                        <label class="control-label text-right">Department </label>
                    </div>
                    <div class="col-xs-1" style="width: 130px">
                        <select id="department" name="department" class="form-control selectize">
                            <option value="">-------</option> 
                             <c:choose>
                                <c:when test="${requestScope['department'] == 'Wendy'}">
                                    <c:set var="selectedWendy" value="selected" />
                                </c:when>
                            </c:choose>
                            <option value="Wendy" ${selectedWendy}>Wendy</option>
                            <c:choose>
                                <c:when test="${requestScope['department'] == 'Inbound'}">
                                    <c:set var="selectedInbound" value="selected" />
                                </c:when>
                            </c:choose>
                            <option value="Inbound" ${selectedInbound}>Inbound</option>
                            <c:choose>
                                <c:when test="${requestScope['department'] == 'Outbound'}">
                                    <c:set var="selectedOutbound" value="selected" />
                                </c:when>
                            </c:choose>
                            <option value="Outbound" ${selectedOutbound}>Outbound</option>
                        </select>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 40px">
                        <label class="control-label text-right">Type </label>
                    </div>
                    <div class="col-xs-1" style="width: 100px">
                        <select id="recType" name="recType" class="form-control selectize">
                            <option value="">----</option> 
                             <c:choose>
                                <c:when test="${requestScope['recType'] == 'T'}">
                                    <c:set var="selectedTemp" value="selected" />
                                </c:when>
                            </c:choose>
                            <option value="T" ${selectedTemp}>Temp</option>
                            <c:choose>
                                <c:when test="${requestScope['recType'] == 'V'}">
                                    <c:set var="selectedVat" value="selected" />
                                </c:when>
                            </c:choose>
                            <option value="V" ${selectedVat}>Vat</option>
                        </select>
                    </div>   
                    <input type="hidden" id="dateFromSearch" name="InputDateFrom" >                        
                    <input type="hidden" id="dateToSearch" name="InputDateTo" > 
                    <!--<input type="hidden" id="ticketId" name="ticketId" >-->
                    <input type="hidden" name="action" id="action" value="">    
                    <div class="col-xs-1 text-left" >
                        <button type="submit" id="ButtonSearch" name="ButtonSearch" onclick="searchAction()" style="height:34px" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search </button>
                    </div>
                    <div class="col-xs-1 text-left" >
                        <button type="button" id="ButtonPrint" onclick="printReceiptSummary()" name="ButtonPrint" class="btn btn-default"><i class="glyphicon glyphicon-print"></i> Print </button>
                    </div>
                </div>
            </form>
            <!--Table-->
            <div class="row">
                <div class="col-md-12 ">
                    <table id="ReceiptListTable" class="display hidden active" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header" >
                                <th style="width:7%;">Receive No</th>
                                <th style="width:7%;">Date</th>
                                <th style="width:7%;">To</th>
                                <th style="width:15%;">Name</th>
                                <th style="width:10%;">Invoice No</th>
                                <th style="width:10%;">Amount</th>
                                <th style="width:7%;">Term Pay</th>
                                <th style="width:7%;">Department</th>
                                <th style="width:7%;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="table" items="${receiptSearchList}" varStatus="dataStatus">
                                <tr>
                                    <td align="center">${table.recNo}</td>
                                    <td align="center">${table.recDate}</td>
                                    <td align="center">${table.recTo}</td>
                                    <td align="center">${table.recName}</td>
                                    <td align="center">${table.invoiceNo}</td>
                                    <td align="center">${table.amount}</td>
                                    <td align="center">${table.termPay}</td>
                                    <td align="center">${table.department}</td>
                                    <td> 
                                        <center> 
                                            <a  href="${callPage}?Id=${table.recId}">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
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

<!--Script-->       
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $("#ReceiptListTable").removeClass('hidden');
        
        var table = $('#ReceiptListTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "aaSorting": [[ 0, "desc" ]]
        });
            
        $('.date').datetimepicker();
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        $('#ButtonShow').on('click', function() {
            $("#ReceiptListTable").removeClass('hidden');
            var table = $('#ReceiptListTable').dataTable({bJQueryUI: true,
                "sPaginationType": "full_numbers",
                "bAutoWidth": false,
                "bFilter": false,
                "aaSorting": [[ 0, "desc" ]]
            });
        });
        
//        $('#ButtonPrint').on('click', function() {
//            alert('Print');
//        });
        
        
        //validate date
        $('#DateFrom').datetimepicker().on('dp.change', function (e) {
            $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputFromDate');
        });
        $('#DateTo').datetimepicker().on('dp.change', function (e) {
            $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputToDate');
        });

        $("#SearchReceiptForm")
                .bootstrapValidator({
                    framework: 'bootstrap',
    //                container: 'tooltip',
                    feedbackIcons: {
                        valid: 'uk-icon-check',
                        invalid: 'uk-icon-times',
                        validating: 'uk-icon-refresh'
                    },
                    fields: {
                        inputFromDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date From is required'
                                },
                                date: {
                                    format: 'YYYY-MM-DD',
                                    max: 'inputToDate',
                                    message: 'The Date From is not a valid'
                                }
                            }
                        },
                        inputToDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date To is required'
                                },
                                date: {
                                    format: 'YYYY-MM-DD',
                                    min: 'inputFromDate',
                                    message: 'The Date To is not a valid'
                                }
                            }
                        }
                    }
                }).on('success.field.fv', function (e, data) {
                    if (data.field === 'inputFromDate' && data.fv.isValidField('inputToDate') === false) {
                        data.fv.revalidateField('inputToDate');
                    }

                    if (data.field === 'inputToDate' && data.fv.isValidField('inputFromDate') === false) {
                        data.fv.revalidateField('inputFromDate');
                    }
                });


    });

function searchAction(){
    var action = document.getElementById('action');
    action.value = 'search';
}

function printReceiptSummary(){
    var recType = document.getElementById('recType').value;
    var department = document.getElementById('department').value;
    var inputToDate = document.getElementById('inputToDate').value;
    var inputFromDate = document.getElementById('inputFromDate').value;
    
    window.open("report.smi?name=ReceiptSummaryReport&dateFrom="+inputFromDate+"&dateTo="+inputToDate+"&departmentRec="+department+"&recType="+recType);
}
</script>
