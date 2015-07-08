/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
  // staff 
$(document).ready(function () {
        var staffCode = [];
        $.each(staff, function (key, value) {
            staffCode.push(value.id);
            staffCode.push(value.name);
        });

        $("#InputStaff").autocomplete({
            source: staffCode,
            close:function( event, ui ) {
            $("#InputStaff").trigger('keyup');
                
            }
        });
          
        $("#InputStaff").on('keyup',function(){
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left);
            var code = this.value.toUpperCase();
//            console.log("Code :"+ code);
            var name = this.value.toUpperCase();
            console.log("Name :"+ name);
            $("#InputStaff,#InputStaffName").val(null);
            $.each(staff, function (key, value) {
                
                if (value.code.toUpperCase() === code ) {  
//                   console.log("Code2 :"+ name);
                    $("#InputStaff").val(value.id);
                    $("#InputStaffName").val(value.name);
                }
                else if(value.name.toUpperCase() === name){
//                    console.log("Name2 :"+ name);
                    $("#InputStaff").val(value.id);
                    $("#InputStaffName").val(value.name);
                }
            }); 
            
        }); 
       
        
    $("#StaffTable tr").on('click', function () {
       $("#SearchStaff").modal('hide');
        var staff_id = $(this).find(".staff-code").text();
        var staff_name = $(this).find(".staff-name").text();
        $("#InputStaff").val(staff_id);
        $("#InputStaffName").val(staff_name);
    });
    // StaffTable
    $('#StaffTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    $('#StaffTable tbody').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
    
    $("#TaxInvoiceTable").on("keyup", function () {
        var rowAll = $("#TaxInvoiceTable tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() != '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#TaxInvoiceTable tr").length;
                //console.log('Row: ' + rowIndex + ', Column: ' + colIndex + ', All Row ' + rowAll);
                if (rowIndex == rowAll) {
                    //console.log("rowIndex: " + rowIndex);
                    console.log("rowAll : " + rowAll);
                    //console.log("addRow");
                    AddRow(rowAll);
                }
            }

        });
    });
});  

function AddRow(row) {
    $("#TaxInvoiceTable tbody").append(
            '<tr>' +
            '<td><input type="text" class="form-control" name="id' + row + '" id="id' + row + '" value="' + row + '" readonly/></td>' +
            '<td><input type="text"  class="form-control" name="codeItemList' + row + '" id="codeItemList' + row + '" value=""/></td>' +
            '<td><select id="SeleteTypeItemList' + row + '" name="SeleteTypeItemList' + row + '" class="form-control"><option value=""><c:out value="" /></option></select></td>' +
            '<td><input type="text" class="form-control" name="payStatusItemList' + row + '" id="payStatusItemList' + row + '" value="" readonly/></td>' +
            '<td><input type="text" class="form-control" name="itemStatusItemList' + row + '" id="itemStatusItemList' + row + '" value="" readonly/></td>' +
            '<td class="text-center"><a href="#" onclick=""  data-toggle="modal" data-target="" class="remCF" id="ButtonRemove' + row + '"><span id="Spanremove' + row + '" class="glyphicon glyphicon-remove deleteicon"  onclick="" data-toggle="modal" data-target="#delStockModal"></span></a></td>' +
            '</tr>'
            );
    var tempCount = parseInt($("#counter").val()) + 1;
    $("#counter").val(tempCount);
}

function searchAction() {
    var action = document.getElementById('action');
    action.value = 'add';
    document.getElementById('StockForm').submit();
}

function addItemList(row){
//    alert("Come");
    var prefix  = document.getElementById('InputPrefix');
    var start  = document.getElementById('InputStart');
    var number  = document.getElementById('InputNumberOfItem');
    var type  = document.getElementById('Selecttype');
//    alert(""+prefix.value + " " + start.value + " " + number.value + " " + type);
    var i = 0;   
    for (i = 1 ; i <= number.value; i++){
//        alert("num : "+ number.value);
        $("#TaxInvoiceTable tbody").append(
            '<tr>' +
            '<td><input type="text" class="form-control" name="id' + i + '" id="id' + i + '" value="'+i+'" readonly/></td>' +
            '<td><input type="text"  class="form-control" name="codeItemList' + i + '" id="codeItemList' + i + '" value="'+prefix.value+'-'+start.value+'"/></td>' +
            '<td><select id="SeleteTypeItemList' + i + '" name="SeleteTypeItemList' + i + '" class="form-control"><option value="'+type.value+'">'+type.value+'</option></select></td>' +
            '<td><input type="text" class="form-control" name="payStatusItemList' + i + '" id="payStatusItemList' + i + '" value="0" readonly/></td>' +
            '<td><input type="text" class="form-control" name="itemStatusItemList' + i + '" id="itemStatusItemList' + i + '" value="normal" readonly/></td>' +
            '<td class="text-center"><a href="#"  class="remCF" id="ButtonRemove' + i + '" onclick="deleteItemListRow('+i+','+ prefix.value+'-'+start.value+')" data-toggle="modal" data-target="#delStockModal"><span id="Spanremove' + i + '" class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
            '</tr>'
            );
            start.value++;
//    var tempCount = parseInt($("#counter").val()) + 1;
//    $("#counter").val(tempCount);
    }
    document.getElementById("TaxInvoiceTable").deleteRow(1);
    AddRow(i);
}

function deleteItemListRow(rowId,code){
    alert("Row : " + rowId);
    $("#delCodeStock").text(' Are you sure to delete Booking Driver ' + code + '?');
//    $('#delCodeStock').innerHTML = ' Are you sure to delete Item code  :  ' + code + '???';
//   $("div#delCodeStock").text(' Are you sure to delete Item code  :  ' + code + '???');
//    document.getElementById("TaxInvoiceTable").deleteRow(rowId);
}