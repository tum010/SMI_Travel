/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateMaxCost(id){
    //alert(id);
    var data = $('#'+id).val();
    data = data.replace(/,/g, '');
    //alert(data);
    if(data.length > 8){
        data = data.substring(0,7);
    }
    $('#'+id).val(data);
}
function setupagentvalue(id,code,name){
    $('#AgentModal').modal('hide');
    document.getElementById('agent_id').value = id;
    document.getElementById('agent_code').value = code;
    document.getElementById('agent_name').value = name;
    document.getElementById('agent_code').focus();
    $('#landForm').bootstrapValidator('revalidateField', 'agent_code');
}

function setupdepartdatevalue(){
    var Product_code = document.getElementById('Product_code').value; 
    var departdate = document.getElementById('departdate').value; 
    if((Product_code !== '') && (departdate !== '')){
        getvalueProduct('departdate'); 
    } else {
        document.getElementById('AD_Cost').value = '0';
        document.getElementById('AD_Price').value = '0';
        document.getElementById('CH_Cost').value = '0';
        document.getElementById('CH_Price').value = '0';
        document.getElementById('IN_Cost').value = '0';
        document.getElementById('IN_Price').value = '0';
    }
}

function setupproductvalue(id,code,name){
    $('#ProductModal').modal('hide');
    document.getElementById('Product_id').value = id;
    document.getElementById('Product_code').value = code;
    document.getElementById('Product_name').value = name;
    var Product_code = document.getElementById('Product_code').value; 
    var departdate = document.getElementById('departdate').value;
    if((Product_code != '') && (departdate != '')){
        getvalueProduct('package'); 
    } else {
        document.getElementById('AD_Cost').value = '0';
        document.getElementById('AD_Price').value = '0';
        document.getElementById('CH_Cost').value = '0';
        document.getElementById('CH_Price').value = '0';
        document.getElementById('IN_Cost').value = '0';
        document.getElementById('IN_Price').value = '0';
    }
}

function getvalueProduct(order) {
    var productid = document.getElementById('Product_id').value;
    var departdate = document.getElementById('departdate').value;
    var todaydate = document.getElementById('todaydate').value; 
    var checkdate = document.getElementById('checkdate').value;
    if((productid !== '') && (departdate !== '')){
        if((order === 'package')){
            var servletName = 'BookLandServlet';
            var servicesName = 'AJAXBean';
            var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&packageid=' + productid +
                        '&departdate='+departdate +
                        '&type=' + 'getvaluePackage';
            CallAjax(param);
        }else if(checkdate !== ''){
            var servletName = 'BookLandServlet';
            var servicesName = 'AJAXBean';
            var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&packageid=' + productid +
                        '&departdate='+departdate +
                        '&type=' + 'getvaluePackage';
            CallAjax(param);
        }else if(departdate !== todaydate){
            var servletName = 'BookLandServlet';
            var servicesName = 'AJAXBean';
            var param = 'action=' + 'text' +
                        '&servletName=' + servletName +
                        '&servicesName=' + servicesName +
                        '&packageid=' + productid +
                        '&departdate='+departdate +
                        '&type=' + 'getvaluePackage';
            CallAjax(param);
        }
    }  
}


function CallAjax(param) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                var path = msg.split(',');
                var AD_Cost = document.getElementById('AD_Cost').value; 
                var CH_Cost = document.getElementById('CH_Cost').value;
                var IN_Cost = document.getElementById('IN_Cost').value;
                var AD_Price = document.getElementById('AD_Price').value; 
                var CH_Price = document.getElementById('CH_Price').value;
                var IN_Price = document.getElementById('IN_Price').value;
                
                var AD_CostRP = AD_Cost.replace(',','');
                var CH_CostRP = CH_Cost.replace(',','');
                var IN_CostRP = IN_Cost.replace(',','');
                var AD_PriceRP = AD_Price.replace(',','');
                var CH_PriceRP = CH_Price.replace(',','');
                var IN_PriceRP = IN_Price.replace(',',''); 
                
                var path0 = (path[0] !== 'null' ? path[0] : '0');
                var path1 = (path[1] !== 'null' ? path[1] : '0');
                var path2 = (path[2] !== 'null' ? path[2] : '0');
                var path3 = (path[3] !== 'null' ? path[3] : '0');
                var path4 = (path[4] !== 'null' ? path[4] : '0');
                var path5 = (path[5] !== 'null' ? path[5] : '0');
                
                if(AD_CostRP === ''){ AD_CostRP = '0'; }
                if(AD_PriceRP === ''){ AD_PriceRP = '0'; }
                if(CH_CostRP === ''){ CH_CostRP = '0'; }
                if(CH_PriceRP === ''){ CH_PriceRP = '0'; }
                if(IN_CostRP === ''){IN_CostRP = '0'; }
                if(IN_PriceRP === ''){IN_PriceRP = '0'; }
                
                if((AD_CostRP === '0') && (CH_CostRP === '0') && (IN_CostRP === '0') && (AD_PriceRP === '0') && (CH_PriceRP === '0') && (IN_PriceRP === '0')){
                    setformatNumber('AD_Cost',path0);
                    setformatNumber('CH_Cost',path1);
                    setformatNumber('IN_Cost',path2);
                    setformatNumber('AD_Price',path3);
                    setformatNumber('CH_Price',path4);
                    setformatNumber('IN_Price',path5);
                    
                }else if((AD_CostRP === path0) && (CH_CostRP === path1) && (IN_CostRP === path2) && (AD_PriceRP === path3) && (CH_PriceRP === path4) && (IN_PriceRP === path5)){
                    
                } else {
                    document.getElementById('path0').value = path0;
                    document.getElementById('path1').value = path1;
                    document.getElementById('path2').value = path2;
                    document.getElementById('path3').value = path3;
                    document.getElementById('path4').value = path4;
                    document.getElementById('path5').value = path5;
                    $('#Confirm').modal('show');
                }                 
                
                getItineraryDetail(document.getElementById('Product_id').value);
            }, error: function(msg) {
                
            }
        });
    } catch (e) {
       // alert(e);
    }
}

function Confirm(){
    var path0 = document.getElementById('path0').value;
    var path1 = document.getElementById('path1').value; 
    var path2 = document.getElementById('path2').value; 
    var path3 = document.getElementById('path3').value; 
    var path4 = document.getElementById('path4').value; 
    var path5 = document.getElementById('path5').value; 
    setformatNumber('AD_Cost',path0);
    setformatNumber('CH_Cost',path1);
    setformatNumber('IN_Cost',path2);
    setformatNumber('AD_Price',path3);
    setformatNumber('CH_Price',path4);
    setformatNumber('IN_Price',path5);           
    $('#Confirm').modal('hide');
}

function setformatNumber(id,data){
    if(data == 0){
        document.getElementById(id).value = '0';
    }else{
        document.getElementById(id).value = numberWithCommas(data);
    }
}

function getItineraryDetail(productid) {
    
    var servletName = 'BookLandServlet';
    var servicesName = 'AJAXBean';
    var productid = productid;
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&packageid=' + productid +
            '&type=' + 'getListItinerary';
    
    CallAjaxIti(param);

}



function CallAjaxIti(param) {
    var url = 'AJAXServlet';
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                //alert(msg);
               if(msg != ''){
                //$('#landForm').bootstrapValidator('revalidateField', 'agent_code');
                var AllDelId =  $('#DelItenarary').val();

                $('#LandItinerary tr:gt(0) ').each(function() {
                    AllDelId += $(this).find('td').find('input').val()+',';
                    $(this).remove();
                });   
                $('#DelItenarary').val(AllDelId);
                
                
                    var path = msg.split('*=');
                    for(var i=0;i<path.length;i++){
                        var detail = path[i].split('&=');
                        
                        AddRowFromValue(detail[0],detail[1],detail[2]);
                    }
                    var rowAll = $("#LandItinerary tr").length;
                        if (rowAll < 2) {
                            $("#tr_ItineraryAddRow").removeClass("hide");
                            $("#tr_ItineraryAddRow").addClass("show");
                    }
                    AddRowFromValue('','','');
                }
               
                

            }, error: function(msg) {
             
            }
        });
    } catch (e) {
       // alert(e);
    }
}

$(document).ready(function () {
       
    var codeAgent = [];
    $.each(agent, function (key, value) {
        codeAgent.push(value.code);
        if ( !(value.name in codeAgent) ){
           codeAgent.push(value.name);
          
        }
    });
    $("#agent_code").autocomplete({
        source: codeAgent,
        close:function( event, ui ) {
           $("#agent_code").trigger('keyup');
        }
    });
    $("#agent_code").on('keyup', function () {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value;
        $("#agent_id,#agent_name").val(null);
                 
       $.each(agent, function (key, value) {
            if (value.code.toUpperCase() === code && code.length > 1) {           
                $("#agent_id").val(value.id);
                $("#agent_name").val(value.name);
            }
            if(name === value.name && name.length > 1){
                $("#agent_code").val(value.code);
                code = $("#agent_code").val().toUpperCase();
            }
        });  
        
       
    });
    
    $("#agent_code").on('blur', function () {
       var delay=500;//1 seconds
        setTimeout(function(){
          $.each(agent, function (key, value) {
             //alert(value.code);
            if($("#agent_code").val() === value.code){
                $("#agent_id").val(value.id);
                $("#agent_name").val(value.name);
            }     
         });   
       
        },delay); 
       
    });
    
    $("#agent_code").on('keydown', function () {
      // $('#landForm').bootstrapValidator('revalidateField', 'agent_code');
    });
    
});


function getAgent(){

     $.each(agent, function (key, value) {
      
            if($("#agent_code").val() == value.code){
                 $("#agent_id").val(value.id);
                $("#agent_name").val(value.name);
            }     
         });  
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
    
$(document).ready(function () {

    codeProduct = [];
    $.each(product, function (key, value) {
        codeProduct.push(value.code);
         if ( !(value.name in codeProduct) ){
           codeProduct.push(value.name);
          
        }
    });

    $("#Product_code").autocomplete({
        source: codeProduct,
        close:function( event, ui ) {
           $("#Product_code").trigger('keyup');
        }
    });
  
    $("#Product_code").on('keyup', function (event) {
        
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        var name = this.value;
        $("#Product_id,#Product_name").val(null);
        
        $.each(product, function (key, value) {    
            if (value.code.toUpperCase() === code) {
                $("#Product_id").val(value.id);
                $("#Product_name").val(value.name);
            }
            if(name === value.name){
                $("#Product_code").val(value.code);
                code = $("#Product_code").val().toUpperCase();
            }
        });
        
        var code = event.keyCode || event.which; 

        if (code  == 13) { 
           setupproductvalue($('#Product_id').val(), $('#Product_code').val(), $('#Product_name').val());
        }
        
    });
    
    $("#Product_code").on('blur', function () {
       var delay=500;//1 seconds
        setTimeout(function(){
          $.each(product, function (key, value) {
            if($("#Product_code").val() == value.code){
                $("#Product_id").val(value.id);
                $("#Product_name").val(value.name);
                setupproductvalue($('#Product_id').val(), $('#Product_code').val(), $('#Product_name').val());
            }     
         });   
       
        },delay); 
       
    });
    
    $("#Product_name").on('keyup',  function(e) { 
        var keyCode  = e.keyCode || e.which; 
        
        if (keyCode == 9) { 
            if($('#Product_code').val() != ''){
                setupproductvalue($('#Product_id').val(),$('#Product_code').val(),$('#Product_name').val());
            }
            
        }      
    });

});

