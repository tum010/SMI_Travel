<%@ tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="id" required="true"%>
<%@ attribute name="name" required="true" %>
<%@ attribute name="type" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="placeholder" required="true" %>


<div class="form-group">
    <label for="${id}">${label}</label>
    <input type="${type}" class="form-control" id="${id}" placeholder="${placeholder}">
</div>


