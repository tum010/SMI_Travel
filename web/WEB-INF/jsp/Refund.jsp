<%-- 
    Document   : Refund
    Created on : Feb 25, 2015, 11:28:32 AM
    Author     : top
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/refund.js"></script>

<c:set var="booking_size" value="${requestScope['BookingSize']}"/>
<c:set var="master" value="${requestScope['Master']}"/>
<c:set var="staff" value="${requestScope['Staff']}"/>
<c:set var="action" value="${requestScope['Action']}"/>

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
        </div>
        <div class="col-sm-10">
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input type="hidden" value="${param.referenceNo}" id="getUrl" >
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
            <form action="AirTicket.smi" method="post" id="RefundForm" role="form">
                <div class="row" style="padding-left: 15px">  
                    <div class="col-md-6">
                        <h4>Refund Ticket</h4>
                    </div>
                    <div class="col-md-5 text-right">
                        <button type="button" onclick="printTicketOrder('${param.referenceNo}');" class="btn btn-default">
                            <span class="glyphicon glyphicon-print"></span> Print
                        </button>
                    </div>
                    <div class="col-md-1 text-right">
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
                            <table  class="display" id="PassengerTable">
                                <thead>
                                    <tr class="datatable-header">
                                        <th>name</th>
                                        <th colspan="3">Ticket</th>
                                        <th>Routing</th>
                                        <th>section refund</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                        <td>123</td>
                                        <td>32343443</td>
                                        <td>345</td>
                                        <td>BKK-HND-GTH-BKK</td>
                                        <td>GTH-BKK</td>
                                        <td class="text-center">
                                            <a class="carousel" data-toggle="collapse" data-parent="#accordion" 
                                               data-target="#passenger1" aria-expanded="true" 
                                               aria-controls="collapseExample">
                                                <span class="glyphicon glyphicon-edit editicon"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>  
                        </div>
                        <%--<c:forEach var="psg" items="${passenger}" varStatus="status">--%>
                        <div class="collapse" id="passenger1">
                            <table  class="display" id="FlightTable">
                                <thead>
                                    <tr class="datatable-header">
                                        <th>No</th>
                                        <th>Flight</th>
                                        <th>From</th>
                                        <th>To</th>
                                        <th>Refund <a id="ckeckList" href="#">CheckAll</a></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>TG001</td>
                                        <td>BKK</td>
                                        <td>HND</td>
                                        <td class="text-center"><input type="checkbox"></td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>TG002</td>
                                        <td>HND</td>
                                        <td>BKK</td>
                                        <td class="text-center"><input type="checkbox"></td>
                                    </tr>
                                </tbody>
                            </table>  
                            <div class="text-center" style="padding-top: 10px">
                                <a href="" class="btn btn-success" data-toggle="modal" data-target="#RefundModal">
                                    <i class="fa fa-save"></i> Save
                                </a>
                                <a href="" class="btn btn-default" data-toggle="collapse" data-parent="#accordion" 
                                   data-target="#passenger1" aria-expanded="true" aria-controls="collapseExample">
                                    Close
                                </a>
                            </div>
                        </div>
                        <%--</c:forEach>--%>
                        <hr/>
                        <div class="row" style="margin-top: 20px">
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label for="Owner" class="col-sm-3 control-label text-right">By</label>
                                    <div class="col-lg-3">
                                        <div class="">
                                            <div class="input-group ">
                                                <input type="hidden" class="form-control" name="staff_id" id="staff_id" value="${rf.id}">
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
                                    <label  class="col-sm-3 control-label text-right">Date</label>
                                    <div class="col-lg-4">
                                        <div class="form-group">
                                            <div class='input-group date' id='datetimepicker3'>
                                                <input type='text' class="form-control" name="get_deadline" id="deadline" data-date-format="YYYY-MM-DD" value="${booking.deadline}"  placeholder="YYYY-MM-DD"/>
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
                                    <label class="col-sm-3 control-label text-right">Cancel Detail</label>
                                    <div class="col-sm-9">                                      
                                        <div class="form-group">
                                            <input class="form-control"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6 form-group">
                                    <label  class="col-sm-3 control-label text-right">Charge</label>
                                    <div class="col-sm-9">  
                                        <input type="text" class="form-control" value="${booking.reConfirm}" maxlength="255"/>
                                    </div>
                                </div>

                            </div>  
                            <div class="row">
                                <div class="col-sm-6 form-group">
                                    <label class="col-sm-3 control-label text-right">Address</label>
                                    <div class="col-sm-9">                                      
                                        <div class="form-group">
                                            <textarea class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>  
                        </div>
                    </div>
                </div>

                <!--Save-->
                <div class="text-center" style="margin-top: 10px">
                    <button type="submit" class="btn btn-success"><span class="fa fa-save"></span> Save</button>
                </div>
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