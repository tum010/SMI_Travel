<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/MPackage.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['package_list']}" />

<section class="content-header" >
    <h1>
        Master - Package
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Package</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" > 
    <div class="col-md-12">
        <div class="row" style="padding-left: 150px">
            <form action="MPackage.smi" method="post" id="SearchPackage" role="form">
                <div class="col-md-2  col-xs-offset-1">
                    <div class="form-group">
                        <label for="PackageCodeS">Code</label>
                        <input type="text" style="text-transform:uppercase"   class="form-control" maxlength="50" id="PackageCodeS" name="PackageCodeS" value="${requestScope['packageCode']}">
                    </div>
                </div>
                        
                <div class="col-md-3 ">
                    <div class="form-group">
                        <label for="CityNameS">Name</label>
                        <input type="text" style="text-transform:uppercase" class="form-control" maxlength="100" id="PackageNameS" name="PackageNameS" value="${requestScope['packageName']}">
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

                <div class="col-md-1">
                    <div  style="padding: 20px 0px 0px 20px">   
                        <button type="button" id="acs" onclick="searchAction()"  class="btn btn-primary">Search</button>           
                        <input type="hidden" name="action" id="Action"/>
                        <input type="hidden" id="PackageID" name="PackageID" >
                    </div>
                </div>
            </form>

        </div>
        <hr>
        <div class="row" style="padding-left: 40px">  
            <div class="col-md-5  col-xs-offset-2">
                <h4><b>Package</b></h4>
            </div>
            <div class="col-md-4 " style="padding: 5px 0px 0px 175px">
                <a id="btnAdd" href="MPackageDetail.smi" class="btn btn-success">
                    <span id="spanAdd" class="glyphicon glyphicon-plus"></span>Add
                </a>
            </div>

        </div>

        <div class="row" style="padding-left: 0px">    
            <div class="col-md-8  col-xs-offset-2">
                <table id="MasterPackage" class="display" cellspacing="0" >
                    <thead>
                        <tr class="datatable-header">
                            <th style="width: 30px" >Code</th>
                            <th>Name</th>
                            <th style="width: 40px">Status</th>
                            <th style="width: 70px">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${dataList}" varStatus="dataStatus">
                            <tr>
                                <td> <c:out value="${table.code}" /></td>
                                <td> <c:out value="${table.name}" /></td>
                                <td> <c:out value="${table.status}" /></td>
                                <td> 
                                    <center> 
                                    <a  href="MPackageDetail.smi?packageid=${table.id}&action=edit">
                                    <span class="glyphicon glyphicon-edit editicon"  ></span>
                                    </a>
                                    <span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeletePackage('${table.id}', '${table.name}')" data-toggle="modal" data-target="#DelPackage" >  </span>
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

<div class="modal fade" id="DelPackage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"> Delete package </h4>
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
    $(document).ready(function() {
        var table = $('#MasterPackage').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });

        $('#MasterPackage tbody').on('click', 'tr', function() {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
            }
        });
    });

</script>