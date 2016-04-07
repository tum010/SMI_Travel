/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function convertFormatDates(date){
    if(date === ''){
        return '';
    }
    var newDate = date.toString().split("-");
    var result = new Date(newDate[2], newDate[1] , newDate[0]);
    return newDate[2] + "-" + newDate[1] + "-" + newDate[0];
}
function printHotelMonthly(){
    var from  = convertFormatDates($("#fromdate").val());
    var to  = convertFormatDates($("#todate").val());
    var department = $('#department').val();
    var detail = $('#detail').val();
    var systemuser = $('#systemuser').val();
   
    if((from !== '') && (to !== '')){
        window.open("report.smi?name=HotelMonthlyTempReport&fromdate="+from+"&todate="+to+"&department="+department+"&detail="+detail+"&systemuser="+systemuser);   
    }else{    
        validateDate();
    }  
}

//function validateDate(date,option){
//        if(option === 'over'){
//            if(date === 'from'){
//                $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'fromdate');
//                $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'todate');
//            }
//            if(date === 'to'){
//                $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'fromdate');
//                $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'todate');
//            }           
//            $("#btnDownloadAP").addClass("disabled");
//        } else {
//            $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'fromdate');
//            $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'todate');
//            $("#btnDownloadAP").addClass("disabled");
//        }
//    }
