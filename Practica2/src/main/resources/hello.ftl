<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="stylesheet.css"/>
        <title>Práctica 2</title>
    </head>
    <body>
        <h1>Practica 2 - Estudiantes</h1>
        <ul id="bar1">
            <li><a href="/" >Inicio</a></li>
            <li><a href="/crear" >Nuevo Estudiante</a></li>
            <li><a href="/editar" >Editar Estudiantes</a></li>
        </ul>
        </br>
        <table>
            <thead>
                <tr>
                    <th>Matrícula</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Teléfono</th>
                </tr>
            </thead>
            <tbody>
                <#list estudiantes as estudiante>
                <tr>
                    <td>${estudiante.getMatricula()} </td>
                    <td>${estudiante.getNombre()} </td>
                    <td>${estudiante.getApellidos()} </td>
                    <td>${estudiante.getTelefono()} </td>

                </tr>
                </#list>

            </tbody>

        </table>
    </body>
</html>