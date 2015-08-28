<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="listClient" value="${requestScope['listClient']}" />
<c:set var="listAccno" value="${requestScope['listAccno']}" />

<section class="content-header"  >
    <h4>
        <b>Report : Invoice monthly report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Invoice monthly</a></li>
    </ol>
</section>

<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/InvoiceMonthlyMenu.html'"></div>
            </div>
            <div class="form-group">
                <div class="col-md-6">
                    <h3>List Invoice monthly</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="ticketsummary" class="form-horizontal">
                    <div class="row"> 
                        <div class="col-sm-8">
                            <div class="form-group">
                                <label for="billFrom" class="col-sm-5 control-label text-right">Bill From</label>
                                <div class="col-sm-3">
                                    <div class="form-group">
                                         <div class='input-group' >
                                            <input type='text' id="billFromCode" name="billFromCode"  class="form-control" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-search" data-toggle="modal" data-target="#BillFromModal"></span></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <input type='text' id="billFromName" name="billFromName"  class="form-control" readonly/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-8">
                            <div class="form-group">
                                <label for="client" class="col-sm-5 control-label text-right">Client</label>
                                <div class="col-sm-3">
                                    <div class="form-group">
                                         <div class='input-group' >
                                            <input type='text' id="clientCode" name="clientCode"  class="form-control" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-search" data-toggle="modal" data-target="#ClientModal"></span></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <input type='text' id="clientName" name="clientName"  class="form-control" readonly/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label for="payment" class="col-sm-5 control-label text-right">Payment</label>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <input type='text' id="payment" name="payment"  class="form-control" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label for="accNo" class="col-sm-5 control-label text-right">Acc No</label>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <select id="accNo" name="accNo"  class="form-control">
                                            <c:forEach var="item" items="${listAccno}" >
                                                <c:set var="selectAccno" value="" />
                                                <%--<c:if test="${item.id == invoice.MAccpay.id}">--%>
                                                    <%--<c:set var="selectTerm" value="selected" />--%>
                                                <%--</c:if>--%>
                                                <option value="${item.code} ${item.accNo}" ${selectAccno}>${item.code} - ${item.accNo}</option>
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
                                <label for="vatType" class="col-sm-5 control-label text-right">Vat Type</label>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <select id="vatType" name="vatType"  class="form-control">
                                            <option value="V">Vat</option>
                                            <option value="N">No Vat</option>
                                            <option value="T">Temp</option>
                                            <option value="A">Ticket</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right"> From </label>
                                <div class="col-md-4">  
                                    <div class="form-group" id="fromdatepanel">
                                        <div class='input-group date' id='fromdate'>
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
                            <div class="form-group" id="todatepanel">
                                <label class="col-md-5 control-label text-right"> To </label>
                                <div class="col-md-4">  
                                    <div class="form-group">
                                        <div class='input-group date' id='todate'>
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
                                <label for="department" class="col-sm-5 control-label text-right">Department</label>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <select id="department" name="department"  class="form-control">
                                            <option value="Wendy">Wendy</option>
                                            <option value="Outbound">Outbound</option>
                                            <option value="Inbound">Inbound</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <div class="col-sm-10 text-right">
                                    <button type="button" onclick="printInvoiceMonthly();" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
                                </div>
                                <div class="col-sm-2 text-left">
                                    <button type="button" onclick="" class="btn btn-warning"><span class="glyphicon glyphicon-print"></span> Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>                
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="BillFromModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Bill From</h4>
            </div>
            <div class="modal-body">
                Bill To List Table
                <table class="display" id="BillFromTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th>Bill From</th>
                            <th>Bill Name</th>
                            <th>Address</th>
                            <th>Tel</th>
                        </tr>
                    </thead>
                    <script>
                        billFrom = [];
                    </script>
                    <tbody>
                        <c:forEach var="item" items="${listClient}">
                            <tr>                                
                                <td class="item-billto">${item.billTo}</td>
                                <td class="item-name">${item.billName}</td>                                
                                <td class="item-address ">${item.address}</td>
                                <td class="item-tel ">${item.tel}</td>
                            </tr>
                        <script>
                            billFrom.push({code: "${item.billTo}", name: "${item.billName}", address: "${item.address}" , tel: "${item.tel}"});
                        </script>
                        </c:forEach>
                            
                    </tbody>

                </table>
                <!--Script Bill To List Table-->
                <script>
                    $(document).ready(function () {
                        var billF = [];
                        $.each(billFrom, function (key, value) {
                            billF.push(value.code);
                            if ( !(value.name in billF) ){
                               billF.push(value.name);
                            }
                        });

                        $("#billFromCode").autocomplete({
                            source: billF,
                            close:function( event, ui ) {
                               $("#billFromCode").trigger('keyup');
                            }
                        });
                        $("#billFromCode").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var code = this.value.toUpperCase();
                            var name = this.value;
                            $("#billFromName").val(null);
                            $.each(billFrom, function (key, value) {
                                if (value.code.toUpperCase() === code) {
                                    $("#billFromName").val(value.name);                                   
                                }
                                if(name === value.name){
                                    $("#billFromCode").val(value.code);
                                    $("#billFromName").val(value.name);    
                                    code = $("#billFromCode").val().toUpperCase();
                                }
                                
                            });
                        });
                        
                        $("#BillFromTable tr").on('click', function () {
                            var billto = $(this).find(".item-billto").text();
                            var billname = $(this).find(".item-name").text();
                            var address = $(this).find(".item-address").text();
                            var tel = $(this).find(".item-tel").text();
                            $("#billFromCode").val(billto);
                            $("#billFromName").val(billname);
//                            $("#address").val(address);
                            $("#BillFromModal").modal('hide');
                        });

                        // BillTo Table
                        var BillToTable = $('#BillFromTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                        $('#BillFromTable tbody').on('click', 'tr', function () {
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
<div class="modal fade" id="ClientModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Client </h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->
                <table class="display" id="ClientTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th>Bill From</th>
                            <th>Bill Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <script>
                        bill = [];
                    </script>
                    <tbody>
                        <c:forEach var="client" items="${listClient}">
                            <tr>                                
                                <td class="item-billto">${client.billTo}</td>
                                <td class="item-name">${client.billName}</td>                                
                                <td class="item-address hidden">${client.address}</td>
                                <td class="item-tel hidden">${client.tel}</td>
                            </tr>
                        <script>
                            bill.push({code: "${client.billTo}", name: "${client.billName}"});
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

                        $("#clientCode").autocomplete({
                            source: billTo,
                            close:function( event, ui ) {
                               $("#clientCode").trigger('keyup');
                            }
                        });
                        $("#clientCode").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var code = this.value.toUpperCase();
                            var name = this.value;
                            $("#clientName").val(null);
                            $.each(bill, function (key, value) {
                                if (value.code.toUpperCase() === code) {
                                    $("#clientName").val(value.name);                                   
                                }
                                if(name === value.name){
                                    $("#clientCode").val(value.code);
                                    $("#clientName").val(value.name);    
                                    code = $("#clientCode").val().toUpperCase();
                                }
                                
                            });
                        });
                        
                        $("#ClientTable tr").on('click', function () {
                            var billto = $(this).find(".item-billto").text();
                            var billname = $(this).find(".item-name").text();
//                            var address = $(this).find(".item-address").text();
//                            var tel = $(this).find(".item-tel").text();
                            $("#clientCode").val(billto);
                            $("#clientName").val(billname);
//                            $("#address").val(address);
                            $("#ClientModal").modal('hide');
                        });

                        // BillTo Table
                        var BillToTable = $('#ClientTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                        $('#ClientTable tbody').on('click', 'tr', function () {
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
<script type="text/javascript" src="js/InvoiceMonthly.js"></script> 