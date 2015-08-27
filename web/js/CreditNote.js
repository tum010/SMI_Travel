var rowIndex;
var taxNoShow = "";
$(document).ready(function () {
    var inputDate = $("#inputDate").val();
    if (inputDate === "") {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1; //January is 0!
        var yyyy = today.getFullYear();
        if (dd < 10) {
            dd = '0' + dd
        }
        if (mm < 10) {
            mm = '0' + mm
        }
        today = yyyy + "-" + mm + "-" + dd;
        $("#inputDate").val(today);
    }

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
        var result = true;
        $('#CreditNoteForm').bootstrapValidator('revalidateField', 'name');
        $('#CreditNoteForm').bootstrapValidator('revalidateField', 'inputDate');
        if ($("#inputDate").val() === "" || $("#name").val() === "") {
            return;
        }
        var action = document.getElementById('action');
        action.value = 'save';
        document.getElementById('CreditNoteForm').submit();
    });

    addRow();
    validFrom();
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
                vatAmount.val((realAmount * 100 / (vat + 100)) * vat / 100);
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
    $("#delCode").html("are you sure to delete tax invoice no " + row.find("[name='taxNo']").val() + "?")
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
    var cnNo = $("#cnNo").val();
    $("#titleVoidModel").html("Void Credit Note");
    $("#voidCode").html("Are you confirm to void credit note " + cnNo + "?");
    $("#voidBtn").attr('onclick', 'submitVoid(2)');
    $("#voidBtn").text("Void");
}

function disableVoid() {
    var cnNo = $("#cnNo").val();
    $("#titleVoidModel").html("Canle Void Credit Note");
    $("#voidCode").html("Are you confirm to cancel void credit note " + cnNo + "?");
    $("#voidBtn").attr('onclick', 'submitVoid(1)');
    $("#voidBtn").text("Cancel void");
}

function submitVoid(status) {
    var action = document.getElementById('action');
    action.value = 'void';
    $('#CreditNoteForm').append('<input type="hidden" name="status" value="' + status + '" /> ');
    document.getElementById('CreditNoteForm').submit();
}

function show(taxNo) {
    if (taxNoShow === "" || taxNoShow !== taxNo) {
        taxNoShow = taxNo;
        var url = 'AJAXServlet';
        var servletName = 'TaxInvoiceServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&type=getTaxInvoice' +
                '&invoiceNo=' + taxNo;
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

                    var table = $("#taxDetail tbody");
                    table.html("");
                    for (var i = 0; i < tax.detailList.length; i++) {
                        var detail = tax.detailList[i];
                        var html = "<tr>" +
                                "<td style='text-align:center'>" + (i + 1) + "</td>" +
                                "<td style='text-align:center'>" + detail.product + "</td>" +
                                "<td style='text-align:center'>" + detail.refNo + "</td>" +
                                "<td style='text-align:center'>" + detail.description + "</td>" +
                                "<td style='text-align:center'>" + detail.amount + "</td>" +
                                "<td style='text-align:center'>" + detail.cur + "</td>" +
                                "</tr>";
                        table.append(html);
                    }
                    $('.collapse').collapse('show');

                }, error: function (msg) {
                }
            });
        } catch (e) {
            alert(e);
        }
    } else {
        taxNoShow = "";
        $('.collapse').collapse('hide');
    }
}

function openReport() {
    var cnId = $("#cnId").val();
    window.open("report.smi?name=CreditNoteReport&cnid=" + cnId);
}

function sendMail() {
    var cnId = $("#cnId").val();
    window.open("SendMail.smi?name=CreditNote&reportid=" + cnId);
}

function validFrom() {
    // Validator Date From and To 
    $("#CreditNoteForm")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    name: {
                        trigger: 'focus keyup change',
                        validators: {
                            notEmpty: {
                                message: 'Please fill name'
                            }
                        }
                    },
                    inputDate: {
                        trigger: 'focus keyup change',
                        validators: {
                            notEmpty: {
                                message: 'Please fill date'
                            }
                        }
                    }
                }
            });

}