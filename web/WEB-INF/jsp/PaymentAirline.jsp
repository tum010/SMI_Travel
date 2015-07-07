<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/PaymentAirline.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="content-header" >
    <h1>
        Checking - Air Ticket
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Checking</a></li>          
        <li class="active"><a href="#">Payment Airline</a></li>
    </ol>
</section>

<div style="padding-top: 15px;padding-right: 0px "ng-app=""> 
    <div class="row">
        <!--Alert Save and Update-->
        <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Success!</strong> 
        </div>
        <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Unsuccess!</strong> 
        </div>
        
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Checking/CheckingAirTicketMenu.html'"></div>
        </div>
        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6" style="padding-right: 15px">
                    <h4><b>Payment Airline</b></h4>
                </div>
            </div>
            <hr/>
            
            <form action="PaymentAirline.smi" method="post" id="PaymentAirlineForm" name="PaymentAirlineForm" role="form">
                
                <div class="panel panel-default">
                    <div class="panel-body"  style="padding-right: 0px;" style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Payment No :</label>
                            </div>
                            <div class="col-xs-1" style="width:290px">
                                <div class='input-group'>
                                    <input id="paymentNo" name="paymentNo" type="text" style="width: 285px" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 140px">
                                <label class="control-label text-right">Payment Date :</label>
                            </div>
                            <div class="col-xs-1" style="width: 170px">
                                <div class='input-group date'>
                                    <input id="inputPaymentDate" name="inputPaymentDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 165px">
                                <label class="control-label text-right">Due Payment Date :</label>
                            </div>
                            <div class="col-xs-1" style="width: 170px">
                                <div class='input-group date'>
                                    <input id="inputDuePaymentDate" name="inputDuePaymentDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Invoice Sup :</label>
                            </div>
                            <div class="col-xs-1"  style="width: 155px">
                                <div class="input-group" id="agentCodeValidate">
                                    <input type="hidden" class="form-control" id="agentId" name="agentId" value="" />
                                    <input type="text" class="form-control" id="agentCode" name="agentCode" value="" />
                                    <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#AgentModal">
                                        <span class="glyphicon-search glyphicon"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-left" style="width: 160px">
                                <input type="text" class="form-control" id="agentName" name="agentName" value="${SelectedAgent.name}" readonly=""
                                               data-bv-notempty="true" data-bv-notempty-message="agent empty !">                           
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 115px">
                                <label class="control-label text-right">A/P Code :</label>
                            </div>
                            <div class="col-xs-1"  style="width: 155px">
                                <div class="input-group" id="apCodeValidate">
                                    <input type="text" class="form-control" id="apCode" name="apCode" value="${SelectedAp.code}" />
                                    <span class="input-group-addon" id="ap_modal"  data-toggle="modal" data-target="#ApModal">
                                        <span class="glyphicon-search glyphicon"></span>
                                    </span>
                                </div>
                            </div>
<!--                            <div class="col-xs-1 text-left" style="width: 160px">
                                <input type="text" class="form-control" id="apName" name="apName" value="${SelectedAp.name}" readonly=""
                                               data-bv-notempty="true" data-bv-notempty-message="agent empty !">                               
                            </div>   -->
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 128px">
                                <label class="control-label text-right">Detail :</label>
                            </div>
                            <div class="col-xs-1"  style="width: 310px">
                                <div class="input-group">                                    
                                    <textarea rows="3" class="form-control" id="detail" name="detail" style="width: 175%"></textarea>  
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 120px">
                                <label class="control-label text-right">Pay By :</label>
                            </div>
                            <div class="col-xs-1" style="width: 170px">
                                <select id="inputPayBy" name="inputPayBy" class="form-control selectize">
                                    <option value="">---Pay By---</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">Ticket Fare</h4>
                    </div> 
                    <div class="panel-body" style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 128px">
                                <label class="control-label text-right">Ticket Form :</label>
                            </div>
                            <div class="col-xs-1" style="width: 300px">
                                <select id="inputTicketForm" name="inputTicketForm" class="form-control selectize">
                                    <option value="">---Ticket Form---</option>

                                </select>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 122px">
                                <label class="control-label text-right">Type Airline :</label>
                            </div>
                            <div class="col-xs-1" style="width: 300px">
                                <select id="inputTypeAirline" name="inputTypeAirline" class="form-control selectize">
                                    <option value="">---Ticket Form---</option>

                                </select>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 128px">
                                <label class="control-label text-right">Form :</label>
                            </div>
                            <div class="col-xs-1" style="width: 170px">
                                <div class='input-group date'>
                                    <input id="inputFormDate" name="inputFormDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 130px">
                            </div>
                            <div class="col-xs-1 text-right" style="width: 122px">
                                <label class="control-label text-right">To :</label>
                            </div>
                            <div class="col-xs-1" style="width: 170px">
                                <div class='input-group date'>
                                    <input id="inputToDate" name="inputToDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <table class="display" id="InvoiceDeailTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:5%;">Ref No</th>
                                    <th style="width:15%;">Ticket No</th>
                                    <th style="width:10%;">Department</th>
                                    <th style="width:20%;">Fare</th>
                                    <th style="width:10%;">Tax</th>
                                    <th style="width:10%;">Insurance</th>
                                    <th style="width:10%;">Commission</th>
                                    <th style="width:10%;">Amount</th>
                                    <th style="width:10%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
                            
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">Refund Ticket</h4>
                    </div> 
                    <div class="panel-body" style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 128px">
                                <label class="control-label text-right">Refund :</label>
                            </div>
                            <div class="col-xs-1" style="width: 280px">
                                <div class='input-group'>
                                    <input id="refund" name="refund" type="text" style="width: 260px" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 100px">
                                <button style="height:30px" type="submit"  id="ButtonAdd"  name="ButtonAdd" onclick="addAction();" class="btn btn-primary btn-sm">&nbsp;&nbsp;Add&nbsp;&nbsp;</button>
                            </div>
                        </div>
                        <table class="display" id="InvoiceDeailTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:5%;">Refund</th>
                                    <th style="width:15%;">Ticket No</th>
                                    <th style="width:10%;">Department</th>
                                    <th style="width:20%;">Route</th>
                                    <th style="width:10%;">Commission</th>
                                    <th style="width:10%;">Amount</th>
                                    <th style="width:10%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
                            
                <div class="panel panel-default">
<!--                    <div class="panel-heading">
                        <h4 class="panel-title">Ticket Detail</h4>
                    </div> -->
                    <div class="panel-body" style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Agent Amount :</label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="agentAmount" name="agentAmount" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Credit Note :</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="creditNote" name="creditNote" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 140px">
                                <label class="control-label text-right">Credit Amount :</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="creditAmount" name="creditAmount" type="text" class="form-control" value="">
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Commission Vat :</label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="commissionVat" name="commissionVat" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Debit Note :</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="debitNote" name="debitNote" type="text" class="form-control" value="">
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Cash :</label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="cash" name="cash" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Withholding Tax :</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="withholdingTax" name="withholdingTax" type="text" class="form-control" value="">
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Chq No :</label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="chqNo" name="chqNo" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 155px">
                                <label class="control-label text-right">Amount :</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="amount" name="amount" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 140px">
                                <label class="control-label text-right">Total Payment :</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">                                    
                                    <input id="totalPaymentAmount" name="totalPaymentAmount" type="text" class="form-control" value="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-12 text-right" >
                            <button type="submit" id="ButtonSave" name="ButtonSave" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                  
                            <button type="submit" id="ButtonSaveAndNew" name="ButtonSaveAndNew" class="btn btn-success"><i class="fa fa-save"></i> Save & New</button>
          
                            <button type="submit" id="ButtonPrint" name="ButtonPrint" class="btn btn-default"><i class="fa fa-print"></i> Print </button>
                        </div>
                    </div>
                </div>           
            </form>               
        </div>
    </div>
                                               
<!--Modal  Agent-->
<div class="modal fade" id="AgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Invoice Sup</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="AgentTable">
                    <thead class="datatable-header">                     
                        <tr>
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                            <th class="hidden">Fax</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        agent = [];
                    </script>
                    <c:forEach var="a" items="${agent}">
                        <tr>
                            <td class="agent-id hidden">${a.id}</td>
                            <td class="agent-user">${a.code}</td>
                            <td class="agent-name">${a.name}</td>
                            <td class="agent-addr hidden">${a.address}</td>
                            <td class="agent-tel hidden">${a.tel}</td>
                            <td class="agent-fax hidden">${a.fax}</td>
                        </tr>
                        <script>
                            agent.push({id: "${a.id}", code: "${a.code}", name: "${a.name}", 
                                        address: "${a.address}", tel: "${a.tel}", fax: "${a.fax}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="AgentModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--Modal  A/P Code-->
<div class="modal fade" id="ApModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">A/P Code</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="ApCodeTable">
                    <thead class="datatable-header">                     
                        <tr>
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                            <th class="hidden">Fax</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        agent = [];
                    </script>
                    <c:forEach var="a" items="${agent}">
                        <tr>
                            <td class="agent-id hidden">${a.id}</td>
                            <td class="agent-user">${a.code}</td>
                            <td class="agent-name">${a.name}</td>
                            <td class="agent-addr hidden">${a.address}</td>
                            <td class="agent-tel hidden">${a.tel}</td>
                            <td class="agent-fax hidden">${a.fax}</td>
                        </tr>
                        <script>
                            agent.push({id: "${a.id}", code: "${a.code}", name: "${a.name}", 
                                        address: "${a.address}", tel: "${a.tel}", fax: "${a.fax}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="ApModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
