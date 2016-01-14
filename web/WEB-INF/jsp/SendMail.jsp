<%-- 
    Document   : SendMail
    Created on : Jun 22, 2015, 3:23:36 PM
    Author     : Jittima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/workspace.js"></script> 
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send an e-mail</title>
    </head>
 <c:set var="file" value="${requestScope['file']}" />   
 <c:set var="reportname" value="${requestScope['reportname']}" />   
 <c:set var="reportid" value="${requestScope['reportid']}" />   
 <c:set var="optionsend" value="${requestScope['optionsend']}" />
 <c:set var="bankid" value="${requestScope['bankid']}" />   
 <c:set var="showstaff" value="${requestScope['showstaff']}" />  
 <c:set var="showleader" value="${requestScope['showleader']}" />  
<c:set var="sign" value="${requestScope['sign']}" />  
<body>
    
<div class ="container"  style="padding-top: 30px;" ng-app="">
    <c:if test="${requestScope['result'] == 'send email success'}">                                            
        <div id="textAlertDivSave"  style="" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Send Email Success!.</strong> 
        </div>
    </c:if>        
    <c:if test="${requestScope['result'] == 'send email fail'}">
        <div id="textAlertDivNotSave"  style="" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Send Email Unsuccess!.</strong> 
        </div>
    </c:if>
    <div class="row">
        <div class="col-sm-12">
            <form action="SendMail.smi" method="post" id="Mail" role="form">
                <input type="text" id="reportname" name="reportname" value="${reportname}" class="hidden">
                <input type="text" id="reportid" name="reportid" value="${reportid}" class="hidden">
                <input type="text" id="optionsend" name="optionsend" value="${optionsend}" class="hidden">
                <input type="text" id="bankid" name="bankid" value="${bankid}" class="hidden">
                <input type="text" id="showstaff" name="showstaff" value="${showstaff}" class="hidden">
                <input type="text" id="showleader" name="showleader" value="${showleader}" class="hidden">
                <input type="text" id="showleader" name="sign" value="${sign}" class="hidden">
                
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Send Email</h3>
                    </div>
                    
                    <div class="panel-body" style="padding-top: 30px">
                        <div class="col-sm-12">
                            <div class="col-xs-1 text-right">
                                <button style="height: 50px ;width: 120px" type="submit" id="sendEmailBtn" name="sendEmailBtn" onclick="sendEmailStatus()" class="form-control btn btn-primary">Send</button>
                            </div>
                            <div class="col-xs-1" style="padding: 20px 0px 0px 50px"><i id="ajaxload"  class="fa fa-spinner fa-spin hidden"></i></div>
                            <div class="col-xs-10 text-right">
                                <label class="col-sm-1 control-label text-right">To<font style="color: red">*</font> : </label>
                                <div class="input-group text-left" >
                                    <input type="text" class="form-control " id="recipient" name="recipient" size="50" style="width: 200%" value="${requestScope['recipient']}" onkeypress="sendEmailStatusCancel()" onfocusout="checkEmailTo()"> 
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="col-xs-1 text-right">
                            </div>
                            <div class="col-xs-10 text-right">
                                <label class="col-sm-2 control-label text-right" style="width: 177px">Cc : </label>
                                <div class="input-group text-left" >
                                    <input type="text" class="form-control" id="sendCc" name="sendCc" size="50" style="width: 200%" value="${requestScope['sendCc']}" onkeypress="sendEmailStatusCancel()" onfocusout="checkEmailCc()"> 
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12" style="padding-top: 20px">
                            <div class="col-xs-1 text-right">
                            </div>
                            <div class="col-xs-10 text-right">
                                <label class="col-sm-2 control-label text-right" style="width: 177px">Subject<font style="color: red">*</font> : </label>
                                <div class="input-group text-left" >
                                    <input type="text" class="form-control" id="subject" name="subject" size="50" style="width: 200%" value="${requestScope['subject']}" onkeypress="sendEmailStatusCancel()"> 
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12" style="padding-top: 20px">
                            <div class="col-xs-1 text-right">
                            </div>
                            <div class="col-xs-10 text-right">
                                <label class="col-sm-2 control-label text-right" style="width: 177px">File : </label>
                                <div class="input-group text-left" >
                                    <input type="text" class="form-control" id="attachfile" name="attachfile" readonly  value="${reportname}" size="50" style="width: 200%"> 
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-12" style="padding-top: 20px">
                            <div class="col-xs-1 text-right">
                            </div>
                            <div class="col-xs-10 text-right">
                                <label class="col-sm-2 control-label text-right" style="width: 177px"></label>
                                <div class="input-group text-left" >
                                    <textarea rows="10" cols="52" class="form-control" id="message" name="message" style="width: 200%" onkeypress="sendEmailStatusCancel()">${requestScope['message']}</textarea>  
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $('#Mail').bootstrapValidator({
            container: 'tooltip',
            excluded: [':disabled'],
            feedbackIcons: {
                valid: 'uk-icon-check',
                invalid: 'uk-icon-times',
                validating: 'uk-icon-refresh'
            },
            fields: {
                recipient: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Please specify a destination email.'
                        }
                    }
                },
                subject: {
                    trigger: 'focus keyup change',
                    validators: {
                        notEmpty: {
                            message: 'Please specify a subject.'
                        }
                    }
                }
            }
        });
    });

    function sendEmail(){
//            alert($('#sendCc').val());
//            alert($('#subject').val());
//            alert($('#fileAttachment').val());
//            alert($('#message').val());
            var servletName = 'MailServlet';
            var servicesName = 'AJAXBean';
            var sendTo = document.getElementById('sendTo').value;
            var sendCc = document.getElementById('sendCc').value;
            var subject = document.getElementById('subject').value;
            var attachfile = document.getElementById('attachfile').value;
            var content = document.getElementById('content').value;

            var param = 'action=' + 'text' +
                '&servletName=' + servletName +
                '&servicesName=' + servicesName +
                '&sendTo=' + sendTo +
                '&sendCc=' + sendCc +
                '&subject=' + subject +
                '&attachfile=' + attachfile +
                '&content=' + content +
                '&type=' + 'sendMail';

                if(confirm('Send Email ?')){
                    callAjaxSendEmail(param);
                }

        }
        
    function callAjaxSendEmail(param){
        var url = 'AJAXServlet';
            $.ajax({
                type: "POST",
                url: url,
                cache: false,
                data: param,
                success: function(msg) {

                    if(msg =='success'){
                        alert('send successful');
                    }else{
                        alert('send unsuccessful');
                    }
                }, error: function(msg) {
                   alert('send unsuccessful');
                }
            });
    }
    
    function sendEmailStatus(){
        $("#ajaxload").removeClass("hidden");
    }
    
    function sendEmailStatusCancel(){
        $("#ajaxload").addClass("hidden");
    }

    function checkEmailTo() {
        var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        var email = document.getElementById('recipient').value;
        var email_list = "";       
        email = email.replace(/ /g,"");
        if(((email).indexOf(',')) !== -1){
            email_list = (email).split(',');
        } else if(((email).indexOf(';')) !== -1){
            email_list = (email).split(';');
        } else {
            email_list = (email).split(';');
        }
        var recipientField = document.getElementById('recipient');
        for(var i=0;i<email_list.length;i++){
            if (!filter.test(email_list[i])) {                 
                recipientField.style.borderColor = "Red";
                $("#sendEmailBtn").addClass("disabled");
                return false;
            }          
        }
        recipientField.style.borderColor = "Green";
        if(document.getElementById('sendCc').style.borderColor === 'red'){
            return false;
        }
        $("#sendEmailBtn").removeClass("disabled");
    }               

    function checkEmailCc(){
        var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        var email = document.getElementById('sendCc').value;
        if(email === ''){
            return ;
        } else {
            var email_list = "";
            email = email.replace(/ /g,"");
            if(((email).indexOf(',')) !== -1){
                email_list = (email).split(',');
            } else if(((email).indexOf(';')) !== -1){
                email_list = (email).split(';');
            } else {
                email_list = (email).split(';');
            }
            
            var sendCcField = document.getElementById('sendCc');
            for(var i=0;i<email_list.length;i++){
                if (!filter.test(email_list[i])) {                 
                    sendCcField.style.borderColor = "Red";
                    $("#sendEmailBtn").addClass("disabled");
                    return false;
                }          
            }
            sendCcField.style.borderColor = "Green";
            if(document.getElementById('recipient').style.borderColor === 'red'){
                return false;
            }
            $("#sendEmailBtn").removeClass("disabled");
        }      
    }

</script>


