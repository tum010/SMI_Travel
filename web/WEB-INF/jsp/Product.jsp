<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Product.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="producttypeList" value="${requestScope['producttype_list']}" />
<c:set var="dataList" value="${requestScope['product_list']}" />
<section class="content-header" >
    <h1>
        Master - Product
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Product MA</a></li>
    </ol>



</section>

<div class="container2" style="padding-top: 15px;" ng-app="">
    <div class="row">
        <!-- side bar -->

        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Master/ProductMenu.html'"></div>
        </div>

        <script type="text/javascript" charset="utf-8">
            $(document).ready(function () {
                var table = $('#MasterProductD').dataTable({
                    bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bInfo": false
                });

                $('#MasterProductD tbody').on('click', 'tr', function () {
                    if ($(this).hasClass('row_selected')) {
                        $(this).removeClass('row_selected');
                    }
                    else {
                        table.$('tr.row_selected').removeClass('row_selected');
                        $(this).addClass('row_selected');
                    }
                });
                setformat();
            });

            function setformat() {
                $('#MasterProductD tr td.moneyformat').each(function () {
                    var innerHTML = $(this).html();
                    $(this).html(numberWithCommas($(this).html()));
                });
            }

        </script>
        <!-- main page -->
        <div class="col-md-9 ">

            <div class="row">
                <form action="Product.smi" method="post" id="ProductForm" role="form" >

                    <div class="col-md-3 ">
                        <div class="form-group">
                            <label for="ProductCodeS">Code</label>
                            <input type="text" class="form-control" maxlength="10" id="CodeS" name="code"  value="${requestScope['master']}">

                        </div>
                    </div>
                    <div class="col-md-3 ">
                        <div class="form-group">
                            <label for="ProductNameS">Name</label>
                            <input type="text" class="form-control" maxlength="50" id="NameS" name="name"  value="${requestScope['name']}">

                        </div>
                    </div>
                    <div class="col-md-3 ">
                        <div class="form-group">
                            <label for="ProductNameS">Type</label>
                            <select name="type" id="type"  class="form-control">
                                <option value="" selected>--select--</option>
                                <c:forEach var="table" items="${producttypeList}">
                                    <c:set var="mySelected" value="" />
                                    <c:choose>
                                        <c:when test="${requestScope['productTypeId'] == table.id}">
                                            <c:set var="mySelected" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="<c:out value="${table.id}" />" ${mySelected}><c:out value="${table.name}" /></option>                   
                                </c:forEach>
                            </select>

                        </div>
                    </div>

                    <div class="col-md-3">
                        <div  style="padding-top: 20px">   
                            <button type="button" id="acs" onclick="searchAction()"  class="btn btn-primary">Search</button>           
                            <input type="hidden" name="action" id="Action"/>
                            <input type="hidden" id="ProductID" name="productID" >
                        </div>
                    </div>                   

                </form>
            </div>
            <hr>

            <div class="row" style="padding-left: 15px">  
                <div class="col-md-8 ">
                    <h4><b>Product</b></h4>
                </div>
                <div class="col-md-4 " style="padding-left:  290px">
                    <a id="btnAdd" name="btnAdd" href="MProductDetail.smi" class="btn btn-success">
                        <span id="spanAdd" class="glyphicon glyphicon-plus"></span>Add
                    </a>
                </div>

            </div>

            <div class="row">
                <div class="col-md-12  ">
                    <table id="MasterProductD" class="display" cellspacing="0">
                        <thead>
                            <tr class="datatable-header">
                                <th style="width: 93px" rowspan="2">Code</th>
                                <th style="width: 200px" rowspan="2">Name</th>
                                <th style="width: 90px" rowspan="2">Effective From</th>
                                <th style="width: 90px" rowspan="2">Effective To</th>
                                <th colspan="3" >Cost</th>
                                <th colspan="3" >Price</th> 
                                <th style="width: 220px" rowspan="2" >Detail</th>
                                <th style="width: 50px" rowspan="2" >By</th>
                                <th rowspan="2">Action</th>
                            </tr>
                            <tr class="datatable-header">

                                <th >Adult</th>
                                <th >Child</th>
                                <th >Infant</th>
                                <th >Adult</th>
                                <th >Child</th>
                                <th >Infant</th>
                            </tr>
                        </thead>
                        <tbody>   
                            <c:forEach var="table" items="${dataList}" varStatus="dataStatus">
                                <tr>
                                    <td><c:out value="${table.code}" /> </td>
                                    <td><c:out value="${table.name}" /> </td>
                                    <td><c:out value="${table.effectiveFrom}" /> </td>
                                    <td><c:out value="${table.effectiveTo}" /> </td>
                                    <td class='tdcenter moneyformat' ><c:out value="${table.adCost}" /> </td>
                                    <td class='tdcenter moneyformat' ><c:out value="${table.chCost}" /> </td>
                                    <td class='tdcenter moneyformat' ><c:out value="${table.inCost}" /> </td>
                                    <td class='tdcenter moneyformat' ><c:out value="${table.adPrice}" /> </td>
                                    <td class='tdcenter moneyformat' ><c:out value="${table.chPrice}" /> </td>
                                    <td class='tdcenter moneyformat' ><c:out value="${table.inPrice}" /> </td>
                                    <td ><c:out value="${table.description}" /> </td>
                                    <td><center><c:out value="${table.updateBy}" /> </center></td>
                            <td>
                            <center> 
                                <a id="btnEdit${dataStatus.count}" name="btnEdit${dataStatus.count}"  href="MProductDetail.smi?productid=${table.id}&action=edit">
                                    <span id="spanEdit${dataStatus.count}" name="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"></span>
                                </a>
                                <span id="spanRemove${dataStatus.count}" name="spanRemove${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteProduct('${table.id}', '${table.name}')" data-toggle="modal" data-target="#DelProduct" >  </span>
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

<div class="modal fade" id="DelProduct" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"> Delete product </h4>
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
    <script language="javascript">
        alert('<c:out value="${requestScope['result']}" />');
    </script>
</c:if>