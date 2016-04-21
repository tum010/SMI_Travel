<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="checkDuplicateUserBooking" value="${sessionScope.checkDuplicateUser}"/>
<html>
    <head>
        <input type="hidden" id="operationUserBooking" name="operationUserBooking" value="${checkDuplicateUserBooking.operationUser}"/>
        <input type="hidden" id="operationDateBooking" name="operationDateBooking" value="${checkDuplicateUserBooking.operationDate}"/> 
        <input type="hidden" id="operationTableBooking" name="operationTableBooking" value="${checkDuplicateUserBooking.operationTable}"/>
        <input type="hidden" id="operationTableIdBooking" name="operationTableIdBooking" value="${checkDuplicateUserBooking.tableId}"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
		
        <!--css-->
        <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap/font-awesome.min.css" />
        <link rel="stylesheet" href="css/bootstrap/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" href="css/dataTables.jqueryui.css">
        <link rel="stylesheet" href="css/jquery-ui.css">
<!--	<link href="css/select2.min.css" rel="stylesheet">-->
        <link href="css/selectize.bootstrap3.css" rel="stylesheet">
        <link href="css/SMITravel.css" rel="stylesheet">
        <link href="css/bootstrapValidator.css" rel="stylesheet">
        <link href="css/loadingpage.css" rel="stylesheet">

        <!--javascript-->
		
        <script src="js/jquery-1.11.0.min.js"></script>
        <script src="js/bootstrapValidator.min.js" type="text/javascript"></script>
        <script src="js/angular.min.js"></script>
<!--	<script src="js/select2.full.js"></script>-->
        <script src="js/es.js"></script>
        <script type="text/javascript" src="js/jquery.mask.min.js"></script>
        <script type="text/javascript" src="js/Utility.js"></script>
        
    </head>
    <body>
        <fmt:setLocale value="en_US" scope="session"/>
        <!--Header-->         
        <div class="page-section-header">          
            <tiles:insertAttribute name="header" />
        </div> 
        <!--Body-->
        <div class="overlay" id="loading-test">
        <div class="loading">
            <img id="loading-image" src="img/loadingBar.GIF" alt="Loading..." />
        </div>
        </div>    
        <div class="page-section-main">
            <tiles:insertAttribute name="body" /> 
        </div>
       
        <!--Footer-->
        <div id="page-section-footer">
            <tiles:insertAttribute name="footer" />
        </div>
        <script src="js/selectize.js"></script>
        <script src="js/common.js"></script>
        <script src="js/moment.js"></script>
        <script src="js/loadingpage.js"></script>
        <script src="js/bootstrap/bootstrap.min.js"></script>
        <script src="js/bootstrap/bootstrap-datetimepicker.js"></script>
        <script src="js/bootstrap/navbar.js"></script>
<!--		<script src="js/bootstrapValidator.min.js" type="text/javascript"></script>-->
        <script src="js/datatable/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.jeditable.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.validate.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.dataTables.editable.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.jeditable.time.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.jeditable.datepicker.js" type="text/javascript"></script>
        <script src="js/datatable/js/jquery.jeditable.checkbox.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                var path = location.pathname;
                var name = path.split("/");
                var str = name[2].split(".");        
                
                //url booking
                var isBooking = false;
                var urlBooking = ['BookDetail','Passenger','PassengerDetail','AirTicket','AirTicketDetail','HotelBooking','HotelDetail',
                    'Daytour','DaytourDetail','Other','OtherDetail','Land','LandDetail','Billable'];                                    
                
                if(str[0] === 'BookDetail'){
                    localStorage.setItem("duplicateUserCancel", "no");
                }
                for(var i=0; i<urlBooking.length; i++){                  
                    if(str[0] === urlBooking[i]){
                        var duplicateUser = [{operationTable : "Master" , operationTableId : $("#master-id").val()}];
                        localStorage.setItem("duplicateUser", JSON.stringify(duplicateUser));
                        editOperationUser();
                        isBooking = true;
                        i = urlBooking.length;
                    }
                }

                //Check current page to set null          
                if(!isBooking){
                    //for modal book
                    localStorage.setItem("duplicateUserCancel", null);                                     
                    updateOperationNull();  
                
                }
                                                             
            });
            
            function updateOperationNull() {
                var duplicateUser = localStorage.getItem("duplicateUser");
                duplicateUser = JSON.parse(duplicateUser);    
                
                var operationAction = '';
                var operationTable = duplicateUser[0].operationTable;
                var operationTableId = duplicateUser[0].operationTableId;
                var operationUser = $("#operationUserBooking").val(); 
                var servletName = 'CheckDuplicateUserServlet';
                var servicesName = 'AJAXBean';
                var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&operationTable=' + operationTable +
                        '&operationTableId=' + operationTableId +
                        '&operationAction=' + operationAction +
                        '&operationUser=' + operationUser +
                        '&type=updateOperationNull'
                callAjaxClearDuplicateUser(param);
            }

            function callAjaxClearDuplicateUser(param) {
                var url = 'AJAXServlet';
                try {
                    $.ajax({
                        type: "POST",
                        url: url,
                        cache: false,
                        data: param,
                        success: function(msg) {
                            console.log('update duplicate user success');
                            localStorage.setItem("duplicateUser", null);
                         // window.location = 'APMonitor.smi';
                        }, error: function(msg) {
                            console.log('update duplicate user fail');
                        }
                    });
                } catch (e) {
                    alert(e);
                    console.log('update duplicate user fail');
                }
            }
            
            function editOperationUser() {
                var duplicateUser = localStorage.getItem("duplicateUser");
                duplicateUser = JSON.parse(duplicateUser);    
                
                var operationAction = '';
                var operationTable = duplicateUser[0].operationTable;
                var operationTableId = duplicateUser[0].operationTableId;
                var operationUser = $("#operationUserBooking").val(); 
                var servletName = 'CheckDuplicateUserServlet';
                var servicesName = 'AJAXBean';
                var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&operationTable=' + operationTable +
                        '&operationTableId=' + operationTableId +
                        '&operationAction=' + operationAction +
                        '&operationUser=' + operationUser +
                        '&type=editOperationUser'
                callAjaxEditDuplicateUser(param);
            }
            
            function callAjaxEditDuplicateUser(param) {
                var url = 'AJAXServlet';
                try {
                    $.ajax({
                        type: "POST",
                        url: url,
                        cache: false,
                        data: param,
                        success: function(msg) {
//                            console.log('update duplicate user success');
//                            localStorage.setItem("duplicateUser", null);
                         // window.location = 'APMonitor.smi';
                        }, error: function(msg) {
                            console.log('update duplicate user fail');
                        }
                    });
                } catch (e) {
                    alert(e);
                    console.log('update duplicate user fail');
                }
            }
        </script>
    </body>
</html>