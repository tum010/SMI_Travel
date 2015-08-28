/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('.date').datetimepicker();
    $('span').click(function () {
        var position = $(this).offset();
        console.log("positon :"+position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });
});

function printInvoiceMonthly(){
   window.open("report.smi?name="+typePrint+"&invoiceid="+invoiceId+"&bankid="+payment+"&showstaff="+sale+"&showleader="+leader+"");   
}