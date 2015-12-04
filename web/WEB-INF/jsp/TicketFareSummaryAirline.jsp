<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/BillAirAgent.js"></script>--> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<c:set var="airlineList" value="${requestScope['airlineList']}" />--%>
<c:set var="airlineCodeList" value="${requestScope['airlineCodeList']}" />
<c:set var="termPayList" value="${requestScope['termPayList']}" />
<c:set var="userList" value="${requestScope['userList']}" />
<c:set var="listAgent" value="${requestScope['listAgent']}" />

<section class="content-header"  >
    <h4>
        <b>Report : Air Ticket report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">List Summary Airline Issue</a></li>
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
                    <h3>List Summary Airline Issue</h3>
                </div>
            </div>
            
            <div class="col-md-10" >
                <form action="TicketFareSummaryAirline.smi" method="post" id="TicketFareSummaryAirlineForm" name="TicketFareSummaryAirlineForm" role="form">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Report Type<font style="color: red">*</font></label>
                                <div class="col-md-6">  
                                    <div class="form-group" id="reporttypepanel">
                                        <select name="reportType" id="reportType"  class="form-control" onchange="jsFunction(this.value);">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="1">TK Detail Airline Pax</option>
                                            <option value="2">Summary Airline Pax</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="issuefromdatepanel">
                                <label class="col-md-6 control-label text-right">Issue Date From<font style="color: red"></font></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <div class='input-group date issuefromdate' id='DateFromIssue'>
                                            <input type='text' id="issueFrom" name="issueFrom" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD"/>
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
                            <div class="form-group" id="issuetodatepanel">
                                <label class="col-md-6 control-label text-right">Issue Date To<font style="color: red"></font></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <div class='input-group date issuetodate' id='DateToIssue'>
                                            <input type='text' id="issueTo" name="issueTo"  class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD"/>
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
                            <div class="form-group" id="fromdatepanel">
                                <label class="col-md-6 control-label text-right">Invoice Date From<font style="color: red"></font></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <div class='input-group date fromdate' id='DateFrom'>
                                            <input type='text' id="invoiceFromDate" name="invoiceFromDate" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD"/>
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
                            <div class="form-group" id="todatepanel">
                                <label class="col-md-6 control-label text-right">Invoice Date To<font style="color: red"></font></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <div class='input-group date todate' id='DateTo'>
                                            <input type='text' id="invoiceToDate" name="invoiceToDate" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD"/>
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
                                <label class="col-md-6 control-label text-right" >Airline Code</label>
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
                                <label class="col-md-6 control-label text-right" >Passenger</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <input type='text' id="passenger" name="passenger" class="form-control"/>
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
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Department</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="department" id="department"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="wendy" >wendy</option>
                                            <option value="inbound" >inbound</option>
                                            <option value="outbound" >outbound</option>
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
                                        <button type="button" id="printbutton"  name="printbutton"  class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
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
    $('.datemask').mask('0000-00-00');
    
    $('.date').datetimepicker({
    });

    $('span').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    
    $('#printbutton').click( function() {  
        printTicketSummaryAirline();
    });
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
    

//       $("#TicketFareSummaryAirlineForm").bootstrapValidator({
//            framework: 'bootstrap',
//            feedbackIcons: {
//                valid: 'uk-icon-check',
//                invalid: 'uk-icon-times',
//                validating: 'uk-icon-refresh'
//            },
//            fields: {
//                startdate: {
//                    trigger: 'focus keyup change',
//                    validators: {
//                        notEmpty: {
//                                   message: 'The Issue Date From is required'
//                        },
//                        date: {
//                            format: 'YYYY-MM-DD',
//                            max: 'enddate',
//                            message: 'The Issue Date From is not a valid'
//                        }
//                    }
//                },
//                enddate: {
//                    trigger: 'focus keyup change',
//                    validators: {
//                        notEmpty: {
//                            message: 'The Issue Date To is required'
//                        },
//                        date: {
//                            format: 'YYYY-MM-DD',
//                            min: 'startdate',
//                            message: 'The Issue Date To is not a valid'
//                        }
//                    }
//                }
//            }
//        });
//        //validate date
//        $('#fromdate').datetimepicker().on('dp.change', function (e) {
//            $('#TicketFareSummaryAirlineForm').bootstrapValidator('revalidateField', 'startdate');
//            $('#TicketFareSummaryAirlineForm').bootstrapValidator('revalidateField', 'enddate');
//            checkFromDateField();
//        });
//        $('#todate').datetimepicker().on('dp.change', function (e) {
//            $('#TicketFareSummaryAirlineForm').bootstrapValidator('revalidateField', 'startdate');
//            $('#TicketFareSummaryAirlineForm').bootstrapValidator('revalidateField', 'enddate');
//            $('#TicketFareSummaryAirlineForm').bootstrapValidator('revalidateField', 'startdate');
//            checkToDateField();
//        });

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

function checkDateValue(date){
    var inputFromDate = "";
        var InputToDate = "";
        if((date === 'from') || (date === 'to')){
            inputFromDate = document.getElementById("invoiceFromDate");
            InputToDate = document.getElementById("invoiceToDate");
        } else {
            inputFromDate = document.getElementById("issueFrom");
            InputToDate = document.getElementById("issueTo");
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
        $("#printbutton").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        $("#issuefromdatepanel").removeClass("has-success");
        $("#issuetodatepanel").removeClass("has-success"); 
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#issuefromdatepanel").addClass("has-error");
        $("#issuetodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    }
}

function printTicketSummaryAirline(){
    var reportType = document.getElementById("reportType").value;
    var issuefrom = document.getElementById("issueFrom").value;
    var issueto = document.getElementById("issueTo").value;
    var invFrom = document.getElementById("invoiceFromDate").value;
    var invTo = document.getElementById("invoiceToDate").value;
    var typeRouting = document.getElementById("typeRouting").value;
    var routingDetail = document.getElementById("routingDetail").value;
    var airlineCode = document.getElementById("airlineCode").value;
    var passenger = document.getElementById("passenger").value;
    var agentId = document.getElementById("agentId").value;
    var department = document.getElementById("department").value;
    var salebyUser = document.getElementById("salebyUser").value;
    var termPay = document.getElementById("termPay").value;
    var salebyName = document.getElementById("salebyName").value;
    
    if((invFrom !== '') && (invTo !== '')){
        if(reportType == 1){
            window.open("Excel.smi?name=TicketFareSummaryAirline&typeRouting=" + typeRouting 
                    + "&routingDetail=" + routingDetail 
                    + "&issuedateFrom=" + issuefrom 
                    + "&issuedateTo=" + issueto 
                    + "&invdateFrom=" + invFrom 
                    + "&invdateTo=" + invTo 
                    + "&airlineCode=" + airlineCode 
                    + "&passenger=" + passenger
                    + "&agentId=" + agentId 
                    + "&department=" + department 
                    + "&salebyUser=" + salebyUser 
                    + "&termPay=" + termPay);
        }else if(reportType == 2){
            window.open("Excel.smi?name=SummaryAirlinePax&typeRouting=" + typeRouting 
                    + "&routingDetail=" + routingDetail 
                    + "&issuedateFrom=" + issuefrom 
                    + "&issuedateTo=" + issueto 
                    + "&invdateFrom=" + invFrom 
                    + "&invdateTo=" + invTo 
                    + "&airlineCode=" + airlineCode 
                    + "&passenger=" + passenger
                    + "&agentId=" + agentId 
                    + "&department=" + department 
                    + "&salebyUser=" + salebyUser 
                    + "&termPay=" + termPay);
        }else{
            $("#reporttypepanel").removeClass("has-success");
            $("#reporttypepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        }   
    } else if((issuefrom !== '') && (issueto !== '')){
        if(reportType == 1){
            window.open("Excel.smi?name=TicketFareSummaryAirline&typeRouting=" + typeRouting 
                    + "&routingDetail=" + routingDetail 
                    + "&issuedateFrom=" + issuefrom 
                    + "&issuedateTo=" + issueto 
                    + "&invdateFrom=" + invFrom 
                    + "&invdateTo=" + invTo 
                    + "&airlineCode=" + airlineCode 
                    + "&passenger=" + passenger
                    + "&agentId=" + agentId 
                    + "&department=" + department 
                    + "&salebyUser=" + salebyUser 
                    + "&termPay=" + termPay);
        }else if(reportType == 2){
            window.open("Excel.smi?name=SummaryAirlinePax&typeRouting=" + typeRouting 
                    + "&routingDetail=" + routingDetail 
                    + "&issuedateFrom=" + issuefrom 
                    + "&issuedateTo=" + issueto 
                    + "&invdateFrom=" + invFrom 
                    + "&invdateTo=" + invTo 
                    + "&airlineCode=" + airlineCode 
                    + "&passenger=" + passenger
                    + "&agentId=" + agentId 
                    + "&department=" + department 
                    + "&salebyUser=" + salebyUser 
                    + "&termPay=" + termPay);
        }else{
            $("#reporttypepanel").removeClass("has-success");
            $("#reporttypepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        }      
    } else {
        if(reportType == ""){
            $("#reporttypepanel").removeClass("has-success");
            $("#reporttypepanel").addClass("has-error");
        }
        validateDate();  
    }
//    alert("reportType ::: "+reportType+
//          "issuefrom ::: "+issuefrom+
//          "issueto ::: "+issueto+
//          "invoiceFromDate ::: "+invFrom+
//          "invoiceToDate ::: "+invTo+
//          "invoiceToDate ::: "+typeRouting+
//          "routingDetail ::: "+routingDetail+
//          "airlineCode ::: "+airlineCode+
//          "passenger ::: "+passenger+
//          "agentCode ::: "+agentCode+
//          "department ::: "+department+
//          "salebyUser ::: "+salebyUser+
//          "termPay ::: "+termPay      
//                );
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
