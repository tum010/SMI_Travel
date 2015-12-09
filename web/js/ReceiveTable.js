/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
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

    $('#SearchReceiveTable').dataTable({bJQueryUI: true,
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
        checkFromDateField();
    });
    $('.todate').datetimepicker().change(function() {
        checkToDateField();
    });

    setEnvironment();
});

//Set Data at start
function setEnvironment() {
    if ($("#receiveId").val() !== '') {
        $("#receiveData").removeClass("hidden");
    }
    if ($("#receiveAmount").val() !== '') {
        $("#receiveAmount").val(formatNumber(parseFloat($("#receiveAmount").val())));
    }
    if ($("#cashAmount").val() !== '') {
        $("#cashAmount").val(formatNumber(parseFloat($("#cashAmount").val())));
    }
    if ($("#bankAmount").val() !== '') {
        $("#bankAmount").val(formatNumber(parseFloat($("#bankAmount").val())));
    }
    if ($("#chqAmount").val() !== '') {
        $("#chqAmount").val(formatNumber(parseFloat($("#chqAmount").val())));
    }
    if ($("#chqBank").val() !== '') {
        $("#chqBank").val(formatNumber(parseFloat($("#chqBank").val())));
    }
    if ($("#chqNo").val() !== '') {
        $("#chqNo").val(formatNumber(parseFloat($("#chqNo").val())));
    }
    if ($("#countCredit").val() !== '1') {
        var row = parseInt($("#countCredit").val());
        for (var i = 1; i <= row; i++) {
            if ($("#creditAmount" + i).val() !== '') {
                $("#creditAmount" + i).val(formatNumber(parseFloat($("#creditAmount" + i).val())));
            }
        }
    }
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
    $("#TaxInvTo").autocomplete("destroy");
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
                $("#receiveArCode").val(billid);
                $("#receiveName").val(billname);
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
    } else {
        $("#receiveData").addClass("hidden");
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
            '<td><input class="form-control" type="text" id="creditNo' + row + '" name="creditNo' + row + '" value=""></td>' +
            '<td>' +
            '<div class="input-group daydatepicker" id="daydatepicker' + row + '">' +
            '<input type="text" name="creditExpire' + row + '" id="creditExpire' + row + '" class="form-control datemask" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />' +
            '<span class="input-group-addon spandate" style="padding : 1px 10px;" onclick="AddrowBySelect(\'' + row + '\')"><span class="glyphicon-calendar glyphicon"></span></span>' +
            '</div>' +
            '</td>' +
            '<td><input class="form-control numerical" style="text-align:right;" type="text" id="creditAmount' + row + '" name="creditAmount' + row + '" value="" onkeyup="insertCommas(this)" onfocusout="calculate(this)"></td>' +
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
    $("#action").val("new");
    document.getElementById("receiveForm").submit();
}

function checkFromDateField() {
    var inputFromDate = document.getElementById("fromDate");
    var InputToDate = document.getElementById("toDate");
    if (InputToDate.value === '' && inputFromDate.value === '') {
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#btnSave").removeClass("disabled");
    } else if (inputFromDate.value === '' || InputToDate.value === '') {
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#btnSave").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#fromdatepanel").addClass("has-success");
        $("#todatepanel").addClass("has-success");
        $("#btnSave").removeClass("disabled");
        checkDateValue("from", "");
    }
}

function checkToDateField() {
    var InputToDate = document.getElementById("toDate");
    var inputFromDate = document.getElementById("fromDate");
    if (InputToDate.value === '' && inputFromDate.value === '') {
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#btnSave").removeClass("disabled");
    } else if (inputFromDate.value === '' || InputToDate.value === '') {
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#btnSave").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-error");
        $("#todatepanel").removeClass("has-error");
        $("#fromdatepanel").addClass("has-success");
        $("#todatepanel").addClass("has-success");
        $("#btnSave").removeClass("disabled");
        checkDateValue("to", "");
    }
}

function checkDateValue(date) {
    var inputFromDate = document.getElementById("fromDate");
    var InputToDate = document.getElementById("toDate");
    if ((inputFromDate.value !== '') && (InputToDate.value !== '')) {
        var fromDate = (inputFromDate.value).split('-');
        var toDate = (InputToDate.value).split('-');
        if ((parseInt(fromDate[0])) > (parseInt(toDate[0]))) {
            validateDate(date, "over");
        }
        if (((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))) {
            validateDate(date, "over");
        }
        if (((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[2])) > (parseInt(toDate[2]))) {
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
        $("#btnSave").addClass("disabled");
    } else {
        $("#fromdatepanel").removeClass("has-success");
        $("#todatepanel").removeClass("has-success");
        $("#fromdatepanel").addClass("has-error");
        $("#todatepanel").addClass("has-error");
        $("#btnSave").addClass("disabled");
    }
}

function saveReceivePeriod() {
    $('#textAlertDivSavePeriod').hide();
    $('#textAlertDivNotSavePeriod').hide();
    var periodId = $("#periodId").val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var periodDetail = $("#periodDetail").val();

    var servletName = 'ReceiveTableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&periodId=' + periodId +
            '&fromDate=' + fromDate +
            '&toDate=' + toDate +
            '&periodDetail=' + periodDetail +
            '&type=' + 'checkPeriodDate';
    CallAjaxCheck(param);

}

function CallAjaxCheck(param) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {               
                if(msg === 'success'){
                    $("#fromdatepanel").removeClass("has-error");
                    $("#fromdatepanel").addClass("has-success");
                    $("#todatepanel").removeClass("has-error");
                    $("#todatepanel").addClass("has-success");
                    $('#textAlertDivSavePeriod').show();
                }else{
                    $("#fromdatepanel").removeClass("has-success");
                    $("#todatepanel").removeClass("has-success");
                    $("#fromdatepanel").addClass("has-error");
                    $("#todatepanel").addClass("has-error");
                    $("#btnSave").addClass("disabled");
                    $('#textAlertDivNotSavePeriod').show();
                }
            }, error: function(msg) {
                console.log('auto ERROR');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function hideTextAlertDivSavePeriod(){
    $('#textAlertDivSavePeriod').hide();
}

function hideTextAlertDivNotSavePeriod(){
    $('#textAlertDivNotSavePeriod').hide();
}
