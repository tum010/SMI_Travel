<%-- 
    Document   : History
    Created on : Dec 19, 2014, 1:55:09 PM
    Author     : sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['Airline_List']}" />
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="historyBookingList" value="${requestScope['HistoryBookingList']}" />
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<!--<input type="hidden" value="${param.referenceNo}" id="getUrl">-->
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<c:set var="staff" value="${requestScope['Staff_List']}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<input type="hidden" value="${master.departmentNo}" id="departmentNo">
<section class="content-header" >
    <h1>
        Booking - History
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">History</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <input type="hidden" value="${master.customer.MInitialname.name}" id="initialname_tmp">
            <input type="hidden" value="${master.customer.firstName}" id="firstname_tmp">
            <input type="hidden" value="${master.customer.lastName}" id="lastname_tmp">  
            <div ng-include="'WebContent/Book/BookMenu.html'"></div>
              <input hidden="" value="${booking_size[0]}" id="input-airticket_size">
              <input hidden="" value="${booking_size[1]}" id="input-hotel_size">
              <input hidden="" value="${booking_size[2]}" id="input-other_size">
              <input hidden="" value="${booking_size[3]}" id="input-land_size">
              <input hidden="" value="${booking_size[4]}" id="input-passenger_size">
              <input hidden="" value="${booking_size[5]}" id="input-billable_size">
              <input hidden="" value="${booking_size[6]}" id="input-daytour_size">
        </div>

        <!-- main page -->
        <div class="col-sm-10">
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
            <input type="hidden" class="form-control"  id="action" name="action" value="${action}" />
            <div class="row" style="padding-left: 15px">  
                <div class="col-md-6">
                    <h4><b>History</b></h4>
                </div>
                <div class="col-md-6 text-right">
                    <input type="hidden" class="form-control" id="referenceNo"   name="referenceNo"  value="${param.referenceNo}" >
                    <!--<a id="ButtonAdd" href="HistoryDetail.smi?referenceNo=${param.referenceNo}" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Add</a>-->
                </div>

            </div>
            <hr/>

            <table class="display" id="HistoryTable">
                <thead class="datatable-header">
                    <tr>
                        <th style="width:15%">No</th>
                        <th style="width:15%">History Date</th>
                        <th style="width:30%">Action</th>
                        <th style="width:12%">Detail</th>
                        <th style="width:15%">Staff</th>
                        <th style="width:8%">Action</th>
                    </tr>
                </thead>
                
                <tbody>
                    <c:forEach var="item" items="${historyBookingList}" varStatus="loopCounter">
                            <c:set var="datedetail" value="${fn:replace(item.detail,'FL', ' ')}" />
                            <c:set var="dateParts" value="${fn:split(datedetail,' ')}" />
                        <tr>

                            <td class="text-center">${loopCounter.count}</td>
                            <td class="text-left"><c:out value="${item.historyDate}" /></td>
                            <td class="text-left"><c:out value="${item.action}" /></td>
                            <td class="text-left"><c:out value="${dateParts[0]} ${dateParts[1]} ${dateParts[2]}" /></td>
                            <td class="text-left" ><c:out value="${item.staff.username}" /></td>
                            <td class="text-center">
                                <a id="ButtonEdit${loopCounter.count}" onclick="hideCollapse()" class="carousel" data-toggle="collapse" data-parent="#accordion" data-target="#historyBooking${loopCounter.count}" aria-expanded="true" aria-controls="collapseExample">
                                    <span id="SpanEdit${loopCounter.count}" class="glyphicon glyphicon-th-list"></span>
                                </a>
                            </td>
<!--                            <td class="text-center">
                                <a id="ButtonHistoryDetail" href="HistoryDetail.smi?referenceNo=${param.referenceNo}&action=edit&id=1">
                                    <span id="SpanHistoryDetail" class="glyphicon glyphicon-list"></span>
                                </a>
                            </td>-->
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:forEach var="data" items="${historyBookingList}" varStatus="loopCounter">
                <div class="collapse" id="historyBooking${loopCounter.count}">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">History Detail</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row" style="padding-top: 0px;">
                                <div class="col-xs-1 text-right" style="width: 100px">
                                    <label class="control-label text-right">Date</label>                                    
                                </div>
                                <div class="col-xs-2" style="width: 250px">
                                    <div class="form-group">
                                        <div class="input-group date" id="HistoryDate">
                                            <input type="text" class="form-control datemask" value="${data.historyDate}" 
                                                   name="historyDate-${loopCounter.count}" id="historyDate-${loopCounter.count}" 
                                                   data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" maxlength="10" readonly=""/>
                                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-1 text-right" style="width: 100px">
                                    <label class="control-label text-right">Staff</label>                                    
                                </div>
                                <div class="col-xs-1" style="width: 170px">
                                    <div class="input-group ">
                                        <input type="hidden" class="form-control" name="staff_id-${loopCounter.count}" id="staff_id-${loopCounter.count}" value="${data.staff.id}">
                                        <input readonly="" type="text" class="form-control" id="staff_username-${loopCounter.count}" name="staff_username-${loopCounter.count}" value="${data.staff.username}" onkeypress="onchangestaff(${loopCounter.count});">
                                        <span id="SpanOpenOwnerModal" class="input-group-addon" data-toggle="modal" data-target="#OwnerModal">
                                            <span id="SpanGlyphiconSearch" class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-xs-1" style="width: 320px">  
                                    <input type="text" class="form-control" id="staff_name-${loopCounter.count}" name="staff_name-${loopCounter.count}" value="${data.staff.name}" readonly="">
                                </div>
                            </div>
                            <div class="row"  style="padding-top: 0px;">
                                <div class="col-xs-1 text-right" style="width: 100px">
                                    <label class="control-label text-right">Action</label>                                    
                                </div>
                                <div class="col-xs-1" style="width: 250px">  
                                    <input readonly="" type="text" class="form-control" id="action-${loopCounter.count}" name="action-${loopCounter.count}" value="${data.action}">
                                </div>
                            </div>
                            <div class="row"  style="padding-top: 15px;">
                                <div class="col-xs-1 text-right" style="width: 100px">
                                    <label class="control-label text-right">Detail</label>                                    
                                </div>
                                <div class="col-xs-1" style="width: 300px">  
                                    <div class="input-group">                                    
                                        <textarea readonly="" rows="5" class="form-control" id="detail-${loopCounter.count}" name="detail-${loopCounter.count}" style="width: 500%" value="${data.detail}">${data.detail}</textarea>  
                                    </div>     
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>   
        </div>
          
    </div>
</div>
<!--Modal  Owner-->
<div class="modal fade" id="OwnerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Staff</h4>
            </div>
            <div class="modal-body">
                <!--Owner List Table-->
                <table class="display" id="OwnerTable">
                    <thead>                        
                    <script>
                        staff = [];
                    </script>
                    <tr class="datatable-header">
                        <th class="hidden">ID</th>
                        <th>User</th>
                        <th>Name</th>
                        <th>Position</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="staff" items="${staff}">
                            <tr>
                                <td class="staff-id hidden">${staff.id}</td>
                                <td class="staff-username">${staff.username}</td>
                                <td class="staff-name">${staff.name}</td>
                                <td class="staff-position">${staff.position}</td>
                            </tr>
                        <script>
                            staff.push({id: "${staff.id}", username: "${staff.username}", name: "${staff.name}", position: "${staff.position}"});
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
<c:if test="${! empty param.result}">
    <c:if test="${param.result =='1'}">        
        <script language="javascript">
            alert("save successful");
        </script>
    </c:if>
    <c:if test="${param.result =='0'}">        
        <script language="javascript">
            alert("save unsuccessful");
        </script>
    </c:if>

</c:if>

<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        var table = $('#HistoryTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });

//        $('#HistoryTable tbody').on('click', 'tr', function () {
//            if ($(this).hasClass('row_selected')) {
//                $(this).removeClass('row_selected');
//            }
//            else {
//                table.$('tr.row_selected').removeClass('row_selected');
//                $(this).addClass('row_selected');
//            }
//        });
        // $('.dataTables_length label').remove();

    // OWNER TABLE AND ISSUE TABLE SET DATATABLE
    var OwnerTable = $('#OwnerTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    $('#OwnerTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
        } else {
            OwnerTable.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }
    });

    $('.spandate').click(function () {
        var position = $(this).offset();
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    
    $('.collapse').collapse('hide');

});

function hideCollapse() {
    $("div").find($('.collapse')).collapse('hide');
}

function onchangestaff(row){
    codeStaff = [];
    $.each(staff, function (key, value) {
        codeStaff.push(value.username);
        if ( !(value.name in codeStaff) ){
           codeStaff.push(value.name);
        }
    });
    
    $("#staff_username-"+row).autocomplete({
        source: codeStaff,
        close:function( event, ui ) {
           $("#staff_username-"+row).trigger('keyup');
        }
    });
    
    $("#staff_username-"+row).on('keyup', function () {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var username = this.value.toUpperCase();
        var name = this.value;
        $("#staff_id-"+row,"#staff_name-"+row).val(null);
        $.each(staff, function (key, value) {
            if (value.username.toUpperCase() === username) {
                $("#staff_id-"+row).val(value.id);
                $("#staff_name-"+row).val(value.name);
            }
            if(name === value.name){
                $("#staff_id-"+row).val(value.id);
                $("#staff_username-"+row).val(value.username);
                $("#staff_name-"+row).val(value.name);
                username = $("#staff_username-"+row).val().toUpperCase();
            }
        });
    });
   
    $("#OwnerTable tbody").on('click', 'tr', function () {
        staff_id = $(this).find(".staff-id").text();
        staff_username = $(this).find(".staff-username").text();
        staff_name = $(this).find(".staff-name").text();
        $("#staff_id-"+row).val(staff_id);
        $("#staff_username-"+row).val(staff_username);
        $("#staff_name-"+row).val(staff_name);
        $("#OwnerModal").modal('hide');
    });   
    
}


</script>

<!--style-->
<style>
    .dataTables_wrapper {
        position: relative;
        min-height: 30px;
        clear: both;
    }
</style>