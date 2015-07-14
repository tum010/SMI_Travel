/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function selectListOther(counter){
    var count = document.getElementById('countListOther');
    for(var i = 1 ; i <= count.value ; i++){
        $('#TableOther'+i).css("display","none");
    }
    $('#SpanEdit'+ counter).click(function() {
//        $('#TableOther'+counter).css("display","block");
        for(var i = 1 ; i <= count.value ; i++){                  
            if(i === counter){
//                alert(i +" : " +counter +">>>" + count.value);
                $('#TableOther'+counter).css("display","block");
            }
        }

    });
}
function searchAction() {
    //alert("OK");
    var action = document.getElementById('action');
    action.value = 'search';
    document.getElementById('DaytourOtherForm').submit();
}
$(document).ready(function () {      
   
    // validate
   $("#InputRefNo").focus();
    $("#InputRefNo").keyup(function (event) {
        if (event.keyCode === 13) {
            searchAction();
        }
    });
    
   
});

$(document).ready(function () {
 $('#PacketTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
});
    
// Search Ajax Booking List
$(document).ready(function () {
    $("#filtercus").keyup(function (event) {
        if (event.keyCode === 13) {
            FilterBookingList($("#filtercus").val());
        }
    });

});

function FilterBookingList(name) {
    // var name = 
    var servletName = 'BookOtherServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getOtherBookList'; 
    CallFilterAjax(param);
}

function CallFilterAjax(param) {
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                try {
                    $('#PacketTable').dataTable().fnClearTable();
                    $('#PacketTable').dataTable().fnDestroy();
                    $("#PacketTable tbody").empty().append(msg);
                    $('#PacketTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });
                     $("#ajaxload").addClass("hidden");
                } catch (e) {
                    alert(e);
                }

            }, error: function (msg) {
                 $("#ajaxload").addClass("hidden");
                alert(msg);
            }
        });
    } catch (e) {
        alert(e);
    }
}

function printOtherVoucher(status) {  
    var voucher = document.getElementById("voucher").value;
//    if(status == "Duplicate"){
//       checkDuplicate();  
//    }

    var InputRefNo = document.getElementById("InputRefNo").value;
    if (voucher == "OtherVouncher"){
        window.open("report.smi?name=DaytourOther&refno=" + InputRefNo+"&comfirm="+status);
    }else{
        window.open("report.smi?name=OtherVouncherEmail&refno=" + InputRefNo+"&comfirm="+status);
    }
}

function checkDuplicate(){
    var duplicate = document.getElementById("Duplicate").value;
    if(duplicate === 'Duplicate'){
        $('#PrintModal').modal("show");
    }else{
        printOtherVoucher('');
    }
}