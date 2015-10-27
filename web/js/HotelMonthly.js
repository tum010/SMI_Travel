/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function printHotelMonthly(){
    var from  = $("#fromdate").val();
    var to  = $("#todate").val();
    var department = $('#department').val();
    var detail = $('#detail').val();
    var systemuser = $('#systemuser').val();
    if((from === '') || (to === '')){
        validateDate();
    } else {
        if(detail === '1'){
            window.open("report.smi?name=HotelMonthlyDetailReport&fromdate="+from+"&todate="+to+"&department="+department+"&detail="+detail+"&systemuser="+systemuser);   
        }else{
            window.open("report.smi?name=HotelMonthlyReport&fromdate="+from+"&todate="+to+"&department="+department+"&detail="+detail+"&systemuser="+systemuser);   
        }
    }
}

function validateDate(date,option){
        if(option === 'over'){
            if(date === 'from'){
                $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'fromdate');
                $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'todate');
            }
            if(date === 'to'){
                $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'fromdate');
                $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'todate');
            }           
            $("#btnDownloadAP").addClass("disabled");
        } else {
            $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'fromdate');
            $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'todate');
            $("#btnDownloadAP").addClass("disabled");
        }
    }
