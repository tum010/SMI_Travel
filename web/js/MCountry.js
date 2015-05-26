/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchAction(){ 
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchCountry').submit();     
}

function save(){
    var CountryCode = document.getElementById('CountryCode');
    var CountryName =  document.getElementById('CountryName');
    if( CountryCode.value == ''){
        alert('please fill in Country code');
    }else if( CountryName.value == ''){
        alert('please fill in Country name');
    }else if(!(CheckCharecter(CountryCode.value) && CheckCharecter(CountryName.value))){
         alert('data contains illegal characters.');
    }else{
        document.getElementById('Countryform').submit();
    }              
}

function addaction(){
    document.getElementById("CountryCode").readOnly = false;
    document.getElementById('CountryCode').value='';
    document.getElementById('CountryName').value='';
    document.getElementById('CountryID').value='';
    document.getElementById('actionIUP').value='add';
}

function EditCountry(id,code,name){
    document.getElementById('CountryCode').value=code;
    document.getElementById('CountryName').value=name;
    document.getElementById('CountryID').value=id;
    document.getElementById('actionIUP').value='update';
    document.getElementById("CountryCode").readOnly = true;
}

function DeleteCountry(id,code){
    var CountryID = document.getElementById('CountryID');
    CountryID.value = id;
     document.getElementById('delCode').innerHTML = "Are you sure to delete code : " + code + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('Countryform').submit();
}

$(document).ready(function () {

    $('#Countryform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            CountryCode: {
                validators: {
                    notEmpty: {
                        message: 'The country code is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The country code contains illegal characters.'
                    }
                }
            },
            CountryName: {
                validators: {
                    notEmpty: {
                        message: 'The country name name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The country name contains illegal characters.'
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

