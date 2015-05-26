<%-- 
    Document   : HotelSumReport
    Created on : Dec 23, 2014, 11:43:55 AM
    Author     : wleenavo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/Hotel.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section class="content-header"  >
    <h4>
        <b>Report : Hotel Summary</b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Report</a></li>          
        <li class="active"><a href="#">Hotel Summary</a></li>
    </ol>
</section>

<div class="container" style="padding-top: 30px;">

    <div class="col-md-12">
        <div class="row">
                        <div class="col-md-2" style="border-right:  solid 1px #01C632;padding-top: 10px">
                <ul class="nav nav-list" style="top: 0px;  background-color: #FAFEFA;border: solid 1px #0063DC">
                    <li class="">
                        <a href="HotelSumReport.smi">
                            <i class="menu-icon fa fa-home"></i>
                            <span class="menu-text">Hotel Summary</span>
                        </a>
                        <b class="arrow"></b>
                    </li>
                </ul>
            </div>
            <div class="form-group">
                <div class="col-md-6">
                    <h3>Check In Date</h3>
                </div>
            </div>
        </div>
        <form role="form" class="form-horizontal">
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                                <label class="col-md-6 control-label text-right"> From Date</label>
                                <div class="col-md-6">  
                                    <div class="form-group">
                                        <div class='input-group date' id='fromdate'>
                                            <input type='text' class="form-control" />
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                    </div>
                </div>



            </div>
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="col-md-6 control-label text-right"> To Date</label>
                        <div class="col-md-6">  
                            <div class="form-group">
                                <div class='input-group date' id='todate'>
                                    <input type='text' class="form-control" />
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="col-md-6 control-label text-right" for="hotel">Hotel</label>
                        <div class="col-md-6">  
                            <div class="form-group">
                                <select name="hotel" id="hotel"  class="form-control">
                                    <option value="0"  selected="selected">-- all --</option>
                                    <option>Anantara</option>
                                    <option>Mandarin</option>
                                </select>
                            </div>
                        </div>   
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="col-md-6 control-label text-right" for="rept"></label>
                        <div class="col-md-6">  
                            <div class="form-group">
                                <button type="submit" onclick class="btn btn-primary" >Save</button>
                            </div>
                        </div>   
                    </div>
                </div>
            </div>
            </div>
        </form>   

    </div>







</div>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        $('.date').datetimepicker();
        $('span').click(function () {
            var position = $(this).offset();
            console.log("positon :"+position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);
            
        });



    });
</script>
