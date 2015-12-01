/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function printDocSummary() {
    var type = document.getElementById("Doctype").value;
    var ticketfrom = document.getElementById("ticketFrom").value;
    var tickettype = document.getElementById("ticketType").value;
    var startdate = document.getElementById("startdate").value;
    var enddate = document.getElementById("enddate").value;

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
        if (type == 1) {
            window.open("report.smi?name=AirlineSummary&ticketfrom=" + ticketfrom + "&tickettype=" + tickettype + "&startdate=" + startdate + "&enddate=" + enddate + "&department=Outbound");
        } else {
            window.open("report.smi?name=StaffSummary&ticketfrom=" + ticketfrom + "&tickettype=" + tickettype + "&startdate=" + startdate + "&enddate=" + enddate + "&department=Outbound");
        }

    }

}

$(document).ready(function() {
    $('.date').datetimepicker();
    $('span').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });

    $('#OutboundAirlineStaffSummaryReportFrom').bootstrapValidator({
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

