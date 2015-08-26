var rowIndex;

$(document).ready(function () {
    $("#cnNo").on("keyup", function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            if ($("#cnNo").val() === "") {
                return;
            }
            var action = document.getElementById('action');
            action.value = 'search';
            document.getElementById('CreditNoteForm').submit();
        }
    });
    $("#buttonSearch").click(function () {
        if ($("#cnNo").val() === "") {
            return;
        }
        var action = document.getElementById('action');
        action.value = 'search';
        document.getElementById('CreditNoteForm').submit();
    });
    $("#buttonSave").click(function () {
        var action = document.getElementById('action');
        action.value = 'save';
        document.getElementById('CreditNoteForm').submit();
    });

    addRow();
});

function addRow() {
    var clone = $('#tempTable tbody tr:lt(2)').clone();
    $('#ItemCreditTable tbody').append(clone);
    $("input[name='taxNo']").each(function () {
        $(this).off();
        $(this).on("keyup", function (event) {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13') {
                getTaxInv(this);
            }
        });
    });

    $("input[name='taxReal']").each(function () {
        $(this).off();
        $(this).on("keyup", function (event) {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13') {
                var realAmount = this.value.replace(",", "");
                var vatAmount = $(this).parent().parent().find("[name='taxVat']");
                vatAmount.val(realAmount * vat / 100);
            }
        });
    });

    $(".decimal").inputmask({
        alias: "decimal",
        integerDigits: 8,
        groupSeparator: ',',
        autoGroup: true,
        digits: 2,
        allowMinus: false,
        digitsOptional: false,
        placeholder: "0.00",
    });
}

function getTaxInv(input) {
    var url = 'AJAXServlet';
    var servletName = 'TaxInvoiceServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&type=getTaxInvoice' +
            '&invoiceNo=' + input.value;
//    var row = parseInt($(input).parent().parent().attr("row"));
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            beforeSend: function () {
                $("#dataload").removeClass("hidden");
            },
            success: function (msg) {
                console.log("getAutoListBillto ==" + msg);
                var tax = JSON.parse(msg);

                var taxDate = $(input).parent().parent().find("[name='taxDate']");
                var amount = $(input).parent().parent().find("[name='taxAmount']");
                var desc = $(input).parent().parent().find("[name='taxDesc']");
                var taxId = $(input).parent().parent().find("[name='taxId']");
                $("#apCode").val(tax.taxTo);
                $("#name").val(tax.taxName);
                $("#address").val(tax.taxAddress);
                taxDate.val(tax.taxDate);
                amount.val(tax.taxAmount);
                desc.val(tax.taxDesc);
                taxId.val(tax.taxId);
                var index = $(input).parent().parent().index();
                var count = $('#ItemCreditTable tbody tr').length;
                if (index == (count - 1)) {
                    addRow();
                }
            }, error: function (msg) {
                $("#alertTextFail").html("Cannot find tax invoice " + ticketNo);
                $("#alertFail").show();
                $("#alertSuccess").hide();
            }
        });
    } catch (e) {
        alert(e);
    }
}

function setDeletRow(btn) {
    var row = $(btn).parent().parent()
    rowIndex = row.index();
    console.log(rowIndex);
    var taxId = row.find("[name='taxId']").val();
    if (taxId !== "") {

    } else {
        row.remove();
        $("#alertTextSuccess").html("Delete success.");
        $("#alertSuccess").show();
        $("#alertFail").hide();
    }
}

function deleteCreditNotedetail() {
    var row = $('#ItemCreditTable tbody tr:nth-child(' + (rowIndex + 1) + ')')
    var id = row.find("[name='id']").val();
    var url = 'AJAXServlet';
    var servletName = 'CreditNoteServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&type=delete' +
            '&cnDetailId=' + id;
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            beforeSend: function () {
            },
            success: function (msg) {
                var result = JSON.parse(msg);
                if (result) {
                    row.remove();
                    $("#alertTextSuccess").html("Delete success.");
                    $("#alertSuccess").show();
                    $("#alertFail").hide();
                } else {
                    $("#alertTextFail").html("Delete Fail.");
                    $("#alertFail").show();
                    $("#alertSuccess").hide();
                }

            }, error: function (msg) {
                console.log('auto ERROR');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function enableVoid() {
    $("#titleVoidModel").html("Void Credit Note");
    $("#voidCode").html("Are you confirm to void credit note {CN No}?");
    $("#voidBtn").attr('onclick', 'submitVoid(2)');
}

function disableVoid() {
    $("#titleVoidModel").html("Canle Void Credit Note");
    $("#voidCode").html("{Are you confirm to cancel voice  credit note  {CN No}?");
    $("#voidBtn").attr('onclick', 'submitVoid(1)');
}

function submitVoid(status) {
    var action = document.getElementById('action');
    action.value = 'void';
    $('#CreditNoteForm').append('<input type="hidden" name="status" value="' + status +'" /> ');
    document.getElementById('CreditNoteForm').submit();
}