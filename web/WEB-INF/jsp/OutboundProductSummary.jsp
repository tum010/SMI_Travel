<%-- 
    Document   : OutboundProductSummary
    Created on : Nov 23, 2015, 5:40:05 PM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">

<c:set var="userList" value="${requestScope['listSale']}" />
<c:set var="listStatus" value="${requestScope['listStatus']}" />
<c:set var="listCountry" value="${requestScope['listCountry']}" />
<c:set var="listCity" value="${requestScope['listCity']}" />
<c:set var="product_list" value="${requestScope['listProduct']}" />
<c:set var="listPayby" value="${requestScope['listPayby']}" />
<c:set var="listBank" value="${requestScope['listBank']}" />
<section class="content-header"  >
    <h4>
        <b>Report : Outbound Product Summary </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Outbound Product Summary</a></li>
    </ol>
</section>
<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/OutboundSummaryMenu.html'"></div>
            </div>
            <div class="form-group">
                <div class="col-md-6">
                    <h3>Product Summary </h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form action="BillAirAgent.smi" method="post" id="BillAirAgent" name="BillAirAgent" role="form">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Country</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectCountry" id="SelectCountry"  class="form-control selectize">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listCountry}" >
                                                <c:if test="">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >City</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectCity" id="SelectCity"  class="form-control selectize">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listCity}" >
                                                <c:if test="}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Product <font style="color: red">*</font></label>
                                <div class="col-md-3 form-group" id="agentcodepanel">  
                                    <div class="input-group">
                                        <input name="InputId" id="InputId" type="hidden" class="form-control" value="" />
                                        <input type="text" class="form-control" id="InputProductId" name="InputProductId" value="" />
                                        <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#ProductModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3" id="agentnamepanel">
                                    <input name="InputProductName" id="InputProductName" type="text" class="form-control" value="" readonly="" />
                                </div>
                            </div> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="invfromdatepanel">
                                <label class="col-md-6 control-label text-right">From</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date invfromdate' id='invDateFrom'>
                                            <input type='text' id="FromDate" name="FromDate" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="invtodatepanel">
                                <label class="col-md-6 control-label text-right">To</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date invtodate' id='invDateTo'>
                                            <input type='text' id="ToDate" name="ToDate" class="form-control datemask" placeholder="YYYY-MM-DD" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>                                     
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Sale By</label>
                                <div class="col-md-3 form-group">  
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="salebyId" name="salebyId" value=""/>
                                        <input type="text" class="form-control" id="salebyUser" name="salebyUser" value="" />
                                        <span class="input-group-addon" id="saleby_modal"  data-toggle="modal" data-target="#SaleByModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="salebyName" name="salebyName" value="" readonly="">
                                </div>
                            </div>   
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Pay By</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectPayby" id="SelectPayby" class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listPayby}" >
                                                <c:if test="}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>                                   
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Bank Transfer</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectBank" id="SelectBank" class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listBank}" >
                                                <c:if test="}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Status</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectStatus" id="SelectStatus" class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listStatus}" >
                                                <c:if test="}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>       
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="rept"></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <button type=button onclick="printBillAirAgent();" id="printbutton" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                </form>                   
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="SaleByModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Sale By</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="SaleByTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        saleby = [];
                    </script>
                    <c:forEach var="table" items="${userList}">
                        <tr>
                            <td class="saleby-id hidden">${table.id}</td>
                            <td class="saleby-user">${table.username}</td>
                            <td class="saleby-name">${table.name}</td>
                        </tr>
                        <script>
                            saleby.push({id: "${table.id}", username: "${table.username}", name: "${table.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="SaleByModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<div class="modal fade" id="ProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                        <c:forEach var="pro" items="${product_list}">
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
                        $("#ProductModal").modal('hide');
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
//
//                    var availableTags = [];
//                    $.each(productCode, function (key, value) {
//                        availableTags.push(value.code);
//                        availableTags.push(value.name);
//                    });
//
//                    $("#InputProductId").autocomplete({
//                        source: availableTags,
//                        close: function (event, ui) {
//                            $("#InputProductId").trigger('keyup');
//                        }
//                    });
//
//                    $("#InputProductId").keyup(function () {
//                        var position = $(this).offset();
//                        $(".ui-widget").css("top", position.top + 30);
//                        $(".ui-widget").css("left", position.left);
//                        var name = this.value.toUpperCase();
//                        var code = this.value.toUpperCase();
//                        $("#InputProductName,#InputId,#InputProductName").val(null);
//                        $.each(productCode, function (key, value) {
//                            if (value.name.toUpperCase() === name ) {  
//                                $("#InputId").val(value.id);
//                                $("#InputProductId").val(value.code);
//                                $("#InputProductName").val(value.name);
//                            }
//                            else if(value.code.toUpperCase() === code){
//                                $("#InputId").val(value.id);
//                                $("#InputProductId").val(value.code);
//                                $("#InputProductName").val(value.name);
//                            }
//                        }); 
//                    }); 
                    
                    var productuser = [];
                    $.each(productCode, function (key, value) {
                        productuser.push(value.code);
                        productuser.push(value.name);
                    });

                    $("#InputProductId").autocomplete({
                        source: productuser,
                        close:function( event, ui ) {
                           $("#InputProductId").trigger('keyup');
                        }
                    });

                    $("#InputProductId").on('keyup',function(){
                        var position = $(this).offset();
                        $(".ui-widget").css("top", position.top + 30);
                        $(".ui-widget").css("left", position.left);
                        var code = this.value.toUpperCase();
                        var name = this.value.toUpperCase();
                       // console.log("Name :"+ name);
                        $("#InputId,#InputProductName").val(null);
                        $.each(productCode, function (key, value) {
                            if (value.code.toUpperCase() === code ) {  
                                $("#InputId").val(value.id);
                                $("#InputProductId").val(value.code);
                                $("#InputProductName").val(value.name);
                            }
                            else if(value.name.toUpperCase() === name){
                                $("#InputProductId").val(value.code);
                                $("#InputId").val(value.id);
                                $("#InputProductName").val(value.name);
                            }
                        }); 
                    }); 
                });        
            </script>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<script type="text/javascript">
$(document).ready(function() {
    $('.date').datetimepicker();
    $('.datemask').mask('0000-00-00', {reverse: true});
    $('.spandate').click(function() {
           var position = $(this).offset();
           console.log("positon :" + position.top);
           $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

       });
});
</script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/OutboundProductSummary.js"></script> 