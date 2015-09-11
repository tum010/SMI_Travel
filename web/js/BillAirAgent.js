/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {

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
    
    //Agent
    $("#AgentTable tr").on('click', function () {
        var saleby_id = $(this).find(".agent-id").text();
        var saleby_user = $(this).find(".agent-user").text();
        var saleby_name = $(this).find(".agent-name").text();
        $("#agentId").val(saleby_id);
        $("#agentCode").val(saleby_user);
        $("#agentName").val(saleby_name);
        $("#AgentModal").modal('hide');
    });
        
    var salebyuseragent = [];
    $.each(agent, function (key, value) {
        salebyuseragent.push(value.username);
        salebyuseragent.push(value.name);
    });

    $("#agentCode").autocomplete({
        source: salebyuseragent,
        close:function( event, ui ) {
           $("#agentCode").trigger('keyup');
        }
    });
        
    $("#agentCode").on('keyup',function(){
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var username = this.value.toUpperCase();
        var name = this.value.toUpperCase();
        console.log("Name :"+ name + " Username : " + username);
        $("#agentId,#agentName").val(null);
        $.each(agent, function (key, value) {
            console.log("Name 1:"+ value.username.toUpperCase() + " Username1 : " + username);
            if (value.username.toUpperCase() === username ) { 
                console.log("Name 2:"+ name + " Username2 : " + username);
                $("#agentId").val(value.id);
                $("#agentCode").val(value.username);
                $("#agentName").val(value.name);
            }
            else if(value.name.toUpperCase() === name){
                console.log("Name 3:"+ name + " Username3 : " + username);
                $("#agentCode").val(value.username);
                $("#agentId").val(value.id);
                $("#agentName").val(value.name);
            }
        }); 
    }); 
    
    $('#AgentTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    $('#AgentTable tbody').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
    
    //validate
    $('.date').datetimepicker({
    });
        
    $('span').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
        
    $("#BillAirAgent").bootstrapValidator({
        framework: 'bootstrap',
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            invoiceFromDate: {
                trigger: 'focus keyup change',
                validators: {
                    date: {
                        format: 'YYYY-MM-DD',
                        max: 'InvoiceToDate',
                        message: 'The Date From is not a valid'
                    },notEmpty: {
                        message: 'The Date To is required'
                    }
                }
            },
            InvoiceToDate: {
                trigger: 'focus keyup change',
                validators: {
                    date: {
                        format: 'YYYY-MM-DD',
                        min: 'invoiceFromDate',
                        message: 'The Date To is not a valid'
                    },notEmpty: {
                        message: 'The Date To is required'
                    }
                }
            },
            issueFrom: {
                trigger: 'focus keyup change',
                validators: {
                    date: {
                        format: 'YYYY-MM-DD',
                        min: 'issueTo',
                        message: 'The Date To is not a valid'
                    }
                }
            },
            issueTo: {
                trigger: 'focus keyup change',
                validators: {
                    date: {
                        format: 'YYYY-MM-DD',
                        min: 'issueFrom',
                        message: 'The Date To is not a valid'
                    }
                }
            },
            refundFrom: {
                trigger: 'focus keyup change',
                validators: {
                    date: {
                        format: 'YYYY-MM-DD',
                        min: 'refundTo',
                        message: 'The Date To is not a valid'
                    }
                }
            },
            refundTo: {
                trigger: 'focus keyup change',
                validators: {
                    date: {
                        format: 'YYYY-MM-DD',
                        min: 'refundFrom',
                        message: 'The Date To is not a valid'
                    }
                }
            }
        }
    }).on('success.field.fv', function (e, data) {
        if (data.field === 'invoiceFromDate' && data.fv.isValidField('InvoiceToDate') === false) {
                data.fv.revalidateField('InvoiceToDate');
        }
        if (data.field === 'InvoiceToDate' && data.fv.isValidField('invoiceFromDate') === false) {
            data.fv.revalidateField('invoiceFromDate');
        }
        
        if (data.field === 'issueFrom' && data.fv.isValidField('issueTo') === false) {
                data.fv.revalidateField('issueTo');
        }
        if (data.field === 'issueTo' && data.fv.isValidField('issueFrom') === false) {
            data.fv.revalidateField('issueFrom');
        }
        
        if (data.field === 'refundFrom' && data.fv.isValidField('refundTo') === false) {
                data.fv.revalidateField('refundTo');
        }
        if (data.field === 'refundTo' && data.fv.isValidField('refundFrom') === false) {
            data.fv.revalidateField('refundFrom');
        }
    });
    $('#DateFrom').datetimepicker().on('dp.change', function (e) {
        $('#BillAirAgent').bootstrapValidator('revalidateField', 'invoiceFromDate');
    });
    $('#DateTo').datetimepicker().on('dp.change', function (e) {
            $('#BillAirAgent').bootstrapValidator('revalidateField', 'InvoiceToDate');
    });
    
    $('#DateFromIssue').datetimepicker().on('dp.change', function (e) {
        $('#BillAirAgent').bootstrapValidator('revalidateField', 'issueFrom');
    });
    $('#DateToIssue').datetimepicker().on('dp.change', function (e) {
            $('#BillAirAgent').bootstrapValidator('revalidateField', 'issueTo');
    });
    
    $('#DateFromRefund').datetimepicker().on('dp.change', function (e) {
        $('#BillAirAgent').bootstrapValidator('revalidateField', 'refundFrom');
    });
    $('#DateToRefund').datetimepicker().on('dp.change', function (e) {
            $('#BillAirAgent').bootstrapValidator('revalidateField', 'refundTo');
    });

});

function printBillAirAgent(){
    var reportType = document.getElementById("reportType").value;
    var ticketType = document.getElementById("ticketType").value;
    var ticketBuy = document.getElementById("ticketBuy").value;
    var airline = document.getElementById("airline").value;
    var airlineCode = document.getElementById("airlineCode").value;
    var from = document.getElementById("startdate").value;
    var to = document.getElementById("enddate").value;
    var department = document.getElementById("department").value;
    var salebyUser = document.getElementById("salebyUser").value;
    var termPay = document.getElementById("termPay").value;

    if(reportType == 1){
        window.open("Excel.smi?name=TicketFareAirlineReport&ticketType=" + ticketType + "&ticketBuy=" + ticketBuy + "&airline=" + airline + "&airlineCode=" + airlineCode + "&dateFrom=" + from + "&dateTo=" + to + "&department=" + department + "&staff=" + salebyUser + "&termPay=" + termPay);
    }else if(reportType == 2){
        window.open("Excel.smi?name=TicketFareInvoicReport&ticketType=" + ticketType + "&ticketBuy=" + ticketBuy + "&airline=" + airline + "&airlineCode=" + airlineCode + "&dateFrom=" + from + "&dateTo=" + to + "&department=" + department + "&staff=" + salebyUser + "&termPay=" + termPay);
    }else if(reportType == 3){
        window.open("Excel.smi?name=TicketFareAgentReport&ticketType=" + ticketType + "&ticketBuy=" + ticketBuy + "&airline=" + airline + "&airlineCode=" + airlineCode + "&dateFrom=" + from + "&dateTo=" + to + "&department=" + department + "&staff=" + salebyUser + "&termPay=" + termPay);
    }

}