$(document).ready(function () {
    // validate
    var $bookForm = $("#BookDetail");
    $bookForm
            .bootstrapValidator({
                container: 'tooltip',
                excluded: [':disabled'],
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    agent_user: {
                        validators: {
                            notEmpty: {
                                message: 'The Agent is required'
                            }
                        }
                    },
                    currency: {
                        validators: {
                            notEmpty: {
                                message: 'The Currency is required'
                            }
                        }
                    },
                    leaderCode: {
                        validators: {
                            notEmpty: {
                                message: 'The Family Leader is required'
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
                    }
                }
            })
            .on('success.field.bv', function (e, data) {
                if (data.bv.isValid()) {
                    data.bv.disableSubmitButtons(false);

                }
            });
    $bookForm.on('mouseover', function () {
        var leadercode = $(this).find('[name="leaderCode"]');
        var isEmpty = leadercode.val() === '';
        $bookForm.bootstrapValidator('enableFieldValidators', 'leaderCode', isEmpty);
        var leaderName = $(this).find('[name="firstname"]');
        var isEmpty = leaderName.val() === '';
        $bookForm.bootstrapValidator('enableFieldValidators', 'firstname', isEmpty);
        var agentName = $(this).find('[name="agent_name"]');
        var isEmpty = agentName.val() === '';
        $bookForm.bootstrapValidator('enableFieldValidators', 'agent_name', isEmpty);
    });
   // set Currency
    $("#currency").val($("#selectedCurrency").val());
    // set Initial name
    $("#initialname option:selected").text($("#get-initial").val());
    // set isPackage
    if ($("#ch_pax").val() === "1") {
        $('#pax').prop('checked', true);
    }
    $("#filtercus").keyup(function (event) {
        if (event.keyCode === 13) {
            FilterCustomerList($("#filtercus").val());
        }
    });



});


// ON KEY INPUT AUTO SELECT  FAMILY LEADER
$(function () {
    //console.log(customer);
    var codeCustomer = [];
    $.each(customer, function (key, value) {
        codeCustomer.push(value.code);
        if ( !(value.lastName in codeCustomer) ){
           codeCustomer.push(value.lastName);
        }
        if ( !(value.firstName in codeCustomer) ){
           codeCustomer.push(value.firstName);
          
        }
    });

    $("#FamilyLeaderCode").autocomplete({
        source: codeCustomer,
        close:function( event, ui ) {
           $("#FamilyLeaderCode").trigger('keyup');
        }
    });
    $("#FamilyLeaderCode").keyup(function () {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var firstname,lastname = this.value;
        $("#leaderId,#initialname,#firstname,#lastName,#address,#tel").val(null);
        $.each(customer, function (key, value) {
            if (value.code.toUpperCase() === code) {
                $("#leaderId").val(value.id);
                $("#initialname").val(value.initial);
                $("#firstname").val(value.firstName);
                $("#lastname").val(value.lastName);
                $("#address").val(value.address);
                $("#tel").val(value.tel);
            }
            if(firstname === value.firstName){
                $("#FamilyLeaderCode").val(value.code);
                $("#leaderId").val(value.id);
                $("#initialname").val(value.initial);
                $("#lastname").val(value.lastName);
                $("#firstname").val(value.firstName);
                $("#address").val(value.address);
                $("#tel").val(value.tel);
                code = $("#FamilyLeaderCode").val().toUpperCase();
            }
            if(lastname === value.lastName){
                $("#FamilyLeaderCode").val(value.code);
                $("#leaderId").val(value.id);
                $("#initialname").val(value.initial);
                $("#lastname").val(value.lastName);
                $("#firstname").val(value.firstName);
                $("#address").val(value.address);
                $("#tel").val(value.tel);
                code = $("#FamilyLeaderCode").val().toUpperCase();
            }
        });
        
    });
});

// AGENT 
$(document).ready(function () {
        var agentCode = [];
        $.each(agent, function (key, value) {
            console.log("agentCount=="+agent.length);
            agentCode.push(value.code);
//            if ( !(value.name in agentCode) ){
               agentCode.push(value.name);
//            }
        });

        $("#agent_user").autocomplete({
            source: agentCode,
            close:function( event, ui ) {
               $("#agent_user").trigger('keyup');
            }
        });
       
        
        $("#agent_user").on('keyup',function(){
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var code = this.value.toUpperCase();
            var name = this.value;
            $("#agent_id,#agent_name,#agent_addr,#agent_tel").val(null);
            $.each(agent, function (key, value) {
                if (value.code.toUpperCase() === code ) {     
                    $("#agent_id").val(value.id);
                    $("#agent_name").val(value.name);
                    $("#agent_addr").val(value.address);
                    $("#agent_tel").val(value.tel);
//                    $("#agent_user").val(value.code);
                }
//                if(name === value.name){
//                    $("#agent_user").val(value.code);
//                    $("#agent_id").val(value.id);
//                    $("#agent_name").val(value.name);
//                    $("#agent_addr").val(value.address);
//                    $("#agent_tel").val(value.tel);
//                }
            }); 
            
        }); 
       
        
    $("#AgentTable tr").on('click', function () {
        var agent_id = $(this).find(".agent-id").text();
        var agent_user = $(this).find(".agent-user").text();
        var agent_name = $(this).find(".agent-name").text();
        var agent_addr = $(this).find(".agent-addr").text();
        var agent_tel = $(this).find(".agent-tel").text();
        $("#agent_id").val(agent_id);
        $("#agent_user").val(agent_user);
        $("#agent_name").val(agent_name);
        $("#agent_addr").val(agent_addr);
        $("#agent_tel").val(agent_tel);
        $("#AgentModal").modal('hide');
    });
    // AGENT TABLE
    $('#AgentTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    $('#AgentTable tbody').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
});

//FamilyLeader
$(document).ready(function () {

    $('#FamilyLeaderTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    $('#FamilyLeaderTable tbody').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
});

// ADD FAMILYLEADER
function add() {
    $("#mb2, #mb1").toggle('fast');
}
function closeReturn() {
    var mb1 = $('#mb1');
    var mb2 = $('#mb2');
    if(mb2.is(":visible")){
        mb2.hide();
        mb1.show();
    }
}
$(document).ready(function () {
    $("#FormAddFamily").bootstrapValidator({}) ;
    $("#FormAddFamily").submit(function (event) {
        event.preventDefault();
        var servletName = 'BookDetailServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&initialID=' + $("#input-initial-name").val() +
                '&first=' + $("#input-first-name").val() +
                '&last=' + $("#input-last-name").val() +
                '&address=' + $("#input-address").val() +
                '&tel=' + $("#input-tel").val() +
                '&type=' + 'saveCustomer';
        CallAjax(param);
    });
});

function saveCus(){
    $("#FormAddFamily").submit();
}

$(document).ready(function () {
    $(document).ajaxComplete(function () {
        //alert("complte");
        $('#FamilyLeaderAjaxTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
            }
            else {
                $(this).addClass('row_selected');
            }
            var customerId = $(this).find(".customerId").text();
            var customerCode = $(this).find(".customerCode").text();
            var customerInitialname = $(this).find(".customerInitialname").text();
            var customerFirstname = $(this).find(".customerFirstname").text();
            var customerLastname = $(this).find(".customerLastname").text();
            var customerAddress = $(this).find(".customerAddress").text();
            var customerTel = $(this).find(".customerTel").text();
            $("#leaderId").val(customerId);
            $("#FamilyLeaderCode").val(customerCode);
            //$("#initialname option:selected").text(customerInitialname);
            $("#initialname").val(customerInitialname);
            $("#firstname").val(customerFirstname);
            $("#lastname").val(customerLastname);
            $("#address").val(customerAddress);
            $("#tel").val(customerTel);
            $("#FamilyLeaderAjaxModal").modal('hide');
        });
    });
});


function FilterCustomerList(name) {
    // var name = 
    var servletName = 'BookDetailServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getCustomerList';
    CallFilterAjax(param);
}

function CallFilterAjax(param) {
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                try {
                    $('#FamilyLeaderTable').dataTable().fnClearTable();
                    $('#FamilyLeaderTable').dataTable().fnDestroy();
                    $("#FamilyLeaderTable tbody").empty().append(msg);
                    $('#FamilyLeaderTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });
                     $("#ajaxload").addClass("hidden");
                } catch (e) {
                    alert(e);
                }

            }, error: function (msg) {
                 $("#ajaxload").addClass("hidden");
                alert(msg);
            }
        });
    } catch (e) {
        alert(e);
    }


}

function setCustomerDetail(customerId, customerCode, customerInitialname, customerFirstname, customerLastname, customerAddress, customerTel) {
    $("#leaderId").val(customerId);
    $("#FamilyLeaderCode").val(customerCode);
    $("#initialname").val(customerInitialname);
    $("#firstname").val(customerFirstname);
    $("#lastname").val(customerLastname);
    if (customerAddress == 'null') {
        $("#address").val('');
    } else {
        $("#address").val(customerAddress);
    }
    if (customerTel == 'null') {
        $("#tel").val('');
    } else {
        $("#tel").val(customerTel);
    }
    $("#FamilyLeaderModal").modal('hide');


}



function checkExistCustomer() {
    var servletName = 'BookDetailServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&initialID=' + '1' +
            '&first=' + 'PUANGSEREE' +
            '&last=' + 'NUTCHAKRIT' +
            '&type=' + 'checkExistCustomer';
    CallAjax(param);
}

function getCustomerList(first, last) {
    var servletName = 'BookDetailServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&initialID=' + '1' +
            '&first=' + first +
            '&last=' + last +
            '&type=' + 'getCustomerList';
    //alert(param);
    CallAjax(param);
    //$("#FamilyLeaderAjaxTable tbody").empty().append(msg);
    //$("#FamilyLeaderAjaxModal").modal();
}


function saveCustomer() {
    var servletName = 'BookDetailServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&initialID=' + '1' +
            '&first=' + 'oUANGSEREE' +
            '&last=' + 'NUTCHAKRIT' +
            '&address=' + 'test' +
            '&tel=' + '021324564' +
            '&type=' + 'saveCustomer';
    CallAjax(param);
}

function CallAjax(param) {
    var url = 'AJAXServlet';
    
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                console.log(msg);
                var str = msg.split("|");
                var id = str[0];
                var code = str[1];
                var initia = str[2];
                var fname = str[3];
                var lname = str[4];
                var address = str[5];
                var tel = str[6];
                $("#leaderId").val(id);
                $("#FamilyLeaderCode").val(code);
                $("#initialname").val(initia);
                $("#firstname").val(fname);
                $("#lastname").val(lname);
                $("#address").val(address);
                $("#tel").val(tel);
                alert("Save OK");
                
                $("#FamilyLeaderModal").modal('hide');
                add();

                //$("#FamilyLeaderAjaxTable tbody").empty().append(msg);
                //$("#FamilyLeaderAjaxModal").modal();
            }, error: function (msg) {
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }


}
