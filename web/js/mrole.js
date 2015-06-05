/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('#Roleform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            RoleName: {
                validators: {
                    notEmpty: {
                        message: 'Role name is required'
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


function searchAction() {

    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchRole').submit();
}

function EditRole() {
    var id = arguments[0];
    var name = arguments[1];
    $('#Roleform')[0].reset();
    $('#Roleform').bootstrapValidator('resetForm', true);
    for (i = 2; i < arguments.length - 1; i++) {
//        document.getElementById(arguments[i]).checked = true;
//        $('#' + arguments[i]).attr('checked', true);
//        $('#' + arguments[i]).prop('checked', 'checked');
        $('#' + arguments[i]).prop('checked', true);
    }
    $('#RoleID').val(id);
    $('#RoleName').val(name);
    $("#actionIUP").val('update');
}

function DeleteRole(id, name) {
    $("#DelRoleID").val(id);
    $('#delCode').html("Are you sure to delete role " + name + " ?");
    $('#DelActionIUP').val('delete');
    $('#DelRoleName').val(name);
    $('#delRoleButton').click();
}

function Delete() {
    $("#actionIUP").val('delete');
    $('#Roleform').submit();
}

