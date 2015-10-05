<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/MFlight.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['Flight_List']}" />
<c:set var="dataFlight" value="${requestScope['FlightData']}" />
<c:set var="dataListService" value="${requestScope['FlightService_List']}" />
<section class="content-header" >
    <h1>
        Master Air ticket - Flight class

    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>       
        <li><a href="Mairticket.smi"> Master Air ticket</a></li>     
        <li class="active"><a href="MFlight.smi">Flight class</a></li>
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
                var table = $('#MasterFlight').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bInfo": false
                });

                $('#MasterFlight tbody').on('click', 'tr', function() {
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
        <div class="col-md-10 ">
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
                    <strong>Fight name already exist!</strong> 
            </div>
            <div class="row">
                <form action="MFlight.smi" method="post" id="SearchFlight" role="form">
                    <div class="col-md-3 ">
                        <div class="form-group">
                            <label for="FlightCodeS">Code</label>
                            <input type="text"   class="form-control" maxlength="3" id="FlightCodeS" name="FlightCode" style="text-transform:uppercase" value="${requestScope['flightCode']}">

                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label for="FlightNameS">Name</label>
                            <input type="text"  class="form-control"  id="FlightNameS" name="FlightName" style="text-transform:uppercase" value="${requestScope['flightName']}">
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div  style="padding-top: 20px">   
                            <button type="button" id="acs" onclick="searchAction()"  class="btn btn-primary"> <span class="fa fa-search"></span> Search</button>           
                            <input type="hidden" name="action" id="Action"/>
                        </div>
                    </div>
                </form>

            </div>
            <hr>
            
            <div class="row" style="padding-left: 15px">  
                <div class="col-md-4 ">
                    <h4><b>Flight Class</b></h4>
                </div>
                <div class="col-md-6 " style="padding-left:  182px">
                    <button id="btnAdd" type="button" class="btn btn-success" onclick="addaction()"  data-toggle="modal" data-target="#FlightModal">
                        <span id="spanAdd" class="glyphicon glyphicon-plus"></span>Add
                    </button>
                </div>

            </div>
            
            <div class="row" style="padding-left: 15px">    
                <div class="col-md-7 "> 
                    <table id="MasterFlight" class="display" cellspacing="0" >
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
                                            <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      onclick="EditFlight('${table.id}', '${table.code}', '${table.name}')" data-toggle="modal" data-target="#FlightModal" ></span>
                                            <span id="spanRemove${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteFlight('${table.id}', '${table.code}')" data-toggle="modal" data-target="#delFlightModal" ></span>
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

<div class="modal fade" id="FlightModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <form action="MFlight.smi" method="post" id="Flightform" class="form-horizontal"  role="form">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Flight</h4>
            </div>
            <div class="modal-body">
                
                    <div class="form-group">
                        <label for="FlightCode" class="col-sm-3 control-label" >Code <font style="color: red">*</font></label>
                        <div class="col-sm-8"> 
                            <input type="text" class="form-control" maxlength="3" id="FlightCode" style="text-transform:uppercase" name="FlightCode" value="${dataFlight.code}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="FlightName" class="col-sm-3 control-label" >Name <font style="color: red">*</font></label>
                        <div class="col-sm-8">  
                            <input type="text" class="form-control" maxlength="50" id="FlightName" style="text-transform:uppercase" name="FlightName" value="${dataFlight.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <table id="MasterFlightService" class="display" cellspacing="0" >
                            <thead>
                                <tr class="datatable-header">
                                    <th style="width: 30px" class="hidden">id</th>
                                    <th style="width: 30px" >Code</th>
                                    <th>Name</th>
                                    <th style="width: 70px">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="tableService" items="${dataListService}" varStatus="dataStatusService">
                                    <tr>
                                        <td class="hidden"><input type="text" class="form-control" maxlength="3" id="FlightServiceId${dataStatusService.count}" style="text-transform:uppercase" name="FlightServiceId${dataStatusService.count}" value="${tableService.id}"></td>
                                        <td><input type="text" class="form-control" maxlength="3" id="FlightServiceCode${dataStatusService.count}" style="text-transform:uppercase" name="FlightServiceCode${dataStatusService.count}" value="${tableService.classCode}"></td>
                                        <td><input type="text" class="form-control" maxlength="3" id="FlightServiceName${dataStatusService.count}" style="text-transform:uppercase" name="FlightServiceName${dataStatusService.count}" value="${tableService.className}"></td>
                                        <td><center><span id="spanRemove${dataStatusService.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteFlight('${tableService.id}', '${tableService.classCode}')" data-toggle="modal" data-target="#delFlightModal" ></span></center></td>                 
                                    </tr>  
                                </c:forEach>
                            </tbody>
                        </table>    
                    </div>
                    <input type="hidden" id="FlightID" name="FlightID" >
                    <input type="hidden" id="actionIUP" name="action">
               
            </div>
            <div class="modal-footer">
                <button id="btnSave" type="submit"  class="btn btn-success"><span  class="fa fa-save"></span> Save</button>
                <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
         </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="delFlightModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Flight</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript" charset="utf-8">
    var select = " ";
    $(document).ready(function () {
        
    }); 
    
    function EditFlight(id,code,name){
//    var idFlight = document.getElementById('FlightID').value;
        var select = "";
        console.log("ID: "  + id);
        document.getElementById("FlightCode").readOnly = true;
        document.getElementById('FlightCode').value=code;
        document.getElementById('FlightName').value=name;
        document.getElementById('FlightID').value = id;
        document.getElementById('actionIUP').value='update';
        document.getElementById('Flightform').submit();

        <c:forEach var="tableService" items="${dataListService}" varStatus="dataStatusService">
                <%--<c:when test="${tableService.MFlight.id == id}">--%>
                    select += '<td class="hidden"><input type="text" class="form-control" maxlength="3" id="FlightServiceId${dataStatusService.count}" style="text-transform:uppercase" name="FlightServiceId${dataStatusService.count}" value="${tableService.id}"></td>';
                    select += '<td><input type="text" class="form-control" maxlength="3" id="FlightServiceCode${dataStatusService.count}" style="text-transform:uppercase" name="FlightServiceCode${dataStatusService.count}" value="${tableService.classCode}"></td>';
                    select += '<td><input type="text" class="form-control" maxlength="3" id="FlightServiceName${dataStatusService.count}" style="text-transform:uppercase" name="FlightServiceName${dataStatusService.count}" value="${tableService.className}"></td>';
                    select += '<td><center><span id="spanRemove${dataStatusService.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteFlight('${tableService.id}', '${tableService.classCode}')" data-toggle="modal" data-target="#delFlightModal" ></span></center></td>';                                                                                                       
              <%--</c:when>--%>
         </c:forEach>
        select += "";
        $("#MasterFlightService tbody").empty().append(select);
        console.log("Select : "+select);

    }
</script>
<c:if test="${! empty requestScope['flightLap']}">
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