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
    $("#receiveCode").keyup(function(event) {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        if ($(this).val() === "") {
            $("#receiveCode").val("");
            $("#receiveName").val("");
            $("#receiveArCode").val("");
        } else {
            if (event.keyCode === 13) {
                searchCustomerAgentAutoList(this.value);
            }
        }
    });
    $("#receiveCode").keydown(function() {
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
            receiveCode: {
                validators: {
                    notEmpty: {
                        message: 'The Code is required'
                    }
                }
            },
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
//        var rowCreditTable = $("#CreditTable tr").length;
    AddRowCreditTable();
    $("#CreditTable").on("keyup", function() {
        var rowAll = $("#CreditTable tr").length;
        $("td").keyup(function() {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#CreditTable tr").length;
//                    alert("rowIndex = "+rowIndex);
//                    alert("rowAll = "+rowAll);
                if (rowIndex === rowAll) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    AddRowCreditTable(parseInt($("#countCredit").val()));
                }
                if (rowAll < 2) {
                    $("#tr_ReceiveTableAddRow").removeClass("hide");
                    $("#tr_ReceiveTableAddRow").addClass("show");
                }
            }
        });
    });

    //Result
    var result = $('#result').val();
    if (result === "success") {
        $('#textAlertDivSave').show();
    } else if (result === "") {
        $('#textAlertDivSave').hide();
    } else if (result === 'fail') {
        $('#textAlertDivNotSave').show();
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
    
    setEnvironment();
});

//Set Data at start
function setEnvironment(){
    $("#receiveAmount").val(formatNumber(parseFloat($("#receiveAmount").val())));
    $("#cashAmount").val(formatNumber(parseFloat($("#cashAmount").val())));
    $("#bankAmount").val(formatNumber(parseFloat($("#bankAmount").val())));
    $("#chqAmount").val(formatNumber(parseFloat($("#chqAmount").val())));
    $("#chqBank").val(formatNumber(parseFloat($("#chqBank").val())));
    $("#chqNo").val(formatNumber(parseFloat($("#chqNo").val())));
    if($("#receiveId").val() !== ''){
        $("#receiveData").removeClass("hidden");
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

                $("#receiveCode").autocomplete({
                    source: billArray,
                    close: function() {
                        $("#receiveCode").trigger("keyup");
                        var billselect = $("#receiveCode").val();
                        for (var i = 0; i < billListId.length; i++) {
                            if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                                $("#receiveCode").val(billListId[i]);
                                $("#receiveArCode").val(billListId[i]);
                                $("#receiveName").val(billListName[i]);
//                                   $("#InvToAddress").val(billListAddress[i]);
                            }
                        }
                    }
                });

                var billval = $("#receiveCode").val();
                for (var i = 0; i < billListId.length; i++) {
                    if (billval == billListName[i]) {
                        $("#receiveCode").val(billListId[i]);
                    }
                }
                if (billListId.length == 1) {
                    showflag = 0;
                    $("#receiveCode").val(billListId[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $("#receiveCode").trigger(event);

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

function calculate(num){
    var number = parseFloat((num.value).replace(/,/g,""));
    num.value = formatNumber(number);
//    document.getElementById("receiveAmount").value = formatNumber(nField);
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
    if($("#receiveDate").val() === ''){
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
            '<input name="creditExpire' + row + '" id="creditExpire' + row + '" type="text" class="form-control" data-date-format="YYYY-MM-DD" placeholder="YYYY-MM-DD" value="" />' +
            '<span class="input-group-addon" onclick="AddrowBySelect(\'' + row + '\')"><i class="glyphicon glyphicon-calendar"></i></span>' +
            '</div>' +
            '</td>' +
            '<td><input class="form-control numerical" style="text-align:right;" type="text" id="creditAmount' + row + '" name="creditAmount' + row + '" value="" onkeyup="insertCommas(this)" onfocusout="calculate(this)"></td>' +
            '<td>' +
            '<center>' +
            '<a id="expenButtonRemove' + row + '" name="expenButtonRemove' + row + '" onclick="deleteTaxList(\'\',\'' + row + '\')"  data-toggle="modal" data-target="#DeleteExpenModal">' +
            '<span id="expenSpanEdit' + row + '" name="expenSpanEdit' + row + '" class="glyphicon glyphicon-remove deleteicon"></span>' +
            '</a>' +
            '</center>' +
            '</td>' +
            '</tr>'
            );
//        $("#tr_TaxInvoiceDetailAddRow").removeClass("show");
//        $("#tr_TaxInvoiceDetailAddRow").addClass("hide");
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

function editAdvanceReceiveConfirm(id) {
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

function deleteAdvanceReceiveCreditConfirm(id) {

}

