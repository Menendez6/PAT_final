precio_total = localStorage.getItem("precio_total");
precio = document.getElementById('precio');
num_tarjeta = document.getElementById('num_tarjeta');
expire = document.getElementById('expire');
cvc = document.getElementById('cvc');
nombre = document.getElementById('nombre');
mesa_num = localStorage.getItem('mesa');
var id_pedido = 0;
pedido = localStorage.getItem('pedido');
pedido = JSON.parse(pedido);
envio = [];

function delay(n){
    return new Promise(function(resolve){
        setTimeout(resolve,n*1000);
    });
}


function cardnumber(inputtxt)
{
  var cardno = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
  if(cardno.test(inputtxt))
        {
      return true;
        }
      else
        {
        alert("No es un número de visa válido!");
        return false;
        }
}

function expire_date(inputtxt){
    var date_regex = /^(0?[1-9]|1[012])[\/\-]\d{4}$/;
    if (!(date_regex.test(inputtxt))) {
        alert("No es una fecha válida!");
        return false;
    }else{
        var array_fecha = inputtxt.split('/');
        let date = new Date(array_fecha[1],array_fecha[0],1);
        var ToDate = new Date();
        if (date.getTime() <= ToDate.getTime()){
            alert("La tarjeta está caducada");
            return false;
        }else{
            return true;
        }
    }

}

function check_CVC(inputtxt){
    if (inputtxt.length === 3){
        return true
    }else{
        alert('CVC no válido');
        return false;
    }
}

function check_name(inputtxt){
    if (inputtxt.trim() === ""){
        alert('Introduce un nombre');
        return false;
    }
    return true;
}

async function crearPedido(){
	let request = await fetch("api/add_pedido",{
		method: "POST",
		credentials: "same-origin",
		headers:{
			"Content-type": "application/json"
		},
		body: JSON.stringify({
			mesa: mesa_num,
            precio: precio_total
		}),
		datatype: "json",
	}).catch(console.error);

	if (request.ok){
        getId();
        console.log(await request.json());

    }
}

async function getId(){
    fetch("api/getId")
    .then(response => response.json())
	.then(data => {
        id_pedido=data.id;
        for(let item in pedido){
            addPlatos(id_pedido,item);
        }

    })

    //await delay(1);
    window.location.href='confirmado.html';
}

async function addPlatos(id_pedido,item){
    let request = await fetch("api/addPlatoPedido",{
        method: "POST",
        credentials: "same-origin",
        headers:{
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            'id_pedido':id_pedido,
            'id_plato':item,
            'num_platos': pedido[item]
        }),
        datatype: "json",
    }).catch(console.error);

    if (request.ok){
        console.log(await request.json());
    }


}

document.addEventListener('DOMContentLoaded', function(event){
	console.log(precio_total);
    precio.innerHTML = 'Paga ' +precio_total + '€';
    precio.onclick = () => {
        
        if (check_CVC(cvc.value) && check_name(nombre.value) && expire_date(expire.value) && cardnumber(num_tarjeta.value)){
            crearPedido();
            
        }
    }
});