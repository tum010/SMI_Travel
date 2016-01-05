/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
//    $('#StockDetailTable').dataTable({bJQueryUI: true,
//        "sPaginationType": "full_numbers",
//        "bAutoWidth": true,
//        "bFilter": true,
//        "bPaginate": true,
//        "bInfo": false,
//        "bLengthChange": false
//    });
    $('#SearchStockTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false
    });
});

function searchStock() {
    $("#SearchStock").modal("show");
}


function searchPaymentNoStock() {
    var action = document.getElementById('action');
    action.value = 'searchPayNo';
    var payNo = document.getElementById('payNo');
    payNo.value = $("#payNo").val();
//    document.getElementById('PaymentStockForm').submit();
}


function createStockDetail(stockid, productName, staff, addDate, effectiveFrom, effectiveTo) {
    var noStockTable = parseInt($("#noStockTable").val());
    for(var i=1; i<=noStockTable; i++){
        if(productName === $("#chk"+i).val()){
            $("#SearchStock").modal("hide");
            $('#fail').show();
            return;
        }
    }
    
    $("#StockTable").append(
            '<tr>' +
            '<td class="hidden"><input type="hidden" id="chk'+ noStockTable +'" name="chk'+ noStockTable +'" value="' + productName + '"/></td>' +
            '<td class="text-center ">' + noStockTable + '</td>' +
            '<td>' + productName + '</td>' +
            '<td>' + staff + '</td>' +
            '<td>' + addDate + '</td>' +
            '<td>' + effectiveFrom + '</td>' +
            '<td>' + effectiveTo + '</td>' +
            '<td class="text-center ">' +
            '<a href="#" onclick="" data-toggle="modal" data-target=""> <span id="editTour" onclick="editTour(\'\')" class="glyphicon glyphicon glyphicon-list-alt"></span></a>' +
            '<a href="#" onclick="" data-toggle="modal" data-target=""> <span id="SpanRemove" class="glyphicon glyphicon-remove deleteicon" onclick="deletelist(\''+ productName +'\', \''+ noStockTable +'\');"></span></a>' +
            '</td>' +
            '<tr>'
            );
    $("#noStockTable").val(noStockTable + 1);
    getStockDetail(stockid);
    $("#SearchStock").modal("hide");
}

function getStockDetail(stockid) {
    var servletName = 'PaymentStockServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&stockId=' + stockid +
            '&type=' + 'getStockDetail';
    CallAjax(param);
}

function CallAjax(param) {
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                if(msg !== 'null'){
//                    $('#StockDetailTable').dataTable().fnClearTable();
//                    $('#StockDetailTable').dataTable().fnDestroy();
                    $("#StockDetailTable tbody").append(msg);

//                    $('#StockDetailTable').dataTable({bJQueryUI: true,
//                        "sPaginationType": "full_numbers",
//                        "bAutoWidth": false,
//                        "bFilter": false,
//                        "bPaginate": true,
//                        "bInfo": false,
//                        "bLengthChange": false,
//                        "iDisplayLength": 5
//                    });
                }
//                $("#ajaxload").addClass("hidden");

            }, error: function(msg) {
//                $("#ajaxload").addClass("hidden");
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function deletelist(productName,no){
    var noStockDetailTable = parseInt($("#noStockDetailTable").val());
    $("#chk" + no).parent().parent().remove();
    for(var i=1; i<=noStockDetailTable; i++){
        if(productName === $("#del"+i).val()){
            $("#del" + i).parent().parent().remove();
        }
    }
}

function deletePaymentStockDetailList(paymentStockDetailId , row){
    document.getElementById('paymentStockDetailIdDelete').value = paymentStockDetailId;
    document.getElementById('paymentStockRowDelete').value = row;
    $("#delPaymentStock").text('Are you sure to delete stock from this payment ?');
    $('#DeletePaymentStock').modal('show');
}

function DeleteRowPaymentStock(){
    var psdIdDelete = document.getElementById('paymentStockDetailIdDelete').value;
    var row = document.getElementById('paymentStockRowDelete').value;
        if (psdIdDelete === '') {
            for(var i=0 ; i < 10 ; i++){
                var psdId = $("#psdIdTable"+i).val();
                if(psdId === psdIdDelete){
                    $("#psdIdTable" + i).parent().parent().remove();
                }
            }
        }
        else {
            $.ajax({
                url: 'PaymentStock.smi?action=deletePaymentStock',
                type: 'get',
                data: {psdIdDelete: psdIdDelete},
                success: function() {
                    for(var i=0 ; i < 10 ; i++){
                        var psdId = $("#psdIdTable"+i).val();
                        if(psdId === psdIdDelete){
                            $("#psdIdTable" + i).parent().parent().remove();
                        }
                    }
                },
                error: function() {
                    console.log("error");
//                    result = 0;
                }
            });
        }
    $('#DeletePaymentStock').modal('hide');    
}


