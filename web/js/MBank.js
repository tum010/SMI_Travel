/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    
    $('#Bankform').bootstrapValidator({
    container: 'tooltip',
    excluded: [':disabled'],
    feedbackIcons: {
        valid: 'uk-icon-check',
        invalid: 'uk-icon-times',
        validating: 'uk-icon-refresh'
    },
    fields: {
        BankCode: {
            validators: {
                notEmpty: {
                    message: 'The Code is required'
                }
            }
        },
        BankName: {
            validators: {
                notEmpty: {
                    message: 'The Name is required'
                }
            }
        },
        BankBranch: {
            validators: {
                notEmpty: {
                    message: 'The Branch is required'
                }
            }
        },
        BankAccountNo: {
            validators: {
                notEmpty: {
                    message: 'The Account No. is required'
                }
            }
        },
        BankAccountType: {
            validators: {
                notEmpty: {
                    message: 'The Account Type is required'
                }
            }
        },
        
    }
}).on('success.field.bv', function (e, data) {
    if (data.bv.isValid()) {
        data.bv.disableSubmitButtons(false);
    }
});
});



function searchAction() {
    $("#Action").val('search');
    $("#SearchBank").submit();
}

function EditBank(id, code, name, branch, accNo, accType) {
    $('#Bankform').bootstrapValidator('resetForm', true);
    $("#BankId").val(id);
    $("#BankCode").val(code);
    $("#BankName").val(name);
    $("#BankBranch").val(branch);
    $("#BankAccountNo").val(accNo);
    $("#BankAccountType").val(accType);
    $("#actionIUP").val('update');
}

function DeleteBank(id,code,name){
    var BankID = document.getElementById('BankId');
    BankID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete Bank Code : " + code + " ?";
    
}

function Delete() {
    var action = document.getElementById('Action');
    action.value = 'delete';
    document.getElementById('SearchBank').submit();
}
