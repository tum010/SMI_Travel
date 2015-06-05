$(document).ready(function () {

    $('#Amadeusform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            NodLm: {
                validators: {
                    notEmpty: {
                        message: 'NodLm number is required, if unsure, use 0'
                    },
                    digits: {
                        message: 'Digit only.!'
                    }
                }
            },
            Length: {
                validators: {
                    notEmpty: {
                        message: 'NodLm number is required'
                    },
                    digits: {
                        message: 'Length number is required, if unsure, use 0'
                    }
                }
            },
            StartLength: {
                validators: {
                    notEmpty: {
                        message: 'startlength number is required, if unsure, use 0'
                    },
                    digits: {
                        message: 'Numbers only.'
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
function Edit(id, name, section, nodlm, length, startlength) {
    $('#Amadeusform').bootstrapValidator('resetForm', true);
    $("#Name").val(name);
    $("#NameDisplay").text(name);
    $("#Section").val(section);
    $("#NodLm").val(nodlm);
    $("#Length").val(length);
    $("#StartLength").val(startlength);
    $("#GID").val(id);
    $("#actionIUP").val('update');
}








