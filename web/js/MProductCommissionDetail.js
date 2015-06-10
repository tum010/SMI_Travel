/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function back() {
    //alert("OK");
    var action = document.getElementById('action');
    action.value = 'back';
    document.getElementById('MProductCommissionDetail').submit();
}

$(document).ready(function () {
    var bla = $('#saveText').val();

    if(bla == "Save"){
        
        $('#textAlertDivSave').show();
    }
});

$(document).ready(function () {
 
        $(".datemask").mask('00-00-0000', {reverse: true});
//        $(".decimal").mask('000,000,000.0', {reverse: true});

        //Add Blank row for user input.
        addRowCommissionTable();
        /*Auto Add lastrow */
        $(document).on('keydown', '#commissionTable tbody tr:last td  input', function (e) {
            addRowCommissionTable();
        });
    });
    
    function validateCheckInput() {
        var isValid = true;
        var trList = $('#commissionTable tbody tr');
        trList.each(function (i,tr) {
           if(i==0 || i==(trList.length-1)){
               return;
           }
           var d1Str = $(tr).find("input[name^='InputFrom']").val();
           var d2Str = $(tr).find("input[name^='InputTo']").val();
           var commissionPercent = $(tr).find("input[name^='InputCommission']").val();
           
           if(validateCommissionPercent(commissionPercent)){
               jQuery("#AlertCheckCommissionPercent").css("display","none");
            }else{
                jQuery("#AlertCheckCommissionPercent").css("display","block");
                isValid = false;
            }
           if( !(isDate(d1Str) && isDate(d2Str)) ){
               isValid = false;
               jQuery("#AlertCheckDate").css("display","block");

           }else{
               var d1Arr = d1Str.split("-");
               var d2Arr = d2Str.split("-");
               var d1 = Date.parse(d1Arr[2]+"-"+d1Arr[1]+"-"+d1Arr[0]);
               var d2 = Date.parse(d2Arr[2]+"-"+d2Arr[1]+"-"+d2Arr[0]);
               if (d1 > d2) {
                  isValid = false;
                  jQuery("#AlertCheckDate").css("display","block");
               }
           }
        });
//        alert(isValid + "Save");
          jQuery("#textAlertDivSave").css("display","block");
        return isValid;
    }
    
    function validateCommissionPercent(s) {
        return s.match(/^(100(\.0{1,2})?|[1-9]?\d(\.\d{1,2})?)$/) != null;
    }
    
    function closePopupDate(){
        //$("#AlertCheckDate").hide();
       return  jQuery("#AlertCheckDate").css("display","none");
    }
    
    function closePopupCommission(){
        //$("#AlertCheckCommissionPercent").hide();
        return jQuery("#AlertCheckCommissionPercent").css("display","none");
    }
    
    function isDate(txtDate)
    {
        var currVal = txtDate;
        if (currVal == '')
            return false;

        var rxDatePattern = /^(\d{1,2})(\-|-)(\d{1,2})(\-|-)(\d{4})$/; //Declare Regex
        var dtArray = currVal.match(rxDatePattern); // is format OK?

        if (dtArray == null)
            return false;

        //Checks for DD-MM-YYYY
        dtMonth = dtArray[3];
        dtDay = dtArray[1];
        dtYear = dtArray[5];

        if (dtMonth < 1 || dtMonth > 12)
            return false;
        else if (dtDay < 1 || dtDay > 31)
            return false;
        else if ((dtMonth == 4 || dtMonth == 6 || dtMonth == 9 || dtMonth == 11) && dtDay == 31)
            return false;
        else if (dtMonth == 2)
        {
            var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
            if (dtDay > 29 || (dtDay == 29 && !isleap))
                return false;
        }
        return true;
    }

    $(document).ready(function () {
         
        $(".datetime").datetimepicker({
                pickTime: false   
        });
        
        $(".datemask").mask('00-00-0000', {reverse: true});
        $(".decimal").inputmask({
            alias:"decimal",
            integerDigits:6,
            groupSeparator: ',', 
            autoGroup: false,
            digits:2,
            allowMinus:false,        
            digitsOptional: false,
            placeholder: "0"
        }); 
        
        $('.datetimefrom').on("dp.change", function(e){
            $('.datetimeto').minDate(e.date);
        });

        $('.datetimeto').on("dp.change", function(e){
            $('.datetimefrom').data("DateTimePicker").maxDate(e.date);
        });
        $(document).on('click', '#commissionTable tbody tr:last td  input ,#commissionTable tbody tr:last td .input-group-addon', function (e) { // .input-group-addon, .datemask
        });
    });
    
  
    function DeleteCommissionRow(id, objspan) {
    var countCommission = $("#commissionTable tbody").find("tr").length;
        if ($("#commissionTable tbody").find("tr").length !== 2) {
            if (id !== null) {
                $('#delContent').html(" Are you sure to delete : Row at Number "+ ($(objspan).parent().parent().index()-1) +" ? " );  

                    console.log('else len1');
                    $('#btnDelete').click(function () {
                        sendDataToDelete(id);
                    });
                
            } else {
                $(objspan).closest('tr').remove();
                console.log("counterCommission=" + countCommission);
                $('#counterCommission').val(countCommission - 1);
            }
        } else {
            if (id !== null) {
                $('#delContent').html(" Are you sure to delete : Row at Number "+ ($(objspan).parent().parent().index()-1) +" ? " );
                    console.log('else len2');
                    $('#btnDelete').click(function () {
                        sendDataToDelete(id);
                    });

            } else {
                alert('this row for fill data');
            }
        }
    }
    
    function addRowCommissionTable() {
        var counter = $('#commissionTable tbody tr').length;
        var clone = $('#commissionTable tbody tr:first').clone();
        clone.removeClass("hide");
        clone.find('div,input,span').each(function () {
            console.log('count :'+counter);
            $(this).attr({
                id: $(this).attr('id') + counter,
                name: $(this).attr('name') + counter
            });
            $(".datetime").datetimepicker({
                pickTime: false   
            });

            $('.decimal').inputmask({
                alias:"decimal",
                integerDigits:6,
                groupSeparator: ',', 
                autoGroup: true,
                digits:2,
                allowMinus:false,        
                digitsOptional: false,
                placeholder: "0"
            }); 
            $("#counterCommission").val(counter + 1);
        });
        $('#commissionTable tbody').append(clone);
    }