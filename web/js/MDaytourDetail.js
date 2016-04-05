/* global display */

$(document).ready(function () {

  $("#MDaytourDetailForm")
          .bootstrapValidator({
                container: 'tooltip',
                excluded: [':disabled'],
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    InputCode: {
                        validators: {
                            notEmpty: {
                                message: 'Code is required'
                            }
                        }
                    },
                    InputName: {
                        validators: {
                            notEmpty: {
                                message: 'Name is required'
                            }
                        }
                    },
                    InputMin: {
//                        excluded: false,
                        trigger: 'focus change keyup',
                        validators: {
                            integer: {                              
                                message: 'valid a Number'
                            }
                            ,lessThan: {
                                value: 'InputMax',
                                message: 'Please enter a value less than or equal to %s'
                            }

                        }
                    },
                    InputMax: {
//                        excluded: false,
                        trigger: 'focus change keyup',
                        validators: {
                            integer: {  
                                message: 'valid a Number'
                            }
                            ,greaterThan: {        
                                value: 'InputMin',
                                message: 'Please enter a value greater than or equal to %s'
                            }
                        }
                    }
                }
            })
            .on('success.field.fv', function(e, data) {
                if (data.fv.getSubmitButton()) {
                    data.fv.disableSubmitButtons(false);
                }          
            });
        
        var delay = (function(){
            var timer = 0;
            return function(callback, ms){
              clearTimeout (timer);
              timer = setTimeout(callback, ms);
            };
        })();   
        
        $("#InputMax").on('blur',function(){
            $("#InputMin").focus();
            $("#InputGuideCommission").focus();
        });
        $("#InputMin").on('keyup',function(){
            delay(function(){
                $("#InputMax").focus();
             }, 1500 ); 
        });
           
    //Number
    $(".money").mask('000,000,000', {reverse: true});
   

    $("#InputGuideCommission").inputmask("decimal",{
         radixPoint:".", 
         groupSeparator: ",", 
         digits: 2,
         autoGroup: true
     });


});

$(document).ready(function () {
    //Add Blank row for user input.
    addRowPriceTable();
    /*Auto Add lastrow priceTable*/
    $(document).on('keydown', '#PriceTable tbody tr:last td  input', function (e) {
        addRowPriceTable();
    });

    //Add Blank row for user input.
    addRowExpenseTable();
    $(document).on('keydown', '#ExpenseTable tbody tr:last td  input', function (e) {
        addRowExpenseTable();
    });


    function addRowPriceTable() {
        var countPrice = $('#PriceTable tbody tr').length;
        var clone = $('#PriceTable tbody tr:first').clone();
        console.log("countPrice="+countPrice);
        clone.removeClass("hide");
        clone.find('input,select,span').each(function () {
            $(this).removeClass('hidden');
            $(this).attr({
                id: $(this).attr('id') + countPrice,
                name: $(this).attr('name') + countPrice
            });
            $('#counterPrice').val(countPrice+1);
        });

        $('#PriceTable tbody').append(clone);
    }

    function addRowExpenseTable() {
        var countExpense = $('#ExpenseTable tbody tr').length;
        var clone = $('#ExpenseTable tbody tr:first').clone();
        clone.removeClass("hide");
        clone.find('input,select,span').each(function () {
            $(this).attr({
                id: $(this).attr('id') + countExpense,
                name: $(this).attr('name') + countExpense
            });
            $('#counterExpense').val(countExpense+1);
        });
        $('#ExpenseTable tbody').append(clone);
    }

    

});

