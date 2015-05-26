/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function searchAction(){ 
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchPackage').submit();     
}


function DeletePackage(id,name){
    var PackageID = document.getElementById('PackageID');
    PackageID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete Package : " + name + " ?";
    
}


function Delete() {
    var action = document.getElementById('Action');
    action.value = 'delete';
    document.getElementById('SearchPackage').submit();
}

$(document).ready(function () {
    $("#PackageCodeS,#PackageNameS").keyup(function (event) {
        if (event.keyCode === 13) {
            searchAction();
        }
    });
});