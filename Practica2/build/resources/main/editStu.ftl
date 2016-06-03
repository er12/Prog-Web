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

<form action="/editarX" method="get">
    <h1>Inserte los datos a modificar de ${NombreEd} ${ApellidosEd}</h1>
    Matrícula (Ej.:20120201):<br>
    <input type="text" name="matricula" style="width:150px;height:30px;"><br>
    Nombre:<br>
    <input type="text" name="nombre" style="width:600px;height:30px;"><br>
    Apellidos:<br>
    <input type="text" name="apellidos"style="width:600px;height:30px;"><br>
    Teléfono:<br>
    <input type="text" name="telefono" style="width:600px;height:30px;"><br>
    <br>
    <input type="hidden" name="MatriculaEd" value="${MatriculaEd}" >
    <input type="hidden" name="NombreEd" value="${NombreEd}" >
    <input type="hidden" name="ApellidosEd" value="${ApellidosEd}" >
    <input type="hidden" name="TelefonoEd" value="${TelefonoEd}" >
    <input type="submit" value="Insertar" >
</form>

</body>
</html>