/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchAction(){ 
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchCity').submit();     
}

function save(){
    var CityCode = document.getElementById('CityCode');
    var CityName =  document.getElementById('CityName');
    if( CityCode.value == ''){
        alert('please fill in city code');
    }else if( CityName.value == ''){
        alert('please fill in city name');
    }else if(!(CheckCharecter(CityCode.value) && CheckCharecter(CityName.value))){
         alert('data contains illegal characters.');
    }else{
        document.getElementById('Cityform').submit();
    }       
}

function EditCity(id,code,name,country){
    document.getElementById('CityCode').value=code;
    document.getElementById('CityName').value=name;
    document.getElementById('CityID').value=id;
    document.getElementById('actionIUP').value='update';
    var $select = $('#SelectCountry').selectize();
    $select[0].selectize.setValue(country);
    document.getElementById("CityCode").readOnly = true;
}

function DeleteCity(id,code){
    var CityID = document.getElementById('CityID');
    CityID.value = id;
     document.getElementById('delCode').innerHTML = "Are you sure to delete code : " + code + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('Cityform').submit();
}

function addaction(){
    document.getElementById("CityCode").readOnly = false;
    document.getElementById('CityCode').value = '';
    document.getElementById('CityName').value = '';
    document.getElementById('CityID').value = '';
    document.getElementById('actionIUP').value='add';
    var $select = $('#SelectCountry').selectize();
    $select[0].selectize.setValue('');
}

$(document).ready(function () {
    
    $('#Cityform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            CityCode: {
                validators: {
                    notEmpty: {
                        message: 'The city code is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The city code contains illegal characters.'
                    }
                }
            },
            CityName: {
                validators: {
                    notEmpty: {
                        message: 'The city name name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The city name contains illegal characters.'
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


