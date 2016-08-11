/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var showflag = 1;
$(document).ready(function() {
    $('.date').datetimepicker();
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });
    jQuery.curCSS = jQuery.css;
    $('#SearchPaymentHotelTable').dataTable({
        bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": true,
        "iDisplayLength": 10
    });
    //validate date
    $('#DateFrom').datetimepicker().on('dp.change', function(e) {
        $('#PaymentSearchForm').bootstrapValidator('revalidateField', 'InputFromDate');
    });
    $('#DateTo').datetimepicker().on('dp.change', function(e) {
        $('#PaymentSearchForm').bootstrapValidator('revalidateField', 'InputToDate');
    });

    $("#PaymentSearchForm").bootstrapValidator({
        framework: 'bootstrap',
//                container: 'tooltip',
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            InputFromDate: {
                trigger: 'focus keyup change',
                validators: {
                    date: {
                        format: 'DD-MM-YYYY',
                        max: 'InputToDate',
                        message: 'The Date From is not a valid'
                    }
                }
            },
            InputToDate: {
                trigger: 'focus keyup change',
                validators: {
                    date: {
                        format: 'DD-MM-YYYY',
                        min: 'InputFromDate',
                        message: 'The Date To is not a valid'
                    }
                }
            }
        }
    }).on('success.field.fv', function(e, data) {
        if (data.field === 'InputFromDate' && data.fv.isValidField('InputToDate') === false) {
            data.fv.revalidateField('InputToDate');
        }

        if (data.field === 'InputToDate' && data.fv.isValidField('InputFromDate') === false) {
            data.fv.revalidateField('InputFromDate');
        }
    });
    
    $("#searchInvoiceSupplier").keyup(function() {
        searchInvoiceSupplierList($("#searchInvoiceSupplier").val());          
    });
    
    //autocomplete
    $("#InputInvoiceSupCode").keyup(function(event){ 
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left); 
        if($(this).val() === ""){
            $("#InputInvoiceSupId").val("");
            $("#InputInvoiceSupCode").val("");
            $("#InputInvoiceSupName").val("");
            $("#InputAPCode").val("");
        }else{
            if(event.keyCode === 13){
                searchInvoiceSupplierListAuto(this.value); 
            }
        }
    });

    $("#InputInvoiceSupCode").keydown(function(){
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left); 
        if(showflag == 0){
            $(".ui-widget").css("top", -1000);
            showflag=1;
        }
    });

});

function searchAction() {
    var action = document.getElementById('action');
    action.value = 'search';
    document.getElementById('PaymentSearchForm').submit();
}

function DeletePayment(id, no) {
    var paymentID = document.getElementById('paymentID');
    var InputPayNo = document.getElementById('InputPayNo');
    paymentID.value = id;
    InputPayNo.value = no;
    document.getElementById('delCode').innerHTML = "Are you sure to delete payment no : " + no + " ?";
}

function Delete() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('PaymentSearchForm').submit();
}
function convertFormatDates(date){
    if(date === ''){
        return '';
    }
    var newDate = date.toString().split("-");
    var result = new Date(newDate[2], newDate[1] , newDate[0]);
    return newDate[2] + "-" + newDate[1] + "-" + newDate[0];
}

function printPaymentSummaryReport() {
    var paydatefrom = convertFormatDates(document.getElementById("InputFromDate").value);
    var paydateto = convertFormatDates(document.getElementById("InputToDate").value);
    var pvtype = document.getElementById("SelectPvType").value;
    var status = document.getElementById("SelectStatus").value;
    var invSupCode = document.getElementById("InputInvoiceSupCode").value;
//    var strStatus = status.options[status.selectedIndex].text;
//    if ((fromdate === '') || (todate === '')) {
//        validateDate();
//    } else {
        window.open("report.smi?name=PaymentTourHotelSummary&fromdate=" + paydatefrom + "&todate=" + paydateto + "&pvtype=" + pvtype + "&comfirm=" + status + "&invSupCode=" + invSupCode);
//    }
}

function searchInvoiceSupplierList(name) {
    name = generateSpecialCharacter(name);
    var servletName = 'MListItemServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
                    '&servletName=' + servletName +
                    '&servicesName=' + servicesName +
                    '&name=' + name +
                    '&type=' + 'getInvoiceSupplierList';
    CallAjaxGetInvoiceSuplierList(param);
}

function CallAjaxGetInvoiceSuplierList(param) {
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                if(msg !== 'fail'){
                    $('#SearchInvoicSupTable').dataTable().fnClearTable();
                    $('#SearchInvoicSupTable').dataTable().fnDestroy();
                    $("#SearchInvoicSupTable tbody").empty().append(msg);

                    $('#SearchInvoicSupTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });

                    $("#ajaxload").addClass("hidden");
                }

            }, error: function(msg) {
                $("#ajaxload").addClass("hidden");
            }
        });
    } catch (e) {
        $("#ajaxload").addClass("hidden");
    }
}

function searchInvoiceSupplierListAuto(name){
    name = generateSpecialCharacter(name);
    var servletName = 'MListItemServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getInvoiceSupplierListAuto';
    CallAjaxGetInvoiceSuplierListAuto(param);
}

function CallAjaxGetInvoiceSuplierListAuto(param){
    var url = 'AJAXServlet';
    var invArray = [];
    var invIdList = [];
    var invCodeList = [];
    var invNameList = [];
    var invARCodeList = [];
    var invId , invCode , invName , invARCode;
    $("#InputInvoiceSupCode").autocomplete("destroy");
    try {
        $.ajax({
           type: "POST",
           url: url,
           cache: false,
           data: param,
           beforeSend: function() {
              $("#dataload").removeClass("hidden");    
           },
           success: function(msg) {     
               var invJson =  JSON.parse(msg);
               for (var i in invJson){
                   if (invJson.hasOwnProperty(i)){
                       invId = invJson[i].id;
                       invCode = invJson[i].code;
                       invName = invJson[i].name;
                       invARCode = invJson[i].arcode;
                       invArray.push(invCode);
                       invArray.push(invName);
                       invIdList.push(invId);
                       invCodeList.push(invCode);
                       invNameList.push(invName);
                       invARCodeList.push(invARCode);                          
                   }                 
                    $("#dataload").addClass("hidden"); 
               }
               $("#InputInvoiceSupId").val(invId);
               $("#InputInvoiceSupCode").val(invCode);
               $("#InputInvoiceSupName").val(invName);
               $("#InputAPCode").val(invARCode);

               $("#InputInvoiceSupCode").autocomplete({
                   source: invArray,
                   close: function(){
                        $("#InputInvoiceSupCode").trigger("keyup");
                        var invselect = $("#InputInvoiceSupCode").val();
                        for(var i =0;i<invIdList.length;i++){
                            if((invselect==invCodeList[i])||(invselect==invNameList[i])){      
                               $("#InputInvoiceSupId").val(invIdList[i]);
                               $("#InputInvoiceSupCode").val(invCodeList[i]);
                               $("#InputInvoiceSupName").val(invNameList[i]);
                               $("#InputAPCode").val(invARCodeList[i]);
                            }                 
                        }   
                   }
                });

               var invval = $("#InputInvoiceSupCode").val();
               for(var i =0;i<invIdList.length;i++){
                   if(invval==invNameList[i]){
                       $("#InputInvoiceSupId").val(invIdList[i]);
                       $("#InputInvoiceSupCode").val(invCodeList[i]);
                       $("#InputInvoiceSupName").val(invNameList[i]);
                       $("#InputAPCode").val(invARCodeList[i]);
                   }
               }
               if(invIdList.length == 1){
                   showflag = 0;
                   $("#InputInvoiceSupId").val(invIdList[0]);
                   $("#InputInvoiceSupCode").val(invCodeList[0]);
                   $("#InputInvoiceSupName").val(invNameList[0]);
                   $("#InputAPCode").val(invARCodeList[0]);
               }
               var event = jQuery.Event('keydown');
               event.keyCode = 40;
               $("#InputInvoiceSupCode").trigger(event);

            }, error: function(msg) {
               console.log('auto ERROR');
               $("#dataload").addClass("hidden");
            }
        });
    } catch (e) {

    }
}

