<%-- 
    Document   : AirTicket
    Created on : Dec 19, 2014, 1:55:09 PM
    Author     : sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/AirticketDetail.js"></script> 
<link href="css/jquery-ui.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>

<c:set var="currentPnr" value="${requestScope['CurrentPnr']}" />
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="airline" value="${requestScope['Airline']}" />
<c:set var="flights" value="${requestScope['Flights']}" />
<c:set var="passengers" value="${requestScope['Passengers']}" />
<c:set var="mFlightList" value="${requestScope['MFlightList']}" />
<c:set var="mTicketTypeList" value="${requestScope['MTicketTypeList']}" />
<c:set var="mPricecategorysList" value="${requestScope['MPricecategorysList']}" />
<c:set var="mItemstatusList" value="${requestScope['MItemstatusList']}" />
<c:set var="InitialName" value="${requestScope['InitialName']}" />
<c:set var="mAirlineList" value="${requestScope['MAirlineList']}" />
<c:set var="airport" value="${requestScope['Airport']}" />
<c:set var="listBookingPnrs" value="${requestScope['List_BookingPnrs']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="action" value="${requestScope['Action']}" />
<c:set var="mInitialname" value="${requestScope['MInitialname']}" />
<c:set var="result" value="${requestScope['Result']}" />
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<c:set var="lockUnlockBooking" value="${requestScope['LockUnlockBooking']}" />
<c:set var="checkPnr_list" value="${requestScope['checkPnr_list']}" />
<c:set var="isBillStatus" value="${requestScope['IsBillStatus']}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<input type="hidden" value="${master.departmentNo}" id="departmentNo">
<input type="hidden" value="${master.id}" id="master-id">
<c:set var="enableSave" value="${requestScope['EnableSave']}" />
<!--Header-->
<section class="content-header" >
    <h1>
        Booking - Air Ticket
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">Air Ticket</a></li>
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
            <form action="AirTicketDetail.smi" method="post" id="AirticketForm">
                <input type="hidden" class="form-control" id="referenceNo"   name="referenceNo"  value="${param.referenceNo}" > 
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

                <div class="row-fluid">  
                    <div class="col-sm-4">
                        <h4><b>Air Ticket</b></h4>
                    </div>
                    <label class="col-sm-1 control-label text-right">PNR</label>
                    <div class="col-sm-2">
                        <div class="form-group">
                            <div class="input-group">
                                <c:if test="${currentPnr.isImport == null}">
                                    <input type="text" class="form-control" id="addpnrname" name="addpnrname" value="${currentPnr.pnr}" maxlength="6">
                                </c:if>
                                <c:if test="${currentPnr.isImport == 0}">
                                    <input type="text" class="form-control" id="addpnrname" name="addpnrname" value="${currentPnr.pnr}" maxlength="6">
                                </c:if>
                                <c:if test="${currentPnr.isImport == 1}">
                                    <input type="text" class="form-control" id="addpnrname" name="addpnrname" value="${currentPnr.pnr}" maxlength="6" readonly>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <label class="col-sm-1 control-label text-right">Import</label>
                    <div class="col-sm-2">
                        <div class="form-group">
                            <div class="input-group">
                                <input type="hidden" class="form-control" id="pnr" name="pnr" value="${currentPnr.id}">
                                <input type="text" class="form-control" id="pnr_name" name="pnr_name" value="">
                                <c:if test="${lockUnlockBooking == 0}">
                                    <c:if test="${isBillStatus == 0}">
                                        <span class="input-group-addon"><span data-toggle="modal" data-target="#ImportModal" class="glyphicon-import glyphicon"></span></span>
                                    </c:if>
                                    <c:if test="${isBillStatus == 1}">
                                        <span class="input-group-addon"><span class="glyphicon-import glyphicon disabled"></span></span>
                                    </c:if>
                                </c:if>
                                <c:if test="${lockUnlockBooking == 1}">
                                    <span class="input-group-addon"><span class="glyphicon-import glyphicon disabled"></span></span>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-1 text-right">   
                        <input type="hidden" id="countRow" name="countRow" value="${flights.size()+1}">
                        <c:choose>
                            <c:when test="${currentPnr.MItemstatus.id == 2}">
                                <button type="button" disabled class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Add</button>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${lockUnlockBooking == 0}">
                                    <c:if test="${isBillStatus == 0}">
                                        <a id="btn-add"  class="btn btn-success" data-toggle="collapse" data-parent="#accordion" aria-controls="collapseExample"><span class="glyphicon glyphicon-plus"></span> Add</a>
                                    </c:if>
                                    <c:if test="${isBillStatus == 1}">
                                        <a class="btn btn-success disabled">
                                            <span class="glyphicon glyphicon-plus"></span>Add</button>
                                        </a>   
                                    </c:if>
                                </c:if>
                                <c:if test="${lockUnlockBooking == 1}">
                                    <a class="btn btn-success disabled">
                                        <span class="glyphicon glyphicon-plus"></span>Add</button>
                                    </a>   
                                </c:if>
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div class="col-sm-1 text-right">
                        <a id="ButtonBack" href="AirTicket.smi?referenceNo=${param.referenceNo}&action=edit" class="btn btn-primary">
                            <span id="SpanBack" class="glyphicon glyphicon-arrow-left"></span> Back
                        </a>
                    </div>

                </div>
                <hr/>
                <!-- Air Table --> 
                <div class="row-fluid">
                    <table  class="display" id="TableAir">
                        <thead>
                            <tr class="datatable-header">
                                <th style="width: 1%">No</th>
                                <th style="width: 2%">Airline</th>
                                <th style="width: 5%">Flight</th>
                                <th style="width: 5%">From</th>
                                <th style="width: 5%">To</th>
                                <th colspan="2" style="width: 12%">Departure Date</th>
                                <th style="width: 8%">Ticket</th>
                                <th style="width: 15%">Class</th>
                                <th style="width: 5%">Cost</th>
                                <th style="width: 5%">Price</th>
                                <th style="width: 3%">Status</th>
                                    <c:if test="${currentPnr.MItemstatus.id != 2}">
                                    <th style="width: 1%">Action</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="flight" items="${flights}" varStatus="i">
                                <c:set var="colourStatus" value="" />
                                <c:set var="colourStatusFirstrow" value="" />
                                <c:if test="${flight.MItemstatus.id == 3}">
                                    <c:set var="colourStatus" value="style='background-color: #FFD3D3'" />
                                    <c:set var="colourStatusFirstrow" value="background-color: #FFD3D3" />
                                </c:if>
                                <tr ${colourStatus}>
                                    <td  ${colourStatus} class="text-center">                              
                                        <c:choose>
                                            <c:when test="${(flight.flightOrder != '') && (flight.flightOrder != null)}">
                                                <input type="text" id="flight-${i.count}-flightOrder" name="flight-${i.count}-flightOrder" class="form-control money2" maxlength="11" value="${flight.flightOrder}" />                               
                                            </c:when>
                                            <c:otherwise>
                                                <input type="text" id="flight-${i.count}-flightOrder" name="flight-${i.count}-flightOrder" class="form-control money2" maxlength="11" value="${i.count}" />
                                            </c:otherwise>    
                                        </c:choose>                                              
                                    </td>
                                    <td>${flight.airticketAirline.MAirline.getCode()}</td>
                                    <td>${flight.flightNo}</td>
                                    <td>${flight.sourceCode}</td>
                                    <td>${flight.desCode}</td>
<!--                                    <script>
                                        $(document).ready(function () {
                                            if("${flight.departDate}" !== ''){
                                                $("#departDate-${flight.id}").text(convertFormatDate("${flight.departDate}"));
                                            }
                                        });
                                    </script>-->
                                    <fmt:formatDate value="${flight.departDate}" var="departDate" pattern="dd-MM-yyyy" />
                                    <td>${departDate}</td>
                                    <td>
                                        <c:set var="departTimeTemp1" value="${fn:substring(flight.departTime, 0, 2)}" />
                                        <c:set var="departTimeTemp2" value="${fn:substring(flight.departTime, 2, 4)}" />
                                        <c:set value="${departTimeTemp1}:${departTimeTemp2}" var="dateDepartTimeTemps" />
                                        <fmt:parseDate value="${dateDepartTimeTemps}" var="dateDepartTimes" pattern="HH:mm" />
                                        <fmt:formatDate value="${dateDepartTimes}" pattern="HH:mm" />  
                                    </td>
                                    <td>
                                        <script>
                                            var tickettype = [];
                                        </script>

                                        <select id="flight-${i.count}-ticketTypeCom" name="flight-${i.count}-ticketTypeCom" class="form-control">          
                                            <option value=""></option>
                                            <c:forEach var="mticket" items="${mTicketTypeList}" >
                                                <c:set var="select" value=""  />
                                                <c:if test="${mticket.id == flight.MTicketType.id}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${mticket.id}" ${select}>${mticket.name}</option>
                                                <script>
                                                    tickettype.push({
                                                        ticket_id: "${mticket.id}",
                                                        ticket_name: "${mticket.name}"
                                                    });
                                                </script>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <div class="col-xs-8">
                                            <select id="flight-${i.count}-classCom" name="flight-${i.count}-classCom" class="form-control">          
                                                <option value=""></option>
                                                <c:forEach var="mflight" items="${mFlightList}" >
                                                    <c:set var="select" value=""  />
                                                    <c:if test="${mflight.id == flight.MFlight.id}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option value="${mflight.id}" ${select}>${mflight.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-xs-4">
                                            <input type="text" id="flight-${i.count}-subClassCom" name="flight-${i.count}-subClassCom" value="${flight.subFlightClass}" class="form-control" maxlength="1"/>    
                                        </div>
                                    </td>
                                    <td class="text-right "><fmt:formatNumber type="currency" pattern="#,##0;-#,##0" value="${flight.totalCost}" /></td>
                                    <td class="text-right "><fmt:formatNumber type="currency" pattern="#,##0;-#,##0" value="${flight.totalPrice}" /></td>
                                    <td>${flight.MItemstatus.getName()}</td>
                                    <td class="text-center">
                                        <a id="ButtonEdit${i.count}" class="carousel" data-toggle="collapse" data-parent="#accordion" data-target="#flight${i.count}" aria-expanded="true" aria-controls="collapseExample">
                                            <span id="SpanEdit${i.count}" class="glyphicon glyphicon-edit editicon"></span>
                                        </a>
                                        <c:if test="${flight.MItemstatus.id != 3}">
                                            <c:if test="${lockUnlockBooking == 0}"> 
                                                <c:if test="${flight.isBill == 0}">
                                                    <span id="SpanRemove${i.count}" class="glyphicon glyphicon-remove deleteicon" onclick="setDisableFlight('${flight.id}', '${flight.flightNo}');" data-toggle="modal" data-target="#DisableFlight" ></span>
                                                </c:if>
                                                <c:if test="${flight.isBill == 1}">
                                                    <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${lockUnlockBooking == 1}">
                                                <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                            </c:if>                                                
                                        </c:if>
                                        <c:if test="${flight.MItemstatus.id == 3}">
                                            <span id="SpanPlus${i.count}" class="glyphicon glyphicon-plus addicon" onclick="setEnableFlight('${flight.id}', '${flight.flightNo}');" data-toggle="modal" data-target="#EnableFlight" ></span>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>  

                </div>


                <!--collapse-->
                <script>
                    flight = [];
                </script>
                <input type="hidden" id="flightSize" name="flightSize" value="${flights.size()}"/>
                <c:forEach var="flight" items="${flights}" varStatus="fStatus">
                    <div class="collapse" id="flight${fStatus.count}">
                        <!--Order Panel-->
                        <div class="panel panel-default" style="margin-top: 10px">
                            <div class="panel-heading">
                                <h3 class="panel-title">Order</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row"  style="margin-bottom: 10px">
                                    <label class="col-sm-1 control-label text-right">Order</label>
                                    <div class="col-sm-2">  
                                        <input name="flight-${fStatus.count}-orderNo" id="flight-${fStatus.count}-orderNo" type="text" 
                                               class="form-control" value="${fStatus.count}" readonly="">
                                        <input name="flight-${fStatus.count}-id" id="flight-${fStatus.count}-id" 
                                               type="hidden" class="form-control" value="${flight.id}" readonly="">
                                    </div>
                                    <label class="col-sm-1 control-label text-right">Flight<strong style="color: red">*</strong></label>
                                    <div class="col-sm-2">  
                                        <input type="text" class="form-control flight-no" data-id="${fStatus.count}" 
                                               name="flight-${fStatus.count}-flightNo" id="flight-${fStatus.count}-flightNo" 
                                               value="${flight.flightNo}" maxlength="10" 
                                               data-bv-notempty data-bv-notempty-message="The flight is required"/>
                                    </div>
                                    <div class="col-sm-1 text-right"><strong>Airline</strong><strong style="color: red">*</strong></div>
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" id="airlineId${fStatus.count}" 
                                                       name="airlineId${fStatus.count}" value="${flight.airticketAirline.MAirline.getId()}">
                                                <input name="airlineCode${fStatus.count}" id="airlineCode${fStatus.count}"  
                                                       class="form-control airline" data-id="${fStatus.count}" 
                                                       value="${flight.airticketAirline.MAirline.getCode()}" 
                                                       data-bv-notempty data-bv-notempty-message="The airline is required"/>
                                                <span class="input-group-addon" data-toggle="modal" data-target="#AirlineModal" 
                                                      onclick="get_PId('airline',${fStatus.count})">
                                                    <span class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <input name="airlineName${fStatus.count}" id="airlineName${fStatus.count}" 
                                               class="form-control" disabled="" value="${flight.airticketAirline.MAirline.getName()}">
                                    </div>

                                </div>
                                <div class="row">
                                    <label class="col-sm-1 control-label text-right">Departure<strong style="color: red">*</strong></label>
                                    <div class="col-sm-2">  
                                        <div class="form-group">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" id="departure-${fStatus.count}-id" 
                                                       name="departure-${fStatus.count}-id">
                                                <input type="hidden" class="form-control" id="departure-${fStatus.count}-code"  
                                                       name="departure-${fStatus.count}-code" value="${flight.sourceCode}" />
                                                <input type="text" class="form-control" id="departure-${fStatus.count}-codeVal" 
                                                       name="departure-${fStatus.count}-codeVal" value="${flight.sourceCode}" 
                                                       data-bv-notempty data-bv-notempty-message="The departure is required"/>
                                                <span class="input-group-addon" data-toggle="modal" data-target="#DepartureModal" onclick="get_id(${fStatus.count})">
                                                    <i id="datadep_load-${fStatus.count}" class="fa fa-spinner fa-spin hidden"></i>
                                                    <span class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">  
                                        <input type="text" class="form-control" name="departure-${fStatus.count}-name" 
                                               id="departure-${fStatus.count}-name" value="" readonly="true">
                                    </div>

                                    <label class="col-sm-1 control-label text-right">Date<strong style="color: red">*</strong></label>
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <div class="input-group date" id="DepartureDate">
                                                <fmt:formatDate value="${flight.departDate}" var="departDate" pattern="dd-MM-yyyy" />
                                                <input type="text" class="form-control" value="${departDate}" 
                                                       name="flight-${fStatus.count}-departDate" id="flight-${fStatus.count}-departDate" 
                                                       data-date-format="DD-MM-YYYY" maxlength="10"  
                                                       data-bv-notempty />
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <label class="col-sm-1 control-label text-right">Time</label>
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <div class="input-group times" id="departtimepanel${fStatus.count}">
<!--                                                <input type="text" class="form-control" value="${flight.departTime}" name="flight-${fStatus.count}-departTime" 
                                                       id="flight-${fStatus.count}-departTime" maxlength="4"
                                                       data-bv-notempty data-bv-notempty-message="The Time is required"/>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                                                </span>-->
                                                <c:set var="departTime1" value="${fn:substring(flight.departTime, 0, 2)}" />
                                                <c:set var="departTime2" value="${fn:substring(flight.departTime, 2,4)}" />
                                                <c:set value="${departTime1}:${departTime2}" var="dateDepartTimeTemp" />
                                                <fmt:parseDate value="${dateDepartTimeTemp}" var="dateDepartTime"
                                                pattern="HH:mm" />
                                                <input id="flight-${fStatus.count}-departTime" name="flight-${fStatus.count}-departTime" class="form-control time" maxlength="255" style="width: 60px" placeholder="HH:MM" value="<fmt:formatDate value="${dateDepartTime}" pattern="HH:mm" />" onkeyup="validateTimeDepart(this,${fStatus.count});" >
                                            </div>
                                        </div>
                                    </div>
                                </div>  
                                <div class="row">
                                    <label class="col-sm-1 control-label text-right">Arrival<strong style="color: red">*</strong></label>
                                    <div class="col-sm-2">  
                                        <div class="form-group">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" id="arrival-${fStatus.count}-id" name="arrival-${fStatus.count}-id">
                                                <input type="hidden" class="form-control" id="arrival-${fStatus.count}-code" 
                                                       name="arrival-${fStatus.count}-code" value="${flight.desCode}"  />
                                                <input type="text" class="form-control" id="arrival-${fStatus.count}-codeVal" 
                                                       name="arrival-${fStatus.count}-codeVal" value="${flight.desCode}" 
                                                       data-bv-notempty data-bv-notempty-message="The arrival is required"/>
                                                <span class="input-group-addon" data-toggle="modal" data-target="#ArrivalModal" onclick="get_id(${fStatus.count})">
                                                    <i id="dataarri_load-${fStatus.count}" class="fa fa-spinner fa-spin hidden"></i>
                                                    <span class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">  
                                        <input type="text" class="form-control" id="arrival-${fStatus.count}-name" name="arrival-${fStatus.count}-name" readonly="">
                                    </div>
                                    <label class="col-sm-1 control-label text-right">Date<strong style="color: red">*</strong></label>
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <div class="input-group date" id="ArrivalDate">
                                                <fmt:formatDate value="${flight.arriveDate}" var="arriveDate" pattern="dd-MM-yyyy" />
                                                <input name="flight-${fStatus.count}-arriveDate" id="flight-${fStatus.count}-arriveDate"  
                                                       type="text" class="form-control" value="${arriveDate}" 
                                                       data-date-format="DD-MM-YYYY" maxlength="10"
                                                       data-bv-notempty />
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <label class="col-sm-1 control-label text-right">Time</label>
                                    <div class="col-sm-2">
                                        <div class="form-group">
                                            <div class="input-group times" id="arrivetimepanel${fStatus.count}">
<!--                                                <input name="flight-${fStatus.count}-arriveTime" id="flight-${fStatus.count}-arriveTime" 
                                                       type="text" class="form-control" value="${flight.arriveTime}"  maxlength="4"
                                                       data-bv-notempty data-bv-notempty-message="The time is required"/>
                                                <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>
                                                </span>-->
                                                
                                                <c:set var="arriveTime1" value="${fn:substring(flight.arriveTime, 0, 2)}" />
                                                <c:set var="arriveTime2" value="${fn:substring(flight.arriveTime, 2,4)}" />
                                                <c:set value="${arriveTime1}:${arriveTime2}" var="dateArriveTimeTemp" />
                                                <fmt:parseDate value="${dateArriveTimeTemp}" var="dateArriveTime"
                                                pattern="HH:mm" />
                                                <input id="flight-${fStatus.count}-arriveTime" name="flight-${fStatus.count}-arriveTime" class="form-control time" maxlength="255" style="width: 60px" placeholder="HH:MM" value="<fmt:formatDate value="${dateArriveTime}" pattern="HH:mm" />" onkeyup="validateTimeArrive(this,${fStatus.count});">
                                                
                                                <!--<input id="flight-${fStatus.count}-arriveTime" name="flight-${fStatus.count}-arriveTime" class="form-control time" maxlength="255" style="width: 60px" placeholder="HH:MM" value="${flight.arriveTime}">-->
                                            </div>
                                        </div>
                                    </div>
                                </div>  
                                <div class="row"  style="margin-bottom: 10px">
                                    <label class="col-sm-1 control-label text-right">Ticket</label>
                                    <div class="col-sm-5">  
                                        <select class="form-control" id="flight-${fStatus.count}-ticketType" name="flight-${fStatus.count}-ticketType" value="${flight.MTicketType.id}">
                                            <option value=""></option>
                                            <c:forEach var="mticket" items="${mTicketTypeList}" >
                                                <option value="${mticket.id}" ${select}>${mticket.name}</option>
                                            </c:forEach>
                                        </select> 
                                    </div>
                                    <label class="col-sm-1 control-label text-right">Class</label>
                                    <div class="col-sm-2">  
                                        <select class="form-control" id="flight-${fStatus.count}-class" 
                                                name="flight-${fStatus.count}-class" value="${flight.MFlight.id}" >
                                            <option value=""></option>
                                            <c:forEach var="m" items="${mFlightList}">
                                                <option value="${m.id}" ${select}>${m.name}</option>
                                            </c:forEach>
                                        </select> 
                                    </div>
                                    <label class="col-sm-1 control-label text-right">Sub</label>
                                    <div class="col-sm-1">  
                                        <input type="text" class="form-control" name="flight-${fStatus.count}-subClass" id="flight-${fStatus.count}-subClass" value="${flight.subFlightClass}" maxlength="1">
                                    </div>
                                </div>
                                <div class="row"  style="margin-bottom: 10px">
                                    <label class="col-sm-1 control-label text-right">Status</label>
                                    <div class="col-sm-2">  
                                        <select class="form-control" name="flight-${fStatus.count}-status" id="flight-${fStatus.count}-status">
                                            <c:forEach var="status" items="${mItemstatusList}">
                                                <option value="${status.name}">${status.name}</option>
                                            </c:forEach>
                                        </select> 
                                    </div>

                                </div>

                            </div>
                        </div>

                        <!--Cost Price Panel-->
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Calculation</h3>
                            </div>
                            <div class="panel-body">
                                <div class="col-sm-3" style="border-right:solid 1px #D9D9D9">
                                    <div class="row form-group text-center">
                                        <label class="control-label">Cost</label>
                                    </div>
                                    <div class="row form-group">
                                        <label class="col-lg-3 control-label text-right">Adult</label>
                                        <div class="col-sm-6">
                                            <input id="adCost-${fStatus.count}" name="adCost-${fStatus.count}" class="form-control text-right decimal" value="${flight.adCost}" type="text" maxlength="10"  tabindex="1"/>
                                        </div>

                                    </div>
                                    <div class="row form-group">
                                        <label class="col-lg-3 control-label text-right">Child</label>
                                        <div class="col-sm-6">
                                            <input id="chCost-${fStatus.count}" name="chCost-${fStatus.count}" class="form-control text-right decimal" value="${flight.chCost}" type="text" maxlength="10" tabindex="4"/>
                                        </div>

                                    </div>
                                    <div class="row form-group text-right">
                                        <label class="col-lg-3 control-label">Infant</label>
                                        <div class="col-sm-6">
                                            <input id="inCost-${fStatus.count}" name="inCost-${fStatus.count}" class="form-control text-right decimal" value="${flight.inCost}" type="text" maxlength="10" tabindex="7"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-3" style="border-right:solid 1px #D9D9D9">
                                    <div class="row form-group text-center">
                                        <label class="control-label">Tax</label>
                                    </div>
                                    <div class="row form-group">
                                        <label class="col-lg-3 control-label text-right">Adult</label>
                                        <div class="col-sm-6">
                                            <input id="adTaxCost-${fStatus.count}" name="adTaxCost-${fStatus.count}" class="form-control text-right decimal" value="${flight.adTaxCost}" type="text" maxlength="10" tabindex="3"/>
                                        </div>

                                    </div>
                                    <div class="row form-group">
                                        <label class="col-lg-3 control-label text-right">Child</label>
                                        <div class="col-sm-6">
                                            <input id="chTaxCost-${fStatus.count}" name="chTaxCost-${fStatus.count}" class="form-control text-right decimal" value="${flight.chTaxCost}" type="text" maxlength="10" tabindex="6"/>
                                        </div>

                                    </div>
                                    <div class="row form-group text-right">
                                        <label class="col-lg-3 control-label">Infant</label>
                                        <div class="col-sm-6">
                                            <input id="inTaxCost-${fStatus.count}" name="inTaxCost-${fStatus.count}" class="form-control text-right decimal" value="${flight.inTaxCost}" type="text" maxlength="10" tabindex="9"/>
                                        </div>
                                    </div>
                                </div>        
                                <div class="col-sm-3" style="border-right:solid 1px #D9D9D9">
                                    <div class="row form-group text-center">
                                        <label class="control-label">Price</label>
                                    </div>
                                    <div class="row form-group">
                                        <label class="col-lg-3 control-label text-right">Adult</label>
                                        <div class="col-sm-6">
                                            <input id="adPrice-${fStatus.count}" name="adPrice-${fStatus.count}" class="form-control text-right decimal" value="${flight.adPrice}" type="text" maxlength="10" tabindex="2"/>
                                        </div>

                                    </div>
                                    <div class="row form-group">
                                        <label class="col-lg-3 control-label text-right">Child</label>
                                        <div class="col-sm-6">
                                            <input id="chPrice-${fStatus.count}" name="chPrice-${fStatus.count}" class="form-control text-right decimal" value="${flight.chPrice}" type="text" maxlength="10" tabindex="5"/>
                                        </div>

                                    </div>
                                    <div class="row form-group text-right">
                                        <label class="col-lg-3 control-label">Infant</label>
                                        <div class="col-sm-6">
                                            <input id="inPrice-${fStatus.count}" name="inPrice-${fStatus.count}" class="form-control text-right decimal" value="${flight.inPrice}" type="text" maxlength="10" tabindex="8"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <div class="row form-group text-center">
                                        <label class="control-label">Tax</label>
                                    </div>
                                    <div class="row form-group">
                                        <label class="col-lg-3 control-label text-right">Adult</label>
                                        <div class="col-sm-6">
                                            <input id="adTax-${fStatus.count}" name="adTax-${fStatus.count}" class="form-control text-right decimal" value="${flight.adTax}" type="text" maxlength="10" tabindex="3"/>
                                        </div>

                                    </div>
                                    <div class="row form-group">
                                        <label class="col-lg-3 control-label text-right">Child</label>
                                        <div class="col-sm-6">
                                            <input id="chTax-${fStatus.count}" name="chTax-${fStatus.count}" class="form-control text-right decimal" value="${flight.chTax}" type="text" maxlength="10" tabindex="6"/>
                                        </div>

                                    </div>
                                    <div class="row form-group text-right">
                                        <label class="col-lg-3 control-label">Infant</label>
                                        <div class="col-sm-6">
                                            <input id="inTax-${fStatus.count}" name="inTax-${fStatus.count}" class="form-control text-right decimal" value="${flight.inTax}" type="text" maxlength="10" tabindex="9"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script>
                        var showflagDep = 1;
                        var showflagArr = 1;
                        $(document).ready(function () {
//                            var flightSize = parseInt($("#flightSize").val());
//                            if(flightSize > 0){
//                                for(var i=1; i<=flightSize; i++){
//                                    if($("#flight-" + i + "-departDate").val() !== ''){
//                                        var departDate = convertFormatDate($("#flight-" + i + "-departDate").val());
//                                       $("#flight-" + i + "-departDate").val(departDate); 
//                                    }  
//                                    if($("#flight-" + i + "-arriveDate").val() !== ''){
//                                        var arriveDate = convertFormatDate($("#flight-" + i + "-arriveDate").val());
//                                       $("#flight-" + i + "-arriveDate").val(arriveDate); 
//                                    }
//                                }          
//                            }
                        //Begin Departure Table Load
                            $("#departure-${fStatus.count}-codeVal").keyup(function (event) {
                                var position = $(this).offset();
                                $(".ui-widget").css("top", position.top + 30);
                                $(".ui-widget").css("left", position.left); 
                                if($(this).val() === ""){
                                    $("#departure-${fStatus.count}-id").val("");
                                    $("#departure-${fStatus.count}-code").val("");
                                    $("#departure-${fStatus.count}-name").val("");
                                }else{
                                    if(event.keyCode === 13){
                                        getDepartureAirport(this.value,${fStatus.count},0); 
                                        console.log("name="+this.value);
                                    }
                                }

                            }); 

                             $("#departure-${fStatus.count}-codeVal").keydown(function (event) {
                                if(! $.isFunction($.fn.curCSS)) {
                                   $.curCSS = $.css; 
                                   $.fn.curCSS = $.fn.css; 
                                }
                                 var position = $(this).offset();
                                    $(".ui-widget").css("top", position.top+30);
                                    $(".ui-widget").css("left", position.left); 
                                    if(showflagDep === 0){
                                        $(".ui-widget").css("top", -1000);
                                        showflagDep = 1;
                                    }
                            });
                        //End Departure Table Load
                        
                        //Begin Arrival Table Load
                            $("#arrival-${fStatus.count}-codeVal").keyup(function (event) {
                                var position = $(this).offset();
                                $(".ui-widget").css("top", position.top + 30);
                                $(".ui-widget").css("left", position.left); 
                                if($(this).val() === ""){
                                    $("#arrival-${fStatus.count}-id").val("");
                                    $("#arrival-${fStatus.count}-code").val("");
                                    $("#arrival-${fStatus.count}-name").val("");
                                }else{
                                    if(event.keyCode === 13){
                                        getArrivalAirport(this.value,${fStatus.count},0); 
                                        console.log("name="+this.value);
                                    }
                                }

                            }); 

                             $("#arrival-${fStatus.count}-codeVal").keydown(function (event) {
                                if(! $.isFunction($.fn.curCSS)) {
                                   $.curCSS = $.css; 
                                   $.fn.curCSS = $.fn.css; 
                                }
                                 var position = $(this).offset();
                                    $(".ui-widget").css("top", position.top+30);
                                    $(".ui-widget").css("left", position.left); 
                                    if(showflagArr === 0){
                                        $(".ui-widget").css("top", -1000);
                                        showflagArr = 1;
                                    }
                            });
                        //End Arrival Table Load
                        });
                        
                        
                        function getDepartureAirport(name, count,option) {//winit
                            var servletName = 'AirTicketServlet';
                            var servicesName = 'AJAXBean';
                            var param = 'action=' + 'text' +
                                    '&servletName=' + servletName +
                                    '&servicesName=' + servicesName +
                                    '&name=' + name +
                                    '&type=' + 'getautoairport';
                            console.log("Ajax param [" + param + "]");
                            CallAjaxDepartureAirport(param, count,option);
                        }

                        function getArrivalAirport(name, count,option) {
                            var servletName = 'AirTicketServlet';
                            var servicesName = 'AJAXBean';
                            var param = 'action=' + 'text' +
                                    '&servletName=' + servletName +
                                    '&servicesName=' + servicesName +
                                    '&name=' + name +
                                    '&type=' + 'getautoairport';
                            console.log("Ajax param [" + param + "]");
                            CallAjaxArrivalAirport(param, count,option);
                        }

                        function CallAjaxDepartureAirport(param,count,option){
                            var url = 'AJAXServlet';
                            var depArray = [];
                            var depListId= [];
                            var depListCode= [];
                            var depListName= [];
                            var depid , depcode ,depname;
                            $("#departure-" + count + "-codeVal").autocomplete("destroy");
                            try {
                               $.ajax({
                                   type: "POST",
                                   url: url,
                                   cache: false,
                                   data: param,
                                   beforeSend: function() {
                                      $("#datadep_load-"+count).removeClass("hidden");    
                                   },
                                   success: function(msg) {     
                                       console.log("getDepartureAirport =="+msg);
                                       var depJson =  JSON.parse(msg);
                                       for (var i in depJson){
                                           if (depJson.hasOwnProperty(i)){
                                               depid = depJson[i].id;
                                               depcode = depJson[i].code;
                                               depname = depJson[i].name;
                                               depArray.push(depcode);
                                               depArray.push(depname);
                                               depListId.push(depid);
                                               depListCode.push(depcode);
                                               depListName.push(depname);
                                           }                 
                                           $("#datadep_load-"+count).addClass("hidden"); 
                                       }
                                       $("#departure-" + count + "-id").val(depid);
//                                       $("#departure-" + count + "-code").val(depcode);
                                       $("#departure-" + count + "-name").val(depname);
                                       $("#departure-" + count + "-codeVal").autocomplete({
                                           source: depArray,
                                           close: function(){
                                               $("#departure-" + count + "-codeVal").trigger("keyup");
                                               var depselect = $("#departure-" + count + "-codeVal").val();
                                               for(var i =0;i<depListId.length;i++){
                                                   if((depselect==depListName[i])||(depselect==depListCode[i])){      
                                                       $("#departure-" + count + "-id").val(depListId[i]);
                                                       $("#departure-" + count + "-code").val(depListCode[i]);
                                                       $("#departure-" + count + "-codeVal").val(depListCode[i]);
                                                       $("#departure-" + count + "-name").val(depListName[i]);
                                                   }                 
                                               }   
                                           }
                                        });

                                       var selectval = $("#departure-" + count + "-codeVal").val();
                                       for(var i =0;i<depListId.length;i++){
                                           if(selectval==depListName[i]){
                                              $("#departure-" + count + "-codeVal").val(depListCode[i]);
                                           }
                                       }
                                       if(depListCode.length === 1){
                                           showflagDep = 0;
                                           $("#departure-" + count + "-codeVal").val(depListCode[0]);
                                       }
                                       if(option == 1){
                                           return;
                                       }else{
                                            var event = jQuery.Event('keydown');
                                            event.keyCode = 40;
                                            $("#departure-" + count + "-codeVal").trigger(event);
                                       }
                                   }, error: function(msg) {
                                       console.log('auto Departure  ERROR');
                                       $("#datadep_load-"+count).addClass("hidden");
                                   }
                               });
                           } catch (e) {
                               alert(e);
                           }
                       }

                    function CallAjaxArrivalAirport(param,count,option){
                        var url = 'AJAXServlet';
                        var arrArray = [];
                        var arrListId= [];
                        var arrListCode= [];
                        var arrListName= [];
                        var arrid , arrcode ,arrname;
                        $("#arrival-"+count+"-codeVal").autocomplete("destroy");
                        try {
                           $.ajax({
                               type: "POST",
                               url: url,
                               cache: false,
                               data: param,
                               beforeSend: function() {
                                  $("#dataarri_load-"+count).removeClass("hidden");    
                               },
                               success: function(msg) {     
                                   console.log("getArrivalAirport =="+msg);
                                   var arrJson =  JSON.parse(msg);
                                   for (var i in arrJson){
                                       if (arrJson.hasOwnProperty(i)){
                                           arrid = arrJson[i].id;
                                           arrcode = arrJson[i].code;
                                           arrname = arrJson[i].name;
                                           arrArray.push(arrcode);
                                           arrArray.push(arrname);
                                           arrListId.push(arrid);
                                           arrListCode.push(arrcode);
                                           arrListName.push(arrname);
                                       }                 
                                       $("#dataarri_load-"+count).addClass("hidden"); 
                                   }
                                   $("#arrival-"+count+"-id").val(arrid);
//                                   $("#arrival-"+count+"-code").val(arrcode);
                                   $("#arrival-"+count+"-name").val(arrname);

                                   $("#arrival-"+count+"-codeVal").autocomplete({
                                       source: arrArray,
                                       close: function(){
                                           $("#arrival-"+count+"-codeVal").trigger("keyup");
                                           var arrselect = $("#arrival-"+count+"-codeVal").val();
                                           for(var i =0;i<arrListId.length;i++){
                                               if((arrselect==arrListName[i])||(arrselect==arrListCode[i])){      
                                                   $("#arrival-"+count+"-id").val(arrListId[i]);
                                                   $("#arrival-"+count+"-code").val(arrListCode[i]);
                                                   $("#arrival-"+count+"-codeVal").val(arrListCode[i]);
                                                   $("#arrival-"+count+"-name").val(arrListName[i]);
                                               }                 
                                           }   
                                       }
                                    });

                                   var selectval = $("#arrival-"+count+"-codeVal").val();
                                   for(var i =0;i<arrListId.length;i++){
                                       if(selectval==arrListName[i]){
                                           $("#arrival-"+count+"-codeVal").val(arrListCode[i]);
                                       }
                                   }
                                   if(arrListCode.length === 1){
                                       showflagArr = 0;
                                       $("#arrival-"+count+"-codeVal").val(arrListCode[0]);
                                   }
                                   if(option == 1){
                                       return;
                                   }else{
                                       var event = jQuery.Event('keydown');
                                        event.keyCode = 40;
                                        $("#arrival-"+count+"-codeVal").trigger(event);
                                   }
                                   
                               }, error: function(msg) {
                                   console.log('auto Arrival ERROR');
                                   $("#dataarri_load-"+count).addClass("hidden");
                               }
                           });
                       } catch (e) {
                           alert(e);
                       }
                   }

                        flight.push({id: "${fStatus.count}",
                            air_id: "${flight.airticketAirline.MAirline.id}",
                            air_code: "${flight.airticketAirline.MAirline.code}",
                            air_name: "${flight.airticketAirline.MAirline.name}"
                        });


                        $(document).ready(function () {

                            var status = "${flight.MItemstatus.name}";
                            var tickettype = "${flight.MTicketType.id}";
                            var flightClass = "${flight.MFlight.id}";
                            $("#flight-${fStatus.count}-ticketType").val(tickettype);
                            $("#flight-${fStatus.count}-class").val(flightClass);
                            $("#flight-${fStatus.count}-status").val(status);

                            var codeAirline = [];
                            $.each(airline, function (key, value) {//winit
                                codeAirline.push(value.airline_code);
                                if (!(value.airline_name in codeAirline)) {
                                    codeAirline.push(value.airline_name);
                                }
                            });
                            $("#airlineCode${fStatus.count}").autocomplete({
                                source: codeAirline,
                                close: function (event, ui) {
                                    $("#airlineCode${fStatus.count}").trigger('keyup');
                                }
                            });
                            $("#airlineCode${fStatus.count}").on('keyup', function () {
                                var position = $(this).offset();
                                console.log("positon :" + position.top);
                                $(".ui-widget").css("top", position.top + 30);
                                $(".ui-widget").css("left", position.left);
                                var code = this.value.toUpperCase();
                                var name = this.value;
                                $("#airlineId${fStatus.count},#airlineName${fStatus.count}").val(null);
                                $.each(airline, function (key, value) {
                                    if (value.airline_code.toUpperCase() === code) {
                                        $("#airlineId${fStatus.count}").val(value.airline_id);
                                        $("#airlineName${fStatus.count}").val(value.airline_name);
                                    }
                                    if (name === value.airline_name) {
                                        $("#airlineId${fStatus.count}").val(value.airline_id);
                                        $("#airlineCode${fStatus.count}").val(value.airline_code);
                                        $("#airlineName${fStatus.count}").val(value.airline_name);
                                        code = $("#airlineCode${fStatus.count}").val().toUpperCase();

                                    }
                                });
                            });
//Departure--------------------------------------------------------------------------------------                            
//                            var codeDeparture = [];
//                            $.each(a, function (key, value) {//winit
//                                codeDeparture.push(value.code);
//                                if (!(value.name in codeDeparture)) {
//                                    codeDeparture.push(value.name);
//                                }
//                            });
//                            $("#departure-${fStatus.count}-code").autocomplete({
//                                source: codeDeparture,
//                                close: function (event, ui) {
//                                    $("#departure-${fStatus.count}-code").trigger('keyup');
//                                }
//                            });
//                            $("#departure-${fStatus.count}-code").on('keyup', function () {
//                                var position = $(this).offset();
//                                console.log("positon :" + position.top);
//                                $(".ui-widget").css("top", position.top + 30);
//                                $(".ui-widget").css("left", position.left);
//                                var code = this.value.toUpperCase();
//                                var name = this.value;
//                                $("#departure-${fStatus.count}-id,#departure-${fStatus.count}-name").val(null);
//                                $.each(a, function (key, value) {
//                                    if (value.code.toUpperCase() === code) {
//                                        console.log('ok');
//                                        $("#departure-${fStatus.count}-id").val(value.id);
//                                        $("#departure-${fStatus.count}-name").val(value.name);
//                                    }
//                                    if (name === value.name) {
//                                        $("#departure-${fStatus.count}-id").val(value.id);
//                                        $("#departure-${fStatus.count}-code").val(value.code);
//                                        $("#departure-${fStatus.count}-name").val(value.name);
//                                        code = $("#departure-${fStatus.count}-code").val().toUpperCase();
//
//                                    }
//                                });
//                            });
//Arrival--------------------------------------------------------------------------------------
//                            var codeArrival = [];
//                            $.each(a, function (key, value) {
//                                codeArrival.push(value.code);
//                                if (!(value.name in codeArrival)) {
//                                    codeArrival.push(value.name);
//                                }
//                            });
//                            $("#arrival-${fStatus.count}-code").autocomplete({
//                                source: codeArrival,
//                                close: function (event, ui) {
//                                    $("#arrival-${fStatus.count}-code").trigger('keyup');
//                                }
//                            });
//                            $("#arrival-${fStatus.count}-code").on('keyup', function () {
//                                var position = $(this).offset();
//                                console.log("positon :" + position.top);
//                                $(".ui-widget").css("top", position.top + 30);
//                                $(".ui-widget").css("left", position.left);
//                                var code = this.value.toUpperCase();
//                                var name = this.value;
//                                $("#arrival-${fStatus.count}-id,#arrival-${fStatus.count}-name").val(null);
//                                $.each(a, function (key, value) {
//                                    if (value.code.toUpperCase() === code) {
//                                        console.log('ok');
//                                        $("#arrival-${fStatus.count}-id").val(value.id);
//                                        $("#arrival-${fStatus.count}-name").val(value.name);
//                                    }
//                                    if (name === value.name) {
//                                        $("#arrival-${fStatus.count}-id").val(value.id);
//                                        $("#arrival-${fStatus.count}-code").val(value.code);
//                                        $("#arrival-${fStatus.count}-name").val(value.name);
//                                        code = $("#arrival-${fStatus.count}-code").val().toUpperCase();
//
//                                    }
//                                });
//                            });





                            // SET FIX DATE START AND STOP
                            $('#flight-${fStatus.count}-departDate,#flight-${fStatus.count}-arriveDate').on('focusout', function () {
                                console.log('on input');
////                                alert($("#flight-1-departDate").val());
                                var start = new Date(convertFormatDate($("#flight-${fStatus.count}-departDate").val()));
                                var stop = new Date(convertFormatDate($("#flight-${fStatus.count}-arriveDate").val()));
                                var check = getDate(start, stop);
                                if (!check) {
                                    alert('Arrival date must over Departure date');
                                }
                            });
                            $("body").not('.date').on('click', function () {
                                console.log('on input');
                                var start = new Date(convertFormatDate($("#flight-${fStatus.count}-departDate").val()));
                                var stop = new Date(convertFormatDate($("#flight-${fStatus.count}-arriveDate").val()));
                                var check = getDate(start, stop);
                                if (!check) {
                                    alert('Arrival date must over Departure date');
                                }
                            });

                        });
                    </script>

                </c:forEach>
                <!--list dropdown  dummy--> 
                <div class="hidden">  
                    <select class="form-control" name="flight-0-status" id="flight-0-status">
                        <c:forEach var="status" items="${mItemstatusList}">
                            <option value="${status.name}">${status.name}</option>
                        </c:forEach>
                    </select> 
                    <select class="form-control" id="flight-0-ticketType" name="flight-0-ticketType">
                        <c:forEach var="mticket" items="${mTicketTypeList}">
                            <option value="${mticket.id}">${mticket.name}</option>
                        </c:forEach>
                    </select>
                    <select class="form-control" id="flight-0-class" name="flight-0-class">
                        <c:forEach var="m" items="${mFlightList}">
                            <option class="flightClass" value="${m.id}">${m.name}</option>
                        </c:forEach>
                    </select> 
                </div>
                <!--add flight-->
                <div id="divAddFight" style="padding-bottom: 20px">

                </div>


                <!-- Passenger Panel-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Passenger</h3>
                    </div>
                    <div class="text-right" style="padding:5px 15px">
                        <%--<c:if test="${empty currentPnr.pnr || currentPnr.pnr=='DUMMY' }">--%>
                        <c:if test="${lockUnlockBooking == 0}">
                            <c:if test="${isBillStatus == 0}">
                                <a id="btn-addPassenger" class="btn btn-success" data-toggle="collapse" data-parent="#accordion"  aria-controls="collapseExample">
                                    <span class="glyphicon glyphicon-plus"></span> Add
                                </a>
                            </c:if>
                            <c:if test="${isBillStatus == 1}">
                                <a class="btn btn-success disabled">
                                    <span class="glyphicon glyphicon-plus"></span>Add</button>
                                </a>
                            </c:if>    
                        </c:if>
                        <c:if test="${lockUnlockBooking == 1}">
                            <a class="btn btn-success disabled">
                                <span class="glyphicon glyphicon-plus"></span>Add</button>
                            </a>   
                        </c:if>
                        <%--</c:if>--%>
                    </div>
                    <div class="panel-body">
                        <strong><div id="showTotalPassener"></div></strong>
                        <table  class="display table-striped table-responsive" id="passenger_table">
                            <thead>
                                <tr class="datatable-header">
                                    <th>Airline</th>
                                    <th>Passenger Type</th>
                                    <th colspan="3">Ticket</th>
                                    <th>Fare</th>
                                    <th>Tax</th>
                                    <th>Ticket Type</th>
                                    <th>Ticket Form</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <input type="text" hidden="" name="countRowPassenger" id="countRowPassenger" value="${passengers.size()+1}">
                            <c:forEach var="passenger" items="${passengers}" varStatus="pStatus">
                                <tr>
                                    <td style="width: 10%">${passenger.airticketAirline.MAirline.code}</td>

                                    <td style="width: 10%"><!--winit-->
                                        <select id="passengerCategoryCom${pStatus.count}" name="passengerCategoryCom${pStatus.count}" class="form-control">          
                                            <c:forEach var="category" items="${mPricecategorysList}" >
                                                <c:set var="select" value=""  />
                                                <c:if test="${category.code == passenger.MPricecategory.code}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${category.code}" ${select}>${category.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td style="width: 5%">${passenger.series1}</td>
                                    <td style="width: 10%">${passenger.series2}</td>
                                    <td style="width: 5%">${passenger.series3}</td>
                                    <td class="text-right moneyformat" style="width: 10%">${passenger.ticketFare}</td>
                                    <td class="text-right moneyformat" style="width: 8%">${passenger.ticketTax}</td>
                                    <td class="text-center" style="width: 6%">
                                        <select id="passengerTicketTypeCom${pStatus.count}" name="passengerTicketTypeCom${pStatus.count}" class="form-control">          
                                            <c:if test="${passenger.ticketType eq 'I'}">
                                                <c:set var="select" value="selected" />
                                                <c:set var="nameChecked" value="Inter" />
                                                <c:set var="nameOther" value="Domestic" />
                                                <c:set var="code" value="D" />
                                            </c:if>
                                            <c:if test="${passenger.ticketType eq 'D'}">
                                                <c:set var="select" value="selected" />
                                                <c:set var="nameChecked" value="Domestic" />
                                                <c:set var="nameOther" value="Inter" />
                                                <c:set var="code" value="I" />
                                            </c:if>
                                            <option value="${passenger.ticketType}" ${select}>${nameChecked}</option>
                                            <option value="${code}" >${nameOther}</option>
                                        </select>
                                    </td>
                                    <td class="text-center" style="width: 6%">
                                        <select id="passengerFromCom${pStatus.count}" name="passengerFromCom${pStatus.count}" class="form-control">          
                                            <c:if test="${passenger.ticketFrom eq 'C'}">
                                                <c:set var="select" value="selected" />
                                                <c:set var="nameChecked" value="In" />
                                                <c:set var="nameOther" value="Out" />
                                                <c:set var="code" value="O" />
                                            </c:if>
                                            <c:if test="${passenger.ticketFrom  eq 'O'}">
                                                <c:set var="select" value="selected" />
                                                <c:set var="nameChecked" value="Out" />
                                                <c:set var="nameOther" value="In" />
                                                <c:set var="code" value="C" />
                                            </c:if>
                                            <option value="${passenger.ticketFrom}" ${select}>${nameChecked}</option>
                                            <option value="${code}" >${nameOther}</option>
                                        </select>
                                    </td>
                                    <td class="text-center" style="width: 3%">
                                        <a id="passenger_tableButtonEdit${pStatus.count}" class="carousel" data-toggle="collapse" data-parent="#accordion" data-target="#passenger${pStatus.count}" aria-expanded="true" aria-controls="collapseExample">
                                            <span id="passenger_tableSpanEdit${pStatus.count}" class="glyphicon glyphicon-edit editicon"></span>
                                        </a>
                                        <%--<c:if test="${flight.MItemstatus.id != 2}">--%>
                                            <c:if test="${lockUnlockBooking == 0}">
                                                <c:if test="${isBillStatus == 0}">
                                                    <a id="passenger_tableButtonRemove${pStatus.count}" href="#" class="confirm-delete" data-id="${passenger.id}">
                                                        <span id="passenger_tableSpanRemove${pStatus.count}" class="glyphicon glyphicon-remove deleteicon"></span>
                                                    </a> 
                                                </c:if>
                                                <c:if test="${isBillStatus == 1}">
                                                    <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${lockUnlockBooking == 1}">
                                                <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                            </c:if>                                                
                                       <%--</c:if>--%>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <c:forEach var="passenger" items="${passengers}" varStatus="pStatus">
                    <div class="collapse" id="passenger${pStatus.count}">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Passenger Details</h3>
                                <input type="hidden" value="${pStatus.count}" name="passengerNo${pStatus.count}" class="form-control">
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-sm-1 text-right"><strong>Name</strong><strong style="color: red">*</strong></div>
                                    <div class="col-sm-2">
                                        <input type="hidden" value="${passenger.MInitialname.name}" class="form-control" id="passengerIntialnameHide${pStatus.count}">
                                        <select class="form-control" name="passengerIntialname${pStatus.count}" id="passengerIntialname${pStatus.count}">
                                            <c:forEach var="i" items="${InitialName}">
                                                <option value="${i.name}">${i.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-3 form-group">
                                        <input type="text" name="passengerlastname${pStatus.count}" id="passengerlastname${pStatus.count}" 
                                               value="${passenger.lastName}" class="form-control" maxlength="50" 
                                               data-bv-notempty data-bv-notempty-message="The Last Name is required"/>
                                    </div>
                                    <div class="col-sm-3 form-group">
                                        <input type="hidden" name="passengerId${pStatus.count}" id="passengerId${pStatus.count}" value="${passenger.id}" class="form-control">
                                        <input type="text" name="passengerfirstname${pStatus.count}" id="passengerfirstname${pStatus.count}" 
                                               value="${passenger.firstName}" class="form-control" maxlength="50" 
                                               data-bv-notempty data-bv-notempty-message="The First Name is required"/>
                                    </div>
                                    <div class="col-sm-1 text-right"><strong>PAX&nbsp;TYPE</strong><strong style="color: red">*</strong></div>
                                    <div class="col-sm-2 form-group"><!--winit-->
                                        <input type="hidden" value="${passenger.MPricecategory.code}" class="form-control" name="passengerCategoryHide${pStatus.count}" id="passengerCategoryHide${pStatus.count}">
                                        <select name="passengerCategory${pStatus.count}" id="passengerCategory${pStatus.count}" class="form-control"
                                                data-bv-notempty data-bv-notempty-message="The Category is required"
                                                data-bv-callback="true"
                                                data-bv-callback-message="category is null"
                                                data-bv-callback-callback="checkCateory">
                                            <c:forEach var="category" items="${mPricecategorysList}">
                                                <option value="${category.code}">${category.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-1 text-right"><strong>Ticket</strong><strong style="color: red">*</strong></div>
                                    <div class="col-sm-5" style="float:left; display:inline-block">
                                        <input name="passengerSeriesOne${pStatus.count}" id="passengerSeriesOne${pStatus.count}" 
                                               value="${passenger.series1}" class="form-control" style="width:10%; display: inline;" maxlength="3" 
                                               data-bv-notempty data-bv-notempty-message="The Ticket is required"/>
                                        <input name="passengerSeriesTwo${pStatus.count}" id="passengerSeriesTwo${pStatus.count}" 
                                               value="${passenger.series2}" class="form-control" style="width:25%; display: inline" maxlength="10" 
                                               data-bv-notempty data-bv-notempty-message="The Ticket is required"/>
                                        <input name="passengerSeriesThree${pStatus.count}" id="passengerThree${pStatus.count}" 
                                               value="${passenger.series3}" class="form-control" style="width:10%; display: inline;" maxlength="3" 
                                               data-bv-notempty data-bv-notempty-message="The Ticket is required"/>
                                    </div>
                                    <div class="col-sm-1 text-right"><strong>Airline</strong><strong style="color: red">*</strong></div>
                                    <div class="col-sm-2 form-group">
                                        <div class="">
                                            <div class="input-group">
                                                <input type="hidden"  class="form-control" id="passengerAirlineId${pStatus.count}" 
                                                       name="passengerAirlineId${pStatus.count}" value="${passenger.airticketAirline.MAirline.id}">
                                                <input name="passengerAirlineCode${pStatus.count}" id="passengerAirlineCode${pStatus.count}" 
                                                       data-id="${pStatus.count}"  class="form-control air-flight" 
                                                       value="${passenger.airticketAirline.MAirline.code}" maxlength="5" 
                                                       data-bv-notempty data-bv-ntempty-message="The airline is required"
                                                       data-bv-callback="true"
                                                       data-bv-callback-message="airline conflicts"
                                                       data-bv-callback-callback="checkPassengerAirline" />
                                                <span class="input-group-addon" data-toggle="modal" data-target="#FlightModal" data-id="${pStatus.count}">
                                                    <span class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <input name="passengerAirlineName${pStatus.count}" id="passengerAirlineName${pStatus.count}" 
                                                       class="form-control" readonly="" value="${passenger.airticketAirline.MAirline.name}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-sm-1 text-right"><strong>Fare</strong></div>
                                    <div class="col-sm-2">
                                        <input name="passengerFare${pStatus.count}" id="passengerFare${pStatus.count}" 
                                               value="${passenger.ticketFare}" class="form-control text-right decimal" maxlength="11" 
                                               />
                                    </div>
                                    <div class="col-sm-1 text-right"><strong>Tax</strong></div>
                                    <div class="col-sm-2"><input name="passengerTax${pStatus.count}" id="passengerTax${pStatus.count}" 
                                                                 value="${passenger.ticketTax}" class="form-control text-right decimal" maxlength="11" 
                                                                 />
                                    </div>
                                    <div class="col-sm-1 text-right"><strong>Type</strong><strong style="color: red">*</strong></div>
                                    <div class="col-sm-2">
                                        <input class="form-control" name="passengerTicketTypeHide${pStatus.count}" id="passengerTicketTypeHide${pStatus.count}" value="${passenger.ticketType}" type="hidden">
                                        <select class="form-control" name="passengerTicketType${pStatus.count}" id="passengerTicketType${pStatus.count}"
                                                data-bv-notempty data-bv-notempty-message="The Type is required">
                                            <option value="I">Inter</option>
                                            <option value="D">Domestic</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-1 text-right"><strong>From</strong><strong style="color: red">*</strong></div>
                                    <div class="col-sm-2">
                                        <input class="form-control" name="passengerFromHide${pStatus.count}" id="passengerFromHide${pStatus.count}" value="${passenger.ticketFrom}" type="hidden">
                                        <select class="form-control" name="passengerFrom${pStatus.count}" id="passengerFrom${pStatus.count}"
                                                data-bv-notempty data-bv-notempty-message="The From is required">
                                            <option value="C">In</option>
                                            <option value="O">Out</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script>

                        // ### SET DEFAULT VALUE SELECT OPTION PASSENGER ### //
                        $("#passengerIntialname${pStatus.count}").val($("#passengerIntialnameHide${pStatus.count}").val());         // SET INTIALNAME
                        $("#passengerCategory${pStatus.count}").val($("#passengerCategoryHide${pStatus.count}").val());         // SET INTIALNAME
                        if ($("#passengerCategoryHide${pStatus.count}").val() == '') {
                            $("#passengerCategory${pStatus.count}").val("NON");
                        }
                        $("#passengerTicketType${pStatus.count}").val($("#passengerTicketTypeHide${pStatus.count}").val());                     // SET TICKET TYPE
                        $("#passengerFrom${pStatus.count}").val($("#passengerFromHide${pStatus.count}").val());                     // SET TICKET FROM
                    </script>
                </c:forEach>
                <div class="row hidden">
                    <div class="col-sm-2">
                        <select class="form-control"  id="passengerIntialnameList">
                            <c:forEach var="i" items="${mInitialname}">
                                <option value="${i.name}">${i.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-2">
                        <select  id="passengerCategoryList" class="form-control">
                            <c:forEach var="category" items="${mPricecategorysList}">
                                <option value="${category.code}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div id="divAddPassenger"></div>

                <div class="text-center">
                    <input name="action" id="action" value="${action}" type="hidden"/>
                    <input name="enableSave" id="enableSave" value="${enableSave}" type="hidden"/>
                    <c:choose>
                        <c:when test="${currentPnr.MItemstatus.id == 2}">
                            <button type="button" disabled id="saveDetail" class="btn btn-success"><span class="fa fa-save"></span> Save</button>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${lockUnlockBooking == 0}">
                                <c:if test="${isBillStatus == 0}">
                                    <button id="ButtonSave" class="btn btn-success" type="submit"><span class="fa fa-save"></span> Save</button>
                                </c:if>
                                <c:if test="${isBillStatus == 1}">
                                    <c:choose>
                                        <c:when test="${enableSave == 0}">
                                            <button id="ButtonSave" class="btn btn-success" type="submit"><span class="fa fa-save"></span> Save</button>
                                        </c:when>
                                        <c:when test="${enableSave == 1}">
                                            <button class="btn btn-success disabled" ><span class="fa fa-save"></span> Save</button>
                                        </c:when>
                                    </c:choose> 
                                </c:if>
                            </c:if>
                            <c:if test="${lockUnlockBooking == 1}">
                                <button class="btn btn-success disabled" ><span class="fa fa-save"></span> Save</button>
                            </c:if> 
                        </c:otherwise>
                    </c:choose>
                </div>

            </form>
        </div>
    </div>
</div>

<!--Modal  Import-->
<div class="modal fade" id="ImportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="AirTicketDetail.smi" method="post" id="Pnrform" class="form-horizontal"  role="form">
            <input type="hidden" id="actionIUP" name="action" />
            <input type="hidden" id="pnrid" name="pnrid" />
            <input type="hidden" id="pnrname" name="pnrname" />
            <input type="hidden" id="referenceNo" name="referenceNo" value="${param.referenceNo}" />
            <input type="hidden" id="pnrIdTemp" name="pnrIdTemp" value="${requestScope['pnrIdTemp']}">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Import</h4>
                </div>
                <div class="modal-body">
                    <!--IMport List Table-->
                    <table class="display" id="PnrTable">
                        <thead>                        
                            <tr class="datatable-header">
                                <th class="hidden">ID</th>
                                <th>Pnr</th>
                            </tr>
                        </thead>
                        <tbody>
                        <script>
                            pnr = [];
                        </script>
                        <c:forEach var="bookingPnr" items="${listBookingPnrs}">
                            <tr>
                                <td class="pnr-id hidden">${bookingPnr.id}</td>
                                <td class="pnr-name">${bookingPnr.pnr}</td>
                            </tr>
                            <script>
                                pnr.push({id: "${bookingPnr.id}", pnr: "${bookingPnr.pnr}"});
                            </script>
                        </c:forEach>

                        </tbody>

                    </table>
                    <!--Script PNR List Table-->
                    <script>

                        $(document).ready(function () {
                            $("#PnrTable td").on('click', function () {
                                pnr_id = $(this).parent().find(".pnr-id").text();
                                pnr_name = $(this).parent().find(".pnr-name").text();
                                $("#pnrid").val(pnr_id);
                                $("#pnrname").val(pnr_name);
                                $("#actionIUP").val('import');
                                
                                
                                var pnr_check = document.getElementById("checkPnr").value
                                var checkRe = pnr_check.indexOf($("#pnrname").val());
                                if(checkRe === -1){                                                             
                                    if(($("#pnrid").val()!="") && ($("#pnrname").val()!="")){
                                        alert("select pnr id[" + $("#pnrid").val() + "] name[" + $("#pnrname").val() + "] refNo[" + $("#referenceNo").val() + "]");
                                        $("#Pnrform").submit();
                                        $("#ImportModal").modal('hide');
                                    }
                                } else {
                                    alert('This pnr is already used.');
                                }  
                            });

                            $("#pnr_name").keyup(function (e) {
                                if(${lockUnlockBooking == 0}){
                                    if (e.keyCode === 13) {
                                        /*e.preventDefault();*/
                                        var name = $("#pnr_name").val();
                                        $("#pnrname").val($("#pnr_name").val());
                                        $("#actionIUP").val('import');
                                        var result = $.grep(pnr, function (e) {
                                            return e.pnr === name;
                                        });

                                        var pnr_check = document.getElementById("checkPnr").value
                                        var checkRe = pnr_check.indexOf($("#pnr_name").val());
                                        if(checkRe === -1){ 
                                            if (result.length > 0) {
                                                $("#Pnrform").submit();
                                                alert('import pnr ' + $("#pnr_name").val() + ' !');
                                            } else {
                                                alert('pnr name "' + name + '" doesn\'t exist! ');
                                                $("#pnr_name").val("");
                                            }
                                        } else {
                                            alert('This pnr is already used.');
                                            document.getElementById("pnr_name").value = "";
                                        }                                                                                                                      
                                    }
                                }
                            });

                            // Owner Table
                            var PnrTable = $('#PnrTable').dataTable({bJQueryUI: true,
                                "sPaginationType": "full_numbers",
                                "bAutoWidth": false,
                                "bFilter": true,
                                "bPaginate": true,
                                "bInfo": false,
                                "bLengthChange": false,
                                "iDisplayLength": 10
                            });
                            $('#PnrTable tbody').on('click', 'tr', function () {
                                //$('.collapse').collapse('show');
                                if ($(this).hasClass('row_selected')) {
                                    $(this).removeClass('row_selected');
                                }
                                else {
                                    PnrTable.$('tr.row_selected').removeClass('row_selected');
                                    $(this).addClass('row_selected');
                                }

                            });
                        });
                    </script>

                </div>
                <div class="modal-footer">
                    <div class="text-right">
                        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div>

<!--Modal  Departure-->
<div class="modal fade" id="DepartureModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Departure</h4>
            </div>
            <div class="modal-body">
                <div style="text-align: right"><i id="ajaxloaddep"  class="fa fa-spinner fa-spin hidden"></i>
                    Search : <input placeholder ="CODE/NAME " type="text" style="width: 175px" id="filterdep" name="filterdep"/>
                </div>
                <!--Airport List -->
                <table class="display" id="DepartureTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th>Name</th>

                        </tr>
                    </thead>
                    <tbody>
                    <script>
//                        a = [];
                    </script>
                    <c:forEach var="a" items="${airport}">
                        <tr class="departure-tr">
                            <td class="departure-id hidden">${a.id}</td>
                            <td class="departure-code">${a.code}</td>
                            <td class="departure-name">${a.name}</td>
                        </tr>
                        <script>
//                            a.push({id: "${a.id}", code: "${a.code}", name: "${a.name}"});
                        </script>
                    </c:forEach>

                    </tbody>

                </table>
                <!--Script Departure -->
                <script>
                    function get_id(id) {
                        console.log("id : " + id);
                        flight_id = id;
                    }

                    function getDeparture(name) {
                        var servletName = 'AirTicketServlet';
                        var servicesName = 'AJAXBean';
                        var param = 'action=' + 'text' +
                                '&servletName=' + servletName +
                                '&servicesName=' + servicesName +
                                '&name=' + name +
                                '&type=' + 'searchairportDeparture';
                        console.log("Ajax param [" + param + "]");
                        CallAjaxDeparture(param);
                    }
                    function CallAjaxDeparture(param) {
                        var url = 'AJAXServlet';
                        $("#ajaxloaddep").removeClass("hidden");
                        try {
                            $.ajax({
                                type: "POST",
                                url: url,
                                cache: false,
                                data: param,
                                success: function (msg) {
                                    $('#DepartureTable').dataTable().fnClearTable();
                                    $('#DepartureTable').dataTable().fnDestroy();
                                    $("#DepartureTable tbody").empty().append(msg);
                                    $('#DepartureTable').dataTable({bJQueryUI: true,
                                        "sPaginationType": "full_numbers",
                                        "bAutoWidth": false,
                                        "bFilter": false,
                                        "bPaginate": true,
                                        "bInfo": false,
                                        "bLengthChange": false,
                                        "iDisplayLength": 10
                                    });
                                    $("#ajaxloaddep").addClass("hidden");
                                }, error: function (msg) {
                                    $("#ajaxloaddep").addClass("hidden");
                                }
                            });
                        } catch (e) {
                            alert(e);
                        }
                    }

                    $(document).ready(function () {
                        $("#filterdep").keyup(function (event) {
                            if (event.keyCode === 13) {
                                getDeparture($("#filterdep").val());
                            }
                        });

                        $.each(flight, function (index_flight, value_flight) {
                            var flightCode = $("#departure-" + value_flight.id + "-code").val();
                            getDepartureAirport(flightCode, value_flight.id,1);
                        });
//                        $.each(a, function (index, value) {
//                            $.each(flight, function (index_flight, value_flight) {
//                                var flightCode = $("#departure-" + value_flight.id + "-code").val();
//
//                                if (flightCode === value.code) {
//                                    console.log("flightCode : " + flightCode);
//                                    $("#departure-" + value_flight.id + "-name").val(value.name);
//                                }
//                            });
//                        });

                        $("#DepartureTable tbody").on('click', 'tr', function () {
                            departure_id = $(this).find(".departure-id").text();
                            departure_code = $(this).find(".departure-code").text();
                            departure_name = $(this).find(".departure-name").text();
                            $("#departure-" + flight_id.toString() + "-id").val(departure_id);
                            $("#departure-" + flight_id.toString() + "-code").val(departure_code);
                            $("#departure-" + flight_id.toString() + "-name").val(departure_name);
                            $("#departure-" + flight_id.toString() + "-codeVal").val(departure_code);
                            console.log(flight_id.toString());
                            $("#DepartureModal").modal('hide');
                        });

                        // Departure Table
                        var DepartureTable = $('#DepartureTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                        $('#DepartureTable tbody').on('click', 'tr', function () {

                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                            } else {
                                DepartureTable.$('tr.row_selected').removeClass('row_selected');
                                $(this).addClass('row_selected');
                            }

                        });
                    });
                </script>

            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Modal  Arrival-->
<div class="modal fade" id="ArrivalModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Arrival</h4>
            </div>
            <div class="modal-body">
                <div style="text-align: right"><i id="ajaxloadarrive"  class="fa fa-spinner fa-spin hidden"></i>
                    Search : <input placeholder ="CODE/NAME " type="text" style="width: 175px" id="filterarrive" name="filterarrive"/>
                </div>
                <!--Arrival List Table-->
                <table class="display" id="ArrivalTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="a" items="${airport}">
                            <tr>
                                <td class="arrival-id hidden">${a.id}</td>
                                <td class="arrival-code">${a.code}</td>
                                <td class="arrival-name">${a.name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <!--Script-->
                <script>
                    function getArrive(name) {
                        var servletName = 'AirTicketServlet';
                        var servicesName = 'AJAXBean';
                        var param = 'action=' + 'text' +
                                '&servletName=' + servletName +
                                '&servicesName=' + servicesName +
                                '&name=' + name +
                                '&type=' + 'searchairportArrive';
                        console.log("Ajax param [" + param + "]");
                        CallAjaxArrive(param);
                    }
                    function CallAjaxArrive(param) {
                        var url = 'AJAXServlet';
                        $("#ajaxloadarrive").removeClass("hidden");
                        try {
                            $.ajax({
                                type: "POST",
                                url: url,
                                cache: false,
                                data: param,
                                success: function (msg) {
                                    $('#ArrivalTable').dataTable().fnClearTable();
                                    $('#ArrivalTable').dataTable().fnDestroy();
                                    $("#ArrivalTable tbody").empty().append(msg);
                                    $('#ArrivalTable').dataTable({bJQueryUI: true,
                                        "sPaginationType": "full_numbers",
                                        "bAutoWidth": false,
                                        "bFilter": false,
                                        "bPaginate": true,
                                        "bInfo": false,
                                        "bLengthChange": false,
                                        "iDisplayLength": 10
                                    });
                                    $("#ajaxloadarrive").addClass("hidden");
                                }, error: function (msg) {
                                    $("#ajaxloadarrive").addClass("hidden");
                                }
                            });
                        } catch (e) {
                            alert(e);
                        }
                    }

                    $(document).ready(function () {
                        $("#filterarrive").keyup(function (event) {
                            if (event.keyCode === 13) {
                                getArrive($("#filterarrive").val());
                            }
                        });
                        
                        $.each(flight, function (index_flight, value_flight) {
                            var flightCode = $("#arrival-" + value_flight.id + "-code").val();
                            getArrivalAirport(flightCode, value_flight.id,1);
                        });
//                        $.each(a, function (index, value) {
//                            $.each(flight, function (index_flight, value_flight) {
//                                var flightCode = $("#arrival-" + value_flight.id + "-code").val();
//                                if (flightCode === value.code) {
//                                    console.log("flightCode : " + flightCode);
//                                    $("#arrival-" + value_flight.id + "-name").val(value.name);
//                                }
//                            });
//                        });
                        $("#ArrivalTable tbody").on('click', 'tr', function () {
                            arrival_id = $(this).find(".arrival-id").text();
                            arrival_code = $(this).find(".arrival-code").text();
                            arrival_name = $(this).find(".arrival-name").text();
                            /*alert("arrival_id :"+arrival_id+"|arrival_code :"+arrival_code+"|arrival_name :"+arrival_name);*/
                            $("#arrival-" + flight_id.toString() + "-id").val(arrival_id);
                            $("#arrival-" + flight_id.toString() + "-code").val(arrival_code);
                            $("#arrival-" + flight_id.toString() + "-name").val(arrival_name);
                            $("#arrival-" + flight_id.toString() + "-codeVal").val(arrival_code);
                            console.log(flight_id.toString());
                            $("#ArrivalModal").modal('hide');
                        });

                        // ArrivalTable Table
                        var ArrivalTable = $('#ArrivalTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });

                        $('#ArrivalTable tbody').on('click', 'tr', function () {
                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                            } else {
                                ArrivalTable.$('tr.row_selected').removeClass('row_selected');
                                $(this).addClass('row_selected');
                            }

                        });
                    });
                </script>

            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Modal  Airline-->
<div class="modal fade" id="AirlineModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Airline</h4>
            </div>
            <div class="modal-body">
                <!--Airline List Table-->
                <table class="display" id="AirlineTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                            <th class="">Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        airline = [];
                    </script>
                    <c:forEach var="airline" items="${mAirlineList}">
                        <tr>
                            <td class="airline-id hidden">${airline.id}</td>
                            <td class="airline-code">${airline.code}</td>
                            <td class="airline-name">${airline.name}</td>
                        </tr>
                        <script>
                            airline.push({
                                airline_id: "${airline.id}",
                                airline_code: "${airline.code}",
                                airline_name: "${airline.name}"
                            });
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
                <!--Script Airline List Table-->
                <script>
                    p_Id = 0;
                    function get_PId(name, id) {
                        console.log("name : " + name);
                        console.log("id : " + id);
                        if (name == "1") {
                            name = "airline";
                        }
                        if (name == "2") {
                            name = "passengerAirline";
                        }
                        p_name = name;
                        p_Id = id;
                    }
                    $(document).ready(function () {                       
                        // AirlineTable Table
                        var OwnerTable = $('#AirlineTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                        $('#AirlineTable tbody').on('click', 'tr', function () {
                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                            } else {
                                OwnerTable.$('tr.row_selected').removeClass('row_selected');
                                $(this).addClass('row_selected');
                            }

                        });

                        $("#AirlineTable").on('click', 'tr', function () {
                            airline_id = $(this).find(".airline-id").text();
                            airline_code = $(this).find(".airline-code").text();
                            airline_name = $(this).find(".airline-name").text();
                            $("#" + p_name.toString() + "Id" + p_Id.toString()).val(airline_id);
                            $("#" + p_name.toString() + "Code" + p_Id.toString()).val(airline_code);
                            $("#" + p_name.toString() + "Name" + p_Id.toString()).val(airline_name);
                            console.log(airline_id);
                            $("#AirlineModal").modal('hide');
                        });


                    });
                </script>

            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Modal  Airline Flight-->
<div class="modal fade" id="FlightModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Airline</h4>
            </div>
            <div class="modal-body">
                <!--Airline Flight Table-->
                <table class="display" id="FlightTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                            <th class="">Name</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
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

<form role="form" id="disableFlight" method="post" action="AirTicketDetail.smi" class="form-horizontal">
    <input type="hidden" class="form-control" id="disableFlightId"   name="disableFlightId"  >     
    <input type="hidden" class="form-control" id="action"   name="action"  value="disableFlight">     
    <input type="hidden" class="form-control" id="referenceNo"   name="referenceNo"  value="${param.referenceNo}" >     
    <input name="pnr" value="${param.pnr}" type="hidden"/>
</form>

<div class="modal fade" id="DisableFlight" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Disable Flight</h4>
            </div>
            <div class="modal-body" id="disableCode">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="disableFlight()" class="btn btn-danger">Disable</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->  

<div class="modal fade" id="DeletePassengerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Delete Passenger</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button"  class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->  

<form role="form" id="enableFlight" method="post" action="AirTicketDetail.smi" class="form-horizontal">
    <input type="hidden" class="form-control" id="enableFlightId"   name="enableFlightId"  >     
    <input type="hidden" class="form-control" id="action"   name="action"  value="enableFlight">     
    <input type="hidden" class="form-control" id="referenceNo"   name="referenceNo"  value="${param.referenceNo}" >     
    <input name="pnr" value="${param.pnr}" type="hidden"/>
</form>

<div class="modal fade" id="EnableFlight" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Enable Flight</h4>
            </div>
            <div class="modal-body" id="enableCode">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="enableFlight()" class="btn btn-danger">Enable</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal --> 

<div class="modal fade" id="ConfirmAdd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="confirmTitle">Add Flight</h4>
            </div>
            <div class="modal-body" id="confirmBody">Are you sure to add flight ?

            </div>
            <div class="modal-footer">
                <button type="button" onclick="addRowTable()" data-dismiss="modal" class="btn btn-success">Yes</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->  

<div class="modal fade" id="ConfirmAddPassenger" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="confirmTitle">Add Passenger</h4>
            </div>
            <div class="modal-body" id="confirmBody">Are you sure to add Passenger ?

            </div>
            <div class="modal-footer">
                <button type="button" onclick="addRowPassengerTable()" data-dismiss="modal" class="btn btn-success">Yes</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->  
<!--style-->

<c:if test="${! empty param.result}">
    <c:if test="${param.result =='1'}">        
        <script language="javascript">
//            alert("save successful");
            $('#textAlertDivSave').show();
        </script>
        <!--<META HTTP-EQUIV="Refresh" CONTENT="0;URL=AirTicketDetail.smi?referenceNo=${param.referenceNo}&pnr=${param.pnr}&action=edit">-->
    </c:if>
    <c:if test="${param.result =='0'}">        
        <script language="javascript">
//            alert("save unsuccessful");
            $('#textAlertDivNotSave').show();
        </script>
        <!--<META HTTP-EQUIV="Refresh" CONTENT="0;URL=AirTicketDetail.smi?referenceNo=${param.referenceNo}&pnr=${param.pnr}&action=edit">-->
    </c:if>
</c:if>

<style>
    .dataTables_wrapper {
        position: relative;
        min-height: 10px;
        clear: both;
    }
    th {
        font-weight: normal;
    }
</style>
<script type="text/javascript">
    var tabindex = 1; //start tabindex || 150 is last tabindex
    $(document).keypress(function(event) {     
        $('.time').mask('00:00');
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13') { //onEnter
            tabindex++;
            //while element exist or it's readonly and tabindex not reached max do
            while(($("[TabIndex='"+tabindex+"']").length == 0 || $("[TabIndex='"+tabindex+"']:not([readonly])").length == 0) && tabindex != 150 ){
                tabindex++;
            }
            if(tabindex == 150){ tabindex = 1 } //reseting tabindex if finished
            $("[TabIndex='"+tabindex+"']").focus()
            return false;
        }
    });

    $("input").click(function() { //if changing field manualy with click - reset tabindex 
        var input = $(this);
        tabindex = input.attr("tabindex");
    })
    
    function validateTimeDepart(el,row){
        var result;
        // first, check if input is fully correct
        if (el.value.match(/^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/)){
            result = "OK";
            $("#departtimepanel"+row).removeClass("has-error");
            $("#departtimepanel"+row).addClass("has-success");
            $("#ButtonSave").removeClass("disabled");
        }
        // then, check if it is not wrong
        else if (el.value.match(/^([0-9]|0[0-9]|1[0-9]|2[0-3])?(:([0-5]|[0-5][0-9])?)?$/)){
            result=""; // don't bother user with excess messages
            $("#departtimepanel"+row).removeClass("has-error");
            $("#departtimepanel"+row).addClass("has-success");
            $("#ButtonSave").removeClass("disabled");
        }
        else{
            result="Please, correct your input";
            $("#departtimepanel"+row).removeClass("has-success");
            $("#departtimepanel"+row).addClass("has-error");
            $("#ButtonSave").addClass("disabled"); 
        }
    }
    
    function validateTimeArrive(el,row){
        var result;
        // first, check if input is fully correct
        if (el.value.match(/^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/)){
            result = "OK";
            $("#arrivetimepanel"+row).removeClass("has-error");
            $("#arrivetimepanel"+row).addClass("has-success");
            $("#ButtonSave").removeClass("disabled");
        }
        // then, check if it is not wrong
        else if (el.value.match(/^([0-9]|0[0-9]|1[0-9]|2[0-3])?(:([0-5]|[0-5][0-9])?)?$/)){
            result=""; // don't bother user with excess messages
            $("#arrivetimepanel"+row).removeClass("has-error");
            $("#arrivetimepanel"+row).addClass("has-success");
            $("#ButtonSave").removeClass("disabled");
        }
        else{
            result="Please, correct your input";
            $("#arrivetimepanel"+row).removeClass("has-success");
            $("#arrivetimepanel"+row).addClass("has-error");
            $("#ButtonSave").addClass("disabled"); 
        }
    }
    
    $(document).ready(function() {
        $(".decimal").inputmask({
            alias: "decimal",
            integerDigits: 8,
            groupSeparator: ',',
            autoGroup: true,
            digits: 2,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0"
        });
    });
</script>
<input type="hidden" id="checkPnr" name="checkPnr" value="${checkPnr_list}"/>




