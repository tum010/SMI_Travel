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

function createStockDetail(no, productName, staff, addDate, effectiveFrom, effectiveTo) {
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
    
    if (productName === 'TEST0001') {
        var ad = 'ad-';
        var type = 'adult';
        var refNo = '250001';
        var pickup = '2015-11-11';
        var pickdate = '2015-12-12';
        for (var i = 0; i < 5; i++) {
            var noStockDetailTable = parseInt($("#noStockDetailTable").val());
            $("#StockDetailTable").append(
                    '<tr>' +
                    '<td class="hidden"><input type="hidden" id="del'+ noStockDetailTable +'" name="del'+ noStockDetailTable +'" value="' + productName + '"/></td>' +
                    '<td class="text-center ">' + noStockDetailTable + '</td>' +
                    '<td>' + ad + i +'</td>' +
                    '<td>' + type + '</td>' +
                    '<td>' + refNo + '</td>' +
                    '<td>' + pickup + '</td>' +
                    '<td>' + pickdate + '</td>' +
                    '<td class="text-center ">' +
                    '<input type="checkbox" id="check" name="check"/>' +
                    '</td>' +
                    '<tr>'

                    );
            $("#noStockDetailTable").val(noStockDetailTable + 1);
        }
    }else if(productName === 'TEST0002'){       
        var ad = 'ch-';
        var type = 'child';
        var refNo = '250002';
        var pickup = '2015-09-09';
        var pickdate = '2015-10-10';
        for (var i = 0; i < 5; i++) {
            var noStockDetailTable = parseInt($("#noStockDetailTable").val());
            $("#StockDetailTable").append(
                    '<tr>' +
                    '<td class="hidden"><input type="hidden" id="del'+ noStockDetailTable +'" name="del'+ noStockDetailTable +'" value="' + productName + '"/></td>' +
                    '<td class="text-center ">' + noStockDetailTable + '</td>' +
                    '<td>' + ad + i +'</td>' +
                    '<td>' + type + '</td>' +
                    '<td>' + refNo + '</td>' +
                    '<td>' + pickup + '</td>' +
                    '<td>' + pickdate + '</td>' +
                    '<td class="text-center ">' +
                    '<input type="checkbox" id="check" name="check"/>' +
                    '</td>' +
                    '<tr>'

                    );
            $("#noStockDetailTable").val(noStockDetailTable + 1);
        }
    }
    $("#SearchStock").modal("hide");
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
