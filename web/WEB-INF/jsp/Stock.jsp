<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Stock.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="content-header" >
    <h1>
        Master Stock
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Master </a></li>          
        <li class="active"><a href="#">Stock</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app="">
    <div class="col-sm-2">
        <div ng-include="'WebContent/Master/StockMenu.html'"></div>
    </div>
    <div class="col-sm-10">
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Stock</b></h4>
            </div>
        </div>
        <hr/>
   
                <div class="row" style="padding-left: 15px"> 
                    <div class="col-xs-12 ">
                        <div class="col-xs-2 text-right"  style="padding-right: 25px">
                            <label class="control-label">Product</lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="padding-left:0px;width: 160px;"> 
                            <div class="input-group" id="gr" >
                                <input type="text" class="form-control" id="InputProduct" name="InputProduct" value="" />
                                <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchProduct">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width: 200px;">
                            <input name="InputProductName" id="InputProductName" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-1 text-right"  >
                            <label class="control-label">Staff</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" > 
                            <div class="input-group" id="gr" >
                                <input type="text" class="form-control" id="InputStaff" name="InputStaff" value="" />
                                <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchStaff">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width: 200px;">
                            <input name="InputStaffName" id="InputStaffName" type="text" class="form-control" value="" />
                        </div>
                    </div>   
                </div><!-- End Row 1-->
                <div class="row" >
                    <div class="col-xs-12 ">
                        <div class="col-xs-2 text-right" >
                            <label class="control-label">Effective From</lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="width: 170px;" >
                            <div class='input-group date' >
                                <input name="InputEffectiveFromDate" id="InputEffectiveFromDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-xs-3 text-right" style="width: 285px;">
                            <label class="control-label">Effective To</lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="padding-left: 17px;width: 170px;" >
                            <div class='input-group date' >
                                <input name="InputInputEffectiveToDate" id="InputInputEffectiveToDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>   
                </div><!-- End Row 2-->
                <div class="row" >
                    <div class="col-xs-12 ">
                        <div class="col-xs-2 text-right" >
                            <label class="control-label">Description</lable>
                        </div>
                        <div class="col-md-5 form-group text-left" style="width: 370px;" >
                             <textarea class="form-control" rows="3" id="descriptionStock"></textarea>
                        </div>
                        <div class="col-xs-1 text-left" style="width: 100px;padding-left: 3px;padding-right: 0px;">
                            <label class="control-label">Add Date</lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="padding-left: 0px;width: 155px;">
                            <div class='input-group date' >
                                <input name="InputStockDate" id="InputStockDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>   
                </div><!-- End Row 3-->

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="row" >
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right"  >
                            <label class="control-label">Prefix</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" > 
                            <input name="InputPrefix" id="InputPrefix" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-1 text-right"  >
                            <label class="control-label">Start</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" > 
                            <input name="InputStart" id="InputStart" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-2 text-right"  >
                            <label class="control-label">Number Of Item</lable>
                        </div>
                        <div class="col-md-1 form-group text-left" > 
                            <input name="InputNumberOfItem" id="InputNumberOfItem" type="text" class="form-control" value="" placeholder="123"/>
                        </div>
                        <div class="col-xs-1 text-right"  >
                            <label class="control-label">Type</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" > 
                            <select name="Selecttype" id="Selecttype" class="form-control">
                                <option id="" value="">---type--</option>
                                <option id="" value="">---type1--</option>
                            </select>
                        </div>
                    </div>   
                </div><!-- End Row 1-->
                <div class="row" >
                    <div class="col-xs-12" style="padding-right: 45px;">
                        <div class="col-md-8 text-right" >                                          
                        </div> 
                        <div class="col-md-2 text-right">
                            <button type="submit"  id="ButtonAdd"  name="ButtonAdd" onclick="" class="btn btn-primary btn-primary " style="width: 100px;">
                                <span id="SpanAdd" class="glyphicon glyphicon-print fa fa-plus-circle"></span> Add
                            </button>                                          
                        </div> 
                        <div class="col-md-2 text-right" >
                            <button type="button" onclick="" class="btn btn-danger" style="width: 160px;">
                                <span id="SpanPrint" class="glyphicon glyphicon-refresh"></span> Clear Expire Stock
                            </button>
                        </div>
                    </div>   
                </div><!-- End Row 2--><br>
                <div class="row" style="padding-left: 0px">
                        <div class="col-xs-12 ">
                            <table class="display" id="TaxInvoiceTable">
                                <thead class="datatable-header">
                                    <tr>
                                        <th style="width: 5%">No</th>                                   
                                        <th style="width: 10%">Code</th>
                                        <th style="width: 15%">Type</th>
                                        <th style="width: 20%">Pay Status</th>
                                        <th style="width: 15%">Item Status</th>
                                        <th style="width: 8%">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>XX</td>
                                        <td>4XX</td>
                                        <td>XXX</td>                                
                                        <td>XXXX</td>
                                        <td>XXXX</td>
                                        <td class="text-center">                                          
                                            <a href="#" onclick=""  data-toggle="modal" data-target="">
                                                <span id="" class="glyphicon glyphicon-remove deleteicon"  onclick="" data-toggle="modal" data-target="#delStockModal"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>    
                        </div>   
                    </div><!-- End Row 3--><br>
                    <div class="row" >
                    <div class="col-xs-12"  style="padding-left: 20px;">
                        <div class="col-md-12 text-right">
                            <button type="submit" id="ButtonSave" name="ButtonSave" class="btn btn-success">
                                <i class="fa fa-save"></i> Save             
                            </button>                           
                        </div>                                         
                    </div>   
                </div><!-- End Row 4-->
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
<!--Delete Stock Modal-->
<div class="modal fade" id="delStockModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Stock</h4>
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
