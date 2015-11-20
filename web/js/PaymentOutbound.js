/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('.date').datetimepicker();
    $(".datemask").mask('00-00-0000', {reverse: true});
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });
    $('#SearchInvoicSupTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false
    });
    $('#SearchAPCodeTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 3
    });

    $('#collapseExample').on('shown.bs.collapse', function() {
        $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-down").addClass("glyphicon glyphicon-chevron-up");
    });

    $('#collapseExample').on('hidden.bs.collapse', function() {
        $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-up").addClass("glyphicon glyphicon-chevron-down");
    });

    $(".numerical").on('input', function() {
        var value = $(this).val().replace(/[^0-9.,]*/g, '');
        value = value.replace(/\.{2,}/g, '.');
        value = value.replace(/\.,/g, ',');
        value = value.replace(/\,\./g, ',');
        value = value.replace(/\,{2,}/g, ',');
        value = value.replace(/\.[0-9]+\./g, '.');
        $(this).val(value);
    });
    
    var rowPaymentDetailTable = parseInt($("#countPaymentDetail").val());
    AddRowPaymentDetailTable(rowPaymentDetailTable);

    $("#PaymentDetailTable").on("keyup", function() {
        var rowAll = ($("#PaymentDetailTable tr").length) - 2;
        $("td").keyup(function() {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 3;
                rowAll = ($("#PaymentDetailTable tr").length);
                if ((rowIndex == rowAll) || ((rowIndex - 1) == rowAll)) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    AddRowPaymentDetailTable(parseInt($("#countPaymentDetail").val()));
                }
                if (rowAll < 2) {
                    $("#tr_PaymentOutboundDetailAddRow").removeClass("hide");
                    $("#tr_PaymentOutboundDetailAddRow").addClass("show");
                }
            }
        });
    });

    var codeInvoiceSup = [];
    $.each(invoiceSup, function(key, value) {
        codeInvoiceSup.push(value.code);
        if (!(value.name in codeInvoiceSup)) {
            codeInvoiceSup.push(value.name);
        }
    });

    $("#invSupCode").autocomplete({
        source: codeInvoiceSup,
        close: function(event, ui) {
            $("#invSupCode").trigger('keyup');
        }
    });

    $("#invSupCode").on('keyup', function() {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value;
        $("#invSupId,#invSupName,#invSupApCode").val(null);
        $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');

        $.each(invoiceSup, function(key, value) {
            if (value.code.toUpperCase() === code) {
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
                $("#invSupApCode").val(value.apcode);
                $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');
            }
            if (name === value.name) {
                $("#invSupCode").val(value.code);
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
                $("#invSupApCode").val(value.apcode);
                code = $("#invSupCode").val().toUpperCase();
                $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');
            }
        });
    });

    $("#invSupCode").on('blur', function() {
        var delay = 500;//1 seconds
        setTimeout(function() {
            $.each(invoiceSup, function(key, value) {
                //alert(value.code);
                if ($("#invSupCode").val() === value.code) {
                    $("#invSupId").val(value.id);
                    $("#invSupName").val(value.name);
                    $("#invSupApCode").val(value.apcode);
                    $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');
                }
            });

        }, delay);

    });

});

function refNoValidate() {
    $('#refnonopanel').removeClass('has-feedback');
    $('#refnonopanel').addClass('has-success');
    $('#refnonopanel').removeClass('has-error');
}

function stockValidate() {
    $('#stockpanel').removeClass('has-feedback');
    $('#stockpanel').addClass('has-success');
    $('#stockpanel').removeClass('has-error');
}

function searchRefNo() {

}

function searchStock() {

}

function AddRowPaymentDetailTable(row) {
    var color = (row % 2 === 0 ? "#F2F2F2" : "");
    if (!row) {
        row = 1;
    }
    $("#PaymentDetailTable tbody").append(
            '<tr >' +
            '<td class="hidden">' +
                '<input type="text" name="detailId' + row + '" id="detailId' + row + '" class="form-control" value=""/>' +
                '<input type="text" name="payId' + row + '" id="payId' + row + '" class="form-control" value=""/>' +
                '<input type="text" name="bookDetailId' + row + '" id="payId' + row + '" class="form-control" value=""/>' +
                '<input type="text" name="bookDetailType' + row + '" id="bookDetailType' + row + '" class="form-control" value=""/>' +
                '<input type="text" name="accCode' + row + '" id="accCode' + row + '" class="form-control" value=""/>' +
                '<input type="text" name="exportDate' + row + '" id="exportDate' + row + '" class="form-control" value=""/>"/>' +
                '<input type="text" name="isExport' + row + '" id="isExport' + row + '" class="form-control" value=""/>' +
            '</td>' +
            '<td>' +
            '<select class="form-control" name="type' + row + '" id="type' + row + '" onchange="addRow()">' +
            '<option  value="" ></option>' +
            '</select>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="refNo' + row + '" id="refNo' + row + '" class="form-control" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="invoice' + row + '" id="invoice' + row + '" class="form-control" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="cost' + row + '" id="cost' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="gross' + row + '" id="gross' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" onfocusout="calculateGrossTotal();calculateVatTotal();" value=""/>' +
            '</td>' +
            '<td align="center">' +
            '<input type="checkbox" id="isVat' + row + '" name="isVat' + row + '" onclick="" value="">' +
            '</td>' +
            '<td align="right" id="vatShow' + row + '">' +
            '<input type="hidden" name="vat' + row + '" id="vat' + row + '" class="form-control" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="amount' + row + '" id="amount' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" onfocusout="calculateGrandTotal();calculateVatTotal();" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="comm' + row + '" id="comm' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" value=""/>' +
            '</td>' +
            '<td>' +
            '<select class="form-control" name="cur' + row + '" id="cur' + row + '" onchange="addRow()">' +
            '<option  value="" ></option>' +
            '</select>' +
            '</td>' +
            '<td class="text-center" rowspan="2">' +
            '<a href="#" onclick=""  data-toggle="modal" data-target="">' +
            '<span id="spanDelete' + row + '" class="glyphicon glyphicon-remove deleteicon"  onclick="deletePaymentDetailList(\'\',\'' + row + '\')" data-toggle="modal" ></span>' +
            '</a>' +
            '</td>' +
            '</tr>' +
            '<tr>' +
            '<td colspan="1" align="right" bgcolor="#E8EAFF" >' +
            '<b>Description</b>' +
            '</td>' +
            '<td colspan="2">' +
            '<input type="text" name="description' + row + '" id="description' + row + '" class="form-control" value=""/>' +
            '</td>' +
            '<td colspan="1" align="right" bgcolor="#E8EAFF">' +
            '<b>Pay Stock</b>' +
            '</td>' +
            '<td colspan="3">' +
            '<input type="text" name="payStock' + row + '" id="payStock' + row + '" class="form-control" value=""/>' +
            '</td>' +
            '<td colspan="1" align="right" bgcolor="#E8EAFF">' +
            '<b>Value</b>' +
            '</td>' +
            '<td colspan="2">' +
            '<input type="text" name="value' + row + '" id="value' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" value=""/>' +
            '</td>' +
            '</tr>'

            );
    $("#tr_PaymentOutboundDetailAddRow").removeClass("show");
    $("#tr_PaymentOutboundDetailAddRow").addClass("hide");
    $("#typeClone option").clone().appendTo("#type" + row);
    $("#curClone option").clone().appendTo("#cur" + row);
//        var vatData = parseFloat($("#vatDefault").val());
//    document.getElementById('vatShow' + row).innerHTML = parseFloat($("#vatDefault").val());
//    document.getElementById('vat' + row).value = parseFloat($("#vatDefault").val());
    $("#countPaymentDetail").val(row + 1);

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

function setupInvSupValue(id, code, name, apcode) {
    $('#SearchInvoiceSup').modal('hide');
    document.getElementById('invSupId').value = id;
    document.getElementById('invSupCode').value = code;
    document.getElementById('invSupName').value = name;
    document.getElementById('invSupApCode').value = apcode;
    document.getElementById('invSupCode').focus();
    $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');
}

function addRow() {
    AddRowPaymentDetailTable(parseInt($("#countPaymentDetail").val()));
}

function deletePaymentDetailList(id, row) {
    $("#delPaymentDetailId").val(id);
    $("#delPaymentDetailRow").val(row);
    $("#delPaymentOutboundModal").modal("show");
}

function confirmDeletePaymentDetailList() {
    var row = document.getElementById('delPaymentDetailRow').value;
    var id = document.getElementById('delPaymentDetailId').value;

    if (id === '') {
        $("#type" + row).parent().parent().remove();
        $("#description" + row).parent().parent().remove();
        var rowAll = $("#PaymentDetailTable tr").length;
        if (rowAll <= 1) {
            $("#tr_PaymentOutboundDetailAddRow").removeClass("hide");
            $("#tr_PaymentOutboundDetailAddRow").addClass("show");
        }

    } else {
        $.ajax({
            url: 'PaymentOutbound.smi?action=deletePaymentOutboundDetail',
            type: 'get',
            data: {ProductDetail: id},
            success: function() {
                $("#type" + row).parent().parent().remove();
                $("#description" + row).parent().parent().remove();
                var rowAll = $("#PaymentDetailTable tr").length;
                if (rowAll <= 1) {
                    $("#tr_PaymentOutboundDetailAddRow").removeClass("hide");
                    $("#tr_PaymentOutboundDetailAddRow").addClass("show");
                }
            },
            error: function() {
                console.log("error");
                result = 0;
            }
        });
    }
    $('#delPaymentOutboundModal').modal('hide');
//    CalculateGrandTotal('');
}

function calculateGrossTotal() {

}

function calculateVatTotal() {

}

function calculateGrandTotal() {

}
