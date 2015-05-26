/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchAction() {
    $("#DaytourSearch").submit();
}

function DeleteDaytour(id, code,name) {
    $("#deleteId").val(id);
    $('#delCode').html("Are you sure to delete Daytour " + code + " ?");
}

$(document).ready(function () {

    var table = $('#MDaytour').DataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": false,
        "bInfo": true,
        "bSort": false
        
    });
    $('#MDaytour tbody').on('click', 'tr', function () {
        if ($(this).hasClass('row_selected')) {
            $(this).removeClass('row_selected');
            $('#hdGridSelected').val('');
        }
        else {
            table.$('tr.row_selected').removeClass('row_selected');
            $(this).addClass('row_selected');
            $('#hdGridSelected').val($('#MDaytour tbody tr.row_selected').attr("id"));
        }
    });

});

function savestaff(){
           
            var servletName = 'DaytourServlet';
            var servicesName = 'AJAXBean';
            var stafftour = document.getElementById('StaffTourName').value;
            var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&name=' + stafftour +
                '&type=' + 'savestaff';
            if(stafftour == ''){
                if(confirm('Staff tour is empty. Are you sure to save?')){
                    callAjaxSaveStaff(param);
                }
            }else{
                callAjaxSaveStaff(param);
            }
        }
        
        function callAjaxSaveStaff(param){
            var url = 'AJAXServlet';
                $.ajax({
                    type: "POST",
                    url: url,
                    cache: false,
                    data: param,
                    success: function(msg) {
                      
                        if(msg =='success'){
                            alert('save successful');
                        }else{
                            alert('save unsuccessful');
                        }
                    }, error: function(msg) {
                       alert('save unsuccessful');
                    }
                });
    
        }
