$(document).ready(function () {
    var checkpnr = $('#pnrdata').val(); 
    if(checkpnr !== "0"){
        $("#ButtonAdd").addClass("disabled");
    }
        
    // MONEY FORMAT 
    $(".money").mask('000,000,000,000,000,000', {reverse: true});
    // SET DATATIMEPICKER
    if($("#deadline").val() !== ''){
        var date = $("#deadline").val();
        $("#deadline").val(convertFormatDate(date));
    }
    $('#datetimepicker3').datetimepicker({
        pickTime: false
    });
    $('.spandate').click(function () {
        var position = $(this).offset();
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    // CHECK GROUP PAX
    if ($("#pnr-size").val() > 1) {
        $("#group-pax").prop("checked", true);
    }
    // ON TABLE ON SELECT ROW
    $('table').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
    //PNR TABLE
    $('#TableAir').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": false,
        "bInfo": false
    });


    // VALIDATE 
    $("#AirTicket").bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            get_deadline: {
                validators: {
                    date: {
                        format: 'DD-MM-YYYY',
                        message: 'The value is not a valid date'
                    }
                }
            }
        }
    }).on('success.field.bv', function (e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);
        }
    }).on('mouseover', function () {
        var staff_name = $(this).find('[name="staff_name"]');
        var isEmpty = staff_name.val() === '';
        $("#AirTicket").bootstrapValidator('enableFieldValidators', 'staff_name', isEmpty);
        var issue_name = $(this).find('[name="issue_name"]');
        var isEmpty = issue_name.val() === '';
        $("#AirTicket").bootstrapValidator('enableFieldValidators', 'issue_name', isEmpty);

    });


});

// OWNER
$(document).ready(function () {
   
    codeStaff = [];
    $.each(staff, function (key, value) {
        codeStaff.push(value.username);
        if ( !(value.name in codeStaff) ){
           codeStaff.push(value.name);
        }
    });
    console.log(codeStaff);
    $("#staff_username").autocomplete({
        source: codeStaff,
        close:function( event, ui ) {
           $("#staff_username").trigger('keyup');
        }
    });
    $("#staff_username").on('keyup', function () {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var username = this.value.toUpperCase();
        var name = this.value;
        $("#staff_id,#staff_name").val(null);
        $.each(staff, function (key, value) {
            if (value.username.toUpperCase() === username) {
                $("#staff_id").val(value.id);
                $("#staff_name").val(value.name);
            }
            if(name === value.name){
                $("#staff_id").val(value.id);
                $("#staff_username").val(value.username);
                $("#staff_name").val(value.name);
                username = $("#staff_username").val().toUpperCase();
            }
        });
    });

    $("#OwnerTable tr").on('click', function () {
        staff_id = $(this).find(".staff-id").text();
        staff_username = $(this).find(".staff-username").text();
        staff_name = $(this).find(".staff-name").text();
        $("#staff_id").val(staff_id);
        $("#staff_username").val(staff_username);
        $("#staff_name").val(staff_name);
        console.log(staff_id);
        $("#OwnerModal").modal('hide');
    });
    // OWNER TABLE AND ISSUE TABLE SET DATATABLE
    var OwnerTable = $('#OwnerTable,#IssueTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });

});

// ISSUE BY
$(document).ready(function () {
    
    
    $("#issue_username").autocomplete({
        source: codeStaff,
        close:function( event, ui ) {
           $("#issue_username").trigger('keyup');
        }
    });
    $("#issue_username").on('keyup', function () {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var username = this.value.toUpperCase();
        var name = this.value;
        $("#issue_id,#issue_name").val(null);
        $.each(staff, function (key, value) {
            if (value.username.toUpperCase() === username) {
                console.log('ok');
                $("#issue_id").val(value.id);
                $("#issue_name").val(value.name);
            }
            if(name === value.name){
                $("#issue_username").val(value.username);
                $("#issue_id").val(value.id);
                $("#issue_name").val(value.name);
                username = $("#issue_username").val().toUpperCase();
            }
            
        });
    });
    $("#IssueTable tr").on('click', function () {
        issue_id = $(this).find(".issue-id").text();
        issue_username = $(this).find(".issue-username").text();
        issue_name = $(this).find(".issue-name").text();
        $("#issue_id").val(issue_id);
        $("#issue_username").val(issue_username);
        $("#issue_name").val(issue_name);
        console.log(issue_id);
        $("#IssueModal").modal('hide');
    });

});

// DETAIL TABLE
$(document).ready(function () {
    AddRow(parseInt($("#counter").val()));
    var templock = parseInt($("#requestLock").val());
    $("#DetailTable").on('click', '.remCF', function () {
        if(templock == 0){
            //console.log('remove');
            $(this).parent().parent().remove();
            //$(this).parent().parent().hide();
            var rowAll = $("#DetailTable tr").length;
            console.log("rowAll : " + rowAll);
            $("#counter").val(rowAll);

            if (rowAll < 2) {
                console.log("show button tr_FormulaAddRow");
                $("#tr_FormulaAddRow").removeClass("hide");
                $("#tr_FormulaAddRow").addClass("show");
            }
        }
    });
    $("#tr_FormulaAddRow a").on('click', function () {
        $(this).parent().removeClass("show");
        $(this).parent().addClass("hide");
    });
    $("#DetailTable").on("keyup", function () {
        var rowAll = $("#DetailTable tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() != '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#DetailTable tr").length;
                //console.log('Row: ' + rowIndex + ', Column: ' + colIndex + ', All Row ' + rowAll);
                if (rowIndex == rowAll) {
                    //console.log("rowIndex: " + rowIndex);
                    console.log("rowAll : " + rowAll);
                    //console.log("addRow");
                    AddRow(rowAll);
                }
            }

        });
    });
    $("#DetailTable").on("change", "select:last", function () {
        var row = parseInt($("#counter").val());
        AddRow(row);
    });

});

function AddRow(row) {

    $("#DetailTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input type="text" class="hidden" id="row-' + row + '-id" name="row-' + row + '-id" /></td>' +
            '<td><input type="text" class="form-control" id="row-' + row + '-detail" name="row-' + row + '-detail" maxlength="100"/></td>' +
            '<td><input type="text" class="form-control money" id="row-' + row + '-qty" name="row-' + row + '-qty" maxlength="11"/></td>' +
            '<td><input type="text" class="form-control money" id="row-' + row + '-cost" name="row-' + row + '-cost" maxlength="11"/></td>' +
            '<td><select id="row-' + row + '-currencycost" name="row-' + row + '-currencycost" class="form-control"><option value="">---------</option></select></td>' +         
            '<td><input type="text" class="form-control money" id="row-' + row + '-amount" name="row-' + row + '-amount" maxlength="11"></td>' +
            '<td><select id="row-' + row + '-currency" name="row-' + row + '-currency" class="form-control"><option value="">---------</option></select></td>' +         
            '<td class="text-center"><a class="remCF" id="ButtonRemove'+row+'"><span id="SpanRemove'+row+'" class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
            '</tr>'
            );
    
    $("#select-currency option").clone().appendTo("#row-" + row + "-currency");
    $("#select-currency option").clone().appendTo("#row-" + row + "-currencycost");
    var tempCount = parseInt($("#counter").val()) + 1;
    $("#counter").val(tempCount);
}

function deleteDesc(bookId, descId) {
    $.ajax({
        url: 'AirTicket.smi?action=deleteDesc',
        type: 'get',
        data: {bId: bookId, dId: descId},
        success: function () {
            console.log('success');
            location.reload();
        },
        error: function () {
            console.log("error");
        }
    });
}

function printTicketOrder(refno) {
    $('#textAlertSelectPnr').hide();
    var PnrID = "";
    $('#TableAir tr.row_selected').each(function () {
        PnrID = $(this).attr('id');
    });
    if (PnrID == "") {
        $('#textAlertSelectPnr').show();
    } else {
        window.open("report.smi?name=TicketOrder&refno=" + refno + "&pnrID=" + PnrID);
    }
}

function disablePnr() {
    $("#disablePnr").submit();
}

function setDisablePnrForm(id, name) {
    $("#disablePnrId").val(id);
    document.getElementById('disableCode').innerHTML = "Are you sure to disable PNR : " + name + " ?";
}
function enablePnr() {
    $("#enablePnr").submit();
}

function setEnablePnrForm(id, name) {
    $("#enablePnrId").val(id);
    document.getElementById('enableCode').innerHTML = "Are you sure to enable PNR : " + name + " ?";
}

function airUnlock(){
    $("#AirUnlockModal").modal("show");
}

function unlockAirTicket(){
    document.getElementById('flagAir').value = '0';
    document.getElementById('action').value = 'unlockAirTicket';
    document.getElementById('AirTicket').submit();
    
}