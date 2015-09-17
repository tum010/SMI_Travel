<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="CollectionList" value="${requestScope['CollectionList']}" />

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
                    <select id="department" name="department" class="form-control selectize">
                        <option value="">-- ALL --</option> 
                        <c:choose>
                            <c:when test="${requestScope['department'] == 'Wendy'}">
                                <c:set var="selectedWendy" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="Wendy" ${selectedWendy}>Wendy</option>
                        
                         <c:choose>
                            <c:when test="${requestScope['department'] == 'Inbound'}">
                                <c:set var="selectedInbound" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="Inbound" ${selectedInbound}>Inbound</option>
                        
                        <c:choose>
                            <c:when test="${requestScope['department'] == 'Outbound'}">
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
                    <select id="type" name="type" class="form-control selectize">
                        <option value="">-- ALL --</option> 
                        <c:choose>
                            <c:when test="${requestScope['type'] == 'V'}">
                                <c:set var="selectedVat" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="V" ${selectedVat}>Vat</option>
                        <c:choose>
                            <c:when test="${requestScope['type'] == 'N'}">
                                <c:set var="selectedNoVat" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="N" ${selectedNoVat}>No Vat</option>
                        <c:choose>
                            <c:when test="${requestScope['type'] == 'T'}">
                                <c:set var="selectedTemp" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="T" ${selectedTemp}>Temp</option>
                        <c:choose>
                            <c:when test="${requestScope['type'] == 'A'}">
                                <c:set var="selectedTicket" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="A" ${selectedTicket}>Ticket</option>
                    </select>
                </div>
                <div class="col-xs-1 text-right" style="width: 120px">
                    <label class="control-label" for="">Status</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                   <select id="status" name="status" class="form-control selectize">
                        <option value="">-- ALL --</option> 
                        <c:choose>
                            <c:when test="${requestScope['status'] == 'CLEAR'}">
                                <c:set var="selectedClear" value="selected" />
                            </c:when>
                        </c:choose>
                        <option value="CLEAR" ${selectedClear}>CLEAR</option>
                         <c:choose>
                            <c:when test="${requestScope['status'] == 'UNCLEAR'}">
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
                    <label class="control-label" for="">From<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1"  style="width: 200px">
                    <div class=" form-group"> 
                        <div class='input-group date fromdate' id="DateFrom">
                            <input id="inputFromDate" name="inputFromDate"  type="text" 
                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['collectionFromDate']}">
                            <span class="input-group-addon spandate" id="InputFromDateSpan1"><span class="glyphicon glyphicon-calendar" id="InputFromDateSpan2"></span></span>
                        </div>
                    </div>            
                </div>
                <!--<div class="col-xs-1" style="width: 120px"></div>-->
                <div class="col-xs-1 text-right" style="width: 100px">
                    <label class="control-label">To<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <div class=" form-group"> 
                        <div class='input-group date todate' id="DateTo">
                            <input id="inputToDate" name="inputToDate"  type="text" 
                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['collectionToDate']}">
                            <span class="input-group-addon spandate" id="InputToDateSpan1"><span class="glyphicon glyphicon-calendar" id="InputToDateSpan2"></span></span>
                        </div>
                    </div>                  
                </div>
                <div class="col-xs-1 text-right" style="width: 120px">
                    <label class="control-label" for="">Invoice No</lable>
                </div>
                <div class="col-xs-1" style="width: 200px">
                    <input id="invno" name="invno"  type="text" class="form-control " value="${requestScope['invno']}">
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-1" style="width: 720px"></div>
                <div class="col-xs-1 " style="width: 80px">
                    <button type="button" id="ButtonPrint" name="ButtonPrint" onclick="printCollectionReport()"class="btn btn-default" data-dismiss="modal">
                        <span id="btnPrintCollection" class="glyphicon glyphicon-print" ></span> Print
                    </button>
                </div>          
                <div class="col-xs-1">
                    <button type="submit" id="ButtonSearch"  name="ButtonSearch" onclick="searchAction()" class="btn btn-primary btn-primary">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>
                </div>
                <input type="hidden" name="action" id="action" value="">
                <div class="col-xs-12"><br></div>  
                <div class="col-xs-12">
                    <table id="collectionDataListTable" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <!--<th class="hidden">Id</th>-->
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
                            <c:forEach var="table" items="${CollectionList}" varStatus="dataStatus">
                                <tr>
                                    <td align="center">${dataStatus.count}</td>
                                    <td>${table.recno}</td>
                                    <td>${table.invno}</td>
                                    <td>${table.arcode}</td>
                                    <td>${table.invto}</td>
                                    <td>${table.acccode}</td>
                                    <td align="right"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.invamount}" /></td>
                                    <td align="right"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.diff}" /></td>
                                    <td align="right"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.recamount}" /></td>
                                    <td align="center">${table.cur}</td>
                                    <td align="center">${table.collectionStatus}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>    
                </div>
            </div>         
        </form>
    </div>
</div>

<script language="javascript">
    $(document).ready(function () {
        
        var table = $('#collectionDataListTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "iDisplayLength": 50
        });
 

        $('#collectionDataListTable tbody').on('click', 'tr', function() {
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
        
        
        $('.date').datetimepicker();
        $('.datemask').mask('0000-00-00');
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        $(".money").mask('000,000,000.00', {reverse: true});

        //validate date
        $('#DateFrom').datetimepicker().on('dp.change', function (e) {
            $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputFromDate');
        });
        $('#DateTo').datetimepicker().on('dp.change', function (e) {
            $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputToDate');
        });

        $("#collectionMonitorForm")
                .bootstrapValidator({
                    framework: 'bootstrap',
    //                container: 'tooltip',
                    feedbackIcons: {
                        valid: 'uk-icon-check',
                        invalid: 'uk-icon-times',
                        validating: 'uk-icon-refresh'
                    },
                    fields: {
                        inputFromDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date From is required'
                                },
                                date: {
                                    format: 'YYYY-MM-DD',
                                    max: 'inputToDate',
                                    message: 'The Date From is not a valid'
                                }
                            }
                        },
                        inputToDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date To is required'
                                },
                                date: {
                                    format: 'YYYY-MM-DD',
                                    min: 'inputFromDate',
                                    message: 'The Date To is not a valid'
                                }
                            }
                        }
                    }
                }).on('success.field.fv', function (e, data) {
                    if (data.field === 'inputFromDate' && data.fv.isValidField('inputToDate') === false) {
                        data.fv.revalidateField('inputToDate');
                    }

                    if (data.field === 'inputToDate' && data.fv.isValidField('inputFromDate') === false) {
                        data.fv.revalidateField('inputFromDate');
                    }
                });

        $('.fromdate').datetimepicker().change(function(){                          
            checkFromDateField();
        });
        $('.todate').datetimepicker().change(function(){                          
            checkToDateField();
        });        
    });
    
    function searchAction(){
        var action = document.getElementById('action');
        action.value = 'search';
    }
    
    function printCollectionReport(){
        var department = document.getElementById('department').value;
        var type = document.getElementById('type').value;
        var status = document.getElementById('status').value;
        var inputFromDate = document.getElementById('inputFromDate').value;
        var inputToDate = document.getElementById("inputToDate").value;
        var invno = document.getElementById("invno").value;
//        var strStatus = status.options[status.selectedIndex].text;
        if((inputToDate === '') || (inputFromDate === '')){
            validateDate();
        } else {
            window.open("Excel.smi?name=CollectionReport&department="+department+"&type="+type+"&status="+status+"&inputFromDate="+inputFromDate+"&inputToDate="+inputToDate+"&invno="+invno);
        }   
    }
    
    function checkFromDateField(){
    var inputFromDate = document.getElementById("inputFromDate");
    var InputToDate = document.getElementById("inputToDate");
    if(inputFromDate.value === ''){          
        var InputFromDateSpan1 = document.getElementById("InputFromDateSpan1");
        inputFromDate.style.borderColor = "red";
        InputFromDateSpan1.style.borderColor = "red";
        if((inputFromDate.style.borderColor === "red") && (InputToDate.style.borderColor === "red")){
            $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputToDate');
            $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputFromDate');
            $("#ButtonPrint").addClass("disabled");
        }
    } else {
        $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputToDate');
        $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputFromDate');
        $("#ButtonPrint").removeClass("disabled");
        checkDateValue("from","");
    }
}
    
function checkToDateField(){
    var InputToDate = document.getElementById("inputToDate");
    var inputFromDate = document.getElementById("inputFromDate");
    if(InputToDate.value === ''){
        var InputToDateSpan1 = document.getElementById("InputToDateSpan1");
        InputToDate.style.borderColor = "red";
        InputToDateSpan1.style.borderColor = "red";
        if((inputFromDate.style.borderColor === "red") && (InputToDate.style.borderColor === "red")){
            $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputToDate');
            $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputFromDate');
            $("#ButtonPrint").addClass("disabled");
        }    
    }else{
        $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputToDate');
        $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputFromDate');
        $("#ButtonPrint").removeClass("disabled");
        checkDateValue("to","");
    }       
}
    
function checkDateValue(date){
    var inputFromDate = document.getElementById("inputFromDate");
    var InputToDate = document.getElementById("inputToDate");
    if((inputFromDate.value !== '') && (InputToDate.value !== '')){
        var fromDate = (inputFromDate.value).split('-');
        var toDate = (InputToDate.value).split('-');
        if((parseInt(fromDate[0])) > (parseInt(toDate[0]))){
            validateDate(date,"over");
        }
        if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))){
            validateDate(date,"over");
        }
        if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[2])) > (parseInt(toDate[2]))){
            validateDate(date,"over");
        }          
    }
}
    
function validateDate(date,option){
    if(option === 'over'){
        if(date === 'from'){
            $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputToDate');
            $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputFromDate');
        }
        if(date === 'to'){
            $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputToDate');
            $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputFromDate');
        }           
        $("#ButtonPrint").addClass("disabled");
    } else {
        $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputToDate');
        $('#collectionMonitorForm').bootstrapValidator('revalidateField', 'inputFromDate');
        $("#ButtonPrint").addClass("disabled");
    }

}
</script>