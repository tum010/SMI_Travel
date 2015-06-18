<%-- 
    Document   : HistoryDetail
    Created on : Dec 27, 2014, 12:10:06 PM
    Author     : sumeta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="HistoryList" value="${requestScope['HistoryList']}" />
<c:set var="booking_size" value="${requestScope['BookingSize']}" />
<c:set var="staffList" value="${requestScope['StaffList']}" />
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
            <div ng-include="'WebContent/Book/BookMenu.html'"></div>
            <input id="now-status" type="hidden" value="${master.getMBookingstatus().getName()}"/>

        </div>

        <!-- main page -->
        <div class="col-sm-10">
            <div ng-include="'WebContent/Book/BookNavbar.html'"></div>
            <input hidden="" value="${booking_size[0]}" id="input-airticket_size">
            <input hidden="" value="${booking_size[1]}" id="input-hotel_size">
            <input hidden="" value="${booking_size[2]}" id="input-other_size">
            <input hidden="" value="${booking_size[3]}" id="input-land_size">
            <input hidden="" value="${booking_size[4]}" id="input-passenger_size">
            <input hidden="" value="${booking_size[5]}" id="input-billable_size">

            <div class="row" style="padding-left: 15px">  
                <div class="col-md-6">
                    <h4><b>History</b></h4>
                </div>
                <div class="col-md-6 text-right">
                    <a class="btn btn-primary" href="History.smi?referenceNo=${param.referenceNo}"><span class="glyphicon glyphicon-arrow-left"></span> Back</a>
                </div>

            </div>
            <hr/>
            <!--form-->
            <form id="HistoryForm-xxx" action="HistoryDetail.smi" method="post">
                <!--History Panel-->
                <div class="panel panel-default" style="margin-top: 10px">
                    <div class="panel-heading">
                        <h3 class="panel-title">History</h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <label class="col-sm-4">Date</label>
                                <div class="col-sm-8">  

                                    <div class='input-group date'>
                                        <input type='text' class="form-control" id="historyDate"
                                               data-date-format="YYYY-MM-DD"/>
                                        <span class="input-group-addon spandate">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-5">
                                <label  class="col-sm-2">Staff</label>
                                <div class="col-sm-4">  
                                    <div class="form-group">
                                        <div class="input-group" id="PassengerInput">
                                            <input type="text" class="form-control" id="passengerId" name="id">
                                            <span class="input-group-addon"   data-toggle="modal" data-target="#StaffModal"><span class="glyphicon-search glyphicon"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">  
                                    <input type="text" id="passengerName"  name="passengerName" class="form-control">
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <label for="Land" class="col-sm-4 control-label">Recall</label>
                                <div class="col-sm-8">  
                                    <select class="form-control"></select>
                                </div>
                            </div>
                        </div> 
                        <div class="row">
                            <label class="col-sm-1 control-label">Description</label>
                            <div class="col-sm-11">  
                                <input type="text" class="form-control" ng-model="Description">
                            </div>
                        </div> 
                    </div>
                </div>
                <!--Subject Panel-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Subject</h3>
                    </div>
                    <div class="panel-body">

                        <div class="row form-group">

                            <div class="col-sm-8">
                                <label for="Land" class="col-sm-2 control-label">Subject</label>
                                <div class="col-sm-10">  
                                    <input type="text" class="form-control">
                                </div>
                            </div>


                            <div class="col-sm-4">
                                <label  class="col-sm-4 control-label">Due Date</label>
                                <div class="col-sm-8">  

                                    <div class='input-group date'>
                                        <input type='text'id="dueDate" class="form-control" data-date-format="YYYY-MM-DD"/>
                                        <span class="input-group-addon spandate">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div> 
                        <div class="row form-group">
                            <div class="col-sm-8">
                                <label  class="col-sm-2 control-label">Department</label>
                                <div class="col-sm-10">  
                                    <input type="text" class="form-control">
                                </div></div><div class="col-sm-4 text-right"> 
                                <a class="btn btn-success btn-sm">Success</a>
                                <a class="btn btn-warning btn-sm">Warning</a>
                                <a class="btn btn-info btn-sm">PostPone</a>
                            </div>
                        </div> 
                        <div class="row">
                            <div class="col-sm-12">

                                <label for="Land" class="col-sm-1 control-label">Message</label>
                                <div class="col-sm-11">  
                                    <textarea name="Message" class="form-control" rows="3"></textarea>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <!--button save-->
                <div class="text-center">
                    <c:if test="${param.action == 'add'}">
                        <input name="action" value="insert"type="hidden">
                    </c:if>
                    <c:if test="${param.action == 'edit'}">
                        <input name="action" value="update"type="hidden">
                    </c:if>
                    <input name="id" value="${param.id}"type="hidden">
                    <input name="referenceNo" value="${param.referenceNo}"type="hidden">
                    <button class="btn btn-success" type="submit"><span class="fa fa-save"></span> Save</button>
                </div>
            </form>

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


<!--Modal  Staff-->
<div class="modal fade" id="StaffModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Staff</h4>
            </div>
            <div class="modal-body">
                <!--Owner List Table-->
                <table class="display" id="OwnerTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                            <th>Position</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                       var staff = [];
                    </script>
                        <c:forEach var="staff" items="${staffList}">
                            <tr>
                                <td class="staff-id hidden">${staff.id}</td>
                                <td class="staff-username">${staff.username}</td>
                                <td class="staff-name">${staff.name}</td>
                                <td class="staff-position">${staff.position}</td>
                            </tr>
                        <script>
                            staff.push({id: "${staff.id}", username: "${staff.username}", name: "${staff.name}", position: "${staff.position}"});
                        </script>
                        </c:forEach>

                    </tbody>

                </table>
                <!--Script Owner List Table-->
                <script>
                    $(document).ready(function () {
                        var staffArray = [];
                        $.each(staff, function (key, value) {
                            staffArray.push(value.username);
                            if ( !(value.name in staffArray) ){
                               staffArray.push(value.name);
                            } 
                        });

                        $("#passengerId").autocomplete({
                            source: staffArray,
                            close:function( event, ui ) {
                               $("#passengerId").trigger('keyup');
                            }
                        });
                        $("#passengerId").keyup(function () {
                            var position = $(this).offset();
                            $(".ui-widget").css("top", position.top + 30);
                            $(".ui-widget").css("left", position.left);
                            var username = this.value.toUpperCase();
                            var name = this.value;
                            $("#passengerName").val(null);
                            $.each(staff, function (key, value) {
                                if (value.username.toUpperCase() === username) {
                                    $("#passengerName").val(value.name);
                                }
                                if(name === value.name){
                                    $("#passengerId").val(value.username);
                                    username = $("#passengerId").val().toUpperCase();
                                }
                               
                            });
                        });
                        
                        
                        
                        $("#OwnerTable tr").on('click', function () {
                            staff_id = $(this).find(".staff-id").text();
                            staff_username = $(this).find(".staff-username").text();
                            staff_name = $(this).find(".staff-name").text();
                            $("#staff_id").val(staff_id);
                            $("#staff_username").val(staff_username);
                            $("#staff_name").val(staff_name);
                            console.log(staff_id);
                            $("#OwnerModal").modal('hide');
                        });
                        // Owner Table
                        var OwnerTable = $('#OwnerTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                        $('#OwnerTable tbody').on('click', 'tr', function () {
                            //$('.collapse').collapse('show');
                            if ($(this).hasClass('row_selected')) {
                                $(this).removeClass('row_selected');
                            }
                            else {
                                OwnerTable.$('tr.row_selected').removeClass('row_selected');
                                $(this).addClass('row_selected');
                            }

                        });
                    });
                </script>

            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<script>

    $(document).ready(function () {


        // datetimepicker
        $('.date').datetimepicker();
        $('.spandate').click(function () {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css({
                top: position.top + 32,
                left: position.left - 100
            });

        });


        $('#HistoryForm').bootstrapValidator({
            container: 'tooltip',
            excluded: [':disabled'],
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                HistoryDate: {
                    validators: {
                        notEmpty: {
                            message: 'The Name is required'
                        }
                    }
                },
                UserName: {
                    validators: {
                        notEmpty: {
                            message: 'The Username is required'
                        }
                    }
                },
                Password: {
                    validators: {
                        notEmpty: {
                            message: 'The Password is required'
                        }
                    }
                },
                Position: {
                    validators: {
                        notEmpty: {
                            message: 'The Position is required'
                        }
                    }
                },
                Tel: {
                    validators: {
                        digits: {
                            message: 'The Tel is required'
                        }
                    }
                },
                Status: {
                    validators: {
                        notEmpty: {
                            message: 'The Status is required'
                        }
                    }
                }

            }
        }).on('success.field.bv', function (e, data) {
            if (data.bv.isValid()) {
                data.bv.disableSubmitButtons(false);
            }
        });


    });


</script>