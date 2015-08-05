<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ attribute name="id" required="true"%>
<%@ attribute name="name" required="true"%>
<%@ attribute name="type" required="true"%>
<%@ attribute name="label" required="false"%>
<%@ attribute name="optionitems" required="false" type="java.util.Map"%>

<div class="form-group">
	<c:if test="${not empty label}">
		<label for="${id}" class="control-label">${label}</label>
	</c:if>
	<c:choose>
		<c:when test="${type=='textarea'}">
			<form:textarea path="${name}" id="${id}" class="form-control" />
		</c:when>
		<c:when test="${type=='text'}">
			<form:input path="${name}" id="${id}" class="form-control" />
		</c:when>
		<c:when test="${type=='hidden'}">
			<form:hidden path="${name}" id="${id}"/>
		</c:when>
		<c:when test="${type=='select'}">
			<form:select path="${name}" id="${id}" class="form-control" items="${optionitems}"></form:select>
		</c:when>
		<c:otherwise>
			<c:out value="Unknow input type?"></c:out>
		</c:otherwise>
	</c:choose>
</div>


