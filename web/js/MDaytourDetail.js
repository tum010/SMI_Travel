/* global display */

$(document).ready(function () {

  $("#MDaytourDetailForm")
          .bootstrapValidator({
//              framework: 'bootstrap',
                container: 'tooltip',
                excluded: [':disabled'],
                feedbackIcons: {required: 'glyphicon glyphicon-asterisk',
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    InputCode: {
                        validators: {
                            notEmpty: {
                                message: 'The Code is required'
                            }
                        }
                    },
                    InputName: {
                        validators: {
                            notEmpty: {
                                message: 'The Name is required'
                            }
                        }
                    },
                    InputMin: {
                        trigger: 'keyup change',
                        validators: {
                            integer: {
                                message: 'valid a Number'
                            }
                        }
                    },
                    InputMax: {
                        trigger: 'keyup change',
                        validators: {
                            integer: {
                                message: 'valid a Number'
                            }
                        }
                    }
                }
            })
            .on('success.field.bv', function (e, data) {
                if (data.bv.isValid()) {
                    data.bv.disableSubmitButtons(false);

                }
            });
    
    //Number
    $(".money").mask('000,000,000', {reverse: true});

    $("#InputGuideCommission").inputmask("decimal",{
         radixPoint:".", 
         groupSeparator: ",", 
         digits: 2,
         autoGroup: true
     });
    
    $(".decimalformat").keypress(function(e){
        var a = [];
        var k = e.which;

        for (i = 48; i < 58; i++)
            a.push(i);

        if (!(a.indexOf(k)>=0))
            e.preventDefault();
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











    /*Delete row priceTable*/
//    $("#PriceTable").on('click', 'span', function (e) {
//        if ($('#PriceTable tbody tr').length < 2) {
//            alert("Can not delete first row");
//            location.reload();
//            return false;
//        } else {
//
//            $(this).closest('tr').remove();
//                location.reload();
//        }

//    });4

    /*Delete row expenseTable*/
//    $("#ExpenseTable").on('click', 'span', function (e) {
//        if ($('#ExpenseTable tbody tr').length < 2) {
//            alert("Can not delete first row");
//            location.reload();
//            return false;
//        } else {
//            $(this).closest('tr').remove();
//                location.reload();
//        }

//    });


});

