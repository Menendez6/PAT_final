var btn = document.querySelector('#confirmar');
const mesa = document.querySelector('#mesa');
id_rest = localStorage.getItem('restaurante');
console.log(id_rest);

mesa.onkeyup = () => {
    if (mesa.value.length > 0){
        btn.disabled = false;
    } else{
        btn.disabled = true;
    }
}

async function getPedidos(){
    fetch("api/getPedidoMesa/"+id_rest+"/"+mesa.value)
	.then(response => response.json())
	.then(data =>{
        if (data.length == 0){
            console.log("aqui");
            window.location.href='menu.html';
        }else{
            console.log('alli');
            window.location.href='pedidos.html';
        }
    })
}

btn.onclick = () => {
    localStorage.setItem('carro','0');
    localStorage.setItem('pedido',JSON.stringify({}));
    localStorage.setItem('mesa',mesa.value);
    getPedidos();
    //window.location.href='menu.html';
}