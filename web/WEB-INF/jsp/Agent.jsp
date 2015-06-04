<%@page                  contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Agent.js"></script> 
<script type="text/javascript" src="js/jquery.maskMoney.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['agent_list']}" />
<section class="content-header" >
    <h1>
        Master :Agent
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Agent MA</a></li>
    </ol>



</section>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        var table = $('#MasterAgent').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false
        });

        $('#MasterAgent tbody').on('click', 'tr', function() {
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
<div class="container" style="padding-top: 30px;">

    <form action="Agent.smi" method="post" id="AgentForm" role="form" >
        <div class="row">
            <div class="col-md-3  col-xs-offset-3">
                <div class="form-group">
                    <label for="AgentCodeS">Code</label>
                    <input  type="text" class="form-control" maxlength="15" id="CodeS" name="code" value="${requestScope['agentCode']}">
		

                </div>
            </div>
            <div class="col-md-3 ">
                <div class="form-group">
                    <label for="AgentNameS">Name</label>
                    <input type="text" class="form-control" maxlength="255" id="NameS" name="name" value="${requestScope['agentName']}" >

                </div>
            </div>

            <div class="col-md-3" style="padding-left:  24px">
                <div  style="padding-top: 20px">   
                    <button type="button" id="acs" onclick="searchAction()"  class="btn btn-primary">Search</button>           
                    <input type="hidden" name="action" id="Action"/>
                    <input type="hidden" id="agentID" name="agentID" >
                </div>
            </div>                   
        </div>
        <div class="row" style="padding-left: 15px">  
            <div class="col-md-5  col-xs-offset-3">
                <h4><b>Agent</b></h4>
            </div>
            <div class="col-md-4 " style="padding-left:  126px">
                <a id="ButtonAdd" href="MAgentDetail.smi" class="btn btn-success">
                        <span class="glyphicon glyphicon-plus"></span>Add
                </a>
            </div>

        </div>
        <div class="row">
            <div class="col-md-7  col-xs-offset-3">
                <table id="MasterAgent" class="display" cellspacing="0" >
                    <thead>
                        <tr class="datatable-header">
                            <th style="width: 30px" >Code</th>
                            <th style="width: 350px">Name</th>
                            <th style="width: 110px">Term pay</th>
                            <th >Pay By</th>
                            <th >Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${dataList}" varStatus="varDataList">
                            <tr>
                                <td><c:out value="${table.code}" /> </td>
                                <td><c:out value="${table.name}" /></td>
                                <td><c:out value="${table.MAccterm.name}" /></td>
                                <td><center> <c:out value="${table.MAccpay.name}" /> </center> </td>
                                <td>
                                <center> 
                                    <a id="btnEdit${varDataList.count}" href="MAgentDetail.smi?agentid=${table.id}&action=edit">
                                        <span id="spanEdit${varDataList.count}" class="glyphicon glyphicon-edit editicon"  ></span>
                                    </a>
                                    <span id="spanRemove${varDataList.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteAgent('${table.id}','${table.name}')" data-toggle="modal" data-target="#DelAgent" >  </span>
                                </center>
                        </td>    
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>   
            </div>

        </div>
    </form>

</div>

<div class="modal fade" id="DelAgent" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"> Delete Agent </h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelte" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<c:if test="${! empty requestScope['result']}">
    <script language="javascript">
        alert('<c:out value="${requestScope['result']}" />');
    </script>
</c:if>
