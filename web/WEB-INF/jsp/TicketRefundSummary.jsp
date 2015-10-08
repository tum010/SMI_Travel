<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/TicketRefundSummary.js"></script>--> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="content-header"  >
    <h4>
        <b>Report : Air Ticket report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Print List Refund Payment</a></li>
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
                    <h3>Print List Refund Payment</h3>
                </div>
            </div>
            
            <div class="col-md-10" >
                <form action="TicketRefundSummary.smi" method="post" id="TicketRefundSummaryForm" name="TicketRefundSummaryForm" role="form">
                    <div class="row">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 330px">
                                <label class="control-label text-right">Refund Agent</label>
                            </div>
                            <div class="col-xs-1"  style="width: 150px">
                                <div class="input-group" id="refundAgentCodeValidate">
                                    <input type="hidden" class="form-control" id="refundAgentId" name="refundAgentId" value="${refundAirline.agent.id}" />
                                    <input type="text" class="form-control" id="refundAgentCode" name="refundAgentCode" value="${refundAirline.agent.code}" />
                                    <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#RefundAgentModal">
                                        <span class="glyphicon-search glyphicon"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-left" style="width: 220px">
                                <input type="text" class="form-control" id="refundAgentName" name="refundAgentName" value="${refundAirline.agent.name}" readonly="">                           
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 330px">
                                <label class="control-label text-right">Refund By </label>
                            </div>
                            <div class="col-xs-1"  style="width: 150px">
                                <div class="input-group" id="refundByValidate">
                                    <input type="text" class="form-control" id="refundBy" name="refundBy" value="${refundAirline.refundBy}" />
                                    <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#refundCustModal">
                                        <span class="glyphicon-search glyphicon"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-left" style="width: 220px">
                                <!--<div class="input-group">-->
                                    <input id="refundByName" name="refundByName" type="text" class="form-control" value="${refundByName}" readonly="">
                                <!--</div>-->
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-10">
                            <div class="form-group">
                                <label class="col-md-1 control-label text-right" style="width: 330px">Passenger</label>
                                <div class="col-md-1" style="width: 370px">  
                                    <div class="form-group">
                                        <input type='text' id="passenger" name="passenger" class="form-control"/>
                                    </div>  
                                </div>   
                            </div>
                        </div>
                    </div>             
                    <div class="row">
                        <div class="col-md-10">
                            <div class="form-group">
                                <label class="col-md-1 control-label text-right" style="width: 330px">Sector To Be Refund</label>
                                <div class="col-md-1" style="width: 370px">  
                                    <div class="form-group">
                                        <input type='text' id="sectortoberef" name="sectortoberef" class="form-control"/>
                                    </div>  
                                </div>   
                            </div>
                        </div>
                    </div>    
                    <div class="row">
                        <div class="col-md-10">
                            <div class="form-group" >
                                <label class="col-md-1 control-label text-right" style="width: 330px">Receive</label>
                                <div class="col-md-1" style="width: 170px">  
                                    <div class="form-group" id="receivefromdatepanel">
                                        <div class='input-group date receivefromdate' id='receiveDateFrom'>
                                            <input type='text' id="receiveFromDate" name="receiveFromDate" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <label class="col-md-1 control-label text-right" style="width: 3px">To</label>
                                <div class="col-md-1" style="width: 170px">
                                    <div class="form-group" id="receivetodatepanel">
                                        <div class='input-group date receivetodate' id='receiveDateTo'>
                                            <input type='text' id="receiveToDate" name="receiveToDate" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-10">
                            <div class="form-group" >
                                <label class="col-md-1 control-label text-right" style="width: 330px">Paid</label>
                                <div class="col-md-1" style="width: 170px">  
                                    <div class="form-group" id="paidfromdatepanel">
                                        <div class='input-group date paidfromdate' id='paidDateFrom'>
                                            <input type='text' id="paidFromDate" name="paidFromDate" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <label class="col-md-1 control-label text-right" style="width: 3px">To</label>
                                <div class="col-md-1" style="width: 170px">
                                    <div class="form-group" id="paidtodatepanel">
                                        <div class='input-group date paidtodate' id='paidDateTo'>
                                            <input type='text' id="paidToDate" name="paidToDate" class="form-control" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>        
                    <div class="row">
                        <div class="col-md-10">
                            <div class="form-group">
                                <label class="col-md-1 control-label text-right" style="width: 330px">Type Print</label>
                                <div class="col-md-1" style="width: 370px">
                                    <div class="form-group" id="reporttypepanel">
                                        <select name="typePrint" id="typePrint"  onchange="jsFunction(this.value);" class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="1" >paid</option>
                                            <option value="2" >payment</option>
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
                                
<!--Modal  Agent-->
<div class="modal fade" id="RefundAgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Refund Agent</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="RefundAgentTable">
                    <thead class="datatable-header">                     
                        <tr>
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
                    <button id="RefundAgentModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>                    
                

<!--Modal  Customer-->
<div class="modal fade" id="refundCustModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Refund By</h4>
            </div>
            <div class="modal-body">
                <div style="text-align: right"> 
                    <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchCustFrom" name="searchCustFrom"/> 
                </div> 
                <table class="display" id="refundCustTable">
                    <thead >   
                        <tr class="datatable-header">
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cust}">
                            <tr onclick="setBillValue('${item.billTo}', '${item.billName}', '${item.address}', '${item.term}', '${item.pay}');">
                                <td class="item-billto">${item.billTo}</td>
                                <td class="item-name">${item.billName}</td>                                
                                <td class="item-address hidden">${item.address}</td>
                                <td class="item-tel hidden">${item.tel}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="rrefundCustModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        $('.datemask').mask('0000-00-00');
        
        var RefundAgentTable = $('#RefundAgentTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });

        var refundCustTable = $('#refundCustTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });
        
        $("#RefundAgentTable tr").on('click', function () {
            var agent_id = $(this).find(".agent-id").text();
            var agent_user = $(this).find(".agent-user").text();
            var agent_name = $(this).find(".agent-name").text();
            $("#refundAgentId").val(agent_id);
            $("#refundAgentCode").val(agent_user);
            $("#refundAgentName").val(agent_name);
            $("#RefundAgentModal").modal('hide');
        });
        
        var agentCode = [];
        $.each(agent, function (key, value) {
            agentCode.push(value.code);
            agentCode.push(value.name);
        });

        $("#refundAgentCode").autocomplete({
            source: agentCode,
            close: function (event, ui) {
                $("#refundAgentCode").trigger('keyup');
            }
        });

        $("#refundAgentCode").on('keyup', function () {
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var code = this.value.toUpperCase();
            var name = this.value.toUpperCase();
            console.log("Name :" + name);
            $("#agent_id,#agent_name,#agent_addr,#agent_tel").val(null);
            $.each(agent, function (key, value) {
                if (value.code.toUpperCase() === code) {
                    $("#refundAgentId").val(value.id);
                    $("#refundAgentName").val(value.name);
                    $("#refundAgentCode").val(value.code);
                }
                else if (value.name.toUpperCase() === name) {
                    $("#refundAgentCode").val(value.code);
                    $("#refundAgentId").val(value.id);
                    $("#refundAgentName").val(value.name);
                }
            });
        });
        
        //autocomplete
        $("#refundBy").keyup(function (event) {
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            if ($(this).val() === "") {
                $("#refundBy").val("");
                $("#refundByName").val("");
            } else {
                if (event.keyCode === 13) {
                    searchCustomerAutoList(this.value);
                }
            }
        });

        var showflag = 1;
        $("#refundBy").keydown(function () {

            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            if (showflag == 0) {
                $(".ui-widget").css("top", -1000);
                showflag = 1;
            }
        });


        $("#searchCustFrom").keyup(function (event) {
            if (event.keyCode === 13) {
                if ($("#searchCustFrom").val() == "") {
                    // alert('please input data');
                }
                searchCustomerAgentList($("#searchCustFrom").val());
            }
        });
    
    $('#printbutton').click( function() {  
        printTicketRefundSummary();
    });
    
    $('.receivefromdate').datetimepicker().change(function(){                          
        checkReceiveFromDateField();
    });
    $('.receivetodate').datetimepicker().change(function(){                          
        checkReceiveToDateField();
    });
    $('.paidfromdate').datetimepicker().change(function(){                          
        checkPaidFromDateField();
    });
    $('.paidtodate').datetimepicker().change(function(){                          
        checkPaidToDateField();
    });
});      


function printTicketRefundSummary(){
    var refundAgentId = document.getElementById("refundAgentId").value;
    var refundAgentCode = document.getElementById("refundAgentCode").value;
    var refundBy = document.getElementById("refundBy").value;
    var passenger = document.getElementById("passenger").value;
    var sectortoberef = document.getElementById("sectortoberef").value;
    var receiveFromDate = document.getElementById("receiveFromDate").value;
    var receiveToDate = document.getElementById("receiveToDate").value;
    var paidFromDate = document.getElementById("paidFromDate").value;
    var paidToDate = document.getElementById("paidToDate").value;
    var typePrint = document.getElementById("typePrint").value;
    
//          alert(
//          "refundAgentId ::: "+refundAgentId+
//          "refundAgentCode ::: "+refundAgentCode+
//          "refundBy ::: "+refundBy+
//          "passenger ::: "+passenger+
//          "sectortoberef ::: "+sectortoberef+
//          "receiveFromDate ::: "+receiveFromDate+
//          "receiveToDate ::: "+receiveToDate+
//          "paidFromDate ::: "+paidFromDate+
//          "paidToDate ::: "+paidToDate+
//          "typePrint ::: "+typePrint
//          );
    if((receiveFromDate !== '') && (receiveToDate !== '')){
        if(typePrint == 1){
        }else if(typePrint == 2){
        }else{
            $("#reporttypepanel").removeClass("has-success");
            $("#reporttypepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        }   
    } else if((paidFromDate !== '') && (paidToDate !== '')){
        if(typePrint == 1){
        }else if(typePrint == 2){
        }else{
            $("#reporttypepanel").removeClass("has-success");
            $("#reporttypepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        }      
    } else {
        if(typePrint == ""){
            $("#reporttypepanel").removeClass("has-success");
            $("#reporttypepanel").addClass("has-error");
        }
        validateDate();  
    }
}

function checkReceiveFromDateField(){
    var InputToDate = document.getElementById("receiveToDate");
    var inputFromDate = document.getElementById("receiveFromDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#receivefromdatepanel").removeClass("has-error");
        $("#receivetodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#receivefromdatepanel").removeClass("has-success");
        $("#receivetodatepanel").removeClass("has-success");
        $("#receivefromdatepanel").addClass("has-error");
        $("#receivetodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#receivefromdatepanel").removeClass("has-error");
        $("#receivetodatepanel").removeClass("has-error");
        $("#paidfromdatepanel").removeClass("has-error");
        $("#paidtodatepanel").removeClass("has-error");
        $("#receivefromdatepanel").addClass("has-success");
        $("#receivetodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("from","");
    }
}
    
function checkReceiveToDateField(){
    var InputToDate = document.getElementById("receiveToDate");
    var inputFromDate = document.getElementById("receiveFromDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#receivefromdatepanel").removeClass("has-error");
        $("#receivetodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#receivefromdatepanel").removeClass("has-success");
        $("#receivetodatepanel").removeClass("has-success");
        $("#receivefromdatepanel").addClass("has-error");
        $("#receivetodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#receivefromdatepanel").removeClass("has-error");
        $("#receivetodatepanel").removeClass("has-error");
        $("#paidfromdatepanel").removeClass("has-error");
        $("#paidtodatepanel").removeClass("has-error");
        $("#receivefromdatepanel").addClass("has-success");
        $("#receivetodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("to","");
    }       
}

function checkPaidFromDateField(){
    var InputToDate = document.getElementById("paidToDate");
    var inputFromDate = document.getElementById("paidFromDate");
    if(inputFromDate.value === '' && InputToDate.value === ''){
        $("#paidfromdatepanel").removeClass("has-error");
        $("#paidtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){ 
        $("#paidfromdatepanel").removeClass("has-success");
        $("#paidtodatepanel").removeClass("has-success");  
        $("#paidfromdatepanel").addClass("has-error");
        $("#paidtodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#receivefromdatepanel").removeClass("has-error");
        $("#receivetodatepanel").removeClass("has-error");
        $("#paidfromdatepanel").removeClass("has-error");
        $("#paidtodatepanel").removeClass("has-error");
        $("#paidfromdatepanel").addClass("has-success");
        $("#paidtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("paidfrom","");
    }    
}
    
function checkPaidToDateField(){
    var InputToDate = document.getElementById("paidToDate");
    var inputFromDate = document.getElementById("paidFromDate");
    if(inputFromDate.value === '' && InputToDate.value === ''){
        $("#paidfromdatepanel").removeClass("has-error");
        $("#paidtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){ 
        $("#paidfromdatepanel").removeClass("has-success");
        $("#paidtodatepanel").removeClass("has-success");  
        $("#paidfromdatepanel").addClass("has-error");
        $("#paidtodatepanel").addClass("has-error");  
        $("#printbutton").addClass("disabled");
    } else {
        $("#receivefromdatepanel").removeClass("has-error");
        $("#receivetodatepanel").removeClass("has-error");
        $("#paidfromdatepanel").removeClass("has-error");
        $("#paidtodatepanel").removeClass("has-error");
        $("#paidfromdatepanel").addClass("has-success");
        $("#paidtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("paidto","");
    }       
}

function checkDateValue(date){
    var inputFromDate = "";
        var InputToDate = "";
        if((date === 'from') || (date === 'to')){
            inputFromDate = document.getElementById("receiveFromDate");
            InputToDate = document.getElementById("receiveToDate");
        } else {
            inputFromDate = document.getElementById("paidFromDate");
            InputToDate = document.getElementById("paidToDate");
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
            $("#receivefromdatepanel").removeClass("has-success");
            $("#receivefromdatepanel").addClass("has-error");                                 
        }
        if(date === 'to'){
            $("#receivetodatepanel").removeClass("has-success");
            $("#receivetodatepanel").addClass("has-error");
        }
        if(date === 'paidfrom'){
            $("#paidfromdatepanel").removeClass("has-success");
            $("#paidfromdatepanel").addClass("has-error");
        }
        if(date === 'paidto'){
            $("#paidtodatepanel").removeClass("has-success"); 
            $("#paidtodatepanel").addClass("has-error");
        }       
        $("#printbutton").addClass("disabled");
    } else {
        $("#receivefromdatepanel").removeClass("has-success");
        $("#receivetodatepanel").removeClass("has-success");
        $("#paidfromdatepanel").removeClass("has-success");
        $("#paidtodatepanel").removeClass("has-success"); 
        $("#receivefromdatepanel").addClass("has-error");
        $("#receivetodatepanel").addClass("has-error");
        $("#paidfromdatepanel").addClass("has-error");
        $("#paidtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    }
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
   
function searchCustomerAutoList(name) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getAutoListBillto';

    var url = 'AJAXServlet';
    var billArray = [];
    var billListId = [];
    var billListName = [];
    var billListAddress = [];
    var billid, billname, billaddr;
    $("#refundBy").autocomplete("destroy");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            beforeSend: function () {
                $("#dataload").removeClass("hidden");
            },
            success: function (msg) {
                var billJson = JSON.parse(msg);
                var billselect = $("#refundBy").val();
                for (var i in billJson) {
                    if (billJson.hasOwnProperty(i)) {
                        billid = billJson[i].id;
                        billname = billJson[i].name;
                        billaddr = billJson[i].address;
                        billArray.push(billid);
                        billArray.push(billname);
                        billListId.push(billid);
                        billListName.push(billname);
                        billListAddress.push(billaddr);
                        if ((billselect === billid) || (billselect === billname)) {
                            $("#refundBy").val(billListId[i]);
                            $("#refundByName").val(billListName[i]);
                        }
                    }
                    $("#dataload").addClass("hidden");
                }
                // $("#refundBy").val(billid);
                //$("#refundByName").val(billname);

                $("#refundBy").autocomplete({
                    source: billArray,
                    close: function () {
                        $("#refundBy").trigger("keyup");
                        var billselect = $("#refundBy").val();
                        for (var i = 0; i < billListId.length; i++) {
                            if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                                $("#refundBy").val(billListId[i]);
                                $("#refundByName").val(billListName[i]);
                            }
                        }
                    }
                });

                var billval = $("#refundBy").val();
                for (var i = 0; i < billListId.length; i++) {
                    if (billval == billListName[i]) {
                        $("#refundBy").val(billListId[i]);
                    }
                }
                if (billListId.length == 1) {
                    showflag = 0;
                    $("#refundBy").val(billListId[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $("#refundBy").trigger(event);

            }, error: function (msg) {
                console.log('auto ERROR');
                $("#dataload").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

function searchCustomerAgentList(name) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getListBillto';
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                $('#refundCustTable').dataTable().fnClearTable();
                $('#refundCustTable').dataTable().fnDestroy();
                $("#refundCustTable tbody").empty().append(msg);

                $('#refundCustTable').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bPaginate": true,
                    "bInfo": false,
                    "bLengthChange": false,
                    "iDisplayLength": 10
                });
                $("#ajaxload").addClass("hidden");

            }, error: function (msg) {
                $("#ajaxload").addClass("hidden");
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function setBillValue(billto, billname, address, term, pay) {

   $("#refundBy").val(billto);
   $("#refundByName").val(billname);
   $("#refundCustModal").modal('hide');
}
    
</script>
