$(document).ready(function () {
    // inicializa a imagem principal
    trocarImagemPrincipalExibida($('#dvImgListaContainer').find('img:first').attr('src'));
});

/*
 * Abre uma nova aba exibindo somente a imagem
 */
function abrirImagemPrincipalEmNovaGuia() {
    window.open($('#dvImgPrincipal').attr('src'), '_blank');
}

/*
 * Realiza a troca da imagem exibida como foto principal
 */
function trocarImagemPrincipalExibida(path) {
    $('#dvImgPrincipal').attr('src', path);
}