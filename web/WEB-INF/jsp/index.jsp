
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="dataList" value="${requestScope['dataList']}" />

<section class="content-header" >
    <h1>
        Dashboard
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Dash board</a></li>          
    </ol>

    <script type="text/javascript" charset="utf-8">
        $(document).ready(function() {
            var table = $('#example').dataTable({bJQueryUI: true,
                "sPaginationType": "full_numbers",
                "bAutoWidth": false,
                "bFilter": false
            });

            $('#example tbody').on('click', 'tr', function() {
                if ($(this).hasClass('row_selected')) {
                    $(this).removeClass('row_selected');
                    $('#hdGridSelected').val('');
                }
                else {
                    table.$('tr.row_selected').removeClass('row_selected');
                    $(this).addClass('row_selected');
                    $('#hdGridSelected').val($('#example tbody tr.row_selected').attr("id"));
                }
            });
            $('.dataTables_length label').remove();
        });

        $('#myTab a').click(function(e) {
            e.preventDefault()
            $(this).tab('show')
        })
    </script>
</section>
<!--
<div class ="container"  style="padding-top: 15px;"> 
    
    <form role="form">
        <div class="row">
            <div class="col-md-3">
                <div class="form-group">
                    <label for="Country">Ref. No</label>
                    <input type="text"  class="form-control" id="Code" name="Code" >
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label for="fromcity">Leader</label>
                    <input type="text"  class="form-control" id="Code" name="Code" >
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label for="fromCode">Section</label>
                    <select name="fromcity" id="city"  class="form-control">
                        <option value="0"  selected="selected"> </option>
                        <option>My</option>
                        <option>department</option>
                        <option>All</option>
                    </select>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label for="fromName">Status</label>
                    <select name="fromcity" id="city"  class="form-control">             
                        <option value="0"  selected="selected"> </option>
                        <option>Normal</option>
                        <option>refund</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="form-actions pull-right">
                <button type="button" class="btn btn-primary">Search</button>
            </div>
        </div> 
    </form>

    <table id="example" class="display" cellspacing="0" width="100%">
        <thead>
            <tr class="datatable-header">
                <th>Ref No</th>
                <th>Agent</th>
                <th>Leader</th>
                <th>Tel</th>
                <th>Status</th>
                <th>Detail</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>022-3831</td>
                <td>WLK</td>
                <td>Mr. TAKEYAMA TORU</td>
                <td>02-1234567</td>
                <td>Refund</td>  
                <td><center><span class="glyphicon glyphicon-th-list" data-toggle="modal" data-target="#DetailModal"> </span></center></td>
        </tr>
        <tr>
            <td>022-3827</td>
            <td>WLK</td>
            <td>Mr. SUZUKI TORU</td>
            <td>02-3434342</td>
            <td>Normal</td>  
            <td><center><span class="glyphicon glyphicon-th-list" data-toggle="modal" data-target="#DetailModal"> </span></center></td>
        </tr>
        <tr>
            <td>022-3822</td>
            <td>WLK</td>
            <td>Mr. SAITO TORU</td>
            <td>02-7586454</td>    
            <td>Print</td>  
            <td><center><span class="glyphicon glyphicon-th-list" data-toggle="modal" data-target="#DetailModal"> </span></center></td>
        </tr>

        </tbody>
    </table>

    <div class="row">
        <form class="form-horizontal" role="form">
            <div class="col-md-3 ">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-5 control-label">Department</label>
                    <div class="col-sm-7">
                        <select name="fromcity" id="city"  class="form-control">             
                            <option value="0"  selected="selected"> </option>
                            <option>Air ticket</option>
                            <option>Hotel</option>
                            <option>Group</option>
                            <option>Day tour</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="col-md-3 ">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-5 control-label">open reason</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="inputEmail3" >
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-5 control-label">New status</label>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" id="inputPassword3" >
                    </div>
                </div>
            </div>
            <div class="col-md-3"> 
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success">Open</button>
                        <button type="submit" class="btn btn-primary">New</button>
                    </div>
                </div>
            </div>
        </form>
    </div>


</div>

<div class="modal fade" id="DetailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Reference Detail</h4>
            </div>
            <div class="modal-body">
                <form  method="post" id="MasterDetail" class="form-horizontal"  role="form">
                    <div class="row">
                        <div class="col-md-6">

                            <div class="form-group">
                                <label for="RefNo" class="col-sm-4 control-label" >Ref. No</label>
                                <div class="col-sm-6"> 
                                    <input type="text" class="form-control" id="RefNo" name="RefNo" readonly>
                                    
                                </div>
                            </div>
                        </div>    
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="Status" class="col-sm-4 control-label" >Status</label>
                                <div class="col-sm-4">  
                                    <input type="text" class="form-control" id="Status" name="Status" >
                                </div>
                            </div>   
                        </div>    
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="CreateD" class="col-sm-4 control-label" >Create Date</label>
                                <div class="col-sm-6"> 
                                    <input type="text" class="form-control" id="CreateD" name="CreateD" >
                                </div>
                            </div>
                        </div>    
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="CreateB" class="col-sm-4 control-label" >Create By</label>
                                <div class="col-sm-4">  
                                    <input type="text" class="form-control" id="CreateB" name="CreateB" >
                                </div>
                            </div>   
                        </div>    
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="RevisedD" class="col-sm-4 control-label" >Revised Date</label>
                                <div class="col-sm-6"> 
                                    <input type="text" class="form-control" id="RevisedD" name="RevisedD" >
                                </div>
                            </div>
                        </div>    
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="RevisedB" class="col-sm-4 control-label" >Revised By</label>
                                <div class="col-sm-4">  
                                    <input type="text" class="form-control" id="CreateB" name="CreateB" >
                                </div>
                            </div>   
                        </div>    
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="AgentC" class="col-sm-2 control-label" >Agent</label>
                                <div class="col-sm-2"> 
                                    <input type="text" class="form-control" id="AgentC" name="AgentC" >
                                </div>
                                <div class="col-sm-3">  
                                    <input type="text" class="form-control" id="AgentN" name="AgentN" >
                                </div>
                                <label for="Tel" class="col-sm-1 " >Tel</label>
                                <div class="col-sm-2"> 
                                    <input type="text" class="form-control" id="TelA" name="TelA" >
                                </div>
                            </div>
                        </div> 
                        <!--
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="AgentN" class="col-sm-3 control-label" >Agent Name</label>
                                <div class="col-sm-8">  
                                    <input type="text" class="form-control" id="AgentN" name="AgentN" >
                                </div>
                            </div>   
                        </div>  
                  
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="FamilyC" class="col-sm-2 control-label" >Falimy Leader</label>
                                <div class="col-sm-2"> 
                                    <input type="text" class="form-control" id="AgentC" name="AgentC" >
                                </div>
                                <div class="col-sm-3">  
                                    <input type="text" class="form-control" id="AgentN" name="AgentN" >
                                </div>
                                <label for="Tel" class="col-sm-1 " >Tel</label>
                                <div class="col-sm-2"> 
                                    <input type="text" class="form-control" id="TelC" name="TelC" >
                                </div>
                            </div>
                        </div>   
               
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="AgentN" class="col-sm-4 control-label" >Passenger</label>
                                <div class="col-sm-8">  
                                    <table class="display" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th> Adult</th>
                                                <th> Child</th>
                                                <th> infant</th>
                                                <th> Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><center> 1 </center></td>
                                        <td><center> 0 </center></td>
                                        <td><center> 0 </center></td>
                                        <td><center> 1 </center></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>   

                        </div>    
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="FamilyN" class="col-sm-3 control-label" >Package</label>
                                <div class="col-sm-8">  
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"> 
                                        </label>
                                    </div>
                                </div>
                            </div>   
                        </div>

                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="Detail" class="col-sm-2 control-label" >Detail</label>
                            <div class="col-md-8" style="padding-left: 25px">
                                <ul id="myTab" class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active"><a href="#Air" id="home-tab" role="tab" data-toggle="tab" aria-controls="Air" aria-expanded="true">Air Ticket</a></li>
                                    <li role="presentation" class=""><a href="#Hotel" role="tab" id="Hotel-tab" data-toggle="tab" aria-controls="Hotel" aria-expanded="false">Hotel</a></li>
                                    <li role="presentation" class=""><a href="#Group" role="tab" id="Group-tab" data-toggle="tab" aria-controls="Group" aria-expanded="false">Group</a></li>
                                    <li role="presentation" class=""><a href="#tours" role="tab" id="tours-tab" data-toggle="tab" aria-controls="tours" aria-expanded="false">Day tours</a></li>
                                    <li role="presentation" class=""><a href="#Others" role="tab" id="Others-tab" data-toggle="tab" aria-controls="Others" aria-expanded="false">Others</a></li>
                                    <li role="presentation" class=""><a href="#Passenger" role="tab" id="Passenger-tab" data-toggle="tab" aria-controls="Passenger" aria-expanded="false">Passenger</a></li>
                                </ul>

                                <div id="myTabContent" class="tab-content">
                                    <div role="tabpanel" class="tab-pane fade active in" id="Air" aria-labelledby="Air-tab">
       
                                            <div class="row" style="padding-top: 10px">
                                                <div class="form-group">
                                                    <label for="PNR" class="col-sm-2 control-label" >PNR</label>
                                                    <div class="col-sm-3">  
                                                        <input type="text" class="form-control" id="PNR" name="PNR" >
                                                    </div>
                                                </div>   
                                            </div>
                                            <div class="row">
                                                <div class="form-group">
                                                    <label for="Ticket" class="col-sm-2 control-label" >Direction</label>
                                                    <div class="col-sm-8">  
                                                        <input type="text" class="form-control" id="Ticket" name="Ticket" >
                                                    </div>
                                                </div>   
                                            </div>
                                  
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="Hotel" aria-labelledby="Hotel-tab">
                                        <div class="row" style="padding-top: 10px">
                                            <div class="col-md-6" >
                                                <div class="form-group">
                                                    <label for="Hotel" class="col-sm-4 control-label" >Hotel</label>
                                                    <div class="col-sm-8">  
                                                        <input type="text" class="form-control" id="Hotel" name="Hotel" >
                                                    </div>
                                                </div>   
                                            </div>
                                        </div>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="Group" aria-labelledby="Group-tab">
                                        <div class="row" style="padding-top: 10px">
                                            <div class="col-md-6" >
                                                <div class="form-group">
                                                    <label for="Group" class="col-sm-4 control-label" >Group</label>
                                                    <div class="col-sm-8">  
                                                        <input type="text" class="form-control" id="Group" name="Group" >
                                                    </div>
                                                </div>   
                                            </div>
                                        </div>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="tours" aria-labelledby="tours-tab">
                                        <div class="row" style="padding-top: 10px">
                                            <div class="col-md-6" >
                                                <div class="form-group">
                                                    <label for="tours" class="col-sm-4 control-label" >tours</label>
                                                    <div class="col-sm-8">  
                                                        <input type="text" class="form-control" id="tours" name="tours" >
                                                    </div>
                                                </div>   
                                            </div>
                                        </div>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="Others" aria-labelledby="Others-tab">
                                        <div class="row" style="padding-top: 10px">
                                            <div class="col-md-6" >
                                                <div class="form-group">
                                                    <label for="Others" class="col-sm-4 control-label" >Others</label>
                                                    <div class="col-sm-8">  
                                                        <input type="text" class="form-control" id="Others" name="Others" >
                                                    </div>
                                                </div>   
                                            </div>
                                        </div>
                                    </div>
                                    <div role="tabpanel" class="tab-pane fade" id="Passenger" aria-labelledby="Passenger-tab">
                                        <div class="row" style="padding-top: 10px">
                                            <div class="col-md-6" >
                                                <div class="form-group">
                                                    <label for="Passenger" class="col-sm-4 control-label" >Passenger</label>
                                                    <div class="col-sm-8">  
                                                        <input type="text" class="form-control" id="Passenger" name="Passenger" >
                                                    </div>
                                                </div>   
                                            </div>
                                        </div>
                                    </div>
                                </div>                           
                            </div>
                        </div>   


                    </div>

                    <input type="hidden" id="CityID" name="CityID" >
                    <input type="hidden" id="actionIUP" name="action">

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" onclick="save()" class="btn btn-primary">Save</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

      -->