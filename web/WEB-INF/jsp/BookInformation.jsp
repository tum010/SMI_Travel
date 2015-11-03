<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/BookInformation.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<input type="text" id="searchType" name="searchType" value="${requestScope['search_type']}"/>
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
                <div class="col-xs-2">
                    <label>Type</label>
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
                    <input class="form-control" id="tourPickup" name="tourPickup" value="${requestScope['tourPickup']}"/>
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
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="landCategory" name="landCategory" 
                                   data-date-format="YYYY-MM-DD" value="${requestScope['landCategory']}" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
                <div class="col-xs-2" >
                    <label>Agent</label>
                    <input class="form-control" id="landAgent" name="landAgent" value="${requestScope['landAgent']}"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="form-actions pull-right" style="padding-top: 20px;" >
                    <button type="button" id="btnSearch" class="btn btn-primary" onclick="searchAction()"><span class="fa fa-search"></span> Search</button>           
                    <input type="hidden" name="action" id="action" value="">
                    <a id="btnNew"  href="BookDetail.smi?&action=new" class="btn btn-success" ><span class="fa fa-plus-circle"></span> New</a>
                </div>
            </div> 
        </div>
                <div class="col-md-12 "><br></div>
        <div class="row">
            <div class="col-md-12 ">
                <c:choose>
                    <c:when test="${requestScope['search_type'] == '2'}">
                        <table id="BookList" class="display" cellspacing="0" style="table-layout: fixed">
                        <thead>
                            <tr class="datatable-header" >
                                <th style="width: 6%">Ref No</th>
                                <th style="width: 7%">Ref Date</th>
                                <th style="width: 15%">Agent</th>
                                <th style="width: 14%">Leader</th>
                                <th style="width: 5%">Hotel</th>
                                <th style="width: 6%">Check In</th>
                                <th style="width: 6%">Check Out</th>
                                <th style="width: 6%">Total Cost</th>
                                <th style="width: 6%">Cur Cost</th>
                                <th style="width: 6%">Total Price</th>
                                <th style="width: 6%">Cur Amount</th>
                            </tr>
                            <tr class="datatable-header">
                                <th colspan="5">Invoice</th>
                                <th colspan="6">Receipt</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                
                            </tr>  
                        </tbody>
                    </table> 
                    </c:when>
                </c:choose>
                
            </div>
        </div>    
    </form>
</div>
