let id_pedido = -1;
let id_cambiado = -1;
let estado = -1;
let num = -1;
let tabla_entregado = false;

confirmados = document.getElementById('confirmados');
listos = document.getElementById('listos');
cocina = document.getElementById('cocina');
entregados = document.getElementById('entregados');

function mostrarPedidos() {
    url = "/api/getPedidos/restaurante/"+localStorage.getItem('restaurante_admin');
    fetch(url)
    .then(response => response.json())
    .then(data => {
        console.log(data);
        data.sort(function(a, b){
            return a.id_pedido - b.id_pedido;
        });


        if (data.length > 0) {
          var temp2 = '';
          confirmados.innerHTML = temp2; //vaciamos las rows previas
          cocina.innerHTML = temp2; //vaciamos las rows previas
          listos.innerHTML = temp2; //vaciamos las rows previas
          entregados.innerHTML = temp2; //vaciamos las rows previas
          for (var i = 0; i < data.length; i++) { // rellenamos fila por fila
            if(data[i].id_pedido != id_pedido) {
                //caso 1: primer plato de un pedido
                id_pedido = data[i].id_pedido;
                num = 1;

                switch(data[i].estado) {
                    case 1:
                        var row = confirmados.insertRow();
                    break;

                    case 2:
                        var row = cocina.insertRow();
                    break;

                    case 3:
                        var row = listos.insertRow();
                    break;

                    case 4:
                        var row = entregados.insertRow();
                        tabla_entregado = true;
                    break;
                }

                if(tabla_entregado) {
                    //si es de la ultima tabla, boton eliminar
                    text_del = document.createTextNode('Eliminar');
                    var botonDelete = document.createElement("button");
                    botonDelete.className = "btn btn-danger btn-outline-white mt-auto py-1 px-2";
                    botonDelete.id = 'delete'+data[i].id_pedido;
                    botonDelete.appendChild(text_del);

                    botonDelete.addEventListener("click",function(event){
                        selectPedido(this.id.substring(6));
                        console.log("dentro");
                        borrarPedido();
                    })

                }
                else {
                    text = document.createTextNode('Cambiar');
                    var botonEstado = document.createElement("button");
                    botonEstado.className = "btn btn-outline-dark mt-auto py-1 px-2";
                    botonEstado.id = 'cambio'+data[i].id_pedido;
                    botonEstado.appendChild(text);

                    botonEstado.addEventListener("click",function(event){
                        selectPedido(this.id.substring(6));
                        estado = document.getElementById('estadoInput'+id_cambiado).value;
                        modificarEstado();
                    })

                }

                var estadoInput = document.createElement("select");
                estadoInput.className = "form-select";
                estadoInput.id = 'estadoInput'+data[i].id_pedido;
                var option1 = document.createElement("option");
                option1.id = "option1-"+data[i].id_pedido;
                option1.value = 1;
                option1.innerHTML = 'Confirmado';
                var option2 = document.createElement("option");
                option2.id = "option2-"+data[i].id_pedido;
                option2.value = 2;
                option2.innerHTML = 'Cocina';
                var option3 = document.createElement("option");
                option3.id = "option3-"+data[i].id_pedido;
                option3.value = 3;
                option3.innerHTML = 'Listo';
                var option4 = document.createElement("option");
                option4.id = "option4-"+data[i].id_pedido;
                option4.value = 4;
                option4.innerHTML = 'Entregado';

                estadoInput.appendChild(option1);
                estadoInput.appendChild(option2);
                estadoInput.appendChild(option3);
                estadoInput.appendChild(option4);

                estadoInput.value = data[i].estado;

                var cell1 = row.insertCell(0);
                cell1.id = 'id'+data[i].id_pedido;
                var cell2 = row.insertCell(1);
                cell2.id = 'mesa'+data[i].id_pedido;
                var cell3 = row.insertCell(2);
                cell3.id = 'precio'+data[i].id_pedido;
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                cell6.id = 'estado'+data[i].id_pedido;
                var cell7 = row.insertCell(6);
                cell7.id = 'boton'+data[i].id_pedido;

                cell1.innerHTML = data[i].id_pedido;
                cell2.innerHTML = data[i].mesa;
                cell3.innerHTML = data[i].precio;
                cell4.innerHTML = data[i].nombre;
                cell5.innerHTML = data[i].num_platos;
                if (tabla_entregado) {
                    cell6.innerHTML = 'Entregado';
                    cell7.appendChild(botonDelete);
                    tabla_entregado = false;
                } else {
                    cell6.appendChild(estadoInput);
                    cell7.appendChild(botonEstado);
                }
            }
            else {
                //caso 2: platos extra de ese pedido
                switch(data[i].estado) {
                    case 1:
                        var row = confirmados.insertRow();
                    break;

                    case 2:
                        var row = cocina.insertRow();
                    break;

                    case 3:
                        var row = listos.insertRow();
                    break;

                    case 4:
                        var row = entregados.insertRow();
                    break;
                }
                
                num++;
                var cell1 = row.insertCell(0);
                document.getElementById('id'+data[i].id_pedido).rowspan = num;
                var cell2 = row.insertCell(1);
                document.getElementById('mesa'+data[i].id_pedido).rowspan = num;
                var cell3 = row.insertCell(2);
                document.getElementById('precio'+data[i].id_pedido).rowspan = num;
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                document.getElementById('estado'+data[i].id_pedido).rowspan = num;
                var cell7 = row.insertCell(6);
                document.getElementById('boton'+data[i].id_pedido).rowspan = num;

                cell4.innerHTML = data[i].nombre;
                cell5.innerHTML = data[i].num_platos;

            }
            
          }
        }
    })
}


async function modificarEstado(){
    let request = await fetch("/api/pedido/update/"+id_cambiado,{
        method: "POST",
        credentials: "same-origin",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            id_pedido: id_cambiado, 
            estado: estado
        }),
        datatype: "json",
    }).catch(console.error);

    if (request.ok){
        location.reload();
        console.log(id_cambiado+" cambiado");
    }
}

async function borrarPedido(){
    let request = await fetch("/api/pedido/"+id_cambiado,{
        method: "DELETE",
        credentials: "same-origin",
        headers: {
            "Content-type": "application/json"
        },
        body: id_cambiado,
        datatype: "json",
    }).catch(console.error);

    if (request.ok){
        location.reload();
        console.log(id_cambiado+" borrado");
    }
}


function selectPedido(pedido) {
    id_cambiado = pedido;
    return;
}

document.addEventListener('DOMContentLoaded',mostrarPedidos());







