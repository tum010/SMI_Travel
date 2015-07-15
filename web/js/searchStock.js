/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function searchAction() {
    //alert("OK");
    var action = document.getElementById('action');
    action.value = 'search';
    document.getElementById('SearchStockForm').submit();
}
 function viewStockDetailAction(id){
    var action = document.getElementById('action');
    var stockIdView = document.getElementById('stockIdView');
    action.value = 'view';
    stockIdView.value = id;
    document.getElementById('SearchStockForm').submit();
 }