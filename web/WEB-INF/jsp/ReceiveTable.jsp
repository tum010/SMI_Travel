<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/ReceiveTable.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="content-header" >
    <h1>
        Finance & Cashier - Receive Table
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Receive Table</a></li>
    </ol>
</section>
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
                                <div class='input-group date' style="width:140px;">
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
                                <select name="SelectStatus" id="SelectStatus" class="form-control">
                                    <option id="" value="">---select--</option>
                                    <option id="" value="">---status1--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-2 text-left" style="width: 100px;">
                            <a id="ButtonSearch" name="ButtonSearch" onclick="" class="btn btn-primary">
                                <i class="glyphicon glyphicon-search"></i> Search
                            </a>
                        </div>
                        <div class="col-xs-2 text-right" style="width: 490px;">
                            <a id="ButtonAdd" name="ButtonAdd" class="btn btn-success" onclick="">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                    </div>   
                </div><!-- End Row 1--><br>
                <div class="row" style="padding-left: 15px;width: 100%;">
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
                                        <span id="removeSpan${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteAir('${table.id}', '${table.code}')" data-toggle="modal" data-target="#delReceiveModal"></span>
                                    </a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <br>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1" style="width: 130px">
                                    <label class="control-label text-left">Receive Date</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 170px">
                                    <div class='input-group date'>
                                        <input name="receiveDate" id="receiveDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </div>   
                        </div><!-- End Row 1--><br>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1" style="width: 130px">
                                    <label class="control-label text-left">Receive Name</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 170px">
                                    <input name="receiveCode" id="receiveCode" type="text" class="form-control" value="" />
                                </div>
                                <div class="col-xs-1" style="width: 420px">
                                    <input name="receiveName" id="receiveName" type="text" class="form-control" value="" />
                                </div>
                                <div class="col-xs-1 text-right" style="width: 175px">
                                    <label class="control-label">AR Code</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="receiveArCode" id="receiveArCode" type="text" class="form-control" value="" readonly=""/>
                                </div>
                            </div>
                        </div><!-- End Row 2--><br>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1" style="width: 130px">
                                    <label class="control-label text-left">Description</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 590px">
                                    <textarea name="description" id="description" class="form-control" rows="3"></textarea>
                                </div>
                                <div class="col-xs-1 text-right" style="width: 175px">
                                    <label class="control-label">Status</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <select name="status" id="status" class="form-control">
                                        <option value=""></option>
                                    </select>    
                                </div>
                            </div>
                        </div><!-- End Row 3--><br>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1" style="width: 130px">
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
                                <div class="col-xs-1" style="width: 40px"></div>
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
                                <div class="col-xs-1" style="width: 130px">
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
                                <div class="col-xs-1" style="width: 60px"></div>
                                <div class="col-xs-1 text-left" style="width: 115px">
                                    <label class="control-label">Chq No</lable>        
                                </div>
                                <div class="col-xs-1" style="width: 200px">
                                    <input name="chqNo" id="chqNo" type="text" class="form-control money" value=""/>
                                </div>
                            </div>
                        </div><!-- End Row 5--><br>
                        <input name="countCredit" id="countCredit" class="form-control" value="1"/>
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
<!--                                        <td>26/07/2015</td>
                                        <td>26/07/2015</td>
                                        <td>26/07/2015</td>
                                        <td>26/07/2015</td>
                                        <td class="text-center">
                                            <a href="#" onclick=""  data-toggle="modal" data-target="">
                                                <span id="removeSpan${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteAir('${table.id}', '${table.code}')" data-toggle="modal" data-target="#delReceiveModal"></span>
                                            </a>
                                        </td>-->
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>                    
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <label class="control-label">Total Receive</lable>
            </div>
            <div class="panel-body">
                <div class="row" style="padding-left: 15px;width: 100%;">
                    <div id='TextBoxesGroup'>
                        <div id="TextBoxDiv1">                    
                            <br>                     
                            <div class="row" style="padding-left: 0px">
                                <div class="col-xs-12 "> 
                                    <div class="col-xs-1" style="width: 110px ;">
                                       <label class="control-label text-center">Credit Bank</lable>                                         
                                    </div>
                                    <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right: 0px;">                                                             
                                        <select name="SelectCreditBank" id="SelectCreditBank" class="form-control" style="width:130px;">
                                            <option value="">--Select--</option>
                                            <option value="">--Select--</option>
                                        </select>
                                    </div>
                                    <div class="col-xs-1" style="width: 80px ;padding-left: 0px;padding-right:0px;">
                                       <label class="control-label text-center">Credit No</lable>                                         
                                    </div>
                                    <div class="col-md-2 form-group text-left" style="padding-left:5px;width:150px;">
                                        <div class="col-sm-12">
                                            <input name="InputCreditNo" id="InputCreditNo" type="text" class="form-control" value="" />
                                        </div>
                                    </div>
                                    <div class="col-xs-1" style="width: 120px ;padding-left: 0px;padding-right: 0px;">
                                       <label class="control-label text-center">Credit Expire</lable>                                         
                                    </div>
                                    <div class="col-md-2 form-group text-left" style="padding-left:0px;width:140px;padding-right: 0px;">
                                        <div class="col-sm-12">
                                           <input name="InputCreditExpire" id="InputCreditExpire" type="text" class="form-control"  placeholder="" value="" />                               
                                        </div>
                                    </div>
                                    <div class="col-xs-1" style="width: 80px ;">
                                       <label class="control-label text-center">Amount</lable>                                         
                                    </div>
                                    <div class="col-md-2 form-group text-left" style="padding-left:5px;width:150px;">
                                        <div class="col-sm-12">
                                            <input name="InputAmount" id="InputAmount" type="text" class="form-control"  placeholder="" value="" /> 
                                        </div>
                                    </div> 
                                    <h4>
                                        <a class="col-xs-1 text-right">
                                           <span class="glyphicon glyphicon-plus-sign" id="addButton" name="addButton"></span>
                                        </a>
                                    </h4>
                                </div>
                                
                            </div>                      
                        </div><!-- End Text 1-->
                        <div id="TextBoxDiv2" style="display: none;">                    
                                <br>                     
                            <div class="row" style="padding-left: 0px">
                                <div class="col-xs-12 "> 
                                    <div class="col-xs-1" style="width: 110px ;">
                                       <label class="control-label text-center">Credit Bank</lable>                                         
                                    </div>
                                    <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right: 0px;">                                                             
                                        <select name="SelectCreditBank1" id="SelectCreditBank1" class="form-control" style="width:130px;">
                                            <option value="">--Select--</option>
                                            <option value="">--Select--</option>
                                        </select>
                                    </div>
                                    <div class="col-xs-1" style="width: 80px ;padding-left: 0px;padding-right:0px;">
                                       <label class="control-label text-center">Credit No</lable>                                         
                                    </div>
                                    <div class="col-md-2 form-group text-left" style="padding-left:5px;width:150px;">
                                        <div class="col-sm-12">
                                            <input name="InputCreditNo1" id="InputCreditNo1" type="text" class="form-control" value="" />
                                        </div>
                                    </div>
                                    <div class="col-xs-1" style="width: 120px ;padding-left: 0px;padding-right: 0px;">
                                       <label class="control-label text-center">Credit Expire</lable>                                         
                                    </div>
                                    <div class="col-md-2 form-group text-left" style="padding-left:0px;width:140px;padding-right: 0px;">
                                        <div class="col-sm-12">
                                           <input name="InputCreditExpire1" id="InputCreditExpire1" type="text" class="form-control"  placeholder="" value="" />                               
                                        </div>
                                    </div>
                                    <div class="col-xs-1" style="width: 80px ;">
                                       <label class="control-label text-center">Amount</lable>                                         
                                    </div>
                                    <div class="col-md-2 form-group text-left" style="padding-left:5px;width:150px;">
                                        <div class="col-sm-12">
                                            <input name="InputAmount1" id="InputAmount1" type="text" class="form-control"  placeholder="" value="" /> 
                                        </div>
                                    </div>
                                    <h4>
                                        <a class="col-xs-1 text-right">
                                           <span class="glyphicon glyphicon-remove deleteicon" id="removeButton" id="removeButton"></span>
                                        </a>
                                    </h4>
                                </div>   
                            </div>                
                        </div> 
                    </div>
                </div><!--End Content 3 -->
                <div class="row">
                    <div id='TextBoxesGroup'>
                        <div class="row" style="padding-left: 0px">
                            <div class="col-xs-12 ">
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Date</lable>
                                </div>  
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">
                                    <div class="col-sm-12">
                                        <div class='input-group date' style="width:140px;">
                                            <input name="InputDateOne" id="InputDateOne" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:150px;">
                                    <label class="control-label">Cash Amount</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">
                                    <div class="col-sm-12">
                                        <input name="InputCashAmount" id="InputCashAmount" type="text" class="form-control" value="" />
                                    </div>
                                </div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Cash(--)</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">                                
                                    <div class="col-sm-12">
                                       <input name="InputCash" id="InputCash" type="text" class="form-control" value="" />                              
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
                                    <input name="InputCheque" id="InputCheque" type="text" class="form-control"  placeholder="" value="" />
                                </div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:150px;">
                                    <label class="control-label">Bank Amount</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">
                                    <div class="col-sm-12">
                                        <input name="InputBankAmount" id="InputBankAmount" type="text" class="form-control" value="" />
                                    </div>
                                </div>
                                <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                                    <label class="control-label">Credit Card</lable>
                                </div> 
                                <div class="col-md-2 form-group text-left" style="padding-left:5px;width:200px;">                                
                                    <div class="col-sm-12">
                                       <input name="InputCreditCard" id="InputCreditCard" type="text" class="form-control"  placeholder="" value="" />                               
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
                                    <textarea class="form-control" rows="3" id="commentDetail" name="commentDetail"></textarea>
                                </div>                                                                               
                            </div>   
                        </div>         
                    </div>
                </div>
                <div class="row text-center" >
                    <div class="col-xs-4 text-right" ></div>
                    <div class="col-xs-2 text-right" style="width: 100px;" >
                        <button type="submit" id="ButtonPrint" name="ButtonPrint" class="btn btn-default">
                            <i class="fa fa-print"></i> Print             
                        </button>
                    </div>
                    <div class="col-xs-2 text-left" style="width: 100px;">
                        <a id="ButtonSave" name="ButtonSave" onclick="" class="btn btn-success">
                            <i class="fa fa-save"></i> Save
                        </a>
                    </div> 
                    <div class="col-xs-2 text-right" style="width: 100px;" >
                        <button type="submit" id="ButtonNew" name="ButtonNew" class="btn btn-primary">
                            <i class="fa fa-plus"></i> New             
                        </button>
                    </div>
                    <div class="col-xs-2 text-right"  ></div>
                </div>
            </div>
        </div>
    </div>                        
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
<script type="text/javascript">
    $(document).ready(function () {
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        $(".money").mask('000,000,000,000.00', {reverse: true});
        
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
    
    function AddRowCreditTable(row) {
        if (!row) {
            row = 1;
        }
        $("#CreditTable tbody").append(           
            '<tr>' +
            '<td class="hidden"><input class="form-control" type="text" id="taxDetailId' + row + '" name="taxDetailId' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailId' + row + '" name="invoiceDetailId' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailCost' + row + '" name="invoiceDetailCost' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="invoiceDetailAmount' + row + '" name="invoiceDetailAmount' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="isExport' + row + '" name="isExport' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="exportDate' + row + '" name="exportDate' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="isProfit' + row + '" name="isProfit' + row + '" value=""></td>' +
            '<td><select class="form-control" name="product' + row + '" id="product' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
            '<td><input class="form-control" type="text" id="refNo' + row + '" name="refNo' + row + '" value="" onfocusout="checkRefNo(\'' + row + '\')"></td>' +
            '<td><input class="form-control" type="text" id="description' + row + '" name="description' + row + '" value=""></td>' +
            '<td><input class="form-control numerical" style="text-align:right;" type="text" id="cost' + row + '" name="cost' + row +'" value="" onfocusout="CalculateAmountTotal()" onkeyup="insertCommas(this)"></td>' +
            '<td><select class="form-control" name="currencyCost' + row + '" id="currencyCost' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
            '<td align="center"><input type="checkbox" id="isVat' + row + '" name="isVat' + row + '" value="1" onclick="CalculateGross(\'' + row + '\')" checked></td>' +
            '<td align="right" id="vatShow' + row + '"></td>' +
            '<td><input class="form-control numerical" style="text-align:right;" type="text" id="gross' + row + '" name="gross' + row + '" value="0.00" readonly=""></td>' +
            '<td><input class="form-control numerical" style="text-align:right;" type="text" id="amount' + row + '" name="amount' + row + '" value="" onfocusout="CalculateAmountTotal(\'' + row + '\')" onkeyup="insertCommas(this)"></td>' +
            '<td><select class="form-control" name="currencyAmount' + row + '" id="currencyAmount' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" >---------</option></select></td>' +
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
        $("#countCredit").val(row+1);      
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
