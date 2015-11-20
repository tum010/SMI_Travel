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
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
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
<c:set var="RefundTicket" value="${requestScope['RefundTicket']}" />
<c:set var="RefundTicketDetail" value="${requestScope['RefundTicketDetail']}" />
<c:set var="listTicketNo" value="${requestScope['listTicketNo']}" />
<c:set var="airbookingid" value="${requestScope['airbookingid']}" />
<c:set var="referenceNo" value="${requestScope['referenceNo']}" />
<c:set var="actionAdd" value="${requestScope['actionAdd']}" />
<c:set var="result" value="${requestScope['result']}" />

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
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Success!</strong> 
            </div>
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Not Success!</strong> 
            </div>
            <div id="textAlertDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Delete Success!</strong> 
            </div>
            <div id="textAlertNotDelete"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Delete Not Success!</strong> 
            </div>
            <input type="hidden" value="${master.customer.MInitialname.name}" id="initialname_tmp">
            <input type="hidden" value="${master.customer.firstName}" id="firstname_tmp">
            <input type="hidden" value="${master.customer.lastName}" id="lastname_tmp">  
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>       
            <input type="text" class="hidden" value="${param.referenceNo}" id="getUrl" >
            
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
            <form action="Refund.smi" method="post" id="RefundForm"  name="RefundForm"  role="form" autocomplete="off">
                <input type="hidden" name="action" value="" id="action">
                <input type="text" class="hidden" value="${actionAdd}" name="actionAdd" id="actionAdd">
                <input type="text" class="hidden" value="${referenceNo}" id="referenceNo" name="referenceNo">
                <input type="text" class="hidden"  value="${airbookingid}" id="airbookingid" name="airbookingid">
                <div class="row" style="padding-left: 15px">  
                    <div class="col-md-6">
                        <h4>Refund Ticket</h4>
                    </div>
                    <div class="col-md-3 text-right">
                        <button type="button" class="btn btn-default" onclick="printAirticketRefund();">
                            <span class="glyphicon glyphicon-print"></span> Print
                        </button>
                    </div>
                    <div class="col-md-1 text-right">
                         <a  id="SpanAdd" href="Refund.smi?referenceNo=${param.referenceNo}&airbookingid=${airbookingid}&action=add">
                            <button type="button"  class="btn btn-primary" id="buttonAddRefundDetail" name="buttonAddRefundDetail" >
                                <span class="glyphicon glyphicon-plus"></span> Add
                            </button>
                        </a>  
                         <input type="hidden" id="countListAdd" name="countListAdd" value="1" >
                    </div>
                    <div class="col-md-2 text-right" style="margin-left: 0px;">
                        <a  href="Refund.smi?referenceNo=${param.referenceNo}&airbookingid=${airbookingid}"
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
                            <table  class="display" id="RefundTable">
                                <thead>
                                    <tr class="datatable-header">
                                        <th style="width: 15%"  class="hidden">id air ticket refund</th>
                                        <th style="width: 15%" >Refund No</th>
                                        <th style="width: 15%" >Refund By</th>
                                        <th style="width: 10%" >Refund Date</th>
                                        <th style="width: 15%" >Receive</th>
                                        <th style="width: 15%" >Change</th>
                                        <th>Detail</th>
                                        <th style="width: 8%" >Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${listRefundTicket != null}"> 
                                        <c:forEach var="table" items="${listRefundTicket}" varStatus="status">
                                        <c:set var="counter" value="${status.count}"></c:set>  
                                            <tr id="${table.id}">
                                                <td class="hidden"><input type="text" id="airticketrefundid${status.count}" name="airticketrefundid${status.count}" value="${table.airticketrefundid}" /></td>
                                                <td><c:out value="${table.refundno}" /></td>
                                                <td><c:out value="${table.refundby}" /></td>
                                                <td><c:out value="${table.refunddate}" /></td>
                                                <td><c:out value="${table.receiveby}" /></td>
                                                <td class="text-right "><c:out value="${table.change}" /></td>
                                                <td><c:out value="${table.detail}" /></td>
                                                <td class="text-center">
                                                    <a  id="SpanEdit${status.count}" href="Refund.smi?referenceNo=${param.referenceNo}&airbookingid=${airbookingid}&refundid=${table.airticketrefundid}&action=searchRefund">
                                                        <span class="glyphicon glyphicon-edit editicon"  ></span>
                                                    </a>
                                                    <a class="carousel" data-target="#DeleteRefund" onclick="DeleteRefund(${status.count},${param.referenceNo},${airbookingid},${table.airticketrefundid},${table.id})" data-toggle="modal">
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
                        
                        <c:if test="${RefundTicket != null}"> 
                            <c:forEach var="table1" items="${RefundTicket}" varStatus="status1">
                            <c:set var="counter1" value="${status1.count}"></c:set>  
                            <div class="row hidden" style="margin-top: 20px" id="RefundTicketDetail" name="RefundTicketDetail" >
                                <div class="row">
                                    <div class="col-sm-6 form-group" style="margin-left: 20px;">
                                         <h4>Refund Ticket Detail</h4>
                                    </div>
                                    <div class="col-sm-6 form-group">
                                        <label for="Owner" class="col-sm-3 control-label text-right">Refund By</label>
                                        <div class="col-lg-4">
                                            <div class="">
                                                <div class="input-group" id="refundpanel">
                                                    <input type="hidden" class="form-control" name="refundById" id="refundById" value="${table1.airticketrefundid}">
                                                    <input type="hidden" class="form-control" name="refundid" id="refundid" value="${table1.id}">
                                                    <input type="text" class="form-control" id="refundBy" name="refundBy" value="${table1.refundcode}">
                                                    <input type="hidden" class="form-control" id="refundNo" name="refundNo" value="${table1.refundno}">
                                                    <span class="input-group-addon" data-toggle="modal" data-target="#refundCustModal">
                                                        <span class="glyphicon-search glyphicon"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-5" >  
                                            <input type="text" class="form-control" id="refundByName" name="refundByName" value="" readonly="">
                                        </div>
                                    </div>
                                    <div class="col-sm-6 form-group">
                                        <label  class="col-sm-3 control-label text-right">Refund Date</label>
                                        <div class="col-lg-4">
                                            <div class="form-group">
                                                <div class='input-group date' id='datetimepicker3'>
                                                    <input type='text' class="form-control" name="refundDate" id="refundDate" data-date-format="YYYY-MM-DD" value="${table1.refunddate}"  placeholder="YYYY-MM-DD"/>
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
                                                <div class="input-group" id="receivepanel">
                                                    <c:if test="${table1.receiveby == null}"> 
                                                        <input type="hidden" class="form-control" name="receiveById" id="receiveById" value="${refundbyidDefault}">
                                                        <input type="text" class="form-control" id="receiveBy" name="receiveBy" value="${refundbyDefault}">
                                                        <span class="input-group-addon" data-toggle="modal" data-target="#receiveUserModal">
                                                            <span class="glyphicon-search glyphicon"></span>
                                                        </span>
                                                    </c:if>
                                                    <c:if test="${table1.receiveby != null}"> 
                                                        <input type="hidden" class="form-control" name="receiveById" id="receiveById" value="">
                                                        <input type="text" class="form-control" id="receiveBy" name="receiveBy" value="${table1.receiveby}">
                                                        <span class="input-group-addon" data-toggle="modal" data-target="#receiveUserModal">
                                                            <span class="glyphicon-search glyphicon"></span>
                                                        </span>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-5"> 
                                            <c:if test="${table1.receiveby == null}"> 
                                                <input type="text" class="form-control" id="receiveByName" name="receiveByName" value="${refundbynameDefault}" readonly="">
                                            </c:if>
                                            <c:if test="${table1.receiveby != null}"> 
                                                <input type="text" class="form-control" id="receiveByName" name="receiveByName" value="" readonly="">
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 form-group">
                                        <label  class="col-sm-3 control-label text-right">Receive Date</label>
                                        <div class="col-lg-4">
                                            <div class="form-group" id="receivedatepanel">
                                                <div class='input-group date' id='datetimepicker4'>
                                                    <c:if test="${table1.receivedate == null}"> 
                                                        <input type='text' class="form-control datemask" name="receiveDate" id="receiveDate" data-date-format="YYYY-MM-DD" value="${create}"  placeholder="YYYY-MM-DD"/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </c:if>
                                                    <c:if test="${table1.receivedate != null}"> 
                                                        <input type='text' class="form-control datemask" name="receiveDate" id="receiveDate" data-date-format="YYYY-MM-DD" value="${table1.receivedate}"  placeholder="YYYY-MM-DD"/>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon glyphicon-calendar"></span></span>
                                                    </c:if>
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
                                                <textarea class="form-control" id="address"  name="address">${table1.address}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 form-group">
                                        <label class="col-sm-3 control-label text-right">Cancel Detail</label>
                                        <div class="col-sm-9">                                      
                                            <div class="form-group">
                                                <textarea class="form-control" id="cancelDetail" name="cancelDetail">${table1.detail}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>  
                                <div class="row">
                                    <div class="col-sm-6 form-group">
                                        <label  class="col-sm-3 control-label text-right">Charge</label>
                                        <div class="col-sm-9">  
                                            <input type="text" class="form-control" value="${table1.change}" maxlength="255" id="charge" name="charge"/>
                                        </div>
                                    </div>
                                </div> 
                                </br>
                                <div class="row">
                                    <div class="col-sm-12 form-group text-center">
                                        <input type="text" class="hidden" id="counterTable" name="counterTable" value="1" >
                                         <input type="text" class="hidden" id="refunddetailid" name="refunddetailid" value="0" />
                                        <table  class="display" id="RefundTicketDetailTable" style="width: 1000px;">
                                            <thead>
                                                <tr class="datatable-header">
                                                    <th class="hidden" >id</th>
                                                    <th style="width: 5%" >No</th>
                                                    <th style="width: 25%">Ticket No</th>
                                                    <th style="width: 25%">Section</th>
                                                    <th style="width: 25%">section refund</th>
                                                    <th style="width: 15%">Change</th>
                                                    <th style="width: 5%" >Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="tableDetail" items="${RefundTicketDetail}" varStatus="statusDetail">
                                                    <tr>
                                                        <td class="hidden"><input type="text" id="airticketrefunddetailid${statusDetail.count}" name="airticketrefunddetailid${statusDetail.count}" value="${tableDetail.refunddetailid}" /></td>
                                                        <td>${statusDetail.count}</td>
                                                        <td>
                                                            <select id="SelectTocketNo${statusDetail.count}" name="SelectTocketNo${statusDetail.count}" class="form-control" onchange="setSectorRefund(${statusDetail.count});">
                                                                <option value='' ></option>
                                                                <c:forEach var="typeP" items="${listTicketNo}">
                                                                    <c:set var="selectTic" value="" />
                                                                    <c:set var="tickno" value="" />
                                                                    <c:if test="${tableDetail.ticketid == typeP.id}">
                                                                        <c:set var="selectTic" value="selected" />
                                                                    </c:if> 
                                                                    <option value='${typeP.id}' ${selectTic}>${typeP.series1}${typeP.series2}${typeP.series3}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                        <td>
                                                            <input type="text" maxlength ="255" class="form-control" id="inputSector${statusDetail.count}" name="inputSector${statusDetail.count}" value="${tableDetail.sector}"></td>
                                                        </td>
                                                        <td>
                                                            <input type="text" class="form-control" id="inputSectorRefund${statusDetail.count}" name="inputSectorRefund${statusDetail.count}" value="${tableDetail.sectorRefund}" onfocusout="checkRefund(this,${statusDetail.count})">
                                                        </td>
                                                        <td>
                                                            <input  maxlength ="15" type="text"  class="form-control numerical text-right"  onfocusout="changeFormatChargeNumber(${statusDetail.count});"  id="inputCharge${statusDetail.count}" name="inputCharge${statusDetail.count}" value="${tableDetail.charge}" >
                                                        </td>
                                                        <td class="text-center">
                                                            <a class="carousel"  
                                                               data-target="#DeleteRefundDetail" 
                                                               onclick="DeleteRefundDetail(${statusDetail.count},${tableDetail.ticketno},${param.referenceNo},${airbookingid},${tableDetail.refunddetailid})"
                                                                data-toggle="modal" >
                                                                <span class="glyphicon glyphicon-remove deleteicon"></span>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
<!--                                    <div class="row">
                                        <div class="col-sm-5 form-group text-right">
                                        <button type="button" id="buttonPrintRefund"  name="buttonPrintRefund" class="btn btn-primary"><span class="fa fa-print"></span> Print</button>
                                    </div>-->
                                    <div class="col-sm-6 form-group text-right">
                                        <a  id="SpanAdd" href="Refund.smi?referenceNo=${param.referenceNo}&airbookingid=${airbookingid}&action=saveRefund">
                                            <button type="submit" class="btn btn-success"  id="buttonSaveRefund" name="buttonSaveRefund" onclick="saveRefund();"><span class="fa fa-save"></span> Save</button>
                                        </a>
                                    </div>
                                    <div class="col-sm-6 form-group text-left">
                                        <button type="button" id="buttonCloseRefund"  name="buttonCloseRefund" class="btn btn-default"><span class="glyphicon glyphicon-remove deleteicon"></span> Close </button>
                                    </div>
                                </div>  
                            </div>
                            </c:forEach>
                        </c:if>
                        <!--Refund Add aaaaaaa-->  
                        <div class="row hidden" style="margin-top: 20px" id="RefundTicketDetailAdd" name="RefundTicketDetailAdd" >
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
                                                <input type="text" class="form-control" id="refundBy" name="refundBy" value="">
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
                                                <input type="text" class="form-control" id="receiveBy" name="receiveBy" value="${refundbyDefault}">
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
                                            <div class='input-group date' id='datetimepicker4'>
                                                <input type='text' class="form-control" name="receiveDate" id="receiveDate" data-date-format="YYYY-MM-DD" value="${create}"  placeholder="YYYY-MM-DD"/>
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
                                <input type="text" class="hidden" id="counterTableAdd" name="counterTableAdd" value="1" >
                                <div class="col-sm-12 form-group text-center">
                                    <table  class="display" id="RefundTicketDetailTableAdd" style="width: 1000px;">
                                        <thead>
                                            <tr class="datatable-header">
                                                <th class="hidden" >id</th>
                                                    <th style="width: 5%" >No</th>
                                                    <th style="width: 25%">Ticket No</th>
                                                    <th style="width: 25%">Section</th>
                                                    <th style="width: 25%">section refund</th>
                                                    <th style="width: 15%">Change</th>
                                                    <th style="width: 5%" >Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
<!--                                <div class="col-sm-5 form-group text-right">
                                    <button type="button" id="buttonPrintRefund"  name="buttonPrintRefund" class="btn btn-primary" ><span class="fa fa-print"></span> Print</button>
                                </div>-->
                                <div class="col-sm-6 form-group text-right">
                                    <a  id="SpanAdd" href="Refund.smi?referenceNo=${param.referenceNo}&airbookingid=${airbookingid}&action=addRefund">
                                    <button type="submit" class="btn btn-success" id="buttonSaveRefund" name="buttonSaveRefund" onclick="saveRefund();"><span class="fa fa-save"></span> Save</button>
                                    </a>
                                </div>
                                <div class="col-sm-6 form-group text-left">
                                    <button type="button" id="buttonCloseRefund"  name="buttonCloseRefund" class="btn btn-default"><span class="glyphicon glyphicon-remove deleteicon"></span> Close </button>
                                </div>
                            </div>  
                        </div> <!-- Refund Add --> 
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
                <button type="button" onclick="DeleteRefundConfirm()" class="btn btn-danger" data-dismiss="modal">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
                
<div class="modal fade" id="DeleteRefundDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Refund Detail</h4>
            </div>
            <div class="modal-body" id="textDeleteRefundDetail">
                <h5 class="modal-title">Are you Delete Refund</h5>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="action" value="${action}">
                <button type="button" onclick="DeleteRefundDetailConfirm()" class="btn btn-danger" data-dismiss="modal">Delete</button>
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
                        <script>
                            customerRefund = [];
                        </script>
                        <c:forEach var="item" items="${listRefundBy}">
                            <tr onclick="setBillValue('${item.billTo}', '${item.billName}', '${item.address}', '${item.term}', '${item.pay}');">
                                <td class="item-billto">${item.billTo}</td>
                                <td class="item-name">${item.billName}</td>                                
                                <td class="item-address hidden">${item.address}</td>
                                <td class="item-tel hidden">${item.tel}</td>
                            </tr>
                            <script>
                                customerRefund.push({id: "${item.billTo}", code: "${item.billTo}", name: "${item.billName}",
                                    address: "${item.address}", tel: "${item.tel}", fax: "${item.tel}"});
                            </script>
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
                        <tr >
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
                        <tr onclick="setBillReceiveValue('${a.username}', '${a.name}', '${a.tel}', '${a.tel}', '${a.tel}');">
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
    var counterror = 0;
    $(document).ready(function () {         
        $('table').on('click', 'tr', function () {
            $(this).addClass('row_selected').siblings().removeClass('row_selected');
        });
        $('#RefundTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": false,
            "bInfo": false
        });
        $(".numerical").on('input', function() {
            var value = $(this).val().replace(/[^0-9.,]*/g, '');
            value = value.replace(/\.{2,}/g, '.');
            value = value.replace(/\.,/g, ',');
            value = value.replace(/\,\./g, ',');
            value = value.replace(/\,{2,}/g, ',');
            value = value.replace(/\.[0-9]+\./g, '.');
            $(this).val(value);
        });
        <c:forEach var="cur" items="${listTicketNo}">
            selectTicketNo += "<option value='${cur.id}' ><c:out value='${cur.series1}${cur.series2}${cur.series3}' /></option>";      
        </c:forEach>
        <c:if test="${RefundTicket != null}">        
            $("#RefundTicketDetail").removeClass("hidden");
        </c:if>
        var addAction = $("#actionAdd").val();
        if(addAction === "add"){
            $("#RefundTicketDetailAdd").removeClass("hidden");
        }
        
        $('.datemask').mask('0000-00-00');
        $('.date').datetimepicker();
        
//        validateRefundForm(); 
        
        $("#RefundTicketDetailTable").on("keyup", function () {
            var rowAll = $("#RefundTicketDetailTable tr").length;
            $("td").keyup(function () {
                if ($(this).find("input").val() !== '') {
                    var colIndex = $(this).parent().children().index($(this));
                    var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                    rowAll = $("#RefundTicketDetailTable tr").length;
                    if (rowIndex === rowAll) {
                        console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                        addRowRefundTicketDetail(rowAll);
                    }
                }
            });
        });
    
    var counter = $('#RefundTicketDetailTable tbody tr').length;
    for(var j = 1;j <= (counter) ; j++){
        changeFormatChargeNumber(j);       
    }
    
    // get row in table now
    var rowCount = $('#RefundTicketDetailTable tr').length;
    console.log("Row Refund Ticket Detail : " + rowCount);
    $("#counterTable").val(rowCount);
    addRowRefundTicketDetail(rowCount++);
    
    $("#RefundTicketDetailTableAdd").on("keyup", function () {
        var rowAll = $("#RefundTicketDetailTableAdd tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#RefundTicketDetailTableAdd tr").length;
                if (rowIndex === rowAll) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    addRowRefundTicketDetailAdd(rowAll);
                }
            }
        });
    });
    
    var counter = $('#RefundTicketDetailTableAdd tbody tr').length;
    for(var j = 1;j <= counter ; j++){
        changeFormatChargeAddNumber(j);       
    }
    var rowCountAdd = $('#RefundTicketDetailTableAdd tr').length;
    console.log("Row Refund Ticket Detail (Add): " + rowCountAdd);
    $("#counterTableAdd").val(rowCountAdd);
    addRowRefundTicketDetailAdd(rowCountAdd++);
   
    $('#datetimepicker4').datetimepicker().on('dp.change', function (e) {
        $('#RefundForm').bootstrapValidator('revalidateField', 'receiveDate');
    });
        
    $('span').click(function () {
        var position = $(this).offset();
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    
    //  Receive
    $("#receiveUserTable tr").on('click', function () {
        var user_id = $(this).find(".user-id").text();
        var user_user = $(this).find(".user-user").text();
        var user_name = $(this).find(".user-name").text();
//        console.log("User : " + user_user + "Name : " + user_name);
        $("#receiveById").val(user_id);
        $("#receiveBy").val(user_user);
        $("#receiveByName").val(user_name);
        $("#receiveUserModal").modal('hide');
    });

    var userCode = [];
    $.each(user, function (key, value) {
        userCode.push(value.code);
        userCode.push(value.name);
        if ($("#receiveBy").val() === value.code) {
            $("#receiveByName").val(value.name);
        }
    });
    
    $("#receiveBy").autocomplete({
        source: userCode,
        close: function (event, ui) {
            $("#receiveBy").trigger('keyup');
        }
    });
    
    $("#receiveBy").on('keyup', function () {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value.toUpperCase();
        console.log("Name :" + name);
        $("#receiveBy,#receiveByName").val(null);
        $.each(user, function (key, value) {
            if (value.code.toUpperCase() === code) {
                $("#receiveByName").val(value.name);
                $("#receiveBy").val(value.code);
            }
            else if (value.name.toUpperCase() === name) {
                $("#receiveBy").val(value.code);
                $("#receiveByName").val(value.name);
            }
        });
    }); 

    // Refund 
    $("#refundCustTable tr").on('click', function () {
        var user_id = $(this).find(".item-billto").text();
        var user_name = $(this).find(".item-name").text();
//        console.log("User : " + user_id + "Name : " + user_name);
        $("#refundBy").val(user_id);
        $("#refundByName").val(user_name);
        $("#refundCustModal").modal('hide');
    });
    
    var customerCode = [];
    $.each(customerRefund, function (key, value) {
        customerCode.push(value.code);
        customerCode.push(value.name);
        if ($("#refundBy").val() === value.code) {
            $("#refundByName").val(value.name);
        }
    });
    
    $("#refundBy").autocomplete({
        source: customerCode,
        close: function (event, ui) {
            $("#refundBy").trigger('keyup');
        }
    });
    
    $("#refundBy").on('keyup', function(){
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value.toUpperCase();
        console.log("Name :" + name);
        $("#refundBy,#refundByName").val(null);
        $.each(customerRefund, function (key, value) {
            if (value.code.toUpperCase() === code) {
                $("#refundByName").val(value.name);
                $("#refundBy").val(value.code);
            }
            else if (value.name.toUpperCase() === name) {
                $("#refundBy").val(value.code);
                $("#refundByName").val(value.name);
            }
        });
    }); 
    
    $("#searchCustFrom").keyup(function (event) {
        if (event.keyCode === 13) {
            if ($("#searchCustFrom").val() === "") {
                
            }
            searchCustomerAgentList($("#searchCustFrom").val());
        }
    });
    
    $('#refundCustTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    $('#receiveUserTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
//     validateRefundForm();    
    $("#RefundForm")
        .bootstrapValidator({
//                framework: 'bootstrap',
            container: 'tooltip',
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                refundBy: {
                     excluded: 'false',
                    validators: {
                        notEmpty: {
                            message: ' refundBy is required'
                        }
                    }
                },
                refundByName: {
                    trigger: 'focus keyup',
                    validators: {
                        notEmpty: {trigger: 'change',
                            message: ' refundByName is required'
                        }
                    }
                },
                receiveBy: {
                    validators: {
                        notEmpty: {
                            message: ' receiveBy is required'
                        }
                    }
                },
                receiveByName: {
                    validators: {
                        notEmpty: {
                            message: ' receiveByName is required'
                        }
                    }
                },
                receiveDate: {
                    validators: {
                        notEmpty: {
                            message: ' receiveDate is required'
                        }
                    }
                }
            }
        })
        .on('success.field.fv', function (e, data) {
            if (data.field === 'refundBy' && data.fv.isValidField('refundBy') === false) {
                data.fv.revalidateField('refundBy');
            }
            if (data.field === 'refundByName' && data.fv.isValidField('refundByName') === false) {
                data.fv.revalidateField('refundByName');
            }
            if (data.field === 'receiveBy' && data.fv.isValidField('receiveBy') === false) {
                data.fv.revalidateField('receiveBy');
            }
            if (data.field === 'receiveByName' && data.fv.isValidField('receiveByName') === false) {
                data.fv.revalidateField('receiveByName');
            }
             if (data.field === 'receiveDate' && data.fv.isValidField('receiveDate') === false) {
                data.fv.revalidateField('receiveDate');
            }
        });
           
        var countTableRefundDetail = $('#RefundTicketDetailTable tr').length;
        console.log("RefundTicketDetailTable : " + countTableRefundDetail);
        for(var i = 1 ; i <= (countTableRefundDetail-1) ; i++){
            checkRefundReady(i);
        }  
        if(counterror === 0){
            $("#buttonSaveRefund").removeAttr("disabled");
            $("#buttonPrintRefund").removeAttr("disabled");
        }else{
            $("#buttonSaveRefund").attr("disabled", "disabled");
            $("#buttonPrintRefund").attr("disabled", "disabled");
        }
        
 }); 
  
function setBillValue(billto, billname, address, term, pay) {
    $("#refundBy").val(billto);
    $("#refundByName").val(billname);

    $('#RefundForm').bootstrapValidator('revalidateField', 'refundBy');
    $('#RefundForm').bootstrapValidator('revalidateField', 'refundByName');

    if($("#receiveBy").val() !== "" && $("#receiveByName").val() !== "" && $("#receiveDate").val() !== "" && $("#refundBy").val() !== "" && $("#refundByName").val() !== ""){
        $('#RefundForm').bootstrapValidator('revalidateField', 'receiveBy');
        $('#RefundForm').bootstrapValidator('revalidateField', 'receiveByName');
        $('#RefundForm').bootstrapValidator('revalidateField', 'receiveDate');
        $("#buttonSaveRefund").removeAttr("disabled");
        $("#buttonPrintRefund").removeAttr("disabled");
        $("#buttonCloseRefund").removeAttr("disabled");
    }else{
        $("#buttonSaveRefund").attr("disabled", "disabled");
        $("#buttonPrintRefund").attr("disabled", "disabled");
        $("#buttonCloseRefund").attr("disabled", "disabled");
    }

    $('#RefundForm').modal('hide');
    $("#refundCustModal").modal('hide');
}

function setBillReceiveValue(billto, billname, address, term, pay) {
    $("#receiveBy").val(billto);
    $("#receiveByName").val(billname);

    $('#RefundForm').bootstrapValidator('revalidateField', 'receiveBy');
    $('#RefundForm').bootstrapValidator('revalidateField', 'receiveByName');
    $('#RefundForm').modal('hide');
    $("#receiveUserModal").modal('hide');
}

function validateRefundForm(){
    var refundby = $("#refundBy").val();
    var refundname = $("#refundByName").val();
    var receiveby = $("#receiveBy").val();
    var receivename = $("#receiveByName").val();
    var receivedate = $("#receiveDate").val();
    console.log("Refund By : " + refundby + " Refund Name : " + refundname + " Receive By : " + receiveby + " Receive Name : " + receivename + "Rceive date : " + receivedate);
    if(refundby === '' && refundname === ''){
        $("#refundpanel").addClass("has-error");
        $("#buttonSaveRefund").attr("disabled", "disabled");
        $("#buttonPrintRefund").attr("disabled", "disabled");
        $("#buttonCloseRefund").attr("disabled", "disabled");
    }else if(refundby !== '' && refundname !== ''){
        $("#refundpanel").removeClass("has-success");
        $("#buttonSaveRefund").removeAttr("disabled");
        $("#buttonPrintRefund").removeAttr("disabled");
        $("#buttonCloseRefund").removeAttr("disabled");
    }else if(receiveby === '' && receivename === ''){
        $("#receivepanel").addClass("has-error");
        $("#buttonSaveRefund").attr("disabled", "disabled");
        $("#buttonPrintRefund").attr("disabled", "disabled");
        $("#buttonCloseRefund").attr("disabled", "disabled");
    }else if(receiveby !== '' && receivename !== ''){
        $("#receivepanel").removeClass("has-success");
        $("#refundpanel").removeClass("has-success");
        $("#buttonSaveRefund").removeAttr("disabled");
        $("#buttonPrintRefund").removeAttr("disabled");
        $("#buttonCloseRefund").removeAttr("disabled");
    }else if(receivedate === '' ){
        $("#receivedatepanel").addClass("has-error");
        $("#buttonSaveRefund").attr("disabled", "disabled");
        $("#buttonPrintRefund").attr("disabled", "disabled");
        $("#buttonCloseRefund").attr("disabled", "disabled");
    }else if(receivedate !== '' ){
        $("#receivedatepanel").removeClass("has-success");
        $("#buttonSaveRefund").removeAttr("disabled");
        $("#buttonPrintRefund").removeAttr("disabled");
        $("#buttonCloseRefund").removeAttr("disabled");
    }else{
        $("#buttonSaveRefund").removeAttr("disabled");
        $("#buttonPrintRefund").removeAttr("disabled");
        $("#buttonCloseRefund").removeAttr("disabled");
    }
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
                            if ((billselect === billListName[i]) || (billselect === billListId[i])) {
                                $("#refundBy").val(billListId[i]);
                                $("#refundByName").val(billListName[i]);
                            }
                        }
                    }
                });

                var billval = $("#refundBy").val();
                for (var i = 0; i < billListId.length; i++) {
                    if (billval === billListName[i]) {
                        $("#refundBy").val(billListId[i]);
                    }
                }
                if (billListId.length === 1) {
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


function saveRefund(){
    var actionG = document.getElementById('action');
    var refno = document.getElementById('referenceNo');
    var bookingid = document.getElementById('airbookingid');
    actionG.value = 'saveRefund';
    console.log("REf : " + refno.value +" ID : " + bookingid.value);
    $('#referenceNo').val(refno.value);
    $('#airbookingid').val(bookingid.value);
    
    var refundby = $("#refundBy").val();
    var refundname = $("#refundByName").val();
    var receiveby = $("#receiveBy").val();
    var receivename = $("#receiveByName").val();
    var receivedate = $("#receiveDate").val();
    if(refundby !== '' && refundname !== '' && receiveby !== '' &&  receivename !== '' && receivedate !== ''){
        $("#buttonSaveRefund").removeAttr("disabled");
        $("#buttonPrintRefund").removeAttr("disabled");
        $("#buttonCloseRefund").removeAttr("disabled");
        document.getElementById('RefundForm').submit();
    }
    // Check Change
   
}
function checkRefundReady(row) {
    var refund = $("#inputSectorRefund" + row).val();
    var issue = $("#inputSector" + row).val();
    console.log("Row Refund detail id : " + row + " Value issue : " + issue + " Refund : " + refund);
    if (issue.indexOf(refund) >= 0) {
//        $("#inputSectorRefund" + row).style.borderColor = "Green";
        $("#inputSectorRefund"+row).addClass("has-success");
        if(refund !== ''){
            $("#inputSectorRefund"+row).css('border-color','green');
        }
        $("#buttonSaveRefund").removeAttr("disabled");
        $("#buttonPrintRefund").removeAttr("disabled");
    } else {
//        $("#inputSectorRefund" + row).style.borderColor = "Red";
        counterror++;
        $("#inputSectorRefund"+row).addClass("has-error");
        $("#inputSectorRefund"+row).css('border-color','red');
        $("#buttonSaveRefund").attr("disabled", "disabled");
        $("#buttonPrintRefund").attr("disabled", "disabled");
        return;
    }
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
        '<td class="hidden"><input type="text" id="airticketrefunddetailid' + row + '" name="airticketrefunddetailid' + row + '" value="" /></td>'+
        '<td>' + row + '</td>' +       
        '<td><select id="SelectTocketNo' + row + '" name="SelectTocketNo' + row + '" class="form-control" onchange="setSectorRefund(' + row + ');">'+ selectTicket +'</select> </td>' +
        '<td><input type="text" maxlength ="255" class="form-control" id="inputSector' + row + '" name="inputSector' + row + '" value=""></td>' +
        '<td><input type="text" class="form-control" id="inputSectorRefund' + row + '" name="inputSectorRefund' + row + '" value=""></td>' +
        '<td><input  maxlength ="15" type="text"  class="form-control numerical text-right"  onfocusout="changeFormatChargeNumber('+row+');"  id="inputCharge' + row + '" name="inputCharge' + row + '" value="" ></td>' +      
        '<td class="text-center"><a class="carousel" data-toggle="modal"  data-target="#DeleteRefundDetail" onclick="DeleteRefundDetail('+row+',\'\')"  ><span class="glyphicon glyphicon-remove deleteicon"></span></a></td>'+
        '</tr>'    
    );
}

function addRowRefundTicketDetailAdd(row,id){
    var selectTicket = "";
    selectTicket = selectTicketNo;
    console.log("Select Ticket No : " + selectTicketNo);
    $("#RefundTicketDetailTableAdd tbody").append(
        '<tr>' +
        '<td class="hidden"><input type="text" id="airticketrefunddetailidadd' + row + '" name="airticketrefunddetailidadd' + row + '" value="" /></td>'+
        '<td>' + row + '</td>' +       
        '<td><select id="SelectTocketNoadd' + row + '" name="SelectTocketNoadd' + row + '" class="form-control" onchange="setSectorRefund(' + row + ');">'+ selectTicket +'</select> </td>' +
        '<td><input type="text" maxlength ="255" class="form-control" id="inputSectoradd' + row + '" name="inputSectoradd' + row + '" value=""></td>' +
        '<td><input type="text" class="form-control" id="inputSectorRefundadd' + row + '" name="inputSectorRefundadd' + row + '" value=""></td>' +
        '<td><input  maxlength ="15" type="text"  class="form-control numerical text-right"  onfocusout="changeFormatChargeAddNumber('+row+');"  id="inputChargeadd' + row + '" name="inputChargeadd' + row + '" value="" ></td>' +      
        '<td class="text-center"><a class="carousel" data-toggle="modal"  data-target="#DeleteRefundDetail" onclick="DeleteRefundDetail('+row+',\'\')"  ><span class="glyphicon glyphicon-remove deleteicon"></span></a></td>'+
        '</tr>'    
    );
}

function setSectorRefund(row){
//    alert("1");
    var txt = $("#SelectTocketNoadd"+ row + " option:selected").text();
    var txtNo = $("#SelectTocketNo"+ row + " option:selected").text();
    console.log("Text : " + txt);
    console.log("Text : " + txtNo);
    if(txt !== ''){
        var url = 'AJAXServlet';
        var servletName = 'RefundAirlineServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&type=getTicketFare' +
                '&ticketNo=' + txt ;
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
                    if (msg !== "") {
                        var fare = JSON.parse(msg);
                        console.log("Sector : "+fare.Sector);
                        $("#inputSectoradd" + row).val(fare.Sector);
                    } 
                }, error: function (msg) {
                    console.log('auto ERROR');
                    $("#dataload").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }
    if(txtNo !== ''){
        var url = 'AJAXServlet';
        var servletName = 'RefundAirlineServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&type=getTicketFare' +
                '&ticketNo=' + txt ;
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
                    if (msg !== "") {
                        var fare = JSON.parse(msg);
                        console.log("Sector : "+fare.Sector);
                        $("#inputSector" + row).val(fare.Sector);
                    } 
                }, error: function (msg) {
                    console.log('auto ERROR');
                    $("#dataload").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }
}

function DeleteRefund(rowID,refno,airbookingid,airticketrefundid,refundid){
    $("#refundid").val(rowID);
        $("#textDeleteRefund").text('Are you sure to delete refund ..?');
        $("#action").val("deleteAirTicketRefund");
        $("#referenceNo").val(refno);
        $("#airbookingid").val(airbookingid);
        $("#refundById").val(airticketrefundid);
        $("#refundid").val(refundid);
        var action = $("#action").val();
        var refno0 = $("#referenceNo").val();
        var airid0 = $("#airbookingid").val();
        var airticketbookingid0 = $("#refundById").val();
        var refundid0 = $("#refundid").val();
        console.log("Action : " + action + "   Ref No : " + refno0 + " Air Booking ID : " + airid0 + " Air Ticket id : " + airticketbookingid0 + " Refund Id : " + refundid0 );
}

function DeleteRefundDetail(rowID,code,refno,airbookingid,refundetailid){
    var refundid0 = $("#refundid").val();
    $("#refunddetailid").val(refundetailid);
    $("#action").val("deleteAirTicketRefundDetail");
    $("#referenceNo").val(refno);
    $("#airbookingid").val(airbookingid);
    $("#refundid").val(refundid0);
    var action = $("#action").val();
    var refno0 = $("#referenceNo").val();
    var airid0 = $("#airbookingid").val();
    var airticketbookingid0 = $("#refundById").val();
    var refundid0 = $("#refundid").val();
    var refunddetailid =  $("#refunddetailid").val();
    console.log("Action : " + action + "   Ref No : " + refno0 + " Air Booking ID : " + airid0 + " Air Ticket id : " + airticketbookingid0 + " Refund Id : " + refundid0  + " Refund Detail id : " + refunddetailid);
//    if(code !== ""){
        $("#textDeleteRefundDetail").text('Are you sure to delete refund detail ..?');
//    }else{
//        $("#textDeleteRefundDetail").text('Are you sure to delete refund detail ?');
//    }
}

function DeleteRefundConfirm() {
    var count = $('#counterTableRefund').val();
    var rowId  = $('#refundid').val();
    var RefundId  = $("#airticketrefundid"+rowId).val();
    console.log("Refund ID : " + RefundId);
    document.getElementById('RefundForm').submit();
}

function DeleteRefundDetailConfirm() {
    var count = $('#counterTable').val();
    var rowId  = $('#refunddetailid').val();
    console.log("Row Refund Detail : " + rowId);
    var RefundId  = $("#airticketrefunddetailid"+rowId).val();
    console.log("Refund Detail ID : " + RefundId);
    document.getElementById('RefundForm').submit();
}

function changeFormatChargeAddNumber(id){
    console.log("Id Row : " + count);
    var count = document.getElementById('inputChargeadd'+id).value;
    count = count.replace(/\,/g,'');
    count  = parseFloat(count);
    if(isNaN(count)){
        document.getElementById('inputChargeadd' + id).value = "";
    }else{
        count = parseFloat(count);
        document.getElementById('inputChargeadd' + id).value = formatNumber(count);
    }
}
function changeFormatChargeNumber(id){
    console.log("Id Row : " + count);
    var count = document.getElementById('inputCharge'+id).value;
    count = count.replace(/\,/g,'');
    count  = parseFloat(count);
    if(isNaN(count)){
        document.getElementById('inputCharge' + id).value = "";
    }else{
        count = parseFloat(count);
        document.getElementById('inputCharge' + id).value = formatNumber(count);
    }
}

function checkRefund(e,row) {
    var refund = e.value;
    var issue = $("#inputSector" + row).val();
    console.log("Row Refund detail id : " + row + " Value issue : " + issue);
    if (issue.indexOf(refund) >= 0) {
        e.style.borderColor = "Green";
        $("#buttonSaveRefund").removeAttr("disabled");
        $("#buttonPrintRefund").removeAttr("disabled");
    } else {
        e.style.borderColor = "Red";
        $("#buttonSaveRefund").attr("disabled", "disabled");
        $("#buttonPrintRefund").attr("disabled", "disabled");
        return;
    }
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

function printAirticketRefund(){
    var PnrID = "";
    $('#RefundTable tr.row_selected').each(function () {
        PnrID = $(this).attr('id');
    });
//    alert("ID : " + PnrID);
    if (PnrID === "") {
        alert("please select airticket refund");
    } else {
        window.open("report.smi?name=RefundAirReport&refundId="+PnrID);
    }
}
</script>
<c:if test="${! empty requestScope['result']}">
    <c:if test="${requestScope['result'] =='success'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='fail'}">        
        <script language="javascript">
           $('#textAlertDivNotSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='delete success'}">        
        <script language="javascript">
           $('#textAlertDelete').show();
        </script>
    </c:if>   
    <c:if test="${requestScope['result'] =='delete fail'}">        
        <script language="javascript">
           $('#textAlertNotDelete').show();
        </script>
    </c:if>    
</c:if>
