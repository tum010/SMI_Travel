
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set var="accCode" value="${requestScope['form'].accCode}"/>
<c:set var="accType" value="${requestScope['form'].accType}"/>

<section class="content-header">
	<h1>Master Account - Account</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>
		<li><a href="Mairticket.smi"> Master Account</a></li>
		<li class="active"><a href="Mairticket.smi">Account</a></li>
	</ol>
</section>

<div class="container" style="padding-top: 15px;" ng-app="">
	<div class="row">
		<!-- side bar -->
		<div class="col-sm-2" style="border-right: solid 1px #01C632; padding-top: 10px">
			<div ng-include="'WebContent/Master/AccountMenu.html'"></div>
		</div>
		<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				var table = $('#MasterOthers').dataTable({
					bJQueryUI : true,
					"sPaginationType" : "full_numbers",
					"bAutoWidth" : false,
					"bFilter" : false,
					"bInfo" : false
				});

				$('#MasterOthers tbody').on('click', 'tr', function() {
					if ($(this).hasClass('row_selected')) {
						$(this).removeClass('row_selected');
					} else {
						table.$('tr.row_selected').removeClass('row_selected');
						$(this).addClass('row_selected');
					}
				});

			});
		</script>
		
		<!-- main page -->
		<div class="col-md-10">
			<!--Alert Save -->
			<div id="textAlertDivSave" style="display: none;" class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Save Success!</strong>
			</div>
			<!--Alert Not Save -->
			<div id="textAlertDivNotSave" style="display: none;" class="alert alert-danger" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Save Not Success!</strong>
			</div>
			<!-- Alert Uni-->
			<div id="textAlertLap" style="display: none;" class="alert alert-danger" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Account Code already exist!</strong>
			</div>

			<!--  -->
			<div class="row">
			<form action='<c:url value="/MAccountCode.smi/search"/>' method="post" id="frm"  role="form">
				<div class="col-md-4">
					<div class="form-group">
						<label for="accCode">Code</label> 
						<input type="text" class="form-control" maxlength="3" id="accCode" name="accCode" style="text-transform: uppercase" value="${accCode}"/>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="accType">Account Type</label>
						<input type="text" class="form-control" id="accType" name="accType" style="text-transform: uppercase" value="${accType}">
					</div>
				</div>
				<div class="col-md-4">
					<div style="padding-top: 20px">
						<button type="submit" id="acs" class="btn btn-primary">
							<span class="fa fa-search">Search</span>
						</button>
					</div>
				</div>
				<input type="hidden" name="id"/>
				<input type="hidden" name="detail"/>
				</form>
			</div>
			<hr>
			
			<!-- Account Code DataTable -->
			<div class="row" style="padding-left: 15px">
				<div class="col-md-6">
					<h4>
						<b>Acoount</b>
					</h4>
				</div>
				<div class="col-md-6 " style="padding-left: 182px">
					<button id="btnAdd" type="button" class="btn btn-success"  data-toggle="modal" data-target="#AccountCodeModal">
						<span id="spanAdd" class="glyphicon glyphicon-plus"></span>Add
					</button>
				</div>

			</div>
			<div class="row" style="padding-left: 15px">
				<div class="col-md-9">
					<table id="MasterOthers" class="display" cellspacing="0">
						<thead>
							<tr class="datatable-header">
								<th class="hidden"></th>
								<th style="width: 30px">Code</th>
								<th>Account Type</th>
								<th style="width: 110px">Detail</th>
								<th style="width: 70px">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="accountCode" items="${requestScope['form'].dataTable}" varStatus="status">
							<tr>
								<td class="hidden">
									<c:out value="${accountCode.id}" />
								</td>
								<td>
									<c:out value="${accountCode.accCode}" />
								</td>
								<td>
									<c:out value="${accountCode.accType}" />
								</td>
								<td>
									<c:out value="${accountCode.detail}" />
								</td>
								<td>
									<center>
										<span class="glyphicon glyphicon-edit editicon" onclick="editAccountCode('${accountCode.id}', '${accountCode.accCode}', '${accountCode.accType}', '${accountCode.detail}')" data-toggle="modal" data-target="#AccountCodeModal"></span> <span  class="glyphicon glyphicon-remove deleteicon" onclick="deleteAccountCode('${accountCode.id}', '${accountCode.accCode}', '${accountCode.accType}', '${accountCode.detail}')" data-toggle="modal" data-target="#delAccountCodeModal"></span> 
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

<div class="modal fade" id="AccountCodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <form action="/${pageContext.request.contextPath}/MAccountCode.smi/add" method="post" id="frmModal" class="form-horizontal"  role="form">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Account Code</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="accCode" class="col-sm-3 control-label" >Code <font style="color: red">*</font></label>
                        <div class="col-sm-8"> 
                            <input type="text" class="form-control" maxlength="3" id="accCode" name="accCode" style="text-transform:uppercase" required >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="accType" class="col-sm-3 control-label" >Acc Type <font style="color: red">*</font></label>
                        <div class="col-sm-8">  
                            <input type="text" class="form-control" maxlength="50" id="accType" name="accType" style="text-transform:uppercase" required >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="detail" class="col-sm-3 control-label" >Detail <font style="color: red">*</font></label>
                        <div class="col-sm-8">   
                            <input type="text" class="form-control" maxlength="3" id="detail" name="detail" style="text-transform:uppercase" > 
                        </div>
                    </div>
                    <input type="hidden" id="id" name="id" >
                </div>
                <div class="modal-footer">
                    <button id="btnSave" type="submit"  class="btn btn-success"><span  class="fa fa-save"></span> Save</button>
                    <button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="delAccountCodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
    <form action='<c:url value="/MAccountCode.smi/delete"/>' method="post" id="frmModal" class="form-horizontal"  role="form">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Account Code</h4>
                <input type="hidden" name="id" id="id"/>
                <input type="hidden" name="accCode" id="accCode"/>
                <input type="hidden" name="accType" id="accType"/>
                <input type="hidden" name="detail" id="detail"/>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="submit"  class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
function deleteAccountCode(id,accCode,accType,detail){
	$('#id').val(id);
	$('#accCode').val(accCode);
	$('#accType').val(accType);
	$('#detail').val(detail);
}

function editAccountCode(id,accCode,accType,detail){
	$('#id').val(id);
	$('#accCode').val(accCode);
	$('#accType').val(accType);
	$('#detail').val(detail);
}

$(function() {
	$('#acs').click(function(e){
		e.preventDefault();
		$('#frm').submit();
	});
});
</script>