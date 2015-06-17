/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function() {
    // validate

    var Billable = $("#Billable");
    /*
    Billable.bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            billdate: {
                validators: {
                    notEmpty: {
                        message: 'bill date is required'
                    },
                    date: {
                        format: 'YYYY-MM-DD',
                        message: 'bill date is not a valid date'
                    }
                }
            }
        }
    }).on('success.field.bv', function(e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);

        }
    });
         */   
    Billable.on('mouseover', function () {
        var billto = $(this).find('[name="billto"]');
        var isEmpty = billto.val() === '';
        Billable.bootstrapValidator('enableFieldValidators', 'billto', isEmpty);
        var billdate = $(this).find('[name="billdate"]');
        var isEmpty = billdate.val() === '';
        Billable.bootstrapValidator('enableFieldValidators', 'billdate', isEmpty);
       
    });

    $("#searchBillto").keyup(function(event) {
        if (event.keyCode === 13) {
            if ($("#searchBillto").val() == "") {
                // alert('please input data');
            }
            searchCustomerAgentList($("#searchBillto").val());
        }
    });


});

function searchCustomerAgentList(name) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getListBillto';
    CallAjax(param);

}

function CallAjax(param) {
    var url = 'AJAXServlet';

    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                $('#BillToTable').dataTable().fnClearTable();
                $('#BillToTable').dataTable().fnDestroy();
                $("#BillToTable tbody").empty().append(msg);

                $('#BillToTable').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bPaginate": true,
                    "bInfo": false,
                    "bLengthChange": false,
                    "iDisplayLength": 10
                });
                $("#ajaxload").addClass("hidden");

            }, error: function(msg) {
                $("#ajaxload").addClass("hidden");
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }


}

function setBillValue(billto, billname, address, term, pay) {

    $("#billto").val(billto);
    $("#billname").val(billname);
    if (address == 'null') {
        $("#address").val("");
    } else {
        $("#address").val(address);
    }

    if (term == 'null') {
        $("#accterm").val("");
    } else {
        $("#accterm").val(term);
    }
    if (pay == 'null') {
        $("#accpay").val("");
    } else {
        $("#accpay").val(pay);
    }

    $("#BillToModal").modal('hide');
}

$(document).ready(function () {
    var bla = $('#resultText').val();

    if(bla == "save successful"){
        
        $('#textAlertDivSave').show();
    }else if ( bla === ""){
        $('#textAlertDivSave').hide();
    }else {
        $('#textAlertDivNotSave').show();
    }
});