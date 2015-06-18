<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<script type="text/javascript" src="js/DaytourTransfer.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="tourdate" value="${requestScope['tourdate']}" />
<c:set var="job" value="${requestScope['TransferJob']}" />
<c:set var="guideList" value="${requestScope['GuideList']}" />
<c:set var="driverList" value="${requestScope['DriverList']}" />
<c:set var="result" value="${requestScope['result']}" />
<c:set var="newAction" value="${requestScope['newAction']}" />
<c:set var="result" value="${requestScope['result']}" />
<input type="hidden" value="${param.referenceNo}" id="getUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">

<section class="content-header" >
    <h1>
        Operation - Transfer Job
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Operation</a></li>          
        <li class="active"><a href="#">Transfer Job</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!--Alert Save and Update-->
        <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Success!</strong> 
        </div>
        <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Save Unsuccess!</strong> 
        </div>
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Book/DaytourMenu.html'"></div>
        </div>
        <div class="col-sm-10">
            <form action="DaytourTransfer.smi" id="saveDaytourTransferForm" name="saveDaytourTransferForm" method="post" role="form" >
                <input id="transferJobId" name="transferJobId" type="hidden" class="form-control" value="${job.id}">
                <input id="action" name="action" type="hidden" class="form-control" value="save">
                <input id="newAction" name="newAction" type="hidden" class="form-control" value="${newAction}">

                <!--<div ng-include="'WebContent/Book/BookNavbar.html'"></div>-->
                <div class="row" style="padding-left: 15px">  
                    <div class="col-sm-6 " style="padding-right: 15px">
                        <h4><b>Day Tours Transfer</b></h4>
                    </div>
                </div>
                <hr/>

                <div class="col-xs-12 ">
                    <div class="col-xs-1  text-right">
                        <label class="control-label">DocNo.</label>
                    </div>

                    <div class="col-xs-3 form-group">
                        <input id="InputDocument" name="InputDocument" type="text" class="form-control" readonly="" value="${job.documentNo}">
                    </div>
                    <div class="col-xs-1 text-right">
                        <label class="">Date<font style="color: red">*</font></label>
                    </div>
                    <div class="col-xs-3 form-group" >
                        <div class="input-group date" >
                            <input id="InputDate" name="InputDate" type="text" 
                                   data-date-format="YYYY-MM-DD" class="form-control datemask" 
                                   placeholder="YYYY-MM-DD" value="${tourdate}" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 form-group">
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">Guide</label>
                    </div>
                    <div class="col-xs-3">
                        <select id="InputGuide" name="InputGuide" class="form-control selectize">
                            <option value="">---All Guides---</option>
                            <c:set var="select" value="" />
                            <c:set var="selectedId" value="${job.staffByGuildeId.id}" />
                            <c:forEach var="guide" items="${guideList}">
                                <c:if test="${guide.id == selectedId}">
                                    <c:set var="select" value="selected" />
                                </c:if>
                                <option value="${guide.id}" ${select}>${guide.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">Driver</label>
                    </div>
                    <div class="col-xs-3">
                        <select id="InputDriver" name="InputDriver" class="form-control selectize"> 
                            <option value="">---All Drivers ---</option>
                            <c:set var="select" value="" />
                            <c:set var="selectedId" value="${job.staffByDriverId.id}" />                            
                            <c:forEach var="driver" items="${driverList}">
                                <c:if test="${driver.id == selectedId}">
                                    <c:set var="select" value="selected" />
                                </c:if>                                
                                <option value="${driver.id}">${driver.name}</option>
                            </c:forEach></select>
                    </div>
                </div>

                <div class="col-xs-12 form-group">
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">Remark</label>
                    </div>
                    <div class="col-xs-7">
                        <textarea id="InputRemark" name="InputRemark" class="form-control" maxlength="255" >${job.remark}</textarea>
                    </div>
                </div>

                <!--Tour Table begin-->
                <div class="col-xs-12 form-group">
                    <div class="col-sm-1 text-right">
                        <label class="control-label">Tour<font style="color: red">*</font></label>
                    </div>
                    <div class="col-sm-5"></div>
                    <div class="col-sm-2 text-right">
                        <a id="ButtonImportTour" name="ButtonImportTour" class="btn btn-sm btn-info"  data-toggle="modal" data-target="#TourModal">
                            <i class="glyphicon glyphicon-plus"></i>&nbsp;Import
                        </a>
                    </div>
                </div>
                <div class="col-xs-12 " style="margin-bottom: 15px;margin-top:-10px">
                    <div class="col-sm-7 col-sm-offset-1">
                        <table class="display" id="transferTourTable" name="transferTourTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:20%">Tour Code</th>
                                    <th style="width:65%">Tour Name</th>
                                    <th style="width:15%">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--import tour data-->
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--Tour Table end-->

                <!--Hotel Table begin-->
                <div class="col-xs-12 form-group">
                    <div class="col-sm-1 text-right">
                        <label class="control-label">Hotel<font style="color: red">*</font></label>
                    </div>
                    <div class="col-sm-2 col-sm-offset-5 text-right"><!--winita-->
                        <a id="ButtonImportHotel" name="ButtonImportHotel" class="btn btn-sm btn-info" onclick="removeDupHotelRow();" data-toggle="modal" data-target="#HotelModal">
                            <i class="glyphicon glyphicon-plus"></i>&nbsp;Import
                        </a>
                    </div>
                </div>
                <div class="col-xs-12" style="margin-top: -10px">
                    <div class="col-sm-7 col-sm-offset-1">
                        <table class="display" id="transferHotelTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th>Name</th>
                                    <th style="width:10%">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--import hotel data-->
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--Hotel Table end-->

                <!--Other Table begin-->
                <div class="col-xs-12 form-group" style="margin-top:15px">
                    <div class="col-sm-1 text-right">
                        <label class="control-label">Other</label>
                    </div>
                    <div class="col-sm-2 col-sm-offset-5 text-right">
                        <a id="ButtonImportOther" name="ButtonImportOther" onclick="removeDupOtherRow();" class="btn btn-sm btn-info" data-toggle="modal" data-target="#OtherModal">
                            <i class="glyphicon glyphicon-plus"></i>&nbsp;Import
                        </a>
                    </div>
                </div>
                <div class="col-xs-12" style="margin-top: -10px">
                    <div class="col-sm-7 col-sm-offset-1">
                        <table class="display" id="transferOtherTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th>Name</th>
                                    <th style="width:10%">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--import other data-->
                            </tbody>
                        </table>
                    </div>
                </div>         
                <!--Other Table end-->

                <div class="col-xs-9 form-group text-center" style="margin-top: 15px">
                    <a id="ButtonPrint" name="ButtonPrint" onclick="printTransferJob();" class="btn btn-default"><i class="fa fa-print"></i> Print</a>
                    <!--winit--> <button type="button"  id="ButtonSave" name="ButtonSave" onclick="saveDaytourTransfer();" class="btn btn-primary"><i class="fa fa-save"></i> Save</button>
                    <button type="button" id="ButtonSaveAndNew" name="ButtonSaveAndNew" onclick="saveAndNewDaytourTransfer();" class="btn btn-primary"><i class="fa fa-save"></i> Save & New</button>
                </div>
                <input type='hidden' class="form-control"  id="tourTableList" name="tourTableList"  value="${job.tour}" >
                <input type='hidden' class="form-control"  id="hotelTableList" name="hotelTableList" value="${job.place}">
                <input type='hidden' class="form-control"  id="otherTableList" name="otherTableList" value="${job.placeOther}" >

            </form>
        </div>
    </div>

</div>

<!--Tour Delete Row winit-->
<div class="modal fade" id="DeleteTourModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Tour</h4>
            </div>
            <div class="modal-body" id="delTourContent"></div>
            <input type="hidden" id="deleteTourId" name="tourid"/>
            <input type="hidden" id="deleteTourPlaceId" name="tourplaceid"/>
            <input type="hidden" id="deleteTourPlaceName" name="tourplacename"/>
            <input type="hidden" id="deleteTourName" name="tourname"/>
            <div class="modal-footer">
                <button id="btnTourDelete" type="button" onclick="removeTourRow();" class="btn btn-danger">Delete</button>
                <button id="btnTourClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->               

<!--Hotel Delete Row winit-->
<div class="modal fade" id="DeleteHotelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Hotel</h4>
            </div>
            <div class="modal-body" id="delHotelContent"></div>
            <input type="hidden" id="deleteHotelId" name="deleteHotelId"/>
            <input type="hidden" id="deleteHotelName" name="deleteHotelName"/>
            <div class="modal-footer">
                <button id="btnHotelDelete" type="button" onclick="removeHotelRow();" class="btn btn-danger">Delete</button>
                <button id="btnHotelClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal --> 

<!--Other Delete Row winit-->
<div class="modal fade" id="DeleteOtherModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Hotel</h4>
            </div>
            <div class="modal-body" id="delOtherContent"></div>
            <input type="hidden" id="deleteOtherId" name="deleteOtherId"/>
            <div class="modal-footer">
                <button id="btnOtherDelete" type="button" onclick="removeOtherRow();" class="btn btn-danger">Delete</button>
                <button id="btnOtherClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal --> 



<!--Tour Modal-->
<div class="modal fade" id="TourModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Tour</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="tourTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th style="width:20%">Code</th>
                            <th style="width:65%">Name</th>
                            <th style="width:15%">Action</th>
                        </tr>
                    </thead>
                    <tbody id="tourTableBody">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="tourModalOkBtn" name="tourModalOkBtn" type="button" onclick="clickTourModalOk();" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!--Hotel Modal-->
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
                            <th>Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="hotelModalOkBtn" name="hotelModalOkBtn" type="button" onclick="clickHotelModalOk();" class="btn btn-success" >OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>


<!--Other Modal-->
<div class="modal fade" id="OtherModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Other</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="otherTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="otherModalOkBtn" name="otherModalOkBtn" type="button" onclick="clickOtherModalOk();" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Date Change Modal-->
<div class="modal fade" id="DateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Date Change</h4>
            </div>
            <div class="modal-body" id="delOtherContent"></div>
            <input type="hidden" id="DateId" name="DateId"/>
            <div class="modal-footer">
                <button id="btnDateOk" type="button" onclick="" class="btn btn-danger">OK</button>
                <button id="btnDateClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal --> 
<c:if test="${! empty result}">
    <c:choose>
        <c:when test="${result =='success'}">
            <script language="javascript">
                $('#textAlertDivSave').show();
            </script>
        </c:when>
        <c:otherwise>
            <script language="javascript">
                $('#textAlertDivNotSave').show();
            </script>
        </c:otherwise>
    </c:choose>   
</c:if> 
<script>
    $(document).ready(function () {
        $("#ButtonImportTour").attr("disabled", "disabled");
        $("#ButtonImportHotel").attr("disabled", "disabled");
        $("#ButtonImportOther").attr("disabled", "disabled");

        var $selectGuide = $('#InputGuide').selectize({
            create: false,
            sortField: 'text'
        });

        var $selectDriver = $('#InputDriver').selectize({
            create: false,
            sortField: 'text'
        });



        if ($("#InputDocument").val().length > 0) {

            console.log("Open Document No. = " + $("#InputDocument").val());

            var controlGuide = $selectGuide[0].selectize;
            controlGuide.setValue([${job.staffByGuildeId.id}]);

            var controlDriver = $selectDriver[0].selectize;
            controlDriver.setValue([${job.staffByDriverId.id}]);

            if ($("#tourTableList").length > 0) {

                var listTourcode = $("#tourTableList").val();
                console.log("ListTourCode = " + listTourcode);
                var x = $("#transferTourTable tbody").find("tr").length;
                console.log("lenght tour table :" + x);
                var inputTourDate = $("#InputDate").val();
                getTourFromDate(inputTourDate);
            }

            if ($("#hotelTableList").length > 0) {
                var listnames = $("#hotelTableList").val();
                console.log("hotels = " + listnames);
            }
        } else if ($("#tourTableList").length > 0) {
            // To Support Save and New action. This block intention to retrieve value.
            var listTourcode = $("#tourTableList").val();
            console.log("ListTourCode = " + listTourcode);

            var inputTourDate = $("#InputDate").val();
            getTourFromDate(inputTourDate);
        }
        //datetime

        $('.date').datetimepicker().change(function () {
            var newDate = $("#InputDate").val();
            $("#ButtonImportHotel").attr("disabled", "disabled");
            $("#transferTourTable tbody").empty();
            $("#transferHotelTable tbody").empty();
            $("#transferOtherTable tbody").empty();

            console.log("inputDate onchange - " + newDate);
            if ((newDate.trim().length > 0) && (newDate.trim().length === 10)) {
                getTourFromDate(newDate);
            } else {
                console.log("ImportTour disabled.");
                $("#ButtonImportTour").attr("disabled", "disabled");
                $("#ButtonImportHotel").attr("disabled", "disabled");
            }
        });

        checkChangeDate();


    });

    function checkChangeDate() {
//    &&  $("#transferTourTable tbody").find("tr").length > 0
        if ($("#InputDocument").val() !== "") {
            window.tmpDate = $('#InputDate').val();
            $("#datetimepicker").on("dp.change", function (e) {
                if (!confirm("Are you sure to change the date \n that clear your data in Tour, Hotel and Other Table ?")) {
                    $('#InputDate').val(window.tmpDate);
                } else {
                    window.tmpDate = $('#InputDate').val();//e.date
                }
            });
        }
    }

    function pullSelectedTour() {
        var matchedFlag = false;
        var selectedList = $("#tourTableList").val();

        var tourCodeArray = selectedList.split("||");
        $("#tourTable tbody").find("tr").each(function () {
            var objId = $(this).find('td.tourid').html();
            var objElem = $(this).find('td.tourcode');
            for (var i = 0; i < tourCodeArray.length; i++) {
                console.log("tourcode - " + objElem.html() + ", codeSearch=[" + tourCodeArray[i] + "]");
                if (tourCodeArray[i].trim() === objElem.html()) {
                    matchedFlag = true;
                    $("#row-" + objId + "-tour").prop("checked", true);

                }
            } // end for loop tourCodeArray;
        }); // end tr loop;

        if (matchedFlag) {
            clickTourModalOk();
        }
    }

    function clickTourModalOk() {
        var tourId;
        var tourPlaceId;
        var tourPlaceName;
        var tourName;
        var tourCode;
        var counter = 0;
        var replaceTourName;
        var replaceTourPlaceName;
        var rowcount = $('#transferTourTable tbody tr').length;

        $('#tourTable tbody').find('tr').each(function () {
            counter++;
            $(this).find('td').each(function () {
                if ($(this).hasClass('tourid')) {
                    tourId = $(this).html();
                }
                if ($(this).hasClass('tourplaceid')) {
                    tourPlaceId = $(this).html();
                }
                if ($(this).hasClass('tourplacename')) {
                    tourPlaceName = $(this).html();
                    replaceTourPlaceName = tourPlaceName.replace(/\s/gi, "_");
                }
                if ($(this).hasClass('tourname')) {
                    tourName = $(this).html();
                    replaceTourName = tourName.replace(/\s/gi, "_");
                }
                if ($(this).hasClass('tourcode')) {
                    tourCode = $(this).html();
                }
                var eachCheckbox = $(this).children();
                if ($(eachCheckbox).is(':checked')) { //element checked winit
                    rowcount += 1;
                    $("#transferTourTable tbody").append(
                            '<tr class="Tr-' + tourId + '">' +
                            '<td class="hide "><input type="text" class="tourId" name="daytourId-' + rowcount + '" id="daytourId-' + rowcount + '" value="' + tourId + '"></td>' +
                            '<td class="hide "><input type="text" class="tourPlaceId" name="daytourPlaceId-' + rowcount + '" id="daytourPlaceId-' + rowcount + '" value="' + tourPlaceId + '"></td>' +
                            '<td class="tourCode">' + tourCode + '</td>' +
                            '<td class="tourName">' + tourName + '</td>' +
                            '<td class="text-center">' +
                            '<a id="RowButtonRemove-"' + $(eachCheckbox).attr('id') + '"  name="RowButtonRemove-"' + $(eachCheckbox).attr('id') + '"  chkId="' + $(eachCheckbox).attr('id') + '"  class="RemoveRow" >' +
                            '<span id="RowSpanRemove-"' + $(eachCheckbox).attr('id') + '"  name="RowSpanRemove-"' + $(eachCheckbox).attr('id') + '" ' +
                            'class="glyphicon glyphicon-remove deleteicon" onclick=deleteTour("' + tourId + '","' + tourPlaceId + '","' + replaceTourPlaceName + '","' + replaceTourName + '"); data-toggle="modal" data-target="#DeleteTourModal" ></span>' +
                            '</a></td>' +
                            '</tr>'
                            );
                    $(eachCheckbox).removeAttr('checked');
                    $(eachCheckbox).attr('disabled', 'disabled');
                } else {
                    $("#TourModal").modal('hide');
                }
                $('#tourTable').find('input:checkbox:disabled').closest('tr').addClass('hidden');
            });
        });

        getActiveHotel();
//        getActiveOther();
        $("#hotelModalOkBtn").trigger("click");
        $("#TourModal").modal('hide');
    }


    function removeDupHotelRow() {
        var hotelIdChk;
        var hotelNameChk;
        $("#hotelTable tbody").find("tr").each(function () {
            $(this).find("td").each(function () {
                if ($(this).hasClass("placeid")) {
                    hotelIdChk = $(this).html();
                }
                if ($(this).hasClass("placename")) {
                    hotelNameChk = $(this).html();
                }
            });
            if ($("#transferHotelTable tbody tr > td:contains('" + hotelIdChk + "')").length > 0) {
//                    alert("NameHOTEL2 , hotelNameChk:"+hotelNameChk);
                console.log('hotelIdChk : ' + hotelIdChk);
                console.log('hotelNameChk : ' + hotelNameChk);
                $('#trPlaceId' + hotelIdChk).addClass('hidden');
            }
        });

    }

    function removeDupOtherRow() {
        var otherIdChk;
        var otherNameChk;
        $("#otherTable tbody").find("tr").each(function () {
            $(this).find("td").each(function () {
                if ($(this).hasClass("otherid")) {
                    otherIdChk = $(this).html();
                }
                if ($(this).hasClass("othername")) {
                    otherNameChk = $(this).html();
                }
                if ($("#transferOtherTable tbody tr > td:contains('" + otherNameChk + "')").length > 0) {
                    $('#otherTable tbody tr > td:contains("' + otherNameChk + '")').parent().addClass('hidden');
                }
            });
        });
    }

    function deleteTour(id, placeid, placename, name) {
        $("#deleteTourId").val(id);
        $("#deleteTourPlaceId").val(placeid);
        $("#deleteTourPlaceName").val(placename);
        $("#deleteTourName").val(name);
        $("#delTourContent").html("Are you sure to delete Tour : " + name + " ?");
    }


    function removeTourRow() {
        var idx = $("#deleteTourId").val();
        var idplace = $("#deleteTourPlaceId").val();
        var nameplace = $("#deleteTourPlaceName").val();
        console.log("idplace : " + idplace);
        console.log("nameplace : " + nameplace);
        if (idplace === $("#transferHotelTable tbody tr > td:contains('" + idplace + "')").html()) {
            alert("Please delete  Hotel Name : " + nameplace + " in Hotel table before..");
        } else {
            $("#transferTourTable tbody").find("tr.Tr-" + idx).remove();
            var tourId = idx;
            console.log("tourId = " + tourId);
            $('#' + 'trTourId' + tourId).removeClass('hidden');
            $('#' + 'row-' + tourId + '-tour').removeAttr('disabled');
            getActiveHotel();
            $("#DeleteTourModal").modal('hide');
        }
        $("#DeleteTourModal").modal('hide');
    }



    function getActiveHotel() {
        var activeTourList = "";
        var transferTourLength = $('#transferTourTable tbody').find('tr').length;
        if (transferTourLength === 0) {
            clearHotelTable();
            clearOtherTable();
            return;
        }
        $('#transferTourTable tbody').find('tr').each(function () {
            $(this).find('td .tourId').each(function () {
                if (activeTourList.length === 0) {
                    activeTourList += $(this).val();
                } else {
                    activeTourList += "," + $(this).val();
                }
            });
        });
//        alert("activeTourList Hotel:"+activeTourList);
        var tourDate = $("#InputDate").val();
        getPlaceFromDateAndTour(tourDate, activeTourList); //step 1
    }

    function clearHotelTable() {
        $("#transferHotelTable tbody").empty();
        $("#hotelTable tbody").empty();
        $("#ButtonImportHotel").attr("disabled", "disabled");
    }

    function clearOtherTable() {
        $("#transferOtherTable tbody").empty();
        $("#otherTable tbody").empty();
        $("#ButtonImportOther").attr("disabled", "disabled");
    }

    function pullSelectedHotel() {  // bug 1
        var matchedFlag = false;
        var selectedList = $("#hotelTableList").val();
        var hotelNameArray = selectedList.split("||");
        console.log("hotelNameArray length = " + hotelNameArray.length);
        $("#hotelTable tbody").find("tr").each(function () {
            console.log("elem -" + $(this).html());
            var objId = $(this).find('td.placeid').html();
            var objElem = $(this).find('td.placename');
            for (i = 0; i < hotelNameArray.length; i++) {
                console.log("hotelName - " + objElem.html());
                if (hotelNameArray[i].trim() === objElem.html()) {
                    matchedFlag = true;
                    if (objId === $("#transferHotelTable tbody tr > td:contains('" + objId + "')").html()) {
                        $("#row-" + objId + "-place").prop("checked", false);
                        $("#row-" + objId + "-place").closest("tr").addClass("hidden");
                    } else {
                        $("#row-" + objId + "-place").prop("checked", true);
                    }

                }
            }
        });

        if (matchedFlag) {
            console.log("clickOkay");
            clickHotelModalOk();
        }

    }

    function clickHotelModalOk() {
        var hotelId;
        var hotelName;
        var replaceHotelName;
        $('#hotelTable').find('tr').each(function () {
            $(this).find('td').each(function () {
                if ($(this).hasClass('placeid')) {
                    hotelId = $(this).html();
                }
                if ($(this).hasClass('placename')) {
                    hotelName = $(this).html();
                    replaceHotelName = hotelName.replace(/\s/gi, "_"); // for delete

                }
                var eachCheckbox = $(this).children();
                if ($(eachCheckbox).is(':checked')) { //element checked 
                    $("#transferHotelTable tbody").append(
                            '<tr class="Tr-' + hotelId + '">' +
                            '<td class="id hide">' + hotelId + '</td>' +
                            '<td class="name">' + hotelName + '</td>' +
                            '<td class="text-center">' +
                            '<a id="RowButtonRemove-"' + $(eachCheckbox).attr('id') + '" name="RowButtonRemove-"' + $(eachCheckbox).attr('id') + '" chkId="' + $(eachCheckbox).attr('id') + '" class="RemoveRow">' +
                            '<span id="RowSpanRemove-"' + $(eachCheckbox).attr('id') + '" name="RowSpanRemove-"' + $(eachCheckbox).attr('id') + '" ' +
                            'class="glyphicon glyphicon-remove deleteicon" onclick=deleteHotel("' + hotelId + '","' + replaceHotelName + '"); data-toggle="modal" data-target="#DeleteHotelModal"></span>' +
                            '</a></td>' +
                            '</tr>'
                            );
                    $(eachCheckbox).removeAttr('checked');
                    $(eachCheckbox).attr('disabled', 'disabled');
                }
                $('#hotelTable').find('input:checkbox:disabled').closest('tr').addClass('hidden');
            });
        });
        getActiveOther();
        $("#HotelModal").modal('hide');

    }

    function deleteHotel(id, name) {
        $("#deleteHotelId").val(id);
        $("#deleteHotelName").val(name);
        $("#delHotelContent").html("Are you sure to delete Hotel : " + name + " ?");
    }

    function removeHotelRow() {
        var idx = $("#deleteHotelId").val();
        var namex = $("#deleteHotelName").val();
        console.log('namex :' + namex);
        console.log('idx :' + idx);
        if (namex === 'OTHERS' && $("#transferOtherTable tbody").find('tr').length > 0) {
            console.log('namex :' + namex);
            console.log('idx :' + idx);
            alert("Please delete data in Other Table before..");
        } else {
            $("#transferHotelTable tbody").find("tr.Tr-" + idx).remove();
            var hotelId = idx;
            console.log("hotelId = " + hotelId);
            $('#trPlaceId' + hotelId).removeClass('hidden');
            $('#row-' + hotelId + '-place').removeAttr('disabled');
            $("#DeleteHotelModal").modal('hide');
        }
        $("#DeleteHotelModal").modal('hide');
    }

    function getActiveOther() {
        var foundOther = false;
        $('#transferHotelTable tbody').find('tr').each(function () {
            $(this).find('td.name').each(function () {
                var name = $(this).html();
                if ("OTHERS" === name) {
                    foundOther = true;
                }
            });
        });

        if (foundOther) {
            var activeTourList = "";
            $('#transferTourTable tbody').find('tr').each(function () {
                $(this).find('td .tourId').each(function () {
                    if (activeTourList.length === 0) {
                        activeTourList += $(this).val();
                    } else {
                        activeTourList += "," + $(this).val();
                    }
                console.log("activeList Other= " + activeTourList);
            });
    }

    function getOtherFromDateAndTour(inputDate, tourId) {
        var servletName = 'TransferJobServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&date=' + inputDate +
                '&tourid=' + tourId +
                '&type=' + 'filterOther';
        console.log("AjaxOther param [" + param + "]");
        CallAjaxOther(param);
    }

    function getPlaceFromDateAndTour(inputDate, tourId) {
        var servletName = 'TransferJobServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&date=' + inputDate +
                '&tourid=' + tourId +
                '&type=' + 'filterPlace';
        console.log("Ajax param [" + param + "]");
        CallAjaxHotel(param);
    }

    function getTourFromDate(inputDate) {
        var servletName = 'TransferJobServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&date=' + inputDate +
                '&type=' + 'filterTour';
        console.log("Ajax param [" + param + "]");
        CallAjaxTour(param);
    }


    function CallAjaxOther(param) {
        var url = 'AJAXServlet';
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function (msg) {
                    console.log("CallAjaxOther Msg [" + msg + "]");
//                    console.log("Call AJax Hotel Msg [" + msg + "]");
                    if (msg) {
                        var html = $.parseHTML(msg);
                        if (html.length > 0) {
                            $("#ButtonImportOther").removeAttr("disabled");
                            $("#otherTable tbody").empty().append(msg);
                            if ($("#InputDocument").val() !== "") {
                                pullSelectedOther();
                            }
                        } else {
                            $("#ButtonImportOther").attr("disabled", "disabled");
                        }
                    } else {
                        $("#ButtonImportOther").attr("disabled", "disabled");
                    }
                    //setformat();
                }, error: function (msg) {
                    $("#otherTable tbody").empty();
                    console.log('CallAjaxOther error ' + msg);
                    $("#ButtonImportOther").attr("disabled", "disabled");
                }
            });
        } catch (e) {
            alert(e);
        }
    }

    function CallAjaxHotel(param) {
        var url = 'AJAXServlet';
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function (msg) {
//                    console.log("Call AJax Hotel Msg [" + msg + "]");
//            alert("activeTourList stil:"+activeTourList);
            var tourDate = $("#InputDate").val();
            getOtherFromDateAndTour(tourDate, activeTourList);
        }
                    if (msg) {
                        var html = $.parseHTML(msg);
                        if (html.length > 1) {
                            $("#ButtonImportHotel").removeAttr("disabled");
                            $("#hotelTable tbody").empty().append(msg);
                            if ($("#InputDocument").val() !== "") {
                                pullSelectedHotel();
                            }
                        } else {

                            $("#ButtonImportHotel").attr("disabled", "disabled");
                        }
                    } else {
                        $("#ButtonImportHotel").attr("disabled", "disabled");
                    }



                    //setformat();
                }, error: function (msg) {
                    $("#hotelTable tbody").empty();
                    console.log('error hotel ' + msg);
                    $("#ButtonImportHotel").attr("disabled", "disabled");
                }
            });
        } catch (e) {
            alert(e);
        }
    }


    function CallAjaxTour(param) {
        var url = 'AJAXServlet';
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function (msg) {
                    if (msg) {
                        var html = $.parseHTML(msg);
                        if (html.length > 1) {
                            console.log("html.len = " + html);
                            $("#ButtonImportTour").removeAttr("disabled");
                            $("#tourTable tbody").empty().append(msg);
                            var newDoc = $("#newAction").val();
                            if (($("#InputDocument").val().length > 0) || (newDoc === "1")) {
                                console.log("doc len =" + $("#InputDocument").val().length);
                                pullSelectedTour();
                                $("#newAction").val("0");
                            }
                        } else {
                            $("#ButtonImportTour").attr("disabled", "disabled");
                        }
                    } else {
                        $("#ButtonImportTour").attr("disabled", "disabled");
                    }
                    // retrieve save Document.

                }, error: function (msg) {
                    $("#tourTable tbody").empty();
                    console.log('error tour ' + msg);
                    $("#ButtonImportTour").attr("disabled", "disabled");
                }
            });
        } catch (e) {
            alert(e);
        }
    }

    function saveDaytourTransfer() {

        if ($("#InputDate").val() === "") {
            alert("please select date ! ");
        } else if ($("#transferTourTable tbody").find("tr").length < 1) {
            alert("please import tour data ! ");
        } else if ($("#transferHotelTable tbody").find("tr").length < 1) {
            alert("please import hotel data ! ");
        } else if ($("#transferHotelTable tbody").find("tr").length > 0) {
            if ($("#transferHotelTable tbody tr > td:contains('OTHERS')").length > 0 &&
                    $("#transferOtherTable tbody").find("tr").length < 1)
            {
                alert("please import other data !");
            } else {
                prepareDaytourTransferForm();
                $("#action").val("save");
                $("#saveDaytourTransferForm").submit();
            }
        }
    }

    function saveAndNewDaytourTransfer() {
        if ($("#InputDate").val() === "") {
            alert("please select date ! ");
        } else
        if ($("#transferTourTable tbody").find("tr").length < 1) {
            alert("please import tour data ! ");
        } else if ($("#transferHotelTable tbody").find("tr").length < 1) {
            alert("please import hotel data ! ");
        } else if ($("#transferHotelTable tbody").find("tr").length > 0) {
            if ($("#transferHotelTable tbody tr > td:contains('OTHERS')").length > 0 &&
                    $("#transferOtherTable tbody").find("tr").length < 1)
            {
                alert("please import other data !");
            } else {
                prepareDaytourTransferForm();
                $("#action").val("saveANDnew");
                $("#saveDaytourTransferForm").submit();
            }

        }


    }


    function prepareDaytourTransferForm() {
        var activeTourCodeList = "";
        $('#transferTourTable tbody').find('tr').each(function () {
            var tourCode = $(this).find('.tourCode').html();
            if (activeTourCodeList.length === 0) {
                activeTourCodeList += tourCode;
            } else {
                activeTourCodeList += " || " + tourCode;
            }
        });

        var activeHotelList = "";
        $('#transferHotelTable tbody').find('tr').each(function () {
//        console.log("hotel - " + $(this).html());
            var hotel = $(this).find('.name').html();
            if (activeHotelList.length === 0) {
                activeHotelList += hotel;
            } else {
                activeHotelList += " || " + hotel;
            }
        });
        var activeOtherList = "";
        $('#transferOtherTable tbody').find('tr').each(function () {
//        console.log("hotel - " + $(this).html());
            var name = $(this).find('.name').html();
            if (activeOtherList.length === 0) {
                activeOtherList += name;
            } else {
                activeOtherList += " || " + name;
            }
        });

        $("#tourTableList").val(activeTourCodeList);
        $("#hotelTableList").val(activeHotelList);
        $("#otherTableList").val(activeOtherList);
    }
    function pullSelectedOther() { //bugme
        var matchedFlag = false;
        var selectedList = $("#otherTableList").val();
        var nameArray = selectedList.split("||");
        console.log("other nameArray length = " + nameArray.length);
        $("#otherTable tbody").find("tr").each(function () {
            var objId = $(this).find('td.otherid').html();
            var objElem = $(this).find('td.othername');
            for (i = 0; i < nameArray.length; i++) {
                if (nameArray[i].trim() === objElem.html()) {
                    matchedFlag = true;
                    if (objId === $("#transferOtherTable tbody tr > td:contains('" + objId + "')").html()) {
                        $("#row-" + objId + "-other").prop("checked", false);
                    } else {
                        $("#row-" + objId + "-other").prop("checked", true);
                    }
                }
            }
        });

        if (matchedFlag) {
            console.log("clickOkay OtherModal");
            clickOtherModalOk();
        }
    }

    function clickOtherModalOk() {
        var id;
        var name;
        var replaceName;
        $('#otherTable').find('tr').each(function () {
            $(this).find('td').each(function () {

                if ($(this).hasClass('otherid')) {
                    id = $(this).html();
                }
                if ($(this).hasClass('othername')) {
                    name = $(this).html();
                    replaceName = name.replace(/\s/gi, "_");
                }
                var eachCheckbox = $(this).children();
                if ($(eachCheckbox).is(':checked')) { //element checked
                    $("#transferOtherTable tbody").append(
                            '<tr class="TrOther-' + id + '">' +
                            '<td class="id hidden">' + id + '</td>' +
                            '<td class="name">' + name + '</td>' +
                            '<td class="text-center">' +
                            '<a id="RowButtonRemove-"' + $(eachCheckbox).attr('id') + '" name="RowButtonRemove-"' + $(eachCheckbox).attr('id') + '" chkId="' + $(eachCheckbox).attr('id') + '" class="RemoveRow">' +
                            '<span id="RowSpanRemove-"' + $(eachCheckbox).attr('id') + '" name="RowSpanRemove-"' + $(eachCheckbox).attr('id') + '" ' +
                            'class="glyphicon glyphicon-remove deleteicon" onclick=deleteOther("' + id + '","' + replaceName + '"); data-toggle="modal" data-target="#DeleteOtherModal"></span>' +
                            '</a></td>' +
                            '</tr>'
                            );
                    $(eachCheckbox).removeAttr('checked');
                    $(eachCheckbox).attr('disabled', 'disabled');
                } else {
                    $("#OtherModal").modal('hide');
                }
                $('#otherTable').find('input:checkbox:disabled').closest('tr').addClass('hidden');
            });
        });
        $("#OtherModal").modal('hide');

    }

    function deleteOther(id, name) {
        $("#deleteOtherId").val(id);
        $("#delOtherContent").html("Are you sure to delete Other : " + name + " ?");
    }

    function removeOtherRow() {
        var idx = $("#deleteOtherId").val();

        $("#transferOtherTable tbody").find("tr.Tr-" + idx).remove();
        var otherId = idx;
        console.log("otherId = " + otherId);
        $('#' + 'trOtherId' + otherId).removeClass('hidden');
        $('#' + 'row-' + otherId + '-other').removeAttr('disabled');


        $("#DeleteOtherModal").modal('hide');
    }
</script>


