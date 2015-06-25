<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/PaymentTourHotel.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="content-header" >
    <h1>
        Checking - Package Tour/Hotel
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Checking</a></li>          
        <li class="active"><a href="#">Package Tour/Hotel</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/Checking/CheckingPackageTourHotel.html'"></div>
    </div>
    <!--Content -->
    <div class="col-sm-10">
        <!--Row 1 -->
        <div class="row" style="padding-left: 15px">
            <div class="col-xs-12 ">
                <div class="col-xs-1 text-right">
                    <label class="control-label">Pay No:</lable>
                </div>
                <div class="col-md-2 form-group text-left">
                    <div class="col-sm-12">
                        <input name="InputPayNo" id="InputPayNo" type="text" class="form-control" value="" />
                    </div>
                </div>
                <div class="col-xs-2 text-right">
                    <label class="control-label">Pay Date:</lable>
                </div>
                <div class="col-md-3 form-group text-left" style="padding-left:5px">
                    <div class="col-sm-12">
                        <div class='input-group date' style="width:140px;">
                            <input name="InputPayDate" id="InputPayDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
                <div class="col-xs-1 text-right" style="padding-left:0px;padding-right:0px;">
                    <label class="control-label">Account:</lable>
                </div>
                <div class="col-md-3  text-left" style="padding-top : 5px;padding-left:0px;padding-right:0px;">
                    <div class="col-sm-6" text-left>
                        <input type="radio" name="account1"  id="account1" value="account1" checked /> &nbsp;account(1)
                    </div>
                    <div class="col-sm-6" text-left>
                        <input type="radio" name="account1"  id="account1" value="account2" />&nbsp;account(2)
                    </div>
                </div>
            </div>
        </div>
        <!--Row 2 -->
        <div class="row" style="padding-left: 15px">
            <div class="col-xs-12 ">
                <div class="col-xs-1 text-right">
                    <label class="control-label">PV No:</lable>
                </div>
                <div class="col-md-2 form-group text-left">
                    <div class="col-sm-12">
                        <input name="InputPvNo" id="InputPvNo" type="text" class="form-control" value="" />
                    </div>
                </div>
                <div class="col-xs-2 text-right" style="padding-left:0px;padding-right:20px;">
                    <label class="control-label">PV Type:</lable>
                </div>
                <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right:0px;">
                    <div class="col-sm-12">
                        <select name="SelectPvType" id="SelectPvType" class="form-control">
                            <option id="" value="">--</option>
                            <option id="" value="">---type--</option>
                        </select>
                    </div>
                </div>
                <div class="col-xs-2 text-right" style="padding-left:0px;">
                    <label class="control-label">Department:</lable>
                </div>
                <div class="col-md-3  text-left" style="padding-top:5px;padding-left:0px;padding-right:0px;">
                    <div class="col-sm-6 text-left" style="padding-left:2px;" >
                        <input type="radio" name="Wendy"  id="Wendy" value="Wendy" checked />&nbsp;Wendy
                    </div>
                    <div class="col-sm-6 text-left"  style="padding-left:2px;">
                        <input type="radio" name="Outbound"  id="Outbound" value="Outbound" /> Outbound
                    </div>
                </div>
            </div>
        </div>
        <!--Row 3 -->
        <div class="row" >
            <div class="col-xs-2 text-right" style="padding-left:10px;padding-right:0px;width:100px;">
                    <label class="control-label">Invoice Sup:</lable>
            </div>
            <div class="col-md-2 form-group text-right" style="padding-left:30px;padding-right:0px;"> 
                <div class="col-sm-12">
                    <div class="input-group" id="CodeValidate">
                        <input name="InputInvoiceSupCode" id="InputInvoiceSupCode" type="text" class="form-control" value="" />
                        <span class="input-group-addon" data-toggle="modal" data-target="#InvoiceSupModal">
                            <span class="glyphicon-search glyphicon"></span>
                        </span>    
                    </div>    
                </div>   
            </div>
            <div class="col-md-4 form-group text-left" style="width:360px;">
                <div class="col-sm-12">
                    <input name="InputInvoiceSupName" id="InputInvoiceSupName" type="text" class="form-control" value="" />           
                </div>
            </div>
            <div class="col-xs-2 text-right" style="padding-left:10px;padding-right:0px;width:140px;">
                <label class="control-label">Status:</lable>
            </div>
            <div class="col-md-2 form-group text-left">
                <div class="col-sm-12">
                    <select name="status" id="status" class="form-control">
                        <option id="" value="">--</option>
                        <option id="" value="">---status--</option>
                    </select>           
                </div>
            </div>
        </div>
        <!--Row 4 -->
        <div class="row" >
            <div class="col-xs-2 text-right" style="padding-left:10px;padding-right:0px;width:100px;">
                    <label class="control-label">Detail:</lable>
            </div>
            <div class="col-md-6 form-group text-left" style="padding-left:30px;padding-right:0px;width:520px;">
                <div class="col-sm-12">
                    <textarea rows="3" cols="60" class="form-control">
                      
                    </textarea>
                </div>   
            </div>
            <div class="col-xs-2 text-right" style="padding-left:10px;padding-right:0px;width:155px;">
                <label class="control-label">Payment:</lable>
            </div>
            <div class="col-md-2 form-group text-left">
                <div class="col-sm-12">
                    <select name="status" id="status" class="form-control">
                        <option id="" value="credit">credit</option>
                        <option id="" value="cash">cash</option>
                        <option id="" value="card">card</option>
                    </select>           
                </div>
            </div>
        </div>
        <!-- Table -->
        <div class="panel panel-default">                    
            <div class="panel-body">
                <table class="display" id="PaymentHotelTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width: 12%">Product</th>
                            <th style="width: 10%">Ref No</th>
                            <th style="width: 10%">Invoice No</th>
                            <th style="width: 10%">Amount</th>
                            <th style="width: 12%">Type</th>
                            <th style="width: 8%">Currency</th>
                            <th style="width: 30%">Description</th>
                            <th style="width: 8%">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Hotel</td>
                            <td>344555</td>
                            <td>1222</td>
                            <td>5,000</td>
                            <td>test</td>
                            <td>THB</td>
                            <td>test description</td>
                            <td class="text-center">
                                <a href="#" onclick=""  data-toggle="modal" data-target="">
                                    <span id="" class="glyphicon glyphicon-remove deleteicon"></span>
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div><!--End Table -->
        <!-- Table Content -->
        <div class="panel panel-default">                    
            <div class="panel-body">
                <!--Row 1.1 -->
                <div class="row" >
                    <div class="col-xs-2 text-right">
                            <label class="control-label">Remark:</lable>
                    </div>
                    <div class="col-md-10 form-group text-left">
                        <div class="col-sm-12">
                            <input name="InputRemark" id="InputRemark" type="text" class="form-control" value="" />           
                        </div>   
                    </div>
                </div>
                <!--Row 1.2 -->
                <div class="row" >
                    <div class="col-xs-2 text-right">
                            <label class="control-label">Cash:</lable>
                    </div>
                    <div class="col-md-2 form-group text-left">
                        <div class="col-sm-12">
                            <input name="InputCash" id="InputCash" type="text" class="form-control" value="" />           
                        </div>   
                    </div>
                </div>
                <!--Row 1.3 -->
                <div class="row" >
                    <div class="col-xs-2 text-right">
                            <label class="control-label">Chq No:</lable>
                    </div>
                    <div class="col-md-2 form-group text-left">
                        <div class="col-sm-12">
                            <input name="InputChqNo" id="InputChqNo" type="text" class="form-control" value="" />           
                        </div>   
                    </div>
                    <div class="col-xs-2 text-right">
                            <label class="control-label">Grand  Total:</lable>
                    </div>
                    <div class="col-md-2 form-group text-left">
                        <div class="col-sm-12">
                            <input name="InputGrandTotal" id="InputGrandTotal" type="text" class="form-control" value="" />           
                        </div>   
                    </div>
                </div>
                <!--Row 1.4 -->
                <div class="row" >
                    <div class="col-xs-2 text-right">
                            <label class="control-label">Chq Amount:</lable>
                    </div>
                    <div class="col-md-2 form-group text-left">
                        <div class="col-sm-12">
                            <input name="InputChqAmount" id="InputChqAmount" type="text" class="form-control" value="" />           
                        </div>   
                    </div>
                </div>
            </div>
        </div><!--End Table Content -->
        <!--Button -->
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
<script type="text/javascript">
    $(document).ready(function () {
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
    });
</script>
