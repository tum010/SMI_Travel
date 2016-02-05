<%-- 
    Document   : MBank
    Created on : Jun 22, 2015, 4:47:40 PM
    Author     : chonnasith
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['Bank_List']}" />
<c:set var="bankSearch" value="${requestScope['BankSearch']}" />
<c:set var="bankData" value="${requestScope['BankData']}" />
<script type="text/javascript" src="js/MBank.js"></script> 
<style>
    input:-webkit-autofill {
        -webkit-box-shadow: 0 0 0px 1000px white inset;
    }
    #BankCode,#BankName,#BankBranch,#BankAccountNo,#BankAccountType:focus {

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
<section class="content-header" >
    <h1>
        Master - Bank

    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Bank</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" > 
    <div class="col-md-12">
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
        <!-- Alert Uni-->
        <div id="textAlertDivCodeAlreadyUsed"  style="display:none;" class="alert alert-danger" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Bank Code already exist!</strong> 
        </div>
        <!-- Alert Uni-->
        <div id="textAlertDivAccountAlreadyUsed"  style="display:none;" class="alert alert-danger" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Bank Account already exist!</strong> 
        </div>
        <!-- Alert Del-->
        <div id="textAlertDivDelete"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Delete Success!</strong> 
        </div>
        <!-- Alert Not Del-->
        <div id="textAlertDivNotDelete"  style="display:none;" class="alert alert-danger" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Delete Not Success!</strong> 
        </div>
        <div class="row">
            <form action="MBank.smi" method="post" id="SearchBank" role="form">
                <div class="col-md-1" style="width: 40px"></div>
                <div class="col-md-1" style="width: 130px">
                    <div class="form-group">
                        <label>Code</label>
                        <input type="text" style="text-transform:uppercase"   class="form-control" maxlength="100" id="CodeSearch" name="BankCode" value="${requestScope['bankCode']}">
                    </div>
                </div>                       
                <div class="col-md-1" style="width: 230px">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" style="text-transform:uppercase" class="form-control" maxlength="100" id="NameSearch" name="BankName" value="${requestScope['bankName']}">
                    </div>
                </div>                   
                <div class="col-md-1" style="width: 230px">
                    <div class="form-group">
                        <label>Branch</label>
                        <input type="text" style="text-transform:uppercase" class="form-control" maxlength="100" id="BrachSearch" name="BankBranch" value="${requestScope['bankBrach']}">
                    </div>
                </div>                
                <div class="col-md-1" style="width: 180px">
                    <div class="form-group">
                        <label>Account No.</label>
                        <input type="text" style="text-transform:uppercase" class="form-control" maxlength="50" id="AccountNoSearch" name="BankAccountNo" value="${requestScope['bankAccountNo']}">
                    </div>
                </div>                    
                <div class="col-md-1" style="width: 130px">
                    <div class="form-group">
                        <label>Account Type</label>
                        <select class="form-control" id="TypeSearch" name="BankAccountType">
                            <option value="">------------</option>
                                <c:choose>
                                    <c:when test="${requestScope['BankAccountType'] == '1'}">
                                        <c:set var="accountOneSelected" value="selected" />
                                    </c:when>
                                </c:choose>
                            <option value="1" ${accountOneSelected}>Account 1</option>
                                <c:choose>
                                    <c:when test="${requestScope['BankAccountType'] == '2'}">
                                        <c:set var="accountTwoSelected" value="selected" />
                                    </c:when>
                                </c:choose>
                            <option value="2" ${accountTwoSelected}>Account 2</option>
                        </select>    
                    </div>
                </div>
                <div class="col-md-1" style="width: 130px">
                    <div class="form-group">
                        <label>Status</label>
                        <select class="form-control" id="StatusSearch" name="BankStatus">
                            <option value="">------------</option>
                                <c:choose>
                                    <c:when test="${requestScope['BankStatus'] == 'Active'}">
                                        <c:set var="statusOneSelected" value="selected" />
                                    </c:when>
                                </c:choose>
                            <option value="ACTIVE" ${statusOneSelected}>Active</option>
                                <c:choose>
                                    <c:when test="${requestScope['BankStatus'] == 'Inactive'}">
                                        <c:set var="statusTwoSelected" value="selected" />
                                    </c:when>
                                </c:choose>
                            <option value="INACTIVE" ${statusTwoSelected}>Inactive</option>
                        </select>    
                    </div>
                </div>        
                <div class="col-md-1" style="width: 150px">
                    <div  style="padding: 22px 0px 0px 10px">   
                        <button type="button" id="searchBank" onclick="searchAction()"  class="btn btn-primary"><span class="fa fa-search"></span> Search</button>           
                        <input type="hidden" name="action" id="Action"/>
                        <input type="hidden" id="BankId" name="BankId" >
                    </div>
                </div>
            </form>

        </div>
        <hr>
        <div class="row" style="padding-left: 130px">  
            <div class="col-md-8  ">
                <h4><b>Bank</b></h4>
            </div>
            <div class="col-md-4 " style="padding: 5px 0px 0px 225px">
                <div class="col-md-2">
                    <button title="${sessionScope['id']}" type="button" class="btn btn-success" id="addBank"   data-toggle="modal"  data-target="#BankModal">
                        <span class="glyphicon glyphicon-plus" ></span>Add
                    </button>
                </div>
            </div>

        </div>

        <div class="row" style="padding-left:130px">    
            <div class="col-md-11">
                <table id="MasterBank" class="display" cellspacing="0" >
                    <thead>
                        <tr class="datatable-header">
                            <th class="hidden">Id</th>
                            <th style="width: 10%" >Code</th>
                            <th style="width: 30%">Name</th>
                            <th style="width: 25%">Branch</th>
                            <th style="width: 12%">Account No.</th>
                            <th style="width: 4%">Account Type</th>
                            <th style="width: 4%">Status</th>
                            <th style="width: 1%">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="table" items="${dataList}" varStatus="dataStatus">
                            <tr>
                                <td class="hidden"> <c:out value="${table.id}" /></td>
                                <td> <c:out value="${table.code}" /></td>
                                <td> <c:out value="${table.name}" /></td>
                                <td> <c:out value="${table.branch}" /></td>
                                <td> <c:out value="${table.accNo}" /></td>
                                <td align="center"> <c:out value="${table.accType}" /></td>
                                <td align="center"><c:out value="${table.status}" /></td>
                                <td> 
                                    <center> 
                                    <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon"      
                                      onclick="EditBank('${table.id}', '${table.code}', '${table.name}', '${table.branch}', '${table.accNo}', '${table.accType}', '${table.status}')" data-toggle="modal"  >
                                    </span>
                                    <span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteBank('${table.id}', '${table.code}', '${table.name}')" data-toggle="modal" data-target="#DelBank" >  </span>
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
                
<!--Bank Modal-->
<div class="modal fade" id="BankModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 550px">
        <form action="MBank.smi" method="post" id="Bankform" class="form-horizontal"  role="form" autocomplete="off">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title"><span class="fa fa-user"></span> Bank</h4>
                </div>
                <div class="modal-body">
                    <div class="col-md-7"></div>
                    <div class="form-group" style="display: block;" id="alertDiv">
                        <font style="color: red" id="alertMessage"></font>
                    </div>
                    <div class="form-group hidden">
                        <label for="BankCode" class="col-sm-3 control-label" >Id <font style="color: red">*</font></label>
                        <div class="col-sm-8"> 
                            <input type="text" style="text-transform:uppercase" class="form-control" maxlength="11" id="BankIdEdit" name="BankId" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="BankCode" class="col-sm-3 control-label" >Code <font style="color: red">*</font></label>
                        <div class="col-sm-8"> 
                            <input type="text" style="text-transform:uppercase" class="form-control specialcharactor" maxlength="21" id="BankCode" name="BankCode" onkeydown="clearAlertMessage()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="BankName" class="col-sm-3 control-label" >Name <font style="color: red">*</font></label>
                        <div class="col-sm-8">  
                            <input type="text" style="text-transform:uppercase" class="form-control" maxlength="255" id="BankName" name="BankName" onkeydown="clearAlertMessage()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="BankBranch" class="col-sm-3 control-label" >Branch <font style="color: red">*</font></label>
                        <div class="col-sm-8">   
                            <input type="text" style="text-transform:uppercase" class="form-control" maxlength="255" id="BankBranch" name="BankBranch" onkeydown="clearAlertMessage()"> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="BankAccountNo" class="col-sm-3 control-label" >Account No. <font style="color: red">*</font></label>
                        <div class="col-sm-8">   
                            <input type="text" style="text-transform:uppercase" class="form-control numerical" maxlength="20" id="BankAccountNo" name="BankAccountNo" onchange="clearAlertMessage()"> 
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="BankAccountType" class="col-sm-3 control-label" >Account Type  <font style="color: red">*</font></label>
                        <div class="col-sm-8 dropdown">
                            <select class="form-control col-sm-8" id="BankAccountType" name="BankAccountType" onchange="clearAlertMessage()">
                                <option value="">------------</option>
                                <option value="1">Account 1</option>
                                <option value="2">Account 2</option>
                            </select>    
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="BankStatus" class="col-sm-3 control-label" >Status<font style="color: red">*</font></label>
                        <div class="col-sm-8 dropdown">
                            <select class="form-control col-sm-8" id="BankStatus" name="BankStatus">
                                <option value="">------------</option>
                                <option value="ACTIVE">Active</option>
                                <option value="INAVTIVE">Inactive</option>
                            </select>    
                        </div>
                    </div>            
                    <input type="hidden" id="BankID" name="BankID" >
                    <input type="hidden" id="actionIUP" name="action">
                </div>
                <div class="modal-footer">
                    <button id="btnSave" type="submit" onclick class="btn btn-success" ><span  class="fa fa-save"></span> Save</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal" onclick="clearAlertMessage()">Close</button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div>               

<!--Delete Bank Modal-->
<div class="modal fade" id="DelBank" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title"> Delete bank </h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->   

<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        var table = $('#MasterBank').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });
        
        $('#addBank').click(function () {
            $('#Bankform').bootstrapValidator('resetForm', true);
            $('#Bankform')[0].reset();
            $('#Bankform').bootstrapValidator('resetForm', true);
            $('#createBy').val($("#addBank").attr("title"));
            $('#actionIUP').val("add");          
        });
        
        $('#MasterBank tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
                $('#hdGridSelected').val('');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
                $('#hdGridSelected').val($('#MasterBank tbody tr.row_selected').attr("id"));
            }
        });
    
        //Check enter key
        $("#CodeSearch,#NameSearch,#BranchSearch,#AccountNoSearch,#TypeSearch,#StatusSearch").keypress(function (event) {
            if (event.which === 13) {
                event.preventDefault();
                $('#searchBank').focus().click();
            }
        });

        // Set value back to search box.
        $("#CodeSearch").val("<c:out value="${bankSearch.code}" />");
        $("#NameSearch").val("<c:out value="${bankSearch.name}" />");
        $("#BranchSearch").val("<c:out value="${bankSearch.branch}" />");
        $("#AccountNoSearch").val("<c:out value="${bankSearch.accNo}" />");
        $("#TypeSearch").val("<c:out value="${bankSearch.accType}" />");
        $("#StatusSearch").val("<c:out value="${bankSearch.status}" />");
    });
    
    function validateFail(){
        $("#BankIdEdit").val("<c:out value="${bankData.id}" />");
        $("#BankCode").val("<c:out value="${bankData.code}" />");
        $("#BankName").val("<c:out value="${bankData.name}" />");
        $("#BankBranch").val("<c:out value="${bankData.branch}" />");
        $("#BankAccountNo").val("<c:out value="${bankData.accNo}" />");
        $("#BankAccountType").val("<c:out value="${bankData.accType}" />");
        $("#BankStatus").val("<c:out value="${bankData.status}" />");
        
        if(($("#BankIdEdit").val() !== null) && ($("#BankIdEdit").val()) !== ''){
            $('#actionIUP').val("update");
        } else {
            $('#actionIUP').val("add");
        }
        
        document.getElementById('alertMessage').innerHTML = '${requestScope['result']}';
        $('#BankModal').modal('show');
    }
    
    function clearAlertMessage(){
        document.getElementById('alertMessage').innerHTML = '';
    }
    
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
    <c:if test="${requestScope['result'] =='bank code already exist.'}">        
        <script language="javascript">
           $('#textAlertDivCodeAlreadyUsed').show();
           $(document).ready(function () {
               validateFail();     
           });                
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='account no already exist.'}">        
        <script language="javascript">
           $('#textAlertDivAccountAlreadyUsed').show();
           $(document).ready(function () {
               validateFail();     
           });  
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='delete successful'}">        
        <script language="javascript">
           $('#textAlertDivDelete').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='delete unsuccessful'}">        
        <script language="javascript">
           $('#textAlertDivNotDelete').show();
        </script>
    </c:if>    
</c:if>            
