/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 var nombre = "Elias";
 var edad = 30;
 var casado = false;
 
 alert(nombre);
 
 
 //declaracion de un arreglo
 var nombres = ["mauricio", "rene", "daniel", "marcelo"];
 
 for (i = 0; i < nombres.length; i++) {
 console.log(nombres[i]);
 }
 
 nombres.forEach(function (item) {
 console.log(item);
 });
 
 var personas =[
 {
 "nombre": "ramiro",
 "apellido": "lopez",
 "edad": 20,
 "rut": "1-9"
 
 },
 {
 "nombre": "maria",
 "apellido": "sanzibar",
 "edad": 18,
 "rut": "1-6"
 
 },
 {
 "nombre": "bryan",
 "apellido": "gonzalez",
 "edad": 100,
 "rut": "1-9"
 
 },
 {
 "nombre": "rosa",
 "apellido": "espinoza",
 "edad": 33,
 "rut": "5-1"
 
 }
 ]
 
 console.log(personas[1].nombre); 
 
 personas.forEach(function(item){
 console.log("--------------"); 
 console.log(item.nombre); 
 console.log(item.apellido);
 console.log(item.edad);
 console.log(item.rut);
 console.log("--------------");
 });
 
 */

//verificar si la pagina web esta cargada correctamente y completamente

$(document).ready(function () {
    $("#cboFamilia").change(function () {
        console.log("se ha cambiado el elemento");

        var familiaId = $("#cboFamilia").val();
        if (familiaId != "") {
            console.log(familiaId);

            //ajax que no se que es
            //nos conectamos con el servlet utilzando ajax
            //usas la url del servlet
            //{"nombreVariable a usar en el servlet: valor"}
            $.get("buscar-razas", {"familiaId": familiaId}, function (respuesta) {
                console.log(respuesta);
                $("#cboRaza").html(respuesta);
            });
        }
        //$.post()

    });
//document.getElementById("cboFamilia") 
});


