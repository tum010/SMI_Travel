function DelCommissionAgent(id,agentcode,agentname,tourcode,tourname) {
        $("#deleteId").val(id);
        $('#delContent').html("Are you sure to delete Commission of agent : "+agentcode+" and tour : "+tourcode+" ?");      
}
     

$(document).ready(function (){
//    $(".money").mask("000,000,000", {reverse: true});
   
    
    $(".decimal").inputmask(
        "decimal",{
            radixPoint:".", 
            groupSeparator: ",", 
            digits: 2,
            autoGroup: true
     });
    
    var table = $('#MasterCommissionAgentTable').DataTable({
       
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bInfo": true,
        "bSort": false
        
    });
    $('#MasterCommissionAgentTable tbody').on('click', 'tr', function () {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
            $('#hdGridSelected').val('');
        }
        else {
            table.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
            $('#hdGridSelected').val($('#MasterCommissionAgentTable tbody tr.row_selected').attr("id"));
        }
    });
});
    

