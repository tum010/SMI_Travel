var rowIndex;
var taxNoShow = "";
$(document).ready(function() {
    $('.datemask').mask('0000-00-00');
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

    var wildCardSearch = ($("#wildCardSearch").val()).indexOf("%");
//    if ($("#cnId").val() !== '') {
        $("#cnNo").focus();
//    }
    $("#cnNo").on("keyup", function(event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') {
            if ($("#cnNo").val() === "") {
                return;
            }
            var action = document.getElementById('action');
            action.value = 'search';
            document.getElementById('CreditNoteForm').submit();
        } else if (keycode == '38') {
            if ((parseInt(wildCardSearch) >= 0) || ($("#cnId").val() !== '')) {
                $("#keyCode").val(keycode);
                var action = document.getElementById('action');
                action.value = 'wildCardSearch';
                document.getElementById('CreditNoteForm').submit();
            }

        } else if (keycode == '40') {
            if ((parseInt(wildCardSearch) >= 0) || ($("#cnId").val() !== '')) {
                $("#keyCode").val(keycode);
                var action = document.getElementById('action');
                action.value = 'wildCardSearch';
                document.getElementById('CreditNoteForm').submit();
            }

        } else if (keycode == '118') {
            $("#keyCode").val(keycode);
            var action = document.getElementById('action');
            action.value = 'new';
            document.getElementById('CreditNoteForm').submit();

        } else if (keycode == '119') {
            $("#keyCode").val(keycode);
            var action = document.getElementById('action');
            action.value = 'wildCardSearch';
            document.getElementById('CreditNoteForm').submit();

        }

    });
    $("#buttonSearch").click(function() {
        if ($("#cnNo").val() === "") {
            return;
        }
        var action = document.getElementById('action');
        action.value = 'search';
        document.getElementById('CreditNoteForm').submit();
    });
    $("#buttonSave").click(function() {
        var result = true;
        $('#CreditNoteForm').bootstrapValidator('revalidateField', 'name');
        $('#CreditNoteForm').bootstrapValidator('revalidateField', 'inputDate');
        $('#CreditNoteForm').bootstrapValidator('revalidateField', 'address');
        if ($("#inputDate").val() === "" || $("#name").val() === "" || $("#address").val() === "") {
            return;
        }
//        $('#ItemCreditTable tbody [name="taxId"]').each(function() {
//            var taxNo = $(this).parent().parent().find("[name='taxNo']");
//            if (taxNo.val() !== '' && this.value === '') {
//                taxNo.style.borderColor = "red";
//                result = false;
//            }
//        });
        $('#ItemCreditTable tbody [name="taxNo"]').each(function() {
            var taxId = $(this).parent().parent().find("[name='taxId']");
            if(this.value !== '' && taxId.val() === ''){
                this.value = '';
                this.style.borderColor = "red";
                $("#alertTextFail").html("Please press enter when fill in complete tax invoice no.");
                $("#alertFail").show();
                result = false;
            }
            if (this.style.borderColor === "red") {
                result = false;
            }
        });
        $('#ItemCreditTable tbody [name="taxReal"]').each(function() {
            if (this.style.borderColor === "red") {
                result = false;
                
            }else if(this.value !== ''){
                var taxRealCheck = $(this).parent().parent().find("[name='taxRealCheck']");
                if(parseFloat((this.value).replace(",","")) > parseFloat((taxRealCheck.val()).replace(",",""))){
                    this.style.borderColor = "red";
                    result = false;
                }
            }
        });
        if(result){
            var action = document.getElementById('action');
            action.value = 'save';
            document.getElementById('CreditNoteForm').submit();
        }    
    });

    $("#buttonNew").click(function() {
        var action = document.getElementById('action');
        action.value = 'new';
        document.getElementById('CreditNoteForm').submit();
    });

    addRow();
    validFrom();

});

function addRow() {
    $("#addRow").removeClass("show");
    $("#addRow").addClass("hide");
    var clone = $('#tempTable tbody tr:lt(2)').clone();
    $('#ItemCreditTable tbody').append(clone);
    $("input[name='taxNo']").each(function() {
        $(this).off();
        $(this).on("keyup", function(event) {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13') {
                getTaxInv(this);
            }
        });
    });

    $("input[id='taxReal']").each(function() {
        $(this).off();
        $(this).on("keyup", function(event) {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13') {
                var creDetailId = $(this).parent().parent().find("[name='id']");
                var taxNo = $(this).parent().parent().find("[name='taxNo']");
                if(taxNo.val() === ''){
                   taxNo.css("border-color", "red"); 
                   return;
                }
                var realAmountBorder = this.style;
                var realAmount = this.value.replace(",", "");
                var realAmountHidden = $(this).parent().parent().find("[name='taxReal']");
                var taxRealCheck = $(this).parent().parent().find("[name='taxRealCheck']");
                var vatAmount = $(this).parent().parent().find("[name='taxVat']");
                var vatAmountID = $(this).parent().parent().find("[id='taxVat']");
                var vatAmountReal = $(this).parent().parent().find("[name='taxVatReal']");
                var vatAmountRealID = $(this).parent().parent().find("[id='taxVatReal']");
                var url = 'AJAXServlet';
                var servletName = 'TaxInvoiceServlet';
                var servicesName = 'AJAXBean';
                var department = $("#department").val();
                var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&type=getTaxInvoiceAmountTotal' +
                        '&invoiceNo=' + taxNo.val() +
                        '&id=' + creDetailId.val() +
                        '&department=' + department;
                try {
                    $.ajax({
                        type: "POST",
                        url: url,
                        cache: false,
                        data: param,
                        beforeSend: function() {
                            $("#dataload").removeClass("hidden");
                        },
                        success: function(msg) {
                            if(msg === "null"){
                                realAmountBorder.borderColor = "Red";
                                $("#alertTextFail").html("Tax Invoice "+ taxNo.val() +" Amount Total much over.");
                                $("#alertFail").show();
                                return;
                            }
                            var amountTotal = msg;
//                            var realAmount = this.value.replace(",", "");
//                            var realAmountHidden = $(this).parent().parent().find("[name='taxReal']");
//                            var taxRealCheck = $(this).parent().parent().find("[name='taxRealCheck']");
                            
//                            if(parseFloat(realAmount) <= parseFloat((taxRealCheck.val()).replace(",", ""))){
                            if(parseFloat(realAmount) <= parseFloat(amountTotal)){
                                realAmountHidden.val(realAmount);
//                                var vatAmount = $(this).parent().parent().find("[name='taxVat']");
//                                var vatAmountID = $(this).parent().parent().find("[id='taxVat']");
                                var calVat = realAmount - (realAmount * 100 / (vat + 100));
                                vatAmount.val(parseFloat(calVat.toFixed(2)));
                                vatAmountID.val(Math.round(calVat * 100) / 100);
                                vatAmountReal.val(parseFloat(calVat.toFixed(4)));
                                vatAmountRealID.val(parseFloat(calVat.toFixed(4)));
//                                this.style.borderColor = "Green";
                                realAmountBorder.borderColor = "Green";

                            }else if(realAmount === ''){
                                realAmountHidden.val(realAmount);
//                                this.style.borderColor = "";
                                realAmountBorder.borderColor = "";    

                            }else{
                                realAmountHidden.val(realAmount);
//                                this.style.borderColor = "Red"; 
                                realAmountBorder.borderColor = "Red";
                            }

                        }, error: function(msg) {

                        }
                    });
                } catch (e) {
                    alert(e);
                }
//                var realAmount = this.value.replace(",", "");
//                var realAmountHidden = $(this).parent().parent().find("[name='taxReal']");
//                var taxRealCheck = $(this).parent().parent().find("[name='taxRealCheck']");
//                if(parseFloat(realAmount) <= parseFloat((taxRealCheck.val()).replace(",", ""))){
//                    realAmountHidden.val(realAmount);
//                    var vatAmount = $(this).parent().parent().find("[name='taxVat']");
//                    var vatAmountID = $(this).parent().parent().find("[id='taxVat']");
//                    var calVat = realAmount - (realAmount * 100 / (vat + 100));
//                    vatAmount.val(calVat);
//                    vatAmountID.val(Math.round(calVat * 100) / 100);
//                    this.style.borderColor = "Green"; 
//                
//                }else if(realAmount === ''){
//                    realAmountHidden.val(realAmount);
//                    this.style.borderColor = ""; 
//                
//                }else{
//                    realAmountHidden.val(realAmount);
//                    this.style.borderColor = "Red"; 
//                }    
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

function callAjaxAmountTotalTaxInvoice(url,param){
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            beforeSend: function() {
                $("#dataload").removeClass("hidden");
            },
            success: function(msg) {
                if(msg !== "null"){
                    return parseFloat(msg);
                }else{
                    return 0;
                }
                
            }, error: function(msg) {
                
            }
        });
    } catch (e) {
        alert(e);
    }
}

function getTaxInv(input) {
    input.style.removeProperty('border');
    $("#alertFail").hide();
    $("#alertSuccess").hide();
    var ticketNo = ""
    ticketNo = input.value;
    var duplicate = false;
    var count = 0;
    var length = $('#ItemCreditTable tbody tr').length;
    if(input.value !== ''){   
        $('#ItemCreditTable tbody [name="taxNo"]').each(function() {
//            alert("this.value : "+this.value+" input.value : "+input.value);
            if (this.value === input.value) {
                count++;
    //            input.style.borderColor = "Red";
    //            $("#alertTextFail").html("Duplicated tax invoice no " + ticketNo);
    //            $("#alertFail").show();
    //            duplicate = true;
    //            return;
            }
        });
//        alert("count : "+count+" length : "+length);
        if(count > 1){
            input.style.borderColor = "Red";
            $("#alertTextFail").html("Duplicated tax invoice no " + ticketNo);
            $("#alertFail").show();
            duplicate = true;
        }
//        alert(duplicate);
        if (!duplicate) {
            var url = 'AJAXServlet';
            var servletName = 'TaxInvoiceServlet';
            var servicesName = 'AJAXBean';
            var department = $("#department").val();
            var param = 'action=' + 'text' +
                    '&servletName=' + servletName +
                    '&servicesName=' + servicesName +
                    '&type=getTaxInvoice' +
                    '&invoiceNo=' + ticketNo +
                    '&department=' + department;
            //    var row = parseInt($(input).parent().parent().attr("row"));
            try {
                $.ajax({
                    type: "POST",
                    url: url,
                    cache: false,
                    data: param,
                    beforeSend: function() {
                        $("#dataload").removeClass("hidden");
                    },
                    success: function(msg) {
                        var tax = JSON.parse(msg);
                        if (tax.status === "1") {
                            var taxDate = $(input).parent().parent().find("[name='taxDate']");
                            var amount = $(input).parent().parent().find("[name='taxAmount']");
                            var amountId = $(input).parent().parent().find("[id='taxAmount']");
                            var amountReal = $(input).parent().parent().find("[name='taxAmountReal']");
                            var amountRealId = $(input).parent().parent().find("[id='taxAmountReal']");
                            var desc = $(input).parent().parent().find("[name='taxDesc']");
                            var taxId = $(input).parent().parent().find("[name='taxId']");
                            var btnDetail = $(input).parent().parent().find("[name='btnDetail']");
                            var amountTotal = $(input).parent().parent().find("[name='taxRealCheck']");
                            var amountTotalId = $(input).parent().parent().find("[id='taxRealCheck']");
                            $("#apCode").val(tax.taxTo);
                            $("#name").val(tax.taxName);
                            $("#address").val(tax.taxAddress);
                            taxDate.val(tax.taxDate);
                            amount.val(parseFloat((tax.taxAmount).toFixed(2)));
                            amountId.val(parseFloat((tax.taxAmount).toFixed(2)));
                            amountReal.val(tax.taxAmount);
                            amountRealId.val(tax.taxAmount);
                            desc.val(tax.taxDesc);
                            taxId.val(tax.taxId);
                            btnDetail.attr('onclick', "show('" + ticketNo + "')");
                            amountTotal.val(tax.amountTotal);
                            amountTotalId.val(tax.amountTotal)
                            var index = $(input).parent().parent().index();
                            var count = $('#ItemCreditTable tbody tr').length;
                            if (index == (count - 1)) {
                                addRow();
                            }
                        } else {

                            $("#alertTextFail").html("Tax invoice no " + ticketNo + " has been void!");
                            $("#alertFail").show();
                            $("#alertSuccess").hide();
                        }
                    }, error: function(msg) {
                        input.style.borderColor = "Red";
                        $("#alertTextFail").html("Cannot find tax invoice no " + ticketNo);
                        $("#alertFail").show();
                        $("#alertSuccess").hide();
                    }
                });
            } catch (e) {
                alert(e);
            }
        }
    }    
}

function setDeletRow(btn) {
    var row = $(btn).parent().parent()
    rowIndex = row.index();
    console.log(rowIndex);
    $("#delCode").html("are you sure to delete tax invoice no " + row.find("[name='taxNo']").val() + "?")
    var id = row.find("[name='id']").val();
    if (id !== "") {

    } else {
        row.remove();
        if ($('#ItemCreditTable tbody tr').length < 1) {
            $("#addRow").removeClass("hide");
            $("#addRow").addClass("show");
        }
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
            beforeSend: function() {
            },
            success: function(msg) {
                var result = JSON.parse(msg);
                if (result) {
                    row.remove();
                    $("#alertTextSuccess").html("Delete success.");
                    $("#alertSuccess").show();
                    $("#alertFail").hide();
                    $('#DeleteDetail').modal('hide');
                    if ($('#ItemCreditTable tbody tr').length < 1) {
                        $("#addRow").removeClass("hide");
                        $("#addRow").addClass("show");
                    }
                } else {
                    $("#alertTextFail").html("Delete Fail.");
                    $("#alertFail").show();
                    $("#alertSuccess").hide();
                    $('#DeleteDetail').modal('hide');
                }

            }, error: function(msg) {
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
        var department = $("#department").val();
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&type=getTaxInvoice' +
                '&invoiceNo=' + taxNo +
                '&department=' + department;
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                beforeSend: function() {
                    $("#dataload").removeClass("hidden");
                },
                success: function(msg) {
                    console.log("getAutoListBillto ==" + msg);
                    var tax = JSON.parse(msg);

                    var table = $("#taxDetail tbody");
                    table.html("");
                    for (var i = 0; i < tax.detailList.length; i++) {
                        var detail = tax.detailList[i];
                        var html = "<tr>" +
                                "<td style='text-align:center'>" + (i + 1) + "</td>" +
                                "<td style='text-align:center'>" + detail.product + "</td>" +
                                "<td style='text-align:center'>" + (detail.refNo !== undefined ? detail.refNo : '') + "</td>" +
                                "<td>" + (detail.description !== null ? detail.description : '') + "</td>" +
                                "<td style='text-align:right'>" + (detail.amount !== '' && detail.amount !== null ? detail.amount : '0.00') + "</td>" +
                                "<td style='text-align:center'>" + detail.cur + "</td>" +
                                "</tr>";
                        table.append(html);
                    }
                    $('.collapse').collapse('show');

                }, error: function(msg) {
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
    window.open("SendMail.smi?reportname=CreditNote&reportid=" + cnId);
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
                        trigger: 'keyup change',
                        validators: {
                            notEmpty: {
                                message: 'Please fill name'
                            }
                        }
                    },
                    inputDate: {
                        trigger: 'keyup change',
                        validators: {
                            notEmpty: {
                                message: 'Please fill date'
                            }
                        }
                    },
                    address: {
                        trigger: 'keyup change',
                        validators: {
                            notEmpty: {
                                message: 'Please fill address'
                            }
                        }
                    }
                }
            });

}

function hideAlert(){
    $("#alertSuccess").hide();
    $("#alertFail").hide(); 
}