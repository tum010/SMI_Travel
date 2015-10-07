<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/ReceiveTable.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="customerAgentInfoList" value="${requestScope['customerAgentInfoList']}" />
<c:set var="mAccpayList" value="${requestScope['mAccpayList']}" />
<c:set var="mCreditBankList" value="${requestScope['mCreditBankList']}" />

<section class="content-header" >
    <h1>
        Finance & Cashier - Receive Table
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Receive Table</a></li>
    </ol>
</section>
<form action="ReceiveTable.smi" method="post" id="receiveForm" role="form" autocomplete="off">
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app="">
    <div class="col-sm-12" style="padding-left: 50px;padding-right: 50px;">
        <div class="panel panel-default">
            <div class="panel-heading">
                 <label class="control-label">Receive Table</lable>
            </div>
            <div class="panel-body">               
                <div class="row" style="padding-left: 0px">
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                            <label class="control-label">Date </lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="padding-left:5px">
                            <div class="col-sm-12">
                                <div class='input-group datesearch' style="width:140px;">
                                    <input name="InputDate" id="InputDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                            <label class="control-label">Vat Type </lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right:0px;width:150px;">
                            <div class="col-sm-12">
                                <select name="SelectStatus" id="SelectStatus" class="form-control" onchange="checkSearch()">
                                    <option value=""></option>
                                    <option value="V">Vat</option>
                                    <option value="T">Temp</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-2 text-left" style="width: 100px;">
                            <a id="ButtonSearch" name="ButtonSearch" onclick="searchReceive()" class="btn btn-primary">
                                <i class="glyphicon glyphicon-search"></i> Search
                            </a>
                        </div>
                        <div class="col-xs-2 text-right" style="width: 100px;" >
                            <button type="button" id="ButtonPrint" name="ButtonPrint" class="btn btn-default">
                                <i class="fa fa-print"></i> Print             
                            </button>
                        </div>
                        <div class="col-xs-2 text-right" style="width: 390px;">
                            <a data-toggle="collapse" class="btn btn-success" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" onclick="AddReceiveData()">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                    </div>   
                </div><!-- End Row 1--><br>                             
                <div class="row" style="padding-left: 25px;width: 100%;">
                    <table class="display" id="ReceiveTable">
                        <thead class="datatable-header">
                            <tr>
                                <th style="width: 10%">Receive Date</th>
                                <th style="width: 8%">A/R Code</th>
                                <th style="width: 20%">Receive Name</th>
                                <th style="width: 20%">Description</th>
                                <th style="width: 10%">Payment</th>
                                <th style="width: 12%">Receive Amount</th>
                                <th style="width: 8%">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>26/07/2015</td>
                                <td>344555</td>
                                <td>XXXXX</td>
                                <td>test description</td>
                                <td>XXXXX</td>
                                <td>10,000</td>
                                <td class="text-center">
                                    <a href="#" onclick=""  data-toggle="modal" data-target="">
                                        <span class="glyphicon glyphicon-edit editicon" onclick="" ></span>
                                    </a>
                                    <a href="#" onclick=""  data-toggle="modal" data-target="">
                                        <span id="removeSpan${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteAir('${table.id}', '${table.code}')" data-toggle="modal" data-target="#delReceiveModal"></span>
                                    </a>                                 
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <br>
                <div class="panel panel-default hidden" id="receiveData">
                    <div class="panel-body">
                        <div class="tab-content collapse out" id="collapseExample" aria-expanded="false">
                        <div role="tabpanel" class="tab-pane hidden active" id="receive">
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1 form-group" style="width: 135px">
                                    <label class="control-label text-left">Receive Date<font style="color: red">*</font></lable>        
                                </div>
                                <div class="col-xs-1 form-group" style="width: 170px">
                                    <div class='input-group date' id='InputDatePicker'>
                                        <input name="receiveDate" id="receiveDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                                <div class="col-xs-1" style="width: 120px"></div>
                                <div class="col-xs-1" style="width: 100px">
                                    <label class="control-label text-left">Vat Type</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <select name="vatType" id="vatType" class="form-control">
                                        <option value=""></option>
                                        <option value="V">Vat</option>
                                        <option value="T">Temp</option>
                                    </select>    
                                </div>
                            </div>   
                        </div><!-- End Row 1--><br>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1 form-group" style="width: 135px">
                                    <label class="control-label text-left">Receive Name<font style="color: red">*</font></lable>        
                                </div>
                                <div class="col-xs-1 form-group" style="width: 170px">
                                    <div class="input-group">
                                        <input name="receiveId" id="receiveId" type="hidden" class="form-control" value="" />
                                        <input name="receiveCode" id="receiveCode" type="text" class="form-control" value="" />
                                        <span class="input-group-addon" id="receiveModal"  data-toggle="modal" data-target="#ReceiveModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>    
                                </div>
                                <div class="col-xs-1 form-group" style="width: 420px">
                                    <input name="receiveName" id="receiveName" type="text" class="form-control" value="" />
                                </div>
                                <div class="col-xs-1 text-right" style="width: 170px">
                                    <label class="control-label">AR Code</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="receiveArCode" id="receiveArCode" type="text" class="form-control" value="" readonly=""/>
                                </div>
                            </div>
                        </div><!-- End Row 2--><br>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1" style="width: 135px">
                                    <label class="control-label text-left">Description</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 590px">
                                    <textarea name="description" id="description" class="form-control" rows="3"></textarea>
                                </div>
                                <div style="col-xs-1">
                                    <div class="col-xs-1 text-right" style="width: 170px;padding-top: 5px">
                                        <label class="control-label">Status<font style="color: red">*</font></lable>        
                                    </div>
                                    <div class="col-xs-1 form-group" style="width: 200px;padding-top: 5px">
                                        <select name="status" id="status" class="form-control">
                                            <option value=""></option>
                                            <c:forEach var="mAccpay" items="${mAccpayList}">                                       
                                                <c:set var="select" value="" />
                                                <c:if test="${mAccpay.code == taxDetail.curCost}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option  value="${mAccpay.code}" ${select}>${mAccpay.name}</option>
                                            </c:forEach>                                            
                                        </select>    
                                    </div>                               
                                    <div class="col-xs-1 text-right" style="width: 895px">
                                        <label class="control-label text-left">Receive Amount<font style="color: red">*</font></lable>        
                                   </div>                               
                                    <div class="col-xs-1 form-group" style="width: 200px">
                                        <input name="receiveAmount" id="receiveAmount" type="text" class="form-control money" value=""/>
                                    </div>
                                </div>
                            </div>
                        </div><!-- End Row 3--><br>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1" style="width: 135px">
                                    <label class="control-label text-left">Cash Amount</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="cashAmount" id="cashAmount" type="text" class="form-control money" value=""/>
                                </div>
                                <div class="col-xs-1" style="width: 60px"></div>
                                <div class="col-xs-1" style="width: 130px">
                                    <label class="control-label text-left">Bank Amount</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="bankAmount" id="bankAmount" type="text" class="form-control money" value=""/>
                                </div>
                                <div class="col-xs-1" style="width: 35px"></div>
                                <div class="col-xs-1 text-right" style="width: 135px">
                                    <label class="control-label">Chq Amount</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="chqAmount" id="chqAmount" type="text" class="form-control money" value=""/>
                                </div>
                            </div>
                        </div><!-- End Row 4--><br>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1" style="width: 135px">
                                    <label class="control-label text-left">Chq Bank</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="chqBank" id="chqBank" type="text" class="form-control money" value=""/>
                                </div>
                                <div class="col-xs-1" style="width: 60px"></div>
                                <div class="col-xs-1" style="width: 130px">
                                    <label class="control-label text-left">Chq Date</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <div class='input-group date'>
                                        <input name="chqDate" id="chqDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                                <div class="col-xs-1" style="width: 55px"></div>
                                <div class="col-xs-1 text-left" style="width: 115px">
                                    <label class="control-label">Chq No</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="chqNo" id="chqNo" type="text" class="form-control money" value=""/>
                                </div>
                            </div>
                        </div><!-- End Row 5-->
                        <div class="col-sm-12"><br></div>
                        <input type="hidden" name="countCredit" id="countCredit" class="form-control" value="1"/>
                        <div class="row" style="padding-left: 15px;width: 100%;">
                            <table class="display" id="CreditTable">
                                <thead class="datatable-header">
                                    <tr>
                                        <th style="width: 10%">Credit Bank</th>
                                        <th style="width: 10%">Credit No</th>
                                        <th style="width: 10%">Credit Expire</th>
                                        <th style="width: 10%">Amount</th>
                                        <th style="width: 10%">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>

                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-sm-12"><br></div>
                        <div class="row text-center" >
                            <div class="col-xs-5 text-right" ></div>                   
                            <div class="col-xs-2 text-left" style="width: 100px;">
                                <button type="submit" id="ButtonSave" name="ButtonSave" onclick="" class="btn btn-success">
                                    <i class="fa fa-save"></i> Save
                                </button>
                            </div> 
                            <div class="col-xs-2 text-right" style="width: 100px;" >
                                <button type="button" id="ButtonNew" name="ButtonNew" class="btn btn-primary">
                                    <i class="fa fa-plus"></i> New             
                                </button>
                            </div>
                            <div class="col-xs-2 text-right"  ></div>
                        </div>
                    </div>
                    </div>
                    </div>                        
                </div>                               
                <input type="hidden" name="action" id="action" value="save"/>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <label class="control-label">Total Receive</lable>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div id='TextBoxesGroup'>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Date</lable>
                                </div>  
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">
                                    <div class="col-sm-12">
                                        <div class='input-group' style="width:165px;">
                                            <input name="InputDateOne" id="InputDateOne" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" readonly=""/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-1" style="width: 80px"></div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:150px;">
                                    <label class="control-label">Cash Amount</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">
                                    <div class="col-sm-12">
                                        <input name="InputCashAmount" id="InputCashAmount" type="text" class="form-control money" value="" readonly=""/>
                                    </div>
                                </div>
                                <div class="col-sm-1" style="width: 80px"></div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Cash(--)</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">                                
                                    <div class="col-sm-12">
                                       <input name="InputCash" id="InputCash" type="text" class="form-control money" value="" readonly=""/>                              
                                    </div>
                                </div>                           
                            </div>   
                        </div>                      
                    </div><!-- End Text 1-->
                    <div id="TextBoxDiv2" >                    
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">                        
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Cheque</lable>
                                </div>  
                                <div class="col-md-2 form-group text-left" style="padding-left:20px;width:200px;">
                                    <input name="InputCheque" id="InputCheque" type="text" class="form-control money"  placeholder="" value="" readonly=""/>
                                </div>
                                <div class="col-sm-1" style="width: 80px"></div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:150px;">
                                    <label class="control-label">Bank Amount</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">
                                    <div class="col-sm-12">
                                        <input name="InputBankAmount" id="InputBankAmount" type="text" class="form-control money" value="" readonly=""/>
                                    </div>
                                </div>
                                <div class="col-sm-1" style="width: 80px"></div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Credit Card</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">                                
                                    <div class="col-sm-12">
                                       <input name="InputCreditCard" id="InputCreditCard" type="text" class="form-control money"  placeholder="" value="" readonly=""/>                               
                                    </div>
                                </div>                                                        
                            </div>   
                        </div><!--End Row 2 -->
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">  
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Detail</lable>
                                </div>  
                                <div class="col-md-2 form-group text-left" style="padding-left:20px;width:390px;">
                                    <textarea class="form-control" rows="3" id="commentDetail" name="commentDetail" readonly=""></textarea>
                                </div>                                                                               
                            </div>   
                        </div>         
                    </div>
                </div>                
            </div>
        </div>
    </div>                        
</div>
</form>                                            
<!--Modal Receive-->
<div class="modal fade" id="ReceiveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Receive</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="SearchReceiveTable">
                    <thead class="datatable-header">
                        <script>
                            var mAccpay = [];
                        </script>
                        <tr>
                            <th style="width: 30%">Code</th>
                            <th style="width: 70%">Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customerAgent" items="${customerAgentInfoList}">
                            <tr onclick ="setupCustomerAgentValue('${customerAgent.billTo}', '${customerAgent.billName}', '${customerAgent.address}')">
                                <td>${customerAgent.billTo}</td>
                                <td>${customerAgent.billName}</td>
                            </tr>
                            <script>
                                mAccpay.push({name: "${customerAgent.billTo}", billTo: "${customerAgent.billName}", address: "${customerAgent.address}"});
                            </script>
                            </tr>    
                        </c:forEach>    
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
                                            
<!--Delete Modal-->
<div class="modal fade" id="delReceiveModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Receipt</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--MCredit Bank List-->
<select class="form-control hidden" name="select_bank_list" id="select_bank_list">
    <c:forEach var="mCreditBank" items="${mCreditBankList}">                                              
        <option  value="${mCreditBank.id}">${mCreditBank.name}</option>
    </c:forEach>
</select>

<script type="text/javascript">
    $(document).ready(function () {
        $("#receive").removeClass('hidden');
        $('.date').datetimepicker();       
        $(".daydatepicker").datetimepicker({
            pickTime: false   
        });
        $('.datemask').mask('0000-00-00');
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        $(".money").mask('000,000,000,000.00', {reverse: true});
               
        $('#SearchReceiveTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });
        
        //Button Search
        $('.datesearch').datetimepicker();
        $('.datesearch').datetimepicker().change(function(){                          
            checkSearch();
        });
        $('#ButtonSearch').ready(function () {
            checkSearch();           
        });
        
        //Auto complete
        var showflag = 1;
        $("#receiveCode").keyup(function(event){ 
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left); 
            if($(this).val() === ""){
                $("#receiveCode").val("");
                $("#receiveName").val("");
                $("#receiveArCode").val("");
            }else{
                if(event.keyCode === 13){
                    searchCustomerAgentAutoList(this.value); 
                }
            }
        });       
        $("#receiveCode").keydown(function(){
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left); 
            if(showflag == 0){
                $(".ui-widget").css("top", -1000);
                showflag=1;
            }
        });
        
        //Validate Field
        $('#InputDatePicker').datetimepicker().on('dp.change', function (e) {
            $('#receiveForm').bootstrapValidator('revalidateField', 'receiveDate');
        });
        $('#receiveForm').bootstrapValidator({
            container: 'tooltip',
            excluded: [':disabled'],
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                receiveDate: {
                    validators: {
                        notEmpty: {
                            message: 'The Date is required'
                        }
                    }
                },
                receiveCode: {
                    validators: {
                        notEmpty: {
                            message: 'The Code is required'
                        }
                    }
                },
                receiveName: {
                    validators: {
                        notEmpty: {
                            message: 'The Name is required'
                        }
                    }
                },
                status: {
                    validators: {
                        notEmpty: {
                            message: 'The Status is required'
                        }
                    }
                },
                receiveAmount: {
                    validators: {
                        notEmpty: {
                            message: 'The Amount is required'
                        }
                    }
                }
            }
        });
                
        //Add row table receive 
//        var rowCreditTable = $("#CreditTable tr").length;
        AddRowCreditTable();
        $("#CreditTable").on("keyup", function () {
            var rowAll = $("#CreditTable tr").length;
            $("td").keyup(function () {
                if ($(this).find("input").val() !== '') {
                    var colIndex = $(this).parent().children().index($(this));
                    var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                    rowAll = $("#CreditTable tr").length;
//                    alert("rowIndex = "+rowIndex);
//                    alert("rowAll = "+rowAll);
                    if (rowIndex === rowAll) {
                        console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                        AddRowCreditTable(parseInt($("#countCredit").val()));
                    }
                    if (rowAll < 2) {
                        $("#tr_ReceiveTableAddRow").removeClass("hide");
                        $("#tr_ReceiveTableAddRow").addClass("show");
                    }
                }
            });
        });
    });
    
    //Auto Complete
    function searchCustomerAgentAutoList(billTo){
        var servletName = 'BillableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&name=' + billTo +
                '&type=' + 'getAutoListBillto';
        CallAjaxAuto(param);
    }    
    function CallAjaxAuto(param){
        var url = 'AJAXServlet';
        var billArray = [];
        var billListId= [];
        var billListName= [];
        var billListAddress= [];
        var billid , billname ,billaddr;
        $("#TaxInvTo").autocomplete("destroy");
        try {
            $.ajax({
               type: "POST",
               url: url,
               cache: false,
               data: param,
               beforeSend: function() {
//                  $("#dataload").removeClass("hidden");    
               },
               success: function(msg) {     
                   var billJson =  JSON.parse(msg);
                   for (var i in billJson){
                       if (billJson.hasOwnProperty(i)){
                           billid = billJson[i].id;
                           billname = billJson[i].name;
                           billaddr = billJson[i].address;
                           billArray.push(billid);
                           billArray.push(billname);
                           billListId.push(billid);
                           billListName.push(billname);
                           billListAddress.push(billaddr);
                       }                 
//                        $("#dataload").addClass("hidden"); 
                   }
//                   $("#InvTo_Id").val(billid);
                   $("#receiveArCode").val(billid);
                   $("#receiveName").val(billname);
//                   $("#InvToAddress").val(billaddr);

                   $("#receiveCode").autocomplete({
                       source: billArray,
                       close: function(){
                            $("#receiveCode").trigger("keyup");
                            var billselect = $("#receiveCode").val();
                            for(var i =0;i<billListId.length;i++){
                                if((billselect==billListName[i])||(billselect==billListId[i])){      
                                   $("#receiveCode").val(billListId[i]);
                                   $("#receiveArCode").val(billListId[i]);
                                   $("#receiveName").val(billListName[i]);
//                                   $("#InvToAddress").val(billListAddress[i]);
                                }                 
                            }   
                       }
                    });

                   var billval = $("#receiveCode").val();
                   for(var i =0;i<billListId.length;i++){
                       if(billval==billListName[i]){
                           $("#receiveCode").val(billListId[i]);
                       }
                   }
                   if(billListId.length == 1){
                       showflag = 0;
                       $("#receiveCode").val(billListId[0]);
                   }
                   var event = jQuery.Event('keydown');
                   event.keyCode = 40;
                   $("#receiveCode").trigger(event);

                }, error: function(msg) {
                   console.log('auto ERROR');
//                   $("#dataload").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }
    
    //Check value for Search
    function checkSearch(){
        if(($("#InputDate").val() === '') || ($("#SelectStatus").val() === '')){
            $("#ButtonSearch").addClass("disabled");
            $("#ButtonPrint").addClass("disabled");
        }else{
            $("#ButtonSearch").removeClass("disabled");
            $("#ButtonPrint").removeClass("disabled");
        }
    }
    
    //Agent List
    function setupCustomerAgentValue(billTo,billName,address){
        document.getElementById('receiveCode').value = billTo;
        document.getElementById('receiveName').value = billName;
        document.getElementById('receiveArCode').value = billTo;        
        $('#receiveForm').bootstrapValidator('revalidateField', 'receiveCode');
        $('#receiveForm').bootstrapValidator('revalidateField', 'receiveName');
        $('#ReceiveModal').modal('hide');
    }
    
    //Add receive data
    function AddReceiveData(){
        if($("#receiveData").hasClass("hidden")){
            $("#receiveData").removeClass("hidden");
        }else{
           $("#receiveData").addClass("hidden");
        }
        $("#receiveDate").val($("#InputDate").val());       
        $("#vatType").val($("#SelectStatus").val());
    }
    
    function reloadDatePicker(){
        try{
           $(".daydatepicker").datetimepicker({
                pickTime: false   
           });  
           $('span').click(function() {
             
                var position = $(this).offset();
                $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
           });
//           $('#CreditTable tbody tr:last td .input-group-addon').click(function() {  
//                AddRowCreditTable(parseInt($("#countCredit").val()));
//           });
           
        }catch(e){
            
        }  
        
        
    }
    
    function AddRowCreditTable(row) {
        if (!row) {
            row = 1;
        }
        $("#CreditTable tbody").append(           
            '<tr>' +
            '<td class="hidden"><input class="form-control" type="text" id="taxDetailId' + row + '" name="taxDetailId' + row + '" value=""></td>' +
            '<td><select class="form-control" name="creditBank' + row + '" id="creditBank' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" ></option></select></td>' +
            '<td><input class="form-control" type="text" id="creditNo' + row + '" name="creditNo' + row + '" value="" onfocusout="checkRefNo(\'' + row + '\')"></td>' +
            '<td>' +
                '<div class="input-group daydatepicker" id="daydatepicker' + row + '">' +
                '<input name="creditExpire' + row + '" id="creditExpire' + row + '" type="text" class="form-control" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />' +
                '<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>' +
                '</div>' + 
            '</td>' +   
            '<td><input class="form-control money" type="text" id="creditAmount' + row + '" name="creditAmount' + row + '" value="" ></td>' +
            '<td>' + 
                '<center>' +
                '<a id="expenButtonRemove' + row + '" name="expenButtonRemove' + row + '" onclick="deleteTaxList(\'\',\'' + row + '\')"  data-toggle="modal" data-target="#DeleteExpenModal">' + 
                '<span id="expenSpanEdit' + row + '" name="expenSpanEdit' + row + '" class="glyphicon glyphicon-remove deleteicon"></span>' +
                '</a>' + 
                '</center>' +
            '</td>' +
            '</tr>'           
        );
//        $("#tr_TaxInvoiceDetailAddRow").removeClass("show");
//        $("#tr_TaxInvoiceDetailAddRow").addClass("hide");
        $("#select_bank_list option").clone().appendTo("#creditBank" + row);
        $("#countCredit").val(row+1);
        reloadDatePicker();
    }
    
    function AddrowBySelect(row){
        var count =  parseInt($("#countCredit").val());
        row = parseInt(row);
        if(row === (count-1)){
           AddRowCreditTable(count); 
        }       
    }
    
    function searchReceive(){
        $("#action").val("search");
        $("#receiveForm").submit();
    }
</script>
<script type="text/javascript">
$(document).ready(function(){
    $("#addButton").click(function () {
        $('#TextBoxDiv2').show();
    });
    $("#removeButton").click(function () {
        $('#TextBoxDiv2').hide();
    });
  });
</script>
