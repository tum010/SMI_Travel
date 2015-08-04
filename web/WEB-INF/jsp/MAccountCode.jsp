
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Initial -->
<c:set var="dataTable" value="${requestScope['form'].dataTable}" />
<c:set var="accTypeList" value="${requestScope['accTypeList']}" />
<c:url var="baseURL" value="/MAccountCode.smi"></c:url>

<section class="content-header">
	<h1>Master Account - Account</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>
		<li><a href="MAccountCode.smi"> Master Account</a></li>
		<li class="active"><a href="MAccountCode.smi">Account</a></li>
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
		<form:form id="frmMAccountCode" action="${baseURL}" method="post" role="form" modelAttribute="form" onsubmit="onSubmit();">
			<div class="col-md-10">
				<!-- Feedback Message  -->
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

				<!-- Main Form -->
				<div class="row">
					
					<div class="col-md-3">
						<div class="form-group">
							<label for="accType">Account Type</label>
							<form:select path="accType" items="${accTypeList}" cssClass="form-control" onchange="onChangeAccType('accType','accCodeAddOn');"/>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label for="accCode">Code</label>
							<div class="input-group">
								<span class="input-group-addon" id="accCodeAddOn">&nbsp;</span>
								<form:input path="accCode2" cssClass="form-control" cssStyle="text-transform: uppercase" aria-describedby="accCodeAddOn"/>
								<form:hidden id="accCode" path="accCode"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div style="padding-top: 25px;padding-left:160px">
							<button id="btnSearch" type="submit"  class="btn btn-primary">
								<span class="fa fa-search">Search</span>
							</button>
						</div>
					</div>
					<input type="hidden" name="id" /><input type="hidden" name="detail" /><input id="action" type="hidden" name="action" value="search" />

				</div>
				<hr>
				<!-- Account Code DataTable -->
				<div class="row" style="padding-left: 15px">
					<div class="col-md-6">
						<h4>
							<b>Acoount</b>
						</h4>
					</div>
					<div class="col-md-6 " style="padding-left: 180px">
						<button id="btnAdd" type="button" class="btn btn-success" data-toggle="modal" data-target="#AccountCodeModal">
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
									<th style="width: 50px">Account Type</th>
									<th style="width: 210px">Detail</th>
									<th style="width: 70px">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="accountCode" items="${dataTable}" varStatus="status">
									<tr>
										<td class="hidden"><c:out value="${accountCode.id}" /></td>
										<td><c:out value="${accountCode.accCode}" /></td>
										<td>
											<c:choose>
												<c:when test="${accountCode.accType == 'A'}">
													<c:out value="ASSETS" />
												</c:when>
												<c:when test="${accountCode.accType == 'L'}">
													<c:out value="LIABILITIES" />
												</c:when>
												<c:when test="${accountCode.accType == 'S'}">
													<c:out value="SHARE HOLDER" />
												</c:when>
												<c:when test="${accountCode.accType == 'I'}">
													<c:out value="INCOMES" />
												</c:when>
												<c:when test="${accountCode.accType == 'E'}">
													<c:out value="EXPENSE" />
												</c:when>
												<c:otherwise>
													<c:out value="N/A" />
												</c:otherwise>
											</c:choose>							
										</td>
										<td><c:out value="${accountCode.detail}" /></td>
										<td>
											<center>
												<span class="glyphicon glyphicon-edit editicon" onclick="editAccountCode('${accountCode.id}', '${accountCode.accCode}', '${accountCode.accType}', '${accountCode.detail}')" data-toggle="modal" data-target="#AccountCodeModal"></span> <span class="glyphicon glyphicon-remove deleteicon" onclick="deleteAccountCode('${accountCode.id}', '${accountCode.accCode}', '${accountCode.accType}', '${accountCode.detail}')" data-toggle="modal" data-target="#delAccountCodeModal"></span>
											</center>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
<div class="modal fade" id="AccountCodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 500px">
		<form:form id="frmAdd" class="form-horizontal" role="form" modelAttribute="form">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">Account Code</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="accTypeModal" class="col-sm-3 control-label">Acc Type <font style="color: red">*</font></label>
						<div class="col-sm-8">
							<!-- <input type="text" class="form-control" maxlength="50" id="accType" name="accType" style="text-transform: uppercase" required> -->
							<form:select path="accTypeModal" items="${accTypeList}" cssClass="form-control" onchange="onChangeAccType('accTypeModal','accCodeModalAddOn');"/>
						</div>
					</div>
					<div class="form-group">
						<label for="accCodeModal" class="col-sm-3 control-label">Code <font style="color: red">*</font></label>
						<div class="col-sm-8">
							<div class="input-group">
								<span class="input-group-addon" id="accCodeModalAddOn">&nbsp;</span>
								<form:input path="accCodeModal2" cssClass="form-control" cssStyle="text-transform: uppercase" maxlength="4" aria-describedby="accCodeModal2"/>
								<form:hidden id="accCodeModal" path="accCodeModal"/>
							</div>
						<!-- <input type="text" class="form-control" maxlength="3" id="accCodeModal" name="accCodeModal" style="text-transform: uppercase" required> -->	
						</div>
					</div>
					<div class="form-group">
						<label for="detailModal" class="col-sm-3 control-label">Detail <font style="color: red">*</font></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="detailModal" name="detailModal" style="text-transform: uppercase">
						</div>
					</div>
					<input type="hidden" id="idModal" name="idModal">
				</div>
				<div class="modal-footer">
					<button id="btnSave" type="submit" class="btn btn-success">
						<span class="fa fa-save"></span> Save
					</button>
					<button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</form:form>
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="modal fade" id="delAccountCodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<form id="frmDelete" class="form-horizontal" role="form">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">Delete Account Code</h4>
					<input type="hidden" name="id" id="id" /> <input type="hidden" name="accCode" id="accCode" /> <input type="hidden" name="accType" id="accType" /> <input type="hidden" name="detail" id="detail" />
				</div>
				<div class="modal-body" id="delCode"></div>
				<div class="modal-footer">
					<button id="btnDelete" type="submit" class="btn btn-danger">Delete</button>
					<button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</form>
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script>

 	var AccountObject =  {
 			A:1,
 			L:2,
 			S:3,
 			I:4,
 			E:5,
 			'':''
 	};
 	
 	function onSubmit(){
 		//set accCode from Addon and Textbox
 		var accCodeAddOn = $('#accCodeAddOn').html();
 		var accCode2 = $('#accCode2').val();
 		$('#accCode').val(accCodeAddOn+accCode2);
 		
 	}
 	
	function onChangeAccType(selectedId,AddOnId){
		var selected = $('#'+selectedId).val();
		console.log('selected : '+selected);
		$('#'+AddOnId).html(AccountObject[selected]);
	}

	function deleteAccountCode(id, accCode, accType, detail) {
		$('#id').val(id);
		$('#accCode').val(accCode);
		$('#accType').val(accType);
		$('#detail').val(detail);
	}

	function editAccountCode(id, accCode, accType, detail) {
		
		$('#idModal').val(id);
		$('#accCodeModal').val(accCode);
		$('#accCodeModal2').val(accCode.substr(1));
		$('#accTypeModal').val(accType);
		$('#detailModal').val(detail);
		onChangeAccType('accTypeModal','accCodeModalAddOn');
		
	}

	$(function() {
		$('#btnSearch').click(function(e) {
			e.preventDefault();
			$('#action').val('search');
			$('#frmMAccountCode').submit();
		});
		
		$('#btnSave').click(function(e){
			e.preventDefault();
			$('#action').val('save');
			var accCodeModalAddOn = $('#accCodeModalAddOn').html();
	 		var accCodeModal2 = $('#accCodeModal2').val();
	 		$('#accCodeModal').val(accCodeModalAddOn+accCodeModal2);
			
			
			var hashList = $('#frmAdd').serializeArray();
			console.log(hashList);
			var appendStr = '';
			for(obj in hashList){
				//console.log(hashList[obj].name);
				appendStr = appendStr+'<input type="hidden" name="'+hashList[obj].name+'" value="'+hashList[obj].value+'"/>';
			}
			console.log(appendStr);
			$('#frmMAccountCode').prepend(appendStr);
			$('#frmMAccountCode').submit();
		});
		
		$('#btnClose').click(function(e){
			e.preventDefault();
		});
		
		onChangeAccType('accTypeModal','accCodeModalAddOn');
		onChangeAccType('accType','accCodeAddOn');
	});
</script>

<c:if test="${not empty requestScope['resultSave']}">
	<script>
		$("#${requestScope['resultSave']}").show();
	</script>
</c:if>