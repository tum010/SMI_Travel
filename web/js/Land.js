/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function DeleteLand(id,name){
    var OtherID = document.getElementById('LandID');
    OtherID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete booking land : " + name + " ?";
}

function EnableLand(id,name){
    var OtherID = document.getElementById('LandID');
    OtherID.value = id;
    document.getElementById('enableCode').innerHTML = "Are you sure to enable booking land : " + name + " ?";
}

function Enable() {
    var action = document.getElementById('action');
    action.value = 'enable';
    document.getElementById('LandForm').submit();
}

function Delete() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('LandForm').submit();
}

function printVoucher(refno){
    var printtype = document.getElementById('printtype').value;
    if(printtype == 0){
        alert('please select voucher to print');
    }else if(printtype == 1){
        window.open("report.smi?name=LandVoucher&refno="+refno);
    }else if(printtype == 2){
        window.open("report.smi?name=LandVoucherEmail&refno="+refno);
    }else if(printtype == 3){
        window.open("report.smi?name=LandVoucherEmailAgent&refno="+refno);
    }
    
}