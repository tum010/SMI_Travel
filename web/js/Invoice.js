/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. test
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
    
    // get row in table now
    var rowCount = $('#DetailBillableTable tr').length;
    $("#counterTable").val(rowCount);
    AddRowDetailBillAble(rowCount++);
//    alert("R : "+rowCount);
   
    $('#TotalNet').ready(function () {
        CalculateGrandTotal('');
    });
    validFromInvoice();
    
    // Invoice No Key Up
    $("#InvNo").keyup(function(event){   
        if(event.keyCode === 13){
            searchInvoiceFromInvoiceNo(); 
        }
    });
 }); 
 
 function searchInvoiceFromInvoiceNo(){
    var action = document.getElementById('action');
    action.value = 'searchInvoice';
    document.getElementById('InvoiceForm').submit();
 }
 
function validFromInvoice(){
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
            });     
}
var isDuplicateInvoiceDetail = 0;
function AddRowDetailBillAble(row,prod,des,cos,id,price){
    var selectT="";
    if(prod === undefined){
        prod = "";
    }
    if(des === undefined){
        des = "";
    }
    if(cos === undefined){
        cos = "";
    }
    if(id === undefined){
        id = "";
    }
    if(price === undefined){
        price = "";
    }
    if (!row) {
        row = 1;
    }
    var rows = document.getElementById("DetailBillableTable").getElementsByTagName("tr").length;
    if(rows > 1){
        $("#tr_FormulaAddRow").css("display","none");
    }
   selectT = selectType.replace("value='"+ prod +"'", "selected value='"+ prod+"'");
//   alert(select);
   $("#DetailBillableTable tbody").append(
        '<tr>' +
        '<td class="hidden"><input type="text" class="form-control" id="detailId' + row + '" name="detailId' + row + '" value="" > </td>' +
        '<td class="hidden"><input type="text" class="form-control" id="DetailBillId' + row + '" name="DetailBillId' + row + '" value="'+id+'" > </td>' +
        '<td><select id="SelectProductType' + row + '" name="SelectProductType' + row + '" class="form-control">'+ selectT +'</select> </td>' +
        '<td><input type="text" class="form-control" id="BillDescription' + row + '" name="BillDescription' + row + '" value="'+des +'" > </td>' +
        '<td><input type="text" class="form-control" id="InputCost' + row + '" name="InputCost' + row + '" value="'+ cos +'" ></td>' +
        '<td><select id="SelectCurrencyCost' + row + '" name="SelectCurrencyCost' + row + '" class="form-control">'+ select +'</select></td>' +
        '<td>'+cos +' </td>' +
        '<td class="hidden"><input type="text" value="'+cos +'" id="InputCostLocalTemp' + row + '" name="InputCostLocalTemp' + row + '"></td>'+
        '<td><input type="checkbox"  id="checkUse' + row + '" name="checkUse' + row + '"  onclick="calculateGross('+row+')"></td>'+
        '<td>'+ defaultD +'</td>'+ 
        '<td class="hidden"><input type="text" class="form-control" id="InputVatTemp' + row + '" name="InputVatTemp' + row + '" value="'+ defaultD +'" ></td>'+
        '<td><input type="text" class="form-control" id="InputGross' + row + '" name="InputGross' + row + '" value="" ></td>'+
        '<td><input type="text" class="form-control" id="InputAmount' + row + '" name="InputAmount' + row + '" value="" onfocusout="CalculateGrandTotal(' + row + ')"></td>'+
        '<td><select id="SelectCurrencyAmount' + row + '" name="SelectCurrencyAmount' + row + '" class="form-control">'+ select +'</select></td>'+
        '<td>'+price +'</td>'+
        '<td class="hidden"><input type="text" value="'+price +'" id="InputAmountLocalTemp' + row + '" name="InputAmountLocalTemp' + row + '"  ></td>'+
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
    $("#disableVoidButton").css("display", "block");
    $("#saveInvoice").prop("disabled",false);
    $('#enableVoidButton').css("display", "none");
    $('#textAlertDisable').hide();
//    var action = document.getElementById('action');
//    action.value = 'enableVoid';
//    document.getElementById('InvoiceForm').submit();
}

function Disable() {
    $("#disableVoidButton").css("display", "none");
    $("#saveInvoice").prop("disabled",true);
    $('#enableVoidButton').css("display", "block");
    $('#textAlertDisable').show();
//    var action = document.getElementById('action');
//    action.value = 'disableVoid';
//    document.getElementById('InvoiceForm').submit();
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
                $("#ARCode").val(billid);
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
                                $("#ARCode").val(billListId[i]);
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
    $('#MasterReservation > tbody  > tr').each(function() {
        $(this).remove();
    });
    var searchNo = $("#SearchRefNo").val();
//    alert("Ref : " + searchNo);
    if(searchNo !== ""){ 
        var servletName = 'InvoiceServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&refNo=' + searchNo +
                '&type=' + 'searchInvoice';
        CallAjaxAdd(param);
    }else{
        $('#SearchRefNo').focus();
    }
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
//                    alert(array[1]);
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
    $('#ARCode').val(array[0]); 
    $('#InvToName').val(array[1]);
    $('#InvToAddress').val(array[2]);
    $('#TermPay').val(array[3]); 
    $('#SaleStaffId').val(array[4]);
    $('#SaleStaffName').val(array[5]);
    $('#SaleStaffCode').val(array[6]);
}

function addInvoiceDetail(rowId){
    isDuplicateInvoiceDetail = 0;
    var countTable = $("#counter").val();
    var count = 1;
    $('#MasterReservation > tbody  > tr').each(function() {
        if(count === rowId){
            var id = $('#invoiceIdSearch'+rowId).val();
            var prod = $('#invoiceIdType'+rowId).val();
            var pro = $(this).find("td").eq(3).html();
            var des = $(this).find("td").eq(4).html();
            var cos = $(this).find("td").eq(5).html();
            var price = $(this).find("td").eq(6).html(); 
            var cur = $(this).find("td").eq(7).html();
            checkDuplicateInvoiceDetail(pro,rowId);
            if(isDuplicateInvoiceDetail === 0){
                $("#DetailBillableTable tr:last").remove(); 
                AddRowDetailBillAble(countTable,prod,des,cos,id,price);
            }else if (isDuplicateInvoiceDetail !== 0){
                alert("Duplicate");
            }
        }
        count++;       
    });
    if(isDuplicateInvoiceDetail === 0){
        countTable++;
        $("#counter").val(countTable);
        AddRowDetailBillAble(countTable);
    }    
}

function checkDuplicateInvoiceDetail(product,rowId){
    $('#DetailBillableTable > tbody  > tr').each(function() {
        var prod = $(this).find('input[type="text"]').eq(2).val();
//        alert("Pro :" + $.trim(prod) + ":Product Input:" + product+":");
        if($.trim(prod) === product){
//            alert("Product Old :" + prod + "Product New:" + product);
            isDuplicateInvoiceDetail++;
        }
    });
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

// Convert numbers to words
// copyright 25th July 2006, by Stephen Chapman http://javascript.about.com
// permission to use this Javascript on your web page is granted
// provided that all of the code (including this copyright notice) is
// used exactly as shown (you can change the numbering system if you wish)

// American Numbering System
var th = ['','thousand','million', 'billion','trillion'];
// uncomment this line for English Number System
// var th = ['','thousand','million', 'milliard','billion'];

var dg = ['zero','one','two','three','four', 'five','six','seven','eight','nine']; var tn = ['ten','eleven','twelve','thirteen', 'fourteen','fifteen','sixteen', 'seventeen','eighteen','nineteen']; var tw = ['twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety']; function toWords(s){s = s.toString(); s = s.replace(/[\, ]/g,''); if (s != parseFloat(s)) return 'not a number'; var x = s.indexOf('.'); if (x == -1) x = s.length; if (x > 15) return 'too big'; var n = s.split(''); var str = ''; var sk = 0; for (var i=0; i < x; i++) {if ((x-i)%3==2) {if (n[i] == '1') {str += tn[Number(n[i+1])] + ' '; i++; sk=1;} else if (n[i]!=0) {str += tw[n[i]-2] + ' ';sk=1;}} else if (n[i]!=0) {str += dg[n[i]] +' '; if ((x-i)%3==0) str += 'hundred ';sk=1;} if ((x-i)%3==1) {if (sk) str += th[(x-i-1)/3] + ' ';sk=0;}} if (x != s.length) {var y = s.length; str += 'point '; for (var i=x+1; i<y; i++) str += dg[n[i]] +' ';} return str.replace(/\s+/g,' ');}

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
        document.getElementById("DetailBillableTable").rows[row].cells[9].innerHTML = vatDefaultData;
    } else {
        document.getElementById('InputGross'+row).value = '';
        document.getElementById("DetailBillableTable").rows[row].cells[9].innerHTML = '';
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
        var bathString = toWords(formatNumber(grandTotal));
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
    alert("C :"+check + "UN :" + unCheck );
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
                                document.getElementById("DetailBillableTable").rows[i].cells[9].innerHTML = vatDefaultData;
                            } else {
                                document.getElementById('InputGross'+i).value = '';
    //                            document.getElementById('InputVat'+i).value = '';
                                document.getElementById("DetailBillableTable").rows[i].cells[9].innerHTML = '';
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
                document.getElementById("DetailBillableTable").rows[i].cells[9].innerHTML = "";
                document.getElementById("InputGross"+i).value = '';
            }   
        }
    }

    if(check === 0 && unCheck !== 0){
//        alert("1");
        for(var i=0; i <= row;i++){
            var isVatCheck = document.getElementById("checkUse"+i);
            if(isVatCheck !== null && isVatCheck !== ''){
                if(document.getElementById("checkUse"+i).checked){

                } else { 
//                    alert("2");
//                    document.getElementById("checkUse"+i).checked = true;
                    $("#checkUse"+i).prop("checked",true);
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
                            document.getElementById("DetailBillableTable").rows[i].cells[9].innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('InputGross'+i).value = '';
//                            document.getElementById('InputVat'+i).value = '';
                            document.getElementById("DetailBillableTable").rows[i].cells[9].innerHTML = '';
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
                document.getElementById("DetailBillableTable").rows[i].cells[9].innerHTML = '';
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
                            document.getElementById("DetailBillableTable").rows[i].cells[9].innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('InputGross'+i).value = '';
//                            document.getElementById('InputVat'+i).value = '';
                            document.getElementById("DetailBillableTable").rows[i].cells[9].innerHTML = vatDefaultData;
                        }
                    }
                }    
            }    
        }             
    }
    CalculateGrandTotal(''); 
}  
