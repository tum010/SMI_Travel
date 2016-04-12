<%--
    Document   : Passenger
    Author     : sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/Passenger.js"></script> 
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="passengerList" value="${requestScope['PassengerList']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<c:set var="lockUnlockBooking" value="${requestScope['LockUnlockBooking']}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<input type="hidden" value="${master.departmentNo}" id="departmentNo">

<input type="hidden" value="${requestScope['result']}" id="resultText">
<section class="content-header" >
    <h1>
        Booking - Passenger
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>
        <li class="active"><a href="#">Passenger</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app="">
    <div class="row">
        <!-- side bar -->
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

        <!--Content-->
        <div class="col-sm-10">
            <!--Nav-->
            <div id="bookNavbar" ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
<!--Alert Save -->
<div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Save Success!</strong> 
</div>
<div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Save Unsuccess!</strong> 
</div>
<div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Delete Success!</strong> 
</div>
<div id="textAlertDivNotDelete"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Delete Unsuccess!</strong> 
</div>       
<div id="textAlertDivNotDeleteLeader"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Cannot Delete Family Leader!</strong> 
</div>

            <!--Head-->
            <div class="row" style="padding-left: 15px">  
                <div class="col-md-6">
                    <h4><b>Passenger</b></h4>
                </div>
                <div class="col-md-6 text-right">
                    <c:if test="${lockUnlockBooking == 0}">
                        <a id="ButtonAdd" href="" class="btn btn-success duplicate">
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
            <hr/>
            
            <script type="text/javascript">
                $(document).ready(function(){
                    var keepcode = "";
                    $("#Passenger .codeCustomer").each(function(){
                        keepcode += "||"+$(this).html();
                    });
                    $("#ButtonAdd").prop('href',"PassengerDetail.smi?referenceNo=${param.referenceNo}&existcode="+keepcode+"&action=add");
                });
               
            </script>

            <table class="display" id="Passenger" >
                <thead class="datatable-header">
                    <tr>
                        <th>No</th>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Nationality</th>
                        <th>Action</th>
                    </tr>

                </thead>
                <tbody>
                    <c:forEach var="passenger" items="${passengerList}" varStatus="varPassenger">
                        <tr>
                            <td class="center">${varPassenger.count}</td>
                            <td class="codeCustomer">${passenger.getCustomer().getCode()}</td>
                            <td>${passenger.getCustomer().getLastName()}    
                                ${passenger.getCustomer().getFirstName()}</td>
                    <script>
                        $(document).ready(function () {
                            //getDate();
                            var birthDate = new Date("${passenger.customer.birthDate}");
                            var age = _calculateAge(birthDate);
                            if (isNaN(age)) {
                                age = "-";
                            }
                            console.log(age);
                            //console.log(${passenger.id});
                            var idName = "#age-${passenger.id}";
                            //console.log(idName);
                            $(idName).text(age);
                        });
                    </script>
                    <td id="age-${passenger.id}" class="text-center"></td>
                    <td>${passenger.customer.nationality}</td>

                    <td class="text-center">
                        <a id="ButtonEdit${varPassenger.count}" href="PassengerDetail.smi?referenceNo=${param.referenceNo}&id=${passenger.id}&action=edit">
                            <i id="IEdit${varPassenger.count}" class="glyphicon glyphicon-edit editicon"></i>
                        </a>
                        <c:if test="${lockUnlockBooking == 0}">
                            <a id="ButtonRemove${varPassenger.count}" data-toggle="modal" data-target="#DeletePassenger" onclick="ModalPassneger(${passenger.id},'${passenger.getCustomer().getCode()}')">
                                <i id="IRemove${varPassenger.count}" class="glyphicon glyphicon-remove deleteicon"></i>
                            </a>                        
                        </c:if>
                        <c:if test="${lockUnlockBooking == 1}">
                            <span class="glyphicon glyphicon-remove deleteicon" ></span>
                        </c:if>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


        </div>
    </div>
</div>

<form role="form" id="PassengerForm" method="post" action="Passenger.smi" class="form-horizontal">
    <input type="hidden" class="form-control" id="PassengerID"   name="PassengerID"  >     
    <input type="hidden" class="form-control" id="action"   name="action"  >     
    <input type="hidden" class="form-control" id="referenceNo"   name="referenceNo"  value="${param.referenceNo}" >     
</form> 


<div class="modal fade duplicatemodal" id="DeletePassenger" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Booking Passenger </h4>
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


