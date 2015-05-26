/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function searchAction(){ 
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchProductType').submit();     
}

function save(){
    var ProductTypeName =  document.getElementById('ProductTypeName');
    if( ProductTypeName.value == ''){
        alert('please fill in product type name');
    }else{
        document.getElementById('ProductTypeform').submit();
    }       
}

function EditProductType(id,name){
    document.getElementById("ProductTypeID").readOnly = true;
    document.getElementById('ProductTypeID').value=id;
    document.getElementById('ProductTypeName').value=name;
    document.getElementById('actionIUP').value='update';
}

function DeleteProductType(id){
    var ProductTypeID = document.getElementById('ProductTypeID');
    ProductTypeID.value = id;
     document.getElementById('delCode').innerHTML = "Are you sure to delete id : " + id + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('ProductTypeform').submit();
}

function addaction(){
    document.getElementById("ProductTypeID").readOnly = false;
    document.getElementById('ProductTypeID').value = '';
    document.getElementById('ProductTypeName').value = '';
    document.getElementById('actionIUP').value='add';
}

$(document).ready(function () {

    $('#ProductTypeform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            
            ProductTypeName: {
                validators: {
                    notEmpty: {
                        message: 'The product type name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The product type name contains illegal characters.'
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


