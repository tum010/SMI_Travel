<%-- 
    Document   : MProductCommissionDetail
    Created on : Jun 3, 2015, 3:02:20 PM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="ListProduct" value="${requestScope['ListProduct']}" />

<section class="content-header"  >
    <h1>
        <b>Master : Product Commission</b>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>  
        <li><a href="#">Product Commission</a></li>
        <li class="active"><a href="#">Product Commission Detail</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;">
    <!--Alert Check Date-->
    <div class="row" style="display:none;" id="AlertCheckDate">
        <div class="col-md-10 col-md-offset-1">
            <div class="alert alert-warning alert-dismissable fade  in" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-lable="Close"><span aria-hidden="true">X</span></button>
                <strong>Please Check Date From And Date To!!!</strong>
            </div>
        </div>
    </div>
    <!--Content -->
    <div class="col-md-10  col-md-offset-1">
        <form action="MProductCommissionDetail.smi" method="post" role="form" onsubmit="return validateSubmit();" >
            <div class="panel panel-default">
                <div class="panel-heading">Detail</div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <div class="col-md-6 col-md-offset-6 text-right">
                                <a id="ButtonBack" name="ButtonBack" href="MProductCommission.smi" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Back</a>
                            </div>
                        </div>
                    </div>

                    <div class="row">         
                            <div class="row" >
                                <div class="col-md-12 form-group" >
                                    <div class="col-md-5">
                                        <div class="col-md-2 text-right"><label class="form-label" for="InputAgent">Product</label></div>

                                        <div class="col-md-5">
                                            <div class="input-group">
                                                <input id="InputProductId" name="InputProductId" type="hidden" class="form-control" value="">
                                                <input id="InputProductCode" name="InputProductCode" class="form-control" maxlenght="50" value="" >
                                                <span id="agentSearchButton" name="agentSearchButton" class="input-group-addon" data-toggle="modal" data-target="#ProductModal">
                                                    <span id="agentSearchButtonIcon" name="agentSearchButtonIcon" class="glyphicon-search glyphicon"></span>
                                                </span>
                                            </div>
                                        </div>

                                        <div class="col-md-5"><input id="InputProductName" name="InputProductName" class="form-control" style="width: 170px" value=""  readonly  ></div>
                                    </div> 
                                </div>
                            </div>
                    </div>

                    <!--table add-->
                    <div class="col-xs-12 form-group">
                        <label class="control-label">Product Commission</label>
                        <table class="display" id="commissionTable" name="commissionTable">
                            <thead>
                                <tr class="datatable-header">
                                    <th style="width: 10%">From</th>
                                    <th style="width: 10%">To</th>
                                    <th style="width: 5%">Cur</th>
                                    <th style="width: 5%">Commission</th>
                                    <th style="width: 2%">Action</th>
                                </tr>
                            </thead>
                            <tbody> 
                                <!--Simulate Row begin-->
                                <tr  class="hide"> 
                                    <td class="hidden"><input type="text" class="form-control text-center" name="InputId-" id="InputId-" value=""></td>
                                    <!--1. From Date Input -->
                                    <td>
                                        <input type="text" class="form-control text-center datemask" 
                                        name="InputFrom-" id="InputFrom-" 
                                        placeholder="DD-MM-YYYY" value="">
                                    </td>
                                    <!--2. To Date Input -->
                                    <td>
                                        <input type="text" class="form-control text-center datemask" 
                                        name="InputTo-" id="InputTo-" 
                                        placeholder="DD-MM-YYYY" value="">
                                    </td>
                                    <!--3. Current Input -->
                                    <td>
                                        <select id="current-" name="current-" class="form-control">
                                            <option value selected="selected"></option>
                                            <option>test1</option>
                                            <option>test2</option>
                                            <option>test3</option>
                                        </select>
                                    </td>
                                    <!--4. Commission Input -->
                                    <td>
                                        <input type="text" class="form-control text-right decimal "  
                                        name="InputCommission-" id="InputCommission-"  
                                        placeholder="0.00" maxlength="10"  value="">
                                    </td>
                                    <!--5. Action Delete -->
                                    <td class="text-center">
                                        <span id="deleteTourCommissionRow-" name="deleteTourCommissionRow-" 
                                              onclick="DeleteCommissionRow(null, this)" 
                                              class="glyphicon glyphicon-remove deleteicon" 
                                              data-toggle="modal" data-target="" >    
                                        </span>
                                    </td>
                                </tr> 
                            <!--Simulate Row end-->
                            <input type="hidden" id="counterCommission" name="counterCommission" value="1">
                            <c:forEach var="item" items="${AgentTourCommissions}" varStatus="loop">
                                <tr> 
                                    <c:if test="${item.from.date  < 10}">
                                        <c:set var="datefrom" value="0${item.from.date}" />
                                    </c:if>
                                    <c:if test="${item.from.date >= 10}">
                                        <c:set var="datefrom" value="${item.from.date}" />
                                    </c:if>
                                    <c:if test="${item.to.date  < 10}">
                                        <c:set var="dateto" value="0${item.to.date}" />
                                    </c:if>
                                    <c:if test="${item.to.date >= 10}">
                                        <c:set var="dateto" value="${item.to.date}" />
                                    </c:if>

                                    <c:if test="${item.from.month  < 10}">
                                        <c:set var="monthfrom" value="0${item.from.month+1}" />
                                    </c:if>
                                    <c:if test="${item.from.month  >= 10}">
                                        <c:set var="monthfrom" value="${item.from.month+1}" />
                                    </c:if>
                                    <c:if test="${item.to.month  < 10}">
                                        <c:set var="monthto" value="0${item.to.month+1}" />
                                    </c:if>
                                    <c:if test="${item.to.month  >= 10}">
                                        <c:set var="monthto" value="${item.to.month+1}" />
                                    </c:if>
                                    <td class="text-center">${loop.count}</td>
                                    <td class="hidden"><input type="text" class="form-control text-center" name="InputId-${loop.count}" id="InputId-${loop.count}" value="${item.id}"></td>
                                    <td>
                                        <input type="text" class="form-control text-center datemask" 
                                               name="InputFrom-${loop.count}" autocomplete="off" 
                                               id="InputFrom-${loop.count}"  placeholder="YYYY-MM-MM" 
                                               value="${datefrom}-${monthfrom}-${(item.from.year+1900)}">
                                    </td>
                                    <td>
                                        <input type="text" class="form-control text-center datemask" 
                                               name="InputTo-${loop.count}" autocomplete="off" 
                                               id="InputTo-${loop.count}"  placeholder="YYYY-MM-MM" 
                                               value="${dateto}-${monthto}-${(item.to.year+1900)}">
                                    </td>
                                    <td class=""><input type="text" class="form-control text-right decimal"  name="InputCommission-${loop.count}" id="InputCommissionRow-${loop.count}" 
                                                        placeholder="0.00" maxlength="10"  value="${item.comission * 10}"></td>
                                    <td class="text-center">
                                        <span id="deleteTourCommissionRow-${loop.count}" name="deleteTourCommissionRow-${loop.count}" 
                                              class="glyphicon glyphicon-remove deleteicon"  
                                              onclick="DeleteCommissionRow('${item.id}', this)"
                                              data-toggle="modal" data-target="#DelCommission" >
                                        </span>      
                                    </td>
                                </tr> 
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-xs-12 text-center">
                        <input type="hidden" id="action" name="action" value="save" />
                        <input type="hidden" id="AgentComID" name="AgentComID" value="${requestScope['AgentComID']}" />
                        <input type="hidden" id="agentCommissionId" name="agentCommissionId" value="${requestScope['agentCommissionId']}" />
                        <button id="ButtonSave" name="ButtonSave" type="submit" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<!--Product Modal-->
<div class="modal fade" id="ProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Product</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="productTable" name="productTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th style="width:20%">Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <script>
                        agentArray = [];
                    </script>
                    <tbody>
                        <c:forEach var="product" items="${ListProduct}">
                            <tr>
                                <td class="object-id hidden">${product.id}</td>
                                <td class="object-code">${product.code}</td>
                                <td class="object-name">${product.name}</td>
                            </tr>
                            <script>
                                agentArray.push({id: "${product.id}", code: "${product.code}", name: "${product.name}"});
                            </script>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Script Product List table-->
            <script>
                $(document).ready(function () {
                    $("#productTable tr").on('click', function () {
                        agent_id = $(this).find(".object-id").text();
                        agent_code = $(this).find(".object-code").text();
                        agent_name = $(this).find(".object-name").text();
                        $("#InputProductId").val(agent_id);
                        $("#InputProductCode").val(agent_code);
                        $("#InputProductName").val(agent_name);
//                        alert("Tour id[" + $("#InputTourId").val() + "] name[" + $("#InputTourName").val() + "] code[" + $("#InputTourCode").val() + "]");
                        $("#ProductModal").modal('hide');
                    });
                    // productTable
                    var productTable = $('#productTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": true,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });
                    $('#productTable tbody').on('click', 'tr', function () {
                        //$('.collapse').collapse('show');
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                        }
                        else {
                            productTable.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                        }

                    });
                    // ON KEY INPUT AUTO SELECT TOURCODE-TOURNAME
                    $(function () {
                        var availableTags = [];
//                                console.log(tourCode);
                        $.each(agentArray, function (key, value) {
                            availableTags.push(value.code);
                        });
//                                console.log(availableTags);
                        $("#InputProductCode").autocomplete({
                            source: availableTags,
                            close:function( event, ui ) {
                               //window.uiTmp = event;
                               //window.uiTmp = ui;
                               //alert('Test');    
                               $("#InputProductCode").trigger('keyup');
                            }
                        });
                        $("#InputProductCode").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var code = this.value.toUpperCase();
                            $("#InputProductName").val(null);
                            $.each(agentArray, function (key, value) {
                                //console.log('each : ' + value.code);
                                //console.log('val : ' + $("#agent_user").val());
                                if (value.code.toUpperCase() === code) {
//                                console.log('ok');
                                    $("#InputProductId").val(value.id);
                                    $("#InputProductName").val(value.name);
                                }
                            }); //end each agentTable


                        }); // end InputAgentCode keyup
                    }); // end AutoComplete AgentCode AgentName

//                    $('.date').mask('0000-00-00');    
                });
            </script>
            <div class="modal-footer">
                <button id="" type="button" onclick="" class="btn btn-success">OK</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>                                


 
<!--Commission Delete Modal-->
<div class="modal fade" id="DelCommission" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="" method="post" id="DelExpenseForm" class="form-horizontal"  role="form">            
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"> Delete AgentTourCommission </h4>
                </div>
                <div class="modal-body" id="delContent"></div>
                <!--                <input type="hidden" id="deleteId" name="agentTourComId"/>
                                <input type="hidden" id="deleteAction" name="action" value="delete"/>-->
                <div class="modal-footer">
                    <button id="btnDelete" type="button" class="btn btn-danger">Delete</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<c:if test="${! empty param.result}">
    <c:if test="${param.result =='success'}">        
        <script language="javascript">
            alert("update successful");
        </script>
        <META HTTP-EQUIV="Refresh" CONTENT="0;URL=MCommissionDetail.smi?commissionId=${param.commissionId}&action=edit">
    </c:if>
    <c:if test="${param.result =='fail'}">        
        <script language="javascript">
            alert("update unsuccessful");
        </script>
        <META HTTP-EQUIV="Refresh" CONTENT="0;URL=MCommissionDetail.smi?commissionId=${param.commissionId}&action=edit">
    </c:if>
</c:if>
<c:if test="${! empty requestScope['VALIDATE']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['VALIDATE']}" />');
    </script>
</c:if>   

<c:if test="${! empty requestScope['ResultSave']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['ResultSave']}" />');
    </script>
</c:if>         

<script type="text/javascript" charset="utf-8" >
    $(document).ready(function () {


        $(".datemask").mask('00-00-0000', {reverse: true});
        $(".decimal").mask('000,000,000.00', {reverse: true});
        
//        $(".decimal").inputmask("decimal",{
//            radixPoint:".", 
//            groupSeparator: ",", 
//            digits: 2,
//            autoGroup: true
//        });

        //Add Blank row for user input.
        addRowCommissionTable();
        /*Auto Add lastrow */
        $(document).on('keydown', '#commissionTable tbody tr:last td  input', function (e) {
            addRowCommissionTable();
        });


    });
    function validateSubmit() {
        var validDate = true;
        var trList = $('#commissionTable tbody tr');
        trList.each(function (i,tr) {
           if(i==0 || i==(trList.length-1)){
               return;
           }
           var d1Str = $(tr).find("input[name^='InputFrom']").val();
           var d2Str = $(tr).find("input[name^='InputTo']").val();
           if( !(isDate(d1Str) && isDate(d2Str)) ){
               validDate = false;
           }else{
               var d1Arr = d1Str.split("-");
               var d2Arr = d2Str.split("-");
               var d1 = Date.parse(d1Arr[2]+"-"+d1Arr[1]+"-"+d1Arr[0]);
               var d2 = Date.parse(d2Arr[2]+"-"+d2Arr[1]+"-"+d2Arr[0]);
               if (d1 > d2) {
                  validDate = false;
               }
           }
           
        });
        if(!validDate){
            $("#AlertCheckDate").show();
          alert("Invalid Date Please ckeck before Save/Update !!");
        }
        return validDate;
    }
    
    function isDate(txtDate)
    {
        var currVal = txtDate;
        if (currVal == '')
            return false;

        var rxDatePattern = /^(\d{1,2})(\-|-)(\d{1,2})(\-|-)(\d{4})$/; //Declare Regex
        var dtArray = currVal.match(rxDatePattern); // is format OK?

        if (dtArray == null)
            return false;

        //Checks for DD-MM-YYYY
        dtMonth = dtArray[3];
        dtDay = dtArray[1];
        dtYear = dtArray[5];

        if (dtMonth < 1 || dtMonth > 12)
            return false;
        else if (dtDay < 1 || dtDay > 31)
            return false;
        else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11) && dtDay == 31)
            return false;
        else if (dtMonth == 2)
        {
            var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
            if (dtDay > 29 || (dtDay == 29 && !isleap))
                return false;
        }
        return true;
    }
    
    function addRowCommissionTable() {
        var counter = $('#commissionTable tbody tr').length;
        var clone = $('#commissionTable tbody tr:first').clone();
        clone.removeClass("hide");
        clone.find('input,span').each(function () {
            $(this).attr({
                id: $(this).attr('id') + counter,
                name: $(this).attr('name') + counter
            });
            $("#counterCommission").val(counter + 1);
        });
        $('#commissionTable tbody').append(clone);
    }

    function DeleteCommissionRow(id, objspan) {
        alert('id:'+id);
        var countCommission = $("#commissionTable tbody").find("tr").length;
        if ($("#commissionTable tbody").find("tr").length !== 2) {
            if (id !== null) {
                $('#deleteAction').val('delete');
                $('#deleteId').val(id);
                $('#delContent').html(" Are you sure to delete : Row at Number "+ ($(objspan).parent().parent().index()-1) +" ? " );  //" + id + " commission ? ");
                $('#btnDelete').click(function (e) {
                    $.ajax({
                        url: 'MCommissionDetail.smi?agentTourComId='+id+'&action=delete',
                        type: 'POST',
                        data: {},
                        success: function () {
                            if (${! empty requestScope['COMMISSIONDELETE']}) {
                                alert('${requestScope['COMMISSIONDELETE']}');  
                            }
                            location.reload();
//                            $("#DelCommission").modal('hide');
                        },
                        error: function () {
                            console.log("error commission");
                        }
                    });
                });
            } else {
                $(objspan).closest('tr').remove();
                console.log("counterCommission=" + countCommission);
                $('#counterCommission').val(countCommission - 1);
            }
        } else {

            if (id !== null) {

                $('#deleteAction').val('delete');
                $('#deleteId').val(id);
                $('#delContent').html(" Are you sure to delete commission ? ");
                $('#btnDelete').click(function (e) {
                    $.ajax({
                        url: 'MCommissionDetail.smi?agentTourComId=' + id + '&action=delete',
                        type: 'POST',
                        data: {},
                        success: function () {
                            if (${! empty requestScope['COMMISSIONDELETE']}) {
                                alert('${requestScope['COMMISSIONDELETE']}');
                            }
                            location.reload();
                        },
                        error: function () {
                            console.log("error commission");
                        }
                    });
                });
            } else {
                alert('this row for add data');
            }
        }
    }
</script>

