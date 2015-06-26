<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/RefundAirline.js"></script> 
<link href="css/jquery-ui.css" rel="stylesheet">

<section class="content-header" >
    <h1>
        Checking - Refund Airline
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i>Checking</a></li>          
        <li class="active"><a href="#">Refund Airline</a></li>
    </ol>
</section>

<div style="padding-top: 15px;padding-right: 0px "ng-app=""> 
    <div class="row">
       
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <div ng-include="'WebContent/Checking/CheckingAirTicketMenu.html'"></div>
        </div>
        
        <div class="col-sm-10">
            <div class="row" style="padding-left: 15px">  
                <div class="col-sm-6" style="padding-right: 15px">
                    <h4><b>Refund Airline</b></h4>
                </div>
            </div>
            <hr/>
            
            <form action="RefundAirline.smi" method="post" id="RefundAirlineForm" name="RefundAirlineForm" role="form">
               <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right" style="width: 140px">
                            <label class="control-label text-right">Refund No :</label>
                        </div>
                        <div class="col-xs-1" style="width: 320px">
                            <input id="refundNo" name="refundNo" type="text" class="form-control" value="">
                        </div>
                        <div class="col-xs-1 text-right"  style="width: 140px">
                            <label class="control-label text-right">Refund Date :</label>
                        </div>
                        <div class="col-xs-1"  style="width: 200px">
                            <div class='input-group date'>
                                <input id="inputRefundDate" name="inputRefundDate"  type="text" 
                                   class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="">
                                <span class="input-group-addon spandate"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right"  style="width: 140px">
                            <label class="control-label text-right">Refund Agent :</label>
                        </div>
                        <div class="col-xs-1"  style="width: 120px">
                            <div class="input-group" id="refundAgentCodeValidate">
                                <input type="text" class="form-control" id="refundAgentUser" name="refundAgentUser" value="${SelectedAgent.code}" />
                                <span class="input-group-addon" id="agen_modal"  data-toggle="modal" data-target="#RefundAgentModal">
                                    <span class="glyphicon-search glyphicon"></span>
                                </span>
                            </div>
                        </div>
                        <div class="col-xs-1 text-left" style="width: 200px">
                            <input type="text" class="form-control" id="refundAgentName" name="refundAgentName" value="${SelectedAgent.name}" readonly=""
                                data-bv-notempty="true" data-bv-notempty-message="agent empty !">                           
                        </div>
                        <div class="col-xs-1 text-right" style="width: 140px">
                            <label class="control-label text-right">Refund By :</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <div class="input-group">
                                <input id="refundBy" name="refundBy" type="text" class="form-control" value="">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 form-group">
                        <div class="col-xs-1 text-right"  style="width: 140px">
                            <label class="control-label text-right">Remark :</label>
                        </div>
                        <div class="col-xs-1" style="width: 200px">
                            <div class="input-group">                                    
                                <textarea rows="3" class="form-control" id="remark" name="remark" style="width: 180%"></textarea>  
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-12 text-right" >
                            <button style="height:30px" type="submit"  id="ButtonAdd"  name="ButtonAdd" onclick="addAction();" class="btn btn-primary btn-sm">&nbsp;&nbsp;Add&nbsp;&nbsp;</button>
                        </div>
                    </div>
                </div>
            </form>
                                
            <!--Table-->
            <div class="row">
                <div class="col-md-12 ">
                    <table id="RefundAirlineList" class="display" cellspacing="0" width="100%">
                        <thead>
                            <tr class="datatable-header" >
                                <th style="width:5%;">No</th>
                                <th style="width:10%;">Ticket No</th>
                                <th style="width:10%;">Issue Date</th>
                                <th style="width:10%;">Department</th>
                                <th style="width:10%;">Passenger</th>
                                <th style="width:10%;">Sector Issue</th>
                                <th style="width:10%;">Sector Refund</th>
                                <th style="width:7%;">Receive</th>
                                <th style="width:7%;">Pay</th>
                                <th style="width:7%;">Profit</th>
                                <th style="width:7%;">Com</th>
                                <th style="width:10%;">Action</th>
                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>      
                </div>
            </div>                

        </div>
    </div>
</div>
<!--Modal  Agent-->
<div class="modal fade" id="RefundAgentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"  id="Titlemodel">Refund Agent</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="RefundAgentTable">
                    <thead class="datatable-header">                     
                        <tr>
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                            <th class="hidden">Fax</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        agent = [];
                    </script>
                    <c:forEach var="a" items="${agent}">
                        <tr>
                            <td class="agent-id hidden">${a.id}</td>
                            <td class="agent-user">${a.code}</td>
                            <td class="agent-name">${a.name}</td>
                            <td class="agent-addr hidden">${a.address}</td>
                            <td class="agent-tel hidden">${a.tel}</td>
                            <td class="agent-fax hidden">${a.fax}</td>
                        </tr>
                        <script>
                            agent.push({id: "${a.id}", code: "${a.code}", name: "${a.name}", 
                                        address: "${a.address}", tel: "${a.tel}", fax: "${a.fax}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="RefundAgentModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!--Script-->       
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
   
        $('.date').datetimepicker();
    
    });
    
</script>
