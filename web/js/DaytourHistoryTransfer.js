/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
//    $('.date').datetimepicker();
//      $(".datemask").mask('0000-00-00', {reverse: true});
 
    
    $('#DateFrom').datetimepicker({
        }).on('change', function(e) {
            // Revalidate the start date field
            $('#SearchHistoryTransfer').bootstrapValidator('revalidateField', 'InputDateFrom');
    });

    $('#DateTo').datetimepicker({
        }).on('change', function(e) {
            $('#SearchHistoryTransfer').bootstrapValidator('revalidateField', 'InputDateTo');
    });
//InputDateFrom
//InputDateTo
$("#SearchHistoryTransfer")
    .bootstrapValidator({
            framework: 'bootstrap',
//            container: 'tooltip',
            feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
            fields: {
                InputDateFrom: {
                    validators: {
                        notEmpty: {
                            message: 'The Date From is required'
                        },
                        date: {
                            format: 'DD-MM-YYYY',
                            max: 'InputDateTo',
                            message: 'The Date From is not a valid'
                        }
                    }
                },
                InputDateTo: {
                    validators: {
                        notEmpty: {
                            message: 'The Date To is required'
                        },
                        date: {
                            format: 'DD-MM-YYYY',
                            min: 'InputDateFrom',
                            message: 'The Date To is not a valid'
                        }
                    }
                }
            }
        })
        .on('success.field.fv', function(e, data) {
            if (data.field === 'InputDateFrom' && data.fv.isValidField('InputDateTo') === false) {
                data.fv.revalidateField('InputDateTo');
            }

            if (data.field === 'InputDateTo' && data.fv.isValidField('InputDateFrom') === false) {
                data.fv.revalidateField('InputDateFrom');
            }
        });
});


function printTransferJob(docno) {
    window.open("report.smi?name=TransferJob&docno=" + docno);
}