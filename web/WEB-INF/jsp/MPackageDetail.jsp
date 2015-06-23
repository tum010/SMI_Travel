<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/MpackageDetail.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<c:set var="itinerarylist" value="${requestScope['itinerary_List']}" />
<c:set var="pricelist" value="${requestScope['price_list']}" />
<c:set var="ListCity" value="${requestScope['ListCity']}" />
<c:set var="ListPackageCity" value="${requestScope['ListPackageCity']}" />

<section class="content-header"  >
    <h4>
        <b>Master : Package</b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li><a href="#">Package </a></li>
        <li class="active"><a href="#">Package Detail</a></li>
    </ol>
</section>
<!--<input type="text" value="${requestScope['packageLap']}">-->
<div class ="container"  style="padding-top: 15px;"> 
    <form action="MPackageDetail.smi" method="post" id="PackageForm" role="form" class="form-horizontal">
        <div class="col-md-8 col-xs-offset-2">
            <!--Alert Save --> 
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Success!</strong> 
            </div>
            <!--Alert Not Save --> 
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Not Success!</strong> 
            </div>
            <!-- Alert Uni-->
            <div id="textAlertLap"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Package already exist!</strong> 
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">Detail</div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <div class="col-md-6 col-md-offset-6 text-right">
                                <a id="ButtonBack" name="ButtonBack" href="MPackage.smi" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Back</a>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2   control-label" for="Country">Code<font style="color: red">*</font></label>
                                <div class="col-sm-9">
                                    <input type="text" style="text-transform:uppercase" ${requestScope['disabledcode']} class="form-control" id="packagecode"  maxlength="50" name="packagecode"  value="${requestScope['packagecode']}" >  
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fromcity">Name<font style="color: red">*</font></label>
                                <div class="col-sm-9">
                                    <input type="text" style="text-transform:uppercase" class="form-control" id="packagename" maxlength="255" name="packagename" value="${requestScope['packagename']}" >  
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label style="padding-left: 31px" class="col-sm-2 control-label" for="fromdes">Detail</label>
                                    <div class="col-sm-9" style="padding-left: 28px">  
                                        <textarea  name="detail" id="detail" maxlength="255"  class="form-control" rows="3">${requestScope['detail']}</textarea>
                                    </div>   
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label style="padding-left: 15px" class="col-sm-2 control-label" for="fromdes">Remark</label>
                                    <div class="col-sm-9" style="padding:0px 28px 0px 12px">  
                                        <textarea  name="remark" id="remark" maxlength="255"  class="form-control" rows="3">${requestScope['remark']}</textarea>
                                    </div>   
                                </div>
                            </div>       
                        </div>                                  
                        <div class="row">
                            <div class="col-md-6" style="padding-left: 30px">
                                <div class="form-group">
                                    <label class="col-sm-2   control-label" for="Country">Status<font style="color: red">*</font></label>
                                    <div class="col-sm-9">
                                        <select name="status" id="status"  class="form-control">
                                            <option value="active"  selected="selected"> active</option>
                                            <option value="inactive" ${requestScope['IsInactive']} >inactive</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6" style="padding-left: 14px">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">Supplier</label>
                                    <div class="col-sm-9" style="padding:0px 28px 0px 12px">
                                        <input type="text" class="form-control" id="supplier" maxlength="200" name="supplier" value="${requestScope['supplier']}" >  
                                    </div>
                                </div>
                            </div>                          
                        </div>
<!--============================================Itinerary=============================================== --> 
                        <div class="row"> 
                            <div class="col-md-6 " style="padding-left: 24px">
                                <h5><b>Itinerary</b> </h5>
                            </div>
                        </div>
                        <div class="row" style="margin-left: 10px;margin-right: 10px;"> 
                            <table id="Itinerary" class="display" cellspacing="0"  >
                                <thead>
                                    <tr class="datatable-header">
                                        <th class="hidden">order</th>
                                        <th class="hidden">id</th>
                                        <th class="hidden">number</th>
                                        <th>No</th>
                                        <th>Time</th>
                                        <th>Description</th>
                                        <th>Action </th>
                                    </tr>

                                </thead>
                                <tbody>
                                    <c:forEach var="table" items="${itinerarylist}" varStatus="dataStatus">
                                        <tr style="higth:100px">
                                            <td class="hidden"> <input id="row-${dataStatus.count -1}-itineraryid" name="row-${dataStatus.count -1}-itineraryid"  type="hidden"  value="${table.id}">  </td>
                                            <td class="hidden orderrow">${dataStatus.count -1}</td>
                                            <td><input style="width: 20px" id="row-${dataStatus.count -1}-no" name="row-${dataStatus.count -1}-no"   type="text" class="form-control number" value="${table.orderNo}"></td>
                                            <td><input style="width: 70px" type="text" id="row-${dataStatus.count -1}-hour" name="row-${dataStatus.count -1}-hour" class="form-control time" placeholder="HH:MM" value="${table.time}"></td>
                                            <td><input   class="form-control" maxlength="255" style="width:570px" id="row-${dataStatus.count -1}-des" name="row-${dataStatus.count -1}-des" rows="2" value="${table.detail.replace("\"","&quot;")}"></td>
                                            <td class="text-center">
                                                <a class="remCF" onclick="ConfirmDelete('1', '${table.id}', '${dataStatus.count-1}')">  
                                                    <span  id="SpanRemove${dataStatus.count-1}"  class="glyphicon glyphicon-remove deleteicon"></span>
                                                </a>
                                            </td>
                                        </tr>              
                                        <script>
                                        $(document).ready(function () {
                                            $("#counterItinerary").val(parseInt("${dataStatus.count}") );    
                                        });
                                        </script>
                                    </c:forEach>
                                </tbody>
                            </table>    
                        </div>
                        <div id="tr_ItineraryAddRow" class="text-center hide" style="padding-top: 10px">
                            <a class="btn btn-success" onclick="AddRowItinerary()">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>  
<!--=====================================================Price=============================================-->
                        <div class="row"> 
                            <div class="col-md-6 " style="padding-left: 24px">
                                <h5><b>Price</b> </h5>
                            </div>
                        </div>
                        <div class="row" style="margin-left: 10px;margin-right: 10px;">            
                            <table id="PackagePrice" class="display" cellspacing="0"  >
                                <thead>
                                    <tr class="datatable-header">   
                                        <th rowspan="2" class="hidden">id</th>
                                         <th rowspan="2" class="hidden">number</th>
                                        <th style="width: 100px" rowspan="2"> From</th>
                                        <th style="width: 100px" rowspan="2"> To</th>
                                        <th colspan="3" >Cost</th>
                                        <th colspan="3" >Price</th> 
                                        <th rowspan="2">Action</th>
                                    </tr>
                                    <tr class="datatable-header">
                                        <th >Adult</th>
                                        <th >Child</th>
                                        <th >Infant</th>
                                        <th >Adult</th>
                                        <th >Child</th>
                                        <th >Infant</th>
                                    </tr>

                                </thead>
                                <tbody>
                                    <c:forEach var="table1" items="${pricelist}" varStatus="priceStatus">
                                        <tr>
                                            <td class="hidden"><input class="form-control" type="hidden"  style="width:50px" id="row-${priceStatus.count-1}-priceid" name="row-${priceStatus.count-1}-priceid" value="${table1.id}"></td>
                                            <td class="hidden orderrow">${priceStatus.count -1}</td>
                                            <td>
                                                <div class='input-group daydatepicker' id='daydatepicker-${Counter.count}' style="padding-left: 0px">
                                                    <input style="width: 100%" type='text' class="form-control"  id="row-${priceStatus.count-1}-datefrom" name="row-${priceStatus.count-1}-datefrom" data-date-format="YYYY-MM-DD"  value="${table1.effectiveFrom}" />
                                                    <span class="input-group-addon" style="padding : 1px 10px;">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    </span>
                                                </div> 
                                            </td>
                                            <td>
                                                <div class='input-group daydatepicker' id='daydatepicker-${Counter.count}' style="padding-left: 0px">
                                                    <input style="width: 100%" type='text' class="form-control"  id="row-${priceStatus.count-1}-dateto" name="row-${priceStatus.count-1}-dateto" data-date-format="YYYY-MM-DD"  value="${table1.effectiveTo}" />
                                                    <span class="input-group-addon" style="padding : 1px 10px;">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    </span>
                                                </div>
                                            </td>
                                            <td><input class="form-control money"  style="width:50px" id="row-${priceStatus.count-1}-adcost" name="row-${priceStatus.count-1}-adcost" value="${table1.adCost}"></td>
                                            <td><input class="form-control money"  style="width:50px" id="row-${priceStatus.count-1}-chcost" name="row-${priceStatus.count-1}-chcost" value="${table1.chCost}"></td>
                                            <td><input class="form-control money"  style="width:50px" id="row-${priceStatus.count-1}-incost" name="row-${priceStatus.count-1}-incost" value="${table1.inCost}"></td>
                                            <td><input class="form-control money"  style="width:50px" id="row-${priceStatus.count-1}-adprice" name="row-${priceStatus.count-1}-adprice" value="${table1.adPrice}"></td>
                                            <td><input class="form-control money"  style="width:50px" id="row-${priceStatus.count-1}-chprice" name="row-${priceStatus.count-1}-chprice" value="${table1.chPrice}"></td>
                                            <td><input class="form-control money"  style="width:50px" id="row-${priceStatus.count-1}-inprice" name="row-${priceStatus.count-1}-inprice" value="${table1.inPrice}"></td>
                                            <td class="text-center">
                                                <a class="remCF" onclick="ConfirmDelete('2', '${table1.id}', '${priceStatus.count-1}')">  
                                                    <span  id="SpanRemove${priceStatus.count-1}"  class="glyphicon glyphicon-remove deleteicon"></span>
                                                </a>
                                            </td>
                                        </tr>
                                        <script>
                                            $(document).ready(function () {
                                                $("#counterPrice").val(parseInt("${priceStatus.count}"));
                                            });
                                        </script>
                                    </c:forEach>
                                </tbody>
                            </table>    
                        </div>            
                        <div id="tr_PackagePriceAddRow" class="text-center hide" style="padding-top: 10px">
                            <a class="btn btn-success" onclick="AddRowPackagePrice()">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>              

                        <input type="hidden" class="form-control" name="action" id="action" value="save" >             
                        <input type="hidden"  id="counterItinerary" name="counterItinerary" value="0" />
                        <input type="hidden"  id="cityCounter" name="cityCounter" value="0" />
                        <input type="hidden"  id="counterPrice" name="counterPrice" value="0" />
                        <input type="hidden"  id="rowType" name="rowType" />
                        <input type="hidden"  id="Itiid" name="Itiid" />
                        <input type="hidden"  id="checkValidate" name="checkValidate" />
                        <input type="hidden"  id="cCount" name="cCount" />
                        <input type="hidden"  id="packageid" name="packageid" value="${requestScope['packageid']}" />
                        
<!--============================================City=============================================== -->
                        <div class="row"> 
                            <div class="col-md-6 " style="padding-left: 24px">
                                <h5><b>City</b> </h5>
                            </div>
                        </div>
                        <!--<div class="panel panel-default">-->  
                        <div class="hidden" id="input-list-city">
                          
                        </div>
                        <!--Div Set Id City -->
                        <div class="hidden" >
                          
                        </div>
                        <select   class="hidden"  id="select-list-city">
                            <c:forEach var="pass" items="${ListCity}" varStatus="status">
                                 <!--<input type="text" class="hidden" id="row-city-${status.count}-id" name="row-city-${status.count}-id" value="${pass.id}" />-->
                                <option  value="${pass.id}">${pass.name}</option>
                           </c:forEach>
                        </select>
                          
                            <div class="row" style="margin-left: 10px;margin-right: 10px;">            
                            <table id="City" class="display" cellspacing="0"  >
                                <thead>
                                    <tr class="datatable-header">   
                                        <th style="width: 10%">No</th>
                                        <th>Name</th>
                                        <th style="width: 10%">Action</th>
                                    </tr>
                                </thead>
                            <script>
                                    var cityName = [];
                            </script>
                            <c:forEach var="cityList" items="${ListCity}" >
                                <script>
                                    cityName.push({value: "${cityList.id}", label: "${cityList.name}"});        
                                </script>
                            </c:forEach>
                            <tbody>
                                <input type="text" class="hidden" id="cityCounter" name="cityCounter" value="0" />
                                <c:forEach var="pa" items="${ListPackageCity}" varStatus="city">
                                    <tr>
                                        <td hidden="">
                                            <input id="row-packcity-${city.count}-id" name="row-packcity-${city.count}-id" type="text" class="form-control" value="${pa.id}">
                                        </td>
                                        <td>
                                            ${city.count}
                                        </td>
                                        <td class="form-group">                 
                                            <input id="input-get-city-${city.count}" value="${pa.MCity.id}" hidden="">
                                                        <!--<input type="text" class="form-control cityName" id="select-passneger-${city.count}" name="row-city-${city.count}-name"  valHidden="${cityList.id}" value="${cityList.name}"  />-->
                                            
                                            <select name="row-city-${city.count}-name" id="row-city-${city.count}-name" class="form-control selectize">
                                                <option value="">- - City - -</option>
                                                <c:forEach var="passen" items="${ListCity}">
                                                    <c:set var="select" value="" />
                                                    <c:set var="selectedId" value="${pa.MCity.id}" />
                                                    <c:if test="${passen.id == selectedId}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option value="${passen.id}" ${select}>${passen.name}</option>   
                                                </c:forEach>
                                            </select>                 
                                                
                                            <script>
                                                $(document).ready(function () {
                                                    $("#row-city-${city.count}").val($("#input-get-city-${city.count}").val());
                                                });
                                            </script>
                                        </td>           
                                        <td class="text-center">
<!--                                            <a id="PassengerButtonRemove${city.count}" class="remCF" onclick="ConfirmDelete('3', '${pa.id}', '${city.count-1}')">
                                                <span id="PassengerSpanRemove${city.count-1}" class="glyphicon glyphicon-remove deleteicon"></span>
                                            </a>-->
                                            <a class="remCF" onclick="ConfirmDelete('3', '${pa.id}', '${city.count}')">  
                                                <span  id="SpanRemove${city.count}"  class="glyphicon glyphicon-remove deleteicon"></span>
                                            </a>
                                        </td>
                                        
                                    </tr>
                             
                                    <script>
                                        $(document).ready(function () {
                                            $("#cityCounter").val(parseInt("${city.count}") + 1);
                                        });
                                    </script>
                                </c:forEach>                              
                            </tbody>
                        </table>
                        <input type="hidden" id="filterCity" name="SelectCity" >
                        <input value="${ListPackageCity.size()}" id="table-city-size" type="hidden">
                        <div id="tr_CityAddRow" class="text-center hide" style="padding-top: 10px">
                            <a class="btn btn-success" onclick="CityAddRow()">
                                <i class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                    <!--</div> End-->                       
                    <!--Button Save -->
                    <div class="row" style="padding-top: 10px">
                        <div class="col-xs-12 text-center">
                            <button id="savePackage" name="savePackage"  type="submit" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                        </div>
                    </div>     
                </div>
            </div>
        </div>
        </div>
    </form>
</div>
                        
<!--DELETE MODAL-->
<div class="modal fade" id="DeletePackage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Master Package Detail</h4>
            </div>
            <div class="modal-body" id="delCode">

            </div>
            <div class="modal-footer">
                <button type="submit" onclick="DeleteRowPackage()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
                       
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        // ******************* start Itinerary script *******************
        
         $("#dateFrom-").datepicker({ dateFormat: "yy-mm-dd" }).val()
        
        $("#Itinerary").on("keyup", function() {
            var rowAll = $("#Itinerary tr").length;
            $("#Itinerary td").keyup(function() {               
                if ($(this).find("input").val() != '') {
                    var colIndex = $(this).parent().children().index($(this));
                    var rowIndex = $(this).parent().find(".orderrow").html();//$(this).parent().parent().children().index($(this).parent());    
                    rowAll = $("#Itinerary tr").length-2;
                    if (rowIndex == rowAll) {
                        AddRowItinerary(parseInt($("#counterItinerary").val()));
                    }
                    if ( $("#Itinerary tr").length < 2) {
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
      
        AddRowItinerary(parseInt($("#counterItinerary").val()));
        
        // ******************* end Itinerary script *******************
        $(".money").mask('000,000,000,000,000,000', {reverse: true});
        $('.date').mask('0000-00-00');
        $('.time').mask('00:00');
        $('.number').mask('00');

         // ******************* start PackagePrice script *******************
        $("#PackagePrice").on('click', '.remCF', function() {
        
        });
        $("#PackagePrice").on("keyup", function() {
            var rowAll = $("#PackagePrice tr").length;       
            $("#PackagePrice td").keyup(function() {              
                if ($(this).find("input").val() != '') {                 
                    var colIndex = $(this).parent().children().index($(this));
                     var rowIndex = $(this).parent().find(".orderrow").html();                  
                    rowAll = $("#PackagePrice tr").length-3;
                    if (rowIndex == rowAll) {                        
                        AddRowPackagePrice(parseInt($("#counterPrice").val()));
                    }
                    if ($("#PackagePrice tr") < 2) {
                        $("#tr_PackagePriceAddRow").removeClass("hide");
                        $("#tr_PackagePriceAddRow").addClass("show");
                    }
                }
            });
        });
        $("#tr_PackagePriceAddRow a").click(function() {
            $(this).parent().removeClass("show");
            $(this).parent().addClass("hide");
        });
        
         AddRowPackagePrice(parseInt($("#counterPrice").val()));
        // ******************* start PackagePrice script *****************
           
  
    });

    function deletelist(id) {
        document.getElementById('DelItenarary').value += id + ',';
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
    function AddRowPackagePrice(row) {
        row = parseInt($("#counterPrice").val());
        $("#PackagePrice tbody").append(
                    '<tr>' +
                    '<td class="hidden"><input class="form-control" type="hidden"  style="width:50px" id="row-'+row+'-priceid" name="row-'+row+'-priceid" ></td>'+
                    '<td class="hidden orderrow">'+getrowcountPrice()+'</td>'+
                    '<td><div class="input-group daydatepicker" id="daydatepicker-'+row+'" style="padding-left: 0px"><input style="width: 100%" type="text" class="form-control"  id="row-'+row+'-datefrom" name="row-'+row+'-datefrom" data-date-format="YYYY-MM-DD"/><span class="input-group-addon" style="padding : 1px 10px;"><span class="glyphicon glyphicon-calendar"></span></span></div></td>' +
                    '<td><div class="input-group daydatepicker" id="daydatepicker-'+row+'" style="padding-left: 0px"><input style="width: 100%" type="text" class="form-control"  id="row-'+row+'-dateto" name="row-'+row+'-dateto" data-date-format="YYYY-MM-DD" /><span class="input-group-addon" style="padding : 1px 10px;"><span class="glyphicon glyphicon-calendar"></span></span></div></td>' +
                    '<td><input class="form-control money" maxlength=10  style="width:50px" id="row-'+row+'-adcost" name="row-'+row+'-adcost" ></td>' +
                    '<td><input class="form-control money" maxlength=10 style="width:50px" id="row-'+row+'-chcost" name="row-'+row+'-chcost" ></td>' +
                    '<td><input class="form-control money" maxlength=10 style="width:50px" id="row-'+row+'-incost" name="row-'+row+'-incost" ></td>' +
                    '<td><input class="form-control money" maxlength=10 style="width:50px" id="row-'+row+'-adprice" name="row-'+row+'-adprice" ></td>' +
                    '<td><input class="form-control money" maxlength=10 style="width:50px" id="row-'+row+'-chprice" name="row-'+row+'-chprice" ></td>' +
                    '<td><input class="form-control money" maxlength=10 style="width:50px" id="row-'+row+'-inprice" name="row-'+row+'-inprice" ></td>'+
                    '<td class="text-center">'+
                    '<a class="remCF" onclick="ConfirmDelete(\'2\', \'\', \''+row+'\')">  '+
                    '<span  id="SpanRemove'+row+'"  class="glyphicon glyphicon-remove deleteicon"></span></a></td>'+                   
                    '</tr>'
                    );
        var tempCount = parseInt($("#counterPrice").val()) + 1;
        $("#counterPrice").val(tempCount);
        reloadDatePicker();
    }

    function DeleteRowItinerary() {
        $(this).remove();
        var tempCount = parseInt($("#counterItinerary").val()) - 1;
        $("#counterItinerary").val(tempCount);
    }
     
    function AddRowItinerary(row) {
         row = parseInt($("#counterItinerary").val());
        try {
            $("#Itinerary tbody").append(
                    '<tr style="higth 100px">' +  
                    '<td class="hidden"> <input id="row-'+row+'-itineraryid" name="row-'+row+'-itineraryid"  type="hidden" >  </td>'+
                    '<td class="hidden orderrow">'+getrowcountItinerary()+'</td>'+
                    '<td class="hidden"> <input id="row-' + row + '-id" name="row-' + row + '-id"  type="hidden" >  </td>' +
                    '<td><input style="width: 20px" id="row-' + row + '-no" name="row-' + row + '-no"   type="text" class="form-control number" ></td>' +
                    '<td><input style="width: 70px" type="text" id="row-' + row + '-hour" name="row-' + row + '-hour" class="form-control time" placeholder="HH:MM" ></td>' +
                    '<td><input   class="form-control" maxlength="255" style="width:570px" id="row-' + row + '-des" name="row-' + row + '-des" rows="2" ></td>' +
                    '<td class="text-center">'+
                    '<a class="remCF" onclick="ConfirmDelete(\'1\', \'\', \''+row+'\')">  '+
                    '<span  id="SpanRemove'+row+'"  class="glyphicon glyphicon-remove deleteicon"></span></a></td>'+   
                    '</tr>'
                    );
        } catch (e) {
//            alert(e);
        }       
        var tempCount = parseInt($("#counterItinerary").val()) + 1;
        $("#counterItinerary").val(tempCount);       
    }
</script>

<c:if test="${! empty requestScope['packageLap']}">
    <script language="javascript">
        $('#textAlertLap').show();
    </script>
</c:if>
<c:if test="${! empty requestScope['result']}">
    <c:if test="${requestScope['result'] =='save successful'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='save unsuccessful'}">        
        <script language="javascript">
           $('#textAlertDivNotSave').show();
        </script>
    </c:if>
</c:if>