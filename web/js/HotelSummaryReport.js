/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function printHotelSummary(){
    var from  = $("#fromdate").val();
    var to  = $("#todate").val();
    var department = $('#department').val();
    if((from === '') || (to === '')){
        validateDate();
    } else {
        window.open("report.smi?name=HotelSummaryReport&fromdate="+from+"&todate="+to+"&department="+department);   
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

