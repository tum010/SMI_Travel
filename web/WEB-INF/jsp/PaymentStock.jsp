<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/PaymentStock.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<section class="content-header" >
    <h1>
        Checking - Payment Stock
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Checking</a></li>          
        <li class="active"><a href="#">Payment Stock</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;padding-right: 0px;" ng-app=""> 
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/Checking/CheckingOutboundMenu.html'"></div>
    </div>
    <!--Content -->
    <div class="col-sm-10">
        <div id="fail"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Stock is already add!</strong> 
        </div>
        <div class="row" style="padding-left: 0px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Payment Stock</b></h4>
            </div>
        </div>
        <hr/>
        <div class="panel panel-default ">
            <div class="panel-heading ">
                <h4 class="panel-title">Payment Stock</h4>
            </div>
            <div class="panel-body"  style="padding-right: 0px;">                                                              
                <div class="row" style="padding-left: 15px;">             
                    <div class="col-xs-1 text-right" style="width:60px;padding-right: 0px;padding-left: 0px;">
                        <label class="control-label">Pay No</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="width:165px;"> 
                        <div class="input-group">
                            <input type="hidden" class="form-control" id="payId" name="payId" value="" />
                            <input type="text" class="form-control" id="payNo" name="payNo" value="" />
                        </div>
                    </div>
                    <div class="col-xs-1 "  style="width: 100px">
                        <button type="button"  id="btnSearch"  name="btnSearch" onclick="" class="btn btn-primary">
                            <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                        </button> 
                    </div>
                </div>            
            </div>
        </div>
        <div class="panel panel-default ">
            <div class="panel-heading ">
                <h4 class="panel-title">Stock List</h4>
            </div>
            <div class="panel-body"  style="padding-right: 0px;">                                                 
                <div class="row hidden" style="padding-left: 0px">
                    <div class="col-xs-1 text-right" style="width:60px;padding-right: 0px;padding-left: 0px;">
                        <label class="control-label">Stock</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="width:165px;"> 
                        <div class="input-group">
                            <input type="hidden" class="form-control" id="stockId" name="stockId" value="" />
                            <input type="text" class="form-control" id="stockCode" name="stockCode" value="" />
                            <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchStock">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                        </div>
                    </div>
                    <div class="col-md-2 form-group text-left" style="width:210px;padding-left: 0px;">
                        <input name="stockName" id="stockName" type="text" class="form-control" value="" readonly=""/>
                    </div>
                    <div class="col-xs-1 text-right" style="width:150px;">
                        <label class="control-label">Effective From</lable>
                    </div>
                    <div class="col-md-1 form-group text-left" style="padding-left:0px;padding-right: 0px;width: 140px;">
                        <div class='input-group date' >
                            <input name="effectiveFrom" id="effectiveFrom" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" readonly=""/>
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div> 
                    </div>
                    <div class="col-xs-1 text-right" style="width:150px;">
                        <label class="control-label">Effective To</lable>
                    </div>
                    <div class="col-md-1 form-group text-left" style="padding-left:0px;padding-right: 0px;width: 140px;">
                        <div class='input-group date' >
                            <input name="effectiveTo" id="effectiveTo" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" readonly=""/>
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div><!--End row 1-->
                <div class="row" style="padding-left: 0px">
                    <div class="col-xs-1 text-right"  style="width: 1030px">
                        <button type="button"  id="btnSearch"  name="btnSearch" onclick="searchStock()" class="btn btn-primary">
                            <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                        </button> 
                    </div>
<!--                    <div class="col-xs-1 text-right"  style="padding-left: 0px">
                        <button type="button"  id="btnAdd"  name="btnAdd" onclick="" class="btn btn-success">
                            <span id="SpanSearch" class="glyphicon glyphicon-plus"></span> Add
                        </button> 
                    </div>                   -->
                </div>
                <br>
                <div class="row" style="padding-left: 15px;">             
                    <div class="row">
                        <div class="col-xs-11" style="width: 1030px">
                            <input type="hidden" id="noStockTable" name="noStockTable" value="1"/>
                            <table class="display" id="StockTable">
                                <thead>
                                    <tr class="datatable-header">
                                        <th style="width: 5%">No</th>                                   
                                        <th style="width: 30%">Product Name</th>
                                        <th style="width: 10%">Staff</th>
                                        <th style="width: 15%">Add Date</th>
                                        <th style="width: 15%">Effective From</th>
                                        <th style="width: 15%">Effective To</th>                                                                      
                                        <th style="width: 10%" >Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                </tbody>
                            </table>
                        </div>   
                    </div>
                </div>            
            </div>
        </div>
        <div class="panel panel-default ">
            <div class="panel-heading ">
                <h4 class="panel-title">Detail</h4>
            </div>
            <div class="panel-body"  style="padding-right: 0px;">                                                              
                <div class="row" style="padding-left: 15px;">             
                    <div class="row">
                        <div class="col-xs-11" style="width: 1030px">
                            <input type="hidden" id="noStockDetailTable" name="noStockDetailTable" value="1"/>
                            <table class="display" id="StockDetailTable">
                                <thead>
                                    <tr class="datatable-header">
                                        <th style="width: 5%">No</th>                                   
                                        <th style="width: 35%">Code</th>
                                        <th style="width: 25%">Type</th>
                                        <th style="width: 11%">Ref No</th>
                                        <th style="width: 11%">Pick Up</th>
                                        <th style="width: 11%">Pick Date</th>                                                                      
                                        <th style="width: 3%" >Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                </tbody>
                            </table>
                        </div>   
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <button type="button" id="btnSave" name="btnSave" class="btn btn-success">
                                <i class="fa fa-save"></i> Save             
                            </button>
                        </div>
                    </div>    
                </div>            
            </div>
        </div>
    </div>
</div>
<!--Search Stock-->
<div class="modal fade" id="SearchStock" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content ">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Search Stock</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="SearchStockTable">
                    <thead>
                        <tr class="datatable-header">
                            <th style="width: 1px">No</th>                                   
                            <th style="width: 5px">Product Name</th>
                            <th style="width: 1px">Staff</th>
                            <th style="width: 2px">Add Date</th>
                            <th style="width: 2px">Effective From</th>
                            <th style="width: 2px">Effective To</th>                                                                      
                            <th style="width: 1px" >Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr id="data1">
                            <td class="text-center ">1</td>
                            <td>TEST0001</td>
                            <td>PJ</td>
                            <td>2015-11-19</td>
                            <td>2015-01-01</td>
                            <td>2015-01-31</td>
                            <td class="text-center ">
                                <a id="" href="#">
                                    <span id="SpanGlyphiconEdit1" class="glyphicon glyphicon-plus" onclick="createStockDetail('1','TEST0001','PJ','2015-11-19','2015-01-01','2015-01-31')"></span>
                                </a>
                            </td>
                        </tr>
                        <tr id="data2" >
                            <td class="text-center ">2</td>
                            <td>TEST0002</td>
                            <td>PJ</td>
                            <td>2015-12-19</td>
                            <td>2015-04-01</td>
                            <td>2015-04-30</td>
                            <td class="text-center ">
                                <a id="" href="#" class="">
                                    <span id="SpanGlyphiconEdit1" class="glyphicon glyphicon-plus " onclick="createStockDetail('2','TEST0002','PJ','2015-12-19','2015-04-01','2015-04-30')"></span>
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="SearchAPCodeOK" name="SearchAPCodeOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchAPCodeClose" name="SearchAPCodeClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->

