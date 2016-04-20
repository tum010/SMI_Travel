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
<script type="text/javascript" src="js/Airticket.js"></script> 
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="pnr" value="${requestScope['AirTicketPnr']}" />
<c:set var="booking" value="${requestScope['AirticketBooking']}" />
<c:set var="issue" value="${requestScope['IssueBy']}" />
<c:set var="owner" value="${requestScope['OwnerBy']}" />
<c:set var="staff" value="${requestScope['Staff_List']}" />
<c:set var="Issue" value="${requestScope['Staff_List']}" />
<c:set var="airticketDescs" value="${requestScope['AirticketDescsList']}" />
<c:set var="master" value="${requestScope['master']}" />
<c:set var="action" value="${requestScope['action']}" />
<c:set var="add_button" value="${requestScope['AddButton']}" />
<c:set var="result" value="${requestScope['Result']}" />
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="lockUnlockBooking" value="${requestScope['LockUnlockBooking']}" />
<c:set var="mCurrency" value="${requestScope['MCurrency']}" />
<c:set var="roleName" value="${requestScope['roleName']}" />
<c:set var="airLocking" value="${requestScope['airLocking']}" />
<c:set var="pnrdata" value="${requestScope['pnrdata']}" />
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<input type="hidden" value="${master.departmentNo}" id="departmentNo">
<input type="hidden" value="${master.id}" id="master-id">
<c:set var="isBillStatus" value="${requestScope['IsBillStatus']}" />

<c:choose>
    <c:when test="${not empty param.referenceNo}">
        <c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
        <c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
        <input type="hidden" value="${refno1}-${refno2}" id="getUrl">
        <input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
    </c:when>
    <c:otherwise>
        <c:set var="refno1" value="${fn:substring(master.referenceNo, 0, 2)} " />
        <c:set var="refno2" value="${fn:substring(master.referenceNo, 2,7)} " />
        <input type="hidden" value="${refno1}- ${refno2}" id="getUrl" >
        <input type="hidden" value="${master.referenceNo}" id="getRealformatUrl">
    </c:otherwise>
</c:choose>


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
            <input type="hidden" value="${master.customer.MInitialname.name}" id="initialname_tmp">
            <input type="hidden" value="${master.customer.firstName}" id="firstname_tmp">
            <input type="hidden" value="${master.customer.lastName}" id="lastname_tmp">  
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
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Unsuccess!</strong> 
            </div>     
            <div id="textAlertSelectPnr"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Please select a PNR to print ticket order!</strong> 
            </div>
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>

            <form action="AirTicket.smi" method="post" id="AirTicket" role="form">
                <input type="hidden" id="airBookingId" name="airBookingId" value="${booking.id}" />
                <c:choose>
                    <c:when test="${not empty param.referenceNo}}">
                        <input type="hidden" value="${param.referenceNo}" id="referenceNo" name="referenceNo"/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" value="${master.referenceNo}" id="referenceNo" name="referenceNo"/>
                    </c:otherwise>
                </c:choose>
                <input type="hidden" id="requestLock" name="requestLock" value="${lockUnlockBooking}"/>
                <input type="hidden" value="${action}" id="action" name="action" />
                <input type="hidden" value="${pnrdata}" id="pnrdata" name="pnrdata" />
                
                <!--Detail Panel-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Air Ticket Detail</h3>
                    </div>
                    <div class="panel-body" style="padding-bottom: 0px;">
                        <div class="row">
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label for="Owner" class="col-sm-3 control-label text-right">Owner</label>
                                    <div class="col-lg-3">
                                        <div class="">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" name="owner_id" id="staff_id" value="${owner.id}">
                                                <input type="text" class="form-control" id="staff_username" name="staff_username" value="${owner.username}"
                                                       data-bv-notempty="true" data-bv-notempty-message="The Owner is required">
                                                <span id="SpanOpenOwnerModal" class="input-group-addon" data-toggle="modal" data-target="#OwnerModal">
                                                    <span id="SpanGlyphiconSearch" class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div><!-- /.col-lg-6 -->

                                    <div class="col-sm-6">  
                                        <input type="text" class="form-control" id="staff_name" name="staff_name" value="${owner.name}" readonly=""
                                               data-bv-notempty="true" data-bv-notempty-message="Owenr empty !">
                                    </div>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label for="Issue" class="col-sm-3 control-label text-right">Issue by</label>
                                    <div class="col-lg-3">
                                        <div class="">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" id="issue_id" name="issue_id">
                                                <input type="text" class="form-control" id="issue_username" name="issue_username" value="${issue.username}"
                                                       data-bv-notempty="true" data-bv-notempty-message="The Issue by is required">
                                                <span id="SpanOpenIssueModal" class="input-group-addon" data-toggle="modal" data-target="#IssueModal">
                                                    <span id="SpanGlyphiconSearch" class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">  
                                        <input type="text" class="form-control" id="issue_name" name="issue_name" value="${issue.name}" readonly=""
                                               data-bv-notempty="true" data-bv-notempty-message="Issue by empty !">
                                    </div>
                                </div>
                            </div>  
                            <div class="row" >
                                <div class="col-sm-6" style="margin-top: -10px;">
                                    <label class="col-sm-3 control-label text-right">Deadline</label>
                                    <div class="col-sm-4">                                      
                                        <div class="form-group">
                                            <div class='input-group date' id='datetimepicker3'>
                                                <fmt:formatDate value="${booking.deadline}" var="deadLine" pattern="dd-MM-yyyy" />
                                                <input type='text' class="form-control" name="get_deadline" id="deadline" 
                                                       data-date-format="DD-MM-YYYY" value="${deadLine}"  placeholder="DD-MM-YYYY"/>
                                                <span id="SpanGroupAddon" class="input-group-addon spandate">
                                                    <span id="SpanGlyphiconCalendar" class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>

                                        </div>
                                    </div>
                                    <!--checkbox-->
                                    <c:set var="groupPax" value=""/>
                                    <c:if test="${booking.groupPax == 1}">
                                        <c:set var="groupPax" value="checked"/>
                                    </c:if>
                                    <div class="col-sm-3">
                                        <div class="control-label">
                                            <input type="hidden" id="pnr-size" value="${pnr.size()}">
                                            <input type="checkbox" id="group-pax" name="group-pax" ${groupPax}> 
                                            <strong class="text-center">Group Pax</strong>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6" style="margin-top: -10px;">
                                    <label for="Adult" class="col-sm-3 control-label text-right">Re-Confirm</label>
                                    <div class="col-sm-9">  
                                        <input type="text" class="form-control" id="reconfirm" name="reconfirm" value="${booking.reConfirm}" maxlength="255"/>
                                    </div>
                                </div>
                            </div>  
                            <div class="row" >
                                <div class="col-sm-6" style="margin-top: -10px;">
                                    <label class="col-sm-3 control-label text-right">Issue Date</label>
                                    <div class="col-sm-4">                                      
                                        <div class="form-group">
                                            <div class='input-group date' id='datetimepicker4'>
                                                <fmt:formatDate value="${booking.issuedate}" var="issDate" pattern="dd-MM-yyyy" />
                                                <input type='text' class="form-control" name="issuedate" id="issuedate" 
                                                       data-date-format="DD-MM-YYYY" value="${issDate}"  placeholder="DD-MM-YYYY"/>
                                                <span id="SpanGroupAddon" class="input-group-addon spandate">
                                                    <span id="SpanGlyphiconCalendar" class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="col-xs-1" style="width: 220px;padding-top:6px;margin-left: -15px " >
                                            <c:set var="checkP" value="" />
                                            <c:if test="${booking.isPickup == 1}">
                                                <c:set var="checkP" value="checked" />
                                            </c:if>
                                            <input type="radio" name="isPickup"  id="isPickupP" value="1" ${checkP}/>&nbsp;Pick Up&nbsp;&nbsp;&nbsp;
                                            <c:set var="checkD" value="" />
                                            <c:if test="${booking.isPickup == 0}">
                                                <c:set var="checkD" value="checked" />
                                            </c:if>  
                                            <input type="radio" name="isPickup"  id="isPickupD" value="0" ${checkD}/>&nbsp;Delivery&nbsp;
                                        </div>   
                                    </div>
                                </div>
                            </div>        
                        </div>
                    </div>
                </div>
                <div class="row" style="padding-left: 15px;">  
                    <div class="col-md-6" style="margin-top: -10px;">
                        <h4><b>PNR List</b></h4>
                    </div>
                    <div class="col-md-5 text-right" style="margin-top: -10px;">
                        <a id="ButtonRefund" href="Refund.smi?referenceNo=${param.referenceNo}&airbookingid=${booking.id}&action=edit" class="btn btn-primary">
                            <span id="SpanRefund" class="glyphicon glyphicon-usd"></span> refund
                        </a>
                        <button id="ButtonPrint" type="button" onclick="printTicketOrder('${param.referenceNo}');" class="btn btn-default">
                            <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print
                        </button>
                    </div>
                    <div class="col-md-1 text-right" style="margin-top: -10px;">
                        <c:if test="${lockUnlockBooking == 0}">
                            <a id="ButtonAdd"  href="AirTicketDetail.smi?referenceNo=${param.referenceNo}&action=add" class="btn btn-success duplicate ${add_button}">
                                <span id="SpanAdd" class="glyphicon glyphicon-plus"></span> Add
                            </a>
                        </c:if>
                        <c:if test="${lockUnlockBooking == 1}">
                            <a class="btn btn-success disabled">
                                <span class="glyphicon glyphicon-plus"></span>Add</button>
                            </a>   
                        </c:if>                       
                    </div>
                </div>
                <!-- Air Table --> 
                <div class="row-fluid">
                    <table  class="display" id="TableAir">
                        <thead>
                            <tr class="datatable-header">
                                <th>No</th>
                                <th>PNR</th>
                                <th>Flight No</th>                               
                                <th>Last Name</th>
                                <th>First Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${pnr}" varStatus="loopCounter">
                                <c:set var="colourStatus" value="" />
                                <c:set var="colourStatusFirstrow" value="" />

                                <%--<c:if test="${p.id == 2}"> Need to be fixed to status of PNR--%>
                                <c:if test="${p.MItemstatus.id == 3}">
                                    <c:set var="colourStatus" value="style='background-color: #FFD3D3'" />
                                    <c:set var="colourStatusFirstrow" value="background-color: #FFD3D3" />
                                </c:if>
                                <tr ${colourStatus} id="${p.id}">                                
                                    <td  ${colourStatus} class="text-center">${loopCounter.count}</td>
                                    <td>${p.pnr}</td>
                                    <td><c:out value="${p.airticketAirlines.toArray()[0].airticketFlights.toArray()[0].flightNo}"></c:out></td>
                                    <td><c:out value="${p.airticketAirlines.toArray()[0].airticketPassengers.toArray()[0].lastName}"></c:out></td>
                                    <td><c:out value="${p.airticketAirlines.toArray()[0].airticketPassengers.toArray()[0].firstName}"></c:out></td>
                                   
                                        <td class="text-center">
                                            <a id="AEdit${loopCounter.count}" href="AirTicketDetail.smi?referenceNo=${param.referenceNo}&pnr=${p.id}&action=edit">
                                            <span id="SpanGlyphiconEdit${loopCounter.count}" class="glyphicon glyphicon-edit editicon"></span>
                                        </a>
                                        <c:if test="${p.MItemstatus.id == 3}">
                                            <span id="SpanGlyphiconAdd${loopCounter.count}" class="glyphicon glyphicon-plus addicon"   onclick="setEnablePnrForm('${p.id}', ' ${p.pnr}');" data-toggle="modal" data-target="#EnablePnr" ></span>
                                        </c:if>
                                        <c:if test="${p.MItemstatus.id == 1}">
                                            <c:if test="${lockUnlockBooking == 0}">
                                                <c:if test="${isBillStatus == 0}">
                                                    <span id="SpanGlyphiconRemove${loopCounter.count}" class="glyphicon glyphicon-remove deleteicon" onclick="setDisablePnrForm('${p.id}', '${p.pnr}');" data-toggle="modal" data-target="#DisablePnr" ></span>
                                                </c:if>
                                                <c:if test="${isBillStatus == 1}">
                                                    <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${lockUnlockBooking == 1}">
                                                <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>  
                </div>
                <!--Detail Panel-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Additional</h3>
                    </div>
                    <div class="panel-body">
                        <!--Detail Table-->
                        <table  class="display table-striped table-responsive " id="DetailTable">
                            <thead>
                                <tr class="datatable-header">
                                    <th class="hidden">id</th>
                                    <th>Detail</th>
                                    <th>Qty</th>
                                    <th>Cost</th>
                                    <th>Cur</th>
                                    <th>Amount</th>
                                    <th>Cur</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <input type="text" class="hidden" id="counter" name="counter" value="1" />
                            <c:forEach var="detail" items="${airticketDescs}" varStatus="airdesc">
                                <tr>
                                    <td class="hidden"><input type="text" class="hidden" id="row-${airdesc.count}-id" name="row-${airdesc.count}-id" value="${detail.id}" /></td>
                                    <td><input  type="text" class="form-control" id="row-${airdesc.count}-detail" name="row-${airdesc.count}-detail" value="${detail.detail}" maxlength="100"/></td>
                                    <td><input  type="text" class="form-control money" id="row-${airdesc.count}-qty" name="row-${airdesc.count}-qty" value="${detail.qty}" maxlength="11"/></td>
                                    <td><input  type="text" class="form-control money" id="row-${airdesc.count}-cost" name="row-${airdesc.count}-cost" value="${detail.cost}" maxlength="11"/></td>
                                    <td>
                                        <select id="row-${airdesc.count}-currencycost" name="row-${airdesc.count}-currencycost" class="form-control">
                                            <option id="" value="">---------</option>
                                            <c:forEach var="price" items="${mCurrency}" >
                                                <c:set var="select1" value="" />
                                                <c:if test="${detail.curCost == price.code}">
                                                    <c:set var="select1" value="selected" />
                                                </c:if>
                                                <option value="<c:out value="${price.code}" />" ${select1}><c:out value="${price.code}" /></option>   
                                            </c:forEach>
                                        </select>                                       
                                    </td>
                                    <td><input  type="text" class="form-control money" id="row-${airdesc.count}-amount" name="row-${airdesc.count}-amount" value="${detail.amount}" maxlength="11"/></td>
                                    <td>
                                        <select id="row-${airdesc.count}-currency" name="row-${airdesc.count}-currency" class="form-control">
                                            <option id="" value="">---------</option>
                                            <c:forEach var="price" items="${mCurrency}" >
                                                <c:set var="select1" value="" />
                                                <c:if test="${detail.curAmount == price.code}">
                                                    <c:set var="select1" value="selected" />
                                                </c:if>
                                                <option value="<c:out value="${price.code}" />" ${select1}><c:out value="${price.code}" /></option>   
                                            </c:forEach>
                                        </select>                                       
                                    </td>
                                    <td class="text-center">
                                        <c:if test="${lockUnlockBooking == 0}">
                                            <c:if test="${isBillStatus == 0}">
                                                <a id="ButtonRemove${airdesc.count}" class="remCF" onclick="deleteDesc(${param.referenceNo},${detail.id})">
                                                    <span id="SpanRemove${airdesc.count}"  class="glyphicon glyphicon-remove deleteicon"></span>
                                                </a>    
                                            </c:if>
                                            <c:if test="${isBillStatus == 1}">
                                                <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${lockUnlockBooking == 1}">
                                            <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                        </c:if>
                                    </td>
                                    <c:if test="${airdesc.last}">
                                    <script>
                                        $("#counter").val(parseInt("${airdesc.count}") + 1);
                                    </script>
                                    <input value="${airdesc.count}" id="airDescRows" name="airDescRows" type="text" class="hidden" />
                                </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table> 
                        <div id="tr_FormulaAddRow" class="text-center hide" style="padding-top: 10px">
                            <a id="ButtonAdd" class="btn btn-success" onclick="AddRow()">
                                <i id="IGlyphiconPluse" class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                    </div>
                </div> 

                <!-- Remark Panel -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Remark</h3>
                    </div>
                    <div class="panel-body">
                        <textarea class="form-control" rows="4" id="remark" name="remark" maxlength="100">${booking.remark}</textarea>

                    </div>
                </div>
                <!--Save-->
                <div class="text-center" style="margin-top: 10px">
                    <c:set var="airUnlock" value="disabled"/>
                    <c:if test="${(roleName == 'YES') && (airLocking == '1')}">
                        <c:set var="airUnlock" value=""/>
                    </c:if>
                    <button type="button" class="btn btn-primary" onclick="airUnlock();" data-toggle="modal" data-target="#AirUnlock" id="ButtonAirUnlock" name="ButtonAirUnlock" ${airUnlock}>
                        <span id="SpanAirUnlock" class="glyphicon glyphicon-ok" ></span> Booking Air Unlock
                    </button>
                    <input type="hidden" id="flagAir" name="flagAir" value="${booking.master.flagAir}"/>
                    <input type="hidden" id="mBookingStatus" name="mBookingStatus" value="${booking.master.MBookingstatus.id}"/>
                    <c:if test="${lockUnlockBooking == 0}">
                        <c:if test="${isBillStatus == 0}">
                            <button id="ButtonSave" type="submit" class="btn btn-success"><span class="fa fa-save"></span> Save</button>
                        </c:if>
                        <c:if test="${isBillStatus == 1}">
                            <button class="btn btn-success disabled" ><span class="fa fa-save"></span> Save</button>
                        </c:if>
                    </c:if>
                    <c:if test="${lockUnlockBooking == 1}">
                        <button class="btn btn-success disabled" ><span class="fa fa-save"></span> Save</button>
                    </c:if>                      
                </div>
            </form>
        </div>
    </div>
</div>

<!--Modal  Owner-->
<div class="modal fade" id="OwnerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Owner</h4>
            </div>
            <div class="modal-body">
                <!--Owner List Table-->
                <table class="display" id="OwnerTable">
                    <thead>                        
                    <script>
                        staff = [];
                    </script>
                    <tr class="datatable-header">
                        <th class="hidden">ID</th>
                        <th>User</th>
                        <th>Name</th>
                        <th>Position</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="staff" items="${staff}">
                            <tr>
                                <td class="staff-id hidden">${staff.id}</td>
                                <td class="staff-username">${staff.username}</td>
                                <td class="staff-name">${staff.name}</td>
                                <td class="staff-position">${staff.position}</td>
                            </tr>
                        <script>
                            staff.push({id: "${staff.id}", username: "${staff.username}", name: "${staff.name}", position: "${staff.position}"});
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

<!--Modal  Issue-->
<div class="modal fade" id="IssueModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Issue</h4>
            </div>
            <div class="modal-body">
                <!--Issue List Table-->
                <table class="display" id="IssueTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                            <th>Position</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="issue" items="${Issue}">
                            <tr>
                                <td class="issue-id hidden">${issue.id}</td>
                                <td class="issue-username">${issue.username}</td>
                                <td class="issue-name">${issue.name}</td>
                                <td class="issue-position">${issue.position}</td>
                            </tr>
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

<!--Booking Air Unlock Modal-->
<div class="modal fade duplicatemodal" id="AirUnlockModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Booking - Air Ticket</h4>
            </div>
            <div class="modal-body" id="enableVoid">
                Are you to unlock booking air ticket?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" data-dismiss="modal" onclick='unlockAirTicket()'>Unlock</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->                                             

<form role="form" id="disablePnr" method="post" action="AirTicket.smi" class="form-horizontal">
    <input type="hidden" class="form-control" id="disablePnrId"   name="disablePnrId"  >     
    <input type="hidden" class="form-control" id="action"   name="action"  value="disablePnr">     
    <input type="hidden" class="form-control" id="referenceNo"   name="referenceNo"  value="${param.referenceNo}" >     
</form>

<div class="modal fade duplicatemodal" id="DisablePnr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Air Ticket </h4>
            </div>
            <div class="modal-body" id="disableCode">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="disablePnr()" class="btn btn-danger">Disable</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->   

<form role="form" id="enablePnr" method="post" action="AirTicket.smi" class="form-horizontal">
    <input type="hidden" class="form-control" id="enablePnrId"   name="enablePnrId"  >     
    <input type="hidden" class="form-control" id="action"   name="action"  value="enablePnr">     
    <input type="hidden" class="form-control" id="referenceNo"   name="referenceNo"  value="${param.referenceNo}" >     
</form>

<select id="select-currency" name="select-currency" class="form-control hidden">
    <c:forEach var="price" items="${mCurrency}" >
        <c:set var="select1" value="" />
        <c:if test="${paymentWendyList.currency == price.code}">
            <c:set var="select1" value="selected" />
        </c:if>
        <option value="<c:out value="${price.code}" />" ${select1}><c:out value="${price.code}" /></option>   
    </c:forEach>
</select>

<div class="modal fade duplicatemodal" id="EnablePnr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Air Ticket </h4>
            </div>
            <div class="modal-body" id="enableCode">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="enablePnr()" class="btn btn-danger">Enable</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->    

<c:if test="${! empty param.result}">
    <c:if test="${param.result =='1'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
        <!--<META HTTP-EQUIV="Refresh" CONTENT="0;URL=AirTicket.smi?referenceNo=${param.referenceNo}&action=edit">-->
    </c:if>
    <c:if test="${param.result =='0'}">        
        <script language="javascript">
            $('#textAlertDivNotSave').show();
        </script>
        <!--<META HTTP-EQUIV="Refresh" CONTENT="0;URL=AirTicket.smi?referenceNo=${param.referenceNo}&action=edit">-->
    </c:if>
</c:if>

<!--style-->
<style>
    .dataTables_wrapper {
        position: relative;
        min-height: 30px;
        clear: both;
    }
</style>
