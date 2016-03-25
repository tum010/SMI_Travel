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
    <div class="col-sm-1" style="border-right:  solid 1px #01C632;padding-top: 10px;width: 150px">
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
            <input type="hidden" value="" id="page" name="page">
            <input type="hidden" value="" id="currentPage" name="currentPage">
            <input type="hidden" value="" id="row" name="row">
            <div class="col-xs-12" style="margin-top: -17px">
                <c:if test="${requestScope['update'] =='updatesuccess'}">                                            
                    <div id="textAlertDivSave"  style="" class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Update Status Success!</strong> 
                    </div>
                </c:if>
                <c:if test="${requestScope['update'] =='updatefail'}">
                <div id="textAlertDivSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <strong>Update Status Unsuccess!</strong> 
                </div>
                </c:if>
                <div id="textAlertDivNotChoose"  style="display: none" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" aria-label="Close" onclick="hideDiv()"><span aria-hidden="true">&times;</span></button>
                   <strong>Please choose the collection monitor list.!</strong> 
                </div>
                
                <div class="col-xs-1 text-right"  style="width: 100px" >
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
                <div class="col-xs-1 text-right" style="width: 100px;margin-top: -13px">
                    <label class="control-label" for="">From<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1"  style="width: 200px;margin-top: -13px">
                    <div class=" form-group"> 
                        <div class='input-group date fromdate' id="DateFrom">
                            <input id="inputFromDate" name="inputFromDate"  type="text" 
                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['collectionFromDate']}">
                            <span class="input-group-addon spandate" id="InputFromDateSpan1"><span class="glyphicon glyphicon-calendar" id="InputFromDateSpan2"></span></span>
                        </div>
                    </div>            
                </div>
                <!--<div class="col-xs-1" style="width: 120px"></div>-->
                <div class="col-xs-1 text-right" style="width: 100px;margin-top: -13px">
                    <label class="control-label">To<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1" style="width: 200px;margin-top: -13px">
                    <div class=" form-group"> 
                        <div class='input-group date todate' id="DateTo">
                            <input id="inputToDate" name="inputToDate"  type="text" 
                               class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${requestScope['collectionToDate']}">
                            <span class="input-group-addon spandate" id="InputToDateSpan1"><span class="glyphicon glyphicon-calendar" id="InputToDateSpan2"></span></span>
                        </div>
                    </div>                  
                </div>
                <div class="col-xs-1 text-right" style="width: 120px;margin-top: -13px">
                    <label class="control-label" for="">Invoice No</lable>
                </div>
                <div class="col-xs-1" style="width: 200px;margin-top: -13px">
                    <input id="invno" name="invno"  type="text" class="form-control " value="${requestScope['invno']}">
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-1" style="width: 720px"></div>
                <div class="col-xs-1 " style="width: 80px">
                </div>          
                <div class="col-xs-1" style="margin-top: -29px">
                    <button type="submit" id="ButtonSearch"  name="ButtonSearch" onclick="searchAction()" class="btn btn-primary btn-primary">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>
                </div>
                <input type="hidden" name="action" id="action" value="">
                <div class="col-xs-12"><br></div>  
                <div class="col-xs-12" style="margin-top: -20px">
                    <input type="hidden" id="coCount" name="coCount" value="${CollectionList.size()}"/>
                    <table id="collectionDataListTable" class="display paginated" cellspacing="0" width="100%" style="table-layout: fixed;">
                        <thead>
                            <tr class="datatable-header" >
                                <th class="hidden">Id</th>
                                <th style="width: 5%" onclick="selectAll()"><u>All</u></th>
                                <th style="width: 5%" >No</th>
                                <th style="width: 10%">Receipt</th>
                                <th style="width: 12%">Inv No.</th>
                                <th style="width: 10%">AR Code</th>
                                <th style="width: 10%">Inv To</th>
                                <th style="width: 9%">Acc Code</th>
                                <th style="width: 10%">Inv Amount</th>
                                <th style="width: 10%">Sum Inv</th>
                                <th style="width: 10%">Diff</th>
                                <th style="width: 10%">Sum Rec</th>
                                <th style="width: 5%">Cur</th>
                                <th style="width: 10%">Collection</th>
                                <th style="width: 8%">Status</th>
                            </tr>
                        </thead>
                        <tbody>               
                            <c:forEach var="table" items="${CollectionList}" varStatus="dataStatus">
                                <tr>
                                    <td class="hidden"><input class="form-control" type="text" id="inputId${dataStatus.count}" name="inputId${dataStatus.count}" value="${table.rowid}"></td>
                                    <td align="center">
                                        <c:choose>
                                            <c:when test="${table.status == 'New'}">
                                                <input type="checkbox" class="form-control" id="selectAll${dataStatus.count}" name="selectAll${dataStatus.count}" value="${dataStatus.count}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="checkbox" class="form-control" id="selectAll" name="selectAll" value="" disabled=""/>
                                            </c:otherwise>
                                        </c:choose>                                  
                                    </td>
                                    <td align="center">${dataStatus.count}</td>
                                    <td>${table.recno}</td>
                                    <td>${table.invno}</td>
                                    <td>${table.arcode}</td>
                                    <td>${table.invto}</td>
                                    <td>${table.acccode}</td>
                                    <td align="right">${table.invoiceamount}</td>
                                    <td align="right"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.invamount}" /></td>
                                    <td align="right"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.diff}" /></td>
                                    <td align="right"><fmt:formatNumber type="currency" pattern="#,##0.00;-#,##0.00" value="${table.recamount}" /></td>
                                    <td align="left">${table.cur}</td>
                                    <td align="center">${table.collectionStatus}</td>
                                    <td align="center">${table.status}</td>
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
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="printCollectionReport()">
                        <span id="btnDownloadAP" class="glyphicon glyphicon-print" ></span> Print Collection Report
                    </button>
                </div>
                <div class="col-xs-1 text-right" style="">
                    <button type="button" class="btn btn-success btn-default" onclick="exportCollection()"  data-dismiss="modal">
                        <span id="btnExportAP" class="glyphicon glyphicon-export" ></span> Export
                    </button>
                </div>    
            </div>
            <div class="col-xs-12"><br></div>
        </form>
    </div>
</div>
<!--Export AR Modal-->
<div class="modal fade" id="collectionExportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Export Collection</h4>
            </div>
            <div class="modal-body" id="copyReceiptModal" >
                <label class="text-right">Are you sure to export collection to nirvana ?</label>
                <input type="hidden" id="chooseCollection" name="chooseCollection" value=""/>
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
        
//        var table = $('#collectionDataListTable').dataTable({bJQueryUI: true,
//            "sPaginationType": "full_numbers",
//            "bAutoWidth": false,
//            "bFilter": false,
//            "iDisplayLength": 50
//        });
        var table = $('#collectionDataListTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false,
            "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
            "iDisplayLength": 50,
            "bSort": false,
            "bPaginate": false
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
        
        $('table.paginated').each(function() {
            var currentPage = 0;
            var numPerPage = 50;
            var $table = $(this);
            $table.bind('repaginate', function() {
                $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
            });
            $table.trigger('repaginate');
            var numRows = $table.find('tbody tr').length;
            var numPages = Math.ceil(numRows / numPerPage);
            var $pager = $('<div class="col-xs-12 text-right" id="pageNo"><font style="color: #499DD5"></font>&nbsp;</div>');
            var $br = $('<div class="col-xs-12"><br></div>');
            
            for (var page = 0; page < numPages; page++) {
                var isShowPage = (page < 5 ? "" : "hidden");
                if(page === 0){
                    $('<font style="color: #499DD5" id="noFirst" onclick="changeColor(\'noFirst\',\'first\',\''+page+'\')"><span class="page-number glyphicon"></span></font>').text(" " + "First" + "  ").bind('click', {
                    newPage: page
                    }, function(event) {
                        currentPage = event.data['newPage'];
                        $table.trigger('repaginate');
                        $(this).addClass('active').siblings().removeClass('active');
                        $(this).css("color", "#AFEEEE");
                    }).appendTo($pager).addClass('clickable');                                      
                    
                    if(numPages > 1){
                        for(var i=0; i<numPages; i++){
                            var isHidden = (i === 0 ? "" : "hidden");
                            $('<font style="color: #499DD5" id="noPrevious'+i+'" onclick="changeColor(\'noPrevious'+i+'\',\'previous\',\''+i+'\')" class="'+isHidden+'"><span class="page-number glyphicon"></span></font>').text(" " + "Previous" + "  ").bind('click', {
                            newPage: i
                            }, function(event) {
                                currentPage = event.data['newPage'];
                                $table.trigger('repaginate');
                                $(this).addClass('active').siblings().removeClass('active');
        //                        $(this).css("color", "#AFEEEE");
                            }).appendTo($pager).addClass('clickable');
                        }
                    }    
                }
                
                $('<font style="color: #499DD5" id="no' + page + '" onclick="changeColor(\'no'+page+'\',\'no\',\''+page+'\')" class="'+isShowPage+'"><span class="page-number glyphicon"></span></font>').text(" " + (page + 1) + "  ").bind('click', {
                    newPage: page
                }, function(event) {                  
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                    $(this).css("color", "#AFEEEE");
                }).appendTo($pager).addClass('clickable');
                
                if(page === (numPages - 1)){
                    if(numPages > 1){
                        for(var i=0; i<numPages; i++){
                            var isHidden = (i === 1 ? "" : "hidden");
                            $('<font style="color: #499DD5" id="noNext'+i+'" onclick="changeColor(\'noNext'+i+'\',\'next\',\''+i+'\')" class="'+isHidden+'"><span class="page-number glyphicon"></span></font>').text(" " + "Next" + "  ").bind('click', {
                            newPage: i
                            }, function(event) {
                                currentPage = event.data['newPage'];
                                $table.trigger('repaginate');
                                $(this).addClass('active').siblings().removeClass('active');
        //                        $(this).css("color", "#AFEEEE");
                            }).appendTo($pager).addClass('clickable');
                        }
                    }    
                                       
                    $('<font style="color: #499DD5" id="noLast" onclick="changeColor(\'noLast\',\'last\',\''+page+'\')"><span class="page-number glyphicon"></span></font>').text(" " + "Last" + "  ").bind('click', {
                    newPage: page
                    }, function(event) {
                        currentPage = event.data['newPage'];
                        $table.trigger('repaginate');
                        $(this).addClass('active').siblings().removeClass('active');
                        $(this).css("color", "#AFEEEE");
                    }).appendTo($pager).addClass('clickable');                                     
                }
            }
            $br.insertAfter($table).addClass('active');
            $pager.insertAfter($table).find('span.page-number:first').addClass('active');
            document.getElementById("pageNo").style.cursor="pointer";
            document.getElementById("page").value = numPages-1;
            document.getElementById("currentPage").value = 0;
            $("#noFirst").css("color", "#AFEEEE");
            $("#no0").css("color", "#AFEEEE");
            $("#noPrevious0").css("color", "#AFEEEE");
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

    function selectAll(){
        var row = $('#collectionDataListTable tr').length;     
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
//        alert("Check : " + check + "  Un : " + unCheck + " Row : " + row);
        if(check > unCheck){
//            alert("1");
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
//            alert("2");
            for(var i=1;i<row;i++){
                var selectAll = document.getElementById("selectAll"+i);
                if(selectAll !== null && selectAll !== ''){
                    document.getElementById("selectAll"+i).checked = false;
                }   
            }
        }

        if(check === 0 && unCheck !== 0){
//            alert("3");
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
//            alert("4");
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
    
    function exportCollection(){
        var row = $('#collectionDataListTable tr').length;     
        var check = 0;
        for(var i=1;i<=row;i++){          
            var selectAll = document.getElementById("selectAll"+i);
            if(selectAll !== null && selectAll !== ''){
                if(document.getElementById("selectAll"+i).checked){
                    check++;
                }    
            }   
        }
        if(check === 0){
            document.getElementById("chooseCollection").value = "false";
        } else {
            document.getElementById("chooseCollection").value = "true";
        }
        $("#textAlertDivSave").hide();
        $('#textAlertDivNotChoose').hide();
        $("#collectionExportModal").modal("show");
    }
    
    function confirmExport(){
        $("#collectionExportModal").modal("hide");
        var chooseCollection = document.getElementById("chooseCollection").value;
        if(chooseCollection === 'true'){
            $('#action').val('export');
            document.getElementById('collectionMonitorForm').submit();
        } else {
            $('#textAlertDivNotChoose').show();
        }    
    }
    
    function hideDiv(){
        $('#textAlertDivNotChoose').hide();
    }
    
    function changeColor(id,type,page){
        var pageNo = parseInt($("#page").val())+1;
        for(var i=0; i<pageNo; i++){
            $("#no"+i).css("color", "#499DD5");
            $("#noPrevious"+i).css("color", "#499DD5");
            $("#noNext"+i).css("color", "#499DD5");
            $("#noFirst").css("color", "#499DD5");                
            $("#noLast").css("color", "#499DD5");
            
            $("#no"+i).addClass("hidden");
            $("#noPrevious"+i).addClass("hidden");
            $("#noNext"+i).addClass("hidden");
        }
        
        var pageShow = parseInt(page);
        if(pageShow > 2 && pageShow < pageNo - 2){
            for(var i=pageShow; i >= pageShow-2; i--){
               $("#no"+i).removeClass("hidden"); 
            }
            for(var i=pageShow; i <= pageShow+2; i++){
               $("#no"+i).removeClass("hidden");  
            }
        
        }else{
            if(pageShow <= 2){
                for(var i=0; i < 5; i++){
                    $("#no"+i).removeClass("hidden");  
                } 
            
            }else if(pageShow <= pageNo-1){
                for(var i=pageNo-5; i < pageNo; i++){
                    $("#no"+i).removeClass("hidden");  
                } 
            }
            
        }    
        
        var previous = ((parseInt(page) === 0 ? 0 : parseInt(page)-1));
        $("#noPrevious"+(previous)).removeClass("hidden");

        var next = ((parseInt(page) === pageNo-1 ? pageNo-1 : parseInt(page)+1));
        $("#noNext"+(next)).removeClass("hidden");
        
        $("#no"+page).css("color", "#AFEEEE");
        
        if(parseInt(page) === 0){
            $("#noFirst").css("color", "#AFEEEE");
            $("#noPrevious"+(previous)).css("color", "#AFEEEE");
        
        }else if(parseInt(page) === pageNo-1){
            $("#noLast").css("color", "#AFEEEE");
            $("#noNext"+(next)).css("color", "#AFEEEE");
        }
        
        if(pageNo-1 === 0){
            $("#noFirst").css("color", "#499DD5");                
            $("#noLast").css("color", "#499DD5");
            
            if(type === 'first'){
                $("#noFirst").css("color", "#AFEEEE");
                
            }else if(type === 'last'){
                $("#noLast").css("color", "#AFEEEE");
                
            }
        }
    
        $("#currentPage").val(page);
             
    }
</script>