/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var ctrlKeyDown = false;
$(document).ready(function() {
    $(document).on("keydown", keydown);
    $(document).on("keyup", keyup);
    
    $("#receive").removeClass('hidden');
    $('.date').datetimepicker();
    $(".daydatepicker").datetimepicker({
        pickTime: false
    });
    $('.datemask').mask('0000-00-00');
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });

    $(".money").mask('000,000,000,000.00', {reverse: true});
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

    $('#ReceiveTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 5
    });

    $('#SearchReceiveTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });

    $('#periodTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });    

    //Button Search
    $('.datesearch').datetimepicker();
    $('.datesearch').datetimepicker().change(function() {
        checkSearch();
    });
    $('#ButtonSearch').ready(function() {
        checkSearch();
    });

    //Auto complete
    var showflag = 1;
    $("#receiveName").keyup(function(event) {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        if ($(this).val() === "") {
//            $("#receiveCode").val("");
            $("#receiveName").val("");
            $("#receiveArCode").val("");
        } else {
            if (event.keyCode === 13) {
                searchCustomerAgentAutoList(this.value);
            }
        }
    });
    $("#receiveName").keydown(function() {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        if (showflag == 0) {
            $(".ui-widget").css("top", -1000);
            showflag = 1;
        }
    });

    //Validate Field
    $('#InputDatePicker').datetimepicker().on('dp.change', function(e) {
        $('#receiveForm').bootstrapValidator('revalidateField', 'receiveDate');
    });
    $('#receiveForm').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            receiveDate: {
                validators: {
                    notEmpty: {
                        message: 'The Date is required'
                    }
                }
            },
//            receiveCode: {
//                validators: {
//                    notEmpty: {
//                        message: 'The Code is required'
//                    }
//                }
//            },
            receiveName: {
                validators: {
                    notEmpty: {
                        message: 'The Name is required'
                    }
                }
            },
            status: {
                validators: {
                    notEmpty: {
                        message: 'The Status is required'
                    }
                }
            },
            receiveAmount: {
                validators: {
                    notEmpty: {
                        message: 'The Amount is required'
                    }
                }
            }
        }
    });

    //Add row table receive 
    var rowCreditTable = $("#CreditTable tr").length;
    AddRowCreditTable(rowCreditTable);
    $("#CreditTable").on("keyup", function() {
        var rowAll = $("#CreditTable tr").length;
        $("td").keyup(function() {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#CreditTable tr").length;
                if (rowIndex === rowAll) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    AddRowCreditTable(parseInt($("#countCredit").val()));
                }
                if (rowAll < 2) {
//                    $("#tr_CreditTableAddRow").removeClass("hide");
//                    $("#tr_CreditTableAddRow").addClass("show");
                }
            }
        });
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

    $(".numerical").on('input', function() {
        var value = $(this).val().replace(/[^0-9.,]*/g, '');
        value = value.replace(/\.{2,}/g, '.');
        value = value.replace(/\.,/g, ',');
        value = value.replace(/\,\./g, ',');
        value = value.replace(/\,{2,}/g, ',');
        value = value.replace(/\.[0-9]+\./g, '.');
        $(this).val(value);
    });

    //Validate Receive Period
    $('.fromdate').datetimepicker().change(function() {
        $("#textAlertDivSavePeriod").hide();
        $("#textAlertDivNotSavePeriod").hide();
        $("#textAlertDivDeletePeriod").hide();
        $("#textAlertDivPeriodMeaasge").hide();
        $("#textAlertDivUpdatePeriod").hide();
        checkFromDateField();
        checkPeriod();
    });
    $('.todate').datetimepicker().change(function() {
        $("#textAlertDivSavePeriod").hide();
        $("#textAlertDivNotSavePeriod").hide();
        $("#textAlertDivDeletePeriod").hide();
        $("#textAlertDivPeriodMeaasge").hide();
        $("#textAlertDivUpdatePeriod").hide();
        checkToDateField();
        checkPeriod();
    });
    
    $("#periodVatType").change(function() {
        checkDateValue("","")
        checkPeriod();
    });

    //Set Status Format Calculate
    $("#receiveAmount,#wht").keyup(function() {
        setStatusFormat();
    });

    $("#cashAmount").keyup(function() {
        setCashAmount(this.value);
        setCashOnDemand()
    });

    $("#bankAmount").keyup(function() {
        setBankAmount(this.value);
        setCashOnDemand()
    });

    $("#chqAmount").keyup(function() {
        setChqAmount(this.value);
        setCashOnDemand()
    });

    //Period Message
    if ($("#periodMessage").val() !== '') {
        showPeriodMessage($("#periodMessage").val());
    }
    
    //Clear Alert Message and Panel color
    $("#periodVatType").click(function() {
        $("#textAlertDivSavePeriod").hide();
        $("#textAlertDivNotSavePeriod").hide();
        $("#textAlertDivDeletePeriod").hide();
        $("#textAlertDivPeriodMeaasge").hide();
        $("#textAlertDivUpdatePeriod").hide();
    });

    setEnvironment();

});

function keydown(e) { 
    if ((e.which || e.keyCode) == 116 || ((e.which || e.keyCode) == 82 && ctrlKeyDown)) {
        // Pressing F5 or Ctrl+R
        // e.preventDefault();
        $("#action").val("search");
        document.getElementById("receiveForm").submit();
    } else if ((e.which || e.keyCode) == 17) {
        // Pressing  only Ctrl
        ctrlKeyDown = true;
    }
};

function keyup(e){
    // Key up Ctrl
    if ((e.which || e.keyCode) == 17) 
        ctrlKeyDown = false;
};

//Set Data at start
function setEnvironment() {
    if ($("#receiveId").val() !== '') {
        $("#receiveData").removeClass("hidden");
    }
    if ($("#receiveAmount").val() !== '') {
        $("#receiveAmount").val(formatNumber(parseFloat($("#receiveAmount").val().replace(/,/g, ""))));
    }
    if ($("#cashAmount").val() !== '') {
        $("#cashAmount").val(formatNumber(parseFloat($("#cashAmount").val().replace(/,/g, ""))));
    }
    if ($("#bankAmount").val() !== '') {
        $("#bankAmount").val(formatNumber(parseFloat($("#bankAmount").val().replace(/,/g, ""))));
    }
    if ($("#chqAmount").val() !== '') {
        $("#chqAmount").val(formatNumber(parseFloat($("#chqAmount").val().replace(/,/g, ""))));
    }
    if ($("#periodDetail").val() !== '') {
        $("#periodDetail").val($("#periodDetail").val().replace(/<br ?\/?>/g, "\n"));
    }
    if ($("#periodCashAmount").val() !== '') {
        $("#periodCashAmount").val(formatNumber(parseFloat($("#periodCashAmount").val().replace(/,/g, ""))));
    }
    if ($("#periodBankAmount").val() !== '') {
        $("#periodBankAmount").val(formatNumber(parseFloat($("#periodBankAmount").val().replace(/,/g, ""))));
    }
    if ($("#periodCash").val() !== '') {
        $("#periodCash").val(formatNumber(parseFloat($("#periodCash").val().replace(/,/g, ""))));
    }
    if ($("#periodCheque").val() !== '') {
        $("#periodCheque").val(formatNumber(parseFloat($("#periodCheque").val().replace(/,/g, ""))));
    }
    if ($("#periodCreditCard").val() !== '') {
        $("#periodCreditCard").val(formatNumber(parseFloat($("#periodCreditCard").val().replace(/,/g, ""))));
    }
    if ($("#wht").val() !== '') {
        $("#wht").val(formatNumber(parseFloat($("#wht").val().replace(/,/g, ""))));
    }
    if ($("#countCredit").val() !== '1') {
        var row = parseInt($("#countCredit").val());
        for (var i = 1; i <= row; i++) {
            if ($("#creditAmount" + i).val() !== '' && $("#creditAmount" + i).val() !== undefined) {
                $("#creditAmount" + i).val(formatNumber(parseFloat($("#creditAmount" + i).val().replace(/,/g, ""))));
            }
        }
    }
    calculateCreditAmount();
    setStatusFormat();
}

//Auto Complete
function searchCustomerAgentAutoList(billTo) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + billTo +
            '&type=' + 'getAutoListBilltoReceiveTable';
    CallAjaxAuto(param);
}
function CallAjaxAuto(param) {
    var url = 'AJAXServlet';
    var billArray = [];
    var billListId = [];
    var billListName = [];
    var billListAddress = [];
    var billid, billname, billaddr;
    $("#receiveName").autocomplete("destroy");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            beforeSend: function() {
//                  $("#dataload").removeClass("hidden");    
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
//                        $("#dataload").addClass("hidden"); 
                }
//                   $("#InvTo_Id").val(billid);
//                $("#receiveArCode").val(billid);
//                $("#receiveName").val(billname);
//                   $("#InvToAddress").val(billaddr);

                $("#receiveName").autocomplete({
                    source: billArray,
                    close: function() {
                        $("#receiveName").trigger("keyup");
                        var billselect = $("#receiveName").val();
                        for (var i = 0; i < billListName.length; i++) {
//                            if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                            if ((billselect == billListName[i])) {
//                                $("#receiveCode").val(billListId[i]);
                                $("#receiveArCode").val(billListId[i]);
                                $("#receiveName").val(billListName[i]);
//                                   $("#InvToAddress").val(billListAddress[i]);
                            }
                        }
                    }
                });

                var billval = $("#receiveName").val();
                for (var i = 0; i < billListName.length; i++) {
                    if (billval == billListName[i]) {
                        $("#receiveName").val(billListName[i]);
                    }
                }
                if (billListName.length == 1) {
                    showflag = 0;
                    $("#receiveName").val(billListName[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $("#receiveName").trigger(event);

            }, error: function(msg) {
                console.log('auto ERROR');
//                   $("#dataload").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

//Insert Comma
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
    nField.value = formatNumber(nField.value);
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

function calculate(num) {
    if (num.value !== '') {
        var number = parseFloat((num.value).replace(/,/g, ""));
        num.value = formatNumber(number);
    }
}

//Check value for Search
function checkSearch() {
    $("#periodVatType").val($("#SelectStatus").val());
    if (($("#InputDate").val() === '') || ($("#SelectStatus").val() === '')) {
        $("#ButtonSearch").addClass("disabled");
        $("#ButtonPrint").addClass("disabled");
    } else {
        $("#ButtonSearch").removeClass("disabled");
        $("#ButtonPrint").removeClass("disabled");
    }

}

//Agent List
function setupCustomerAgentValue(billTo, billName, address) {
    document.getElementById('receiveCode').value = billTo;
    document.getElementById('receiveName').value = billName;
    document.getElementById('receiveArCode').value = billTo;
    $('#receiveForm').bootstrapValidator('revalidateField', 'receiveCode');
    $('#receiveForm').bootstrapValidator('revalidateField', 'receiveName');
    $('#ReceiveModal').modal('hide');
}

//Add receive data
function AddReceiveData() {
    if ($("#receiveData").hasClass("hidden")) {
        $("#receiveData").removeClass("hidden");
        $("#addIcon").removeClass("glyphicon-plus").addClass("glyphicon-minus");
    } else {
        $("#receiveData").addClass("hidden");
        $("#addIcon").removeClass("glyphicon-minus").addClass("glyphicon-plus");
    }
    if ($("#receiveDate").val() === '') {
        $("#receiveDate").val($("#InputDate").val());
        $("#vatType").val($("#SelectStatus").val());
    }
}

function reloadDatePicker() {
    try {
        $(".daydatepicker").datetimepicker({
            pickTime: false
        });
        $('span').click(function() {

            var position = $(this).offset();
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });
//           $('#CreditTable tbody tr:last td .input-group-addon').click(function() {  
//                AddRowCreditTable(parseInt($("#countCredit").val()));
//           });

    } catch (e) {

    }
}

function AddRowCreditTable(row) {
    if (!row) {
        row = 1;
    }
    $("#CreditTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input class="form-control" type="text" id="advanceReceiveCreditId' + row + '" name="advanceReceiveCreditId' + row + '" value=""></td>' +
            '<td class="hidden"><input class="form-control" type="text" id="advanceReceiveId' + row + '" name="advanceReceiveId' + row + '" value=""></td>' +
            '<td><select class="form-control" name="creditCard' + row + '" id="creditCard' + row + '" onchange="AddrowBySelect(\'' + row + '\')"><option  value="" ></option></select></td>' +
            '<td><input class="form-control" type="text" id="creditNo' + row + '" name="creditNo' + row + '" value="" maxlength="20"></td>' +
            '<td>' +
            '<div class="input-group daydatepicker" id="daydatepicker' + row + '">' +
            '<input type="text" name="creditExpire' + row + '" id="creditExpire' + row + '" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />' +
            '<span class="input-group-addon spandate" id="spandate' + row + '" style="padding : 1px 10px;" onclick="AddrowBySelect(\'' + row + '\')"><span class="glyphicon-calendar glyphicon"></span></span>' +
            '</div>' +
            '</td>' +
            '<td><input class="form-control decimal" type="text" id="creditAmount' + row + '" name="creditAmount' + row + '" value="" onfocusout="calculate(this); calculateCreditAmount(); setCreditAmount(); setCashOnDemand();"></td>' +
            '<td>' +
            '<center>' +
            '<a id="expenButtonRemove' + row + '" name="expenButtonRemove' + row + '" onclick="deleteAdvanceReceiveCreditConfirm(\'\',\'' + row + '\')"  data-toggle="modal" data-target="#DeleteExpenModal">' +
            '<span id="expenSpanEdit' + row + '" name="expenSpanEdit' + row + '" class="glyphicon glyphicon-remove deleteicon"></span>' +
            '</a>' +
            '</center>' +
            '</td>' +
            '</tr>'
            );
//    $("#tr_CreditTableAddRow").removeClass("show");
//    $("#tr_CreditTableAddRow").addClass("hide");
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
    $("#select_bank_list option").clone().appendTo("#creditCard" + row);
    $("#countCredit").val(row + 1);
    reloadDatePicker();
}

function AddrowBySelect(row) {
    var count = parseInt($("#countCredit").val());
    row = parseInt(row);
    if (row === (count - 1)) {
        AddRowCreditTable(count);
    }
}

function searchReceive() {
    $("#action").val("search");
    document.getElementById("receiveForm").submit();
}

function editAdvanceReceive(id) {
    $("#receiveId").val(id);
    $("#action").val("edit");
    document.getElementById("receiveForm").submit();
}

function deleteAdvanceReceiveConfirm(id) {
    $("#receiveId").val(id);
    $("#delReceiveModal").modal("show");
}

function deleteAdvanceReceive() {
    $("#delReceiveModal").modal("hide");
    $("#action").val("deleteAdvanceReceive");
    document.getElementById("receiveForm").submit();
}

function deleteAdvanceReceiveCreditConfirm(id, row) {
    $("#receiveCreditId").val(id);
    $("#receiveCreditRow").val(row);
    $("#delReceiveCreditModal").modal("show");
}

function deleteAdvanceReceiveCredit() {
    var id = document.getElementById('receiveCreditId').value;
    var row = document.getElementById('receiveCreditRow').value;
    var count = document.getElementById('countCredit').value;
    if (id === '') {
        $("#creditCard" + row).parent().parent().remove();
        var rowAll = $("#CreditTable tr").length;
//        if (rowAll <= 1) {
//            $("#tr_CreditTableAddRow").removeClass("hide");
//            $("#tr_CreditTableAddRow").addClass("show");
//        }
        if ((parseInt(count) - 1) === parseInt(row)) {
            AddRowCreditTable(parseInt(count));
        }
        calculateCreditAmount();
        setCreditAmount();
        setCashOnDemand();
//            $("#countTaxInvoice").val(count+1);
    } else {
        $.ajax({
            url: 'ReceiveTable.smi?action=deleteAdvanceReceiveCredit',
            type: 'get',
            data: {receiveCreditId: id},
            success: function() {
                $("#creditCard" + row).parent().parent().remove();
                var rowAll = $("#creditCard tr").length;
//                if (rowAll <= 1) {
//                    $("#tr_CreditTableAddRow").removeClass("hide");
//                    $("#tr_CreditTableAddRow").addClass("show");
//                }
//                    $("#countTaxInvoice").val(count+1);
                if ((parseInt(count) - 1) === parseInt(row)) {
                    AddRowCreditTable(parseInt(count));
                }
                calculateCreditAmount();
                setCreditAmount();
                setCashOnDemand();
            },
            error: function() {
                console.log("error");
                result = 0;
            }
        });
    }
    $('#delReceiveCreditModal').modal('hide');
}

function addNewRowCreditTable() {
    var count = document.getElementById('countCredit').value;
    AddRowCreditTable(parseInt(count));
}

function newReceiveTable() {
//    $("#action").val("new");
//    document.getElementById("receiveForm").submit();

    $("#receiveId").val('');
    $("#receiveName").val('');
    $("#receiveArCode").val('');
    $("#description").val('');
    $("#status").val('');
    $("#receiveAmount").val('');
    $("#wht").val('');
    $("#cashAmount").val('');
    $("#bankAmount").val('');
    $("#chqAmount").val('');
    $("#chqBank").val('');
    $("#chqDate").val('');
    $("#chqNo").val('');
    $("#createBy").val('');
    $("#createDate").val('');
    $("#chqDate").val('');
    $("#chqDate").val('');
    $("#countCredit").val(1);
    $('#CreditTable > tbody  > tr').each(function() {
        $(this).remove();
    });
    AddRowCreditTable(parseInt($("#countCredit").val()));

}

function checkPeriodVatType() {
    var vatType = $("#periodVatType").val();
    var inputFromDate = $("#fromDate").val();
    var InputToDate = $("#toDate").val();
    if (vatType === '' && inputFromDate === '' && InputToDate === '') {
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#vattypepanel").removeClass("has-error");
        $("#btnSave").addClass("disabled");
    } else if (vatType === '' || inputFromDate === '' || InputToDate === '') {
        $("#vattypepanel").removeClass("has-success");
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        $("#vattypepanel").addClass("has-error");
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#btnSave").addClass("disabled");
    } else {
        $("#vattypepanel").removeClass("has-error");
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#vattypepanel").addClass("has-success");
        $("#fromdatepanel").addClass("has-success");
        $("#todatepanel").addClass("has-success");
        $("#btnSave").removeClass("disabled");
    }
}

function checkFromDateField() {
    var inputFromDate = document.getElementById("fromDate");
    var InputToDate = document.getElementById("toDate");
    var vatType = document.getElementById("periodVatType");
    if (InputToDate.value === '' && inputFromDate.value === '' && vatType.value === '') {
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#vattypepanel").removeClass("has-error");
        $("#btnSave").removeClass("disabled");
    } else if (inputFromDate.value === '' || InputToDate.value === '' || vatType.value === '') {
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        $("#vattypepanel").removeClass("has-success");
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#vattypepanel").addClass("has-error");
        $("#btnSave").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#vattypepanel").removeClass("has-error");
        $("#fromdatepanel").addClass("has-success");
        $("#todatepanel").addClass("has-success");
        $("#vattypepanel").addClass("has-success");
        $("#btnSave").removeClass("disabled");
        checkDateValue("from", "");
    }
}

function checkToDateField() {
    var InputToDate = document.getElementById("toDate");
    var inputFromDate = document.getElementById("fromDate");
    var vatType = document.getElementById("periodVatType");
    if (InputToDate.value === '' && inputFromDate.value === '' && vatType.value === '') {
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#vattypepanel").removeClass("has-error");
        $("#btnSave").removeClass("disabled");
    } else if (inputFromDate.value === '' || InputToDate.value === '' || vatType.value === '') {
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        $("#vattypepanel").removeClass("has-success");
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#vattypepanel").addClass("has-error");
        $("#btnSave").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#vattypepanel").removeClass("has-error");
        $("#fromdatepanel").addClass("has-success");
        $("#todatepanel").addClass("has-success");
        $("#vattypepanel").addClass("has-success");
        $("#btnSave").removeClass("disabled");
        checkDateValue("to", "");
    }
}

function checkDateValue(date) {
    var inputFromDate = document.getElementById("fromDate");
    var InputToDate = document.getElementById("toDate");
    var vatType = document.getElementById("periodVatType");
    if ((inputFromDate.value !== '') && (InputToDate.value !== '') && (vatType.value !== '')) {
        var fromDate = (inputFromDate.value).split('-');
        var toDate = (InputToDate.value).split('-');
        if ((parseInt(fromDate[0])) > (parseInt(toDate[0]))) {
            validateDate(date, "over");
        }
        if (((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))) {
            validateDate(date, "over");
        }
        if (((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && ((parseInt(fromDate[2])) > (parseInt(toDate[2])))) {
            validateDate(date, "over");
        }
    }
}

function validateDate(date, option) {
    if (option === 'over') {
        $("#fromdatepanel").removeClass("has-success");
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").removeClass("has-success");
        $("#todatepanel").addClass("has-error");
        $("#vattypepanel").removeClass("has-success");
        $("#vattypepanel").addClass("has-error");
        $("#btnSave").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        $("#vattypepanel").removeClass("has-success");
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#vattypepanel").addClass("has-error");
        $("#btnSave").addClass("disabled");
    }
}

// Function Receive Period
function saveReceivePeriod() {
    $('#textAlertDivSavePeriod').hide();
    $('#textAlertDivNotSavePeriod').hide();
    var periodId = $("#periodId").val();
    var receiveFrom = $("#receiveFrom").val();
    var receiveTo = $("#receiveTo").val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var periodDetail = $("#periodDetail").val();
    var vatType = $("#periodVatType").val();
    var department = $("#department").val();
    if (department === 'W') {
        department = 'Wendy';
    } else if (department === 'O') {
        department = 'Outbound';
    } else if (department === 'I') {
        department = 'Inbound';
    } else if (department === 'WO') {
        department = 'Wendy,Outbound';
    }

    if (fromDate !== '' && toDate !== '' && vatType !== '') {
        var servletName = 'ReceiveTableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&periodId=' + periodId +
                '&receiveFrom=' + receiveFrom +
                '&receiveTo=' + receiveTo +
                '&fromDate=' + fromDate +
                '&toDate=' + toDate +
                '&periodDetail=' + periodDetail +
                '&vatType=' + vatType +
                '&department=' + department +
                '&type=' + 'checkPeriodDate';
        CallAjaxCheckPeriod(param);

    } else {
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        $("#vattypepanel").removeClass("has-success");
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#vattypepanel").addClass("has-error");
        $("#btnSave").addClass("disabled");
    }

}

function CallAjaxCheckPeriod(param) {
    $('#ajaxPeriod').removeClass('hidden');
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                if (msg !== 'fail') {
                    var result = msg.split("//");
//                    $('#periodTable > tbody  > tr').each(function() {
//                        $(this).remove();
//                    });
//                    $("#periodTable tbody").append(msg);
                    if(result[1] !== ''){
                        $('#periodTable').dataTable().fnClearTable();
                        $('#periodTable').dataTable().fnDestroy();
                        $("#periodTable tbody").empty().append(result[1]);
                        $('#periodTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                    }    
                    
                    $("#periodTable tbody").append(result[0]);
                    $("#periodId").val($("#periodIdTemp").val());
                    $("#receiveFrom").val($("#periodFromTemp").val());
                    $("#receiveTo").val($("#periodToTemp").val());
                    $("#receiveDetail").val($("#periodDetailTemp").val());
                    $("#receiveCashAmount").val($("#periodCashAmountTemp").val());
                    $("#receiveBankAmount").val($("#periodBankAmountTemp").val());
                    $("#receiveCash").val($("#periodCashMinusAmountTemp").val());
                    $("#receiveCheque").val($("#periodChqAmountTemp").val());
                    $("#receiveCreditCard").val($("#periodCreditAmountTemp").val());

                    $("#periodDetail").val($("#periodDetailTemp").val());
                    $("#periodCashAmount").val($("#periodCashAmountTemp").val());
                    $("#periodBankAmount").val($("#periodBankAmountTemp").val());
                    $("#periodCash").val($("#periodCashMinusAmountTemp").val());
                    $("#periodCheque").val($("#periodChqAmountTemp").val());
                    $("#periodCreditCard").val($("#periodCreditAmountTemp").val());
                    $("#periodSize").val($("#periodSizeTemp").val());
                    setEnvironment();
                    
                    var id = $("#periodIdTemp").val();
                    var receiveFrom = $("#periodFromTemp").val();
                    var receiveTo = $("#periodToTemp").val();
                    var vatType = $("#periodVatTypeTemp").val();
                    var department = $("#periodId").val();
                    var cashAmount = $("#periodCashAmountTemp").val();
                    var cashMinusAmount = $("#periodCashMinusAmountTemp").val();
                    var detail = $("#periodDetailTemp").val();
                    var bankTransfer = $("#periodBankAmountTemp").val();
                    var chqAmount = $("#periodChqAmountTemp").val();
                    var creditAmount = $("#periodCreditAmountTemp").val();
                    var department = $("#periodDepartmentTemp").val();
                    editAdvanceReceivePeriod(id, receiveFrom, receiveTo, detail, vatType, department, cashAmount, cashMinusAmount, bankTransfer, chqAmount, creditAmount)

//                    var billJson = JSON.parse(msg);
//                    for (var i in billJson) {
//                        if (billJson.hasOwnProperty(i)) {
//                            $("#periodId").val(billJson[i].periodId);
//                            $("#receiveFrom").val(billJson[i].receiveFrom);
//                            $("#receiveTo").val(billJson[i].receiveTo);
//                            $("#receiveDetail").val(billJson[i].periodIdDetail);
//                            $("#receiveCashAmount").val(billJson[i].cashamount);
//                            $("#receiveBankAmount").val(billJson[i].bankamount);
//                            $("#receiveCash").val(billJson[i].cashminusamount);
//                            $("#receiveCheque").val(billJson[i].cheque);
//                            $("#receiveCreditCard").val(billJson[i].creditcard);
//
//                            $("#periodDetail").val(billJson[i].periodIdDetail);
//                            $("#periodCashAmount").val(billJson[i].cashamount);
//                            $("#periodBankAmount").val(billJson[i].bankamount);
//                            $("#periodCash").val(billJson[i].cashminusamount);
//                            $("#periodCheque").val(billJson[i].cheque);
//                            $("#periodCreditCard").val(billJson[i].creditcard);
//                            setEnvironment();
//                        }
//                    }
                    $("#fromdatepanel").removeClass("has-error");
                    $("#fromdatepanel").addClass("has-success");
                    $("#todatepanel").removeClass("has-error");
                    $("#todatepanel").addClass("has-success");
                    $("#vattypepanel").removeClass("has-error");
                    $("#vattypepanel").addClass("has-success");
                    $('#textAlertDivSavePeriod').show();
                    $('#ajaxPeriod').addClass('hidden');
                } else {
//                    $("#fromdatepanel").removeClass("has-success");
//                    $("#todatepanel").removeClass("has-success");
//                    $("#vattypepanel").removeClass("has-success");
//                    $("#fromdatepanel").addClass("has-error");
//                    $("#todatepanel").addClass("has-error");
//                    $("#vattypepanel").addClass("has-error");
                    $("#btnSave").addClass("disabled");
                    $('#textAlertDivNotSavePeriod').show();
                    $('#ajaxPeriod').addClass('hidden');
                }
            }, error: function(msg) {
                console.log('auto ERROR');
                $('#ajaxPeriod').addClass('hidden');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function hideTextAlertDivSavePeriod() {
    $('#textAlertDivSavePeriod').hide();
}

function hideTextAlertDivNotSavePeriod() {
    $('#textAlertDivNotSavePeriod').hide();
}

function hideTextAlertDivDeletePeriod() {
    $('#textAlertDivNotDeletePeriod').hide();
}

function hideTextAlertPeriodMessage() {
    $('#textAlertDivPeriodMeaasge').hide();
}

function hideTextAlertDivUpdatePeriod() {
    $('#textAlertDivUpdatePeriod').hide();
}

function newReceivePeriod() {
    $("#periodId").val('');
    $("#receiveFrom").val('');
    $("#receiveTo").val('');
    $("#receiveCashAmount").val('');
    $("#receiveCash").val('');
    $("#receiveCheque").val('');
    $("#receiveBankAmount").val('');
    $("#receiveCreditCard").val('');
    $("#receiveDetail").val('');

    $("#fromDate").val('');
    $("#toDate").val('');
    $("#periodVatType").val('');
    $("#periodCashAmount").val('');
    $("#periodCash").val('');
    $("#periodCheque").val('');
    $("#periodBankAmount").val('');
    $("#periodCreditCard").val('');
    $("#periodDetail").val('');

    $("#textAlertDivSavePeriod").hide();
    $("#textAlertDivNotSavePeriod").hide();
    $("#textAlertDivDeletePeriod").hide();
    $("#textAlertDivPeriodMeaasge").hide();
    $('#textAlertDivUpdatePeriod').hide();
    $("#fromdatepanel").removeClass("has-error");
    $("#fromdatepanel").removeClass("has-success");
    $("#todatepanel").removeClass("has-error");
    $("#todatepanel").removeClass("has-success");
    $("#vattypepanel").removeClass("has-error");
    $("#vattypepanel").removeClass("has-success");
}

function callPeriodList() {
    $("#textAlertDivSavePeriod").hide();
    $("#textAlertDivNotSavePeriod").hide();
    $("#textAlertDivDeletePeriod").hide();
    $("#textAlertDivPeriodMeaasge").hide();
    $('#textAlertDivUpdatePeriod').hide();
    $("#fromdatepanel").removeClass("has-error");
    $("#fromdatepanel").removeClass("has-success");
    $("#todatepanel").removeClass("has-error");
    $("#todatepanel").removeClass("has-success");
    $("#vattypepanel").removeClass("has-error");
    $("#vattypepanel").removeClass("has-success");
    $("#receivePeriodModal").modal("show");
}

function editAdvanceReceivePeriod(id, receiveFrom, receiveTo, detail, vatType, department, cashAmount, cashMinusAmount, bankTransfer, chqAmount, creditAmount) {
//    alert(id + " " + receiveFrom + " " + receiveTo + " " + detail + " " + vatType + " " + department + " " + cashAmount + " " + cashMinusAmount + " " + bankTransfer + " " + chqAmount + " " + creditAmount);
    $("#periodId").val(id);
    $("#receiveFrom").val(receiveFrom);
    $("#receiveTo").val(receiveTo);
    $("#receiveCashAmount").val(cashAmount);
    $("#receiveCash").val(cashMinusAmount);
    $("#receiveCheque").val(chqAmount);
    $("#receiveBankAmount").val(bankTransfer);
    $("#receiveCreditCard").val(creditAmount);
    $("#receiveDetail").val(detail);

    $("#fromDate").val(receiveFrom);
    $("#toDate").val(receiveTo);
    $("#periodVatType").val(vatType);
    $("#periodCashAmount").val(cashAmount !== '' ? formatNumber(parseFloat(cashAmount.replace(/,/g, ""))) : '');
    $("#periodCash").val(cashMinusAmount !== '' ? formatNumber(parseFloat(cashMinusAmount.replace(/,/g, ""))) : '');
    $("#periodCheque").val(chqAmount !== '' ? formatNumber(parseFloat(chqAmount.replace(/,/g, ""))) : '');
    $("#periodBankAmount").val(bankTransfer !== '' ? formatNumber(parseFloat(bankTransfer.replace(/,/g, ""))) : '');
    $("#periodCreditCard").val(creditAmount !== '' ? formatNumber(parseFloat(creditAmount.replace(/,/g, ""))) : '');
    $("#periodDetail").val(detail);
    $("#receivePeriodModal").modal("hide");
    
    if(department === 'WendyOutbound'){
        department = 'Wendy,Outbound';
    }
        
    var servletName = 'ReceiveTableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&receiveFrom=' + receiveFrom +
            '&department=' + department +
            '&vatType=' + vatType +
            '&type=' + 'compareReceiptSummary';
    CallAjaxCompareReceiptSummary(param);
}

function CallAjaxCompareReceiptSummary(param) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                if(msg !== ''){
                    showPeriodMessage(msg);
                }
            }, error: function(msg) {
                console.log('auto ERROR');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function deleteAdvanceReceivePeriod() {
    var periodSize = parseInt($("#periodSize").val());
    var periodId = '';
    for (var i = 1; i <= periodSize; i++) {
        if ($('#periodCheckbox' + i).is(":checked")) {
            periodId += $('#periodId' + i).val() + ',';
        }
    }

    if (periodId !== '') {
        var department = $("#department").val();
        if (department === 'W') {
            department = 'Wendy';
        } else if (department === 'O') {
            department = 'Outbound';
        } else if (department === 'I') {
            department = 'Inbound';
        } else if (department === 'WO') {
            department = 'WendyOutbound';
        }
        var servletName = 'ReceiveTableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&periodId=' + periodId +
                '&department=' + department +
                '&type=' + 'deletePeriodDate';
        CallAjaxDeletePeriod(param, periodId);
//        $("#receivePeriodModal").modal("hide");
    } else {
        $("#receivePeriodModal").modal("hide");
    }
}

function CallAjaxDeletePeriod(param, periodId) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                if (msg !== 'fail') {
//                    $('#periodTable > tbody  > tr').each(function() {
//                        $(this).remove();
//                    });
                    var result = msg.split("//");
                    if(result[1] !== ''){
                        $('#periodTable').dataTable().fnClearTable();
                        $('#periodTable').dataTable().fnDestroy();
                        $("#periodTable tbody").empty().append(result[1]);
                        $('#periodTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                    }    
                    $("#periodTable tbody").append(result[0]);
                    $("#periodSize").val($("#periodSizeTemp").val());
                    var periodSize = parseInt($("#periodSize").val());
                    if(periodSize > 0){
                        var id = periodId.split(",");
                        for (var i = 0; i < id.length; i++) {
                            if ($("#periodId").val() === id[i]) {
                                $("#periodId").val('');
                                $("#receiveFrom").val('');
                                $("#receiveTo").val('');
                                $("#receiveDetail").val('');
                                $("#receiveCashAmount").val('');
                                $("#receiveBankAmount").val('');
                                $("#receiveCash").val('');
                                $("#receiveCheque").val('');
                                $("#receiveCreditCard").val('');

                                $("#fromDate").val('');
                                $("#toDate").val('');
                                $("#periodVatType").val('');
                                $("#periodDetail").val('');
                                $("#periodCashAmount").val('');
                                $("#periodBankAmount").val('');
                                $("#periodCash").val('');
                                $("#periodCheque").val('');
                                $("#periodCreditCard").val('');
                                i = id.length;
                            }
                        }
                    }else{
//                        $('#periodTable > tbody  > tr').each(function() {
//                            $(this).remove();
//                        });
                        $('#periodTable').dataTable().fnClearTable();
                        $("#periodId").val('');
                        $("#receiveFrom").val('');
                        $("#receiveTo").val('');
                        $("#receiveDetail").val('');
                        $("#receiveCashAmount").val('');
                        $("#receiveBankAmount").val('');
                        $("#receiveCash").val('');
                        $("#receiveCheque").val('');
                        $("#receiveCreditCard").val('');
                        
                        $("#fromDate").val('');
                        $("#toDate").val('');
                        $("#periodVatType").val('');
                        $("#periodDetail").val('');
                        $("#periodCashAmount").val('');
                        $("#periodBankAmount").val('');
                        $("#periodCash").val('');
                        $("#periodCheque").val('');
                        $("#periodCreditCard").val('');
                    }   
                }
//                if(msg === 'success'){
//                    var row = periodRow.split(",");
//                    for(var i=0; i<row.length; i++){
//                        $("#periodRow" + row[i]).parent().parent().remove();
//                    }                  
//                    $('#textAlertDivNotDeletePeriod').show();
//                }


            }, error: function(msg) {
                console.log('auto ERROR');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function updateReceivePeriod(){
    var periodId = $("#periodId").val();
    var receiveFrom = $("#receiveFrom").val();
    var receiveTo = $("#receiveTo").val();
    var vatType = $("#periodVatType").val();
    var department = $("#department").val();
    if (department === 'W') {
        department = 'Wendy';
    } else if (department === 'O') {
        department = 'Outbound';
    } else if (department === 'I') {
        department = 'Inbound';
    } else if (department === 'WO') {
        department = 'Wendy,Outbound';
    }

    if (receiveFrom !== '' && receiveTo !== '' && vatType !== '') {
        var servletName = 'ReceiveTableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&periodId=' + periodId +
                '&receiveFrom=' + receiveFrom +
                '&receiveTo=' + receiveTo +
                '&vatType=' + vatType +
                '&department=' + department +
                '&type=' + 'updateReceivePeriodSummary';
        CallAjaxUpdateReceivePeriod(param);

    }
}

function CallAjaxUpdateReceivePeriod(param) {
    $('#ajaxPeriod').removeClass('hidden');
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                if(msg !== 'fail'){
//                    $('#periodTable > tbody  > tr').each(function() {
//                        $(this).remove();
//                    });
                    var result = msg.split("//");
                    if(result[1] !== ''){
                        $('#periodTable').dataTable().fnClearTable();
                        $('#periodTable').dataTable().fnDestroy();
                        $("#periodTable tbody").empty().append(result[1]);
                        $('#periodTable').dataTable({bJQueryUI: true,
                            "sPaginationType": "full_numbers",
                            "bAutoWidth": false,
                            "bFilter": true,
                            "bPaginate": true,
                            "bInfo": false,
                            "bLengthChange": false,
                            "iDisplayLength": 10
                        });
                    }    
                    $("#periodTable tbody").append(result[0]);
                    $("#periodId").val($("#periodIdTemp").val());
                    $("#receiveFrom").val($("#periodFromTemp").val());
                    $("#receiveTo").val($("#periodToTemp").val());
                    $("#receiveVatType").val($("#periodVatTypeTemp").val());
                    $("#receiveDetail").val($("#periodDetailTemp").val());
                    $("#receiveCashAmount").val($("#periodCashAmountTemp").val());
                    $("#receiveBankAmount").val($("#periodBankAmountTemp").val());
                    $("#receiveCash").val($("#periodCashMinusAmountTemp").val());
                    $("#receiveCheque").val($("#periodChqAmountTemp").val());
                    $("#receiveCreditCard").val($("#periodCreditAmountTemp").val());

                    $("#periodDetail").val($("#periodDetailTemp").val());
                    $("#periodCashAmount").val($("#periodCashAmountTemp").val());
                    $("#periodBankAmount").val($("#periodBankAmountTemp").val());
                    $("#periodCash").val($("#periodCashMinusAmountTemp").val());
                    $("#periodCheque").val($("#periodChqAmountTemp").val());
                    $("#periodCreditCard").val($("#periodCreditAmountTemp").val());
                    setEnvironment();
                    
                    $("#textAlertDivSavePeriod").show();
                    $("#textAlertDivNotSavePeriod").hide();
                    $("#textAlertDivDeletePeriod").hide();
                    $("#textAlertDivPeriodMeaasge").hide();
                    $('#ajaxPeriod').addClass('hidden');                    
                
                }else{
                    $('#textAlertDivNotSavePeriod').show();
                    $('#ajaxPeriod').addClass('hidden');
                }
                
            }, error: function(msg) {
                $('#ajaxPeriod').addClass('hidden');
                console.log('auto ERROR');
            }
        });
    } catch (e) {
        alert(e);
    }
}

// Period Message
function showPeriodMessage(message) {
//    if ($("#periodMessage").val() !== '') {
        $("#textAlertDivPeriodMeaasge").show();
        $("#periodAlertMessage").text(message);

//    }
}

//Print Receive Table Report
function printReceiveTableReport() {
    var receiveDate = $("#InputDate").val();
    var vatType = $("#SelectStatus").val();
    var department = $("#department").val();
    if (department === 'W') {
        department = 'Wendy';
    } else if (department === 'O') {
        department = 'Outbound';
    } else if (department === 'I') {
        department = 'Inbound';
    } else if (department === 'WO') {
        department = 'WendyOutbound';
    }
    window.open("report.smi?name=CollectionReport&receiveDate=" + receiveDate + "&vatType=" + vatType + "&department=" + department);
}

function checkPeriod() {
    if ($("#periodId").val() !== '') {
        var from = $("#fromDate").val();
        var fromCheck = $("#receiveFrom").val();
        var to = $("#toDate").val();
        var toCheck = $("#receiveTo").val();
        var vatType = $("#periodVatType").val();
        var vatTypeCheck = $("#receiveVatType").val();
        if ((from === fromCheck) && (to === toCheck) && (vatType === vatTypeCheck)) {
            $("#periodDetail").val($("#receiveDetail").val());
            $("#periodCashAmount").val($("#receiveCashAmount").val() !== '' ? formatNumber(parseFloat($("#receiveCashAmount").val().replace(/,/g, ""))) : '');
            $("#periodBankAmount").val($("#receiveBankAmount").val() !== '' ? formatNumber(parseFloat($("#receiveBankAmount").val().replace(/,/g, ""))) : '');
            $("#periodCash").val($("#receiveCash").val() !== '' ? formatNumber(parseFloat($("#receiveCash").val().replace(/,/g, ""))) : '');
            $("#periodCheque").val($("#receiveCheque").val() !== '' ? formatNumber(parseFloat($("#receiveCheque").val().replace(/,/g, ""))) : '');
            $("#periodCreditCard").val($("#receiveCreditCard").val() !== '' ? formatNumber(parseFloat($("#receiveCreditCard").val().replace(/,/g, ""))) : '');
        } else {
            $("#periodDetail").val('');
            $("#periodCashAmount").val('');
            $("#periodBankAmount").val('');
            $("#periodCash").val('');
            $("#periodCheque").val('');
            $("#periodCreditCard").val('');
        }
    }
}

function setStatusFormat() {
    var status = $("#status option:selected").text();
//    var receiveAmount = parseInt(($("#receiveAmount").val()).replace(/,/g, ""));
    if (status == 'Cash') {
        var cashAmount = $("#cashAmount").val();
        disabledReceiveTableField(status);
        setCashAmount(cashAmount);

    } else if (status == 'Cash on Demand') {
        setCashOnDemand();

    } else if (status == 'Credit Card') {
        disabledReceiveTableField(status);
        setCreditAmount();

    } else if (status == 'Bank Transfer') {
        var bankAmount = $("#bankAmount").val();
        disabledReceiveTableField(status);
        setBankAmount(bankAmount);

    } else if (status == 'Cheque') {
        var chqAmount = $("#chqAmount").val();
        disabledReceiveTableField(status);
        setChqAmount(chqAmount);

    } else if (status == 'Void' || status == 'Wait') {
        setVoidAndWaitAmount();
    }
}

function disabledReceiveTableField(status) {
    $("#cashAmountPanel").removeClass("has-success");
    $("#cashAmountPanel").removeClass("has-error");
    $("#bankAmountPanel").removeClass("has-success");
    $("#bankAmountPanel").removeClass("has-error");
    $("#chqAmountPanel").removeClass("has-success");
    $("#chqAmountPanel").removeClass("has-error");

    if (status == 'Credit Card' || status == 'Bank Transfer' || status == 'Cheque') {
        $("#cashAmount").val('');
        $("#cashAmount").attr("disabled", "disabled");
    }
    if (status == 'Cash' || status == 'Credit Card' || status == 'Cheque') {
        $("#bankAmount").val('');
        $("#bankAmount").attr("disabled", "disabled");
    }
    if (status == 'Cash' || status == 'Bank Transfer' || status == 'Credit Card') {
        $("#chqAmount").val('');
        $("#chqAmount").attr("disabled", "disabled");
    }
    if (status == 'Cash' || status == 'Bank Transfer' || status == 'Cheque') {
        $("#creditAmount").val(0);
        $("#countCredit").val(1);
        $('#CreditTable > tbody  > tr').each(function() {
            $(this).remove();
        });
        AddRowCreditTable(parseInt($("#countCredit").val()));
        $("#creditCard" + (parseInt($("#countCredit").val()) - 1)).attr("disabled", "disabled");
        $("#creditNo" + (parseInt($("#countCredit").val()) - 1)).attr("disabled", "disabled");
        $("#creditExpire" + (parseInt($("#countCredit").val()) - 1)).attr("disabled", "disabled");
        $("#creditAmount" + (parseInt($("#countCredit").val()) - 1)).attr("disabled", "disabled");
        $("#expenButtonRemove" + (parseInt($("#countCredit").val()) - 1)).addClass("hidden");
        $('#spandate' + (parseInt($("#countCredit").val()) - 1)).addClass("hidden");
    }
}

function setCashAmount(cash) {
    var status = $("#status option:selected").text();
    if (status == 'Cash') {
        $("#cashAmount").prop("disabled", false);
        $("#cashAmount").val(cash);
        var receiveAmount = ($("#receiveAmount").val() !== '' ? parseFloat((($("#receiveAmount").val()).replace(/,/g, ""))) : 0);
        var wht = ($("#wht").val() !== '' ? parseFloat((($("#wht").val()).replace(/,/g, ""))) : 0);
        var cashAmount = parseFloat(cash.replace(/,/g, ""));
        if ((cashAmount + wht) === receiveAmount) {
            $("#cashAmountPanel").removeClass("has-error");
            $("#cashAmountPanel").addClass("has-success");
            $("#ButtonSave").removeClass("disabled");
        } else {
            $("#cashAmountPanel").removeClass("has-success");
            $("#cashAmountPanel").addClass("has-error");
            $("#ButtonSave").addClass("disabled");
        }
    }
}

function setBankAmount(bank) {
    var status = $("#status option:selected").text();
    if (status == 'Bank Transfer') {
        $("#bankAmount").prop("disabled", false);
        $("#bankAmount").val(bank);
        var receiveAmount = ($("#receiveAmount").val() !== '' ? parseFloat((($("#receiveAmount").val()).replace(/,/g, ""))) : 0);
        var wht = ($("#wht").val() !== '' ? parseFloat((($("#wht").val()).replace(/,/g, ""))) : 0);
        var bankAmount = parseFloat(bank.replace(/,/g, ""));
        if ((bankAmount + wht) === receiveAmount) {
            $("#bankAmountPanel").removeClass("has-error");
            $("#bankAmountPanel").addClass("has-success");
            $("#ButtonSave").removeClass("disabled");
        } else {
            $("#bankAmountPanel").removeClass("has-success");
            $("#bankAmountPanel").addClass("has-error");
            $("#ButtonSave").addClass("disabled");
        }
    }
}

function setChqAmount(chq) {
    var status = $("#status option:selected").text();
    if (status == 'Cheque') {
        $("#chqAmount").prop("disabled", false);
        $("#chqAmount").val(chq);
        var receiveAmount = ($("#receiveAmount").val() !== '' ? parseFloat((($("#receiveAmount").val()).replace(/,/g, ""))) : 0);
        var wht = ($("#wht").val() !== '' ? parseFloat((($("#wht").val()).replace(/,/g, ""))) : 0);
        var chqAmount = parseFloat(chq.replace(/,/g, ""));
        if ((chqAmount + wht) === receiveAmount) {
            $("#chqAmountPanel").removeClass("has-error");
            $("#chqAmountPanel").addClass("has-success");
            $("#ButtonSave").removeClass("disabled");
        } else {
            $("#chqAmountPanel").removeClass("has-success");
            $("#chqAmountPanel").addClass("has-error");
            $("#ButtonSave").addClass("disabled");
        }
    }
}

function setCreditAmount() {
    var status = $("#status option:selected").text();
    if (status == 'Credit Card') {
        $("#creditCard" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#creditNo" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#creditExpire" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#creditAmount" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#expenButtonRemove" + (parseInt($("#countCredit").val()) - 1)).removeClass("hidden");
        $('#spandate' + (parseInt($("#countCredit").val()) - 1)).removeClass("hidden");

        var receiveAmount = ($("#receiveAmount").val() !== '' ? parseFloat((($("#receiveAmount").val()).replace(/,/g, ""))) : 0);
        var wht = ($("#wht").val() !== '' ? parseFloat((($("#wht").val()).replace(/,/g, ""))) : 0);
        var creditAmount = parseFloat($("#creditAmount").val());
        if ((creditAmount + wht) === receiveAmount) {
            $("#ButtonSave").removeClass("disabled");
            var count = parseInt($("#countCredit").val());
            for (var i = 1; i <= count; i++) {
                var creditAmount = $("#creditAmount" + i);
                if (creditAmount.val() !== undefined) {
                    $("#creditAmount" + i).css("border", "green ridge 1px");
                }
            }
        } else {
            $("#ButtonSave").addClass("disabled");
            var count = parseInt($("#countCredit").val());
            for (var i = 1; i <= count; i++) {
                var creditAmount = $("#creditAmount" + i);
                if (creditAmount.val() !== undefined) {
                    $("#creditAmount" + i).css("border", "red solid 1px");
                }
            }
        }
    }
}

function calculateCreditAmount() {
    var count = parseInt($("#countCredit").val());
    var creditTotal = 0;
    for (var i = 1; i <= count; i++) {
        var creditAmount = $("#creditAmount" + i);
        if (creditAmount.val() !== '' && creditAmount.val() !== undefined) {
            creditTotal += parseFloat((($("#creditAmount" + i).val()).replace(/,/g, "")));
        }
    }
    $("#creditAmount").val(creditTotal);
}

function setCashOnDemand() {
    var status = $("#status option:selected").text();
    if (status == 'Cash on Demand') {
        $("#cashAmount").prop("disabled", false);
        $("#bankAmount").prop("disabled", false);
        $("#chqAmount").prop("disabled", false);
        $("#creditCard" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#creditNo" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#creditExpire" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#creditAmount" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#expenButtonRemove" + (parseInt($("#countCredit").val()) - 1)).removeClass("hidden");
        $('#spandate' + (parseInt($("#countCredit").val()) - 1)).removeClass("hidden");

        var receiveAmount = ($("#receiveAmount").val() !== '' ? parseFloat((($("#receiveAmount").val()).replace(/,/g, ""))) : 0);
        var wht = ($("#wht").val() !== '' ? parseFloat((($("#wht").val()).replace(/,/g, ""))) : 0);
        var cashAmount = ($("#cashAmount").val() !== '' ? parseFloat((($("#cashAmount").val()).replace(/,/g, ""))) : 0);
        var bankAmount = ($("#bankAmount").val() !== '' ? parseFloat((($("#bankAmount").val()).replace(/,/g, ""))) : 0);
        var chqAmount = ($("#chqAmount").val() !== '' ? parseFloat((($("#chqAmount").val()).replace(/,/g, ""))) : 0);
        var creditAmount = parseFloat($("#creditAmount").val());

        if ((receiveAmount) === (cashAmount + bankAmount + chqAmount + creditAmount + wht)) {
            $("#cashAmountPanel").removeClass("has-error");
            $("#cashAmountPanel").addClass("has-success");
            $("#bankAmountPanel").removeClass("has-error");
            $("#bankAmountPanel").addClass("has-success");
            $("#chqAmountPanel").removeClass("has-error");
            $("#chqAmountPanel").addClass("has-success");
            $("#ButtonSave").removeClass("disabled");
            var count = parseInt($("#countCredit").val());
            for (var i = 1; i <= count; i++) {
                var creditAmount = $("#creditAmount" + i);
                if (creditAmount.val() !== undefined) {
                    $("#creditAmount" + i).css("border", "green ridge 1px");
                }
            }
        } else {
            $("#cashAmountPanel").removeClass("has-success");
            $("#cashAmountPanel").addClass("has-error");
            $("#bankAmountPanel").removeClass("has-success");
            $("#bankAmountPanel").addClass("has-error");
            $("#chqAmountPanel").removeClass("has-success");
            $("#chqAmountPanel").addClass("has-error");
            $("#ButtonSave").addClass("disabled");
            var count = parseInt($("#countCredit").val());
            for (var i = 1; i <= count; i++) {
                var creditAmount = $("#creditAmount" + i);
                if (creditAmount.val() !== undefined) {
                    $("#creditAmount" + i).css("border", "red solid 1px");
                }
            }
        }
    }
}

function setVoidAndWaitAmount() {
    var status = $("#status option:selected").text();
    if (status == 'Void' || status == 'Wait') {
        $("#cashAmount").prop("disabled", false);
        $("#bankAmount").prop("disabled", false);
        $("#chqAmount").prop("disabled", false);
        $("#creditCard" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#creditNo" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#creditExpire" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#creditAmount" + (parseInt($("#countCredit").val()) - 1)).prop("disabled", false);
        $("#expenButtonRemove" + (parseInt($("#countCredit").val()) - 1)).removeClass("hidden");
        $('#spandate' + (parseInt($("#countCredit").val()) - 1)).removeClass("hidden");

        $("#cashAmountPanel").removeClass("has-success");
        $("#cashAmountPanel").removeClass("has-error");
        $("#bankAmountPanel").removeClass("has-success");
        $("#bankAmountPanel").removeClass("has-error");
        $("#chqAmountPanel").removeClass("has-success");
        $("#chqAmountPanel").removeClass("has-error");
        $("#ButtonSave").removeClass("disabled");
        var count = parseInt($("#countCredit").val());
        for (var i = 1; i <= count; i++) {
            var creditAmount = $("#creditAmount" + i);
            if (creditAmount.val() !== undefined) {
                $("#creditAmount" + i).css("border", "black solid 1px");
            }
        }
    }
}