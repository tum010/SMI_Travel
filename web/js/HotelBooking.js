 $(document).ready(function () {
     
        //Table
        var table = $('#HotelTable').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bPaginate": false,
            "bInfo": false
        });
        $('#HotelTable tbody').on('click', 'tr', function () {
            $('.collapse').collapse('show');
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
            }
        });

        setformat();
    });


 function setformat() {
    $('#HotelTable tr td.moneyformat').each(function () {
        var innerHTML = $(this).html();
        $(this).html(numberWithCommas($(this).html()));
    });
}


function DeleteHotel(id,name){
    var HotelID = document.getElementById('HotelID');
    HotelID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete booking Hotel : " + name + " ?";
}

function EnableHotel(id,name){
    var OtherID = document.getElementById('HotelID');
    OtherID.value = id;
    document.getElementById('enableCode').innerHTML = "Are you sure to enable booking Hotel : " + name + " ?";
}

function Enable() {
    var action = document.getElementById('action');
    action.value = 'enable';
    document.getElementById('HotelForm').submit();
}

function Delete() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('HotelForm').submit();
}

function printVoucher(){
    $('#textAlertSelectHotel').hide();
    $('#textAlertSelectVoucher').hide();
    var printtype = document.getElementById('printtype').value;
    var HotelID = "";
    $('#HotelTable tr.row_selected').each(function () {
        HotelID = $(this).attr('id');
    });
    if(HotelID == ""){
        $('#textAlertSelectVoucher').hide();
        $('#textAlertSelectHotel').show();
    }else if(printtype == 0){
        $('#textAlertSelectHotel').hide();
        $('#textAlertSelectVoucher').show();
    }else if(printtype == 1){
        window.open("report.smi?name=HotelVoucher&hotelID="+HotelID);
    }else if(printtype == 2){
        window.open("report.smi?name=HotelInoundVoucher&hotelID="+HotelID);
    }else if(printtype == 3){
        window.open("report.smi?name=HotelVoucherEmail&hotelID="+HotelID);
    }else if(printtype == 4){
        window.open("report.smi?name=HotelVoucherEmailAgent&hotelID="+HotelID);
    }
    
}