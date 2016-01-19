/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
$(document).ready(function () {
    $(".money").mask('00,000,000.00', {reverse: true});
     
    $(".numerical").on('input', function() { 
        var value=$(this).val().replace(/[^0-9.,]*/g, '');
        value=value.replace(/\.{2,}/g, '.');
        value=value.replace(/\.,/g, ',');
        value=value.replace(/\,\./g, ',');
        value=value.replace(/\,{2,}/g, ',');
        value=value.replace(/\.[0-9]+\./g, '.');
        $(this).val(value);
    });
    
   // Invoice To Modal
   var showflag = 1;
    var ReceiveFromTable = $('#AgentTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    $('#AgentTable tbody').on('click', 'tr', function () {
        $('.collapse').collapse('show');
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
        }
        else {
            ReceiveFromTable.$('tr.row_selected').removeClass('row_selected');
            var staff_code = $(this).find("td").eq(1).html();
//            alert("Herree" + staff_code);
            $("#InvToName").val(staff_code);
            $(this).addClass('row_selected');
        }
    });
    
    //autocomplete
    $("#InvTo").keyup(function(event){   
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left); 
        if($(this).val() === ""){
            $("#InvToId").val("");
            $("#InvToName").val("");
//            $("#InvToAddress").val("");
        }else{
            if(event.keyCode === 13){
                searchCustomerAutoList(this.value); 
            }
        }
    });
    $("#InvTo").keydown(function(){
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left); 
            if(showflag == 0){
                $(".ui-widget").css("top", -1000);
                showflag=1;
            }
    });
    
    $('.fromdate').datetimepicker().change(function(){                          
        checkFromDateField();
    });
    $('.todate').datetimepicker().change(function(){                          
        checkToDateField();
    });
    
    $("#searchInvoiceFrom").keyup(function(event) {
        if (event.keyCode === 13) {
            if ($("#searchInvoiceFrom").val() == "") {
                // alert('please input data');
            }
            searchCustomerAgentList($("#searchInvoiceFrom").val());
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
                $('#AgentTable').dataTable().fnClearTable();
                $('#AgentTable').dataTable().fnDestroy();
                $("#AgentTable tbody").empty().append(msg);

                $('#AgentTable').dataTable({bJQueryUI: true,
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
    $("#InvTo").val(billto);
    $("#InvName").val(billname);
    $("#AgentModal").modal('hide');
}

function searchCustomerAutoList(name){
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getAutoListBillto';
    CallAjaxAuto(param);
}

function CallAjaxAuto(param){
     var url = 'AJAXServlet';
     var billArray = [];
     var billListId= [];
     var billListName= [];
     var billListAddress= [];
     var billid , billname ,billaddr;
     $("#InvTo").autocomplete("destroy");
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
                var billJson =  JSON.parse(msg);
                for (var i in billJson){
                    if (billJson.hasOwnProperty(i)){
                        billid = billJson[i].id;
                        billname = billJson[i].name;
                        billaddr = billJson[i].address;
                        billArray.push(billid);
                        billArray.push(billname);
                        billListId.push(billid);
                        billListName.push(billname);
                        billListAddress.push(billaddr);
                    }                 
                     $("#dataload").addClass("hidden"); 
                }
                $("#InvToId").val(billid);
//                $("#ARCode").val(billid);
                $("#InvToName").val(billname);
//                $("#InvToAddress").val(billaddr);

                $("#InvTo").autocomplete({
                    source: billArray,
                    close: function(){
                         $("#InvTo").trigger("keyup");
                         var billselect = $("#InvTo").val();
                        for(var i =0;i<billListId.length;i++){
                            if((billselect==billListName[i])||(billselect==billListId[i])){      
                                $("#InvTo").val(billListId[i]);
//                                $("#ARCode").val(billListId[i]);
                                $("#InvToName").val(billListName[i]);
//                                $("#InvToAddress").val(billListAddress[i]);
                            }                 
                        }   
                    }
                 });

                var billval = $("#InvTo").val();
                for(var i =0;i<billListId.length;i++){
                    if(billval==billListName[i]){
                        $("#InvTo").val(billListId[i]);
                    }
                }
                if(billListId.length == 1){
                    showflag = 0;
                    $("#InvTo").val(billListId[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $("#InvTo").trigger(event);

            }, error: function(msg) {
                console.log('auto ERROR');
                $("#dataload").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

 function validForm(){
//     alert("1");
      $("#SearchInvoiceForm")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    FromDate: {
                        trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date From is required'
                                },
                                date: {
                                    format: 'YYYY-MM-DD',
                                    max: 'ToDate',
                                    message: 'The Date From is not a valid'
                                }
                            }
                    },
                    ToDate: {
                        trigger: 'focus keyup change',
                            validators: {
                                notEmpty: {
                                    message: 'The Date To is required'
                                },
                                date: {
                                    format: 'YYYY-MM-DD',
                                    min: 'FromDate',
                                    message: 'The Date To is not a valid'
                                }
                            }
                    }
                }
            }).on('success.field.fv', function (e, data) {
                if (data.field === 'FromDate' && data.fv.isValidField('ToDate') === false) {
                        data.fv.revalidateField('ToDate');
                    }

                    if (data.field === 'ToDate' && data.fv.isValidField('FromDate') === false) {
                        data.fv.revalidateField('FromDate');
                    }
            });
            
            $('#DateFrom').datetimepicker().on('dp.change', function (e) {
                $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'FromDate');
            });
            $('#DateTo').datetimepicker().on('dp.change', function (e) {
                $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'ToDate');
            });
            
 }
 
 function search(){
    var action = document.getElementById('action');
    action.value = 'search';
//    validForm();
//    document.getElementById('SearchInvoiceForm').submit();
 }
function DisableInvoice(){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('disableVoid').innerHTML = "Are you sure to delete booking other : " +  + " ?";
}

function EnableInvoice(){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('enableVoid').innerHTML = "Are you sure to enable booking other : " + code + " ?";
}

function Enable() {
    var action = document.getElementById('action');
    action.value = 'enable';
    document.getElementById('OtherForm').submit();
}

function Disable() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('OtherForm').submit();
}

function printInvoiceSummary(){
    var from = $('#FromDate').val();
    var to = $('#ToDate').val();
    var department = $('#Department').val();
    var type = $('#Type').val();
    var agent = $('#InvTo').val();
    var status = $('#status').val();
//    windsow.open("report.smi?name=InvoiceSummary");
    if(department === 'WendyOutbound'){
        department = 'Wendy,Outbound'
    }
    if((from === '') || (to === '')){
        validateDate();
    } else {
        window.open("report.smi?name=InvoiceSummary"+"&fromdate="+from+"&todate="+to+"&department="+department+"&type="+type+"&agent="+agent+"&status="+status);  
    }   
}

function checkFromDateField(){
    var inputFromDate = document.getElementById("FromDate");
    if(inputFromDate.value === ''){          
        $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'FromDate');
        $("#btnPrint").addClass("disabled");         
    } else {
        $("#btnPrint").removeClass("disabled");
        checkDateValue("from","");
    }      
}
    
function checkToDateField(){
    var InputToDate = document.getElementById("ToDate");
    if(InputToDate.value === ''){
        $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'ToDate');
        $("#btnPrint").addClass("disabled");  
    }else{
        $("#btnPrint").removeClass("disabled");
        checkDateValue("to","");
    }               
}

function checkDateValue(date){
    var inputFromDate = document.getElementById("FromDate");
    var InputToDate = document.getElementById("ToDate");
    if((inputFromDate.value !== '') && (InputToDate.value !== '')){
        var fromDate = (inputFromDate.value).split('-');
        var toDate = (InputToDate.value).split('-');      
        if((parseInt(fromDate[0])) > (parseInt(toDate[0]))){
            validateDate(date,"over");
        }else if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))){
            validateDate(date,"over");
        }else if(((parseInt(fromDate[0])) >= (parseInt(toDate[0]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[2])) > (parseInt(toDate[2]))){
            validateDate(date,"over");
        }else{
            $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'FromDate');
            $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'ToDate');
        }           
    }
}

function validateDate(date,option){
    if(option === 'over'){
        if(date === 'from'){
            $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'FromDate');
            $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'ToDate');
        }
        if(date === 'to'){
            $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'FromDate');
            $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'ToDate');
        }           
        $("#btnPrint").addClass("disabled");
    } else {
        $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'FromDate');
        $('#SearchInvoiceForm').bootstrapValidator('revalidateField', 'ToDate');
        $("#btnPrint").addClass("disabled");
    }
}