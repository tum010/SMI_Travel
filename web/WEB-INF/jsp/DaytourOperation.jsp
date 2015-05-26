<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="master" value="${requestScope['Master']}" />
<input type="hidden" value="${param.referenceNo}" id="getUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">

<section class="content-header" >
    <h1>
        Operation - Day Tours Operation
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Operation</a></li>          
        <li class="active"><a href="#">Day Tours Operation</a></li>
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
            <!--<div ng-include="'WebContent/Book/BookNavbar.html'"></div>-->
            <!--<input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>-->
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Day Tours Operation</b></h4>
                </div>
                <div class="col-sm-6 text-right">
                    <a href="DaytourOperationDetail.smi?action=add" class="btn btn-success"><i class="fa fa-plus"></i> Add</a>
                </div>
            </div>
            <hr/>
            <!-- Daytour Table-->
            <h4>List Package Tour</h4>
            <table class="display" id="HotelTable">
                <thead class="datatable-header">
                    <tr>
                        <th>Date</th>
                        <th>Code</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>22/03/2015</td>
                        <td>0014</td>
                        <td><a href="DaytourOperationDetail.smi?&action=edit">get</a></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>


