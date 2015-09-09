<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/APMonitor.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="mpaytype_list" value="${requestScope['mpaytype_list']}" />
<section class="content-header" >
    <h1>
        Nirvana Interface
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Nirvana Interface</a></li>          
        <li class="active"><a href="#"></a>AP Monitor</li>
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
                <h4><b>AP Monitor</b></h4>                  
            </div>
            <div class="col-xs-12 form-group"><hr/></div>
        </div>
        <form action="APMonitor.smi" method="post" id="apMonitorForm" role="form" autocomplete="off">
            <div class="col-xs-12">
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">Payment</lable>
                </div>
                <div class="col-xs-1" style="width: 240px">
                    <select class="form-control" id="apPayment" name="apPayment" onchange="apPaymentAir()">
                        <option value=""> </option>
                        <c:set var="A" value="" />
                        <c:if test="${'A' == requestScope['apPayment']}">
                            <c:set var="A" value="selected" />
                        </c:if>
                        <option value="A" ${A}>Air Ticket</option>
                        <c:set var="W" value="" />
                        <c:if test="${'W' == requestScope['apPayment']}">
                            <c:set var="W" value="selected" />
                        </c:if>
                        <option value="W" ${W}>Wendy</option>
                        <c:set var="O" value="" />
                        <c:if test="${'O' == requestScope['apPayment']}">
                            <c:set var="O" value="selected" />
                        </c:if>
                        <option value="O" ${O}>Outbound</option>
                    </select>
                </div>
                <div class="col-xs-1" style="width: 50px"></div>
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">Type</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <c:set var="air" value="" />
                        <c:if test="${'A' == requestScope['apPayment']}">
                            <c:set var="air" value="disabled" />
                        </c:if>
                    <select class="form-control" id="apType" name="apType" ${air}>
                        <option value=""> </option>
                        <c:forEach var="mpaytype_list" items="${mpaytype_list}">
                            <c:set var="select" value="" />
                            <c:if test="${mpaytype_list.id == requestScope['apType']}">
                                <c:set var="select" value="selected" />
                            </c:if>
                            <option value="<c:out value="${mpaytype_list.id}" />" ${select}><c:out value="${mpaytype_list.name}" /></option>                                         
                        </c:forEach>
                    </select>
                </div>
                <div class="col-xs-1" style="width: 50px"></div>
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">Status</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <select class="form-control" id="apStatus" name="apStatus">
                        <option value=""> </option>
                        <c:set var="A" value="" />
                        <c:if test="${'N' == requestScope['apStatus']}">
                            <c:set var="N" value="selected" />
                        </c:if>
                        <option value="N" ${N}>New</option>
                        <c:set var="E" value="" />
                        <c:if test="${'E' == requestScope['apStatus']}">
                            <c:set var="E" value="selected" />
                        </c:if>
                        <option value="E" ${E}>Export</option>
                        <c:set var="C" value="" />
                        <c:if test="${'C' == requestScope['apStatus']}">
                            <c:set var="C" value="selected" />
                        </c:if>
                        <option value="C" ${C}>Change</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-1 text-left">
                    <label class="control-label" for="">From</lable>
                </div>
                <div class="col-xs-1" style="width: 170px">
                    <div class='input-group date' id='InputFromDate'>
                    <c:if test='${taxInvoice.taxInvDate != null}'>
                        <input id="apFromDate" name="apFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${taxInvoice.taxInvDate == null}'>
                        <input id="apFromDate" name="apFromDate"  type="text" 
                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['apFromDate']}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>               
                </div>
                <div class="col-xs-1" style="width: 120px"></div>
                <div class="col-xs-1 text-left">
                    <label class="control-label">To</lable>
                </div>
                <div class="col-xs-1" style="width: 170px">
                    <div class='input-group date' id='InputToDate'>
                    <c:if test='${taxInvoice.taxInvDate != null}'>
                        <input id="apToDate" name="apToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${taxInvoice.taxInvDate == null}'>
                        <input id="apToDate" name="apToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['apToDate']}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>               
                </div>
                <div class="col-xs-1" style="width: 245px"></div>
                <div class="col-xs-1">
                    <button type="submit"  id="btnSearchAP"  name="btnSearchAP" class="btn btn-primary btn-primary">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>
                </div>
                <div class="col-xs-12"><br></div>  
                <div class="col-xs-12">
                    <input type="hidden" id="apCount" name="apCount" value="1"/>
                    <table id="apDataListTable" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden">Id</th>
                                <th onclick="selectAll()" style="width: 1%"><u>All</u></th>
                                <th style="width: 1%" >No</th>
                                <th style="width: 12%">Pay No.</th>
                                <th style="width: 12%">AP Code</th>
                                <th style="width: 26%">Invoice Sup</th>
                                <th style="width: 12%">Acc Code</th>
                                <th style="width: 15%">Gross</th>
                                <th style="width: 15%">Amount</th>
                                <th style="width: 2%">Cur</th>
                                <th style="width: 5%">Status</th>
                             </tr>
                        </thead>
                        <tbody>               
                            <tr>
                                <td class="hidden">1</td>
                                <td align="center">
                                    <input class="form-control" type="checkbox" id="selectAll1" name="selectAll1" value="1">
                                </td>
                                <td align="center">1</td>
                                <td>150814</td>
                                <td>150814</td>
                                <td>150814</td>
                                <td>150814</td>
                                <td align="right" class="money">1000000</td>
                                <td align="right" class="money">1000000</td>
                                <td align="center">THB</td>
                                <td align="center">NORMAL</td>
                            </tr>
                        </tbody>
                    </table>    
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-1 text-right" style="width: 665px"></div>
                <div class="col-xs-1 text-right" style="width: 210px">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span id="btnDownloadAP" class="glyphicon glyphicon-print" ></span> Print Change AP Report 
                    </button>
                </div>
                <div class="col-xs-1 text-left" style="">
                    <button type="button" class="btn btn-success btn-default" onclick="exportAP()"  data-dismiss="modal">
                        <span id="btnExportAP" class="glyphicon glyphicon-export" ></span> Export
                    </button>
                </div>    
            </div>
            <input type="hidden" id="action" name="action" value="search"/>
        </form>
    </div>
</div> 

<!--Export AP Modal-->
<div class="modal fade" id="apExportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Export AP</h4>
            </div>
            <div class="modal-body" id="copyReceiptModal" >
                <label class="text-right">Are you sure to ap to nirvana ?</label>                                  
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
        var table = $('#apDataListTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });
        
        $('#apDataListTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#apDataListTable tbody tr.row_selected').attr("id"));
            }
        });
    });
    
    function exportAP(){
        $("#apExportModal").modal("show");
    }
    
    function confirmExport(){
        $("#apExportModal").modal("hide");
    }
    
    function selectAll(){
        var row = $('#apDataListTable tr').length;     
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

        if(check > unCheck){
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
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    document.getElementById("selectAll"+i).checked = false;
                }   
            }
        }

        if(check === 0 && unCheck !== 0){
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
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    document.getElementById("selectAll"+i).checked = false;
                }   
            }
        }

        if(check === unCheck){
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
    
    function apPaymentAir(){
        var apPayment = document.getElementById("apPayment").value;
        if(apPayment !== 'A'){
            $('#apType').removeAttr('disabled');           
        } else {
            $('#apType').attr('disabled', 'disabled');
            $('[name=apType] option').filter(function() { 
                return ($(this).val() === '');
            }).prop('selected', true);      
        }
    }
</script>