id_pedido = localStorage.getItem('id_pedido');
titulo = document.getElementById('titulo');
subtitulo = document.getElementById('subtitulo');

//Evita que se pueda volver atrás
history.forward();

async function getPedido(){
    fetch("api/getPedido/"+id_pedido)
    .then(response => response.json())
	.then(data => {
        if (data.estado == 1){
            titulo.innerHTML = "El estado de su pedido es: confirmado";
            subtitulo.innerHTML = "Empezarán a cocinarlo cuanto antes";
        }else if(data.estado == 2){
            titulo.innerHTML = "El estado de su pedido es: en cocina";
            subtitulo.innerHTML = "Vete preparando que en nada está en la mesa";
        }else if(data.estado == 3){
            titulo.innerHTML = "El estado de su pedido es: listo";
            subtitulo.innerHTML = "Ya te lo llevamos a la mesa";
        }

    })
}

document.addEventListener('DOMContentLoaded',getPedido());