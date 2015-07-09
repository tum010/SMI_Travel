// staff 
$(document).ready(function () {
    $('.date').datetimepicker();
    $(".datemask").mask('0000-00-00', {reverse: true});
    
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    
    var count  = document.getElementById('counter');
    AddRow(count.value);
        var staffCode = [];
        $.each(staff, function (key, value) {
            staffCode.push(value.username);
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
            var name = this.value.toUpperCase();
            console.log("Name :"+ name);
            $("#InputStaff,#InputStaffName,#InputStaffId").val(null);
            $.each(staff, function (key, value) {
                
                if (value.code.toUpperCase() === code ) { 
                    $("#InputStaffId").val(value.id);
                    $("#InputStaff").val(value.username);
                    $("#InputStaffName").val(value.name);
                }
                else if(value.name.toUpperCase() === name){
                    $("#InputStaffId").val(value.id);
                    $("#InputStaff").val(value.username);
                    $("#InputStaffName").val(value.name);
                }
            });           
        });       
        
    $("#StaffTable tr").on('click', function () {
       $("#SearchStaff").modal('hide');
       var staff_id = $(this).find(".staff-id").text();
        var staff_code = $(this).find(".staff-code").text();
        var staff_name = $(this).find(".staff-name").text();
        $("#InputStaffId").val(staff_id);
        $("#InputStaff").val(staff_code);
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
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#TaxInvoiceTable tr").length;
                if (rowIndex === rowAll) {
                    console.log("rowAll : " + rowAll);
                    AddRow(rowAll);
                }
            }
        });
    });
});  

function AddRow(row) {
    $("#TaxInvoiceTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input type="hidden"  id="stockDetailId' + row + '" name="stockDetailId' + row + '" value="" /></td>' +
            '<td>' + row + '</td>' +
            '<td><input type="text"  class="form-control" name="codeItemList' + row + '" id="codeItemList' + row + '" value=""/></td>' +
            '<td><select id="SeleteTypeItemList' + row + '" name="SeleteTypeItemList' + row + '" class="form-control"><option value=""><c:out value="" /></option></select></td>' +
            '<td>0</td>' +
            '<td>NEW</td>' +
            '<td class="text-center"><a href="#" onclick=""  data-toggle="modal" data-target="" class="remCF" id="ButtonRemove' + row + '"><span id="Spanremove' + row + '" class="glyphicon glyphicon-remove deleteicon"  onclick="" data-toggle="modal" data-target="#delStockModal"></span></a></td>' +
            '</tr>'
            );
    var tempCount = parseInt($("#counter").val()) + 1;
    $("#counter").val(tempCount);
}

function searchAction() {
    
    var action = document.getElementById('action');
    action.value = 'save';
    document.getElementById('StockForm').submit();
}

function addItemList(){
    var prefix  = document.getElementById('InputPrefix');
    var start  = document.getElementById('InputStart');
    var number  = document.getElementById('InputNumberOfItem');
    var type  = document.getElementById('Selecttype');
    var count = document.getElementById('counterTable');
    var countAdd = document.getElementById('counterAdd');
    if(countAdd.value === 1){
        document.getElementById("TaxInvoiceTable").deleteRow(1);
    }else{
        $("#TaxInvoiceTable tr:last").remove();
    }
    for (var i = 1 ; i <= number.value; i++){
        $("#TaxInvoiceTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input type="hidden"  id="stockDetailId' + i + '" name="stockDetailId' + i + '" value="" /></td>' +
            '<td>'+ count.value +'</td>' +
            '<td><input type="text"  class="form-control" name="codeItemList' + i + '" id="codeItemList' + i + '" value="'+prefix.value+'-'+start.value+'"/></td>' +
            '<td><select id="SeleteTypeItemList' + i + '" name="SeleteTypeItemList' + i + '" class="form-control"><option value="'+type.value+'">'+type.value+'</option></select></td>' +
            '<td>0</td>' +
            '<td>NEW</td>' +
            '<td class="text-center"><a href="#"  class="remCF" id="ButtonRemove' + i + '" onclick="deleteItemListRow('+count.value+",'"+ prefix.value+"-"+start.value+"'"+')" data-toggle="modal" data-target="#delStockModal"><span id="Spanremove' + i + '" class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
            '</tr>'
            );
            start.value++;
            count.value++;
    }
    countAdd.value++;
    AddRow(count.value);
}

function deleteItemListRow(rowId,code){
    // Click Action Delete
    $("#idCodeStockDelete").val(rowId);
    $("#delCodeStock").text(' Are you sure to delete Item code : ' + code + ' in Row : ' + rowId +' ??');
}

function deleteStock(){
    // ID In Modal Delete
    var rowId  = document.getElementById('idCodeStockDelete');
    document.getElementById("TaxInvoiceTable").deleteRow(rowId.value);
    resetNumberItemList();
}

function resetNumberItemList(){
    var rows = document.getElementById("TaxInvoiceTable").getElementsByTagName("tr").length;
    var countRow = document.getElementById('TaxInvoiceTable').rows;
    for (var i = 1 ; i <= (rows -1); i++){   
        countRow[i].cells[0].innerHTML = i; 
         alert(countRow[i].cells[0]);
    }
}