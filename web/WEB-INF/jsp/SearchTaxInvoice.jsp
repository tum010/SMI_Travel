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
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/TaxInvoiceMenu.html'"></div>
        </div>
        <div class="col-sm-10">
            <div class="panel panel-default">            
                <div class="panel-heading">
                    <label class="control-label">Search Tax</lable>
                </div>
                <div class="panel-body">
                <form action="" method="post" id="" name="" role="form">
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right">
                            <label class="control-label" for="">From<font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-2 form-group" style="width: 200px;"> 
                            <div class='input-group date' id='InputDatePicker'>
                                <c:if test='${dayTourOperation.tourDate != null}'>
                                    <input id="InputTourDetailTourDate" name="InputTourDetailTourDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${dayTourOperation.tourDate}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>

                                </c:if>
                                <c:if test='${dayTourOperation.tourDate == null}'>
                                    <input id="InputTourDetailTourDate" name="InputTourDetailTourDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['tourDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                        
                                </c:if>                             
                            </div>
                        </div>
                        <div class="col-xs-1 text-right">
                            <label class="control-label" for="">To<font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-2 form-group" style="width: 200px;">
                            <div class='input-group date' id='InputDatePicker'>
                                <c:if test='${dayTourOperation.tourDate != null}'>
                                    <input id="InputTourDetailTourDate" name="InputTourDetailTourDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${dayTourOperation.tourDate}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>

                                </c:if>
                                <c:if test='${dayTourOperation.tourDate == null}'>
                                    <input id="InputTourDetailTourDate" name="InputTourDetailTourDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['tourDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                             
                                </c:if>                             
                            </div>
                        </div>
                        <div class="col-xs-2 text-right" style="padding: 0px 0px 0px 20px">
                            <label class="control-label" for="">Department<font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-2 form-group" style="padding: 0px 0px 0px 30px">
                            <select class="form-control" id="" name="">
                                <option value="">Choose</option>
                                    <c:choose>
                                        <c:when test="${requestScope['BankAccountType'] == '1'}">
                                        <c:set var="accountOneSelected" value="selected" />
                                        </c:when>
                                    </c:choose>
                                <option value="1" ${accountOneSelected}>Account 1</option>
                                    <c:choose>
                                        <c:when test="${requestScope['BankAccountType'] == '2'}">
                                            <c:set var="accountTwoSelected" value="selected" />
                                        </c:when>
                                    </c:choose>
                                <option value="2" ${accountTwoSelected}>Account 2</option>
                            </select>    
                        </div>
                    </div>
                    <div class="col-xs-12 form-group"></div>        
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right">                        
                        </div>
                        <div class="col-md-1 text-right ">
                            <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="" class="btn btn-primary btn-primary ">
                                <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                            </button>                                          
                        </div>                   
                        <div class="col-md-2 text-right " style="padding: 0px 60px 0px 0px">
                            <button type="button" onclick="printVoucher('');" class="btn btn-default">
                                <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print
                            </button>
                        </div>    
                    </div>
                </form>
                </div>
            </div>
            <div class="row" >    
                <div class="col-md-12">
                    <table id="SearchTaxInvoice" class="display" cellspacing="0" width="100%">
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
                                        <span  class="glyphicon glyphicon-remove deleteicon"  onclick="DisableInvoice('', '')" data-toggle="modal" data-target="#DisableTaxInvoice">  </span>
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

<!--Disable-->
<div class="modal fade" id="DisableTaxInvoice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Finance & Cashier - Search Tax Invoice</h4>
            </div>
            <div class="modal-body" id="disableVoid">
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick='window.top.location.href="Invoice.smi?button=disable"'>Delete</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<!--Enable-->
<div class="modal fade" id="EnableInvoid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Finance & Cashier - Search invoice </h4>
            </div>
            <div class="modal-body" id="enableCode">
                
            </div>
            <div class="modal-footer">
                <button type="button" onclick="Enable()" class="btn btn-success">Enable</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal --> 
                                        
<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        var table = $('#SearchTaxInvoice').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });
        
        $('#SearchTaxInvoice tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#SearchTaxInvoice tbody tr.row_selected').attr("id"));
            }
        });
               
    });   
</script>