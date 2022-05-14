primeros = document.getElementById("primeros");
segundos = document.getElementById("segundos");
postres = document.getElementById("postres");
carro = document.getElementById('carro');

async function mostrarPlatos(){
	//carro.innerHTML = localStorage.getItem('carro');
	localStorage.setItem('carro',0);
	var restaurante = localStorage.getItem('restaurante');
	url = "/api/platos/"+restaurante;
	fetch(url)
    .then(response => response.json())
	.then(data => {
		console.log(data);
		console.log(primeros);

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
                                '<!-- <a class="btn btn-outline-dark mt-auto" href="#">Añadir</a></div>-->'+
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

			document.getElementById('boton'+i).addEventListener('click',function(event){
				carro.innerHTML = parseInt(localStorage.getItem('carro')) + parseInt(document.getElementById('num'+i).value);
				localStorage.setItem('carro',carro.innerHTML);
			})
			
		}
	})
}

document.addEventListener('DOMContentLoaded',mostrarPlatos());
