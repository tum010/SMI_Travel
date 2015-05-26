<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/MpackageDetail.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<c:set var="itinerarylist" value="${requestScope['itinerary_List']}" />
<c:set var="pricelist" value="${requestScope['price_list']}" />
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

<div class ="container"  style="padding-top: 15px;"> 
    <form action="MPackageDetail.smi" method="post" id="PackageForm" role="form" class="form-horizontal">
        <div class="col-md-8 col-xs-offset-2">
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
                                    <input type="text" ${requestScope['disabledcode']} class="form-control" id="packagecode"  maxlength="50" name="packagecode"  value="${requestScope['packagecode']}" >  

                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fromcity">Name<font style="color: red">*</font></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="packagename" maxlength="255" name="packagename" value="${requestScope['packagename']}" >  
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


                        </div>                

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
                                        <td><input style="width: 100px" onchange="setValidate();" type="text" id="row-${priceStatus.count-1}-datefrom" name="row-${priceStatus.count-1}-datefrom" class="form-control date" placeholder="YYYY-MM-DD" autocomplete="off" value="${table1.effectiveFrom}"></td>
                                        <td><input style="width: 100px" onchange="setValidate();" type="text" id="row-${priceStatus.count-1}-dateto" name="row-${priceStatus.count-1}-dateto" class="form-control date" placeholder="YYYY-MM-DD" autocomplete="off" value="${table1.effectiveTo}"></td>
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
                        <input type="hidden"  id="counterPrice" name="counterPrice" value="0" />
                        <input type="hidden"  id="rowType" name="rowType" />
                        <input type="hidden"  id="Itiid" name="Itiid" />
                        <input type="hidden"  id="checkValidate" name="checkValidate" />
                        <input type="hidden"  id="cCount" name="cCount" />
                        <input type="hidden"  id="packageid" name="packageid" value="${requestScope['packageid']}" />
                        
                   
                         
                        
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
                <button type="button" onclick="DeleteRow()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
                        

<script type="text/javascript" charset="utf-8">

    $(document).ready(function() {
  

        // ******************* start Itinerary script *******************

        $("#Itinerary").on("keyup", function() {
            var rowAll = $("#Itinerary tr").length;

            $("#Itinerary td").keyup(function() {
                
                if ($(this).find("input").val() != '') {
                    var colIndex = $(this).parent().children().index($(this));
                    var rowIndex = $(this).parent().find(".orderrow").html();//$(this).parent().parent().children().index($(this).parent());    
                    rowAll = $("#Itinerary tr").length-2;
                //    alert(rowIndex+','+rowAll);
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
         //   $(this).parent().parent().remove();
         //   var rowAll = $("#PackagePrice tr").length;
         //   if (rowAll < 3) {
         //       $("#tr_PackagePriceAddRow").removeClass("hide");
         //       $("#tr_PackagePriceAddRow").addClass("show");
          //  }
        });
        $("#PackagePrice").on("keyup", function() {
            var rowAll = $("#PackagePrice tr").length;
         
            $("#PackagePrice td").keyup(function() {
                
                if ($(this).find("input").val() != '') {
                  
                    var colIndex = $(this).parent().children().index($(this));
                     var rowIndex = $(this).parent().find(".orderrow").html();
                    //var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 3;
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
        // ******************* start PackagePrice script *******************

    });

    function deletelist(id) {
        document.getElementById('DelItenarary').value += id + ',';
    }



    function AddRowPackagePrice(row) {
        row = parseInt($("#counterPrice").val());
        $("#PackagePrice tbody").append(
                    '<tr>' +
                    '<td class="hidden"><input class="form-control" type="hidden"  style="width:50px" id="row-'+row+'-priceid" name="row-'+row+'-priceid" ></td>'+
                    '<td class="hidden orderrow">'+getrowcountPrice()+'</td>'+
                    '<td><input style="width: 100px" onchange="setValidate();"  type="text" id="row-'+row+'-datefrom" name="row-'+row+'-datefrom" class="form-control date" placeholder="YYYY-MM-DD" autocomplete="off"></td>' +
                    '<td><input style="width: 100px" onchange="setValidate();"  type="text" id="row-'+row+'-dateto" name="row-'+row+'-dateto" class="form-control date" placeholder="YYYY-MM-DD" autocomplete="off"></td>' +
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
            alert(e);
        }
        
        var tempCount = parseInt($("#counterItinerary").val()) + 1;
        $("#counterItinerary").val(tempCount);
        
    }




</script>

<c:set var="result" value="${requestScope['result']}" />
<c:if test="${! empty result}">
        <script language="javascript">
            alert('${result}');  
        </script>
        <META HTTP-EQUIV="Refresh" CONTENT="0;URL=MPackageDetail.smi?packageid=${requestScope['packageid']}&action=edit">
</c:if>

<c:if test="${! empty requestScope['packageLap']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['packageLap']}" />');
    </script>
</c:if>

    
    