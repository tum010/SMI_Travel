<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="smi" uri="/WEB-INF/tlds/smi.tld"%>

<!-- Initial -->
<c:set var="dataTable" value="${requestScope['form'].dataTable}" />
<c:set var="typeList" value="${requestScope['typeList']}" />
<c:url var="baseURL" value="/APARCode.smi"></c:url>
<form:form id="frmAPARCode" action="${baseURL}" method="post" role="form" modelAttribute="form">
<input type="hidden" name="action" id="action"/>
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
					<strong>APARCode already exist!</strong>
				</div>

				<!-- Main Form -->
				<div class="row form-inline">
					<div class="col-md-3">
						<smi:input label="Code" name="code" type="text" id="code"/>
					</div>
					<div class="col-md-3">
						<smi:input label="Name" name="name" type="text" id="name"/>
					</div>
					<div class="col-md-3">
						<smi:input label="Type" name="type" type="select" id="type" optionitems="${typeList}"/>
					</div>
					<div class="col-md-3">
						<div style="padding-top: 25px; padding-left: 160px">
							<button id="btnSearch" type="submit" class="btn btn-primary">
								<span class="fa fa-search">Search</span>
							</button>
						</div>
					</div>
				</div>
				<hr>
				<!-- Account Code DataTable -->
				<div class="row" style="padding-left: 15px">
					<div class="col-md-9">
						<table id="MasterOthers" class="display" cellspacing="0">
							<thead>
								<tr class="datatable-header">
									<th class="hidden"></th>
									<th style="width: 190px">Code</th>
									<th style="width: 500px">Name</th>
									<th style="width: 90px">Type</th>
									<th style="width: 70px">APCode</th>
									<th style="width: 70px">ARCode</th>
									<th style="width: 70px">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="aparCode" items="${requestScope['form'].dataTable}" varStatus="status">
									<tr>
										<td class="hidden"><c:out value="${aparCode.id}" /></td>
										<td><c:out value="${aparCode.code}" /></td>
										<td><c:out value="${aparCode.name}" /></td>
										<td><c:out value="${aparCode.type}" /></td>
										<td><c:out value="${aparCode.apcode}" /></td>
										<td><c:out value="${aparCode.arcode}" /></td>
										<td>
											<center>
												<span class="glyphicon glyphicon-edit editicon" onclick="editAPARCode('${aparCode.id}', '${aparCode.code}', '${aparCode.name}', '${aparCode.type}', '${aparCode.apcode}', '${aparCode.arcode}')" data-toggle="modal" data-target="#formModal"></span>
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
<smi:modal title="AP/AR Code" id="formModal" enableOKButton="true" enableCancelButton="true">
	<smi:input name="aparCode.id" type="hidden" id="frmModalId"/>
	<smi:input label="Code" name="aparCode.code" type="text" id="frmModalCode" isReadonly="true"/>
	<smi:input label="Name" name="aparCode.name" type="text" id="frmModalName" isReadonly="true"/>
	<smi:input label="APCode" name="aparCode.apcode" type="text" id="frmModalApcode"/>
	<smi:input label="ARCode" name="aparCode.arcode" type="text" id="frmModalArcode"/>
</smi:modal>
</form:form>
<script>
function editAPARCode(id,code,name,type,apcode,arcode){
	if(type=='hotel'){
		$('#frmModalApcode').attr('readonly','readonly');
	}
	$('#frmModalId').val(id);
	$('#frmModalCode').val(code);
	$('#frmModalName').val(name);
	$('#frmModalApcode').val(apcode);
	$('#frmModalArcode').val(arcode);
}

$(function(){
	$('#btnSearch').click(function(e){
		e.preventDefault();
		$('#action').val('search');
		$('#frmAPARCode').submit();
	});
	
	$('#formModalBtnOK').click(function(e){
		e.preventDefault();
		$('#action').val('edit');
		$('#frmAPARCode').submit();
	});
	
});
</script>
<c:if test="${not empty requestScope['resultSave']}">
	<script>
		$("#${requestScope['resultSave']}").show();
	</script>
</c:if>


