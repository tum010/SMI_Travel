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
        BankStatus: {
            validators: {
                notEmpty: {
                    message: 'The Status is required'
                }
            }
        },
        
    }
    }).on('success.field.bv', function (e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);
        }
    });
    
    $("#CodeSearch,#NameSearch,#BrachSearch,#AccountNoSearch,#TypeSearch,#StatusSearch").keyup(function (event) {
        if (event.keyCode === 13) {
            searchAction();
        }
    });
    
    $(".numerical").on('input', function() { 
        var value=$(this).val().replace(/[^0-9]*/g, '');
        $(this).val(value);
    });
    
    $(".specialcharactor").on('input', function() { 
        var value=$(this).val().replace(/[^ก-ฮa-z0-9]*/g, '');
        $(this).val(value);
    });
});



function searchAction() {
    $("#Action").val('search');
    $("#SearchBank").submit();
}

function EditBank(id, code, name, branch, accNo, accType, status) {
    $('#Bankform').bootstrapValidator('resetForm', true);
    $("#BankIdEdit").val(id);
    $("#BankCode").val(code);
    $("#BankName").val(name);
    $("#BankBranch").val(branch);
    $("#BankAccountNo").val(accNo);
    $("#BankAccountType").val(accType);
    $("#BankStatus").val(status);
    $("#actionIUP").val('update');
    $('#BankModal').modal('show');
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


