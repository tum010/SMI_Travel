<%-- 
    Document   : AirTicketMonthlyReport
    Created on : Dec 23, 2014, 11:45:58 AM
    Author     : wleenavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script type="text/javascript" src="js/TicketSummary.js"></script>--> 
<link href="css/jquery-ui.css" rel="stylesheet">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="customerAgentList" value="${requestScope['customerAgent']}" />
<section class="content-header"  >
    <h4>
        <b>Report : Air Ticket report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Ticket summary</a></li>
    </ol>
</section>

<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/PackageTourHotelMenu.html'"></div>
            </div>
            
        
            <div class="form-group">
                <div class="col-md-6">
                    <h3>Ticket summary by customer</h3>
                </div>
            </div>
            <div class="col-md-10" >
            <form role="form" id="ticketsummary" class="form-horizontal">
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
                        <label class="col-md-6 control-label text-right"> From Date <font style="color: red;">*</font></label>
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
                        <label class="col-md-6 control-label text-right"> To Date <font style="color: red;">*</font></label>
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
                                <button type="button" onclick="printTicketSummary();" id="printbutton" name="printbutton"  class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
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






<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        $('.date').datetimepicker();
        $('span').click(function () {
            var position = $(this).offset();
            console.log("positon :"+position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
            
        });



    });
</script>

<div class="modal fade" id="BillToModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Bill To</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->
                <div style="text-align: right"> <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchBillTo" name="searchBillTo" placeholder="Bill To,Name"/> </div> 
                <table class="display" id="BillToTable" style="table-layout: fixed;">
                    <thead>                        
                        <tr class="datatable-header">
                            <th style="width: 15%;">Bill To</th>
                            <th style="width: 30%;">Bill Name</th>
                            <th style="width: 35%;">Address</th>
                            <th style="width: 20%;">Tel</th>
                        </tr>
                    </thead>
                    <script>
                        bill = [];
                    </script>
                    <tbody>
                        <c:forEach var="item" items="${customerAgentList}">
                            <tr onclick="setBillValue('${item.billTo}', '${item.billName}', '${item.address}', '${item.term}', '${item.pay}');">                             
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
                                                // BillTo Table
                        var BillToTable = $('#BillToTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": false,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                        var billTo = [];
                        $.each(bill, function (key, value) {
                            billTo.push(value.code);
                            if ( !(value.name in billTo) ){
                               billTo.push(value.name);
                            }
                        });
                                        //autocomplete
                        $("#billto").keyup(function (event) {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            if ($(this).val() === "") {
                                $("#billto").val("");
                                $("#billname").val("");
                            } else {
                                if (event.keyCode === 13) {
                                    searchCustomerAutoList(this.value);
                                }
                            }
                        });

                        var showflag = 1;
                        $("#billto").keydown(function () {

                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            if (showflag == 0) {
                                $(".ui-widget").css("top", -1000);
                                showflag = 1;
                            }
                        });
                        
                        $("#searchBillTo").keyup(function(event) {
                            if (event.keyCode === 13) {
                                searchBillTo($("#searchBillTo").val());             
                            }
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
        
//        var from = setValueFromDate();
//        var to = setValueToDate();
//        $("#fromdate").val(from);
//        $("#todate").val(to);

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
        var fromdatepanel = document.getElementById("fromdatepanel");
        
        if((startdate !== '') && (enddate !== '')){
            window.open("report.smi?name=TicketSummary&ticketfrom=" + ticketfrom + "&tickettype=" + tickettype + "&startdate=" + startdate + "&enddate=" + enddate + "&billto=" + billto);
        }else{    
            validateDate();
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
        $("#billto").autocomplete("destroy");
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
                    var billselect = $("#billto").val();
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
                                $("#billto").val(billListId[i]);
                                $("#billname").val(billListName[i]);
                            }
                        }
                        $("#dataload").addClass("hidden");
                    }
                    // $("#refundBy").val(billid);
                    //$("#refundByName").val(billname);

                    $("#billto").autocomplete({
                        source: billArray,
                        close: function () {
                            $("#billto").trigger("keyup");
                            var billselect = $("#billto").val();
                            for (var i = 0; i < billListId.length; i++) {
                                if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                                    $("#billto").val(billListId[i]);
                                    $("#billname").val(billListName[i]);
                                }
                            }
                        }
                    });

                    var billval = $("#billto").val();
                    for (var i = 0; i < billListId.length; i++) {
                        if (billval == billListName[i]) {
                            $("#billto").val(billListId[i]);
                        }
                    }
                    if (billListId.length == 1) {
                        showflag = 0;
                        $("#billto").val(billListId[0]);
                    }
                    var event = jQuery.Event('keydown');
                    event.keyCode = 40;
                    $("#billto").trigger(event);

                }, error: function (msg) {
                    console.log('auto ERROR');
                    $("#dataload").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }
    
    function searchBillTo(name){
        var servletName = 'BillableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&name=' + name +
                '&type=' + 'getListBilltoOverdueSummary';
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
                    $('#BillToTable').dataTable().fnClearTable();
                    $('#BillToTable').dataTable().fnDestroy();
                    $("#BillToTable tbody").empty().append(msg);

                    $('#BillToTable').dataTable({bJQueryUI: true,
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
    
    function setBillValue(billto, billname, address, term, pay) {
        $("#billto").val(billto);
        $("#billname").val(billname);
        $("#BillToModal").modal('hide');
    }
</script>