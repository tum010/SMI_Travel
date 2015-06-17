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

function getvalueDepartDate(){
    var productid = document.getElementById('Product_id').value;
    var departdate = document.getElementById('departdate').value;
    if((productid == '') || (departdate == '')){
        
    } else {
        var result = confirm("Are you sure to update Cost and Price ?");
        if (result == true) {
            getvalueProduct();
        } else {
            
        }
    }
}

function setupproductvalue(id,code,name){
    $('#ProductModal').modal('hide');
    document.getElementById('Product_id').value = id;
    document.getElementById('Product_code').value = code;
    document.getElementById('Product_name').value = name;
    
    var productid = document.getElementById('Product_id').value;
    var departdate = document.getElementById('departdate').value;
    if((productid == '') || (departdate == '')){
        getvalueProduct();
    } else {
        var result = confirm("Are you sure to update Cost and Price ?");
        if (result == true) {
            getvalueProduct();
        } else {
            
        }     
    }
}

function getvalueProduct() {
    var servletName = 'BookLandServlet';
    var servicesName = 'AJAXBean';
    var productid = document.getElementById('Product_id').value;
    var departdate = document.getElementById('departdate').value;
    var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&packageid=' + productid +
                '&departdate='+departdate +
                '&type=' + 'getvaluePackage';
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
            success: function(msg) {
                var path = msg.split(',');
                setformatNumber('AD_Cost',path[0]);
                setformatNumber('CH_Cost',path[1]);
                setformatNumber('IN_Cost',path[2]);
                setformatNumber('AD_Price',path[3]);
                setformatNumber('CH_Price',path[4]);
                setformatNumber('IN_Price',path[5]);
                getItineraryDetail(document.getElementById('Product_id').value);
            }, error: function(msg) {
                
            }
        });
    } catch (e) {
       // alert(e);
    }
}

function setformatNumber(id,data){
    if(data == 0){
        document.getElementById(id).value = '';
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
            if (value.code.toUpperCase() === code) {           
                $("#agent_id").val(value.id);
                $("#agent_name").val(value.name);
            }
            if(name === value.name){
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

