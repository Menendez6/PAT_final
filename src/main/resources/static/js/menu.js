primeros = document.getElementById("primeros");
segundos = document.getElementById("segundos");
postres = document.getElementById("postres");
carro = document.getElementById('carro');
carro.innerHTML = localStorage.getItem('carro');
nombre_rest = document.getElementById('nombre_rest');
direccion = document.getElementById('direc_rest');
const restaurante = localStorage.getItem('restaurante');
const mesa = localStorage.getItem('mesa');
var pedido = localStorage.getItem('pedido');
pedido = JSON.parse(pedido);
const btn_carro = document.getElementById('btn_carro');


// Función para cambiar el nombre de la página en función del restaurante
async function mostrarRestaurante(){
	url ="api/restaurants/"+restaurante;
	fetch(url)
	.then(response => response.json())
	.then(data =>{
		nombre_rest.innerHTML = data.restName;
		direccion.innerHTML = data.direccion;
	})
}

// Función para mostrar los platos en función del restaurante
async function mostrarPlatos(){
	//carro.innerHTML = localStorage.getItem('carro');
	//localStorage.setItem('carro',0);
	url = "/api/platos/"+restaurante;
	fetch(url)
    .then(response => response.json())
	.then(data => {

		for (let i = 0; i<data.length; i++){
			var code = '<div class="col">' +
                          '<div class="row h-auto no-gutters">'+
                            '<div class="col-3">'+
                              '<img src='+data[i].foto+' class="card-img pt-3" alt="...">'+
                            '</div>'+
                            '<div class="col-7">'+
                              '<div class="ml-0 py-2">'+
                                '<h7>'+data[i].nombre+'</h7>'+
                                '<p class="pt-1">'+data[i].descripcion+'</p>'+
                              '</div>'+
                            '</div>'+
                            '<div class="col-2">'+
                              '<div class="ml-0 py-2">'+
                                '<h7>'+data[i].precio+'€</h7>'+
                              '</div>'+
                            '</div>'+
                          '</div>'+
                          '<div class="row no-gutters">'+
                            '<div class="col-7">'+
                            '</div>'+
                            '<div class="col-3 pr-0 pl-4">'+
                                '<div class="qty py-1 pr-0 pl-5">'+
                                        '<span id="minus'+i+'" class="minus bg-secondary">-</span> ' +
                                        '<input id="num'+i+'" type="number" class="count" name="qty" value=1> '+
                                        '<span id="plus'+i+'" class="plus bg-secondary">+</span> '+
                                    '</div>'+
                              '</div>'+
                              '<div class="col-2 float-left px-0">'+
                                '<a id="boton'+i+'" class="btn btn-outline-dark mt-auto btn-anadir py-1 px-1 mt-auto" href="#">Añadir</a></div>'+
                          '</div>'+
                '</div>'+'<hr class="mb-0">'
			
			// Asignamos el plato a una sección en función de si es primero, segundo o postre
			var div1 = document.createElement('div');
			div1.innerHTML = code;
			if (data[i].seccion == "1"){
				primeros.appendChild(div1);
			}
			if (data[i].seccion == "2"){
				segundos.appendChild(div1);
			}
			if (data[i].seccion == "3"){
				postres.appendChild(div1);
			}
			
			
			//Metemos la funcionalidad de poder seleccionar el número de platos que quieres en cada cosa
			document.getElementById('plus'+i).addEventListener('click',function(event){
				if (parseInt(document.getElementById('num'+i).value) != 9){
					document.getElementById('num'+i).value = parseInt(document.getElementById('num'+i).value) + 1;
				}
			})

			document.getElementById('minus'+i).addEventListener('click',function(event){
				if (parseInt(document.getElementById('num'+i).value) != 1){
					document.getElementById('num'+i).value = parseInt(document.getElementById('num'+i).value) - 1;
				}
			})

			//Al darle a añadir se actualiza el carro y la base de datos con los pedidos
			document.getElementById('boton'+i).addEventListener('click',function(event){
				if (pedido[data[i].id_plato]){
					pedido[data[i].id_plato]=parseInt(document.getElementById('num'+i).value) + pedido[data[i].id_plato];
				}else{
					pedido[data[i].id_plato] = parseInt(document.getElementById('num'+i).value);
				}
				carro.innerHTML = parseInt(carro.innerHTML) + parseInt(document.getElementById('num'+i).value);
				//localStorage.setItem('carro',carro.innerHTML);
				localStorage.setItem('pedido',JSON.stringify(pedido));
				localStorage.setItem('carro',carro.innerHTML);
				console.log(pedido);
				document.getElementById('num'+i).value = 1;
				//El id de pedido es la mesa
			})
			
		}
	})
}


/*async function crearPedido(){
	let request = await fetch("api/add_pedido/"+mesa,{
		method: "POST",
		credentials: "same-origin",
		headers:{
			"Content-type": "application/json"
		},
		body: JSON.stringify({
			id: mesa
		}),
		datatype: "json",
	}).catch(console.error);

	if (request.ok){
        console.log(await request.json());
    }
}*/

document.addEventListener('DOMContentLoaded', function(event){
	//crearPedido para la mesa X
	//crearPedido()
	console.log(pedido);
	mostrarRestaurante();
	mostrarPlatos();
});

btn_carro.addEventListener('click', function(event){
	event.preventDefault();
	localStorage.setItem('pedido',JSON.stringify(pedido));
	localStorage.setItem('carro',carro.innerHTML);
	window.location.href = 'carrito.html';
})
