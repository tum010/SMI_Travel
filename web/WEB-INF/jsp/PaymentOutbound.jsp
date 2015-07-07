<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/PaymentOutbound.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="content-header" >
    <h1>
        Checking - Package Outbound
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Checking</a></li>          
        <li class="active"><a href="#">Package Outbound</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;padding-right: 0px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/Checking/CheckingOutboundMenu.html'"></div>
    </div>
    <!--Content -->
    <div class="col-sm-10">
        <div class="row" style="padding-left: 0px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Payment Outbound</b></h4>
            </div>
        </div>
        <hr/>
        <!--Row 1 -->
        <div class="row" style="padding-left: 0px">
                <div class="col-xs-1 text-right" style="width:100px;padding-right: 0px;padding-left: 0px;">
                    <label class="control-label">PV No</lable>
                </div>
                <div class="col-md-4 form-group text-left" >
                    <input name="InputPayNo" id="InputPayNo" type="text" class="form-control" value="" />
                </div>
                <div class="col-xs-1 text-left" style="width:90px;">
                    <label class="control-label">Pay Date</lable>
                </div>
                <div class="col-md-3 form-group text-left" style="padding-left:0px;padding-right: 0px;width: 150px;">
                    <div class='input-group date' >
                        <input name="InputPayDate" id="InputPayDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                </div>
                <div class="col-xs-1 text-left" style="width:80px;padding-left:10px;padding-right:0px;">
                    <label class="control-label">Account</lable>
                </div>
                <div class="col-md-3  text-left" style="padding-top : 5px;padding-left:0px;padding-right:0px;">
                    <div class="col-sm-6" text-left>
                        <input type="radio" name="account1"  id="account1" value="account1" checked /> &nbsp;account(1)
                    </div>
                    <div class="col-sm-6" text-left>
                        <input type="radio" name="account1"  id="account1" value="account2" />&nbsp;account(2)
                    </div>
            </div>
        </div><!--End row 1-->
        <div class="row" style="padding-left: 0px">
                <div class="col-xs-1 text-right" style="width:100px;padding-right: 0px;padding-left: 0px;">
                    <label class="control-label">Invoice Sup</lable>
                </div>
                <div class="col-md-2 form-group text-left" > 
                    <div class="input-group" id="gr" >
                        <input type="text" class="form-control" id="InputSup" name="InputSup" value="" />
                        <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchInvoiceSup">
                            <span class="glyphicon-search glyphicon"></span>
                        </span>
                    </div>
                </div>
                <div class="col-md-2 form-group text-left" >
                    <input name="InputSupName" id="InputSupName" type="text" class="form-control" value="" />
                </div>
                <div class="col-xs-1 text-left" style="width:78px;padding-right: 0px;">
                    <label class="control-label">A/P Code</lable>
                </div>
                <div class="col-md-1 form-group text-left" style="padding-left:0px;padding-right: 0px;width: 180px;">
                    <div class="col-sm-12">
                        <div class="input-group" id="CodeValidate">
                            <input name="InputInvoiceSupCode" id="InputInvoiceSupCode" type="text" class="form-control" value="" />
                            <span class="input-group-addon" data-toggle="modal" data-target="#SearchAPCode">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>    
                        </div>    
                    </div> 
                </div>
        </div><!--End row 2-->
        <div class="row text-right" style="padding-right: 60px">
            <div class="col-md-12 form-group text-right" style="padding-left:30px;padding-right:0px;">              
                <a id="ButtonNew" name="ButtonFind" onclick="" class="btn btn-primary" data-toggle="modal" data-target="">
                    <span id=""   onclick="" data-toggle="modal" data-target="#SearchPaymentOutboundModal"><i class="glyphicon glyphicon-search" ></i> Search</span>
                </a>
            </div>              
        </div><!--End row 3-->
        <div class="row" style="padding-left: 15px;width: 1040px;">             
                    <div class="row" style="padding-left: 0px">
                        <div class="col-xs-12 ">
                            <table class="display" id="TaxInvoiceTable">
                                <thead class="datatable-header">
                                    <tr>
                                        <th style="width: 5%">Type</th>                                   
                                        <th style="width: 10%">Ref No</th>
                                        <th style="width: 15%">Product No</th>
                                        <th style="width: 20%">Invoice R</th>
                                        <th style="width: 5%">Net</th>
                                        <th style="width: 10%">Confirm Net</th>                                                                      
                                        <th style="width: 10%">Cur</th>
                                        <th style="width: 8%">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>XX</td>
                                        <td>4XX</td>
                                        <td>ProXXX</td>                                                                   
                                        <td>XXX</td>
                                        <td>XXX</td>                                
                                        <td>XXXX</td>
                                        <td>THB</td>
                                        <td class="text-center">                                 
                                            <a href="#" onclick=""  data-toggle="modal" data-target="">
                                                <span id="" class="glyphicon glyphicon-remove deleteicon"  onclick="" data-toggle="modal" data-target="#delPaymentOutboundModal"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>    
                        </div>   
                    </div><!-- End Row 1-->
        </div><br>
        <div class="row text-right" >
            <div class="col-md-12 form-group text-right" style="padding-left:30px;padding-right:20px;"> 
                <div class="row">
                    <div class="col-md-9 text-right"><label class="control-label">Total</lable></div>
                    <div class="col-md-3 text-right"><input name="InputTotal" id="InputTotal" type="text" class="form-control" value="" /></div>
                </div>
            </div>                     
        </div><!--End row 5-->
        <div class="row text-center" >
            <div class="col-xs-6 text-right">
                <button type="submit" id="ButtonSave" name="ButtonSave" class="btn btn-success">
                    <i class="fa fa-save"></i> Save             
                </button>
            </div>
            <div class="col-xs-6 text-left">
                <a id="ButtonNew" name="ButtonNew" onclick="" class="btn btn-primary">
                    <i class="glyphicon glyphicon-plus"></i> New
                </a>
            </div>                         
        </div><!--End Button -->
    </div>
</div>
<!--Delete Payment Outbound Modal-->
<div class="modal fade" id="delPaymentOutboundModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
<!--Search Modal-->
<div class="modal fade" id="SearchPaymentOutboundModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Search</h4>
            </div>
            <div class="modal-body">
                <div style="height: 200px;">
                    <div class="text-right">
                        Invoice <input type="text" value="">
                    </div>
                <table class="display" id="InvoiceTable" >
                    <thead class="datatable-header">
                        <tr>
                            <th>Product</th>
                            <th>Description</th>
                            <th>Cost</th>
                            <th>Base Cost</th>
                            <th>Amount</th>
                            <th>Base Amount</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr class="">
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="text-center">
                                    <a href="">
                                        <span class="glyphicon glyphicon-check"></span>
                                    </a>
                                </td>
                            </tr>
                            <tr class="">
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="text-center">
                                    <a href="">
                                        <span class="glyphicon glyphicon-check"></span>
                                    </a>
                                </td>
                            </tr>
                    </tbody>
                </table>
                </div><br>
                <div style="height: 200px;">
                    <div class="text-right">
                        Ref No <input type="text" value="">
                    </div>
                <table class="display" id="RefNoTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Product</th>
                            <th>Description</th>
                            <th>Cost</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr class="">
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="">xxxx</td>
                                <td>xxx</td>
                                <td class="text-center">
                                    <a href="">
                                        <span class="glyphicon glyphicon-check"></span>
                                    </a>
                                </td>
                            </tr>
                    </tbody>
                </table>
                </div>
            </div>
            <div class="modal-footer">
                <button id="SearchPaymentOutboundModalOK" name="SearchPaymentOutboundModalOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchPaymentOutboundModalClose" name="SearchPaymentOutboundModalClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
                <h4 class="modal-title"  id="Titlemodel">Search Invoice</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="SearchInvoicSupTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
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
                <button id="SearchInvoiceSupOK" name="SearchInvoiceSupOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchInvoiceSupClose" name="SearchInvoiceSupClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
//    $(document).on('click', '#commissionTable tbody tr:last td  input ,#commissionTable tbody tr:last td .input-group-addon', function (e) { // .input-group-addon, .datemask
    $('#InvoiceTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 3
    });
    $('#RefNoTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 3
    });
    
    $('#SearchInvoicSupTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 3
        });
});
</script>
