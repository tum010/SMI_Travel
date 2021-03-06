<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/TicketFareSummaryReport.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="airlineList" value="${requestScope['airlineList']}" />
<c:set var="airlineCodeList" value="${requestScope['airlineCodeList']}" />
<c:set var="userList" value="${requestScope['userList']}" />
<c:set var="termPayList" value="${requestScope['termPayList']}" />

<section class="content-header"  >
    <h4>
        <b>Report : Air Ticket report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Ticket Fare summary</a></li>
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
                    <h3>Ticket Fare summary</h3>
                </div>
            </div>
            
            <div class="col-md-10" >
                <form action="TicketFareSummaryReport.smi" method="post" id="TicketFareSummaryReport" name="TicketFareSummaryReport" role="form">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Report Type<font style="color: red">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group" id="reporttypepanel">
                                        <select name="reportType" id="reportType"  class="form-control" onchange="jsFunction(this.value);">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="1">Ticket Fare Airline</option>
                                            <option value="2">Ticket Fare Invoice</option>
                                            <option value="3">Ticket Fare Agent</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Ticket Type</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="ticketType" id="ticketType"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="B" >BSP</option>
                                            <option value="D" >DOMESTIC</option>
                                            <option value="A" >AGENT</option>
                                            <option value="TI" >TG INTER</option>
                                            <option value="TD" >TG DOMESTIC</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Ticket Buy</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="ticketBuy" id="ticketBuy" class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="C" >IN</option>
                                            <option value="O" >OUT</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Airline Code</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="airline" id="airline"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:forEach var="table" items="${airlineList}" >
                                                <c:set var="select" value="" />
                                                <option value="${table.id}" ${select}>${table.code}</option>  
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
                                <label class="col-md-6 control-label text-right" >Airline</label>
                                <div class="col-md-5">  
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
                            <div class="form-group" id="issuefromdatepanel">
                                <label class="col-md-6 control-label text-right">Issue Date From<font style="color: red"></font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date issuefromdate' id='DateFromIssue'>
                                            <input type='text' id="issueFrom" name="issueFrom" class="form-control datemask" placeholder="DD-MM-YYYY" data-date-format="DD-MM-YYYY"/>
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
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date issuetodate' id='DateToIssue'>
                                            <input type='text' id="issueTo" name="issueTo" class="form-control datemask" placeholder="DD-MM-YYYY" data-date-format="DD-MM-YYYY" />
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
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date fromdate' id='DateFrom'>
                                            <input type='text' id="invoiceFromDate" name="invoiceFromDate" class="form-control datemask" placeholder="DD-MM-YYYY" data-date-format="DD-MM-YYYY"/>
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
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date todate' id='DateTo'>
                                            <input type='text' id="invoiceToDate" name="invoiceToDate"  class="form-control datemask" placeholder="DD-MM-YYYY" data-date-format="DD-MM-YYYY" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>        
                     
<!--                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="fromdatepanel">
                                <label class="col-md-6 control-label text-right">From<font style="color: red">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date' id='fromdate'>
                                            <input type='text' id="startdate" name="startdate" class="form-control" data-date-format="DD-MM-YYYY"/>
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
                                <label class="col-md-6 control-label text-right">To<font style="color: red">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date' id='todate'>
                                            <input type='text' id="enddate" name="enddate"  class="form-control" data-date-format="DD-MM-YYYY" />
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
                                <label class="col-md-6 control-label text-right" >Department</label>
                                <div class="col-md-5">  
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
                                <div class="col-md-2 form-group">  
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
                                <div class="col-md-5">  
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
                                        <button type="button" id="printbutton"  name="printbutton"  onclick="printTicketFareSummary();" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
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
                                
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $('.date').datetimepicker({
            
        });
        $('.datemask').mask('00-00-0000');
        $('span').click(function(){
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });
    
    $("#printbutton").addClass("disabled");
         
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