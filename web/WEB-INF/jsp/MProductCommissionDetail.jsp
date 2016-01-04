<%-- 
    Document   : MProductCommissionDetail
    Created on : Jun 3, 2015, 3:02:20 PM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="css/jquery-ui.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/MProductCommissionDetail.js"></script>

<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">

<c:set var="ListProduct" value="${requestScope['ListProduct']}" />
<c:set var="listProductCommission" value="${requestScope['listProductCommission']}" />
<c:set var="InputProdductCommissionId" value="${requestScope['InputProdductCommissionId']}" />
<c:set var="InputProductId" value="${requestScope['InputProductId']}" />
<c:set var="InputProductCode" value="${requestScope['InputProductCode']}" />
<c:set var="InputProductName" value="${requestScope['InputProductName']}" />
<c:set var="actionAdd" value="${requestScope['actionAdd']}" />
<c:set var="status" value="${requestScope['Status']}" />
<c:set var="agentList" value="${requestScope['AgentList']}" />
<c:set var="selectAgent" value="${requestScope['SelectAgent']}" />

<section class="content-header"  >
    <h1>
        <b>Master : Product Commission</b>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>  
        <li><a href="#">Product Commission</a></li>
        <li class="active"><a href="#">Product Commission Detail</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;">
    <!--Content -->
    <div class="col-md-10  col-md-offset-1">
        <!--Alert delete -->
    <div class="row" style="display:none;" id="textAlertDivDelete">
        <div class="col-md-10 col-md-offset-1">
            <div class="alert alert-warning alert-dismissable fade  in" role="alert">
                <button type="button" class="close" data-dismiss="alert" ><span aria-hidden="true" onclick="">X</span></button>
                <strong>Delete Success !!!</strong>
            </div>
        </div>
    </div>
    <!--Alert Save and Update-->
    <input type="hidden" id="saveText" name="saveText" value="${status}">
 
    <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Save Success!</strong> 
        </div>
    <!--Alert Check Date-->
    <div class="row" style="display:none;" id="AlertCheckDate">
        <div class="col-md-10 col-md-offset-1">
            <div class="alert alert-warning alert-dismissable fade  in" role="alert">
                <button type="button"  data-dismiss="alert" ><span aria-hidden="true" onclick="closePopupDate()">X</span></button>
                <strong>Please Check Date From And Date To!!!</strong>
            </div>
        </div>
    </div>
    <!--Alert Check Commission Percent-->
    <div class="row" style="display:none;" id="AlertCheckCommissionPercent">
        <div class="col-md-10 col-md-offset-1">
            <div class="alert alert-warning alert-dismissable fade  in" role="alert">
                <button type="button" class="close" data-dismiss="alert" ><span aria-hidden="true" onclick="closePopupCommission()">X</span></button>
                <strong>Please Input 00.00 - 100.00 !!!</strong>
            </div>
        </div>
    </div>
        <form action="MProductCommissionDetail.smi" method="post" role="form" id="MProductCommissionDetail" onsubmit="return validateCheckInput();" >
            <div class="panel panel-default">
                <div class="panel-heading">Detail</div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <div class="col-md-6 col-md-offset-6 text-right">
                                <a id="ButtonBack" name="ButtonBack" href="MProductCommission.smi?action=back&ProductNameSearch=${InputProductName}&ProductCodeSearch=${InputProductCode}" onclick="" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Back</a>
                            </div>
                        </div>
                    </div>

                    <div class="row">         
                            <div class="row" >
                                <div class="col-md-12 form-group" >
                                    <div class="col-md-5">
                                        <div class="col-md-2 text-right"><label class="form-label" for="InputAgent">Product</label></div>

                                        <div class="col-md-5">
                                            <div class="input-group">
                                                <input id="InputProductId" name="InputProductId" type="hidden" class="form-control" value="${InputProductId}">
                                                <input id="InputProductCode" name="InputProductCode" class="form-control" maxlenght="50" value="${InputProductCode}" >
                                                <span id="agentSearchButton" name="agentSearchButton" class="input-group-addon" data-toggle="modal" data-target="#ProductModal">
                                                    <span id="agentSearchButtonIcon" name="agentSearchButtonIcon" class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>

                                        <div class="col-md-5"><input id="InputProductName" name="InputProductName" class="form-control" style="width: 170px" value="${InputProductName}"  readonly  ></div>
                                    </div> 
                                </div>
                            </div>
                    </div>
                    <style>
                     .input-group-addon {
                         padding: 2px 10px; 
                     }
                    </style> 

                    <!--table add-->
                    <div class="col-xs-12 form-group">
                        <label class="control-label">Product Commission</label>
                        <table class="display" id="commissionTable" name="commissionTable">
                            <thead>
                                <tr class="datatable-header">
                                    <th style="width: 10%">From</th>
                                    <th style="width: 10%">To</th>
                                    <th style="width: 5%">Guide Comm(%)</th>
                                    <th style="width: 5%">Guide Comm(THB)</th>
                                    <th style="width: 15%">Agent</th>
                                    <th style="width: 5%">Agent Comm(%)</th>
                                    <th style="width: 5%">Agent Comm(THB)</th>
                                    <th style="width: 2%">Action</th>
                                </tr>
                            </thead>
                            <script>
                                agentName = [];
                            </script>
                            <c:forEach var="agent" items="${agentList}" >
                                <script>
                                    agentName.push({value: "${agent.id}", label: "${agent.name}"});
                                </script>
                            </c:forEach>

                                <!--Simulate Row begin-->
                                <!--<input type="text" id="actionAdd" name="actionAdd" value="${actionAdd}">-->
                                <tr  class="hide " > 
                                    <td class="hidden"></td>
                                    <td class="hidden">
                                        <input type="text" class="form-control text-center" 
                                               name="InputId-" id="InputId-" value="">
                                        <input type="text" class="form-control text-center" 
                                               name="createBy-" id="createBy-" value="">
                                        <input type="text" class="form-control text-center" 
                                               name="createDate-" id="createDate-" value="">
                                        <input type="text" class="form-control text-center" 
                                               name="updateBy-" id="updateBy-" value="">
                                        <input type="text" class="form-control text-center" 
                                               name="updateDate-" id="updateDate-" value="">
                                    </td>
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
                                    <td>
                                        <input type="text" class="form-control text-right decimal" name="InputCommissionPercent-" id="InputCommissionPercent-" placeholder="0.00" maxlength="10"  value="">
                                    </td>
                                    <td>
                                        <input type="text" class="form-control text-right decimal" name="InputCommission-" id="InputCommission-" placeholder="0.00" maxlength="10"  value="">
                                    </td>
                                    <td>
                                        <input type="hidden" class="form-control" id="AgentId-" name="AgentId-"  valHidden="" value=""  />
                                        <input type="text" class="form-control" id="AgentName-" name="AgentName-"  valHidden="" value=""  />
                                    </td>
                                    <td>
                                        <input type="text" class="form-control text-right decimal"  
                                               name="InputAgentCommissionPercent-" id="InputAgentCommissionPercent-"  
                                                placeholder="0.00" maxlength="10"  value="">
                                    </td>
                                    <td>
                                        <input type="text" class="form-control text-right decimal"  
                                               name="InputAgentCommission-" id="InputAgentCommission-"  
                                                placeholder="0.00" maxlength="10"  value="">
                                    </td>
                                    
                                    <td class="text-center">
                                        <span id="deleteTourCommissionRow-" name="deleteTourCommissionRow-" 
                                              onclick="DeleteCommissionRow(null, this)" 
                                              class="glyphicon glyphicon-remove deleteicon" 
                                              data-toggle="modal" data-target="" >    
                                        </span>
                                    </td>
                                </tr> 
                            <input type="hidden" id="filterAgent" name="SelectAgent" >   
                                <!--Simulate Row end-->
                            <input type="hidden" id="counterCommission" name="counterCommission" value="1">
                            <c:forEach var="item" items="${listProductCommission}" varStatus="loop">
                                <tr id="commissionId-${item.id}"> 
                                    <c:choose>  
                                        <c:when test="${item.effectiveFrom.date < 10}">
                                            <c:set var="datefrom" value="0${item.effectiveFrom.date}"></c:set>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="datefrom" value="${item.effectiveFrom.date}"></c:set>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>  
                                        <c:when test="${item.effectiveFrom.month < 10}">
                                            <c:set var="monthfrom" value="0${item.effectiveFrom.month+1}"></c:set>
                                        </c:when>
                                        <c:otherwise >
                                            <c:set var="monthfrom" value="${item.effectiveFrom.month+1}"></c:set>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${(monthfrom eq '010') || (monthfrom eq '10')}">
                                            <c:set var="monthfrom" value="${item.effectiveFrom.month+1}"></c:set>
                                        </c:when>
                                    </c:choose>
                                    <c:set var="yearfrom" value="${item.effectiveFrom.year+1900}"></c:set>
                                    
                                    <!--date to-->
                                    <c:choose>  
                                        <c:when test="${item.effectiveTo.date < 10}">
                                            <c:set var="dateto" value="0${item.effectiveTo.date}"></c:set>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="dateto" value="${item.effectiveTo.date}"></c:set>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>  
                                        <c:when test="${item.effectiveTo.month < 10}">
                                            <c:set var="monthto" value="0${item.effectiveTo.month+1}"></c:set>
                                        </c:when>
                                        <c:otherwise >
                                            <c:set var="monthto" value="${item.effectiveTo.month+1}"></c:set>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${(monthto eq '010') || (monthto eq '10')}">
                                            <c:set var="monthto" value="${item.effectiveTo.month+1}"></c:set>
                                        </c:when>
                                    </c:choose>
                                    <c:set var="yearto" value="${item.effectiveTo.year+1900}"></c:set>
                                
                                    <td class="hidden">${loop.count}</td>
                                    <td class="hidden">
                                        <input type="text" class="form-control text-center" 
                                               name="InputId-${loop.count}" id="InputId-${loop.count}" value="${item.id}">
                                        <input type="text" class="form-control text-center" 
                                               name="createBy-${loop.count}" id="createBy-${loop.count}" value="${item.createBy}">
                                        <input type="text" class="form-control text-center" 
                                               name="createDate-${loop.count}" id="createDate-${loop.count}" value="${item.createDate}">
                                        <input type="text" class="form-control text-center" 
                                               name="updateBy-${loop.count}" id="updateBy-${loop.count}" value="${item.updateBy}">
                                        <input type="text" class="form-control text-center" 
                                               name="updateDate-${loop.count}" id="updateDate-${loop.count}" value="${item.updateDate}">
                                    </td>
                                    <td>
                                        <div class="input-group  datetime" id="dateFrom-${loop.count}" name="dateFrom-${loop.count}">
                                            <input type="text"  class="form-control text-center datemask" 
                                               data-date-format="DD-MM-YYYY" name="InputFrom-${loop.count}" id="InputFrom-${loop.count}"
                                               placeholder="DD-MM-YYYY" value="${datefrom}-${monthfrom}-${yearfrom}" />
                                            <a class="input-group-addon">
                                                <i class="glyphicon-calendar glyphicon "></i>
                                            </a>
                                        </div>
                                        
                                    </td>
                                    <td>
                                        <div class="input-group  datetime" id="dateTo-${loop.count}" name="dateTo-${loop.count}">
                                            <input type="text"  class="form-control text-center datemask" 
                                               data-date-format="DD-MM-YYYY" name="InputTo-${loop.count}" id="InputTo-${loop.count}"
                                               placeholder="DD-MM-YYYY" value="${dateto}-${monthto}-${yearto}" />
                                            <a class="input-group-addon">
                                                <i class="glyphicon-calendar glyphicon "></i>
                                            </a>
                                        </div>
                                       
                                    </td>
                                    <td class="">
                                        <input type="text" class="form-control text-right decimal"  
                                        name="InputCommissionPercent-${loop.count}" id="InputCommissionPercentRow-${loop.count}" 
                                        placeholder="0.00" maxlength="10"  value="${item.comissionPercent}">
                                    </td>
                                    <td class="">
                                        <input type="text" class="form-control text-right decimal"  
                                        name="InputCommission-${loop.count}" id="InputCommissionRow-${loop.count}" 
                                        placeholder="0.00" maxlength="10"  value="${item.comission}" >
                                    </td>
                                    <td class="">
                                        <input type="hidden" class="form-control" id="AgentId-${loop.count}" name="AgentId-" value="${item.agent.id}"  />
                                        <input type="text" class="form-control" id="AgentName-${loop.count}" name="AgentName-"  valHidden="${item.agent.id}" value="${item.agent.name}"  />
                                    </td>
                                    <td class="">
                                        <input type="text" class="form-control text-right decimal"  
                                        name="InputAgentCommissionPercent-${loop.count}" id="InputAgentCommissionPercentRow-${loop.count}" 
                                        placeholder="0.00" maxlength="10"  value="${item.agentCommissionPercent}" >
                                    </td>
                                    <td class="">
                                        <input type="text" class="form-control text-right decimal"  
                                        name="InputAgentCommission-${loop.count}" id="InputAgentCommissionRow-${loop.count}" 
                                        placeholder="0.00" maxlength="10"  value="${item.agentCommission}" >
                                    </td>
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
                        <table class="display hide" id="EditTable" name="EditTable">
                        <tbody></tbody>
                        </table>
                        <hr/>
                        <div class="text-center">
                        </div>
                    </div>
                    <div class="col-xs-12 text-center">
                        <input type="hidden" id="action" name="action" value="save" />
                        
                        <button id="ButtonSave" name="ButtonSave" onclick="saveAction()" type="button" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<!--Product Modal-->
<div class="modal fade" id="ProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Product</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="productTable" name="productTable">
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
                        <c:forEach var="product" items="${ListProduct}">
                            <tr>
                                <td class="object-id hidden">${product.id}</td>
                                <td class="object-code">${product.code}</td>
                                <td class="object-name">${product.name}</td>
                            </tr>
                            <script>
                               // agentArray.push({id: "${product.id}", code: "${product.code}", name: "${product.name}"});
                            </script>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Script Product List table-->
            <script>
                $(document).ready(function () {
                    $("#productTable tr").on('click', function () {
                        agent_id = $(this).find(".object-id").text();
                        agent_code = $(this).find(".object-code").text();
                        agent_name = $(this).find(".object-name").text();
                        $("#InputProductId").val(agent_id);
                        $("#InputProductCode").val(agent_code);
                        $("#InputProductName").val(agent_name);
//                        alert("Tour id[" + $("#InputTourId").val() + "] name[" + $("#InputTourName").val() + "] code[" + $("#InputTourCode").val() + "]");
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
                        //$('.collapse').collapse('show');
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                        }
                        else {
                            productTable.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                        }

                    });
                    // ON KEY INPUT AUTO SELECT TOURCODE-TOURNAME
                    $(function () {
                        var availableTags = [];
//                                console.log(tourCode);
                        $.each(agentArray, function (key, value) {
                            availableTags.push(value.code);
                        });
//                                console.log(availableTags);
                        $("#InputProductCode").autocomplete({
                            source: availableTags,
                            close:function( event, ui ) {
                               //window.uiTmp = event;
                               //window.uiTmp = ui;
                               //alert('Test');    
                               $("#InputProductCode").trigger('keyup');
                            }
                        });
                        $("#InputProductCode").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var code = this.value.toUpperCase();
                            $("#InputProductName").val(null);
                            $.each(agentArray, function (key, value) {
                                //console.log('each : ' + value.code);
                                //console.log('val : ' + $("#agent_user").val());
                                if (value.code.toUpperCase() === code) {
//                                console.log('ok');
                                    $("#InputProductId").val(value.id);
                                    $("#InputProductName").val(value.name);
                                }
                            }); //end each agentTable


                        }); // end InputAgentCode keyup
                    }); // end AutoComplete AgentCode AgentName

//                    $('.date').mask('0000-00-00');    
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
                    <h4 class="modal-title"> Delete Agent Tour Commission </h4>
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
<script type="text/javascript" charset="utf-8" >
    $(document).ready(function () { 
        jQuery.curCSS = jQuery.css;
        var dataAgent = [];
        dataAgent = agentName;
        var agentcount= 0 ; 
        $("#commissionTable tbody").find("tr").each(function(){ 
            agentcount++;
            $("#AgentName-"+agentcount).autocomplete({
                source: dataAgent,
                focus: function( event, ui ) {
                    event.preventDefault();
                    $(this).val(ui.item.label);
                },
                select: function( event, ui ) {
                    event.preventDefault();
                    $(this).val(ui.item.label);
                    $(this).attr("valHidden",ui.item.value);
                    $("#AgentId-"+agentcount).val(ui.item.value);
                },
                close:function( event, ui ) {
                   $("#AgentName-"+agentcount).trigger('keyup');
                } 
            });
            
            $("#AgentName-"+agentcount).keyup(function () {
                var position = $(this).offset();
                $(".ui-widget").css("top", position.top + 30);
                $(".ui-widget").css("left", position.left);
                $(".ui-widget").css("font-size", 10);
            }); 
            
        });
        
        
        var tableLength = $("#commissionTable tbody").find("tr").length;
        console.log("table length " + tableLength);

        for (var i = 1; i <= tableLength; i++) {

            $(name).selectize({
                removeItem: '',
                sortField: 'text',
                create: false,
                dropdownParent: 'body',
                plugins: {
                    'clear_selection': {}
                }

            });

        }
    });
    
function sendDataToDelete(param){ //wii
        $.ajax({
                dataType: 'html',
                type: "POST",
                url: "MProductCommissionDetail.smi",
                data: "productComId="+ param +"&action=delete",
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
//                    alert("Delete Success!!");
//                        var status = $(tr).find("input[name^='textAlert']").val();
//                        if(status != null){
                    jQuery("#textAlertDivDelete").css("display","block");
                    $('#textAlertDivSave').hide();
//                        }
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
//                        jQuery("#textAlertDivDelete").css("display","block");
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
//                        jQuery("#textAlertDivDelete").css("display","block");
                    });

            } else {
                alert('this row for fill data');
            }
        }
    }
    function saveAction(){
        var counter = 0;
        $("#commissionTable tbody").find("tr").each(function(){
                var cloneTr = $(this).clone();
                cloneTr.find('input,select,span').each(function() {
                    $(this).removeClass('hidden');
                    if ($(this).attr('name') === "AgentName-") {
                        $(this).val($(this).attr("valHidden"));
                    }
                    if ($(this).attr('name') === "AgentName-"+counter) {
                        $("#AgentName-"+counter).val($(this).attr("valHidden"));
                    }
                    $(this).attr({
                        name: $(this).attr('name') + counter
                    });
                });
                $("#EditTable tbody").append(cloneTr);
                counter++;
        });
        $("#MProductCommissionDetail").submit();
    }
    
    
    
</script>