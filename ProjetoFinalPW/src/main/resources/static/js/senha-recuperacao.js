$(document).ready(function () {
    $("#formSenhaReset").submit(function (e) {
        e.preventDefault();
        requestSenhaReset();
    });
});

function requestSenhaReset() {
    let url = window.location;
    let baseUrl = url.protocol + "//" + url.host + "/";
    let email = $("#email").val();

    if (email && email !== '') {
        swal.showLoading();
        $.ajax({
            type: 'POST',
            url: baseUrl.concat('login/recuperar-senha-enviar-email'),
            data: {email: email},
            success: function (response) {
                swal.close();
                Swal.fire({
                    title: 'Requisição enviada!',
                    text: `Se o login (${email}) constar na nossa base de dados, você receberá um e-mail em breve.`,
                    type: 'success'
                });
            }, error: function (data) {
                swal.close();
                console.error(data);
                Swal.fire('Erro!', 'Não foi possível enviar a requisição.', 'error');
            }
        });
    }
}