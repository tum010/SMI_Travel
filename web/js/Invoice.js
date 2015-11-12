/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. test
 */
$(document).ready(function() {
    $(".money").mask('0000000000', {reverse: true});

    $(".numerical").on('input', function() {
        var value = $(this).val().replace(/[^0-9.,]*/g, '');
        value = value.replace(/\.{2,}/g, '.');
        value = value.replace(/\.,/g, ',');
        value = value.replace(/\,\./g, ',');
        value = value.replace(/\,{2,}/g, ',');
        value = value.replace(/\.[0-9]+\./g, '.');
        $(this).val(value);
    });

    // Invoice To Modal
    var showflag = 1;
    var ReceiveFromTable = $('#InvToTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    $('#InvToTable tbody').on('click', 'tr', function() {
        $('.collapse').collapse('show');
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
        }
        else {
            ReceiveFromTable.$('tr.row_selected').removeClass('row_selected');
            var staff_code = $(this).find("td").eq(0).html();
            var staff_name = $(this).find("td").eq(1).html();
            var staff_dress = $(this).find("td").eq(2).html();
//            alert("Herree" + staff_code);
            $("#InvTo").val(staff_code);
            $("#InvToName").val(staff_name);
            $("#InvToAddress").val(staff_dress);
            $("#ARCode").val(staff_code);
            $(this).addClass('row_selected');
        }
    });

    $("#searchInvoiceFrom").keyup(function(event) {
        if (event.keyCode === 13) {
            if ($("#searchInvoiceFrom").val() == "") {
                // alert('please input data');
            }
            searchCustomerAgentList($("#searchInvoiceFrom").val());
        }
    });

    //autocomplete
    $("#InvTo").keyup(function(event) {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        if ($(this).val() === "") {
            $("#InvToId").val("");
            $("#InvToName").val("");
            $("#InvToAddress").val("");
        } else {
            if (event.keyCode === 13) {
                searchCustomerAutoList(this.value);
            }
        }
    });
    $("#InvTo").keydown(function() {

        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        if (showflag == 0) {
            $(".ui-widget").css("top", -1000);
            showflag = 1;
        }
    });

    // Sale Staff Modal
    var staffCode = [];
    $("#SaleStaffTable tr").on('click', function() {//winit
        $("#SaleStaffModal").modal('hide');
        var staff_id = $(this).find(".staff-id").html();
        var staff_code = $(this).find(".staff-code").html();
        var staff_name = $(this).find(".staff-name").html();
        $("#SaleStaffId").val(staff_id);
        $("#SaleStaffCode").val(staff_code);
        $("#SaleStaffName").val(staff_name);
    });
    var staffTable = $('#SaleStaffTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });

    $('#SaleStaffTable tbody').on('click', 'tr', function() {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
        }
        else {
            staffTable.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }
    });

    $(function() {
        var availableTags = [];
        $.each(staff, function(key, value) {
            availableTags.push(value.code);
            if (!(value.name in availableTags)) {
                availableTags.push(value.name);
            }
        });

        $("#SaleStaffCode").autocomplete({
            source: availableTags,
            close: function(event, ui) {
                $("#SaleStaffCode").trigger('keyup');
            }
        });

        $("#SaleStaffCode").keyup(function() {
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var name = this.value;
            var code = this.value.toUpperCase();
            $("#SaleStaffName").val(null);
            $.each(staff, function(key, value) {
                if (name === value.name.toUpperCase()) {
                    $("#SaleStaffId").val(value.id);
                    $("#SaleStaffCode").val(value.code);
                    $("#SaleStaffName").val(value.name);
                }
                if (value.code.toUpperCase() === code) {
                    $("#SaleStaffId").val(value.id);
                    $("#SaleStaffCode").val(value.code);
                    $("#SaleStaffName").val(value.name);
                }
            });
        });
    });

    // Add Row Auto key
    $("#DetailBillableTable").on("keyup", function() {
        var rowAll = $("#DetailBillableTable tr").length;
        $("td").keyup(function() {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#DetailBillableTable tr").length;
                if (rowIndex == rowAll) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    AddRowDetailBillAble(rowAll);
                }
            }
        });
    });
    // Add Row Detail Bill Able
    var countertable = $("#counterTable").val();

    // get row in table now
    var rowCount = $('#DetailBillableTable tr').length;
    $("#counterTable").val(rowCount);
    AddRowDetailBillAble(rowCount++);

    validFromInvoice();

    // Invoice No Key Up
    var wildCardSearch = ($("#wildCardSearch").val()).indexOf("%");
    if ($("#InvoiceId").val() !== '') {
        $("#InvNo").focus();
    }
    $("#InvNo").keyup(function(event) {
        if (event.keyCode === 13) {
            searchInvoiceFromInvoiceNo();
        } else if (event.keyCode === 38) {
            if ((parseInt(wildCardSearch) >= 0) || ($("#InvoiceId").val() !== '')) {
                $("#keyCode").val(event.keyCode);
                var action = document.getElementById('action');
                action.value = 'wildCardSearch';
                document.getElementById('InvoiceForm').submit();
            }

        } else if (event.keyCode === 40) {
            if ((parseInt(wildCardSearch) >= 0) || ($("#InvoiceId").val() !== '')) {
                $("#keyCode").val(event.keyCode);
                var action = document.getElementById('action');
                action.value = 'wildCardSearch';
                document.getElementById('InvoiceForm').submit();
            }

        } else if (event.keyCode === 118) {
            $("#keyCode").val(event.keyCode);
            var action = document.getElementById('action');
            action.value = 'new';
            document.getElementById('InvoiceForm').submit();

        } else if (event.keyCode === 119) {
            $("#keyCode").val(event.keyCode);
            var action = document.getElementById('action');
            action.value = 'wildCardSearch';
            document.getElementById('InvoiceForm').submit();

        }
    });

    CalculateGrandTotal('');
    var counter = $('#DetailBillableTable tbody tr').length;
    for (var j = 1; j <= (counter - 1); j++) {
        changeFormatCostNumber(j);
        changeFormatCostLocalNumber(j);
        changeFormatAmountNumber(j);
        changeFormatAmountLocalNumber(j);

    }

    var invoiceNumber = $('#InvNo').val();
    if (invoiceNumber === '') {
        document.getElementById("printButton").disabled = true;
        document.getElementById("sendEmailButton").disabled = true;
    }

    //autocomplete
    $("#SearchRefNo").keyup(function(event) {
        if (event.keyCode === 13) {
            searchAction();
        }
    });

});

function searchInvoiceFromInvoiceNo() {
    var action = document.getElementById('action');
    action.value = 'searchInvoice';
    document.getElementById('InvoiceForm').submit();
}

function searchInvoice() {

}

var currency = 0;
function validFromInvoice() {


    var counter = $('#DetailBillableTable tbody tr').length;
    var different = 0;
    var rowTemp = 0;
    var checkcur1 = false;
    for (var i = 1; i <= (counter - 1); i++) {
        var currency1 = $('#SelectCurrencyAmount' + i).find(":selected").text();
        if (currency1 === '') {
            checkcur1 = true;
        } else {
            $('#textAlertCurrencyAmountNotEmpty').hide();
        }
        for (var j = 2; j <= (counter - 1); j++) {
            var type = $('#SelectProductType' + j).find(":selected").text();
            if (type !== "") {
                var currency2 = $('#SelectCurrencyAmount' + j).find(":selected").text();
                if (currency1 !== currency2) {
                    rowTemp = j;
                    different++;
                }
            }
        }
    }
    if (different > 0) {
        $('#DetailBillableTable').find('tr').each(function() {
            $(this).find('td').each(function() {
                if ($(this).hasClass('priceCurrencyAmount')) {
                    $(this).addClass("alert-danger");
                }
            });
        });
        $('#textAlertCurrency').show();
        currency = 1;
        document.getElementById("saveInvoice").disabled = true;
//        alert("Currency : " + currency); 
//        $('#InvoiceForm').bootstrapValidator('validateField', 'SelectCurrencyAmount2'+rowTemp);
        if (checkcur1) {
            $('#textAlertCurrencyAmountNotEmpty').show();
            document.getElementById("saveInvoice").disabled = true;
        } else {
            $('#textAlertInvoiceNotEmpty').hide();
            document.getElementById("saveInvoice").disabled = false;
        }
        return false;
    } else {
        $('#DetailBillableTable').find('tr').each(function() {
            $(this).find('td').each(function() {
                if ($(this).hasClass('priceCurrencyAmount')) {
                    $(this).removeClass("alert-danger");
                }
            });
        });
        $('#textAlertCurrency').hide();
        currency = 0;
        document.getElementById("saveInvoice").disabled = false;
        if (checkcur1) {
            $('#textAlertCurrencyAmountNotEmpty').show();
            document.getElementById("saveInvoice").disabled = true;
        } else {
            $('#textAlertInvoiceNotEmpty').hide();
            document.getElementById("saveInvoice").disabled = false;
        }
        return true;
    }
}

function checkCurrencyCost() {
//    alert("check");
    var counter = $('#DetailBillableTable tbody tr').length;
    var different = 0;
    var rowTemp = 0;
    for (var i = 1; i <= (counter - 1); i++) {
        var currency1 = $('#SelectCurrencyAmount' + i).find(":selected").text();
        for (var j = 2; j <= (counter - 1); j++) {
            var currency2 = $('#SelectCurrencyAmount' + j).find(":selected").text();
            if (currency1 !== currency2) {
                rowTemp = j;
                different++;
            }
        }
    }
//                alert("Heeee : " + different);
    if (different > 0) {
        $('#DetailBillableTable').find('tr').each(function() {
            $(this).find('td').each(function() {
                if ($(this).hasClass('priceCurrencyAmount')) {
                    $(this).addClass("alert-danger");
                }
            });
        });
        $('#textAlertCurrency').show();
        currency = 1;
        alert("Currency : " + currency);
        $('#InvoiceForm').bootstrapValidator('revalidateField', '');
        return false;
    } else {
        $('#DetailBillableTable').find('tr').each(function() {
            $(this).find('td').each(function() {
                if ($(this).hasClass('priceCurrencyAmount')) {
                    $(this).removeClass("alert-danger");
                }
            });
        });
        $('#textAlertCurrency').hide();
        currency = 0;
        return true;
    }

}

var isDuplicateInvoiceDetail = 0;
var description = "";
function AddRowDetailBillAble(row, prod, des, cos, id, price, RefNo, cur, cur_c) {

    var selectT = "";
    var selectC = "";
    var selectCC = "";
    var showvat = $('#showvat').val();
    var check = "";
    var vatValue = '';
    var vathidden = '';
    var vat = $('#vatBase').val();

    if (showvat == 'true') {
        vathidden = '';
        check = "checked";
        vatValue = defaultD;
    } else {
        vathidden = 'class="hidden"';
    }
    if (prod === undefined) {
        prod = "";
    }
    if (des === undefined) {
        des = "";
    }
    if (cos === undefined) {
        cos = "";
    }
    if (id === undefined) {
        id = "";
    }
    if (RefNo === undefined) {
        RefNo = "";
    }

    if (price === undefined) {
        price = "";
    }
    if (cur === undefined) {
        cur = "";
    }
    if (cur_c === undefined) {
        cur_c = "";
    }
    if (!row) {
        row = 1;
    }
    var rows = document.getElementById("DetailBillableTable").getElementsByTagName("tr").length;
    if (rows > 1) {
        $("#tr_FormulaAddRow").css("display", "none");
    }
    selectT = selectType.replace("value='" + prod + "'", "selected value='" + prod + "'");
    selectC = select.replace("value='" + cur + "'", "selected value='" + cur + "'");
    selectCC = select_cost.replace("value='" + cur_c + "'", "selected value='" + cur_c + "'");
//    alert("D : " + des + " R : " + RefNo);
    if (des != '' || RefNo != '') {
        $("#DetailBillableTable tbody").append(
                '<tr>' +
                '<td class="hidden"><input type="text" class="form-control" id="detailId' + row + '" name="detailId' + row + '" value="" > </td>' +
                '<td class="hidden"><input type="text" class="form-control" id="DetailBillId' + row + '" name="DetailBillId' + row + '" value="' + id + '" > </td>' +
                '<td><select id="SelectProductType' + row + '" name="SelectProductType' + row + '" class="form-control">' + selectT + '</select> </td>' +
                '<td><input type="text" class="form-control" id="BillDescriptionTemp' + row + '" name="BillDescriptionTemp' + row + '" value="" onkeyup="setDescription(' + row + ')"></td>' +
                '<td class="hidden"><input type="text" class="form-control" id="BillDescription' + row + '" name="BillDescription' + row + '" value="' + des + '" > </td>' +
                '<td><input  maxlength ="15" type="text" onfocusout="changeFormatCostNumber(' + row + ')" class="form-control numerical text-right" id="InputCost' + row + '" name="InputCost' + row + '" value="' + cos + '" ></td>' +
                '<td><select id="SelectCurrencyCost' + row + '" name="SelectCurrencyCost' + row + '" class="form-control">' + selectCC + '</select></td>' +
                '<td><input type="text" onfocusout="changeFormatCostLocalNumber(' + row + ')"  value="' + cos + '" id="InputCostLocal' + row + '" name="InputCostLocal' + row + '" class="form-control text-right"></td>' +
                '<td class="hidden"><input type="text" value="' + cos + '" id="InputCostLocalTemp' + row + '" name="InputCostLocalTemp' + row + '"></td>' +
                '<td  ' + vathidden + '><input type="checkbox" ' + check + ' id="checkUse' + row + '" name="checkUse' + row + '"  onclick="calculateGross(' + row + ')"></td>' +
                '<td align="center" ' + vathidden + '>' + vatValue + '</td>' +
                '<td class="hidden"><input type="text" class="form-control" id="InputVatTemp' + row + '" name="InputVatTemp' + row + '" value="' + vat + '" ></td>' +
                '<td ' + vathidden + ' ><input type="text" maxlength ="15" readonly onfocusout="changeFormatGrossNumber(' + row + ')" class="form-control numerical text-right" id="InputGross' + row + '" name="InputGross' + row + '" value="" ></td>' +
                '<td><input type="text" maxlength ="15" onfocusout="changeFormatAmountNumber(' + row + ');" class="form-control numerical text-right" id="InputAmount' + row + '" name="InputAmount' + row + '" value="' + price + '" ></td>' +
                '<td class="priceCurrencyAmount"><select id="SelectCurrencyAmount' + row + '" name="SelectCurrencyAmount' + row + '" class="form-control" onclick="validFromInvoice()">' + selectC + '</select></td>' +
                '<td><input type="text" onfocusout="changeFormatAmountLocalNumber(' + row + ')" value="' + price + '" id="InputAmountLocal' + row + '" name="InputAmountLocal' + row + '" class="form-control text-right" ></td>' +
                '<td class="hidden"><input type="text" onfocusout="changeFormatAmountLocalTempNumber(' + row + ')" value="' + price + '" id="InputAmountLocalTemp' + row + '" name="InputAmountLocalTemp' + row + '"  ></td>' +
                '<td align="center" ><span  class="glyphicon glyphicon-th-list" data-toggle="modal" data-target="#DescriptionInvoiceDetailModal" onclick="getDescriptionDetail(' + row + ')" id="InputDescription' + row + '"></span><span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill(' + row + ',\'\')" data-toggle="modal" data-target="#DelDetailBill" >  </span></td>' +
                '<td class="hidden"><textarea id="DescriptionInvoiceDetail' + row + '" name="DescriptionInvoiceDetail' + row + '"> ' + description + '</textarea> </td>' +
                '</tr>'
                );
    } else if (des == '' || RefNo == '') {
        $("#DetailBillableTable tbody").append(
                '<tr>' +
                '<td class="hidden"><input type="text" class="form-control" id="detailId' + row + '" name="detailId' + row + '" value="" > </td>' +
                '<td class="hidden"><input type="text" class="form-control" id="DetailBillId' + row + '" name="DetailBillId' + row + '" value="' + id + '" > </td>' +
                '<td><select id="SelectProductType' + row + '" name="SelectProductType' + row + '" class="form-control">' + selectT + '</select> </td>' +
                '<td><input type="text" class="form-control" id="BillDescriptionTemp' + row + '" name="BillDescriptionTemp' + row + '" value="" onkeyup="setDescription(' + row + ')"></td>' +
                '<td class="hidden"><input type="text" class="form-control" id="BillDescription' + row + '" name="BillDescription' + row + '" value="' + des + '" > </td>' +
                '<td><input  maxlength ="15" type="text" onfocusout="changeFormatCostNumber(' + row + ')" class="form-control numerical text-right" id="InputCost' + row + '" name="InputCost' + row + '" value="' + cos + '" ></td>' +
                '<td><select id="SelectCurrencyCost' + row + '" name="SelectCurrencyCost' + row + '" class="form-control">' + selectCC + '</select></td>' +
                '<td><input type="text" onfocusout="changeFormatCostLocalNumber(' + row + ')"  value="' + cos + '" id="InputCostLocal' + row + '" name="InputCostLocal' + row + '" class="form-control text-right"></td>' +
                '<td class="hidden"><input type="text" value="' + cos + '" id="InputCostLocalTemp' + row + '" name="InputCostLocalTemp' + row + '"></td>' +
                '<td  ' + vathidden + '><input type="checkbox" ' + check + ' id="checkUse' + row + '" name="checkUse' + row + '"  onclick="calculateGross(' + row + ')"></td>' +
                '<td align="center" ' + vathidden + '>' + vatValue + '</td>' +
                '<td class="hidden"><input type="text" class="form-control" id="InputVatTemp' + row + '" name="InputVatTemp' + row + '" value="' + vat + '" ></td>' +
                '<td ' + vathidden + ' ><input type="text" maxlength ="15" readonly onfocusout="changeFormatGrossNumber(' + row + ')" class="form-control numerical text-right" id="InputGross' + row + '" name="InputGross' + row + '" value="" ></td>' +
                '<td><input type="text" maxlength ="15" onfocusout="changeFormatAmountNumber(' + row + ');" class="form-control numerical text-right" id="InputAmount' + row + '" name="InputAmount' + row + '"  value="' + price + '" ></td>' +
                '<td class="priceCurrencyAmount"><select id="SelectCurrencyAmount' + row + '" name="SelectCurrencyAmount' + row + '" class="form-control" onclick="validFromInvoice()">' + selectC + '</select></td>' +
                '<td><input type="text" onfocusout="changeFormatAmountLocalNumber(' + row + ')" value="' + price + '" id="InputAmountLocal' + row + '" name="InputAmountLocal' + row + '" class="form-control text-right" ></td>' +
                '<td class="hidden"><input type="text" onfocusout="changeFormatAmountLocalTempNumber(' + row + ')" value="' + price + '" id="InputAmountLocalTemp' + row + '" name="InputAmountLocalTemp' + row + '"  ></td>' +
                '<td align="center" ><span  class="glyphicon glyphicon-th-list" data-toggle="modal" data-target="#DescriptionInvoiceDetailModal" onclick="getDescriptionDetail(' + row + ')" id="InputDescription' + row + '"></span><span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill(' + row + ',\'\')" data-toggle="modal" data-target="#DelDetailBill" >  </span></td>' +
                '<td class="hidden"><textarea id="DescriptionInvoiceDetail' + row + '" name="DescriptionInvoiceDetail' + row + '"> ' + description + '</textarea> </td>' +
                '</tr>'
                );
    }
    var count = document.getElementById('counterTable');
    count.value = row++;

}

function setDescription(row) {
    var des = $('#BillDescriptionTemp' + row).val();
    $('#BillDescription' + row).val(des);
    console.log('Description ' + row + " : " + des);
}

function getDescriptionDetail(row) {
    var description = $('#DescriptionInvoiceDetail' + row).val();
//    alert(description);
    $('#InputDescriptionDetailId').val(row);
    $('#InputDescriptionDetail').val(description);
}

function saveDescriptionDetail() {
    var row = $('#InputDescriptionDetailId').val();
    var descriptionDetail = $('#InputDescriptionDetail').val();
    console.log("Detail : " + $('#InputDescriptionDetail').val());
    $('#DescriptionInvoiceDetail' + row).html(descriptionDetail);
}
function subStringDescription(description, row) {
    var index = description.indexOf("\n");
    var product = $('#SelectProductType' + row + '  option:selected').text();
    console.log("Product :" + product);
    var des = description.substring(0, index);
    console.log("description :" + description);

}
function changeFormatAmountNumber(id) {
    var count = document.getElementById('InputAmount' + id).value;

    var curamount = document.getElementById('SelectCurrencyAmount' + id).value;
    if (curamount === '') {
        $('#textAlertCurrencyAmountNotEmpty').show();
        document.getElementById("saveInvoice").disabled = true;
    } else {
        $('#textAlertInvoiceNotEmpty').hide();
        document.getElementById("saveInvoice").disabled = false;
    }

    count = count.replace(/\,/g, '');
    count = parseFloat(count);
    if (isNaN(count)) {
        document.getElementById('InputAmount' + id).value = "";
    } else {
        count = parseFloat(count);
        document.getElementById('InputAmount' + id).value = formatNumber(count);
    }
    CalculateGrandTotal(id);
    calculateGross(id);
}
function changeFormatAmountLocalNumber(id) {
    var count = parseFloat(document.getElementById('InputAmountLocal' + id).value);

    if (isNaN(count)) {
        document.getElementById('InputAmountLocal' + id).value = "";
    } else {
        count = parseFloat((document.getElementById('InputAmountLocal' + id).value).replace(/,/g,""));
        document.getElementById('InputAmountLocal' + id).value = formatNumber(count);
    }
}
function changeFormatAmountLocalTempNumber(id) {
    var count = parseFloat(document.getElementById('InputAmountLocalTemp' + id).value);

    if (isNaN(count)) {
        document.getElementById('InputAmountLocalTemp' + id).value = "";
    } else {
        count = parseFloat(document.getElementById('InputAmountLocalTemp' + id).value);
        document.getElementById('InputAmountLocalTemp' + id).value = formatNumber(count);
    }
}
function changeFormatCostNumber(id) {
    var count = document.getElementById('InputCost' + id).value;
    count = count.replace(/\,/g, '');
    count = parseFloat(count);
    if (isNaN(count)) {
        document.getElementById('InputCost' + id).value = "";
    } else {
        count = parseFloat(count);
        document.getElementById('InputCost' + id).value = formatNumber(count);
    }
}
function changeFormatCostLocalNumber(id) {
    var count = parseFloat(document.getElementById('InputCostLocal' + id).value);

    if (isNaN(count)) {
        document.getElementById('InputCostLocal' + id).value = "";
    } else {
        count = parseFloat(document.getElementById('InputCostLocal' + id).value);
        document.getElementById('InputCostLocal' + id).value = formatNumber(count);
    }
}
function changeFormatGrossNumber(id) {
    var count = parseFloat(document.getElementById('InputGross' + id).value);
    if (isNaN(count)) {
        document.getElementById('InputGross' + id).value = "";
    } else {
        count = parseFloat(document.getElementById('InputGross' + id).value);
        document.getElementById('InputGross' + id).value = formatNumber(count);
    }
}

function DeleteDetailBill(rowID, code) {
    $("#idDeleteDetailBillable").val(rowID);
    if (code !== "") {
        $("#DeleteDetailBillable").text('Are you sure to delete detail billable : ' + code + '..?');
    } else {
        $("#DeleteDetailBillable").text('Are you sure to delete detail billable ?');
    }
}

function DisableVoidInvoice() {
    var InvNo = document.getElementById('InvNo');
    document.getElementById('disableVoid').innerHTML = "Are you sure to delete booking other : " + InvNo.value + " ?";
}

function EnableVoidInvoice() {
    var InvNo = document.getElementById('InvNo');
    document.getElementById('enableVoid').innerHTML = "Are you sure to enable booking other : " + InvNo.value + " ?";
}

function Enable() {
    var action = document.getElementById('action');
    action.value = 'enableVoid';
    document.getElementById('InvoiceForm').submit();
}

function DisableInvoice() {
    var action = document.getElementById('action');
    action.value = 'disableVoid';
    document.getElementById('InvoiceForm').submit();
}

function printInvoice(text) {
    $('#typePrint').val(text);
}

function printInvoiceNew() {
    var invoiceId = $('#InvoiceId').val();
    var typePrint = $('#SelectTypePrint').val();
    var sale = $('#selectSalesStaff').val();
    var leader = $('#selectLeader').val();
    var payment = $('#selectPayment').val();
    var type = $('#typePrint').val();
    var sign = $('#SelectSign').val();
    var invoiceType = $('#invoiceType').val();
    if (type === 'print') {
        if (invoiceType === 'T') {
            window.open("report.smi?name=InvoiceTemp&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        } else {
            window.open("report.smi?name=" + typePrint + "&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        }
    } else if (type === 'email') {
        if (invoiceType === 'T') {
            window.open("report.smi?name=InvoiceTemp&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        } else {
            window.open("SendMail.smi?reportname=Invoice&reportid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        }
    }
}

function CallAjaxAuto(billto, billname, address, term, pay) {
    $("#InvTo").val(billto);
    $("#InvToName").val(billname);
    if (address == 'null') {
        $("#InvToAddress").val("");
    } else {
        $("#InvToAddress").val(address);
    }
    $("#InvToModal").modal('hide');
}

function DeleteBill() {
    var count = document.getElementById('counterTable');
    var rowId = document.getElementById('idDeleteDetailBillable');
    var row = rowId.value;
    var DetailBillId = $("#detailId" + rowId.value).val();
    if ((DetailBillId !== "") && (DetailBillId !== undefined)) {
        rowId.value = DetailBillId;
        console.log("Row : " + rowId.value);
        var servletName = 'BillableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&name=' + DetailBillId +
                '&type=' + 'deleteInvoiceDetail';
        CallAjaxDeleteBill(param, row);

    } else {
        $("#BillDescription" + rowId.value).parent().parent().remove();
        $('#DelDetailBill').modal('hide');
        console.log("Row 0  : " + count.value);
        if (count.value <= 1) {
            console.log("show button tr_FormulaAddRow : ");
            $("#tr_FormulaAddRow").css("display", "block");
        }
        count.value = count.value - 1;
    }
}

function CallAjaxDeleteBill(param, row) {
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                console.log("Message : " + msg);
                $('#resultText').val(msg);
                if (msg === 'success') {
                    console.log("Delete Detail");
                    $("#BillDescription" + row).parent().parent().remove();
                    $('#textAlertInvoiceNotEmpty').hide();
                } else if (msg === 'notDeleteReciptAndTax') {
                    $('#textAlertInvoiceNotEmpty').show();
                }
                $("#ajaxload").addClass("hidden");

            }, error: function(msg) {
                $("#ajaxload").addClass("hidden");
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function searchCustomerAgentList(name) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getListBillto';
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
                $('#InvToTable').dataTable().fnClearTable();
                $('#InvToTable').dataTable().fnDestroy();
                $("#InvToTable tbody").empty().append(msg);

                $('#InvToTable').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bPaginate": true,
                    "bInfo": false,
                    "bLengthChange": false,
                    "iDisplayLength": 10
                });
                $("#ajaxload").addClass("hidden");

            }, error: function(msg) {
                $("#ajaxload").addClass("hidden");
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function searchCustomerAutoList(name) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getAutoListBillto';
    CallAjaxAuto(param);
}

function CallAjaxAuto(param) {
    var url = 'AJAXServlet';
    var billArray = [];
    var billListId = [];
    var billListName = [];
    var billListAddress = [];
    var billid, billname, billaddr;
    $("#InvTo").autocomplete("destroy");
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
                var billJson = JSON.parse(msg);
                for (var i in billJson) {
                    if (billJson.hasOwnProperty(i)) {
                        billid = billJson[i].id;
                        billname = billJson[i].name;
                        billaddr = billJson[i].address;
                        billArray.push(billid);
                        billArray.push(billname);
                        billListId.push(billid);
                        billListName.push(billname);
                        billListAddress.push(billaddr);
                    }
                    $("#dataload").addClass("hidden");
                }
                $("#InvTo_Id").val(billid);
                $("#ARCode").val(billid);
                $("#InvToName").val(billname);
                $("#InvToAddress").val(billaddr);

                $("#InvTo").autocomplete({
                    source: billArray,
                    close: function() {
                        $("#InvTo").trigger("keyup");
                        var billselect = $("#InvTo").val();
                        for (var i = 0; i < billListId.length; i++) {
                            if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                                $("#InvTo").val(billListId[i]);
                                $("#ARCode").val(billListId[i]);
                                $("#InvToName").val(billListName[i]);
                                $("#InvToAddress").val(billListAddress[i]);
                            }
                        }
                    }
                });

                var billval = $("#InvTo").val();
                for (var i = 0; i < billListId.length; i++) {
                    if (billval == billListName[i]) {
                        $("#InvTo").val(billListId[i]);
                    }
                }
                if (billListId.length == 1) {
                    showflag = 0;
                    $("#InvTo").val(billListId[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $("#InvTo").trigger(event);

            }, error: function(msg) {
                console.log('auto ERROR');
                $("#dataload").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

function searchAction() {
    $('#MasterReservation > tbody  > tr').each(function() {
        $(this).remove();
    });

    var searchNo = $("#SearchRefNo").val();
    var invType = $("#invType").val();
//    alert("Ref : " + searchNo);
    console.log("inv type : " + invType + ":");
    if (searchNo !== "") {
        var servletName = 'InvoiceServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&refNo=' + searchNo +
                '&invType=' + invType +
                '&type=' + 'searchInvoice';
        CallAjaxAdd(param);
    } else {
        $('#SearchRefNo').focus();
    }

}
function CallAjaxAdd(param) {
    var url = 'AJAXServlet';
    var BookintType = "";
    $("#ajaxloadsearch").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                var strx = msg.split('||');
                var array = [];
                array = array.concat(strx);

                BookintType = array[0];
                if (BookintType == $('#typeBooking').val()) {
                    $('#AlertBooking').hide();
                    $('#AlertBookingRefno').hide();
                    setBillableInvoice(array[1]);
                    try {
                        $("#MasterReservation tbody").append(array[2]);
                    } catch (e) {
                        alert(e);
                    }
                } else {
                    $('#AlertBooking').show();
                    $('#AlertBookingRefno').show();

                }

                $("#ajaxloadsearch").addClass("hidden");
            }, error: function(msg) {
                $("#ajaxloadsearch").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

function setBillableInvoice(data) {
    var strx = data.split('//');
    var array = [];
    array = array.concat(strx);
    $('#InvTo').val(array[0]);
    $('#ARCode').val(array[0]);
    $('#InvToName').val(array[1]);
    $('#InvToAddress').val(array[2]);
    $('#TermPay').val(array[3]);
    $('#SaleStaffId').val(array[4]);
    $('#SaleStaffName').val(array[5]);
    $('#SaleStaffCode').val(array[6]);
    $('#InputDueDate').val(array[7]);
}

function addInvoiceDetail(rowId) {
    isDuplicateInvoiceDetail = 0;
    var rowCount = $('#DetailBillableTable >tbody >tr').length;
    $("#counter").val((rowCount++));
    var countTable = $("#counter").val();
    var RefNo = $("#SearchRefNo").val();
    var count = 1;
    $('#MasterReservation > tbody  > tr').each(function() {
        if (count === rowId) {
            var id = $('#invoiceIdSearch' + rowId).val();
            var prod = $('#invoiceIdType' + rowId).val();
            var refItemId = $('#RefItemId' + rowId).val();
            var pro = $(this).find("td").eq(3).html();
            var des = $(this).find("td").eq(4).html();
            var cos = $(this).find("td").eq(5).html();
            var cur_c = $(this).find("td").eq(6).html();
            var price = $(this).find("td").eq(7).html();
            var cur = $(this).find("td").eq(8).html();
            checkDuplicateInvoiceDetail(id, rowId);
            console.log("Duplicate : " + isDuplicateInvoiceDetail);
            if (isDuplicateInvoiceDetail === 0) {
                $('#textAlertDuplicate').hide();
                $("#DetailBillableTable tr:last").remove();
                // Search Description
                var servletName = 'InvoiceServlet';
                var servicesName = 'AJAXBean';
                var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&refNo=' + refItemId +
                        '&typeId=' + prod +
                        '&type=' + 'searchInvoiceDescription';
                CallAjaxSearchDescription(param, countTable, pro, RefNo);
                // Send Add Row
                AddRowDetailBillAble(countTable, prod, des, cos, id, price, RefNo, cur, cur_c);
//                alert("C : " + countTable);
                CalculateGrandTotal(countTable);
                calculateGross(countTable);
            } else if (isDuplicateInvoiceDetail !== 0) {
//                alert("Duplicate");
                $('#textAlertDuplicate').show();
            }
        }
        count++;
    });
    if (isDuplicateInvoiceDetail === 0) {
        countTable++;
        $("#counter").val(countTable);
        AddRowDetailBillAble(countTable);
    }
}

function CallAjaxSearchDescription(param, rowId, des, RefNo) {
    var url = 'AJAXServlet';
    $("#ajaxloadsearch").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                var strx = msg.split('|');
                var array = [];
                array = array.concat(strx);
                setbillAndDescription(rowId, RefNo, array[1], array[0], des);
                try {
                    $("#ajaxloadsearch").addClass("hidden");
                } catch (e) {
                    alert(e);
                }
            }, error: function(msg) {
                $("#ajaxloadsearch").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

function setbillAndDescription(row, ref, name, text, des) {
    var bill = ref + " " + name + " " + des;
    $('#BillDescriptionTemp' + row).val(bill);
    $('#BillDescription' + row).val(bill);
    $('#DescriptionInvoiceDetail' + row).html(text);
    $("#DescriptionInvoiceDetailTextArea" + row).html(text);
}

function checkDuplicateInvoiceDetail(product, rowId) {
    $('#DetailBillableTable > tbody  > tr').each(function() {
        var prod = $(this).find('input[type="text"]').eq(1).val();
        if ($.trim(prod) === product) {
            isDuplicateInvoiceDetail++;
        }
    });
}

function setDescriptionAirAdditional(data, row) {
    var array = [];
    array = data;
    var text = "";
    text += array[2];
    $('#DescriptionInvoiceDetail' + row).html(text);
    $("#DescriptionInvoiceDetailTextArea" + row).html(text);
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

// American Numbering System
var countVat = 0;
function calculateGross(row) {
    var amount = document.getElementById('InputAmount' + row).value;
    var gross = document.getElementById('InputGross' + row).value;
    var varTemp = $('#InputVatTemp' + row).val();
    var vatDefaultData = parseFloat(varTemp);
    $('#checkUse' + row).val();
    amount = amount.replace(/,/g, "");
    var grossTotal = parseFloat(amount);
    if ($('#checkUse' + row).is(":checked")) {
        if (countVat !== 0) {
            var vatT = $('#vatBase').val();
            var vatTT = parseFloat(vatT);
            document.getElementById("DetailBillableTable").rows[row].cells[10].innerHTML = vatTT;
            grossTotal = (amount * 100) / (100 + vatTT);
            document.getElementById('InputGross' + row).value = formatNumber(grossTotal);
        } else {
            document.getElementById("DetailBillableTable").rows[row].cells[10].innerHTML = vatDefaultData;
            grossTotal = (amount * 100) / (100 + vatDefaultData);
            document.getElementById('InputGross' + row).value = formatNumber(grossTotal);
        }
        countVat++;
    } else {
        document.getElementById("DetailBillableTable").rows[row].cells[10].innerHTML = '';
        document.getElementById('InputGross' + row).value = '';
    }
}

function calculateGrossTemp(row) {
    var amount = document.getElementById('InputAmount' + row).value;
    var gross = document.getElementById('InputGross' + row).value;
    var varTemp = document.getElementById('InputVatTemp' + row).value;
    var vatDefaultData = parseFloat(varTemp);

    amount = amount.replace(/,/g, "");
    var grossTotal = parseFloat(amount);

    if ((gross === '')) {
        grossTotal = (amount * 100) / (100 + vatDefaultData);
        document.getElementById("DetailBillableTable").rows[row].cells[10].innerHTML = vatDefaultData;
    } else if ((gross === '0.00')) {
        grossTotal = (amount * 100) / (100 + vatDefaultData);
        document.getElementById('InputGross' + row).value = formatNumber(grossTotal);
        document.getElementById("DetailBillableTable").rows[row].cells[10].innerHTML = vatDefaultData;
    } else {
        document.getElementById('InputGross' + row).value = '';
        document.getElementById("DetailBillableTable").rows[row].cells[10].innerHTML = '';
    }
}

function CalculateGrandTotal(id) {
    var count = parseInt(document.getElementById('counterTable').value);
    var i;
    var grandTotal = 0;
    if ((id !== null) || (id !== '')) {
        for (i = 0; i < count + 1; i++) {
            var amount = document.getElementById("InputAmount" + i);
            if (amount !== null) {
                var value = amount.value;
                if (value !== '') {
                    value = value.replace(/,/g, "");
                    var total = parseFloat(value);
                    grandTotal += total;
                    document.getElementById('InputAmount' + i).value = formatNumber(total);
                }
            }
        }
        if (id !== '') {
        }

        document.getElementById('TotalNet').value = formatNumber(grandTotal);
        if (grandTotal !== 0) {
            var bathString = toWords((grandTotal));
            document.getElementById('TextAmount').value = bathString;
        }
    }
}

function checkVatInvoiceAll() {
    var row = document.getElementById('counterTable').value;
    var check = 0;
    var unCheck = 0;
    for (var i = 1; i <= row; i++) {
        var isVatCheck = document.getElementById("checkUse" + i);
        if (isVatCheck !== null && isVatCheck !== '') {
            if (document.getElementById("checkUse" + i).checked) {
                check++;
            } else {
                unCheck++;
            }
        }
    }
    if (check > unCheck) {
        if (unCheck !== 0) {
            for (var i = 0; i <= row; i++) {
                var isVatCheck = document.getElementById("checkUse" + i);
                if (isVatCheck !== null && isVatCheck !== '') {
                    if (document.getElementById("checkUse" + i).checked) {

                    } else {
                        document.getElementById("checkUse" + i).checked = true;
                        var amountChk = document.getElementById('InputAmount' + i);
                        if (amountChk !== null && amountChk !== '') {
                            var amount = document.getElementById('InputAmount' + i).value;
                            var gross = document.getElementById('InputGross' + i).value;

                            var vatTemp = $('#vatBase').val();
                            var vatDefaultData = parseFloat(vatTemp);
                            console.log("Vat : " + vatDefaultData);
                            amount = amount.replace(/,/g, "");
                            var grossTotal = parseFloat(amount);

                            if ((gross === '')) {
                                grossTotal = (amount * 100) / (100 + vatDefaultData);
                                document.getElementById('InputGross' + i).value = formatNumber(grossTotal);
                                document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = vatDefaultData;
                            } else {
                                document.getElementById('InputGross' + i).value = '';
                                document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = '';
                            }
                        }
                    }
                }
            }
        }
    }

    if (check < unCheck) {
        for (var i = 0; i <= row; i++) {
            var isVatCheck = document.getElementById("checkUse" + i);
            if (isVatCheck !== null && isVatCheck !== '') {
                document.getElementById("checkUse" + i).checked = false;
                document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = "";
                document.getElementById("InputGross" + i).value = '';
            }
        }
    }

    if (check === 0 && unCheck !== 0) {
        for (var i = 0; i <= row; i++) {
            var isVatCheck = document.getElementById("checkUse" + i);
            if (isVatCheck !== null && isVatCheck !== '') {
                if (document.getElementById("checkUse" + i).checked) {

                } else {
                    $("#checkUse" + i).prop("checked", true);
                    var amountChk = document.getElementById('InputAmount' + i);
                    if (amountChk !== null && amountChk !== '') {
                        var amount = document.getElementById('InputAmount' + i).value;
                        var gross = document.getElementById('InputGross' + i).value;
                        var vatTemp = $('#vatBase').val();
                        var vatDefaultData = parseFloat(vatTemp);
                        amount = amount.replace(/,/g, "");
                        var grossTotal = parseFloat(amount);
                        if ((gross === '')) {
                            grossTotal = (amount * 100) / (100 + vatDefaultData);
                            document.getElementById('InputGross' + i).value = formatNumber(grossTotal);
                            document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('InputGross' + i).value = '';
                            document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = '';
                        }
                    }
                }
            }
        }
    }

    if (check !== 0 && unCheck === 0) {
        for (var i = 0; i <= row; i++) {
            var isVatCheck = document.getElementById("checkUse" + i);
            if (isVatCheck !== null && isVatCheck !== '') {
                document.getElementById("checkUse" + i).checked = false;
                document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = '';
                document.getElementById("InputGross" + i).value = '';
            }
        }
    }

    if (check === unCheck) {
        for (var i = 0; i <= row; i++) {
            var isVatCheck = document.getElementById("checkUse" + i);
            if (isVatCheck !== null && isVatCheck !== '') {
                if (document.getElementById("checkUse" + i).checked) {

                } else {
                    document.getElementById("checkUse" + i).checked = true;
                    var amountChk = document.getElementById('InputAmount' + i).value;
                    if (amountChk !== null && amountChk !== '') {
                        var amount = document.getElementById('InputAmount' + i).value;
                        var gross = document.getElementById('InputGross' + i).value;
                        var vatTemp = $('#vatBase').val();
                        var vatDefaultData = parseFloat(vatTemp);
                        amount = amount.replace(/,/g, "");
                        var grossTotal = parseFloat(amount);

                        if ((gross === '')) {
                            grossTotal = (amount * 100) / (100 + vatDefaultData);
                            document.getElementById('InputGross' + i).value = formatNumber(grossTotal);
                            document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('InputGross' + i).value = '';
                            document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = vatDefaultData;
                        }
                    }
                }
            }
        }
    }
    CalculateGrandTotal('');
}

function sendEmailInvoice() {
    var InvoiceId = document.getElementById('InvoiceId').value;
    window.open("SendMail.smi?reportname=Invoice&reportid=" + InvoiceId + "&bankid=4");
}

$(document).ready(function() {
    var bla = $('#resultText').val();
    if (bla === "success") {
        $('#textAlertDivSave').show();
    } else if (bla === "") {
        $('#textAlertDivSave').hide();
    } else if (bla === "notInvoice") {
        $('#textAlertNotInvoice').show();
    } else if (bla === "yesInvoice") {
        $('#textAlertNotInvoice').hide();
    } else if (bla === "moreMoney") {
        $('#textAlertMoney').show();
    } else if (bla === "okMoney") {
        $('#textAlertMoney').hide();
    } else if (bla === "fail") {
        $('#textAlertDivNotSave').show();
    } else if (bla === "NEW") {
        clearInvoice();
    } else if (bla === "notDeleteReciptAndTax") {
        $("#textAlertInvoiceNotEmpty").show();
    }
});

function clearScreenInvoice() {
    var action = document.getElementById('action');
    action.value = 'new';
    document.getElementById('InvoiceForm').submit();

}

function clearInvoice() {
    $('#SearchRefNo, #InvNo, #InputDueDate, #InvTo, #InvToName, #InvToAddress, #SaleStaffId, #SaleStaffCode, #SaleStaffName, #ARCode, #Remark, #TextAmount, #TotalNet,#InvoiceId ').val('');
    $('#Grpup').attr('checked', false);
    $('#MasterReservation > tbody  > tr').each(function() {
        $(this).remove();
    });

    $('#DetailBillableTable > tbody  > tr').each(function() {
        $(this).remove();
    });
    $('#counterTable').val('1');
    AddRowDetailBillAble();
}
function copyInvoice() {
    $('#InvoiceId').val('');
    $('#InvNo').val('');
    console.log("Invoice Id : " + $('#InvoiceId').val() + "Invoice Number : " + $('#InvNo').val());
    var action = document.getElementById('action');
    action.value = 'copyInvoice';
    document.getElementById('InvoiceForm').submit();
}

function setBillValue(billto, billname, address, term, pay) {
    $("#InvTo").val(billto);
    $("#InvName").val(billname);
    $("#InvToAddress").val(address);
    $("#InvToModal").modal('hide');
}