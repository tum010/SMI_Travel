<%-- 
    Document   : HotelBooking
    Created on : Dec 20, 2014, 1:19:53 PM
    Author     : sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/HotelBooking.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="booktype" value="${requestScope['BOOKING_TYPE']}" />
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="hotelBookingList" value="${requestScope['HotelBookingList']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="currency" value="${requestScope['Currency']}" />
<c:set var="lockUnlockBooking" value="${requestScope['LockUnlockBooking']}" />
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">

<section class="content-header" >
    <h1>
        Booking - Hotel
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">Hotel</a></li>
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
<!--Alert Save -->
<div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Save Success!</strong> 
</div>
<div id="textAlertDivNotSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Save Success!</strong> 
</div>

            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Hotel</b> </h4>
                </div>
                <div class="row-fluid">

                    <div class="form-actions pull-right" style="padding-right: 20px">
                    <c:if test="${lockUnlockBooking == 0}">
                        <a id="ButtonAdd" href="HotelDetail.smi?referenceNo=${param.referenceNo}&Order=${hotelBookingList.size()+1}&action=new" class="btn btn-success">
                            <span id="SpanAdd" class="glyphicon glyphicon-plus"></span>Add
                        </a>                    </c:if>
                    <c:if test="${lockUnlockBooking == 1}">
                        <a id="ButtonAdd" class="btn btn-success disabled">
                            <span class="glyphicon glyphicon-plus"></span>Add
                        </a>
                    </c:if>    

                    </div>
                    <c:choose>
                        <c:when test="${booktype == 'I'}">
                    <div class="form-actions pull-right" style="padding-right: 20px">
                        <div class="form-group">
                            <div class="col-sm-9">
                                <button id="ButtonPrint" type="button" onclick="printVoucher('${param.referenceNo}');" class="btn btn-default">
                                    <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print
                                </button> 
                            </div>
                        </div>
                    </div>
                    <div class="form-actions pull-right" style="padding-top: 2px">
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="Type">Type</label>
                            <div class="col-sm-9">
                                <select name="printtype" id="printtype"  class="form-control">
                                    <option value="0"  selected="selected">--select--</option>
                                    <option value="1"  > Hotel Voucher</option>
                                    <option value="2"  > Hotel Inbound Voucher</option>
                                    <option value="3"  > Hotel Voucher Email</option>
                                    <option value="4"  > Hotel Voucher Email (Agent)</option>
                                </select>
                            </div>
                        </div>
                    </div> 
                         </c:when>

                    </c:choose>    

                </div>  

            </div>
            <hr/>
            <!-- Hotel Table-->
            <table class="display" id="HotelTable">
                <thead class="datatable-header">
                    <tr>
                        <th>No</th>
                        <th>Hotel</th>
                        <th>Check In</th>
                        <th>Check Out</th>
                        <th>No.night</th>
                        <th>Remarks</th>
                        <th>Price Room</th>
                        <th>Price addition</th>
                        <th>Total Cost</th>
                        <th>Cur</th>
                        <th>Total Price</th>
                        <th>Cur</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <c:forEach var="b" items="${hotelBookingList}" varStatus="loopCounter">

                        <c:set var="colourStatus" value="" />
                        <c:set var="colourStatusFirstrow" value="" />

                        <c:if test="${b.MItemstatus.id == 2}">
                            <c:set var="colourStatus" value="style='background-color: #FFD3D3'" />
                            <c:set var="colourStatusFirstrow" value="background-color: #FFD3D3" />
                        </c:if>

                        <tr ${colourStatus} id="${b.id}">   
                            <td class="text-center" ${colourStatus}>${loopCounter.count}</td>
                            <td>${b.hotel.getName()}</td>
                            <td>${b.checkin}</td>
                            <td>${b.checkout}</td>
                    <script>
                        $(document).ready(function () {
                            getDate();
                            function getDate() {
                                var start = new Date("${b.checkin}");
                                var end = new Date("${b.checkout}");
                                var diff = new Date(end - start);
                                var days = diff / 1000 / 60 / 60 / 24;
                                if (days) {
                                    $("#day-${b.id}").text(days);
                                } else {
                                    $("#day-${b.id}").text(0);
                                }
                            }
                        });

                    </script>
                    <td class="text-center" id="day-${b.id}"></td>
                    <td>${b.remark}</td>
                    <td class="moneyformat text-right">${b.adult}</td>
                    <td class="moneyformat text-right">${b.child}</td>
                    <td class="moneyformat text-right">${b.totalcost}</td>
                    <td class="tdcenter">${b.curCost}</td>
                    <td class="moneyformat text-right">${b.adult + b.child}</td>
                    <td class="tdcenter">${b.curAmount}</td>
                    <td class="text-center">
                        
                        <a href="HotelDetail.smi?referenceNo=${param.referenceNo}&id=${b.id}&action=edit"><span class="glyphicon glyphicon-edit editicon" id="EditHotel-${loopCounter.count}" ></span></a>
                            <c:if test="${b.MItemstatus.id == 2}">
                            <span class="glyphicon glyphicon-plus addicon" id="EnableHotel-${loopCounter.count}"   onclick="EnableHotel('${b.id}', ' ${b.hotel.getName()}');" data-toggle="modal" data-target="#EnableHotel" ></span>
                        </c:if>
                        <c:if test="${b.MItemstatus.id == 1}">
                            <c:if test="${lockUnlockBooking == 0}">
                                <span class="glyphicon glyphicon-remove deleteicon" id="DisableHotel-${loopCounter.count}"   onclick="DeleteHotel('${b.id}', ' ${b.hotel.getName()}');" data-toggle="modal" data-target="#DeleteHotel" ></span>
                            </c:if>
                            <c:if test="${lockUnlockBooking == 1}">
                                <span class="glyphicon glyphicon-remove deleteicon disabled"></span>
                            </c:if>
                        </c:if>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<c:if test="${! empty param.result}">
    <c:if test="${param.result =='1'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${param.result =='0'}">        
        <script language="javascript">
            $('#textAlertDivNotSave').show();
        </script>
    </c:if>

</c:if>

<form role="form" id="HotelForm" method="post" action="HotelBooking.smi" class="form-horizontal">
    <input type="hidden" class="form-control" id="HotelID"   name="HotelID"  >     
    <input type="hidden" class="form-control" id="action"   name="action"  >     
    <input type="hidden" class="form-control" id="referenceNo"   name="referenceNo"  value="${param.referenceNo}" >     
</form> 

<!--Modal Add Hotel-->
<div class="modal fade" id="AddHotel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Add Hotel</h4>
            </div>
            <div class="modal-body">

                <div class="form-group row">
                    <label for="" class="col-sm-3 text-right">Hotel Name</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="" placeholder="Hotel Name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="" class="col-sm-3 text-right">ref</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="" placeholder="ref">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="" class="col-sm-3 text-right">ref confirm</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="" placeholder="ref confirm">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="" class="col-sm-3 text-right">Check in</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="" placeholder="Check out">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="" class="col-sm-3 text-right">Check out</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="" placeholder="Check out">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="" class="col-sm-3 text-right">Meal</label> 
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="" placeholder="Meal">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="" class="col-sm-3 text-right">Passenger</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="" placeholder="Passenger">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="" class="col-sm-3 text-right">remark</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="" placeholder="Remark">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="" class="col-sm-3 text-right">Flight</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="" placeholder="Flight">
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" onclick="save()" class="btn btn-primary btn-sm">Save</button>
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<div class="modal fade" id="DeleteHotel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Booking Hotel </h4>
            </div>
            <div class="modal-body" id="delCode">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /Delete Hotel modal -->      

<div class="modal fade" id="EnableHotel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Booking Hotel </h4>
            </div>
            <div class="modal-body" id="enableCode">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="Enable()" class="btn btn-success">Enable</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /Enable Hotel modal -->      


