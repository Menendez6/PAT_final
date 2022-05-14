var btn = document.querySelector('#confirmar');
const mesa = document.querySelector('#mesa')

mesa.onkeyup = () => {
    if (mesa.value.length > 0){
        btn.disabled = false;
    } else{
        btn.disabled = true;
    }
}

btn.onclick = () => {
    localStorage.setItem('carro','0');
    localStorage.setItem('pedido',JSON.stringify({}));
    localStorage.setItem('mesa',mesa.value);
    window.location.href='menu.html';
}