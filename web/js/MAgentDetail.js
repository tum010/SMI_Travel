/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var validateagent = /^[a-z0-9 ()./,/-]+$/i;
$(document).ready(function () {

    $('#AgentForm').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            agentname: {
                validators: {
                    notEmpty: {
                        message: 'The agent name name is required'
                    }
                }
            },
            Email: {
                validators: {
                    regexp: {
                        regexp: /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
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
            Tel: {
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

