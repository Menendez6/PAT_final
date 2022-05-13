$(document).ready(function(){
	$('.count').prop('disabled', true);
	$(document).on('click','.plus',function(){
		$('.count').val(parseInt($('.count').val()) + 1 );
	});
	$(document).on('click','.minus',function(){
		$('.count').val(parseInt($('.count').val()) - 1 );
			if ($('.count').val() == 0) {
				$('.count').val(1);
			}
		});
});

primeros = document.getElementById("primeros");
segundos = document.getElementById("segundos");
postres = document.getElementById("postres");

async function mostrarPlatos(){
	var restaurante = localStorage.getItem('restaurante');
	url = "/api/platos/"+restaurante;
	fetch("/api/platos/1")
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
                                        '<span class="minus bg-secondary">-</span> ' +
                                        '<input type="number" class="count" name="qty" value="1"> '+
                                        '<span class="plus bg-secondary">+</span> '+
                                    '</div>'+
                              '</div>'+
                              '<div class="col-2 float-left px-0">'+
                                '<a class="btn btn-outline-dark mt-auto btn-anadir py-1 px-1 mt-auto" href="#">Añadir</a></div>'+
                          '</div>'+
                '</div>'+'<hr class="mb-0">'

			var div1 = document.createElement('div');
			div1.innerHTML = code;
			primeros.appendChild(div1);
		}
	})
}

document.addEventListener('DOMContentLoaded',mostrarPlatos());
