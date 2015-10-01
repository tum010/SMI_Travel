function searchAction(){ 
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchHost').submit();     
}

function save(){
    var HostName = document.getElementById('HostName');
    var HostStatus =  document.getElementById('HostStatus');
    if( HostName.value == ''){
        alert('please fill in Host Name ');
    }else if( HostStatus.value == ''){
        alert('please fill in Host Status');
    }else if(!(CheckCharecter(HostName.value) && CheckCharecter(HostStatus.value))){
         alert('data contains illegal characters.');
    }else{
        document.getElementById('Hostform').submit();
    }              
}

//function addaction(){
//    document.getElementById("CurrencyCode").readOnly = false;
//    document.getElementById('CurrencyCode').value='';
//    document.getElementById('CurrencyName').value='';
//    document.getElementById('CurrencyID').value='';
//    document.getElementById('actionIUP').value='add';
//}
//
//function EditCurrency(id,code,name){
//    document.getElementById('CurrencyCode').value=code;
//    document.getElementById('CurrencyName').value=name;
//    document.getElementById('CurrencyID').value=id;
//    document.getElementById('actionIUP').value='update';
//    document.getElementById("CurrencyCode").readOnly = true;
//}
//
//function DeleteCurrency(id,code){
//    var CurrencyID = document.getElementById('CurrencyID');
//    CurrencyID.value = id;
//     document.getElementById('delCode').innerHTML = "Are you sure to delete code : " + code + " ?";
//}
//
//function Delete() {
//    var action = document.getElementById('actionIUP');
//    action.value = 'delete';
//    document.getElementById('Currencyform').submit();
//}

$(document).ready(function () {

    $('#Hostform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            HostName: {
                validators: {
                    notEmpty: {
                        message: 'The host name is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The host name contains illegal characters.'
                    }
                }
            },
            HostStatus: {
                validators: {
                    notEmpty: {
                        message: 'The host Status name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The host Status contains illegal characters.'
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