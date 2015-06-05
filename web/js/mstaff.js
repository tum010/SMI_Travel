

$(document).ready(function () {
//    $("#addStaff").appendTo("#MasterOthers_filter").show();


    /* remove border around all input elements */
    if (navigator.userAgent.toLowerCase().indexOf("chrome") >= 0) {
        $(window).load(function () {
            $('input:-webkit-autofill').each(function () {
                var text = $(this).val();
                var id = $(this).attr('id');
                $(this).after(this.outerHTML).remove();
                $('input[id=' + id + ']').val(text);
            });
        });
    }


//$('#StaffModal').modal('show')  ;
    $("body").mousemove(function () {
        $("#UserName").attr("autocomplete", "off");
//    setTimeout('$("#UserName").val("")', 1);
        $("#Password").attr("autocomplete", "off");
//    setTimeout('$("#Password").val("")', 1);

    });

    $("#UserName").one('click', function () {
        password = $("#Password").val();
        console.log(password);
        $("#Password").val(password);
        $("#Password").one('mouseover', function () {
            console.log(password);
            $("#Password").val(password);
        });
    });




    $('#Staffform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            StaffName: {
                validators: {
                    notEmpty: {
                        message: 'The Name is required'
                    }
                }
            },
            UserName: {
                validators: {
                    notEmpty: {
                        message: 'The Username is required'
                    }
                }
            },
            Password: {
                validators: {
                    notEmpty: {
                        message: 'The Password is required'
                    }
                }
            },
            Position: {
                validators: {
                    notEmpty: {
                        message: 'The Position is required'
                    }
                }
            },
            Role: {
                validators: {
                    notEmpty: {
                        message: 'Role is required'
                    }
                }
            },
            Status: {
                validators: {
                    notEmpty: {
                        message: 'The Status is required'
                    }
                }
            }

        }
    }).on('success.field.bv', function (e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);
        }
    });
    var vform = $("#Staffform");
    vform.on('mouseover', function () {
        var leadercode = $(this).find('[name="Password"]');
        var isEmpty = leadercode.val() == ''; // true
        vform.bootstrapValidator('enableFieldValidators', 'Password', isEmpty);
       if (leadercode.val().length >= 1) {
            vform.bootstrapValidator('validateField', 'Password');
        }
    });


});

function searchAction() {
    $("#Action").val('search');
    $("#SearchStaff").submit();
}

function EditStaff(id, name, username, password, position, tel, car, departmentId, status, createBy, roleId) {
    $('#Staffform').bootstrapValidator('resetForm', true);
    $("#StaffName").val(name);
    $("#UserName").val(username);
    $("#Password").val(password);
    $("#Position").val(position);
    $("#Tel").val(tel);
    $("#Car").val(car);
    $("#Department").val(departmentId);
    console.log(status + " roldId = " + roleId);
    var $radios = $('input:radio[name=Status]');
    $radios.filter('[value=' + status + ']').prop('checked', true);
    $("#createBy").val(createBy);
    $("#StaffID").val(id);
    $("#actionIUP").val('update');
    $("#Role").val(roleId);



}

function AddStaffDefault() {
    var status = 'U';
    //set default value to status -> enable.
    var $radios = $('input:radio[name=Status]');
    $radios.filter('[value=' + status + ']').prop('checked', true);

}






