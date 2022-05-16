pedidos = document.getElementById('pedidos');
mesa = localStorage.getItem('mesa');
id_rest = localStorage.getItem('restaurante');

async function mostrarPedidos(){
    fetch("api/getPedidoMesa/"+id_rest+"/"+mesa)
	.then(response => response.json())
	.then(data_n =>{
        for (let i = 0; i<data_n.length; i++){
            fetch("api/getPedido2/"+data_n[i].id_pedido)
            .then(response => response.json())
            .then(data =>{
                div1 = document.createElement('div');
                div1.className = 'container';

                titulo = document.createElement('h2');
                titulo.innerHTML = 'Id Pedido: ' + data_n[i].id_pedido;
                div1.appendChild(titulo);

                for (let j = 0; j<data.length; j++){
                    plato = document.createElement('p');
                    plato.innerHTML = data[j].num_platos+'x ' + data[j].nombre;
                    div1.appendChild(plato)
                }

                precio = document.createElement('h5');
                precio.innerHTML = 'Total: '+data[0].precio +'€';
                div1.appendChild(precio);

                estado = document.createElement('button');
                estado.innerHTML = 'Estado de mi pedido';

                estado.addEventListener('click',function(event){
                    localStorage.setItem('id_pedido',data_n[i].id_pedido);
                    window.location.href='estado.html';
                })

                div1.appendChild(estado);
                div1.appendChild(document.createElement('hr'));

                pedidos.appendChild(div1);
            })
        }
    })
}

document.addEventListener('DOMContentLoaded',mostrarPedidos());