<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ListProductStock" value="${requestScope['ListProductStock']}" />
<c:set var="ListStaffStock" value="${requestScope['ListStaffStock']}" />
<c:set var="getType" value="${requestScope['getType']}" />
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
    <div class="col-sm-2">
        <div ng-include="'WebContent/Master/StockMenu.html'"></div>
    </div>
    <form action="Stock.smi" method="post" id="StockForm" role="form" >
    <div class="col-sm-10">
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6" style="padding-right: 15px">
                <h4><b>Stock</b></h4>
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
                        <input type="text" class="form-control hidden" id="InputProductId" name="InputProductId" value="" />
                        <div class="col-xs-2 text-right"  style="padding-right: 25px;width: 140px;">
                            <label class="control-label">Product</lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="padding-left:5px;width: 160px;"> 
                            <div class="input-group" id="gr" >
                                <input type="text" class="form-control" id="InputProduct" name="InputProduct" value="" />
                                <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchProduct">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width: 200px;">
                            <input name="InputProductName" id="InputProductName" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-1 text-right"  style="width: 87px;" >
                            <label class="control-label">Staff</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width: 170px;" > 
                            <div class="input-group" id="gr" >
                                <input type="text" class="form-control" id="InputStaff" name="InputStaff" value="" />
                                <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#SearchStaff">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width: 200px;">
                            <input name="InputStaffName" id="InputStaffName" type="text" class="form-control" value="" />
                        </div>
                    </div>   
                </div><!-- End Row 1-->
                <div class="row" >
                    <div class="col-xs-12 ">
                        <div class="col-xs-2 text-right" style="width: 130px;">
                            <label class="control-label">Effective From</lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="width: 170px;" >
                            <div class='input-group date' >
                                <input name="InputEffectiveFromDate" id="InputEffectiveFromDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <div class="col-xs-3 text-right" style="width: 285px;">
                            <label class="control-label">Effective To</lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="padding-left: 17px;width: 170px;" >
                            <div class='input-group date' >
                                <input name="InputInputEffectiveToDate" id="InputInputEffectiveToDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
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
                             <textarea class="form-control" rows="3" id="descriptionStock" name="descriptionStock"></textarea>
                        </div>
                        <div class="col-xs-1 text-left" style="width: 90px;padding-left: 3px;padding-right: 0px;">
                            <label class="control-label">Add Date</lable>
                        </div>
                        <div class="col-md-3 form-group text-left" style="padding-left: 0px;width: 155px;">
                            <div class='input-group date' >
                                <input name="InputStockDate" id="InputStockDate" type="text" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />
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
                        <div class="col-xs-1 text-right" style="width: 130px;">
                            <label class="control-label">Prefix</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width: 163px;"> 
                            <input name="InputPrefix" id="InputPrefix" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-1 text-right"  style="width: 60px;">
                            <label class="control-label">Start</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" style="width: 163px;">  
                            <input name="InputStart" id="InputStart" type="text" class="form-control" value="" />
                        </div>
                        <div class="col-xs-2 text-right" style="width: 140px;">
                            <label class="control-label">Number Of Item</lable>
                        </div>
                        <div class="col-md-1 form-group text-left" > 
                            <input name="InputNumberOfItem" id="InputNumberOfItem" type="text" class="form-control" value="" placeholder="123"/>
                        </div>
                        <div class="col-xs-1 text-right"  >
                            <label class="control-label">Type</lable>
                        </div>
                        <div class="col-md-2 form-group text-left" > 
                            <select name="Selecttype" id="Selecttype" class="form-control">
                                <c:forEach var="type1" items="${getType}">
                                    <option value="${type1.name}"><c:out value="${type1.name}" /></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>   
                </div><!-- End Row 1-->
                <div class="row" >
                    <div class="col-xs-12" style="padding-right: 45px;">
                        <div class="col-md-8 text-right" >                                          
                        </div> 
                        <div class="col-md-2 text-right">
                            <button type="button"  id="ButtonAdd"  name="ButtonAdd" onclick=" return addItemList()" class="btn btn-primary btn-primary " style="width: 100px;">
                                <span id="SpanAdd" class="glyphicon glyphicon-print fa fa-plus-circle"></span> Add
                                <input type="hidden" name="action" id="action"/>
                            </button>                                          
                        </div> 
                        <div class="col-md-2 text-right" >
                            <button type="button" onclick="" class="btn btn-danger" id="SpanClearStock" name="SpanClearStock" style="width: 160px;">
                                <span  class="glyphicon glyphicon-refresh"></span> Clear Expire Stock
                            </button>
                        </div>
                    </div>   
                </div><!-- End Row 2--><br>
                <div class="row" style="padding-left: 0px">
                        <div class="col-xs-12 ">
                                    <table class="display" id="TaxInvoiceTable">
                                        <thead class="datatable-header">
                                            <tr>
                                                <th style="width: 5%">No</th>                                   
                                                <th style="width: 10%">Code</th>
                                                <th style="width: 15%">Type</th>
                                                <th style="width: 20%">Pay Status</th>
                                                <th style="width: 15%">Item Status</th>
                                                <th style="width: 8%">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <input type="text" class="hidden" id="counter" name="counter" value="1" />
                                            <c:set var="count" value="1"></c:set>
                                            <%--<c:forEach var="tax" items="${ListTaxInvoice}" varStatus="taxdesc">--%>
                                            <tr>
                                                <td><input type="text" class="form-control" name="id${count}" id="id${count}" value="${count}" readonly/></td>
                                                <td><input type="text"  class="form-control" name="codeItemList${count}" id="codeItemList${count}" value=""/></td>
                                                <td>       
                                                    <select id="SeleteTypeItemList${count}" name="SeleteTypeItemList${count}" class="form-control">
                                                        <c:forEach var="type" items="${getType}">
                                                            <option value="${type.name}"><c:out value="${type.name}" /></option>
                                                        </c:forEach>
                                                    </select>                                             
                                                </td>                                
                                                <td><input type="text" class="form-control" name="payStatusItemList${count}" id="payStatusItemList${count}" value="" readonly/></td>
                                                <td><input type="text" class="form-control" name="itemStatusItemList${count}" id="itemStatusItemList${count}" value="" readonly/></td>
                                                <td class="text-center">                                          
                                                    <a href="#" onclick="deleteItemListRow(${count})"  data-toggle="modal" data-target="" class="remCF" id="ButtonRemove${count}">
                                                        <span id="Spanremove${count}" class="glyphicon glyphicon-remove deleteicon"  onclick="" data-toggle="modal" data-target="#delStockModal"></span>
                                                    </a>
                                                    
                                                </td>
                                                <c:set var="valueNameRow" value="${count}"></c:set>
                                            </tr>
                                            <c:if test="${taxdesc.last}">
                                                <script>
                                                    $("#counter").val(${taxdesc.count} + 1);
                                                </script>
                                                <input value="${taxdesc.count}" id="taxDescRows" name="taxDescRows" type="text" class="hidden" />
                                            </c:if>                                            
                                            <%--</c:forEach>--%>
                                        </tbody>
                                    </table> 
                                </div>
                    </div><!-- End Row 3--><br>
                    <div class="row" >
                    <div class="col-xs-12"  style="padding-left: 20px;">
                        <div class="col-md-12 text-right">
                            <button type="submit" id="ButtonSave" name="ButtonSave" class="btn btn-success">
                                <i class="fa fa-save"></i> Save             
                            </button>                           
                        </div>                                         
                    </div>   
                </div><!-- End Row 4-->
            </div>
        </div>
    </div>
    </form>
</div>
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
            <!-- Script Daytour List table-->
            <script>
                $(document).ready(function () {
                    $('.date').datetimepicker();
                    $('.datemask').mask('0000-00-00');
                    $("#productTable tr").on('click', function () {//winit
                        $("#SearchProduct").modal('hide');
                        var tour_id = $(this).find(".product-id").html();
                        var tour_code = $(this).find(".product-code").html();
                        var tour_name = $(this).find(".product-name").html();
                        $("#InputProductId").val(tour_id);
                        $("#InputProduct").val(tour_code);
                        $("#InputProductName").val(tour_name);
                    });

                    // tourTable
                    var tourTable = $('#productTable').dataTable({bJQueryUI: true,
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
                            tourTable.$('tr.row_selected').removeClass('row_selected');
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

                        $("#InputProduct").autocomplete({
                            source: availableTags,
                            close: function (event, ui) {
                                $("#InputProduct").trigger('keyup');
                            }
                        });


                        $("#InputProduct").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var name = this.value;
                            var code = this.value.toUpperCase();
                            $("#InputProductName").val(null);
                            $.each(productCode, function (key, value) {
                                if (name === value.name) {
                                    $("#InputProduct").val(value.code);
                                    code = $("#InputProduct").val().toUpperCase();
                                }
                                if (value.code.toUpperCase() === code) {
                                    $("#InputProductId").val(value.id);
                                    $("#InputProductName").val(value.name);
                                }
                            }); //end each productCode
                        }); // end InputproductCode keyup
                    }); // end AutoComplete productCode

                });
                
            </script>
            <div class="modal-footer">
                <button id="" type="button" onclick="" class="btn btn-success">OK</button>
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
                <table class="display" id="StaffTable">
                    <thead class="datatable-header">
                        <tr>
                            <th>Code</th>
                            <th> Staff Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        staff = [];
                    </script>
                        <c:forEach var="item" items="${ListStaffStock}" varStatus="loop">
                            <tr class="packet">
                                <td class="staff-code"><c:out value="${item.id}" /></td>
                                <td class="staff-name"><c:out value="${item.name}" /></td>                            
                            </tr>
                            <script>
                                staff.push({id: "${item.id}", code: "${item.id}", name: "${item.name}"});
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
                <div id="delCodeStock">Are You Sure?</div>
            </div>
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
    $(".datemask").mask('00-00-0000', {reverse: true});
    
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    
//    $('#StaffTable').dataTable({bJQueryUI: true,
//        "sPaginationType": "full_numbers",
//        "bAutoWidth": true,
//        "bFilter": true,
//        "bPaginate": true,
//        "bInfo": false,
//        "bLengthChange": false,
//        "iDisplayLength": 10
//    });
});
</script>
<script type="text/javascript" src="js/stock.js"></script>
