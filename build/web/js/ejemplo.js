/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



var form = document.querySelector("form");
var log = document.querySelector("#log");

form.addEventListener("submit", function(event) {
  var data = new FormData(form);
  var output = "";
  for (const entry of data) {
    output = entry[0] + "=" + entry[1] + "\r";
     var preguntas=[entry[0]];
     var respuestas=[entry[1]];
  };
    for (i = 0; i < preguntas.length; i++) {
 console.log(preguntas[i]);
 }
 
  for (i = 0; i < respuestas.length; i++) {
 console.log(respuestas[i]);
 }
  
  log.innerText = output;
  event.preventDefault();
}, false);
