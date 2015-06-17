<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<script type="text/javascript" src="js/DaytourOperationDetail.js"></script> 

<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="dayTourList" value="${requestScope['DayTourList']}" />
<c:set var="daytourPrice" value="${requestScope['DaytourPrice']}" />
<c:set var="daytourPassengers" value="${requestScope['DaytourPassengers']}" />
<c:set var="dayTourDetailList" value="${requestScope['DayTourDetailList']}" />
<c:set var="dayTourOperation" value="${requestScope['DayTourOperation']}" />
<c:set var="dayTourOperationDriver" value="${requestScope['TourDescDriverList']}" />
<c:set var="dayTourOperationExpense" value="${requestScope['TourDescExpenseList']}" />

<c:set var="guildeList" value="${requestScope['GuildeList']}" />
<c:set var="driverList" value="${requestScope['DriverList']}" />
<c:set var="pickupList" value="${requestScope['PickupList']}" />
<c:set var="masterPrice" value="${requestScope['MasterPrice']}" />
<c:set var="masterExpen" value="${requestScope['MasterExpen']}" />
<c:set var="mCurrency" value="${requestScope['MCurrency']}" />
<c:set var="daytour" value="${requestScope['DaytourList']}" />

<input type="hidden" value="${param.referenceNo}" id="getUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<input type="hidden" value="${param.tourID}" id="tourID">

<section class="content-header" >
    <h1>
        Operation - Day Tours Operation Detail
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Operation</a></li>          
        <li class="active"><a href="#">Day Tours Operation Detail</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Book/DaytourMenu.html'"></div>
            <input hidden="" value="${booking_size[0]}" id="input-airticket_size">
            <input hidden="" value="${booking_size[1]}" id="input-hotel_size">
            <input hidden="" value="${booking_size[2]}" id="input-other_size">
            <input hidden="" value="${booking_size[3]}" id="input-land_size">
            <input hidden="" value="${booking_size[4]}" id="input-passenger_size">
            <input hidden="" value="${booking_size[5]}" id="input-billable_size">
        </div>
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Day Tours Operation Detail</b></h4>
                </div>
                <div class="col-sm-6 text-right">
                    <a id="PackageTour" class="btn btn-primary" data-toggle="modal" data-target="#ListPacketModal"> Package Tour</a>
                </div>
            </div>
            <hr/>

            <div class="col-xs-12 form-group"><h4>Tour Detail</h4></div>
            <div class="col-xs-12 form-group">
                <div class="col-xs-1 text-right">
                    <label class="control-label text-right">Tour&nbsp;Code</lable>
                </div>

                <div class="col-xs-3">
                    <input id="InputDetailTourCode" name="InputDetailTourCode"  type="text" 
                           class="form-control" readonly="" value="${daytour.code}">
                </div>
                <div class="col-xs-3 text-right">
                    <label class="control-label text-right">Tour&nbsp;Date</lable>
                </div>
                <div class="col-xs-3">
                    <input id="InputTourDetailTourDate" name="InputTourDetailTourDate"  type="text" 
                           class="form-control" readonly="" value="${dayTourOperation.tourDate}">
                </div>

            </div>
            <div class="col-xs-12 form-group">
                <div class="col-xs-1 text-right">
                    <label class="control-label text-right">&nbsp;&nbsp;&nbsp;&nbsp;Confirm</lable>
                </div>
                <div class="col-xs-2">
                    <input id="InputTourDetailConfirm" name="InputTourDetailConfirm" type="text" style="width: 220px" class="form-control" readonly="" value="${daytour.updateBy}">
                </div>
                <div class="col-xs-1">
                </div>
                <div class="col-xs-3 text-right">
                    <label class="control-label text-right">Min/Max</lable>
                </div>
                <div class="col-xs-1">
                    <input id="InputTourDetailMin" name="InputTourDetailMin" type="text" class="form-control" 
                           readonly="" value="${daytour.min}">
                </div>
                <div class="col-xs-1">
                    <input id="InputTourDetailMax" name="InputTourDetailMax" type="text" class="form-control" 
                           readonly="" value="${daytour.max}">
                </div>
            </div>
            <div class="col-xs-12 form-group"><hr/></div>
            <form action="DaytourOperationDetail.smi" method="post" id="AirticketForm">
                <div role="tabpanel">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#info" aria-controls="info" role="tab" data-toggle="tab">Info</a></li>
                        <li role="presentation" class=""><a href="#master" aria-controls="master" role="tab" data-toggle="tab">Master</a></li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane hidden active" id="info">
                            <div class="col-xs-12 form-group"><h4>Info</h4></div>
                            <div class="col-xs-12 form-group">
                                <div class="col-xs-1 text-right">
                                    <label class="control-label text-right">Guide</label>
                                </div>
                                <div class="col-xs-3">
                                    <select id="SelectGuideCode1" name="SelectGuideCode1"  onchange="$('#InputGuideName1').val($('#SelectGuideCode1 option:selected').text());" class="form-control">
                                        <option></option>
                                        <c:forEach var="item1" items="${guildeList}" >
                                            <c:set var="select1" value="" />
                                            <c:set var="selectedPlaceId1" value="${dayTourOperation.staffByGuide1.id}" />
                                            <c:if test="${item1.id == selectedPlaceId1}">
                                                <c:set var="select1" value="selected" />
                                            </c:if>
                                            <option value="<c:out value="${item1.id}" />" ${select1}><c:out value="${item1.name}" /></option>   
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="col-xs-4">
                                    <input id="InputGuideName1" name="InputGuideName1"   type="text" class="form-control" readonly="" value="${dayTourOperation.staffByGuide1.name}">
                                </div>
                            </div>
                            <div class="col-xs-12 form-group">
                                <div class="col-xs-1 text-right">
                                </div>
                                <div class="col-xs-3">
                                    <select id="SelectGuideCode2" name="SelectGuideCode2" class="form-control" onchange="$('#InputGuideName2').val($('#SelectGuideCode2 option:selected').text());">
                                        <option></option>
                                        <c:forEach var="item2" items="${guildeList}" >
                                            <c:set var="select2" value="" />
                                            <c:set var="selectedPlaceId2" value="${dayTourOperation.staffByGuide2.id}" />
                                            <c:if test="${item2.id == selectedPlaceId2}">
                                                <c:set var="select2" value="selected" />
                                            </c:if>
                                            <option value="<c:out value="${item2.id}" />" ${select2}><c:out value="${item2.name}" /></option>   
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-xs-4">
                                    <input id="InputGuideName2" name="InputGuideName2"  type="text" class="form-control" readonly="" value="${dayTourOperation.staffByGuide2.name}">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12 form-group text-right">
                                    <div class="col-xs-1">
                                        <label class="control-label">Meal</lable>
                                    </div>
                                    <div class="col-xs-5">
                                        <div class="col-xs-12">
                                            <textarea id="TextareaMeal" name="TextareaMeal"  class="form-control" maxlength="255" style="resize: none">${dayTourOperation.meal}</textarea>
                                        </div>
                                    </div>
                                    <div class="col-xs-1">
                                        <label class="control-label">Transfer&nbsp;info</lable>
                                    </div>
                                    <div class="col-xs-5">
                                        <div class="col-xs-12">
                                            <textarea id="TextareaTransferInfo" name="TextareaTransferInfo" class="form-control" maxlength="255" style="resize: none">${dayTourOperation.transferInfo}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12 form-group text-right">
                                    <div class="col-xs-1">
                                        <label class="control-label">Remark</lable>
                                    </div>
                                    <div class="col-xs-5">
                                        <div class="col-xs-12">
                                            <textarea id="TextareaRemark" name="TextareaRemark" class="form-control" maxlength="255" style="resize: none">${dayTourOperation.remark}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">Driver</h4>
                                </div>                    
                                <div class="panel-body">
                                    <table class="display" id="BookingDriverTable">
                                        <thead class="datatable-header">
                                            <tr>
                                                <th>No</th>
                                                <th>Driver</th>
                                                <th>Name</th>
                                                <th>Car&nbsp;No.</th>
                                                <th style="width:30%;">Gas Fee</th>
                                                <th style="width:30%;">Tip Fee</th>
                                                <th style="width:3%;">Action
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="driver" items="${dayTourOperationDriver}" varStatus="status">
                                                <c:choose>
                                                <c:when test="${status.count < 6}">                                               
                                                <tr>
                                                    <td class="hidden">
                                                        <input id="InfoTableId${status.count}" name="InfoTableId${status.count}" value="${driver.id}">
                                                        <input id="countInfoTable${status.count}" name="countInfoTable" value="${status.count+1}">
                                                    </td>
                                                    <td class="text-center">${status.count}</td>
                                                    <td>
                                                        <select id="SelectTableDrive${status.count}" name="SelectTableDrive${status.count}" class="">
                                                            <option></option>
                                                            <c:forEach var="dr" items="${driverList}" >
                                                                <c:set var="select1" value="" />
                                                                <c:set var="selectedPlaceId1" value="${driver.staff.id}" />
                                                                <c:if test="${dr.id == selectedPlaceId1}">
                                                                    <c:set var="select1" value="selected" />
                                                                </c:if>
                                                                <option value="<c:out value="${dr.id}" />" ${select1}><c:out value="${dr.name}" /></option>   
                                                            </c:forEach>

                                                        </select>
                                                        <script>
                                                            $('#SelectTableDrive${status.count}').selectize({
                                                                sortField: 'text'
                                                            });
                                                            $("#SelectTableDrive${status.count}").change(function () {
                                                                var driverId = $(this).val();
                                                                console.log(driverId);
                                                                $.each(driver, function (i, dri) {
                                                                    if (driverId == dri.id) {
                                                                        var id = dri.id;
                                                                        var name = dri.name;
                                                                        var username = dri.username;
                                                                        var car = dri.car;
                                                                        console.log(name);
                                                                        $("#driverName${status.count}").text(name);
                                                                        $("#driverCarNo${status.count}").text(car);
                                                                        $("#carNo${status.count}").val(car);
                                                                    }
                                                                });
                                                            });
                                                        </script>
                                                    </td>
                                                    <td id="driverName${status.count}">${driver.staff.name}</td>
                                                    <td><div id="driverCarNo${status.count}">${driver.carNo}</div><input name="carNo${status.count}" id="carNo${status.count}" value="${driver.carNo}" type="hidden"></td>
                                                    <td>
                                                        <div class="col-xs-7">
                                                            <input id="InfoTableGasFee${status.count}" name="InfoTableGasFee${status.count}" class="form-control" value="${driver.gasFee}">
                                                        </div>
                                                        <div class="col-xs-5">
                                                            <input id="InfoTableValue"${status.count} name="InfoTableGasValue${status.count}" class="form-control money" value="${driver.gasValue}">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="col-xs-7">
                                                            <input id="InfoTableTipFee${status.count}" name="InfoTableTipFee${status.count}" class="form-control" value="${driver.tipFee}">
                                                        </div>
                                                        <div class="col-xs-5">
                                                            <input id="InfoTableTipValue${status.count}" name="InfoTableTipValue${status.count}" class="form-control money" value="${driver.tipValue}">
                                                        </div>
                                                    </td>
                                                    <td class="text-center">
                                                        <a href="#" onclick="setDriverId(${driver.id},'${driver.staff.name}')"  data-toggle="modal" data-target="#DeleteDriverModal">
                                                            <span id="spanRemove${status.count}" class="glyphicon glyphicon-remove deleteicon"></span>
                                                        </a>
                                                    </td>
                                                </tr>
                                                </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div> 
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">Ref Booking</h4>
                                </div>                    
                                <div class="panel-body">

                                    
                                    <table class="display" id="RefBookTable">
                                        <thead class="datatable-header">
                                            <tr>
                                                <th style="width:5%;">Pu</th>
                                                <th style="width:10%;">Ref. No</th>
                                                <th>Agent</th>
                                                <th>AD</th>
                                                <th>CH</th>
                                                <th>IN</th>
                                                <th>Remark</th>
                                                <th>Pick up</th> 
                                                <th>Room</th>
                                                <th>Time</th>    
                                                <th>Pay</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="dayTourDetai" items="${dayTourDetailList}" varStatus="status">
                                                <tr>
                                                    <td>
                                                        <input type="text" id="InputPu${status.count}" name="InputPu${status.count}" class="form-control money" value="${dayTourDetai.pickupOrder}" />
                                                        <input type="hidden" id="countPu${status.count}" name="countPu"  value="${status.count}" />
                                                        <input type="hidden" id="refBookId${status.count}" name="refBookId${status.count}"  value="${dayTourDetai.id}" />
                                                    </td>
                                                    <c:set var="refno1" value="${fn:substring(dayTourDetai.master.referenceNo, 0, 2)}" />
                                                    <c:set var="refno2" value="${fn:substring(dayTourDetai.master.referenceNo, 2,7)}" />
                                                    <td>${refno1}-${refno2}</td>
                                                    <td>${dayTourDetai.master.agent.name}</td>
                                                    <td class="sumConfirm">${dayTourDetai.adult}</td>
                                                    <td class="sumConfirm">${dayTourDetai.child}</td>
                                                    <td class="sumConfirm">${dayTourDetai.infant}</td>
                                                    <td>${dayTourDetai.remark}</td>
                                                    <td>${dayTourDetai.place.place eq 'OTHERS' ? dayTourDetai.pickupDetail : dayTourDetai.place.place}</td>
                                                    <td>${dayTourDetai.pickupRoom}</td>
                                                    <td>${dayTourDetai.pickupTime}</td>
                                                    <td class="text-center">${dayTourDetai.isPay eq 1  ? 'Y' : 'N'}</td>
                                                    <td class="text-center">
                                                        <span id="RefBookTableButtonEdit" name="RefBookTableButtonEdit" class="glyphicon glyphicon-edit editicon" onclick="window.open('/SMITravel/DaytourDetail.smi?referenceNo=${dayTourDetai.master.referenceNo}&action=edit&daytourBooking=${dayTourDetai.id}');"></span>
                                                        <a data-toggle="collapse" href="#collapseExample${status.count}" aria-expanded="false" aria-controls="collapseExample${status.count}">
                                                            <span id="SpanEdit${status.count}" class="glyphicon glyphicon-list-alt"></span>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <c:forEach var="dayTourDetai" items="${dayTourDetailList}" varStatus="status">
                                    <div class="collapsing" id="collapseExample${status.count}" aria-expanded="false">
                                        <label class="control-label" style="margin-top: 10px;margin-left: 20px;">Passenger</label>
                                        <table class="display" style="width:97%;margin-top: 10px;">
                                            <thead class="datatable-header">
                                                <tr>
                                                    <th>No</th>
                                                    <th>Code</th>
                                                    <th>Name</th>
                                                    <th>Tel</th>
                                                    <th>Nationality</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="passenger" items="${daytourPassengers}" varStatus="status">
                                                    <tr>
                                                        <td>${status.count}</td>
                                                        <td>${passenger.customer.code}</td>
                                                        <c:if test="${passenger.customer.MInitialname != null}">
                                                            <td>${passenger.customer.MInitialname.name}  ${passenger.customer.lastName} ${passenger.customer.firstName} </td>
                                                        </c:if>
                                                        <c:if test="${passenger.customer.MInitialname == null}">
                                                            <td>${passenger.customer.lastName} ${passenger.customer.firstName} </td>
                                                        </c:if>    
                                                        
                                                        <td>${passenger.customer.tel}</td>
                                                        <td>${passenger.customer.nationality}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>




                                        <label class="control-label" style="margin-top: 20px;margin-left: 20px;">Price</label>
                                        <table class="display" style="width:97%;margin-top: 10px; margin-bottom: 20px">
                                            <thead class="datatable-header">
                                                <tr>
                                                    <th>Category</th>
                                                    <th>Detail</th>
                                                    <th>Price</th>
                                                    <th>Qty</th>
                                                    <th>Total Price</th>
                                                    <th>Cur</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="price" items="${dayTourDetai.daytourBookingPrices}">
                                                    <tr>
                                                        <td>${price.MPricecategory.name}</td>
                                                        <td>${price.detail}</td>
                                                        <td class="money">${price.price}</td>
                                                        <td class="text-center">${price.qty}</td>
                                                        <td class="money">${price.price*price.qty}</td>
                                                        <td class="text-center">${price.currency}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div> <!--collapse -->
                                </c:forEach>

                            </div>            
                            <div class="col-xs-12 form-group"><hr/></div>
                            <div class="col-sm-6">
                                <h4>Booking Expense</h4>
                            </div>
                            <div class="col-sm-6 text-right">
                                <a href="#" id="ButtonImportTourExpense" name="ButtonImportTourExpense" class="btn btn-success" data-toggle="modal" data-target="#ExpenseModal">
                                    <i class="glyphicon glyphicon-plus"></i>&nbsp;Import
                                </a>
                            </div>
                            <table class="display" id="BookingExpenseTable">
                                <thead class="datatable-header">
                                    <tr>
                                        <th>Description</th>
                                        <th>Qty</th>
                                        <th>Amount</th>
                                        <th>Type</th>
                                        <th>Cur</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="guideBill" value="0"/>
                                    <c:set var="total" value="0"/>
                                    <c:forEach var="expen" items="${dayTourOperationExpense}" varStatus="status">
                                        <tr>
                                            <td class="hidden">
                                                <input id="expenId${status.count}" name="expenId${status.count}" class="form-control" value="${expen.id}">
                                                <input id="countExpen${status.count}" name="countExpen" class="form-control" value="${status.count}">
                                            </td>
                                            <td><input id="expenDescription${status.count}" name="expenDescription${status.count}" class="form-control" value="${expen.description}"></td>
                                            <td style="width: 80px"><input id="expenQty${status.count}" name="expenQty${status.count}" class="form-control money" value="${expen.qty}"></td>
                                            <td style="width: 100px"><input id="expenAmount${status.count}" name="expenAmount${status.count}" class="form-control money" value="${expen.amount}"></td>
                                            <td>
                                                <input type="hidden" value="${expen.priceType}" id="expenPriceTypeHiden${status.count}">
                                                <input id="expenTypeS${status.count}" type="radio" value="S" name="expenPriceType${status.count}">&nbsp;&nbsp;S&nbsp;&nbsp;&nbsp;
                                                <input id="expenTypeG${status.count}" type="radio" value="G" name="expenPriceType${status.count}">&nbsp;&nbsp;G
                                                <script>
                                                    $(document).ready(function () {
                                                        var status = $("#expenPriceTypeHiden${status.count}").val();
                                                        $("input[name=expenPriceType${status.count}][value=" + status + "]").attr('checked', 'checked');
                                                    });
                                                </script>

                                            </td>
                                            <td>                            
                                                <select name="expenSelectCur${status.count}" id="expenSelectCur${status.count}" class="form-control">
                                                    <option value="${expen.currency}">${expen.currency}</option>
                                                    <c:forEach var="price" items="${mCurrency}" varStatus="status">
                                                        <c:if test="${expen.currency != price.code}">
                                                            <option value="${price.code}">${price.code}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>

                                            </td>
                                            <td class="text-center">
                                                <a id="expenButtonRemove${status.count}" name="expenButtonRemove${status.count}" onclick="setExpenId(${expen.id})"  data-toggle="modal" data-target="#DeleteExpenModal">
                                                    <span id="expenSpanRemove${status.count}" name="expenSpanRemove${status.count}"  class="glyphicon glyphicon-remove deleteicon"></span>
                                                </a>
                                            </td>
                                        </tr>
                                        <c:if test="${expen.priceType == 'G'}">
                                            <c:set var="guideBill" value="${guideBill +(expen.qty * expen.amount)}"/>
                                        </c:if>
                                        <c:set var="total" value="${total +(expen.qty * expen.amount)}"/>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="col-xs-12 form-group" style="padding-top: 10px">
                                <div class="col-xs-1">
                                    <label class="control-label">Guide&nbsp;bill</label>
                                </div>
                                <div class="col-xs-5">
                                    <input id="InputGuideBill" name="InputGuideBill" class="form-control money" readonly="" value="${guideBill}">
                                </div>
                                <div class="col-xs-1 text-right">
                                    <label class="control-label">Total</label>
                                </div>
                                <div class="col-xs-5">
                                    <input id="InputTotal" name="InputTotal" class="form-control money" readonly="" value="${total}">
                                </div>
                            </div>
                            <div class="text-center" style="padding-top: 10px">
                                <a id="ButtonPrint" name="ButtonPrint" onclick="printGuideJob();" class="btn btn-primary"><i class="fa fa-print"></i> Print</a>
                                <input type="hidden" name="action" value="update" />
                                <input type="hidden" name="tourID" value="${param.tourID}" />
                                <input type="hidden" name="tourDate" value="${param.tourDate}" />
                                <button type="submit" id="ButtonSave" name="ButtonSave" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane hidden" id="master">
                            <div class="form-group">
                                <h4>Price</h4>
                                <table class="display">
                                    <thead class="datatable-header">
                                        <tr>
                                            <th>Category</th>
                                            <th>Detail</th>
                                            <th>Price</th>
                                            <th>Cur</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="mPrice" items="${masterPrice}" varStatus="status">
                                            <tr>
                                                <td class="text-center">${mPrice.MPricecategory.name}</td>
                                                <td>${mPrice.detail}</td>
                                                <td class="money">${mPrice.price}</td>
                                                <td class="text-center">${mPrice.currency}</td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                            <div class="form-group">
                                <h4>Expense</h4>
                                <table class="display">
                                    <thead class="datatable-header">
                                        <tr>
                                            <th>Description</th>
                                            <th>Amount</th>
                                            <th>Cur</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="mExpen" items="${MasterExpen}" varStatus="status">
                                            <tr>
                                                <td class="text-left">${mExpen.description}</td>
                                                <td class="money">${mExpen.amount}</td>
                                                <td class="text-center">${mExpen.currency}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </form>                        
        </div>
    </div>

</div>

<!--Booking Modal-->
<div class="modal fade" id="BookingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Booking Detail</h4>
            </div>
            <div class="modal-body">
                Passenger
                <table class="display">
                    <thead class="datatable-header">
                        <tr>
                            <th>No</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Room</th>
                            <th>Tel</th>
                            <th>Nationality</th>
                            <th>Leader</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="text-center">1</td>
                            <td>Y0002463</td>
                            <td>YAMAMOTO HITOMI</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="text-center">Y</td>
                        </tr>
                    </tbody>
                </table>
                Price
                <table class="display">
                    <thead class="datatable-header">
                        <tr>
                            <th>Category</th>
                            <th>Detail</th>
                            <th>Cost</th>
                            <th>Qty</th>
                            <th>Price</th>
                            <th>Cur</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>ADULT</td>
                            <td>B07 17% OFF</td>
                            <td class="money"></td>
                            <td class="text-center">2</td>
                            <td class="money">1,148.00</td>
                            <td class="text-center">THB</td>
                        </tr>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="BookingModalOK" name="BookingModalOK" type="button" onclick="" class="btn btn-success">OK</button>
                <button id="BookingModalClose" name="BookingModalClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Expense Modal-->
<div class="modal fade" id="ExpenseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Tour Expense</h4>
            </div>
            <div class="modal-body" id="">
                <table class="display" id="toureExpenTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>ID</th>
                            <th>Description</th>
                            <th>Amount</th>
                            <th>Cur</th>
                            <th>Action <input onclick="expenseAll(this);" type="checkbox"></th>
                            <th class="hidden"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="mExpen" items="${MasterExpen}" varStatus="expenStatus">
                            <tr id="expenID-${mExpen.id}">
                                <td id="rowImportExpenId">${mExpen.id}</td>
                                <td id="rowImportExpenDescription">${mExpen.description}</td>
                                <td id="rowImportExpenAmount"class="money">${mExpen.amount}</td>
                                <td id="rowImportExpenCurrency" class="text-center">${mExpen.currency}</td>
                                <td class="text-center"><input id="inputCheckbook${expenStatus.count}" type="checkbox"></td>
                                <td id="rowImportExpenPriceType" class="hidden">${mExpen.priceType}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="ExpenseModalButtonOK" name="ExpenseModalButtonOK" onclick="importExpen()" type="button" onclick="" class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="ExpenseModalButtonClose" name="ExpenseModalButtonClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->

<!--List Packet Tour Modal-->
<div class="modal fade" id="ListPacketModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">List Package Tour</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="PacketTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Date</th>
                            <th>Code</th>
                            <th>Action</th>
                            <th class="hidden">guide</th>
                             <th class="hidden">id</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${dayTourList}" varStatus="dayStatus">
                            <tr class="packet">
                                <td class="pack-date">${table.tourDate}</td>
                                <td>${table.daytour.code}</td>
                                <td class="text-center">
                                    <a href="DaytourOperationDetail.smi?action=edit&tourID=${table.daytour.id}&tourDate=${table.tourDate}">
                                        <span class="glyphicon glyphicon-check"></span>
                                    </a>
                                </td>
                                <td class="hidden pack-guide">${table.guide}</td>
                                <td class="hidden pack-id">${table.id}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="ListPacketModalOK" name="ListPacketModalOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="ListPacketModalClose" name="ListPacketModalClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->



<!--Delete Driver Modal-->
<div class="modal fade" id="DeleteDriverModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Tour Driver</h4>
            </div>
            <div class="modal-body">
               <div id="driverNameDelete"></div>
            </div>
            <div class="modal-footer">
                <a id="btnExpenDeleteOK"  type="button" onclick="deleteBookDriver()" data-dismiss="modal" class="btn btn-danger">Delete</a>
                <a id="btnExpenDeleteClose"  type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->

<!--Delete Expense Modal-->
<div class="modal fade" id="DeleteExpenModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Tour Expense</h4>
            </div>
            <div class="modal-body">
                Are you sure to delete Booking Expense ?
            </div>
            <div class="modal-footer">
                <a id="btnExpenDeleteOK"  type="button" onclick="deleteBookExpen()" data-dismiss="modal" class="btn btn-danger">Delete</a>
                <a id="btnExpenDeleteClose"  type="button" class="btn btn-default" data-dismiss="modal">Close</a>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->



<script> driver = []</script>
<c:forEach var="dr" items="${driverList}">
    <script>
        driver.push({
            id: "${dr.id}",
            name: "${dr.name}",
            username: "${dr.username}",
            car: "${dr.car}",
        });
    </script>
</c:forEach>

<c:if test="${! empty requestScope['resultStatus']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['resultStatus']}" />');
    </script>
    <META HTTP-EQUIV="Refresh" CONTENT="0;URL=${requestScope['redirectUrl']}">
</c:if>

<style>

</style>