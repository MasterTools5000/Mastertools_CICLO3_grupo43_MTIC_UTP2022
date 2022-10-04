var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {
        
        $("#mi-perfil-btn").attr("href","profile.html?username=" + username);
        
        $("#user-saldo").html(user.saldo.toFixed(2) + "$");

        getHerramientas(false, "ASC");

        $("#ordenar-tipo").click(ordenarHerramientas);
    });
});


async function getUsuario() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });

}
function getHerramientas(ordenar, orden) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletHerramientaListar",
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                mostrarHerramientas(parsedResult);
            } else {
                console.log("Error recuperando los datos de las herramientas");
            }
        }
    });
}
function mostrarHerramientas(herramientas) {

    let contenido = "";

    $.each(herramientas, function (index, herramienta) {

        herramienta = JSON.parse(herramienta);
        let precio;

        if (herramienta.copias > 0) {

            if (user.premium) {

                if (herramienta.novedad) {
                    precio = (50000 - (2 * 0.1));
                } else {
                    precio = (10000 - (1 * 0.1));
                }
            } else {
                if (herramienta.novedad) {
                    precio = 50000;
                } else {
                    precio = 10000;
                }
            }

            contenido += '<tr><th scope="row">' + herramienta.id + '</th>' +
                    '<td>' + herramienta.titulo + '</td>' +
                    '<td>' + herramienta.tipo + '</td>' +
                    '<td>' + herramienta.marca + '</td>' +
                    '<td>' + herramienta.copias + '</td>' +
                    '<td><input type="checkbox" name="novedad" id="novedad' + herramienta.id + '" disabled ';
            if (herramienta.novedad) {
                contenido += 'checked';
            }
            contenido += '></td>' +
                    '<td>' + precio + '</td>' +
                    '<td><button onclick="alquilarHerramienta(' + herramienta.id + ',' + precio + ');" class="btn btn-success" ';
            if (user.saldo < precio) {
                contenido += ' disabled ';
            }

            contenido += '>Reservar</button></td></tr>'

        }
    });
    $("#herramientas-tbody").html(contenido);
}

function ordenarHerramientas() {

    if ($("#icono-ordenar").hasClass("fa-sort")) {
        getHerramientas(true, "ASC");
        $("#icono-ordenar").removeClass("fa-sort");
        $("#icono-ordenar").addClass("fa-sort-down");
    } else if ($("#icono-ordenar").hasClass("fa-sort-down")) {
        getHerramientas(true, "DESC");
        $("#icono-ordenar").removeClass("fa-sort-down");
        $("#icono-ordenar").addClass("fa-sort-up");
    } else if ($("#icono-ordenar").hasClass("fa-sort-up")) {
        getHerramientas(false, "ASC");
        $("#icono-ordenar").removeClass("fa-sort-up");
        $("#icono-ordenar").addClass("fa-sort");
    }
}
function alquilarHerramienta(id, precio) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletHerramientaAlquilar",
        data: $.param({
            id: id,
            username: username

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                restarDinero(precio).then(function () {
                    location.reload();
                })
            } else {
                console.log("Error en la reserva de la película");
            }
        }
    });
}


async function restarDinero(precio) {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioRestarDinero",
        data: $.param({
            username: username,
            saldo: parseFloat(user.saldo - precio)

        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                console.log("Saldo actualizado");
            } else {
                console.log("Error en el proceso de pago");
            }
        }
    });
}