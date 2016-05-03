<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/MProductDetail.js"></script> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="js/jquery.mask.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.js"></script>
<script type="text/javascript" src="js/jquery.inputmask.numeric.extensions.js"></script>

<c:set var="producttypeList" value="${requestScope['producttype_list']}" />
<c:set var="productPriceList" value="${requestScope['productPrice_list']}" />
<c:set var="displayAddprice" value="" />
<c:set var="displayTablePrice" value="" />
<c:set var="disableProductCode" value="${requestScope['disableProductCode']}" />


<c:if test="${!empty requestScope['Oldproduct']}">
    <c:set var="displayAddprice" value="style='display:none;'" />
    <c:set var="displayTablePrice" value="display:none;" />
</c:if>


<section class="content-header"  >
    <h4>
        <b>Master : Product</b>
    </h4>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>          
        <li><a href="Product.smi">Product MA</a></li>
        <li class="active"><a href="#">Product Detail</a></li>
    </ol>
</section>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        var table = $('#ProductCostList').dataTable({bJQueryUI: true,
            "sPaginationType": "full_numbers",
            "bAutoWidth": false,
            "bFilter": false,
            "bInfo": false
        });

        $('#ProductCostList tbody').on('click', 'tr', function() {
            if ($(this).hasClass('row_selected')) {
                $(this).removeClass('row_selected');
            }
            else {
                table.$('tr.row_selected').removeClass('row_selected');
                $(this).addClass('row_selected');
            }
        });

        $('.date').datetimepicker();
        $('span').click(function() {
            var position = $(this).offset();
            console.log("positon :" + position.top);
            $(".bootstrap-datetimepicker-widget").css("top", position.top + 30);

        });
        
        
        
         $(".money").mask('000,000,000,000,000,000', {reverse: true});
         setformat();
    });
    function setformat() {
                $('#ProductCostList tr td.moneyformat').each(function() {
                    var innerHTML = $(this).html();
                    $(this).html(numberWithCommas($(this).html()));
                });
            }
    
</script>
<div class ="container"  style="padding-top: 15px;"> 
    <form action="MProductDetail.smi" method="post" id="ProductDetailForm" role="form" class="form-horizontal">
        <div class="col-md-8 col-xs-offset-2">
            <!--Alert Save --> 
            <div id="textAlertDivSave"  style="display:none;" class="alert alert-success alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Success!</strong> 
            </div>
            <!--Alert Not Save --> 
            <div id="textAlertDivNotSave"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Save Not Success!</strong> 
            </div>
            <!-- Alert Uni -->
            <div id="textAlertLap"  style="display:none;" class="alert alert-danger" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>Product Code already exist!</strong> 
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">Detail</div>
                <div class="panel-body">
                        <div class="row">
                        <div class="col-md-12 form-group">
                            <div class="col-md-6 col-md-offset-6 text-right" style="width: 415px">
                                <a id="ButtonBack" name="ButtonBack" href="Product.smi" class="btn btn-primary"><i class="fa fa-arrow-left"></i> Back</a>
                            </div>
                        </div>
                        </div>

                    <div class="row" >
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2   control-label" for="codeProduct">Code<font style="color: red">*</font></label>
                                <div class="col-sm-10">
                                    <input type="text" maxlength="20" class="form-control" ${disableProductCode} id="code" name="code" style="text-transform:uppercase" value="${requestScope['code']}" >  
                                    <input type="text" maxlength="20" class="hidden"  id="tempcode" name="tempcode" value="${requestScope['code']}" >  
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="nameProduct">Name<font style="color: red">*</font></label>
                                <div class="col-sm-10">
                                    <c:set var="validate" value="" />
                                    <c:if test="${requestScope['stock'] == 'success'}">
                                        <c:set var="validate" value="onkeyup=\"validateNameProduct()\"" />
                                    </c:if>
                                    <input type="text" maxlength="100" class="form-control" id="name" name="name" style="text-transform:uppercase" value="${requestScope['name']}" ${validate}>  
                                </div>
                            </div>
                        </div>
                    </div> 

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="nameProduct">Type</label>
                                <div class="col-sm-10"> 
                                    <select name="producttype" id="producttype"  class="form-control">
                                        <option value=""  selected="selected">------------------------select----------------------------</option>
                                        <c:forEach var="table" items="${producttypeList}">
                                            <c:set var="select" value="" />
                                            <c:if test="${table.id == requestScope['producttype']}">
                                                <c:set var="select" value="selected" />
                                            </c:if>
                                            <option value="<c:out value="${table.id}" />" ${select}><c:out value="${table.name}" /></option>   
                                        </c:forEach>
                                    </select>
                                </div>                               
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="nameProduct">Stock</label>
                                <div class="col-sm-1" style="width: 60px">
                                    <c:set var="check" value="" />
                                        <c:if test="${requestScope['isStock'] == '1'}">
                                            <c:set var="check" value="checked" />
                                        </c:if>
                                    <input class="form-control" type="checkbox" id="isStock" name="isStock" value="1" ${check}>
                                    <input class="form-control" type="hidden" id="stock" name="stock" value="${requestScope['stock']}">
                                </div>            
                            </div>
                        </div>    
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fromdes">Detail</label>
                                <div class="col-sm-10">  
                                    <textarea name="description" maxlength="255" id="description" class="form-control" rows="3">${requestScope['description']}</textarea>
                                </div>   
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="fromRemark">Remark</label>
                                <div class="col-sm-10">  
                                    <textarea name="remark" id="remark" maxlength="255" class="form-control" rows="3">${requestScope['remark']}</textarea>
                                </div>   
                            </div>
                        </div>
                    </div>
                    <div class="row ">  
                        <div class="col-md-3 col-md-offset-8" style="padding-left:  155px">
                            <button id="btnAdd"  type="button" ${requestScope['disabledPrice']} class="btn btn-success" onclick="addaction()"  ${displayAddprice}  data-toggle="modal" data-target="#ProductModal">
                                <span id="spanAdd" class="glyphicon glyphicon-plus"></span>AddPrice
                            </button>
                        </div>               
                    </div>
                    <div class="row" style="padding-left: 15px;${displayTablePrice}" >    
                        <div class="col-md-12"> 
                            <table id="ProductCostList" class="display" cellspacing="0"  >
                                <thead>
                                    <tr class="datatable-header">
                                        <th style="width:12%" rowspan="2">Effective from</th>
                                        <th style="width:12%" rowspan="2">Effective to</th>
                                        <th style="width:30%" colspan="3" >Cost</th>
                                        <th style="width:30%" colspan="3" >Price</th>
                                        <th rowspan="2" >By</th>
                                        <th style="width:12%" rowspan="2">Action</th>
                                    </tr>
                                    <tr class="datatable-header">
                                        <th>Adult</th>
                                        <th>Child</th>
                                        <th>Infant</th>
                                        <th>Adult</th>
                                        <th>Child</th>
                                        <th>Infant</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="table" items="${productPriceList}" varStatus="dataStatus">
                                        <tr>
                                            <td><fmt:formatDate value="${table.effectiveFrom}" var="effectiveFrom" pattern="dd-MM-yyyy" /><c:out value="${effectiveFrom}" /> </td>
                                            <td><fmt:formatDate value="${table.effectiveTo}" var="effectiveTo" pattern="dd-MM-yyyy" /><c:out value="${effectiveTo}" /> </td>
                                            <td class="tdcenter moneyformat"><c:out value="${table.adCost}" /> </td>
                                            <td class="tdcenter moneyformat"><c:out value="${table.chCost}" /> </td>
                                            <td class="tdcenter moneyformat"><c:out value="${table.inCost}" /> </td>
                                            <td class="tdcenter moneyformat"><c:out value="${table.adPrice}" /> </td>
                                            <td class="tdcenter moneyformat"><c:out value="${table.chPrice}" /> </td>
                                            <td class="tdcenter moneyformat"><c:out value="${table.inPrice}" /> </td>
                                            <td class="tdcenter"><c:out value="${table.updateBy}" /> </td>
                                            <td>
                                            <center> 
                                                <span id="spanEdit${dataStatus.count}" class="glyphicon glyphicon-edit editicon" data-toggle="modal" data-target="#ProductModal" onclick="EditProductPrice('${table.id}', '${effectiveFrom}', '${effectiveTo}', '${table.adCost}', '${table.adPrice}', '${table.chCost}', '${table.chPrice}', '${table.inCost}', '${table.inPrice}')"></span>
                                                <span id="spanRemove${dataStatus.count}" class="glyphicon glyphicon-remove deleteicon"  onclick="DeleteProductPrice(${table.id})" data-toggle="modal" data-target="#delProductPriceModal" >  </span>
                                            </center>
                                            </td>    
                                        </tr>
                                </c:forEach>

                                </tbody> 
                            </table>    
                        </div>
                    </div>
                </div>
            </div>
        </div>

         <div class="row">
                <div class="col-xs-12 text-center">
                <c:choose>
                    <c:when test="${(requestScope['stock'] == 'success') && (requestScope['isStock'] == '1')}">
                        <button id="saveProduct" name="saveProduct" type="button" class="btn btn-success" onclick="confirmDisableStock()"><i class="fa fa-save"></i> Save </button>
                    </c:when>
                    <c:otherwise>    
                        <button id="saveProduct" name="saveProduct" type="submit" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
                    </c:otherwise>    
                </c:choose>    
                </div>
        </div>               
        <input type="hidden" id="F" name="ProductID" value="${requestScope['ProductID']}" >
        <input type="hidden" id="actionIUP" name="action" value="save">
    </form>      
</div>

<div class="modal fade" id="ProductModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" style="width: 810px;" >
        <form action="MProductDetail.smi" method="post" id="ProductPriceform" class="form-horizontal"  role="form">
            <div class="modal-content ">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title">Master Product : Price</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                    <div class ="col-md-6" style="padding-right:  68px">
                        <label for="effectivefrom" class="col-sm-5 control-label" >Effective from <font style="color: red">*</font></label>
                        <div class=' col-sm-6 input-group date' id='effectivefromClass'>
                            <input type='text' class="form-control datemask"  id="effectivefrom" name="effectivefrom" data-date-format="DD-MM-YYYY"  placeholder="DD-MM-YYYY" value="" />
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                    <div class ="col-md-6" style="padding-left: 37px;">
                        <label for="effectiveto" class="col-sm-4 control-label" >Effective to <font style="color: red">*</font></label>
                        <div class=' col-sm-6 input-group date' id='effectivetoClass'>
                            <input type='text' class="form-control datemask"  id="effectiveto" name="effectiveto" data-date-format="DD-MM-YYYY"  placeholder="DD-MM-YYYY" value="" />
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>   
                    </div>

                    <div class = "row">
                        <div class="form-group">
                            <div class ="col-md-6">
                                <label for="cost" class="col-sm-4 col-md-offset-3 control-label" >Cost</label>              
                            </div>
                            <div class ="col-md-6">
                                <label for="price" class="col-sm-4 col-md-offset-3 control-label" >Price</label>
                            </div>
                        </div>
                    </div>

                    <div class = "row">
                        <div class="form-group">
                            <div class ="col-md-6">
                                <label for="AD_cost" class="col-sm-4 control-label" >Adult</label>
                                <div class="col-sm-6"> 
                                    <input type="text" class="form-control decimal" id="ADcost" name="ADcost" value="0" >
                                </div>
                            </div>
                            <div class ="col-md-6">
                                <label for="effectiveto" class="col-sm-4 control-label" >Adult</label>
                                <div class="col-sm-6">   
                                    <input type="text" class="form-control decimal"  id="ADprice" name="ADprice" value="0" > 
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class = "row">
                        <div class="form-group">
                            <div class ="col-md-6">
                                <label for="AD_cost" class="col-sm-4 control-label" >Child</label>
                                <div class="col-sm-6"> 
                                    <input type="text" class="form-control decimal" id="CHcost" name="CHcost" value="0" >
                                </div>
                            </div>
                            <div class ="col-md-6">
                                <label for="effectiveto" class="col-sm-4 control-label" >Child</label>
                                <div class="col-sm-6">   
                                    <input type="text" class="form-control decimal"  id="CHprice" name="CHprice" value="0" > 
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class = "row">
                        <div class="form-group">
                            <div class ="col-md-6">
                                <label for="AD_cost" class="col-sm-4 control-label" >Infant</label>
                                <div class="col-sm-6"> 
                                    <input type="text" class="form-control decimal" id="INcost" name="INcost" value="0" >
                                </div>
                            </div>
                            <div class ="col-md-6">
                                <label for="effectiveto" class="col-sm-4 control-label" >Infant</label>
                                <div class="col-sm-6">   
                                    <input type="text" class="form-control decimal"  id="INprice" name="INprice" value="0" > 
                                </div>
                            </div>
                        </div>
                    </div>

                    <input type="hidden" id="actionprice" name="action" value="addlistprice">
                    <input type="hidden" id="PriceItemID" name="PriceItemID" >
                    <input type="hidden" id="updateBy" name="updateBy" value="ut">
                    <input type="hidden" id="ProductFormID" name="ProductID" value="${requestScope['ProductID']}" >

                </div>
                <div class="modal-footer">
                    <button type="submit" id="saveprice" name="saveprice" class="btn btn-primary">Save</button>
                    <button type="button" id="btnCloses" name="btnCloses" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="delProductPriceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Delete Product price</h4>
            </div>
            <div class="modal-body" id="delCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="Delete()" class="btn btn-danger">Delete</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="disableIsStockModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title">Disabled isStock product</h4>
            </div>
            <div class="modal-body" id="disabledCode"></div>
            <div class="modal-footer">
                <button id="btnDelete" type="button" onclick="disabledIsStock()" class="btn btn-danger">Disabled</button>
                <button id="btnDeleteClose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal --> 

<c:if test="${! empty requestScope['product_lap']}">
    <script language="javascript">
        $('#textAlertLap').show();
    </script>
</c:if>
<c:if test="${! empty requestScope['result']}">
    <c:if test="${requestScope['result'] =='save successful'}">        
        <script language="javascript">
            $('#textAlertDivSave').show();
        </script>
    </c:if>
    <c:if test="${requestScope['result'] =='save unsuccessful'}">        
        <script language="javascript">
           $('#textAlertDivNotSave').show();
        </script>
    </c:if>
</c:if>
<script type="text/javascript" charset="utf-8">


    $(document).ready(function() {    
        $(".decimal").inputmask({
            alias: "decimal",
            integerDigits: 8,
            groupSeparator: ',',
            autoGroup: true,
            digits: 2,
            allowMinus: false,
            digitsOptional: false,
            placeholder: "0"
        });
        $('.datemask').mask('00-00-0000');
    
    });
        
        
</script>