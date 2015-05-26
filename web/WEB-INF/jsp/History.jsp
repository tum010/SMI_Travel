<%-- 
    Document   : History
    Created on : Dec 19, 2014, 1:55:09 PM
    Author     : sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dataList" value="${requestScope['Airline_List']}" />
<c:set var="booking_size" value="${requestScope['BookingSize']}" />

<c:set var="master" value="${requestScope['Master']}" />


<input type="hidden" value="${param.referenceNo}" id="getUrl">
<input type="hidden" value="${master.createDate}" id="master-createDate">
<input type="hidden" value="${master.createBy}" id="master-createBy">


<section class="content-header" >
    <h1>
        Booking - History
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Booking</a></li>          
        <li class="active"><a href="#">History</a></li>
    </ol>
</section>

<div class ="container"  style="padding-top: 15px;" ng-app=""> 
    <div class="row">
        <!-- side bar -->
        <div class="col-sm-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
            <input type="hidden" value="${master.customer.MInitialname.name}" id="initialname_tmp">
            <input type="hidden" value="${master.customer.firstName}" id="firstname_tmp">
            <input type="hidden" value="${master.customer.lastName}" id="lastname_tmp">  
            <div ng-include="'WebContent/Book/BookMenu.html'"></div>
                <input hidden="" value="${booking_size[0]}" id="input-airticket_size">
              <input hidden="" value="${booking_size[1]}" id="input-hotel_size">
              <input hidden="" value="${booking_size[2]}" id="input-other_size">
              <input hidden="" value="${booking_size[3]}" id="input-land_size">
              <input hidden="" value="${booking_size[4]}" id="input-passenger_size">
              <input hidden="" value="${booking_size[5]}" id="input-billable_size">

        </div>

        <!-- main page -->
        <div class="col-sm-10">
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>

            <div class="row" style="padding-left: 15px">  
                <div class="col-md-6">
                    <h4><b>History</b></h4>
                </div>
                <div class="col-md-6 text-right">
                    <a id="ButtonAdd" href="HistoryDetail.smi?referenceNo=${param.referenceNo}" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Add</a>
                </div>

            </div>
            <hr/>

            <table class="display" id="HistoryTable">
                <thead class="datatable-header">
                    <tr>
                        <th>Date</th><th>Staff</th><th>Recall</th><th>Subject</th><th>Dule Date</th><th>Deperment</th><th>Warn Message</th><th>Status</th><th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>18-11-14,21:24</td>
                        <td>BT</td>
                        <td>Self Revieew an Click</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>OK</td>
                        <td class="text-center">
                            <a id="ButtonHistoryDetail" href="HistoryDetail.smi?referenceNo=${param.referenceNo}&action=edit&id=1">
                                <span id="SpanHistoryDetail" class="glyphicon glyphicon-list"></span>
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>



        </div>
    </div>
</div>
                    
<c:if test="${! empty param.result}">
    <c:if test="${param.result =='1'}">        
        <script language="javascript">
            alert("save successful");
        </script>
    </c:if>
    <c:if test="${param.result =='0'}">        
        <script language="javascript">
            alert("save unsuccessful");
        </script>
    </c:if>

</c:if>

<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        var table = $('#HistoryTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });

        $('#HistoryTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
            }
        });
        // $('.dataTables_length label').remove();


    });

</script>