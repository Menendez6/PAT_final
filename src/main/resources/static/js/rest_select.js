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
                location.href='admin.html';
            })

            document.getElementById('boton').appendChild(boton);
            }
        }
    })
}

function selectRestaurante(id_restaurante) {
    localStorage.setItem('restaurante_admin',id_restaurante)
    return;
}

document.addEventListener('DOMContentLoaded',mostrarRestaurantes());