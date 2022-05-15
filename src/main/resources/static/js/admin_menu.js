let restaurante = 0;
let plato = 0;

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
            //boton.onclick = "selectRestaurante("+data[i].id+")";
            boton.innerHTML = "Ver";

            boton.addEventListener("click",function(event){
                selectRestaurante(boton.id);
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
            let seccion = data_p[i].seccion;
            switch(seccion) {
                case "1":
                seccion = 'Primero';
                break;

                case "2":
                seccion = 'Segundo';
                break;

                case "3":
                seccion = 'Postre';
                break;
            }
            temp2 = '<tr>';
            temp2 += '<td><img src="'+ imagen +'" id="foto-'+data_p[i].id_plato+'" height="120px" width="auto"></td>';
            temp2 += '<td id="id-'+data_p[i].id_plato+'">' + data_p[i].id_plato + '</td>';
            temp2 += '<td id="nombre-'+data_p[i].id_plato+'">' + data_p[i].nombre + '</td>';
            temp2 += '<td id="desc-'+data_p[i].id_plato+'">' + data_p[i].descripcion + '</td>';
            temp2 += '<td id="seccion-'+data_p[i].id_plato+'">' + seccion + '</td>';
            temp2 += '<td id="precio-'+data_p[i].id_plato+'">' + data_p[i].precio + '</td>';
            temp2 += '<td class="text-center" id="botones'+data_p[i].id_plato+'"></td></tr>';

            document.getElementById('data_platos').innerHTML += temp2;

            var botonModify = document.createElement("a");
            botonModify.className = "btn btn-outline-dark mt-auto py-1 px-3";
            botonModify.id = 'modify'+data_p[i].id_plato;
            //botonModify.onclick = "selectPlato("+data_p[i].id_plato+")";
            botonModify.innerHTML = "Modificar";

            document.getElementById('botones'+data_p[i].id_plato).appendChild(botonModify);

            document.getElementById('modify'+data_p[i].id_plato).addEventListener("click",function(event){
                selectPlato(document.getElementById('modify'+data_p[i].id_plato).id.substring(6));
                localStorage.setItem('id_plato',document.getElementById('id-'+plato).innerHTML);
                localStorage.setItem('nombre_plato',document.getElementById('nombre-'+plato).innerHTML);
                localStorage.setItem('foto_plato',document.getElementById('foto-'+plato).src);
                localStorage.setItem('desc_plato',document.getElementById('desc-'+plato).innerHTML);
                localStorage.setItem('seccion_plato',document.getElementById('seccion-'+plato).innerHTML);
                localStorage.setItem('precio_plato',document.getElementById('precio-'+plato).innerHTML);
                location.href = "mod_plato.html";
            })

            var botonDelete = document.createElement("a");
            botonDelete.className = "btn btn-danger btn-outline-dark mt-auto py-1 px-3";
            botonDelete.id = 'delete'+data_p[i].id_plato;
            //botonDelete.onclick = "selectPlato("+data_p[i].id_plato+")";
            botonDelete.innerHTML = "Eliminar";

            botonDelete.addEventListener("click",function(event){
                selectPlato(botonDelete.id.substring(6));
                console.log("dentro");
                borrarPlato();
            })

            document.getElementById('botones'+data_p[i].id_plato).appendChild(botonDelete);
          }
        }
    })
}


async function borrarPlato(){
    let request = await fetch("/api/platos/"+plato,{
        method: "DELETE",
        credentials: "same-origin",
        headers: {
            "Content-type": "application/json"
        },
        body: plato,
        datatype: "json",
    }).catch(console.error);

    if (request.ok){
        console.log(plato+" borrado");
        location.reload();
    }
}

function selectRestaurante(id_restaurante) {
    restaurante = id_restaurante;
    return;
}

function selectPlato(id_plato) {
    plato = id_plato;
    return;
}

document.addEventListener('DOMContentLoaded',mostrarRestaurantes());
