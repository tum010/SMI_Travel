/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var showflag = 1;
$(document).ready(function() {
    $(".money").mask('000,000,000.00', {reverse: true});
    $(".decimal").inputmask({
        alias: "decimal",
        integerDigits: 8,
        groupSeparator: ',',
        autoGroup: true,
        digits: 2,
        allowMinus: false,
        digitsOptional: false,
        placeholder: "0.00",
    });
    $(".decimalexrate").inputmask({
        alias: "decimal",
        integerDigits: 6,
        groupSeparator: ',',
        autoGroup: true,
        digits: 4,
        allowMinus: false,
        digitsOptional: false,
        placeholder: "0.0000",
    });
    $('.date').datetimepicker();
    $(".datemask").mask('00-00-0000');
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });
    var SearchInvoicSupTable = $('#SearchInvoicSupTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });

    $('#SearchInvoicSupTable tbody').on('click', 'tr', function() {
        $('.collapse').collapse('show');
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
        }
        else {
            SearchInvoicSupTable.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }
    });
    $('#SearchAPCodeTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": true,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 3
    });
    
    $(".daydatepicker").datetimepicker({
        pickTime: false
    });

    $('#collapseExample').on('shown.bs.collapse', function() {
        $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-down").addClass("glyphicon glyphicon-chevron-up");
    });

    $('#collapseExample').on('hidden.bs.collapse', function() {
        $(".arrowReservstion").removeClass("glyphicon glyphicon-chevron-up").addClass("glyphicon glyphicon-chevron-down");
    });

    $(".numerical").on('input', function() {
        var value = $(this).val().replace(/[^0-9.,]*/g, '');
        value = value.replace(/\.{2,}/g, '.');
        value = value.replace(/\.,/g, ',');
        value = value.replace(/\,\./g, ',');
        value = value.replace(/\,{2,}/g, ',');
        value = value.replace(/\.[0-9]+\./g, '.');
        $(this).val(value);
    });

    $("#payNo").keyup(function(event) {
        if (event.keyCode === 13) {
            searchPvNo();
        }
    });

    $("#refNo").keyup(function(event) {
        if (event.keyCode === 13) {
            searchRefNo();
        }
    });

    $("#payStockNo").keyup(function(event) {
        if (event.keyCode === 13) {
            searchStock();
        }
    });

    var rowPaymentDetailTable = parseInt($("#countPaymentDetail").val());
    addRowPaymentDetailTable(rowPaymentDetailTable);

    $("#PaymentDetailTable").on("keyup", function() {
        var rowAll = ($("#PaymentDetailTable tr").length) - 2;
        $("td").keyup(function() {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 3;
                rowAll = ($("#PaymentDetailTable tr").length);
                if ((rowIndex == rowAll) || ((rowIndex - 1) == rowAll)) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    addRowPaymentDetailTable(parseInt($("#countPaymentDetail").val()));
                }
                if (rowAll < 2) {
                    $("#tr_PaymentOutboundDetailAddRow").removeClass("hide");
                    $("#tr_PaymentOutboundDetailAddRow").addClass("show");
                }
            }
        });
    });

    var codeInvoiceSup = [];
    $.each(invoiceSup, function(key, value) {
        codeInvoiceSup.push(value.code);
        if (!(value.name in codeInvoiceSup)) {
            codeInvoiceSup.push(value.name);
        }
    });

    $("#invSupCode").autocomplete({
        source: codeInvoiceSup,
        close: function(event, ui) {
            $("#invSupCode").trigger('keyup');
        }
    });

    $("#invSupCode").on('keyup', function() {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value;
        $("#invSupId,#invSupName,#invSupApCode").val(null);
        $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');

        $.each(invoiceSup, function(key, value) {
            if (value.code.toUpperCase() === code && code.length > 1) {
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
                $("#invSupApCode").val(value.apcode);
                $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');
            }
            if (name === value.name && name.length > 1) {
                $("#invSupCode").val(value.code);
                $("#invSupId").val(value.id);
                $("#invSupName").val(value.name);
                $("#invSupApCode").val(value.apcode);
                code = $("#invSupCode").val().toUpperCase();
                $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');
            }
        });
        validatePaymentOutbound('paymentOutbound');
    });

    $("#invSupCode").on('blur', function() {
        var delay = 500;//1 seconds
        setTimeout(function() {
            $.each(invoiceSup, function(key, value) {
                //alert(value.code);
                if ($("#invSupCode").val() === value.code) {
                    $("#invSupId").val(value.id);
                    $("#invSupName").val(value.name);
                    $("#invSupApCode").val(value.apcode);
                    $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');
                }
            });

        }, delay);
        validatePaymentOutbound('paymentOutbound');
    });

    //Result
    var result = $('#result').val();
    if (result === "success") {
        $('#textAlertDivSave').show();
    } else if (result === 'fail') {
        $('#textAlertDivNotSave').show();
    } else if (result === 'not found') {
        $('#textAlertNotFound').show();
    } else if (result === "") {
        $('#textAlertDivSave').hide();
        $('#textAlertDivNotSave').hide();
        $('#textAlertNotFound').hide();
    }

    $("#realExRate").focusout(function() {
        $("#realExRate").val(this.value !== '' ? formatNumberFourDecimal(parseFloat((this.value).replace(/,/g, ""))) : '');
    });

    $("#payExRate").focusout(function() {
        $("#payExRate").val(this.value !== '' ? formatNumberFourDecimal(parseFloat((this.value).replace(/,/g, ""))) : '');
    });

    $("#value").focusout(function() {
        $("#value").val(this.value !== '' ? formatNumber(parseFloat((this.value).replace(/,/g, ""))) : '');
    });

    $("#isWht").click(function() {
        calculateWhtAmount();
    });
    
    $("#wht").focusout(function() {
        calculateWhtAmount(this.value);
    });

    $("#isComVat").click(function() {
        calculateVatRecComAmount();
    });
    
    $("#comm").focusout(function() {
        calculateVatRecComAmount();
    });

    setEnvironment()

    $("#account1,#account2").click(function() {
        validatePaymentOutbound('paymentOutbound');
    });

    $('.payDate').datetimepicker().change(function() {
        validatePaymentOutbound('paymentOutbound');
    });

    $("#status").change(function() {
        validatePaymentOutbound('paymentOutbound');
    });

    $("#invSupApCode").keypress(function() {
        validatePaymentOutbound('paymentOutbound');
    });
    
    setDescription();
    
    $("#searchInvoiceSupplier").keyup(function() {
        searchInvoiceSupplierList($("#searchInvoiceSupplier").val());          
    });
    
    //autocomplete
    $("#invSupCode").keyup(function(event){ 
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left); 
        if($(this).val() === ""){
            $("#invSupId").val("");
            $("#invSupCode").val("");
            $("#invSupName").val("");
            $("#invSupApCode").val("");
        }else{
            if(event.keyCode === 13){
                searchInvoiceSupplierListAuto(this.value); 
            }
        }
    });

    $("#invSupCode").keydown(function(){
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left); 
        if(showflag == 0){
            $(".ui-widget").css("top", -1000);
            showflag=1;
        }
    });
});

function reloadPage() {
    var action = document.getElementById('action');
    action.value = 'new';
    document.getElementById('PaymentOutboundForm').submit();
}

//Set Data at start
function setEnvironment() {
    for (var i = 0; i < invoiceSup.length; i++) {
        if (invoiceSup[i].code === $("#invSupCode").val()) {
            $("#invSupName").val(invoiceSup[i].name);
            i = invoiceSup.length;
        }
    }

//    var row = parseInt($("#countPaymentDetail").val());
//    for (var i = 1; i <= row; i++) {
//        if ($("#cost" + i).val() !== '') {
//            $("#cost" + i).val(formatNumber(parseFloat($("#cost" + i).val())));
//        }
//        if ($("#gross" + i).val() !== '') {
//            $("#gross" + i).val(formatNumber(parseFloat($("#gross" + i).val())));
//        }
//        if ($("#amount" + i).val() !== '') {
//            $("#amount" + i).val(formatNumber(parseFloat($("#amount" + i).val())));
//        }
//        if ($("#comm" + i).val() !== '') {
//            $("#comm" + i).val(formatNumber(parseFloat($("#comm" + i).val())));
//        }
//        if ($("#saleAmount" + i).val() !== '') {
//            $("#saleAmount" + i).val(formatNumber(parseFloat($("#saleAmount" + i).val())));
//        }
//    }
    calculateGrossTotal();
    calculateGrandTotal();
    calculateVatTotal();
    
    calculateTotalCom('',0);
    calculateTotalWht('',0);
    calculateTotalPayment();
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

function formatNumberFourDecimal(num) {
    return  num.toFixed(4).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
}

function refNoValidate() {
    $('#refnonopanel').removeClass('has-feedback');
    $('#refnonopanel').addClass('has-success');
    $('#refnonopanel').removeClass('has-error');
}

function stockValidate() {
    $('#stockpanel').removeClass('has-feedback');
    $('#stockpanel').addClass('has-success');
    $('#stockpanel').removeClass('has-error');
}

function searchRefNo() {
    if($("#ajaxLoadSearch").hasClass("hidden")){
        var refNo = $("#refNo").val();
        if (refNo === "") {
            if (!$('#refnopanel').hasClass('has-feedback')) {
                $('#refnopanel').addClass('has-feedback');
            }
            $('#refnopanel').removeClass('has-success');
            $('#refnopanel').addClass('has-error');
            $('#RefNoTable > tbody  > tr').each(function() {
                $(this).remove();
            });
            $('#searchRefNo2').addClass('hidden');
        } else {
            var servletName = 'PaymentOutboundServlet';
            var servicesName = 'AJAXBean';
            var param = 'action=' + 'text' +
                    '&servletName=' + servletName +
                    '&servicesName=' + servicesName +
                    '&refNo=' + refNo +
                    '&type=' + 'searchRefNo';
            CallAjaxSearchRef(param);
        }
    }
}

function CallAjaxSearchRef(param) {
    var url = 'AJAXServlet';
    $("#ajaxLoadSearch").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                try {
                    if (msg === "null") {
                        $('#RefNoTable > tbody  > tr').each(function() {
                            $(this).remove();
                        });
                        $('#searchRefNo2').addClass('hidden');

                    } else {
                        $('#RefNoTable > tbody  > tr').each(function() {
                            $(this).remove();
                        });
                        $("#RefNoTable tbody").append(msg);
                        var rowAll = ($("#RefNoTable tr").length);
                        for (var i = 1; i < rowAll; i++) {
                            var mCost = document.getElementById("mCost" + i);
                            if (mCost !== null) {
                                var mCostTemp = mCost.innerHTML;
                                if (mCostTemp !== '') {
                                    mCost.innerHTML = formatNumber(parseFloat(mCostTemp));
                                }
                            }
                            var mSale = document.getElementById("mSale" + i);
                            if (mSale !== null) {
                                var mSaleTemp = mSale.innerHTML;
                                if (mSaleTemp !== '') {
                                    mSale.innerHTML = formatNumber(parseFloat(mSaleTemp));
                                }
                            }
                        }
                        $('#searchRefNo2').removeClass('hidden');

                    }
                    $("#ajaxLoadSearch").addClass("hidden");

                } catch (e) {
                    $('#RefNoTable > tbody  > tr').each(function() {
                        $(this).remove();
                    });
                    $("#ajaxLoadSearch").addClass("hidden");
                }

            }, error: function(msg) {
                $('#RefNoTable > tbody  > tr').each(function() {
                    $(this).remove();
                });
                $('#searchRefNo2').addClass('hidden');
                $("#ajaxLoadSearch").addClass("hidden");
            }
        });
    } catch (e) {
        $('#RefNoTable > tbody  > tr').each(function() {
            $(this).remove();
        });
        $('#searchRefNo2').addClass('hidden');
    }
}

function searchStock() {
    hideTextAlertDiv();
    if($("#ajaxLoadSearch").hasClass("hidden")){
        var payStockNo = $("#payStockNo").val();
        if (payStockNo === "") {
            if (!$('#stockpanel').hasClass('has-feedback')) {
                $('#stockpanel').addClass('has-feedback');
            }
            $('#stockpanel').removeClass('has-success');
            $('#stockpanel').addClass('has-error');
            $('#StockTable > tbody  > tr').each(function() {
                $(this).remove();
            });
            $('#searchStock2').addClass('hidden');
        } else {
            var countPaymentDetail = parseInt($("#countPaymentDetail").val());
            var isNotDuplicate = true;
            for(var i=1; i<=countPaymentDetail; i++){
                var countTemp = document.getElementById("count" + i);       
                if (countTemp !== null) {
                    var payStock = $("#payStock"+i).val();
                    if(payStock === payStockNo){
                        isNotDuplicate = false;
                        i = countPaymentDetail+1;
                    }
                }  
            }

            if(isNotDuplicate){
                var servletName = 'PaymentOutboundServlet';
                var servicesName = 'AJAXBean';
                var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&payStockNo=' + payStockNo +
                        '&type=' + 'searchStock';
                CallAjaxSearchStock(param);

            }else{
    //            $("#textAlertSearchDuplicateStock").show();
            }    
        }
    }    
}

function CallAjaxSearchStock(param) {
    var url = 'AJAXServlet';
    $("#ajaxLoadSearch").removeClass("hidden");
    hideTextAlertDiv();
    $(".clickable").remove();
    $(".pageno").remove();
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                try {
                    $('#StockTable > tbody  > tr').each(function() {
                        $(this).remove();
                    });
                    if (msg === "null") {                        
                        $('#searchStock2').addClass('hidden');

                    } else if(msg === "duplicate"){
//                        $("#textAlertSearchDuplicateStock").show();
                    
                    } else {
                        $("#StockTable tbody").append(msg);
                        var rowAll = ($("#StockTable tr").length);
                        for (var i = 1; i < rowAll; i++) {
                            var mCostAmount = document.getElementById("mCostAmount" + i);
                            if (mCostAmount !== null) {
                                var mCostAmountTemp = mCostAmount.innerHTML;
                                if (mCostAmountTemp !== '') {
                                    mCostAmount.innerHTML = formatNumber(parseFloat(mCostAmountTemp));
                                }
                            }
                            var mSaleAmount = document.getElementById("mSaleAmount" + i);
                            if (mSaleAmount !== null) {
                                var mSaleAmountTemp = mSaleAmount.innerHTML;
                                if (mSaleAmountTemp !== '') {
                                    mSaleAmount.innerHTML = formatNumber(parseFloat(mSaleAmountTemp));
                                }
                            }
                        }
                        
                        $('table.paginated').each(function() {
                            var currentPage = 0;
                            var numPerPage = 5;
                            var $table = $(this);
                            $table.bind('repaginate', function() {
                                $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
                            });
                            $table.trigger('repaginate');
                            var numRows = $table.find('tbody tr').length;
                            var numPages = Math.ceil(numRows / numPerPage);
                            var $pager = $('<div class="col-xs-12 text-right pageno" id="pageNo"><font style="color: #499DD5"></font>&nbsp;</div>');
                            var $br = $('<div class="col-xs-12 pageno"><br></div>');

                            for (var page = 0; page < numPages; page++) {
                                var isShowPage = (page < 5 ? "" : "hidden");
                                if(page === 0){
                                    $('<font style="color: #499DD5" id="noFirst" onclick="changeColor(\'noFirst\',\'first\',\''+page+'\')"><span class="page-number glyphicon"></span></font>').text(" " + "First" + "  ").bind('click', {
                                    newPage: page
                                    }, function(event) {
                                        currentPage = event.data['newPage'];
                                        $table.trigger('repaginate');
                                        $(this).addClass('active').siblings().removeClass('active');
                                        $(this).css("color", "#AFEEEE");
                                    }).appendTo($pager).addClass('clickable');                                      

                                    if(numPages > 1){
                                        for(var i=0; i<numPages; i++){
                                            var isHidden = (i === 0 ? "" : "hidden");
                                            $('<font style="color: #499DD5" id="noPrevious'+i+'" onclick="changeColor(\'noPrevious'+i+'\',\'previous\',\''+i+'\')" class="'+isHidden+'"><span class="page-number glyphicon"></span></font>').text(" " + "Previous" + "  ").bind('click', {
                                            newPage: i
                                            }, function(event) {
                                                currentPage = event.data['newPage'];
                                                $table.trigger('repaginate');
                                                $(this).addClass('active').siblings().removeClass('active');
                        //                        $(this).css("color", "#AFEEEE");
                                            }).appendTo($pager).addClass('clickable');
                                        }
                                    }    
                                }

                                $('<font style="color: #499DD5" id="no' + page + '" onclick="changeColor(\'no'+page+'\',\'no\',\''+page+'\')" class="'+isShowPage+'"><span class="page-number glyphicon"></span></font>').text(" " + (page + 1) + "  ").bind('click', {
                                    newPage: page
                                }, function(event) {                  
                                    currentPage = event.data['newPage'];
                                    $table.trigger('repaginate');
                                    $(this).addClass('active').siblings().removeClass('active');
                                    $(this).css("color", "#AFEEEE");
                                }).appendTo($pager).addClass('clickable');

                                if(page === (numPages - 1)){
                                    if(numPages > 1){
                                        for(var i=0; i<numPages; i++){
                                            var isHidden = (i === 1 ? "" : "hidden");
                                            $('<font style="color: #499DD5" id="noNext'+i+'" onclick="changeColor(\'noNext'+i+'\',\'next\',\''+i+'\')" class="'+isHidden+'"><span class="page-number glyphicon"></span></font>').text(" " + "Next" + "  ").bind('click', {
                                            newPage: i
                                            }, function(event) {
                                                currentPage = event.data['newPage'];
                                                $table.trigger('repaginate');
                                                $(this).addClass('active').siblings().removeClass('active');
                        //                        $(this).css("color", "#AFEEEE");
                                            }).appendTo($pager).addClass('clickable');
                                        }
                                    }    

                                    $('<font style="color: #499DD5" id="noLast" onclick="changeColor(\'noLast\',\'last\',\''+page+'\')"><span class="page-number glyphicon"></span></font>').text(" " + "Last" + "  ").bind('click', {
                                    newPage: page
                                    }, function(event) {
                                        currentPage = event.data['newPage'];
                                        $table.trigger('repaginate');
                                        $(this).addClass('active').siblings().removeClass('active');
                                        $(this).css("color", "#AFEEEE");
                                    }).appendTo($pager).addClass('clickable');                                     
                                }
                            }
                            $br.insertAfter($table).addClass('active');
                            $pager.insertAfter($table).find('span.page-number:first').addClass('active');
                            document.getElementById("pageNo").style.cursor="pointer";
                            document.getElementById("page").value = numPages-1;
                            document.getElementById("currentPage").value = 0;
                            $("#noFirst").css("color", "#AFEEEE");
                            $("#no0").css("color", "#AFEEEE");
                            $("#noPrevious0").css("color", "#AFEEEE");
                        });
                        
                        $('#searchStock2').removeClass('hidden');

                    }
                    $("#ajaxLoadSearch").addClass("hidden");

                } catch (e) {
                    $('#StockTable > tbody  > tr').each(function() {
                        $(this).remove();
                    });
                    $("#ajaxLoadSearch").addClass("hidden");
                }

            }, error: function(msg) {
                $('#StockTable > tbody  > tr').each(function() {
                    $(this).remove();
                });
                $('#searchStock2').addClass('hidden');
                $("#ajaxLoadSearch").addClass("hidden");
            }
        });
    } catch (e) {
        $('#StockTable > tbody  > tr').each(function() {
            $(this).remove();
        });
        $('#searchStock2').addClass('hidden');
    }
}

function changeColor(id,type,page){
    var pageNo = parseInt($("#page").val())+1;
    for(var i=0; i<pageNo; i++){
        $("#no"+i).css("color", "#499DD5");
        $("#noPrevious"+i).css("color", "#499DD5");
        $("#noNext"+i).css("color", "#499DD5");
        $("#noFirst").css("color", "#499DD5");                
        $("#noLast").css("color", "#499DD5");

        $("#no"+i).addClass("hidden");
        $("#noPrevious"+i).addClass("hidden");
        $("#noNext"+i).addClass("hidden");
    }

    var pageShow = parseInt(page);
    if(pageShow > 2 && pageShow < pageNo - 2){
        for(var i=pageShow; i >= pageShow-2; i--){
           $("#no"+i).removeClass("hidden"); 
        }
        for(var i=pageShow; i <= pageShow+2; i++){
           $("#no"+i).removeClass("hidden");  
        }

    }else{
        if(pageShow <= 2){
            for(var i=0; i < 5; i++){
                $("#no"+i).removeClass("hidden");  
            } 

        }else if(pageShow <= pageNo-1){
            for(var i=pageNo-5; i < pageNo; i++){
                $("#no"+i).removeClass("hidden");  
            } 
        }

    }    

    var previous = ((parseInt(page) === 0 ? 0 : parseInt(page)-1));
    $("#noPrevious"+(previous)).removeClass("hidden");

    var next = ((parseInt(page) === pageNo-1 ? pageNo-1 : parseInt(page)+1));
    $("#noNext"+(next)).removeClass("hidden");

    $("#no"+page).css("color", "#AFEEEE");

    if(parseInt(page) === 0){
        $("#noFirst").css("color", "#AFEEEE");
        $("#noPrevious"+(previous)).css("color", "#AFEEEE");

    }else if(parseInt(page) === pageNo-1){
        $("#noLast").css("color", "#AFEEEE");
        $("#noNext"+(next)).css("color", "#AFEEEE");
    }

    if(pageNo-1 === 0){
        $("#noFirst").css("color", "#499DD5");                
        $("#noLast").css("color", "#499DD5");

        if(type === 'first'){
            $("#noFirst").css("color", "#AFEEEE");

        }else if(type === 'last'){
            $("#noLast").css("color", "#AFEEEE");

        }
    }

    $("#currentPage").val(page);

}

function addRowPaymentDetailTable(row) {
    var color = (row % 2 === 0 ? "#F2F2F2" : "");
    if (!row) {
        row = 1;
    }
    $("#PaymentDetailTable tbody").append(
            '<tr >' +
            '<td class="hidden">' +
            '<input type="text" name="count' + row + '" id="count' + row + '" class="form-control" value="' + row + '"/>' +
            '<input type="text" name="detailId' + row + '" id="detailId' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="payId' + row + '" id="payId' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="bookDetailId' + row + '" id="bookDetailId' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="bookDetailType' + row + '" id="bookDetailType' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="accCode' + row + '" id="accCode' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="exportDate' + row + '" id="exportDate' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="isExport' + row + '" id="isExport' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="realExRate' + row + '" id="realExRate' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="payExRate' + row + '" id="payExRate' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="isWht' + row + '" id="isWht' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="wht' + row + '" id="wht' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="whtTemp' + row + '" id="whtTemp' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="whtAmount' + row + '" id="whtAmount' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="isComVat' + row + '" id="isComVat' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="vatRecCom' + row + '" id="vatRecCom' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="vatRecComTemp' + row + '" id="vatRecComTemp' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="vatRecComAmount' + row + '" id="vatRecComAmount' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="value' + row + '" id="value' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="payStockId' + row + '" id="payStockId' + row + '" class="form-control" value=""/>' +
            '<input type="text" name="comm' + row + '" id="comm' + row + '" class="form-control" value="" onfocusout="onfocusoutComm(this,' + row + ');"/>' +
            '<input type="text" name="isNewWht' + row + '" id="isNewWht' + row + '" class="form-control" value="0"/>' +
            '</td>' +
            '<td>' +
            '<select class="form-control" name="type' + row + '" id="type' + row + '" onchange="addRow(\'' + row + '\')">' +
            '<option  value="" ></option>' +
            '</select>' +
            '</td>' +
            '<td>' +
            '<input type="text" maxlength="6" name="refNo' + row + '" id="refNo' + row + '" class="form-control" onfocusout="checkRefNo(\'' + row + '\')" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="invoice' + row + '" id="invoice' + row + '" class="form-control" value="" maxlength="255"/>' +
            '</td>' +
            '<td>' +
                '<div class="input-group daydatepicker" id="daydatepicker' + row + '">' +
                '<input type="text" name="invDate' + row + '" id="invDate' + row + '" class="form-control datemask" data-date-format="DD-MM-YYYY" />' +
                '<span class="input-group-addon spandate" style="padding : 1px 10px;" onclick="addRow(\'' + row + '\')"><span class="glyphicon-calendar glyphicon"></span></span>' +
                '</div>' +            
            '</td>' +
            '<td>' +
            '<input type="text" name="cost' + row + '" id="cost' + row + '" class="form-control decimal" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="gross' + row + '" id="gross' + row + '" style="text-align:right;" class="form-control numerical" onkeyup="insertCommas(this)" value="" readonly=""/>' +
            '</td>' +
            '<td align="center">' +
            '<input type="checkbox" id="isVat' + row + '" name="isVat' + row + '" onclick="calculateGross(\'' + row + '\'); calculateWhtAmount(\'\');" value="1">' +
            '</td>' +
            '<td align="right" id="vatShow' + row + '"></td>' +
            '<td class="hidden">' +
            '<input type="text" id="vat' + row + '" name="vat' + row + '" value=""/>' +
            '</td>' +
            '<td>' +
            '<input type="text" name="amount' + row + '" id="amount' + row + '" class="form-control decimal" onfocusout="setFormatNumber(\'amount\',\'' + row + '\'); calculateWhtAmount(\'\');" value=""/>' +
            '</td>' +
            '<td>' +
            '<select class="form-control" name="cur' + row + '" id="cur' + row + '" onchange="addRow(\'' + row + '\')">' +
            '<option  value="" ></option>' +
            '</select>' +
            '</td>' +
            '<td class="text-center" rowspan="2">' +
            '<a href="#" onclick=""  data-toggle="modal" data-target=""> ' +
            '<span id="editPaymentDetail' + row + '" onclick="editPaymentDetail(\'' + row + '\')" class="glyphicon glyphicon glyphicon-list-alt"></span>' +
            '</a> ' +
            '<a href="#" onclick=""  data-toggle="modal" data-target="">' +
            '<span id="spanDelete' + row + '" class="glyphicon glyphicon-remove deleteicon"  onclick="deletePaymentDetailList(\'\',\'' + row + '\')" data-toggle="modal" ></span>' +
            '</a>' +
            '</td>' +
            '</tr>' +
            '<tr>' +
            '<td colspan="1" align="right" bgcolor="#E8EAFF" >' +
            '<b>Detail</b>' +
            '</td>' +
            '<td colspan="2">' +
            '<input type="text" name="description' + row + '" id="description' + row + '" class="form-control" value="" maxlength="255" onfocusout=editDescription(\'' + row + '\')/>' +
            '</td>' +
            '<td colspan="1" align="right" bgcolor="#E8EAFF">' +
            '<b>Pay Stock</b>' +
            '</td>' +
            '<td colspan="3">' +
            '<input type="text" name="payStock' + row + '" id="payStock' + row + '" class="form-control" value="" onfocusout="checkPayStock(\'' + row + '\')" maxlength="10" disabled=""/>' +
            '</td>' +
            '<td colspan="1" align="right" bgcolor="#E8EAFF">' +
            '<b>Sale</b>' +
            '</td>' +
            '<td colspan="1">' +
            '<input type="text" name="saleAmount' + row + '" id="saleAmount' + row + '" class="form-control decimal" value=""/>' +
            '</td>' +
            '<td colspan="1">' +
            '<select class="form-control" name="saleCurrency' + row + '" id="saleCurrency' + row + '" onchange="addRow(\'' + row + '\')">' +
            '<option  value="" ></option>' +
            '</td>' +
            '</tr>'
            );
    $(".decimal").inputmask({
        alias: "decimal",
        integerDigits: 8,
        groupSeparator: ',',
        autoGroup: true,
        digits: 2,
        allowMinus: false,
        digitsOptional: false,
        placeholder: "0.00",
    });
    $("#tr_PaymentOutboundDetailAddRow").removeClass("show");
    $("#tr_PaymentOutboundDetailAddRow").addClass("hide");
    $("#typeClone option").clone().appendTo("#type" + row);
    $("#curClone option").clone().appendTo("#cur" + row);
    $("#curClone option").clone().appendTo("#saleCurrency" + row);
//    document.getElementById('vatShow' + row).innerHTML = parseFloat($("#mVat").val());
    document.getElementById('vat' + row).value = parseFloat($("#mVat").val());
    $("#countPaymentDetail").val(row + 1);
    reloadDatePicker();
}

function reloadDatePicker() {
    try {
        $(".daydatepicker").datetimepicker({
            pickTime: false
        });
        $('span').click(function() {
            var position = $(this).offset();
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
        });
    } catch (e) {

    }
}

function insertCommas(nField) {
    if (/^0/.test(nField.value)) {
        nField.value = nField.value.substring(0, 1);
    }
    if (Number(nField.value.replace(/,/g, ""))) {
        var tmp = nField.value.replace(/,/g, "");
        tmp = tmp.toString().split('').reverse().join('').replace(/(\d{3})/g, '$1,').split('').reverse().join('').replace(/^,/, '');
        if (/\./g.test(tmp)) {
            tmp = tmp.split(".");
            tmp[1] = tmp[1].replace(/\,/g, "").replace(/ /, "");
            nField.value = tmp[0] + "." + tmp[1]
        } else {
            nField.value = tmp.replace(/ /, "");
        }
    } else {
        nField.value = nField.value.replace(/[^\d\,\.]/g, "").replace(/ /, "");
    }
}

function setupInvSupValue(id, code, name, apcode) {
    $('#SearchInvoiceSup').modal('hide');
    document.getElementById('invSupId').value = id;
    document.getElementById('invSupCode').value = code;
    document.getElementById('invSupName').value = name;
    document.getElementById('invSupApCode').value = apcode;
    document.getElementById('invSupCode').focus();
    $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');
    validatePaymentOutbound('paymentOutbound');
}

function addRow(row) {
    var count = parseInt($("#countPaymentDetail").val());
    if (count === (parseInt(row) + 1)) {
        addRowPaymentDetailTable(count);
    }
}

function deletePaymentDetailList(id, row) {
    $("#delPaymentDetailId").val(id);
    $("#delPaymentDetailRow").val(row);
    $("#delPaymentOutboundModal").modal("show");
}

function confirmDeletePaymentDetailList() {
    var row = document.getElementById('delPaymentDetailRow').value;
    var id = document.getElementById('delPaymentDetailId').value;
    var count = parseInt($("#countPaymentDetail").val());
    var countPayInv = parseInt($("#countPaymentInvDetail").val());
    if (id === '') {
        $("#refitemid" + row).parent().remove();
        $("#type" + row).parent().parent().remove();
        $("#description" + row).parent().parent().remove();
        var rowAll = $("#PaymentDetailTable tr").length;
        if (rowAll <= 1) {
            $("#tr_PaymentOutboundDetailAddRow").removeClass("hide");
            $("#tr_PaymentOutboundDetailAddRow").addClass("show");
        }
        $("#countPaymentDetail").val(++count);

    } else {
        $.ajax({
            url: 'PaymentOutbound.smi?action=deletePaymentOutboundDetail',
            type: 'get',
            data: {paymentOutboundDetailId: id},
            success: function() {
                var bookid = $("#bookDetailId" + row).val();
                var booktype = $("#bookDetailType" + row).val();
                for(var i = 1 ; i < countPayInv ; i++){
                    var refitem = $("#refitemid" + i).val();
                    var billtypeid = $("#billtypeid" + i).val();
                    if(bookid == refitem && booktype == billtypeid){
                        $("#refitemid" + i).parent().remove();
                    }
                }
                $("#type" + row).parent().parent().remove();
                $("#description" + row).parent().parent().remove();
                var rowAll = $("#PaymentDetailTable tr").length;
                if (rowAll <= 1) {
                    $("#tr_PaymentOutboundDetailAddRow").removeClass("hide");
                    $("#tr_PaymentOutboundDetailAddRow").addClass("show");
                }
                $("#countPaymentDetail").val(++count);
            },
            error: function() {
                console.log("error");
                result = 0;
            }
        });
    }
    $('#delPaymentOutboundModal').modal('hide');
//    CalculateGrandTotal('');
}

function searchPvNo() {
    var action = document.getElementById('action');
    action.value = 'search';
    document.getElementById('PaymentOutboundForm').submit();
}

function checkRefNo(row) {
    var count = parseInt(document.getElementById('countPaymentDetail').value);
    var list = document.getElementById('refNoList').value;
    var refNo = document.getElementById('refNo' + row).value;

    list = list.replace("[", "");
    list = list.replace("]", "");
    list = list.replace(/ /g, "");

    var refNo_list = list.split(',');
    for (var i = 0; i <= refNo_list.length; i++) {
        if (String(refNo) === String(refNo_list[i])) {
            var refNoField = document.getElementById('refNo' + row);
            refNoField.style.borderColor = "Green";
            $("#btnSave").removeClass("disabled");
            i = refNo_list.length;

        } else if (refNo === '') {
            var refNoField = document.getElementById('refNo' + row);
            refNoField.style.borderColor = "";
            i = refNo_list.length;

        } else {
            var refNoField = document.getElementById('refNo' + row);
            refNoField.style.borderColor = "Red";
            $("#btnSave").addClass("disabled");
        }
    }
    validatePaymentOutbound("paymentOutboundDetail");
}

function calculateGross(row) {
    var amount = document.getElementById('amount' + row).value;
    var vatData = parseFloat(document.getElementById('vat' + row).value);
    var isVat = document.getElementById('isVat' + row).checked;
    var mVat = document.getElementById('mVat').value;

    amount = amount.replace(/,/g, "");
    var amountTotal = parseFloat(amount);
    var vatTotal = parseFloat(vatData);

    if (isVat) {
        amountTotal = (amount * (100 / (100 + vatData)));
        document.getElementById('gross' + row).value = formatNumber(amountTotal);
        document.getElementById('vatShow' + row).innerHTML = vatTotal;

    } else {
        document.getElementById('gross' + row).value = '';
        document.getElementById('vatShow' + row).innerHTML = '';
        document.getElementById('vat' + row).value = mVat;

    }
    calculateWhtAmount();
    calculateGrossTotal();
}


function setFormatNumber(type, row) {
    if (type === 'cost') {
        var cost = document.getElementById('cost' + row).value;
        if (cost !== '') {
            document.getElementById('cost' + row).value = formatNumber(parseFloat(cost.replace(/,/g, "")));
        }
    }
    if (type === 'comm') {
        var comm = document.getElementById('comm' + row).value;
        if (comm !== '') {
            document.getElementById('comm' + row).value = formatNumber(parseFloat(comm.replace(/,/g, "")));
        }
    }
    if (type === 'saleAmount') {
        var saleAmount = document.getElementById('saleAmount' + row).value;
        if (saleAmount !== '') {
            document.getElementById('saleAmount' + row).value = formatNumber(parseFloat(saleAmount.replace(/,/g, "")));
        }
    }
    if (type === 'amount') {
        var amount = document.getElementById('amount' + row).value;
        var isVat = document.getElementById('isVat' + row).checked;
        if (amount !== '') {
            document.getElementById('amount' + row).value = formatNumber(parseFloat(amount.replace(/,/g, "")));
        }
        if (isVat) {
            calculateGross(row);
        }
        calculateGrandTotal();
    }
}

function calculateGrossTotal() {
    var count = parseInt(document.getElementById('countPaymentDetail').value);
    var i;
    var grossTotal = 0;
    for (i = 1; i < count + 1; i++) {
        var gross = document.getElementById("gross" + i);
        if (gross !== null) {
            var value = gross.value;
            if (value !== '') {
                value = value.replace(/,/g, "");
                var total = parseFloat(value);
                grossTotal += total;

            }
        }
    }
    document.getElementById('grossTotal').value = formatNumber(grossTotal);
    calculateVatTotal();
}

function calculateVatTotal() {
//    var grandTotal = parseFloat($("#grandTotal").val().replace(/,/g, ""));
//    var grossTotal = parseFloat($("#grossTotal").val().replace(/,/g, ""));
//    $("#vatTotal").val(formatNumber(grandTotal - grossTotal));
    var count = parseInt(document.getElementById('countPaymentDetail').value);
    var i;
    var vatTotal = 0;
    for (i = 1; i < count + 1; i++) {
        var isVat = document.getElementById("isVat" + i);
        var amount = document.getElementById("amount" + i);
        if (isVat !== null) {
            if (isVat.checked) {
                var amountTemp = (amount.value !== '' ? parseFloat((amount.value).replace(/,/g, "")) : 0.00);
                var vatTemp = parseFloat(document.getElementById('vat' + i).value);
                var vat = parseFloat(amountTemp - (amountTemp * (100 / (100 + vatTemp))).toFixed(2));
                vatTotal += vat;
            }
        }
    }
    document.getElementById('vatTotal').value = formatNumber(vatTotal);
}

function calculateGrandTotal() {
    var count = parseInt(document.getElementById('countPaymentDetail').value);
    var i;
    var grandTotal = 0;
    for (i = 1; i < count + 1; i++) {
        var amount = document.getElementById("amount" + i);
        if (amount !== null) {
            var value = amount.value;
            if (value !== '') {
                value = value.replace(/,/g, "");
                var total = parseFloat(value);
                grandTotal += total;

            }
        }
    }
    document.getElementById('grandTotal').value = formatNumber(grandTotal);
    calculateVatTotal();
    calculateTotalPayment();
}

function addRefNo(refNo, type, description, billType, cost, curCost, sale, curSale, bookId) {
    var countPaymentDetail = parseInt($("#countPaymentDetail").val());
    var count = 0;
    var checkAddDuplicate = false;
    for (var i = 1; i <= countPaymentDetail; i++) {
        var countTemp = document.getElementById("count" + i);       
        if (countTemp !== null) {
            var typeTemp = $("#type"+i).val();
            var refNoTemp = $("#refNo"+i).val();
            var invoiceTemp = $("#invoice"+i).val();
            var costTemp = $("#cost"+i).val();
            var grossTemp = $("#gross"+i).val();
            var amountTemp = $("#amount"+i).val();
            var commTemp = $("#comm"+i).val();
            var curTemp = $("#cur"+i).val();
            var descriptionTemp = $("#description"+i).val();
            var payStockTemp = $("#payStock"+i).val();
            var saleAmountTemp = $("#saleAmount"+i).val();
            var saleCurrencyTemp = $("#saleCurrency"+i).val();
            
            var realExRateTemp = $("#realExRate"+i).val();
            var payExRateTemp = $("#payExRate"+i).val();
            var whtAmountTemp = $("#whtAmount"+i).val();
            var vatRecComAmountTemp = $("#vatRecComAmount"+i).val();
            var valueTemp = $("#value"+i).val();
            var whtTemp = $("#wht"+i).val();
            var vatRecComTemp = $("#vatRecCom"+i).val();
            var bookIdTemp = $("#bookDetailId"+i).val();
            if(bookIdTemp === bookId){
                checkAddDuplicate = true;
            }
//            alert(typeTemp+":"+refNoTemp+":"+invoiceTemp+":"+costTemp+":"+grossTemp+":"+amountTemp+":"+commTemp+":"+curTemp+":"+descriptionTemp+":"+payStockTemp+":"+saleAmountTemp+":"+saleAmountTemp);
//            alert(realExRateTemp+":"+payExRateTemp+":"+whtAmountTemp+":"+vatRecComAmountTemp+":"+valueTemp+":"+whtTemp+":"+vatRecComTemp);
            
            if(typeTemp === '' && refNoTemp === '' && invoiceTemp === '' && costTemp === '' && grossTemp === '' && amountTemp === '' && commTemp === '' &&
                    curTemp === '' && descriptionTemp === '' && payStockTemp === '' && saleAmountTemp === '' && saleCurrencyTemp === '' &&
                    realExRateTemp === '' && payExRateTemp === '' && whtAmountTemp === '' && vatRecComAmountTemp === '' && valueTemp === '' &&
                    whtTemp === '' && vatRecComTemp === ''){
                count = parseInt(countTemp.value);
                
                $("#isWht" + i).val('');
                $("#isComVat" + i).val('');
                $("#detailId" + i).val('');
                $("#payId" + i).val('');
                $("#bookDetailId" + i).val('');
                $("#bookDetailType" + i).val('');
                $("#accCode" + i).val('');
                $("#exportDate" + i).val('');
                $("#isExport" + i).val('');
                $("#whtTemp" + i).val('');
                $("#vatRecComTemp" + i).val('');
                $("#vatRecComAmount" + i).val('');
                $("#payStockId" + i).val('');
               
            }    
        }      
    }
    if(count === 0){
        count = ++countPaymentDetail;
        addRowPaymentDetailTable(count);      
    }
    
    if(checkAddDuplicate){
        $('#textAlertDuplicateRefNo').show();
    }else{
        addRowPaymentDetailTableByRefNo(refNo, type, description, billType, parseFloat(cost), curCost, parseFloat(sale), curSale, bookId, count);
    }
}

function addRowPaymentDetailTableByRefNo(refNo, type, description, billType, cost, curCost, sale, curSale, bookId, row) {
    var color = (row % 2 === 0 ? "#F2F2F2" : "");
    if (!row) {
        row = 1;
    }
    addInvoiceDetail(bookId,billType,row);
    $("#count" + row).val(row);
    $("#bookDetailId" + row).val(bookId);
    $("#bookDetailType" + row).val(billType);
    $("#refNo" + row).val(refNo);
    $("#description" + row).val(description);
    $("#cost" + row).val(formatNumber(cost));
    $("#amount" + row).val(formatNumber(cost));
    $("[name=type" + row + "] option").filter(function() {
        return ($(this).text() === type);
    }).prop('selected', true);
    $("[name=cur" + row + "] option").filter(function() {
        return ($(this).text() === curCost);
    }).prop('selected', true);
    $("#saleAmount" + row).val(formatNumber(sale));
    $("[name=saleCurrency" + row + "] option").filter(function() {
        return ($(this).text() === curSale);
    }).prop('selected', true);

    row = row + 1;
    addRowPaymentDetailTable(row);
}

function addInvoiceDetail(bookid,billtype,row){
//    var count = parseInt($("#countPaymentInvDetail").val());
    var servletName = 'PaymentOutboundServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
        '&servletName=' + servletName +
        '&servicesName=' + servicesName +
        '&bookdetailid=' + bookid +
        '&billtype=' + billtype +
        '&rowCount=' + row +
        '&type=' + 'addInvoiceDetail';
    CallAjaxAdd(param);
}

function CallAjaxAdd(param) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                try {
                    if(msg == "null"){
                        
                    }else{
                        $("#InvoiceDeailTable tbody").append(msg);
//                        var count = parseInt($("#countPaymentInvDetail").val())+1;
//                        $("#countPaymentInvDetail").val(count);
                    }
                } catch (e) {
                    alert(e);
                }
            }, error: function (msg) {
                 $("#ajaxload").addClass("hidden");
            }

        });
    } catch (e) {
        alert(e);
    }
}     
function addStock(stockId, payStockNo, costAmount, saleAmount, curCost, curSale, detail) {
    var countPaymentDetail = parseInt($("#countPaymentDetail").val());
    var count = 0;
    var checkAddDuplicate = false;
    for (var i = 1; i <= countPaymentDetail; i++) {        
        var countTemp = document.getElementById("count" + i);       
        if (countTemp !== null) {
            var typeTemp = $("#type"+i).val();
            var refNoTemp = $("#refNo"+i).val();
            var invoiceTemp = $("#invoice"+i).val();
            var costTemp = $("#cost"+i).val();
            var grossTemp = $("#gross"+i).val();
            var amountTemp = $("#amount"+i).val();
            var commTemp = $("#comm"+i).val();
            var curTemp = $("#cur"+i).val();
            var descriptionTemp = $("#description"+i).val();
            var payStockTemp = $("#payStock"+i).val();
            if(payStockNo === payStockTemp){
               checkAddDuplicate = true ;     
            }
            var saleAmountTemp = $("#saleAmount"+i).val();
            var saleCurrencyTemp = $("#saleCurrency"+i).val();
            
            var realExRateTemp = $("#realExRate"+i).val();
            var payExRateTemp = $("#payExRate"+i).val();
            var whtAmountTemp = $("#whtAmount"+i).val();
            var vatRecComAmountTemp = $("#vatRecComAmount"+i).val();
            var valueTemp = $("#value"+i).val();
            var whtTemp = $("#wht"+i).val();
            var vatRecComTemp = $("#vatRecCom"+i).val();
//            alert(typeTemp+":"+refNoTemp+":"+invoiceTemp+":"+costTemp+":"+grossTemp+":"+amountTemp+":"+commTemp+":"+curTemp+":"+descriptionTemp+":"+payStockTemp+":"+saleAmountTemp+":"+saleAmountTemp);
//            alert(realExRateTemp+":"+payExRateTemp+":"+whtAmountTemp+":"+vatRecComAmountTemp+":"+valueTemp+":"+whtTemp+":"+vatRecComTemp);
            
            if(typeTemp === '' && refNoTemp === '' && invoiceTemp === '' && costTemp === '' && grossTemp === '' && amountTemp === '' && commTemp === '' &&
                    curTemp === '' && descriptionTemp === '' && payStockTemp === '' && saleAmountTemp === '' && saleCurrencyTemp === '' &&
                    realExRateTemp === '' && payExRateTemp === '' && whtAmountTemp === '' && vatRecComAmountTemp === '' && valueTemp === '' &&
                    whtTemp === '' && vatRecComTemp === ''){
                count = parseInt(countTemp.value);
                
                $("#isWht" + i).val('');
                $("#isComVat" + i).val('');
                $("#detailId" + i).val('');
                $("#payId" + i).val('');
                $("#bookDetailId" + i).val('');
                $("#bookDetailType" + i).val('');
                $("#accCode" + i).val('');
                $("#exportDate" + i).val('');
                $("#isExport" + i).val('');
                $("#whtTemp" + i).val('');
                $("#vatRecComTemp" + i).val('');
                $("#vatRecComAmount" + i).val('');
                $("#payStockId" + i).val('');
               
            }    
        }
    }
//    alert(count);
    if(count === 0){
        count = ++countPaymentDetail;
        addRowPaymentDetailTable(count);      
    }
    if(checkAddDuplicate){
        $('#textAlertDuplicateStock').show();
    }else{
        addRowPaymentDetailTableByStock(stockId, payStockNo, parseFloat(costAmount), curCost, parseFloat(saleAmount), curSale, 'Others', count, detail);
    }
}

function addRowPaymentDetailTableByStock(stockId, payStockNo, costAmount, curCost, saleAmount, curSale, type, row, detail) {
    var color = (row % 2 === 0 ? "#F2F2F2" : "");
    if (!row) {
        row = 1;
    }

    $("#count" + row).val(row);
    $("[name=type" + row + "] option").filter(function() {
        return ($(this).text() === type);
    }).prop('selected', true);
    $("#cost" + row).val(formatNumber(costAmount));
    $("#amount" + row).val(formatNumber(costAmount));
    $("[name=cur" + row + "] option").filter(function() {
        return ($(this).text() === curCost);
    }).prop('selected', true);
    $("#amount" + row).val(formatNumber(costAmount));
    $("#saleAmount" + row).val(formatNumber(saleAmount));
    $("[name=saleCurrency" + row + "] option").filter(function() {
        return ($(this).text() === curSale);
    }).prop('selected', true);
    $("#payStockId" + row).val(stockId);
    $("#payStock" + row).val(payStockNo);
    $("#description" + row).val(detail);   

    row = row + 1;
    addRowPaymentDetailTable(row);
}

function checkVatAll() {
    var row = document.getElementById('countPaymentDetail').value;
    var mVat = document.getElementById('mVat').value;
    var check = 0;
    var unCheck = 0;
    for (var i = 1; i < row; i++) {
        var isVatCheck = document.getElementById("isVat" + i);
        if (isVatCheck !== null && isVatCheck !== '') {
            if (document.getElementById("isVat" + i).checked) {
                check++;
            } else {
                unCheck++;
            }
        }
    }

    if (check === 0 && unCheck !== 0) {
        for (var i = 1; i < row; i++) {
            var isVatCheck = document.getElementById("isVat" + i);
            if (isVatCheck !== null && isVatCheck !== '') {
                if (document.getElementById("isVat" + i).checked) {

                } else {
                    document.getElementById("isVat" + i).checked = true;
                    var vatDefaultData = parseFloat(document.getElementById('vat' + i).value);
                    var amountChk = document.getElementById('amount' + i);
                    if (amountChk !== null && amountChk !== '') {
                        var amount = document.getElementById('amount' + i).value;
                        var gross = document.getElementById('gross' + i).value;

                        amount = amount.replace(/,/g, "");
                        var grossTotal = parseFloat(amount);

                        if ((gross === '')) {
                            grossTotal = (amount * 100) / (100 + vatDefaultData);
                            document.getElementById('gross' + i).value = formatNumber(grossTotal);
                            document.getElementById('vatShow' + i).innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('gross' + i).value = '';
                            document.getElementById('vatShow' + i).innerHTML = '';
                        }
                    }
                }
            }
        }
        calculateGrossTotal();
        return;
    } else if (check > unCheck && unCheck !== 0) {
        for (var i = 1; i < row; i++) {
            var isVatCheck = document.getElementById("isVat" + i);
            if (isVatCheck !== null && isVatCheck !== '') {
                if (document.getElementById("isVat" + i).checked) {

                } else {
                    document.getElementById("isVat" + i).checked = true;
                    var vatDefaultData = parseFloat(document.getElementById('vat' + i).value);
                    var amountChk = document.getElementById('amount' + i);
                    if (amountChk !== null && amountChk !== '') {
                        var amount = document.getElementById('amount' + i).value;
                        var gross = document.getElementById('gross' + i).value;

                        amount = amount.replace(/,/g, "");
                        var grossTotal = parseFloat(amount);

                        if ((gross === '')) {
                            grossTotal = (amount * 100) / (100 + vatDefaultData);
                            document.getElementById('gross' + i).value = formatNumber(grossTotal);
                            document.getElementById('vatShow' + i).innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('gross' + i).value = '';
                            document.getElementById('vatShow' + i).innerHTML = '';
                        }
                    }
                }
            }
        }
        calculateGrossTotal();
        return;
    }

    if (check !== 0 && unCheck === 0) {
        for (var i = 1; i < row; i++) {
            var isVatCheck = document.getElementById("isVat" + i);
            if (isVatCheck !== null && isVatCheck !== '') {
                document.getElementById("isVat" + i).checked = false;
                document.getElementById('vatShow' + i).innerHTML = '';
                document.getElementById("gross" + i).value = '';
                document.getElementById('vat' + i).value = mVat;
            }
        }
        calculateGrossTotal();
        return;
    } else if (check < unCheck && check !== 0) {
        for (var i = 1; i < row; i++) {
            var isVatCheck = document.getElementById("isVat" + i);
            if (isVatCheck !== null && isVatCheck !== '') {
                document.getElementById("isVat" + i).checked = false;
                document.getElementById("vatShow" + i).innerHTML = '';
                document.getElementById("gross" + i).value = '';
                document.getElementById('vat' + i).value = mVat;
            }
        }
        calculateGrossTotal();
        return;
    }

    if (check === unCheck) {
        for (var i = 1; i < row; i++) {
            var isVatCheck = document.getElementById("isVat" + i);
            if (isVatCheck !== null && isVatCheck !== '') {
                if (document.getElementById("isVat" + i).checked) {

                } else {
                    document.getElementById("isVat" + i).checked = true;
                    var vatDefaultData = parseFloat(document.getElementById('vat' + i).value);
                    var amountChk = document.getElementById('amount' + i);
                    if (amountChk !== null && amountChk !== '') {
                        var amount = document.getElementById('amount' + i).value;
                        var gross = document.getElementById('gross' + i).value;

                        amount = amount.replace(/,/g, "");
                        var grossTotal = parseFloat(amount);

                        if ((gross === '')) {
                            grossTotal = (amount * 100) / (100 + vatDefaultData);
                            document.getElementById('gross' + i).value = formatNumber(grossTotal);
                            document.getElementById('vatShow' + i).innerHTML = vatDefaultData;
                        } else {
                            document.getElementById('gross' + i).value = '';
                            document.getElementById('vatShow' + i).innerHTML = '';
                        }
                    }
                }
            }
        }
        calculateGrossTotal();
    }
}

function editPaymentDetail(row) {
    $("#rowCommEdit").val(row);
    if (row !== $("#rowDetail").val()) {
        $("#rowDetail").val(row);
        $("#realExRate").val(($("#realExRate" + row).val() !== '' ? formatNumberFourDecimal(parseFloat(($("#realExRate" + row).val()).replace(/,/g, ""))) : ''));
        $("#payExRate").val(($("#payExRate" + row).val() !== '' ? formatNumberFourDecimal(parseFloat(($("#payExRate" + row).val()).replace(/,/g, ""))) : ''));
        $("#whtAmount").val(($("#whtAmount" + row).val() !== '' ? formatNumber(parseFloat(($("#whtAmount" + row).val()).replace(/,/g, ""))) : ''));
        $("#vatRecComAmount").val(($("#vatRecComAmount" + row).val() !== '' ? formatNumber(parseFloat(($("#vatRecComAmount" + row).val()).replace(/,/g, ""))) : ''));
        $("#value").val(($("#value" + row).val() !== '' ? formatNumber(parseFloat(($("#value" + row).val()).replace(/,/g, ""))) : ''));
        $("#wht").val(($("#wht" + row).val() !== '' ? (parseFloat(($("#wht" + row).val()).replace(/,/g, ""))) : ''));
        $("#vatRecCom").val(($("#vatRecCom" + row).val() !== '' ? (parseFloat(($("#vatRecCom" + row).val()).replace(/,/g, ""))) : ''));
        $("#comm").val(($("#comm" + row).val() !== '' ? (parseFloat(($("#comm" + row).val()).replace(/,/g, ""))) : ''));
        $('#isWht').prop('checked', ($("#isWht" + row).val() === '1' ? true : false));
        $('#isComVat').prop('checked', ($("#isComVat" + row).val() === '1' ? true : false));

        $("#paymentDescription").val(($("#description" + row).val()).replace(/\<br>/g,"\n"));

        $("#paymentDetailPanel").removeClass("hidden");

    } else {
        $("#rowDetail").val('');
        $("#realExRate").val('');
        $("#payExRate").val('');
        $("#whtAmount").val('');
        $("#vatRecComAmount").val('');
        $("#value").val('');
        $("#wht").val('');
        $("#vatRecCom").val('');
        $("#comm").val('');
        $('#isWht').prop('checked', false);
        $('#isComVat').prop('checked', false);
        $("#paymentDescription").val('');
        $("#paymentDetailPanel").addClass("hidden");
    }

}

function savePaymentDetail() {
    var row = $("#rowDetail").val();
    $("#realExRate" + row).val($("#realExRate").val());
    $("#payExRate" + row).val($("#payExRate").val());
    $("#whtAmount" + row).val($("#whtAmount").val());
    $("#vatRecComAmount" + row).val($("#vatRecComAmount").val());
    $("#value" + row).val($("#value").val());
    $("#wht" + row).val($("#wht").val());
    $("#vatRecCom" + row).val($("#vatRecCom").val());
    $("#isWht" + row).val(($("#isWht").is(':checked') ? '1' : '0'));
    $("#isComVat" + row).val(($("#isComVat").is(':checked') ? '1' : '0'));
    $("#description" + row).val(($("#paymentDescription").val()).replace(/\n/g, "<br>"));
    $("#comm" + row).val($("#comm").val());
    $("#rowDetail").val('');
    $("#realExRate").val('');
    $("#payExRate").val('');
    $("#whtAmount").val('');
    $("#vatRecComAmount").val('');
    $("#value").val('');
    $("#wht").val('');
    $("#vatRecCom").val('');
    $('#isWht').prop('checked', false);
    $('#isComVat').prop('checked', false);
    $("#paymentDescription").val('');
    $("#comm").val('');
    $("#paymentDetailPanel").addClass("hidden");
}

function calculateWhtAmount(newWht) {
    var row = $("#rowDetail").val();
    if ($("#isWht").is(':checked')) {       
        var isVat = ($("#isVat" + row).is(':checked') ? true : false);
        var isNewWht = $("#isNewWht" + row).val();
        
        var wht = 0.00;
        if(newWht === '' && isNewWht !== '1'){
            wht = ($("#whtTemp" + row).val() === '' ? parseFloat($("#mWht").val()) : parseFloat($("#whtTemp" + row).val()));           
        }else{
            wht = ($("#wht").val() === '' ? parseFloat($("#mWht").val()) : parseFloat($("#wht").val()));
            $("#isNewWht" + row).val('1');
        }
        
        var money = 0.00;
        if(isVat){
            money = ($("#gross" + row).val() !== '' ? parseFloat(($("#gross" + row).val()).replace(/,/g, "")) : 0.00);
        }else{
            money = ($("#amount" + row).val() !== '' ? parseFloat(($("#amount" + row).val()).replace(/,/g, "")) : 0.00);
        }
        var whtAmount = money * (wht / 100);
        $("#whtAmount").val(formatNumber(whtAmount));
        calculateTotalWht(formatNumber(whtAmount),row);
        $("#wht").val(wht);
        if ($("#whtTemp" + row).val() === '') {
            $("#whtTemp" + row).val(parseFloat($("#mWht").val()));
        } else {
            $("#whtTemp" + row).val(parseFloat($("#mWht").val()));
        }
    } else {
        $("#wht").val('');
        $("#whtAmount").val('');
        calculateTotalWht(formatNumber(0),row);
        $("#isNewWht" + row).val('0');
    }
}

function calculateVatRecComAmount() {
    var row = $("#rowDetail").val();
    var rowCommEdit = $("#rowCommEdit").val();
    if ($("#isComVat").is(':checked')) {
        var vatRecCom = ($("#vatRecCom" + row).val() === '' ? parseFloat($("#mVat").val()) : parseFloat($("#vatRecCom" + row).val()));
        var comm = ($("#comm").val() !== '' ? parseFloat(($("#comm").val()).replace(/,/g, "")) : 0.00);
        var vatRecComAmount = (comm * 100)/(100 + vatRecCom);
        $("#vatRecComAmount").val(formatNumber(vatRecComAmount));
        $("#vatRecCom").val(vatRecCom);
        if ($("#vatRecComTemp" + row).val() === '') {
            $("#vatRecComTemp" + row).val(parseFloat($("#mVat").val()));
        } else {
            $("#vatRecComTemp" + row).val(parseFloat($("#mVat").val()));
        }
        calculateTotalCom(formatNumber(comm),rowCommEdit);
    } else {
        calculateTotalCom(formatNumber($("#comm").val() !== '' ? parseFloat(($("#comm").val()).replace(/,/g, "")) : 0.00),rowCommEdit);
        $("#vatRecCom").val('');
        $("#vatRecComAmount").val('');
    }
}
function showSearchStock() {
    if ($("#searchStock1").hasClass("hidden")) {
        $("#searchRefNo1").addClass("hidden");
        $("#searchRefNo2").addClass("hidden");
        $("#searchStock1").removeClass("hidden");
    } else {
        $("#searchStock1").addClass("hidden");
    }
    $("#searchStock2").addClass("hidden");
}

function showSearchRefNo() {
    if ($("#searchRefNo1").hasClass("hidden")) {
        $("#searchStock1").addClass("hidden");
        $("#searchStock2").addClass("hidden");
        $("#searchRefNo1").removeClass("hidden");
    } else {
        $("#searchRefNo1").addClass("hidden");
    }
    $("#searchRefNo2").addClass("hidden");
}

//Validate Pay Stock
function checkPayStock(row) {
    var payStockNo = $("#payStock" + row).val();
    if (payStockNo !== '') {
        var servletName = 'PaymentOutboundServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&payStockNo=' + payStockNo +
                '&type=' + 'checkPayStock';
        CallAjaxCheckStock(param, row);
    } else {
        var payStockField = document.getElementById('payStock' + row);
        payStockField.style.borderColor = "";
    }
    validatePaymentOutbound("paymentOutboundDetail");
}

function CallAjaxCheckStock(param, row) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                try {
                    if (msg === 'success') {
                        var payStockField = document.getElementById('payStock' + row);
                        payStockField.style.borderColor = "green";

                    } else if (msg === 'fail') {
                        var payStockField = document.getElementById('payStock' + row);
                        payStockField.style.borderColor = "red";
                    }

                } catch (e) {

                }

            }, error: function(msg) {

            }
        });
    } catch (e) {

    }
}

function validatePaymentOutbound(option) {
    $("#textAlertCurrencyNotMatch").hide();
    $("#textAlertCurrencyNotEmpty").hide();
    var count = parseInt($("#countPaymentDetail").val());
    var lengthTable = $("#PaymentDetailTable tr").length;
    var booleanPaymentOutbound = true;
    var booleanRefNo = true;
    var booleanPayStock = true;
    var booleanCurrency = true;
    var payDate = $("#payDate").val();
    var account1 = $("#account1").is(":checked");
    var account2 = $("#account2").is(":checked");
    var invSupApCode = $("#invSupApCode").val();
    var status = $("#status").val();
    var currencyMessage = "";

    if (option === 'paymentOutboundDetail' || option === 'paymentOutbound' || option === 'save') {
        for (var i = 1; i < count; i++) {
            var refNoField = document.getElementById('refNo' + i);
            if (refNoField !== null) {
                if (refNoField.style.borderColor === "red") {
                    $("#btnSave").addClass("disabled");
                    booleanRefNo = false;
                    i = count;
                }
            }
        }

        for (var i = 1; i < count; i++) {
            var payStockField = document.getElementById('payStock' + i);
            if (payStockField !== null) {
                if (payStockField.style.borderColor === "red") {
                    $("#btnSave").addClass("disabled");
                    booleanPayStock = false;
                    i = count;
                }
            }
        }
    }

    if (option === 'paymentOutbound' || option === 'save') {
        if (payDate === '' || invSupApCode === '' || status === '' || !(account1 || account2)) {
            $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'payDate');
            $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');
            $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'status');
            $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'account');
            booleanPaymentOutbound = false;
        }
    }

    if (booleanPaymentOutbound && booleanRefNo && booleanPayStock) {
        $("#btnSave").removeClass("disabled");
        if (option === 'save') {
            var currencyNotMatch = false;
            var currencyNotEmpty = 0;
            for(var i=1; i<=count; i++){
                var currency1 = document.getElementById('cur'+i);               
                
                if(currency1 !== null){
                    var type1 = document.getElementById('type'+i).value;
                    var refNo1 = document.getElementById('refNo'+i).value;
                    var invoice1 = document.getElementById('invoice'+i).value;
                    var cost1 = document.getElementById('cost'+i).value;
                    var gross1 = document.getElementById('gross'+i).value;
                    var amount1 = document.getElementById('amount'+i).value;
                    var comm1 = document.getElementById('amount'+i).value;
                    var description1 = document.getElementById('description'+i).value;
                    var payStock1 = document.getElementById('payStock'+i).value;
                    var saleAmount1 = document.getElementById('saleAmount'+i).value;
                    var saleCurrency1 = document.getElementById('saleCurrency'+i).value;
                    if(currency1.value !== '' || type1 !== '' || refNo1 !== '' || invoice1 !== '' || cost1 !== '' || gross1 !== '' || amount1 !== '' || 
                            comm1 !== '' || description1 !== '' || payStock1 !== '' || saleAmount1 !== '' || saleCurrency1 !== ''){
                        var currencyTemp1 = currency1.value;
                        for(var j=i+1; j<=count; j++){
                            var currency2 = document.getElementById('cur'+j);

                            if(currency2 !== null){
                                var type2 = document.getElementById('type'+j).value;
                                var refNo2 = document.getElementById('refNo'+j).value;
                                var invoice2 = document.getElementById('invoice'+j).value;
                                var cost2 = document.getElementById('cost'+j).value;
                                var gross2 = document.getElementById('gross'+j).value;
                                var amount2 = document.getElementById('amount'+j).value;
                                var comm2 = document.getElementById('amount'+j).value;
                                var description2 = document.getElementById('description'+j).value;
                                var payStock2 = document.getElementById('payStock'+j).value;
                                var saleAmount2 = document.getElementById('saleAmount'+j).value;
                                var saleCurrency2 = document.getElementById('saleCurrency'+j).value;
                                
                                var currencyTemp2 = currency2.value;
                                if(currency2.value !== '' || type2 !== '' || refNo2 !== '' || invoice2 !== '' || cost2 !== '' || gross2 !== '' || amount2 !== '' || 
                                        comm2 !== '' || description2 !== '' || payStock2 !== '' || saleAmount2 !== '' || saleCurrency2 !== ''){                                                                 
                                    if((currencyTemp1 !== currencyTemp2)){
                                        currencyNotMatch = true;
                                        booleanCurrency = false;
                                        i = count+1;
                                        j = count+1;
                                    }                               
                                }
                                if(currencyTemp1 === '' && currencyTemp2 === ''){
                                    currencyNotEmpty++;
                                    booleanCurrency = false;
                                }
                            }
                        }
                    }    
                }    
            }
            if(currencyNotMatch){
                $("#textAlertCurrencyNotMatch").hide();
                $("#textAlertCurrencyNotEmpty").hide();
               for(var i=1; i<=count; i++){
                    var currency = document.getElementById('cur'+i);
 
                    if(currency !== null){
                        var type = document.getElementById('type'+i).value;
                        var refNo = document.getElementById('refNo'+i).value;
                        var invoice = document.getElementById('invoice'+i).value;
                        var cost = document.getElementById('cost'+i).value;
                        var gross = document.getElementById('gross'+i).value;
                        var amount = document.getElementById('amount'+i).value;
                        var comm = document.getElementById('amount'+i).value;
                        var description = document.getElementById('description'+i).value;
                        var payStock = document.getElementById('payStock'+i).value;
                        var saleAmount = document.getElementById('saleAmount'+i).value;
                        var saleCurrency = document.getElementById('saleCurrency'+i).value;
                        if(currency.value !== '' || type !== '' || refNo !== '' || invoice !== '' || cost !== '' || gross !== '' || amount !== '' || 
                            comm !== '' || description !== '' || payStock !== '' || saleAmount !== '' || saleCurrency !== ''){
                            currency.style.borderColor = 'red';
                            booleanCurrency = false;
                        }    
                    }    
                }
                currencyMessage = "notMatch";
//                $("#textAlertCurrencyAmountNotMatch").show();
//                return;
            }
            if(currencyNotEmpty > 0){
                $("#textAlertCurrencyNotMatch").hide();
                $("#textAlertCurrencyNotEmpty").hide();
                for(var i=1; i<=count; i++){
                    var currency = document.getElementById('cur'+i);
                    
                    if(currency !== null){
                        var type = document.getElementById('type'+i).value;
                        var refNo = document.getElementById('refNo'+i).value;
                        var invoice = document.getElementById('invoice'+i).value;
                        var cost = document.getElementById('cost'+i).value;
                        var gross = document.getElementById('gross'+i).value;
                        var amount = document.getElementById('amount'+i).value;
                        var comm = document.getElementById('amount'+i).value;
                        var description = document.getElementById('description'+i).value;
                        var payStock = document.getElementById('payStock'+i).value;
                        var saleAmount = document.getElementById('saleAmount'+i).value;
                        var saleCurrency = document.getElementById('saleCurrency'+i).value;
                        if(currency.value !== '' || type !== '' || refNo !== '' || invoice !== '' || cost !== '' || gross !== '' || amount !== '' || 
                            comm !== '' || description !== '' || payStock !== '' || saleAmount !== '' || saleCurrency !== ''){
                            currency.style.borderColor = 'red';
                            booleanCurrency = false;
                        }    
                    }    
                }
                currencyMessage = "empty";
//                $("#textAlertCurrencyAmountNotEmpty").show();
//                return;
            }
            
//            for (var i = 1; i < count; i++) {
//                var curField1 = document.getElementById('cur' + i);
//                if (curField1 !== null) {
//                    var cur1 = curField1.value;
//                    for (var j = i+1; j < count; j++) {
//                        var curField2 = document.getElementById('cur' + j);
//                        if (curField2 !== null) {
//                            var cur2 = curField2.value;
//                            if(cur1 !== cur2 && cur2 !== ''){
//                                booleanCurrency = false;
//                                i = count;
//                                j = count;
//                                currencyMessage = "notMatch";
//                            }else if(cur1 === '' && cur2 === ''){
//                                booleanCurrency = false;
//                                i = count;
//                                j = count;
//                                currencyMessage = "empty";
//                            }
//                        }
//                    }
//                }
//            }
            if(booleanCurrency){
                $("#action").val("save");
                document.getElementById("PaymentOutboundForm").submit();
            }else{
                checkCurrency(currencyMessage);
            }
        }
    } else {
        $("#btnSave").addClass("disabled");
    }
   
}

function checkCurrency(option) {
//    var count = parseInt($("#countPaymentDetail").val());
//    for (var i = 1; i <= count; i++) {
//        var curField = document.getElementById('cur' + i);
//        if(curField != null){
//            curField.style.borderColor = "red";
//        }    
//    }
    if(option === 'notMatch'){
        $("#textAlertCurrencyNotMatch").show();
    }else if(option === 'empty'){
        $("#textAlertCurrencyNotEmpty").show();
    }
    
}

function hideTextAlertDiv(){
    $("#textAlertDivSave").hide();
    $("#textAlertDivNotSave").hide();
    $("#textAlertNotFound").hide();   
    $("#textAlertCurrencyNotMatch").hide();
    $("#textAlertCurrencyNotEmpty").hide();
    $("#textAlertDuplicateStock").hide();
    $("#textAlertDuplicateRefNo").hide();
    $("#textAlertSearchDuplicateStock").hide();
}

function setDescription(){
    var count = parseInt($("#countPaymentDetail").val());
    for(var i=1; i<=count; i++){
        if($("#description" + i).val() !== undefined && $("#description" + i).val() !== ''){         
            var descriptionTemp = ($("#descriptionTemp"+i).val()).replace(/\n/g, "<br>");
            $("#description" + i).val(descriptionTemp);
        }
    }
}

function editDescription(row){
    if($("#rowDetail").val() === row){
        if($("#description"+row).val() !== ''){
            var description = ($("#description"+row).val()).replace(/<br>/g, "\n");
            $("#paymentDescription").val(description); 
        }        
    }   
}

function printPaymentOutboundReport(optionReport){
    var paymentOutboundId = $("#payId").val();
    window.open("report.smi?name=PaymentOutboundReport&paymentOutboundId=" + paymentOutboundId +"&optionReport="+optionReport);
}

function replaceAll(find, replace, str) {
    return str.replace(new RegExp(find, 'g'), replace);
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
    $("#invSupCode").autocomplete("destroy");
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
               $("#invSupId").val(invId);
               $("#invSupCode").val(invCode);
               $("#invSupName").val(invName);
               $("#invSupApCode").val(invARCode);

               $("#invSupCode").autocomplete({
                   source: invArray,
                   close: function(){
                        $("#invSupCode").trigger("keyup");
                        var invselect = $("#invSupCode").val();
                        for(var i =0;i<invIdList.length;i++){
                            if((invselect==invCodeList[i])||(invselect==invNameList[i])){      
                               $("#invSupId").val(invIdList[i]);
                               $("#invSupCode").val(invCodeList[i]);
                               $("#invSupName").val(invNameList[i]);
                               $("#invSupApCode").val(invARCodeList[i]);
                               $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');
                            }                 
                        }   
                   }
                });

               var invval = $("#invSupCode").val();
               for(var i =0;i<invIdList.length;i++){
                   if(invval==invNameList[i]){
                       $("#invSupId").val(invIdList[i]);
                       $("#invSupCode").val(invCodeList[i]);
                       $("#invSupName").val(invNameList[i]);
                       $("#invSupApCode").val(invARCodeList[i]);
                       $('#PaymentOutboundForm').bootstrapValidator('revalidateField', 'invSupApCode');
                   }
               }
               if(invIdList.length == 1){
                   showflag = 0;
                   $("#invSupId").val(invIdList[0]);
                   $("#invSupCode").val(invCodeList[0]);
                   $("#invSupName").val(invNameList[0]);
                   $("#invSupApCode").val(invARCodeList[0]);
               }
               var event = jQuery.Event('keydown');
               event.keyCode = 40;
               $("#invSupCode").trigger(event);

            }, error: function(msg) {
               console.log('auto ERROR');
               $("#dataload").addClass("hidden");
            }
        });
    } catch (e) {

    }
}


