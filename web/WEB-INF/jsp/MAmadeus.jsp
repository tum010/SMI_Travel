<%-- 
    Document   : MAmadeus
    Created on : Dec 30, 2014, 9:22:14 PM
    Author     : wleenavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['Amadeus_List']}" />
<script type="text/javascript" src="js/mamadeus.js"></script> 
<section class="content-header" >
    <h1>
        Master - Amadeus

    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Amadeus</a></li>
    </ol>
</section>
<!--<input type="text" value="${requestScope['result']}">-->
<div class ="container"  style="padding-top: 15px;"> 
    <div class="row">
        <!--Script-->
        <script type="text/javascript" charset="utf-8">
            $(document).ready(function () {
                var table = $('#MasterAmadeus').DataTable({
                    "bJQueryUI": true,
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bInfo": false,
                    "bSort": false,
                    "bPaginate": false
                });

                $('#MasterAmadeus tbody').on('click', 'tr', function () {
                    if ($(this).hasClass('row_selected')) {
                        $(this).removeClass('row_selected');
                        $('#hdGridSelected').val('');
                    }
                    else {
                        table.$('tr.row_selected').removeClass('row_selected');
                        $(this).addClass('row_selected');
                        $('#hdGridSelected').val($('#MasterAmadeus tbody tr.row_selected').attr("id"));
                    }
                });
//                $('.dataTables_length label').remove();
            });
        </script>
        <!-- main page -->
            <hr>
             <div class="row" style="padding-left: 15px"> 
                <!--Alert Save --> 
                <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>Save Success!</strong> 
                </div>
                <div class="col-md-8">
                    <h4><b>Master Amadeus</b></h4>
                </div>
                <div class="col-md-4">
                </div>
            </div>
          
            <div class="row" style="padding-left: 15px">    
                <div class="col-md-9">
                    <table id="MasterAmadeus" class="display" cellspacing="0" >
                        <thead>
                            <tr class="datatable-header">
                                <th >Name</th>
                                <th style="width: 30px" >section</th>
                                <th style="width: 30px" >nodlm</th>
                                <th style="width: 10px" >length</th>
                                <th style="width: 30px" >start length</th>
                                <th style="width: 5px">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="table" items="${dataList}" varStatus="dataStatus">
                                <tr>
                                    <td><c:out value="${table.name}" /></td>
                                    <td><c:out value="${table.section}" /></td>
                                    <td><c:out value="${table.nodlm}" /></td>
                                    <td><c:out value="${table.length}" /></td>
                                    <td><c:out value="${table.startlength}" /></td>  
                                    <td>
                                        <center> 
                                            <span id="spanEdit${dataStatus.count}" name="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      
                                                onclick="Edit('${table.id}', '${table.name}', '${table.section}', '${table.nodlm}', '${table.length}', '${table.startlength}')" data-toggle="modal" data-target="#AmadeusModal" ></span>
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

<!--Amadeus Modal-->
<div class="modal fade" id="AmadeusModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <form action="MAmadeus.smi" method="post" id="Amadeusform" class="form-horizontal"  role="form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"><span class="fa fa-user"></span> Amadeus</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="NameDisplay" class="col-sm-3 control-label" >Name</label>
                        <div class="col-sm-8"> 
                            <p class="form-control-static" id="NameDisplay" name="NameDisplay" ></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Section" class="col-sm-3 control-label" >Section <font style="color: red">*</font></label>
                        <div class="col-sm-8">  
                            <input type="text" class="form-control" maxlength="50" id="Section" name="Section" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="NodLm" class="col-sm-3 control-label" >NodLm <font style="color: red">*</font></label>
                        <div class="col-sm-8">   
                            <input type="text" class="form-control" maxlength="3" id="NodLm" name="NodLm" > 
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Length" class="col-sm-3 control-label" >Length <font style="color: red">*</font></label>
                        <div class="col-sm-8">   
                            <input type="text" class="form-control" maxlength="3" id="Length" name="Length" > 
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="StartLength" class="col-sm-3 control-label" >Start Length <font style="color: red">*</font></label>
                        <div class="col-sm-8">   
                            <input type="text" class="form-control" maxlength="3" id="StartLength" name="StartLength" > 
                        </div>
                    </div>
                    <input type="hidden" id="GID" name="GID" >
                    <input type="hidden" id="actionIUP" name="action">
                    <input type="hidden" id="Name" name="Name" >
                </div>
                <div class="modal-footer">
                    <button id="saveConfigButton" name="saveConfigButton" type="submit" onclick class="btn btn-success" disabled><span  class="fa fa-save"></span> Save</button>
                    <button id="closeConfigButton" name="closeConfigButton" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div>

<c:if test="${! empty requestScope['result']}">
    <c:if test="${requestScope['result'] =='save successful'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
</c:if>