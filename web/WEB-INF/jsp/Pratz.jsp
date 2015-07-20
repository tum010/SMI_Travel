<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="dataModel" value="${requestScope['data-model']}"/>
<h1>Pratz Data :: ${dataModel}</h1> 