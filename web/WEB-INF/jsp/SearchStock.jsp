<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ListProductStock" value="${requestScope['ListProductStock']}" />
<c:set var="listStock" value="${requestScope['listStock']}" />
<c:set var="listStockDetail" value="${requestScope['listStockDetail']}" />
<c:set var="stockClass" value="${requestScope['stock']}" />
<c:set var="stockSum" value="${requestScope['stockSummary']}" />
<c:set var="stockSumDetail" value="${requestScope['stockSumDetail']}" />
<c:set var="ListItemStatus" value="${requestScope['ListItemStatus']}" />
<c:set var="itemStatus" value="${requestScope['itemStatus']}" />
<c:set var="payStatus" value="${requestScope['payStatus']}" />
<c:set var="expire" value="${requestScope['expire']}" />
<c:set var="createDate" value="${requestScope['createDate']}" />
<c:set var="EffecttiveFrom" value="${requestScope['EffecttiveFrom']}" />
<c:set var="EffectiveTo" value="${requestScope['EffectiveTo']}" />
<c:set var="result" value="${requestScope['result']}" />

<section class="content-header" >
    <h1>
        Master Stock
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Master </a></li>          
        <li class="active"><a href="#">Search Stock</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app="">
    <form action="SearchStock.smi" method="post" id="SearchStockForm" role="form" onsubmit="validFrom()">
    <div class="col-sm-1">
<!--        <div ng-include="'WebContent/Master/StockMenu.html'"></div>-->
    </div>
    <div class="col-sm-10">
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
                <h4><b>Search Stock</b></h4>
            </div>
        </div>
        <hr/>
        <div class="row" >
            <!--Alert Save -->
        
            <div class="col-xs-12 ">
                <div class="col-xs-1 text-right" style="width: 130px;"> 
                    <label class="control-label">Product</lable>
                </div>
                
                <c:set var="setProductAdd" value="" />
                <c:if test="${createDate != ''}">
                    <c:set var="setProductAdd" value="${createDate}" />
                </c:if> 
                <c:if test="${createDate == ''}">
                     <c:set var="setProductAdd" value="${stockClass.createDate}" />
                </c:if>
                
                <c:set var="setFrom" value="" />
                <c:if test="${EffecttiveFrom != ''}">
                    <c:set var="setFrom" value="${EffecttiveFrom}" />
                </c:if> 
                <c:if test="${EffecttiveFrom == ''}">
                     <c:set var="setFrom" value="${stockClass.effectiveFrom}" />
                </c:if>
                
                <c:set var="setTo" value="" />
                <c:if test="${EffectiveTo != ''}">
                    <c:set var="setTo" value="${EffectiveTo}" />
                </c:if> 
                <c:if test="${EffectiveTo == ''}">
                     <c:set var="setTo" value="${stockClass.effectiveTo}" />
                </c:if>
                
                <input name="InputId" id="InputId" type="hidden" class="form-control" value="${stockClass.product.id}" />
                <div class="col-md-2 form-group text-left" > 
                    <div class="input-group" id="gr" >
                        <input type="text" class="form-control" id="InputProductId" name="InputProductId" value="${stockClass.product.code}" />
                        <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchProduct">
                            <span class="glyphicon-search glyphicon"></span>
                        </span>
                    </div>
                </div>
                <div class="col-md-2 form-group text-left" > 
                    <input name="InputProductName" id="InputProductName" type="text" class="form-control" value="${stockClass.product.name}" readonly="" />
                </div>
                <div class="col-xs-1 text-right" style="width: 100px;padding-right: 10px;padding-left: 0px;">
                    <label class="control-label">Pay Status</lable>
                </div>
                <div class="col-md-2 form-group text-left" style="padding-left: 0px;">
                    <select name="SelectPayStatus" id="SelectPayStatus" class="form-control">
                         <c:set var="select" value="" />
                            <c:if test="${payStatus == '1'}">
                                <c:set var="select" value="selected" />
                            </c:if>
                            <c:if test="${payStatus == '0'}">
                                <c:set var="select" value="selected" />
                            </c:if>
                        <option id="" value="">--status--</option>
                        <option id="" value="1" ${select}>Pay</option>
                        <option id="" value="0" ${select}>Not Paid</option>
                    </select>
                </div>
                <div class="col-xs-1 text-right" style="width: 80px;padding-right: 0px;padding-left: 0px;">
                    <label class="control-label">Item Status</lable>
                </div>
                <div class="col-md-2 form-group text-left" style="padding-left: 8px;width: 180px;">
                    <select name="SelectItemStatus" id="SelectItemStatus" class="form-control">
                        <option value=""  selected="selected">--status--</option>
                        <c:forEach var="sta" items="${ListItemStatus}">
                            <c:set var="select" value="" />
                            <c:if test="${sta.id == itemStatus}">
                                <c:set var="select" value="selected" />
                            </c:if>
                            <option value="${sta.id}" ${select}>${sta.name}</option>   
                        </c:forEach>
                    </select>
                </div>
            </div>   
        </div><!-- End Row 1-->
        <div class="row" >
            <div class="col-xs-1 text-right" style="width: 143px;"> 
                <label class="control-label">Add Date</lable>
            </div>
            <div class="col-md-3 form-group text-left" style="width: 170px;" >
                <div class='input-group date' >
                    <input name="InputStockDate" id="InputStockDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${setProductAdd}" />
                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <div class="col-xs-2 text-right" style="width: 265px;padding-right: 0px;padding-left: 7px;" >
                <label class="control-label">Effective From</lable>
            </div>
            <div class="col-md-2 form-group text-left" style="padding-left: 8px;"> 
                <div class='input-group date' id="DateFrom">
                    <input name="InputEffectiveFromDate" id="InputEffectiveFromDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${setFrom}" />
                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
            <div class="col-xs-1 text-right" style="width: 85px;padding-right: 0px;padding-left: 0px;">
                <label class="control-label">Effective To</lable>
            </div>
            <div class="col-md-2 form-group text-left" style="padding-left: 6px;"> 
                <div class='input-group date' id="DateTo">
                    <input name="InputInputEffectiveToDate" id="InputInputEffectiveToDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${setTo}" />
                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
            </div>
        </div><!-- End Row 2-->
        <div class="row" >
            <c:set var="expireStockShow" value="" />
            <c:set var="expireStockNotShow" value="checked" />
                <c:if test="${expire == '1'}">
                    <c:set var="expireStockShow" value="checked" />
                    <c:set var="expireStockNotShow" value="" />
                </c:if>
                <c:if test="${expire == '0'}">
                    <c:set var="expireStockNotShow" value="checked" />
                    <c:set var="expireStockShow" value="" />
                </c:if>
            <div class="col-xs-12" >
                <div class="col-md-1 text-right" style="padding-left: 20px;width: 130px;">
                    <label class="control-label">Expire </lable>
                </div>
                <div class="col-md-2 text-left" style="width: 120px;padding-top: 6px;">
                    <input type="radio" name="SelectExpire" id="SelectExpireNotShow" value="0" ${expireStockNotShow}> Not Show
                </div>
                <div class="col-md-5 text-left" style="padding-left: 0px;padding-top: 6px;" >
                    <input type="radio" name="SelectExpire" id="SelectExpireShow" value="1" ${expireStockShow}> Show
                </div>
                <div class="col-md-2 text-right" style="padding-right: 30px;">
                    <input type="hidden" name="action" id="action">
                    <a href="Stock.smi?action=new">
                    <button type="button"  id="ButtonNew"  name="ButtonNew"  class="btn btn-success" style="width: 100px;">
                        <span id="SpanSearch" class="glyphicon glyphicon-plus"></span> New
                    </button>   
                    </a>
                </div> 
                <div class="col-md-2 text-right" style="padding-right: 30px;">
                    <input type="hidden" name="action" id="action">
                    <button type="button"  id="ButtonSearch"  name="ButtonSearch" onclick="searchAction()" class="btn btn-primary" style="width: 100px;">
                        <span id="SpanSearch" class="glyphicon glyphicon-search"></span> Search
                    </button>                                          
                </div>                   
            </div>   
        </div><!-- End Row 3--><br>
        <div class="row" >
            <div class="col-xs-12" >
                 <input type="hidden" value="" id="stockIdView" name="stockIdView">
                <table class="display" id="StockTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden" >id</th>
                            <th style="width: 15%">Product</th>                                   
                            <th style="width: 20%">Staff</th>
                            <th style="width: 15%">Add Date</th>
                            <th style="width: 15%">Effective From</th>
                            <th style="width: 15%">Effective To</th>
                            <th style="width: 10%">Action</th>
                        </tr>
                    </thead>
                    <tbody>                  
                    <c:forEach var="stock" items="${listStock}" varStatus="stockCount">    
                        <tr>
                            <td class="hidden">${stock.id}</td>
                            <td>${stock.product.code}</td>
                            <td>${stock.staff.name}</td>
                            <td>${stock.createDate}</td>                                
                            <td>${stock.effectiveFrom}</td>
                            <td>${stock.effectiveTo}</td> 
                            <td class="text-center">
                                <a href="Stock.smi?InputStockId=${stock.id}&action=edit">
                                    <span id="RefStockTableButtonEdit" name="RefStockTableButtonEdit" class="glyphicon glyphicon-edit editicon"></span>
                                </a>
                                <a  href="#" >
                                    <span  class="glyphicon glyphicon-list-alt" id="SpanEdit${stockCount.count}" onclick="viewStockDetailAction('${stock.id}')"  ></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>    
            </div>
        </div><br>
        <div class="panel panel-default">            
            <div class="panel-heading">
                Item List
            </div>
            <div class="panel-body">
                <table class="display" id="StockSummary">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width: 15%">Number Of Item</th>                                   
                            <th style="width: 10%">New</th>
                            <th style="width: 15%">Cancel</th>
                            <th style="width: 20%">Bill</th>
                            <th style="width: 15%">In Use</th>
                        </tr>
                    </thead>
                    <tbody>
                        <td class="text-right">${stockSum.numOfItem}</td>
                        <td class="text-right">${stockSum.normal}</td>
                        <td class="text-right">${stockSum.cancel}</td>
                        <td class="text-right">${stockSum.bill}</td>
                        <td class="text-right">${stockSum.inuse}</td>
                    </tbody>
                </table><br>       
                <table class="display" id="ItemListTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width: 5%">No</th>                                   
                            <th style="width: 13%">Code</th>
                            <th style="width: 6%">Type</th>
                            <th style="width: 10%">Ref No</th>
                            <th style="width: 10%">Pick Up</th>
                            <th style="width: 12%">Pick Up Date</th>
                            <th style="width: 12%">Pay Status</th>
                            <th style="width: 12%">Item Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="stockDetail" items="${stockSum.itemList}" varStatus="num1">
                            
                        <tr>
                            <td>${num1.count}</td>
                            <td>${stockDetail.code}</td>
                            <td>${stockDetail.typeName}</td>
                            <td>${stockDetail.refNo}</td>                                
                            <td>${stockDetail.pickup}</td>
                            <td>${stockDetail.pickupDate}</td>
                            <td>
                                <c:set var="pay" value="" />
                                <c:if test="${stockDetail.payStatusName == 0}">
                                    <c:set var="pay" value="No Paid" />
                                </c:if> 
                                <c:if test="${stockDetail.payStatusName == 1}">
                                    <c:set var="pay" value="Pay" />
                                </c:if>
                                ${pay}
                            </td>
                            <td>
                                <c:if test="${stockDetail.itemStatus == 'REFUND'}">
                                    <font style="color: red;"> ${stockDetail.itemStatus} </font>
                                </c:if>
                                <c:if test="${stockDetail.itemStatus != 'REFUND'}">
                                   ${stockDetail.itemStatus}
                                </c:if>
                            </td>                            
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>           
            </div>
        </div>
    </div>
    <div class="col-sm-1">
<!--        <div ng-include="'WebContent/Master/StockMenu.html'"></div>-->
    </div>
    </form>
</div>

<!--Search Product-->
<div class="modal fade" id="SearchProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Search Product</h4>
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
            <!-- Script Product List table-->
            <script>
                $(document).ready(function () {
//                    alert("<%=new java.util.Date()%>");
                    $("#productTable tr").on('click', function () {//winit
                        $("#SearchProduct").modal('hide');
                        var product_id = $(this).find(".product-id").html();
                        var product_code = $(this).find(".product-code").html();
                        var product_name = $(this).find(".product-name").html();
                        $("#InputId").val(product_id);
                        $("#InputProductId").val(product_code);
                        $("#InputProductName").val(product_name);
                    });

                    // productTable
                    var productTable = $('#productTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": true,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });

                    $('#productTable tbody').on('click', 'tr', function () {
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                        }
                        else {
                            productTable.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                        }
                    });
                    // ON KEY INPUT AUTO SELECT PRODUCTCODE
                    $(function () {
                        var availableTags = [];
                        $.each(productCode, function (key, value) {
                            availableTags.push(value.code);
                            if (!(value.name in availableTags)) {
                                availableTags.push(value.name);
                            }
                        });

                        $("#InputProductId").autocomplete({
                            source: availableTags,
                            close: function (event, ui) {
                                $("#InputProductId").trigger('keyup');
                            }
                        });

                        $("#InputProductId").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var name = this.value;
                            var code = this.value.toUpperCase();
                            $("#InputProductName").val(null);
                            $.each(productCode, function (key, value) {
                                if (name === value.name) {
                                    $("#InputProductId").val(value.code);
                                    code = $("#InputProductId").val().toUpperCase();
                                }
                                if (value.code.toUpperCase() === code) {
                                    $("#InputId").val(value.id);
                                    $("#InputProductName").val(value.name);
                                }
                            }); //end each productCode
                        }); // end InputproductCode keyup
                    }); // end AutoComplete productCode
                });        
      
                
                
            </script>
            <div class="modal-footer">
                <button id="SearchProductOK" name="SearchProductOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="SearchProductClose" name="SearchProductClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->
<!--Search Staff-->
<div class="modal fade" id="SearchStaff" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Search Staff</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="StaffTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Code</th>
                            <th> Staff Name</th>
                        </tr>
                    </thead>
                    <tbody>
                            <tr class="packet">
                                <td class="">XXX
                                <td>XXXXX</td>
                            </tr>
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
<script type="text/javascript">
 
 $(document).ready(function () {
//    	$(".loader").fadeOut("slow");
    $('.date').datetimepicker();
    $('.datemask').mask('0000-00-00', {reverse: true});
     $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
    $('#ProductTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    $('#StaffTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    $('#StockTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": true,
        "iDisplayLength": 10
    });
    $('#ItemListTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": true,
        "iDisplayLength": 50
    });
    $('#StockSummary').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": false,
        "bPaginate": false,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    $("#SearchStockForm")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    InputEffectiveFromDate: {
                        trigger: 'focus keyup change',
                            validators: {
                                date: {
                                    format: 'YYYY-MM-DD',
                                    max: 'InputInputEffectiveToDate',
                                    message: 'The Date From is not a valid'
                                }
                            }
                    },
                    InputInputEffectiveToDate: {
                        trigger: 'focus keyup change',
                            validators: {
                                date: {
                                    format: 'YYYY-MM-DD',
                                    min: 'InputEffectiveFromDate',
                                    message: 'The Date To is not a valid'
                                }
                            }
                    }
                }
            }).on('success.field.fv', function (e, data) {
                alert("1");
                if (data.field === 'InputEffectiveFromDate' && data.fv.isValidField('InputInputEffectiveToDate') === false) {
                        data.fv.revalidateField('InputInputEffectiveToDate');
                    }

                    if (data.field === 'InputInputEffectiveToDate' && data.fv.isValidField('InputEffectiveFromDate') === false) {
                        data.fv.revalidateField('InputEffectiveFromDate');
                    }
            });
    $('#DateFrom').datetimepicker().on('dp.change', function (e) {
        $('#SearchStockForm').bootstrapValidator('revalidateField', 'InputEffectiveFromDate');
    });
    $('#DateTo').datetimepicker().on('dp.change', function (e) {
        $('#SearchStockForm').bootstrapValidator('revalidateField', 'InputInputEffectiveToDate');
    });
});

</script>
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
<script type="text/javascript" src="js/searchStock.js"></script>
<!--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>-->