/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
   // Add Row Auto key
   $("#DetailBillableTable").on("keyup", function () {
        var rowAll = $("#DetailBillableTable tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#DetailBillableTable tr").length;
//                alert("Goo : " + colIndex + " " + rowIndex +"Row All :  " + rowAll);
                if (rowIndex == rowAll) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    AddRowDetailBillAble(rowAll);
                }
            }
        });
    });
   // Add Row Detail Bill Able
    var countertable = $("#counterTable").val();
    AddRowDetailBillAble(countertable);
 });  

function AddRowDetailBillAble(row){
    if (!row) {
        row = 1;
    }
    var rows = document.getElementById("DetailBillableTable").getElementsByTagName("tr").length;
    if(rows > 1){
        $("#tr_FormulaAddRow").css("display","none");
    }

    $("#DetailBillableTable tbody").append(
        '<tr>' +
        '<td class="hidden"><input type="text" class="form-control" id="DetailBillId' + row + '" name="DetailBillId' + row + '" value="" > </td>' +
        '<td> </td>' +
        '<td><input type="text" class="form-control" id="BillDescription' + row + '" name="BillDescription' + row + '" value="" > </td>' +
        '<td><input type="text" class="form-control" id="InputCost' + row + '" name="InputCost' + row + '" value="" ></td>' +
        '<td><select id="SelectCurrencyCost' + row + '" name="SelectCurrencyCost' + row + '" class="form-control">'+ select +'</select></td>' +
        '<td> </td>' +
        '<td> <input type="text" class="form-control" id="InputAmount' + row + '" name="InputAmount' + row + '" value="" ></td>'+
        '<td><select id="SelectCurrencyAmount' + row + '" name="SelectCurrencyAmount' + row + '" class="form-control">'+ select +'</select></td>'+
        '<td>100000</td>'+
        '<td>'+ defaultD +'</td>'+
        '<td><input type="checkbox" value="" id="checkUse' + row + '" name="checkUse' + row + '" class="form-control"></td>'+
        '<td>100000</td>'+
        '<td align="center" ><center><span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill('+row+',\'\')" data-toggle="modal" data-target="#DelDetailBill" >  </span></center>'+
        '</tr>'
    );
    var count = document.getElementById('counterTable');
    count.value = row++;
}

function DeleteDetailBill(rowID,code){
//    alert("rowID:"+rowID+":");
    $("#idDeleteDetailBillable").val(rowID);
    if(code !== ""){
        $("#DeleteDetailBillable").text('Are you sure to delete detail billable Code :'+ code +'..?');
    }else{
        $("#DeleteDetailBillable").text('Are you sure to delete detail billable ?');
    }
}

function DeleteBill() {
    var count = document.getElementById('counterTable');
    var rowId  = document.getElementById('idDeleteDetailBillable');
    var DetailBillId  = $("#DetailBillId"+rowId.value).val();
    if((DetailBillId !== "")&&(DetailBillId !== undefined)){
        rowId.value = DetailBillId ;
        var action = document.getElementById('action');
        action.value = 'delete';
        document.getElementById('InvoiceForm').submit();
        
    }else{
        $("#BillDescription" + rowId.value).parent().parent().remove();
        $('#DelDetailBill').modal('hide');
        console.log("Row 0  : " + count.value );
        if (count.value <= 1) {
            console.log("show button tr_FormulaAddRow : " );
            $("#tr_FormulaAddRow").css("display","block");
        }
        count.value = count.value - 1 ;
    }
}

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

function printInvoice(){  
    window.open("report.smi?name=InvoiceReport");   
}