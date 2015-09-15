<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/CollectionMonitor.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="content-header" >
    <h1>
        Nirvana Interface
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Nirvana Interface</a></li>          
        <li class="active"><a href="#"></a>Collection Monitor</li>
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
                <h4><b>Collection Monitor</b></h4>                  
            </div>
            <div class="col-xs-12 form-group"><hr/></div>
        </div>
        <form action="CollectionMonitor.smi" method="post" id="collectionMonitorForm" role="form" autocomplete="off">
            <div class="col-xs-12">
                <div class="col-xs-1 text-right"  style="width: 100px">
                    <label class="control-label" for="">Department</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <select id="collectionDepartment" name="collectionDepartment" class="form-control selectize">
                        <option value="">-- ALL --</option> 
                        <c:choose>
                            <c:when test="${requestScope['collectionDepartment'] == 'Wendy'}">
                                <c:set var="selectedWendy" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="Wendy" ${selectedWendy}>Wendy</option>
                        
                         <c:choose>
                            <c:when test="${requestScope['collectionDepartment'] == 'Inbound'}">
                                <c:set var="selectedInbound" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="Inbound" ${selectedInbound}>Inbound</option>
                        
                        <c:choose>
                            <c:when test="${requestScope['collectionDepartment'] == 'Outbound'}">
                                <c:set var="selectedOutbound" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="Outbound" ${selectedOutbound}>Outbound</option>
                    </select>
                </div>
                <div class="col-xs-1 text-right"  style="width: 100px">
                    <label class="control-label" for="">Type</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <select id="collectionType" name="collectionType" class="form-control selectize">
                        <option value="">-- ALL --</option> 
                        <c:choose>
                            <c:when test="${requestScope['collectionType'] == 'V'}">
                                <c:set var="selectedVat" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="V" ${selectedVat}>Vat</option>
                         <c:choose>
                            <c:when test="${requestScope['collectionType'] == 'T'}">
                                <c:set var="selectedTemp" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="T" ${selectedTemp}>Temp</option>
                    </select>
                </div>
                <div class="col-xs-1 text-right" style="width: 120px">
                    <label class="control-label" for="">Status</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                   <select id="collectionStatus" name="collectionStatus" class="form-control selectize">
                        <option value="">-- ALL --</option> 
                        <c:choose>
                            <c:when test="${requestScope['collectionStatus'] == 'CLEAR'}">
                                <c:set var="selectedClear" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="CLEAR" ${selectedClear}>CLEAR</option>
                         <c:choose>
                            <c:when test="${requestScope['collectionStatus'] == 'UNCLEAR'}">
                                <c:set var="selectedUnclear" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="UNCLEAR" ${selectedUnclear}>UNCLEAR</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-1 text-right" style="width: 100px">
                    <label class="control-label" for="">From</lable>
                </div>
                <div class="col-xs-1"  style="width: 200px">
                    <div class='input-group date' id='InputFromDate'>
                    <c:if test='${taxInvoice.taxInvDate != null}'>
                        <input id="collectionFromDate" name="collectionFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['collectionFromDate']}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${taxInvoice.taxInvDate == null}'>
                        <input id="collectionFromDate" name="collectionFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['collectionFromDate']}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>               
                </div>
                <!--<div class="col-xs-1" style="width: 120px"></div>-->
                <div class="col-xs-1 text-right" style="width: 100px">
                    <label class="control-label">To</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <div class='input-group date' id='InputToDate'>
                    <c:if test='${taxInvoice.taxInvDate != null}'>
                        <input id="collectionToDate" name="collectionToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['collectionToDate']}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${taxInvoice.taxInvDate == null}'>
                        <input id="collectionToDate" name="collectionToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['collectionToDate']}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>               
                </div>
                <div class="col-xs-1 text-right" style="width: 120px">
                    <label class="control-label" for="">Invoice No</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <input id="collectionInvNo" name="collectionInvNo"  type="text" class="form-control " value="${requestScope['collectionInvNo']}">
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-1" style="width: 720px"></div>
                <div class="col-xs-1 " style="width: 80px">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span id="btnPrintCollection" class="glyphicon glyphicon-print" ></span> Print
                    </button>
                </div>          
                <div class="col-xs-1">
                    <button type="submit"  id="btnSearch"  name="btnSearch" onclick="searchAction()" class="btn btn-primary btn-primary">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>
                </div>
                <input type="hidden" name="action" id="action" value="">
                <div class="col-xs-12"><br></div>  
                <div class="col-xs-12">
                    <table id="collectionDataListTable" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden">Id</th>
                                <th style="width: 1%" >No</th>
                                <th style="width: 12%">Receipt</th>
                                <th style="width: 12%">Inv No.</th>
                                <th style="width: 10%">AR Code</th>
                                <th style="width: 15%">Inv To</th>
                                <th style="width: 10%">Acc Code</th>
                                <th style="width: 15%">Inv Amount</th>
                                <th style="width: 15%">Diff</th>
                                <th style="width: 15%">Rec Amount</th>
                                <th style="width: 2%">Cur</th>
                                <th style="width: 15%">Status</th>
                            </tr>
                        </thead>
                        <tbody>               
                            <tr>
                                <td class="hidden">1</td>
                                <td align="center">1</td>
                                <td>150814</td>
                                <td>150814</td>
                                <td>150814</td>
                                <td>150814</td>
                                <td>150814</td>
                                <td align="right" class="money">1000000</td>
                                <td align="right" class="money">1000000</td>
                                <td align="right" class="money">1000000</td>
                                <td align="center">THB</td>
                                <td align="center" >Clear</td>
                            </tr>
                        </tbody>
                    </table>    
                </div>
            </div>         
        </form>
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
        var table = $('#collectionDataListTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });
        
        $('#collectionDataListTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#collectionDataListTable tbody tr.row_selected').attr("id"));
            }
        });
    });
    function searchAction(){
        var action = document.getElementById('action');
        action.value = 'search';
    }
</script>