<%-- 
    Document   : PassengerDetail
    Created on : Dec 27, 2014, 12:09:47 PM
    Author     : sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="passenger" value="${requestScope['PassengerList']}" />
<c:set var="action" value="${requestScope['action']}" />
<c:set var="initialList" value="${requestScope['initialList']}" />
<c:set var="customerList" value="${requestScope['customerList']}" />
<script type="text/javascript" src="js/PassengerDetail.js"></script> 
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">

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
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>

            <div class="row" style="padding-left: 15px">  
                <div class="col-md-6">
                    <h4>Passenger</h4>
                </div>
                <div class="col-md-6 text-right">
                    <a id="ButtonBack" href="Passenger.smi?referenceNo=${param.referenceNo}&action=edit" class="btn btn-primary">
                        <span id="SpanBack" class="glyphicon glyphicon-arrow-left"></span> Back</a>
                </div>
            </div>
            <hr/>
            <!--form-->
            <form id="PassengerForm" action="PassengerDetail.smi" method="post">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row-fluid"> 
                            <div class="padding5">
                                <div class="form-group">
                                    <input type="hidden" class="form-control" id="customerId" name="customerId" />
                                    <label for="Order" class="col-sm-2 text-right">Order</label>
                                    <div class="col-sm-3">
                                        <input  value="${passenger.orderNo}" type="text" class="form-control" id="Order" placeholder="Order" name="orderNo" readonly="" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-3 text-right">Profile Code</label>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <div class="input-group" id="PassengerInput">
                                                <input  value="${passenger.getCustomer().getCode()}" type="text" class="form-control" id="passengerId" name="code"/>
                                                <span id="SpanGroupAddon" class="input-group-addon"   data-toggle="modal" data-target="#CustomerModal"><span class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="padding5">
                                <div class="form-group">
                                    <label for="" class="col-sm-2 text-right">Name <strong style="color: red">*</strong></label>
                                    <div class="col-sm-1">
                                        <select class="form-control" name="MInitialname" id="MInitialname">
                                            <c:forEach var="initial" items="${initialList}">
                                                <option value="${initial.id}"><c:out value="${initial.name}" /></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <input  value="${passenger.getCustomer().getFirstName()}" type="text" maxlength="50" class="form-control" id="firstName" placeholder="Last name" name="firstName">
                                    </div>
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <input  value="${passenger.getCustomer().getLastName()}" type="text" maxlength="50" class="form-control" id="lastName" name="lastName" placeholder="First Name">
                                    </div>
                                </div>
                            </div>
                            <div class="padding5">
                                <div class="form-group">
                                    <label for="" class="col-sm-2 text-right">Japanese Name</label>
                                    <div class="col-sm-1">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <input value="${passenger.customer.firstNameJapan}" type="text" maxlength="50" class="form-control" id="firstNameJapan" placeholder="Japanese last Name" name="firstNameJapan" />
                                    </div>
                                </div>
                                <div class="col-sm-2"></div>
                                <div class="form-group">
                                    <div class="col-sm-4">
                                        <input value="${passenger.customer.lastNameJapan}" type="text" maxlength="50" class="form-control" id="lastNameJapan" name="lastNameJapan" placeholder="Japanese Name" />
                                    </div>
                                </div>
                            </div>
                            <div class="padding5">
                                <div class="form-group">
                                    <label for="" class="col-sm-2 text-right">Birth</label>
                                    <div class="col-sm-4">
                                        <div class="">
                                            <div class='input-group date'>
                                                <input id="birthDate" name="birthDate"  value="${passenger.getCustomer().getBirthDate()}" type='text' class="form-control" data-date-format="YYYY-MM-DD"  placeholder="YYYY-MM-DD"/>
                                                <span id="SpanGroupCalendar" class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 text-right">Age</label>
                                    <div class="col-sm-4">
                                        <input  class="form-control" id="age" name="age" placeholder="Age" readonly="">
                                    </div>
                                </div>
                            </div>
                            <div class="padding5">
                                <div class="form-group">
                                    <label for="" class="col-sm-2 text-right">Personal ID</label>
                                    <div class="col-sm-4">
                                        <input  value="${passenger.customer.personalId}" type="text" maxlength="13" class="form-control" id="personalId" name="personalId" placeholder="ID No">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="" class="col-sm-2 text-right">Passport</label>
                                    <div class="col-sm-4">
                                        <input  value="${passenger.getCustomer().getPassportNo()}" type="text" maxlength="30" class="form-control" id="Passport" name="Passport" placeholder="Passport">
                                    </div>
                                </div>
                            </div>
                            <div class="padding5">
                                <div class="form-group">
                                    <label for="" class="col-sm-2 text-right">Nationality</label>
                                    <div class="col-sm-4">
                                        <input  value="${passenger.customer.nationality}" type="text" maxlength="30" class="form-control" id="Nationnality" name="Nationality" placeholder="Nationality" />
                                    </div>
                                    <label for="" class="col-sm-2 text-right">Sex</label>
                                    <div class="col-sm-4">
                                        <!--<input  value="${passenger.customer.sex}" type="text" class="form-control" id="sex" name="sex" placeholder="Sex" />-->
                                        <input type="radio" name="sex" value="m" checked=""> Man 
                                        <input type="radio" name="sex" value="w"> Woman
                                    </div>
                                    <script>
                                        $(document).ready(function () {
                                            $("input[name=sex][value=${passenger.customer.sex}]").prop('checked', true);
                                        });
                                    </script>

                                </div>
                            </div>
                            <div class="padding5">
                                <div class="form-group">
                                    <label for="" class="col-sm-2 text-right">Address</label> 
                                    <div class="col-sm-4">
                                        <input  value="${passenger.getCustomer().getAddress()}" type="text" maxlength="100" class="form-control" id="address" name="address" placeholder="Address">
                                    </div>
                                    <label for="postalCode" class="col-sm-2 text-right">Postal Code</label> 
                                    <div class="col-sm-2">
                                        <input  value="${passenger.getCustomer().postalCode}" type="text" maxlength="10" class="form-control" id="postalCode" name="postalCode" placeholder="Postal Code">
                                    </div>
                                </div>
                            </div>
                            <div class="padding5">
                                <div class="form-group">
                                    <label for="" class="col-sm-2 text-right">Tel</label>
                                    <div class="col-sm-4">
                                        <input  value="${passenger.getCustomer().getTel()}" type="text" maxlength="20" class="form-control" id="tel" name="tel" placeholder="Tel"
                                                data-bv-regexp="true" data-bv-regexp-regexp="^[0-9()+,/#\-]+$"  data-bv-regexp-message="The tel is not valid" />
                                    </div>
                                </div>
                                <label for="" class="col-sm-2 text-right">Phone</label>
                                <div class="col-sm-4 form-group">
                                    <input  value="${passenger.getCustomer().getPhone()}" type="text" maxlength="20" class="form-control" name="phone" id="phone" placeholder="Phone"
                                            data-bv-regexp="true" data-bv-regexp-regexp="^[0-9()+,/#\-]+$"  data-bv-regexp-message="The phone is not valid" />
                                </div>

                            </div>
                            <div class="padding5">
                                <div class="form-group">
                                    <label for="" class="col-sm-2 text-right">email</label>
                                    <div class="col-sm-10">
                                        <input  value="${passenger.getCustomer().getEmail()}" type="text" maxlength="50" class="form-control" id="email" name="email" placeholder="email">
                                    </div>
                                </div>
                            </div>
                            <div class="padding5">
                                <div class="form-group">
                                    <label for="" class="col-sm-2 text-right">Remarks</label>
                                    <div class="col-sm-10">
                                        <textarea class="form-control" maxlength="100" id="remark" name="remark" placeholder="Remark" rows="3">${passenger.getCustomer().getRemark()}</textarea>
                                    </div>
                                </div>
                            </div>
                            <!--button save-->
                            <div class="text-center" style="margin-top: 50px">
                                <input name="action" value="${action}"type="hidden">
                                <input name="id" value="${param.id}"type="hidden">
                                <input name="master" value="${passenger.getMaster().getId()}"type="hidden">
                                <input name="referenceNo" value="${param.referenceNo}"type="hidden">
                                <button id="ButtonSave" class="btn btn-success" type="submit"><span class="fa fa-save"></span> Save</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>


<!--Modal  Owner-->
<div class="modal fade" id="CustomerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Passenger</h4>
            </div>
            <div class="modal-body">
                <!--Owner List Table-->
                <table class="display" id="CustomerTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th>Initial Name</th>
                            <th class="hidden">InitialID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th class="hidden">sex</th>
                            <th class="hidden">address</th>
                            <th class="hidden">tel</th>
                            <th class="hidden">phone</th>
                            <th class="hidden">postal code</th>
                            <th class="hidden">email</th>
                            <th class="hidden">Japanese firstname</th>
                            <th class="hidden">Japanese lastname</th>
                            <th class="hidden">remark</th>
                            <th class="hidden">passportNo</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        customer = [];
                    </script>
                    <c:forEach var="customer" items="${customerList}">
                        <tr>
                            <td class="customer-id hidden">${customer.id}</td>
                            <td class="customer-code">${customer.code}</td>
                            <td class="customer-initial">${customer.getMInitialname().getName()}</td>
                            <td class="customer-initialId hidden">${customer.getMInitialname().getId()}</td>
                            <td class="customer-firstname">${customer.firstName}</td>
                            <td class="customer-lastname">${customer.lastName}</td>
                            <td class="customer-sex hidden">${customer.sex}</td>
                            <td class="customer-address hidden">${customer.address}</td>
                            <td class="customer-tel hidden">${customer.tel}</td>
                            <td class="customer-phone hidden">${customer.phone}</td>
                            <td class="customer-postal hidden">${customer.postalCode}</td>
                            <td class="customer-email hidden">${customer.email}</td>
                            <td class="customer-japanfirstname hidden">${customer.firstNameJapan}</td>
                            <td class="customer-japanlastname hidden">${customer.lastNameJapan}</td>
                            <td class="customer-remark hidden">${customer.remark}</td>
                            <td class="customer-passportno hidden">${customer.passportNo}</td>                                
                        </tr>
                        <script>
                            customer.push({
                                id: "${customer.id}",
                                code: "${customer.code}",
                                initial: "${customer.getMInitialname().getName()}",
                                initialId: "${customer.getMInitialname().getId()}",
                                firstname: "${customer.firstName}",
                                lastname: "${customer.lastName}",
                                sex: "${customer.sex}",
                                address: "${customer.address}",
                                tel: "${customer.tel}",
                                phone: "${customer.phone}",
                                postalCode: "${customer.postalCode}",
                                email: "${customer.email}",
                                firstNameJpan: "${customer.firstNameJapan}",
                                lastNameJapan: "${customer.lastNameJapan}",
                                remark: "${customer.remark}",
                                passportNo: "${customer.passportNo}",
                            });
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