/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function searchAction() {
    //alert("OK");
    var action = document.getElementById('action');
    action.value = 'search';
    document.getElementById('MProductCommission').submit();
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


function DeleteProductCommission(id,name){
    var ProductID = document.getElementById('ProductCommissionID');
    ProductID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete product : " + name + " ?";
}

function DeleteProductCom() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('MProductCommission').submit();
}

