<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/AddTicketFare.js"></script>--> 
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="airlineList" value="${requestScope['airlineList']}" />
<c:set var="ticketFare" value="${requestScope['ticketFare']}" />
<c:set var="agent" value="${requestScope['Agent']}" />
<c:set var="SelectedAgent" value="${requestScope['SelectedAgent']}" />
<c:set var="ticketList" value="${requestScope['ticketList']}" />
<c:set var="flightDetail" value="${requestScope['Flight_Detail']}" /> 
<section class="content-header" >
    <h1>
        Checking - Air Ticket
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Checking</a></li>          
        <li class="active"><a href="#">Add Ticket Fare</a></li>
    </ol>
</section>

<div style="padding-top: 15px;padding-right: 0px "ng-app=""> 
    <div class="row">
        <!--Alert Save and Update-->
        <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Success!</strong> 
        </div>
        <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Unsuccess!</strong> 
        </div>
        
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Checking/CheckingAirTicketMenu.html'"></div>
        </div>
        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6" style="padding-right: 15px">
                    <h4><b>Add Ticket Fare</b></h4>
                </div>
            </div>
            <hr/>
            
            <form action="AddTicketFare.smi" method="post" id="AddTicketFareForm" name="AddTicketFareForm" role="form">
                <input type="hidden" name="masterId" id="masterId" value="${ticketFare.master.id}">
                <input type="hidden" name="ticketTemp" id="ticketTemp" value="">
                <input type="hidden" name="ticketFareTemp" id="ticketFareTemp" value="">
                <input type="hidden" name="ticketTaxTemp" id="ticketTaxTemp" value="">
                <input type="hidden" name="issueDateTemp" id="issueDateTemp" value="">
                <input type="hidden" name="ticketRoutingTemp" id="ticketRoutingTemp" value="">
                <input type="hidden" name="airlineTemp" id="airlineTemp" value="">
                <input type="hidden" name="ticketByTemp" id="ticketByTemp" value="">
                <input type="hidden" name="passengerTemp" id="passengerTemp" value="">
                <input type="hidden" name="departmentTemp" id="departmentTemp" value="">
                <input type="hidden" name="masterIdTemp" id="masterIdTemp" value="">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Ticket&nbsp;No&nbsp;<font style="color: red">*</font></label>
                        </div>
                        <div class="col-xs-1 form-group" style="width: 200px" id="ticketnopanel">
                            <div class="input-group"  id='ticketnumber'>
                                <input id="ticketId" name="ticketId" type="hidden" class="form-control" maxlength="11" value="${ticketFare.id}">
                                <input id="ticketNo" name="ticketNo" type="text" class="form-control" maxlength="20" value="${ticketFare.ticketNo}">
                            </div>
                            
                        </div>
                        <div class="col-xs-1  text-right" style="width: 8px"><i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i></div>
                        <div class="col-xs-1 text-right" style="width: 100px">
                            <button style="height:34px" type="button"  id="ButtonSearch"  name="ButtonSearch" onclick="searchTicketNo();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search</button>

                        </div>
                        
                        <div class="col-xs-1 text-right" style="width: 100px">
                            <!--<button type="submit"  id="ButtonRefno"  name="ButtonRefno" onclick="refnoAction();"  class="btn btn-primary" data-toggle="modal" data-target="#ListRefnoModal"> Refno </button>-->
                            <a  id="ButtonRefno" class="btn btn-primary" data-toggle="modal" data-target="#ListRefnoModal"  style="height: 34px"><i class="glyphicon glyphicon-th-list"></i>&nbsp;Refno</a>
                        </div>
                    </div> 
                </div>
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Ticket&nbsp;Type&nbsp;</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select id="ticketType" name="ticketType" class="form-control selectize" >
                                <option value="">--- Type ---</option> 
                                <c:choose>
                                    <c:when test="${requestScope['TicketType'] == 'B'}">
                                        <c:set var="selectedB" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="B" ${selectedB}>BSP</option>
                                <c:choose>
                                    <c:when test="${requestScope['TicketType'] == 'D'}">
                                        <c:set var="selectedD" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="D" ${selectedD}>DOMESTIC</option>
                                <c:choose>
                                    <c:when test="${requestScope['TicketType'] == 'A'}">
                                        <c:set var="selectedA" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="A" ${selectedA}>AGENT</option>
                            </select>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Ticket Routing </label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select id="ticketRouting" name="ticketRouting" class="form-control selectize">
                                <option value="">--- Routing ---</option> 
                                <c:choose>
                                    <c:when test="${requestScope['TicketRouting'] == 'I'}">
                                        <c:set var="selectedI" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="I" ${selectedI}>INTER</option>
                                <c:choose>
                                    <c:when test="${requestScope['TicketRouting'] == 'D'}">
                                        <c:set var="selectedDomestic" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="D" ${selectedDomestic}>DOMESTIC</option>
                                <c:choose>
                                    <c:when test="${requestScope['TicketRouting'] == 'C'}">
                                        <c:set var="selectedCancel" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="C" ${selectedCancel}>CANCEL</option>
                            </select>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Airline&nbsp;</label>
                        </div> 
                        <div class="col-xs-1" style="width: 200px">
                            <select name="ticketAirline" id="ticketAirline" class="form-control">
                                <option value="">--- Airline ---</option> 
                                <c:forEach var="table" items="${airlineList}" >
                                    <c:set var="select" value="" />
                                    <c:set var="selectedId" value="${ticketFare.MAirlineAgent.id}" />
                                    <c:if test="${table.id == selectedId}">
                                        <c:set var="select" value="selected" />
                                    </c:if>
                                    <option value="${table.id}" ${select}>${table.code}</option>  
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Passenger</label>
                        </div>
                        <div class="col-xs-1 form-group" style="width: 200px">
                            <div class="input-group">
                                <input id="passenger" name="passenger" type="text" class="form-control" maxlength="50" value="${ticketFare.passenger}">
                            </div>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Ticket&nbsp;Buy&nbsp;</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select name="ticketBuy" id="ticketBuy" class="form-control">
                                <option value="">--- Buy ---</option> 
                                 <c:choose>
                                    <c:when test="${requestScope['TicketBuy'] == 'C'}">
                                        <c:set var="selectedC" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="C" ${selectedC}>IN</option>
                                <c:choose>
                                    <c:when test="${requestScope['TicketBuy'] == 'O'}">
                                        <c:set var="selectedO" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="O" ${selectedO}>OUT</option>
                            </select>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Department&nbsp;</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select id="department" name="department" class="form-control selectize">
                                <option value="">--- Department ---</option> 
                                 <c:choose>
                                    <c:when test="${requestScope['department'] == 'wendy'}">
                                        <c:set var="selectedWendy" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="wendy" ${selectedWendy}>wendy</option>
                                <c:choose>
                                    <c:when test="${requestScope['department'] == 'inbound'}">
                                        <c:set var="selectedInbound" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="inbound" ${selectedInbound}>inbound</option>
                                <c:choose>
                                    <c:when test="${requestScope['department'] == 'outbound'}">
                                        <c:set var="selectedOutbound" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="outbound" ${selectedOutbound}>outbound</option>
                            </select>
                        </div>    
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">PV Type</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select name="pvType" id="pvType" class="form-control">
                                <option value="">--- PV Type ---</option> 
                                <c:forEach var="table" items="${pvTypeList}" >
                                    <c:set var="select" value="" />
                                    <c:set var="selectedId" value="${ticketFare.MPaymentDoctype.id}" />
                                    <c:if test="${table.id == selectedId}">
                                        <c:set var="select" value="selected" />
                                    </c:if>
                                    <option value="${table.id}" ${select}>${table.name}</option>  
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">PV Code</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <input id="pvCode" name="pvCode" type="text" class="form-control" maxlength="20" value="${ticketFare.pvCode}" readonly="">
                        </div>
                    </div>
                </div>
                <!----- Ticket Detail ----->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">Ticket Detail</h4>
                    </div> 
                    <div class="panel-body"  style="padding-right: 0px;" style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Issue Date </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date' id='InputDatePicker'>
                                    <input id="issueDate" name="issueDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['issueDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Ticket Fare </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketFare" name="ticketFare" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.ticketFare}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Ticket Tax </label> 
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketTax" name="ticketTax" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.ticketTax}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Ticket Ins </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketIns" name="ticketIns" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.ticketIns}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Ticket Comm </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketCommission" name="ticketCommission" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.ticketCommission}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Agent Comm </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentCommission" name="agentCommission" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.agentCommission}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Inv Amount</label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">
                                    <input id="invoiceAmount" name="invoiceAmount" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" value="" readonly="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Sale Price </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="salePrice" name="salePrice" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.salePrice}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Diff Vat </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="diffVat" name="diffVat" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" onkeypress="return isNumberKey(event)" value="${ticketFare.diffVat}">
                                </div>
                            </div>

                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Agent</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">
                                    <input type="hidden" class="form-control" id="agent_id" name="agent_id" value="${SelectedAgent.id}"/>
                                    <input type="text" class="form-control" id="agent_user" name="agent_user" value="${SelectedAgent.code}" />
                                    <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#AgentModal">
                                        <span class="glyphicon-search glyphicon"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="col-xs-1" style="width: 5px">   
                            </div>
                            <div class="col-xs-1" style="width: 298px">
                                <input type="text" class="form-control" id="agent_name" name="agent_name" value="${SelectedAgent.name}" readonly="">
                            </div>
                        </div>
                        <div class="col-xs-10 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Remark </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <textarea rows="3" class="form-control" id="remark" maxlength="255" name="remark" style="width: 307%" >${ticketFare.remark}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!----- Detail ----->
                <div class="panel panel-default">
<!--                    <div class="panel-heading">
                        <h4 class="panel-title"></h4>
                    </div> -->
                    <div class="panel-body"  style="padding-right: 0px;" style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Over Comm </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date'>
                                    <input id="overCommission" name="overCommission" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.overCommission}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Add Pay </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="addPay" name="addPay" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.addPay}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Agent Comm Pay </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentComPay" name="agentComPay" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.agentComPay}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date'>
                                    <input id="overDate" name="overDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['overDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date'>
                                    <input id="addPayDate" name="addPayDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['addPayDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date'>
                                    <input id="agentPayDate" name="agentPayDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['agentPayDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Little Comm </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date'>
                                    <input id="litterCommission" name="litterCommission" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.litterCommission}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Dec Pay </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="decPay" name="decPay" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.decPay}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Agent Comm Receive </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentComReceive" name="agentComReceive" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.agentComReceive}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date'>
                                    <input id="litterDate" name="litterDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['litterDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date' id='date1'>
                                    <input id="decPayDate" name="decPayDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['decPayDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date' id='date2'>
                                    <input id="agentReceiveDate" name="agentReceiveDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['agentReceiveDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" style="padding-bottom: 20px">
                    <div class="col-xs-12">
                        <div class="col-xs-12 text-center" >  
                            <input type="hidden" name="action" id="action" value="">
                            <input type="hidden" name="temp" id="temp" value="">
                            <input type="hidden" name="refno" id="refno" value="${requestScope['refNo']}"> 
                            <input type="hidden" name="ticketFareFlag" id="ticketFareFlag" value="${requestScope['ticketFareFlag']}">
                            <input type="hidden" name="flightDetailFlag" id="flightDetailFlag" value="${requestScope['flightDetailFlag']}">
                            <input type="hidden" name="optionSave" id="optionSave" value="${requestScope['optionSave']}">
                            <button type="submit" id="ButtonSave" name="ButtonSave" onclick="saveAction(0)" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                            <button type="submit" id="ButtonSaveAndNew" name="ButtonSaveAndNew" onclick="saveAction(1)" class="btn btn-success"><i class="fa fa-save"></i> Save & New</button>
                        </div>
                    </div>
                </div>
                            
                <!----- Invoice Detail ----->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">Invoice Detail</h4>
                    </div> 
                    <div class="panel-body">
                        <table class="display" id="InvoiceDeailTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:5%;">No</th>
                                    <th style="width:15%;">Invoice No</th>
                                    <th style="width:15%;">Invoice Date</th>
                                    <th style="width:20%;">Department</th>
                                    <th style="width:15%;">Due Date</th>
                                    <th style="width:30%;">Sale Staff</th>
                                </tr>
                            </thead>
                            <tbody>
                               
                            </tbody>
                        </table>
                    </div>
                </div> 
                <!----- Receipt Detail ----->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">Receipt Detail</h4>
                    </div> 
                    <div class="panel-body">
                        <table class="display" id="ReceiptDetailTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:5%;">No</th>
                                    <th style="width:20%;">Receipt No</th>
                                    <th style="width:20%;">Receipt Date</th>
                                    <th style="width:20%;">Invoice No</th>
                                    <th style="width:15%;">Date</th>
                                    <th style="width:20%;">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                    </div>
                </div>  
                <!----- Flight Detail ----->
                <div id="flightPanel" class="panel panel-default hidden">
                    <div class="panel-heading">
                        <h4 class="panel-title">Flight Detail</h4>
                    </div> 
                    <div class="panel-body">
                        <table class="display" id="FlightDeailTable" cellspacing="0" >
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:5%;">No</th>
                                    <th style="width:15%;">Airline</th>
                                    <th style="width:10%;">Flight</th>
                                    <th style="width:10%;">Class</th>
                                    <th style="width:10%;">From</th>
                                    <th style="width:10%;">To</th>
                                    <th style="width:20%;">Depart Date</th>
                                    <th style="width:20%;">Arriver Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="table" items="${flightDetail}" varStatus="dataStatus">
                                    <tr>
                                        <td align="center"> <c:out value="${dataStatus.count}" /></td>
                                        <td align="center"> <c:out value="${table.bookingAirline.airlineCode}" /></td>
                                        <td align="center"> <c:out value="${table.flightNo}" /></td>
                                        <td align="center"> <c:out value="${table.flightClass}" /></td>
                                        <td align="center"> <c:out value="${table.sourceCode}" /></td>
                                        <td align="center"> <c:out value="${table.desCode}" /></td>
                                        <td align="center"> <c:out value="${table.departureDate}" /></td>
                                        <td align="center"> <c:out value="${table.arrivalDate}" /></td>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div> 
                    
                    
                    
            </form>                
        </div>
        
        
<!--List Refno Modal-->
<div class="modal fade" id="ListRefnoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 50%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">List Ticket No</h4>
            </div>
            <div class="modal-body">
                <div style="text-align: right;padding-bottom: 12px"><i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i>Search : <input placeholder ="Ref No" type="text" style="width: 175px" id="filtercus" name="filtercus"/> </div> 
                <table class="display" id="ListRefnoTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width:5%;">Ticket</th>
                            <th style="width:15%;">Name</th>
                            <th style="width:15%;">Class</th>
                            <th style="width:15%;">Depart Date</th>
                            <th style="width:15%;">Fare</th>
                            <th style="width:15%;">Tax</th>
                            <th style="width:5%;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="ListRefnoModalOK" name="ListRefnoModalOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="ListRefnoModalClose" name="ListRefnoModalClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->
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
<!-- MODAL-->
<div class="modal fade" id="AddTicketByRefModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Ticket No</h4>
            </div>
            <div class="modal-body" id="ticketNoAlert">

            </div>
            <div class="modal-footer">
                <button type="button" onclick="selectTicketNo()" class="btn btn-danger">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>               
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<c:if test="${! empty requestScope['saveresult']}">
    <c:if test="${requestScope['saveresult'] =='save successful'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
        <!--<META HTTP-EQUIV="Refresh" CONTENT="0;URL=http://localhost:8080/SMITravel/AddTicketFare.smi">-->
    </c:if>
    <c:if test="${requestScope['saveresult'] =='save unsuccessful'}">        
        <script language="javascript">
           $('#textAlertDivNotSave').show();
        </script>
        <!--<META HTTP-EQUIV="Refresh" CONTENT="0;URL=http://localhost:8080/SMITravel/AddTicketFare.smi">-->
    </c:if>
</c:if>
       
        
       
<script type="text/javascript">
    setTicketDetailTemp = [];
    $(document).ready(function () {
        $('.datemask').mask('0000-00-00');
        
        $("#flightPanel").addClass('hidden');
        if($('#flightDetailFlag').val() == "notdummy"){
            $("#flightPanel").removeClass('hidden');  
        }

        if($('#ticketFareFlag').val() == "dummy"){
            alert('Ticket no. not available');
        }
        if($('#refno').val() != ""){
            var refNo = $('#refno').val();
            $("#filtercus").val(refNo);
            FilterTicketList($("#filtercus").val());
        }
        
        $(".money").mask('000,000,000.00', {reverse: true});
        $('.date').datetimepicker();

        $('#ListRefnoTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });

        $("#ticketNo").keyup(function (event) {
            if(event.keyCode === 13){
               searchTicketNo();
            }
        });
        
        setFormatCurrency();
        
        $( ".numerical" ).on('input', function() { 
            var value=$(this).val().replace(/[^0-9.,]*/g, '');
            value=value.replace(/\.{2,}/g, '.');
            value=value.replace(/\.,/g, ',');
            value=value.replace(/\,\./g, ',');
            value=value.replace(/\,{2,}/g, ',');
            value=value.replace(/\.[0-9]+\./g, '.');
            $(this).val(value)
        });
        
        //on modal List Ticket
        $("#filtercus").keyup(function (event) {
            if (event.keyCode === 13) {
                FilterTicketList($("#filtercus").val());
            }
        });
        $("#AgentTable tr").on('click', function () {
            var agent_id = $(this).find(".agent-id").text();
            var agent_user = $(this).find(".agent-user").text();
            var agent_name = $(this).find(".agent-name").text();
            var agent_addr = $(this).find(".agent-addr").text();
            var agent_tel = $(this).find(".agent-tel").text();
            $("#agent_id").val(agent_id);
            $("#agent_user").val(agent_user);
            $("#agent_name").val(agent_name);
            $("#agent_addr").val(agent_addr);
            $("#agent_tel").val(agent_tel);
            $("#AgentModal").modal('hide');
        });
        
        var agentCode = [];
        $.each(agent, function (key, value) {
            agentCode.push(value.code);
            agentCode.push(value.name);
        });

        $("#agent_user").autocomplete({
            source: agentCode,
            close:function( event, ui ) {
               $("#agent_user").trigger('keyup');
            }
        });
        
        $("#agent_user").on('keyup',function(){
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var code = this.value.toUpperCase();
            var name = this.value.toUpperCase();
            console.log("Name :"+ name);
            $("#agent_id,#agent_name,#agent_addr,#agent_tel").val(null);
            $.each(agent, function (key, value) {
                if (value.code.toUpperCase() === code ) {  
                    $("#agent_id").val(value.id);
                    $("#agent_name").val(value.name);
                    $("#agent_addr").val(value.address);
                    $("#agent_tel").val(value.tel);
                    $("#agent_user").val(value.code);
                }
                else if(value.name.toUpperCase() === name){
                    $("#agent_user").val(value.code);
                    $("#agent_id").val(value.id);
                    $("#agent_name").val(value.name);
                    $("#agent_addr").val(value.address);
                    $("#agent_tel").val(value.tel);
                }
            }); 
        }); 

        // AGENT TABLE
        $('#AgentTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });
        $('#AgentTable tbody').on('click', 'tr', function () {
            $(this).addClass('row_selected').siblings().removeClass('row_selected');
        });
        
        $("#AddTicketFareForm")
            .bootstrapValidator({
                container: 'tooltip',
                excluded: [':disabled', ':hidden', ':not(:visible)'],
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    ticketNo: {
                        validators: {
                            notEmpty: {
                                message: ' Ticket No is required'
                            }
                        }
                    }
                }
            });
            
        $("#invoiceAmount").focusout(function(){
            calculateVat();
            setDataCurrency();
        });
        
        $("#ticketFare").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
            calculateVat();
        });
        
       $("#ticketTax").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
            calculateVat();
        });
        
        $("#ticketIns").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
            calculateVat();
        });
        
        $("#agentComReceive").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });
        $("#agentComPay").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });
        $("#addPay").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });
        $("#decPay").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });
        $("#litterCommission").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });
        $("#overCommission").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });
        $("#diffVat").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });
        $("#salePrice").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });
        $("#agentCommission").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });
        $("#ticketCommission").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });
        setDataCurrency();
        
        if($('#optionSave').val() == "1"){
            clearData();
            $("#ticketNo").val("");
        }
   });
   
function setFormatCurrency(){    
    var ticketFare = replaceAll(",","",$('#ticketFare').val()); 
    if (ticketFare == ""){
        ticketFare = 0;
    }
    ticketFare = parseFloat(ticketFare); 
    document.getElementById("ticketFare").value = formatNumber(ticketFare);
    
    var ticketTax = replaceAll(",","",$('#ticketTax').val()); 
    if (ticketTax == ""){
        ticketTax = 0;
    }
    ticketTax = parseFloat(ticketTax); 
    document.getElementById("ticketTax").value = formatNumber(ticketTax);
    
    var ticketIns = replaceAll(",","",$('#ticketIns').val()); 
    if (ticketIns == ""){
        ticketIns = 0;
    }
    ticketIns = parseFloat(ticketIns); 
    document.getElementById("ticketIns").value = formatNumber(ticketIns);    
    
    var ticketCommission = replaceAll(",","",$('#ticketCommission').val()); 
    if (ticketCommission == ""){
        ticketCommission = 0;
    }
    ticketCommission = parseFloat(ticketCommission); 
    document.getElementById("ticketCommission").value = formatNumber(ticketCommission);
    
    var agentCommission = replaceAll(",","",$('#agentCommission').val()); 
    if (agentCommission == ""){
        agentCommission = 0;
    }
    agentCommission = parseFloat(agentCommission); 
    document.getElementById("agentCommission").value = formatNumber(agentCommission);
    
    var salePrice = replaceAll(",","",$('#salePrice').val()); 
    if (salePrice == ""){
        salePrice = 0;
    }
    salePrice = parseFloat(salePrice); 
    document.getElementById("salePrice").value = formatNumber(salePrice);    
    
    var diffVat = replaceAll(",","",$('#diffVat').val()); 
    if (diffVat == ""){
        diffVat = 0;
    }
    diffVat = parseFloat(diffVat); 
    document.getElementById("diffVat").value = formatNumber(diffVat);
    
    var overCommission = replaceAll(",","",$('#overCommission').val()); 
    if (overCommission == ""){
        overCommission = 0;
    }
    overCommission = parseFloat(overCommission); 
    document.getElementById("overCommission").value = formatNumber(overCommission);    
    
    var litterCommission = replaceAll(",","",$('#litterCommission').val()); 
    if (litterCommission == ""){
        litterCommission = 0;
    }
    litterCommission = parseFloat(litterCommission); 
    document.getElementById("litterCommission").value = formatNumber(litterCommission);
    
    var decPay = replaceAll(",","",$('#decPay').val()); 
    if (decPay == ""){
        decPay = 0;
    }
    decPay = parseFloat(decPay); 
    document.getElementById("decPay").value = formatNumber(decPay);    
    
    var addPay = replaceAll(",","",$('#addPay').val()); 
    if (addPay == ""){
        addPay = 0;
    }
    addPay = parseFloat(addPay); 
    document.getElementById("addPay").value = formatNumber(addPay);    
    
    var agentComPay = replaceAll(",","",$('#agentComPay').val()); 
    if (agentComPay == ""){
        agentComPay = 0;
    }
    agentComPay = parseFloat(agentComPay); 
    document.getElementById("agentComPay").value = formatNumber(agentComPay);
    
    var agentComReceive = replaceAll(",","",$('#agentComReceive').val()); 
    if (agentComReceive == ""){
        agentComReceive = 0;
    }
    agentComReceive = parseFloat(agentComReceive); 
    document.getElementById("agentComReceive").value = formatNumber(agentComReceive);    
}

function setDataCurrency(){    
    var ticketFare = replaceAll(",","",$('#ticketFare').val()); 
    if (ticketFare == "" || ticketFare == 0){
        document.getElementById("ticketFare").value = "";
    }
    
    var ticketTax = replaceAll(",","",$('#ticketTax').val()); 
    if (ticketTax == "" || ticketTax == 0){
        document.getElementById("ticketTax").value = "";
    }
    
    var ticketIns = replaceAll(",","",$('#ticketIns').val()); 
    if (ticketIns == "" || ticketIns == 0){
        document.getElementById("ticketIns").value = "";  
    }
            
    
    var ticketCommission = replaceAll(",","",$('#ticketCommission').val()); 
    if (ticketCommission == "" || ticketCommission == 0){
        document.getElementById("ticketCommission").value = ""; 
    }
    
    
    var agentCommission = replaceAll(",","",$('#agentCommission').val()); 
    if (agentCommission == "" || agentCommission == 0){
        document.getElementById("agentCommission").value = ""; 
    }
    
    var salePrice = replaceAll(",","",$('#salePrice').val()); 
    if (salePrice == "" || salePrice == 0){
        document.getElementById("salePrice").value = "";  
    }
    
    var diffVat = replaceAll(",","",$('#diffVat').val()); 
    if (diffVat == "" || diffVat == 0){
        document.getElementById("diffVat").value =""; 
    }
    
    var overCommission = replaceAll(",","",$('#overCommission').val()); 
    if (overCommission == "" || overCommission == 0){
        document.getElementById("overCommission").value = "";    
    }
    
    var litterCommission = replaceAll(",","",$('#litterCommission').val()); 
    if (litterCommission == "" || litterCommission == 0){
        document.getElementById("litterCommission").value = ""; 
    }
    
    var decPay = replaceAll(",","",$('#decPay').val()); 
    if (decPay == "" || decPay == 0){
        document.getElementById("decPay").value = ""; 
    }
    
    var addPay = replaceAll(",","",$('#addPay').val()); 
    if (addPay == "" || addPay == 0){
        document.getElementById("addPay").value = ""; 
    }
    
    var agentComPay = replaceAll(",","",$('#agentComPay').val()); 
    if (agentComPay == "" || agentComPay == 0){
        document.getElementById("agentComPay").value = ""; 
    }
    
    var agentComReceive = replaceAll(",","",$('#agentComReceive').val()); 
    if (agentComReceive == "" || agentComReceive == 0){
        document.getElementById("agentComReceive").value = ""; 
    }
}
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;

    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
       return false;
    }
    
    return true;
}

function saveAction(optionsave){
    $("#optionSave").val(optionsave); 
    var action = document.getElementById('action');
    action.value = 'save';
    var ticketNo = document.getElementById('ticketNo');
    ticketNo.value = $("#ticketNo").val();
    var ticketType = document.getElementById('ticketType');
    ticketType.value = $("#ticketType").val();
    var ticketBuy = document.getElementById('ticketBuy');
    ticketBuy.value = $("#ticketBuy").val();
    var ticketRouting = document.getElementById('ticketRouting');
    ticketRouting.value = $("#ticketRouting").val();
    var ticketAirline = document.getElementById('ticketAirline');
    ticketAirline.value = $("#ticketAirline").val(); 
    var department = document.getElementById('department');
    department.value = $("#department").val();
    var passenger = document.getElementById('passenger');
    passenger.value = $("#passenger").val();
    var issueDate = document.getElementById('issueDate');
    issueDate.value = $("#issueDate").val();
    var ticketFare = document.getElementById('ticketFare'); 
    ticketFare.value = $("#ticketFare").val().replace(",","");
    var ticketTax = document.getElementById('ticketTax');
    ticketTax.value = $("#ticketTax").val();
    var ticketIns = document.getElementById('ticketIns');
    ticketIns.value = $("#ticketIns").val();
    var ticketCommission = document.getElementById('ticketCommission');
    ticketCommission.value = $("#ticketCommission").val();
    var agentCommission = document.getElementById('agentCommission');
    agentCommission.value = $("#agentCommission").val();
    var salePrice = document.getElementById('salePrice');
    salePrice.value = $("#salePrice").val();
    var diffVat = document.getElementById('diffVat');
    diffVat.value = $("#diffVat").val();
    var agentId = document.getElementById('agent_id');
    agentId.value = $("#agent_id").val();
    var remark = document.getElementById('remark');
    remark.value = $("#remark").val();
    var overCommission = document.getElementById('overCommission');
    overCommission.value = $("#overCommission").val();
    var litterCommission = document.getElementById('litterCommission');
    litterCommission.value = $("#litterCommission").val();
    var decPay = document.getElementById('decPay');
    decPay.value = $("#decPay").val();
    var addPay = document.getElementById('addPay');
    addPay.value = $("#addPay").val();
    var agentComPay = document.getElementById('agentComPay');
    agentComPay.value = $("#agentComPay").val();
    var agentComReceive = document.getElementById('agentComReceive');
    agentComReceive.value = $("#agentComReceive").val();
    var overDate = document.getElementById('overDate');
    overDate.value = $("#overDate").val();
    var litterDate = document.getElementById('litterDate');
    litterDate.value = $("#litterDate").val();
    var decPayDate = document.getElementById('decPayDate');
    decPayDate.value = $("#decPayDate").val();
    var addPayDate = document.getElementById('addPayDate');
    addPayDate.value = $("#addPayDate").val();
    var agentPayDate = document.getElementById('agentPayDate');
    agentPayDate.value = $("#agentPayDate").val();
    var agentReceiveDate = document.getElementById('agentReceiveDate');
    agentReceiveDate.value = $("#agentReceiveDate").val();
    var pvCode = document.getElementById('pvCode');
    pvCode.value = $("#pvCode").val(); 
    var pvType = document.getElementById('pvType');
    pvType.value = $("#pvType").val(); 
    var optionSave = document.getElementById('optionSave');
    optionSave.value = $("#optionSave").val(); 
}

function searchTicketNo() {
    var ticketNo = $("#ticketNo").val();
    var ticketnopanel = $("#ticketnopanel").val();
    if(ticketNo == ""){
        if(!$('#ticketnopanel').hasClass('has-feedback')) {
            $('#ticketnopanel').addClass('has-feedback');
        }
        $('#ticketnopanel').removeClass('has-success');
        $('#ticketnopanel').addClass('has-error');
    }
    else{
        $('#ticketNo').focus();
        var action = document.getElementById('action');
        action.value = 'search';
        var ticketNo = document.getElementById('ticketNo');
        ticketNo.value = $("#ticketNo").val();
        document.getElementById('AddTicketFareForm').submit();
    }
}
function FilterTicketList(referNo) {
    var servletName = 'TicketFareAirlineServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&referNo=' + referNo +
            '&type=' + 'getTicketList';
    CallFilterAjax(param);
}

function CallFilterAjax(param) {
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                try { 
                    if(msg == "null"){
                        $('#ListRefnoTable').dataTable().fnClearTable();
                        $('#ListRefnoTable').dataTable().fnDestroy();
                        $('#ListRefnoTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                    }else{
                        $('#ListRefnoTable').dataTable().fnClearTable();
                        $('#ListRefnoTable').dataTable().fnDestroy();
                        $("#ListRefnoTable tbody").empty().append(msg);
                        $('#ListRefnoTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength":10
                        });
                    }

                     $("#ajaxload").addClass("hidden");
                } catch (e) {
                    alert(e);
                }

            }, error: function (msg) {
                 $("#ajaxload").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}   
function selectTicketNo(){
    clearData();
    $('#AddTicketByRefModal').modal('hide');
    
    $.each(setTicketDetailTemp, function (key, value) {
        $("#ticketNo").val(value.no);
        $('#AddTicketFareForm').bootstrapValidator('revalidateField', 'ticketNo');
        searchTicketNo();
    });
    setFormatCurrency();
    setDataCurrency();
    $("#ListRefnoModal").modal('hide');
}

function setTicketFareDetail(ticket,refno){
    setTicketDetailTemp.push({no:ticket,refno:refno});
    var ticketNo = $("#ticketNo").val();
    if(ticketNo != ""){
        $("#ticketNoAlert").text('Are you sure to edit Ticket No. ?');
        $('#AddTicketByRefModal').modal('show');
    }else{
        $("#ticketNo").val(ticket);
        $("#refno").val(refno);
        searchTicketNo();
    }
    setFormatCurrency();
    setDataCurrency();
    $("#ListRefnoModal").modal('hide');
}
function clearData(){
//    $("#ticketNo").val("");
    $("#ticketType").val("");
    $("#ticketRouting").val("");
    $("#ticketAirline").val("");
    $("#passenger").val("");
    $("#ticketBuy").val("");
    $("#department").val("");
    $("#pvType").val("");
    $("#pvCode").val("");
    $("#issueDate").val("");
    $("#ticketFare").val(""); 
    $("#ticketTax").val("");
    $("#ticketIns").val("");
    $("#ticketCommission").val("");
    $("#agentCommission").val("");
    $("#invoiceAmount").val("");
    $("#salePrice").val("");
    $("#diffVat").val("");
    $("#agent_id").val("");
    $("#agent_user").val("");
    $("#agent_name").val("");
    $("#remark").val("");
    $("#overCommission").val("");
    $("#litterCommission").val("");
    $("#decPay").val("");
    $("#addPay").val("");
    $("#agentComPay").val("");
    $("#agentComReceive").val("");
    $("#overDate").val("");
    $("#litterDate").val("");
    $("#decPayDate").val("");
    $("#addPayDate").val("");
    $("#agentPayDate").val("");
    $("#agentReceiveDate").val("");

}
function calculateVat() {

    //Diff Vat = Inv Amount - Fare - Tax - Ins (ค่า Diff vat สามารถติดลบได้)
    var ticketType = document.getElementById('ticketType').value;
    if(ticketType == "A" || ticketType == "B"){
        var invAmount = replaceAll(",","",$('#invoiceAmount').val()); 
        if (invAmount == ""){
            invAmount = 0;
        }

        var ticketfare = replaceAll(",","",$('#ticketFare').val()); 
        if (ticketfare == ""){
            ticketfare = 0;
        }

        var tickettax = replaceAll(",","",$('#ticketTax').val()); 
        if (tickettax == ""){
            tickettax = 0;
        }

        var ticketins = replaceAll(",","",$('#ticketIns').val()); 
        if (ticketins == ""){
            ticketins = 0;
        }
      
       var inv = parseFloat(invAmount); 
       var fare = parseFloat(ticketfare);
       var tax = parseFloat(tickettax);
       var ins = parseFloat(ticketins);
       var diffvat = inv - fare - tax - ins;
       document.getElementById("diffVat").value = formatNumber(diffvat);
    }
}
function replaceAll(find, replace, str) {
  return str.replace(new RegExp(find, 'g'), replace);
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
}

function insertCommas(nField){
    if (/^0/.test(nField.value)){
        nField.value = nField.value.substring(0,1);
    }
    if (Number(nField.value.replace(/,/g,""))){
        var tmp = nField.value.replace(/,/g,"");
        tmp = tmp.toString().split('').reverse().join('').replace(/(\d{3})/g,'$1,').split('').reverse().join('').replace(/^,/,'');
        if (/\./g.test(tmp)){
            tmp = tmp.split(".");
            tmp[1] = tmp[1].replace(/\,/g,"").replace(/ /,"");
            nField.value = tmp[0]+"."+tmp[1]
        }else{
            nField.value = tmp.replace(/ /,"");
        } 
    }else{
        nField.value = nField.value.replace(/[^\d\,\.]/g,"").replace(/ /,"");
    }
}
</script>
