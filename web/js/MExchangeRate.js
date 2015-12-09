/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function searchExchange() {
   
    var action = document.getElementById('actionSearch');
    action.value = 'search';
//    document.getElementById('SearchExchange').submit();

}

function addaction(){
    document.getElementById('ExchangeID').value = '';
    document.getElementById('ExchangeDate').value = '';
    document.getElementById('ExchangeDate').value = '';
    document.getElementById('action').value='add';
}

function EditExchange(id, exdate, cur, exrate,createby,createdate) {
    document.getElementById('ExchangeID').value = id;
    document.getElementById('ExchangeDate').value = exdate;
    document.getElementById('ExchangeRate').value = exrate;
    document.getElementById('Currency').value = cur;
    document.getElementById('action').value = 'update';
}

function DeleteExchange(id, exdate,cur) {
    var ExchangeID = document.getElementById('ExchangeID');
    ExchangeID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete exchange rate : " + exdate + "( " + cur+") ?";
}

function Delete() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('ExchangeRateForm').submit();
}

function formatDecimal() {
    var decimalOnly = /^\s*-?[1-9]\d*(\.\d{1,4})?\s*$/;
    var myData = document.getElementById("ExchangeRate").value;
    if(myData!==''){
        if(decimalOnly.test(myData)){
//            alert('It is GOOD!');
            document.getElementById("btnSave").disabled = false;
        }else{
            document.getElementById("btnSave").disabled = true;
            alert('Please Input 4 decimal point!');
        }
    }
    return;  
}

$(document).ready(function() {
    $('.date').datetimepicker();
    $('.datemask').mask('0000-00-00');
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    
    $('#DateExchange').datetimepicker().on('dp.change', function (e) {
        $('#ExchangeRateForm').bootstrapValidator('revalidateField', 'ExchangeDate');
    });
    
//    $("#SearchExchange")
//        .bootstrapValidator({
//            framework: 'bootstrap',
//            feedbackIcons: {
//                valid: 'uk-icon-check',
//                invalid: 'uk-icon-times',
//                validating: 'uk-icon-refresh'
//            },
//            fields: {
//                FromDate: {
//                    trigger: 'focus keyup change',
//                        validators: {
//                            notEmpty: {
//                                message: 'The Date From is required'
//                            },
//                            date: {
//                                format: 'YYYY-MM-DD',
//                                max: 'ToDate',
//                                message: 'The Date From is not a valid'
//                            }
//                        }
//                },
//                ToDate: {
//                    trigger: 'focus keyup change',
//                        validators: {
//                            notEmpty: {
//                                message: 'The Date To is required'
//                            },
//                            date: {
//                                format: 'YYYY-MM-DD',
//                                min: 'FromDate',
//                                message: 'The Date To is not a valid'
//                            }
//                        }
//                }
//            }
//        }).on('success.field.fv', function (e, data) {
//            if (data.field === 'FromDate' && data.fv.isValidField('ToDate') === false) {
//                    data.fv.revalidateField('ToDate');
//                }
//
//                if (data.field === 'ToDate' && data.fv.isValidField('FromDate') === false) {
//                    data.fv.revalidateField('FromDate');
//                }
//        });
//
//        $('#DateFrom').datetimepicker().on('dp.change', function (e) {
//            $('#SearchExchange').bootstrapValidator('revalidateField', 'FromDate');
//        });
//        $('#DateTo').datetimepicker().on('dp.change', function (e) {
//            $('#SearchExchange').bootstrapValidator('revalidateField', 'ToDate');
//        });
//            
    
    $('#ExchangeRateForm').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            ExchangeDate: {
                validators: {
                    notEmpty: {
                        message: 'The ExchangeDate is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The ExchangeDate contains illegal characters.'
                    }
                }
            },
            Currency: {
                validators: {
                    notEmpty: {
                        message: 'The Currency is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The Currency contains illegal characters.'
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
