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

//    var $selectAgent = $('#SelectAgent').selectize({
//        create: false,
//        sortField: 'text'
//    });
//    controlAgent = $selectAgent[0].selectize;

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
//    console.log("table length " + tableLength);

//    var dataGuide = [];
//    dataGuide = guideName;
//    console.log('dataGuide :'+dataGuide);
//    for (var i = 1; i <= tableLength; i++) {
//        var name = "#selectGuide-" + i;
//        console.log("name = " + name);
//
//        $(name).selectize({
//            removeItem: '',
//            sortField: 'text',
//            create: false,
//            dropdownParent: 'body',
//            plugins: {
//                'clear_selection': {}
//            }
////            onDropdownOpen:  function() {
////                arguments[0][0].style.width = "250%";
////	    }
//        });
//
//    }

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
    
    $('table.paginated').each(function() {
        var currentPage = 0;
        var numPerPage = 10;
        var $table = $(this);
        $table.bind('repaginate', function() {
            $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
        });
        $table.trigger('repaginate');
        var numRows = $table.find('tbody tr').length;
        var numPages = Math.ceil(numRows / numPerPage);
        var $pager = $('<div class="col-xs-12 text-right" id="pageNo"><font style="color: #499DD5"></font>&nbsp;</div>');
        var $br = $('<div class="col-xs-12"><br></div>');

        for (var page = 0; page < numPages; page++) {
            var isShowPage = (page < 5 ? "" : "hidden");
            if(page === 0){
                $('<font style="color: #499DD5" id="noFirst" onclick="changeColor(\'noFirst\',\'first\',\''+page+'\')"><span class="page-number glyphicon"></span></font>').text(" " + "First" + "  ").bind('click', {
                newPage: page
                }, function(event) {
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                    $(this).css("color", "#AFEEEE");
                }).appendTo($pager).addClass('clickable');                                      

                if(numPages > 1){
                    for(var i=0; i<numPages; i++){
                        var isHidden = (i === 0 ? "" : "hidden");
                        $('<font style="color: #499DD5" id="noPrevious'+i+'" onclick="changeColor(\'noPrevious'+i+'\',\'previous\',\''+i+'\')" class="'+isHidden+'"><span class="page-number glyphicon"></span></font>').text(" " + "Previous" + "  ").bind('click', {
                        newPage: i
                        }, function(event) {
                            currentPage = event.data['newPage'];
                            $table.trigger('repaginate');
                            $(this).addClass('active').siblings().removeClass('active');
    //                        $(this).css("color", "#AFEEEE");
                        }).appendTo($pager).addClass('clickable');
                    }
                }    
            }

            $('<font style="color: #499DD5" id="no' + page + '" onclick="changeColor(\'no'+page+'\',\'no\',\''+page+'\')" class="'+isShowPage+'"><span class="page-number glyphicon"></span></font>').text(" " + (page + 1) + "  ").bind('click', {
                newPage: page
            }, function(event) {                  
                currentPage = event.data['newPage'];
                $table.trigger('repaginate');
                $(this).addClass('active').siblings().removeClass('active');
                $(this).css("color", "#AFEEEE");
            }).appendTo($pager).addClass('clickable');

            if(page === (numPages - 1)){
                if(numPages > 1){
                    for(var i=0; i<numPages; i++){
                        var isHidden = (i === 1 ? "" : "hidden");
                        $('<font style="color: #499DD5" id="noNext'+i+'" onclick="changeColor(\'noNext'+i+'\',\'next\',\''+i+'\')" class="'+isHidden+'"><span class="page-number glyphicon"></span></font>').text(" " + "Next" + "  ").bind('click', {
                        newPage: i
                        }, function(event) {
                            currentPage = event.data['newPage'];
                            $table.trigger('repaginate');
                            $(this).addClass('active').siblings().removeClass('active');
    //                        $(this).css("color", "#AFEEEE");
                        }).appendTo($pager).addClass('clickable');
                    }
                }    

                $('<font style="color: #499DD5" id="noLast" onclick="changeColor(\'noLast\',\'last\',\''+page+'\')"><span class="page-number glyphicon"></span></font>').text(" " + "Last" + "  ").bind('click', {
                newPage: page
                }, function(event) {
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                    $(this).css("color", "#AFEEEE");
                }).appendTo($pager).addClass('clickable');                                     
            }
        }
        $br.insertAfter($table).addClass('active');
        $pager.insertAfter($table).find('span.page-number:first').addClass('active');
        document.getElementById("pageNo").style.cursor="pointer";
        document.getElementById("page").value = numPages-1;
        document.getElementById("currentPage").value = 0;
        $("#noFirst").css("color", "#AFEEEE");
        $("#no0").css("color", "#AFEEEE");
        $("#noPrevious0").css("color", "#AFEEEE");
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
    var agent_id = $("#agent_id").val();
    $("#filterAgent").val(agent_id);
    $("#dateFromSearch").val(dateFrom);
    $("#dateToSearch").val(dateTo);
    $("#filterGuide").val(controlGuide.getValue());
    
    var count = $("#CommissionTable tr").length;
    var row = 1;
    for(var i = 1; i <= count; i++){
        if($("#hasEdit-" + i).is(':checked')){
            var daytourBookingId = $("#daytourBookingId-" + i).val();
            var guideName = $("#GuideName-" + i).attr("valHidden");
            var guideCom = $("#guideComm-" + i).val();
            var guideRemark = $("#guideRemark-" + i).val();
            var agentName = $("#AgentName-" + i).attr("valHidden");
            var agentComm = $("#agentComm-" + i).val();
            var agentRemark = $("#agentRemark-" + i).val();
            
            var rowTr = '<tr>' +
                        '<td class="sorting_' + row + '"><input type="text" id="daytourBookingId-' + row + '" name="daytourBookingId-' + row + '" value="' + daytourBookingId + '"></td>' +
                        '<td class="selectGuide form-group ">' +
                        '<input type="text" class="form-control guidename ui-autocomplete-input" id="GuideName-' + row + '" name="GuideName-' + row + '" valhidden="" value="' + guideName + '" autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true">' + 
                        '</td>' +
                        '<td class="form-group ">' +
                        '<input type="text" class="form-control decimal guidecom" id="guideComm-' + row + '" name="guideComm-' + row + '" value="' + guideCom + '" maxlength="14" style="text-align: right;">' +
                        '</td>' +
                        '<td class="form-group ">' +
                        '<input type="text" class="form-control" id="guideRemark-' + row + '" name="guideRemark-' + row + '" value="' + guideRemark + '" maxlength="255" >' +
                        '</td>' +
                        '<td class="form-group ">' +
                        '<input type="text" class="form-control agentname" id="AgentName-' + row + '" name="AgentName-' + row + '" valhidden="" value="' + agentName + '" >' + 
                        '</td>' +
                        '<td class="form-group ">' +
                        '<input type="text" class="form-control decimal agentcom" id="agentComm-' + row + '" name="agentComm-' + row + '" value="' + agentComm + '" maxlength="14" style="text-align: right;">' +
                        '</td>' +
                        '<td class="agentRemark form-group ">' +
                        '<input type="text" class="form-control" id="agentRemark-' + row + '" name="agentRemark-' + row + '" value="' + agentRemark + '" maxlength="255" >' +
                        '</td>' +
                        '<td class="hidden edited ">' +
                        '<input type="checkbox" class="form-control" id="hasEdit-' + row + '" name="hasEdit-' + row + '" checked="checked">' +
                        '</td>'
                    '</tr>';
            
            $("#EditTable tbody").append(rowTr);
            row += 1;
        }
    }
//    alert($("#GuideName-3").attr("valHidden"));
//    
//    var counter = 1;
//    $("#CommissionTable tbody").find("tr").each(function(){
//        var checkbox = $(this).find("td.edited").children();
//        if ($(checkbox).is(":checked")) {
//            var cloneTr = $(this).clone();
//            cloneTr.find('input,select,span').each(function() {
//                $(this).removeClass('hidden');
//                if ($(this).attr('name') === "AgentName-") {
//                    $(this).val($(this).attr("valHidden"));
//                    
//                }
//                if ($(this).attr('name') === "GuideName-") {
//                    $(this).val($(this).attr("valHidden"));
//                    
//                }
//                $(this).attr({
//                    name: $(this).attr('name') + counter
//                });
//            });
//            $("#EditTable tbody").append(cloneTr);
//            counter++;
//        }
//    });

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
//        $("#saveDaytourCommissionForm").submit(function(e) {
//        });

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
    var selAgentReport = document.getElementById("agent_id").value;
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

function getPageNo(type){
    var page = parseInt($("#page").val())+1;
    var currentPage = parseInt($("#currentPage").val());
    var row = 0;
    var start = 0;
    var end = page-1;
    if(type === 'previous'){
        row = (currentPage !== start ? currentPage-1 : start);

    }else if(type === 'next'){
        row = (currentPage !== end ? currentPage+1 : end);
    }
    $("#row").val(row);
    return row;
}

function changeColor(id,type,page){
    var pageNo = parseInt($("#page").val())+1;
    for(var i=0; i<pageNo; i++){
        $("#no"+i).css("color", "#499DD5");
        $("#noPrevious"+i).css("color", "#499DD5");
        $("#noNext"+i).css("color", "#499DD5");
        $("#noFirst").css("color", "#499DD5");                
        $("#noLast").css("color", "#499DD5");

        $("#no"+i).addClass("hidden");
        $("#noPrevious"+i).addClass("hidden");
        $("#noNext"+i).addClass("hidden");
    }

    var pageShow = parseInt(page);
    if(pageShow > 2 && pageShow < pageNo - 2){
        for(var i=pageShow; i >= pageShow-2; i--){
           $("#no"+i).removeClass("hidden"); 
        }
        for(var i=pageShow; i <= pageShow+2; i++){
           $("#no"+i).removeClass("hidden");  
        }

    }else{
        if(pageShow <= 2){
            for(var i=0; i < 5; i++){
                $("#no"+i).removeClass("hidden");  
            } 

        }else if(pageShow <= pageNo-1){
            for(var i=pageNo-5; i < pageNo; i++){
                $("#no"+i).removeClass("hidden");  
            } 
        }

    }    

    var previous = ((parseInt(page) === 0 ? 0 : parseInt(page)-1));
    $("#noPrevious"+(previous)).removeClass("hidden");

    var next = ((parseInt(page) === pageNo-1 ? pageNo-1 : parseInt(page)+1));
    $("#noNext"+(next)).removeClass("hidden");

    $("#no"+page).css("color", "#AFEEEE");

    if(parseInt(page) === 0){
        $("#noFirst").css("color", "#AFEEEE");
        $("#noPrevious"+(previous)).css("color", "#AFEEEE");

    }else if(parseInt(page) === pageNo-1){
        $("#noLast").css("color", "#AFEEEE");
        $("#noNext"+(next)).css("color", "#AFEEEE");
    }

    if(pageNo-1 === 0){
        $("#noFirst").css("color", "#499DD5");                
        $("#noLast").css("color", "#499DD5");

        if(type === 'first'){
            $("#noFirst").css("color", "#AFEEEE");

        }else if(type === 'last'){
            $("#noLast").css("color", "#AFEEEE");

        }
    }

    $("#currentPage").val(page);

}