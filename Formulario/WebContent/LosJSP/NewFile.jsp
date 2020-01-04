<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Formulario</title>
</head>
<body>
<h1 style='text-align:center'>Formulario de Estudiantes </h1>
<form action="../formServlet" method="get">

	Nombre: <input type="text" value="" name="nombre">
	<br><br>
	Apellido:<input type="text" value="" name="apellido">
	<br><br>
	Edad:<input type="number" value="" name="edad">
	<br><br>
	Carrera:<input type="text" value="" name="carrera">
	<br><br>
	Semestre:<input type="number" value="" name="semestre">
	<br><br>
	<input type="submit" value="Enviar">
	<br><br>
</form >

<form action="../consultarServlet" method="get">
	<input type="submit" value="Consultar">
</form>

</body>
</html>