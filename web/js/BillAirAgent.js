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
    $('.date').datetimepicker();
        $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });
//        validateBillAirAgent();
    
//            .on('success.field.fv', function (e, data) {
//        if (data.field === 'invoiceFromDate' && data.fv.isValidField('InvoiceToDate') === false) {
//                data.fv.revalidateField('InvoiceToDate');
//        }
//        if (data.field === 'InvoiceToDate' && data.fv.isValidField('invoiceFromDate') === false) {
//            data.fv.revalidateField('invoiceFromDate');
//        }
//        
//        if (data.field === 'issueFrom' && data.fv.isValidField('issueTo') === false) {
//                data.fv.revalidateField('issueTo');
//        }
//        if (data.field === 'issueTo' && data.fv.isValidField('issueFrom') === false) {
//            data.fv.revalidateField('issueFrom');
//        }
//        
//        if (data.field === 'refundFrom' && data.fv.isValidField('refundTo') === false) {
//                data.fv.revalidateField('refundTo');
//        }
//        if (data.field === 'refundTo' && data.fv.isValidField('refundFrom') === false) {
//            data.fv.revalidateField('refundFrom');
//        }
//    });
//    $('#DateFrom').datetimepicker().on('dp.change', function (e) {
//        $('#BillAirAgent').bootstrapValidator('revalidateField', 'invoiceFromDate');
//    });
//    $('#DateTo').datetimepicker().on('dp.change', function (e) {
//        $('#BillAirAgent').bootstrapValidator('revalidateField', 'InvoiceToDate');
//    });
//    
//    $('#DateFromIssue').datetimepicker().on('dp.change', function (e) {
//        $('#BillAirAgent').bootstrapValidator('revalidateField', 'issueFrom');
//    });
//    $('#DateToIssue').datetimepicker().on('dp.change', function (e) {
//        $('#BillAirAgent').bootstrapValidator('revalidateField', 'issueTo');
//    });
//    
//    $('#DateFromRefund').datetimepicker().on('dp.change', function (e) {
//        $('#BillAirAgent').bootstrapValidator('revalidateField', 'refundFrom');
//    });
//    $('#DateToRefund').datetimepicker().on('dp.change', function (e) {
//        $('#BillAirAgent').bootstrapValidator('revalidateField', 'refundTo');
//    });
    $('.invfromdate').datetimepicker().change(function(){                          
        checkInvFromDateField();
    });
    $('.invtodate').datetimepicker().change(function(){                          
        checkInvToDateField();
    });
    $('.issuefromdate').datetimepicker().change(function(){                          
        checkIssueFromDateField();
    });
    $('.issuetodate').datetimepicker().change(function(){                          
        checkIssueToDateField();
    });
    
    $('.refundfromdate').datetimepicker().change(function(){                          
        checkRefundFromDateField();
    });
    $('.refundtodate').datetimepicker().change(function(){                          
        checkRefundToDateField();
    });
});

function printBillAirAgent(){
    var agentCode = document.getElementById("agentId").value;       
    var invoiceFrom = document.getElementById("invoiceFromDate").value;
    var invoiceTo = document.getElementById("InvoiceToDate").value;
    var issueFrom = document.getElementById("issueFrom").value;
    var issueTo = document.getElementById("issueTo").value;
    var refundFrom = document.getElementById("refundFrom").value;
    var refundTo = document.getElementById("refundTo").value;
    var departments = document.getElementById("department").value;
    var salebyUsers = document.getElementById("salebyUser").value;
    var termPays = document.getElementById("termPay").value;
    var paymentType = document.getElementById("paymentType").value;
    var vatTemp = document.getElementById("vatTemp").value;
    var whtTemp = document.getElementById("whtTemp").value;
    console.log("Agent : " + agentCode);
    if(agentCode !== ''){
        if((invoiceFrom !== '') && (invoiceTo !== '')){
            window.open("Excel.smi?name=BillAirAgentSummary&agentCode=" + agentCode + "&invoiceFrom=" + invoiceFrom + "&invoiceTo=" + invoiceTo + "&issueFrom=" + issueFrom + "&issueTo=" + issueTo +  "&refundFrom=" + refundFrom + "&refundTo=" + refundTo + "&department=" + departments+ "&salebyUser=" + salebyUsers + "&termPay=" + termPays +"&paymentType="+paymentType+"&vatTemp="+vatTemp+"&whtTemp="+whtTemp);
        }else if((issueFrom !== '') && (issueTo !== '')){
            window.open("Excel.smi?name=BillAirAgentSummary&agentCode=" + agentCode + "&invoiceFrom=" + invoiceFrom + "&invoiceTo=" + invoiceTo + "&issueFrom=" + issueFrom + "&issueTo=" + issueTo +  "&refundFrom=" + refundFrom + "&refundTo=" + refundTo + "&department=" + departments+ "&salebyUser=" + salebyUsers + "&termPay=" + termPays+"&paymentType="+paymentType+"&vatTemp="+vatTemp+"&whtTemp="+whtTemp);
        }else if((refundFrom !== '') && (refundFrom !== '')){
            window.open("Excel.smi?name=BillAirAgentSummary&agentCode=" + agentCode + "&invoiceFrom=" + invoiceFrom + "&invoiceTo=" + invoiceTo + "&issueFrom=" + issueFrom + "&issueTo=" + issueTo + "&refundFrom=" + refundFrom + "&refundTo=" + refundTo + "&department=" + departments+ "&salebyUser=" + salebyUsers + "&termPay=" + termPays+"&paymentType="+paymentType+"&vatTemp="+vatTemp+"&whtTemp="+whtTemp);
        }else {
            validateDate();  
        }
    }else{
        validateBillAirAgent();  
    }
    
//    if(reportType == 1){
//        window.open("Excel.smi?name=TicketFareAirlineReport&ticketType=" + ticketType + "&ticketBuy=" + ticketBuy + "&airline=" + airline + "&airlineCode=" + airlineCode + "&dateFrom=" + from + "&dateTo=" + to + "&department=" + department + "&staff=" + salebyUser + "&termPay=" + termPay);
//    }else if(reportType == 2){
//        window.open("Excel.smi?name=TicketFareInvoicReport&ticketType=" + ticketType + "&ticketBuy=" + ticketBuy + "&airline=" + airline + "&airlineCode=" + airlineCode + "&dateFrom=" + from + "&dateTo=" + to + "&department=" + department + "&staff=" + salebyUser + "&termPay=" + termPay);
//    }else if(reportType == 3){
//        window.open("Excel.smi?name=TicketFareAgentReport&ticketType=" + ticketType + "&ticketBuy=" + ticketBuy + "&airline=" + airline + "&airlineCode=" + airlineCode + "&dateFrom=" + from + "&dateTo=" + to + "&department=" + department + "&staff=" + salebyUser + "&termPay=" + termPay);
//    }

}

function checkInvFromDateField(){
    var InputToDate = document.getElementById("InvoiceToDate");
    var inputFromDate = document.getElementById("invoiceFromDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#invfromdatepanel").removeClass("has-success");
        $("#invtodatepanel").removeClass("has-success");
        $("#invfromdatepanel").addClass("has-error");
        $("#invtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");
        $("#refundfromdatepanel").removeClass("has-error");
        $("#refundtodatepanel").removeClass("has-error");
        $("#invfromdatepanel").addClass("has-success");
        $("#invtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("invfrom","");
    }
}
    
function checkInvToDateField(){
    var InputToDate = document.getElementById("InvoiceToDate");
    var inputFromDate = document.getElementById("invoiceFromDate");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#invfromdatepanel").removeClass("has-success");
        $("#invtodatepanel").removeClass("has-success");
        $("#invfromdatepanel").addClass("has-error");
        $("#invtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");
        $("#refundfromdatepanel").removeClass("has-error");
        $("#refundtodatepanel").removeClass("has-error");
        $("#invfromdatepanel").addClass("has-success");
        $("#invtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("invto","");
    }       
}

function checkIssueFromDateField(){
    var InputToDate = document.getElementById("issueTo");
    var inputFromDate = document.getElementById("issueFrom");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#issuefromdatepanel").removeClass("has-success");
        $("#issuetodatepanel").removeClass("has-success");
        $("#issuefromdatepanel").addClass("has-error");
        $("#issuetodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");
        $("#refundfromdatepanel").removeClass("has-error");
        $("#refundtodatepanel").removeClass("has-error");
        $("#issuefromdatepanel").addClass("has-success");
        $("#issuetodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("issuefrom","");
    }
}
    
function checkIssueToDateField(){
    var InputToDate = document.getElementById("issueTo");
    var inputFromDate = document.getElementById("issueFrom");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#issuefromdatepanel").removeClass("has-success");
        $("#issuetodatepanel").removeClass("has-success");
        $("#issuefromdatepanel").addClass("has-error");
        $("#issuetodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");
        $("#refundfromdatepanel").removeClass("has-error");
        $("#refundtodatepanel").removeClass("has-error");
        $("#issuefromdatepanel").addClass("has-success");
        $("#issuetodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("issueto","");
    }       
}


function checkRefundFromDateField(){
    var InputToDate = document.getElementById("refundTo");
    var inputFromDate = document.getElementById("refundFrom");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#refundfromdatepanel").removeClass("has-error");
        $("#refundtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#refundfromdatepanel").removeClass("has-success");
        $("#refundtodatepanel").removeClass("has-success");
        $("#refundfromdatepanel").addClass("has-error");
        $("#refundtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");
        $("#refundfromdatepanel").removeClass("has-error");
        $("#refundtodatepanel").removeClass("has-error");
        $("#refundfromdatepanel").addClass("has-success");
        $("#refundtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("refundfrom","");
    }
}
    
function checkRefundToDateField(){
    var InputToDate = document.getElementById("refundTo");
    var inputFromDate = document.getElementById("refundFrom");
    if(InputToDate.value === '' && inputFromDate.value === ''){
        $("#refundfromdatepanel").removeClass("has-error");
        $("#refundtodatepanel").removeClass("has-error");  
        $("#printbutton").removeClass("disabled");
    }else if(inputFromDate.value === '' || InputToDate.value === ''){
        $("#refundfromdatepanel").removeClass("has-success");
        $("#refundtodatepanel").removeClass("has-success");
        $("#refundfromdatepanel").addClass("has-error");
        $("#refundtodatepanel").addClass("has-error");
        $("#printbutton").addClass("disabled");
    } else {
        $("#invfromdatepanel").removeClass("has-error");
        $("#invtodatepanel").removeClass("has-error");
        $("#issuefromdatepanel").removeClass("has-error");
        $("#issuetodatepanel").removeClass("has-error");
        $("#refundfromdatepanel").removeClass("has-error");
        $("#refundtodatepanel").removeClass("has-error");
        $("#refundfromdatepanel").addClass("has-success");
        $("#refundtodatepanel").addClass("has-success");
        $("#printbutton").removeClass("disabled");
        checkDateValue("refundto","");
    }       
}

function checkDateValue(date){
    var inputFromDate = "";
        var InputToDate = "";
        if((date === 'invfrom') || (date === 'invto')){
            inputFromDate = document.getElementById("invoiceFromDate");
            InputToDate = document.getElementById("InvoiceToDate");
        } else if ((date === 'issuefrom') || (date === 'issueto')){
            inputFromDate = document.getElementById("issueFrom");
            InputToDate = document.getElementById("issueTo");
        } else {
            inputFromDate = document.getElementById("refundFrom");
            InputToDate = document.getElementById("refundTo");
        }
    if((inputFromDate.value !== '') && (InputToDate.value !== '')){
        var fromDate = (inputFromDate.value).split('-');
        var toDate = (InputToDate.value).split('-');
        if((parseInt(fromDate[0])) > (parseInt(toDate[0]))){
            validateDate(date,"over");
        }
        if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))){
            validateDate(date,"over");
        }
        if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[2])) > (parseInt(toDate[2]))){
            validateDate(date,"over");
        }          
    }
}
    
function validateDate(date,option){
   if(option === 'over'){
        if(date === 'invfrom'){
            $("#invfromdatepanel").removeClass("has-success");
            $("#invfromdatepanel").addClass("has-error");                                 
        }
        if(date === 'invto'){
            $("#invtodatepanel").removeClass("has-success");
            $("#invtodatepanel").addClass("has-error");
        }
        if(date === 'issuefrom'){
            $("#issuefromdatepanel").removeClass("has-success");
            $("#issuefromdatepanel").addClass("has-error");
        }
        if(date === 'issueto'){
            $("#issuetodatepanel").removeClass("has-success"); 
            $("#issuetodatepanel").addClass("has-error");
        }
        if(date === 'refundfrom'){
            $("#refundfromdatepanel").removeClass("has-success");
            $("#refundfromdatepanel").addClass("has-error");
        }
        if(date === 'refundto'){
            $("#refundtodatepanel").removeClass("has-success"); 
            $("#refundtodatepanel").addClass("has-error");
        } 
        $("#printbutton").addClass("disabled");
    } else {
        $("#invfromdatepanel").removeClass("has-success");
        $("#invtodatepanel").removeClass("has-success");
        $("#issuefromdatepanel").removeClass("has-success");
        $("#issuetodatepanel").removeClass("has-success"); 
        $("#refundfromdatepanel").removeClass("has-success");
        $("#refundtodatepanel").removeClass("has-success"); 
        $("#agentcodepanel").removeClass("has-success"); 
        $("#agentnamepanel").removeClass("has-success"); 
        
        $("#invfromdatepanel").addClass("has-error");
        $("#invtodatepanel").addClass("has-error");
        $("#issuefromdatepanel").addClass("has-error");
        $("#issuetodatepanel").addClass("has-error");
        $("#refundfromdatepanel").addClass("has-error");
        $("#refundtodatepanel").addClass("has-error");
        $("#agentcodepanel").addClass("has-error"); 
        $("#agentnamepanel").addClass("has-error"); 
        $("#printbutton").addClass("disabled");
    }
   
}
    