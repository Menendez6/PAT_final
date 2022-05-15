const nombre = document.getElementById('nombre');
const foto = document.getElementById('foto');
const desc = document.getElementById('desc');
const seccion = document.getElementById('seccion');
const precio = document.getElementById('precio');
const error = document.getElementById('error');
var id_plato = localStorage.getItem('id_plato');
const form = document.getElementById('update');

form.addEventListener("click", function (event) {
	// stop form submission
	event.preventDefault();
    if (validateForm()){
        mandarInfo();
    }
});

function validateForm(){

    if (nombre.value.trim() === ""){
        error.style.visibility = "visible";
        return false;
    }

    if (precio.value.trim() === ""){
        error.style.visibility = "visible";
        return false;
    }

    return true;
}

nombre.addEventListener("input", function (event) {

    error.style.visibility = "hidden";

});

precio.addEventListener("input", function (event) {

    error.style.visibility = "hidden";

});

async function mandarInfo(){
    let request = await fetch("api/platos/update/"+id_plato,{
        method: "POST",
        credentials: "same-origin",
        headers: {
            "Content-type": "application/json"
            "responseType" : 'text',
        },
        body: JSON.stringify({
            id_plato: id_plato, 
            nombre: nombre.value,
            foto: foto.value,
            descripcion: desc.value,
            precio: precio.value,
            seccion: seccion.value
        }),
        datatype: "json",
    }).catch(console.error);

    if (request.ok){
    	//console.log(await request.json()); //no va
        location.href = "admin_menu.html";
    }
}

function loadPlato() {
	document.getElementById('id').innerHTML = localStorage.getItem('id_plato');
	document.getElementById('foto').value = localStorage.getItem('foto_plato');
	document.getElementById('nombre').value = localStorage.getItem('nombre_plato');
	document.getElementById('desc').value = localStorage.getItem('desc_plato');
	switch(localStorage.getItem('seccion_plato')) {
		case "Primero":
			document.getElementById('seccion').value = 1;
		break;

		case "Segundo":
			document.getElementById('seccion').value = 2;
		break;

		case "Postre":
			document.getElementById('seccion').value = 3;
		break;

	}

	document.getElementById('precio').value = localStorage.getItem('precio_plato');
}

document.addEventListener('DOMContentLoaded',loadPlato());
