var deleteRowNo = 0;
$(document).ready(function () {


    $('.datemask').mask('0000-00-00');
    addRowRefundAirlineList();
    $(".decimal").inputmask({
        alias: "decimal",
        integerDigits: 8,
        groupSeparator: ',',
        autoGroup: true,
        digits: 2,
        allowMinus: false,
        digitsOptional: false,
        placeholder: "0.00"
    });


    $("#RefundAirlineForm").bootstrapValidator({
//        excluded: [':disabled', ':hidden', ':not(:visible)'],
        framework: 'bootstrap',
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            agentCode: {
                validators: {
                    notEmpty: {
                        message: 'The agent is required'
                    }
                }
            }
        }
    });

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

    $("#receiveUserTable tr").on('click', function () {
        var user_id = $(this).find(".user-id").text();
        var user_user = $(this).find(".user-user").text();
        var user_name = $(this).find(".user-name").text();
        $("#receiveBy").val(user_user);
        $("#receiveByName").val(user_name);
        $("#receiveUserModal").modal('hide');
    });

    var userCode = [];
    $.each(user, function (key, value) {
        userCode.push(value.code);
        userCode.push(value.name);
        if ($("#receiveBy").val() === value.code) {
            $("#receiveByName").val(value.name);
        }
    });

    $("#refundAgentCode").autocomplete({
        source: agentCode,
        close: function (event, ui) {
            $("#refundAgentCode").trigger('keyup');
        }
    });

    $("#receiveBy").autocomplete({
        source: userCode,
        close: function (event, ui) {
            $("#receiveBy").trigger('keyup');
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

    //Add Blank row for user input.
    /*Auto Add lastrow */

    $("#refundNo").on("keyup", function (e) {
        if (e.which == 13) {

            var action = document.getElementById('action');
            action.value = 'search';
            document.getElementById('RefundAirlineForm').submit();
        }
    });

    $("#ButtonSearch").click(function () {

        if ($("#refundNo").val() === "") {
            return;
        }
        var action = document.getElementById('action');
        action.value = 'search';
        document.getElementById('RefundAirlineForm').submit();
    });

    $("#buttonSave").click(function () {

        $('#RefundAirlineForm').bootstrapValidator('revalidateField', 'agentCode');

        if ($("#refundAgentId").val() === "") {
            return;
        }
        var valid = true;
        for (var i = 1; i < $("#counter").val(); i++) {
            var refund = $("#refund" + i).val();
            var sector = $("#sectorIssue" + i).html();
            if ("" === refund || sector.indexOf(refund) < 0) {
                $("#refund" + i).css('border-color', "Red");
                valid = false;
            } else {
                $("#refund" + i).css('border-color', "Green");
            }
        }
        if (valid) {
            var action = document.getElementById('action');
            action.value = 'save';
            document.getElementById('RefundAirlineForm').submit();
        }
    });


    $("#ButtonSaveAndNew").click(function () {

        $('#RefundAirlineForm').bootstrapValidator('revalidateField', 'agentCode');

        if ($("#refundAgentId").val() === "") {
            return;
        }
        var valid = true;
        for (var i = 1; i < $("#counter").val(); i++) {
            var refund = $("#refund" + i).val();
            var sector = $("#sectorIssue" + i).html();
            if ("" === refund || sector.indexOf(refund) < 0) {
                $("#refund" + i).css('border-color', "Red");
                valid = false;
            } else {
                $("#refund" + i).css('border-color', "Green");
            }
        }
        if (valid) {
            var action = document.getElementById('action');
            action.value = 'saveAndNew';
            document.getElementById('RefundAirlineForm').submit();
        }
    });
//
//    $.each(customer, function (key, value) {
//        if ($("#refundBy").val() === value.code) {
//            $("#refundByName").val(value.name);
//        }
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
        $(this).attr({
            id: $(this).attr('colName') + counter,
            name: $(this).attr('colName') + counter
        });
        $(".datetime").datetimepicker({
        });
        $(".decimal").inputmask({
            alias: "decimal",
            integerDigits: 8,
            groupSeparator: ',',
            autoGroup: true,
            digits: 2,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0.00"
        });
        $("#counter").val(counter);

        if (this.id.startsWith("ticketNo")) {
            $(this).on("keyup", function (event) {
                var inputTicket = this;
                var ticketNo = this.value;
                var ticketNoId = this.id;
                var keycode = (event.keyCode ? event.keyCode : event.which);
                if (keycode == '13') {
                    var duplicate = false;
                    $("#alertFail").hide();
                    $("#alertSuccess").hide();
                    $('#RefundAirlineTable tbody [colName="ticketNo"]').each(function () {
                        if (ticketNoId !== this.id && ticketNo === this.value) {
                            console.log(this.value);
                            inputTicket.style.borderColor = "Red";
                            duplicate = true;
                            return;
                        }
                    });
                    if (!duplicate) {
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
                                    if (msg !== "") {
                                        var fare = JSON.parse(msg);
                                        console.log(fare.Id);
                                        $("#ticketId" + row).val(fare.Id);
                                        $("#ticketDate" + row).html(fare.TicketDate);
                                        $("#sectorIssue" + row).html(fare.Sector);
                                        $("#total" + row).html(fare.Total);
                                        $("#department" + row).html(fare.Dept);
                                        $("#passsenger" + row).html(fare.Passenger);
                                        document.getElementById("refundBy").value = fare.InvTo;
                                        document.getElementById("refundByName").value = fare.InvName;
                                        var size = fare.SizeData ;
                                        if(size === "1"){
                                            $("#TicketnoUsedSizeAlert").text('Ticket No : '+ ticketNo + ' is already been used in ' + fare.SizeData + ' time');
                                            $('#TicketnoUsedSizeModal').modal('show');
                                        }else if(size > "1"){
                                            $("#TicketnoUsedSizeAlert").text('Ticket No : '+ ticketNo + ' is already been used ' + fare.SizeData + ' times');
                                            $('#TicketnoUsedSizeModal').modal('show');
                                        }
                                        
                                        var counter = $('#RefundAirlineTable tbody tr').length / 2;
                                        if (row === counter) {
                                            addRowRefundAirlineList();
                                            $(".decimal").inputmask({
                                                alias: "decimal",
                                                integerDigits: 8,
                                                groupSeparator: ',',
                                                autoGroup: true,
                                                digits: 2,
                                                allowMinus: false,
                                                digitsOptional: false,
                                                placeholder: "0.00"
                                            });
                                        }
                                        inputTicket.style.removeProperty('border');
                                    } else {
                                        $("#alertTextFail").html("Ticket no " + ticketNo + " is not available.");
                                        $("#alertFail").show();
                                        $("#alertSuccess").hide();
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
    $("#alertSuccess").hide();
    $("#alertFail").hide();
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
                        $("#alertFail").hide();
                    } else {
                        var ticketNo = $("#ticketNo" + deleteRowNo).val();
                        $("#alertTextFail").html("TicketNo " + ticketNo + " is already use in payment airline.");
                        $("#alertFail").show();
                        $("#alertSuccess").hide();
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

function checkRefund(e) {
    var refund = e.value;
    var row = $(e).parent().parent().attr("row");
    var issue = $("#sectorIssue" + row).html();
    if (issue.indexOf(refund) >= 0) {
        e.style.borderColor = "Green";
    } else {
        e.style.borderColor = "Red";
        return;
    }
}

function searchCustomerAutoList(name) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getAutoListBillto';

    var url = 'AJAXServlet';
    var billArray = [];
    var billListId = [];
    var billListName = [];
    var billListAddress = [];
    var billid, billname, billaddr;
    $("#refundBy").autocomplete("destroy");
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
                var billJson = JSON.parse(msg);
                var billselect = $("#refundBy").val();
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
                        if ((billselect === billid) || (billselect === billname)) {
                            $("#refundBy").val(billListId[i]);
                            $("#refundByName").val(billListName[i]);
                        }
                    }
                    $("#dataload").addClass("hidden");
                }
                // $("#refundBy").val(billid);
                //$("#refundByName").val(billname);

                $("#refundBy").autocomplete({
                    source: billArray,
                    close: function () {
                        $("#refundBy").trigger("keyup");
                        var billselect = $("#refundBy").val();
                        for (var i = 0; i < billListId.length; i++) {
                            if ((billselect == billListName[i]) || (billselect == billListId[i])) {
                                $("#refundBy").val(billListId[i]);
                                $("#refundByName").val(billListName[i]);
                            }
                        }
                    }
                });

                var billval = $("#refundBy").val();
                for (var i = 0; i < billListId.length; i++) {
                    if (billval == billListName[i]) {
                        $("#refundBy").val(billListId[i]);
                    }
                }
                if (billListId.length == 1) {
                    showflag = 0;
                    $("#refundBy").val(billListId[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $("#refundBy").trigger(event);

            }, error: function (msg) {
                console.log('auto ERROR');
                $("#dataload").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

function searchCustomerAgentList(name) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getListBillto';
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                $('#refundCustTable').dataTable().fnClearTable();
                $('#refundCustTable').dataTable().fnDestroy();
                $("#refundCustTable tbody").empty().append(msg);

                $('#refundCustTable').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bPaginate": true,
                    "bInfo": false,
                    "bLengthChange": false,
                    "iDisplayLength": 10
                });
                $("#ajaxload").addClass("hidden");

            }, error: function (msg) {
                $("#ajaxload").addClass("hidden");
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function setBillValue(billto, billname, address, term, pay) {

    $("#refundBy").val(billto);
    $("#refundByName").val(billname);
    $("#refundCustModal").modal('hide');
}


function calculateProfit(e){
    var row = $(e).parent().parent().attr("row");
    
    var receive = replaceAll(",","",$("#receive"+row).val());
    if (receive == ""){
        receive = 0;
    }
    
    var receivetemp = parseFloat(receive);
    
    var pay = replaceAll(",","",$("#pay"+row).val());
    if (pay == ""){
        pay = 0;
    }
    
    var paytemp = parseFloat(pay);
    
    var profit = receivetemp - paytemp ;
    document.getElementById("profit"+row).value = (profit);
}

function replaceAll(find, replace, str) {
  return str.replace(new RegExp(find, 'g'), replace);
}