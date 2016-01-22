<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<c:set var="userList" value="${requestScope['userList']}" />
<c:set var="listAgent" value="${requestScope['listAgent']}" />
<c:set var="listTermPay" value="${requestScope['listTermPay']}" />
<c:set var="vatMDE" value="${requestScope['vatMDE']}" />
<c:set var="whtMDE" value="${requestScope['whtMDE']}" />
<section class="content-header"  >
    <h4>
        <b>Report : Billing Air Agent </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Billing Air Agent</a></li>
    </ol>
</section>
<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/AirticketReportMenu.html'"></div>
            </div>

            <div class="form-group">
                <div class="col-md-6">
                    <h3>Billing Air Agent</h3>
                </div>
            </div>
            
            <div class="col-md-10" >
                <form action="BillAirAgent.smi" method="post" id="BillAirAgent" name="BillAirAgent" role="form">
                    <input type="hidden" class="form-control" id="vatTemp" name="vatTemp" value="${vatMDE}"/>
                    <input type="hidden" class="form-control" id="whtTemp" name="whtTemp" value="${whtMDE}"/>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Agent <font style="color: red">*</font></label>
                                <div class="col-md-3 form-group" id="agentcodepanel">  
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="agentId" name="agentId" value=""/>
                                        <input type="text" class="form-control" id="agentCode" name="agentCode" value="" />
                                        <span class="input-group-addon" id="saleby_modal"  data-toggle="modal" data-target="#AgentModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3" id="agentnamepanel">
                                    <input type="text" class="form-control" id="agentName" name="agentName" value="" readonly="">
                                </div>
                            </div> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="invfromdatepanel">
                                <label class="col-md-6 control-label text-right">Inv From</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date invfromdate' id='invDateFrom'>
                                            <input type='text' id="invoiceFromDate" name="invoiceFromDate" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="invtodatepanel">
                                <label class="col-md-6 control-label text-right">Inv To</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date invtodate' id='invDateTo'>
                                            <input type='text' id="InvoiceToDate" name="InvoiceToDate" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="issuefromdatepanel">
                                <label class="col-md-6 control-label text-right">Issue From</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date issuefromdate' id='DateFromIssue'>
                                            <input type='text' id="issueFrom" name="issueFrom" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="issuetodatepanel">
                                <label class="col-md-6 control-label text-right">Issue To</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date issuetodate' id='DateToIssue'>
                                            <input type='text' id="issueTo" name="issueTo" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Payment Type</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="paymentType" id="paymentType"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="B" >BSP</option>
                                            <option value="D" >DOMESTIC</option>
                                            <option value="A" >AGENT</option>
                                            <option value="TI" >TG INTER</option>
                                            <option value="TD" >TG DOMESTIC</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="refundfromdatepanel">
                                <label class="col-md-6 control-label text-right">Refund Receive From <font style="color: red">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date refundfromdate' id='DateFromRefund'>
                                            <input type='text' id="refundFrom" name="refundFrom" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="refundtodatepanel">
                                <label class="col-md-6 control-label text-right">Refund Receive To <font style="color: red">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date refundtodate' id='DateToRefund'>
                                            <input type='text' id="refundTo" name="refundTo"  class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Department</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="department" id="department" class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="wendy" >Wendy</option>
                                            <option value="inbound" >Inbound</option>
                                            <option value="outbound" >Outbound</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>           
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Sale By</label>
                                <div class="col-md-3 form-group">  
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="salebyId" name="salebyId" value=""/>
                                        <input type="text" class="form-control" id="salebyUser" name="salebyUser" value="" />
                                        <span class="input-group-addon" id="saleby_modal"  data-toggle="modal" data-target="#SaleByModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="salebyName" name="salebyName" value="" readonly="">
                                </div>
                            </div>   
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Term Pay</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="termPay" id="termPay"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:forEach var="term" items="${listTermPay}" >
                                                <c:set var="select" value="" />
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="rept"></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <button type=button onclick="printBillAirAgent();" id="printbutton" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                </form>                
            </div>
        </div>
    </div>
</div>
                                
<div class="modal fade" id="SaleByModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Sale By</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="SaleByTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        saleby = [];
                    </script>
                    <c:forEach var="table" items="${userList}">
                        <tr>
                            <td class="saleby-id hidden">${table.id}</td>
                            <td class="saleby-user">${table.username}</td>
                            <td class="saleby-name">${table.name}</td>
                        </tr>
                        <script>
                            saleby.push({id: "${table.id}", username: "${table.username}", name: "${table.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="SaleByModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div> 
<div class="modal fade" id="AgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Agent</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="AgentTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        agent = [];
                    </script>
                    <c:forEach var="ag" items="${listAgent}">
                        <tr onclick="setBillAgentValue('${ag.id}', '${ag.code}', '${ag.name}');">
                            <td class="agent-id hidden">${ag.id}</td>
                            <td class="agent-user">${ag.code}</td>
                            <td class="agent-name">${ag.name}</td>
                        </tr>
                        <script>
                            agent.push({id: "${ag.id}", username: "${ag.code}", name: "${ag.name}"});
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
<script type="text/javascript">
    $(document).ready(function() {
    $('.datemask').mask('0000-00-00');

    $("#BillAirAgent").bootstrapValidator({
        framework: 'bootstrap',
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            agentCode: {
                trigger: 'focus keyup change',
                validators: {
                    notEmpty: {
                            message: 'Input agentCode '
                        }
                }
            },
            agentName: {
                trigger: 'focus keyup change',
                validators: {
                    notEmpty: {
                            message: 'Input agentName '
                        }
                }
            },
            refundFrom: {
                trigger: 'focus keyup change',
                validators: {
                    notEmpty: {
                        message: 'Refund Date From is required'
                    },
                    date: {
                        format: 'YYYY-MM-DD',
                        max: 'refundTo:',
                        message: 'Refund Date From is not a valid'
                    }
                }
            },
            refundTo: {
                trigger: 'focus keyup change',
                validators: {
                    notEmpty: {
                        message: 'Refund Date To is required'
                    },
                    date: {
                        format: 'YYYY-MM-DD',
                        min: 'refundFrom',
                        message: 'Refund Date To is not a valid'
                    }
                }
            }
        }
        });

    });
    function setBillAgentValue(id, code, name) {
        $("#agentId").val(id);
        $("#agentCode").val(code);
        $("#agentName").val(name);
        
        $('#BillAirAgent').bootstrapValidator('revalidateField', 'agentName');
        $('#BillAirAgent').bootstrapValidator('revalidateField', 'agentCode');
        $("#AgentModal").modal('hide');
    }
    
    function validateBillAirAgent(){
//        $("#agentcodepanel").removeClass("has-success"); 
//        $("#agentnamepanel").removeClass("has-success"); 
//        
//        $("#agentcodepanel").addClass("has-error"); 
//        $("#agentnamepanel").addClass("has-error"); 
        $('#BillAirAgent').bootstrapValidator('revalidateField', 'agentName');
        $('#BillAirAgent').bootstrapValidator('revalidateField', 'agentCode');
        
//        $("#printbutton").addClass("disabled");
    }
    
    function validateRefundDate(){
        $("#refundfromdatepanel").removeClass("has-success"); 
        $("#refundtodatepanel").removeClass("has-success"); 
        
        $("#refundfromdatepanel").addClass("has-error"); 
        $("#refundtodatepanel").addClass("has-error"); 
        
    }
</script>  
<script type="text/javascript" src="js/BillAirAgent.js"></script> 