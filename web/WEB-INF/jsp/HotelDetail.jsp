<%-- 
    Document   : HotelDetail
    Created on : Dec 20, 2014, 1:19:53 PM
    Author     : sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/HotelDetail.js"></script> 
<script src="js/select2.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">


<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="hotelBooking" value="${requestScope['HotelBooking']}" />
<c:set var="hotelRoomsList" value="${requestScope['HotelRooms']}" />
<c:set var="hotelRequestsList" value="${requestScope['HotelRequestsList']}" />
<c:set var="hotelPassengerList" value="${requestScope['HotelPassengerList']}" />
<c:set var="mMealsList" value="${requestScope['MMealsList']}" />
<c:set var="mItemstatusesList" value="${requestScope['MItemstatusesList']}" />
<c:set var="hotelList" value="${requestScope['HotelList']}" />
<c:set var="result" value="${requestScope['Result']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="passengerList" value="${requestScope['PassengerList']}" />
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<c:set var="lockUnlockBooking" value="${requestScope['LockUnlockBooking']}" />
<c:set var="mCurrency" value="${requestScope['MCurrency']}" />
<c:set var="isBillStatus" value="${requestScope['IsBillStatus']}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<input type="hidden" value="${master.id}" id="master-id">
<input type="hidden" value="${master.departmentNo}" id="departmentNo">
<input type="hidden" value="1" id="statusBar">
<c:set var="enableSave" value="${requestScope['EnableSave']}" />
<c:set var="readonly" value="" />
<c:if test="${lockUnlockBooking == 0}">
    <c:if test="${isBillStatus == 0}">
        <c:set var="readonly" value="" />
    </c:if>
    <c:if test="${isBillStatus == 1}">
        <c:set var="readonly" value="readonly" />
    </c:if>
</c:if>
<c:if test="${lockUnlockBooking == 1}">
    <c:set var="readonly" value="readonly" />
</c:if>
<c:if test="${lockUnlockBooking == 2}">
    <c:set var="readonly" value="" />
</c:if>
<input type="hidden" value="${readonly}" id="readonly">
<section class="content-header" >
    <h1>
        Booking - Hotel
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">Hotel</a></li>
    </ol>
</section>
<input type="hidden" id="requestLock" name="requestLock" value="${lockUnlockBooking}"/>
<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
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
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <!--Alert Save and Update-->
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Success!</strong> 
            </div>
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Unsuccess!</strong> 
            </div> 
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
            <div class="row" >
                <div class="col-sm-6" style="margin-top: -5px;">
                    <h4>Hotel Detail</h4>
                </div>

                <div class="col-sm-6 text-right" style="margin-top: -5px;">
                    <div class="row-fluid">

                        <a id="ButtonBack" class="btn btn-primary" href="HotelBooking.smi?referenceNo=${param.referenceNo}">
                            <i id="SpanBack" class="glyphicon glyphicon-arrow-left"></i> Back
                        </a>
                    </div>
                </div>
            </div>
            <!--form-->
            <form action="HotelDetail.smi" method="post"  role="form" id="HotelForm">
                <!--Detail Panal-->
                <div class="panel panel-default">
                    <div class="panel-heading">Detail</div>
                    <div class="panel-body" style="padding-bottom: 0px;">
                        <div class="row">
                            <div class="col-sm-4 form-group" style="margin-top: -5px;">
                                <label class="col-sm-5 control-label text-right">Order</label>
                                <div class="col-sm-6">
                                    <c:if test="${param.Order == null}">
                                        <input id="orderNo" name="orderNo" class="form-control" readonly="" value="${hotelBooking.orderNo}">
                                    </c:if>
                                    <c:if test="${param.Order != null}">
                                        <input id="orderNo" name="orderNo" class="form-control" readonly="" value="${param.Order}">
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4" style="margin-top: -5px;">
                                    <label class="col-sm-5 control-label text-right">Hotel <strong style="color: red">*</strong></label>
                                    <div class="col-sm-6">
                                        <div class="input-group">
                                            <input name="hotel-id" id="hotel-id" type="hidden" value="${hotelBooking.hotel.getId()}" >
                                            <input name="isBill" id="isBill" type="hidden" value="${hotelBooking.isBill}" >
                                            <input name="hotelCode" id="hotel-code" type="text" class="form-control" value="${hotelBooking.hotel.getCode()}">
                                            <span class="input-group-addon" id="hotel-modal"  data-toggle="modal" data-target="#HotelModal">
                                                <span id="SpanHotelModal" class="glyphicon-search glyphicon"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4" style="margin-top: -5px;">
                                    <div class="col-sm-12">
                                        <input name="hotelName" id="hotel-name" class="form-control" value="${hotelBooking.hotel.getName()}" readonly=""
                                               data-bv-notempty="true" data-bv-notempty-message="Hotel empty !">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4 form-group" style="margin-top: -10px;">
                                <label class="col-sm-5 control-label text-right">Check In <strong style="color: red">*</strong></label>
                                <div class="col-sm-6">
                                    <c:if test="${lockUnlockBooking == 1}">
                                        <div class='input-group ' >
                                            <fmt:formatDate value="${hotelBooking.checkin}" var="checkIn" pattern="dd-MM-yyyy" />
                                            <input id="checkin" name="checkin" type='text' class="form-control datemask"  data-date-format="DD-MM-YYYY" value="${checkIn}" placeholder="DD-MM-YYYY" ${readonly}/>
                                            <span id="SpanCheckin" class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </c:if>
                                    <c:if test="${lockUnlockBooking == 0}">
                                        <div class='input-group date' >
                                            <fmt:formatDate value="${hotelBooking.checkin}" var="checkIn" pattern="dd-MM-yyyy" />
                                            <input id="checkin" name="checkin" type='text' class="form-control datemask"  data-date-format="DD-MM-YYYY" value="${checkIn}" placeholder="DD-MM-YYYY" ${readonly}/>
                                            <span id="SpanCheckin" class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" style="margin-top: -10px;">
                                <label class="col-sm-5 control-label text-right">Check Out <strong style="color: red">*</strong></label>
                                <div class="col-sm-6">
                                    <c:if test="${lockUnlockBooking == 1}">
                                        <div class='input-group '>
                                            <fmt:formatDate value="${hotelBooking.checkout}" var="checkOut" pattern="dd-MM-yyyy" />
                                            <input id="checkout" name="checkout" type='text'  data-date-format="DD-MM-YYYY" class="form-control datemask" value="${checkOut}" placeholder="DD-MM-YYYY" ${readonly}/>
                                            <span id="SpanCheckout" class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </c:if>
                                    <c:if test="${lockUnlockBooking == 0}">
                                        <div class='input-group date'>
                                            <fmt:formatDate value="${hotelBooking.checkout}" var="checkOut" pattern="dd-MM-yyyy" />
                                            <input id="checkout" name="checkout" type='text'  data-date-format="DD-MM-YYYY" class="form-control datemask" value="${checkOut}" placeholder="DD-MM-YYYY" ${readonly}/>
                                            <span id="SpanCheckout" class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                     </c:if>
                                </div>
                            </div>
                            <div class="col-sm-4" style="margin-top: -10px;">
                                <label class="col-sm-4 control-label text-right">Night</label>
                                <div class="col-sm-8">
                                    <input name="day" id="day" class="form-control" disabled="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4" style="margin-top: -10px;">
                                <label class="col-sm-5 control-label text-right">Meal</label>
                                <div class="col-sm-6">
                                    <input value="${hotelBooking.getMMeal().getName()}" id="get-meal" type="hidden">
                                    <select class="form-control" name="meal"  id="meal">
                                        <c:forEach var="m" items="${mMealsList}">
                                            <option value="${m.name}">${m.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 form-group" style="margin-top: -10px;">
                                <label class="col-sm-5 control-label text-right">Deadline</label>
                                <div class="col-sm-6">
                                    <div class="input-group date">
                                        <fmt:formatDate value="${hotelBooking.deadline}" var="deadLine" pattern="dd-MM-yyyy" />
                                        <input name="deadline" id="deadline" type="text"  data-date-format="DD-MM-YYYY" maxlength="10" class="form-control datemask" value="${deadLine}" placeholder="DD-MM-YYYY"/>
                                        <span id="SpanDeadline" class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4" style="margin-top: -10px;">
                                <!--<label class="col-sm-4 control-label text-right">Status</label>-->
                                <div class="col-sm-8 hidden">
                                    <input value="${hotelBooking.getMItemstatus().getName()}" id="get-itemstatus" type="hidden">
                                    <select class="form-control" name="status" id="status">
                                        <c:forEach var="item" items="${mItemstatusesList}">
                                            <option value="${item.name}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <label class="col-sm-4 control-label text-right">Hotel Ref</label>
                                <div class="col-sm-8">
                                    <input id="hotelRef" name="hotelRef" class="form-control" value="${hotelBooking.hotelRef}" maxlength="50">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <c:choose>
                                <c:when test="${param.action == 'new'}">
                                    <div class="col-sm-4 form-group" style="margin-top: -10px;">
                                        <label class="col-sm-5 control-label text-right">Adult<strong style="color: red">*</strong></label>
                                        <div class="col-sm-3">
                                            <input name="adult" type="number" min="0" id="adult" class="form-control" value="${master.adult}" maxlength="11" >
                                        </div>
                                    </div>
                                    <div class="col-sm-2 form-group" style="margin-left: -125px; margin-top: -10px;">
                                        <label class="col-sm-4 control-label text-right">Child<strong style="color: red">*</strong></label>
                                        <div class="col-sm-7">
                                            <input name="child" id="child" type="number" min="0" class="form-control" value="${master.child}" maxlength="11">
                                        </div>
                                    </div>
                                    <div class="col-sm-2 form-group" style="margin-top: -10px;">
                                        <label class="col-sm-4 control-label text-right">Infant<strong style="color: red">*</strong></label>
                                        <div class="col-sm-7">
                                            <input name="infant" type="number" min="0" id="infant" class="form-control" value="${master.infant}" maxlength="11" >
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="col-sm-4 form-group" style="margin-top: -10px;">
                                        <label class="col-sm-5 control-label text-right">Adult<strong style="color: red">*</strong></label>
                                        <div class="col-sm-3">
                                            <input name="adult" type="number" min="0" id="adult" class="form-control" value="${hotelBooking.adult}" maxlength="11">
                                        </div>
                                    </div>
                                    <div class="col-sm-2 form-group" style="margin-left: -125px; margin-top: -10px;">
                                        <label class="col-sm-4 control-label text-right">Child<strong style="color: red">*</strong></label>
                                        <div class="col-sm-7">
                                            <input name="child" id="child" type="number" min="0" class="form-control" value="${hotelBooking.child}" maxlength="11" >
                                        </div>
                                    </div>
                                    <div class="col-sm-2 form-group" style="margin-top: -10px;">
                                        <label class="col-sm-4 control-label text-right">Infant<strong style="color: red">*</strong></label>
                                        <div class="col-sm-7">
                                            <input name="infant" type="number" min="0" id="infant" class="form-control" value="${hotelBooking.infant}" maxlength="11">
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                            <div class="col-sm-2" style="margin-top: -10px;">
                                <label class="col-sm-4 control-label text-right">Sum</label>
                                <div class="col-sm-7">
                                    <input name="Sum"  type="number" min="0" id="sum" class="form-control" disabled="">
                                </div>
                            </div>
                            <div class="col-sm-3" style="margin-top: -10px;">
                                <label class="col-sm-3 control-label text-right">Flight</label>
                                <div class="col-sm-9">
                                    <input class="form-control" maxlength="20" id="Flight" name="Flight" value="${hotelBooking.flight}" style="width: 180px;">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-4  form-group" style="margin-top: -10px;">
                                <label class="col-sm-5 control-label text-right">Cost Currency</label>
                                <div class="col-sm-6">
                                    <select id="selectcurrencycost" name="selectcurrencycost" class="form-control">
                                        <option id="" value="">---------</option>
                                        <c:forEach var="price" items="${mCurrency}" >
                                            <c:set var="select1" value="" />
                                            <c:if test="${hotelBooking.curCost == price.code}">
                                                <c:set var="select1" value="selected" />
                                            </c:if>
                                            <option value="<c:out value="${price.code}" />" ${select1}><c:out value="${price.code}" /></option>   
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4  form-group" style="margin-top: -10px;">
                                <label class="col-sm-5 control-label text-right">Price Currency</label>
                                <div class="col-sm-6">
                                    <select id="selectcurrency" name="selectcurrency" class="form-control">
                                    <option id="" value="">---------</option>
                                    <c:forEach var="price" items="${mCurrency}" >
                                        <c:set var="select1" value="" />
                                        <c:if test="${hotelBooking.curAmount == price.code}">
                                            <c:set var="select1" value="selected" />
                                        </c:if>
                                        <option value="<c:out value="${price.code}" />" ${select1}><c:out value="${price.code}" /></option>   
                                    </c:forEach>
                                </select>
                                </div>
                            </div>
                            <div class="col-sm-4  form-group" style="margin-top: -10px;"> 
                                <label class="col-sm-4 control-label text-right">Supplier</label>
                                <div class="col-sm-6">
                                    <input class="form-control" type="text" maxlength="" id="Supplier" name="Supplier" 
                                           value="${hotelBooking.supplier}" style="width: 180px;">
                                </div>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-sm-12" style="margin-top: -10px;">
                                <label class="col-sm-1 control-label text-right"  style="margin-left: 45px;">Remarks</label>
                                <div class="col-sm-4" style="width: 50%;">
                                    <textarea class="form-control" style="resize: none; " id="remark" name="remark" rows="2" cols="50" maxlength="100">${hotelBooking.remark}</textarea>
                                    <!--<input id="remark" name="remark" class="form-control" value="${hotelBooking.remark}" maxlength="100">-->
                                </div>
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-sm-12" style="margin-top: -10px;">
                                <label class="col-sm-1 control-label text-right"  style="margin-left: 45px;">OK By</label>
                                <div class="col-sm-4" style="width: 50%;">
                                    <input id="reconfirm" name="reconfirm" class="form-control" value="${hotelBooking.reconfirm}" maxlength="100">
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!--Formula Panel-->
                <div class="panel panel-default">
                    <div class="panel-heading">Formula</div>
                    <div class="panel-body">
                        <table class="display" id="formula-table">
                            <thead class="datatable-header">
                                <tr>
                                    <th class="hidden">ID</th>
                                    <th>Qty</th>
                                    <th>Room</th>
                                    <th>Category</th>
                                    <th>Cost</th>
                                    <th>Price</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <input type="text" class="hidden" id="roomCounter" name="roomCounter" value="0" />
                            <c:forEach var="re" items="${hotelRoomsList}"  varStatus="formula">
                                <tr>
                                    <td class="hidden"><input id="row-room-${formula.count}-id" name="row-room-${formula.count}-id" class="form-control" value="${re.id}"></td>
                                    <td><input id="row-room-${formula.count}-qty" name="row-room-${formula.count}-qty" class="form-control text-right money" value="${re.qty}" maxlength="3" ${readonly}></td>
                                    <td><input id="row-room-${formula.count}-room" name="row-room-${formula.count}-room" class="form-control" value="${re.room}" maxlength="50"></td>
                                    <td><input id="row-room-${formula.count}-category" name="row-room-${formula.count}-category" class="form-control" value="${re.category}" maxlength="50"></td>
                                    <td><input id="row-room-${formula.count}-cost" name="row-room-${formula.count}-cost" class="form-control text-right decimal" value="${re.cost}" maxlength="11" ${readonly}></td>
                                    <td><input id="row-room-${formula.count}-price" name="row-room-${formula.count}-price" class="form-control text-right decimal" value="${re.price}" maxlength="11" ${readonly}></td>
                                    <td class="text-center">
                                        <c:if test="${lockUnlockBooking == 0}">
                                            <c:if test="${re.hotelBooking.isBill == 0}">
                                                <a id="ButtonRemove${formula.count}" class="remCF" onclick="ConfirmDelete('${hotelBooking.id}', '1', '${re.id}', '${formula.count}')">
                                                    <span id="SpanRemove${formula.count}" class="glyphicon glyphicon-remove deleteicon"></span>
                                                </a> 
                                            </c:if>
                                            <c:if test="${re.hotelBooking.isBill == 1}">
                                                <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${lockUnlockBooking == 1}">
                                            <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                        </c:if>    
                                    </td>
                                <script>
                                    $(document).ready(function () {
                                        $("#roomCounter").val(parseInt("${formula.count}") + 1);
                                    });
                                </script>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <input value="${hotelRoomsList.size()}" id="table-formula-size" type="hidden">
                        <div id="tr_FormulaAddRow" class="text-center hide" style="padding-top: 10px">
                            <a class="btn btn-success" onclick="FormulaAddRow()">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>

                    </div>
                </div>
                <!--Addition Panel-->
                <div class="panel panel-default">
                    <div class="panel-heading">Additional</div>
                    <div class="panel-body">
                        <table class="display" id="addition-table">
                            <thead class="datatable-header">
                                <tr>
                                    <th class="hidden">ID</th>
                                    <th>Category</th>
                                    <th>Description</th>
                                    <th>Cost</th>
                                    <th>Price</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <input type="hidden"  id="requestCounter" name="requestCounter" value="0" />
                            
                            <c:forEach var="re" items="${hotelRequestsList}" varStatus="additional">
                                <tr>
                                    <td class="hidden"><input id="row-request-${additional.count}-id" name="row-request-${additional.count}-id" class="form-control" value="${re.id}"></td>
                                    <td><input id="row-request-${additional.count}-category" name="row-request-${additional.count}-category" class="form-control" value="${re.category}" maxlength="100"></td>
                                    <td><input id="row-request-${additional.count}-description" name="row-request-${additional.count}-description" class="form-control " value="${re.description}" maxlength="100"></td>
                                    <td><input id="row-request-${additional.count}-cost" name="row-request-${additional.count}-cost" class="form-control text-right decimaladditional" value="${re.cost}" maxlength="11" ${readonly}></td>
                                    <td><input id="row-request-${additional.count}-price" name="row-request-${additional.count}-price" class="form-control text-right decimaladditional" value="${re.price}" maxlength="11" ${readonly}></td>
                                    <td class="text-center">
                                        <c:if test="${lockUnlockBooking == 0}">
                                            <c:if test="${re.hotelBooking.isBill == 0}">
                                                <a id="AdditionalButtonRemove${additional.count}" class="remCF" onclick="ConfirmDelete('${hotelBooking.id}', '2', '${re.id}', '${additional.count}')">
                                                    <span id="AdditionalSpanRemove${additional.count}" class="glyphicon glyphicon-remove deleteicon"></span>
                                                </a>     
                                            </c:if>
                                            <c:if test="${re.hotelBooking.isBill == 1}">
                                                <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${lockUnlockBooking == 1}">
                                            <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                        </c:if>                                         
                                    </td>
                                <script>
                                    $(document).ready(function () {
                                        $("#requestCounter").val(parseInt("${additional.count}") + 1);
                                    });
                                </script>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <input value="${hotelRequestsList.size()}" id="table-additional-size" type="hidden">

                        <div id="tr_AdditionAddRow" class="text-center hide" style="padding-top: 10px">
                            <a class="btn btn-success" onclick="AdditionAddRow()">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>

                    </div>
                </div>
                <!--Passenger Panel-->
                <div class="panel panel-default">
                    <div class="panel-heading">Passenger</div>
                    <div class="panel-body">
                        <select   class="hidden"  id="select-list-passenger">
                            <c:forEach var="pass" items="${passengerList}" varStatus="status">
                                <option  value="${pass.id}">${pass.customer.MInitialname.name} ${pass.customer.lastName} ${pass.customer.firstName}</option>
                            </c:forEach>
                        </select>
                        <table class="display" id="passenger-table">
                            <thead class="datatable-header">
                                <tr>
                                    <th  style="width: 10%">No</th>
                                    <th>Name</th>
                                    <th  style="width: 5%">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <input type="text" class="hidden" id="passengerCounter" name="passengerCounter" value="0" />

                            <c:forEach var="pa" items="${hotelPassengerList}" varStatus="passenger">
                                <tr>
                                    <td hidden="">
                                        <input id="row-passenger-${passenger.count}-id" name="row-passenger-${passenger.count}-id" type="text" class="form-control" value="${pa.id}">
                                    </td>
                                    <td>
                                        ${passenger.count}
                                    </td>
                                    <td>
                                        <div id="div-passenger">
                                            <input id="input-get-passenger-${passenger.count}" value="${pa.passenger.id}" hidden="">
                                            <select  class="form-control"  name="row-passenger-${passenger.count}-name" id="select-passneger-${passenger.count}">
                                                <c:forEach var="passen" items="${passengerList}" varStatus="status">  
                                                    <option class="passenger-option" value="${passen.id}">  ${passen.customer.MInitialname.name} ${passen.customer.lastName} ${passen.customer.firstName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <script>
                                            $(document).ready(function () {
                                                $("#select-passneger-${passenger.count}").val($("#input-get-passenger-${passenger.count}").val());
                                            });
                                        </script>
                                    </td>          
                                    <td class="text-center">
                                        <c:if test="${lockUnlockBooking == 0}">
                                            <c:if test="${pa.hotelBooking.isBill == 0}">
                                                <a id="PassengerButtonRemove${passenger.count}" class="remCF" onclick="ConfirmDelete('${hotelBooking.id}', '3', '${pa.id}', '${passenger.count}')">
                                                    <span id="PassengerSpanRemove${passenger.count}" class="glyphicon glyphicon-remove deleteicon"></span>
                                                </a>
                                            </c:if>
                                            <c:if test="${pa.hotelBooking.isBill == 1}">
                                                <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${lockUnlockBooking == 1}">
                                            <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                        </c:if>  
                                    </td>
                                </tr>
                                <script>
                                    $(document).ready(function () {
                                        $("#passengerCounter").val(parseInt("${passenger.count}") + 1);
                                    });
                                </script>
                            </c:forEach>
                            </tbody>
                        </table>
                        <input value="${hotelPassengerList.size()}" id="table-passenger-size" type="hidden">

                        <div id="tr_PassengerAddRow" class="text-center hide" style="padding-top: 10px">
                            <a class="btn btn-success" onclick="PassengerAddRow()">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                    </div>
                </div>
                <!--button save-->
                <div class="text-center">
                    <c:if test="${param.action == 'new'}">
                        <input name="action" value="insert"type="hidden">
                    </c:if>
                    <c:if test="${param.action == 'edit'}">
                        <input name="action" value="update"type="hidden">
                    </c:if>
                    <input name="id" value="${param.id}"type="hidden">
                    <input name="referenceNo" value="${param.referenceNo}"type="hidden">
                    <c:if test="${lockUnlockBooking == 0}">
                        <%--<c:if test="${isBillStatus == 0}">--%>
                            <button id="hotelSave" name="hotelSave" type="submit" class="btn btn-success duplicate" ><span class="fa fa-save"></span> Save</button>
                        <%--</c:if>--%>
                        <%--<c:if test="${isBillStatus == 1}">--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${enableSave == 0}">--%>
                                    <!--<button id="hotelSave" name="hotelSave" type="submit" class="btn btn-success duplicate" ><span class="fa fa-save"></span> Save</button>-->
                                <%--</c:when>--%>
                                <%--<c:when test="${enableSave == 1}">--%>
                                    <!--<button class="btn btn-success disabled" ><span class="fa fa-save"></span> Save</button>-->
                                <%--</c:when>--%>
                            <%--</c:choose>--%> 
                        <%--</c:if>--%>
                    </c:if>
                    <c:if test="${lockUnlockBooking == 1}">
                        <button class="btn btn-success disabled" ><span class="fa fa-save"></span> Save</button>
                    </c:if>    
                </div>
            </form>
        </div>

    </div>
</div>

<c:if test="${! empty param.result}">
    <c:if test="${param.result =='1'}">        
        <script language="javascript">
//            alert("save successful");
            $('#textAlertDivSave').show();
    </script>
    <!--<META HTTP-EQUIV="Refresh" CONTENT="0;URL=HotelDetail.smi?referenceNo=${param.referenceNo}&id=${param.id}&action=edit">-->
</c:if>
<c:if test="${param.result =='0'}">        
    <script language="javascript">
//        alert("save unsuccessful");
            $('#textAlertDivNotSave').show();
    </script>
    <!--<META HTTP-EQUIV="Refresh" CONTENT="0;URL=HotelDetail.smi?referenceNo=${param.referenceNo}&id=${param.id}&action=edit">-->
</c:if>
</c:if>

<!--HOTEL MODAL-->
<div class="modal fade duplicatemodal" id="HotelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Hotel</h4>
            </div>
            <div class="modal-body">
                <!--Hotel List Table-->
                <table class="display" id="HotelTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                            <th class="">Name</th>
                            <th class="">Country</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        hotel = [];
                    </script>
                    <c:forEach var="hotel" items="${hotelList}">
                        <tr>
                            <td class="hotel-id hidden">${hotel.id}</td>
                            <td class="hotel-code">${hotel.code}</td>
                            <td class="hotel-name">${hotel.name}</td>
                            <td class="hotel-country"><c:out value="${fn:toUpperCase(hotel.MCountry.name)}"/></td>
                        </tr>
                        <script>
                            hotel.push({id: "${hotel.id}", code: "${hotel.code}", name: "${hotel.name}", country: "${hotel.MCountry.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--DELETE MODAL-->
<div class="modal fade duplicatemodal" id="DeleteHotel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Hotel Detail</h4>
            </div>
            <div class="modal-body" id="delCode">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="DeleteRow(hotel, rowtype, cId, cCount)" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
