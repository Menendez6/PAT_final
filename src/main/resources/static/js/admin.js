tabla = document.getElementById('data_res');

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
            var boton = document.createElement("button");
            boton.className = "btn btn-outline-dark mt-auto py-1 px-4";
            boton.id = data[i].id;
            boton.innerHTML = "Ver";

            boton.addEventListener("click",function(event){
                selectRestaurante(this.id);
                location.href='admin_acciones.html';
            })

            var row = tabla.insertRow();

            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            cell4.id = 'boton'+data[i].id;

            cell1.innerHTML = data[i].id;
            cell2.innerHTML = data[i].restName;
            cell3.innerHTML = data[i].direccion;
            cell4.appendChild(boton);
            }
        }
    })
}

function selectRestaurante(id_restaurante) {
    localStorage.setItem('restaurante_admin',id_restaurante)
    return;
}

document.addEventListener('DOMContentLoaded',mostrarRestaurantes());