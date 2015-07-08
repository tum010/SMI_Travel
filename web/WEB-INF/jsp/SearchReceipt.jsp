<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/SearchReceipt.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="content-header" >
    <h1>
        Finance & Cashier - Receipt
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Finance & Cashier</a></li>          
        <li class="active"><a href="#">Search Receipt</a></li>
    </ol>
</section>

<div style="padding-top: 15px;padding-right: 0px "ng-app=""> 
    <div class="row">
       
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/ReceiptMenu.html'"></div>
        </div>
        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6" style="padding-right: 15px">
                    <h4><b>Search Receipt</b></h4>
                </div>
            </div>
            <hr/>
            
            <form action="SearchReceipt.smi" method="post" id="SearchReceiptForm" name="SearchReceiptForm" role="form">
                <div class="col-xs-12 form-group">
                    <div class="col-xs-1 text-right"  style="width: 80px">
                        <label class="control-label text-right">From </label>
                    </div>
                    <div class="col-xs-1"  style="width: 200px">
                        <div class='input-group date'>
                            <input id="inputFromDate" name="inputFromDate"  type="text" 
                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="col-xs-1 text-right"  style="width: 80px">
                        <label class="control-label text-right">To </label>
                    </div>
                    <div class="col-xs-1"  style="width: 200px">
                        <div class='input-group date'>
                            <input id="inputToDate" name="inputToDate"  type="text" 
                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                            <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                    <div class="col-xs-1 text-right" style="width: 140px">
                        <label class="control-label text-right">Department </label>
                    </div>
                    <div class="col-xs-1" style="width: 200px">
                        <select id="inputDepartment" name="inputDepartment" class="form-control selectize">
                            <option value="">---Department---</option>

                        </select>
                    </div>
                    <div class="col-xs-1 text-left" >
                        <button type="submit" id="ButtonShow" name="ButtonShow" onclick="showReceiptListTable()" style="height:34px" class="btn btn-primary btn-sm"><i class="fa fa-search"></i>&nbsp;Show </button>
                    </div>
                    <div class="col-xs-1 text-left" >
                        <button type="submit" id="ButtonPrint" name="ButtonPrint" class="btn btn-default"><i class="glyphicon glyphicon-print"></i> Print </button>
                    </div>
                </div>
            </form>
            <!--Table-->
            <div class="row">
                <div class="col-md-12 ">
                    <table id="ReceiptListTable" class="display hidden active" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header" >
                                <th style="width:7%;">Receive No</th>
                                <th style="width:7%;">Date</th>
                                <th style="width:7%;">To</th>
                                <th style="width:15%;">Name</th>
                                <th style="width:10%;">Invoice No</th>
                                <th style="width:10%;">Amount</th>
                                <th style="width:7%;">Term Pay</th>
                                <th style="width:7%;">Department</th>
                                <th style="width:7%;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><center>111111</center></td>
                                <td><center>2015-01-02</center></td>
                                <td><center>333333</center></td>
                                <td><center>444444</center></td>
                                <td><center>555555</center></td>
                                <td><center>666666</center></td>
                                <td><center>777777</center></td>
                                <td><center>888888</center></td>
                                <td>
                                    <center>
                                        <a href="Receipt.smi"><span class="glyphicon glyphicon-edit editicon"></span></a>
                                    </center>
                                </td>
                            </tr>
                        </tbody>
                    </table>      
                </div>
            </div>
    
        </div> 
    </div> 
</div>

<!--Script-->       
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $("#ReceiptListTable").removeClass('hidden');
        
        var table = $('#ReceiptListTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "aaSorting": [[ 0, "desc" ]]
        });
            
        $('.date').datetimepicker();
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        $('#ButtonShow').on('click', function() {
            $("#ReceiptListTable").removeClass('hidden');
            var table = $('#ReceiptListTable').dataTable({bJQueryUI: true,
                "sPaginationType": "full_numbers",
                "bAutoWidth": false,
                "bFilter": false,
                "aaSorting": [[ 0, "desc" ]]
            });
        });
        
        $('#ButtonPrint').on('click', function() {
            alert('Print');
        });
        


    });

    
</script>
