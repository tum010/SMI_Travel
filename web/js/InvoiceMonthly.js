/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function printInvoiceMonthly(){
//    validateInvoiceMonthly();
    var BillFrom = $("#billFromName").val();
    var ClientTo = $("#clientCode").val();
    var ClientName = $("#clientName").val();
    var Payment  = $("#payment").val();
    var Accno  = $("#accNo").val();
    var vattype  = $("#vatType").val();
    var from  = $("#fromdate").val();
    var to  = $("#todate").val();
    var departmentInvoice = $('#departmentInvoice').val();
    if((from === '') || (to === '')){
        validateDate();
    } else {
        window.open("report.smi?name=InvoiceMonthlyReport&billFromName="+BillFrom+"&clientCode="+ClientTo+"&clientName="+ClientName+"&payment="+Payment+"&accNo="+Accno+"&vatType="+vattype+"&fromdate="+from+"&todate="+to+"&departmentInvoice="+departmentInvoice);   
    }
}

function validateDate(date,option){
        if(option === 'over'){
            if(date === 'from'){
                $('#InvoiceMonthlyFrom').bootstrapValidator('revalidateField', 'fromdate');
                $('#InvoiceMonthlyFrom').bootstrapValidator('revalidateField', 'todate');
            }
            if(date === 'to'){
                $('#InvoiceMonthlyFrom').bootstrapValidator('revalidateField', 'fromdate');
                $('#InvoiceMonthlyFrom').bootstrapValidator('revalidateField', 'todate');
            }           
            $("#btnDownloadAP").addClass("disabled");
        } else {
            $('#InvoiceMonthlyFrom').bootstrapValidator('revalidateField', 'fromdate');
            $('#InvoiceMonthlyFrom').bootstrapValidator('revalidateField', 'todate');
            $("#btnDownloadAP").addClass("disabled");
        }
    }