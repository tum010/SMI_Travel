<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/SearchReceipt.js"></script>--> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="receiptSearchList" value="${requestScope['receiptSearchList']}" />
<c:set var="callPage" value="${requestScope['callPage']}" />
<c:set var="mFinanceItemStatus_List" value="${requestScope['mFinanceItemStatus_List']}" />
<c:set var="hiddenMenu" value="${requestScope['hiddenMenu']}" />

<section class="content-header" >
    <h1>
        Finance & Cashier - Search Receipt
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
            <!--<div class="row" style="padding-left: 15px">-->  
<!--                <div class="col-sm-6" style="padding-right: 15px">
                    <h4><b>Search Receipt</b></h4>
                </div>-->
            <!--</div>-->
            <!--<hr/>-->
            
            <form action="SearchReceipt.smi" method="post" id="SearchReceiptForm" name="SearchReceiptForm" role="form">
                <div class="sm_row col-xs-12" style="padding-top: 25px">
                    <div class="col-xs-1 text-right"  style="width: 80px">
                        <label class="control-label text-right">From<font style="color: red">*</font></label>
                    </div>
                    <div class="col-xs-1"  style="width: 170px">
                        <div class=" form-group"> 
                            <div class='input-group date fromdate' id="DateFrom">
                                <input id="inputFromDate" name="inputFromDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['inputFromDate']}">
                                <span class="input-group-addon spandate" id="InputFromDateSpan1"><span class="glyphicon glyphicon-calendar" id="InputFromDateSpan2"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-1 text-right"  style="width: 80px">
                        <label class="control-label text-right">To<font style="color: red">*</font></label>
                    </div>
                    <div class="col-xs-1"  style="width: 170px">
                        <div class=" form-group"> 
                            <div class='input-group date todate' id="DateTo">
                                <input id="inputToDate" name="inputToDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['inputToDate']}">
                                <span class="input-group-addon spandate" id="InputToDateSpan1"><span class="glyphicon glyphicon-calendar" id="InputToDateSpan2"></span></span>
                            </div>
                        </div>        
                    </div>
                    <div class="col-xs-1 text-right" style="width: 100px">
                        <label class="control-label text-right">Department </label>
                    </div>
                    <div class="col-xs-1" style="width: 125px">
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
                            <c:choose>
                                <c:when test="${requestScope['department'] == 'WendyOutbound'}">
                                    <c:set var="selectedWendyOutbound" value="selected" />
                                </c:when>
                            </c:choose>
                            <option value="WendyOutbound" ${selectedWendyOutbound}>Wendy + Outbound</option>
                        </select>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 40px">
                        <label class="control-label text-right">Type </label>
                    </div>
                    <div class="col-xs-1" style="width: 98px"  id="classHideTemp">
                        <select id="recType" name="recType" class="form-control selectize">
                            <option value="V" ${selectedVat}>Vat</option>
                        </select>
                    </div>
                    <div class="col-xs-1" style="width: 98px"  id="classShowTemp" hidden="">
                        <select id="recType" name="recType" class="form-control selectize">
                            <option value="T" ${selectedTemp}>Temp</option>
                        </select>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 50px">
                        <label class="control-label text-right">Payment </label>
                    </div>          
                    <div class="col-md-1 form-group" style="padding: 0px 0px 0px 30px;width: 120px">
                        <select class="form-control" id="status" name="status">
                            <option value="">----</option>
                            <c:forEach var="mFinanceList" items="${mFinanceItemStatus_List}" varStatus="i">
                                <c:set var="select" value="" />
                                <c:if test="${mFinanceList.id == requestScope['status']}">
                                    <c:set var="select" value="selected" />
                                </c:if>
                                <option  value="${mFinanceList.id}" ${select}>${mFinanceList.name}</option>
                            </c:forEach>
                        </select>    
                    </div>                   
                    <input type="hidden" id="dateFromSearch" name="InputDateFrom" >                        
                    <input type="hidden" id="dateToSearch" name="InputDateTo" >
                    <input type="hidden" name="hiddenMenu" id="hiddenMenu" value="${hiddenMenu}">       
                    <!--<input type="hidden" id="ticketId" name="ticketId" >-->
                    <input type="hidden" name="action" id="action" value="">                       
                </div>
                <div class="sm_row col-xs-12 form-group"></div>  
                <div class="sm_row col-xs-12">
                    <div class="col-xs-1" style="width: 854px"></div>
                    <div class="col-xs-1 text-left" >
                        <button type="submit" id="ButtonSearch" name="ButtonSearch" onclick="searchAction()" style="height:34px" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search </button>
                    </div>
                    <div class="col-xs-1 text-left" >
                        <button type="button" id="ButtonPrint" onclick="printReceiptSummary()" name="ButtonPrint" class="btn btn-default"><i class="glyphicon glyphicon-print"></i> Print </button>
                    </div>      
                </div>   
            </form>
            <div class="col-xs-12 form-group"></div>  
            <div class="sm_row col-xs-12 form-group"><hr/></div>       
            <!--Table-->
            <div class="sm_row row">
                <div class="sm_row col-md-12 ">
                    <table id="ReceiptListTable" class="display hidden active" cellspacing="0" width="100%" style="table-layout: fixed">
                        <thead> 
                            <tr class="datatable-header" >
                                <th style="width:7%;">Receive No</th>
                                <th style="width:7%;">Date</th>
                                <th style="width:7%;">Code</th>
                                <th style="width:20%;">Name</th>
                                <th style="width:8%;">Invoice No</th>
                                <th style="width:10%;">Amount</th>
                                <th style="width:7%;">Status</th>
                                <th style="width:7%;">Department</th>
                                <th style="width:7%;">Status</th>
                                <th style="width:7%;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="table" items="${receiptSearchList}" varStatus="dataStatus">
                                <tr>
                                    <td align="center">${table.recNo}</td>
                                    <td align="center">${table.recDate}</td>
                                    <td>${table.recTo}</td>
                                    <td>${table.recName}</td>
                                    <td>${table.invoiceNo}</td>
                                    <td align="right"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.amount}" /></td>
                                    <td align="center">${table.termPay}</td>
                                    <td align="center">${table.department}</td>
                                    <td align="center">${table.status}</td>
                                    <td> 
                                        <center>
                                            <c:if test="${table.department == 'Wendy'}">
                                                <a  href="ReceiptW${table.recType}.smi?Id=${table.recId}">
                                                    <span class="glyphicon glyphicon-edit editicon"  ></span>
                                                </a>
                                            </c:if>
                                            <c:if test="${table.department == 'Inbound'}">
                                                <a  href="ReceiptI${table.recType}.smi?Id=${table.recId}">
                                                    <span class="glyphicon glyphicon-edit editicon"  ></span>
                                                </a>
                                            </c:if>
                                            <c:if test="${table.department == 'Outbound'}">
                                                <a  href="ReceiptO${table.recType}.smi?Id=${table.recId}">
                                                    <span class="glyphicon glyphicon-edit editicon"  ></span>
                                                </a>
                                            </c:if>
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
        $('.datemask').mask('0000-00-00');
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
        
//         $('#ReceiptListTable tbody').on('click', 'tr', function() {
//            if ($(this).hasClass('row_selected')) {
//                $(this).removeClass('row_selected');
//                $('#hdGridSelected').val('');
//            }
//            else {
//                table.$('tr.row_selected').removeClass('row_selected');
//                $(this).addClass('row_selected');
//                $('#hdGridSelected').val($('#ReceiptListTable tbody tr.row_selected').attr("id"));
//            }
//        });
        
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

        $('.fromdate').datetimepicker().change(function(){                          
            checkFromDateField();
        });
        $('.todate').datetimepicker().change(function(){                          
            checkToDateField();
        });   
        
        setMenu();
    });
    
function setMenu(){
    if($("#hiddenMenu").val() === "1111"){
        $(".receiptTemp").show();
        $("#tempBox").val("");
        $("#hiddenMenu").val('1111');
        $('#recType')
            .find('option')
            .remove()
            .end()
        ;       
        $('#recType').append($('<option>', {
            value: 'T',
            text: 'Temp'
        }));
    }else if($("#hiddenMenu").val() === "2222"){
        $(".receiptTemp").hide();
        $("#tempBox").val("");
        $("#hiddenMenu").val('2222');
        $('#recType')
            .find('option')
            .remove()
            .end()
         ;       
        $('#recType').append($('<option>', {
            value: 'V',
            text: 'Vat'
        }));
    }
}

function searchAction(){
    var action = document.getElementById('action');
    action.value = 'search';
}

function printReceiptSummary(){
    var recType = document.getElementById('recType').value;
    var department = document.getElementById('department').value;
    var inputToDate = document.getElementById('inputToDate').value;
    var inputFromDate = document.getElementById('inputFromDate').value;
    var status = document.getElementById("status").value;
    var strStatus = '';
    if(status === '1'){
        strStatus = "NORMAL";
    }else if(status === '2'){
        strStatus = "VOID";
    }
    if(department === 'WendyOutbound'){
        department = 'Wendy,Outbound';
    }
    if((inputToDate === '') || (inputFromDate === '')){
        validateDate();
    } else {
        window.open("report.smi?name=ReceiptSummaryReport&dateFrom="+inputFromDate+"&dateTo="+inputToDate+"&departmentRec="+department+"&recType="+recType+"&comfirm="+strStatus);
    }   
}

function checkFromDateField(){
    var inputFromDate = document.getElementById("inputFromDate");
    var InputToDate = document.getElementById("inputToDate");
    if(inputFromDate.value === ''){          
        var InputFromDateSpan1 = document.getElementById("InputFromDateSpan1");
        inputFromDate.style.borderColor = "red";
        InputFromDateSpan1.style.borderColor = "red";
        if((inputFromDate.style.borderColor === "red") && (InputToDate.style.borderColor === "red")){
            $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputToDate');
            $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputFromDate');
            $("#ButtonPrint").addClass("disabled");
        }
    } else {
        $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputToDate');
        $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputFromDate');
        $("#ButtonPrint").removeClass("disabled");
        checkDateValue("from","");
    }
}
    
function checkToDateField(){
    var InputToDate = document.getElementById("inputToDate");
    var inputFromDate = document.getElementById("inputFromDate");
    if(InputToDate.value === ''){
        var InputToDateSpan1 = document.getElementById("InputToDateSpan1");
        InputToDate.style.borderColor = "red";
        InputToDateSpan1.style.borderColor = "red";
        if((inputFromDate.style.borderColor === "red") && (InputToDate.style.borderColor === "red")){
            $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputToDate');
            $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputFromDate');
            $("#ButtonPrint").addClass("disabled");
        }    
    }else{
        $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputToDate');
        $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputFromDate');
        $("#ButtonPrint").removeClass("disabled");
        checkDateValue("to","");
    }       
}
    
function checkDateValue(date){
    var inputFromDate = document.getElementById("inputFromDate");
    var InputToDate = document.getElementById("inputToDate");
    if((inputFromDate.value !== '') && (InputToDate.value !== '')){
        var fromDate = (inputFromDate.value).split('-');
        var toDate = (InputToDate.value).split('-');
        if((parseInt(fromDate[0])) > (parseInt(toDate[0]))){
            validateDate(date,"over");
        }
        if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))){
            validateDate(date,"over");
        }
        if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[2])) > (parseInt(toDate[2]))){
            validateDate(date,"over");
        }          
    }
}
    
function validateDate(date,option){
    var inputFromDate = document.getElementById("inputFromDate");
    var InputFromDateSpan1 = document.getElementById("InputFromDateSpan1");
    var InputToDate = document.getElementById("inputToDate");
    var InputToDateSpan1 = document.getElementById("InputToDateSpan1");
    if(option === 'over'){

        if(date === 'from'){
            $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputToDate');
            $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputFromDate');
        }
        if(date === 'to'){
            $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputToDate');
            $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputFromDate');
        }           
        $("#ButtonPrint").addClass("disabled");
    } else {
        $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputToDate');
        $('#SearchReceiptForm').bootstrapValidator('revalidateField', 'inputFromDate');
        $("#ButtonPrint").addClass("disabled");
    }

}
</script>
