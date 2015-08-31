/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {

    $('#ProductDetailForm').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            code: {
                validators: {
                    notEmpty: {
                        message: 'The product code is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The product code contains illegal characters.'
                    }
                }
            },
            name: {
                validators: {
                    notEmpty: {
                        message: 'The product name name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The product name contains illegal characters.'
                    }
                }
            }

        }
    }).on('success.field.bv', function(e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);
        }
    });


    $('#ProductPriceform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            effectivefrom: {
                validators: {
                    notEmpty: {
                        message: 'The effectivefrom  is required'
                    },
                    date: {
                        format: 'YYYY-MM-DD',
                        message: 'The format is YYYY-MM-DD'
                    }
                }
            },
            effectiveto: {
                validators: {
                    notEmpty: {
                        message: 'The effectiveto is required'
                    },
                    date: {
                        format: 'YYYY-MM-DD',
                        message: 'The format is YYYY-MM-DD'
                    }
                }
            }


        }
    }).on('success.field.bv', function(e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);
        }
    });

    $('.date')
            .on('dp.change dp.show', function(e) {
                // Revalidate the date when user change it
                $('#ProductPriceform').bootstrapValidator('revalidateField', 'effectivefrom');
                $('#ProductPriceform').bootstrapValidator('revalidateField', 'effectiveto');
            });



});

function EditProductPrice(id, from, to, adcost, adprice, chcost, chprice, incost, inprice) {
    document.getElementById('PriceItemID').value = id;
    document.getElementById('effectivefrom').value = from;
    document.getElementById('effectiveto').value = to;
    document.getElementById('ADcost').value = numberWithCommas(adcost);
    document.getElementById('ADprice').value = numberWithCommas(adprice);
    document.getElementById("CHcost").value = numberWithCommas(chcost);
    document.getElementById("CHprice").value = numberWithCommas(chprice);
    document.getElementById("INcost").value = numberWithCommas(incost);
    document.getElementById("INprice").value = numberWithCommas(inprice);
    document.getElementById("actionprice").value = 'updatePriceItem';

}

function DeleteProductPrice(id) {
    var PriceItemID = document.getElementById('PriceItemID');
    PriceItemID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete price?";

}

function Delete() {
    document.getElementById("actionprice").value = 'deletePriceItem';
    document.getElementById("ProductPriceform").submit();
}

function confirmDisableStock(){
    var name = document.getElementById('name').value;
    if(!document.getElementById('isStock').checked){          
        var code = document.getElementById('code').value;
        document.getElementById('disabledCode').innerHTML = code + " is currently being used in stock. Are you sure to disable isStock " + code + " ?";
        
        $('#disableIsStockModal').modal('show');
    } else {       
        if(name === ''){
            $("#saveProduct").addClass("disabled");
        } else {
            document.getElementById("ProductDetailForm").submit();
        }               
    }    
}

function disabledIsStock(){
    $('#disableIsStockModal').modal('hide');
    document.getElementById("ProductDetailForm").submit();
}

function validateNameProduct(){
    var name = document.getElementById('name').value;
    if(name !== ''){
        $("#saveProduct").removeClass("disabled");
    } else {
        $("#saveProduct").addClass("disabled");
    }
}