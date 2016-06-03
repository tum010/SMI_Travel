<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/MAgentDetail.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="accpayList" value="${requestScope['accpay_list']}" />
<c:set var="acctermList" value="${requestScope['accterm_list']}" />
<c:set var="branchList" value="${requestScope['branch_list']}" />
<c:set var="disableAgentCode" value="${requestScope['disableAgentCode']}" />

<section class="content-header" >
    <h1>
        Master - Agent
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Agent MA</a></li>
    </ol>
</section>

<div class="container" style="padding-top: 10px;">
            <!-- Alert Uni-->
            <div id="textAlertLap"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Agent name already exist!</strong> 
            </div>
    <form action="MAgentDetail.smi" method="post" id="AgentForm" role="form" class="form-horizontal">
        <div class="col-md-8 col-xs-offset-2">
            <div class="panel panel-default" style="margin-bottom:5px;">
                <div class="panel-heading">Detail</div>
                <div class="panel-body">

                    <div class="row">
                        <div class="row">
                        <div class="col-md-12 form-group">
                            <div class="col-md-6 col-md-offset-6 text-right">
                                <a id="ButtonBack" name="ButtonBack" href="Agent.smi" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Back</a>
                            </div>
                        </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3   control-label" for="codeAgent">Code <font style="color: red">*</font></label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" maxlength="15" ${disableAgentCode} id="agentcode" name="agentcode" style="text-transform:uppercase" value='${requestScope['agentcode']}' readonly=""> 
                                    <input type="text" class="hidden" id="tempagentcode"  maxlength="10" name="tempagentcode"  value="${requestScope['agentcode']}" >
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="nameAgent">Name <font style="color: red">*</font></label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" maxlength="255" id="agentname" name="agentname" style="text-transform:uppercase" value='${requestScope['agentname']}'>  
                                </div>
                            </div>
                        </div>
                    </div>  
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="fromDetail">Detail</label>
                                <div class="col-sm-9">  
                                    <textarea name="Detail" id="description" maxlength="255" class="form-control" rows="3">${requestScope['Detail']}</textarea>
                                </div>   
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="fromAdd">Address</label>
                                <div class="col-sm-9">  
                                    <textarea name="Address" id="address" maxlength="255" class="form-control" style="text-transform:uppercase" rows="3">${requestScope['Address']}</textarea>
                                </div>   
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="Pay">term pay</label>
                                <div class="col-sm-6">
                                    <select name="termpay" id="country"  class="form-control">
                                        <option value="0"  selected="selected">--select term--</option>
                                        <c:forEach var="table" items="${acctermList}">
                                            <c:set var="select" value="" />
                                            <c:if test="${table.id == requestScope['termpay']}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option value='${table.id}' ${select}>${table.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>        
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="Pay">Pay by</label>
                                <div class="col-sm-6">
                                    <select name="payby" id="country"  class="form-control">
                                        <option value="0"  selected="selected">--select pay--</option>
                                        <c:forEach var="table" items="${accpayList}">
                                            <c:set var="select" value="" />
                                            <c:if test="${table.id == requestScope['payby']}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option value='${table.id}'  ${select}>${table.name}</option>
                                            
                                        </c:forEach>
                                           
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3   control-label" for="Tax">Tax NO</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" maxlength="50" id="Tax" name="Tax" value='${requestScope['Tax']}'>  
                                </div>
                            </div>
                        </div>                                    

                    </div>           
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3   control-label" for="codeProduct">Branch</label>
                                <div class="col-sm-6">
                                    <select name="Branch" id="Branch"  class="form-control">
                                        <option value=""> --select branch-- </option> 
                                        <c:forEach var="table" items="${branchList}">
                                            <c:set var="select" value="" />
                                            <c:if test="${table.id == requestScope['Branch']}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option value='${table.id}'  ${select}>${table.name}</option>
                                            
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="nameProduct">BranchNo</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" maxlength="50" id="BranchNo" name="BranchNo" value='${requestScope['BranchNo']}' >  
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3   control-label" for="codeProduct">Tel</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" maxlength="20" id="Tel" name="Tel" value='${requestScope['Tel']}'>  
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="nameProduct">Fax</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" maxlength="100" id="Fax" name="Fax" value='${requestScope['Fax']}'>  
                                </div>
                            </div>
                        </div>
                    </div>  
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3   control-label" for="codeProduct">Email</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" maxlength="50" id="Email" name="Email" value='${requestScope['Email']}' >  
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="nameProduct">Web</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" maxlength="255"  id="Web" name="Web" value='${requestScope['Web']}' >  
                                </div>
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3   control-label" for="codeProduct">warning</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" maxlength="255" id="warning" name="warning" value='${requestScope['warning']}' >  
                                    
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-3 control-label" for="nameRemark">remark</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" maxlength="255" id="remark" name="remark" value='${requestScope['remark']}' >  
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="AgentID" name="AgentID" value="${requestScope['AgentID']}">
                        <input type="hidden" id="actionIUP" name="action" value="save">
                    </div> 

                </div>
            </div>
            <div class="row">
                 <div class="col-xs-12 text-center">
                        <button id="saveHotel" name="saveHotel" type="submit" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                 </div>
            </div>

        </div>
    </form>   

</div>
<c:if test="${! empty requestScope['agentLap']}">
    <script language="javascript">
        $('#textAlertLap').show();
    </script>
</c:if>