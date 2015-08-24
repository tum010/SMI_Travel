/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
$(window).keydown(function(event){
    if((event.which== 13) && (event.target.nodeName !='TEXTAREA')) {

      event.preventDefault();
      return false;
    }
  });
});
