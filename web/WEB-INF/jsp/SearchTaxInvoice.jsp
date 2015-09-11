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
<section class="content-header" >
    <h1>
        Finance & Cashier - Tax Invoice
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Search Tax Invoice</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app="">
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/TaxInvoiceMainMenu.html'"></div>
        </div>        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Search Tax Invoice</b></h4>
                </div>            
            </div>
            <form action="SearchTaxInvoice.smi" method="post" id="TaxInvoiceSearchForm" name="TaxInvoiceSearchForm" role="form">
            <div class="col-xs-12 ">
                <div class="col-xs-1 text-right">
                    <label class="control-label" for="">From<font style="color: red"></font>&nbsp;</lable>
                </div>
                <div class="col-md-2 form-group"> 
                    <div class='input-group date' id="FromDate">
                        <input id="InputFromDate" name="InputFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['inputFromDate']}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                         
                    </div>
                </div>
                <div class="col-xs-1 text-right">
                    <label class="control-label" for="">To<font style="color: red"></font>&nbsp;</lable>
                </div>
                <div class="col-md-2 form-group"> 
                    <div class='input-group date' id='ToDate'>                    
                        <input id="InputToDate" name="InputToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['inputToDate']}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                    </div>
                </div>
                <div class="col-xs-1 text-right" style="padding: 0px 0px 0px 20px">
                    <label class="control-label" for="">Department<font style="color: red"></font></lable>
                </div>
                <div class="col-md-2 form-group" style="padding: 0px 0px 0px 30px">
                    <select class="form-control" id="Department" name="Department">
                        <option value="">Choose</option>
                            <c:choose>
                                <c:when test="${requestScope['department'] == 'Wendy'}">
                                    <c:set var="select1" value="selected" />
                                </c:when>
                            </c:choose>
                        <option value="Wendy" ${select1}>Wendy</option>                      
                            <c:choose>
                                <c:when test="${requestScope['department'] == 'Outbound'}">
                                    <c:set var="select2" value="selected" />
                                </c:when>
                            </c:choose>
                        <option value="Outbound" ${select2}>Outbound</option>
                            <c:choose>
                                <c:when test="${requestScope['department'] == 'Inbound'}">
                                    <c:set var="select3" value="selected" />
                                </c:when>
                            </c:choose>
                         <option value="Inbound" ${select3}>Inbound</option>
                    </select>    
                </div>
                <div class="col-md-2 text-right " style="padding: 0px 0px 0px 0px">
                    <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="" class="btn btn-primary btn-primary ">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>                                          
                </div>
                <div class="col-md-1 text-right " style="padding: 0px 0px 0px 0px">
                    <button type="button" onclick="printTaxInvoiceReportSummary();" class="btn btn-default">
                        <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print
                    </button>
                </div>   
            </div>
            <input type="hidden" id="action" name="action" value="search">
            <input type="hidden" class="form-control" id="user" name="user" value="${requestScope['user']}"/>
            </form>
            <div class="col-xs-12 form-group"></div>        
            <div class="col-xs-12 form-group"><hr/></div>
            <div class="row" >    
                <div class="col-md-12">
                    <table id="SearchTaxInvoiceTable" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden" >Id</th>
                                <th class="hidden" >Department</th>
                                <th style="width: 1%">No</th>
                                <th style="width: 5%">Tax No</th>
                                <th style="width: 9%">Date</th>
                                <th style="width: 5%">Code</th>
                                <th style="width: 10%">Name</th>
                                <th style="width: 8%">Detail</th>
                                <th style="width: 7%">Invoice No</th>
                                <th style="width: 7%">Receipt No</th>
                                <th style="width: 7%">Gross</th>
                                <th style="width: 7%">Vat</th>
                                <th style="width: 7%">Amount</th>
                                <th style="width: 1%">Status</th>
                                <th style="width: 1%">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="taxInvoice" items="${taxInvoiceView_List}" varStatus="i">
                                <tr id="${taxInvoice.taxId}"> 
                                    <th class="hidden">${taxInvoice.taxId}</th>
                                    <th class="hidden">${taxInvoice.department}</th>
                                    <td align="center">${i.count}</td>
                                    <td align="center">${taxInvoice.taxNo}</td>
                                    <td align="center">${taxInvoice.taxDate}</td>
                                    <td align="center">${taxInvoice.taxTo}</td>
                                    <td align="center">${taxInvoice.name}</td>
                                    <td>${taxInvoice.detail}</td>
                                    <td>${taxInvoice.invoiceNo}</td>
                                    <td>${taxInvoice.receiptNo}</td>
                                    <td class="money" align="right">${taxInvoice.totalGross}</td>
                                    <td class="money" align="right">${taxInvoice.totalVat}</td>
                                    <td class="money" align="right">${taxInvoice.totalAmount}</td>
                                    <td align="center">${taxInvoice.status}</td>
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
        $(".money").mask('000,000,000,000.00', {reverse: true});
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
        $('.spandate').click(function () {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        
        var table = $('#SearchTaxInvoiceTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
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
                                date: {
                                    format: 'YYYY-MM-DD',
                                    max: 'InputToDate',
                                    message: 'The Date From is not a valid'
                                }
                            }
                        },
                        InputToDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                date: {
                                    format: 'YYYY-MM-DD',
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
        window.open("report.smi?name=TaxInvoiceSummaryReport&fromdate="+fromdate+"&todate="+todate+"&department="+department+"&systemuser="+systemuser);
    }
</script>