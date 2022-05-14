const main_div = document.getElementById("main_div");
let restaurante = 0;

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
            temp2 += '<tr>';
            temp2 += '<td><img src="'+ imagen +'" height="120px" width="auto"></td>';
            temp2 += '<td>' + data_p[i].id_plato + '</td>';
            temp2 += '<td>' + data_p[i].nombre + '</td>';
            temp2 += '<td>' + data_p[i].descripcion + '</td>';
            temp2 += '<td>' + data_p[i].seccion + '</td>';
            temp2 += '<td>' + data_p[i].precio + '</td>';
            temp2 += '<td class="text-center"><button class="btn btn-outline-dark mt-auto py-1 px-3 mt-auto" id="modify-' + data_p[i].id_plato +'">Modificar</button>';
            temp2 += '<button class="btn btn-danger btn-outline-dark mt-auto py-1 px-3 mt-auto" id="delete-' + data_p[i].id_plato +'">Eliminar</button></td></tr>';
        }
         document.getElementById('data_platos').innerHTML = temp2; // metemos las filas ya modificadas

        }
    })
}

document.addEventListener('DOMContentLoaded',mostrarRestaurantes())