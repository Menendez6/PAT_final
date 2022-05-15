precio_total = localStorage.getItem("precio_total");
precio = document.getElementById('precio');
num_tarjeta = document.getElementById('num_tarjeta');
expire = document.getElementById('expire');
cvc = document.getElementById('cvc');
nombre = document.getElementById('nombre');

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

document.addEventListener('DOMContentLoaded', function(event){
	console.log(precio_total);
    precio.innerHTML = 'Paga ' +precio_total + '€';
    precio.onclick = () => {
        
        if (check_CVC(cvc.value) && check_name(nombre.value) && expire_date(expire.value) && cardnumber(num_tarjeta.value)){
            

        }
    }
});