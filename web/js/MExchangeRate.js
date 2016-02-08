/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function searchExchange() {
    var action = document.getElementById('actionSearch');
    action.value = 'search';
    
    document.getElementById('SearchExchange').submit();
}

function addaction(){
    document.getElementById('ExchangeID').value = '';
    document.getElementById('ExchangeDate').value = '';
    document.getElementById('ExchangeDate').value = '';
    document.getElementById('action').value='add';
}

function EditExchange(id, exdate, cur, exrate,createby,createdate) {
    document.getElementById('ExchangeID').value = id;
    document.getElementById('ExchangeDate').value = exdate;
    document.getElementById('ExchangeRate').value = exrate;
    document.getElementById('Currency').value = cur;
    document.getElementById('action').value = 'add';
}

function DeleteExchange(id, exdate,cur) {
    var ExchangeID = document.getElementById('ExchangeID');
    ExchangeID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete exchange rate : " + exdate + "( " + cur+") ?";
}

function Delete() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('ExchangeRateForm').submit();
}

function formatDecimal() {
    var decimalOnly = /^\s*-?[0-9]\d*(\.\d{1,4})?\s*$/;
    var myData = document.getElementById("ExchangeRate").value;
    if(myData!==''){
        if(decimalOnly.test(myData)){
//            alert('It is GOOD!');
            document.getElementById("btnSave").disabled = false;
            $('#textAlertDivDecimal').hide();
        }else{
            document.getElementById("btnSave").disabled = true;
//            alert('Please Input 4 decimal point!');
            $('#textAlertDivDecimal').show();
        }
    }
    return;  
}

function saveMExchangeRate(){
    var exrateId = $("#ExchangeID").val();
    var exrateDate = $("#ExchangeDate").val();
    var exrateCurrency = $("#Currency").val();
    if(exrateDate === '' || exrateCurrency === ''){
        $('#ExchangeRateForm').bootstrapValidator('revalidateField', 'ExchangeDate');
        $('#ExchangeRateForm').bootstrapValidator('revalidateField', 'Currency');
        return;
    }
    document.getElementById('ExchangeRateForm').submit();
}

$(document).ready(function() {
    $('.date').datetimepicker();
    $('.datemask').mask('0000-00-00');
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    
});
