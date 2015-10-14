/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    //Select City
    Selectize.define('clear_selection', function(options) {
        var self = this;
        self.plugins.settings.dropdown_header = {
            title: 'Clear Selection'
        };
        this.require('dropdown_header');
        self.setup = (function() {
            var original = self.setup;
            return function() {
                original.apply(this, arguments);
                this.$dropdown.on('mousedown', '.selectize-dropdown-header', function(e) {
                    self.setValue('');
                    self.close();
                    self.blur();
                    return false;
                });
            };
        })();
    });

    var dataCity = [];
    dataCity = cityName;
    $("#select-list-city option").clone().appendTo("#city");

    var nameCity = "#city";
    console.log("name = " + nameCity);

    $(nameCity).selectize({
        removeItem: '',
        sortField: 'text',
        create: false,
        dropdownParent: 'body',
        plugins: {
            'clear_selection': {}
        }

    });
    
    //Select Country
    var dataCountry = [];
    dataCountry = cityCountry;
    $("#select-list-country option").clone().appendTo("#country");

    var nameCountry = "#country";
    console.log("name = " + nameCountry);
    
    $(nameCountry).selectize({
        removeItem: '',
        sortField: 'text',
        create: false,
        dropdownParent: 'body',
        plugins: {
            'clear_selection': {}
        }
    });
    $(".selectize-input").width(210);

    $('#HotelForm').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
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
    }).on('success.field.bv', function(e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);
        }
    });


});

