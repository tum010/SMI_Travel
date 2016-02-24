<%-- 
    Document   : ProfitLoss
    Created on : Dec 15, 2015, 2:52:50 PM
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

<c:set var="invSupList" value="${requestScope['invSupList']}" />
<c:set var="userList" value="${requestScope['staffList']}" />
<c:set var="productList" value="${requestScope['productList']}" />
<c:set var="listCity" value="${requestScope['cityList']}" />

<section class="content-header"  >
    <h4>
        <b>Report : Checking Outbound report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Profit & Loss</a></li>
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
                    <h3>Profit & Loss</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="ProfitLossFrom" name="ProfitLossFrom" method="post" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right">Departure Date From</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date departfromdate' id='departfromdatepanel'>                    
                                            <input id="departFromDate" name="departFromDate"  type="text" 
                                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Departure Date To</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date departtodate' id='departtodatepanel'>                    
                                            <input id="departToDate" name="departToDate"  type="text" 
                                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>   
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right">Invoice Date From</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date invfromdate' id='invfromdatepanel'>                    
                                            <input id="invFromDate" name="invFromDate"  type="text" 
                                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Invoice Date To</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date invtodate' id='invtodatepanel'>                    
                                            <input id="invToDate" name="invToDate"  type="text" 
                                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Owner</label>
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
                                    <input type="text" class="form-control" id="salebyName" name="salebyName" value="" readonly="" style="width: 132px">
                                </div>
                            </div>   
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-left" >City</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectCity" id="SelectCity"  class="form-control selectize">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listCity}" >
                                                <c:if test="}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
<!--                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-left" >City</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectCity" id="SelectCity"  class="form-control selectize">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listCity}" >
                                                <c:if test="}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>-->
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Product</label>
                                <div class="col-md-3 form-group">  
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="InputProductId" name="InputProductId" value=""/>
                                        <input type="text" class="form-control" id="InputProductCode" name="InputProductCode" value="" />
                                        <span id="agentSearchButton" name="agentSearchButton" class="input-group-addon" data-toggle="modal" data-target="#ProductModal">
                                            <span id="agentSearchButtonIcon" name="agentSearchButtonIcon" class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="InputProductName" name="InputProductName" value="" readonly="" style="width: 132px">
                                </div>
                            </div>   
                        </div>
                    </div>                    
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Invoice Sup</label>
                                <div class="col-md-3 form-group">  
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="invSupId" name="invSupId" value=""/>
                                        <input type="text" class="form-control" id="invSupCode" name="invSupCode" value="" />
                                        <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchInvoiceSup">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="invSupName" name="invSupName" value="" readonly="" style="width: 132px">
                                </div>
                            </div>   
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right">Pay Date From</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date payfromdate' id='payfromdatepanel'>                    
                                            <input id="payFromDate" name="payFromDate"  type="text" 
                                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Pay Date To</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date paytodate' id='paytodatepanel'>                    
                                            <input id="payToDate" name="payToDate"  type="text" 
                                                class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                       
                                        </div>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>  
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Type</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="selectGroup" id="selectGroup" class="form-control" >
                                            <option value="OWNER" >OWNER</option>
                                            <option value="PRODUCT TYPE" >PRODUCT TYPE</option>
                                            <option value="CITY" >CITY</option>
                                            <option value="CLIENT NAME" >CLIENT NAME</option>
                                            <option value="DEPARTURE DATE" >DEPARTURE DATE</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right"></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <button type="button"  class="btn btn-success" id="printbutton"  name="printbutton" onclick="printProfitLoss();"><span class="glyphicon glyphicon-print" ></span> Print</button>
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

<div class="modal fade" id="SearchInvoiceSup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Invoice Supplier</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="SearchInvoicSupTable">
                    <thead class="datatable-header">
                        <script>
                            var invoiceSup = [];
                        </script>
                        <tr>
                            <th class="hidden">Id</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th>AP code</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="invSup" items="${invSupList}">
                            <tr onclick ="setupInvSupValue('${invSup.id}', '${invSup.code}', '${invSup.name}', '${invSup.apcode}')" >
                                <td class="hidden">${invSup.id}</td>
                                <td>${invSup.code}</td>
                                <td>${invSup.name}</td>
                                <td>${invSup.apcode}</td> 
                            </tr>
                            <script>
                                invoiceSup.push({id: "${invSup.id}", code: "${invSup.code}", name: "${invSup.name}", apcode: "${invSup.apcode}"});
                            </script>
                        </c:forEach>    
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="SearchInvoiceSupOK" name="SearchInvoiceSupOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchInvoiceSupClose" name="SearchInvoiceSupClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->


<!--Product Modal-->
<div class="modal fade" id="ProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Product</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="productTable" name="productTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th style="width:20%">Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <script>
                        agentArray = [];
                    </script>
                    <tbody>
                        <c:forEach var="product" items="${productList}">
                            <tr>
                                <td class="object-id hidden">${product.id}</td>
                                <td class="object-code">${product.code}</td>
                                <td class="object-name">${product.name}</td>
                            </tr>
                            <script>
                                agentArray.push({id: "${product.id}", code: "${product.code}", name: "${product.name}"});
                            </script>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Script Product List table-->
            <script>
                $(document).ready(function () {
                    $("#productTable tr").on('click', function () {
                        agent_id = $(this).find(".object-id").text();
                        agent_code = $(this).find(".object-code").text();
                        agent_name = $(this).find(".object-name").text();
                        $("#InputProductId").val(agent_id);
                        $("#InputProductCode").val(agent_code);
                        $("#InputProductName").val(agent_name);
//                        alert("Tour id[" + $("#InputTourId").val() + "] name[" + $("#InputTourName").val() + "] code[" + $("#InputTourCode").val() + "]");
                        $("#ProductModal").modal('hide');
                    });
                    // productTable
                    var productTable = $('#productTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": true,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });
                    $('#productTable tbody').on('click', 'tr', function () {
                        //$('.collapse').collapse('show');
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                        }
                        else {
                            productTable.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                        }

                    });
                    // ON KEY INPUT AUTO SELECT TOURCODE-TOURNAME
                    $(function () {
                        var availableTags = [];
//                                console.log(tourCode);
                        $.each(agentArray, function (key, value) {
                            availableTags.push(value.code);
                        });
//                                console.log(availableTags);
                        $("#InputProductCode").autocomplete({
                            source: availableTags,
                            close:function( event, ui ) {
                               //window.uiTmp = event;
                               //window.uiTmp = ui;
                               //alert('Test');    
                               $("#InputProductCode").trigger('keyup');
                            }
                        });
                        $("#InputProductCode").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var code = this.value.toUpperCase();
                            $("#InputProductName").val(null);
                            $.each(agentArray, function (key, value) {
                                //console.log('each : ' + value.code);
                                //console.log('val : ' + $("#agent_user").val());
                                if (value.code.toUpperCase() === code) {
//                                console.log('ok');
                                    $("#InputProductId").val(value.id);
                                    $("#InputProductName").val(value.name);
                                }
                            }); //end each agentTable


                        }); // end InputAgentCode keyup
                    }); // end AutoComplete AgentCode AgentName

//                    $('.date').mask('0000-00-00');    
                });
            </script>
            <div class="modal-footer">
                <button id="" type="button" onclick="" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>           
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
    $('.date').datetimepicker();
    $('.datemask').mask('0000-00-00', {reverse: true});
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });
    
    $("#printbutton").addClass("disabled");
         
    $('.departfromdate').datetimepicker().change(function(){                          
        checkDepartFromDateField();
    });
    $('.departtodate').datetimepicker().change(function(){                          
        checkDepartToDateField();
    });
    $('.payfromdate').datetimepicker().change(function(){                          
        checkPayFromDateField();
    });
    $('.paytodate').datetimepicker().change(function(){                          
        checkPayToDateField();
    });
    $('.invfromdate').datetimepicker().change(function(){                          
        checkInvFromDateField();
    });
    $('.invtodate').datetimepicker().change(function(){                          
        checkInvToDateField();
    });
    
    Selectize.define('clear_selection', function(options) {
        var self = this;
        self.plugins.settings.dropdown_header = {
            title: 'Clear Selection'
        };
        this.require('dropdown_header');
        self.setup = (function() {
            var original = self.setup;
            return function() {
                original.apply(this, arguments);
                this.$dropdown.on('mousedown', '.selectize-dropdown-header', function(e) {
                    self.setValue('');
                    self.close();
                    self.blur();
                    return false;
                });
            };
        })();
    });

    var City = "#SelectCity";
    $(City).selectize({
        removeItem: '',
        sortField: 'text',
        create: false,
        dropdownParent: 'body',
        plugins: {
            'clear_selection': {}
        }
    });

    var codeInvoiceSup = [];
    $.each(invoiceSup, function(key, value) {
        codeInvoiceSup.push(value.code);
        if (!(value.name in codeInvoiceSup)) {
            codeInvoiceSup.push(value.name);
        }
    });

    $("#invSupCode").autocomplete({
        source: codeInvoiceSup,
        close: function(event, ui) {
            $("#invSupCode").trigger('keyup');
        }
    });

    $("#invSupCode").on('keyup', function() {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value;
        $("#invSupId,#invSupName,#invSupApCode").val(null);
        $.each(invoiceSup, function(key, value) {
            if (value.code.toUpperCase() === code) {
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
                $("#invSupApCode").val(value.apcode);
            }
            if (name === value.name) {
                $("#invSupCode").val(value.code);
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
                $("#invSupApCode").val(value.apcode);
                code = $("#invSupCode").val().toUpperCase();
            }
        });
    });

    $("#invSupCode").on('blur', function() {
        var delay = 500;//1 seconds
        setTimeout(function() {
            $.each(invoiceSup, function(key, value) {
                //alert(value.code);
                if ($("#invSupCode").val() === value.code) {
                    $("#invSupId").val(value.id);
                    $("#invSupName").val(value.name);
                    $("#invSupApCode").val(value.apcode);
                }
            });
        }, delay);
    });
    
    $('#SearchInvoicSupTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false
    });
    
    //Sale By Auto Complete
    $("#SaleByTable tr").on('click', function() {
        var saleby_id = $(this).find(".saleby-id").text();
        var saleby_user = $(this).find(".saleby-user").text();
        var saleby_name = $(this).find(".saleby-name").text();
        $("#salebyId").val(saleby_id);
        $("#salebyUser").val(saleby_user);
        $("#salebyName").val(saleby_name);
        $("#SaleByModal").modal('hide');
    });

    var salebyuser = [];
    $.each(saleby, function(key, value) {
        salebyuser.push(value.username);
        salebyuser.push(value.name);
    });

    $("#salebyUser").autocomplete({
        source: salebyuser,
        close: function(event, ui) {
            $("#salebyUser").trigger('keyup');
        }
    });

    $("#salebyUser").on('keyup', function() {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var username = this.value.toUpperCase();
        var name = this.value.toUpperCase();
        // console.log("Name :"+ name);
        $("#salebyId,#salebyName").val(null);
        $.each(saleby, function(key, value) {
            if (value.username.toUpperCase() === username) {
                $("#salebyId").val(value.id);
                $("#salebyUser").val(value.username);
                $("#salebyName").val(value.name);
            }
            else if (value.name.toUpperCase() === name) {
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

    $('#SaleByTable tbody').on('click', 'tr', function() {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });

});


function checkDepartFromDateField(){
    var inputFromDate = document.getElementById("departFromDate");
    var InputToDate = document.getElementById("departToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#departfromdatepanel").removeClass("has-error");
        $("#departtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#departfromdatepanel").removeClass("has-success");
        $("#departtodatepanel").removeClass("has-success");
        $("#departfromdatepanel").addClass("has-error");
        $("#departtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#departfromdatepanel").removeClass("has-error");
        $("#departtodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#departfromdatepanel").addClass("has-success");
        $("#departtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("departfrom","");
    }
}
    
function checkDepartToDateField(){
    var inputFromDate = document.getElementById("departFromDate");
    var InputToDate = document.getElementById("departToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#departfromdatepanel").removeClass("has-error");
        $("#departtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#departfromdatepanel").removeClass("has-success");
        $("#departtodatepanel").removeClass("has-success");
        $("#departfromdatepanel").addClass("has-error");
        $("#departtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#departfromdatepanel").removeClass("has-error");
        $("#departtodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#departfromdatepanel").addClass("has-success");
        $("#departtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("departto","");
    }       
}

// Pay Date
function checkPayFromDateField(){
    var inputFromDate = document.getElementById("payFromDate");
    var InputToDate = document.getElementById("payToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#payfromdatepanel").removeClass("has-success");
        $("#paytodatepanel").removeClass("has-success");
        $("#payfromdatepanel").addClass("has-error");
        $("#paytodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#departfromdatepanel").removeClass("has-error");
        $("#departtodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#payfromdatepanel").addClass("has-success");
        $("#paytodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("payfrom","");
    }
}
    
function checkPayToDateField(){
    var inputFromDate = document.getElementById("payFromDate");
    var InputToDate = document.getElementById("payToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#payfromdatepanel").removeClass("has-success");
        $("#paytodatepanel").removeClass("has-success");
        $("#payfromdatepanel").addClass("has-error");
        $("#paytodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#departfromdatepanel").removeClass("has-error");
        $("#departtodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#payfromdatepanel").addClass("has-success");
        $("#paytodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("payto","");
    }    
}
//Inv Date
function checkInvFromDateField(){
    var inputFromDate = document.getElementById("invFromDate");
    var InputToDate = document.getElementById("invToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#invfromdatepanel").removeClass("has-success");
        $("#invtodatepanel").removeClass("has-success");
        $("#invfromdatepanel").addClass("has-error");
        $("#invtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#departfromdatepanel").removeClass("has-error");
        $("#departtodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#invfromdatepanel").addClass("has-success");
        $("#invtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("invfrom","");
    }
}
    
function checkInvToDateField(){
    var inputFromDate = document.getElementById("invFromDate");
    var InputToDate = document.getElementById("invToDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#invfromdatepanel").removeClass("has-success");
        $("#invtodatepanel").removeClass("has-success");
        $("#invfromdatepanel").addClass("has-error");
        $("#invtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#departfromdatepanel").removeClass("has-error");
        $("#departtodatepanel").removeClass("has-error");
        $("#payfromdatepanel").removeClass("has-error");
        $("#paytodatepanel").removeClass("has-error");
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#invfromdatepanel").addClass("has-success");
        $("#invtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("invto","");
    }     
}

function checkDateValue(date){
    var inputFromDate = "";
        var InputToDate = "";
        if((date === 'departfrom') || (date === 'departto')){
            inputFromDate = document.getElementById("departFromDate");
            InputToDate = document.getElementById("departToDate");
        } else if((date === 'payfrom') || (date === 'payto')){
            inputFromDate = document.getElementById("payFromDate");
            InputToDate = document.getElementById("payToDate");
        } else {
            inputFromDate = document.getElementById("invFromDate");
            InputToDate = document.getElementById("invToDate");
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
        if(date === 'departfrom'){
            $("#departfromdatepanel").removeClass("has-success");
            $("#departfromdatepanel").addClass("has-error");                                 
        }
        if(date === 'departto'){
            $("#departtodatepanel").removeClass("has-success");
            $("#departtodatepanel").addClass("has-error");
        }
        if(date === 'payfrom'){
            $("#payfromdatepanel").removeClass("has-success");
            $("#payfromdatepanel").addClass("has-error");                                 
        }
        if(date === 'payto'){
            $("#paytodatepanel").removeClass("has-success");
            $("#paytodatepanel").addClass("has-error");
        }
        if(date === 'invfrom'){
            $("#invfromdatepanel").removeClass("has-success");
            $("#invfromdatepanel").addClass("has-error");                                 
        }
        if(date === 'invto'){
            $("#invtodatepanel").removeClass("has-success");
            $("#invtodatepanel").addClass("has-error");
        }
        $("#printbutton").addClass("disabled");
    } else {
        $("#departfromdatepanel").removeClass("has-success");
        $("#departtodatepanel").removeClass("has-success");
        $("#payfromdatepanel").removeClass("has-success");
        $("#paytodatepanel").removeClass("has-success");
        $("#invfromdatepanel").removeClass("has-success");
        $("#invtodatepanel").removeClass("has-success");
        $("#departfromdatepanel").addClass("has-error");
        $("#departtodatepanel").addClass("has-error");
        $("#payfromdatepanel").addClass("has-error");
        $("#paytodatepanel").addClass("has-error");
        $("#invfromdatepanel").addClass("has-error");
        $("#invtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    }
}


function printProfitLoss(){
    var owner = document.getElementById("salebyUser").value;
    var selectCity = document.getElementById("SelectCity").value;
    var productCode = document.getElementById("InputProductCode").value;
    var selectGroup = document.getElementById("selectGroup").value;
    var departFromDate = document.getElementById("departFromDate").value;
    var departToDate = document.getElementById("departToDate").value;
    var payFromDate = document.getElementById("payFromDate").value;
    var payToDate = document.getElementById("payToDate").value;
    var invFromDate = document.getElementById("invFromDate").value;
    var invToDate = document.getElementById("invToDate").value;
    var invSupCode = document.getElementById("invSupCode").value;
    
    
    alert( " departFromDate " + departFromDate
        + " departToDate " + departToDate
        + " invFromDate " + invFromDate
        + " invToDate " + invToDate
        + " owner " + owner
        + " selectCity " + selectCity
        + " productCode " + productCode
        + " invSupCode " + invSupCode
        + " payFromDate " + payFromDate
        + " payToDate " + payToDate
        + " selectGroup " + selectGroup );
//    if(((bookFromDate !== '') && (bookToDate !== '')) 
//        || ((payFromDate !== '') && (payToDate !== '')) 
//        || ((invFromDate !== '') && (invToDate !== ''))) {
//        if(reportType == 1){
//            window.open("Excel.smi?name=BookingInvoiceSummary&owner=" + owner + 
//                "&invto=" + billto + 
//                "&bookdatefrom=" + bookFromDate + 
//                "&bookdateto=" + bookToDate+ 
//                "&invdatefrom=" + invFromDate +""+
//                "&invdateto=" + invToDate+"");
//        }else if(reportType == 2){
//            window.open("Excel.smi?name=BookingNonInvoiceSummary&owner=" + owner +
//                "&invsup=" + invSupCode +
//                "&bookdatefrom=" + bookFromDate + 
//                "&bookdateto=" + bookToDate +
//                "&paydatefrom=" + payFromDate +
//                "&paydateto=" + payToDate);
//        }
//    } else {
//        validateDate();  
//    }
 
}

function setupInvSupValue(id, code, name, apcode) {
    $('#SearchInvoiceSup').modal('hide');
    document.getElementById('invSupId').value = id;
    document.getElementById('invSupCode').value = code;
    document.getElementById('invSupName').value = name;
    document.getElementById('invSupApCode').value = apcode;
    document.getElementById('invSupCode').focus();
}
</script>
