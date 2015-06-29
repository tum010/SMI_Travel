<%-- 
    Document   : MRole
    Created on : Dec 18, 2014, 7:54:33 AM
    Author     : wleenavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="dataList" value="${requestScope['Role_List']}" />
<c:set var="funcList" value="${requestScope['Func_List']}" />
<c:set var="roleSearch" value="${requestScope['RoleSearch']}" />
<script type="text/javascript" src="js/mrole.js"></script> 
<section class="content-header" >
    <h1>
        Master - Role
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Role</a></li>
    </ol>
</section>
<!--<input type="text" value="${requestScope['result']}">-->
<div class ="container"  style="padding-top: 15px;"> 
    <div class="row">
        <!-- side bar -->
        <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px" id="Menu">
            <ul class="nav nav-list" style="top: 0px;  background-color: #FAFEFA;border: solid 1px #0063DC">
                <li class="">
                    <a href="MStaff.smi">
                        <i class="menu-icon fa fa-user"></i>
                        <span class="menu-text"> Staff </span>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="">
                    <a href="MRole.smi">
                        <i class="menu-icon fa fa-user"></i>
                        <span class="menu-text">Role</span>
                    </a>
                    <b class="arrow"></b>
                </li>                
            </ul>
        </div>

        <script type="text/javascript" charset="utf-8">
            $(document).ready(function () {
               $('#delRoleButton').hide();
                var table = $('#MasterOthers').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bSort": false
                });

                $('#MasterOthers tbody').on('click', 'tr', function () {
                    if ($(this).hasClass('row_selected')) {
                        $(this).removeClass('row_selected');
                        $('#hdGridSelected').val('');
                    }
                    else {
                        table.$('tr.row_selected').removeClass('row_selected');
                        $(this).addClass('row_selected');
                        $('#hdGridSelected').val($('#MasterOthers tbody tr.row_selected').attr("id"));
                    }
                });
//                $('.dataTables_length label').remove();

                $('#addRole').click(function () {
                    $('#Roleform')[0].reset();
                    $('#Roleform').bootstrapValidator('resetForm', true);
                    $('#RoleName').val("");
                    $('#actionIUP').val("add");
                });

                // Set value back to search box.
                $("#NameSearch").val("<c:out value="${roleSearch.name}" />");

                // Hilight Menu
                var path = location.pathname;
                var name = path.split("/");
                console.log(name[2]);
                str = name[2].split(".");
                console.log(str[0]);
                sub = str[0].substr(0, 3);
                console.log(sub);
                var $select = $('a[href*="' + sub + '"]');
                $("#Menu").find($select).css('background-color', '#ccc');

            });
        </script>
        <!-- main page -->
        <div class="col-md-10">
            <!--Alert Save --> 
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Add Success!</strong> 
            </div>
            <!--Alert Not Save --> 
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Add Not Success!</strong> 
            </div>
            <!--Alert Update --> 
            <div id="textAlertDivUpdate"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Update Success!</strong> 
            </div>
            <!--Alert Delete --> 
            <div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Delete Success!</strong> 
            </div>
            <!--Alert Not Update --> 
            <div id="textAlertDivNotUpdate"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Update Not Success!</strong> 
            </div>
            <!-- Alert Uni-->
            <div id="textAlertLap"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Add unsuccessful! role name already exist!</strong> 
            </div>
            <div class="row">
                <form action="MRole.smi" method="post" id="SearchRole" role="form">
                    <div class="col-md-offset-5 col-md-3">
                        <div class="form-group">
                            <label for="NameSearch">Name</label>
                            <input type="text"   class="form-control" id="NameSearch" maxlength="50" name="RoleName" value="${requestScope['roleName']}">
                        </div>
                    </div>

                    <div class="col-md-2">
                        <div  style="padding-top: 20px">   
                            <button type="button" id="acs" onclick="searchAction()"  class="btn btn-primary"><span class="fa fa-search"></span> Search</button>           
                            <input type="hidden" name="action" id="Action"/>
                        </div>
                    </div>
                </form>

            </div>
            <hr>
            <div class="row" style="padding-left: 15px">  
                <div class="col-md-6">
                    <h4><b>Role</b></h4>
                </div>
                <div class="col-md-6 " style="padding-left:  182px">
                    <button  type="button" class="btn btn-success" id="addRole"  data-toggle="modal" data-target="#RoleModal">
                        <span id="spanAdd" class="glyphicon glyphicon-plus"></span>Add
                    </button>
                </div>
            </div>
            <div class="row" style="padding-left: 15px">    
                <div class="col-md-9"> 
                    <table id="MasterOthers" class="display" cellspacing="0" >
                        <thead>
                            <tr class="datatable-header">
                                <th style="width: 80%" >Name</th>
                                <th stype="width: 20%" >Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="table" items="${dataList}" varStatus="dataStatus">
                                <tr>
                                    <td><c:out value="${table.name}" /></td>
                                    <td>
                            <center> 
                                <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      
                                      onclick="EditRole('${table.id}', '${table.name}', <c:forEach var="roleMap" items="${table.roleMappings}">
                                              '<c:out value="${roleMap.function.id}" />',</c:forEach>
    'end')" data-toggle="modal" data-target="#RoleModal" ></span>
                                <span id="spanRemove${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteRole('${table.id}', '${table.name}')"></span>
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

<div class="modal fade" id="RoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <form action="MRole.smi" method="post" id="Roleform" class="form-horizontal"  role="form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Role</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="RoleName" class="col-sm-3 control-label" >Name <font style="color: red">*</font></label>
                        <div class="col-sm-8"> 
                            <input type="text" class="form-control" id="RoleName" maxlength="50" name="RoleName" >
                        </div>
                    </div>
                    <div class="form-group pre-scrollable">
                        <label for="FunctionName" class="col-sm-5 control-label" >Function</label>
                        <label for="MenuName" class="col-sm-4 control-label" >Menu</label>
                        </br>
                        <hr>
                        <c:forEach var="function" items="${funcList}">
                            <div class="checkbox col-sm-offset-2 col-sm-9">
                                <div class = "col-md-6">
                                    <label>
                                        <input type="checkbox" id="<c:out value="${function.id}" />" name="checkedFunction" value="<c:out value="${function.id}" />"><c:out value="${function.name}" />
                                    </label>
                                </div>
                                <div class = "col-md-6">
                                    <label>
                                        <c:out value="${function.mainMenu.name}" />
                                    </label>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <input type="hidden" id="RoleID" name="RoleID" >
                    <input type="hidden" id="actionIUP" name="action">
                </div>
                <div class="modal-footer">
                    <button id="btnSave" type="submit" class="btn btn-success"><span  class="fa fa-save"></span> Save</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<button type="button" class="btn btn-success" id="delRoleButton"  data-toggle="modal" data-target="#delRoleModal"></button>
<div class="modal fade" id="delRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <form action="MRole.smi" method="post" id="DelRoleform" class="form-horizontal"  role="form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Delete Role</h4>
                </div>
                <div class="modal-body" id="delCode"></div>

                <input type="hidden" id="DelRoleID" name="RoleID"/>
                <input type="hidden" id="DelActionIUP" name="action" />
                <input type="hidden" id="DelRoleName" name="RoleName" />
                <div class="modal-footer">
                    <button type="submit"  class="btn btn-danger">Delete</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<c:if test="${! empty requestScope['result']}">
    <c:if test="${requestScope['result'] =='Add successful'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='Add unsuccessful'}">        
        <script language="javascript">
           $('#textAlertDivNotSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='save successful'}">        
        <script language="javascript">
           $('#textAlertDivUpdate').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='save unsuccessful'}">        
        <script language="javascript">
           $('#textAlertDivNotUpdate').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='Add unsuccessful! role name already exist'}">        
        <script language="javascript">
           $('#textAlertLap').show();
        </script>
    </c:if>Delete successful
    <c:if test="${requestScope['result'] =='Delete successful'}">        
        <script language="javascript">
           $('#textAlertDivDelete').show();
        </script>
    </c:if>
</c:if>