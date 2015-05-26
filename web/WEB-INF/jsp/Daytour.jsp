<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="daytourBookingList" value="${requestScope['DaytourBookingList']}" />
<c:set var="OtherLists" value="${requestScope['OtherLists']}" />
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">

<section class="content-header" >
    <h1>
        Booking - Day Tours
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">Day Tours</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Book/BookMenu.html'"></div>
            <input hidden="" value="${booking_size[0]}" id="input-airticket_size">
            <input hidden="" value="${booking_size[1]}" id="input-hotel_size">
            <input hidden="" value="${booking_size[2]}" id="input-other_size">
            <input hidden="" value="${booking_size[3]}" id="input-land_size">
            <input hidden="" value="${booking_size[4]}" id="input-passenger_size">
            <input hidden="" value="${booking_size[5]}" id="input-billable_size">
            <input hidden="" value="${booking_size[6]}" id="input-daytour_size">
        </div>
        
       
        <div class="col-sm-10">
            <input type="hidden" value="${master.customer.MInitialname.name}" id="initialname_tmp">
            <input type="hidden" value="${master.customer.firstName}" id="firstname_tmp">
            <input type="hidden" value="${master.customer.lastName}" id="lastname_tmp">  
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Day Tours</b></h4>
                </div>
                <div class="col-sm-2 col-sm-offset-4 text-right" style="padding-left: 26px">
                    <a id="Add" name="Add" href="DaytourDetail.smi?action=new&referenceNo=${param.referenceNo}">
                        <button id="ButtonAdd" name="ButtonAdd" type="button" class="btn btn-success" onclick="">
                            <span id="SpanAdd" name="SpanAdd" class="glyphicon glyphicon-plus"></span>Add
                        </button>
                    </a>
                </div>
            </div>
            <hr/>
            <!-- Day Tours Table-->
            <table class="display" id="HotelTable">
                <thead class="datatable-header">
                    <tr>
                        <th style="width:20%">Tour Code</th>
                        <th style="width:15%">Date</th>
                        <th style="width:30%">Pick Up</th>
                        <th style="width:12%">Qty</th>
                        <th style="width:15%">Price</th>
                        <th style="width:8%">Action</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <c:forEach var="item" items="${daytourBookingList}">
                        <c:set var="sumPrice" value="0" />
                        <c:set var="sumQty" value="0" />
                        <c:set var="colourStatus" value="" />
                        <c:if test="${item.MItemstatus.id == 2}">
                            <c:set var="colourStatus" value="style='background-color: #FFD3D3'" />
                        </c:if>
                        <tr ${colourStatus} >
                            <td>${item.daytour.code}</td>
                            <td class="text-center">${item.tourDate}</td>
                            <td>${item.place.place}</td>
                            <c:forEach var="price" items="${item.daytourBookingPrices}">
                                <c:set var="sumPrice" value="${sumPrice + (price.price * price.qty)}" />
                                <c:set var="sumQty" value="${sumQty + price.qty}" />
                                
                            </c:forEach>
                            <td class="text-center money"><c:out value="${sumQty}"/></td>
                            <td class="text-right money"><c:out value="${sumPrice}" /></td>
                            
                            <td class="text-center">
                                <a href="DaytourDetail.smi?referenceNo=${param.referenceNo}&action=edit&daytourBooking=${item.id}"><span class="glyphicon glyphicon-edit editicon"></span></a>
                                    <c:if test="${item.MItemstatus.id == 2}">
                                    <span id="SpanGlyphiconAdd" class="glyphicon glyphicon-plus addicon"   onclick="setEnableDaytour('${item.id}', '${item.daytour.code}');" data-toggle="modal" data-target="#DaytourModal" ></span>
                                </c:if>
                                <c:if test="${item.MItemstatus.id == 1}">
                                    <span id="SpanGlyphiconRemove" class="glyphicon glyphicon-remove deleteicon" onclick="setDisableDaytour('${item.id}', ' ${item.daytour.code}');" data-toggle="modal" data-target="#DaytourModal" ></span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div><!--row1-->
    
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-10">
                <label class="control-label"><h4>Other</h4></label>
        </div>
    </div>
    
    <div class="row">
        <div class="col-sm-2"></div>
        <div class="col-sm-10">
            <table class="display" id="OtherTable">
                <thead class="datatable-header">
                    <tr>
                        <th style="width:15%" rowspan="2">Date</th>
                        <th style="width:20%" rowspan="2">Product Name</th>
                        <th style="width:15%" colspan="3">Adult</th>
                        <th style="width:15%" colspan="3">Child</th>
                        <th style="width:15%" colspan="3">Infant</th>
                        <th style="width:10%" rowspan="2">Cur</th>
                        <th style="width:10%" rowspan="2">Amount</th>
                    </tr>
                    <tr>
                        <th style="width:5%">Cost</th>
                        <th style="width:5%">Qty</th>
                        <th style="width:5%">Price</th>
                        <th style="width:5%">Cost</th>
                        <th style="width:5%">Qty</th>
                        <th style="width:5%">Price</th>
                        <th style="width:5%">Cost</th>
                        <th style="width:5%">Qty</th>
                        <th style="width:5%">Price</th>
               
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="table" items="${OtherLists}">
                        <tr>
                            <td class="tdcenter">${table.otherDate}</td>
                            <td>${table.product.name}</td>
                            <td class="tdright moneyformat"> ${table.adCost}</td>
                            <td class="tdcenter moneyformat"> ${table.adQty}</td>
                            <td class="tdright moneyformat"> ${table.adPrice}</td>
                            <td class="tdright moneyformat"> ${table.chCost}</td>
                            <td class="tdcenter moneyformat"> ${table.chQty}</td>
                            <td class="tdright moneyformat"> ${table.chPrice}</td>
                            <td class="tdright moneyformat"> ${table.inCost}</td>
                            <td class="tdcenter moneyformat"> ${table.inQty}</td>
                            <td class="tdright moneyformat"> ${table.inPrice}</td>
                            <td class="tdcenter">${master.currency}</td>
                            <td class="tdright moneyformat"> ${(table.adPrice * table.adQty) + 
                                                               (table.chPrice * table.chQty) + 
                                                               (table.inPrice * table.inQty)}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div><!--row3-->
</div>

<div class="modal fade" id="DaytourModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="Daytour.smi" method="post" id="DaytourForm" class="form-horizontal"  role="form">            
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 id="titleDaytourModal" class="modal-title"> Booking Day Tours </h4>
                </div>
                <div class="modal-body" id="modalText" name="modalText"></div>
                <input type="hidden" id="daytourId" name="daytourId"/>
                <input type="hidden" id="daytourCode" name="tourCode"/>
                <input type="hidden" id="referenceNo" name="referenceNo"/>
                <input type="hidden" id="action" name="action" />
                <div class="modal-footer">
                    <button type="button" id="disableModal" name="disableModal" class="btn btn-danger">Disable</button>
                    <button type="button" id="enableModal" name="enableModal" class="btn btn-success">Enable</button>
                    <button type="button" id="closeModal" name="closeModal" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script language="javascript">
    function setformatmoney(){
        $('#OtherTable tr td.moneyformat').each(function() {
            var innerHTML = $(this).html();
            $(this).html(numberWithCommas(innerHTML));       
        });
      }
    function setEnableDaytour(id, code) {
        $("#titleDaytourModal").html("Enable Booking Daytour");
//        $("#referenceNo").val("${param.referenceNo}");
//        $("#daytourId").val(id);
//        $("#action").val("enable");
        $('#modalText').html("Are you sure to enable booking daytour  : " + code + " ? ");
        $("#disableModal").hide();
        $("#enableModal").show();
        $("#enableModal").click(function (e) {
            $.ajax({
                url: 'Daytour.smi?daytourId=' + id + '&referenceNo=' +${param.referenceNo} + '&action=enable',
                type: 'POST',
                success: function () {
                    location.reload();
                },
                error: function () {
                    console.log("error disable");
                }
            });
        });
    }
    function setDisableDaytour(id, code) {
        $("#titleDaytourModal").html("Disable Booking Daytour");
//        $("#referenceNo").val("${param.referenceNo}");
//        $("#daytourId").val(id);
//        $("#action").val("disable");
        $('#modalText').html("Are you sure to disabled booking daytour  : " + code + " ? ");
        $("#enableModal").hide();
        $("#disableModal").show();
        $("#disableModal").click(function (e) {
            $.ajax({
                url: 'Daytour.smi?daytourId=' + id + '&referenceNo=' +${param.referenceNo} + '&action=disable',
                type: 'POST',
                success: function () {
                    location.reload();
                },
                error: function () {
                    console.log("error disable");
                }
            });
        });
    }

    $(document).ready(function () {
        
        var table = $('#OtherTable').DataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bInfo": true,
        "bSort": false
        
    });
    $('#OtherTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
            $('#hdGridSelected').val('');
        }
        else {
            table.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
            $('#hdGridSelected').val($('#OtherTable tbody tr.row_selected').attr("id"));
        }
    });
        $('.time').mask('00:00');
        //Number
        $(".money").mask('000,000,000', {reverse: true});
        setformatmoney();
    });
</script>