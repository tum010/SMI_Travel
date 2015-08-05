//  Booking Expense
//      auto add row where input value in row

var guideList = [];
$(document).ready(function () {  
    checkExpenseTour();
    $(".money").mask('000,000,000,000,000,000', {reverse: true});
    $("a").click(function () {
        $(".collapse").collapse('hide');
    });

    $('#PacketTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    window.expensTable = $('#toureExpenTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    var sumConfirm = 0;
    $(".sumConfirm").each(function () {
        sumConfirm += +$(this).text();
    });
    $("#InputTourDetailConfirm").val(sumConfirm);
    /*
     $('#PacketTable tbody').on('click', 'tr', function () {
     $(this).addClass('row_selected').siblings().removeClass('row_selected');
     var packId = $(this).find(".pack-id").text();
     var packDate = $(this).find(".pack-date").text();
     $("#InputDetailTourCode").val(packId);
     $("#InputTourDetailTourDate").val(packDate);
     $("#ListPacketModal").modal('hide');
     $("#info,#master").removeClass('hidden');
     });
     */

    var rowDriver = $("#BookingDriverTable tr").length;
    BookingDriverTableAddRow(rowDriver);

    var rowExpen = $("#BookingExpenseTable tr").length;
    BookingExpenseTableAddRow(rowExpen);

    $('#SelectGuideCode1').selectize({
        sortField: 'text'
    });
    $('#SelectGuideCode2').selectize({
        sortField: 'text'
    });


    $("#BookingDriverTable").on("keyup", function () {
        var rowAll = $("#BookingDriverTable tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() != '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#BookingDriverTable tr").length;
                //console.log('Row: ' + rowIndex + ', Column: ' + colIndex + ', All Row ' + rowAll);
                if (rowIndex == rowAll) {
                    //console.log("rowIndex: " + rowIndex);
                    //console.log("rowAll : " + rowAll);
                    //console.log("addRow");
                    if (rowAll <= 5) {
                        BookingDriverTableAddRow(rowAll);
                    }
                }
            }
        });
    });
    $("#BookingExpenseTable").on("keyup", function () {
        var rowAll = $("#BookingExpenseTable tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#BookingExpenseTable tr").length;
                //console.log('Row: ' + rowIndex + ', Column: ' + colIndex + ', All Row ' + rowAll);
                if (rowIndex == rowAll) {
                    //console.log("rowIndex: " + rowIndex);
                    //console.log("rowAll : " + rowAll);
                    //console.log("addRow");
                    BookingExpenseTableAddRow(rowAll);
                }
            }
        });
    });
    var tourId = $("#tourID").val();
    if (tourId != '') {
        $("#info,#master").removeClass('hidden');
    }
    
    $('#InputDatePicker').datetimepicker({
        }).on('change', function(e) {
            $('#DaytourOperationForm').bootstrapValidator('revalidateField', 'InputTourDetailTourDate');
    });
    $("#DaytourOperationForm")
            .bootstrapValidator({
//                framework: 'bootstrap',
                container: 'tooltip',
                excluded: [':disabled', ':hidden', ':not(:visible)'],
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    InputDetailTourCode: {
                        trigger: 'focus keyup',
                        validators: {
                            notEmpty: {trigger: 'change',
                                message: ' Tour Code is required'
                            }
                        }
                    },
                    InputTourDetailTourDate: {
                        validators: {
                            notEmpty: {
                                message: ' Tour Date is required'
                            }
                        }
                    }
          
                }
            })
            .on('success.field.fv', function (e, data) {
                if (data.field === 'InputTourDetailTourDate' && data.fv.isValidField('InputTourDetailTourDate') === false) {
                    data.fv.revalidateField('InputTourDetailTourDate');
                }
                if (data.field === 'InputDetailTourCode' && data.fv.isValidField('InputDetailTourCode') === false) {
                    data.fv.revalidateField('InputDetailTourCode');
                }
            });
            
});

function selectTour(element) {
    var selectedTd = element.parentNode;
    var selectedRow = selectedTd.parentNode;
    $(selectedRow).addClass('row_selected').siblings().removeClass('row_selected');
    var packId = $(selectedRow).find(".pack-id").text();
    var packDate = $(selectedRow).find(".pack-date").text();
    var packGuide = $(selectedRow).find(".pack-guide").text();
    $("#InputDetailTourCode").val(packId);
    $("#InputTourDetailTourDate").val(packDate);
    $("#InputTourDetailConfirm").val(packGuide);

    $("#ListPacketModal").modal('hide');

}

// driverTable add row
function BookingDriverTableAddRow(row) {
    if (!row) {
        row = 1;
    }
    if(row!==6){
    $("#BookingDriverTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input id="countInfoTable' + row + '" name="countInfoTable"  type="text" value="' + row + '">' +
            '<td class="text-center">' + row + '</td>' +
            '<td><select name="SelectTableDrive' + row + '" id="SelectTableDrive' + row + '" class=""><option value=""></option></select></td>' +
            '<td id="driverName' + row + '"></td>' +
            '<td id="driverCarNo' + row + '"><input name="carNo' + row + '" type="hidden"></td>' +
            '<td class="hidden"><div class="col-sm-8"><input id="carNo' + row + '" name="carNo' + row + '" type="text" class="" maxlength="252" value=""></div>' +
            '<td><div class="col-sm-7"><input id="InfoTableGasFee' + row + '" name="InfoTableGasFee' + row + '" type="text" class="form-control " maxlength="252"></div>' +
            '<div class="col-sm-5"><input id="InfoTableGasValue' + row + '" name="InfoTableGasValue' + row + '" type="text" class="form-control money" maxlength="252"></div></td>' +
            '<td><div class="col-sm-7"><input id="InfoTableTipFee' + row + '" name="InfoTableTipFee' + row + '" type="text" class="form-control " maxlength="252"></div>' +
            '<div class="col-sm-5"><input id="InfoTableTipValue' + row + '" name="InfoTableTipValue' + row + '" type="text" class="form-control money" maxlength="252" onfocusout="calculateGuideBill()"></td></div>' +
            '<td class="text-center">' +
            '<a id="expenButtonRemove' + row + '" name="expenButtonRemove' + row + '" class="RemoveRow" onclick="calculateGuideBill()">' +
            '<span  id="expenSpanEdit' + row + '" name="expenSpanEdit' + row + '" class="glyphicon glyphicon-remove deleteicon"></span>' +
            '</a></td>' +
            '</tr>'
            );
    }
    $.each(driver, function (i, item) {
        $('#SelectTableDrive' + row).append($('<option>', {
            value: item.id,
            text: item.name
        }));


    });
    $('#SelectTableDrive' + row).selectize({
        sortField: 'text',
        dropdownParent: 'body'
    });

    $("#SelectTableDrive" + row).change(function () {
        var driverId = $(this).val();
        console.log(driverId);
        $.each(driver, function (i, dri) {
            if (driverId == dri.id) {
                var id = dri.id;
                var name = dri.name;
                var username = dri.username;
                var car = dri.car;
                console.log(name);
                $("#driverName" + row).text(name);
                $("#driverCarNo" + row).text(car);
                $("#carNo" + row).val(car);
            }
        });

        var rowAll = $("#BookingDriverTable tr").length;
        var rowIndex = $(this).parent().parent().index() + 2;
        console.log(rowAll);
        console.log(rowIndex);
        if (rowIndex == rowAll) {
            if (rowAll <= 5) {
                BookingDriverTableAddRow(rowAll);

            }
        }


    });
    $("input[name=countInfoTable]").val(row);
}

// expanseTable add row
function BookingExpenseTableAddRow(row) {
    if (!row) {
        row = 1;
    }
    $("#BookingExpenseTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input id="expenId' + row + '" name="expenId' + row + '"  type="text">' +
            '<td class="hidden"><input id="countExpen' + row + '" name="countExpen"  type="text" value="' + row + '">' +
            '<td><input id="expenDescription' + row + '" name="expenDescription' + row + '"  type="text" class="form-control" maxlength="50"></td>' +
            '<td style="width:80px"><input id="expenQty' + row + '" name="expenQty' + row + '" type="text" class="form-control money" maxlength="50" onfocusout="calculateGuideBill()"></td>' +
            '<td style="width: 100px"><input id="expenAmount' + row + '" name="expenAmount' + row + '" type="text" class="form-control money" maxlength="50" onfocusout="calculateGuideBill()"></td>' +       
//            '<td><select name="expenSelectCur' + row + '" id="expenSelectCur' + row + '" class=""><option value=""></option></select></td>' +
            '<td class="text-center"><input id="expenTypeS' + row + '" name="expenPriceType' + row + '" type="radio" value="S" checked="checked" onclick="calculateGuideBill()">&nbsp;&nbsp;S&nbsp;&nbsp;&nbsp;&nbsp;' +
            '<input id="expenTypeG' + row + '" name="expenPriceType' + row + '" type="radio" value="G" onclick="calculateGuideBill()">&nbsp;&nbsp;G</td>' +
            '<td class="text-center">' +
            '<a id="expenButtonRemove' + row + '" name="expenButtonRemove' + row + '" class="RemoveRow" onclick="calculateGuideBill()">' +
            '<span  id="expenSpanEdit' + row + '" name="expenSpanEdit' + row + '" class="glyphicon glyphicon-remove deleteicon"></span>' +
            '</a></td>' +
            '</tr>'
            );
    $("input[name=countExpen]").val(row);

//    $.each(currency,function(i,item){
//        $('#expenSelectCur' + row).append($('<option>', {
//            value: item.code,
//            text: item.code
//        }));
//    });
//    $('#expenSelectCur' + row).selectize({
//        sortField: 'text'
//    });
}


// import expen
function importExpen() {
    var arrExpen = [];
    var expenseTrNode = $(window.expensTable.fnGetNodes());
    expenseTrNode.find(':checked').each(function (i, item) {
        var tr = $(this).parent().parent();
        var id = tr.find('td:eq(0)').text();
        var desciption = tr.find('td:eq(1)').text();
        var amount = tr.find('td:eq(2)').text();
        var cur = tr.find('td:eq(3)').text();
        var priceType = tr.find('td:eq(5)').text();
        arrExpen.push({id: id, desciption: desciption, amount: amount, cur: cur,priceType: priceType});
        $(this).attr('checked', false);
        tr.addClass("hidden");
    });
    console.log(arrExpen);
    addImportExpen(arrExpen);

}

function expenseAll(element){
   var checkStatus = $(element).prop('checked');
   var expenseTrNode = $(window.expensTable.fnGetNodes());
   $.each(expenseTrNode, function (key, value) {
        $(value).find('input:checkbox').prop('checked',checkStatus);
    });
   
}

function addImportExpen(arrExpen) {
    $.each(arrExpen, function (i, item) {
        var row = $("#BookingExpenseTable tr").length;
        $("#BookingExpenseTable tr:last").before(
                '<tr>' +
                '<td class="hidden"><input id="expenId' + row + '" name="expenId' + row + '"  type="text">' +
                '<td class="hidden"><input id="countExpen' + row + '" name="countExpen"  type="text" value="' + row + '">' +
                '<td><input id="expenDescription' + row + '" name="expenDescription' + row + '"  type="text" class="form-control text-left" maxlength="50" value="' + item.desciption + '"></td>' +
                '<td style="width: 80px"><input id="expenQty' + row + '" name="expenQty' + row + '" type="text" class="form-control money" maxlength="50"></td>' +
                '<td style="width: 100px"><input id="expenAmount' + row + '" name="expenAmount' + row + '" type="text" class="form-control money" maxlength="50" value="' + item.amount + '" onfocusout="calculateGuideBill()"></td>' +
//                '<td><select name="expenSelectCur' + row + '" id="expenSelectCur' + row + '" class="form-control"><option value="' + item.cur + '">' + item.cur + '</option></select></td>' +
                '<td class="text-center"><input id="expenTypeS' + row + '" name="expenPriceType' + row + '" type="radio" value="S" '+(item.priceType==="S"?"checked":"")+ ' >&nbsp;&nbsp;S&nbsp;&nbsp;&nbsp;&nbsp;' +
                '<input id="expenTypeG' + row + '" name="expenPriceType' + row + '" type="radio" value="G" '+(item.priceType==="G"?"checked":"")+ ' >&nbsp;&nbsp;G</td>' +
                '<td class="text-center">' +
                '<a id="expenButtonRemove' + row + '" idExpen="'+item.id+'" name="expenButtonRemove' + row + '" class="RemoveRow" onclick="calculateGuideBill()">' +
                '<span  id="expenSpanEdit' + row + '" name="expenSpanEdit' + row + '" class="glyphicon glyphicon-remove deleteicon"></span>' +
                '</a></td>' +
                '</tr>'
                );
        $("input[name=countExpen]").val(row);
        console.log('row :' + row);

    });

}

//      remove row
$(document).ready(function () {
    $("#BookingDriverTable").on('click', '.RemoveRow', function () {
//        $(this).parent().parent().remove
        var lentable =  $("#BookingDriverTable tbody").find("tr").length;
        if(lentable === 1){
            alert("this row for add data");
        }else{
            $(this).parent().parent().remove(); 
        }
        calculateGuideBill();
    });
    $("#BookingExpenseTable").on('click', '.RemoveRow', function () {
       var lentable =  $("#BookingExpenseTable tbody").find("tr").length;
       console.log("lentable"+lentable);
        if($(this).attr('idExpen')){
            var expenseTrNode = $(window.expensTable.fnGetNodes());
            var delId = $(this).attr('idExpen');
            $.each(expenseTrNode, function (key, value) {
                if($(value).attr('id')===('expenID-'+delId)){
                   $(value).removeClass("hidden");
                }               
            });
        }else{
            if(lentable === 1){
                alert("this row for add data");
            }else{
                $(this).parent().parent().remove(); 
            }
                
        }
        calculateGuideBill();

    });   
   
    $("#SelectGuideCode1").on("change", function () {
        setGuideName($('#SelectGuideCode1 option:selected').text(),'name1');
    });
    
    $("#SelectGuideCode2").on("change", function () {
        setGuideName($('#SelectGuideCode2 option:selected').text(),'name2');
    });    
    
    $('#InvoiceSupGuideBill').ready(function () {
        setGuideName('','ready');
    });

});

function setGuideName(name,no){
    if(no === 'name1'){
        guideList = [];
        guideList.push({name: name});
        var name2 = document.getElementById('InputGuideName2').value;
        guideList.push({name: name2});
    }

    if(no === 'name2'){
        guideList = [];
        var name1 = document.getElementById('InputGuideName1').value;
        guideList.push({name: name1});
        guideList.push({name: name});
    }
    
    if(no === 'ready'){
        var name1 = document.getElementById('InputGuideName1').value;
        var name2 = document.getElementById('InputGuideName2').value;       
        guideList.push({name: name1});
        guideList.push({name: name2});
        
        var guideName = document.getElementById('guideName').value;
        if(guideName !== ''){
            if(name1 === guideName){
                document.getElementById("InvoiceSupGuideBill").selectedIndex = 1;
            } else {
                document.getElementById("InvoiceSupGuideBill").selectedIndex = 2;
            }           
        }
    }
    
    document.getElementById('GuideNo1').innerHTML = guideList[0].name;
    document.getElementById('GuideNo1').value = guideList[0].name;
    document.getElementById('GuideNo2').innerHTML = guideList[1].name;
    document.getElementById('GuideNo2').value = guideList[1].name;
    
}


function  setDriverId(id, name) {
    driverId = id;
    $("#driverNameDelete").text(' Are you sure to delete Booking Driver ' + name + '?');
}

function  deleteBookDriver() {
    $.ajax({
        url: 'DaytourOperationDetail.smi?action=deleteBookingDriver',
        type: 'post',
        data: {action: 'deleteBookingDriver', driverId: driverId},
        success: function () {
            console.log('success');
            alert('Delete Booking Driver successful');
            location.reload();
        },
        error: function () {
            console.log("error");
        }
    });

}

function  setExpenId(id) {
    expenId = id;
}

function  deleteBookExpen() {
    $.ajax({
        url: 'DaytourOperationDetail.smi?action=deleteBookingExpense',
        type: 'post',
        data: {action: 'deleteBookingExpense', refBookId: expenId},
        success: function () {
            console.log('success');
            alert('Delete Booking Expense successful');
            location.reload();
            calculateGuideBill();
        },
        error: function () {
            console.log("error");
        }
    });

}

function printGuideJob() {
    var inputDetailTourCode = document.getElementById("InputDetailTourCode").value;
    var inputTourDetailTourDate = document.getElementById("InputTourDetailTourDate").value;
    window.open("report.smi?name=GuideJob&tourdate=" + inputTourDetailTourDate + "&tourcode=" + inputDetailTourCode);
}

function checkExpenseTour(){
    var booking = document.getElementById('BookingExpenseTable').tBodies[0];
    for (var r=0, n = booking.rows.length; r < n; r++) {
        $('#toureExpenTable > tbody  > tr  ').each(
        function() {
                var book = booking.rows[r].cells[1].childNodes[0].value;
                var tour = $(this).find("#rowImportExpenDescription").html();
                var tourId = $(this).find("#rowImportExpenId").html();
                if(book === tour){
                     $('#expenID-'+tourId).hide();
                }         
        });  
    }
}

// Calculte Money
$(document).ready(function () {
    var guideList = [];
    $( ".numerical" ).on('input', function() { 
        var value=$(this).val().replace(/[^0-9.,]*/g, '');
        value=value.replace(/\.{2,}/g, '.');
        value=value.replace(/\.,/g, ',');
        value=value.replace(/\,\./g, ',');
        value=value.replace(/\,{2,}/g, ',');
        value=value.replace(/\.[0-9]+\./g, '.');
        $(this).val(value)
    });
    
    $('#InputGuideBill').ready(function () {
        calculateGuideBill();
    });
    
    $('#AmountGuideBill').ready(function () {
        confirmCheckboxGuideBill();
    });
   
});


function calculateGuideBill(){
    var guideBillTotal = 0;
    
    var rowDriver = $("#BookingDriverTable tr").length;
    for(var i=0;i<rowDriver;i++){
        var tipValue = document.getElementById('InfoTableTipValue'+i);
        if(tipValue !== null){
            if(tipValue.value !== ''){
                var tipValueReplace = document.getElementById('InfoTableTipValue'+i).value;
                tipValueReplace = tipValueReplace.replace(/,/g,"");
                var tipValue = parseFloat(tipValueReplace);
                guideBillTotal += tipValue;
            }
        }
    }
    
    var total = 0;
    var countCheckbox = 0;
    var rowExpen = $("#BookingExpenseTable tr").length;
    for(var i=0;i<rowExpen;i++){
        var expenAmount = document.getElementById('expenAmount'+i);
        var expenQty = document.getElementById('expenQty'+i);
        if((expenAmount !== null) && (expenQty !== null)){
            if((expenAmount.value !== '') && (expenQty.value !== '')){
                var expenAmountReplace = document.getElementById('expenAmount'+i).value;
                var expenQtyReplace = document.getElementById('expenQty'+i).value;
                var checkbox = document.getElementById('expenTypeG'+i);
                if(checkbox !== null){            
                    if(document.getElementById('expenTypeG'+i).checked){
                        expenAmountReplace = expenAmountReplace.replace(/,/g,"");
                        expenQtyReplace = expenQtyReplace.replace(/,/g,"");
                        var expenAmountValue = parseFloat(expenAmountReplace);
                        var expenQtyValue = parseFloat(expenQtyReplace);
                        guideBillTotal += (expenAmountValue*expenQtyValue);
                        total += (expenAmountValue*expenQtyValue);
                        countCheckbox++;
                    } 
                }
            }    
        }
    }
    
    if(countCheckbox === 0){
        var nodes = document.getElementById("GuideBillForm").getElementsByTagName('*');
        for(var i = 0; i < nodes.length; i++){
            nodes[i].disabled = true;
        }       
        document.getElementById('ConfirmGuideBill').checked = false;
        document.getElementById('AmountGuideBill').value = '';
        
    } else {
        var nodes = document.getElementById("GuideBillForm").getElementsByTagName('*');
        for(var i = 0; i < nodes.length; i++){
            nodes[i].disabled = false;
        }
     
    }
    
    document.getElementById('InputGuideBill').value = formatNumber(guideBillTotal);  
    document.getElementById('InputTotal').value = formatNumber(total);
    
    var check = document.getElementById('ConfirmGuideBill').checked;
    if(check){
        document.getElementById('AmountGuideBill').value = formatNumber(guideBillTotal);
    }
    
}

function confirmCheckboxGuideBill(){
    var check = document.getElementById('ConfirmGuideBill').checked;
    if(check){
        var InputGuideBill = document.getElementById('InputGuideBill').value;
        document.getElementById('AmountGuideBill').value = InputGuideBill;
    } else {
        var amountDefault = parseFloat(document.getElementById('AmountGuideBillDefault').value);
        document.getElementById('AmountGuideBill').value = formatNumber(amountDefault);
    }   
}

function formatNumber(num) {
    return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
}

function insertCommas(nField){
    if (/^0/.test(nField.value)){
        nField.value = nField.value.substring(0,1);
    }
    if (Number(nField.value.replace(/,/g,""))){
        var tmp = nField.value.replace(/,/g,"");
        tmp = tmp.toString().split('').reverse().join('').replace(/(\d{3})/g,'$1,').split('').reverse().join('').replace(/^,/,'');
        if (/\./g.test(tmp)){
            tmp = tmp.split(".");
            tmp[1] = tmp[1].replace(/\,/g,"").replace(/ /,"");
            nField.value = tmp[0]+"."+tmp[1]
        } else {
            nField.value = tmp.replace(/ /,"");
        } 
    } else {
        nField.value = nField.value.replace(/[^\d\,\.]/g,"").replace(/ /,"");
    }
}

