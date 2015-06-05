// ### SET DEFAULT PAEG ### //
$(document).ready(function () {
    $('.date').datetimepicker();
    $('span').click(function () {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    $(".money").mask('000,000,000,000,000,000', {reverse: true});
});

// ### DETAIL PANAL ### //
$(document).ready(function () {
    // COUNT DAY
    getDate();
    $("button").on('mouseover', function () {
       var days =  getDate();
       if(days<=0){
           alert("Night ="+days +" !");
       }
       console.log(days);
       if(isNaN(days)){
           alert("CheckIn or Check Out not date format");
       }
    });
    $(document).on('mousemove', function () {
       getDate();
    });
    // SET VALUE MEAL
    $("#meal").val($("#get-meal").val());
    // SET STATUS
    $("#status").val($("#get-itemstatus").val());
    if ($("#get-itemstatus").val() === "") {
        $("#status").val("ok");
    }
    //  SUM PASSENGER
    $("#sum").val(parseInt($("#adult").val()) + parseInt($("#child").val()) + parseInt($("#infant").val()));
    $("#adult,#child,#infant").on('keyup change', function () {
        $("#sum").val(parseInt($("#adult").val()) + parseInt($("#child").val()) + parseInt($("#infant").val()));
    });

    // VALIDATE
    var vForm = $("#HotelForm");
    vForm.bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            checkin: {
                validators: {
                    notEmpty: {
                        message: 'The Chick In is required'
                    },
                    date: {
                        format: 'YYYY-MM-DD',
                        message: 'The value is not a valid date'
                    }
                }
            },
            checkout: {
                validators: {
                    notEmpty: {
                        message: 'The Chick out is required'
                    },
                    date: {
                        format: 'YYYY-MM-DD',
                        message: 'The value is not a valid date'
                    }
                }
            },
            deadline: {
                validators: {
                    date: {
                        format: 'YYYY-MM-DD',
                        message: 'The value is not a valid date'
                    }
                }
            },
            adult: {
                validators: {
                    notEmpty: {
                        message: 'The adult is required'
                    },
                    digits: {
                        message: 'The adult is Number'
                    }
                }
            },
            child: {
                validators: {
                    notEmpty: {
                        message: 'The child is required'
                    },
                    digits: {
                        message: 'The child is Number'
                    }
                }
            },
            infant: {
                validators: {
                    notEmpty: {
                        message: 'The infant is required'
                    },
                    digits: {
                        message: 'The adult is Number'
                    }
                }
            },
            leaderCode: {
                validators: {
                    notEmpty: {
                        message: 'The Leader is required',
                        //enabled: false
                    },
                }
            },
        }
    })
            .on('success.field.bv', function (e, data) {

                if (data.bv.isValid()) {
                    data.bv.disableSubmitButtons(false);
                }
            });
    vForm.on('mouseover', function () {
        var leadercode = $(this).find('[name="hotelName"]');
        var isEmpty = leadercode.val() === '';
        vForm.bootstrapValidator('enableFieldValidators', 'hotelName', isEmpty);
        var checkin = $(this).find('[name="checkin"]');
        var isEmpty = checkin.val() === '';
        vForm.bootstrapValidator('enableFieldValidators', 'checkin', isEmpty);
        var checkout = $(this).find('[name="checkout"]');
        var isEmpty = checkout.val() === '';
        vForm.bootstrapValidator('enableFieldValidators', 'checkout', isEmpty);
    });

    var codeHotel = [];
    $.each(hotel, function (key, value) {
        codeHotel.push(value.code);
        if ( !(value.name in codeHotel) ){
           codeHotel.push(value.name);
          
        }
    });
    $("#hotel-code").autocomplete({
        source: codeHotel,
        close:function( event, ui ) {
           $("#hotel-code").trigger('keyup');
        }
    });
    $("#hotel-code").on('keyup', function () {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value;
        $("#hotel-id,#hotel-name").val(null);
        $.each(hotel, function (key, value) {
            if (value.code.toUpperCase() === code) {
                console.log('ok');
                $("#hotel-id").val(value.id);
                $("#hotel-name").val(value.name);
            }
            if(name === value.name){
                $("#hotel-code").val(value.code);
                code = $("#hotel-code").val().toUpperCase();
            }
        });
    });

});
function getDate() {
    var start = new Date($('#checkin').val());
    var end = new Date($('#checkout').val());
    var diff = new Date(end - start);
    var days = diff / 1000 / 60 / 60 / 24;
    if (days) {
        $("#day").val(days);
//        console.log('checkin : ' + start);
//        console.log('checkout : ' + end);
//        console.log('day : ' + days);
    } else {
        $("#day").val(0);
    }
     return days;
}

// ### Formula PANAL ### //
$(document).ready(function () {
    FormulaAddRow(parseInt($("#table-formula-size").val()) + 1);
    $("#formula-table").on('click', '.newRemCF', function () {
        //console.log('remove');
        $(this).parent().parent().remove();
        var rowAll = $("#formula-table tr").length;
        console.log("rowAll : " + rowAll);
        $("#roomCounter").val(rowAll);
        if (rowAll < 2) {
            console.log("show button tr_FormulaAddRow");
            $("#tr_FormulaAddRow").removeClass("hide");
            $("#tr_FormulaAddRow").addClass("show");
        }
    });
    $("#tr_FormulaAddRow a").on('click', function () {
        $(this).parent().removeClass("show");
        $(this).parent().addClass("hide");
    });

    $("#formula-table").on("keyup", function () {
        var rowAll = $("#formula-table tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() != '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#formula-table tr").length;
                //console.log('Row: ' + rowIndex + ', Column: ' + colIndex + ', All Row ' + rowAll);
                if (rowIndex == rowAll) {
                    //console.log("rowIndex: " + rowIndex);
                    //console.log("rowAll : " + rowAll);
                    //console.log("addRow");
                    FormulaAddRow(rowAll);
                }
            }

        });
    });


});
function FormulaAddRow(row) {

    if (!row) {
        row = 1;
    }

    $("#formula-table tbody").append(
            '<tr>' +
            '<td class="hidden"><input id="row-room-' + row + '-id" name="row-room-' + row + '-id"  type="text" class="form-control"></td>' +
            '<td><input id="row-room-' + row + '-qty" name="row-room-' + row + '-qty"  type="text" class="form-control text-right money" maxlength="3"></td>' +
            '<td><input id="row-room-' + row + '-room" name="row-room-' + row + '-room" type="text" class="form-control" maxlength="50"></td>' +
            '<td><input id="row-room-' + row + '-category" name="row-room-' + row + '-category" type="text" class="form-control" maxlength="50"></td>' +
            '<td><input id="row-room-' + row + '-cost" name="row-room-' + row + '-cost" type="text" class="form-control money" maxlength="11"></td>' +
            '<td><input id="row-room-' + row + '-price" name="row-room-' + row + '-price" type="text" class="form-control money" maxlength="11"></td>' +
            '<td class="text-center">' +
            '<a class="newRemCF" id="ButtonFormulaRemove'+row+'"><span id="SpanFormulaRemove'+row+'"  class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
            '</tr>'
            );
    var tempCount = parseInt($("#roomCounter").val()) + 1;
    $("#roomCounter").val(tempCount);
}
function deleteRoom(hotelId, roomId) {
    console.log(hotelId + ":" + roomId);
    $.ajax({
        url: 'HotelDetail.smi?action=deleterRoom',
        type: 'get',
        data: {hId: hotelId, rId: roomId},
        success: function () {
            console.log('success');
        },
        error: function () {
            console.log("error");
        }
    });
}


// ### ADDTION PANEL ### //
$(document).ready(function () {
    AdditionAddRow(parseInt($("#table-additional-size").val()) + 1);
    $("#addition-table").on('click', '.newRemCF', function () {
        $(this).parent().parent().remove();
        var rowAll = $("#addition-table tr").length;
        console.log("rowAll : " + rowAll);
        if (rowAll < 2) {
            console.log("show button");
            $("#tr_AdditionAddRow").removeClass("hide");
            $("#tr_AdditionAddRow").addClass("show");
        }
    });
    $("#tr_AdditionAddRow a").click(function () {
        $(this).parent().removeClass("show");
        $(this).parent().addClass("hide");
    });
    $("#addition-table").on("keyup", function () {
        var rowAll = $("#addition-table tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() != '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#addition-table tr").length;
                //console.log('Row: ' + rowIndex + ', Column: ' + colIndex + ', All Row ' + rowAll);
                if (rowIndex == rowAll) {
                    //console.log("rowIndex: " + rowIndex);
                    //console.log("rowAll : " + rowAll);
                    //console.log("addRow");
                    AdditionAddRow(rowAll);
                }
            }

        });
    });


});
function AdditionAddRow(row) {

    if (!row) {
        row = 1;
    }

    $("#addition-table tbody").append(
            '<tr>' +
            '<td><input id="row-request-' + row + '-category" name="row-request-' + row + '-category" type="text" class="form-control" maxlength="100"></td>' +
            '<td><input id="row-request-' + row + '-description" name="row-request-' + row + '-description" type="text" class="form-control" maxlength="100"></td>' +
            '<td><input id="row-request-' + row + '-cost" name="row-request-' + row + '-cost" type="text" class="form-control money" maxlength="11"></td>' +
            '<td><input id="row-request-' + row + '-price" name="row-request-' + row + '-price" type="text" class="form-control money" maxlength="11"></td>' +
            '<td class="text-center">' +
            '<a id="ButtonAdditonRemove'+row+'" class="newRemCF"><span id="SpanAdditionRemove'+row+'"  class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
            '</tr>'
            );
    var tempCount = parseInt($("#requestCounter").val()) + 1;
    $("#requestCounter").val(tempCount);
}
function deleteAddition(hotelId, requestId) {
    console.log(hotelId + ":" + requestId);
    $.ajax({
        url: 'HotelDetail.smi?action=deleteRequest',
        type: 'get',
        data: {hId: hotelId, reId: requestId},
        success: function () {
            console.log('success');
        },
        error: function () {
            console.log("error");
        }
    });
}

// ### PASSENGER PANEL ### //
$(document).ready(function () {
    PassengerAddRow((parseInt($("#table-passenger-size").val()) + 1));
    $("#passenger-table").on('click', '.newRemCF', function () {
        $(this).parent().parent().remove();
        var rowAll = $("#passenger-table tr").length;
        console.log("rowAll : " + rowAll);
        if (rowAll < 2) {
            console.log("show button");
            $("#tr_PassengerAddRow").removeClass("hide");
            $("#tr_PassengerAddRow").addClass("show");
        }
    });
    $("#tr_PassengerAddRow a").click(function () {
        $(this).parent().removeClass("show");
        $(this).parent().addClass("hide");
    });
    $("#passenger-table").on("change", "select:last", function () {
        console.log('on change');
        var rowAll = $("#passenger-table tr").length;
        PassengerAddRow(rowAll);
    });
});
function PassengerAddRow(row) {

    if (!row) {
        row = 1;
    }

    $("#passenger-table tbody").append(
            '<tr>' +
            '<td  hidden=""><input  id="row-passenger-' + row + '-id" name="row-passenger-' + row + '-id"  type="text" class="form-control"></td>' +
            '<td>' + row + '</td>' +
            '<td><select id="row-passenger-' + row + '-name" name="row-passenger-' + row + '-name" class="form-control"><option></option></select></td>' +
            '<td class="text-center">' +
            '<a class="newRemCF" id="ButtonPassengerRemove'+row+'"><span id="SpanPassengerRemove'+row+'" class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
            '</tr>'
            );
    $("#select-list-passenger option").clone().appendTo("#row-passenger-" + row + "-name");
    var tempCount = parseInt($("#passengerCounter").val()) + 1;
    $("#passengerCounter").val(tempCount);
}
function deletePassenger(hotelId, passengerId) {
    console.log(hotelId + ":" + passengerId);
    $.ajax({
        url: 'HotelDetail.smi?action=deletePassenger',
        type: 'get',
        data: {hId: hotelId, pId: passengerId},
        success: function () {
            console.log('success');
        },
        error: function () {
            console.log("error");
        }
    });
}

// ### HOTEL MODAL #### //
$(document).ready(function () {
    var OwnerTable = $('#HotelTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    $('#HotelTable tbody').on('click', 'tr', function () {
        $('.collapse').collapse('show');
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
    $("#HotelTable").on('click','tr', function () {
        console.log('table tr');
        hotel_id = $(this).find(".hotel-id").text();
        hotel_code = $(this).find(".hotel-code").text();
        hotel_name = $(this).find(".hotel-name").text();
        $("#hotel-id").val(hotel_id);
        $("#hotel-code").val(hotel_code);
        $("#hotel-name").val(hotel_name);
        console.log(hotel_id);
        $("#HotelModal").modal('hide');
    });
    $("#hotel_code").on('keyup', function () {
        $.each(array_air, function (index, value) {
            if (value.hotel_code == $("#hotel_code").val()) {
                console.log(index + ": " + value.hotel_code);
                $("#hotel_name").val(value.hotel_name);
            }
        });

    });

});

// ### DELETE MODAL #### //
function ConfirmDelete(hotelId, rowType, id, Ccount) {
    hotel = hotelId;
    rowtype = rowType;
    cId = id;
    cCount = Ccount;
    var deleteType;
    if (rowType === '1') {
        deleteType = 'room ?';
    } else if (rowType === '2') {
        deleteType = 'request ?';
    } else if (rowtype === '3') {
        deleteType = 'passenger ?';
    }
    $("#delCode").text('are you sure delete ' + deleteType);
    $('#DeleteHotel').modal('show');

}
function DeleteRow(hotelId, rowType, id, cCount) {
    console.log(rowType);
    if (rowType === '1') {
        deleteRoom(hotelId, id);
        $("#row-room-" + cCount + "-room").parent().parent().remove();
        var rowAll = $("#formula-table tr").length;
        console.log("rowAll : " + rowAll);
        $("#roomCounter").val(rowAll);
        if (rowAll < 2) {
            console.log("show button tr_FormulaAddRow");
            $("#tr_FormulaAddRow").removeClass("hide");
            $("#tr_FormulaAddRow").addClass("show");
        }
    } else if (rowType === '2') {
        deleteAddition(hotelId, id);
        $("#row-request-" + cCount + "-price").parent().parent().remove();
        var rowAll = $("#addition-table tr").length;
        console.log("rowAll : " + rowAll);
        if (rowAll < 2) {
            $("#tr_AdditionAddRow").removeClass("hide");
            $("#tr_AdditionAddRow").addClass("show");
        }

    } else if (rowType === '3') {
        deletePassenger(hotelId, id);
        $("#row-passenger-" + cCount + "-id").parent().parent().remove();
        var rowAll = $("#passenger-table tr").length;
        console.log("rowAll : " + rowAll);
        if (rowAll < 2) {
            console.log("show button");
            $("#tr_PassengerAddRow").removeClass("hide");
            $("#tr_PassengerAddRow").addClass("show");
        }
    }
    $('#DeleteHotel').modal('hide');
}