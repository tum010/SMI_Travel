<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Other.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="other_list" value="${requestScope['OtherList']}" />
<c:set var="currency" value="${requestScope['Currency']}" />
<c:set var="totalcost" value="${requestScope['totalcost']}" />
<c:set var="totalprice" value="${requestScope['totalprice']}" />
<c:set var="amount" value="${requestScope['amount']}" />
<c:set var="markup" value="${requestScope['markup']}" />
<c:set var="master" value="${requestScope['Master']}" />
<c:set var="refno1" value="${fn:substring(param.referenceNo, 0, 2)}" />
<c:set var="refno2" value="${fn:substring(param.referenceNo, 2,7)}" />
<c:set var="lockUnlockBooking" value="${requestScope['LockUnlockBooking']}" />
<c:set var="isBillStatus" value="${requestScope['IsBillStatus']}" />
<input type="hidden" value="${refno1}-${refno2}" id="getUrl">
<input type="hidden" value="${param.referenceNo}" id="getRealformatUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">
<input type="hidden" value="${master.departmentNo}" id="departmentNo">
<input type="hidden" value="${master.id}" id="master-id">
<input type="hidden" value="${requestScope['result']}" id="resultText">
<section class="content-header" >
    <h1>
        Booking - Other
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">Other</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <input type="hidden" value="${master.customer.MInitialname.name}" id="initialname_tmp">
            <input type="hidden" value="${master.customer.firstName}" id="firstname_tmp">
            <input type="hidden" value="${master.customer.lastName}" id="lastname_tmp">  
            <div ng-include="'WebContent/Book/BookMenu.html'"></div>
            <input hidden value="${booking_size[0]}" id="input-airticket_size">
            <input hidden value="${booking_size[1]}" id="input-hotel_size">
            <input hidden value="${booking_size[2]}" id="input-other_size">
            <input hidden value="${booking_size[3]}" id="input-land_size">
            <input hidden value="${booking_size[4]}" id="input-passenger_size">
            <input hidden value="${booking_size[5]}" id="input-billable_size">
            <input hidden="" value="${booking_size[6]}" id="input-daytour_size">  

        </div>

        <script type="text/javascript" charset="utf-8">
            $(document).ready(function() {
                var table = $('#MasterOther').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bInfo": false
                });

                $('#MasterOther tbody').on('click', 'tr', function() {
                    if ($(this).hasClass('row_selected')) {
                        $(this).removeClass('row_selected');
                    }
                    else {
                        table.$('tr.row_selected').removeClass('row_selected');
                        $(this).addClass('row_selected');
                    }
                });

                $(function() {
                    $('[data-toggle="tooltip"]').tooltip({html: true})
                })
                
                setformat();
            });

//              $(".money").mask('000,000,000,000,000,000.00', {reverse: true});
              $(".percent").mask('000,000,000,000,000.00%', {reverse: true});
              
              function setformat(){
                $('#MasterOther tr td.moneyformat').each(function() {
                    var innerHTML = $(this).html();
                    $(this).html(numberWithCommas($(this).html()));       
                });
              }
        </script>

        <div class="col-sm-10">
           
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>
            <!--Alert Save -->
<div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Save Success!</strong> 
</div>
<div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <strong>Save Not Success!</strong> 
</div>
            <div class="row"> 
                <div class="col-md-6 " style="padding-right: 15px">
                    <h4><b>Other</b> </h4>
                </div>

                <div class="row-fluid">
                    <div class="form-actions pull-right" style="padding-right: 20px">
                        <c:if test="${lockUnlockBooking == 0 || lockUnlockBooking == 2}">
                            <a href="OtherDetail.smi?referenceNo=${param.referenceNo}&action=new&callPageFrom=FromOther"><button type="button" id="acs" onclick=""  class="btn btn-success duplicate"><span class="glyphicon glyphicon-plus"></span>Add</button>  </a>  
                        </c:if>
                        <c:if test="${lockUnlockBooking == 1}">
                            <a class="btn btn-success disabled">
                                <span class="glyphicon glyphicon-plus"></span>Add</button>
                            </a>   
                        </c:if>
                    </div>
                </div> 

            </div>

            <div class="row" style="margin-left: 0px;margin-right: 0px;"> 
                <table id="MasterOther" class="display" cellspacing="0" width="100%" style="table-layout: auto;">
                    <thead>
                        <tr class="datatable-header">
                            <th class="hidden" rowspan="2">Code</th>
                            <th rowspan="2" style="width: 9%">Date</th>
                            <th rowspan="2" style="width: 12%">Product Name</th>
                            <th colspan="3" style="width: 17%">Adult</th>
                            <th colspan="3" style="width: 17%">Child</th>
                            <th colspan="3" style="width: 17%">Infant</th>
                            <th rowspan="2" style="width: 7%">Cost</th>
                            <th rowspan="2" style="width: 4%">Cur</th>
                            <th rowspan="2" style="width: 7%">Price</th>
                            <th rowspan="2" style="width: 4%">Cur</th>
                            <th rowspan="2" style="width: 6%">Act</th>

                        </tr>

                        <tr class="datatable-header">
                            <th>Cost</th>
                            <th>Qty</th>
                            <th>Price</th>
                            <th>Cost</th>
                            <th >Qty</th>
                            <th>Price</th>
                            <th>Cost</th>
                            <th >Qty</th>
                            <th>Price</th>
                        </tr>                        
                    </thead>
                    <tbody>
                     
                        <c:forEach var="table" items="${other_list}" varStatus="i">
                             <c:set var="colourStatus" value="" />
                             <c:set var="colourStatusFirstrow" value="" />
                            
                             <c:if test="${table.status.id == 2}">
                                <c:set var="colourStatus" value="style='background-color: #FFD3D3'" />
                                <c:set var="colourStatusFirstrow" value="background-color: #FFD3D3" />
                                <c:set var="statusicon" value="glyphicon-remove deleteicon" />
                             </c:if>
                             
<!--                            <script>
                                $(document).ready(function () {
                                    if("${table.otherDate}" !== ''){
                                        $("#otherdate-${table.id}").text(convertFormatDate("${table.otherDate}"));
                                    }
                                });
                            </script>-->
                            <tr data-toggle="tooltip"  data-placement="left" title="<p align='left'>  Remark :${table.remark} <br> Remark Stock :${table.remarkTicket} </p>" ${colourStatus} ${colourStatus}>
                                <td class="hidden tdcenter ${colourStatus}" style="width:75px;${colourStatusFirstrow}"> </td>
                                <fmt:formatDate value="${table.otherDate}" var="otherDate" pattern="dd-MM-yyyy" />
                                <td class="tdcenter ${colourStatus}" id="otherdate-${table.id}" style="width:75px;${colourStatusFirstrow}">${otherDate}</td>
                                <td> ${table.product.name}</td>
                                
                                <td class="tdright moneyformat"><font size="2"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.adCost}" /></font></td>
                                <td class="tdcenter moneyformat"><font size="2"><fmt:formatNumber type="currency" pattern="#,##0;-#,##0" value="${table.adQty}" /></font></td>
                                <td class="tdright moneyformat"><font size="2"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.adPrice}" /></font></td>
                                <td class="tdright moneyformat"><font size="2"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.chCost}" /></font></td>
                                <td class="tdcenter moneyformat"><font size="2"><fmt:formatNumber type="currency" pattern="#,##0;-#,##0" value="${table.chQty}" /></font></td>
                                <td class="tdright moneyformat"><font size="2"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.chPrice}" /></font></td>
                                <td class="tdright moneyformat"><font size="2"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.inCost}" /></font></td>
                                <td class="tdcenter moneyformat"><font size="2"><fmt:formatNumber type="currency" pattern="#,##0;-#,##0" value="${table.inQty}" /></font></td>
                                <td class="tdright moneyformat"><font size="2"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.inPrice}" /></font></td>
                                <td class="tdright moneyformat"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${(table.adCost * table.adQty) + (table.chCost * table.chQty) + (table.inCost * table.inQty)}" /></td>
                                <td class="tdcenter"> ${table.curCost}</td>
                                <td class="tdright moneyformat"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${(table.adPrice * table.adQty) + (table.chPrice * table.chQty) + (table.inPrice * table.inQty)}" /></td>
                                <td class="tdcenter"> ${table.curAmount}</td>
                                <td> 
                                    <center> 
                                        <a href="OtherDetail.smi?referenceNo=${param.referenceNo}&itemid=${table.id}&action=edit&callPageFrom=FromOther"><span class="glyphicon glyphicon-edit editicon"      onclick="" ></span></a>
                                        <c:if test="${table.status.id == 2}">
                                            <span class="glyphicon glyphicon-plus addicon"   onclick="EnableOther('${table.id}',' ${table.product.code}');" data-toggle="modal" data-target="#EnableOther" ></span>
                                        </c:if>
                                        <c:if test="${table.status.id == 1}">
                                            <c:if test="${lockUnlockBooking == 0}">
                                                <c:if test="${table.isBill == 0}">
                                                    <span class="glyphicon glyphicon-remove deleteicon"   onclick="getCouponCheck('${table.id}',' ${table.product.code}');" data-toggle="modal" data-target="" ></span>
                                                </c:if>
                                                <c:if test="${table.isBill == 1}">
                                                    <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${lockUnlockBooking == 1}">
                                                <span class="glyphicon glyphicon-remove deleteicon" ></span>
                                            </c:if>
                                            <c:if test="${lockUnlockBooking == 2}">
                                                <span class="glyphicon glyphicon-remove deleteicon"   onclick="getCouponCheck('${table.id}',' ${table.product.code}');" data-toggle="modal" data-target="" ></span>
                                            </c:if>
                                        </c:if>
                                        
                                    </center>
                                </td>
                            </tr>                    
                        </c:forEach>
                    </tbody>
                </table>        
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Summary</h3>
                </div>
                <div class="panel-body">
                    <form role="form" id="OtherForm" method="post" action="Other.smi" class="form-horizontal">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-sm-3   control-label" for="codeProduct">Total Cost</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control money" id="Totalcost" disabled  name="Totalcost" value="${totalcost}" >  
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" for="nameProduct">Total Price</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control money" id="Totalprice" disabled name="Totalprice" value="${totalprice}" >  
                                    </div>
                                </div>
                            </div>
                        </div>    
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" for="nameProduct">Amount</label>
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control money" id="Amount" disabled name="Amount" value="${amount}">  
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-sm-3 control-label" for="nameProduct">Mark up</label>
                                    <div class="col-sm-6">                                       
                                        <fmt:parseNumber var="markupShow" type="number" value="${markup}" />
                                        <input type="text" class="form-control text-right" id="markup" disabled name="markup" value="${markupShow}%">
                                    </div>
                                </div>
                            </div>
                        </div>    
                        <input type="hidden" class="form-control" id="OtherID"   name="OtherID"  >     
                        <input type="hidden" class="form-control" id="action"   name="action"  > 
                        <input type="hidden" class="form-control" id="callPageFrom"   name="callPageFrom"  >  
                        <input type="hidden" class="form-control" id="referenceNo"   name="referenceNo"  value="${param.referenceNo}" >     
                    </form>



                </div>

            </div>

        </div>  




    </div>

</div>
                                    
<div class="modal fade duplicatemodal" id="DelOther" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Booking other </h4>
            </div>
            <div class="modal-body" id="delCode">
                
            </div>
            <div class="modal-footer">
                <button type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      

<div class="modal fade duplicatemodal" id="EnableOther" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Booking other </h4>
            </div>
            <div class="modal-body" id="enableCode">
                
            </div>
            <div class="modal-footer">
                <button type="button" onclick="Enable()" class="btn btn-success">Enable</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->      
