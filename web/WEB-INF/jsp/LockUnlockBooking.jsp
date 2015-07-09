<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/LockUnlockBooking.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<c:set var="statusList" value="${requestScope['status_list']}" />
<c:set var="bookStatus" value="${requestScope['bookStatus']}" />
<c:set var="saveSuccess" value="${requestScope['save_success']}" />
<c:set var="bookStatusFromRefNo" value="${requestScope['bookStatusFromRefNo']}" />

<section class="content-header" >
    <h1>
        Finance & Cashier
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-book"></i> Finance & Cashier </a></li>          
        <li class="active"><a href="#">Lock and Unlock Booking</a></li>
    </ol>
</section>
<div class ="container"  style="padding-top: 15px;padding-left: 5px;" ng-app="">
    <div class="col-sm-3"></div>
    <div class="col-sm-6">
    <form action="LockUnlockBooking.smi" method="post" id="LockUnlockBookingForm" name="LockUnlockBookingForm" role="form">
    <div class="panel panel-default">  
        <div class="panel-heading">
            <label class="control-label">Lock And Unlock Booking</lable>
        </div>
        <div class="panel-body">       
            <div class="row" style="padding-left: 0px">
                <div class="col-xs-12 ">
                    <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                        <label class="control-label">REF NO.<font style="color: red">*</font></lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right:0px;width:150px;">
                        <div class="col-sm-9">
                            <input style="width:100px;" type="text" class="form-control" id="referenceNo" name="referenceNo"  value="${bookStatusFromRefNo.referenceNo}" onkeypress="return isNumberKey(event)" >
                        </div>
                        <div class="col-sm-1 text-right" style="padding-top: 8px;">
                            <i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i>
                        </div>
                    </div>
                   
                    <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                        <label class="control-label">Status</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right:0px;width:150px;">
                        <div class="col-sm-12">
                            <input id="inputBookStatus" value="${bookStatusFromRefNo.MBookingstatus.id}" hidden="">
                            <select name="SelectStatus" id="SelectStatus" class="form-control" value="${requestScope['SelectStatus']}">
                                <c:forEach var="table" items="${statusList}" >
                                    <c:set var="select" value="" />
                                    <c:set var="selectedId" value="${bookStatusFromRefNo.MBookingstatus.id}" />
                                    <c:if test="${table.id == selectedId}">
                                        <c:set var="select" value="selected" />
                                    </c:if>
                                    <option value="${table.id}" ${select}>${table.name}</option> 
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>   
            </div><!-- End Row 1-->
            <div class="row" style="padding-left: 30px">
                <div class="col-xs-4 text-left">
                    <input type="checkbox" data-label="STRAFF PERSONAL"/>  STRAFF PERSONAL 
                </div>      
            </div>
            <div class="row" style="padding-left: 30px">
                <div class="col-xs-4 text-left">
                    <input type="checkbox" id="flagAir" name="flagAir" data-label="AIR" value="${requestScope['flagAir']}"/>  AIR 
                </div>      
            </div>
            <div class="row" style="padding-left: 30px">
                <div class="col-xs-4 text-left">
                    <input type="checkbox" id="flagHotel" name="flagHotel" data-label="HOTEL" value="${requestScope['flagHotel']}"/>  HOTEL 
                </div>      
            </div>
            <div class="row" style="padding-left: 30px">
                <div class="col-xs-4 text-left">
                    <input type="checkbox" id="flagDaytour" name="flagDaytour" data-label="DAYTOUR" value="${requestScope['flagDaytour']}"/>  DAY TOUR 
                </div>      
            </div>
            <div class="row" style="padding-left: 30px">
                <div class="col-xs-4 text-left">
                    <input type="checkbox" id="flagLand" name="flagLand" data-label="LAND" value="${requestScope['flagLand']}"/>  LAND 
                </div>      
            </div>
            <div class="row" style="padding-left: 30px">
                <div class="col-xs-4 text-left">
                    <input type="checkbox" id="flagOther" name="flagOther" data-label="OTHER" value="${requestScope['flagOther']}"/>  OTHER
                </div>      
            </div>
            <div class="row" style="padding-left: 25px;padding-top: 10px;">
                <div class="col-xs-4 text-right" ></div>
                <div class="col-xs-2 text-right" style="width: 100px;" >
                    <input type="hidden" name="action" id="action" value="save">
                    <button type="submit" id="ButtonSave" name="ButtonSave" onclick="saveAction()" class="btn btn-success">
                        <i class="fa fa-save"></i> Save             
                    </button>
                </div>
                <div class="col-xs-2 text-left" style="width: 100px;">
                    <a id="ButtonNew" name="ButtonNew" onclick="clearAction()" class="btn btn-primary">
                        <i class="glyphicon glyphicon-plus"></i> New
                    </a>
                </div> 
                <div class="col-xs-4 text-right" ></div>
            </div>
        </div>
    </div>
    </form>
    </div>
    <div class="col-sm-3"></div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        setCheckboxFlag();
        
        $("#referenceNo").keyup(function (event) {
            if(event.keyCode === 13){
               var refNo = $("#referenceNo").val();
               searchBookStatus(refNo);
            }
        });
        
        if(${bookStatusFromRefNo.flagAir == 1}){
            $('input:checkbox[name=flagAir]').attr('checked',true);
        }else{
            $('input:checkbox[name=flagAir]').attr('checked',false);
        }
        
        if(${bookStatusFromRefNo.flagHotel == 1}){
            $('input:checkbox[name=flagHotel]').attr('checked',true);
        }else{
            $('input:checkbox[name=flagHotel]').attr('checked',false);
        }
        
        if(${bookStatusFromRefNo.flagDaytour == 1}){
            $('input:checkbox[name=flagDaytour]').attr('checked',true);
        }else{
            $('input:checkbox[name=flagDaytour]').attr('checked',false);
        }
        
        if(${bookStatusFromRefNo.flagOther == 1}){
            $('input:checkbox[name=flagOther]').attr('checked',true);
        }else{
            $('input:checkbox[name=flagOther]').attr('checked',false);
        }
        
        if(${bookStatusFromRefNo.flagLand == 1}){
            $('input:checkbox[name=flagLand]').attr('checked',true);
        }else{
            $('input:checkbox[name=flagLand]').attr('checked',false);
        }

         $("#LockUnlockBookingForm")
            .bootstrapValidator({
//                framework: 'bootstrap',
                container: 'tooltip',
                excluded: [':disabled', ':hidden', ':not(:visible)'],
                feedbackIcons: {
                    valid: 'uk-icon-check',
                    invalid: 'uk-icon-times',
                    validating: 'uk-icon-refresh'
                },
                fields: {
                    referenceNo: {
                        trigger: 'focus keyup',
                        validators: {
                            notEmpty: {trigger: 'change',
                                message: ' Ref No is required'
                            }
                        }
                    }
                }
            })
            .on('success.field.fv', function (e, data) {
                if (data.field === 'referenceNo' && data.fv.isValidField('referenceNo') === false) {
                    data.fv.revalidateField('referenceNo');
                }
            });
});

function setCheckboxFlag(){
    var flagAir = $("#flagAir").val();
    var flagHotel = $("#flagHotel").val();
    var flagDaytour = $("#flagDaytour").val();
    var flagOther = $("#flagOther").val();
    var flagLand = $("#flagLand").val();

    if(flagAir == '1'){
        $('input:checkbox[name=flagAir]').attr('checked',true);
    }else{
        $('input:checkbox[name=flagAir]').attr('checked',false);
    }
    $("#flagAir").click(function () {
        if($('input:checkbox[name=flagAir]').is(':checked')){
            $("#flagAir").val('1');
        }else{
            $("#flagAir").val('0');
        }
    });

    if(flagHotel == '1'){
        $('input:checkbox[name=flagHotel]').attr('checked',true);
    }else{
        $('input:checkbox[name=flagHotel]').attr('checked',false);
    }
    $("#flagHotel").click(function () {
        if($('input:checkbox[name=flagHotel]').is(':checked')){
            $("#flagHotel").val('1');
        }else{
            $("#flagHotel").val('0');
        }
    });

    if(flagDaytour == '1'){
        $('input:checkbox[name=flagDaytour]').attr('checked',true);
    }else{
        $('input:checkbox[name=flagDaytour]').attr('checked',false);
    }
    $("#flagDaytour").click(function () {
        if($('input:checkbox[name=flagDaytour]').is(':checked')){
            $("#flagDaytour").val('1');
        }else{
            $("#flagDaytour").val('0');
        }
    });

    if(flagOther == '1'){
        $('input:checkbox[name=flagOther]').attr('checked',true);
    }else{
        $('input:checkbox[name=flagOther]').attr('checked',false);
    }
    $("#flagOther").click(function () {
        if($('input:checkbox[name=flagOther]').is(':checked')){
            $("#flagOther").val('1');
        }else{
            $("#flagOther").val('0');
        }
    });

    if(flagLand == '1'){
        $('input:checkbox[name=flagLand]').attr('checked',true);
    }else{
        $('input:checkbox[name=flagLand]').attr('checked',false);
    }
    $("#flagLand").click(function () {
        if($('input:checkbox[name=flagLand]').is(':checked')){
            $("#flagLand").val('1');
        }else{
            $("#flagLand").val('0');
        }
    });
}
    
function searchBookStatus(refNo) {
    var servletName = 'BookingStatusServlet';
    var servicesName = 'AJAXBean';
    var param = 'action=' + 'text' +
            '&servletName=' + servletName +
            '&servicesName=' + servicesName +
            '&refNo=' + refNo +
            '&type=' + 'search';
    CallAjax(param);
}

function CallAjax(param) {
    var url = 'AJAXServlet';
    $("#ajaxload").removeClass("hidden");
    try {
        $.ajax({
            type: "POST",
            url: url,
            cache: false,
            data: param,
            success: function(msg) {
                var path = msg.split(',');
                document.getElementById('SelectStatus').value = path[0];
                document.getElementById('flagAir').value = path[1];
                document.getElementById('flagHotel').value = path[2];
                document.getElementById('flagDaytour').value = path[3];
                document.getElementById('flagLand').value = path[4];
                document.getElementById('flagOther').value = path[5];
                setCheckboxFlag();
                $("#ajaxload").addClass("hidden");
            }, error: function(msg) {
                $("#ajaxload").addClass("hidden");
                alert('Reference No Not Valid');
            }
        });
    } catch (e) {
        alert(e);
    }
}

function saveAction() {
    inputCheckBoxVal();
    var action = document.getElementById('action');
    action.value = 'save';
    var referenceNo = document.getElementById('referenceNo');
    referenceNo.value = $("#referenceNo").val();
    var SelectStatus = document.getElementById('SelectStatus');
    SelectStatus.value = $("#SelectStatus").val();
    var flagAir = document.getElementById('flagAir');
    flagAir.value = $("#flagAir").val();
    var flagHotel = document.getElementById('flagHotel');
    flagHotel.value = $("#flagHotel").val();
    var flagDaytour = document.getElementById('flagDaytour');
    flagDaytour.value = $("#flagDaytour").val();
    var flagLand = document.getElementById('flagLand');
    flagLand.value = $("#flagLand").val();
    var flagOther = document.getElementById('flagOther');
    flagOther.value = $("#flagOther").val();
    document.getElementById('LockUnlockBookingForm').submit();
    
}

function inputCheckBoxVal(){
    if($('input:checkbox[name=flagAir]').is(':checked')){
        $("#flagAir").val('1');
    }else{
        $("#flagAir").val('0');
    }
    if($('input:checkbox[name=flagHotel]').is(':checked')){
        $("#flagHotel").val('1');
    }else{
        $("#flagHotel").val('0');
    }
    if($('input:checkbox[name=flagDaytour]').is(':checked')){
        $("#flagDaytour").val('1');
    }else{
        $("#flagDaytour").val('0');
    }
    if($('input:checkbox[name=flagLand]').is(':checked')){
        $("#flagLand").val('1');
    }else{
        $("#flagLand").val('0');
    }
    if($('input:checkbox[name=flagOther]').is(':checked')){
        $("#flagOther").val('1');
    }else{
        $("#flagOther").val('0');
    }
}
function clearAction() {
    $("#referenceNo").val('');
    $("#SelectStatus").val('1');
    $('input:checkbox[name=flagAir]').attr('checked',false);
    $("#flagAir").val('0');
    $('input:checkbox[name=flagHotel]').attr('checked',false);
    $("#flagHotel").val('0');
    $('input:checkbox[name=flagDaytour]').attr('checked',false);
    $("#flagDaytour").val('0');
    $('input:checkbox[name=flagLand]').attr('checked',false);
    $("#flagLand").val('0');
    $('input:checkbox[name=flagOther]').attr('checked',false);
    $("#flagOther").val('0');
}

function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : evt.keyCode;

    if (charCode == 45 || (charCode >= 48 && charCode <= 57)){
       return true;
    }
    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)){
       return false;
    }
//    return true;
}

    $('input[type="checkbox"]').checkbox({
        checkedClass: 'icon-check',
        uncheckedClass: 'icon-check-empty'
    });

</script>