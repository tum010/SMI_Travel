<%-- 
    Document   : MCommissionDetail
    Created on : Mar 27, 2015, 5:09:31 PM
    Author     : Winit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/MCommissionDetail.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="agentList" value="${requestScope['AgentList']}" />
<c:set var="tourList" value="${requestScope['TourList']}" />
<c:set var="AgentTourCommissions" value="${requestScope['AgentTourCommissions']}" />
<c:set var="AgentCommissions" value="${requestScope['AgentCommissions']}" />
<c:set var="AgentTours" value="${requestScope['AgentTours']}" />

<!--Alert Save -->
<div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>${requestScope['ResultSave']}</strong> 
</div>
<div id="textAlertDivUpdateSuccess"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Update Success!</strong> 
</div>
<div id="textAlertDivUpdateFail"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Update Fail!</strong> 
</div>
<section class="content-header"  >
    <h1>
        <b>Master : Tour Commission</b>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>  
        <li><a href="#">Tour Commission</a></li>
        <li class="active"><a href="#">Tour Commission Detail</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;">
    <!--Table add-->
    <div class="col-md-10  col-md-offset-1">
        <form action="MCommissionDetail.smi" id="MCommissionDetailForm" method="post" role="form" onsubmit="return validateSubmit();" >
            <div class="panel panel-default">
                <div class="panel-heading">Detail</div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <div class="col-md-6 col-md-offset-6 text-right">
                                <a id="ButtonBack" name="ButtonBack" href="MCommission.smi" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Back</a>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <form action="MCommissionDetail.smi" method="post" id="InputAgent" role="form" class="form-inline" >                               
                            <div class="row" >
                                <div class="col-md-12 form-group" >
                                    <div class="col-md-5">
                                        <div class="col-md-2 text-right"><label class="form-label" for="InputAgent">Agent</label></div>

                                        <div class="col-md-5">
                                            <div class="input-group">
                                                <input id="InputAgentId" name="InputAgentId" type="hidden" class="form-control" value="${AgentCommissions.agent.id}">
                                                <input id="InputAgentCode" name="InputAgentCode" class="form-control" maxlenght="50" value="${AgentCommissions.agent.code}" >
                                                <span id="agentSearchButton" name="agentSearchButton" class="input-group-addon" data-toggle="modal" data-target="#AgentModal">
                                                    <span id="agentSearchButtonIcon" name="agentSearchButtonIcon" class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>

                                        <div class="col-md-5"><input id="InputAgentName" name="InputAgentName" class="form-control" style="width: 170px" value="${AgentCommissions.agent.name}"  readonly  ></div>
                                    </div> 
                                    <div class="col-md-5">
                                        <div class="col-md-2 text-right"><label class="form-label" for="InputTourCode">Tour</label></div>

                                        <div class="col-md-5">
                                            <div class="input-group">
                                                <input type="hidden" id="InputTourId" name="InputTourId" class="form-control"  value="${AgentTours.id}">
                                                <input type="text" id="InputTourCode" name="InputTourCode" class="form-control" maxlenght="50" value="${AgentTours.code}" >                  
                                                <span id="tourSearchButton" name="tourSearchButton" class="input-group-addon" data-toggle="modal" data-target="#TourModal">
                                                    <span id="tourSearchButtonIcon" name="tourSearchButtonIcon" class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>
                                        <div class="col-md-5"><input type="text" id="InputTourName" name="InputTourName" class="form-control" style="width: 170px" value="${AgentTours.name}" readonly ></div>
                                    </div>

                                    <div class="col-md-2"  >
                                        <c:set var="checkedPay" value="" />
                                        <c:if test="${AgentCommissions.isPay == 1}">
                                            <c:set var="checkedPay" value="checked" />
                                        </c:if>                            
                                        <div class="text-right"style="padding-right: 20px">
                                            <label><input type="checkbox" id="CheckPay" name="CheckPay" ${checkedPay}>              Pay</label>
                                        </div>
                                    </div>
                                </div>
                            </div>      
                    </div>


                    <style>
                     .input-group-addon {
                         padding: 2px 10px; 
                     }
                    </style>
                    
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-2"><label class="control-label">Tour Commission</label></div>
                        <table class="display" id="commissionTable" name="commissionTable" STYLE="table-layout:fixed;">
                            <thead>
                                <tr class="datatable-header">
                                    <th style="width: 2%">No.</th>
                                    <th style="width: 10%">From</th>
                                    <th style="width: 10%">To</th>
                                    <th style="width: 5%">Commission</th>
                                    <th style="width: 2%">Action</th>
                                </tr>
                            </thead>
                            <tbody> 

                                <!--Simulate Row begin-->
                                <tr  class="hide " > 
                                    <td class="text-center"></td>
                                    <td class="hidden"><input type="text" class="form-control text-center" name="InputId-" id="InputId-" value=""></td>
                                    <td>
                                        <div class="input-group  datetime" id="dateFrom-" name="dateFrom-">
                                            <input type="text"  class="form-control text-center datemask  " 
                                               data-date-format="DD-MM-YYYY" name="InputFrom-" id="InputFrom-"
                                               placeholder="DD-MM-YYYY" value="" />
                                            <a class="input-group-addon">
                                                <i class="glyphicon-calendar glyphicon "></i>
                                            </a>
                                        </div>          
                                    </td>
                                    <td>
                                        <div class="input-group  datetime" id="dateTo-" name="dateTo-">
                                            <input type="text"  class="form-control text-center datemask" 
                                               data-date-format="DD-MM-YYYY" name="InputTo-" id="InputTo-" 
                                               placeholder="DD-MM-YYYY" value="" />
                                            <a class="input-group-addon">
                                                <i class="glyphicon-calendar glyphicon "></i>
                                            </a>
                                        </div>
                                        
                                    </td>
                                    <td><input type="text" class="form-control text-right decimal"  name="InputCommission-" id="InputCommission-"  
                                                placeholder="0.00" maxlength="10"  value=""></td>
                                    <td class="text-center">
                                        <span id="deleteTourCommissionRow-" name="deleteTourCommissionRow-" 
                                              onclick="DeleteCommissionRow(null, this)" 
                                              class="glyphicon glyphicon-remove deleteicon" 
                                              data-toggle="modal" data-target="" >    
                                        </span>
                                    </td>
                                </tr> 
                                <!--Simulate Row end-->
                            <input type="hidden" id="counterCommission" name="counterCommission" value="1">
                            <c:forEach var="item" items="${AgentTourCommissions}" varStatus="loop">
                                <tr id="commissionId-${item.id}"> 
                                    <!--date from-->
                                    <c:choose>  
                                        <c:when test="${item.from.date < 10}">
                                            <c:set var="datefrom" value="0${item.from.date}"></c:set>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="datefrom" value="${item.from.date}"></c:set>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>  
                                        <c:when test="${item.from.month < 10}">
                                            <c:set var="monthfrom" value="0${item.from.month+1}"></c:set>
                                        </c:when>
                                        <c:otherwise >
                                            <c:set var="monthfrom" value="${item.from.month+1}"></c:set>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${(monthfrom eq '010') || (monthfrom eq '10')}">
                                            <c:set var="monthfrom" value="${item.from.month+1}"></c:set>
                                        </c:when>
                                    </c:choose>
                                    <c:set var="yearfrom" value="${item.from.year+1900}"></c:set>
                                    
                                    <!--date to-->
                                    <c:choose>  
                                        <c:when test="${item.to.date < 10}">
                                            <c:set var="dateto" value="0${item.to.date}"></c:set>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="dateto" value="${item.to.date}"></c:set>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>  
                                        <c:when test="${item.to.month < 10}">
                                            <c:set var="monthto" value="0${item.to.month+1}"></c:set>
                                        </c:when>
                                        <c:otherwise >
                                            <c:set var="monthto" value="${item.to.month+1}"></c:set>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${(monthto eq '010') || (monthto eq '10')}">
                                            <c:set var="monthto" value="${item.to.month+1}"></c:set>
                                        </c:when>
                                    </c:choose>
                                    <c:set var="yearto" value="${item.to.year+1900}"></c:set>
                                    
                                    
                                    <td class="text-center">${loop.count}</td>
                                    <td class="hidden">
                                        <input type="text" class="form-control text-center" 
                                               name="InputId-${loop.count}" id="InputId-${loop.count}" value="${item.id}">
                                    </td>
                                    
                                    <td>
                                        <div class="input-group  datetime" id="dateFrom-${loop.count}" name="dateFrom-${loop.count}">
                                            <input type="text"  class="form-control text-center datemask  " 
                                               data-date-format="DD-MM-YYYY" name="InputFrom-${loop.count}" id="InputFrom-${loop.count}"
                                               placeholder="DD-MM-YYYY" value="${datefrom}-${monthfrom}-${yearfrom}" />
                                            <a class="input-group-addon">
                                                <i class="glyphicon-calendar glyphicon "></i>
                                            </a>
                                        </div>
                                        
                                    </td>
                                    <td>
                                        <div class="input-group  datetime" id="dateTo-${loop.count}" name="dateTo-${loop.count}">
                                            <input type="text"  class="form-control text-center datemask  " 
                                               data-date-format="DD-MM-YYYY" name="InputTo-${loop.count}" id="InputTo-${loop.count}"
                                               placeholder="DD-MM-YYYY" value="${dateto}-${monthto}-${yearto}" />
                                            <a class="input-group-addon">
                                                <i class="glyphicon-calendar glyphicon "></i>
                                            </a>
                                        </div>
                                       
                                    </td>
                                    <td class=""><input type="text" class="form-control text-right decimal"  name="InputCommission-${loop.count}" id="InputCommissionRow-${loop.count}" 
                                                        placeholder="0.00" maxlength="10"  value="${item.comission}"></td>
                                    <td class="text-center">
                                        <span id="deleteTourCommissionRow-${loop.count}" name="deleteTourCommissionRow-${loop.count}" 
                                              class="glyphicon glyphicon-remove deleteicon"  
                                              onclick="DeleteCommissionRow('${item.id}', this)"
                                              data-toggle="modal" data-target="#DelCommission" >
                                        </span>      
                                    </td>
                                </tr> 
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-xs-12 text-center">
                        <input type="hidden" id="action" name="action" value="save" />
                        <input type="hidden" id="AgentComID" name="AgentComID" value="${requestScope['AgentComID']}" />
                        <input type="hidden" id="agentCommissionId" name="agentCommissionId" value="${requestScope['agentCommissionId']}" />
                        <button id="ButtonSave" name="ButtonSave" type="submit" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<!--Agent Modal-->
<div class="modal fade" id="AgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Agent</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="agentTable" name="agentTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th style="width:20%">Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <script>
                        agentArray = [];
                    </script>
                    <tbody>
                        <c:forEach var="agent" items="${agentList}">
                            <tr>
                                <td class="object-id hidden">${agent.id}</td>
                                <td class="object-code">${agent.code}</td>
                                <td class="object-name">${agent.name}</td>
                            </tr>
                        <script>
                            agentArray.push({id: "${agent.id}", code: "${agent.code}", name: "${agent.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Script Daytour List table-->
            <script>

                $(document).ready(function () {
                    $("#agentTable tr").on('click', function () {
                        agent_id = $(this).find(".object-id").text();
                        agent_code = $(this).find(".object-code").text();
                        agent_name = $(this).find(".object-name").text();
                        $("#InputAgentId").val(agent_id);
                        $("#InputAgentCode").val(agent_code);
                        $("#InputAgentName").val(agent_name);
                        $("#AgentModal").modal('hide');
                    });
                    // agentTable
                    var agentTable = $('#agentTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": true,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });
                    $('#agentTable tbody').on('click', 'tr', function () {
                        //$('.collapse').collapse('show');
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                        }
                        else {
                            agentTable.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                        }

                    });
                    // ON KEY INPUT AUTO SELECT TOURCODE-TOURNAME
                    $(function () {
                        var availableTags = [];
                        $.each(agentArray, function (key, value) {
                            availableTags.push(value.code);
                            if ( !(value.name in availableTags) ){
                               availableTags.push(value.name);
                            }
                        });

                        $("#InputAgentCode").autocomplete({
                            source: availableTags,
                            close:function( event, ui ) {
                               $("#InputAgentCode").trigger('keyup');
                            }
                        });
                        $("#InputAgentCode").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var code = this.value.toUpperCase();
                            var name = this.value;
                            $("#InputAgentName").val(null);
                            $.each(agentArray, function (key, value) {
                                if (value.code.toUpperCase() === code) {
                                    $("#InputAgentId").val(value.id);
                                    $("#InputAgentName").val(value.name);
                                }
                               if(name === value.name){
                                    $("#InputAgentCode").val(value.code);
                                    $("#InputAgentId").val(value.id);
                                    $("#InputAgentName").val(value.name);
                                    code = $("#InputAgentCode").val().toUpperCase();
                                }
                            }); //end each agentTable
                        }); // end InputAgentCode keyup
                    }); // end AutoComplete AgentCode AgentName
                });
            </script>
            <div class="modal-footer">
                <button id="" type="button" onclick="" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>                                


<!--Tour Modal-->
<div class="modal fade" id="TourModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Tour</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="tourTable" name="tourTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th style="width:20%">Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <script>
                        tourArray = [];
                    </script>
                    <tbody>
                        <c:forEach var="tour" items="${tourList}">
                            <tr>
                                <td class="object-id hidden">${tour.id}</td>
                                <td class="object-code">${tour.code}</td>
                                <td class="object-name">${tour.name}</td>
                            </tr>
                        <script>
                            tourArray.push({id: "${tour.id}", code: "${tour.code}", name: "${tour.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Script tour List table-->
            <script>

                $(document).ready(function () {
                    $("#tourTable tr").on('click', function () {
                        tour_id = $(this).find(".object-id").text();
                        tour_code = $(this).find(".object-code").text();
                        tour_name = $(this).find(".object-name").text();
                        $("#InputTourId").val(tour_id);
                        $("#InputTourCode").val(tour_code);
                        $("#InputTourName").val(tour_name);
//                        alert("Tour id[" + $("#InputTourId").val() + "] name[" + $("#InputTourName").val() + "] code[" + $("#InputTourCode").val() + "]");
                        $("#TourModal").modal('hide');
                    });
                    // tourTable
                    var tourTable = $('#tourTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": true,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });
                    $('#tourTable tbody').on('click', 'tr', function () {
                        //$('.collapse').collapse('show');
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                        }
                        else {
                            tourTable.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                        }

                    });
                    // ON KEY INPUT AUTO SELECT TOURCODE-TOURNAME
                    $(function () {
                        var availableTags = [];
                        $.each(tourArray, function (key, value) {
                            availableTags.push(value.code);
                            if ( !(value.name in availableTags) ){
                               availableTags.push(value.name);
                            }
                        });
                        $("#InputTourCode").autocomplete({
                            source: availableTags,
                            close:function( event, ui ) {
                               $("#InputTourCode").trigger('keyup');
                            }
                        });
                        $("#InputTourCode").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var code = this.value.toUpperCase().trim();
                            var name = this.value;
                            $("#InputTourName").val(null);
                            $.each(tourArray, function (key, value) {
                                if (value.code.toUpperCase().trim() === code) {
                                    $("#InputTourId").val(value.id);
                                    $("#InputTourName").val(value.name);
                                }
                                if(name === value.name){
                                    $("#InputTourCode").val(value.code);
                                    $("#InputTourId").val(value.id);
                                    $("#InputTourName").val(value.name);
                                    code = $("#InputTourCode").val().toUpperCase();
                                }
                            }); //end each tourTable


                        }); // end InputTourCode keyup
                    }); // end AutoComplete TourCode TourName


                });
            </script>
            <div class="modal-footer">
                <button id="" type="button" onclick="" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>  
<!--Commission Delete Modal-->
<div class="modal fade" id="DelCommission" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="" method="post" id="DelExpenseForm" class="form-horizontal"  role="form">            
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"> Delete AgentTourCommission </h4>
                </div>
                <div class="modal-body" id="delContent"></div>
                <div class="modal-footer">
                    <button id="btnDelete" type="button" class="btn btn-danger">Delete</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<c:if test="${! empty param.result}">
    <c:if test="${param.result =='success'}">        
        <script language="javascript">
            $('#textAlertDivUpdateSuccess').show();
        </script>
        <META HTTP-EQUIV="Refresh" CONTENT="0;URL=MCommissionDetail.smi?commissionId=${param.commissionId}&action=edit">
    </c:if>
    <c:if test="${param.result =='fail'}">        
        <script language="javascript">
            $('#textAlertDivUpdateFail').show();
        </script>
        <META HTTP-EQUIV="Refresh" CONTENT="0;URL=MCommissionDetail.smi?commissionId=${param.commissionId}&action=edit">
    </c:if>
</c:if>
<c:if test="${! empty requestScope['VALIDATE']}">
    <script language="javascript">
//        $('#textAlertDivSave').show();
        alert('<c:out value="${requestScope['VALIDATE']}" />');
    </script>
</c:if>   

<c:if test="${! empty requestScope['ResultSave']}">
    <script language="javascript">
        $('#textAlertDivSave').show();
    </script>
</c:if>         

<script type="text/javascript" charset="utf-8" >
    $(document).ready(function () {
         
        $(".datetime").datetimepicker({
                  
        });
        
        $(".datemask").mask('00-00-0000', {reverse: true});
        $(".decimal").inputmask({
            alias:"decimal",
            integerDigits:6,
            groupSeparator: ',', 
            autoGroup: true,
            digits:2,
            allowMinus:false,        
            digitsOptional: false,
            placeholder: "0"
        }); 
        
       
        //Add Blank row for user input.
        addRowCommissionTable();
        /*Auto Add lastrow */
       
        $(document).on('click', '#commissionTable tbody tr:last td  input ,#commissionTable tbody tr:last td .input-group-addon', function (e) { // .input-group-addon, .datemask
            

            /*OnEvent Add lastrow */
            addRowCommissionTable();
        });
        
    });
    
    function sendDataToDelete(param){ //wii
        $.ajax({
                dataType: 'html',
                type: "POST",
                url: "MCommissionDetail.smi",
                data: "agentTourComId="+ param +"&actionDelete=delete",
                "beforeSend": function () {
                    console.log("sending...");
                },
                "success": function () {
                    if (${! empty requestScope['COMMISSIONDELETE']}) {
                        alert('${requestScope['COMMISSIONDELETE']}');  
                    }
                    $("#commissionId-"+param).remove();
                    $("#DelCommission").modal('hide');
                    console.log("success!");
                },
                "error": function () {
                    console.log("error!");
                }
        }).done(function () {
            console.log("done!");
        });   
    }
    

    function DeleteCommissionRow(id, objspan) {
    var countCommission = $("#commissionTable tbody").find("tr").length;
        if ($("#commissionTable tbody").find("tr").length !== 2) {
            if (id !== null) {
                $('#delContent').html(" Are you sure to delete : Row at Number "+ ($(objspan).parent().parent().index()-1) +" ? " );  

                    console.log('else len1');
                    $('#btnDelete').click(function () {
                        sendDataToDelete(id);
                    });
                
            } else {
                $(objspan).closest('tr').remove();
                console.log("counterCommission=" + countCommission);
                $('#counterCommission').val(countCommission - 1);
            }
        } else {
            if (id !== null) {
                $('#delContent').html(" Are you sure to delete : Row at Number "+ ($(objspan).parent().parent().index()-1) +" ? " );
                    console.log('else len2');
                    $('#btnDelete').click(function () {
                        sendDataToDelete(id);
                    });

            } else {
                alert('this row for fill data');
            }
        }
    }
    
    function validateSubmit() {
        var validDate = true;
        var trList = $('#commissionTable tbody tr');
        trList.each(function (i,tr) {
           if(i==0 || i==(trList.length-1)){
               return;
           }
           var d1Str = $(tr).find("input[name^='InputFrom']").val();
           var d2Str = $(tr).find("input[name^='InputTo']").val();
           if( !(isDate(d1Str) && isDate(d2Str)) ){
               validDate = false;
           }else{
               var d1Arr = d1Str.split("-");
               var d2Arr = d2Str.split("-");
               var d1 = Date.parse(d1Arr[2]+"-"+d1Arr[1]+"-"+d1Arr[0]);
               var d2 = Date.parse(d2Arr[2]+"-"+d2Arr[1]+"-"+d2Arr[0]);
               if (d1 > d2) {
                  validDate = false;
               }
           }
           
        });
        if(!validDate){
          alert("Invalid Date Please ckeck before Save/Update !!");
        }
        return validDate;
    }
    
    function isDate(txtDate)
    {
        var currVal = txtDate;
        if (currVal == '')
            return false;

        var rxDatePattern = /^(\d{1,2})(\-|-)(\d{1,2})(\-|-)(\d{4})$/; //Declare Regex
        var dtArray = currVal.match(rxDatePattern); // is format OK?

        if (dtArray == null)
            return false;

        //Checks for DD-MM-YYYY
        dtMonth = dtArray[3];
        dtDay = dtArray[1];
        dtYear = dtArray[5];

        if (dtMonth < 1 || dtMonth > 12)
            return false;
        else if (dtDay < 1 || dtDay > 31)
            return false;
        else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11) && dtDay == 31)
            return false;
        else if (dtMonth == 2)
        {
            var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
            if (dtDay > 29 || (dtDay == 29 && !isleap))
                return false;
        }
        return true;
    }
    
    function addRowCommissionTable() {
        var counter = $('#commissionTable tbody tr').length;
        var clone = $('#commissionTable tbody tr:first').clone();
        clone.removeClass("hide");
        clone.find('div,input,span').each(function () {
            console.log('count :'+counter);
            $(this).attr({
                id: $(this).attr('id') + counter,
                name: $(this).attr('name') + counter
            });
            $(".datetime").datetimepicker({
       
            });

            $('.decimal').inputmask({
                alias:"decimal",
                integerDigits:6,
                groupSeparator: ',', 
                autoGroup: true,
                digits:2,
                allowMinus:false,        
                digitsOptional: false,
                placeholder: "0"
            }); 
            $("#counterCommission").val(counter + 1);
        });
        $('#commissionTable tbody').append(clone);
    } 
</script>