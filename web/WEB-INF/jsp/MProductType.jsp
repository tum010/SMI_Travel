<%-- 
    Document   : MProductType
    Created on : Jan 28, 2015, 10:56:34 AM
    Author     : Thitikul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['ProductType_List']}" />
<script type="text/javascript" src="js/MProductType.js"></script> 
<section class="content-header" >
    <h1>
        Master - Product Type
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Product Type</a></li>
    </ol>
</section>

<div class ="container2"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Master/ProductMenu.html'"></div>
        </div>

        <script type="text/javascript" charset="utf-8">
            $(document).ready(function() {
                var table = $('#MasterProductType').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bInfo": false
                });

                $('#MasterProductType tbody').on('click', 'tr', function() {
                    if ($(this).hasClass('row_selected')) {
                        $(this).removeClass('row_selected');
                    }
                    else {
                        table.$('tr.row_selected').removeClass('row_selected');
                        $(this).addClass('row_selected');         
                    }
                });
                //$('.dataTables_length label').remove();
            });

        </script>
        <!-- main page -->
        <div class="col-md-9 ">
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
            <!-- Alert Uni-->
            <div id="textAlertLap"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Product type already exist!</strong> 
            </div>
            <div class="row">
                <form action="MProductType.smi" method="post" id="SearchProductType" role="form">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="ProductTypeNameS">Name</label>
                            <input type="text"  class="form-control" maxlength="50" id="ProductTypeNameS" name="ProductTypeName"  style="text-transform:uppercase" value="${requestScope['productTypeName']}">
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div  style="padding-top: 20px">   
                            <button type="button" id="acs" onclick="searchAction()"  class="btn btn-primary"><span class="fa fa-search"></span> Search</button>           
                            <input type="hidden" name="action" id="Action"/>
                        </div>
                    </div>
                </form>

            </div>
            <hr>
            
            <div class="row" style="padding-left: 15px">  
                <div class="col-md-4 ">
                    <h4><b>Product Type</b></h4>
                </div>
                <div class="col-md-6 " style="padding-left:  196px">
                    <button type="button" class="btn btn-success" onclick="addaction()"  data-toggle="modal" data-target="#ProductTypeModal"><span class="glyphicon glyphicon-plus"></span>Add</button>
                </div>

            </div>
            
            <div class="row" style="padding-left: 15px">    
                <div class="col-md-7 "> 
                    <table id="MasterProductType" class="display" cellspacing="0" >
                        <thead>
                            <tr class="datatable-header">
                                <th style="width: 30px" >ID</th>
                                <th>Name</th>
                                <th style="width: 70px">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="table" items="${dataList}">
                                <tr>
                                    <td><c:out value="${fn:toUpperCase(table.id)}" /></td>
                                    <td><c:out value="${fn:toUpperCase(table.name)}" /></td>
                                    <td>
                                        <center> 
                                            <span class="glyphicon glyphicon-edit editicon"      onclick="EditProductType('${table.id}', '${table.name}')" data-toggle="modal" data-target="#ProductTypeModal" ></span>
                                            <span class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteProductType('${table.id}')" data-toggle="modal" data-target="#delProductTypeModal" >  </span>
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


<div class="modal fade" id="ProductTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <form action="MProductType.smi" method="post" id="ProductTypeform" class="form-horizontal"  role="form">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Product Type</h4>
            </div>
            <div class="modal-body">
                
                    <div class="form-group">
                        <label for="ProductTypeName" class="col-sm-3 control-label" >Name <font style="color: red">*</font></label>
                        <div class="col-sm-8">  
                            <input type="text" class="form-control" maxlength="50" id="ProductTypeName" style="text-transform:uppercase" name="ProductTypeName" >
                        </div>
                    </div> 
                    <input type="hidden" id="ProductTypeID" name="ProductTypeID" >
                    <input type="hidden" id="actionIUP" name="action">
                
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-success"><span  class="fa fa-save"></span> Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="delProductTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Product Type</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<c:if test="${! empty requestScope['productTypeLap']}">
    <script language="javascript">
        $('#textAlertLap').show();
    </script>
</c:if>
<c:if test="${! empty requestScope['result']}">
    <c:if test="${requestScope['result'] =='save successful'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='save unsuccessful'}">        
        <script language="javascript">
           $('#textAlertDivNotSave').show();
        </script>
    </c:if>
</c:if>
