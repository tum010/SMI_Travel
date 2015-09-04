<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="js/jquery.mask.min.js"></script>

<c:set var="result" value="${requestScope['result']}" />
<c:set var="ListProductStock" value="${requestScope['ListProductStock']}" />
<c:set var="ListStaffStock" value="${requestScope['ListStaffStock']}" />
<c:set var="getType" value="${requestScope['getType']}" />
<c:set var="stockData" value="${requestScope['stockData']}" />
<c:set var="from" value="${requestScope['FromDate']}" />
<c:set var="to" value="${requestScope['ToDate']}" />
<c:choose>
    <c:when test="${requestScope['CreateDate'] != null}">
        <c:set var="create" value="${requestScope['CreateDate']}" />
    </c:when>
    <c:otherwise>
       <c:set var="create" value="${requestScope['thisdate']}" />    
    </c:otherwise>
</c:choose>
<c:set var="listStockDetail" value="${requestScope['listStockDetail']}" />

<section class="content-header" >
    <h1>
        Master Stock
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Master </a></li>          
        <li class="active"><a href="#">Stock</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app="">
   
    <div class="col-sm-1">
        <!--<div ng-include="'WebContent/Master/StockMenu.html'"></div>-->
    </div>
    <form action="Stock.smi" method="post" id="StockForm" name="StockFormName" role="form" onsubmit="">
    <div class="col-sm-10">
        <input type="hidden" id="idStockDelete" name= "idStockDelete" value="1" />
        <!--Alert Save -->
        <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Success!</strong> 
        </div>
        <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Save Not Success!</strong> 
        </div>
        <div id="textAlertMoreOne"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Duplicate Data in table !!</strong> 
        </div>
        <div id="checklengthCode"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Max length More 50!!</strong> 
        </div>
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Stock</b></h4>
            </div>
            <div class="col-sm-6 text-right" style="padding-right: 15px">
                <a href="SearchStock.smi">
                    <button type="button"  id="ButtonSearch"  name="ButtonSearch"  class="btn btn-primary" style="width: 100px;">
                        <span id="SpanSearch" class="glyphicon glyphicon-arrow-left"></span> Back
                    </button>   
                </a>
            </div>
        </div>
        <hr/>
        <div class="panel panel-default">
            <div class="panel-heading">
                Detail
            </div>
            <div class="panel-body">
                <div class="row" > 
                    <div class="col-xs-12 ">
                        <input type="text" class="form-control hidden" id="InputStockId" name="InputStockId" value="${stockData.id}" />
                        <input type="text" class="form-control hidden" id="InputProductId" name="InputProductId" value="${stockData.product.id}" />
                         <input name="InputStaffId" id="InputStaffId" type="hidden" class="form-control" value="${stockData.staff.id}" />
                        <div class="col-xs-2 text-right"  style="padding-right: 25px;width: 140px;">
                            <label class="control-label">Product<font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="padding-left:5px;width: 160px;"> 
                            <div class="input-group" id="gr" >
                                <input type="text" class="form-control" id="InputProduct" name="InputProduct" value="${stockData.product.code}" />
                                <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchProduct">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width: 200px;">
                            <input name="InputProductName" id="InputProductName" type="text" class="form-control" value="${stockData.product.name}" readonly=""/>
                        </div>
                        <div class="col-xs-1 text-right"  style="width: 87px;" >
                            <label class="control-label">Staff</lable>
                        </div>                     
                        <div class="col-md-2 form-group text-left" style="width: 178px;" > 
                            <div class="input-group" id="gr"  >
                                <input type="text" class="form-control" id="InputStaff" name="InputStaff" value="${stockData.staff.username}" />
                                <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchStaff">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width: 200px;">
                            <input name="InputStaffName" id="InputStaffName" type="text" class="form-control" value="${stockData.staff.name}" readonly=""/>
                        </div>
                    </div>   
                </div><!-- End Row 1-->
                <div class="row" >
                    <div class="col-xs-12 ">
                        <div class="col-xs-2 text-right" style="width: 140px;">
                            <label class="control-label">Effective From</lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="width: 170px;padding-left: 3px;" >
                            <div class='input-group date' id="DateFrom">
                                <!--<input name="InputEffectiveFromDate0" id="InputEffectiveFromDate0" type="text" class="form-control " data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${from}" />-->
                                <input name="InputEffectiveFromDate" id="InputEffectiveFromDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${from}" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-xs-3 text-right" style="width: 285px;">
                            <label class="control-label">Effective To</lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="padding-left: 6px;width: 170px;" >
                            <div class='input-group date' id="DateTo">
                                <input name="InputInputEffectiveToDate" id="InputInputEffectiveToDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${to}" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>   
                </div><!-- End Row 2-->
                <div class="row" >
                    <div class="col-xs-12 ">
                        <div class="col-xs-2 text-right" style="padding-right: 25px;width: 140px;">
                            <label class="control-label">Description</lable>
                        </div>
                        <div class="col-md-5 form-group text-left" style="width: 370px;padding-left: 3px;" >
                            <textarea class="form-control" rows="3" id="descriptionStock" name="descriptionStock" maxlength="255">${stockData.description}</textarea>
                        </div>
                        <div class="col-xs-1 text-left" style="width: 90px;padding-left: 3px;padding-right: 0px;">
                            <label class="control-label">Add Date <font style="color: red">*</font></lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="padding-left: 0px;width: 165px;">
                            <div class='input-group date' >
                                <input name="InputStockDate" id="InputStockDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${create}" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>   
                </div><!-- End Row 3-->
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading">
                Item List
            </div>
            <div class="panel-body">
                <div class="row" >
                    <div class="col-xs-12 ">
                        <div class="col-xs-1 text-right" style="width: 50px;">
                            <label class="control-label">Prefix</lable>
                        </div>
                        <div class="col-md-1 form-group text-left" style="width: 130px;" id="PrefixInput"> 
                            <input name="InputPrefix" id="InputPrefix" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-1 text-right"  style="width: 60px;">
                            <label class="control-label">Start</lable>
                        </div>
                        <div class="col-md-1 form-group text-left" style="width: 120px;" id="StartInput">  
                            <input name="InputStart" id="InputStart"  maxlength="10" type="text" class="form-control number" value="" onkeyup="setNumberFormat(this);" />
                        </div>
                        <div class="col-xs-1 text-right"  style="width: 60px;">
                            <label class="control-label">Digit</lable>
                        </div>
                        <div class="col-md-1 form-group text-left" style="width: 90px;" id="DigitInput">  
                            <input name="InputDigit" id="InputDigit"  maxlength="10" type="text" class="form-control money" value="" />
                        </div>
                        <div class="col-xs-2 text-right" style="width: 140px;">
                            <label class="control-label">Number Of Item</lable>
                        </div>
                        <div class="col-md-1 form-group text-left" id="NumberOfItemInput"> 
                            <input name="InputNumberOfItem" id="InputNumberOfItem" type="text" class="form-control money" value="" placeholder="123"/>
                        </div>
                        <div class="col-xs-1 text-right"  >
                            <label class="control-label">Type</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" > 
                            <select name="Selecttype" id="Selecttype" class="form-control">
                                <c:forEach var="type1" items="${getType}">
                                    <option value="${type1.id}"><c:out value="${type1.name}" /></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>   
                </div><!-- End Row 1-->
                <div class="row" >
                    <div class="col-xs-12" style="padding-right: 17px;">
                        <div class="col-md-8 text-right" >                                          
                        </div> 
                        <div class="col-md-2 text-right"  >                 
                        </div>
                        <div class="col-md-2 text-right">
                            <button type="button"  id="ButtonAdd"  name="ButtonAdd" onclick=" return addItemList()" class="btn btn-primary btn-primary " style="width: 130px;">
                                <span id="SpanAdd" class="glyphicon glyphicon-print fa fa-plus-circle"></span> Generate Item
                            </button>                                          
                        </div>
                    </div>   
                </div><!-- End Row 2--><br>
                <div class="row" style="padding-left: 0px">
                        <div class="col-xs-12 ">
                            <table class="display" id="StockTable">
                                <thead class="datatable-header">
                                    <tr>
                                        <th class="hidden">id</th>
                                        <th style="width: 5%">No</th>                                   
                                        <th style="width: 20%">Code</th>
                                        <th style="width: 10%">Type</th>
                                        <th style="width: 15%">Pay Status</th>
                                        <th class="hidden">Pay Status Temp</th>
                                        <th style="width: 15%">Item Status</th>
                                        <th class="hidden">Item Status Temp</th>
                                        <th style="width: 8%">Action</th>
                                        <th class="hidden">Ref no</th>
                                        <th class="hidden">Pick up</th>
                                        <th class="hidden">Pick up date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <input type="text" class="hidden" id="counter" name="counter" value="1" />
                                    <input type="text" class="hidden" id="counterTable" name="counterTable" value="0" />
                                    <input type="text" class="hidden" id="counterAdd" name="counterAdd" value="1" />
                                    <c:set var="count" value="1"></c:set>                                          
                                    <c:forEach var="std" items="${listStockDetail}" varStatus="taxdesc">
                                        <c:set var="pay" value="" />
                                        <c:if test="${std.payStatus == 0}">
                                            <c:set var="pay" value="No Paid" />
                                        </c:if> 
                                        <c:if test="${std.payStatus == 1}">
                                            <c:set var="pay" value="Pay" />
                                        </c:if>
                                        
                                        <c:set var="itemS" value="" />
                                        <c:if test="${std.MStockStatus.id == 1}">
                                            <c:set var="itemS" value="NEW" />
                                        </c:if> 
                                        <c:if test="${std.MStockStatus.id == 2}">
                                            <c:set var="itemS" value="INUSE" />
                                        </c:if>
                                        <c:if test="${std.MStockStatus.id == 3}">
                                            <c:set var="itemS" value="CANCEL" />
                                        </c:if> 
                                        <c:if test="${std.MStockStatus.id == 4}">
                                            <c:set var="itemS" value="REFUND" />
                                        </c:if>
                                    <tr>
                                        <td class="hidden"><input type="text" class="hidden" id="stockDetailId${taxdesc.count}" name="stockDetailId${taxdesc.count}" value="${std.id}" /></td>
                                        <td>${taxdesc.count}</td>
                                        <td><input type="text"  class="form-control" name="codeItemList${taxdesc.count}" id="codeItemList${taxdesc.count}" value="${std.code}" maxlength="50"/></td>
                                        <td>       
                                            <select id="SeleteTypeItemList${taxdesc.count}" name="SeleteTypeItemList${taxdesc.count}" class="form-control" >
                                                <c:forEach var="type" items="${getType}">
                                                    <c:set var="select" value="" />
                                                        <c:if test="${type.id == std.typeId.id}">
                                                            <c:set var="select" value="selected" />

                                                        </c:if>   
                                                     <option value="${type.id}" ${select}><c:out value="${type.name}" /></option>
                                                </c:forEach>
                                            </select>                                             
                                        </td>                                
                                        <td>${pay}</td>
                                        <td class="hidden"><input type="text"  class="form-control" name="payTemp${taxdesc.count}" id="payTemp${taxdesc.count}" value="${std.payStatus}" /></td>
                                        <td>${itemS}</td>
                                        <td class="hidden"><input type="text"  class="form-control" name="itemTemp${taxdesc.count}" id="itemTemp${taxdesc.count}" value="${std.MStockStatus.id}" /></td>
                                        <td class="text-center">                                          
                                            <a href="#" onclick="deleteItemListRow('${taxdesc.count}','${std.code}')"  data-toggle="modal" data-target="" class="remCF" id="ButtonRemove${taxdesc.count}">
                                                <span id="Spanremove${taxdesc.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="" data-toggle="modal" data-target="#delStockModal"></span>
                                            </a>

                                        </td>
                                        <td class="hidden"><input type="text"  class="form-control" name="otherBookingId${taxdesc.count}" id="otherBookingId${taxdesc.count}" value="${std.otherBooking.id}" /></td>
                                        <td class="hidden"><input type="text"  class="form-control" name="staffId${taxdesc.count}" id="staffId${taxdesc.count}" value="${std.staff.id}" /></td>
                                        <td class="hidden"><input type="text"  class="form-control" name="pickupDate${taxdesc.count}" id="pickupDate${taxdesc.count}" value="${std.pickupDate}" /></td>
                                        <c:set var="valueNameRow" value="${count}"></c:set>
                                    </tr>                                                                                       
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div id="tr_FormulaAddRow" class="text-center" style="padding-top: 10px;display: none;">
                                <a class="btn btn-success" onclick="AddRow()">
                                    <i class="glyphicon glyphicon-plus"></i> Add
                                </a>
                            </div>
                            <c:forEach var="std" items="${listStockDetail}" varStatus="taxdesc"> 
                                 <c:if test="${taxdesc.last}">
                                <script>
                                        $("#counter").val(${taxdesc.count} + 1);
                                </script>
                                <input value="${taxdesc.count}" id="taxDescRows" name="taxDescRows" type="text" class="hidden" />
                            </c:if>                                               
                            </c:forEach>
                        </div>
                    </div><!-- End Row 3--><br>
                    <div class="row" >
                    <div class="col-xs-12"  style="padding-left: 20px;">
                        <input name="action" id="action" type="hidden" class="form-control" value="save" />
                        <div class="col-md-12 text-right">
                            <button type="submit" id="ButtonSave" name="ButtonSave" class="btn btn-success" >
                                <i class="fa fa-save"></i> Save             
                            </button>                           
                        </div>                                         
                    </div>   
                </div><!-- End Row 4-->
            </div>
        </div>
    </div>
    <div class="col-sm-1">
        <!--<div ng-include="'WebContent/Master/StockMenu.html'"></div>-->
    </div>
    </form>
                                   
<!--Search Product-->
<div class="modal fade" id="SearchProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Product</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="productTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th style="width:20%">Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <script>
                        productCode = [];
                    </script>
                    <tbody>
                        <c:forEach var="pro" items="${ListProductStock}">
                            <tr>
                                <td class="product-id hidden">${pro.id}</td>
                                <td class="product-code">${pro.code}</td>
                                <td class="product-name">${pro.name}</td>
                            </tr>
                        <script>
                            productCode.push({id: "${pro.id}", code: "${pro.code}", name: "${pro.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="" type="button" onclick="" class="btn btn-success" data-dismiss="modal">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--Search Staff-->
<div class="modal fade" id="SearchStaff" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Search Staff</h4>
            </div>
            <div class="modal-body">
                <script>
                    staff = [];
                </script>
                <table class="display" id="StaffTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th>staff Name</th>
                        </tr>
                    </thead>                  
                    <tbody>                  
                        <c:forEach var="item" items="${ListStaffStock}" varStatus="loop">
                            <tr class="packet">
                                <td class="staff-id hidden"><c:out value="${item.id}" /></td>
                                <td class="staff-code"><c:out value="${item.username}" /></td>
                                <td class="staff-name"><c:out value="${item.name}" /></td>                            
                            </tr>
                            <script>
                                staff.push({id: "${item.id}", code: "${item.username}", name: "${item.name}"});
                            </script>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="SearchStaffOK" name="SearchStaffOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchStaffClose" name="SearchStaffClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->
<!--Delete Stock Modal-->
<div class="modal fade" id="delStockModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Stock</h4>
            </div>
            <div class="modal-body" >
                
                <div id="delCodeStock"></div>
            </div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick=" return deleteStock()" class="btn btn-danger" data-dismiss="modal">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<c:if test="${! empty result}">
    <c:if test="${result =='success'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${result =='fail'}">        
        <script language="javascript">
           $('#textAlertDivNotSave').show();
        </script>
    </c:if>
    <c:if test="${result =='moreOne'}">        
        <script language="javascript">
           $('#textAlertMoreOne').show();
        </script>
    </c:if>
</c:if>
<script>
    var select = "";
    $(document).ready(function () {
        var cout = document.getElementById('counterTable');
        var type  = document.getElementById('Selecttype');
        <c:forEach var="type" items="${getType}">
            select += "<option value='${type.id}' ><c:out value='${type.name}' /></option>";
        </c:forEach>  
            
    });
</script>
<script type="text/javascript" src="js/stock.js"></script>