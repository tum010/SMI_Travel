<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Hotel.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['hotel_list']}" />
<section class="content-header"  >
    <h4>
        <b>Master : Hotel</b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Hotel MA</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;"> 
    <form action="MHotel.smi" method="post" id="HotelForm" role="form" >
        <div class="row">
            <div class="col-md-8 col-xs-offset-2">
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
            </div>
        </div>
        <div class="row">
            <div class="col-md-2  col-xs-offset-2">
               
                <div class="form-group">
                    <label for="HotelCodeS">Code</label>
                    <input type="text" class="form-control" maxlength="10" id="CodeS" name="code" style="text-transform:uppercase" value="${requestScope['hotelCode']}">

                </div>
            </div>
            <div class="col-md-3 ">
                <div class="form-group">
                    <label for="HotelNameS">Name</label>
                    <input type="text" class="form-control" maxlength="50" id="NameS" name="name" style="text-transform:uppercase" value="${requestScope['hotelName']}">
                </div>
            </div>

            <div class="col-md-3" style="padding-left:  24px" >
                <div  style="padding-top: 20px">   
                    <button type="button" id="acs" onclick="searchAction()"  class="btn btn-primary"><span class="fa fa-search"></span> Search</button>           
                    <input type="hidden" name="action" id="Action"/>
                    <input type="hidden" id="HotelID" name="hotelID" >
                </div>
            </div>                   
        </div>
        <div class="row" style="padding-left: 5px">  
            <div class="col-md-3  col-xs-offset-2">
                <h4><b>Hotel</b></h4>
            </div>
            <div class="col-md-5 " style="padding-left:  235px">
                <a id="btnAdd" href="MHotelDetail.smi" class="btn btn-success">
                    <span id="spanAdd" class="glyphicon glyphicon-plus"></span>Add
                </a>
            </div>

        </div>
        

    </form>

    <div class="row">
        <div class="col-md-9  col-xs-offset-2">
            <table id="MasterHotel" class="display" cellspacing="0" >
                <thead>
                    <tr class="datatable-header">
                        <th>Code</th>
                        <th style="width:300px">Name</th>
                        <th>City</th>
                        <th>Country</th>
                        <th>Tel</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="table" items="${dataList}" varStatus="dataStatus">
                        <tr>
                            <td><c:out value="${fn:toUpperCase(table.code)}" /></td>
                            <td><c:out value="${fn:toUpperCase(table.name)}" /></td>
                            <td><c:out value="${fn:toUpperCase(table.MCity.name)}" /></td>
                            <td><c:out value="${fn:toUpperCase(table.MCountry.name)}" /></td>
                            <td><c:out value="${table.telNo}" /></td>
                    <td>
                    <center> 
                        <a id="btnEdit${dataStatus.count}" href="MHotelDetail.smi?hotelid=${table.id}&action=edit">
                            <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"  ></span>
                        </a>
                        <span id="spanRemove${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteHotel('${table.id}','${table.name}')" data-toggle="modal" data-target="#DelHotel" >  </span>
                    </center>
                    </td>
                    </tr>
                </c:forEach>


                </tbody>
            </table>   
        </div>

    </div>


</div>


<div class="modal fade" id="DelHotel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"> Delete hotel </h4>
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
        var table = $('#MasterHotel').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });

        $('#MasterHotel tbody').on('click', 'tr', function() {
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