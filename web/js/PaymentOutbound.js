/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $(".money").mask('000,000,000.00', {reverse: true});
    $('.date').datetimepicker();
    $(".datemask").mask('0000-00-00', {reverse: true});
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

    $("#payNo").keyup(function(event) {
        if (event.keyCode === 13) {
            searchPvNo();
        }
    });

    $("#refNo").keyup(function(event) {
        if (event.keyCode === 13) {
            searchRefNo();
        }
    });

    var rowPaymentDetailTable = parseInt($("#countPaymentDetail").val());
    addRowPaymentDetailTable(rowPaymentDetailTable);

    $("#PaymentDetailTable").on("keyup", function() {
        var rowAll = ($("#PaymentDetailTable tr").length) - 2;
        $("td").keyup(function() {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 3;
                rowAll = ($("#PaymentDetailTable tr").length);
                if ((rowIndex == rowAll) || ((rowIndex - 1) == rowAll)) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    addRowPaymentDetailTable(parseInt($("#countPaymentDetail").val()));
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

    //Result
    var result = $('#result').val();
    if (result === "success") {
        $('#textAlertDivSave').show();
    } else if (result === 'fail') {
        $('#textAlertDivNotSave').show();
    } else if (result === 'delete success') {
        $('#textAlertDivDelete').show();
    } else if (result === 'delete fail') {
        $('#textAlertDivNotDelete').show();
    } else if (result === "") {
        $('#textAlertDivSave').hide();
        $('#textAlertDivNotSave').hide();
        $('#textAlertDivDelete').hide();
        $('#textAlertDivNotDelete').hide();
    }

    setEnvironment()

});

function reloadPage(){
    var action = document.getElementById('action');
    action.value = 'new';
    document.getElementById('PaymentOutboundForm').submit();
}

//Set Data at start
function setEnvironment() {
    var row = parseInt($("#countPaymentDetail").val());
    for (var i = 1; i <= row; i++) {
        if ($("#cost" + i).val() !== '') {
            $("#cost" + i).val(formatNumber(parseFloat($("#cost" + i).val())));
        }
        if ($("#gross" + i).val() !== '') {
            $("#gross" + i).val(formatNumber(parseFloat($("#gross" + i).val())));
        }
        if ($("#amount" + i).val() !== '') {
            $("#amount" + i).val(formatNumber(parseFloat($("#amount" + i).val())));
        }
        if ($("#comm" + i).val() !== '') {
            $("#comm" + i).val(formatNumber(parseFloat($("#comm" + i).val())));
        }
        if ($("#value" + i).val() !== '') {
            $("#value" + i).val(formatNumber(parseFloat($("#value" + i).val())));
        }
    }
    calculateGrossTotal()
    calculateGrandTotal()
    calculateVatTotal()
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

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
    var refNo = $("#refNo").val();
    if (refNo === "") {
        if (!$('#refnopanel').hasClass('has-feedback')) {
            $('#refnopanel').addClass('has-feedback');
        }
        $('#refnopanel').removeClass('has-success');
        $('#refnopanel').addClass('has-error');
    } else {
        var servletName = 'PaymentOutboundServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&refNo=' + refNo +
                '&type=' + 'searchRefNo';
        CallAjaxSearchRef(param);
    }
}

function CallAjaxSearchRef(param) {
    var url = 'AJAXServlet';
    $("#ajaxloadRefNo").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                try {
                    if (msg == "null") {
                        $('#RefNoTable > tbody  > tr').each(function() {
                            $(this).remove();
                        });

                    } else {
                        $('#RefNoTable > tbody  > tr').each(function() {
                            $(this).remove();
                        });
                        $("#RefNoTable tbody").append(msg);

                    }
                    $("#ajaxloadRefNo").addClass("hidden");

                } catch (e) {
                    $('#RefNoTable > tbody  > tr').each(function() {
                        $(this).remove();
                    });
                    $("#ajaxloadRefNo").addClass("hidden");
                }

            }, error: function(msg) {
                $('#RefNoTable > tbody  > tr').each(function() {
                    $(this).remove();
                });
                $("#ajaxloadRefNo").addClass("hidden");
            }
        });
    } catch (e) {
        $('#RefNoTable > tbody  > tr').each(function() {
            $(this).remove();
        });
    }
}

function searchStock() {

}

function addRowPaymentDetailTable(row) {
    var color = (row % 2 === 0 ? "#F2F2F2" : "");
    if (!row) {
        row = 1;
    }
    $("#PaymentDetailTable tbody").append(
            '<tr >' +
            '<td class="hidden">' +
            '<input type="text" name="count' + row + '" id="count' + row + '" class="form-control" value="' + row + '"/>' +
            '<input type="text" name="detailId' + row + '" id="detailId' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="payId' + row + '" id="payId' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="bookDetailId' + row + '" id="payId' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="bookDetailType' + row + '" id="bookDetailType' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="accCode' + row + '" id="accCode' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="exportDate' + row + '" id="exportDate' + row + '" class="form-control" value=""/>"/>' +
            '<input type="text" name="isExport' + row + '" id="isExport' + row + '" class="form-control" value=""/>' +
            '</td>' +
            '<td>' +
            '<select class="form-control" name="type' + row + '" id="type' + row + '" onchange="addRow(\'' + row + '\')">' +
            '<option  value="" ></option>' +
            '</select>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="refNo' + row + '" id="refNo' + row + '" class="form-control" onfocusout="checkRefNo(\'' + row + '\')" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="invoice' + row + '" id="invoice' + row + '" class="form-control" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="cost' + row + '" id="cost' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" onfocusout="setFormatNumber(\'cost\',\'' + row + '\')" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="gross' + row + '" id="gross' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" value="" readonly=""/>' +
            '</td>' +
            '<td align="center">' +
            '<input type="checkbox" id="isVat' + row + '" name="isVat' + row + '" onclick="calculateGross(\'' + row + '\')" value="">' +
            '</td>' +
            '<td align="right" id="vatShow' + row + '"></td>' +
            '<td class="hidden">' +
            '<input type="text" id="vat' + row + '" name="vat' + row + '" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="amount' + row + '" id="amount' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" onfocusout="setFormatNumber(\'amount\',\'' + row + '\')" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="comm' + row + '" id="comm' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" onfocusout="setFormatNumber(\'comm\',\'' + row + '\')" value=""/>' +
            '</td>' +
            '<td>' +
            '<select class="form-control" name="cur' + row + '" id="cur' + row + '" onchange="addRow(\'' + row + '\')">' +
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
            '<input type="text" name="value' + row + '" id="value' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" onfocusout="setFormatNumber(\'value\',\'' + row + '\')" value=""/>' +
            '</td>' +
            '</tr>'

            );
    $("#tr_PaymentOutboundDetailAddRow").removeClass("show");
    $("#tr_PaymentOutboundDetailAddRow").addClass("hide");
    $("#typeClone option").clone().appendTo("#type" + row);
    $("#curClone option").clone().appendTo("#cur" + row);
//    document.getElementById('vatShow' + row).innerHTML = parseFloat($("#mVat").val());
    document.getElementById('vat' + row).value = parseFloat($("#mVat").val());
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

function addRow(row) {
    var count = parseInt($("#countPaymentDetail").val());
    if (count === (parseInt(row) + 1)) {
        addRowPaymentDetailTable(count);
    }
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
            data: {paymentOutboundDetailId: id},
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

function searchPvNo() {
    var action = document.getElementById('action');
    action.value = 'search';
    document.getElementById('PaymentOutboundForm').submit();
}

function checkRefNo(row) {
    var list = document.getElementById('refNoList').value;
    var refNo = document.getElementById('refNo' + row).value;

    if (refNo === '') {
        var refNoField = document.getElementById('refNo' + row);
        refNoField.style.borderColor = "";
        $("#btnSave").removeClass("disabled");
        return;
    }

    list = list.replace("[", "");
    list = list.replace("]", "");
    list = list.replace(/ /g, "");

    var refNo_list = list.split(',');
    for (var i = 0; i <= refNo_list.length; i++) {
        if (String(refNo) === String(refNo_list[i])) {
            var refNoField = document.getElementById('refNo' + row);
            refNoField.style.borderColor = "Green";
            $("#btnSave").removeClass("disabled");
            return;
        } else {
            var refNoField = document.getElementById('refNo' + row);
            refNoField.style.borderColor = "Red";
            $("#btnSave").addClass("disabled");
        }
    }
}

function calculateGross(row) {
    var cost = document.getElementById('cost' + row).value;
    var vatData = parseFloat(document.getElementById('vat' + row).value);
    var isVat = document.getElementById('isVat' + row).checked;
    var mVat = document.getElementById('mVat').value;

    cost = cost.replace(/,/g, "");
    var amountTotal = parseFloat(cost);
    var vatTotal = parseFloat(vatData);

    if (isVat) {
        amountTotal = (cost * (100 / (100 + vatData)));
        document.getElementById('gross' + row).value = formatNumber(amountTotal);
        document.getElementById('vatShow' + row).innerHTML = vatTotal;

    } else {
        document.getElementById('gross' + row).value = '0.00';
        document.getElementById('vatShow' + row).innerHTML = '';
        document.getElementById('vat' + row).value = mVat;

    }
    calculateGrossTotal();
}


function setFormatNumber(type, row) {
    if (type === 'cost') {
        var cost = document.getElementById('cost' + row).value;
        var isVat = document.getElementById('isVat' + row).checked;
        if (cost !== '') {
            document.getElementById('cost' + row).value = formatNumber(parseFloat(cost.replace(/,/g, "")));
        }
        if (isVat) {
            calculateGross(row);
        }
    }
    if (type === 'comm') {
        var comm = document.getElementById('comm' + row).value;
        if (comm !== '') {
            document.getElementById('comm' + row).value = formatNumber(parseFloat(comm.replace(/,/g, "")));
        }
    }
    if (type === 'value') {
        var value = document.getElementById('value' + row).value;
        if (value !== '') {
            document.getElementById('value' + row).value = formatNumber(parseFloat(value.replace(/,/g, "")));
        }
    }
    if (type === 'amount') {
        var amount = document.getElementById('amount' + row).value;
        if (amount !== '') {
            document.getElementById('amount' + row).value = formatNumber(parseFloat(amount.replace(/,/g, "")));
        }
        calculateGrandTotal();
    }
}

function calculateGrossTotal() {
    var count = parseInt(document.getElementById('countPaymentDetail').value);
    var i;
    var grossTotal = 0;
    for (i = 1; i < count + 1; i++) {
        var gross = document.getElementById("gross" + i);
        if (gross !== null) {
            var value = gross.value;
            if (value !== '') {
                value = value.replace(/,/g, "");
                var total = parseFloat(value);
                grossTotal += total;

            }
        }
    }
    document.getElementById('grossTotal').value = formatNumber(grossTotal);
    calculateVatTotal();
}

function calculateVatTotal() {
    var grandTotal = parseFloat($("#grandTotal").val().replace(/,/g, ""));
    var grossTotal = parseFloat($("#grossTotal").val().replace(/,/g, ""));
    $("#vatTotal").val(formatNumber(grandTotal - grossTotal));
}

function calculateGrandTotal() {
    var count = parseInt(document.getElementById('countPaymentDetail').value);
    var i;
    var grandTotal = 0;
    for (i = 1; i < count + 1; i++) {
        var amount = document.getElementById("amount" + i);
        if (amount !== null) {
            var value = amount.value;
            if (value !== '') {
                value = value.replace(/,/g, "");
                var total = parseFloat(value);
                grandTotal += total;

            }
        }
    }
    document.getElementById('grandTotal').value = formatNumber(grandTotal);
    calculateVatTotal();
}

function addRefNo(refNo, type, description, billType, cost, cur, bookId) {
    var countPaymentDetail = parseInt($("#countPaymentDetail").val());
    var count = 0;
    for (var i = 1; i < countPaymentDetail-1; i++) {
        var countTemp = document.getElementById("count" + i);
        if (countTemp !== null) {
            count = parseInt(countTemp.value);
        }
    }
    addRowPaymentDetailTableByRefNo(refNo, type, description, billType, parseFloat(cost), cur, bookId, count + 1);
}

function addRowPaymentDetailTableByRefNo(refNo, type, description, billType, cost, cur, bookId, row) {
    var color = (row % 2 === 0 ? "#F2F2F2" : "");
    if (!row) {
        row = 1;
    }

    $("#count" + row).val(row);
    $("#bookDetailId" + row).val(bookId);
    $("#bookDetailType" + row).val(billType);
    $("#refNo" + row).val(refNo);
    $("#description" + row).val(description);
    $("#cost" + row).val(formatNumber(cost));
    $("[name=type" + row + "] option").filter(function() {
        return ($(this).text() === type);
    }).prop('selected', true);
    $("[name=cur" + row + "] option").filter(function() {
        return ($(this).text() === cur);
    }).prop('selected', true);
    
    row = row + 1;

    $("#PaymentDetailTable tbody").append(
            '<tr >' +
            '<td class="hidden">' +
            '<input type="text" name="count' + row + '" id="count' + row + '" class="form-control" value="' + row + '"/>' +
            '<input type="text" name="detailId' + row + '" id="detailId' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="payId' + row + '" id="payId' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="bookDetailId' + row + '" id="payId' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="bookDetailType' + row + '" id="bookDetailType' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="accCode' + row + '" id="accCode' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="exportDate' + row + '" id="exportDate' + row + '" class="form-control" value=""/>"/>' +
            '<input type="text" name="isExport' + row + '" id="isExport' + row + '" class="form-control" value=""/>' +
            '</td>' +
            '<td>' +
            '<select class="form-control" name="type' + row + '" id="type' + row + '" onchange="addRow(\'' + row + '\')">' +
            '<option  value="" ></option>' +
            '</select>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="refNo' + row + '" id="refNo' + row + '" class="form-control" onfocusout="checkRefNo(\'' + row + '\')" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="invoice' + row + '" id="invoice' + row + '" class="form-control" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="cost' + row + '" id="cost' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" onfocusout="setFormatNumber(\'cost\',\'' + row + '\')" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="gross' + row + '" id="gross' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" value="" readonly=""/>' +
            '</td>' +
            '<td align="center">' +
            '<input type="checkbox" id="isVat' + row + '" name="isVat' + row + '" onclick="calculateGross(\'' + row + '\')" value="">' +
            '</td>' +
            '<td align="right" id="vatShow' + row + '"></td>' +
            '<td class="hidden">' +
            '<input type="text" id="vat' + row + '" name="vat' + row + '" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="amount' + row + '" id="amount' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" onfocusout="setFormatNumber(\'amount\',\'' + row + '\')" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="comm' + row + '" id="comm' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" onfocusout="setFormatNumber(\'comm\',\'' + row + '\')" value=""/>' +
            '</td>' +
            '<td>' +
            '<select class="form-control" name="cur' + row + '" id="cur' + row + '" onchange="addRow(\'' + row + '\')">' +
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
            '<input type="text" name="value' + row + '" id="value' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" onfocusout="setFormatNumber(\'value\',\'' + row + '\')" value=""/>' +
            '</td>' +
            '</tr>'

            );
    $("#tr_PaymentOutboundDetailAddRow").removeClass("show");
    $("#tr_PaymentOutboundDetailAddRow").addClass("hide");
    $("#typeClone option").clone().appendTo("#type" + row);
    $("#curClone option").clone().appendTo("#cur" + row);
//    document.getElementById('vatShow' + row).innerHTML = parseFloat($("#mVat").val());
    document.getElementById('vat' + row).value = parseFloat($("#mVat").val());
    $("#countPaymentDetail").val(row + 1);

}