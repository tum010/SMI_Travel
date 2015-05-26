$(document).ready(function () {
    // PASSENGER TABLE
    $('#PassengerTable,#FlightTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": false,
        "bInfo": false
    });
    // FLIGHT TABLE
    $("#ckeckList").click(function () {
        $("#FlightTable input:checkbox").prop('checked',true);
    });
    $('table').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });

    $("#staff_username").on('keyup', function () {
        $("#staff_name").val(null);
        var code = $(this).val().toUpperCase();
        $.each(sf, function (key, value) {
            if (value.username.toUpperCase() === code) {
                $("#staff_id").val(value.id);
                $("#staff_name").val(value.name);
            }
        });
    });

    $('#datetimepicker3').datetimepicker({
        pickTime: false
    });
    $('span').click(function () {
        var position = $(this).offset();
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });

    // STAFF TABLE
    $('#StaffTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    $("#StaffTable tr").on('click', function () {
        var staff_id = $(this).find(".staff-id").text();
        var staff_username = $(this).find(".staff-username").text();
        var staff_name = $(this).find(".staff-name").text();
        $("#staff_id").val(staff_id);
        $("#staff_username").val(staff_username);
        $("#staff_name").val(staff_name);
        $("#StaffModal").modal('hide');
    });
    // VALIDATOR
    $("#RefundForm").bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            required: 'glyphicon glyphicon-asterisk',
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        }
    });

});