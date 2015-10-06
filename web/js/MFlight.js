/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
function searchAction(){ 
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchFlight').submit();            
}

function save(){
    var FlightCode = document.getElementById('FlightCode');
    var FlightName =  document.getElementById('FlightName');
    if( FlightCode.value == ''){
        alert('please fill in Flight code');
    }else if( FlightName.value == ''){
        alert('please fill in Flight name');
    }else if(!(CheckCharecter(FlightCode.value) && CheckCharecter(FlightName.value))){
         alert('data contains illegal characters.');
    }else{
        document.getElementById('Flightform').submit();
    }              
}

function addaction(){
    document.getElementById("FlightCode").readOnly = false;
    document.getElementById('FlightCode').value='';
    document.getElementById('FlightName').value='';
    document.getElementById('FlightID').value='';
    document.getElementById('actionIUP').value='add';
        $('#MasterFlightService').dataTable().fnClearTable();
        $('#MasterFlightService').dataTable().fnDestroy();
        var rowCount = $('#MasterFlightService tr').length;
        $("#counterTable").val(rowCount);
        addRowMFlightService(rowCount++);
}

function addRowMFlightService(row){
    $("#MasterFlightService tbody").append(
        '<tr>'+
        '<td class="hidden"><input type="text" class="form-control" maxlength="3" id="FlightServiceId' + row + '" style="text-transform:uppercase" name="FlightServiceId' + row + '" value=""></td>'+
        '<td><input type="text" class="form-control" maxlength="1" id="FlightServiceCode' + row + '" style="text-transform:uppercase" name="FlightServiceCode' + row + '" value=""></td>'+
        '<td><input type="text" class="form-control" maxlength="50" id="FlightServiceName' + row + '" style="text-transform:uppercase" name="FlightServiceName' + row + '" value=""></td>'+
        '<td><center><span id="spanRemove' + row + '" class="glyphicon glyphicon-remove deleteicon"  onclick="deleteMFlightService('+ row +')"  ></span></center></td>'+                            
        '</tr>'
    );
    var count = document.getElementById('counterTable');
    count.value = row++;
}

function EditFlight(id,code,name){
    $('#setIdMFlightService').val('');
    var idFlight = document.getElementById('FlightID').value;
    console.log("ID: "  + idFlight);
    document.getElementById("FlightCode").readOnly = true;
    document.getElementById('FlightCode').value=code;
    document.getElementById('FlightName').value=name;
    document.getElementById('FlightID').value=id;
    document.getElementById('actionIUP').value='update';
    if((id !== "")&&(id !== undefined)){
        var servletName = 'BillableServlet';
        var servicesName = 'AJAXBean';
        var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + id +
            '&type=' + 'searchFlightService';
        CallAjax(param);
        
    }
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
                console.log("Seach light Gooo");
                $('#MasterFlightService').dataTable().fnClearTable();
                $('#MasterFlightService').dataTable().fnDestroy();
                $("#MasterFlightService tbody").empty().append(msg);
                console.log(msg);
                var rowCount = $('#MasterFlightService tr').length;
                $("#counterTable").val(rowCount);
                addRowMFlightService(rowCount++);
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

function DeleteFlight(id,code){
    var FlightID = document.getElementById('FlightID');
    FlightID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete code : " + code + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('Flightform').submit();
}

function deleteMFlightService(id,code,row){
    console.log("row : " + row);
    if(row === undefined){
        $("#FlightServiceCode"+id).parent().parent().remove();
    }else{
        $("#FlightServiceCode"+row).parent().parent().remove();
    }
    
    var textId = $('#setIdMFlightService').val();
    if(textId !== ''){
        $('#setIdMFlightService').val(textId +"" +id+"/"); 
    }else{
        $('#setIdMFlightService').val(id+"/");
    }
    var textIdNew = $('#setIdMFlightService').val();
    console.log("Text Id : " + textIdNew);
}

$(document).ready(function () {

    $('#Flightform').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            FlightCode: {
                validators: {
                    notEmpty: {
                        message: 'The flight code is required'
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The flight code contains illegal characters.'
                    }
                }
            },
            FlightName: {
                validators: {
                    notEmpty: {
                        message: 'The flight name name is required'
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The flight name contains illegal characters.'
                    }
                }
            }
        }
    }).on('success.field.bv', function (e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);
        }
    });
    
    $("#MasterFlightService").on("keyup", function () {
        var rowAll = $("#MasterFlightService tr").length;
        $("td").keyup(function () {
            if ($(this).find("input").val() !== '') {
                var colIndex = $(this).parent().children().index($(this));
                var rowIndex = $(this).parent().parent().children().index($(this).parent()) + 2;
                rowAll = $("#MasterFlightService tr").length;
                if (rowIndex == rowAll) {
                    console.log("rowAll : " + rowAll + " Row Index : " + rowIndex);
                    addRowMFlightService(rowAll);
                }
            }
        });
    });
    
});

