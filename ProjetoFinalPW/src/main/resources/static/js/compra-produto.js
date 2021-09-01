$(document).ready(function () {
    // inicializa a imagem principal
    inicializarCamposQuantidade();
});

/*
 * Adiciona as máscaras dos campos de quantidade de cada produto
 */
function inicializarCamposQuantidade() {
    $('*[id*=quantidadeItem]:visible').each(function () {
        $(this).mask("#.##0", {reverse: true});
    });
}

/*
 * Remove um produto da compra
 */
function removerProdutoCompra(produtoId) {
    removerItemCarrinho(produtoId, reloadProdutos);
}

/*
 * Executa um get via ajax para remontar os produtos da compra
 */
function reloadProdutos() {
    let url = window.location;
    let baseUrl = url.protocol + "//" + url.host + "/";

    $.ajax({
        type: 'GET',
        url: baseUrl.concat('compra/carrinho-itens'),
        success: function (fragment) {
            if (+lerCookie('carrinhoCount') > 0) {
                $("#compraProdutos").replaceWith(fragment);
            } else {
                window.location = '/home';
            }
        }, error: function (data) {
            console.error(data);
            Swal.fire('Erro!', 'Falha ao finalizar ao recarregar os produtos.', 'error');
        }
    });
}

/*
 * Executa no change do input "quantidade" de cada produto
 * * Trata e atualiza os valores dos cookies
 */
function produtoCompraChangeQuantidade(produtoId) {
    let campo = $('#quantidadeItem' + produtoId);
    let valorCampo = campo.val();

    if (+valorCampo >= 1) {
        adicionarNoCarrinho(produtoId, null, +valorCampo, calcularTotalCompra);
    } else if (valorCampo !== '') {
        removerProdutoCompra(produtoId);
    }
}

/*
 * Adiciona 1 na quantidade do produto
 */
function produtoCompraAddQuantidade(produtoId) {
    let campo = $('#quantidadeItem' + produtoId);
    campo.val(+campo.val() + 1);
    produtoCompraChangeQuantidade(produtoId);
}

/*
 * Subtrai 1 na quantidade do produto
 */
function produtoCompraSubtQuantidade(produtoId) {
    let campo = $('#quantidadeItem' + produtoId);
    let valorCampo = +campo.val();

    if (valorCampo > 1) {
        campo.val(valorCampo - 1);
        produtoCompraChangeQuantidade(produtoId);
    } else {
        removerProdutoCompra(produtoId);
    }
}
