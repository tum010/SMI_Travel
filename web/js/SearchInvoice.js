/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Validator Date From and To
$(document).ready(function () {
//    var $stockForm = $("#StockForm");
   
 });
 
 function validForm(){
      $("#SearchInvoiceForm")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    FromDate: {
                        trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                max: 'FromDate',
                                message: 'The Date From is not a valid'
                            }
                        }
                    },
                    ToDate: {
                        trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                min: 'ToDate',
                                message: 'The Date To is not a valid'
                            }
                        }
                    }
                }
            }).on('success.field.fv', function (e, data) {
                alert("1");
                if (data.field === 'FromDate' && data.fv.isValidField('ToDate') === false) {
                    data.fv.revalidateField('ToDate');
                }

                if (data.field === 'ToDate' && data.fv.isValidField('FromDate') === false) {
                    data.fv.revalidateField('FromDate');
                }
            });
            
            $('#DateFrom').datetimepicker().on('dp.change', function (e) {
                $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'FromDate');
            });
            $('#DateTo').datetimepicker().on('dp.change', function (e) {
                $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'ToDate');
            });
 }
 
 function search(){
    var action = document.getElementById('action');
    action.value = 'search';
    document.getElementById('SearchInvoiceForm').submit();
 }
function DisableInvoice(){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('disableVoid').innerHTML = "Are you sure to delete booking other : " +  + " ?";
}

function EnableInvoice(){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('enableVoid').innerHTML = "Are you sure to enable booking other : " + code + " ?";
}

function Enable() {
    var action = document.getElementById('action');
    action.value = 'enable';
    document.getElementById('OtherForm').submit();
}

function Disable() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('OtherForm').submit();
}

