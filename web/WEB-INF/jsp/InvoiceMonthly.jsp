<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="listClient" value="${requestScope['listClient']}" />
<c:set var="listAccno" value="${requestScope['listAccno']}" />
<c:set var="listStaff" value="${requestScope['listStaff']}" />
<section class="content-header"  >
    <h4>
        <b>Report : Invoice monthly report </b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Invoice monthly</a></li>
    </ol>
</section>

<div class="container" style="padding-top: 30px;" ng-app="">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <div ng-include="'WebContent/Report/InvoiceMonthlyMenu.html'"></div>
            </div>
            <div class="form-group">
                <div class="col-md-6">
                    <h3>List Invoice monthly</h3>
                </div>
            </div>
            <div class="col-md-12" ><br></div>
            <div class="col-md-12" >
                <form role="form" id="InvoiceMonthlyFrom" method="post" class="form-horizontal" onsubmit="printInvoiceMonthly();">                   
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-1" style="width: 120px">
                                <label for="client" class="control-label">Client</label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 130px">
                                <div class="form-group">
                                    <div class='input-group' >
                                        <input type='text' id="clientCode" name="clientCode"  class="form-control" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-search" data-toggle="modal" data-target="#ClientModal"></span></span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-1" style="width: 200px">
                                <input type='text' id="clientName" name="clientName"  class="form-control" readonly/>
                            </div>
                            <div class="col-sm-1"></div>
                            <div class="col-sm-1">
                                <label for="client" class="control-label">ATTN</label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 250px">
                                <input type='text' id="billingAttn" name="billingAttn"  class="form-control" value="${requestScope['billingAttn']}"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-1" style="width: 120px">
                                <label for="client" class="control-label">Vat Type</label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 200px">
                                <div class="form-group">
                                    <select id="vatType" name="vatType"  class="form-control">
                                        <option value="">--select--</option>
                                        <option value="V">Vat</option>
                                        <option value="N">No Vat</option>
                                        <option value="T">Temp</option>
                                        <option value="A">Ticket</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-1" style="width: 233px"></div>
                            <div class="col-sm-1">
                                <label for="client" class="control-label">From</label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 250px">
                                <input type='text' id="billingFrom" name="billingFrom"  class="form-control" value="${requestScope['billingFrom']}"/>
                            </div>
                        </div>
                    </div>   
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-1" style="width: 120px">
                                <label class="control-label">From<font style="color: red;">*</font></label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 200px">
                                <div class="form-group" id="DateFrom">
                                    <div class='input-group date'>
                                        <input type='text' id="fromdate" name="fromdate" class="form-control" data-date-format="YYYY-MM-DD" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-1" style="width: 233px"></div>
                            <div class="col-sm-1" style="width: 117px">
                                <label for="client" class="control-label">Bill Date</label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 220px">
                                <div class="form-group">
                                    <div class='input-group date'>
                                        <input type='text' id="billingDate" name="billingDate" class="form-control" data-date-format="YYYY-MM-DD" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-1" style="width: 120px">
                                <label class="control-label">To<font style="color: red;">*</font></label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 200px">
                                <div class="form-group" id="DateTo">
                                    <div class='input-group date'>
                                        <input type='text' id="todate" name="todate" class="form-control" data-date-format="YYYY-MM-DD" />
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-1" style="width: 233px"></div>
                            <div class="col-sm-1" >
                                <label for="client" class="control-label">Tel</label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 250px">
                                <input type='text' id="billingTel" name="billingTel"  class="form-control" value="${requestScope['billingTel']}"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="col-sm-2"></div>
                            <div class="col-sm-1" style="width: 120px">
                                <label class="control-label">Department</label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 200px">
                                <div class="form-group">
                                    <select id="departmentInvoice" name="departmentInvoice"  class="form-control">
                                        <option value="">--Select--</option>
                                        <option value="Wendy">Wendy</option>
                                        <option value="Outbound">Outbound</option>
                                        <option value="Inbound">Inbound</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-1" style="width: 233px"></div>
                            <div class="col-sm-1" >
                                <label for="client" class="control-label">Fax</label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 250px">
                                <input type='text' id="billingFax" name="billingFax"  class="form-control" value="${requestScope['billingFax']}"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">                         
                            <div class="col-sm-1" style="width: 755px"></div>
                            <div class="col-sm-1" >
                                <label class="control-label">Email</label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 250px">
                                <input type='text' id="billingMail" name="billingMail"  class="form-control" value="${requestScope['billingMail']}"/>
                            </div>
                        </div>
                    </div>
                    <div class="row"><br></div>
                    <div class="row">
                        <div class="col-sm-12">                         
                            <div class="col-sm-1" style="width: 700px"></div>
                            <div class="col-sm-1" style="width: 170px">
                                <label class="control-label">Remittance To</label>                                                                      
                            </div>
                            <div class="col-sm-1" style="width: 220px">
                                <div class="form-group">
                                    <select id="remittanceTo" name="remittanceTo"  class="form-control">
                                        <option value="">--Select--</option>
                                        <c:forEach var="item" items="${listAccno}" >
                                            <c:set var="selectAccno" value="" />
                                            <%--<c:if test="${item.id == invoice.MAccpay.id}">--%>
                                                <%--<c:set var="selectTerm" value="selected" />--%>
                                            <%--</c:if>--%>
                                            <option value="${item.name},${item.branch},${item.accNo}" ${selectAccno}>${item.code} - ${item.accNo}</option>
                                        </c:forEach>
                                    </select>
                                </div>    
                            </div>
                        </div>
                    </div>
                    <div class="row"><br></div>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <div class="col-sm-10 text-right">
                                    <button type="button"  class="btn btn-success" onclick="printInvoiceMonthly()"><span class="glyphicon glyphicon-print" id="btnDownloadAP" ></span> Print</button>
                                </div>
                                <div class="col-sm-2 text-left">
                                    <button type="button" onclick="" class="btn btn-warning"><span class="glyphicon glyphicon-print"></span> Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>                
            </div>
        </div>
    </div>
</div>
                            
<div class="modal fade" id="ClientModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Client</h4>
            </div>
            <div class="modal-body">
                <!--Bill To List Table-->
                <div style="text-align: right"> <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i> Search : <input type="text" style="width: 175px" id="searchClientFrom" name="searchClientFrom"/> </div> 
                <table class="display" id="ClientTable">
                    <thead>                        
                        <tr class="datatable-header">
                            <th>Bill From</th>
                            <th>Bill Name</th>
                            <th class="hidden">Address</th>
                            <th class="hidden">Tel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="client" items="${listClient}">
                            <tr onclick="setBillValue('${client.billTo}', '${client.billName}');">                                
                                <td class="item-billto">${client.billTo}</td>
                                <td class="item-name">${client.billName}</td>                                
                                <td class="item-address hidden">${client.address}</td>
                                <td class="item-tel hidden">${client.tel}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <div  class="text-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog --> <!-- /.modal-dialog -->
</div>

<!--Script-->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () { 
        $('.date').datetimepicker();
    $('span').click(function () {
        var position = $(this).offset();
        console.log("positon :"+position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });
         $("#InvoiceMonthlyFrom")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
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
//                alert("1");
                if (data.field === 'fromdate' && data.fv.isValidField('todate') === false) {
                    data.fv.revalidateField('todate');
                }

                if (data.field === 'todate' && data.fv.isValidField('fromdate') === false) {
                    data.fv.revalidateField('fromdate');
                }
            });
            $('#DateFrom').datetimepicker().on('dp.change', function (e) {
//                alert("1");
                $('#InvoiceMonthlyFrom').bootstrapValidator('revalidateField', 'fromdate');
            });
            $('#DateTo').datetimepicker().on('dp.change', function (e) {
                $('#InvoiceMonthlyFrom').bootstrapValidator('revalidateField', 'todate');
            });
            
            var from = setValueFromDate();
            var to = setValueToDate();
            $("#fromdate").val(from);
            $("#todate").val(to);
            
            
            var showflag = 1;
            var ClientTable = $('#ClientTable').dataTable({bJQueryUI: true,
                "sPaginationType": "full_numbers",
                "bAutoWidth": false,
                "bFilter": false,
                "bPaginate": true,
                "bInfo": false,
                "bLengthChange": false,
                "iDisplayLength": 10
            });

            $('#ClientTable tbody').on('click', 'tr', function() {
                $('.collapse').collapse('show');
                if ($(this).hasClass('row_selected')) {
                    $(this).removeClass('row_selected');
                }
                else {
                    ClientTable.$('tr.row_selected').removeClass('row_selected');
                    var staff_code = $(this).find("td").eq(0).html();
                    var staff_name = $(this).find("td").eq(1).html();
        //            alert("Herree" + staff_code);
                    $("#clientCode").val(staff_code);
                    $("#clientName").val(staff_name);
                    $(this).addClass('row_selected');
                }
            });

            $("#searchClientFrom").keyup(function(event) {
                if (event.keyCode === 13) {
                    if ($("#searchClientFrom").val() == "") {
                        // alert('please input data');
                    }
                    searchCustomerAgentList($("#searchClientFrom").val());
                }
            });

            //autocomplete
            $("#clientCode").keyup(function(event) {
                var position = $(this).offset();
                $(".ui-widget").css("top", position.top + 30);
                $(".ui-widget").css("left", position.left);
                if ($(this).val() === "") {
                     $("#clientCode").val("");
                    $("#clientName").val("");
                } else {
                    if (event.keyCode === 13) {
                        searchCustomerAutoList(this.value);
                    }
                }
            });

            $("#clientCode").keydown(function() {

                var position = $(this).offset();
                $(".ui-widget").css("top", position.top + 30);
                $(".ui-widget").css("left", position.left);
                if (showflag == 0) {
                    $(".ui-widget").css("top", -1000);
                    showflag = 1;
                }
            });
    });   
    
    function searchCustomerAgentList(name) {
        var servletName = 'BillableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&name=' + name +
                '&type=' + 'getListBillto';
        CallAjax(param);
    }

    function CallAjax(param) {
        var url = 'AJAXServlet';
        $("#ajaxload").removeClass("hidden");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function(msg) {
                    $('#ClientTable').dataTable().fnClearTable();
                    $('#ClientTable').dataTable().fnDestroy();
                    $("#ClientTable tbody").empty().append(msg);

                    $('#ClientTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });
                    $("#ajaxload").addClass("hidden");

                }, error: function(msg) {
                    $("#ajaxload").addClass("hidden");
                    alert('error');
                }
            });
        } catch (e) {
            alert(e);
        }
    }
    
    
    function searchCustomerAutoList(name) {
        var servletName = 'BillableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&name=' + name +
                '&type=' + 'getAutoListBillto';
        CallAjaxAuto(param);
    }

    function CallAjaxAuto(param) {
        var url = 'AJAXServlet';
        var billArray = [];
        var billListId = [];
        var billListName = [];
        var billid, billname;
        $("#clientCode").autocomplete("destroy");
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                beforeSend: function() {
                    $("#dataload").removeClass("hidden");
                },
                success: function(msg) {
                    var billJson = JSON.parse(msg);
                    for (var i in billJson) {
                        if (billJson.hasOwnProperty(i)) {
                            billid = billJson[i].id;
                            billname = billJson[i].name;
                            billArray.push(billid);
                            billArray.push(billname);
                            billListId.push(billid);
                            billListName.push(billname);
                        }
                        $("#dataload").addClass("hidden");
                    }
//                    $("#clientCode").val(billid);
                    $("#clientName").val(billname);
                    $("#clientCode").autocomplete({
                        source: billArray,
                        close: function() {
                            $("#clientCode").trigger("keyup");
                            var billselect = $("#clientCode").val();
                            for (var i = 0; i < billListId.length; i++) {
                                if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                                    $("#clientCode").val(billListId[i]);
                                    $("#clientName").val(billListName[i]);
                                }
                            }
                        }
                    });

                    var invoiceTo = $("#clientCode").val();
                    for (var i = 0; i < billListId.length; i++) {
                        if (invoiceTo == billListName[i]) {
                            $("#clientCode").val(billListId[i]);
                        }
                    }
                    if (billListId.length == 1) {
                        showflag = 0;
                        $("#clientCode").val(billListId[0]);
                    }
                    var event = jQuery.Event('keydown');
                    event.keyCode = 40;
                    $("#clientCode").trigger(event);

                }, error: function(msg) {
                    console.log('auto ERROR');
                    $("#dataload").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    }

    
    function setBillValue(billto, billname) {
        $("#clientCode").val(billto);
        $("#clientName").val(billname);

        $("#ClientModal").modal('hide');
    }

</script>
<script type="text/javascript" src="js/InvoiceMonthly.js"></script> 