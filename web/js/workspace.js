/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function searchAction(){
    var action = document.getElementById('action');
    action.value = 'search';
    document.getElementById('BookSearch').submit();     
}

function getSummaryBook(refno) {
    var servletName = 'BookServlet';
    var servicesName = 'AJAXBean';
   
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&initialID=' + '1' +
            '&refno=' + refno +
            '&type=' + 'summaryBook';
    CallAjax(param);
}



function CallAjax(param) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                $("#TableBookSummary tbody").empty().append(msg);
                setformat();
            }, error: function (msg) {
                alert('error');
            }
        });
    } catch (e) {
        alert(e);
    }
}

$(document).ready(function () {
    // validate
   $("#refno").focus();
    $("#refno,#PassFirst,#PassLast,#Bookdate ").keyup(function (event) {
        if (event.keyCode === 13) {
            searchAction();
        }
    });



});