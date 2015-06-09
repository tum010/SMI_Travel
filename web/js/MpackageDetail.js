/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ConfirmDelete(rowType, itineraryid, Ccount) {
    $("#rowType").val(rowType);
    $("#Itiid").val(itineraryid);
    $("#cCount").val(Ccount);
    var deleteType;
    if (rowType === '1') {
        deleteType = 'itinerary ?';
    }else if (rowType === '2') {
        deleteType = 'price ?';
    } 
    $("#delCode").text('are you sure delete ' + deleteType);
    $('#DeletePackage').modal('show');

}

function DeleteRow() {
    var rowType = $("#rowType").val();
    var ItiId = $("#Itiid").val();
    var cCount = $("#cCount").val();

    if (rowType === '1') {
        deleteItinerary(ItiId,cCount);
    }else if (rowType === '2') {
        deletePrice(ItiId,cCount);
    } 
    $('#DeletePackage').modal('hide');
}

function deleteItinerary(ItiId,count){
    if(ItiId == ''){
                 var countrow=0;
            $("#row-" + count + "-no").parent().parent().remove();
            var rowAll = $("#Itinerary tr").length;
            if (rowAll < 2) {
                console.log("show button tr_FormulaAddRow");
                $("#tr_ItineraryAddRow").removeClass("hide");
                $("#tr_ItineraryAddRow").addClass("show");
            }            
     //       $("#counterItinerary").val(parseInt($("#counterItinerary").val()) -1);
            $('#Itinerary tr:gt(0) ').each(function() {
                $(this).find('td:eq(1)').html(countrow) ; 
                countrow = countrow+1;
            });   
    }else{
     $.ajax({
        url: 'MPackageDetail.smi?action=deleterItinerary',
        type: 'get',
        data: {ItiID: ItiId},
        success: function () {
            
            var countrow=0;
            $("#row-" + count + "-no").parent().parent().remove();
            var rowAll = $("#Itinerary tr").length;
            if (rowAll < 2) {
                console.log("show button tr_FormulaAddRow");
                $("#tr_ItineraryAddRow").removeClass("hide");
                $("#tr_ItineraryAddRow").addClass("show");
            }            
     //       $("#counterItinerary").val(parseInt($("#counterItinerary").val()) -1);
            $('#Itinerary tr:gt(0) ').each(function() {
                $(this).find('td:eq(1)').html(countrow) ; 
                countrow = countrow+1;
            });   
          
        },
        error: function () {
            console.log("error");
            result =0;
        }
    });       
    }

    
}

function getrowcountItinerary(){
    var count =0;
     $('#Itinerary tr:gt(0) ').each(function() {
                count = count+1;
    });  
    return count;        
}

function getrowcountPrice(){
    var count =0;
     $('#PackagePrice tr:gt(1)').each(function() {
                count = count+1;
    });  
    return count;        
}


function deletePrice(priceId,count){
    if(priceId == ''){
        var countrow =0;
            $("#row-" + count + "-datefrom").parent().parent().remove();
            var rowAll = $("#PackagePrice tr").length;
            if (rowAll <= 2) {
                $("#tr_PackagePriceAddRow").removeClass("hide");
                $("#tr_PackagePriceAddRow").addClass("show");
            }      
            $('#PackagePrice tr:gt(1) ').each(function() {
              
                $(this).find('td:eq(1)').html(countrow) ; 
                countrow = countrow+1;
            });    
    }else{
        $.ajax({
        url: 'MPackageDetail.smi?action=deleterPrice',
        type: 'get',
        data: {priceId: priceId},
        success: function () {
           var countrow =0;
            $("#row-" + count + "-datefrom").parent().parent().remove();
            var rowAll = $("#PackagePrice tr").length;
            if (rowAll <= 2) {
                $("#tr_PackagePriceAddRow").removeClass("hide");
                $("#tr_PackagePriceAddRow").addClass("show");
            }      
            $('#PackagePrice tr:gt(1) ').each(function() {
             
                $(this).find('td:eq(1)').html(countrow) ; 
                countrow = countrow+1;
            });    
        },
        error: function () {
            console.log("error");
            result =0;
        }
    });
    }
    
    
}

function DeleteRowPrice(count){
    var countrow =0;
     $("#row-" + count + "-datefrom").parent().parent().remove();
            var rowAll = $("#PackagePrice tr").length;
            if (rowAll <= 2) {
                $("#tr_PackagePriceAddRow").removeClass("hide");
                $("#tr_PackagePriceAddRow").addClass("show");
            }      
            $("#counterPrice").val(parseInt($("#counterPrice").val()) -1);
      
     $('#PackagePrice tr:gt(1) ').each(function() {
         $(this).find('td:eq(1)').html(countrow) ; 
         countrow = countrow+1;
     });    
            
}


$(document).ready(function () {

    try{
 $('#PackageForm').bootstrapValidator({
        container: 'tooltip',

        feedbackIcons: {
            valid: 'uk-icon-check',
            invalid: 'uk-icon-times',
            validating: 'uk-icon-refresh'
        },
        fields: {
            packagecode: {
                validators: {
                    notEmpty: {    
                        message: 'The package code is required'  
                    },
                    regexp: {
                        regexp: validatcode,
                        message: 'The package code contains illegal characters.'
                    }
                   
                }
            },
            packagename: {
                validators: {
                    notEmpty: {   
                        message: 'The package name is required'  
                    },
                    regexp: {
                        regexp: validatename,
                        message: 'The package name contains illegal characters.'
                    }
                   
                }
            }
          
        }
    }).on('success.field.bv', function (e, data) {
        if (data.bv.isValid()) {
           // data.bv.disableSubmitButtons(false);    
        }
    });       
    }catch(e){
      //  alert(e);
    }
    
    
    $('#PackageForm').on('submit', function(e) {

        if (validateDate()){
            
        }else{
            e.preventDefault();
            $("#savePackage").prop('disabled', false);
            return false;
        }
    });


});

function setValidate(){
   
     $('#checkValidate').val('');
}

function validateDate(){
    var result = '';
    var row =0;
    $('#PackagePrice  tbody   tr').each(function() {
        //alert($(this).find('#row-'+row+'-datefrom').val() +'    _     '+$(this).find('#row-'+row+'-dateto').val());
       
        if(IsStartDateOver($(this).find('#row-'+row+'-datefrom').val(),$(this).find('#row-'+row+'-dateto').val())){
            $(this).find('#row-'+row+'-datefrom').addClass('input-success');
            $(this).find('#row-'+row+'-dateto').addClass('input-success');
        }else{
            result += 'row '+(row+1) +' : effective date from  is more than effective date to';
            result +='\n';
            $(this).find('#row-'+row+'-datefrom').addClass('input-error');
            $(this).find('#row-'+row+'-dateto').addClass('input-error');
        }
        row++;
        
        
    });
    if(result != ''){
        if( $('#checkValidate').val()==''){
            alert(result);
            $('#checkValidate').val(1);
        }
        return false;
    }else{
        $("#savePackage").prop('disabled', false);
        return true;
    }
    
}

function IsStartDateOver(date1S, date2S){
   // Convert both dates to milliseconds
   if((date1S == '')&&(date2S == '')){
       return true;
   }else if((date1S == undefined )&&(date2S == undefined)){
       return true;
   }
   try{
        var path1 = date1S.split('-');
        var path2 = date2S.split('-');
  
        var date1  = new Date(path1[0],path1[1],path1[2]);
        var date2  = new Date(path2[0],path2[1],path2[2]);
  
        var date1_ms = date1.getTime();
        var date2_ms = date2.getTime();

        // Calculate the difference in milliseconds
        var difference_ms = date2_ms - date1_ms;
  
        if(difference_ms >= 0){
           
            return true;
        }else{
            return false;
        }       
   }catch(e){
       alert(e);
   }

}

// City 

$(document).ready(function () {
    PassengerAddRow((parseInt($("#table-passenger-size").val()) + 1));
    $("#City").on('click', '.newRemCF', function () {
        $(this).parent().parent().remove();
        var rowAll = $("#City tr").length;
//        console.log("rowAll : " + rowAll);
        if (rowAll < 2) {
            console.log("show button");
            $("#tr_PassengerAddRow").removeClass("hide");
            $("#tr_PassengerAddRow").addClass("show");
        }
    });
    $("#tr_PassengerAddRow a").click(function () {
        $(this).parent().removeClass("show");
        $(this).parent().addClass("hide");
    });
    $("#City").on("change", "select:last", function () {
        console.log('on change');
        var rowAll = $("#City tr").length;
        PassengerAddRow(rowAll);
    });
});

function PassengerAddRow(row) {

    if (!row) {
        row = 1;
    }

    $("#City tbody").append(
            '<tr>' +
            '<td  hidden=""><input  id="row-passenger-' + row + '-id" name="row-passenger-' + row + '-id"  type="text" class="form-control"></td>' +
            '<td>' + row + '</td>' +
            '<td><select id="row-passenger-' + row + '-name" name="row-passenger-' + row + '-name" class="form-control"><option></option></select></td>' +
            '<td class="text-center">' +
            '<a class="newRemCF" id="ButtonPassengerRemove'+row+'"><span id="SpanPassengerRemove'+row+'" class="glyphicon glyphicon-remove deleteicon"></span></a></td>' +
            '</tr>'
            );
    $("#select-list-passenger option").clone().appendTo("#row-passenger-" + row + "-name");
    var tempCount = parseInt($("#passengerCounter").val()) + 1;
    $("#passengerCounter").val(tempCount);
}

function deletePassenger(hotelId, passengerId) {
    console.log(hotelId + ":" + passengerId);
    $.ajax({
        url: 'HotelDetail.smi?action=deletePassenger',
        type: 'get',
        data: {hId: hotelId, pId: passengerId},
        success: function () {
            console.log('success');
        },
        error: function () {
            console.log("error");
        }
    });
}

        

