/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function searchAction() {
   
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchAirLine').submit();

}

function save() {
    var AirCode = document.getElementById('AirCode');
    var AirName = document.getElementById('AirName');
    var Code3  = document.getElementById('Code3');
    if (AirCode.value == '') {
        alert('please fill in airline code');
    } else if (AirName.value == '') {
        alert('please fill in airline name');
    } else if(!(CheckCharecter(AirCode.value) && CheckCharecter(AirName.value) && CheckCharecter(Code3.value))){
         alert('data contains illegal characters.');
    }else {
        document.getElementById('Aitlineform').submit();
    }
}

function EditAir(id, code, name, code3) {
    document.getElementById('AirCode').value = code;
    document.getElementById('AirName').value = name;
    document.getElementById('Code3').value = code3;
    document.getElementById('AirID').value = id;
    document.getElementById('actionIUP').value = 'update';
    document.getElementById("AirCode").readOnly = true;
}

function DeleteAir(id, code) {
    var AirID = document.getElementById('AirID');
    AirID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete code : " + code + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('Aitlineform').submit();
}

function addaction(){
    document.getElementById("AirCode").readOnly = false;
    document.getElementById('AirCode').value = '';
    document.getElementById('AirName').value = '';
    document.getElementById('AirID').value = '';
    document.getElementById('actionIUP').value='add';
}

$(document).ready(function () {

    $('#Aitlineform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            AirCode: {
                validators: {
                    notEmpty: {
                        message: 'The airline code is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The airline code contains illegal characters.'
                    }
                }
            },
            AirName: {
                validators: {
                    notEmpty: {
                        message: 'The airline name name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The airline name contains illegal characters.'
                    }
                }
            },
            Code3: {
                validators: {
                    regexp: {
                        regexp: validatcode,
                        message: 'The Code3letter  contains illegal characters.'
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

