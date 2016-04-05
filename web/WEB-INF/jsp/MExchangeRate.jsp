<%-- 
    Document   : MExchangeRate
    Created on : Dec 9, 2015, 8:49:44 AM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/MExchangeRate.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<c:set var="ExchangeList" value="${requestScope['ExchangeList']}" />
<c:set var="listCurrency" value="${requestScope['listCurrency']}" />
<section class="content-header" >
    <h1>
        Master Exchange Rate - Exchange Rate
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>        
        <li><a href="Mairticket.smi"> Master Change Rate</a></li>        
        <li class="active"><a href="Mairticket.smi">Change Rate</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            
        </div>
        <!-- main page -->
        <div class="col-md-10">
            <!--Alert Save --> 
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Success!</strong> 
            </div>
            <!--Alert Not Save --> 
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Not Success!</strong> 
            </div>
            <div id="textAlertDivUpdate"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Update Success!</strong> 
            </div>
            <div id="textAlertDivNotUpdate"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Update Not Success!</strong> 
            </div>
            <div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Delete Success!</strong> 
            </div>
            <div id="textAlertDivNotDelete"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Delete Not Success!</strong> 
            </div>
            <!-- Alert Uni-->
            <div id="textAlertDuplicate"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Currency and exchange date already exist!</strong> 
            </div>
            <div class="row" style="padding-left: 15px">
                <form action="MExchangeRate.smi" method="post" id="SearchExchange" name="SearchExchange"  role="form">
                    <div class="col-md-2 ">
                        <div class="form-group">
                            <label>From</label>
                            <div class='input-group date fromdate' id='fromdatepanel'> 
                                <c:set var="FromDate" value="${fromdate}" />
                                <fmt:parseDate value="${FromDate}" var="FromDate" pattern="yyyy-MM-dd" />
                                <fmt:formatDate value="${FromDate}" var="FromDate" pattern="dd-MM-yyyy" />
                                <input id="FromDate" name="FromDate"  type="text" 
                                   class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${FromDate}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                                          
                            </div>
                        </div>
                    </div>
                                
                     <div class="col-md-2 ">
                        <div class="form-group">
                            <label>To</label>
                            <div class='input-group date todate' id='todatepanel'> 
                                <c:set var="ToDate" value="${todate}" />
                                <fmt:parseDate value="${ToDate}" var="ToDate" pattern="yyyy-MM-dd" />
                                <fmt:formatDate value="${ToDate}" var="ToDate" pattern="dd-MM-yyyy" />
                                <input id="ToDate" name="ToDate"  type="text" 
                                   class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="${ToDate}">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                        
                            </div>
                        </div>
                    </div>            
                                
                    <div class="col-md-2 ">
                        <div class="form-group">
                            <label>Currency</label>
                            <select class="form-control" id="CurrencyS" name="CurrencyS">
                                <option value="">--select--</option>
                                <c:forEach var="cur" items="${listCurrency}">
                                    <c:set var="selectA" value="" />
                                    <c:if test="${cur.code == currency_exchange}">
                                        <c:set var="selectA" value="selected" />
                                    </c:if> 
                                    <option value='${cur.code}' ${selectA}>${cur.code}</option>
                                </c:forEach>
                            </select>  
                        </div>
                    </div>      
                                
                    <div class="col-md-2">
                        <div style="padding-top: 20px">   
                            <button type="button" id="ButtonSearch"  name="ButtonSearch" onclick="searchExchange()"  class="btn btn-primary"><span class="fa fa-search"></span>Search</button>           
                            <input type="hidden" name="actionSearch" id="actionSearch"/>
                        </div>
                    </div>
                </form>

            </div>
            <hr>
            <div class="row" style="padding-left: 15px">  
                <div class="col-md-6">
                    <h4><b>Exchange Rate</b></h4>
                </div>
                <div class="col-md-6 " style="padding-left:  182px">
                    <button id="btnAdd" type="button" class="btn btn-success" onclick="addaction()"  data-toggle="modal" data-target="#ExchangeRateModal">
                        <span id="spanAdd" class="glyphicon glyphicon-plus"></span>Add
                    </button>
                </div>

            </div>
            <div class="row" style="padding-left: 15px">    
                <div class="col-md-9"> 
                    <table id="ExchangeRateTable" class="display" cellspacing="0" >
                        <thead>
                            <tr class="datatable-header">
                                <th class="hidden" ></th>
                                <th>Date</th>
                                <th>Currency</th>
                                <th>Exchange Rate</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="table" items="${ExchangeList}" varStatus="dataStatus">
                            <tr>
                                <td class="hidden" ><c:out value="${table.id}" /></td>
                                <td class="text-center"><fmt:formatDate value="${table.exdate}" var="exdate" pattern="dd-MM-yyyy" /><c:out value="${exdate}"  /></td>
                                <td class="text-center"><c:out value="${fn:toUpperCase(table.currency)}"/></td>
                                <td class="text-right"><fmt:formatNumber type="currency" pattern="#,##0.0000" value="${fn:toUpperCase(table.exrate)}" /></td>
                                <td>
                                <center> 
                                    <span id="editSpan${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      onclick="EditExchange('${table.id}', '${exdate}', '${table.currency}', '${table.exrate}', '${table.createby}', '${table.createdate}')" data-toggle="modal" data-target="#ExchangeRateModal" ></span>
                                    <span id="removeSpan${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteExchange('${table.id}', '${table.exdate}', '${table.currency}')" data-toggle="modal" data-target="#DeleteExchangeModal"></span>
                                </center>
                                </td>                 
                            </tr>  
                        </c:forEach>
                        </tbody>
                    </table>    
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="ExchangeRateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <form action="MExchangeRate.smi" method="post" id="ExchangeRateForm" name="ExchangeRateForm" class="form-horizontal"  role="form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Exchange Rate</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group" style="width: 480px;padding-left: 20px;">
                        <div id="textAlertDivDecimal"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert" >
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <strong>Please Input 4 decimal point!</strong> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ExchangeRate" class="col-sm-4 control-label" >Exchange Date<font style="color: red">*</font></label>
                        <div class="col-sm-7 " > 
                            <div class='input-group date ' id="DateExchange">
                                <input id="ExchangeDate" name="ExchangeDate"  type="text" 
                                   class="form-control datemask" data-date-format="DD-MM-YYYY" placeholder="DD-MM-YYYY" value="">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>                                                                          
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ExchangeRate" class="col-sm-4 control-label" >Exchange Rate </label>
                        <div class="col-sm-7">  
                            <input type="text" class="form-control decimalexrate" maxlength="50" id="ExchangeRate" name="ExchangeRate" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Code3" class="col-sm-4 control-label" >Currency <font style="color: red">*</font></label>
                        <div class="col-sm-7">   
                            <select class="form-control" id="Currency" name="Currency">
                            <option value="">--select--</option>
                            <c:forEach var="cur" items="${listCurrency}">
                                <c:set var="selectA" value="" />
                                <c:if test="${cur.code == currency_exchange}">
                                    <c:set var="selectA" value="selected" />
                                </c:if> 
                                <option value='${cur.code}' ${selectA}>${cur.code}</option>
                            </c:forEach>
                        </select> 
                        </div>
                    </div>
                    <input type="hidden" id="ExchangeID" name="ExchangeID" >
                    <input type="hidden" id="action" name="action">

                </div>
                <div class="modal-footer">
                    <button id="btnSave" type="button" onclick="saveMExchangeRate()" class="btn btn-success"><span  class="fa fa-save"></span> Save</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="DeleteExchangeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Exchange</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<c:if test="${! empty requestScope['result']}">
    <c:if test="${requestScope['result'] =='save success'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='save unsuccess'}">        
        <script language="javascript">
           $('#textAlertDivNotSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='update success'}">        
        <script language="javascript">
            $('#textAlertDivUpdate').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='update unsuccess'}">        
        <script language="javascript">
           $('#textAlertDivNotUpdate').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='delete success'}">        
        <script language="javascript">
            $('#textAlertDivDelete').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='delete unsuccess'}">        
        <script language="javascript">
           $('#textAlertDivNotDelete').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='duplicate'}">        
        <script language="javascript">
           $('#textAlertDuplicate').show();
        </script>
    </c:if>
</c:if>
  <script type="text/javascript" charset="utf-8">      
$(document).ready(function() {
    $('.date').datetimepicker();
    $('.datemask').mask('00-00-0000');
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    
    var table = $('#ExchangeRateTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bInfo": false
    });   
    
    $(".decimalexrate").inputmask({
        alias: "decimal",
        integerDigits: 6,
        groupSeparator: ',',
        autoGroup: true,
        digits: 4,
        allowMinus: false,
        digitsOptional: false,
        placeholder: "0.0000",
    });
    
    $('#DateExchange').datetimepicker().on('dp.change', function (e) {
        $('#ExchangeRateForm').bootstrapValidator('revalidateField', 'ExchangeDate');
    });
        
    $('#ExchangeRateForm').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            ExchangeDate: {
                validators: {
                    notEmpty: {
                        message: 'The exchange date is required'
                    }
                }
            },
            Currency: {
                validators: {
                    notEmpty: {
                        message: 'The currency is required'
                    }
                }
            }
        }
    });
        
    $('.fromdate').datetimepicker().change(function(){                          
        checkFromDateField();
    });
    $('.todate').datetimepicker().change(function(){                          
        checkToDateField();
    });
});

    function checkFromDateField(){      
        var inputFromDate = document.getElementById("FromDate");
        var InputToDate = document.getElementById("ToDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");  
            $("#ButtonSearch").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success");
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#ButtonSearch").addClass("disabled");
        } else {
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#issuefromdatepanel").removeClass("has-error");
            $("#issuetodatepanel").removeClass("has-error");
            $("#fromdatepanel").addClass("has-success");
            $("#todatepanel").addClass("has-success");
            $("#ButtonSearch").removeClass("disabled");
            checkDateValue("from","");
        }
    }
    
    function checkToDateField(){
        var InputToDate = document.getElementById("ToDate");
        var inputFromDate = document.getElementById("FromDate");
        if(InputToDate.value === '' && inputFromDate.value === ''){
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");  
            $("#ButtonSearch").removeClass("disabled");
        }else if(inputFromDate.value === '' || InputToDate.value === ''){
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success");
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#ButtonSearch").addClass("disabled");
        }else{
            $("#fromdatepanel").removeClass("has-error");
            $("#todatepanel").removeClass("has-error");
            $("#issuefromdatepanel").removeClass("has-error");
            $("#issuetodatepanel").removeClass("has-error");
            $("#fromdatepanel").addClass("has-success");
            $("#todatepanel").addClass("has-success");
            $("#ButtonSearch").removeClass("disabled");
            checkDateValue("to","");
        }       
    }
    
    function checkDateValue(date){
        var inputFromDate = document.getElementById("FromDate");
        var InputToDate = document.getElementById("ToDate");
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
            $("#fromdatepanel").removeClass("has-success");
            $("#fromdatepanel").addClass("has-error");                                 
            $("#todatepanel").removeClass("has-success");
            $("#todatepanel").addClass("has-error");   
            $("#ButtonSearch").addClass("disabled");
        } else {
            $("#fromdatepanel").removeClass("has-success");
            $("#todatepanel").removeClass("has-success"); 
            $("#fromdatepanel").addClass("has-error");
            $("#todatepanel").addClass("has-error");
            $("#ButtonSearch").addClass("disabled");
        }
    }       
</script>