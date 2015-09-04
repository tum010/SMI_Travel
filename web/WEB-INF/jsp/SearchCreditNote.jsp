<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/SearchCreditNote.js"></script>--> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
<section class="content-header" >
    <h1>
        Finance & Cashier - Credit Note
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Search Credit Note</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app="">
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/CreditNoteMainMenu.html'"></div>
        </div>
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Search Credit Note</b></h4>
                </div>            
            </div>
            <hr/>
            <form action="SearchCreditNote.smi" method="post" id="SearchCreditNoteForm" name="SearchCreditNoteForm" role="form">
                <input type="hidden" name="action" value="search" />
                <div class="panel panel-default">
                    <div class="panel-body"  style="width: 100%">
                        <div class="col-xs-3 form-group" style="padding-top: 15px">
                            <div class="col-xs-1 text-right"  style="width: 70px; margin-left: -20px">
                                <label class="control-label text-right">Form<font style="color: red">*</font>&nbsp;</label>
                            </div>
                            <div class="col-xs-1"  style="width: 170px">
                                <div class='input-group date' id="dateFrom">
                                    <input id="iDateFrom" name="iDateFrom"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['iDateFrom']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-3 form-group" style="padding-top: 15px">
                            <div class="col-xs-1 text-right"  style="width: 70px; margin-left: -20px">
                                <label class="control-label text-right">To<font style="color: red">*</font>&nbsp;</label>
                            </div>
                            <div class="col-xs-1"  style="width: 170px">
                                <div class='input-group date' id="dateTo" >
                                    <input id="iDateTo" name="iDateTo"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['iDateTo']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 form-group" style="padding-top: 15px">
                            <div class="col-xs-1 text-right" style="width: 80px">
                                <label class="control-label text-right">Department</label>
                            </div>
                            <div class="col-md-2 form-group" style="margin-left: 20px">
                                 <select class="form-control" id="department" name="department" style="width: 100px">
                                    <option value="W">Wendy</option>
                                    <option value="O">Outbound</option>
                                    <option value="I">Inbound</option>
                                </select>    
                            </div>
                            <div class="col-md-1 text-right " style="padding: 0px 0px 0px 0px ; width: 160px">
                                <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="" class="btn btn-primary btn-primary ">
                                    <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                                </button>                                
                            </div>
                            <div class="col-md-1 text-right " style="padding: 0px 0px 0px 0px">
                                <button type="button" onclick="printCreditNoteSummaryReport();" class="btn btn-default">
                                    <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print
                                </button>                                
                            </div>
                        </div>
                    </div>
                </div>
                
                <!--Table-->
                <div class="row">
                    <div class="col-md-12 ">
                        <table id="CreditNoteTable" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr class="datatable-header" >
                                    <th style="width:10%;">CN No.</th>
                                    <th style="width:10%;">Date</th>
                                    <th style="width:20%;">Name</th>
                                    <th style="width:10%;">AP Code</th>
                                    <th style="width:10%;">Department</th>
                                    <th style="width:10%;">Sub Total</th>
                                    <th style="width:10%;">Grand Total</th>
                                    <th style="width:10%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="creditNote" items="${creditNoteList}">
                                <tr>
                                    <td align="center">${creditNote.cnno}</td>
                                    <td align="center">${creditNote.cndate}</td>
                                    <td align="center">${creditNote.cnname}</td>
                                    <td align="center">${creditNote.apCode}</td>
                                    <td align="center">${creditNote.department}</td>
                                    <td align="right">${creditNote.subtotal}</td>
                                    <td align="right">${creditNote.grandTotal}</td>
                                    <td> 
                                        <center> 
                                            <a  href="CreditNote${creditNote.department}.smi?action=search&cnNo=${creditNote.cnno}">
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
            </form>
         </div>
     </div>
</div> 

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $('.date').datetimepicker();
        var table = $('#CreditNoteTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false
        });

        $('#CreditNoteTable tbody').on('click', 'tr', function() {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#CreditNoteTable tbody tr.row_selected').attr("id"));
            }
        });
        //validate date
        $('#dateFrom').datetimepicker().on('dp.change', function (e) {
            $('#SearchCreditNoteForm').bootstrapValidator('revalidateField', 'iDateFrom');
        });
        $('#dateTo').datetimepicker().on('dp.change', function (e) {
            $('#SearchCreditNoteForm').bootstrapValidator('revalidateField', 'iDateTo');
        });
        
        
    $("#SearchCreditNoteForm")
            .bootstrapValidator({
                framework: 'bootstrap',
//                container: 'tooltip',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    iDateFrom: {
                        trigger: 'focus keyup change',
                        validators: {
                            notEmpty: {
                                message: 'The Date From is required'
                            },
                            date: {
                                format: 'YYYY-MM-DD',
                                max: 'iDateTo',
                                message: 'The Date From is more than  date to'
                            }
                        }
                    },
                    iDateTo: {
                        trigger: 'focus keyup change',
                        validators: {
                            notEmpty: {
                                message: 'The Date To is required'
                            },
                            date: {
                                format: 'YYYY-MM-DD',
                                min: 'iDateFrom',
                                message: 'The Date To is less than date from'
                            }
                        }
                    }
                }
            }).on('success.field.fv', function (e, data) {
                if (data.field === 'iDateFrom' && data.fv.isValidField('iDateTo') === false) {
                    data.fv.revalidateField('iDateTo');
                }

                if (data.field === 'iDateTo' && data.fv.isValidField('iDateFrom') === false) {
                    data.fv.revalidateField('iDateFrom');
                }
            });
    });

function searchAction() {
    var action = document.getElementById('action');
    action.value = 'search';
    var dateForm = document.getElementById('iDateFrom');
    dateForm.value = $("#iDateFrom").val();
    var dateTo = document.getElementById('iDateTo');
    dateTo.value = $("#iDateTo").val();
    var cnNo = document.getElementById('cnNo');
    cnNo.value = $("#cnNo").val();
    document.getElementById('SearchCreditNoteForm').submit();
}

function printCreditNoteSummaryReport(){
    window.open("report.smi?name=CreditNoteSummaryReport");
}
</script>
