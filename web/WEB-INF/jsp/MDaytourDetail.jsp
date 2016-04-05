<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/MDaytourDetail.js"></script> 
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="categoryList" value="${requestScope['category_list']}" />
<c:set var="currencyList" value="${requestScope['currency_list']}" />
<c:set var="daytour" value="${requestScope['DAYTOUR']}" />
<c:set var="daytourPrices" value="${requestScope['DAYTOURPRICES']}" />
<c:set var="daytourExpenses" value="${requestScope['DAYTOUREXPENSES']}" />
<c:set var="disableMDaytourCode" value="${requestScope['disableMDaytourCode']}" />

<section class="content-header">
    <h4>
        <b>Master : Day Tours</b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>  
        <li><a href="#">Day Tours MA</a></li>
        <li class="active"><a href="#">Day Tours Detail</a></li>
    </ol>
</section>
<!--<input type="text" value="${param.result}">-->
<!--<input type="text" value="${requestScope['VALIDATE']}">-->
<div class ="container"  style="padding-top: 15px;"> 
    <div class="col-md-10  col-md-offset-1">
        <form id="MDaytourDetailForm" name="MDaytourDetailForm" action="MDaytourDetail.smi" method="post" role="form" >
            <div class="panel panel-default">
                <!-- Alert Uni-->
                <div id="textAlertLap"  style="display:none;" class="alert alert-danger" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>tour code already exist!</strong> 
                </div>
                <!-- Alert Not Update-->
                <div id="textAlertDivNotUpdate"  style="display:none;" class="alert alert-danger" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Update unsuccessful!</strong> 
                </div>
                <!--Alert Not Save --> 
                <div id="textAlertDivUpdate"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Update Success!</strong> 
                </div>
                <div class="panel-heading">Detail</div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <div class="col-md-6 col-md-offset-6 text-right">
                                <a id="ButtonBack" name="ButtonBack" href="MDaytour.smi" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Back</a>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <div class="col-md-5">
                                <div class="col-md-3 text-right"><label class="form-label" for="InputCode">Code<font style="color: red">*</font></label></div>
                                <div class="col-md-9"><input id="InputCode" name="InputCode" class="form-control" maxlenght="50" style="text-transform:uppercase"  
                                                             value="${daytour.code}" ${disableMDaytourCode} ></div>
                            </div>
                            <div class="col-md-2 text-right"><label  class="form-label text-left" for="InputName">Name<font style="color: red">*</font></label></div>
                            <div class="col-md-5"><input id="InputName" name="InputName" class="form-control" maxlength="255" style="text-transform:uppercase" 
                                                         value="${daytour.name}" required="true"></div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 ">
                            <div class="col-md-5">
                                <div class="col-md-3 text-right"><label class="control-label " for="InputMinMax">Min/Max</label></div>
                                <div class="col-md-2 form-group" id="Min">
                                    <input type="number" id="InputMin" name="InputMin" style="width: 50px" 
                                           class="form-control"  max="InputMax"  min="0"
                                           maxlength="3" value="${daytour.min}">
                                </div>
                                <div class="col-md-1">/</div>
                                <div class="col-md-2 form-group" id="Max">
                                    <input type="number" id="InputMax" name="InputMax" style="width: 50px"  
                                           class="form-control" min="InputMin"  
                                           maxlength="3" value="${daytour.max}">
                                </div>
                            </div>
                            <div class="col-md-2 text-right"><label class="control-label " for="InputGuideCommission">Guide&nbsp;Commission</label></div>
                            <div class="col-md-2 "><input id="InputGuideCommission" name="InputGuideCommission" class="form-control text-right" value="${daytour.guideComission}" placeholder="0.00" maxlength="11"></div>
                            <div class="col-md-1 text-right"><label class="control-label " for="InputStatus">Status</label></div>
                            <div class="col-md-2">
                                <select name="InputStatus" id="InputStatus"  class="form-control">
                                    <option value="active" ${daytour.status.equals("active") ? "selected" : ""}>Active</option>
                                    <option value="inactive" ${daytour.status.equals("inactive") ? "selected" : ""}>Inactive</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 ">
                            <div class="col-md-5">
                                <div class="col-md-3 text-right"><label class="control-label" for="TextareaRemark">Remark</label></div>
                                <div class="col-md-9"><textarea id="TextareaRemark" name="TextareaRemark" class="form-control" style="resize: none" maxlength="255">${daytour.remark}</textarea></div>
                            </div>
                            <div class="col-md-2 text-right"><label class="control-label" for="TextareaCondition">Condition</label></div>
                            <div class="col-md-5"><textarea id="TextareaCondition" name="TextareaCondition" class="form-control" style="resize: none" maxlength="255">${daytour.condition}</textarea></div>
                        </div>
                    </div>

                    <div class="col-xs-12 ">
                        <div class="col-xs-1"><label class="control-label">Price</label></div>
                        <table class="display table-striped table-responsive" id="PriceTable" name="PriceTable">
                            <thead>
                                <tr class="datatable-header">
                                    <th style="width: 25%">Category</th>
                                    <th style="width: 42%">Detail</th>
                                    <th style="width: 15%">Price</th>
                                    <th style="width: 10%">Cur</th>
                                    <th style="width: 8%">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--simulate rowPrice For new add  BEGIN-->
                                <tr class="hide">
                                    <td class="hidden"><input type="text" id="priceInputId-" name="priceInputId-" class="hidden" /></td>
                                    <td>
                                        <select id="priceRowSelectCat-" name="priceRowSelectCat-" class="form-control">
                                            <option value="0" selected="selected"></option>
                                            <c:forEach var="category" items="${categoryList}" >
                                                <option value="${category.id}" >${category.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>

                                    <td><input type="text" id="priceInputDetail-" name="priceInputDetail-" class="form-control" maxlength="255"></td>
                                    <td><input type="text" id="priceInputPrice-" name="priceInputPrice-" class="form-control text-right decimal" maxlength="11"></td>
                                    <td>
                                        <select class="form-control" id="priceRowSelectCur-" name="priceRowSelectCur-">
                                            <option value="0" selected="selected"></option>
                                            <c:forEach var="currency" items="${currencyList}">
                                                <c:set var="select" value="" />
                                                <option value="<c:out value="${currency.code}" />" ><c:out value="${currency.code}" /></option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td class="text-center">
                                        <span  id="PrictRowButtonDelete-" name="PrictRowButtonDelete-"
                                              class="glyphicon glyphicon-remove deleteicon hidden"  
                                              onclick="DeletePrice(null, null,this)"
                                              data-toggle="modal" data-target="" >
                                        </span>      
                                    </td>
                                </tr>
                                <!--simulate rowPrice For new add  END-->
                            <input type="hidden"  id="counterPrice" name="counterPrice" class="" value="1" />
                            <c:forEach var="daytourPrice" items="${daytourPrices}" varStatus="loopCounter">
                                <tr>
                                    <td class="hidden"><input type="text" class="hidden" id="priceInputId-${loopCounter.count}" name="priceInputId-${loopCounter.count}" value="${daytourPrice.id}" /></td>
                                    <td>
                                        <select id="priceRowSelectCat-${loopCounter.count}" name="priceRowSelectCat-${loopCounter.count}" class="form-control">
                                            <option value="0" selected="selected"></option>
                                            <c:forEach var="category" items="${categoryList}" >
                                                <c:set var="select" value=""  />
                                                <c:if test="${category.id == daytourPrice.MPricecategory.id}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${category.id}" ${select}>${category.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>

                                    <td><input type="text"  class="form-control" name="priceInputDetail-${loopCounter.count}" id="priceInputDetail-${loopCounter.count}" value="<c:out value="${daytourPrice.detail}"/>"></td>
                                    <td><input type="text" class="form-control text-right decimal" name="priceInputPrice-${loopCounter.count}" id="priceInputPrice-${loopCounter.count}" 
                                               value="<c:out value="${daytourPrice.price}"/>" maxlength="11"></td>
                                    <td>
                                        <select id="priceRowSelectCur-${loopCounter.count}" name="priceRowSelectCur-${loopCounter.count}" class="form-control">
                                            <option value="0" selected="selected"></option>
                                            <c:forEach var="currency" items="${currencyList}">
                                                <c:set var="select" value="" />
                                                <c:if test="${currency.code == daytourPrice.currency}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="<c:out value="${currency.code}" />" ${select}><c:out value="${currency.code}" /></option>
                                            </c:forEach>
                                        </select>

                                    </td>
                                    <td class="text-center">
                                        <span id="PrictRowButtonDelete-${loopCounter.count}" name="PrictRowButtonDelete-${loopCounter.count}" 
                                              class="glyphicon glyphicon-remove deleteicon remPrice"  
                                              onclick="DeletePrice('${daytourPrice.id}', '${daytourPrice.detail}',this)"
                                              data-toggle="modal" data-target="#DelPrice" >
                                        </span>      
                                    </td>
                                    <c:if test="${airdesc.last}">
                                    <input value="${airdesc.count}" id="airDescRows" name="airDescRows" type="text" class="hidden" />
                                </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div id="tr_AddRowPrice" class="text-center hide" style="padding-top: 10px">
                            <a id="ButtonAddPrice" class="btn btn-success" onclick="AddRowPrice()">
                                <i id="IGlyphiconPluse" class="glyphicon glyphicon-plus"></i> Add
                            </a>
                        </div>
                    </div>                  
                    <div class="col-xs-1 form-group"></div>                       
                    <div class="col-xs-12 ">
                        <div class="col-xs-1"><label class="form-label">Expense</label></div>
                        <table class="display" id="ExpenseTable">
                            <thead>
                                <tr class="datatable-header">
                                    <th style="width: 50%">Description</th>
                                    <th style="width: 20%">Amount</th>
                                    <th style="width: 10%">Cur</th>
                                    <th style="width: 12%">Type</th>
                                    <th style="width: 8%">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--simulate rowExpense For new add  BEGIN-->
                                    <tr class="hide">
                                        <td class="hidden"><input type="text" id="expenseInputId-" name="expenseInputId-" class="hidden" /></td>
                                        <td><input type="text" name="expenseInputDescription-" id="expenseInputDescription-" class="form-control" maxlength="255"></td>
                                        <td><input type="text" class="form-control text-right decimal" name="expenseInputAmount-" id="expenseInputAmount-"  maxlength="11" ></td>
                                        <td>
                                            <select id="ExpenseRowCur-" name="ExpenseRowCur-" class="form-control">
                                                <option value="0" selected="selected"></option>
                                                <c:forEach var="currency" items="${currencyList}">
                                                    <option value="<c:out value="${currency.code}" />" ><c:out value="${currency.code}" /></option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td class="text-center">
                                            <input type="radio"  name="price_type-" value="S" checked >&nbsp;&nbsp;S&nbsp;&nbsp;&nbsp;
                                            <input type="radio"  name="price_type-" value="G" >&nbsp;&nbsp;G
                                        </td>
                                        <td class="text-center">
                                            <span id="ExpenseRowButtonDelete-" name="ExpenseRowButtonDelete-" 
                                                  class="glyphicon glyphicon-remove deleteicon"
                                                  onclick="DeleteExpense(null, null,this)" data-toggle="modal" 
                                                  data-target="">
                                            </span>
                                        </td>

                                    </tr>
                                <!--simulate rowExpense For new add  END-->
                            <input type="hidden"  id="counterExpense" name="counterExpense" value="1" />
                                <c:forEach var="daytourExpense" items="${daytourExpenses}" varStatus="loopCounter">
                                    <tr>
                                        <td class="hidden"><input type="text" class="hidden" id="expenseInputId-${loopCounter.count}" name="expenseInputId-${loopCounter.count}" value="${daytourExpense.id}" /></td>
                                        <td><input type="text" name="expenseInputDescription-${loopCounter.count}" id="expenseInputDescription-${loopCounter.count}" class="form-control" value="${daytourExpense.description}" maxlength="255"></td>
                                        <td><input type="text" class="form-control text-right decimal" name="expenseInputAmount-${loopCounter.count}" id="expenseInputAmount-${loopCounter.count}"   value="${daytourExpense.amount}" maxlength="11" ></td>
                                        <td>
                                            <select id="ExpenseRowCur-${loopCounter.count}" name="ExpenseRowCur-${loopCounter.count}" class="form-control">
                                                <option value="0" selected="selected"></option>
                                                <c:forEach var="currency" items="${currencyList}">
                                                    <c:set var="select" value="" />
                                                    <c:if test="${currency.code == daytourExpense.currency}">
                                                        <c:set var="select" value="selected" />
                                                    </c:if>
                                                    <option value="<c:out value="${currency.code}" />" ${select}><c:out value="${currency.code}" /></option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td class="text-center">
                                            <input type="radio" value="S" name="price_type-${loopCounter.count}" ${daytourExpense.priceType.equals("S") ? 'checked' : ''}>&nbsp;&nbsp;S&nbsp;&nbsp;&nbsp;
                                            <input type="radio" value="G" name="price_type-${loopCounter.count}" ${daytourExpense.priceType.equals("G") ? 'checked' : ''}>&nbsp;&nbsp;G
                                        </td>
                                        <td class="text-center">
                                            <span id="ExpenseRowButtonDelete-${loopCounter.count}" name="ExpenseRowButtonDelete-${loopCounter.count}" 
                                                  class="glyphicon glyphicon-remove deleteicon"
                                                  onclick="DeleteExpense('${daytourExpense.id}', '${daytourExpense.description}',this)" data-toggle="modal" 
                                                  data-target="#DelExpense">
                                            </span>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-xs-1 form-group"></div>    
                    <input name="daytourid" value="${daytour.id}"type="hidden">
                    <input type="hidden" id="action" name="action" value="save">
                    <div class="col-xs-12 text-center">
                        <button id="ButtonSave" name="ButtonSave" type="submit"  class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
                 
<!--Price Delete Modal-->
<div class="modal fade" id="DelPrice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="" method="post" id="DelPriceForm" class="form-horizontal"  role="form">            
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"> Delete Price </h4>
                </div>
                <div class="modal-body" id="delTextPrice"></div>
                <input type="hidden" id="deletePriceId" name="priceId"/>
                <input type="hidden" id="deletePriceAction" name="actionPrice" value="delete"/>
                <div class="modal-footer">
                    <button id="btnPriceDelete" type="button" class="btn btn-danger">Delete</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->                        

<!--Expense Delete Modal-->
<div class="modal fade" id="DelExpense" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="" method="post" id="DelExpenseForm" class="form-horizontal"  role="form">            
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"> Delete Expense </h4>
                </div>
                <div class="modal-body" id="delTextExpense"></div>
                <input type="hidden" id="deleteExpenseId" name="expenseId"/>
                <input type="hidden" id="deleteExpenseAction" name="actionExpense" value="delete"/>
                <div class="modal-footer">
                    <button id="btnExpenseDelete" type="button" class="btn btn-danger">Delete</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript" charset="utf-8">
    function DeletePrice(id, detail,objspan) {
        var countPrice =   $("#PriceTable tbody").find("tr").length;
        if($("#PriceTable tbody").find("tr").length !== 2){
            if(id !== null ){
                $('#deletePriceAction').val('delete');
                $('#deletePriceId').val(id);
                $('#delTextPrice').html(" Are you sure to delete price : " + detail + " ? ");
                $('#btnPriceDelete').click(function (e) {
                    $.ajax({
                        url: 'MDaytourDetail.smi?priceId='+id+'&actionPrice=delete',
                        type: 'POST',
                        data: {},
                        success: function () {
                            if (${! empty requestScope['resultPrice']}) {
                                alert('${requestScope['resultPrice']}');
                            }
                            location.reload();
                        },
                        error: function () {
                            console.log("error price");
                        }
                    });
                }); 
            }else{
                $(objspan).closest('tr').remove();
                $('#counterPrice').val(countPrice-1);
            }
        }else{
            if(id !== null ){
                $('#deletePriceAction').val('delete');
                $('#deletePriceId').val(id);
                $('#delTextPrice').html(" Are you sure to delete price : " + detail + " ? ");
                $('#btnPriceDelete').click(function (e) {
                    $.ajax({
                        url: 'MDaytourDetail.smi?priceId='+id+'&actionPrice=delete',
                        type: 'POST',
                        data: {},
                        success: function () {
                            if (${! empty requestScope['resultPrice']}) {
                                alert('${requestScope['resultPrice']}');
                            }
                            location.reload();
                        },
                        error: function () {
                            console.log("error price");
                        }
                    });
                });
            }else{
                alert('this row for add data');
            }
        }
    }


    function DeleteExpense(id, description,objspan) {
        var countExpense =   $("#ExpenseTable tbody").find("tr").length;
        if($("#ExpenseTable tbody").find("tr").length !== 2){
                if(id !== null){
                    $('#deleteExpenseAction').val("delete");
                    $('#deleteExpenseId').val(id);
                    $('#delTextExpense').html("Are you sure to delete expense :" + description + " ?");
                    $('#btnExpenseDelete').click(function (e) {
                        $.ajax({
                            url: 'MDaytourDetail.smi?expenseId='+id+'&actionExpense=delete',
                            type: 'POST',
                            data: {},
                            success: function () {
                                if (${! empty requestScope['resultExpense']}) {
                                    alert('${requestScope['resultExpense']}');
                                }
                                location.reload();
                            },
                            error: function () {
                                console.log("error expense");
                            }
                        });
                    });
                }else{
                        $(objspan).closest('tr').remove();
                        $('#counterExpense').val(countExpense-1);
                }

            }else{
                if(id !== null){
                    $('#deleteExpenseAction').val("delete");
                    $('#deleteExpenseId').val(id);
                    $('#delTextExpense').html("Are you sure to delete expense :" + description + " ?");
                    $('#btnExpenseDelete').click(function (e) {
                        $.ajax({
                            url: 'MDaytourDetail.smi?expenseId='+id+'&actionExpense=delete',
                            type: 'POST',
                            data: {},
                            success: function () {
                                if (${! empty requestScope['resultExpense']}) {
                                    alert('${requestScope['resultExpense']}');
                                }
                                location.reload();
                            },
                            error: function () {
                                console.log("error expense");
                            }
                        });
                    });
                }else{
                     alert('this row for add data');
                }
        }
    }
</script>
<c:if test="${! empty param.result}">
    <c:if test="${param.result =='success'}">            
        <script language="javascript">
           $('#textAlertDivUpdate').show();
        </script>
    </c:if>
    <c:if test="${param.result =='fail'}">        
        <script language="javascript">
             $('#textAlertDivNotUpdate').show();
        </script>
    </c:if>
</c:if>     
<c:if test="${! empty requestScope['VALIDATE']}">
    <c:if test="${requestScope['VALIDATE'] =='tour code already exist'}">        
        <script language="javascript">
           $('#textAlertLap').show();
        </script>
    </c:if>
</c:if>
        
<script type="text/javascript" charset="utf-8">

    $(document).ready(function() {    
        $(".decimal").inputmask({
            alias: "decimal",
            integerDigits: 8,
            groupSeparator: ',',
            autoGroup: true,
            digits: 0,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0"
        });
        $('.datemask').mask('00-00-0000');
    
    });
        
        
</script>