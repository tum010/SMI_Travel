<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Land.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="booktype" value="${requestScope['BOOKING_TYPE']}" />
<c:set var="datalist" value="${requestScope['Land_list']}" />
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">

<input type="hidden" value="${requestScope['result']}" id="resultText">
<section class="content-header" >
    <h1>
        Booking - Land
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">Land</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
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
            <div class="row"> 
                <div class="col-md-6 " style="padding-right: 15px">
                    <h4><b>Land</b> </h4>
                </div>

                <div class="row-fluid">

                    <div class="form-actions pull-right" style="padding-right: 20px">
                        <a href="LandDetail.smi?referenceNo=${param.referenceNo}&action=add"><button type="button" id="acs" onclick=""  class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Add</button>  </a>   

                    </div>
                    <c:choose>
                        <c:when test="${booktype == 'i'}">
                            <div class="form-actions pull-right" style="padding-right: 20px">
                                <div class="form-group">
                                    <div class="col-sm-9">
                                        <button type="button" onclick="printVoucher('${param.referenceNo}');" class="btn btn-default">
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
                                            <option value="1"  > Land Voucher</option>
                                            <option value="2"  > Land Voucher Email</option>
                                            <option value="3"  > Land Voucher Email(Agent)</option>
                                        </select>
                                    </div>
                                </div>
                            </div>    
                        </c:when>

                    </c:choose>

                </div> 

            </div>
            <div class="row" style="margin-left: 10px;margin-right: 10px;"> 
                <table id="MasterLand" class="display" cellspacing="0"  >
                    <thead>
                        <tr class="datatable-header">
                            <th>Agent Code</th>
                            <th>Agent name</th>
                                <c:choose>
                                    <c:when test="${booktype == 'i'}">
                                    <th>Ok by</th>
                                    <th>Category</th>
                                    <th>Description</th>
                                    <!--
                                    <th>Cost</th>
                                    <th>Price</th>
                                    <th>Qty</th> -->
                                    <th>Hotel</th>
                                    <th>Total Cost</th>
                                    <th>Total Price</th>
                                    <th>Cur</th> 
                                   <!-- <th>Total</th> -->
                                    </c:when>
                                    <c:when test="${booktype == 'o'}">
                                    <th>Category</th>
                                    <th>Description</th>
                                    <th>Total Cost</th>
                                    <th>Total Price</th>
                                    <th>Cur</th>
                                    <th>Remark </th>
                                    </c:when>
                                </c:choose>

                            <th> Action</th>
                        </tr>

                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${datalist}">
                            <c:set var="colourStatus" value="" />
                            <c:set var="colourStatusFirstrow" value="" />

                            <c:if test="${table.MItemstatus.id == 2}">
                                <c:set var="colourStatus" value="style='background-color: #FFD3D3'" />
                                <c:set var="colourStatusFirstrow" value="background-color: #FFD3D3" />
                                <c:set var="statusicon" value="glyphicon-remove deleteicon" />
                            </c:if>
                            <tr ${colourStatus} id="${table.id}">
                                <td class="tdcenter" ${colourStatus}>${table.agent.code} </td>
                                <td>${table.agent.name} </td>
                                <c:choose>
                                    <c:when test="${booktype == 'i'}">
                                        <td>${table.okBy} </td>
                                        <td>${table.category} </td>
                                        <td>${table.description} </td>
                                        <td>${table.inboundHotel} </td>
                                        <td class='moneyformat tdright'>${(table.inboundCost * table.inboundQty) + (table.inboundChCost * table.inboundChQty)+ (table.inboundInCost * table.inboundInQty)} </td>
                                        <td class='moneyformat tdright'>${(table.inboundPrice * table.inboundQty) + (table.inboundChPrice * table.inboundChQty)+ (table.inboundInPrice * table.inboundInQty)} </td>
                                        <td class="center" >${table.currency} </td>
                                    </c:when>
                                    <c:when test="${booktype == 'o'}">
                                        <td>${table.category} </td>
                                        <td>${table.description} </td>
                                        <td class='moneyformat tdright'> ${(table.outboundAdQty * table.outboundAdCost)+(table.outboundChQty * table.outboundChCost)+(table.outboundInQty * table.outboundInCost)}</td>
                                        <td class='moneyformat tdright'> ${(table.outboundAdQty * table.outboundAdPrice)+(table.outboundChQty * table.outboundChPrice)+(table.outboundInQty * table.outboundInPrice)}</td>
                                        <td class="center">${table.currency} </td>
                                        <td>${table.remark} </td>
                                    </c:when>
                                </c:choose>

                                <td class="tdcenter">
                                    <a href="LandDetail.smi?referenceNo=${param.referenceNo}&landid=${table.id}&action=edit"><span class="glyphicon glyphicon-edit editicon" ></span></a>
                                        <c:if test="${table.MItemstatus.id == 2}">
                                        <span class="glyphicon glyphicon-plus addicon"   onclick="EnableLand('${table.id}', ' ${table.agent.code}');" data-toggle="modal" data-target="#EnableLand" ></span>
                                    </c:if>
                                    <c:if test="${table.MItemstatus.id == 1}">
                                        <span class="glyphicon glyphicon-remove deleteicon"   onclick="DeleteLand('${table.id}', ' ${table.agent.code}');" data-toggle="modal" data-target="#DelLand" ></span>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>


                    </tbody>
                </table>    
            </div>

            <form role="form" id="LandForm" method="post" action="Land.smi" class="form-horizontal">
                <input type="hidden" class="form-control" id="LandID"   name="LandID"  >     
                <input type="hidden" class="form-control" id="action"   name="action"  >     
                <input type="hidden" class="form-control" id="referenceNo"   name="referenceNo"  value="${param.referenceNo}" >     
            </form>          
            <!--
             <div class="row"> 
                <div class="col-md-6 " style="padding-right: 15px">
                    <h4><b>Itinerary</b> </h4>
                </div>
    
                <div class="row-fluid">
                    <div class="form-actions pull-right" style="padding-right: 20px">
                        <button type="button" id="acs" onclick=""  class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>Add</button>    
                    </div>
                </div> 
    
            </div>
            
            <div class="row" style="margin-left: 10px;margin-right: 10px;"> 
                        <table id="MasterItinerary" class="display" cellspacing="0"  >
                            <thead>
                                <tr class="datatable-header">
                                    <th> No</th>
                                    <th> Date</th>
                                    <th> Time</th>
                                    <th> Description</th>
                                </tr>
    
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1 </td>
                                    <td>17/12/2014 </td>
                                    <td>13:50 </td>
                                    <td>LCH at C/R. CEI City S/S (Wat Phra Sing, Wat Phra Kaeo), Golden Triangle. </td>
                                </tr>
                            </tbody>
                        </table>    
               
          
    
            </div>
            
            -->
        </div>  

    </div>



</div>

<div class="modal fade" id="DelLand" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Booking land </h4>
            </div>
            <div class="modal-body" id="delCode">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<div class="modal fade" id="EnableLand" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Booking land </h4>
            </div>
            <div class="modal-body" id="enableCode">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="Enable()" class="btn btn-success">Enable</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      
