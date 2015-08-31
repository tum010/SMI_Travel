/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    
    $("#CodeS,#NameS").keyup(function (event) {
        if (event.keyCode === 13) {
            searchAction();
        }
    });
});

function searchAction(){
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('ProductForm').submit();     
}

function DeleteProduct(id,name){
    var ProductID = document.getElementById('ProductID');
    ProductID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete product : " + name + " ?";
}

function Delete() {
    var action = document.getElementById('Action');
    action.value = 'delete';
    document.getElementById('ProductForm').submit();
}