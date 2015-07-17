// staff 
$(document).ready(function () {
    var rows = document.getElementById("StockTable").getElementsByTagName("tr").length;
    var count = document.getElementById('counterTable');
    count.value = rows;
    
    $('.date').datetimepicker();
    $(".datemask").mask('0000-00-00', {reverse: true});
    $(".money").mask('000,000,000,000,000,000', {reverse: true});
    
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
            $("#InputStaffId,#InputStaff,#InputStaffName").val(null);
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
                
//      StaffTable
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

    
    $("#StockTable").on("keyup", function () {
        var rowAll = $("#StockTable tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#StockTable tr").length;
//                alert("Goo : " + colIndex + " " + rowIndex +"Row All :  " + rowAll);
                if (rowIndex === rowAll) {
                    console.log("rowAll : " + rowAll);
                    AddRow(rowAll);
                }
            }
        });
    });
    validFrom();
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
                            notEmpty: {
                                message: 'The Date From is required'
                            },
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
                            notEmpty: {
                                message: 'The Date To is required'
                            },
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
            
//            $('#StockForm').submit();
}

function AddRow(row) {
//    alert("Se" + select);
    $("#StockTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input type="hidden"  id="stockDetailId' + row + '" name="stockDetailId' + row + '" value="" /></td>' +
            '<td>' + row + '</td>' +
            '<td><input type="text"  class="form-control" name="codeItemList' + row + '" id="codeItemList' + row + '" value=""/></td>' +
            '<td><select id="SeleteTypeItemList' + row + '" name="SeleteTypeItemList' + row + '" class="form-control"><option></option>'+select +'</select></td>' +
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
    var st = start.value;
//    alert("Type : " + type.value +" Start : " + st);
    if(countAdd.value === 1){
        document.getElementById("StockTable").deleteRow(1);
    }else{
        $("#StockTable tr:last").remove();
    }
    var res = select.replace("value='"+ type.value+"'", "selected value='"+ type.value+"'");
//    alert(res);
    for (var i = 1 ; i <= number.value; i++){
        $("#StockTable tbody").append(
            '<tr>' +
            '<td class="hidden"><input type="hidden"  id="stockDetailId' + count.value + '" name="stockDetailId' + count.value + '" value="" /></td>' +
            '<td>'+ count.value +'</td>' +
            '<td><input type="text"  class="form-control" name="codeItemList' + count.value + '" id="codeItemList' + count.value + '" value="'+prefix.value+'-'+start.value+'"/></td>' +
            '<td><select id="SeleteTypeItemList' + count.value + '" name="SeleteTypeItemList' + count.value + '" class="form-control">' + res + '</select></td>' +
            '<td>0</td>' +
            '<td>NEW</td>' +
            '<td class="text-center"><a href="#"  class="remCF" id="ButtonRemove' + count.value + '" onclick="deleteItemListRow('+count.value+",'"+ prefix.value+"-"+start.value+"'"+')" data-toggle="modal" data-target="#delStockModal"><span id="Spanremove' + count.value + '" class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
            '</tr>'
            );
            start.value++;
            count.value++;
//            $("#SeleteTypeItemList"+count.value+"  option[value='" + type.value +"']").attr("selected","selected");

            
    }
    start.value = st;
    countAdd.value++;
    AddRow(count.value);
    resetNumberItemList();
}

function deleteItemListRow(rowId,code){
    // Click Action Delete
    $("#idStockDelete").val(rowId);
    $("#delCodeStock").text(' Are you sure to delete Item code : ' + code + ' in Row : ' + rowId +' ??');
//    console.log("rowAll : " + rowAll);
    resetNumberItemList();
}

function deleteStock(){
    // ID In Modal Delete
    var rowId  = document.getElementById('idStockDelete');
    var stockDetailId  = $("#stockDetailId"+rowId.value).val();

//    alert(" R : " + rowId.value);
    document.getElementById("StockTable").deleteRow(rowId.value);
    resetNumberItemList();
    if(stockDetailId !== ""){
        rowId.value = stockDetailId ;
        var action = document.getElementById('action');
        action.value = 'delete';
        document.getElementById('StockForm').submit();
    }
}
function resetNumberItemList(){
    var rows = document.getElementById("StockTable").getElementsByTagName("tr").length;
    var countRow = document.getElementById('StockTable').rows; 
    for (var i = 1 ; i <= (rows-1); i++){  
        countRow[i].cells[1].innerHTML = i; 
        countRow[i].cells[2].getElementsByTagName("input")[0].name = "codeItemList" + i;
    }  
}