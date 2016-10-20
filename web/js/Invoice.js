/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. test
 */
$(document).ready(function() {
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
    $(".decimalexrate").inputmask({
        alias: "decimal",
        integerDigits: 20,
        groupSeparator: ',',
        autoGroup: true,
        digits: 10,
        allowMinus: false,
        digitsOptional: false,
        placeholder: "0.0000000000"
    });
    
    $("#InvNo").focus();
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
//            $("#InvTo").val(staff_code);
//            $("#InvToName").val(staff_name);
//            $("#InvToAddress").val(staff_dress);
//            $("#ARCode").val(staff_code);
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

//    validFromInvoice();

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
        changeFormatExRateNumber(j)
        calculateAmountLocal(j,'setEvent');
    }

    var invoiceNumber = $('#InvNo').val();
    if (invoiceNumber === '') {
        document.getElementById("printButton").disabled = true;
        document.getElementById("printButtonEmail").disabled = true;
        document.getElementById("sendEmailButton").disabled = true;
    }

    //autocomplete
    $("#SearchRefNo").keyup(function(event) {
        if (event.keyCode === 13) {
            searchAction();         
        }
    });
    
    //Operation Duplicate
    if($("#isDuplicate").val() === '1'){
        var username = $("#operationUser").val();
        $("#operationMessage").text("User " + username + " is using information. Do you want to continue ?");
        $("#operationModal").modal("show");
    }

});

function searchInvoiceFromInvoiceNo() {
    var action = document.getElementById('action');
    action.value = 'searchInvoice';
    document.getElementById('InvoiceForm').submit();
}

function validFromInvoice(){
    var departmentPage = $("#InputInvoiceType").val();    
    if(departmentPage !== 'W/A'){
        var invDate = $("#InputInvDate").val();
        var invTo = $("#InvTo").val();
        var invToName = $("#InvToName").val();
        var arCode = $("#ARCode").val();
        if(invDate === '' || invTo === '' || invToName === '' || arCode === ''){
            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InputInvDate');
            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InvTo');
            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InvToName');
            $('#InvoiceForm').bootstrapValidator('revalidateField', 'ARCode');
            return;
        }
        
    }else if(departmentPage === 'W/A'){
        var invDate = $("#InputInvDate").val();
        var invTo = $("#InvTo").val();
        var invToName = $("#InvToName").val();
        var arCode = $("#ARCode").val();
        var departmentAirticket = $("#DepartmentAirTicket").is(":checked");
        var departmentPackage = $("#DepartmentPackage").is(":checked");
        if(invDate === '' || invTo === '' || invToName === '' || arCode === '' || !(departmentAirticket || departmentPackage)){
            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InputInvDate');
            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InvTo');
            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InvToName');
            $('#InvoiceForm').bootstrapValidator('revalidateField', 'ARCode');
            $('#InvoiceForm').bootstrapValidator('revalidateField', 'Department');
            return;
        }
    }

    var count = parseInt($("#counterTable").val());   
    //Check Currency
    var currencyNotMatch = false;
    var currencyNotEmpty = 0;
    for(var i=1; i<=count; i++){
        var currency1 = document.getElementById('SelectCurrencyAmount'+i);
        var product1 = document.getElementById('SelectProductType'+i);
        var description1 = document.getElementById('BillDescription'+i);
        var cost1 = document.getElementById('InputCost'+i);
        var amount1 = document.getElementById('InputAmount'+i);
        var curCost1 = document.getElementById('SelectCurrencyCost'+i);
        var costLocal1 = document.getElementById('InputCostLocal'+i);
        var exRate1 = document.getElementById('InputExRate'+i);
        var amountLocal1 = document.getElementById('InputAmountLocal'+i);
        if(currency1 !== null){
            if(product1.value !== '' || description1.value !== '' || cost1.value !== '' || amount1.value !== '' || curCost1.value !== '' || costLocal1.value !== '' || exRate1.value !== '' || amountLocal1.value !== ''){
                var currencyTemp1 = currency1.value;
                for(var j=i+1; j<=count; j++){
                    var currency2 = document.getElementById('SelectCurrencyAmount'+j);
                    var product2 = document.getElementById('SelectProductType'+j);
                    var description2 = document.getElementById('BillDescription'+j);
                    var cost2 = document.getElementById('InputCost'+j);
                    var amount2 = document.getElementById('InputAmount'+j);
                    var curCost2 = document.getElementById('SelectCurrencyCost'+j);
                    var costLocal2 = document.getElementById('InputCostLocal'+j);
                    var exRate2 = document.getElementById('InputExRate'+j);
                    var amountLocal2 = document.getElementById('InputAmountLocal'+j);
                    if(currency2 !== null){
                        var currencyTemp2 = currency2.value;
                        if(product2.value !== '' || description2.value !== '' || cost2.value !== '' || amount2.value !== '' || curCost2.value !== '' || costLocal2.value !== '' || exRate2.value !== '' || amountLocal2.value !== ''){                               
                            if((currencyTemp1 !== currencyTemp2)){
                                currencyNotMatch = true;
                                i = count+1;
                                j = count+1;
                            }                               
                        }
                        if(currencyTemp1 === '' && currencyTemp2 === ''){
                            currencyNotEmpty++;
                        }
                    }
                }
            }    
        }    
    }
    if(currencyNotMatch){
        $("#textAlertCurrencyAmountNotEmpty").hide();
        $("#textAlertCurrency").hide();
       for(var i=1; i<=count; i++){
            var currency = document.getElementById('SelectCurrencyAmount'+i);
            var product = document.getElementById('SelectProductType'+i);
            var description = document.getElementById('BillDescription'+i);
            var cost = document.getElementById('InputCost'+i);
            var amount = document.getElementById('InputAmount'+i);
            var curCost = document.getElementById('SelectCurrencyCost'+i);
            var costLocal = document.getElementById('InputCostLocal'+i);
            var exRate = document.getElementById('InputExRate'+i);
            var amountLocal = document.getElementById('InputAmountLocal'+i);
            if(currency !== null){
                if(product.value !== '' || description.value !== '' || cost.value !== '' || amount.value !== '' || curCost.value !== '' || costLocal.value !== '' || exRate.value !== '' || amountLocal.value !== ''){  
                    currency.style.borderColor = 'red';
                }    
            }    
        }
        $("#textAlertCurrency").show();
        return;
    }
    if(currencyNotEmpty > 0){
        $("#textAlertCurrencyAmountNotEmpty").hide();
        $("#textAlertCurrency").hide();
        for(var i=1; i<=count; i++){
            var currency = document.getElementById('SelectCurrencyAmount'+i);
            var product = document.getElementById('SelectProductType'+i);
            var description = document.getElementById('BillDescription'+i);
            var cost = document.getElementById('InputCost'+i);
            var amount = document.getElementById('InputAmount'+i);
            var curCost = document.getElementById('SelectCurrencyCost'+i);
            var costLocal = document.getElementById('InputCostLocal'+i);
            var exRate = document.getElementById('InputExRate'+i);
            var amountLocal = document.getElementById('InputAmountLocal'+i);
            if(currency !== null){
                if(product.value !== '' || description.value !== '' || cost.value !== '' || amount.value !== '' || curCost.value !== '' || costLocal.value !== '' || exRate.value !== '' || amountLocal.value !== ''){  
                    currency.style.borderColor = 'red';
                }    
            }     
        }
        $("#textAlertCurrencyAmountNotEmpty").show();
        return;
    }

    if(!currencyNotMatch && currencyNotEmpty === 0){
        $("#textAlertCurrencyAmountNotEmpty").hide();
        $("#textAlertCurrency").hide();
        $("#action").val('save');
        document.getElementById('InvoiceForm').submit();
    }

}

//var currency = 0;
//function validFromInvoice() {
//    var departmentPage = $("#departmentPage").val();    
//    if(departmentPage !== 'ticket'){
//        var invDate = $("#InputInvDate").val();
//        var invTo = $("#InvTo").val();
//        var invToName = $("#InvToName").val();
//        var arCode = $("#ARCode").val();
//        if(invDate === '' || invTo === '' || invToName === '' || arCode === ''){
//            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InputInvDate');
//            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InvTo');
//            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InvToName');
//            $('#InvoiceForm').bootstrapValidator('revalidateField', 'ARCode');
//            return;
//        }
//        
//    }else if(departmentPage === 'ticket'){
//        var invDate = $("#InputInvDate").val();
//        var invTo = $("#InvTo").val();
//        var invToName = $("#InvToName").val();
//        var arCode = $("#ARCode").val();
//        var departmentAirticket = $("#DepartmentAirTicket").is(":checked");
//        var departmentPackage = $("#DepartmentPackage").is(":checked");
//        if(invDate === '' || invTo === '' || invToName === '' || arCode === '' || !(departmentAirticket || departmentPackage)){
//            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InputInvDate');
//            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InvTo');
//            $('#InvoiceForm').bootstrapValidator('revalidateField', 'InvToName');
//            $('#InvoiceForm').bootstrapValidator('revalidateField', 'ARCode');
//            $('#InvoiceForm').bootstrapValidator('revalidateField', 'Department');
//            return;
//        }
//    }    
//
////    var counter = $('#DetailBillableTable tbody tr').length;
//    var counter = parseInt($("#counterTable"));
//    var different = 0;
//    var rowTemp = 0;
//    var checkcur1 = false;
//    for (var i = 1; i <= (counter - 1); i++) {
//        var currency1 = $('#SelectCurrencyAmount' + i).find(":selected").text();
//        if (currency1 === '') {
//            checkcur1 = true;
//        } else {
//            $('#textAlertCurrencyAmountNotEmpty').hide();
//        }
//        for (var j = 2; j <= (counter - 1); j++) {
//            var type = $('#SelectProductType' + j).find(":selected").text();
//            if (type !== "") {
//                var currency2 = $('#SelectCurrencyAmount' + j).find(":selected").text();
//                if (currency1 !== currency2) {
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
//        return;
//        currency = 1;
////        document.getElementById("saveInvoice").disabled = true;
////        alert("Currency : " + currency); 
////        $('#InvoiceForm').bootstrapValidator('validateField', 'SelectCurrencyAmount2'+rowTemp);
//        if (checkcur1) {
//            $('#textAlertCurrencyAmountNotEmpty').show();
//            return;
////            document.getElementById("saveInvoice").disabled = true;
//        }
////        return false;
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
////        document.getElementById("saveInvoice").disabled = false;
//        if (checkcur1) {
//            $('#textAlertCurrencyAmountNotEmpty').show();
////            document.getElementById("saveInvoice").disabled = true;
//        } else {
//            $('#textAlertInvoiceNotEmpty').hide();
////            document.getElementById("saveInvoice").disabled = false;
//            document.getElementById('InvoiceForm').submit();
//        }
////        return true;
//    }
//}

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
function AddRowDetailBillAble(row, prod, des, cos, id, price, RefNo, cur, cur_c, refItemId, productName) {

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
    if (refItemId === undefined) {
        refItemId = "";
    }
    if (productName === undefined) {
        productName = "";
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
    $('#DetailBillableTable tr input:last').removeClass('lastrow');
    if (des != '' || RefNo != '') {
        $("#DetailBillableTable tbody").append(
                '<tr>' +
                '<td class="hidden"><input type="text" class="form-control" id="detailId' + row + '" name="detailId' + row + '" value="" > </td>' +
                '<td class="hidden"><input type="text" class="form-control" id="DetailBillId' + row + '" name="DetailBillId' + row + '" value="' + id + '" > </td>' +
                '<td><select id="SelectProductType' + row + '" name="SelectProductType' + row + '" class="form-control" onchange="AddrowBySelect(\'' + row + '\');">' + selectT + '</select> </td>' +
                '<td><input type="text" class="form-control" id="BillDescriptionTemp' + row + '" name="BillDescriptionTemp' + row + '" value="" onkeyup="setDescription(' + row + ')" onchange="setDescription(' + row + ')"></td>' +
                '<td class="hidden"><input type="text" class="form-control" id="BillDescription' + row + '" name="BillDescription' + row + '" value="' + des + '" > </td>' +
                '<td><input type="text" onfocusout="changeFormatCostNumber(' + row + ')" class="form-control decimal text-right" id="InputCost' + row + '" name="InputCost' + row + '" value="' + cos + '" ></td>' +
                '<td><select id="SelectCurrencyCost' + row + '" name="SelectCurrencyCost' + row + '" class="form-control" onchange="AddrowBySelect(\'' + row + '\');">' + selectCC + '</select></td>' +
                '<td><input type="text" onfocusout="changeFormatCostLocalNumber(' + row + ')"  value="' + cos + '" id="InputCostLocal' + row + '" name="InputCostLocal' + row + '" class="form-control text-right decimal"></td>' +
                '<td class="hidden"><input type="text" value="' + cos + '" id="InputCostLocalTemp' + row + '" name="InputCostLocalTemp' + row + '"></td>' +
                '<td  ' + vathidden + '><input type="checkbox" ' + check + ' id="checkUse' + row + '" name="checkUse' + row + '"  onclick="calculateGross(' + row + ')"></td>' +
                '<td align="center" ' + vathidden + '>' + vatValue + '</td>' +
                '<td class="hidden"><input type="text" class="form-control" id="InputVatTemp' + row + '" name="InputVatTemp' + row + '" value="' + vat + '" ></td>' +
                '<td ' + vathidden + ' ><input type="text" tabIndex="-1" readonly  onfocusout="changeFormatGrossNumber(' + row + ')" class="form-control decimal text-right" id="InputGross' + row + '" name="InputGross' + row + '" value="" ></td>' +
                '<td><input type="text" onfocusout="changeFormatAmountNumber(' + row + ');" class="form-control decimal text-right" id="InputAmount' + row + '" name="InputAmount' + row + '" value="' + price + '" ></td>' +
                '<td class="priceCurrencyAmount"><select id="SelectCurrencyAmount' + row + '" name="SelectCurrencyAmount' + row + '" class="form-control" onclick="" onchange="CalculateGrandTotal(\'\'); checkCurrency(\'' + row + '\'); AddrowBySelect(\'' + row + '\');">' + selectC + '</select></td>' +
                '<td><input type="text" id="InputExRate' + row + '" onfocusout="changeFormatExRateNumber(' + row + ')" name="InputExRate' + row + '" class="form-control text-right decimalexrate" ></td>' +
                '<td><input type="text" onfocusout="changeFormatAmountLocalNumber(' + row + ')" value="' + price + '" id="InputAmountLocal' + row + '" name="InputAmountLocal' + row + '" class="form-control text-right decimal" ></td>' +
                '<td class="hidden"><input type="text" onfocusout="changeFormatAmountLocalTempNumber(' + row + ')" value="' + price + '" id="InputAmountLocalTemp' + row + '" name="InputAmountLocalTemp' + row + '"  ></td>' +
                '<td align="center" ><span  class="glyphicon glyphicon-th-list" data-toggle="modal" data-target="#DescriptionInvoiceDetailModal" onclick="getDescriptionDetail(' + row + ')" id="InputDescription' + row + '"></span><span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill(' + row + ',\'\')" data-toggle="modal" data-target="#DelDetailBill" >  </span></td>' +
                '<td class="hidden"><textarea id="DescriptionInvoiceDetail' + row + '" name="DescriptionInvoiceDetail' + row + '"> ' + description + '</textarea> </td>' +
                '<td class="hidden"><textarea id="DescriptionInvoiceDetailCheck' + row + '" name="DescriptionInvoiceDetailCheck' + row + '"> ' + description + '</textarea> </td>' +
                '<td class="hidden"><input type="text" value="' + prod + '" id="mBilltypeId' + row + '" name="mBilltypeId' + row + '" class="form-control" ></td>' +
                '<td class="hidden"><input type="text" value="' + productName + '" id="mBilltypeName' + row + '" name="mBilltypeName' + row + '" class="form-control" ></td>' +
                '<td class="hidden"><input type="text" value="' + refItemId + '" id="refItemId' + row + '" name="refItemId' + row + '" class="form-control" ></td>' +
                '<td class="hidden"><input type="text" value="' + RefNo + '" id="refNo' + row + '" name="refNo' + row + '" class="form-control" ></td>' +
                '<td class="hidden"><input type="text" value="' + selectC + '" id="curExRateTemp' + row + '" name="curExRateTemp' + row + '" class="form-control" ></td>' +
                '</tr>'
                );
        calculateAmountLocal(row, 'amountLocal');

    } else if (des == '' || RefNo == '') {
        $("#DetailBillableTable tbody").append(
                '<tr>' +
                '<td class="hidden"><input type="text" class="form-control" id="detailId' + row + '" name="detailId' + row + '" value="" > </td>' +
                '<td class="hidden"><input type="text" class="form-control" id="DetailBillId' + row + '" name="DetailBillId' + row + '" value="' + id + '" > </td>' +
                '<td><select id="SelectProductType' + row + '" name="SelectProductType' + row + '" class="form-control" onchange="AddrowBySelect(\'' + row + '\');">' + selectT + '</select> </td>' +
                '<td><input type="text" class="form-control" id="BillDescriptionTemp' + row + '" name="BillDescriptionTemp' + row + '" value="" onkeyup="setDescription(' + row + ')" onchange="setDescription(' + row + ')"></td>' +
                '<td class="hidden"><input type="text" class="form-control" id="BillDescription' + row + '" name="BillDescription' + row + '" value="' + des + '" > </td>' +
                '<td><input type="text" onfocusout="changeFormatCostNumber(' + row + ')" class="form-control decimal text-right" id="InputCost' + row + '" name="InputCost' + row + '" value="' + cos + '" ></td>' +
                '<td><select id="SelectCurrencyCost' + row + '" name="SelectCurrencyCost' + row + '" class="form-control" onchange="AddrowBySelect(\'' + row + '\');">' + selectCC + '</select></td>' +
                '<td><input type="text" onfocusout="changeFormatCostLocalNumber(' + row + ')"  value="' + cos + '" id="InputCostLocal' + row + '" name="InputCostLocal' + row + '" class="form-control text-right decimal"></td>' +
                '<td class="hidden"><input type="text" value="' + cos + '" id="InputCostLocalTemp' + row + '" name="InputCostLocalTemp' + row + '"></td>' +
                '<td  ' + vathidden + '><input type="checkbox" ' + check + ' id="checkUse' + row + '" name="checkUse' + row + '"  onclick="calculateGross(' + row + ')"></td>' +
                '<td align="center" ' + vathidden + '>' + vatValue + '</td>' +
                '<td class="hidden"><input type="text" class="form-control" id="InputVatTemp' + row + '" name="InputVatTemp' + row + '" value="' + vat + '" ></td>' +
                '<td ' + vathidden + ' ><input type="text" tabIndex="-1" readonly onfocusout="changeFormatGrossNumber(' + row + ')" class="form-control decimal text-right" id="InputGross' + row + '" name="InputGross' + row + '" value="" ></td>' +
                '<td><input type="text" onfocusout="changeFormatAmountNumber(' + row + ');" class="form-control decimal text-right" id="InputAmount' + row + '" name="InputAmount' + row + '"  value="' + price + '" ></td>' +
                '<td class="priceCurrencyAmount"><select id="SelectCurrencyAmount' + row + '" name="SelectCurrencyAmount' + row + '" class="form-control" onclick="" onchange="CalculateGrandTotal(\'\'); checkCurrency(\'' + row + '\'); AddrowBySelect(\'' + row + '\');">' + selectC + '</select></td>' +
                '<td><input type="text" id="InputExRate' + row + '" onfocusout="changeFormatExRateNumber(' + row + ')" name="InputExRate' + row + '" class="form-control text-right decimalexrate" ></td>' +
                '<td><input type="text" onfocusout="changeFormatAmountLocalNumber(' + row + ')" value="' + price + '" id="InputAmountLocal' + row + '" name="InputAmountLocal' + row + '" class="form-control text-right decimal" ></td>' +
                '<td class="hidden"><input type="text" onfocusout="changeFormatAmountLocalTempNumber(' + row + ')" value="' + price + '" id="InputAmountLocalTemp' + row + '" name="InputAmountLocalTemp' + row + '"  ></td>' +
                '<td align="center" ><span  class="glyphicon glyphicon-th-list" data-toggle="modal" data-target="#DescriptionInvoiceDetailModal" onclick="getDescriptionDetail(' + row + ')" id="InputDescription' + row + '"></span><span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill(' + row + ',\'\')" data-toggle="modal" data-target="#DelDetailBill" >  </span></td>' +
                '<td class="hidden"><textarea id="DescriptionInvoiceDetail' + row + '" name="DescriptionInvoiceDetail' + row + '"> ' + description + '</textarea> </td>' +
                '<td class="hidden"><textarea id="DescriptionInvoiceDetailCheck' + row + '" name="DescriptionInvoiceDetailCheck' + row + '"> ' + description + '</textarea> </td>' +
                '<td class="hidden"><input type="text" value="' + prod + '" id="mBilltypeId' + row + '" name="mBilltypeId' + row + '" class="form-control" ></td>' +
                '<td class="hidden"><input type="text" value="' + productName + '" id="mBilltypeName' + row + '" name="mBilltypeName' + row + '" class="form-control" ></td>' +
                '<td class="hidden"><input type="text" value="' + refItemId + '" id="refItemId' + row + '" name="refItemId' + row + '" class="form-control" ></td>' +
                '<td class="hidden"><input type="text" value="' + RefNo + '" id="refNo' + row + '" name="refNo' + row + '" class="form-control" ></td>' +
                '<td class="hidden"><input type="text" value="' + selectC + '" id="curExRateTemp' + row + '" name="curExRateTemp' + row + '" class="form-control" ></td>' +
                '</tr>'
                );
        calculateAmountLocal(row, 'amountLocal');
    }
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
    $(".decimalexrate").inputmask({
        alias: "decimal",
        integerDigits: 20,
        groupSeparator: ',',
        autoGroup: true,
        digits: 10,
        allowMinus: false,
        digitsOptional: false,
        placeholder: "0.0000000000"
    });
    $('#DetailBillableTable input:last').addClass('lastrow');
    $("#SelectProductType"+row+",#BillDescriptionTemp"+row+",#InputCost"+row+",#SelectCurrencyCost"+row+",#InputCostLocal"+row+",#InputAmount"+row+",#SelectCurrencyAmount"+row+",#InputExRate"+row+",#InputAmountLocal"+row).focus(function() {
        if($("#curExRateTemp"+(parseInt(row)-1)).hasClass("lastrow")){
           AddRowDetailBillAble(parseInt($("#counterTable").val()) + 1);
        }            
    });
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
    $('#DescriptionInvoiceDetail' + row).val(descriptionDetail);
}
function cancelDescriptionDetail() {
    var row = $("#InputDescriptionDetailId").val();
    var product = $("#mBilltypeName" + row).val();
    var refNo = $("#refNo" + row).val();
    var refItemId = $("#refItemId" + row).val();
    var prod = $("#mBilltypeId" + row).val();
    if (product !== '' && refNo !== '' && refItemId !== '' && prod !== '') {
        var servletName = 'InvoiceServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&refNo=' + refItemId +
                '&typeId=' + prod +
                '&type=' + 'searchInvoiceDescription';
        //    alert("row : "+row+" product : "+product+" refNo : "+refNo+" refItemId : "+refItemId+" prod : "+prod);
        CallAjaxSearchDescriptionCancel(param, row, product, refNo);
    } else {
        var description = $('#DescriptionInvoiceDetailCheck' + row).val();
        $('#InputDescriptionDetailId').val(row);
        $('#InputDescriptionDetail').val(description);

    }
}
function subStringDescription(description, row) {
    var index = description.indexOf("\n");
    var product = $('#SelectProductType' + row + '  option:selected').text();
    console.log("Product :" + product);
    var des = description.substring(0, index);
    console.log("description :" + description);

}
function changeFormatAmountNumber(id) {
//    var count = document.getElementById('InputAmount' + id).value;
//
//    var curamount = document.getElementById('SelectCurrencyAmount' + id).value;
//    if (curamount === '') {
//        $('#textAlertCurrencyAmountNotEmpty').show();
//        document.getElementById("saveInvoice").disabled = true;
//    } else {
//        $('#textAlertInvoiceNotEmpty').hide();
//        document.getElementById("saveInvoice").disabled = false;
//    }
//
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
}
function changeFormatAmountLocalNumber(id) {
//    var count = parseFloat(document.getElementById('InputAmountLocal' + id).value);
//
//    if (isNaN(count)) {
//        document.getElementById('InputAmountLocal' + id).value = "";
//    } else {
//        count = parseFloat((document.getElementById('InputAmountLocal' + id).value).replace(/,/g, ""));
//        document.getElementById('InputAmountLocal' + id).value = formatNumber(count);
//    }
}
function changeFormatAmountLocalTempNumber(id) {
//    var count = parseFloat(document.getElementById('InputAmountLocalTemp' + id).value);
//
//    if (isNaN(count)) {
//        document.getElementById('InputAmountLocalTemp' + id).value = "";
//    } else {
//        count = parseFloat(document.getElementById('InputAmountLocalTemp' + id).value);
//        document.getElementById('InputAmountLocalTemp' + id).value = formatNumber(count);
//    }
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
//    var count = parseFloat(document.getElementById('InputCostLocal' + id).value);
//
//    if (isNaN(count)) {
//        document.getElementById('InputCostLocal' + id).value = "";
//    } else {
//        count = parseFloat((document.getElementById('InputCostLocal' + id).value).replace(/,/g, ""));
//        document.getElementById('InputCostLocal' + id).value = formatNumber(count);
//    }
}
function changeFormatGrossNumber(id) {
    //    var count = parseFloat(document.getElementById('InputGross' + id).value);
//    if (isNaN(count)) {
//        document.getElementById('InputGross' + id).value = "";
//    } else {
//        count = parseFloat(document.getElementById('InputGross' + id).value);
//        document.getElementById('InputGross' + id).value = formatNumber(count);
//    }

//    var count = document.getElementById('InputAmount' + id).value;

//    var curamount = document.getElementById('SelectCurrencyAmount' + id).value;
//    if (curamount === '') {
//        $('#textAlertCurrencyAmountNotEmpty').show();
//        document.getElementById("saveInvoice").disabled = true;
//    } else {
//        $('#textAlertInvoiceNotEmpty').hide();
//        document.getElementById("saveInvoice").disabled = false;
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
}
function changeFormatExRateNumber(id) {
//    var count = parseFloat(document.getElementById('InputExRate' + id).value);
//
//    if (isNaN(count)) {
//        document.getElementById('InputExRate' + id).value = "";
//    } else {
//        count = parseFloat((document.getElementById('InputExRate' + id).value).replace(/,/g, ""));
//        document.getElementById('InputExRate' + id).value = formatExRateNumber(count);
//    }
}

function DeleteDetailBill(rowID, code) {
    if(code === ""){
        code = $("#BillDescription"+rowID).val();
    }
    $("#idDeleteDetailBillable").val(rowID);
    var operationAction = $("#action").val();
    var operationTable = $("#operationTable").val();
    var operationTableId = $("#operationTableId").val();
    var operationUser = $("#username").val();
    var operationDate = $("#operationDate").val();
    checkDuplicateUserActionDelete(operationTable, operationTableId, operationAction, operationUser,operationDate,code);

}   
    function checkDuplicateUserActionDelete(operationTable, operationTableId, operationAction, operationUser ,operationDate,code) {
        var servletName = 'CheckDuplicateUserServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&operationTable=' + operationTable +
                '&operationTableId=' + operationTableId +
                '&operationAction=' + operationAction +
                '&operationUser=' + operationUser +
                '&operationDate=' + operationDate + 
                '&type=' + 'checkOperationUser';
        callAjaxCheckDuplicateUserActionDelete(param,code);
    }

    function callAjaxCheckDuplicateUserActionDelete(param,code) {
        var url = 'AJAXServlet';
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function (msg) {
                    if(msg === 'success'){
                        if (code !== "") {
                            $("#DeleteDetailBillable").text('Are you sure to delete detail billable : ' + code + '..?');
                        } else {
                            $("#DeleteDetailBillable").text('Are you sure to delete detail billable ?');
                        }
                    }else{
                        var username = msg;
                        $("#operationMessage").text("User " + username + " is using information. Do you want to continue ?");
                        $("#operationModal").modal("show");
                    }
                }, error: function (msg) {
                    console.log('update duplicate user fail');
                }
            });
        } catch (e) {
            alert(e);
            console.log('update duplicate user fail');
        }
    }
    
function DisableVoidInvoice() {
    var InvNo = document.getElementById('InvNo');
    document.getElementById('disableVoidModal').innerHTML = "Are you sure to delete booking other : " + InvNo.value + " ?";
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

function printInvoice(text, report) {
    $('#typePrint').val(text);
    $('#typeReport').val(report);
}

function printInvoiceNew() {
    var invoiceId = $('#InvoiceId').val();
    var typePrint = $('#SelectTypePrint').val();
    var sale = $('#selectSalesStaff').val();
    var leader = $('#selectLeader').val();
    var payment = $('#selectPayment').val();
    var type = $('#typePrint').val();
    var typeReport = $('#typeReport').val();
    var sign = $('#SelectSign').val();
    var invoiceType = $('#invoiceType').val();
    if (type === 'print') {
        if (invoiceType === 'T') {
            window.open("report.smi?name=InvoiceTemp&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        } else {
            window.open("report.smi?name=" + typeReport + "&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        }
    } else if (type === 'printEmail') {
        if (invoiceType === 'T') {
            window.open("report.smi?name=InvoiceTempEmail&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        } else {
            window.open("report.smi?name=" + typeReport + "&invoiceid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
        }
    } else if (type === 'email') {
        if (invoiceType === 'T') {
            window.open("SendMail.smi?reportname=InvoiceTempEmail&reportid=" + invoiceId + "&bankid=" + payment + "&showstaff=" + sale + "&showleader=" + leader + "&sign=" + sign);
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
        $('#DetailBillableTable input:last').removeClass('lastrow');
        $('#DetailBillableTable input:last').addClass('lastrow');
//        count.value = count.value - 1;
        CalculateGrandTotal('');
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
                    $('#textAlertInvoiceNotEmptyReceiptAndTax').show();
                } else if (msg === 'notDeleteTax') {
                    $('#textAlertInvoiceNotEmptyTax').show();
                } else if (msg === 'notDeleteRecipt') {
                    $('#textAlertInvoiceNotEmptyReceipt').show();
                }
                $("#ajaxload").addClass("hidden");
                $('#DetailBillableTable input:last').removeClass('lastrow');
                $('#DetailBillableTable input:last').addClass('lastrow');
                CalculateGrandTotal('');

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
                        billtype = billJson[i].type
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
                            if ((billselect == billListName[i]) || (billselect == billListId[i])) {
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
    if($("#ajaxloadsearch").hasClass("hidden")){
        $('#MasterReservation > tbody  > tr').each(function() {
            $(this).remove();
        });

        var searchNo = $("#SearchRefNo").val();
        var invType = $("#invType").val();
        var InputInvoiceType = ($("#InputInvoiceType").val()).split("/");
        var department = (InputInvoiceType[0] === 'O' ? "Outbound" : "");

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
                    '&department=' + department +
                    '&type=' + 'searchInvoice';
            CallAjaxAdd(param);
        } else {
            $('#SearchRefNo').focus();
            $("#searchRefNo2").addClass("hidden");
        }
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
            beforeSend: function() {
                $('#MasterReservation > tbody  > tr').each(function() {
                    $(this).remove();
                });              
            },
            success: function(msg) {
                var strx = msg.split('||');
                var array = [];
                array = array.concat(strx);
                BookintType = array[0];
                if (BookintType == $('#typeBooking').val()) {
                    $('#AlertBooking').hide();
                    $('#AlertBookingRefno').hide();
                    setBillableInvoice(array[1]);
                    if (array[2] !== '') {
                        try {
                            $("#searchRefNo2").removeClass("hidden");
                            $("#MasterReservation tbody").append(array[2]);
                        } catch (e) {
                            alert(e);
                        }
                    } else {
                        $("#searchRefNo2").addClass("hidden");
                    }
                } else {
                    $("#searchRefNo2").addClass("hidden");
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
    var billType = array[8];
    $('#InvTo').val(array[0]);
    $('#ARCode').val(billType === 'C' ? 'DUMMY' : array[0]);
    $('#InvToName').val(array[1]);
    $('#InvToAddress').val(array[2]);
    $('#TermPay').val(array[3]);
    $('#SaleStaffId').val(array[4]);
    $('#SaleStaffName').val(array[5]);
    $('#SaleStaffCode').val(array[6]);
    
    var dueDate = '';
    if(array[7] !== null && array[7] !== ''){
        var date = array[7].split('-');
        dueDate = date[2]+"-"+date[1]+"-"+date[0];
    }
    $('#InputDueDate').val(dueDate);
}

function addInvoiceDetail(rowId) {
    isDuplicateInvoiceDetail = 0;
    var rowCount = $('#DetailBillableTable >tbody >tr').length;
    $("#counter").val((rowCount++));
    var countTable = $("#counter").val();
    var RefNo = $("#SearchRefNo").val();
    var count = 1;
    var curChk = "";
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
            curChk = cur;
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
                AddRowDetailBillAble(countTable, prod, des, cos, id, price, RefNo, cur, cur_c, refItemId, pro);
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
//    if (curChk === '') {
//        validFromInvoice();
//    }
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
                var strx = (msg !== 'null' ? msg.split('|') : []);
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

function CallAjaxSearchDescriptionCancel(param, rowId, des, RefNo) {
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
                var description = $('#DescriptionInvoiceDetail' + rowId).val();
                $('#InputDescriptionDetailId').val(rowId);
                $('#InputDescriptionDetail').val(description);
            }, error: function(msg) {
                $("#ajaxloadsearch").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

function setbillAndDescription(row, ref, name, text, des) {
    var bill = ref + " " + (name !== undefined ? name : '') + " " + des;
    $('#BillDescriptionTemp' + row).val(bill);
    $('#BillDescription' + row).val(bill);
    $('#DescriptionInvoiceDetail' + row).val(text !== undefined ? text : '');
    $("#DescriptionInvoiceDetailTextArea" + row).val(text !== undefined ? text : '');
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
    var numTemp1 = String(num);
    var numTemp2 = (numTemp1.indexOf(".") >= 0 ? String(num.toFixed(2)) : '');
    if(numTemp1.length <= 8 || (numTemp2 !== '' && numTemp2.length <= 10)){
        return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
    }else{
        return '';
    }   
}

 function formatExRateNumber(num) {
    return  num.toFixed(10).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
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
            var currency = getCurrency();
            var bathString = toWords(grandTotal,currency);
            document.getElementById('TextAmount').value = bathString;
        }
    }
}

function getCurrency(){
    var countTaxInvoice = parseInt($("#counterTable").val());
    var currency = '';
    for(var i=1; i<=countTaxInvoice; i++){
        var currency1 = document.getElementById('SelectCurrencyAmount'+i);
        var product1 = document.getElementById('SelectProductType'+i);
        var description1 = document.getElementById('BillDescriptionTemp'+i);
        var cost1  = document.getElementById('InputCost'+i);
        var costLocal1 = document.getElementById('InputCostLocal'+i);
        var amount1 = document.getElementById('InputAmount'+i);
        if(currency1 !== null){
            if(product1.value !== '' || description1.value !== '' || cost1.value !== '' || costLocal1.value !== ''  || amount1.value !== ''){
                currency = currency1.value;
                var currencyTemp1 = currency1.value;
                for(var j=i+1; j<=countTaxInvoice; j++){
                    var currency2 = document.getElementById('SelectCurrencyAmount'+j);
                    var product2 = document.getElementById('SelectProductType'+j);
                    var description2 = document.getElementById('BillDescriptionTemp'+j);
                    var cost2 = document.getElementById('InputCost'+j);
                    var costLocal2 = document.getElementById('InputCostLocal'+j);
                    var amount2 = document.getElementById('InputAmount'+j);
                    if(currency2 !== null){
                        if(product2.value !== '' || description2.value !== '' || cost2.value !== '' || costLocal2.value !== '' || amount2.value !== ''){
                            var currencyTemp2 = currency2.value;
                            if((currencyTemp1 !== currencyTemp2)){
                                currency = ''; 
                                i = countTaxInvoice+1;
                                j = countTaxInvoice+1;
                            }
                        }    
                    }
                }
            }    
        }    
    }
    return currency;
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

function setBillValue(billto, billname, address, term, pay, type) {
    $("#InvTo").val(billto);
    $("#InvToName").val(billname); 
    $("#InvToAddress").val(address);
    $("#ARCode").val(type !== 'C' ? billto : 'DUMMY');

    if ($("#InvTo").val() !== "" && $("#InvToName").val() !== "" && $("#ARCode").val() !== "" && $("#InputInvDate").val() !== "") {
//        alert("1");
        $('#InvoiceForm').bootstrapValidator('revalidateField', 'InvTo');
        $('#InvoiceForm').bootstrapValidator('revalidateField', 'InvToName');
        $('#InvoiceForm').bootstrapValidator('revalidateField', 'ARCode');
        $("#saveInvoice").removeAttr("disabled");
        $("#disableVoidButton").removeAttr("disabled");
    } else {
//         alert("2");
        $("#saveInvoice").attr("disabled", "disabled");
        $("#disableVoidButton").attr("disabled", "disabled");
    }
    $("#InvToModal").modal('hide');
}
function showSearchRefNo() {
    if ($("#searchRefNo1").hasClass("hidden")) {
        $("#searchRefNo1").removeClass("hidden");
    } else {
        $("#searchRefNo1").addClass("hidden");
    }
    $("#searchRefNo2").addClass("hidden");
//    if($("#searchRefNo2").hasClass("hidden")){
//        $("#searchRefNo2").removeClass("hidden");
//    }else{
//        $("#searchRefNo2").addClass("hidden");
//    }
}
function calculateAmountLocal(row, option) {
    if (option === 'amountLocal') {
        var curAmount = $("#SelectCurrencyAmount" + row).val();
        if (curAmount !== undefined) {
            if(curAmount !== 'THB'){
                $("#InputAmountLocal" + row).val('');
            }           
            $("#InputExRate" + row).keyup(function(event) {
                if (event.keyCode === 13) {
                    calculateAmountLocal(row, 'exRate');
                }
            });
        }
    } else if (option === 'exRate') {
        var curAmount = $("#SelectCurrencyAmount" + row).val();
        if(curAmount !== undefined && curAmount !== 'THB'){
            var amount = ($("#InputAmount" + row).val() !== '' ? parseFloat(($("#InputAmount" + row).val()).replace(/\,/g, '')) : 0);
            var exRate = ($("#InputExRate" + row).val() !== '' ? parseFloat(($("#InputExRate" + row).val()).replace(/\,/g, '')) : 0);
            var amountLocal = amount * exRate;
            $("#InputAmountLocal" + row).val((amountLocal !== 0 ? formatNumber(amountLocal) : ''));
        }       
    } else if (option === 'setEvent'){
        $("#InputExRate" + row).keyup(function(event) {
            if (event.keyCode === 13) {
                calculateAmountLocal(row, 'exRate');
            }
        });
    }
}

//Operation Duplicate
function enableOperationDuplicate(){
    var action = document.getElementById("action");
    action.value = "operationUpdate";
    document.getElementById("InvoiceForm").submit();
}

function disableOperationDuplicate(){   
    $("#saveInvoice").attr("disabled", true);
    $("#enableVoidButton").attr("disabled", true);
    $("#disableVoidButton").attr("disabled", true); 
    $("#DelDetailBill").addClass("hidden");
}

$(window).on("beforeunload", function() {
    var operationAction = $("#action").val();
    var operationTable = $("#operationTable").val();
    var operationTableId = $("#operationTableId").val();
    var operationUser = $("#username").val();
    console.log("action : "+operationAction);
    console.log("operationTable : "+operationTable);
    console.log("operationTableId : "+operationTable);
    console.log("operationUser : "+operationUser);
    clearDuplicateUser(operationTable,operationTableId,operationAction,operationUser);  
});
    
function clearDuplicateUser(operationTable,operationTableId,operationAction,operationUser) {
    var servletName = 'CheckDuplicateUserServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&operationTable=' + operationTable +
            '&operationTableId=' + operationTableId +
            '&operationAction=' + operationAction +
            '&operationUser=' + operationUser;
    callAjaxClearDuplicateUser(param);
}

function callAjaxClearDuplicateUser(param) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                console.log('update duplicate user success');
             // window.location = 'APMonitor.smi';
            }, error: function(msg) {
                console.log('update duplicate user fail');
            }
        });
    } catch (e) {
        alert(e);
        console.log('update duplicate user fail');
    }
}

function hideTextAlertDiv(){
    $("#textAlertInvoiceNotEmptyReceiptAndTax").hide();
    $("#textAlertInvoiceNotEmptyTax").hide();
    $("#textAlertInvoiceNotEmptyReceipt").hide();
    $("#textAlertDuplicate").hide();
}

function checkCurrency(row){
    var currencyAmount = $("#SelectCurrencyAmount" + row).val();
    var product = $('#SelectProductType' + row + ' option:selected').text();
    if(currencyAmount !== 'THB' && product === 'Air Ticket'){
        var description = $("#DescriptionInvoiceDetail" + row).val();
        while(description.indexOf("<P>") !== -1){
            var tagP1 = description.indexOf("<P>");
            var tagP2 = description.indexOf("</P>");
            var amount = description.substring(tagP1,tagP2+4);
            description = description.replace(amount,'');
        }
        $("#DescriptionInvoiceDetail" + row).val(description);
    }
}

function AddrowBySelect(row){
//    var count =  parseInt($("#counterTable").val());
//    row = parseInt(row);
//    if(row === (count)){
//       AddRowDetailBillAble(count++); 
//    }       
    
}