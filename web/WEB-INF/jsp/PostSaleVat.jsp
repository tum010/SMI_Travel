<%-- 
    Document   : PostSaleVat
    Created on : Nov 17, 2015, 9:46:42 AM
    Author     : Jittima
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="Department" value="${requestScope['Department']}" />
<c:set var="From" value="${requestScope['From']}" />
<c:set var="To" value="${requestScope['To']}" />
<c:set var="Status" value="${requestScope['Status']}" />
<c:set var="listPost" value="${requestScope['listPost']}" />
<c:set var="Type" value="${requestScope['Type']}" />
<section class="content-header" >
    <h1>
        Tax Invoice Report
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Tax Invoice Report</a></li>          
        <li class="active"><a href="#"></a>Post Sale Vat</li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app="">
    <!-- side bar -->
    <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
        <div ng-include="'WebContent/Accounting/TaxInvoiceReportMenu.html'"></div>
    </div>
    <div class="col-sm-10">
        <div class="row" style="padding-left: 15px">  
            <div class="col-sm-6 " style="padding-right: 15px">
                <h4><b>Post Sale Vat</b></h4>                  
            </div>
            <div class="col-xs-12 form-group"><hr/></div>
        </div>
        <form action="PostSaleVat.smi" method="post" id="PostSaleVatForm" role="form" autocomplete="off">
            <input type="hidden" value="searchPostSaleVat" id="action" name="action">
            <div class="col-xs-12">
                <c:if test="${requestScope['update'] =='1'}">                                            
                    <div id="textAlertDivSave"  style="" class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Update Status Success!</strong> 
                    </div>
                </c:if>
                <c:if test="${requestScope['update'] =='0'}">
                <div id="textAlertDivSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <strong>Update Status Unsuccess!</strong> 
                </div>
                </c:if>
                <div id="textAlertDivNotChoose"  style="display: none" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" aria-label="Close" onclick="hideDiv()"><span aria-hidden="true">&times;</span></button>
                   <strong>Please choose the post tax invoice list.!</strong> 
                </div>
                <div class="col-xs-1 text-left" style="width: 50px">
                    <label class="control-label">Type<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1 form-group" style="width: 120px">
                    <c:set var="selectTX" value="" />
                    <c:set var="selectCN" value="" />
                    <c:if test="${Type == 'TX'}">
                        <c:set var="selectTX" value="selected" />
                    </c:if>
                    <c:if test="${Type == 'CN'}">
                        <c:set var="selectCN" value="selected" />
                    </c:if>
                    <select class="form-control" id="postType" name="postType">
                        <option value="">--Select--</option>
                        <option value="TX" ${selectTX}> TX </option>
                        <option value="CN" ${selectCN}> CN </option>
                    </select>
                </div>
                <div class="col-xs-1 text-right" style="width: 50px">
                    <label class="control-label" for="">From<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1 form-group" style="width: 170px">
                    <div class='input-group date' id='InputFromDate'>
                    <c:if test='${From != null}'>
                        <input id="postFromDate" name="postFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${From}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${From == null}'>
                        <input id="postFromDate" name="postFromDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>  
                </div>
                <div class="col-xs-1 text-right" style="width: 30px">
                    <label class="control-label">To<font style="color: red">*</font></lable>
                </div>
                <div class="col-xs-1 form-group" style="width: 170px">
                    <div class='input-group date' id='InputToDate'>
                    <c:if test='${To != null}'>
                        <input id="postToDate" name="postToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="${To}">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>        
                    </c:if>
                    <c:if test='${To == null}'>
                        <input id="postToDate" name="postToDate"  type="text" 
                            class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                        <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                
                    </c:if>                             
                    </div>   
                </div>
                <div class="col-xs-1 text-left" style="width: 90px">
                    <label class="control-label">Department</lable>
                </div>
                <div class="col-xs-1 form-group" style="width: 125px">
                    <c:set var="selectDepartWendy" value="" />
                    <c:set var="selectDepartOutbound" value="" />
                    <c:set var="selectDepartInbound" value="" />
                    <c:if test="${Department == 'Wendy'}">
                        <c:set var="selectDepartWendy" value="selected" />
                    </c:if>
                    <c:if test="${Department == 'Outbound'}">
                        <c:set var="selectDepartOutbound" value="selected" />
                    </c:if>
                    <c:if test="${Department == 'Inbound'}">
                        <c:set var="selectDepartInbound" value="selected" />
                    </c:if>
                    <select class="form-control" id="department" name="department">
                        <option value="">--Select--</option>
                        <option value="Wendy" ${selectDepartWendy}>Wendy </option>
                        <option value="Outbound" ${selectDepartOutbound}>Outbound </option>
                        <option value="Inbound" ${selectDepartInbound}>Inbound </option>
                    </select>
                </div>
                <div class="col-xs-1 text-right" style="width: 60px">
                    <label class="control-label" for="">Status</lable>
                </div>
                <div class="col-xs-1" style="width: 120px">
                    <c:set var="selectVoid" value="" />
                    <c:set var="selectNormal" value="" />
                    <c:set var="selectPost" value="" />
                    <c:set var="selectChange" value="" />
                    <c:if test="${Status == 'Normal'}">
                        <c:set var="selectNormal" value="selected" />
                    </c:if>
                    <c:if test="${Status == 'Post'}">
                        <c:set var="selectPost" value="selected" />
                    </c:if>
                    <c:if test="${Status == 'Change'}">
                        <c:set var="selectChange" value="selected" />
                    </c:if>
                    <c:if test="${Status == 'Void'}">
                        <c:set var="selectVoid" value="selected" />
                    </c:if>
                    <select class="form-control" id="postStatus" name="postStatus">
                        <option value="">--Select--</option>
                        <option value="Normal" ${selectNormal}>Normal </option>
                        <option value="Post" ${selectPost}>Post </option>
                        <option value="Change" ${selectChange}>Change </option>
                        <option value="Void" ${selectVoid}>Void </option>
                    </select>
                </div>
            </div>
            <div class="col-xs-12"> 
                <div class="col-xs-10" style="width: 867px">
                </div>
                <div class="col-xs-1 text-right" style="width: 50px">
                    <button type="submit"  id="btnSearch"  name="btnSearch"  onclick="searchPostSaleVat()"  class="btn btn-primary btn-primary">
                        <span id="SpanSearch" class="glyphicon glyphicon-print fa fa-search"></span> Search
                    </button>
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <!--<div class="col-xs-12"><br></div>--> 
                <div class="col-xs-12">
                    <input type="hidden" id="postCount" name="postCount" value="${listPost.size()}"/>
                    <table id="postSaleVatDataListTable" class="display paginated" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden">Id</th>
                                <th style="width: 1%" onclick="selectAll()"><u>Post</u></th>
                                <th style="width: 13%" >Tax No.</th>
                                <th style="width: 15%">Tax Date</th>
                                <th style="width: 9%">A/R</th>
                                <th style="width: 22%">Tax Inv Name</th>
                                <th style="width: 12%">Gross</th>
                                <th style="width: 7%">Vat</th>
                                <th style="width: 12%">Amount</th>
                                <th style="width: 10%">Department</th>
                                <th style="width: 5%">Status</th>
                             </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="table" items="${listPost}" varStatus="counter">
                            <tr>
                                <td class="hidden"><input class="form-control" type="text" id="taxId${counter.count}" name="taxId${counter.count}" value="${table.taxid}"></td>
                                <td class="hidden"><input class="form-control" type="text" id="taxType${counter.count}" name="taxType${counter.count}" value="${table.type}"></td>
                                <td align="center">
                                    <c:choose>
                                        <c:when test="${table.status == 'Normal'}">
                                            <input type="checkbox" class="form-control" id="selectAll${counter.count}" name="selectAll${counter.count}" value="${counter.count}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" class="form-control" id="selectAll" name="selectAll" value="" disabled=""/>
                                        </c:otherwise>
                                    </c:choose>                                  
                                </td>
                                <td>${table.taxno}</td>
                                <td>${table.taxdate}</td>
                                <td>${table.arcode}</td>
                                <td>${table.taxinvname}</td>
                                <td align="right" class="money">${table.gross}</td>
                                <td align="right" class="money">${table.vat}</td>
                                <td align="right" class="money">${table.amount}</td>
                                <td align="center">${table.department}</td>
                                <td align="center">${table.status}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>    
                </div>
            </div>
            <div class="col-xs-12"><br></div>
            <div class="col-xs-12">
                <div class="col-xs-1 text-right" style="width: 890px"></div>
                <div class="col-xs-1 text-right" style="">
                    <button type="button" class="btn btn-success btn-default" onclick="postSaleVat()"  data-dismiss="modal">
                        <span id="btnPostSaleVat" class="glyphicon glyphicon-export" ></span> Post
                    </button>
                </div>    
            </div>
            <div class="col-xs-12"><br></div>
        </form>
    </div>
</div>
                    
<!--Export AR Modal-->
<div class="modal fade" id="postSaleVatModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Post Sale Vat</h4>
            </div>
            <div class="modal-body">
                <label class="text-right">Are you confirm to post tax invoice ?</label>
                <input type="hidden" id="chooseSaleVat" name="chooseSaleVat" value=""/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" onclick="confirmPostSaleVat()">
                    <span id="btnConfirmPostSaleVat" class="glyphicon" ></span> OK
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

//        var table = $('#postSaleVatDataListTable').dataTable({bJQueryUI: true,
//            "sPaginationType": "full_numbers",
//            "bAutoWidth": false,
//            "bFilter": false,
//            "bInfo": false,
//            "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
//            "iDisplayLength": 50,
//            "bSort": false,
//            "bPaginate": false
//        });
        
//        $('#postSaleVatDataListTable tbody').on('click', 'tr', function () {
//            if ($(this).hasClass('row_selected')) {
//                $(this).removeClass('row_selected');
//                $('#hdGridSelected').val('');
//            }
//            else {
//                table.$('tr.row_selected').removeClass('row_selected');
//                $(this).addClass('row_selected');
//                $('#hdGridSelected').val($('#postSaleVatDataListTable tbody tr.row_selected').attr("id"));
//            }
//        });
        
        $('#InputFromDate').datetimepicker().on('dp.change', function (e) {
            $('#PostSaleVatForm').bootstrapValidator('revalidateField', 'postFromDate');
        });
        $('#InputToDate').datetimepicker().on('dp.change', function (e) {
            $('#PostSaleVatForm').bootstrapValidator('revalidateField', 'postToDate');
        });
        
        $("#PostSaleVatForm").bootstrapValidator({
                    framework: 'bootstrap',
    //                container: 'tooltip',
                    feedbackIcons: {
                        valid: 'uk-icon-check',
                        invalid: 'uk-icon-times',
                        validating: 'uk-icon-refresh'
                    },
                    fields: {
                        postFromDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date From is required'
                                },
                                date: {
                                    format: 'YYYY-MM-DD',
                                    max: 'postToDate',
                                    message: 'The Date From is not a valid'
                                }
                            }
                        },
                        postToDate: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date To is required'
                                },
                                date: {
                                    format: 'YYYY-MM-DD',
                                    min: 'postFromDate',
                                    message: 'The Date To is not a valid'
                                }
                            }
                        },
                        postType: {
                            trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'Type is required'
                                }
                            }
                        }
                    }
                }).on('success.field.fv', function (e, data) {
            if (data.field === 'postFromDate' && data.fv.isValidField('postToDate') === false) {
                data.fv.revalidateField('postToDate');
            }

            if (data.field === 'postToDate' && data.fv.isValidField('postFromDate') === false) {
                data.fv.revalidateField('postFromDate');
            }
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
                if(page === 0){
                    $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + "First" + "  ").bind('click', {
                    newPage: page
                    }, function(event) {
                        currentPage = event.data['newPage'];
                        $table.trigger('repaginate');
                        $(this).addClass('active').siblings().removeClass('active');
                    }).appendTo($pager).addClass('clickable');
                }
                
                $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + (page + 1) + "  ").bind('click', {
                    newPage: page
                }, function(event) {
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                }).appendTo($pager).addClass('clickable');
                
                if(page === (numPages - 1)){
                    $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + "Last" + "  ").bind('click', {
                    newPage: page
                    }, function(event) {
                        currentPage = event.data['newPage'];
                        $table.trigger('repaginate');
                        $(this).addClass('active').siblings().removeClass('active');
                    }).appendTo($pager).addClass('clickable');
                }
            }
            $br.insertAfter($table).addClass('active');
            $pager.insertAfter($table).find('span.page-number:first').addClass('active');
            document.getElementById("pageNo").style.cursor="pointer";
        });

    });
    
    function postSaleVat(){
        var row = $('#postSaleVatDataListTable tr').length;     
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
            document.getElementById("chooseSaleVat").value = "false";
        } else {
            document.getElementById("chooseSaleVat").value = "true";
        }
        $("#textAlertDivSave").hide();
        $('#textAlertDivNotChoose').hide();
        $("#postSaleVatModal").modal("show");
    }
    
    function validateDate(date,option){
        if(option === 'over'){
            if(date === 'from'){
                $('#PostSaleVatForm').bootstrapValidator('revalidateField', 'postFromDate');
                $('#PostSaleVatForm').bootstrapValidator('revalidateField', 'postToDate');
            }
            if(date === 'to'){
                $('#PostSaleVatForm').bootstrapValidator('revalidateField', 'postFromDate');
                $('#PostSaleVatForm').bootstrapValidator('revalidateField', 'postToDate');
            }           
        } else {
            $('#PostSaleVatForm').bootstrapValidator('revalidateField', 'postFromDate');
            $('#PostSaleVatForm').bootstrapValidator('revalidateField', 'postToDate');
        }
    }
    
    function confirmPostSaleVat(){
        $("#postSaleVatModal").modal("hide");
        var chooseSaleVat = document.getElementById("chooseSaleVat").value;
        if(chooseSaleVat === 'true'){
            $('#action').val('postSaleVat');
            document.getElementById('PostSaleVatForm').submit();
        } else {
            $('#textAlertDivNotChoose').show();
        }    
    }
    
    function hideDiv(){
        $('#textAlertDivNotChoose').hide();
    }
    
    function searchPostSaleVat(){
        var action = $('#action').val();
        action.value = 'searchPostSaleVat';
    }
    
    function selectAll(){
        var row = $('#postSaleVatDataListTable tr').length;     
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
//            alert("5");
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
