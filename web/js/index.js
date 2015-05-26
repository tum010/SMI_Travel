/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function clearsession() {
    var action = document.getElementById('action');
    action.value = 'clear';
    submitForm();
}

function submitForm() {
    document.formApplication.submit();
}
