<%-- 
    Document   : MProductCommission
    Created on : Jun 3, 2015, 1:38:21 PM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">
<link href="css/jquery-ui.css" rel="stylesheet">

<c:set var="ListProductCommission" value="${requestScope['ListProductCommission']}" />
<c:set var="ProductCode" value="${requestScope['ProductCode']}" />
<c:set var="ProductName" value="${requestScope['ProductName']}" />

<script type="text/javascript" src="js/MProductCommission.js"></script> 
<!DOCTYPE html>
<section class="content-header" >
    <h1> Master - Product Commission</h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>Master</a></li>          
        <li class="active"><a href="#">Product Commission</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <form action="MProductCommission.smi" method="post" id="MProductCommission" role="form">
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Master/MasterMenu.html'"></div>
            
        </div>
        <!-- main page -->
        <div class="col-md-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-md-8">
                    <h4><b>Product Commission</b></h4>
                </div>
            </div>
            <br/>
            <div class="row" style="padding-left: 15px">
               
                    <div class="col-md-2 ">
                        <div class="form-group">
                            <label>Product Code</label>
                            <input type="text" class="form-control" maxlength="15" id="ProductCodeSearch" name="ProductCodeSearch" value="<c:out value="${ProductCode}" />"></input>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label>Product Name</label>
                            <input type="text" class="form-control" maxlength="255" id="ProductNameSearch" name="ProductNameSearch" value="<c:out value="${ProductName}" />">
                        </div>
                    </div>       
                    <div class="col-md-2">
                        <div style="padding-top: 20px;">   
                            <input type="hidden" name="action" id="action" value="search">
                            <button type="submit" id="btnSearchCommission" name="btnSearchCommission" onclick="searchAction()" class="btn btn-primary"><span class="fa fa-search"></span> Search</button>           
                        </div>
                    </div>
                    <div class="col-md-2" >
                        <div style="padding-top: 20px">  
                            <a id="Add" name="Add" href="MProductCommissionDetail.smi?actionAdd=new">
                                <button id="ButtonAdd" name="ButtonAdd" type="button" class="btn btn-success" onclick="">
                                    <span id="SpanAdd" name="SpanAdd" class="glyphicon glyphicon-plus"></span>Add
                                </button>
                            </a>
                        </div>
                    </div>
              
            </div>
            <hr>
            <div class="row" style="padding-left: 15px">    
                <div class="col-md-11">
                    <table id="MasterCommission" class="display" cellspacing="0" >
                        <thead>
                            <tr class="datatable-header">
                                <th style="width: 30%" colspan="2">Product</th>
                                <th style="width: 10%" rowspan="2">From</th>
                                <th style="width: 10%" rowspan="2">To</th>
                                <th style="width: 10%" rowspan="2">Commission(%)</th>
                                <th style="width: 10%" rowspan="2">Commission(Bath)</th>
                                <th style="width: 10%" rowspan="2" >Action</th>
                            </tr>
                            <tr class="datatable-header">
                                <th style="width: 10%">Code</th>
                                <th style="width: 20%">Name</th>
                            </tr> 
                            
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${ListProductCommission}" varStatus="loop">
                                <tr>
                  
                                    <td><c:out value="${item.productId.code}" /></td>
                                    <td><c:out value="${item.productId.name}" /></td>
                                    <td class="text-center"><c:out value="${item.effectiveFrom}" /></td>
                                    <td class="text-center"><c:out value="${item.effectiveTo}" /></td>
                                    <td class="text-right" id="compoint-${loop.count}"><c:out value="${item.comission}" /></td>
                                    <td class="text-right" id="compoint-${loop.count}"><c:out value="${item.comissionPercent}" /></td>
                                    <td class="text-center">
                                        <!--Edit -->
                                        <a id="ButtonEdit-${loop.count}" name="ButtonEdit-${loop.count}" 
                                        href="MProductCommissionDetail.smi?action=edit&ProductCommissionId=${item.id}&ProductId=${item.productId.id}&ProductNameSearch=${item.productId.name}&ProductCodeSearch=${item.productId.code}">
                                            <span  id="RowSpanEdit-${loop.count}" name="RowSpanEdit-${loop.count}"  class="glyphicon glyphicon-edit editicon"  ></span>
                                        </a>
                                        <!--Delete -->
                                        <input type="hidden" id="ProductCommissionID" name="ProductCommissionID" >
                                        <span id="ButtonDelete-${loop.count}" name="ButtonDelete-${loop.count}" 
                                              class="glyphicon glyphicon-remove deleteicon"  
                                              onclick="DeleteProductCommission('${item.id}', '${item.productId.name}')" 
                                              data-toggle="modal" data-target="#DelProduct" >
                                        </span>
                                    </td>            
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>    
                </div>
            </div> 
        </div>
    </div>
   </form>
</div>

<!--Delete Model -->
<div class="modal fade" id="DelProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"> Delete product </h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="DeleteProductCom()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<c:if test="${! empty requestScope['DeleteMCommission']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['DeleteMCommission']}" />');
    </script>
</c:if>
<c:if test="${! empty requestScope['ResultSave']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['ResultSave']}" />');
    </script>
</c:if>
<c:if test="${! empty requestScope['VALIDATE']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['VALIDATE']}" />');
    </script>
</c:if>
    <script type="text/javascript" charset="utf-8">
        function DelCommissionAgent(id,agentcode,agentname,tourcode,tourname) {
            alert('id:'+id);
            $("#deleteId").val(id);
            $('#delContent').html("Are you sure to delete Commission of agent : "+agentcode+" and tour : "+tourcode+" ?");      
        }
     

$(document).ready(function (){
    var i = 0;
    $("#MasterCommission tbody").find("tr").each(function(){
        i++;
        var parts = parseFloat($("#compoint-"+i).text()).toFixed(2).split(".");
        var num = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (+parts[1] ? "." + parts[1] : ".00");
        console.log("C"+i+":"+num);
        $("#compoint-"+i).text(num);
    });
 
//    $(".money").mask("000,000,000", {reverse: true});
//    $(".decimal").inputmask(
//        "decimal",{
//            radixPoint:".", 
//            groupSeparator: ",", 
//            digits: 2,
//            autoGroup: true
//     });
    
    var table = $('#MasterCommission').DataTable({
       
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bInfo": true,
        "bSort": false
        
    });
    $('#MasterCommission tbody').on('click', 'tr', function () {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
            $('#hdGridSelected').val('');
        }
        else {
            table.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
            $('#hdGridSelected').val($('#MasterCommission tbody tr.row_selected').attr("id"));
        }
    });
});

</script>