<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/SearchInvoice.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
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
                        <c:if test='${dayTourOperation.tourDate != null}'>
                            <input id="InputFromDate" name="InputFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                        </c:if>
                        <c:if test='${dayTourOperation.tourDate == null}'>
                            <input id="InputFromDate" name="InputFromDate"  type="text" 
                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['']}">
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                        </c:if>                             
                    </div>
                </div>
                <div class="col-xs-1 text-right">
                    <label class="control-label" for="">To<font style="color: red"></font>&nbsp;</lable>
                </div>
                <div class="col-md-2 form-group"> 
                    <div class='input-group date' id='ToDate'>
                        <c:if test='${dayTourOperation.tourDate != null}'>
                            <input id="InputToDate" name="InputToDate"  type="text" 
                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                               
                        </c:if>
                        <c:if test='${dayTourOperation.tourDate == null}'>
                            <input id="InputToDate" name="InputToDate"  type="text" 
                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['']}">
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                               
                        </c:if>                             
                    </div>
                </div>
                <div class="col-xs-1 text-right" style="padding: 0px 0px 0px 20px">
                    <label class="control-label" for="">Department<font style="color: red"></font></lable>
                </div>
                <div class="col-md-2 form-group" style="padding: 0px 0px 0px 30px">
                    <select class="form-control" id="Department" name="Department">
                        <option value="">Choose</option>
                            <c:choose>
                                <c:when test="${requestScope['BankAccountType'] == '1'}">
                                    <c:set var="accountOneSelected" value="selected" />
                                </c:when>
                            </c:choose>
                        <option value="1" ${accountOneSelected}>Inbound</option>
                            <c:choose>
                                <c:when test="${requestScope['BankAccountType'] == '2'}">
                                    <c:set var="accountTwoSelected" value="selected" />
                                </c:when>
                            </c:choose>
                        <option value="2" ${accountTwoSelected}>Outbound</option>
                            <c:choose>
                                <c:when test="${requestScope['BankAccountType'] == '2'}">
                                    <c:set var="accountTwoSelected" value="selected" />
                                </c:when>
                            </c:choose>
                        <option value="2" ${accountTwoSelected}>Wendy</option>
                    </select>    
                </div>
                <div class="col-md-2 text-right " style="padding: 0px 0px 0px 0px">
                    <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="" class="btn btn-primary btn-primary ">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>                                          
                </div>
                <div class="col-md-1 text-right " style="padding: 0px 0px 0px 0px">
                    <button type="button" onclick="printVoucher('');" class="btn btn-default">
                        <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print
                    </button>
                </div>   
            </div>
            <input type="hidden" id="action" name="action" value="search">        
            </form>
            <div class="col-xs-12 form-group"></div>        
            <div class="col-xs-12 form-group"><hr/></div>
            <div class="row" >    
                <div class="col-md-12">
                    <table id="SearchTaxInvoiceTable" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <th>No</th>
                                <th>Tax No</th>
                                <th>Date</th>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Detail</th>
                                <th>Invoice No</th>
                                <th>Receipt No</th>
                                <th>Gross</th>
                                <th>Vat</th>
                                <th>Amount</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>               
                                <tr>
                                    <td>XXX</td>
                                    <td>XXXXXX</td>
                                    <td>2015-01-01</td>
                                    <td>XXXXXX</td>
                                    <td>Mr.Test Testman</td>
                                    <td>XXXXXXXX</td>
                                    <td>XXX</td>
                                    <td>XXX</td>
                                    <td>XXX</td>
                                    <td>XXX</td>
                                    <td>XXX</td>
                                    <td>XXX</td>
                                    <td align="center" > 
                                        <center> 
                                        <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      
                                          onclick="EditBank('${table.id}', '${table.code}', '${table.name}', '${table.branch}', '${table.accNo}', '${table.accType}')" data-toggle="modal" data-target="#BankModal" >
                                        </span>                                       
                                        </center>
                                    </td>
                                </tr>
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
</script>