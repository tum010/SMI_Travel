/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var validatcode = /^[a-z0-9\s/-]+$()/i;
var validatename = /^[a-z0-9\u0E01-\u0E5B (),./-/&]+$/i;

function  CheckCharecter(checkString) {
    if (checkString != "") {
        if (/[^A-Za-z0-9\- \d()./]/.test(checkString)) {
            return (false);
        }
    }
    return true;
}

function setNumberFormat(arg){
    var id = arg.getAttribute('id');
    var value =  $('#'+id).val();
    if(!isNaN(value)){
         $('#'+id).val(parseInt(value));
    }
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}


function reloadData(response, targetId, haveAll, action) {
    var resxml = response;
    if (action == 'text') {
      var target = document.getElementById(targetId);
      target.value = response
    }else if (action == 'inner') {
      var target = document.getElementById(targetId);
      target.innerHTML = response
    }
    else {
        var objCombo = document.getElementById(targetId);
        var objComboLength = objCombo.options.length;
        //remove old value
        for (var i = 0; i < objComboLength; i++) {
            objCombo.remove(0);
        }

        var optionArr = resxml.getElementsByTagName('option');
        var optionArrLen = optionArr.length;

        if (null != haveAll)
        {
            var newOption = document.createElement("option");
            newOption.text = "ALL";
            newOption.value = "";
            objCombo.add(newOption);
        }
        //set new data
        for (var j = 0; j < optionArr.length; j++) {
            var optionLabel = optionArr[j].childNodes[0].childNodes[0].nodeValue;
            var optionValue = optionArr[j].childNodes[1].childNodes[0].nodeValue;
            var newOption = document.createElement("option");
            newOption.text = optionLabel;
            newOption.value = optionValue;
            objCombo.add(newOption);
        }

        var actionArr = resxml.getElementsByTagName('action');
        var actionArrLen = actionArr.length;
        if (actionArrLen > 0) {
            var actionType = actionArr[0].childNodes[0].nodeValue;
            if (actionType == "select")
            {
                selectData();
            }
        }
    }
}


