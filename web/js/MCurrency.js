/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchAction(){ 
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchCurrency').submit();     
}

function save(){
    var CurrencyCode = document.getElementById('CurrencyCode');
    var CurrencyName =  document.getElementById('CurrencyName');
    if( CurrencyCode.value == ''){
        alert('please fill in Currency code');
    }else if( CurrencyName.value == ''){
        alert('please fill in Currency description');
    }else if(!(CheckCharecter(CurrencyCode.value) && CheckCharecter(CurrencyName.value))){
         alert('data contains illegal characters.');
    }else{
        document.getElementById('Currencyform').submit();
    }              
}

function addaction(){
    document.getElementById("CurrencyCode").readOnly = false;
    document.getElementById('CurrencyCode').value='';
    document.getElementById('CurrencyName').value='';
    document.getElementById('CurrencyID').value='';
    document.getElementById('actionIUP').value='add';
}

function EditCurrency(id,code,name){
    document.getElementById('CurrencyCode').value=code;
    document.getElementById('CurrencyName').value=name;
    document.getElementById('CurrencyID').value=id;
    document.getElementById('actionIUP').value='update';
    document.getElementById("CurrencyCode").readOnly = true;
}

function DeleteCurrency(id,code){
    var CurrencyID = document.getElementById('CurrencyID');
    CurrencyID.value = id;
     document.getElementById('delCode').innerHTML = "Are you sure to delete code : " + code + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('Currencyform').submit();
}

$(document).ready(function () {

    $('#Currencyform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            CurrencyCode: {
                validators: {
                    notEmpty: {
                        message: 'The currency code is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The currency code contains illegal characters.'
                    }
                }
            },
            CurrencyName: {
                validators: {
                    notEmpty: {
                        message: 'The currency name name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The currency name contains illegal characters.'
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

