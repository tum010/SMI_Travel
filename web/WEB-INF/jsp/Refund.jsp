<%-- 
    Document   : Refund
    Created on : Feb 25, 2015, 11:28:32 AM
    Author     : top
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="js/refund.js"></script>

<c:set var="booking_size" value="${requestScope['BookingSize']}"/>
<c:set var="master" value="${requestScope['Master']}"/>
<c:set var="staff" value="${requestScope['Staff']}"/>
<c:set var="action" value="${requestScope['Action']}"/>
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
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
            <input type="hidden" value="${master.customer.MInitialname.name}" id="initialname_tmp">
            <input type="hidden" value="${master.customer.firstName}" id="firstname_tmp">
            <input type="hidden" value="${master.customer.lastName}" id="lastname_tmp">  
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>       
            <input type="hidden" value="${param.referenceNo}" id="getUrl" >
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
            <form action="Refund.smi" method="post" id="RefundForm" role="form">
                <div class="row" style="padding-left: 15px">  
                    <div class="col-md-6">
                        <h4>Refund Ticket</h4>
                    </div>
                    <div class="col-md-3 text-right">
                        <button type="button" onclick="" class="btn btn-default">
                            <span class="glyphicon glyphicon-print"></span> Print
                        </button>
                    </div>
                    <div class="col-md-1 text-right">
                        <button type="button" onclick="addRefundDetail(1);" class="btn btn-primary" id="buttonAddRefundDetail" name="buttonAddRefundDetail" >
                            <span class="glyphicon glyphicon-plus"></span> Add
                        </button>
                         <input type="hidden" id="countListAdd" name="countListAdd" value="1" >
                    </div>
                    <div class="col-md-2 text-right" style="margin-left: 0px;">
                        <a  href="AirTicket.smi?referenceNo=${param.referenceNo}&action=edit" 
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
                        <!-- Air Table --> 
                        <div class="row-fluid">
                            <table  class="display" id="RefundTable">
                                <thead>
                                    <tr class="datatable-header">
                                        <th>Refund No</th>
                                        <th>Ticket</th>
                                        <th>Section</th>
                                        <th>section refund</th>
                                        <th>Change</th>
                                        <th>Detail</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!--C:if ,C:foreach, c:set >>> counter -->
                                    <tr>
                                        <td>R0012</td>
                                        <td>2172305640387</td>
                                        <td>BKK-HND-GTH-BKK</td>
                                        <td>GTH-BKK</td>
                                        <td>25090</td>
                                        <td>Test</td>
                                        <td class="text-center">
                                            <!--<a class="carousel" data-toggle="collapse" data-parent="#accordion"--> 
<!--                                               data-target="#passenger1" aria-expanded="true" 
                                               aria-controls="collapseExample">-->
                                                <span class="glyphicon glyphicon-edit editicon" id="SpanEdit1" onclick="selectRefundDetail(1)"></span>
                                            <!--</a>-->
                                            <a class="carousel" data-toggle="collapse" data-parent="#accordion" 
                                               data-target="#passenger1" aria-expanded="true" 
                                               aria-controls="collapseExample">
                                                <span class="glyphicon glyphicon-remove deleteicon"></span>
                                            </a>
                                        </td>
                                    </tr>
                                    <!--<input type="hidden" id="countListOther" name="countListOther" value="${counter}" >-->
                                     <input type="hidden" id="countListOther" name="countListOther" value="1" >
                                </tbody>
                            </table>  
                        </div>
                        <hr/>
                        <div class="row hidden" style="margin-top: 20px" id="RefundTicketDetail1" name="RefundTicketDetail1" >
                            <div class="row">
                                <div class="col-sm-6 form-group" style="margin-left: 20px;">
                                     <h4>Refund Ticket Detail</h4>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label for="Owner" class="col-sm-3 control-label text-right">Refund By</label>
                                    <div class="col-lg-3">
                                        <div class="">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" name="staff_id" id="staff_id" value="">
                                                <input type="text" class="form-control" id="staff_username" name="staff_username" value="${rf.username}"
                                                       data-bv-notempty data-bv-notempty-message="The By is required">
                                                <span class="input-group-addon" data-toggle="modal" data-target="#StaffModal">
                                                    <span class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">  
                                        <input type="text" class="form-control" id="staff_name" name="staff_name" value="${rf.name}" readonly="">
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
                                <div class="col-sm-12 form-group text-center">
                                    <table  class="display" id="RefundTable" style="width: 1000px;">
                                        <thead>
                                            <tr class="datatable-header">
                                                <th style="width: 5%" >No</th>
                                                <th>Ticket No</th>
                                                <th>Section</th>
                                                <th>section refund</th>
                                                <th>Change</th>
                                                <th style="width: 5%" >Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>2172305640387</td>
                                                <td>BKK-HND-GTH-BKK</td>
                                                <td>GTH-BKK</td>
                                                <td>25090</td>
                                                <td class="text-center">
                                                    <a class="carousel" data-toggle="collapse" data-parent="#accordion" 
                                                       data-target="#passenger1" aria-expanded="true" 
                                                       aria-controls="collapseExample">
                                                        <span class="glyphicon glyphicon-remove deleteicon"></span>
                                                    </a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-5 form-group text-right">
                                    <button type="submit" class="btn btn-primary"><span class="fa fa-print"></span> Print</button>
                                </div>
                                <div class="col-sm-1 form-group text-right">
                                    <button type="submit" class="btn btn-success"><span class="fa fa-save"></span> Save</button>
                                </div>
                                <div class="col-sm-6 form-group text-left">
                                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-remove deleteicon"></span> Close </button>
                                </div>
                            </div>  
                        </div>
                        <!--Refund Add -->
                        <div class="row hidden" style="margin-top: 20px" id="RefundTicketDetailAdd1" name="RefundTicketDetailAdd1" >
                            <div class="row">
                                <div class="col-sm-6 form-group" style="margin-left: 20px;">
                                     <h4>Refund Ticket Detail</h4>
                                </div>
                                <div class="col-sm-6 form-group">
                                    <label for="Owner" class="col-sm-3 control-label text-right">Refund By</label>
                                    <div class="col-lg-3">
                                        <div class="">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" name="staff_id" id="staff_id" value="">
                                                <input type="text" class="form-control" id="staff_username" name="staff_username" value="${rf.username}"
                                                       data-bv-notempty data-bv-notempty-message="The By is required">
                                                <span class="input-group-addon" data-toggle="modal" data-target="#StaffModal">
                                                    <span class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">  
                                        <input type="text" class="form-control" id="staff_name" name="staff_name" value="${rf.name}" readonly="">
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
                                <div class="col-sm-12 form-group text-center">
                                    <table  class="display" id="RefundTable" style="width: 1000px;">
                                        <thead>
                                            <tr class="datatable-header">
                                                <th style="width: 5%" >No</th>
                                                <th>Ticket No</th>
                                                <th>Section</th>
                                                <th>section refund</th>
                                                <th>Change</th>
                                                <th style="width: 5%" >Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>2172305640387</td>
                                                <td>BKK-HND-GTH-BKK</td>
                                                <td>GTH-BKK</td>
                                                <td>25090</td>
                                                <td class="text-center">
                                                    <a class="carousel" data-toggle="collapse" data-parent="#accordion" 
                                                       data-target="#passenger1" aria-expanded="true" 
                                                       aria-controls="collapseExample">
                                                        <span class="glyphicon glyphicon-remove deleteicon"></span>
                                                    </a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-5 form-group text-right">
                                    <button type="submit" class="btn btn-primary"><span class="fa fa-print"></span> Print</button>
                                </div>
                                <div class="col-sm-1 form-group text-right">
                                    <button type="submit" class="btn btn-success"><span class="fa fa-save"></span> Save</button>
                                </div>
                                <div class="col-sm-6 form-group text-left">
                                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-remove deleteicon"></span> Close </button>
                                </div>
                            </div>  
                        </div>
                    </div>
                </div>
<!--
                Save
                <div class="text-center" style="margin-top: 10px">
                    <button type="submit" class="btn btn-success"><span class="fa fa-save"></span> Save</button>
                </div>-->
            </form>
        </div>
    </div>
</div>

<!--MODAL STAFF-->
<div class="modal fade" id="StaffModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Staff</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="StaffTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th>Name</th>

                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        sf = [];
                    </script>
                    <c:forEach var="sf" items="${staff}">
                        <tr class="departure-tr">
                            <td class="staff-id hidden">${sf.id}</td>
                            <td class="staff-username">${sf.username}</td>
                            <td class="staff-name">${sf.name}</td>
                        </tr>
                        <script>
                            sf.push({id: "${sf.id}", username :"${sf.username}", name: "${sf.name}"});
                        </script>
                    </c:forEach>

                    </tbody>

                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <input type="hidden" name="action" value="${action}">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
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

<!--style-->
<style>
    .dataTables_wrapper {
        position: relative;
        min-height: 100px;
        clear: both;
    }
</style>