<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['price_List']}" />
<section class="content-header" >
    <h1>
        Master Air ticket - Passenger Type

    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>   
        <li><a href="Mairticket.smi"> Master Air ticket</a></li>     
        <li class="active"><a href="MFlight.smi">Passenger Type</a></li>
    </ol>



</section>


<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
 
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Master/AirticketMenu.html'"></div>

        </div>

        <script type="text/javascript" charset="utf-8">
            $(document).ready(function() {
                var table = $('#MasterPricecategory').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bInfo": false
                });

                $('#MasterPricecategory tbody').on('click', 'tr', function() {
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

            <div class="row">
                <form action="MPricecategory.smi" method="post" id="SearchPricecategory" role="form">
                    <div class="col-md-3 ">
                        <div class="form-group">
                            <label for="PricecategoryCodeS">Code</label>
                            <input type="text"   class="form-control" maxlength="3" id="PricecategoryCodeS" name="PricecategoryCode" style="text-transform:uppercase" value="${requestScope['pricecategoryCode']}">

                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="PricecategoryNameS">Name</label>
                            <input type="text"  class="form-control" maxlength="50" id="PricecategoryNameS" name="PricecategoryName" style="text-transform:uppercase" value="${requestScope['pricecategoryName']}">
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div  style="padding-top: 20px">   
                            <button type="button" id="acs" onclick="searchAction()"  class="btn btn-primary">Search</button>           
                            <input type="hidden" name="action" id="Action"/>
                        </div>
                    </div>
                </form>

            </div>
            <hr>
            
            <div class="row" style="padding-left: 15px">  
                <div class="col-md-4 ">
                    <h4><b>Passenger Type</b></h4>
                </div>
                <div class="col-md-6 " style="padding-left:  156px">
                    <button id="btnAdd" type="button" class="btn btn-success" onclick="addaction()"  data-toggle="modal" data-target="#PricecategoryModal">
                        <span id="spanAdd" class="glyphicon glyphicon-plus"></span>Add
                    </button>
                </div>

            </div>
            
            <div class="row" style="padding-left: 15px">    
                <div class="col-md-7 "> 
                    <table id="MasterPricecategory" class="display" cellspacing="0" >
                        <thead>
                            <tr class="datatable-header">
                                <th style="width: 30px" >Code</th>
                                <th>Name</th>
                                <th style="width: 70px">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="table" items="${dataList}" varStatus="dataStatus">
                                <tr>
                                    <td><c:out value="${fn:toUpperCase(table.code)}" /></td>
                                    <td><c:out value="${fn:toUpperCase(table.name)}" /></td>
                                    <td>
                                        <center> 
                                            <span id="btnEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      onclick="EditPricecategory('${table.id}', '${table.code}', '${table.name}')" data-toggle="modal" data-target="#PricecategoryModal" ></span>
                                            <span id="btnRemove${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeletePricecategory('${table.id}', '${table.code}')" data-toggle="modal" data-target="#delPricecategoryModal" ></span>
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

<div class="modal fade" id="PricecategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <form action="MPricecategory.smi" method="post" id="Pricecategoryform" class="form-horizontal"  role="form">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Pricecategory</h4>
            </div>
            <div class="modal-body">
                
                    <div class="form-group">
                        <label for="PricecategoryCode" class="col-sm-3 control-label" >Code <font style="color: red">*</font></label>
                        <div class="col-sm-8"> 
                            <input type="text" class="form-control" maxlength="3" id="PricecategoryCode" style="text-transform:uppercase" name="PricecategoryCode" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="PricecategoryName" class="col-sm-3 control-label" >Name <font style="color: red">*</font></label>
                        <div class="col-sm-8">  
                            <input type="text" class="form-control" maxlength="50" id="PricecategoryName" style="text-transform:uppercase" name="PricecategoryName" >
                        </div>
                    </div> 
                    <input type="hidden" id="PricecategoryID" name="PricecategoryID" >
                    <input type="hidden" id="actionIUP" name="action">
                
            </div>
            <div class="modal-footer">
                <button id="btnSave" type="submit"  class="btn btn-primary">Save</button>
                <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="delPricecategoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Pricecategory</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<c:if test="${! empty requestScope['cityLap']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['cityLap']}" />');
    </script>
</c:if>
<c:if test="${! empty requestScope['result']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['result']}" />');
    </script>
</c:if>
<script type="text/javascript" src="js/MPricecategory.js"></script> 