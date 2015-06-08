<%-- 
    Document   : Staff
    Created on : Dec 11, 2014, 9:22:14 PM
    Author     : wleenavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="dataList" value="${requestScope['Staff_List']}" />
<c:set var="roleList" value="${requestScope['Role_List']}" />
<c:set var="deptList" value="${requestScope['Dept_List']}" />
<c:set var="staffSearch" value="${requestScope['StaffSearch']}" />
<style>
    input:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px white inset;
    }
</style>
<section class="content-header" >
    <h1>
        Master - Staff

    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Staff</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;"> 
    <div class="row">
        <!-- side bar -->
        <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px" id="Menu">
            <ul class="nav nav-list" style="top: 0px;  background-color: #FAFEFA;border: solid 1px #0063DC">
                <li class="">
                    <a href="MStaff.smi">
                        <i class="menu-icon fa fa-user"></i>
                        <span class="menu-text">Staff</span>
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


        <!--Script-->
        <script type="text/javascript" charset="utf-8">
            $(document).ready(function () {

                $('#delStaffButton').hide();


                var table = $('#MasterOthers').DataTable({
                    "bJQueryUI": true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bInfo": true,
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

                $('#addStaff').click(function () {
                    $('#Staffform').bootstrapValidator('resetForm', true);
                    $('#Staffform')[0].reset();
                    $('#Staffform').bootstrapValidator('resetForm', true);
                    $('#createBy').val($("#addStaff").attr("title"));
                    $('#actionIUP').val("add");
                    //set default to staff modal;
                    AddStaffDefault();
                });


                //Check enter key
                $("#NameSearch,#PositionSearch,#RoleSearch,#DepartmentSearch,#StatusSearch").keypress(function (event) {
                    if (event.which == 13) {
                        event.preventDefault();
                        $('#searchStaff').focus().click();
                    }
                });

                // Set value back to search box.
                $("#NameSearch").val("<c:out value="${staffSearch.name}" />");
                $("#PositionSearch").val("<c:out value="${staffSearch.position}" />");
                $("#RoleSearch").val("<c:out value="${staffSearch.role.id}" />");
                $("#DepartmentSearch").val("<c:out value="${staffSearch.MDepartment.id}" />");
                $("#StatusSearch").val("<c:out value="${staffSearch.status}" />");

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
            <div class="row" style="padding-left: 15px">
                <form action="MStaff.smi" method="post" id="SearchStaff" role="form">
                    <div class="col-md-2 ">
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" maxlength="100" id="NameSearch" name="StaffName" value="${requestScope['staffName']}"></input>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label>Position</label>
                            <input type="text" class="form-control" maxlength="50" id="PositionSearch" name="Position" value="${requestScope['position']}">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label for="RoleLabel">Role</label> 
                            <select class="form-control" id="RoleSearch" name="Role">
                                <option value="">Select Role</option>
                                <c:forEach var="role" items="${roleList}">
                                    <c:set var="mySelected" value="" />
                                    <c:choose>
                                        <c:when test="${requestScope['roleId'] == role.id}">
                                            <c:set var="mySelected" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="<c:out value="${role.id}" />" ${mySelected}><c:out value="${role.name}" /></option>
                                </c:forEach>
                            </select>    
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label>Department</label>
                            <select class="form-control" id="DepartmentSearch" name="Department">
                                <option value="">Select Department</option>
                                <c:forEach var="dept" items="${deptList}">
                                    <c:set var="mySelected" value="" />
                                    <c:choose>
                                        <c:when test="${requestScope['departmentId'] == dept.id}">
                                            <c:set var="mySelected" value="selected" />
                                        </c:when>
                                    </c:choose>
                                    <option value="<c:out value="${dept.id}" />" ${mySelected}><c:out value="${dept.name}" /></option>
                                </c:forEach>
                            </select>  
                        </div>
                    </div>                    
                    <div class="col-md-2">
                        <div class="form-group">
                            <label>Status</label>
                            <select class="form-control" id="StatusSearch" name="Status">
                                <option value="">Select Status</option>
                                <c:choose>
                                    <c:when test="${requestScope['status'] == 'active'}">
                                        <c:set var="activeSelected" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="active" ${activeSelected}>active</option>
                                <c:choose>
                                    <c:when test="${requestScope['status'] == 'inactive'}">
                                        <c:set var="inactiveSelected" value="selected" />
                                    </c:when>
                                </c:choose>
                                <option value="inactive" ${inactiveSelected}>inactive</option>
                            </select>    
                        </div>
                    </div>

                    <div class="col-md-2">
                        <div style="padding-top: 20px">   
                            <button type="button" id="searchStaff" onclick="searchAction()" class="btn btn-primary">Search</button>           
                            <input type="hidden" name="action" id="Action">
                        </div>
                    </div>
                </form>
            </div>
            <hr>

            <div class="row" style="padding-left: 15px">  
                <div class="col-md-10">
                    <h4><b>Master Staff</b></h4>
                </div>
                <div class="col-md-2">
                    <button title="${sessionScope['id']}" type="button" class="btn btn-success" id="addStaff"   data-toggle="modal"  data-target="#StaffModal"><span class="glyphicon glyphicon-plus" ></span>Add</button>
                </div>

            </div>

            <div class="row" style="padding-left: 15px">    
                <div class="col-md-11">
                    <table id="MasterOthers" class="display" cellspacing="0" >
                        <thead>
                            <tr class="datatable-header">
                                <th style="width: 35%">Name</th>
                                <th style="width: 5%" >Username</th>
                                <th style="width: 10%" >Position</th>
                                <th style="width: 24%" >Tel</th>
                                <th style="width: 10%" >Car</th>
                                <th style="width: 10%" >Department</th>
                                <th style="width: 10%" >Role</th>
                                <th style="width: 3%">Status</th>
                                <th style="width: 3%">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="table" items="${dataList}" varStatus="dataStatus">
                                <tr>
                                    <td><c:out value="${table.name}" /></td>
                                    <td><c:out value="${table.username}" /></td>
                                    <td><c:out value="${table.position}" /></td>
                                    <td><c:out value="${table.tel}" /></td>
                                    <td><c:out value="${table.car}" /></td>
                                    <td><c:out value="${table.MDepartment.name}" /></td>
                                    <td><c:out value="${table.role.name}" /></td>  
                                    <td><c:out value="${table.status}" /></td>
                                    <td>
                            <center> 
                                <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      
                                      onclick="EditStaff('${table.id}', '${table.name}', '${table.username}', '${table.password}', '${table.position}', '${table.tel}', '${table.car}', '${table.MDepartment.id}', '${table.status}', '${table.createBy}', '${table.role.id}')" data-toggle="modal" data-target="#StaffModal" >
                                </span>
                                <span id="spanRemove${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon hidden"  onclick="DeleteStaff('${table.id}', '${table.name}')"></span>
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

<!--Staff Modal-->
<div class="modal fade" id="StaffModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <form action="MStaff.smi" method="post" id="Staffform" class="form-horizontal"  role="form" autocomplete="off">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"><span class="fa fa-user"></span> Staff</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="StaffName" class="col-sm-3 control-label" >Name <font style="color: red">*</font></label>
                        <div class="col-sm-8"> 
                            <input type="text" class="form-control" maxlength="100" id="StaffName" name="StaffName" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="UserNames" class="col-sm-3 control-label" >Username <font style="color: red">*</font></label>
                        <div class="col-sm-8">  
                            <input type="text" class="form-control" maxlength="50" id="UserName" name="UserName"   autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Password" class="col-sm-3 control-label" >Password <font style="color: red">*</font></label>
                        <div class="col-sm-8">   
                            <input type="password" class="form-control" maxlength="50" id="Password" name="Password"  autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false"> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Position" class="col-sm-3 control-label" >Position <font style="color: red">*</font></label>
                        <div class="col-sm-8">   
                            <input type="text" class="form-control" maxlength="50" id="Position" name="Position" > 
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Role" class="col-sm-3 control-label" >Role <font style="color: red">*</font></label>
                        <div class="col-sm-8">
                            <select class="form-control col-sm-8" id="Role" name="Role">
                                <option value="">Select Role</option>
                                <c:forEach var="role" items="${roleList}">
                                    <option value="<c:out value="${role.id}" />"><c:out value="${role.name}" /></option>
                                </c:forEach>
                            </select>    
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Tel" class="col-sm-3 control-label" >Telephone</label>
                        <div class="col-sm-8">   
                            <input type="text" class="form-control" maxlength="30" id="Tel" name="Tel" data-bv-digits="true"> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Car" class="col-sm-3 control-label" >Car</label>
                        <div class="col-sm-8">   
                            <input type="text" class="form-control" maxlength="50" id="Car" name="Car" > 
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Department" class="col-sm-3 control-label" >Department</label>
                        <div class="col-sm-8">   
                            <select class="form-control col-sm-8" id="Department" name="Department">
                                <option value="">Select Department</option>
                                <c:forEach var="dept" items="${deptList}">
                                    <option value="<c:out value="${dept.id}" />"><c:out value="${dept.name}" /></option>
                                </c:forEach> 
                            </select>
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label for="Status" class="col-sm-3 control-label" >Status <font style="color: red">*</font></label>
                        <div class="radio col-sm-8">   
                            <label ><input value="inactive" id="StatusInactive" name="Status" type="radio" >Disable</label>
                        </div><br/>
                        <div class="radio col-sm-3 col-sm-offset-3">
                            <label ><input value="active" id="StatusActive" name="Status" type="radio" >Enable</label>
                        </div>
                    </div>
                    <!--<label for="createBy" class="col-sm-3 control-label" >createBy</label>-->
                    <!--<div class="col-sm-8">-->   
                    <input type="hidden" class="form-control" id="createBy" name="createBy"> 
                    <!--</div>-->

                    <input type="hidden" id="StaffID" name="StaffID" >
                    <input type="hidden" id="actionIUP" name="action">
                </div>
                <div class="modal-footer">
                    <button id="btnSave" type="submit" onclick class="btn btn-primary" disabled>Save</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div>
<!--Staff Delete-->
<button type="button" class="btn btn-success" id="delStaffButton"  data-toggle="modal" data-target="#delStaffModal"></button>
<div class="modal fade" id="delStaffModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Staff</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<c:if test="${! empty requestScope['airlineLap']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['airlineLap']}" />');
    </script>
</c:if>
<c:if test="${! empty requestScope['result']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['result']}" />');
    </script>
</c:if>
<!--Script mstaff.js-->
<script type="text/javascript" src="js/mstaff.js"></script> 

<style>


    #UserName,#Password:focus {

        -webkit-box-shadow: 0 0 0px 1000px #ffffff inset !important;

    }
    input:-webkit-autofill, 
    input::-webkit-autofill:focus, 
    :focus, input::focus:-webkit-autofill, 
    input:-webkit-autofill:focus,  
    input:focus:-webkit-autofill  {
        -webkit-box-shadow: inset 0 0px 1000px #ffffff  !important;
    }
</style>