/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function DeleteOther(id,code){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete booking other : " + code + " ?";
}

function EnableOther(id,code){
    var OtherID = document.getElementById('OtherID');
    OtherID.value = id;
    document.getElementById('enableCode').innerHTML = "Are you sure to enable booking other : " + code + " ?";
}

function Enable() {
    var action = document.getElementById('action');
    action.value = 'enable';
    document.getElementById('OtherForm').submit();
}

function Delete() {
    var action = document.getElementById('action');
    action.value = 'delete';
    document.getElementById('OtherForm').submit();
}