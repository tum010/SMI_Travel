/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $('.date').datetimepicker();
    $('.datemask').mask('0000-00-00');
    $('.spandate').click(function () {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });
    jQuery.curCSS = jQuery.css;
    $('#SearchPaymentHotelTable').dataTable({
        bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": true,
        "iDisplayLength": 10
    });
    //validate date
    $('#DateFrom').datetimepicker().on('dp.change', function (e) {
        $('#PaymentSearchForm').bootstrapValidator('revalidateField', 'InputFromDate');
    });
    $('#DateTo').datetimepicker().on('dp.change', function (e) {
        $('#PaymentSearchForm').bootstrapValidator('revalidateField', 'InputToDate');
    });

    $("#PaymentSearchForm").bootstrapValidator({
                framework: 'bootstrap',
//                container: 'tooltip',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    InputFromDate: {
                        trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                max: 'InputToDate',
                                message: 'The Date From is not a valid'
                            }
                        }
                    },
                    InputToDate: {
                        trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                min: 'InputFromDate',
                                message: 'The Date To is not a valid'
                            }
                        }
                    }
                }
            }).on('success.field.fv', function (e, data) {
        if (data.field === 'InputFromDate' && data.fv.isValidField('InputToDate') === false) {
            data.fv.revalidateField('InputToDate');
        }

        if (data.field === 'InputToDate' && data.fv.isValidField('InputFromDate') === false) {
            data.fv.revalidateField('InputFromDate');
        }
    });

});

function searchAction(){
    var action = document.getElementById('action');
    action.value = 'search';
    document.getElementById('PaymentSearchForm').submit();     
}

function DeletePayment(id, no) {
    var paymentID = document.getElementById('paymentID');
    var InputPayNo = document.getElementById('InputPayNo');
    paymentID.value = id;
    InputPayNo.value = no;
    document.getElementById('delCode').innerHTML = "Are you sure to delete payment no : " + no + " ?";
}

function Delete() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('PaymentSearchForm').submit();
}

