/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. test
 */
 $(document).ready(function () {
    $(".money").mask('0000000000', {reverse: true});
     
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
            var staff_code = $(this).find("td").eq(0).html();
//            alert("Herree" + staff_code);
            $("#ARCode").val(staff_code);
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
   
    validFromInvoice();
    
    // Invoice No Key Up
    $("#InvNo").keyup(function(event){   
        if(event.keyCode === 13){
            searchInvoiceFromInvoiceNo(); 
        }
    });
    CalculateGrandTotal('');
 }); 
 
 function searchInvoiceFromInvoiceNo(){
    var action = document.getElementById('action');
    action.value = 'searchInvoice';
    document.getElementById('InvoiceForm').submit();
 }
 
 function searchInvoice(){
     
 }

var currency = 0;
function validFromInvoice(){
    // Validator Date From and To
    var result = checkCurrencyCost();
    if(result === true){
        $("#InvoiceForm")
        .bootstrapValidator({
            framework: 'bootstrap',
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {                
                InvTo: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input Invoice To'
                        }
                    }
                },
                InvToName: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input Invoice To Name'
                        }
                    }
                },
                ARCode: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input A/R Code'
                        }
                    }
                },
                InputInvDate: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Input Invoice Date'
                        }
                    }
                } 
            }  
        });
        return true;
//                .on('err.form.fv', function(e) {
//            checkCurrencyCost
//        });
//        var action = document.getElementById('action');
//        action.value = 'save';
//        document.getElementById('InvoiceForm').submit();
    }else if(result === false){
        alert("Stop");
        return false;
    }
//    checkCurrencyCost();
}

function checkCurrencyCost(){
//    alert("check");
    var counter = $('#DetailBillableTable tbody tr').length;
    var different = 0;
    var rowTemp = 0;
    for(var i=1 ; i <= (counter-1);i++){
        var currency1 = $('#SelectCurrencyAmount' +i).find(":selected").text();
        for(var j=2;j<=(counter-1);j++){
            var currency2 = $('#SelectCurrencyAmount' +j).find(":selected").text();
            if(currency1 !== currency2){
                rowTemp = j;
                different++;
            }
        }
    }
//                alert("Heeee : " + different);
    if(different > 0){          
        $('#DetailBillableTable').find('tr').each(function () { 
            $(this).find('td').each(function () { 
                if ($(this).hasClass('priceCurrencyAmount')) {
                    $(this).addClass("alert-danger");
                }
            });
        });    
        $('#textAlertCurrency').show();
        currency = 1;
        alert("Currency : " + currency); 
//        $('#InvoiceForm').bootstrapValidator('validateField', 'SelectCurrencyAmount2'+rowTemp);
        return false;
    } else {
         $('#DetailBillableTable').find('tr').each(function () { 
            $(this).find('td').each(function () { 
                if ($(this).hasClass('priceCurrencyAmount')) {
                    $(this).removeClass("alert-danger");
                }
            });
        });
        $('#textAlertCurrency').hide();
        currency = 0;
        return true;
    } 

}

var isDuplicateInvoiceDetail = 0;
var description ="";
function AddRowDetailBillAble(row,prod,des,cos,id,price,RefNo,cur){

    var selectT="";
    var selectC = "";
    var showvat = $('#showvat').val();
    var check = "";
    var vatValue = '';
    var vathidden = '';
   
    if(showvat=='true'){
        vathidden = '';
        check  = "checked";
        vatValue = defaultD;
    }else{
        vathidden ='class="hidden"';
    }
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
    if(RefNo === undefined ){
        RefNo = "";
    }
   
    if(price === undefined){
        price = "";
    }
    if(cur === undefined){
        cur = "";
    }
    if (!row) {
        row = 1;
    }
    var rows = document.getElementById("DetailBillableTable").getElementsByTagName("tr").length;
    if(rows > 1){
        $("#tr_FormulaAddRow").css("display","none");
    }
   selectT = selectType.replace("value='"+ prod +"'", "selected value='"+ prod+"'");
   selectC = select.replace("value='"+ cur +"'", "selected value='"+ cur+"'");
//    alert("D : " + des + " R : " + RefNo);
    if(des != '' || RefNo != ''){
        $("#DetailBillableTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input type="text" class="form-control" id="detailId' + row + '" name="detailId' + row + '" value="" > </td>' +
            '<td class="hidden"><input type="text" class="form-control" id="DetailBillId' + row + '" name="DetailBillId' + row + '" value="'+id+'" > </td>' +
            '<td><select id="SelectProductType' + row + '" name="SelectProductType' + row + '" class="form-control">'+ selectT +'</select> </td>' +
            '<td><input type="text" class="form-control" id="BillDescriptionTemp' + row + '" name="BillDescriptionTemp' + row + '" value="'+des +'" ></td>' +
            '<td class="hidden"><input type="text" class="form-control" id="BillDescription' + row + '" name="BillDescription' + row + '" value="'+des +'" > </td>' +
            '<td><input  maxlength ="15" type="text" onfocusout="changeFormatCostNumber(' + row + ')" class="form-control numerical" id="InputCost' + row + '" name="InputCost' + row + '" value="'+ cos +'" ></td>' +
            '<td><select id="SelectCurrencyCost' + row + '" name="SelectCurrencyCost' + row + '" class="form-control">'+ selectC +'</select></td>' +
            '<td><input type="text" onfocusout="changeFormatCostLocalNumber(' + row + ')"  value="'+cos +'" id="InputCostLocal' + row + '" name="InputCostLocal' + row + '" class="form-control"></td>' +
            '<td class="hidden"><input type="text" value="'+cos +'" id="InputCostLocalTemp' + row + '" name="InputCostLocalTemp' + row + '"></td>'+
            '<td  '+vathidden+'><input type="checkbox" '+check+' id="checkUse' + row + '" name="checkUse' + row + '"  onclick="calculateGross('+row+')"></td>'+
            '<td align="center" '+vathidden+'>'+vatValue +'</td>'+ 
            '<td class="hidden"><input type="text" class="form-control" id="InputVatTemp' + row + '" name="InputVatTemp' + row + '" value="'+ defaultD +'" ></td>'+
            '<td '+vathidden+' ><input type="text" maxlength ="15" readonly onfocusout="changeFormatGrossNumber(' + row + ')" class="form-control numerical" id="InputGross' + row + '" name="InputGross' + row + '" value="" ></td>'+
            '<td><input type="text" maxlength ="15" onfocusout="changeFormatAmountNumber('+row+');CalculateGrandTotal('+row+');calculateGross('+row+');" class="form-control numerical" id="InputAmount' + row + '" name="InputAmount' + row + '"  value="'+price +'" ></td>'+
            '<td class="priceCurrencyAmount"><select id="SelectCurrencyAmount' + row + '" name="SelectCurrencyAmount' + row + '" class="form-control">'+ selectC +'</select></td>'+
            '<td><input type="text" onfocusout="changeFormatAmountLocalNumber(' + row + ')" value="'+price +'" id="InputAmountLocal' + row + '" name="InputAmountLocal' + row + '" class="form-control" ></td>'+
            '<td class="hidden"><input type="text" onfocusout="changeFormatAmountLocalTempNumber(' + row + ')" value="'+price +'" id="InputAmountLocalTemp' + row + '" name="InputAmountLocalTemp' + row + '"  ></td>'+
            '<td align="center" ><span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill('+row+',\'\')" data-toggle="modal" data-target="#DelDetailBill" >  </span><a href="" data-toggle="modal" data-target="#DescriptionInvoiceDetailModal" onclick="getDescriptionDetail(' + row + ')" id="InputDescription' + row + '"><span  class="glyphicon glyphicon-file"></span></a></td>'+
            '<td class="hidden"><textarea id="DescriptionInvoiceDetail' + row + '" name="DescriptionInvoiceDetail' + row + '"> '+ description +'</textarea> </td>'+
            '</tr>'
        );
    }else if(des == '' || RefNo == ''){
        $("#DetailBillableTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input type="text" class="form-control" id="detailId' + row + '" name="detailId' + row + '" value="" > </td>' +
            '<td class="hidden"><input type="text" class="form-control" id="DetailBillId' + row + '" name="DetailBillId' + row + '" value="'+id+'" > </td>' +
            '<td><select id="SelectProductType' + row + '" name="SelectProductType' + row + '" class="form-control">'+ selectT +'</select> </td>' +
            '<td><input type="text" class="form-control" id="BillDescriptionTemp' + row + '" name="BillDescriptionTemp' + row + '" value="'+des +'" ></td>' +
            '<td class="hidden"><input type="text" class="form-control" id="BillDescription' + row + '" name="BillDescription' + row + '" value="'+des +'" > </td>' +
            '<td><input  maxlength ="15" type="text" onfocusout="changeFormatCostNumber(' + row + ')" class="form-control numerical" id="InputCost' + row + '" name="InputCost' + row + '" value="'+ cos +'" ></td>' +
            '<td><select id="SelectCurrencyCost' + row + '" name="SelectCurrencyCost' + row + '" class="form-control">'+ selectC +'</select></td>' +
            '<td><input type="text" onfocusout="changeFormatCostLocalNumber(' + row + ')"  value="'+cos +'" id="InputCostLocal' + row + '" name="InputCostLocal' + row + '" class="form-control"></td>' +
            '<td class="hidden"><input type="text" value="'+cos +'" id="InputCostLocalTemp' + row + '" name="InputCostLocalTemp' + row + '"></td>'+
            '<td  '+vathidden+'><input type="checkbox" '+check+' id="checkUse' + row + '" name="checkUse' + row + '"  onclick="calculateGross('+row+')"></td>'+
            '<td align="center" '+vathidden+'>'+vatValue +'</td>'+ 
            '<td class="hidden"><input type="text" class="form-control" id="InputVatTemp' + row + '" name="InputVatTemp' + row + '" value="'+ defaultD +'" ></td>'+
            '<td '+vathidden+' ><input type="text" maxlength ="15" readonly onfocusout="changeFormatGrossNumber(' + row + ')" class="form-control numerical" id="InputGross' + row + '" name="InputGross' + row + '" value="" ></td>'+
            '<td><input type="text" maxlength ="15" onfocusout="changeFormatAmountNumber('+row+');CalculateGrandTotal('+row+');calculateGross('+row+');" class="form-control numerical" id="InputAmount' + row + '" name="InputAmount' + row + '"  value="'+price +'" ></td>'+
            '<td class="priceCurrencyAmount"><select id="SelectCurrencyAmount' + row + '" name="SelectCurrencyAmount' + row + '" class="form-control">'+ selectC +'</select></td>'+
            '<td><input type="text" onfocusout="changeFormatAmountLocalNumber(' + row + ')" value="'+price +'" id="InputAmountLocal' + row + '" name="InputAmountLocal' + row + '" class="form-control" ></td>'+
            '<td class="hidden"><input type="text" onfocusout="changeFormatAmountLocalTempNumber(' + row + ')" value="'+price +'" id="InputAmountLocalTemp' + row + '" name="InputAmountLocalTemp' + row + '"  ></td>'+
            '<td align="center" ><span  class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteDetailBill('+row+',\'\')" data-toggle="modal" data-target="#DelDetailBill" >  </span><a href="" data-toggle="modal" data-target="#DescriptionInvoiceDetailModal" onclick="getDescriptionDetail(' + row + ')" id="InputDescription' + row + '"><span  class="glyphicon glyphicon-file"></span></a></td>'+
            '<td class="hidden"><textarea id="DescriptionInvoiceDetail' + row + '" name="DescriptionInvoiceDetail' + row + '"> '+ description +'</textarea> </td>'+
            '</tr>'
        );
    }
    var count = document.getElementById('counterTable');
    count.value = row++;

}

function getDescriptionDetail(row){
    var description = $('#DescriptionInvoiceDetail'+row).val();
//    alert(description);
    $('#InputDescriptionDetailId').val(row);
    $('#InputDescriptionDetail').val(description);
}

function saveDescriptionDetail(){
    var row = $('#InputDescriptionDetailId').val();
    var descriptionDetail = $('#InputDescriptionDetail').val();
//    $('#BillDescription'+row).val(descriptionDetail);
//    subStringDescription(descriptionDetail,row);
    console.log("Detail : "+$('#InputDescriptionDetail').val());
    $('#DescriptionInvoiceDetail'+row).html(descriptionDetail);
}
function subStringDescription(description,row){
    var index = description.indexOf("\n");
    var product = $('#SelectProductType'+row +'  option:selected').text();
    console.log("Product :" + product);
    var des = description.substring(0,index);
    console.log("description :" + description);
//    if(index === -1){
//        $('#BillDescriptionTemp'+row).val(description +" >> "+product);
//    }else{
//        $('#BillDescriptionTemp'+row).val(des +" >> "+product);
//    }
}
function changeFormatAmountNumber(id){
    var count  = parseFloat(document.getElementById('InputAmount'+id).value);
    
    if(isNaN(count)){
        document.getElementById('InputAmount' + id).value = "";
    }else{
        count = parseFloat(document.getElementById('InputAmount'+id).value);
        document.getElementById('InputAmount' + id).value = formatNumber(count);
    } 
}
function changeFormatAmountLocalNumber(id){
    var count  = parseFloat(document.getElementById('InputAmountLocal'+id).value);
    
    if(isNaN(count)){
        document.getElementById('InputAmountLocal' + id).value = "";
    }else{
        count = parseFloat(document.getElementById('InputAmountLocal'+id).value);
        document.getElementById('InputAmountLocal' + id).value = formatNumber(count);
    } 
}
function changeFormatAmountLocalTempNumber(id){
    var count  = parseFloat(document.getElementById('InputAmountLocalTemp'+id).value);
    
    if(isNaN(count)){
        document.getElementById('InputAmountLocalTemp' + id).value = "";
    }else{
        count = parseFloat(document.getElementById('InputAmountLocalTemp'+id).value);
        document.getElementById('InputAmountLocalTemp' + id).value = formatNumber(count);
    } 
}
function changeFormatCostNumber(id){
    var count  = parseFloat(document.getElementById('InputCost'+id).value);
    
    if(isNaN(count)){
        document.getElementById('InputCost' + id).value = "";
    }else{
        count = parseFloat(document.getElementById('InputCost'+id).value);
        document.getElementById('InputCost' + id).value = formatNumber(count);
    } 
}
function changeFormatCostLocalNumber(id){
    var count  = parseFloat(document.getElementById('InputCostLocal'+id).value);
    
    if(isNaN(count)){
        document.getElementById('InputCostLocal' + id).value = "";
    }else{
        count = parseFloat(document.getElementById('InputCostLocal'+id).value);
        document.getElementById('InputCostLocal' + id).value = formatNumber(count);
    } 
}
function changeFormatGrossNumber(id){
    var count = parseFloat(document.getElementById('InputGross'+id).value);
    if(isNaN(count)){
        document.getElementById('InputGross' + id).value = "";
    }else{
        count = parseFloat(document.getElementById('InputGross'+id).value);
        document.getElementById('InputGross' + id).value = formatNumber(count);
    }
}

function DeleteDetailBill(rowID,code){
    $("#idDeleteDetailBillable").val(rowID);
    if(code !== ""){
        $("#DeleteDetailBillable").text('Are you sure to delete detail billable : '+ code +'..?');
    }else{
        $("#DeleteDetailBillable").text('Are you sure to delete detail billable ?');
    }
}

function DisableVoidInvoice(){
    var InvNo = document.getElementById('InvNo');
    document.getElementById('disableVoid').innerHTML = "Are you sure to delete booking other : " + InvNo.value + " ?";
}

function EnableVoidInvoice(){
    var InvNo = document.getElementById('InvNo');
    document.getElementById('enableVoid').innerHTML = "Are you sure to enable booking other : " + InvNo.value + " ?";
}

function Enable() {
//    $("#disableVoidButton").css("display", "block");
//    $("#saveInvoice").prop("disabled",false);
//    $('#enableVoidButton').css("display", "none");
//    $('#textAlertDisable').hide();
    var action = document.getElementById('action');
    action.value = 'enableVoid';
    document.getElementById('InvoiceForm').submit();
}

function DisableInvoice() {
    var action = document.getElementById('action');
    action.value = 'disableVoid';
    document.getElementById('InvoiceForm').submit();
}

function printInvoice(text){
    $('#typePrint').val(text); 
}

function printInvoiceNew(){
    var invoiceId = $('#InvoiceId').val();
    var typePrint = $('#SelectTypePrint').val();
    var sale = $('#selectSalesStaff').val();
    var leader = $('#selectLeader').val();
    var payment = $('#selectPayment').val();
    var type = $('#typePrint').val(); 
    if(type === 'print'){
        window.open("report.smi?name="+typePrint+"&invoiceid="+invoiceId+"&bankid="+payment+"&showstaff="+sale+"&showleader="+leader+"");   
    }else if(type === 'email'){
        window.open("SendMail.smi?reportname=Invoice&reportid="+invoiceId+"&bankid="+payment+"&showstaff="+sale+"&showleader="+leader+"");   
    }
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

function DeleteBill() {
    var count = document.getElementById('counterTable');
    var rowId  = document.getElementById('idDeleteDetailBillable');
    var row = rowId.value;
    var DetailBillId  = $("#detailId"+rowId.value).val();
    if((DetailBillId !== "")&&(DetailBillId !== undefined)){
        rowId.value = DetailBillId ;
        console.log("Row : " + rowId.value);
        var servletName = 'BillableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + DetailBillId +
            '&type=' + 'deleteInvoiceDetail';
    CallAjaxDeleteBill(param,row);
        
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

function CallAjaxDeleteBill(param,row) {
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                if(msg === 'success'){
                    $("#BillDescription" + row).parent().parent().remove();
                }
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
    var invType = $("#invType").val();
//    alert("Ref : " + searchNo);
    console.log("inv type : " +invType+":");
    if(searchNo !== ""){ 
        var servletName = 'InvoiceServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&refNo=' + searchNo +
                '&invType=' + invType +
                '&type=' + 'searchInvoice';
        CallAjaxAdd(param);
    }else{
        $('#SearchRefNo').focus();
    }
   
}
function CallAjaxAdd(param) {
    var url = 'AJAXServlet';
    var BookintType = "";
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
                
                BookintType = array[0];
                if(BookintType == $('#typeBooking').val()){
                    $('#AlertBooking').hide();
                    setBillableInvoice(array[1]);
//                    alert(array[1]);
                    //MasterReservation Table
                    try {
                        $("#MasterReservation tbody").append(array[2]);          
                    } catch (e) {
                        alert(e);
                    }
                }else{          
                    $('#AlertBooking').show();
                    
                }
                
                 $("#ajaxloadsearch").addClass("hidden");
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
    $('#InputDueDate').val(array[7]);
}

function addInvoiceDetail(rowId){
    isDuplicateInvoiceDetail = 0;
    var rowCount = $('#DetailBillableTable >tbody >tr').length;
    $("#counter").val((rowCount++));
    var countTable = $("#counter").val();
//    alert("C : "+countTable);
    var RefNo = $("#SearchRefNo").val();
    var count = 1;
    $('#MasterReservation > tbody  > tr').each(function() {
        if(count === rowId){
            var id = $('#invoiceIdSearch'+rowId).val();
            var prod = $('#invoiceIdType'+rowId).val();
            var refItemId = $('#RefItemId'+rowId).val();
            var pro = $(this).find("td").eq(3).html();
            var des = $(this).find("td").eq(4).html();
            var cos = $(this).find("td").eq(5).html();
            var price = $(this).find("td").eq(6).html(); 
            var cur = $(this).find("td").eq(7).html();
            checkDuplicateInvoiceDetail(id,rowId);
            console.log("Duplicate : " + isDuplicateInvoiceDetail);
            if(isDuplicateInvoiceDetail === 0){
                $("#DetailBillableTable tr:last").remove(); 
                // Search Description
                var servletName = 'InvoiceServlet';
                var servicesName = 'AJAXBean';
                var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&refNo=' + refItemId +
                        '&typeId=' + prod +
                        '&type=' + 'searchInvoiceDescription';
                   CallAjaxSearchDescription(param,countTable);
                // Send Add Row
                AddRowDetailBillAble(countTable,prod,des,cos,id,price,RefNo,cur);
//                alert("C : " + countTable);
                CalculateGrandTotal(countTable);
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

function CallAjaxSearchDescription(param,rowId) {
    var url = 'AJAXServlet';
    $("#ajaxloadsearch").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                var strx   = msg.split('|');
                var array  = [];
                array = array.concat(strx);
                if(array[1] === 'Others'){
                    setDescriptionOther(array,rowId);
                }else if(array[1] === 'Land'){
                    setDescriptionLand(array,rowId);
                }else if(array[1] === 'Hotel'){
                    setDescriptionHotel(array,rowId);
                }else if(array[1] === 'Day Tour'){
                    setDescriptionDaytour(array,rowId);
                }else if(array[1] === 'Air Ticket'){
                    setDescriptionAirticket(array,rowId);
                }else if(array[1] === 'Air Additional'){
                    setDescriptionAirAdditional(array,rowId);
                }
                
//                alert("DES :"+array[1]);
                //MasterReservation Table
                try {
//                    alert(array[1]);
//                    $("#DescriptionInvoiceDetail").html( msg );
                    
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

function setDescriptionDaytour(des,row){
//    var strx   = des.split(',');
    var array  = [];
    array = des;
    var arrayNew ="";
    var i = 0;
    for (i = 1 ; i <= (array.length-1) ; i++){
        arrayNew += array[i] +",";
    };
//    alert(arrayNew + " R : " + row );
    var text = "";
//    text += "<div class='row'> <div class='col-md-2 text-right' ><b>Description</b> </div> <div class='col-md-10 text-left'><textarea class='form-control' rows='5'>";
    text += "Ref No. "+ array[2] +" : " + array[3] + " " + array[4] + " " + array[5] + "\n";
    text += "" + array[1] + " " + array[6] + " : " + array[7]+ "\n";
    text += "\t(" + array[8] + " " + array[9]+ ")\n";
//    text += "</textarea> </div> </div>";
    $('#DescriptionInvoiceDetail'+row).html(text);
    $("#DescriptionInvoiceDetailTextArea"+row).html(text);
}
function setDescriptionOther(data,row){
     //    var strx   = des.split(',');
    var array  = [];
    array = data;
    var arrayNew ="";
    var i = 0;
    for (i = 1 ; i <= (array.length-1) ; i++){
        arrayNew += array[i] +",";
    };
    var text = "";
//    text += "<div class='row'> <div class='col-md-2 text-right' ><b>Description</b> </div> <div class='col-md-10 text-left'><textarea class='form-control' rows='5'>";
    text += "Ref No. "+array[2] +" : " + array[3] + " " + array[4] + " " + array[5] + "\n";
    text += "" + array[1] + " (" + array[6] + ")  " + array[7]+ " \n";
    text += "\t " + array[8] + " "+ array[9] +  " "+ array[10] + " \n";
//    text += "</textarea> </div> </div>";
    $('#DescriptionInvoiceDetail'+row).html(text);
    $("#DescriptionInvoiceDetailTextArea"+row).html(text);
}
function setDescriptionLand(data,row){
    //    var strx   = des.split(',');
    var array  = [];
    array = data;
    var arrayNew ="";
    var i = 0;
    for (i = 1 ; i <= (array.length-1) ; i++){
        arrayNew += array[i] +",";
    };
    var text = "";
//    text += "<div class='row'> <div class='col-md-2 text-right' ><b>Description</b> </div> <div class='col-md-10 text-left'><textarea class='form-control' rows='5'>";
    text += "Ref No. "+ array[2] +" : " + array[3] + " " + array[4] + " " + array[5] + "\n";
    text += "" + array[1] + " (" + array[6] + ")  " + array[7]+ " NTS \n";
    text += "\t " + array[8] + " PAX \n";
//    text += "</textarea> </div> </div>";
    $('#DescriptionInvoiceDetail'+row).html(text);
    $("#DescriptionInvoiceDetailTextArea"+row).html(text);
}
function setDescriptionHotel(data,row){
    //    var strx   = des.split(',');
    var array  = [];
    array = data;
    var arrayNew ="";
    var i = 0;
    for (i = 1 ; i <= (array.length-1) ; i++){
        arrayNew += array[i] +",";
    };
    var text = "";
//    text += "<div class='row'> <div class='col-md-2 text-right' ><b>Description</b> </div> <div class='col-md-10 text-left'><textarea class='form-control' rows='5'>";
    text += "Ref No. "+ array[2] +" : " + array[3] + " " + array[4] + " " + array[5] + "\n";
    text += "" + array[1] + "  " + array[6] + " \n";
    text += "\t" + array[7] + " - " + array[8]+ " \n";
    text += "\t" + array[9] + "  " + array[10]+ "  " + array[11] + "  " + array[12] + " : "+  array[13] + "  NTS  \n";
//    text += "</textarea> </div> </div>";
    $('#DescriptionInvoiceDetail'+row).html(text);
    $("#DescriptionInvoiceDetailTextArea"+row).html(text);
}
function setDescriptionAirticket(data,row){
   
    var array  = [];
    array = data;
    var text = "";
    text += array[2];
    $('#DescriptionInvoiceDetail'+row).html(text);
    $("#DescriptionInvoiceDetailTextArea"+row).html(text);
}

function checkDuplicateInvoiceDetail(product,rowId){
    $('#DetailBillableTable > tbody  > tr').each(function() {
        var prod = $(this).find('input[type="text"]').eq(1).val();
//        alert("Pro :" + $.trim(prod) + ":Product Input:" + product+":");
        if($.trim(prod) === product){
//            alert("Product Old :" + prod + "Product New:" + product);
            isDuplicateInvoiceDetail++;
        }
    });
}

function setDescriptionAirAdditional(data,row){
   
    var array  = [];
    array = data;
    var text = "";
    text += array[2];
    $('#DescriptionInvoiceDetail'+row).html(text);
    $("#DescriptionInvoiceDetailTextArea"+row).html(text);
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
//    alert("1");
    var amount = document.getElementById('InputAmount'+row).value;
    var gross = document.getElementById('InputGross'+row).value;
//    var vatData = document.getElementById("DetailBillableTable").rows[row].cells[7].innerHTML;
    var varTemp = document.getElementById('InputVatTemp'+row).value;
    var vatDefaultData = parseFloat(varTemp);
    $('#checkUse'+row).val();
    amount = amount.replace(/,/g,"");
    var grossTotal = parseFloat(amount);
    if ($('#checkUse'+row).is(":checked")){
       document.getElementById("DetailBillableTable").rows[row].cells[10].innerHTML = vatDefaultData;
       grossTotal = (amount*100)/(100+vatDefaultData);
       document.getElementById('InputGross'+row).value = formatNumber(grossTotal); 
    }else{
       document.getElementById("DetailBillableTable").rows[row].cells[10].innerHTML = ''; 
       document.getElementById('InputGross'+row).value = '';
    }
    /*
    if((gross === '')){
        //alert("1: " + gross);
        
    }else if((gross === '0.00')){
        //alert("3: " + gross);
        grossTotal = (amount*100)/(100+vatDefaultData);
        if(grossTotal == 0){
            grossTotal = '';
        }
        document.getElementById('InputGross'+row).value = formatNumber(grossTotal);
    }else {
        //alert("2 : " + gross)
        document.getElementById('InputGross'+row).value = '';
    } */
}

function calculateGrossTemp(row){
    var amount = document.getElementById('InputAmount'+row).value;
    var gross = document.getElementById('InputGross'+row).value;
    var varTemp = document.getElementById('InputVatTemp'+row).value;
    var vatDefaultData = parseFloat(varTemp);

    amount = amount.replace(/,/g,"");
    var grossTotal = parseFloat(amount);

    if((gross === '')){
        grossTotal = (amount*100)/(100+vatDefaultData);
        document.getElementById("DetailBillableTable").rows[row].cells[10].innerHTML = vatDefaultData;
    }else if((gross === '0.00')){
        grossTotal = (amount*100)/(100+vatDefaultData);
        document.getElementById('InputGross'+row).value = formatNumber(grossTotal);
        document.getElementById("DetailBillableTable").rows[row].cells[10].innerHTML = vatDefaultData;
    }else {
        document.getElementById('InputGross'+row).value = '';
        document.getElementById("DetailBillableTable").rows[row].cells[10].innerHTML = '';
    }  
}

function CalculateGrandTotal(id){
//    alert("11");
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
        if(id !== ''){
          //  calculateGrossTemp(id);
        }
        
        document.getElementById('TotalNet').value = formatNumber(grandTotal);
        var bathString = toWords(formatNumber(grandTotal));
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
//    alert("C :"+check + "UN :" + unCheck );
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
                                document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = vatDefaultData;
                            } else {
                                document.getElementById('InputGross'+i).value = '';
    //                            document.getElementById('InputVat'+i).value = '';
                                document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = '';
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
                document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = "";
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
                            document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('InputGross'+i).value = '';
//                            document.getElementById('InputVat'+i).value = '';
                            document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = '';
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
                document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = '';
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
                            document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('InputGross'+i).value = '';
//                            document.getElementById('InputVat'+i).value = '';
                            document.getElementById("DetailBillableTable").rows[i].cells[10].innerHTML = vatDefaultData;
                        }
                    }
                }    
            }    
        }             
    }
    CalculateGrandTotal(''); 
}  


function sendEmailInvoice(){
    var InvoiceId = document.getElementById('InvoiceId').value;
    window.open("SendMail.smi?reportname=Invoice&reportid="+InvoiceId+"&bankid=4");
}


$(document).ready(function () {
    var bla = $('#resultText').val();
    if(bla === "success"){  
        $('#textAlertDivSave').show();
    }else if ( bla === ""){
        $('#textAlertDivSave').hide();
    }else if(bla === "notInvoice"){  
        $('#textAlertNotInvoice').show();
    }else if(bla === "yesInvoice"){  
        $('#textAlertNotInvoice').hide();
    }else if(bla === "moreMoney"){  
        $('#textAlertMoney').show();
    }else if(bla === "okMoney"){  
        $('#textAlertMoney').hide();
    }else if(bla === "fail") {
        $('#textAlertDivNotSave').show();
    }
});

function copyInvoice(){
    $('#InvoiceId').val('');
    $('#InvNo').val('');
    console.log("Invoice Id : " + $('#InvoiceId').val() + "Invoice Number : " + $('#InvNo').val());
    var action = document.getElementById('action');
    action.value = 'copyInvoice';
    document.getElementById('InvoiceForm').submit();
}