// staff 
$(document).ready(function () {
    var rows = document.getElementById("StockTable").getElementsByTagName("tr").length;
    var count = document.getElementById('counterTable');
    count.value = rows;
    console.log(" Row is Now : " + count.value );
    $('.date').datetimepicker();
    $(".datemask").mask('0000-00-00', {reverse: true});
    $(".money").mask('000,000,000,000,000,000', {reverse: true});
    
    $(".number").mask('000000000000000000', {reverse: true});
    var currentDate = new Date();  
    $("#InputStockDate").datepicker("setDate",currentDate);
    
    $('#InputDatePicker').datetimepicker({
        }).on('change', function(e) {
            $('#StockForm').bootstrapValidator('revalidateField', 'InputEffectiveFromDate');
    });
    
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    
    var count  = document.getElementById('counter');
    AddRow(count.value);
    
    
    $("#productTable tr").on('click', function () {//winit
        $("#SearchProduct").modal('hide');
            var tour_id = $(this).find(".product-id").html();
            var tour_code = $(this).find(".product-code").html();
            var tour_name = $(this).find(".product-name").html();
            $("#InputProductId").val(tour_id);
            $("#InputProduct").val(tour_code);
            $("#InputProductName").val(tour_name);
        });

        // productTable
        var tourTable = $('#productTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });

        $('#productTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
            }
            else {
                tourTable.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
            }
        });
        // ON KEY INPUT AUTO SELECT PRODUCTCODE
        $(function () {
            var availableTags = [];
            $.each(productCode, function (key, value) {
                availableTags.push(value.code);
                if (!(value.name in availableTags)) {
                    availableTags.push(value.name);
                }
            });

            $("#InputProduct").autocomplete({
                source: availableTags,
                close: function (event, ui) {
                    $("#InputProduct").trigger('keyup');
                }
            });

            $("#InputProduct").keyup(function () {
                var position = $(this).offset();
                $(".ui-widget").css("top", position.top + 30);
                $(".ui-widget").css("left", position.left);
                var name = this.value;
                var code = this.value.toUpperCase();
                $("#InputProductName").val(null);
                $.each(productCode, function (key, value) {
                    if (name === value.name) {
                        $("#InputProduct").val(value.code);
                        code = $("#InputProduct").val().toUpperCase();
                    }
                    if (value.code.toUpperCase() === code) {
                        $("#InputProductId").val(value.id);
                        $("#InputProductName").val(value.name);
                    }
                }); //end each productCode
            }); // end InputproductCode keyup
        }); // end AutoComplete productCode
    
        var staffCode = [];       
        $("#StaffTable tr").on('click', function () {//winit
            $("#SearchStaff").modal('hide');
            var staff_id = $(this).find(".staff-id").html();
            var staff_code = $(this).find(".staff-code").html();
            var staff_name = $(this).find(".staff-name").html();
            $("#InputStaffId").val(staff_id);
            $("#InputStaff").val(staff_code);
            $("#InputStaffName").val(staff_name);

        });

        // tourTable
        var staffTable = $('#StaffTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": true,
            "bPaginate": true,
            "bInfo": false,
            "bLengthChange": false,
            "iDisplayLength": 10
        });

        $('#StaffTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
            }
            else {
                staffTable.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
            }
        });
        // ON KEY INPUT AUTO SELECT PRODUCTCODE
        $(function () {
            var availableTags = [];
            $.each(staff, function (key, value) {
                availableTags.push(value.code);
                if (!(value.name in availableTags)) {
                    availableTags.push(value.name);
                }
            });

            $("#InputStaff").autocomplete({
                source: availableTags,
                close: function (event, ui) {
                    $("#InputStaff").trigger('keyup');
                }
            });

            $("#InputStaff").keyup(function () {
                var position = $(this).offset();
                $(".ui-widget").css("top", position.top + 30);
                $(".ui-widget").css("left", position.left);
                var name = this.value;
                var code = this.value.toUpperCase();
                $("#InputStaffName").val(null);
                $.each(staff, function (key, value) {
                    if (name === value.name.toUpperCase()) {
                        $("#InputStaffId").val(value.id);
                        $("#InputStaff").val(value.code);
                        $("#InputStaffName").val(value.name);
//                        code = $("#InputStaff").val().toUpperCase();
                    }
                    if (value.code.toUpperCase() === code) {
                        $("#InputStaffId").val(value.id);
                        $("#InputStaff").val(value.code);
                        $("#InputStaffName").val(value.name);
                    }
                }); //end each productCode
            }); // end InputproductCode keyup
        }); // end AutoComplete productCode
     
    $("#StockTable").on("keyup", function () {
        var rowAll = $("#StockTable tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#StockTable tr").length;
//                alert("Goo : " + colIndex + " " + rowIndex +"Row All :  " + rowAll);
                if (rowIndex == rowAll) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    AddRow(rowAll);
                }
            }
        });
    });
    // Validator Date From and To
    var $stockForm = $("#StockForm");
    $("#StockForm")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    InputEffectiveFromDate: {
                        trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                max: 'InputInputEffectiveToDate',
                                message: 'The Date From is not a valid'
                            }
                        }
                    },
                    InputInputEffectiveToDate: {
                        trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                min: 'InputEffectiveFromDate',
                                message: 'The Date To is not a valid'
                            }
                        }
                    },
                    InputProduct : {
                        validators: {
                            notEmpty: {
                                message: 'The full name is required'
                            }
                        }
                    },
                    InputStockDate : {
                        trigger: 'focus keyup change',
                        validators: {
                            notEmpty: {
                                message: 'The Date To is required'
                            },
                            date: {
                                format: 'YYYY-MM-DD',
                                min: 'InputStockDate',
                                message: 'The Date To is not a valid'
                            }
                        }
                    }
                }
            }).on('success.field.fv', function (e, data) {
                if (data.field === 'InputEffectiveFromDate' && data.fv.isValidField('InputInputEffectiveToDate') === false) {
                    data.fv.revalidateField('InputInputEffectiveToDate');
                }

                if (data.field === 'InputInputEffectiveToDate' && data.fv.isValidField('InputEffectiveFromDate') === false) {
                    data.fv.revalidateField('InputEffectiveFromDate');
                }
            });
            
            $('#DateFrom').datetimepicker().on('dp.change', function (e) {
                $('#StockForm').bootstrapValidator('revalidateField', 'InputEffectiveFromDate');
            });
            $('#DateTo').datetimepicker().on('dp.change', function (e) {
                $('#StockForm').bootstrapValidator('revalidateField', 'InputInputEffectiveToDate');
            });
            $stockForm.on('mouseover', function () {
                var InputProduct = $(this).find('[name="InputProduct"]');
                var isEmpty = InputProduct.val() === '';
                $stockForm.bootstrapValidator('enableFieldValidators', 'InputProduct', isEmpty);
            });
});

function validFrom(){
    // Validator Date From and To
    $("#StockForm")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    InputEffectiveFromDate: {
                        trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                max: 'InputInputEffectiveToDate',
                                message: 'The Date From is not a valid'
                            }
                        }
                    },
                    InputInputEffectiveToDate: {
                        trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                min: 'InputEffectiveFromDate',
                                message: 'The Date To is not a valid'
                            }
                        }
                    },
                    InputProduct : {
                        validators: {
                            notEmpty: {
                                message: 'The full name is required'
                            }
                        }
                    },
                    InputStockDate : {
                        trigger: 'focus keyup change',
                        validators: {
                            notEmpty: {
                                message: 'The Date To is required'
                            },
                            date: {
                                format: 'YYYY-MM-DD',
                                min: 'InputStockDate',
                                message: 'The Date To is not a valid'
                            }
                        }
                    }
                }
            }).on('success.field.fv', function (e, data) {
                if (data.field === 'InputEffectiveFromDate' && data.fv.isValidField('InputInputEffectiveToDate') === false) {
                    data.fv.revalidateField('InputInputEffectiveToDate');
                }

                if (data.field === 'InputInputEffectiveToDate' && data.fv.isValidField('InputEffectiveFromDate') === false) {
                    data.fv.revalidateField('InputEffectiveFromDate');
                }
            });
            
            $('#DateFrom').datetimepicker().on('dp.change', function (e) {
                $('#StockForm').bootstrapValidator('revalidateField', 'InputEffectiveFromDate');
            });
            $('#DateTo').datetimepicker().on('dp.change', function (e) {
                $('#StockForm').bootstrapValidator('revalidateField', 'InputInputEffectiveToDate');
            });
}

function AddRow(row) {
    $("#StockTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input type="hidden"  id="stockDetailId' + row + '" name="stockDetailId' + row + '" value="" /></td>' +
            '<td>' + row + '</td>' +
            '<td><input type="text"  class="form-control" name="codeItemList' + row + '" id="codeItemList' + row + '" value=""/></td>' +
            '<td><select id="SeleteTypeItemList' + row + '" name="SeleteTypeItemList' + row + '" class="form-control"><option></option>'+ select +'</select></td>' +
            '<td>No Paid</td>' +
            '<td>NEW</td>' +
            '<td class="text-center"><a href="#" onclick="deleteItemListRow('+row+')"  data-toggle="modal" data-target="" class="remCF" id="ButtonRemove' + row + '"><span id="Spanremove' + row + '" class="glyphicon glyphicon-remove deleteicon"  onclick="" data-toggle="modal" data-target="#delStockModal"></span></a></td>' +
            '</tr>'
            );
    var tempCount = parseInt($("#counter").val()) + 1;
    var count = document.getElementById('counterTable');
    count.value = row;
    $("#counter").val(tempCount);
}

function searchAction() { 
    var action = document.getElementById('action');
    action.value = 'save';
    document.getElementById('StockForm').submit();
}
function zeroPad(num, places) {
  var zero = places - num.toString().length + 1;
  return Array(+(zero > 0 && zero)).join("0") + num;
}

var isCheckLength = 0;
var isCheckDuplicate = 0;
var isEmpty = 0;

function addItemList(){
//    alert(zeroPad(100, 5)); // "05"
    var prefix  = document.getElementById('InputPrefix');
    var start  = document.getElementById('InputStart');
    var number  = document.getElementById('InputNumberOfItem');
    var digit = document.getElementById("InputDigit");
    var type  = document.getElementById('Selecttype');
    var count = document.getElementById('counterTable');
    var countAdd = document.getElementById('counterAdd');
    checkEmptyValueBeforeAddItem(prefix.value,start.value,number.value,digit.value);
    if(isEmpty === 0){
    //check length code 
    checklength(prefix.value,digit.value);
//    alert("Is Check : " + isCheckLength);
            if(isCheckLength === 0){      
                var st = start.value;
                $("#StockTable tr:last").remove();
//                if(countAdd.value === 1){
//                    document.getElementById("StockTable").deleteRow(1);
//                }else{
//                    
//                }
                var res = select.replace("value='"+ type.value+"'", "selected value='"+ type.value+"'");
                checkDuplicate(prefix.value,digit.value,start.value,number.value);
                    if(isCheckDuplicate === 0){
                        for (var i = 1 ; i <= number.value; i++){
                            // Concat prefix + start + digit
                            var code = zeroPad(start.value, digit.value);
  
                            $("#StockTable tbody").append(
                                '<tr>' +
                                '<td class="hidden"><input type="text"  id="stockDetailId' + count.value + '" name="stockDetailId' + count.value + '" value="" /></td>' +
                                '<td>'+ count.value +'</td>' +
                                '<td><input type="text"  class="form-control" name="codeItemList' + count.value + '" id="codeItemList' + count.value + '" value="'+prefix.value+'-'+code+'"/></td>' +
                                '<td><select id="SeleteTypeItemList' + count.value + '" name="SeleteTypeItemList' + count.value + '" class="form-control">' + res + '</select></td>' +
                                '<td>No Paid</td>' +
                                '<td>NEW</td>' +
                                '<td class="text-center"><a href="#"  class="remCF" id="ButtonRemove' + count.value + '" onclick="deleteItemListRow('+count.value+",'"+ prefix.value+"-"+code+"'"+')" data-toggle="modal" data-target="#delStockModal"><span id="Spanremove' + count.value + '" class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
                                '</tr>'
                                );
                                start.value++;
                                count.value++; 
                              
                        }        
                        start.value = st;
                        countAdd.value++;
                        AddRow(count.value);
                        
                }else if(isCheckDuplicate !== 0){
                    alert("Duplicate Code in Table!!!");
                    AddRow(count.value);
                }
            }
        }else if(isEmpty === 1){
            alert("Please Input Prefix, Start, Digit, Number Of Item!!");
        }
        resetNumberItemList();
}

function checklength(prefix,digit){
    var code = "";
    var  len = 0;
    var text = "";
    for(var i = 0 ; i < digit ; i++){
        text += 0;
    }
    
    code = prefix + "-" + text;
    len = code.length;
//    alert("Length : "+len);
    if(len > 50){
//        $('#checklengthCode').show();
        alert("Max length More 50");
        isCheckLength = 1;
    }else{
//        $('#checklengthCode').hide();
        isCheckLength = 0;
    }
}

function deleteItemListRow(rowId,code){
    // Click Action Delete
    console.log("Code : " + code + "Row Id : " +rowId);
    $("#idStockDelete").val(rowId);
    $("#delCodeStock").text(' Are you sure to delete Item code : ' + code +'?');
    
    resetNumberItemList();
}

function deleteStock(){
    // ID In Modal Delete
    var count = document.getElementById('counterTable');
    var rowId  = document.getElementById('idStockDelete');
    var stockDetailId  = $("#stockDetailId"+rowId.value).val();   
 
    if((stockDetailId !== "")&&(stockDetailId !== undefined)){
        rowId.value = stockDetailId ;
        var action = document.getElementById('action');
        action.value = 'delete';
        resetNumberItemList();
        document.getElementById('StockForm').submit();
        
    }else{
        document.getElementById("StockTable").deleteRow(rowId.value);
     
//        alert("Row Delete : " + rowId.value);
        count.value = count.value -1 ;
       // resetNumberItemList();
    }
    resetNumberItemList();
}
function resetNumberItemList(){
    var rows = document.getElementById("StockTable").getElementsByTagName("tr").length;
    var countRow = document.getElementById('StockTable').rows; 
    for (var i = 1 ; i <= rows; i++){ 
//        alert("Row :"+row);
        var code = $("#codeItemList"+i).val();
      //  alert(code);
//        $("#StockTable").children().children()[i].children[1].innerHTML = i;
    //    countRow[i].cells[1].innerHTML = i;
     //   countRow[i].cells[6].innerHTML = "<a href='#'  class='remCF' id='ButtonRemove"+ i +"'  onclick=\"deleteItemListRow("+ i +",'"+ code +"')\"  data-toggle='modal' data-target='#delStockModal'><span id='Spanremove"+ i +"'  class='glyphicon glyphicon-remove deleteicon'></span></a>";
    }  
    
    var count =1;
    $('#StockTable > tbody  > tr').each(function() {
        var codea = $(this).find("td").eq(2).find("input").val();
        $(this).find("td").eq(1).html(count);
        if(codea !== undefined ){
            $(this).find("td").eq(6).html("<a href='#'  class='remCF' id='ButtonRemove"+ count +"'  onclick=\"deleteItemListRow("+ count +",'"+ codea +"')\"  data-toggle='modal' data-target='#delStockModal'><span id='Spanremove"+ count +"'  class='glyphicon glyphicon-remove deleteicon'></span></a>");      
        }
        count+= 1;
    });
    
    
    
    
}

function checkDuplicate(prefix,digit,start,number){
    var codeNew = [];
    for(var j = 1 ; j <= number ; j++){
        codeNew.push(prefix + "-"+zeroPad(start, digit));
        start++;
    }
    var rows = document.getElementById("StockTable").getElementsByTagName("tr").length;
    for (var i = 1 ; i <= (rows-1); i++){  
        var codeOld = document.getElementById("codeItemList"+i);
        var k = 0;
        for(k = 0 ; k < codeNew.length ; k++){
            if(codeNew[k] === codeOld.value){
                isCheckDuplicate++;
            }
        }      
    }  
}

function checkEmptyValueBeforeAddItem(prefix,start,number,digit){
    if(prefix === "" || start === "" ||  number ===  "" ||  digit === ""){
        isEmpty = 1;
    }else{
        isEmpty = 0;
    }
//    alert(isEmpty);
}