$(document).ready(function () {
    $('table a').on('click', function () {
        $("#ModalTitle").text("Passenger Detail");
    });
    $('#btnAdd').on('click', function () {
        $("#ModalTitle").text("Add Passenger");
    });
    $('.date').datetimepicker();
    $('span').click(function () {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });

    var table = $('#Passenger').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bInfo": false
    });

    $('#Passenger tbody').on('click', 'tr', function () {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
        }
        else {
            table.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }
    });
    //$('.dataTables_length label').remove();

});

function _calculateAge(birthday) {
    var ageDifMs = Date.now() - birthday.getTime();
    var ageDate = new Date(ageDifMs);
    return Math.abs(ageDate.getUTCFullYear() - 1970);
}

function ModalPassneger(id, code) {
    var PassnegerID = document.getElementById('PassengerID');
    PassnegerID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete booking passenger : " + code + " ?";
}

function Delete() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('PassengerForm').submit();
}

$(document).ready(function () {
    var bla = $('#resultText').val();
   
    if(bla == "save successful"){
        
        $('#textAlertDivSave').show();
    }else if ( bla === ""){
        $('#textAlertDivSave').hide();
    }else {
        $('#textAlertDivNotSave').show();
    }
});