/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function selectListOther(counter,id){
    var count = document.getElementById('countListOther');
    var countClick = $('#countClick'+counter).val();
    var rowTable = $('#rowTable').val();
    var countTable = parseInt($('#countTable').val());
//    for(var i = 1 ; i <= count.value ; i++){
//        $("#TableOther"+i).addClass("hidden");
//    }
//    $('#SpanEdit'+ counter).click(function() {
        var num = countClick++;
        $('#countClick'+counter).val(num);
        var clickcount = $('#countClick'+counter).val();    
        
        if(parseInt(rowTable) !== id){
            for(var i = 1 ; i <= count.value ; i++){
                $("#TableOther"+i).addClass("hidden");
            }
            $("#TableOther"+counter).removeClass("hidden");
            $('#rowTable').val(id);
        
        }else{
            if($("#TableOther"+counter).hasClass("hidden")){
                $("#TableOther"+counter).removeClass("hidden");
            }else{
                $("#TableOther"+counter).addClass("hidden");
            }
            $('#rowTable').val(id);
        }
        
        
//        if((clickcount%2) === 0){
//            console.log("Click 0 / % TableOther"+ counter +" : " + (clickcount%2));
//            $('#TableOther'+counter).css("display","block");
//        }
//        if((clickcount%2) !== 0){
//            $('#TableOther'+counter).css("display","none");
//            console.log("Click 1  % TableOther"+ counter +"  : " + (clickcount%2));
//            
//        }
//    });
}
function searchAction() {
    //alert("OK");
    $('#textAlertDivNotPrint').hide();
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
 oTable = $('#PacketTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    oTable.fnSort( [ [0,'desc'] ] );
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
                    success: function(msg) {
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
                }, error: function(msg) {
                    $("#ajaxload").addClass("hidden");
//                    alert(msg);
               }
           });
        } catch (e) {
           alert(e);
        }
    
//    var url = 'AJAXServlet';
//    $("#ajaxload").removeClass("hidden");
//    try {
//        $.ajax({
//            type: "POST",
//            url: url,
//            cache: false,
//            data: param,
//            success: function (msg) {
//                try {
//                    
//                } catch (e) {
//                    alert(e);
//                }
//
//            }, error: function (msg) {
//                 $("#ajaxload").addClass("hidden");
//                alert(msg);
//            }
//        });
//    } catch (e) {
//        alert(e);
//    }
}

function printOtherVoucher(status) {  
    var voucher = document.getElementById("voucher").value;
//    if(status == "Duplicate"){
//       checkDuplicate();  
//    }
    var InputRefNo = document.getElementById("InputRefNo").value;
    if(InputRefNo.indexOf('-') >= 0){
        InputRefNo = InputRefNo.replace(/\-/g,'');
    }
    var otherId = "";
    var countOther = parseInt($("#countOther").val());
    for(var i=1; i<=countOther; i++){
        if ($('#otherCheck' + i).is(":checked")) {
            otherId += (otherId !== '' ? ',' + $('#otherId' + i).val() : $('#otherId' + i).val());
        }
    }
    if(otherId === ''){
        $('#textAlertDivSelect').show();
        return;
    }
    var passengerId = "";
    var countPassenger = parseInt($("#countPassenger").val());
    for(var i=1; i<=countPassenger; i++){
        if ($('#passengerCheck' + i).is(":checked")) {
            passengerId += (passengerId !== '' ? ',' + $('#passengerId' + i).val() : $('#passengerId' + i).val());
        }
    }
    
    if (voucher === "OtherVouncher"){
        window.open("report.smi?name=DaytourOther&otherId=" + otherId + "&passengerId=" + passengerId + "&refNo=" + InputRefNo + "&comfirm="+status);
    }else{
        window.open("report.smi?name=OtherVouncherEmail&otherId=" + otherId + "&passengerId=" + passengerId + "&refNo=" + InputRefNo + "&comfirm="+status);
    }
}

function checkDuplicate(){
    $('#textAlertDivNotPrint').hide();
    var voucher = document.getElementById("voucher").value;
    var duplicate = document.getElementById("Duplicate").value;
    if(voucher === ''){
        $('#textAlertDivNotPrint').show();
    }else{
        if(duplicate === 'Duplicate'){
            $('#PrintModal').modal("show");
        }else{
            printOtherVoucher('');
        }
    }
}

function hideTextAlert(){
    $('#textAlertDivNotPrint').hide();
    $('#textAlertDivSelect').hide();
}