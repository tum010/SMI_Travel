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

function selectRefundDetail(counter){
    $("#RefundTicketDetailAdd"+counter).addClass("hidden");
    var count = document.getElementById('countListOther');
    for(var i = 1 ; i <= count.value ; i++){
        $("#RefundTicketDetail"+i).addClass("hidden");
    }
    $('#SpanEdit'+ counter).click(function() {

        if($("#RefundTicketDetail"+counter).hasClass("hidden")){
            $("#RefundTicketDetail"+counter).removeClass("hidden");
        }else{
            $("#RefundTicketDetail"+counter).addClass("hidden");
        }
    });
}

function addRefundDetail(counter){
    var count = document.getElementById('countListOther');
//    console.log(count.value);
    for(var i = 1 ; i <= count.value ; i++){
        $("#RefundTicketDetail"+i).addClass("hidden");
    }
    var countadd = document.getElementById('countListAdd');
    for(var i = 1 ; i <= countadd.value ; i++){
        $("#RefundTicketDetailAdd"+i).addClass("hidden");
    }
    $("#buttonAddRefundDetail").click(function() {
           console.log("counter : " + counter);
        if($("#RefundTicketDetailAdd"+counter).hasClass("hidden")){
            console.log("Show");
            $("#RefundTicketDetailAdd"+counter).removeClass("hidden");
        }else{
            console.log("NotShow");
            $("#RefundTicketDetailAdd"+counter).addClass("hidden");
        }
    });
}