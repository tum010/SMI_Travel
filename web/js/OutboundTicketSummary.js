/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function printTicketSummary() {
    var ticketfrom = document.getElementById("ticketFrom").value;
    var tickettype = document.getElementById("ticketType").value;
    var startdate = document.getElementById("startdate").value;
    var enddate = document.getElementById("enddate").value;
    var billto = document.getElementById("billto").value;
    var fromdatepanel = document.getElementById("fromdatepanel");

    if (startdate == "") {
        if (!$('#fromdatepanel').hasClass('has-feedback')) {
            $('#fromdatepanel').addClass('has-feedback');
        }
        $('#fromdatepanel').removeClass('has-success');
        $('#fromdatepanel').addClass('has-error');
    } else if (enddate == "") {
        if (!$('#todatepanel').hasClass('has-feedback')) {
            $('#todatepanel').addClass('has-feedback');
        }
        $('#todatepanel').removeClass('has-success');
        $('#todatepanel').addClass('has-error');
    }
    else {
        $('#startdate').focus();
        $('#enddate').focus();
        window.open("report.smi?name=TicketSummary&ticketfrom=" + ticketfrom + "&tickettype=" + tickettype + "&startdate=" + startdate + "&enddate=" + enddate + "&billto=" + billto + "&department=Outbound");
    }

}

function focusEndDate() {
    document.getElementById("enddate").focus();
}
$(document).ready(function() {
    $('.date').datetimepicker();
    $('span').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });

    $('#OutboundTicketSummaryReportFrom').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            startdate: {
                trigger: 'focus',
                validators: {
                    notEmpty: {
                        message: 'The From Date is required'
                    }
                }
            },
            enddate: {
                trigger: 'focus',
                validators: {
                    notEmpty: {
                        message: 'The To Date is required'
                    }
                }
            }
        }
    });
});
