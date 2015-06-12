<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="js/DaytourDetail.js"></script> 
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
<link href="css/jquery-ui.min.css" rel="stylesheet">

<c:set var="booktype" value="${requestScope['BOOKING_TYPE']}" />
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="tourList" value="${requestScope['TourList']}" />
<c:set var="places" value="${requestScope['Places']}" />
<c:set var="daytourBooking" value="${requestScope['DaytourBooking']}" />
<c:set var="result" value="${requestScope['TransactionResult']}" />
<c:set var="coupons" value="${requestScope['Coupons']}" />
<c:set var="daytourbookPrices" value="${requestScope['DAYTOURBOOKPRICES']}" />
<c:set var="guideList" value="${requestScope['GuideList']}" />

<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<!--Alert Save -->
<div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Save Success!</strong> 
</div>
<div id="textAlertDivNotSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Save Success!</strong> 
</div>
<section class="content-header" >
    <h1>
        Booking - Day Tours Detail
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">Day Tours Detail</a></li>
    </ol>
</section>

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
            <input type="hidden" value="${master.customer.MInitialname.name}" id="initialname_tmp">
            <input type="hidden" value="${master.customer.firstName}" id="firstname_tmp">
            <input type="hidden" value="${master.customer.lastName}" id="lastname_tmp"> 
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
            <div class="row" style="padding-left: 15px">  
                <div class="col-md-6 " style="padding-right: 15px">
                    <h4><b>Day Tours Detail</b></h4>
                </div>
                <div class="col-md-6 text-right" >
                    <a id="ButtonBack" name="ButtonBack" href="Daytour.smi?referenceNo=${param.referenceNo}" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Back</a>
                </div>
            </div>
            <hr/>
            <form action="DaytourDetail.smi" method="post" id="DaytourDetailForm" name="DaytourDetailForm" role="form">
                <input type="hidden" class="form-control" id="referenceNo"   name="referenceNo"  value="${param.referenceNo}" > 
                <input name="action" value="save"type="hidden">
                <input type="hidden" id="daytourBooking" name="daytourBooking" value="${daytourBooking.id}" />
                <div class="row " >
                    <div class="col-md-12">
                        <div class="col-md-2 text-right ">
                            <label class="control-label" for="InputTourCode">Tour&nbsp;Code<font style="color: red">*</font></label>
                        </div>
                        <div class="col-md-2 form-group">
                            <div class="col-sm-12">
                                <div class="input-group" id="CodeValidate">
                                    <input name="InputTourCode" id="InputTourCode" type="text" 
                                           class="form-control" value="${daytourBooking.daytour.code}" />
                                    <input name="InputTourId" id="InputTourId" type="hidden" 
                                           class="form-control" value="${daytourBooking.daytour.id}" />
                                    <span class="input-group-addon" data-toggle="modal" data-target="#TourModal">
                                        <span class="glyphicon-search glyphicon"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="col-sm-12">
                                <input name="InputTourName" id="InputTourName"  type="text" style="width: 200px" class="form-control" readonly="" value="${daytourBooking.daytour.name}">
                            </div>
                        </div>
                        <div class="col-md-2 text-right">
                            <label class="control-label text-right" for="InputTourDate">Tour Date<font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-2 form-group"> 
                            <div class='input-group date' id='InputDatePicker'>
                                <input id="InputTourDate" name="InputTourDate" type="text" data-date-format="YYYY-MM-DD" 
                                       class="form-control datemask" placeholder="YYYY-MM-DD" value="${daytourBooking.tourDate}">
                                <span class="input-group-addon">
                                    <span class="glyphicon-calendar glyphicon"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-2 text-right ">
                            <a data-toggle="modal" data-target="#ReferenceModal"  id="BtnTourRef" name="BtnTourRef" class="btn btn-primary btn-sm" >Tour Reference&nbsp;&nbsp;<light id="badgeRef" name="badgeRef"></light> </a>
                        </div>
                    </div>
                </div>
                <div class="row form-group" >
                    <div class="col-md-12">
                        <div class="col-md-2 text-right">
                            <label class="control-label">Passenger&nbsp;Tour</label>
                        </div>
                        <c:choose>
                            <c:when test="${param.action == 'new'}">
                                <div class="col-md-5">
                                    <div class="col-sm-2">
                                        <label class="control-label" for="InputAdult">Adult</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input name="InputAdult" id="InputAdult" type="number" min="0" class="form-control" onkeyup="manualNumberOnly(this);" maxlength="3" value="${master.adult}">
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label" for="InputChild">Child</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input name="InputChild" id="InputChild" type="number" min="0" class="form-control" onkeyup="manualNumberOnly(this);" maxlength="3" value="${master.child}">
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label" for="InputInfant">Infant</label>
                                    </div>
                                    <div class="col-sm-2 text-right">
                                        <input name="InputInfant" id="InputInfant" type="number" min="0" class="form-control" onkeyup="manualNumberOnly(this);" maxlength="3" value="${master.infant}">
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-md-5">
                                    <div class="col-sm-2">
                                        <label class="control-label" for="InputAdult">Adult</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input name="InputAdult" id="InputAdult" type="number" class="form-control" onkeyup="manualNumberOnly(this);" min="0" maxlength="3" value="${daytourBooking.adult}">
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label" for="InputChild">Child</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input name="InputChild" id="InputChild" type="number" class="form-control" onkeyup="manualNumberOnly(this);" min="0" maxlength="3" value="${daytourBooking.child}">
                                    </div>
                                    <div class="col-sm-2">
                                        <label class="control-label" for="InputInfant">Infant</label>
                                    </div>
                                    <div class="col-sm-2 text-right">
                                        <input name="InputInfant" id="InputInfant" type="number" class="form-control" onkeyup="manualNumberOnly(this);" min="0" maxlength="3" value="${daytourBooking.infant}">
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <div class="col-md-1 text-right">
                            <c:set var="checkedPay" value="" />
                            <c:if test="${daytourBooking.isPay  == '1'}">
                                <c:set var="checkedPay" value="checked" />
                            </c:if>                            
                            <label class="control-label" for="CheckPay">Pay</label>
                        </div>
                        <div class="col-md-1 " >
                            <input type="checkbox" id="CheckPay" name="CheckPay"  ${checkedPay}>
                        </div>
                    </div>
                </div>
                <div class="row " >
                    <div class="col-md-12 ">
                        <div class="col-md-2 text-right">
                            <label class="control-label text-right" for="SelectPickUp">&nbsp;&nbsp;&nbsp;&nbsp;Pick&nbsp;Up<font style="color: red">*</font></label>
                        </div>
                        <div class="col-md-5 form-group">
                            <div class="col-sm-12  " id="selectValid">
                                <select name="SelectPickUp" id="SelectPickUp"  class="form-control" >
                                    <option value="" >--------------------Please select--------------------</option>
                                    <c:forEach var="item" items="${places}" >
                                        <option value="<c:out value="${item.id}" />" ><c:out value="${item.place}" /></option>   
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-1 text-right">
                            <label class="control-label" for="InputPickUp">Other</label>
                        </div>
                        <div class="col-md-4">
                            <input name="InputPickUp" id="InputPickUp" class="form-control" readonly="" maxlength="255" value="${daytourBooking.pickupDetail}">
                        </div>

                    </div>
                </div>
                <div class="row form-group "  >
                    <div class="col-md-12">
                        <div class="col-md-2 text-right">
                            <label class="control-label">Room</lable>
                        </div>
                        <div class="col-md-2">
                            <div class="col-sm-12">
                                <input name="InputRoom" id="InputRoom" name="InputRoom" class="form-control" maxlength="100" value="${daytourBooking.pickupRoom}">
                            </div>
                        </div>
                        <div class="col-md-1 text-right">
                            <label class="control-label">Time</lable>
                        </div>
                        <div class="col-md-1 text-left">
                            <!--<div class="col-sm-12">-->
                            <input name="InputTime" id="InputTime" name="InputTime" class="form-control time" maxlength="255" value="${daytourBooking.pickupTime}" placeholder="HH:MM">
                            <!--</div>-->
                        </div>
                        <div class="col-md-1 text-right"></div>
                        
                        <div class="col-md-1 text-right">
                            <label class="control-label" for="TestareaMemo">Memo</label>
                        </div>
                        <div class="col-md-4">
                            <textarea id="TextareaMemo" name="TextareaMemo" style="resize: none" class="form-control" maxlength="255">${daytourBooking.memo}</textarea>
                        </div>
                    </div>
                </div>            
                <div class="row form-group"  >            
                    <div class="col-md-12">
                        <div class="col-md-2 text-right">
                            <label class="control-label" for="TestareaRequirement">Requirement</label>
                        </div>
                        <div class="col-md-5 text-right">
                            <div class="col-sm-12">
                                <textarea id="TextareaRequirement" name="TextareaRequirement" style="resize: none" class="form-control" maxlength="255" >${daytourBooking.requirement}</textarea>
                            </div>
                        </div>
                       <div class="col-md-1 text-right">
                            <label class="control-label">Remark</label>
                        </div>
                        <div class="col-xs-4">                     
                                <textarea id="TextareaRemark" name="TextareaRemark" style="resize: none" class="form-control">${daytourBooking.remark}</textarea>
                        </div>

                    </div>
                </div>

                <div class="row  form-group" style="margin-top: 20px;">
                    <!--<div class="col-md-12 form-group">-->
                    <div class="col-md-2 text-left">
                        <label class="control-label text-right">Value&nbsp;List</lable>
                    </div>
                    <div class="col-md-10 text-right" >
                        <a name="ButtonPriceList" id="ButtonPriceList" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#PriceModal">Price List</a>
                    </div>
                    <!--</div>-->
                </div>

                <!-- PriceTable-->
                <div class="row form-group" style="margin-top: 20px; ">

                    <div class="col-md-12">
                        <input type="hidden"  id="counterPrice" name="counterPrice" class="" value="1" />
                        <table class="display" id="bookingTourPriceTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:10%">Type</th>
                                    <th style="width:42%">Detail</th>
                                    <th style="width:8%">Price</th>
                                    <th style="width:8%">Other Price</th>
                                    <th style="width:8%">Qty</th>
                                    <th style="width:8%">Total</th>
                                    <th style="width:8%">Cur</th>
                                    <th style="width:8%">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${daytourbookPrices}" varStatus="loop">
                                    <tr id="${loop.count}">  
                                        <td class="bookingPriceId hide"><input  type=hidden id="row-${loop.count}-bookingpriceid" name="row-${loop.count}-bookingpriceid" value="${item.id}"></td>
                                        <td class="priceCategoryId hide"><input  type=hidden id="row-${loop.count}-pricecategoryid" name="row-${loop.count}-pricecategoryid" value="${item.MPricecategory.id}" >${item.MPricecategory.id}</td>
                                        <td class="priceCategoryName"><input  type=hidden  id="row-${loop.count}-pricecategoryname" name="row-${loop.count}-pricecategoryname" value="${item.MPricecategory.name}" >${item.MPricecategory.name}</td>
                                        <td class="tourCode hide">${item.daytourBooking.daytour.code}</td>
                                        <td class="priceDetail"><input  type=hidden id="row-${loop.count}-pricedetail" name="row-${loop.count}-pricedetail" value="${item.detail}" >${item.detail}</td>
                                        <td class="hide"><input type=hidden id="row-${loop.count}-priceDefault" name="row-${loop.count}-priceDefault" value="${item.price}" ></td>
                                        <td class="priceAmountDefault money">${item.price}</td>
                                        <td class="priceAmount"><input type="text" class="form-control money otherprice" id="row-${loop.count}-priceamount" name="row-${loop.count}-priceamount" value="${item.price}" ></td>
                                        <td class="priceQty"><input type=text class="form-control text-right numbermask qty" id="row-${loop.count}-priceqty" name="row-${loop.count}-priceqty"  value="${item.qty}" maxlength="3"></td>
                                        <td class="priceTotal money"><input  type=hidden class="form-control money" id="row-${loop.count}-pricetotal" name="row-${loop.count}-pricetotal" value="${item.qty * item.price}" readonly="">${item.qty * item.price}</td>
                                        <td class="priceCurrency text-center"><input  type=hidden id="row-${loop.count}-pricecurrency" name="row-${loop.count}-pricecurrency" value="${item.currency}" >${item.currency}</td>
                                        <td class="text-center">
                                            <a id="RowPriceButtonRemove-${loop.count}"  name="RowPriceButtonRemove-${loop.count}"  ParentTrPriceId=""  class="RemovePriceRow">
                                                <span id="RowPriceSpanRemove-${loop.count}"  name="RowPriceSpanRemove-${loop.count}"  
                                                      class="glyphicon glyphicon-remove deleteicon" 
                                                      onclick="DeletePrice('${item.id}', '${item.detail}');"
                                                      data-toggle="modal" data-target="#DelPrice" ></span>
                                            </a></td>
                                    </tr>

                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <style>
                    .panel{
                        float: left;
                        overflow: hidden; 
                        margin: 0px; 
                        /*margin-top: 20px;*/
                        width: 1035px; 
                    }
                </style>

                <!--CouponTable-->
                <!--<div class="col-xs-12 form-group" style="margin-top: 20px; " >-->
                <div class="panel panel-default">
                    <div class="panel-heading">Coupon</div>
                    <div class="panel-body">
                        <!-- Coupons Table-->
                        <table class="display" id="CouponTable" name="CouponTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th class="hide">Other Id</th>
                                    <th class="hide">Coupon Id</th>
                                    <th>No</th>       
                                    <th>Date</th>
                                    <th>Time</th>
                                    <th>Qty</th>      
                                    <th>Description</th>
                                    
                                    <th>Use</th>
                                </tr>
                            </thead>

                            <tbody>
                                <c:forEach var="item" items="${Coupons}" varStatus="status">
                                    <c:set var="checkedCoupon" value="" />
                                    <c:set var="CouponId" value="" />
                                    <c:forEach var="coupon" items="${daytourBooking.coupons}">
                                        <c:if test="${coupon.otherBooking.id == item.id}">
                                            <c:set var="checkedCoupon" value="checked" />
                                            <c:set var="CouponId" value="${coupon.id}" />
                                        </c:if>
                                    </c:forEach>
                                    <!-- OtherBooking is CouponList , BookingId is DaytourBook and Coupons id is relationship between OtherBooking Id and DaytourBooking. -->
                                    <tr id="${status.count}" >
                                        <td class="hide"><input type="text" id="row-${status.count}-bookingotherid" name="row-${status.count}-bookingotherid" value="${item.id}">${item.id}</td>
                                        <td class="hide"><input type="text" id="row-${status.count}-couponid" name="row-${status.count}-couponid" value="${CouponId}"></td>
                                        <td>${status.count}</td>  
                                        <td class="text-center">${item.otherDate}</td>
                                        <td class="text-center">${item.otherTime}</td>
                                        <td class="text-center money">${item.adQty + item.chQty + item.inQty }</td>
                                        <td><input type="hidden" id="row-${status.count}-couponname" name="row-${status.count}-couponname" value="${item.product.name}">${item.product.name}</td>
                                        <td class="text-center"><input  type="checkbox"   id="row-${status.count}-couponcheck" name="row-${status.count}-couponcheck"  ${checkedCoupon}></td>
                                            <c:if test="${status.last}">
                                    <input type="hidden" id="counterCoupon" name="counterCoupon" />
                                </c:if>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--</div>-->
                <div class="col-xs-12 form-group"  ></div>
                <div class="text-center" style="margin-top: 20px">
                    <button id="ButtonSave" type="submit" onclick="submitAction();" class="btn btn-success"><span class="fa fa-save"></span> Save</button>
                    <input type="hidden" name="action" id="action" value="${param.action}">
                </div>
            </form>
        </div>
    </div>
</div>

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
                    $("#tourTable tr").on('click', function () {//winit
                        $("#TourModal").modal('hide');
                        var tour_id = $(this).find(".tour-id").html();
                        var tour_code = $(this).find(".tour-code").html();
                        var tour_name = $(this).find(".tour-name").html();
                        $("#InputTourId").val(tour_id);
                        $("#InputTourCode").val(tour_code);
                        $("#InputTourName").val(tour_name);
                        $("#InputTourCode").focus();
//                        $("#bookingTourPriceTable tbody").empty();
//                        getDayTourPrice(tour_id);
                        checkUpdTourRef();
                        if (isValidTourCode(tour_code)) {
                            var existingCode = "";
                            $("#bookingTourPriceTable tbody").find("tr").each(function () {
                                existingCode = $(this).find("td.tourCode").text();
                                console.log("tourTable click - existing code - " + existingCode);

                                if (existingCode !== tour_code) {
                                    UpdTourCodeShow(existingCode);
                                }

                            });
                        }
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
                            if ( !(value.name in availableTags) ){
                               availableTags.push(value.name);
                            }
                        });

                        $("#InputTourCode").autocomplete({
                            source: availableTags,
                            close:function( event, ui ) {    
                               $("#InputTourCode").trigger('keyup');
                            }                        
                        });
                        
                        
                        $("#InputTourCode").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var name = this.value;
                            var code = this.value.toUpperCase();
                            $("#InputTourName").val(null);
                            $.each(tourCode, function (key, value) {
                                if(name === value.name){
                                    $("#InputTourCode").val(value.code);
                                    code = $("#InputTourCode").val().toUpperCase();
                                }
                                if (value.code.toUpperCase() === code) {
                                    $("#InputTourId").val(value.id);
                                    $("#InputTourName").val(value.name);
                                }
                            }); //end each tourCode
                        }); // end InputTourCode keyup
                    }); // end AutoComplete TourCode TourName
                });
            </script>
            <div class="modal-footer">
                <button id="" type="button" onclick="" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--Agent Modal-->
<div class="modal fade" id="AgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Agent</h4>
            </div>
            <div class="modal-body">
                <table class="display">
                    <thead class="datatable-header">
                        <tr>
                            <th>ID</th>
                            <th>Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="AgentModalButtonOK" name="AgentModalButtonOK" type="button" onclick="" class="btn btn-success">OK</button>
                <button id="AgentModalButtonClose" name="AgentModalButtonClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--Reference Modal-->
<div class="modal fade" id="ReferenceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Reference</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="referenceTable" name="referenceTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Ref No</th>
                            <th>Leader</th>
                            <th>Ad</th>
                            <th>Ch</th>
                            <th>Inf</th>
                            <th>Course</th>
                            <th>Pick Up</th>
                            <th>Time</th>
                            <th>Status</th>
                            <th>Staff</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <!--<button id="ReferenceModalButtonOK"  name="ReferenceModalButtonOK" type="button" onclick="" class="btn btn-success">OK</button>-->
                <button id="ReferenceModalButtonClose" name="ReferenceModalButtonClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--Price Modal-->
<div class="modal fade" id="PriceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="PriceModalTitle">Price</h4>
            </div>
            <div class="modal-body" id="">
                <table class="display" id="dayTourPriceTable" name="dayTourPriceTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">Id</th>
                            <th>Category</th>
                            <th>Detail</th>
                            <th>Price</th>
                            <th>Cur</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="PriceModalButtonOK" name="PriceModalButtonOK" type="button" onclick="clickPriceModalOk();" class="btn btn-success">OK</button>
                <button id="PriceModalButtonClose" name="PriceModalButtonClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>



<!--Price Delete Modal-->
<div class="modal fade" id="DelPrice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="DaytourDetail.smi" method="post" id="DelPriceForm" name="DelPriceForm" class="form-horizontal" role="form">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"> Delete Price </h4>
                </div>
                <div class="modal-body" id="delTextPrice"></div>
                <input type="hidden" id="deleteRefNo" name="referenceNo" value="${param.referenceNo}"/>
                <input type="hidden" id="daytourBookingId" name="daytourBooking" value="${param.daytourBooking}"/>
                <input type="hidden" id="deleteId" name="deleteId" value="" />
                <input type="hidden" id="deletePriceAction" name="action" value="delete"/>
                <div class="modal-footer">
                    <button id="btnDelete" type="button" onclick="delPriceFormClick();" class="btn btn-danger">Delete</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->  


<!--Update TourCode Modal-->
<div class="modal fade" id="UpdTourCode" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="DaytourDetail.smi" method="post" id="UpdTourCodeForm" name="UpdTourCodeForm" class="form-horizontal" role="form">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"> Update Tour </h4>
                </div>
                <div class="modal-body" id="updTourCodeBody"></div>
                <input type="hidden" id="updRefNo" name="referenceNo" value="${param.referenceNo}"/>
                <input type="hidden" id="updDaytourBookingId" name="daytourBooking" value="${param.daytourBooking}"/>
                <input type="hidden" id="updateId" name="InputTourId" value=""/>
                <input type="hidden" id="deletePriceId" name="deletePriceId" value="" />
                <input type="hidden" id="deletePriceAction" name="action" value="deletePrice"/>
                <div class="modal-footer">
                    <button id="btnDelete" type="button" onclick="updTourCodeForm();" class="btn btn-danger">Delete</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<c:if test="${! empty param.result}">
    <c:if test="${param.result =='success'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
        <META HTTP-EQUIV="Refresh" CONTENT="0;URL=DaytourDetail.smi?referenceNo=${param.referenceNo}&daytourBooking=${param.daytourBooking}&action=edit">
    </c:if>
    <c:if test="${param.result =='fail'}">        
        <script language="javascript">
            $('#textAlertDivNotSave').show();
        </script>
        <META HTTP-EQUIV="Refresh" CONTENT="0;URL=DaytourDetail.smi?referenceNo=${param.referenceNo}&daytourBooking=${param.daytourBooking}&action=edit">
    </c:if>
</c:if> 

<script>

    $(document).ready(function () {
        $('.time').mask('00:00');
        $('.datemask').mask('0000-00-00');
        $('.numbermask').mask('0000');
        //Number
        var maskMoney = "000,000,000";
        $(".money").mask('000,000,000', {reverse: true});
        $('.date').datetimepicker();
        $('span').click(function () {
            var position = $(this).offset();
            console.log("span calendar positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });

//        var $selectGuide = $('#InputGuide').selectize({
//            create: false,
//            sortField: 'text'
//        });
//        var controlGuide = $selectGuide[0].selectize;
        //Disable TourRef Button and Enable if there is tourName and tourDate
       
        
        $("#BtnTourRef").attr("disabled", "disabled");
        if (($("#InputTourName,#InputTourDate").val().trim().length !== 0)) {
            $("#BtnTourRef").removeAttr("disabled");
        }
        //Handle Enable,Disable BtnTourRef.
        $("#InputTourDate, #InputTourCode, #InputDatePicker").on('change blur', function (event) {
            var eventName = event.type; // return click
            var tourId = $("#InputTourId").val();
            var tourName = $("#InputTourName").val();
            var tour_code = $("#InputTourCode").val();
            var tourDate = $("#InputTourDate").val();

            if (isValidTourCode(tour_code)) {
                var existingCode = "";
                $("#bookingTourPriceTable tbody").find("tr").each(function () {
                    existingCode = $(this).find("td.tourCode").text();
                    console.log("existing code - " + existingCode);

                    if (existingCode !== tour_code) {
                        UpdTourCodeShow(existingCode);
                    }

                });
                getDayTourPrice(tourId);
            }


//            console.log("eventName= " + eventName + "tourName = " + tourName + ", tourDate = " + tourDate + ", tourId = " + tourId);
            checkUpdTourRef();

        });

        var tourDate = $("#InputTourDate").val();
        var tourId = $("#InputTourId").val();
        if (tourDate.length > 0 && tourId.length > 0) {
            getTourRef(tourId, tourDate);
            getDayTourPrice(tourId);
        }

        var $selectPickup = $('#SelectPickUp').selectize({
            create: false,
            sortField: 'text'
        });

        var controlPickup = $selectPickup[0].selectize;
        controlPickup.on('change', function () {
            var pickupValue = controlPickup.getItem(controlPickup.getValue()).text();
//            alert(pickupValue);
            if (pickupValue !== 'OTHERS') {
                $('#InputPickUp').prop('readonly', true);
            }else{
                $('#InputPickUp').prop('readonly', false);
            }
        });
        if ($("#daytourBooking").val().length > 0) {
            controlPickup.setValue([${daytourBooking.place.id}]);
//            controlGuide.setValue([${daytourBooking.guide.id}]);
        }

        $("#bookingTourPriceTable").on('change', '.qty , .otherprice', function (e) {
//            console.log("bookingTourPriceTable qty change!!(");
            var tdAmount = $(this).closest('tr').find('td.priceAmount');
            var tdQty = $(this).closest('tr').find('td.priceQty');
            var tdTotal = $(this).closest('tr').find('td.priceTotal');

//            console.log("tdAmount " + tdAmount.text());
//            console.log("tdQty " + tdQty.html());
//            console.log("tdTotal " + tdTotal.html());
            var amount = tdAmount.find(":input");//text().replace(",", "");
            var qtyInput = tdQty.find(":input");
            var qty = qtyInput.val();
            var total = qty * amount.val().replace(",", "");
            tdTotal.text(addCommas(total));
//            console.log("qty " + qtyInput.val());

        });

    }); // end ready.

    function UpdTourCodeShow(code) {
        $('#updTourCodeBody').html("If change tour code. System will delete price is not the same {" + code + "} Are you confirm?");
        $("#UpdTourCode").modal("show");
    }

    function isValidTourCode(code) {
        var matchTourCode = $.grep(tourCode, function (e) {
            return e.code === code;
        });

        return Boolean(matchTourCode.length > 0);
    }

    function addCommas(nStr)
    {
        nStr += '';
        x = nStr.split('.');
        x1 = x[0];
        x2 = x.length > 1 ? '.' + x[1] : '';
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        var result = x1 + x2;
        return result;
    }


    function checkUpdTourRef() {
        if (($("#InputTourName").val().trim().length !== 0) && ($("#InputTourDate").val().trim().length !== 0)) {
            var tourId = $("#InputTourId").val();
            var tourDate = $("#InputTourDate").val();
            getTourRef(tourId, tourDate);
            getDayTourPrice(tourId);
        } else if (($("#InputTourName").val().trim().length !== 0)) {
            var tourId = $("#InputTourId").val();
            getDayTourPrice(tourId);
        }


    }

    function getTourRef(tourid, date) {
        var servletName = 'BookDaytourServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&date=' + date +
                '&tourid=' + tourid +
                '&type=' + 'TourReference';
        console.log("Ajax param [" + param + "]");
        CallAjax(param);
    }



    function CallAjax(param) {
        var url = 'AJAXServlet';
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function (msg) {
//                    console.log("Msg [" + msg + "]")
                    if (msg) {
                        var html = $.parseHTML(msg);
//                        console.log("length " + html.length);
                        $("#badgeRef").html('<span  class="badge" style="background-color:#0ACDCD; float:right">' + html.length + '</span>');
                        if (html.length > 0) {
                            $("#BtnTourRef").removeAttr("disabled");
                        } else {
                            $("#BtnTourRef").attr("disabled", "disabled");
                        }
                        $("#referenceTable tbody").empty().append(msg);
                    } else {
                        $("#badgeRef").html('<span  class="badge" style="background-color:#0ACDCD; float:right">0</span>');
                        $("#BtnTourRef").attr("disabled", "disabled");
                    }
                }, error: function (msg) {
                    //alert('error ' + msg);
                    $("#BtnTourRef").attr("disabled", "disabled");
                }
            });
        } catch (e) {
            //alert(e);
        }
    }

    function submitAction() {
    
         var priceLength = $('#bookingTourPriceTable tbody tr').length;
        $("#counterPrice").val(priceLength);
        var couponLength = $('#CouponTable tbody tr').length;
        $("#counterCoupon").val(couponLength);
//        console.log("submit daytourdetail with pricerows(" + priceLength + "), couponrows(" + couponLength + ") rows.");
        $("#DaytourDetailForm").submit();

    }

    function getDayTourPrice(tourid) {
        var servletName = 'BookDaytourServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&tourid=' + tourid +
                '&type=' + 'DaytourPrice';
        console.log("getDayTourPrice param [" + param + "]");
        CallAjaxDayTourPrice(param);
    }

    function CallAjaxDayTourPrice(param) {
        var url = 'AJAXServlet';
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function (msg) {
                    console.log("AJAX-dayTourPrice Msg [" + msg + "]")
                    if (msg) {
                        var html = $.parseHTML(msg);
                        console.log("AJAX - daytourprice length " + html.length);
                        if (html.length > 0) {
                            $("#ButtonPriceList").removeAttr("disabled");
                        } else {
                            $("#ButtonPriceList").attr("disabled", "disabled");
                        }
                    }
                    $("#dayTourPriceTable tbody").empty().append(msg);
                    hideChosenDayTourPriceRow();
                }, error: function (msg) {
//                    console.log('dayTourPrice error ' + msg);
                    console.log('empty dayTourPriceTable');
                    $("#dayTourPriceTable tbody").empty();
                    $("#ButtonPriceList").attr("disabled", "disabled");
                }
            });
        } catch (e) {
        }
    }

    function hideChosenDayTourPriceRow() {
        $('#bookingTourPriceTable tbody').find('tr').each(function () {
            var priceCategoryId;
            var priceDetail;
            var priceAmountDefault;
            $(this).find('td').each(function () {  //loop td to get value
                if ($(this).hasClass('priceCategoryId')) {
                    priceCategoryId = $(this).text();
                }
                if ($(this).hasClass('priceDetail')) {
                    priceDetail = $(this).text();
                }
                if($(this).hasClass( "priceAmountDefault" )){
                    priceAmountDefault = $(this);
                }
            });
//            console.log("Cat Id " + priceCategoryId + ", priceDetail " + priceDetail);

            var foundMatchRow;


            $("#dayTourPriceTable tbody").find('tr').each(function () {
                var categoryId;
                var detail;
                var priceAmount;
                $(this).find('td').each(function () {  //loop td to get value
                    if ($(this).hasClass('priceCategoryId')) {
                        categoryId = $(this).text();
                    }
                    if ($(this).hasClass('priceDetail')) {
                        detail = $(this).text();
                    }
                    if ($(this).hasClass('priceAmount')){
                        priceAmount = $(this).text();
                        priceAmount = priceAmount.replace("," , "");
                        priceAmount = priceAmount.replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
                    }
                });

//                console.log("dayTourPrice Cat Id " + categoryId + ", priceDetail " + detail);


                if ((categoryId === priceCategoryId) && (detail == priceDetail)) {
//                    console.log("match both");
                    $(this).css("display", "none");
                    priceAmountDefault.text(priceAmount);
                    //priceAmountDefault.addClass('money')
                    
                }
            }); // end loop tr daytourPriceTable

        }); // end loop tr - bookingTourPriceTable

    }

    function DeletePrice(id, detail, priceId) {
        console.log("id=" + id + ", detail=" + detail);
        $('#deletePriceAction').val('delete');
        var act = $("#deletePriceAction").val();
        $('#deleteId').val(id);
        $('#deletePriceId').val(priceId);
        $('#delTextPrice').html(" Are you sure to delete price : " + detail + " ? ");
        console.log("action = " + act);
    }

    function clickPriceModalOk() {
        var row = $('#bookingTourPriceTable tbody tr').length + 1;
        var daytourBookingId;
        var priceId, priceCategoryId, priceCategoryName, priceDetail, priceAmount,
                priceQtyAdult, priceQtyChild, priceQtyInfant, priceTotal, priceCurrency, priceQty, tourCode;
        var ParentTrPriceId;  //id for check box
        $('#dayTourPriceTable').find('tr').each(function () { //loop this table
            ParentTrPriceId = $(this).attr('id');// get tr id of table
            priceQtyAdult = parseInt($('#InputAdult').val());
            priceQtyChild = parseInt($('#InputChild').val());
            priceQtyInfant = parseInt($('#InputInfant').val());

            $(this).find('td').each(function () {  //loop td to get value
                if ($(this).hasClass('bookingPriceId')) {
                    daytourBookingId = $(this).html();
                }
                if ($(this).hasClass('priceId')) {
                    priceId = $(this).html();
                }
                if ($(this).hasClass('priceCategoryId')) {
                    priceCategoryId = $(this).html();
                }
                if ($(this).hasClass('priceCategoryName')) {
                    priceCategoryName = $(this).html();
                }
                if ($(this).hasClass('tourCode')) {
                    tourCode = $(this).html();
                }
                if ($(this).hasClass('priceDetail')) {
                    priceDetail = $(this).html();
                }
                if ($(this).hasClass('priceAmount')) {
                    priceAmount = $(this).html();
                }
                if ($(this).hasClass('priceCurrency')) {
                    priceCurrency = $(this).html();
                }
                var eachCheckbox = $(this).children();  //goto  td child that is checkbox               
                if ($(eachCheckbox).is(':checked')) {
                    priceAmount = priceAmount.replace(",", "");
                    if (priceCategoryId == 1) {
                        priceTotal = priceAmount * priceQtyAdult;
                        priceQty = priceQtyAdult;
//                        console.log("priceTotal:" + priceTotal);
                    } else if (priceCategoryId == 2) {
                        priceTotal = priceAmount * priceQtyChild;
                        priceQty = priceQtyChild;
                    } else if (priceCategoryId == 3) {
                        priceTotal = priceAmount * priceQtyInfant;
                        priceQty = priceQtyInfant;
                    }

                    $("#bookingTourPriceTable tbody").append(
                            '<tr id="bTourPriceId' + priceId + '" >' +
                            '<td class="bookingPriceId hide"><input  type=hidden id="row-' + row + '-bookingpriceid" name="row-' + row + '-bookingpriceid" value=""></td>' +
//                            '<td class="priceId hide"><input  type=hidden id="row-' + row + '-priceid" name="row-' + row + '-priceid" value="' + priceId + '">' + priceId + '</td>' +
                            '<td class="priceId hide">' + priceId + '</td>' +
                            '<td class="priceCategoryId hide"><input  type=hidden id="row-' + row + '-pricecategoryid" name="row-' + row + '-pricecategoryid" value="' + priceCategoryId + '" >' + priceCategoryId + '</td>' +
                            '<td class="priceCategoryName "><input  type=hidden id="row-' + row + '-pricecategoryname" name="row-' + row + '-pricecategoryname" value="' + priceCategoryName + '" >' + priceCategoryName + '</td>' +
                            '<td class="tourCode hide">' + tourCode + '</td>' +
                            '<td class="priceDetail"><input  type=hidden id="row-' + row + '-pricedetail" name="row-' + row + '-pricedetail" value="' + priceDetail + '" >' + priceDetail + '</td>' +
                            '<td class="hide"><input type=hidden id="row-' + row + '-priceDefault" name="row-' + row + '-priceDefault" value="' + priceAmount + '" ></td>'+
                            '<td class="priceAmountDefault money">'+ priceAmount + '</td>' + 
                            '<td class="priceAmount"><input class="form-control money otherprice text-right" type="text" id="row-' + row + '-priceamount" name="row-' + row + '-priceamount" value="' + priceAmount + '" ></td>' +
                            '<td class="priceQty"><input type=text class="form-control text-right numbermask qty" id="row-' + row + '-priceqty" name="row-' + row + '-priceqty"  value="' + priceQty + '" maxlength="3"></td>' +
                            '<td class="priceTotal money"><input type=hidden class="form-control" id="row-' + row + '-pricetotal" name="row-' + row + '-pricetotal" value="' + priceTotal + '" >' + priceTotal + '</td>' +
                            '<td class="priceCurrency text-center"><input  type=hidden id="row-' + row + '-pricecurrency" name="row-' + row + '-pricecurrency" value="' + priceCurrency + '" >' + priceCurrency + '</td>' +
                            '<td class="text-center">' +
                            '<a id="RowPriceButtonRemove-' + row + '"  name="RowPriceButtonRemove-' + row + '"  ParentTrPriceId="' + ParentTrPriceId + '"  class="RemovePriceRow">' +
                            '<span id="RowPriceSpanRemove-' + row + '"  name="RowPriceSpanRemove-' + row + '"  class="glyphicon glyphicon-remove deleteicon"' +
                            'onclick="DeletePrice(\'x\', \'' + priceDetail + '\', \'' + priceId + '\');"' +
                            'data-toggle="modal" data-target="#DelPrice"></span>' +
                            '</a></td>' +
                            '</tr>'
                            );
                    row++; //loop id 1,2,3....
                    $("#counterPrice").val(row);  //set value id to counter
                    $(eachCheckbox).removeAttr('checked');
                    $('#' + ParentTrPriceId).hide();    //hide this checked       
                } else {
                }
            });

        });
        $("#PriceModal").modal('hide');
    }
    function manualNumberOnly(elements){
        //$('.test').keyup(function () { 
        elements.value = elements.value.replace(/[^0-9\.]/g,'');
        //});
    }
    function delPriceFormClick() {
        var id = $('#deleteId').val();
//        console.log("delPriceForm click " + id);
        if (id === 'x') {
            var priceId = $("#deletePriceId").val();
//            console.log("goto enable in Value List table priceId ="  + priceId);
            $("#trpriceid" + priceId).css("display", "");

            $("#bTourPriceId" + priceId).remove();
            $("#DelPrice").modal("hide");
        } else {
            $("#DelPriceForm").submit();
        }
    }

    function updTourCodeForm() {
        var tourCode = $("#InputTourCode").val();
        var daytourBooking = $("#daytourBooking").val();
//        console.log("Update Tour - "  + tourId);
        $("#updateId").val(tourCode);
//        console.log("Update Tour - " + $("#updateId").val());

        $.ajax({
            url: 'DaytourDetail.smi?InputTourCode=' + tourCode + '&daytourBooking=' + daytourBooking + '&action=deletePrice',
            type: 'POST',
            data: {},
            success: function () {
//                alert('${requestScope['TransactionResult']}');
                if (${! empty requestScope['TransactionResult']}) {
                    $('#textAlertDivSave').show();
                    alert('${requestScope['TransactionResult']}');
                }
//                console.log("success calling DaytourDetail Delete");
                $("#bookingTourPriceTable tbody").empty();
//                location.reload();
            },
            error: function () {
                console.log("error UpdTourCodeForm");
                alert("error on delete value list, pls contact admin");
            }
        });
        $("#UpdTourCode").modal("hide");
//        $("#UpdTourCodeForm").submit();

    }
    
   
</script>

