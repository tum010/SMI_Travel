<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/SearchPaymentOutbound.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="statusList" value="${requestScope['statusList']}" />

<section class="content-header" >
    <h1>
        Checking - Search Payment Outbound
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Checking</a></li>          
        <li class="active"><a href="#">Search Payment Outbound</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/Checking/CheckingOutboundMenu.html'"></div>
    </div>
    <!--Content -->
    <div class="col-sm-10">
        <div class="row" style="padding-left: 0px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Search Payment Outbound</b></h4>
            </div>
        </div>
        <hr/>
        <div class="row" style="padding-left: 0px">
            <div class="col-xs-1 text-right" style="width:150px;padding-right: 0px;padding-left: 0px;">
                <label class="control-label">From </lable>
            </div>
            <div class="col-md-2 form-group text-left" >
                <div class='input-group date' >
                    <input name="fromDate" id="fromDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <div class="col-xs-1 text-right" style="width:150px;padding-right: 0px;padding-left: 0px;">
                <label class="control-label">To</lable>
            </div>
            <div class="col-md-2 form-group text-left" >
                <div class='input-group date' >
                    <input name="toDate" id="toDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <div class="col-xs-1 text-right" style="width:150px;padding-right: 0px;padding-left: 0px;">
                <label class="control-label">Status</lable>
            </div>
            <div class="col-md-2 form-group text-left" >
                <select class="form-control" name="status" id="status">
                    <option  value="" >---------</option>
                    <c:forEach var="status" items="${statusList}">                                       
                        <c:set var="select" value="" />
                        <c:if test="${status.id == taxDetail.mbillType.id}">
                            <c:set var="select" value="selected" />
                        </c:if>
                        <option  value="${status.id}" ${select}>${status.name}</option>
                    </c:forEach>
                </select>   
            </div>
            
        </div><!-- End Row 1-->
        <div class="row" style="padding-left: 0px">
            <div class="col-xs-1 text-right" style="width:150px;padding-right: 0px;padding-left: 0px;">
                <label class="control-label">Invoice Sup</lable>
            </div>
            <div class="col-md-2 form-group text-left" > 
                <div class="input-group" id="gr" >
                    <input type="hidden" class="form-control" id="invSupId" name="invSupId" value="" />
                    <input type="text" class="form-control" id="invSupCode" name="invSupCode" value="" />
                    <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchInvoiceSup">
                        <span class="glyphicon-search glyphicon"></span>
                    </span>
                </div>
            </div>
            <div class="col-md-2 form-group text-left" style="width:310px;padding-right: 0px;padding-left: 0px;">
                <input name="invSupName" id="invSupName" type="text" class="form-control" value="" readonly=""/>
            </div>
            <div class="col-xs-1 text-right" style="width:165px;padding-right: 0px;padding-left: 0px;">
                <label class="control-label">Ref No</lable>
            </div>
            <div class="col-md-2 form-group text-left" >
                <input name="refNo" id="refNo" type="text" class="form-control" value="" />
            </div>
        </div>
        <div class="row" style="padding-left: 775px">
            <div class="col-xs-1 text-left" style="width: 100px;">
                <a id="ButtonSearch" name="ButtonSearch" onclick="" class="btn btn-primary">
                    <i class="glyphicon glyphicon-search"></i> Search
                </a>
            </div>
            <div class="col-xs-1 text-left" style="width: 100px;">
                <a id="ButtonPrint" name="ButtonPrint" onclick="" class="btn btn-default">
                    <i class="glyphicon glyphicon-print"></i> Print
                </a>
            </div>
        </div>
            <div class="row" style="padding-left: 15px;width: 1040px;">
                <table class="display" id="SearchPaymentTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width: 10%">Pay No</th>
                            <th style="width: 8%">Pay Date</th>
                            <th style="width: 20%">Invoice Sup</th>
                            <th style="width: 10%">Dept</th>
                            <th style="width: 12%">Total</th>
                            <th style="width: 12%">Cur</th>
                            <th style="width: 15%">Status</th>
                            <th style="width: 8%">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>XXXxx</td>
                            <td>26/07/2015</td>
                            <td>XXXXX</td>
                            <td>XXXX</td>
                            <td>XXXXX</td>
                            <td>THB</td>
                            <td>XXX</td>
                            <td class="text-center">
                                <a href="#" onclick=""  data-toggle="modal" data-target="" id="editPayment" name="editPayment">
                                    <span  class="glyphicon glyphicon-edit editicon" onclick=""></span>
                                </a>    
                                <a href="#" onclick=""  data-toggle="modal" data-target="" id="deletePayment" name="deletePayment">
                                    <span id="" class="glyphicon glyphicon-remove deleteicon"  onclick="" data-toggle="modal" data-target="#delSearchPaymentOutboundModal"></span>
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
    </div>
</div>
<!--Delete Payment Outbound Modal-->
<div class="modal fade" id="delSearchPaymentOutboundModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Payment Outbound</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--Search Invoice Sup-->
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
<script type="text/javascript">
$(document).ready(function () {
    $('.date').datetimepicker();
    $('.datemask').mask('0000-00-00');
    $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
    
    $('#SearchPaymentTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": true,
        "iDisplayLength": 10
    });
    
    $('#SearchInvoicSupTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false
    });
    
    var codeInvoiceSup = [];
    $.each(invoiceSup, function (key, value) {
        codeInvoiceSup.push(value.code);
        if ( !(value.name in codeInvoiceSup) ){
           codeInvoiceSup.push(value.name);        
        }
    });
        
    $("#invSupCode").autocomplete({
        source: codeInvoiceSup,
        close:function( event, ui ) {
            $("#invSupCode").trigger('keyup');
        }
    });
        
    $("#invSupCode").on('keyup', function () {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value;
        $("#invSupId,#invSupName").val(null);

        $.each(invoiceSup, function (key, value) {
            if (value.code.toUpperCase() === code) {           
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
            }
            if(name === value.name){
                $("#invSupCode").val(value.code);
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
                code = $("#invSupCode").val().toUpperCase();
            }
        });  
    });
        
    $("#invSupCode").on('blur', function () {
        var delay=500;//1 seconds
        setTimeout(function(){
            $.each(invoiceSup, function (key, value) {
                if($("#invSupCode").val() === value.code){
                    $("#invSupId").val(value.id);
                    $("#invSupName").val(value.name);
                }     
            });   
        },delay); 
    });
});

    function setupInvSupValue(id,code,name,apcode){
        $('#SearchInvoiceSup').modal('hide');
        document.getElementById('invSupId').value = id;
        document.getElementById('invSupCode').value = code;
        document.getElementById('invSupName').value = name;
        document.getElementById('invSupCode').focus();
//        $('#PaymentTourHotelForm').bootstrapValidator('revalidateField', 'InputInvoiceSupCode');
//        $('#PaymentTourHotelForm').bootstrapValidator('revalidateField', 'InputAPCode');
    }
</script>
