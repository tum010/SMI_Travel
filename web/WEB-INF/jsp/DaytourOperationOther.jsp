<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<script type="text/javascript" src="js/DaytourOther.js"></script> 

<c:set var="listOtherBooking" value="${requestScope['listOtherBooking']}" />
<c:set var="listPassenger" value="${requestScope['listPassenger']}" />
<c:set var="RefNo" value="${requestScope['RefNo']}" />
<c:set var="ListBookingAll" value="${requestScope['ListBookingAll']}" />
<c:set var="ListBookingAllView" value="${requestScope['ListBookingAllView']}" />
<c:set var="isDuplicate" value="${requestScope['Duplicate']}" />

<input type="hidden" value="${param.referenceNo}" id="getUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<input type="hidden" value="${param.tourID}" id="tourID">
<input type="hidden" value="${isDuplicate}" id="Duplicate">

<section class="content-header" >
    <h1>
        Operation - Day Tours Operation Others 
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Operation</a></li>          
        <li class="active"><a href="#">Day Tours Operation Other</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <form action="DaytourOperationOther.smi" method="post" id="DaytourOtherForm" role="form">
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-1" style="border-right:  solid 1px #01C632;padding-top: 10px; width: 160px; padding-left: 0px;">
            <div ng-include="'WebContent/Book/OperationOther.html'"></div>
            <input hidden="" value="${booking_size[0]}" id="input-airticket_size">
            <input hidden="" value="${booking_size[1]}" id="input-hotel_size">
            <input hidden="" value="${booking_size[2]}" id="input-other_size">
            <input hidden="" value="${booking_size[3]}" id="input-land_size">
            <input hidden="" value="${booking_size[4]}" id="input-passenger_size">
            <input hidden="" value="${booking_size[5]}" id="input-billable_size">
        </div>
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b></b></h4>
                </div>
                <div class="col-sm-6 text-right">
                    <a id="PackageTour" class="btn btn-primary" data-toggle="modal" data-target="#BookingModal">Booking</a>
                </div>
            </div>
            <hr/>
            <div id="textAlertDivNotPrint"  style="display:none;" class="alert alert-danger">
                <button type="button" class="close" aria-label="Close" onclick="hideTextAlert()"><span aria-hidden="true">&times;</span></button>
                <strong>Please Select Report Type !</strong> 
            </div>
            <div id="textAlertDivSelect"  style="display:none;" class="alert alert-danger">
                <button type="button" class="close" aria-label="Close" onclick="hideTextAlert()"><span aria-hidden="true">&times;</span></button>
                <strong>Please Select Product !</strong> 
            </div>
            <div class="col-xs-12 form-group">
                <div class="col-xs-1 text-right">
                    <label class="control-label text-right">Ref&nbsp;No</lable>
                </div>

                <div class="col-xs-3">
                    <input id="InputRefNo" name="InputRefNo"  type="text"  class="form-control"  value="<c:out value="${RefNo}" />">
                </div>
                <div class="col-xs-3">
                    <input type="hidden" name="action" id="action">
                    <button type="button" id="acs" onclick="searchAction()" class="btn btn-primary"><span class="fa fa-search"></span>Search</button>
                </div>
            </div>
    
            <!--Information -->
            <input type="hidden" id="countOther" name="countOther" value="${listOtherBooking.size()}"/>
            <input type="hidden" id="countPassenger" name="countPassenger" value="${listPassenger.size()}"/>
            <div class="col-xs-12 form-group">
                <!--Ref No Book  -->
                <div class="panel panel-default"> 
                    <div class="panel-heading">
                        <h5 class="panel-title">Information</h5>
                    </div>
                    <div class="panel-body">
                        <table class="display" id="RefBookTable" name="RefBookTable">
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width:1%;"></th>
                                    <th style="width:10%;">Code</th>
                                    <th style="width:40%;">Name</th>
                                    <th style="width:10%;">Date</th>
                                    <th style="width:5%;">Time</th>
                                    <th style="width:20%;">Remark</th>
                                    <th style="width:10%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${listOtherBooking != null}"> 
                                    
                                    <c:forEach var="table" items="${listOtherBooking}" varStatus="status">
                                    <c:set var="counter" value="${status.count}"></c:set>               
                                    <tr>
                                        <td>
                                            <center>
                                                <input type="checkbox" id="otherCheck${status.count}" name="otherCheck${status.count}" checked=""/>
                                                <input type="hidden" id="otherId${status.count}" name="otherId${status.count}" value="${table.id}"/>
                                            </center>
                                        </td>
                                        <td><center><c:out value="${table.product.code}" /></center></td>
                                        <td><center><c:out value="${table.product.name}" /></center></td>
                                        <td><center><c:out value="${table.otherDate}" /></center></td>
                                        <td><center><c:out value="${table.otherTime}" /></center></td>
                                        <td><center><c:out value="${table.remark}" /></center></td>
                                        <td class="text-center">
                                            <span id="RefBookTableButtonEdit" name="RefBookTableButtonEdit" class="glyphicon glyphicon-edit editicon" onclick="window.open('/SMITravel/OtherDetail.smi?referenceNo=${RefNo}&itemid=${table.id}&action=edit');"></span>
                                            <a  href="#" >
                                                <span  class="glyphicon glyphicon-list-alt" id="SpanEdit${status.count}" onclick="selectListOther(${status.count},${table.id})"  ></span>
                                            </a>
                                                <input type="text" class="hidden" id="countClick${status.count}" name="countClick${status.count}" value="0">
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </c:if>
                                    <input type="hidden" id="countListOther" name="countListOther" value="${counter}" >
                                    <input type="hidden" id="rowTable" name="rowTable" value=""/>
                            </tbody>
                        </table>
                    </div>
                    <!--View Detail Tour Pop up -->                   
                    <c:forEach var="table2" items="${listOtherBooking}" varStatus="status">
                        <c:set var="otherBookingId" value="${table2.id}"/>
                        <div class="hidden" id="TableOther${status.count}" name="TableOther${status.count}" >
                        <label class="control-label" style="margin-top: 10px;margin-left: 20px;">Price (<c:out value="${table2.product.code}" />)</label>
                        <table class="display" style="width:97%;margin-top: 10px;">
                            <thead class="datatable-header">
                                <tr>
                                    <th colspan="3" align="center">Adult</th>
                                    <th colspan="3" align="center">Child</th>
                                    <th colspan="3" align="center">Infant</th>
                                    <th rowspan="2" align="center">Cur</th>
                                    <th rowspan="2" align="center">Amount</th>
                                </tr>
                                <tr>
                                    <th colspan="1" align="center">Cost</th>
                                    <th colspan="1" align="center">Qty</th>
                                    <th colspan="1" align="center">Price</th>
                                    <th colspan="1" align="center">Cost</th>
                                    <th colspan="1" align="center">Qty</th>
                                    <th colspan="1" align="center">Price</th>
                                    <th colspan="1" align="center">Cost</th>
                                    <th colspan="1" align="center">Qty</th>
                                    <th colspan="1" align="center">Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="table" items="${listOtherBooking}" varStatus="status">
                                <c:if test="${table.id == otherBookingId}">   
                                <tr>
                                    <td class="money text-right" ><c:out value="${table.adCost}" /></td>
                                    <td><center><c:out value="${table.adQty}" /></center></td>
                                    <td class="money text-right"><c:out value="${table.adPrice}" /></td>
                                    <td class="money text-right"><c:out value="${table.chCost}" /></td>
                                    <td><center><c:out value="${table.chQty}" /></center></td>
                                    <td class="money text-right"><c:out value="${table.chPrice}" /></td>
                                    <td class="money text-right"><c:out value="${table.inCost}" /></td>
                                    <td><center><c:out value="${table.inQty}" /></center></td>
                                    <td class="money text-right"><c:out value="${table.inPrice}" /></td>
                                    <td><center><c:out value="${table.curCost}" /></center></td>
                                    <td class="money text-right"><c:out value="${(table.adQty*table.adPrice)+(table.chQty*table.chPrice)+(table.inQty*table.inPrice)}" /></td>
                                </tr>
                                </c:if> 
                                </c:forEach>
                            </tbody>
                        </table>                                              
                    </div> <!--collapse -->
                    </c:forEach>
                    <div class="panel-body">
                        <label class="control-label" style="margin-top: 10px;margin-left: 10px;">Passenger</label>
                        <table class="display" id="PassengerTable" name="PassengerTable">                           
                            <thead class="datatable-header">
                                <tr>
                                    <th style="width: 1%"></th>
                                    <th style="width: 3%">No</th>
                                    <th style="width: 10%">Code</th>
                                    <th style="width: 30%">Name</th>
                                    <th style="width: 5%">Age</th>
                                    <th style="width: 10%">Tel</th>
                                    <th style="width: 10%">Nationality</th>
                                    <th style="width: 5%">Reader</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="table1" items="${listPassenger}" varStatus="status">
                                <tr>
                                    <c:set var="leader" value="Yes"/>
                                    <c:set var="leaderCheck" value="checked"/>
                                    <c:if test="${table1.isLeader == 0}">
                                        <c:set var="leader" value="No"/>
                                        <c:set var="leaderCheck" value=""/>
                                    </c:if>
                                    <td>
                                        <center>
                                            <input type="checkbox" id="passengerCheck${status.count}" name="passengerCheck${status.count}" ${leaderCheck}/>
                                            <input type="hidden" id="passengerId${status.count}" name="passengerId${status.count}" value="${table1.customer.id}"/>
                                        </center>
                                    </td>
                                    <td><center><c:out value="${status.count}" /></center></td>
                                    <td><center><c:out value="${table1.customer.code}" /></center></td>
                                    <td><c:out value="${table1.customer.MInitialname.name}" />&nbsp;<c:out value="${table1.customer.firstName}" />&nbsp;<c:out value="${table1.customer.lastName}" /></td>
                                    <td>
                                        <center>
                                            <c:if test="${table1.customer.birthDate != '' && table1.customer.birthDate != null}">
                                                <c:set var="today" value="<%=new Date()%>"/>
                                                <fmt:formatDate var="yearNow" value="${today}" pattern="yyyy"/>
                                                <c:set var="birthDate" value="${table1.customer.birthDate}"/>
                                                <fmt:formatDate var="yearDate" value="${birthDate}" pattern="yyyy"/>                                            
                                                ${yearNow-yearDate}
                                            </c:if>
                                        </center>
                                    </td>
                                    <td><center><c:out value="${table1.customer.tel}" /></center></td>
                                    <td><center><c:out value="${table1.customer.nationality}" /></center></td>
                                    <td>
                                        <center>                                           
                                            <c:out value="${leader}" />
                                        </center>
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div> <!--End Ref No Book  -->
                                              
                <div class="col-xs-12 form-group"> <!--Select Print Report -->
                    <div class="col-xs-2 text-right">
                        <label class="control-label text-right" align="left">Report &nbsp;Type</lable>
                    </div>
                 
                    <div class="col-xs-3">
                         <select name="voucher" id="voucher"  class="form-control">
                            <option value="">--Select Type--</option>
                            <option value="OtherVouncher">Other Voucher</option>
                            <option value="OtherVoucherEmail">Other Voucher Email</option>
                        </select>
                    </div>
                    <div class="col-xs-3">
                        <a id="ButtonPrint" name="ButtonPrint"  class="btn btn-default" onclick="checkDuplicate()">
                             <i class="fa fa-print"></i> Print
                         </a>
                    </div>
                </div><!-- End Select Print Report -->
 
            </div><!--End Information -->
            
        </div>
    </div>
    </form>
</div>

<!--Booking Modal-->
<div class="modal fade" id="BookingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog"  style="width: 60%">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Booking List</h4>
            </div>
            <div class="modal-body">
                <div style="text-align: right"><i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i>Search : <input type="text" style="width: 175px" id="filtercus" name="filtercus"/> </div> 
                <table class="display" id="PacketTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width:8%;">Ref No</th>
                            <th style="width:11%;">Other Date</th>
                            <th style="width:20%;">Leader</th>
                            <th style="width:24%;">Product Name</th>
                            <th style="width:10%;">Status</th>
                            <th style="width:7%;">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="table1" items="${ListBookingAllView}" varStatus="dayStatus">
                            <tr>
<!--                                <td class="pack-date"><div style="width: 31px" >${table1.refno}</div></td>
                                <td class="text-center"><div style="width: 65px" >${table1.otherdate}</div></td>
                                <td class="text-left"><div style="width: 140px" > ${table1.leader}</div></td>
                                <td class="text-left"><div style="width: 160px" >${table1.product}</div></td>
                                <td class="text-center"><div style="width: 10px" >${table1.status}</div></td>
                                <td class="text-center">
                                    <a href="DaytourOperationOther.smi?InputRefNo=${table1.refno}&action=search">
                                        <span class="glyphicon glyphicon-check"></span>
                                    </a>
                                </td>-->
                                <td class="pack-date text-center">
                                    <c:set var="refno1" value="${fn:substring(table1.refno, 0, 2)}" />
                                    <c:set var="refno2" value="${fn:substring(table1.refno, 2,7)}" />
                                    <c:choose>
                                        <c:when test="${not empty table1.refno}">
                                            ${refno1}-${refno2}
                                        </c:when>
                                    </c:choose>                                   
                                </td>
                                <td class="text-center">${table1.otherdate}</td>
                                <td class="text-left"> ${table1.leader}</td>
                                <td class="text-left">${table1.product}</td>
                                <td class="text-center">${table1.status}</td>
                                <td class="text-center">
                                    <a href="DaytourOperationOther.smi?InputRefNo=${table1.refno}&action=search">
                                        <span class="glyphicon glyphicon-check"></span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="ListPacketModalOK" name="ListPacketModalOK" type="button"  class="btn btn-success" data-dismiss="modal">OK</button>
                <button id="ListPacketModalClose" name="ListPacketModalClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->

<!--Print Modal-->
<div class="modal fade" id="PrintModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Print Other Voucher</h4>
            </div>
            <div class="modal-body">
                <!--<input type="" id="ConfirmPrint"  name="ConfirmPrint" value="">-->
                Coupon is duplicate.Are you sure to print all coupon?
            </div>
            <div class="modal-footer">
                <button id="PrintOK" name="PrintOK" type="button"  class="btn btn-success" data-dismiss="modal" onclick="printOtherVoucher('CANCEL');">YES</button><!--Print All -->
                <button id="PrintCancel" name="PrintCancel" type="button" class="btn btn-default" data-dismiss="modal" onclick="printOtherVoucher('OK');">NO</button><!--Print No Duplicate -->
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal-dialog -->

<script> driver = []</script>
<c:forEach var="dr" items="${driverList}">
    <script>
        driver.push({
            id: "${dr.id}",
            name: "${dr.name}",
            username: "${dr.username}",
            car: "${dr.car}",
        });
    </script>
</c:forEach>

<c:if test="${! empty requestScope['resultStatus']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['resultStatus']}" />');
    </script>
    <META HTTP-EQUIV="Refresh" CONTENT="0;URL=${requestScope['redirectUrl']}">
</c:if>
    
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () { 
        $(".money").mask('000,000,000,000', {reverse: true});
    });
</script>   