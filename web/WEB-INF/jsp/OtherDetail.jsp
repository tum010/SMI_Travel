<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/OtherDetail.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">
<c:set var="master" value="${requestScope['Master']}" />

<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<c:set var="callpage" value="${requestScope['callpage']}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="product_list" value="${requestScope['product_list']}" />
<c:set var="agent_list" value="${requestScope['agent_list']}" />
<c:set var="currency_list" value="${requestScope['currency_list']}" />
<c:set var="booktype" value="${requestScope['BOOKING_TYPE']}" />
<c:set var="ticketList" value="${requestScope['ticketList']}" />
<c:set var="isBillStatus" value="${requestScope['IsBillStatus']}" />
<input type="hidden" value="${master.departmentNo}" id="departmentNo">
<input type="hidden" value="${master.id}" id="master-id">
<input type="hidden" value="${requestScope['passengerId']}" id="passengerId">
<c:set var="enableVat" value="" />
<c:set var="checkVat" value="checked" />
<c:set var="hiddenPrintBtn" value="" />
<c:if test="${booktype == 'i'}">
    <c:set var="enableVat" value="disabled" />
    <c:set var="checkVat" value="" />
</c:if>
<c:if test="${booktype == 'o'}">
    <c:set var="enableVat" value="disabled" />
    <c:set var="checkVat" value="" />
    <c:set var="hiddenPrintBtn" value="hidden" />
</c:if>
<c:set var="lockUnlockBooking" value="${requestScope['LockUnlockBooking']}" />
<c:set var="readonly" value="" />
<c:if test="${isBillStatus == 1}">
    <c:set var="readonly" value="readonly" />
</c:if>
<c:set var="enableSave" value="${requestScope['EnableSave']}" />
<section class="content-header" >
    <h1>
        Booking - Other Detail
    </h1>
    <ol class="breadcrumb">
        <li><a href="Book.smi?referenceNo=${param.referenceNo}"><i class="fa fa-book"></i> Booking</a></li>          
        <li><a href="other.smi?referenceNo=${param.referenceNo}">Other </a></li>
        <li class="active"><a href="#">Other Detail</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Book/BookMenu.html'"></div>
            <input hidden="" value="${booking_size[0]}" id="input-airticket_size">
            <input hidden="" value="${booking_size[1]}" id="input-hotel_size">
            <input hidden="" value="${booking_size[2]}" id="input-other_size">
            <input hidden="" value="${booking_size[3]}" id="input-land_size">
            <input hidden="" value="${booking_size[4]}" id="input-passenger_size">
            <input hidden="" value="${booking_size[5]}" id="input-billable_size">
            <input hidden="" value="${booking_size[6]}" id="input-daytour_size">  
        </div>
        <form  id="otherForm" action="OtherDetail.smi" method="post" role="form" class="form-horizontal">
        <div class="col-sm-10">
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
            <input type="hidden" id="bookingtype" name="bookingtype" value="${booktype}">
            <input type="hidden" id="callpage" name="callpage" value="${param.callpage}">            
            <!--Alert Save -->
            <c:set var="require" value=""/>
            <c:set var="adultCancel" value=""/>
            <c:set var="childCancel" value=""/>
            <c:set var="infantCancel" value=""/>
            <c:set var="alert" value=""/>
            <c:if test="${(requestScope['adultCancel'] != '0') || (requestScope['childCancel'] != '0') || (requestScope['infantCancel'] != '0')}">
                <c:set var="require" value="Require Ticket - "/>
                <c:set var="alert" value="alert-danger"/>
            </c:if>
            <c:if test="${requestScope['adultCancel'] != '0'}">
                <c:set var="adultCancel" value="Adult: ${requestScope['adultCancel']}"/>
            </c:if>
            <c:if test="${requestScope['childCancel'] != '0'}">
                <c:set var="childCancel" value="Child: ${requestScope['childCancel']}"/>
            </c:if>  
            <c:if test="${requestScope['infantCancel'] != '0'}">
                <c:set var="infantCancel" value="Infant: ${requestScope['infantCancel']}"/>
            </c:if>           
            <c:if test="${requestScope['resultText'] == 'success'}">
                <c:if test="${alert == ''}">
                    <c:set var="alert" value="alert-success"/>
                </c:if>
            <div id="textAlertDivSave"  style="" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Success! </strong><br>
                    <strong><font style="color: red">${require} ${adultCancel} ${childCancel} ${infantCancel}</font></strong> 
            </div>
            </c:if>
            <c:if test="${requestScope['resultText'] == 'unsuccess'}">
            <div id="textAlertDivNotSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Not Success!</strong> 
            </div>
            </c:if>
            <c:if test="${requestScope['resultTicket'] == 'stock success'}">
            <div id="textAlertDivNotSave"  style="" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Update Stock Ticket Success!</strong> 
            </div>
            </c:if>
            <c:if test="${requestScope['resultTicket'] == 'stock fail'}">
            <div id="textAlertDivNotSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Update Stock Ticket Not Success!</strong> 
            </div>
            </c:if>
            <c:if test="${requestScope['resultsave'] == 'success'}">
            <div id="textAlertDivSavess"  style="" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Success! </strong><br>
            </div>
            </c:if>
            <c:if test="${requestScope['resultsave'] == 'unsuccess'}">
            <div id="textAlertDivNotSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Not Success!</strong> 
            </div>
            </c:if>
            <div class="row">
                <div class="col-sm-3">
                    <h4>Booking Other Detail</h4>
                </div>

                <div class="col-sm-9 text-right">                  
                    <c:choose>
                        <c:when test="${fn:containsIgnoreCase(callpage , 'FromOther')}">
                            <a class="btn btn-primary" href="Other.smi?referenceNo=${param.referenceNo}"><i class="glyphicon glyphicon-chevron-left"></i> Back</a>
                        </c:when>
                        <c:when test="${fn:containsIgnoreCase(callpage , 'FromDayTour')}">
                            <a class="btn btn-primary" href="Daytour.smi?referenceNo=${param.referenceNo}"><i class="glyphicon glyphicon-chevron-left"></i> Back</a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-primary" href="Other.smi?referenceNo=${param.referenceNo}"><i class="glyphicon glyphicon-chevron-left"></i> Back</a>
                        </c:otherwise>
                    </c:choose>                                                                       
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">Detail</div>
                <div class="panel-body">
                    
                        <div class="row">
                            <div class="col-sm-6" style="padding-left: 70px; margin-top: -5px;">
                                <label  class="col-sm-2 control-label" >Product<font style="color: red">*</font></label>
                                <div class="col-sm-3">  
                                    <div class="form-group">
                                        <div class="input-group " style="padding-left: 5px">
                                            <input type="hidden" id="callpageSubmit" name="callpageSubmit" value="${requestScope['callpage']}">
                                            <input type="hidden"  class="form-control" name="product_id" id="product_id" value="${requestScope['product_id']}" >
                                            <input type="text"  class="form-control" name="product_code" id="product_code" 
                                                   data-bv-notempty="true" data-bv-notempty-message="The product code is required" value="${requestScope['product_code']}">
                                            <span class="input-group-addon" id="product_modal"  data-toggle="modal" data-target="#ProductModal">
                                                <span class="glyphicon-search glyphicon"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6" style="padding-left: 20px">  
                                    <input type="text" class="form-control" style="width: 250px" id="product_name" name="product_name" value="${requestScope['product_name']}" readonly="">
                                </div>
                            </div>
                            <div class="col-md-3 " style="margin-top: -5px;">
                                <div class="form-group">

                                    <label for="effectivefrom" class="col-sm-3 control-label" > From </label>
                                    <div class=' col-sm-6 input-group date' id='effectivetoClass' style="width: 140px">
                                        <c:set var="otherDate" value="${requestScope['otherdate']}" />
                                        <fmt:parseDate value="${otherDate}" var="otherDate" pattern="yyyy-MM-dd" />
                                        <fmt:formatDate value="${otherDate}" var="otherDate" pattern="dd-MM-yyyy" />
                                        <input type='text' class="form-control datemask"  id="otherdate" name="otherdate" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${otherDate}"/>                                     
                                        <span class="input-group-addon spandate">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                        <input type='hidden' class="form-control datemask"  id="todaydate" name="todaydate" data-date-format="DD-MM-YYYY" value=""/>
                                        <input type='hidden' class="form-control datemask"  id="checkdate" name="checkdate" data-date-format="DD-MM-YYYY" value="${requestScope['otherdate']}"/>                                                                              
                                </div>
                            </div>
                            <div class="col-md-3 " style="margin-top: -5px;">
                                <div class="form-group">
                                    <a class="btn btn-primary" id="btnCheckStock" onclick="showStock()"><i class="glyphicon "></i> Check Stock</a>
                                </div>
                            </div>    
                        </div>
                                
                        <div class="row">
                             <div class="col-sm-6" style="padding-left: 70px; margin-top: -10px;">
                                <label  class="col-sm-2 control-label" >Agent</label>
                                <div class="col-sm-3">  
                                    <div class="form-group">
                                        <div class="input-group " style="padding-left: 5px">
                                            <input type="hidden" class="form-control" name="agent_id" id="agent_id" value="${requestScope['agent_id']}">
                                            <input type="text" class="form-control"  name="agent_code" id="agent_code"value="${requestScope['agent_code']}">
                                            <span class="input-group-addon" id="agent_modal" data-toggle="modal" data-target="#AgentModal">
                                                <span class="glyphicon-search glyphicon"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6" style="padding-left: 20px">  
                                    <input type="text" class="form-control" style="width: 250px" id="agent_name" name="agent_name" value="${requestScope['agent_name']}" readonly="">
                                </div>
                            </div>
                            <div class="col-md-3 " style="margin-top: -10px;">
                                <div class="form-group">

                                    <label for="effectivefrom" class="col-sm-3 control-label" > To </label>
                                    <div class=' col-sm-6 input-group dateto' id='effectivefromClass' style="width: 140px">
                                        <c:set var="otherdateTo" value="${requestScope['otherdateTo']}" />
                                        <fmt:parseDate value="${otherdateTo}" var="otherdateTo" pattern="yyyy-MM-dd" />
                                        <fmt:formatDate value="${otherdateTo}" var="otherdateTo" pattern="dd-MM-yyyy" />
                                        <input type='text' class="form-control datemask"  id="otherdateTo" name="otherdateTo" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${otherdateTo}"/>                                     
                                        <span class="input-group-addon spandate">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>                                                                            
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-3 col-xs-offset-1" style="margin-top: -10px;">
                                <label  class="col-sm-2 col-xs-offset-1 control-label text-right" style="width: 130px">Cost</label>
                            </div>
                            <div class="col-sm-1" style="margin-top: -10px;">
                                <label  class="col-sm-2 control-label text-right" style="width: 130px">Qty</label>
                            </div>
                            <div class="col-sm-3" style="margin-top: -10px;">
                                <label  class="col-sm-2  control-label text-right" style="width: 130px">Price</label>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-3 col-xs-offset-1">
                                <div class="form-group">
                                    <label class="col-sm-2   control-label" for="cost">Adult</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control decimal text-right" id="ad_cost" name="ad_cost" value="${requestScope['ad_cost']}" ${readonly}>  
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1 ">
                                <div class="form-group">
                                    <div class="col-sm-12" >
                                        <input type="text" class="form-control money text-right" id="ad_qty" name="ad_qty" value="${requestScope['ad_qty']}" ${readonly}>  
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 ">
                                <div class="form-group">
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control decimal text-right" id="ad_price" name="ad_price" value="${requestScope['ad_price']}" ${readonly}>  
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3" >
                                <div class="form-group">
                                    <label class="col-sm-3   control-label" for="cost" style="width: 130px">Cost Currency</label>
                                    <input type="hidden" class="form-control" id="selectedCurrencyCost" name="selectedCurrencyCost" value="${requestScope['currencycost']}" >                                   
                                    <div class="col-sm-5" style="padding-left: 20px">                                        
                                        <select class="form-control" id="currencycost" name="currencycost">    
                                            <c:forEach var="currency" items="${currency_list}">
                                                <c:set var="select" value="" />
                                                <c:if test="${currency.code == requestScope['currencycost']}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="<c:out value="${currency.code}" />" ${select}><c:out value="${currency.code}" /></option>                                         
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-4 hidden">
                                        <label class="control-label"><input onclick='calculateVatvalue();' type="checkbox" id="Vat" name="Vat" ${enableVat} ${checkVat}>  Vat</label>
                                    </div>
                                </div>
                            </div>
                           
                            <!--
                            
                            -->
                        </div>

                        <div class="row">
                            <div class="col-md-3 col-xs-offset-1" style="margin-top: -10px;">
                                <div class="form-group">
                                    <label class="col-sm-2   control-label" for="cost">Child</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control decimal text-right" id="ch_cost" name="ch_cost" value="${requestScope['ch_cost']}" ${readonly}>  
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1 " style="margin-top: -10px;">
                                <div class="form-group">
                                    <div class="col-sm-12" >
                                        <input type="text" class="form-control money text-right" id="ch_qty" name="ch_qty" value="${requestScope['ch_qty']}" ${readonly} >  
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 " style="margin-top: -10px;">
                                <div class="form-group">
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control decimal text-right" id="ch_price" name="ch_price" value="${requestScope['ch_price']}" ${readonly}>  
                                    </div>
                                </div>
                            </div>
                                    
                           <div class="col-md-3" style="margin-top: -10px;">
                                <div class="form-group">
                                    <label class="col-sm-3   control-label" for="cost" style="width: 130px">Price Currency</label>
                                    <input type="hidden" class="form-control" id="selectedCurrency" name="selectedCurrency" value="${requestScope['currency']}" >                                   
                                    <div class="col-sm-5" style="padding-left: 20px">                                        
                                        <select class="form-control" id="currency" name="currency">    
                                            <c:forEach var="currency" items="${currency_list}">
                                                <c:set var="select" value="" />
                                                <c:if test="${currency.code == requestScope['currency']}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="<c:out value="${currency.code}" />" ${select}><c:out value="${currency.code}" /></option>                                         
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-sm-4 hidden">
                                        <label class="control-label"><input onclick='calculateVatvalue();' type="checkbox" id="Vat" name="Vat" ${enableVat} ${checkVat}>  Vat</label>
                                    </div>
                                </div>
                            </div>                                                                               
                        </div>

                        <div class="row">
                            <div class="col-md-3 col-xs-offset-1" style="margin-top: -10px;">
                                <div class="form-group">
                                    <label class="col-sm-2   control-label" for="cost">Infant</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control decimal text-right" id="in_cost" name="in_cost"  value="${requestScope['in_cost']}" ${readonly}>  
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-1 " style="margin-top: -10px;">
                                <div class="form-group">
                                    <div class="col-sm-12" >
                                        <input type="text" class="form-control money text-right" id="in_qty" name="in_qty"  value="${requestScope['in_qty']}" ${readonly}>  
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 " style="margin-top: -10px;">
                                <div class="form-group">
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control decimal text-right" id="in_price" name="in_price" value="${requestScope['in_price']}" ${readonly} >  
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3" style="margin-top: -10px;">
                                <div class="form-group">
                                    <label class="col-sm-3   control-label" for="cost" >Time</label>
                                    <div class='col-sm-7 input-group times' style="padding-left: 20px;width: 157px" id="arrive-time">
                                        <input type='text' class="form-control" id="othertime" name="othertime" value="${requestScope['othertime']}"  />
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-time"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>                                  
                        </div> 

                        <div class="row">
                            <div class="col-md-6 " style="margin-top: -10px;">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label text-right"  for="fromdes">Remark</label>
                                    <div class="col-sm-9">  
                                        <textarea class="form-control" maxlength="500" rows="2" id="remark" name="remark">${requestScope['remark']}</textarea>
                                    </div>   
                                </div>
                            </div>   
                            <div class="col-md-2" ></div>      
                            <div class="col-md-3" style="margin-top: -10px;">
                                <div class="form-group">
                                    <label class="col-sm-3   control-label" for="cost">Cancel</label>
                                    <div class='col-sm-7 input-group date' style="padding-left: 20px;width: 157px" id="arrive-time">
                                        <%--<c:set var="cancelDate" value="${requestScope['cancelDate']}" />--%>
                                        <%--<fmt:parseDate value="${cancelDate}" var="cancelDate" pattern="yyyy-MM-dd" />--%>
                                        <%--<fmt:formatDate value="${cancelDate}" var="cancelDate" pattern="dd-MM-yyyy" />--%>
                                        <input type='text' disabled class="form-control" id="cancelDate" name="cancelDate" value="${requestScope['cancelDate']}"  />
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>  
                        </div>
                        <div class="row">
                            <div class="col-md-6 " style="margin-top: -10px;">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label text-right" >Memo</label>
                                    <div class="col-sm-9">  
                                        <textarea class="form-control" maxlength="500" rows="2" id="memo" name="memo">${requestScope['memo']}</textarea>
                                    </div>   
                                </div>
                            </div>    
                        </div>
                        <input type="hidden" class="form-control" id="action" name="action" value="save">
                        <input type="hidden" class="form-control" id="itemid" name="itemid" value="${requestScope['itemid']}">
                        <input type="hidden" value="${param.referenceNo}" id="refno" name="referenceNo">
                        <input type="hidden" class="form-control" id="status" name="status" value="${requestScope['status']}">
                        <input type="hidden" class="form-control" id="isbill" name="isbill" value="${requestScope['isbill']}">
                        <input type="hidden" class="form-control" id="createby" name="createby" value="${requestScope['createby']}">
                        <input type="hidden" class="form-control" id="createdate" name="createdate" value="${requestScope['createdate']}">
                        <input type="hidden" class="form-control" id="ticketstatus" name="ticketstatus" value="">
                        <input type="hidden" class="form-control" id="addticket" name="addticket" value="">
                        <input type="hidden" class="form-control" id="counter" name="counter" value="">
                        <div class="col-xs-12" >  
                            <!--<button type="button" class="btn btn-default duplicate" onclick="printOther()"><span class="glyphicon glyphicon-print"></span> Print </button>-->
                            <div class="col-xs-3"></div>
                            <c:set var="disabled" value=""/>
                            <c:if test="${requestScope['itemid'] == null || requestScope['itemid'] == ''}">
                                <c:set var="disabled" value="disabled"/>
                            </c:if>
                            <div class="col-xs-2">
                                <select name="voucher" id="voucher"  class="form-control ${hiddenPrintBtn}" style="width: 160px; height: 34px" ${disabled}>
                                    <option value="">--Select Type--</option>
                                    <option value="OtherVouncher">Other Voucher</option>
                                    <option value="OtherVoucherEmail">Other Voucher Email</option>
                                </select>
                            </div>
                            <div class="col-xs-1">
                                <button type="button" class="btn btn-default duplicate ${hiddenPrintBtn}" onclick="printOther()" ${disabled}><span class="glyphicon glyphicon-print"></span> Print </button>
                            </div>
                            <div class="col-xs-1">
                                <c:choose>
                                    <c:when test="${requestScope['status'] == 2}">
                                        <button type="submit" disabled  class="btn btn-success"><span class="fa fa-save"></span> Save</button>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${lockUnlockBooking == 0}">
                                            <%--<c:if test="${isBillStatus == 0}">--%>
                                                <button type="button" class="btn btn-success duplicate" onclick="saveOther()"><span class="fa fa-save"></span> Save</button>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${isBillStatus == 1}">--%>
                                                <%--<c:choose>--%>
                                                    <%--<c:when test="${enableSave == 0}">--%>
                                                        <!--<button type="button" class="btn btn-success" onclick="saveOther()"><span class="fa fa-save"></span> Save</button>-->
                                                    <%--</c:when>--%>
                                                    <%--<c:when test="${enableSave == 1}">--%>
                                                        <!--<button class="btn btn-success disabled" ><span class="fa fa-save"></span> Save</button>-->
                                                    <%--</c:when>--%>
                                                <%--</c:choose>--%> 
                                            <%--</c:if>--%>
                                        </c:if>
                                        <c:if test="${lockUnlockBooking == 1}">
                                            <button class="btn btn-success disabled"><span class="fa fa-save"></span> Save</button>
                                        </c:if>   
                                    </c:otherwise>
                                </c:choose>  
                            </div>
                        </div>
                        <input type="hidden" id="callPageFrom" name="callPageFrom" value="${callpage}">
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">Ticket</div>
                <div class="panel-body">                    
                    <div class="form-group col-md-1" style="width: 200px">
                        <label class="col-sm-3 control-label">Adult</label>
                        <div class="col-sm-1" style="width: 100px">
                            <input type="text" class="form-control decimalticket text-right" id="adTicket" name="adTicket" value="0">  
                        </div>
                    </div>
                    <div class="form-group col-md-1" style="width: 200px">
                        <label class="col-sm-3 control-label">Child</label>
                        <div class="col-sm-1" style="width: 100px">
                            <input type="text" class="form-control decimalticket text-right" id="chTicket" name="chTicket" value="0">  
                        </div>
                    </div>    
                    <div class="form-group col-md-1" style="width: 200px">
                        <label class="col-sm-3 control-label">Infant</label>
                        <div class="col-sm-1" style="width: 100px">
                            <input type="text" class="form-control decimalticket text-right" id="infTicket" name="infTicket" value="0">  
                        </div>
                    </div>
                    <div class="form-group col-md-1" style="width: 200px">
                        <button type="button"  class="btn btn-success duplicate" onclick="addStockTicket()" id="addTicketButton" name="addTicketButton" ${disabled}>
                            <span class="glyphicon glyphicon-plus"></span> Add
                        </button>
                    </div>
                    <div class="form-group col-md-1 text-right" style="padding-left: 190px">
                        <button type="button" class="btn btn-danger duplicate" onclick="setStockTicket()" id="changeStatusButton" name="changeStatusButton" ${disabled}>
                            <span id="SpanDisableVoid" class="glyphicon glyphicon-remove" ></span> Change Status
                        </button>
                    </div> 
                    <div class="row" style="padding: 50px 0px 0px 30px">
                        <font style="color: red" id="alertCheckbox"></font>
                    </div>
                    <input type="hidden"  id="ticketListSize" name="ticketListSize" value="${requestScope['ticketListSize']}"/>
                    <div class="row" style="margin-left: 10px;margin-right: 10px;"> 
                        <table id="TicketTable" class="display" cellspacing="0"  >
                            <thead>
                                <tr class="datatable-header">
                                    <th class="hidden">id</th>
                                    <th style="width: 3%" onclick="selectAll()"><u>All</u></th>
                                    <th style="width: 5%">No</th>
                                    <th style="width: 11%">Add Date</th>
                                    <th style="width: 41%">Ticket</th>
                                    <th style="width: 21%">Type</th>
                                    <th style="width: 20%">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="table" items="${ticketList}" varStatus="i"> 
                                    <tr>
                                        <td class="hidden">
                                            <input type="hidden" class="form-control" id="stockticketid${i.count}" name="stockticketid${i.count}" value="${table.id}">
                                        </td>
                                        <td align="center">
                                            <input type="checkbox" class="form-control" id="selectAll${i.count}" name="selectAll${i.count}" value="1" onclick="removeAlertCheckbox();setSelectCheckBox('${i.count}')">
                                        </td>
                                        <td align="center">${i.count}</td>
                                        <fmt:formatDate value="${table.addDate}" var="addDate" pattern="dd-MM-yyyy" />
                                        <td align="center" >${addDate}</td>
                                        <td>${table.ticketCode}</td>
                                        <td align="center">${table.typeName}</td>
                                        <th align="center">
                                            <c:if test="${table.status == 'REFUND'}">
                                                <font style="color: red;"> ${table.status} </font>
                                            </c:if>
                                            <c:if test="${table.status != 'REFUND'}">
                                               ${table.status}
                                            </c:if>
                                        </th>                                        
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <script type="text/javascript" charset="utf-8">
                        $(document).ready(function() {
                            var table = $('#TicketTable').dataTable({bJQueryUI: true,
                                "bJQueryUI": true,
                                "sPaginationType": "full_numbers",
                                "bAutoWidth": false,
                                "bFilter": false,
                                "bInfo": true,
                                "bSort": false

                            });
                            $('#TicketTable tbody').on('click', 'tr', function() {
                                if ($(this).hasClass('row_selected')) {
                                    $(this).removeClass('row_selected');
                                    $('#hdGridSelected').val('');
                                }
                                else {
                                    table.$('tr.row_selected').removeClass('row_selected');
                                    $(this).addClass('row_selected');
                                    $('#hdGridSelected').val($('#TicketTable tbody tr.row_selected').attr("id"));
                                }
                            });                           
                        });
                    </script>
                    </div>
                </div>
            </div>       
            </div>
        </form>                
    </div>
</div>                        

<div class="modal fade" id="Confirm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"  id="Titlemodel">Booking - Other </h4>
            </div>
            <div class="modal-body" id="confirmMessage">
                Are you sure to update cost and price ?
            </div>
            <div class="modal-footer">
                <button type="button" onclick="Confirm()" class="btn btn-danger">Yes</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
            </div>
            <div class="hidden">
                <input type="text" class="form-control" id="path0" name="path0" value="">
                <input type="text" class="form-control" id="path1" name="path1" value="">
                <input type="text" class="form-control" id="path2" name="path2" value="">
                <input type="text" class="form-control" id="path3" name="path3" value="">
                <input type="text" class="form-control" id="path4" name="path4" value="">
                <input type="text" class="form-control" id="path5" name="path5" value="">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<div class="modal fade" id="ProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Product</h4>
            </div>
            <div class="modal-body">
                <!--Airline List Table-->
                <table class="display" id="ProductTable">
                    <thead>
                        <script>
                            product = [];                      
                        </script>
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                            <th class="">Name</th>
                            <th class="">Detail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${product_list}">
                            <tr onclick ="setupproductvalue('${table.id}', '${table.code}', '${table.name}' , '${booktype}');" >
                                <td class="hidden">${table.id}</td>
                                <td>${table.code} </td>
                                <c:set var="name1" value="${table.name}"/>
                                <c:set var="name2" value="${fn:replace(name1, '\\\\', '')}" />
                                <td>${name2}</td>
                                <c:set var="description1" value="${table.description}"/>
                                <c:set var="description2" value="${fn:replace(description1, '\\\\', '')}" />
                                <td>${description2} </td>
                            </tr>
                            <script>
                            product.push({id: "${table.id}",code: "${table.code}",name: "${table.name}"});
                            </script>
                        </c:forEach>

                    </tbody>

                </table>
                <!--Script Airline List Table-->
                <script type="text/javascript" charset="utf-8">
                    $(document).ready(function() {
                        var table = $('#ProductTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10,
                            "aaSorting": [[ 1, "asc" ]]
                        });
                        
//                        var otdate = $('#otherdate').val();
//                        if(otdate !== ''){
//                            $('#otherdate').val(convertFormatDate(otdate));
//                        }
//                        var otdateto = $('#otherdateTo').val();
//                        if(otdateto !== ''){
//                            $('#otherdateTo').val(convertFormatDate(otdateto));
//                        }
//                        var ccdate = $('#cancelDate').val();
//                        if(ccdate !== ''){
//                            $('#cancelDate').val(convertFormatDate(ccdate));
//                        }
                        
                        $('#ProductTable tbody').on('click', 'tr', function() {
                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                                $('#hdGridSelected').val('');
                            }
                            else {
                                table.$('tr.row_selected').removeClass('row_selected');
                                $(this).addClass('row_selected');
                                $('#hdGridSelected').val($('#MasterProduct tbody tr.row_selected').attr("id"));
                            }
                        });
                           
                        $('.date').datetimepicker().change(function(){
                            $("#effectivefromClass").removeClass("has-error");
                            $("#effectivetoClass").removeClass("has-error");
                            setupotherdatevalue('${booktype}');
                        });
                        
                        $('.dateto').datetimepicker().change(function(){
                            $("#effectivefromClass").removeClass("has-error");
                            $("#effectivetoClass").removeClass("has-error");
                        });
                        
//                        $("#otherdate").focusout(function() {
//                            setupotherdatevalue('${booktype}');
//                        });
//                        
                        var now = new Date();
                        var day = ("0" + now.getDate()).slice(-2);
                        var month = ("0" + (now.getMonth() + 1)).slice(-2);
                        var today = (day)+"-"+(month)+"-"+now.getFullYear() ;
                        $('#todaydate').val(today);
                                                                                              
                        $('.spandate').click(function() {
                            var position = $(this).offset();
                            console.log("positon :" + position.top);
                            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

                        });
                        
                        $('.times').datetimepicker({
                            pickDate: false,
                            pickTime: true,
                            pick12HourFormat: false,
                            format: 'HH:mm'
                        });

                        $(".money").mask('000,000,000,000,000,000', {reverse: true});

                    });
                </script>

            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>


<div class="modal fade" id="AgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Agent</h4>
            </div>
            <div class="modal-body">
                <!--Airline List Table-->
                <table class="display" id="AgentTable">
                    <thead>    
                     <script>
                        agent = [];
                       
                     </script>
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th class="">Code</th>
                            <th class="">Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="a" items="${agent_list}">
                            <tr onclick ="setupagentvalue('${a.id}', '${a.code}', '${a.name}')" >
                                <td class="hidden">${a.id}</td>
                                <td>${a.code} </td>
                                <td>${a.name} </td>
                            </tr>      
                        <script>
                            agent.push({id: "${a.id}",code: "${a.code}",name: "${a.name}"});
                          </script>
                        </c:forEach>

                    </tbody>
                </table>
                <!--Script Airline List Table-->
                <script type="text/javascript" charset="utf-8">
                    $(document).ready(function() {
                        $(".decimal").inputmask({
                            alias: "decimal",
                            integerDigits: 8,
                            groupSeparator: ',',
                            autoGroup: true,
                            digits: 2,
                            allowMinus: false,
                            digitsOptional: false,
                            placeholder: "0"
                        });
                        
                        $(".decimalticket").inputmask({
                            alias: "decimal",
                            integerDigits: 3,
                            groupSeparator: ',',
                            autoGroup: true,
                            digits: 0,
                            allowMinus: false,
                            digitsOptional: false,
                            placeholder: "0"
                        });
                        
                        var table = $('#AgentTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10,
                            "aaSorting": [[ 1, "asc" ]]
                        });
                       
                        $('#AgentTable tbody').on('click', 'tr', function() {
                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                                $('#hdGridSelected').val('');
                            }
                            else {
                                a.$('tr.row_selected').removeClass('row_selected');
                                $(this).addClass('row_selected');
                                $('#hdGridSelected').val($('#MasterProduct tbody tr.row_selected').attr("id"));
                            }
                        });

                        $("div").find('.date').datetimepicker();
                        $("div").find('.dateto').datetimepicker();
                        $("div").find('.times').datetimepicker({
                            pickDate: false,
                            pickTime: true,
                            pick12HourFormat: false,
                            format: 'HH:mm'
                        });
                        $("div").find('.input-group-addon').click(function () {
                            var position = $(this).offset();
                            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
                        });                      
                    });
                </script>

            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
                        
<div class="modal fade" id="stockTicketModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="Daytour.smi" method="post" id="DaytourForm" class="form-horizontal"  role="form">            
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 id="titleDaytourModal" class="modal-title"> Cancel Ticket </h4>
                </div>
                <div class="modal-body" id="modalText" name="modalText">
                    <div>Please select ticket status ?</div>                   
                    <div class="radio col-xs-12">
                        <div class="col-xs-4">
                            <label ><input value="reuse" id="reuse" name="cancelTicket" type="radio" >Reuse</label>
                        </div>
                        <div class="col-xs-4">
                            <label ><input value="refund" id="refund" name="cancelTicket" type="radio" >Refund</label>
                        </div>    
                        <div class="col-xs-4">
                            <label ><input value="void" id="void" name="cancelTicket" type="radio" >Void</label>
                        </div>
                    </div><br/>                   
                </div>                              
                <div class="modal-footer">
                    <button type="button" id="enableCancelStockTicket" name="enableCancelStockTicket" class="btn btn-success" onclick="cancelStockTicket()">OK</button>
                    <button type="button" id="closeModal" name="closeModal" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="Stock" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title"  id="Titlemodel">Booking - Other </h4>
            </div>
            <div class="modal-body">
                <table class="display" id="StockTable">
                    <thead class="datatable-header">
                        <tr>
                            <th style="width: 33%">Adult</th>
                            <th style="width: 33%">Child</th>
                            <th style="width: 33%">Infant</th>
                        </tr>
                    </thead>
                    <tbody>
                                    
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                
            </div>
            <div class="hidden">
                
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<c:if test="${! empty requestScope['result']}">
    <script language="javascript">
        
//        alert('<c:out value="${requestScope['result']}" />');
    </script>
</c:if>
    
<script>
    $(document).ready(function () {
        $('.datemask').mask('00-00-0000');
    });
</script>