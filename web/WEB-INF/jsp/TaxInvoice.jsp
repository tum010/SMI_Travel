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
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6 " style="padding-right: 15px">
		<c:choose>
                    <c:when test="${param.Department=='WO'}">
                        <h4><b>Tax Invoice Wendy/Outbound</b></h4>
                    </c:when>
                    <c:when test="${param.Department=='INB'}">
                        <h4><b>Tax Invoice Inbound</b></h4>
                    </c:when> 
		</c:choose> 
            </div>
            <div class="col-xs-12 form-group"><hr/></div>
        </div>
        
        <!--Search Invoice-->
        <div class="row" style="padding-left: 15px">  
            <form action="" method="post" id="ReservationTravox">
                <div role="tabpanel">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane  active" id="infoSearchInvoice">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h2 class="panel-title">
                                        <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" onclick="">
                                            <span id="SpanEdit${advanced.search}">Search Invoice</span>
                                        </a>
                                        <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}" style="margin-left: 54em" onclick="">
                                            <span id="arrowReservstion" class="arrowReservstion glyphicon glyphicon-chevron-up"></span> 
                                        </a>
                                    </h2>               
                                </div>
                                    <div class="panel-body">               
                                        <div class=" accordion-body collapse in" id="collapseExample${advanced.search}" aria-expanded="false">
                                            <div class="col-md-12">
                                                <div class="col-xs-1 text-right">
                                                    <label class="control-label" for="">Inv No </lable>
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
                                                                <th style="width: 15%" >Product</th>
                                                                <th style="width: 60%">Description</th>
                                                                <th style="width: 10%">Amount</th>
                                                                <th style="width: 1%">Currency</th>
                                                                <th style="width: 1%">Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>               
                                                            <tr>
                                                                <td>TEST</td>
                                                                <td>Hello World</td>
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
                </form>                                        
                <div class="col-xs-12 form-group"></div>
                
                <!--Search-->  
                <form action="" method="post" id="" name="" role="form">
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Tax Invoice No</lable>
                        </div>
                        <div class="col-md-2 form-group">
                            <input type="text"  class="form-control" id="TaxInvNo" name="TaxInvNo"  value="" >
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
                    </div>    
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Tax Inv To</lable>
                        </div>
                        <div class="col-md-6 form-group">
                            <div class="input-group">
                            <input type="hidden" class="form-control" id="TaxInvTo_Id" name="TaxInvToId" value=""/>
                            <input type="text" class="form-control" id="TaxInvTo" name="TaxInvTo" value="" style="background-color: #ffffff">
                            <span class="input-group-addon" id="TaxInvTo_Modal"  data-toggle="modal" data-target="#TaxInvToModal">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Name </lable>
                        </div>    
                        <div class="col-md-6 form-group">
                            <input  type="text" id="InvToName" name="InvToName" class="form-control" value="" readonly="">
                        </div>  
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Address </lable>
                        </div>
                        <div class="col-md-6 form-group">
                            <textarea  rows="3" cols="100" id="InvToAddress" name="InvToAddress" class="form-control" value="" ></textarea>
                        </div>
                    </div>
                    <div class="col-xs-12 ">
                        <div class="col-md-2 text-left">
                            <label class="control-label" for="">Passenger</label>
                        </div>                       
                        <div class="col-md-2 form-group">
                            <div class="input-group">
                            <input type="hidden" class="form-control" id="PassengerId" name="PassengerId" value=""/>
                            <input type="text" class="form-control" id="PassengerCode" name="PassengerCode" value="" style="background-color: #ffffff">
                            <span class="input-group-addon" id="Passenger_Modal"  data-toggle="modal" data-target="#PassengerModal">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                            </div>
                        </div>
                        <div class="col-md-3 form-group">
                            <input type="text"  class="form-control" id="PassengerName" name="PassengerName"  value="" readonly="">
                        </div>
                        <div class="col-md-1 text-right">
                            <label class="control-label" for="" >A/R&nbsp;Code</label>
                        </div>  
                        <div class="col-md-2 form-group">
                            <div class="input-group">
                                <input type="hidden" class="form-control" id="ARCodeId" name="ARCodeId" value=""/>
                                <input type="text" class="form-control" id="ARCode" name="ARCode" value="" style="background-color: #ffffff">
                                <span class="input-group-addon" id="ARCode_Modal"  data-toggle="modal" data-target="#ARCodeModal">
                                   <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                    </div>    
                </form>                
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
                                            <button type="button" onclick="" class="btn btn-default">
                                                <span id="SpanPrintInvoiceNew" class="glyphicon glyphicon-print"></span> Print Invoice New
                                            </button>
                                        </div>
                                        <div class="col-md-4 text-right "></div>
                                        <div class="col-md-1 text-right ">
                                            <button type="button" onclick="printVoucher('');" class="btn btn-default">
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
    </div>
                                            
<!--Modal Search Tax Inv To-->
<div class="modal fade" id="TaxInvToModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Tax Invoice To</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="TaxInvoiceToTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr class="packet">
                                <td class="">XXX
                                <td>XXXXX</td>                              
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
<div class="modal fade" id="PassengerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Passenger</h4>
            </div>
            <div class="modal-body">
                <!--Passenger List Table-->
                <table class="display" id="PassengerTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr class="packet">
                                <td class="">XXX
                                <td>XXXXX</td>  
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
        
        $('#PassengerTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": true,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 3
        });
        
        $('#TaxInvoiceToTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });
        
        $('#ARCodeTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });
        
         $('#collapseExample${advanced.search}').on('shown.bs.collapse', function () {
            $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-down").addClass("glyphicon glyphicon-chevron-up");
        });

        $('#collapseExample${advanced.search}').on('hidden.bs.collapse', function () {
           $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-up").addClass("glyphicon glyphicon-chevron-down");
        });
        
        
        
    });
</script>
