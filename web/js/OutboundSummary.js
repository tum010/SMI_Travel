/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('.date').datetimepicker();
    $('.datemask').mask('0000-00-00', {reverse: true});
    $('.spandate').click(function() {
        var position = $(this).offset();
        console.log("positon :" + position.top);
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

    });

    $("#HotelSummaryReportFrom")
            .bootstrapValidator({
                framework: 'bootstrap',
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    FromDate: {
                        trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                max: 'ToDate',
                                message: 'The Date From is not a valid'
                            }, notEmpty: {
                                message: 'The Date From is required'
                            }
                        }
                    },
                    ToDate: {
                        trigger: 'focus keyup change',
                        validators: {
                            date: {
                                format: 'YYYY-MM-DD',
                                min: 'FromDate',
                                message: 'The Date To is not a valid'
                            }, notEmpty: {
                                message: 'The Date To is required'
                            }
                        }
                    }
                }
            }).on('success.field.fv', function(e, data) {
        if (data.field === 'FromDate' && data.fv.isValidField('ToDate') === false) {
            data.fv.revalidateField('ToDate');
        }

        if (data.field === 'ToDate' && data.fv.isValidField('FromDate') === false) {
            data.fv.revalidateField('FromDate');
        }
    });
    $('#DateFrom').datetimepicker().on('dp.change', function(e) {
        $('#OutboundProductSummaryForm').bootstrapValidator('revalidateField', 'FromDate');
    });
    $('#DateTo').datetimepicker().on('dp.change', function(e) {
        $('#OutboundProductSummaryForm').bootstrapValidator('revalidateField', 'ToDate');
    });

    Selectize.define('clear_selection', function(options) {
        var self = this;
        self.plugins.settings.dropdown_header = {
            title: 'Clear Selection'
        };
        this.require('dropdown_header');
        self.setup = (function() {
            var original = self.setup;
            return function() {
                original.apply(this, arguments);
                this.$dropdown.on('mousedown', '.selectize-dropdown-header', function(e) {
                    self.setValue('');
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
        sortField: 'text',
        create: false,
        dropdownParent: 'body',
        plugins: {
            'clear_selection': {}
        }
    });

    var City = "#SelectCity";
    $(City).selectize({
        removeItem: '',
        sortField: 'text',
        create: false,
        dropdownParent: 'body',
        plugins: {
            'clear_selection': {}
        }
    });
    //Sale By Auto Complete
    $("#SaleByTable tr").on('click', function() {
        var saleby_id = $(this).find(".saleby-id").text();
        var saleby_user = $(this).find(".saleby-user").text();
        var saleby_name = $(this).find(".saleby-name").text();
        $("#salebyId").val(saleby_id);
        $("#salebyUser").val(saleby_user);
        $("#salebyName").val(saleby_name);
        $("#SaleByModal").modal('hide');
    });

    var salebyuser = [];
    $.each(saleby, function(key, value) {
        salebyuser.push(value.username);
        salebyuser.push(value.name);
    });

    $("#salebyUser").autocomplete({
        source: salebyuser,
        close: function(event, ui) {
            $("#salebyUser").trigger('keyup');
        }
    });

    $("#salebyUser").on('keyup', function() {
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left);
        var username = this.value.toUpperCase();
        var name = this.value.toUpperCase();
        // console.log("Name :"+ name);
        $("#salebyId,#salebyName").val(null);
        $.each(saleby, function(key, value) {
            if (value.username.toUpperCase() === username) {
                $("#salebyId").val(value.id);
                $("#salebyUser").val(value.username);
                $("#salebyName").val(value.name);
            }
            else if (value.name.toUpperCase() === name) {
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

    $('#SaleByTable tbody').on('click', 'tr', function() {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });

});

function printOutboundHotelSummary(){
    var from  = $("#FromDate").val();
    var to  = $("#ToDate").val();
    var hotelid  = $("#InputId").val();
    var saleby  = $("#salebyId").val();
    var payby  = $("#SelectPayby").val();  
    var bank  = $("#SelectBank").val();
//    var status  = $("#SelectStatus").val();
    var status = $("#SelectStatus :selected").text()
    var country  = $("#SelectCountry").val();
    var city  = $("#SelectCity").val();

    if((from === '') || (to === '')){
        validateDate();
    } else {
        window.open("Excel.smi?name=OutboundHotelSummary&fromdate="+from+"&todate="+to+"&hotelid="+hotelid+"&saleby="+saleby+"&payby="+payby+"&bank="+bank+"&status="+status+"&country="+country+"&city="+city);   
    }
}

function validateDate(date,option){
    if(option === 'over'){
        if(date === 'from'){
            $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'FromDate');
            $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'ToDate');
        }
        if(date === 'to'){
            $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'FromDate');
            $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'ToDate');
        }           
        $("#btnPrint").addClass("disabled");
    } else {
//        alert("1");
        $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'FromDate');
        $('#HotelSummaryReportFrom').bootstrapValidator('revalidateField', 'ToDate');
        $("#btnPrint").addClass("disabled");
    }
}

