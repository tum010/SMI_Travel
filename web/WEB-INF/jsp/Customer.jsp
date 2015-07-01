<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Customer.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="content-header" >
    <h1>
        Customer MA
 
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li class="active"><a href="#">Customer MA</a></li>
    </ol>


    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
            var table = $('#MasterCustomer').dataTable({bJQueryUI: true,
                "sPaginationType": "full_numbers",
                "bAutoWidth": false,
                "bFilter": false
            });

            $('#MasterCustomer tbody').on('click', 'tr', function() {
                if ($(this).hasClass('row_selected')) {
                    $(this).removeClass('row_selected');
                    $('#hdGridSelected').val('');
                }
                else {
                    table.$('tr.row_selected').removeClass('row_selected');
                    $(this).addClass('row_selected');
                    $('#hdGridSelected').val($('#MasterCustomer tbody tr.row_selected').attr("id"));
                }
            });

            $('.dataTables_length label').remove();

        });
    </script>
</section>
