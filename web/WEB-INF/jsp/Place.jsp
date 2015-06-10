<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Place.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['Place_List']}" />
<c:set var="ListStatus" value="${requestScope['List_status']}" />

<section class="content-header" >
    <h1>
        Place MA

    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Place MA</a></li>
    </ol>

  </section>  
    
<div class ="container"  style="padding-top: 15px;"> 
    <div class="row">
        
        <script type="text/javascript" charset="utf-8">
            $(document).ready(function() {
                var table = $('#MasterPlace').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bInfo": false
                });

                $('#MasterPlace tbody').on('click', 'tr', function() {
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
        <div class="col-md-9  col-xs-offset-3">

            <div class="row">
                <form action="Place.smi" method="post" id="SearchPlace" role="form">
                    <div class="col-md-3 ">
                        <div class="form-group">
                            <label for="PlaceName">name</label>
                            <input type="text"   class="form-control"  id="PlaceNameS" name="Placename" style="text-transform:uppercase" value="${requestScope['placeName']}">

                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="StatusNameS">status</label> 
                            <select name="Placestatus" id="StatusS"  class="form-control">
                                <option value="0"  selected="selected"> </option>
                                <c:forEach var="status" items="${ListStatus}">
                                    <c:set var="mySelected" value="" />
                                    <c:choose>
                                        <c:when test="${requestScope['placeStatusID'] == status.id}">
                                            <c:set var="mySelected" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="${status.id}" ${mySelected}>${status.name}</option>
                                </c:forEach>   
                            </select>
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
                    <h4><b>Place</b></h4>
                </div>
                <div class="col-md-6 " style="padding-left:  156px">
                    <button id="btnAdd" type="button" class="btn btn-success" onclick="addaction()"  data-toggle="modal" data-target="#PlaceModal">
                        <span id="spanAdd" class="glyphicon glyphicon-plus"></span>Add
                    </button>
                </div>

            </div>
            
            <div class="row" style="padding-left: 15px">    
                <div class="col-md-7 "> 
                    <table id="MasterPlace" class="display" cellspacing="0" >
                        <thead>
                            <tr class="datatable-header">
                                <th style="width: 50%">Name</th>
                                <th style="width: 15%">Status</th>
                                <th style="width: 15%">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="table" items="${dataList}" varStatus="dataStatus">
                                <tr>
                                    <td><c:out value="${fn:toUpperCase(table.place)}" /></td>
                                    <td><center><c:out value="${fn:toUpperCase(table.MPlacestatus.name)}" /></center></td>
                                    <td>
                                        <center> 
                                            <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      onclick="EditPlace('${table.id}', '${table.place}','${table.MPlacestatus.id}')" data-toggle="modal" data-target="#PlaceModal" ></span>
                                            <span id="spanRemove${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeletePlace('${table.id}', '${table.place}')" data-toggle="modal" data-target="#delPlaceModal" >  </span>
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

<div class="modal fade" id="PlaceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Place</h4>
            </div>
            <div class="modal-body">
                <form action="Place.smi" method="post" id="Placeform" class="form-horizontal"  role="form">
                    <div class="form-group">
                        <label for="CityCode" class="col-sm-3 control-label" >Name <font style="color: red">*</font></label>
                        <div class="col-sm-8"> 
                            <input type="text" class="form-control" maxlength="50" id="Placename" style="text-transform:uppercase" name="Placename" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="CityName" class="col-sm-3 control-label" >Status <font style="color: red">*</font></label>
                        <div class="col-sm-8">  
                            <select name="Placestatus" id="Placestatus"  class="form-control">
                                <option value="0"  selected="selected"> </option>
                                <c:forEach var="status" items="${ListStatus}">
                                   <option value="${status.id}">${status.name}</option>
                                </c:forEach>   
                            </select>
                        </div>
                    </div> 
                    <input type="hidden" id="PlaceID" name="PlaceID" >
                    <input type="hidden" id="actionIUP" name="action">
                </form>
            </div>
            <div class="modal-footer">
                <button id="btnSave" type="button" onclick="save()" class="btn btn-primary">Save</button>
                <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="delPlaceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Place</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<c:if test="${! empty requestScope['placeLap']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['placeLap']}" />');
    </script>
</c:if>
<c:if test="${! empty requestScope['result']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['result']}" />');
    </script>
</c:if>

