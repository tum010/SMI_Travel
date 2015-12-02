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
    $(".money").mask('000,000,000,000.00', {reverse: true});

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
            if (page === 0) {
                $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + "First" + "  ").bind('click', {
                    newPage: page
                }, function(event) {
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                }).appendTo($pager).addClass('clickable');
            }

            $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + (page + 1) + " ").bind('click', {
                newPage: page
            }, function(event) {
                currentPage = event.data['newPage'];
                $table.trigger('repaginate');
                $(this).addClass('active').siblings().removeClass('active');
            }).appendTo($pager).addClass('clickable');

            if (page === (numPages - 1)) {
                $('<font style="color: #499DD5"><span class="page-number glyphicon"></span></font>').text(" " + "Last" + "  ").bind('click', {
                    newPage: page
                }, function(event) {
                    currentPage = event.data['newPage'];
                    $table.trigger('repaginate');
                    $(this).addClass('active').siblings().removeClass('active');
                }).appendTo($pager).addClass('clickable');
            }
        }
        $br.insertAfter($table).addClass('active');
        $pager.insertAfter($table).find('span.page-number:first').addClass('active');
        document.getElementById("pageNo").style.cursor = "pointer";
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
        #packageName,#packageAgent,#tourCode,#tourName,#tourDate,#tourPickup,#otherCode,#otherName,#otherDate,#otherAgent,#landOkBy,\n\
            #landCategory,#landAgent").keyup(function (event) {
        if (event.keyCode === 13) {
            searchAction();
        }
    });

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

