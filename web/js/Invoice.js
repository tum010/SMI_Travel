/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function DisableVoid(){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('disableVoid').innerHTML = "Are you sure to delete booking other : " +  + " ?";
}

function EnableVoid(){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('enableVoid').innerHTML = "Are you sure to enable booking other : " + code + " ?";
}

function Enable() {
    var action = document.getElementById('action');
    action.value = 'enable';
    document.getElementById('OtherForm').submit();
}

function Disable() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('OtherForm').submit();
}

function DeleteDetailBill(){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('disableVoid').innerHTML = "Are you sure to delete booking other : " +  + " ?";
}

function DeleteBill() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('OtherForm').submit();
}

function printInvoice(){  
    window.open("report.smi?name=InvoiceReport");   
}

function printInvoiceNew(){  
    window.open("report.smi?name=InvoiceEmail");   
}

 $(document).ready(function () {
    var tableSaleStaff = $('#SaleStaffTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    var tableARCode = $('#ARCodeTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    var tableInvTo = $('#InvToTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
 });  

