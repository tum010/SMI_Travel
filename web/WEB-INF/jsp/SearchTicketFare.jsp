<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/SearchTicketFare.js"></script> 
<link href="css/jquery-ui.css" rel="stylesheet">

<section class="content-header" >
    <h1>
        Checking - Air Ticket
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Checking</a></li>          
        <li class="active"><a href="#">Search Ticket Fare</a></li>
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
                    <h4><b>Search Ticket Fare</b></h4>
                </div>
            </div>
            <hr/>
            
            <form action="SearchTicketFare.smi" method="post" id="SearchTicketFareForm" name="SearchTicketFareForm" role="form">
                <div class="panel panel-default">
                    <div class="panel-body"  style="width: 100%">
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Ticket&nbsp;Type&nbsp;:</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <select id="inputTicketType" name="inputTicketType" class="form-control selectize">
                                    <option value="">---Ticket Type---</option>

                                </select>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Ticket Routing :</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <select id="inputTicketRouting" name="inputTicketRouting" class="form-control selectize">
                                    <option value="">---Ticket Routing---</option>

                                </select>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Airline&nbsp;:</label>
                            </div>
                            <div class="col-xs-1" style="width: 190px">
                                <select id="inputAirline" name="inputAirline" class="form-control selectize">
                                    <option value="">---Airline---</option>

                                </select>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Ticket No :</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <div class="input-group">
                                    <input id="ticketNo" name="ticketNo" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 150px">
                                <label class="control-label text-right">Issue Date :</label>
                            </div>
                            <div class="col-xs-1"  style="width: 200px">
                                <div class='input-group date'>
                                    <input id="inputIssueDate" name="inputIssueDate"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-12 form-group">
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Invoice No :</label>
                            </div>
                            <div class="col-xs-1 form-group" style="width: 200px">
                                <div class="input-group">
                                    <input id="invoiceNo" name="invoiceNo" type="text" class="form-control" value="">
                                </div>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 150px">
                                <label class="control-label text-right">Department :</label>
                            </div>
                            <div class="col-xs-1" style="width: 200px">
                                <select id="inputDepartment" name="inputDepartment" class="form-control selectize">
                                    <option value="">---Department---</option>

                                </select>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 150px">
                            </div>
                            <div class="col-xs-1 text-left" style="width: 190px">
                                <button style="height:34px" type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="searchAction();" class="btn btn-primary btn-sm">
                                    <i class="fa fa-search"></i>&nbsp;Search</button></button>
                            </div>
                        </div>
                    </div>
                </div>
                <!--Table-->
                <div class="row">
                    <div class="col-md-12 ">
                        <table id="TicketFareList" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr class="datatable-header" >
                                    <th style="width:5%;">Type</th>
                                    <th style="width:7%;">Buy</th>
                                    <th style="width:7%;">Airline</th>
                                    <th style="width:10%;">Ticket No</th>
                                    <th style="width:10%;">Issue Date</th>
                                    <th style="width:10%;">Inv No</th>
                                    <th style="width:10%;">Department</th>
                                    <th style="width:7%;">Fare</th>
                                    <th style="width:7%;">Tax</th>
                                    <th style="width:7%;">T Com</th>
                                    <th style="width:7%;">A Com</th>
                                    <th style="width:7%;">Diff Vat</th>
                                    <th style="width:10%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>      
                    </div>
                </div>
            </form>
        </div> 
    </div> 
</div>

<!--Script-->       
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        
        var table = $('#TicketFareList').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "aaSorting": [[ 0, "desc" ]]
        });

        $('#TicketFareList tbody').on('click', 'tr', function() {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#TicketFareList tbody tr.row_selected').attr("id"));
            }
        });
        
        $('.date').datetimepicker();
    
    });
    
</script>
