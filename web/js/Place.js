/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function searchAction(){ 
   
     var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('SearchPlace').submit();            
 

}

function save(){
    var Placename = document.getElementById('Placename');
    var Placestatus =  document.getElementById('Placestatus');
    if( Placename.value == ''){
        alert('please fill in place name');
    }else if( Placestatus.value == ''){
        alert('please select status');
    }else if(!(CheckCharecter(Placename.value))){
         alert('data contains illegal characters.');
    }else{
        document.getElementById('Placeform').submit();
    }              
}

function addaction(){
    document.getElementById('Placename').value='';
    document.getElementById('Placestatus').value='1';
    document.getElementById('actionIUP').value='add';
}

function EditPlace(id,name,status){
    
    document.getElementById('Placename').value=name;
    document.getElementById('Placestatus').value=status;
    document.getElementById('PlaceID').value=id;
    document.getElementById('actionIUP').value='update';
    
}

function DeletePlace(id,name){
    
    var PlaceId = document.getElementById('PlaceID');
    PlaceId.value = id;
    
    document.getElementById('delCode').innerHTML = "Are you sure to delete place : " + name + " ?";
}

function Delete() {
    var action = document.getElementById('actionIUP');
    action.value = 'delete';
    document.getElementById('Placeform').submit();
}

