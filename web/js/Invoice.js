/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 $(document).ready(function () {
   // Invoice To Modal
   var showflag = 1;
    var ReceiveFromTable = $('#InvToTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    $('#InvToTable tbody').on('click', 'tr', function () {
        $('.collapse').collapse('show');
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
        }
        else {
            ReceiveFromTable.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }
    });
                        
    $("#searchInvoiceFrom").keyup(function(event) {
        if (event.keyCode === 13) {
            if ($("#searchInvoiceFrom").val() == "") {
                // alert('please input data');
            }
            searchCustomerAgentList($("#searchInvoiceFrom").val());
        }
    });
                        
    //autocomplete
    $("#InvTo").keyup(function(event){   
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left); 
        if($(this).val() === ""){
            $("#InvToName").val("");
            $("#InvToAddress").val("");
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
    
   // Sale Staff Modal
   var staffCode = [];       
    $("#SaleStaffTable tr").on('click', function () {//winit
        $("#SaleStaffModal").modal('hide');
        var staff_id = $(this).find(".staff-id").html();
        var staff_code = $(this).find(".staff-code").html();
        var staff_name = $(this).find(".staff-name").html();
        $("#SaleStaffId").val(staff_id);
        $("#SaleStaffCode").val(staff_code);
        $("#SaleStaffName").val(staff_name);
    });
    var staffTable = $('#SaleStaffTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });

    $('#SaleStaffTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
        }
        else {
            staffTable.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }
    });

    $(function () {
        var availableTags = [];
        $.each(staff, function (key, value) {
            availableTags.push(value.code);
            if (!(value.name in availableTags)) {
                availableTags.push(value.name);
            }
        });

        $("#SaleStaffCode").autocomplete({
            source: availableTags,
            close: function (event, ui) {
                $("#SaleStaffCode").trigger('keyup');
            }
        });

        $("#SaleStaffCode").keyup(function () {
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var name = this.value;
            var code = this.value.toUpperCase();
            $("#SaleStaffName").val(null);
            $.each(staff, function (key, value) {
                if (name === value.name.toUpperCase()) {
                    $("#SaleStaffId").val(value.id);
                    $("#SaleStaffCode").val(value.code);
                    $("#SaleStaffName").val(value.name);
                }
                if (value.code.toUpperCase() === code) {
                    $("#SaleStaffId").val(value.id);
                    $("#SaleStaffCode").val(value.code);
                    $("#SaleStaffName").val(value.name);
                }
            }); 
        }); 
    }); 
    
   // Add Row Auto key
   $("#DetailBillableTable").on("keyup", function () {
        var rowAll = $("#DetailBillableTable tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#DetailBillableTable tr").length;
                if (rowIndex == rowAll) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    AddRowDetailBillAble(rowAll);
                }
            }
        });
    });
   // Add Row Detail Bill Able
    var countertable = $("#counterTable").val();
    AddRowDetailBillAble(countertable);
 }); 
 
function validFrom(){
    // Validator Date From and To
    $("#InvoiceForm")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {                
                    InvTo : {
                        trigger: 'focus keyup change',
                        validators: {
                            notEmpty: {
                                message: 'Input Invoice To'
                            }
                        }
                    },
                    InvToName : {
                        trigger: 'focus keyup change',
                        validators: {
                            notEmpty: {
                                message: 'Input Invoice To Name'
                            }
                        }
                    },ARCode : {
                        trigger: 'focus keyup change',
                        validators: {
                            notEmpty: {
                                message: 'Input A/R Code'
                            }
                        }
                    }
                }
            }).on('success.field.fv', function (e, data) {
                
            });
         
}

function AddRowDetailBillAble(row,prod,des,cos){
    if(prod === undefined){
        prod = "";
    }
    if(des === undefined){
        des = "";
    }
    if(cos === undefined){
        cos = "";
    }
    if (!row) {
        row = 1;
    }
    var rows = document.getElementById("DetailBillableTable").getElementsByTagName("tr").length;
    if(rows > 1){
        $("#tr_FormulaAddRow").css("display","none");
    }

    $("#DetailBillableTable tbody").append(
        '<tr>' +
        '<td class="hidden"><input type="text" class="form-control" id="DetailBillId' + row + '" name="DetailBillId' + row + '" value="" > </td>' +
        '<td>'+prod +' </td>' +
        '<td><input type="text" class="form-control" id="BillDescription' + row + '" name="BillDescription' + row + '" value="'+des +'" > </td>' +
        '<td><input type="text" class="form-control" id="InputCost' + row + '" name="InputCost' + row + '" value="'+ cos +'" ></td>' +
        '<td><select id="SelectCurrencyCost' + row + '" name="SelectCurrencyCost' + row + '" class="form-control">'+ select +'</select></td>' +
        '<td>'+cos +' </td>' +
        '<td><input type="checkbox" value="" id="checkUse' + row + '" name="checkUse' + row + '" class="form-control"></td>'+
        '<td>'+ defaultD +'</td>'+   
        '<td> </td>'+
        '<td><input type="text" class="form-control" id="InputAmount' + row + '" name="InputAmount' + row + '" value="" ></td>'+
        '<td><select id="SelectCurrencyAmount' + row + '" name="SelectCurrencyAmount' + row + '" class="form-control">'+ select +'</select></td>'+
        '<td>100000</td>'+
        '<td align="center" ><center><span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill('+row+',\'\')" data-toggle="modal" data-target="#DelDetailBill" >  </span></center>'+
        '</tr>'
    );
    var count = document.getElementById('counterTable');
    count.value = row++;
}

function DeleteDetailBill(rowID,code){
    $("#idDeleteDetailBillable").val(rowID);
    if(code !== ""){
        $("#DeleteDetailBillable").text('Are you sure to delete detail billable Code :'+ code +'..?');
    }else{
        $("#DeleteDetailBillable").text('Are you sure to delete detail billable ?');
    }
}

function DeleteBill() {
    var count = document.getElementById('counterTable');
    var rowId  = document.getElementById('idDeleteDetailBillable');
    var DetailBillId  = $("#DetailBillId"+rowId.value).val();
    if((DetailBillId !== "")&&(DetailBillId !== undefined)){
        rowId.value = DetailBillId ;
        var action = document.getElementById('action');
        action.value = 'delete';
        document.getElementById('InvoiceForm').submit();
        
    }else{
        $("#BillDescription" + rowId.value).parent().parent().remove();
        $('#DelDetailBill').modal('hide');
        console.log("Row 0  : " + count.value );
        if (count.value <= 1) {
            console.log("show button tr_FormulaAddRow : " );
            $("#tr_FormulaAddRow").css("display","block");
        }
        count.value = count.value - 1 ;
    }
}

function DisableVoid(){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('disableVoid').innerHTML = "Are you sure to delete booking other : " +  + " ?";
}

function EnableVoid(){
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

function printInvoice(){  
    window.open("report.smi?name=InvoiceReport");   
}

function printInvoiceNew(){  
    window.open("report.smi?name=InvoiceEmail");   
}

function setBillValue(billto, billname, address, term, pay) {
    $("#InvTo").val(billto);
    $("#InvToName").val(billname);
    if (address == 'null') {
        $("#InvToAddress").val("");
    } else {
        $("#InvToAddress").val(address);
    }
    $("#InvToModal").modal('hide');
}
                    
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
                $('#InvToTable').dataTable().fnClearTable();
                $('#InvToTable').dataTable().fnDestroy();
                $("#InvToTable tbody").empty().append(msg);

                $('#InvToTable').dataTable({bJQueryUI: true,
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
                $("#InvTo_Id").val(billid);
                $("#InvToName").val(billname);
                $("#InvToAddress").val(billaddr);

                $("#InvTo").autocomplete({
                    source: billArray,
                    close: function(){
                         $("#InvTo").trigger("keyup");
                         var billselect = $("#InvTo").val();
                        for(var i =0;i<billListId.length;i++){
                            if((billselect==billListName[i])||(billselect==billListId[i])){      
                                $("#InvTo").val(billListId[i]);
                                $("#InvToName").val(billListName[i]);
                                $("#InvToAddress").val(billListAddress[i]);
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

function searchAction(){
    var searchNo = $("#SearchRefNo").val();
    var servletName = 'InvoiceServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&refNo=' + searchNo +
            '&type=' + 'searchInvoice';
    CallAjaxAdd(param);
}
function CallAjaxAdd(param) {
    var url = 'AJAXServlet';
    $("#ajaxloadsearch").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                var strx   = msg.split('||');
                var array  = [];
                array = array.concat(strx);
                setBillableInvoice(array[0]);
                //MasterReservation Table
                try {
                    $("#MasterReservation tbody").append(array[1]);

                     $("#ajaxloadsearch").addClass("hidden");
                } catch (e) {
                    alert(e);
                }
            }, error: function (msg) {
                 $("#ajaxloadsearch").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}    

function setBillableInvoice(data){
    var strx   = data.split(',');
    var array  = [];
    array = array.concat(strx);
    $('#InvTo').val(array[0]);         
    $('#InvToName').val(array[1]);
    $('#InvToAddress').val(array[2]);
    $('#TermPay').val(array[3]); 
    $('#SaleStaffId').val(array[4]);
    $('#SaleStaffName').val(array[5]);
    $('#SaleStaffCode').val(array[6]);
}

function addInvoiceDetail(rowId){
    var countTable = $("#counter").val();
    var count = 1;
    $('#MasterReservation > tbody  > tr').each(function() {
        if(count === rowId){
            alert("Row : " + rowId);
            var prod = $(this).find("td").eq(1).html();
            var des = $(this).find("td").eq(2).html();
            var cos = $(this).find("td").eq(3).html();
            var cur = $(this).find("td").eq(5).html();
            alert(prod + " " + des + " " + cos + " " + cur);
            $("#DetailBillableTable tr:last").remove();
            AddRowDetailBillAble(countTable,prod,des,cos);
            
        }
        count+= 1;       
    });
    countTable +=1;
    AddRowDetailBillAble(countTable);
}