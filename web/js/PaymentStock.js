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


function createStockDetails(stockid, productName, staff, addDate, effectiveFrom, effectiveTo) {
    var noStockTable = parseInt($("#noStockTable").val());
    for(var i=1; i<=noStockTable; i++){
        if(productName === $("#chk"+i).val()){
            $("#SearchStock").modal("hide");
            $('#fail').show();
            return;
        }else{
            $('#fail').hide();
        }
    }
                        
    $("#StockTable").append(
            '<tr>' +
            '<input type="hidden" id="paymentStockDetailId'+ noStockTable +'" name="paymentStockDetailId'+ noStockTable +'"  value=""> '+
            '<input type="hidden" id="paymentStockId'+ noStockTable +'" name="paymentStockId'+ noStockTable +'"  value="">' +
            '<input type="hidden" id="stockId'+ noStockTable +'" name="stockId'+ noStockTable +'"  value="'+ stockid +'"> '+
            '<td class="hidden"><input type="hidden" id="chk'+ noStockTable +'" name="chk'+ noStockTable +'" value="' + productName + '"/></td>' +
            '<td class="text-center ">' + noStockTable + '</td>' +
            '<td>' + productName + '</td>' +
            '<td>' + staff + '</td>' +
            '<td>' + addDate + '</td>' +
            '<td>' + effectiveFrom + '</td>' +
            '<td>' + effectiveTo + '</td>' +
            '<td class="text-center ">' +
            '<a href="#" onclick="" data-toggle="modal" data-target=""> <span id="editStockDetail" onclick="getStockDetail('+ stockid +')" class="glyphicon glyphicon glyphicon-list-alt"></span></a>' +
            '<a href="#" onclick="" data-toggle="modal" data-target=""> <span id="SpanRemove" class="glyphicon glyphicon-remove deleteicon" onclick="deletePaymentStockDetailList(\'\', \'' + noStockTable + '\' , \'' + stockid + '\');"></span></a>' +
            '</td>' +
            '<tr>'
            );
    
    $("#noStockTable").val(noStockTable+1);
    getStockDetail(stockid);
    $("#SearchStock").modal("hide");
}

function getStockDetail(stockid) {
    var countRowStockDetail = $("#StockDetailTable tr").length;
    var servletName = 'PaymentStockServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&stockId=' + stockid +
            '&countRowDetail=' + countRowStockDetail +
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
//    var countRowStockDetail = $("#StockDetailTable tr").length; 
//    $("#noStockTable").val(countRowStockDetail);
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

function deletePaymentStockDetailList(paymentStockDetailId , row , stockid){
    if(paymentStockDetailId === ''){
        $("#paymentStockDetailId" + row).parent().remove();
        var countRowStockDetail = $("#StockDetailTable tr").length; 
        for(var i=1 ; i < countRowStockDetail ; i++){
            var sit = $("#stockIdTable"+i).val();
            if(sit === stockid){
                $("#psdIdTable" + i).parent().parent().remove();
            }
        }

        var countRowStock = $("#StockDetailTable tr").length;   
        if(countRowStock === 1){
            document.getElementById('totalCost').value = formatNumber(0);
            document.getElementById('totalSale').value = formatNumber(0);
        }else{
            for(var i=1;i<countRowStock;i++){
                setFormatCurrency(i);
                calculateCostTotal();
                calculateSaleTotal();
            }
        }
    }else{
        document.getElementById('paymentStockDetailIdDelete').value = paymentStockDetailId;
        document.getElementById('paymentStockRowDelete').value = row;
        $("#delPaymentStock").text('Are you sure to delete stock from this payment ?');
        $('#DeletePaymentStock').modal('show');
    }
    
    
}

function DeleteRowPaymentStock(){
    var psdIdDelete = document.getElementById('paymentStockDetailIdDelete').value;
    var row = document.getElementById('paymentStockRowDelete').value;
        if (psdIdDelete === '') {
            var countRowStock = $("#StockTable tr").length;    
            var countRowStockDetail = $("#StockDetailTable tr").length; 
            for(var i=0 ; i < countRowStockDetail ; i++){
                var psdId = $("#psdIdTable"+i).val();
                var paymentStockDetailId = $("#paymentStockDetailId"+i).val();
                if(paymentStockDetailId  === psdIdDelete){
                    $("#paymentStockDetailId" + i).parent().parent().remove();
                }
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
                    var countRowStock = $("#StockTable tr").length;    
                    var countRowStockDetail = $("#StockDetailTable tr").length;    
                    
                    for(var i=1 ; i < countRowStockDetail ; i++){
                        var psdId = $("#psdIdTable"+i).val();
                        var paymentStockDetailId = $("#paymentStockDetailId"+i).val();
                        if(paymentStockDetailId  === psdIdDelete){
                            $("#paymentStockDetailId" + i).parent().parent().remove();
                        }
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
//    var countRowStockDetail = $("#StockDetailTable tr").length; 
//    for(var i=1 ; i < countRowStockDetail ; i++){
//        setFormatCurrency(i);
//        calculateCostTotal();
//        calculateSaleTotal();
//    }
    
    countRowStock = $("#StockDetailTable tr").length;   
    if(countRowStock === 1){
        document.getElementById('totalCost').value = formatNumber(0);
        document.getElementById('totalSale').value = formatNumber(0);
    }else{
        for(var i=1;i<countRowStock;i++){
            setFormatCurrency(i);
            calculateCostTotal();
            calculateSaleTotal();
        }
    }
}

function calculateCostTotal() {
    var count = $("#StockDetailTable tr").length;    
    var i;
    var grandTotal = 0;
    for (i = 1; i < count + 1; i++) {
        var amount = document.getElementById("cost" + i);
        if (amount !== null) {
            var value = amount.value;
            if (value !== '') {
                value = value.replace(/,/g, "");
                var total = parseFloat(value);
                grandTotal += total;
                document.getElementById('cost' + i).value = formatNumber(total);
            }
        }
    }
    document.getElementById('totalCost').value = formatNumber(grandTotal);
}

function calculateSaleTotal() {
    var count = $("#StockDetailTable tr").length; 
    var i;
    var grandTotal = 0;
    for (i = 1; i < count + 1; i++) {
        var amount = document.getElementById("sale" + i);
        if (amount !== null) {
            var value = amount.value;
            if (value !== '') {
                value = value.replace(/,/g, "");
                var total = parseFloat(value);
                grandTotal += total;
                document.getElementById('sale' + i).value = formatNumber(total);
            }
        }
    }
    document.getElementById('totalSale').value = formatNumber(grandTotal);
}

function setFormatCurrencyOnFocusOut(row) {
    $('#cost' + row).focusout(function() {
        setFormatCurrency(row);
        calculateCostTotal();
    });

    $('#sale' + row).focusout(function() {
        setFormatCurrency(row);
        calculateSaleTotal();
    });

}
	
function setFormatCurrency(row) {
    var cost = replaceAll(",", "", $('#cost' + row).val());
    if (cost == "") {
        cost = 0;
    }
    cost = parseFloat(cost);
    document.getElementById("cost" + row).value = formatNumber(cost);

    var sale = replaceAll(",", "", $('#sale' + row).val());
    if (sale == "") {
        sale = 0;
    }
    sale = parseFloat(sale);
    document.getElementById("sale" + row).value = formatNumber(sale);

    if (cost == "" || cost == 0) {
        document.getElementById("cost" + row).value = "";
    }

    if (sale == "" || sale == 0) {
        document.getElementById("sale" + row).value = "";
    }
    
    calculateCostTotal();
    calculateSaleTotal();
}
	
function replaceAll(find, replace, str) {
    return str.replace(new RegExp(find, 'g'), replace);
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
}

function insertCommas(nField) {
    if (/^0/.test(nField.value)) {
        nField.value = nField.value.substring(0, 1);
    }
    if (Number(nField.value.replace(/,/g, ""))) {
        var tmp = nField.value.replace(/,/g, "");
        tmp = tmp.toString().split('').reverse().join('').replace(/(\d{3})/g, '$1,').split('').reverse().join('').replace(/^,/, '');
        if (/\./g.test(tmp)) {
            tmp = tmp.split(".");
            tmp[1] = tmp[1].replace(/\,/g, "").replace(/ /, "");
            nField.value = tmp[0] + "." + tmp[1]
        } else {
            nField.value = tmp.replace(/ /, "");
        }
    } else {
        nField.value = nField.value.replace(/[^\d\,\.]/g, "").replace(/ /, "");
    }
}

