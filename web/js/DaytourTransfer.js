/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    //Number
    $(".money").mask('000,000,000', {reverse: true});
    $(".datemask").mask('0000-00-00', {reverse: true});
    
    
//     $('#datetimepicker').datetimepicker({}).on('change', function(e) {
//            $('#saveDaytourTransferForm').bootstrapValidator('revalidateField', 'InputDate');
//      });

//$("#saveDaytourTransferForm")
//          .bootstrapValidator({
//              framework: 'bootstrap',
////                container: 'tooltip',
////                excluded: [':disabled'],
//                feedbackIcons: {required: 'glyphicon glyphicon-asterisk',
//                    valid: 'glyphicon glyphicon-ok',
//                    invalid: 'glyphicon glyphicon-remove',
//                    validating: 'glyphicon glyphicon-refresh'
//                },
//                fields: {
//                    InputDate: {
//                        validators: {
//                            notEmpty: {
//                                message: 'The Date is required'
//                            }
//                        }
//                    }
//                }
//            })
//            .on('success.field.fv', function(e, data) {
//                if (data.fv.isValidField('InputDate') === false) {
//                    data.fv.revalidateField('InputDate');
//                }
////                if (data.bv.isValid()) {
////                    data.bv.disableSubmitButtons(false);
////                }
//            });
});

function printTransferJob() {
     if($("#InputDocument").val() === ""){
           $("#ButtonPrint").disableSelection();
         
     }else{
         var inputDocument = document.getElementById("InputDocument").value;
         window.open("report.smi?name=TransferJob&docno=" + inputDocument);
     }
    
}


