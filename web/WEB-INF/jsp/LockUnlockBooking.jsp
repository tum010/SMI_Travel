<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/LockUnlockBooking.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <div class="panel panel-default">  
        <div class="panel-heading">
            <label class="control-label">Lock And Unlock Booking</lable>
        </div>
        <div class="panel-body">       
            <div class="row" style="padding-left: 0px">
                <div class="col-xs-12 ">
                    <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                        <label class="control-label">REF NO.</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right:0px;width:150px;">
                        <div class="col-sm-12">
                            <input name="RefNo" id="RefNo" type="text" class="form-control" value="" />
                        </div>
                    </div>
                    <div class="col-xs-1 text-right" style="padding-left: 0px;width:100px;">
                        <label class="control-label">Status</lable>
                    </div>
                    <div class="col-md-2 form-group text-left" style="padding-left:0px;padding-right:0px;width:150px;">
                        <div class="col-sm-12">
                            <select name="SelectStatus" id="SelectStatus" class="form-control">
                                <option id="" value="">---select--</option>
                                <option id="" value="">---status1--</option>
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
                    <input type="checkbox" data-label="AIR"/>  AIR 
                </div>      
            </div>
            <div class="row" style="padding-left: 30px">
                <div class="col-xs-4 text-left">
                    <input type="checkbox" data-label="HOTEL"/>  HOTEL 
                </div>      
            </div>
            <div class="row" style="padding-left: 30px">
                <div class="col-xs-4 text-left">
                    <input type="checkbox" data-label="DAYTOUR"/>  DAY TOUR 
                </div>      
            </div>
            <div class="row" style="padding-left: 30px">
                <div class="col-xs-4 text-left">
                    <input type="checkbox" data-label="LAND"/>  LAND 
                </div>      
            </div>
            <div class="row" style="padding-left: 30px">
                <div class="col-xs-4 text-left">
                    <input type="checkbox" data-label="OTHER"/>  OTHER
                </div>      
            </div>
            <div class="row" style="padding-left: 25px;padding-top: 10px;">
                <div class="col-xs-4 text-right" ></div>
                <div class="col-xs-2 text-right" style="width: 100px;" >
                    <button type="submit" id="ButtonSave" name="ButtonSave" class="btn btn-success">
                        <i class="fa fa-save"></i> Save             
                    </button>
                </div>
                <div class="col-xs-2 text-left" style="width: 100px;">
                    <a id="ButtonNew" name="ButtonNew" onclick="" class="btn btn-primary">
                        <i class="glyphicon glyphicon-plus"></i> New
                    </a>
                </div> 
                <div class="col-xs-4 text-right" ></div>
            </div>
        </div>
    </div>
    </div>
    <div class="col-sm-3"></div>
</div>
<script type="text/javascript">
    $('input[type="checkbox"]').checkbox({
        checkedClass: 'icon-check',
        uncheckedClass: 'icon-check-empty'
    });
</script>