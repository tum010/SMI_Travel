<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<script type="text/javascript" src="js/DaytourComission.js"></script> 
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="dateTo" value="${requestScope['DateTo']}" />
<c:set var="dateFrom" value="${requestScope['DateFrom']}" />
<c:set var="selectGuide" value="${requestScope['SelectGuide']}" />
<c:set var="selectAgent" value="${requestScope['SelectAgent']}" />
<c:set var="guideList" value="${requestScope['GuideList']}" />
<c:set var="agentList" value="${requestScope['AgentList']}" />
<c:set var="bookingList" value="${requestScope['BookingList']}" />
<c:set var="result" value="${requestScope['TransactionResult']}" />
<input type="hidden" value="${param.referenceNo}" id="getUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<!--Alert Save -->

<section class="content-header" >
    <h1>
        Operation - Day Tours Agent & Guide Commission
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Operation</a></li>          
        <li class="active"><a href="#">Day Tours Agent & Guide Commission</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Book/DaytourMenu.html'"></div>
        </div>
        <div class="col-sm-10">
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Success!</strong> 
            </div>
            <div id="textAlertDivSaveAddGuide"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Guide Success!</strong> 
            </div>
            <div id="textAlertDivNotSaveAddGuide"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Guide Not Success!</strong> 
            </div>
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Not Success!</strong> 
            </div>
            <!--<div ng-include="'WebContent/Book/BookNavbar.html'"></div>-->
            <div class="row" style="padding-left: 15px; margin-top: -10px;">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Day Tours (Agent & Guide Commission)</b></h4>
                </div>
            </div>
            <div class="row" style="padding-left: 15px; margin-top: -10px;"><hr/></div>
            <form action="DaytourCommission.smi" id="searchDaytourCommissionForm" name="searchDaytourCommissionForm" method="post" role="form" >
                <div class="col-xs-12 ">
                    <input type="hidden" id="searchAction" name="action" value="search">
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">From <font style="color: red">*</font></label>
                    </div>
                    
                    <div class="col-xs-2">
                       <div class=" form-group">     
                            <div class="input-group date fromDate" id="DateFrom">
                                <c:set var="InputDateFrom" value="${dateFrom}" />
                                <fmt:parseDate value="${InputDateFrom}" var="InputDateFrom" pattern="yyyy-MM-dd" />
                                <fmt:formatDate value="${InputDateFrom}" var="InputDateFrom" pattern="dd-MM-yyyy" />
                                <input  id="InputDateFrom" name="InputDateFrom" type="text" 
                                         class="form-control datemask" data-date-format="DD-MM-YYYY"
                                        placeholder="DD-MM-YYYY" value="${InputDateFrom}">
                                <span class="input-group-addon spandate">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div> 
                        </div>
                    </div>
                    <div class="col-xs-1">

                    </div>
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">To <font style="color: red">*</font></label>
                    </div>
                    <div class="col-xs-2 form-group">
                        <div class="input-group date todate" id="DateTo">
                            <c:set var="InputDateTo" value="${dateTo}" />
                            <fmt:parseDate value="${InputDateTo}" var="InputDateTo" pattern="yyyy-MM-dd" />
                            <fmt:formatDate value="${InputDateTo}" var="InputDateTo" pattern="dd-MM-yyyy" />
                            <input id="InputDateTo" name="InputDateTo" type="text"
                                    class="form-control datemask" data-date-format="DD-MM-YYYY"
                                   placeholder="DD-MM-YYYY" value="${InputDateTo}">
                            <span class="input-group-addon spandate">
                                    <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>

                    <div class="col-xs-2 text-right">
                        <button type="submit" id="ButtonSearch" name="ButtonSearch" onclick="" class="btn btn-primary btn-sm"><i class="fa fa-search"></i> Search</button>
                    </div>
                    <div class="col-xs-2 text-left">
                        <button type="button" id="ButtonAdd" name="ButtonAdd" onclick="" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#AddGuideModal"><i class="fa fa-plus"></i> Add Guide</button>
                    </div>
                </div>
                <div class="col-xs-12 form-group" style="margin-top: -10px;">
                    <div class="col-xs-1 text-right">
                        <label class="control-label text-right">Agent</label>
                    </div>
                    <div class="col-xs-2"> 
                        <div class="input-group" id="gr">
                            <input type="hidden" class="form-control" id="agent_id" name="agent_id" value="${selectAgent.id}" />
                            <input type="text" class="form-control" id="agent_code" name="agent_code" value="${selectAgent.code}" />
                            <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#AgentHeaderModal">
                                <span class="glyphicon-search glyphicon"></span>
                            </span>
                        </div>
                    </div>
                    <div class="col-xs-4"> 
                        <input type="text" class="form-control" id="agent_name" name="agent_name" value="${selectAgent.name}" readonly="" >
                    </div>                    
                </div>
                <div class="col-xs-12 form-group" style="margin-top: -10px;">
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
 
            <form action="DaytourCommission.smi" id="saveDaytourCommissionForm" name="saveDaytourCommissionForm" method="post" role="form" >
                <table class="display paginated" id="CommissionTable" name="CommissionTable" cellspacing="0" style="table-layout: fixed;">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hide">Booking ID</th>
                            <th style="width:9%">Tour Date</th>
                            <th style="width:11%">Tour Code</th>
                            <th style="width:7%">Ref. No</th>
                            <th style="width:15%">Client Name</th>
                            <th style="width:10%">Guide Name(<font style="color: red">*</font>)</th>
                            <th style="width:9%">Guide Comm(<font style="color: red">*</font>)</th>
                            <th style="width:10%">Remark Guide</th>
                            <th style="width:10%">Agent Name(<font style="color: red">*</font>)</th>
                            <th style="width:9%">Agent Comm(<font style="color: red">*</font>)</span></th>
                            <th style="width:10%">Remark Agent</th>
                            <th class="hide" >Edit</th>
                        </tr>
                    </thead>
                    <script>
                        guideName = [];
                    </script>
                    <c:forEach var="guide" items="${guideList}" >
                        <script>
                            guideName.push({value: "${guide.id}", label: "${guide.name} ${guide.tel}"});
                        </script>
                    </c:forEach>
                    <script>
                        agentName = [];
                        agentCode = [];
                    </script>
                    <c:forEach var="agent" items="${agentList}" >
                        <script>
                            agentName.push({value: "${agent.id}", label: "${agent.name}"});
                            agentCode.push("${agent.code}");
                            agentCode.push("${agent.name}");
                        </script>
                    </c:forEach>

                    <tbody>
                        <input type="text" id="bookinglistcount" name="bookinglistcount" class="hidden" value="${fn:length(bookingList)}" >
                        <c:forEach var="item" items="${bookingList}" varStatus="status" >
                            <c:set var="color" value=""/>
                            <c:if test="${status.count%2 == 0}">
                                <c:set var="color" value="#F2F2F2"/>
                            </c:if>
                            <tr bgcolor="${color}">
                                <td class="hide"><input type="hidden" id="daytourBookingId-${status.count}" name="daytourBookingId-" value="${item.id}"></td>
                                <td class="text-center">
                                    <fmt:formatDate value="${item.tourdate}" var="tourDate" pattern="dd-MM-yyyy" />${tourDate}
                                </td>
                                <td>${item.daytourcode}</td>
                                <c:set var="refno1" value="${fn:substring(item.refno,0,2)}" />
                                <c:set var="refno2" value="${fn:substring(item.refno,2,7)}" />        
                                <td class="text-center">${refno1}-${refno2}</td>
                                <td>
                                    ${item.initialname} ${item.lastname} ${item.firstname}
                                </td>
                                <td class="selectGuide form-group">
                                    <input type="text" class="form-control guidename" id="GuideName-${status.count}" name="GuideName-"  valHidden="${item.guideid}" value="${item.guidename}" onkeyup="getGuideName('${status.count}')" /> 
<!--                                    <select class="guidename"  id="selectGuide-${status.count}" name="selectGuide-" onchange="getGuideCommission('${item.daytourcode}','guideComm-${status.count}');" onfocus="setDecimalFormat();" class="selectize"   >
                                        <option value="" >--- select ---</option>
                                        <%--<c:forEach var="guide" items="${guideList}" >--%>
                                            <%--<c:set var="select" value="" />--%>
                                            <%--<c:set var="selectedId" value="${item.guideid}" />--%>
                                            <%--<c:if test="${guide.id == selectedId}">--%>
                                                <%--<c:set var="select" value="selected" />--%>
                                            <%--</c:if>--%>
                                            <option value="${guide.id}" ${select}>${guide.name}" &nbsp; : &nbsp;${guide.tel}"</option>   
                                        <%--</c:forEach>--%>
                                    </select>-->
                                </td>
                                <td class="form-group" >
                                    <input type="text" class="form-control decimal-${status.count} guidecom" id="guideComm-${status.count}" name="guideComm-" 
                                           value="${item.guidecommission}" maxlength="14" onfocus="setDecimalFormat(${status.count});">
                                </td>
                                <td class="form-group">
                                    <input type="text" class="form-control" id="guideRemark-${status.count}" name="guideRemark-" 
                                           value="${item.remarkguidecom}" maxlength="255"  >
                                </td>
                                <td class="form-group">
                                    <input type="text" class="form-control agentname" id="AgentName-${status.count}" name="AgentName-"  valHidden="${item.agentid}" value="${item.agentname}" onkeyup="getAgentName('${status.count}')"  /> 
                                </td>
                                <td class="form-group">
                                    <input type="text" class="form-control decimal-${status.count} agentcom" id="agentComm-${status.count}" name="agentComm-" 
                                           value="${item.agentcomission}" maxlength="14" onfocus="setDecimalFormat(${status.count});" />
                                </td>
                                <td class="agentRemark form-group">
                                    <input type="text" class="form-control" id="agentRemark-${status.count}" name="agentRemark-" 
                                           value="${item.remarkagentcom}" maxlength="255" />
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
                    <input type="hidden" value="" id="page" name="page">
                    <input type="hidden" value="" id="currentPage" name="currentPage">
                    <input id="dayCommRows" name="dayCommRows" type="text" class="hidden" />
                    <input type="hidden" id="dateFromSearch" name="InputDateFrom" >                        
                    <input type="hidden" id="dateToSearch" name="InputDateTo" >                        
                    <input type="hidden" id="filterGuide" name="SelectGuide" >                        
                    <!--<input type="hidden" id="filterAgent" name="SelectAgent" >-->
                    <input type="hidden" id="filterAgent" name="agent_id" > 
                    <input type="hidden" id="action" name="action" value="save"> 
                    <a id="ButtonPrintGuide" name="ButtonPrintGuide" onclick="printGuideCommission();" class="btn btn-primary" data-toggle="modal" ><i class="fa fa-print"></i> Print Guide</a>
                    <a id="ButtonPrintAgent" name="ButtonPrintAgent" onclick="printAgentCommission();" class="btn btn-primary" data-toggle="modal"><i class="fa fa-print"></i> Print Agent</a>
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
                            <div class="input-group date guideFromDate" id="guideFromDatePanel">
                                <input  name="guidePrintFrom" id="guidePrintFrom" type="text"
                                        data-date-format="DD-MM-YYYY" class="form-control" 
                                        placeholder="DD-MM-YYYY" />
                                <span class="input-group-addon spandate2">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-2">To</div>
                        <div class="col-xs-5">
                            <div class="input-group date guideToDate" id="guideToDatePanel">
                                <input  name="guidetPrintTo" id="guidePrintTo" type="text" 
                                        data-date-format="DD-MM-YYYY" class="form-control" 
                                        placeholder="DD-MM-YYYY" />
                               <span class="input-group-addon spandate2">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="btnPrintGuideCommission" name="btnPrintGuideCommission" onclick="" class="btn btn-success">OK</button>
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
                <h4 class="modal-title"  id="Titlemodel">Agent Commssion Service</h4>
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
                        <div class="col-xs-5 ">
                            <div class="input-group date agentFromDate" id="agentFromDatePanel">
                                <input  name="agentPrintFrom" id="agentPrintFrom" type="text" 
                                        data-date-format="DD-MM-YYYY" class="form-control"
                                        placeholder="DD-MM-YYYY" />
                                    <span class="input-group-addon spandate2">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 form-group ">
                        <div class="col-xs-2">To</div>
                        <div class="col-xs-5 ">
                            <div class="form-group">
                                 <div class="input-group date agentToDate" id="agentToDatePanel">
                                    <input  name="agentPrintTo" id="agentPrintTo" type="text"
                                            data-date-format="DD-MM-YYYY" class="form-control" 
                                            placeholder="DD-MM-YYYY" />
                                    <span class="input-group-addon spandate2">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                           
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="btnPrintAgentCommission" name="btnPrintAgentCommission" onclick="" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->       
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->

<div class="modal fade" id="AddGuideModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog ">
        <form action="DaytourCommission.smi" id="searchDaytourCommissionAddGuideForm" name="searchDaytourCommissionAddGuideForm" method="post" role="form" class="form-horizontal" >
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
                <button id="btnAdd" type="submit" onclick="addGuide()" class="btn btn-primary">Add</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
                      </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--Modal  Agent-->
<div class="modal fade" id="AgentHeaderModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Agent</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="AgentTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        agent = [];
                    </script>
                    <c:forEach var="a" items="${agentList}">
                        <tr>
                            <td class="agent-id hidden">${a.id}</td>
                            <td class="agent-code">${a.code}</td>
                            <td class="agent-name">${a.name}</td>
                        </tr>
                        <script>
                            agent.push({id: "${a.id}", code: "${a.code}", name: "${a.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="AgentHeaderModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<script type="text/javascript" charset="uts-8">
    $(document).ready(function () { 
        jQuery.curCSS = jQuery.css;
        var InputDateFrom = $('#InputDateFrom').val();
        if(InputDateFrom !== ''){
            $('#InputDateFrom').val(InputDateFrom);
        }
        var InputDateTo = $('#InputDateTo').val();
        if(InputDateTo !== ''){
            $('#InputDateTo').val(InputDateTo);
        }
        $('.datemask').mask('00-00-0000');
    
        $('#CommissionTable').dataTable({
            bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": false,
            "bInfo": true,
            "iDisplayLength":10
        });
        
        
        setDecimalFormatOnload();
        
//        $(".decimal").inputmask({
//            alias: "decimal",
//            integerDigits: 8,
//            groupSeparator: ',',
//            autoGroup: true,
//            digits: 2,
//            allowMinus: false,
//            digitsOptional: false,
//            placeholder: "0.00",
//        });
        
        //datetime
        $('.date').datetimepicker();
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });
        $('.spandate2').click(function() {
            var position = $(this).offset();
            console.log("positon 2:" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top - 85);
        });
        
 
//        var dataAgent = [];
//        dataAgent = agentName;
//        var agentcount= 0 ; 

//        $("#CommissionTable tbody").find("tr").each(function(){ 
//            agentcount++;
//            $("#AgentName-"+agentcount).autocomplete({
//                source: dataAgent,
//                focus: function( event, ui ) {
//                    event.preventDefault();
//                    $(this).val(ui.item.label);
//                },
//                select: function( event, ui ) {
//                    event.preventDefault();
//                    $(this).val(ui.item.label);
//                    $(this).attr("valHidden",ui.item.value);
//                },
//                close:function( event, ui ) {
//                   var editCheckBox = $(this).closest('tr').find('td.edited').children();
//                   $(editCheckBox).attr("checked", true);
//                   $("#AgentName-"+agentcount).trigger('keyup');
//                } 
//            });
//        
//            $("#AgentName-"+agentcount).keyup(function () {
//                var position = $(this).offset();
//                $(".ui-widget").css("top", position.top + 30);
//                $(".ui-widget").css("left", position.left);
//                $(".ui-widget").css("font-size", 10);
//                if($(this).val() == ''){
//                    $(this).attr("valHidden",'');
//                }
//            }); 
//        });
      

        $("#CommissionTable").on('change', 'input,select', function (e) {
            var editCheckBox = $(this).closest('tr').find('td.edited').children();
            $(editCheckBox).attr("checked", true);
        });
        
        
        
    });
    
    function setDecimalFormat(row){
        $(".decimal-"+row).inputmask({
            alias: "decimal",
            integerDigits: 8,
            groupSeparator: ',',
            autoGroup: true,
            digits: 2,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0.00",
        });
    }
    
    function setDecimalFormatOnload(){
        var bookinglistcount = $("#bookinglistcount").val();
        bookinglistcount = parseInt(bookinglistcount);
        for(var i = 1 ; i < bookinglistcount ; i++){
            var guideComm = $("#guideComm-"+i).val();
            var agentComm = $("#agentComm-"+i).val();
            if(guideComm !== "" || agentComm !== "") {
                $(".decimal-"+i).inputmask({
                    alias: "decimal",
                    integerDigits: 8,
                    groupSeparator: ',',
                    autoGroup: true,
                    digits: 2,
                    allowMinus: false,
                    digitsOptional: false,
                    placeholder: "0.00",
                });
            }
        }
    }
    
    function getAgentName(agentcount){
        var dataAgent = [];
        dataAgent = agentName;
        $("#CommissionTable tbody").find("tr").each(function(){ 
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
                if($(this).val() == ''){
                    $(this).attr("valHidden",'');
                }
            }); 
        });
    }
    
    function getGuideName(guidecount){
        var dataGuide = [];
        dataGuide = guideName;
        $("#CommissionTable tbody").find("tr").each(function(){ 
            $("#GuideName-"+guidecount).autocomplete({
                source: dataGuide,
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
                   $("#GuideName-"+guidecount).trigger('keyup');
                } 
            });
        
            $("#GuideName-"+guidecount).keyup(function () {
                var position = $(this).offset();
                $(".ui-widget").css("top", position.top + 30);
                $(".ui-widget").css("left", position.left);
                $(".ui-widget").css("font-size", 10);
                if($(this).val() == ''){
                    $(this).attr("valHidden",'');
                }
            }); 
        });
    }
    
    
    // AGENT 
$(document).ready(function () {
//    var agentCode = [];
//    $.each(agent, function (key, value) {
//        console.log("agentCount=="+agent.length);
//        agentCode.push(value.code);
//           agentCode.push(value.name);
//    });

    $("#agent_code").autocomplete({
        source: agentCode,
        close:function( event, ui ) {
           $("#agent_code").trigger('keyup');

        }
    });
       
        
    $("#agent_code").on('keyup',function(){
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value.toUpperCase();
        $("#agent_id,#agent_name").val(null);
        $.each(agent, function (key, value) {
            if (value.code.toUpperCase() === code  && code.length > 1) {  
                $("#agent_id").val(value.id);
                $("#agent_name").val(value.name);
                $("#agent_code").val(value.code);
            }
            else if(value.name.toUpperCase() === name && name.length > 1){
                $("#agent_code").val(value.code);
                $("#agent_id").val(value.id);
                $("#agent_name").val(value.name);
            }
        }); 

    }); 
       
        
    $("#AgentTable tr").on('click', function () {
        var agent_id = $(this).find(".agent-id").text();
        var agent_code = $(this).find(".agent-code").text();
        var agent_name = $(this).find(".agent-name").text();
        $("#agent_id").val(agent_id);
        $("#agent_code").val(agent_code);
        $("#agent_name").val(agent_name);
        $("#AgentHeaderModal").modal('hide');
    });
    // AGENT TABLE
    $('#AgentTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10,
        "aaSorting": [[ 1, "asc" ]]
    });
    $('#AgentTable tbody').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
});
   
</script>

<c:if test="${! empty result}">
    <c:if test="${result =='success'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${result =='fail'}">        
        <script language="javascript">
           $('#textAlertDivNotSave').show();
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