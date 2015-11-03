<%-- 
    Document   : DayTourOtherComission
    Created on : May 22, 2015, 9:40:38 AM
    Author     : Kanokporn
--%>

<%@page import="com.smi.travel.util.UtilityFunction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<script type="text/javascript" src="js/DaytourComission.js"></script> 
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="dateTo" value="${requestScope['DateTo']}" />
<c:set var="dateFrom" value="${requestScope['DateFrom']}" />
<c:set var="selectGuide" value="${requestScope['SelectGuide']}" />
<c:set var="selectAgent" value="${requestScope['SelectAgent']}" />
<c:set var="guideList" value="${requestScope['GuideList']}" />
<c:set var="agentList" value="${requestScope['AgentList']}" />
<c:set var="bookingList" value="${requestScope['BookingList']}" /> <!-- Table Booking After Click Button Search -->
<c:set var="result" value="${requestScope['TransactionResult']}" />
<input type="hidden" value="${param.referenceNo}" id="getUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">

<section class="content-header" >
    <h1>
        Operation - Other Agent & Guide Commission
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Operation</a></li>          
        <li class="active"><a href="#">Other Agent & Guide Commission</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Book/OperationOther.html'"></div>
        </div>
        <div class="col-sm-10">
            <div id="textAlertDivSaveAddGuide"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Guide Success!</strong> 
            </div>
            <div id="textAlertDivNotSaveAddGuide"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Guide Not Success!</strong> 
            </div>
            <!--<div ng-include="'WebContent/Book/BookNavbar.html'"></div>-->
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Others (Agent & Guide Commission)</b></h4>
                </div>
            </div>
            <hr/>
            
            <!--Search Content Agent and guide -->
            <form action="DayTourOtherComission.smi" id="searchDaytourCommissionForm" name="searchDaytourCommissionForm" method="post" role="form" >
                <div class="col-xs-12 ">
                    <input type="hidden" id="searchAction" name="action" value="search">
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">From <font style="color: red">*</font></label>
                    </div>
                    <div class="col-xs-2 form-group">
                        <div class="input-group  date " id="DateFrom">
                            <input  id="InputDateFrom" name="InputDateFrom" type="text" data-date-format="YYYY-MM-DD" class="form-control datemask" placeholder="YYYY-MM-DD" value="${dateFrom}">
                            <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span>
                            </span>
                        </div>
                    </div>
                    <div class="col-xs-1">

                    </div>
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">To <font style="color: red">*</font></label>
                    </div>
                    <div class="col-xs-2 form-group">
                        <div class="input-group  date" id="DateTo">
                            <input id="InputDateTo" name="InputDateTo" type="text" data-date-format="YYYY-MM-DD" class="form-control datemask" placeholder="YYYY-MM-DD" value="${dateTo}">
                            <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span>
                            </span>
                        </div>
                    </div>
                    <div class="col-xs-2 text-right">
                        <button type="submit" id="ButtonSearch" name="ButtonSearch" onclick="searchAction()" class="btn btn-primary "><i class="fa fa-search"></i> Search</button>
                    </div>
                    <div class="col-xs-2 text-left">
                        <button type="button" id="ButtonAdd" name="ButtonAdd" onclick="" class="btn btn-primary" data-toggle="modal" data-target="#AddGuideModal"><i class="fa fa-plus"></i> Add Guide</button>
                    </div>
                </div>
                <div class="col-xs-12 form-group">
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">Agent</label>
                    </div>
                    <div class="col-xs-6">
                        <select name="SelectAgent" id="SelectAgent"  class="form-control"   >
                            <option value="">- - AGENT - -</option>
                            <c:forEach var="item" items="${agentList}" >
                                <c:set var="select" value="" />
                                <c:set var="selectedGuideId" value="${selectAgent}" />
                                <c:if test="${item.id == selectedGuideId}">
                                    <c:set var="select" value="selected" />
                                </c:if>
                                <option value="<c:out value="${item.id}" />" ${select}><c:out value="${item.name}" /></option>   
                            </c:forEach>
                        </select>                    
                    </div>
                </div>
                <div class="col-xs-12 form-group">
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">Guide</label>
                    </div>
                    <div class="col-xs-6">
                        <select name="SelectGuide" id="SelectGuide"  class="form-control"   >
                            <option value="">- - GUIDE - -</option>
                            <c:forEach var="item" items="${guideList}" >
                                <c:set var="select" value="" />
                                <c:set var="selectedId" value="${selectGuide}" />
                                <c:if test="${item.id == selectedId}">
                                    <c:set var="select" value="selected" />
                                </c:if>
                                <option value="<c:out value="${item.id}" />" ${select}><c:out value="${item.name}" /></option>   
                            </c:forEach>
                        </select>
                    </div>
                </div>
                   
            </form>
                            
 <!--table form-->
            <!--Table show detail when search -->
           
            <form action="DayTourOtherComission.smi" id="saveDaytourCommissionForm" name="saveDaytourCommissionForm" method="post" role="form" >
                <table class="display" id="CommissionTable" name="CommissionTable" cellspacing="0">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hide">Booking ID</th>
                            <th style="width:5%">Code</th>
                            <th style="width:10%">Name</th>
                            <th style="width:10%">Date</th>
                            <th style="width:5%">Ref. No</th>
                            <th style="width:15%">Client Name</th>
                            <th style="width:15%">Guide Name</th>
                            <th style="width:6%">Guide Comm</th>
                            <th style="width:12%">Remark Guide</th>
                            <th style="width:15%">Agent Name</th>
                            <th style="width:6%">Agent Comm</th>
                            <th style="width:12%">Remark Agent</th>
                            <th class="hide" >Edit</th>
                        </tr>
                    </thead>
                    <script>
                        guideName = [];
                    </script>
                    <c:forEach var="guide" items="${guideList}" >
                        <script>
                            guideName.push({id: "${guide.id}", name: "${guide.name}"});
                        </script>
                    </c:forEach>
                    <script>
                        agentName = [];
                    </script>
                    <c:forEach var="agent" items="${agentList}" >
                        <script>
                            agentName.push({value: "${agent.id}", label: "${agent.name}"});
                        </script>
                    </c:forEach>

                    <tbody>
                        <c:forEach var="item" items="${bookingList}" varStatus="status" >
                            <tr>
                                <td class="hide"><input type="hidden" id="daytourBookingId-${status.count}" name="daytourBookingId-" value="${item.id}"></td>
                                <td>${item.product.code}</td>
                                <td>${item.product.name}</td>
                                <td>${item.otherDate}</td>
                                <c:set var="refno1" value="${fn:substring(item.master.referenceNo,0,2)}" />
                                <c:set var="refno2" value="${fn:substring(item.master.referenceNo,2,7)}" />   
                                <td>${item.master.referenceNo}  </td>
                                <td>${item.master.customer.firstName} &nbsp; ${item.master.customer.lastName} </td>
                                <td class="selectGuide form-group">  
                                    <select class="guidename"  id="selectGuide-${status.count}" name="selectGuide-" onchange="getGuideCommission('${item.guide.name}','guideComm-${status.count}');" class="selectize"   >
                                        <option value="" >--- select ---</option>
                                        <c:forEach var="guide" items="${guideList}" >
                                            <c:set var="select" value="" />
                                            <c:set var="selectedId" value="${item.guide.id}" />
                                            <c:if test="${guide.id == selectedId}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option value="${guide.id}" ${select}>${guide.name}" &nbsp; : &nbsp;${guide.tel}"</option>   
                                        </c:forEach>
                                    </select>
                                </td>
                                <td class="form-group" >
                                    <input type="text" class="form-control money guidecom" id="guideComm-${status.count}" name="guideComm-" 
                                           value="${item.guideCommission}" maxlength="14">
                                </td>
                                <td class="form-group">
                                    <input type="text" class="form-control" id="guideRemark-${status.count}" name="guideRemark-" 
                                           value="${item.remarkGuideCommission}" maxlength="255">
                                </td>
                                <td class="form-group">
                                    <input type="text" class="form-control agentname" id="AgentName-${status.count}" name="AgentName-" 
                                           valHidden="${item.agent.id}" value="${item.agent.name}"  /> 
                                </td>
                                <td class="form-group">
                                    <input type="text" class="form-control money agentcom" id="agentComm-${status.count}" name="agentComm-" 
                                           value="${item.agentCommission}" maxlength="14">
                                </td>
                                <td class="agentRemark form-group">
                                    <input type="text" class="form-control" id="agentRemark-${status.count}" name="agentRemark-" 
                                           value="${item.remarkAgentCommission}" maxlength="255">
                                </td>
                                <td class="hidden edited">
                                    <input type="checkbox" class="form-control" id="hasEdit-${status.count}" name="hasEdit-" >
                                </td>
                            </tr>
                            <c:if test="${status.last}">
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
                
                <table class="display hide" id="EditTable" name="EditTable">
                    <tbody></tbody>
                </table>
                <hr/>
                <div class="text-center">
                    <input id="dayCommRows" name="dayCommRows" type="text" class="hidden" />
                    <input type="hidden" id="dateFromSearch" name="InputDateFrom" >                        
                    <input type="hidden" id="dateToSearch" name="InputDateTo" >                        
                    <input type="hidden" id="filterGuide" name="SelectGuide" >                        
                    <input type="hidden" id="filterAgent" name="SelectAgent" >                        
                    <input type="hidden" id="action" name="action" value="save"> 
                   <a id="ButtonPrintGuide" name="ButtonPrintGuide" class="btn btn-primary" data-toggle="modal" data-target="#GuideModal"><i class="fa fa-print"></i> Print Guide</a>
                   <a id="ButtonPrintAgent" name="ButtonPrintAgent" class="btn btn-primary" data-toggle="modal" data-target="#AgentModal"><i class="fa fa-print"></i> Print Agent</a>
                   <button type="submit" id="ButtonSave" name="ButtonSave" onclick="saveDaytourCommission();"  class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                </div>
            </form>
        </div>
        
    </div>
</div>

<!--Guide Modal-->
<div class="modal fade" id="GuideModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Guide Commission Service</h4>
            </div>
            <div class="modal-body" id="">
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-2">Guide</div>
                        <div class="col-xs-9">
                            <select id="selGuideReport" class="form-control">
                                <option value="">- - GUIDE - -</option>
                                <c:forEach var="item" items="${guideList}" >
                                    <option value="<c:out value="${item.id}" />" ${select}><c:out value="${item.name}" /></option>   
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-2">From</div>
                        <div class="col-xs-5">
                            <div class="input-group date form_datetime">
                                <input  name="guidePrintFrom" id="guidePrintFrom" type="text" data-date-format="YYYY-MM-DD" class="form-control" placeholder="YYYY-MM-DD">
                                <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-2">To</div>
                        <div class="col-xs-5">
                            <div class="input-group date form_datetime">
                                <input  name="guidetPrintTo" id="guidePrintTo" type="text" data-date-format="YYYY-MM-DD" class="form-control" placeholder="YYYY-MM-DD">
                                <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="printGuideCommission();" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->

<!--Agent Modal-->
<div class="modal fade" id="AgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Agent Commission Service</h4>
            </div>
            <div class="modal-body" id="">
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-2">Agent</div>
                        <div class="col-xs-9">
                            <select id="selAgentReport"  class="form-control">
                                <option value="">- - AGENT - -</option>
                                <c:forEach var="item" items="${agentList}" >
                                    <option value="<c:out value="${item.id}" />"><c:out value="${item.name}" /></option>   
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-2">From</div>
                        <div class="col-xs-5">
                            <div class="input-group date form_datetime" >
                                <input  name="agentPrintFrom" id="agentPrintFrom" type="text" data-date-format="YYYY-MM-DD" class="form-control" placeholder="YYYY-MM-DD">
                                <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 form-group form_datetime">
                        <div class="col-xs-2">To</div>
                        <div class="col-xs-5">
                            <div class="input-group date">
                                <input  name="agentPrintTo" id="agentPrintTo" type="text" data-date-format="YYYY-MM-DD" class="form-control" placeholder="YYYY-MM-DD">
                                <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="printAgentCommission();" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->
<div class="modal fade" id="AddGuideModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog ">
        <form action="DayTourOtherComission.smi" id="searchDaytourCommissionAddGuideForm" name="searchDaytourCommissionAddGuideForm" method="post" role="form" class="form-horizontal" >
        <div class="modal-content">
            <div class="modal-header">    
               <h4 class="modal-title">Add Guide</h4>                              
            </div>        
            <div class="modal-body" id="add">   
                
                <input type="text" class="hidden" id="addGuideAction" name="addGuideAction" value="addGuide">
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-3 text-right">Name <font style="color: red">*</font></div>
                        <div class="col-xs-7" style="padding-left: 0px;width: 345px;">
                            <input  name="guideName" id="guideName" type="text"  class="form-control" />
                        </div>
                    </div>
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-3 text-right">Detail <font style="color: red">*</font></div>
                        <div class="col-xs-7 ">
                            <div class="form-group" >
                               <input  name="guideDetail" id="guideDetail" type="text"  class="form-control" />
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 form-group ">
                        <div class="col-xs-3 text-right">Tel</div>
                        <div class="col-xs-7 ">
                            <div class="form-group">
                                <input  name="guideTel" id="guideTel" type="tel"  maxlength="100" class="form-control number" />
                            </div> 
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button id="btnAdd" type="submit" onclick="addGuideOtherCommission()" class="btn btn-primary">Add</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
                      </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript" charset="uts-8">
    $(document).ready(function () { 
        jQuery.curCSS = jQuery.css;
        $('#CommissionTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": true,
            "bInfo": true,
            "iDisplayLength":10
        });
        

        $('.form_datetime').datetimepicker({ 
            pickTime: false      
        });
 
        var dataAgent = [];
        dataAgent = agentName;
        var agentcount= 0 ; 
//        console.log("dataAgent : "+dataAgent);
        $("#CommissionTable tbody").find("tr").each(function(){ 
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
                },
                close:function( event, ui ) {
                   var editCheckBox = $(this).closest('tr').find('td.edited').children();
                   $(editCheckBox).attr("checked", true);
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
      

        $("#CommissionTable").on('change', 'input,select', function (e) {
            var editCheckBox = $(this).closest('tr').find('td.edited').children();
            $(editCheckBox).attr("checked", true);
        });
        
        

    });
    
    
   
</script>
<style>
 .bootstrap-datetimepicker-widget { 
    left: 650px !important;
    position: absolute !important;
    right: auto !important;
    top: 290px !important;
}
</style>
<c:if test="${! empty result}">
    <c:if test="${result =='success'}">        
        <script language="javascript">
            alert("save successful");
        </script>
    </c:if>
    <c:if test="${result =='fail'}">        
        <script language="javascript">
            alert("save unsuccessful");
        </script>
    </c:if>
    <c:if test="${result =='guideunsuccess'}">        
        <script language="javascript">
            $('#textAlertDivNotSaveAddGuide').show();
        </script>
    </c:if>
    <c:if test="${result =='guidesuccess'}">        
        <script language="javascript">
           $('#textAlertDivSaveAddGuide').show();
        </script>
    </c:if>
</c:if>