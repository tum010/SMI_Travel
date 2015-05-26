$(document).ready(function () {

    $('#Galileoform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            Section: {
                stringLength: {
                    min: 2,
                    max: 3,
                    message: 'Section can be AXX or AX'
                },
                regexp: {
                    regexp: /^[A][0-9][0-9]$/,
                    message: 'The username can only consist of alphabetical, number and underscore'
                }
            },
            Line: {
                validators: {
                    notEmpty: {
                        message: 'Line number is required! Otherwise \'0\''
                    },
                    digits: {
                        message: 'Line must be digit only!'
                    }
                }
            },
            Length: {
                validators: {
                    notEmpty: {
                        message: 'Length number is required!'
                    },
                    digits: {
                        message: 'Length number is digit only!'
                    }
                }
            },
            StartLength: {
                validators: {
                    notEmpty: {
                        message: 'Start length is required!'
                    },
                    digits: {
                        message: 'Start Length must be number!'
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

function EditGalileo(id, name, section, line, length, startlength) {
    $('#Galileoform').bootstrapValidator('resetForm', true);
    $("#NameDisplay").text(name);
    $("#Name").val(name);
    $("#Section").val(section);
    $("#Line").val(line);
    $("#Length").val(length);
    $("#StartLength").val(startlength);
    $("#GID").val(id);
    $("#actionIUP").val('update');



}








