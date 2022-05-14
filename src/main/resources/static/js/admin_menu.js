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
            temp += '<td class="text-center"><button class="btn btn-outline-dark mt-auto py-1 px-4 mt-auto" id="' + data[i].id +'">Ver</button></td></tr>';

            document.getElementById(data[i].id).addEventListener("click",function(event){
                restaurante=data[i].id;
            })
        }
         document.getElementById('data_res').innerHTML = temp; // metemos las filas ya modificadas

        }
    })
}

function mostrarPlatos() {
    url = "/api/platos/"+restaurante;
    fetch(url)
    .then(response => response.json())
    .then(data_p => {
        console.log(data_p);
        console.log(data_p.length);

        if (data_p.length > 0) {
          var temp2 = '';
          document.getElementById('data_platos').innerHTML = temp2; //vaciamos las rows previas
          for (var i = 0; i < data_p.length; i++) { // rellenamos fila por fila
            var imagen += datos[i].foto;
            temp += '<tr>';
            temp += '<td><img src="'+ imagen +'" height="120px" width="auto"></td>';
            temp2 += '<td>' + data_p[i].id_plato + '</td>';
            temp2 += '<td>' + data_p[i].nombre + '</td>';
            temp2 += '<td>' + data_p[i].descripcion + '</td>';
            temp2 += '<td>' + data_p[i].seccion + '</td>';
            temp2 += '<td>' + data_p[i].precio + '</td>';
             temp2 += '<td class="text-center"><button class="btn btn-outline-dark mt-auto py-1 px-4 mt-auto" id="modify-' + data_p[i].id_plato +'">Modificar</button>';
            temp2 += '<button class="btn btn-danger btn-outline-dark mt-auto py-1 px-4 mt-auto" id="delete-' + data_p[i].id_plato +'">Eliminar</button></td></tr>';
        }
         document.getElementById('data_platos').innerHTML = temp2; // metemos las filas ya modificadas

        }
    })
}

document.addEventListener('DOMContentLoaded',mostrarRestaurantes())