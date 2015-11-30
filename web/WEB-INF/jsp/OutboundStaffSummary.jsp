<%-- 
    Document   : OutboundStaffSummary
    Created on : Nov 23, 2015, 5:40:20 PM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="userList" value="${requestScope['userList']}" />
<c:set var="currencyList" value="${requestScope['currencyList']}" />
<section class="content-header"  >
    <h4>
        <b>Report : Outbound Staff Summary </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Outbound Staff Summary</a></li>
    </ol>
</section>
<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/OutboundSummaryMenu.html'"></div>
            </div>
            <div class="form-group">
                <div class="col-md-6">
                    <h3>Outbound Staff Summary Report</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form role="form" id="HotelSummaryReportFrom" method="post" class="form-horizontal" >                   
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right"> From <font style="color: red;">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group" id="DateFrom">
                                        <div class='input-group date'>
                                            <input type='text' id="fromdate" name="fromdate" class="form-control" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>            
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="DateTo">
                                <label class="col-md-5 control-label text-right"> To <font style="color: red;">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date'>
                                            <input   type='text' id="todate" name="todate" class="form-control" data-date-format="YYYY-MM-DD"  />
                                            <span class="input-group-addon"><span  class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Sale By</label>
                                <div class="col-md-3 form-group">  
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="salebyId" name="salebyId" value=""/>
                                        <input type="text" class="form-control" id="salebyUser" name="salebyUser" value="" />
                                        <span class="input-group-addon" id="saleby_modal"  data-toggle="modal" data-target="#SaleByModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="salebyName" name="salebyName" value="" readonly="">
                                </div>
                            </div>   
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-5 control-label text-right" >Currency</label>
                                <!--<div class="col-md-5 form-group">-->  
                                    <div class="col-md-5 input-group">
                                        <select class="form-control" name="currency" id="currency">
                                            <option  value="" >---------</option>
                                            <c:forEach var="cur" items="${currencyList}" varStatus="status">                                       
                                                <option  value="${cur.code}" >${cur.code}</option>
                                            </c:forEach>
                                        </select> 
                                    </div>
                                <!--</div>-->
                            </div>   
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label for="depart" class="col-sm-5 control-label text-right">Detail</label>
                                <div class="col-sm-5">
                                    <div class="form-group">
                                        <select id="detail" name="detail"  class="form-control">
                                            <option value="1">Show</option>
                                            <option value="2">Not Show</option>
                                            <!--<option value="Inbound">Inbound</option>-->
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <div class="col-sm-7 text-right">
                                    <button type="button"  class="btn btn-success" onclick="printOutboundStaffSummary();"><span class="glyphicon glyphicon-print" id="btnDownloadAP"></span> Print</button>
                                </div>
                                <div class="col-sm-2 text-left">
                                    <button type="button" onclick="" class="btn btn-warning"><span class="glyphicon glyphicon-print"></span> Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>                
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="SaleByModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Sale By</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="SaleByTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        saleby = [];
                    </script>
                    <c:forEach var="table" items="${userList}">
                        <tr>
                            <td class="saleby-id hidden">${table.id}</td>
                            <td class="saleby-user">${table.username}</td>
                            <td class="saleby-name">${table.name}</td>
                        </tr>
                        <script>
                            saleby.push({id: "${table.id}", username: "${table.username}", name: "${table.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="SaleByModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div> 

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $('.datemask').mask('0000-00-00');
        $('.date').datetimepicker({
            
        });
        
        $('span').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });
//        
//        $('.fromdate').datetimepicker().change(function(){                          
//            checkFromDateField();
//        });
//        $('.todate').datetimepicker().change(function(){                          
//            checkToDateField();
//        });
//        $('.issuefromdate').datetimepicker().change(function(){                          
//            checkIssueFromDateField();
//        });
//        $('.issuetodate').datetimepicker().change(function(){                          
//            checkIssueToDateField();
//        });
        
        $("#SaleByTable tr").on('click', function () {
            var saleby_id = $(this).find(".saleby-id").text();
            var saleby_user = $(this).find(".saleby-user").text();
            var saleby_name = $(this).find(".saleby-name").text();
            $("#salebyId").val(saleby_id);
            $("#salebyUser").val(saleby_user);
            $("#salebyName").val(saleby_name);
            $("#SaleByModal").modal('hide');
        });

        var salebyuser = [];
        $.each(saleby, function (key, value) {
            salebyuser.push(value.username);
            salebyuser.push(value.name);
        });

        $("#salebyUser").autocomplete({
            source: salebyuser,
            close:function( event, ui ) {
               $("#salebyUser").trigger('keyup');
            }
        });

        $("#salebyUser").on('keyup',function(){
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var username = this.value.toUpperCase();
            var name = this.value.toUpperCase();
           // console.log("Name :"+ name);
            $("#salebyId,#salebyName").val(null);
            $.each(saleby, function (key, value) {
                if (value.username.toUpperCase() === username ) {  
                    $("#salebyId").val(value.id);
                    $("#salebyUser").val(value.username);
                    $("#salebyName").val(value.name);
                }
                else if(value.name.toUpperCase() === name){
                    $("#salebyUser").val(value.username);
                    $("#salebyId").val(value.id);
                    $("#salebyName").val(value.name);
                }
            }); 
        }); 

        $('#SaleByTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });

        $('#SaleByTable tbody').on('click', 'tr', function () {
            $(this).addClass('row_selected').siblings().removeClass('row_selected');
        });
        
//        $('#printbutton').click( function() {  
//            printTicketSummaryCostIncome();
//        });
    });
    
    function printOutboundStaffSummary(){
        var form = $("#fromdate").val();
        var to = $("#todate").val();
        var saleby = $("#salebyUser").val();
        var currency = $("#currency").val();
        var detail = $("#detail").val();
        
        
//        alert(form+"+++++"+to+"+++++"+saleby);
    }
   
</script>