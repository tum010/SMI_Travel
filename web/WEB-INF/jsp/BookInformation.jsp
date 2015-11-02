<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/BookInformation.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <div class="col-xs-1"></div>
                <div class="col-xs-2" >
                    <label>Ref No.</label>
                    <input class="form-control" id="bookRefNo" name="bookRefNo" value=""/>
                </div>
                <div class="col-xs-2">
                    <label>Leader</label>
                    <input class="form-control" id="bookLeader" name="bookLeader" value=""/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Booking Date</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="bookDate" name="bookDate" 
                                   data-date-format="YYYY-MM-DD" value="" />
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
                <div class="col-xs-1"></div>
                <div class="col-xs-2" >
                    <label>Pnr</label>
                    <input class="form-control" id="airPnr" name="airPnr" value=""/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Dept Date</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="airDeptDate" name="airDeptDate" 
                                   data-date-format="YYYY-MM-DD" value="" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
                <div class="col-xs-2" >
                    <label>Flight</label>
                    <input class="form-control" id="airFlight" name="airFlight" value=""/>
                </div>
            </div>
            <div class="col-md-12 hidden" id="searchHotel">
                <div class="col-xs-1"></div>
                <div class="col-xs-2" >
                    <label>Hotel</label>
                    <input class="form-control" id="hotelName" name="hotelName" value=""/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Check In</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="hotelCheclIn" name="hotelCheclIn" 
                                   data-date-format="YYYY-MM-DD" value="" />
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
                                   data-date-format="YYYY-MM-DD" value="" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
            </div>
            <div class="col-md-12 hidden" id="searchPackageTour">
                <div class="col-xs-1"></div>
                <div class="col-xs-2" >
                    <label>Package</label>
                    <input class="form-control" id="packageName" name="packageName" value=""/>
                </div>
                <div class="col-xs-2" >
                    <label>Agent</label>
                    <input class="form-control" id="packageAgent" name="packageAgent" value=""/>
                </div>
            </div>
            <div class="col-md-12 hidden" id="searchDayTours">
                <div class="col-xs-1"></div>
                <div class="col-xs-2" >
                    <label>Tour Code</label>
                    <input class="form-control" id="tourCode" name="tourCode" value=""/>
                </div>
                <div class="col-xs-2" >
                    <label>Tour Name</label>
                    <input class="form-control" id="tourName" name="tourName" value=""/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Tour Date</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="tourDate" name="tourDate" 
                                   data-date-format="YYYY-MM-DD" value="" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
                <div class="col-xs-2" >
                    <label>Pick up</label>
                    <input class="form-control" id="tourPickup" name="tourPickup" value=""/>
                </div>
            </div>
            <div class="col-md-12 hidden" id="searchOther">
                <div class="col-xs-1"></div>
                <div class="col-xs-2" >
                    <label>Other Code</label>
                    <input class="form-control" id="otherCode" name="otherCode" value=""/>
                </div>
                <div class="col-xs-2" >
                    <label>Other Name</label>
                    <input class="form-control" id="otherName" name="otherName" value=""/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Other Date</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="otherDate" name="otherDate" 
                                   data-date-format="YYYY-MM-DD" value="" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
                <div class="col-xs-2" >
                    <label>Agent</label>
                    <input class="form-control" id="otherAgent" name="otherAgent" value=""/>
                </div>
            </div>
            <div class="col-md-12 hidden" id="searchLand">
                <div class="col-xs-1"></div>
                <div class="col-xs-2" >
                    <label>OK By</label>
                    <input class="form-control" id="landOkBy" name="landOkBy" value=""/>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Category</label>
                        <div class=' col-sm-12 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control"  id="landCategory" name="landCategory" 
                                   data-date-format="YYYY-MM-DD" value="" />
                            <span class="input-group-addon spandate">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </div>  
                <div class="col-xs-2" >
                    <label>Agent</label>
                    <input class="form-control" id="landAgent" name="landAgent" value=""/>
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
    </form>
</div>
