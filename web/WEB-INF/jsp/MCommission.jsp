 <%-- 
    Document   : Comission
    Created on : Mar 26, 2015, 9:25:13 PM
    Author     : Winit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>
<script type="text/javascript" src="js/MCommission.js"></script> 

<style>
    input:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px white inset;
    }
</style>

<section class="content-header" >
    <h1> Master -  Commission</h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#"> Commission</a></li>
    </ol>
</section>
<!--<input type="text" value="${requestScope['DeleteMCommission']}">-->
<div class ="container"  style="padding-top: 15px;">
    <div class="row">
        <!-- side bar -->
        <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px" id="Menu">
            <ul class="nav nav-list" style="top: 0px;  background-color: #FAFEFA;border: solid 1px #0063DC">
                <li class="">
                    <a href="MCommission.smi" id="agentLink" name="agentLink">
                        <i class="menu-icon fa fa-user"></i>
                        <span class="menu-text">Tour</span>
                    </a>
                    <b class="arrow"></b>
                </li>
                <li class="">
                    <a href="MProductCommission.smi" id="couponLink" name="couponLink">
                        <i class="menu-icon fa fa-user"></i>
                        <span class="menu-text">Coupon</span>
                    </a>
                    <b class="arrow"></b>
                </li>
            </ul>
        </div>
        <!-- main page -->
        <div class="col-md-10">
            <!--Alert Update --> 
            <div id="textAlertDivUpdate"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Update Success!</strong> 
            </div>
            <!--Alert Save --> 
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Success!</strong> 
            </div>
            <!--Alert Delete Success --> 
            <div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Delete Success!</strong> 
            </div>
            <!--Alert Not Save --> 
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Update Not Success!</strong> 
            </div>
            <div class="row" style="padding-left: 15px">
                <form action="MCommission.smi" method="post" id="searchCommission" role="form">
                    <div class="col-md-2 ">
                        <div class="form-group">
                            <label>Agent Code</label>
                            <input type="text" style="text-transform:uppercase" class="form-control" maxlength="15" id="AgentCodeSearch" name="AgentCodeSearch" value="${requestScope['agentcode']}"></input>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label>Agent Name</label>
                            <input type="text" style="text-transform:uppercase" class="form-control" maxlength="255" id="AgentNameSearch" name="AgentNameSearch" value="${requestScope['agentname']}">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label>Tour Code</label>
                            <input type="text" style="text-transform:uppercase" class="form-control" maxlength="50" id="TourCodeSearch" name="TourCodeSearch" value="${requestScope['tourcode']}">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="form-group">
                            <label>Tour Name</label>
                            <input type="text" style="text-transform:uppercase" class="form-control" maxlength="255" id="TourNameSearch" name="TourNameSearch" value="${requestScope['tourname']}">
                        </div>
                    </div>        
                    <div class="col-md-2">
                        <div style="padding-top: 20px">   
                            <input type="hidden" name="action" id="action" value="search">
                            <button type="submit" id="btnSearchCommission" name="btnSearchCommission" onclick="" class="btn btn-primary">Search</button>           
                        </div>
                    </div>
                </form>
            </div>
            <hr>

            <div class="row" style="padding-left: 15px">  
                <div class="col-md-10">
                    <h4><b>Tour Commission</b></h4>
                </div>
                <div class="col-md-2 " style="padding-right:  126px">
                    <a id="Add" name="Add" href="MCommissionDetail.smi?action=new">
                    <button id="ButtonAdd" name="ButtonAdd" type="button" class="btn btn-success" onclick="">
                        <span id="SpanAdd" name="SpanAdd" class="glyphicon glyphicon-plus"></span>Add
                    </button>
                </a>
                </div>

            </div>
            <br/>
            <div class="row" style="padding-left: 15px">    
                <div class="col-md-11">
                    <table id="MasterCommission" class="display" cellspacing="0" >
                        <thead>
                            
                            <tr class="datatable-header">
                                
                                <th style="width: 30%" colspan="2">Agent</th>
                                <th style="width: 30%" colspan="2">Tour</th>
                                <th style="width: 10%" rowspan="2">From</th>
                                <th style="width: 10%" rowspan="2">To</th>
                                <th style="width: 10%" rowspan="2">Commission(%)</th>
                                <th style="width: 10%"  rowspan="2" >Action</th>
                            </tr>
                            <tr class="datatable-header">
                                <th style="width: 10%">Code</th>
                                <th style="width: 20%">Name</th>
                                <th style="width: 10%">Code</th>
                                <th style="width: 20%">Name</th>
                            </tr> 
                            
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${AgentTourCommissions}" varStatus="loop">
                                <tr>
                                     
                                    <td><c:out value="${item.agentComission.agent.code}" /></td>
                                    <td><c:out value="${item.agentComission.agent.name}" /></td>
                                    <td><c:out value="${item.daytour.code}" /></td>
                                    <td><c:out value="${item.daytour.name}" /></td>
                                    <td class="text-center"><c:out value="${item.from}" /></td>
                                    <td class="text-center"><c:out value="${item.to}" /></td>
                                    <td class="text-right" id="compoint-${loop.count}"><c:out value="${item.comission}" /></td>
                                    <td class="text-center">
                                        <a id="ButtonEdit-${loop.count}" name="ButtonEdit-${loop.count}" href="MCommissionDetail.smi?action=edit&agentCommissionId=${item.agentComission.agent.id}&AgentComID=${item.agentComission.id}&InputTourId=${item.daytour.id}">
                                            <span  id="RowSpanEdit-${loop.count}" name="RowSpanEdit-${loop.count}"  class="glyphicon glyphicon-edit editicon"  ></span>
                                        </a>
                                        <span id="ButtonDelete-${loop.count}" name="ButtonDelete-${loop.count}" 
                                              class="glyphicon glyphicon-remove deleteicon"  
                                              onclick="DelCommissionAgent('${item.agentComission.id}','${item.agentComission.agent.code}',
                                                                          '${item.agentComission.agent.name}','${item.daytour.code}',
                                                                          '${item.daytour.name}')"
                                              data-toggle="modal" data-target="#DeleteModal" ></span>
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

<div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="MCommission.smi" method="post" id="DelMCommissionForm" class="form-horizontal"  role="form">            
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"> Delete Commission of Agent </h4>
                </div>
                <div class="modal-body" id="delContent"></div>
                <input type="hidden" id="deleteId" name="commissionId"/>
                <input type="hidden" id="deleteAgentCode" name="agentCode"/>
                <input type="hidden" id="deleteAgentName" name="agentName"/>
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
<script type="text/javascript" charset="utf-8">
        function DelCommissionAgent(id,agentcode,agentname,tourcode,tourname) {
//            alert('id:'+id);
            $("#deleteId").val(id);
            $('#delContent').html("Are you sure to delete Commission of agent : "+agentcode+" and tour : "+tourcode+" ?");      
        }
     

$(document).ready(function (){
    var i = 0;
    $("#MasterCommission tbody").find("tr").each(function(){
        i++;
        var parts = parseFloat($("#compoint-"+i).text()).toFixed(2).split(".");
        var num = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (+parts[1] ? "." + parts[1] : ".00");
        console.log("C"+i+":"+num);
        $("#compoint-"+i).text(num);
    });
 
//    $(".money").mask("000,000,000", {reverse: true});
//    $(".decimal").inputmask(
//        "decimal",{
//            radixPoint:".", 
//            groupSeparator: ",", 
//            digits: 2,
//            autoGroup: true
//     });
    
    var table = $('#MasterCommission').DataTable({
       
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bInfo": true,
        "bSort": false
        
    });
    $('#MasterCommission tbody').on('click', 'tr', function () {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
            $('#hdGridSelected').val('');
        }
        else {
            table.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
            $('#hdGridSelected').val($('#MasterCommission tbody tr.row_selected').attr("id"));
        }
    });
});

</script>
<c:if test="${! empty requestScope['DeleteMCommission']}">
    <c:if test="${requestScope['DeleteMCommission'] =='delete: success'}">        
        <script language="javascript">
            $('#textAlertDivDelete').show();
        </script>
    </c:if>
</c:if>
<c:if test="${! empty requestScope['VALIDATE']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['VALIDATE']}" />');
    </script>
</c:if>
<c:if test="${! empty requestScope['ResultSave']}">
    <c:if test="${requestScope['ResultSave'] =='update successful'}">        
        <script language="javascript">
            $('#textAlertDivUpdate').show();
        </script>
    </c:if>
    <c:if test="${requestScope['ResultSave'] =='update unsuccessful'}">        
        <script language="javascript">
           $('#textAlertDivNotSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['ResultSave'] =='save : success'}">        
        <script language="javascript">
           $('#textAlertDivSave').show();
        </script>
    </c:if>
</c:if>