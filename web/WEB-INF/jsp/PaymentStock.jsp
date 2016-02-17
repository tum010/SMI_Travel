<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/PaymentStock.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>

<c:set var="paymentStock" value="${requestScope['PaymentStock']}" /> 
<c:set var="PaymentStockDetailList" value="${requestScope['PaymentStockDetailList']}" />
<c:set var="PaymentStockItemList" value="${requestScope['PaymentStockItemList']}" /> 
<c:set var="stockList" value="${requestScope['StockList']}" />
<c:set var="currencyList" value="${requestScope['currencyList']}" />
<c:set var="StockDetailList" value="${requestScope['StockDetailList']}" /> 
<c:set var="PaymentStockTemp" value="${requestScope['PaymentStockTemp']}" /> 
<!--testtt-->
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
    
    <div class="col-sm-10">
        <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Success!</strong> 
        </div>
        <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Unsuccess!</strong> 
        </div>
        <div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Delete Success!</strong> 
        </div>
        <div id="textAlertDivNotDelete"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Delete Unsuccess!</strong> 
        </div>       
        <div id="textAlertPayNo"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Pay No. not available!</strong> 
        </div>
        <div id="fail"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Stock is already add!</strong> 
        </div>
    </div> 
    
    <!--Content -->
    <div class="col-sm-10">
        <div class="row" style="padding-left: 0px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Payment Stock</b></h4>
            </div>
        </div>
        <hr/>
        <form action="PaymentStock.smi" method="post" id="PaymentStockForm" name="PaymentStockForm" role="form">
            <input type="hidden" class="form-control" id="hideCollapseCheck" name="hideCollapseCheck" value="" />
            <input type="hidden" class="form-control" id="action" name="action" value="" />
            <input type="hidden" class="form-control" id="paymentStockDetailIdDelete" name="paymentStockDetailIdDelete" value="" />
            <input type="hidden" class="form-control" id="paymentStockRowDelete" name="paymentStockRowDelete" value="" />
            <input type="hidden" class="form-control" id="page" name="page" value="" />
            <input type="hidden" class="form-control" id="createBy" name="createBy" value="${paymentStock.createBy}" />
            <input type="hidden" class="form-control" id="createDate" name="createDate" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['createDate']}" />
            
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
                                <input type="hidden" class="form-control" id="payId" name="payId" value="${paymentStock.id}" />
                                <input type="text" class="form-control" id="payNo" name="payNo" value="${paymentStock.payStockNo}" />
                            </div>
                        </div>
                        <div class="col-xs-1 "  style="width: 100px">
                            <button type="submit"  id="SearchPayNoAction"  name="SearchPayNoAction" onclick="searchPaymentNoStock()" class="btn btn-primary">
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
                                <input type="hidden" id="noStockTableTemp" name="noStockTableTemp" value="${requestScope['noStockTable']}"/>
                                <input type="hidden" id="noStockTable" name="noStockTable" value="${requestScope['noStockTable']}"/>
                                <table class="display" id="StockTable">
                                    <thead>
                                        <tr class="datatable-header">
                                            <th style="width: 5%">No</th>                                   
                                            <th style="width: 30%">Product Name</th>
                                            <th style="width: 5%">Staff</th>
                                            <th style="width: 10%">Add Date</th>
                                            <th style="width: 10%">Effective From</th>
                                            <th style="width: 10%">Effective To</th> 
                                            <th style="width: 12%">Cost</th> 
                                            <th style="width: 12%">Sale</th> 
                                            <th style="width: 10%" >Action</th>
                                        </tr>
                                    </thead>
                                    <script>
                                        paymentstockdetail = [];
                                    </script>
                                    <tbody>
                                        <c:forEach var="table" items="${PaymentStockDetailList}" varStatus="i">
                                            <tr>
                                                <input type="hidden" id="paymentStockDetailId${i.count}" name="paymentStockDetailId${i.count}"  value="${table.id}"> 
                                                <input type="hidden" id="paymentStockId${i.count}" name="paymentStockId${i.count}"  value="${table.paymentStock.id}"> 
                                                <input type="hidden" id="stockId${i.count}" name="stockId${i.count}"  value="${table.stock.id}"> 
                                                <td align="center"><div id="runningNo${i.count}" value="${i.count}"></div></td>
                                                <td align="left">${table.stock.product.name}</td>
                                                <td align="left">${table.stock.staff.username}</td>
                                                <td align="center">${table.stock.createDate}</td>
                                                <td align="center">${table.stock.effectiveFrom}</td>
                                                <td align="center">${table.stock.effectiveTo}</td>
                                                <td align="right"><input type="text" id="totalcostamount${i.count}" name="totalcostamount${i.count}" class="form-control money" value="${table.cost}" readonly=""></td>
                                                <td align="right"><input type="text" id="totalsaleamount${i.count}" name="totalsaleamount${i.count}" class="form-control money" value="${table.sale}" readonly=""></td>
                                                <td class="text-center ">
                                                    <a id="ButtonEdit${i.count}" onclick="getPaymentStockItemCostSale('${table.id}','${table.stock.id}','${table.stock.product.name}','${i.count}');hideCollapse(${i.count});" data-target="#payStockDetail" >
                                                        <span id="SpanEdit${i.count}" class="glyphicon glyphicon glyphicon-list-alt"></span>
                                                    </a>
                                                    <a href="#" onclick="" data-toggle="modal" data-target=""> <span id="SpanRemove" class="glyphicon glyphicon-remove deleteicon" onclick="deletePaymentStockDetailList('${table.id}','${i.count}','');"></span></a>
                                                </td>  
                                            </tr>
                                            <script>
                                                paymentstockdetail.push({
                                                    psdId: "${table.id}",
                                                    stockid: "${table.stock.id}",
                                                    productname: "${table.stock.product.name}",
                                                    noStockTable: "${i.count}",
                                                    psId: "${table.paymentStock.id}"
                                                });
                                            </script>
                                        </c:forEach>
                                            
                                    </tbody>
                                </table>
                            </div>   
                        </div>
                    </div>  
                                
                                
                <div class="col-xs-1 text-right" style="width: 130px;padding-top: 8px">
                    <label class="control-label text-right">Total Cost</label>
                </div>
                <div class="col-xs-1" style="width: 200px;padding-top: 8px">
                    <input type="text" class="form-control money text-right" id="totalCostAll" name="totalCostAll" value="${paymentStock.costAmount}" readonly=""/>
                </div>
                <div class="col-xs-1" style="width: 100px;padding-top: 8px">
                    <select class="form-control" name="curCost" id="curCost" >
                        <option  value="" >---------</option>
                        <c:forEach var="curCost" items="${currencyList}" varStatus="status">                                       
                            <c:set var="select" value="" />
                            <c:if test="${curCost.code == paymentStock.curCost}">
                                <c:set var="select" value="selected" />
                            </c:if>
                            <option  value="${curCost.code}" ${select} >${curCost.code}</option>
                        </c:forEach>
                    </select>
                </div> 
                <div class="col-xs-1 text-right" style="width: 130px;padding-top: 8px">
                    <label class="control-label text-right">Total Sale</label>
                </div>
                <div class="col-xs-1" style="width: 200px;padding-top: 8px">
                    <input type="text" class="form-control money text-right" id="totalSaleAll" name="totalSaleAll" value="${paymentStock.saleAmount}" readonly=""/>
                </div>
                 <div class="col-xs-1" style="width: 100px;padding-top: 8px">
                    <select class="form-control" name="curSale" id="curSale" >
                        <option  value="" >---------</option>
                        <c:forEach var="curSale" items="${currencyList}" varStatus="status">                                       
                            <c:set var="select" value="" />
                            <c:if test="${curSale.code == paymentStock.curSale}">
                                <c:set var="select" value="selected" />
                            </c:if>
                            <option  value="${curSale.code}" ${select} >${curSale.code}</option>
                        </c:forEach>
                    </select>
                </div>
                </div>
            </div>
            <div class="collapse" id="payStockDetail">                    
                <div class="panel panel-default ">
                    <div class="panel-heading ">
                        <h4 class="panel-title"><div id="detailName" name="detailName" style="display:none" ></div>
                        </h4>
                    </div>
                    <div class="panel-body"  style="padding-right: 0px;">                                                              
                        <div class="row" style="padding-left: 15px;">             
                            <div class="row">
                                <div class="col-xs-11" style="width: 1030px">
                                    <input type="hidden" id="noStockDetailTable" name="noStockDetailTable" value="1"/>
                                    <table class="display paginated" id="StockDetailTable" >
                                        <thead>
                                            <tr class="datatable-header">
                                                <th style="width: 5%">No</th>                                   
                                                <th style="width: 10%">Code</th>
                                                <th style="width: 10%">Type</th>
                                                <th style="width: 10%">Ref No</th>
                                                <th style="width: 10%">Pick Up</th>
                                                <th style="width: 10%">Pick Date</th>                                                                      
                                                <th style="width: 10%">Cost</th>
                                                <th style="width: 10%">Sale</th>
                                            </tr>
                                        </thead>
                                        <tbody> 
<!--                                            <tr>
                                                <td align="center">1</td>
                                                <td align="left">1</td>
                                                <td align="left">1</td>
                                                <td align="center">1</td>
                                                <td align="center">1</td>
                                                <td align="center">1</td>
                                                <td align="center">1</td>
                                                <td align="center">1</td>
                                            </tr>-->
                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-xs-11" style="width: 1030px">
                                    <table class="hidden" id="StockDetailTableTemp">
                                        <thead>
                                            <tr class="datatable-header">
                                                <th style="width: 5%">No</th>                                   
                                                <th style="width: 10%">Code</th>
                                                <th style="width: 10%">Type</th>
                                                <th style="width: 10%">Ref No</th>
                                                <th style="width: 10%">Pick Up</th>
                                                <th style="width: 10%">Pick Date</th>                                                                      
                                                <th style="width: 10%">Cost</th>
                                                <th style="width: 10%">Sale</th>
                                            </tr>
                                        </thead>
                                        <tbody> 

                                        </tbody>
                                    </table>
                                </div>
                                <div class="col-xs-11" style="width: 1030px">
                                    <table  class="hidden" id="StockDetailTableTempCal">
                                        <thead>
                                            <tr class="datatable-header">
                                                <th style="width: 5%">No</th>                                   
                                                <th style="width: 10%">Code</th>
                                                <th style="width: 10%">Type</th>
                                                <th style="width: 10%">Ref No</th>
                                                <th style="width: 10%">Pick Up</th>
                                                <th style="width: 10%">Pick Date</th>                                                                      
                                                <th style="width: 10%">Cost</th>
                                                <th style="width: 10%">Sale</th>
                                            </tr>
                                        </thead>
                                        <tbody> 
                                            
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>            
                    </div>
                    <div class="row" style="padding-top: 15px;padding-bottom:  15px; padding-left:  100px;">
                        <div class="col-xs-1 text-right" style="width: 130px">
                            <label class="control-label text-right">Total Cost</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <input type="hidden" class="form-control text-right" id="totalCostTempCal" name="totalCostTempCal" value="${paymentStock.costAmount}" readonly=""/>
                            <input type="text" class="form-control text-right" id="totalCost" name="totalCost" value="" readonly=""/>
                        </div>

                        <div class="col-xs-1 text-right" style="width: 130px">
                            <label class="control-label text-right">Total Sale</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <input type="hidden" class="form-control text-right" id="totalSaleTempCal" name="totalSaleTempCal" value="${paymentStock.saleAmount}" readonly=""/>
                            <input type="text" class="form-control text-right" id="totalSale" name="totalSale" value="" readonly=""/>
                        </div>
                    </div> 
                </div>
            </div> 
            <br>
            <div class="row">
                <div class="col-xs-12 text-center">
                    <input type="hidden" class="form-control" id="countRowStock" name="countRowStock" value="${requestScope['countRowStock']}" />
                    <input type="hidden" class="form-control" id="countRowDetail" name="countRowDetail" value="${requestScope['countRowDetail']}" />
                    <button type="button" id="btnSave" name="btnSave" class="btn btn-success" onclick="saveAction()">
                        <i class="fa fa-save"></i> Save             
                    </button>
                    
                    <button type="button" id="ButtonNew" name="ButtonNew" onclick="clearNew()" class="btn btn-success"><i class="fa fa-plus-circle"></i> New</button>
                </div>
            </div>                     
      
        </form>    
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
                        <c:forEach var="table" items="${stockList}" varStatus="i">
                            <tr>
                                <td align="center">${i.count}</td>
                                <td align="left">${table.product.name}</td>
                                <td align="left">${table.staff.username}</td>
                                <td align="center">${table.createDate}</td>
                                <td align="center">${table.effectiveFrom}</td>
                                <td align="center">${table.effectiveTo}</td>
                                <td class="text-center ">
                                    <a id="" href="#">
                                        <span id="SpanGlyphiconEdit1" class="glyphicon glyphicon-plus" onclick="createStockDetails('${table.id}','${table.product.name}','${table.staff.username}','${table.createDate}','${table.effectiveFrom}','${table.effectiveTo}');"></span>
                                    </a>
                                </td>   
                            </tr>
                        </c:forEach>
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

<!--DELETE MODAL-->
<div class="modal fade" id="DeletePaymentStock" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Payment Stock Detail</h4>
            </div>
            <div class="modal-body" id="delPaymentStock">

            </div>
            <div class="modal-footer">
                <button type="submit" onclick="DeleteRowPaymentStock()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>               
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<c:if test="${! empty requestScope['saveresult']}">
    <c:if test="${requestScope['saveresult'] =='save successful'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['saveresult'] =='save unsuccessful'}">        
        <script language="javascript">
            $('#textAlertDivNotSave').show();
        </script>
    </c:if>
</c:if>
<c:if test="${! empty requestScope['deleteresult']}">
    <c:if test="${requestScope['deleteresult'] =='delete successful'}">        
        <script language="javascript">
            $('#textAlertDivDelete').show();
        </script>
    </c:if>
    <c:if test="${requestScope['deleteresult'] =='delete unsuccessful'}">        
        <script language="javascript">
            $('#textAlertDivNotDelete').show();
        </script>
    </c:if>
</c:if>
<c:if test="${! empty requestScope['searchresult']}">
    <c:if test="${requestScope['searchresult'] =='searchfail'}">        
        <script language="javascript">
            $('#textAlertPayNo').show();
        </script>
    </c:if>
</c:if>       
        
        
<script type="text/javascript" charset="utf-8">
    
    function hideCollapse(count) {
        var hideCollapseCheck = document.getElementById('hideCollapseCheck').value;
        if(hideCollapseCheck == count){
            $("div").find($('.collapse')).collapse('hide');
            document.getElementById('hideCollapseCheck').value = '';
        }else{
            $('.collapse').collapse('show');
            document.getElementById('hideCollapseCheck').value = count;
        }
    }
    
    $(document).ready(function() {  
        $(".money").mask('000,000,000.00', {reverse: true});

        $("#payNo").keyup(function(event) {
           if (event.keyCode === 13) {
               searchPaymentNoStock();
           }
        });
        calculateCostTotalAll();
        calculateSaleTotalAll();
        
        $(".decimal").inputmask({
            alias: "decimal",
            integerDigits: 8,
            groupSeparator: ',',
            autoGroup: true,
            digits: 2,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0.00",
        });
        
            var noStockTable = parseInt($("#noStockTable").val());
            var no = 1;
            for(var i = 1 ; i < noStockTable ; i++){
                var runningNo = $("#runningNo"+i).val();
                if(runningNo != null){
                    document.getElementById('runningNo' + i).style.display = 'block';
                    document.getElementById('runningNo' + i).innerHTML = no;
                    no ++ ;
                }   
            }
            $("#noStockTableTemp").val(no);
    });
    
    function saveAction() {
        $('#textAlertDivSave').hide();
        $('#textAlertDivNotSave').hide();
        $('#textAlertDivDelete').hide();
        $('#textAlertDivNotDelete').hide();
        $('#textAlertPayNo').hide();
        $('#fail').hide();
        
        var action = document.getElementById('action');
        action.value = 'savePaymentStock';
        var countRowStock = document.getElementById('countRowStock');
        countRowStock.value = $("#StockTable tr").length;
        var countRowDetail = document.getElementById('countRowDetail');
        countRowDetail.value = $("#StockDetailTableTempCal tr").length;
        document.getElementById('PaymentStockForm').submit();
    }
    
    function getPaymentStockItemCostSale(psdId,stockid,productname,noStockTable) {
        getStockDetail(stockid,psdId,productname,noStockTable);
        getStockDetailTempCal(stockid,psdId,productname,noStockTable);
    }
    
    function getPaymentStockItemCostSaleAjax(psdId,noStockTable){
        var servletName = 'PaymentStockServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&psdId=' + psdId +
                '&countRowDetail=' + 1 +
                '&type=' + 'getPaymentStockItemCostSale';
        CallAjaxPaymentStockItemCostSale(param,noStockTable);
    }
    
    function CallAjaxPaymentStockItemCostSale(param,noStockTable) {
        var url = 'AJAXServlet';
        $("#ajaxload").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function(msg) {
                    if(msg !== 'null'){
                        $('#StockDetailTableTemp').dataTable().fnClearTable();
                        $('#StockDetailTableTemp').dataTable().fnDestroy();
                        $("#StockDetailTableTemp tbody").append(msg);
                        var countRowStockDetail = $("#StockDetailTable tr").length;
                        var noMaxTemp = $("#noMaxTemp").val();
                        for(var i = 1 ; i<countRowStockDetail ; i++ ){
                            for(var j = 1 ; j < noMaxTemp+1 ; j++ ){
                                var stockDetailIdTable = $("#stockDetailIdTable"+i).val(); 
                                var stockDetailIdTemp = $("#stockDetailIdTemp"+j).val();
                                if(stockDetailIdTable === stockDetailIdTemp){
                                    $("#cost"+i).val( $("#costTemp"+j).val()); 
                                    $("#sale"+i).val( $("#saleTemp"+j).val()); 
                                    $("#psiIdTable"+i).val($("#psiIdTemp"+j).val());
                                    $("#psdIdTable"+i).val($("#psdIdTemp"+j).val());
                                    setCostAmountToTableTemp(i);
                                    setFormatCurrency(i,noStockTable);
                                    calculateCostTotal(noStockTable);
                                    calculateSaleTotal(noStockTable);
                                }
                            }
                        }
                        
                        var countRowStockDetail = $("#StockDetailTable tr").length;
                        var countRow = $("#StockDetailTableTempCal tr").length;
                        for(var i = 1 ; i<countRowStockDetail ; i++ ){
                            for(var j = 1 ; j < countRow ; j++ ){
                                var stockDetailIdTable = $("#stockDetailIdTable"+i).val(); 
                                var stockDetailIdTemp = $("#stockDetailIdTableTempCount"+j).val();
                                if(stockDetailIdTable === stockDetailIdTemp){
                                    $("#costTempCount"+j).val( $("#cost"+i).val()); 
                                    $("#saleTempCount"+j).val( $("#sale"+i).val()); 
                                    $("#psiIdTableTempCount"+j).val($("#psiIdTable"+i).val());
                                    $("#psdIdTableTempCount"+j).val($("#psdIdTable"+i).val());
//                                    calculateCostTotalAll();
//                                    calculateSaleTotalAll();
                                }
                            }
                        }

                    }
                }, error: function(msg) {
                    alert('error');
                }
            });
        } catch (e) {
            alert(e);
        }
    }

    
function deletePaymentStockDetailList(paymentStockDetailId , row , stockid){
    $('#textAlertDivSave').hide();
    $('#textAlertDivNotSave').hide();
    $('#textAlertDivDelete').hide();
    $('#textAlertDivNotDelete').hide();
    $('#textAlertPayNo').hide();
    $('#fail').hide();
    if(paymentStockDetailId === ''){
        $("#paymentStockDetailId" + row).parent().remove();
        document.getElementById('totalCost').value = formatNumber(0);
        document.getElementById('totalSale').value = formatNumber(0);
        var hideCollapseCheck = document.getElementById('hideCollapseCheck').value;
        if(hideCollapseCheck !== ''){
            $("div").find($('.collapse')).collapse('hide');
            document.getElementById('hideCollapseCheck').value = '';
        }
        $('#textAlertDivDelete').show();
        calculateCostTotalAll();
        calculateSaleTotalAll();
    }else{
        document.getElementById('paymentStockDetailIdDelete').value = paymentStockDetailId;
        document.getElementById('paymentStockRowDelete').value = row;
        $("#delPaymentStock").text('Are you sure to delete stock from this payment ?');
        $('#DeletePaymentStock').modal('show');
    }
    
    var noStockTable = parseInt($("#noStockTable").val());
    var no = 1;
    for(var i = 1 ; i < noStockTable ; i++){
        var runningNo = $("#runningNo"+i).val();
        if(runningNo != null){
            document.getElementById('runningNo' + i).innerHTML = no;
            no ++ ;
        }   
    }
    $("#noStockTableTemp").val(no);
}

function DeleteRowPaymentStock(){
    $('#textAlertDivSave').hide();
    $('#textAlertDivNotSave').hide();
    $('#textAlertDivDelete').hide();
    $('#textAlertDivNotDelete').hide();
    $('#textAlertPayNo').hide();
    $('#fail').hide();
    
    var psdIdDelete = document.getElementById('paymentStockDetailIdDelete').value;
    var row = document.getElementById('paymentStockRowDelete').value;
        if (psdIdDelete === '') {
            $("#paymentStockDetailId" + row).parent().remove();
            $('#textAlertDivDelete').show();
            document.getElementById('totalCost').value = formatNumber(0);
            document.getElementById('totalSale').value = formatNumber(0);
            var hideCollapseCheck = document.getElementById('hideCollapseCheck').value;
            if(hideCollapseCheck !== ''){
                $("div").find($('.collapse')).collapse('hide');
                document.getElementById('hideCollapseCheck').value = '';
            }
            calculateCostTotalAll();
            calculateSaleTotalAll();
            
            var noStockTable = parseInt($("#noStockTable").val());
            var no = 1;
            for(var i = 1 ; i < noStockTable ; i++){
                var runningNo = $("#runningNo"+i).val();
                if(runningNo != null){
                    document.getElementById('runningNo' + i).innerHTML = no;
                    no ++ ;
                }   
            }
            $("#noStockTableTemp").val(no);
        }
        else {
            $.ajax({
                url: 'PaymentStock.smi?action=deletePaymentStock',
                type: 'get',
                data: {psdIdDelete: psdIdDelete},
                success: function() {
                    $("#paymentStockDetailId" + row).parent().remove();
                    $('#textAlertDivDelete').show();
                    document.getElementById('totalCost').value = formatNumber(0);
                    document.getElementById('totalSale').value = formatNumber(0);
                    var hideCollapseCheck = document.getElementById('hideCollapseCheck').value;
                    if(hideCollapseCheck !== ''){
                        $("div").find($('.collapse')).collapse('hide');
                        document.getElementById('hideCollapseCheck').value = '';
                    }
                    calculateCostTotalAll();
                    calculateSaleTotalAll();
                    
                    var noStockTable = parseInt($("#noStockTable").val());
                    var no = 1;
                    for(var i = 1 ; i < noStockTable ; i++){
                        var runningNo = $("#runningNo"+i).val();
                        if(runningNo != null){
                            document.getElementById('runningNo' + i).innerHTML = no;
                            no ++ ;
                        }   
                    }
                    $("#noStockTableTemp").val(no);
                },
                error: function() {
                    console.log("error");
                    $('#textAlertDivNotDelete').show();
                }
            });
        }
    $('#DeletePaymentStock').modal('hide');  
}

function getStockDetailTempCal(stockid,psdId,productname,noStockTable) {
        var countRow = $("#StockDetailTableTempCal tr").length;
        var servletName = 'PaymentStockServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&stockId=' + stockid +
                '&countRowDetail=' + countRow +
                '&type=' + 'getStockDetailTempCal';
        CallAjaxTempCal(param,psdId,stockid,noStockTable);
    }
    
    function CallAjaxTempCal(param,psdId,stockid,noStockTable) {
        var check = true;
        var url = 'AJAXServlet';
        $("#ajaxload").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function(msg) {
                    if(msg !== 'null'){
                        var countRow = $("#StockDetailTableTempCal tr").length;
                        for(var i = 1 ; i<countRow ; i++ ){
                            var stockDetail = $("#stockIdTableTempCount"+i).val();
                            if(stockDetail === stockid) {
                                check = false;
                            }
                        }
                        if(check){
                            $("#StockDetailTableTempCal tbody").append(msg);
                        }
                    }
                }, error: function(msg) {
                   alert('error');
                }
            });
        } catch (e) {
            alert(e);
        }

    }
    
    function searchStock() {
        $("#SearchStock").modal("show");
    }


    function searchPaymentNoStock() {
        var action = document.getElementById('action');
        action.value = 'searchPayNo';
        var payNo = document.getElementById('payNo');
        payNo.value = $("#payNo").val();
        document.getElementById('PaymentStockForm').submit();
    }


    function createStockDetails(stockid, productName, staff, addDate, effectiveFrom, effectiveTo) {
        $('#textAlertDivSave').hide();
        $('#textAlertDivNotSave').hide();
        $('#textAlertDivDelete').hide();
        $('#textAlertDivNotDelete').hide();
        $('#textAlertPayNo').hide();
        $('#fail').hide();
        var noStockTable = parseInt($("#noStockTable").val());
        var noStockTableTemp = parseInt($("#noStockTableTemp").val());
        for(var i=1; i<noStockTable; i++){
            if(stockid === $("#stockId"+i).val()){
                $("#SearchStock").modal("hide");
                $('#fail').show();
                return;
            }else{
                $('#fail').hide();
            }        
        }
        $("#StockTable").append(
                '<tr>' +
                '<input type="hidden" id="paymentStockDetailId'+ noStockTable +'" name="paymentStockDetailId'+ noStockTable +'"  value=""> '+
                '<input type="hidden" id="paymentStockId'+ noStockTable +'" name="paymentStockId'+ noStockTable +'"  value="">' +
                '<input type="hidden" id="stockId'+ noStockTable +'" name="stockId'+ noStockTable +'"  value="'+ stockid +'"> '+
                '<td class="hidden"><input type="hidden" id="chk'+ noStockTable +'" name="chk'+ noStockTable +'" value="' + productName + '"/></td>' +
                '<td class="text-center "><div id="runningNo' + noStockTable + '" value="' + noStockTableTemp + '"></div></td>' +
                '<td>' + productName + '</td>' +
                '<td>' + staff + '</td>' +
                '<td class="text-center ">' + addDate + '</td>' +
                '<td class="text-center ">' + effectiveFrom + '</td>' +
                '<td class="text-center ">' + effectiveTo + '</td>' +
                '<td class="text-right"><input type="text" id="totalcostamount'+ noStockTable +'" name="totalcostamount'+ noStockTable +'" class="form-control money" value="0.00" readonly=""></td>' +
                '<td class="text-right"><input type="text" id="totalsaleamount'+ noStockTable +'" name="totalsaleamount'+ noStockTable +'" class="form-control money" value="0.00" readonly=""></td>' +
                '<td class="text-center ">' +
                '<a id="ButtonEdit'+ noStockTable +'" onclick="getStockDetail( \'' + stockid + '\', \'' + "null" + '\' , \'' + productName + '\', \'' + noStockTable + '\');hideCollapse(\'' + noStockTable + '\');" data-target="#payStockDetail">'+
                '<span id="SpanEdit'+ noStockTable +'" class="glyphicon glyphicon glyphicon-list-alt"></span></a>'+
                '<a href="#" onclick="" data-toggle="modal" data-target=""> <span id="SpanRemove" class="glyphicon glyphicon-remove deleteicon" onclick="deletePaymentStockDetailList(\'\', \'' + noStockTable + '\' , \'' + stockid + '\');"></span></a>' +
                '</td>' +
                '<tr>'
                );
        document.getElementById('runningNo' + noStockTable).style.display = 'block';
        document.getElementById('runningNo' + noStockTable).innerHTML = noStockTableTemp;
        $("#noStockTable").val(noStockTable+1);
        $("#noStockTableTemp").val(noStockTableTemp+1);
        getStockDetail(stockid,"null",productName,noStockTable);
        getStockDetailTempCal(stockid,"null",productName,noStockTable,"");
        $("#SearchStock").modal("hide");

    }

    function getStockDetail(stockid,psdId,productname,noStockTable) {
        document.getElementById('detailName').style.display = 'block';
        document.getElementById('detailName').innerHTML = "Detail (" + productname + ")";
        var servletName = 'PaymentStockServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&stockId=' + stockid +
                '&countRowDetail=' + 1 +
                '&noStockTable=' +noStockTable +
                '&type=' + 'getStockDetail';
        CallAjax(param,psdId,stockid,noStockTable);
    }

    function CallAjax(param,psdId,stockid,noStockTable) {
        var url = 'AJAXServlet';
        $("#ajaxload").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function(msg) {
                    if(msg !== 'null'){
                        $('#StockDetailTable').dataTable().fnClearTable();
                        $('#StockDetailTable').dataTable().fnDestroy();
                        $("#StockDetailTable tbody").append(msg);

                        $('#StockDetailTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bInfo": false,
                            "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
                            "iDisplayLength": 10,
                            "bSort": false,
                            "bPaginate": false
                        });
                        
                        $(".decimal").inputmask({
                            alias: "decimal",
                            integerDigits: 8,
                            groupSeparator: ',',
                            autoGroup: true,
                            digits: 2,
                            allowMinus: false,
                            digitsOptional: false,
                            placeholder: "0.00",
                        });
                        
                        var countRowStockDetail = $("#StockDetailTable tr").length;
                        var countRow = $("#StockDetailTableTempCal tr").length;
                        
                        for(var i = 1 ; i<countRowStockDetail ; i++ ){
                            for(var j = 1 ; j < countRow ; j++ ){
                                var stockDetailIdTable = $("#stockDetailIdTable"+i).val(); 
                                var stockDetailIdTemp = $("#stockDetailIdTableTempCount"+j).val();
                                if(stockDetailIdTable === stockDetailIdTemp){
                                    $("#cost"+i).val( $("#costTempCount"+j).val()); 
                                    $("#sale"+i).val( $("#saleTempCount"+j).val()); 
                                    $("#psiIdTable"+i).val($("#psiIdTableTempCount"+j).val());
                                    $("#psdIdTable"+i).val($("#psdIdTableTempCount"+j).val());
                                    setFormatCurrency(i,noStockTable);
                                    calculateCostTotalAll();
                                    calculateSaleTotalAll();
                                }
                            }
                        }

                        $('table.paginated').each(function() {
                            var currentPage = 0;
                            var numPerPage = 10;
                            var $table = $(this);
                            $table.bind('repaginate', function() {
                                $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
                            });
                            $table.trigger('repaginate');
                            var numRows = $table.find('tbody tr').length;
                            var numPages = Math.ceil(numRows / numPerPage);
                            var $pager = $('<div class="col-xs-12 text-right" id="pageNo"><font style="color: #499DD5"></font>&nbsp;</div>');
                            var $br = $('<div class="col-xs-12"><br></div>');
                            for (var page = 0; page < numPages; page++) {
                                if(page === 0){
                                    $('<font style="color: #499DD5" id="noFirst" onclick="changeColor(\'first\')"><span class="page-number glyphicon"></span></font>').text(" " + "First" + "  ").bind('click', {
                                    newPage: page
                                    }, function(event) {
                                        changeColor('first');
                                        currentPage = event.data['newPage'];
                                        $table.trigger('repaginate');
                                        $(this).addClass('active').siblings().removeClass('active');
                                        $(this).css("color", "#AFEEEE");
                                    }).appendTo($pager).addClass('clickable');
                                }

                                $('<font style="color: #499DD5" id="no' + page + '" onclick="changeColor(\'' + page + '\')"><span class="page-number glyphicon"></span></font>').text(" " + (page + 1) + "  ").bind('click', {
                                    newPage: page
                                }, function(event) {                  
                                    currentPage = event.data['newPage'];
                                    $table.trigger('repaginate');
                                    $(this).addClass('active').siblings().removeClass('active');
                                    $(this).css("color", "#AFEEEE");
                                }).appendTo($pager).addClass('clickable');

                                if(page === (numPages - 1)){
                                    $('<font style="color: #499DD5" id="noLast" onclick="changeColor(\'last\')"><span class="page-number glyphicon"></span></font>').text(" " + "Last" + "  ").bind('click', {
                                    newPage: page
                                    }, function(event) {
                                        currentPage = event.data['newPage'];
                                        $table.trigger('repaginate');
                                        $(this).addClass('active').siblings().removeClass('active');
                                        $(this).css("color", "#AFEEEE");
                                    }).appendTo($pager).addClass('clickable');
                                }
                            }
                            $br.insertAfter($table).addClass('active');
                            $pager.insertAfter($table).find('span.page-number:first').addClass('active');
                            document.getElementById("pageNo").style.cursor="pointer";
                            document.getElementById("page").value = numPages-1;
                        });


                        if(psdId !== "null" && psdId !== null){
                            getPaymentStockItemCostSaleAjax(psdId,noStockTable);
                        }else{
                            $('.collapse').collapse('show');
                            document.getElementById('hideCollapseCheck').value = noStockTable;
                        }

                    }
                }, error: function(msg) {
                   alert('error');
                }
            });
        } catch (e) {
            alert(e);
        }

    }
    
    function changeColor(row){
        var page = parseInt($("#page").val())+1;
        var rowTemp = parseInt(row);
        var start = 0;
        var end = page-1;
        for(var i=0; i<page; i++){
           if(i === rowTemp){
                $("#no"+i).css("color", "#AFEEEE"); 
           
            }else{
                $("#no"+i).css("color", "#499DD5");
                $("#noFirst").css("color", "#499DD5");  
                $("#noLast").css("color", "#499DD5");  
            }
            
            if(row === 'first' || start === rowTemp){
                $("#no"+start).css("color", "#AFEEEE"); 
                $("#noFirst").css("color", "#AFEEEE"); 
            
            }else if(row === 'last' || end === rowTemp){
                $("#no"+end).css("color", "#AFEEEE"); 
                $("#noLast").css("color", "#AFEEEE");     
            }
        }       
    }

    function calculateCostTotal(noStockTable) {
        var count = $("#StockDetailTable tr").length;    
        var i;
        var grandTotal = 0;
        for (i = 1; i < count + 1; i++){
            var amount = document.getElementById("cost" + i);
            if (amount !== null) {
                var value = amount.value;
                if (value !== '') {
                    value = value.replace(/,/g, "");
                    var total = parseFloat(value);
                    grandTotal += total;
                    document.getElementById('cost' + i).value = formatNumber(total);
                }
            }
        }
        document.getElementById('totalCost').value = formatNumber(grandTotal);
        document.getElementById('totalcostamount'+(noStockTable)).value = $("#totalCost").val();
        calculateCostTotalAll();
    }

    function calculateSaleTotal(noStockTable) {
        var count = $("#StockDetailTable tr").length;
        var i;
        var grandTotal = 0;
        for (i = 1; i < count + 1; i++) {
            var amount = document.getElementById("sale" + i);
            if (amount !== null) {
                var value = amount.value;
                if (value !== '') {
                    value = value.replace(/,/g, "");
                    var total = parseFloat(value);
                    grandTotal += total;
                    document.getElementById('sale' + i).value = formatNumber(total);
                }
            }
        }
        document.getElementById('totalSale').value = formatNumber(grandTotal);
        document.getElementById('totalsaleamount'+noStockTable).value = $("#totalSale").val();
        calculateSaleTotalAll();
    }

    function setFormatCurrencyOnFocusOut(row,noStockTable) {
        $('#cost' + row).focusout(function() {
            setFormatCurrency(row,noStockTable);
            calculateCostTotal(noStockTable);
            setCostAmountToTableTemp(row);
        });

        $('#sale' + row).focusout(function() {
            setFormatCurrency(row,noStockTable);
            calculateSaleTotal(noStockTable);
            setCostAmountToTableTemp(row);
        });
    }

    function setFormatCurrency(row,noStockTable) {
        var cost = replaceAll(",", "", $('#cost' + row).val());
        if (cost == "") {
            cost = 0;
        }
        cost = parseFloat(cost);
        document.getElementById("cost" + row).value = formatNumber(cost);

        var sale = replaceAll(",", "", $('#sale' + row).val());
        if (sale == "") {
            sale = 0;
        }
        sale = parseFloat(sale);
        document.getElementById("sale" + row).value = formatNumber(sale);

        if (cost == "" || cost == 0) {
            document.getElementById("cost" + row).value = "";
        }

        if (sale == "" || sale == 0) {
            document.getElementById("sale" + row).value = "";
        }

        calculateCostTotal(noStockTable);
        calculateSaleTotal(noStockTable);
    }

    function replaceAll(find, replace, str) {
        return str.replace(new RegExp(find, 'g'), replace);
    }

    function formatNumber(num) {
        return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
    }

    function insertCommas(nField) {
        if (/^0/.test(nField.value)) {
            nField.value = nField.value.substring(0, 1);
        }
        if (Number(nField.value.replace(/,/g, ""))) {
            var tmp = nField.value.replace(/,/g, "");
            tmp = tmp.toString().split('').reverse().join('').replace(/(\d{3})/g, '$1,').split('').reverse().join('').replace(/^,/, '');
            if (/\./g.test(tmp)) {
                tmp = tmp.split(".");
                tmp[1] = tmp[1].replace(/\,/g, "").replace(/ /, "");
                nField.value = tmp[0] + "." + tmp[1]
            } else {
                nField.value = tmp.replace(/ /, "");
            }
        } else {
            nField.value = nField.value.replace(/[^\d\,\.]/g, "").replace(/ /, "");
        }
    }
    
    function setCostAmountToTableTemp(row){

        var stockdetailid = $('#stockDetailIdTable' + row).val();
        var costtemp = $('#cost' + row).val();
        var saletemp = $('#sale' + row).val();

        var countRow = $("#StockDetailTableTempCal tr").length;
        for(var i = 1 ; i<countRow ; i++ ){
            var stockDetailIdTableTempCount = $("#stockDetailIdTableTempCount"+i).val();
            if(stockDetailIdTableTempCount === stockdetailid) {
                var cost = replaceAll(",", "",costtemp);
                if (cost == "") {
                    cost = 0;
                }
                cost = parseFloat(cost);
                document.getElementById("costTempCount" + i).value = formatNumber(cost);

                var sale = replaceAll(",", "",saletemp);
                if (sale == "") {
                    sale = 0;
                }
                sale = parseFloat(sale);
                document.getElementById("saleTempCount" + i).value = formatNumber(sale);

                calculateCostTotalAll();
                calculateSaleTotalAll();
            }
        }
    }


    function calculateCostTotalAll() {
        var count = $("#StockTable tr").length;    
        var i;
        var grandTotal = 0;
        for (i = 1; i < count + 1; i++){
            var amount = document.getElementById("totalcostamount" + i);
            if (amount !== null) {
                var value = amount.value;
                if (value !== '') {
                    value = value.replace(/,/g, "");
                    var total = parseFloat(value);
                    grandTotal += total;
                    document.getElementById('totalcostamount' + i).value = formatNumber(total);
                }
            }
        }
        document.getElementById('totalCostAll').value = formatNumber(grandTotal);
    }

    function calculateSaleTotalAll() {
        var count = $("#StockTable tr").length;
        var i;
        var grandTotal = 0;
        for (i = 1; i < count + 1; i++) {
            var amount = document.getElementById("totalsaleamount" + i);
            if (amount !== null) {
                var value = amount.value;
                if (value !== '') {
                    value = value.replace(/,/g, "");
                    var total = parseFloat(value);
                    grandTotal += total;
                    document.getElementById('totalsaleamount' + i).value = formatNumber(total);
                }
            }
        }
        document.getElementById('totalSaleAll').value = formatNumber(grandTotal);
    }
    
    function clearNew() {
        var action = document.getElementById('action');
        action.value = 'new';
        document.getElementById('PaymentStockForm').submit();
    }
</script>    