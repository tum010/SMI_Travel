
$(document).ready(function () {
    //Number
    $(".money").mask('000,000,000', {reverse: true});
    
    $('.spandate').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css({
                top: position.top + 30
            });
    });
   
});

function printTransferJob() {
     if($("#InputDocument").val() === ""){
           $("#ButtonPrint").disableSelection();
         
     }else{
         var inputDocument = document.getElementById("InputDocument").value;
         window.open("report.smi?name=TransferJob&docno=" + inputDocument);
     }
    
}


