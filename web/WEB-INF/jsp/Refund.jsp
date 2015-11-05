<%-- 
    Document   : Refund
    Created on : Feb 25, 2015, 11:28:32 AM
    Author     : top
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="booking_size" value="${requestScope['BookingSize']}"/>
<c:set var="master" value="${requestScope['Master']}"/>
<c:set var="staff" value="${requestScope['Staff']}"/>
<c:set var="action" value="${requestScope['Action']}"/>
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<c:set var="listRefundBy" value="${requestScope['listRefundBy']}"/>
<c:set var="listReceiveBy" value="${requestScope['listReceiveBy']}"/>
<c:set var="refundbyidDefault" value="${requestScope['refundbyidDefault']}"/>
<c:set var="refundbyDefault" value="${requestScope['refundbyDefault']}"/>
<c:set var="refundnameDefault" value="${requestScope['refundnameDefault']}"/>
<c:set var="create" value="${requestScope['thisdate']}" />
<c:set var="listRefundTicket" value="${requestScope['listRefundTicket']}" />
<c:set var="listTicketNo" value="${requestScope['listTicketNo']}" />

<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<!--Header-->
<section class="content-header" >
    <h1>
        Booking - Refund
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">Refund</a></li>
    </ol>
</section>
<!--content-->
<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
        <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Book/BookMenu.html'"></div>
            <input hidden="" value="${booking_size[0]}" id="input-airticket_size">
            <input hidden="" value="${booking_size[1]}" id="input-hotel_size">
            <input hidden="" value="${booking_size[2]}" id="input-other_size">
            <input hidden="" value="${booking_size[3]}" id="input-land_size">
            <input hidden="" value="${booking_size[4]}" id="input-passenger_size">
            <input hidden="" value="${booking_size[5]}" id="input-billable_size">
            <input hidden="" value="${booking_size[6]}" id="input-daytour_size">  
        </div>
        <div class="col-sm-10">
            <input type="hidden" value="${master.customer.MInitialname.name}" id="initialname_tmp">
            <input type="hidden" value="${master.customer.firstName}" id="firstname_tmp">
            <input type="hidden" value="${master.customer.lastName}" id="lastname_tmp">  
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>       
            <input type="hidden" value="${param.referenceNo}" id="getUrl" >
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
            <form action="Refund.smi" method="post" id="RefundForm" role="form">
                <div class="row" style="padding-left: 15px">  
                    <div class="col-md-6">
                        <h4>Refund Ticket</h4>
                    </div>
                    <div class="col-md-3 text-right">
                        <button type="button" onclick="" class="btn btn-default">
                            <span class="glyphicon glyphicon-print"></span> Print
                        </button>
                    </div>
                    <div class="col-md-1 text-right">
                        <button type="button" onclick="addRefundDetail(1);" class="btn btn-primary" id="buttonAddRefundDetail" name="buttonAddRefundDetail" >
                            <span class="glyphicon glyphicon-plus"></span> Add
                        </button>
                         <input type="hidden" id="countListAdd" name="countListAdd" value="1" >
                    </div>
                    <div class="col-md-2 text-right" style="margin-left: 0px;">
                        <a  href="AirTicket.smi?referenceNo=${param.referenceNo}&action=edit" 
                            class="btn btn-primary">
                            <span class="glyphicon glyphicon-arrow-left"></span> Back
                        </a>
                    </div>
                </div>
                <hr/>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Passenger</h3>
                    </div>
                    <div class="panel-body">
                        <!-- Refund Table --> 
                        <div class="row-fluid">
                            <input type="text" class="hidden" id="refundid" name="refundid" value="0" /> 
                            <table  class="display" id="RefundTable">
                                <thead>
                                    <tr class="datatable-header">
                                        <th style="width: 15%"  class="hidden">id air ticket refund</th>
                                        <th style="width: 15%" >Refund No</th>
                                        <th style="width: 15%" >Refund By</th>
                                        <th style="width: 10%" >Refund Date</th>
                                        <th style="width: 15%" >Receive</th>
                                        <th style="width: 15%"  >Change</th>
                                        <th>Detail</th>
                                        <th style="width: 8%" >Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${listRefundTicket != null}"> 
                                        <c:forEach var="table" items="${listRefundTicket}" varStatus="status">
                                        <c:set var="counter" value="${status.count}"></c:set>  
                                            <tr>
                                                <td class="hidden"><input type="text" id="airticketrefundid${status.count}" name="airticketrefundid${status.count}" value="${table.airticketrefundid}" /></td>
                                                <td><c:out value="${table.refundno}" /></td>
                                                <td><c:out value="${table.refundby}" /></td>
                                                <td><c:out value="${table.refunddate}" /></td>
                                                <td><c:out value="${table.receiveby}" /></td>
                                                <td class="text-right "><c:out value="${table.change}" /></td>
                                                <td><c:out value="${table.detail}" /></td>
                                                <td class="text-center">
                                                    <span class="glyphicon glyphicon-edit editicon" id="SpanEdit${status.count}" onclick="selectRefundDetail(${table.airticketrefundid},${status.count})"></span>
                                                    <a class="carousel" data-target="#DeleteRefund" onclick="DeleteRefund(${status.count},${table.refundno})" data-toggle="modal">
                                                       <span class="glyphicon glyphicon-remove deleteicon"></span>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                   <input type="hidden" id="counterTableRefund" name="counterTableRefund" value="${counter}" >
                                </tbody>
                            </table>  
                        </div>
                        <hr/>
                        <div class="row hidden" style="margin-top: 20px" id="RefundTicketDetail1" name="RefundTicketDetail1" >
                            <div class="row">
                                <div class="col-sm-6 form-group" style="margin-left: 20px;">
                                     <h4>Refund Ticket Detail</h4>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label for="Owner" class="col-sm-3 control-label text-right">Refund By</label>
                                    <div class="col-lg-4">
                                        <div class="">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" name="refundById" id="refundById" value="">
                                                <input type="text" class="form-control" id="refundBy" name="refundBy" value=""
                                                       data-bv-notempty data-bv-notempty-message="The By is required">
                                                <span class="input-group-addon" data-toggle="modal" data-target="#refundCustModal">
                                                    <span class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-5">  
                                        <input type="text" class="form-control" id="refundByName" name="refundByName" value="" readonly="">
                                    </div>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label  class="col-sm-3 control-label text-right">Refund Date</label>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <div class='input-group date' id='datetimepicker3'>
                                                <input type='text' class="form-control" name="refundDate" id="refundDate" data-date-format="YYYY-MM-DD" value="${booking.deadline}"  placeholder="YYYY-MM-DD"/>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label for="Owner" class="col-sm-3 control-label text-right">Receive By</label>
                                    <div class="col-lg-4">
                                        <div class="">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" name="receiveById" id="receiveById" value="${refundbyidDefault}">
                                                <input type="text" class="form-control" id="receiveBy" name="receiveBy" value="${refundbyDefault}"
                                                       data-bv-notempty data-bv-notempty-message="The By is required">
                                                <span class="input-group-addon" data-toggle="modal" data-target="#receiveUserModal">
                                                    <span class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-5">  
                                        <input type="text" class="form-control" id="receiveByName" name="receiveByName" value="${refundnameDefault}" readonly="">
                                    </div>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label  class="col-sm-3 control-label text-right">Receive Date</label>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <div class='input-group date' id='datetimepicker3'>
                                                <input type='text' class="form-control" name="refundDate" id="refundDate" data-date-format="YYYY-MM-DD" value="${create}"  placeholder="YYYY-MM-DD"/>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>                    
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label class="col-sm-3 control-label text-right">Address</label>
                                    <div class="col-sm-9">                                      
                                        <div class="form-group">
                                            <textarea class="form-control" id="address"  name="address"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label class="col-sm-3 control-label text-right">Cancel Detail</label>
                                    <div class="col-sm-9">                                      
                                        <div class="form-group">
                                            <textarea class="form-control" id="cancelDetail" name="cancelDetail"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>  
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label  class="col-sm-3 control-label text-right">Charge</label>
                                    <div class="col-sm-9">  
                                        <input type="text" class="form-control" value="${booking.reConfirm}" maxlength="255" id="charge" name="charge"/>
                                    </div>
                                </div>
                            </div> 
                            </br>
                            <div class="row">
                                <div class="col-sm-12 form-group text-center">
                                    <input type="text" class="hidden" id="counterTable" name="counterTable" value="1" >
                                    <table  class="display" id="RefundTicketDetailTable" style="width: 1000px;">
                                        <thead>
                                            <tr class="datatable-header">
                                                <th style="width: 5%" >No</th>
                                                <th style="width: 25%">Ticket No</th>
                                                <th style="width: 25%">Section</th>
                                                <th style="width: 25%">section refund</th>
                                                <th style="width: 15%">Change</th>
                                                <th style="width: 5%" >Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
<!--                                            <tr>
                                                <td>1</td>
                                                <td>2172305640387</td>
                                                <td>BKK-HND-GTH-BKK</td>
                                                <td>GTH-BKK</td>
                                                <td>25090</td>
                                                <td class="text-center">
                                                    <a class="carousel" data-toggle="collapse" data-parent="#accordion" 
                                                       data-target="#passenger1" aria-expanded="true" 
                                                       aria-controls="collapseExample">
                                                        <span class="glyphicon glyphicon-remove deleteicon"></span>
                                                    </a>
                                                </td>
                                            </tr>-->
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-5 form-group text-right">
                                    <button type="submit" class="btn btn-primary"><span class="fa fa-print"></span> Print</button>
                                </div>
                                <div class="col-sm-1 form-group text-right">
                                    <button type="submit" class="btn btn-success"><span class="fa fa-save"></span> Save</button>
                                </div>
                                <div class="col-sm-6 form-group text-left">
                                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-remove deleteicon"></span> Close </button>
                                </div>
                            </div>  
                        </div>
                        <!--Refund Add --> 
                        <div class="row hidden" style="margin-top: 20px" id="RefundTicketDetailAdd1" name="RefundTicketDetailAdd1" >
                            <div class="row">
                                <div class="col-sm-6 form-group" style="margin-left: 20px;">
                                     <h4>Refund Ticket Detail</h4>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label for="Owner" class="col-sm-3 control-label text-right">Refund By</label>
                                    <div class="col-lg-4">
                                        <div class="">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" name="refundById" id="refundById" value="">
                                                <input type="text" class="form-control" id="refundBy" name="refundBy" value=""
                                                       data-bv-notempty data-bv-notempty-message="The By is required">
                                                <span class="input-group-addon" data-toggle="modal" data-target="#refundCustModal">
                                                    <span class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-5">  
                                        <input type="text" class="form-control" id="refundByName" name="refundByName" value="" readonly="">
                                    </div>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label  class="col-sm-3 control-label text-right">Refund Date</label>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <div class='input-group date' id='datetimepicker3'>
                                                <input type='text' class="form-control" name="refundDate" id="refundDate" data-date-format="YYYY-MM-DD" value="${booking.deadline}"  placeholder="YYYY-MM-DD"/>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label for="Owner" class="col-sm-3 control-label text-right">Receive By</label>
                                    <div class="col-lg-4">
                                        <div class="">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" name="receiveById" id="receiveById" value="${refundbyidDefault}">
                                                <input type="text" class="form-control" id="receiveBy" name="receiveBy" value="${refundbyDefault}"
                                                       data-bv-notempty data-bv-notempty-message="The By is required">
                                                <span class="input-group-addon" data-toggle="modal" data-target="#receiveUserModal">
                                                    <span class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-5">  
                                        <input type="text" class="form-control" id="receiveByName" name="receiveByName" value="${refundnameDefault}" readonly="">
                                    </div>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label  class="col-sm-3 control-label text-right">Receive Date</label>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <div class='input-group date' id='datetimepicker3'>
                                                <input type='text' class="form-control" name="refundDate" id="refundDate" data-date-format="YYYY-MM-DD" value="${create}"  placeholder="YYYY-MM-DD"/>
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div> 
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label class="col-sm-3 control-label text-right">Address</label>
                                    <div class="col-sm-9">                                      
                                        <div class="form-group">
                                            <textarea class="form-control" id="address"  name="address"></textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label class="col-sm-3 control-label text-right">Cancel Detail</label>
                                    <div class="col-sm-9">                                      
                                        <div class="form-group">
                                            <textarea class="form-control" id="cancelDetail" name="cancelDetail"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>  
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label  class="col-sm-3 control-label text-right">Charge</label>
                                    <div class="col-sm-9">  
                                        <input type="text" class="form-control" value="${booking.reConfirm}" maxlength="255" id="charge" name="charge"/>
                                    </div>
                                </div>
                            </div> 
                            </br>
                            <div class="row">
                                <div class="col-sm-12 form-group text-center">
                                    <table  class="display" id="RefundTable" style="width: 1000px;">
                                        <thead>
                                            <tr class="datatable-header">
                                                <th style="width: 5%" >No</th>
                                                <th>Ticket No</th>
                                                <th>Section</th>
                                                <th>section refund</th>
                                                <th>Change</th>
                                                <th style="width: 5%" >Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>2172305640387</td>
                                                <td>BKK-HND-GTH-BKK</td>
                                                <td>GTH-BKK</td>
                                                <td>25090</td>
                                                <td class="text-center">
                                                    <a class="carousel" data-toggle="collapse" data-parent="#accordion" 
                                                       data-target="#passenger1" aria-expanded="true" 
                                                       aria-controls="collapseExample">
                                                        <span class="glyphicon glyphicon-remove deleteicon"></span>
                                                    </a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-5 form-group text-right">
                                    <button type="submit" class="btn btn-primary"><span class="fa fa-print"></span> Print</button>
                                </div>
                                <div class="col-sm-1 form-group text-right">
                                    <button type="submit" class="btn btn-success"><span class="fa fa-save"></span> Save</button>
                                </div>
                                <div class="col-sm-6 form-group text-left">
                                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-remove deleteicon"></span> Close </button>
                                </div>
                            </div>  
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!--MODAL STAFF-->
<div class="modal fade" id="DeleteRefund" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Refund</h4>
            </div>
            <div class="modal-body" id="textDeleteRefund">
                <h5 class="modal-title">Are you Delete Refund</h5>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="action" value="${action}">
                <button type="button" onclick="DeleteRefundConfirm()" class="btn btn-danger" data-dismiss="modal">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<div class="modal fade" id="RefundModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Confirm Refund</h4>
            </div>
            <div class="modal-body">are you sure refund ?</div>
            <div class="modal-footer">
                <button type="button"  class="btn btn-warning">Confirm</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal --> 
<!--Modal  Customer-->
<div class="modal fade" id="refundCustModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Refund By</h4>
            </div>
            <div class="modal-body">
                <div style="text-align: right"> 
                    <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchCustFrom" name="searchCustFrom"/> 
                </div> 
                <table class="display" id="refundCustTable">
                    <thead >   
                        <tr class="datatable-header">
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${listRefundBy}">
                            <tr onclick="setBillValue('${item.billTo}', '${item.billName}', '${item.address}', '${item.term}', '${item.pay}');">
                                <td class="item-billto">${item.billTo}</td>
                                <td class="item-name">${item.billName}</td>                                
                                <td class="item-address hidden">${item.address}</td>
                                <td class="item-tel hidden">${item.tel}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="rrefundCustModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--Modal  User-->
<div class="modal fade" id="receiveUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Receive User</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="receiveUserTable">
                    <thead class="datatable-header">                     
                        <tr>
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                            <th class="hidden">Fax</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        user = [];
                    </script>
                    <c:forEach var="a" items="${listReceiveBy}">
                        <tr>
                            <td class="user-id hidden">${a.id}</td>
                            <td class="user-user">${a.username}</td>
                            <td class="user-name">${a.name}</td>
                            <td class="user-addr hidden">${a.name}</td>
                            <td class="user-tel hidden">${a.tel}</td>
                            <td class="user-fax hidden">${a.tel}</td>
                        </tr>
                        <script>
                            user.push({id: "${a.id}", code: "${a.username}", name: "${a.name}",
                                address: "${a.name}", tel: "${a.tel}", fax: "${a.tel}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="receiveUserModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--style-->
<style>
    .dataTables_wrapper {
        position: relative;
        min-height: 100px;
        clear: both;
    }
</style>
<script type="text/javascript" charset="utf-8">
    var selectTicketNo = "<option value='' ></option>";
    $(document).ready(function () {
        <c:forEach var="cur" items="${listTicketNo}">
            selectTicketNo += "<option value='${cur.id}${cur.series1}${cur.series2}${cur.series3}' ><c:out value='${cur.id}${cur.series1}${cur.series2}${cur.series3}' /></option>";      
        </c:forEach>
         console.log("TicketNo :" + selectTicketNo);
    }); 
    
    function setBillValue(billto, billname, address, term, pay) {

    $("#refundBy").val(billto);
    $("#refundByName").val(billname);
    $("#refundCustModal").modal('hide');
}


function searchCustomerAutoList(name) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getAutoListBillto';

    var url = 'AJAXServlet';
    var billArray = [];
    var billListId = [];
    var billListName = [];
    var billListAddress = [];
    var billid, billname, billaddr;
    $("#refundBy").autocomplete("destroy");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            beforeSend: function () {
                $("#dataload").removeClass("hidden");
            },
            success: function (msg) {
                var billJson = JSON.parse(msg);
                var billselect = $("#refundBy").val();
                for (var i in billJson) {
                    if (billJson.hasOwnProperty(i)) {
                        billid = billJson[i].id;
                        billname = billJson[i].name;
                        billaddr = billJson[i].address;
                        billArray.push(billid);
                        billArray.push(billname);
                        billListId.push(billid);
                        billListName.push(billname);
                        billListAddress.push(billaddr);
                        if ((billselect === billid) || (billselect === billname)) {
                            $("#refundBy").val(billListId[i]);
                            $("#refundByName").val(billListName[i]);
                        }
                    }
                    $("#dataload").addClass("hidden");
                }
                // $("#refundBy").val(billid);
                //$("#refundByName").val(billname);

                $("#refundBy").autocomplete({
                    source: billArray,
                    close: function () {
                        $("#refundBy").trigger("keyup");
                        var billselect = $("#refundBy").val();
                        for (var i = 0; i < billListId.length; i++) {
                            if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                                $("#refundBy").val(billListId[i]);
                                $("#refundByName").val(billListName[i]);
                            }
                        }
                    }
                });

                var billval = $("#refundBy").val();
                for (var i = 0; i < billListId.length; i++) {
                    if (billval == billListName[i]) {
                        $("#refundBy").val(billListId[i]);
                    }
                }
                if (billListId.length == 1) {
                    showflag = 0;
                    $("#refundBy").val(billListId[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $("#refundBy").trigger(event);

            }, error: function (msg) {
                console.log('auto ERROR');
                $("#dataload").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

function searchCustomerAgentList(name) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getListBillto';
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                $('#refundCustTable').dataTable().fnClearTable();
                $('#refundCustTable').dataTable().fnDestroy();
                $("#refundCustTable tbody").empty().append(msg);

                $('#refundCustTable').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bPaginate": true,
                    "bInfo": false,
                    "bLengthChange": false,
                    "iDisplayLength": 10
                });
                $("#ajaxload").addClass("hidden");

            }, error: function (msg) {
                $("#ajaxload").addClass("hidden");
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function selectRefundDetail(airbookingid,counter){
    $("#RefundTicketDetailAdd"+counter).addClass("hidden");
    var count = document.getElementById('counterTableRefund');
    for(var i = 1 ; i <= count.value ; i++){
        $("#RefundTicketDetail"+i).addClass("hidden");
    }
    $('#SpanEdit'+ counter).click(function() {

        if($("#RefundTicketDetail"+counter).hasClass("hidden")){
            $("#RefundTicketDetail"+counter).removeClass("hidden");
        }else{
            $("#RefundTicketDetail"+counter).addClass("hidden");
        }
    });
}

function addRefundDetail(counter){
    var count = document.getElementById('counterTableRefund');
    for(var i = 1 ; i <= count.value ; i++){
        $("#RefundTicketDetail"+i).addClass("hidden");
    }
    var countadd = document.getElementById('countListAdd');
    for(var i = 1 ; i <= countadd.value ; i++){
        $("#RefundTicketDetailAdd"+i).addClass("hidden");
    }
    
    $("#buttonAddRefundDetail").click(function() {
           console.log("counter : " + counter);
        if($("#RefundTicketDetailAdd"+counter).hasClass("hidden")){
            console.log("Show");
            $("#RefundTicketDetailAdd"+counter).removeClass("hidden");
        }else{
            console.log("NotShow");
            $("#RefundTicketDetailAdd"+counter).addClass("hidden");
        }
    });
}

function addRowRefundTicketDetail(row,id){
    var selectTicket = "";
    selectTicket = selectTicketNo;
    console.log("Select Ticket No : " + selectTicketNo);
    $("#RefundTicketDetailTable tbody").append(
        '<tr>' +
        '<td>' + row + '</td>' +
        '<td><select id="SelectTocketNo' + row + '" name="SelectTocketNo' + row + '" class="form-control">'+ selectTicket +'</select> </td>' +
        '<td><input type="text" maxlength ="255" class="form-control" id="inputSector' + row + '" name="inputSector' + row + '" value=""></td>' +
        '<td><input type="text" class="form-control" id="inputSectorRefund' + row + '" name="inputSectorRefund' + row + '" value=""></td>' +
        '<td><input  maxlength ="15" type="text"  class="form-control numerical text-right" id="inputCharge' + row + '" name="inputCharge' + row + '" value="" ></td>' +      
        '<td class="text-center"><a class="carousel" data-toggle="collapse" data-parent="#accordion" data-target="#DeleteRefundDetail('+row+',\'\')" aria-expanded="true" ><span class="glyphicon glyphicon-remove deleteicon"></span></a></td>'+
        '</tr>'    
    );
}

function DeleteRefund(rowID,code){
    $("#refundid").val(rowID);
    if(code !== ""){
        $("#textDeleteRefund").text('Are you sure to delete refund : '+ code +'..?');
    }else{
        $("#textDeleteRefund").text('Are you sure to delete refund  ?');
    }
}

function DeleteRefundConfirm() {
    var count = $('counterTableRefund').val();
    var rowId  = $('refundid').val();
    var RefundId  = $("#airticketrefundid"+rowId).val();
    console.log("Refund ID : " + RefundId);
}
</script>

<script type="text/javascript" src="js/refund.js"></script>