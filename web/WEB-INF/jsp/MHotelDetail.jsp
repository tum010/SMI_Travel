<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/MHotelDetail.js"></script> 
<script src="js/select2.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cityList" value="${requestScope['city_list']}" />
<c:set var="countryList" value="${requestScope['country_list']}" />
<c:set var="disableHotelCode" value="${requestScope['disableHotelCode']}" />
<section class="content-header"  >
    <h4>
        <b>Master : Hotel</b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li><a href="#">Hotel MA</a></li>
        <li class="active"><a href="#">Hotel Detail</a></li>
    </ol>
</section>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        $('select').select2();
        $(".city-multiple").select2({
        });
        $(".country-multiple").select2({});
        
        $('#country').on('click',  function () {
            $('#country').select2('focus');
        });
        
    });

   

</script>

<div class ="container"  style="padding-top: 15px;"> 
    <form action="MHotelDetail.smi" method="post" id="HotelForm" role="form" class="form-horizontal">
        <div class="col-md-8 col-xs-offset-2">
            <!-- Alert Uni-->
            <div id="textAlertLap"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Hotel name already exist!</strong> 
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">Detail</div>
                <div class="panel-body">

                      <div class="row">
                        <div class="col-md-12 form-group">
                            <div class="col-md-6 col-md-offset-6 text-right">
                                <a id="ButtonBack" name="ButtonBack" href="MHotel.smi" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Back</a>
                            </div>
                        </div>
                        </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2   control-label" for="Country">Code<font style="color: red">*</font></label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="hotelcode" ${disableHotelCode} maxlength="10" name="hotelcode"  style="text-transform:uppercase" value="${requestScope['hotelcode']}" >  
                                    <input type="text" class="hidden" id="temphotelcode"  maxlength="10" name="temphotelcode" style="text-transform:uppercase" value="${requestScope['hotelcode']}" >
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fromcity">Name<font style="color: red">*</font></label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" id="hotelname" maxlength="50" name="hotelname" style="text-transform:uppercase" value="${requestScope['hotelname']}" >  
                                </div>
                            </div>
                        </div>

                    </div>
                    <!--List City-->
                    <select class="hidden" id="select-list-city">
                        <c:forEach var="pass" items="${cityList}" varStatus="status">
                            <!--<input type="text" class="hidden" id="row-city-${status.count}-id" name="row-city-${status.count}-id" value="${pass.id}" />-->                           
                            <c:set var="select" value="" />
                            <c:if test="${pass.id == requestScope['city']}">
                                <c:set var="select" value="selected" />
                            </c:if>
                            <option value="<c:out value="${pass.id}" />" ${select}  ><c:out value="${pass.name}" /></option>    
                        </c:forEach>
                    </select>
                    <script>
                        var cityName = [];
                    </script>
                    <c:forEach var="cityList" items="${cityList}" >
                        <script>
                            cityName.push({value: "${cityList.id}", label: "${cityList.name}"});        
                        </script>
                    </c:forEach>   
                                
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-1 control-label" for="fromcity" style="width: 65px;text-align: left">City</label>                               
                                <div class="col-sm-6">
                                    <!--New Version-->
                                    <select id="city" name="city" class="selectize not-full form-control">
                                        <option value="">- - City - -</option>
                                    </select>
<!--                                    Old version
                                    <select name="city" id="city"  class="form-control  city-multiple" >
                                        <option value="0"  selected="selected"></option>
                                        <c:forEach var="table" items="${cityList}">
                                            <c:set var="select" value="" />
                                            <c:if test="${table.id == requestScope['city']}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option value="<c:out value="${table.id}" />" ${select}><c:out value="${table.name}" /></option>   
                                        </c:forEach>
                                    </select>-->
                                </div>
                            </div>
                        </div>
                    
                        
                    <!--List Country-->
                    <select class="hidden" id="select-list-country">
                        <c:forEach var="pass" items="${countryList}" varStatus="status">
                            <!--<input type="text" class="hidden" id="row-city-${status.count}-id" name="row-city-${status.count}-id" value="${pass.id}" />-->                                
                            <c:set var="select" value="" />
                            <c:if test="${pass.id == requestScope['country']}">
                                <c:set var="select" value="selected" />
                            </c:if>
                            <option value="<c:out value="${pass.id}" />" ${select}  ><c:out value="${pass.name}" /></option>                    
                        </c:forEach>
                    </select>
                    <script>
                        var cityCountry = [];
                    </script>
                    <c:forEach var="countryList" items="${cityList}" >
                        <script>
                            cityCountry.push({value: "${countryList.id}", label: "${countryList.name}"});        
                        </script>
                    </c:forEach>       
      
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-1 control-label" for="fromcountry" style="width: 64px;text-align: left">Country</label>
                                <div class="col-sm-6">
                                    <!--New Version-->
                                    <select id="country" name="country" class="selectize not-full form-control">
                                        <option value="">- - Country - -</option>
                                        <c:forEach var="table" items="${countryList}">
                                            <c:set var="select" value="" />
                                            <c:if test="${table.id == requestScope['country']}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option value="<c:out value="${table.id}" />" ${select}  ><c:out value="${table.name}" /></option>            
                                        </c:forEach>
                                    </select>
<!--                                    Old Version
                                    <select name="country" id="country"  class="form-control country-multiple">
                                        <option value="0"  selected="selected"> </option>
                                        <c:forEach var="table" items="${countryList}">
                                            <c:set var="select" value="" />
                                            <c:if test="${table.id == requestScope['country']}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option value="<c:out value="${table.id}" />" ${select}  ><c:out value="${table.name}" /></option>            
                                        </c:forEach>
                                    </select>-->
                                </div>   
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fromAdd" >Telno</label>
                                <div class="col-sm-8">  
                                    <input type="text" class="form-control" id="Telno" maxlength="100" name="Telno" value="${requestScope['Telno']}"  >  
                                </div>   
                            </div>
                        </div> 
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fromAdd">Fax</label>
                                <div class="col-sm-8">  
                                    <input type="text" class="form-control" id="Telno" maxlength="100" name="Fax"  value="${requestScope['Fax']}" >  
                                </div>   
                            </div>
                        </div> 
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fromEmail">Email</label>
                                <div class="col-sm-8">  
                                    <input type="text" class="form-control" id="Email" maxlength="50" name="Email" value="${requestScope['Email']}"  >  
                                </div>   
                            </div>
                        </div> 
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fromWeb">Web</label>
                                <div class="col-sm-8">  
                                    <input type="text" class="form-control" maxlength="50" id="Web" name="Web" value="${requestScope['Web']}"  >  
                                </div>   
                            </div>
                        </div> 
                    </div>   

                    <div class="row">
                        <div class="col-md-11">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fromdes">Description</label>
                                <div class="col-sm-10">  
                                    <textarea name="description" id="description" maxlength="255"  class="form-control" rows="3">${requestScope['description']}</textarea>
                                </div>   
                            </div>
                        </div>

                    </div>

                    <div class="row">
                        <div class="col-md-11">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fromAdd">Address</label>
                                <div class="col-sm-10">  
                                    <textarea name="address" id="address" class="form-control" maxlength="255" style="text-transform:uppercase" rows="3">${requestScope['address'] }</textarea>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="HotelID" name="HotelID" value="${requestScope['HotelID']}">
                    <input type="hidden" id="actionIUP" name="action" value="save">

                </div>

            </div>    

        </div>

        <div class="row">
                 <div class="col-xs-12 text-center">
                        <button id="saveHotel" name="saveHotel" type="submit" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                 </div>
        </div>

    </form>

</div>
<c:if test="${! empty requestScope['hotelLap']}">
    <script language="javascript">
        $('#textAlertLap').show();
    </script>
</c:if>