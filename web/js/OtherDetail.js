/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var tempadcost = 0;
var tempchcost = 0;
var tempincost = 0;
var tempadprice = 0;
var tempchprice = 0;
var tempinprice = 0;

function setupproductvalue(id, code, name, booktype) {
    $('#ProductModal').modal('hide');
    document.getElementById('product_id').value = id;
    document.getElementById('product_code').value = code;
    document.getElementById('product_name').value = name;
    document.getElementById('product_code').focus();
    getvalueProduct(booktype);


}

function setupagentvalue(id, code, name) {
    $('#AgentModal').modal('hide');
    document.getElementById('agent_id').value = id;
    document.getElementById('agent_code').value = code;
    document.getElementById('agent_name').value = name;
    document.getElementById('agent_code').focus();
}


$(document).ready(function() {


    var codeProduct = [];
    $.each(product, function (key, value) {
        codeProduct.push(value.code);
        if ( !(value.name in codeProduct) ){
           codeProduct.push(value.name);
        }
    });

    $("#product_code").autocomplete({
        source: codeProduct,
        close:function( event, ui ) {
           $("#product_code").trigger('keyup');
        }
    });
    $("#product_code").on('keyup', function () {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        $("#product_id").val(null);
        var code = this.value.toUpperCase();
        var name = this.value;
        $.each(product, function (key, value) {
            if (value.code.toUpperCase() === code  ) {   
                    $("#product_id").val(value.id);
                    $("#product_name").val(value.name); 
            }
            if(name === value.name){
                $("#product_code").val(value.code);
                $("#product_name").val(value.name); 
                $("#product_id").val(value.id);
                code = $("#product_code").val().toUpperCase();

            }
        });
        
        var code = event.keyCode || event.which; 

        if (code  == 13) { 
           getvalueProduct($('#bookingtype').val());
        }
       
    });
    
        $("#product_code").on('blur', function () {
       var delay=500;//1 seconds
        setTimeout(function(){
          $.each(product, function (key, value) {
            if($("#product_code").val() == value.code){
                $("#product_id").val(value.id);
                $("#product_name").val(value.name);
                getvalueProduct($('#bookingtype').val());
            }     
         });   
       
        },delay); 
       
    });
    
    $("#product_code").on('keyup',  function(e) { 
        var keyCode  = e.keyCode || e.which; 
        
        if (keyCode == 9) { 
            if($('#product_code').val() != ''){
                getvalueProduct($('#bookingtype').val());
            }
            
        }      
    });
    
   
    

    var codeAgent = [];
    $.each(agent, function (key, value) {
        codeAgent.push(value.code);
        if ( !(value.name in codeAgent) ){
           codeAgent.push(value.name);
        }
    });

    $("#agent_code").autocomplete({
        source: codeAgent,
        close:function( event, ui ) {
           $("#agent_code").trigger('keyup');
        }
    });
    
    $("#agent_code").on('keyup', function () {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        $("#agent_id").val(null);
        var code = this.value.toUpperCase();
        var name = this.value;
        $.each(agent, function (key, value) {
            if (value.code.toUpperCase() === code  ) {   
                    $("#agent_id").val(value.id);
                    $("#agent_name").val(value.name); 
            }
            if(name === value.name){
                $("#agent_code").val(value.code);
                $("#agent_name").val(value.name); 
                $("#agent_id").val(value.id);
                code = $("#agent_code").val().toUpperCase();
               
            }
        });
    });
    

    $('#otherForm').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            product_code: {
                excluded: false,
                trigger: 'focus keyup',
                validators: {
                    notEmpty: {
                        message: 'The product code is required'
                    }
                }
            }
           
        }
    }).on('success.field.bv', function(e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);
        }
    });


});


function getvalueProduct(booktype) {
    var servletName = 'BookOtherServlet';
    var servicesName = 'AJAXBean';
    var productid = document.getElementById('product_id').value;
    var otherdate = document.getElementById('otherdate').value;
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&productid=' + productid +
            '&otherdate=' + otherdate +
            '&type=' + 'getvalueProduct';
    
    CallAjax(param, booktype);
    
}

function calculateVatvalue() {
    var adcost = document.getElementById('ad_cost');
    var chcost = document.getElementById('ch_cost');
    var incost = document.getElementById('in_cost');
    var adprice = document.getElementById('ad_price');
    var chprice = document.getElementById('ch_price');
    var inprice = document.getElementById('in_price');

    if (document.getElementById('Vat').checked) {

        calculateVat();
    } else {

        adcost.value = returnvat(adcost.value);
        chcost.value = returnvat(chcost.value);
        incost.value = returnvat(incost.value);
        adprice.value = returnvat(adprice.value);
        chprice.value = returnvat(chprice.value);
        inprice.value = returnvat(inprice.value);
    }


}

function returnvat(input) {
    input = replaceComma(input);
    if(input != 0){
        return numberWithCommas(Math.round(input * (100 / 107)));
    } else {
        return null;
    }
}

function CallAjax(param, booktype) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {

                var path = msg.split(',');

                if (booktype == 'i') {
                    document.getElementById('ad_cost').value = numberWithCommas(path[0]);
                    document.getElementById('ch_cost').value = numberWithCommas(path[1]);
                    document.getElementById('in_cost').value = numberWithCommas(path[2]);
                    document.getElementById('ad_price').value = numberWithCommas(path[3]);
                    document.getElementById('ch_price').value = numberWithCommas(path[4]);
                    document.getElementById('in_price').value = numberWithCommas(path[5]);
                }

                if (booktype == 'o') {
                    document.getElementById('ad_cost').value = path[0];
                    document.getElementById('ch_cost').value = path[1];
                    document.getElementById('in_cost').value = path[2];
                    document.getElementById('ad_price').value = path[3];
                    document.getElementById('ch_price').value = path[4];
                    document.getElementById('in_price').value = path[5];
                    calculateVat();
                }
            }, error: function(msg) {
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function calculateVat() {

    var adcost = document.getElementById('ad_cost');
    var chcost = document.getElementById('ch_cost');
    var incost = document.getElementById('in_cost');
    var adprice = document.getElementById('ad_price');
    var chprice = document.getElementById('ch_price');
    var inprice = document.getElementById('in_price');

    tempadcost = replaceComma(adcost.value);
    tempchcost = replaceComma(chcost.value);
    tempincost = replaceComma(incost.value);
    tempadprice = replaceComma(adprice.value);
    tempchprice = replaceComma(chprice.value);
    tempinprice = replaceComma(inprice.value);
    
    if(tempadcost == 0){
        adcost.value = '';
        chcost.value = '';
        incost.value = '';
        adprice.value = '';
        chprice.value = '';
        inprice.value = '';
    } else { 
        adcost.value = numberWithCommas(parseInt((tempadcost * 7 / 100)) + parseInt(tempadcost));
        chcost.value = numberWithCommas(parseInt((tempchcost * 7 / 100)) + parseInt(tempchcost));
        incost.value = numberWithCommas(parseInt((tempincost * 7 / 100)) + parseInt(tempincost));
        adprice.value = numberWithCommas(parseInt((tempadprice * 7 / 100)) + parseInt(tempadprice));
        chprice.value = numberWithCommas(parseInt((tempchprice * 7 / 100)) + parseInt(tempchprice));
        inprice.value = numberWithCommas(parseInt((tempinprice * 7 / 100)) + parseInt(replaceComma(tempinprice)));
    }


}

function replaceComma(input) {
    if(input=='') return '0';
    return input.replace(',', '');
}


