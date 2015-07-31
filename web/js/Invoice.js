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
            $("#InvToId").val("");
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
    
    $('#TotalNet').ready(function () {
        CalculateGrandTotal('');
    });
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
        '<td><input type="checkbox" value="" id="checkUse' + row + '" name="checkUse' + row + '"  onclick="calculateGross('+row+')"></td>'+
        '<td>'+ defaultD +'</td>'+ 
        '<td class="hidden"><input type="text" class="form-control" id="InputVatTemp' + row + '" name="InputVatTemp' + row + '" value="'+ defaultD +'" ></td>'+
        '<td><input type="text" class="form-control" id="InputGross' + row + '" name="InputGross' + row + '" value="" ></td>'+
        '<td><input type="text" class="form-control" id="InputAmount' + row + '" name="InputAmount' + row + '" value="" onfocusout="CalculateGrandTotal(' + row + ')"></td>'+
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
//            alert("Row : " + rowId);
            var prod = $(this).find("td").eq(1).html();
            var des = $(this).find("td").eq(2).html();
            var cos = $(this).find("td").eq(3).html();
            var cur = $(this).find("td").eq(5).html();
//            alert(prod + " " + des + " " + cos + " " + cur);
            $("#DetailBillableTable tr:last").remove();
            AddRowDetailBillAble(countTable,prod,des,cos);
            
        }
        count++;       
    });
    countTable++;
    $("#counter").val(countTable);
    AddRowDetailBillAble(countTable);
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

function ThaiBaht(Number){
    for (var i = 0; i < Number.length; i++){
            Number = Number.replace (",", ""); //ไม่ต้องการเครื่องหมายคอมมาร์
            Number = Number.replace (" ", ""); //ไม่ต้องการช่องว่าง
            Number = Number.replace ("บาท", ""); //ไม่ต้องการตัวหนังสือ บาท
            Number = Number.replace ("฿", ""); //ไม่ต้องการสัญลักษณ์สกุลเงินบาท
    }
    var TxtNumArr = new Array ("ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า", "สิบ");
    var TxtDigitArr = new Array ("", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน");
    var BahtText = "";
    //ตรวจสอบตัวเลข isNaN == true ถ้าเป็นข้อความ == false ถ้าเป็นตัวเลข
    if (isNaN(Number)){
            return "ข้อมูลนำเข้าไม่ถูกต้อง";
    }else{
        //ตรวสอบอีกสักครั้งว่าตัวเลขมากเกินความต้องการหรือเปล่า
        if ((Number - 0) > 9999999999.9999){
            return "ข้อมูลนำเข้าเกินขอบเขตที่ตั้งไว้";
        }else{
            Number = Number.split (".");
            if (Number[1].length > 0) {
                Number[1] = Number[1].substring(0, 2);
            }
            var NumberLen = Number[0].length - 0;
            for(var i = 0; i < NumberLen; i++){
                var tmp = Number[0].substring(i, i + 1) - 0;
                if (tmp != 0){
                    if ((i == (NumberLen - 1)) && (tmp == 1)){
                        BahtText += "เอ็ด";
                    }else if ((i == (NumberLen - 2)) && (tmp == 2)){
                        BahtText += "ยี่";
                    }else if ((i == (NumberLen - 2)) && (tmp == 1)) {
                        BahtText += "";
                    }else {
                        BahtText += TxtNumArr[tmp];
                    }
                    BahtText += TxtDigitArr[NumberLen - i - 1];
                }
            }
            BahtText += "บาท";
            if ((Number[1] == "0") || (Number[1] == "00")) {
                BahtText += "ถ้วน";
            } else {
                DecimalLen = Number[1].length - 0;
                for (var i = 0; i < DecimalLen; i++) {
                    var tmp = Number[1].substring(i, i + 1) - 0;
                    if (tmp != 0) {
                        if ((i == (DecimalLen - 1)) && (tmp == 1)) {
                            BahtText += "เอ็ด";
                        } else if ((i == (DecimalLen - 2)) && (tmp == 2)) {
                            BahtText += "ยี่";
                        } else if ((i == (DecimalLen - 2)) && (tmp == 1)) {
                            BahtText += "";
                        } else {
                            BahtText += TxtNumArr[tmp];
                        }
                        BahtText += TxtDigitArr[DecimalLen - i - 1];
                    }
                }
                BahtText += "สตางค์";
            }
            return BahtText;
        }
    }
}

function calculateGross(row){
    var amount = document.getElementById('InputAmount'+row).value;
    var gross = document.getElementById('InputGross'+row).value;
//    var vatData = document.getElementById("DetailBillableTable").rows[row].cells[7].innerHTML;
    var varTemp = document.getElementById('InputVatTemp'+row).value;
    var vatDefaultData = parseFloat(varTemp);

    amount = amount.replace(/,/g,"");
    var grossTotal = parseFloat(amount);

    if((gross === '')){
        grossTotal = (amount*100)/(100+vatDefaultData);
        document.getElementById('InputGross'+row).value = formatNumber(grossTotal);
        document.getElementById("DetailBillableTable").rows[row].cells[7].innerHTML = vatDefaultData;
    } else {
        document.getElementById('InputGross'+row).value = '';
        document.getElementById("DetailBillableTable").rows[row].cells[7].innerHTML = '';
    }
//    CalculateGrossTotal('',row);   
}

function CalculateGrandTotal(id){
    var count = parseInt(document.getElementById('counterTable').value);
    var i;
    var grandTotal = 0;
    if((id!==null) || (id!=='') ){
        for(i=0;i<count+1;i++){
            var amount = document.getElementById("InputAmount" + i);

            if (amount !== null){
                var value = amount.value;

                if(value !== ''){
                    value = value.replace(/,/g,"");
                    var total = parseFloat(value);
                    grandTotal += total;
                    document.getElementById('InputAmount' + i).value = formatNumber(total);
                }
            }    
        }
        document.getElementById('TotalNet').value = formatNumber(grandTotal);
        var bathString = ThaiBaht(formatNumber(grandTotal));
//            alert("Thai Text :" + bathString);
        document.getElementById('TextAmount').value = bathString;
        $( ".numerical" ).on('input', function() { 
            var value=$(this).val().replace(/[^0-9.,]*/g, '');
            value=value.replace(/\.{2,}/g, '.');
            value=value.replace(/\.,/g, ',');
            value=value.replace(/\,\./g, ',');
            value=value.replace(/\,{2,}/g, ',');
            value=value.replace(/\.[0-9]+\./g, '.');
            $(this).val(value);
        });
    }
}

function clearScreenInvoice(){
    $('#SearchRefNo, #InvNo, #InputInvDate, #InputDueDate, #InvTo, #InvToName, #InvToAddress, #SaleStaffId, #SaleStaffCode, #SaleStaffName, #ARCode, #Remark, #TextAmount, #TotalNet ').val('');
    $('#Grpup').attr('checked', false);
    $('input[name="Department"]')[0].checked = false;
    $('input[name="Department"]')[1].checked = false;
    $('input[name="Department"]')[2].checked = false;
    $('#MasterReservation > tbody  > tr').each(function() {
        $(this).remove();
    });
    $('#DetailBillableTable > tbody  > tr').each(function() {
        $(this).remove();
    });
    $('#counterTable').val('1');
    AddRowDetailBillAble();
}

function checkVatInvoiceAll(){
    var row = document.getElementById('counterTable').value;
    var check = 0;
    var unCheck = 0;
    for(var i=1 ; i<= row;i++){          
        var isVatCheck = document.getElementById("checkUse"+i);
        if(isVatCheck !== null && isVatCheck !== ''){
            if(document.getElementById("checkUse"+i).checked){
               check++;
            } else {
               unCheck++;
            }
        }   
    }

    if(check > unCheck){
        if(unCheck !== 0){
            for(var i=0;i<=row;i++){
                var isVatCheck = document.getElementById("checkUse"+i);
                if(isVatCheck !== null && isVatCheck !== ''){
                    if(document.getElementById("checkUse"+i).checked){

                    } else { 
                        document.getElementById("checkUse"+i).checked = true;
                        var amountChk = document.getElementById('InputAmount'+i);
                        if(amountChk !== null && amountChk !== ''){
                            var amount = document.getElementById('InputAmount'+i).value;
                            var gross = document.getElementById('InputGross'+i).value;
//                            var vatData = document.getElementById("DetailBillableTable").rows[i].cells[7].innerHTML;

                            var vatTemp = document.getElementById('InputVatTemp'+i).value;
                            var vatDefaultData = parseFloat(vatTemp);
                            console.log("Vat : " + vatDefaultData);
                            amount = amount.replace(/,/g,"");
                            var grossTotal = parseFloat(amount);

                            if((gross === '')){
                                grossTotal = (amount*100)/(100+vatDefaultData);
                                document.getElementById('InputGross'+i).value = formatNumber(grossTotal);
    //                            document.getElementById('InputVat'+i).value = vatDefaultData;
                                document.getElementById("DetailBillableTable").rows[i].cells[7].innerHTML = vatDefaultData;
                            } else {
                                document.getElementById('InputGross'+i).value = '';
    //                            document.getElementById('InputVat'+i).value = '';
                            document.getElementById("DetailBillableTable").rows[i].cells[7].innerHTML = '';
                            }
                        }
                    }    
                }   
            }
        }
    }

    if(check < unCheck){
        for(var i=0;i<=row;i++){
            var isVatCheck = document.getElementById("checkUse"+i);
            if(isVatCheck !== null && isVatCheck !== ''){
                document.getElementById("checkUse"+i).checked = false;
//                document.getElementById("InputVat"+i).value = '';
                document.getElementById("DetailBillableTable").rows[i].cells[7].innerHTML = "";
                document.getElementById("InputGross"+i).value = '';
            }   
        }
    }

    if(check === 0 && unCheck !== 0){
        for(var i=0;i<=row;i++){
            var isVatCheck = document.getElementById("checkUse"+i);
            if(isVatCheck !== null && isVatCheck !== ''){
                if(document.getElementById("checkUse"+i).checked){

                } else { 
                    document.getElementById("checkUse"+i).checked = true;
                    var amountChk = document.getElementById('InputAmount'+i);                                     
                    if(amountChk !== null && amountChk !== ''){
                        var amount = document.getElementById('InputAmount'+i).value;
                        var gross = document.getElementById('InputGross'+i).value;
//                        var vatData = document.getElementById("DetailBillableTable").rows[i].columns[7].innerHTML;
//                        var vatDefaultData = parseFloat(document.getElementById('InputVat'+i).value);
//                        var vatData = document.getElementById("DetailBillableTable").rows[i].cells[7].innerHTML;
                        var vatTemp = document.getElementById('InputVatTemp'+i).value;
                        var vatDefaultData = parseFloat(vatTemp);
                        amount = amount.replace(/,/g,"");
                        var grossTotal = parseFloat(amount);
                        if((gross === '')){
                            grossTotal = (amount*100)/(100+vatDefaultData);
                            document.getElementById('InputGross'+i).value = formatNumber(grossTotal);
//                            document.getElementById('InputVat'+i).value = vatDefaultData;
                            document.getElementById("DetailBillableTable").rows[i].cells[7].innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('InputGross'+i).value = '';
//                            document.getElementById('InputVat'+i).value = '';
                            document.getElementById("DetailBillableTable").rows[i].cells[7].innerHTML = '';
                        }
                    }
                }    
            }    
        }
    } 

    if(check !== 0 && unCheck === 0){
        for(var i=0;i<=row;i++){
            var isVatCheck = document.getElementById("checkUse"+i);
            if(isVatCheck !== null && isVatCheck !== ''){
                document.getElementById("checkUse"+i).checked = false;
//                document.getElementById("InputVat"+i).value = '';
                document.getElementById("DetailBillableTable").rows[i].cells[7].innerHTML = '';
                document.getElementById("InputGross"+i).value = '';
            }   
        }
    }

    if(check === unCheck){
        for(var i=0;i<=row;i++){
            var isVatCheck = document.getElementById("checkUse"+i);
            if(isVatCheck !== null && isVatCheck !== ''){
                if(document.getElementById("checkUse"+i).checked){

                } else { 
                    document.getElementById("checkUse"+i).checked = true;
                    var amountChk = document.getElementById('InputAmount'+i).value;
                    if(amountChk !== null && amountChk !== ''){
                        var amount = document.getElementById('InputAmount'+i).value;
                        var gross = document.getElementById('InputGross'+i).value;
//                        var vat = document.getElementById('InputVat'+i).value;
//                        var vatDefaultData = parseFloat(document.getElementById('InputVat'+i).value);
//                        var vatData = document.getElementById("DetailBillableTable").rows[i].cells[7].innerHTML;
                        var vatTemp = document.getElementById('InputVatTemp'+i).value;
                        var vatDefaultData = parseFloat(vatTemp);
                        amount = amount.replace(/,/g,"");
                        var grossTotal = parseFloat(amount);

                        if((gross === '')){
                            grossTotal = (amount*100)/(100+vatDefaultData);
                            document.getElementById('InputGross'+i).value = formatNumber(grossTotal);
//                            document.getElementById('InputVat'+i).value = vatDefaultData;
                            document.getElementById("DetailBillableTable").rows[i].cells[7].innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('InputGross'+i).value = '';
//                            document.getElementById('InputVat'+i).value = '';
                            document.getElementById("DetailBillableTable").rows[i].cells[7].innerHTML = vatDefaultData;
                        }
                    }
                }    
            }    
        }             
    }
    CalculateGrandTotal(''); 
}  