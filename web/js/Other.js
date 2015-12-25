/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function DeleteOther(id,code){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete booking other : " + code + " ?";
    $('#DelOther').modal('show');
}

function EnableOther(id,code){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('enableCode').innerHTML = "Are you sure to enable booking other : " + code + " ?";
}

function Enable() {
    var action = document.getElementById('action');
    action.value = 'enable';
    document.getElementById('OtherForm').submit();
}

function Delete() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('OtherForm').submit();
}

$(document).ready(function () {
    var bla = $('#resultText').val();

    if(bla == "save successful"){
        
        $('#textAlertDivSave').show();
    }else if ( bla === ""){
        $('#textAlertDivSave').hide();
    }else {
        $('#textAlertDivNotSave').show();
    }
//    setMarkUp();
});

function getCouponCheck(id,code) {
    var servletName = 'BookOtherServlet';
    var servicesName = 'AJAXBean';
    var couponId = id;
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&couponId=' + couponId +
            '&type=' + 'getCouponCheck';
    CallAjax(param, id, code);
    
}

function CallAjax(param, id, code) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                var result = msg; 
                if (result == 'true') {                
                    DeleteOther(id,code);                                
                }

                if (result == 'false') {
                    alert('Can\'t Delete this other package!!!'); 
                }
            }, error: function(msg) {
                //alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

function setMarkUp(){
    var totalCost = parseFloat(($("#Totalcost").val()).replace(/,/g, ""));
    var totalPrice = parseFloat(($("#Totalprice").val()).replace(/,/g, ""));
    var markUp = ((totalPrice/totalCost)-1)*100;
    $("#markup").val(formatNumber(markUp));
}

