<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/AddTicketFare.js"></script> 
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<c:set var="ticketTypeList" value="${requestScope['ticketTypeList']}" />
<c:set var="ticketRoutingList" value="${requestScope['ticketRoutingList']}" />
<c:set var="ticketByList" value="${requestScope['ticketByList']}" />--%>
<c:set var="airlineList" value="${requestScope['airlineList']}" />
<c:set var="ticketFare" value="${requestScope['ticketFare']}" />
<c:set var="agent" value="${requestScope['Agent']}" />
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
        <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
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
                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Ticket&nbsp;No&nbsp;</label>
                        </div>
                        <div class="col-xs-1 form-group" style="width: 200px">
                            <div class="input-group">
                                <input id="ticketNo" name="ticketNo" type="text" class="form-control" value="${ticketFare.ticketNo}">
                            </div>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 100px">
                            <button style="height:34px" type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="searchAction();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search</button>

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
                            <select id="ticketType" name="ticketType" class="form-control selectize">
                                <option value="B">BSP</option>
                                <option value="D">DOMESTIC</option>
                                <option value="A">AGENT</option>
                            </select>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Ticket Routing </label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select id="ticketRounting" name="ticketRounting" class="form-control selectize" >
                                <option value="I">INTER</option>
                                <option value="D">DOMESTIC</option>
                                <option value="C">CANCEL</option>
                            </select>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Airline&nbsp;</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select name="ticketAirline" id="ticketAirline" class="form-control">
                                <c:forEach var="table" items="${airlineList}" >
                                    <c:set var="select" value="" />
                                    <c:set var="selectedId" value="${MAirline.MAirlineAgent.id}" />
                                    <c:if test="${table.id == selectedId}">
                                        <c:set var="select" value="selected" />
                                    </c:if>
                                    <option value="${table.id}" ${select}>${table.name}</option> 
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label">
                                <input type="checkbox" id="passengerCheckbox" name="passengerCheckbox" data-label="passengerCheckbox" value=""/>&nbsp;Passenger&nbsp; 
                            </label>
                        </div>
                        <div class="col-xs-1 form-group" style="width: 200px">
                            <div class="input-group">
                                <input id="passenger" name="passenger" type="text" class="form-control" value="">
                            </div>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Ticket&nbsp;Buy&nbsp;</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select name="ticketBuy" id="ticketBuy" class="form-control">
                                <option value="C">IN</option>
                                <option value="O">OUT</option>
                            </select>
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
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Ticket Fare </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketFare" name="ticketFare" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Ticket Tax </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketTax" name="ticketTax" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Ticket Ins </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketIns" name="ticketIns" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Ticket Comm </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketCommission" name="ticketCommission" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Agent Comm </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentCommission" name="agentCommission" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Sale Price </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="salePrice" name="salePrice" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Diff Vat </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="diffVat" name="diffVat" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
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
                                <input type="text" class="form-control" id="agent_name" name="agent_name" value="${SelectedAgent.name}" readonly=""
                                    data-bv-notempty="true" data-bv-notempty-message="agent empty !">
                            </div>
                        </div>
                        <div class="col-xs-10 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Remark </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <textarea rows="3" class="form-control" id="remark" name="remark" style="width: 307%"></textarea>  
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
                                    <input id="overCommission" name="overCommission" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Add Pay </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="addPay" name="addPay" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Agent Comm Pay </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentComPay" name="agentComPay" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
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
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date'>
                                    <input id="addPayDate" name="addPayDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date'>
                                    <input id="agentPayDate" name="agentPayDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
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
                                    <input id="litterCommission" name="litterCommission" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Dec Pay </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="decPay" name="decPay" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Agent Comm Receive </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentComReceive" name="agentComReceive" type="text" class="form-control" onkeypress="return isNumberKey(event)" value="">
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
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date'>
                                    <input id="decPayDate" name="decPayDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date'>
                                    <input id="agentReceiveDate" name="agentReceiveDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
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
                            <button type="submit" id="ButtonSave" name="ButtonSave" onclick="saveAction()" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                            <button type="submit" id="ButtonSaveAndNew" name="ButtonSaveAndNew" class="btn btn-success"><i class="fa fa-save"></i> Save & New</button>
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
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">Flight Detail</h4>
                    </div> 
                    <div class="panel-body">
                        <table class="display" id="FlightDeailTable">
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
                                
                            </tbody>
                        </table>
                    </div>
                </div> 
                    
                    
                    
            </form>                
        </div>
        
        
<!--List Refno Modal-->
<div class="modal fade" id="ListRefnoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">List Ticket No</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="ListRefnoTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Ticket</th>
                            <th>Name</th>
                            <th>Class</th>
                            <th>Depart Date</th>
                            <th>Fare</th>
                            <th>Tax</th>
                            <th>Action</th>
                            <th class="hidden"></th>
                            <th class="hidden"></th>
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

<script type="text/javascript">
    $(document).ready(function () {
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
   });
   
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
       return false;
    }
    
    return true;
}

function saveAction() {
    var action = document.getElementById('action');
    action.value = 'save';
    var ticketNo = document.getElementById('ticketNo');
    ticketNo.value = $("#ticketNo").val();
    var ticketType = document.getElementById('ticketType');
    ticketType.value = $("#ticketType").val();
    var ticketBuy = document.getElementById('ticketBuy');
    ticketBuy.value = $("#ticketBuy").val();
    var ticketRounting = document.getElementById('ticketRounting');
    ticketRounting.value = $("#ticketRounting").val();
    var ticketAirline = document.getElementById('ticketAirline');
    ticketAirline.value = $("#ticketAirline").val();

    document.getElementById('AddTicketFareForm').submit();
    
}
</script>
