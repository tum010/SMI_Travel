/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function printTicketFareSummary(){
    var reportType = document.getElementById("reportType").value;
    var ticketType = document.getElementById("ticketType").value;
    var ticketBuy = document.getElementById("ticketBuy").value;
    var airline = document.getElementById("airline").value;
    var airlineCode = document.getElementById("airlineCode").value;
//    var from = document.getElementById("startdate").value;
//    var to = document.getElementById("enddate").value;
    var department = document.getElementById("department").value;
    var salebyUser = document.getElementById("salebyUser").value;
    var termPay = document.getElementById("termPay").value;
    var issuefrom = document.getElementById("issueFrom").value;
    var issueto = document.getElementById("issueTo").value;
    var invFrom = document.getElementById("invoiceFromDate").value;
    var invTo = document.getElementById("invoiceToDate").value;
    
    if((invFrom !== '') && (invTo !== '')){
        if(reportType == 1){
            window.open("Excel.smi?name=TicketFareAirlineReport&ticketType=" + ticketType + 
                    "&ticketBuy=" + ticketBuy + 
                    "&airline=" + airline + 
                    "&airlineCode=" + airlineCode + 
                    "&issuedateFrom=" + issuefrom +
                    "&issuedateTo=" + issueto +
                    "&invdateFrom=" + invFrom +
                    "&invdateTo=" + invTo +
                    "&department=" + department + 
                    "&staff=" + salebyUser + 
                    "&termPay=" + termPay);
        }else if(reportType == 2){
            window.open("Excel.smi?name=TicketFareInvoicReport&ticketType=" + ticketType + 
                    "&ticketBuy=" + ticketBuy + 
                    "&airline=" + airline + 
                    "&airlineCode=" + airlineCode + 
                    "&issuedateFrom=" + issuefrom +
                    "&issuedateTo=" + issueto +
                    "&invdateFrom=" + invFrom +
                    "&invdateTo=" + invTo +
                    "&department=" + department + 
                    "&staff=" + salebyUser + 
                    "&termPay=" + termPay);
        }else if(reportType == 3){
            window.open("Excel.smi?name=TicketFareAgentReport&ticketType=" + ticketType + 
                    "&ticketBuy=" + ticketBuy + 
                    "&airline=" + airline + 
                    "&airlineCode=" + airlineCode + 
                    "&issuedateFrom=" + issuefrom +
                    "&issuedateTo=" + issueto +
                    "&invdateFrom=" + invFrom +
                    "&invdateTo=" + invTo +
                    "&department=" + department + 
                    "&staff=" + salebyUser + 
                    "&termPay=" + termPay);
        }else{
            $("#reporttypepanel").removeClass("has-success");
            $("#reporttypepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        }
    } else if((issuefrom !== '') && (issueto !== '')){
        if(reportType == 1){
            window.open("Excel.smi?name=TicketFareAirlineReport&ticketType=" + ticketType + 
                    "&ticketBuy=" + ticketBuy + 
                    "&airline=" + airline + 
                    "&airlineCode=" + airlineCode + 
                    "&issuedateFrom=" + issuefrom +
                    "&issuedateTo=" + issueto +
                    "&invdateFrom=" + invFrom +
                    "&invdateTo=" + invTo +
                    "&department=" + department + 
                    "&staff=" + salebyUser + 
                    "&termPay=" + termPay);
        }else if(reportType == 2){
            window.open("Excel.smi?name=TicketFareInvoicReport&ticketType=" + ticketType + 
                    "&ticketBuy=" + ticketBuy + 
                    "&airline=" + airline + 
                    "&airlineCode=" + airlineCode + 
                    "&issuedateFrom=" + issuefrom +
                    "&issuedateTo=" + issueto +
                    "&invdateFrom=" + invFrom +
                    "&invdateTo=" + invTo +
                    "&department=" + department + 
                    "&staff=" + salebyUser + 
                    "&termPay=" + termPay);
        }else if(reportType == 3){
            window.open("Excel.smi?name=TicketFareAgentReport&ticketType=" + ticketType + 
                    "&ticketBuy=" + ticketBuy + 
                    "&airline=" + airline + 
                    "&airlineCode=" + airlineCode + 
                    "&issuedateFrom=" + issuefrom +
                    "&issuedateTo=" + issueto +
                    "&invdateFrom=" + invFrom +
                    "&invdateTo=" + invTo +
                    "&department=" + department + 
                    "&staff=" + salebyUser + 
                    "&termPay=" + termPay);
        }else{
            $("#reporttypepanel").removeClass("has-success");
            $("#reporttypepanel").addClass("has-error");
            $("#printbutton").addClass("disabled");
        }
    } else {
        validateDate();  
    }
 
}

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

});

