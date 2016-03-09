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
<c:set var="tourList" value="${requestScope['TourList']}" />
<c:set var="statusList" value="${requestScope['StatusList']}" />
<c:set var="invoiceSupList" value="${requestScope['InvoiceSupList']}" />
<c:set var="paymentWendyList" value="${requestScope['PaymentWendyList']}" />
<c:set var="paymentWendyDetailList" value="${requestScope['PaymentWendyDetailList']}" />
<c:set var="mDepartmentName" value="${requestScope['mDepartmentName']}" />
<input type="hidden" value="${mDepartmentName}" id="MDname">
<input type="hidden" value="${param.referenceNo}" id="getUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<input type="hidden" value="${param.tourID}" id="tourID">
<script>
    var guideChk = [];
</script>
<c:forEach var="table" items="${guildeList}">
    <script>
        guideChk.push({name: "${table.name}"});
    </script>
</c:forEach>

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
        <!--Alert Save and Update-->
        <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Success!</strong> 
        </div>
        <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Unsuccess!</strong> 
        </div>
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

            <div class="col-xs-12 form-group" style="margin-top: -10px;"><h4>Tour Detail</h4></div>
            <form action="DaytourOperationDetail.smi" method="post" id="DaytourOperationForm" name="DaytourOperationForm" role="form">
                <div class="col-xs-12 " style="margin-top: -10px;">
                    <div class="col-xs-1 text-right">
                        <label class="control-label" for="InputDetailTourCode">Tour&nbsp;Code<font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-2 form-group">
                        <div class="col-sm-12">
                            <div class="input-group" id="CodeValidate">
                                <input name="InputDetailTourCode" id="InputDetailTourCode" type="text" 
                                       class="form-control" value="${daytour.code}" />
                                <input name="InputDetailTourId" id="InputDetailTourId" type="hidden" 
                                       class="form-control" value="${daytour.id}" />
                                <span class="input-group-addon" data-toggle="modal" data-target="#TourModal">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="col-sm-12">
                            <input name="InputDetailTourName" id="InputDetailTourName"  type="text" style="width: 200px" class="form-control" readonly="" value="${daytour.name}">
                        </div>
                    </div>              
                    <div class="col-md-2 text-right">
                        <label class="control-label text-right" for="InputTourDetailTourDate">Tour&nbsp;Date<font style="color: red">*</font></lable>
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
                    <div class="col-md-2 text-right ">
                        <input type="hidden" id="InputTourId" name="tourID" >
                        <input type="hidden" id="tourDate" name="tourDate" >
                        <input type="hidden" name="action" id="action" value="edit">
                        <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="checkUpdTour();" class="btn btn-primary btn-sm">Search</button>

                        <!--                        <button id="BtnTourSearch" class="btn btn-success" type="submit"><span class="fa fa-search"></span> Search</button>
                                                <input type="hidden" name="action" id="action" value="edit">-->
                    </div>
                </div>

                <div class="col-xs-12 form-group" style="margin-top: -10px;">
                    <div class="col-xs-1 text-right">
                        <label class="control-label" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Confirm</lable>
                    </div>
                    <div class="col-md-2 form-group">
                        <div class="col-sm-12">
                            <input id="InputTourDetailConfirm" name="InputTourDetailConfirm" type="text" style="width: 370px" class="form-control" readonly="" value="${daytour.updateBy}">
                        </div>
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
            </form>
            <div class="col-xs-12 form-group" style="margin-top: -20px;"><hr/></div>
            <form action="DaytourOperationDetail.smi" method="post" id="AirticketForm">
                <div role="tabpanel" style="margin-top: -10px;">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#info" aria-controls="info" role="tab" data-toggle="tab">Info</a></li>
                        <li role="presentation" class=""><a href="#master" aria-controls="master" role="tab" data-toggle="tab">Master</a></li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane hidden active" id="info">
                            <div class="col-xs-12 form-group"><h4>Info</h4></div>
                            <input type="hidden" name="mDepartmentName" id="mDepartmentName" value="${mDepartmentName}" class="form-group"/>
                            <c:choose>
                                <c:when test="${fn:containsIgnoreCase(mDepartmentName , 'tour')}">    
                                    <div class="col-xs-12 form-group" style="margin-top: -20px;">
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
                                                    <option id="GuideName1" value="<c:out value="${item1.id}" />" ${select1}><c:out value="${item1.name}" /></option>   
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-xs-4">
                                            <input id="InputGuideName1" name="InputGuideName1"   type="text" class="form-control" readonly="" value="${dayTourOperation.staffByGuide1.name}">
                                        </div>
                                    </div>
                                    <div class="col-xs-12 form-group" style="margin-top: -10px;">
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
                                        <div class="col-xs-12 form-group text-right" style="margin-top: -10px;">
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
                                        <div class="col-xs-12 form-group text-right" style="margin-top: -10px;">
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
                                                                        sortField: 'text',
                                                                        dropdownParent: 'body'
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
                                                                            <input id="InfoTableTipValue${status.count}" name="InfoTableTipValue${status.count}" class="form-control money" value="${driver.tipValue}" onfocusout="calculateGuideBill()">
                                                                        </div>
                                                                    </td>
                                                                    <td class="text-center">
                                                                        <a href="#" onclick="setDriverId(${driver.id}, '${driver.staff.name}')"  data-toggle="modal" data-target="#DeleteDriverModal">
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
                                                        <th>Family Leader</th>
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
                                                                <input type="hidden" id="masterId${status.count}" name="masterId${status.count}"  value="${dayTourDetai.master.id}" />
                                                            </td>
                                                            <c:set var="refno1" value="${fn:substring(dayTourDetai.master.referenceNo, 0, 2)}" />
                                                            <c:set var="refno2" value="${fn:substring(dayTourDetai.master.referenceNo, 2,7)}" />
                                                            <td>${refno1}-${refno2}</td>
                                                            <td>${dayTourDetai.master.customer.MInitialname.name} ${dayTourDetai.master.customer.lastName} ${dayTourDetai.master.customer.firstName}</td>
                                                            <td class="sumConfirm">${dayTourDetai.adult}</td>
                                                            <td class="sumConfirm">${dayTourDetai.child}</td>
                                                            <td class="sumConfirm">${dayTourDetai.infant}</td>
                                                            <td>${dayTourDetai.remark}</td>
                                                            <td>${dayTourDetai.place.place eq 'OTHERS' ? dayTourDetai.pickupDetail : dayTourDetai.place.place}</td>
                                                            <td>${dayTourDetai.pickupRoom}</td>
                                                            <td>${fn:substring(dayTourDetai.pickupTime,0,5)}</td>
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
                                </c:when>
                                <c:when test="${fn:containsIgnoreCase(mDepartmentName , 'checking wendy')}">    
                                    <div class="col-xs-12 form-group">
                                        <div class="col-xs-1 text-right">
                                            <label class="control-label text-right">Guide</label>
                                        </div>
                                        <div class="col-xs-3">
                                            <select id="SelectGuideCode1Show" name="SelectGuideCode1Show"  onchange="$('#InputGuideName1').val($('#SelectGuideCode1 option:selected').text());" class="form-control" disabled="">
                                                <option></option>
                                                <c:forEach var="item1" items="${guildeList}" >
                                                    <c:set var="select1" value="" />
                                                    <c:set var="selectedPlaceId1" value="${dayTourOperation.staffByGuide1.id}" />
                                                    <c:if test="${item1.id == selectedPlaceId1}">
                                                        <c:set var="select1" value="selected" />
                                                    </c:if>
                                                    <option id="GuideName1" value="<c:out value="${item1.id}" />" ${select1}><c:out value="${item1.name}" /></option>   
                                                </c:forEach>
                                            </select>
                                            <select id="SelectGuideCode1" name="SelectGuideCode1"  onchange="$('#InputGuideName1').val($('#SelectGuideCode1 option:selected').text());" class="form-control hidden">
                                                <option></option>
                                                <c:forEach var="item1" items="${guildeList}" >
                                                    <c:set var="select1" value="" />
                                                    <c:set var="selectedPlaceId1" value="${dayTourOperation.staffByGuide1.id}" />
                                                    <c:if test="${item1.id == selectedPlaceId1}">
                                                        <c:set var="select1" value="selected" />
                                                    </c:if>
                                                    <option id="GuideName1" value="<c:out value="${item1.id}" />" ${select1}><c:out value="${item1.name}" /></option>   
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
                                            <select id="SelectGuideCode2Show" name="SelectGuideCode2Show" class="form-control" onchange="$('#InputGuideName2').val($('#SelectGuideCode2 option:selected').text());" disabled="">
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
                                            <select id="SelectGuideCode2" name="SelectGuideCode2" class="form-control hidden" onchange="$('#InputGuideName2').val($('#SelectGuideCode2 option:selected').text());">
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
                                                    <textarea id="TextareaMeal" name="TextareaMeal"  class="form-control" maxlength="255" style="resize: none" readonly="">${dayTourOperation.meal}</textarea>
                                                </div>
                                            </div>
                                            <div class="col-xs-1">
                                                <label class="control-label">Transfer&nbsp;info</lable>
                                            </div>
                                            <div class="col-xs-5">
                                                <div class="col-xs-12">
                                                    <textarea id="TextareaTransferInfo" name="TextareaTransferInfo" class="form-control" maxlength="255" style="resize: none" readonly="">${dayTourOperation.transferInfo}</textarea>
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
                                                    <textarea id="TextareaRemark" name="TextareaRemark" class="form-control" maxlength="255" style="resize: none" readonly="">${dayTourOperation.remark}</textarea>
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
                                                                        <select id="SelectTableDriveShow${status.count}" name="SelectTableDriveShow${status.count}" class="" disabled="">
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
                                                                        <select id="SelectTableDrive${status.count}" name="SelectTableDrive${status.count}" class="hidden">
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
                                                                        sortField: 'text',
                                                                        dropdownParent: 'body'
                                                                    });
                                                                    $('#SelectTableDriveShow${status.count}').selectize({
                                                                        sortField: 'text',
                                                                        dropdownParent: 'body'
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
                                                                    $("#SelectTableDriveShow${status.count}").change(function () {
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
                                                                            <input id="InfoTableGasFee${status.count}" name="InfoTableGasFee${status.count}" class="form-control" value="${driver.gasFee}" readonly="">
                                                                        </div>
                                                                        <div class="col-xs-5">
                                                                            <input id="InfoTableValue"${status.count} name="InfoTableGasValue${status.count}" class="form-control money" value="${driver.gasValue}" readonly="">
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="col-xs-7">
                                                                            <input id="InfoTableTipFee${status.count}" name="InfoTableTipFee${status.count}" class="form-control" value="${driver.tipFee}" >
                                                                        </div>
                                                                        <div class="col-xs-5">
                                                                            <input id="InfoTableTipValue${status.count}" name="InfoTableTipValue${status.count}" class="form-control money" value="${driver.tipValue}" onfocusout="calculateGuideBill()">
                                                                        </div>
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
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="dayTourDetai" items="${dayTourDetailList}" varStatus="status">
                                                        <tr>
                                                            <td>
                                                                <input type="text" id="InputPu${status.count}" name="InputPu${status.count}" class="form-control money" value="${dayTourDetai.pickupOrder}" readonly=""/>
                                                                <input type="hidden" id="countPu${status.count}" name="countPu"  value="${status.count}" />
                                                                <input type="hidden" id="refBookId${status.count}" name="refBookId${status.count}"  value="${dayTourDetai.id}" />
                                                                <input type="hidden" id="masterId${status.count}" name="masterId${status.count}"  value="${dayTourDetai.master.id}" />
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
                                                            <td>${fn:substring(dayTourDetai.pickupTime,0,5)}</td>
                                                            <td class="text-center">${dayTourDetai.isPay eq 1  ? 'Y' : 'N'}</td>                                                            
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
                                </c:when>
                            </c:choose>
                            
                            <c:choose>
                                <c:when test="${fn:containsIgnoreCase(mDepartmentName , 'tour')}">   
                                    <div class="col-xs-12 form-group" style="margin-top: -10px;"><hr/></div>
                                    <div class="col-sm-6" style="margin-top: -10px;">
                                        <h4>Booking Expense</h4>
                                    </div>
                                    <div class="col-sm-6 text-right" style="margin-top: -10px;">
                                        <a href="#" id="ButtonImportTourExpense" name="ButtonImportTourExpense" class="btn btn-success disabled" data-toggle="modal" >
                                            <i class="glyphicon glyphicon-plus"></i>&nbsp;Import
                                        </a>
                                    </div>
                                    <table class="display" id="BookingExpenseTable">
                                        <thead class="datatable-header">
                                            <tr>
                                                <th>Description</th>
                                                <th>Qty</th>
                                                <th>Amount</th>
                                                <th class="hidden">Cur</th>
                                                <th>Type</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:set var="guideBill" value="0"/>
                                            <c:set var="total" value="0"/>
                                            <c:forEach var="expen" items="${dayTourOperationExpense}" varStatus="i">
                                                <tr>
                                                    <td class="hidden">
                                                        <input id="expenId${i.count}" name="expenId${i.count}" class="form-control" value="${expen.id}">
                                                        <input id="countExpen${i.count}" name="countExpen" class="form-control" value="${i.count}">
                                                    </td>
                                                    <td>${expen.description}</td>
                                                    <td style="width: 80px">
                                                        <input id="expenQty${i.count}" name="expenQty${i.count}" 
                                                               class="form-control money" value="${expen.qty}" readonly="">
                                                    </td>
                                                    <td style="width: 100px">
                                                        <input id="expenAmount${i.count}" name="expenAmount${i.count}" 
                                                               class="form-control money" value="${expen.amount}" readonly="">
                                                    </td>
        <!--                                            <td>                            
                                                        <select name="expenSelectCur${i.count}" id="expenSelectCur${i.count}" class="form-control">
                                                            <option value="${expen.currency}">${expen.currency}</option>
                                                            <c:forEach var="price" items="${mCurrency}" varStatus="status">
                                                                <c:if test="${expen.currency != price.code}">
                                                                    <option value="${price.code}">${price.code}</option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </td>-->
                                                    <td class="hidden">
                                                       <select id="expenSelectCur${i.count}" name="expenSelectCur${i.count}" class="">
                                                           <option></option>
                                                           <c:forEach var="price" items="${mCurrency}" >
                                                               <c:set var="select1" value="" />
                                                               <c:set var="selectedPlaceId1" value="${driver.staff.id}" />
                                                               <c:if test="${expen.currency == price.code}">
                                                                   <c:set var="select1" value="selected" />
                                                               </c:if>
                                                               <option value="<c:out value="${price.code}" />" ${select1}><c:out value="${price.code}" /></option>   
                                                           </c:forEach>
                                                       </select>
                                                       <script>
                                                           $("#expenSelectCur${i.count}").selectize({
                                                               sortField: 'text'

                                                           });
                                                       </script>
                                                   </td>
                                                    <td class="text-center">${expen.priceType}</td>

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
                                    <div class="col-xs-12 form-group"><hr/></div>
                                    <div class="col-xs-12 form-group" style="padding-top: 10px">                               
                                        <div id="textAlertDivGuideName" style="display:none; color: red">

                                        </div>
                                    </div>                               
                                    <div class="col-xs-12 form-group" style="margin-top: -10px;">
                                        <div class="col-sm-6" style="margin-top: -30px;">
                                            <h4>Guide Bill</h4>
                                        </div>
                                    </div>
                                    <div id="GuideBillForm">
                                        <div class="col-xs-12 form-group" style="margin-top: -10px;">
                                            <div class="col-xs-1">
                                                <label class="control-label">Payno</label>                                                                                  
                                            </div>
                                            <div class="col-xs-1" style="width: 250px">                                       
                                                <input class="form-control" type="text" id="PayNoGuideBill" name="PayNoGuideBill" value="${paymentWendyList.payNo}" readonly="">
                                            </div>
                                            <div class="col-xs-1 text-right" style="width: 120px">
                                                <label class="control-label">Invoice&nbsp;Sup</label>
                                            </div>
                                            <div class="col-xs-1" style="width: 250px">
                                                <input class="form-control" type="hidden" id="guideName" name="guideName" value="${requestScope['guideName']}" readonly="">
                                                <select id="InvoiceSupGuideBillShow" name="InvoiceSupGuideBillShow" class="form-control" onchange="setGuideName('','check')" disabled="">
                                                    <option id="" value="">---------</option>
                                                </select>
                                                <select id="InvoiceSupGuideBill" name="InvoiceSupGuideBill" class="form-control hidden" onchange="setGuideName('','check')">
                                                    <option id="" value="">---------</option>
                                                </select>
                                            </div>
                                            <div class="col-xs-1 text-right" style="width: 120px">
                                                <label class="control-label">Currency</label>
                                            </div>
                                            <div class="col-xs-1" style="width: 150px">
                                                <select id="SelectCur" name="SelectCurShow" class="form-control" disabled="">
                                                    <option id="" value="">---------</option>
                                                    <c:forEach var="price" items="${mCurrency}" >
                                                        <c:set var="select1" value="" />
                                                            <c:if test="${paymentWendyList.currency == price.code}">
                                                                <c:set var="select1" value="selected" />
                                                            </c:if>
                                                        <option value="<c:out value="${price.code}" />" ${select1}><c:out value="${price.code}" /></option>   
                                                    </c:forEach>
                                                </select>
                                                <select id="SelectCur" name="SelectCur" class="form-control hidden">
                                                    <option id="" value="">---------</option>
                                                    <c:forEach var="price" items="${mCurrency}" >
                                                        <c:set var="select1" value="" />
                                                            <c:if test="${paymentWendyList.currency == price.code}">
                                                                <c:set var="select1" value="selected" />
                                                            </c:if>
                                                        <option value="<c:out value="${price.code}" />" ${select1}><c:out value="${price.code}" /></option>   
                                                    </c:forEach>
                                                </select>
                                                <script>
                                                    $("#expenSelectCur${i.count}").selectize({
                                                        sortField: 'text'

                                                    });
                                                </script>
                                            </div>
                                        </div>
                                        <div class="col-xs-12 form-group" style="margin-top: -10px;">
                                            <div class="col-xs-1">
                                                <label class="control-label">Status</label>
                                            </div>
                                            <div class="col-xs-1" style="width: 250px">
                                                <select class="form-control" id="StatusGuideBillShow" name="StatusGuideBill" disabled="">
                                                    <option id="" value="">---------</option>  
                                                    <c:forEach var="status" items="${statusList}">
                                                        <c:set var="select2" value="" />
                                                        <c:if test="${status.id == paymentWendyList.MItemstatus.id}">
                                                            <c:set var="select2" value="selected" />
                                                        </c:if>
                                                        <option value="<c:out value="${status.id}" />" ${select2}><c:out value="${status.name}" /></option>                                         
                                                    </c:forEach>
                                                </select>
                                                <select class="form-control hidden" id="StatusGuideBill" name="StatusGuideBill" >
                                                    <option id="" value="">---------</option>  
                                                    <c:forEach var="status" items="${statusList}">
                                                        <c:set var="select2" value="" />
                                                        <c:if test="${status.id == paymentWendyList.MItemstatus.id}">
                                                            <c:set var="select2" value="selected" />
                                                        </c:if>
                                                        <option value="<c:out value="${status.id}" />" ${select2}><c:out value="${status.name}" /></option>                                         
                                                    </c:forEach>
                                                </select>    
                                            </div>
                                            <div class="col-xs-1 text-right" style="width: 120px">
                                                <label class="control-label">Amount</label>
                                            </div>
                                            <div class="col-xs-1" style="width: 250px">
                                                <input class="form-control money" type="text" id="AmountGuideBill" name="AmountGuideBill" value="${paymentWendyDetailList.amount}" readonly="">
                                                <input class="form-control numerical" type="hidden" id="AmountGuideBillDefault" name="AmountGuideBillDefault" value="${paymentWendyDetailList.amount}" readonly="">
                                            </div>
                                            <div class="col-xs-1 hidden" style="padding: 0px 0px 20px 30px">
                                                <input class="form-control" type="checkbox" id="ConfirmGuideBill" name="ConfirmGuideBill" value="1" onclick="confirmCheckboxGuideBill()">
                                            </div>
                                            <div class="col-xs-1 text-right hidden" style="width: 15px">
                                                <label class="control-label">Confirm</label>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${fn:containsIgnoreCase(mDepartmentName , 'checking wendy')}">
                                    <div class="col-xs-12 form-group" style="margin-top: -10px;"><hr/></div>
                                    <div class="col-sm-6" style="margin-top: -10px;">
                                        <h4>Booking Expense</h4>
                                    </div>
                                    <div class="col-sm-6 text-right" style="margin-top: -10px;">
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
                                                <th class="hidden">Cur</th>
                                                <th>Type</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:set var="guideBill" value="0"/>
                                            <c:set var="total" value="0"/>
                                            <c:forEach var="expen" items="${dayTourOperationExpense}" varStatus="i">
                                                <tr>
                                                    <td class="hidden">
                                                        <input id="expenId${i.count}" name="expenId${i.count}" class="form-control" value="${expen.id}">
                                                        <input id="countExpen${i.count}" name="countExpen" class="form-control" value="${i.count}">
                                                    </td>
                                                    <td><input id="expenDescription${i.count}" name="expenDescription${i.count}" class="form-control" value="${expen.description}"></td>
                                                    <td style="width: 80px">
                                                        <input id="expenQty${i.count}" name="expenQty${i.count}" 
                                                               class="form-control money" value="${expen.qty}" onfocusout="calculateGuideBill()">
                                                    </td>
                                                    <td style="width: 100px">
                                                        <input id="expenAmount${i.count}" name="expenAmount${i.count}" 
                                                               class="form-control money" value="${expen.amount}" onfocusout="calculateGuideBill()" onkeyup="insertCommas(this)">
                                                    </td>
        <!--                                            <td>                            
                                                        <select name="expenSelectCur${i.count}" id="expenSelectCur${i.count}" class="form-control">
                                                            <option value="${expen.currency}">${expen.currency}</option>
                                                            <c:forEach var="price" items="${mCurrency}" varStatus="status">
                                                                <c:if test="${expen.currency != price.code}">
                                                                    <option value="${price.code}">${price.code}</option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </td>-->
                                                    <td class="hidden">
                                                       <select id="expenSelectCur${i.count}" name="expenSelectCur${i.count}" class="">
                                                           <option></option>
                                                           <c:forEach var="price" items="${mCurrency}" >
                                                               <c:set var="select1" value="" />
                                                               <c:set var="selectedPlaceId1" value="${driver.staff.id}" />
                                                               <c:if test="${expen.currency == price.code}">
                                                                   <c:set var="select1" value="selected" />
                                                               </c:if>
                                                               <option value="<c:out value="${price.code}" />" ${select1}><c:out value="${price.code}" /></option>   
                                                           </c:forEach>
                                                       </select>
                                                       <script>
                                                           $("#expenSelectCur${i.count}").selectize({
                                                               sortField: 'text'

                                                           });
                                                       </script>
                                                   </td>
                                                    <td class="text-center">
                                                        <input type="hidden" value="${expen.priceType}" id="expenPriceTypeHiden${i.count}">
                                                        <input  type="radio" value="S" id="expenTypeS${i.count}" 
                                                               name="expenPriceType${i.count}" ${expen.priceType.equals("S") ? 'checked' : ''} onclick="calculateGuideBill()">&nbsp;&nbsp;S&nbsp;&nbsp;&nbsp;
                                                        <input  type="radio" value="G" id="expenTypeG${i.count}"
                                                                name="expenPriceType${i.count}" ${expen.priceType.equals("G") ? 'checked' : ''} onclick="calculateGuideBill()" >&nbsp;&nbsp;G
                                                        <script>
                                                            $(document).ready(function () {  
                                                                var status = $("#expenPriceTypeHiden${i.count}").val();
                                                                $("input[name=expenPriceType${i.count}][value=" + status + "]").attr('checked', 'checked');
                                                            });
                                                        </script>
                                                    </td>

                                                    <td class="text-center">
                                                        <a id="expenButtonRemove${i.count}" name="expenButtonRemove${i.count}" onclick="setExpenId(${expen.id})"  data-toggle="modal" data-target="#DeleteExpenModal">
                                                            <span id="expenSpanRemove${i.count}" name="expenSpanRemove${i.count}"  class="glyphicon glyphicon-remove deleteicon"></span>
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
                                    <div class="col-xs-12 form-group" style="margin-top: -10px;">
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
                                    <div class="col-xs-12 form-group" style="margin-top: -10px;"><hr/></div>
                                    <div class="col-xs-12 form-group" style="margin-top: -10px;">                               
                                        <div id="textAlertDivGuideName" style="display:none; color: red">

                                        </div>
                                    </div>                               
                                    <div class="col-xs-12 form-group" style="margin-top: -10px;">
                                        <div class="col-sm-6" style="margin-top: -30px;">
                                            <h4>Guide Bill</h4>
                                        </div>
                                    </div>
                                    <div id="GuideBillForm">
                                        <div class="col-xs-12 form-group" style="margin-top: -10px;">
                                            <div class="col-xs-1">
                                                <label class="control-label">Payno</label>
                                            </div>                                   
                                            <div class="col-xs-1" style="width: 250px">                                       
                                                <input class="form-control" type="text" id="PayNoGuideBill" name="PayNoGuideBill" value="${paymentWendyList.payNo}" readonly="">
                                            </div>
                                            <div class="col-xs-1 text-right" style="width: 120px">
                                                <label class="control-label">Invoice&nbsp;Sup</label>
                                            </div>
                                            <div class="col-xs-1" style="width: 250px">
                                                <input class="form-control" type="hidden" id="guideName" name="guideName" value="${requestScope['guideName']}" readonly="">
                                                <select id="InvoiceSupGuideBill" name="InvoiceSupGuideBill" class="form-control" onchange="setGuideName('','check')" disabled="">
                                                    <option id="" value="">---------</option>
                                                </select>
                                            </div>
                                            <div class="col-xs-1 text-right" style="width: 120px">
                                                <label class="control-label">Currency</label>
                                            </div>
                                            <div class="col-xs-1" style="width: 150px">
                                                <select id="SelectCur" name="SelectCur" class="form-control">
                                                    <option id="" value="">---------</option>
                                                    <c:forEach var="price" items="${mCurrency}" >
                                                        <c:set var="select1" value="" />
                                                            <c:if test="${paymentWendyList.currency == price.code}">
                                                                <c:set var="select1" value="selected" />
                                                            </c:if>
                                                        <option value="<c:out value="${price.code}" />" ${select1}><c:out value="${price.code}" /></option>   
                                                    </c:forEach>
                                                </select>
                                                <script>
                                                    $("#expenSelectCur${i.count}").selectize({
                                                        sortField: 'text'

                                                    });
                                                </script>
                                            </div>
                                        </div>
                                        <div class="col-xs-12 form-group" style="margin-top: -10px;">
                                            <div class="col-xs-1">
                                                <label class="control-label">Status</label>
                                            </div>
                                            <div class="col-xs-1" style="width: 250px">
                                                <select class="form-control" id="StatusGuideBill" name="StatusGuideBill">
                                                    <option id="" value="">---------</option>  
                                                    <c:forEach var="status" items="${statusList}">
                                                        <c:set var="select2" value="" />
                                                        <c:if test="${status.id == paymentWendyList.MItemstatus.id}">
                                                            <c:set var="select2" value="selected" />
                                                        </c:if>
                                                        <option value="<c:out value="${status.id}" />" ${select2}><c:out value="${status.name}" /></option>                                         
                                                    </c:forEach>
                                                </select>    
                                            </div>
                                            <div class="col-xs-1 text-right" style="width: 120px">
                                                <label class="control-label">Amount</label>
                                            </div>
                                            <div class="col-xs-1" style="width: 250px">
                                                <input class="form-control money" type="text" id="AmountGuideBill" name="AmountGuideBill" value="${paymentWendyDetailList.amount}" readonly="">
                                                <input class="form-control numerical" type="hidden" id="AmountGuideBillDefault" name="AmountGuideBillDefault" value="${paymentWendyDetailList.amount}" readonly="">
                                            </div>
                                            <div class="col-xs-1 hidden" style="padding: 0px 0px 20px 30px">
                                                <input class="form-control" type="checkbox" id="ConfirmGuideBill" name="ConfirmGuideBill" value="1" onclick="confirmCheckboxGuideBill()">
                                            </div>
                                            <div class="col-xs-1 text-right hidden" style="width: 15px">
                                                <label class="control-label">Confirm</label>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                            <input class="form-control" type="hidden" id="PayNoGuideBillId" name="PayNoGuideBillId" value="${paymentWendyList.id}" readonly="">
                            <input class="form-control" type="hidden" id="PaymentWendyDetailId" name="PaymentWendyDetailId" value="${paymentWendyDetailList.id}" readonly="">
                            <input class="form-control" type="hidden" id="createBy" name="createBy" value="${paymentWendyList.createBy}" readonly="">
                            <input class="form-control" type="hidden" id="createDate" name="createDate" value="${paymentWendyList.createDate}" readonly="">
                            <input class="form-control" type="hidden" id="invNo" name="invNo" value="${paymentWendyDetailList.invoiceCreditor}" readonly="">
                            
                            <div class="col-xs-12 form-group"><hr/></div>  
                            <div class="text-center" style="padding-top: 10px">
                                <a id="ButtonPrint" name="ButtonPrint" onclick="printGuideJob();" class="btn btn-primary"><i class="fa fa-print"></i> Print</a>
                                <input type="hidden" name="action" value="update" />
                                <input type="hidden" name="tourID" value="${param.tourID}" />
                                <input type="hidden" name="tourDate" value="${param.tourDate}" />
                                <input type="hidden" name="tourCode" value="${daytour.code}" />
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

<!--Tour Modal-->
<div class="modal fade" id="TourModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Tour</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="tourTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th style="width:20%">Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <script>
                        tourCode = [];
                        
                    </script>
                    <tbody>
                        <c:forEach var="tour" items="${tourList}">
                            <tr>
                                <td class="tour-id hidden">${tour.id}</td>
                                <td class="tour-code">${tour.code}</td>
                                <td class="tour-name">${tour.name}</td>
                            </tr>
                        <script>
                            tourCode.push({id: "${tour.id}", code: "${tour.code}", name: "${tour.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Script Daytour List table-->
            <script>
                $(document).ready(function () {
                    $("#ButtonSearch").attr("disabled", "disabled");

                    if (($("#InputDetailTourCode,#InputTourDetailTourDate").val().trim().length !== 0)) {
                        $("#ButtonSearch").removeAttr("disabled");
                    }else{
                        $("#ButtonSearch").attr("disabled", "disabled");
                    }

                    $('.date').datetimepicker();
                    $('.datemask').mask('0000-00-00');
                    $("#tourTable tr").on('click', function () {//winit
                        $("#TourModal").modal('hide');
                        var tour_id = $(this).find(".tour-id").html();
                        var tour_code = $(this).find(".tour-code").html();
                        var tour_name = $(this).find(".tour-name").html();
                        $("#InputDetailTourId").val(tour_id);
                        $("#InputDetailTourCode").val(tour_code);
                        $("#InputDetailTourName").val(tour_name);
                        $("#InputDetailTourCode").focus();
                    });

                    // tourTable
                    var tourTable = $('#tourTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": true,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });

                    $('#tourTable tbody').on('click', 'tr', function () {
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                        }
                        else {
                            tourTable.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                        }

                    });
                    // ON KEY INPUT AUTO SELECT TOURCODE-TOURNAME
                    $(function () {
                        var availableTags = [];

                        $.each(tourCode, function (key, value) {
                            availableTags.push(value.code);
                            if (!(value.name in availableTags)) {
                                availableTags.push(value.name);
                            }
                        });

                        $("#InputDetailTourCode").autocomplete({
                            source: availableTags,
                            close: function (event, ui) {
                                $("#InputDetailTourCode").trigger('keyup');
                            }
                        });


                        $("#InputDetailTourCode").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var name = this.value;
                            var code = this.value.toUpperCase();
                            $("#InputDetailTourName").val(null);
                            $.each(tourCode, function (key, value) {
                                if (name === value.name) {
                                    $("#InputDetailTourCode").val(value.code);
                                    code = $("#InputDetailTourCode").val().toUpperCase();
                                }
                                if (value.code.toUpperCase() === code) {
                                    $("#InputDetailTourId").val(value.id);
                                    $("#InputDetailTourName").val(value.name);
                                }
                            }); //end each tourCode
                        }); // end InputTourCode keyup
                    }); // end AutoComplete TourCode TourName

                });
                function checkUpdTour() {
                    if (($("#InputDetailTourCode").val().trim().length !== 0) && ($("#InputTourDetailTourDate").val().trim().length !== 0)) {
                        searchAction();
                    }
                }
                
                function searchAction() {
                    var action = document.getElementById('action');
                    action.value = 'edit';
                    var InputTourId = document.getElementById('InputTourId');
                    InputTourId.value = $("#InputDetailTourId").val();
                    var tourDate = document.getElementById('tourDate');
                    tourDate.value = $("#InputTourDetailTourDate").val();

                    document.getElementById('DaytourOperationForm').submit();
                }
                              
            </script>
            <div class="modal-footer">
                <button id="" type="button" onclick="" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<script> driver = []</script>
<c:forEach var="dr" items="${driverList}">
    <script>
        driver.push({
            id: "${dr.id}",
            name: "${dr.name}",
            username: "${dr.username}",
            car: "${dr.car}"
        });
    </script>
</c:forEach>
    
<script> 
    currency = [];
</script>
<c:forEach var="cur" items="${mCurrency}">
    <script>
        currency.push({
            id: "${cur.id}",
            code: "${cur.code}"
        });
    </script>
</c:forEach>    
    
<c:if test="${! empty param.result}">
    <c:if test="${param.result =='success'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${param.result =='fail'}">        
        <script language="javascript">
            $('#textAlertDivNotSave').show();
        </script>
    </c:if>
</c:if>

