pedido = localStorage.getItem('pedido');
pedido = JSON.parse(pedido);
carro = localStorage.getItem('carro');


platos = document.getElementById('platos');
const restaurante = localStorage.getItem('restaurante');
confirmar = document.getElementById('button_conf');
total = document.getElementById('aqui');
var precio_total = 0;

console.log(pedido);

async function mostrarPlatos(){
	//carro.innerHTML = localStorage.getItem('carro');
	//localStorage.setItem('carro',0);
	url = "/api/platos/"+restaurante;
	fetch(url)
    .then(response => response.json())
	.then(data => {

		for (let i = 0; i<data.length; i++){
            if (pedido[data[i].id_plato]){
                confirmar.disabled = false;
                var precio = parseInt(data[i].precio) * parseInt(pedido[data[i].id_plato]);
                precio_total+= precio;
                //console.log(precio_total);
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
                                    '<h7>'+precio+'€</h7>'+
                                '</div>'+
                                '</div>'+
                            '</div>'+
                            '<div class="row no-gutters">'+
                                '<div class="col-7">'+
                                '</div>'+
                                '<div class="col-3 pr-0 pl-4">'+
                                    '<div class="qty py-1 pr-0 pl-5">'+
                                            '<h5>'+pedido[data[i].id_plato]+'</h5> ' +
                                        '</div>'+
                                '</div>'+
                                '<div class="col-2 float-left px-0">'+
                                    '<a id="boton'+i+'" class="btn btn-danger mt-auto btn-eliminar py-1 px-1 mt-auto" href="#">Eliminar</a></div>'+
                            '</div>'+
                    '</div>'+'<hr class="mb-0">'
                
                // Asignamos todos los platos a la sección
                var div1 = document.createElement('div');
                div1.innerHTML = code;
                platos.appendChild(div1);
                
                


                //Al darle a añadir se actualiza el carro y la base de datos con los pedidos
                document.getElementById('boton'+i).addEventListener('click',function(event){
                    carro = parseInt(carro) - parseInt(pedido[data[i].id_plato]);
                    localStorage.setItem('carro',carro);
                    delete pedido[data[i].id_plato];
                    console.log(pedido);
                    console.log(carro);
                    localStorage.setItem('pedido',JSON.stringify(pedido));
                    window.location.href='carrito.html';
				    
                })
			
		    }
        }

        if (precio_total == 0){
            aqui.innerHTML = "No hay platos seleccionados";
        }else{
            aqui.innerHTML = "Total = " + precio_total + "€";
            localStorage.setItem('precio_total',precio_total);
        }

	})
}

document.addEventListener('DOMContentLoaded', function(event){
	mostrarPlatos();
});