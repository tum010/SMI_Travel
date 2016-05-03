/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
//    setDateFormat();
    $('.datemask').mask('00-00-0000');
    $('.date').datetimepicker();
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
    });
    $(".money").mask('000,000,000,000.00', {reverse: true});
    $(".number").mask('00000000000', {reverse: true});

    setSearchFormat($("#searchType").val());

    $('table.paginated').each(function() {
        var currentPage = 0;
        var numPerPage = 30;
        var $table = $(this);
        $table.bind('repaginate', function() {
            $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
        });
        $table.trigger('repaginate');
        var numRows = $table.find('tbody tr').length;
        var numPages = Math.ceil(numRows / numPerPage);
        var $pager = $('<div class="col-xs-12 text-right" id="pageNo"><font style="color: #499DD5"></font>&nbsp;</div>');
        var $br = $('<div class="col-xs-12"><br></div>');

        for (var page = 0; page < numPages; page++) {
            var isShowPage = (page < 5 ? "" : "hidden");
            if(page === 0){
                $('<font style="color: #499DD5" id="noFirst" onclick="changeColor(\'noFirst\',\'first\',\''+page+'\')"><span class="page-number glyphicon"></span></font>').text(" " + "First" + "  ").bind('click', {
                newPage: page
                }, function(event) {
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                    $(this).css("color", "#AFEEEE");
                }).appendTo($pager).addClass('clickable');                                      

                if(numPages > 1){
                    for(var i=0; i<numPages; i++){
                        var isHidden = (i === 0 ? "" : "hidden");
                        $('<font style="color: #499DD5" id="noPrevious'+i+'" onclick="changeColor(\'noPrevious'+i+'\',\'previous\',\''+i+'\')" class="'+isHidden+'"><span class="page-number glyphicon"></span></font>').text(" " + "Previous" + "  ").bind('click', {
                        newPage: i
                        }, function(event) {
                            currentPage = event.data['newPage'];
                            $table.trigger('repaginate');
                            $(this).addClass('active').siblings().removeClass('active');
    //                        $(this).css("color", "#AFEEEE");
                        }).appendTo($pager).addClass('clickable');
                    }
                }    
            }

            $('<font style="color: #499DD5" id="no' + page + '" onclick="changeColor(\'no'+page+'\',\'no\',\''+page+'\')" class="'+isShowPage+'"><span class="page-number glyphicon"></span></font>').text(" " + (page + 1) + "  ").bind('click', {
                newPage: page
            }, function(event) {                  
                currentPage = event.data['newPage'];
                $table.trigger('repaginate');
                $(this).addClass('active').siblings().removeClass('active');
                $(this).css("color", "#AFEEEE");
            }).appendTo($pager).addClass('clickable');

            if(page === (numPages - 1)){
                if(numPages > 1){
                    for(var i=0; i<numPages; i++){
                        var isHidden = (i === 1 ? "" : "hidden");
                        $('<font style="color: #499DD5" id="noNext'+i+'" onclick="changeColor(\'noNext'+i+'\',\'next\',\''+i+'\')" class="'+isHidden+'"><span class="page-number glyphicon"></span></font>').text(" " + "Next" + "  ").bind('click', {
                        newPage: i
                        }, function(event) {
                            currentPage = event.data['newPage'];
                            $table.trigger('repaginate');
                            $(this).addClass('active').siblings().removeClass('active');
    //                        $(this).css("color", "#AFEEEE");
                        }).appendTo($pager).addClass('clickable');
                    }
                }    

                $('<font style="color: #499DD5" id="noLast" onclick="changeColor(\'noLast\',\'last\',\''+page+'\')"><span class="page-number glyphicon"></span></font>').text(" " + "Last" + "  ").bind('click', {
                newPage: page
                }, function(event) {
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                    $(this).css("color", "#AFEEEE");
                }).appendTo($pager).addClass('clickable');                                     
            }
        }
        $br.insertAfter($table).addClass('active');
        $pager.insertAfter($table).find('span.page-number:first').addClass('active');
        document.getElementById("pageNo").style.cursor="pointer";
        document.getElementById("page").value = numPages-1;
        document.getElementById("currentPage").value = 0;
        $("#noFirst").css("color", "#AFEEEE");
        $("#no0").css("color", "#AFEEEE");
        $("#noPrevious0").css("color", "#AFEEEE");
    });

    $('#BookInformationSearch').bootstrapValidator({
        container: 'tooltip',
        excluded: [':disabled'],
        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            bookType: {
                validators: {
                    notEmpty: {
                        message: 'The Book Type is required'
                    }
                }
            }
        }
    });
    
    $("#bookRefNo,#bookLeader,#bookDate,#bookType,#airPnr,#airDeptDate,#airFlight,#hotelName,#hotelCheckIn,#hotelCheckOut,\n\
        #packageName,#packageAgent,#tourCode,#tourName,#tourDate,#tourPickup,#tourAgent,#otherCode,#otherName,#otherDate,#otherAgent,#landOkBy,\n\
            #landCategory,#landAgent").keyup(function (event) {
        if (event.keyCode === 13) {
            searchAction();
        }
    });
    
    if($("#searchType").val() === '2'){
        setEnvironment();
    }

});

function searchAction() {
    if($("#bookType").val() !== ''){
        document.getElementById("action").value = "search";
        document.getElementById('BookInformationSearch').submit();
    }else{
        $("#BookInformationSearch").bootstrapValidator('revalidateField', 'bookType');
    }   
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
    } else if (type === '2') {
        $("#searchHotel").removeClass("hidden");
    } else if (type === '3') {
        $("#searchPackageTour").removeClass("hidden");
    } else if (type === '4') {
        $("#searchDayTours").removeClass("hidden");
    } else if (type === '5') {
        $("#searchOther").removeClass("hidden");
    } else if (type === '6') {
        $("#searchLand").removeClass("hidden");
    }
}

function setSearchFormat(searchType) {
    $("#bookType").val(searchType);
    if (searchType === '1') {
        $("#searchAir").removeClass("hidden");
    } else if (searchType === '2') {
        $("#searchHotel").removeClass("hidden");
    } else if (searchType === '3') {
        $("#searchPackageTour").removeClass("hidden");
    } else if (searchType === '4') {
        $("#searchDayTours").removeClass("hidden");
    } else if (searchType === '5') {
        $("#searchOther").removeClass("hidden");
    } else if (searchType === '6') {
        $("#searchLand").removeClass("hidden");
    }
}

function viewBooking(type, refNo, id) {
//    alert(type+" "+refNo+" "+id);
    if (type === '1') {
        window.open("AirTicket.smi?action=edit&referenceNo=" + refNo);
    } else if (type === '2') {
        window.open("HotelDetail.smi?action=edit&referenceNo=" + refNo + "&id=" + id);
    } else if (type === '3') {
        window.open("BookDetail.smi?action=edit&referenceNo=" + refNo);
    } else if (type === '4') {
        window.open("DaytourDetail.smi?action=edit&referenceNo=" + refNo + "&daytourBooking=" + id);
    } else if (type === '5') {
        window.open("OtherDetail.smi?action=edit&referenceNo=" + refNo + "&itemid=" + id + "&callPageFrom=FromOther");
    } else if (type === '6') {
        window.open("LandDetail.smi?action=edit&referenceNo=" + refNo + "&landid=" + id);
    }
}

function changeColor(id,type,page){
    var pageNo = parseInt($("#page").val())+1;
    for(var i=0; i<pageNo; i++){
        $("#no"+i).css("color", "#499DD5");
        $("#noPrevious"+i).css("color", "#499DD5");
        $("#noNext"+i).css("color", "#499DD5");
        $("#noFirst").css("color", "#499DD5");                
        $("#noLast").css("color", "#499DD5");

        $("#no"+i).addClass("hidden");
        $("#noPrevious"+i).addClass("hidden");
        $("#noNext"+i).addClass("hidden");
    }

    var pageShow = parseInt(page);
    if(pageShow > 2 && pageShow < pageNo - 2){
        for(var i=pageShow; i >= pageShow-2; i--){
           $("#no"+i).removeClass("hidden"); 
        }
        for(var i=pageShow; i <= pageShow+2; i++){
           $("#no"+i).removeClass("hidden");  
        }

    }else{
        if(pageShow <= 2){
            for(var i=0; i < 5; i++){
                $("#no"+i).removeClass("hidden");  
            } 

        }else if(pageShow <= pageNo-1){
            for(var i=pageNo-5; i < pageNo; i++){
                $("#no"+i).removeClass("hidden");  
            } 
        }

    }    

    var previous = ((parseInt(page) === 0 ? 0 : parseInt(page)-1));
    $("#noPrevious"+(previous)).removeClass("hidden");

    var next = ((parseInt(page) === pageNo-1 ? pageNo-1 : parseInt(page)+1));
    $("#noNext"+(next)).removeClass("hidden");

    $("#no"+page).css("color", "#AFEEEE");

    if(parseInt(page) === 0){
        $("#noFirst").css("color", "#AFEEEE");
        $("#noPrevious"+(previous)).css("color", "#AFEEEE");

    }else if(parseInt(page) === pageNo-1){
        $("#noLast").css("color", "#AFEEEE");
        $("#noNext"+(next)).css("color", "#AFEEEE");
    }

    if(pageNo-1 === 0){
        $("#noFirst").css("color", "#499DD5");                
        $("#noLast").css("color", "#499DD5");

        if(type === 'first'){
            $("#noFirst").css("color", "#AFEEEE");

        }else if(type === 'last'){
            $("#noLast").css("color", "#AFEEEE");

        }
    }

    $("#currentPage").val(page);

}

function setDateFormat(){
    if($("#bookDate").val() !== ''){
        var date = $("#bookDate").val();
        $("#bookDate").val(convertFormatDate(date));
    }
    if($("#hotelCheckIn").val() !== ''){
        var date = $("#hotelCheckIn").val();
        $("#hotelCheckIn").val(convertFormatDate(date));
    }
    if($("#hotelCheckOut").val() !== ''){
        var date = $("#hotelCheckOut").val();
        $("#hotelCheckOut").val(convertFormatDate(date));
    }
    if($("#tourDate").val() !== ''){
        var date = $("#tourDate").val();
        $("#tourDate").val(convertFormatDate(date));
    }
    if($("#otherDate").val() !== ''){
        var date = $("#otherDate").val();
        $("#otherDate").val(convertFormatDate(date));
    }
}

