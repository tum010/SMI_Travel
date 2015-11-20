<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['MDaytour_list']}" />
<c:set var="daytourSearch" value="${requestScope['DaytourSearch']}" />
<c:set var="stafftour" value="${requestScope['stafftour']}" />
<script type="text/javascript" src="js/mdaytour.js"></script> 
<section class="content-header"  >
    <h4>
        <b>Master : Day Tours</b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Day Tours MA</a></li>
    </ol>
</section>
<!--<input type="text" value="${requestScope['result']}">-->
<div class ="container"  style="padding-top: 15px;"> 
    <form action="MDaytour.smi" method="post" id="DaytourSearch" role="form" >
        <div class="row">
            <div class="col-md-8  col-md-offset-2">
            <!--Alert Save --> 
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Success!</strong> 
            </div> 
            <!--Alert Delete --> 
            <div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Delete Success!</strong> 
            </div>
            <div id="textAlertDivDeleteUseBooking"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Delete Not Success : Use Booking !</strong> 
            </div>
            <div id="textAlertDivDeleteUseExpense"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Delete Not Success : Use Tour Expense !</strong> 
            </div>
            <div id="textAlertDivDeleteUsePrice"  style="display:none;" class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Delete Not Success : Use Tour Price !</strong> 
            </div>
            </div>
            <div class="col-md-4  col-md-offset-2">
                <div class="form-group">
                    <label for="tourCode">Tour code</label>
                    <input type="text" class="form-control" maxlength="50" id="tourCode" name="tourCode" style="text-transform:uppercase" value="${requestScope['tourCode']}">

                </div>
            </div>
            <div class="col-md-4 ">
                <div class="form-group">
                    <label for="tourName">Tour name</label>
                    <input type="text" class="form-control" maxlength="255" id="tourName" name="tourName" style="text-transform:uppercase" value="${requestScope['tourName']}">
                </div>
            </div>

            <div class="col-md-2 " >
                <div  style="padding-top: 20px; padding-left: 8px">   
                    <button type="button" id="daytourSearchButton" name="daytourSearchButton" onclick="searchAction()"  class="btn btn-primary"><span class="fa fa-search"></span> Search</button>           
                    <input type="hidden" name="action" id="Action" value="search"/>
                </div>
            </div>   
        
        </div>
        <div class="row" >  
            <div class="col-md-5  col-md-offset-3">
            </div>
            <div class="col-md-2 col-md-offset-2" style="padding-left: 26px">
                <a id="Add" name="Add" href="MDaytourDetail.smi?action=new">
                    <button id="ButtonAdd" name="ButtonAdd" type="button" class="btn btn-success" onclick="">
                        <span id="SpanAdd" name="SpanAdd" class="glyphicon glyphicon-plus"></span>Add
                    </button>
                </a>
            </div>
        </div>
    </form>

    <div class="row">
        <div class="col-md-9  col-md-offset-2">
            <table id="MDaytour" class="display" cellspacing="0" >
                <thead>
                    <tr class="datatable-header">
                        <th style="width: 15%">Code</th>
                        <th>Name</th>
                        <th style="width: 10%">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="table" items="${dataList}" varStatus="loopCounter">
                        <tr>
                            <td><c:out value="${fn:toUpperCase(table.code)}" /></td>
                            <td><c:out value="${fn:toUpperCase(table.name)}" /></td>
                            <td>
                            <center> 
                                <a id="ButtonEdit-${loopCounter.count}" name="ButtonEdit-${loopCounter.count}" href="MDaytourDetail.smi?daytourid=${table.id}&action=edit">
                                    <span  id="RowSpanEdit-${loopCounter.count}" name="RowSpanEdit-${loopCounter.count}"  class="glyphicon glyphicon-edit editicon"  ></span>
                                </a>
                                <span id="ButtonDelete-${loopCounter.count}" name="ButtonDelete-${loopCounter.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDaytour('${table.id}', '${table.code}','${table.name}')" data-toggle="modal" data-target="#DeleteModal" >  </span>
                            </center>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>   
        </div>
    </div>
    <div class="row">
        <div class="col-md-9  col-md-offset-2" style="padding-top: 20px">
            <div class="panel panel-default">
                <div class="panel-heading">Initial staff tour</div>
                <div class="panel-body">

                    <div class="row">
                        <div class="col-md-11">
                            <div class="form-group">
                                <label class="col-sm-1 control-label" for="fromAdd">StaffTour</label>
                                <div class="col-sm-11">  
                                    <input type="text" class="form-control" id="StaffTourName" maxlength="255" name="StaffTourName" value="${stafftour}"  >  
                                </div>   
                            </div>
                        </div> 
                       <div class="col-md-1 " style="padding:0 9px 0 0" >
                           <button  type="button" id="InitialVarButton" name="InitialVarButton" onclick="savestaff();"  class="form-control btn btn-primary"><i class="fa fa-save"></i>Save</button>           
                      </div>                                            
                    </div>
                </div>
            </div>    
         </div>           
    </div>    
</div>

<div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="MDaytour.smi" method="post" id="DelMDaytourForm" class="form-horizontal"  role="form">            
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"> Delete Day Tours </h4>
                </div>
                <div class="modal-body" id="delCode"></div>
                <input type="hidden" id="deleteId" name="daytourId"/>
                <input type="hidden" id="deleteTourCode" name="tourCode"/>
                <input type="hidden" id="deleteTourName" name="tourName"/>
                <input type="hidden" id="deleteAction" name="action" value="delete"/>
                <div class="modal-footer">
                    <button id="btnDelete" type="submit" class="btn btn-danger">Delete</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->  
<script type="text/javascript" >
        $(document).ready(function () {
            //Check enter key
            $("#tourCode,#tourName").keypress(function (event) {
                if (event.which == 13) {
                    event.preventDefault();
                    $('#daytourSearchButton').focus().click();
                }
            });

            // Set value back to search box.
            $("#tourName").val("<c:out value="${daytourSearch.name}" />");
            $("#tourCode").val("<c:out value="${daytourSearch.code}" />");
        });   
</script>
<c:if test="${! empty requestScope['result']}">
    <c:if test="${requestScope['result'] =='save : success'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='delete successful'}">        
        <script language="javascript">
            $('#textAlertDivDelete').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='Use Booking'}">        
        <script language="javascript">
            $('#textAlertDivDeleteUseBooking').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='Use Tour Price'}">        
        <script language="javascript">
            $('#textAlertDivDeleteUsePrice').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='Use Tour Expense'}">        
        <script language="javascript">
            $('#textAlertDivDeleteUseExpense').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='tour code already exist'}">        
        <script language="javascript">
           $('#textAlertLap').show();
        </script>
    </c:if>
</c:if>