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
        if(value !== ""){
            $('#'+id).val(parseInt(value));
        }
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

function toWords(s){
    if(s === 0){
        var defaultWord = '';
        return  defaultWord;
    }
    var hundred = s;
    var th = ['','thousand','million', 'billion','trillion'];
    var dg = ['zero','one','two','three','four', 'five','six','seven','eight','nine']; 
    var tn = ['ten','eleven','twelve','thirteen', 'fourteen','fifteen','sixteen', 'seventeen','eighteen','nineteen']; 
    var tw = ['twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety']; 
    s = s.toString(); 
    s = s.replace(/[\, ]/g,''); 
    if (s != parseFloat(s)) return 'not a number'; 
    var x = s.indexOf('.'); 
    if (x == -1) x = s.length; if (x > 15) return 'too big'; 
    var n = s.split(''); 
    var str = ''; 
    var sk = 0; 
    for (var i=0; i < x; i++) {
        if ((x-i)%3==2) {
            if (n[i] == '1') {
                str += tn[Number(n[i+1])] + ' '; i++; sk=1;
            } else if (n[i]!=0) {
                str += tw[n[i]-2] + ' ';sk=1;}
        } else if (n[i]!=0) {
            str += dg[n[i]] +' '; 
            if ((x-i)%3==0){
                str += 'hundred ';
                if(((parseInt(hundred))%100) !== 0){
                    if(((parseInt(hundred))%10) !== 0){
                        str += 'and ';
                    } else {
                        str += 'and ';
                    }                  
                }
            }
            sk=1;
        }             
        if ((x-i)%3==1) {
            if (sk) str += th[(x-i-1)/3] + ' ';
            sk=0;
        }
    } 
    str += ' baht ';
    if (x != s.length) {
        var y = s.length; str += 'point '; 
        for (var i=x+1; i<y; i++) str += dg[n[i]] +' ';
    } else {
        str += ' only ';
    }   
    return str.replace(/\s+/g,' ').toUpperCase();
}


