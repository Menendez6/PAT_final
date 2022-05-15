const nombre = document.getElementById('nombre');
const foto = document.getElementById('foto');
const desc = document.getElementById('desc');
const seccion = document.getElementById('seccion');
const precio = document.getElementById('precio');
const error = document.getElementById('error');
const form = document.getElementById('add');
let id_max = -1;

function calcularID() {
    let url = "/api/platos/"+localStorage.getItem('restaurante_admin');
        fetch(url)
            .then(response => response.json())
            .then(data => {

                id_max = data[0].id_plato;
                for(let i=1;i<data.length;i++){
                    if (data[i].id_plato > id_max){
                        id_max = data[i].id_plato;
                    }
                }
            })
}

form.addEventListener("click", function (event) {
	// stop form submission
	event.preventDefault();
    if (validateForm()){
        if(id_max!=-1) {
            id_max++;
            mandarInfo();
        }
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
    console.log(desc.value);
    let request = await fetch("api/platos/add",{
        method: "POST",
        credentials: "same-origin",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            id_rest: localStorage.getItem('restaurante_admin'),
            id_plato: id_max,
            nombre: nombre.value,
            foto: foto.value,
            descripcion: desc.value,
            precio: precio.value,
            seccion: seccion.value
        }),
        datatype: "json",
    }).catch(console.error);

    if (request.ok){
    	//console.log(await request.json()); //no va correctamente
        location.reload();
    }
}

document.addEventListener('DOMContentLoaded',calcularID());
