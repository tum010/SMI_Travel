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
<c:set var="flightDetailFromAirticket" value="${requestScope['Flight_Detail_Airticket']}" /> 
<c:set var="invoiceDetailList" value="${requestScope['invoiceDetailList']}" />
<c:set var="receiptDetailList" value="${requestScope['receiptDetailList']}" />
<c:set var="withholdingtax" value="${requestScope['withholdingtax']}" />
<c:set var="VAT" value="${requestScope['VAT']}" />
<c:set var="refundDetailList" value="${requestScope['refundDetailList']}" />

<section class="content-header" >
    <h1>
        Checking - Air Ticket
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Checking</a></li>          
        <li class="active"><a href="#">Add Ticket Fare</a></li>
    </ol>
</section>

<div class="container" style="padding-top: 15px;padding-right: 0px; padding-left: 0px;"ng-app=""> 
    <div class="row">
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px;">
            <div ng-include="'WebContent/Checking/CheckingAirTicketMenu.html'"></div>
        </div>
        <div class="col-xs-10">
            <!--Alert Save and Update-->
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Success!</strong> 
            </div>
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Unsuccess!</strong> 
            </div>
            <div id="textAlertTicketNo"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Ticket no. not available !</strong> 
            </div>
        </div>
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6" style="padding-right: 15px">
                    <h4><b>Add Ticket Fare</b></h4>
                </div>
            </div>
            <hr/>
            
            <form action="AddTicketFare.smi" method="post" id="AddTicketFareForm" name="AddTicketFareForm" role="form">
                <input type="hidden" name="invoiceDetailTableId" id="invoiceDetailTableId" value="${requestScope['invDetailTableId']}">
                <input type="hidden" name="isTempTicket" id="isTempTicket" value="${ticketFare.isTempTicket}">
                <input type="hidden" name="masterId" id="masterId" value="${ticketFare.master.id}">
                <input type="hidden" name="whtax" id="whtax" value="${withholdingtax}">
                <input type="hidden" name="vat" id="vat" value="${VAT}">
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
                <input type="hidden" name="countRow" id="countRow" value="">
                <input type="hidden" name="invNoFilter" id="invNoFilter" value="${requestScope['invNoFilter']}">

                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Ticket&nbsp;No&nbsp;<font style="color: red">*</font></label>
                        </div>
                        <div class="col-xs-1 form-group" style="width: 200px" id="ticketnopanel">
                            <div class="input-group"  id='ticketnumber'>
                                <input id="ticketId" name="ticketId" type="hidden" class="form-control" maxlength="11" value="${ticketFare.id}">
                                <input id="ticketNoTempSearch" name="ticketNoTempSearch" type="hidden" class="form-control" maxlength="20" value="${ticketFare.ticketNo}">
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
                        <div class="col-xs-1 text-right" style="width: 100px">
                            <a  id="ButtonInv" class="btn btn-primary" data-toggle="modal" data-target="#ListInvnoModal"  style="height: 34px"><i class="glyphicon glyphicon-th-list"></i>&nbsp;Invno</a>
                        </div>
                    </div> 
                </div>
                <div class="row">
                    <div class="col-xs-12" style="margin-top: -10px">
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Ticket&nbsp;Type&nbsp;</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select id="ticketType" name="ticketType" class="form-control selectize" onchange="calculateVat()">
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
                                <c:choose>
                                    <c:when test="${requestScope['TicketType'] == 'TI'}">
                                        <c:set var="selectedTI" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="TI" ${selectedTI}>TG Inter</option>
                                
                                <c:choose>
                                    <c:when test="${requestScope['TicketType'] == 'TD'}">
                                        <c:set var="selectedTD" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="TD" ${selectedTD}>TG Domestic</option>
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
                            <label class="control-label text-right">Airline&nbsp;Agent</label>
                        </div> 
                        <div class="col-xs-1" style="width: 100px; padding-right: 0px;">
                            <select name="ticketAirline" id="ticketAirline" class="form-control" onclick="checkAirlineSelected()">
                                <option value="">-Airline-</option> 
                                <c:forEach var="table" items="${airlineList}" >
                                    <c:set var="select" value="" />
                                    <c:set var="selectedId" value="${ticketFare.MAirlineAgent.id}" />
                                    <c:if test="${table.id == selectedId}">
                                        <c:set var="select" value="selected" />
                                    </c:if>
                                    <option value="${table.id}" ${select}>${table.code}</option>
                                </c:forEach>
                                    <option value="OTHER">OTHER</option>
                            </select>
                        </div>
                        <div class="col-xs-1" style="width:100px">
                            <input id="ticketAirlineOther" name="ticketAirlineOther" type="text" class="form-control" maxlength="50" value="${ticketFare.otherAirAgent}" disabled="">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12" style="margin-top: 3px">
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
                    <div class="col-xs-12" style="margin-top: -10px">
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
                            <div class="col-xs-1 text-right" style="width: 50px">
                                <%--<c:choose>--%>
                                    <%--<c:when test="${ticketFare.enablePvCode == 1}">--%>
                                        <!--<input type="checkbox" class="form-control" id="enablePvCode" name="enablePvCode" onclick="checkboxEnablePvCode(this)" value="1" checked/>-->
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                        <input type="checkbox" class="form-control" id="enablePvCode" name="enablePvCode" onclick="checkboxEnablePvCode(this)" value="0"/>
                                    <%--</c:otherwise>--%>
                                <%--</c:choose>--%> 
                            </div>
                            <label class="control-label text-right">PV Code</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <input id="pvCode" name="pvCode" type="text" class="form-control" maxlength="20" value="${ticketFare.pvCode}" readonly="">
                        </div>
                        <div class="col-xs-1 text-right"  style="width: 150px">
                           <label class="control-label text-right">Due Date </label>
                        </div>
                        <div class="col-xs-1"  style="width: 200px">
                            <div class='input-group date' id='InputDueDate'>
                                <c:set var="dueDate" value="${requestScope['dueDate']}" />
                                <fmt:parseDate value="${dueDate}" var="dueDate" pattern="yyyy-MM-dd" />
                                <fmt:formatDate value="${dueDate}" var="dueDate" pattern="dd-MM-yyyy" />
                                <input id="dueDate" name="dueDate"  type="text" 
                                   class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${dueDate}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12" style="padding-top: 20px"></div>    
                </div>
                <!----- Ticket Detail ----->
                <div class="panel panel-default" style="margin-top: -13px">
                    <div class="panel-heading" >
                        <h4 class="panel-title">Ticket Detail</h4>
                    </div> 
                    <div class="panel-body"  style="padding-right: 0px;" style="width: 100%" style="margin-top: -10px">
                        <div class="col-xs-12" style="margin-top: -10px">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Issue Date </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date' id='InputDatePicker'>
                                    <c:set var="issueDate" value="${requestScope['issueDate']}" />
                                    <fmt:parseDate value="${issueDate}" var="issueDate" pattern="yyyy-MM-dd" />
                                    <fmt:formatDate value="${issueDate}" var="issueDate" pattern="dd-MM-yyyy" />
                                    <input id="issueDate" name="issueDate"  type="text" 
                                       class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${issueDate}">
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
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Ticket Tax </label> 
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketTax" name="ticketTax" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.ticketTax}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12" style="margin-top: 3px">
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
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Agent Comm </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentCommission" name="agentCommission" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.agentCommission}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12" style="margin-top: 3px">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Inv Amount</label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">
                                    <input id="invoiceAmount" name="invoiceAmount" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.invAmount}">
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
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Diff Vat </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="diffVat" name="diffVat" type="text" class="form-control " style="text-align: right" onkeyup="insertCommas(this)" onkeypress="return isNumberKey(event)" value="${ticketFare.diffVat}">
                                    <span class="input-group-addon" id="caldiffvat"><span class=" glyphicon glyphicon-info-sign"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12" style="margin-top: 3px">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Ticket Comm Date </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date' >
                                    <c:set var="ticketCommDate" value="${requestScope['ticketCommDate']}" />
                                    <fmt:parseDate value="${ticketCommDate}" var="ticketCommDate" pattern="yyyy-MM-dd" />
                                    <fmt:formatDate value="${ticketCommDate}" var="ticketCommDate" pattern="dd-MM-yyyy" />
                                    <input id="ticketCommDate" name="ticketCommDate"  type="text" 
                                       class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${ticketCommDate}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Agent Comm Date </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date'>
                                    <c:set var="agentCommDate" value="${requestScope['agentCommDate']}" />
                                    <fmt:parseDate value="${agentCommDate}" var="agentCommDate" pattern="yyyy-MM-dd" />
                                    <fmt:formatDate value="${agentCommDate}" var="agentCommDate" pattern="dd-MM-yyyy" />
                                    <input id="agentCommDate" name="agentCommDate"  type="text" 
                                       class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${agentCommDate}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 110px"></div>
                            <div class="col-xs-1 text-right"  style="width: 50px">
                                <c:choose>
                                    <c:when test="${ticketFare.isWaitPay == 1}">
                                        <input type="checkbox" class="form-control" id="isWaitPay" name="isWaitPay" onclick="checkboxIsWaitPay(this)" value="1" checked/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" class="form-control" id="isWaitPay" name="isWaitPay" onclick="checkboxIsWaitPay(this)" value="0"/>
                                    </c:otherwise>
                                </c:choose> 
                            </div>
                            <div class="col-xs-1"  style="width: 100px">
                                <label class="control-label text-right">Wait Pay</label>
                            </div>        
                        </div>        
                        <div class="col-xs-12" style="margin-top: 3px">
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
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Agent Charge</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="airlineCharge" name="airlineCharge" type="text" maxlength="10" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" onkeypress="return isNumberKey(event)" value="${ticketFare.airlineCharge}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12" style="margin-top: 3px">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Remark </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <textarea rows="3" class="form-control" id="remark" maxlength="255" name="remark" style="width: 307%" >${ticketFare.remark}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12" style="margin-top: 3px">
                            <div class="col-xs-1 text-right" style="width: 125px">
                                
                                <!--<div class="col-xs-1 text-right" style="width: 50px">-->
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${ticketFare.enableInvNo == 1}">--%>
                                            <!--<input type="checkbox" class="form-control" id="enableInvNo" name="enableInvNo" onclick="checkboxEnableInvNo(this)" value="1" checked/>-->
                                        <%--</c:when>--%>
                                        <%--<c:otherwise>--%>
                                            <!--<input type="checkbox" class="form-control" id="enableInvNo" name="enableInvNo" onclick="checkboxEnableInvNo(this)" value="0"/>-->
                                        <%--</c:otherwise>--%>
                                    <%--</c:choose>--%> 
                                <!--</div>-->
                                <label class="control-label text-right">Inv No</label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px;margin-left: -3px">
                                <div class="input-group" id="invnopanel">
                                    <input id="invoiceNo" name="invoiceNo" type="text" class="form-control" maxlength="50" value="${requestScope['invoiceNo']}" disabled="disabled" onfocusout="searchInvoiceNoAjax(this)">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Inv Date </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">            
                                    <c:set var="invoiceDate" value="${requestScope['invoiceDate']}" />
                                    <fmt:parseDate value="${invoiceDate}" var="invDate" pattern="yyyy-MM-dd" />
                                    <fmt:formatDate value="${invDate}" var="invoiceDates" pattern="dd-MM-yyyy" />
                                    <input id="invoiceDate" name="invoiceDate" type="text" class="form-control" readonly="" value="${invoiceDates}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Credit</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="invoiceCredit" name="invoiceCredit" type="text" class="form-control" maxlength="30" readonly="" value="${requestScope['invoiceCredit']}">
                                    <input id="invoiceCreditValue" name="invoiceCreditValue" type="hidden" class="form-control" maxlength="5" readonly="" value="${requestScope['invoiceCreditValue']}">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12" style="margin-top: 3px;margin-bottom: -10px">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Owner</label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">
                                    <input id="owner" name="owner" type="text" class="form-control" maxlength="50" value="${ticketFare.owner}" readonly="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Routing</label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="routing" name="routing" type="text"  style="width: 555px" class="form-control" maxlength="255" readonly="" value="${ticketFare.routingDetail}">
                                </div>
                            </div>
                        </div>        
                    </div>
                </div>
                <!----- Detail ----->
                <div class="panel panel-default" style="margin-top: -13px" >
<!--                    <div class="panel-heading">
                        <h4 class="panel-title"></h4>
                    </div> -->
                    <div class="panel-body"  style="padding-right: 0px;" style="width: 100%" >
                        <div div class="col-sm-4" style="margin-top: -10px;margin-bottom: -10px">
                            <div class="row" style="padding-top: 3px">
                                <label class="col-lg-4 control-label text-right">Over Comm </label>
                                <div class="col-sm-6">
                                    <input id="overCommission" name="overCommission" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.overCommission}" tabindex="1"/>
                                </div>
                            </div>
                            <div class="row" style="padding-top: 3px">
                                <label class="col-lg-4 control-label text-right">Date </label>
                                    <div class="col-sm-6">
                                    <div class='input-group date'>
                                        <c:set var="overDate" value="${requestScope['overDate']}" />
                                        <fmt:parseDate value="${overDate}" var="overDate" pattern="yyyy-MM-dd" />
                                        <fmt:formatDate value="${overDate}" var="overDate" pattern="dd-MM-yyyy" />
                                        <input id="overDate" name="overDate"  type="text" 
                                           class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${overDate}" tabindex="2"/>
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </div>         
                        </div>                
                        <div div class="col-sm-4" style="margin-top: -10px;margin-bottom: -10px">
                            <div class="row" style="padding-top: 3px">
                                <label class="col-lg-4 control-label text-right">Little Comm </label>
                                <div class="col-sm-6">
                                    <input id="litterCommission" name="litterCommission" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.litterCommission}" tabindex="3"/>
                                </div>
                            </div>    
                            <div class="row" style="padding-top: 3px">
                                <label class="col-lg-4 control-label text-right">Date </label>
                                <div class="col-sm-6">                             
                                    <div class='input-group date'>
                                        <c:set var="litterDate" value="${requestScope['litterDate']}" />
                                        <fmt:parseDate value="${litterDate}" var="litterDate" pattern="yyyy-MM-dd" />
                                        <fmt:formatDate value="${litterDate}" var="litterDate" pattern="dd-MM-yyyy" />
                                        <input id="litterDate" name="litterDate"  type="text" 
                                           class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${litterDate}" tabindex="4"/>
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>                            
                            </div>
                        </div>
                        <div div class="col-sm-4" style="margin-top: -10px;margin-bottom: -10px">
                            <div class="row" style="padding-top: 3px">
                                <label class="col-lg-4 control-label text-right">Add Pay </label>
                                <div class="col-sm-6">
                                    <div class="input-group">                                    
                                        <input id="addPay" name="addPay" type="text" class="form-control numerical" style="text-align: right" onkeyup="insertCommas(this)" maxlength="16" onkeypress="return isNumberKey(event)" value="${ticketFare.addPay}" tabindex="5"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="padding-top: 3px">
                                <label class="col-lg-4 control-label text-right">Date </label>
                                <div class="col-sm-6"> 
                                <div class='input-group date'>
                                    <c:set var="addPayDate" value="${requestScope['addPayDate']}" />
                                    <fmt:parseDate value="${addPayDate}" var="addPayDate" pattern="yyyy-MM-dd" />
                                    <fmt:formatDate value="${addPayDate}" var="addPayDate" pattern="dd-MM-yyyy" />
                                    <input id="addPayDate" name="addPayDate"  type="text" 
                                       class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${addPayDate}" tabindex="6"/>
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row" style="margin-top: -12px">
                    <div class="col-xs-12">
                        <div class="col-xs-12 text-center" >
                            <input type="hidden" name="checkInvId" id="checkInvId" value="">
                            <input type="hidden" name="airlinecode" id="airlinecode" value="">
                            <input type="hidden" name="action" id="action" value="">
                            <input type="hidden" name="temp" id="temp" value="">
                            <input type="hidden" name="invno" id="invno" value="${requestScope['invNo']}"> 
                            <input type="hidden" name="refno" id="refno" value="${requestScope['refNo']}"> 
                            <input type="hidden" name="ticketFareFlag" id="ticketFareFlag" value="${requestScope['ticketFareFlag']}">
                            <input type="hidden" name="flightDetailFlag" id="flightDetailFlag" value="${requestScope['flightDetailFlag']}">
                            <input type="hidden" name="optionSave" id="optionSave" value="${requestScope['optionSave']}">
                            <button type="submit" id="ButtonSave" name="ButtonSave" onclick="saveAction(0)" class="btn btn-success"><i class="fa fa-save"></i> Save </button>
                            <button type="submit" id="ButtonSaveAndNew" name="ButtonSaveAndNew" onclick="saveAction(1)" class="btn btn-success"><i class="fa fa-save"></i> Save & New </button>
                            <button type="submit" id="ButtonNew" name="ButtonNew" class="btn btn-primary"><i class="fa fa-plus-circle"></i> New </button>
                        </div>
                    </div>
                </div>
                <!----- Refund Detail ----->
                <div class="panel panel-default" style="margin-top: 5px">
                    <div class="panel-heading">
                        <h4 class="panel-title">Refund Detail</h4>
                    </div> 
                    <div class="panel-body" style="margin-top: -10px;margin-bottom: -10px">
                        <table class="display" id="RefundDetailTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:5%;">No</th>
                                    <th style="width:20%;">Refund No</th>
                                    <th style="width:15%;">Airline Receive</th>
                                    <th style="width:10%;">Receive Date</th>
                                    <th style="width:15%;">Air Comm Receive</th>
                                    <th style="width:10%;">Pay Date</th>
                                    <th style="width:15%;">Agent Comm Receive</th>
                                    <th style="width:10%;">Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="table" items="${refundDetailList}" varStatus="dataStatus">
                                    <tr>
                                        <td align="center">${dataStatus.count}</td>
                                        <td >${table.refundAirticket.refundNo}</td>
                                        <td class="money">${table.receiveAirline}</td>
                                        <td align="center"><fmt:formatDate value="${table.refundAirticket.receiveDate}" var="receiveDate" pattern="dd-MM-yyyy" />${receiveDate}</td>
                                        <td class="money">${table.airComission}</td>
                                        <td align="center">${table.receiveDate}</td>
                                        <td class="money">${table.agentComission}</td>
                                        <td align="center">${table.expenseDate}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>             
                <!----- Invoice Detail ----->
                <div class="panel panel-default" style="margin-top: -12px">
                    <div class="panel-heading">
                        <h4 class="panel-title">Invoice Detail</h4>
                    </div> 
                    <div class="panel-body" style="margin-top: -10px;margin-bottom: -10px">
                        <table class="display" id="InvoiceDeailTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:5%;">Select Inv</th>
                                    <th style="width:5%;">No</th>
                                    <th style="width:15%;">Invoice No</th>
                                    <th style="width:15%;">Invoice Date</th>
                                    <th style="width:20%;">Department</th>
                                    <th style="width:15%;">Due Date</th>
                                    <th style="width:15%;">Sale Staff</th>
                                    <th style="width:15%;">Amount Invoice</th>
                                </tr>
                            </thead>
                            <tbody>
                               <c:forEach var="table" items="${invoiceDetailList}" varStatus="dataStatus">
                                    <tr>
                                        <input type="hidden" name="selectInvId" id="selectInvId" value="${table.invoiceId}">
                                        <input type="hidden" name="tableId${dataStatus.count}" id="tableId${dataStatus.count}" value="${table.id}"> 
                                        <input type="hidden" name="invoiceId${dataStatus.count}" id="invoiceId${dataStatus.count}" value="${table.invoiceId}">
                                        <td align="center">
                                            <c:set var="invRadio" value="" />
                                            <c:if test="${requestScope['selectInvIdTemp']  == table.invoiceId}">
                                                <c:set var="invRadio" value="checked" /> 
                                            </c:if>  
                                            <input type="radio" name="selectInvoiceDetail" id="selectInvoiceDetail${dataStatus.count}" value="${table.invoiceId}" ${invRadio} onclick="selectInvoiceDetailRadio('${dataStatus.count}')">
                                        </td>
                                        <td align="center">${dataStatus.count}</td>
                                        <td align="center">${table.invNo}</td>
                                        <td align="center"><fmt:formatDate value="${table.invDate}" var="invDate" pattern="dd-MM-yyyy" />${invDate}</td>
                                        <td align="center">${table.department}</td>
                                        <td align="center"><fmt:formatDate value="${table.dueDate}" var="dueDate" pattern="dd-MM-yyyy" />${dueDate}</td>
                                        <td align="center">${table.staffName}</td>
                                        <td class="money">${table.amountInvoice}</td>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div> 
                <!----- Receipt Detail ----->
                <div class="panel panel-default" style="margin-top: -12px">
                    <div class="panel-heading">
                        <h4 class="panel-title">Receipt Detail</h4>
                    </div> 
                    <div class="panel-body" style="margin-top: -10px;margin-bottom: -10px">
                        <table class="display" id="ReceiptDetailTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:5%;">No</th>
                                    <th style="width:20%;">Receipt No</th>
                                    <th style="width:20%;">Receipt Date</th>
                                    <th style="width:20%;">Invoice No</th>
                                    <th style="width:15%;">Receive Date</th>
                                    <th style="width:20%;">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="table" items="${receiptDetailList}" varStatus="dataStatus">
                                    <tr>
                                        <td align="center">${dataStatus.count}</td>
                                        <td align="center">${table.receiptNo}</td>
                                        <td align="center"><fmt:formatDate value="${table.receiptDate}" var="receiptDate" pattern="dd-MM-yyyy" />${receiptDate}</td>
                                        <td align="center">${table.invoiceNo}</td>
                                        <td align="center"><fmt:formatDate value="${table.receiveDate}" var="receiveDate" pattern="dd-MM-yyyy" />${receiveDate}</td>
                                        <td align="center">${table.mbillTypeStatus}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>  
                <!----- Flight Detail ----->
                <div id="flightPanel" class="panel panel-default hidden" style="margin-top: -12px">
                    <div class="panel-heading">
                        <h4 class="panel-title">Flight Detail</h4>
                    </div> 
                    <div class="panel-body" style="margin-top: -10px;margin-bottom: -10px">
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
                                        <td align="center"> <fmt:formatDate value="${table.departureDate}" var="departureDate" pattern="dd-MM-yyyy" />${departureDate}</td>
                                        <td align="center"> <fmt:formatDate value="${table.arrivalDate}" var="arrivalDate" pattern="dd-MM-yyyy" />${arrivalDate}</td>
                                </c:forEach>
                                <c:forEach var="table" items="${flightDetailFromAirticket}" varStatus="dataStatus">
                                    <tr>
                                        <td align="center"> <c:out value="${dataStatus.count}" /></td>
                                        <td align="center"> <c:out value="${table.airlineCode}" /></td>
                                        <td align="center"> <c:out value="${table.flightNo}" /></td>
                                        <td align="center"> <c:out value="${table.flightClass}" /></td>
                                        <td align="center"> <c:out value="${table.sourceCode}" /></td>
                                        <td align="center"> <c:out value="${table.desCode}" /></td>
                                        <td align="center"> <fmt:formatDate value="${table.departDate}" var="departDate" pattern="dd-MM-yyyy" />${departDate}</td>
                                        <td align="center"> <fmt:formatDate value="${table.arriveDate}" var="arriveDate" pattern="dd-MM-yyyy" />${arriveDate}</td>
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

<!--List Invno Modal-->
<div class="modal fade" id="ListInvnoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 55%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">List Ticket No</h4>
            </div>
            <div class="modal-body">
                <div style="text-align: right;padding-bottom: 12px"><i id="ajaxloadInvno"  class="fa fa-spinner fa-spin hidden"></i>Search : <input placeholder ="Inv No" type="text" style="width: 175px" id="filtercusInvno" name="filtercusInvno" /> </div> 
                <table class="display" id="ListInvnoTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width:10%;">Ref No</th>
                            <th style="width:10%;">Ticket</th>
                            <th style="width:15%;">Name</th>
                            <th style="width:10%;">Class</th>
                            <th style="width:10%;">Depart Date</th>
                            <th style="width:10%;">Fare</th>
                            <th style="width:10%;">Tax</th>
                            <th style="width:5%;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="ListInvnoModalOK" name="ListInvnoModalOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="ListInvnoModalClose" name="ListInvnoModalClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
<!--                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                            <th class="hidden">Fax</th>-->
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
<!--                            <td class="agent-addr hidden">${a.address}</td>
                            <td class="agent-tel hidden">${a.tel}</td>
                            <td class="agent-fax hidden">${a.fax}</td>-->
                        </tr>
                        <script>
                            agent.push({id: "${a.id}", code: "${a.code}", name: "${a.name}", 
                                        address: "${a.name}", tel: "${a.tel}", fax: "${a.fax}"});
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

<!--List Refno Modal-->
<div class="modal fade" id="ListTicketNoDuplicateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 40%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">List Ticket No</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="ListTicketNoDuplicateTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width:7%;">No</th>
                            <th style="width:30%;">Ticket No</th>
                            <th style="width:25%;">Invoice No</th>
                            <th style="width:25%;">Inv Amount</th>
                            <th style="width:13%;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="ListTicketNoDuplicateOK" name="ListTicketNoDuplicateOK" type="button"  onclick="searchTicketNoNew()" class="btn btn-success" data-dismiss="modal">NEW</button>
                <button id="ListTicketNoDuplicateClose" name="ListTicketNoDuplicateClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->

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
    setTicketDetailByInvTemp = [];
    setSelectTicketNoTemp = [];
    $(document).ready(function () {
        $("#ticketNo").focus();
        $('.datemask').mask('00-00-0000');
        $("#flightPanel").addClass('hidden');
        if($('#flightDetailFlag').val() == "notdummy"){
            $("#flightPanel").removeClass('hidden');  
        }

        if($('#ticketFareFlag').val() == "dummy"){
            $('#textAlertTicketNo').show();
        }
        
        if($('#refno').val() != ""){
            var refNo = $('#refno').val();
            $("#filtercus").val(refNo);
            FilterTicketList($("#filtercus").val());
        }
        
        if($('#ticketAirlineOther').val() === ''){
            $("#ticketAirlineOther").attr("disabled", "disabled");
        }else{
            $('#ticketAirline').val('OTHER');
            $("#ticketAirlineOther").removeAttr("disabled");
        }
//        if($('#refno').val() != ""){
//            var refNo = $('#refno').val();
//            $("#filtercus").val(refNo);
//            FilterTicketList($("#filtercus").val());
//        }
    
//        if(document.getElementById("enablePvCode").value == '1'){
//            $('#pvCode').prop('readonly', false);
//        }else{
//            $('#pvCode').prop('readonly', true);
//        }
        
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
        $('#ListInvnoTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });
        $('#ListTicketNoDuplicateTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });
        
        
        //on modal List Ticket
        $("#filtercusInvno").keyup(function (event) {
            if (event.keyCode === 13) {
                $("#invNoFilter").val($("#filtercusInvno").val());
                FilterTicketListByInvoice($("#filtercusInvno").val());
            }
        });
        
        $("#ticketNo").keyup(function (event) {
            if(event.keyCode === 13){
               searchTicketNo();
            }
        });
        
        setFormatCurrency();
        
        $( ".numerical" ).on('input', function() { 
            var value= $(this).val().replace(/[^0-9.,]*/g, '');
            value = value.replace(/\.{2,}/g, '.');
            value = value.replace(/\.,/g, ',');
            value = value.replace(/\,\./g, ',');
            value = value.replace(/\,{2,}/g, ',');
            value = value.replace(/\.[0-9]+\./g, '.');
            $(this).val(value)
        });
        
        //on modal List Ticket
        $("#filtercus").keyup(function (event) {
            if (event.keyCode === 13) {
                $("#refno").val($("#filtercus").val());
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
           // console.log("Name :"+ name);
            $("#agent_id,#agent_name,#agent_addr,#agent_tel").val(null);
            $.each(agent, function (key, value) {
                if (value.code.toUpperCase() === code && code.length > 1) {  
                    $("#agent_id").val(value.id);
                    $("#agent_name").val(value.name);
                    $("#agent_addr").val(value.address);
                    $("#agent_tel").val(value.tel);
                    $("#agent_user").val(value.code);
                }
                else if(value.name.toUpperCase() === name && name.length > 1){
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
            
        $("#caldiffvat").on('click', function () {
            var diffVat = document.getElementById('diffVat').value;
            //var whtax = document.getElementById('whtax').value;
            var Mvat    = document.getElementById('vat').value;
            var diffVat = replaceAll(",","",$('#diffVat').val()); 
            if (diffVat == ""){
                diffVat = 0;
            }

            var vat = parseFloat(diffVat); 
            var tax = parseFloat(Mvat);
            
            if(diffVat < 0) {
        //      Over Comm  =  (Diff vat * -1)
                var overcomm = (vat * (-1)) ;
                document.getElementById("overCommission").value = formatNumber(overcomm);
                document.getElementById("overDate").value = document.getElementById("invoiceDate").value ;
                
                document.getElementById("litterCommission").value = '';
                document.getElementById("litterDate").value = '' ;  
            }else if(diffVat > 0){
        //      Little Comm = (Diff vat ) *(100/(100+wh))
                var littlecomm = (vat) * (100/(100+tax));
                document.getElementById("litterCommission").value = formatNumber(littlecomm);
                document.getElementById("litterDate").value = document.getElementById("invoiceDate").value ; 
                document.getElementById("overCommission").value = '';
                document.getElementById("overDate").value = '' ;
            }
        });
            
        $("#invoiceAmount").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
            calculateVat();
            setDataCurrency();
            calculateSalePrice();
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
        $("#addPay").focusout(function(){
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
            calculateSalePrice();
        });
        $("#ticketCommission").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
            calculateVat();
        });
        
        $("#airlineCharge").focusout(function(){
            setFormatCurrency();
            setDataCurrency();
        });
        
        
        setDataCurrency();
        calculateSalePrice();
        if($('#optionSave').val() == "1"){
            clearData();
            $("#ticketNo").val("");
        }
        
        $('#invoiceCreditValue').ready(function () {
            setDuaDate();
        });
        
   });

function setDuaDate(){
    var ticketNo = document.getElementById('ticketNo').value;
    var dueDate = document.getElementById('dueDate').value;
    var creditValue = document.getElementById('invoiceCreditValue').value;
    var invoiceDate = document.getElementById('invoiceDate').value;
    if((ticketNo !== '') && (dueDate === '') && (creditValue !== '0') && (creditValue !== '')){
        var invdate = invoiceDate.split('-');
        var creval = parseInt(creditValue);
        
        var billdate = parseInt(30/creval);
        var billdatelist = [];
        for(var i=0;i<billdate;i++){
            billdatelist.push((creval*(i+1)));
        }
        //Year
        var yshow = parseInt(invdate[0]);
       
        //Month
        var month28 = [2];
        var month30 = [4,6,9,11];
        var month31 = [1,3,5,7,8,10,12];      
        var month = parseInt(invdate[1]);
        var m28 = 0;
        var m30 = 0;
        var m31 = 0;
        for(var i=0;i<month28.length;i++){
            if(month === month28[i]){
                m28++;
            }
        }
        for(var i=0;i<month30.length;i++){
            if(month === month30[i]){
                m30++;
            }
        }
        for(var i=0;i<month31.length;i++){
            if(month === month31[i]){
                m31++;
            }
        }
        
        //Day
        var day = parseInt(invdate[2]);
        if(m28>0){
            day = creval+day;
            if((yshow%4) === 0){
                if(day>29){
                    day = day%29;
                    month = month+1;
                }
            }else{
                if(day>28){
                    day = day%28;
                    month = month+1;
                }
            }
        }
        if(m30>0){
            day = creval+day;
            if(day>30){
                day = day%30;
                month = month+1;
            }
        }
        if(m31>0){
            day = creval+day;
            if(day>31){
                day = day%31;
                if(month !== 12){
                    month = month+1;
                } else {
                    month = 1;
                    yshow = parseInt(invdate[0])+1;
                }    
            }
        }

        //Month Show
        var mshow = 0;
        for(var i=0;i<month28.length;i++){
            if(month === month28[i]){
                mshow = month28[i];
            }
        }
        for(var i=0;i<month30.length;i++){
            if(month === month30[i]){
                mshow = month30[i];
            }
        }
        for(var i=0;i<month31.length;i++){
            if(month === month31[i]){
                mshow = month31[i];
            }
        }
        
        //Day Show    
        var dshow = "";
        for(var i=0;i<billdatelist.length;i++){
            if(i === 0){
                if((day>=1) && (day<=billdatelist[i])){
                    dshow = billdatelist[i];
                }
            }
            if((day>billdatelist[i-1]) && (day<=billdatelist[i])){
                dshow = billdatelist[i];
            }
        }
        
        //String Zero
        var point1 = "";
        var point2 = "";
        if((mshow.toString()).length === 1){
            point1 = "0";
        }
        if((dshow.toString()).length === 1){
            point2 = "0";
        }
        
        //Result
        document.getElementById('dueDate').value =  point2+dshow+"-" +point1+mshow+ "-" + yshow;
    }
}
    
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
    
    var addPay = replaceAll(",","",$('#addPay').val()); 
    if (addPay == ""){
        addPay = 0;
    }
    addPay = parseFloat(addPay); 
    document.getElementById("addPay").value = formatNumber(addPay); 
    
    var airlineCharge = replaceAll(",","",$('#airlineCharge').val()); 
    if (airlineCharge == ""){
        airlineCharge = 0;
    }
    airlineCharge = parseFloat(airlineCharge); 
    document.getElementById("airlineCharge").value = formatNumber(airlineCharge);    
    
    var invoiceAmount = replaceAll(",","",$('#invoiceAmount').val()); 
    if (invoiceAmount == ""){
        invoiceAmount = 0;
    }
    invoiceAmount = parseFloat(invoiceAmount); 
    document.getElementById("invoiceAmount").value = formatNumber(invoiceAmount);    
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
    
    var addPay = replaceAll(",","",$('#addPay').val()); 
    if (addPay == "" || addPay == 0){
        document.getElementById("addPay").value = ""; 
    }
    
    var airlineCharge = replaceAll(",","",$('#airlineCharge').val()); 
    if (airlineCharge == "" || airlineCharge == 0){
        document.getElementById("airlineCharge").value = ""; 
    }
    
    var invoiceAmount = replaceAll(",","",$('#invoiceAmount').val()); 
    if (invoiceAmount == "" || invoiceAmount == 0){
        document.getElementById("invoiceAmount").value = ""; 
    }
}

function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode != 45 && charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
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
    var addPay = document.getElementById('addPay');
    addPay.value = $("#addPay").val();
    var overDate = document.getElementById('overDate');
    overDate.value = $("#overDate").val();
    var litterDate = document.getElementById('litterDate');
    litterDate.value = $("#litterDate").val();
    var addPayDate = document.getElementById('addPayDate');
    addPayDate.value = $("#addPayDate").val();
    var pvCode = document.getElementById('pvCode');
    pvCode.value = $("#pvCode").val(); 
    var pvType = document.getElementById('pvType');
    pvType.value = $("#pvType").val(); 
    var optionSave = document.getElementById('optionSave');
    optionSave.value = $("#optionSave").val(); 
    
    var countRow = document.getElementById('countRow');
    countRow.value = $('#InvoiceDeailTable tr').length;
    
    var ticketCommDate = document.getElementById('ticketCommDate');
    ticketCommDate.value = $("#ticketCommDate").val();
    var agentCommDate = document.getElementById('agentCommDate');
    agentCommDate.value = $("#agentCommDate").val();
}

function searchTicketNo() {
    var ticketnotempsearch = $("#ticketNoTempSearch").val();
    var ticketNo = $("#ticketNo").val();
    if(ticketnotempsearch !== ticketNo){
        $("#ticketId").val("");
    }
    FilterCheckTicketNoList(ticketNo);
    
//    var ticketnopanel = $("#ticketnopanel").val();
//    if(ticketNo == ""){
//        if(!$('#ticketnopanel').hasClass('has-feedback')) {
//            $('#ticketnopanel').addClass('has-feedback');
//        }
//        $('#ticketnopanel').removeClass('has-success');
//        $('#ticketnopanel').addClass('has-error');
//    }
//    else{
//        $('#ticketNo').focus();
//        var action = document.getElementById('action');
//        action.value = 'search';
//        var ticketNo = document.getElementById('ticketNo');
//        ticketNo.value = $("#ticketNo").val();
//        var ticketId = document.getElementById('ticketId');
//        ticketId.value = $("#ticketId").val();
//        document.getElementById('AddTicketFareForm').submit();
//    }
}

function FilterCheckTicketNoList(ticketNo) {
    var servletName = 'TicketFareAirlineServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&ticketNo=' + ticketNo +
            '&type=' + 'checkTicketNo';
    CallFilterCheckTicketNoList(param);
}

function CallFilterCheckTicketNoList(param) {
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
                        $('#ListTicketNoDuplicateTable').dataTable().fnClearTable();
                        $('#ListTicketNoDuplicateTable').dataTable().fnDestroy();
                        $('#ListTicketNoDuplicateTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                        $("#ajaxload").addClass("hidden");
//                        $("#isTempTicket").val("1"); 
                        searchTicketNoAction();
                    }else{
                        $('#ListTicketNoDuplicateTable').dataTable().fnClearTable();
                        $('#ListTicketNoDuplicateTable').dataTable().fnDestroy();
                        $("#ListTicketNoDuplicateTable tbody").empty().append(msg);
                        $('#ListTicketNoDuplicateTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength":10
                        });
                        $('#ListTicketNoDuplicateModal').modal('show');
                        $("#ajaxload").addClass("hidden");
                    }
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


function FilterTicketListByInvoice(invNo) {
    var servletName = 'TicketFareAirlineServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&invNo=' + invNo +
            '&type=' + 'getTicketListByInvno';
    CallFilterAjaxByInvoice(param);
}

function CallFilterAjaxByInvoice(param) {
    var url = 'AJAXServlet';
    $("#ajaxloadInvno").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                try { 
                    if(msg == "null"){
                        $('#ListInvnoTable').dataTable().fnClearTable();
                        $('#ListInvnoTable').dataTable().fnDestroy();
                        $('#ListInvnoTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                    }else{
                        $('#ListInvnoTable').dataTable().fnClearTable();
                        $('#ListInvnoTable').dataTable().fnDestroy();
                        $("#ListInvnoTable tbody").empty().append(msg);
                        $('#ListInvnoTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength":10
                        });
                    }

                     $("#ajaxloadInvno").addClass("hidden");
                } catch (e) {
                    alert(e);
                }

            }, error: function (msg) {
                 $("#ajaxloadInvno").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}   

function selectTicketNo(){
    clearData();
    $('#AddTicketByRefModal').modal('hide');
    var invno = '';
    $.each(setTicketDetailTemp, function (key, value) {
        $("#ticketNo").val(value.no);
        invno = value.invno;
        $('#AddTicketFareForm').bootstrapValidator('revalidateField', 'ticketNo');
        searchTicketNo();
    });
    setFormatCurrency();
    setDataCurrency();
    $("#ListRefnoModal").modal('hide');
    if(invno === ''){
        $("#ListRefnoModal").modal('hide');
    }else{
        $("#ListInvnoModal").modal('hide');
    }
}

function setTicketFareDetail(ticket,refno,invno,masterid){
    setTicketDetailTemp.push({no:ticket,refno:refno,invno:invno});
    $("#masterId").val(masterid);
    var ticketNo = $("#ticketNo").val();
    if(ticketNo != ""){
        $("#ticketNoAlert").text('Are you sure to edit Ticket No. ?');
        $('#AddTicketByRefModal').modal('show');
    }else{
        $("#ticketNo").val(ticket);
        $("#ticketId").val("");
        $("#refno").val(refno);
        $("#invno").val(invno);
        searchTicketNo();
    }
    setFormatCurrency();
    setDataCurrency();
    
    if(invno === ''){
        $("#ListRefnoModal").modal('hide');
    }else{
        $("#ListInvnoModal").modal('hide');
    }
}
function clearData(){
    
//    $("#ticketNo").val("");
    $("#ticketType").val("");
    $("#ticketRouting").val("");
    $("#ticketAirline").val("");
    $("#ticketAirlineOther").val("");
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
    $("#addPay").val("");
    $("#overDate").val("");
    $("#litterDate").val("");
    $("#addPayDate").val("");
    $("#ticketCommDate").val("");
    $("#agentCommDate").val("");
    $("#ticketId").val("");
    $("#invoiceId").val("");
    $("#invoiceDetailTableId").val("");
    $("#isTempTicket").val("");
    
    $('#FlightDeailTable').dataTable().fnClearTable();
    $('#FlightDeailTable').dataTable().fnDestroy();
    $("#FlightDeailTable tbody").empty();
    
    $('#RefundDetailTable').dataTable().fnClearTable();
    $('#RefundDetailTable').dataTable().fnDestroy();
    $("#RefundDetailTable tbody").empty();
    
    $('#InvoiceDeailTable').dataTable().fnClearTable();
    $('#InvoiceDeailTable').dataTable().fnDestroy();
    $("#InvoiceDeailTable tbody").empty();
    
    $('#ReceiptDetailTable').dataTable().fnClearTable();
    $('#ReceiptDetailTable').dataTable().fnDestroy();
    $("#ReceiptDetailTable tbody").empty();
    
    
    $("#invoiceNo").val("");
    $("#invoiceDate").val("");
    $("#invoiceCredit").val("");
    $("#owner").val("");
    $("#routing").val("");
    $("#ticketNo").val("");
    $("#dueDate").val("");
    $("#airlineCharge").val("");
    $("#isWaitPay").val("0");
     document.getElementById("isWaitPay").checked = false;
    $("#flightPanel").addClass('hidden');
}

function calculateSalePrice(){
    var invoiceAmount = replaceAll(",","",$('#invoiceAmount').val()); 
    if (invoiceAmount == ""){
        invoiceAmount = 0;
    }
    var agentCommission = replaceAll(",","",$('#agentCommission').val()); 
    if (agentCommission == ""){
        agentCommission = 0;
    }
    var inv = parseFloat(invoiceAmount); 
    var agentcom = parseFloat(agentCommission);
    var sale = inv - agentcom;
    document.getElementById("salePrice").value = formatNumber(sale);
}
function calculateVat() {
//Diff Vat = Inv Amount - Fare - Tax - Ins (ค่า Diff vat สามารถติดลบได้)
//Diffvat = INV Amount – Fare – Tax – Insurance – Ticcom
    var ticketType = document.getElementById('ticketType').value;
    var ticketNo = document.getElementById('ticketNo').value;
    
    if(ticketType == "A" && ticketNo != ""){
        var aircode = ticketNo.substr(0,3);
        getMAirlineAgent(aircode);
    }
    
    if(ticketType == "B" || ticketType == "TI"){
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
        
        var ticketCommission = replaceAll(",","",$('#ticketCommission').val()); 
        if (ticketCommission == ""){
            ticketCommission = 0;
        }
       var inv = parseFloat(invAmount); 
       var fare = parseFloat(ticketfare);
       var tax = parseFloat(tickettax);
       var ins = parseFloat(ticketins);
       var ticketComm = parseFloat(ticketCommission);
       var diffvat = inv - fare - tax - ticketComm;
       document.getElementById("diffVat").value = formatNumber(diffvat);
    } else if(ticketType == "D" || ticketType == "TD" || ticketType == "A"){
        var invAmount = replaceAll(",","",$('#invoiceAmount').val()); 
        if (invAmount == ""){
            invAmount = 0;
        }
        var ticketfare = replaceAll(",","",$('#ticketFare').val()); 
        if (ticketfare == ""){
            ticketfare = 0;
        }
        var ticketins = replaceAll(",","",$('#ticketIns').val()); 
        if (ticketins == ""){
            ticketins = 0;
        }
      
        var tickettax = replaceAll(",","",$('#ticketTax').val()); 
        if (tickettax == ""){
            tickettax = 0;
        }
        var tax = parseFloat(tickettax);
        var inv = parseFloat(invAmount); 
        var fare = parseFloat(ticketfare);
        var ins = parseFloat(ticketins);
        var diffvat = inv - fare - tax;
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
    if(nField.value !== '-'){
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
}

function getMAirlineAgent(airlinecode) {
    document.getElementById('airlinecode').value = airlinecode;
    var servletName = 'TicketFareAirlineServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&airlineCode=' + airlinecode +
            '&type=' + 'getMAirlineAgentByAirCode';
    CallGetMAirlineAgent(param);
}

function CallGetMAirlineAgent(param) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
               document.getElementById('ticketAirline').value = msg;
            }, error: function (msg) {
                 $("#ajaxloadInvno").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}   


function checkboxIsWaitPay(e) {
    if(e.checked) {
        document.getElementById("isWaitPay").value = "1";
    }else{
        document.getElementById("isWaitPay").value = "0";
    }
}

function checkboxEnablePvCode(e) {
    if(e.checked) {
        document.getElementById("enablePvCode").value = "1";
//        $("#pvCode").attr("readonly", false);
        $('#pvCode').prop('readonly', false);
    }else{
        document.getElementById("enablePvCode").value = "0";
//        $("#pvCode").attr('readonly', true);
        $('#pvCode').prop('readonly', true);
    }
}
function checkboxEnableInvNo(e) {
    $("#invnopanel").removeClass("has-error");
    $("#invnopanel").removeClass("has-success");
    $("#ButtonSave").removeClass("disabled");
    $("#ButtonSaveAndNew").removeClass("disabled");
    if(e.checked) {
        document.getElementById("enableInvNo").value = "1";
        $("#invoiceNo").removeAttr("disabled");
    }else{
        document.getElementById("enableInvNo").value = "0";
        $("#invoiceNo").attr("disabled", "disabled");
        $("#invoiceNo").val("");
    }
}
function setSelectTicketNoDetail(ticketno,ticketid,invamount){
    $('#ListTicketNoDuplicateModal').modal('hide');
    setSelectTicketNoTemp.push({no:ticketno,id:ticketid,amount:invamount});
    if(ticketid != "" ){
        $.each(setSelectTicketNoTemp, function (key, value) {
            $("#ticketNo").val(value.no);
            $("#ticketId").val(value.id);
            $("#invoiceAmount").val(value.amount);
            $('#AddTicketFareForm').bootstrapValidator('revalidateField', 'ticketNo');
            searchTicketNoAction();
        });
    }else{
        $("#ticketNo").val(ticketno);
        $("#ticketId").val(ticketid);
        $("#invoiceAmount").val(invamount);
        searchTicketNoAction();
    }
}

function searchTicketNoAction(){
    var ticketnopanel = $("#ticketnopanel").val();
    var ticketNumber = $("#ticketNo").val(); 
    if(ticketNumber == ""){
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
        var ticketId = document.getElementById('ticketId');
        ticketId.value = $("#ticketId").val();
        document.getElementById('AddTicketFareForm').submit();
    }
}

function searchTicketNoNew(){
    var ticketNo = document.getElementById('ticketNo');
    ticketNo.value = $("#ticketNo").val();
    var action = document.getElementById('action');
    action.value = 'searchTicketnoNew';   
    document.getElementById('AddTicketFareForm').submit();    
}

function selectInvoiceDetailRadio(row){
   document.getElementById('selectInvId').value = $("#selectInvoiceDetail"+row).val();
}    

function checkAirlineSelected(){
    var air = $("#ticketAirline").val(); 
    if(air === 'OTHER'){
        $("#ticketAirlineOther").removeAttr("disabled");
    }else{
        $("#ticketAirlineOther").val("");
        $("#ticketAirlineOther").attr("disabled", "disabled");
    }
}

function searchInvoiceNoAjax(invno){
    var invoiceNo = invno.value;
    if(invoiceNo !== ''){
        var servletName = 'TicketFareAirlineServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&invNo=' + invoiceNo +
                '&type=' + 'searchInvoiceNo';
        CallAjaxSearchInvoice(param);
    }else{
        $("#invnopanel").removeClass("has-error");
        $("#invnopanel").removeClass("has-success");
        $("#ButtonSave").removeClass("disabled");
        $("#ButtonSaveAndNew").removeClass("disabled");
    }
}

function CallAjaxSearchInvoice(param) {
        var url = 'AJAXServlet';
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function (msg) {
                    try {
                        if(msg === 'null'){
                            $("#invnopanel").removeClass("has-success");
                            $("#invnopanel").addClass("has-error");  
                            $("#ButtonSave").addClass("disabled");
                            $("#ButtonSaveAndNew").addClass("disabled");
                        }else{
                            $("#checkInvId").val(msg);
                            $("#invnopanel").removeClass("has-error");
                            $("#invnopanel").addClass("has-success");
                            $("#ButtonSave").removeClass("disabled");
                            $("#ButtonSaveAndNew").removeClass("disabled");
                        }
                    } catch (e) {
                        alert(e);
                    }

                }, error: function (msg) {
                    
                }
            });
        } catch (e) {
            alert(e);
        }
    }
</script>
  