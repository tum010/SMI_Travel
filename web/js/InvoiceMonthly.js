/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function printInvoiceMonthly(){
//    validateInvoiceMonthly();
    var ClientTo = $("#clientCode").val();
    var ClientName = $("#clientName").val();   
    var vattype  = $("#vatType").val();
    var from  = convertFormatDate($("#fromdate").val());
    var to  = convertFormatDate($("#todate").val());
    var departmentInvoice = $('#departmentInvoice').val();
    
    var billingAttn = $("#billingAttn").val();
    var billingFrom = $("#billingFrom").val();
    var billingTel = $("#billingTel").val();
    var billingFax = $("#billingFax").val();
    var billingMail = $("#billingMail").val();
    var billingDate = convertFormatDate($("#billingDate").val());
    var remittanceTo  = $("#remittanceTo").val();      
    
    if((from === '') || (to === '')){
        validateDate();
    } else {
        window.open("report.smi?name=InvoiceMonthlyReport&clientCode="+ClientTo+"&clientName="+ClientName+"&accNo="+remittanceTo+"&vatType="+vattype+"&fromdate="+from+"&todate="+to+"&departmentInvoice="+departmentInvoice+"&billingAttn="+billingAttn+"&billingFrom="+billingFrom+"&billingTel="+billingTel+"&billingFax="+billingFax+"&billingMail="+billingMail+"&billingDate="+billingDate);   
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