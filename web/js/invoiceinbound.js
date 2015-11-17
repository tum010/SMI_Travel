/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
            if ($("#searchInvoiceFrom").val() === "") {
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
        if (showflag === 0) {
            $(".ui-widget").css("top", -1000);
            showflag = 1;
        }
    });
    
    // Add Row Auto key
    $("#DetailBillableTable").on("keyup", function() {
        var rowAll = $("#DetailBillableTable tr").length;
        $("td").keyup(function() {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#DetailBillableTable tr").length;
                if (rowIndex === rowAll) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    addRowInvoiceInboundDetail(rowAll);
                }
            }
        });
    });
    var rowCount = $('#DetailBillableTable tr').length;
    $("#counterTable").val(rowCount);
    addRowInvoiceInboundDetail(rowCount++);
});

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
                            if ((billselect === billListName[i]) || (billselect === billListId[i])) {
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
                    if (billval === billListName[i]) {
                        $("#InvTo").val(billListId[i]);
                    }
                }
                if (billListId.length === 1) {
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

function setBillValue(billto, billname, address, term, pay) {
    $("#InvTo").val(billto);
    $("#InvName").val(billname);
    $("#InvToAddress").val(address);
    $("#InvToModal").modal('hide');
}

var countVat = 0;
function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

function calculateGross(row) {
    console.log("Row : " + row);
    var amount = document.getElementById('InputAmount' + row).value;
    var gross = document.getElementById('InputGross' + row).value;
    var varTemp = $('#vatBase').val();
    var vatDefaultData = parseFloat(varTemp);
    var grossTotal =0;
    $('#checkUse' + row).val();
    amount = amount.replace(/,/g, "");
    if(amount === ''){
        amount = 0.0;
    }
    grossTotal = parseFloat(amount);
    if ($('#checkUse' + row).is(":checked")) {
        if (countVat !== 0) {
            var vatT = $('#vatBase').val();
            var vatTT = parseFloat(vatT);
            console.log("Vat : " + vatTT);
            document.getElementById("DetailBillableTable").rows[row].cells[3].innerHTML = vatTT;
            grossTotal = (amount * 100) / (100 + vatTT);
            document.getElementById('InputGross' + row).value = formatNumber(grossTotal);
        } else {
            document.getElementById("DetailBillableTable").rows[row].cells[3].innerHTML = vatDefaultData;
            if(amount !== 0){
                grossTotal = (amount * 100) / (100 + vatDefaultData);
            }else{
                grossTotal = 0.0;;
            }

            document.getElementById('InputGross' + row).value = formatNumber(grossTotal);
        }
        countVat++;
    } else {
         console.log("Vat : " + vatTT);
        document.getElementById("DetailBillableTable").rows[row].cells[3].innerHTML = '';
        document.getElementById('InputGross' + row).value = '';
    }
}

function checkVatInvoiceInboundAll() {
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
                                document.getElementById("DetailBillableTable").rows[i].cells[4].innerHTML = vatDefaultData;
                            } else {
                                document.getElementById('InputGross' + i).value = '';
                                document.getElementById("DetailBillableTable").rows[i].cells[4].innerHTML = '';
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
                document.getElementById("DetailBillableTable").rows[i].cells[4].innerHTML = "";
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
                            document.getElementById("DetailBillableTable").rows[i].cells[4].innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('InputGross' + i).value = '';
                            document.getElementById("DetailBillableTable").rows[i].cells[4].innerHTML = '';
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
                document.getElementById("DetailBillableTable").rows[i].cells[4].innerHTML = '';
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
                            document.getElementById("DetailBillableTable").rows[i].cells[4].innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('InputGross' + i).value = '';
                            document.getElementById("DetailBillableTable").rows[i].cells[4].innerHTML = vatDefaultData;
                        }
                    }
                }
            }
        }
    }
    CalculateGrandTotal('');
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

function changeFormatGrossNumber(id) {
    var count = parseFloat(document.getElementById('InputGross' + id).value);
    if (isNaN(count)) {
        document.getElementById('InputGross' + id).value = "";
    } else {
        count = parseFloat(document.getElementById('InputGross' + id).value);
        document.getElementById('InputGross' + id).value = formatNumber(count);
    }
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

function addRowInvoiceInboundDetail(row){
    var vat = $('#vatBase').val();
    $("#DetailBillableTable tbody").append(
    '<tr>' +
    '<td class="hidden"><input type="text" class="form-control" id="detailId' + row + '" name="detailId' + row + '" value="" > </td>' +
    '<td><input type="text" class="form-control" id="BillDescriptionTemp' + row + '" name="BillDescriptionTemp' + row + '"   value=""></td>' +
    '<td><input type="checkbox" id="checkUse' + row + '" name="checkUse' + row + '" onclick="calculateGross(' + row + ')" value=""></td>' +
    '<td class="hidden" ><input type="text" id="InputVatTemp' + row + '" name="InputVatTemp' + row + '"  value="' + vat + '"></td>' +
    '<td >'+vat +'</td>' +
    '<td ><input type="text" maxlength ="15" readonly  onfocusout="changeFormatGrossNumber(' + row + ')" class="form-control numerical" id="InputGross' + row + '" name="InputGross' + row + '" value="" ></td>' +
    '<td><input type="text" maxlength ="15" class="form-control numerical text-right" id="InputAmount' + row + '" name="InputAmount' + row + '" onfocusout="changeFormatAmountNumber(' + row + ');"  value=""></td>' +
    '<td class="priceCurrencyAmount"><select id="SelectCurrencyAmount' + row + '" name="SelectCurrencyAmount' + row + '" class="form-control" >' + select + '</select></td>' +              
    '<td align="center" ><span  class="glyphicon glyphicon-th-list" data-toggle="modal" data-target="#DescriptionInvoiceDetailModal" onclick="getDescriptionDetail(' + row + ')" id="InputDescription' + row + '"></span><span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill(' + row + ',\'\')" data-toggle="modal" data-target="#DelDetailBill" >  </span></td>' +           
    '</tr>'
    );
    var count = document.getElementById('counterTable');
    count.value = row++;
}