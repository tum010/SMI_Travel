/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    Selectize.define( 'clear_selection', function ( options ) {
        var self = this;
        self.plugins.settings.dropdown_header = {
            title: 'Clear Selection'
        };
        this.require( 'dropdown_header' );
        self.setup = (function () {
            var original = self.setup;
            return function () {
                original.apply( this, arguments );
                this.$dropdown.on( 'mousedown', '.selectize-dropdown-header', function ( e ) {
                    self.setValue( '' );
                    self.close();
                    self.blur();
                    return false;
                });
            };
        })();
    });

    var Country = "#SelectCountry";
    $(Country).selectize({
        removeItem: '',
        sortField: 'text' ,
        create: false ,
        dropdownParent: 'body',
        plugins: {
            'clear_selection': {}
        }
    });

    var City = "#SelectCity";
    $(City).selectize({
        removeItem: '',
        sortField: 'text' ,
        create: false ,
        dropdownParent: 'body',
        plugins: {
            'clear_selection': {}
        }
    });
    //Sale By Auto Complete
    $("#SaleByTable tr").on('click', function () {
        var saleby_id = $(this).find(".saleby-id").text();
        var saleby_user = $(this).find(".saleby-user").text();
        var saleby_name = $(this).find(".saleby-name").text();
        $("#salebyId").val(saleby_id);
        $("#salebyUser").val(saleby_user);
        $("#salebyName").val(saleby_name);
        $("#SaleByModal").modal('hide');
    });
        
    var salebyuser = [];
    $.each(saleby, function (key, value) {
        salebyuser.push(value.username);
        salebyuser.push(value.name);
    });

    $("#salebyUser").autocomplete({
        source: salebyuser,
        close:function( event, ui ) {
           $("#salebyUser").trigger('keyup');
        }
    });
        
    $("#salebyUser").on('keyup',function(){
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var username = this.value.toUpperCase();
        var name = this.value.toUpperCase();
       // console.log("Name :"+ name);
        $("#salebyId,#salebyName").val(null);
        $.each(saleby, function (key, value) {
            if (value.username.toUpperCase() === username ) {  
                $("#salebyId").val(value.id);
                $("#salebyUser").val(value.username);
                $("#salebyName").val(value.name);
            }
            else if(value.name.toUpperCase() === name){
                $("#salebyUser").val(value.username);
                $("#salebyId").val(value.id);
                $("#salebyName").val(value.name);
            }
        }); 
    }); 
    
    $('#SaleByTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "bFilter": true,
        "bPaginate": true,
        "bInfo": false,
        "bLengthChange": false,
        "iDisplayLength": 10
    });
    
    $('#SaleByTable tbody').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
    
});

function setupproductvalue(id, code, name) {
    $('#ProductModal').modal('hide');
    document.getElementById('product_id').value = id;
    document.getElementById('product_code').value = code;
    document.getElementById('product_name').value = name;
    document.getElementById('product_code').focus();
}

function printOutboundProductSummary(){
    var from  = $("#FromDate").val();
    var to  = $("#ToDate").val();
    var productid  = $("#InputId").val();
    var saleby  = $("#salebyId").val();
    var payby  = $("#SelectPayby").val();
    var bank  = $("#SelectBank").val();
    var productname  = $("#InputProductName").val();
    var salename  = $("#salebyName").val();
    var status = $("#SelectStatus").val();
    
    
    if(((from !== '') && (to !== '')) && from < to ){
        $("#printbutton").removeClass("disabled");
        window.open("Excel.smi?name=OutboundProduct&fromdate="+from+"&todate="+to+"&productid="+productid+"&saleby="+saleby+"&payby="+payby+"&bank="+bank+"&productname="+productname+"&salename="+salename+"&status="+status);   
    }else if((((from !== '') && (to !== '')) && from === to) ) {
        $("#printbutton").removeClass("disabled");
        window.open("Excel.smi?name=OutboundProduct&fromdate="+from+"&todate="+to+"&productid="+productid+"&saleby="+saleby+"&payby="+payby+"&bank="+bank+"&productname="+productname+"&salename="+salename+"&status="+status);   
    }else {
        $('#OutboundProductSummaryForm').bootstrapValidator('revalidateField', 'FromDate');
        $('#OutboundProductSummaryForm').bootstrapValidator('revalidateField', 'ToDate');
        $("#printbutton").addClass("disabled");
    }
//    if((from === '') || (to === '')){
//        validateDate();
//    } else {
//        window.open("Excel.smi?name=OutboundProduct&fromdate="+from+"&todate="+to+"&productid="+productid+"&saleby="+saleby+"&payby="+payby+"&bank="+bank+"&productname="+productname+"&salename="+salename+"&status="+status);   
//    }
}

function validateDate(date,option){
    if(option === 'over'){
        if(date === 'from'){
            $('#OutboundProductSummaryForm').bootstrapValidator('revalidateField', 'FromDate');
            $('#OutboundProductSummaryForm').bootstrapValidator('revalidateField', 'ToDate');
        }
        if(date === 'to'){
            $('#OutboundProductSummaryForm').bootstrapValidator('revalidateField', 'FromDate');
            $('#OutboundProductSummaryForm').bootstrapValidator('revalidateField', 'ToDate');
        }           
        $("#btnDownloadAP").addClass("disabled");
    } else {
//        alert("1");
        $('#OutboundProductSummaryForm').bootstrapValidator('revalidateField', 'FromDate');
        $('#OutboundProductSummaryForm').bootstrapValidator('revalidateField', 'ToDate');
        $("#btnDownloadAP").addClass("disabled");
    }
}
