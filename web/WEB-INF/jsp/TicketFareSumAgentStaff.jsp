<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="airlineList" value="${requestScope['airlineList']}" />
<c:set var="airlineCodeList" value="${requestScope['airlineCodeList']}" />
<c:set var="userList" value="${requestScope['userList']}" />
<c:set var="termPayList" value="${requestScope['termPayList']}" />

<section class="content-header"  >
    <h4>
        <b>Report : Air Ticket report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Ticket Fare Summary Agent Staff</a></li>
    </ol>
</section>

<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/AirticketReportMenu.html'"></div>
            </div>

            <div class="form-group">
                <div class="col-md-6">
                    <h3>Ticket Fare Summary Agent Staff</h3>
                </div>
            </div>
            
            <div class="col-md-10" >
                <form action="TicketFareSumAgentStaff.smi" method="post" id="TicketFareSumAgentStaff" name="TicketFareSumAgentStaff" role="form">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Report Type</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="reportType" id="reportType"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="1">Ticket Summary By Staff</option>
                                            <option value="2">Ticket Summary By Agent</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Ticket Type</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="ticketType" id="ticketType"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="B" >BSP</option>
                                            <option value="D" >DOMESTIC</option>
                                            <option value="A" >AGENT</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Ticket Buy</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="ticketBuy" id="ticketBuy" class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="C" >IN</option>
                                            <option value="O" >OUT</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Airline</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="airline" id="airline"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:forEach var="table" items="${airlineList}" >
                                                <c:set var="select" value="" />
                                                <option value="${table.id}" ${select}>${table.code}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Airline Code</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="airlineCode" id="airlineCode"  class="form-control">
                                            
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="fromdatepanel">
                                <label class="col-md-6 control-label text-right">Issue Date From</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date' id='fromdate'>
                                            <input type='text' id="startdate" name="startdate" class="form-control" data-date-format="YYYY-MM-DD"/>
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
                            <div class="form-group" id="todatepanel">
                                <label class="col-md-6 control-label text-right">To</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date' id='todate'>
                                            <input type='text' id="enddate" name="enddate"  class="form-control" data-date-format="YYYY-MM-DD" />
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
                            <div class="form-group" id="frominvdatepanel">
                                <label class="col-md-6 control-label text-right">Invoice Date From</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date' id='frominvdate'>
                                            <input type='text' id="startinvdate" name="startinvdate" class="form-control" data-date-format="YYYY-MM-DD"/>
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
                            <div class="form-group" id="toinvdatepanel">
                                <label class="col-md-6 control-label text-right">To</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date' id='toinvdate'>
                                            <input type='text' id="endinvdate" name="endinvdate"  class="form-control" data-date-format="YYYY-MM-DD" />
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
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Department</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="department" id="department"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <option value="wendy" >wendy</option>
                                            <option value="inbound" >inbound</option>
                                            <option value="outbound" >outbound</option>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Sale By</label>
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
                                <label class="col-md-6 control-label text-right" >Term Pay</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="termPay" id="termPay"  class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:forEach var="table" items="${termPayList}" >
                                                <c:set var="select" value="" />
                                                <option value="${table.id}" ${select}>${table.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="rept"></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <button type="button" id="printbutton"  name="printbutton"  class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
                                    </div>
                                </div>   
                            </div> 
                        </div>
                    </div>
                </form>                
            </div>
        </div>
    </div>
</div>
            <!--onclick="printTicketFareSummary();"-->                    
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
        $('.date').datetimepicker({
        });
        
        $('span').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });
        

        
        $("#TicketFareSumAgentStaff").bootstrapValidator({
            framework: 'bootstrap',
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                startdate: {
                    trigger: 'focus keyup change',
                    validators: {
                        date: {
                            format: 'YYYY-MM-DD',
                            max: 'enddate',
                            message: 'The Issue Date From is not a valid'
                        }
                    }
                },
                enddate: {
                    trigger: 'focus keyup change',
                    validators: {
                        date: {
                            format: 'YYYY-MM-DD',
                            min: 'startdate',
                            message: 'The Issue Date To is not a valid'
                        }
                    }
                },
                startinvdate: {
                    trigger: 'focus keyup change',
                    validators: {
                        date: {
                            format: 'YYYY-MM-DD',
                            max: 'endinvdate',
                            message: 'The Invoice Date From is not a valid'
                        }
                    }
                },
                endinvdate: {
                    trigger: 'focus keyup change',
                    validators: {
                        date: {
                            format: 'YYYY-MM-DD',
                            min: 'startinvdate',
                            message: 'The Invoice Date To is not a valid'
                        }
                    }
                }
            }
        });
        //validate date
        $('#fromdate').datetimepicker().on('dp.change', function (e) {
            $('#TicketFareSumAgentStaff').bootstrapValidator('revalidateField', 'startdate');
            $('#TicketFareSumAgentStaff').bootstrapValidator('revalidateField', 'enddate');
        });
        $('#todate').datetimepicker().on('dp.change', function (e) {
            $('#TicketFareSumAgentStaff').bootstrapValidator('revalidateField', 'startdate');
            $('#TicketFareSumAgentStaff').bootstrapValidator('revalidateField', 'enddate');
        });
	$('#frominvdate').datetimepicker().on('dp.change', function (e) {
            $('#TicketFareSumAgentStaff').bootstrapValidator('revalidateField', 'startinvdate');
            $('#TicketFareSumAgentStaff').bootstrapValidator('revalidateField', 'endinvdate');
        });
        $('#toinvdate').datetimepicker().on('dp.change', function (e) {
            $('#TicketFareSumAgentStaff').bootstrapValidator('revalidateField', 'startinvdate');
            $('#TicketFareSumAgentStaff').bootstrapValidator('revalidateField', 'endinvdate');
        });
        $('#printbutton').click( function() {  
            printTicketFareSummary();
        });

        //Sale By Auto Complete
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
    });
    
    function printTicketFareSummary(){
        var reportType = document.getElementById("reportType").value;
        var ticketType = document.getElementById("ticketType").value;
        var ticketBuy = document.getElementById("ticketBuy").value;
        var airline = document.getElementById("airline").value;
        var airlineCode = document.getElementById("airlineCode").value;
        var issuefrom = document.getElementById("startdate").value;
        var issueto = document.getElementById("enddate").value;
        var invfrom = document.getElementById("startinvdate").value;
        var invto = document.getElementById("endinvdate").value;
        var department = document.getElementById("department").value;
        var salebyUser = document.getElementById("salebyUser").value;
        var termPay = document.getElementById("termPay").value;

        if(reportType == 1){
            window.open("Excel.smi?name=TicketFareSummaryByStaff&ticketType=" + ticketType + "&ticketBuy=" + ticketBuy + "&airline=" + airline + "&airlineCode=" + airlineCode + "&issuedateFrom=" + issuefrom + "&issuedateTo=" + issueto + "&department=" + department + "&staff=" + salebyUser + "&termPay=" + termPay + "&invdateFrom=" + invfrom + "&invdateTo=" + invto);
        }else if(reportType == 2){
            window.open("Excel.smi?name=TicketFareSummaryByAgent&ticketType=" + ticketType + "&ticketBuy=" + ticketBuy + "&airline=" + airline + "&airlineCode=" + airlineCode + "&issuedateFrom=" + issuefrom + "&issuedateTo=" + issueto + "&department=" + department + "&staff=" + salebyUser + "&termPay=" + termPay + "&invdateFrom=" + invfrom + "&invdateTo=" + invto);
        }

    }
</script>
