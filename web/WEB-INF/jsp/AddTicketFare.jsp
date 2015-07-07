<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/AddTicketFare.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
                                <input id="ticketNo" name="ticketNo" type="text" class="form-control" value="">
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
                            <select id="inputTicketType" name="inputTicketType" class="form-control selectize">
                                <option value="">---Ticket Type---</option>

                            </select>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Ticket Routing </label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select id="inputTicketRouting" name="inputTicketRouting" class="form-control selectize">
                                <option value="">---Ticket Routing---</option>

                            </select>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Airline&nbsp;</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select id="inputAirline" name="inputAirline" class="form-control selectize">
                                <option value="">---Airline---</option>

                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label">
                                <input type="checkbox" id="passengerCheck" name="passengerCheck"/>&nbsp;Passenger&nbsp; 
                            </label>
                            <input type="hidden" id="passengerCheckbox" value=""/>
    <!--                        <label class="control-label text-right">Passenger&nbsp;:</label>-->
                        </div>
                        <div class="col-xs-1 form-group" style="width: 200px">
                            <div class="input-group">
                                <input id="passenger" name="passenger" type="text" class="form-control" value="">
                            </div>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 150px">
                            <label class="control-label text-right">Ticket&nbsp;By&nbsp;</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <select id="inputTicketBy" name="inputTicketBy" class="form-control selectize">
                                <option value="">---Ticket By---</option>

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
                                    <input id="inputIssueDate" name="inputIssueDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Ticket Fare </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketFare" name="ticketFare" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Ticket Tax </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketTax" name="ticketTax" type="text" class="form-control" value="">
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Ticket Ins </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketIns" name="ticketIns" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Ticket Comm </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="ticketComm" name="ticketComm" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Agent Comm </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentComm" name="agentComm" type="text" class="form-control" value="">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Sale Price </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="salePrice" name="salePrice" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Diff Vat </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="diffVat" name="diffVat" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Agent Name </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentName" name="agentName" type="text" class="form-control" value="">
                                </div>
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
                                    <input id="overComm" name="overComm" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Add Pay </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="addPay" name="addPay" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Agent Comm Pay </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentCommPay" name="agentCommPay" type="text" class="form-control" value="">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date'>
                                    <input id="inputOverCommDate" name="inputOverCommDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date'>
                                    <input id="inputAddPayDate" name="inputAddPayDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date'>
                                    <input id="inputAgentCommPayDate" name="inputAgentCommPayDate"  type="text" 
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
                                    <input id="littleComm" name="littleComm" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Dec Pay </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="decPay" name="decPay" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Agent Comm Receive </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentCommReceive" name="agentCommReceive" type="text" class="form-control" value="">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 121px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date'>
                                    <input id="inputLittleCommDate" name="inputLittleCommDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date'>
                                    <input id="inputDecPayDate" name="inputDecPayDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 185px">
                                <label class="control-label text-right">Date </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class='input-group date'>
                                    <input id="inputAgentCommReceiveDate" name="inputAgentCommReceiveDate"  type="text" 
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
                            <button type="submit" id="ButtonSave" name="ButtonSave" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
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
