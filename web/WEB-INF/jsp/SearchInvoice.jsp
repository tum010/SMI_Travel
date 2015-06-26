<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Invoice.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
<section class="content-header" >
    <h1>
        Finance & Cashier - Search Invoice
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Search Invoice</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app="">
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/InvoiceMenu.html'"></div>
        </div>
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Search</b></h4>
                </div>            
            </div>
            <form action="" method="post" id="" name="" role="form">
                <div class="col-xs-12 ">
                    <div class="col-xs-1 text-right">
                        <label class="control-label" for="">From<font style="color: red">*</font>&nbsp;:</lable>
                    </div>
                    <div class="col-md-2 form-group"> 
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
                        <label class="control-label" for="">To<font style="color: red">*</font>&nbsp;:</lable>
                    </div>
                    <div class="col-md-2 form-group"> 
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
                    <div class="col-xs-1 text-right" style="padding: 0px 0px 0px 20px">
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
                    <div class="col-xs-1 text-right" style="padding: 0px 0px 0px 20px">
                        <label class="control-label" for="">Type<font style="color: red">*</font></lable>
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
            <div class="col-xs-12 form-group"><hr/></div>
            <div class="row" style="padding-left:35px">    
                <div class="col-md-12">
                    <table id="MasterInvoice" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <th style="width: 5%" >Invoice No</th>
                                <th style="width: 20%" >Department</th>
                                <th style="width: 15%">Date</th>
                                <th style="width: 15%">To</th>
                                <th style="width: 40%">Name</th>
                                <th style="width: 1%">Sum Cost</th>
                                <th style="width: 1%">Sum Amount</th>
                                <th style="width: 1%">Receive Amount</th>
                                <th style="width: 1%">Term Pay</th>
                                <th style="width: 1%">Action</th>
                            </tr>
                        </thead>
                        <tbody>               
                                <tr>
                                    <td align="center">111111</td>
                                    <td>ABC</td>
                                    <td align="center">2015-01-01</td>
                                    <td align="center">2015-09-30</td>
                                    <td>Mr.Test Testman</td>
                                    <td align="center">100000</td>
                                    <td align="center">100000</td>
                                    <td align="center">100000</td>
                                    <td align="center">100000</td>
                                    <td align="center" > 
                                        <center> 
                                        <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      
                                          onclick="EditBank('${table.id}', '${table.code}', '${table.name}', '${table.branch}', '${table.accNo}', '${table.accType}')" data-toggle="modal" data-target="#BankModal" >
                                        </span>
                                        <span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteBank('${table.id}', '${table.code}', '${table.name}')" data-toggle="modal" data-target="#DelBank" >  </span>
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
        var table = $('#MasterInvoice').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });
        
        $('#MasterInvoice tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#MasterInvoice tbody tr.row_selected').attr("id"));
            }
        });
    });   
</script>