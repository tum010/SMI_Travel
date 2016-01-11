<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/PaymentStock.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="paymentStock" value="${requestScope['PaymentStock']}" /> 
<c:set var="PaymentStockDetailList" value="${requestScope['PaymentStockDetailList']}" />
<c:set var="PaymentStockItemList" value="${requestScope['PaymentStockItemList']}" /> 
<c:set var="stockList" value="${requestScope['StockList']}" />
<c:set var="currencyList" value="${requestScope['currencyList']}" />

<c:set var="StockDetailList" value="${requestScope['StockDetailList']}" /> 

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
            <strong>Pay No. not available !</strong> 
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
            <input type="hidden" class="form-control" id="action" name="action" value="" />
            <input type="hidden" class="form-control" id="paymentStockDetailIdDelete" name="paymentStockDetailIdDelete" value="" />
            <input type="hidden" class="form-control" id="paymentStockRowDelete" name="paymentStockRowDelete" value="" />
            
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
                                <input type="hidden" id="noStockTable" name="noStockTable" value="${requestScope['noStockTable']}"/>
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
                                        <c:forEach var="table" items="${PaymentStockDetailList}" varStatus="i">
                                            <tr>
                                                <input type="hidden" id="paymentStockDetailId${i.count}" name="paymentStockDetailId${i.count}"  value="${table.id}"> 
                                                <input type="hidden" id="paymentStockId${i.count}" name="paymentStockId${i.count}"  value="${table.paymentStock.id}"> 
                                                <input type="hidden" id="stockId${i.count}" name="stockId${i.count}"  value="${table.stock.id}"> 
                                                <td align="center">${i.count}</td>
                                                <td align="left">${table.stock.product.name}</td>
                                                <td align="left">${table.stock.staff.username}</td>
                                                <td align="center">${table.stock.createDate}</td>
                                                <td align="center">${table.stock.effectiveFrom}</td>
                                                <td align="center">${table.stock.effectiveTo}</td>
                                                <td class="text-center ">
                                                    <a id="ButtonEdit${i.count}" onclick="getPaymentStockItemCostSale('${table.id}','${table.stock.id}');hideCollapse();" class="carousel" data-toggle="collapse" data-parent="#accordion" data-target="#payStockDetail" aria-expanded="true" aria-controls="collapseExample">
                                                        <span id="SpanEdit${i.count}" class="glyphicon glyphicon glyphicon-list-alt"></span>
                                                    </a>
                                                    <a href="#" onclick="" data-toggle="modal" data-target=""> <span id="SpanRemove" class="glyphicon glyphicon-remove deleteicon" onclick="deletePaymentStockDetailList('${table.id}','${i.count}','');"></span></a>
                                                </td>  
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>   
                        </div>
                    </div>            
                </div>
            </div>
            <%--<c:forEach var="data" items="${PaymentStockDetailList}" varStatus="i">--%>  
            <div class="collapse" id="payStockDetail">                    
                <div class="panel panel-default ">
                    <div class="panel-heading ">
                        <h4 class="panel-title">Detail</h4>
                    </div>
                    <div class="panel-body"  style="padding-right: 0px;">                                                              
                        <div class="row" style="padding-left: 15px;">             
                            <div class="row">
                                <div class="col-xs-11" style="width: 1030px">
                                    <input type="hidden" id="noStockDetailTable" name="noStockDetailTable" value="1"/>
                                    <table class="display" id="StockDetailTable" >
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
                                            <tr>
                                                <td align="center">1</td>
                                                <td align="left">1</td>
                                                <td align="left">1</td>
                                                <td align="center">1</td>
                                                <td align="center">1</td>
                                                <td align="center">1</td>
                                                <td align="center">1</td>
                                                <td align="center">1</td>
                                            </tr>
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
                            </div>
                        </div>            
                    </div>
                </div>
            </div> 
            <%--</c:forEach>--%> 
            <div class="row" style="padding-top: 15px;padding-bottom:  15px; padding-left:  100px;">
                <div class="col-xs-1 text-right" style="width: 130px">
                    <label class="control-label text-right">Total Cost</label>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <input type="hidden" class="form-control text-right" id="totalCostTmp" name="totalCostTmp" value="" readonly=""/>
                    <input type="text" class="form-control text-right" id="totalCost" name="totalCost" value="${paymentStock.costAmount}" readonly=""/>
                </div>
                <div class="col-xs-1" style="width: 100px">
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
                <div class="col-xs-1 text-right" style="width: 130px">
                    <label class="control-label text-right">Total Sale</label>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <input type="text" class="form-control text-right" id="totalSale" name="totalSale" value="${paymentStock.saleAmount}" readonly=""/>
                </div>
                 <div class="col-xs-1" style="width: 100px">
                    <select class="form-control" name="curSale" id="curSale" >
                        <option  value="" >---------</option>
                        <c:forEach var="curSale" items="${currencyList}" varStatus="status">                                       
                            <c:set var="select" value="" />
                            <c:if test="${curSale.code == paymentStock.curCost}">
                                <c:set var="select" value="selected" />
                            </c:if>
                            <option  value="${curSale.code}" ${select} >${curSale.code}</option>
                        </c:forEach>
                    </select>
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
        
<script type="text/javascript" charset="utf-8">
    
    function hideCollapse() {
        $("div").find($('.collapse')).collapse('hide');
    }
    
    $(document).ready(function() {  
        document.getElementById('totalCost').value = formatNumber(0);
        document.getElementById('totalSale').value = formatNumber(0);
        
//        var countRowStock = $("#StockDetailTable tr").length;    
//        if(countRowStock === 1){
//            document.getElementById('totalCost').value = formatNumber(0);
//            document.getElementById('totalSale').value = formatNumber(0);
//        }else{
//            for(var i=1;i<countRowStock;i++){
//                setFormatCurrency(i);
//                calculateCostTotal();
//                calculateSaleTotal();
//            }
//        }
        
        $("#payNo").keyup(function(event) {
           if (event.keyCode === 13) {
               searchPaymentNoStock();
           }
        });
        
        $('.collapse').collapse('hide');
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
        countRowDetail.value = $("#StockDetailTable tr").length;

        document.getElementById('PaymentStockForm').submit();
    }
    
    function getPaymentStockItemCostSale(psdId,stockid) {
        getStockDetail(stockid,psdId,stockid);
    }
    
    function getPaymentStockItemCostSaleAjax(psdId){
        var servletName = 'PaymentStockServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&psdId=' + psdId +
                '&countRowDetail=' + 1 +
                '&type=' + 'getPaymentStockItemCostSale';
        CallAjaxPaymentStockItemCostSale(param);
        
        
    }
    function CallAjaxPaymentStockItemCostSale(param) {
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
                                    setFormatCurrency(i);
                                    calculateCostTotal();
                                    calculateSaleTotal();
                                }
                            }
                        }
//                        $("div").find($('.collapse')).collapse('hide');
                        hideCollapse();
                        $('.collapse').collapse('show');
                            
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
        $('.collapse').collapse('hide');
        document.getElementById('totalCost').value = formatNumber(0);
        document.getElementById('totalSale').value = formatNumber(0);
        $('#textAlertDivDelete').show();
    }else{
        document.getElementById('paymentStockDetailIdDelete').value = paymentStockDetailId;
        document.getElementById('paymentStockRowDelete').value = row;
        $("#delPaymentStock").text('Are you sure to delete stock from this payment ?');
        $('#DeletePaymentStock').modal('show');
    }
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
            $('.collapse').collapse('hide');
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
                    $('.collapse').collapse('hide');
                },
                error: function() {
                    console.log("error");
                    $('#textAlertDivNotDelete').show();
                }
            });
        }
    $('#DeletePaymentStock').modal('hide');  
}
</script>    