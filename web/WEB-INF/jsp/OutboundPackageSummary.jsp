<%-- 
    Document   : OutboundPackageSummary
    Created on : Nov 23, 2015, 5:39:50 PM
    Author     : Kanokporn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/selectize.js"></script>
<link href="css/selectize.bootstrap3.css" rel="stylesheet">

<c:set var="userList" value="${requestScope['listSale']}" />
<c:set var="listStatus" value="${requestScope['listStatus']}" />
<c:set var="listCity" value="${requestScope['listCity']}" />
<c:set var="package_list" value="${requestScope['listPackage']}" />
<c:set var="listPayby" value="${requestScope['listPayby']}" />
<c:set var="listBank" value="${requestScope['listBank']}" />

<section class="content-header"  >
    <h4>
        <b>Report : Outbound Package Summary </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Outbound Package Summary</a></li>
    </ol>
</section>
<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/OutboundSummaryMenu.html'"></div>
            </div>
            <div class="form-group">
                <div class="col-md-6">
                    <h3>Package Summary</h3>
                </div>
            </div>
            <div class="col-md-10" >
                <form  method="post" id="PackageSummaryForm" name="PackageSummaryForm" role="form">
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >City</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectCity" id="SelectCity"  class="form-control selectize">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listCity}" >
                                                <c:if test="}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Package <font style="color: red">*</font></label>
                                <div class="col-md-3 form-group" >  
                                    <div class="input-group">
                                        <input name="InputPackageId" id="InputPackageId" type="hidden" class="form-control" value="" />
                                        <input type="text" class="form-control" id="InputPackageCode" name="InputPackageCode" value="" />
                                        <span class="input-group-addon" id="package_modal"  data-toggle="modal" data-target="#PackageModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3" id="agentnamepanel">
                                    <input name="InputPackageName" id="InputPackageName" type="text" class="form-control" value="" readonly="" />
                                </div>
                            </div> 
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="fromdatepanel">
                                <label class="col-md-6 control-label text-right">From<font style="color: red">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date' id='DateFrom'>
                                            <input type='text' id="fromdate" name="fromdate" class="form-control datemask" data-date-format="YYYY-MM-DD"/>
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group" id="todatepanel">
                                <label class="col-md-6 control-label text-right">To<font style="color: red">*</font></label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <div class='input-group date' id='DateTo'>
                                            <input type='text' id="todate" name="todate"  class="form-control datemask" data-date-format="YYYY-MM-DD" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>                                   
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Sale By</label>
                                <div class="col-md-3 form-group">  
                                    <div class="input-group">
                                        <input type="hidden" class="form-control" id="salebyId" name="salebyId" value=""/>
                                        <input type="text" class="form-control" id="salebyUser" name="salebyUser" value="" />
                                        <span class="input-group-addon" id="saleby_modal"  data-toggle="modal" data-target="#SaleByModal">
                                            <span class="glyphicon-search glyphicon"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="salebyName" name="salebyName" value="" readonly="">
                                </div>
                            </div>   
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Pay By</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectPayby" id="SelectPayby" class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listPayby}" >
                                                <c:if test="}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>                                   
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Bank Transfer</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectBank" id="SelectBank" class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listBank}" >
                                                <c:if test="}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" >Status</label>
                                <div class="col-md-5">  
                                    <div class="form-group">
                                        <select name="SelectStatus" id="SelectStatus" class="form-control">
                                            <option value=""  selected="selected">-- ALL --</option>
                                            <c:set var="select" value="" />
                                            <c:forEach var="term" items="${listStatus}" >
                                                <c:if test="}">
                                                    <c:set var="select" value="selected" />
                                                </c:if>
                                                <option value="${term.id}" ${select}>${term.name}</option>  
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>       
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="col-md-6 control-label text-right" for="rept"></label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <button type=button  id="printbutton" name="printbutton" onclick="printPackageSummary();" id="printbutton" class="btn btn-success"><span class="glyphicon glyphicon-print"></span> Print</button>
                                    </div>
                                </div>   
                            </div>
                        </div>
                    </div>
                </form>                   
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="SaleByModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Sale By</h4>
            </div>
            <div class="modal-body">
                <!--Agent List Table-->
                <table class="display" id="SaleByTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th class="hidden">ID</th>
                            <th>User</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <tbody>
                    <script>
                        saleby = [];
                    </script>
                    <c:forEach var="table" items="${userList}">
                        <tr>
                            <td class="saleby-id hidden">${table.id}</td>
                            <td class="saleby-user">${table.username}</td>
                            <td class="saleby-name">${table.name}</td>
                        </tr>
                        <script>
                            saleby.push({id: "${table.id}", username: "${table.username}", name: "${table.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div class="text-right">
                    <button id="SaleByModalClose" type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<div class="modal fade" id="PackageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Package</h4>
            </div>
            <div class="modal-body">
                <table class="display" id="PackageTable">
                    <thead class="datatable-header">
                        <tr>
                            <th class="hidden">ID</th>
                            <th style="width:20%">Code</th>
                            <th>Name</th>
                        </tr>
                    </thead>
                    <script>
                        packageCode = [];
                    </script>
                    <tbody>
                        <c:forEach var="pac" items="${package_list}">
                            <tr>
                                <td class="package-id hidden">${pac.id}</td>
                                <td class="package-code">${pac.code}</td>
                                <td class="package-name">${pac.name}</td>
                            </tr>
                        <script>
                            packageCode.push({id: "${pac.id}", code: "${pac.code}", name: "${pac.name}"});
                        </script>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Script Package List table-->
            <script>
                $(document).ready(function () {
                    
                    $("#PackageTable tr").on('click', function () {//winit
                        var package_id = $(this).find(".package-id").html();
                        var package_code = $(this).find(".package-code").html();
                        var package_name = $(this).find(".package-name").html();
                        $("#InputPackageId").val(package_id);
                        $("#InputPackageCode").val(package_code);
                        $("#InputPackageName").val(package_name);
                        $("#PackageModal").modal('hide');
                    });

                    // productTable
                    var PackageTable = $('#PackageTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": true,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });

                    $('#PackageTable tbody').on('click', 'tr', function () {
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                        }
                        else {
                            PackageTable.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                        }
                    });

                    var packagetemp = [];
                    $.each(packageCode, function (key, value) {
                        packagetemp.push(value.code);
                        packagetemp.push(value.name);
                    });

                    $("#InputPackageCode").autocomplete({
                        source: packagetemp,
                        close:function( event, ui ) {
                           $("#InputPackageCode").trigger('keyup');
                        }
                    });

                    $("#InputPackageCode").on('keyup',function(){
                        var position = $(this).offset();
                        $(".ui-widget").css("top", position.top + 30);
                        $(".ui-widget").css("left", position.left);
                        var code = this.value.toUpperCase();
                        var name = this.value.toUpperCase();
                       // console.log("Name :"+ name);
                        $("#InputPackageId,#InputPackageName").val(null);
                        $.each(packageCode, function (key, value) {
                            if (value.code.toUpperCase() === code ) {  
                                $("#InputPackageId").val(value.id);
                                $("#InputPackageCode").val(value.code);
                                $("#InputPackageName").val(value.name);
                            }
                            else if(value.name.toUpperCase() === name){
                                $("#InputPackageCode").val(value.code);
                                $("#InputPackageId").val(value.id);
                                $("#InputPackageName").val(value.name);
                            }
                        }); 
                    }); 
                });        
            </script>
            <div class="modal-footer">
                <div class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<script type="text/javascript">
$(document).ready(function() {
    $('.date').datetimepicker();
    $('.datemask').mask('0000-00-00', {reverse: true});
    $('.spandate').click(function() {
           var position = $(this).offset();
           console.log("positon :" + position.top);
           $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

       });
       
     Selectize.define( 'clear_selection', function ( options ) {
        var self = this;
        self.plugins.settings.dropdown_header = {
            title: 'Clear Selection'
        };
        this.require( 'dropdown_header' );
        self.setup = (function () {
            var original = self.setup;
            return function () {
                original.apply( this, arguments );
                this.$dropdown.on( 'mousedown', '.selectize-dropdown-header', function ( e ) {
                    self.setValue( '' );
                    self.close();
                    self.blur();
                    return false;
                });
            };
        })();
    });

    var City = "#SelectCity";
    $(City).selectize({
        removeItem: '',
        sortField: 'text' ,
        create: false ,
        dropdownParent: 'body',
        plugins: {
            'clear_selection': {}
        }
    });
    
    //Sale By Auto Complete
    $("#SaleByTable tr").on('click', function () {
        var saleby_id = $(this).find(".saleby-id").text();
        var saleby_user = $(this).find(".saleby-user").text();
        var saleby_name = $(this).find(".saleby-name").text();
        $("#salebyId").val(saleby_id);
        $("#salebyUser").val(saleby_user);
        $("#salebyName").val(saleby_name);
        $("#SaleByModal").modal('hide');
    });
        
    var salebyuser = [];
    $.each(saleby, function (key, value) {
        salebyuser.push(value.username);
        salebyuser.push(value.name);
    });

    $("#salebyUser").autocomplete({
        source: salebyuser,
        close:function( event, ui ) {
           $("#salebyUser").trigger('keyup');
        }
    });
        
    $("#salebyUser").on('keyup',function(){
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var username = this.value.toUpperCase();
        var name = this.value.toUpperCase();
       // console.log("Name :"+ name);
        $("#salebyId,#salebyName").val(null);
        $.each(saleby, function (key, value) {
            if (value.username.toUpperCase() === username ) {  
                $("#salebyId").val(value.id);
                $("#salebyUser").val(value.username);
                $("#salebyName").val(value.name);
            }
            else if(value.name.toUpperCase() === name){
                $("#salebyUser").val(value.username);
                $("#salebyId").val(value.id);
                $("#salebyName").val(value.name);
            }
        }); 
    }); 
    
    $('#SaleByTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    $('#SaleByTable tbody').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });   
    
    var from = setValueFromDate();
    var to = setValueToDate();
    $("#fromdate").val(from);
    $("#todate").val(to);

    $("#PackageSummaryForm")
        .bootstrapValidator({
            framework: 'bootstrap',
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: { 
                InputPackageCode: {
                    trigger: 'focus keyup change',
                        validators: {
                            notEmpty: {
                                message: 'Package is required'
                            }
                        }
                },
                InputPackageName: {
                    trigger: 'focus keyup change',
                        validators: {
                            notEmpty: {
                                message: 'Package is required'
                            }
                        }
                },
                fromdate: {
                    trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                max: 'todate',
                                message: 'The Date From is not a valid'
                            },notEmpty: {
                                message: 'The Date From is required'
                            }
                        }
                },
                todate: {
                    trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                min: 'fromdate',
                                message: 'The Date To is not a valid'
                            },notEmpty: {
                                message: 'The Date From is required'
                            }
                        }
                }
            }
        }).on('success.field.fv', function (e, data) {
            if (data.field === 'fromdate' && data.fv.isValidField('todate') === false) {
                data.fv.revalidateField('todate');
            }
            if (data.field === 'todate' && data.fv.isValidField('fromdate') === false) {
                data.fv.revalidateField('fromdate');
            }
        });

    $('#DateFrom').datetimepicker().on('dp.change', function (e) {
        $('#PackageSummaryForm').bootstrapValidator('revalidateField', 'fromdate');
        $('#PackageSummaryForm').bootstrapValidator('revalidateField', 'todate');
        var fromdate = document.getElementById("fromdate").value;
        var todate = document.getElementById("todate").value;
        if(((fromdate !== '') && (todate !== '')) && fromdate < todate){
            $("#printbutton").removeClass("disabled");
        }else if((((fromdate !== '') && (todate !== '')) && fromdate === todate)) {
            $("#printbutton").removeClass("disabled");
        }else{
            $("#printbutton").addClass("disabled");
        }
    });
    $('#DateTo').datetimepicker().on('dp.change', function (e) {
        $('#PackageSummaryForm').bootstrapValidator('revalidateField', 'fromdate');
        $('#PackageSummaryForm').bootstrapValidator('revalidateField', 'todate');
        var fromdate = document.getElementById("fromdate").value;
        var todate = document.getElementById("todate").value;
        if(((fromdate !== '') && (todate !== '')) && fromdate < todate){
            $("#printbutton").removeClass("disabled");
        }else if((((fromdate !== '') && (todate !== '')) && fromdate === todate)) {
            $("#printbutton").removeClass("disabled");
        }else{
            $("#printbutton").addClass("disabled");
        }
    });  
});


function printPackageSummary(){
        var form = $("#fromdate").val();
        var to = $("#todate").val();
        var saleby = $("#salebyId").val();
        var payby = $("#SelectPayby").val();
        var banktran = $("#SelectBank").val();
        var status = $("#SelectStatus").val();
        var city = $("#SelectCity").val();
        var package = $("#InputPackageId").val();
        
        if(((form !== '') && (to !== '')) && form < to && (package !== '')){
            $("#printbutton").removeClass("disabled");
            window.open("Excel.smi?name=OutboundPackageSummary"
                        + "&fromdate=" + form 
                        + "&todate=" + to 
                        + "&saleby=" + saleby 
                        + "&payby=" + payby 
                        + "&banktran=" + banktran 
                        + "&status=" + status 
                        + "&city=" + city 
                        + "&package=" + package 
                        );
        }else if((((form !== '') && (to !== '')) && form === to) && (package !== '')) {
            $("#printbutton").removeClass("disabled");
            window.open("Excel.smi?name=OutboundPackageSummary"
                        + "&fromdate=" + form 
                        + "&todate=" + to 
                        + "&saleby=" + saleby 
                        + "&payby=" + payby 
                        + "&banktran=" + banktran 
                        + "&status=" + status 
                        + "&city=" + city 
                        + "&package=" + package 
                        );
        }else {
            $('#PackageSummaryForm').bootstrapValidator('revalidateField', 'fromdate');
            $('#PackageSummaryForm').bootstrapValidator('revalidateField', 'todate');
            $('#PackageSummaryForm').bootstrapValidator('revalidateField', 'InputPackageCode');
            $("#printbutton").addClass("disabled");
        }

    }
</script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
    </div>
</div>