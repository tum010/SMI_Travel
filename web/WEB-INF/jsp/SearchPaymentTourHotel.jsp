<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/SearchPaymentTourHotel.js"></script> 
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<c:set var="dataList" value="${requestScope['payment_list']}" />
<c:set var="PaymentTypeList" value="${requestScope['payment_type']}" />
<c:set var="InputFromDate" value="${requestScope['InputFromDate']}" />
<c:set var="InputToDate" value="${requestScope['InputToDate']}" />
<section class="content-header" >
    <h1>
        Checking - Search Payment Tour / Hotel
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Checking</a></li>          
        <li class="active"><a href="#">Search Payment Tour / Hotel</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/Checking/CheckingPackageTourHotel.html'"></div>
    </div>
    <!--Content -->
    <div class="col-sm-10">
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Search Payment Tour / Hotel</b></h4>
            </div>
        </div>
        <hr/><br>
        <!--Input Search -->
        <form action="SearchPaymentTourHotel.smi" method="post" id="PaymentSearchForm" role="form">
                <div class="row" style="padding-left: 5px" >
                    <div class="col-xs-1 text-left" style="width:70px;">
                        <label class="control-label">From<font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left:5px">
                        <div class="col-sm-12">
                            <div class='input-group date' style="width:140px;">
                                <input name="InputFromDate" id="InputFromDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${InputFromDate}" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-1 text-right" style="width:70px;">
                        <label class="control-label">To <font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left:5px">
                        <div class="col-sm-12">
                            <div class='input-group date' style="width:140px;">
                                <input name="InputToDate" id="InputToDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${InputToDate}" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-2 text-right" style="padding-left:0px;padding-right:20px;width:100px;">
                        <label class="control-label">PV Type</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right:0px;">
                        <div class="col-sm-12">
                            <select name="selectPvType" id="SelectPvType" class="form-control" value="${requestScope['selectPvType']}">
                                <option id="" value="">---type--</option>
                                <c:forEach var="type" items="${PaymentTypeList}">
                                    <option value='${type.id}'  ${type.id == requestScope['selectPvType'] ? 'selected="selected"' : ''}> ${type.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <!--Button Print and Search -->
                    <div class="col-md-3 form-group text-left" style="padding-left:0px;padding-right:0px;">
                        <div class="col-xs-6 text-left">
<!--                            <a id="ButtonSearch" name="ButtonSearch" type="submit" class="btn btn-primary">
                                <i class="fa fa-search"></i> Search
                            </a>-->
                            <button type="submit" id="ButtonSearch" name="ButtonSearch" onclick="" class="btn btn-primary btn-sm">Search</button>
                            <input type="hidden" name="action" id="action" value="search">
                            <input type="hidden" id="paymentID" name="paymentID" >
                        </div>
                        <div class="col-xs-6 text-right" style="padding-left:0px;width:60px;">
                            <button type="submit" id="ButtonPrint" name="ButtonPrint" class="btn btn-default">
                                <i class="fa fa-print"></i> Print             
                            </button>
                        </div>                        
                    </div>
                </div>
        </form><!--End Search -->
                
                <br>
        <!-- Table -->                  
            <div class="row" style="padding-left: 15px;width: 1030px;">
                <table class="display" id="SearchPaymentHotelTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width: 8%">Id</th>
                            <th style="width: 8%">PV No</th>
                            <th style="width: 10%">Pay Date</th>
                            <th style="width: 20%">PV Type</th>
                            <th style="width: 20%">Invoice Sup</th>
                            <th style="width: 5%">Acc</th>
                            
                            <th style="width: 8%">Total</th>
                            <th style="width: 8%">Status</th>
                            <th style="width: 10%">Action</th>
                        </tr>
                    </thead>
                    <tbody>
<!--                        <tr>
                            <td>41399</td>
                            <td>2015-05-05</td>
                            <td>Wendy Van</td>
                            <td>Hook In</td>
                            <td>1</td>
                            
                            <td>23,000</td>
                            <td>THB</td>
                            <td>wait</td>
                            <td class="text-center">
                                <span id="RefPaymentHotelEdit" name="RefPaymentHotelEdit" class="glyphicon glyphicon-edit editicon" onclick=""></span>
                                <a href="#" onclick=""  data-toggle="modal" data-target="">
                                    <span id="" class="glyphicon glyphicon-remove deleteicon"  onclick="" data-toggle="modal" data-target="#delSearchPaymentTourHotelModal"></span>
                                </a>
                            </td>                           
                        </tr>-->
                    <c:forEach var="payment" items="${dataList}">
                        <tr>
                            <td>${payment.id}</td>
                            <td>${payment.payNo}</td>
                            <td>${payment.payDate}</td>
                            <td>${payment.payType}</td>
                            <td>${payment.invoiceSup}</td>
                            <td>${payment.accNo}</td>
                            <td>${payment.total}</td>
                            <td>${payment.status}</td>
                            <td class="text-center">
                                <span id="RefPaymentHotelEdit" name="RefPaymentHotelEdit" class="glyphicon glyphicon-edit editicon" onclick="location.href='PaymentTourHotel.smi?action=edit&paymentId=${payment.id}'"></span>
                                <a href="#" onclick=""  data-toggle="modal" data-target="">
                                    <span id="" class="glyphicon glyphicon-remove deleteicon"  onclick="DeletePayment('${payment.id}','${payment.payNo}')" data-toggle="modal" data-target="#delSearchPaymentTourHotelModal"></span>
                                </a>
                            </td>  
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div><!--End Table -->
    </div>
</div>
<!--Delete Payment Tour Hotel Modal-->
<div class="modal fade" id="delSearchPaymentTourHotelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Tour / Hotel</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
    $(document).ready(function () {
    });
  
</script>
