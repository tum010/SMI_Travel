<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/SearchInvoice.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
<c:set var="taxInvoiceView_List" value="${requestScope['taxInvoiceView_List']}" />
<c:set var="mFinanceItemStatus_List" value="${requestScope['mFinanceItemStatus_List']}" />
<section class="content-header" >
    <h1>
        Finance & Cashier - Search Tax Invoice
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Search Tax Invoice</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app="">
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-1" style="border-right:  solid 1px #01C632;padding-top: 10px; padding-left: 0px; width: 140px;">
            <div ng-include="'WebContent/FinanceAndCashier/TaxInvoiceMainMenu.html'"></div>
        </div>        
        <div class="col-sm-11" style="padding-right: 0px; width: 1120px;">
<!--            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Search Tax Invoice</b></h4>
                </div>            
            </div>-->
            <form action="SearchTaxInvoice.smi" method="post" id="TaxInvoiceSearchForm" name="TaxInvoiceSearchForm" role="form">
            <div class="sm_row col-xs-12 " style="padding-top: 25px">
                <div class="col-xs-1 text-right">
                    <label class="control-label" for="">From<font style="color: red">*</font>&nbsp;</lable>
                </div>
                <div class="col-md-2 form-group">
                    <%--<fmt:parseDate value="${requestScope['inputFromDate']}" var="fromDate" pattern="dd-MM-yyyy" />--%>
                    <div class='input-group date fromdate' id="FromDate">
                        <input id="InputFromDate" name="InputFromDate"  type="text" onfocusout="checkDate()"
                            class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${requestScope['inputFromDate']}">
                        <span class="input-group-addon spandate" id="InputFromDateSpan1"><span class="glyphicon glyphicon-calendar" id="InputFromDateSpan2"></span></span>                                                         
                    </div>
                </div>
                <div class="col-xs-1 text-right">
                    <label class="control-label" for="">To<font style="color: red">*</font>&nbsp;</lable>
                </div>
                <div class="col-md-2 form-group">
                    <%--<fmt:parseDate value="${requestScope['inputToDate']}" var="toDate" pattern="dd-MM-yyyy" />--%>
                    <div class='input-group date todate' id='ToDate'>                    
                        <input id="InputToDate" name="InputToDate"  type="text" 
                            class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${requestScope['inputToDate']}">
                        <span class="input-group-addon spandate" id="InputToDateSpan1"><span class="glyphicon glyphicon-calendar" id="InputToDateSpan2"></span></span>                                                       
                    </div>
                </div>
                <div class="col-xs-1 text-right" style="padding: 0px 0px 0px 20px">
                    <label class="control-label" for="">Department<font style="color: red"></font></lable>
                </div>
                <div class="col-md-2 form-group" style="padding: 0px 0px 0px 30px;width: 125px">
                    <select class="form-control" id="Department" name="Department">
                        <option value="">Choose</option>
                            <c:choose>
                                <c:when test="${requestScope['department'] == 'Wendy'}">
                                    <c:set var="select1" value="selected" />
                                </c:when>
                            </c:choose>
                        <option value="Wendy" ${select1} >Wendy</option>                      
                            <c:choose>
                                <c:when test="${requestScope['department'] == 'Outbound'}">
                                    <c:set var="select2" value="selected" />
                                </c:when>
                            </c:choose>
                        <option value="Outbound" ${select2} >Outbound</option>
                            <c:choose>
                                <c:when test="${requestScope['department'] == 'Inbound'}">
                                    <c:set var="select3" value="selected" />
                                </c:when>
                            </c:choose>
                         <option value="Inbound" ${select3}>Inbound</option>
                            <c:choose>
                                <c:when test="${requestScope['department'] == 'WendyOutbound'}">
                                    <c:set var="select4" value="selected" />
                                </c:when>
                            </c:choose>
                         <option value="WendyOutbound" ${select4}>Wendy + Outbound</option>
                    </select>    
                </div>
                <div class="col-xs-1 text-right" style="padding: 0px 0px 0px 20px">
                    <label class="control-label" for="">Status<font style="color: red"></font></lable>
                </div>    
                <div class="col-md-2 form-group" style="padding: 0px 0px 0px 30px;width: 120px">
                    <select class="form-control" id="Status" name="Status">
                        <option value="">Choose</option>
                        <c:forEach var="mFinanceList" items="${mFinanceItemStatus_List}" varStatus="i">
                            <c:set var="select" value="" />
                            <c:if test="${mFinanceList.id == requestScope['status']}">
                                <c:set var="select" value="selected" />
                            </c:if>
                            <option  value="${mFinanceList.id}" ${select}>${mFinanceList.name}</option>
                        </c:forEach>
                    </select>    
                </div>                    
            </div>
            <div class="sm_row col-xs-12" style="padding-top: 5px">
                <div class="col-xs-1" style="width: 695px"></div>
                 <div class="col-md-2 text-right " style="padding: 0px 0px 0px 0px">
                    <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="" class="btn btn-primary btn-primary ">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>                                          
                </div>
                <div class="col-md-1 text-right " style="padding: 0px 0px 0px 0px">
                    <button type="button" id="btnPrint" onclick="printTaxInvoiceReportSummary();" class="btn btn-default">
                        <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print
                    </button>
                </div>          
            </div>        
            <input type="hidden" id="action" name="action" value="search">
            <input type="hidden" class="form-control" id="user" name="user" value="${requestScope['user']}"/>
            </form>
            <div class="col-xs-12 form-group"></div>        
            <div class="sm_row col-xs-12 form-group"><hr/></div>
            <div class="sm_row row" >    
                <div class="sm_row col-md-12" style="padding-right: 0px;">
                    <table id="SearchTaxInvoiceTable" class="display" cellspacing="0" width="100%" style="table-layout: fixed">
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden" >Id</th>
                                <th class="hidden" >Department</th>
                                <th style="width: 8%">Tax No</th>
                                <th style="width: 9%">Date</th>
                                <th style="width: 8%">Code</th>
                                <th style="width: 16%">Name</th>
                                <th style="width: 10%">Invoice No</th>
                                <th style="width: 8%">Receipt No</th>
                                <th style="width: 11%">Gross</th>
                                <th style="width: 9%">Vat</th>
                                <th style="width: 11%">Amount</th>
                                <th style="width: 7%">Status</th>
                                <th style="width: 5%">Ref</th>
                                <th style="width: 5%">Act</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="taxInvoice" items="${taxInvoiceView_List}" varStatus="i">
                                <tr id="${taxInvoice.taxId}"> 
                                    <th class="hidden">${taxInvoice.taxId}</th>
                                    <th class="hidden">${taxInvoice.department}</th>
                                    <td align="center">${taxInvoice.taxNo}</td>
                                    <td align="center">${taxInvoice.taxDate}</td>
                                    <td>${taxInvoice.taxTo}</td>
                                    <td>${taxInvoice.name}</td>
                                    <td>${taxInvoice.invoiceNo}</td>
                                    <td>${taxInvoice.receiptNo}</td>
                                    <td class="money" align="right">${taxInvoice.totalGross}</td>
                                    <td class="money" align="right">${taxInvoice.totalVat}</td>
                                    <td class="money" align="right">${taxInvoice.totalAmount}</td>
                                    <td align="center">${taxInvoice.status}</td>
                                    <td align="center">${taxInvoice.isProfit}</td>
                                    <td align="center" > 
                                        <center> 
                                        <span id="spanEdit${i.count}" class="glyphicon glyphicon-edit editicon"      
                                          onclick="EditTaxInvoice('${taxInvoice.taxId}','${taxInvoice.taxNo}','${taxInvoice.department}')" data-toggle="modal">
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
                                        
<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
//        if($("#InputFromDate").val() !== ''){
//            var date = $("#InputFromDate").val();
//            $("#InputFromDate").val(convertFormatDate(date));
//        }
//        if($("#InputToDate").val() !== ''){
//            var date = $("#InputToDate").val();
//            $("#InputToDate").val(convertFormatDate(date));
//        }
        $(".money").mask('000,000,000,000.00', {reverse: true});
        $('.date').datetimepicker();
        $('.datemask').mask('00-00-0000');
        $('.spandate').click(function () {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        
        var table = $('#SearchTaxInvoiceTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false
//            "aaSorting": [[ 0, "desc" ]]
        });
        
        $('#SearchTaxInvoiceTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#SearchTaxInvoiceTable tbody tr.row_selected').attr("id"));
            }
        });
        
        //validate date
        $('#FromDate').datetimepicker().on('dp.change', function (e) {
            $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputFromDate');
        });
        $('#ToDate').datetimepicker().on('dp.change', function (e) {
            $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputToDate');
        });

        $("#TaxInvoiceSearchForm").bootstrapValidator({
                    framework: 'bootstrap',
    //                container: 'tooltip',
                    feedbackIcons: {
                        valid: 'uk-icon-check',
                        invalid: 'uk-icon-times',
                        validating: 'uk-icon-refresh'
                    },
                    fields: {
                        InputFromDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date From is required'
                                },
                                date: {
                                    format: 'DD-MM-YYYY',
                                    max: 'InputToDate',
                                    message: 'The Date From is not a valid'
                                }
                            }
                        },
                        InputToDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date From is required'
                                },
                                date: {
                                    format: 'DD-MM-YYYY',
                                    min: 'InputFromDate',
                                    message: 'The Date To is not a valid'
                                }
                            }
                        }
                    }
                }).on('success.field.fv', function (e, data) {
            if (data.field === 'InputFromDate' && data.fv.isValidField('InputToDate') === false) {
                data.fv.revalidateField('InputToDate');
            }

            if (data.field === 'InputToDate' && data.fv.isValidField('InputFromDate') === false) {
                data.fv.revalidateField('InputFromDate');
            }
        });
        
        $('.fromdate').datetimepicker().change(function(){                          
            checkFromDateField();
        });
        $('.todate').datetimepicker().change(function(){                          
            checkToDateField();
        });
        
    });   
    
    function EditTaxInvoice(taxId,taxNo,department){
        var page = "";
        if(department === 'Inbound'){
            page = 'I';
        } else if(department === 'Outbound'){
            page = 'O';
        } else {
            page = 'W';
        }
        window.location = ("TaxInvoice"+page+".smi?action=edit&TaxInvId="+taxId+"&TaxInvNo="+taxNo+"&department="+department);
    }
    
    function printTaxInvoiceReportSummary(){
        var fromdate = document.getElementById("InputFromDate").value;
        var todate= document.getElementById("InputToDate").value;
        var department = document.getElementById("Department").value;
        var systemuser = document.getElementById("user").value;
        var status = document.getElementById("Status");
        var strStatus = status.options[status.selectedIndex].text;
        if(strStatus === 'Choose'){
            strStatus = "";
        }
        if(department === 'WendyOutbound'){
            department = 'Wendy,Outbound';
        }
        if((fromdate === '') || (todate === '')){
           validateDate();
        } else {
            window.open("report.smi?name=TaxInvoiceSummaryReport&fromdate="+fromdate+"&todate="+todate+"&department="+department+"&comfirm="+strStatus+"&systemuser="+systemuser);
        }    
    }
  
    function checkFromDateField(){
        var inputFromDate = document.getElementById("InputFromDate");
        if(inputFromDate.value === ''){          
            $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputFromDate');
            $("#btnPrint").addClass("disabled");         
        } else {
            $("#btnPrint").removeClass("disabled");
            checkDateValue("from","");
        }      
    }
    
    function checkToDateField(){
        var InputToDate = document.getElementById("InputToDate");
        if(InputToDate.value === ''){
            $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputToDate');
            $("#btnPrint").addClass("disabled");  
        }else{
            $("#btnPrint").removeClass("disabled");
            checkDateValue("to","");
        }               
    }
    
    function checkDateValue(date){
        var inputFromDate = document.getElementById("InputFromDate");
        var InputToDate = document.getElementById("InputToDate");
        if((inputFromDate.value !== '') && (InputToDate.value !== '')){
            var fromDate = (inputFromDate.value).split('-');
            var toDate = (InputToDate.value).split('-');
            if((parseInt(fromDate[2])) > (parseInt(toDate[2]))){
                validateDate(date,"over");
            }else if(((parseInt(fromDate[2])) >= (parseInt(toDate[2]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))){
                validateDate(date,"over");
            }else if(((parseInt(fromDate[2])) >= (parseInt(toDate[2]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[0])) > (parseInt(toDate[0]))){
                validateDate(date,"over");
            }else{
                $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputFromDate');
                $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputToDate');
            }          
        }
    }
    
    function validateDate(date,option){
        if(option === 'over'){
            if(date === 'from'){
                $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputFromDate');
                $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputToDate');
            }
            if(date === 'to'){
                $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputFromDate');
                $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputToDate');
            }           
            $("#btnPrint").addClass("disabled");
        } else {
            $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputFromDate');
            $('#TaxInvoiceSearchForm').bootstrapValidator('revalidateField', 'InputToDate');
            $("#btnPrint").addClass("disabled");
        }
    }
</script>