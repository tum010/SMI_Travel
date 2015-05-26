/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function searchAction(){
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('HotelForm').submit();     
}

function DeleteHotel(id,name){
    var HotelID = document.getElementById('HotelID');
    HotelID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete hotel : " + name + " ?";
}

function Delete() {
    var action = document.getElementById('Action');
    action.value = 'delete';
    document.getElementById('HotelForm').submit();
}