/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. testtt
 */
$(document).ready(function() {
    $('#StockDetailTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
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
    document.getElementById('PaymentStockForm').submit();
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
    
    for(var i=1; i<noStockTable; i++){
        if(stockid === $("#stockId"+i).val()){
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
            '<td class="text-center ">'+ noStockTable +'</td>' +
            '<td>' + productName + '</td>' +
            '<td>' + staff + '</td>' +
            '<td class="text-center ">' + addDate + '</td>' +
            '<td class="text-center ">' + effectiveFrom + '</td>' +
            '<td class="text-center ">' + effectiveTo + '</td>' +
            '<td class="text-center ">' +
            '<a id="ButtonEdit'+ noStockTable +'" onclick="getStockDetail( \'' + stockid + '\', \'' + "null" + '\' , \'' + productName + '\');hideCollapse();" class="carousel" data-toggle="collapse" data-parent="#accordion" data-target="#payStockDetail" aria-expanded="true" aria-controls="collapseExample">'+
            '<span id="SpanEdit'+ noStockTable +'" class="glyphicon glyphicon glyphicon-list-alt"></span></a>'+
            '<a href="#" onclick="" data-toggle="modal" data-target=""> <span id="SpanRemove" class="glyphicon glyphicon-remove deleteicon" onclick="deletePaymentStockDetailList(\'\', \'' + noStockTable + '\' , \'' + stockid + '\');"></span></a>' +
            '</td>' +
            '<tr>'
            );
    $("#noStockTable").val(noStockTable+1);
    getStockDetail(stockid,"null",productName);
    $("#SearchStock").modal("hide");
    
}

function getStockDetail(stockid,psdId,productname) {
    document.getElementById('detailName').style.display = 'block';
    document.getElementById('detailName').innerHTML = "Detail (" + productname + ")";
    var servletName = 'PaymentStockServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&stockId=' + stockid +
            '&countRowDetail=' + 1 +
            '&type=' + 'getStockDetail';
    CallAjax(param,psdId,stockid);
}

function CallAjax(param,psdId,stockid) {
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
                    $('#StockDetailTable').dataTable().fnClearTable();
                    $('#StockDetailTable').dataTable().fnDestroy();
                    $("#StockDetailTable tbody").append(msg);

                    $('#StockDetailTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": true,
                        "bInfo": false,
                        "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
                        "iDisplayLength": 10,
                        "bSort": false,
                        "bPaginate": false
                    });
                    
                    
                    $('table.paginated').each(function() {
                        var currentPage = 0;
                        var numPerPage = 10;
                        var $table = $(this);
                        $table.bind('repaginate', function() {
                            $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
                        });
                        $table.trigger('repaginate');
                        var numRows = $table.find('tbody tr').length;
                        var numPages = Math.ceil(numRows / numPerPage);
                        var $pager = $('<div class="col-xs-12 text-right" id="pageNo"><font style="color: #499DD5"></font>&nbsp;</div>');
                        var $br = $('<div class="col-xs-12"><br></div>');
                        for (var page = 0; page < numPages; page++) {
                            if(page === 0){
                                $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + "First" + "  ").bind('click', {
                                newPage: page
                                }, function(event) {
                                    currentPage = event.data['newPage'];
                                    $table.trigger('repaginate');
                                    $(this).addClass('active').siblings().removeClass('active');
                                }).appendTo($pager).addClass('clickable');
                            }

                            $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + (page + 1) + "  ").bind('click', {
                                newPage: page
                            }, function(event) {
                                currentPage = event.data['newPage'];
                                $table.trigger('repaginate');
                                $(this).addClass('active').siblings().removeClass('active');
                            }).appendTo($pager).addClass('clickable');

                            if(page === (numPages - 1)){
                                $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + "Last" + "  ").bind('click', {
                                newPage: page
                                }, function(event) {
                                    currentPage = event.data['newPage'];
                                    $table.trigger('repaginate');
                                    $(this).addClass('active').siblings().removeClass('active');
                                }).appendTo($pager).addClass('clickable');
                            }
                        }
                        $br.insertAfter($table).addClass('active');
                        $pager.insertAfter($table).find('span.page-number:first').addClass('active');
                        document.getElementById("pageNo").style.cursor="pointer";
                    });
                    
                    
                    if(psdId !== "null" && psdId !== null){
                        getPaymentStockItemCostSaleAjax(psdId);
                    }else{
//                        hideCollapse();
                        $('.collapse').collapse('show');
                    }
                    
                }
            }, error: function(msg) {
               alert('error');
            }
        });
    } catch (e) {
        alert(e);
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

