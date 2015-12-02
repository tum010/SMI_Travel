<%-- 
    Document   : OutboundSummary
    Created on : Nov 23, 2015, 5:04:17 PM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/OutboundSummary.js"></script>
<c:set var="listCity" value="${requestScope['city_list']}" />
<c:set var="listCountry" value="${requestScope['country_list']}" />
<c:set var="hotelList" value="${requestScope['HotelList']}" />
<c:set var="userList" value="${requestScope['listSale']}" />
<c:set var="listStatus" value="${requestScope['listStatus']}" />
<c:set var="listPayby" value="${requestScope['listPayby']}" />
<c:set var="listBank" value="${requestScope['listBank']}" />
<section class="content-header"  >
    <h4>
        <b>Report : Outbound Hotel Summary </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Outbound Hotel Summary</a></li>
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
                    <h3>Outbound Hotel Summary Report</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="HotelSummaryReportFrom" method="post" class="form-horizontal" >
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-left" >Country</label>
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
                                <label class="col-md-5 control-label text-left" >City</label>
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
                                <label class="col-md-5 control-label text-right" >Hotel <font style="color: red">*</font></label>
                                <div class="col-md-3 form-group" id="agentcodepanel">  
                                    <div class="input-group">
                                        <input name="InputId" id="InputId" type="hidden" class="form-control" value="" />
                                        <input type="text" class="form-control" id="InputHotelId" name="InputHotelId" value="" />
                                        <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#HotelModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3" id="agentnamepanel">
                                    <input name="InputHotelName" id="InputHotelName" type="text" class="form-control" value="" readonly="" />
                                </div>
                            </div> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="invfromdatepanel">
                                <label class="col-md-5 control-label text-right">From</label>
                                <div class="col-md-5">  
                                    <div class="form-group" id="DateFrom">
                                        <div class='input-group date'>
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
                                <label class="col-md-5 control-label text-right">To</label>
                                <div class="col-md-5">  
                                    <div class="form-group" id="DateTo">
                                        <div class='input-group date' >
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
                                <label class="col-md-5 control-label text-right" >Sale By</label>
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
                                <label class="col-md-5 control-label text-right" >Pay By</label>
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
                                <label class="col-md-5 control-label text-right" >Bank Transfer</label>
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
                                <label class="col-md-5 control-label text-right" >Status</label>
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
<!--                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label for="depart" class="col-sm-5 control-label text-right">Department</label>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <select id="department" name="department"  class="form-control">
                                            <option value="">--Select--</option>
                                            <option value="I">Wendy</option>
                                            <option value="O">Outbound</option>
                                            <option value="Inbound">Inbound</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>-->
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <div class="col-sm-7 text-right">
                                    <button type="button"  class="btn btn-success" id="btnPrint" onclick="printOutboundHotelSummary();"><span class="glyphicon glyphicon-print" ></span> Print</button>
                                </div>
                                <div class="col-sm-2 text-left">
                                    <button type="button" onclick="" class="btn btn-warning"><span class="glyphicon glyphicon-print"></span> Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>                
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="HotelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Hotel</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="hotelTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th style="width:20%">Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <script>
                        hotelCode = [];
                    </script>
                    <tbody>
                        <c:forEach var="hotel" items="${hotelList}">
                            <tr>
                                <td class="hotel-id hidden">${hotel.id}</td>
                                <td class="hotel-code">${hotel.code}</td>
                                <td class="hotel-name">${hotel.name}</td>
                            </tr>
                        <script>
                            hotelCode.push({id: "${hotel.id}", code: "${hotel.code}", name: "${hotel.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Script Product List table-->
            <script>
                $(document).ready(function () {
//                    alert("<%=new java.util.Date()%>");
                    $("#hotelTable tr").on('click', function () {//winit
                        var hotel_id = $(this).find(".hotel-id").html();
                        var hotel_code = $(this).find(".hotel-code").html();
                        var hotel_name = $(this).find(".hotel-name").html();
                        $("#InputId").val(hotel_id);
                        $("#InputHotelId").val(hotel_code);
                        $("#InputHotelName").val(hotel_name);
                        $("#HotelModal").modal('hide');
                    });

                    // hotelTable
                    var hotelTable = $('#hotelTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": true,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });

                    $('#hotelTable tbody').on('click', 'tr', function () {
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                        }
                        else {
                            hotelTable.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                        }
                    });

                    var hoteluser = [];
                    $.each(hotelCode, function (key, value) {
                        hoteluser.push(value.code);
                        hoteluser.push(value.name);
                    });

                    $("#InputHotelId").autocomplete({
                        source: hoteluser,
                        close:function( event, ui ) {
                           $("#InputHotelId").trigger('keyup');
                        }
                    });

                    $("#InputHotelId").on('keyup',function(){
                        var position = $(this).offset();
                        $(".ui-widget").css("top", position.top + 30);
                        $(".ui-widget").css("left", position.left);
                        var code = this.value.toUpperCase();
                        var name = this.value.toUpperCase();
                       // console.log("Name :"+ name);
                        $("#InputId,#InputHotelName").val(null);
                        $.each(productCode, function (key, value) {
                            if (value.code.toUpperCase() === code ) {  
                                $("#InputId").val(value.id);
                                $("#InputHotelId").val(value.code);
                                $("#InputHotelName").val(value.name);
                            }
                            else if(value.name.toUpperCase() === name){
                                $("#InputHotelId").val(value.code);
                                $("#InputId").val(value.id);
                                $("#InputHotelName").val(value.name);
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