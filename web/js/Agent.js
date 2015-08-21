/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */  

 
function searchAction() {
    var action = document.getElementById('Action');
    action.value = 'search';
    document.getElementById('AgentForm').submit();
}

function DeleteAgent(id, name) {

    var agentID = document.getElementById('agentID');
    agentID.value = id;
    document.getElementById('delCode').innerHTML = "Are you sure to delete agent : " + name + " ?";


}

function Delete() {
    var action = document.getElementById('Action');
    action.value = 'delete';
    document.getElementById('AgentForm').submit();
}