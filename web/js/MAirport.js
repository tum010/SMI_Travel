/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchAction(){ 
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchAirport').submit();     
}

function save(){
    var AirportCode = document.getElementById('AirportCode');
    var AirportName =  document.getElementById('AirportName');
    if( AirportCode.value == ''){
        alert('please fill in airport code');
    }else if( AirportName.value == ''){
        alert('please fill in airport name');
    }else if(!(CheckCharecter(AirportCode.value) && CheckCharecter(AirportName.value))){
         alert('data contains illegal characters.');
    }else{
        document.getElementById('Airportform').submit();
    }       
}

function EditAirport(id,code,name){
    document.getElementById('AirportCode').value=code;
    document.getElementById('AirportName').value=name;
    document.getElementById('AirportID').value=id;
    document.getElementById('actionIUP').value='update';
    document.getElementById("AirportCode").readOnly = true;
}

function DeleteAirport(id,code){
    var AirportID = document.getElementById('AirportID');
    AirportID.value = id;
     document.getElementById('delCode').innerHTML = "Are you sure to delete code : " + code + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('Airportform').submit();
}

function addaction(){
    document.getElementById("AirportCode").readOnly = false;
    document.getElementById('AirportCode').value = '';
    document.getElementById('AirportName').value = '';
    document.getElementById('AirportID').value = '';
    document.getElementById('actionIUP').value='add';
}

$(document).ready(function () {

    $('#Airportform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            AirportCode: {
                validators: {
                    notEmpty: {
                        message: 'The airport code is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The airport code contains illegal characters.'
                    }
                }
            },
            AirportName: {
                validators: {
                    notEmpty: {
                        message: 'The airport name name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The airport name contains illegal characters.'
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



