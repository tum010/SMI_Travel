<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--  script type="text/javascript" src="js/DefineVar.js"></script>-->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="result" value="${requestScope['result']}" />
<c:set var="vat" value="${requestScope['form'].vat}" />
<c:set var="bankChart" value="${requestScope['form'].bankchart}" />
<c:set var="withholdingTax" value="${requestScope['form'].withholdingtax}" />
<section class="content-header">
	<h1>Master : Define Variable</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Master</a></li>
		<li class="active"><a href="#">Define Variable</a></li>
	</ol>
</section>
<div class="container" style="padding-top: 15px; padding-left: 5px;">
	<div class="row">
		<c:choose>
			<c:when test="${result == 'saved'}">
				<!--Alert Save and Update-->
				<div id="textAlertDivSave"  class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Save Success!</strong>
				</div>
			</c:when>
			<c:when test="${result == 'fail'}">
				<div id="textAlertDivNotSave"  class="alert alert-success alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>Save Unsuccess!</strong>
				</div>
			</c:when>
		</c:choose>
	</div>
	<div class="col-sm-3"></div>
	<div class="col-sm-6">
		<form action="DefineVar.smi" method="post" id="DefineVarForm" name="DefineVarForm" role="form">
			<div class="panel panel-default">
				<div class="panel-heading">
					<label class="control-label">Define Variable</lable>
				</div>
				<div class="panel-body">
					<div class="row" style="padding-left: 0px">
						<div class="col-xs-2 text-right" style="padding-left: 10px; width: 130px;">
							<label class="control-label">Vat<span style="color: red;"></span></label>
						</div>
						<div class="col-md-2 form-group text-left" style="padding-left: 0px; padding-right: 0px; width: 150px;">
							<div class="col-sm-12">
								<input style="width: 100px;" class="form-control text-right" onkeyup="insertCommas(this)" type="text" class="form-control" id="vat" name="vat" value="${vat}" />
							</div>
						</div>
					</div>
					<div class="row" style="padding-left: 0px">
						<div class="col-xs-2 text-right" style="padding-left: 1px; width: 130px;">
							<label class="control-label">Bank charge</label>
						</div>
						<div class="col-md-2 form-group text-left" style="padding-left: 0px; padding-right: 0px; width: 150px;">
							<div class="col-sm-12">
								<input style="width: 100px;" class="form-control text-right" onkeyup="insertCommas(this)" type="text" class="form-control" id="bankChart" name="bankChart" value="${bankChart}" />
							</div>
						</div>
					</div>
					<div class="row" style="padding-left: 0px">
						<div class="col-xs-2 text-right" style="padding-left: 10px; width: 130px;">
							<label class="control-label">Withholding tax</lable>
						</div>
						<div class="col-md-2 form-group text-left" style="padding-left: 0px; padding-right: 0px; width: 150px;">
							<div class="col-sm-12">
								<input style="width: 100px;" class="form-control text-right" onkeyup="insertCommas(this)" type="text" class="form-control" id="withholdingTax" name="withholdingTax" value="${withholdingTax}" />
							</div>
						</div>
					</div>
					<div class="row" style="padding-left: 25px; padding-top: 10px;">
						<div class="col-xs-4 text-right"></div>
						<div class="col-xs-4 text-right" style="width: 100px;">
							<input type="hidden" name="temp" id="temp" value="1"> <input type="hidden" name="action" id="action" value="">
							<button type="submit" id="ButtonSave" name="ButtonSave" onclick="saveAction();" class="btn btn-success">
								<i class="fa fa-save"></i> Save
							</button>
						</div>
						<div class="col-xs-4 text-right"></div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="col-sm-3"></div>
</div>
<script>
        $(document).ready(function() {
            $(".money").mask('000,000,000.00', {reverse: true});
            
            setFormatCurrency();
            
            $("#withholdingTax").focusout(function(){
                setFormatCurrency();
            }); 
            $("#bankChart").focusout(function(){
                setFormatCurrency();
            }); 
            $("#vat").focusout(function(){
                setFormatCurrency();
            }); 
        });
        
        function setFormatCurrency(){
            
            var withholdingTax = replaceAll(",","",$('#withholdingTax').val()); 
            if (withholdingTax == ""){
                withholdingTax = 0;
            }
            withholdingTax = parseFloat(withholdingTax); 
            document.getElementById("withholdingTax").value = formatNumber(withholdingTax);
            
            var bankChart = replaceAll(",","",$('#bankChart').val()); 
            if (bankChart == ""){
                bankChart = 0;
            }
            bankChart = parseFloat(bankChart); 
            document.getElementById("bankChart").value = formatNumber(bankChart);
            
            var vat = replaceAll(",","",$('#vat').val()); 
            if (vat == ""){
                vat = 0;
            }
            vat = parseFloat(vat); 
            document.getElementById("vat").value = formatNumber(vat);
    
        }
        
        function insertCommas(nField){
            if (/^0/.test(nField.value)){
                nField.value = nField.value.substring(0,1);
            }
            if (Number(nField.value.replace(/,/g,""))){
                var tmp = nField.value.replace(/,/g,"");
                tmp = tmp.toString().split('').reverse().join('').replace(/(\d{3})/g,'$1,').split('').reverse().join('').replace(/^,/,'');
                if (/\./g.test(tmp)){
                    tmp = tmp.split(".");
                    tmp[1] = tmp[1].replace(/\,/g,"").replace(/ /,"");
                    nField.value = tmp[0]+"."+tmp[1]
                }else{
                    nField.value = tmp.replace(/ /,"");
                } 
            }else{
                nField.value = nField.value.replace(/[^\d\,\.]/g,"").replace(/ /,"");
            }
        }
        
        function replaceAll(find, replace, str) {
            return str.replace(new RegExp(find, 'g'), replace);
        }

        function formatNumber(num) {
            return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g,"$1,")
        } 

	function saveAction() {
		console.log('Save action');
		$('#action').val('Save');
		$('#DefineVarForm').submit();
	}

	$(function() {
            
                
		$('#DefineVarForm').bootstrapValidator({
			container : 'tooltip',
			excluded : [ ':disabled' ],
			feedbackIcons : {
				valid : 'uk-icon-check',
				invalid : 'uk-icon-times',
				validating : 'uk-icon-refresh'
			},
			fields : {
				vat : {
					validators : {
						notEmpty : {
							message : 'Vat is required'
						}
					}
				},
				bankChart : {
					validators : {
						notEmpty : {
							message : 'Bank Chart is required'
						}
					}
				},
				withholdingTax : {
					validators : {
						notEmpty : {
							message : 'Withholding Tax is required'
						}
					}
				}
			}
		}).on('success.field.bv', function(e, data) {
			if (data.bv.isValid()) {
				data.bv.disableSubmitButtons(false);
			}
		});
	});
</script>
