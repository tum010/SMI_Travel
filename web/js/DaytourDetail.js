$(document).ready(function () {
    // validate

    $('#InputDatePicker').datetimepicker({
        }).on('change', function(e) {
            $('#DaytourDetailForm').bootstrapValidator('revalidateField', 'InputTourDate');
    });

    $("#DaytourDetailForm")
            .bootstrapValidator({
//                framework: 'bootstrap',
                container: 'tooltip',
                excluded: [':disabled', ':hidden', ':not(:visible)'],
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    SelectPickUp: {
                         excluded: 'false',
                        validators: {
                            notEmpty: {
                                message: ' Pick Up is required'
                            }
                        }
                    },
                    InputTourId: {
                        validators: {
                            notEmpty: {
                                message: ' Tour name is required'
                            }
                        }
                    },
                    InputTourCode: {
                        trigger: 'focus keyup',
                        validators: {
                            notEmpty: {trigger: 'change',
                                message: ' Tour Code is required'
                            }
                        }
                    },
                    InputTourName: {
                        validators: {
                            notEmpty: {
                                message: ' Tour name is required'
                            }
                        }
                    },
                    InputTourDate: {
                        validators: {
                            notEmpty: {
                                message: ' Date From is required'
                            }
                        }
                    }
          
                }
            })
            .on('success.field.fv', function (e, data) {
                if (data.field === 'InputTourDate' && data.fv.isValidField('InputTourDate') === false) {
                    data.fv.revalidateField('InputTourDate');
                }
                if (data.field === 'InputTourCode' && data.fv.isValidField('InputTourCode') === false) {
                    data.fv.revalidateField('InputTourCode');
                }
            });
            

});