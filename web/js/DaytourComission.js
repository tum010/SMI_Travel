/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var controlGuide;
var controlAgent;

$(document).ready(function() {
    
    $(".number").mask('000000000000000000', {reverse: true});
    Selectize.define('clear_selection', function(options) {
        var self = this;
        self.plugins.settings.dropdown_header = {
            title: 'Clear Selection'
        };
        this.require('dropdown_header');
        self.setup = (function() {
            var original = self.setup;
            return function() {
                original.apply(this, arguments);
                this.$dropdown.on('mousedown', '.selectize-dropdown-header', function(e) {
                    self.setValue('');
                    self.close();
                    self.blur();
                    return false;
                });
            };
        })();
    });

//Number
    
    $(".money").mask('000,000,000', {reverse: true});

    var $selectAgent = $('#SelectAgent').selectize({
        create: false,
        sortField: 'text'
    });
    controlAgent = $selectAgent[0].selectize;

    $('#selAgentReport').selectize({
        create: false,
        sortField: 'text'
    });


    var $selectGuide = $('#SelectGuide').selectize({
        create: false,
        sortField: 'text'
    });

    $('#selGuideReport').selectize({
        create: false,
        sortField: 'text'
    });
    controlGuide = $selectGuide[0].selectize;
    var tableLength = $("#CommissionTable tbody").find("tr").length;
    console.log("table length " + tableLength);

    var dataGuide = [];
    dataGuide = guideName;
//    console.log('dataGuide :'+dataGuide);
    for (var i = 1; i <= tableLength; i++) {
        var name = "#selectGuide-" + i;
        console.log("name = " + name);

        $(name).selectize({
            removeItem: '',
            sortField: 'text',
            create: false,
            dropdownParent: 'body',
            plugins: {
                'clear_selection': {}
            }
//            onDropdownOpen:  function() {
//                arguments[0][0].style.width = "250%";
//	    }
        });

    }

    //validate date
    $('#DateFrom').datetimepicker().on('dp.change', function(e) {
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateFrom');
    });
    $('#DateTo').datetimepicker().on('dp.change', function(e) {
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateTo');
    });

    $("#searchDaytourCommissionForm")
            .bootstrapValidator({
                framework: 'bootstrap',
//                container: 'tooltip',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    InputDateFrom: {
                        trigger: 'focus keyup change',
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
                        trigger: 'focus keyup change',
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
            }).on('success.field.fv', function(e, data) {
        if (data.field === 'InputDateFrom' && data.fv.isValidField('InputDateTo') === false) {
            data.fv.revalidateField('InputDateTo');
        }

        if (data.field === 'InputDateTo' && data.fv.isValidField('InputDateFrom') === false) {
            data.fv.revalidateField('InputDateFrom');
        }
    });

    $('.fromDate').datetimepicker().change(function() {
        checkFromDateField();
    });
    $('.toDate').datetimepicker().change(function() {
        checkToDateField();
    });

});

function checkFromDateField() {
    var InputToDate = document.getElementById("InputDateTo");
    var inputFromDate = document.getElementById("InputDateFrom");
    if (InputToDate.value === '' && inputFromDate.value === '') {
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateTo');
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateFrom');
        $("#printbutton").removeClass("disabled");
    } else if (inputFromDate.value === '' || InputToDate.value === '') {
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateTo');
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateFrom');
        $("#ButtonPrintGuide").addClass("disabled");
        $("#ButtonPrintAgent").addClass("disabled");
    } else {
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateTo');
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateFrom');
        $("#ButtonPrintGuide").removeClass("disabled");
        $("#ButtonPrintAgent").removeClass("disabled");
        checkDateValue("from", "");
    }
}

function checkToDateField() {
    var InputToDate = document.getElementById("InputDateTo");
    var inputFromDate = document.getElementById("InputDateFrom");
    if (InputToDate.value === '' && inputFromDate.value === '') {
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateTo');
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateFrom');
        $("#printbutton").removeClass("disabled");
    } else if (inputFromDate.value === '' || InputToDate.value === '') {
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateTo');
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateFrom');
        $("#ButtonPrintGuide").addClass("disabled");
        $("#ButtonPrintAgent").addClass("disabled");
    } else {
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateTo');
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateFrom');
        $("#ButtonPrintGuide").removeClass("disabled");
        $("#ButtonPrintAgent").removeClass("disabled");
        checkDateValue("to", "");
    }
}

function checkDateValue(date) {
    var inputFromDate = document.getElementById("InputDateFrom");
    var InputToDate = document.getElementById("InputDateTo");

    if ((inputFromDate.value !== '') && (InputToDate.value !== '')) {
        var fromDate = (inputFromDate.value).split('-');
        var toDate = (InputToDate.value).split('-');
        if ((parseInt(fromDate[0])) > (parseInt(toDate[0]))) {
            validateDate(date, "over");
        }
        if (((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))) {
            validateDate(date, "over");
        }
        if (((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[2])) > (parseInt(toDate[2]))) {
            validateDate(date, "over");
        }
    }
}

function validateDate(date, option) {
    if (option === 'over') {
        if (date === 'from') {
            $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateTo');
            $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateFrom');
        }
        if (date === 'to') {
            $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateTo');
            $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateFrom');
        }
        $("#ButtonPrintGuide").addClass("disabled");
        $("#ButtonPrintAgent").addClass("disabled");
    } else {
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateTo');
        $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateFrom');
        $("#ButtonPrintGuide").addClass("disabled");
        $("#ButtonPrintAgent").addClass("disabled");
    }
}

function verifyValueToGuide() {
//    guideCheck();
    $("#GuideModal").modal('show');
}
function verifyValueToAgent() {
//    agentCheck();
    $("#AgentModal").modal('show');
}
//function guideCheck(){
//    var isguidename = true;
//    var isguidecom = true;
//    var isagentname = true;
//    var isagentcom = true;
//    $('#CommissionTable tbody tr td').each(function(){
//        $(this).find('select.guidename').each(function(){
//            console.log('guidename :'+$(this).val());
//            if ($(this).val() === '') {
//                 $(this).focus();
//                 isguidename = false;        
//                return false; 
//            }
//        });
//        $(this).find('.guidecom').each(function(){
//            if ($(this).val() === '' ||  $(this).val() === '0') {
//                 $(this).focus();
//                isguidecom = false;        
//                return false; 
//            }
//        });
//        $(this).find('.agentname').each(function(){
//            if ($(this).val() === '' ) {
//                 $(this).focus();
//                isagentname = false;        
//                return false; 
//            }
//        });
//        $(this).find('.agentcom').each(function(){
//            if ($(this).val() === '' ||  $(this).val() === '0' ) {
//                 $(this).focus();
//                isagentcom = false;        
//                return false; 
//            }
//        });
//    });
//    var alerttext = "";
//    if(isguidename === false){
//         alerttext += "- Some Guide name are still empty...! \n";
//        $("#GuideModal").modal('hide');
//    }
//    if(isguidecom === false){
//         alerttext += "- Some Guide commission are still empty...! \n";
//        $("#GuideModal").modal('hide');
//    }
//    if(isagentname === false){
//        alerttext += "- Some Agent name are still empty...! \n";
//        $("#GuideModal").modal('hide');
//    }
//    if(isagentcom === false){
//        alerttext += "- Some Agent commission are still empty...! \n";
//        $("#GuideModal").modal('hide');
//    }
//    if(isguidename === true && isguidecom === true && isagentname === true && isagentcom === true){ 
//            $("#GuideModal").modal('show');
//    }
//    
//    if(alerttext !== ''){
//        alert(alerttext);
//    }else{
//        return ;
//    }
//     
//}

//function agentCheck(){
//    var isguidename = true;
//    var isguidecom = true;
//    var isagentname = true;
//    var isagentcom = true;
//    $('#CommissionTable tbody tr td').each(function(){
//        $(this).find('select.guidename').each(function(){
//            console.log('guidename :'+$(this).val());
//            if ($(this).val() === '') {
//                 $(this).focus();
//                 isguidename = false;        
//                return false; 
//            }
//        });
//        $(this).find('.guidecom').each(function(){
//            if ($(this).val() === '' ||  $(this).val() === '0') {
//                 $(this).focus();
//                isguidecom = false;        
//                return false; 
//            }
//        });
//        $(this).find('.agentname').each(function(){
//            if ($(this).val() === '' ) {
//                 $(this).focus();
//                isagentname = false;        
//                return false; 
//            }
//        });
//        $(this).find('.agentcom').each(function(){
//            if ($(this).val() === '' ||  $(this).val() === '0' ) {
//                 $(this).focus();
//                isagentcom = false;        
//                return false; 
//            }
//        });
//    });
//    var alerttext = "";
//    if(isguidename === false){
//         alerttext += "- Some Guide name are still empty...! \n";
//        $("#AgentModal").modal('hide');
//    }
//    if(isguidecom === false){
//         alerttext += "- Some Guide commission are still empty...! \n";
//        $("#AgentModal").modal('hide');
//    }
//    if(isagentname === false){
//        alerttext += "- Some Agent name are still empty...! \n";
//        $("#AgentModal").modal('hide');
//    }
//    if(isagentcom === false){
//        alerttext += "- Some Agent commission are still empty...! \n";
//        $("#AgentModal").modal('hide');
//    }
//    if(isguidename === true && isguidecom === true && isagentname === true && isagentcom === true){ 
//            $("#AgentModal").modal('show');
//    }
//    
//    if(alerttext !== ''){
//        alert(alerttext);
//    }else{
//        return ;
//    }
//     
//}


function searchDaytourCommission() {
    console.log("searchDaytourCommission");
    $("#searchDaytourCommissionForm").submit();
}

function saveDaytourCommission() {
    console.log("saveDaytourCommission");
    var dateTo = $("#InputDateTo").val();
    var dateFrom = $("#InputDateFrom").val();
    $("#dateFromSearch").val(dateFrom);
    $("#dateToSearch").val(dateTo);
    $("#filterGuide").val(controlGuide.getValue());
    $("#filterAgent").val(controlAgent.getValue());
    var counter = 1;
    $("#CommissionTable tbody").find("tr").each(function(){
        var checkbox = $(this).find("td.edited").children();
        if ($(checkbox).is(":checked")) {
            var cloneTr = $(this).clone();
            cloneTr.find('input,select,span').each(function() {
                $(this).removeClass('hidden');
                if ($(this).attr('name') === "AgentName-") {
                    $(this).val($(this).attr("valHidden"));
                    
                }
                $(this).attr({
                    name: $(this).attr('name') + counter
                });
            });
            $("#EditTable tbody").append(cloneTr);
            counter++;
        }
    });

    var rows = $("#EditTable tbody").find("tr").length;
    var rows1 = $("#CommissionTable tbody").find("tr").length;
    console.log(" EditTable (" + rows + ") row[s].");
    console.log(" CommissionTable (" + rows1 + ") row[s].");
    $("#dayCommRows").val(rows);
    if ($("#CommissionTable tbody").find("tr").length === 1 && $("#inputDateFrom").val() === "" && $("#inputDateTo").val() === "") {
        console.log("submit search");
        $("#searchDaytourCommissionForm").submit();
    } else {
        console.log("submit save");
        $("#saveDaytourCommissionForm").submit(function(e) {
        });

    }

}

function printGuideCommission() {
//    var guidePrintFrom = document.getElementById("guidePrintFrom").value;
//    var guidePrintTo = document.getElementById("guidePrintTo").value;
//    var selGuideReport = document.getElementById("selGuideReport").value;
    var guidePrintFrom = document.getElementById("InputDateFrom").value;
    var guidePrintTo = document.getElementById("InputDateTo").value;
    var selGuideReport = document.getElementById("SelectGuide").value;
    if ((guidePrintFrom !== '') && (guidePrintTo !== '')) {
        window.open("report.smi?name=GuideCommission&startdate=" + guidePrintFrom + "&enddate=" + guidePrintTo + "&GuideID=" + selGuideReport);
    } else {
        validateDate();
    }

}

function printAgentCommission() {
//    var agentPrintFrom = document.getElementById("agentPrintFrom").value;
//    var agentPrintTo = document.getElementById("agentPrintTo").value;
//    var selAgentReport = document.getElementById("selAgentReport").value;
    var agentPrintFrom = document.getElementById("InputDateFrom").value;
    var agentPrintTo = document.getElementById("InputDateTo").value;
    var selAgentReport = document.getElementById("SelectAgent").value;
    if ((agentPrintFrom !== '') && (agentPrintTo !== '')) {
        window.open("report.smi?name=AgentCommission&startdate=" + agentPrintFrom + "&enddate=" + agentPrintTo + "&agentID=" + selAgentReport);
    } else {
        validateDate();
    }    
}

function addGuide() {
    var name = $('#guideName').val();
    var detail = $('#guideDetail').val();
    if (name !== '' && detail !== '') {
        $('#addGuideAction').val('addGuide');
        var action = $('#addGuideAction').val();
        console.log('Action Add Guide : ' + action);
        $("#AddGuideModal").hide();
        $("#searchDaytourCommissionAddGuideForm").submit();
    } else {
        validateAddGuide();
    }
}

function addGuideOtherCommission() {
    var name = $('#guideName').val();
    var detail = $('#guideDetail').val();
    var fromdate = $('#fromdateAdd').val();
    var todate = $('#todateAdd').val();
    var agent = $('#agentAdd').val();
    var guide = $('#guideAdd').val();
    console.log("Add Guide  Confirm : " + fromdate + " " + todate + " " + agent + " " + guide);
    if (name !== '' && detail !== '') {
        $('#addGuideAction').val('addGuide');
        $('#fromdateAdd').val(fromdate);
        $('#todateAdd').val(todate);
        $('#agentAdd').val(agent);
        $('#guideAdd').val(guide);
        var action = $('#addGuideAction').val();
        console.log('Action Add Guide : ' + action);
        $("#AddGuideModal").hide();
        $("#searchDaytourCommissionAddGuideForm").submit();
    } else {
        validateAddGuide();
    }
}

function validateAddGuide() {
    // Validate form add Guide
    $('#searchDaytourCommissionAddGuideForm').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            guideName: {
                validators: {
                    notEmpty: {
                        message: 'The name guide is required'
                    }
                }
            },
            guideDetail: {
                validators: {
                    notEmpty: {
                        message: 'The detail guide name is required'
                    }
                }
            }

        }
    }).on('success.field.bv', function(e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);
        }
    });

}

function getGuideCommission(tourcode, textid) {
    var servletName = 'DaytourCommissionServlet';
    var servicesName = 'AJAXBean';
    var tourCode = tourcode;
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&tourcode=' + tourCode +
            '&type=' + 'getguidecom';
    CallGuideComAjax(param, textid);
}

function CallGuideComAjax(param, textid) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                if (msg != 'null') {
                    $('#' + textid).val(msg);
                }

            }, error: function(msg) {
                alert('error');
            }
        });
    } catch (e) {
        // alert(e);
    }
}

function getAgentCommission(tourCode, tourDate, agentid, price, textid) {
    var servletName = 'DaytourCommissionServlet';
    var servicesName = 'AJAXBean';

    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&tourcode=' + tourCode +
            '&tourdate=' + tourDate +
            '&agentid=' + agentid +
            '&price=' + price +
            '&type=' + 'getagentcom';

    CallGuideComAjax(param, textid);
}

function CallGuideComAjax(param, textid) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {

                if (msg != 'null') {
                    $('#' + textid).val(msg);
                }

            }, error: function(msg) {
                alert('error');
            }
        });
    } catch (e) {
        // alert(e);
    }
}