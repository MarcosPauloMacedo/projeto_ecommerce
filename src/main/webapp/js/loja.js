let notificaocao = document.querySelector('#notificacao')
const alertPlaceholder = document.getElementById('liveAlertPlaceholder')

let alterStatus = () =>{
    let numberNotificacao = parseInt(notificaocao.innerHTML)
    notificaocao.innerHTML = numberNotificacao + 1
}


let appendAlert = (message, type) => {
    const wrapper = document.createElement('div')
    wrapper.innerHTML = [
      `<div class="alert alert-${type} alert-dismissible" role="alert">`,
      `   <div>${message}</div>`,
      '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
      '</div>'
    ].join('')
  
    alertPlaceholder.append(wrapper)
}

const addCarrinho = () => {
    alterStatus()
    appendAlert('Produto adicionado ao carrinho', 'success')
}

let botoes = document.querySelectorAll('#liveAlertBtn')

botoes.forEach((botao) => botao.addEventListener('click', addCarrinho))
