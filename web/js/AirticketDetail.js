
function disableFlight() {
    $("#disableFlight").submit();
}

function setDisableFlight(id, flightNo) {
    $("#disableFlightId").val(id);
    document.getElementById('disableCode').innerHTML = "Are you sure to disable flight no.: " + flightNo + " ?";
}

function enableFlight() {
    $("#enableFlight").submit();
}

function setEnableFlight(id, flightNo) {
    $("#enableFlightId").val(id);
    document.getElementById('enableCode').innerHTML = "Are you sure to enable flight no.: " + flightNo + " ?";
}

function getDate(start, stop) {
    var diff = new Date(stop - start);
    var days = diff / 1000 / 60 / 60 / 24;
    if (days >= 0) {
        console.log(days);
        return true;
    } else {
        return false;
    }
}

function checkCateory(value, validator) {
    var check = true;
    if (value == 'NON') {
        return false;
    }
    return check;
}
;
function checkPassengerAirline(value, validator) {
    var a = false;
    $.each(flight, function (key, v) {
        if (v.air_code.toUpperCase() === value.toUpperCase()) {
            console.log('airline ok');
            a = true;
        }
    });
    return a;
}
;

// ON KEY INPUT AUTO SELECT PNR
$(function () {
    var availableTags = [];
    $.each(pnr, function (key, value) {
        availableTags.push(value.pnr);
        
    });
    $("#pnr_name").autocomplete({
        source: availableTags
    });
    $("#pnr_name").keyup(function () {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
    });
});

// ######################### FLIGHT ######################### //
$(document).ready(function () {
    
    $("#TableAir tbody tr").each(function(i){
        i++;
        $("#flight-"+i+"-ticketTypeCom").change(function(){          
             $("#flight-"+i+"-ticketType  > option[value="+this.value+"]").prop('selected',true);
        });
        
        $("#flight-"+i+"-classCom").change(function(){          
             $("#flight-"+i+"-class  > option[value="+this.value+"]").prop('selected',true);
        });
        
        $("#flight-"+i+"-subClassCom").change(function(){   
             document.getElementById("flight-"+i+"-subClass").value = (this.value);
        });
    });
    
    $("#passenger_table tbody tr").each(function(i){
        i++;
        $("#passengerCategoryCom"+i).change(function(){   
             $("#passengerCategory"+i+" > option[value="+this.value+"]").prop('selected',true);
        });
        
        $("#passengerFromCom"+i).change(function(){   
             $("#passengerFrom"+i+" > option[value="+this.value+"]").prop('selected',true);
        });
        
        $("#passengerTicketTypeCom"+i).change(function(){   
             $("#passengerTicketType"+i+" > option[value="+this.value+"]").prop('selected',true);
        });
    });
    
    $(".money").mask('000,000,000,000,000,000', {reverse: true});
    $(".money2").mask('000', {reverse: true});
    setformat();
    $('.collapse').collapse('hide');
    // datetimepicker
    $("div").find('.date').datetimepicker();
    $("div").find('.times').datetimepicker({
        format: 'HH:mm'
    });


    $('#passenger_table,#TableAir').on('click', 'a', function () {
        console.log('hide collapse');
        $('.collapse').collapse('hide');
    });
    //TABLE
    TableAir = $('#TableAir').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": false,
        "bInfo": false
    });
    $('#TableAir tbody').on('click', 'tr', function () {

        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
        } else {
            TableAir.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }

    });

    var $bookForm = $("#AirticketForm");
    $bookForm
            .bootstrapValidator({
                container: 'tooltip',
                excluded: [':disabled'],
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                }
            })
            .on('success.field.bv', function (e, data) {
                if (data.bv.isValid()) {
                    data.bv.disableSubmitButtons(false);

                }
            });
    $bookForm.on('mouseover', function () {
        var airlineCode = $(this).find('[name="airlineCode1"]');
        var isEmpty = airlineCode.val() === '';
        $bookForm.bootstrapValidator('enableFieldValidators', 'airlineCode1', isEmpty);
    });
    $("#btn-add").click(function () {
        $('.collapse').collapse('hide');
        var countNow = parseInt($("#countRow").val());
        console.log('countNow ' + countNow);
        var row = $("#TableAir tr:last td:first").text();
        console.log('row ' + row);
        var empty = $('#TableAir tbody td').hasClass('dataTables_empty');
        if (empty) {
            addFight(countNow);
            $("#flight" + countNow).collapse();
            $("#countRow").val(countNow + 1);
        } else {
            if ((countNow - row) == 1) {
                addFight(countNow);
                $("#flight" + countNow).collapse();
                $("#countRow").val(countNow + 1);
            } else {
                $("#flight" + (countNow - 1)).collapse('show');
            }
        }

    });

    // ON  SUBMIT 
    $("#AirticketForm").submit(function (e) {
        // count passenger_table
        var rowCount = $('#passenger_table tr').length;
        console.log('rowCount' + rowCount);
        var empty = $('#passenger_table tbody td').hasClass('dataTables_empty');
        if (empty || rowCount < 2) {
            e.preventDefault();
            console.log('alert');
            alert('please add Passengers...');
            return false;
        } else {
            
            console.log('submit');
            return true;
        }
    });

});
// ADD FLIGHT
function addFight(rowId) {
    $("#divAddFight").append(
            '<div class="collapse" id="flight' + rowId + '">'
            + '<div class="panel panel-default" style="margin-top: 10px">'
            + '<div class="panel-heading">'
            + '<h3 class="panel-title">Order</h3>'
            + '</div>'
            + '<div class="panel-body">'
            + '<div class="row"  style="margin-bottom: 10px">'
            + '<label class="col-sm-1 control-label text-right">Order</label>'
            + '<div class="col-sm-2">  '
            + '<input name="flight-' + rowId + '-orderNo" id="flight-' + rowId + '-orderNo" type="text" class="form-control" value="' + rowId + '" readonly="">'
            + '<input name="flight-' + rowId + '-id" id="flight-' + rowId + '-id" type="hidden" class="form-control" value="' + rowId + '" readonly="">'
            + '</div>'
            + '<label class="col-sm-1 control-label text-right">Flight<strong style="color: red">*</strong></label>'
            + '<div class="col-sm-2">'
            + '<input type="text" class="form-control flight-no" data-id="' + rowId + '" name="flight-' + rowId + '-flightNo" id="flight-' + rowId + '-flightNo" maxlength="10" data-bv-notempty data-bv-notempty-message="The flight is required">'
            + '</div>'
            + '<label class="col-sm-1 control-label text-right">Airline<strong style="color: red">*</strong></label>'
            + '<div class = "col-sm-2">'
            + '<div class = "form-group">'
            + '<div class = "input-group">'
            + '<input type = "hidden" class = "form-control" id = "airlineId' + rowId + '" name = "airlineId' + rowId + '" >'
            + '<input name = "airlineCode' + rowId + '" id = "airlineCode' + rowId + '"  class = "form-control airline" data-id="' + rowId + '"  data-bv-notempty data-bv-notempty-message="The airline is required">'
            + '<span class = "input-group-addon" data-toggle = "modal" data-target = "#AirlineModal" onclick = "get_PId(1,' + rowId + ')" >'
            + '<span class = "glyphicon-search glyphicon" > </span>'
            + '</span>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class = "col-sm-3">'
            + '<input name = "airlineName' + rowId + '" id = "airlineName' + rowId + '" class = "form-control" disabled = "" >'
            + '</div>'
            + '</div>'
            + '<div class="row">'
            + '<label class="col-sm-1 control-label text-right">Departure<strong style="color: red">*</strong></label>'
            + '<div class="col-sm-2">'
            + '<div class="form-group">'
            + '<div class="input-group ">'
            + '<input type="hidden" class="form-control departureid" id="departure-' + rowId + '-id" name="departure-' + rowId + '-id">'
            + '<input type="hidden" class="form-control departurecode" data-id="' + rowId + '" id="departure-' + rowId + '-code" name="departure-' + rowId + '-code" placeholder=test>'
            + '<input type="text" class="form-control departurecodeVal" data-id="' + rowId + '" id="departure-' + rowId + '-codeVal" name="departure-' + rowId + '-codeVal" >'
            + '<span class="input-group-addon" data-toggle="modal" data-target="#DepartureModal" onclick="get_id(' + rowId + ')">'
            + '<i id="datadepload-'+rowId+'" class="fa fa-spinner fa-spin hidden"></i>'
            + '<span class="glyphicon-search glyphicon"></span>'
            + '</span>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="col-sm-3">  '
            + '<input type="text" class="form-control departurename" name="departure-' + rowId + '-name" id="departure-' + rowId + '-name" readonly="" >'
            + '</div>'
            + '<label class="col-sm-1 control-label text-right">Date<strong style="color: red">*</strong></label>'
            + '<div class="col-sm-2">'
            + '<div class="form-group">'
            + '<div class="input-group date" id="DepartureDate">'
            + '<input type="text" class="form-control"  name="flight-' + rowId + '-departDate" id="flight-' + rowId + '-departDate" data-date-format="YYYY-MM-DD" maxlength="10" />'
            + '<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>'
            + '</span>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="col-sm-2">'
            + '<div class="form-group">'
            + '<div class="input-group times">'
            + '<input type="text" class="form-control" name="flight-' + rowId + '-departTime" id="flight-' + rowId + '-departTime" maxlength="4"/>'
            + '<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>'
            + '</span>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '</div>  '
            + '<div class="row">'
            + '<label class="col-sm-1 control-label text-right">Arrival<strong style="color: red">*</strong></label>'
            + '<div class="col-sm-2">  '
            + '<div class="form-group">'
            + '<div class="input-group ">'
            + '<input type="hidden" class="form-control arrivalid" id="arrival-' + rowId + '-id" name="arrival-' + rowId + '-id">'
            + '<input type="hidden" class="form-control arrivalcode" data-id="' + rowId + '" id="arrival-' + rowId + '-code" name="arrival-' + rowId + '-code"  placeholder="testArrive">'
            + '<input type="text" class="form-control arrivalcodeVal" data-id="' + rowId + '" id="arrival-' + rowId + '-codeVal" name="arrival-' + rowId + '-codeVal">'
            + '<span class="input-group-addon" data-toggle="modal" data-target="#ArrivalModal" onclick="get_id(' + rowId + ')">'
            + '<i id="dataarrload-'+rowId+'" class="fa fa-spinner fa-spin hidden"></i>'
            + '<span class="glyphicon-search glyphicon"></span>'
            + '</span>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="col-sm-3">  '
            + '<input type="text" class="form-control arrivalname" id="arrival-' + rowId + '-name" name="arrival-' + rowId + '-name" readonly="">'
            + '</div>'
            + '<label class="col-sm-1 control-label text-right">Date<strong style="color: red">*</strong></label>'
            + '<div class="col-sm-2">'
            + '<div class="form-group">'
            + '<div class="input-group date" id="ArrivalDate">'
            + '<input name="flight-' + rowId + '-arriveDate" id="flight-' + rowId + '-arriveDate"  type="text" class="form-control" data-date-format="YYYY-MM-DD" maxlength="10" />'
            + '<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>'
            + '</span>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="col-sm-2">'
            + '<div class="form-group">'
            + '<div class="input-group times" id="arrive-time">'
            + '<input name="flight-' + rowId + '-arriveTime" id="flight-' + rowId + '-arriveTime" type="text" class="form-control"  maxlength="4" />'
            + '<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span>'
            + '</span>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '</div>  '
            + '<div class="row"  style="margin-bottom: 10px">'
            + '<label class="col-sm-1 control-label text-right">Ticket</label>'
            + '<div class="col-sm-5">  '
            + '<select class="form-control" id="flight-' + rowId + '-ticketType" name="flight-' + rowId + '-ticketType">'
            + '<option value=""></option>'
            + '</select> '
            + '</div>'
            + '<label class="col-sm-1 control-label text-right">Class</label>'
            + '<div class="col-sm-2">  '
            + '<select class="form-control" id="flight-' + rowId + '-class" name="flight-' + rowId + '-class">'
            + '<option value=""></option>'
            + '</select> '
            + '</div>'
            + '<label class="col-sm-1 control-label text-right">Sub</label>'
            + '<div class="col-sm-1">'
            + '<input type="text" class="form-control" name="flight-' + rowId + '-subClass" id="flight-' + rowId + '-subClass" maxlength="1">'
            + '</div>'
            + '</div>'
            + '<div class="row"  style="margin-bottom: 10px">'
            + '<label class="col-sm-1 control-label text-right">Status</label>'
            + '<div class="col-sm-2">  '
            + '<select class="form-control" id="flight-' + rowId + '-status" name="flight-' + rowId + '-status">'
            + '</select> '
            + '</div>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="panel panel-default">'
            + '<div class="panel-heading">'
            + '<h3 class="panel-title">Calculation</h3>'
            + '</div>'
            + '<div class="panel-body">'
            + '<div class="col-sm-3" style="border-right:solid 1px #D9D9D9"><div class="row form-group text-center"><label class="control-label">Cost</label></div>'
            + '<div class="row form-group">'
            + '<label class="col-lg-3 control-label text-right">Adult</label>'
            + '<div class="col-sm-6">'
            + '<input id="adCost-' + rowId + '" name="adCost-' + rowId + '" class="form-control text-right money"  type="text" maxlength="10">'
            + '</div>'
            + '</div>'
            + '<div class="row form-group">'
            + '<label class="col-lg-3 control-label text-right">Child</label>'
            + '<div class="col-sm-6">'
            + '<input class="form-control text-right money" id="chCost-' + rowId + '" name="chCost-' + rowId + '"   type="text" maxlength="10" />'
            + '</div>'
            + '</div>'
            + '<div class="row form-group text-right">'
            + '<label class="col-lg-3 control-label">Infant</label>'
            + '<div class="col-sm-6">'
            + '<input id="inCost-' + rowId + '" name="inCost-' + rowId + '" class="form-control text-right money"  type="text"  maxlength="10" />'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="col-sm-3" style="border-right:solid 1px #D9D9D9"><div class="row form-group text-center"><label class="control-label">Tax</label></div>'
            + '<div class="row form-group">'
            + '<label class="col-lg-3 control-label text-right">Adult</label>'
            + '<div class="col-sm-6">'
            + '<input id="adTaxCost-' + rowId + '" name="adTaxCost-' + rowId + '" class="form-control text-right money"  type="text" maxlength="10" >'
            + '</div>'
            + '</div>'
            + '<div class="row form-group">'
            + '<label class="col-lg-3 control-label text-right">Child</label>'
            + '<div class="col-sm-6">'
            + '<input id="chTaxCost-' + rowId + '" name="chTaxCost-' + rowId + '" class="form-control text-right money"  type="text" maxlength="10">'
            + '</div>'
            + '</div>'
            + '<div class="row form-group text-right">'
            + '<label class="col-lg-3 control-label">Infant</label>'
            + '<div class="col-sm-6">'
            + '<input id="inTaxCost-' + rowId + '" name="inTaxCost-' + rowId + '" class="form-control text-right money"  type="text" maxlength="10" >'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="col-sm-3" style="border-right:solid 1px #D9D9D9"><div class="row form-group text-center"><label class="control-label">Price</label></div>'
            + '<div class="row form-group">'
            + '<label class="col-lg-3 control-label text-right">Adult</label>'
            + '<div class="col-sm-6">'
            + '<input id="adPrice-' + rowId + '" name="adPrice-' + rowId + '" class="form-control text-right money"  type="text" maxlength="10" >'
            + '</div>'
            + '</div>'
            + '<div class="row form-group">'
            + '<label class="col-lg-3 control-label text-right">Child</label>'
            + '<div class="col-sm-6">'
            + '<input id="chPrice-' + rowId + '" name="chPrice-' + rowId + '" class="form-control text-right money"  type="text" maxlength="10" >'
            + '</div>'
            + '</div>'
            + '<div class="row form-group text-right">'
            + '<label class="col-lg-3 control-label">Infant</label>'
            + '<div class="col-sm-6">'
            + '<input id="inPrice-' + rowId + '" name="inPrice-' + rowId + '" class="form-control text-right money"  type="text" maxlength="10" >'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="col-sm-3"><div class="row form-group text-center"><label class="control-label">Tax</label></div>'
            + '<div class="row form-group">'
            + '<label class="col-lg-3 control-label text-right">Adult</label>'
            + '<div class="col-sm-6">'
            + '<input id="adTax-' + rowId + '" name="adTax-' + rowId + '" class="form-control text-right money"  type="text" maxlength="10" >'
            + '</div>'
            + '</div>'
            + '<div class="row form-group">'
            + '<label class="col-lg-3 control-label text-right">Child</label>'
            + '<div class="col-sm-6">'
            + '<input id="chTax-' + rowId + '" name="chTax-' + rowId + '" class="form-control text-right money"  type="text" maxlength="10">'
            + '</div>'
            + '</div>'
            + '<div class="row form-group text-right">'
            + '<label class="col-lg-3 control-label">Infant</label>'
            + '<div class="col-sm-6">'
            + '<input id="inTax-' + rowId + '" name="inTax-' + rowId + '" class="form-control text-right money"  type="text" maxlength="10" >'
            + '</div>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="text-center">'
            + '<a class="btn btn-success" id="btnConfirmAdd-' + rowId + '" data-toggle="modal" onclick="setId(' + rowId + ')" data-target="#ConfirmAdd"><span class="glyphicon glyphicon-ok"></span> Confirm</a>'
            + '</div>'
            + '</div>'
            );
    $("div").find('.date').datetimepicker();
    $("div").find('.times').datetimepicker({
        pickDate: false,
        pickTime: true,
        pick12HourFormat: false,
        format: 'HH:mm'
    });
    $("div").find('.input-group-addon').click(function () {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    $("#flight-0-status option").clone().appendTo("#flight-" + rowId + "-status");
    $("#flight-0-ticketType option").clone().appendTo("#flight-" + rowId + "-ticketType");
    $("#flight-0-class option").clone().appendTo("#flight-" + rowId + "-class");
//     $.each(tickettype, function(key, value) { 
//     $("#flight-"+rowId+"-ticketTypeCom")
//         .append($("<option></option>")
//         .attr("value",key)
//         .text(value)); 
//    });

}

//// ON KEY INPUT AUTO SELECT AIRLIN FOR AIRLINE
$(document).ready(function () {
$(document).on('keyup', '.airline', function () {   
    var id = $(this).data("id"); 
    console.log("id :" + id);
    
    codeAirline = [];
    $.each(airline, function (key, value) {
        codeAirline.push(value.airline_code);
        if ( !(value.airline_name in codeAirline) ){
           codeAirline.push(value.airline_name);
        }
    });
    console.log(codeAirline);
    $("#airlineCode" + id).autocomplete({
        source: codeAirline,
        close:function( event, ui ) {
           $("#airlineCode" + id).trigger('keyup');
        }
    });
    
    var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        
    $("#airlineId" + id + ",#airlineName" + id).val(null);
    
    var code = $(this).val().toUpperCase();
    console.log(code);
    $.each(airline, function (key, value) {
        //alert("key:"+value.airline_id);
        if (value.airline_code.toUpperCase() === code) {
            console.log('add newww');
            $("#airlineId" + id).val(value.airline_id);
            $("#airlineCode" + id).val(value.airline_code);
            $("#airlineName" + id).val(value.airline_name);
        }else if (value.airline_name.toUpperCase() === code){
            $("#airlineId" + id).val(value.airline_id);
            $("#airlineCode" + id).val(value.airline_code);
            $("#airlineName" + id).val(value.airline_name);
        }
    });
});
});
// ON KEY INPUT AUTO SELECT AIRLIN FOR DEPARTURE
var showflagDepDum = 1;
var showflagArrDum = 1;
function getDepartureAirportDummy(name,count){
    var servletName = 'AirTicketServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getautoairport';
    CallAjaxDepartureAirportDummy(param,count);
}
function getArrivalAirportDummy(name,count){
    var servletName = 'AirTicketServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getautoairport';
    CallAjaxArraivalAirportDummy(param,count);
}
function CallAjaxDepartureAirportDummy(param,count){
     var url = 'AJAXServlet';
     var depArray = [];
     var depListId= [];
     var depListCode= [];
     var depListName= [];
     var depid , depcode ,depname;
     $(".departurecodeVal").autocomplete("destroy");
     try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            beforeSend: function() {
               $("#datadepload-"+count).removeClass("hidden");    
            },
            success: function(msg) {     
                console.log("getDepartureAirportDummy =="+msg);
                var depJson =  JSON.parse(msg);
                for (var i in depJson){
                    if (depJson.hasOwnProperty(i)){
                        depid = depJson[i].id;
                        depcode = depJson[i].code;
                        depname = depJson[i].name;
                        depArray.push(depcode);
                        depArray.push(depname);
                        depListId.push(depid);
                        depListCode.push(depcode);
                        depListName.push(depname);
                    }                 
                    $("#datadepload-"+count).addClass("hidden"); 
                }
                $(".departureid").val(depid);
                $(".departurecode").val(depcode);
                $(".departurename").val(depname);

                $(".departurecodeVal").autocomplete({
                    source: depArray,
                    close: function(){
                        $(".departurecodeVal").trigger("keyup");
                        var depselect = $(".departurecodeVal").val();
                        for(var i =0;i<depListId.length;i++){
                            if((depselect==depListName[i])||(depselect==depListCode[i])){      
                                $(".departureid").val(depListId[i]);
                                $(".departurecode").val(depListCode[i]);
                                $(".departurecodeVal").val(depListCode[i]);
                                $(".departurename").val(depListName[i]);
                            }                 
                        }   
                    }
                 });
                
                var selectval = $(".departurecodeVal").val();
                for(var i =0;i<depListId.length;i++){
                    if(selectval==depListName[i]){
                        $(".departurecodeVal").val(depListCode[i]);
                    }
                }
                if(depListCode.length === 1){
                    showflagDepDum = 0;
                    $(".departurecodeVal").val(depListCode[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $(".departurecodeVal").trigger(event);
            }, error: function(msg) {
                console.log('auto Departure Dummy ERROR');
                $("#datadepload-"+count).addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}
function CallAjaxArraivalAirportDummy(param,count){
     var url = 'AJAXServlet';
     var arrArray = [];
     var arrListId= [];
     var arrListCode= [];
     var arrListName= [];
     var arrid , arrcode ,arrname;
     $(".arrivalcodeVal").autocomplete("destroy");
     try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            beforeSend: function() {
               $("#dataarrload-"+count).removeClass("hidden");    
            },
            success: function(msg) {     
                console.log("getArrivalAirportDummy =="+msg);
                var arrJson =  JSON.parse(msg);
                for (var i in arrJson){
                    if (arrJson.hasOwnProperty(i)){
                        arrid = arrJson[i].id;
                        arrcode = arrJson[i].code;
                        arrname = arrJson[i].name;
                        arrArray.push(arrcode);
                        arrArray.push(arrname);
                        arrListId.push(arrid);
                        arrListCode.push(arrcode);
                        arrListName.push(arrname);
                    }                 
                    $("#dataarrload-"+count).addClass("hidden"); 
                }
                $(".arrivalid").val(arrid);
                $(".arrivalcode").val(arrcode);
                $(".arrivalname").val(arrname);

                $(".arrivalcodeVal").autocomplete({
                    source: arrArray,
                    close: function(){
                        $(".arrivalcodeVal").trigger("keyup");
                        var arrselect = $(".arrivalcodeVal").val();
                        for(var i =0;i<arrListId.length;i++){
                            if((arrselect==arrListName[i])||(arrselect==arrListCode[i])){      
                                $(".arrivalid").val(arrListId[i]);
                                $(".arrivalcode").val(arrListCode[i]);
                                $(".arrivalcodeVal").val(arrListCode[i]);
                                $(".arrivalname").val(arrListName[i]);
                            }                 
                        }   
                    }
                 });
                
                var selectval = $(".arrivalcodeVal").val();
                for(var i =0;i<arrListId.length;i++){
                    if(selectval==arrListName[i]){
                        $(".arrivalcodeVal").val(arrListCode[i]);
                    }
                }
                if(arrListId.length === 1){
                    showflagArrDum = 0;
                    $(".arrivalcodeVal").val(arrListCode[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $(".arrivalcodeVal").trigger(event);
            }, error: function(msg) {
                console.log('auto Arrival Dummy ERROR');
                $("#dataarrload-"+count).addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}
$(document).ready(function () {
 //Begin departure*********************************
$(document).on('keyup', '.departurecodeVal', function (event) {
    var id = $(this).data("id");
    console.log("id :" + id);
    var position = $(this).offset();
    $(".ui-widget").css("top", position.top + 30);
    $(".ui-widget").css("left", position.left); 
    if($(this).val() === ""){
        $(".departureid").val("");
        $(".departurecode").val("");
        $(".departurename").val("");
    }else{
        if(event.keyCode === 13){
            getDepartureAirportDummy(this.value,id); 
            console.log("name="+this.value);
        }
    }

}); 
    
$(document).on('keydown','.departurecodeVal',function (event) {
    //debug curCSS
    if(! $.isFunction($.fn.curCSS)) {
       $.curCSS = $.css; 
       $.fn.curCSS = $.fn.css; 
    }
     var position = $(this).offset();
        $(".ui-widget").css("top", position.top+30);
        $(".ui-widget").css("left", position.left); 
        if(showflagDepDum === 0){
            $(".ui-widget").css("top", -1000);
            showflagDepDum = 1;
        }
    
});
//End departure***********************************
//Begin Arraival***********************************
$(document).on('keyup', '.arrivalcodeVal', function (event) {
    var id = $(this).data("id");
    console.log("id :" + id);
    var position = $(this).offset();
    $(".ui-widget").css("top", position.top + 30);
    $(".ui-widget").css("left", position.left); 
    if($(this).val() === ""){
        $(".arrivalid").val("");
        $(".arrivalcode").val("");
        $(".arrivalname").val("");
    }else{
        if(event.keyCode === 13){
            getArrivalAirportDummy(this.value,id); 
            console.log("name="+this.value);
        }
    }

}); 
    
$(document).on('keydown','.arrivalcodeVal',function (event) {
    //debug curCSS
    if(! $.isFunction($.fn.curCSS)) {
       $.curCSS = $.css; 
       $.fn.curCSS = $.fn.css; 
    }
     var position = $(this).offset();
        $(".ui-widget").css("top", position.top+30);
        $(".ui-widget").css("left", position.left); 
        if(showflagArrDum === 0){
            $(".ui-widget").css("top", -1000);
            showflagArrDum = 1;
        }
    
});
//End Arraival***********************************


//    codeDeparture = [];
//    $.each(a, function (key, value) {
//        codeDeparture.push(value.code);
//        if ( !(value.name in codeDeparture) ){
//           codeDeparture.push(value.name);
//        }
//    });
//    console.log(codeDeparture);
//    $("#departure-" + id + "-code").autocomplete({
//        source: codeDeparture,
//        close:function( event, ui ) {
//           $("#departure-" + id + "-code").trigger('keyup');
//        }
//    });
//    
//    var position = $(this).offset();
//        console.log("positon :" + position.top);
//        $(".ui-widget").css("top", position.top + 30);
//        $(".ui-widget").css("left", position.left);
//    
//    $("#departure-" + id + "-id,#departure" + id + "-name").val(null);
//    var code = $(this).val().toUpperCase();
//    console.log(code);
//    $.each(a, function (key, value) {
//        if (value.code.toUpperCase() === code) {
//            console.log('add new');
//            $("#departure-" + id + "-id").val(value.id);
//            $("#departure-" + id + "-code").val(value.code);
//            $("#departure-" + id + "-name").val(value.name);
//        }else if (value.name.toUpperCase() === code){
//            $("#departure-" + id + "-id").val(value.id);
//            $("#departure-" + id + "-code").val(value.code);
//            $("#departure-" + id + "-name").val(value.name);
//        }
//    });


   
});
// ON KEY INPUT AUTO SELECT AIRLIN FOR ARRIVAL
$(document).ready(function () {
$(document).on('keyup', '.arrival', function () {
    var id = $(this).data("id");
    console.log("id :" + id);
    
    codeArrival = [];
    $.each(a, function (key, value) {
        codeArrival.push(value.code);
        if ( !(value.name in codeArrival) ){
           codeArrival.push(value.name);
        }
    });
    console.log(codeArrival);
    $("#arrival-" + id + "-code").autocomplete({
        source: codeArrival,
        close:function( event, ui ) {
           $("#arrival-" + id + "-code").trigger('keyup');
        }
    });
    
    var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
    
    $("#arrival-" + id + "-id,#arrival" + id + "-name").val(null);
    var code = $(this).val().toUpperCase();
    console.log(code);
    $.each(a, function (key, value) {
        if (value.code.toUpperCase() === code) {
            console.log('add new');
            $("#arrival-" + id + "-id").val(value.id);
            $("#arrival-" + id + "-code").val(value.code);
            $("#arrival-" + id + "-name").val(value.name);
        }else if (value.name.toUpperCase() === code){
            $("#arrival-" + id + "-id").val(value.id);
            $("#arrival-" + id + "-code").val(value.code);
            $("#arrival-" + id + "-name").val(value.name);
        }
    });
});
});

function setId(rowId) {
    nId = rowId;
}
// ADD FLIGHT TO TABLE
function addRowTable() {//winit
    var counter = nId;
   
    console.log('addRowTable');
    TableAir.fnAddData([
        counter, //order 1
        $("#airlineName" + counter).val(), //name 1
        $("#flight-" + counter + "-flightNo").val(),
        $("#departure-" + counter + "-code").val(),
        $("#arrival-" + counter + "-code").val(),
        $("#flight-" + counter + "-departDate").val(),
        $("#flight-" + counter + "-departTime").val(),
//        '<select id="flight-'+counter+'-ticketTypeCom" name="flight-'+counter+'-ticketTypeCom" class="form-control">'+ 
//        '</select>',
//        '<select id="flight-'+counter+'-classCom" name="flight-'+counter+'-classCom" class="form-control">'+
//            '<option value=""></option>'+
//        '</select>',
        $("#flight-"+counter+"-ticketType option:selected").text(),
        $("#flight-"+counter+"-class option:selected").text()+" "+$("#flight-" + counter + "-subClass").val(),
        $("#adCost-" + counter).val(),
        $("#adPrice-" + counter).val(),
        $("#flight-" + counter + "-status").val(),
        '<div class="text-center"><a class="carousel" data-toggle="collapse" data-parent="#accordion"data-target="#flight' + counter + '" aria-expanded="true" aria-controls="collapseExample"><span class="glyphicon glyphicon-edit editicon"></span></a>'
                + ' <span class="glyphicon glyphicon-remove deleteicon" onclick="setDisableFlight(' + counter + ',2);" data-toggle="modal" data-target="#DisableFlight"></span></div>'
    ]);
    $("div").find($('.collapse')).collapse('hide');
    $("#btnConfirmAdd-" + counter).prop('class', 'hidden');

    var airId = $("#airlineId" + counter).val();
    var airCode = $("#airlineCode" + counter).val();
    var airName = $("#airlineName" + counter).val();

    $("#FlightTable tbody").append(
            '<tr><td class="air-flight-id hidden">' + airId
            + '</td><td class="air-flight-code">' + airCode
            + '</td><td class="air-flight-name">' + airName
            + '</td></tr>'
            );

    flight.push({id: counter, air_id: airId, air_code: airCode, air_name: airName});

}


function hideCollapse() {
    console.log('hide collapse2');
    $("div").find($('.collapse')).collapse('hide');
}

function setformat() {
    $('.moneyformat').each(function () {
        var innerHTML = $(this).html();
        $(this).html(numberWithCommas($(this).html()));
    });
}


// #############################   PASSENGER  ############################### //
$(document).ready(function () {
    Array.prototype.remove = function (from, to) {
        var rest = this.slice((to || from) + 1 || this.length);
        this.length = from < 0 ? this.length + from : from;
        return this.push.apply(this, rest);
    };

    $("div").on('mouseover', '.panel-default', function () {
        for (var i = 0; i < flight.length - 1; i++) {
            if (flight[i].air_id == flight[i + 1].air_id) {
                flight.remove(i);
            }
        }
//        console.log(flight);
        $("#FlightTable tbody tr").empty();
        $.each(flight, function (key, value) {
            $("#FlightTable tbody").append(
                    '<tr><td class="air-flight-id hidden">' + value.air_id + '</td><td class="air-flight-code">' + value.air_code + '</td><td class="air-flight-name">' + value.air_name + '</td></tr>'
                    );
        });
    });

    flightTable = $('#FlightTable');
    $("div").on('click', '.input-group-addon', function () {
        airline_id = $(this).data("id");
    });
    flightTable.on('click', 'tr', function () {
        var airFlightId = $(this).find(".air-flight-id").text();
        var airFlightCode = $(this).find(".air-flight-code").text();
        var airFlightName = $(this).find(".air-flight-name").text();
        $("#passengerAirlineId" + airline_id).val(airFlightId);
        $("#passengerAirlineCode" + airline_id).val(airFlightCode);
        $("#passengerAirlineName" + airline_id).val(airFlightName);
        $("#FlightModal").modal('hide');

    });


    $(document).on('keyup', '.air-flight', function () {
        var id = $(this).data("id");
        console.log("id :" + id);
        $("#passengerAirlineId" + id + ",#passengerAirlineName" + id).val(null);
        var code = $(this).val().toUpperCase();
        console.log(code);
        $.each(flight, function (key, value) {
            if (value.air_code.toUpperCase() === code) {
                console.log('add new');
                $("#passengerAirlineId" + id).val(value.air_id);
                $("#passengerAirlineName" + id).val(value.air_name);
            }
        });
    });

    // SET TABLE PASSNEGER
    passengerTable = $('#passenger_table');
    passengerDatatable = passengerTable.dataTable({bJQueryUI: false,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bPaginate": false,
        "bInfo": true,
        "oLanguage": {
            "sInfo": 'Total : _TOTAL_',
            "sInfoEmpty": 'Total : 0'
        }
    });
    // TABLE PASSENGER SHOW TOTAL
    $("#passenger_table_info").appendTo($("#showTotalPassener"));


    passengerTable.find('tbody').on('click', 'tr', function () {

        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
        } else {
            passengerDatatable.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
        }

    });
    $("#btn-addPassenger").click(function () {
        $('.collapse').collapse('hide');
        var countNow = parseInt($("#countRowPassenger").val());
        var row = $("#passenger_table tr").length;
        row = parseInt(row - 1);
        console.log(row);
        var empty = $('#passenger_table tbody td').hasClass('dataTables_empty');
        if (empty) {
            addPassenger(countNow);
            $('#passenger' + countNow).collapse();
            $("#countRowPassenger").val(countNow + 1);
            if (flight.length == 1) {
                console.log('flight =1');
                $("#passengerAirlineId" + countNow).val(flight[0].air_id);
                $("#passengerAirlineCode" + countNow).val(flight[0].air_code);
                $("#passengerAirlineName" + countNow).val(flight[0].air_name);
            }
        } else {
            if ((countNow - row) == 1) {
                addPassenger(countNow);
                $('#passenger' + countNow).collapse();
                $("#countRowPassenger").val(countNow + 1);
                if (flight.length == 1) {
                    console.log('flight =1');
                    $("#passengerAirlineId" + countNow).val(flight[0].air_id);
                    $("#passengerAirlineCode" + countNow).val(flight[0].air_code);
                    $("#passengerAirlineName" + countNow).val(flight[0].air_name);
                }
            } else {
                // SHOW COLLAPSE
                $("#passenger" + (countNow - 1)).collapse('show');
            }
        }

    });



// DELETE PASSENGER
    $('.confirm-delete').on('click', function (e) {
        e.preventDefault();
        var id = $(this).data('id');
        $("#DeletePassengerModal").find('.modal-body').text("are you sure delete passenger ?");
        $('#DeletePassengerModal').data('id', id).modal('show');
    });
    $('#DeletePassengerModal .btn-danger').click(function () {
        var id = $('#DeletePassengerModal').data('id');
        console.log(id);
        $.ajax({
            url: 'AirTicketDetail.smi?action=deletePassenger',
            type: 'get',
            data: {passengerId: id},
            success: function () {
                console.log('success');
            },
            error: function () {
                console.log("error");
            }
        });
        //$('#passenger_table [data-id=' + id + ']').parent().parent().remove();
        //$('#DeletePassengerModal').modal('hide');
        location.reload();
    });

});

function addPassenger(row) {
    $("#divAddPassenger").append(
            '<div class="collapse" id="passenger' + row + '">'
            + '<div class="panel panel-default">'
            + '<div class="panel-heading">'
            + '<h3 class="panel-title">Passenger Detail</h3>'
            + '</div>'
            + '<div class="panel-body">'
            + '<div class="row form-group">'
            + '<div class="col-sm-1 text-right"><strong>Name<strong style="color: red">*</strong></strong></div>'
            + '<div class="col-sm-2">'
            + '<select class="form-control" name="passengerIntialname' + row + '" id="passengerIntialname' + row + '">'
            + '</select>'
            + '</div>'
            + '<div class="col-sm-3">'
            + '<input type="text" name="passengerlastname' + row + '" id="passengerlastname' + row + '"  class="form-control" maxlength="50" />'
            + '</div>'
            + '<div class="col-sm-3">'
            + '<input type="text" name="passengerfirstname' + row + '" id="passengerfirstname' + row + '"  class="form-control pFirstname" maxlength="50"  data-bv-notempty data-bv-notempty-message="The Firstname is required"/>'
            + '</div>'
            + '<div class="col-sm-1 text-right"><strong>PAX&nbsp;TYPE</strong><strong style="color: red">*</strong></div>'
            + '<div class="col-sm-2">'
            + '<select name="passengerCategory' + row + '" id="passengerCategory' + row + '" class="form-control">'
            + '</select>'
            + '</div>'
            + '</div>'
            + '<div class="row form-group">'
            + '<div class="col-sm-1 text-right"><strong>Ticket</strong></div>'
            + '<div class="col-sm-5" style="float:left; display:inline-block">'
            + '<input name="passengerSeriesOne' + row + '" id="passengerSeriesOne' + row + '"  class="form-control" style="width:10%; display: inline;" maxlength="3" />'
            + '<input name="passengerSeriesTwo' + row + '" id="passengerSeriesTwo' + row + '"  class="form-control" style="width:25%; display: inline;" maxlength="10" />'
            + '<input name="passengerSeriesThree' + row + '" id="passengerSeriesThree' + row + '"  class="form-control" style="width:10%; display: inline;" maxlength="3" />'
            + '</div>'
            + '<div class="col-sm-1 text-right"><strong>Airline</strong><strong style="color: red">*</strong></div>'
            + '<div class="col-sm-2">'
            + '<div class="form-group">'
            + '<div class="input-group ">'
            + '<input type="hidden" class="form-control" id="passengerAirlineId' + row + '" name="passengerAirlineId' + row + '">'
            + '<input name="passengerAirlineCode' + row + '" id="passengerAirlineCode' + row + '"  data-id="' + row + '" class="form-control air-flight" maxlength="5" data-bv-notempty data-bv-notempty-message="The airline is required"/>'
            + '<span class="input-group-addon" data-toggle="modal" data-target="#FlightModal" data-id="' + row + '">'
            + '<span class="glyphicon-search glyphicon"></span>'
            + '</span>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="col-sm-3">'
            + '<input name="passengerAirlineName' + row + '" id="passengerAirlineName' + row + '" class="form-control" disabled="">'
            + '</div>'
            + '</div>'
            + '<div class="row form-group">'
            + '<div class="col-sm-1 text-right"><strong>Fare</strong></div>'
            + '<div class="col-sm-2"><input name="passengerFare' + row + '" id="passengerFare' + row + '"  class="form-control text-right money" maxlength="11" /></div>'
            + '<div class="col-sm-1 text-right"><strong>Tax</strong></div>'
            + '<div class="col-sm-2"><input name="passengerTax' + row + '" id="passengerTax' + row + '"  class="form-control text-right money" maxlength="11" /></div>'
            + '<div class="col-sm-1 text-right"><strong>Type</strong><strong style="color: red">*</strong></div>'
            + '<div class="col-sm-2">'
            + '<select class="form-control" name="passengerTicketType' + row + '" id="passengerTicketType' + row + '">'
            + '<option value="I">Inter</option>'
            + '<option value="D">Domestic</option>'
            + '</select>'
            + '</div>'
            + '<div class="col-sm-1 text-right"><strong>From</strong><strong style="color: red">*</strong></div>'
            + '<div class="col-sm-2">'
            + '<select class="form-control" name="passengerFrom' + row + '" id="passengerFrom' + row + '">'
            + '<option value="C">In</option>'
            + '<option value="O">Out</option>'
            + '</select>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="text-center" style="padding:5px">'
            + '<a class="btn btn-success" id="btnConfirmAddPassenger' + row + '" data-toggle="modal" onclick="setPId(' + row + ')" data-target="#ConfirmAddPassenger"><span class="glyphicon glyphicon-ok"></span> Confirm</a>'
            + '</div>'
            + '</div>'
            + '</div>'
            );

    $("#passengerIntialnameList option").clone().appendTo("#passengerIntialname" + row);    // SET LIST INTIALNAME
    $("#passengerCategoryList option").clone().appendTo("#passengerCategory" + row);        // SET LIST CATEGORY
    $("#passengerCategory" + row).val("NON");


}

function setPId(rowPId) {
    pId = rowPId;
}

function addRowPassengerTable() {
    var counter = pId;
    console.log('addRowPassengerTable');
    passengerDatatable.fnAddData([
        $("#passengerAirlineName" + counter).val(),
        $("#passengerCategory" + counter + " option:selected").text(),
        $("#passengerSeriesOne" + counter).val(),
        $("#passengerSeriesTwo" + counter).val(),
        $("#passengerSeriesThree" + counter).val(),
        $("#passengerFare" + counter).val(),
        $("#passengerTax" + counter).val(),
        $("#passengerTicketType" + counter + " option:selected").text(),
        $("#passengerFrom" + counter + " option:selected").text(),
        '<div class="text-center"><a data-toggle="collapse" onclick="hideCollapse()" data-parent="#accordion" data-target="#passenger' + counter + '" aria-expanded="true" aria-controls="collapseExample"><span class="glyphicon glyphicon-edit editicon"></span></a>'
                // + ' <span class="glyphicon glyphicon-remove deleteicon" onclick="setDisable(' + counter + ',2);" data-toggle="modal" data-target="#DisableFlight"></span></div>'
    ]);
    $("div").find($('.collapse')).collapse('hide');
    $("#btnConfirmAddPassenger" + counter).prop('class', 'hidden');

            document.getElementById("ButtonSave").disabled = false;
}

