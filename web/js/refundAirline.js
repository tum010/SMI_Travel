var deleteRowNo = 0;
$(document).ready(function () {
    $(".datetime").datetimepicker({
    });

//    $(".datemask").mask('0000-00-00', {reverse: true});
    $(".decimal").inputmask({
        alias: "decimal",
        integerDigits: 6,
        groupSeparator: ',',
        autoGroup: true,
        digits: 2,
        allowMinus: false,
        digitsOptional: false,
        placeholder: "0"
    });


    //Add Blank row for user input.
    addRowRefundAirlineList();
    /*Auto Add lastrow */

    $("#refundNo").on("keyup", function (e) {
        if (e.which == 13) {
            var action = document.getElementById('action');
            action.value = 'search';
            document.getElementById('RefundAirlineForm').submit();
        }
    });

    $("#buttonSave").click(function () {

        var action = document.getElementById('action');
        action.value = 'save';
        document.getElementById('RefundAirlineForm').submit();
    });

//    $(document).on('click', '#RefundAirlineTable tbody tr:nth-last-child(2) td  input ,#RefundAirlineTable tbody tr:nth-last-child(2) td .input-group-addon', function (e) { // .input-group-addon, .datemask
//
//
//        /*OnEvent Add lastrow */
//        addRowRefundAirlineList();
//    });

});

function addRowRefundAirlineList() {
    var counter = $('#RefundAirlineTable tbody tr').length / 2;
    counter++;
    var clone = $('#tempRow tbody tr:lt(2)').clone();
    clone.removeClass("hide");

    clone.each(function () {
        $(this).attr({row: counter});
    });
    clone.find('div,input,span,output').each(function () {
        console.log('count :' + counter);
        $(this).attr({
            id: $(this).attr('colName') + counter,
            name: $(this).attr('colName') + counter
        });
        $(".datetime").datetimepicker({
        });

        $('.decimal').inputmask({
            alias: "decimal",
            integerDigits: 6,
            groupSeparator: ',',
            autoGroup: true,
            digits: 2,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0"
        });
        $("#counter").val(counter);


        $("#RefundAgentTable tr").on('click', function () {
            var agent_id = $(this).find(".agent-id").text();
            var agent_user = $(this).find(".agent-user").text();
            var agent_name = $(this).find(".agent-name").text();
            $("#refundAgentId").val(agent_id);
            $("#refundAgentCode").val(agent_user);
            $("#refundAgentName").val(agent_name);
            $("#RefundAgentModal").modal('hide');
        });

        var agentCode = [];
        $.each(agent, function (key, value) {
            agentCode.push(value.code);
            agentCode.push(value.name);
        });

        $("#refundAgentCode").autocomplete({
            source: agentCode,
            close: function (event, ui) {
                $("#refundAgentCode").trigger('keyup');
            }
        });

        $("#refundAgentCode").on('keyup', function () {
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var code = this.value.toUpperCase();
            var name = this.value.toUpperCase();
            console.log("Name :" + name);
            $("#agent_id,#agent_name,#agent_addr,#agent_tel").val(null);
            $.each(agent, function (key, value) {
                if (value.code.toUpperCase() === code) {
                    $("#refundAgentId").val(value.id);
                    $("#refundAgentName").val(value.name);
                    $("#refundAgentCode").val(value.code);
                }
                else if (value.name.toUpperCase() === name) {
                    $("#refundAgentCode").val(value.code);
                    $("#refundAgentId").val(value.id);
                    $("#refundAgentName").val(value.name);
                }
            });
        });

        console.log(this.id + '/' + "ticketno");
        if (this.id.startsWith("ticketNo")) {
            $(this).on("keyup", function (event) {
                var keycode = (event.keyCode ? event.keyCode : event.which);
                if (keycode == '13') {

                    var url = 'AJAXServlet';
                    var servletName = 'RefundAirlineServlet';
                    var servicesName = 'AJAXBean';
                    var param = 'action=' + 'text' +
                            '&servletName=' + servletName +
                            '&servicesName=' + servicesName +
                            '&type=getTicketFare' +
                            '&ticketNo=' + this.value;
                    var row = parseInt($(this).parent().parent().attr("row"));
                    try {
                        $.ajax({
                            type: "POST",
                            url: url,
                            cache: false,
                            data: param,
                            beforeSend: function () {
                                $("#dataload").removeClass("hidden");
                            },
                            success: function (msg) {
                                console.log("getAutoListBillto ==" + msg);
                                var fare = JSON.parse(msg);
                                console.log(fare.Id);
                                $("#ticketId" + row).val(fare.Id);
                                $("#ticketDate" + row).html(fare.TicketDate);
                                $("#sectorIssue" + row).html(fare.Sector);
                                $("#total" + row).html(fare.Total);
                                $("#department" + row).html(fare.Dept);
                                $("#passsenger" + row).html(fare.Passenger);
                                var counter = $('#RefundAirlineTable tbody tr').length / 2;
                                if (row === counter) {
                                    addRowRefundAirlineList();
                                }

                            }, error: function (msg) {
                                console.log('auto ERROR');
                                $("#dataload").addClass("hidden");
                            }
                        });
                    } catch (e) {
                        alert(e);
                    }
                }
                event.stopPropagation();
            });
        }
    });
    $('#RefundAirlineTable tbody').append(clone);

    $(".daydatepicker").datetimepicker({
        pickTime: false
    });
}

function setDeletRow(el) {
    $("#delCode").text('are you sure delete ?');
    deleteRowNo = $(el).parent().parent().attr("row");
}

function deleteRowRefundAirlineList() {
    var detailId = $("#detailId" + deleteRowNo).val();
    if ("" !== detailId) {
        console.log("call AJAX")

        var url = 'AJAXServlet';
        var servletName = 'RefundAirlineServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&type=delete' +
                '&detailId=' + detailId;
        try {
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                beforeSend: function () {
                    $("#dataload").removeClass("hidden");
                },
                success: function (msg) {
                    //   console.log("getAutoListBillto =="+msg);
                    var result = JSON.parse(msg);
                    if (result) {
                        removeAndRenameRow();
                        $("#alertTextSuccess").html("Delete success.");
                        $("#alertSuccess").show();
                    } else {
                        var ticketNo = $("#ticketNo" + deleteRowNo).val();
                        $("#alertTextFail").html("TicketNo " + ticketNo + " is already use in payment airline.");
                        $("#alertFail").show();
                        $('#DeleteRefundAirline').modal('hide');
                    }

                }, error: function (msg) {
                    console.log('auto ERROR');
                    $("#dataload").addClass("hidden");
                }
            });
        } catch (e) {
            alert(e);
        }
    } else {
        removeAndRenameRow();
    }
}

function removeAndRenameRow() {
    $('#RefundAirlineTable tbody tr').each(function () {
        var row = $(this).attr('row');
        if (row === deleteRowNo) {
            $(this).remove();
        }
    });

    var i = 1;
    $('#RefundAirlineTable tbody tr:nth-child(2n-1)').each(function () {
        $(this).next().add(this).attr({row: i});
        $(this).next().add(this).find('div,input,span,output').each(function () {
            $(this).attr({
                id: $(this).attr('colName') + i,
                name: $(this).attr('colName') + i
            });
        });
        i++;
    });
    $("#counter").val(i - 1);
    $('#DeleteRefundAirline').modal('hide');
}

