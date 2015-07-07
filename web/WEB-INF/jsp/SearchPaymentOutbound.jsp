<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/SearchPaymentOutbound.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <div class="col-xs-1 text-right" >
                            <label class="control-label">From </lable>
                        </div>
                        <div class="col-md-2 form-group text-left" >
                                <div class='input-group date' >
                                    <input name="InputDateFrom" id="InputDateFrom" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                        </div>
                        <div class="col-xs-1 text-right" >
                            <label class="control-label">To</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" >
                                <div class='input-group date' >
                                    <input name="InputDateTo" id="InputDateTo" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 100px;">
                            <label class="control-label">PV Type</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" >
                            <select id="SelectPVType" name="SelectPVType" class="form-control">
                                <option value="">--select--</option>
                            </select>
                        </div>
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
                </div><!-- End Row 1--><br>
                <div class="row" style="padding-left: 15px;width: 1040px;">
                <table class="display" id="SearchPaymentTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width: 10%">PV NO</th>
                            <th style="width: 8%">Pay Date</th>
                            <th style="width: 15%">PV Type</th>
                            <th style="width: 20%">Invoice Sup</th>
                            <th style="width: 10%">Acc</th>
                            <th style="width: 12%">Dept.</th>
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
                            <td>XXXX</td>
                            <td>XXXX</td>
                            <td>THB</td>
                            <td>XXX</td>
                            <td class="text-center">
                                <span  class="glyphicon glyphicon-edit editicon" onclick=""></span>                                          
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
});
</script>
