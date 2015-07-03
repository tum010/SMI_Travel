<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Stock.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="content-header" >
    <h1>
        Master Search Stock
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Master </a></li>          
        <li class="active"><a href="#">Search Stock</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app="">
    <div class="col-sm-2">
        <div ng-include="'WebContent/Master/StockMenu.html'"></div>
    </div>
    <div class="col-sm-10">
        <div class="panel panel-default">
            <div class="panel-heading">
                 <label class="control-label">Check Stock</lable>
            </div>
            <div class="panel-body">
                <div class="row" >
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right"  >
                            <label class="control-label">Product</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" > 
                            <div class="input-group" id="gr" >
                                <input type="text" class="form-control" id="InputProduct" name="InputProduct" value="" />
                                <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchProduct">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                         <div class="col-md-2 form-group text-left" > 
                            <input name="InputPrefix" id="InputPrefix" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-1 text-right" style="width: 100px;padding-right: 0px;padding-left: 0px;">
                            <label class="control-label">Effective From</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="padding-left: 5px;">
                            <div class='input-group date' >
                                <input name="InputEffectiveFromDate" id="InputEffectiveFromDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 100px;padding-right: 0px;padding-left: 0px;">
                            <label class="control-label">Effective To</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="padding-left: 5px;">
                            <div class='input-group date' >
                                <input name="InputInputEffectiveToDate" id="InputInputEffectiveToDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>   
                </div><!-- End Row 1-->
                <div class="row" >
                    <div class="col-xs-1 text-right" style="width: 95px;" >
                        <label class="control-label">Add Date</lable>
                    </div>
                    <div class="col-md-3 form-group text-left" >
                        <div class='input-group date' >
                            <input name="InputStockDate" id="InputStockDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="col-xs-2 text-right" style="padding-left: 0px;" >
                            <label class="control-label">Pay Status</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left: 10px;"> 
                        <select name="Selecttype" id="Selecttype" class="form-control">
                            <option id="" value="">---select--</option>
                            <option id="" value="">---s1elect--</option>
                        </select>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 100px;padding-right: 0px;padding-left: 0px;">
                            <label class="control-label">Item Status</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left: 10px;"> 
                        <select name="Selecttype" id="Selecttype" class="form-control">
                            <option id="" value="">---select--</option>
                            <option id="" value="">---s1elect--</option>
                        </select>
                    </div>
                </div><!-- End Row 2-->
                <div class="row" >
                    <div class="col-xs-12"  style="padding-left: 20px;">
                        <div class="col-md-11 text-right">
                            <button type="submit"  id="ButtonAdd"  name="ButtonAdd" onclick="" class="btn btn-primary" style="width: 100px;">
                                <span id="SpanAdd" class="glyphicon glyphicon-print fa fa-plus-circle"></span> Add
                            </button>                                          
                        </div>                   
                    </div>   
                </div><!-- End Row 3-->
            </div>
        </div><!-- End Panel One-->
        <div class="panel panel-default">
             <div class="panel-heading">
                 <label class="control-label">Summary</lable>
            </div>
            <div class="panel-body">
                <div class="row" >
                    <div class="col-xs-12 ">
                        <div class="col-xs-2 text-right"  >
                            <label class="control-label">Product</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" > 
                            <input name="InputProduct" id="InputProduct" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-2 text-right" style="width: 100px;padding-right: 0px;" >
                            <label class="control-label">Staff</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" > 
                            <input name="InputStaff" id="InputStaff" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-1 text-right" style="width: 100px;">
                            <label class="control-label">Add Date</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="padding-left: 0px;width: 160px;">
                            <div class='input-group date' >
                                <input name="InputStockDate" id="InputStockDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-md-1 text-right">
                            <button type="submit" id="ButtonSave" name="ButtonSave" class="btn btn-success">
                                <i class="fa fa-save"></i> Save             
                            </button>                                         
                        </div>    
                    </div>   
                </div><!-- End Row 1-->
                <div class="row" >
                    <div class="col-xs-12"  style="padding-left: 20px;">
                        <div class="col-xs-2 text-right" >
                            <label class="control-label">Effective From</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" >
                            <div class='input-group date' >
                                <input name="InputEffectiveFromDate" id="InputEffectiveFromDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-xs-1 text-right" style="width: 100px;padding-left: 0px;padding-right: 0px;">
                            <label class="control-label">Effective To</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" >
                            <div class='input-group date' >
                                <input name="InputInputEffectiveToDate" id="InputInputEffectiveToDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>   
                </div><!-- End Row 2--><br>
                <div class="row" style="padding-left: 0px">
                        <div class="col-xs-12 ">
                            <table class="display" id="TaxInvoiceTable">
                                <thead class="datatable-header">
                                    <tr>
                                        <th style="width: 15%">Number Of Item</th>                                   
                                        <th style="width: 10%">Normal</th>
                                        <th style="width: 15%">Cancel</th>
                                        <th style="width: 20%">Bill</th>
                                        <th style="width: 15%">In Use</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>XX</td>
                                        <td>4XX</td>
                                        <td>XXX</td>                                
                                        <td>XXXX</td>
                                        <td>XXXX</td>                
                                    </tr>
                                </tbody>
                            </table>    
                        </div>   
                    </div><!-- End Row 3--><br>
                    <div class="row" style="padding-left: 0px">
                        <div class="col-xs-12 ">
                            <table class="display" id="TaxInvoiceTable">
                                <thead class="datatable-header">
                                    <tr>
                                        <th style="width: 5%">No</th>                                   
                                        <th style="width: 10%">Code</th>
                                        <th style="width: 15%">Ref No</th>
                                        <th style="width: 10%">Pick Up</th>
                                        <th style="width: 15%">Pay Status</th>
                                        <th style="width: 15%">Item Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>XX</td>
                                        <td>4XX</td>
                                        <td>XXX</td>                                
                                        <td>XXXX</td>
                                        <td>XXXX</td>
                                        <td>XXXX</td>                            
                                    </tr>
                                </tbody>
                            </table>    
                        </div>   
                    </div><!-- End Row 3--><br>
            </div>
        </div>
    </div>
</div>
<!--Search Product-->
<div class="modal fade" id="SearchProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Search Product</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="ProductTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Code</th>
                            <th> Product Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr class="packet">
                                <td class="">XXX
                                <td>XXXXX</td>
                                <td class="text-center">
                                    <a href="">
                                        <span class="glyphicon glyphicon-check"></span>
                                    </a>
                                </td>
                            </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="SearchProductOK" name="SearchProductOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchProductClose" name="SearchProductClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->
<!--Search Staff-->
<div class="modal fade" id="SearchStaff" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Search Staff</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="StaffTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Code</th>
                            <th> Staff Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr class="packet">
                                <td class="">XXX
                                <td>XXXXX</td>
                                <td class="text-center">
                                    <a href="">
                                        <span class="glyphicon glyphicon-check"></span>
                                    </a>
                                </td>
                            </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="SearchStaffOK" name="SearchStaffOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchStaffClose" name="SearchStaffClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->
<script type="text/javascript">
$(document).ready(function () {
    $('.date').datetimepicker();
    
     $(".datemask").mask('00-00-0000', {reverse: true});
     $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
    $('#ProductTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    $('#StaffTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
});
</script>
