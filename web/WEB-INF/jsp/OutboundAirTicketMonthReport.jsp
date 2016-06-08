<%-- 
    Document   : OutboundAirTicketMonthReport
    Created on : Dec 1, 2015, 10:16:45 AM
    Author     : chonnasith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="customerAgentList" value="${requestScope['customerAgent']}" />
<section class="content-header"  >
    <h4>
        <b>Report : Outbound Ticket summary by customer </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Outbound Ticket summary by customer</a></li>
    </ol>
</section>
<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/OutboundSummaryMenu.html'"></div>
            </div>
            <div class="form-group">
                <div class="col-md-6">
                    <h3>Outbound Ticket summary by customer</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="OutboundTicketSummaryReportFrom" method="post" class="form-horizontal" >                   
                    <div class="row"> 
                        <div class="col-sm-8">
                            <div class="form-group">
                                <label for="billTo" class="col-sm-6 control-label text-right">Bill To</label>
                                <div class="col-sm-6">
                                    <div class="form-group">
                                        <div class='input-group' >
                                            <input type="hidden" class="form-control" id="receiveFromId" name="receiveFromId" value=""/>
                                            <input type="text" class="form-control" id="receiveFromCode" name="receiveFromCode" maxlength="11" value="" style="text-transform:uppercase"/>
                                            <span class="input-group-addon" id="receive_modal"  data-toggle="modal" data-target="#ReceiveFromModal">
                                                <span class="glyphicon-search glyphicon"></span>
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
                                <label class="col-md-6 control-label text-right"> Bill name</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="receiveFromName" name="receiveFromName" value="" readonly="">  
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right"> From <font style="color: red;">*</font></label>
                                <div class="col-md-6">  
                                    <div class="form-group" id="DateFrom">
                                        <div class='input-group date fromdate' id="fromdatepanel">
                                            <input type='text' id="fromdate" name="fromdate" class="form-control" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY"/>
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
                            <div class="form-group" id="DateTo">
                                <label class="col-md-6 control-label text-right"> To <font style="color: red;">*</font></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <div class='input-group date todate' id="todatepanel">
                                            <input   type='text' id="todate" name="todate" class="form-control" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" />
                                            <span class="input-group-addon"><span  class="glyphicon glyphicon-calendar"></span>
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
                                <label class="col-md-6 control-label text-right" for="ticketUse">Ticket From</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="ticketFrom" id="ticketFrom"  class="form-control">
                                            <option value=""  selected="selected">-- all --</option>
                                            <option value="C" >In</option>
                                            <option value="O" >Out</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="ticketType">Ticket Type</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <select name="ticketType" id="ticketType"  class="form-control">
                                            <option value=""  selected="selected">-- all --</option>
                                            <option value="I">Inter</option>
                                            <option value="D">Domestic</option>
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
                                        <button type="button" onclick="printTicketSummary();" id="printbutton" name="printbutton" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
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
<!--List Receive From Modal-->
<div class="modal fade" id="ReceiveFromModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4  class="modal-title">Receive From</h4>
            </div>
            <div class="modal-body">
                <!-- Receive From List Table-->
                <div style="text-align: right"> <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchReceiveFrom" name="searchReceiveFrom"/> </div> 
                <table class="display" id="ReceiveFromTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th>Code</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${customerAgentList}">
                            <tr>                                
                                <td class="item-billto">${item.billTo}</td>
                                <td class="item-name">${item.billName}</td>                                
                                <td class="item-address hidden">${item.address}</td>
                                <td class="item-tel hidden">${item.tel}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <!--Script Receive From List Table-->
                <script>
                    var showflag = 1;
                    $(document).ready(function() {
                        // Receive From Table
                        var ReceiveFromTable = $('#ReceiveFromTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });

                        $('#ReceiveFromTable tbody').on('click', 'tr', function() {
                            $('.collapse').collapse('show');
                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                            }
                            else {
                                ReceiveFromTable.$('tr.row_selected').removeClass('row_selected');
                                $(this).addClass('row_selected');
                            }
                            var billto = $(this).find(".item-billto").html();
                            var name = $(this).find(".item-name").html();
                            var address = $(this).find(".item-address").html();
                            setBillValue(billto, name, address);
                        });

                        $("#searchReceiveFrom").keyup(function(event) {
                            if (event.keyCode === 13) {
                                if ($("#searchReceiveFrom").val() == "") {
                                    // alert('please input data');
                                }
                                searchCustomerAgentList($("#searchReceiveFrom").val());
                            }
                        });

//                        autocomplete
                        $("#receiveFromCode").keyup(function(event) {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            if ($(this).val() === "") {
                                $("#receiveFromName").val("");
                                $("#receiveFromAddress").val("");
                                $("#arCode").val("");
                            } else {
                                if(event.keyCode === 13){
                                    searchCustomerAutoList(this.value);
                                }
                            }
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                        });
                        $("#receiveFromCode").keydown(function() {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            if (showflag == 0) {
                                $(".ui-widget").css("top", -1000);
                                showflag = 1;
                            }
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                        });
                    });

                    function setBillValue(billto, billname, address) {
                        $("#receiveFromCode").val(billto);
                        $("#arCode").val(billto);
                        $("#receiveFromName").val(billname);
                        if (address == 'null') {
                            $("#receiveFromAddress").val("");
                        } else {
                            $("#receiveFromAddress").val(address);
                        }
                        $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                        $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                        $("#ReceiveFromModal").modal('hide');
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
                        CallAjax(param);
                    }

                    function CallAjax(param) {
                        var url = 'AJAXServlet';
                        $("#ajaxload").removeClass("hidden");
                        try {
                            $.ajax({
                                type: "POST",
                                url: url,
                                cache: false,
                                data: param,
                                success: function(msg) {
                                    $('#ReceiveFromTable').dataTable().fnClearTable();
                                    $('#ReceiveFromTable').dataTable().fnDestroy();
                                    $("#ReceiveFromTable tbody").empty().append(msg);

                                    $('#ReceiveFromTable').dataTable({bJQueryUI: true,
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
                            alert(e);
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
                        CallAjaxAuto(param);
                    }

                    function CallAjaxAuto(param) {
                        var url = 'AJAXServlet';
                        var billArray = [];
                        var billListId = [];
                        var billListName = [];
                        var billListAddress = [];
                        var billid, billname, billaddr;
                        $("#receiveFromCode").autocomplete("destroy");
                        try {
                            $.ajax({
                                type: "POST",
                                url: url,
                                cache: false,
                                data: param,
                                beforeSend: function() {
                                    $("#dataload").removeClass("hidden");
                                },
                                success: function(msg) {
                                    var billJson = JSON.parse(msg);
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
                                        }
                                        $("#dataload").addClass("hidden");
                                    }
                                    $("#receiveFromId").val(billid);
                                    $("#receiveFromName").val(billname);
                                    $("#receiveFromAddress").val(billaddr);

                                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');

                                    $("#receiveFromCode").autocomplete({
                                        source: billArray,
                                        close: function() {
                                            $("#receiveFromCode").trigger("keyup");
                                            var billselect = $("#receiveFromCode").val();
                                            for (var i = 0; i < billListId.length; i++) {
                                                if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                                                    $("#receiveFromCode").val(billListId[i]);
                                                    $("#arCode").val(billListId[i]);
                                                    $("#receiveFromName").val(billListName[i]);
                                                    $("#receiveFromAddress").val(billListAddress[i]);

                                                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                                                    $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                                                }
                                            }
                                        }
                                    });

                                    var billval = $("#receiveFromCode").val();
                                    for (var i = 0; i < billListId.length; i++) {
                                        if (billval == billListName[i]) {
                                            $("#receiveFromCode").val(billListId[i]);
                                            $("#arCode").val(billListId[i]);
                                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                                            $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                                        }
                                    }
                                    if (billListId.length == 1) {
                                        showflag = 0;
                                        $("#receiveFromCode").val(billListId[0]);
                                        $("#arCode").val(billListId[0]);
                                        $('#ReceiptForm').bootstrapValidator('revalidateField', 'receiveFromCode');
                                        $('#ReceiptForm').bootstrapValidator('revalidateField', 'arCode');
                                    }
                                    var event = jQuery.Event('keydown');
                                    event.keyCode = 40;
                                    $("#receiveFromCode").trigger(event);

                                }, error: function(msg) {
                                    console.log('auto ERROR');
                                    $("#dataload").addClass("hidden");
                                }
                            });
                        } catch (e) {
                            alert(e);
                        }
                    }
                </script>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="ButtonBilltoModal" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        $('.datemask').mask('00-00-0000');
        $('.date').datetimepicker();
        $('span').click(function () {
            var position = $(this).offset();
            console.log("positon :"+position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        
        var from = setValueFromDate();
        var to = setValueToDate();
        $("#fromdate").val(from);
        $("#todate").val(to);

        $('.fromdate').datetimepicker().change(function(){                          
            checkFromDateField();
        });
        $('.todate').datetimepicker().change(function(){                          
            checkToDateField();
        });
    });   
    
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
    
    function checkFromDateField(){      
        var inputFromDate = document.getElementById("fromdate");
        var InputToDate = document.getElementById("todate");
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
        var InputToDate = document.getElementById("todate");
        var inputFromDate = document.getElementById("fromdate");
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
        var inputFromDate = document.getElementById("fromdate");
        var InputToDate = document.getElementById("todate");
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
    
    function printTicketSummary() {    
        var ticketfrom = document.getElementById("ticketFrom").value;
        var tickettype = document.getElementById("ticketType").value;
        var startdate = convertFormatDate(document.getElementById("fromdate").value);
        var enddate = convertFormatDate(document.getElementById("todate").value);
        var billto = document.getElementById("receiveFromCode").value;

        if((startdate !== '') && (enddate !== '')){
            window.open("report.smi?name=TicketSummary&ticketfrom=" + ticketfrom + "&tickettype=" + tickettype + "&startdate=" + startdate + "&enddate=" + enddate + "&billto=" + billto + "&department=Outbound");
        }else{    
            validateDate();
        }  
    }
</script>
