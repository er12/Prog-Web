<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="stylesheet.css"/>
    <title>Práctica 2</title>
</head>
<body>
<h1>20120201</h1>
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
        <td><form><input type="checkbox" name="check" value="edit"></form></td>

    </tr>
    </#list>

    </tbody>

</table>


</body>
<script>
    function changeImage() {
        var image = document.getElementById('myImage');
        if (image.src.match("bulbon")) {
            image.src = "pic_bulboff.gif";
        } else {
            image.src = "pic_bulbon.gif";
        }
    }
    if ((Type == 2 && PageCount == 0) || (Type == 2 && PageCount == '')) {
        PageCount= document.getElementById('<%=hfPageCount.ClientID %>').value;
    }
</script>
</html>