<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/DaytourHistoryTransfer.js"></script> 
<script type="text/javascript" src="jsfnAddTr.js"></script> 


<c:set var="transferJobs" value="${requestScope['transferJobs']}" />
<c:set var="dateTo" value="${requestScope['dateTo']}" />
<c:set var="dateFrom" value="${requestScope['dateFrom']}" />
<c:set var="Hotel" value="${requestScope['Hotel']}" />

<input type="hidden" value="${param.referenceNo}" id="getUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">

<section class="content-header" >
    <h1>
        Operation - Day Tours History Transfer Job
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Operation</a></li>          
        <li class="active"><a href="#">Day Tours History Transfer Job</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Book/DaytourMenu.html'"></div>
        </div>
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Day Tours History Transfer Job</b></h4>
                </div>
            </div>
            <hr/>
            <form action="DaytourHistoryTransfer.smi" method="post" id="SearchHistoryTransfer" name="SearchHistoryTransfer" role="form">
                <div class="col-xs-12 ">
                    <div class="col-xs-2 text-right">
                        <label class="control-label text-right">Date From<font style="color: red">*</font></label>
                    </div>
                    <div class="col-xs-2 form-group">
                        <div class="input-group date" id="DateFrom">
                            <input id="InputDateFrom" name="InputDateFrom" type="text" data-date-format="YYYY-MM-DD" class="form-control datemask" placeholder="YYYY-MM-DD" value="${dateFrom}">
                            <span class="input-group-addon spandate"><span class="glyphicon-calendar glyphicon"></span>
                            </span>
                        </div>
                    </div>
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">To<font style="color: red">*</font></label>
                    </div>
                    <div class="col-xs-2 form-group">
                        <div class="input-group date" id="DateTo">
                            <input id="InputDateTo" name="InputDateTo" type="text" data-date-format="YYYY-MM-DD" 
                                   class="form-control datemask" placeholder="YYYY-MM-DD">
                            <span class="input-group-addon spandate">
                                <span class="glyphicon-calendar glyphicon"></span>
                            </span>
                        </div>
                    </div>
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">Place</label>
                    </div>
                    <div class="col-xs-3 form-group">
                        <div class="input-group date" id="hotelData">
                            <input id="hotel" name="hotel" type="text"  class="form-control" value="${Hotel}">
                        </div>
                    </div>       
                    <div class="col-xs-1 text-right">
                        <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="" class="btn btn-primary btn-sm">Search</button>
                        <input type="hidden" name="action" id="actionSearch" name="action" value="search">
                    </div>

                </div>
            </form>
            <!-- Day Tours Table-->
            <table class="display" id="HistoryTransferTable" name="HistoryTransferTable">
                <thead class="datatable-header">
                    <tr>
                        <th>Document No.</th>
                        <th>Date</th>
                        <th>Guide</th>
                        <th>Driver</th>
                        <th>Tour</th>
                        <th>Hotel</th>
                        <th>Create Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${transferJobs}" varStatus="i">
                        <tr>
                            <td>${item.documentNo}</td>
                            <td>${item.transferDate}</td>                        
                            <td>${item.staffByGuildeId.name}</td>
                            <td>${item.staffByDriverId.name}</td>
                            <td>${item.tour}</td>
                            <td>${item.place}</td>
                            <td>${item.transferTime}</td>
                            <td class="text-center">
                                <a id="RowButtonPrint${i.count}" name="ButtonPrint${i.count}" >
                                    <span id="RowSpanPrint${i.count}" onclick="printTransferJob('${item.documentNo}')" name="RowSpanPrint${i.count}" class="glyphicon glyphicon-print"></span>
                                </a>
                                <a id="RowButtonEdit${i.count}" name="RowButtonEdit${i.count}" href="#" onclick="getJobDetail('${item.tour}', '${item.transferDate}', '${item.place}', '${item.placeOther}');">
                                    <span id="RowSpanEdit1" name="RowSpanEdit1"  class="glyphicon glyphicon-th-list"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="col-xs-12 form-group">
                <label class="control-label text-right">Summary</label>
            </div>
            <!-- Transfer Job Details Table-->
            <table class="display" id="jobDetailTable" name="jobDetailTable">
                <thead class="datatable-header">
                    <tr>
                        <th>No</th>
                        <th>Pick Up</th>
                        <th style='text-align: center'>Room</th>
                        <th style='text-align: center'>Time</th>
                        <th style='text-align: center'>Name</th>
                        <th class='text-center'>AD</th>
                        <th class='text-center'>CH</th>
                        <th class='text-center'>IN</th>
                        <th >Cost</th>
                        <th>Guide</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</div>


<!--<link href="css/datatable/demo_table.css" rel="stylesheet">-->    

<style type="text/css">
    .dataTables_wrapper { //set this hieght wrapper of HistoryTransferTable 
                          _height: 150px;
                          min-height: 150px;
    }
    .alignLeft { text-align: left; }
    .alignRight { text-align: right; }
    .alignCenter { text-align: center; }
</style>
<script>
    var JobDetailTable;
    $(document).ready(function () {

        $('#HistoryTransferTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": true,
            "bInfo": false
        });
        JobDetailTable = $('#jobDetailTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": true,
            "bInfo": false,
            "bSort": false,
            "aoColumns": [
                {sClass: "alignCenter"},
                {sClass: "alignLeft"},
                {sClass: "alignCenter"},
                {sClass: "alignCenter"},
                {sClass: "alignLeft"},
                {sClass: "alignCenter"},
                {sClass: "alignCenter"},
                {sClass: "alignCenter"},
                {sClass: "alignRight"},
                {sClass: "alignLeft"}
            ]
        });
        // Set value back to search box.
        $("#InputDateFrom").val("<c:out value="${dateFrom}" />");
        $("#InputDateTo").val("<c:out value="${dateTo}" />");
    });
//    function searchAction() {
//        $("#SearchHistoryTransfer").submit();
//    }

    function getJobDetail(tourCode, tourDate, place, other) {
        var servletName = 'TransferJobServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&tourcode=' + tourCode +
                '&date=' + tourDate +
                '&place=' + place +
                '&other=' + other +
                '&type=' + 'getjobDetail';
        console.log("Ajax param [" + param + "]");
        CallAjax(param);
    }



    function CallAjax(param) {
        var url = 'AJAXServlet';
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function (msg) {
//                    $("#jobDetailTable tbody").empty();
//                    $("#jobDetailTable tbody").empty().append(msg);
                    var table = $('#jobDetailTable').dataTable();
                    var tbody = $.parseHTML(msg);
                    table.fnClearTable();
                    var tr = [];
                    $(tbody).find('tr').each(function () {
                        var idx = 0;
                        $(this).find('td').each(function () {
                            tr[idx] = $(this).text();
                            idx++;
                        });
                        table.fnAddData(tr);
                    });
                    //setformat();
                }, error: function (msg) {
                    $("#jobDetailTable tbody").empty();
                    console.log('error ' + msg);
                }
            });
        } catch (e) {
            alert(e);
        }
    }
</script>

