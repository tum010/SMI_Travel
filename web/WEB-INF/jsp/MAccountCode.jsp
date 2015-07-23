<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- 
<c:set var="dataList" value="${requestScope['Airline_List']}" />

<script type="text/javascript" src="js/MAirticket.js"></script>  -->

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




			<div class="row">

				<!--  <form action="MAccountCode.smi" method="post" id="frmSearch" role="form"> -->
				<form:form action="/MAccountCode.smi" id="frmSearch" role="form" modelAttribute="form">
					<div class="col-md-4">
						<div class="form-group">
							<label for="txtCode">Code</label> 
							<!-- <input type="text" class="form-control" maxlength="3" id="txtCode" name="txtCode" style="text-transform: uppercase" value="${requestScope['txtCode']}"> -->
							<form:input path="code" cssClass="form-control" maxlength="3" id="code" cssStyle="text-transform: uppercase"/>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="accType">Account Type</label> 
							<!--<input type="text" class="form-control" id="AirNameS" name="AirNameS" style="text-transform: uppercase" value="${requestScope['airName']}"> -->
							<form:input path="accType" cssClass="form-control" maxlength="3" id="accType" cssStyle="text-transform: uppercase"/>
						</div>
					</div>
					<div class="col-md-4">
						<div style="padding-top: 20px">
							<button type="button" id="acs" onclick="searchAction()" class="btn btn-primary">
								<span class="fa fa-search"></span>Search
							</button>
							<input type="hidden" name="action" id="Action" />
						</div>
					</div>
					<!--  </form>-->
				</form:form>
			</div>
			<hr>
			<div class="row" style="padding-left: 15px">
				<div class="col-md-6">
					<h4>
						<b>Acoount</b>
					</h4>
				</div>
				<div class="col-md-6 " style="padding-left: 182px">
					<button id="btnAdd" type="button" class="btn btn-success" onclick="addaction()" data-toggle="modal" data-target="#AirModal">
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
							<!--<c:forEach var="table" items="${dataList}" varStatus="dataStatus">-->
								<tr>
									<td class="hidden"><!--<c:out value="${table.name}" />--></td>
									<td><!--<c:out value="${fn:toUpperCase(table.code)}" />--></td>
									<td><!--<c:out value="${fn:toUpperCase(table.name)}" />--></td>
									<td><!--<c:out value="${fn:toUpperCase(table.code3Letter)}" />--></td>
									<td>
										<!--<center>
											<span id="editSpan${dataStatus.count}" class="glyphicon glyphicon-edit editicon" onclick="EditAir('${table.id}', '${table.code}', '${table.name}', '${table.code3Letter}')" data-toggle="modal" data-target="#AirModal"></span> <span id="removeSpan${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon" onclick="DeleteAir('${table.id}', '${table.code}')" data-toggle="modal" data-target="#delAirModal"></span>
										</center>-->
									</td>
								</tr>
							<!--</c:forEach>-->
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="AirModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 500px">
		<form action="Mairticket.smi" method="post" id="Aitlineform" class="form-horizontal" role="form">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">AirLine</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="AirCode" class="col-sm-3 control-label">Code <font style="color: red">*</font>
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" maxlength="3" id="AirCode" name="AirCode" style="text-transform: uppercase" required>
						</div>
					</div>
					<div class="form-group">
						<label for="AirName" class="col-sm-3 control-label">Account Type <font style="color: red">*</font>
						</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" maxlength="50" id="AirName" name="AirName" style="text-transform: uppercase" required>

						</div>
					</div>
					<div class="form-group">
						<label for="Code3" class="col-sm-3 control-label">Detail</label>
						<div class="col-sm-8">
							<!-- <input type="text" class="form-control" maxlength="3" id="Code3" name="Code3" style="text-transform:uppercase" >  -->
							<textarea name="detail" id="detail" maxlength="255" class="form-control" rows="3">-</textarea>
						</div>
					</div>
					<input type="hidden" id="AirID" name="AirID"> <input type="hidden" id="actionIUP" name="action">

				</div>
				<div class="modal-footer">
					<button id="btnSave" type="submit" class="btn btn-success">
						<span class="fa fa-save"></span> Save
					</button>
					<button id="btnClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</form>
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<div class="modal fade" id="delAirModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">Delete Airline</h4>
			</div>
			<div class="modal-body" id="delCode"></div>
			<div class="modal-footer">
				<button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
				<button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<c:if test="${! empty requestScope['airlineLap']}">
	<script language="javascript">
		$('#textAlertLap').show();
	</script>
</c:if>
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