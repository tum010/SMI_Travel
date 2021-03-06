<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/TicketRefundSummary.js"></script>--> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">
<link href="css/selectize.bootstrap3.css" rel="stylesheet">

<c:set var="agent" value="${requestScope['agent']}" />
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
                                <label class="control-label text-right">Request Airline</label>
                            </div>
                            <div class="col-xs-1"  style="width: 150px">
                                <div class="input-group" id="refundAgentCodeValidate">
                                    <input type="hidden" class="form-control" id="refundAgentId" name="refundAgentId" value="${refundAirline.agent.id}" />
                                    <input type="text" class="form-control" id="refundAgentCode" name="refundAgentCode" value="${refundAirline.agent.code}"
                                        data-bv-notempty="true" data-bv-notempty-message="The refund agent is required">
                                     <span id="RefundAgentModal" class="input-group-addon" data-toggle="modal" data-target="#RefundAgentModalModal">
                                         <span id="SpanGlyphiconSearch" class="glyphicon-search glyphicon"></span>
                                     </span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-left" style="width: 220px">
                                <input type="text" class="form-control" id="refundAgentName" 
                                       name="refundAgentName" value="${refundAirline.agent.name}" readonly="">                           
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
                                            <input type='text' id="receiveFromDate" name="receiveFromDate" class="form-control datemask" placeholder="DD-MM-YYYY" data-date-format="DD-MM-YYYY"/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <label class="col-md-1 control-label text-right" style="width: 3px">To</label>
                                <div class="col-md-1" style="width: 170px">
                                    <div class="form-group" id="receivetodatepanel">
                                        <div class='input-group date receivetodate' id='receiveDateTo'>
                                            <input type='text' id="receiveToDate" name="receiveToDate" class="form-control datemask" placeholder="DD-MM-YYYY" data-date-format="DD-MM-YYYY"/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
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
                                            <input type='text' id="paidFromDate" name="paidFromDate" class="form-control datemask" placeholder="DD-MM-YYYY" data-date-format="DD-MM-YYYY"/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <label class="col-md-1 control-label text-right" style="width: 3px">To</label>
                                <div class="col-md-1" style="width: 170px">
                                    <div class="form-group" id="paidtodatepanel">
                                        <div class='input-group date paidtodate' id='paidDateTo'>
                                            <input type='text' id="paidToDate" name="paidToDate" class="form-control datemask" placeholder="DD-MM-YYYY" data-date-format="DD-MM-YYYY"/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>        
<!--                    <div class="row">
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
                    </div>-->
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="rept"></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <button type="button" id="printbutton"  name="printbutton"  class="btn btn-success" onclick="printTicketRefundSummary();"><span class="glyphicon glyphicon-print"></span> Print</button>
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
<div class="modal fade" id="RefundAgentModalModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Refund Agent</h4>
            </div>
            <div class="modal-body">
                <!--Owner List Table-->
                <table class="display" id="AgentRefundTable">
                    <thead>                        
                    <script>
                        staff = [];
                    </script>
                    <tr class="datatable-header">
                        <th class="hidden">ID</th>
                        <th>User</th>
                        <th>Name</th>
                        <th class="hidden">Position</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="staff" items="${agent}">
                            <tr >
                                <td class="staff-id hidden">${staff.id}</td>
                                <td class="staff-username">${staff.code}</td>
                                <td class="staff-name">${staff.name}</td>
                                <td class="staff-position hidden"></td>
                            </tr>
                        <script>
                            staff.push({id: "${staff.id}", username: "${staff.code}", name: "${staff.name}", position: ""});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" id="RefundAgentModalClose"  class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
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
   
        var codeStaff = [];
        $.each(staff, function (key, value) {
            codeStaff.push(value.username);
            if ( !(value.name in codeStaff) ){
               codeStaff.push(value.name);
            }
        });
        console.log(codeStaff);
        $("#refundAgentCode").autocomplete({
            source: codeStaff,
            close:function( event, ui ) {
               $("#refundAgentCode").trigger('keyup');
            }
        });
        $("#refundAgentCode").on('keyup', function () {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var username = this.value.toUpperCase();
            var name = this.value.toUpperCase();
            $("#refundAgentId,#refundAgentName").val(null);
            $.each(staff, function (key, value) {
                if (value.username.toUpperCase() === username && username.length > 1) {
                    $("#refundAgentId").val(value.id);
                    $("#refundAgentName").val(value.name);
                }
                if(name === value.name.toUpperCase() && name.length > 1){
                    $("#refundAgentId").val(value.id);
                    $("#refundAgentCode").val(value.username);
                    $("#refundAgentName").val(value.name);
                    username = $("#refundAgentCode").val().toUpperCase();
                }
            });
        });

        $("#AgentRefundTable tr").on('click', function () {
            var staff_id = $(this).find(".staff-id").text();
            var staff_username = $(this).find(".staff-username").text();
            var staff_name = $(this).find(".staff-name").text();
            $("#refundAgentId").val(staff_id);
            $("#refundAgentCode").val(staff_username);
            $("#refundAgentName").val(staff_name);
            console.log(staff_id);
            $("#RefundAgentModalModal").modal('hide');
        });
        // OWNER TABLE AND ISSUE TABLE SET DATATABLE
        $('#AgentRefundTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });

    });
    $(document).ready(function () {
        $('.datemask').mask('00-00-0000');
        $('.date').datetimepicker();
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
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
                if ($("#searchCustFrom").val() === "") {
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
    var refundByName = document.getElementById("refundByName").value;
    var refundBy = document.getElementById("refundBy").value;
    var passenger = document.getElementById("passenger").value;
    var receiveFromDate = convertFormatDate(document.getElementById("receiveFromDate").value);
    var receiveToDate = convertFormatDate(document.getElementById("receiveToDate").value);
    var paidFromDate = convertFormatDate(document.getElementById("paidFromDate").value);
    var paidToDate = convertFormatDate(document.getElementById("paidToDate").value);
    var sectortoberef = document.getElementById("sectortoberef").value;
//    var typePrint = document.getElementById("typePrint").value;
    
    if((receiveFromDate !== '') && (receiveToDate !== '')){
//        if(typePrint === 1){
//            window.open("Excel.smi?name=RefundTicketDetail&refundAgentId=" + refundAgentId 
//                + "&refundAgentName=" + refundAgentName 
//                + "&refundBy=" + refundBy 
//                + "&passenger=" + passenger 
//                + "&receiveFromDate=" + receiveFromDate 
//                + "&receiveToDate=" + receiveToDate 
//                + "&paidFromDate=" + paidFromDate 
//                + "&paidToDate=" + paidToDate
//                + "&sectortoberef=" + sectortoberef);
//        }else if(typePrint === 2){
            window.open("Excel.smi?name=RefundTicketDetail&refundAgentId=" + refundAgentId 
                + "&refundByName=" + refundByName 
                + "&refundBy=" + refundBy 
                + "&passenger=" + passenger 
                + "&receiveFromDate=" + receiveFromDate 
                + "&receiveToDate=" + receiveToDate 
                + "&paidFromDate=" + paidFromDate 
                + "&paidToDate=" + paidToDate
                + "&sectortoberef=" + sectortoberef);
//        }else{
//            $("#reporttypepanel").removeClass("has-success");
//            $("#reporttypepanel").addClass("has-error");
//            $("#printbutton").addClass("disabled");
//        }   
    } else if((paidFromDate !== '') && (paidToDate !== '')){
//        if(typePrint === 1){
            window.open("Excel.smi?name=RefundTicketDetail&refundAgentId=" + refundAgentId 
                + "&refundByName=" + refundByName 
                + "&refundBy=" + refundBy 
                + "&passenger=" + passenger 
                + "&receiveFromDate=" + receiveFromDate 
                + "&receiveToDate=" + receiveToDate 
                + "&paidFromDate=" + paidFromDate 
                + "&paidToDate=" + paidToDate
                + "&sectortoberef=" + sectortoberef);
//        }else if(typePrint === 2){
//            window.open("Excel.smi?name=RefundTicketDetail&refundAgentId=" + refundAgentId 
//                + "&refundAgentName=" + refundAgentName 
//                + "&refundBy=" + refundBy 
//                + "&passenger=" + passenger 
//                + "&receiveFromDate=" + receiveFromDate 
//                + "&receiveToDate=" + receiveToDate 
//                + "&paidFromDate=" + paidFromDate 
//                + "&paidToDate=" + paidToDate
//                + "&sectortoberef=" + sectortoberef);   
//        }else{
//            $("#reporttypepanel").removeClass("has-success");
//            $("#reporttypepanel").addClass("has-error");
//            $("#printbutton").addClass("disabled");
//        }      
    } else {
//        if(typePrint === ""){
//            $("#reporttypepanel").removeClass("has-success");
//            $("#reporttypepanel").addClass("has-error");
//        }else{
         validateDate(); 
//        }
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
        var fromDate = (convertFormatDate(inputFromDate.value)).split('-');
        var toDate = (convertFormatDate(InputToDate.value)).split('-');
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
//    alert("1");
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
        $("#printbutton").removeClass("disabled");
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
    name = generateSpecialCharacter(name);
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
    name = generateSpecialCharacter(name);
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

function setRefundAgentValue(id, code, name, term, pay) {
alert("HH");
   $("#refundAgentId").val(id);
   $("#refundAgentCode").val(code);
   $("#refundAgentName").val(name);
   $("#RefundAgentModalModal").modal('hide');
}
    
</script>
