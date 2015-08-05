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
									<th style="width: 30px">Code</th>
									<th style="width: 50px">Name</th>
									<th style="width: 210px">Type</th>
									<th style="width: 70px">APCode</th>
									<th style="width: 70px">ARCode</th>
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
<smi:modal title="AP/AR Code" id="formModal" enableOKButton="true">
	<smi:input name="aparCode.id" type="hidden" id="aparCode.id"/>
	<smi:input label="Code" name="aparCode.code" type="text" id="aparCode.code"/>
	<smi:input label="Name" name="aparCode.name" type="text" id="aparCode.name"/>
	<smi:input label="APCode" name="aparCode.apcode" type="text" id="aparCode.apcode"/>
	<smi:input label="ARCode" name="aparCode.arcode" type="text" id="aparCode.arcode"/>
</smi:modal>
</form:form>
<script>
function editAPARCode(id,code,name,type,apcode,arcode){
	alert(type);
	$('#aparCode.id').val(id);
	$('#aparCode.code').val(code);
	$('#aparCode.name').val(name);
	$('#aparCode.apcode').val(apcode);
	$('#aparCode.arcode').val(arcode);
}

$(function(){
	
	$('#btnSearch').click(function(e){
		e.preventDefault();
		$('#action').val('search');
	});
	
	$('#formModalBtnOK').click(function(e){
		e.preventDefault();
		$('#action').val('edit');
	});
	
});
</script>
<c:if test="${not empty requestScope['resultSave']}">
	<script>
		$("#${requestScope['resultSave']}").show();
	</script>
</c:if>


