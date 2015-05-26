/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    $('#HotelForm').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            hotelcode: {
                validators: {
                    notEmpty: {
                        message: 'The hotel code is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The hotel code contains illegal characters.'
                    }
                }
            },
            hotelname: {
                validators: {
                    notEmpty: {
                        message: 'The hotel name name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The hotel name contains illegal characters.'
                    }
                }
            },
            Email: {
                validators: {
                    regexp: {
                        regexp: "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$",
                        message: 'The value is not a valid email address'
                    }
                    
                }
            },
            Web: {
                validators: {
                    regexp: {
                        regexp: "^[a-zA-Z0-9\-\.]+\.(com|org|net|mil|edu|COM|ORG|NET|MIL|EDU)$",
                        message: 'The website address is not valid'
                    }
                }
            },
            Telno: {
                validators: {
                    regexp: {
                        regexp: "^[0-9()+,/#\-]+$",
                        message: 'The tel is not valid'
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

