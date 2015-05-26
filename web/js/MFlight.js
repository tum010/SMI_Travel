/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchAction(){ 
   
     var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchFlight').submit();            
 

}

function save(){
    var FlightCode = document.getElementById('FlightCode');
    var FlightName =  document.getElementById('FlightName');
    if( FlightCode.value == ''){
        alert('please fill in Flight code');
    }else if( FlightName.value == ''){
        alert('please fill in Flight name');
    }else if(!(CheckCharecter(FlightCode.value) && CheckCharecter(FlightName.value))){
         alert('data contains illegal characters.');
    }else{
        document.getElementById('Flightform').submit();
    }              
}

function addaction(){
    document.getElementById("FlightCode").readOnly = false;
    document.getElementById('FlightCode').value='';
    document.getElementById('FlightName').value='';
    document.getElementById('FlightID').value='';
    document.getElementById('actionIUP').value='add';
}

function EditFlight(id,code,name){
    document.getElementById("FlightCode").readOnly = true;
    document.getElementById('FlightCode').value=code;
    document.getElementById('FlightName').value=name;
    document.getElementById('FlightID').value=id;
    document.getElementById('actionIUP').value='update';
}

function DeleteFlight(id,code){
    var FlightID = document.getElementById('FlightID');
    FlightID.value = id;
     document.getElementById('delCode').innerHTML = "Are you sure to delete code : " + code + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('Flightform').submit();
}

$(document).ready(function () {

    $('#Flightform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            FlightCode: {
                validators: {
                    notEmpty: {
                        message: 'The flight code is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The flight code contains illegal characters.'
                    }
                }
            },
            FlightName: {
                validators: {
                    notEmpty: {
                        message: 'The flight name name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The flight name contains illegal characters.'
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

