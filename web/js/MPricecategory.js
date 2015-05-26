/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchAction(){ 
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchPricecategory').submit();     
}

function save(){
    var PricecategoryCode = document.getElementById('PricecategoryCode');
    var PricecategoryName =  document.getElementById('PricecategoryName');
    if( PricecategoryCode.value == ''){
        alert('please fill in price code');
    }else if( PricecategoryName.value == ''){
        alert('please fill in price name');
    }else if(!(CheckCharecter(PricecategoryCode.value) && CheckCharecter(PricecategoryName.value))){
         alert('data contains illegal characters.');
    }else{
        document.getElementById('Pricecategoryform').submit();
    }       
}

function EditPricecategory(id,code,name){
    document.getElementById('PricecategoryCode').value=code;
    document.getElementById('PricecategoryName').value=name;
    document.getElementById('PricecategoryID').value=id;
    document.getElementById('actionIUP').value='update';
    document.getElementById("PricecategoryCode").readOnly = true;
}

function DeletePricecategory(id,code){
    var PricecategoryID = document.getElementById('PricecategoryID');
    PricecategoryID.value = id;
     document.getElementById('delCode').innerHTML = "Are you sure to delete code : " + code + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('Pricecategoryform').submit();
}

function addaction(){
    document.getElementById("PricecategoryCode").readOnly = false;
    document.getElementById('PricecategoryCode').value = '';
    document.getElementById('PricecategoryName').value = '';
    document.getElementById('PricecategoryID').value = '';
    document.getElementById('actionIUP').value='add';
}

$(document).ready(function () {

    $('#Pricecategoryform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            PricecategoryCode: {
                validators: {
                    notEmpty: {
                        message: 'The category code is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The category code contains illegal characters.'
                    }
                }
            },
            PricecategoryName: {
                validators: {
                    notEmpty: {
                        message: 'The category name name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The category name contains illegal characters.'
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

