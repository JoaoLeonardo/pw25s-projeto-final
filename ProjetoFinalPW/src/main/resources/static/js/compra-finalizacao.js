$(document).ready(function () {
    $("#frm").submit(function (e) {
        e.preventDefault();
        finalizarCompra();
    });
});

/*
 * Executa a finalização da compra
 */
function finalizarCompra() {
    let freteSelecionado = $('#freteId').val();

    $.ajax({
        type: $('#frm').attr('method'),
        url: $('#frm').attr('action'),
        data: {freteId: freteSelecionado},
        success: function () {
            Swal.fire({
                title: 'Compra concluída!',
                text: 'A compra finalizada com sucesso!',
                type: 'success'
            }).then((result) => {
                    window.location = '/home';
                    limparCarrinho();
                }
            );
        }, error: function (data) {
            console.error(data);
            Swal.fire('Erro!', 'Falha ao finalizar a compra.', 'error');
        }
    });
}