<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap/font-awesome.min.css" />
        <link rel="stylesheet" href="css/dataTables.jqueryui.css">
        <link href="css/SMITravel.css" rel="stylesheet">
        <script src="js/datatable/js/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <!--Header-->
        
        <div class="page-section-header">
            <tiles:insertAttribute name="header" />
        </div> 
        <!--Body-->    
        <div class="page-section-main">
            <tiles:insertAttribute name="body" />
        </div>
        <!--Footer-->
        <div id="page-section-footer">
            <tiles:insertAttribute name="footer" />
        </div>
        <script src="js/jquery-1.11.0.min.js"></script>
        <script src="js/bootstrap/bootstrap.min.js"></script>
        <script src="js/bootstrap/navbar.js"></script>
        <script src="js/datatable/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.jeditable.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.validate.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.jeditable.time.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.jeditable.datepicker.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.jeditable.checkbox.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.jeditable.ajaxUpload.js" type="text/javascript"></script>
    </body>
</html>