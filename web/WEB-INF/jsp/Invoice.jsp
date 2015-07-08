<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Invoice.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="type" value="${requestScope['typeInvoice']}" />
<input type="hidden" id="type" name="type" value="${param.type}">


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
                        <c:when test="${fn:contains(type , 'temp')}">
                            <h4><b>Invoice Temp Wendy/Outbound</b></h4>
                        </c:when>
                        <c:when test="${fn:contains(type , 'vat')}">
                            <h4><b>Invoice Vat Wendy/Outbound</b></h4>
                        </c:when>    
                        <c:when test="${fn:contains(type , 'NoVat')}">
                            <h4><b>Invoice No Vat Wendy/Outbound</b></h4>
                        </c:when>    
                    </c:choose>                
                </div>
                <div class="col-xs-12 form-group"><hr/></div>
            </div>
            
            <!--Search Invoice-->
            <div class="row" style="padding-left: 15px">  
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="infoSearchInvoice">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h2 class="panel-title">
                                        <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" onclick="">
                                            <span id="SpanEdit${advanced.search}">Search Invoice</span>
                                        </a>
                                        <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" style="margin-left: 55em" onclick="">
                                            <span id="arrowReservstion" class="arrowReservstion glyphicon glyphicon-chevron-up"></span> 
                                        </a>
                                    </h2>               
                                </div>
                                <div class="panel-body">               
                                    <div class=" accordion-body collapse in" id="collapseExample${advanced.search}" aria-expanded="false">
                                        <div class="col-md-12">
                                            <div class="col-xs-1 text-right">
                                                <label class="control-label" for="">Ref no </lable>
                                            </div>
                                            <div class="col-md-2 form-group">
                                                <div class="input-group">
                                                    <input type="text" class="form-control" id="SearchRefNo" name="SearchRefNo" value="" >                                 
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
                                                                    <a href=""><span class="glyphicon glyphicon-plus"></span></a>
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
            <div class="col-xs-12 form-group"></div>
                
                <!--Search-->  
                <form action="" method="post" id="" name="" role="form">
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right">
                            <label class="control-label" for="">INV no</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <input type="text"  class="form-control" id="InvNo" name="InvNo"  value="" >
                        </div>
                        <div class="col-xs-2 text-right">
                            <label class="control-label" for="">Invoice date</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <div class='input-group date' id='InputDatePicker'>
                            <c:if test='${dayTourOperation.tourDate != null}'>
                                <input id="InputInvDate" name="InputInvDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>
                            <c:if test='${dayTourOperation.tourDate == null}'>
                                <input id="InputInvDate" name="InputInvDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['']}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                
                            </c:if>                             
                        </div>
                        </div>
                        <div class="col-xs-2 text-right">
                            <label class="control-label" for="">Due date </lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <div class='input-group date' id='InputDatePicker'>
                            <c:if test='${dayTourOperation.tourDate != null}'>
                                <input id="InputDueDate" name="InputDueDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                         
                            </c:if>
                            <c:if test='${dayTourOperation.tourDate == null}'>
                                <input id="InputDueDate" name="InputDueDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['']}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                              
                            </c:if>                             
                        </div>
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-sm-1 text-right">
                            <label class="control-label" for="">Inv To</lable>
                        </div>
                        <div class="col-md-6 form-group">
                            <div class="input-group">
                            <input type="hidden" class="form-control" id="InvTo_Id" name="InvToId" value=""/>
                            <input type="text" class="form-control" id="InvTo" name="InvTo" value="" style="background-color: #ffffff">
                            <span class="input-group-addon" id="InvTo_Modal"  data-toggle="modal" data-target="#InvToModal">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                            </div>
                        </div>
                        <div class="col-xs-2 text-right">
                            <label class="control-label" for="">Term pay</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <input type="text"  class="form-control" id="TermPay" name="TermPay"  value="" >
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-sm-1 text-right">
                            <label class="control-label" for="">Name </lable>
                        </div>    
                        <div class="col-md-6 form-group">
                            <input  type="text" id="InvToName" name="InvToName" class="form-control" value="" >
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2" style="padding-left: 53px">
                            <label for="Department" class="col-sm-3 control-label" >Department</label>
                            </div>
                            <div class="radio col-sm-2">   
                                <label><input value="WendyAirTicket" id="DepartmentAirTicket" name="Department" type="radio" >Wendy Air Ticket</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-sm-1 text-right">
                            <label class="control-label" for="">Address </lable>
                        </div>
                        <div class="col-md-6 form-group">
                            <textarea  rows="3" cols="100" id="InvToAddress" name="InvToAddress" class="form-control" value="" ></textarea>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2"></div>
                            <div class="radio col-sm-2">   
                                <label><input value="WendyPackage" id="DepartmentPackage" name="Department" type="radio" >Wendy Package</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-2"></div>
                            <div class="radio col-sm-2">   
                                <label><input value="Outbound" id="DepartmentOutbound" name="Department" type="radio" >Outbound</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right">
                            <label class="control-label" for="">Sale&nbsp;Staff</label>
                        </div>                       
                        <div class="col-md-2 form-group">
                            <div class="input-group">
                            <input type="hidden" class="form-control" id="" name="" value=""/>
                            <input type="text" class="form-control" id="SaleStaffName" name="SaleStaffName" value="" style="background-color: #ffffff">
                            <span class="input-group-addon" id="SaleStaff_Modal"  data-toggle="modal" data-target="#SaleStaffModal">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                            </div>
                        </div>
                        <div class="col-md-3 form-group">
                            <input type="text"  class="form-control" id="" name=""  value="" readonly="">
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
                                <input type="text" class="form-control" id="ARCode" name="ARCode" value="" style="background-color: #ffffff">
                                <span class="input-group-addon" id="ARCode_Modal"  data-toggle="modal" data-target="#ARCodeModal">
                                   <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                    </div>    
                </form>                
            </div>
            
            <form action="Invoice.smi" method="post" id="DetailBillableForm">
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="infoDetailBillable">
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
                                                      onclick="" data-toggle="modal" data-target="#EditDetailBill" >
                                                    </span>
                                                    <span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill('', '')" data-toggle="modal" data-target="#DelDetailBill" >  
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
            
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="infoRemark">
                            <div class="panel panel-default">                              
                                <div class="panel-body">
                                    <div class="col-xs-12 ">
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Remark&nbsp;</lable>                                         
                                        </div>
                                        <div class="col-sm-6" style="padding-left: 50px">
                                            <textarea  rows="3" cols="200" id="Remark" name="Remark" class="form-control" value=""></textarea>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 form-group"></div>
                                    <div class="col-xs-12 ">
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Text&nbsp;Amount&nbsp;:</lable>                                         
                                        </div>                                      
                                        <div class="col-sm-6" style="padding-left: 50px">
                                            <input  rows="3" cols="200" id="TextAmount" name="TextAmount" class="form-control" value="" readonly="">
                                        </div>
                                        <div class="col-sm-1">
                                            <label class="control-label" for="">Total&nbsp;Net&nbsp;:</lable>                                         
                                        </div>
                                        <div class="col-sm-3" >
                                            <input  rows="3" cols="200" id="TotalNet" name="TotalNet" class="form-control" value="" readonly="">
                                        </div>
                                    </div>    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="infoButton">
                            <div class="panel panel-default">                              
                                <div class="panel-body">
                                    <div class="col-xs-12 ">
                                        <div class="col-md-2 text-right ">
                                            <button type="button" onclick="" class="btn btn-default">
                                                <span id="SpanPrintPackage" class="glyphicon glyphicon-print"></span> Print Package
                                            </button>
                                        </div>
                                        <div class="col-md-2 text-left " style="padding-left: 0px">
                                            <button type="button" onclick="printInvoiceNew('')" class="btn btn-default">
                                                <span id="SpanPrintInvoiceNew" class="glyphicon glyphicon-print"></span> Print Invoice New
                                            </button>
                                        </div>
                                        <div class="col-md-4 text-right "></div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="printInvoice('');" class="btn btn-default">
                                                <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" class="btn btn-primary hidden" onclick="EnableVoid();" data-toggle="modal" data-target="#EnableVoid">
                                                <span id="SpanEnableVoid" class="glyphicon glyphicon-ok" ></span> Void
                                            </button>
                                            <button type="button" class="btn btn-danger" onclick="DisableVoid();" data-toggle="modal" data-target="#DisableVoid">
                                                <span id="SpanDisableVoid" class="glyphicon glyphicon-remove" ></span> Void
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="" class="btn btn-success">
                                                <span id="SpanSave" class="fa fa-save"></span> Save 
                                            </button>
                                        </div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="" class="btn btn-success">
                                                <span id="SpanNew" class="fa fa-plus-circle"></span> New 
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

<!--Disable Modal-->
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
                <button type="button" class="btn btn-danger" onclick='window.top.location.href="Invoice.smi?type=${param.type}&action=edit"'>Delete</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--Enable Modal-->
<div class="modal fade" id="EnableVoid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Finance & Cashier - Invoice</h4>
            </div>
            <div class="modal-body" id="enableVoid">
                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick='window.top.location.href="Invoice.smi?type=${param.type}&action=edit"'>Enable</button>               
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<!--Delete Detail Billable Modal-->
<div class="modal fade" id="DelDetailBill" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Finance & Cashier - Invoice </h4>
            </div>
            <div class="modal-body" id="enableCode">
                
            </div>
            <div class="modal-footer">  
                <button type="button" onclick="DeleteDetailBill()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->  

<!--Inv To Modal-->
<div class="modal fade" id="InvToModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Inv To</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->

                <table class="display" id="InvToTable">
                    <thead>    
                        <script>
                            var inv_to = [];
                        </script>
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                            <th class="">Name</th>
                        </tr>                      
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${agent_list}">
                            <tr onclick ="setupagentvalue('${table.id}', '${table.code}', '${table.name}')" >
                                <td class="hidden">${table.id}</td>
                                <td>${table.code} </td>
                                <td>${table.name} </td>
                            </tr>    
                            <script>
                                inv_to.push({id: "${table.id}", code: "${table.code}", name: "${table.name}"});
                            </script>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div  class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Sale Staff To Modal-->
<div class="modal fade" id="SaleStaffModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Sale Staff</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->

                <table class="display" id="SaleStaffTable">
                    <thead>    
                        <script>
                            var sale_staff = [];
                        </script>
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                            <th class="">Name</th>
                        </tr>                      
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${agent_list}">
                            <tr onclick ="setupagentvalue('${table.id}', '${table.code}', '${table.name}')" >
                                <td class="hidden">${table.id}</td>
                                <td>${table.code} </td>
                                <td>${table.name} </td>
                            </tr>    
                            <script>
                                sale_staff.push({id: "${table.id}", code: "${table.code}", name: "${table.name}"});
                            </script>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div  class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--A/R Code Modal-->
<div class="modal fade" id="ARCodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">A/R Code</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->

                <table class="display" id="ARCodeTable">
                    <thead>    
                        <script>
                            var arcode = [];
                        </script>
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                        </tr>                      
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${agent_list}">
                            <tr onclick ="setupagentvalue('${table.id}', '${table.code}', '${table.name}')" >
                                <td class="hidden">${table.id}</td>
                                <td>${table.code} </td>
                                <td>${table.name} </td>
                            </tr>    
                            <script>
                                arcode.push({id: "${table.id}", code: "${table.code}", name: "${table.name}"});
                            </script>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div  class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>  
                                                    
<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
       $('.date').datetimepicker();
       $('.datemask').mask('0000-00-00');
       $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

       });
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
        
        $('#DetailBillableTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#DetailBillableTable tbody tr.row_selected').attr("id"));
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
