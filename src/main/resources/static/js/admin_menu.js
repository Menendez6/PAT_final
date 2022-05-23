let plato = 0;
tabla = document.getElementById('data_platos');

function mostrarPlatos() {
    limpiarStoragePlato(); //limpiamos cada vez que abrimos esta pagina
    url = "/api/platos/"+localStorage.getItem('restaurante_admin');
    fetch(url)
    .then(response => response.json())
    .then(data_p => {
        console.log(data_p);
        console.log(data_p.length);
        data_p.sort(function(a, b){
            return a.seccion - b.seccion;
        });

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

                case "4":
                seccion = 'Bebida';
                break;
            }

            img = document.createElement('img');
            img.style.height = '120px';
            img.style.width = 'auto';
            img.src = imagen;
            img.id = "foto-"+data_p[i].id_plato;

            text_mod = document.createTextNode('Modificar');
            var botonModify = document.createElement("button");
            botonModify.className = "btn btn-outline-dark mt-auto py-1 px-3";
            botonModify.id = 'modify'+data_p[i].id_plato;
            botonModify.appendChild(text_mod);

            botonModify.addEventListener("click",function(event){
                selectPlato(this.id.substring(6));
                localStorage.setItem('id_plato',document.getElementById('id-'+plato).innerHTML);
                localStorage.setItem('nombre_plato',document.getElementById('nombre-'+plato).innerHTML);
                localStorage.setItem('foto_plato',document.getElementById('foto-'+plato).src);
                localStorage.setItem('desc_plato',document.getElementById('desc-'+plato).innerHTML);
                localStorage.setItem('seccion_plato',document.getElementById('seccion-'+plato).innerHTML);
                localStorage.setItem('precio_plato',document.getElementById('precio-'+plato).innerHTML);
                location.href = "mod_plato.html";
            })

            text_del = document.createTextNode('Eliminar');
            var botonDelete = document.createElement("button");
            botonDelete.className = "btn btn-danger btn-outline-white mt-auto py-1 px-3";
            botonDelete.id = 'delete'+data_p[i].id_plato;
            botonDelete.appendChild(text_del);

            botonDelete.addEventListener("click",function(event){
                selectPlato(this.id.substring(6));
                console.log("dentro");
                borrarPlato();
            })

            var row = tabla.insertRow();

            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            cell2.id = "id-"+data_p[i].id_plato;
            var cell3 = row.insertCell(2);
            cell3.id = "nombre-"+data_p[i].id_plato;
            var cell4 = row.insertCell(3);
            cell4.id = "desc-"+data_p[i].id_plato;
            var cell5 = row.insertCell(4);
            cell5.id = "seccion-"+data_p[i].id_plato;
            var cell6 = row.insertCell(5);
            cell6.id = "precio-"+data_p[i].id_plato;
            var cell7 = row.insertCell(6);

            cell1.appendChild(img);
            cell2.innerHTML = data_p[i].id_plato;
            cell3.innerHTML = data_p[i].nombre;
            cell4.innerHTML = data_p[i].descripcion;
            cell5.innerHTML = seccion;
            cell6.innerHTML = data_p[i].precio;
            cell7.appendChild(botonModify);
            cell7.appendChild(botonDelete);
          }
        }
    })
}

function limpiarStoragePlato(){
    localStorage.removeItem('id_plato');
    localStorage.removeItem('nombre_plato');
    localStorage.removeItem('foto_plato');
    localStorage.removeItem('desc_plato');
    localStorage.removeItem('seccion_plato');
    localStorage.removeItem('precio_plato');
}

async function borrarPlato(){
    let request = await fetch("/api/platos/delete/"+plato,{
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


function selectPlato(id_plato) {
    plato = id_plato;
    return;
}

document.addEventListener('DOMContentLoaded',mostrarPlatos());







