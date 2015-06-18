/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var controlGuide;
var controlAgent;

$(document).ready(function () {
    Selectize.define( 'clear_selection', function ( options ) {
    var self = this;
    self.plugins.settings.dropdown_header = {
        title: 'Clear Selection'
    };
    this.require( 'dropdown_header' );
    self.setup = (function () {
        var original = self.setup;
        return function () {
            original.apply( this, arguments );
            this.$dropdown.on( 'mousedown', '.selectize-dropdown-header', function ( e ) {
                self.setValue( '' );
                self.close();
                self.blur();
                return false;
            });
        };
    })();
});

//Number
    $(".datemask").mask('0000-00-00', {reverse: true});
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
    dataGuide = guideName ;
//    console.log('dataGuide :'+dataGuide);
    for (var i = 1; i <= tableLength; i++) {
        var name = "#selectGuide-" + i;
        console.log("name = " + name);
       
        $(name).selectize({
            removeItem: '',
            sortField: 'text' ,
            create: false ,
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
    $('#DateFrom').datetimepicker().on('dp.change', function (e) {
            $('#searchDaytourCommissionForm').bootstrapValidator('revalidateField', 'InputDateFrom');
    });
    $('#DateTo').datetimepicker().on('dp.change', function (e) {
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
                                format: 'YYYY-MM-DD',
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
                                format: 'YYYY-MM-DD',
                                min: 'InputDateFrom',
                                message: 'The Date To is not a valid'
                            }
                        }
                    }
                }
            }).on('success.field.fv', function (e, data) {
                if (data.field === 'InputDateFrom' && data.fv.isValidField('InputDateTo') === false) {
                    data.fv.revalidateField('InputDateTo');
                }

                if (data.field === 'InputDateTo' && data.fv.isValidField('InputDateFrom') === false) {
                    data.fv.revalidateField('InputDateFrom');
                }
            });
    
});

function verifyValueToGuide(){
//    guideCheck();
$("#GuideModal").modal('show');
}
function verifyValueToAgent(){
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
    $("#CommissionTable tbody").find("tr").each(function () {
        var checkbox = $(this).find("td.edited").children();
        if ($(checkbox).is(":checked")) {
            var cloneTr = $(this).clone();
            cloneTr.find('input,select,span').each(function () {
                $(this).removeClass('hidden');
                if($(this).attr('name')==="AgentName-"){
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
    if($("#CommissionTable tbody").find("tr").length === 1 && $("#inputDateFrom").val() === "" && $("#inputDateTo").val() === ""){
        console.log("submit search");
        $("#searchDaytourCommissionForm").submit();
    }else {
        console.log("submit save");
        $("#saveDaytourCommissionForm").submit(function(e){});
       
    }  
    
}

function printGuideCommission() {
    var guidePrintFrom = document.getElementById("guidePrintFrom").value;
    var guidePrintTo = document.getElementById("guidePrintTo").value;
    var selGuideReport = document.getElementById("selGuideReport").value;
    window.open("report.smi?name=GuideCommission&startdate=" + guidePrintFrom + "&enddate=" + guidePrintTo+"&GuideID="+selGuideReport);
}

function printAgentCommission() {
    var agentPrintFrom = document.getElementById("agentPrintFrom").value;
    var agentPrintTo = document.getElementById("agentPrintTo").value;
    var selAgentReport = document.getElementById("selAgentReport").value;
    window.open("report.smi?name=AgentCommission&startdate=" + agentPrintFrom + "&enddate=" + agentPrintTo+"&agentID="+selAgentReport);
}

function getGuideCommission(tourcode,textid){
    var servletName = 'DaytourCommissionServlet';
    var servicesName = 'AJAXBean';
    var tourCode = tourcode;
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&tourcode=' + tourCode +
            '&type=' + 'getguidecom';
    CallGuideComAjax(param,textid);
}

function CallGuideComAjax(param,textid) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
               if(msg != 'null'){
                    $('#'+textid).val(msg);
               }
              
            }, error: function(msg) {
                alert('error');
            }
        });
    } catch (e) {
       // alert(e);
    }
}

function getAgentCommission(tourCode,tourDate,agentid,price,textid){
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

    CallGuideComAjax(param,textid);
}

function CallGuideComAjax(param,textid) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
               
               if(msg != 'null'){
                    $('#'+textid).val(msg);
               }
              
            }, error: function(msg) {
                alert('error');
            }
        });
    } catch (e) {
       // alert(e);
    }
}