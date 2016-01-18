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
                                            <input type='text' id="billto" name="billto"  class="form-control" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-search" data-toggle="modal" data-target="#BillToModal"></span>
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
                                        <input type='text' readonly="" id="billname" name="billname" class="form-control" />
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
                                            <input type='text' id="fromdate" name="fromdate" class="form-control" data-date-format="YYYY-MM-DD" />
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
                                            <input   type='text' id="todate" name="todate" class="form-control" data-date-format="YYYY-MM-DD"  />
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

<div class="modal fade" id="BillToModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Bill To</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->
                <table class="display" id="BillToTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th>Bill To</th>
                            <th>Bill Name</th>
                            <th>Address</th>
                            <th>Tel</th>
                        </tr>
                    </thead>
                    <script>
                        bill = [];
                    </script>
                    <tbody>
                        <c:forEach var="item" items="${customerAgentList}">
                            <tr>                                
                                <td class="item-billto">${item.billTo}</td>
                                <td class="item-name">${item.billName}</td>                                
                                <td class="item-address ">${item.address}</td>
                                <td class="item-tel ">${item.tel}</td>
                            </tr>
                        <script>
                            bill.push({code: "${item.billTo}", name: "${item.billName}", address: "${item.address}" , tel: "${item.tel}"})
                        </script>
                        </c:forEach>
                            
                    </tbody>

                </table>
                <!--Script Bill To List Table-->
                <script>
                    $(document).ready(function () {
                        var billTo = [];
                        $.each(bill, function (key, value) {
                            billTo.push(value.code);
                            if ( !(value.name in billTo) ){
                               billTo.push(value.name);
                            }
                        });

                        $("#billto").autocomplete({
                            source: billTo,
                            close:function( event, ui ) {
                               $("#billto").trigger('keyup');
                            }
                        });
                        $("#billto").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var code = this.value.toUpperCase();
                            var name = this.value;
                            $("#billname").val(null);
                            $.each(bill, function (key, value) {
                                if (value.code.toUpperCase() === code) {
                                    $("#billname").val(value.name);                                   
                                }
                                if(name === value.name){
                                    $("#billto").val(value.code);
                                    $("#billname").val(value.name);    
                                    code = $("#billto").val().toUpperCase();
                                }
                                
                            });
                        });
                        
                        $("#BillToTable tr").on('click', function () {
                            var billto = $(this).find(".item-billto").text();
                            var billname = $(this).find(".item-name").text();
                            var address = $(this).find(".item-address").text();
                            var tel = $(this).find(".item-tel").text();
                            $("#billto").val(billto);
                            $("#billname").val(billname);
                            $("#address").val(address);
                            $("#BillToModal").modal('hide');
                        });

                        // BillTo Table
                        var BillToTable = $('#BillToTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                        $('#BillToTable tbody').on('click', 'tr', function () {
                            $('.collapse').collapse('show');
                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                            }
                            else {
                                BillToTable.$('tr.row_selected').removeClass('row_selected');
                                $(this).addClass('row_selected');
                            }
                        });

                    });

                </script>

            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () { 
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
    
    function printTicketSummary() {    
        var ticketfrom = document.getElementById("ticketFrom").value;
        var tickettype = document.getElementById("ticketType").value;
        var startdate = document.getElementById("fromdate").value;
        var enddate = document.getElementById("todate").value;
        var billto = document.getElementById("billto").value;

        if((startdate !== '') && (enddate !== '')){
            window.open("report.smi?name=TicketSummary&ticketfrom=" + ticketfrom + "&tickettype=" + tickettype + "&startdate=" + startdate + "&enddate=" + enddate + "&billto=" + billto + "&department=Outbound");
        }else{    
            validateDate();
        }  
    }
</script>
