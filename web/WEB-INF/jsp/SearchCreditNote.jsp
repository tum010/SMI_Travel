<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/SearchCreditNote.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">
<section class="content-header" >
    <h1>
        Finance & Cashier - Credit Note
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Search Credit Note</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app="">
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/FinanceAndCashier/CreditNoteMainMenu.html'"></div>
        </div>
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6 " style="padding-right: 15px">
                    <h4><b>Search Credit Note</b></h4>
                </div>            
            </div>
            <hr/>
            <form action="SearchCreditNote.smi" method="post" id="SearchCreditNoteForm" name="SearchCreditNoteForm" role="form">
                <div class="panel panel-default">
                    <div class="panel-body"  style="width: 100%">
                        <div class="col-xs-12 form-group" style="padding-top: 15px">
                            <div class="col-xs-1 text-right"  style="width: 70px">
                                <label class="control-label text-right">Form<font style="color: red">*</font>&nbsp;</label>
                            </div>
                            <div class="col-xs-1"  style="width: 170px">
                                <div class='input-group date'>
                                    <input id="dateForm" name="dateForm"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['dateForm']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right"  style="width: 70px">
                                <label class="control-label text-right">To<font style="color: red">*</font>&nbsp;</label>
                            </div>
                            <div class="col-xs-1"  style="width: 170px">
                                <div class='input-group date'>
                                    <input id="dateTo" name="dateTo"  type="text" 
                                       class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['dateTo']}">
                                    <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-xs-1 text-right" style="width: 80px">
                                <label class="control-label text-right">CN No.</label>
                            </div>
                            <div class="col-xs-1" style="width: 170px">
                                <div class="input-group">
                                    <input id="cnNo" name="cnNo" type="text" class="form-control" value="${ticketFare.ticketNo}">
                                </div>
                            </div>
                            <div class="col-md-1 text-right " style="padding: 0px 0px 0px 0px ; width: 160px">
                                <button type="submit"  id="ButtonSearch"  name="ButtonSearch" onclick="" class="btn btn-primary btn-primary ">
                                    <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                                </button>                                
                            </div>
                            <div class="col-md-1 text-right " style="padding: 0px 0px 0px 0px">
                                <button type="button" onclick="printVoucher('');" class="btn btn-default">
                                    <span id="SpanPrint" class="glyphicon glyphicon-print"></span> Print
                                </button>                                
                            </div>
                        </div>
                    </div>
                </div>
                
                <!--Table-->
                <div class="row">
                    <div class="col-md-12 ">
                        <table id="CreditNoteTable" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr class="datatable-header" >
                                    <th style="width:10%;">CN No.</th>
                                    <th style="width:10%;">Date</th>
                                    <th style="width:30%;">Name</th>
                                    <th style="width:20%;">AP Code</th>
                                    <th style="width:10%;">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <tr>
                                    <td align="center">1</td>
                                    <td align="center">2015-07-21</td>
                                    <td align="center">Test 1</td>
                                    <td align="center">12345678</td>
                                    <td> 
                                        <center> 
                                            <a  href="SearchCreditNote.smi?action=edit">
                                                <span class="glyphicon glyphicon-edit editicon"  ></span>
                                            </a>
                                        </center>
                                    </td>  
                                </tr>
                                <%--<c:forEach var="table" items="${dataList}" varStatus="dataStatus">--%>
<!--                                    <tr>
                                        <td align="center"> <c:out value="${table.type}" /></td>
                                        <td align="center"> <c:out value="${table.buy}" /></td>
                                        <td align="center"> <c:out value="${table.airline}" /></td>
                                        <td align="center"> <c:out value="${table.ticketNo}" /></td>
                                        <td> 
                                            <center> 
                                                <a  href="SearchCreditNote.smi?ticketId=${table.id}&action=edit">
                                                    <span class="glyphicon glyphicon-edit editicon"  ></span>
                                                </a>

                                            </center>
                                        </td>                                    
                                    </tr>-->
                                <%--</c:forEach>--%>
                            </tbody>
                        </table>      
                    </div>
                </div>                
            </form>
         </div>
     </div>
</div> 

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $('.date').datetimepicker();
        var table = $('#CreditNoteTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false
        });

        $('#CreditNoteTable tbody').on('click', 'tr', function() {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#CreditNoteTable tbody tr.row_selected').attr("id"));
            }
        });
    });

function searchAction() {
    var action = document.getElementById('action');
    action.value = 'search';
    var dateForm = document.getElementById('dateForm');
    dateForm.value = $("#dateForm").val();
    var dateTo = document.getElementById('dateTo');
    dateTo.value = $("#dateTo").val();
    var cnNo = document.getElementById('cnNo');
    cnNo.value = $("#cnNo").val();
    document.getElementById('SearchCreditNoteForm').submit();
}
</script>
