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

    var product_code = document.getElementById('product_code').value;
    var otherdate = (document.getElementById('otherdate').value !== '' ? document.getElementById('otherdate').value : setTodayDate());

    if ((product_code !== '') && (otherdate !== '')) {
        getvalueProduct('product', booktype);
    } else {
        document.getElementById('ad_cost').value = '0';
        document.getElementById('ad_price').value = '0';
        document.getElementById('ch_cost').value = '0';
        document.getElementById('ch_price').value = '0';
        document.getElementById('in_cost').value = '0';
        document.getElementById('in_price').value = '0';
    }

}

function setupproductvaluestock(id, code, name, booktype) {
    $('#ProductModalStock').modal('hide');
    document.getElementById('product_id_stock').value = id;
    document.getElementById('product_code_stock').value = code;
    document.getElementById('product_name_stock').value = name;
    document.getElementById('product_code_stock').focus();
    
    var product_id = document.getElementById('product_id_stock').value;
    var product_code = document.getElementById('product_code_stock').value;

    if (product_id !== '' && product_code !== '') {
        getvalueStock(id);
    } 
}


function setupagentvalue(id, code, name) {
    $('#AgentModal').modal('hide');
    document.getElementById('agent_id').value = id;
    document.getElementById('agent_code').value = code;
    document.getElementById('agent_name').value = name;
    document.getElementById('agent_code').focus();
}

function setupotherdatevalue(booktype) {
    var product_code = document.getElementById('product_code').value;
    var otherdate = document.getElementById('otherdate').value;
    var todaydate = document.getElementById('todaydate').value;
    var checkdate = document.getElementById('checkdate').value;
    if ((product_code !== '') && (otherdate !== '')) {
//        if (checkdate !== '') {
//            if (checkdate !== otherdate) {
                getvalueProduct('date', booktype);
//            }
//        } 
    }else if((product_code !== '') && (otherdate === '')){ 
        getvalueProduct('date', booktype);
    }else {
        document.getElementById('ad_cost').value = '0';
        document.getElementById('ad_price').value = '0';
        document.getElementById('ch_cost').value = '0';
        document.getElementById('ch_price').value = '0';
        document.getElementById('in_cost').value = '0';
        document.getElementById('in_price').value = '0';
    }
}

$(document).ready(function() {
    
    checkStock();
    var codeProduct = [];
    $.each(product, function(key, value) {
        codeProduct.push(value.code);
        if (!(value.name in codeProduct)) {
            if (value.code !== value.name) {
                codeProduct.push(value.name);
            }
        }
    });

    $("#product_code").autocomplete({
        source: codeProduct,
        close: function(event, ui) {
            $("#product_code").trigger('keyup');
        }
    });
    $("#product_code").on('keyup', function() {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        $("#product_id").val(null);
        var code = this.value.toUpperCase();
        var name = this.value;
        $.each(product, function(key, value) {
            if (value.code.toUpperCase() === code && code.length > 1) {
                $("#product_id").val(value.id);
                $("#product_name").val(value.name);
            }
            if (name === value.name && name.length > 1) {
                $("#product_code").val(value.code);
                $("#product_name").val(value.name);
                $("#product_id").val(value.id);
                code = $("#product_code").val().toUpperCase();

            }
            if(code === ''){
                $("#product_code").val('');
                $("#product_name").val('');
            }
        });

        var code = event.keyCode || event.which;

        if (code == 13) {
            getvalueProduct('product', $('#bookingtype').val());
        }

    });

    $("#product_code").on('blur', function() {
        var delay = 500;//1 seconds
        setTimeout(function() {
            $.each(product, function(key, value) {
                if ($("#product_code").val() == value.code) {
                    $("#product_id").val(value.id);
                    $("#product_name").val(value.name);
                    getvalueProduct('product', $('#bookingtype').val());
                }
            });

        }, delay);

    });

    $("#product_code").on('keyup', function(e) {
        var keyCode = e.keyCode || e.which;

        if (keyCode == 9) {
            if ($('#product_code').val() != '') {
                getvalueProduct('product', $('#bookingtype').val());
            }

        }
    });
    
    getStock();
    var codeStock = [];
    $.each(stock, function(key, value) {
        codeStock.push(value.code);
        if (!(value.name in codeStock)) {
            if (value.code !== value.name) {
                codeStock.push(value.name);
            }
        }
    });
    
    $("#product_code_stock").focusout(function() {
        var productIdStock = $("#product_id_stock").val();
        var productCodeStock = $("#product_code_stock").val();
        if(productCodeStock !== '') getvalueStock(productIdStock);
    });
    
    $("#product_code_stock").autocomplete({
        source: codeStock,
        close: function(event, ui) {
            $("#product_code_stock").trigger('keyup');
        }
    });
    $("#product_code_stock").on('keyup', function() {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        $("#product_id_stock").val(null);
        var code = this.value.toUpperCase();
        var name = this.value;
        $.each(stock, function(key, value) {
            if (value.code.toUpperCase() === code && code.length > 1) {
                $("#product_id_stock").val(value.id);
                $("#product_name_stock").val(value.name);
            }
            if (name === value.name && name.length > 1) {
                $("#product_code_stock").val(value.code);
                $("#product_name_stock").val(value.name);
                $("#product_id_stock").val(value.id);
                code = $("#product_code_stock").val().toUpperCase();

            }
            if(code === ''){
                $("#product_id_stock").val('');
                $("#product_code_stock").val('');
                $("#product_name_stock").val('');
            }
        });

        var code = event.keyCode || event.which;

        if (code == 13) {
//            getvalueProduct('product', $('#bookingtype').val());
        }

    });

    $("#product_code_stock").on('blur', function() {
        var delay = 500;//1 seconds
        setTimeout(function() {
            $.each(stock, function(key, value) {
                if ($("#product_code_stock").val() == value.code) {
                    $("#product_id_stock").val(value.id);
                    $("#product_name_stock").val(value.name);
//                    getvalueProduct('product', $('#bookingtype').val());
                }
            });

        }, delay);

    });

    $("#product_code_stock").on('keyup', function(e) {
        var keyCode = e.keyCode || e.which;

        if (keyCode == 9) {
            if ($('#product_code_stock').val() != '') {
//                getvalueProduct('product', $('#bookingtype').val());
            }

        }
    });

    var codeAgent = [];
    $.each(agent, function(key, value) {
        codeAgent.push(value.code);
        if (!(value.name in codeAgent)) {
            if (value.code !== value.name) {
                codeAgent.push(value.name);
            }
        }
    });

    $("#agent_code").autocomplete({
        source: codeAgent,
        close: function(event, ui) {
            $("#agent_code").trigger('keyup');
        }
    });

    $("#agent_code").on('keyup', function() {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        $("#agent_id").val(null);
        var code = this.value.toUpperCase();
        var name = this.value;
        $.each(agent, function(key, value) {
            if (value.code.toUpperCase() === code) {
                $("#agent_id").val(value.id);
                $("#agent_name").val(value.name);
            }
            if (name === value.name) {
                $("#agent_code").val(value.code);
                $("#agent_name").val(value.name);
                $("#agent_id").val(value.id);
                code = $("#agent_code").val().toUpperCase();

            }
            if(code === ''){
                $("#agent_code").val('');
                $("#agent_name").val('');
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
            },
            currencycost: {
                validators: {
                    notEmpty: {
                        message: 'The Cost Currency is required',
                        //enabled: false
                    }
                }
            },
            currency: {
                validators: {
                    notEmpty: {
                        message: 'The Price Currency is required',
                        //enabled: false
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

function setTodayDate(){
    var itemId = $("#itemid").val();
    if(itemId === ''){
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1; 
        var yyyy = today.getFullYear();

        if(dd<10) {
            dd='0'+dd
        } 

        if(mm<10) {
            mm='0'+mm
        } 

        today = mm+'-'+dd+'-'+yyyy;
        return today;
    }
}

function getvalueStock(id){
    var servletName = 'BookOtherServlet';
    var servicesName = 'AJAXBean';
    var otherId = $("#itemid").val();
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&productid=' + id +
            '&otherId=' + otherId +
            '&type=' + 'getvalueStock';
    CallAjaxStock(param);
}

function getvalueProduct(order, booktype) {
    $("#btnCheckStock").addClass("disabled");
    var product_code = document.getElementById('product_code').value;
    var otherdate = (document.getElementById('otherdate').value !== '' ? document.getElementById('otherdate').value : setTodayDate());
    var todaydate = document.getElementById('todaydate').value;
    var checkdate = document.getElementById('checkdate').value;
    if ((product_code !== '') && (otherdate !== '')) {
        if ((order === 'product')) {
            var servletName = 'BookOtherServlet';
            var servicesName = 'AJAXBean';
            var productid = document.getElementById('product_id').value;
            var otherdate = (document.getElementById('otherdate').value !== '' ? document.getElementById('otherdate').value : setTodayDate());
            var param = 'action=' + 'text' +
                    '&servletName=' + servletName +
                    '&servicesName=' + servicesName +
                    '&productid=' + productid +
                    '&otherdate=' + otherdate +
                    '&type=' + 'getvalueProduct';
            CallAjax(param, booktype);
        } else if (checkdate !== '') {
            if (checkdate !== otherdate) {
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
        } else {
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
    
    }else if ((product_code !== '') && (otherdate === '')) {
        var servletName = 'BookOtherServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&productid=' + productid +
                '&otherdate=' + otherdate +
                '&type=' + 'getvalueProduct';
        CallAjax(param, booktype);
    }
}

function checkStock() {
    $("#btnCheckStock").addClass("disabled");
    var servletName = 'BookOtherServlet';
    var servicesName = 'AJAXBean';
    var productid = document.getElementById('product_id').value;
    var otherdate = (document.getElementById('otherdate').value !== '' ? document.getElementById('otherdate').value : setTodayDate());
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&productid=' + productid +
            '&otherdate=' + otherdate +
            '&type=' + 'getStock';
    CallAjaxCheckStock(param);
}

function showStock(){
    $("#Stock").modal("show");
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
    if (input != 0) {
        return numberWithCommas(Math.round(input * (100 / 107)));
    } else {
        return null;
    }
}

function CallAjaxStock(param) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                if(msg !== 'fail'){
                    var result = msg.split("//");
                    $('#TicketTable').dataTable().fnClearTable();
                    $('#TicketTable').dataTable().fnDestroy();
                    $("#TicketTable tbody").empty().append(result[0]);
                    var table = $('#TicketTable').dataTable({bJQueryUI: true,
                        "bJQueryUI": true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bInfo": true,
                        "bSort": false

                    });
                    $('#TicketTable tbody').on('click', 'tr', function() {
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                            $('#hdGridSelected').val('');
                        }
                        else {
                            table.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                            $('#hdGridSelected').val($('#TicketTable tbody tr.row_selected').attr("id"));
                        }
                    });
                    $("#ticketListSize").val(result[1]);
                
                } else if(msg === 'fail') {
                    $('#TicketTable').dataTable().fnClearTable();
                    $('#TicketTable').dataTable().fnDestroy();
                    var table = $('#TicketTable').dataTable({bJQueryUI: true,
                        "bJQueryUI": true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bInfo": true,
                        "bSort": false

                    });
                    $('#TicketTable tbody').on('click', 'tr', function() {
                        if ($(this).hasClass('row_selected')) {
                            $(this).removeClass('row_selected');
                            $('#hdGridSelected').val('');
                        }
                        else {
                            table.$('tr.row_selected').removeClass('row_selected');
                            $(this).addClass('row_selected');
                            $('#hdGridSelected').val($('#TicketTable tbody tr.row_selected').attr("id"));
                        }
                    });
                    $("#ticketListSize").val(0);
                }
            }, error: function(msg) {
                //alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function CallAjaxCheckStock(param) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                $('#StockTable > tbody  > tr').each(function() {
                    $(this).remove();
                });
                if (msg !== 'notStock') {
                    var stock = msg.split(",");
                    $("#StockTable tbody").append(
                            '<tr>' +
                            '<td style="text-align:right;" >' + stock[0] + '</td>' +
                            '<td style="text-align:right;" >' + stock[1] + '</td>' +
                            '<td style="text-align:right;" >' + stock[2] + '</td>' +                           
                            '</tr>'
                            );
                    $("#btnCheckStock").removeClass("disabled");
                    if($("#product_id_stock").val() === ''){
                        $("#product_id_stock").val($("#product_id").val());
                        $("#product_code_stock").val($("#product_code").val());
                        $("#product_name_stock").val($("#product_name").val());
                    }
                    getvalueStock($("#product_id").val());
                } else {
                    $('#TicketTable').dataTable().fnClearTable();
                    $('#TicketTable').dataTable().fnDestroy();
                    var table = $('#TicketTable').dataTable({bJQueryUI: true,
                        "bJQueryUI": true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bInfo": true,
                        "bSort": false
                    });
                    if($("#product_id_stock").val() === ''){
                        $("#product_id_stock").val('');
                        $("#product_code_stock").val('');
                        $("#product_name_stock").val('');
                    }
                    getvalueStock($("#product_id").val());
                }
            }, error: function(msg) {
                //alert('error');
            }
        });
    } catch (e) {
        alert(e);
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
                var AD_Cost = document.getElementById('ad_cost').value;
                var AD_Price = document.getElementById('ad_price').value;
                var CH_Cost = document.getElementById('ch_cost').value;
                var CH_Price = document.getElementById('ch_price').value;
                var IN_Cost = document.getElementById('in_cost').value;
                var IN_Price = document.getElementById('in_price').value;
                var AD_CostRP = AD_Cost.replace(',', '');
                var AD_PriceRP = AD_Price.replace(',', '');
                var CH_CostRP = CH_Cost.replace(',', '');
                var CH_PriceRP = CH_Price.replace(',', '');
                var IN_CostRP = IN_Cost.replace(',', '');
                var IN_PriceRP = IN_Price.replace(',', '');
                
                if (AD_CostRP === '' || AD_CostRP === 'null') {
                    AD_CostRP = '0.00';
                }
                if (AD_PriceRP === '' || AD_PriceRP === 'null') {
                    AD_PriceRP = '0.00';
                }
                if (CH_CostRP === '' || CH_CostRP === 'null') {
                    CH_CostRP = '0.00';
                }
                if (CH_PriceRP === '' || CH_PriceRP === 'null') {
                    CH_PriceRP = '0.00';
                }
                if (IN_CostRP === '' || IN_CostRP === 'null') {
                    IN_CostRP = '0.00';
                }
                if (IN_PriceRP === '' || IN_PriceRP === 'null') {
                    IN_PriceRP = '0.00';
                }
                if (booktype === 'i') {
                    if ((AD_CostRP === '0.00') && (CH_CostRP === '0.00') && (IN_CostRP === '0.00') && (AD_PriceRP === '0.00') && (CH_PriceRP === '0.00') && (IN_PriceRP === '0.00')) {
                        setformatNumber('ad_cost', path[0]);
                        setformatNumber('ch_cost', path[1]);
                        setformatNumber('in_cost', path[2]);
                        setformatNumber('ad_price', path[3]);
                        setformatNumber('ch_price', path[4]);
                        setformatNumber('in_price', path[5]);
                    } else if ((AD_CostRP === path[0]) && (CH_CostRP === path[1]) && (IN_CostRP === path[2]) && (AD_PriceRP === path[3]) && (CH_PriceRP === path[4]) && (IN_PriceRP === path[5])) {
                        
                    } else if ((path[0] === '0.00') && (path[1] === '0.00') && (path[2] === '0.00') && (path[3] === '0.00') && (path[4] === '0.00') && (path[5] === '0.00')){
                        document.getElementById('path0').value = path[0];
                        document.getElementById('path1').value = path[1];
                        document.getElementById('path2').value = path[2];
                        document.getElementById('path3').value = path[3];
                        document.getElementById('path4').value = path[4];
                        document.getElementById('path5').value = path[5];
                        $('#Confirm').modal('show');
                    } else {
                        document.getElementById('path0').value = path[0];
                        document.getElementById('path1').value = path[1];
                        document.getElementById('path2').value = path[2];
                        document.getElementById('path3').value = path[3];
                        document.getElementById('path4').value = path[4];
                        document.getElementById('path5').value = path[5];
                        $('#Confirm').modal('show');
                    }
                }

                if (booktype === 'o') {
                    if ((AD_CostRP === '0.00') && (CH_CostRP === '0.00') && (IN_CostRP === '0.00') && (AD_PriceRP === '0.00') && (CH_PriceRP === '0.00') && (IN_PriceRP === '0.00')) {
                        setformatNumber('ad_cost', path[0]);
                        setformatNumber('ch_cost', path[1]);
                        setformatNumber('in_cost', path[2]);
                        setformatNumber('ad_price', path[3]);
                        setformatNumber('ch_price', path[4]);
                        setformatNumber('in_price', path[5]);
                    } else if ((AD_CostRP === path[0]) && (CH_CostRP === path[1]) && (IN_CostRP === path[2]) && (AD_PriceRP === path[3]) && (CH_PriceRP === path[4]) && (IN_PriceRP === path[5])) {

                    } else if ((path[0] === '0.00') && (path[1] === '0.00') && (path[2] === '0.00') && (path[3] === '0.00') && (path[4] === '0.00') && (path[5] === '0.00')){
                        document.getElementById('path0').value = path[0];
                        document.getElementById('path1').value = path[1];
                        document.getElementById('path2').value = path[2];
                        document.getElementById('path3').value = path[3];
                        document.getElementById('path4').value = path[4];
                        document.getElementById('path5').value = path[5];
                        $('#Confirm').modal('show');
                        
                    } else {
                        document.getElementById('path0').value = path[0];
                        document.getElementById('path1').value = path[1];
                        document.getElementById('path2').value = path[2];
                        document.getElementById('path3').value = path[3];
                        document.getElementById('path4').value = path[4];
                        document.getElementById('path5').value = path[5];
                        $('#Confirm').modal('show');
                    }
//                    if((AD_CostRP == path[0]) && (CH_CostRP == path[1]) && (IN_CostRP == path[2]) && (AD_PriceRP == path[3]) && (CH_PriceRP == path[4]) && (IN_PriceRP == path[5])){
//                            
//                    } else {
//                        document.getElementById('path0').value = path[0];
//                        document.getElementById('path1').value = path[1];
//                        document.getElementById('path2').value = path[2];
//                        document.getElementById('path3').value = path[3];
//                        document.getElementById('path4').value = path[4];
//                        document.getElementById('path5').value = path[5];
//                        $('#Confirm').modal('show');
//                    }                                   
                    //calculateVat();
                }
                checkStock();
            }, error: function(msg) {
                //alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function Confirm() {
    var path0 = document.getElementById('path0').value;
    var path1 = document.getElementById('path1').value;
    var path2 = document.getElementById('path2').value;
    var path3 = document.getElementById('path3').value;
    var path4 = document.getElementById('path4').value;
    var path5 = document.getElementById('path5').value;
    setformatNumber('ad_cost', path0);
    setformatNumber('ch_cost', path1);
    setformatNumber('in_cost', path2);
    setformatNumber('ad_price', path3);
    setformatNumber('ch_price', path4);
    setformatNumber('in_price', path5);
    $('#Confirm').modal('hide');
}

function setformatNumber(id, data) {
    if (data == 0) {
        document.getElementById(id).value = '0';
    } else {
        document.getElementById(id).value = numberWithCommas(data);
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

    adcost.value = numberWithCommas(parseInt((tempadcost * 7 / 100)) + parseInt(tempadcost));
    chcost.value = numberWithCommas(parseInt((tempchcost * 7 / 100)) + parseInt(tempchcost));
    incost.value = numberWithCommas(parseInt((tempincost * 7 / 100)) + parseInt(tempincost));
    adprice.value = numberWithCommas(parseInt((tempadprice * 7 / 100)) + parseInt(tempadprice));
    chprice.value = numberWithCommas(parseInt((tempchprice * 7 / 100)) + parseInt(tempchprice));
    inprice.value = numberWithCommas(parseInt((tempinprice * 7 / 100)) + parseInt(replaceComma(tempinprice)));

    var AD_Cost = document.getElementById('ad_cost').value;
    var CH_Cost = document.getElementById('ch_cost').value;
    var IN_Cost = document.getElementById('in_cost').value;


}

function replaceComma(input) {
    if (input == '')
        return '0';
    return input.replace(',', '');
}

function setStockTicket() {
    $("#reuse").prop("checked", false);
    $("#void").prop("checked", false);
    $("#refund").prop("checked", false);
    $("#stockTicketModal").modal("show");
}

function cancelStockTicket() {
//    var row = $('#TicketTable tr').length;
    var row = parseInt($('#ticketListSize').val())+1;
    var check = 0;
    for (var i = 1; i < row; i++) {
        var selectAll = document.getElementById("selectAll" + i);
        if (selectAll !== null && selectAll !== '') {           
            if (document.getElementById("selectAll" + i).checked) {
                check++;
            }
        }
    }
    if (check !== 0) {
        $('#counter').val(row);
        var ticketstatus = document.getElementById('ticketstatus');
        if (document.getElementById('reuse').checked) {
            var reuseTicket = document.getElementById('reuse').value;
            ticketstatus.value = reuseTicket;
            document.getElementById('otherForm').submit();
        } else if (document.getElementById('refund').checked) {
            var refundTicket = document.getElementById('refund').value;
            ticketstatus.value = refundTicket;
            document.getElementById('otherForm').submit();
        } else if (document.getElementById('void').checked) {
            var voidTicket = document.getElementById('void').value;
            ticketstatus.value = voidTicket;
            document.getElementById('otherForm').submit();
        } else {

        }
    } else {
        document.getElementById('alertCheckbox').innerHTML = 'Please select check box!.';
    }
    $("#stockTicketModal").modal("hide");
}

function addStockTicket() {
    var productCode = document.getElementById("product_code").value;
    var addticket = document.getElementById("addticket");
    if (productCode !== '') {
        addticket.value = "addTicket";
        document.getElementById('otherForm').submit();
    }
}

function selectAll() {
//    var row = $('#TicketTable tr').length;
    var row = parseInt($('#ticketListSize').val())+1;
    var check = 0;
    var unCheck = 0;

    for (var i = 1; i < row; i++) {
        var selectAll = document.getElementById("selectAll" + i);
        if (selectAll !== null && selectAll !== '') {
            if (document.getElementById("selectAll" + i).checked && !$("#selectAll" + i).is(':disabled')) {
                check++;
            } else if(!$("#selectAll" + i).is(':disabled')){
                unCheck++;
            }
        }
    }

    if (check > unCheck) {
        for (var i = 1; i < row; i++) {
            var selectAll = document.getElementById("selectAll" + i);
            if (selectAll !== null && selectAll !== '') {
                if (document.getElementById("selectAll" + i).checked) {

                } else {
                    document.getElementById("selectAll" + i).checked = $("#selectAll" + i).is(':disabled') ? false : true;
                }
            }
        }
    }

    if (check < unCheck) {
        for (var i = 1; i < row; i++) {
            var selectAll = document.getElementById("selectAll" + i);
            if (selectAll !== null && selectAll !== '') {
                document.getElementById("selectAll" + i).checked = false;
            }
        }
    }

    if (check === 0 && unCheck !== 0) {
        for (var i = 1; i < row; i++) {
            var selectAll = document.getElementById("selectAll" + i);
            if (selectAll !== null && selectAll !== '') {
                if (document.getElementById("selectAll" + i).checked) {

                } else {
                    document.getElementById("selectAll" + i).checked = $("#selectAll" + i).is(':disabled') ? false : true;

                }
            }
        }
    }

    if (check !== 0 && unCheck === 0) {
        for (var i = 1; i < row; i++) {
            var selectAll = document.getElementById("selectAll" + i);
            if (selectAll !== null && selectAll !== '') {
                document.getElementById("selectAll" + i).checked = false;
            }
        }
    }

    if (check === unCheck) {
        for (var i = 1; i < row; i++) {
            var selectAll = document.getElementById("selectAll" + i);
            if (selectAll !== null && selectAll !== '') {
                if (document.getElementById("selectAll" + i).checked) {

                } else {
                    document.getElementById("selectAll" + i).checked = $("#selectAll" + i).is(':disabled') ? false : true;
                }
            }
        }
    }
    removeAlertCheckbox();
}

function removeAlertCheckbox() {
    document.getElementById('alertCheckbox').innerHTML = '';
}
function setSelectCheckBox(row){
    if(document.getElementById("selectAll" + row).checked){
        document.getElementById("selectAll" + row).value = '1';
    }
}
function saveOther(){
    var isSave = true;
    var productCode = $("#product_code").val();
    var otherDate = $("#otherdate").val();
    var otherDateTo = $("#otherdateTo").val();
    if(productCode === ''){
        $('#otherForm').bootstrapValidator('revalidateField', 'product_code');
        isSave = false;
    }
    if(otherDate !== '' && otherDateTo !== ''){
        isSave = checkDateValue(otherDate,otherDateTo);
    }
    if(isSave){
        document.getElementById("otherForm").submit();
    }
}

function checkDateValue(inputFromDate,InputToDate){
    var isSave = true;
    var fromDate = (inputFromDate).split('-');
    var toDate = (InputToDate).split('-');
    if((parseInt(fromDate[2])) > (parseInt(toDate[2]))){
        isSave = false;
    }else if(((parseInt(fromDate[2])) >= (parseInt(toDate[2]))) && ((parseInt(fromDate[1])) > (parseInt(toDate[1])))){
        isSave = false;
    }else if(((parseInt(fromDate[2])) >= (parseInt(toDate[2]))) && ((parseInt(fromDate[1])) >= (parseInt(toDate[1]))) && (parseInt(fromDate[0])) > (parseInt(toDate[0]))){
        isSave = false;
    }
    if(!isSave){
        $("#effectivefromClass").addClass("has-error");
        $("#effectivetoClass").addClass("has-error");
    }
    return isSave;
}

function printOther(){
    var voucher = document.getElementById("voucher").value;
    var otherId = document.getElementById("itemid").value;
    var InputRefNo = document.getElementById("refno").value;
    
    var passengerId = document.getElementById("passengerId").value;
    var status = '';
    if (voucher === "OtherVouncher"){
        window.open("report.smi?name=DaytourOther&otherId=" + otherId + "&passengerId=" + passengerId + "&refNo=" + InputRefNo + "&comfirm="+status);
    }else{
        window.open("report.smi?name=OtherVouncherEmail&otherId=" + otherId + "&passengerId=" + passengerId + "&refNo=" + InputRefNo + "&comfirm="+status);
    }
}

function getStock(){
    var stockId = $("#product_id_stock").val();
    for(var i = 0; i < stock.length; i++){
        if(stockId === stock[i].id){
            $("#product_code_stock").val(stock[i].code);
            $("#product_name_stock").val(stock[i].name);
            i = stock.length;
        }
    }
    getvalueStock(stockId);
}


