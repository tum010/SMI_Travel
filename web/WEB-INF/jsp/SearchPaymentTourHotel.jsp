<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">
<section class="content-header" >
    <h1>
        Checking - Search Payment
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Checking</a></li>          
        <li class="active"><a href="#">Search Payment</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/Checking/CheckingPackageTourHotel.html'"></div>
    </div>
    <!--Content -->
    <div class="col-sm-10">
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Search Payment Tour / Hotel</b></h4>
            </div>
        </div>
        <hr/>
        <!--Input Search -->
        <div class="panel panel-default">  
            <div class="panel-heading">
                <h4 class="panel-title">Search</h4>
            </div>
            <div class="panel-body">
                <div class="row" >
                    <div class="col-xs-1 text-right" style="width:70px;">
                        <label class="control-label">From:</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left:5px">
                        <div class="col-sm-12">
                            <div class='input-group date' style="width:140px;">
                                <input name="InputPayDate" id="InputPayDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-1 text-right" style="width:70px;">
                        <label class="control-label">To:</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left:5px">
                        <div class="col-sm-12">
                            <div class='input-group date' style="width:140px;">
                                <input name="InputPayDate" id="InputPayDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-2 text-right" style="padding-left:0px;padding-right:20px;width:100px;">
                        <label class="control-label">PV Type:</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right:0px;">
                        <div class="col-sm-12">
                            <select name="SelectPvType" id="SelectPvType" class="form-control">
                                <option id="" value="">--</option>
                                <option id="" value="">---type--</option>
                            </select>
                        </div>
                    </div>
                    <!--Button Print and Search -->
                    <div class="col-md-3 form-group text-left" style="padding-left:0px;padding-right:0px;">
                        <div class="col-xs-6 text-left">
                            <a id="ButtonNew" name="ButtonNew" onclick="" class="btn btn-primary">
                                <i class="fa fa-search"></i> Search
                            </a>
                        </div>
                        <div class="col-xs-6 text-right">
                            <button type="submit" id="ButtonSave" name="ButtonSave" class="btn btn-success">
                                <i class="fa fa-print"></i> Print             
                            </button>
                        </div>                        
                    </div>
                </div>
            </div>
        </div><!--End Search -->
        <!-- Table -->
        <div class="panel panel-default">                    
            <div class="panel-body">
                <table class="display" id="SearchPaymentHotelTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width: 8%">PV No</th>
                            <th style="width: 10%">Pay Date</th>
                            <th style="width: 20%">PV Type</th>
                            <th style="width: 20%">Invoice Sup</th>
                            <th style="width: 5%">Acc</th>
                            <th style="width: 8%">Dept.</th>
                            <th style="width: 8%">Total</th>
                            <th style="width: 5%">Car</th>
                            <th style="width: 8%">Status</th>
                            <th style="width: 10%">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>41399</td>
                            <td>2015-05-05</td>
                            <td>Wendy Van</td>
                            <td>Hook In</td>
                            <td>1</td>
                            <td>Wendy</td>
                            <td>23,000</td>
                            <td>THB</td>
                            <td>wait</td>
                            <td class="text-center">
                                <span id="RefBookTableButtonEdit" name="RefBookTableButtonEdit" class="glyphicon glyphicon-edit editicon" onclick="window.open('/SMITravel/DaytourDetail.smi?referenceNo=${dayTourDetai.master.referenceNo}&action=edit&daytourBooking=${dayTourDetai.id}');"></span>
                                <a data-toggle="collapse" href="#collapseExample${status.count}" aria-expanded="false" aria-controls="collapseExample${status.count}">
                                    <span id="SpanEdit${status.count}" class="glyphicon glyphicon-list-alt"></span>
                                </a>
                            </td>                           
                        </tr>
                    </tbody>
                </table>
            </div>
        </div><!--End Table -->
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
        
        jQuery.curCSS = jQuery.css;
        $('#SearchPaymentHotelTable').dataTable({
            bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": true,
            "bInfo": true,
            "iDisplayLength":10
        });
    });
</script>
