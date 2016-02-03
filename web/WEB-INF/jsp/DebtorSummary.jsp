<%-- 
    Document   : DebtorSummary
    Created on : Dec 15, 2015, 2:52:40 PM
    Author     : Jittima
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="listAgent" value="${requestScope['listAgent']}" />

<section class="content-header"  >
    <h4>
        <b>Report : Checking Outbound report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Debtor Summary</a></li>
    </ol>
</section>

<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/CheckingOutboundMenu.html'"></div>
            </div>

            <div class="form-group">
                <div class="col-md-6">
                    <h3>Debtor Summary</h3>
                </div>
            </div>
            
            <div class="col-md-10" >
                <form action="DebtorSummary.smi" method="post" id="DebtorSummaryForm" name="DebtorSummaryForm" role="form">
                    <div class="row">    
                        <div class="col-xs-1 text-right"  style="width: 305px">
                            <label class="control-label text-right">From<font style="color:red">*</font></label>
                        </div>
                        <div class="col-xs-1 form-group" style="width: 290px"> 
                            <div class='input-group date fromdate' id='fromdatepanel'>                    
                                <input id="FromDate" name="FromDate"  type="text" 
                                    class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                            </div>
                        </div>
                    </div>
                    <div class="row">    
                        <div class="col-xs-1 text-right"  style="width: 305px">
                            <label class="control-label text-right">To<font style="color:red">*</font></label>
                        </div>
                        <div class="col-xs-1 form-group" style="width: 290px"> 
                            <div class='input-group date todate' id='todatepanel'>                    
                                <input id="ToDate" name="ToDate"  type="text" 
                                    class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-7">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Department</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="department" id="department"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="Wendy" >Wendy</option>
                                            <option value="Inbound" >Inbound</option>
                                            <option value="Outbound" >Outbound</option>
                                            <option value="WendyOutbound">Wendy + Outbound</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-7">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Type</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="type" id="type"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="V">Vat</option>
                                            <option value="N">No Vat</option>
                                            <option value="A">Ticket</option>
                                            <option value="T">Temp</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-7">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Agent</label>
                                <div class="col-md-3 form-group">  
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="InvTo" name="InvTo" value="" />
                                        <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#AgentModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="InvToName" name="InvToName" value="" readonly="">
                                </div>
                            </div>   
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-7">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Status</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="status" id="status"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="1">Normal</option>
                                            <option value="2">Void</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-7">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Air Ticket Wendy</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="airTicketWendy" id="airTicketWendy"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="airticket">Air Ticket</option>
                                            <option value="package">Package</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-7">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="rept"></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <button type="button" id="printbutton"  name="printbutton"  onclick="printDebtorSummary();" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
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

<div class="modal fade" id="AgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Agent</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->
                <div style="text-align: right"> <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchAgent" name="searchAgent"/> </div> 
                <table class="display" id="AgentTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${listAgent}">
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
                <div  class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog --> <!-- /.modal-dialog -->
</div>              

<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        $('.date').datetimepicker();       
        $('.datemask').mask('0000-00-00');
        
        var ReceiveFromTable = $('#AgentTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });
        
        $('#AgentTable tbody').on('click', 'tr', function () {
            $('.collapse').collapse('show');
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
            }
            else {
                ReceiveFromTable.$('tr.row_selected').removeClass('row_selected');
                var staff_code = $(this).find("td").eq(1).html();
    //            alert("Herree" + staff_code);
                $("#InvToName").val(staff_code);
                $(this).addClass('row_selected');
            }
        });

        //autocomplete
        $("#InvTo").keyup(function(event){   
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left); 
            if($(this).val() === ""){
                $("#InvToId").val("");
                $("#InvToName").val("");
    //            $("#InvToAddress").val("");
            }else{
                if(event.keyCode === 13){
                    searchCustomerAutoList(this.value); 
                }
            }
        });
        $("#InvTo").keydown(function(){
                var position = $(this).offset();
                $(".ui-widget").css("top", position.top + 30);
                $(".ui-widget").css("left", position.left); 
                if(showflag == 0){
                    $(".ui-widget").css("top", -1000);
                    showflag=1;
                }
        });

        $('.fromdate').datetimepicker().change(function(){                          
            checkFromDateField();
        });
        $('.todate').datetimepicker().change(function(){                          
            checkToDateField();
        });
        $("#searchAgent").keyup(function(event) {
        if (event.keyCode === 13) {
            searchAgent($("#searchAgent").val());             
        }
    });
});
    
function checkFromDateField(){      
    var inputFromDate = document.getElementById("FromDate");
    var InputToDate = document.getElementById("ToDate");
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
    var InputToDate = document.getElementById("FromDate");
    var inputFromDate = document.getElementById("ToDate");
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
    
function checkDateValue(date){
    var inputFromDate = document.getElementById("FromDate");
    var InputToDate = document.getElementById("ToDate");
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
        $("#fromdatepanel").removeClass("has-success");
        $("#fromdatepanel").addClass("has-error");                                 
        $("#todatepanel").removeClass("has-success");
        $("#todatepanel").addClass("has-error");   
        $("#printbutton").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success"); 
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    }
}
    
function setBillValue(billto, billname, address, term, pay) {
    $("#InvTo").val(billto);
    $("#InvName").val(billname);
    $("#AgentModal").modal('hide');
}

function printDebtorSummary(){
//    var from = document.getElementById("FromDate").value;
//    var to = document.getElementById("ToDate").value;
//    var department = document.getElementById("department").value;
//    var type = document.getElementById("type").value;
//    var InvTo = document.getElementById("InvTo").value;
//    var status = document.getElementById("status").value;
//    var airTicketWendy = document.getElementById("airTicketWendy").value;
    
    var from = $('#FromDate').val();
    var to = $('#ToDate').val();
    var department = $('#department').val();
    var type = $('#type').val();
    var agent = $('#InvTo').val();
    var status = $('#status').val();
    var airTicketWendy = document.getElementById("airTicketWendy").value;
    
    if((from === '') || (to === '')){
        validateDate();
    } else {
        window.open("report.smi?name=InvoiceSummary"+"&fromdate="+from+"&todate="+to+"&department="+department+"&type="+type+"&agent="+agent+"&status="+status);  
    }  

}

function searchAgent(name){
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getListBillto';
    callAjax(param);
}

function callAjax(param){
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                $('#AgentTable').dataTable().fnClearTable();
                $('#AgentTable').dataTable().fnDestroy();
                $("#AgentTable tbody").empty().append(msg);

                $('#AgentTable').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bPaginate": true,
                    "bInfo": false,
                    "bLengthChange": false,
                    "iDisplayLength": 10
                });
                $("#ajaxload").addClass("hidden");

            }, error: function(msg) {
                $("#ajaxload").addClass("hidden");
                alert('error');
            }
        });
    } catch (e) {
        $("#ajaxload").addClass("hidden");
        alert(e);
    }
}
</script>
