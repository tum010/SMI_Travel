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
//    $(".numerical").mask('00000000', {reverse: true});
    
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
//            $("#ARCode").val(staff_code);
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
            $("#ARCode").val("");
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
                document.getElementById('InvoiceInboundForm').submit();
            }

        } else if (event.keyCode === 40) {
            if ((parseInt(wildCardSearch) >= 0) || ($("#InvoiceId").val() !== '')) {
                $("#keyCode").val(event.keyCode);
                var action = document.getElementById('action');
                action.value = 'wildCardSearch';
                document.getElementById('InvoiceInboundForm').submit();
            }

        } else if (event.keyCode === 118) {
            $("#keyCode").val(event.keyCode);
            var action = document.getElementById('action');
            action.value = 'new';
            document.getElementById('InvoiceInboundForm').submit();

        } else if (event.keyCode === 119) {
            $("#keyCode").val(event.keyCode);
            var action = document.getElementById('action');
            action.value = 'wildCardSearch';
            document.getElementById('InvoiceInboundForm').submit();

        }
    });

    CalculateGrandTotal('');
    var counter = $('#DetailBillableTable tbody tr').length;
    for (var j = 1; j <= (counter - 1); j++) {
        changeFormatAmountNumber(j);
    }
    
    var invoiceNumber = $('#InvNo').val();
    if (invoiceNumber === '') {
        document.getElementById("printButton").disabled = true;
        document.getElementById("printButtonEmail").disabled = true;
        document.getElementById("sendEmailButton").disabled = true;
    }else{
        document.getElementById("printButton").disabled = false;
        document.getElementById("printButtonEmail").disabled = false;
        document.getElementById("sendEmailButton").disabled = false;
    }
    
//    validFromInvoiceInbound();
});

function searchInvoiceFromInvoiceNo() {
    
    var invno = $("#InvNo").val();
    if(invno !== ""){
        var action = document.getElementById('action');
        action.value = 'searchInvoice';
        document.getElementById('InvoiceInboundForm').submit();
    }else{
        $("#InvNo").focus();
    }
}

function searchCustomerAgentList(name) {
    name = generateSpecialCharacter(name);
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
    name = generateSpecialCharacter(name);
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
    var billListType = [];
    var billid, billname, billaddr, billtype;
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
                        billtype = billJson[i].type;
                        billArray.push(billid);
                        billArray.push(billname);
                        billListId.push(billid);
                        billListName.push(billname);
                        billListAddress.push(billaddr);
                        billListType.push(billtype);
                    }
                    $("#dataload").addClass("hidden");
                }
                $("#InvTo_Id").val(billid);
                $("#ARCode").val(billtype !== 'C' ? billid : 'DUMMY');
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
                                $("#ARCode").val(billListType[i] !== 'C' ? billListId[i] : 'DUMMY');
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

function setBillValue(billto, billname, address, term, pay, type) {
    $("#InvTo").val(billto);
    $("#InvToName").val(billname);
    $("#InvToAddress").val(address);
    $("#ARCode").val(type !== 'C' ? billto : 'DUMMY');

    if($("#InvTo").val() !== "" && $("#InvToName").val() !== ""  && $("#ARCode").val() !== "" && $("#InputInvDate").val() !== ""){
//        alert("1");
        $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvTo');
        $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvToName');
        $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'ARCode');
        $("#saveInvoice").removeAttr("disabled");
        $("#disableVoidButton").removeAttr("disabled");
        $("#newInvoiceInbound").removeAttr("disabled");
    }else{
//         alert("2");
        $("#saveInvoice").attr("disabled", "disabled");
        $("#disableVoidButton").attr("disabled", "disabled");
        $("#newInvoiceInbound").attr("disabled", "disabled");
    }
    $("#InvToModal").modal('hide');
}

var countVat = 0;
function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

function calculateGross(row) {
    console.log("Row : " + row);
    var countTable = parseInt($("#counterTable").val());
    var amount = document.getElementById('InputAmount' + row).value;
    var gross = document.getElementById('InputGross' + row).value;
//    var varTemp = $('#vatBase').val();
    var varTemp = $('#InputVatTemp'+row).val();
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
//            var vatT = $('#vatBase').val();
            var vatT= $('#InputVatTemp'+row).val();
            var vatTT = parseFloat(vatT);
            console.log("Vat : " + formatNumber(vatTT));
            document.getElementById("DetailBillableTable").rows[row].cells[4].innerHTML = (vatTT);
            grossTotal = (amount * 100) / (100 + vatTT);
            document.getElementById('InputGross' + row).value = formatNumber(grossTotal);
            document.getElementById('InputVatTemp' + row).value = formatNumber(countVat+1 < countTable ? parseFloat($('#InputVatTemp'+row).val()) : parseFloat($('#vatBase').val()));
        } else {
            document.getElementById("DetailBillableTable").rows[row].cells[4].innerHTML = (vatDefaultData);
            if(amount !== 0){
                grossTotal = (amount * 100) / (100 + vatDefaultData);
            }else{
                grossTotal = 0.0;;
            }

            document.getElementById('InputGross' + row).value = formatNumber(grossTotal);
            document.getElementById('InputVatTemp' + row).value = formatNumber(countVat+1 < countTable ? parseFloat($('#InputVatTemp'+row).val()) : parseFloat($('#vatBase').val()));
        }
        countVat++;
    } else {
        console.log("Vat : " + vatTT);
        document.getElementById("DetailBillableTable").rows[row].cells[4].innerHTML = '';
        document.getElementById('InputGross' + row).value = '';
        document.getElementById('InputVatTemp' + row).value = formatNumber(countVat+1 < countTable ? parseFloat($('#InputVatTemp'+row).val()) : parseFloat($('#vatBase').val()));
    }
    CalculateTotalNet(row);

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
    CalculateTotalNet('1');
}

function CalculateGrandTotal(id) {
    var count = parseInt(document.getElementById('counterTable').value);
    var i;
    var grandTotal = 0;
    if ((id !== null) || (id !== '')) {
        for (i = 1; i < count + 1; i++) {
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
        document.getElementById('GrandTotal').value = formatNumber(grandTotal);
        if (grandTotal !== 0) {
            var currency = getCurrency();
            var bathString = toWords(grandTotal,currency);
            document.getElementById('TextAmount').value = bathString;
        }
    }
}

function getCurrency(){
    var count = parseInt($("#counterTable").val());
    var currency = '';
    for(var i=1; i<=count; i++){
        var currency1 = document.getElementById('SelectCurrencyAmount'+i);
        var description1 = document.getElementById('BillDescriptionTemp'+i);
        var amount1 = document.getElementById('InputAmount'+i);
        if(currency1 !== null){
            if(description1.value !== '' || amount1.value !== ''){
                currency = currency1.value;
                var currencyTemp1 = currency1.value;
                for(var j=i+1; j<=count; j++){
                    var currency2 = document.getElementById('SelectCurrencyAmount'+j);
                    var description2 = document.getElementById('BillDescriptionTemp'+j);
                    var amount2 = document.getElementById('InputAmount'+j);
                    if(currency2 !== null){
                        if(description2.value !== '' || amount2.value !== ''){
                            var currencyTemp2 = currency2.value;
                            if((currencyTemp1 !== currencyTemp2)){
                                currency = ''; 
                                i = count+1;
                                j = count+1;
                            }
                        }    
                    }
                }
            }    
        }    
    }
    return currency;
}

function toWordsMoney(s){
    var currency = $('#SelectCurrencyAmount1').find(":selected").text();
    if(s === 0){
        var defaultWord = '';
        return  defaultWord;
    }
    var hundred = s;
    var th = ['','thousand','million', 'billion','trillion'];
    var dg = ['zero','one','two','three','four', 'five','six','seven','eight','nine']; 
    var tn = ['ten','eleven','twelve','thirteen', 'fourteen','fifteen','sixteen', 'seventeen','eighteen','nineteen']; 
    var tw = ['twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety']; 
    s = s.toString(); 
    s = s.replace(/[\, ]/g,''); 
    if (s != parseFloat(s)) return 'not a number'; 
    var x = s.indexOf('.'); 
    if (x == -1) x = s.length; if (x > 15) return 'too big'; 
    var n = s.split(''); 
    var str = ''; 
    var sk = 0; 
    for (var i=0; i < x; i++) {
        if ((x-i)%3==2) {
            if (n[i] == '1') {
                str += tn[Number(n[i+1])] + ' '; i++; sk=1;
            } else if (n[i]!=0) {
                str += tw[n[i]-2] + ' ';sk=1;}
        } else if (n[i]!=0) {
            str += dg[n[i]] +' '; 
            if ((x-i)%3==0){
                str += 'hundred ';
                if(((parseInt(hundred))%100) !== 0){
                    if(((parseInt(hundred))%10) !== 0){
                        str += 'and ';
                    } else {
                        str += 'and ';
                    }                  
                }
            }
            sk=1;
        }             
        if ((x-i)%3==1) {
            if (sk) str += th[(x-i-1)/3] + ' ';
            sk=0;
        }
    }
    if(currency === 'THB'){
        str += ' BAHT ';
    }else if(currency === 'JPY'){
        str += ' YEN ';    
    }else if(currency === 'USD'){
        str += ' DOLLAR ';    
    }else{
        str += ''+currency;
    }
    if (x != s.length) {
        var y = s.length; str += 'point '; 
        for (var i=x+1; i<y; i++) str += dg[n[i]] +' ';
    } else {
        str += ' only ';
    }   
    return str.replace(/\s+/g,' ').toUpperCase();
}

function CalculateTotalNet(id) {
    var count = parseInt(document.getElementById('counterTable').value);
    var i;
    var grandTotal = 0;
    var totalnet=0;
    var amount = 0;
    var vatnet = 0;
    if ((id !== null) || (id !== '')) {
        for (i = 1; i < count + 1; i++) {
//            alert(".." + i);
            var amount_temp = document.getElementById('InputAmount' + i);
            var isVat_temp = document.getElementById('checkUse' + i);
            if(amount_temp !== null && isVat_temp !== null){
                amount = amount_temp.value;
                amount = amount.replace(/,/g, "");
                var grossTotal = 0;
                if (amount !== '' && isVat_temp.checked) {
                    var total = parseFloat(amount);
                    grandTotal += total;

//                    var vatT = $('#vatBase').val();
                    var vatT = $('#InputVatTemp'+i).val();
                    var vatTT = parseFloat(vatT);
                    grossTotal = parseFloat(((amount * 100) / (100 + vatTT)).toFixed(2));
                    totalnet += grossTotal;
                    vatnet += total - grossTotal;
                }else if(amount !== '' && !isVat_temp.checked){
                    var total = parseFloat(amount);
                    totalnet += total;
                    vatnet += 0.0;
                }
            }    
        }
//        vatnet = grandTotal-totalnet;
        if(document.getElementById('TotalNet') !== null){
            document.getElementById('TotalNet').value = formatNumber(totalnet);
        }    
        document.getElementById('VatNet').value = formatNumber(vatnet);
    }
}

function changeFormatGrossNumber(id) {
//    var count = document.getElementById('InputAmount' + id).value;
//
//    count = count.replace(/\,/g, '');
//    count = parseFloat(count);
//    if (isNaN(count)) {
//        document.getElementById('InputAmount' + id).value = "";
//        document.getElementById('InputGross' + id).value = "";
//    } else {
//        count = parseFloat(count);
//        document.getElementById('InputAmount' + id).value = formatNumber(count);
//    }
    CalculateGrandTotal(id);
    CalculateTotalNet(id);
    calculateGross(id);
}

function changeFormatAmountNumber(id) {
//    var count = document.getElementById('InputAmount' + id).value;
//    var type = $("#InputTypeInvoiceInbound").val();
//    var curamount = document.getElementById('SelectCurrencyAmount' + id).value;
//    if (curamount === '') {
//        $('#textAlertCurrencyAmountNotEmpty').show();
//    } else {
//        $('#textAlertInvoiceNotEmpty').hide();
//    }

//    count = count.replace(/\,/g, '');
//    count = parseFloat(count);
//    if (isNaN(count)) {
//        document.getElementById('InputAmount' + id).value = "";
//    } else {
//        count = parseFloat(count);
//        document.getElementById('InputAmount' + id).value = formatNumber(count);
//    }
    CalculateGrandTotal(id);
    calculateGross(id);
    if(type === 'RV'){
        CalculateTotalNet(id);
    }
}

function addRowInvoiceInboundDetail(row){
    $('#DetailBillableTable input:last').removeClass('lastrow');
    var typeInvoiceInbound = $("#InputTypeInvoiceInbound").val();
    var vatTemp = $('#vatBase').val();
    var textHidden = '';
    var textHiddenVat = 'class="text-center"';
    if(typeInvoiceInbound === "PM"){
        textHidden = 'class="hidden"';
        textHiddenVat = 'class="text-center hidden"'
    }
    var vat = $('#vatBase').val();
    $("#DetailBillableTable tbody").append(
    '<tr>' +
    '<td class=""><input type="hidden" class="form-control" id="detailId' + row + '" name="detailId' + row + '" value="" ><select class="form-control" name="product' + row + '" id="product' + row + '" ><option  value="" >---------</option></select> </td>' +
    '<td><input type="text" class="form-control" id="BillDescriptionTemp' + row + '" name="BillDescriptionTemp' + row + '"   value=""></td>' +
    '<td align="center" '+ textHidden+'><input type="checkbox" id="checkUse' + row + '" name="checkUse' + row + '" onclick="calculateGross(' + row + ')" value="" checked></td>' +
    '<td class="hidden" ><input type="text" id="InputVatTemp' + row + '" name="InputVatTemp' + row + '"  value="' + vat + '"></td>' +
    '<td '+ textHiddenVat+'>'+vatTemp+'</td>' +
    '<td '+ textHidden+'><input type="text" disabled="disabled" onfocusout="changeFormatGrossNumber(' + row + ')" class="form-control decimal" id="InputGross' + row + '" name="InputGross' + row + '" value="" ></td>' +
    '<td><input type="text" class="form-control decimal" id="InputAmount' + row + '" name="InputAmount' + row + '" onfocusout="changeFormatAmountNumber(' + row + ');"  value=""></td>' +
    '<td class="priceCurrencyAmount"><select id="SelectCurrencyAmount' + row + '" name="SelectCurrencyAmount' + row + '" class="form-control" onclick="checkCurrency()" onchange="CalculateGrandTotal(\'\')">' + select + '</select></td>' +              
    '<td align="center" ><span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBillInbound(' + row + ',\'\')" data-toggle="modal" data-target="#DelDetailBill" >  </span></td>' +           
    '</tr>'
    );
    $('[name=SelectCurrencyAmount' + row + '] option').filter(function() { 
        return ($(this).text() === 'THB');
    }).prop('selected', true);     
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
    $("#mBillTypeListTemp option").clone().appendTo("#product" + row);
    
    $('#DetailBillableTable input:last').addClass('lastrow');
    $("#product"+row+",#BillDescriptionTemp"+row+",#InputAmount"+row+",#SelectCurrencyAmount"+row).focus(function() {
        if($("#InputAmount"+(parseInt(row)-1)).hasClass("lastrow")){
           addRowInvoiceInboundDetail(parseInt($("#counterTable").val()) + 1);
        }            
    });
    
    var count = document.getElementById('counterTable');
    count.value = row++;
}

function saveInvoiceInbound(){
//    validFromInvoiceInbound();
    var actionG = document.getElementById('action');
    actionG.value = 'save';
    document.getElementById('InvoiceInboundForm').submit();
}

function clearScreenInvoiceInbound() {
    var action = document.getElementById('action');
    action.value = 'new';
    document.getElementById('InvoiceInboundForm').submit();

}

function DeleteDetailBillInbound(rowID, code) {
    code = $("#BillDescriptionTemp"+rowID).val();
    $("#idDeleteDetailBillable").val(rowID);
    if (code !== "") {
        $("#DeleteDetailBillable").text('Are you sure to delete detail billable : ' + code + '..?');
    } else {
        $("#DeleteDetailBillable").text('Are you sure to delete detail billable ?');
    }
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
        $("#BillDescriptionTemp" + rowId.value).parent().parent().remove();
        $('#DelDetailBill').modal('hide');
        console.log("Row 0  : " + count.value);
        if (count.value <= 1) {
            console.log("show button tr_FormulaAddRow : ");
            $("#tr_FormulaAddRow").css("display", "block");
        }
        count.value = count.value - 1;
        
        $('#DetailBillableTable input:last').removeClass('lastrow');
        $('#DetailBillableTable input:last').addClass('lastrow');
        
        CalculateTotalNet('1')
        CalculateGrandTotal('1');
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
                
                $('#textAlertInvoiceNotEmpty').hide();
                $('#textAlertInvoiceNotEmptyTax').hide();
                $('#textAlertInvoiceNotEmptyReceipt').hide();
                
                if (msg === 'success') {
                    console.log("Delete Detail");
                    $("#BillDescriptionTemp" + row).parent().parent().remove();
                } else if (msg === 'notDeleteReciptAndTax') {
                    $('#textAlertInvoiceNotEmpty').show();
                } else if (msg === 'notDeleteTax') {
                    $('#textAlertInvoiceNotEmptyTax').show();
                } else if (msg === 'notDeleteRecipt') {
                    $('#textAlertInvoiceNotEmptyReceipt').show();
                }
                
                $('#DetailBillableTable input:last').removeClass('lastrow');
                $('#DetailBillableTable input:last').addClass('lastrow');
                
                $("#ajaxload").addClass("hidden");
                CalculateTotalNet('1')
                CalculateGrandTotal('1');
        
            }, error: function(msg) {
                $("#ajaxload").addClass("hidden");
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function DisableVoidInvoice() {
    var InvNo = document.getElementById('InvNo');
    document.getElementById('disableVoidModal').innerHTML = "Are you sure to delete booking other : " + InvNo.value + " ?";
}

function DisableInvoice() {
    var action = document.getElementById('action');
    action.value = 'disableVoid';
    document.getElementById('InvoiceInboundForm').submit();
}

function Enable() {
    var action = document.getElementById('action');
    action.value = 'enableVoid';
    document.getElementById('InvoiceInboundForm').submit();
}

function EnableVoidInvoice() {
    var InvNo = document.getElementById('InvNo');
    document.getElementById('enableVoid').innerHTML = "Are you sure to enable booking other : " + InvNo.value + " ?";
}

function printInvoiceInbound(text, report) {
    $('#typePrint').val(text);
    $('#typeReport').val(report);
}

function printInvoiceInboundNew() {
    var invoiceId = $('#InvoiceInboundId').val();
    var typePrint = $('#SelectTypePrint').val();
    var sale = 0;
    var leader = $('#selectLeader').val();
    var payment = $('#selectPayment').val();
    var type = $('#typePrint').val();
    var typeReport = $('#typeReport').val();
    var sign = $('#SelectSign').val();
    var invoiceType = $('#InputTypeInvoiceInbound').val();
    console.log("Invoice TYpe : " + invoiceType + " TYpe : " + type);
    if (type === 'print') {
        if (invoiceType === 'PM') {
            window.open("report.smi?name=InvoiceInboundPerformaReport&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        } else {
            window.open("report.smi?name=InvoiceInboundRevenueReport"+ "&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        }
    } else if (type === 'email') {
        if (invoiceType === 'PM') {
            window.open("report.smi?name=InvoiceInboundPerformaEmail&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        } else {
            window.open("report.smi?name=InvoiceInboundRevenueEmail&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        }
    }else if (type === 'sendemail') {
        if (invoiceType === 'PM') {
            window.open("SendMail.smi?reportname=InvoiceInboundPerformaEmail&reportid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        } else {
            window.open("SendMail.smi?reportname=InvoiceInboundRevenueEmail&reportid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        }
    }
}
var currency = 0;
function validFromInvoiceInbound() {
    var invTo = $('#InvTo').val();
    var invToName = $('#InvToName').val();
    var arCode = $('#ARCode').val();
    var invDate = $('#InputInvDate').val();
    if(invTo !== '' && invToName !== '' && arCode !== '' && invDate !== ''){
        var counter = $('#DetailBillableTable tbody tr').length;
        var different = 0;
        var first = 0;
        var checkcur1 = false;
        for(var i=1; i<counter; i++){
            if($('#SelectCurrencyAmount' + i).val() !== undefined){
                var cur1 = $('#SelectCurrencyAmount' + i).find(":selected").text();
                var billDescriptionTemp1 = $('#BillDescriptionTemp' + i).val();
                var amount1 = $('#InputAmount' + i).val();
                if((billDescriptionTemp1 !== '' || amount1 !== '') && (first === 0) && (cur1 === '')){
                    checkcur1 = true;
                    first++;
                }
                for(var j=i+1; j<counter; j++){
                    if($('#SelectCurrencyAmount' + j).val() !== undefined){
                        var cur2 = $('#SelectCurrencyAmount' + j).find(":selected").text(); 
                        var billDescriptionTemp2 = $('#BillDescriptionTemp' + j).val();
                        var amount2 = $('#InputAmount' + j).val();
                        if((cur2 !== '' || billDescriptionTemp2 !== '' || amount2 !== '') && (cur1 !== cur2)){
                            rowTemp = j;
                            different++;
                        }
                        if(different > 0){
                            i = counter+1;
                            j = counter+1;
                        }
                    }
                }
            }            
        }

//        for (var i = 1; i <= (counter); i++) {
//            var currency1 = $('#SelectCurrencyAmount' + i).find(":selected").text();
//            var BillDescriptionTemp1 = $('#BillDescriptionTemp' + i).val();
//            var InputAmount1 = $('#InputAmount' + i).val();
//
//            if (currency1 !== '' || BillDescriptionTemp1 !== '' || InputAmount1 !== '') {
//                checkcur1 = true;
//            } else {
//                $('#textAlertCurrencyAmountNotEmpty').hide();
//            }
//            for (var j = 2; j <= (counter); j++) {
//                var type = $('#BillDescriptionTemp' + j).val();
//                if (type !== "") {
//                    var currency2 = $('#SelectCurrencyAmount' + j).find(":selected").text();
//                    if (currency1 !== currency2 || currency2 === '') {
//                        rowTemp = j;
//                        different++;
//                    }
//                }
//            }
//        }

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

            if (checkcur1) {
                $('#textAlertCurrencyAmountNotEmpty').show();
                return ;
            }
            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvTo');
            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvToName');
            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'ARCode');
            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InputInvDate');        
            return ;
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

            if (checkcur1) {
                $('#textAlertCurrencyAmountNotEmpty').show();
                $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvTo');
                $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvToName');
                $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'ARCode');
                $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InputInvDate');
                return ;
            } else {
                $('#textAlertInvoiceNotEmpty').hide();
                $('#textAlertInvoiceNotEmptyTax').hide();
                $('#textAlertInvoiceNotEmptyReceipt').hide();
            }

            var action = document.getElementById('action');
            action.value = 'save';
            document.getElementById('InvoiceInboundForm').submit();

        }
    }else{
        $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvTo');
        $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvToName');
        $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'ARCode');
        $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InputInvDate');        
    }   
}

function checkCurrency(){
//    var counter = $('#DetailBillableTable tbody tr').length;
//    var different = 0;
//    var checkcur1 = false;
//    for (var i = 1; i <= (counter - 1); i++) {
//        var currency1 = $('#SelectCurrencyAmount' + i).find(":selected").text();
//        if (currency1 === '') {
//            checkcur1 = true;
//        } else {
//            $('#textAlertCurrencyAmountNotEmpty').hide();
//        }
//        for (var j = 2; j <= (counter - 1); j++) {
//            var type = $('#BillDescriptionTemp' + j).val();
//            if (type !== "") {
//                var currency2 = $('#SelectCurrencyAmount' + j).find(":selected").text();
//                if (currency1 !== currency2 || currency2 === '') {
//                    rowTemp = j;
//                    different++;
//                }
//            }
//        }
//    }
//    if (different > 0) {
//        $('#DetailBillableTable').find('tr').each(function() {
//            $(this).find('td').each(function() {
//                if ($(this).hasClass('priceCurrencyAmount')) {
//                    $(this).addClass("alert-danger");
//                }
//            });
//        });
//        $('#textAlertCurrency').show();
//        currency = 1;
//        
//        if (checkcur1) {
//            $('#textAlertCurrencyAmountNotEmpty').show();
//            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvTo');
//            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvToName');
//            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'ARCode');
//            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InputInvDate');
//            return ;
//        }
//        $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvTo');
//        $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvToName');
//        $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'ARCode');
//        $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InputInvDate');
//
//    } else {
//        $('#DetailBillableTable').find('tr').each(function() {
//            $(this).find('td').each(function() {
//                if ($(this).hasClass('priceCurrencyAmount')) {
//                    $(this).removeClass("alert-danger");
//                }
//            });
//        });
//        $('#textAlertCurrency').hide();
//        currency = 0;
//
//        if (checkcur1) {
//            $('#textAlertCurrencyAmountNotEmpty').show();
//            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvTo');
//            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InvToName');
//            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'ARCode');
//            $('#InvoiceInboundForm').bootstrapValidator('revalidateField', 'InputInvDate');
//            return ;
//        } else {
//            $('#textAlertInvoiceNotEmpty').hide();
//        }
//    }
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
//        clearInvoice();
    } else if (bla === "notDeleteReciptAndTax") {
        $("#textAlertInvoiceNotEmpty").show();
    } else if (bla === "notDeleteTax") {
        $("#textAlertInvoiceNotEmptyTax").show();
    } else if (bla === "notDeleteRecipt") {
        $("#textAlertInvoiceNotEmptyReceipt").show();
    }
});