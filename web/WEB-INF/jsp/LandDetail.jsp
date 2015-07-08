<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/LandDetail.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="currencyList" value="${requestScope['CurrencyList']}" />
<c:set var="booktype" value="${requestScope['BOOKING_TYPE']}" />
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="product_list" value="${requestScope['product_list']}" />
<c:set var="agent_list" value="${requestScope['agent_list']}" />
<c:set var="itinerary_list" value="${requestScope['itinerary_list']}" />
<c:set var="package_list" value="${requestScope['package_list']}" />
<c:set var="itinerarycount" value="${requestScope['itinerarycount']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<c:set var="lockUnlockBooking" value="${requestScope['LockUnlockBooking']}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<c:set var="DescriptionSize" value="670px" />
<c:if test="${booktype == 'i'}">
    <c:set var="DescriptionSize" value="670px" />
</c:if>
<c:if test="${booktype == 'o'}">
    <c:set var="DescriptionSize" value="670px" />
</c:if>

<section class="content-header" >
    <h1>
        Booking - Land Detail
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">Land</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
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

        <script type="text/javascript" charset="utf-8">
            $(document).ready(function() {

                var table = $('#MasterLand').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bInfo": false
                });

                $('#MasterLand tbody').on('click', 'tr', function() {
                    if ($(this).hasClass('row_selected')) {
                        $(this).removeClass('row_selected');
                    }
                    else {
                        table.$('tr.row_selected').removeClass('row_selected');
                        $(this).addClass('row_selected');
                    }

                });

                var table = $('#MasterItinerary').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bInfo": false
                });

                $('#MasterItinerary tbody').on('click', 'tr', function() {
                    if ($(this).hasClass('row_selected')) {
                        $(this).removeClass('row_selected');
                    }
                    else {
                        table.$('tr.row_selected').removeClass('row_selected');
                        $(this).addClass('row_selected');
                    }
                });

                var table = $('#ProductTable').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": true,
                    "bPaginate": true,
                    "bInfo": false,
                    "bLengthChange": false,
                    "iDisplayLength": 10
                });

/*
                $('#ProductTable tbody').on('mouseover', 'tr', function() {
                    if ($(this).hasClass('row_selected')) {
                        $(this).removeClass('row_selected');
                        $('#hdGridSelected').val('');
                    }
                    else {
                        table.$('tr.row_selected').removeClass('row_selected');
                        $(this).addClass('row_selected');
                        $('#hdGridSelected').val($('#MasterProduct tbody tr.row_selected').attr("id"));
                    }
                });
*/
                var table = $('#AgentTable').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": true,
                    "bPaginate": true,
                    "bInfo": false,
                    "bLengthChange": false,
                    "iDisplayLength": 10
                });
/*
                $('#AgentTable tbody').on('mouseover', 'tr', function() {
                    if ($(this).hasClass('row_selected')) {
                        $(this).removeClass('row_selected');
                     //   $('#hdGridSelected').val('');
                    }
                    else {
                    //    table.$('tr.row_selected').removeClass('row_selected');
                     //   $(this).addClass('row_selected');
                     //   $('#hdGridSelected').val($('#MasterProduct tbody tr.row_selected').attr("id"));
                    }
                });

*/              
                var templock = parseInt($("#requestLock").val());
                $("#LandItinerary").on('click', '.remCF', function() {
                    if(templock == 0){
                        $(this).parent().parent().remove();
                         var rowAll = $("#LandItinerary tr").length;
                        if (rowAll < 2) {
                            $("#tr_ItineraryAddRow").removeClass("hide");
                            $("#tr_ItineraryAddRow").addClass("show");
                        }
                    }
                });
                $("#LandItinerary").on("keyup", function() {
                    var rowAll = $("#LandItinerary tr").length;
                    $("td").keyup(function() {
                        if ($(this).find("input").val() != '') {
                            var colIndex = $(this).parent().children().index($(this));
                            var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                            rowAll = $("#LandItinerary tr").length;
                            //console.log('Row: ' + rowIndex + ', Column: ' + colIndex + ', All Row ' + rowAll);
                            if (rowIndex == rowAll) {
                                AddRow(parseInt($("#counter").val()));
                            }
                            if (rowAll < 2) {

                                $("#tr_ItineraryAddRow").removeClass("hide");
                                $("#tr_ItineraryAddRow").addClass("show");
                            }
                        }

                    });
                });
                $("#tr_ItineraryAddRow a").click(function() {
                    $(this).parent().removeClass("show");
                    $(this).parent().addClass("hide");
                });
                $(".money").mask('000,000,000,000,000,000', {reverse: true});
                $('.date').mask('0000-00-00');
                $('.time').mask('00:00');
                $('.number').mask('00');

                if('${booktype}' == 'i'){
                   if(${requestScope['isEdit']} == 0){
                      if('${master.packageTour.id}' != ''){
                          getItineraryDetail('${master.packageTour.id}');
                      }
                   }   
                }
                
                 $('#LandItinerary tbody tr:last td .input-group-addon').click(function() {  
                    AddRow(parseInt($("#counter").val()));
                });
   
            });

            function deletelist(id) {
                document.getElementById('DelItenarary').value += id + ',';
            }

            function readdata() {

                var path = '';
                var row = 1;
                var count = 0;
                var rowselect = 1;
                $('#LandItinerary tr input').each(function() {
                    var innerHTML = $(this).html(),
                            value = $(this).val();
                    var  insert = '';      
                    if(value == ''){
                          insert = ' ';  
                    }else{
                        insert = value;
                    }
                    if (count === 4) {
                        
                        path += insert + '&';

                    } else {
                        path += insert + ',';
                    }
                    if (count === 4) {
                        count = 0;
                    } else {
                        count = count + 1;
                    }

                });

                $('#LandItinerary tr select').each(function() {
                    var innerHTML = $(this).html(),
                            value = $(this).val();
                    path = path.replace('code' + rowselect, value);
                    rowselect += 1;
                });
                
                document.getElementById('Itenarary').value = path;


            }
            
            
            function reloadPackage(){
                var count = $('counter').val();
                if('${master.packageTour.id}' != ''){
                   if((count != 0)&&(count != '')){
                       if(confirm('Are you confirm to add new package itinerary?')){
                           getItineraryDetail('${master.packageTour.id}');
                        }else{
                         
                        }
                   }else{
                       getItineraryDetail('${master.packageTour.id}');
                    }    
                }
            }                 
        </script>

        <div class="col-sm-10">
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
            <input type="hidden" id="requestLock" name="requestLock" value="${lockUnlockBooking}"/>
            <form  id="landForm" action="LandDetail.smi" method="post" role="form" class="form-horizontal">
                <div class="row">
                    <div class="col-sm-3">
                        <h4>Booking Land Detail</h4>
                    </div>

                    <div class="col-sm-9 text-right">
                        <a class="btn btn-primary" href="Land.smi?referenceNo=${param.referenceNo}"><i class="glyphicon glyphicon-chevron-left"></i> Back</a>
                    </div>
                </div>

                <div class="panel panel-default">

                    <div class="panel-heading">
                        <h3 class="panel-title">Detail</h3>
                    </div>


                    <div class="panel-body">


                        <div class="row">
                            <div class="col-sm-6" style="padding-left: 21px">
                                <label  class="col-sm-3 control-label" >Agent<font style="color: red">*</font></label>
                                <div class="col-sm-3">  
                                    <div class="form-group">
                                        <div class="input-group ">
                                            <input type="hidden" class="form-control" id="agent_id" name="agent_id" value="${requestScope['agent_id']}">
                                            <input type="text" class="form-control" id="agent_code" onkeypress ="getAgent();"   name="agent_code" value="${requestScope['agent_code']}">
                                            <span class="input-group-addon" id="agent_modal"  data-toggle="modal" data-target="#AgentModal">
                                                <span class="glyphicon-search glyphicon"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">  
                                    <input type="text" class="form-control" id="agent_name" readonly="" name="agent_name" value='${requestScope['agent_name']}'>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" for="nameProduct">Category</label>
                                    <div class="col-sm-7">
                                        <input type="text" maxlength="100" class="form-control " id="Category" name="Category" value='${requestScope['Category']}' >  
                                    </div>
                                </div>
                            </div>

                        </div>    
                        <div class="row">
                            <div class="col-md-6 ">
                                <div class="form-group">
                                    <label style="margin-left: 8px" class="col-sm-3 control-label" for="nameProduct">OK by</label>
                                    <div class="col-sm-8"  style="width: 377px;margin-left: -12px">
                                        <input type="text" maxlength="100" class="form-control" id="okby" name="okby" value='${requestScope['okby']}'>  
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label  class="col-sm-3 control-label" for="nameProduct">Description</label>
                                    <div class="col-sm-7">
                                        <input type="text" maxlength="255" class="form-control"  id="Description" name="Description" value='${requestScope['Description']}' >  
                                    </div>
                                </div>
                            </div>
                        </div>   

                        <c:choose>
                            <c:when test="${booktype == 'i'}">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group" style="padding-left: 30px">
                                            <label class="col-sm-3 col-md-offset-7 control-label" for="nameProduct">Cost</label>

                                        </div>
                                    </div>
                                    <div class="col-md-1 " style="padding-left: 30px" >
                                        <div class="form-group">
                                            <label class="col-sm-3  control-label"  for="nameProduct">Qty</label>

                                        </div>
                                    </div>
                                    <div class="col-md-2 " style="padding-left: 45px">
                                        <div class="form-group">
                                            <label class="col-sm-1  control-label"  for="nameProduct">Price</label>

                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                        <label class="col-sm-3 control-label" >Hotel</label>
                                        <div class="col-sm-7">                             
                                            <input type="text" maxlength="255" class="form-control"  id="hotel" name="hotel" value='${requestScope['hotel']}' >  
                                        </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <!--
                                <div class="row">
                                    <div class="col-md-6 " >
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="nameProduct">Cost</label>
                                            <div class="col-sm-2" style="width: 103px">
                                                <input type="text" maxlength="10" onkeyup="validateMaxCost('inb_Cost');" class="form-control money" id="inb_Cost" name="inb_Cost" value="${requestScope['inb_Cost']}" >  
                                            </div>

                                            <label class="col-sm-1 control-label" for="nameProduct">Price</label>
                                            <div class="col-sm-2" style="width: 103px">
                                                <input type="text" maxlength="10" onkeyup="validateMaxCost('inb_Price');" class="form-control money" id="inb_Price" name="inb_Price" value="${requestScope['inb_Price']}" >  
                                            </div>
                                            <label class="col-sm-1 control-label"  for="nameProduct">Qty</label>
                                            <div class="col-sm-1" >
                                                <input type="text" maxlength="3" class="form-control money" style="width: 50px"  id="inb_QTY" name="inb_QTY" value="${requestScope['inb_QTY']}" >  
                                            </div>
                                        </div>
                                    </div>
                                </div> -->
                                <div class="row">
                                    <div class="col-md-6 " >
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="nameProduct">Adult</label>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" onkeyup="validateMaxCost('inb_Cost');"  class="form-control money" id="inb_Cost" name="inb_Cost" value="${requestScope['inb_Cost']}" >  
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" maxlength="3"  class="form-control money" id="inb_QTY" name="inb_QTY" value="${requestScope['inb_QTY']}">  
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" onkeyup="validateMaxCost('inb_Price');"  class="form-control money" id="inb_Price" name="inb_Price" value="${requestScope['inb_Price']}">  
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                        <label class="col-sm-3 control-label" >Currency</label>
                                            <div class="col-sm-4">                             
                                            <select class="form-control" id="currency" name="currency">
                                                <c:forEach var="currency" items="${currencyList}">
                                                    <c:set var="select" value="" />
                                                    <c:if test="${currency.code == requestScope['currency']}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option value="${currency.code}" ${select}>${currency.code}</option>
                                                </c:forEach>
                                            </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                            
                                <div class="row">                                   
                                    <div class="col-md-6 " >
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="nameProduct">Child</label>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" class="form-control money" onkeyup="validateMaxCost('inb_CH_Cost');"  id="inb_CH_Cost" name="inb_CH_Cost" value="${requestScope['inb_CH_Cost']}" >  
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" maxlength="3" class="form-control money"   id="inb_CH_Qty" name="inb_CH_Qty" value="${requestScope['inb_CH_Qty']}" >  
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" class="form-control money" onkeyup="validateMaxCost('inb_CH_Price');"  id="inb_CH_Price" name="inb_CH_Price" value="${requestScope['inb_CH_Price']}" >  
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                 
                                    <div class="col-md-6 " >
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="nameProduct">Infant</label>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" class="form-control money" onkeyup="validateMaxCost('inb_IN_Cost');" id="inb_IN_Cost" name="inb_IN_Cost" value="${requestScope['inb_IN_Cost']}" >  
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" maxlength="3" class="form-control money"  id="inb_IN_Qty" name="inb_IN_Qty" value="${requestScope['inb_IN_Qty']}" >  
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" class="form-control money" onkeyup="validateMaxCost('inb_IN_Price');" id="inb_IN_Price" name="inb_IN_Price"  value="${requestScope['inb_IN_Price']}" >  
                                            </div>
                                        </div>
                                    </div>
                                </div>                                           


                            </c:when>
                            <c:when test="${booktype == 'o'}">
                                <div class="row">
                                    <div class="col-sm-6" style="padding-left: 18px">
                                        <label   class="col-sm-3 control-label" >Package</label>
                                        <div class="col-sm-3">  
                                            <div class="form-group">
                                                <div class="input-group " style="padding-left: 2px">
                                                    <input type="hidden" class="form-control" id="Product_id" name="Product_id" value="${requestScope['Product_id']}">
                                                    <input  type="text" class="form-control" id="Product_code"  name="Product_code" value="${requestScope['Product_code']}">
                                                    <span class="input-group-addon" id="Product_modal"  data-toggle="modal" data-target="#ProductModal">
                                                        <span class="glyphicon-search glyphicon"></span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-6">  
                                            <input type="text" class="form-control" id="Product_name" readonly name="Product_name" value="${requestScope['Product_name']}">
                                        </div>
                                    </div>
                                    <div class="col-md-6 ">
                                        <div class="form-group">                                           
                                            <label for="effectivefrom" class="col-sm-3 control-label"> Depart Date </label>
                                            <div class=' col-sm-4 input-group datepicker' id='effectivefromClass' style="padding-left: 15px">
                                                <input type='text' class="form-control"  id="departdate" name="departdate" data-date-format="YYYY-MM-DD" value="${requestScope['departdate']}" />
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>    
                                    
                                    <div class="col-md-3 " >
                                        <div class="form-group">
                                            <label class="col-sm-3 col-md-offset-7 control-label" for="nameProduct">Cost</label>

                                        </div>
                                    </div>
                                    <div class="col-md-1 " style="padding-left: 30px" >
                                        <div class="form-group">
                                            <label class="col-sm-3  control-label"  for="nameProduct">Qty</label>

                                        </div>
                                    </div>
                                    <div class="col-md-2 " style="padding-left: 45px">
                                        <div class="form-group">
                                            <label class="col-sm-1  control-label"  for="nameProduct">Price</label>

                                        </div>
                                    </div>
                                    <div class="col-md-6 " >
                                        <div class="form-group">
                                            
                                            <label for="effectivefrom" class="col-sm-3 control-label" > Arrive Date </label>
                                            <div class=' col-sm-4 input-group datepicker' id='effectivefromClass' style="padding-left: 15px">
                                                <input type='text' class="form-control"  id="arrivedate" name="arrivedate" data-date-format="YYYY-MM-DD" value="${requestScope['arrivedate']}" />
                                                <span class="input-group-addon">
                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                </span>
                                            </div>
                                        </div>
                                    </div>                                  
                                </div>
                                        
                                <div class="row">
                                    <div class="col-md-6 " >
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="nameProduct">Adult</label>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" onkeyup="validateMaxCost('AD_Cost');"  class="form-control money" id="AD_Cost" name="AD_Cost" value="${requestScope['AD_Cost']}" >  
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" maxlength="3" onkeyup="validateMaxCost('AD_Qty');" class="form-control money" id="AD_Qty" name="AD_Qty" value="${requestScope['AD_Qty']}">  
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" onkeyup="validateMaxCost('AD_Price');"  class="form-control money" id="AD_Price" name="AD_Price" value="${requestScope['AD_Price']}">  
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                        <label class="col-sm-3 control-label" >Currency</label>
                                        <div class="col-sm-4">                             
                                            <select class="form-control" id="currency" name="currency">
                                                <c:forEach var="currency" items="${currencyList}">
                                                    <c:set var="select" value="" />
                                                    <c:if test="${currency.code == requestScope['currency']}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option value="${currency.code}" ${select}>${currency.code}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        </div>
                                    </div>                           
                                </div>
                                            
                                <div class="row">
                                    <div class="col-md-6 " >
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="nameProduct">Child</label>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" class="form-control money" onkeyup="validateMaxCost('CH_Cost');"  id="CH_Cost" name="CH_Cost" value="${requestScope['CH_Cost'] }" >  
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" maxlength="3" class="form-control money" onkeyup="validateMaxCost('CH_Qty');"  id="CH_Qty" name="CH_Qty" value="${requestScope['CH_Qty'] }" >  
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" class="form-control money" onkeyup="validateMaxCost('CH_Price');"  id="CH_Price" name="CH_Price" value="${requestScope['CH_Price'] }" >  
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 " > 
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="remark">Remark</label>
                                            <div class="col-sm-7" >
                                                <textarea rows="2" cols="50" class="form-control" maxlength="255" id="remark" name="remark" >${requestScope['remark']}</textarea>                                              
                                            </div>
                                        </div>
                                    </div>       
                                    
                                </div>
                                            
                                <div class="row">
                                    <div class="col-md-6 " >
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="nameProduct">Infant</label>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" class="form-control money" onkeyup="validateMaxCost('IN_Cost');" id="IN_Cost" name="IN_Cost" value="${requestScope['IN_Cost']}" >  
                                            </div>
                                            <div class="col-sm-2">
                                                <input type="text" maxlength="3" class="form-control money" onkeyup="validateMaxCost('IN_Qty');" id="IN_Qty" name="IN_Qty" value="${requestScope['IN_Qty']}" >  
                                            </div>
                                            <div class="col-sm-3">
                                                <input type="text" maxlength="10" class="form-control money" onkeyup="validateMaxCost('IN_Price');" id="IN_Price" name="IN_Price"  value="${requestScope['IN_Price']}" >  
                                            </div>
                                        </div>
                                    </div>
                                                                   
                                </div>
                            </c:when>
                        </c:choose>
                    </div>           

                </div>

                <div class="row"> 
                    <div class="col-md-6 " style="padding-right: 15px">
                        <h4><b>Itinerary</b> </h4>
                    </div>
                    <c:if test="${booktype == 'i'}">
                    <div class="col-sm-6 text-right">
                        <button type="button" id="reloadPack"  onclick="reloadPackage();" class="btn btn-primary "><span class="fa fa-long-arrow-down"></span> Reload</button>
                    </div>
                    </c:if>

                        
                </div>
                
                <style>
                     .input-group-addon {
                         padding: 2px 10px; 
                     }
                </style>                         
                                    
                <div class="row" style="margin-left: 10px;margin-right: 10px;"> 
                    <table id="LandItinerary" class="display" cellspacing="0"  >
                        <thead>
                            <tr class="datatable-header">
                                <th>No</th>
                                <th >Date</th>
                                <th>Time</th>
                                <th>Description</th>
                                <th>Action </th>
                            </tr>

                        </thead>
                        <tbody>
                            <c:forEach var="table" items="${itinerary_list}" varStatus="Counter">
                                <tr>
                                    <td class="hidden"> <input  type="hidden"  value="${table.id}">  </td>
                                    <td> <input style="width: 20px" id="row-${Counter.count}-no" name="row-${Counter.count}-no" type="text"  class="form-control number" value="${table.orderNo}">  </td>
                                    <td> 
                                        <div class='input-group daydatepicker' id='daydatepicker-${Counter.count}' style="padding-left: 15px">
                                            <input style="width: 100px" type='text' class="form-control"  id="dayDate-${Counter.count}" name="dayDate-${Counter.count}" data-date-format="YYYY-MM-DD" value="${table.dayDate}" />
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </td>
                                    <td> <input style="width: 80px" type="text" class="form-control time" value="${table.dayTime}" placeholder="HH:MM">  </td>
                                    <td> <input style="width: ${DescriptionSize}" maxlength ="255"  type="text" class="form-control" value="${table.description}">  </td>
                                    <td class="text-center">
                                        <c:if test="${lockUnlockBooking == 0}">
                                            <a class="remCF"><span  onclick="deletelist('${table.id}');" class="glyphicon glyphicon-remove deleteicon "></span></a>
                                        </c:if>
                                        <c:if test="${lockUnlockBooking == 1}">
                                            <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                        </c:if>
                                    </td>
                                </tr>                       
                            </c:forEach>     
                        </tbody>
                    </table>    
                </div>

                <div id="tr_ItineraryAddRow" class="text-center hide" style="padding-top: 10px">
                    <c:if test="${lockUnlockBooking == 0}">
                        <a class="btn btn-success" onclick="AddRow()">
                            <i class="glyphicon glyphicon-plus"></i> Add
                        </a>                       
                    </c:if>
                    <c:if test="${lockUnlockBooking == 1}">
                        <a class="btn btn-success disabled">
                            <span class="glyphicon glyphicon-plus"></span>Add</button>
                        </a>   
                    </c:if>
                </div>
                <input type="hidden" class="form-control" name="action" id="action" value="save" >  
                <input type="hidden" class="form-control" name="Itenarary" id="Itenarary" >  
                <input type="hidden" class="form-control" name="DelItenarary" id="DelItenarary" >  
                <input type="hidden" class="form-control" name="isbill" id="isbill" value="${requestScope['isbill']}" >  
                <input type="hidden"  id="counter" name="counter" value="${itinerarycount}" />
                <input type="hidden" class="form-control" id="itemid" name="landid" value="${requestScope['landid']}">
                <input type="hidden" value="${param.referenceNo}" id="refno" name="referenceNo">
                <input type="hidden" class="form-control" id="status" name="status" value="${requestScope['status']}">
                <div class="text-center" style="padding-top: 10px">
                    <c:choose>
                        <c:when test="${requestScope['status'] == 2}">
                            <button type="button" disabled id="saveland"  onclick="readdata()" class="btn btn-success"><span class="fa fa-save"></span> Save</button>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${lockUnlockBooking == 0}">
                                <button type="submit" id="savereal" onfocus="readdata()"  onmouseover="readdata()"  class="btn btn-success "><span class="fa fa-save"></span> Save</button>
                            </c:if>
                            <c:if test="${lockUnlockBooking == 1}">
                                <button class="btn btn-success disabled"><span class="fa fa-save"></span> Save</button>
                            </c:if>   
                        </c:otherwise>
                    </c:choose>
                </div>              
            </form>               
        </div>  
    </div>
</div>
                

<div class="modal fade" id="ProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Product</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="ProductTable">
                    <thead>  
                        <script>
                            product = [];
                        </script>
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                            <th class="">Name</th>
                            <th class="">Detail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${product_list}">
                            <tr onclick ="setupproductvalue('${table.id}', '${table.code}', '${table.name}')" >
                                <td class="hidden">${table.id}</td>
                                <td>${table.code} </td>
                                <td>${table.name} </td>
                                <td>${table.detail} </td>

                            </tr>  
                            <script>
                                product.push({id: "${table.id}", code: "${table.code}", name: "${table.name}"});
                            </script>
                        </c:forEach>
                   </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<div class="modal fade" id="AgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Agent</h4>
            </div>
            <div class="modal-body">
                <!--Airline List Table-->

                <table class="display" id="AgentTable">
                    <thead>    
                        <script>
                            var agent = [];
                        </script>
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                            <th class="">Name</th>
                        </tr>                      
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${agent_list}">
                            <tr onclick ="setupagentvalue('${table.id}', '${table.code}', '${table.name}')" >
                                <td class="hidden">${table.id}</td>
                                <td>${table.code} </td>
                                <td>${table.name} </td>
                            </tr>    
                            <script>
                                agent.push({id: "${table.id}", code: "${table.code}", name: "${table.name}"});
                            </script>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div  class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>  

<div class="modal fade" id="Confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"  id="Titlemodel">Booking - Land Detail </h4>
            </div>
            <div class="modal-body" id="confirmMessage">
                Are you sure to update cost and price ?
            </div>
            <div class="modal-footer">
                <button type="button" onclick="Confirm()" class="btn btn-danger">Yes</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
            </div>
            <div class="hidden">
                <input type="text" class="form-control" id="path0" name="path0" value="">
                <input type="text" class="form-control" id="path1" name="path1" value="">
                <input type="text" class="form-control" id="path2" name="path2" value="">
                <input type="text" class="form-control" id="path3" name="path3" value="">
                <input type="text" class="form-control" id="path4" name="path4" value="">
                <input type="text" class="form-control" id="path5" name="path5" value="">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<script type="text/javascript" charset="utf-8">

    AddRow(parseInt($("#counter").val()));
                      
    function AddRow(row) {

        $("#LandItinerary tbody").append(
                '<tr style="higth 100px">' +
                '<td class="hidden"> <input id="row-' + row + '-id" name="row-' + row + '-id"  type="hidden" >  </td>' +
                '<td><input style="width: 20px" id="row-' + row + '-no" name="row-' + row + '-no"   type="text" class="form-control number"  ></td>' +
                '<td><div class="input-group daydatepicker" id="daydatepicker-' + row + '" style="padding-left: 15px">'+
                '<input style="width: 100px" type="text" class="form-control"  id="dayDate-' + row + '" name="dayDate-' + row + '" data-date-format="YYYY-MM-DD" />'+
                '<span class="input-group-addon">' +                                               
                '<i class="glyphicon glyphicon-calendar"></i></span></div></td>' +
                '<td><input style="width: 80px" type="text" id="row-' + row + '-hour" name="row-' + row + '-hour" class="form-control time" placeholder="HH:MM" ></td>' +
                '<td><input   class="form-control" maxlength="255" style="width: ${DescriptionSize}" id="row-' + row + '-des" name="row-' + row + '-des" rows="2" ></td>' +
                '<td class="text-center">' +
                '<a class="remCF">  <span   class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
                '</tr>'
                );
                var tempCount = parseInt($("#counter").val()) + 1;
        $("#counter").val(tempCount);
        reloadDatePicker();
        
    }
    
        function AddRowFromValue(no,hour,description) {
            row = parseInt($("#counter").val());
        $("#LandItinerary tbody").append(
                '<tr style="higth 100px">' +
                '<td class="hidden"> <input id="row-' + row + '-id" name="row-' + row + '-id"  type="hidden" value="" >  </td>' +
                '<td><input style="width: 20px" id="row-' + row + '-no" name="row-' + row + '-no"   type="text" class="form-control number" value="'+no+'" ></td>' +
                '<td><div class="input-group daydatepicker" id="daydatepicker-' + row + '" style="padding-left: 15px">'+
                '<input style="width: 100px" type="text" class="form-control"  id="row-' + row + '-date" name="row-' + row + '-date" data-date-format="YYYY-MM-DD" />'+
                '<span class="input-group-addon">' +                                               
                '<i class="glyphicon glyphicon-calendar"></i></span></div></td>' +
                '<td><input style="width: 80px" type="text" id="row-' + row + '-hour" name="row-' + row + '-hour" class="form-control time" placeholder="HH:MM" value="'+hour+'" ></td>' +
                '<td><input   class="form-control" maxlength="255" style="width: ${DescriptionSize}" id="row-' + row + '-des" name="row-' + row + '-des" rows="2" value="'+description+'" ></td>' +
                '<td class="text-center">' +
                '<a class="remCF">  <span   class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
                '</tr>'
                );
                var tempCount = parseInt($("#counter").val()) + 1;
        $("#counter").val(tempCount);
        reloadDatePicker();
    }

    function reloadDatePicker(){
        try{
           $(".daydatepicker").datetimepicker({
                pickTime: false   
           });  
           $('span').click(function() {
             
                var position = $(this).offset();
                $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
           });
           $('#LandItinerary tbody tr:last td .input-group-addon').click(function() {  
                AddRow(parseInt($("#counter").val()));
           });
           
        }catch(e){
            
        }  
        
        
    }

    function DeleteRow() {
        $(this).remove();
        var tempCount = parseInt($("#counter").val()) - 1;
        $("#counter").val(tempCount);
    }
    
    
var issubmit =0;
$('#landForm').on("keyup keypress", function(e) {
  var code = e.keyCode || e.which; 
  if (code  == 13) {  
 
      if(issubmit ==0){
        e.preventDefault();
        return false;         
      }
  }
});

$('#savereal').on("keyup keypress", function(e) {
  var code = e.keyCode || e.which; 
  if (code  == 13) {
      issubmit = 1;
      //$('#landForm').submit();
  }
});



  $( "#landForm" ).submit(function( event ) { 
              
                if(($("#agent_id").val()=='')||($("#agent_code").val()=='')||($("#agent_name").val()=='')){
                    var agentcode = $("#agent_code").val();
                   $("#agent_code").val('');
                    $('#landForm').bootstrapValidator('revalidateField', 'agent_code');
                    $("#agent_code").val(agentcode);
                   event.preventDefault();
                   return false;
                }else{
                   var data = validateNumber();
                   if(data){
                       
                        $("#savereal").prop('disabled', false);
                        event.preventDefault();
                        return false;
                   }else{
                       readdata();
                    }
                }
                
            });


    $(document).ready(function() {
        $('.datepicker').datetimepicker().change(function(){                          
            setupdepartdatevalue();
        });
        $(".daydatepicker").datetimepicker({
            pickTime: false   
        });
        $('span').click(function() {
                            var position = $(this).offset();
                            console.log("positon :" + position.top);
                            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
                        
        $('#landForm').bootstrapValidator({
            container: 'tooltip',
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                agent_code: {
                    validators: {
                        notEmpty: {
                            message: 'The agent code is required'  
                        }

                    }
                }
            }
        });  
    });

var maxint = 2147483647;
var maxvalue = 100000000;
function validateNumber(){
   
   var alertMessage = '';   
    
     
<c:if test="${booktype == 'i'}">
     
    var inbcost = replaceAll(',','',$('#inb_Cost').val());
    var inbqty = $('#inb_QTY').val();
    var inbprice = replaceAll(',','',$('#inb_Price').val());
    
    var inbchcost = replaceAll(',','',$('#inb_CH_Cost').val());
    var inbchqty = $('#inb_CH_Qty').val();
    var inbchprice = replaceAll(',','',$('#inb_CH_Price').val());
    
    var inbincost = replaceAll(',','',$('#inb_IN_Cost').val());
    var inbinqty = $('#inb_IN_Qty').val(); 
    var inbinprice = replaceAll(',','',$('#inb_IN_Price').val());

    if((inbcost*inbqty) >maxvalue){
        alertMessage += 'Adult cost is overflow please check it\n'
    }
    if((inbchcost*inbchqty) >maxvalue){
        alertMessage += 'Child cost is overflow please check it\n'
    }
    if((inbincost*inbinqty) >maxvalue){
        alertMessage += 'Infant cost is overflow please check it\n'
    }
    
    if((inbprice*inbqty) >maxvalue){
       alertMessage += 'Adult price is overflow please check it\n'
    }
    if((inbchprice*inbqty) >maxvalue){
       alertMessage += 'Child price is overflow please check it\n'
    }
    if((inbinprice*inbqty) >maxvalue){
       alertMessage += 'Infant price is overflow please check it\n'
    }
    
    if(alertMessage != ''){
       alert(alertMessage);
       return true; 
    } 
    return false;
    
</c:if>
<c:if test="${booktype == 'o'}">
   
    var adcost =  replaceAll(',','',$('#AD_Cost').val());
    var chcost =  replaceAll(',','',$('#CH_Cost').val());
    var incost =  replaceAll(',','',$('#IN_Cost').val());
    var adprice = replaceAll(',','',$('#AD_Price').val());
    var chprice = replaceAll(',','',$('#CH_Price').val());
    var inprice = replaceAll(',','',$('#IN_Price').val());
    var adqty = replaceAll(',','',$('#AD_Qty').val());
    var chqty = replaceAll(',','',$('#CH_Qty').val());
    var inqty = replaceAll(',','',$('#IN_Qty').val());
    var sumcost = (adcost * adqty)+ (chcost * chqty) + (incost * inqty);
    var sumprice = (adprice * adprice)+ (chprice * chprice) + (inprice * inprice);
         
      
    if(adcost*adqty > maxvalue){
        alertMessage += 'Adult cost is overflow please check it\n';
    } 
    
    if(chcost*adqty> maxvalue){
        alertMessage += 'Child cost is overflow please check it\n';
    } 
    
    if(incost*inqty > maxvalue){
        alertMessage += 'Infant cost is overflow please check it\n';
    } 
    if(adprice*adqty > maxvalue){
        alertMessage += 'Adult price is overflow please check it\n';
    } 
    if(chprice*chqty > maxvalue){
        alertMessage += 'Child price is overflow please check it\n';
    } 
    if(inprice*inqty > maxvalue){
        alertMessage += 'Infant price is overflow please check it\n';
    } 
    if(sumcost > maxint){
        alertMessage += 'Sum of cost is overflow please check it\n';
    } 
    if(sumprice > maxint){
       alertMessage += 'Sum of price is overflow please check it\n';
    }
    if(alertMessage != ''){
       alert(alertMessage);
       return true; 
    } 

   
 </c:if>  
 
    return false;
}
    
function replaceAll(find, replace, str) {
  return str.replace(new RegExp(find, 'g'), replace);
}

</script>
<c:if test="${! empty requestScope['result']}">
    <script language="javascript">
        $('#textAlertDivSave').show();
    </script>
</c:if>
<c:if test="${ empty requestScope['result']}">
    <script language="javascript">
        $('#textAlertDivNotSave').show();
    </script>
</c:if>
