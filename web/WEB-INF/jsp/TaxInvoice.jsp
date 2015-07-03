<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/TaxInvoice.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="Type" value="${requestScope['Department']}" />
<input type="hidden" id="Type" name="Type" value="${param.Department}">
<section class="content-header" >
    <h1>
        Finance & Cashier
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Tax Invoice</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/FinanceAndCashier/TaxInvoiceMenu.html'"></div>
    </div>
    <!--Content -->
    <div class="col-sm-10">
        <div class="panel panel-default">            
            <div class="panel-heading">
                <c:choose>
                    <c:when test="${param.Department=='WO'}">
                        <label class="control-label">Tax Invoice Wendy/Outbound</lable>
                    </c:when>
                    <c:when test="${param.Department=='INB'}">
                        <label class="control-label">Tax Invoice Inbound</lable>
                    </c:when> 
                </c:choose> 
            </div>
            
            <div class="row text-right" style="padding-top: 5px;padding-right: 15px;" >
                <a class="col-xs-12 text-right" data-toggle="collapse" href="#collapseTab" aria-expanded="false" aria-controls="collapseTab">
                    <span id="arrowReceipt" class="arrowReceipt glyphicon glyphicon-chevron-up"></span>
                </a>
            </div>
            <div class="panel-body">
            <div class="tab-content collapse in" id="collapseTab" aria-expanded="false">
                <div class="row" >
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-left" style="width: 120px;" >
                            <label class="control-label">Invoice No </lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width: 200px;" >                         
                            <input name="InputInvoiceNo" id="InputInvoiceNo" type="text" class="form-control"  placeholder="" value="" />                                   
                        </div>                
                        <div class="col-xs-2 text-left" style="width: 100px;">
                            <a id="ButtonNew" name="ButtonFind" onclick="" class="btn btn-primary">
                                <i class="glyphicon glyphicon-search"></i> Search
                            </a>
                        </div>                     
                    </div>   
                </div><!-- End Row 1-->
                <div class="row" style="padding-left: 0px">
                    <div class="col-xs-12 ">
                        <table class="display" id="TaxInvoiceTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width: 20%">Product</th>
                                    <th style="width: 25%">Description</th>
                                    <th style="width: 10%">Amount</th>
                                    <th style="width: 10%">Cur</th>
                                    <th style="width: 10%">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>product1</td>
                                    <td>description</td>
                                    <td>30</td>                                
                                    <td>THB</td>
                                    <td class="text-center">
                                        <a href="#" onclick=""  data-toggle="modal" data-target="">
                                            <span id="removeSpan${dataStatus.count}" class="glyphicon glyphicon-plus"  onclick="" data-toggle="modal" data-target=""></span>
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>    
                    </div>   
                </div><!-- End Row 2-->
            </div>
        </div>
        </div><!--End Panel 1 -->
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="row" style="padding-left: 0px">
                        <div class="col-xs-1 text-right" style="width:140px;">
                            <label class="control-label">Tax Invoice No</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width:200px;">
                            <input name="InputTaxInvoiceNo" id="InputTaxInvoiceNo" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-2 text-right" style="width:150px;">
                            <label class="control-label">Invoice Date</lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="padding-left:5px">
                            <div class="col-sm-12">
                                <div class='input-group date' style="width:155px;">
                                    <input name="InputTaxInvoiceDate" id="InputTaxInvoiceDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>                     
                </div><!--End Row 1 -->
                <div class="row" style="padding-left: 0px">
                        <div class="col-xs-1 text-right" style="width:140px;">
                            <label class="control-label">To</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width:200px;"> 
                            <div class="input-group" id="gr" >
                                <input type="text" class="form-control" id="InputTo" name="InputTo" value="" />
                                <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchToModal">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-4 form-group text-left" >
                            <input name="InputToDescription" id="InputToDescription" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-1 text-right" style="width:100px;">
                            <label class="control-label">A/R Code</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" >
                            <input name="InputARCode" id="InputARCode" type="text" class="form-control" placeholder="" value="" />
                        </div>                     
                </div><!--End Row 2 -->
                <div class="row" style="padding-left: 0px">
                        <div class="col-xs-1 text-right" style="width:140px;"></div>
                        <div class="col-md-2 form-group text-right" style="width:200px;"> 
                            <label class="control-label">Name</lable>
                        </div>
                        <div class="col-md-4 form-group text-left" >
                            <input name="InputToDescription" id="InputToDescription" type="text" class="form-control" value="" />
                        </div>                    
                </div><!--End Row 3 -->
                <div class="row" style="padding-left: 0px">
                    <div class="col-xs-1 text-right" style="width:140px;"></div>
                    <div class="col-md-2 form-group text-right" style="width:200px;"> 
                        <label class="control-label">Address</lable>
                    </div>
                    <div class="col-md-4 form-group text-left" >
                        <textarea rows="3" cols="60" class="form-control">                     
                        </textarea>
                    </div>                    
                </div><!--End Row 4 -->
                <div class="row" style="padding-left: 0px">
                        <div class="col-xs-1 text-right" style="width:140px;">
                            <label class="control-label">Passenger</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width:200px;"> 
                            <div class="input-group" id="gr" >
                                <input type="text" class="form-control" id="InputPassenger" name="InputPassenger" value="" />
                                <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchPassengerModal">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-4 form-group text-left" >
                            <input name="InputPassengerDescription" id="InputPassengerDescription" type="text" class="form-control" value="" />
                        </div>                                     
                </div><!--End Row 5 -->
            </div>        
        </div><!-- End Panel 2-->
        <div class="panel panel-default">         
            <div class="panel-body">              
                <div class="row" style="padding-left: 0px">
                    <div class="col-xs-12 ">
                        <table class="display" id="TaxInvoiceTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width: 5%">No</th>
                                    <th style="width: 15%">Product</th>
                                    <th style="width: 10%">Ref No</th>
                                    <th style="width: 20%">Description</th>
                                    <th style="width: 5%">T/C</th>
                                    <th style="width: 10%">Cost</th>                                   
                                    <th style="width: 10%">Amount</th>
                                    <th style="width: 10%">Vat</th>
                                    <th style="width: 10%"></th>
                                    <th style="width: 8%">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>pppp</td>
                                    <td>3330</td>                                
                                    <td>test dddddd</td>
                                    <td>T</td>
                                    <td>3000</td>
                                    <td>5</td>                                
                                    <td>32</td>
                                    <td>XXXXX</td>
                                    <td class="text-center">
                                        <a href="#" onclick=""  data-toggle="modal" data-target="">
                                            <span id="" class="glyphicon glyphicon-remove deleteicon"  onclick="" data-toggle="modal" data-target="#delTaxInvoiceModal"></span>
                                        </a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>    
                    </div>   
                </div><!-- End Row 1--><br>
                <div class="row text-left" >
                    <div class="col-md-2 form-group text-right" > 
                        <label class="control-label">Remark</lable>
                    </div>
                    <div class="col-md-4 form-group text-left" >
                        <textarea rows="3" cols="60" class="form-control">                    
                        </textarea>
                    </div>                    
                </div><!--End Row 2 -->
                <div class="row" style="padding-left: 0px">
                    <div class="col-xs-2 text-right" >
                            <label class="control-label">Text Amount</lable>
                    </div>
                    <div class="col-md-4 form-group text-left" >                        
                        <input type="text" class="form-control" id="InputTextAmount" name="InputTextAmount" value="" />
                    </div>
                    <div class="col-xs-1 text-left" >
                            <label class="control-label">Total</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" >
                            <input name="InputTotal" id="InputTotal" type="text" class="form-control" value="" />
                    </div>                                     
                </div><!--End Row 3 -->
            </div>
        </div><!--End Panel 3 -->
        <div class="row text-center" >
            <div class="col-xs-3 text-right" ></div>
            <div class="col-xs-2 text-right" style="width: 120px;" >
                <button type="submit" id="ButtonPrintNew" name="ButtonPrintNew" class="btn btn-primary">
                    <i class="fa fa-print"></i> Print New             
                </button>
            </div>
            <div class="col-xs-2 text-right" style="width: 100px;" >
                <button type="submit" id="ButtonPrint" name="ButtonPrint" class="btn btn-primary">
                    <i class="fa fa-print"></i> Print             
                </button>
            </div>
            <div class="col-xs-2 text-right" style="width: 100px;" >
                <button type="submit" id="ButtonSave" name="ButtonVoid" class="btn btn-danger">
                    <i class="fa fa-refresh"></i> Void             
                </button>
            </div>
            <div class="col-xs-2 text-left" style="width: 100px;">
                <a id="ButtonSave" name="ButtonSave" onclick="" class="btn btn-success">
                    <i class="fa fa-save"></i> Save
                </a>
            </div> 
            <div class="col-xs-2 text-right" style="width: 100px;" >
                <button type="submit" id="ButtonNew" name="ButtonNew" class="btn btn-success">
                    <i class="fa fa-plus"></i> New             
                </button>
            </div>
        </div>
    </div>
</div>
<!--Modal Search To -->
<div class="modal fade" id="SearchToModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">To</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="ToTable">
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
                <div class="text-right">
                    <button id="SearchToModal" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--Modal Search Passenger -->
<div class="modal fade" id="SearchPassengerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Passenger</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="PassengerTable">
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
                <div class="text-right">
                    <button id="SearchPassengerModal" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--Delete Tax Invoice Modal-->
<div class="modal fade" id="delTaxInvoiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <c:choose>
                    <c:when test="${param.Department=='WO'}">
                        <h4 class="modal-title">Delete Tax Invoice Wendy/Outbound</h4>
                    </c:when>
                    <c:when test="${param.Department=='INB'}">
                        <h4 class="modal-title">Delete Tax Invoice Inbound</h4>
                    </c:when> 
                </c:choose>
                <!--<h4 class="modal-title">Delete Tax Invoice </h4>-->
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script language="javascript">
    $(document).ready(function () {
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        $('#collapseTab').on('shown.bs.collapse', function () {
            $(".arrowReceipt").removeClass("glyphicon glyphicon-chevron-down").addClass("glyphicon glyphicon-chevron-up");
        });

        $('#collapseTab').on('hidden.bs.collapse', function () {
           $(".arrowReceipt").removeClass("glyphicon glyphicon-chevron-up").addClass("glyphicon glyphicon-chevron-down");
        });
        
        $('#ToTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 3
        });
    
        $('#PassengerTable').dataTable({bJQueryUI: true,
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
