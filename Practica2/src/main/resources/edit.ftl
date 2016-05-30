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
<form action="/" method="post">
<table>
    <thead>
    <tr>
        <th>Matrícula</th>
        <th>Nombre</th>
        <th>Apellidos</th>
        <th>Teléfono</th>
        <th>Edit</th>

    </tr>
    </thead>
    <tbody>
    <#list estudiantes as estudiante>
    <tr>
        <td>${estudiante.getMatricula()} </td>
        <td>${estudiante.getNombre()} </td>
        <td>${estudiante.getApellidos()} </td>
        <td>${estudiante.getTelefono()} </td>
        <td><input id = "check" type="checkbox" name="check" value="edit" onclick="change ()"></td>
    </tr>
    </#list>

    </tbody>

</table>

<input type = "submit"  value="Eliminar" action="/eliminar" method="get">     <br>
    <input id = "elegido" type = "submit"  value="Editar">
</form>
</body>
<script>
    function change() {

        var count = document.querySelectorAll('input[type="checkbox"]:checked').length;

        if(count>=2) {
            hidden = true;
        }
        else {
            hidden = false;
        }
        if (hidden) {
            document.getElementById('elegido').style.visibility = 'hidden';
        } else {
            document.getElementById('elegido').style.visibility = 'visible';
        }
    }
</script>
</html>