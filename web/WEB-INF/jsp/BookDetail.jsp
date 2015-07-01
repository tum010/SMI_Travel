<!--    Document   : BookDetail
    Created on : Dec 19, 2014, 1:55:09 PM
    Author     : sumeta-->


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/BookDetail.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script> 
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="detail" value="${requestScope['BookDetail']}" />
<c:set var="agent" value="${requestScope['Agent']}" />
<c:set var="action" value="${requestScope['action']}" />
<c:set var="SelectedAgent" value="${requestScope['SelectedAgent']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="initialList" value="${requestScope['initialList']}" />
<c:set var="currencyList" value="${requestScope['CurrencyList']}" />
<c:set var="customerList" value="${requestScope['customerList']}" />
<c:set var="mBookingstatuseList" value="${requestScope['BookingstatuseList']}" />
<c:set var="packList" value="${requestScope['packagelist']}" />
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />

<input type="hidden" value="1" id="enable-status">
<input type="hidden" value="${param.action}" id="actionIDown">
<input type="hidden" id="saveText" name="saveText" value="${status}">


<c:choose>
    <c:when test="${not empty param.referenceNo}">
        <!-- <input type="hidden" value="${param.referenceNo}" id="getUrl"> -->
         <input type="hidden" value="${refno1}-${refno2}" id="getUrl">
         <input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
    </c:when>
    <c:otherwise>
        <input type="hidden" value="${detail.referenceNo}" id="getUrl">
    </c:otherwise>
</c:choose>

<section class="content-header" >
    <h1>
        Booking - Detail <!--${string2} - ${string3} -->
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">Detail</a></li>
    </ol>
</section>

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
            <input type="hidden" value="${detail.customer.MInitialname.name}" id="initialname_tmp">
            <input type="hidden" value="${detail.customer.firstName}" id="firstname_tmp">
            <input type="hidden" value="${detail.customer.lastName}" id="lastname_tmp">  
            
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input id="get-status" type="hidden" value="${detail.getMBookingstatus().getName()}"/>
            <c:forEach var="status" items="${mBookingstatuseList}">
                <input name="status_list" type="hidden" value="${status.name}">
            </c:forEach>

            <form action="BookDetail.smi" method="post" id="BookDetail" role="form">
                <input type="hidden" class="form-control" id="referenceNo" name="referenceNo" value="${detail.referenceNo}"/>
                <input type="hidden" class="form-control" id="agent_id" name="agent_id" value="${SelectedAgent.id}"/>
                <input type="hidden" class="form-control" id="masterId" name="masterId" value="${detail.id}" />
                <input type="hidden" class="form-control"  id="action" name="action" value="${action}" />
                <input type="hidden" value="${detail.createDate}" id="master-createDate">
                <input type="hidden" value="${detail.createBy}" id="master-createBy">
                <c:choose>
                    <c:when test="${not empty detail.MBookingstatus.name}">
                        <input type="hidden" id="status" name="status" value="${detail.MBookingstatus.name}">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" id="status" name="status" value="Normal">
                    </c:otherwise>
                </c:choose>

            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Success!</strong> 
            </div>
                <div class="panel panel-default">
                    <div class="panel-body">

                        <!--Agent-->
                        <div class="row" style="margin-bottom: 10px">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label text-right">Agent</label>
                                    <div class="col-sm-4"> 
                                        <div class="input-group" id="gr">
                                            <input type="text" class="form-control" id="agent_user" name="agent_user" value="${SelectedAgent.code}" />
                                            <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#AgentModal">
                                                <span class="glyphicon-search glyphicon"></span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-sm-5 form-group"> 
                                        <input type="text" class="form-control" id="agent_name" name="agent_name" value="${SelectedAgent.name}" readonly=""
                                               data-bv-notempty="true" data-bv-notempty-message="agent empty !">
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-4">
                                <label class="col-sm-3 control-label text-right">Address</label>
                                <div class="col-sm-9"> 
                                    <input type="text" class="form-control" id="agent_addr" value="${SelectedAgent.address}" readonly="">
                                </div>
                            </div>
                            <div class="col-sm-2 row-fluid" style="margin-left: -40px">
                                <label class="col-sm-1 control-label">Tel</label>
                                <div class="col-sm-9"> 
                                    <input type="text" class="form-control" id="agent_tel"  value="${SelectedAgent.tel}" readonly="">
                                </div>
                            </div>

                        </div>

                        <!--Agent Ref-->
                        <div class="row"  style="margin-bottom: 10px">
                            <div class="col-sm-6">
                                <label class="col-sm-3 control-label text-right">Agent Ref</label>
                                <div class="col-sm-9">                             
                                    <input type="text" class="form-control" id="agent_ref" name="agent_ref" value="${detail.agentRef}" maxlength="100">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <label class="col-sm-2 control-label text-right">Currency</label>
                                <input type="hidden" class="form-control" id="selectedCurrency" name="selectedCurrency" value="${detail.currency}" />
                                <div class="col-sm-4 form-group">                             
                                    <select class="form-control" id="currency" name="currency">
                                        <c:forEach var="currency" items="${currencyList}">
                                            <option value="${currency.code}">${currency.code}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <!--Family Leader-->            
                        <div class="row" style="margin-bottom: 10px">
                            <div class="col-sm-12">
                                <label class="col-sm-2 control-label text-right" style="width: 127px">Family Leader</label>
                                <div class="col-sm-2 form-group"> 
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="leaderId" name="leaderId" value="${detail.customer.id}"/>
                                        <input type="text" class="form-control" id="FamilyLeaderCode" name="leaderCode" value="${detail.customer.code}"   style="background-color: #ffffff">
                                        <span class="input-group-addon" id="familyleader-modal"  data-toggle="modal" data-target="#FamilyLeaderModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-sm-2">  
                                    <input value="${detail.customer.MInitialname.name}" id="get-initial" type="hidden">
                                    <!--                                    <select class="form-control " id="initialname" name="initialname">
                                    <c:forEach var="initial" items="${initialList}">
                                        <option value="${initial.id}">${initial.name}</option>
                                    </c:forEach>
                                </select>-->
                                    <input  type="text" id="initialname" name="initialname" class="form-control" value="${detail.customer.MInitialname.name}" readonly="">
                                </div>     
                                <div class="col-sm-1"> </div>
                                <label class="col-sm-1 text-right" style="margin-left: -24px">Name</label>
                                <div class="col-sm-2"> 
                                    <input type="text" class="form-control" id="lastname" name="lastname" value="${detail.customer.lastName}" readonly="">
                                </div>
                                <div class="col-sm-2" style="margin-left: -7px"> 
                                    <input type="text" class="form-control"  id="firstname" name="firstname" 
                                           value="${detail.customer.firstName}" readonly=""
                                           data-bv-notempty="true"
                                           data-bv-notempty-message="The name is required">
                                </div>
                                <div class=""> 
                                </div>
                            </div>
                        </div>
                        <div class="row">

                            <div class="col-sm-12">
                                <div class="">
                                    <label class="col-sm-2 control-label text-right" style="width: 127px">Address</label>
                                    <div class="col-sm-4"> 
                                        <input type="text" class="form-control" name="address"  id="address" value="${detail.customer.address}" maxlength="100">
                                    </div>
                                </div>
                                <div class="col-sm-6 form-group"> 
                                    <div class="col-sm-1"></div>
                                    <label class="col-sm-2 control-label text-right" style="margin-left: 8px">Tel</label>
                                    <div class="col-sm-5" style="margin-left: -5px"> 
                                        <input type="text" class="form-control" name="tel" id="tel" value="${detail.customer.tel}" maxlength="20"
                                               data-bv-regexp="true" data-bv-regexp-regexp="^[0-9()+,/#\-]+$"  data-bv-regexp-message="The tel is not valid" >
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!--Adult-->
                        <div class="row">
                            <div class="col-sm-10">
                                <c:choose>
                                    <c:when test="${param.action == 'new'}">
                                        <label class="col-sm-1 control-label text-right" style="width: 127px">Adult</label>
                                        <div class="col-sm-1"> 
                                            <input type="number" min="0" class="form-control" name="adult" id="adult" value="0">
                                        </div>
                                        <label class="col-sm-1 control-label text-right">Child</label>
                                        <div class="col-sm-1"> 
                                            <input type="number" min="0" class="form-control" name="child" id="child" value="0">
                                        </div>
                                        <label class="col-sm-1 control-label text-right">Infant</label>
                                        <div class="col-sm-1"> 
                                            <input type="number" min="0" class="form-control" name="infant" id="infant" value="0">
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <label class="col-sm-1 control-label text-right" style="width: 127px">Adult</label>
                                        <div class="col-sm-1"> 
                                            <input type="number" min="0" class="form-control" name="adult" id="adult" value="${detail.adult}" maxlength="11">
                                        </div>
                                        <label class="col-sm-1 control-label text-right">Child</label>
                                        <div class="col-sm-1"> 
                                            <input type="number" min="0" class="form-control" name="child" id="child" value="${detail.child}" maxlength="11">
                                        </div>
                                        <label class="col-sm-1 control-label text-right">Infant</label>
                                        <div class="col-sm-1"> 
                                            <input type="number" min="0" class="form-control" name="infant" id="infant" value="${detail.infant}" maxlength="11">
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                                <label class="col-sm-2 control-label text-right" style="margin-left: -9px">Package</label>
                                    <div class="col-sm-3" style="padding-left: 25px;margin-left: -15px"> 
                                        <input id="input-get-city" value="${detail.packageTour.id}" hidden="">
                                        <select name="packagecode" id="packagecode"  class="selectize"  >
                                            <option value="">- - package - -</option>
                                            <c:forEach var="item" items="${packList}" >
                                                <c:set var="select" value="" />
                                                <c:set var="selectedId" value="${detail.packageTour.id}" />
                                                <c:if test="${item.id == selectedId}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                 <option value="${item.id}" ${select}>${item.code}</option>   
                                            </c:forEach>
                                        </select> 
                                            <input type="hidden" id="ch_pax" value="${master.isPackage}">
                                    </div>
                                   <script>
                                        $(document).ready(function () {
                                            Selectize.define( 'clear_selection', function ( options ) {
                                                var self = this;
                                                self.plugins.settings.dropdown_header = {
                                                    title: 'Clear Selection'
                                                };
                                                this.require( 'dropdown_header' );
                                                self.setup = (function () {
                                                    var original = self.setup;
                                                    return function () {
                                                        original.apply( this, arguments );
                                                        this.$dropdown.on( 'mousedown', '.selectize-dropdown-header', function ( e ) {
                                                            self.setValue( '' );
                                                            self.close();
                                                            self.blur();
                                                            return false;
                                                        });
                                                    };
                                                })();
                                            });

                                            var name = "#packagecode";
                                            console.log("name = " + name);

                                            $(name).selectize({
                                                removeItem: '',
                                                sortField: 'text' ,
                                                create: false ,
                                                dropdownParent: 'body',
                                                plugins: {
                                                    'clear_selection': {}
                                                }
                                            });
                                        });
                                    </script>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Save-->
                <div class="text-center" style="margin-top: 10px">
                    <button id="BookDetailButtonSave" type="submit" class="btn btn-success" >
                        <span id="BookDetailSpanSave" class="fa fa-save"></span> Save
                    </button>
                </div>



            </form>

        </div>

    </div>
</div>

<!--Modal  Agent-->
<div class="modal fade" id="AgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Agent</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="AgentTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                            <th class="hidden">Fax</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        agent = [];
                    </script>
                    <c:forEach var="a" items="${agent}">
                        <tr>
                            <td class="agent-id hidden">${a.id}</td>
                            <td class="agent-user">${a.code}</td>
                            <td class="agent-name">${a.name}</td>
                            <td class="agent-addr hidden">${a.address}</td>
                            <td class="agent-tel hidden">${a.tel}</td>
                            <td class="agent-fax hidden">${a.fax}</td>
                        </tr>
                        <script>
                            agent.push({id: "${a.id}", code: "${a.code}", name: "${a.name}", 
                                        address: "${a.address}", tel: "${a.tel}", fax: "${a.fax}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="AgentModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Modal  Family Leader-->
<div class="modal fade" id="FamilyLeaderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Family Leader</h4>
            </div>
            <div class="modal-body" id="mb1">
                <!--FamilyLeaderTable List Table-->

                <div style="text-align: right"><i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i>Search : <input placeholder ="LAST/FIRST " type="text" style="width: 175px" id="filtercus" name="filtercus"/> </div> 
                <table class="display" id="FamilyLeaderTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th class="hidden">Initial Name</th>
                            <th>Last Name</th>
                            <th>First Name</th>
                            <th class="hidden">Address</th>
                            <th >Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        customer = [];
                    </script>
                    <c:forEach var="customer" items="${customerList}">
                        <tr onclick="setCustomerDetail('${customer.id}', '${customer.code}', '${customer.MInitialname.name}', '${customer.firstName}', '${customer.lastName}', '${customer.address}', '${customer.tel}')">
                            <td id="customerId" class="hidden customerId">${customer.id}</td>
                            <td >${customer.code}</td>
                            <td class="hidden">${customer.MInitialname.name}</td>
                            <td >${customer.lastName}</td>
                            <td >${customer.firstName}</td>
                            <td class="hidden">${customer.address}</td>
                            <td >${customer.tel}</td>
                        </tr>
                        <script>
                            customer.push({id: "${customer.id}", code: "${customer.code}", 
                                            initial: "${customer.MInitialname.name}", firstName: "${customer.firstName}", 
                                            lastName: "${customer.lastName}", address: "${customer.address}", tel: "${customer.tel}"});   
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!--Family Form Add-->
            <div class="modal-body" id="mb2" hidden="">
                <form  id="FormAddFamily" method="post" class="form-horizontal" action="BookDetail.smi?referenceNo=${param.referenceNo}&action=addfamily">
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Initial Name</label>
                        <div class="col-xs-7">
                            <select class="form-control" name="input-initial-name" id="input-initial-name">
                                <c:forEach var="initial" items="${initialList}">
                                    <option value="${initial.id}">${initial.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Last Name</label>
                        <div class="col-xs-7">
                            <input type="text" class="form-control" name="input-last-name" id="input-last-name" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">First Name</label>
                        <div class="col-xs-7">
                            <input type="text" class="form-control" name="input-first-name" id="input-first-name" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Address</label>
                        <div class="col-xs-7">
                            <input type="text" class="form-control" name="input-address" id="input-address" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Tel</label>
                        <div class="col-xs-7">
                            <input type="text" class="form-control" name="input-tel" id="input-tel"
                            data-bv-regexp="true" data-bv-regexp-regexp="^[0-9()+,/#\-]+$"  data-bv-regexp-message="The tel is not valid" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="text-center">
                            <button id="FamilyLeaderModalFormAddButtonSave"   type="button" onclick="saveCus()" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <a id="FamilyLeaderModalButtonSave" class="btn btn-success btn-sm" onclick="add()">
                        <i class="glyphicon glyphicon-plus"></i> Add
                    </a>
                    <button id="FamilyLeaderModalButtonClose" type="button" onclick="closeReturn()" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div><!-- /.modal-dialog -->
</div>

<!--Modal  Family Leader-->
<div class="modal fade" id="FamilyLeaderAjaxModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Family Leader</h4>
            </div>
            <div class="modal-body">
                <!--FamilyLeaderTable List Table-->
                <table class="display" id="FamilyLeaderAjaxTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th class="hidden">Initial Name</th>
                            <th>Last Name</th>
                            <th>First Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
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

<c:if test="${! empty requestScope['result']}">
    <script language="javascript">
        $('#textAlertDivSave').show();
    </script>
</c:if>

