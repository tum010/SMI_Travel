<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/refundAirline.js"></script> 
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">

<section class="content-header" >
    <h1>
        Checking - Air Ticket
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Checking</a></li>          
        <li class="active"><a href="#">Refund Airline</a></li>
    </ol>
</section>

<div style="padding-top: 15px;padding-right: 0px "ng-app=""> 
    <div class="row">
       
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Checking/CheckingAirTicketMenu.html'"></div>
        </div>
        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6" style="padding-right: 15px">
                    <h4><b>Refund Airline</b></h4>
                </div>
            </div>
            <hr/>
            
            <form  method="post" id="RefundAirlineForm" name="RefundAirlineForm" role="form">
                <div id="alertSuccess"  style="" class="alert alert-success alert-dismissible" role="alert" <c:if test="${successStatus != true}">hidden="true"</c:if> >
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong id="alertTextSuccess">Save Success!</strong> 
                </div>
                <div id="alertFail"  style="" class="alert alert-danger alert-dismissible" role="alert"  <c:if test="${failStatus != true}">hidden="true"</c:if>>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong id="alertTextFail">${failMessage}</strong> 
                </div>
                <input type="hidden" name="action" id="action" value="search">
                <input type="hidden" name="counter" id="counter" >
                <input type="hidden" name="refundId" id="refundId" value="${refundAirline.id}">
                <div class="row">
                    <div class="col-xs-6 form-group">
                        <div class="col-xs-1 text-right" style="width: 140px">
                            <label class="control-label text-right">Refund No</label>
                        </div>
                        <div class="col-xs-1" style="width: 290px">
                            <input id="refundNo" name="refundNo" type="text" class="form-control" value="${refundAirline.refundNo}">
                        </div>
                        <div class="col-xs-1 text-right" style="width: 100px">
                            <button style="height:34px" type="button" id="ButtonSearch" name="ButtonSearch" onclick="searchTicketNo();" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Search</button>
                        </div>
                    </div>
                    <div class="col-xs-4 form-group">
                        <div class="col-xs-1 text-right"  style="width: 140px">
                            <label class="control-label text-right">Refund Date </label>
                        </div>
                        <div class="col-xs-1"  style="width: 200px">
                            <div class='input-group date'>
                                <input id="inputRefundDate" name="refundDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${refundAirline.refundDate}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6 form-group">
                        <div class="col-xs-1 text-right"  style="width: 140px">
                            <label class="control-label text-right">Refund Agent <font style="color: red">*</font> </label>
                        </div>
                        <div class="col-xs-1"  style="width: 150px">
                            <div class="input-group" id="refundAgentCodeValidate">
                                    <input type="hidden" class="form-control" id="refundAgentId" name="agentId" value="${refundAirline.agent.id}" />
                                <input type="text" class="form-control" id="refundAgentCode" name="agentCode" value="${refundAirline.agent.code}" />
                                <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#RefundAgentModal">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-xs-1 text-left" style="width: 250px">
                            <input type="text" class="form-control" id="refundAgentName" name="agentName" value="${refundAirline.agent.name}" readonly=""
                                data-bv-notempty="true" data-bv-notempty-message="agent empty !">                           
                        </div>
                    </div>  
                    <div class="col-xs-4 form-group">
                        <div class="col-xs-1 text-right" style="width: 140px">
                            <label class="control-label text-right">Refund By </label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <div class="input-group">
                                <input id="refundBy" name="refundBy" type="text" class="form-control" value="${refundAirline.refundBy}" maxlength="5">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right"  style="width: 140px">
                            <label class="control-label text-right">Remark </label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <div class="input-group">                                    
                                <textarea rows="3" class="form-control" id="remark" name="remark" maxlength="255" style="width: 228%">${refundAirline.remark}</textarea>  
                            </div>
                        </div>
                    </div>
                </div>
                                
            <!--Table-->
            <div class="row">
                <div class="col-md-12 ">
                    <table id="RefundAirlineTable" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header" >
                                <th style="width:10%;">Ticket No</th>
                                <th style="width:25%;">Sector Refund</th>
                                <th style="width:10%;" >Receive</th>
                                <th style="width:9%;" >Pay</th>
                                <th style="width:9%;" >Profit</th>
                                <th style="width:9%;" >Air Com</th>
                                <th style="width:9%;" >Agent Com</th>
                                <th style="width:14%;" >Pay Date</th>
                                <th style="width:5%;" rowspan="2" >Action</th>
                            </tr>
                            <tr class="datatable-header" >
                                <th>Ticket Date</th>
                                <th>Sector issue</th>
                                <th>Total</th>
                                <th>Department</th>
                                <th colspan="3">Passenger</th>
                                <th >Receive Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="detail" items="${refundAirline.refundAirticketDetails}" varStatus="varRefundAirline">
                                <c:set var="index" value="${varRefundAirline.index + 1}"></c:set>
                                <tr row="${index}">
                            <input type="hidden" name="detailId${index}" id="detailId${index}" colName="detailId" value="${detail.id}">
                            <input type="hidden" id="ticketId${index}" name="ticketId${index}" colName="ticketId" value="${detail.airticketPassenger.id}">
                            <td style="text-align:center"> <input id="ticketNo${index}" name="ticketNo${index}" colName="ticketNo" type="text" class="form-control" value="${detail.ticketFareAirline["TicketNo"]}"></td>
                            <td style="text-align:center"> <input id="refund${index}" name="refund${index}" colName="refund" type="text" class="form-control" value="${detail.sectorRefund}" onfocusout="checkRefund(this)"></td>
                            <td style="text-align:center"> <input id="Receive${index}" name="receive${index}" colName="receive" type="text" class="form-control text-right decimal" style="text-align: right" value="${detail.receiveAirline}"></td>
                            <td style="text-align:center"> <input id="Pay${index}" name="pay${index}" colName="pay" type="text" class="form-control text-right decimal" style="text-align: right" value="${detail.payCustomer}"></td>
                            <td style="text-align:center"> <input id="Profit${index}" name="profit${index}" colName="profit" type="text" class="form-control text-right decimal" style="text-align: right" value="${detail.profit}"></td>
                            <td style="text-align:center"> <input id="AirCom${index}" name="airCom${index}" colName="airCom" type="text" class="form-control text-right decimal" style="text-align: right" value="${detail.airComission}"></td>
                            <td style="text-align:center"> <input id="AgentCom${index}" name="agentCom${index}" colName="agentCom" type="text" class="form-control text-right decimal" style="text-align: right" value="${detail.agentComission}"></td>
                            <td> 
                                <div class="input-group daydatepicker" id="daydatepicker-0" style="padding-left: 0px">
                                    <input style="width: 100%" type="text" class="form-control" id="paydate${index}" name="paydate${index}" colName="paydate" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${detail.expenseDate}">
                                    <span class="input-group-addon" style="padding : 1px 10px;"><span class="glyphicon-calendar glyphicon"></span></span>
                                </div>
                            </td>       

                            <td rowspan='2' class="text-center">
                                <a id="ButtonRemove${varRefundAirline.count}" data-toggle="modal" data-target="#DeleteRefundAirline" onclick="setDeletRow(this)">
                                    <i id="IRemove${varRefundAirline.count}" class="glyphicon glyphicon-remove deleteicon"></i>
                                </a>
                            </td>
                            </tr>
                            <tr row="${varRefundAirline.index + 1}">
                                <td  style="text-align:center"><span id="ticketDate" name="ticketDate">${detail.ticketFareAirline["TicketDate"]}</span></td>
                                <td ><span id="sectorIssue${index}">${detail.ticketFareAirline["Sector"]}</span></td>
                                <td style="text-align:center"><span id="total">${detail.ticketFareAirline["Total"]}</span></td>
                                <td ><span id="department">${detail.ticketFareAirline["Dept"]}</span></td>       
                                <td colspan='3'><span id="passsenger" name="passsenger">${detail.ticketFareAirline["Passenger"]}</span></td>
                                <td> 
                                    <div class="input-group daydatepicker" id="daydatepicker-0" style="padding-left: 0px">
                                        <input style="width: 100%" type="text" class="form-control" id="receivedate${index}" name="receivedate${index}"  colName="receivedate" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${detail.receiveDate}">
                                        <span class="input-group-addon" style="padding : 1px 10px;"><span class="glyphicon-calendar glyphicon"></span></span>
                                    </div>
                                </td>     

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>      
                </div>
            </div>        
                        
            <div class="col-xs-12 text-center" style="padding-top: 10px">
                    <button  id="buttonSave" name="ButtonSave" class="btn btn-success" value="save"><i class="fa fa-save"></i> Save</button>
                    <button type="submit" id="ButtonSaveAndNew" name="ButtonSaveAndNew" class="btn btn-success"><i class="fa fa-save"></i> Save &amp; New</button>
            </div>            
            </form>
        </div>
    </div>
</div>
<!--Modal  Agent-->
<div class="modal fade" id="RefundAgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Refund Agent</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="RefundAgentTable">
                    <thead class="datatable-header">                     
                        <tr>
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                            <th class="hidden">Fax</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        agent = [];
                    </script>
                    <c:forEach var="a" items="${agent}">
                        <tr>
                            <td class="agent-id hidden">${a.id}</td>
                            <td class="agent-user">${a.code}</td>
                            <td class="agent-name">${a.name}</td>
                            <td class="agent-addr hidden">${a.address}</td>
                            <td class="agent-tel hidden">${a.tel}</td>
                            <td class="agent-fax hidden">${a.fax}</td>
                        </tr>
                        <script>
                            agent.push({id: "${a.id}", code: "${a.code}", name: "${a.name}", 
                                        address: "${a.address}", tel: "${a.tel}", fax: "${a.fax}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="RefundAgentModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<div class="modal fade" id="DeleteRefundAirline" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Refund Airline</h4>
            </div> 
            <div class="modal-body" id="delCode">
            </div>
            <div class="modal-footer">
                <button type="button" onclick="deleteRowRefundAirlineList()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /Delete Hotel modal -->

<div  class="hide" id="">
    <table id="tempRow" class="display" cellspacing="0" width="100%">
        <tbody>
            <tr>
        <input type="hidden" name="detailId" id="detailId" colName="detailId" value="">
        <input type="hidden" id="ticketId" name="ticketId" colName="ticketId" value="">
        <td style="text-align:center"> <input id="ticketNo" name="ticketNo" colName="ticketNo" type="text" class="form-control" value=""></td>
        <td style="text-align:center"> <input id="refund" name="refund" colName="refund" type="text" maxlength="255" class="form-control" value="" onfocusout="checkRefund(this)"></td>
        <td style="text-align:center"> <input id="receive" name="receive" colName="receive" type="text" class="form-control text-right decimal" value=""></td>
        <td style="text-align:center"> <input id="pay" name="pay" type="text" colName="pay" class="form-control text-right decimal"></td>
        <td style="text-align:center"> <input id="profit" name="profit" colName="profit" type="text" class="form-control text-right decimal"></td>
        <td style="text-align:center"> <input id="airCom" name="airCom" colName="airCom" type="text" class="form-control text-right decimal"></td>
        <td style="text-align:center"> <input id="agentCom" name="agentCom" colName="agentCom" type="text" class="form-control text-right decimal"></td>
        <td> 
            <div class="input-group daydatepicker" id="daydatepicker-0" style="padding-left: 0px">
                <input style="width: 100%" type="text" class="form-control" id="paydate" name="paydate" colName="paydate" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD">
                <span class="input-group-addon" style="padding : 1px 10px;"><span class="glyphicon-calendar glyphicon"></span></span>
            </div>
        </td>
        <td rowspan='2' class="text-center">
            <a id="ButtonRemove${varRefundAirline.count}" data-toggle="modal" data-target="#DeleteRefundAirline" onclick="setDeletRow(this)">
                <i id="IRemove${varRefundAirline.count}" class="glyphicon glyphicon-remove deleteicon"></i>
            </a>
        </td>
        </tr>
        <tr>
            <td  style="text-align:center"><span id="ticketDate" colName="ticketDate"></span></td>
            <td ><span id="sectorIssue" colName="sectorIssue"></span></td>
            <td style="text-align:center"><span id="total" colName="total"></span></td>
            <td ><span id="department" colName="department"></span></td>       
            <td colspan='3'><span id="passsenger" colName="passsenger"></span></td>
            <td> 
                <div class="input-group daydatepicker" id="daydatepicker-0" style="padding-left: 0px">
                    <input style="width: 100%" type="text" class="form-control" id="receivedate" name="receivedate" colName="receivedate" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD">
                    <span class="input-group-addon" style="padding : 1px 10px;"><span class="glyphicon-calendar glyphicon"></span></span>
                </div>
            </td>                              
        </tr>
        </tbody>
    </table>
</div>
<!--Script-->       
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
   
        $('.date').datetimepicker();
        
        $(".daydatepicker").datetimepicker({
            pickTime: false   
        }); 
        var RefundAgentTable = $('#RefundAgentTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });
        
        var rowRefund = $("#RefundAirlineTable tr").length;
     //   RefundAirlineTableAddRow(rowRefund);
        
//        $("#RefundAirlineTable").on("keyup", function () {
//            var rowAll = $("#RefundAirlineTable tr").length;
//            $("td").keyup(function () {
//                if ($(this).find("input").val() !== '') {
//                    var colIndex = $(this).parent().children().index($(this));
//                    var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
//                    rowAll = $("#RefundAirlineTable tr").length;
//                    if (rowIndex == rowAll) {
//                        RefundAirlineTableAddRow(rowAll);
//                    }
//                }
//            });
//        });
        
 
    });
    
// Refund Table add row
function RefundAirlineTableAddRow(row) {
    if (!row) {
        row = 1;
    }
    //Total	Receive	Pay	Profit	Com	Date	Action
    $("#RefundAirlineTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input id="refundId' + row + '" name="refundId' + row + '"  type="text">' +
            '<td class="hidden"><input id="refundCount' + row + '" name="refundCount"  type="text" value="' + row + '">' +
            '<td><input id="ticketNo-' + row + '" name="ticketNo-' + row + '"  type="text" class="form-control" maxlength="20"></td>' +
            '<td><div class="input-group daydatepicker" id="daydatepicker-'+row+'" style="padding-left: 0px"><input style="width: 100%" type="text" class="form-control"  id="ticketDate-'+row+'" name="ticketDate-'+row+'" data-date-format="YYYY-MM-DD"/><span class="input-group-addon" style="padding : 1px 10px;"><span class="glyphicon glyphicon-calendar"></span></span></div></td>' +
            '<td><input id="dept-' + row + '" name="dept-' + row + '"  type="text" class="form-control" maxlength="20"></td>' +
            '<td><input id="passenger-' + row + '" name="passenger-' + row + '"  type="text" class="form-control"></td>' +
            '<td><input id="sectorIssue-' + row + '" name="sectorIssue-' + row + '"  type="text" class="form-control"></td>' +
            '<td><input id="sectorRefund-' + row + '" name="sectorRefund-' + row + '"  type="text" class="form-control" maxlength="255"></td>' +
            '<td><input id="total-' + row + '" name="total-' + row + '"  type="text" class="form-control" maxlength="20"></td>' +
            '<td><input id="receive-' + row + '" name="receive-' + row + '"  type="text" class="form-control"></td>' +
            '<td><input id="pay-' + row + '" name="pay-' + row + '"  type="text" class="form-control"></td>' +
            '<td><input id="profit-' + row + '" name="profit-' + row + '"  type="text" class="form-control"></td>' +
            '<td><input id="com-' + row + '" name="com-' + row + '"  type="text" class="form-control"></td>' +
            '<td><div class="input-group daydatepicker" id="daydatepicker-'+row+'" style="padding-left: 0px"><input style="width: 100%" type="text" class="form-control"  id="inputDate-'+row+'" name="inputDate-'+row+'" data-date-format="YYYY-MM-DD"/><span class="input-group-addon" style="padding : 1px 10px;"><span class="glyphicon glyphicon-calendar"></span></span></div></td>' +
            '<td class="text-center">' +
            '<a class="remCF" onclick="ConfirmDelete(\'1\', \'\', \''+row+'\')">  '+
            '<span  id="SpanRemove'+row+'"  class="glyphicon glyphicon-remove deleteicon"></span></a></td>'+   
            '</tr>'
            );
    $("input[name=countRefund]").val(row);

}

function ConfirmDelete(rowType,itineraryid,Ccount) {
    $("#rowType").val(rowType);
    $("#Itiid").val(itineraryid);
    $("#cCount").val(Ccount);
    var deleteType;
    if (rowType === '1'){
        deleteType = 'Refund Airline ?';
    }
    $("#delCode").text('are you sure delete ' + deleteType);
    $('#DeleteRefundAirline').modal('show');
}

function deleteRow() {
    var rowType = $("#rowType").val();
    var ItiId = $("#Itiid").val();
    var cCount = $("#cCount").val();
    if(rowType === '3'){
        deleteRefund(ItiId,cCount);
    }
    $('#DeleteRefundAirline').modal('hide');
}
//
//function deleteRefund(refundId,count){
////    alert("Delete City");
//    if(refundId === ''){
//            var countrow=0;
////            alert($("#row-passenger-2-name").parent().parent().html());
//            $("#row-city-" + count + "-name").parent().parent().remove();
//            var rowAll = $("#RefundAirlineTable tr").length;
//            if (rowAll < 2) {
////                console.log("show button tr_FormulaAddRow");
//                $("#tr_CityAddRow").removeClass("hide");
//                $("#tr_CityAddRow").addClass("show");
//            }            
//     //       $("#counterItinerary").val(parseInt($("#counterItinerary").val()) -1);
//            $('#City tr:gt(0) ').each(function() {
//                $(this).find('td:eq(1)').html(countrow) ; 
//                countrow = countrow+1;
//            });   
//    }else{
//     $.ajax({
//        url: 'MPackageDetail.smi?action=deleteCity',
//        type: 'get',
//        data: {refundId refundId},
//        success: function () { 
//            var countrow=1;
//            $("#row-packcity-" + count + "-id").parent().parent().remove();
//            var rowAll = $("#City tr").length;
//            if (rowAll < 2) {
//                console.log("show button tr_FormulaAddRow");
//                $("#tr_CityAddRow").removeClass("hide");
//                $("#tr_CityAddRow").addClass("show");
//            }            
//     //       $("#counterItinerary").val(parseInt($("#counterItinerary").val()) -1);
//            $('#City tr:gt(0) ').each(function() {
//                $(this).find('td:eq(1)').html(countrow) ; 
//                countrow = countrow+1;
//            });
//        },
//        error: function () {
//            console.log("error");
//            result =0;
//        }
//    });       
//    }
//}
</script>
