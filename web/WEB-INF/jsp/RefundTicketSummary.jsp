<%-- 
    Document   : RefundTicketSummaryReport
    Created on : Dec 18, 2015, 3:01:40 PM
    Author     : Jittima
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
        <b>Report : Refund Ticket Summary </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Refund Ticket Summary</a></li>
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
                    <h3>Refund Ticket Summary</h3>
                </div>
            </div>
            <div class="col-md-10" >
            <form role="form" id="RefundTicketSummaryForm" class="form-horizontal">
            <div class="row"> 
                <div class="col-sm-8">
                    <div class="form-group">
                        <label for="billTo" class="col-sm-6 control-label text-right">Refund By</label>
                        <div class="col-sm-6">
                            <div class="form-group">
                                 <div class='input-group' >
                                    <input type='text' id="refundBy" name="refundBy"  class="form-control" />
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-search" data-toggle="modal" data-target="#refundCustModal"></span>
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
                        <label class="col-md-6 control-label text-right">Refund Name</label>
                        <div class="col-md-6">  
                            <div class="form-group">
                                <input type='text' readonly="" id="refundByName" name="refundByName" class="form-control" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="col-md-6 control-label text-right">Ticket From<font style="color: red"></font></label>
                        <div class="col-md-6">  
                            <div class="form-group">
                                <div class='input-group date Fromdate' id='fromdatepanel'>
                                    <input type='text' id="fromdate" name="fromdate" class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY"/>
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
                        <label class="col-md-6 control-label text-right">Ticket To<font style="color: red"></font></label>
                        <div class="col-md-6">  
                            <div class="form-group">
                                <div class='input-group date Todate' id='todatepanel'>
                                    <input type='text' id="todate" name="todate"  class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY"/>
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
                    <div class="form-group" id="refundfromdatepanel">
                        <label class="col-md-6 control-label text-right">Refund From<font style="color: red">*</font></label>
                        <div class="col-md-6">  
                            <div class="form-group">
                                <div class='input-group date' id='RefundDateFrom'>
                                    <input type='text' id="refundFrom" name="refundFrom" class="form-control datemask" data-date-format="DD-MM-YYYY"/>
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
                    <div class="form-group" id="refundtodatepanel">
                        <label class="col-md-6 control-label text-right">From To<font style="color: red">*</font></label>
                        <div class="col-md-6">  
                            <div class="form-group">
                                <div class='input-group date' id='RefundDateTo'>
                                    <input type='text' id="refundTo" name="refundTo"  class="form-control datemask" data-date-format="DD-MM-YYYY" />
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
                        <label class="col-md-6 control-label text-right" for="rept"></label>
                        <div class="col-md-6">  
                            <div class="form-group">
                                <button type="button"  id="printbutton" name="printbutton"  onclick="printRefundSummary();" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
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
                        <c:forEach var="item" items="${customerAgentList}">
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
        $('.date').datetimepicker({
        });
        $('.datemask').mask('00-00-0000');
        
        $('span').click(function () {
            var position = $(this).offset();
            console.log("positon :"+position.top);
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
//        var from = setValueFromDate();
//        var to = setValueToDate();
//        $("#fromdate").val(from);
//        $("#todate").val(to);
        
        
        $("#RefundTicketSummaryForm")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    refundFrom: {
                        trigger: 'focus keyup change',
                            validators: {
                                date: {
                                    format: 'DD-MM-YYYY',
                                    max: 'refundTo',
                                    message: 'The Refund From is not a valid'
                                },notEmpty: {
                                    message: 'The Refund From is required'
                                }
                            }
                    },
                    refundTo: {
                        trigger: 'focus keyup change',
                            validators: {
                                date: {
                                    format: 'DD-MM-YYYY',
                                    min: 'refundFrom',
                                    message: 'The Refund To is not a valid'
                                },notEmpty: {
                                    message: 'The Refund To is required'
                                }
                            }
                    }
                }
            }).on('success.field.fv', function (e, data) {
//                alert("1");
                if (data.field === 'refundFrom' && data.fv.isValidField('refundTo') === false) {
                    data.fv.revalidateField('refundTo');
                }

                if (data.field === 'refundTo' && data.fv.isValidField('refundFrom') === false) {
                    data.fv.revalidateField('refundFrom');
                }
            });
            
            $('#RefundDateFrom').datetimepicker().on('dp.change', function (e) {
                $('#RefundTicketSummaryForm').bootstrapValidator('revalidateField', 'refundFrom');
                $('#RefundTicketSummaryForm').bootstrapValidator('revalidateField', 'refundTo');
                var fromdate = convertFormatDate(document.getElementById("refundFrom").value);
                var todate = convertFormatDate(document.getElementById("refundTo").value);
                if(((fromdate !== '') && (todate !== '')) && fromdate < todate){
                    $("#printbutton").removeClass("disabled");
                }else if((((fromdate !== '') && (todate !== '')) && fromdate === todate)) {
                    $("#printbutton").removeClass("disabled");
                }else{
                    $("#printbutton").addClass("disabled");
                }
            });
            
            $('#RefundDateTo').datetimepicker().on('dp.change', function (e) {
                $('#RefundTicketSummaryForm').bootstrapValidator('revalidateField', 'refundFrom');
                $('#RefundTicketSummaryForm').bootstrapValidator('revalidateField', 'refundTo');
                var fromdate = convertFormatDate(document.getElementById("refundFrom").value);
                var todate = convertFormatDate(document.getElementById("refundTo").value);
                if(((fromdate !== '') && (todate !== '')) && fromdate < todate){
                    $("#printbutton").removeClass("disabled");
                }else if((((fromdate !== '') && (todate !== '')) && fromdate === todate)) {
                    $("#printbutton").removeClass("disabled");
                }else{
                    $("#printbutton").addClass("disabled");
                }
            });    
            
        $('.Fromdate').datetimepicker().change(function(){                          
            checkFromDateField();
        });
        $('.Todate').datetimepicker().change(function(){                          
            checkToDateField();
        });
        
        
    });
    
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
            $("#fromdatepanel").addClass("has-success");
            $("#todatepanel").addClass("has-success");
            $("#printbutton").removeClass("disabled");
            checkDateValue("from","");
        }
    }
    
    function checkToDateField(){
        var InputToDate = document.getElementById("fromdate");
        var inputFromDate = document.getElementById("todate");
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
    
    function printRefundSummary(){
        var fromdate = convertFormatDate(document.getElementById("fromdate").value);
        var todate = convertFormatDate(document.getElementById("todate").value);
        var refundFrom = convertFormatDate(document.getElementById("refundFrom").value);
        var refundTo = convertFormatDate(document.getElementById("refundTo").value);
        var refundBy = document.getElementById("refundBy").value;
        $('#RefundTicketSummaryForm').bootstrapValidator('revalidateField', 'refundTo');
        $('#RefundTicketSummaryForm').bootstrapValidator('revalidateField', 'refundFrom');
        if(((refundFrom !== '') && (refundTo !== '')) && refundFrom < refundTo){
            window.open("report.smi?name=RefundTicketSummaryReport&refundFrom=" + refundFrom + "&refundTo=" + refundTo + "&refundBy=" + refundBy + "&ticketFrom=" + fromdate + "&ticketTo=" + todate);
        }else if((((refundFrom !== '') && (refundTo !== '')) && refundFrom === refundTo)) {
            window.open("report.smi?name=RefundTicketSummaryReport&refundFrom=" + refundFrom + "&refundTo=" + refundTo + "&refundBy=" + refundBy + "&ticketFrom=" + fromdate + "&ticketTo=" + todate);
        }else{
            $('#RefundTicketSummaryForm').bootstrapValidator('revalidateField', 'refundTo');
            $('#RefundTicketSummaryForm').bootstrapValidator('revalidateField', 'refundFrom');
        }
    }
    
    function checkValidateRefundTo(){
        $('#RefundTicketSummaryForm').bootstrapValidator('revalidateField', 'refundTo');
    }
    function checkValidateRefundFrom(){
        $('#RefundTicketSummaryForm').bootstrapValidator('revalidateField', 'refundFrom');
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
</script>

