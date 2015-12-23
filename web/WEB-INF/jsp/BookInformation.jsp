<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/BookInformation.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="bookingList" value="${requestScope['booking_list']}"/>
<input type="hidden" id="searchType" name="searchType" value="${requestScope['search_type']}"/>
<section class="content-header" >
    <h1>
        Booking - Information
    </h1>
    <ol class="breadcrumb">
        <li><a href="BookInformation.smi?action=new"><i class="fa fa-book"></i> Booking - Information</a></li>          
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;">
    <form action="BookInformation.smi" method="post" id="BookInformationSearch" role="form">
        <div class="row" >
            <div class="col-md-12 ">
                <div class="col-xs-2"></div>
                <div class="col-xs-2" >
                    <label>Ref No.</label>
                    <input class="form-control" id="bookRefNo" name="bookRefNo" value="${requestScope['bookRefNo']}"/>
                </div>
                <div class="col-xs-2">
                    <label>Leader</label>
                    <input class="form-control" id="bookLeader" name="bookLeader" value="${requestScope['bookLeader']}"/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Booking Date</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="bookDate" name="bookDate" 
                                   data-date-format="YYYY-MM-DD" value="${requestScope['bookDate']}" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
                <div class="col-xs-2 form-group">
                    <label>Type<font style="color: red">*</font></label>
                    <select class="form-control" id="bookType" name="bookType" onchange="searchFillter()">
                        <option value=""></option>
                        <option value="1">Air</option>
                        <option value="2">Hotel</option>
                        <option value="3">Package Tour</option>
                        <option value="4">Day Tours</option>
                        <option value="5">Other</option>
                        <option value="6">Land</option>
                    </select>
                </div>
            </div>
            <div class="col-md-12 hidden" id="searchAir">
                <div class="col-xs-2"></div>
                <div class="col-xs-2" >
                    <label>Pnr</label>
                    <input class="form-control" id="airPnr" name="airPnr" value="${requestScope['airPnr']}"/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Dept Date</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="airDeptDate" name="airDeptDate" 
                                   data-date-format="YYYY-MM-DD" value="${requestScope['airDeptDate']}" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
                <div class="col-xs-2" >
                    <label>Flight</label>
                    <input class="form-control" id="airFlight" name="airFlight" value="${requestScope['airFlight']}"/>
                </div>
            </div>
            <div class="col-md-12 hidden" id="searchHotel">
                <div class="col-xs-2"></div>
                <div class="col-xs-2" >
                    <label>Hotel</label>
                    <input class="form-control" id="hotelName" name="hotelName" value="${requestScope['hotelName']}"/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Check In</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="hotelCheckIn" name="hotelCheckIn" 
                                   data-date-format="YYYY-MM-DD" value="${requestScope['hotelCheckIn']}" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Check Out</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="hotelCheckOut" name="hotelCheckOut" 
                                   data-date-format="YYYY-MM-DD" value="${requestScope['hotelCheckOut']}" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
            </div>
            <div class="col-md-12 hidden" id="searchPackageTour">
                <div class="col-xs-2"></div>
                <div class="col-xs-2" >
                    <label>Package</label>
                    <input class="form-control" id="packageName" name="packageName" value="${requestScope['packageName']}"/>
                </div>
                <div class="col-xs-2" >
                    <label>Agent</label>
                    <input class="form-control" id="packageAgent" name="packageAgent" value="${requestScope['packageAgent']}"/>
                </div>
            </div>
            <div class="col-md-12 hidden" id="searchDayTours">
                <div class="col-xs-2"></div>
                <div class="col-xs-2" >
                    <label>Tour Code</label>
                    <input class="form-control" id="tourCode" name="tourCode" value="${requestScope['tourCode']}"/>
                </div>
                <div class="col-xs-2" >
                    <label>Tour Name</label>
                    <input class="form-control" id="tourName" name="tourName" value="${requestScope['tourName']}"/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Tour Date</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="tourDate" name="tourDate" 
                                   data-date-format="YYYY-MM-DD" value="${requestScope['tourDate']}" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
                <div class="col-xs-2" >
                    <label>Pick up</label>
                    <input class="form-control" id="tourPickUp" name="tourPickUp" value="${requestScope['tourPickUp']}"/>
                </div>
                <div class="col-xs-2" >
                    <label>Agent</label>
                    <input class="form-control" id="tourAgent" name="tourAgent" value="${requestScope['tourAgent']}"/>
                </div>
            </div>
            <div class="col-md-12 hidden" id="searchOther">
                <div class="col-xs-2"></div>
                <div class="col-xs-2" >
                    <label>Other Code</label>
                    <input class="form-control" id="otherCode" name="otherCode" value="${requestScope['otherCode']}"/>
                </div>
                <div class="col-xs-2" >
                    <label>Other Name</label>
                    <input class="form-control" id="otherName" name="otherName" value="${requestScope['otherName']}"/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Other Date</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="otherDate" name="otherDate" 
                                   data-date-format="YYYY-MM-DD" value="${requestScope['otherDate']}" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
                <div class="col-xs-2" >
                    <label>Agent</label>
                    <input class="form-control" id="otherAgent" name="otherAgent" value="${requestScope['otherAgent']}"/>
                </div>
            </div>
            <div class="col-md-12 hidden" id="searchLand">
                <div class="col-xs-2"></div>
                <div class="col-xs-2" >
                    <label>OK By</label>
                    <input class="form-control" id="landOkBy" name="landOkBy" value="${requestScope['landOkBy']}"/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Category</label>
                        <input class="form-control" id="landCategory" name="landCategory" value="${requestScope['landCategory']}"/>
                    </div>
                </div>  
                <div class="col-xs-2" >
                    <label>Agent</label>
                    <input class="form-control" id="landAgent" name="landAgent" value="${requestScope['landAgent']}"/>
                </div>
            </div>
            <div class="col-xs-1 text-right" style="width: 1280px">
                <button type="submit" id="btnSearch" class="btn btn-primary" ><span class="fa fa-search"></span> Search</button>           
                <input type="hidden" name="action" id="action" value="search">
                <a id="btnNew"  href="BookDetail.smi?&action=new" class="btn btn-success" ><span class="fa fa-plus-circle"></span> New</a>
            </div> 
        </div>
                <div class="col-md-12 "><br></div>
        <div class="row">
            <div class="col-md-12 ">
                <c:set var="colorTrHead" value="#C5F1C2"/>
                <c:set var="colorTr" value="#D6F1D4"/>
                <c:choose>                  
                    <c:when test="${requestScope['search_type'] == '1'}">
                        <table id="BookList" class="displayblackcolor paginated" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header" >
                                <th style="width: 1%">No</th>
                                <th style="width: 5%">Ref No</th>
                                <th style="width: 6%">Ref Date</th>
                                <th style="width: 15%">Agent</th>
                                <th style="width: 10%">Leader</th>
                                <th style="width: 2%">Flight</th>
                                <th style="width: 3%">Pax</th>
                                <th style="width: 3%">Pnr</th>
                                <th style="width: 3%">Dept</th>
                                <th style="width: 3%">Arrv</th>
                                <th style="width: 7%">Depart Date</th>
                                <th style="width: 3%">Action</th>   
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="air" items="${bookingList}" varStatus="i">
                                <c:set var="color" value=""/>
                                <c:if test="${i.count%2 == 0}">
                                    <c:set var="color" value="#F2F2F2"/>
                                </c:if>
                            <tr id="trTable${i.count}" bgcolor="">
                                <td align="center">${i.count}</td>
                                <td align="center">${air.refno}</td>
                                <td align="center">${air.refdate}</td>
                                <td align="left">${air.agent}</td>
                                <td align="left">${air.leader}</td>
                                <td align="center">${air.flight}</td>
                                <td align="center" >${air.pax}</td>
                                <td align="center">${air.pnr}</td>
                                <td align="center">${air.dept}</td>
                                <td align="center">${air.arrv}</td>
                                <td align="center">${air.departdate}</td>
                                <td align="center" rowspan="2">
                                    <a href="#" onclick="" data-toggle="modal" data-target="">
                                        <span id="editSpan" class="glyphicon glyphicon-th-list" onclick="viewBooking('${requestScope['search_type']}','${air.refno}','')"></span>
                                    </a>
                                </td>
                            </tr>
                            <tr style="height: 1.8em;" bgcolor="${colorTr}">
                                <td colspan="1" align="right" bgcolor="${colorTrHead}"><b>Invoice</b></td>
                                <td colspan="4">${air.invoice}</td>
                                <td colspan="1" align="right" bgcolor="${colorTrHead}"><b>Receipt</b></td>
                                <td colspan="5">${air.receipt}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table> 
                    </c:when>
                    <c:when test="${requestScope['search_type'] == '2'}">
                    <table id="BookList" class="displayblackcolor paginated" cellspacing="0" style="table-layout: fixed">
                        <thead>
                            <tr class="datatable-header" >
                                <th style="width: 2%">No</th>
                                <th style="width: 4%">Ref No</th>
                                <th style="width: 5%">Ref Date</th>
                                <th style="width: 12%">Agent</th>
                                <th style="width: 12%">Leader</th>
                                <th style="width: 8%">Hotel</th>
                                <th style="width: 5%">Check In</th>
                                <th style="width: 5%">Check Out</th>
                                <th style="width: 5%">Total Cost</th>
                                <th style="width: 2%">Cur</th>
                                <th style="width: 5%">Total Price</th>
                                <th style="width: 2%">Cur</th>
                                <th style="width: 3%">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="hotel" items="${bookingList}" varStatus="i">
                                <c:set var="color" value=""/>
                                <c:if test="${i.count%2 == 0}">
                                    <c:set var="color" value="#F2F2F2"/>
                                </c:if>
                            <tr bgcolor="">
                                <td align="center">${i.count}</td>
                                <td align="center">${hotel.refno}</td>
                                <td align="center">${hotel.refdate}</td>
                                <td align="left">${hotel.agent}</td>
                                <td align="left">${hotel.leader}</td>
                                <td align="left">${hotel.hotel}</td>
                                <td align="center">${hotel.checkin}</td>
                                <td align="center">${hotel.checkout}</td>
                                <td align="right" id="totalCost${i.count}" class="money">${hotel.totalcost}</td>
                                <td align="center">${hotel.curcost}</td>
                                <td align="right" id="totalPrice${i.count}" class="money">${hotel.totalprice}</td>
                                <td align="center">${hotel.curamount}</td>
                                <td align="center" rowspan="2">
                                    <a href="#" onclick="" data-toggle="modal" data-target="">
                                        <span id="editSpan" class="glyphicon glyphicon-th-list" onclick="viewBooking('${requestScope['search_type']}','${hotel.refno}','${hotel.id}')"></span>
                                    </a>
                                </td>
                            </tr>
                            <tr style="height: 1.8em;" bgcolor="${colorTr}">
                                <td colspan="2" align="right" bgcolor="${colorTrHead}"><b>Invoice</b></td>
                                <td colspan="3">${hotel.invoice}</td>
                                <td colspan="1" align="right" bgcolor="${colorTrHead}"><b>Receipt</b></td>
                                <td colspan="6">${hotel.receipt}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table> 
                    </c:when>
                    <c:when test="${requestScope['search_type'] == '3'}">
                    <table id="BookList" class="displayblackcolor paginated" cellspacing="0" style="table-layout: fixed">
                        <thead>
                            <tr class="datatable-header" >
                                <th style="width: 2%">No</th>
                                <th style="width: 4%">Ref No</th>
                                <th style="width: 5%">Ref Date</th>
                                <th style="width: 18%">Agent</th>
                                <th style="width: 18%">Leader</th>
                                <th style="width: 10%">Code</th>
                                <th style="width: 10%">Name</th>
                                <th style="width: 3%">Action</th>   
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pack" items="${bookingList}" varStatus="i">
                            <c:set var="color" value=""/>    
                            <c:if test="${i.count%2 == 0}">
                                <c:set var="color" value="#F2F2F2"/>
                            </c:if>    
                            <tr bgcolor="">
                                <td align="center">${i.count}</td>
                                <td align="center">${pack.refno}</td>
                                <td align="center">${pack.refdate}</td>
                                <td align="left">${pack.agent}</td>
                                <td align="left">${pack.leader}</td>
                                <td align="left">${pack.code}</td>
                                <td align="left">${pack.name}</td>
                                <td align="center" rowspan="2">
                                    <a href="#" onclick="" data-toggle="modal" data-target="">
                                        <span id="editSpan1" class="glyphicon glyphicon-th-list" onclick="viewBooking('${requestScope['search_type']}','${pack.refno}','')"></span>
                                    </a>
                                </td>
                            </tr>
                            <tr style="height: 1.8em;" bgcolor="${colorTr}">
                                <td colspan="2" align="right" bgcolor="${colorTrHead}"><b>Invoice</b></td>
                                <td colspan="2">${pack.invoice}</td>
                                <td colspan="1" align="right" bgcolor="${colorTrHead}"><b>Receipt</b></td>
                                <td colspan="2">${pack.receipt}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table> 
                    </c:when>
                    <c:when test="${requestScope['search_type'] == '4'}">
                    <table id="BookList" class="displayblackcolor paginated" cellspacing="0" style="table-layout: fixed">
                        <thead>
                            <tr class="datatable-header" >
                                <th style="width: 2%">No</th>
                                <th style="width: 5%">Ref No</th>
                                <th style="width: 6%">Ref Date</th>
                                <th style="width: 11%">Agent</th>
                                <th style="width: 11%">Leader</th>
                                <th style="width: 3%">Pax</th>
                                <th style="width: 3%">Tour Code</th>
                                <th style="width: 7%">Tour Name</th>
                                <th style="width: 6%">Tour Date</th>
                                <th style="width: 9%">Pickup</th>
                                <th style="width: 3%">Time</th>
                                <th style="width: 2%">Ad</th>
                                <th style="width: 2%">Ch</th>
                                <th style="width: 2%">Inf</th>
                                <th style="width: 5%">Remark</th>
                                <th style="width: 4%">Action</th>   
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="daytour" items="${bookingList}" varStatus="i">
                            <c:set var="color" value=""/>    
                            <c:if test="${i.count%2 == 0}">
                                <c:set var="color" value="#F2F2F2"/>
                            </c:if>    
                            <tr bgcolor="">
                                <td align="center">${i.count}</td>
                                <td align="center">${daytour.refno}</td>
                                <td align="center">${daytour.refdate}</td>
                                <td align="left">${daytour.agent}</td>
                                <td align="left">${daytour.leader}</td>
                                <td align="center">${daytour.pax}</td>
                                <td align="center">${daytour.tourcode}</td>
                                <td align="left">${daytour.tourname}</td>
                                <td align="center">${daytour.tourdate}</td>
                                <td align="left">${daytour.pickup}</td>
                                <td align="center">${daytour.time}</td>
                                <td align="center">${daytour.adult}</td>
                                <td align="center">${daytour.child}</td>
                                <td align="center">${daytour.infant}</td>
                                <td align="center">${daytour.remark}</td>
                                <td align="center" rowspan="2">
                                    <a href="#" onclick="" data-toggle="modal" data-target="">
                                        <span id="editSpan1" class="glyphicon glyphicon-th-list" onclick="viewBooking('${requestScope['search_type']}','${daytour.refno}','${daytour.id}')"></span>
                                    </a>
                                </td>
                            </tr>
                            <tr style="height: 1.8em;" bgcolor="${colorTr}">
                                <td colspan="2" align="right" bgcolor="${colorTrHead}"><b>Invoice</b></td>
                                <td colspan="5">${daytour.invoice}</td>
                                <td colspan="1" align="right" bgcolor="${colorTrHead}"><b>Receipt</b></td>
                                <td colspan="7">${daytour.receipt}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table> 
                    </c:when>
                    <c:when test="${requestScope['search_type'] == '5'}">
                    <table id="BookList" class="displayblackcolor paginated" cellspacing="0" style="table-layout: fixed">
                        <thead>
                            <tr class="datatable-header" >
                                <th style="width: 2%">No</th>
                                <th style="width: 3%">Ref No</th>
                                <th style="width: 5%">Ref Date</th>
                                <th style="width: 12%">Agent</th>
                                <th style="width: 12%">Leader</th>
                                <th style="width: 6%">Code</th>
                                <th style="width: 12%">Name</th>
                                <th style="width: 5%">Other Date</th>      
                                <th style="width: 3%">Action</th>   
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="other" items="${bookingList}" varStatus="i">
                            <c:set var="color" value=""/>    
                            <c:if test="${i.count%2 == 0}">
                                <c:set var="color" value="#F2F2F2"/>
                            </c:if>    
                            <tr bgcolor=""> 
                                <td align="center">${i.count}</td>
                                <td align="center">${other.refno}</td>
                                <td align="center">${other.refdate}</td>
                                <td align="left">${other.agent}</td>
                                <td align="left">${other.leader}</td>
                                <td align="center" >${other.code}</td>
                                <td align="left">${other.name}</td>
                                <td align="center">${other.otherdate}</td>
                                <td align="center" rowspan="2">
                                    <a href="#" onclick="" data-toggle="modal" data-target="">
                                        <span id="editSpan1" class="glyphicon glyphicon-th-list" onclick="viewBooking('${requestScope['search_type']}','${other.refno}','${other.id}')"></span>
                                    </a>
                                </td>
                            </tr>
                            <tr style="height: 1.8em;" bgcolor="${colorTr}">
                                <td colspan="2" align="right" bgcolor="${colorTrHead}"><b>Invoice</b></td>
                                <td colspan="3">${other.invoice}</td>
                                <td colspan="1" align="right" bgcolor="${colorTrHead}"><b>Receipt</b></td>
                                <td colspan="2">${other.receipt}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table> 
                    </c:when>
                    <c:when test="${requestScope['search_type'] == '6'}">
                    <table id="BookList" class="displayblackcolor paginated" cellspacing="0" style="table-layout: fixed">
                        <thead>
                            <tr class="datatable-header" >
                                <th style="width: 2%">No</th>
                                <th style="width: 5%">Ref No</th>
                                <th style="width: 6%">Ref Date</th>
                                <th style="width: 14%">Agent</th>
                                <th style="width: 14%">Leader</th>
                                <th style="width: 4%">OK By</th>
                                <th style="width: 10%">Description</th>
                                <th style="width: 4%">Category</th>
                                <th style="width: 3%">Qty</th>   
                                <th style="width: 3%">Action</th>   
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="land" items="${bookingList}" varStatus="i">
                            <c:set var="color" value=""/>    
                            <c:if test="${i.count%2 == 0}">
                                <c:set var="color" value="#F2F2F2"/>
                            </c:if>    
                            <tr bgcolor="">
                                <td align="center">${i.count}</td>
                                <td align="center">${land.refno}</td>
                                <td align="center">${land.refdate}</td>
                                <td align="left">${land.agent}</td>
                                <td align="left">${land.leader}</td>
                                <td align="center" >${land.okby}</td>
                                <td align="left">${land.description}</td>
                                <td align="center">${land.category}</td>
                                <td align="center">${land.qty}</td>
                                <td align="center" rowspan="2">
                                    <a href="#" onclick="" data-toggle="modal" data-target="">
                                        <span id="editSpan1" class="glyphicon glyphicon-th-list" onclick="viewBooking('${requestScope['search_type']}','${land.refno}','${land.id}')"></span>
                                    </a>
                                </td>
                            </tr>
                            <tr style="height: 1.8em;" bgcolor="${colorTr}">
                                <td colspan="2" align="right" bgcolor="${colorTrHead}"><b>Invoice</b></td>
                                <td colspan="3">${land.invoice}</td>
                                <td colspan="1" align="right" bgcolor="${colorTrHead}"><b>Receipt</b></td>
                                <td colspan="3">${land.receipt}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table> 
                    </c:when>
                </c:choose>
                
            </div>
        </div>    
    </form>
</div>
