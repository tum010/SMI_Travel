/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('.date').datetimepicker();
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
});

function searchAction(){
    document.getElementById("action").value = "search";
    document.getElementById('BookInformationSearch').submit();
}

function searchFillter() {
    var type = document.getElementById("bookType").value;
    $("#searchAir").addClass("hidden");
    $("#searchHotel").addClass("hidden");
    $("#searchPackageTour").addClass("hidden");
    $("#searchDayTours").addClass("hidden");
    $("#searchOther").addClass("hidden");
    $("#searchLand").addClass("hidden");
    if (type === '1') {
        $("#searchAir").removeClass("hidden");
    } else if(type === '2'){
        $("#searchHotel").removeClass("hidden");
    } else if(type === '3'){
        $("#searchPackageTour").removeClass("hidden");
    } else if(type === '4'){
        $("#searchDayTours").removeClass("hidden");
    } else if(type === '5'){
        $("#searchOther").removeClass("hidden");
    } else if(type === '6'){
        $("#searchLand").removeClass("hidden");
    }
}

