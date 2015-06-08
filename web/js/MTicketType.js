/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchAction(){ 
   
     var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchTicketType').submit();            
 

}

function save(){
    var TicketTypeCode = document.getElementById('TicketTypeCode');
    var TicketTypeName =  document.getElementById('TicketTypeName');
    if( TicketTypeCode.value == ''){
        alert('please fill in TicketType code');
    }else if( TicketTypeName.value == ''){
        alert('please fill in TicketType name');
    }else if(!(CheckCharecter(TicketTypeCode.value) && CheckCharecter(TicketTypeName.value))){
         alert('data contains illegal characters.');
    }else{
        document.getElementById('TicketTypeform').submit();
    }              
}

function addaction(){
    document.getElementById("TicketTypeCode").readOnly = false;
    document.getElementById('TicketTypeCode').value='';
    document.getElementById('TicketTypeName').value='';
    document.getElementById('TicketTypeID').value='';
    document.getElementById('actionIUP').value='add';
}

function EditTicketType(id,code,name){
    document.getElementById("TicketTypeCode").readOnly = true;
    document.getElementById('TicketTypeCode').value=code;
    document.getElementById('TicketTypeName').value=name;
    document.getElementById('TicketTypeID').value=id;
    document.getElementById('actionIUP').value='update';
}

function DeleteTicketType(id,code){
    var TicketTypeID = document.getElementById('TicketTypeID');
    TicketTypeID.value = id;
     document.getElementById('delCode').innerHTML = "Are you sure to delete code : " + code + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('TicketTypeform').submit();
}

$(document).ready(function () {

    $('#TicketTypeform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            TicketTypeCode: {
                validators: {
                    notEmpty: {
                        message: 'The ticket type code is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The ticket type code contains illegal characters.'
                    }
                }
            },
            TicketTypeName: {
                validators: {
                    notEmpty: {
                        message: 'The ticket type name name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The ticket type name contains illegal characters.'
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
