<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/SearchTicketFare.js"></script> 
<link href="css/jquery-ui.css" rel="stylesheet">
<c:set var="airlineList" value="${requestScope['airlineList']}" />
<c:set var="ticketFare" value="${requestScope['ticketFare']}" />
<c:set var="dataList" value="${requestScope['Ticket_List']}" /> 
<%--<c:set var="ticketAlreadyUse" value="${requestScope['TicketAlreadyUse']}" />--%>
<section class="content-header" >
    <h1>
        Checking - Air Ticket
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Checking</a></li>          
        <li class="active"><a href="#">Search Ticket Fare</a></li>
    </ol>
</section>

<div style="padding-top: 15px;padding-right: 0px "ng-app=""> 
    <div class="row">
        <!-- Alert Del-->
        <div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Delete Success!</strong> 
        </div>
        <!-- Alert Not Del-->
        <div id="textAlertDivNotDelete"  style="display:none;" class="alert alert-danger" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Delete Not Success!</strong> 
        </div>
        
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Checking/CheckingAirTicketMenu.html'"></div>
        </div>
        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6" style="padding-right: 15px">
                    <h4><b>Search Ticket Fare</b></h4>
                </div>
            </div>
            <hr/>
            
            <form action="SearchTicketFare.smi" method="post" id="SearchTicketFareForm" name="SearchTicketFareForm" role="form">
                <div class="panel panel-default">
                    <div class="panel-body"  style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Ticket&nbsp;Type&nbsp;</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <select id="ticketType" name="ticketType" class="form-control selectize" >
                                    <option value="">--- Type ---</option> 
                                    <c:choose>
                                        <c:when test="${requestScope['TicketType'] == 'B'}">
                                            <c:set var="selectedB" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="B" ${selectedB}>BSP</option>
                                    <c:choose>
                                        <c:when test="${requestScope['TicketType'] == 'D'}">
                                            <c:set var="selectedD" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="D" ${selectedD}>DOMESTIC</option>
                                    <c:choose>
                                        <c:when test="${requestScope['TicketType'] == 'A'}">
                                            <c:set var="selectedA" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="A" ${selectedA}>AGENT</option>
                                </select>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Ticket Routing </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <select id="ticketRouting" name="ticketRouting" class="form-control selectize">
                                    <option value="">--- Routing ---</option> 
                                    <c:choose>
                                        <c:when test="${requestScope['TicketRouting'] == 'I'}">
                                            <c:set var="selectedI" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="I" ${selectedI}>INTER</option>
                                    <c:choose>
                                        <c:when test="${requestScope['TicketRouting'] == 'D'}">
                                            <c:set var="selectedD" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="D" ${selectedD}>DOMESTIC</option>
                                    <c:choose>
                                        <c:when test="${requestScope['TicketRouting'] == 'C'}">
                                            <c:set var="selectedC" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="C" ${selectedC}>CANCEL</option>
                                </select>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Airline&nbsp;</label>
                            </div>
                            <div class="col-xs-1" style="width: 180px">
                                <select name="ticketAirline" id="ticketAirline" class="form-control">
                                    <option value="">--- Airline ---</option> 
                                    <c:forEach var="table" items="${airlineList}" >
                                        <c:set var="select" value="" />
                                        <c:set var="selectedId" value="${ticketFare.airline}" />
                                        <c:if test="${table.id == selectedId}">
                                            <c:set var="select" value="selected" />
                                        </c:if>
                                        <option value="${table.id}" ${select}>${table.code}</option>  
                                    </c:forEach>
                                </select>
                            </div>    
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Ticket No </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">
                                    <input id="ticketNo" name="ticketNo" type="text" class="form-control" value="${ticketFare.ticketNo}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Issue Date </label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date'>
                                    <input id="issueDate" name="issueDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['issueDate']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Invoice No </label>
                            </div>
                            <div class="col-xs-1 form-group" style="width: 200px">
                                <div class="input-group">
                                    <input id="invoiceNo" name="invoiceNo" type="text" class="form-control" value="${requestScope['invoiceNo']}">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Department </label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <select id="department" name="department" class="form-control selectize">
                                    <option value="">--- Department ---</option> 
                                     <c:choose>
                                        <c:when test="${requestScope['department'] == 'wendy'}">
                                            <c:set var="selected1" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="wendy" ${selected1}>wendy</option>
                                    <c:choose>
                                        <c:when test="${requestScope['department'] == 'inbound'}">
                                            <c:set var="selected2" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="inbound" ${selected2}>inbound</option>
                                    <c:choose>
                                        <c:when test="${requestScope['department'] == 'outbound'}">
                                            <c:set var="selected3" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="outbound" ${selected3}>outbound</option>

                                </select>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 150px">
                            </div>
                            <div class="col-xs-1 text-left" style="width: 190px">
                                <input type="hidden" id="ticketId" name="ticketId" >
                                <input type="hidden" name="action" id="action" value="">
                                <button style="height:34px" type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="searchAction();" class="btn btn-primary btn-sm">
                                    <i class="fa fa-search"></i>&nbsp;Search</button></button>
                            </div>
                        </div>
                    </div>
                </div>
                <!--Table-->
                <div class="row">
                    <div class="col-md-12 ">
                        <table id="TicketFareList" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr class="datatable-header" >
                                    <th style="width:5%;">Type</th>
                                    <th style="width:5%;">Buy</th>
                                    <th style="width:5%;">Airline</th>
                                    <th style="width:15%;">Ticket No</th>
                                    <th style="width:15%;">Issue Date</th>
                                    <th style="width:15%;">Inv No</th>
                                    <th style="width:10%;">Department</th>
                                    <th style="width:10%;">Fare</th>
                                    <th style="width:10%;">Tax</th>
                                    <th style="width:10%;">T Com</th>
                                    <th style="width:10%;">A Com</th>
                                    <th style="width:10%;">Diff Vat</th>
                                    <th style="width:10%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="table" items="${dataList}" varStatus="dataStatus">
                                    <tr>
                                        <td align="center">${table.type}</td>
                                        <td align="center">${table.buy}</td>
                                        <td align="center">${table.airline}</td>
                                        <td align="center">${table.ticketNo}</td>
                                        <td align="center">${table.issueDate}</td>
                                        <td align="center">${table.invoiceNo}</td>
                                        <td align="center">${table.department}</td>
                                        <td class="money">${table.fare}</td>
                                        <td class="money">${table.tax}</td>
                                        <td class="money">${table.ticketCommission}</td>
                                        <td class="money">${table.agentCommission}</td>
                                        <td align="right"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.diffVat}" /></td>
                                        <td> 
                                            <center> 
                                            <a  href="AddTicketFare.smi?ticketId=${table.id}&action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                            <span  class="glyphicon glyphicon-remove deleteicon"  onclick="deleteTicket('${table.id}','${table.ticketNo}')" 
                                                   data-toggle="modal" data-target="#DelTicket" >  </span>
                                            </center>
                                            <input type="hidden" name="deleteTicketNo" id="deleteTicketNo" value="${table.ticketNo}">
                                            <input type="hidden" name="deleteTicketId" id="deleteTicketId" value="${table.id}">
                                        </td>                                    
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>      
                    </div>
                </div>
            </form>
        </div> 
    </div> 
</div>

<!--Delete Bank Modal-->
<div class="modal fade" id="DelTicket" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"> Delete Ticket </h4>
            </div>
            <div class="modal-body" id="delTicketNo"></div>
            <div class="modal-footer" id="delfooter">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->   
<c:if test="${! empty requestScope['result']}">
    <c:if test="${requestScope['result'] =='delete successful'}">        
        <script language="javascript">
           $('#textAlertDivDelete').show();
           
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='delete unsuccessful'}">        
        <script language="javascript">
           $('#textAlertDivNotDelete').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='already use all'}">        
        <script language="javascript">
           $(document).ready(function() {
               var TicketAlreadyUse = ${requestScope['TicketAlreadyUse']}
               alert("TicketNo "+TicketAlreadyUse+" is already use in payment airline & refund airline.");
           });
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='already use payment'}">        
        <script language="javascript">
           $(document).ready(function() {
               var TicketAlreadyUse = ${requestScope['TicketAlreadyUse']}
               alert("TicketNo "+TicketAlreadyUse+" is already use in payment airline.");
           });
        </script>
    </c:if>   
    <c:if test="${requestScope['result'] =='already use refund'}">        
        <script language="javascript">
           $(document).ready(function() {
               var TicketAlreadyUse = ${requestScope['TicketAlreadyUse']}
               alert("TicketNo "+TicketAlreadyUse+" is already use in refund airline.");
           });
        </script>
    </c:if>   
</c:if>  
        
<!--Script-->       
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $('.date').datetimepicker();
        
        $(".money").mask('000,000,000.00', {reverse: true});
        
        var table = $('#TicketFareList').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false
        });
        
        $( ".numerical" ).on('input', function() { 
            var value=$(this).val().replace(/[^0-9.,]*/g, '');
            value=value.replace(/\.{2,}/g, '.');
            value=value.replace(/\.,/g, ',');
            value=value.replace(/\,\./g, ',');
            value=value.replace(/\,{2,}/g, ',');
            value=value.replace(/\.[0-9]+\./g, '.');
            $(this).val(value)
        });
        
        $('#TicketFareList tbody').on('click', 'tr', function() {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#TicketFareList tbody tr.row_selected').attr("id"));
            }
        });
        
        $('.date').datetimepicker();

    });
    
function searchAction() {
    var action = document.getElementById('action');
    action.value = 'search';
    var ticketNo = document.getElementById('ticketNo');
    ticketNo.value = $("#ticketNo").val();
    var ticketType = document.getElementById('ticketType');
    ticketType.value = $("#ticketType").val();
    var ticketRouting = document.getElementById('ticketRouting');
    ticketRouting.value = $("#ticketRouting").val();
    var ticketAirline = document.getElementById('ticketAirline');
    ticketAirline.value = $("#ticketAirline").val();
    var issueDate = document.getElementById('issueDate');
    issueDate.value = $("#issueDate").val();
    var department = document.getElementById('department');
    department.value = $("#department").val();
    document.getElementById('SearchTicketFareForm').submit();
}

function deleteTicket(id,ticketNo){
    var TicketId = document.getElementById('ticketId');
    TicketId.value = id;
    var TicketNo = document.getElementById('ticketNo');
    TicketNo.value = ticketNo;
    $("#deleteTicketNo").val(ticketNo);
    $("#deleteTicketId").val(id);
    document.getElementById('delTicketNo').innerHTML = "Are you sure to delete Ticket No : " + ticketNo + " ?";
}

function Delete() {
    var action = document.getElementById('action');
    action.value = 'delete';
    var deleteTicketNo = document.getElementById('deleteTicketNo');
    deleteTicketNo.value = $("#deleteTicketNo").val();
    var deleteTicketId = document.getElementById('deleteTicketId');
    deleteTicketId.value = $("#deleteTicketId").val();
    document.getElementById('SearchTicketFareForm').submit();
}

</script>
