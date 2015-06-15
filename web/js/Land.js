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

 $(document).ready(function() {

    var table = $('#MasterLand').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": false,
        "bInfo": false
    });

    $('#MasterLand tbody').on('click', 'tr', function() {
        $('.collapse').collapse('show');
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
            }
    });

    setformat();
});

function setformat() {
    $('#MasterLand tr td.moneyformat').each(function() {
        var innerHTML = $(this).html();
        $(this).html(numberWithCommas($(this).html()));
    });
}

function printVoucher(refno){
    var printtype = document.getElementById('printtype').value;
    var landId = "";
    $('#MasterLand tr.row_selected').each(function () {
        landId = $(this).attr('id');
    });
    if(landId == ""){
        alert("please select land to print voucher");
    }else if(printtype == 0){
        alert('please select voucher to print');
    }else if(printtype == 1){
        window.open("report.smi?name=LandVoucher&refno="+refno+"&landId="+landId);
    }else if(printtype == 2){
        window.open("report.smi?name=LandVoucherEmail&refno="+refno+"&landId="+landId);
    }else if(printtype == 3){
        window.open("report.smi?name=LandVoucherEmailAgent&refno="+refno+"&landId="+landId);
    }
    
}