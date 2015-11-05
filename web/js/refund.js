$(document).ready(function () {  
    // Add Row Auto key
    $("#RefundTicketDetailTable").on("keyup", function () {
        var rowAll = $("#RefundTicketDetailTable tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#RefundTicketDetailTable tr").length;
                if (rowIndex === rowAll) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    addRowRefundTicketDetail(rowAll);
                }
            }
        });
    });
    
    // get row in table now
    var rowCount = $('#RefundTicketDetailTable tr').length;
    console.log("Row Refund Ticket Detail : " + rowCount);
    $("#counterTable").val(rowCount);
    addRowRefundTicketDetail(rowCount++);

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
    
    // Refund  and Receive
    $("#receiveUserTable tr").on('click', function () {
        var user_id = $(this).find(".user-id").text();
        var user_user = $(this).find(".user-user").text();
        var user_name = $(this).find(".user-name").text();
        $("#receiveById").val(user_id);
        $("#receiveBy").val(user_user);
        $("#receiveByName").val(user_name);
        $("#receiveUserModal").modal('hide');
    });

    $("#refundCustTable tr").on('click', function () {
        var user_id = $(this).find(".item-billto").text();
//        var user_code = $(this).find(".user-user").text();
        var user_name = $(this).find(".item-name").text();
//        $("#refundById").val(user_id);
        $("#refundBy").val(user_id);
        $("#refundByName").val(user_name);
        $("#refundCustModal").modal('hide');
    });
    
    var userCode = [];
    $.each(user, function (key, value) {
        userCode.push(value.code);
        userCode.push(value.name);
        if ($("#receiveBy").val() === value.code) {
            $("#receiveByName").val(value.name);
        }
    });

     $("#receiveBy").autocomplete({
        source: userCode,
        close: function (event, ui) {
            $("#receiveBy").trigger('keyup');
        }
    });
    
    $("#receiveBy").on('keyup', function () {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value.toUpperCase();
        console.log("Name :" + name);
        $("#agent_id,#agent_name,#agent_addr,#agent_tel").val(null);
        $.each(user, function (key, value) {
            if (value.code.toUpperCase() === code) {
                $("#receiveByName").val(value.name);
                $("#receiveBy").val(value.code);
            }
            else if (value.name.toUpperCase() === name) {
                $("#receiveBy").val(value.code);
                $("#receiveByName").val(value.name);
            }
        });
    });

    //autocomplete
    $("#refundBy").keyup(function (event) {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        if ($(this).val() === "") {
            $("#refundBy").val("");
            $("#refundByName").val("");
        } else {
            if (event.keyCode === 13) {
                searchCustomerAutoList(this.value);
            }
        }
    });

    var showflag = 1;
    $("#refundBy").keydown(function () {

        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        if (showflag == 0) {
            $(".ui-widget").css("top", -1000);
            showflag = 1;
        }
    });

    $("#searchCustFrom").keyup(function (event) {
        if (event.keyCode === 13) {
            if ($("#searchCustFrom").val() == "") {
                // alert('please input data');
            }
            searchCustomerAgentList($("#searchCustFrom").val());
        }
    });
    
    $('#refundCustTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    $('#receiveUserTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
   
});

