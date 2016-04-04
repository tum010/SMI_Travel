
var showflag = 1;
$(document).ready(function () {
    // DATATIMEPICKER
    if($("#birthDate").val() !== ''){
        var date = $("#birthDate").val();
        $("#birthDate").val(convertFormatDate(date));
    }
    $('.date').datetimepicker();
    $('.spandate').click(function () {
        var position = $(this).offset();
        $(".bootstrap-datetimepicker-widget").css("top", position.top + 32);
    });
    $('body').on('click mousemove', function () {
        //getDate();
        var birthDate = new Date(convertFormatDate($('#birthDate').val()));
        var age = _calculateAge(birthDate);
        if($("#birthDate").val() === ""){
            $("#age").val(0);
        }else{
            $("#age").val(age);
        }
        
    });
    // VALIDATE
    $('#PassengerForm')
            .bootstrapValidator({
                container: 'tooltip',
                excluded: [':disabled'],
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    MInitialname: {
                        validators: {
                            notEmpty: {
                                message: 'The Initialname is required'
                            }
                        }
                    },
                    firstName: {
                        validators: {
                            notEmpty: {
                                message: 'The FirstName is required'
                            }
                        }
                    },
                    lastName: {
                        validators: {
                            notEmpty: {
                                message: 'The lastName is required'
                            }
                        }
                    },
                    birthDate: {
                        validators: {
                            date: {
                                format: 'DD-MM-YYYY',
                                message: 'The value is not a valid date'
                            }
                        }
                    },
                    postalCode: {
                        validators: {
                            digits: {
                                message: 'The Postal Code is Number'
                            }
                        }
                    },
                    email: {
                        validators: {
                            emailAddress: {
                                message: 'The value is not a valid email address'
                            },
                            regexp: {
                                regexp: '^[^@\\s]+@([^@\\s]+\\.)+[^@\\s]+$',
                                message: 'The value is not a valid email address'
                            }
                        }
                    }

                }
            }).on('success.field.bv', function (e, data) {
        if (data.bv.isValid()) {
            data.bv.disableSubmitButtons(false);
        }
    });
    
    
    //Autocomplete Ajax begin     
    $("#passengerIdVal").keyup(function(event){   
        var position = $(this).offset();
        $(".ui-widget").css("top", position.top + 30);
        $(".ui-widget").css("left", position.left); 
        if($(this).val() === ""){
            $("#customerId").val("");
            $("#MInitialname").val("");
            $("#firstName").val("");
            $("#lastName").val("");
            $("input[name=sex]").prop('checked', false);
            $("#address").val("");
            $("#tel").val("");
            $("#phone").val("");
            $("#email").val("");
            $("#remark").val("");
            $("#Passport").val("");
            $("#firstNameJapan").val("");
            $("#lastNameJapan").val("");
        }else{
            if(event.keyCode === 13){
                searchPassengerAutoList(this.value); 
            }
        }
    });
    $("#passengerIdVal").keydown(function(){
            var position = $(this).offset();
            $(".ui-widget").css("top", position.top + 30);
            $(".ui-widget").css("left", position.left); 
            if(showflag == 0){
                $(".ui-widget").css("top", -1000);
                showflag=1;
            }
        
    });
      
//Autocomplete Ajax end  
});

function searchPassengerAutoList(name){
    var servletName = 'PassengerServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'searchAutoPassenger';
    CallAjaxPassengerAuto(param);
}
function CallAjaxPassengerAuto(param){
     var url = 'AJAXServlet';
     var PSArray = [];
     var PSListId= [];
     var PSListCode= [];
     var PSListInitialName = [];
     var PSListFirtName = [];
     var PSListLastName = [];
     var PSListSex = [];
     var PSListAddress = [];
     var PSListTel = [];
     var PSListPhone = [];
     var PSListEmail = [];
     var PSListRemark = [];
     var PSListPassportNo = [];
     var PSListFirstNameJapan = [];
     var PSListLastNameJapan = [];
     var psid ,pscode , psinitialname, psfirstname ,pslastname, pssex, psaddress,
               pstel, psphone, psemail, psremark, pspassportno, psfirstnamejapan,
               pslastnamejapan;
     $("#passengerIdVal").autocomplete("destroy");
     try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            beforeSend: function() {
               $("#dataload").removeClass("hidden");    
            },
            success: function(msg) {     
                console.log("getAutoListPS =="+msg);
                var PSJson =  JSON.parse(msg);
                for (var i in PSJson){
                    if (PSJson.hasOwnProperty(i)){
                        psid          = PSJson[i].id;
                        pscode        = PSJson[i].code;
                        psinitialname = PSJson[i].initialname;
                        psfirstname   =  PSJson[i].firstname;
                        pslastname    =  PSJson[i].lastname;
                        pssex         = PSJson[i].sex;
                        psaddress     = PSJson[i].address;
                        pstel         = PSJson[i].tel;
                        psphone       = PSJson[i].phone;
                        psemail       = PSJson[i].email;
                        psremark      = PSJson[i].remark;
                        pspassportno  = PSJson[i].passportno;
                        psfirstnamejapan  = PSJson[i].firstnamejapan;
                        pslastnamejapan  = PSJson[i].lastnamejapan;
                        
                        PSArray.push(pscode);
                        PSArray.push(pslastname+" "+psfirstname);
//                        PSArray.push();
                        
                        PSListId.push(psid);
                        PSListCode.push(pscode);
                        PSListInitialName.push(psinitialname);
                        PSListFirtName.push(psfirstname);
                        PSListLastName.push(pslastname);                     
                        PSListSex.push(pssex);
                        PSListAddress.push(psaddress);
                        PSListTel.push(pstel);
                        PSListPhone.push(psphone);
                        PSListEmail.push(psemail);
                        PSListRemark.push(psremark);
                        PSListPassportNo.push(pspassportno);
                        PSListFirstNameJapan.push(psfirstnamejapan);
                        PSListLastNameJapan.push(pslastnamejapan);
               
                    }                 
                     $("#dataload").addClass("hidden"); 
                }
                $("#customerId").val(psid);
                $("#passengerId").val(pscode);
                $("#MInitialname").val(psinitialname);
                $("#firstName").val(psfirstname);
                $("#lastName").val(pslastname);
                if(pssex === "m"){
                    $("input[name=sex][value=m]").prop('checked', true);
                }else if(pssex === "w"){
                    $("input[name=sex][value=w]").prop('checked', true);
                }else{
                    $("input[name=sex]").prop('checked', false);
                }                
                $("#address").val(psaddress);
                $("#tel").val(pstel);
                $("#phone").val(psphone);
                $("#email").val(psemail);
                $("#remark").val(psremark);
                $("#Passport").val(pspassportno);
                $("#firstNameJapan").val(psfirstnamejapan);
                $("#lastNameJapan").val(pslastnamejapan);
              
                $("#passengerIdVal").autocomplete({
                    source: PSArray,
                    close: function(){
                        $("#passengerIdVal").trigger("keyup");
                        var psselect = $("#passengerIdVal").val();
                        for(var i =0;i < PSListCode.length;i++){
                            if((psselect===PSListLastName[i]+" "+PSListFirtName[i])||(psselect===PSListCode[i])){  
                                $("#customerId").val(PSListId[i]);
                                $("#passengerId").val(PSListCode[i]);
                                $("#passengerIdVal").val(PSListCode[i]);
                                $("#MInitialname").val(PSListInitialName[i]);
                                $("#firstName").val(PSListFirtName[i]);
                                $("#lastName").val(PSListLastName[i]); 
                                $("input[name=sex][value="+PSListSex[i]+"]").prop('checked', true);
                                $("#address").val(PSListAddress[i]);
                                $("#tel").val(PSListTel[i]);
                                $("#phone").val(PSListPhone[i]);
                                $("#email").val(PSListEmail[i]);
                                $("#remark").val(PSListRemark[i]);
                                $("#Passport").val(PSListPassportNo[i]);
                                $("#firstNameJapan").val(PSListFirstNameJapan[i]);
                                $("#lastNameJapan").val(PSListLastNameJapan[i]);
                            }                 
                        }   
                    }
                 });
                
                var PSVal = $("#passengerIdVal").val();
                for(var i =0;i<PSListCode.length;i++){
                    if(PSVal==PSListFirtName[i]){
                        $("#passengerIdVal").val(PSListCode[i]);
                    }
                }
                if(PSListCode.length == 1){
                    showflag = 0;
                    $("#passengerIdVal").val(PSListCode[0]);
                }
                var event = jQuery.Event('keydown');
                event.keyCode = 40;
                $("#passengerIdVal").trigger(event);
                  
            }, error: function(msg) {
                console.log('auto ERROR');
                $("#dataload").addClass("hidden");
            }
        });
    } catch (e) {
        alert(e);
    }
}

$(document).ready(function () {
  


//    console.log(customer);
//    var codeCustomer = [];
//    $.each(customer, function (key, value) {
//        console.log('customer=='+customer.length);
//        codeCustomer.push(value.code);
//        if ( !(value.firstname in codeCustomer) ){
//           codeCustomer.push(value.firstname);
//        }
//        if ( !(value.lastname in codeCustomer) ){
//           codeCustomer.push(value.lastname);
//        }
//    });
//    $("#passengerId").autocomplete({
//        source: codeCustomer,
//        close:function( event, ui ) {
//           $("#passengerId").trigger('keyup');
//        }
//    });
//    $("#passengerId").on('keyup', function () {
//        var position = $(this).offset();
//        console.log("positon :" + position.top);
//        $(".ui-widget").css("top", position.top + 30);
//        $(".ui-widget").css("left", position.left);
//        var code = this.value.toUpperCase();
//        var firstname = this.value;
//        var lastname = this.value;
//        $("#id,#MInitialname,#firstName,#lastName,#sex,#address,#tel,#phone,#email,#remark,#Passport,#firstNameJapan,#lastNameJapan").val(null);
//        $.each(customer, function (key, value) {
//            if (value.code.toUpperCase() === code) {
//                console.log('ok');
//                $("#MInitialname").val(value.initialId);
//                $("#firstName").val(value.firstname);
//                $("#lastName").val(value.lastname);
//                $("#sex").val(value.sex);
//                $("#address").val(value.address);
//                $("#tel").val(value.tel);
//                $("#phone").val(value.phone);
//                $("#email").val(value.email);
//                $("#remark").val(value.remark);
//                $("#Passport").val(value.passportNo);
//                $("#firstNameJapan").val(value.firstNameJapan);
//                $("#lastNameJapan").val(value.lastNameJapan);
//            }
//            if(firstname === value.firstname){
//                $("#passengerId").val(value.code);
//                $("#MInitialname").val(value.initialId);
//                $("#firstName").val(value.firstname);
//                $("#lastName").val(value.lastname);
//                $("#sex").val(value.sex);
//                $("#address").val(value.address);
//                $("#tel").val(value.tel);
//                $("#phone").val(value.phone);
//                $("#email").val(value.email);
//                $("#remark").val(value.remark);
//                $("#Passport").val(value.passportNo);
//                $("#firstNameJapan").val(value.firstNameJapan);
//                $("#lastNameJapan").val(value.lastNameJapan);
//                code = $("#passengerId").val().toUpperCase();
//            }
//            if(lastname === value.lastname){
//                $("#passengerId").val(value.code);
//                $("#MInitialname").val(value.initialId);
//                $("#firstName").val(value.firstname);
//                $("#lastName").val(value.lastname);
//                $("#sex").val(value.sex);
//                $("#address").val(value.address);
//                $("#tel").val(value.tel);
//                $("#phone").val(value.phone);
//                $("#email").val(value.email);
//                $("#remark").val(value.remark);
//                $("#Passport").val(value.passportNo);
//                $("#firstNameJapan").val(value.firstNameJapan);
//                $("#lastNameJapan").val(value.lastNameJapan);
//                code = $("#passengerId").val().toUpperCase();
//            }
//        });
//    });
    $("#CustomerTable tr").on('click', function () {
        var customer_id = $(this).find(".customer-id").text();
        var customer_code = $(this).find(".customer-code").text();
        var customer_initial = $(this).find(".customer-initial").text();
        var customer_initialId = $(this).find(".customer-initialId").text();
        var customer_firstname = $(this).find(".customer-firstname").text();
        var customer_lastname = $(this).find(".customer-lastname").text();
        var customer_sex = $(this).find(".customer-sex").text();
        var customer_address = $(this).find(".customer-address").text();
        var customer_tel = $(this).find(".customer-tel").text();
        var customer_phone = $(this).find(".customer-phone").text();
        var customer_postal = $(this).find(".customer-postal").text();
        var customer_email = $(this).find(".customer-email").text();
        var customer_remark = $(this).find(".customer-remark").text();
        var customer_passportno = $(this).find(".customer-passportno").text();
        var customer_japanfirstname = $(this).find(".customer-japanfirstname").text();
        var customer_japanlastname = $(this).find(".customer-japanlastname").text();
        $("#customerId").val(customer_id);
        $("#MInitialname").val(customer_initialId);
        $("#passengerId").val(customer_code);
        $("#passengerIdVal").val(customer_code);
        $("#firstName").val(customer_firstname);
        $("#lastName").val(customer_lastname);
        if(customer_sex !== ""){
            $("input[name=sex][value="+customer_sex+"]").prop('checked', true);
        }else{
            $("input[name=sex]").prop('checked', false);
        }
        
        $("#address").val(customer_address);
        $("#tel").val(customer_tel);
        $("#phone").val(customer_phone);
        $("#email").val(customer_email);
        $("#remark").val(customer_remark);
        $("#Passport").val(customer_passportno);
        $("#CustomerModal").modal('hide');
    });
    // PASSENGER TABLE
    $('#CustomerTable').dataTable({bJQueryUI: true,
        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
    });
    $('#CustomerTable tbody').on('click', 'tr', function () {
        $(this).addClass('row_selected').siblings().removeClass('row_selected');
    });
    
     $("#filtercus").keyup(function (event) {
        if (event.keyCode === 13) {
            FilterCustomerList($("#filtercus").val());
        }
    });
});

function _calculateAge(birthday) {
    var ageDifMs = Date.now() - birthday.getTime();
    var ageDate = new Date(ageDifMs);
    return Math.abs(ageDate.getUTCFullYear() - 1970);
}

function FilterCustomerList(name) {
    // var name = 
    var servletName = 'PassengerServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&name=' + name +
            '&type=' + 'searchPassenger';
    CallFilterAjax(param);
    
}

function CallFilterAjax(param) {
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function (msg) {
                try {
                   
                    $('#CustomerTable').dataTable().fnClearTable();
                    $('#CustomerTable').dataTable().fnDestroy();
                    $("#CustomerTable tbody").empty().append(msg);
                    $("#CustomerTable tr").on('click', function () {
                    var customer_id = $(this).find(".customer-id").text();
                    var customer_code = $(this).find(".customer-code").text();
                    var customer_initial = $(this).find(".customer-initial").text();
                    var customer_initialId = $(this).find(".customer-initialId").text();
                    var customer_firstname = $(this).find(".customer-firstname").text();
        var customer_lastname = $(this).find(".customer-lastname").text();
        var customer_sex = $(this).find(".customer-sex").text();
        var customer_address = $(this).find(".customer-address").text();
        var customer_tel = $(this).find(".customer-tel").text();
        var customer_phone = $(this).find(".customer-phone").text();
        var customer_postal = $(this).find(".customer-postal").text();
        var customer_email = $(this).find(".customer-email").text();
        var customer_remark = $(this).find(".customer-remark").text();
        var customer_passportno = $(this).find(".customer-passportno").text();
        var customer_japanfirstname = $(this).find(".customer-japanfirstname").text();
        var customer_japanlastname = $(this).find(".customer-japanlastname").text();
        $("#customerId").val(customer_id);
        $("#MInitialname").val(customer_initialId);
        $("#passengerId").val(customer_code);
        $("#firstName").val(customer_firstname);
        $("#lastName").val(customer_lastname);
        $("#sex").val(customer_sex);
        $("#address").val(customer_address);
        $("#tel").val(customer_tel);
        $("#phone").val(customer_phone);
        $("#email").val(customer_email);
        $("#remark").val(customer_remark);
        $("#Passport").val(customer_passportno);
        $("#CustomerModal").modal('hide');
    });
                    $('#CustomerTable').dataTable({bJQueryUI: true,
                        "sPaginationType": "full_numbers",
                        "bAutoWidth": false,
                        "bFilter": false,
                        "bPaginate": true,
                        "bInfo": false,
                        "bLengthChange": false,
                        "iDisplayLength": 10
                    });
                     $("#ajaxload").addClass("hidden");
                     
                
                
                
 
                } catch (e) {
                    alert(e);
                }

            }, error: function (msg) {
                 $("#ajaxload").addClass("hidden");
                alert(msg);
            }
        });
    } catch (e) {
        alert(e);
    }


}