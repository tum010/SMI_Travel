<%-- 
    Document   : Book
    Created on : Dec 12, 2014, 8:25:47 PM
    Author     : Sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">
<c:set var="dataList" value="${requestScope['booking_list']}" />
<c:set var="userdepartment" value="${requestScope['userdepartment']}" />
<c:set var="booking_date" value="${requestScope['booking_date']}" />
<c:set var="BookstatusList" value="${requestScope['booking_status']}" /> 
<c:set var="BookpaybyList" value="${requestScope['payby_list']}" />
<c:set var="BookbanktransferList" value="${requestScope['banktrasfer_list']}" />

<section class="content-header" >
    <h1>
        Booking
    </h1>
    <ol class="breadcrumb">
        <li><a href="BookDetail.smi?action=new"><i class="fa fa-book"></i> Booking</a></li>          
    </ol>
</section>




<div class ="container"  style="padding-top: 15px;"> 


    <form action="Book.smi" method="post" id="BookSearch" role="form">
        <div class="row" >
            <div class="col-md-12 ">
                <div class="col-md-2">
                    <div class="form-group">
                        <label for="Country">Ref. No</label>
                        <input type="text"  class="form-control" id="refno" name="refno"  value="${requestScope['refno']}" >
                    </div>
                </div>
                <div class="col-md-2 ">
                    <div class="form-group">
                        <label for="fromcity">Passenger Lastname</label>
                        <input type="text"  class="form-control" id="PassLast" name="PassLast" value="${requestScope['PassLast']}">
                    </div>
                </div>  
                <div class="col-md-2 ">
                    <div class="form-group">
                        <label for="fromcity">Passenger Firstname</label>
                        <input type="text"  class="form-control" id="PassFirst" name="PassFirst" value="${requestScope['PassFirst']}" >
                    </div>
                </div>    
                <div class="col-md-2">
                    <div class="form-group">
                        <label for="fromCode">Section</label>
                        <c:choose>
                            <c:when test="${requestScope['section'] == 1}">
                                <c:set var="mybook" value="selected" />
                            </c:when>
                            <c:when test="${requestScope['section'] == 2}">
                                <c:set var="mydepart" value="selected" />
                            </c:when>
                            <c:when test="${requestScope['section'] == 3}">
                                <c:set var="all" value="selected" />
                            </c:when>
                        </c:choose>
                        <select name="section" id="section"  class="form-control">
                            <option value="1"  ${mybook}>My booking </option>
                            <option value="2"  ${mydepart}>My department</option>
                            <option value="3"  ${all}>All</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label for="Bookdate">Booking Date</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="Bookdate" name="Bookdate" 
                                   data-date-format="YYYY-MM-DD" value="${Bookdate}" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>

                    </div>


                </div>  
                <div class="col-md-2">
                    <div class="form-group">
                        <label for="fromName">Status</label>

                        <select name="status" id="status"  class="form-control">
                            <option value=""  selected="selected"> </option>
                            <c:forEach var="table" items="${BookstatusList}">
                                <c:set var="select" value="" />
                                <c:if test="${table.id == requestScope['status']}">
                                    <c:set var="select" value="selected" />
                                </c:if>
                                <option value='${table.id}' ${select}>${table.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                            
                <div class="col-md-2">
                    <div class="form-group">
                        <a data-toggle="collapse" href="#collapseExample${advanced.search}" aria-expanded="false" aria-controls="collapseExample${advanced.search}">
                        <span id="SpanEdit${advanced.search}">Advanced Search</span>
                        <!--<span id="SpanEdit${advanced.search}" class="glyphicon glyphicon-list-alt"></span>-->
                        </a>
                    </div>
                </div>

                <div class="collapsing" id="collapseExample${advanced.search}" aria-expanded="false">
                    <div class="col-md-11">
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="Country">PNR</label>
                                <input type="text"  class="form-control" id="pnr" name="pnr"  value="${requestScope['pnr']}" >
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="Country">Ticket No.</label>
                                <input type="text"  class="form-control" id="ticketNo" name="ticketNo"  value="${requestScope['ticketNo']}" >
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label for="Country">Pay By</label>
                                <select class="form-control" id="payBy" name="payBy"  value="${requestScope['payBy']}" onchange="bankTrasferField()">
                                    <option value=""> </option>                               
                                    <c:forEach var="table" items="${BookpaybyList}">
                                        <c:set var="select" value="" />
                                        <c:if test="${table.id == requestScope['payBy']}">
                                            <c:set var="select" value="selected" />
                                        </c:if>
                                        <option value='${table.id}' ${select}>${table.name}</option>
                                    </c:forEach>
                                </select>    
                            </div>
                        </div>
                        <c:set var="bankTransferField" value="hidden"/>
                        <c:if test="${requestScope['payBy'] == '4'}">
                            <c:set var="bankTransferField" value=""/>
                        </c:if>
                        <div id="bankTransferField" class="col-md-2 ${bankTransferField}">
                            <div class="form-group">
                                <label for="Country">Bank Transfer</label>
                                <select class="form-control" id="bankTransfer" name="bankTransfer"  value="${requestScope['bankTransfer']}">
                                    <option value=""> </option>                               
                                    <c:forEach var="table" items="${BookbanktransferList}">
                                        <c:set var="select" value="" />
                                        <c:if test="${table.id == requestScope['bankTransfer']}">
                                            <c:set var="select" value="selected" />
                                        </c:if>
                                        <option value='${table.id}' ${select}>${table.code} (${table.accNo})</option>
                                    </c:forEach>
                                </select>    
                            </div>
                        </div>        
                    </div>
                </div>
                 
                <div class="row-fluid">
                    <div class="form-actions pull-right" style="padding-top: 20px;" >
                        <button type="button" id="acs" onclick="searchAction()" class="btn btn-primary"><span class="fa fa-search"></span> Search</button>           
                        <input type="hidden" name="action" id="action">
                        <a id="btnNew"  href="BookDetail.smi?&action=new" class="btn btn-success" ><span class="fa fa-plus-circle"></span> New</a>

                    </div>
                </div> 
            </div>    
        </div>

    </form>




    <!--Table-->
    <div class="row">
        <div class="col-md-12 ">
            <table id="BookList" class="display" cellspacing="0" style="table-layout: fixed">
                <thead>
                    <tr class="datatable-header" >
                        <th style="width: 6%">Ref No</th>
                        <th style="width: 7%">Agent</th>
                        <th style="width: 15%">Leader</th>
                            <c:choose>
                                <c:when test="${userdepartment  == 1}">
                                <th>PNR</th>
                                <th style="width: 7%">Depart Date</th>
                                </c:when>
                                <c:when test="${userdepartment  == 4}">
                                <th style="width: 10%">Hotel</th>
                                <th style="width: 7%">Check in Date</th>
                                </c:when> 
                                <c:otherwise>
                                <th>PNR</th>
                                <th style="width: 7%">Depart Date</th>
                                <th style="width: 10%">Hotel</th>
                                <th style="width: 7%">Check in Date</th>
                                </c:otherwise>
                            </c:choose>
                        <th style="width: 7%">Create Date</th>
                        <th style="width: 5%">By</th>
                       
                        <th style="width: 5%">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="table" items="${dataList}">
                        <c:set var="refno1" value="${fn:substring(table.refno, 0, 2)}" />
                        <c:set var="refno2" value="${fn:substring(table.refno, 2,7)}" />
                <tr>
                            <td><center><c:out value="${refno1}-${refno2}" /></center></td>
                    <td><center><c:out value="${table.agentCode}" /></center></td>
                    <td><c:out value="${table.leaderName}" /> </td>
                    <c:choose>
                        <c:when test="${userdepartment == 1}">
                            <td><center><c:out value="${table.pnr}" /></center></td>
                            <td><center><c:out value="${table.firstDepartDate}" /></center></td>  
                            </c:when>
                            <c:when test="${userdepartment  == 4}">
                            <td><c:out value="${table.hotelName}" /></td>  
                            <td><center><c:out value="${table.firstCheckinDate}" /></center></td>  
                            </c:when> 
                            <c:otherwise>
                            <td><center><c:out value="${table.pnr}" /></center></td>
                            <td><center><c:out value="${table.firstDepartDate}" /></center></td>  
                            <td><c:out value="${table.hotelName}" /></td>  
                            <td><center><c:out value="${table.firstCheckinDate}" /></center></td>  
                            </c:otherwise>
                        </c:choose>

                    <td><center><c:out value="${table.createDate}" /></center></td>  
                    <td><center><c:out value="${table.createBy}" /></center></td> 
                  
                    <td>
                        <center>
                            <a href="BookDetail.smi?referenceNo=${table.refno}&action=edit"><span class="glyphicon glyphicon-th-list"></span></a>
                            <span onclick="getSummaryBook('${table.refno}'); getSummaryTel('${table.tel}','${table.remark}','${table.email}');" class="glyphicon glyphicon glyphicon-list-alt"></span>
                        </center>
                    </td>           
                </tr>  
                </c:forEach>
                </tbody>
            </table>      
        </div>


    </div>
    
    <div class="row" style="padding-top: 10px">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Summary</h3>
                    </div>
                    <div class="panel-body">
                        
                        <div class="col-md-12">
                            <div class="col-md-2">
                                <div class="form-group">
                                    <label for="telNo">Tel</label>
                                    <input type="text" readonly="" class="form-control" maxlength="20" id="telNo" name="telNo" >
                                </div>
                            </div>
                             <div class="col-md-2 ">
                                <div class="form-group">
                                    <label for="remark">Email</label>
                                    <input type="text" readonly="" class="form-control" maxlength="50" id="email" name="email" >
                                </div>
                            </div>
                            <div class="col-md-2 ">
                                <div class="form-group">
                                    <label for="remark">Remark</label>
                                    <input type="text" readonly="" class="form-control" maxlength="50" id="remark" name="remark" >
                                </div>
                            </div>
                            
                        </div>

                        <table  class="display" id="TableBookSummary">
                            <thead>
                                <tr class="datatable-header">
                                    <th>Date</th>
                                    <th>type</th>
                                    <th>Description</th>
                                    <th>Date tour</th>
                                    <th>Amount</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table> 
                    </div>
                </div>
    </div>

</div>


<div class="modal fade" id="AddAirticket" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Add Booking</h4>
            </div>
            <div class="modal-body">

                <form method="post" id="FormBookAdd" class="" role="form">
                    <div class="form-group">
                        <label>Agent</label>
                        <input type="text" class="form-control"  placeholder="Agent">
                    </div>
                    <div class="form-group">
                        <label>Leader</label>
                        <input type="text" class="form-control"  placeholder="Leader">
                    </div>
                    <div class="form-group">
                        <label>Tel</label>
                        <input type="text" class="form-control"  placeholder="Tel">
                    </div>

                </form>

            </div>
            <div class="modal-footer">
                <div class="form-group">
                    <div class="col-sm-12">
                        <button type="button" onclick="save()" class="btn btn-primary btn-sm">Save</button>
                        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>



<div class="modal fade" id="DetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Reference Detail</h4>
            </div>
            <div class="modal-body">
                <form  method="post" id="MasterDetail" class="form-horizontal"  role="form">
                    <div class="row">
                        <div class="col-md-6">

                            <div class="form-group">
                                <label for="RefNo" class="col-sm-4 control-label" >Ref. No</label>
                                <div class="col-sm-6"> 
                                    <input type="text" class="form-control" id="RefNo" name="RefNo" readonly>

                                </div>
                            </div>
                        </div>    
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="Status" class="col-sm-4 control-label" >Status</label>
                                <div class="col-sm-4">  
                                    <input type="text" class="form-control" id="Status" name="Status" >
                                </div>
                            </div>   
                        </div>    
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="CreateD" class="col-sm-4 control-label" >Create Date</label>
                                <div class="col-sm-6"> 
                                    <input type="text" class="form-control" id="CreateD" name="CreateD" >
                                </div>
                            </div>
                        </div>    
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="CreateB" class="col-sm-4 control-label" >Create By</label>
                                <div class="col-sm-4">  
                                    <input type="text" class="form-control" id="CreateB" name="CreateB" >
                                </div>
                            </div>   
                        </div>    
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="RevisedD" class="col-sm-4 control-label" >Revised Date</label>
                                <div class="col-sm-6"> 
                                    <input type="text" class="form-control" id="RevisedD" name="RevisedD" >
                                </div>
                            </div>
                        </div>    
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="RevisedB" class="col-sm-4 control-label" >Revised By</label>
                                <div class="col-sm-4">  
                                    <input type="text" class="form-control" id="CreateB" name="CreateB" >
                                </div>
                            </div>   
                        </div>    
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="AgentC" class="col-sm-2 control-label" >Agent</label>
                                <div class="col-sm-2"> 
                                    <input type="text" class="form-control" id="AgentC" name="AgentC" >
                                </div>
                                <div class="col-sm-3">  
                                    <input type="text" class="form-control" id="AgentN" name="AgentN" >
                                </div>
                                <label for="Tel" class="col-sm-1 " >Tel</label>
                                <div class="col-sm-2"> 
                                    <input type="text" class="form-control" id="TelA" name="TelA" >
                                </div>
                            </div>
                        </div> 
                        <!--
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="AgentN" class="col-sm-3 control-label" >Agent Name</label>
                                <div class="col-sm-8">  
                                    <input type="text" class="form-control" id="AgentN" name="AgentN" >
                                </div>
                            </div>   
                        </div>  
                        -->
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="FamilyC" class="col-sm-2 control-label" >Falimy Leader</label>
                                <div class="col-sm-2"> 
                                    <input type="text" class="form-control" id="AgentC" name="AgentC" >
                                </div>
                                <div class="col-sm-3">  
                                    <input type="text" class="form-control" id="AgentN" name="AgentN" >
                                </div>
                                <label for="Tel" class="col-sm-1 " >Tel</label>
                                <div class="col-sm-2"> 
                                    <input type="text" class="form-control" id="TelC" name="TelC" >
                                </div>
                            </div>
                        </div>   
                        <!--
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="FamilyN" class="col-sm-3 control-label" >FL Name</label>
                                <div class="col-sm-8">  
                                    <input type="text" class="form-control" id="AgentN" name="AgentN" >
                                </div>
                            </div>   
                        </div>    
                        -->
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="AgentN" class="col-sm-4 control-label" >Passenger</label>
                                <div class="col-sm-8">  
                                    <table class="display" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th> Adult</th>
                                                <th> Child</th>
                                                <th> infant</th>
                                                <th> Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><center> 1 </center></td>
                                        <td><center> 0 </center></td>
                                        <td><center> 0 </center></td>
                                        <td><center> 1 </center></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>   

                        </div>    
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="FamilyN" class="col-sm-3 control-label" >Package</label>
                                <div class="col-sm-8">  
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"> 
                                        </label>
                                    </div>
                                </div>
                            </div>   
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="Detail" class="col-sm-2 control-label" >Detail</label>
                            <div class="col-md-8" style="padding-left: 25px">
                                <ul id="myTab" class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active"><a href="#Air" id="home-tab" role="tab" data-toggle="tab" aria-controls="Air" aria-expanded="true">Air Ticket</a></li>
                                    <li role="presentation" class=""><a href="#Hotela" role="tab" id="Hotel-tab" data-toggle="tab" aria-controls="Hotel" aria-expanded="false">Hotel</a></li>
                                    <li role="presentation" class=""><a href="#Group" role="tab" id="Group-tab" data-toggle="tab" aria-controls="Group" aria-expanded="false">Group</a></li>
                                    <li role="presentation" class=""><a href="#tours" role="tab" id="tours-tab" data-toggle="tab" aria-controls="tours" aria-expanded="false">Day tours</a></li>
                                    <li role="presentation" class=""><a href="#Others" role="tab" id="Others-tab" data-toggle="tab" aria-controls="Others" aria-expanded="false">Others</a></li>
                                    <li role="presentation" class=""><a href="#Passengera" role="tab" id="Passenger-tab" data-toggle="tab" aria-controls="Passenger" aria-expanded="false">Passenger</a></li>
                                </ul>

                                <div id="myTabContent" class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade active in" id="Air" aria-labelledby="Air-tab">

                                        <div class="row" style="padding-top: 10px">
                                            <div class="form-group">
                                                <label for="PNR" class="col-sm-2 control-label" >PNR</label>
                                                <div class="col-sm-3">  
                                                    <input type="text" class="form-control" id="PNR" name="PNR" >
                                                </div>
                                            </div>   
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
                                                <label for="Ticket" class="col-sm-2 control-label" >Direction</label>
                                                <div class="col-sm-8">  
                                                    <input type="text" class="form-control" id="Ticket" name="Ticket" >
                                                </div>
                                            </div>   
                                        </div>

                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="Hotela" aria-labelledby="Hotel-tab">
                                        <div class="row" style="padding-top: 10px">
                                            <div class="col-md-6" >
                                                <div class="form-group">
                                                    <label for="Hotel" class="col-sm-4 control-label" >Hotel</label>
                                                    <div class="col-sm-8">  
                                                        <input type="text" class="form-control" id="Hotel" name="Hotel" >
                                                    </div>
                                                </div>   
                                            </div>
                                        </div>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="Group" aria-labelledby="Group-tab">
                                        <div class="row" style="padding-top: 10px">
                                            <div class="col-md-6" >
                                                <div class="form-group">
                                                    <label for="Group" class="col-sm-4 control-label" >Group</label>
                                                    <div class="col-sm-8">  
                                                        <input type="text" class="form-control" id="Group" name="Group" >
                                                    </div>
                                                </div>   
                                            </div>
                                        </div>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="tours" aria-labelledby="tours-tab">
                                        <div class="row" style="padding-top: 10px">
                                            <div class="col-md-6" >
                                                <div class="form-group">
                                                    <label for="tours" class="col-sm-4 control-label" >tours</label>
                                                    <div class="col-sm-8">  
                                                        <input type="text" class="form-control" id="tours" name="tours" >
                                                    </div>
                                                </div>   
                                            </div>
                                        </div>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="Others" aria-labelledby="Others-tab">
                                        <div class="row" style="padding-top: 10px">
                                            <div class="col-md-6" >
                                                <div class="form-group">
                                                    <label for="Others" class="col-sm-4 control-label" >Others</label>
                                                    <div class="col-sm-8">  
                                                        <input type="text" class="form-control" id="Others" name="Others" >
                                                    </div>
                                                </div>   
                                            </div>
                                        </div>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="Passengera" aria-labelledby="Passenger-tab">
                                        <div class="row" style="padding-top: 10px">
                                            <div class="col-md-6" >
                                                <div class="form-group">
                                                    <label for="Passenger" class="col-sm-4 control-label" >Passenger</label>
                                                    <div class="col-sm-8">  
                                                        <input type="text" class="form-control" id="Passenger" name="Passenger" >
                                                    </div>
                                                </div>   
                                            </div>
                                        </div>
                                    </div>
                                </div>                           
                            </div>
                        </div>   


                    </div>

                    <input type="hidden" id="CityID" name="CityID" >
                    <input type="hidden" id="actionIUP" name="action">

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="save()" class="btn btn-primary">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        var table = $('#BookList').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "aaSorting": [[ 0, "desc" ]]
        });
 
//        $("#btnNew").appendTo("#example_filter label").show();

        $('#BookList tbody').on('click', 'tr', function() {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#BookList tbody tr.row_selected').attr("id"));
            }
        });
        $('.dataTables_length label').remove();

        $('.date').datetimepicker();
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        
    });

    $('#myTab a').click(function(e) {
        e.preventDefault()
        $(this).tab('show')
    });
    
    function setformat() {
                $('#TableBookSummary tr td.moneyformat').each(function() {
                    var innerHTML = $(this).html();

                    $(this).html(numberWithCommas($(this).html()));
                });
            }
            
    function getSummaryTel(tel,remark,email){
        document.getElementById('telNo').value=tel;
        document.getElementById('remark').value=remark;
        document.getElementById('email').value=email;
    }
    
    $("#AirTicket").load("WebContent/Book/AirTicket.jsp");
    $("#Hotel").load("WebContent/Book/Hotel.jsp");
    $("#Passenger").load("WebContent/Book/Passenger.jsp");
    $("#History").load("WebContent/Book/History.jsp");
    $("#Billable").load("WebContent/Book/Billable.jsp");
    
    function bankTrasferField(){
        var payBy = document.getElementById("payBy").value;
        console.log(payBy);
        if(payBy === '4'){
            $("#bankTransferField").removeClass("hidden");
        } else {
            $("#bankTransferField").addClass("hidden");
        }
    }

</script>

