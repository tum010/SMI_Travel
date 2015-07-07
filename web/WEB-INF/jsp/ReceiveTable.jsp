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
            </div>
        </div>
                <div class="panel panel-default">
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
    });
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
