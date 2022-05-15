const main_div = document.getElementById("main_div");
let restaurante = 0;
let id = 0;

async function mostrarRestaurantes(){
    fetch("/api/restaurants")
    .then(response => response.json())
    .then(data => {
        console.log(data);
        console.log(data.length);

        if (data.length > 0) {
          var temp = '';
          document.getElementById('data_res').innerHTML = temp; //vaciamos las rows previas
          for (var i = 0; i < data.length; i++) { // rellenamos fila por fila
            temp += '<tr>';
            temp += '<td>' + data[i].id + '</td>';
            temp += '<td>' + data[i].restName + '</td>';
            temp += '<td>' + data[i].direccion + '</td>';
            temp += '<td class="text-center" id="boton"></td></tr>';

            document.getElementById('data_res').innerHTML += temp; // metemos las filas ya modificadas

            var boton = document.createElement("a");
            boton.className = "btn btn-outline-dark mt-auto py-1 px-4";
            boton.id = data[i].id;
            boton.onclick = "restaurante="+data[i].id;
            boton.innerHTML = "Ver";

            boton.addEventListener("click",function(event){
                mostrarPlatos();
            })

            document.getElementById('boton').appendChild(boton);
            }
        }
    })
}

function mostrarPlatos() {
    url = "/api/platos/"+localStorage.getItem('restaurante');
    fetch(url)
    .then(response => response.json())
    .then(data_p => {
        console.log(data_p);
        console.log(data_p.length);

        if (data_p.length > 0) {
          var temp2 = '';
          document.getElementById('tabla_platos').style.visibility = "visible";
          document.getElementById('data_platos').innerHTML = temp2; //vaciamos las rows previas
          for (var i = 0; i < data_p.length; i++) { // rellenamos fila por fila
            var imagen = data_p[i].foto;
            switch(data_p[i].seccion) {
                case 1:
                var seccion = 'Primero';

                case 2:
                var seccion = 'Segundo';

                case 3:
                var seccion = 'Postre';
            }
            temp2 += '<tr>';
            temp2 += '<td><img src="'+ imagen +'" height="120px" width="auto"></td>';
            temp2 += '<td>' + data_p[i].id_plato + '</td>';
            temp2 += '<td>' + data_p[i].nombre + '</td>';
            temp2 += '<td>' + data_p[i].descripcion + '</td>';
            temp2 += '<td>' + seccion + '</td>';
            temp2 += '<td>' + data_p[i].precio + '</td>';
            temp += '<td class="text-center" id="botonModify"></td>';
            temp += '<td class="text-center" id="botonDelete"></td></tr>';
            temp2 += '<button class="btn btn-danger btn-outline-dark mt-auto py-1 px-3" id="delete-' + data_p[i].id_plato +'">Eliminar</button></td></tr>';

            var botonModify = document.createElement("a");
            botonModify.className = "btn btn-outline-dark mt-auto py-1 px-3";
            botonModify.id = data_p[i].id_plato;
            botonModify.onclick = "id="+data_p[i].id_plato;
            botonModify.innerHTML = "Modificar";

            botonModify.addEventListener("click",function(event){
                location.reload();
            })

            document.getElementById('botonModify').appendChild(botonModify);

            var botonDelete = document.createElement("a");
            botonDelete.className = "btn btn-outline-dark mt-auto py-1 px-3";
            botonDelete.id = data_p[i].id_plato;
            botonDelete.onclick = "id="+data_p[i].id_plato;
            botonDelete.innerHTML = "Eliminar";

            botonDelete.addEventListener("click",function(event){
                console.log("dentro");
                borrarPlato();
            })

            document.getElementById('botonDelete').appendChild(botonDelete);
        }
         document.getElementById('data_platos').innerHTML = temp2; // metemos las filas ya modificadas

        }
    })
}


async function borrarPlato(){
    let request = await fetch("/api/platos/"+id,{
        method: "DELETE",
        credentials: "same-origin",
        headers: {
            "Content-type": "application/json"
        },
        body: name,
        datatype: "json",
    }).catch(console.error);

    if (request.ok){
        console.log(await request.json());
        location.reload();
    }
}

document.addEventListener('DOMContentLoaded',mostrarRestaurantes())