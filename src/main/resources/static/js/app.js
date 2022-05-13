const main_div = document.getElementById("main_div");

async function mostrarRestaurantes(){
    fetch("/api/restaurants")
    .then(response => response.json())
    .then(data => {
        console.log(data);

        for (let i = 0; i<data.length; i++){
            var div1 = document.createElement('div');
            div1.className = "col mb-5";
            main_div.appendChild(div1);

            var div2 = document.createElement('div');
            div2.className = "card h-100";
            div1.appendChild(div2);

            var imagen = document.createElement('img');
            imagen.src = data[i].foto;
            div2.appendChild(imagen);

            var div3 = document.createElement('div');
            div3.className = "card-body p-4";
            div2.appendChild(div3);

            var div4 = document.createElement('div');
            div4.className = "text-center";
            div3.appendChild(div4);

            var nombre = document.createElement('h5');
            nombre.className = "fw-bolder";
            nombre.innerHTML=data[i].restName;
            div4.appendChild(nombre);

            var direccion = document.createElement("p");
            direccion.innerHTML = data[i].direccion;
            div4.appendChild(direccion);
            //div4.innerHTML = data[i].direccion;

            var div5 = document.createElement('div');
            div5.className = "card-footer p-4 pt-0 border-top-0 bg-transparent";
            div2.appendChild(div5);

            var div_button = document.createElement('div');
            div_button.className = "text-center";
            div5.appendChild(div_button);

            var boton = document.createElement("a");
            boton.className = "btn btn-outline-dark mt-auto";
            boton.href = "mesa.html";
            boton.innerHTML = "Pedir";
            div_button.appendChild(boton);

            boton.addEventListener("click",function(event){
                localStorage.setItem('restaurante',data[i].id);
            })


        }
    })
}

document.addEventListener('DOMContentLoaded',mostrarRestaurantes())