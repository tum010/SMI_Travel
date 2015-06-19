$(document).ready(function () {
    // DATATIMEPICKER
    $('.date').datetimepicker();
    $('.spandate').click(function () {
        var position = $(this).offset();
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 32);
    });
    $('body').on('click mousemove', function () {
        //getDate();
        var birthDate = new Date($('#birthDate').val());
        var age = _calculateAge(birthDate);
        $("#age").val(age);
    });
    // VALIDATE
    $('#PassengerForm')
            .bootstrapValidator({
                container: 'tooltip',
                excluded: [':disabled'],
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    MInitialname: {
                        validators: {
                            notEmpty: {
                                message: 'The Initialname is required'
                            }
                        }
                    },
                    firstName: {
                        validators: {
                            notEmpty: {
                                message: 'The FirstName is required'
                            }
                        }
                    },
                    lastName: {
                        validators: {
                            notEmpty: {
                                message: 'The lastName is required'
                            }
                        }
                    },
                    birthDate: {
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                message: 'The value is not a valid date'
                            }
                        }
                    },
                    postalCode: {
                        validators: {
                            digits: {
                                message: 'The Postal Code is Number'
                            }
                        }
                    },
                    email: {
                        validators: {
                            emailAddress: {
                                message: 'The value is not a valid email address'
                            },
                            regexp: {
                                regexp: '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
                                message: 'The value is not a valid email address'
                            }
                        }
                    }

                }
            }).on('success.field.bv', function (e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);
        }
    });
});

$(document).ready(function () {
    console.log(customer);
    var codeCustomer = [];
    $.each(customer, function (key, value) {
        console.log('customer=='+customer.length);
        codeCustomer.push(value.code);
        if ( !(value.firstname in codeCustomer) ){
           codeCustomer.push(value.firstname);
        }
        if ( !(value.lastname in codeCustomer) ){
           codeCustomer.push(value.lastname);
        }
    });
    $("#passengerId").autocomplete({
        source: codeCustomer,
        close:function( event, ui ) {
           $("#passengerId").trigger('keyup');
        }
    });
    $("#passengerId").on('keyup', function () {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var firstname = this.value;
        var lastname = this.value;
        $("#id,#MInitialname,#firstName,#lastName,#sex,#address,#tel,#phone,#email,#remark,#Passport,#firstNameJapan,#lastNameJapan").val(null);
        $.each(customer, function (key, value) {
            if (value.code.toUpperCase() === code) {
                console.log('ok');
                $("#MInitialname").val(value.initialId);
                $("#firstName").val(value.firstname);
                $("#lastName").val(value.lastname);
                $("#sex").val(value.sex);
                $("#address").val(value.address);
                $("#tel").val(value.tel);
                $("#phone").val(value.phone);
                $("#email").val(value.email);
                $("#remark").val(value.remark);
                $("#Passport").val(value.passportNo);
                $("#firstNameJapan").val(value.firstNameJapan);
                $("#lastNameJapan").val(value.lastNameJapan);
            }
            if(firstname === value.firstname){
                $("#passengerId").val(value.code);
                $("#MInitialname").val(value.initialId);
                $("#firstName").val(value.firstname);
                $("#lastName").val(value.lastname);
                $("#sex").val(value.sex);
                $("#address").val(value.address);
                $("#tel").val(value.tel);
                $("#phone").val(value.phone);
                $("#email").val(value.email);
                $("#remark").val(value.remark);
                $("#Passport").val(value.passportNo);
                $("#firstNameJapan").val(value.firstNameJapan);
                $("#lastNameJapan").val(value.lastNameJapan);
                code = $("#passengerId").val().toUpperCase();
            }
            if(lastname === value.lastname){
                $("#passengerId").val(value.code);
                $("#MInitialname").val(value.initialId);
                $("#firstName").val(value.firstname);
                $("#lastName").val(value.lastname);
                $("#sex").val(value.sex);
                $("#address").val(value.address);
                $("#tel").val(value.tel);
                $("#phone").val(value.phone);
                $("#email").val(value.email);
                $("#remark").val(value.remark);
                $("#Passport").val(value.passportNo);
                $("#firstNameJapan").val(value.firstNameJapan);
                $("#lastNameJapan").val(value.lastNameJapan);
                code = $("#passengerId").val().toUpperCase();
            }
        });
    });
    $("#CustomerTable tr").on('click', function () {
        var customer_id = $(this).find(".customer-id").text();
        var customer_code = $(this).find(".customer-code").text();
        var customer_initial = $(this).find(".customer-initial").text();
        var customer_initialId = $(this).find(".customer-initialId").text();
        var customer_firstname = $(this).find(".customer-firstname").text();
        var customer_lastname = $(this).find(".customer-lastname").text();
        var customer_sex = $(this).find(".customer-sex").text();
        var customer_address = $(this).find(".customer-address").text();
        var customer_tel = $(this).find(".customer-tel").text();
        var customer_phone = $(this).find(".customer-phone").text();
        var customer_postal = $(this).find(".customer-postal").text();
        var customer_email = $(this).find(".customer-email").text();
        var customer_remark = $(this).find(".customer-remark").text();
        var customer_passportno = $(this).find(".customer-passportno").text();
        var customer_japanfirstname = $(this).find(".customer-japanfirstname").text();
        var customer_japanlastname = $(this).find(".customer-japanlastname").text();
        $("#customerId").val(customer_id);
        $("#MInitialname").val(customer_initialId);
        $("#passengerId").val(customer_code);
        $("#firstName").val(customer_firstname);
        $("#lastName").val(customer_lastname);
        $("#sex").val(customer_sex);
        $("#address").val(customer_address);
        $("#tel").val(customer_tel);
        $("#phone").val(customer_phone);
        $("#email").val(customer_email);
        $("#remark").val(customer_remark);
        $("#Passport").val(customer_passportno);
        $("#CustomerModal").modal('hide');
    });
    // PASSENGER TABLE
    $('#CustomerTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
    });
    $('#CustomerTable tbody').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
    
     $("#filtercus").keyup(function (event) {
        if (event.keyCode === 13) {
            FilterCustomerList($("#filtercus").val());
        }
    });
});

function _calculateAge(birthday) {
    var ageDifMs = Date.now() - birthday.getTime();
    var ageDate = new Date(ageDifMs);
    return Math.abs(ageDate.getUTCFullYear() - 1970);
}

function FilterCustomerList(name) {
    // var name = 
    var servletName = 'PassengerServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'searchPassenger';
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
                   
                    $('#CustomerTable').dataTable().fnClearTable();
                    $('#CustomerTable').dataTable().fnDestroy();
                    $("#CustomerTable tbody").empty().append(msg);
                    $("#CustomerTable tr").on('click', function () {
                    var customer_id = $(this).find(".customer-id").text();
                    var customer_code = $(this).find(".customer-code").text();
                    var customer_initial = $(this).find(".customer-initial").text();
                    var customer_initialId = $(this).find(".customer-initialId").text();
                    var customer_firstname = $(this).find(".customer-firstname").text();
        var customer_lastname = $(this).find(".customer-lastname").text();
        var customer_sex = $(this).find(".customer-sex").text();
        var customer_address = $(this).find(".customer-address").text();
        var customer_tel = $(this).find(".customer-tel").text();
        var customer_phone = $(this).find(".customer-phone").text();
        var customer_postal = $(this).find(".customer-postal").text();
        var customer_email = $(this).find(".customer-email").text();
        var customer_remark = $(this).find(".customer-remark").text();
        var customer_passportno = $(this).find(".customer-passportno").text();
        var customer_japanfirstname = $(this).find(".customer-japanfirstname").text();
        var customer_japanlastname = $(this).find(".customer-japanlastname").text();
        $("#customerId").val(customer_id);
        $("#MInitialname").val(customer_initialId);
        $("#passengerId").val(customer_code);
        $("#firstName").val(customer_firstname);
        $("#lastName").val(customer_lastname);
        $("#sex").val(customer_sex);
        $("#address").val(customer_address);
        $("#tel").val(customer_tel);
        $("#phone").val(customer_phone);
        $("#email").val(customer_email);
        $("#remark").val(customer_remark);
        $("#Passport").val(customer_passportno);
        $("#CustomerModal").modal('hide');
    });
                    $('#CustomerTable').dataTable({bJQueryUI: true,
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