<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="id" required="true"%>
<%@ attribute name="title" required="true"%>
<%@ attribute name="enableOKButton" required="false" rtexprvalue="true"%>
<%@ attribute name="enableSaveButton" required="false" rtexprvalue="true"%>


<div class="modal fade" id="${id}" tabindex="-1" role="dialog" aria-labelledby="${id}Label">
		<div class="modal-dialog" role="document">
			<div class="modal-content ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="${id}Label">${title}</h4>
				</div>
				<div class="modal-body">
					<jsp:doBody></jsp:doBody>
				</div>
				<div class="modal-footer">
					
					<c:if test="${enableOKButton}">
						<button id="${id}BtnOK" type="button" class="btn btn-primary">OK</button>
					</c:if>
					<c:if test="${enableSaveButton}">
						<button id="${id}BtnSave" type="button" class="btn btn-primary">Save</button>
					</c:if>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


