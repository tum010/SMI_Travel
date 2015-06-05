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


function setupproductvalue(id,code,name){
    $('#ProductModal').modal('hide');
    document.getElementById('Product_id').value = id;
    document.getElementById('Product_code').value = code;
    document.getElementById('Product_name').value = name;
    getvalueProduct();
}

function getvalueProduct() {
    var servletName = 'BookLandServlet';
    var servicesName = 'AJAXBean';
    var productid = document.getElementById('Product_id').value;
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&packageid=' + productid +
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
                document.getElementById('AD_Cost').value = numberWithCommas(path[0]);
                document.getElementById('CH_Cost').value = numberWithCommas(path[1]);
                document.getElementById('IN_Cost').value = numberWithCommas(path[2]);
                document.getElementById('AD_Price').value = numberWithCommas(path[3]);
                document.getElementById('CH_Price').value = numberWithCommas(path[4]);
                document.getElementById('IN_Price').value = numberWithCommas(path[5]);
                getItineraryDetail(document.getElementById('Product_id').value);
            }, error: function(msg) {
                
            }
        });
    } catch (e) {
       // alert(e);
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
                    
                    //alert($(this).find('td').html());
                    //alert($(this).find('td').find('input').val());
                    AllDelId += $(this).find('td').find('input').val()+',';
                    $(this).remove();
                });   
                //alert(AllDelId);
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
    });
    $("#agent_code").autocomplete({
        source: codeAgent
    });
    $("#agent_code").on('keyup', function () {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        $("#agent_id,#agent_name").val(null);
                 
       $.each(agent, function (key, value) {
           
            if (value.code.toUpperCase() === code) {
                
                $("#agent_id").val(value.id);
                $("#agent_name").val(value.name);
            }
        });  
        
       
    });
    
    $("#agent_code").on('blur', function () {
       var delay=500;//1 seconds
        setTimeout(function(){
          $.each(agent, function (key, value) {
             //alert(value.code);
            if($("#agent_code").val() == value.code){
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
$(document).ready(function () {

    codeProduct = [];
    $.each(product, function (key, value) {
        codeProduct.push(value.code);
    });

    $("#Product_code").autocomplete({
        source: codeProduct
    });
  
    $("#Product_code").on('keyup', function (event) {
        
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var code = this.value.toUpperCase();
        $("#Product_id,#Product_name").val(null);
        $.each(product, function (key, value) {    
            if (value.code.toUpperCase() === code) {
                $("#Product_id").val(value.id);
                $("#Product_name").val(value.name);
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

