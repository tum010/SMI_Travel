<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="listType" value="${requestScope['listType']}" />
<c:set var="listAr" value="${requestScope['listAr']}" />
<section class="content-header" >
    <h1>
        Nirvana Interface
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Nirvana Interface</a></li>          
        <li class="active"><a href="#"></a>AR Monitor</li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app="">
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/Accounting/NirvanaInterfaceMenu.html'"></div>
    </div>
    <div class="col-sm-10">
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6 " style="padding-right: 15px">
                <h4><b>AR Monitor</b></h4>                  
            </div>
            <div class="col-xs-12 form-group"><hr/></div>
        </div>
        <form action="ARMonitor.smi" method="post" id="arMonitorForm" role="form" autocomplete="off">
            <input type="hidden" value="searchAr" id="action" name="action">
            <div class="col-xs-12">
                <div class="col-xs-1 text-left" style="width: 120px">
                    <label class="control-label" for="">Invoice Type</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <select class="form-control" id="department" name="department">
                        <option value="">--Select--</option>
                        <option value="Wendy">Wendy </option>
                        <option value="Outbound">Outbound </option>
                        <option value="Inbound">Inbound </option>
                    </select>
                </div>
                <!--<div class="col-xs-1" style="width: 50px"></div>-->
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">Department </lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <select class="form-control" id="invoiceType" name="invoiceType">
                        <option value="">--Select--</option>
                        <option value="V">Vat </option>
                        <option value="N">No Vat </option>
                        <option value="T">Temp </option>
                    </select>
                </div>
                <div class="col-xs-1" style="width: 50px"></div>
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">Type</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <select class="form-control" id="arType" name="arType">
                        <option value="">--Select--</option>
                        <c:forEach var="type" items="${listType}" varStatus="count">
                            <option value="${type.id}">${type.name} </option>
                        </c:forEach>
                    </select>
                </div>
            </div><br><br>
            <div class="col-xs-12"></div>
            <div class="col-xs-12"> <!--Row 2 -->
                <div class="col-xs-1 text-left" style="width: 120px">
                    <label class="control-label" for="">From</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <div class='input-group date' id='InputFromDate'>
                    <c:if test='${taxInvoice.taxInvDate != null}'>
                        <input id="arFromDate" name="arFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${taxInvoice.taxInvDate == null}'>
                        <input id="arFromDate" name="arFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>  
                </div>
                <!--<div class="col-xs-1" style="width: 50px"></div>-->
                <div class="col-xs-1 text-left">
                    <label class="control-label">To</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <div class='input-group date' id='InputToDate'>
                    <c:if test='${taxInvoice.taxInvDate != null}'>
                        <input id="arToDate" name="arToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${taxInvoice.taxInvDate == null}'>
                        <input id="arToDate" name="arToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>   
                </div>
                <div class="col-xs-1" style="width: 50px"></div>
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">Status</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <select class="form-control" id="arStatus" name="arStatus">
                        <option value="">--Select--</option>
                        <option value="N">New </option>
                        <option value="E">Export </option>
                        <option value="C">Change </option>
                    </select>
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-10"></div>
                <div class="col-xs-1">
                    <button type="submit"  id="btnSearchAR"  name="btnSearchAR"  onclick="searchArmonitor()"  class="btn btn-primary btn-primary">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>
                </div>
                <div class="col-xs-12"><br></div>  
                <div class="col-xs-12">
                    <table id="arDataListTable" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden">Id</th>
                                <th style="width: 1%" onclick="selectAll()"><u>All</u></th>
                                <th style="width: 1%" >No</th>
                                <th style="width: 12%">Inv No.</th>
                                <th style="width: 12%">AR Code</th>
                                <th style="width: 26%">Inv To</th>
                                <th style="width: 12%">Acc Code</th>
                                <th style="width: 15%">Gross</th>
                                <th style="width: 15%">Amount</th>
                                <th style="width: 2%">Cur</th>
                                <th style="width: 5%">Status</th>
                             </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ar_nirvana" items="${listAr}" varStatus="countar">
                            <tr>
                                <td class="hidden">1</td>
                                <td align="center">
                                    <input class="form-control" type="checkbox" id="selectAll${countar.count}" name="selectAll${countar.count}" value="${countar.count}">
                                </td>
                                <td align="center">${countar.count}</td>
                                <td>${ar_nirvana.intreference}</td>
                                <td>${ar_nirvana.customerid}</td>
                                <td>${ar_nirvana.customername}</td>
                                <td>${ar_nirvana.salesaccount1}</td>
                                <td align="right" class="money">${ar_nirvana.aramt -  ar_nirvana.vatamt}</td>
                                <td align="right" class="money">${ar_nirvana.aramt}</td>
                                <td align="center">${ar_nirvana.currencyid}</td>
                                <td align="center">${ar_nirvana.status}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>    
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-1 text-right" style="width: 665px"></div>
                <div class="col-xs-1 text-right" style="width: 210px">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span id="btnDownloadAP" class="glyphicon glyphicon-print" ></span> Print Change AR Report
                    </button>
                </div>
                <div class="col-xs-1 text-right" style="">
                    <button type="button" class="btn btn-success btn-default" onclick="exportAR()"  data-dismiss="modal">
                        <span id="btnExportAP" class="glyphicon glyphicon-export" ></span> Export
                    </button>
                </div>    
            </div>
            <div class="col-xs-12"><br></div>
        </form>
    </div>
</div> 

<!--Export AR Modal-->
<div class="modal fade" id="arExportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Export AR</h4>
            </div>
            <div class="modal-body" id="copyReceiptModal" >
                <label class="text-right">Are you sure to ar to nirvana ?</label>                                  
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="confirmExport()">
                    <span id="btnConfirmExport" class="glyphicon" ></span> Ok
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script language="javascript">
    $(document).ready(function () {
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        $(".money").mask('000,000,000.00', {reverse: true});
        var table = $('#arDataListTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });
        
        $('#arDataListTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#arDataListTable tbody tr.row_selected').attr("id"));
            }
        });
    });
    
    function exportAR(){
        $("#arExportModal").modal("show");
    }
    
    function confirmExport(){
        $("#arExportModal").modal("hide");
        var action = $('#action').val();
        action.value = 'export';
        document.getElementById('arMonitorForm').submit();
    }
    
    function searchArmonitor(){
        var action = $('#action').val();
        action.value = 'searchAr';
        document.getElementById('arMonitorForm').submit();
    }
    function selectAll(){
        var row = $('#arDataListTable tr').length;     
        var check = 0;
        var unCheck = 0;
        for(var i=1;i<row;i++){          
            var selectAll = document.getElementById("selectAll"+i);
            if(selectAll !== null && selectAll !== ''){
                if(document.getElementById("selectAll"+i).checked){
                    check++;
                } else {
                    unCheck++;
                }
            }   
        }
        alert("Check : " + check + "  Un : " + unCheck + " Row : " + row);
        if(check > unCheck){
            alert("1");
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    if(document.getElementById("selectAll"+i).checked){

                    } else { 
                        document.getElementById("selectAll"+i).checked = true;                   
                    }    
                }   
            }
        }

        if(check < unCheck){
            alert("2");
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    document.getElementById("selectAll"+i).checked = false;
                }   
            }
        }

        if(check === 0 && unCheck !== 0){
            alert("3");
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    if(document.getElementById("selectAll"+i).checked){

                    } else { 
                        document.getElementById("selectAll"+i).checked = true;

                    }
                }    
            }    
        }

        if(check !== 0 && unCheck === 0){
            alert("4");
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    document.getElementById("selectAll"+i).checked = false;
                }   
            }
        }

        if(check === unCheck){
            alert("5");
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    if(document.getElementById("selectAll"+i).checked){

                    } else { 
                        document.getElementById("selectAll"+i).checked = true;                        
                    }    
                }             
            }            
        }
    }
</script>
