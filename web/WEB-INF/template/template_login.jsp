<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" ignore="true"/></title>
         <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link href="css/SMITravel.css" rel="stylesheet">
        <script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>  
    </head>
    <body>  
            <tiles:insertAttribute name="body" />
            <script src="js/bootstrap/bootstrap.min.js"></script>
    </body>
</html>