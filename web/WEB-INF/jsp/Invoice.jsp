<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Invoice.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="Type" value="${requestScope['typeInvoice']}" />
<input type="hidden" id="Type" name="Type" value="${param.Type}">


<section class="content-header" >
    <h1>
        Finance & Cashier - Invoice
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Invoice</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app="">
    <div class="row">
        
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/InvoiceMenu.html'"></div>
        </div>
        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <c:choose>
                        <c:when test="${fn:contains(Type , 'temp')}">
                            <h4><b>Invoice Temp</b></h4>
                        </c:when>
                        <c:when test="${fn:contains(Type , 'vat')}">
                            <h4><b>Invoice Vat</b></h4>
                        </c:when>    
                        <c:when test="${fn:contains(Type , 'NoVat')}">
                            <h4><b>Invoice No Vat</b></h4>
                        </c:when>    
                    </c:choose>                
                </div>
            </div>
            
            <!--Reservation-->
            <div class="row" style="padding-left: 15px">  
                <form action="" method="post" id="ReservationTravox">
                    <div role="tabpanel">
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane  active" id="info">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4>
                                            <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" onclick="">
                                            <span id="SpanEdit${advanced.search}">Reservation</span>
                                            </a>
                                            <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" style="margin-left: 50em" onclick="">
                                                <span id="arrowReservstion" class="arrowReservstion glyphicon glyphicon-chevron-down"></span> 
                                            </a>
                                        </h4>               
                                    </div>
                                    <div class="panel-body">               
                                        <div class="collapsing" id="collapseExample${advanced.search}" aria-expanded="false">
                                            <div class="col-md-12">
                                                <div class="col-xs-2 col-md-2 text-left">
                                                    <label class="control-label" for="">Reservation Travox </lable>
                                                </div>
                                                <div class="col-md-2 form-group">
                                                    <div class="input-group">
                                                        <input type="hidden" class="form-control" id="" name="" value=""/>
                                                        <input type="text" class="form-control" id="" name="" value="" style="background-color: #ffffff">
                                                        <span class="input-group-addon" id=""  data-toggle="modal" data-target="#">
                                                            <span class="glyphicon-search glyphicon"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="col-md-1 text-right">
                                                    <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="" class="btn btn-primary btn-sm">
                                                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                                                    </button>                                          
                                                </div>      
                                            </div>
                                            <div class="col-xs-12 form-group"></div>
                                            <div class="row" style="padding-left:35px">    
                                                <div class="col-md-12">
                                                    <table id="MasterReservation" class="display" cellspacing="0" width="100%">
                                                        <thead>
                                                            <tr class="datatable-header">
                                                                <th style="width: 5%" >No</th>
                                                                <th style="width: 15%" >Type</th>
                                                                <th style="width: 60%">Description</th>
                                                                <th style="width: 10%">Cost</th>
                                                                <th style="width: 10%">Price</th>
                                                                <th style="width: 1%">Currency</th>
                                                                <th style="width: 1%">Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>               
                                                                <tr>
                                                                    <td align="center">1</td>
                                                                    <td>TEST</td>
                                                                    <td>Hello World</td>
                                                                    <td align="center">100000</td>
                                                                    <td align="center">100000</td>
                                                                    <td align="center">THB</td>
                                                                    <td align="center" > 
                                                                        <center> 
                                                                            <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      
                                                                              onclick="EditBank('${table.id}', '${table.code}', '${table.name}', '${table.branch}', '${table.accNo}', '${table.accType}')" data-toggle="modal" data-target="#BankModal" >
                                                                            </span>
                                                                        </center>
                                                                    </td>
                                                                </tr>
                                                        </tbody>
                                                    </table>    
                                                </div>
                                            </div>
                                            <div class="col-xs-12 form-group"><hr/></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>                                        
                <div class="col-xs-12 form-group"></div>
                
                <!--Search-->  
                <form action="" method="post" id="" name="" role="form">
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right">
                            <label class="control-label" for="">INV No :</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <input type="text"  class="form-control" id="" name=""  value="" >
                        </div>
                        <div class="col-xs-2 text-right">
                            <label class="control-label" for="">Invoice Date :</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <input type="text"  class="form-control" id="" name=""  value="" >
                        </div>
                        <div class="col-xs-2 text-right">
                            <label class="control-label" for="">Due Date :</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <input type="text"  class="form-control" id="" name=""  value="" >
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-sm-1 text-right">
                            <label class="control-label" for="">Inv To :</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <div class="input-group">
                            <input type="hidden" class="form-control" id="" name="" value=""/>
                            <input type="text" class="form-control" id="" name="" value="" style="background-color: #ffffff">
                            <span class="input-group-addon" id=""  data-toggle="modal" data-target="#">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                            </div>
                        </div>
                        <div class="col-sm-4">  
                            <input value="${detail.customer.MInitialname.name}" id="get-initial" type="hidden">                                
                                <c:forEach var="initial" items="${initialList}">
                                    <option value="${initial.id}">${initial.name}</option>
                                </c:forEach>
                            <input  type="text" id="initialname" name="initialname" class="form-control" value="${detail.customer.MInitialname.name}" readonly="">
                        </div>
                        <div class="col-xs-2 text-right">
                            <label class="control-label" for="">Term Pay :</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <input type="text"  class="form-control" id="" name=""  value="" >
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-sm-3" style="padding-left: 190px">
                            <label class="control-label" for="">Name :</lable>
                        </div>    
                        <div class="col-sm-4">
                            <input  type="text" id="" name="" class="form-control" value="" >
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2" style="padding-left: 53px">
                            <label for="Department" class="col-sm-3 control-label" >Department&nbsp;:</label>
                            </div>
                            <div class="radio col-sm-2">   
                                <label ><input value="" id="DepartmentInactive" name="Department" type="radio" >Wendy Air Ticket</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-sm-3" style="padding-left: 170px">
                            <label class="control-label" for="">Address :</lable>
                        </div>
                        <div class="col-sm-4">
                            <textarea  rows="3" cols="100" id="" name="" class="form-control" value="" ></textarea>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2"></div>
                            <div class="radio col-sm-2">   
                                <label><input value="" id="DepartmentInactive" name="Department" type="radio" >Wendy Package</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2"></div>
                            <div class="radio col-sm-2">   
                                <label><input value="" id="DepartmentInactive" name="Department" type="radio" >Outbound</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right">
                            <label class="control-label" for="">Sale&nbsp;Staff</label>
                        </div>                       
                        <div class="col-md-2 form-group">
                            <input type="text"  class="form-control" id="" name=""  value="" >
                        </div>
                        <div class="col-md-3 form-group">
                            <input type="text"  class="form-control" id="" name=""  value="" >
                        </div>
                        <div class="col-md-2 form-group">
                             <label class="control-label"><input onclick='' type="checkbox" id="Grpup" name="Grpup" ${enableGrpup} ${checkGrpup}>  Group Yes/No</label>
                        </div>
                        <div class="col-xs-1 text-right">
                            <label class="control-label" for="" >A/R&nbsp;Code</label>
                        </div>  
                        <div class="col-md-2 form-group">
                            <div class="input-group">
                                <input type="hidden" class="form-control" id="" name="" value=""/>
                                <input type="text" class="form-control" id="" name="" value="" style="background-color: #ffffff">
                                <span class="input-group-addon" id=""  data-toggle="modal" data-target="#">
                                   <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                    </div>    
                </form>                
            </div>
            
            <form action="DaytourOperationDetail.smi" method="post" id="AirticketForm">
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="info">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">Detail Billable</h4>
                                </div>
                                <div class="panel-body">
                                    <table class="display" id="DetailBillableTable">
                                        <thead class="datatable-header">
                                            <tr>
                                                <th style="width:10%;">Product</th>
                                                <th style="width:20%;">Description</th>
                                                <th style="width: 1%">T/C</th>
                                                <th style="width: 5%">Cost</th>
                                                <th style="width: 5%">Cost Local</th>
                                                <th style="width: 5%">Amount</th>
                                                <th style="width: 5%">Amount Local</th>
                                                <th style="width: 2%">Vat</th> 
                                                <th style="width: 5%">Net</th>
                                                <th style="width: 2%">Action</th>    
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>TEST</td>
                                                <td>Hello World</td>
                                                <td align="center">[T][C]</td>
                                                <td>1000000 THB</td>
                                                <td>100000</td>
                                                <td>100000 THB</td>
                                                <td>100000</td>
                                                <td>100000</td>
                                                <td>100000</td>
                                                <td align="center" > 
                                                <center> 
                                                    <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      
                                                      onclick="EditBank('${table.id}', '${table.code}', '${table.name}', '${table.branch}', '${table.accNo}', '${table.accType}')" data-toggle="modal" data-target="#BankModal" >
                                                    </span>
                                                    <span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteBank('${table.id}', '${table.code}', '${table.name}')" data-toggle="modal" data-target="#DelBank" >  
                                                    </span>
                                                </center>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <form action="DaytourOperationDetail.smi" method="post" id="AirticketForm">
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="info">
                            <div class="panel panel-default">                              
                                <div class="panel-body">
                                    <div class="col-xs-12 ">
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Remark&nbsp;:</lable>                                         
                                        </div>
                                        <div class="col-sm-6" style="padding-left: 50px">
                                            <textarea  rows="3" cols="200" id="" name="" class="form-control" value=""></textarea>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 form-group"></div>
                                    <div class="col-xs-12 ">
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Text&nbsp;Amount&nbsp;:</lable>                                         
                                        </div>                                      
                                        <div class="col-sm-6" style="padding-left: 50px">
                                            <input  rows="3" cols="200" id="" name="" class="form-control" value="" readonly="">
                                        </div>
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Total&nbsp;Net&nbsp;:</lable>                                         
                                        </div>
                                        <div class="col-sm-3" >
                                            <input  rows="3" cols="200" id="" name="" class="form-control" value="" readonly="">
                                        </div>
                                    </div>    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="info">
                            <div class="panel panel-default">                              
                                <div class="panel-body">
                                    <div class="col-xs-12 ">
                                        <div class="col-md-2 text-right ">
                                            <button type="button" onclick="printVoucher('');" class="btn btn-default">
                                                <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print Package
                                            </button>
                                        </div>
                                        <div class="col-md-2 text-left " style="padding-left: 0px">
                                            <button type="button" onclick="printVoucher('');" class="btn btn-default">
                                                <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print Invoice New
                                            </button>
                                        </div>
                                        <div class="col-md-4 text-right "></div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="printVoucher('');" class="btn btn-default">
                                                <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" class="btn btn-primary" onclick="EnableVoid();" data-toggle="modal" data-target="#EnableVoid">
                                                <span id="SpanPrint" class="glyphicon glyphicon-ok" ></span> Void
                                            </button>
                                            <button type="button" class="btn btn-danger" onclick="DisableVoid();" data-toggle="modal" data-target="#DisableVoid">
                                                <span id="SpanPrint" class="glyphicon glyphicon-remove" ></span> Void
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="" class="btn btn-success">
                                                <span id="SpanPrint" class="fa fa-save"></span> Save 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="" class="btn btn-success">
                                                <span id="" class="fa fa-plus-circle"></span> New 
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>    
            </form>    
        </div>
    </div>
</div>        

<!--Disable-->
<div class="modal fade" id="DisableVoid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Finance & Cashier - Invoice</h4>
            </div>
            <div class="modal-body" id="disableVoid">
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick='window.top.location.href="Invoice.smi?button=disable"'>Delete</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<!--Enable-->
<div class="modal fade" id="EnableVoid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Finance & Cashier - Invoice </h4>
            </div>
            <div class="modal-body" id="enableCode">
                
            </div>
            <div class="modal-footer">
                <button type="button" onclick="Enable()" class="btn btn-success">Enable</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      
                                                    
<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
       $('#MasterReservation tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#MasterReservation tbody tr.row_selected').attr("id"));
            }
        });
        
        $('#collapseExample${advanced.search}').on('shown.bs.collapse', function () {
            $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-down").addClass("glyphicon glyphicon-chevron-up");
        });

        $('#collapseExample${advanced.search}').on('hidden.bs.collapse', function () {
           $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-up").addClass("glyphicon glyphicon-chevron-down");
        });
    });   
</script>
