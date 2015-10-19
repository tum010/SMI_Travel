<%-- 
    Document   : TicketSummaryCommission
    Created on : Oct 12, 2015, 9:20:12 AM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/TicketSummaryCommission.js"></script> 
<link href="css/jquery-ui.css" rel="stylesheet">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="customerAgentList" value="${requestScope['customerAgent']}" />

<c:set var="airlineCodeList" value="${requestScope['airlineCodeList']}" />
<c:set var="termPayList" value="${requestScope['termPayList']}" />
<c:set var="userList" value="${requestScope['userList']}" />
<c:set var="listAgent" value="${requestScope['listAgent']}" />


<section class="content-header"  >
    <h4>
        <b>Report : Air Ticket </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Ticket Summary Commission </a></li>
    </ol>
</section>
<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/AirticketReportMenu.html'"></div>
            </div>

            <div class="form-group">
                <div class="col-md-6">
                    <h3>List Summary Commission</h3>
                </div>
            </div>
            
            <div class="col-md-10" >
                <form action="TicketFareSummaryAirline.smi" method="post" id="TicketFareSummaryAirlineForm" name="TicketFareSummaryAirlineForm" role="form">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" >
                                <label class="col-md-6 control-label text-right">Invoice Date<font style="color: red"></font></label>
                                <div class="col-md-3">  
                                    <div class="form-group" id="fromdatepanel">
                                        <div class='input-group date fromdate' id='DateFrom'>
                                            <input type='text' id="invoiceFromDate" name="invoiceFromDate" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">  
                                    <div class="form-group" id="todatepanel">
                                        <div class='input-group date todate' id='DateTo'>
                                            <input type='text' id="invoiceToDate" name="invoiceToDate"  class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right">Issue Date<font style="color: red"></font></label>
                                <div class="col-md-3">  
                                    <div class="form-group" id="issuefromdatepanel">
                                        <div class='input-group date issuefromdate' id='DateFromIssue'>
                                            <input type='text' id="issueFrom" name="issueFrom" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">  
                                    <div class="form-group" id="issuetodatepanel">
                                        <div class='input-group date issuetodate' id='DateToIssue'>
                                            <input type='text' id="issueTo" name="issueTo"  class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Type Routing</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="typeRouting" id="typeRouting"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="I" >INTER</option>
                                            <option value="D" >DOMESTIC</option>
                                            <option value="C" >CANCEL</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Routing Detail</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <input type='text' id="routingDetail" name="routingDetail" class="form-control"/>
                                    </div>  
                                </div>   
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Air</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="airlineCode" id="airlineCode"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:forEach var="table" items="${airlineCodeList}" >
                                                <c:set var="select" value=""/>
                                                <option value="${table.code3Letter}" ${select}>${table.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Agent Name</label>
                                <div class="col-md-3 form-group">  
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="agentId" name="agentId" value=""/>
                                        <input type="text" class="form-control" id="agentCode" name="agentCode" value="" />
                                        <span class="input-group-addon" id="agent_modal"  data-toggle="modal" data-target="#AgentModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="agentName" name="agentName" value="" readonly="">
                                </div>
                            </div> 
                        </div>
                    </div>
<!--                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right">Agent Comm Date<font style="color: red"></font></label>
                                <div class="col-md-3">  
                                    <div class="form-group" id="agentcomfromdatepanel">
                                        <div class='input-group date agentcomfromdate' id='DateFromAgentCom'>
                                            <input type='text' id="agentcomFrom" name="agentcomFrom" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">  
                                    <div class="form-group" id="agentcomtodatepanel">
                                        <div class='input-group date agentcomtodate' id='DateToAgentCom'>
                                            <input type='text' id="agentcomTo" name="agentcomTo"  class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right">Ticket Comm Date<font style="color: red"></font></label>
                                <div class="col-md-3">  
                                    <div class="form-group" id="ticketcomfromdatepanel">
                                        <div class='input-group date ticketcomfromdate' id='DateFromTicketCom'>
                                            <input type='text' id="ticketcomFrom" name="ticketcomFrom" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">  
                                    <div class="form-group" id="ticketcomtodatepanel">
                                        <div class='input-group date ticketcomtodate' id='DateToTicketCom'>
                                            <input type='text' id="ticketcomTo" name="ticketcomTo"  class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>-->
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right">Over Date<font style="color: red"></font></label>
                                <div class="col-md-3">  
                                    <div class="form-group" id="overfromdatepanel">
                                        <div class='input-group date overfromdate' id='DateFromOver'>
                                            <input type='text' id="overFrom" name="overFrom" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">  
                                    <div class="form-group" id="overtodatepanel">
                                        <div class='input-group date overtodate' id='DateToOver'>
                                            <input type='text' id="overTo" name="overTo"  class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right">Little Date<font style="color: red"></font></label>
                                <div class="col-md-3">  
                                    <div class="form-group" id="littlefromdatepanel">
                                        <div class='input-group date littlefromdate' id='DateFromLittle'>
                                            <input type='text' id="littleFrom" name="littleFrom" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">  
                                    <div class="form-group" id="littletodatepanel">
                                        <div class='input-group date littletodate' id='DateToLittle'>
                                            <input type='text' id="littleTo" name="littleTo"  class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right">Agent Comm Receive Date<font style="color: red"></font></label>
                                <div class="col-md-3">  
                                    <div class="form-group" id="agentcomreceivefromdatepanel">
                                        <div class='input-group date agentcomreceivefromdate' id='DateFromAgentComReceive'>
                                            <input type='text' id="agentcomreceiveFrom" name="agentcomreceiveFrom" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">  
                                    <div class="form-group" id="agentcomreceivetodatepanel">
                                        <div class='input-group date agentcomreceivetodate' id='DateToAgentComReceive'>
                                            <input type='text' id="agentcomreceiveTo" name="agentcomreceiveTo"  class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right">Comm Refund Date<font style="color: red"></font></label>
                                <div class="col-md-3">  
                                    <div class="form-group" id="comrefundfromdatepanel">
                                        <div class='input-group date comrefundfromdate' id='DateFromComRefund'>
                                            <input type='text' id="comrefundFrom" name="comrefundFrom" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">  
                                    <div class="form-group" id="comrefundtodatepanel">
                                        <div class='input-group date comrefundtodate' id='DateToComRefund'>
                                            <input type='text' id="comrefundTo" name="comrefundTo"  class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right">Add Pay Date<font style="color: red"></font></label>
                                <div class="col-md-3">  
                                    <div class="form-group" id="addpayfromdatepanel">
                                        <div class='input-group date addpayfromdate' id='DateFromAddPay'>
                                            <input type='text' id="addpayFrom" name="addpayFrom" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">  
                                    <div class="form-group" id="addpaytodatepanel">
                                        <div class='input-group date addpaytodate' id='DateToAddPay'>
                                            <input type='text' id="addpayTo" name="addpayTo"  class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right">Decrease Pay Date<font style="color: red"></font></label>
                                <div class="col-md-3">  
                                    <div class="form-group" id="decreasepayfromdatepanel">
                                        <div class='input-group date decreasepayfromdate' id='DateFromDecreasePay'>
                                            <input type='text' id="decreasepayFrom" name="decreasepayFrom" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">  
                                    <div class="form-group" id="decreasepaytodatepanel">
                                        <div class='input-group date decreasepaytodate' id='DateToDecreasePay'>
                                            <input type='text' id="decreasepayTo" name="decreasepayTo"  class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Ticket No </label>
                                <div class="col-md-6">  
                                    <div class="form-group" id="ticketnopanel">
                                        <input type='text' id="ticketno" name="ticketno" class="form-control"/>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>     
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Department</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="department" id="department"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="wendy" >Wendy</option>
                                            <option value="inbound" >Inbound</option>
                                            <option value="outbound" >Outbound</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Sale By</label>
                                <div class="col-md-3 form-group">  
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="salebyId" name="salebyId" value=""/>
                                        <input type="text" class="form-control" id="salebyUser" name="salebyUser" value="" />
                                        <span class="input-group-addon" id="saleby_modal"  data-toggle="modal" data-target="#SaleByModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="salebyName" name="salebyName" value="" readonly="">
                                </div>
                            </div>   
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Term Pay</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="termPay" id="termPay"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:forEach var="table" items="${termPayList}" >
                                                <c:set var="select" value="" />
                                                <option value="${table.name}" ${select}>${table.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="rept"></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <button type="button" id="printbutton"  name="printbutton"  class="btn btn-success" onclick="printExcel();"><span class="glyphicon glyphicon-print"></span> Print</button>
                                    </div>
                                </div>   
                            </div> 
                        </div>
                    </div>
                </form>                
            </div>
        </div>
    </div>
</div>
                                
<div class="modal fade" id="SaleByModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Sale By</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="SaleByTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        saleby = [];
                    </script>
                    <c:forEach var="table" items="${userList}">
                        <tr>
                            <td class="saleby-id hidden">${table.id}</td>
                            <td class="saleby-user">${table.username}</td>
                            <td class="saleby-name">${table.name}</td>
                        </tr>
                        <script>
                            saleby.push({id: "${table.id}", username: "${table.username}", name: "${table.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="SaleByModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div> 
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
                            <th>Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        agent = [];
                    </script>
                    <c:forEach var="ag" items="${listAgent}">
                        <tr>
                            <td class="agent-id hidden">${ag.id}</td>
                            <td class="agent-user">${ag.code}</td>
                            <td class="agent-name">${ag.name}</td>
                        </tr>
                        <script>
                            agent.push({id: "${ag.id}", username: "${ag.code}", name: "${ag.name}"});
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


<script type="text/javascript" charset="utf-8">
    
$(document).ready(function() {
    
    $('.date').datetimepicker({
    });

    $('span').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    
//    $('#printbutton').click( function() {  
//        printTicketSummaryAirline();
//    });
    $("#printbutton").addClass("disabled");
    //Sale By Auto Complete
    $("#SaleByTable tr").on('click', function () {
        var saleby_id = $(this).find(".saleby-id").text();
        var saleby_user = $(this).find(".saleby-user").text();
        var saleby_name = $(this).find(".saleby-name").text();
        $("#salebyId").val(saleby_id);
        $("#salebyUser").val(saleby_user);
        $("#salebyName").val(saleby_name);
        $("#SaleByModal").modal('hide');
    });
        
    var salebyuser = [];
    $.each(saleby, function (key, value) {
        salebyuser.push(value.username);
        salebyuser.push(value.name);
    });

    $("#salebyUser").autocomplete({
        source: salebyuser,
        close:function( event, ui ) {
           $("#salebyUser").trigger('keyup');
        }
    });
        
    $("#salebyUser").on('keyup',function(){
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var username = this.value.toUpperCase();
        var name = this.value.toUpperCase();
       // console.log("Name :"+ name);
        $("#salebyId,#salebyName").val(null);
        $.each(saleby, function (key, value) {
            if (value.username.toUpperCase() === username ) {  
                $("#salebyId").val(value.id);
                $("#salebyUser").val(value.username);
                $("#salebyName").val(value.name);
            }
            else if(value.name.toUpperCase() === name){
                $("#salebyUser").val(value.username);
                $("#salebyId").val(value.id);
                $("#salebyName").val(value.name);
            }
        }); 
    }); 
    
    $('#SaleByTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    $('#SaleByTable tbody').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
    
    //Agent
    $("#AgentTable tr").on('click', function () {
        var saleby_id = $(this).find(".agent-id").text();
        var saleby_user = $(this).find(".agent-user").text();
        var saleby_name = $(this).find(".agent-name").text();
        $("#agentId").val(saleby_id);
        $("#agentCode").val(saleby_user);
        $("#agentName").val(saleby_name);
        $("#AgentModal").modal('hide');
    });
        
    var salebyuseragent = [];
    $.each(agent, function (key, value) {
        salebyuseragent.push(value.username);
        salebyuseragent.push(value.name);
    });

    $("#agentCode").autocomplete({
        source: salebyuseragent,
        close:function( event, ui ) {
           $("#agentCode").trigger('keyup');
        }
    });
        
    $("#agentCode").on('keyup',function(){
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var username = this.value.toUpperCase();
        var name = this.value.toUpperCase();
        console.log("Name :"+ name + " Username : " + username);
        $("#agentId,#agentName").val(null);
        $.each(agent, function (key, value) {
            console.log("Name 1:"+ value.username.toUpperCase() + " Username1 : " + username);
            if (value.username.toUpperCase() === username ) { 
                console.log("Name 2:"+ name + " Username2 : " + username);
                $("#agentId").val(value.id);
                $("#agentCode").val(value.username);
                $("#agentName").val(value.name);
            }
            else if(value.name.toUpperCase() === name){
                console.log("Name 3:"+ name + " Username3 : " + username);
                $("#agentCode").val(value.username);
                $("#agentId").val(value.id);
                $("#agentName").val(value.name);
            }
        }); 
    }); 
    
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
    
    
    $('.fromdate').datetimepicker().change(function(){                          
        checkFromDateField();
    });
    $('.todate').datetimepicker().change(function(){                          
        checkToDateField();
    });
    $('.issuefromdate').datetimepicker().change(function(){                          
        checkIssueFromDateField();
    });
    $('.issuetodate').datetimepicker().change(function(){                          
        checkIssueToDateField();
    });
//    $('.agentcomfromdate').datetimepicker().change(function(){                          
//        checkAgentComFromDateField();
//    });
//    $('.agentcomtodate').datetimepicker().change(function(){                          
//        checkAgentComToDateField();
//    });
//    $('.ticketcomfromdate').datetimepicker().change(function(){                          
//        checkTicketComFromDateField();
//    });
//    $('.ticketcomtodate').datetimepicker().change(function(){                          
//        checkTicketComToDateField();
//    });
    $('.overfromdate').datetimepicker().change(function(){                          
        checkOverFromDateField();
    });
    $('.overtodate').datetimepicker().change(function(){                          
        checkOverToDateField();
    });

    $('.littlefromdate').datetimepicker().change(function(){                          
        checkLittleFromDateField();
    });
    $('.littletodate').datetimepicker().change(function(){                          
        checkLittleToDateField();
    });

    $('.agentcomreceivefromdate').datetimepicker().change(function(){                          
        checkAgentComReceiveFromDateField();
    });
    $('.agentcomreceivetodate').datetimepicker().change(function(){                          
        checkAgentComReceiveToDateField();
    });

    $('.comrefundfromdate').datetimepicker().change(function(){                          
        checkComRefundFromDateField();
    });
    $('.comrefundtodate').datetimepicker().change(function(){                          
        checkComRefundToDateField();
    });

    $('.addpayfromdate').datetimepicker().change(function(){                          
        checkAddpayFromDateField();
    });
    $('.addpaytodate').datetimepicker().change(function(){                          
        checkAddpayToDateField();
    });

    $('.decreasepayfromdate').datetimepicker().change(function(){                          
        checkDecreasepayFromDateField();
    });
    $('.decreasepaytodate').datetimepicker().change(function(){                          
        checkDecreasepayToDateField();
    });
    
   
});      


function checkFromDateField(){
    var InputToDate = document.getElementById("invoiceToDate");
    var inputFromDate = document.getElementById("invoiceFromDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");
        
        $("#fromdatepanel").addClass("has-success");
        $("#todatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("from","");
    }
}
    
function checkToDateField(){
    var InputToDate = document.getElementById("invoiceToDate");
    var inputFromDate = document.getElementById("invoiceFromDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    }else{
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");
        $("#fromdatepanel").addClass("has-success");
        $("#todatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("to","");
    }       
}

function checkIssueFromDateField(){
    var issueToDate = document.getElementById("issueTo");
    var issueFromDate = document.getElementById("issueFrom");
    if(issueFromDate.value === '' && issueToDate.value === ''){
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(issueFromDate.value === '' || issueToDate.value === ''){ 
        $("#issuefromdatepanel").removeClass("has-success");
        $("#issuetodatepanel").removeClass("has-success");  
        $("#issuefromdatepanel").addClass("has-error");
        $("#issuetodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");
        $("#issuefromdatepanel").addClass("has-success");
        $("#issuetodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("issuefrom","");
    }    
}
    
function checkIssueToDateField(){
    var issueToDate = document.getElementById("issueTo");
    var issueFromDate = document.getElementById("issueFrom");
    if(issueFromDate.value === '' && issueToDate.value === ''){
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(issueFromDate.value === '' || issueToDate.value === ''){
        $("#issuefromdatepanel").removeClass("has-success");
        $("#issuetodatepanel").removeClass("has-success");  
        $("#issuefromdatepanel").addClass("has-error");
        $("#issuetodatepanel").addClass("has-error"); 
        $("#printbutton").addClass("disabled");
    }else{
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");
        
        $("#issuefromdatepanel").addClass("has-success");
        $("#issuetodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("issueto","");
    }       
}

//function checkAgentComFromDateField(){
//    var agentComToDate = document.getElementById("agentcomTo");
//    var agentComFromDate = document.getElementById("agentcomFrom");
//    if(agentComFromDate.value === '' && agentComToDate.value === ''){
//        $("#agentcomfromdatepanel").removeClass("has-error");
//        $("#agentcomtodatepanel").removeClass("has-error");  
//        $("#printbutton").removeClass("disabled");
//    }else if(agentComFromDate.value === '' || agentComToDate.value === ''){ 
//        $("#agentcomfromdatepanel").removeClass("has-success");
//        $("#agentcomtodatepanel").removeClass("has-success");  
//        $("#agentcomfromdatepanel").addClass("has-error");
//        $("#agentcomtodatepanel").addClass("has-error");  
//        $("#printbutton").addClass("disabled");
//    } else {
//        $("#agentcomfromdatepanel").removeClass("has-error");
//        $("#agentcomtodatepanel").removeClass("has-error");
//        
//        $("#agentcomfromdatepanel").addClass("has-success");
//        $("#agentcomtodatepanel").addClass("has-success");
//        $("#printbutton").removeClass("disabled");
//        checkDateValue("agentcomfrom","");
//    }    
//}
//    
//function checkAgentComToDateField(){
//     var agentComToDate = document.getElementById("agentcomTo");
//    var agentComFromDate = document.getElementById("agentcomFrom");
//    if(agentComFromDate.value === '' && agentComToDate.value === ''){
//        $("#agentcomfromdatepanel").removeClass("has-error");
//        $("#agentcomtodatepanel").removeClass("has-error");  
//        $("#printbutton").removeClass("disabled");
//    }else if(agentComFromDate.value === '' || agentComToDate.value === ''){
//        $("#agentcomfromdatepanel").removeClass("has-success");
//        $("#agentcomtodatepanel").removeClass("has-success");  
//        $("#agentcomfromdatepanel").addClass("has-error");
//        $("#agentcomtodatepanel").addClass("has-error");  
//        $("#printbutton").addClass("disabled");
//    }else{
//        $("#agentcomfromdatepanel").removeClass("has-error");
//        $("#agentcomtodatepanel").removeClass("has-error");
//        
//        $("#agentcomfromdatepanel").addClass("has-success");
//        $("#agentcomtodatepanel").addClass("has-success");
//        $("#printbutton").removeClass("disabled");
//        checkDateValue("agentcomto","");
//    }       
//}
//
//function checkTicketComFromDateField(){
//    var ticketComFromDate = document.getElementById("ticketcomFrom");
//    var ticketComToDate = document.getElementById("ticketcomTo");
//    if(ticketComFromDate.value === '' && ticketComToDate.value === ''){
//        $("#ticketcomfromdatepanel").removeClass("has-error");
//        $("#ticketcomtodatepanel").removeClass("has-error");  
//        $("#printbutton").removeClass("disabled");
//    }else if(ticketComFromDate.value === '' || ticketComToDate.value === ''){ 
//        $("#ticketcomfromdatepanel").removeClass("has-success");
//        $("#ticketcomtodatepanel").removeClass("has-success");  
//        $("#ticketcomfromdatepanel").addClass("has-error");
//        $("#ticketcomtodatepanel").addClass("has-error");  
//        $("#printbutton").addClass("disabled");
//    } else {
//        $("#ticketcomfromdatepanel").removeClass("has-error");
//        $("#ticketcomtodatepanel").removeClass("has-error");
//        
//        $("#ticketcomfromdatepanel").addClass("has-success");
//        $("#ticketcomtodatepanel").addClass("has-success");
//        $("#printbutton").removeClass("disabled");
//        checkDateValue("ticketcomfrom","");
//    }    
//}
//    
//function checkTicketComToDateField(){
//    var ticketComFromDate = document.getElementById("ticketcomFrom");
//    var ticketComToDate = document.getElementById("ticketcomTo");
//    if(ticketComFromDate.value === '' && ticketComToDate.value === ''){
//        $("#ticketcomfromdatepanel").removeClass("has-error");
//        $("#ticketcomtodatepanel").removeClass("has-error");  
//        $("#printbutton").removeClass("disabled");
//    }else if(ticketComFromDate.value === '' || ticketComToDate.value === ''){ 
//        $("#ticketcomfromdatepanel").removeClass("has-success");
//        $("#ticketcomtodatepanel").removeClass("has-success");  
//        $("#ticketcomfromdatepanel").addClass("has-error");
//        $("#ticketcomtodatepanel").addClass("has-error");  
//        $("#printbutton").addClass("disabled");
//    } else {
//        $("#ticketcomfromdatepanel").removeClass("has-error");
//        $("#ticketcomtodatepanel").removeClass("has-error");
//        
//        $("#ticketcomfromdatepanel").addClass("has-success");
//        $("#ticketcomtodatepanel").addClass("has-success");
//        $("#printbutton").removeClass("disabled");
//        checkDateValue("ticketcomto","");
//    }       
//}

//function checkTicketComFromDateField(){
//    var ticketComFromDate = document.getElementById("overFrom");
//    var ticketComToDate = document.getElementById("ticketcomTo");
//    if(ticketComFromDate.value === '' && ticketComToDate.value === ''){
//        $("#ticketcomfromdatepanel").removeClass("has-error");
//        $("#ticketcomtodatepanel").removeClass("has-error");  
//        $("#printbutton").removeClass("disabled");
//    }else if(ticketComFromDate.value === '' || ticketComToDate.value === ''){ 
//        $("#ticketcomfromdatepanel").removeClass("has-success");
//        $("#ticketcomtodatepanel").removeClass("has-success");  
//        $("#ticketcomfromdatepanel").addClass("has-error");
//        $("#ticketcomtodatepanel").addClass("has-error");  
//        $("#printbutton").addClass("disabled");
//    } else {
//        $("#ticketcomfromdatepanel").removeClass("has-error");
//        $("#ticketcomtodatepanel").removeClass("has-error");
//        
//        $("#ticketcomfromdatepanel").addClass("has-success");
//        $("#ticketcomtodatepanel").addClass("has-success");
//        $("#printbutton").removeClass("disabled");
//        checkDateValue("ticketcomfrom","");
//    }    
//}
//    
//function checkTicketComToDateField(){
//    var ticketComFromDate = document.getElementById("ticketcomFrom");
//    var ticketComToDate = document.getElementById("ticketcomTo");
//    if(ticketComFromDate.value === '' && ticketComToDate.value === ''){
//        $("#ticketcomfromdatepanel").removeClass("has-error");
//        $("#ticketcomtodatepanel").removeClass("has-error");  
//        $("#printbutton").removeClass("disabled");
//    }else if(ticketComFromDate.value === '' || ticketComToDate.value === ''){ 
//        $("#ticketcomfromdatepanel").removeClass("has-success");
//        $("#ticketcomtodatepanel").removeClass("has-success");  
//        $("#ticketcomfromdatepanel").addClass("has-error");
//        $("#ticketcomtodatepanel").addClass("has-error");  
//        $("#printbutton").addClass("disabled");
//    } else {
//        $("#ticketcomfromdatepanel").removeClass("has-error");
//        $("#ticketcomtodatepanel").removeClass("has-error");
//        
//        $("#ticketcomfromdatepanel").addClass("has-success");
//        $("#ticketcomtodatepanel").addClass("has-success");
//        $("#printbutton").removeClass("disabled");
//        checkDateValue("ticketcomto","");
//    }       
//}

function checkOverFromDateField(){
    var overFromDate = document.getElementById("overFrom");
    var overToDate = document.getElementById("overTo");
    if(overFromDate.value === '' && overToDate.value === ''){
        $("#overfromdatepanel").removeClass("has-error");
        $("#overtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(overFromDate.value === '' || overToDate.value === ''){ 
        $("#overfromdatepanel").removeClass("has-success");
        $("#overtodatepanel").removeClass("has-success");  
        $("#overfromdatepanel").addClass("has-error");
        $("#overtodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#overfromdatepanel").removeClass("has-error");
        $("#overtodatepanel").removeClass("has-error");
        
        $("#overfromdatepanel").addClass("has-success");
        $("#overtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("overfrom","");
    }    
}
    
function checkOverToDateField(){
    var overFromDate = document.getElementById("overFrom");
    var overToDate = document.getElementById("overTo");
    if(overFromDate.value === '' && overToDate.value === ''){
        $("#overfromdatepanel").removeClass("has-error");
        $("#overtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(overFromDate.value === '' || overToDate.value === ''){ 
        $("#overfromdatepanel").removeClass("has-success");
        $("#overtodatepanel").removeClass("has-success");  
        $("#overfromdatepanel").addClass("has-error");
        $("#overtodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#overfromdatepanel").removeClass("has-error");
        $("#overtodatepanel").removeClass("has-error");
        
        $("#overfromdatepanel").addClass("has-success");
        $("#overtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("overto","");
    }       
}

function checkLittleFromDateField(){
    var littleFromDate = document.getElementById("littleFrom");
    var littleToDate = document.getElementById("littleTo");
    if(littleFromDate.value === '' && littleToDate.value === ''){
        $("#littlefromdatepanel").removeClass("has-error");
        $("#littletodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(littleFromDate.value === '' || littleToDate.value === ''){ 
        $("#littlefromdatepanel").removeClass("has-success");
        $("#littletodatepanel").removeClass("has-success");  
        $("#littlefromdatepanel").addClass("has-error");
        $("#littletodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#littlefromdatepanel").removeClass("has-error");
        $("#littletodatepanel").removeClass("has-error");
        
        $("#littlefromdatepanel").addClass("has-success");
        $("#littletodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("littlefrom","");
    }    
}
    
function checkLittleToDateField(){
    var littleFromDate = document.getElementById("littleFrom");
    var littleToDate = document.getElementById("littleTo");
    if(littleFromDate.value === '' && littleToDate.value === ''){
        $("#littlefromdatepanel").removeClass("has-error");
        $("#littletodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(littleFromDate.value === '' || littleToDate.value === ''){ 
        $("#littlefromdatepanel").removeClass("has-success");
        $("#littletodatepanel").removeClass("has-success");  
        $("#littlefromdatepanel").addClass("has-error");
        $("#littletodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#littlefromdatepanel").removeClass("has-error");
        $("#littletodatepanel").removeClass("has-error");
        
        $("#littlefromdatepanel").addClass("has-success");
        $("#littletodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("littleto","");
    }       
}

function checkAgentComReceiveFromDateField(){
    var agentcomreceiveFromDate = document.getElementById("agentcomreceiveFrom");
    var agentcomreceiveToDate = document.getElementById("agentcomreceiveTo");
    if(agentcomreceiveFromDate.value === '' && agentcomreceiveToDate.value === ''){
        $("#agentcomreceivefromdatepanel").removeClass("has-error");
        $("#agentcomreceivetodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(agentcomreceiveFromDate.value === '' || agentcomreceiveToDate.value === ''){ 
        $("#agentcomreceivefromdatepanel").removeClass("has-success");
        $("#agentcomreceivetodatepanel").removeClass("has-success");  
        $("#agentcomreceivefromdatepanel").addClass("has-error");
        $("#agentcomreceivetodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#agentcomreceivefromdatepanel").removeClass("has-error");
        $("#agentcomreceivetodatepanel").removeClass("has-error");
        
        $("#agentcomreceivefromdatepanel").addClass("has-success");
        $("#agentcomreceivetodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("agentcomreceivefrom","");
    }    
}
    
function checkAgentComReceiveToDateField(){
    var agentcomreceiveFromDate = document.getElementById("agentcomreceiveFrom");
    var agentcomreceiveToDate = document.getElementById("agentcomreceiveTo");
    if(agentcomreceiveFromDate.value === '' && agentcomreceiveToDate.value === ''){
        $("#agentcomreceivefromdatepanel").removeClass("has-error");
        $("#agentcomreceivetodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(agentcomreceiveFromDate.value === '' || agentcomreceiveToDate.value === ''){ 
        $("#agentcomreceivefromdatepanel").removeClass("has-success");
        $("#agentcomreceivetodatepanel").removeClass("has-success");  
        $("#agentcomreceivefromdatepanel").addClass("has-error");
        $("#agentcomreceivetodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#agentcomreceivefromdatepanel").removeClass("has-error");
        $("#agentcomreceivetodatepanel").removeClass("has-error");
        
        $("#agentcomreceivefromdatepanel").addClass("has-success");
        $("#agentcomreceivetodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("agentcomreceiveto","");
    }       
}

function checkComRefundFromDateField(){
    var comrefundFromDate = document.getElementById("comrefundFrom");
    var comrefundToDate = document.getElementById("comrefundTo");
    if(comrefundFromDate.value === '' && comrefundToDate.value === ''){
        $("#comrefundfromdatepanel").removeClass("has-error");
        $("#comrefundtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(comrefundFromDate.value === '' || comrefundToDate.value === ''){ 
        $("#comrefundfromdatepanel").removeClass("has-success");
        $("#comrefundtodatepanel").removeClass("has-success");  
        $("#comrefundfromdatepanel").addClass("has-error");
        $("#comrefundtodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#comrefundfromdatepanel").removeClass("has-error");
        $("#comrefundtodatepanel").removeClass("has-error");
        
        $("#comrefundfromdatepanel").addClass("has-success");
        $("#comrefundtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("comrefundfrom","");
    }    
}
    
function checkComRefundToDateField(){
    var comrefundFromDate = document.getElementById("comrefundFrom");
    var comrefundToDate = document.getElementById("comrefundTo");
    if(comrefundFromDate.value === '' && comrefundToDate.value === ''){
        $("#comrefundfromdatepanel").removeClass("has-error");
        $("#comrefundtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(comrefundFromDate.value === '' || comrefundToDate.value === ''){ 
        $("#comrefundfromdatepanel").removeClass("has-success");
        $("#comrefundtodatepanel").removeClass("has-success");  
        $("#comrefundfromdatepanel").addClass("has-error");
        $("#comrefundtodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#comrefundfromdatepanel").removeClass("has-error");
        $("#comrefundtodatepanel").removeClass("has-error");
        
        $("#comrefundfromdatepanel").addClass("has-success");
        $("#comrefundtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("comrefundto","");
    }       
}

function checkAddpayFromDateField(){
    var addpayFromDate = document.getElementById("addpayFrom");
    var addpayToDate = document.getElementById("addpayTo");
    if(addpayFromDate.value === '' && addpayToDate.value === ''){
        $("#addpayfromdatepanel").removeClass("has-error");
        $("#addpaytodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(addpayFromDate.value === '' || addpayToDate.value === ''){ 
        $("#addpayfromdatepanel").removeClass("has-success");
        $("#addpaytodatepanel").removeClass("has-success");  
        $("#addpayfromdatepanel").addClass("has-error");
        $("#addpaytodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#addpayfromdatepanel").removeClass("has-error");
        $("#addpaytodatepanel").removeClass("has-error");
        
        $("#addpayfromdatepanel").addClass("has-success");
        $("#addpaytodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("addpayfrom","");
    }    
}
    
function checkAddpayToDateField(){
    var addpayFromDate = document.getElementById("addpayFrom");
    var addpayToDate = document.getElementById("addpayTo");
    if(addpayFromDate.value === '' && addpayToDate.value === ''){
        $("#addpayfromdatepanel").removeClass("has-error");
        $("#addpaytodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(addpayFromDate.value === '' || addpayToDate.value === ''){ 
        $("#addpayfromdatepanel").removeClass("has-success");
        $("#addpaytodatepanel").removeClass("has-success");  
        $("#addpayfromdatepanel").addClass("has-error");
        $("#addpaytodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#addpayfromdatepanel").removeClass("has-error");
        $("#addpaytodatepanel").removeClass("has-error");
        
        $("#addpayfromdatepanel").addClass("has-success");
        $("#addpaytodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("addpayto","");
    }       
}

function checkDecreasepayFromDateField(){
    var decreasepayFromDate = document.getElementById("decreasepayFrom");
    var decreasepayToDate = document.getElementById("decreasepayTo");
    if(decreasepayFromDate.value === '' && decreasepayToDate.value === ''){
        $("#decreasepayfromdatepanel").removeClass("has-error");
        $("#decreasepaytodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(decreasepayFromDate.value === '' || decreasepayToDate.value === ''){ 
        $("#decreasepayfromdatepanel").removeClass("has-success");
        $("#decreasepaytodatepanel").removeClass("has-success");  
        $("#decreasepayfromdatepanel").addClass("has-error");
        $("#decreasepaytodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#decreasepayfromdatepanel").removeClass("has-error");
        $("#decreasepaytodatepanel").removeClass("has-error");
        
        $("#decreasepayfromdatepanel").addClass("has-success");
        $("#decreasepaytodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("decreasepayfrom","");
    }    
}
    
function checkDecreasepayToDateField(){
    var decreasepayFromDate = document.getElementById("decreasepayFrom");
    var decreasepayToDate = document.getElementById("decreasepayTo");
    if(decreasepayFromDate.value === '' && decreasepayToDate.value === ''){
        $("#decreasepayfromdatepanel").removeClass("has-error");
        $("#decreasepaytodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(decreasepayFromDate.value === '' || decreasepayToDate.value === ''){ 
        $("#decreasepayfromdatepanel").removeClass("has-success");
        $("#decreasepaytodatepanel").removeClass("has-success");  
        $("#decreasepayfromdatepanel").addClass("has-error");
        $("#decreasepaytodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#decreasepayfromdatepanel").removeClass("has-error");
        $("#decreasepaytodatepanel").removeClass("has-error");
        
        $("#decreasepayfromdatepanel").addClass("has-success");
        $("#decreasepaytodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("decreasepayto","");
    }       
}

function checkDateValue(date){
    var inputFromDate = "";
        var InputToDate = "";
        if((date === 'from') || (date === 'to')){
            inputFromDate = document.getElementById("invoiceFromDate");
            InputToDate = document.getElementById("invoiceToDate");
        } else if((date === 'issuefrom') || (date === 'issueto')) {
            inputFromDate = document.getElementById("issueFrom");
            InputToDate = document.getElementById("issueTo");
        }else if ((date === 'overfrom') || (date === 'overto')){
            inputFromDate = document.getElementById("overFrom");
            InputToDate = document.getElementById("overTo");
        }else if ((date === 'littlefrom') || (date === 'littleto')){
            inputFromDate = document.getElementById("littleFrom");
            InputToDate = document.getElementById("littleTo");
        }else if ((date === 'agentcomreceivefrom') || (date === 'agentcomreceiveto')){
            inputFromDate = document.getElementById("agentcomreceiveFrom");
            InputToDate = document.getElementById("agentcomreceiveTo");
        }else if ((date === 'comrefundfrom') || (date === 'comrefundto')){
            inputFromDate = document.getElementById("comrefundFrom");
            InputToDate = document.getElementById("comrefundTo");
        }else if ((date === 'addpayfrom') || (date === 'addpayto')){
            inputFromDate = document.getElementById("addpayFrom");
            InputToDate = document.getElementById("addpayTo");
        }else if ((date === 'decreasepayfrom') || (date === 'decreasepayto')){
            inputFromDate = document.getElementById("decreasepayFrom");
            InputToDate = document.getElementById("decreasepayTo");
        }
    if((inputFromDate.value !== '') && (InputToDate.value !== '')){
        var fromDate = (inputFromDate.value).split('-');
        var toDate = (InputToDate.value).split('-');
        if((parseInt(fromDate[0])) > (parseInt(toDate[0]))){
            validateDate(date,"over");
        }
        if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))){
            validateDate(date,"over");
        }
        if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[2])) > (parseInt(toDate[2]))){
            validateDate(date,"over");
        }          
    }
}
    
function validateDate(date,option){
   if(option === 'over'){
        if(date === 'from'){
            $("#fromdatepanel").removeClass("has-success");
            $("#fromdatepanel").addClass("has-error");                                 
        }
        if(date === 'to'){
            $("#todatepanel").removeClass("has-success");
            $("#todatepanel").addClass("has-error");
        }
        if(date === 'issuefrom'){
            $("#issuefromdatepanel").removeClass("has-success");
            $("#issuefromdatepanel").addClass("has-error");
        }
        if(date === 'issueto'){
            $("#issuetodatepanel").removeClass("has-success"); 
            $("#issuetodatepanel").addClass("has-error");
        } 
        if(date === 'overfrom'){
            $("#overfromdatepanel").removeClass("has-success");
            $("#overfromdatepanel").addClass("has-error");
        }
        if(date === 'overto'){
            $("#overtodatepanel").removeClass("has-success"); 
            $("#overtodatepanel").addClass("has-error");
        }
        if(date === 'littlefrom'){
            $("#littlefromdatepanel").removeClass("has-success");
            $("#littlefromdatepanel").addClass("has-error");
        }
        if(date === 'littleto'){
            $("#littletodatepanel").removeClass("has-success"); 
            $("#littletodatepanel").addClass("has-error");
        }
        if(date === 'agentcomreceivefrom'){
            $("#agentcomreceivefromdatepanel").removeClass("has-success");
            $("#agentcomreceivefromdatepanel").addClass("has-error");
        }
        if(date === 'agentcomreceiveto'){
            $("#agentcomreceivetodatepanel").removeClass("has-success"); 
            $("#agentcomreceivetodatepanel").addClass("has-error");
        }
        if(date === 'comrefundfrom'){
            $("#comrefundfromdatepanel").removeClass("has-success");
            $("#comrefundfromdatepanel").addClass("has-error");
        }
        if(date === 'comrefundto'){
            $("#comrefundtodatepanel").removeClass("has-success"); 
            $("#comrefundtodatepanel").addClass("has-error");
        }
        if(date === 'addpayfrom'){
            $("#addpayfromdatepanel").removeClass("has-success");
            $("#addpayfromdatepanel").addClass("has-error");
        }
        if(date === 'addpayto'){
            $("#addpaytodatepanel").removeClass("has-success"); 
            $("#addpaytodatepanel").addClass("has-error");
        }
        if(date === 'decreasepayfrom'){
            $("#decreasepayfromdatepanel").removeClass("has-success");
            $("#decreasepayfromdatepanel").addClass("has-error");
        }
        if(date === 'decreasepayto'){
            $("#decreasepaytodatepanel").removeClass("has-success"); 
            $("#decreasepaytodatepanel").addClass("has-error");
        }
        $("#printbutton").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        
        $("#issuefromdatepanel").removeClass("has-success");
        $("#issuetodatepanel").removeClass("has-success"); 
        
        $("#overfromdatepanel").removeClass("has-success");
        $("#overtodatepanel").removeClass("has-success");
        
        $("#littlefromdatepanel").removeClass("has-success");
        $("#littletodatepanel").removeClass("has-success");
        
        $("#agentcomreceivefromdatepanel").removeClass("has-success");
        $("#agentcomreceivetodatepanel").removeClass("has-success");
        
        $("#comrefundfromdatepanel").removeClass("has-success");
        $("#comrefundtodatepanel").removeClass("has-success");
        
        $("#addpayfromdatepanel").removeClass("has-success");
        $("#addpaytodatepanel").removeClass("has-success");
        
        $("#decreasepayfromdatepanel").removeClass("has-success");
        $("#decreasepaytodatepanel").removeClass("has-success");
        
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        
        $("#issuefromdatepanel").addClass("has-error");
        $("#issuetodatepanel").addClass("has-error");
        
        $("#agentcomfromdatepanel").addClass("has-error");
        $("#agentcomtodatepanel").addClass("has-error");
        
        $("#ticketcomfromdatepanel").removeClass("has-error");
        $("#ticketcomtodatepanel").removeClass("has-error");
        
        $("#overfromdatepanel").removeClass("has-error");
        $("#overtodatepanel").removeClass("has-error");
        
        $("#littlefromdatepanel").removeClass("has-error");
        $("#littletodatepanel").removeClass("has-error");
        
        $("#agentcomreceivefromdatepanel").removeClass("has-error");
        $("#agentcomreceivetodatepanel").removeClass("has-error");
        
        $("#comrefundfromdatepanel").removeClass("has-error");
        $("#comrefundtodatepanel").removeClass("has-error");
        
        $("#addpayfromdatepanel").removeClass("has-error");
        $("#addpaytodatepanel").removeClass("has-error");
        
        $("#decreasepayfromdatepanel").removeClass("has-error");
        $("#decreasepaytodatepanel").removeClass("has-error");
        
        $("#printbutton").addClass("disabled");
    }
}

function printExcel(){
    var invoicefromdate = document.getElementById("invoiceFromDate").value;
    var invoicetodate = document.getElementById("invoiceToDate").value;
    var issuefromdate = document.getElementById("issueFrom").value;
    var issuetodate = document.getElementById("issueTo").value;
    var overfromdate = document.getElementById("overFrom").value;
    var overtodate = document.getElementById("overTo").value;
    var littlefromdate = document.getElementById("littleFrom").value;
    var littletodate = document.getElementById("littleTo").value;
    var agemtcomreceivefromdate = document.getElementById("agentcomreceiveFrom").value;
    var agemtcomreceivetodate = document.getElementById("agentcomreceiveTo").value;
    var comrefundfromdate = document.getElementById("comrefundFrom").value;
    var comrefundtodate = document.getElementById("comrefundTo").value;
    var addpayfromdate = document.getElementById("addpayFrom").value;
    var addpaytodate = document.getElementById("addpayTo").value;
    var decreasepayfromdate = document.getElementById("decreasepayFrom").value;
    var decreasepaytodate = document.getElementById("decreasepayTo").value;
    var typeRoutings = document.getElementById("typeRouting").value;
    var routingDetails = document.getElementById("routingDetail").value;
    var airlineCodes = document.getElementById("airlineCode").value;
    var agentCodes = document.getElementById("agentCode").value;
    var agentName = document.getElementById("agentName").value;
    var ticketno = document.getElementById("ticketno").value;
    var departmentts = document.getElementById("department").value;
    var salebyUserts = document.getElementById("salebyUser").value;
    var salebyName = document.getElementById("salebyName").value;
    var termPayts   = document.getElementById("termPay").value;

window.open("Excel.smi?name=TicketSummaryCommission&invoiceFromDate=" + invoicefromdate 
        + "&invoiceToDate=" + invoicetodate 
        + "&issueFrom=" + issuefromdate 
        + "&issueTo=" + issuetodate 
        + "&overFrom=" + overfromdate 
        + "&overTo=" + overtodate 
        + "&littleFrom=" + littlefromdate 
        + "&littleTo=" + littletodate
        + "&agentcomreceiveFrom=" + agemtcomreceivefromdate 
        + "&agentcomreceiveTo=" + agemtcomreceivetodate 
        + "&comrefundFrom=" + comrefundfromdate 
        + "&comrefundTo=" + comrefundtodate 
        + "&addpayFrom=" + addpayfromdate 
        + "&addpayTo=" + addpaytodate 
        + "&decreasepayFrom=" + decreasepayfromdate
        + "&decreasepayTo=" + decreasepaytodate 
        + "&typeRouting=" + typeRoutings 
        + "&routingDetail=" + routingDetails 
        + "&airlineCode=" + airlineCodes
        + "&agentCode=" + agentCodes 
        + "&agentName=" + agentName 
        + "&ticketno=" + ticketno
        + "&department=" + departmentts 
        + "&salebyUser=" + salebyUserts 
        + "&salebyName=" + salebyName 
        + "&termPay=" + termPayts
        );
    
}

function jsFunction(value){
    if(value == ""){
        $("#reporttypepanel").removeClass("has-success");
        $("#reporttypepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    }else{
        $("#reporttypepanel").removeClass("has-error");
        $("#reporttypepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
    }
}
</script>
