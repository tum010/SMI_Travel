function searchAction(){ 
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchHost').submit();     
}

function actionAdd(){ 
    var action = document.getElementById('actionIUP');
    action.value = 'save';
    document.getElementById('Hostform').submit();     
}

function save(){
    var HostName = document.getElementById('HostName');
    var HostCode =  document.getElementById('HostCode');
    if( HostName.value == ''){
        alert('please fill in Host Name ');
    }else if( HostCode.value == ''){
        alert('please fill in Host Codes');
    }else if(!(CheckCharecter(HostName.value) && CheckCharecter(HostCode.value))){
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
function EditHost(id,code,name,address,tel,fax){
    document.getElementById('HostCode').value=code;
    document.getElementById('HostName').value=name;
    document.getElementById('HostAddress').value=address;
    document.getElementById('HostTel').value=tel;
    document.getElementById('HostFax').value=fax;
    document.getElementById('HostID').value=id;
    document.getElementById('actionIUP').value='save';
    document.getElementById("HostCode").readOnly = true;
}

function DeleteHost(id,code){
    var HostID = document.getElementById('HostID');
    HostID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete code : " + code + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('Hostform').submit();
}

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
            HostCode: {
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