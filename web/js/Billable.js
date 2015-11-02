/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var showflag = 1;


$(document).ready(function() {
    // validate
    var Billable = $("#Billable");
    Billable.on('mouseover', function () {
        var billto = $(this).find('[name="billto"]');
        var isEmpty = billto.val() === '';
        Billable.bootstrapValidator('enableFieldValidators', 'billto', isEmpty);
        var billdate = $(this).find('[name="billdate"]');
        var isEmpty = billdate.val() === '';
        Billable.bootstrapValidator('enableFieldValidators', 'billdate', isEmpty);
       
    });

    $("#searchBillto").keyup(function(event) {
        if (event.keyCode === 13) {
            if ($("#searchBillto").val() == "") {
                // alert('please input data');
            }
            searchCustomerAgentList($("#searchBillto").val());
        }
    });

    //autocomplete
    $("#billtoVal").keyup(function(event){   
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left); 
        if($(this).val() === ""){
            $("#billname").val("");
            $("#address").val("");
        }else{
            if(event.keyCode === 13){
                searchCustomerAutoList(this.value); 
            }
        }
    });
    $("#billtoVal").keydown(function(){
        
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left); 
            if(showflag == 0){
                $(".ui-widget").css("top", -1000);
                showflag=1;
            }
        
    });
    
  
});

function searchCustomerAutoList(name){
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getAutoListBillto';
    CallAjaxAuto(param);
}

function CallAjaxAuto(param){
     var url = 'AJAXServlet';
     var billArray = [];
     var billListId= [];
     var billListName= [];
     var billListAddress= [];
     var billid , billname ,billaddr;
     $("#billtoVal").autocomplete("destroy");
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
             //   console.log("getAutoListBillto =="+msg);
                var billJson =  JSON.parse(msg);
                for (var i in billJson){
                    if (billJson.hasOwnProperty(i)){
                        billid = billJson[i].id;
                        billname = billJson[i].name;
                        billaddr = billJson[i].address;
                        billArray.push(billid);
                        billArray.push(billname);
                        billListId.push(billid);
                        billListName.push(billname);
                        billListAddress.push(billaddr);
                    }                 
                     $("#dataload").addClass("hidden"); 
                }
                $("#billto").val(billid);
                $("#billname").val(billname);
                $("#address").val(billaddr);
                
              
                $("#billtoVal").autocomplete({
                    source: billArray,
                    close: function(){
                         $("#billtoVal").trigger("keyup");
                         var billselect = $("#billtoVal").val();
                        for(var i =0;i<billListId.length;i++){
                            if((billselect==billListName[i])||(billselect==billListId[i])){      
                                $("#billtoVal").val(billListId[i]);
                                $("#billname").val(billListName[i]);
                                $("#address").val(billListAddress[i]);
                            }                 
                        }   
                    }
                 });
                
                var billval = $("#billtoVal").val();
                for(var i =0;i<billListId.length;i++){
                    if(billval==billListName[i]){
                        $("#billtoVal").val(billListId[i]);
                    }
                }
                if(billListId.length == 1){
                    showflag = 0;
                    $("#billtoVal").val(billListId[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $("#billtoVal").trigger(event);
                  
            }, error: function(msg) {
                console.log('auto ERROR');
                $("#dataload").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

function searchCustomerAgentList(name) {
    var servletName = 'BillableServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'getListBillto';
    CallAjax(param);
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
                $('#BillToTable').dataTable().fnClearTable();
                $('#BillToTable').dataTable().fnDestroy();
                $("#BillToTable tbody").empty().append(msg);

                $('#BillToTable').dataTable({bJQueryUI: true,
                    "sPaginationType": "full_numbers",
                    "bAutoWidth": false,
                    "bFilter": false,
                    "bPaginate": true,
                    "bInfo": false,
                    "bLengthChange": false,
                    "iDisplayLength": 10
                });
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

function setBillValue(billto, billname, address, term, pay) {
    $("#billtoVal").val(billto);
    $("#billname").val(billname);
    if (address == 'null') {
        $("#address").val("");
    } else {
        $("#address").val(address);
    }

    if (term == 'null') {
        $("#accterm").val("");
    } else {
        $("#accterm").val(term);
    }
    if (pay == 'null') {
        $("#accpay").val("");
    } else {
        $("#accpay").val(pay);
    }

    $("#BillToModal").modal('hide');
}

$(document).ready(function () {
    var bla = $('#resultText').val();

    if(bla == "save successful"){
        
        $('#textAlertDivSave').show();
    }else if ( bla === ""){
        $('#textAlertDivSave').hide();
    }else if(bla ===  "deletesuccess"){
        $('#textAlertDivDelete').show();
    }else if(bla ===  "deletefail"){
        $('#textAlertDivNotDelete').show();
    }else {
        $('#textAlertDivNotSave').show();
    }
    
});

//Print Ticket Order Button
$(document).ready(function () {
    var printBtn = $('#printTicketOrder').val();
    if(printBtn !== 'fail'){
        $('#btnPrintTicketOrder').removeClass('disabled');
    }
    
});

function printTicketOrderReport(){
    var printBtn = $('#printTicketOrder').val();
    var refno = $('#getRealformatUrl').val(); 
    window.open("report.smi?name=TicketOrder"+"&refno="+refno+"&pnrID="+printBtn);  
}

//Add New Booking
function addNewBooking(){
    window.location.href = "BookDetail.smi?&action=new";
}

function deleteBillableList(id,Ccount) {
    document.getElementById('billDescIdDelete').value = id;
    document.getElementById('billDescRowDelete').value = Ccount;
    
    var billtype = document.getElementById('billtype-'+Ccount).value ;
    var detail = document.getElementById('detail-'+Ccount).value ;

    $("#delBillable").text('Are you comfirm to delete billable '+ billtype + ' : '+ detail);
    $('#DeleteBillableListModal').modal('show');
}

function DeleteRowBillable(){
    var cCount = document.getElementById('billDescRowDelete').value;
    var id = document.getElementById('billDescIdDelete').value;
    if(id === ''){
        $("#billtype-" + cCount).parent().parent().remove();
    }else {
        var action = document.getElementById('action');
        action.value = 'deleteBillable';
        document.getElementById('billDescIdDelete').value = id;
        document.getElementById('billDescRowDelete').value = cCount;
        
        document.getElementById('Billable').submit();
        
//        if($("#deleteresultText").val() === "successful" ){
//            $("#billtype-" + cCount).parent().parent().remove();
//        }
//        $.ajax({
//            url: 'Billable.smi?action=deleteBillable',
//            type: 'get',
//            data: {billdescIdDelete: id},
//            success: function () {
//                $("#billtype-" + cCount).parent().parent().remove();
////                $('#textAlertDivDelete').show();
//            },
//            error: function () {
//                console.log("error");
//                $('#textAlertDivNotDelete').show();
//            }
//        }); 
    }
    $('#DeleteBillableListModal').modal('hide');
}

//function deleteReceiptDetailList(id,Ccount) {
//    document.getElementById('receiptIdDelete').value = id;
//    document.getElementById('receiptRowDelete').value = Ccount;
//    $("#delReceiptDetail").text('Are you sure delete this credit ?');
//    $('#DeleteReceiptDetailListModal').modal('show');
//}