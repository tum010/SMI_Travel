<%-- 
    Document   : TicketSummaryCommission
    Created on : Oct 12, 2015, 9:20:12 AM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/TicketSummaryCommission.js"></script> 
<link href="css/jquery-ui.css" rel="stylesheet">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="customerAgentList" value="${requestScope['customerAgent']}" />
<section class="content-header"  >
    <h4>
        <b>Report : Air Ticket </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Ticket Summary Commission </a></li>
    </ol>
</section>

